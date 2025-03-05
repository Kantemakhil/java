CREATE USER OMS_CONV
   IDENTIFIED BY oms_conv
   DEFAULT TABLESPACE TAG_DATA  
   TEMPORARY TABLESPACE temp
   PROFILE DEFAULT;

GRANT CONNECT TO oms_conv;
GRANT CREATE SESSION TO oms_conv;
GRANT RESOURCE TO oms_conv;
GRANT UNLIMITED TABLESPACE TO oms_conv;
GRANT DEBUG CONNECT SESSION  to oms_conv;

GRANT CREATE TABLE, CREATE VIEW, CREATE SEQUENCE, CREATE PROCEDURE, CREATE TYPE, CREATE ANY INDEX TO oms_conv;
GRANT CREATE PUBLIC SYNONYM, DROP PUBLIC SYNONYM TO oms_conv; 
GRANT EXECUTE ANY PROCEDURE TO oms_conv;
GRANT ALTER ANY SEQUENCE TO oms_conv;
GRANT ALTER ANY TRIGGER TO oms_conv;
GRANT ALTER ANY TABLE TO oms_conv;


GRANT EXECUTE ON DBMS_STATS TO oms_conv; 
GRANT SELECT ON DBA_OBJECTS TO oms_conv; 
GRANT SELECT ON DBA_TRIGGERS TO oms_conv;
GRANT SELECT ON DBA_CONSTRAINTS TO oms_conv;
GRANT SELECT ON DBA_SEQUENCES TO oms_conv;

grant analyze any dictionary to oms_conv;
grant analyze any to oms_conv;

GRANT SELECT ON V_$SESSION TO oms_conv; 
GRANT SELECT ON V_$INSTANCE TO oms_conv; 

BEGIN
   FOR t IN (SELECT object_name, object_type 
               FROM dba_objects 
               WHERE owner='OMS_OWNER' 
                 AND object_type IN ('TABLE','VIEW','PROCEDURE','FUNCTION','PACKAGE', 'SEQUENCE') 
                 AND status = 'VALID' 
             ) 
   LOOP 
      BEGIN 
         IF t.object_type IN ('TABLE','VIEW') THEN
            EXECUTE IMMEDIATE 'GRANT SELECT, UPDATE, INSERT, DELETE ON OMS_OWNER.' || t.object_name || ' TO OMS_CONV';
         ELSIF t.object_type IN ('SEQUENCE') THEN
            EXECUTE IMMEDIATE 'GRANT SELECT ON OMS_OWNER.' || t.object_name || ' TO OMS_CONV';
         ELSIF t.object_type IN ('PROCEDURE','FUNCTION','PACKAGE') THEN
            EXECUTE IMMEDIATE 'GRANT EXECUTE ON OMS_OWNER.' || t.object_name || ' TO OMS_CONV';
         END IF; 
      EXCEPTION 
      WHEN OTHERS THEN 
           dbms_output.put_line(t.object_name || '***' || SQLERRM);
      END;
   END LOOP;
END;
/

--create the staff member associated with user OMS_CONV 
insert into staff_members(staff_id, assigned_caseload_id, working_caseload_id, user_id, last_name, first_name
                         , position, update_allowed_flag, suspended_flag, personnel_type, as_of_date, status
                         , queue_cluster_id, first_logon_flag)
SELECT staff_id.nextval, NULL, NULL, 'OMS_CONV', 'CONVERSION', 'CONVERSION'
      ,'CONV', 'Y', 'N', 'CONV', SYSDATE, 'ACTIVE'
      ,1, 'Y' 
   FROM dual 
 WHERE NOT EXISTS (SELECT 1 FROM staff_members WHERE user_id = 'OMS_CONV');

COMMIT;


--grant access to stage schema tables to OMS_CONV
GRANT SELECT ON OMS_STAGE.STAGE_OFFENDERS TO OMS_CONV;
GRANT SELECT ON OMS_STAGE.STAGE_BOOKINGS TO OMS_CONV;
GRANT SELECT ON OMS_STAGE.STAGE_MOVEMENTS TO OMS_CONV;
GRANT SELECT ON OMS_STAGE.STAGE_HOUSING TO OMS_CONV;
GRANT SELECT ON OMS_STAGE.STAGE_OFF_PROFILES TO OMS_CONV;
GRANT SELECT ON OMS_STAGE.STAGE_OFF_P_ATTRIBUTES TO OMS_CONV;
GRANT SELECT ON OMS_STAGE.STAGE_IMAGE TO OMS_CONV;
GRANT SELECT ON OMS_STAGE.STAGE_ADDRESSES TO OMS_CONV;
GRANT SELECT ON OMS_STAGE.STAGE_PHONES TO OMS_CONV;



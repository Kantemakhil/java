insert into oms_owner.STAFF_ACCESSIBLE_CASELOADS(CASELOAD_ID ,STAFF_ID ,UPDATE_ALLOWED_FLAG ,CREATE_DATETIME ,CREATE_USER_ID ,MODIFY_DATETIME, MODIFY_USER_ID )
 SELECT (select caseload_id from caseloads where caseload_type = 'INST' order by caseload_id limit 1), (select STAFF_ID from STAFF_MEMBERS where USER_ID = 'SYSTEM') , 'Y' 
     , current_timestamp , 'OMS_OWNER' , current_timestamp , 'OMS_OWNER' 
  WHERE NOT EXISTS (SELECT 1 FROM STAFF_ACCESSIBLE_CASELOADS WHERE staff_id = (select STAFF_ID from STAFF_MEMBERS where USER_ID = 'SYSTEM'));
 
 
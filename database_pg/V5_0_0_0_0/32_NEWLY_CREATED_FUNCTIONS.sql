CREATE OR REPLACE FUNCTION FUNC_GET_NEXTBUTTON(P_LIVING_UNIT_ID bigint)
 RETURNS character varying
 LANGUAGE plpgsql
AS $function$
   DECLARE 
      GET_NEXTBUTTON CURSOR
      FOR
         select case when (select count(1) from V_LIVING_UNIT_SUMMARIES where parent_living_unit_id=P_LIVING_UNIT_ID)>0 then 'Y'
     else  'N' end;
      NEXTBUTTON   character varying;
   BEGIN
      OPEN GET_NEXTBUTTON;

      FETCH GET_NEXTBUTTON
       INTO NEXTBUTTON;

      CLOSE GET_NEXTBUTTON;

      RETURN NEXTBUTTON;
   EXCEPTION
      WHEN OTHERS
      THEN
        raise '% %',sqlerrm,sqlstate;
   END ;
$function$
;

CREATE OR REPLACE PROCEDURE oms_owner.drop_dynamic_tables()
 LANGUAGE plpgsql
AS $procedure$
  DECLARE 
    r RECORD;
BEGIN
  FOR r IN 
    (
      select module_name||'_data' as table_name from module_dynamic_forms mdf
    ) 
  LOOP
     EXECUTE 'DROP TABLE IF EXISTS ' || r.table_name || ' CASCADE';
  END LOOP;
  delete from oms_modules A where module_name=(select module_name from module_dynamic_forms B where A.MODULE_NAME=B.MODULE_NAME);
  delete from module_dynamic_forms;
  commit;
 EXCEPTION
      WHEN OTHERS
      THEN
        raise '% %',sqlerrm,sqlstate;
END $procedure$
;

insert into oms_owner.STAFF_ACCESSIBLE_CASELOADS(CASELOAD_ID ,STAFF_ID ,UPDATE_ALLOWED_FLAG ,CREATE_DATETIME ,CREATE_USER_ID ,MODIFY_DATETIME)
 values((select caseload_id from caseloads where caseload_type = 'INST' order by caseload_id limit 1), (select STAFF_ID from STAFF_MEMBERS where USER_ID = 'SYSTEM') , 'Y' , current_timestamp , 'OMS_OWNER' , null);
 
 
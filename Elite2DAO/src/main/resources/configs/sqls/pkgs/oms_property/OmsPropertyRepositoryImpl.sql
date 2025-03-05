CHECK_STORAGE_CAPACITY{
select 1 from dual where exists ( select a.internal_location_id from int_loc_usage_locations a, internal_location_usages b where a.internal_location_id = :p_property_storage_id and a.internal_location_usage_id = b.internal_location_usage_id and b.internal_location_usage = 'PROP' and a.capacity > ( select COUNT(*) from offender_ppty_containers c where c.internal_location_id = a.internal_location_id ) )
}

GET_TRAN_ROOM_STORAGE_ID{
select c.internal_location_id from internal_location_usages ils, int_loc_usage_locations ilus, ( select internal_location_code, description, user_desc, internal_location_type, internal_location_id from agency_internal_locations where internal_location_code = 'TR' ) c where ils.internal_location_usage_id = ilus.internal_location_usage_id and ilus.internal_location_id = c.internal_location_id and ilus.parent_usage_location_id is null and ils.agy_loc_id = :p_agy_loc_id and ils.internal_location_usage = 'PROP'  
}
OFFENDER_PPTY_ITEMS_UPDATE{
 update offender_ppty_items set status_code = :p_status_code, agy_loc_id = :p_agy_loc_id, MODIFY_USER_ID = :modifyUserId , MODIFY_DATETIME = CURRENT_TIMESTAMP where property_container_id = :p_property_container_id
 }
 
 OMS_PROPERTY_UPDATE{
 UPDATE offender_ppty_containers SET seal_mark = NULL WHERE property_container_id = :p_property_container_id AND seal_mark IS NOT NULL
 }
OMS_PROPERTY_SELECT{ 
SELECT agy_loc_id FROM staff_members staff, staff_accessible_caseloads staff_ac, caseloads csld, caseload_agency_locations csld_al WHERE staff.user_id = upper(USER) AND staff_ac.caseload_id = staff.working_caseload_id AND staff_ac.staff_id = staff.staff_id AND staff_ac.caseload_id = csld.caseload_id AND csld_al.caseload_id = csld.caseload_id AND csld_al.agy_loc_id NOT IN ('OUT', 'TRN') AND csld.access_property_flag = 'Y'
}

GET_CON_AGY_LOC_SELECT{
SELECT agy_loc_id FROM offender_ppty_containers WHERE property_container_id = :p_property_container_id
}

OMS_OFFENDER_PPTY_ITEMS_GETTING_OLD_DATA{
select * from OFFENDER_PPTY_ITEMS where PROPERTY_CONTAINER_ID = :property_container_id
}

 OIMILOCA_FIND_RGAGYLOC {
SELECT description, al.agy_loc_id code, al.active_flag  FROM agency_locations al WHERE agency_location_type = 'INST' AND ( ( active_flag = 'Y' AND deactivation_date IS NULL ) OR 'ENTER-QUERY' = 'ENTER-QUERY' ) AND al.agy_loc_id IN ( SELECT ca.agy_loc_id FROM caseload_agency_locations ca WHERE ca.caseload_id = ( SELECT working_caseload_id FROM staff_members WHERE user_id = :P_USER_ID ) ) AND al.agy_loc_id NOT IN ( 'TRN', 'OUT' ) ORDER BY list_seq
}

OIMILOCA_FIND_RGLEVELTYPE {
SELECT code, description FROM reference_codes WHERE domain = 'ILOC_TYPE' AND ( ( active_flag = 'Y' AND expired_date IS NULL ) ) ORDER BY list_seq, description
}

OIMILOCA_INTLOCL1_FIND_AGENCY_INTERNAL_LOCATIONS {
select INTERNAL_LOCATION_ID, INTERNAL_LOCATION_TYPE, INTERNAL_LOCATION_CODE, AGY_LOC_ID, DESCRIPTION, SECURITY_LEVEL_CODE, CAPACITY, CREATE_USER_ID, PARENT_INTERNAL_LOCATION_ID, ACTIVE_FLAG, LIST_SEQ, CREATE_DATETIME, MODIFY_DATETIME, MODIFY_USER_ID, CNA_NO, CERTIFIED_FLAG, DEACTIVATE_DATE, REACTIVATE_DATE, DEACTIVATE_REASON_CODE, COMMENT_TEXT, USER_DESC, ACA_CAP_RATING, UNIT_TYPE, OPERATION_CAPACITY, NO_OF_OCCUPANT, TRACKING_FLAG, SEAL_FLAG, ( select count(*) from offender_inter_mvmt_locations oiml where agency_iml_id in (with recursive parent_records as ( select internal_location_id, parent_internal_location_id from agency_internal_locations where internal_location_id = ail.internal_location_id union all select child.internal_location_id, child.parent_internal_location_id from agency_internal_locations child inner join parent_records parent on parent.parent_internal_location_id = child.internal_location_id ) select internal_location_id from parent_records)) as linked_offender_count, ( select count(*) from offender_inter_mvmt_locations oiml where agency_iml_id in (with recursive cte as ( select lu1.internal_location_id, lu1.parent_internal_location_id from agency_internal_locations as lu1 where lu1.internal_location_id = ail.internal_location_id union select lu2.internal_location_id, lu2.parent_internal_location_id from agency_internal_locations lu2 join cte on cte.internal_location_id = lu2.parent_internal_location_id ) select cte.internal_location_id from cte)) as linked_offender_count1  from AGENCY_INTERNAL_LOCATIONS ail
}
OIMILOCA_INTLOCL1_INSERT_AGENCY_INTERNAL_LOCATIONS {
 insert into AGENCY_INTERNAL_LOCATIONS(INTERNAL_LOCATION_ID, INTERNAL_LOCATION_CODE, AGY_LOC_ID, INTERNAL_LOCATION_TYPE, DESCRIPTION, SECURITY_LEVEL_CODE, CAPACITY, CREATE_USER_ID, PARENT_INTERNAL_LOCATION_ID, ACTIVE_FLAG, LIST_SEQ, CREATE_DATETIME, MODIFY_DATETIME, CNA_NO, CERTIFIED_FLAG, DEACTIVATE_DATE, REACTIVATE_DATE, DEACTIVATE_REASON_CODE, COMMENT_TEXT, USER_DESC, ACA_CAP_RATING, UNIT_TYPE, OPERATION_CAPACITY, NO_OF_OCCUPANT, TRACKING_FLAG, SEAL_FLAG) values(:internalLocationId, :internalLocationCode, :agyLocId, :internalLocationType, :description, :securityLevelCode, :capacity, :createUserId, :parentInternalLocationId, :activeFlag, :listSeq, current_timestamp, NULL, :cnaNo, :certifiedFlag, :deactivateDate, :reactivateDate, :deactiveReasonCode, :commentText, :userDesc, :acaCapRating, :unitType, :operationCapacity, :noOfOccupant, :trackingFlag, :sealFlag) 
}

OIMILOCA_INTLOCL1_UPDATE_AGENCY_INTERNAL_LOCATIONS {
   update AGENCY_INTERNAL_LOCATIONS set INTERNAL_LOCATION_ID = :internalLocationId, INTERNAL_LOCATION_CODE = :internalLocationCode, AGY_LOC_ID = :agyLocId, INTERNAL_LOCATION_TYPE = :internalLocationType, DESCRIPTION = :description, SECURITY_LEVEL_CODE = :securityLevelCode, CAPACITY = :capacity, PARENT_INTERNAL_LOCATION_ID = :parentInternalLocationId, ACTIVE_FLAG = :activeFlag, LIST_SEQ = :listSeq, MODIFY_DATETIME = current_timestamp, MODIFY_USER_ID = :modifyUserId, CNA_NO = :cnaNo, CERTIFIED_FLAG = :certifiedFlag, DEACTIVATE_DATE = :deactivateDate, REACTIVATE_DATE = :reactivateDate, DEACTIVATE_REASON_CODE = :deactiveReasonCode, COMMENT_TEXT = :commentText, USER_DESC = :userDesc, ACA_CAP_RATING = :acaCapRating, UNIT_TYPE = :unitType, OPERATION_CAPACITY = :operationCapacity, NO_OF_OCCUPANT = :noOfOccupant, TRACKING_FLAG = :trackingFlag, SEAL_FLAG = :sealFlag where INTERNAL_LOCATION_ID = :internalLocationId
}

OIMILOCA_INTLOCL1_DELETE_AGENCY_INTERNAL_LOCATIONS { 
	  DELETE FROM AGENCY_INTERNAL_LOCATIONS  WHERE INTERNAL_LOCATION_ID = :internalLocationId

}

OIMILOCA_INTLOCL2_FIND_AGENCY_INTERNAL_LOCATIONS {
select INTERNAL_LOCATION_ID,INTERNAL_LOCATION_TYPE, INTERNAL_LOCATION_CODE, AGY_LOC_ID, DESCRIPTION, SECURITY_LEVEL_CODE, CAPACITY, CREATE_USER_ID, PARENT_INTERNAL_LOCATION_ID, ACTIVE_FLAG, LIST_SEQ, CREATE_DATETIME, MODIFY_DATETIME, MODIFY_USER_ID, CNA_NO, CERTIFIED_FLAG, DEACTIVATE_DATE, REACTIVATE_DATE, DEACTIVATE_REASON_CODE, COMMENT_TEXT, USER_DESC, ACA_CAP_RATING, UNIT_TYPE, OPERATION_CAPACITY, NO_OF_OCCUPANT, TRACKING_FLAG, SEAL_FLAG, ( select count(*) from offender_inter_mvmt_locations oiml where agency_iml_id in (with recursive parent_records as ( select internal_location_id, parent_internal_location_id from agency_internal_locations where internal_location_id = ail.internal_location_id union all select child.internal_location_id, child.parent_internal_location_id from agency_internal_locations child inner join parent_records parent on parent.parent_internal_location_id = child.internal_location_id ) select internal_location_id from parent_records)) as linked_offender_count, ( select count(*) from offender_inter_mvmt_locations oiml where agency_iml_id in (with recursive cte as ( select lu1.internal_location_id, lu1.parent_internal_location_id from agency_internal_locations as lu1 where lu1.internal_location_id = ail.internal_location_id union select lu2.internal_location_id, lu2.parent_internal_location_id from agency_internal_locations lu2 join cte on cte.internal_location_id = lu2.parent_internal_location_id ) select cte.internal_location_id from cte)) as linked_offender_count1 from AGENCY_INTERNAL_LOCATIONS ail where AGY_LOC_ID = :AGYLOCID and PARENT_INTERNAL_LOCATION_ID = :PARENTINTERNALLOCATIONID order by list_seq
}

OIMILOCA_INT_LOC_L1_ONCHECKDELETEMASTER_ {
	SELECT 1 FROM AGENCY_INTERNAL_LOCATIONS A WHERE A.PARENT_INTERNAL_LOCATION_ID = :INTERNALLOCATIONID

}

OIMILOCA_INT_LOC_L2_ONCHECKDELETEMASTER_ {
	SELECT 1 FROM AGENCY_INTERNAL_LOCATIONS A WHERE A.PARENT_INTERNAL_LOCATION_ID = :INTERNALLOCATIONID
		

}

OIMILOCA_INT_LOC_L3_ONCHECKDELETEMASTER_ {
	SELECT 1 FROM AGENCY_INTERNAL_LOCATIONS A WHERE A.PARENT_INTERNAL_LOCATION_ID = :INTERNALLOCATIONID
	
}

OIMILOCA_CONSTRAINT_VALIDATIONS {
  select tc.table_name from information_schema.table_constraints as tc join information_schema.key_column_usage as kcu on tc.constraint_name = kcu.constraint_name and tc.table_schema = kcu.table_schema join information_schema.constraint_column_usage as ccu on ccu.constraint_name = tc.constraint_name and ccu.table_schema = tc.table_schema where tc.constraint_type = 'FOREIGN KEY' and upper(ccu.table_name)= 'AGENCY_INTERNAL_LOCATIONS' and upper(tc.CONSTRAINT_NAME)= :CONSTRAINTNAME and upper(tc.constraint_schema)= 'OMS_OWNER'

}
OIMILOCA_GETTING_OLD_DATA{
select * from AGENCY_INTERNAL_LOCATIONS where INTERNAL_LOCATION_ID=:internalLocationId
}

OIMILOCA_FIND_BOTHLOV {
select  * from reference_codes rc where domain = :domain order by list_seq
} 
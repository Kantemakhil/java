QUERY_INTERNAL_LOCATIONS_QUERY_ONE {
	 SELECT a.internal_location_code,
                    a.description,
                    b.location_level,
                    a.internal_location_id,
                    a.parent_internal_location_id,
                    a.unit_type
               FROM (SELECT internal_location_code,
                            description,
                            internal_location_id,
                            parent_internal_location_id,
                            unit_type
                       FROM agency_internal_locations
                      WHERE (   :p_location_code IS NULL
                             OR internal_location_code LIKE :p_location_code
                            )
                        AND (   :p_description IS NULL
                             OR description LIKE :p_description
                            )
                        AND  agy_loc_id = :p_agy_loc_id
                        AND ((:p_show_living_units = 'N' and unit_type is null)
                            OR (:p_show_living_units = 'Y'))) a,
                    (SELECT     internal_location_code,
                                description,
                                LEVEL location_level,
                                internal_location_id,
                                parent_internal_location_id
                           FROM agency_internal_locations
                          WHERE agy_loc_id = :p_agy_loc_id
                        AND ((:p_show_living_units = 'N' and unit_type is null)
                            OR (:p_show_living_units = 'Y'))
                     START WITH parent_internal_location_id IS NULL
                     CONNECT BY PRIOR internal_location_id =
                                                   parent_internal_location_id
                            AND (:p_level IS NULL OR LEVEL = :p_level)) b
              WHERE a.internal_location_id = b.internal_location_id
           ORDER BY 3,1
           
  }
  
  QUERY_INTERNAL_LOCATIONS_QUERY_TWO {
  	SELECT     internal_location_code,
                        description,
                        LEVEL location_level,
                        internal_location_id,
                        parent_internal_location_id,
                        unit_type
                   FROM agency_internal_locations
                  WHERE agy_loc_id = :p_agy_loc_id
                        AND ((:p_show_living_units = 'N' and unit_type is null)
                            OR (:p_show_living_units = 'Y'))
             START WITH parent_internal_location_id IS NULL
             CONNECT BY PRIOR internal_location_id =
                                                   parent_internal_location_id
               ORDER BY 3, 1
  }
  
  QUERY_INTERNAL_LOCATIONS_QUERY_THREE {
  	SELECT internal_location_code,
                 description,
                 NULL,
                 internal_location_id,
                 parent_internal_location_id,
                 unit_type
            FROM agency_internal_locations
           WHERE parent_internal_location_id = :p_internal_location_id
             AND (   :p_location_code IS NULL
                  OR internal_location_code LIKE :p_location_code
                 )
             AND (:p_description IS NULL OR description LIKE :p_description)
             AND agy_loc_id = :p_agy_loc_id
             AND ((:p_show_living_units = 'N' and unit_type is null)
                    OR (:p_show_living_units = 'Y'))
        ORDER BY 1

  
  }
  DESC_CUR{
  SELECT DESCRIPTION || '-' || :P_INTERNAL_LOCATION_CODE
        FROM AGENCY_INTERNAL_LOCATIONS
       WHERE INTERNAL_LOCATION_ID = :P_PARENT_INTERNAL_LOCATION_ID
}
INTERNAL_LOCATION_ID{
select NEXTVAL('internal_location_id')
}
GET_INTERNAL_LOCATION_RECORD_LOC_CUR {
	SELECT internal_location_code,
             description,
             user_desc,
             internal_location_type
        FROM agency_internal_locations
       WHERE internal_location_id = :p_internal_location_id

}

AGENCY_INTERNAL_LOCATIONS{
update agency_internal_locations set active_flag = :p_active_flag, deactivate_date = date_trunc('day', localtimestamp ), modify_user_id = :modified_user_id, modify_datetime = current_timestamp where INTERNAL_LOCATION_ID in (with recursive cte as ( select INTERNAL_LOCATION_ID from agency_internal_locations where INTERNAL_LOCATION_ID = :p_parent_lu_id union all select alu.INTERNAL_LOCATION_ID from agency_internal_locations alu join cte c on (c.INTERNAL_LOCATION_ID = alu.PARENT_INTERNAL_LOCATION_ID ) ) select * from cte where INTERNAL_LOCATION_ID <> :p_parent_lu_id and active_flag = 'Y')
}

AGENCY_INTERNAL_LOCATIONS_UPDATE{ 
update agency_internal_locations set active_flag = :p_active_flag, deactivate_date = null, modify_user_id = :modified_user_id, modify_datetime = current_timestamp where INTERNAL_LOCATION_ID in (with recursive cte as ( select INTERNAL_LOCATION_ID from agency_internal_locations where INTERNAL_LOCATION_ID = :p_parent_lu_id union all select alu.INTERNAL_LOCATION_ID from agency_internal_locations alu join cte c on (c.INTERNAL_LOCATION_ID = alu.PARENT_INTERNAL_LOCATION_ID ) ) select * from cte where INTERNAL_LOCATION_ID <> :p_parent_lu_id and active_flag <> :p_active_flag)
}

UPDATE_RELATED_TRACKING_FLAGS_UPDATE{  
update agency_internal_locations set tracking_flag = :p_tracking_flag, modify_user_id = :modified_user_id, modify_datetime = current_timestamp where internal_location_id in (with recursive cte as ( select internal_location_id, parent_internal_location_id from agency_internal_locations where internal_location_id = :p_internal_location_id union all select AIL.internal_location_id, AIL.parent_internal_location_id from agency_internal_locations AIL join cte on cte.parent_internal_location_id = AIL.internal_location_id) select internal_location_id from cte) and internal_location_id != :p_internal_location_id
}


GET_OLD_RECORD_AGENCY_INT_LOC{
SELECT INTERNAL_LOCATION_ID , INTERNAL_LOCATION_CODE , AGY_LOC_ID, INTERNAL_LOCATION_TYPE, DESCRIPTION, SECURITY_LEVEL_CODE, CAPACITY, CREATE_USER_ID, PARENT_INTERNAL_LOCATION_ID, ACTIVE_FLAG, LIST_SEQ, CREATE_DATETIME, MODIFY_DATETIME, MODIFY_USER_ID, CNA_NO, CERTIFIED_FLAG, DEACTIVATE_DATE, REACTIVATE_DATE, DEACTIVATE_REASON_CODE, COMMENT_TEXT, USER_DESC, ACA_CAP_RATING, UNIT_TYPE, OPERATION_CAPACITY, NO_OF_OCCUPANT, TRACKING_FLAG, SEAL_FLAG FROM AGENCY_INTERNAL_LOCATIONS WHERE INTERNAL_LOCATION_ID = :INTERNAL_LOCATION_ID
}
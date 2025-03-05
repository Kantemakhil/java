
OIMSGLEN_AGENCYSEGMENTLENGTHS_FIND_AGENCY_SEGMENT_LENGTHS {  
 select asl.*,tag_utils_get_agency_descp(asl.from_agy_loc_id) as from_description,
    tag_utils_get_agency_descp(asl.to_agy_loc_id) as to_description from AGENCY_SEGMENT_LENGTHS asl order by from_agy_loc_id desc,to_agy_loc_id desc
}
OIMSGLEN_AGENCYSEGMENTLENGTHS_UPDATE_AGENCY_SEGMENT_LENGTHS {
	update AGENCY_SEGMENT_LENGTHS set segment_length = :segmentLength, modify_user_id =:modifyUserId, modify_datetime=current_timestamp where from_agy_loc_id = :fromAgyLocId and to_agy_loc_id = :toAgyLocId

}


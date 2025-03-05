AGENCY_LOCATION_COUNTS_T1_UPDATE{
update
	TEMP_OIDCOUNT
set
	reported_count = :reportedCount,
	discrepancy_count = (:reportedCount - :actualCount),
	date_submitted = :dateSubmitted,
	entered_by_userid = :enteredByUserid,
	modify_user_id = :modifyUserId,
	modify_datetime = current_timestamp
where
	agy_seq = :agySeq
	and count_type_id = :countTypeId
	and reporting_loc_id = :reportingLocId
	and coalesce(reported_count, -1) != :reportedCount
}

GET_REPORTING_LOCATION_RECOUNT_TOTAL{
select recount_total from
	agency_location_counts
where
	agy_seq = :agySeq
	and count_type_id = :countTypeId
	and reporting_loc_id = :reportingLocId
}
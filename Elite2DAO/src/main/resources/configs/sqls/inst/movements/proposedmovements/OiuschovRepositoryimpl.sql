OIUSCHOV_VOFFSCH_OVERVIEW_DATA{
select
	move_seq,
	int_ext,
	status_code,
	txn_status,
	tstatus,
	event_date,
	event_time,
	movement_type,
	movement_reason,
	upper(type_descp) type_descp, upper(rsn_descp) rsn_descp 
from
	v_off_sch_overview
where
	offender_book_id = :offender_book_id

}

OIUSCHOV_MOVETYPE_CUR{
select upper(description) 
			from reference_codes 
				where domain IN ('MOVE_TYPE','INT_SCH_RSN') 
					and code = :movement_type
					and active_flag = 'Y'
}

OIUSCHOV_MOVEREASON_CUR{
select upper(description) 
			from movement_reasons 
				where movement_reason_code = :movement_reason
					and active_flag = 'Y'
}


OIUSCHOV_MOVEREASONINT_CUR{

select upper(description) 
			from reference_codes 
				where domain IN ('INT_SCH_RSN') 
					and code = :movement_reason
					and active_flag = 'Y'
}




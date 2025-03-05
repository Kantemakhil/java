OIUSCHCO_VOFFSCH_OVERVIEW_DATA{
select tstatus, int_ext, event_date , event_time , upper(type_descp) type_descp, upper(rsn_descp) rsn_descp 
from v_off_sch_overview where offender_book_id  = :offender_book_id 
}

OIUSCHCO_MOVE_TYPE_CUR{
	select upper(description) 
			from reference_codes 
				where domain = 'MOVE_TYPE' 
					and code = :movement_type
}

OIUSCHCO_MOVE_REASON_CUR{
	select upper(description) 
			from movement_reasons 
				where movement_reason_code = :movement_reason
					and active_flag = 'Y'
}
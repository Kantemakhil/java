OIDALLOW_GET_ALLOWENCE_TYPE_LOW_DATA {
SELECT DISTINCT ON (allowance_type)
    allowance_type as CODE ,  active_flag, version_no,
    
    (select description from reference_codes rc where "domain" ='ALLOWANCE' and code=allowance_type )as DESCRIPTION
FROM
    allowance_types
ORDER BY
    allowance_type, version_no DESC
 }
 OIDALLOW_GET_ALLOWENCE_TYPE_UNIT_RATE_DATA {
select
	T.UNIT,
	T.RATE
from
	(
	select
		ALLOWANCE_TYPE,
		MAX(VERSION_NO) VERSION_NO
	from
		ALLOWANCE_TYPES
	group by
		ALLOWANCE_TYPE
) A
inner join ALLOWANCE_TYPES T on
	A.ALLOWANCE_TYPE = T.ALLOWANCE_TYPE
	and A.VERSION_NO = T.VERSION_NO
where
	T.ALLOWANCE_TYPE =:allowanceType }
 
 
 OIDALLOW_GET_OFFENDER_ALLOWANCE_GRID_DATA {
 select oa.off_allowance_id, oa.offender_book_id, oa.allowance_type, oa.start_date, oa.end_date, oa.comment_text, oa.create_datetime, oa.create_user_id, oa.seal_flag,
 (select T.RATE from ( select ALLOWANCE_TYPE, MAX(VERSION_NO) VERSION_NO from ALLOWANCE_TYPES group by ALLOWANCE_TYPE ) A inner join ALLOWANCE_TYPES T on A.ALLOWANCE_TYPE = T.ALLOWANCE_TYPE and A.VERSION_NO = T.VERSION_NO where T.ALLOWANCE_TYPE =oa.allowance_type) as rate,
 (select T.UNIT from ( select ALLOWANCE_TYPE, MAX(VERSION_NO) VERSION_NO from ALLOWANCE_TYPES group by ALLOWANCE_TYPE ) A inner join ALLOWANCE_TYPES T on A.ALLOWANCE_TYPE = T.ALLOWANCE_TYPE and A.VERSION_NO = T.VERSION_NO where T.ALLOWANCE_TYPE =oa.allowance_type) as unit,
 ( select count(*)  from off_alowances_pay_details oapd where off_allowance_id = oa.off_allowance_id and pay_flag = 'Y') as paid_flag
 from offender_allowances oa where offender_book_id =:offenderBookId order by create_datetime asc
 }
 OIDALLOW_INSERT_OFFENDER_ALLOWANCE_DATA{
 insert into offender_allowances(off_allowance_id, offender_book_id, allowance_type, start_date, end_date, comment_text,create_datetime, create_user_id, seal_flag) values (nextval('off_allowance_id_seq'), :offenderBookId,:allowanceType,:startDate,:endDate,:commentText,current_timestamp,:createUserId,:sealFlag)
 }
 
 OIDALLOW_UPDATE_OFFENDER_ALLOWANCE_DATA {
 update offender_allowances set comment_text=:commentText,end_date =:endDate,modify_user_id =:modifyUserId,modify_datetime =current_timestamp where off_allowance_id=:offAllowanceId
 }
 
 OIDALLOW_DELETE_OFFENDER_ALLOWANCE_DATA {
 delete from offender_allowances where off_allowance_id=:offAllowanceId
 }
 
 OIDALLOW_GET_LAST_PAID_DATE {
 select max(off_allowance_day) from off_alowances_pay_details where offender_book_id =:offenderBookId and off_allowance_id = :offAllowanceId and pay_flag = 'Y'
 }
 
 OIDALLOW_INSERT_OFF_ALLOWANCES_PAY_RECORDS{
insert into off_alowances_pay_details (detail_id, offender_book_id,off_allowance_id, off_allowance_day, allowance_type, version_no, allowance_version_unit, allowance_version_max_unit, allowance_version_rate,pay_actual_rate, pay_actual_amount,batch_id,pay_flag, create_datetime,create_user_id, seal_flag) values ((select  (coalesce((select  max(detail_id) from off_alowances_pay_details ),0)+1)), :offenderBookId, :offAllowanceId, :offAllowanceDay, :allowanceType, :versionNo, :allowanceVersionUnit, :allowanceVersionMaxUnit, :allowanceVersionRate, :payActualRate, :payActualAmount, :batchId, 'N' ,current_timestamp, :createUserId, :sealFlag)
}

OIDALLOW_GET_OFF_ALLOWANCES_RECORDS{
select * from offender_allowances where  offender_book_id = :offenderBookId
}

OIDALLOW_RETRIVE_ALLOWANCES_RECORDS{
select
	at2.*
from 
	(
	select
		max(version_start_date) as version_start_date,
		allowance_type
	from
		allowance_types
	group by
		allowance_type,
		version_start_date::date) at1
join allowance_types at2 
	on
	(at1.version_start_date = at2.version_start_date
		and at1.allowance_type = at2.allowance_type)  order by at2.allowance_type , at2.version_start_date asc
}

OIDALLOW_GET_OFF_ALLOWANCES_PAY_COUNT{
select count(*) from off_alowances_pay_details oapd where offender_book_id = :offenderBookId and off_allowance_id = :offAllowanceId and off_allowance_day = :OffAllowStartDt 
}

OIDALLOW_DELETE_OFF_ALOWANCES_PAY_DETAILS {
delete from off_alowances_pay_details where off_allowance_id = :offAllowanceId and offender_book_id = :offenderBookId
}

OIDALLOW_DELETE_REMAINING_OFF_ALOWANCES_PAY_DETAILS {
delete from off_alowances_pay_details where off_allowance_id = :offAllowanceId and off_allowance_day > :endDate and off_allowance_day <= current_timestamp and pay_flag = 'N'
}
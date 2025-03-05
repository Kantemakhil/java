OIMALLOW_INSERT_ALLOWANCES_RECORDS{
insert into allowance_types (allowance_type, unit,max_unit, rate, sunday_flag, monday_flag, tuesday_flag, wednesday_flag, thursday_flag,friday_flag, saturday_flag,active_flag,expiry_date,version_no, version_start_date, create_datetime,create_user_id, seal_flag) values (:allowanceType, :unit, :maxUnit, :rate, :sundayFlag, :mondayFlag, :tuesdayFlag, :wednesdayFlag, :thursdayFlag, :fridayFlag, :saturdayFlag, :activeFlag, :expiryDate, (select  (coalesce((select  max(version_no) from allowance_types where allowance_type =:allowanceType ),0)+1)),current_timestamp, current_timestamp, :createUserId,:sealFlag)
}

OIMALLOW_UPDATE_ALLOWANCES_RECORDS{
update allowance_types set active_flag = 'N', expiry_date = current_timestamp , modify_datetime = current_timestamp , modify_user_id = :modifyUserId, seal_flag = :sealFlag where allowance_type=:allowanceType and version_no = :versionNo
}

OIMALLOW_GET_ALLOWANCES_RECORDS{
SELECT DISTINCT ON (allowance_type)
    allowance_type, unit, max_unit, rate, sunday_flag, monday_flag, tuesday_flag, wednesday_flag, thursday_flag,friday_flag, saturday_flag, active_flag, expiry_date, version_no
FROM
    allowance_types
ORDER BY
    allowance_type, version_no DESC
}

OIMALLOW_GET_HRS_UNIT{
select code, description from reference_codes rd where domain = 'UNIT' and code = 'HRS'
}

OIMALLOW_GET_OFF_ALLOWANCES_RECORDS{
select * from offender_allowances where  current_date between start_date  and coalesce(end_date, current_date) ;
}

OIMALLOW_RETRIVE_ALLOWANCES_RECORDS{
select
	at1.*
from
	(
	select
		distinct on
		(at2.version_start_date::date,
		allowance_type) *,
		at2.version_start_date::date as versionDate
	from
		allowance_types at2 ) a
join allowance_types at1 on
	(at1.allowance_type = a.allowance_type
		and at1.version_start_date::date = a.versionDate::date)
where
	at1.allowance_type = a.allowance_type
	and at1.version_start_date =
(
	select
		max(at.version_start_date)
	from
		allowance_types at
	where
		at.allowance_type = a.allowance_type)
}

OIMALLOW_INSERT_OFF_ALLOWANCES_PAY_RECORDS{
insert into off_alowances_pay_details (detail_id, offender_book_id,off_allowance_id, off_allowance_day, allowance_type, version_no, allowance_version_unit, allowance_version_max_unit, allowance_version_rate,pay_actual_rate, pay_actual_amount,batch_id,pay_flag, create_datetime,create_user_id, seal_flag) values ((select  (coalesce((select  max(detail_id) from off_alowances_pay_details ),0)+1)), :offenderBookId, :offAllowanceId, current_timestamp, :allowanceType, :versionNo, :allowanceVersionUnit, :allowanceVersionMaxUnit, :allowanceVersionRate, :payActualRate, :payActualAmount, :batchId, 'N' ,current_timestamp, :createUserId, :sealFlag)
}

OIMALLOW_GET_OFF_ALLOWANCES_PAY_COUNT{
select count(*) from off_alowances_pay_details oapd where offender_book_id = :offenderBookId and off_allowance_id = :offAllowanceId and off_allowance_day = current_date::date 
}
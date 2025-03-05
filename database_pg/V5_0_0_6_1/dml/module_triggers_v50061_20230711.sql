insert
	into
	MODULE_TRIGGERS(MODULE_NAME,
	MODULE_DESCRIPTION,
	TRIGGER_ID,
	TRIGGER_NAME,
	DTO_NAME,
	CREATE_DATETIME,
	CREATE_USER_ID,
	MODIFY_DATETIME,
	MODIFY_USER_ID,
	SEAL_FLAG)
select
	'OCDIPLAN',
	'Case Plan',
	'116',
	'Verification',
	'net.syscon.s4.inst.casemanagement.beans.WorkFlowLogs',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null
where
	not exists (
	select
		1
	from
		module_triggers
	where
		module_name = 'OCDIPLAN'
		and dto_name = 'net.syscon.s4.inst.casemanagement.beans.WorkFlowLogs'
		and module_description = 'Case Plan'
		and trigger_name = 'Verification' );

insert
	into
	MODULE_TRIGGERS
(MODULE_NAME,
	MODULE_DESCRIPTION,
	TRIGGER_ID,
	TRIGGER_NAME,
	DTO_NAME,
	CREATE_DATETIME,
	CREATE_USER_ID,
	MODIFY_DATETIME,
	MODIFY_USER_ID,
	SEAL_FLAG)
 select
	'OCDNOQUE',
	'Offender Assessment Questionnaire',
	'118',
	'Assessments',
	'net.syscon.s4.common.beans.OffenderAssessments',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null
where
	not exists (
	select
		1
	from
		module_triggers
	where
		module_name = 'OCDNOQUE'
		and dto_name = 'net.syscon.s4.common.beans.OffenderAssessments'
		and module_description = 'Offender Assessment Questionnaire'
		and trigger_name = 'Assessments'
		and TRIGGER_ID = '118');

insert
	into
	oms_owner.module_triggers
(module_name,
	dto_name,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	trigger_name,
	module_description,
	trigger_id,
	seal_flag)
select
	'OIDOFFOB',
	'net.syscon.s4.inst.offenderobservations.beans.OffenderObservationPeriods',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	'Periods Block',
	'Offender Observations',
	'119',
	null
where
	not exists (
	select
		1
	from
		module_triggers
	where
		module_name = 'OIDOFFOB'
		and module_description = 'Offender Observations'
		and trigger_name = 'Periods Block');
		
insert
	into
	oms_owner.module_triggers
(module_name,
	dto_name,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	trigger_name,
	module_description,
	trigger_id,
	seal_flag)
select
	'OCDCGPAY',
	'net.syscon.s4.im.beans.VOffenderCourseAttendances',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	'Confirm/Generate',
	'Confirm/Generate Pay',
	'122',
	null
where
	not exists (
	select
		1
	from
		module_triggers
	where
		module_name = 'OCDCGPAY'
		and module_description = 'Confirm/Generate Pay'
		and trigger_name = 'Confirm/Generate');

insert
	into
	oms_owner.module_triggers
(module_name,
	dto_name,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	trigger_name,
	module_description,
	trigger_id,
	seal_flag)
select
	'OIDRPLAN',
	'net.syscon.s4.im.beans.ReleasePlans',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null,
	'Release Plan',
	'123',
	null
where
	not exists (
	select
		1
	from
		module_triggers
	where
		module_name = 'OIDRPLAN'
		and module_description = 'Release Plan');
		
insert
	into
	MODULE_TRIGGERS(MODULE_NAME,
	MODULE_DESCRIPTION,
	TRIGGER_ID,
	TRIGGER_NAME,
	DTO_NAME,
	CREATE_DATETIME,
	CREATE_USER_ID,
	MODIFY_DATETIME,
	MODIFY_USER_ID,
	SEAL_FLAG)
select
	'OIDINCDE',
	'Incident Reporting',
	'121',
	'Reportable Incident Type',
	'net.syscon.s4.im.incidentsoic.beans.ReportableIncedentDetails',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null
where
	not exists (
	select
		1
	from
		module_triggers
	where
		module_name = 'OIDINCDE'
		and dto_name = 'net.syscon.s4.im.incidentsoic.beans.ReportableIncedentDetails'
		and module_description = 'Incident Reporting'
		and trigger_name = 'Reportable Incident Type' );		
		
		
insert
	into
	MODULE_TRIGGERS(MODULE_NAME,
	MODULE_DESCRIPTION,
	TRIGGER_ID,
	TRIGGER_NAME,
	DTO_NAME,
	CREATE_DATETIME,
	CREATE_USER_ID,
	MODIFY_DATETIME,
	MODIFY_USER_ID,
	SEAL_FLAG) 
select
	'OUMMEROF',
	'Merge Offenders',
	'120',
	'Merge To Offenders',
	'net.syscon.s4.sa.recordmaintenance.MergeTransactionBean',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null
where
	not exists (
	select
		1
	from
		module_triggers
	where
		module_name = 'OUMMEROF'
		and dto_name = 'net.syscon.s4.sa.recordmaintenance.MergeTransactionBean'
		and
 module_description = 'Merge Offenders'
		and trigger_name = 'Merge To Offenders'
		and TRIGGER_ID = '120');		
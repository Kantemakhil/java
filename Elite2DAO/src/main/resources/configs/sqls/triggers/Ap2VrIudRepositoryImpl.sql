AP_VR_IUD_OFF_AP_V2_OLD{
--SELECT OFF_ACTION_PLAN_ID,CASEWORK_TYPE,CASEWORK_TYPE_DESC,OFF_CRIM_NEED_ID,OFF_CASE_COND_ID,PROGRAM_ID,PROGRAM_DESC,NOTES,START_DATE,END_DATE,PROGRAM_CATEGORY,PRG_CATEGORY_DESC FROM OFF_AP_V2 WHERE OFF_ACTION_PLAN_ID =:offActionPlanId
select
	oap.off_action_plan_id,
	oap.casework_type,
	(
	select
		rc.description
	from
		reference_codes rc
	where
		rc.domain::text = 'CASEPLAN_STP'::text
		and rc.code::text = oap.casework_type::text
	limit 1) as casework_type_desc,
	oap.off_crim_need_id,
	oap.off_case_cond_id,
	oap.program_id,
	ps.description as program_desc,
	oap.notes,
	oap.start_date,
	oap.end_date,
	ps.program_category,
	(
	select
		reference_codes.description
	from
		reference_codes
	where
		reference_codes.domain::text = 'PS_CATEGORY'::text
		and reference_codes.code::text = ps.program_category::text) as prg_category_desc
from
	offender_action_plans oap
left join program_services ps on
	oap.program_id = ps.program_id where oap.off_action_plan_id = :offActionPlanId
}

OCMPSSET_IEP_RECORDGROP_DATA {
 SELECT IEP_LEVEL_CODE CODE,IEP_LEVEL_DESCRIPTION  DESCRIPTION,
 case when active_flag = 'Y' then true else false end canDisplay FROM INCENTIVES_EARN_PRIVS IEP 
}

OCMPSSET_PROG_SERV_SETTING_DATA {
 SELECT PAY_FLAG,PAY_SYSTEM_CODE,PAY_CYCLE_START_DAY,INST_ACT_DEFAULT_ATT_CODE,COALESCE(INST_ACT_MAX_SCHEDULED_HOURS,0) INST_ACT_MAX_SCHEDULED_HOURS,ACP_ATT_CODE ACPATTCODEVAL,INST_ACT_ATT_CODE INSTACTATTCODEVAL,IEP_LEVEL_CODE,CREATE_DATETIME,CREATE_USER_ID,INST_ACT_DEL_FLAG FROM PROGRAMS_PAY_SETTINGS 
}

OCMPSSET_ACP_ATTENDENCE_OUTCOME_CODES {
SELECT RC.DESCRIPTION, EMO.OUTCOME_CODE CODE FROM EVENT_MEASURE_OUTCOMES EMO, EVENT_MEASURES EM, REFERENCE_CODES RC
WHERE EMO.EVENT_MEASURE_ID = EM.EVENT_MEASURE_ID AND EM.EVENT_TYPE = 'ACP' AND EM.EVENT_SUB_TYPE = 'PROG_SESS'
AND RC.CODE = EMO.OUTCOME_CODE AND RC.DOMAIN = 'OUTCOMES' ORDER BY EMO.LIST_SEQ, RC.DESCRIPTION, EMO.OUTCOME_CODE
}

OCMPSSET_PROG_SERV_SETTING_INSERT_QUERY {
 insert into programs_pay_settings(pay_flag, pay_system_code, pay_cycle_start_day, inst_act_default_att_code, inst_act_max_scheduled_hours, acp_att_code, inst_act_att_code, iep_level_code, create_datetime, create_user_id,inst_act_del_flag) values(:payFlag, :paySystemCode, :payCycleStartDay, :instActDefaultAttCode, :instActMaxScheduledHours, :acpAttCodeVal, :instActAttCodeVal, :iepLevelCode, current_timestamp, :createUserId ,:instActDelFlag)
 }

OCMPSSET_PROG_SERV_SETTING_UPDATE_QUERY {
update programs_pay_settings set pay_flag =:payFlag, pay_system_code =:paySystemCode, pay_cycle_start_day =:payCycleStartDay, inst_act_default_att_code =:instActDefaultAttCode, inst_act_max_scheduled_hours =:instActMaxScheduledHours, acp_att_code =:acpAttCodeVal, inst_act_att_code =:instActAttCodeVal, iep_level_code =:iepLevelCode, modify_datetime =current_timestamp , modify_user_id =:modifyUserId ,inst_act_del_flag=:instActDelFlag
}

 OCMPSSET_GET_PRGSRV_SETTING_HOURS {
  select coalesce(inst_act_max_scheduled_hours,0) inst_act_max_scheduled_hours from programs_pay_settings
  }
  
  OCMPSSET_GET_PRGSRV_DATA {
  SELECT PAY_FLAG, coalesce(inst_act_max_scheduled_hours,0) inst_act_max_scheduled_hours FROM PROGRAMS_PAY_SETTINGS
  }
  
  OCMPSSET_GET_PAY_SCHEDULE_DATA {
  select case when actrate is not null then actrate else orgrate end pay_system_rate,
(select inst_act_max_scheduled_hours from programs_pay_settings) pay_system_unit,pay_hours,ed event_id
,case when actrate is not null then actrate*pay_hours
 when orgrate is not null then orgrate*pay_hours
else 0 end pay_actual_amount ,start_time,end_time
from(
select voce.event_id ed,voce.start_time ,voce.end_time ,
(select rate from programs_pay_compensation where PROGRAM_ID = voce.program_id and crs_acty_id = voce.crs_acty_id) as actrate,
(select rate from programs_pay_compensation where PROGRAM_ID = voce.program_id and coalesce(crs_acty_id,0) = 0) as orgrate,
round((ABS(extract(EPOCH from (voce.start_time-voce.end_time)/ 3600)))::decimal,2) pay_hours ,voce.event_id
from v_offender_course_events voce where offender_book_id =:offenderBookId and event_id =:event_id  
and exists (select 'Y' from programs_pay where program_category = voce.event_type and active_flag = 'Y')
and exists ( select 'Y' from programs_pay_settings where encode(inst_act_att_code, 'escape') like  '%'|| voce.event_outcome || '%')
and exists (select 'Y' from programs_pay_compensation where program_category = voce.event_type and program_id = voce.program_id)
)a
}

OCMPSSET_UPDATE_SYSTEM_PAY_RATES {
 update OFFENDER_COURSE_ATTENDANCES set PAY_SYSTEM_UNIT =:paySystemUnit, PAY_SYSTEM_RATE =:paySystemRate, pay_hours =:payHours, pay_actual_amount = :payActualAmount, pay_actual_rate = :paySystemRate, pay_actual_unit =:paySystemUnit, modify_datetime = current_timestamp , modify_user_id =:modifyUserId where EVENT_ID =:eventId
}

 OCMPSSET_GET_PAY_SCHEDULE_DATA_ACP {
 select case when actrate is not null then actrate else orgrate end pay_system_rate,
(select inst_act_max_scheduled_hours from programs_pay_settings) pay_system_unit,pay_hours,ed event_id
,case when actrate is not null then actrate*pay_hours
 when orgrate is not null then orgrate*pay_hours
else 0 end pay_actual_amount ,start_time,end_time
from(
select voce.event_id ed,voce.program_id,voce.crs_acty_id,voce.off_prgref_id,voce.in_time start_time ,voce.out_time end_time ,
(select rate from programs_pay_compensation where PROGRAM_ID = (select program_id from v_offender_prg_obligations vopo where offender_prg_obligation_id in (select offender_prg_obligation_id from offender_program_profiles where off_prgref_id =voce.off_prgref_id)
) and crs_acty_id in (SELECT crs_acty_id FROM COURSE_ACTIVITIES WHERE crs_acty_id in (SELECT parent_crs_acty_id FROM COURSE_ACTIVITIES WHERE crs_acty_id = voce.crs_acty_id))) as actrate,
(select rate from programs_pay_compensation where PROGRAM_ID = (select program_id from v_offender_prg_obligations vopo where offender_prg_obligation_id in (select offender_prg_obligation_id from offender_program_profiles where off_prgref_id =voce.off_prgref_id)
) and coalesce(crs_acty_id,0) = 0) as orgrate,
round((ABS(extract(EPOCH from (voce.in_time-voce.out_time)/ 3600)))::decimal,2) pay_hours,voce.event_id
from v_offender_course_events voce where offender_book_id =:offenderBookId and event_id =:event_id and voce.program_id is not null and voce.crs_acty_id is not null
and exists (select 'Y' from programs_pay where program_category = voce.event_type and active_flag = 'Y')
and exists ( select 'Y' from programs_pay_settings where encode(acp_att_code, 'escape') like  '%'|| voce.event_outcome || '%')
and exists (select 'Y' from programs_pay_compensation where program_category = voce.event_type and program_id = (select program_id from v_offender_prg_obligations vopo 
where offender_prg_obligation_id in (select offender_prg_obligation_id from offender_program_profiles where off_prgref_id =voce.off_prgref_id)))
)a
}

OCMPSSET_ELITE_FINANCIALS_DATA {

SELECT * ,
case when active_flag = 'Y' then true else false end canDisplay FROM REFERENCE_CODES WHERE DOMAIN = 'PS_PAY' AND (PARENT_CODE  IS null or PARENT_CODE ='')

}
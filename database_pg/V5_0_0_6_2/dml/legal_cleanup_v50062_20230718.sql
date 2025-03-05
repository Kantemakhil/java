--cleanig community services programs data as they depend on legals 
delete from oms_owner.offender_course_skills 
 where event_id in (select event_id 
                      from oms_owner.offender_course_attendances 
                     where off_prgref_id in (select off_prgref_id from oms_owner.offender_program_profiles where program_id in (select program_id from oms_owner.program_services where program_category = 'UW')));
 
delete from oms_owner.offender_course_attendances 
 where off_prgref_id in (select off_prgref_id from oms_owner.offender_program_profiles where program_id in (select program_id from oms_owner.program_services where program_category = 'UW'));
 
delete from oms_owner.offender_course_appt_rules 
 where offender_course_appt_grp_id in (select offender_course_appt_grp_id 
                                         from  oms_owner.offender_course_appt_grps 
										where off_prgref_id in (select off_prgref_id from oms_owner.offender_program_profiles where program_id in (select program_id from oms_owner.program_services where program_category = 'UW')));
 
delete from oms_owner.offender_course_appt_grps 
 where off_prgref_id in (select off_prgref_id from oms_owner.offender_program_profiles where program_id in (select program_id from oms_owner.program_services where program_category = 'UW'));
 
delete from oms_owner.offender_program_profiles where program_id in (select program_id from oms_owner.program_services where program_category = 'UW');
 
--cleaning legal tables data
delete from offender_interested_parties; 
delete from ocdlegls_data_hty; 
delete from ocdlegls_data; 
delete from ocuchgou_data; 
delete from ocdchgsu_data;  
delete from ocdleglo_sanction_hty; 
--children of offender_case_conditions
delete from offender_action_plans;
--children of offender_sent_conditions
delete from offender_unpaid_work_adj;
delete from offender_cond_transfer; 
delete from offender_sent_cond_statuses;

delete from offender_obligation_adjs;

update offender_prg_obligations 
  set offender_sent_cond_act_id = NULL 
 where offender_sent_cond_act_id IS NOT NULL; 

delete from offender_sent_cond_acts;

delete from  offender_case_conditions; 

delete from offender_ind_sch_sents;

UPDATE offender_program_profiles 
   SET offender_sent_condition_id = NULL
      ,sentence_seq = NULL  
 WHERE offender_sent_condition_id IS NOT NULL or sentence_seq IS NOT NULL; 

delete from offender_sent_conditions; 
--
delete from offender_legal_adjustments ; 
delete from offender_custody_status; 
delete from ocdleglo_data;
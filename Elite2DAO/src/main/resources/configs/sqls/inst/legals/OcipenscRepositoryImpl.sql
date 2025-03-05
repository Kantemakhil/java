DYNAMIC_PENDING_SENTENCE_CALC_DATA {
select events.id, vo.offender_id_display, vo.last_name, vo.first_name, events.module_name, events.calc_reason as calc_reason, events.offender_book_id from (select id, form_identifier::jsonb->> 'offenderBookId' as offender_book_id,calc_reason,module_name from ocdlegls_data_pending_calculation order by id desc) as events join v_offenders as vo on to_char(vo.offender_book_id) = events.offender_book_id
}

OCIPENSC_GET_OFFENDER_PENDING_EVENT{
select *,calc_engine_input as form_info_json_blob from ocdlegls_data_pending_calculation WHERE LOWER(form_identifier)  like %formdentifier% order by create_datetime
}

OCIPENSC_INSERT_OCDLEGLS_DATA_PENDING_CALCULATION {
insert into ocdlegls_data_pending_calculation (id, calc_engine_input, form_identifier, module_name, calc_reason, action_type, create_datetime, create_user_id) values(nextval('ocdlegls_data_pending_calculation_id_seq'), :calcEngineInput, :formIdentifier, :moduleName, :calcReason, :actionType, current_timestamp, :createUserId)
}
OCIPENSC_DELETE_OCDLEGLS_DATA_PENDING_CALCULATION {
delete from ocdlegls_data_pending_calculation where id = :id
}
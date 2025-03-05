OIMIITPS_GRIEVENCE_PERMISSIONS_EXECUTE_QUERRY{
select gt.griev_type , gr.griev_reason_code , coalesce(( select create_amend_flag from grievance_types_permissions where role_id = :roleId and griev_type = gr.griev_type and griev_reason_code = gr.griev_reason_code), 'N') as create_flag, coalesce(( select view_flag from grievance_types_permissions where role_id = :roleId and griev_type = gr.griev_type and griev_reason_code = gr.griev_reason_code), 'N') as view_flag, case when ( select count(*) from grievance_types_permissions where role_id = :roleId and griev_type = gr.griev_type and griev_reason_code = gr.griev_reason_code) > 0 then 'Y' else 'N' end as is_saved from grievance_reasons gr, grievance_types gt where gt.griev_type = gr.griev_type ;
}

OIMIITPS_GRIEVENCE_TYPE_RECORD_GROUP{
select griev_type as code , description, active_flag from grievance_types gt 
}
OIMIITPS_GRIEVENCE_REASON_RECORD_GROUP{
select griev_reason_code as code, description ,  active_flag from grievance_reasons gr where griev_type = :grievType
}

OIMIITPS_GRIEVENCE_PERMISSIONS_INSERTING{
INSERT INTO GRIEVANCE_TYPES_PERMISSIONS(ROLE_ID,griev_type,griev_reason_code,create_amend_flag ,VIEW_FLAG,CREATE_DATETIME,CREATE_USER_ID,MODIFY_DATETIME,SEAL_FLAG) VALUES (:roleId,:grievType,:grievReasonCode,:createFlag,:viewFlag,current_timestamp , :createUserId , null , :sealFlag )
}

OIMIITPS_GRIEVENCE_PERMISSIONS_UPDATING{
 update GRIEVANCE_TYPES_PERMISSIONS set create_amend_flag=:createFlag,VIEW_FLAG=:viewFlag,modify_user_id =:modifyUserId, modify_datetime = current_timestamp, seal_flag = :sealFlag  where ROLE_ID=:roleId and griev_type=:grievType and griev_reason_code=:grievReasonCode

}

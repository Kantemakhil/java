OCMTIRLV_FIND_MAINTAIN_TIER_LEVELS{
SELECT tier_level_code,tier_level_desc description, workload_value , review_days ,default_intake_tier_flag , list_seq, active_flag, expiry_date, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag,(select count(*) from offender_tier_levels otl where otl.tier_level_code = mtl.tier_level_code) editable_btn FROM maintain_tier_levels mtl  order by mtl.list_seq asc 
}
OCMTIRLV_INSERT_MAINTAIN_COMMUNITY_TIER_LEVELS{
insert into maintain_tier_levels(tier_level_code,tier_level_desc, workload_value, review_days, default_intake_tier_flag ,list_seq,active_flag,expiry_date,create_datetime ,create_user_id ,seal_flag,modify_datetime)
values(:code,:description,:workloadValue, :reviewDays, :defaultIntakeTierFlag , :listSequence,:activeFlag,:expiryDate,current_timestamp,:createUserId,:sealFlag,null )
}
OCMTIRLV_UPDATE_MAINTAIN_COMMUNITY_TIER_LEVELS{
update maintain_tier_levels set tier_level_desc = :description , workload_value =:workloadValue, review_days =:reviewDays, default_intake_tier_flag =:defaultIntakeTierFlag , list_seq =:listSequence, active_flag =:activeFlag, expiry_date =:expiryDate, modify_datetime =current_timestamp, modify_user_id =:modifyUserId where tier_level_code =:code
}
OCMTIRLV_DELETE_MAINTAIN_COMMUNITY_TIER_LEVELS{ 
delete from maintain_tier_levels where tier_level_code =:code
}
OCMTIRLV_RECORD_GROUP_MAINTAIN_TIER_LEVELS{
select tier_level_code code,tier_level_desc description from maintain_tier_levels mlvl where mlvl.expiry_date is null
}

OIUONONA_OFFNONASSO_FIND_OFFENDER_NA_DETAILS {
select ond.*,
tag_utils_get_ref_descp('NON_ASSO_RSN',ond.NS_REASON_CODE) as NBT_REASON,
	tag_utils_get_ref_descp('NON_ASSO_TYP',ond.NS_TYPE) as NBT_TYPE,
offender_id_display,last_name || ', ' || first_name as offender_name ,root_offender_id
	from offenders o,Offender_Na_Details ond where o.root_offender_id  = ond.ns_offender_id and o.offender_id  = ond.ns_offender_id and ond.OFFENDER_ID = :OFFENDER_ID 
and (ond.ns_expiry_date is null or date_trunc('day',ond.ns_expiry_date) > date_trunc('day',current_timestamp))
}


OIUONONA_STGRELATIONSHIPS_FIND_STG_RELATIONSHIPS {
 	select
	*,
	(
	select
		coalesce(stg3.description, '') || '-' || coalesce(stg2.description, '') || '-' || coalesce(stg1.description, '')
	from
		security_threat_groups stg1,
		security_threat_groups stg2,
		security_threat_groups stg3
	where
		stg1.stg_id = STG_RELATIONSHIPS.RELATED_STG_ID
		and stg1.stg_level = 'SET'
		and stg1.active_flag = 'Y'
		and stg1.parent_stg_id = stg2.stg_id
		and stg2.parent_stg_id = stg3.stg_id
union
	select
		coalesce(stg2.description, '') || '-' || coalesce(stg1.description, '')
	from
		security_threat_groups stg1,
		security_threat_groups stg2
	where
		stg1.stg_id = STG_RELATIONSHIPS.RELATED_STG_ID
		and stg1.stg_level = 'GANG'
		and stg1.active_flag = 'Y'
		and stg1.parent_stg_id = stg2.stg_id
union
	select
		description
	from
		security_threat_groups
	where
		stg_id = STG_RELATIONSHIPS.RELATED_STG_ID
		and stg_level = 'NATION'
		and active_flag = 'Y') as NBT_RELATED_STG_ID
from
	STG_RELATIONSHIPS  where				
	relationship_type = 'ENEMY' and stg_id in 
	(SELECT STG_ID FROM OFFENDER_STG_AFFILIATIONS WHERE OFFENDER_BOOK_ID =:offender_book_id AND ACTIVE_FLAG = 'Y')
	and active_flag = 'Y'
}

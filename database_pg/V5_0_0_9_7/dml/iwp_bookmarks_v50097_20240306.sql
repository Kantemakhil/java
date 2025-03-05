update
	oms_owner.iwp_bookmarks
set
	sql_text = 'select a.obs_oopcid,
a.obs_oopcsdte,
a.obs_oopcvar,
a.obs_oopcstme,
a.obs_oopcdte,
a.obs_oopctme,
a.obs_oopcfreq,
a.obs_oopcstaf,
a.comment_text,
 CASE 
        WHEN a.activity IS NULL THEN
            (SELECT STRING_AGG(description, '', '') 
             FROM reference_codes rc 
             JOIN off_obs_add_details ooad ON rc.code = ooad.detail_code
             WHERE rc.domain = ''ACTIVITY'' 
             AND ooad.obs_type_version_id = ?
             AND ooad.detail_type = ''ACTIVITY''
            )
        ELSE a.activity 
    END AS activity,
	CASE 
        WHEN a.cell_cnditns IS NULL THEN
            (SELECT STRING_AGG(description, '', '') 
             FROM reference_codes rc 
             JOIN off_obs_add_details ooad ON rc.code = ooad.detail_code
             WHERE rc.domain = ''CELL_CNDITNS'' 
             AND ooad.obs_type_version_id = ?
             AND ooad.detail_type = ''CELL_CNDITNS''
            )
        ELSE a.cell_cnditns 
    END AS cell_cnditns,
	CASE 
        WHEN a.com_det_cat IS NULL THEN
            (SELECT STRING_AGG(description, '', '') 
             FROM reference_codes rc 
             JOIN off_obs_add_details ooad ON rc.code = ooad.detail_code
             WHERE rc.domain = ''COM_DET_CAT'' 
             AND ooad.obs_type_version_id = ?
             AND ooad.detail_type = ''COM_DET_CAT''
            )
        ELSE a.com_det_cat 
    END AS com_det_cat,
	 CASE 
        WHEN a.not_in_cell IS NULL THEN
            (SELECT STRING_AGG(description, '', '') 
             FROM reference_codes rc 
             JOIN off_obs_add_details ooad ON rc.code = ooad.detail_code
             WHERE rc.domain = ''NOT_IN_CELL'' 
             AND ooad.obs_type_version_id = ?
             AND ooad.detail_type = ''NOT_IN_CELL''
            )
        ELSE a.not_in_cell 
    END AS not_in_cell
 from (select
	obp.check_id as OBS_OOPCID,
	to_char(obp.schedule_datetime, ''DD-MM-YYYY'') as  OBS_OOPCSDTE,
	greatest((extract(epoch
from
	check_datetime)* 1000-extract(epoch
from
	obp.schedule_datetime)* 1000-(
	select
		frequency
	from
		offender_observation_types
	where
		obs_type_version_id =?))/ 60000,0) as OBS_OOPCVAR ,
	to_char(obp.schedule_datetime, ''HH24:MI'') as   OBS_OOPCSTME,
	to_char(obp.check_datetime , ''DD-MM-YYYY'') as  OBS_OOPCDTE,
	to_char(obp.check_datetime, ''HH24:MI'') as   OBS_OOPCTME,
	(
	select
		frequency 	
	from
		offender_observation_types
	where
		obs_type_version_id =?) as OBS_OOPCFREQ,
	(
	select
		last_name || '','' || '' '' || first_name as user_id
	from
		staff_members sm
	where
		staff_id = obp.performing_staff_id ) as OBS_OOPCSTAF,
		obp.comment_text ,
	(select string_agg(description, '','')  from reference_codes rc where code in (select detail_code  from off_obs_add_check_details ooacd where ooacd.obs_type_version_id =?  and  ooacd.detail_type = ''ACTIVITY''))as ACTIVITY,
	(select string_agg(description, '','')  from reference_codes rc where code in (select detail_code  from off_obs_add_check_details ooacd where ooacd.obs_type_version_id =? and   ooacd.detail_type = ''CELL_CNDITNS'')) as CELL_CNDITNS,
  	(select string_agg(description, '','')  from reference_codes rc where code in (select detail_code  from off_obs_add_check_details ooacd where  ooacd.obs_type_version_id =? and  ooacd.detail_type = ''COM_DET_CAT''))as COM_DET_CAT,
  	(select string_agg(description, '','')  from reference_codes rc where code in (select detail_code  from off_obs_add_check_details ooacd where   ooacd.obs_type_version_id =? and ooacd.detail_type = ''NOT_IN_CELL'')) as NOT_IN_CELL
from
	OFF_OBS_PERIOD_CHECKS obp
	LEFT join off_obs_add_check_details ooacd
	on  obp.check_id = ooacd.check_id  
where
	OBS_PERIOD_ID =?
GROUP BY
	obp.check_id) as a',
	modify_datetime = current_timestamp ,
	modify_user_id = 'OMS_OWNER'
where
	bookmark_name = 'OFF_OBS_CHEC';

update
	oms_owner.iwp_bookmarks
set
	sql_text = 'select A.*,B.* from (
select
	order_proposal_code,
	order_id,
	(
	select
		sct.description
	from
		SENTENCE_CALC_TYPES sct
	where
		sct.sentence_calc_type = order_proposal_code
		and
	active_flag = ''Y''
		and sentence_category = ''NCUS'') as LEG_CRRPDSP,
	
	NOT_SUITABLE_FLAG as LEG_CRRPNS,
		(
	select
		description
	from
		reference_codes rc
	where
		"domain" = ''CRT_REP_UNS''
		and code = NOT_SUITABLE_REASON) as LEG_CRRPNSR,

	comment_text as LEG_CRRPCOM
from
	order_proposals
where
	order_id=?
order by
	oumagloc_sort_on_description(''RPT_PPSL'',
	order_proposal_code))A
left join (
select
	STRING_AGG((select description from community_conditions  where category_type = ''CRT_REP'' and comm_condition_code=opc.comm_condition_code order by opc.comm_condition_code limit 1), '','' order by order_id,order_proposal_code) as LEG_CRPCON,
	STRING_AGG(CAST(length AS TEXT), '','' order by order_id,order_proposal_code) as LEG_CRPCLN,
	STRING_AGG((
                    SELECT description
                    FROM reference_codes rc
                    WHERE domain = ''UNIT'' AND code = length_unit_code
                ), '','' order by order_id,order_proposal_code) as LEG_CRPCCTY,
	STRING_AGG(comment_text , '','' order by order_id,order_proposal_code) as LEG_CRPCCOM,
	order_id,
	order_proposal_code
from
	order_proposal_conditions opc
group by
	order_id,
	order_proposal_code)B on
A.order_proposal_code = B.ORDER_PROPOSAL_CODE
and A.order_id = B.order_id',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
where
	bookmark_name = 'CRT_REP_PROP';

update
	iwp_bookmarks
set
	sql_text = 'select
	voff.offender_id_display as VIS_CVTSVOID,
	voff.last_name as VIS_CVTSVOLN,
	voff.first_name as VIS_CVTSVOFN,
	voff.living_unit_description as VIS_CVTSVOFA,
     case
		when (coalesce(ovv.EVENT_OUTCOME, ''ATT''::character varying)) = ''ATT'' then ''Y''::text
		else ''N''::text
	end as VIS_CVTSVOAT ,
	(
	select
		description
	from
		reference_codes
	where
		"domain" = ''CONTACTS''
		and code = ocp.contact_type)as VIS_CVTSVOCT,
			(
	select
		description
	from
		reference_codes
	where
		"domain" = ''RELATIONSHIP''
		and code = ocp.relationship_type)as VIS_CVTSVORL,
		(
	select
		rc.parent_code
	from
		offender_restrictions ors,
		reference_codes rc
	where
		ors.offender_book_id = ovv.OFFENDER_BOOK_ID
		and rc.domain = ''VST_RST_TYPE''
		and rc.code = ors.restriction_type
		and ors.effective_date::date <= ov.visit_date
		and (ors.expiry_date is null
			or ors.expiry_date::date >ov.visit_date)
	order by
		rc.parent_code)as VIS_CVTSVORS,
ovv.comment_text 
from
	OFFENDER_VISIT_VISITORS ovv,
	v_name_search2_fn(:USER_ID) voff,
	offender_contact_persons ocp,
	offender_bookings ob,
	offender_visits ov
where
	ovv.OFFENDER_BOOK_ID is not null
	and ovv.PERSON_ID is null
	and ovv.offender_book_id = voff.offender_book_id
	and ov.offender_visit_id = ovv.offender_visit_id
	and ob.offender_book_id = ovv.offender_book_id
	and ocp.contact_root_offender_id = ob.root_offender_id
	and ovv.OFFENDER_BOOK_ID != ocp.offender_book_id
	and ocp.offender_book_id =?
	and ovv.OFFENDER_VISIT_ID =?
order by ovv.CREATE_DATETIME desc',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
where
	bookmark_name = 'VISIT_OFFEND';
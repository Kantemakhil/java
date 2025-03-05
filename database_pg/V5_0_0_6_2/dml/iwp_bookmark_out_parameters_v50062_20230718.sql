insert
	into
	iwp_bookmark_out_parameters
(parameter_name,
	bookmark_name,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag,
	parameter_desc)
select
	'CLS_ATYPE',
	'OFF_ASSESS',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null,
	'Assessment Type - Name of the Assessment from the Assessment Card/Block'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'OFF_ASSESS'
		and parameter_name = 'CLS_ATYPE');

insert
	into
	iwp_bookmark_out_parameters
(parameter_name,
	bookmark_name,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag,
	parameter_desc)
select
	'CLS_ASTAT',
	'OFF_ASSESS',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null,
	'Assessement Status from the Assessments Card/Block'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'OFF_ASSESS'
		and parameter_name = 'CLS_ASTAT');

insert
	into
	iwp_bookmark_out_parameters
(parameter_name,
	bookmark_name,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag,
	parameter_desc)
select
	'CLS_ASCORE',
	'OFF_ASSESS',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null,
	'Calculated Assessment Score from the Assessment Card/Block'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'OFF_ASSESS'
		and parameter_name = 'CLS_ASCORE');

insert
	into
	iwp_bookmark_out_parameters
(parameter_name,
	bookmark_name,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag,
	parameter_desc)
select
	'CLS_AOSCORE',
	'OFF_ASSESS',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null,
	'Override Assessment Score from the Assessment Card/Block'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'OFF_ASSESS'
		and parameter_name = 'CLS_AOSCORE');

insert
	into
	iwp_bookmark_out_parameters
(parameter_name,
	bookmark_name,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag,
	parameter_desc)
select
	'CLS_ACRSLT',
	'OFF_ASSESS',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null,
	'Calculated Result from the Assessment Card/Block'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'OFF_ASSESS'
		and parameter_name = 'CLS_ACRSLT');

insert
	into
	iwp_bookmark_out_parameters
(parameter_name,
	bookmark_name,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag,
	parameter_desc)
select
	'CLS_AAPRSLT',
	'OFF_ASSESS',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null,
	'Approved Result from the Assessment Card/Block'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'OFF_ASSESS'
		and parameter_name = 'CLS_AAPRSLT');

insert
	into
	iwp_bookmark_out_parameters
(parameter_name,
	bookmark_name,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag,
	parameter_desc)
select
	'CLS_AORSLT',
	'OFF_ASSESS',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null,
	'Override Result from the Assessment Card/Block'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'OFF_ASSESS'
		and parameter_name = 'CLS_AORSLT');

insert
	into
	iwp_bookmark_out_parameters
(parameter_name,
	bookmark_name,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag,
	parameter_desc)
select
	'CLS_ADFAC',
	'OFF_ASSESS',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null,
	'Facility from the Assessment Details Card/Block based on the record selected in the Assessments Card/Block'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'OFF_ASSESS'
		and parameter_name = 'CLS_ADFAC');

insert
	into
	iwp_bookmark_out_parameters
(parameter_name,
	bookmark_name,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag,
	parameter_desc)
select
	'CLS_ADASSESR',
	'OFF_ASSESS',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null,
	'Assessor from the Assessment Details Card/Block based on the record selected in the Assessments Card/Block'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'OFF_ASSESS'
		and parameter_name = 'CLS_ADASSESR');

insert
	into
	iwp_bookmark_out_parameters
(parameter_name,
	bookmark_name,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag,
	parameter_desc)
select
	'CLS_ADAUTH',
	'OFF_ASSESS',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null,
	'Authority from the Assessment Details Card/Block based on the record selected in the Assessments Card/Block'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'OFF_ASSESS'
		and parameter_name = 'CLS_ADAUTH');

insert
	into
	iwp_bookmark_out_parameters
(parameter_name,
	bookmark_name,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag,
	parameter_desc)
select
	'CLS_ADCRDTE',
	'OFF_ASSESS',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null,
	'Create Date from the Assessment Details Card/Block based on the record selected in the Assessments Card/Block'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'OFF_ASSESS'
		and parameter_name = 'CLS_ADCRDTE');

insert
	into
	iwp_bookmark_out_parameters
(parameter_name,
	bookmark_name,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag,
	parameter_desc)
select
	'CLS_ADSBDTE',
	'OFF_ASSESS',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null,
	'Submission Date from the Assessment Details Card/Block based on the record selected in the Assessments Card/Block'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'OFF_ASSESS'
		and parameter_name = 'CLS_ADSBDTE');

insert
	into
	iwp_bookmark_out_parameters
(parameter_name,
	bookmark_name,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag,
	parameter_desc)
select
	'CLS_ADRADTE',
	'OFF_ASSESS',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null,
	'Re-Assessment Date from the Assessment Details Card/Block based on the record selected in the Assessments Card/Block'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'OFF_ASSESS'
		and parameter_name = 'CLS_ADRADTE');

insert
	into
	iwp_bookmark_out_parameters
(parameter_name,
	bookmark_name,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag,
	parameter_desc)
select
	'CLS_ADACOM',
	'OFF_ASSESS',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null,
	'Assessment Comment from the Assessment Details Card/Block based on the record selected in the Assessments Card/Block'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'OFF_ASSESS'
		and parameter_name = 'CLS_ADACOM');

insert
	into
	iwp_bookmark_out_parameters
(parameter_name,
	bookmark_name,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag,
	parameter_desc)
select
	'CLS_OAORRSLT',
	'OFF_ASSESS',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null,
	'Override Result from the Override Assessment Card/Block based on the record selected in the Assessments Card/Block'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'OFF_ASSESS'
		and parameter_name = 'CLS_OAORRSLT');

insert
	into
	iwp_bookmark_out_parameters
(parameter_name,
	bookmark_name,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag,
	parameter_desc)
select
	'CLS_OAORRSN',
	'OFF_ASSESS',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null,
	'Override reason from the Override Assessment Card/Block based on the record selected in the Assessments Card/Block'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'OFF_ASSESS'
		and parameter_name = 'CLS_OAORRSN');

insert
	into
	iwp_bookmark_out_parameters
(parameter_name,
	bookmark_name,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag,
	parameter_desc)
select
	'CLS_OAORUID',
	'OFF_ASSESS',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null,
	'Override User ID from the Override Assessment Card/Block based on the record selected in the Assessments Card/Block'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'OFF_ASSESS'
		and parameter_name = 'CLS_OAORUID');

insert
	into
	iwp_bookmark_out_parameters
(parameter_name,
	bookmark_name,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag,
	parameter_desc)
select
	'CLS_OARECPLA',
	'OFF_ASSESS',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null,
	'Recommended Placement from the Override Assessment Card/Block based on the record selected in the Assessments Card/Block'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'OFF_ASSESS'
		and parameter_name = 'CLS_OARECPLA');

insert
	into
	iwp_bookmark_out_parameters
(parameter_name,
	bookmark_name,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag,
	parameter_desc)
select
	'CLS_OAORCOM',
	'OFF_ASSESS',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null,
	'Assessment Comment from the Override Assessment Card/Block based on the record selected in the Assessments Card/Block'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'OFF_ASSESS'
		and parameter_name = 'CLS_OAORCOM');

insert
	into
	iwp_bookmark_out_parameters
(parameter_name,
	bookmark_name,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag,
	parameter_desc)
select
	'CLS_APARDTE',
	'OFF_ASSESS',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null,
	'Date field from the Assessment Recommendation Card/Block'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'OFF_ASSESS'
		and parameter_name = 'CLS_APARDTE');

insert
	into
	iwp_bookmark_out_parameters
(parameter_name,
	bookmark_name,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag,
	parameter_desc)
select
	'CLS_APARTYP',
	'OFF_ASSESS',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null,
	'Type field from the Assessment Recommendation Card/Block'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'OFF_ASSESS'
		and parameter_name = 'CLS_APARTYP');

insert
	into
	iwp_bookmark_out_parameters
(parameter_name,
	bookmark_name,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag,
	parameter_desc)
select
	'CLS_APARATH',
	'OFF_ASSESS',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null,
	'Authority field from the Assessment Recommendation Card/Block'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'OFF_ASSESS'
		and parameter_name = 'CLS_APARATH');

insert
	into
	iwp_bookmark_out_parameters
(parameter_name,
	bookmark_name,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag,
	parameter_desc)
select
	'CLS_APARSCR',
	'OFF_ASSESS',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null,
	'Score field from the Assessment Recommendation Card/Block'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'OFF_ASSESS'
		and parameter_name = 'CLS_APARSCR');

insert
	into
	iwp_bookmark_out_parameters
(parameter_name,
	bookmark_name,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag,
	parameter_desc)
select
	'CLS_APARCAL',
	'OFF_ASSESS',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null,
	'Calculated field from the Assessment Recommendation Card/Block'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'OFF_ASSESS'
		and parameter_name = 'CLS_APARCAL');

insert
	into
	iwp_bookmark_out_parameters
(parameter_name,
	bookmark_name,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag,
	parameter_desc)
select
	'CLS_APARORD',
	'OFF_ASSESS',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null,
	'Override field from the Assessment Recommendation Card/Block'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'OFF_ASSESS'
		and parameter_name = 'CLS_APARORD');

insert
	into
	iwp_bookmark_out_parameters
(parameter_name,
	bookmark_name,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag,
	parameter_desc)
select
	'CLS_APARAPR',
	'OFF_ASSESS',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null,
	'Approved field from the Assessment Recommendation Card/Block'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'OFF_ASSESS'
		and parameter_name = 'CLS_APARAPR');

insert
	into
	iwp_bookmark_out_parameters
(parameter_name,
	bookmark_name,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag,
	parameter_desc)
select
	'CLS_APARAPA',
	'OFF_ASSESS',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null,
	'Approval Authority field from the Assessment Recommendation Card/Block'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'OFF_ASSESS'
		and parameter_name = 'CLS_APARAPA');

insert
	into
	iwp_bookmark_out_parameters
(parameter_name,
	bookmark_name,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag,
	parameter_desc)
select
	'CLS_APADTEAP',
	'OFF_ASSESS',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null,
	'Date of Approval field from the Approval Card/Block'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'OFF_ASSESS'
		and parameter_name = 'CLS_APADTEAP');

insert
	into
	iwp_bookmark_out_parameters
(parameter_name,
	bookmark_name,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag,
	parameter_desc)
select
	'CLS_APAAUTH',
	'OFF_ASSESS',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null,
	'Authority field from the Approval Card/Block'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'OFF_ASSESS'
		and parameter_name = 'CLS_APAAUTH');

insert
	into
	iwp_bookmark_out_parameters
(parameter_name,
	bookmark_name,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag,
	parameter_desc)
select
	'CLS_APAAPPD',
	'OFF_ASSESS',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null,
	'Approval Decision field from the Approval Card/Block'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'OFF_ASSESS'
		and parameter_name = 'CLS_APAAPPD');

insert
	into
	iwp_bookmark_out_parameters
(parameter_name,
	bookmark_name,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag,
	parameter_desc)
select
	'CLS_APAAPRS',
	'OFF_ASSESS',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null,
	'Approval Result field from the Approval Card/Block'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'OFF_ASSESS'
		and parameter_name = 'CLS_APAAPRS');

insert
	into
	iwp_bookmark_out_parameters
(parameter_name,
	bookmark_name,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag,
	parameter_desc)
select
	'CLS_APACOM',
	'OFF_ASSESS',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null,
	'Comment field from the Approval Card/Block'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'OFF_ASSESS'
		and parameter_name = 'CLS_APACOM');

insert
	into
	iwp_bookmark_out_parameters
(parameter_name,
	bookmark_name,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag,
	parameter_desc)
select
	'CLS_APAUID',
	'OFF_ASSESS',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null,
	'User field from the Approval Card/Block'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'OFF_ASSESS'
		and parameter_name = 'CLS_APAUID');

insert
	into
	iwp_bookmark_out_parameters
(parameter_name,
	bookmark_name,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag,
	parameter_desc)
select
	'CLS_APAAPPLC',
	'OFF_ASSESS',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null,
	'Approved Placement field from the Approval Card/Block'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'OFF_ASSESS'
		and parameter_name = 'CLS_APAAPPLC');

insert
	into
	iwp_bookmark_out_parameters
(parameter_name,
	bookmark_name,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag,
	parameter_desc)
select
	'CLS_APACOM2',
	'OFF_ASSESS',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null,
	'Comment(2) field from the Approval Card/Block'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'OFF_ASSESS'
		and parameter_name = 'CLS_APACOM2');

insert
	into
	iwp_bookmark_out_parameters
(parameter_name,
	bookmark_name,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag,
	parameter_desc)
select
	'CLS_APANRD',
	'OFF_ASSESS',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null,
	'Next Review Date from the Approval Card/Block'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'OFF_ASSESS'
		and parameter_name = 'CLS_APANRD');

insert
	into
	iwp_bookmark_out_parameters
(parameter_name,
	bookmark_name,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag,
	parameter_desc)
select
	'CLS_APACOM3',
	'OFF_ASSESS',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null,
	'Comment(3) field from the Approval Card/Block'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'OFF_ASSESS'
		and parameter_name = 'CLS_APACOM3');

insert
	into
	iwp_bookmark_out_parameters
(parameter_name,
	bookmark_name,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag,
	parameter_desc)
select
	'LEG_NCODFNM',
	'LEG_NCUS',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null,
	'No. field from the top Card/Block'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'LEG_NCUS'
		and parameter_name = 'LEG_NCODFNM');

insert
	into
	iwp_bookmark_out_parameters
(parameter_name,
	bookmark_name,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag,
	parameter_desc)
select
	'LEG_NCODODTE',
	'LEG_NCUS',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null,
	'Ordered Date field from the top Card/Block'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'LEG_NCUS'
		and parameter_name = 'LEG_NCODODTE');

insert
	into
	iwp_bookmark_out_parameters
(parameter_name,
	bookmark_name,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag,
	parameter_desc)
select
	'LEG_NCODCMTN',
	'LEG_NCUS',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null,
	'Case(s)/Matter(s) field from the top Card/Block'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'LEG_NCUS'
		and parameter_name = 'LEG_NCODCMTN');

insert
	into
	iwp_bookmark_out_parameters
(parameter_name,
	bookmark_name,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag,
	parameter_desc)
select
	'LEG_NCODCRT',
	'LEG_NCUS',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null,
	'Court field from the top Card/Block'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'LEG_NCUS'
		and parameter_name = 'LEG_NCODCRT');

insert
	into
	iwp_bookmark_out_parameters
(parameter_name,
	bookmark_name,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag,
	parameter_desc)
select
	'LEG_NCODOTYP',
	'LEG_NCUS',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null,
	'Type field from the top Card/Block'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'LEG_NCUS'
		and parameter_name = 'LEG_NCODOTYP');

insert
	into
	iwp_bookmark_out_parameters
(parameter_name,
	bookmark_name,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag,
	parameter_desc)
select
	'LEG_NCODCTYP',
	'LEG_NCUS',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null,
	'Commencement Type field from the top Card/Block'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'LEG_NCUS'
		and parameter_name = 'LEG_NCODCTYP');

insert
	into
	iwp_bookmark_out_parameters
(parameter_name,
	bookmark_name,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag,
	parameter_desc)
select
	'LEG_NCODLNKT',
	'LEG_NCUS',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null,
	'Link To field from the top Card/Block'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'LEG_NCUS'
		and parameter_name = 'LEG_NCODLNKT');

insert
	into
	iwp_bookmark_out_parameters
(parameter_name,
	bookmark_name,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag,
	parameter_desc)
select
	'LEG_NCODCDTE',
	'LEG_NCUS',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null,
	'Commencement Date field from the top Card/Block'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'LEG_NCUS'
		and parameter_name = 'LEG_NCODCDTE');

insert
	into
	iwp_bookmark_out_parameters
(parameter_name,
	bookmark_name,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag,
	parameter_desc)
select
	'LEG_NCODDTN',
	'LEG_NCUS',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null,
	'Duration field from the top Card/Block'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'LEG_NCUS'
		and parameter_name = 'LEG_NCODDTN');

insert
	into
	iwp_bookmark_out_parameters
(parameter_name,
	bookmark_name,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag,
	parameter_desc)
select
	'LEG_NCODEDTE',
	'LEG_NCUS',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null,
	'Expiry Date field from the top Card/Block'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'LEG_NCUS'
		and parameter_name = 'LEG_NCODEDTE');

insert
	into
	iwp_bookmark_out_parameters
(parameter_name,
	bookmark_name,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag,
	parameter_desc)
select
	'LEG_NCODSTS',
	'LEG_NCUS',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null,
	'Status field from the top Card/Block'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'LEG_NCUS'
		and parameter_name = 'LEG_NCODSTS');

insert
	into
	iwp_bookmark_out_parameters
(parameter_name,
	bookmark_name,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag,
	parameter_desc)
select
	'LEG_NCODTNM',
	'LEG_NCUS_CHG',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null,
	'Matter number field from the bottom Card/Block'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'LEG_NCUS_CHG'
		and parameter_name = 'LEG_NCODTNM');

insert
	into
	iwp_bookmark_out_parameters
(parameter_name,
	bookmark_name,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag,
	parameter_desc)
select
	'LEG_NCODCHRG',
	'LEG_NCUS_CHG',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null,
	'Charge field from the bottom Card/Block'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'LEG_NCUS_CHG'
		and parameter_name = 'LEG_NCODCHRG');

insert
	into
	iwp_bookmark_out_parameters
(parameter_name,
	bookmark_name,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag,
	parameter_desc)
select
	'LEG_NCODOTCM',
	'LEG_NCUS_CHG',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null,
	'Outcome field from the bottom Card/Block'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'LEG_NCUS_CHG'
		and parameter_name = 'LEG_NCODOTCM');

insert
	into
	iwp_bookmark_out_parameters
(parameter_name,
	bookmark_name,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag,
	parameter_desc)
select
	'LEG_NCODTYPE',
	'LEG_NCUS_CHG',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null,
	'Type field from the bottom Card/Block'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'LEG_NCUS_CHG'
		and parameter_name = 'LEG_NCODTYPE');

insert
	into
	iwp_bookmark_out_parameters
(parameter_name,
	bookmark_name,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag,
	parameter_desc)
select
	'LEG_NCODPLEA',
	'LEG_NCHG_DET',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null,
	'PLEA field from the details (Charge Details) Card/Block'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'LEG_NCHG_DET'
		and parameter_name = 'LEG_NCODPLEA');

insert
	into
	iwp_bookmark_out_parameters
(parameter_name,
	bookmark_name,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag,
	parameter_desc)
select
	'LEG_NCODINDT',
	'LEG_NCHG_DET',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null,
	'Incident Date field from the details (Charge Details) Card/Block'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'LEG_NCHG_DET'
		and parameter_name = 'LEG_NCODINDT');

insert
	into
	iwp_bookmark_out_parameters
(parameter_name,
	bookmark_name,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag,
	parameter_desc)
select
	'LEG_NCODRAN',
	'LEG_NCHG_DET',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null,
	'Range field from the details (Charge Details) Card/Block'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'LEG_NCHG_DET'
		and parameter_name = 'LEG_NCODRAN');

insert
	into
	iwp_bookmark_out_parameters
(parameter_name,
	bookmark_name,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag,
	parameter_desc)
select
	'LEG_NCODPART',
	'LEG_NCHG_DET',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null,
	'Particulars field from the details (Charge Details) Card/Block'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'LEG_NCHG_DET'
		and parameter_name = 'LEG_NCODPART');

insert
	into
	iwp_bookmark_out_parameters
(parameter_name,
	bookmark_name,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag,
	parameter_desc)
select
	'CHARGEID',
	'LEG_NCHG_DET',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null,
	'CHARGEID'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'LEG_NCHG_DET'
		and parameter_name = 'CHARGEID');

insert
	into
	iwp_bookmark_out_parameters
(parameter_name,
	bookmark_name,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag,
	parameter_desc)
select
	'OFFENDERBOOKID',
	'LEG_NCHG_DET',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null,
	'OFFENDERBOOKID'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'LEG_NCHG_DET'
		and parameter_name = 'OFFENDERBOOKID');

insert
	into
	iwp_bookmark_out_parameters
(parameter_name,
	bookmark_name,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag,
	parameter_desc)
select
	'CODE',
	'LEG_NCHG_CAT',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null,
	'CODE'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'LEG_NCHG_CAT'
		and parameter_name = 'CODE');

insert
	into
	iwp_bookmark_out_parameters
(parameter_name,
	bookmark_name,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag,
	parameter_desc)
select
	'LEG_NCODCAT',
	'LEG_NCHG_CAT',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null,
	'Category field from the details (Charge Details) Card/Block'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'LEG_NCHG_CAT'
		and parameter_name = 'LEG_NCODCAT');

insert
	into
	iwp_bookmark_out_parameters
(parameter_name,
	bookmark_name,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag,
	parameter_desc)
select
	'LEG_NCODSEV',
	'LEG_NCHG_CAT',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null,
	'LEG_NCODSEV'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'LEG_NCHG_CAT'
		and parameter_name = 'LEG_NCODSEV');

insert
	into
	iwp_bookmark_out_parameters
(parameter_name,
	bookmark_name,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag,
	parameter_desc)
select
	'OFFENCEID',
	'LEG_NCHG_CAT',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null,
	'OFFENCEID'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'LEG_NCHG_CAT'
		and parameter_name = 'OFFENCEID');

insert
	into
	iwp_bookmark_out_parameters
(parameter_name,
	bookmark_name,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag,
	parameter_desc)
select
	'LEG_NCODINCD',
	'LEG_NCHG_IND',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null,
	'Indicator Code field from the details (Charge Details) Card/Block'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'LEG_NCHG_IND'
		and parameter_name = 'LEG_NCODINCD');

insert
	into
	iwp_bookmark_out_parameters
(parameter_name,
	bookmark_name,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag,
	parameter_desc)
select
	'LEG_BAILONMF',
	'LEG_BAIL',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null,
	'No. field from the top Card/Block'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'LEG_BAIL'
		and parameter_name = 'LEG_BAILONMF');

insert
	into
	iwp_bookmark_out_parameters
(parameter_name,
	bookmark_name,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag,
	parameter_desc)
select
	'LEG_BAILODTE',
	'LEG_BAIL',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null,
	'Ordered Date field from the top Card/Block'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'LEG_BAIL'
		and parameter_name = 'LEG_BAILODTE');

insert
	into
	iwp_bookmark_out_parameters
(parameter_name,
	bookmark_name,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag,
	parameter_desc)
select
	'LEG_BAILCMTN',
	'LEG_BAIL',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null,
	'Case(s)/Matter(s) number field from the top Card/Block'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'LEG_BAIL'
		and parameter_name = 'LEG_BAILCMTN');

insert
	into
	iwp_bookmark_out_parameters
(parameter_name,
	bookmark_name,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag,
	parameter_desc)
select
	'LEG_BAILCRT',
	'LEG_BAIL',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null,
	'Court field from the top Card/Block'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'LEG_BAIL'
		and parameter_name = 'LEG_BAILCRT');

insert
	into
	iwp_bookmark_out_parameters
(parameter_name,
	bookmark_name,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag,
	parameter_desc)
select
	'LEG_BAILOTYP',
	'LEG_BAIL',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null,
	'Type field from the top Card/Block'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'LEG_BAIL'
		and parameter_name = 'LEG_BAILOTYP');

insert
	into
	iwp_bookmark_out_parameters
(parameter_name,
	bookmark_name,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag,
	parameter_desc)
select
	'LEG_BAILLGTN',
	'LEG_BAIL',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null,
	'Legislation field from the top Card/Block'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'LEG_BAIL'
		and parameter_name = 'LEG_BAILLGTN');

insert
	into
	iwp_bookmark_out_parameters
(parameter_name,
	bookmark_name,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag,
	parameter_desc)
select
	'LEG_BAILCMDT',
	'LEG_BAIL',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null,
	'Commence Date field from the top Card/Block'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'LEG_BAIL'
		and parameter_name = 'LEG_BAILCMDT');

insert
	into
	iwp_bookmark_out_parameters
(parameter_name,
	bookmark_name,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag,
	parameter_desc)
select
	'LEG_BAILEXDT',
	'LEG_BAIL',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null,
	'Expiry Date field from the top Card/Block'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'LEG_BAIL'
		and parameter_name = 'LEG_BAILEXDT');

insert
	into
	iwp_bookmark_out_parameters
(parameter_name,
	bookmark_name,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag,
	parameter_desc)
select
	'LEG_BAILSGRQ',
	'LEG_BAIL',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null,
	'Signature Required Status field from the top Card/Block'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'LEG_BAIL'
		and parameter_name = 'LEG_BAILSGRQ');

insert
	into
	iwp_bookmark_out_parameters
(parameter_name,
	bookmark_name,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag,
	parameter_desc)
select
	'LEG_BAILOSTS',
	'LEG_BAIL',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null,
	'Status field from the top Card/Block '
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'LEG_BAIL'
		and parameter_name = 'LEG_BAILOSTS');

insert
	into
	iwp_bookmark_out_parameters
(parameter_name,
	bookmark_name,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag,
	parameter_desc)
select
	'LEG_BAILMTNM',
	'LEG_BAIL_CHG',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null,
	'Matter number field from the bottom Card/Block'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'LEG_BAIL_CHG'
		and parameter_name = 'LEG_BAILMTNM');

insert
	into
	iwp_bookmark_out_parameters
(parameter_name,
	bookmark_name,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag,
	parameter_desc)
select
	'LEG_BAILCHRG',
	'LEG_BAIL_CHG',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null,
	'Charge field from the bottom Card/Block'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'LEG_BAIL_CHG'
		and parameter_name = 'LEG_BAILCHRG');

insert
	into
	iwp_bookmark_out_parameters
(parameter_name,
	bookmark_name,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag,
	parameter_desc)
select
	'LEG_BAILOTCM',
	'LEG_BAIL_CHG',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null,
	'Outcome field from the bottom Card/Block'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'LEG_BAIL_CHG'
		and parameter_name = 'LEG_BAILOTCM');

insert
	into
	iwp_bookmark_out_parameters
(parameter_name,
	bookmark_name,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag,
	parameter_desc)
select
	'LEG_BAILTYPE',
	'LEG_BAIL_CHG',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null,
	'Type field from the bottom Card/Block'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'LEG_BAIL_CHG'
		and parameter_name = 'LEG_BAILTYPE');

insert
	into
	iwp_bookmark_out_parameters
(parameter_name,
	bookmark_name,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag,
	parameter_desc)
select
	'LEG_BAILPLEA',
	'LEG_BCHG_DET',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null,
	'PLEA field from the details (Charge Details) Card/Block'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'LEG_BCHG_DET'
		and parameter_name = 'LEG_BAILPLEA');

insert
	into
	iwp_bookmark_out_parameters
(parameter_name,
	bookmark_name,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag,
	parameter_desc)
select
	'LEG_BAILINDT',
	'LEG_BCHG_DET',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null,
	'Incident Date field from the details (Charge Details) Card/Block'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'LEG_BCHG_DET'
		and parameter_name = 'LEG_BAILINDT');

insert
	into
	iwp_bookmark_out_parameters
(parameter_name,
	bookmark_name,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag,
	parameter_desc)
select
	'LEG_BAILRAN',
	'LEG_BCHG_DET',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null,
	'Range field from the details (Charge Details) Card/Block'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'LEG_BCHG_DET'
		and parameter_name = 'LEG_BAILRAN');

insert
	into
	iwp_bookmark_out_parameters
(parameter_name,
	bookmark_name,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag,
	parameter_desc)
select
	'LEG_BAILPAR',
	'LEG_BCHG_DET',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null,
	'Particulars field from the details (Charge Details) Card/Block'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'LEG_BCHG_DET'
		and parameter_name = 'LEG_BAILPAR');

insert
	into
	iwp_bookmark_out_parameters
(parameter_name,
	bookmark_name,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag,
	parameter_desc)
select
	'CHARGEID',
	'LEG_BCHG_DET',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null,
	'CHARGEID'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'LEG_BCHG_DET'
		and parameter_name = 'CHARGEID');

insert
	into
	iwp_bookmark_out_parameters
(parameter_name,
	bookmark_name,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag,
	parameter_desc)
select
	'OFFENDERBOOKID',
	'LEG_BCHG_DET',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null,
	'OFFENDERBOOKID'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'LEG_BCHG_DET'
		and parameter_name = 'OFFENDERBOOKID');

insert
	into
	iwp_bookmark_out_parameters
(parameter_name,
	bookmark_name,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag,
	parameter_desc)
select
	'LEG_PAROFNM',
	'LEG_PAR',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null,
	'No. field from the top Card/Block'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'LEG_PAR'
		and parameter_name = 'LEG_PAROFNM');

insert
	into
	iwp_bookmark_out_parameters
(parameter_name,
	bookmark_name,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag,
	parameter_desc)
select
	'LEG_PAROODTE',
	'LEG_PAR',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null,
	'Hearing Date field from the top Card/Block'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'LEG_PAR'
		and parameter_name = 'LEG_PAROODTE');

insert
	into
	iwp_bookmark_out_parameters
(parameter_name,
	bookmark_name,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag,
	parameter_desc)
select
	'LEG_PAROCMTN',
	'LEG_PAR',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null,
	' Authority field from the top Card/Block'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'LEG_PAR'
		and parameter_name = 'LEG_PAROCMTN');

insert
	into
	iwp_bookmark_out_parameters
(parameter_name,
	bookmark_name,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag,
	parameter_desc)
select
	'LEG_PAROOTYP',
	'LEG_PAR',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null,
	'Type field from the top Card/Block'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'LEG_PAR'
		and parameter_name = 'LEG_PAROOTYP');

insert
	into
	iwp_bookmark_out_parameters
(parameter_name,
	bookmark_name,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag,
	parameter_desc)
select
	'LEG_PAROCTYP',
	'LEG_PAR',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null,
	'Commence Type field from the top Card/Block'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'LEG_PAR'
		and parameter_name = 'LEG_PAROCTYP');

insert
	into
	iwp_bookmark_out_parameters
(parameter_name,
	bookmark_name,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag,
	parameter_desc)
select
	'LEG_PARODUTN',
	'LEG_PAR',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null,
	'Duration field from the top Card/Block'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'LEG_PAR'
		and parameter_name = 'LEG_PARODUTN');

insert
	into
	iwp_bookmark_out_parameters
(parameter_name,
	bookmark_name,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag,
	parameter_desc)
select
	'LEG_PAROCDTE',
	'LEG_PAR',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null,
	'Commence Date field from the top Card/Block'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'LEG_PAR'
		and parameter_name = 'LEG_PAROCDTE');

insert
	into
	iwp_bookmark_out_parameters
(parameter_name,
	bookmark_name,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag,
	parameter_desc)
select
	'LEG_PAROEXDT',
	'LEG_PAR',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null,
	'Expiry Date field from the top Card/Block'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'LEG_PAR'
		and parameter_name = 'LEG_PAROEXDT');

insert
	into
	iwp_bookmark_out_parameters
(parameter_name,
	bookmark_name,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag,
	parameter_desc)
select
	'LEG_PAROOSTS',
	'LEG_PAR',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null,
	'Status field from the top Card/Block'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'LEG_PAR'
		and parameter_name = 'LEG_PAROOSTS');

insert
	into
	iwp_bookmark_out_parameters
(parameter_name,
	bookmark_name,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag,
	parameter_desc)
select
	'LEG_POAONMB',
	'LEG_PAR_AFFE',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null,
	'Number field from the bottom (Affected Orders) Card/Block'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'LEG_PAR_AFFE'
		and parameter_name = 'LEG_POAONMB');

insert
	into
	iwp_bookmark_out_parameters
(parameter_name,
	bookmark_name,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag,
	parameter_desc)
select
	'LEG_POAOTYPE',
	'LEG_PAR_AFFE',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null,
	'Type field from the bottom (Affected Orders) Card/Block '
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'LEG_PAR_AFFE'
		and parameter_name = 'LEG_POAOTYPE');

insert
	into
	iwp_bookmark_out_parameters
(parameter_name,
	bookmark_name,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag,
	parameter_desc)
select
	'LEG_POAOTERM',
	'LEG_PAR_AFFE',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null,
	'Term field from the bottom (Affected Orders) Card/Block'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'LEG_PAR_AFFE'
		and parameter_name = 'LEG_POAOTERM');

insert
	into
	iwp_bookmark_out_parameters
(parameter_name,
	bookmark_name,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag,
	parameter_desc)
select
	'LEG_POAORLTD',
	'LEG_PAR_AFFE',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null,
	'Related to field from the bottom (Affected Orders) Card/Block '
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'LEG_PAR_AFFE'
		and parameter_name = 'LEG_POAORLTD');

insert
	into
	iwp_bookmark_out_parameters
(parameter_name,
	bookmark_name,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag,
	parameter_desc)
select
	'LEG_POAOCMDT',
	'LEG_PAR_AFFE',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null,
	'Commence Date field from the bottom (Affected Orders) Card/Block'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'LEG_PAR_AFFE'
		and parameter_name = 'LEG_POAOCMDT');

insert
	into
	iwp_bookmark_out_parameters
(parameter_name,
	bookmark_name,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag,
	parameter_desc)
select
	'LEG_POAOERD',
	'LEG_PAR_AFFE',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null,
	'ERD field from the bottom (Affected Orders) Card/Block'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'LEG_PAR_AFFE'
		and parameter_name = 'LEG_POAOERD');

insert
	into
	iwp_bookmark_out_parameters
(parameter_name,
	bookmark_name,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag,
	parameter_desc)
select
	'LEG_POAOPED',
	'LEG_PAR_AFFE',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null,
	'PED field from the bottom (Affected Orders) Card/Block'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'LEG_PAR_AFFE'
		and parameter_name = 'LEG_POAOPED');

insert
	into
	iwp_bookmark_out_parameters
(parameter_name,
	bookmark_name,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag,
	parameter_desc)
select
	'LEG_POAOLRD',
	'LEG_PAR_AFFE',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null,
	'LRD field from the bottom (Affected Orders) Card/Block'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'LEG_PAR_AFFE'
		and parameter_name = 'LEG_POAOLRD');

insert
	into
	iwp_bookmark_out_parameters
(parameter_name,
	bookmark_name,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag,
	parameter_desc)
select
	'LEG_POAOSTS',
	'LEG_PAR_AFFE',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null,
	'Status field from the bottom (Affected Orders) Card/Block'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'LEG_PAR_AFFE'
		and parameter_name = 'LEG_POAOSTS');

insert
	into
	iwp_bookmark_out_parameters
(parameter_name,
	bookmark_name,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag,
	parameter_desc)
select
	'LEG_PAROTYPE',
	'LEG_PAR_DUR',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null,
	'Term Type field from the Length Btn (Order Duration) Card/Block'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'LEG_PAR_DUR'
		and parameter_name = 'LEG_PAROTYPE');

insert
	into
	iwp_bookmark_out_parameters
(parameter_name,
	bookmark_name,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag,
	parameter_desc)
select
	'LEG_PAROYRS',
	'LEG_PAR_DUR',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null,
	'Years field from the Length Btn (Order Duration) Card/Block'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'LEG_PAR_DUR'
		and parameter_name = 'LEG_PAROYRS');

insert
	into
	iwp_bookmark_out_parameters
(parameter_name,
	bookmark_name,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag,
	parameter_desc)
select
	'LEG_PAROMTHS',
	'LEG_PAR_DUR',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null,
	'Months field from the Length Btn (Order Duration) Card/Block'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'LEG_PAR_DUR'
		and parameter_name = 'LEG_PAROMTHS');

insert
	into
	iwp_bookmark_out_parameters
(parameter_name,
	bookmark_name,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag,
	parameter_desc)
select
	'LEG_PAROWKS',
	'LEG_PAR_DUR',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null,
	'Weeks field from the Length Btn (Order Duration) Card/Block'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'LEG_PAR_DUR'
		and parameter_name = 'LEG_PAROWKS');

insert
	into
	iwp_bookmark_out_parameters
(parameter_name,
	bookmark_name,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag,
	parameter_desc)
select
	'LEG_PARODAYS',
	'LEG_PAR_DUR',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null,
	'Days field from the Length Btn (Order Duration) Card/Block'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'LEG_PAR_DUR'
		and parameter_name = 'LEG_PARODAYS');
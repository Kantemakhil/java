update
	oms_owner.iwp_bookmarks
set
	sql_text = 'select
(select description from reference_codes rc where domain = ''PLEA_STATUS'' and code = plea) as LEG_NCODPLEA,
to_char(nullif(incidentdate,'''')::timestamp,''DD-MM-YYYY'') as LEG_NCODINDT,
to_char(nullif(range,'''')::timestamp,''DD-MM-YYYY'') as LEG_NCODRAN,
particulars as LEG_NCODPART,
chargeid,
offenderbookid
from 
(select
	jsonb_array_elements(encode(form_info_json, ''escape'')::jsonb)->>''plea'' as plea,
	jsonb_array_elements(encode(form_info_json, ''escape'')::jsonb)->>''Range'' as range,
	jsonb_array_elements(encode(form_info_json, ''escape'')::jsonb)->>''incidentDate'' as incidentdate,
	jsonb_array_elements(encode(form_info_json, ''escape'')::jsonb)->>''particulars'' as particulars,
	jsonb_array_elements(encode(form_info_json, ''escape'')::jsonb)->>''chargeId'' as chargeid,
	form_identifier::jsonb->>''offenderBookId'' as offenderbookid
from
	ocdchgsu_data
)A where offenderbookid=? and chargeid=?',
	modify_datetime = current_timestamp ,
	modify_user_id = 'OMS_OWNER'
where
	bookmark_name = 'LEG_NCHG_DET';

update
	oms_owner.iwp_bookmarks
set
	sql_text = 'select
(select description from reference_codes rc where domain = ''PLEA_STATUS'' and code = plea) as LEG_BAILPLEA,
to_char(nullif(incidentdate,'''')::timestamp,''DD-MM-YYYY'') as LEG_BAILINDT,
to_char(nullif(range,'''')::timestamp,''DD-MM-YYYY'') as LEG_BAILRAN,
particulars as LEG_BAILPAR,
chargeid,
offenderbookid
from 
(select
	jsonb_array_elements(encode(form_info_json, ''escape'')::jsonb)->>''plea'' as plea,
	jsonb_array_elements(encode(form_info_json, ''escape'')::jsonb)->>''Range'' as range,
	jsonb_array_elements(encode(form_info_json, ''escape'')::jsonb)->>''incidentDate'' as incidentdate,
	jsonb_array_elements(encode(form_info_json, ''escape'')::jsonb)->>''particulars'' as particulars,
	jsonb_array_elements(encode(form_info_json, ''escape'')::jsonb)->>''chargeId'' as chargeid,
	form_identifier::jsonb->>''offenderBookId'' as offenderbookid
from
	ocdchgsu_data
)A where offenderbookid=? and chargeid=?',
	modify_datetime = current_timestamp ,
	modify_user_id = 'OMS_OWNER'
where
	bookmark_name = 'LEG_BCHG_DET';
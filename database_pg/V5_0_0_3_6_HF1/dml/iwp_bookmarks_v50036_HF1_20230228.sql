INSERT INTO iwp_bookmarks
(bookmark_name, active_flag, bookmark_type, description, sql_text, date_created, user_created, create_datetime, create_user_id, modify_datetime, modify_user_id, expiry_date, sql_verified_flag, seal_flag)
VALUES('PHYS_CHAR', 'Y', 'COMP', 'Composite bookmark for Physical Characteristics', 'select
	description,
	profile_code
from
	(
	select
		PT.description ,
		OPD.PROFILE_CODE ,
		OPD.LIST_SEQ
	from
		OFFENDER_PROFILE_DETAILS OPD
	inner join PROFILE_TYPES PT on
		OPD.PROFILE_TYPE = PT.PROFILE_TYPE
	where
		OFFENDER_BOOK_ID =?
		and PROFILE_SEQ = 1
	order by
		LIST_SEQ)A',current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL, NULL, NULL, 'N', NULL);

INSERT INTO oms_owner.iwp_bookmarks
(bookmark_name, active_flag, bookmark_type, description, sql_text, date_created, user_created, create_datetime, create_user_id, modify_datetime, modify_user_id, expiry_date, sql_verified_flag, seal_flag)
VALUES('PHYS_MARKS', 'Y', 'COMP', 'Composite bookmark for Physical Marks', 'select
	MARK_TYPE as type,
	(
	select
		description
	from
		reference_codes rc
	where
		domain = ''SIDE''
		and code = SIDE_CODE) as side,
	 (
	select
		description
	from
		reference_codes rc
	where
		domain = ''BODY_PART''
		and code = body_part_code)as Body_Part,
	(
	select
		description
	from
		reference_codes rc
	where
		domain = ''PART_ORIENT''
		and code = PART_ORIENTATION_CODE) as ORIENTATION,
	COMMENT_TEXT as comment

from
	OFFENDER_IDENTIFYING_MARKS oim
where
	OFFENDER_BOOK_ID = ?', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL, NULL, NULL, 'N', NULL);


INSERT INTO oms_owner.iwp_bookmarks
(bookmark_name, active_flag, bookmark_type, description, sql_text, date_created, user_created, create_datetime, create_user_id, modify_datetime, modify_user_id, expiry_date, sql_verified_flag, seal_flag)
VALUES('PHYS_ATTRI', 'Y', 'COMP', 'Composite bookmark for Physical Attributes', 'select
	HEIGHT_FT as FEET,
	HEIGHT_IN as Inches,
	HEIGHT_CM Centimeters,
	WEIGHT_LBS Pounds,
	WEIGHT_KG Kilograms,
	(
	select
		(
		select
			description
		from
			reference_codes rc
		where
			domain = ''ETHNICITY''
			and code = race_code) as race
	from
		offenders o,
		offender_bookings ob
	where
		o.offender_id = ob.offender_id
		and ob.offender_book_id = opa.OFFENDER_BOOK_ID)
from
	OFFENDER_PHYSICAL_ATTRIBUTES opa
where
	OFFENDER_BOOK_ID = ?', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL, NULL, NULL, 'N', NULL);

INSERT INTO oms_owner.iwp_bookmarks
(bookmark_name, active_flag, bookmark_type, description, sql_text, date_created, user_created, create_datetime, create_user_id, modify_datetime, modify_user_id, expiry_date, sql_verified_flag, seal_flag)
VALUES('PHY_MKS_IMG', 'Y', 'BINARY', 'Physical Identifier Marks Images', 'select
	image_thumbnail
from images B,
	OFFENDER_IDENTIFYING_MARKS A
	
where
	B.image_object_id = A.offender_book_id
	and offender_book_id = ?
	and B.active_flag = ''Y''
	and image_object_type = ''OFF_IDM''', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL, NULL, NULL, 'N', NULL);	
	
	
insert

	into

	system_profiles (

	profile_type,

	profile_code,

	description,

	profile_value,

	create_user_id,

	create_datetime,

	seal_flag)

select 'CLIENT',

'BOOK_ID_OUTB',

'Y: Included leading zeros, N: Do not included leading zeros',

'N',

'OMS_OWNER',

current_timestamp,

null

where

not exists (

select

	1

from

	system_profiles

where

	profile_type = 'CLIENT'

	and profile_code = 'BOOK_ID_OUTB');
	
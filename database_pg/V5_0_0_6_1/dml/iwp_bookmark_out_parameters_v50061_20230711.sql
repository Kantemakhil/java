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
	'PARTY_TYPE',
	'CUS_INT_PART',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	null,
	'PARTY_TYPE'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'CUS_INT_PART'
		and parameter_name = 'PARTY_TYPE');

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
	'PARTY_DESCRIPTION',
	'CUS_INT_PART',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	null,
	'PARTY_DESCRIPTION'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'CUS_INT_PART'
		and parameter_name = 'PARTY_DESCRIPTION');

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
	'PARTY_COMMENT',
	'CUS_INT_PART',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	null,
	'PARTY_COMMENT'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'CUS_INT_PART'
		and parameter_name = 'PARTY_COMMENT');

update
	iwp_bookmark_out_parameters
set
	parameter_desc = 'Request and Grievances Issue ID for Selected Grid Record',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
where
	parameter_name = 'ISS_RGISSID';

update
	iwp_bookmark_out_parameters
set
	parameter_desc = 'Request and Grievances Issue Date for Selected Grid Record (MM-DD-YYYY)',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
where
	parameter_name = 'ISS_RGISDTE1';

update
	iwp_bookmark_out_parameters
set
	parameter_desc = 'Request and Grievances Issue Date for Selected Grid Record (DD-MM-YYYY)',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
where
	parameter_name = 'ISS_RGISDTE2';

update
	iwp_bookmark_out_parameters
set
	parameter_desc = 'Request and Grievances Issue Date for Selected Grid Record (YYYY-MM-DD)',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
where
	parameter_name = 'ISS_RGISDTE3';

update
	iwp_bookmark_out_parameters
set
	parameter_desc = 'Request and Grievances Issue Type for the Selected Grid Record',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
where
	parameter_name = 'ISS_RGISTYP';

update
	iwp_bookmark_out_parameters
set
	parameter_desc = 'Request and Grievances Issue Reason for the Selected Grid Record',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
where
	parameter_name = 'ISS_RGISSRSN';

update
	iwp_bookmark_out_parameters
set
	parameter_desc = 'Request and Grievances Issue Related Offence in Custody Number for the Selected Grid Record',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
where
	parameter_name = 'ISS_RGOICNUM';

update
	iwp_bookmark_out_parameters
set
	parameter_desc = 'Request and Grievances Issue Claim Amount for the Selected Grid Record',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
where
	parameter_name = 'ISS_RGCLAMNT';

update
	iwp_bookmark_out_parameters
set
	parameter_desc = 'Request and Grievances Issue Filed At Location for the Selected Grid Record',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
where
	parameter_name = 'ISS_RGFLLOC';

update
	iwp_bookmark_out_parameters
set
	parameter_desc = 'Request and Grievances Issue Current Level for the Selected Grid Record',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
where
	parameter_name = 'ISS_RGCURLVL';

update
	iwp_bookmark_out_parameters
set
	parameter_desc = 'Request and Grievances Issue Status for the Selected Grid Record',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
where
	parameter_name = 'ISS_RGCURSTS';

update
	iwp_bookmark_out_parameters
set
	parameter_desc = 'Request and Grievances Issue Comment for the Selected Grid Record',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
where
	parameter_name = 'ISS_RGCOMNT';

update
	iwp_bookmark_out_parameters
set
	parameter_desc = 'Request and Grievances Issue Transaction Date for the Selected Grid Record (MM-DD-YYYY)',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
where
	parameter_name = 'ISS_RGTRDTE1';

update
	iwp_bookmark_out_parameters
set
	parameter_desc = 'Request and Grievances Issue Transaction Date for the Selected Grid Record (DD-MM-YYYY)',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
where
	parameter_name = 'ISS_RGTRDTE2';

update
	iwp_bookmark_out_parameters
set
	parameter_desc = 'Request and Grievances Issue Transaction Date for the Selected Grid Record (YYYY-MM-DD)',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
where
	parameter_name = 'ISS_RGTRDTE3';

update
	iwp_bookmark_out_parameters
set
	parameter_desc = 'Request and Grievances Issue Transaction for the Selected Grid Record',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
where
	parameter_name = 'ISS_RGTRNACT';

update
	iwp_bookmark_out_parameters
set
	parameter_desc = 'Request and Grievances Issue Transaction Finding for the Selected Grid Record',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
where
	parameter_name = 'ISS_RGTFIND';

update
	iwp_bookmark_out_parameters
set
	parameter_desc = 'Request and Grievances Issue Transaction Assigned To for the Selected Grid Record',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
where
	parameter_name = 'ISS_RGTASSGN';

update
	iwp_bookmark_out_parameters
set
	parameter_desc = 'Request and Grievances Issue Transaction Level for the Selected Grid Record',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
where
	parameter_name = 'ISS_RGTRNLVL';

update
	iwp_bookmark_out_parameters
set
	parameter_desc = 'Request and Grievances Issue Transaction Days Left for the Selected Grid Record',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
where
	parameter_name = 'ISS_RGTDYLFT';

update
	iwp_bookmark_out_parameters
set
	parameter_desc = 'Request and Grievances Issue Transaction User ID for the Selected Grid Record',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
where
	parameter_name = 'ISS_RGTUID';

update
	iwp_bookmark_out_parameters
set
	parameter_desc = 'Request and Grievances Issue Transaction Status for the Selected Grid Record',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
where
	parameter_name = 'ISS_RGTSTS';

update
	iwp_bookmark_out_parameters
set
	parameter_desc = 'Request and Grievances Issue Transaction Proposed Response and Comment for the Selected Grid Record',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
where
	parameter_name = 'ISS_RGTPRCOM';

update
	iwp_bookmark_out_parameters
set
	parameter_desc = 'Request and Grievances Issue Transaction Official Response for the Selected Grid Record',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
where
	parameter_name = 'ISS_RGTOFRSP';

update
	iwp_bookmark_out_parameters
set
	parameter_desc = 'Request and Grievances Issue Staff Involved - Staff ID for the Selected Grid Record',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
where
	parameter_name = 'ISS_RGSINVID';

update
	iwp_bookmark_out_parameters
set
	parameter_desc = 'Request and Grievances Issue Staff Involved - Staff Name (F, L MI) for the Selected Grid Record',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
where
	parameter_name = 'ISS_RGSINSNM';

update
	iwp_bookmark_out_parameters
set
	parameter_desc = 'Height (Feet)',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
where
	parameter_name = 'FEET';

update
	iwp_bookmark_out_parameters
set
	parameter_desc = 'Height (Inches)',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
where
	parameter_name = 'INCHES';

update
	iwp_bookmark_out_parameters
set
	parameter_desc = 'Height (Centimeters)',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
where
	parameter_name = 'CENTIMETRES';

update
	iwp_bookmark_out_parameters
set
	parameter_desc = 'Weight (Pounds)',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
where
	parameter_name = 'POUNDS';

update
	iwp_bookmark_out_parameters
set
	parameter_desc = 'Weight (Pounds)',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
where
	parameter_name = 'KILOGRAMS';

update
	iwp_bookmark_out_parameters
set
	parameter_desc = 'Race',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
where
	parameter_name = 'RACE';

update
	iwp_bookmark_out_parameters
set
	parameter_desc = 'Type',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
where
	parameter_name = 'TYPE';

update
	iwp_bookmark_out_parameters
set
	parameter_desc = 'Side',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
where
	parameter_name = 'SIDE';

update
	iwp_bookmark_out_parameters
set
	parameter_desc = 'Body Part',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
where
	parameter_name = 'BODY_PART';

update
	iwp_bookmark_out_parameters
set
	parameter_desc = 'Comment',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
where
	parameter_name = 'COMMENT';

update
	iwp_bookmark_out_parameters
set
	parameter_desc = 'Orientation',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
where
	parameter_name = 'ORIENTATION';

update
	iwp_bookmark_out_parameters
set
	parameter_desc = 'Characteristic Description',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
where
	parameter_name = 'DESCRIPTION';

update
	iwp_bookmark_out_parameters
set
	parameter_desc = 'Characterisitic',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
where
	parameter_name = 'PROFILE_CODE';

update
	iwp_bookmark_out_parameters
set
	parameter_desc = 'Property Item Type with a Status of Registered',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
where
	parameter_name = 'PROP_ITMTYPR';

update
	iwp_bookmark_out_parameters
set
	parameter_desc = 'Property Item Description with a Status of Registered',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
where
	parameter_name = 'PROP_ITMDESR';

update
	iwp_bookmark_out_parameters
set
	parameter_desc = 'Property Item Quantity with a Status of Registered',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
where
	parameter_name = 'PROP_ITMQTYR';

update
	iwp_bookmark_out_parameters
set
	parameter_desc = 'Property Item Received By (e.g., Prisoner, Police, etc.) with a Status of Registered',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
where
	parameter_name = 'PROP_ITMRECR';

update
	iwp_bookmark_out_parameters
set
	parameter_desc = 'Property Item Color with a Status of Registered',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
where
	parameter_name = 'PROP_ITMCLRR';

update
	iwp_bookmark_out_parameters
set
	parameter_desc = 'Property Item Condition with a Status of Registered',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
where
	parameter_name = 'PROP_ITMCONR';

update
	iwp_bookmark_out_parameters
set
	parameter_desc = 'Property Item Make with a Status of Registered',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
where
	parameter_name = 'PROP_ITMMKR';

update
	iwp_bookmark_out_parameters
set
	parameter_desc = 'Property Item Serial Number with a Status of Registered',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
where
	parameter_name = 'PROP_ITMSNR';

update
	iwp_bookmark_out_parameters
set
	parameter_desc = 'Property Item image with a Status of Registered',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
where
	parameter_name = 'PROP_ITMIMGR';

update
	iwp_bookmark_out_parameters
set
	parameter_desc = 'Property Item Type Stored in a Bulk (BULK) Container',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
where
	parameter_name = 'PROP_ITMTYPB';

update
	iwp_bookmark_out_parameters
set
	parameter_desc = 'Property Item Description Stored in a Bulk (BULK) Container',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
where
	parameter_name = 'PROP_ITMDESB';

update
	iwp_bookmark_out_parameters
set
	parameter_desc = 'Property Item Quantity Stored in a Bulk (BULK) Container',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
where
	parameter_name = 'PROP_ITMQTYB';

update
	iwp_bookmark_out_parameters
set
	parameter_desc = 'Property Item Received By (e.g., Prisoner, Police, etc.) Stored in a Bulk (BULK) Container',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
where
	parameter_name = 'PROP_ITMRECB';

update
	iwp_bookmark_out_parameters
set
	parameter_desc = 'Property Item Color Stored in a Bulk (BULK) Container',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
where
	parameter_name = 'PROP_ITMCLRB';

update
	iwp_bookmark_out_parameters
set
	parameter_desc = 'Property Item Condition Stored in a Bulk (BULK) Container',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
where
	parameter_name = 'PROP_ITMCONB';

update
	iwp_bookmark_out_parameters
set
	parameter_desc = 'Property Item Make Stored in a Bulk (BULK) Container',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
where
	parameter_name = 'PROP_ITMMKB';

update
	iwp_bookmark_out_parameters
set
	parameter_desc = 'Property Item Serial Number Stored in a Bulk (BULK) Container',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
where
	parameter_name = 'PROP_ITMSNB';

update
	iwp_bookmark_out_parameters
set
	parameter_desc = 'Property Item Type Stored in a Confiscated (CO) Container',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
where
	parameter_name = 'PROP_ITMTYPC';

update
	iwp_bookmark_out_parameters
set
	parameter_desc = 'Property Item Description Stored in a Confiscated (CO) Container',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
where
	parameter_name = 'PROP_ITMDESC';

update
	iwp_bookmark_out_parameters
set
	parameter_desc = 'Property Item Quantity Stored in a Confiscated (CO) Container',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
where
	parameter_name = 'PROP_ITMQTYC';

update
	iwp_bookmark_out_parameters
set
	parameter_desc = 'Property Item Received By (e.g., Prisoner, Police, etc.) Stored in a Confiscated (CO) Container',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
where
	parameter_name = 'PROP_ITMRECC';

update
	iwp_bookmark_out_parameters
set
	parameter_desc = 'Property Item Color Stored in a Confiscated (CO) Container',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
where
	parameter_name = 'PROP_ITMCLRC';

update
	iwp_bookmark_out_parameters
set
	parameter_desc = 'Property Item Condition Stored in a Confiscated (CO) Container',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
where
	parameter_name = 'PROP_ITMCONC';

update
	iwp_bookmark_out_parameters
set
	parameter_desc = 'Property Item Make Stored in a Confiscated (CO) Container',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
where
	parameter_name = 'PROP_ITMMKC';

update
	iwp_bookmark_out_parameters
set
	parameter_desc = 'Property Item Serial Number Stored in a Confiscated (CO) Container',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
where
	parameter_name = 'PROP_ITMSNC';

update
	iwp_bookmark_out_parameters
set
	parameter_desc = 'Property Item Type Stored in a For Destruction (DES) Container',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
where
	parameter_name = 'PROP_ITMTYPD';

update
	iwp_bookmark_out_parameters
set
	parameter_desc = 'Property Item Description Stored in a For Destruction (DES) Container',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
where
	parameter_name = 'PROP_ITMDESD';

update
	iwp_bookmark_out_parameters
set
	parameter_desc = 'Property Item Quantity Stored in a For Destruction (DES) Container',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
where
	parameter_name = 'PROP_ITMQTYD';

update
	iwp_bookmark_out_parameters
set
	parameter_desc = 'Property Item Received By (e.g., Prisoner, Police, etc.) Stored in a For Destruction (DES) Container',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
where
	parameter_name = 'PROP_ITMRECD';

update
	iwp_bookmark_out_parameters
set
	parameter_desc = 'Property Item Color Stored in a For Destruction (DES) Container',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
where
	parameter_name = 'PROP_ITMCLRD';

update
	iwp_bookmark_out_parameters
set
	parameter_desc = 'Property Item Condition Stored in a For Destruction (DES) Container',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
where
	parameter_name = 'PROP_ITMCOND';

update
	iwp_bookmark_out_parameters
set
	parameter_desc = 'Property Item Make Stored in a For Destruction (DES) Container',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
where
	parameter_name = 'PROP_ITMMKD';

update
	iwp_bookmark_out_parameters
set
	parameter_desc = 'Property Item Serial Number Stored in a For Destruction (DES) Container',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
where
	parameter_name = 'PROP_ITMSND';

update
	iwp_bookmark_out_parameters
set
	parameter_desc = 'Property Item Type Stored in a Prescription (SRIPTS) Container',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
where
	parameter_name = 'PROP_ITMTYPS';

update
	iwp_bookmark_out_parameters
set
	parameter_desc = 'Property Item Description Stored in a Prescription (SRIPTS) Container',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
where
	parameter_name = 'PROP_ITMDESS';

update
	iwp_bookmark_out_parameters
set
	parameter_desc = 'Property Item Quantity Stored in a Prescription (SRIPTS) Container',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
where
	parameter_name = 'PROP_ITMQTYS';

update
	iwp_bookmark_out_parameters
set
	parameter_desc = 'Property Item Received By (e.g., Prisoner, Police, etc.) Stored in a Prescription (SRIPTS) Container',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
where
	parameter_name = 'PROP_ITMRECS';

update
	iwp_bookmark_out_parameters
set
	parameter_desc = 'Property Item Color Stored in a Prescription (SRIPTS) Container',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
where
	parameter_name = 'PROP_ITMCLRS';

update
	iwp_bookmark_out_parameters
set
	parameter_desc = 'Property Item Condition Stored in a Prescription (SRIPTS) Container',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
where
	parameter_name = 'PROP_ITMCONS';

update
	iwp_bookmark_out_parameters
set
	parameter_desc = 'Property Item Make Stored in a Prescription (SRIPTS) Container',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
where
	parameter_name = 'PROP_ITMMKS';

update
	iwp_bookmark_out_parameters
set
	parameter_desc = 'Property Item Serial Number Stored in a Prescription (SRIPTS) Container',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
where
	parameter_name = 'PROP_ITMSNS';

update
	iwp_bookmark_out_parameters
set
	parameter_desc = 'Property Item Type Stored in a Valuable (VALU) Container',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
where
	parameter_name = 'PROP_ITMTYPV';

update
	iwp_bookmark_out_parameters
set
	parameter_desc = 'Property Item Description Stored in a Valuable (VALU) Container',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
where
	parameter_name = 'PROP_ITMDESV';

update
	iwp_bookmark_out_parameters
set
	parameter_desc = 'Property Item Quantity Stored in a Valuable (VALU) Container',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
where
	parameter_name = 'PROP_ITMQTYV';

update
	iwp_bookmark_out_parameters
set
	parameter_desc = 'Property Item Received By (e.g., Prisoner, Police, etc.) Stored in a Valuable (VALU) Container',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
where
	parameter_name = 'PROP_ITMRECV';

update
	iwp_bookmark_out_parameters
set
	parameter_desc = 'Property Item Color Stored in a Valuable (VALU) Container',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
where
	parameter_name = 'PROP_ITMCLRV';

update
	iwp_bookmark_out_parameters
set
	parameter_desc = 'Property Item Condition Stored in a Valuable (VALU) Container',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
where
	parameter_name = 'PROP_ITMCONV';

update
	iwp_bookmark_out_parameters
set
	parameter_desc = 'Property Item Make Stored in a Valuable (VALU) Container',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
where
	parameter_name = 'PROP_ITMMKV';

update
	iwp_bookmark_out_parameters
set
	parameter_desc = 'Property Item Serial Number Stored in a Valuable (VALU) Container',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
where
	parameter_name = 'PROP_ITMSNV';

update
	iwp_bookmark_out_parameters
set
	parameter_desc = 'Property Item Image Stored in a Bulk (BULK) Container ',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
where
	parameter_name = 'PROP_ITMIMGB';

update
	iwp_bookmark_out_parameters
set
	parameter_desc = 'Property Item Image Stored in a Confiscated (CO) Container ',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
where
	parameter_name = 'PROP_ITMIMGC';

update
	iwp_bookmark_out_parameters
set
	parameter_desc = 'Property Item Image Stored in a For Destruction (DES) Container ',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
where
	parameter_name = 'PROP_ITMIMGB';

update
	iwp_bookmark_out_parameters
set
	parameter_desc = 'Property Item Image Stored in a Prescription (SCRIPT) Container ',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
where
	parameter_name = 'PROP_ITMIMGB';

update
	iwp_bookmark_out_parameters
set
	parameter_desc = 'Property Item Image Stored in a Valuables (VALU) Container ',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
where
	parameter_name = 'PROP_ITMIMGB';

update
	iwp_bookmark_out_parameters
set
	parameter_desc = 'Schedule TAP Application Date for Selected Grid Record (MM-DD-YYYY)',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
where
	parameter_name = 'SCH_TAPADTE1';

update
	iwp_bookmark_out_parameters
set
	parameter_desc = 'Schedule TAP Application Date for Selected Grid Record (DD-MM-YYYY)',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
where
	parameter_name = 'SCH_TAPADTE2';

update
	iwp_bookmark_out_parameters
set
	parameter_desc = 'Schedule TAP Application Date for Selected Grid Record (YYYY-MM-DD)',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
where
	parameter_name = 'SCH_TAPADTE3';

update
	iwp_bookmark_out_parameters
set
	parameter_desc = 'Scheduled TAP Application Time for Selected Grid Record (HH24:MI)',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
where
	parameter_name = 'SCH_TAPAPTME';

update
	iwp_bookmark_out_parameters
set
	parameter_desc = 'Schedule TAP Release Date for Selected Grid Record (MM-DD-YYYY)',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
where
	parameter_name = 'SCH_TAPRLDT1';

update
	iwp_bookmark_out_parameters
set
	parameter_desc = 'Schedule TAP Release Date for Selected Grid Record (DD-MM-YYYY)',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
where
	parameter_name = 'SCH_TAPRLDT2';

update
	iwp_bookmark_out_parameters
set
	parameter_desc = 'Schedule TAP Release Date for Selected Grid Record (YYYY-MM-DD)',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
where
	parameter_name = 'SCH_TAPRLDT3';

update
	iwp_bookmark_out_parameters
set
	parameter_desc = 'Scheduled TAP Release Time for Selected Grid Record (HH24:MI)',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
where
	parameter_name = 'SCH_TAPRLTME';

update
	iwp_bookmark_out_parameters
set
	parameter_desc = 'Schedule TAP Return Date for Selected Grid Record (MM-DD-YYYY)',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
where
	parameter_name = 'SCH_TAPRDTE1';

update
	iwp_bookmark_out_parameters
set
	parameter_desc = 'Schedule TAP Return Date for Selected Grid Record (DD-MM-YYYY)',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
where
	parameter_name = 'SCH_TAPRDTE2';

update
	iwp_bookmark_out_parameters
set
	parameter_desc = 'Schedule TAP Return Date for Selected Grid Record (YYYY-MM-DD)',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
where
	parameter_name = 'SCH_TAPRDTE3';

update
	iwp_bookmark_out_parameters
set
	parameter_desc = 'Scheduled TAP Return Time for Selected Grid Record (HH24:MI)',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
where
	parameter_name = 'SCH_TAPRTTME';

update
	iwp_bookmark_out_parameters
set
	parameter_desc = 'Scheduled TAP Calculated Days Out for Selected Grid Record',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
where
	parameter_name = 'SCH_TAPDYOUT';

update
	iwp_bookmark_out_parameters
set
	parameter_desc = 'Scheduled TAP Calculated Hours Out for Selected Grid Record',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
where
	parameter_name = 'SCH_TAPHROUT';

update
	iwp_bookmark_out_parameters
set
	parameter_desc = 'Schedule TAP Reason for Selected Grid Record ',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
where
	parameter_name = 'SCH_TAPRSN';

update
	iwp_bookmark_out_parameters
set
	parameter_desc = 'Schedule TAP Escort for Selected Grid Record',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
where
	parameter_name = 'SCG_TAPESCT';

update
	iwp_bookmark_out_parameters
set
	parameter_desc = 'Schedule TAP Transport for Selected Grid Record',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
where
	parameter_name = 'SCH_TAPTSP';

update
	iwp_bookmark_out_parameters
set
	parameter_desc = 'Schedule TAP Status for Selected Grid Record',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
where
	parameter_name = 'SCH_TAPSTAT';

update
	iwp_bookmark_out_parameters
set
	parameter_desc = 'Schedule TAP Comment for the Selected Grid Record',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
where
	parameter_name = 'SCH_TAPCOMNT';

update
	iwp_bookmark_out_parameters
set
	parameter_desc = 'Schedule TAP Destination Name',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
where
	parameter_name = 'SCH_TAPDSTNM';

update
	iwp_bookmark_out_parameters
set
	parameter_desc = 'Schedule TAP Destination Contact Person',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
where
	parameter_name = 'SCH_TAPDSTCP';

update
	iwp_bookmark_out_parameters
set
	parameter_desc = 'Schedule TAP Destination Suite Number',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
where
	parameter_name = 'SCH_TAPDSSTN';

update
	iwp_bookmark_out_parameters
set
	parameter_desc = 'Schedule TAP Destination Street Information',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
where
	parameter_name = 'SCH_TAPDSSTR';

update
	iwp_bookmark_out_parameters
set
	parameter_desc = 'Schedule TAP Destination City or Town',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
where
	parameter_name = 'SCH_TAPDSCTY';

update
	iwp_bookmark_out_parameters
set
	parameter_desc = 'Schedule TAP Destination State, Province, Territory',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
where
	parameter_name = 'SCH_TAPDSST';

update
	iwp_bookmark_out_parameters
set
	parameter_desc = 'Schedule TAP Destination Postal Code',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
where
	parameter_name = 'SCH_TAPDSPC';

update
	iwp_bookmark_out_parameters
set
	parameter_desc = 'Schedule TAP Destination Country',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
where
	parameter_name = 'SCH_TAPDSCCO';

update
	iwp_bookmark_out_parameters
set
	parameter_desc = 'Schedule TAP Destination Phone Type(s)',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
where
	parameter_name = 'SCH_TAPDSPHT';

update
	iwp_bookmark_out_parameters
set
	parameter_desc = 'Schedule TAP Destination Phone Number(s)',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
where
	parameter_name = 'SCH_TAPDSPHN';

update
	iwp_bookmark_out_parameters
set
	parameter_desc = 'Schedule TAP Destination Phone Extension(s)',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
where
	parameter_name = 'SCH_TAPDSPXT';

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
	'OBS_OOPOBID',
	'OFF_OBS_PERI',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	null,
	'Observation ID from the Offender Observation Periods Card/Block'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'OFF_OBS_PERI'
		and parameter_name = 'OBS_OOPOBID');

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
	'OBS_OOPOTYP',
	'OFF_OBS_PERI',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	null,
	'Observation Type from the Offender Observation Periods Card/Block'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'OFF_OBS_PERI'
		and parameter_name = 'OBS_OOPOTYP');

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
	'OBS_OOPFREQ',
	'OFF_OBS_PERI',
	current_timestamp,
	'OMS_OWNER',
	null,
	'OMS_OWNER',
	null,
	'Freq (frequency) from the Offender Observation Periods Card/Block'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'OFF_OBS_PERI'
		and parameter_name = 'OBS_OOPFREQ');

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
	'OBS_OOPNTFY',
	'OFF_OBS_PERI',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	null,
	'Notify from the Offender Observation Periods Card/Block'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'OFF_OBS_PERI'
		and parameter_name = 'OBS_OOPNTFY');

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
	'OBS_OOPNBUF',
	'OFF_OBS_PERI',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	null,
	'Notification Buffer from the Offender Observation Periods Card/Block'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'OFF_OBS_PERI'
		and parameter_name = 'OBS_OOPNBUF');

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
	'OBS_OOPSDTE',
	'OFF_OBS_PERI',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	null,
	'Start Date from the Offender Observation Periods Card/Block'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'OFF_OBS_PERI'
		and parameter_name = 'OBS_OOPSDTE');

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
	'OBS_OOPSTME',
	'OFF_OBS_PERI',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	null,
	'Start Time from the Offender Observation Periods Card/Block'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'OFF_OBS_PERI'
		and parameter_name = 'OBS_OOPSTME');

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
	'OBS_OOPERSN',
	'OFF_OBS_PERI',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	null,
	'End Reason from the Offender Observation Periods Card/Block'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'OFF_OBS_PERI'
		and parameter_name = 'OBS_OOPERSN');

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
	'OBS_OOPEDTE',
	'OFF_OBS_PERI',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	null,
	'End Date from the Offender Observation Periods Card/Block'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'OFF_OBS_PERI'
		and parameter_name = 'OBS_OOPEDTE');

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
	'OBS_OOPETME',
	'OFF_OBS_PERI',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	null,
	'End Time from the Offender Observation Periods Card/Block'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'OFF_OBS_PERI'
		and parameter_name = 'OBS_OOPETME');

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
	'OBS_OOPSTAT',
	'OFF_OBS_PERI',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	null,
	'Status from the Offender Observation Periods Card/Block'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'OFF_OBS_PERI'
		and parameter_name = 'OBS_OOPSTAT');

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
	'OBS_OOPCID',
	'OFF_OBS_CHEC',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	null,
	'Check ID # from the child Card/Block based on the record selected in the Offender Observation Periods Card/Block'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'OFF_OBS_CHEC'
		and parameter_name = 'OBS_OOPCID');

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
	'OBS_OOPCSDTE',
	'OFF_OBS_CHEC',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	null,
	'Scheduled Date from the child Card/Block based on the record selected in the Offender Observation Periods Card/Block'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'OFF_OBS_CHEC'
		and parameter_name = 'OBS_OOPCSDTE');

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
	'OBS_OOPCSTME',
	'OFF_OBS_CHEC',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	null,
	'Scheduled Time from the child Card/Block based on the record selected in the Offender Observation Periods Card/Block'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'OFF_OBS_CHEC'
		and parameter_name = 'OBS_OOPCSTME');

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
	'OBS_OOPCFREQ',
	'OFF_OBS_CHEC',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	null,
	'Freq (frequency) from the child Card/Block based on the record selected in the Offender Observation Periods Card/Block'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'OFF_OBS_CHEC'
		and parameter_name = 'OBS_OOPCFREQ');

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
	'OBS_OOPCDTE',
	'OFF_OBS_CHEC',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	null,
	'Check Date from the child Card/Block based on the record selected in the Offender Observation Periods Card/Block'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'OFF_OBS_CHEC'
		and parameter_name = 'OBS_OOPCDTE');

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
	'OBS_OOPCTME',
	'OFF_OBS_CHEC',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	null,
	'Check Time from the child Card/Block based on the record selected in the Offender Observation Periods Card/Block'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'OFF_OBS_CHEC'
		and parameter_name = 'OBS_OOPCTME');

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
	'OBS_OOPCVAR',
	'OFF_OBS_CHEC',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	null,
	'Variance from the child Card/Block based on the record selected in the Offender Observation Periods Card/Block'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'OFF_OBS_CHEC'
		and parameter_name = 'OBS_OOPCVAR');

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
	'OBS_OOPCSTAF',
	'OFF_OBS_CHEC',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	null,
	'Staff Name from the child Card/Block based on the record selected in the Offender Observation Periods Card/Block'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'OFF_OBS_CHEC'
		and parameter_name = 'OBS_OOPCSTAF');

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
	'VIS_CVTSVTYP',
	'OFFEN_VISITS',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	null,
	'Visit Type field from the Create or View Existing Time Slot Card/Block'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'OFFEN_VISITS'
		and parameter_name = 'VIS_CVTSVTYP');

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
	'VIS_CVTSLOCA',
	'OFFEN_VISITS',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	null,
	'Location field from the Create or View Existing Time Slot Card/Block'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'OFFEN_VISITS'
		and parameter_name = 'VIS_CVTSLOCA');

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
	'VIS_CVTSCRSN',
	'OFFEN_VISITS',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	null,
	'Cancel Reason field from the Create or View Existing Time Slot Card/Block'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'OFFEN_VISITS'
		and parameter_name = 'VIS_CVTSCRSN');

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
	'VIS_CVTSCOMP',
	'OFFEN_VISITS',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	null,
	'Completion field from the Create or View Existing Time Slot Card/Block'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'OFFEN_VISITS'
		and parameter_name = 'VIS_CVTSCOMP');

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
	'VIS_CVTSATND',
	'OFFEN_VISITS',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	null,
	'Attended checkbox field from the Create or View Existing Time Slot Card/Block'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'OFFEN_VISITS'
		and parameter_name = 'VIS_CVTSATND');

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
	'VIS_CVTSCMNT',
	'OFFEN_VISITS',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	null,
	'Comment field from the Create or View Existing Time Slot Card/Block'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'OFFEN_VISITS'
		and parameter_name = 'VIS_CVTSCMNT');

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
	'VIS_CVTSDTE',
	'OFFEN_VISITS',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	null,
	'Date field from the Create or View Existing Time Slot Card/Block'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'OFFEN_VISITS'
		and parameter_name = 'VIS_CVTSDTE');

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
	'VIS_CVTSSLOT',
	'OFFEN_VISITS',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	null,
	'Time Slot field from the Create or View Existing Time Slot Card/Block'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'OFFEN_VISITS'
		and parameter_name = 'VIS_CVTSSLOT');

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
	'VIS_CVTSSTRT',
	'OFFEN_VISITS',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	null,
	'Start time field from the Create or View Existing Time Slot Card/Block'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'OFFEN_VISITS'
		and parameter_name = 'VIS_CVTSSTRT');

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
	'VIS_CVTSEND',
	'OFFEN_VISITS',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	null,
	'End time field from the Create or View Existing Time Slot Card/Block'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'OFFEN_VISITS'
		and parameter_name = 'VIS_CVTSEND');

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
	'VIS_CVTSVLN',
	'OFFN_VISTORS',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	null,
	'Last Name from the Visitors Tab based on the record selected in the Create or View Existing Time Slot Card/Block'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'OFFN_VISTORS'
		and parameter_name = 'VIS_CVTSVLN');

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
	'VIS_CVTSVFN',
	'OFFN_VISTORS',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	null,
	'First Name from the Visitors Tab based on the record selected in the Create or View Existing Time Slot Card/Block'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'OFFN_VISTORS'
		and parameter_name = 'VIS_CVTSVFN');

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
	'VIS_CVTSVCT',
	'OFFN_VISTORS',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	null,
	'Contact Type from the Visitors Tab based on the record selected in the Create or View Existing Time Slot Card/Block'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'OFFN_VISTORS'
		and parameter_name = 'VIS_CVTSVCT');

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
	'VIS_CVTSVREL',
	'OFFN_VISTORS',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	null,
	'Relationship from the Visitors Tab based on the record selected in the Create or View Existing Time Slot Card/Block'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'OFFN_VISTORS'
		and parameter_name = 'VIS_CVTSVREL');

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
	'VIS_CVTSVAGE',
	'OFFN_VISTORS',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	null,
	'Age from the Visitors Tab based on the record selected in the Create or View Existing Time Slot Card/Block'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'OFFN_VISTORS'
		and parameter_name = 'VIS_CVTSVAGE');

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
	'VIS_CVTSVRES',
	'OFFN_VISTORS',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	null,
	'Restriction from the Visitors Tab based on the record selected in the Create or View Existing Time Slot Card/Block'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'OFFN_VISTORS'
		and parameter_name = 'VIS_CVTSVRES');

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
	'VIS_CVTSVBAN',
	'OFFN_VISTORS',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	null,
	'Visit Ban from the Visitors Tab based on the record selected in the Create or View Existing Time Slot Card/Block'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'OFFN_VISTORS'
		and parameter_name = 'VIS_CVTSVBAN');

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
	'VIS_CVTSVATD',
	'OFFN_VISTORS',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	null,
	'Attended from the Visitors Tab based on the record selected in the Create or View Existing Time Slot Card/Block'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'OFFN_VISTORS'
		and parameter_name = 'VIS_CVTSVATD');

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
	'VIS_CVTSVCOM',
	'OFFN_VISTORS',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	null,
	'Comment from the Visitors Tab based on the record selected in the Create or View Existing Time Slot Card/Block'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'OFFN_VISTORS'
		and parameter_name = 'VIS_CVTSVCOM');

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
	'VIS_CVTSVUID',
	'OFFN_VISTORS',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	null,
	'Visitor ID from the Visitors Tab based on the record selected in the Create or View Existing Time Slot Card/Block'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'OFFN_VISTORS'
		and parameter_name = 'VIS_CVTSVUID');

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
	'VIS_CVTSVOID',
	'VISIT_OFFEND',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	null,
	'PID# from the Visiting Offenders Tab based on the record selected in the Create or View Existing Time Slot Card/Block'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'VISIT_OFFEND'
		and parameter_name = 'VIS_CVTSVOID');

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
	'VIS_CVTSVOLN',
	'VISIT_OFFEND',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	null,
	'Last Name from the Visiting Offenders Tab based on the record selected in the Create or View Existing Time Slot Card/Block'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'VISIT_OFFEND'
		and parameter_name = 'VIS_CVTSVOLN');

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
	'VIS_CVTSVOFN',
	'VISIT_OFFEND',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	null,
	'First Name from the Visiting Offenders Tab based on the record selected in the Create or View Existing Time Slot Card/Block'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'VISIT_OFFEND'
		and parameter_name = 'VIS_CVTSVOFN');

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
	'VIS_CVTSVOFA',
	'VISIT_OFFEND',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	null,
	'Facility from the Visiting Offenders Tab based on the record selected in the Create or View Existing Time Slot Card/Block'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'VISIT_OFFEND'
		and parameter_name = 'VIS_CVTSVOFA');

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
	'VIS_CVTSVOCT',
	'VISIT_OFFEND',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	null,
	'Contact Type from the Visiting Offenders Tab based on the record selected in the Create or View Existing Time Slot Card/Block'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'VISIT_OFFEND'
		and parameter_name = 'VIS_CVTSVOCT');

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
	'VIS_CVTSVORL',
	'VISIT_OFFEND',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	null,
	'Relationship from the Visiting Offenders Tab based on the record selected in the Create or View Existing Time Slot Card/Block'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'VISIT_OFFEND'
		and parameter_name = 'VIS_CVTSVORL');

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
	'VIS_CVTSVORS',
	'VISIT_OFFEND',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	null,
	'Restriction from the Visiting Offenders Tab based on the record selected in the Create or View Existing Time Slot Card/Block'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'VISIT_OFFEND'
		and parameter_name = 'VIS_CVTSVORS');

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
	'VIS_CVTSVOAT',
	'VISIT_OFFEND',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	null,
	'Attended from the Visiting Offenders Tab based on the record selected in the Create or View Existing Time Slot Card/Block'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'VISIT_OFFEND'
		and parameter_name = 'VIS_CVTSVOAT');

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
	'CMG_RPPDCMRA',
	'RELEASE_PLAN',
	current_timestamp,
	'OMS_OWNER',
	null ,
	null,
	null,
	'Community Risk Assessment from the Plan Details Tab based on the record selected in the top Card/Block'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'RELEASE_PLAN'
		and parameter_name = 'CMG_RPPDCMRA');

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
	'CMG_RPPDADTE',
	'RELEASE_PLAN',
	current_timestamp,
	'OMS_OWNER',
	null ,
	null,
	null,
	'Assessment Date from the Plan Details Tab based on the record selected in the top Card/Block'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'RELEASE_PLAN'
		and parameter_name = 'CMG_RPPDADTE');

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
	'CMG_RPPDPHOU',
	'RELEASE_PLAN',
	current_timestamp,
	'OMS_OWNER',
	null ,
	null,
	null,
	'Proposed Housing from the Plan Details Tab based on the record selected in the top Card/Block'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'RELEASE_PLAN'
		and parameter_name = 'CMG_RPPDPHOU');

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
	'CMG_RPPDADTY',
	'RELEASE_PLAN',
	current_timestamp,
	'OMS_OWNER',
	null ,
	null,
	null,
	'Address Type from the Plan Details Tab based on the record selected in the top Card/Block'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'RELEASE_PLAN'
		and parameter_name = 'CMG_RPPDADTY');

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
	'CMG_RPPDPEMP',
	'RELEASE_PLAN',
	current_timestamp,
	'OMS_OWNER',
	null ,
	null,
	null,
	'Proposed Employment from the Plan Details Tab based on the record selected in the top Card/Block'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'RELEASE_PLAN'
		and parameter_name = 'CMG_RPPDPEMP');

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
	'CMG_RPPDAREC',
	'RELEASE_PLAN',
	current_timestamp,
	'OMS_OWNER',
	null ,
	null,
	null,
	'Agent Recommendation from the Plan Details Tab based on the record selected in the top Card/Block'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'RELEASE_PLAN'
		and parameter_name = 'CMG_RPPDAREC');

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
	'CMG_RPCRDTE',
	'RELEASE_PLAN',
	current_timestamp,
	'OMS_OWNER',
	null ,
	null,
	null,
	'Create Date from the top Card/Block'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'RELEASE_PLAN'
		and parameter_name = 'CMG_RPCRDTE');

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
	'CMG_RPCAMNGR',
	'RELEASE_PLAN',
	current_timestamp,
	'OMS_OWNER',
	null ,
	null,
	null,
	'Case Manager from the top Card/Block'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'RELEASE_PLAN'
		and parameter_name = 'CMG_RPCAMNGR');

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
	'CMG_RPPAAGNT',
	'RELEASE_PLAN',
	current_timestamp,
	'OMS_OWNER',
	null ,
	null,
	null,
	'Parole Agent from the top Card/Block'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'RELEASE_PLAN'
		and parameter_name = 'CMG_RPPAAGNT');

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
	'CMG_RPPLSTS',
	'RELEASE_PLAN',
	current_timestamp,
	'OMS_OWNER',
	null ,
	null,
	null,
	'Plan Status from the top Card/Block'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'RELEASE_PLAN'
		and parameter_name = 'CMG_RPPLSTS');

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
	'CMG_RPUPDTE',
	'RELEASE_PLAN',
	current_timestamp,
	'OMS_OWNER',
	null ,
	null,
	null,
	'Last Updated date from the top Card/Block'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'RELEASE_PLAN'
		and parameter_name = 'CMG_RPUPDTE');

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
	'CMG_RPUPDBY',
	'RELEASE_PLAN',
	current_timestamp,
	'OMS_OWNER',
	null ,
	null,
	null,
	'Updated By from the top Card/Block'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'RELEASE_PLAN'
		and parameter_name = 'CMG_RPUPDBY');

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
	'CMG_RPPDRCOM',
	'RELEASE_PLAN',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	null,
	'Comments pertaining to risk assessment from the Plan Details Tab based on the record selected in the top Card/Block'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'RELEASE_PLAN'
		and parameter_name = 'CMG_RPPDRCOM');

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
	'CMG_RPPDEMST',
	'RELEASE_PLAN',
	current_timestamp,
	'OMS_OWNER',
	null ,
	null,
	null,
	'Employment Status from the Plan Details Tab based on the record selected in the top Card/Block'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'RELEASE_PLAN'
		and parameter_name = 'CMG_RPPDEMST');

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
	'CMG_RPPDECOM',
	'RELEASE_PLAN',
	current_timestamp,
	'OMS_OWNER',
	null ,
	null,
	null,
	'Comments pertaining to employment from the Plan Details Tab based on the record selected in the top Card/Block'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'RELEASE_PLAN'
		and parameter_name = 'CMG_RPPDECOM');

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
	'CMG_RPPDCOND',
	'RELEASE_PLAN',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	null,
	'Conditions from the Plan Details Tab based on the record selected in the top Card/Block'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'RELEASE_PLAN'
		and parameter_name = 'CMG_RPPDCOND');

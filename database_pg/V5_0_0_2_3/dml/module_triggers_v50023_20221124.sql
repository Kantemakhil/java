insert
	into
	MODULE_TRIGGERS (MODULE_NAME,
	DTO_NAME,
	MODULE_DESCRIPTION,
	TRIGGER_NAME,
	TRIGGER_ID,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag)
values('OCONDAWAIT',
'net.syscon.s4.inst.legals.beans.OffenderCondTransfer',
'Conditions Awaiting Assignment',
'Conditions',
'85',
CURRENT_TIMESTAMP,
'OMS_OWNER',
CURRENT_TIMESTAMP,
NULL,
NULL);

insert
	into
	MODULE_TRIGGERS (MODULE_NAME,
	DTO_NAME,
	MODULE_DESCRIPTION,
	TRIGGER_NAME,
	TRIGGER_ID,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag)
values('OCONDTRF',
'net.syscon.s4.inst.legals.beans.OffenderCondTransfer',
'Transfer Conditions',
'Conditions',
'86',
CURRENT_TIMESTAMP,
'OMS_OWNER',
CURRENT_TIMESTAMP,
NULL,
NULL);

update
	MODULE_TRIGGERS
set  modify_datetime = current_timestamp
    ,modify_user_id = 'OMS_OWNER' 
    ,MODULE_NAME = 'OCDCSCH',
	MODULE_DESCRIPTION = 'Scheduler'
where
	TRIGGER_ID = '21';

insert
	into
	MODULE_TRIGGERS (MODULE_NAME,
	DTO_NAME,
	MODULE_DESCRIPTION,
	TRIGGER_ID,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag)
values('OCDALTCC',
'net.syscon.s4.im.beans.OffenderBookingAgyLocs',
'Add Location To Current Contact',
'89',
CURRENT_TIMESTAMP,
'OMS_OWNER',
CURRENT_TIMESTAMP,
NULL,
NULL);

insert
	into
	MODULE_TRIGGERS (MODULE_NAME,
	DTO_NAME,
	MODULE_DESCRIPTION,
	TRIGGER_ID,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag)
values('OIDCRTEV',
'net.syscon.s4.inst.schedules.bean.CourtEvents',
'Court Events',
'90',
CURRENT_TIMESTAMP,
'OMS_OWNER',
CURRENT_TIMESTAMP,
NULL,
NULL);

insert
	into
	MODULE_TRIGGERS (MODULE_NAME,
	DTO_NAME,
	MODULE_DESCRIPTION,
	TRIGGER_NAME,
	TRIGGER_ID,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag)
values('OCDADDRE',
'net.syscon.s4.im.beans.Addresses',
'Offender Addresses',
'Add New Address',
'91',
CURRENT_TIMESTAMP,
'OMS_OWNER',
CURRENT_TIMESTAMP,
NULL,
NULL);

insert
	into
	MODULE_TRIGGERS (MODULE_NAME,
	DTO_NAME,
	MODULE_DESCRIPTION,
	TRIGGER_NAME,
	TRIGGER_ID,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag)
values('OIDONONA',
'net.syscon.s4.im.beans.OffenderNonAssociations',
'Offender Non-Associations',
'Non-Associations',
'92',
CURRENT_TIMESTAMP,
'OMS_OWNER',
CURRENT_TIMESTAMP,
NULL,
NULL);

insert
	into
	MODULE_TRIGGERS (MODULE_NAME,
	DTO_NAME,
	MODULE_DESCRIPTION,
	TRIGGER_ID,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag)
values('OCDXPROG',
'net.syscon.s4.im.beans.OffenderPrgObligations',
'Offender Specialized Programs',
'93',
CURRENT_TIMESTAMP,
'OMS_OWNER',
CURRENT_TIMESTAMP,
NULL,
NULL);

insert
	into
	MODULE_TRIGGERS (MODULE_NAME,
	DTO_NAME,
	MODULE_DESCRIPTION,
	TRIGGER_ID,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag)
values('OCDORASS',
'net.syscon.s4.cm.primaryofficeragentassignment.beans.VOmTeamMembers',
'Offenders Requiring Assignment',
'94',
CURRENT_TIMESTAMP,
'OMS_OWNER',
CURRENT_TIMESTAMP,
NULL,
NULL);

insert
	into
	MODULE_TRIGGERS (MODULE_NAME,
	DTO_NAME,
	MODULE_DESCRIPTION,
	TRIGGER_ID,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag)
values('OIDPINFO',
'net.syscon.s4.im.beans.OffenderProfileDetails',
'Personal Information',
'95',
CURRENT_TIMESTAMP,
'OMS_OWNER',
CURRENT_TIMESTAMP,
NULL,
NULL);

insert
	into
	MODULE_TRIGGERS (MODULE_NAME,
	DTO_NAME,
	MODULE_DESCRIPTION,
	TRIGGER_ID,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag)
values('OCDRLFCC',
'net.syscon.s4.im.beans.OffenderBookingAgyLocs',
'Remove Location From Current Contact',
'96',
CURRENT_TIMESTAMP,
'OMS_OWNER',
CURRENT_TIMESTAMP,
NULL,
NULL);

insert
	into
	MODULE_TRIGGERS (MODULE_NAME,
	DTO_NAME,
	MODULE_DESCRIPTION,
	TRIGGER_NAME,
	TRIGGER_ID,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag)
values('OCDSABUS',
'net.syscon.s4.inst.booking.beans.OffenderSubstanceUses',
'Substance Abuse',
'Substance Abuse History',
'97',
CURRENT_TIMESTAMP,
'OMS_OWNER',
CURRENT_TIMESTAMP,
NULL,
NULL);

insert
	into
	MODULE_TRIGGERS (MODULE_NAME,
	DTO_NAME,
	MODULE_DESCRIPTION,
	TRIGGER_NAME,
	TRIGGER_ID,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag)
values('OCDSABUS',
'net.syscon.s4.inst.booking.beans.OffenderSubstanceDetails',
'Substance Abuse',
'Details of Use',
'98',
CURRENT_TIMESTAMP,
'OMS_OWNER',
CURRENT_TIMESTAMP,
NULL,
NULL);

insert
	into
	MODULE_TRIGGERS (MODULE_NAME,
	DTO_NAME,
	MODULE_DESCRIPTION,
	TRIGGER_NAME,
	TRIGGER_ID,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag)
values('OCDSABUS',
'net.syscon.s4.inst.booking.beans.OffenderSubstanceTreatments',
'Substance Abuse',
'Treatment',
'99',
CURRENT_TIMESTAMP,
'OMS_OWNER',
CURRENT_TIMESTAMP,
NULL,
NULL);
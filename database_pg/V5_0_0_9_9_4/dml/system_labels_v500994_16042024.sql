INSERT
	INTO
	OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID,
	MODULE_NAME,
	MSG_KEY,
	MSG_VALUE,
	MSG_TYPE,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag)

SELECT
	nextval('LABLE_ID_SEQUENCE'),
	'OIDCHOLO',
	'oidcholo.nobedavailableforselectedlocation',
	'Not enough space available in the selected location',
	'LABEL',
	current_timestamp,
	'OMS_OWNER',
	NULL,
	NULL,
	NULL
WHERE
	NOT EXISTS (
	SELECT
		1
	FROM
		OMS_OWNER.SYSTEM_LABELS
	WHERE
		MODULE_NAME = 'OIDCHOLO'
		AND msg_key = 'oidcholo.nobedavailableforselectedlocation');

INSERT
	INTO
	OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID,
	MODULE_NAME,
	MSG_KEY,
	MSG_VALUE,
	MSG_TYPE,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag)
SELECT
	nextval('LABLE_ID_SEQUENCE'),
	'COMMON',
	'common.fromandtolocationcannotbesame',
	'from and to location can not be same',
	'LABEL',
	current_timestamp,
	'OMS_OWNER',
	NULL,
	NULL,
	NULL
WHERE
	NOT EXISTS (
	SELECT
		1
	FROM
		OMS_OWNER.SYSTEM_LABELS
	WHERE
		MODULE_NAME = 'COMMON'
		AND msg_key = 'common.fromandtolocationcannotbesame');

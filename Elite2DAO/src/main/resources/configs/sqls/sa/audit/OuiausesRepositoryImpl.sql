

OUIAUSES_CREATE_FORM_GLOBALS {
	SELECT DESCRIPTION INTO V_FORM_DESC FROM OMS_MODULES WHERE MODULE_NAME = V_FORM_NAME
}

OUIAUSES_GET_DATE_TIME{

SELECT
    to_date(to_char(sys.fga_log$.ntimestamp#, 'DD-MON-YYYY')) dates,
    to_char(sys.fga_log$.ntimestamp#, 'hh12:mi') times
FROM
    sys.fga_log$
WHERE
    sessionid = :sessionId
}

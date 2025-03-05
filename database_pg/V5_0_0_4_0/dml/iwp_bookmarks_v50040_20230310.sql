update iwp_bookmarks 
   set modify_datetime = current_timestamp 
	  ,modify_user_id = 'OMS_OWNER'
	  ,sql_text ='select
    (
    select
        description
    from
        reference_codes rc
    where
        code = OID.ALERT_TYPE
        and domain = ''ALERT'') as Alert_Type,
    (
    select
        description
    from
        reference_codes rc
    where
        code = OID.ALERT_CODE
        and domain = ''ALERT_CODE'') as Alert,
    OID.ALERT_STATUS as status,
    to_char(OID.ALERT_DATE,''MM-DD-YYYY'') as Effective_date1,
    to_char(OID.ALERT_DATE,''DD-MM-YYYY'') as Effective_date2,
    to_char(OID.ALERT_DATE,''YYYY-MM-DD'') as Effective_date3,
    to_char(OID.EXPIRY_DATE,''MM-DD-YYYY'') as Expiry_date1,
    to_char(OID.EXPIRY_DATE,''DD-MM-YYYY'') as Expiry_date2,
     to_char(OID.EXPIRY_DATE,''YYYY-MM-DD'') as Expiry_date3,

    OID.VERIFIED_FLAG as Verified,
    OID.AUTHORIZE_PERSON_TEXT as Authorized_by,
    OID.COMMENT_TEXT as comment
from
    OFFENDER_ALERTS OID
where
    offender_book_id=?' where bookmark_name ='OFFEN_ALERT';
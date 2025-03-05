OIDCUSTAD_SAVE_BOOKINGS{
insert into oms_owner.offender_legal_adjustments (offender_order_adjust_id, adjust_code, offender_book_id, object_id, object_type, adjust_date, adjust_days, adjust_from_date, adjust_to_date, comment_text, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag, rd_months, rd_weeks, rd_days, rd_years) values(nextval('offender_order_adjust_id_seq'), :adjustCode, :offenderBookId, :objectId, :objectType, :adjustDate, :adjustDays, :adjustFromDate, :adjustToDate, :commentText, current_timestamp, :createUserId, null, null, :sealFlag, :rdMonths, :rdWeeks, :rdDays,:rdYears)
}

OIDCUSTAD_UPDATE_BOOKINGS{
update
	offender_legal_adjustments
set
	object_id =:objectId,
	adjust_date =:adjustDate,
	adjust_days =:adjustDays,
	adjust_from_date =:adjustFromDate,
	adjust_to_date =:adjustToDate,
	comment_text =:commentText,
	modify_datetime = current_timestamp,
	modify_user_id =:modifyUserId,
	rd_years =:rdYears,
	rd_months =:rdMonths,
	rd_weeks =:rdWeeks,
	rd_days =:rdDays
where
	offender_book_id =:offenderBookId
	and offender_order_adjust_id =:offenderOrderAdjustId
}

OIDCUSTAD_DELETE_BOOKINGS{
delete from  offender_legal_adjustments where offender_order_adjust_id=:offenderOrderAdjustId
}

OIDCUSTAD_FETCH_BOOKING_DETAILS{
select ola.*, ( select usage_code from sentence_adjustments sa where sentence_adjust_code = ola.adjust_code) as usage_code,(select debit_credit_code from sentence_adjustments sa where sentence_adjust_code = ola.adjust_code) as debit_credit_code from offender_legal_adjustments ola where offender_book_id = :offenderBookId order by adjust_date 
}

OIDCUSTAD_FETCH_BOOKING_LOV_VALUES{
select SENTENCE_ADJUST_CODE code,description,usage_code,debit_credit_code,active_flag from SENTENCE_ADJUSTMENTS where usage_code in('BKG','BKG_REMISS')
}
OIDCUSTAD_FETCH_DEBIT_CREDIT_CODE{
select rc.description from reference_codes rc where rc."domain" = 'AC_TXN_POST' and code =( select sa.debit_credit_code from sentence_adjustments sa where sa.sentence_adjust_code =:sentenceAdjustCode ) 
}



OIDCUSTAD_FETCH_MAX_ID{
SELECT MAX(object_id) FROM offender_legal_adjustments WHERE OFFENDER_BOOK_ID = :offenderBookId
}

OIDCUSTAD_FETCH_USAGE_CODE{
select usage_code from sentence_adjustments sa where sentence_adjust_code =:sentenceAdjustCode
}

OIDCUSTAD_FETCH_SENTENCE_LOV_VALUES{
select SENTENCE_ADJUST_CODE code,description,usage_code,debit_credit_code,active_flag from SENTENCE_ADJUSTMENTS where usage_code in('SENT','SENT_REMISS')
}

OIDCUSTAD_FETCH_SENTENCE_DATA{
select ola.*, ( select usage_code from sentence_adjustments sa where sentence_adjust_code = ola.adjust_code) as usage_code, (select debit_credit_code from sentence_adjustments sa where sentence_adjust_code = ola.adjust_code) as debit_credit_code from offender_legal_adjustments ola where offender_book_id = :offenderBookId and object_id =:objectId order by adjust_date 
}

OIDCUSTAD_FETCH_ESCAPE_COUNT{
select movement_date from OFFENDER_EXTERNAL_MOVEMENTS where offender_book_id =:offenderBookId and movement_reason_code =( select value from legal_settings ls where code = 'RRTIEA') order by movement_date desc limit 1
}

OIDCUSTAD_RETRIEVE_ESCAPE_VALUES{
select count(1) from offender_legal_adjustments ola where offender_book_id =:offenderBookId and adjust_code ='ESCP'
}

OIDCUSTAD_RETRIEVE_INTAKE_DATE{
select booking_begin_date from offender_bookings ob  where offender_book_id =:offenderBookId 
}

OIDCUSTAD_FETCH_REMISSION_ELIGIBLITY{
select value , code from legal_settings ls  where code in ('EMRE','DERD')
}

OIDCUSTAD_FETCH_ESCAPE_ADJUSTMENTS{
select value from legal_settings ls where code='RRTIEA'
}


OIDCUSTAD_FETCH_OFFENDER_STATUS_COUNT{
select count(1) from v_header_block vhb where offender_book_id =:offenderBookId and status_display ='Active' and movement_reason ='RECA'
}

OIDCUSTAD_FETCH_MOVEMENT_SEQ{
select
	movement_seq 
from
	OFFENDER_EXTERNAL_MOVEMENTS
where
	offender_book_id =:offenderBookId
	and movement_reason_code =(
	select
		value
	from
		legal_settings ls
	where
		code = 'RRTIEA') and movement_type ='REL' and movement_reason_code ='ESCP'
order by
	create_datetime desc
limit 1
}

OIDCUSTAD_GET_INTKAE_DETAILS{
select * from offender_external_movements oem where offender_book_id =:offenderBookId order by create_datetime desc limit 1
}

OIDCUSTAD_GET_REASONS{
select count(1) from MOVEMENT_REASONS where movement_type ='REL'  and movement_reason_code ='ESCP' and close_contact_flag ='N'
}

OIDCUSTAD_GET_ADMISSION_DETAILS{
select
	*
from
	offender_external_movements oem
where
	offender_book_id =:offenderBookId
	order by create_datetime desc limit 1
}

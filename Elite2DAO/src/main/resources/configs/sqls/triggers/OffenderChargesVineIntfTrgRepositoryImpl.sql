OFFENDER_CHARGES_VINE_INTF_TRG_CUR_ACT_BOOK{
SELECT OFFENDER_ID, AGY_LOC_ID, BOOKING_NO FROM offender_bookings WHERE offender_book_id = :pOffBookId AND active_flag = 'Y'
}
OFFENDER_CHARGES_VINE_INTF_TRG_CUR_OFF{
SELECT OFFENDER_ID_DISPLAY FROM OFFENDERS WHERE offender_id = :pOffId
}
OFFENDER_CHARGES_VINE_INTF_TRG_CUR_OFF_DESC{
SELECT description FROM offences WHERE offence_code = :offCode AND   statute_code = :statCode
}
OFFENDER_CHARGES_VINE_INTF_TRG_OFFENDER_CHARGES{
SELECT OFFENDER_BOOK_ID,OFFENDER_CHARGE_ID,STATUTE_CODE,OFFENCE_CODE,NO_OF_OFFENCES,OFFENCE_DATE,OFFENCE_RANGE_DATE,PLEA_CODE,PROPERTY_VALUE,TOTAL_PROPERTY_VALUE,CJIT_OFFENCE_CODE_1,CJIT_OFFENCE_CODE_2,CJIT_OFFENCE_CODE_3,CHARGE_STATUS,CREATE_USER_ID,MODIFY_USER_ID,MODIFY_DATETIME,CREATE_DATETIME,RESULT_CODE_1,RESULT_CODE_2,RESULT_CODE_1_INDICATOR,RESULT_CODE_2_INDICATOR,CASE_ID,MOST_SERIOUS_FLAG,CHARGE_SEQ,ORDER_ID,LIDS_OFFENCE_NUMBER,OFFENCE_TYPE,SEAL_FLAG FROM OFFENDER_CHARGES WHERE OFFENDER_CHARGE_ID = :offenderChargeId
}
OFFENDER_CHARGES_VINE_INTF_TRG_INSERTING{
INSERT INTO ch_audit(action_type,agy_loc_id,offender_id_display,booking_no,offence_code,initial_counts,offence_type,offender_charge_id,charge_status,description,statute_code,offence_date,offender_book_id,modified_date,create_datetime,modify_datetime,create_user_id)VALUES(:actionType,:agyLocId,:offenderIdDisplay,:bookingNo,:offenceCode,:initialCounts,:offenceType,:offenderChargeId,:chargeStatus,:description,:statuteCode,:offenceDate,:offenderBookId,:modifiedDate,CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,:createUserId)
}
OFFENDER_CHARGES_VINE_INTF_TRG_UPDATING{
UPDATE ch_audit SET action_type='U',agy_loc_id = :agyLocId,offender_id_display = :offenderIdDisplay,booking_no = :bookingNo,offence_code = :offenceCode,offence_type = :offenceType,charge_status = :chargeStatus,description = :description,statute_code = :statuteCode,offence_date = :offenceDate,modified_date = :modifiedDate,modify_datetime =CURRENT_TIMESTAMP,modify_user_id = :modifyUserId WHERE offender_charge_id = :offenderBookId AND action_type IN ('A','U')
}
OFFENDER_CHARGES_VINE_INTF_TRG_DELETING{
INSERT INTO ch_audit(action_type,agy_loc_id,offender_id_display,booking_no,offence_code,initial_counts,offence_type,offender_charge_id,charge_status,description,statute_code,offence_date,offender_book_id,modified_date)VALUES(:actionType,:agyLocId,:offenderIdDisplay,:bookingNo,:offenceCode,:initialCounts,:offenceType,:offenderChargeId,:chargeStatus,:description,:statuteCode,:offenceDate,:offenderBookId,:modifiedDate)
}

TAG_ASSESSMENT_GET_ASSESSMENT_ID{
 select NEXTVAL('assessment_id')
}

CHK_BKG_CSA{
SELECT COUNT(*) FROM OFFENDER_BOOKING_DETAILS WHERE cell_sharing_alert_flag = 'Y' AND offender_book_id = :offenderBookId
}

GET_CSA_OCCUPANTS{
SELECT bkg.offender_book_id offender_book_id, OFF.offender_id_display offender_id_display, OFF.last_name last_name, OFF.first_name first_name FROM OFFENDER_BOOKING_DETAILS det, OFFENDER_BOOKINGS bkg, OFFENDERS OFF WHERE det.offender_book_id = bkg.offender_book_id AND bkg.offender_id = OFF.offender_id AND det.cell_sharing_alert_flag = 'Y' AND det.offender_book_id IN ( 
	select
		bkg2.offender_book_id
	from
		offender_bookings bkg2
	where
		bkg2.living_unit_id in (
(
		select
			living_unit_id
		from
			living_units lu
		where
			parent_living_unit_id =((
			select
				case
					when ((
					select
						parent_living_unit_id
					from
						living_units lu
					where
						living_unit_id = :livingUnitId) is not null) 
then (
					select
						parent_living_unit_id
					from
						living_units lu
					where
						living_unit_id = :livingUnitId)
					else :livingUnitId
				end))))
		or bkg2.living_unit_id =:livingUnitId)
}
CHK_ASSESSMENT_CSA_CSA_RESULT{
SELECT cell_sharing_alert_flag FROM ASSESSMENT_RESULTS WHERE assessment_id = :pAssessmentId  AND supervision_level_type = :pAssessmentCode
  AND ( SELECT cell_sharing_alert_flag  FROM ASSESSMENTS   WHERE assessment_id = :pAssessmentId ) = 'Y'
 }

OFFENDER_BOOKING_DETAILS_UPDATE_BKG_CSA{
   update OFFENDER_BOOKING_DETAILS set cell_sharing_alert_flag = :cellSharingAlertFlag, modify_user_id = :modifyUserId, modify_datetime = current_timestamp where offender_book_id = :offenderBookId and cell_sharing_alert_flag != :cellSharingAlertFlag
}
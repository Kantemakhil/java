
OIIHLHIS_BEDAH_FIND_BED_ASSIGNMENT_HISTORIES {
 	SELECT (select description from reference_codes where domain ='CHG_HOUS_RSN' and code = bah.assignment_reason) assignment_reason ,offender_book_id,bed_assign_seq,living_unit_id,assignment_date,assignment_time,assignment_end_date,assignment_end_time,create_datetime,create_user_id,modify_datetime,modify_user_id,seal_flag  FROM BED_ASSIGNMENT_HISTORIES bah  where bah.offender_book_id=:offender_book_id ORDER BY bah.create_datetime desc
}

OIIHLHIS_POST_QUERY {
	SELECT LIV_UNIT.AGY_LOC_ID
            ,LIV_UNIT.LIVING_UNIT_CODE
            ,LIV_UNIT.PARENT_LIVING_UNIT_ID
            ,LIV_UNIT.DESCRIPTION
      FROM   LIVING_UNITS LIV_UNIT
      WHERE  LIV_UNIT.LIVING_UNIT_ID = :LIVING_UNIT_ID
}

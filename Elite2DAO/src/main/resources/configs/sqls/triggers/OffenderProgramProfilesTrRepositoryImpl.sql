OFFENDER_PROGRAM_PROFILES_TR_OFFENDER_PROGRAM_STATUS {
	SELECT count(*)
        FROM   Reference_codes
        WHERE  code = :offender_Program_status
        AND    DOMAIN = ('OFF_PRG_STS')
}

OFFENDER_PROGRAM_PROFILES_TR_WAITLIST_DECISION_CODE {
	SELECT count(*)
        FROM   Reference_codes
        WHERE  code = :waitlist_Decision_code
        AND    DOMAIN = ('PS_ACT_DEC')
}

OFFENDER_PROGRAM_PROFILES_TR_CRS_ACTY_ID {
	SELECT count(*)
        FROM   course_activities
        WHERE  crs_acty_ID = :crs_acty_id
        AND    course_activity_Type IS NOT NULL 
}

OCUVWARN_OFFENDERRESTRICTION_INSERT_TAG_VISITS.GET_OFFENDER_RESTRICTIONS {
	INSERT INTO TAG_VISITS.GET_OFFENDER_RESTRICTIONS() VALUES(:)
}

OCUVWARN_VISITORRESTRICTIONS_INSERT_TAG_VISITS.GET_VISITOR_RESTRICTIONS {
	INSERT INTO TAG_VISITS.GET_VISITOR_RESTRICTIONS() VALUES(:)
}
OCUVWARN_TAG_VISIT_GET_VISITOR_RESTRICTIONS {
select
	VST.RESTRICTION_DATE RESTRICTIONDATE,
	SUBSTR(VST.RESTRICTION_CODE,
	5,
	20) VISITRESTRICTIONTYPE,
	case
		when SUBSTR(VST.RESTRICTION_CODE,
		1,
		4)= 'GLOB' then 'GLOBAL RESTRICTION'
	end DESCRIPTION
from
	(
	select
		VR.EFFECTIVE_DATE RESTRICTION_DATE,
		'GLOB' || RC.CODE RESTRICTION_CODE
	from
		VISITOR_RESTRICTIONS VR,
		REFERENCE_CODES RC
	where
		RC.DOMAIN = 'VST_RST_TYPE'
		and RC.CODE = VR.VISIT_RESTRICTION_TYPE
		and VR.PERSON_ID = :PERSON_ID
		and VR.EFFECTIVE_DATE <= date_trunc('day', :VISIT_DATE::timestamp)
		and (coalesce(VR.EXPIRY_DATE::text, '') = ''
			or
                        VR.EXPIRY_DATE > date_trunc('day', :VISIT_DATE::timestamp))
union all
	select
		OCR.RESTRICTION_EFFECTIVE_DATE RESTRICTION_DATE,
		'REST' || RC.CODE RESTRICTION_CODE
	from
		OFFENDER_PERSON_RESTRICTS OCR,
		REFERENCE_CODES RC
	where
		RC.DOMAIN = 'VST_RST_TYPE'
		and RC.CODE = OCR.RESTRICTION_TYPE
		and OCR.OFFENDER_CONTACT_PERSON_ID in (
		select
			distinct OFFENDER_CONTACT_PERSON_ID
		from
			OFFENDER_CONTACT_PERSONS
		where
			PERSON_ID = :PERSON_ID
			and OFFENDER_BOOK_ID = :OFFENDER_BOOK_ID)
		and OCR.RESTRICTION_EFFECTIVE_DATE <= date_trunc('day', :VISIT_DATE::timestamp)
		and (coalesce(OCR.RESTRICTION_EXPIRY_DATE::text, '') = ''
			or
                        OCR.RESTRICTION_EXPIRY_DATE > date_trunc('day', :VISIT_DATE::timestamp))) VST
order by
	DESCRIPTION desc,
	RESTRICTIONDATE asc;
                   }
OCUVWARN_GET_PROFILE_VALUES{
SELECT * FROM  SYSTEM_PROFILES WHERE PROFILE_TYPE=:PROFILE_TYPE and PROFILE_CODE=:PROFILE_CODE
}


OCUVWARN_GET_OFFENDER_RESTRICTIONS {
                SELECT ORS.EFFECTIVE_DATE,
                ORS.RESTRICTION_TYPE
           FROM OFFENDER_RESTRICTIONS ORS
          WHERE ORS.OFFENDER_BOOK_ID = :OFFENDER_BOOK_ID
            AND ORS.EFFECTIVE_DATE <= :VISIT_DATE
            AND (ORS.EXPIRY_DATE IS NULL OR
                ORS.EXPIRY_DATE > :VISIT_DATE)
}

OCUVWARN_GET_OFFENDER_NAMES {
SELECT FIRST_NAME,
                LAST_NAME
           FROM OFFENDERS
          WHERE OFFENDER_ID = :offenderId limit 1
}

OCUVWARN_GET_DESCCODE {
select DESCRIPTION from REFERENCE_CODES where DOMAIN = :domain and CODE = :code
}

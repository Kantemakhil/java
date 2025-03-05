
OIUSANCT_OFFENDEROICSANCTIONS_FIND_OFFENDER_OIC_SANCTIONS {
 	SELECT *,( select description 
			from reference_codes 
				where domain = 'OIC_SANCT' 
					and code = oic_sanction_code) as description  FROM OFFENDER_OIC_SANCTIONS WHERE OFFENDER_BOOK_ID = :offenderBookId ORDER BY EFFECTIVE_DATE ASC
}



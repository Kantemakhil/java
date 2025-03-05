package net.syscon.s4.triggers.impl;

import java.math.BigDecimal;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.im.beans.OffenderCases;
import net.syscon.s4.triggers.OffenderCasesT4Repository;
@Repository
public class OffenderCasesT4RepositoryImpl extends RepositoryBase implements OffenderCasesT4Repository{

	private static Logger logger = LogManager.getLogger(OffenderCasesT4RepositoryImpl.class.getName());
	
//	@Override
//	public OffenderCases getOffenderCases(final Long caseId) {
//		final String sql = getQuery("GET_OFFENDER_CASES");
//		return  namedParameterJdbcTemplate
//				.queryForObject(sql, createParams("CASE_ID",caseId),new BeanPropertyRowMapper<OffenderCases>(OffenderCases.class));
//		
//	}
	
	@Override
	public BigDecimal getLidsCaseNumber(final Long offenderBookId) {
		final String sql = getQuery("GET_LIDS_CASE_NUMBER");
		BigDecimal resp = null;
		try {
			resp = namedParameterJdbcTemplate.queryForObject(sql, createParams("offender_book_id", offenderBookId),
					BigDecimal.class);
		} catch (Exception e) {
			logger.error("getLidsCaseNumber :" + e);
		}
		return resp;
	}
}


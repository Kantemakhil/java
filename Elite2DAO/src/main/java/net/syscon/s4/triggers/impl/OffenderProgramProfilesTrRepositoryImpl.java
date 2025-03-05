package net.syscon.s4.triggers.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.triggers.OffenderProgramProfilesTrRepository;

@Repository
public class OffenderProgramProfilesTrRepositoryImpl extends RepositoryBase implements OffenderProgramProfilesTrRepository{
	
	private static Logger logger = LogManager.getLogger(OffenderProgramProfilesTrRepositoryImpl.class);
	
	@Override
	public Integer offenderProgramStatusCount(String offenderProgramStatus) {
		Integer result=null;
		final String sqlname = getQuery("OFFENDER_PROGRAM_PROFILES_TR_OFFENDER_PROGRAM_STATUS");
		try {
			result= namedParameterJdbcTemplate.queryForObject(sqlname, createParams("offender_Program_status",offenderProgramStatus), Integer.class);
		} catch (final Exception e) {
			logger.error("lCheckExistCur", e);
			
		}
		return result;
	}
	@Override
	public Integer waitlistDecisionCodeCount(String waitlistDecisionCode) {
		Integer result=null;
		final String sqlname = getQuery("OFFENDER_PROGRAM_PROFILES_TR_WAITLIST_DECISION_CODE");
		try {
			result= namedParameterJdbcTemplate.queryForObject(sqlname, createParams("waitlist_Decision_code",waitlistDecisionCode), Integer.class);
		} catch (final Exception e) {
			logger.error("lCheckExistCur", e);
			
		}
		return result;
	}
	@Override
	public Integer crsActyIdCount(Long crsActyId) {
		Integer result=null;
		final String sqlname = getQuery("OFFENDER_PROGRAM_PROFILES_TR_CRS_ACTY_ID");
		try {
			result= namedParameterJdbcTemplate.queryForObject(sqlname, createParams("crs_acty_id",crsActyId), Integer.class);
		} catch (final Exception e) {
			logger.error("lCheckExistCur", e);
			
		}
		return result;
	}
}

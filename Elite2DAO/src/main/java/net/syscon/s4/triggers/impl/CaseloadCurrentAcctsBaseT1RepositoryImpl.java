package net.syscon.s4.triggers.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.inmate.trust.checks.checksmainataince.impl.OtmbaccoRepositoryImpl;
import net.syscon.s4.triggers.CaseloadCurrentAcctsBaseT1Repository;

@Repository
public class CaseloadCurrentAcctsBaseT1RepositoryImpl extends RepositoryBase
		implements CaseloadCurrentAcctsBaseT1Repository {

	private static Logger logger = LogManager.getLogger(CaseloadCurrentAcctsBaseT1RepositoryImpl.class.getName());
	private static final String N = "N";

	@Override
	public String gettingCheckBookExistFlag(String caseloadId, Long accountCode) {
		final String sql = getQuery("GETTING_CHQ_BOOKS_EXISTS_FLAG");
		String retVal = N;
		try {
			retVal = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("caseload_id", caseloadId, "account_code", accountCode), String.class);
		} catch (Exception e) {
			logger.error("gettingCheckBookExistFlag", e);
			retVal = N;
		}
		return retVal;
	}

	@Override
	public Integer updatingBankChequeBooks(String caseloadId, Long accountCode, String bankAccountNumber) {

		final String sql = getQuery("UPDATE_BANK_CHEQUE_BOOKS");
		Integer retVal = 0;
		retVal = namedParameterJdbcTemplate.update(sql, createParams("bankAccountNumber", bankAccountNumber,
				"caseload_id", caseloadId, "account_code", accountCode));
		return retVal;

	}

}

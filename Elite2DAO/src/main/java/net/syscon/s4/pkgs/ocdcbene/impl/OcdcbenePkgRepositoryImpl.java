package net.syscon.s4.pkgs.ocdcbene.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.inmate.beans.BeneficiaryTransactions;
import net.syscon.s4.pkgs.ocdcbene.OcdcbenePkgRepository;

@Repository
public class OcdcbenePkgRepositoryImpl extends RepositoryBase implements OcdcbenePkgRepository {
	private static Logger logger = LogManager.getLogger(OcdcbenePkgRepositoryImpl.class.getName());

	private final Map<String, FieldMapper> couEveMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DESCRIPTION", new FieldMapper("description ")).build();
	
	private final Map<String, FieldMapper> cChequeMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("TXN_ID",						new FieldMapper("txnId"))
			.put("PERSON_ID",					new FieldMapper("personId"))
			.put("CORPORATE_ID",				new FieldMapper("corporateId"))
			.put("AMOUNT",						new FieldMapper("amount"))
			.put("OFFENDER_DEDUCTION_ID",		new FieldMapper("offenderDeductionId"))
			.put("OFFENDER_ID",					new FieldMapper("rootOffenderId"))
			.put("CREATE_DATETIME",				new FieldMapper("createDateTime"))
			.put("MODIFY_DATETIME",				new FieldMapper("modifyDateTime"))
			.build();

	@Override
	public Date getLockedDate(final String PModuleName, final String PCaseloadId) {

		final String sql = getQuery("GET_LOCKED_DATE");
		return namedParameterJdbcTemplate.queryForObject(sql,
				createParams("P_MODULE_NAME", PModuleName, "P_CASELOAD_ID", PCaseloadId), Date.class);
	}

	@Override
	public List<BeneficiaryTransactions> lBenPersonTxnCur(final BigDecimal PPersonId, final BigDecimal pAccountCode,
			final String pCaseloadId, final Date pLockedDate) {
		List<BeneficiaryTransactions> liReturn =null;
		final String sql = getQuery("L_BEN_PERSON_TXN_CUR");
		try {
			liReturn= namedParameterJdbcTemplate.query(sql,
					createParams("P_PERSON_ID", PPersonId, "P_ACCOUNT_CODE", pAccountCode, "P_CASELOAD_ID", pCaseloadId,
							"P_LOCKED_DATE", pLockedDate),
					new BeanPropertyRowMapper<BeneficiaryTransactions>(BeneficiaryTransactions.class));
		} catch (Exception e) {
			logger.error(this.getClass().getName()+"Error in method lBenPersonTxnCur:", e);
			liReturn=null;
		}
		return liReturn;
	}

	@Override
	public List<BeneficiaryTransactions> lBenCorporateTxnCur(final BigDecimal pCorporateId, final BigDecimal pAccountCode,
			final String pCaseloadId, final Date pLockedDate) {
		final String sql = getQuery("L_BEN_CORPORATE_TXN_CUR");
		final RowMapper<BeneficiaryTransactions> sysProfMapper = Row2BeanRowMapper.makeMapping(sql,
				BeneficiaryTransactions.class, cChequeMapping);
		return namedParameterJdbcTemplate.query(sql,
				createParams("P_CORPORATE_ID", pCorporateId, "P_ACCOUNT_CODE", pAccountCode, "P_CASELOAD_ID",
						pCaseloadId, "P_LOCKED_DATE", pLockedDate),
				new BeanPropertyRowMapper<BeneficiaryTransactions>(BeneficiaryTransactions.class));
	}

	@Override
	public Map<String, Object> lChequeDetails(final String pModuleName, final String pCaseloadId,
			final BigDecimal pAccountCode) {
		final String sql = getQuery("L_CHEQUE_DETAILS");
		return namedParameterJdbcTemplate.queryForMap(sql, createParams("P_MODULE_NAME", pModuleName, "P_CASELOAD_ID",
				pCaseloadId, "P_DR_ACCOUNT_CODE", pAccountCode));
	}

	@Override
	public String lValidatePerson(final BigDecimal pPersonId, final BigDecimal pOffenderDeductionId) {

		final String sql = getQuery("L_VALIDATE_PERSON");
		return namedParameterJdbcTemplate.queryForObject(sql,
				createParams("P_PERSON_ID", pPersonId, "P_OFFENDER_DEDUCTION_ID", pOffenderDeductionId), String.class);
	}

	@Override
	public String lValidateCorporate(final BigDecimal pCorporateId, final BigDecimal pOffenderDeductionId) {
          String liReturn=null;
		final String sql = getQuery("L_VALIDATE_CORPORATE");
		try {
			liReturn= namedParameterJdbcTemplate.queryForObject(sql,
					createParams("P_CORPORATE_ID", pCorporateId, "P_OFFENDER_DEDUCTION_ID", pOffenderDeductionId),
					String.class);
		} catch (Exception e) {
			logger.error(this.getClass().getName()+"Error in method lValidateCorporate:", e);
			liReturn=null;
		}
		return liReturn;
	}

	@Override
	public Integer updateBeneficiaryTransactionsByPersonId(final BigDecimal pPersonId,
			final BeneficiaryTransactions beneficiaryTransactions, final BigDecimal pAccountCode,
			final String userName) {
		final String sql = getQuery("UPDATE_BENEFICIARY_TRANSACTIONS_BY_PERSON_ID");
		return namedParameterJdbcTemplate.update(sql,
				createParams("P_PERSON_ID", pPersonId, "TXN_ID", beneficiaryTransactions.getTxnId(),
						"OFFENDER_DEDUCTION_ID", beneficiaryTransactions.getOffenderDeductionId(), "P_ACCOUNT_CODE",
						pAccountCode, "MODIFY_USER_ID", userName));
	}

	@Override
	public Integer insertBankChequeBeneficiariesByPersonId(BigDecimal pNewTxnId, BigDecimal pTotalAmount,
			BigDecimal lAbsTxnEntryAmount, final BeneficiaryTransactions beneficiaryTransactions,String userName) {
		final String sql = getQuery("INSERT_BANK_CHEQUE_BENEFICIARIES_FOR_PERSON_ID");
		Map<String, Object> inParamMap = new HashMap<String, Object>();

		inParamMap.put("P_NEW_TXN_ID", pNewTxnId);
		inParamMap.put("P_TOTAL_AMOUNT", pTotalAmount);
		inParamMap.put("PERSON_ID", beneficiaryTransactions.getPersonId());
		inParamMap.put("TXN_ID", beneficiaryTransactions.getTxnId());
		inParamMap.put("OFFENDER_ID", beneficiaryTransactions.getOffenderId());
		inParamMap.put("L_ABS_TXN_ENTRY_AMOUNT", lAbsTxnEntryAmount);
		inParamMap.put("OFFENDER_DEDUCTION_ID", beneficiaryTransactions.getOffenderDeductionId());
		inParamMap.put("CREATE_USER_ID", userName);
		return namedParameterJdbcTemplate.update(sql, inParamMap);

	}

	@Override
	public Integer updateBeneficiaryTransactionsByCorporateId(final BigDecimal pCorporateId,
			final BeneficiaryTransactions beneficiaryTransactions, final BigDecimal pAccountCode,String userName) {
		final String sql = getQuery("UPDATE_BENEFICIARY_TRANSACTIONS_BY_CORPORATE_ID");
		return namedParameterJdbcTemplate.update(sql,
				createParams("P_CORPORATE_ID", pCorporateId, "TXN_ID", beneficiaryTransactions.getTxnId(),
						"OFFENDER_DEDUCTION_ID", beneficiaryTransactions.getOffenderDeductionId(), "P_ACCOUNT_CODE",
						pAccountCode,"userName",userName));
	}

	@Override
	public Integer insertBankChequeBeneficiariesByCorporateId(final BigDecimal pNewTxnId, final BigDecimal pTotalAmount,
			final BigDecimal lAbsTxnEntryAmount, final BeneficiaryTransactions beneficiaryTransactions,
			final String userName) {
		final String sql = getQuery("INSERT_BANK_CHEQUE_BENEFICIARIES_FOR_CORPORATE_ID");
		Map<String, Object> inParamMap = new HashMap<String, Object>();
		Integer retVal = 0;

		inParamMap.put("P_NEW_TXN_ID", pNewTxnId);
		inParamMap.put("P_TOTAL_AMOUNT", pTotalAmount);
		inParamMap.put("CORPORATE_ID", beneficiaryTransactions.getCorporateId());
		inParamMap.put("TXN_ID", beneficiaryTransactions.getTxnId());
		inParamMap.put("OFFENDER_ID", beneficiaryTransactions.getOffenderId());
		inParamMap.put("AMOUNT", lAbsTxnEntryAmount);
		inParamMap.put("OFFENDER_DEDUCTION_ID", beneficiaryTransactions.getOffenderDeductionId());
		inParamMap.put("CREATE_USER_ID", userName);

		return namedParameterJdbcTemplate.update(sql, inParamMap);
	}

}

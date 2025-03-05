package net.syscon.s4.inmate.trust.deductions.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.OmsModules;
import net.syscon.s4.im.beans.TransactionOperation;
import net.syscon.s4.inmate.beans.BankChequeBeneficiaries;
import net.syscon.s4.inmate.beans.BeneficiaryTransactions;
import net.syscon.s4.inmate.beans.VClearAccountPayables;
import net.syscon.s4.inmate.trust.deductions.OcdcbeneRepository;

/**
 * Class OcdcbeneRepositoryImpl
 */
@Repository
public class OcdcbeneRepositoryImpl extends RepositoryBase implements OcdcbeneRepository {
	
	private static Logger logger = LogManager.getLogger(OcdcbeneRepositoryImpl.class.getName());

	private final Map<String, FieldMapper> vClrAcMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CASELOAD_ID",                 new FieldMapper("caseloadId"))
			.put("CORPORATE_NAME",              new FieldMapper("corporateName"))
			.put("TOTAL_AMOUNT",                new FieldMapper("totalAmount"))
			.put("ACCOUNT_CODE",                new FieldMapper("accountCode"))
			.put("PERSON_ID",                   new FieldMapper("personId"))
			.put("CORPORATE_ID",                new FieldMapper("corporateId"))
			.build();
	
	private final Map<String, FieldMapper> benefTransMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("TXN_ENTRY_TIME",              new FieldMapper("txnEntryTime"))
			.put("UNKNOWN_BEN_ID",              new FieldMapper("unknownBenId"))
			.put("REV_TXN_FLAG",                new FieldMapper("revTxnFlag"))
			.put("PERSON_ID",                   new FieldMapper("personId"))
			.put("RECEIPT_TXN_TYPE",            new FieldMapper("receiptTxnType"))
			.put("GL_ENTRY_SEQ",                new FieldMapper("glEntrySeq"))
			.put("CHECK_CLEARED_DATE",          new FieldMapper("checkClearedDate"))
			.put("SEAL_FLAG",                   new FieldMapper("sealFlag"))
			.put("CREATE_DATETIME",             new FieldMapper("createDatetime"))
			.put("BENEFICIARY_CLEARED_FLAG",    new FieldMapper("beneficiaryClearedFlag"))
			.put("TXN_TYPE",                    new FieldMapper("txnType"))
			.put("REV_TXN_ENTRY_SEQ",           new FieldMapper("revTxnEntrySeq"))
			.put("MODIFY_DATETIME",             new FieldMapper("modifyDatetime"))
			.put("REV_GL_ENTRY_SEQ",            new FieldMapper("revGlEntrySeq"))
			.put("TXN_ID",                      new FieldMapper("txnId"))
			.put("BENEFICIARY_ID",              new FieldMapper("beneficiaryId"))
			.put("CREATE_USER_ID",              new FieldMapper("createUserId"))
			.put("OFFENDER_DEDUCTION_ID",       new FieldMapper("offenderDeductionId"))
			.put("TXN_ENTRY_DATE",              new FieldMapper("txnEntryDate"))
			.put("MODIFY_USER_ID",              new FieldMapper("modifyUserId"))
			.put("TXN_ENTRY_AMOUNT",            new FieldMapper("txnEntryAmount"))
			.put("BEN_ENTRY_SEQ",               new FieldMapper("benEntrySeq"))
			.put("REV_BEN_ENTRY_SEQ",           new FieldMapper("revBenEntrySeq"))
			.put("CASELOAD_ID",                 new FieldMapper("caseloadId"))
			.put("MODIFY_DATE",                 new FieldMapper("modifyDate"))
			.put("REV_TXN_ID",                  new FieldMapper("revTxnId"))
			.put("TXN_ENTRY_SEQ",               new FieldMapper("txnEntrySeq"))
			.put("ACCOUNT_CODE",                new FieldMapper("accountCode"))
			.put("TXN_POST_USAGE",              new FieldMapper("txnPostUsage"))
			.put("TXN_ENTRY_DESC",              new FieldMapper("txnEntryDesc"))
			.put("CORPORATE_ID",                new FieldMapper("corporateId"))
			.put("ROOT_OFFENDER_ID",            new FieldMapper("rootOffenderId"))
			.put("OFFENDER_ID_DISPLAY",         new FieldMapper("offenderIdDisplay"))
			.put("LAST_FIRST_NAME",         new FieldMapper("lastFirstName"))
			.build();
	
	private final Map<String, FieldMapper> sysPrflsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("PROFILE_CODE",                new FieldMapper("profileCode"))
			.put("PROFILE_TYPE",                new FieldMapper("profileType"))
			.put("CREATE_USER_ID",              new FieldMapper("createUserId"))
			.put("SEAL_FLAG",                   new FieldMapper("sealFlag"))
			.put("CREATE_DATETIME",             new FieldMapper("createDatetime"))
			.put("MODIFY_USER_ID",              new FieldMapper("modifyUserId"))
			.put("OLD_TABLE_NAME",              new FieldMapper("oldTableName"))
			.put("PROFILE_VALUE",               new FieldMapper("profileValue"))
			.put("MODIFY_DATETIME",             new FieldMapper("modifyDatetime"))
			.put("PROFILE_VALUE_2",             new FieldMapper("profileValue2"))
			.put("DESCRIPTION",                 new FieldMapper("description"))
			.build();
	
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
	private final Map<String, FieldMapper> transOpersMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CR_ACCOUNT_CODE", 				new FieldMapper("crAccountCode"))
			.put("CHEQUE_PRODUCTION_FLAG", 			new FieldMapper("chequeProductionFlag"))
			.put("TXN_TYPE", 						new FieldMapper("txnType"))
			.put("BANK_CR_ACCOUNT_CODE", 			new FieldMapper("bankCrAccountCode"))
			.put("BANK_DR_ACCOUNT_CODE", 			new FieldMapper("bankDrAccountCode"))
			.put("DESCRIPTION", 					new FieldMapper("description"))
			.build();
	
	/**
	 * Creates new OcdcbeneRepositoryImpl class Object
	 */
	public OcdcbeneRepositoryImpl() {
		// OcdcbeneRepositoryImpl
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            VClearAccountPayables
	 *
	 * @return List<VClearAccountPayables>
	 *
	 */
	public List<VClearAccountPayables> payeeAbExecuteQuery(final VClearAccountPayables objSearchDao) {
		final String sql = getQuery("OCDCBENE_PAYEEAB_FIND_V_CLEAR_ACCOUNT_PAYABLES");
		String preparedSql = null;
		final StringBuffer sqlQuery = new StringBuffer();
		String preSqlQuery = null;
		final MapSqlParameterSource params = new MapSqlParameterSource();
		sqlQuery.append(sql);
		if (objSearchDao != null) {
			sqlQuery.append(" where ");
			if (objSearchDao != null && objSearchDao.getAccountCode() != null) {
				sqlQuery.append(" ACCOUNT_CODE  = :accountCode  " + " and ");
				params.addValue("accountCode", objSearchDao.getAccountCode());
			}
			if (objSearchDao != null && objSearchDao.getPersonId() != null) {
				sqlQuery.append(" PERSON_ID  = :personId  " + " and ");
				params.addValue("personId", objSearchDao.getPersonId());
			}
			if (objSearchDao != null && objSearchDao.getCorporateId() != null) {
				sqlQuery.append(" CORPORATE_ID  = :corporateId  " + " and ");
				params.addValue("corporateId", objSearchDao.getCorporateId());
			}
			if (objSearchDao != null && objSearchDao.getCorporateName() != null) {
				sqlQuery.append(" CORPORATE_NAME  = :corporateName  " + " and ");
				params.addValue("corporateName", objSearchDao.getCorporateName());
			}
			sqlQuery.append(
					" CASELOAD_ID = (SELECT WORKING_CASELOAD_ID FROM STAFF_MEMBERS WHERE USER_ID = :USER) AND TOTAL_AMOUNT > 0 ");
			params.addValue("USER", objSearchDao.getCreateUserId());
		}
		preparedSql = sqlQuery.toString().trim();
		if (preparedSql.endsWith("where")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 5);
		}
		if (preparedSql.endsWith("and")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 3);
		}
		preSqlQuery = preparedSql.concat(" ORDER BY ACCOUNT_CODE, PERSON_ID, CORPORATE_ID ");
		final RowMapper<VClearAccountPayables> vclrAcntPayMapper = Row2BeanRowMapper.makeMapping(sql,
				VClearAccountPayables.class, vClrAcMapping);
		List<VClearAccountPayables> returnList = new ArrayList<>();
		returnList = namedParameterJdbcTemplate.query(preSqlQuery, params, vclrAcntPayMapper);
		return returnList;
	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstVClearAccountPayables
	 *            List<VClearAccountPayables>
	 *
	 */
	public Integer payeeAbUpdateVClearAccountPayables(final List<VClearAccountPayables> list) {
		final String sql = getQuery("OCDCBENE_PAYEEAB_UPDATE_V_CLEAR_ACCOUNT_PAYABLES");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final VClearAccountPayables obj : list) {
			parameters.add(new BeanPropertySqlParameterSource(obj));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error(this.getClass().getName()+"Error in method payeeAbUpdateVClearAccountPayables:", e);
		}
		if (list.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            BeneficiaryTransactions
	 *
	 * @return List<BeneficiaryTransactions>
	 *
	 */
	public List<BeneficiaryTransactions> benTxnExecuteQuery(final BeneficiaryTransactions objSearchDao) {
		final String sql = getQuery("OCDCBENE_BENTXN_FIND_BENEFICIARY_TRANSACTIONS");
		String preparedSql = null;
		final StringBuffer sqlQuery = new StringBuffer();
		final MapSqlParameterSource params = new MapSqlParameterSource();
		sqlQuery.append(sql);
		if (objSearchDao != null) {
			if (objSearchDao.getPersonId() != null) {
//				sqlQuery.append(
//						" WHERE exists  (select 1 from beneficiary_transactions bt, beneficiary_transactions btx, offender_deductions od WHERE bt.person_id IS NOT NULL  and bt.txn_id = beneficiary_transactions.txn_id "
//								+ " and bt.txn_entry_seq = beneficiary_transactions.txn_entry_seq and bt.gl_entry_seq = beneficiary_transactions.gl_entry_seq "
//								+ " and bt.ben_entry_seq = beneficiary_transactions.ben_entry_seq "
//								+ " AND bt.offender_deduction_id = od.offender_deduction_id AND bt.caseload_id = :caseLoadId AND bt.person_id = :persionId "
//								+ " AND bt.account_code = :accountCode AND bt.rev_txn_id = btx.txn_id(+) AND bt.rev_txn_entry_seq = btx.txn_entry_seq(+) "
//								+ " AND bt.rev_gl_entry_seq = btx.gl_entry_seq(+) AND bt.ben_entry_seq = btx.ben_entry_seq(+) "
//								+ " AND ((  bt.beneficiary_cleared_flag = 'N' OR bt.beneficiary_cleared_flag IS NULL) "
//								+ " AND decode(bt.rev_txn_id, null, bt.txn_entry_date, btx.txn_entry_date) <= "
//								+ " (SELECT SYSDATE - NVL (MAX (TO_NUMBER (s1.profile_value)), 0) FROM system_profiles s1, system_profiles s2 "
//								+ " WHERE s1.profile_type = 'TRUST_INF' AND s1.profile_code = 'CHECK_AGING' "
//								+ " AND s2.profile_type = 'CHECK_AGING' AND s2.profile_code = bt.receipt_txn_type)))");
				
				sqlQuery.append(
						"WHERE exists (select 1 from beneficiary_transactions bt  LEFT JOIN beneficiary_transactions btx ON bt.rev_txn_id = btx.txn_id"
								+ "  AND bt.rev_txn_entry_seq = btx.txn_entry_seq "
								+ " AND bt.rev_gl_entry_seq = btx.gl_entry_seq "
								+ "  AND bt.ben_entry_seq = btx.ben_entry_seq "
								+ "  INNER JOIN offender_deductions      od ON bt.offender_deduction_id = od.offender_deduction_id "
								+ " where bt.person_id is not null "
								+ "  AND bt.person_id = :persionId "
								+ "    AND bt.txn_id = beneficiary_transactions.txn_id "
								+ "   AND bt.txn_entry_seq = beneficiary_transactions.txn_entry_seq "
								+ " AND bt.gl_entry_seq = beneficiary_transactions.gl_entry_seq "
								+ " AND bt.ben_entry_seq = beneficiary_transactions.ben_entry_seq "
								+ "  AND bt.caseload_id = :caseLoadId "
								+ "AND bt.account_code = :accountCode"
								+ "    AND ( bt.beneficiary_cleared_flag = 'N'  OR bt.beneficiary_cleared_flag IS NULL )"
								+ " AND case  when bt.rev_txn_id IS NULL then  bt.txn_entry_date  else btx.txn_entry_date  end <= (  SELECT current_timestamp - coalesce(  MAX( s1.profile_value::bigint), 0 ) * interval '1 day'"
								+ " FROM   system_profiles s1,system_profiles s2 WHERE s1.profile_type = 'TRUST_INF' AND s1.profile_code = 'CHECK_AGING' "
								+ "AND s2.profile_type = 'CHECK_AGING' AND s2.profile_code = bt.receipt_txn_type))");
				
				params.addValue("accountCode", objSearchDao.getAccountCode());
				params.addValue("caseLoadId", objSearchDao.getCaseloadId());
				params.addValue("persionId", objSearchDao.getPersonId());
			}
			if (objSearchDao.getCorporateId() != null) {
//				sqlQuery.append(
//						"WHERE exists (select 1 from beneficiary_transactions bt, beneficiary_transactions btx, offender_deductions od"
//								+ " WHERE bt.corporate_id IS NOT NULL and bt.txn_id = beneficiary_transactions.txn_id "
//								+ " and bt.txn_entry_seq = beneficiary_transactions.txn_entry_seq and bt.gl_entry_seq = beneficiary_transactions.gl_entry_seq "
//								+ " and bt.ben_entry_seq = beneficiary_transactions.ben_entry_seq "
//								+ " AND bt.offender_deduction_id = od.offender_deduction_id  AND bt.caseload_id = :caseLoadId "
//								+ " AND bt.corporate_id = :corporateId AND bt.account_code = :accountCode "
//								+ " AND bt.rev_txn_id = btx.txn_id(+) AND bt.rev_txn_entry_seq = btx.txn_entry_seq(+) "
//								+ " AND bt.rev_gl_entry_seq = btx.gl_entry_seq(+) AND bt.ben_entry_seq = btx.ben_entry_seq(+) "
//								+ " AND (  bt.beneficiary_cleared_flag = 'N' OR bt.beneficiary_cleared_flag IS NULL) "
//								+ " AND decode(bt.rev_txn_id, null, bt.txn_entry_date, btx.txn_entry_date) <=  (SELECT SYSDATE -       NVL (MAX (TO_NUMBER (s1.profile_value)), 0) "
//								+ " FROM system_profiles s1, system_profiles s2 WHERE s1.profile_type = 'TRUST_INF' AND s1.profile_code = 'CHECK_AGING' "
//								+ " AND s2.profile_type = 'CHECK_AGING' AND s2.profile_code = bt.receipt_txn_type)) ");
				sqlQuery.append(
						"WHERE exists (select 1 from beneficiary_transactions bt  LEFT JOIN beneficiary_transactions btx ON bt.rev_txn_id = btx.txn_id"
								+ "  AND bt.rev_txn_entry_seq = btx.txn_entry_seq "
								+ " AND bt.rev_gl_entry_seq = btx.gl_entry_seq "
								+ "  AND bt.ben_entry_seq = btx.ben_entry_seq "
								+ "  INNER JOIN offender_deductions      od ON bt.offender_deduction_id = od.offender_deduction_id "
								+ " where bt.corporate_id is not null "
								+ "  AND bt.corporate_id = :corporateId "
								+ "    AND bt.txn_id = beneficiary_transactions.txn_id "
								+ "   AND bt.txn_entry_seq = beneficiary_transactions.txn_entry_seq "
								+ " AND bt.gl_entry_seq = beneficiary_transactions.gl_entry_seq "
								+ " AND bt.ben_entry_seq = beneficiary_transactions.ben_entry_seq "
								+ "  AND bt.caseload_id = :caseLoadId "
								+ "AND bt.account_code = :accountCode"
								+ "    AND ( bt.beneficiary_cleared_flag = 'N'  OR bt.beneficiary_cleared_flag IS NULL )"
								+ " AND case  when bt.rev_txn_id IS NULL then  bt.txn_entry_date  else btx.txn_entry_date  end <= (  SELECT current_timestamp - coalesce(  MAX( s1.profile_value::bigint), 0 ) * interval '1 day'"
								+ " FROM   system_profiles s1,system_profiles s2 WHERE s1.profile_type = 'TRUST_INF' AND s1.profile_code = 'CHECK_AGING' "
								+ "AND s2.profile_type = 'CHECK_AGING' AND s2.profile_code = bt.receipt_txn_type))");
				
				params.addValue("accountCode", objSearchDao.getAccountCode());
				params.addValue("caseLoadId", objSearchDao.getCaseloadId());
				params.addValue("corporateId", objSearchDao.getCorporateId());
			}
			if (objSearchDao.getRootOffenderId() != null) {
				if (objSearchDao.getRootOffenderId() == null) {
					objSearchDao.setRootOffenderId(-1L);
				}
				sqlQuery.append(" AND offender_deduction_id IN (SELECT offender_deduction_id FROM offender_deductions"
						+ " WHERE offender_id = :rootOffenderId ");
				params.addValue("rootOffenderId", objSearchDao.getRootOffenderId());
			}
			if (objSearchDao.getLastFirstName() != null) {
				sqlQuery.append(
						" AND offender_deduction_id IN (SELECT offender_deduction_id FROM offender_deductions WHERE offender_id IN "
								+ " (SELECT root_offender_id FROM offenders WHERE last_name || '', ''|| first_name '|| like '%' :lastFirstName '%' ");
				params.addValue("lastFirstName", objSearchDao.getLastFirstName());
			}

		}
		preparedSql = sqlQuery.toString().trim();
		if (preparedSql.endsWith("where")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 5);
		}
		if (preparedSql.endsWith("and")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 3);
		}
		preparedSql = preparedSql.concat(" ORDER BY txn_entry_date, txn_entry_time ");
		final RowMapper<BeneficiaryTransactions> benfTransMapper = Row2BeanRowMapper.makeMapping(sql,
				BeneficiaryTransactions.class, benefTransMapping);
		List<BeneficiaryTransactions> returnList = new ArrayList<>();
		returnList = namedParameterJdbcTemplate.query(preparedSql, params, benfTransMapper);
		return returnList;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            SystemProfiles
	 *
	 * @return List<SystemProfiles>
	 *
	 */
	public List<SystemProfiles> sysPflExecuteQuery(final SystemProfiles objSearchDao) {
		final String sql = getQuery("OCDCBENE_SYSPFL_FIND_SYSTEM_PROFILES");
		final RowMapper<SystemProfiles> sysProfMapper = Row2BeanRowMapper.makeMapping(sql, SystemProfiles.class,
				sysPrflsMapping);
		List<SystemProfiles> returnList = new ArrayList<>();
		returnList = namedParameterJdbcTemplate.query(sql, createParams(), sysProfMapper);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * createFormGlobals
	 *
	 * @param params
	 *
	 */
	public String createFormGlobals(final OmsModules paramBean) {
		final String sql = getQuery("OCDCBENE_CREATE_FORM_GLOBALS");
		final String returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(paramBean), String.class);
		return returnObj;
	}

	/**
	 * Fetch the offender names from database table
	 *
	 * @param objSearchDao
	 *            BeneficiaryTransactions
	 *
	 * @return BeneficiaryTransactions
	 *
	 */
	public BeneficiaryTransactions offenderNameCursor(final BeneficiaryTransactions objSearchDao) {
		final String sql = getQuery("OCDCBENE_OFFENDER_NAME_CURSOR");
		BeneficiaryTransactions returnList = new BeneficiaryTransactions();
		final RowMapper<BeneficiaryTransactions> benfTransMapper = Row2BeanRowMapper.makeMapping(sql,
				BeneficiaryTransactions.class, benefTransMapping);
		try {
			returnList = namedParameterJdbcTemplate.queryForObject(sql, createParams("OFFENDER_DEDUCTION_ID",
					objSearchDao.getOffenderDeductionId(), "CASELOAD_ID", objSearchDao.getCaseloadId()),
					benfTransMapper);
		} catch (Exception e) {
			logger.error(this.getClass().getName()+"Error in method offenderNameCursor:", e);
			returnList = new BeneficiaryTransactions();
		}
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * createFormGlobals
	 *
	 * @param params
	 *
	 */
	public String txnPostingTypeCursor(final Integer accountCode) {
		final String sql = getQuery("OCDCBENE_TXN_POSTING_TYPE_CURSOR");
		String returnObj = null;
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams("ACCOUNT_CODE", accountCode),
					String.class);
		} catch (Exception e) {
			logger.error(this.getClass().getName()+"Error in method txnPostingTypeCursor:", e);
			returnObj = null;
		}
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * getPersonsInfoCursor
	 *
	 * @param params
	 *
	 */
	public String getPersonsInfoCursor(final BigDecimal personId) {
		final String sql = getQuery("OCDCBENE_GET_PERSON_INFO_CURSROR");
		String returnObj = null;
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams("PERSON_ID", personId),
					String.class);
		} catch (Exception e) {
			logger.error(this.getClass().getName()+"Error in method getPersonsInfoCursor:", e);
			returnObj = "N";
		}
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * getCorporateInfoCursor
	 *
	 * @param params
	 *
	 */
	public String getCorporateInfoCursor(final BigDecimal corporateId) {
		final String sql = getQuery("OCDCBENE_GET_CORPORATE_INFO_CURSROR");
		String returnObj = null;
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams("CORPORATE_ID", corporateId),
					String.class);
		} catch (Exception e) {
			logger.error(this.getClass().getName()+"Error in method getCorporateInfoCursor:", e);
			returnObj = "N";
		}
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * getCorporateInfoCursor
	 *
	 * @param params
	 *
	 */
	public BigDecimal getMinimuimBalCursor(final BigDecimal accountCode) {
		final String sql = getQuery("OCDCBENE_GET_MIN_BAL_CURSROR");
		BigDecimal returnObj = new BigDecimal(0);
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams("ACCOUNT_CODE", accountCode),
					BigDecimal.class);
		} catch (Exception e) {
			logger.error(this.getClass().getName()+"Error in method getMinimuimBalCursor:", e);
			returnObj = new BigDecimal(0);
		}
		return returnObj;
	}

	/**
	 * 
	 * @param caseLoadId
	 * @return
	 */
	public String checkLock(final String caseLoadId) {
		final String sql = getQuery("OCDCBENE_CHECK_LOCK");
		String returnObj = null;
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams("CASELOAD_ID", caseLoadId),
					String.class);
		} catch (Exception e) {
			logger.error(this.getClass().getName()+"Error in method checkLock:", e);
			returnObj = null;
		}
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 *
	 * @return Integer
	 */
	public Integer txnIdNextValData() {
		final String sql = getQuery("OCDCBENE_GET_TXN_ID");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams(), Integer.class);
	}

	/**
	 * This method will update the transaction in the table
	 * 
	 * @param offTrans
	 * @return Integer
	 */
	@Override
	public Integer processBankCheque(final BigDecimal personId, final BigDecimal corporateId,
			final BigDecimal accountCode, final String corporateName, final String caseloadId,
			final BigDecimal totalAmount, final Integer txnIdNextVal) {
		Integer genSeq = 0;
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		inParamMap.put("p_person_id", personId);
		inParamMap.put("p_corporate_id", corporateId);
		inParamMap.put("p_account_code", accountCode);
		inParamMap.put("p_payee_name", corporateName);
		inParamMap.put("p_caseload_id", caseloadId);
		inParamMap.put("p_module_name", "OCDCBENE");
		inParamMap.put("p_total_amount", totalAmount);
		inParamMap.put("p_new_txn_id", txnIdNextVal);
		try {
			namedParameterJdbcTemplate
					.update("call OMS_OWNER.OCDCBENE.PROCESS_BANK_CHEQUE(:p_person_id, :p_corporate_id, :p_account_code, :p_payee_name, :p_caseload_id, :p_module_name, :p_total_amount,"
							+ " :p_new_txn_id)", inParamMap);
			genSeq = 1;
		} catch (Exception e) {
			genSeq = 0;
		}
		return genSeq;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            VClearAccountPayables
	 *
	 * @return List<VClearAccountPayables>
	 *
	 */
	public List<BeneficiaryTransactions> personIdExecuteQuery(final VClearAccountPayables objSearchDao) {
		final String sql = getQuery("OCDCBENE_PERSON_ID");
		final RowMapper<BeneficiaryTransactions> sysProfMapper = Row2BeanRowMapper.makeMapping(sql,
				BeneficiaryTransactions.class, cChequeMapping);
		List<BeneficiaryTransactions> returnList = new ArrayList<>();
		returnList = namedParameterJdbcTemplate
				.query(sql,
						createParams("PERSONID", objSearchDao.getPersonId(), "ACCOUNTCODE",
								objSearchDao.getAccountCode(), "CASELOADID", objSearchDao.getCaseloadId()),
						sysProfMapper);
		return returnList;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            VClearAccountPayables
	 *
	 * @return List<VClearAccountPayables>
	 *
	 */
	public List<BeneficiaryTransactions> corporateIdExecuteQuery(final VClearAccountPayables objSearchDao) {
		final String sql = getQuery("OCDCBENE_CORPORATE_ID");
		final RowMapper<BeneficiaryTransactions> sysProfMapper = Row2BeanRowMapper.makeMapping(sql,
				BeneficiaryTransactions.class, cChequeMapping);
		List<BeneficiaryTransactions> returnList = new ArrayList<>();
		returnList = namedParameterJdbcTemplate
				.query(sql,
						createParams("CORPORATEID", objSearchDao.getCorporateId(), "ACCOUNTCODE",
								objSearchDao.getAccountCode(), "CASELOADID", objSearchDao.getCaseloadId()),
						sysProfMapper);
		return returnList;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            VClearAccountPayables
	 *
	 * @return List<VClearAccountPayables>
	 *
	 */
	public TransactionOperation checkDetailsExecuteQuery(final VClearAccountPayables objSearchDao) {
		final String sql = getQuery("OCDCBENE_CHEQUE_DETAILS");
		final RowMapper<TransactionOperation> sysProfMapper = Row2BeanRowMapper.makeMapping(sql,
				TransactionOperation.class, transOpersMapping);
		TransactionOperation returnList = null;
		returnList = namedParameterJdbcTemplate.queryForObject(sql,
				createParams("ACCOUNTCODE", objSearchDao.getAccountCode(), "CASELOADID", objSearchDao.getCaseloadId()),
				sysProfMapper);
		return returnList;
	}

	/**
	 * 
	 * @param caseLoadId
	 * @return
	 */
	public Integer personIdCheck(final BigDecimal personId, final BigDecimal deductionId) {
		final String sql = getQuery("OCDCBENE_PERSON_ID_CHECK");
		Integer returnObj = null;
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("PERSONID", personId, "OFFENDERDEDUCTIONID", deductionId), Integer.class);
		} catch (Exception e) {
			logger.error(this.getClass().getName()+"Error in method personIdCheck:", e);
			returnObj = null;
		}
		return returnObj;
	}

	/**
	 * 
	 * @param caseLoadId
	 * @return
	 */
	public Integer corporateIdCheck(final BigDecimal corporateId, final BigDecimal deductionId) {
		final String sql = getQuery("OCDCBENE_PERSON_ID_CHECK");
		Integer returnObj = null;
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("CORPORATEID", corporateId, "OFFENDERDEDUCTIONID", deductionId), Integer.class);
		} catch (Exception e) {
			logger.error(this.getClass().getName()+"Error in method corporateIdCheck:", e);
			returnObj = null;
		}
		return returnObj;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param beneTransactions
	 *            BeneficiaryTransactions
	 *
	 * @return Integer
	 */
	public Integer updatePersonBeneficiaryTransactions(final BeneficiaryTransactions beneTransactions) {
		final String sql = getQuery("OCDCBENE_PERSON_UPDATE_BENEFICIARY_TRANSACTIONS");
		return namedParameterJdbcTemplate.update(sql, new BeanPropertySqlParameterSource(beneTransactions));
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param beneTransactions
	 *            BeneficiaryTransactions
	 *
	 * @return Integer
	 */
	public Integer updateCorporateBeneficiaryTransactions(final BeneficiaryTransactions beneTransactions) {
		final String sql = getQuery("OCDCBENE_CORPORATE_UPDATE_BENEFICIARY_TRANSACTIONS");
		return namedParameterJdbcTemplate.update(sql, new BeanPropertySqlParameterSource(beneTransactions));
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param beneTransactions
	 *            BeneficiaryTransactions
	 *
	 * @return Integer
	 */
	public Integer insertPersonBeneficiaryTransactions(final Integer txnIdNextVal, final BigDecimal totalAmount,
			final BigDecimal personId, final Long txnId, final Long rootOffenderId, final BigDecimal lAbsTxnEntryAmount,
			final BigDecimal offenderDeductionId) {
		final BankChequeBeneficiaries objData = new BankChequeBeneficiaries();
		objData.setChequeTxnId(BigDecimal.valueOf(txnIdNextVal));
		objData.setChequeAmount(totalAmount);
		objData.setPersonId(personId);
		objData.setTxnId(BigDecimal.valueOf(txnId));
		objData.setOffenderId(BigDecimal.valueOf(rootOffenderId));
		objData.setAmount(lAbsTxnEntryAmount);
		objData.setOffenderDeductionId(offenderDeductionId);
		final String sql = getQuery("OCDCBENE_INSERT_INTO_PERSON_BANK_CHEQUE_BENEFICIARIES");
		return namedParameterJdbcTemplate.update(sql, new BeanPropertySqlParameterSource(objData));
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param beneTransactions
	 *            BeneficiaryTransactions
	 *
	 * @return Integer
	 */
	public Integer insertCorporateBeneficiaryTransactions(final Integer txnIdNextVal, final BigDecimal totalAmount,
			final BigDecimal corporateId, final Long txnId, final Long rootOffenderId,
			final BigDecimal lAbsTxnEntryAmount, final BigDecimal offenderDeductionId) {
		final BankChequeBeneficiaries objData = new BankChequeBeneficiaries();
		objData.setChequeTxnId(BigDecimal.valueOf(txnIdNextVal));
		objData.setChequeAmount(totalAmount);
		objData.setCorporateId(corporateId);
		objData.setTxnId(BigDecimal.valueOf(txnId));
		objData.setOffenderId(BigDecimal.valueOf(rootOffenderId));
		objData.setAmount(lAbsTxnEntryAmount);
		objData.setOffenderDeductionId(offenderDeductionId);
		final String sql = getQuery("OCDCBENE_INSERT_INTO_CORPORATE_BANK_CHEQUE_BENEFICIARIES");
		return namedParameterJdbcTemplate.update(sql, new BeanPropertySqlParameterSource(objData));
	}

	/**
	 * This method will update the transaction in the table
	 * 
	 * @param offTrans
	 * @return String
	 */
	public void checkCaseloadUnlockType(final String caseloadId) {
		final String user = userNameData();
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		inParamMap.put("p_module_name", "OCDCBENE");
		inParamMap.put("p_caseload", caseloadId);
		inParamMap.put("p_user", user);
		try {
			namedParameterJdbcTemplate.update("call OMS_OWNER.UNLOCK_FORM_MODULE(:p_module_name, :p_caseload, :p_user)",
					inParamMap);
		} catch (Exception e) {
			logger.error("checkCaseloadUnlockType :" + e);
		}
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param beneTransactions
	 *            BeneficiaryTransactions
	 *
	 * @return Integer
	 */
	public String userNameData() {
		final String sql = getQuery("OCDCBENE_USER_DATA");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams(), String.class);
	}

	/**
	 * This method will update the transaction in the table
	 * 
	 * @param offTrans
	 * @return String
	 */
	public void checkCaseloadlockType(final String caseloadId) {
		final String user = userNameData();
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		inParamMap.put("p_module_name", "OCDCBENE");
		inParamMap.put("p_caseload", caseloadId);
		inParamMap.put("p_user", user);
		try {
			namedParameterJdbcTemplate.update("call OMS_OWNER.LOCK_FORM_MODULE(:p_module_name, :p_caseload, :p_user)",
					inParamMap);
		} catch (Exception e) {
			logger.error("checkCaseloadUnlockType :" + e);
		}
	}
}

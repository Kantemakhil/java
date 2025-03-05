package net.syscon.s4.inmate.trust.deductions.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.support.TransactionOperations;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.cf.deductions.beans.OffFeeBillTransactions;
import net.syscon.s4.cf.deductions.beans.offBillingStatements;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.Corporates;
import net.syscon.s4.im.beans.OffenderTransactions;
import net.syscon.s4.im.beans.TransactionOperation;
import net.syscon.s4.inmate.beans.OffenderBeneficiaries;
import net.syscon.s4.inmate.beans.OffenderDeductions;
import net.syscon.s4.inmate.trust.deductions.OcdpayobRepository;
import net.syscon.s4.inst.booking.beans.Persons;
import oracle.jdbc.OracleTypes;

/**
 * Class OcdpayobRepositoryImpl
 */
@Repository
public class OcdpayobRepositoryImpl extends RepositoryBase implements OcdpayobRepository {
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OcdpayobRepositoryImpl.class.getName());

private final Map<String, FieldMapper> personsMapping = new ImmutableMap.Builder<String, FieldMapper>()
.put("PERSON_ID", 							new FieldMapper("personId"))
.put("LAST_NAME", 							new FieldMapper("lastName"))
.put("FIRST_NAME", 							new FieldMapper("firstName"))
.build();
private final Map<String, FieldMapper> offenderBeneficiariesMapping = new ImmutableMap.Builder<String, FieldMapper>()
.put("OVERRIDE_AMOUNT", 					new FieldMapper("overrideAmount"))
.put("COMMENT_TEXT", 						new FieldMapper("commentText"))
.put("RECURSIVE_AMOUNT", 					new FieldMapper("recursiveAmount"))
.put("CREATE_USER_ID", 						new FieldMapper("createUserId"))
.put("OFFENDER_DEDUCTION_ID", 				new FieldMapper("offenderDeductionId"))
.put("PRIORITY", 						    new FieldMapper("priority"))
.put("MODIFY_USER_ID", 						new FieldMapper("modifyUserId"))
.put("UNKNOWN_BEN_ID", 						new FieldMapper("unknownBenId"))
.put("PERSON_ID", 						    new FieldMapper("personId"))
.put("OFFENDER_ID", 						new FieldMapper("offenderId"))
.put("MONTHLY_AMOUNT", 						new FieldMapper("monthlyAmount"))
.put("TBD_FLAG", 						    new FieldMapper("tbdFlag"))
.put("PERCENT", 						    new FieldMapper("percent"))
.put("AMOUNT", 						        new FieldMapper("amount"))
.put("RECEIVED_AMOUNT", 					new FieldMapper("receivedAmount"))
.put("SEAL_FLAG", 						    new FieldMapper("sealFlag"))
.put("CREATE_DATETIME", 					new FieldMapper("createDateTime"))
.put("MODIFY_DATETIME", 					new FieldMapper("modifyDateTime"))
.put("BENEFICIARY_ID", 						new FieldMapper("beneficiaryId"))
.put("CORPORATE_ID", 						new FieldMapper("corporateId"))
.build();
private final Map<String, FieldMapper> offenderDeductionsMapping = new ImmutableMap.Builder<String, FieldMapper>()
.put("DEDUCTION_TYPE", 						new FieldMapper("deductionType"))
.put("DEDUCTION_DESC", 						new FieldMapper("deductionDesc"))
.put("INFORMATION_NUMBER", 					new FieldMapper("informationNumber"))
.put("OFFENDER_DEDUCTION_ID", 				new FieldMapper("offenderDeductionId"))
.put("ADJUSTMENT_REASON_CODE", 				new FieldMapper("adjustmentReasonCode"))
.put("ADJUSTMENT_AMOUNT", 					new FieldMapper("adjustmentAmount"))
.build();
private final Map<String, FieldMapper> systemProfilesMapping = new ImmutableMap.Builder<String, FieldMapper>()
.put("PROFILE_CODE", 						new FieldMapper("profileCode"))
.put("PROFILE_TYPE", 						new FieldMapper("profileType"))
.put("CREATE_USER_ID", 						new FieldMapper("createUserId"))
.put("SEAL_FLAG", 						    new FieldMapper("sealFlag"))
.put("CREATE_DATETIME", 					new FieldMapper("createDatetime"))
.put("MODIFY_USER_ID", 						new FieldMapper("modifyUserId"))
.put("OLD_TABLE_NAME", 						new FieldMapper("oldTableName"))
.put("PROFILE_VALUE", 						new FieldMapper("profileValue"))
.put("MODIFY_DATETIME", 					new FieldMapper("modifyDatetime"))
.put("PROFILE_VALUE_2", 					new FieldMapper("profileValue2"))
.put("DESCRIPTION", 						new FieldMapper("description"))
.build();
private final Map<String, FieldMapper> transactionOperationsMapping = new ImmutableMap.Builder<String, FieldMapper>()
.put("ACCOUNT_NAME", 						new FieldMapper("accountName"))
.put("SUB_ACCOUNT_TYPE", 					new FieldMapper("subAccountType"))
.put("CASELOAD_ID", 						new FieldMapper("caseloadId"))
.build();
private final Map<String, FieldMapper> offenderTransactionsMapping = new ImmutableMap.Builder<String, FieldMapper>()
.put("TXN_REFERENCE_NUMBER", 				new FieldMapper("txnReferenceNumber"))
.put("INFO_NUMBER", 						new FieldMapper("infoNumber"))
.put("APPLY_SPENDING_LIMIT_AMOUNT", 		new FieldMapper("applySpendingLimitAmount"))
.put("TXN_ADJUSTED_FLAG", 					new FieldMapper("txnAdjustedFlag"))
.put("ADJUST_TXN_ENTRY_ID", 				new FieldMapper("adjustTxnEntryId"))
.put("PAYEE_NAME_TEXT", 					new FieldMapper("payeeNameText"))
.put("RECEIPT_PRINTED_FLAG", 				new FieldMapper("receiptPrintedFlag"))
.put("OFFENDER_ID", 						new FieldMapper("offenderId"))
.put("HOLD_CLEAR_FLAG", 					new FieldMapper("holdClearFlag"))
.put("GROSS_NET_FLAG", 						new FieldMapper("grossNetFlag"))
.put("SEAL_FLAG", 						    new FieldMapper("sealFlag"))
.put("CREATE_DATETIME", 					new FieldMapper("createDatetime"))
.put("HOLD_NUMBER", 						new FieldMapper("holdNumber"))
.put("TXN_TYPE", 						    new FieldMapper("txnType"))
.put("GROSS_AMOUNT", 						new FieldMapper("grossAmount"))
.put("RECEIPT_PENDING_PRINT_FLAG", 			new FieldMapper("receiptPendingPrintFlag"))
.put("MODIFY_DATETIME", 					new FieldMapper("modifyDatetime"))
.put("PAYEE_PERSON_ID", 					new FieldMapper("payeePersonId"))
.put("ORG_TXN_TYPE", 						new FieldMapper("orgTxnType"))
.put("TRANSFER_CASELOAD_ID", 				new FieldMapper("transferCaseloadId"))
.put("TXN_ID", 						        new FieldMapper("txnId"))
.put("OFFENDER_BOOK_ID", 					new FieldMapper("offenderBookId"))
.put("REMITTER_ID", 						new FieldMapper("remitterId"))
.put("CREATE_USER_ID", 						new FieldMapper("createUserId"))
.put("TXN_ENTRY_DATE", 						new FieldMapper("txnEntryDate"))
.put("PAYEE_CODE", 						    new FieldMapper("payeeCode"))
.put("MODIFY_USER_ID", 						new FieldMapper("modifyUserId"))
.put("TXN_ENTRY_AMOUNT", 					new FieldMapper("txnEntryAmount"))
.put("ADJUST_OFFENDER_ID", 					new FieldMapper("adjustOffenderId"))
.put("RECEIPT_NUMBER", 						new FieldMapper("receiptNumber"))
.put("ADJUST_TXN_ID", 						new FieldMapper("adjustTxnId"))
.put("CLOSING_CHEQUE_NUMBER", 				new FieldMapper("closingChequeNumber"))
.put("HOLD_UNTIL_DATE", 					new FieldMapper("holdUntilDate"))
.put("SLIP_PRINTED_FLAG", 					new FieldMapper("slipPrintedFlag"))
.put("ADJUST_ACCOUNT_CODE", 				new FieldMapper("adjustAccountCode"))
.put("CASELOAD_ID", 						new FieldMapper("caseloadId"))
.put("MODIFY_DATE", 						new FieldMapper("modifyDate"))
.put("DEDUCTION_FLAG", 						new FieldMapper("deductionFlag"))
.put("TXN_ENTRY_SEQ", 						new FieldMapper("txnEntrySeq"))
.put("DEDUCTION_TYPE", 						new FieldMapper("deductionType"))
.put("REMITTER_NAME", 						new FieldMapper("remitterName"))
.put("PAYEE_CORPORATE_ID", 					new FieldMapper("payeeCorporateId"))
.put("SUB_ACCOUNT_TYPE", 					new FieldMapper("subAccountType"))
.put("TXN_ENTRY_DESC", 						new FieldMapper("txnEntryDesc"))
.put("PRE_WITHHOLD_AMOUNT", 				new FieldMapper("preWithholdAmount"))
.put("TXN_POSTING_TYPE", 					new FieldMapper("txnPostingType"))
.build();
private final Map<String, FieldMapper> corporatesMapping = new ImmutableMap.Builder<String, FieldMapper>()
.put("CORPORATE_NAME", 						new FieldMapper("corporateName"))
.put("CORPORATE_ID", 						new FieldMapper("corporateId"))
.build();
private final Map<String, FieldMapper> TrnOperationMapping = new ImmutableMap.Builder<String, FieldMapper>()
.put("CODE",                 				new FieldMapper("code"))
.put("DESCRIPTION",                    		new FieldMapper("description"))
.build();

private final Map<String, FieldMapper> offFeeMapping = new ImmutableMap.Builder<String, FieldMapper>()
.put("FEECODE", new FieldMapper("feeCode"))
.put("BILL_TXN_AMOUNT",new FieldMapper("balance"))
.put("BOOKING_NO",new FieldMapper("bookingNo"))
.put("CASELOAD_ID",new FieldMapper("caseloadId"))
.put("offender_fee_id",new FieldMapper("offenderFeeId"))
.put("RECEIPTPRODUCTIONFLAG", new FieldMapper("receiptProductionFlag")).build();

	/**
	 * Creates new OcdpayobRepositoryImpl class Object
	 */
	public OcdpayobRepositoryImpl() {
		
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            OffenderTransactions
	 *
	 * @return List<OffenderTransactions>
	 *
	 * @
	 */
	public List<OffenderTransactions> offTxnExecuteQuery(final OffenderTransactions objSearchDao) {
		final String sql = getQuery("OCDPAYOB_OFFTXN_FIND_OFFENDER_TRANSACTIONS");
		final RowMapper<OffenderTransactions> OffenderTransactionsRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderTransactions.class, offenderTransactionsMapping);
		List<OffenderTransactions> returnList = new ArrayList<>();
		returnList = namedParameterJdbcTemplate.query(sql, createParams(), OffenderTransactionsRowMapper);
		return returnList;
	}

	/**
	 * This method is used to insert the records in the data base tables based
	 * on
	 *
	 * @param lstOffenderTransactions
	 *            List<OffenderTransactions>
	 *
	 * @return List<Integer>
	 *
	 * @
	 */
	public Integer offTxnInsertOffenderTransactions(final List<OffenderTransactions> lstOffenderTransactions) {
		final String sql = getQuery("OCDPAYOB_OFFTXN_INSERT_OFFENDER_TRANSACTIONS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffenderTransactions obj : lstOffenderTransactions) {
			parameters.add(new BeanPropertySqlParameterSource(obj));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstOffenderTransactions.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            OffenderBeneficiaries
	 *
	 * @return List<OffenderBeneficiaries>
	 *
	 * @
	 */
	public List<OffenderBeneficiaries> offBncExecuteQuery(final OffenderBeneficiaries objSearchDao,String deductionId) {
		final String sql = getQuery("OCDPAYOB_OFFBNC_FIND_OFFENDER_BENEFICIARIES");
		String preparedSql = null;
		final StringBuffer sqlQuery = new StringBuffer();
		String preSqlQuery = null;
		final MapSqlParameterSource params = new MapSqlParameterSource();
		sqlQuery.append(sql);
		if (objSearchDao != null) {
			if (objSearchDao.getOffenderId() != null && objSearchDao.getCaseloadId() != null
					&& objSearchDao.getCaseLoadType() != null) {
				sqlQuery.append(" WHERE ");
				sqlQuery.append(deductionId);
				if (objSearchDao.getCaseloadId() != null) {
					params.addValue("caseload_id", objSearchDao.getCaseloadId());
				}
				if (objSearchDao.getCaseLoadType() != null) {
					params.addValue("caseload_type", objSearchDao.getCaseLoadType());
				}
				if (objSearchDao.getOffenderId() != null) {
					params.addValue("root_offender_id", objSearchDao.getOffenderId());
				}
				if(objSearchDao.getOffenderId() != null) {
					params.addValue("OVR_OFFENDER_ID", objSearchDao.getOffenderId());
				}
				params.addValue("receipt_txn_type", objSearchDao.getTxnType());
			}
		}
		preparedSql = sqlQuery.toString().trim();
		if (preparedSql.endsWith("WHERE")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 5);
		}
		if (preparedSql.endsWith("AND")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 3);
		}
		preSqlQuery = preparedSql.concat(" ORDER BY OFFENDER_DEDUCTION_ID ");

		final RowMapper<OffenderBeneficiaries> OffenderBeneficiariesRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderBeneficiaries.class, offenderBeneficiariesMapping);
		List<OffenderBeneficiaries> returnList = new ArrayList<>();
		returnList = namedParameterJdbcTemplate.query(preSqlQuery, params, OffenderBeneficiariesRowMapper);
		return returnList;
	}

	public String setDefaultWhereOffBnc(final OffenderBeneficiaries objSearchDao) {
		String openAnAccount = null;
		Map<String, Object> returnObject = null;
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[10];
		sqlParameters = new SqlParameter[] { new SqlParameter("p_caseload_id", OracleTypes.VARCHAR),
				new SqlParameter("p_caselaod_type", OracleTypes.VARCHAR),
				new SqlParameter("p_off_id", OracleTypes.NUMBER),
				new SqlParameter("p_module_name", OracleTypes.VARCHAR),
				new SqlParameter("p_info_num", OracleTypes.VARCHAR),
				new SqlParameter("P_receipt_txn_type", OracleTypes.VARCHAR),
				new SqlOutParameter("p_where_statement", OracleTypes.VARCHAR), };
		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("OCDPAYOB").withProcedureName("SET_DEFAULT_WHERE_OFF_BNC")
				.declareParameters(sqlParameters);
		inParamMap.put("p_caseload_id", objSearchDao.getCaseloadId());
		inParamMap.put("p_caselaod_type", objSearchDao.getCaseLoadType());
		inParamMap.put("p_off_id", objSearchDao.getOffenderId());
		inParamMap.put("p_module_name", "OCDPAYOB");
		inParamMap.put("p_info_num", null);
		inParamMap.put("P_receipt_txn_type", null);
		final SqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		try {
			returnObject = simpleJDBCCall.execute(inParameter);
			openAnAccount = String.valueOf(returnObject.get("p_where_statement"));
		} catch (Exception e) {
			logger.error("setDefaultWhereOffBnc :" + e);
		}
		return openAnAccount;
	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstOffenderBeneficiaries
	 *            List<OffenderBeneficiaries>
	 *
	 * @
	 */
	public Integer offBncUpdateOffenderBeneficiaries(final List<OffenderBeneficiaries> lstOffenderBeneficiaries) {
		final String sql = getQuery("OCDPAYOB_OFFBNC_UPDATE_OFFENDER_BENEFICIARIES");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffenderBeneficiaries obj : lstOffenderBeneficiaries) {
			parameters.add(new BeanPropertySqlParameterSource(obj));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstOffenderBeneficiaries.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            SystemProfiles
	 *
	 * @return List<SystemProfiles>
	 *
	 * @
	 */
	public List<SystemProfiles> sysPflExecuteQuery(final SystemProfiles objSearchDao) {
		final String sql = getQuery("OCDPAYOB_SYSPFL_FIND_SYSTEM_PROFILES");
		final RowMapper<SystemProfiles> SystemProfilesRowMapper = Row2BeanRowMapper.makeMapping(sql,
				SystemProfiles.class, systemProfilesMapping);
		List<SystemProfiles> returnList = new ArrayList<>();
		returnList = namedParameterJdbcTemplate.query(sql, createParams(), SystemProfilesRowMapper);
		return returnList;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<TransactionOperation> cgfkOffTxnSubAccountTypeRecordGroup(final String caseloadId) {
		final String sql = getQuery("OCDPAYOB_FIND_CGFKOFFTXNSUBACCOUNTTYPE");
		final RowMapper<TransactionOperation> trnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				TransactionOperation.class, TrnOperationMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams("CASELOADID", caseloadId), trnRowMapper);
		} catch (Exception e) {
			logger.error("cgfkOffTxnSubAccountTypeRecordGroup : ", e);
			return Collections.emptyList();
		}
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfkchkOffTxnOffTxnRef
	 *
	 * @param params
	 *
	 */
	public Object cgfkchkOffTxnOffTxnRef(final TransactionOperations paramBean) {
		final String sql = getQuery("OCDPAYOB_CGFKCHK_OFF_TXN_OFF_TXN_REF_C");
		final RowMapper<TransactionOperations> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				TransactionOperations.class, transactionOperationsMapping);
		final TransactionOperations returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(),
				columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfkchkOffBncOffBncCorp
	 *
	 * @param params
	 *
	 */
	public Corporates cgfkchkOffBncOffBncCorp(final OffenderBeneficiaries paramBean) {
		final String sql = getQuery("OCDPAYOB_CGFKCHK_OFF_BNC_OFF_BNC_CORP");
		final RowMapper<Corporates> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, Corporates.class,
				corporatesMapping);
		Corporates returnObj = new Corporates();
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("CORPORATEID", paramBean.getCorporateId()), columnRowMapper);
		} catch (Exception e) {
			return returnObj;
		}
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfkchkOffBncOffBncPer
	 *
	 * @param params
	 *
	 */
	public Persons cgfkchkOffBncOffBncPer(final OffenderBeneficiaries paramBean) {
		final String sql = getQuery("OCDPAYOB_CGFKCHK_OFF_BNC_OFF_BNC_PER_F");
		final RowMapper<Persons> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, Persons.class, personsMapping);
		Persons returnObj = new Persons();
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("PERSONID", paramBean.getPersonId()), columnRowMapper);
		} catch (Exception e) {
			return returnObj;
		}
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfkchkOffBncOffBncOff
	 *
	 * @param params
	 *
	 */
	public OffenderDeductions cgfkchkOffBncOffBncOff(final OffenderBeneficiaries paramBean) {
		final String sql = getQuery("OCDPAYOB_CGFKCHK_OFF_BNC_OFF_BNC_OFF_D");
		final RowMapper<OffenderDeductions> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderDeductions.class, offenderDeductionsMapping);
		OffenderDeductions returnObj = new OffenderDeductions();
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("OFFENDERDEDUCTIONID", paramBean.getOffenderDeductionId()), columnRowMapper);
		} catch (Exception e) {
			return returnObj;
		}
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * offBncPostQuery
	 *
	 * @param paramBean
	 *
	 */
	public OffenderDeductions offBncPostQuery(final OffenderBeneficiaries paramBean) {
		final String sql = getQuery("OCDPAYOB_POST_QUERY");
		final RowMapper<OffenderDeductions> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderDeductions.class, offenderDeductionsMapping);
		OffenderDeductions returnObj = new OffenderDeductions();
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("OFFENDER_DEDUCTION_ID", paramBean.getOffenderDeductionId()), columnRowMapper);
		} catch (Exception e) {
			return returnObj;
		}
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * offBncWriteOffCur
	 *
	 * @param paramBean
	 *
	 */
	public OffenderDeductions offBncWriteOffCur(final OffenderBeneficiaries paramBean) {
		final String sql = getQuery("OCDPAYOB_WRITE_OFF_CUR");
		final RowMapper<OffenderDeductions> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderDeductions.class, offenderDeductionsMapping);
		OffenderDeductions returnObj = new OffenderDeductions();
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("OFFENDER_DEDUCTION_ID", paramBean.getOffenderDeductionId()), columnRowMapper);
		} catch (Exception e) {
			return returnObj;
		}
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * offBncVmonAmt
	 *
	 * @param paramBean
	 *
	 */
	public BigDecimal offBncVmonAmt(final OffenderBeneficiaries paramBean) {
		final String sql = getQuery("OCDPAYOB_V_MON_AMT");
		BigDecimal returnObj = null;
		try {
			returnObj = namedParameterJdbcTemplate
					.queryForObject(sql,
							createParams("OFFENDER_DEDUCTION_ID", paramBean.getOffenderDeductionId(), "CORPORATE_ID",
									paramBean.getCorporateId(), "PERSON_ID", paramBean.getPersonId()),
							BigDecimal.class);
		} catch (Exception e) {
			return returnObj;
		}
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * offBncVrecMAx
	 *
	 * @param paramBean
	 *
	 */
	public BigDecimal offBncVrecMAx(final OffenderBeneficiaries paramBean) {
		final String sql = getQuery("OCDPAYOB_V_REC_MAX");
		BigDecimal returnObj = null;
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("OFFENDER_DEDUCTION_ID", paramBean.getOffenderDeductionId()), BigDecimal.class);
		} catch (Exception e) {
			return returnObj;
		}
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * getCurrentBalance
	 *
	 * @param paramBean
	 *
	 */
	public BigDecimal getCurrentBalance(final OffenderTransactions searchBean) {
		final String sql = getQuery("OCDPAYOB_V_BALANCE");
		BigDecimal returnObj = BigDecimal.ZERO;
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams("subAccountType", searchBean.getSubAccountType(),
					"caseloadType", searchBean.getCaseloadType(), "caseloadId", searchBean.getCaseloadId(), "rootOffenderId", searchBean.getRootOffenderId()),
					BigDecimal.class);
		} catch (Exception e) {
			returnObj = BigDecimal.ZERO;
		}
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 *
	 * @return Integer
	 */
	public Integer txnIdNextValData() {
		final String sql = getQuery("OCDPAYOB_TXN_ID");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams(), Integer.class);
	}

	@Override
	public Integer financialDoDuctionsFinancial(final String caseloadId, final BigDecimal offenderId,
			final Long offenderBookId, final String txnType, final Integer txnIdNextVal, final Date transDate,
			final String subAccountType, final String string, final BigDecimal txnEntryAmount, final Integer pDedAmt,
			final Integer pTxnEntrySeq) {
		Integer genSeq = 0;
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		inParamMap.put("P_CSLD_ID", caseloadId);
		inParamMap.put("P_OFF_ID", offenderId);
		inParamMap.put("P_OFF_BOOK_ID", offenderBookId);
		inParamMap.put("P_TRANS_TYPE", txnType);
		inParamMap.put("P_TRANS_NUMBER", txnIdNextVal);
		inParamMap.put("P_TRANS_DATE", transDate);
		inParamMap.put("P_SUB_ACT_TYPE", subAccountType);
		inParamMap.put("P_DED_FLAG", string);
		inParamMap.put("P_RECEIPT_AMOUNT", txnEntryAmount);
		inParamMap.put("P_SHADOW_ID", null);
		inParamMap.put("P_DED_AMOUNT", pDedAmt);
		inParamMap.put("TXN_SEQUENCE", pTxnEntrySeq);
		inParamMap.put("P_INFO_NUMBER", null);
		try {
			namedParameterJdbcTemplate
					.update("call OMS_OWNER.FINANCIAL.DO_DEDUCTIONS_FINANCIAL(:P_CSLD_ID, :P_OFF_ID, :P_OFF_BOOK_ID, :P_TRANS_TYPE, "
							+ ":P_TRANS_NUMBER, :P_TRANS_DATE, :P_SUB_ACT_TYPE, :P_DED_FLAG, :P_RECEIPT_AMOUNT, :P_SHADOW_ID, :P_DED_AMOUNT, "
							+ ":TXN_SEQUENCE, :P_INFO_NUMBER)", inParamMap);
			genSeq = 1;
		} catch (Exception e) {
			logger.error("financialDoDuctionsFinancial :" + e);
			genSeq = 0;
			return genSeq;
		}
		return genSeq;
	}

	/**
	 * This method will update the transaction in the table
	 * 
	 * @param offTrans
	 * @return Integer
	 */
	public String getAcAndSetIndDate(final BigDecimal offenderId, final String caseloadId) {
		String openAnAccount = null;
		Map<String, Object> inParamMap = new HashMap<String, Object>();
		inParamMap.put("p_csld_id", caseloadId);
		inParamMap.put("p_off_id", offenderId);
		try {
			namedParameterJdbcTemplate
					.update("call OMS_OWNER.DEDUCTIONS.GET_AC_AND_SET_IND_DATE(:p_off_id, :p_csld_id)", inParamMap);
			openAnAccount = "GETINDDATE";
		} catch (Exception e) {
			logger.error("getAcAndSetIndDate :" + e);
		}
		return openAnAccount;
	}

	@Override
	public Integer glTxnsCount(final Integer txnId) {
		final String sql = getQuery("OCDPAYOB_TXN_ID_COUNT");
		Integer returnObj = null;
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams("TXNID", txnId), Integer.class);
		} catch (Exception e) {
			return returnObj;
		}
		return returnObj;
	}

	public Integer updateOffenderBeneficiaries(final OffenderBeneficiaries objOffBen) {
		final String sql = getQuery("OCDPAYOB_OFFBNC_UPDATE_OFFENDER_BENEFICIARIES");
		Map<String, Object> inParamMap = new HashMap<String, Object>();
		inParamMap.put("OVERRIDEAMOUNT", objOffBen.getOverrideAmount());
		inParamMap.put("OFFENDERDEDUCTIONID", objOffBen.getOffenderDeductionId());
		inParamMap.put("OFFENDERID", objOffBen.getOffenderId());
		inParamMap.put("BENEFICIARYID", objOffBen.getBeneficiaryId());
		inParamMap.put("modifyUserId", objOffBen.getCreateUserId());
		Integer returnArray = null;
		returnArray = namedParameterJdbcTemplate.update(sql, inParamMap);
		if (returnArray != 0) {
			return returnArray;
		}
		return returnArray;
	}

	public Integer updateOffenderBeneficiariesOverRidden(final OffenderBeneficiaries objOffBen) {
		final String sql = getQuery("OCDPAYOB_OFFBNC_UPDATE_OFFENDER_BENEFICIARIES_OVERRIDDEN");
		Map<String, Object> inParamMap = new HashMap<String, Object>();
		inParamMap.put("OFFENDERDEDUCTIONID", objOffBen.getOffenderDeductionId());
		inParamMap.put("OFFENDERID", objOffBen.getOffenderId());
		inParamMap.put("BENEFICIARYID", objOffBen.getBeneficiaryId());
		inParamMap.put("modifyUserId", objOffBen.getModifyUserId());
		Integer returnArray = null;
		returnArray = namedParameterJdbcTemplate.update(sql, inParamMap);
		if (returnArray != 0) {
			return returnArray;
		}
		return returnArray;
	}

	public String txnTyepOffTxns(final String subAccountType, final String caseloadType, final String caseloadId) {
		final String sql = getQuery("OCDPAYOB_TXN_TYPE");
		String returnObj = null;
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams("SUBACCOUNTTYPE", subAccountType,
					"CASELOADTYPE", caseloadType, "CASELOADID", caseloadId), String.class);
		} catch (Exception e) {
			return returnObj;
		}
		return returnObj;
	}
	
	@Override
	public String getOffenderFeesEnableBtn() {
		final String sql = getQuery("OCDPAYOB_OFFENDER_FEES_SECTION");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams(), String.class);
	}

	@Override
	public List<OffFeeBillTransactions> offFeeExecuteQuery(OffFeeBillTransactions serachBean) {
		final String sql = getQuery("OCDPAYOB_OFF_FEE_BILL_TRN_DATA");
		final RowMapper<OffFeeBillTransactions> feeBillTrnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffFeeBillTransactions.class, offFeeMapping);
		return namedParameterJdbcTemplate.query(sql, createParams("OFFENDER_BOOK_ID", serachBean.getOffenderBookId()),
				feeBillTrnRowMapper);
	}

	@Override
	public Integer offFeesBillTransactionUpdate(List<OffFeeBillTransactions> updateList) {
		final String sql = getQuery("OCDPAYOB_OFF_FEE_BILL_TRANSACTIONS_UPDATE");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffFeeBillTransactions obj : updateList) {
			parameters.add(new BeanPropertySqlParameterSource(obj));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (updateList.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	@Override
	public Integer getstaffId(String userId) {
		final String sql = getQuery("OCDBRECI_GET_CURRENT_STAFFDETAILS");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("userId", userId), Integer.class);
	}

	public Integer getBillTranId(final String billId) {
		final String sql = getQuery("OCDPAYOB_OFF_FEE_BILLS_TRANSACTIONS_PRE_INSERT");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("BILL_ID", billId), Integer.class);
	}

	@Override
	public Integer insertOffenderFees(OffFeeBillTransactions bean) {
		final String sql = getQuery("OCDPAYOB_OFF_BILL_TRN_INSERT");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<>();
		parameters.add(new BeanPropertySqlParameterSource(bean));
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error("insertOffenderFees ", e);
		}
		if (returnArray.length == 1) {
			return 1;
		} else {
			return 0;
		}

	}

	@Override
	public BigDecimal getCrDeductAcntCode(String dedType, String caseLoad) {
		final String sql = getQuery("OCDPAYOB_GET_CR_DEDUCT_TO");
		return namedParameterJdbcTemplate.queryForObject(sql,
				createParams("deductionType", dedType, "caseLoadId", caseLoad), BigDecimal.class);
	}

	@Override
	public Integer insertIntoOffenderTransaction(OffenderTransactions offTran) {
		final String sql = getQuery("OCDPAYOB_INSERT_INTO_OFFENDER_TRANSACTIONS");
		final Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("P_trans_number", offTran.getTxnId());
		paramMap.put("P_trans_seq", offTran.getTxnEntrySeq());
		paramMap.put("P_csld_id", offTran.getCaseloadId());
		paramMap.put("P_off_id", offTran.getRootOffenderId());
		paramMap.put("P_off_book_id", offTran.getOffenderBookId());
		paramMap.put("P_trans_post_type", offTran.getTxnPostingType());
		paramMap.put("P_trans_type", offTran.getTxnType());
		paramMap.put("p_trans_desc", offTran.getTxnEntryDesc());
		paramMap.put("p_trans_amount", offTran.getTxnEntryAmount());
		paramMap.put("P_trans_date", offTran.getTxnEntryDate());
		paramMap.put("p_sub_act_type", offTran.getSubAccountType());
		paramMap.put("p_slip_print_flag", "N");
		paramMap.put("p_pre_ded_amount", null);
		paramMap.put("p_deduction_flag", offTran.getDeductionFlag());
		paramMap.put("p_payee_code", null);
		paramMap.put("p_payee_corp_id", null);
		paramMap.put("p_payee_person_id", null);
		paramMap.put("p_payee_name_text", null);
		paramMap.put("p_deduction_type", null);
		paramMap.put("p_info_number", null);
		paramMap.put("p_remitter_name", null);
		paramMap.put("p_remitter_id", null);
		paramMap.put("p_receipt_number", offTran.getReceiptNumber());
		paramMap.put("p_txn_reference_number", offTran.getTxnReferenceNumber());
		paramMap.put("userId", offTran.getCreateUserId());
		paramMap.put("p_remitter_id", null);
		Integer returnArray = null;
		returnArray = namedParameterJdbcTemplate.update(sql, paramMap);
		if (returnArray != 0) {
			returnArray = 1;
			return returnArray;
		}
		return returnArray;

	}

	@Override
	public Integer getDrAccountCode(final String billTxnType, final String caseloadId) {
		String sql = getQuery("OCDPAYOB_GET_DR_ACCOUNT_CODE");
		Integer val = 0;
		try {
			val = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("txnType", billTxnType, "caseloadId", caseloadId), Integer.class);
		} catch (Exception e) {
			logger.error("getDrAccountCode ::" + e);
		}
		return val;
	}

	@Override
	public String getSubAccountTypeDesc(String txnType) {
		String sql = getQuery("OCDPAYOB_GET_SUB_ACCOUNT_DESC");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("txnType", txnType), String.class);
	}

	@Override
	public Integer trustInsertGltransNew(final OffenderTransactions offTxnObj) {
		Integer genSequence = 0;
		SqlParameter[] sqlParameters = new SqlParameter[] {

				new SqlParameter("p_post_type", OracleTypes.VARCHAR),
				new SqlParameter("p_account_code", OracleTypes.NUMBER),
				new SqlParameter("p_acnt_posting", OracleTypes.VARCHAR),
				new SqlParameter("p_csld_id", OracleTypes.VARCHAR),
				new SqlParameter("p_trans_type", OracleTypes.VARCHAR),
				new SqlParameter("p_trans_amount", OracleTypes.NUMBER),
				new SqlParameter("p_trans_number", OracleTypes.NUMBER),
				new SqlParameter("p_trans_date", OracleTypes.DATE),
				new SqlParameter("p_trans_desc", OracleTypes.VARCHAR),
				new SqlParameter("p_trans_seq", OracleTypes.NUMBER), new SqlParameter("p_gl_sqnc", OracleTypes.NUMBER),
				new SqlParameter("p_off_id", OracleTypes.NUMBER), new SqlParameter("p_off_book_id", OracleTypes.NUMBER),
				new SqlParameter("p_info_number", OracleTypes.VARCHAR),
				new SqlParameter("p_payee_person_id", OracleTypes.NUMBER),
				new SqlParameter("p_payee_corporate_id", OracleTypes.NUMBER),
				new SqlParameter("p_payee_name_text", OracleTypes.VARCHAR),
				new SqlParameter("p_revr_txn_id", OracleTypes.NUMBER),
				new SqlParameter("p_revr_txn_entry_seq", OracleTypes.NUMBER),
				new SqlParameter("p_revr_gl_entry_seq", OracleTypes.NUMBER),
				new SqlParameter("p_txn_ref_number", OracleTypes.VARCHAR),
				new SqlParameter("p_off_ded_id", OracleTypes.NUMBER), };
		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("TRUST").withProcedureName("INSERT_GL_TRANS_NEW").declareParameters(sqlParameters);
		Map<String, Object> inParamMap = new HashMap<String, Object>();
		inParamMap.put("p_post_type", offTxnObj.getTxnPostingType());
		inParamMap.put("p_account_code", offTxnObj.getAccountCode());
		inParamMap.put("p_acnt_posting", offTxnObj.getTxnPostUsage());
		inParamMap.put("p_csld_id", offTxnObj.getCaseloadId());
		inParamMap.put("p_trans_type", "DEDC");
		inParamMap.put("p_trans_amount", offTxnObj.getTxnEntryAmount());
		inParamMap.put("p_trans_number", offTxnObj.getTxnId());
		inParamMap.put("p_trans_date", offTxnObj.getTxnEntryDate());
		inParamMap.put("p_trans_desc", offTxnObj.getTxnEntryDesc());
		inParamMap.put("p_trans_seq", offTxnObj.getTxnEntrySeq());
		inParamMap.put("p_gl_sqnc", offTxnObj.getGlEntrySeq());
		inParamMap.put("p_off_id", offTxnObj.getOffenderId());
		inParamMap.put("p_off_book_id", offTxnObj.getOffenderBookId());
		inParamMap.put("p_info_number", null);
		inParamMap.put("p_payee_person_id", null);
		inParamMap.put("p_payee_corporate_id", null);
		inParamMap.put("p_payee_name_text", null);
		inParamMap.put("p_revr_txn_id", null);
		inParamMap.put("p_revr_txn_entry_seq", null);
		inParamMap.put("p_revr_gl_entry_seq", null);
		inParamMap.put("p_txn_ref_number", null);
		inParamMap.put("p_off_ded_id", null);
		final MapSqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);

		try {
			simpleJDBCCall.execute(inParameter);
			genSequence = 1;
		} catch (Exception e) {
			logger.error("trustInsertGltransNew :" + e);
			genSequence = 0;
			return genSequence;
		}
		return genSequence;
	}

	@Override
	public OffenderTransactions updateOffenderBalance(OffenderTransactions offTxnObj) throws Exception {
		try {
			final SqlParameter[] sqlParameters = new SqlParameter[] {
					new SqlParameter("p_csld_id", OracleTypes.VARCHAR),
					new SqlParameter("p_off_id", OracleTypes.NUMBER),
					new SqlParameter("p_trans_post_type", OracleTypes.VARCHAR),
					new SqlParameter("p_trans_date", OracleTypes.DATE),
					new SqlParameter("p_trans_number", OracleTypes.NUMBER),
					new SqlParameter("p_trans_type", OracleTypes.VARCHAR),
					new SqlParameter("p_trans_amount", OracleTypes.NUMBER),
					new SqlParameter("p_sub_act_type", OracleTypes.VARCHAR),
					new SqlParameter("p_allow_overdrawn", OracleTypes.VARCHAR), };
			final SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
					.withCatalogName("TRUST").withProcedureName("UPDATE_OFFENDER_BALANCE")
					.declareParameters(sqlParameters);
			final Map<String, Object> inParamMap = new HashMap<>();
			inParamMap.put("p_csld_id", offTxnObj.getCaseloadId());
			inParamMap.put("p_off_id", offTxnObj.getRootOffenderId());
			inParamMap.put("p_trans_post_type", offTxnObj.getTxnPostingType());
			inParamMap.put("p_trans_date", offTxnObj.getTxnEntryDate());
			inParamMap.put("p_trans_number", offTxnObj.getTxnId());
			inParamMap.put("p_trans_type", offTxnObj.getTxnType());
			inParamMap.put("p_trans_amount", offTxnObj.getTxnEntryAmount());
			inParamMap.put("p_sub_act_type", offTxnObj.getSubAccountType());
			inParamMap.put("p_allow_overdrawn", "N");
			final MapSqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
			simpleJDBCCall.execute(inParameter);
		} catch (final Exception e) {
			offTxnObj.setSealFlag("9");
			// Other Error in TRUST: ' || SQLERRM
			throw new Exception("UPDATE_OFFENDER_BALANCE");
		}
		return offTxnObj;
	}

	@Override
	public Integer getMaxTxnEntrySeq(Long offenderId, Integer txnInd) {
		final String sql = getQuery("OCDPAYOB_GET_MAX_TXN_ENTRY_SEQ");
		Integer retVal = 0;
		try {
			retVal = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("offenderId", offenderId, "txnId", txnInd), Integer.class);
		} catch (Exception e) {
			logger.error("getMaxTxnEntrySeq ::" + e);
		}
		return retVal;
	}

	@Override
	public Integer getStaffID() {
		final String sql = getQuery("OCDPAYOB_GET_STAFF_ID");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams(), Integer.class);
	}

	@Override
	public Integer offStmtCommit(offBillingStatements insertList) {
		String sql = getQuery("OCDPAYOB_OFF_BILL_STMT_INSERT_QUERY");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<>();
		parameters.add(new BeanPropertySqlParameterSource(insertList));
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (returnArray.length == 1) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public Integer offFeeBillsUpdate(offBillingStatements updateBean) {
		String sql = getQuery("OCDPAYOB_UPDATE_OFF_FEE_BILLS");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<>();
		parameters.add(new BeanPropertySqlParameterSource(updateBean));
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (returnArray.length == 1) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public Integer getMaxGlEntrySeq(Long offenderId, Integer txnInd) {
		final String sql = getQuery("OCDPAYOB_GET_MAX_GL_ENTRY_SEQ");
		Integer retVal = 0;
		try {
			retVal = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("offenderId", offenderId, "txnId", txnInd), Integer.class);
		} catch (Exception e) {
			logger.error("getMaxGlEntrySeq ::" + e);
		}
		return retVal;
	}

	@Override
	public void updateOffFeeBillsDate(List<OffFeeBillTransactions> updateOffFeeBillsList) {
		final String sql = getQuery("OCDPAYOB_UPDATE_OFF_FEE_BILLS_DATE");
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (OffFeeBillTransactions obj : updateOffFeeBillsList) {
			parameters.add(new BeanPropertySqlParameterSource(obj));
		}
		namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
	}
	
	
	public OffenderBeneficiaries getBeneficiariesOldData(Long beneficiaryId) {
		final String sql = getQuery("OCDPAYOB_OFFENDER_BENEFICIARIES_OLD_DATA");
		OffenderBeneficiaries beneficiaries=new OffenderBeneficiaries();
		try {
			beneficiaries=namedParameterJdbcTemplate.queryForObject(sql, createParams("beneficiaryId",beneficiaryId), new BeanPropertyRowMapper<OffenderBeneficiaries>(OffenderBeneficiaries.class));
		}catch (Exception e) {
			logger.error(this.getClass().getName()+"getBeneficiariesOldData ::" + e);
		}
		return beneficiaries;
	}
}

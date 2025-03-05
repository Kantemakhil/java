package net.syscon.s4.inmate.trust.generalledger.impl;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
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
import net.syscon.s4.cf.deductions.beans.OffFeeBills;
import net.syscon.s4.cf.offendertransactions.OcdbreciRepository;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.OffenderCreditPriorPayments;
import net.syscon.s4.im.beans.OffenderTransactions;
import net.syscon.s4.inmate.beans.GlTransactions;
import net.syscon.s4.inmate.beans.OffenderBeneficiaries;
import net.syscon.s4.inmate.beans.OffenderDeductions;
import net.syscon.s4.inmate.beans.OffenderSubAccounts;
import net.syscon.s4.inmate.trust.generalledger.OtdglirtRepository;
import oracle.jdbc.OracleTypes;

/**
 * Class OtdglirtRepositoryImpl
 */
@Repository
public class OtdglirtRepositoryImpl extends RepositoryBase implements OtdglirtRepository {
	
	private static Logger logger = LogManager.getLogger(OtdglirtRepositoryImpl.class.getName());

	@Autowired
	private OcdbreciRepository ocdbreciRepository;
	
	private final Map<String, FieldMapper> systemProfilesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("PROFILE_CODE", 				new FieldMapper("profileCode"))
			.put("PROFILE_TYPE", 				new FieldMapper("profileType"))
			.put("CREATE_USER_ID", 				new FieldMapper("createUserId"))
			.put("SEAL_FLAG", 					new FieldMapper("sealFlag"))
			.put("CREATE_DATETIME", 			new FieldMapper("createDatetime"))
			.put("MODIFY_USER_ID", 				new FieldMapper("modifyUserId"))
			.put("OLD_TABLE_NAME", 				new FieldMapper("oldTableName"))
			.put("PROFILE_VALUE", 				new FieldMapper("profileValue"))
			.put("MODIFY_DATETIME", 			new FieldMapper("modifyDatetime"))
			.put("PROFILE_VALUE_2", 			new FieldMapper("profileValue2"))
			.put("DESCRIPTION", 				new FieldMapper("description"))
			.build();
	private final Map<String, FieldMapper> mMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CODE", 						new FieldMapper("code"))
			.put("DESCRIPTION", 				new FieldMapper("description"))
			.put("LIST_SEQ", 					new FieldMapper("listSeq"))
			.build();
	private final Map<String, FieldMapper> glTransactionsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("PROFILE_CODE", 				new FieldMapper("profileCode"))
			.put("PROFILE_TYPE", 				new FieldMapper("profileType"))
			.put("CREATE_USER_ID", 				new FieldMapper("createUserId"))
			.put("CREATE_DATETIME", 			new FieldMapper("createDatetime"))
			.put("MODIFY_USER_ID", 				new FieldMapper("modifyUserId"))
			.put("OLD_TABLE_NAME", 				new FieldMapper("oldTableName"))
			.put("PROFILE_VALUE", 				new FieldMapper("profileValue"))
			.put("MODIFY_DATETIME", 			new FieldMapper("modifyDatetime"))
			.put("PROFILE_VALUE_2", 			new FieldMapper("profileValue2"))
			.put("DESCRIPTION", 				new FieldMapper("description"))
			.put("OFFENDER_ID", 				new FieldMapper("offenderId"))
			.put("CASELOAD_ID", 				new FieldMapper("caseloadId"))
			.build();
	private final Map<String, FieldMapper> offenderTransactionsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("TXN_REFERENCE_NUMBER", 		new FieldMapper("txnReferenceNumber"))
			.put("INFO_NUMBER", 				new FieldMapper("infoNumber"))
			.put("APPLY_SPENDING_LIMIT_AMOUNT", new FieldMapper("applySpendingLimitAmount"))
			.put("TXN_ADJUSTED_FLAG", 			new FieldMapper("txnAdjustedFlag"))
			.put("ADJUST_TXN_ENTRY_ID", 		new FieldMapper("adjustTxnEntryId"))
			.put("PAYEE_NAME_TEXT", 			new FieldMapper("payeeNameText"))
			.put("RECEIPT_PRINTED_FLAG", 		new FieldMapper("receiptPrintedFlag"))
			.put("OFFENDER_ID", 				new FieldMapper("offenderId"))
			.put("HOLD_CLEAR_FLAG", 			new FieldMapper("holdClearFlag"))
			.put("GROSS_NET_FLAG", 				new FieldMapper("grossNetFlag"))
			.put("SEAL_FLAG", 					new FieldMapper("sealFlag"))
			.put("CREATE_DATETIME", 			new FieldMapper("createDatetime"))
			.put("HOLD_NUMBER", 				new FieldMapper("holdNumber"))
			.put("TXN_TYPE", 					new FieldMapper("txnType"))
			.put("GROSS_AMOUNT", 				new FieldMapper("grossAmount"))
			.put("RECEIPT_PENDING_PRINT_FLAG", 	new FieldMapper("receiptPendingPrintFlag"))
			.put("MODIFY_DATETIME", 			new FieldMapper("modifyDatetime"))
			.put("PAYEE_PERSON_ID", 			new FieldMapper("payeePersonId"))
			.put("ORG_TXN_TYPE", 				new FieldMapper("orgTxnType"))
			.put("TRANSFER_CASELOAD_ID", 		new FieldMapper("transferCaseloadId"))
			.put("TXN_ID", 						new FieldMapper("txnId"))
			.put("OFFENDER_BOOK_ID", 			new FieldMapper("offenderBookId"))
			.put("REMITTER_ID", 				new FieldMapper("remitterId"))
			.put("CREATE_USER_ID", 				new FieldMapper("createUserId"))
			.put("TXN_ENTRY_DATE", 				new FieldMapper("txnEntryDate"))
			.put("PAYEE_CODE", 					new FieldMapper("payeeCode"))
			.put("MODIFY_USER_ID", 				new FieldMapper("modifyUserId"))
			.put("TXN_ENTRY_AMOUNT", 			new FieldMapper("txnEntryAmount"))
			.put("ADJUST_OFFENDER_ID", 			new FieldMapper("adjustOffenderId"))
			.put("RECEIPT_NUMBER", 				new FieldMapper("receiptNumber"))
			.put("ADJUST_TXN_ID", 				new FieldMapper("adjustTxnId"))
			.put("CLOSING_CHEQUE_NUMBER", 		new FieldMapper("closingChequeNumber"))
			.put("HOLD_UNTIL_DATE", 			new FieldMapper("holdUntilDate"))
			.put("SLIP_PRINTED_FLAG", 			new FieldMapper("slipPrintedFlag"))
			.put("ADJUST_ACCOUNT_CODE", 		new FieldMapper("adjustAccountCode"))
			.put("CASELOAD_ID", 				new FieldMapper("caseloadId"))
			.put("MODIFY_DATE", 				new FieldMapper("modifyDate"))
			.put("DEDUCTION_FLAG", 				new FieldMapper("deductionFlag"))
			.put("TXN_ENTRY_SEQ", 				new FieldMapper("txnEntrySeq"))
			.put("DEDUCTION_TYPE", 				new FieldMapper("deductionType"))
			.put("REMITTER_NAME", 				new FieldMapper("remitterName"))
			.put("PAYEE_CORPORATE_ID", 			new FieldMapper("payeeCorporateId"))
			.put("SUB_ACCOUNT_TYPE", 			new FieldMapper("subAccountType"))
			.put("TXN_ENTRY_DESC", 				new FieldMapper("txnEntryDesc"))
			.put("PRE_WITHHOLD_AMOUNT", 		new FieldMapper("preWithholdAmount"))
			.put("TXN_POSTING_TYPE", 			new FieldMapper("txnPostingType"))
			.put("PROFILE_CODE", 				new FieldMapper("profileCode"))
			.put("PROFILE_TYPE", 				new FieldMapper("profileType"))
			.put("OLD_TABLE_NAME", 				new FieldMapper("oldTableName"))
			.put("PROFILE_VALUE", 				new FieldMapper("profileValue"))
			.put("PROFILE_VALUE_2", 			new FieldMapper("profileValue2"))
			.put("DESCRIPTION", 				new FieldMapper("description"))
			.put("OFF_TXN_TYPE", 				new FieldMapper("offTxnType"))
			.put("OFF_DEDUCTION_ID", 			new FieldMapper("offDeductionId"))
			.build();
	private final Map<String, FieldMapper> offenderDeductionsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("MAX_TOTAL_AMOUNT", 			new FieldMapper("maxTotalAmount"))
			.put("DEDUCTION_AMOUNT", 			new FieldMapper("deductionAmount"))
			.put("BALANCE", 					new FieldMapper("balance"))
			.put("IND_DATE", 					new FieldMapper("indDate"))
			.put("DEDUCTION_ID", 				new FieldMapper("deductionId"))
			.build();
	private final Map<String, FieldMapper> OffenderCreditPriorPaymentsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("PAYMENT_AMOUNT", 				new FieldMapper("paymentAmount"))
			.put("LOCATION", 					new FieldMapper("location"))
			.put("TXN_ID", 						new FieldMapper("txnId"))
			.put("OFFENDER_ID", 				new FieldMapper("offenderId"))
			.put("CASELOAD_ID", 				new FieldMapper("caseloadId"))
			.put("PAYMENT_DATE", 				new FieldMapper("paymentDate"))
			.put("TXN_ENTRY_SEQ", 				new FieldMapper("txnEntrySeq"))
			.build();
	
	private final Map<String, FieldMapper> offFeeMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("FEECODE", new FieldMapper("feeCode")).build();

	/**
	 * Creates new OtdglirtRepositoryImpl class Object
	 */
	public OtdglirtRepositoryImpl() {
		// OtdglirtRepositoryImpl
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            GlTransactions
	 *
	 * @return List<GlTransactions>
	 *
	 * @throws SQLException
	 */
	public List<GlTransactions> glTxnExecuteQuery(final GlTransactions objSearchDao) {
		final String sql = getQuery("OTDGLIRT_GLTXN_FIND_GL_TRANSACTIONS");
		String preparedSql = null;
		final StringBuffer sqlQuery = new StringBuffer();
		String preSqlQuery = null;
		final MapSqlParameterSource params = new MapSqlParameterSource();
		sqlQuery.append(sql);
		if (objSearchDao != null) {
			sqlQuery.append(" where ");
			if (objSearchDao != null && objSearchDao.getTxnEntryDate() != null) {
				sqlQuery.append(" :txnEntryDate  = TO_CHAR(TXN_ENTRY_DATE ,'MM/DD/YYYY')  " + " and");
				params.addValue("txnEntryDate",
						new SimpleDateFormat("MM/dd/yyyy").format(objSearchDao.getTxnEntryDate()));
			}
			if (objSearchDao != null && objSearchDao.getTxnEntryTime() != null) {
				sqlQuery.append(" TXN_ENTRY_TIME  = :txnEntryTime  " + " and");
				params.addValue("txnEntryTime", objSearchDao.getTxnEntryTime());
			}
			if (objSearchDao != null && objSearchDao.getTxnId() != null) {
				sqlQuery.append("  TXN_ID  = :txnId " + " and");
				params.addValue("txnId", objSearchDao.getTxnId());
			}
			if (objSearchDao != null && objSearchDao.getTxnEntrySeq() != null) {
				sqlQuery.append("  TXN_ENTRY_SEQ  = :txnEntrySeq " + " and");
				params.addValue("txnEntrySeq", objSearchDao.getTxnEntrySeq());
			}
			if (objSearchDao != null && objSearchDao.getAccountCode() != null) {
				sqlQuery.append("  ACCOUNT_CODE  = :accountCode " + " and");
				params.addValue("accountCode", objSearchDao.getAccountCode());
			}
			if (objSearchDao != null && objSearchDao.getOffenderId() != null) {
				sqlQuery.append("  OFFENDER_ID  = :offenderId " + " and");
				params.addValue("offenderId", objSearchDao.getOffenderId());
			}
			if (objSearchDao != null && objSearchDao.getTxnEntryAmount() != null) {
				sqlQuery.append("  TXN_ENTRY_AMOUNT  = :txnEntryAmount " + " and");
				params.addValue("txnEntryAmount", objSearchDao.getTxnEntryAmount());
			}
			if (objSearchDao != null && objSearchDao.getInfoNumber() != null) {
				sqlQuery.append("  INFO_NUMBER  = :infoNumber " + " and ");
				params.addValue("infoNumber", objSearchDao.getInfoNumber());
			}
			if (objSearchDao != null && objSearchDao.getReceiptNumber() != null) {
				sqlQuery.append("  RECEIPT_NUMBER  = :receiptNumber " + " and ");
				params.addValue("receiptNumber", objSearchDao.getReceiptNumber());
			}
			sqlQuery.append(" CASELOAD_ID = (SELECT WORKING_CASELOAD_ID FROM STAFF_MEMBERS WHERE USER_ID = :USER) ");
			params.addValue("USER", objSearchDao.getCreateUserId());
		}
		preparedSql = sqlQuery.toString().trim();
		if (preparedSql.endsWith("where")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 5);
		}
		if (preparedSql.endsWith("and")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 3);
		}
		//preSqlQuery = preparedSql.concat(" ORDER BY TXN_ENTRY_DATE, TXN_ID, TXN_ENTRY_SEQ, TXN_POST_USAGE DESC ");
		preSqlQuery = preparedSql.concat("order by TXN_ENTRY_DATE, TXN_ID, TXN_ENTRY_SEQ, TXN_POST_USAGE desc )"
				+ "A left join OFFENDER_TRANSACTIONS B on A.TXN_ID = B.TXN_ID and A.TXN_ENTRY_SEQ = B.TXN_ENTRY_SEQ "
				+ "left join ( select distinct OFFENDER_ID_DISPLAY, ROOT_OFFENDER_ID from V_TRUST_HEADER_A VTHA) C "
				+ "on B.OFFENDER_ID = C.ROOT_OFFENDER_ID");
		final RowMapper<GlTransactions> GlTransactionsRowMapper = Row2BeanRowMapper.makeMapping(sql,
				GlTransactions.class, glTransactionsMapping);
		List<GlTransactions> returnList = new ArrayList<>();
		returnList = namedParameterJdbcTemplate.query(preSqlQuery, params, GlTransactionsRowMapper);
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
	 * @throws SQLException
	 */
	public List<GlTransactions> glTxnOneExecuteQuery(final GlTransactions objSearchDao) {
		final String sql = getQuery("OTDGLIRT_GLTXN_FIND_GL_TRANSACTIONS_ONE");
		final RowMapper<GlTransactions> GlTransactionsRowMapper = Row2BeanRowMapper.makeMapping(sql,
				GlTransactions.class, glTransactionsMapping);
		List<GlTransactions> returnList = new ArrayList<>();
		returnList = namedParameterJdbcTemplate.query(sql,
				createParams("TXNID", objSearchDao.getTxnId(), "TXNENTRYSEQ", objSearchDao.getTxnEntrySeq()),
				GlTransactionsRowMapper);
		return returnList;
	}

	/**
	 * This method is used to insert the records in the data base tables based
	 * on
	 *
	 * @param lstSystemProfiles
	 *            List<SystemProfiles>
	 *
	 * @return List<Integer>
	 *
	 * @throws SQLException
	 */
	public Integer glTxnInsertSystemProfiles(final List<GlTransactions> lstGlTransactions) {
		String sql = getQuery("OTDGLIRT_SYSPFL_INSERT_SYSTEM_PROFILES");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (GlTransactions glTransactions : lstGlTransactions) {
			parameters.add(new BeanPropertySqlParameterSource(glTransactions));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstGlTransactions.size() == returnArray.length) {
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
	 * @throws SQLException
	 */
	public List<SystemProfiles> sysPflExecuteQuery(final SystemProfiles objSearchDao) {
		final String sql = getQuery("OTDGLIRT_SYSPFL_FIND_SYSTEM_PROFILES");
		final RowMapper<SystemProfiles> SystemProfilesRowMapper = Row2BeanRowMapper.makeMapping(sql,
				SystemProfiles.class, systemProfilesMapping);
		List<SystemProfiles> returnList = new ArrayList<>();
		returnList = namedParameterJdbcTemplate.query(sql, createParams(), SystemProfilesRowMapper);
		return returnList;
	}

	/**
	 * This method is used to insert the records in the data base tables based
	 * on
	 *
	 * @param lstSystemProfiles
	 *            List<SystemProfiles>
	 *
	 * @return List<Integer>
	 *
	 * @throws SQLException
	 */
	public Integer sysPflInsertSystemProfiles(final List<SystemProfiles> lstSystemProfiles) {
		String sql = getQuery("OTDGLIRT_SYSPFL_INSERT_SYSTEM_PROFILES");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (SystemProfiles systemProfiles : lstSystemProfiles) {
			parameters.add(new BeanPropertySqlParameterSource(systemProfiles));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstSystemProfiles.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to delete records from data base tables based on
	 *
	 * @param lstSystemProfiles
	 *            List<SystemProfiles>
	 *
	 * @throws SQLException
	 */
	public Integer sysPflDeleteSystemProfiles(final List<SystemProfiles> lstSystemProfiles) {
		String sql = getQuery("OTDGLIRT_SYSPFL_DELETE_SYSTEM_PROFILES");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (SystemProfiles systemProfiles : lstSystemProfiles) {
			parameters.add(new BeanPropertySqlParameterSource(systemProfiles));
		}
		try {
			String tableName = "SYSTEM_PROFILES";
			String whereClause = null;
			batchUpdatePreDeletedRows(tableName, whereClause , parameters);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method sysPflDeleteSystemProfiles", e);
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstSystemProfiles.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<ReferenceCodes> cgfkGlTxnReversalReasonRecordGroup() {
		final String sql = getQuery("OTDGLIRT_FIND_CGFKGLTXNREVERSALREASON");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (Exception e) {
			return Collections.emptyList();
		}
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfkchkGlTxn2GlTxnAcCod
	 *
	 * @param params
	 *
	 */
	public String cgfkchkGlTxnGlTxnAcCod(final BigDecimal accountCode,String userId) {
		final String sql = getQuery("OTDGLIRT_CGFKCHK_GL_TXN_GL_TXN_AC_COD");
		String returnObj = "";
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams("ACCOUNTCODE", accountCode,"USER_ID",userId),
					String.class);
		} catch (Exception e) {
			return returnObj;
		}
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * receiptNumberQuery
	 *
	 * @param params
	 * @return String
	 *
	 */
	public GlTransactions receiptNumberQuery(final GlTransactions paramBean) {
		final String sql = getQuery("OTDGLIRT_RECEIPT_NUMBER");
		final RowMapper<GlTransactions> GlTransactionsRowMapper = Row2BeanRowMapper.makeMapping(sql,
				GlTransactions.class, glTransactionsMapping);
		GlTransactions returnObj = new GlTransactions();
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("TXNID", paramBean.getTxnId(), "TXNENTRYSEQ", paramBean.getTxnEntrySeq()),
					GlTransactionsRowMapper);
		} catch (Exception e) {
			return returnObj;
		}
		return returnObj;
	}
	
	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * receiptNumberQuery
	 *
	 * @param params
	 * @return String
	 *
	 */
	public GlTransactions receiptNumberQueryReturn(final GlTransactions paramBean) {
		final String sql = getQuery("OTDGLIRT_GETTING_RECEIPT_NUMBER_TXN_ID");
		final RowMapper<GlTransactions> GlTransactionsRowMapper = Row2BeanRowMapper.makeMapping(sql,
				GlTransactions.class, glTransactionsMapping);
		GlTransactions returnObj = new GlTransactions();
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("RECEIPTNUMBER", paramBean.getReceiptNumber()), GlTransactionsRowMapper);
		} catch (Exception e) {
			return returnObj;
		}
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * receiptNumberQuery
	 *
	 * @param params
	 * @return String
	 *
	 */
	public String offenderIdDisplayQuery(final GlTransactions paramBean) {
		final String sql = getQuery("OTDGLIRT_OFFENDER_ID_DISPLAY");
		String returnObj = null;
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("OFFENDERID", paramBean.getOffenderId()), String.class);
		} catch (Exception e) {
			return returnObj;
		}
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * receiptNumberQuery
	 *
	 * @param params
	 * @return String
	 *
	 */
	public List<GlTransactions> txnReversedFlagData(final Long txnId, final Long txnEntrySeq) {
		final String sql = getQuery("OTDGLIRT_TXN_REVERSED_FLAG_DATA");
		final RowMapper<GlTransactions> GlTransactionsRowMapper = Row2BeanRowMapper.makeMapping(sql,
				GlTransactions.class, glTransactionsMapping);
		List<GlTransactions> returnObj = new ArrayList<>();
		try {
			returnObj = namedParameterJdbcTemplate.query(sql, createParams("TXNID", txnId, "TXNENTRYSEQ", txnEntrySeq),
					GlTransactionsRowMapper);
		} catch (Exception e) {
			return returnObj;
		}
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * receiptNumberQuery
	 *
	 * @param params
	 * @return String
	 *
	 */
	public BigDecimal lvAdjustmentAmtData(final BigDecimal deductionId) {
		final String sql = getQuery("OTDGLIRT_ADJUSTMENT_AMT");
		BigDecimal returnObj = null;
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams("DEDUCTIONID", deductionId),
					BigDecimal.class);
		} catch (Exception e) {
			return returnObj;
		}
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * receiptNumberQuery
	 *
	 * @param params
	 * @return String
	 *
	 */
	public Integer transactionOperationsModule(final String txnType,String userName) {
		final String sql = getQuery("OTDGLIRT_OTUCOBWO_TRANSACTION_OPERATIONS");
		Integer returnObj = 0;
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams("TXNTYPE", txnType,"USER_ID",userName), Integer.class);
		} catch (Exception e) {
			return returnObj;
		}
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * receiptNumberQuery
	 *
	 * @param params
	 * @return String
	 *
	 */
	public String txnUsageData(final String txnType) {
		final String sql = getQuery("OTDGLIRT_TXN_USAGE");
		String returnObj = null;
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams("TXNTYPE", txnType), String.class);
		} catch (Exception e) {
			return returnObj;
		}
		return returnObj;
	}

	@Override
	public Integer whenNextbuttonClick(GlTransactions searchRecord) {
		return null;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * receiptNumberQuery
	 *
	 * @param params
	 * @return String
	 *
	 */
	public Integer transactionOperationsDataTest(final String txnType, final String caseloadId) {
		final String sql = getQuery("OTDGLIRT_TRANSACTION_OPERATIONS_OODOSALE_OODORETU");
		Integer returnObjTemp = 0;
		Integer returnObj = namedParameterJdbcTemplate.queryForObject(sql,
				createParams("TXNTYPE", txnType, "CASELOADID", caseloadId), Integer.class);
		if (returnObj > 0) {
			returnObjTemp = 1;
		} else {
			returnObjTemp = 0;
		}
		return returnObjTemp;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * receiptNumberQuery
	 *
	 * @param params
	 * @return String
	 *
	 */
	public Integer transactionOperationsOtdshift(final String txnType, final String caseloadId) {
		final String sql = getQuery("OTDGLIRT_TRANSACTION_OPERATIONS_OTDSHIFT");
		Integer returnObjTemp = 0;
		Integer returnObj = namedParameterJdbcTemplate.queryForObject(sql,
				createParams("TXNTYPE", txnType, "CASELOADID", caseloadId), Integer.class);
		if (returnObj > 0) {
			returnObjTemp = 1;
		} else {
			returnObjTemp = 0;
		}
		return returnObjTemp;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * receiptNumberQuery
	 *
	 * @param params
	 * @return String
	 *
	 */
	public String txnTypeHor(final String txnType, final String caseloadId) {
		final String sql = getQuery("OTDGLIRT_TXN_TYPE_OTDHIREM");
		String returnObj = null;
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("TXNTYPE", txnType, "CASELOADID", caseloadId), String.class);
		} catch (Exception e) {
			return returnObj;
		}
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * receiptNumberQuery
	 *
	 * @param params
	 * @return String
	 *
	 */
	public Long txnOffenderId(final Long txnId, final Long txnEntrySeq) {
		final String sql = getQuery("OTDGLIRT_OFFENDER_ID");
		Long returnObj = null;
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("TXNID", txnId, "TXNENTRYSEQ", txnEntrySeq), Long.class);
		} catch (Exception e) {
			return returnObj;
		}
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * receiptNumberQuery
	 *
	 * @param params
	 * @return String
	 *
	 */
	public String accountClosedFlag(final Long offId, final String caseloadId) {
		final String sql = getQuery("OTDGLIRT_ACCOUNT_CLOSED_FLAG");
		String returnObj = null;
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("OFFID", offId, "CASELOADID", caseloadId), String.class);
		} catch (Exception e) {
			return returnObj;
		}
		return returnObj;
	}

	@Override
	public Integer whenNextbuttonUpdates(final GlTransactions searchRecord) {
		final String sql = getQuery("REOPEN_CLOSED_TRUST_ACCOUNT");
		try {
			return namedParameterJdbcTemplate.update(sql, createParams("offenderId", searchRecord.getOffenderId(),
					"caseloadId", searchRecord.getCaseloadId()));
		} catch (Exception e) {
			return 0;
		}

	}

	@Override
	public BigDecimal maxOffenderBookId(final BigDecimal offId) {
		final String sql = getQuery("OTDGLIRT_MAX_OFFENDER_BOOK_ID");
		BigDecimal returnData = null;
		try {
			returnData = namedParameterJdbcTemplate.queryForObject(sql, createParams("OFFID", offId),
					BigDecimal.class);
		} catch (Exception e) {
			return returnData;
		}
		return returnData;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 *
	 * @return Integer
	 */
	public Integer txnIdNextValData() {
		final String sql = getQuery("OTDGLIRT_TXN_ID");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams(), Integer.class);
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * receiptNumberQuery
	 *
	 * @param params
	 * @return String
	 *
	 */
	public String txnType(String userId) {
		final String sql = getQuery("OTDGLIRT_TXN_TYPE_OTDOPCTA");
		String returnObj = null;
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams("USER_ID",userId), String.class);
		} catch (Exception e) {
			return returnObj;
		}
		return returnObj;
	}

	@Override
	public Map<String, Object> getSubActType(String moduleName, String txnType, String caseloadId) {
		String procedureQuery = "{CALL OMS_OWNER.TRUST.GET_SUB_ACT_TYPE(:P_MODULE_NAME, :P_TXN_TYPE, :P_TXN_POST_TYPE, :P_SUB_ACT_TYPE, :CSLD_ID)}";
		try {
			Connection connection = dataSource.getConnection();
			CallableStatement callableStatement = connection.prepareCall(procedureQuery);
			callableStatement.setString("P_MODULE_NAME", moduleName);
			callableStatement.setString("P_TXN_TYPE", txnType);
			callableStatement.registerOutParameter("P_TXN_POST_TYPE", Types.VARCHAR);
			callableStatement.registerOutParameter("P_SUB_ACT_TYPE", Types.VARCHAR);
			callableStatement.setString("CSLD_ID", caseloadId);
			callableStatement.execute();
			Map<String, Object> returnMapData = new HashMap<>();
			returnMapData.put("P_TXN_POST_TYPE", callableStatement.getString("P_TXN_POST_TYPE"));
			returnMapData.put("P_SUB_ACT_TYPE", callableStatement.getString("P_SUB_ACT_TYPE"));
			return returnMapData;
		} catch (Exception e) {
			logger.error("getSubActType", e);
		}
		return null;
	}

	public Integer insertIntoOffenderTransaction(final OffenderTransactions offTrans) {
		Integer genSeq = 0;
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		inParamMap.put("p_trans_number", offTrans.getTxnId());
		inParamMap.put("p_trans_seq", offTrans.getTxnEntrySeq());
		inParamMap.put("p_csld_id", offTrans.getCaseloadId());
		inParamMap.put("p_off_id", offTrans.getOffenderId());
		inParamMap.put("p_off_book_id", offTrans.getOffenderBookId());
		inParamMap.put("p_trans_post_type", offTrans.getTxnPostingType());
		inParamMap.put("p_trans_type", offTrans.getTxnType());
		inParamMap.put("p_trans_desc", offTrans.getTxnEntryDesc());
		inParamMap.put("p_trans_amount", new BigDecimal(0.0));
		inParamMap.put("p_trans_date", offTrans.getTxnEntryDate());
		inParamMap.put("p_sub_act_type", offTrans.getSubAccountType());
		inParamMap.put("p_deduction_flag", offTrans.getDeductionFlag());
		inParamMap.put("p_pre_ded_amount", offTrans.getPreWithholdAmount());
		inParamMap.put("p_deduction_type", offTrans.getDeductionType());
		inParamMap.put("p_payee_corp_id", offTrans.getPayeeCorporateId());
		inParamMap.put("p_payee_person_id", offTrans.getPayeePersonId());
		inParamMap.put("p_info_number", offTrans.getInfoNumber());
		inParamMap.put("p_slip_print_flag", "N");
		inParamMap.put("p_allow_overdrawn", offTrans.getPayeePersonId());
		try {
			namedParameterJdbcTemplate.update(
					" call OMS_OWNER.TRUST.INSERT_INTO_OFFENDER_TRANS(:p_trans_number, :p_trans_seq, :p_csld_id, :p_off_id, :p_off_book_id, :p_trans_post_type, "
							+ " :p_trans_type, :p_trans_desc, :p_trans_amount, :p_trans_date, :p_sub_act_type, :p_deduction_flag, :p_pre_ded_amount, :p_deduction_type, "
							+ " :p_payee_corp_id, :p_payee_person_id, :p_info_number, :p_slip_print_flag, :p_allow_overdrawn)",
					inParamMap);
			genSeq = 1;
		} catch (Exception e) {
			logger.error("insertIntoOffenderTransaction :" + e);
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
	@Override
	public Integer processGlTransNew(final OffenderTransactions offTrans) {
		Integer genSeq = 0;
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		inParamMap.put("P_CSLD_ID", offTrans.getCaseloadId());
		inParamMap.put("P_TRANS_TYPE", offTrans.getTxnType());
		inParamMap.put("P_OPERATION_TYPE", null);
		inParamMap.put("P_TRANS_AMOUNT", 0.0);
		inParamMap.put("P_TRANS_NUMBER", offTrans.getTxnId());
		inParamMap.put("P_TRANS_DATE", offTrans.getTxnEntryDate());
		inParamMap.put("P_TRANS_DESC", offTrans.getTxnEntryDesc());
		inParamMap.put("P_TRANS_SEQ", offTrans.getTxnEntrySeq());
		inParamMap.put("P_MODULE_NAME", "OTDOPCTA");
		inParamMap.put("P_OFF_ID", offTrans.getOffenderId());
		inParamMap.put("P_OFF_BOOK_ID", offTrans.getOffenderBookId());
		inParamMap.put("P_SUB_ACT_TYPE_DR", null);
		inParamMap.put("P_SUB_ACT_TYPE_CR", offTrans.getSubAccountType());
		inParamMap.put("P_PAYEE_PERS_ID", null);
		inParamMap.put("P_PAYEE_CORP_ID", null);
		inParamMap.put("P_PAYEE_NAME_TEXT", null);
		inParamMap.put("P_GL_SQNC", 0);
		inParamMap.put("P_OFF_DED_ID", null);
		namedParameterJdbcTemplate
				.update("call OMS_OWNER.TRUST.PROCESS_GL_TRANS_NEW(:P_CSLD_ID, :P_TRANS_TYPE, :P_OPERATION_TYPE, :P_TRANS_AMOUNT, :P_TRANS_NUMBER, :P_TRANS_DATE, :P_TRANS_DESC,"
						+ " :P_TRANS_SEQ, :P_MODULE_NAME, :P_OFF_ID, :P_OFF_BOOK_ID, :P_SUB_ACT_TYPE_DR, :P_SUB_ACT_TYPE_CR, :P_PAYEE_PERS_ID, :P_PAYEE_CORP_ID, :P_PAYEE_NAME_TEXT,"
						+ " :P_GL_SQNC, :P_OFF_DED_ID)", inParamMap);
		genSeq = 2;
		return genSeq;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * receiptNumberQuery
	 *
	 * @param params
	 * @return String
	 *
	 */
	public List<GlTransactions> findingDeductionId(final Long txnId, final Long txnEntrySeq) {
		final String sql = getQuery("OTDGLIRT_DEDUCTION_ID");
		final RowMapper<GlTransactions> GlTransactionsRowMapper = Row2BeanRowMapper.makeMapping(sql,
				GlTransactions.class, glTransactionsMapping);
		List<GlTransactions> returnObj = new ArrayList<>();
		try {
			returnObj = namedParameterJdbcTemplate.query(sql, createParams("TXNID", txnId, "TXNENTRYSEQ", txnEntrySeq),
					GlTransactionsRowMapper);
		} catch (Exception e) {
			return returnObj;
		}
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * receiptNumberQuery
	 *
	 * @param params
	 * @return String
	 *
	 */
	public OffenderTransactions findingDeductionType(final Long txnId, final Long txnEntrySeq) {
		final String sql = getQuery("OTDGLIRT_DEDUCTION_TYPE");
		final RowMapper<OffenderTransactions> GlTransactionsRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderTransactions.class, glTransactionsMapping);
		OffenderTransactions returnObj = null;
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("TXNID", txnId, "TXNENTRYSEQ", txnEntrySeq), GlTransactionsRowMapper);
		} catch (Exception e) {
			return returnObj;
		}
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * receiptNumberQuery
	 *
	 * @param params
	 * @return String
	 *
	 */
	public String findingDeductionCategory(final String dedType) {
		final String sql = getQuery("OTDGLIRT_DEDUCTION_CATEGORY");
		String returnObj = null;
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams("DEDTYPE", dedType), String.class);
		} catch (Exception e) {
			return returnObj;
		}
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * receiptNumberQuery
	 *
	 * @param params
	 * @return String
	 *
	 */
	public OffenderDeductions findingDeductionAmount(final Long dedId) {
		final String sql = getQuery("OTDGLIRT_DEDUCTION_AMOUNT");
		final RowMapper<OffenderDeductions> GlTransactionsRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderDeductions.class, offenderDeductionsMapping);
		OffenderDeductions returnObj = null;
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams("DEDID", dedId),
					GlTransactionsRowMapper);
		} catch (Exception e) {
			return returnObj;
		}
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * receiptNumberQuery
	 *
	 * @param params
	 * @return String
	 *
	 */
	public List<TransactionOperations> chkHoldTrans(final String txnType, final String caseloadId) {
		final String sql = getQuery("OTDGLIRT_CHK_HOLD_TRANS");
		final RowMapper<TransactionOperations> GlTransactionsRowMapper = Row2BeanRowMapper.makeMapping(sql,
				TransactionOperations.class, glTransactionsMapping);
		List<TransactionOperations> returnObj = new ArrayList<>();
		try {
			returnObj = namedParameterJdbcTemplate.query(sql,
					createParams("TXNTYPE", txnType, "CASELOADID", caseloadId), GlTransactionsRowMapper);
		} catch (Exception e) {
			return returnObj;
		}
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * receiptNumberQuery
	 *
	 * @param params
	 * @return String
	 *
	 */
	public String bankChequeData(final Long txnId) {
		final String sql = getQuery("OTDGLIRT_BANK_CHEQUE_DATA");
		String returnObj = null;
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams("TXNID", txnId), String.class);
		} catch (Exception e) {
			return returnObj;
		}
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * receiptNumberQuery
	 *
	 * @param params
	 * @return String
	 *
	 */
	public String profileValue() {
		final String sql = getQuery("OTDGLIRT_PROFILE_VALUE");
		String returnObj = null;
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), String.class);
		} catch (Exception e) {
			return returnObj;
		}
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * receiptNumberQuery
	 *
	 * @param params
	 * @return String
	 *
	 */
	public String beneficiaryTransactions(final Long txnId, final Long txnEntrySeq) {
		final String sql = getQuery("OTDGLIRT_BENEFICIARY_TRANSACTIONS");
		String returnObj = null;
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("TXNID", txnId, "TXNENTRYSEQ", txnEntrySeq), String.class);
		} catch (Exception e) {
			return returnObj;
		}
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * receiptNumberQuery
	 *
	 * @param params
	 * @return String
	 *
	 */
	public String bankChequeRegisters(final Long txnId) {
		final String sql = getQuery("OTDGLIRT_BANK_CHEQUE_REGISTERS");
		String returnObj = null;
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams("TXNID", txnId), String.class);
		} catch (Exception e) {
			return returnObj;
		}
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * receiptNumberQuery
	 *
	 * @param params
	 * @return String
	 *
	 */
	public String accountFlagTrust(final BigDecimal offId,String userName) {
		final String sql = getQuery("OTDGLIRT_ACCOUNT_CLOSED_FLAG_TRUST");
		String returnObj = null;
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams("OFFID", offId,"USER_ID",userName), String.class);
		} catch (Exception e) {
			return returnObj;
		}
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * receiptNumberQuery
	 *
	 * @param params
	 * @return String
	 *
	 */
	public OffenderTransactions infoNumberCreditObligationType(final GlTransactions offTxns) {
		final String sql = getQuery("OTDGLIRT_INFO_NUMBER_CREDIT_OBLIGATION_TYPE");
		final RowMapper<OffenderTransactions> GlTransactionsRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderTransactions.class, offenderTransactionsMapping);
		OffenderTransactions returnObj = new OffenderTransactions();
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("OFFID", offTxns.getTxnId(), "TRANSENTRYSEQ", offTxns.getTxnEntrySeq(),"USER_ID",offTxns.getCreateUserId()),
					GlTransactionsRowMapper);
		} catch (Exception e) {
			return returnObj;
		}
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * receiptNumberQuery
	 *
	 * @param params
	 * @return String
	 *
	 */
	public OffenderTransactions txnIdOffIdData(final Long txnId, final Long txnEntrySeq) {
		final String sql = getQuery("OTDGLIRT_TXN_TYPE_OFFENDER_ID");
		final RowMapper<OffenderTransactions> GlTransactionsRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderTransactions.class, glTransactionsMapping);
		OffenderTransactions returnObj = null;
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("TXNID", txnId, "TXNENTRYSEQ", txnEntrySeq), GlTransactionsRowMapper);
		} catch (Exception e) {
			return returnObj;
		}
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * receiptNumberQuery
	 *
	 * @param params
	 * @return String
	 *
	 */
	public OffenderSubAccounts balanceIndDateData(final Long offId, final Long txnId, final Long txnEntrySeq,String userName) {
		final String sql = getQuery("OTDGLIRT_BALANCE_IND_DATE");
		final RowMapper<OffenderSubAccounts> OffenderSubAccountsRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderSubAccounts.class, offenderDeductionsMapping);
		OffenderSubAccounts returnObj = null;
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("OFFID", offId, "TXNID", txnId, "TXNENTRYSEQ", txnEntrySeq,"USER_ID",userName),
					OffenderSubAccountsRowMapper);
		} catch (Exception e) {
			return returnObj;
		}
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * receiptNumberQuery
	 *
	 * @param params
	 * @return String
	 *
	 */
	public List<GlTransactions> glTransactionsNewData(final Long txnId, final Long txnEntrySeq, final String csldId) {
		final String sql = getQuery("OTDGLIRT_NEW_GL_TRANSACTIONS");
		final RowMapper<GlTransactions> GlTransactionsRowMapper = Row2BeanRowMapper.makeMapping(sql,
				GlTransactions.class, glTransactionsMapping);
		List<GlTransactions> returnObj = new ArrayList<>();
		try {
			returnObj = namedParameterJdbcTemplate.query(sql,
					createParams("TXNID", txnId, "TXNENTRYSEQ", txnEntrySeq, "CSLDID", csldId),
					GlTransactionsRowMapper);
		} catch (Exception e) {
			return returnObj;
		}
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * receiptNumberQuery
	 *
	 * @param params
	 * @return String
	 *
	 */
	public String txnPostingTypeTemp(final BigDecimal accntCode,String userName) {
		final String sql = getQuery("OTDGLIRT_TXN_POSTING_TYPE");
		String returnObj = null;
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams("ACCOUNTCODE", accntCode,"USER_ID",userName),
					String.class);
		} catch (Exception e) {
			return returnObj;
		}
		return returnObj;
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
	public Integer offTxnInsertGlTransactions(final Long txnId, final Long txnEntrySeq, final BigDecimal accountCode,
			final Long glSeq, final Date transDate, final String txnType, final String revTypeTrnsctnPstng,
			final String caseloadId, final BigDecimal offenderId, final BigDecimal offenderBookId,
			final BigDecimal txnEntryAmount, final String txnEntryDesc, final Long offTxnId, final Long offTxnEntrySeq,
			final Long glEntrySeq, final String reversalReasonCode, final Date transTime,
			final BigDecimal deductionId,String userName) {
		final String sql = getQuery("OTDGLIRT_INSERT_INTO_GL_TRANSACTIONS");
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("txnId", txnId);
		paramMap.put("txnEntrySeq", txnEntrySeq);
		paramMap.put("glSeq", glSeq);
		paramMap.put("accountCode", accountCode);
		paramMap.put("txnEntryDate", transDate);
		paramMap.put("txnType", txnType);
		paramMap.put("txnPostUsage", revTypeTrnsctnPstng);
		paramMap.put("caseloadId", caseloadId);
		paramMap.put("offenderId", offenderId);
		paramMap.put("offenderBookId", offenderBookId);
		paramMap.put("txnEntryAmount", txnEntryAmount);
		paramMap.put("txnEntryDesc", txnEntryDesc);
		paramMap.put("reversedTxnId", offTxnId);
		paramMap.put("reversedTxnEntrySeq", offTxnEntrySeq);
		paramMap.put("reversedGlEntrySeq", glEntrySeq);
		paramMap.put("reversalReasonCode", reversalReasonCode);
		paramMap.put("txnEntryTime", transTime);
		paramMap.put("deductionId", deductionId);
		paramMap.put("userId", userName);
		Integer returnArray = null;
		returnArray = namedParameterJdbcTemplate.update(sql, paramMap);
		if (returnArray != 0) {
			returnArray = 1;
			return returnArray;
		}
		return returnArray;
	}

	public String updateGlBalance(final String caseloadId, final BigDecimal accountCode, final Double pStngAmount,
			final Date transDate) {
		String openAnAccount = null;
		Map<String, Object> inParamMap = new HashMap<String, Object>();
		inParamMap.put("P_csld_id", caseloadId);
		inParamMap.put("P_account_code", accountCode);
		inParamMap.put("pstng_amount", pStngAmount);
		inParamMap.put("P_trans_date", transDate);
		try {
			namedParameterJdbcTemplate.update(
					"call OMS_OWNER.TRUST.UPDATE_GL_BALANCE(:P_csld_id, :P_account_code, :pstng_amount, :P_trans_date)",
					inParamMap);
			openAnAccount = "TEST";
		} catch (Exception e) {
			logger.error("updateGlBalance :" + e);
		}
		return openAnAccount;
	}

	/**
	 * This method will update the transaction in the table
	 * 
	 * @param offTrans
	 * @return String
	 */
	public String checkCaseloadType(final String caseloadId) {
		String openAnAccount = null;
		Map<String, Object> returnObject = null;
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[10];
		sqlParameters = new SqlParameter[] { new SqlParameter("CSLD_ID", OracleTypes.VARCHAR),
				new SqlOutParameter("CSLD_TYPE", OracleTypes.VARCHAR), };
		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("TRUST").withProcedureName("GET_CASELOAD_TYPE").declareParameters(sqlParameters);
		inParamMap.put("CSLD_ID", caseloadId);
		final SqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		try {
			returnObject = simpleJDBCCall.execute(inParameter);
			openAnAccount = String.valueOf(returnObject.get("CSLD_TYPE"));
		} catch (Exception e) {
			logger.error("checkCaseloadType :" + e);
		}
		return openAnAccount;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * receiptNumberQuery
	 *
	 * @param params
	 * @return String
	 *
	 */
	public List<GlTransactions> accountClosedFlagData(final BigDecimal offId,String userName) {
		final String sql = getQuery("OTDGLIRT_ACCOUNT_CLOSED_FLAG_Y");
		final RowMapper<GlTransactions> GlTransactionsRowMapper = Row2BeanRowMapper.makeMapping(sql,
				GlTransactions.class, OffenderCreditPriorPaymentsMapping);
		List<GlTransactions> returnObj = new ArrayList<>();
		try {
			returnObj = namedParameterJdbcTemplate.query(sql, createParams("OFFID", offId,"USER_ID",userName), GlTransactionsRowMapper);
		} catch (Exception e) {
			return returnObj;
		}
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * receiptNumberQuery
	 *
	 * @param params
	 * @return String
	 *
	 */
	public String subAccountTypeOffTrans(final BigDecimal accntCode,String userName) {
		final String sql = getQuery("OTDGLIRT_SUB_ACCOUNT_TYPE_OFF_TRANS");
		String returnObj = null;
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams("ACCOUNTCODE", accntCode,"USER_ID",userName),
					String.class);
		} catch (Exception e) {
			return returnObj;
		}
		return returnObj;
	}

	public Integer updateOffenderTransactions(final Long txnId, final Long txnEntrySeq,String userName) {
		final String sql = getQuery("OTDGLIRT_UPDATE_OFFENDER_TRANSACTIONS_Y");
		Map<String, Object> inParamMap = new HashMap<String, Object>();
		inParamMap.put("TXNID", txnId);
		inParamMap.put("TXNENTRYSEQ", txnEntrySeq); 
		inParamMap.put("USER_ID", userName);
		Integer returnArray = null;
		returnArray = namedParameterJdbcTemplate.update(sql, inParamMap);
		if (returnArray != 0) {
			return returnArray;
		}
		return returnArray;
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
	public Integer offTxnInsertOffenderTransactions(final Long txnId, final Long txnEntrySeq,
			final BigDecimal offenderId, final BigDecimal offenderBookId, final Date transDate, final String caseloadId,
			final BigDecimal txnEntryAmount, final String txnEntryDesc, final String txnType,
			final String acntTypePstng, final String subAccountTypeFlag, final String infoNumber, final Long offTxnId,
			final Long offTxnEntrySeq, final String dedType,String userName) {
		final String sql = getQuery("OTDGLIRT_INSERT_INTO_OFFENDER_TRANSACTIONS");
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("P_trans_number", txnId);
		paramMap.put("P_trans_seq", txnEntrySeq);
		paramMap.put("P_off_id", offenderId);
		paramMap.put("P_off_book_id", offenderBookId);
		paramMap.put("P_trans_date", transDate);
		paramMap.put("P_csld_id", caseloadId);
		paramMap.put("trans_amount", txnEntryAmount);
		paramMap.put("trans_desc", txnEntryDesc);
		paramMap.put("P_trans_type", txnType);
		paramMap.put("P_trans_post_type", acntTypePstng);
		paramMap.put("sub_acnt_type", subAccountTypeFlag);
		paramMap.put("P_info_number", infoNumber);
		paramMap.put("old_txn_id", offTxnId);
		paramMap.put("old_txn_seq", offTxnEntrySeq);
		paramMap.put("P_ded_type", dedType);
		paramMap.put("createUserId", userName);
		Integer returnArray = null;
		returnArray = namedParameterJdbcTemplate.update(sql, paramMap);
		if (returnArray != 0) {
			returnArray = 1;
			return returnArray;
		}
		return returnArray;
	}

	public void updateOffenderBalance(final String caseloadId, final BigDecimal offenderId, final String acntTypePstng,
			final Date transDate, final Long txnId, final String txnType, final BigDecimal txnEntryAmount,
			final String subAccountTypeFlag) {
		SqlParameter[] sqlParameters = new SqlParameter[] { new SqlParameter("p_csld_id", OracleTypes.VARCHAR),
				new SqlParameter("p_off_id", OracleTypes.NUMBER),
				new SqlParameter("p_trans_post_type", OracleTypes.VARCHAR),
				new SqlParameter("p_trans_date", OracleTypes.DATE),
				new SqlParameter("p_trans_number", OracleTypes.NUMBER),
				new SqlParameter("p_trans_type", OracleTypes.VARCHAR),
				new SqlParameter("p_trans_amount", OracleTypes.NUMBER),
				new SqlParameter("p_sub_act_type", OracleTypes.VARCHAR),
				new SqlParameter("p_allow_overdrawn", OracleTypes.VARCHAR), };
		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("TRUST").withProcedureName("UPDATE_OFFENDER_BALANCE").declareParameters(sqlParameters);
		Map<String, Object> inParamMap = new HashMap<String, Object>();
		inParamMap.put("p_csld_id", caseloadId);
		inParamMap.put("p_off_id", offenderId);
		inParamMap.put("p_trans_post_type", acntTypePstng);
		inParamMap.put("p_trans_date", transDate);
		inParamMap.put("p_trans_number", txnId);
		inParamMap.put("p_trans_type", txnType);
		inParamMap.put("p_trans_amount", txnEntryAmount);
		inParamMap.put("p_sub_act_type", subAccountTypeFlag);
		inParamMap.put("p_allow_overdrawn", "N");
		final MapSqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		simpleJDBCCall.execute(inParameter);
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * receiptNumberQuery
	 *
	 * @param params
	 * @return String
	 *
	 */
	public String findCaseloadId(final BigDecimal dedId) {
		final String sql = getQuery("OTDGLIRT_CASELOAD_ID");
		String returnObj = null;
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams("DEDID", dedId), String.class);
		} catch (Exception e) {
			return returnObj;
		}
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * receiptNumberQuery
	 *
	 * @param params
	 * @return String
	 *
	 */
	public String findingDeductionCategoryTemp(final String dedType) {
		final String sql = getQuery("OTDGLIRT_DEDUCTION_CATEGORY_TEMP");
		String returnObj = null;
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams("DEDTYPE", dedType), String.class);
		} catch (Exception e) {
			return returnObj;
		}
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * receiptNumberQuery
	 *
	 * @param params
	 * @return String
	 *
	 */
	public OffenderCreditPriorPayments paymentAmountData(final Long txnId) {
		final String sql = getQuery("OTDGLIRT_PAYMENT_AMOUNT");
		final RowMapper<OffenderCreditPriorPayments> OffenderSubAccountsRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderCreditPriorPayments.class, OffenderCreditPriorPaymentsMapping);
		OffenderCreditPriorPayments returnObj = null;
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams("TXNID", txnId),
					OffenderSubAccountsRowMapper);
		} catch (Exception e) {
			return returnObj;
		}
		return returnObj;
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
	public Integer updateOffenderDeductions(final String deductionType, final BigDecimal offenderId,
			final Double lvrevdedamount, final BigDecimal deductionId, final String createCaseload,String userId) {
		final String sql = getQuery("OTDGLIRT_UPDATE_OFFENDER_DEDUCTIONS");
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("TXAMNT", lvrevdedamount);
		paramMap.put("CASELOADID", createCaseload);
		paramMap.put("OFFENDERID", offenderId);
		paramMap.put("DEDTYPE", deductionType);
		paramMap.put("DEDID", deductionId);
		paramMap.put("USER_ID", userId);
		Integer returnArray = null;
		returnArray = namedParameterJdbcTemplate.update(sql, paramMap);
		if (returnArray != 0) {
			returnArray = 1;
			return returnArray;
		}
		return returnArray;
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
	public Integer updateOffenderBeneficiaries(final Double lvrevdedamount, final BigDecimal deductionId) {
		final String sql = getQuery("OTDGLIRT_UPDATE_OFFENDER_BENEFICIARIES");
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("TXAMNT", lvrevdedamount);
		paramMap.put("DEDID", deductionId);
		Integer returnArray = null;
		returnArray = namedParameterJdbcTemplate.update(sql, paramMap);
		if (returnArray != 0) {
			returnArray = 1;
			return returnArray;
		}
		return returnArray;
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
	public Integer updateOffenderDeductionsTemp(final String deductionType, final BigDecimal offenderId,
			final Double lvrevdedamount, final BigDecimal deductionId, final String createCaseload,String userId) {
		final String sql = getQuery("OTDGLIRT_UPDATE_OFFENDER_DEDUCTIONS_TEMP");
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("TXAMNT", lvrevdedamount);
		paramMap.put("CASELOADID", createCaseload);
		paramMap.put("OFFENDERID", offenderId);
		paramMap.put("DEDTYPE", deductionType);
		paramMap.put("DEDID", deductionId);
		paramMap.put("USER_ID", userId);
		Integer returnArray = null;
		returnArray = namedParameterJdbcTemplate.update(sql, paramMap);
		if (returnArray != 0) {
			returnArray = 1;
			return returnArray;
		}
		return returnArray;
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
	public Integer insertOffenderCreditPriorPayments(final Long txnId, final String caseloadId,
			final BigDecimal offenderId, final BigDecimal lvrevdedamount, final String location,
			final String commentText,String userName) {
		final String sql = getQuery("INSERT_INTO_OFFENDER_CREDIT_PRIOR_PAYMENTS");
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("NEWTXNNUMBER", txnId);
		paramMap.put("CASELOADID", caseloadId);
		paramMap.put("OFFID", offenderId);
		paramMap.put("LVREVDEDAMOUNT", lvrevdedamount);
		paramMap.put("LOCATION", location);
		paramMap.put("COMMENTTEXT", commentText);
		paramMap.put("CREATE_USER_ID", userName);
		Integer returnArray = null;
		returnArray = namedParameterJdbcTemplate.update(sql, paramMap);
		if (returnArray != 0) {
			returnArray = 1;
			return returnArray;
		}
		return returnArray;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * receiptNumberQuery
	 *
	 * @param params
	 * @return String
	 *
	 */
	public List<GlTransactions> corporateIdPersonId(final BigDecimal dedId) {
		final String sql = getQuery("OTDGLIRT_CORPORATE_ID_PERSON_ID");
		final RowMapper<GlTransactions> GlTransactionsRowMapper = Row2BeanRowMapper.makeMapping(sql,
				GlTransactions.class, OffenderCreditPriorPaymentsMapping);
		List<GlTransactions> returnObj = new ArrayList<>();
		try {
			returnObj = namedParameterJdbcTemplate.query(sql, createParams("OFFDEDID", dedId), GlTransactionsRowMapper);
		} catch (Exception e) {
			return returnObj;
		}
		return returnObj;
	}

	public String getAcAndSetIndDate(final String caseloadId, final BigDecimal offenderId) {
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

	public String reverseBeneficiaryTrans(final Long txnId, final Long txnEntrySeq, final long glEntrySeq,
			final Long offTxnId, final Long glSeq, final String txnType, final String txnEntryDesc) {
		String openAnAccount = null;
		Map<String, Object> inParamMap = new HashMap<String, Object>();
		inParamMap.put("p_rev_txn_id", txnId);
		inParamMap.put("p_rev_txn_entry_seq", txnEntrySeq);
		inParamMap.put("p_rev_gl_entry_seq", glEntrySeq);
		inParamMap.put("p_txn_id", offTxnId);
		inParamMap.put("p_txn_entry_seq", 1);
		inParamMap.put("p_gl_entry_seq", glSeq);
		inParamMap.put("p_txn_type", txnType);
		inParamMap.put("p_txn_entry_desc", txnEntryDesc);
		try {
			namedParameterJdbcTemplate.update(
					"call OMS_OWNER.FINANCIAL.REVERSE_BENEFICIARY_TRANS(:p_rev_txn_id, :p_rev_txn_entry_seq, :p_rev_gl_entry_seq,"
							+ ":p_txn_id, :p_txn_entry_seq, :p_gl_entry_seq, :p_txn_type, :p_txn_entry_desc)",
					inParamMap);
			openAnAccount = "GETINDDATE";
		} catch (Exception e) {
			logger.error("reverseBeneficiaryTrans :" + e);
		}
		return openAnAccount;
	}

	public String updateOffenderBeneficiaries(final BigDecimal deductionId, final BigDecimal payeePersonId,
			final BigDecimal payeeCorporateId, final BigDecimal txnEntryAmount) {
		String openAnAccount = null;
		Map<String, Object> inParamMap = new HashMap<String, Object>();
		inParamMap.put("p_ded_id", deductionId);
		inParamMap.put("p_per_id", payeePersonId);
		inParamMap.put("p_corp_id", payeeCorporateId);
		inParamMap.put("p_unknown_id", null);
		inParamMap.put("p_amount_to_ben", txnEntryAmount);
		try {
			namedParameterJdbcTemplate
					.update("call OMS_OWNER.FINANCIAL.UPDATE_OFFENDER_BENEFICIARIES(:p_ded_id, :p_per_id, :p_corp_id,"
							+ ":p_unknown_id, :p_amount_to_ben)", inParamMap);
			openAnAccount = "GETINDDATE";
		} catch (Exception e) {
			logger.error("updateOffenderBeneficiaries :" + e);
		}
		return openAnAccount;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * receiptNumberQuery
	 *
	 * @param params
	 * @return String
	 *
	 */
	public String txnTypeOutputData(final String txnType) {
		final String sql = getQuery("OTDGLIRT_TXN_TYPE_OTUCOBWO");
		String returnObj = null;
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams("TRANSTYPE", txnType),
					String.class);
		} catch (Exception e) {
			return returnObj;
		}
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * receiptNumberQuery
	 *
	 * @param dedId
	 * @return OffenderDeductions
	 *
	 */
	public OffenderDeductions maxTotalAmountDeductionAamount(final BigDecimal dedId) {
		final String sql = getQuery("OTDGLIRT_MAX_TOTAL_AMOUNT_DEDUCTION_AMOUNT");
		final RowMapper<OffenderDeductions> GlTransactionsRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderDeductions.class, offenderDeductionsMapping);
		OffenderDeductions returnObj = null;
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams("DEDID", dedId),
					GlTransactionsRowMapper);
		} catch (Exception e) {
			return returnObj;
		}
		return returnObj;
	}

	/**
	 * This method is used to insert the records in the data base tables based
	 * on
	 *
	 * @param lstOffenderTransactions
	 *            List<OffenderTransactions>
	 *
	 * @return List<Integer>
	 */
	public Integer insertOffenderAdjustmentTxns(final Long txnId, final BigDecimal offenderId,
			final BigDecimal deductionId, final BigDecimal txnEntryAmount, final Long offTxnId, String userName) {
		final String sql = getQuery("OTDGLIRT_INSERT_INTO_OFFENDER_ADJUSTMENT_TXNS");
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("PGLTXNID", txnId);
		paramMap.put("POFFENDERID", offenderId);
		paramMap.put("POFFDEDID", deductionId);
		paramMap.put("PTXNENTRYAMOUNT", txnEntryAmount);
		paramMap.put("PREVTXNID", offTxnId);
		paramMap.put("CREATEUSERID", userName);
		Integer returnArray = null;
		returnArray = namedParameterJdbcTemplate.update(sql, paramMap);
		if (returnArray != 0) {
			returnArray = 1;
			return returnArray;
		}
		return returnArray;
	}

	/**
	 * This method is used to insert the records in the data base tables based
	 * on
	 *
	 * @param lstOffenderTransactions
	 *            List<OffenderTransactions>
	 *
	 * @return List<Integer>
	 */
	public Integer updateOffenderDeductionsAdjustmentAmount(final Double lvAdjustmentAmount,
			final BigDecimal txnEntryAmount, final String lvAdjustmentReason, final BigDecimal deductionId , String userName) {
		final String sql = getQuery("OTDGLIRT_UPDATE_OFFENDER_DEDUCTIONS_ADJUSTMENT_AMOUNT");
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("LVADJUSTMENTAMOUNT", lvAdjustmentAmount);
		paramMap.put("PTXNENTRYAMOUNT", txnEntryAmount);
		paramMap.put("LVADJUSTMENTREASON", lvAdjustmentReason);
		paramMap.put("POFFDEDID", deductionId);
		paramMap.put("modifyUserId", userName);
		Integer returnArray = null;
		returnArray = namedParameterJdbcTemplate.update(sql, paramMap);
		if (returnArray != 0) {
			returnArray = 1;
			return returnArray;
		}
		return returnArray;
	}

	/**
	 * This method is used to insert the records in the data base tables based
	 * on
	 *
	 * @param lstOffenderTransactions
	 *            List<OffenderTransactions>
	 *
	 * @return List<Integer>
	 */
	public Integer updateGlTransactionsTxnReversedFlag(final String txnReversedFlag, final Long txnId,
			final Long txnEntrySeq, String caseloadId,String userId) {
		final String sql = getQuery("OTDGLIRT_UPDATE_GL_TRANSACTIONS");
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("FLAG", txnReversedFlag);
		paramMap.put("TXNNUMBER", txnId);
		paramMap.put("TXNENTRYSEQ", txnEntrySeq);
		paramMap.put("CASELOADID", caseloadId);
		paramMap.put("modifyUserId", caseloadId);
		Integer returnArray = null;
		returnArray = namedParameterJdbcTemplate.update(sql, paramMap);
		if (returnArray != 0) {
			returnArray = 1;
			return returnArray;
		}
		return returnArray;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * receiptNumberQuery
	 *
	 * @param params
	 * @return String
	 *
	 */
	public String txnTypeExist(final String txnType, final String caseloadId) {
		final String sql = getQuery("OTDGLIRT_TXN_TYPE_EXIST");
		String returnObj = null;
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("TRANSTYPE", txnType, "CSLDID", caseloadId), String.class);
		} catch (Exception e) {
			return returnObj;
		}
		return returnObj;
	}

	/**
	 * This method is used to insert the records in the data base tables based
	 * on
	 *
	 * @param lstOffenderTransactions
	 *            List<OffenderTransactions>
	 *
	 * @return List<Integer>
	 */
	public Integer updateOffenderWorks(final BigDecimal offenderId, final Long txnId,String userId) {
		final String sql = getQuery("OTDGLIRT_OFFENDER_WORKS");
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("OFFID", offenderId);
		paramMap.put("TXNNUMBER", txnId);
		paramMap.put("modifyUserId", userId);
		Integer returnArray = null;
		returnArray = namedParameterJdbcTemplate.update(sql, paramMap);
		if (returnArray != 0) {
			returnArray = 1;
			return returnArray;
		}
		return returnArray;
	}

	@Override
	public String txnEntryDescTempData(final String transDesc, final String vReversalRsn) {
		final String sql = getQuery("TXN_ENTRY_DESC_TEMP_DATA");
		String returnObj = null;
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("TRANSDESC", transDesc, "VREVERSALRSN", vReversalRsn), String.class);
		} catch (Exception e) {
			return returnObj;
		}
		return returnObj;
	}
	
	@Override
	public OffenderTransactions txnIdBasedData(final Long txnId) {
		final String sql = getQuery("OTDGLIRT_GETTING_TXN_ID_BASED");
		OffenderTransactions returnObj = new OffenderTransactions();
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("txnId",txnId), new BeanPropertyRowMapper<OffenderTransactions>(OffenderTransactions.class));
		} catch (Exception e) {
			logger.error("txnIdBasedData",e);
		}
		return returnObj;
	}
	
	
	public OffFeeBillTransactions offFeeExecuteQuery(final Long txnId,final Long txnSeqId) {
		final String sql = getQuery("OTDGLIRT_GETTING_OFF_FEE_BILL_TRANSACTIONS");
		try {
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("txnId", txnId,"txnEntrySeq",txnSeqId),
				new BeanPropertyRowMapper<OffFeeBillTransactions>(OffFeeBillTransactions.class));
		}catch (Exception e) {
			return new OffFeeBillTransactions();
		}
	}

	@Override
	public OffenderTransactions offenderTxnData(Long txnId, Long txnEntrySeq) {
		final String sql = getQuery("OTDGLIRT_OFFENDER_TRANSACTIONS_OLD_DATA");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("txnId", txnId, "txnEntrySeq", txnEntrySeq),
				new BeanPropertyRowMapper<OffenderTransactions>(OffenderTransactions.class));
	}
	
	@Override
	public Integer getDrAccountCode(String txnType, String caseLoadId) {
		String sql = getQuery("OTDGLIRT_DR_ACCOUNT_CODE");
		Integer drCode=null;
		try {
			drCode=namedParameterJdbcTemplate.queryForObject(sql,createParams("txnType", txnType, "caseloadId", caseLoadId), Integer.class);
		}catch (Exception e) {
			logger.error(this.getClass().getName()+" getDrAccountCode method call in error ::" + e);
		}
		return drCode;
	}
	
	@Override
	public Integer getCrAccountCode(String txnType, String caseLoadId) {
		String sql = getQuery("OTDGLIRT_CR_ACCOUNT_CODE");
		Integer crCode=null;
		try {
			crCode=namedParameterJdbcTemplate.queryForObject(sql,createParams("txnType", txnType, "caseloadId", caseLoadId), Integer.class);
		}catch (Exception e) {
			logger.error(this.getClass().getName()+" getQuery method call in error ::" + e);
		}
		return crCode;
	}
	
	@Override
	public Integer trustInsertGltransNew(OffenderTransactions offTxnObj) {
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
		inParamMap.put("p_info_number", offTxnObj.getInfoNumber());
		inParamMap.put("p_payee_person_id", null);
		inParamMap.put("p_payee_corporate_id", null);
		inParamMap.put("p_payee_name_text", null);
		inParamMap.put("p_revr_txn_id", null);
		inParamMap.put("p_revr_txn_entry_seq", null);
		inParamMap.put("p_revr_gl_entry_seq", null);
		inParamMap.put("p_txn_ref_number",offTxnObj.getTxnReferenceNumber());
		inParamMap.put("p_off_ded_id", null);
		final MapSqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);

		try {
			simpleJDBCCall.execute(inParameter);
			genSequence = 1;
		} catch (Exception e) {
			logger.error(this.getClass().getName()+" trustInsertGltransNew method call in error ::" + e);
			genSequence = 0;
			return genSequence;
		}
		return genSequence;
	}
	
	public Integer getGlTxnEntrySeq(Integer txnId, Long offenderId) {
		final String sql = getQuery("OTDGLIRT_GET_GL_TXN_ENTRYSEQ");
		Integer enterSeq=null; 
		try {
			enterSeq=namedParameterJdbcTemplate.queryForObject(sql, createParams("txnId", txnId, "offenderId", offenderId),Integer.class);
		}catch (Exception e) {
			logger.error(this.getClass().getName()+" getOffFeeBillsData method call in error ::", e);
		}
		return enterSeq;
	}
	
	@Override
	public OffFeeBills getOffFeeBillsData(String billId) {
		final String sql=getQuery("OTDGLIRT_GET_OFF_FEE_BILLS");
		OffFeeBills bean=new OffFeeBills();
		try {
			bean=namedParameterJdbcTemplate.queryForObject(sql, createParams("billId",billId), new BeanPropertyRowMapper<OffFeeBills>(OffFeeBills.class));
		}catch (Exception e) {
			logger.error(this.getClass().getName()+" getOffFeeBillsData method call in error ::", e);
		}
		return bean;
	}

	@Override
	public Integer updateOffenderFees(OffFeeBillTransactions offFeeBillTxn) {
		final String sql = getQuery("OTDGLIRT_OFF_BILL_TRN_INSERT");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<>();
		offFeeBillTxn.setBillTxnStaffId(ocdbreciRepository.getstaffId(offFeeBillTxn.getCreateUserId()));
		parameters.add(new BeanPropertySqlParameterSource(offFeeBillTxn));
		try {
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		}catch (Exception e) {
			logger.error(this.getClass().getName()+" updateOffenderFees method call in error ::", e);
		}
		if (returnArray.length == 1) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public List<OffFeeBillTransactions> getUdjustmentFeeBills(OffFeeBillTransactions bean) {
		final String sql = getQuery("OTDGLIRT_GET_UNREVERSAL_FEE_BILLS");
		try {
			final RowMapper<OffFeeBillTransactions> feeBillTrnRowMapper = Row2BeanRowMapper.makeMapping(sql,
					OffFeeBillTransactions.class, offFeeMapping);
			return namedParameterJdbcTemplate.query(sql,
					createParams("billId", bean.getOriginalBillId(), "billTxnNo", bean.getOriginalBillTxnNo()),
					feeBillTrnRowMapper);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " getUdjustmentFeeBills method call in error ::", e);
		}
		return null;
	}
	
	@Override
	public OffFeeBills getUdjustmentFeeBills(String billId) {
		final String sql = getQuery("OTDGLIRT_GET_OFF_FEE_BILLS_OLD_DATA");
		OffFeeBills bean = new OffFeeBills();
		try {
			bean = namedParameterJdbcTemplate.queryForObject(sql, createParams("billId",billId), new BeanPropertyRowMapper<OffFeeBills>(OffFeeBills.class));
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " getUdjustmentFeeBills method call in error ::", e);
		}
		return bean;
	}
	
	
	@Override
	public OffenderDeductions getOffenderDeductionOldData(Long offDedctionId) {
		final String sql = getQuery("OTDGLIRT_UPDATE_OFFENDER_DEDUCTIONS_GET_OLD_DATA");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql, createParams("offenderDeductionId", offDedctionId),
					new BeanPropertyRowMapper<OffenderDeductions>(OffenderDeductions.class));
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " getOffenderDeductionOldData method call in error ::", e);
			return null;
		}
	}
	
	@Override
	public OffenderBeneficiaries getOldDataOfOffenderBeneficiaries(Long offDedctionId) {
		final String sql = getQuery("OTDGLIRT_UPDATE_OFFENDER_DEDUCTIONS_GET_OLD_DATA");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql, createParams("offenderDeductionId", offDedctionId),
					new BeanPropertyRowMapper<OffenderBeneficiaries>(OffenderBeneficiaries.class));
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " getOldDataOfOffenderBeneficiaries method call in error ::", e);
			return null;
		}
	}
}

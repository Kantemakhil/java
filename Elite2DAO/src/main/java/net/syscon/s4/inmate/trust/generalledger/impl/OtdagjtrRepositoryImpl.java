package net.syscon.s4.inmate.trust.generalledger.impl;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.beans.TransactionTypes;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.AccountCodes;
import net.syscon.s4.im.beans.Corporates;
import net.syscon.s4.im.beans.SysDual;
import net.syscon.s4.inmate.beans.GlTransactions;
import net.syscon.s4.inmate.trust.generalledger.OtdagjtrRepository;
import net.syscon.s4.inst.booking.beans.Persons;
import oracle.jdbc.internal.OracleTypes;

@Repository
public class OtdagjtrRepositoryImpl extends RepositoryBase implements OtdagjtrRepository{

	private static Logger logger = LogManager.getLogger(OtdagjtrRepositoryImpl.class.getName());

	private final Map<String, FieldMapper> personsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("PAYEE_PERSON_ID", 				new FieldMapper("payeePersonId"))
			.put("LAST_NAME", 						new FieldMapper("lastName"))
			.put("FIRST_NAME", 						new FieldMapper("firstName"))
			.build();
	private final Map<String, FieldMapper> transactionTypesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DESCRIPTION", 					new FieldMapper("description"))
			.put("CASELOAD_TYPE", 					new FieldMapper("caseloadType"))
			.put("CASELOAD_ID", 					new FieldMapper("caseloadId"))
			.put("TXN_TYPE", 						new FieldMapper("txnType"))
			.build();

	private final Map<String, FieldMapper> accountCodesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("ACCOUNT_NAME", 					new FieldMapper("accountName"))
			.put("TXN_POSTING_TYPE", 				new FieldMapper("txnPostingType"))
			.put("CASELOAD_TYPE", 					new FieldMapper("caseloadType"))
			.put("ACCOUNT_CODE", 					new FieldMapper("accountCode"))
			.build();
	private final Map<String, FieldMapper> systemProfilesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("PROFILE_CODE", 					new FieldMapper("profileCode"))
			.put("PROFILE_TYPE", 					new FieldMapper("profileType"))
			.put("CREATE_USER_ID", 					new FieldMapper("createUserId"))
			.put("SEAL_FLAG", 						new FieldMapper("sealFlag"))
			.put("CREATE_DATETIME", 				new FieldMapper("createDatetime"))
			.put("MODIFY_USER_ID", 					new FieldMapper("modifyUserId"))
			.put("OLD_TABLE_NAME", 					new FieldMapper("oldTableName"))
			.put("PROFILE_VALUE", 					new FieldMapper("profileValue"))
			.put("MODIFY_DATETIME", 				new FieldMapper("modifyDatetime"))
			.put("PROFILE_VALUE_2", 				new FieldMapper("profileValue2"))
			.put("DESCRIPTION", 					new FieldMapper("description"))
			.build();
	private final Map<String, FieldMapper> mMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("PERSON_ID", 						new FieldMapper("personId"))
			.put("CORPORATE_ID", 					new FieldMapper("corporateId"))
			.put("LAST_NAME", 						new FieldMapper("lastName"))
			.put("FIRST_NAME", 						new FieldMapper("firstName"))
			.put("TXN_TYPE", 						new FieldMapper("txnType"))
			.put("CODE", 							new FieldMapper("code"))
			.put("DESCRIPTION", 					new FieldMapper("description"))
			.put("DR_ACCOUNT_CODE", 				new FieldMapper("drAccountCode"))
			.put("CR_ACCOUNT_CODE", 				new FieldMapper("crAccountCode"))

			.build();
	private final Map<String, FieldMapper> glTransactionsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("PAYEE_CLEAR_FLAG", 				new FieldMapper("payeeClearFlag"))
			.put("TXN_REFERENCE_NUMBER", 			new FieldMapper("txnReferenceNumber"))
			.put("REVERSED_TXN_ID", 				new FieldMapper("reversedTxnId"))
			.put("INFO_NUMBER", 					new FieldMapper("infoNumber"))
			.put("TXN_ENTRY_TIME", 					new FieldMapper("txnEntryTime"))
			.put("PAYEE_NAME_TEXT", 				new FieldMapper("payeeNameText"))
			.put("OFFENDER_ID", 					new FieldMapper("offenderId"))
			.put("GL_ENTRY_SEQ", 					new FieldMapper("glEntrySeq"))
			.put("SEAL_FLAG", 						new FieldMapper("sealFlag"))
			.put("CREATE_DATETIME", 				new FieldMapper("createDatetime"))
			.put("TXN_TYPE", 						new FieldMapper("txnType"))
			.put("MODIFY_DATETIME", 				new FieldMapper("modifyDatetime"))
			.put("ACCOUNT_PERIOD_ID", 				new FieldMapper("accountPeriodId"))
			.put("TXN_OBJECT_ID", 					new FieldMapper("txnObjectId"))
			.put("PAYEE_PERSON_ID", 				new FieldMapper("payeePersonId"))
			.put("REVERSED_TXN_ENTRY_SEQ", 			new FieldMapper("reversedTxnEntrySeq"))
			.put("TXN_ID", 							new FieldMapper("txnId"))
			.put("OFFENDER_BOOK_ID", 				new FieldMapper("offenderBookId"))
			.put("CREATE_USER_ID", 					new FieldMapper("createUserId"))
			.put("DEDUCTION_ID", 					new FieldMapper("deductionId"))
			.put("TXN_ENTRY_DATE", 					new FieldMapper("txnEntryDate"))
			.put("RECON_CLEAR_FLAG", 				new FieldMapper("reconClearFlag"))
			.put("REVERSED_GL_ENTRY_SEQ", 			new FieldMapper("reversedGlEntrySeq"))
			.put("BANK_STATEMENT_DATE", 			new FieldMapper("bankStatementDate"))
			.put("MODIFY_USER_ID", 					new FieldMapper("modifyUserId"))
			.put("TXN_ENTRY_AMOUNT", 				new FieldMapper("txnEntryAmount"))
			.put("LIST_SEQ", 						new FieldMapper("listSeq"))
			.put("TXN_LOC_ID", 						new FieldMapper("txnLocId"))
			.put("RECEIPT_NUMBER", 					new FieldMapper("receiptNumber"))
			.put("REVERSAL_REASON_CODE", 			new FieldMapper("reversalReasonCode"))
			.put("CASELOAD_ID", 					new FieldMapper("caseloadId"))
			.put("TXN_OBJECT_CODE", 				new FieldMapper("txnObjectCode"))
			.put("TXN_ENTRY_SEQ", 					new FieldMapper("txnEntrySeq"))
			.put("ACCOUNT_CODE", 					new FieldMapper("accountCode"))
			.put("PAYEE_CORPORATE_ID", 				new FieldMapper("payeeCorporateId"))
			.put("CREATE_DATE", 					new FieldMapper("createDate"))
			.put("TXN_REVERSED_FLAG", 				new FieldMapper("txnReversedFlag"))
			.put("TXN_POST_USAGE", 					new FieldMapper("txnPostUsage"))
			.put("'1'", 							new FieldMapper("  '1' "))
			.put("TXN_ENTRY_DESC", 					new FieldMapper("txnEntryDesc"))
			.build();
	private final Map<String, FieldMapper> corporatesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CORPORATE_NAME", 					new FieldMapper("corporateName"))
			.put("PAYEE_CORPORATE_ID", 				new FieldMapper("payeeCorporateId"))
			.build();

	/**
	 * Creates new OtdagjtrRepositoryImpl class Object
	 */
	public OtdagjtrRepositoryImpl() {
		
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
	public List<GlTransactions> glTxnExecuteQuery(GlTransactions objSearchDao) {
		final String sql = getQuery("OTDAGJTR_GLTXN_FIND_GL_TRANSACTIONS");
		final RowMapper<GlTransactions> GlTransactionsRowMapper = Row2BeanRowMapper.makeMapping(sql,
				GlTransactions.class, glTransactionsMapping);
		final ArrayList<GlTransactions> returnList = (ArrayList<GlTransactions>) namedParameterJdbcTemplate.query(sql,
				createParams(), GlTransactionsRowMapper);
		return returnList;
	}

	/**
	 * @param
	 *
	 * @throws SQLException
	 *
	 */
	public int PRE_INSERT() {
		return 0;
	}

	/**
	 * This method is used to insert the records in the data base tables based
	 * on
	 *
	 * @param lstGlTransactions
	 *            List<GlTransactions>
	 *
	 * @return List<Integer>
	 *
	 * @throws SQLException
	 */
	public Integer glTxnInsertGlTransactions(final List<GlTransactions> lstGlTransactions) {

		String sql = getQuery("OTDAGJTR_GLTXN_INSERT_GL_TRANSACTIONS");
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
	public List<SystemProfiles> sysPflExecuteQuery(SystemProfiles objSearchDao) {
		final String sql = getQuery("OTDAGJTR_SYSPFL_FIND_SYSTEM_PROFILES");
		final RowMapper<SystemProfiles> SystemProfilesRowMapper = Row2BeanRowMapper.makeMapping(sql,
				SystemProfiles.class, systemProfilesMapping);
		final ArrayList<SystemProfiles> returnList = (ArrayList<SystemProfiles>) namedParameterJdbcTemplate.query(sql,
				createParams(), SystemProfilesRowMapper);
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
		String sql = getQuery("OTDAGJTR_SYSPFL_INSERT_SYSTEM_PROFILES");
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
		String sql = getQuery("OTDAGJTR_SYSPFL_DELETE_SYSTEM_PROFILES");
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
	public List<TransactionTypes> cgfkGlTxnTxnTypeRecordGroup(final String caseloadId, final String caseloadType) {
		final String sql = getQuery("OTDAGJTR_FIND_CGFKGLTXNTXNTYPE");
		final RowMapper<TransactionTypes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, TransactionTypes.class,
				mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql,
					createParams("caseloadId", caseloadId, "caseloadType", caseloadType), mRowMapper);
		} catch (Exception e) {
			logger.error(
					"OtdagjtrRepositoryImpl cgfkGlTxnTxnTypeRecordGroup Exception while calling Txn.Type LOV Data");
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<Persons> cgfkGlTxnPayeePersonIdRecordGroup(final Integer personId) {
		final String sql = getQuery("OTDAGJTR_FIND_CGFKGLTXNPAYEEPERSONID");
		final RowMapper<Persons> mRowMapper = Row2BeanRowMapper.makeMapping(sql, Persons.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams("personId", personId), mRowMapper);
		} catch (Exception e) {
			logger.error(
					"OtdagjtrRepositoryImpl cgfkGlTxnPayeePersonIdRecordGroup Exception in calling Person Query : ", e);
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<MMCrp.corporateNameCrp.caseloadId>
	 */
	public List<Corporates> cgfkGlTxnPayeeCorporateIdRecordGroup(final Integer corporateId) {
		final String sql = getQuery("OTDAGJTR_FIND_CGFKGLTXNPAYEECORPORATEID");
		final RowMapper<Corporates> mMCrpRowMapper = Row2BeanRowMapper.makeMapping(sql, Corporates.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams("corporateId", corporateId), mMCrpRowMapper);
		} catch (Exception e) {
			logger.error(
					"OtdagjtrRepositoryImpl cgfkGlTxnPayeeCorporateIdRecordGroup Exception occured while calling Corporate Query",
					e);
			return Collections.emptyList();
		}
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfkchkGlTxnGlTxnCorpF8
	 *
	 * @param params
	 *
	 */
	public Corporates cgfkchkGlTxnGlTxnCorpF8(Corporates paramBean) {
		final String sql = getQuery("OTDAGJTR_CGFKCHK_GL_TXN_GL_TXN_CORP_F8");
		final RowMapper<Corporates> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, Corporates.class,
				corporatesMapping);
		Corporates returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfkchkGlTxnGlTxnPerF7
	 *
	 * @param params
	 *
	 */
	public Persons cgfkchkGlTxnGlTxnPerF7(Persons paramBean) {
		final String sql = getQuery("OTDAGJTR_CGFKCHK_GL_TXN_GL_TXN_PER_F7");
		final RowMapper<Persons> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, Persons.class, personsMapping);
		Persons returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfkchkGlTxnGlTxnTxnTyp
	 *
	 * @param params
	 *
	 */
	public TransactionTypes cgfkchkGlTxnGlTxnTxnTyp(TransactionTypes paramBean) {
		final String sql = getQuery("OTDAGJTR_CGFKCHK_GL_TXN_GL_TXN_TXN_TYP");
		final RowMapper<TransactionTypes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, TransactionTypes.class,
				transactionTypesMapping);
		TransactionTypes returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfkchkGlTxnGlTxnAcCode
	 *
	 * @param params
	 *
	 */
	public AccountCodes cgfkchkGlTxnGlTxnAcCode(AccountCodes paramBean) {
		final String sql = getQuery("OTDAGJTR_CGFKCHK_GL_TXN_GL_TXN_AC_CODE");
		final RowMapper<AccountCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, AccountCodes.class,
				accountCodesMapping);
		AccountCodes returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgwhenNewFormInstance
	 *
	 * @param params
	 *
	 */
	public List<SysDual> cgwhenNewFormInstance(SysDual paramBean) {
		final String sql = getQuery("OTDAGJTR_CGWHEN_NEW_FORM_INSTANCE");
		final RowMapper<SysDual> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, SysDual.class, mMapping);
		final ArrayList<SysDual> returnList = (ArrayList<SysDual>) namedParameterJdbcTemplate.query(sql, createParams(),
				columnRowMapper);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfkchkGlTxn1GlTxnAcCod
	 *
	 * @param params
	 *
	 */
	public AccountCodes cgfkchkGlTxn1GlTxnAcCod(AccountCodes paramBean) {
		final String sql = getQuery("OTDAGJTR_CGFKCHK_GL_TXN1_GL_TXN_AC_COD");
		final RowMapper<AccountCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, AccountCodes.class,
				accountCodesMapping);
		AccountCodes returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgrichkGlTransactions
	 *
	 * @param params
	 *
	 */
	public List<GlTransactions> cgrichkGlTransactions(GlTransactions paramBean) {
		final String sql = getQuery("OTDAGJTR_CGRICHK_GL_TRANSACTIONS");
		final RowMapper<GlTransactions> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, GlTransactions.class,
				glTransactionsMapping);
		final ArrayList<GlTransactions> returnList = (ArrayList<GlTransactions>) namedParameterJdbcTemplate.query(sql,
				createParams(), columnRowMapper);
		return returnList;
	}

	@Override
	public Date trustGLReopenClosedPeriod(String caseloadId) {
		Date txnDate = null;
		final SqlParameter[] sqlParameters = new SqlParameter[] {
				new SqlParameter("P_CASELOAD_ID", OracleTypes.VARCHAR),
				new SqlOutParameter("P_TXN_DATE", OracleTypes.DATE), };
		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("TRUST_GJ").withProcedureName("REOPEN_CLOSED_PERIOD").declareParameters(sqlParameters);
		Map<String, Object> inParamMap = new HashMap<String, Object>();
		inParamMap.put("P_CASELOAD_ID", caseloadId);
		final MapSqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		final Map<String, Object> returnObject = simpleJDBCCall.execute(inParameter);
		if (returnObject != null && returnObject.get("P_TXN_DATE") != null) {
			txnDate = (Date) returnObject.get("P_TXN_DATE");
		}
		return txnDate;

	}

	@Override
	public BigDecimal trustGjGetLastClosedPeriod(String caseloadId) {
		final String sql = getQuery("TRUST_GJ_GET_LAST_CLOSED_PERIOD");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql, createParams("caseloadId", caseloadId),
					BigDecimal.class);
		} catch (Exception e) {
			logger.error(
					"OtdagjtrRepositoryImpl trustGjGetLastClosedPeriod Unable to call TRUST_GJ.GET_LAST_CLOSED_PERIOD");
		}
		return null;
	}

	@Override
	public BigDecimal trustGjGetAllowedBackDatedPeriod(String caseloadId) {
		final String sql = getQuery("TRUST_GJ_GET_ALLOWED_BACK_DATED_PERIOD");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql, createParams("caseloadId", caseloadId),
					BigDecimal.class);
		} catch (Exception e) {
			logger.error(
					"OtdagjtrRepositoryImpl trustGjGetAllowedBackDatedPeriod Unable to call TRUST_GJ.GET_ALLOWED_BACK_DATED_PERIOD");
		}
		return null;
	}

	@Override
	public BigDecimal trustGjGetAccountPeriod(Date txnEntryDate) {
		final String sql = getQuery("TRUST_GJ_GET_ACCOUNT_PERIOD");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql, createParams("txnEntryDate", txnEntryDate),
					BigDecimal.class);
		} catch (Exception e) {
			logger.error("OtdagjtrRepositoryImpl trustGjGetAccountPeriod Unable to call TRUST_GJ.GET_ACCOUNT_PERIOD");
		}
		return null;
	}

	@Override
	public BigDecimal checkPeriodDays(String caseloadId, BigDecimal lvEnterAccountPeriod) {
		final String sql = getQuery("CHECK_PERIOD_DAYS");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql,
					createParams("caseloadId", caseloadId, "lvEnterAccountPeriod", lvEnterAccountPeriod),
					BigDecimal.class);
		} catch (Exception e) {
			logger.error("OtdagjtrRepositoryImpl checkPeriodDays Unable to call CHECK_PERIOD_DAYS Query");
		}
		return null;
	}

	@Override
	public String cAccountStatus(String caseloadId, BigDecimal lvEnterAccountPeriod) {
		final String sql = getQuery("C_ACCOUNT_STATUS");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql,
					createParams("caseloadId", caseloadId, "lvEnterAccountPeriod", lvEnterAccountPeriod), String.class);
		} catch (Exception e) {
			logger.error("OtdagjtrRepositoryImpl cAccountStatus Unable to call C_ACCOUNT_STATUS Query");
		}
		return null;
	}

	@Override
	public Date trustGjGetPeriodStartDate(BigDecimal lvEnterAccountPeriod) {
		final String sql = getQuery("TRUST_GJ_GET_PERIOD_START_DATE");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql,
					createParams("lvEnterAccountPeriod", lvEnterAccountPeriod), Date.class);
		} catch (Exception e) {
			logger.error(
					"OtdagjtrRepositoryImpl trustGjGetPeriodStartDate Unable to call TRUST_GJ.GET_PERIOD_START_DATE");
		}
		return null;
	}

	@Override
	public Date trustGjGetPeriodEndDate(BigDecimal lvLastClosedPeriod) {
		final String sql = getQuery("TRUST_GJ_GET_PERIOD_END_DATE");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql,
					createParams("lvLastClosedPeriod", lvLastClosedPeriod), Date.class);
		} catch (Exception e) {
			logger.error("OtdagjtrRepositoryImpl trustGjGetPeriodEndDate Unable to call TRUST_GJ.GET_PERIOD_END_DATE");
		}
		return null;
	}

	@Override
	public Map<String, Object> getTxnOp(String caseloadId, String caseloadType, String txnType) {
		final String sql = getQuery("GET_TXN_OP");
		try {
			return namedParameterJdbcTemplate.queryForMap(sql,
					createParams("txnType", txnType, "caseloadId", caseloadId));
		} catch (Exception e) {
			logger.error("OtdagjtrRepositoryImpl getTxnOp unable to call query for GET_TXN_OP", e);
		}

		return null;
	}

	@Override
	public Map<String, Object> getAccountTypeAndPostingType(BigDecimal acct, String caseloadType) {
		final String sql = getQuery("GET_ACCOUNT_TYPE_AND_POSTING_TYPE");
		try {
			return namedParameterJdbcTemplate.queryForMap(sql,
					createParams("acct", acct, "caseloadType", caseloadType));
		} catch (Exception e) {
			logger.error(
					"OtdagjtrRepositoryImpl getAccountTypeAndPostingType unable to call query for GET_ACCOUNT_TYPE_AND_POSTING_TYPE",
					e);
		}
		return null;
	}

	@Override
	public BigDecimal getCurrentBalance(BigDecimal acct, String caseloadId,String userId) {
		final String sql = getQuery("GET_CURRENT_BALANCE_FN");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql, createParams("acct", acct, "caseloadId", caseloadId,"userId",userId),
					BigDecimal.class);
		} catch (Exception e) {
			logger.error("OtdagjtrRepositoryImpl getCurrentBalance unable to call query for GET_CURRENT_BALANCE", e);
		}
		return null;
	}

	@Override
	public Map<String, Object> getProductionAndPayeeFlag(String caseloadId, String caseloadType, String txnType) {
		final String sql = getQuery("GET_PRODUCTION_AND_PAYEE_FLAG");
		Map<String, Object> returnData = new HashMap<>();
		try {
			returnData = namedParameterJdbcTemplate.queryForMap(sql,
					createParams("txnType", txnType, "caseloadId", caseloadId, "caseloadType", caseloadType));
		} catch (EmptyResultDataAccessException e) {
			logger.error(
					"OtdagjtrRepositoryImpl getProductionAndPayeeFlag Please setup Transaction Operation for OTDAGJTR.",
					e);
			returnData.put("error", true);
			returnData.put("msg", "otdagjtr.plzsetup");
			return returnData;
		} catch (IncorrectResultSizeDataAccessException e) {
			logger.error(
					"OtdagjtrRepositoryImpl getProductionAndPayeeFlag More then one Default Potential Payees setup found for + "
							+ txnType,
					e);
			returnData.put("error", true);
			returnData.put("codeOne", txnType);
			returnData.put("msg", "otdagjtr.mrethnone");
			return returnData;
		} catch (Exception e) {
			logger.error(
					"OtdagjtrRepositoryImpl getProductionAndPayeeFlag Please check setup of Transaction Operation for OTDAGJTR.",
					e);
			returnData.put("error", true);
			returnData.put("msg", "otdagjtr.plzsetup");
			return returnData;
		}
		return returnData;
	}

	@Override
	public Map<String, Object> defaultCorpPerson(String txnType) {
		final String sql = getQuery("DEFAULT_CORP_PERSON");
		try {
			return namedParameterJdbcTemplate.queryForMap(sql, createParams("txnType", txnType));
		} catch (Exception e) {
			logger.error("OtdagjtrRepositoryImpl defaultCorpPerson Unable to call DEFAULT_CORP_PERSON Query ", e);
		}
		return null;
	}

	@Override
	public String getPersonName(BigDecimal personId) {
		final String sql = getQuery("GET_PERSON_NAME");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql, createParams("personId", personId), String.class);
		} catch (Exception e) {
			logger.error("OtdagjtrRepositoryImpl getPersonName Unable to call GET_PERSON_NAME Query ", e);
		}
		return null;
	}

	@Override
	public String getCorporateName(BigDecimal corporateId) {
		final String sql = getQuery("GET_CORPORATE_NAME");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql, createParams("corporateName", corporateId),
					String.class);
		} catch (Exception e) {
			logger.error("OtdagjtrRepositoryImpl getCorporateName Unable to call GET_CORPORATE_NAME Query ", e);
		}
		return null;
	}

	@Override
	public List<GlTransactions> prGetOffsetAccounts(final GlTransactions paramBean) {
		final String sql = getQuery("PR_GET_OFFSET_ACCOUNTS");
		final RowMapper<GlTransactions> mRowMapper = Row2BeanRowMapper.makeMapping(sql, GlTransactions.class, mMapping);
		try {
			return namedParameterJdbcTemplate.query(sql,
					createParams("caseloadId", paramBean.getCaseloadId(), "txnType", paramBean.getTxnType()),
					mRowMapper);

		} catch (Exception e) {
			logger.error("OtdagjtrRepositoryImpl prGetOffsetAccounts Unable to call PR_GET_OFFSET_ACCOUNTS Query ", e);
		}
		return null;
	}

	@Override
	public Integer genTrustTrans(String seqId) {
		try {
			final String sql = getQuery("GEN_TRUST_TRANS");
			if (sql != null) {
				final String preparedSql = sql.replace("#SEQ", seqId);
				return namedParameterJdbcTemplate.queryForObject(preparedSql, createParams(), Integer.class);
			}
		} catch (Exception e) {
			logger.error("OTDRDTFU_MAIN_PROCESS_TXN_ID_CUR", e);
		}
		return null;
	}

	@Override
	public Integer trustInsertIntoChequeData(GlTransactions glTxnModule) {
		final String sql = getQuery("OMS_OWNER_TRUST_INSERT_INTO_CHEQUE_DATA");
		Integer returnValue = 1;
		Map<String, Object> inParamMap = new HashMap<String, Object>();
		inParamMap.put("P_CSLD_ID", glTxnModule.getCaseloadId());
		inParamMap.put("P_TRANS_NUMBER", glTxnModule.getTxnId());
		inParamMap.put("P_TRANS_AMOUNT", glTxnModule.getTxnEntryAmount());
		inParamMap.put("P_CHEQUE_FLAG", "G");
		inParamMap.put("P_START_TXN_ID", glTxnModule.getTxnEntryAmount());
		inParamMap.put("P_END_TXN_ID", glTxnModule.getTxnEntryAmount());
		inParamMap.put("P_PERS_PAYEE_ID", glTxnModule.getPayeePersonId());
		inParamMap.put("P_CORP_PAYEE_ID", glTxnModule.getPayeeCorporateId());
		inParamMap.put("P_PAYEE_NAME", glTxnModule.getPayeeNameText());
		inParamMap.put("P_OFFENDER_PAYEE", null);
		inParamMap.put("P_SINGLE_ENTRY", "1");
		inParamMap.put("P_BANK_ACT_CODE", glTxnModule.getAccountCode());
		inParamMap.put("P_MODULE_NAME", "OTDAGJTR");
		inParamMap.put("P_TRANS_TYPE", "GJ");
		namedParameterJdbcTemplate.update(sql, inParamMap);
		return returnValue;
	}
	public void trustGjReopenClosedPeriod (String caseloadId, Date txnDate) {
		try {
			final String sql = getQuery("OMS_OWNER_TRUST_GJ_REOPEN_CLOSED_PERIOD");
			Map<String, Object> inParamMap = new HashMap<String, Object>();
			inParamMap.put("P_CASELOAD_ID", caseloadId);
			inParamMap.put("P_TXN_DATE", txnDate);
			namedParameterJdbcTemplate.update(sql, inParamMap);
		} catch(Exception e) {
			logger.error("OtdagjtrRepositoryImpl trustGjReopenClosedPeriod unable to call procedure TRUST_GJ_REOPEN_CLOSED_PERIOD", e);
		}
	}

}

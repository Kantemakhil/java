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
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlInOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.AccountCodes;
import net.syscon.s4.im.beans.Corporates;
import net.syscon.s4.inmate.beans.GlTransactions;
import net.syscon.s4.inmate.trust.generalledger.OtdmgjtrRepository;
import net.syscon.s4.inst.booking.beans.Persons;
import oracle.jdbc.OracleTypes;

/**
 * Class OtdmgjtrRepositoryImpl
 */
@Repository
public class OtdmgjtrRepositoryImpl extends RepositoryBase implements OtdmgjtrRepository {

	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger log = LogManager.getLogger(OtdmgjtrRepositoryImpl.class.getName());
	private final Map<String, FieldMapper> accountCodesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("ACCOUNT_NAME", 					new FieldMapper("accountName"))
			.put("TXN_POSTING_TYPE", 				new FieldMapper("txnPostingType"))
			.put("ACCOUNT_TYPE", 					new FieldMapper("accountType"))
			.put("CASELOAD_TYPE", 					new FieldMapper("caseloadType"))
			.put("ACCOUNT_CODE", 					new FieldMapper("accountCode"))
			.put("accountPeriodStatus",              new FieldMapper("accountPeriodStatus")).build();
	private final Map<String, FieldMapper> mMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("ACCOUNT_NAME", 					new FieldMapper("accountName"))
			.put("TXN_POSTING_TYPE", 				new FieldMapper("txnPostingType"))
			.put("PERSON_ID", 						new FieldMapper("personId"))
			.put("ACCOUNT_CODE", 					new FieldMapper("accountCode")).put("LAST_NAME", new FieldMapper("lastName")).build();
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
			.put("TXN_ENTRY_DESC", 					new FieldMapper("txnEntryDesc")).build();
	private final Map<String, FieldMapper> caseloadIdMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CORPORATE_ID", 					new FieldMapper("corporateId")).build();
	private final Map<String, FieldMapper> corporatesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CORPORATE_NAME", 					new FieldMapper("corporateName"))
			.put("PAYEE_CORPORATE_ID", 				new FieldMapper("payeeCorporateId")).build();

	/**
	 * Creates new OtdmgjtrRepositoryImpl class Object
	 */
	public OtdmgjtrRepositoryImpl() {
		
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            GlTransactions
	 *
	 * @return List<GlTransactions>
	 *
	 * @
	 */
	public List<GlTransactions> glTxnExecuteQuery(final GlTransactions objSearchDao) {
		final String sql = getQuery("OTDMGJTR_GLTXN_FIND_GL_TRANSACTIONS");
		final RowMapper<GlTransactions> GlTransactionsRowMapper = Row2BeanRowMapper.makeMapping(sql,
				GlTransactions.class, glTransactionsMapping);
		final ArrayList<GlTransactions> returnList = (ArrayList<GlTransactions>) namedParameterJdbcTemplate.query(sql,
				createParams(), GlTransactionsRowMapper);
		return returnList;
	}



	/**
	 * Used to capture results from select query
	 * 
	 * @return List<MCrp.corporateNameCrp.caseloadId>
	 */
	public List<Corporates> cgfkGlTxnPayeeCorporateIdRecordGroup() {
		final String sql = getQuery("OTDMGJTR_FIND_CGFKGLTXNPAYEECORPORATEID");
		final RowMapper<Corporates> caseloadIdRowMapper = Row2BeanRowMapper.makeMapping(sql, Corporates.class,
				caseloadIdMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), caseloadIdRowMapper);
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<AccountCodes> cgfkGlTxn1AccountCodeRecordGroup(final String caseloadType,final String caseloadId) {
		final String sql = getQuery("OTDMGJTR_FIND_CGFKGLTXN1ACCOUNTCODE");
		List<AccountCodes> returnList= new ArrayList<AccountCodes>();
		final RowMapper<AccountCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, AccountCodes.class, mMapping);

		try {
			returnList= namedParameterJdbcTemplate.query(sql, createParams("CASELOADTYPE",caseloadType,"CASELOADID",caseloadId), mRowMapper);
			for (AccountCodes accountCodes : returnList) {
				accountCodes.setListSeq(new BigDecimal(accountCodes.getAccountCode()));
			}
		} catch (Exception e) {
			log.error("cgfkGlTxn1AccountCodeRecordGroup",e);
		}
		return returnList;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<AccountCodes> cgfkGlTxnAccountCodeRecordGroup() {
		final String sql = getQuery("OTDMGJTR_FIND_CGFKGLTXNACCOUNTCODE");
		final RowMapper<AccountCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, AccountCodes.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<Persons> cgfkGlTxnPayeePersonIdRecordGroup() {
		final String sql = getQuery("OTDMGJTR_FIND_CGFKGLTXNPAYEEPERSONID");
		final RowMapper<Persons> mRowMapper = Row2BeanRowMapper.makeMapping(sql, Persons.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
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
	public Corporates cgfkchkGlTxnGlTxnCorpF8(final Corporates paramBean) {
		final String sql = getQuery("OTDMGJTR_CGFKCHK_GL_TXN_GL_TXN_CORP_F8");
		final RowMapper<Corporates> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, Corporates.class,
				corporatesMapping);
		Corporates returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
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
		final String sql = getQuery("OTDMGJTR_CGFKCHK_GL_TXN_GL_TXN_AC_CODE");
		final RowMapper<AccountCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, AccountCodes.class,
				accountCodesMapping);
		AccountCodes returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfkchkGlTxn1GlTxnAcCod
	 *
	 * @param params
	 *
	 */
	public AccountCodes cgfkchkGlTxn1GlTxnAcCod(final AccountCodes paramBean) {
		final String sql = getQuery("OTDMGJTR_CGFKCHK_GL_TXN1_GL_TXN_AC_COD");
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
	public List<GlTransactions> cgrichkGlTransactions(final GlTransactions paramBean) {
		final String sql = getQuery("OTDMGJTR_CGRICHK_GL_TRANSACTIONS");
		final RowMapper<GlTransactions> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, GlTransactions.class,
				glTransactionsMapping);
		final ArrayList<GlTransactions> returnList = (ArrayList<GlTransactions>) namedParameterJdbcTemplate.query(sql,
				createParams(), columnRowMapper);
		return returnList;
	}

	
	public List<AccountCodes> getDescandType(final String code,final String caseloadType) {
		final String sql = getQuery("OTDMGJTR_GET_DESC_TYPE");
		List<AccountCodes> returnList= new ArrayList<AccountCodes>();
		final RowMapper<AccountCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, AccountCodes.class, mMapping);

		try {
			returnList= namedParameterJdbcTemplate.query(sql, createParams("code",code,"caseloadType",caseloadType), mRowMapper);
		} catch (Exception e) {
			log.error("getDescandType",e);
		}
		return returnList;
	}

	
	public Integer lvlLastclosedPeriod(final String caseloadId) {
		final String sql = getQuery("OTDMGJTR_LVLLASTCLOSEDPERIOD");
		Integer  returnList= 0;
		try {
			returnList= namedParameterJdbcTemplate.queryForObject(sql, createParams("caseloadId",caseloadId), Integer.class);
		} catch (Exception e) {
			log.error("getDescandType",e);
		}
		return returnList;
	}
	
	public Integer lvAllowedReopenPeriod(final String caseloadId) {
		final String sql = getQuery("OTDMGJTR_LVALLOWEDREOPENPERIOD");
		Integer  returnList= 0;
		try {
			returnList= namedParameterJdbcTemplate.queryForObject(sql, createParams("caseloadId",caseloadId), Integer.class);
		} catch (Exception e) {
			log.error("getDescandType",e);
		}
		return returnList;
	}

	
	public Integer lvEnteraccountPeriod(final Date txnEntryDate) {
		final String sql = getQuery("OTDMGJTR_LVENTERACCOUNTPERIOD");
		Integer  returnList= 0;
		try {
			returnList= namedParameterJdbcTemplate.queryForObject(sql, createParams("txnEntryDate",txnEntryDate), Integer.class);
		} catch (Exception e) {
			log.error("getDescandType",e);
		}
		return returnList;
	}

	
	public Integer isPeriodValid(final String caseloadId, final Integer lventerAccountPeriod) {
		final String sql = getQuery("OTDMGJTR_ISPERIODVALID");
		Integer  returnList= 0;
		try {
			returnList= namedParameterJdbcTemplate.queryForObject(sql, createParams("caseloadId",caseloadId,"lventerAccountPeriod",lventerAccountPeriod), Integer.class);
		} catch (Exception e) {
			log.error("getDescandType",e);
		}
		return returnList;
	}

	
	public String lvAccountStatus(final Integer lventerAccountPeriod, final String caseloadId) {
		final String sql = getQuery("OTDMGJTR_LVACCOUNTSTATUS");
		String  returnList= null;
		try {
			returnList= namedParameterJdbcTemplate.queryForObject(sql, createParams("lventerAccountPeriod",lventerAccountPeriod,"caseloadId",caseloadId), String.class);
		} catch (Exception e) {
			log.error("getDescandType",e);
		}
		return returnList;
	}

	
	public Date getPeriodStartDate(final Integer lventerAccountPeriod) {
		final String sql = getQuery("OTDMGJTR_GETPERIODSTARTDATE");
		Date  returnList= null;
		try {
			returnList= namedParameterJdbcTemplate.queryForObject(sql, createParams("lventerAccountPeriod",lventerAccountPeriod), Date.class);
		} catch (Exception e) {
			log.error("getDescandType",e);
		}
		return returnList;
	}
	
	public Date getperiodEndDate(final Integer lvlastClosedPeriod) {
		final String sql = getQuery("OTDMGJTR_GETPERIODENDDATE");
		Date  returnList= null;
		try {
			returnList= namedParameterJdbcTemplate.queryForObject(sql, createParams("lvlastClosedPeriod",lvlastClosedPeriod), Date.class);
		} catch (Exception e) {
			log.error("getDescandType",e);
		}
		return returnList;
	}

	
	public Integer isAccountchecking(final String caseloadId, final Integer accountCode) {
		final String sql = getQuery("OTDMGJTR_ISACCOUNTCHECKING");
		Integer  returnList= null;
		try {
			returnList= namedParameterJdbcTemplate.queryForObject(sql, createParams("caseloadId",caseloadId,"accountCode",accountCode), Integer.class);
		} catch (Exception e) {
			log.error("getDescandType",e);
		}
		return returnList;
	}

	
	public Integer getTrustTrans() {
		final String sql = getQuery("OTDMGJTR_GET_NEXTVAL");
		Integer  returnList= 0;
		try {
			returnList= namedParameterJdbcTemplate.queryForObject(sql, createParams(), Integer.class);
		} catch (Exception e) {
			log.error("getDescandType",e);
		}
		return returnList;
	}

	
	public void trustGjReopenClosedPeriod(final String caseloadId, final Date txnEntryDate) {
		SqlParameter[] sqlParameters = new SqlParameter[] { new SqlParameter("P_CASELOAD_ID", OracleTypes.VARCHAR),
				new SqlParameter("P_TXN_DATE", OracleTypes.DATE) };
		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("TRUST_GJ").withProcedureName("REOPEN_CLOSED_PERIOD").declareParameters(sqlParameters);
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		inParamMap.put("P_CASELOAD_ID", caseloadId);
		inParamMap.put("P_TXN_DATE", txnEntryDate);
		final MapSqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		try {
			simpleJDBCCall.execute(inParameter);
		} catch (Exception e) {
			log.error("trustGjReopenClosedPeriod", e);
		}

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
	public Integer glTxnInsertGlTransactions(final List<GlTransactions> glList) {

		String sql = getQuery("OTDMGJTR_GLTXN_INSERT_GL_TRANSACTIONS");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (GlTransactions glTransactions : glList) {
			parameters.add(new BeanPropertySqlParameterSource(glTransactions));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (glList.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}
	public Integer insertIntoCheckData(final GlTransactions obj) {
		final String sql = getQuery("OTDMGJTR_OMS_OWNER_TRUST_INSERT_INTO_CHEQUE_DATA");
		Integer returnValue = 1;
		Map<String, Object> inParamMap = new HashMap<String, Object>();
		inParamMap.put("P_CSLD_ID", obj.getCaseloadId());
		inParamMap.put("P_TRANS_NUMBER", obj.getTxnId());
		inParamMap.put("P_TRANS_AMOUNT", obj.getTxnEntryAmount());
		inParamMap.put("P_CHEQUE_FLAG", "G");
		inParamMap.put("P_START_TXN_ID", obj.getTxnId());
		inParamMap.put("P_END_TXN_ID", obj.getTxnId());
		inParamMap.put("P_PERS_PAYEE_ID", obj.getPayeePersonId());
		inParamMap.put("P_CORP_PAYEE_ID", obj.getPayeeCorporateId());
		inParamMap.put("P_PAYEE_NAME", obj.getPayeeNameText());
		inParamMap.put("P_OFFENDER_PAYEE", null);
		inParamMap.put("P_SINGLE_ENTRY", "1");
		inParamMap.put("P_BANK_ACT_CODE", obj.getAccountCode());
		inParamMap.put("P_MODULE_NAME", "OTDMGJTR");
		inParamMap.put("P_TRANS_TYPE", "GJ");
		namedParameterJdbcTemplate.update(sql, inParamMap);
		return returnValue;
	}

	
	public String cStatusNumber(final AccountCodes accountCodes) {
		final String sql = getQuery("OTDMGJTR_LVACCOUNTSTATUS");
		String returnList=null;
		try {
			returnList= namedParameterJdbcTemplate.queryForObject(sql, createParams("lventerAccountPeriod",accountCodes.getLvEnteraccountPeriodId(),"caseloadId",accountCodes.getCaseloadId()), String.class);
		} catch (Exception e) {
			log.error("getDescandType",e);
		}
		return returnList;
	}

	public BigDecimal getCurrentBalance(final String caseloadId, final Integer accountCode) {
		BigDecimal value = BigDecimal.valueOf(0);
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[3];
		sqlParameters = new SqlParameter[] { new SqlInOutParameter("RETURN_VALUE", OracleTypes.NUMBER),
				new SqlParameter("P_CASELOAD_ID", OracleTypes.VARCHAR),
				new SqlParameter("P_ACCOUNT_CODE", OracleTypes.VARCHAR) };

		final SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("TRUST_GJ").withFunctionName("GET_CURRENT_BALANCE").declareParameters(sqlParameters);
		inParamMap.put("P_CASELOAD_ID", caseloadId);
		inParamMap.put("P_ACCOUNT_CODE", accountCode);
		final SqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		value = simpleJDBCCall.executeFunction(BigDecimal.class, inParameter);
		return value;
	}
}

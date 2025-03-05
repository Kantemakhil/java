package net.syscon.s4.inmate.trust.trustaccounts.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
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
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.AccountCodes;
import net.syscon.s4.im.beans.OffenderTransactions;
import net.syscon.s4.im.beans.OffenderTrustAccounts;
import net.syscon.s4.im.beans.SysDual;
import net.syscon.s4.inmate.beans.OffenderSubAccounts;
import net.syscon.s4.inmate.trust.trustaccounts.OtdopctaRepository;
import oracle.jdbc.OracleTypes;

/**
 * Class OtdopctaRepositoryImpl
 */
@Repository
public class OtdopctaRepositoryImpl extends RepositoryBase implements OtdopctaRepository {

	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OtdopctaRepositoryImpl.class.getName());

	private final Map<String, FieldMapper> offSubAccMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("caseloadId", 					new FieldMapper("caseloadId"))
			.put("offenderId", 					new FieldMapper("offenderId "))
			.build();
	private final Map<String, FieldMapper> sysProfMapping = new ImmutableMap.Builder<String, FieldMapper>()
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
	private final Map<String, FieldMapper> offTrstAccMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CREATE_USER_ID", 				new FieldMapper("createUserId"))
			.put("MODIFY_USER_ID", 				new FieldMapper("modifyUserId"))
			.put("LIST_SEQ", 					new FieldMapper("listSeq"))
			.put("HOLD_BALANCE", 				new FieldMapper("holdBalance"))
			.put("CURRENT_BALANCE", 			new FieldMapper("currentBalance"))
			.put("OFFENDER_ID", 				new FieldMapper("offenderId"))
			.put("CASELOAD_ID", 				new FieldMapper("caseloadId"))
			.put("MODIFY_DATE", 				new FieldMapper("modifyDate"))
			.put("SEAL_FLAG", 					new FieldMapper("sealFlag"))
			.put("ACCOUNT_CLOSED_FLAG", 		new FieldMapper("accountClosedFlag"))
			.put("CREATE_DATETIME", 			new FieldMapper("createDatetime"))
			.put("MODIFY_DATETIME", 			new FieldMapper("modifyDatetime"))
			.put("NOTIFY_DATE", 				new FieldMapper("notifyDate"))
			.build();
	private final Map<String, FieldMapper> sysDualMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("SYSDATE", 					new FieldMapper("sysDate"))
			.put("USER", 						new FieldMapper("user"))
			.build();
	private final Map<String, FieldMapper> accCodesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("ACCOUNT_NAME", 				new FieldMapper("accountName"))
			.put("ACCOUNT_CODE", 				new FieldMapper("accountCode"))
			.put("SUB_ACCOUNT_TYPE", 			new FieldMapper("subAccountType"))
			.put("TXN_POSTING_TYPE", 			new FieldMapper("txnPostingType"))
			.build();

	/**
	 * Creates new OtdopctaRepositoryImpl class Object
	 */
	public OtdopctaRepositoryImpl() {
		// OtdopctaRepositoryImpl
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            OffenderTrustAccounts
	 *
	 * @return List<OffenderTrustAccounts>
	 *
	 * 
	 */
	public List<OffenderTrustAccounts> offTaExecuteQuery(final OffenderTrustAccounts objSearchDao) {
		final String sql = getQuery("OTDOPCTA_OFFTA_FIND_OFFENDER_TRUST_ACCOUNTS");
		String preparedSql = null;
		final StringBuffer sqlQuery = new StringBuffer();
		final MapSqlParameterSource valuesList = new MapSqlParameterSource();
		List<OffenderTrustAccounts> lstTransactions = new ArrayList<OffenderTrustAccounts>();
		if (objSearchDao != null) {
			if (objSearchDao.getOffenderId() > 0 && objSearchDao.getCaseloadId() != null) {
				final RowMapper<OffenderTrustAccounts> offTrustRM = Row2BeanRowMapper.makeMapping(sql,
						OffenderTrustAccounts.class, offTrstAccMapping);
				sqlQuery.append(sql);
				sqlQuery.append(" where ");
				sqlQuery.append("OFFENDER_ID  = :offenderId and CASELOAD_ID = :caseloadId");
				valuesList.addValue("offenderId", objSearchDao.getOffenderId());
				valuesList.addValue("caseloadId", objSearchDao.getCaseloadId());
				preparedSql = sqlQuery.toString().trim();
				lstTransactions = (List<OffenderTrustAccounts>) namedParameterJdbcTemplate.query(preparedSql,
						valuesList, offTrustRM);
			}
		}
		return lstTransactions;
	}

	/**
	 * @param
	 *
	 * 
	 *
	 */
	public Integer preInsert() {
		final String sql = getQuery("OTDOPCTA_TXN_ID_SEQUENCE");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams(), Integer.class);
	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstOffTrustAc
	 *            List<OffenderTrustAccounts>
	 *
	 * 
	 */
	public Integer offTaUpdateOffenderTrustAccounts(final List<OffenderTrustAccounts> lstOffTrustAc) {
		final String sql = getQuery("OTDOPCTA_OFFTA_UPDATE_OFFENDER_TRUST_ACCOUNTS");
		int returnValue = 0;
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffenderTrustAccounts offTrustAc : lstOffTrustAc) {
			parameters.add(new BeanPropertySqlParameterSource(offTrustAc));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstOffTrustAc.size() == returnArray.length) {
			returnValue = 1;
		}
		return returnValue;
	}

	/**
	 * This method is used to delete records from data base tables based on
	 *
	 * @param lstOffTrustAc
	 *            List<OffenderTrustAccounts>
	 *
	 * 
	 */
	public Integer offTaDeleteOffenderTrustAccounts(final List<OffenderTrustAccounts> lstOffTrustAc) {
		final String sql = getQuery("OTDOPCTA_OFFTA_DELETE_OFFENDER_TRUST_ACCOUNTS");
		int returnValue = 0;
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffenderTrustAccounts offTrustAc : lstOffTrustAc) {
			parameters.add(new BeanPropertySqlParameterSource(offTrustAc));
		}
		try {
			String tableName = "OFFENDER_TRUST_ACCOUNTS";
			String whereClause = "CASELOAD_ID  = :caseloadId  and OFFENDER_ID  = :offenderId";
			batchUpdatePreDeletedRows(tableName, whereClause , parameters);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method offTaDeleteOffenderTrustAccounts", e);
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstOffTrustAc.size() == returnArray.length) {
			returnValue = 1;
		}
		return returnValue;

	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            SystemProfiles
	 *
	 * @return List<SystemProfiles>
	 *
	 * 
	 */
	public List<SystemProfiles> sysPflExecuteQuery(final SystemProfiles objSearchDao) {
		final String sql = getQuery("OTDOPCTA_SYSPFL_FIND_SYSTEM_PROFILES");
		final RowMapper<SystemProfiles> sysProfRM = Row2BeanRowMapper.makeMapping(sql, SystemProfiles.class,
				sysProfMapping);
		final ArrayList<SystemProfiles> returnList = (ArrayList<SystemProfiles>) namedParameterJdbcTemplate.query(sql,
				createParams(), sysProfRM);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgwhenNewFormInstance
	 *
	 * @param params
	 *
	 */
	public List<SysDual> cgwhenNewFormInstance(final SysDual paramBean) {
		final String sql = getQuery("OTDOPCTA_CGWHEN_NEW_FORM_INSTANCE");
		final RowMapper<SysDual> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, SysDual.class, sysDualMapping);
		final ArrayList<SysDual> returnList = (ArrayList<SysDual>) namedParameterJdbcTemplate.query(sql, createParams(),
				columnRowMapper);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgrichkOffenderTrustAccoun
	 *
	 * @param params
	 *
	 */
	public List<OffenderSubAccounts> cgrichkOffenderTrustAccoun(final OffenderSubAccounts paramBean) {
		final String sql = getQuery("OTDOPCTA_CGRICHK_OFFENDER_TRUST_ACCOUN");
		final RowMapper<OffenderSubAccounts> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderSubAccounts.class, offSubAccMapping);
		final List<OffenderSubAccounts> returnObj = (ArrayList<OffenderSubAccounts>) namedParameterJdbcTemplate.query(
				sql,
				createParams("P_OFFENDER_ID", paramBean.getOffenderId(), "P_CASELOAD_ID", paramBean.getCaseloadId()),
				columnRowMapper);
		return returnObj;
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
		inParamMap.put("P_PAYEE_NAME_TEXT", "");
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
	 * This method will update the transaction in the table
	 * 
	 * @param offTrans
	 * @return Integer
	 */
	@Override
	public Integer updateOffenderSubAccounts(final OffenderTransactions offTrans) {
		final String sql = getQuery("OTDOPCTA_UPDATE_OFFENDER_SUB_ACCOUNTS");
		return namedParameterJdbcTemplate.update(sql,
				createParams("OFFENDERID", offTrans.getOffenderId(), "CASELOADID", offTrans.getCaseloadId()));
	}

	/**
	 * This method will update the transaction in the table
	 * 
	 * @param offTrans
	 * @return Integer
	 */
	public String getAcAndSetIndDate(final OffenderTransactions paramBean) {
		String openAnAccount = null;
		Map<String, Object> inParamMap = new HashMap<String, Object>();
		inParamMap.put("p_csld_id", paramBean.getCaseloadId());
		inParamMap.put("p_off_id", paramBean.getOffenderId());
		try {
			namedParameterJdbcTemplate
					.update("call OMS_OWNER.DEDUCTIONS.GET_AC_AND_SET_IND_DATE(:p_off_id, :p_csld_id)", inParamMap);
			openAnAccount = "GETINDDATE";
		} catch (Exception e) {
			logger.error("getAcAndSetIndDate" + e);
		}
		return openAnAccount;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            SystemProfiles
	 *
	 * @return List<AccountCodes>
	 *
	 * 
	 */
	public AccountCodes getSubAccountType(final OffenderTransactions objSearchDao) {
		final String sql = getQuery("OTDOPCTA_ACCOUNT_CODES_TYPE");
		final RowMapper<AccountCodes> accountCodesRM = Row2BeanRowMapper.makeMapping(sql, AccountCodes.class,
				accCodesMapping);
		final AccountCodes returnList = namedParameterJdbcTemplate
				.queryForObject(sql,
						createParams("TXNTYPE", objSearchDao.getTxnType(), "CASELOADID", objSearchDao.getCaseloadId(),
								"CASELOADTYPE", objSearchDao.getCaseloadType(), "MODULENAME", "OTDOPCTA"),
						accountCodesRM);
		return returnList;
	}

	/**
	 * This method will update the transaction in the table
	 * 
	 * @param offTrans
	 * @return Integer
	 */
	@Override
	public String checkAccountSatus(final OffenderTransactions offTrans) {
		String openAnAccount = null;
		Map<String, Object> returnObject = null;
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[10];
		sqlParameters = new SqlParameter[] { new SqlParameter("P_CSLD_ID", OracleTypes.VARCHAR),
				new SqlParameter("P_OFFENDER_ID", OracleTypes.NUMBER),
				new SqlOutParameter("P_OPEN_AN_ACCOUNT", OracleTypes.VARCHAR), };
		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("TRUST").withProcedureName("CHK_ACCOUNT_STATUS").declareParameters(sqlParameters);
		inParamMap.put("P_CSLD_ID", offTrans.getCaseloadId());
		inParamMap.put("P_OFFENDER_ID", offTrans.getOffenderId());
		final SqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		try {
			returnObject = simpleJDBCCall.execute(inParameter);
			openAnAccount = String.valueOf(returnObject.get("P_OPEN_AN_ACCOUNT"));
		} catch (Exception e) {
			logger.error("checkAccountSatus" + e);
		}
		return openAnAccount;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            SystemProfiles
	 *
	 * @return String
	 *
	 * 
	 */
	public String getTransactionType(final String caseloadId) {
		final String sql = getQuery("OTDOPCTA_OFFENDER_TRUST_ACCOUNT_TXN_TYPE");
		String strTxnType = null;
		try {
			strTxnType = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("CASELOADID", caseloadId, "MODULENAME", "OTDOPCTA"), String.class);
		} catch (EmptyResultDataAccessException e) {
			logger.error("getTransactionType", e);
			return strTxnType;
		}
		return strTxnType;
	}

}
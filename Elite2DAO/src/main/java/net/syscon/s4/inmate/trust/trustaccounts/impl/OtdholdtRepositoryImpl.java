package net.syscon.s4.inmate.trust.trustaccounts.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.OffenderTransactions;
import net.syscon.s4.im.beans.Offenders;
import net.syscon.s4.im.beans.SysDual;
import net.syscon.s4.inmate.trust.trustaccounts.OtdholdtRepository;
import oracle.jdbc.OracleTypes;

/**
 * Class OtdholdtRepositoryImpl
 */
@Repository
public class OtdholdtRepositoryImpl extends RepositoryBase implements OtdholdtRepository {

	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OtdholdtRepositoryImpl.class.getName());
	

	private final Map<String, FieldMapper> referenceCodesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DOMAIN", new FieldMapper("domain"))
			.put("DESCRIPTION", new FieldMapper("description"))
			.put("SUB_ACCOUNT_TYPE", new FieldMapper("subAccountType"))
			.build();
	private final Map<String, FieldMapper> systemProfilesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("PROFILE_CODE", new FieldMapper("profileCode"))
			.put("PROFILE_TYPE", new FieldMapper("profileType"))
			.put("CREATE_USER_ID", new FieldMapper("createUserId"))
			.put("SEAL_FLAG", new FieldMapper("sealFlag"))
			.put("CREATE_DATETIME", new FieldMapper("createDatetime"))
			.put("MODIFY_USER_ID", new FieldMapper("modifyUserId"))
			.put("OLD_TABLE_NAME", new FieldMapper("oldTableName"))
			.put("PROFILE_VALUE", new FieldMapper("profileValue"))
			.put("MODIFY_DATETIME", new FieldMapper("modifyDatetime"))
			.put("PROFILE_VALUE_2", new FieldMapper("profileValue2"))
			.put("DESCRIPTION", new FieldMapper("description"))
			.build();
	private final Map<String, FieldMapper> mMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CODE", new FieldMapper("code"))
			.put("DESCRIPTION", new FieldMapper("description"))
			.put("DOMAIN", new FieldMapper("domain"))
			.build();
	private final Map<String, FieldMapper> offTransMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DEDUCTION_FLAG", new FieldMapper("deductionFlag"))
			.put("TXN_ENTRY_SEQ", new FieldMapper("txnEntrySeq"))
			.put("DEDUCTION_TYPE", new FieldMapper("deductionType"))
			.put("REMITTER_NAME", new FieldMapper("remitterName"))
			.put("ROOT_OFFENDER_ID", new FieldMapper("rootOffenderId"))
			.put("PAYEE_CORPORATE_ID", new FieldMapper("payeeCorporateId"))
			.put("SUB_ACCOUNT_TYPE", new FieldMapper("subAccountType"))
			.put("TXN_ENTRY_DESC", new FieldMapper("txnEntryDesc"))
			.put("PRE_WITHHOLD_AMOUNT", new FieldMapper("preWithholdAmount"))
			.put("TXN_POSTING_TYPE", new FieldMapper("txnPostingType"))
			.put("CREATE_USER_ID", new FieldMapper("createUserId"))
			.put("SEAL_FLAG", new FieldMapper("sealFlag"))
			.put("CREATE_DATETIME", new FieldMapper("createDateTime"))
			.put("MODIFY_USER_ID", new FieldMapper("modifyUserId"))
			.put("MODIFY_DATETIME", new FieldMapper("modifyDateTime"))
			.build();
	private final Map<String, FieldMapper> sysDualMapping = new ImmutableMap.Builder<String, FieldMapper>().build();
	
	/**
	 * Creates new OtdholdtRepositoryImpl class Object
	 */
	public OtdholdtRepositoryImpl() {
		//OtdholdtRepositoryImpl
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            OffenderTransactions
	 *
	 * @return List<OffenderTransactions>
	 *
	 * 
	 */
	public BigDecimal offTxnExecuteQuery(final Offenders objSearchDao) {
		String preparedSql = null;
		final StringBuffer sqlQuery = new StringBuffer();
		final MapSqlParameterSource valuesList = new MapSqlParameterSource();
		BigDecimal lstTransactions = BigDecimal.ZERO;
		if (objSearchDao != null) {
			if (objSearchDao.getOffenderId() != null && objSearchDao.getCaseloadType() != null) {
				final String sql = getQuery("OTDHOLDT_OFFTXN_FIND_OFFENDER_TRANSACTIONS");
//				final RowMapper<OffenderTransactions> offTransRowMapper = Row2BeanRowMapper.makeMapping(sql,
//						OffenderTransactions.class, offTransMapping);
				sqlQuery.append(sql);
				sqlQuery.append(" where ");
				sqlQuery.append("OFFENDER_ID  = :offenderId and CASELOAD_ID = :caseloadId");
				valuesList.addValue("offenderId", objSearchDao.getRootOffenderId());
				valuesList.addValue("caseloadId", objSearchDao.getCaseloadType());
				preparedSql = sqlQuery.toString().trim();
				lstTransactions = namedParameterJdbcTemplate.queryForObject(preparedSql, valuesList,BigDecimal.class);
			}
		}
		return lstTransactions;
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
	 * 
	 */
	public Integer offTxnInsertOffenderTransactions(final List<OffenderTransactions> lstOffTrans) {
		int inValue = 100;
		final String sql = getQuery("OTDHOLDT_OFFTXN_INSERT_OFFENDER_TRANSACTIONS");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffenderTransactions objTrans : lstOffTrans) {
			parameters = new ArrayList<SqlParameterSource>();
			parameters.add(new BeanPropertySqlParameterSource(objTrans));
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
			if (  lstOffTrans.size() == returnArray.length) {
				inValue = 1;
			}
		}
		return inValue;
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
		final String sql = getQuery("OTDHOLDT_SYSPFL_FIND_SYSTEM_PROFILES");
		final RowMapper<SystemProfiles> sysProfRM = Row2BeanRowMapper.makeMapping(sql,
				SystemProfiles.class, systemProfilesMapping);
		final ArrayList<SystemProfiles> returnList = (ArrayList<SystemProfiles>) namedParameterJdbcTemplate.query(sql,
				createParams(), sysProfRM);
		return returnList;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<ReferenceCodes> cgfkOffTxnSubAccountTypeRecordGroup(final String caseLoadId) {
		final String sql = getQuery("OTDHOLDT_FIND_CGFKOFFTXNSUBACCOUNTTYPE");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams("caseloadId", caseLoadId), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("In cgfkOffTxnSubAccountTypeRecordGroup :" + e);
			return Collections.emptyList();
		}
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * offBkgOnCheckDeleteMaster
	 *
	 * @param params
	 *
	 */
	public List<OffenderTransactions> offBkgOnCheckDeleteMaster(final OffenderTransactions paramBean) {
		final String sql = getQuery("OTDHOLDT_OFF_BKG_ONCHECKDELETEMASTER");
		final RowMapper<OffenderTransactions> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderTransactions.class, offTransMapping);
		final ArrayList<OffenderTransactions> returnList = (ArrayList<OffenderTransactions>) namedParameterJdbcTemplate
				.query(sql, createParams(), columnRowMapper);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfkchkOffTxnOffTxnRef
	 *
	 * @param params
	 *
	 */
	public ReferenceCodes cgfkchkOffTxnOffTxnRef(final ReferenceCodes paramBean) {
		final String sql = getQuery("OTDHOLDT_CGFKCHK_OFF_TXN_OFF_TXN_REF_C");
		final RowMapper<ReferenceCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				referenceCodesMapping);
		return namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
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
		final String sql = getQuery("OTDHOLDT_CGWHEN_NEW_FORM_INSTANCE");
		final RowMapper<SysDual> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, SysDual.class, sysDualMapping);
		return namedParameterJdbcTemplate.query(sql, createParams(),
				columnRowMapper);
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * getExistingHoldAmount
	 *
	 * @param params
	 *
	 */
	public BigDecimal getExistingHoldAmount(final Offenders paramBean) {
		final String sql = getQuery("OTDHOLDT_NBT_EXISTING_HOLD_AMOUNT");
		BigDecimal holdAmount = null;
		try {
			holdAmount = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("OFFENDERID", paramBean.getRootOffenderId(), "CASELOADID", paramBean.getCaseloadType()),
					BigDecimal.class);
		} catch (EmptyResultDataAccessException e) {
			holdAmount = null;
			logger.error("In getExistingHoldAmount :" + e);
		}
		return holdAmount;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * getSubAccountBalance
	 *
	 * @param params
	 *
	 */
	public BigDecimal getSubAccountBalance(final Offenders paramBean) {
		final String sql = getQuery("OTDHOLDT_NBT_SUB_ACCOUNT_AMOUNT");
		BigDecimal holdAmount = null;
		try {
			holdAmount = namedParameterJdbcTemplate.queryForObject(
					sql, createParams("OFFENDERID", paramBean.getRootOffenderId(), "CASELOADID",
							paramBean.getCaseloadType(), "SUBACCOUNTTYPE", paramBean.getAddInfoCode()),
					BigDecimal.class);
		} catch (EmptyResultDataAccessException e) {
			holdAmount = null;
			logger.error("In getSubAccountBalance :" + e);
		}
		return holdAmount;
	}

	/**
	 * This method will get the sequence
	 * 
	 * @return Integer
	 * 
	 */
	public Integer preInsert() {
		final String sql = getQuery("OTDHOLDT_TXN_ID_SEQUENCE");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams(), Integer.class);
	}

	/**
	 * This method will get the hold number sequence
	 * 
	 * @return Integer
	 */
	public Integer preInsertHoldNumber() {
		final String sql = getQuery("OTDHOLDT_HOLD_NUMBER_SEQUENCE");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams(), Integer.class);
	}

	/**
	 * This method will get the max offenderBookId
	 * @return Integer
	 */
	public Integer getMaxOffenderBookId(final Long offenderId) {
		final String sql = getQuery("OTDHOLD_MAX_OFFENDER_BOOK_ID");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("OFFENDERID", offenderId), Integer.class);
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * @return Integer
	 *
	 */
	@Override
	public Integer updateOffenderBalance(final OffenderTransactions offTrans) {

		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[10];
		sqlParameters = new SqlParameter[] { 
				new SqlParameter("P_CSLD_ID", OracleTypes.VARCHAR),
				new SqlParameter("P_OFF_ID", OracleTypes.NUMBER),
				new SqlParameter("P_TRANS_POST_TYPE", OracleTypes.VARCHAR),
				new SqlParameter("P_TRANS_DATE", OracleTypes.DATE),
				new SqlParameter("P_TRANS_NUMBER", OracleTypes.NUMBER),
				new SqlParameter("P_TRANS_TYPE", OracleTypes.VARCHAR),
				new SqlParameter("P_TRANS_AMOUNT", OracleTypes.NUMBER),
				new SqlParameter("P_SUB_ACT_TYPE", OracleTypes.VARCHAR),
				new SqlParameter("P_ALLOW_OVERDRAWN", OracleTypes.VARCHAR), };
		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("TRUST").withProcedureName("UPDATE_OFFENDER_BALANCE").declareParameters(sqlParameters);
		inParamMap.put("P_CSLD_ID", offTrans.getCaseloadId());
		inParamMap.put("P_OFF_ID", offTrans.getOffenderId());
		inParamMap.put("P_TRANS_POST_TYPE", offTrans.getTxnPostingType());
		inParamMap.put("P_TRANS_DATE", offTrans.getTxnEntryDate());
		inParamMap.put("P_TRANS_NUMBER", offTrans.getTxnId());
		inParamMap.put("P_TRANS_TYPE", offTrans.getTxnType());
		inParamMap.put("P_TRANS_AMOUNT", offTrans.getTxnEntryAmount());
		inParamMap.put("P_SUB_ACT_TYPE", offTrans.getSubAccountType());
		inParamMap.put("P_ALLOW_OVERDRAWN", "N");
		final SqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		try {
			 simpleJDBCCall.execute(inParameter);
			} catch(Exception e) {
				logger.error("In updateOffenderBalance :" + e);
				return 100;
			}
			return 1;
		}
	
	
	/**
	 * This method will update the transaction in the table
	 * @param offTrans
	 * @return Integer
	 */
	@Override
	public Integer processGlTransNew(final OffenderTransactions offTrans) {
		Integer genSeq = 0;
		final String sql = "call OMS_OWNER.TRUST.PROCESS_GL_TRANS_NEW(:P_CSLD_ID, :P_TRANS_TYPE, :P_OPERATION_TYPE, :P_TRANS_AMOUNT, :P_TRANS_NUMBER, :P_TRANS_DATE, :P_TRANS_DESC,"
				+ " :P_TRANS_SEQ, :P_MODULE_NAME, :P_OFF_ID, :P_OFF_BOOK_ID, :P_SUB_ACT_TYPE_DR, :P_SUB_ACT_TYPE_CR, :P_PAYEE_PERS_ID, :P_PAYEE_CORP_ID, :P_PAYEE_NAME_TEXT,"
				+ " :P_GL_SQNC, :P_OFF_DED_ID)";
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		inParamMap.put("P_CSLD_ID", offTrans.getCaseloadId());
		inParamMap.put("P_TRANS_TYPE", offTrans.getTxnType());
		inParamMap.put("P_OPERATION_TYPE", null);
		inParamMap.put("P_TRANS_AMOUNT", offTrans.getTxnEntryAmount());
		inParamMap.put("P_TRANS_NUMBER", offTrans.getTxnId());
		inParamMap.put("P_TRANS_DATE", offTrans.getTxnEntryDate());
		inParamMap.put("P_TRANS_DESC", offTrans.getTxnEntryDesc());
		inParamMap.put("P_TRANS_SEQ", offTrans.getTxnEntrySeq());
		inParamMap.put("P_MODULE_NAME","OTDHOLDT");
		inParamMap.put("P_OFF_ID", offTrans.getOffenderId());
		inParamMap.put("P_OFF_BOOK_ID", offTrans.getOffenderBookId());
		inParamMap.put("P_SUB_ACT_TYPE_DR", offTrans.getSubAccountType());
		inParamMap.put("P_SUB_ACT_TYPE_CR", null);
		inParamMap.put("P_PAYEE_PERS_ID", 0);
		inParamMap.put("P_PAYEE_CORP_ID", 0);
		inParamMap.put("P_PAYEE_NAME_TEXT", null);
		inParamMap.put("P_GL_SQNC", 0);
		inParamMap.put("P_OFF_DED_ID", offTrans.getRemitterId());
		try {
			namedParameterJdbcTemplate.update(sql, inParamMap);
			genSeq = 1;
			} catch (InvalidDataAccessApiUsageException e) {
				return 0;
			} catch (Exception e) {
				logger.error("In processGlTransNew :" + e);
			}
			return genSeq;
		}
	
	/**
	 * This method will update the values in the table
	 * 
	 * @param offTrans
	 * @return Integer
	 */
	public Integer updateOffTrustAccounts(final OffenderTransactions offTrans) {
		final String sql = getQuery("OTDHOLD_UPDATE_OFFENDER_TRUST_ACCOUNTS");
		return namedParameterJdbcTemplate.update(sql, createParams("TOTALAMT",offTrans.getTxnEntryAmount(), 
				"modifyUserId",offTrans.getCreateUserId(),"OFFENDERID",offTrans.getOffenderId(),"CASELOADID", offTrans.getCaseloadId()));
	}

	@Override
	public BigDecimal otdholdGetSubacHoldbalance(OffenderTransactions objTrans) {
		final String sql = getQuery("OTDHOLD_GET_SUBAC_HOLDBALANCE");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("caseloadId", objTrans.getCaseloadId(), "offenderId", objTrans.getOffenderId(), "subAccountType", objTrans.getSubAccountType()), BigDecimal.class);
	}

	@Override
	public Integer otdholdUpdateSubacHoldbalance(BigDecimal holdBal, String caseloadId, Long offenderId,
			String subAccountType,String modifyUserId) {
		final String sql = getQuery("OTDHOLD_UPDATE_SUBAC_HOLDBALANCE");
		return namedParameterJdbcTemplate.update(sql, createParams("holdBalance", holdBal, "caseloadId", caseloadId, "offenderId", offenderId,
				"subAccoutType", subAccountType,"modifyUserId",modifyUserId));
	}

	@Override
	public void getAcAndSetIndDate(Long offenderId, String caseloadId) {
		final String sql = getQuery("OTDHOLD_DEDUCTIONS_GET_AC_AND_SET_IND_DATE");
		namedParameterJdbcTemplate.update(sql, createParams("P_OFF_ID", offenderId, "P_CSLD_ID", caseloadId));
		
	}
	
}

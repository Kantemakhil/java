package net.syscon.s4.inmate.trust.trustaccounts.impl;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.OffenderBookings;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.Corporates;
import net.syscon.s4.inmate.beans.OffenderBeneficiaries;
import net.syscon.s4.inmate.beans.OffenderDeductions;
import net.syscon.s4.inmate.trust.trustaccounts.OcuovrobRepository;
import net.syscon.s4.inst.booking.beans.Persons;
import oracle.jdbc.OracleTypes;
/**
 * Class OcuovrobRepositoryImpl
 */
@Repository
public class OcuovrobRepositoryImpl extends RepositoryBase implements OcuovrobRepository{

private final Map<String, FieldMapper> personsMapping = new ImmutableMap.Builder<String, FieldMapper>()
.put("PERSON_ID", 						new FieldMapper("personId"))
.put("LAST_NAME", 						new FieldMapper("lastName"))
.put("FIRST_NAME", 						new FieldMapper("firstName"))
.build();
private final Map<String, FieldMapper> offenderBeneficiariesMapping = new ImmutableMap.Builder<String, FieldMapper>()
.put("OVERRIDE_AMOUNT", 						new FieldMapper("overrideAmount"))
.put("COMMENT_TEXT", 						new FieldMapper("commentText"))
.put("RECURSIVE_AMOUNT", 						new FieldMapper("recursiveAmount"))
.put("CREATE_USER_ID", 						new FieldMapper("createUserId"))
.put("OFFENDER_DEDUCTION_ID", 						new FieldMapper("offenderDeductionId"))
.put("PRIORITY", 						new FieldMapper("priority"))
.put("MODIFY_USER_ID", 						new FieldMapper("modifyUserId"))
.put("UNKNOWN_BEN_ID", 						new FieldMapper("unknownBenId"))
.put("PERSON_ID", 						new FieldMapper("personId"))
.put("OFFENDER_ID", 						new FieldMapper("offenderId"))
.put("MONTHLY_AMOUNT", 						new FieldMapper("monthlyAmount"))
.put("TBD_FLAG", 						new FieldMapper("tbdFlag"))
.put("PERCENT", 						new FieldMapper("percent"))
.put("AMOUNT", 						new FieldMapper("amount"))
.put("RECEIVED_AMOUNT", 						new FieldMapper("receivedAmount"))
.put("SEAL_FLAG", 						new FieldMapper("sealFlag"))
.put("CREATE_DATETIME", 						new FieldMapper("createDatetime"))
.put("MODIFY_DATETIME", 						new FieldMapper("modifyDatetime"))
.put("BENEFICIARY_ID", 						new FieldMapper("beneficiaryId"))
.put("CORPORATE_ID", 						new FieldMapper("corporateId"))
.build();
private final Map<String, FieldMapper> offenderDeductionsMapping = new ImmutableMap.Builder<String, FieldMapper>()
.put("MAX_TOTAL_AMOUNT", 						new FieldMapper("maxTotalAmount"))
.put("MAX_RECURSIVE_AMOUNT", 						new FieldMapper("maxRecursiveAmount"))
.put("ADJUSTMENT_AMOUNT", 						new FieldMapper(" adjustmentAmount "))
.put("INFORMATION_NUMBE", 						new FieldMapper("informationNumbe"))
.put("OFFENDER_DEDUCTION_ID", 						new FieldMapper("offenderDeductionId"))
.build();
private final Map<String, FieldMapper> systemProfilesMapping = new ImmutableMap.Builder<String, FieldMapper>()
.put("PROFILE_CODE", 						new FieldMapper("profileCode"))
.put("PROFILE_TYPE", 						new FieldMapper("profileType"))
.put("CREATE_USER_ID", 						new FieldMapper("createUserId"))
.put("SEAL_FLAG", 						new FieldMapper("sealFlag"))
.put("CREATE_DATETIME", 						new FieldMapper("createDatetime"))
.put("MODIFY_USER_ID", 						new FieldMapper("modifyUserId"))
.put("OLD_TABLE_NAME", 						new FieldMapper("oldTableName"))
.put("PROFILE_VALUE", 						new FieldMapper("profileValue"))
.put("MODIFY_DATETIME", 						new FieldMapper("modifyDatetime"))
.put("PROFILE_VALUE_2", 						new FieldMapper("profileValue2"))
.put("DESCRIPTION", 						new FieldMapper("description"))
.build();
private final Map<String, FieldMapper> corporatesMapping = new ImmutableMap.Builder<String, FieldMapper>()
.put("CORPORATE_NAME", 						new FieldMapper("corporateName"))
.put("CORPORATE_ID", 						new FieldMapper("corporateId"))
.build();
private final Map<String, FieldMapper> offenderBkgMapping = new ImmutableMap.Builder<String, FieldMapper>()
.put("OFFENDER_BOOK_ID", 						new FieldMapper("offenderBookId"))
.put("OFFENDER_ID", 						new FieldMapper("offenderId"))
.build();


	/**
	 * Creates new OcuovrobRepositoryImpl class Object
	 */
	public OcuovrobRepositoryImpl() {
		// OcuovrobRepositoryImpl
	}

	private static Logger logger = LogManager.getLogger(OcuovrobRepositoryImpl.class.getName());

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            OffenderBeneficiaries
	 *
	 * @return List<OffenderBeneficiaries>
	 *
	 * 
	 */
	public List<OffenderBeneficiaries> offBncExecuteQuery(final OffenderBeneficiaries paramBean,String whereQuery) {
//		String whereQuery = null;
//		Map<String, Object> returnObject = null;
//		final Map<String, Object> inParamMap = new HashMap<String, Object>();
//		SqlParameter[] sqlParameters = new SqlParameter[20];
//		sqlParameters = new SqlParameter[] { new SqlParameter("P_CASELOAD_ID", OracleTypes.VARCHAR),
//				new SqlParameter("P_CASELAOD_TYPE", OracleTypes.VARCHAR),
//				new SqlParameter("P_OFF_ID", OracleTypes.NUMBER),
//				new SqlParameter("P_MODULE_NAME", OracleTypes.VARCHAR),
//				new SqlParameter("P_INFO_NUM", OracleTypes.VARCHAR),
//				new SqlParameter("P_RECEIPT_TXN_TYPE", OracleTypes.VARCHAR) };
//		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
//				.withCatalogName("OCDPAYOB").withProcedureName("SET_DEFAULT_WHERE_OFF_BNC")
//				.declareParameters(sqlParameters);
//		if (paramBean != null) {
//			inParamMap.put("P_CASELOAD_ID", paramBean.getCaseloadId());
//			inParamMap.put("P_CASELAOD_TYPE", paramBean.getCaseLoadType());
//			inParamMap.put("P_OFF_ID", paramBean.getOffenderId());
//			inParamMap.put("P_MODULE_NAME", paramBean.getModuleName());
//			inParamMap.put("P_INFO_NUM", paramBean.getInformationNumber());
//			inParamMap.put("P_RECEIPT_TXN_TYPE", paramBean.getTxnType());
//		}
//		final MapSqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
//		try {
//			returnObject = simpleJDBCCall.execute(inParameter);
//			whereQuery = returnObject.get("P_WHERE_STATEMENT").toString();
//		} catch (Exception e) {
//			logger.error("offBncExecuteQuery: ", e);
//		}
		final String sql = getQuery("OCUOVROB_OFFBNC_FIND_OFFENDER_BENEFICIARIES_QUERY");
		String preparedSql = null;
		final StringBuffer sqlQuery = new StringBuffer();
		final MapSqlParameterSource valuesList = new MapSqlParameterSource();
		sqlQuery.append(sql);
		if (paramBean != null) {
			sqlQuery.append(" where ");
			if (whereQuery != null) {
				sqlQuery.append(whereQuery + " and ");
				valuesList.addValue("caseload_id", paramBean.getCaseloadId());
				valuesList.addValue("caseload_type", paramBean.getCaseLoadType());
				valuesList.addValue("OVR_OFFENDER_ID", paramBean.getOffenderId());
				valuesList.addValue("receipt_txn_type", paramBean.getTxnType());
				valuesList.addValue("receipt_txn_type", paramBean.getTxnType());
				valuesList.addValue("ovr_information_number", paramBean.getInformationNumber());
				valuesList.addValue("root_offender_id", paramBean.getOffenderId());
			}
		}
		preparedSql = sqlQuery.toString().trim();
		if (preparedSql.endsWith("where")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 5);
		}
		if (preparedSql.endsWith("and")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 3);
		}
		preparedSql = preparedSql + " order by  OFFENDER_DEDUCTION_ID";
		final RowMapper<OffenderBeneficiaries> offenderBeneficiariesRowMapper = Row2BeanRowMapper
				.makeMapping(preparedSql, OffenderBeneficiaries.class, offenderBeneficiariesMapping);
		List<OffenderBeneficiaries> returnList = new ArrayList<>();
		returnList = namedParameterJdbcTemplate.query(preparedSql, valuesList, offenderBeneficiariesRowMapper);
		return returnList;
	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstOffenderBeneficiaries
	 *            List<OffenderBeneficiaries>
	 *
	 * 
	 */
	public Integer offBncUpdateOffenderBeneficiaries(final List<OffenderBeneficiaries> lstOffenderBeneficiaries) {
		final String sql = getQuery("OCUOVROB_OFFBNC_UPDATE_OFFENDER_BENEFICIARIES");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffenderBeneficiaries offenderBeneficiaries : lstOffenderBeneficiaries) {
			parameters.add(new BeanPropertySqlParameterSource(offenderBeneficiaries));
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
	 * 
	 */
	public List<SystemProfiles> sysPflExecuteQuery(final SystemProfiles objSearchDao) {
		final String sql = getQuery("OCUOVROB_SYSPFL_FIND_SYSTEM_PROFILES");
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
	 * 
	 */
	public Integer sysPflInsertSystemProfiles(final List<SystemProfiles> lstSystemProfiles) {
		final String sql = getQuery("OCUOVROB_SYSPFL_INSERT_SYSTEM_PROFILES");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final SystemProfiles systemProfiles : lstSystemProfiles) {
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
	 * 
	 */
	public Integer sysPflDeleteSystemProfiles(final List<SystemProfiles> lstSystemProfiles) {
		final String sql = getQuery("OCUOVROB_SYSPFL_DELETE_SYSTEM_PROFILES");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final SystemProfiles systemProfiles : lstSystemProfiles) {
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
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfkchkOffBncOffBncCorp
	 *
	 * @param params
	 *
	 */
	public Corporates cgfkchkOffBncOffBncCorp(final Corporates paramBean) {
		final String sql = getQuery("OCUOVROB_CGFKCHK_OFF_BNC_OFF_BNC_CORP");
		final RowMapper<Corporates> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, Corporates.class,
				corporatesMapping);
		Corporates returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
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
		final String sql = getQuery("OCUOVROB_CGFKCHK_OFF_BNC_OFF_BNC_PER_F");
		final RowMapper<Persons> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, Persons.class, personsMapping);
		Persons returnObj = namedParameterJdbcTemplate.queryForObject(sql,
				createParams("PERSONID", paramBean.getPersonId()), columnRowMapper);
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
	public OffenderDeductions cgfkchkOffBncOffBncOff(final OffenderDeductions paramBean) {
		OffenderDeductions returnObj = new OffenderDeductions();
		final String sql = getQuery("OCUOVROB_CGFKCHK_OFF_BNC_OFF_BNC_OFF_D");
		final RowMapper<OffenderDeductions> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderDeductions.class, offenderDeductionsMapping);
		returnObj = namedParameterJdbcTemplate.queryForObject(sql,
				createParams("OFFENDERDEDUCTIONID", paramBean.getOffenderDeductionId()), columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfdgetOffBncDrvAmount
	 *
	 * @param params
	 *
	 */
	public OffenderDeductions cgfdgetOffBncDrvAmount(final OffenderDeductions paramBean) {
		final String sql = getQuery("OCUOVROB_CGFDGET_OFF_BNC_DRV_AMOUNT");
		final RowMapper<OffenderDeductions> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderDeductions.class, offenderDeductionsMapping);
		OffenderDeductions returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
		return returnObj;
	}

	@Override
	public String getCaseLoadType(String userId) {
		final String sql = getQuery("OCUOVROB_GET_CASELOAD_TYPE");
		String returnObj = null;
		returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams("createUserId",userId), String.class);
		return returnObj;
	}

	@Override
	public BigDecimal getDrvAmount(final OffenderBeneficiaries returnBean) {
		final String sql = getQuery("OCUOVROB_CGFDGET_OFF_BNC_DRV_AMOUNT");
		BigDecimal returnObj = null;
		returnObj = namedParameterJdbcTemplate.queryForObject(sql,
				createParams("LV_OFF_DED_ID", returnBean.getOffenderDeductionId()), BigDecimal.class);
		return returnObj;
	}

	@Override
	public String getCorporateNameFromId(final OffenderBeneficiaries returnBean) {
		final String sql = getQuery("OCUOVROB_GET_CORPORATE_NAME");
		String returnObj = null;
		returnObj = namedParameterJdbcTemplate.queryForObject(sql,
				createParams("corporateId", returnBean.getCorporateId()), String.class);
		return returnObj;
	}

	@Override
	public String checkCfppsetUp(final Long offenderDeductionId) {
		final String sql = getQuery("OCUOVROB_CHECK_CFPP_SETUP");
		String returnObj = null;
		returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams("p_off_ded_id", offenderDeductionId),
				String.class);
		return returnObj;
	}

	@Override
	public String getCaseNumber(final Long offenderDeductionId) {
		final String sql = getQuery("GET_CASE_NUMBER");
		String returnObj = null;
		returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams("p_ded_id", offenderDeductionId),
				String.class);
		return returnObj;
	}

	@Override
	public String getCrAccCodeCur(String caseloadId, String deductionType) {
		final String sql = getQuery("GET_CR_ACC_CODE_CUR");
		return namedParameterJdbcTemplate.queryForObject(sql,
				createParams("lv_caseload_id", caseloadId, "deduction_type", deductionType), String.class);
	}

	@Override
	public String getTransDetailCur(String caseloadId) {
		final String sql = getQuery("GET_TRANS_DETAIL_CUR");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("lv_caseload_id", caseloadId), String.class);
	}

	public OffenderBookings getOffenderBookIdCur(BigDecimal offenderId) {
		final String sql = getQuery("GET_OFFENDER_BOOK_ID_CUR");
		final RowMapper<OffenderBookings> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, OffenderBookings.class,
				offenderBkgMapping);
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("lv_root_offender_id", offenderId),
				columnRowMapper);
	}

	@Override
	public OffenderDeductions getDeductionInfoCur(Long lvDeductionId) {
		final String sql = getQuery("GET_DEDUCTION_INFO_CUR");
		final RowMapper<OffenderDeductions> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderDeductions.class, offenderDeductionsMapping);
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("lv_deduction_id", lvDeductionId),
				columnRowMapper);
	}

	@Override
	public String getPostingTypeCur(String lvCrAccCode) {
		final String sql = getQuery("GET_POSTING_TYPE_CUR");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("cp_account_code", lvCrAccCode!=null ? Integer.parseInt(lvCrAccCode):lvCrAccCode),
				String.class);
	}

	public Integer trustInsertGltransNew(String TxnCode, Integer glSeq, String lvCrAccCode, String lvTxnPostUsage,
			String caseloadId, String lvTxnDesc, Integer lvTxnSeq, BigDecimal offenderId, Long offenderBookId,
			String lvCaseNumber, BigDecimal personId, BigDecimal corporateId, Long lvDeductionId, Integer txnId,
			Date sysdate) {
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
		inParamMap.put("p_post_type", TxnCode);
		inParamMap.put("p_account_code", lvCrAccCode);
		inParamMap.put("p_acnt_posting", lvTxnPostUsage);
		inParamMap.put("p_csld_id", caseloadId);
		inParamMap.put("p_trans_type", "DEDC");
		inParamMap.put("p_trans_amount", 0);
		inParamMap.put("p_trans_number", txnId);
		inParamMap.put("p_trans_date", sysdate);
		inParamMap.put("p_trans_desc", lvTxnDesc);
		inParamMap.put("p_trans_seq", lvTxnSeq);
		inParamMap.put("p_gl_sqnc", glSeq);
		inParamMap.put("p_off_id", offenderId);
		inParamMap.put("p_off_book_id", offenderBookId);
		inParamMap.put("p_info_number", lvCaseNumber);
		inParamMap.put("p_payee_person_id", personId);
		inParamMap.put("p_payee_corporate_id", corporateId);
		inParamMap.put("p_payee_name_text", null);
		inParamMap.put("p_revr_txn_id", null);
		inParamMap.put("p_revr_txn_entry_seq", null);
		inParamMap.put("p_revr_gl_entry_seq", null);
		inParamMap.put("p_txn_ref_number", null);
		inParamMap.put("p_off_ded_id", lvDeductionId);
		final MapSqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);

		try {
			simpleJDBCCall.execute(inParameter);
			genSequence = 1;
		} catch (Exception e) {
			logger.error("updatePaymentPlanSchedules :" + e);
			genSequence = 0;
			return genSequence;
		}
		return genSequence;
	}

	@Override
	public Integer insertIntoBenTransactions(Integer lvTxnId, Integer lvTxnSeq, Long lvDeductionId, BigDecimal personId,
			BigDecimal corporateId, BigDecimal unknownBenId, String lvCrAccCode, String lvTxnDesc, String caseloadId,
			Long beneficiaryId, String userName) {
		final String sql = getQuery("INSERT_INTO_BEN_TRANSACTIONS");
		Integer liReturn = 0;
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("txnId", lvTxnId);
		paramMap.put("txnEntrySeq", lvTxnSeq);
		paramMap.put("offenderDeductionId", lvDeductionId);
		paramMap.put("accountCode", lvCrAccCode != null ? Integer.parseInt(lvCrAccCode) : lvCrAccCode);
		paramMap.put("personId", personId);
		paramMap.put("corporateId", corporateId);
		paramMap.put("unknownBenId", unknownBenId);
		paramMap.put("caseloadId", caseloadId);
		paramMap.put("txnEntryDesc", lvTxnDesc);
		paramMap.put("beneficiaryId", beneficiaryId);
		paramMap.put("createUserId", userName);
		try {
			liReturn = namedParameterJdbcTemplate.update(sql, paramMap);
		} catch (Exception e) {
			logger.error("insertIntoBenTransaction :" + e);
			return liReturn;
		}
		return liReturn;
	}

	@Override
	public String checkPaymentPlan() {
		final String sql = getQuery("CHECK_PAYMENT_PLAN");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams(), String.class);
	}

	@Override
	public Integer updatePaymentPlanSchedules(BigDecimal offenderId, BigDecimal overrideAmount, int amt,
			String informationNumber, BigDecimal groupId) {
		Integer genSeq = 0;
		final Map<String, Object> inParamMap = new HashMap<>();
		inParamMap.put("P_OFF_ID", offenderId);
		inParamMap.put("P_AMOUNT_TO_PLAN", overrideAmount);
		inParamMap.put("P_RECURSIVE_AMT", amt);
		inParamMap.put("P_INFO_NUMBER", informationNumber);
		inParamMap.put("P_GRP_ID", groupId);
		try {
			namedParameterJdbcTemplate.update(
					"call OMS_OWNER.FINANCIAL.UPDATE_PAYMENT_PLAN_SCHEDULES(:P_OFF_ID, :P_AMOUNT_TO_PLAN, :P_RECURSIVE_AMT, :P_INFO_NUMBER, :P_GRP_ID)",
					inParamMap);
			genSeq = 1;
		} catch (Exception e) {
			logger.error("updatePaymentPlanSchedules :" + e);
			genSeq = 0;
			return genSeq;
		}
		return genSeq;

	}

	@Override
	public Integer insertIntoOffenderTrans(Integer lvTxnId, Integer lvTxnSeq, String caseloadId, BigDecimal offenderId,
			Long offenderBookId, String txnPostingType, String txnType, String lvTxnDesc, int transAmt, Date sysdate,
			String lvSubAccountType, String dedFlag, BigDecimal overrideAmount, String deductionType,
			BigDecimal corporateId, BigDecimal personId, String informationNumber) {
		Integer genSeq = 0;
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		inParamMap.put("p_trans_number", lvTxnId);
		inParamMap.put("p_trans_seq", lvTxnSeq);
		inParamMap.put("p_csld_id", caseloadId);
		inParamMap.put("p_off_id", offenderId);
		inParamMap.put("p_off_book_id", offenderBookId);
		inParamMap.put("p_trans_post_type", txnPostingType);
		inParamMap.put("p_trans_type", txnType);
		inParamMap.put("p_trans_desc", lvTxnDesc);
		inParamMap.put("p_trans_amount", new BigDecimal(0.0));
		inParamMap.put("p_trans_date", sysdate);
		inParamMap.put("p_sub_act_type", lvSubAccountType);
		inParamMap.put("p_deduction_flag", dedFlag);
		inParamMap.put("p_pre_ded_amount", overrideAmount);
		inParamMap.put("p_deduction_type", deductionType);
		inParamMap.put("p_payee_corp_id", corporateId);
		inParamMap.put("p_payee_person_id", personId);
		inParamMap.put("p_info_number", informationNumber);
		inParamMap.put("p_slip_print_flag", "Y");
		inParamMap.put("p_allow_overdrawn", "N");
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

	@Override
	public Integer getTxnId() {
		final String sql = getQuery("GET_TXN_ID_ONE");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams(), Integer.class);
	}

	@Override
	public String getSubAccountTypeCur(String lvTransOprRec) {
		final String sql = getQuery("GET_SUB_ACCOUNT_TYPE_CUR");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("dr_account_code",
				lvTransOprRec != null ? Integer.parseInt(lvTransOprRec) : lvTransOprRec),
				String.class);
	}

	@Override
	public Integer insOffCrPriorPayments(OffenderBeneficiaries bean) {
		final String sql = getQuery("INS_OFF_CR_PRIOR_PAYMENTS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		parameters.add(new BeanPropertySqlParameterSource(bean));
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (returnArray.length == 1) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public Integer updtOffenderDeductions(OffenderBeneficiaries bean) {
		final String sql = getQuery("UPDT_OFFENDER_DEDUCTIONS");
		Integer liReturn = 0;
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("lv_amount", bean.getOverrideAmount());
		paramMap.put("lv_deduction_id", bean.getOffenderDeductionId());
		try {
			liReturn = namedParameterJdbcTemplate.update(sql, paramMap);
		} catch (Exception e) {
			logger.error("insertIntoOffenderTransaction :" + e);
			return liReturn;
		}
		return liReturn;
	}

	@Override
	public BigDecimal getReceivedAmount(OffenderBeneficiaries bean) {
		final String sql = getQuery("GET_RECEIVED_AMOUNT");
		return namedParameterJdbcTemplate.queryForObject(sql,
				createParams("OVERRIDE_AMOUNT", bean.getOverrideAmount(), "RECEIVED_AMOUNT", bean.getReceivedAmount()),
				BigDecimal.class);
	}
	
	@Override
	public List<OffenderBeneficiaries> getOffenderBenOldRec(Long beneficiaryId) {
		final String sql = getQuery("GET_OFFENDER_BENEFICIARIES_OLD_REC");
		final RowMapper<OffenderBeneficiaries> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderBeneficiaries.class, offenderBeneficiariesMapping);
		return namedParameterJdbcTemplate.query(sql, createParams("beneficiaryId", beneficiaryId),
				columnRowMapper);
	}
	
	@Override
	public OffenderDeductions getoffenderDedOldRec(Long offenderDeductionId) {
		final String sql = getQuery("GET_OFFENDER_DEDUCTIONS_OLD_REC");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("offenderDeductionId", offenderDeductionId),
			new BeanPropertyRowMapper<OffenderDeductions>(OffenderDeductions.class));
	}
}

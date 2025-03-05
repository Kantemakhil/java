package net.syscon.s4.inmate.trust.deductions.deductionsmaintenance.impl;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.management.RuntimeErrorException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.CaseloadDedBeneficiaries;
import net.syscon.s4.common.beans.OffenderSentObligations;
import net.syscon.s4.common.beans.TransactionTypes;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.AccountCodes;
import net.syscon.s4.im.beans.CaseloadDeductionDetails;
import net.syscon.s4.im.beans.Corporates;
import net.syscon.s4.im.beans.OmsModules;
import net.syscon.s4.im.beans.SysDual;
import net.syscon.s4.inmate.beans.CaseloadDeductionProfiles;
import net.syscon.s4.inmate.beans.DeductionTypes;
import net.syscon.s4.inmate.trust.deductions.deductionsmaintenance.OtmfoproRepository;
import net.syscon.s4.inst.booking.beans.Persons;
import net.syscon.s4.inst.casemanagement.beans.CommunityConditions;
/**
 * Class OtmfoproRepositoryImpl
 */
@Repository
public class OtmfoproRepositoryImpl extends RepositoryBase implements OtmfoproRepository{

	private static Logger logger = LogManager.getLogger(OtmfoproRepositoryImpl.class.getName());
	
private final Map<String, FieldMapper> personsMapping = new ImmutableMap.Builder<String, FieldMapper>()
.put("PERSON_ID", 						new FieldMapper("personId"))
.put("LAST_NAME", 						new FieldMapper("lastName"))
.put("FIRST_NAME", 						new FieldMapper("firstName"))
.build();
private final Map<String, FieldMapper> transactionTypesMapping = new ImmutableMap.Builder<String, FieldMapper>()
.put("DESCRIPTION", 				new FieldMapper("description"))
.put("CODE", 						new FieldMapper("code"))
.put("CASELOAD_TYPE", 				new FieldMapper("caseloadType"))
.put("RECEIPT_TXN_TYPE", 			new FieldMapper("receiptTxnType"))
.build();
private final Map<String, FieldMapper> caseloadDeductionDetailsMapping = new ImmutableMap.Builder<String, FieldMapper>()
.put("CASELOAD_ID",						new FieldMapper("caseloadId"))
.put("DEDUCTION_TYPE",					new FieldMapper("deductionType"))
.put("RECEIPT_TXN_TYPE",				new FieldMapper("receiptTxnType"))
.put("PERCENTAGE",						new FieldMapper("percentage"))
.put("MODIFY_USER_ID",					new FieldMapper("modifyUserId"))
.put("MODIFY_DATE",						new FieldMapper("modifyDate"))
.put("LIST_SEQ",						new FieldMapper("listSeq"))
.put("FLAT_RATE",						new FieldMapper("flatRate"))
.put("MINIMUM_TRUST_BALANCE_FLAG",		new FieldMapper("minimumTrustBalanceFlag"))
.put("CREATE_DATETIME",					new FieldMapper("createDatetime"))
.put("CREATE_USER_ID",					new FieldMapper("createUserId"))
.put("MODIFY_DATETIME",					new FieldMapper("modifyDatetime"))
.put("SEAL_FLAG",						new FieldMapper("sealFlag"))

.build();
private final Map<String, FieldMapper> offenderSentObligationsMapping = new ImmutableMap.Builder<String, FieldMapper>()
.put("'1'", 						new FieldMapper("  '1' "))
.build();
private final Map<String, FieldMapper> deductionTypesMapping = new ImmutableMap.Builder<String, FieldMapper>()
.put("DEDUCTION_TYPE", 						new FieldMapper("deductionType"))
.put("DEDUCTION_DESC", 						new FieldMapper("deductionDesc"))
.put("CASELOAD_TYPE", 						new FieldMapper("caseloadType"))
.build();
private final Map<String, FieldMapper> accountCodesMapping = new ImmutableMap.Builder<String, FieldMapper>()
.put("ACCOUNT_NAME", 						new FieldMapper("accountName"))
.put("CASELOAD_TYPE", 						new FieldMapper("caseloadType"))
.put("ACCOUNT_CODE", 						new FieldMapper("accountCode"))
.build();
private final Map<String, FieldMapper> mMapping = new ImmutableMap.Builder<String, FieldMapper>()
.put("DEDUCTION_TYPE", 						new FieldMapper("deductionType"))
.put("COMM_CONDITION_CODE", 				new FieldMapper("commConditionCode"))
.put("DESCRIPTION", 						new FieldMapper("description"))
.put("ACCOUNT_CODE", 						new FieldMapper("accountCode"))
.put("TXN_TYPE", 							new FieldMapper("txnType"))
.put("CODE", 								new FieldMapper("code"))
.put("COMM_CONDITION_TYPE", 				new FieldMapper("commConditionType"))
.build();
private final Map<String, FieldMapper> corporatesMapping = new ImmutableMap.Builder<String, FieldMapper>()
.put("CORPORATE_NAME", 						new FieldMapper("corporateName"))
.put("CORPORATE_ID", 						new FieldMapper("corporateId"))
.put("CASELOAD_ID", 						new FieldMapper("caseloadId"))
.build();
private final Map<String, FieldMapper> globalMapping = new ImmutableMap.Builder<String, FieldMapper>()
.put("CORPORATE_NAME", 						new FieldMapper("corporateName"))
.put("CORPORATE_ID", 						new FieldMapper("corporateId"))
.put("CASELOAD_ID", 						new FieldMapper("caseloadId"))
.build();

private final Map<String, FieldMapper> caseloadDedBeneficiariesMapping = new ImmutableMap.Builder<String, FieldMapper>()
.put("CASELOAD_DED_BENEFICIARY_ID",	new FieldMapper("caseloadDedBeneficiaryId"))
.put("CASELOAD_ID",					new FieldMapper("caseloadId"))
.put("DEDUCTION_TYPE",				new FieldMapper("deductionType"))
.put("PERSON_ID",					new FieldMapper("personId"))
.put("CORPORATE_ID",				new FieldMapper("corporateId"))
.put("PRIORITY",					new FieldMapper("priority"))
.put("AMOUNT",						new FieldMapper("amount"))
.put("PERCENT",						new FieldMapper("percent"))
.put("MODIFY_USER_ID",				new FieldMapper("modifyUserId"))
.put("MODIFY_DATE",					new FieldMapper("modifyDate"))
.put("CREATE_DATETIME",				new FieldMapper("createDatetime"))
.put("CREATE_USER_ID",				new FieldMapper("createUserId"))
.put("MODIFY_DATETIME",				new FieldMapper("modifyDatetime"))
.put("SEAL_FLAG",					new FieldMapper("sealFlag"))
.put("CO_CREDIT_WHEN_INDIGENT_FLAG",new FieldMapper("coCreditWhenIndigentFlag"))

.build();



private final Map<String, FieldMapper> caseloadDeductionProfilesMapping = new ImmutableMap.Builder<String, FieldMapper>()
.put("EXTERNAL_PRIORITY_NO",			new FieldMapper("externalPriorityNo"))
.put("ACCOUNT_CODE",					new FieldMapper("accountCode"))
.put("CO_LIMIT_AMOUNT",					new FieldMapper("coLimitAmount"))
.put("CO_CREDIT_WHEN_INDIGENT_FLAG",	new FieldMapper("coCreditWhenIndigentFlag"))
.put("MAX_MONTHLY_AMOUNT",				new FieldMapper("maxMonthlyAmount"))
.put("MAX_TOTAL_AMOUNT",				new FieldMapper("maxTotalAmount"))
.put("EXPIRY_DATE",						new FieldMapper("expiryDate"))
.put("PAYEE_PERSON_ID",					new FieldMapper("payeePersonId"))
.put("PAYEE_CORPORATE_ID",				new FieldMapper("payeeCorporateId"))
.put("MODIFY_USER_ID",					new FieldMapper("modifyUserId"))
.put("MODIFY_DATE",						new FieldMapper("modifyDate"))
.put("LIST_SEQ",						new FieldMapper("listSeq"))
.put("FLAT_RATE",						new FieldMapper("flatRate"))
.put("MINIMUM_TRUST_BALANCE",			new FieldMapper("minimumTrustBalance"))
.put("INDIGENT_MANDATORY_FLAG",			new FieldMapper("indigentMandatoryFlag"))
.put("COMM_CONDITION_TYPE",				new FieldMapper("commConditionType"))
.put("COMM_CONDITION_CODE",				new FieldMapper("commConditionCode"))
.put("MAX_RECURSIVE_AMOUNT",			new FieldMapper("maxRecursiveAmount"))
.put("CREATE_DATETIME",					new FieldMapper("createDateTime"))
.put("CREATE_USER_ID",					new FieldMapper("createUserId"))
.put("MODIFY_DATETIME",					new FieldMapper("modifyDateTime"))
.put("CATEGORY_TYPE",					new FieldMapper("categoryType"))
.put("SEAL_FLAG",						new FieldMapper("sealFlag"))

.build();

	/**
	 * Creates new OtmfoproRepositoryImpl class Object
	 */
	public OtmfoproRepositoryImpl() {
		// OtmfoproRepositoryImpl
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            CaseloadDeductionProfiles
	 *
	 * @return List<CaseloadDeductionProfiles>
	 *
	 * 
	 */
	public List<CaseloadDeductionProfiles> csldDpExecuteQuery(final CaseloadDeductionProfiles objSearchDao) {
		final String sql = getQuery("OTMFOPRO_CSLDDP_FIND_CASELOAD_DEDUCTION_PROFILES");
		final StringBuffer paramQuery = new StringBuffer("");
		final MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("caseloadId", objSearchDao.getCaseloadId());
		params.addValue("caseloadType", objSearchDao.getCaseloadType());
		if (objSearchDao.getDeductionType() != null) {
			paramQuery.append(" AND DEDUCTION_TYPE = :deductionType ");
			params.addValue("deductionType", objSearchDao.getDeductionType());
		}
		if (objSearchDao.getAccountCode() != null) {
			paramQuery.append(" AND ACCOUNT_CODE = :accountCode ");
			params.addValue("accountCode", objSearchDao.getAccountCode());
		}
		if (objSearchDao.getListSeq() != null) {
			paramQuery.append(" AND LIST_SEQ = :listSeq ");
			params.addValue("listSeq", objSearchDao.getListSeq());
		}
		if (objSearchDao.getListSeq() != null) {
			paramQuery.append(" AND LIST_SEQ = :listSeq ");
			params.addValue("listSeq", objSearchDao.getListSeq());
		}
		if (objSearchDao.getNbtModifyUserId() != null) {
			paramQuery.append(
					" AND DEDUCTION_TYPE IN (SELECT DEDUCTION_TYPE  FROM DEDUCTION_TYPES WHERE FROM_BALANCE_TYPE = :calc) ");
			params.addValue("calc", objSearchDao.getNbtModifyUserId());
		}
		final String paramSql = sql.replace("#param", paramQuery.toString());
		final RowMapper<CaseloadDeductionProfiles> CaseloadDeductionProfilesRowMapper = Row2BeanRowMapper
				.makeMapping(sql, CaseloadDeductionProfiles.class, caseloadDeductionProfilesMapping);
		final List<CaseloadDeductionProfiles> returnList = namedParameterJdbcTemplate.query(paramSql, params,
				CaseloadDeductionProfilesRowMapper);
		return returnList;
	}
	/**
	 * @param
	 *
	 * 
	 *
	 */
	// public int PRE_INSERT() {
	// return 0;
	// }

	/**
	 * This method is used to insert the records in the data base tables based
	 * on
	 *
	 * @param lstCaseloadDeductionProfiles
	 *            List<CaseloadDeductionProfiles>
	 *
	 * @return List<Integer>
	 *
	 * 
	 */
	public Integer csldDpInsertCaseloadDeductionProfiles(
			final List<CaseloadDeductionProfiles> lstCaseloadDeductionProfiles) {
		String sql = getQuery("OTMFOPRO_CSLDDP_INSERT_CASELOAD_DEDUCTION_PROFILES");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (CaseloadDeductionProfiles caseloadDeductionProfiles : lstCaseloadDeductionProfiles) {
			parameters.add(new BeanPropertySqlParameterSource(caseloadDeductionProfiles));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstCaseloadDeductionProfiles.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstCaseloadDeductionProfiles
	 *            List<CaseloadDeductionProfiles>
	 *
	 * 
	 */
	public Integer csldDpUpdateCaseloadDeductionProfiles(
			final List<CaseloadDeductionProfiles> lstCaseloadDeductionProfiles) {
		String sql = getQuery("OTMFOPRO_CSLDDP_UPDATE_CASELOAD_DEDUCTION_PROFILES");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (CaseloadDeductionProfiles caseloadDeductionProfiles : lstCaseloadDeductionProfiles) {
			parameters.add(new BeanPropertySqlParameterSource(caseloadDeductionProfiles));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstCaseloadDeductionProfiles.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to delete records from data base tables based on
	 *
	 * @param lstCaseloadDeductionProfiles
	 *            List<CaseloadDeductionProfiles>
	 *
	 * 
	 */
	public Integer csldDpDeleteCaseloadDeductionProfiles(
			final List<CaseloadDeductionProfiles> lstCaseloadDeductionProfiles) {
		String sql = getQuery("OTMFOPRO_CSLDDP_DELETE_CASELOAD_DEDUCTION_PROFILES");
		//int[] returnArray = new int[] {};
		int count = 0;
		//List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (CaseloadDeductionProfiles bean : lstCaseloadDeductionProfiles) {
			try {
				String tableName = "CASELOAD_DEDUCTION_PROFILES";
				String whereClause = "CASELOAD_ID = :caseloadId AND DEDUCTION_TYPE = :deductionType";
				Map<String , Object> inputMap = new HashMap<String, Object>();
				inputMap.put("caseloadId", bean.getCaseloadId());
				inputMap.put("deductionType", bean.getDeductionType());
				inputMap.put("modifyUserId", bean.getModifyUserId());
				updatePreDeletedRow(tableName, whereClause , inputMap);
			} catch (Exception e) {
				logger.error("Exception occured in " + this.getClass().getName() + " in method csldDpDeleteCaseloadDeductionProfiles", e);
			}
			count = namedParameterJdbcTemplate.update(sql,
					createParams("caseloadId", bean.getCaseloadId(), "deductionType", bean.getDeductionType()));
			if (count == 0)
				return 0;
		}

		return 1;

	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            CaseloadDedBeneficiaries
	 *
	 * @return List<CaseloadDedBeneficiaries>
	 *
	 */
	public List<CaseloadDedBeneficiaries> csldDbenExecuteQuery(final CaseloadDedBeneficiaries objSearchDao) {
		final String sql = getQuery("OTMFOPRO_CSLDDBEN_FIND_CASELOAD_DED_BENEFICIARIES");
		final RowMapper<CaseloadDedBeneficiaries> CaseloadDedBeneficiariesRowMapper = Row2BeanRowMapper.makeMapping(sql,
				CaseloadDedBeneficiaries.class, caseloadDedBeneficiariesMapping);
		final List<CaseloadDedBeneficiaries> returnList = namedParameterJdbcTemplate.query(sql,
				createParams("caseloadId", objSearchDao.getCaseloadId(), "deductionType",
						objSearchDao.getDeductionType()),
				CaseloadDedBeneficiariesRowMapper);
		return returnList;
	}

	/**
	 * This method is used to insert the records in the data base tables based
	 * on
	 *
	 * @param lstCaseloadDedBeneficiaries
	 *            List<CaseloadDedBeneficiaries>
	 *
	 * @return List<Integer>
	 *
	 * 
	 */
	public Integer csldDbenInsertCaseloadDedBeneficiaries(
			final List<CaseloadDedBeneficiaries> lstCaseloadDedBeneficiaries) {
		String sql = getQuery("OTMFOPRO_CSLDDBEN_INSERT_CASELOAD_DED_BENEFICIARIES");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (CaseloadDedBeneficiaries caseloadDedBeneficiaries : lstCaseloadDedBeneficiaries) {
			parameters.add(new BeanPropertySqlParameterSource(caseloadDedBeneficiaries));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstCaseloadDedBeneficiaries.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstCaseloadDedBeneficiaries
	 *            List<CaseloadDedBeneficiaries>
	 *
	 * 
	 */
	public Integer csldDbenUpdateCaseloadDedBeneficiaries(
			final List<CaseloadDedBeneficiaries> lstCaseloadDedBeneficiaries) {
		String sql = getQuery("OTMFOPRO_CSLDDBEN_UPDATE_CASELOAD_DED_BENEFICIARIES");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (CaseloadDedBeneficiaries caseloadDedBeneficiaries : lstCaseloadDedBeneficiaries) {
			parameters.add(new BeanPropertySqlParameterSource(caseloadDedBeneficiaries));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstCaseloadDedBeneficiaries.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to delete records from data base tables based on
	 *
	 * @param lstCaseloadDedBeneficiaries
	 *            List<CaseloadDedBeneficiaries>
	 *
	 * 
	 */
	public Integer csldDbenDeleteCaseloadDedBeneficiaries(
			final List<CaseloadDedBeneficiaries> lstCaseloadDedBeneficiaries) {
		String sql = getQuery("OTMFOPRO_CSLDDBEN_DELETE_CASELOAD_DED_BENEFICIARIES");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (CaseloadDedBeneficiaries caseloadDedBeneficiaries : lstCaseloadDedBeneficiaries) {
			parameters.add(new BeanPropertySqlParameterSource(caseloadDedBeneficiaries));
		}
		try {
			String tableName = "CASELOAD_DED_BENEFICIARIES";
			String whereClause = "CASELOAD_DED_BENEFICIARY_ID = :caseloadDedBeneficiaryId";
			batchUpdatePreDeletedRows(tableName, whereClause , parameters);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method csldDbenDeleteCaseloadDedBeneficiaries", e);
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstCaseloadDedBeneficiaries.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            CaseloadDeductionDetails
	 *
	 * @return List<CaseloadDeductionDetails>
	 *
	 * 
	 */
	public List<CaseloadDeductionDetails> csldDdExecuteQuery(final CaseloadDeductionDetails objSearchDao) {
		final String sql = getQuery("OTMFOPRO_CSLDDD_FIND_CASELOAD_DEDUCTION_DETAILS");
		final RowMapper<CaseloadDeductionDetails> CaseloadDeductionDetailsRowMapper = Row2BeanRowMapper.makeMapping(sql,
				CaseloadDeductionDetails.class, caseloadDeductionDetailsMapping);
		final List<CaseloadDeductionDetails> returnList = namedParameterJdbcTemplate.query(sql,
				createParams("caseloadId", objSearchDao.getCaseloadId(), "deductionType",
						objSearchDao.getDeductionType()),
				CaseloadDeductionDetailsRowMapper);
		return returnList;
	}

	/**
	 * @param
	 *
	 * 
	 *
	 */
	public int PRE_INSERT() {
		return 0;
	}

	/**
	 * This method is used to insert the records in the data base tables based
	 * on
	 *
	 * @param lstCaseloadDeductionDetails
	 *            List<CaseloadDeductionDetails>
	 *
	 * @return List<Integer>
	 *
	 * 
	 */
	public Integer csldDdInsertCaseloadDeductionDetails(
			final List<CaseloadDeductionDetails> lstCaseloadDeductionDetails) {
		String sql = getQuery("OTMFOPRO_CSLDDD_INSERT_CASELOAD_DEDUCTION_DETAILS");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (CaseloadDeductionDetails caseloadDeductionDetails : lstCaseloadDeductionDetails) {
			parameters.add(new BeanPropertySqlParameterSource(caseloadDeductionDetails));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			String error = "Error : " + e.getMessage();
			if (error.contains("violates unique constraint \"csld_dd_pk\"")) {
				return 3;
			}
		}
		if (lstCaseloadDeductionDetails.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstCaseloadDeductionDetails
	 *            List<CaseloadDeductionDetails>
	 *
	 * 
	 */
	public Integer csldDdUpdateCaseloadDeductionDetails(
			final List<CaseloadDeductionDetails> lstCaseloadDeductionDetails) {
		String sql = getQuery("OTMFOPRO_CSLDDD_UPDATE_CASELOAD_DEDUCTION_DETAILS");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (CaseloadDeductionDetails caseloadDeductionDetails : lstCaseloadDeductionDetails) {
			parameters.add(new BeanPropertySqlParameterSource(caseloadDeductionDetails));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstCaseloadDeductionDetails.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to delete records from data base tables based on
	 *
	 * @param lstCaseloadDeductionDetails
	 *            List<CaseloadDeductionDetails>
	 *
	 * 
	 */
	public Integer csldDdDeleteCaseloadDeductionDetails(
			final List<CaseloadDeductionDetails> lstCaseloadDeductionDetails) {
		String sql = getQuery("OTMFOPRO_CSLDDD_DELETE_CASELOAD_DEDUCTION_DETAILS");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (CaseloadDeductionDetails caseloadDeductionDetails : lstCaseloadDeductionDetails) {
			parameters.add(new BeanPropertySqlParameterSource(caseloadDeductionDetails));
		}
		try {
			String tableName = "CASELOAD_DEDUCTION_DETAILS";
			String whereClause = "DEDUCTION_TYPE = :deductionType AND CASELOAD_ID = :caseloadId AND RECEIPT_TXN_TYPE = :receiptTxnType";
			batchUpdatePreDeletedRows(tableName, whereClause , parameters);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method csldDdDeleteCaseloadDeductionDetails", e);
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstCaseloadDeductionDetails.size() == returnArray.length) {
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
	public List<TransactionTypes> cgfkCsldDdReceiptTxnTypeRecordGroup(final String caseloadType) {
		final String sql = getQuery("OTMFOPRO_FIND_CGFKCSLDDDRECEIPTTXNTYPE");
		final RowMapper<TransactionTypes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, TransactionTypes.class,
				mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams("caseloadType", caseloadType), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<CommunityConditions> rgConditionRecordGroup() {
		final String sql = getQuery("OTMFOPRO_FIND_RGCONDITION");
		final RowMapper<CommunityConditions> mRowMapper = Row2BeanRowMapper.makeMapping(sql, CommunityConditions.class,
				mMapping);

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
	public List<AccountCodes> cgfkCsldDpAccountCodeRecordGroup(final String caseloadType) {
		final String sql = getQuery("OTMFOPRO_FIND_CGFKCSLDDPACCOUNTCODE");
		final RowMapper<AccountCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, AccountCodes.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams("caseloadType", caseloadType), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<MM>
	 */
	public List<Corporates> rgCorpRecordGroup() {
		final String sql = getQuery("OTMFOPRO_FIND_RGCORP");
		final RowMapper<Corporates> mMRowMapper = Row2BeanRowMapper.makeMapping(sql, Corporates.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mMRowMapper);
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<DeductionTypes> cgfkCsldDpDeductionTypeRecordGroup(final String caseloadType) {
		final String sql = getQuery("OTMFOPRO_FIND_CGFKCSLDDPDEDUCTIONTYPE");
		final RowMapper<DeductionTypes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, DeductionTypes.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams("caseloadType", caseloadType), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}
	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * csldDpPreDeletePRE-DELETE
	 *
	 * @param params
	 *
	 */
	// csldDpPreDeletePRE-DELETE(
	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * csldDpPreDeletePRE-DELETE
	 *
	 * @param params
	 *
	 */

	// csldDpPreDeletePRE-DELETE(
	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * csldDpPostQuery
	 *
	 * @param params
	 *
	 */
	public List<CommunityConditions> csldDpPostQuery(final CommunityConditions paramBean) {
		final String sql = getQuery("OTMFOPRO_CSLD_DP_POSTQUERY");
		final RowMapper<CommunityConditions> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				CommunityConditions.class, caseloadDeductionDetailsMapping); // communityConditionsMapping
		final ArrayList<CommunityConditions> returnList = (ArrayList<CommunityConditions>) namedParameterJdbcTemplate
				.query(sql, createParams(), columnRowMapper);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * csldDbenPreInsert
	 *
	 * @param params
	 *
	 */
	public Object csldDbenPreInsert(final SysDual paramBean) {
		final String sql = getQuery("OTMFOPRO_CSLD_DBEN_PREINSERT");
		final RowMapper<SysDual> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, SysDual.class, globalMapping);
		SysDual returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * createFormGlobalsCREATE_FORM_GLOBALS
	 *
	 * @param params
	 *
	 */
	public List<OmsModules> createFormGlobalsCreateFormGlobals(final OmsModules paramBean) {
		final String sql = getQuery("OTMFOPRO_CREATE_FORM_GLOBALS_CREATE_FORM_GLOBALS");
		final RowMapper<OmsModules> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, OmsModules.class,
				globalMapping);
		final ArrayList<OmsModules> returnList = (ArrayList<OmsModules>) namedParameterJdbcTemplate.query(sql,
				createParams(), columnRowMapper);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfkchkCsldDpDedprofAcCo
	 *
	 * @param params
	 *
	 */
	public List<AccountCodes> cgfkchkCsldDpDedprofAcCo(final AccountCodes paramBean) {
		final String sql = getQuery("OTMFOPRO_CGFKCHK_CSLD_DP_DEDPROF_AC_CO");
		final RowMapper<AccountCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, AccountCodes.class,
				accountCodesMapping);
		final ArrayList<AccountCodes> returnList = (ArrayList<AccountCodes>) namedParameterJdbcTemplate.query(sql,
				createParams(), columnRowMapper);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfkchkCsldDpDedprofDedty
	 *
	 * @param params
	 *
	 */
	public List<DeductionTypes> cgfkchkCsldDpDedprofDedty(final DeductionTypes paramBean) {
		final String sql = getQuery("OTMFOPRO_CGFKCHK_CSLD_DP_DEDPROF_DEDTY");
		final RowMapper<DeductionTypes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, DeductionTypes.class,
				deductionTypesMapping);
		final ArrayList<DeductionTypes> returnList = (ArrayList<DeductionTypes>) namedParameterJdbcTemplate.query(sql,
				createParams(), columnRowMapper);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfkchkCsldDbenCsldDben
	 *
	 * @param params
	 *
	 */
	public Persons cgfkchkCsldDbenCsldDben(final Long personId) {
		final String sql = getQuery("OTMFOPRO_CGFKCHK_CSLD_DBEN_CSLD_DBEN_P");
		final RowMapper<Persons> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, Persons.class, personsMapping);
		Persons returnList = new Persons();
		try {
			returnList = namedParameterJdbcTemplate.queryForObject(sql, createParams("personId", personId),
					columnRowMapper);
		} catch (Exception e) {
			return returnList;
		}
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfkchkCsldDbenCsldDben
	 *
	 * @param params
	 *
	 */
	public Corporates cgfkchkCsldDbenCsldDben(final BigDecimal corporateId) {
		final String sql = getQuery("OTMFOPRO_CGFKCHK_CSLD_DBEN_CSLD_DBEN_C");
		final RowMapper<Corporates> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, Corporates.class,
				corporatesMapping);
		Corporates returnList = new Corporates();
		try {
			returnList = namedParameterJdbcTemplate.queryForObject(sql, createParams("corporateId", corporateId),
					columnRowMapper);
		} catch (Exception e) {
			return returnList;
		}
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
	public List<Object> cgwhenNewFormInstance(final SysDual paramBean) {
		final String sql = getQuery("OTMFOPRO_CGWHEN_NEW_FORM_INSTANCE");
		final RowMapper<Object> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, Object.class, globalMapping);
		final ArrayList<Object> returnList = (ArrayList<Object>) namedParameterJdbcTemplate.query(sql, createParams(),
				columnRowMapper);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfkchkCsldDdCsldDdTxnty
	 *
	 * @param params
	 *
	 */
	public List<TransactionTypes> cgfkchkCsldDdCsldDdTxnty(final TransactionTypes paramBean) {
		final String sql = getQuery("OTMFOPRO_CGFKCHK_CSLD_DD_CSLD_DD_TXNTY");
		final RowMapper<TransactionTypes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, TransactionTypes.class,
				transactionTypesMapping);
		final List<TransactionTypes> returnList = namedParameterJdbcTemplate.query(sql, createParams(),
				columnRowMapper);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgrichkCaseloadDeductionPr
	 *
	 * @param params
	 *
	 */
	public Object cgrichkCaseloadDeductionPr(final CaseloadDeductionDetails paramBean) {
		final String sql = getQuery("OTMFOPRO_CGRICHK_CASELOAD_DEDUCTION_PR");
		final RowMapper<CaseloadDeductionDetails> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				CaseloadDeductionDetails.class, caseloadDeductionDetailsMapping);
		CaseloadDeductionDetails returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(),
				columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgrichkCaseloadDeductionDe
	 *
	 * @param params
	 *
	 */
	public List<OffenderSentObligations> cgrichkCaseloadDeductionDe(final OffenderSentObligations paramBean) {
		final String sql = getQuery("OTMFOPRO_CGRICHK_CASELOAD_DEDUCTION_DE");
		final RowMapper<OffenderSentObligations> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderSentObligations.class, offenderSentObligationsMapping);
		final ArrayList<OffenderSentObligations> returnList = (ArrayList<OffenderSentObligations>) namedParameterJdbcTemplate
				.query(sql, createParams(), columnRowMapper);
		return returnList;
	}

	@Override
	public BigDecimal getPriorityAmount(final String caseloadId, final String deductionType,
			final BigDecimal priority) {
		final String sql = getQuery("OTMFOPRO_GET_PRIORITY_AMOUNT");
		BigDecimal PriorityAmount = BigDecimal.ZERO;
		try {
			PriorityAmount = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("caseloadId", caseloadId, "deductionType", deductionType, "priority", priority),
					BigDecimal.class);
		} catch (Exception e) {
			throw new RuntimeException("Other error when getting the priority total.");
		}
		return PriorityAmount;
	}

	@Override
	public String calculateOn(final String deductionType) {
		final String sql = getQuery("OTMFOPRO_CALCULATE_ON");
		String aCur = "";
		try {
			aCur = namedParameterJdbcTemplate.queryForObject(sql, createParams("deductionType", deductionType),
					String.class);
		} catch (Exception e) {

		}

		return aCur;
	}

	@Override
	public Map<String, Object> calcBenTotal(final String caseloadId, final String deductionType) {
		final String sql = getQuery("OTMFOPRO_CALC_BEN_TOTAL");
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			result = namedParameterJdbcTemplate.queryForMap(sql,
					createParams("caseloadId", caseloadId, "deductionType", deductionType));
		} catch (Exception e) {
			throw new RuntimeException("otmfopro.errwhnsumbefire");
		}
		return result;
	}

	@Override
	public List<CaseloadDedBeneficiaries> dedPriorities(final CaseloadDedBeneficiaries objSearchDao) {
		final String sql = getQuery("OTMFOPRO_DED_PRIORITIES");
		final RowMapper<CaseloadDedBeneficiaries> CaseloadDedBeneficiariesRowMapper = Row2BeanRowMapper.makeMapping(sql,
				CaseloadDedBeneficiaries.class, caseloadDedBeneficiariesMapping);
		final List<CaseloadDedBeneficiaries> returnList = namedParameterJdbcTemplate.query(sql,
				createParams("caseloadId", objSearchDao.getCaseloadId(), "deductionType",
						objSearchDao.getDeductionType()),
				CaseloadDedBeneficiariesRowMapper);
		return returnList;
	}

	@Override
	public Integer updateCaseloadDedBeneficiariesAmount(final CaseloadDedBeneficiaries bean) {
		final String sql = getQuery("OTMFOPRO_UPDATE_CASELOAD_DED_BENEFICIARIES_AMOUNT");
		try {
			namedParameterJdbcTemplate.update(sql,
					createParams("caseloadId", bean.getCaseloadId(), "deductionType", bean.getDeductionType(),"modifyUserId",bean.getModifyUserId()));
		} catch (Exception e) {
			return 0;
		}
		return 1;
	}

	@Override
	public Integer updateCaseloadDedBeneficiariesPercentage(final BigDecimal percentage,
			BigDecimal caseloadDedBeneficiaryId,String modifyUserId) {
		final String sql = getQuery("OTMFOPRO_UPDATE_CASELOAD_DED_BENEFICIARIES_PERCENTAGE");
		try {
			namedParameterJdbcTemplate.update(sql,
					createParams("percent", percentage, "caseloadDedBeneficiaryId", caseloadDedBeneficiaryId,"modifyUserId",modifyUserId));
		} catch (Exception e) {
			return 0;
		}
		return 1;
	}

	@Override
	public BigDecimal countMinBalLogic(String caseloadId, String deductionType) {
		final String sql = getQuery("OTMFOPRO_COUNT_MIN_BAL_LOGIC");
		return namedParameterJdbcTemplate.queryForObject(sql,
				createParams("caseloadId", caseloadId, "deductionType", deductionType), BigDecimal.class);
	}

	@Override
	public BigDecimal getMaxExternalPriorityNo(String caseloadId) {
		final String sql = getQuery("OTMFOPRO_GET_MAX_EXTERNAL_PRIORITY_NO");

		try {
			return namedParameterJdbcTemplate.queryForObject(sql, createParams("caseloadId", caseloadId),
					BigDecimal.class);
		} catch (Exception e) {
			throw new RuntimeException("otmfopro.otrerrwhngetnavaexpcomb");
		}
	}

	@Override
	public BigDecimal getMaxInternalPriorityNo(String caseloadId, BigDecimal externalPriorityNo) {
		final String sql = getQuery("OTMFOPRO_GET_MAX_INTERNAL_PRIORITY_NO");

		try {
			return namedParameterJdbcTemplate.queryForObject(sql,
					createParams("caseloadId", caseloadId, "externalPriorityNo", externalPriorityNo), BigDecimal.class);
		} catch (Exception e) {
			throw new RuntimeException("Other error when getting next available EXP/INP combination.");
		}
	}

	@Override
	public BigDecimal vParentExists(String deductionType) {
		final String sql = getQuery("OTMFOPRO_V_PARENT_EXISTS");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("deductionType", deductionType),
				BigDecimal.class);
	}

	@Override
	public BigDecimal vBenCount(String deductionType, String caseloadId) {
		final String sql = getQuery("OTMFOPRO_V_BEN_COUNT");
		return namedParameterJdbcTemplate.queryForObject(sql,
				createParams("deductionType", deductionType, "caseloadId", caseloadId), BigDecimal.class);
	}

	@Override
	public BigDecimal getReceiptPercentage(String deductionType, String caseloadId) {
		final String sql = getQuery("OTMFOPRO_GET_RECEIPT_PERCENTAGE");
		return namedParameterJdbcTemplate.queryForObject(sql,
				createParams("deductionType", deductionType, "caseloadId", caseloadId), BigDecimal.class);
	}

	@Override
	public Integer updateCaseloadDeductionProfilesPercentage(BigDecimal percentage, String caseloadId,
			String deductionType,String modifyUserId) {
		final String sql = getQuery("OTMFOPRO_UPDATE_CASELOAD_DEDUCTION_PROFILES_PERCENTAGE");
		return namedParameterJdbcTemplate.update(sql,
				createParams("percentage", percentage, "caseloadId", caseloadId, "deductionType", deductionType,"modifyUserId",modifyUserId));
	}

	@Override
	public Map<String, Object> getPercentAndEnternalPriority(String caseloadId, String deductionType) {
		final String sql = getQuery("OTMFOPRO_GET_PERCENT_AND_ENTERNAL_PRIORITY");
		Map<String, Object> param = new HashMap<>();
		param.put("caseloadId", caseloadId);
		param.put("deductionType", deductionType);
		return namedParameterJdbcTemplate.queryForMap(sql, param);
	}

}

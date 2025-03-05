package net.syscon.s4.cf.deductions.maintenance.impl;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.syscon.s4.cf.deductions.beans.FeeAccountProfiles;
import net.syscon.s4.common.beans.CaseloadDedBeneficiaries;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.im.beans.CaseloadDeductionDetails;
import net.syscon.s4.im.beans.Corporates;
import net.syscon.s4.im.beans.SysDual;
import net.syscon.s4.inst.booking.beans.Persons;
import net.syscon.s4.inst.legalscreens.maintenance.bean.AllowableOffenceTypes;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.cf.deductions.maintenance.OcmfaproRepository;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.inmate.beans.CaseloadDeductionProfiles;

@Repository
public class OcmfaproRepositoryImpl extends RepositoryBase implements OcmfaproRepository {

	private static Logger logger = LogManager.getLogger(OcmfaproRepositoryImpl.class.getName());

	private final Map<String, FieldMapper> mMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DEDUCTION_TYPE", 			new FieldMapper("deductionType"))
			.put("SANCTION_NOTICE_CODE", 	new FieldMapper("sanctionNoticeCode"))
			.put("DEDUCTION_CATEGORY", 		new FieldMapper("deductionCategory"))
			.put("DESCRIPTION", 			new FieldMapper("description"))
			.put("LATE_DAYS", 				new FieldMapper("lateDays"))
			.put("CODE", 				new FieldMapper("code"))
			.put("ACTIVE_FLAG", 				new FieldMapper("activeFlag"))
			.put("LIST_SEQ", 				new FieldMapper("listSeq"))
			.build();

	private final Map<String, FieldMapper> corporatesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CORPORATE_NAME", 						new FieldMapper("corporateName"))
			.put("CORPORATE_ID", 						new FieldMapper("corporateId"))
			.put("CASELOAD_ID", 						new FieldMapper("caseloadId"))
			.build();

	private final Map<String, FieldMapper> personsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("PERSON_ID", 						new FieldMapper("personId"))
			.put("LAST_NAME", 						new FieldMapper("lastName"))
			.put("FIRST_NAME", 						new FieldMapper("firstName"))
			.build();

	private final Map<String, FieldMapper> systemProfilesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("PROFILE_CODE", 						new FieldMapper("profileCode"))
			.put("PROFILE_TYPE", 						new FieldMapper("profileType"))
			.put("CREATE_USER_ID", 						new FieldMapper("createUserId"))
			.put("SEAL_FLAG", 							new FieldMapper("sealFlag"))
			.put("CREATE_DATETIME", 					new FieldMapper("createDatetime"))
			.put("MODIFY_USER_ID", 						new FieldMapper("modifyUserId"))
			.put("OLD_TABLE_NAME", 						new FieldMapper("oldTableName"))
			.put("PROFILE_VALUE", 						new FieldMapper("profileValue"))
			.put("MODIFY_DATETIME", 					new FieldMapper("modifyDatetime"))
			.put("PROFILE_VALUE_2", 					new FieldMapper("profileValue2"))
			.put("DESCRIPTION", 						new FieldMapper("description"))
			.build();

	private final Map<String, FieldMapper> cdpMapping = new ImmutableMap.Builder<String, FieldMapper>()
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
			.put("LOCATION",						new FieldMapper("location"))
			.put("AMOUNT",						new FieldMapper("amount"))
			.put("FREQUENCY",						new FieldMapper("frequency"))
			.put("DAY_OF_MONTH",						new FieldMapper("dayOfMonth"))
			.put("BACK_BILL",						new FieldMapper("backBill"))
			.put("CODE",						new FieldMapper("code"))
			.put("FREQUENCY_TYPE",						new FieldMapper("frequencyType"))
			.put("FREQUENCY_CODE",						new FieldMapper("frequencyCode"))
			.put("NON_BILLABLE_STATUS",						new FieldMapper("nonBillableStatus"))

			.build();

	private final Map<String, FieldMapper> csdBenMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CASELOAD_DED_BENEFICIARY_ID", new FieldMapper("caseloadDedBeneficiaryId"))
			.put("CASELOAD_ID", new FieldMapper("caseloadId"))
			.put("DEDUCTION_TYPE", new FieldMapper("deductionType"))
			.put("PERSON_ID", new FieldMapper("personId"))
			.put("CORPORATE_ID", new FieldMapper("corporateId"))
			.put("PRIORITY", new FieldMapper("priority"))
			.put("AMOUNT", new FieldMapper("amount"))
			.put("PERCENT", new FieldMapper("percent"))
			.put("MODIFY_USER_ID", new FieldMapper("modifyUserId"))
			.put("MODIFY_DATE", new FieldMapper("modifyDate"))
			.put("CREATE_DATETIME", new FieldMapper("createDatetime"))
			.put("CREATE_USER_ID", new FieldMapper("createUserId"))
			.put("MODIFY_DATETIME", new FieldMapper("modifyDatetime"))
			.put("SEAL_FLAG", new FieldMapper("sealFlag"))
			.put("CO_CREDIT_WHEN_INDIGENT_FLAG", new FieldMapper("coCreditWhenIndigentFlag"))
			.put("LONGEST_SUPV_EXPIRY_DATE", new FieldMapper("longestSupvExpiryDate"))
			.put("SERVICE_DATE", new FieldMapper("serviceDate"))
			.put("COMMENT_TEXT", new FieldMapper("commentText"))
			.put("OFFENDER_FEE_ID", new FieldMapper("offenderFeeId"))
			.put("DAY_OF_MONTH", new FieldMapper("dayOfMonth"))
			.put("EFFECTIVE_DATE", new FieldMapper("effectiveDate"))
			.put("EXPIRY_DATE", new FieldMapper("expiryDate"))
			.put("FEE_ACT_STATUS", new FieldMapper("feeActStatus"))
			.put("FEE_CODE", new FieldMapper("feeCode"))
			.put("INFO_NUMBER", new FieldMapper("infoNumber"))
			.put("ODP", new FieldMapper("odp"))
			.put("OFFENDER_BOOK_ID", new FieldMapper("offenderBookId"))
			.put("START_DATE", new FieldMapper("startDate"))
			.put("STATUS_EFFECTIVE_DATE", new FieldMapper("statusEffectiveDate"))
			.put("OFF_FEE_DED_BENEFICIARY_ID", new FieldMapper("offFeeDedBeneficiaryId"))

			.build();

	private final Map<String, FieldMapper> csdDetMapping = new ImmutableMap.Builder<String, FieldMapper>()
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

	/**
	 * Creates new OcmfaproRepositoryImpl class Object
	 */
	public OcmfaproRepositoryImpl() {
		// OcmfaproRepositoryImpl
	}

	/**
	 * Used to capture results from select query
	 *
	 * @return List<M>
	 */
	@Override
	public List<ReferenceCodes> feeActTypeRecordGroup() {
		final String sql = getQuery("OCMFAPRO_FEE_ACCOUNT_TYPE_RG");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,mMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("feeActTypeRecordGroup", e);
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 *
	 * @return List<M>
	 */
	@Override
	public List<ReferenceCodes> locationRecordGroup(final String agyLocType) {
		final String sql = getQuery("OCMFAPRO_LOCATION_RG");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,mMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams("agyLocType", agyLocType), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("locationRecordGroup", e);
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 *
	 * @return List<M>
	 */
	@Override
	public List<ReferenceCodes> creditDedToRecordGroup(final String caseloadType) {
		final String sql = getQuery("OCMFAPRO_CREDIT_DEDUCTION_TO_RG");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,mMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams("caseloadType", caseloadType), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("creditDedToRecordGroup", e);
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 *
	 * @return List<M>
	 */
	@Override
	public List<ReferenceCodes> frequencyRecordGroup() {
		final String sql = getQuery("OCMFAPRO_FREQUENCY_RG");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,mMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("frequencyRecordGroup", e);
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 *
	 * @return List<M>
	 */
	@Override
	public List<ReferenceCodes> codeRecordGroup() {
		final String sql = getQuery("OCMFAPRO_CODE_RG");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,mMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("codeRecordGroup", e);
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 *
	 * @return List<M>
	 */
	@Override
	public List<ReferenceCodes> reciptTypeRecordGroup(final String caseloadType) {
		final String sql = getQuery("OCMFAPRO_RECIPT_TYPE_RG");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,mMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams("caseloadType", caseloadType), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("reciptTypeRecordGroup", e);
			return Collections.emptyList();
		}
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao CaseloadDeductionProfiles
	 *
	 * @return List<CaseloadDeductionProfiles>
	 *
	 * @throws SQLException
	 */
	@Override
	public List<CaseloadDeductionProfiles> caseloadDedProfExecuteQuery(final CaseloadDeductionProfiles objSearchDao)  {
		final String sql = getQuery("OCMFAPRO_FIND_CASELOAD_DEDUCTION_PROFILES");
		final RowMapper<CaseloadDeductionProfiles> OffencesRowMapper = Row2BeanRowMapper.makeMapping(sql, CaseloadDeductionProfiles.class,cdpMapping);
		final ArrayList<CaseloadDeductionProfiles> returnList = (ArrayList<CaseloadDeductionProfiles>)namedParameterJdbcTemplate.query(sql, createParams("caseloadId", objSearchDao.getCaseloadId(), "deductionType", objSearchDao.getDeductionType()), OffencesRowMapper);
		return returnList;
	}

	@Override
	public String calculateOn(final String deductionType) {
		final String sql = getQuery("OCMFAPRO_CALCULATE_ON");
		String aCur = "";
		try {
			aCur = namedParameterJdbcTemplate.queryForObject(sql, createParams("deductionType", deductionType),
					String.class);
		} catch (Exception e) {

		}

		return aCur;
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
	public Map<String, Object> getPercentAndEnternalPriority(String caseloadId, String deductionType) {
		final String sql = getQuery("OTMFOPRO_GET_PERCENT_AND_ENTERNAL_PRIORITY");
		Map<String, Object> param = new HashMap<>();
		param.put("caseloadId", caseloadId);
		param.put("deductionType", deductionType);
		return namedParameterJdbcTemplate.queryForMap(sql, param);
	}

	/**
	 *  This method is used to insert the records in the data base tables based on
	 *
	 * @param listCaseloadDedProf List<CaseloadDeductionProfiles>
	 *
	 * @return List<Integer>
	 *
	 * @throws SQLException
	 */
	@Override
	public Integer caseloadDedProfInsert(final List<CaseloadDeductionProfiles> listCaseloadDedProf)  {
		final String sql = getQuery("OCMFAPRO_INSERT_CASELOAD_DEDUCTION_PROFILES");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final CaseloadDeductionProfiles caseloadDedProf : listCaseloadDedProf) {
			parameters.add(new BeanPropertySqlParameterSource(caseloadDedProf));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));

		if (listCaseloadDedProf.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param listCaseloadDedProf List<CaseloadDeductionProfiles>
	 *
	 * @throws SQLException
	 */
	@Override
	public Integer caseloadDedProfUpdate(final List<CaseloadDeductionProfiles> listCaseloadDedProf)  {
		final String sql = getQuery("OCMFAPROP_UPDATE_CASELOAD_DEDUCTION_PROFILES");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (CaseloadDeductionProfiles caseloadDedProf : listCaseloadDedProf) {
			parameters.add(new BeanPropertySqlParameterSource(caseloadDedProf));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (listCaseloadDedProf.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to delete records from  data base tables based on
	 *
	 * @param listCaseloadDedProf List<AllowableOffenceTypes>
	 *
	 * @throws SQLException
	 */
	@Override
	public Integer caseloadDedProfDelete(final List<CaseloadDeductionProfiles> listCaseloadDedProf) {
		final String sql = getQuery("OCMFAPRO_DELETE_CASELOAD_DEDUCTION_PROFILES");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (CaseloadDeductionProfiles caseloadDedProf : listCaseloadDedProf) {
			parameters.add(new BeanPropertySqlParameterSource(caseloadDedProf));
		}
		try {
			String tableName = "CASELOAD_DEDUCTION_PROFILES";
			String whereClause = "CASELOAD_ID=:caseloadId AND DEDUCTION_TYPE=:deductionType";
			batchUpdatePreDeletedRows(tableName, whereClause , parameters);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method caseloadDedProfDelete", e);
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));

		if (listCaseloadDedProf.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao CaseloadDedBeneficiaries
	 *
	 * @return List<CaseloadDedBeneficiaries>
	 *
	 * @throws SQLException
	 */
	@Override
	public List<CaseloadDedBeneficiaries> caseloadDedBenficExecuteQuery(final CaseloadDedBeneficiaries objSearchDao)  {
		final String sql = getQuery("OCMFAPRO_FIND_CASELOAD_DED_BENEFICIARIES");
		final RowMapper<CaseloadDedBeneficiaries> OffencesRowMapper = Row2BeanRowMapper.makeMapping(sql, CaseloadDedBeneficiaries.class,csdBenMapping);
		final ArrayList<CaseloadDedBeneficiaries> returnList = (ArrayList<CaseloadDedBeneficiaries>)namedParameterJdbcTemplate.query(sql, createParams(
				"caseloadId", objSearchDao.getCaseloadId(), "deductionType",
				objSearchDao.getDeductionType()), OffencesRowMapper);
		return returnList;
	}

	@Override
	public Persons cgfkchkCsldDbenCsldDben(final Long personId) {
		final String sql = getQuery("OCMFAPRO_CGFKCHK_CSLD_DBEN_CSLD_DBEN_P");
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
	@Override
	public Corporates cgfkchkCsldDbenCsldDben(final BigDecimal corporateId) {
		final String sql = getQuery("OCMFAPRO_CGFKCHK_CSLD_DBEN_CSLD_DBEN_C");
		final RowMapper<Corporates> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, Corporates.class,corporatesMapping);
		Corporates returnList = new Corporates();
		try {
			returnList = namedParameterJdbcTemplate.queryForObject(sql, createParams("corporateId", corporateId),
					columnRowMapper);
		} catch (Exception e) {
			return returnList;
		}
		return returnList;
	}

	@Override
	public Long csldDbenPreInsert() {
		final String sql = getQuery("OCMFAPRO_CSLD_DBEN_PREINSERT");
		Long obj = null;
		Long returnval = null;
		obj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), Long.class);
		if (obj != null) {
			returnval = obj;
		}
		return returnval;
	}

	/**
	 *  This method is used to insert the records in the data base tables based on
	 *
	 * @param listCaseloadDedBenfi List<CaseloadDedBeneficiaries>
	 *
	 * @return List<Integer>
	 *
	 * @throws SQLException
	 */
	@Override
	public Integer caseloadDedBenficInsert(final List<CaseloadDedBeneficiaries> listCaseloadDedBenfi)  {
		final String sql = getQuery("OCMFAPRO_INSERT_CASELOAD_DED_BENEFICIARIES");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final CaseloadDedBeneficiaries caseloadDedBenfi : listCaseloadDedBenfi) {
			parameters.add(new BeanPropertySqlParameterSource(caseloadDedBenfi));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));

		if (listCaseloadDedBenfi.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param listCaseloadDedBenfi List<CaseloadDedBeneficiaries>
	 *
	 * @throws SQLException
	 */
	@Override
	public Integer caseloadDedBenficiUpdate(final List<CaseloadDedBeneficiaries> listCaseloadDedBenfi)  {
		final String sql = getQuery("OCMFAPRO_UPDATE_CASELOAD_DED_BENEFICIARIES");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (CaseloadDedBeneficiaries caseloadDedBenfic : listCaseloadDedBenfi) {
			parameters.add(new BeanPropertySqlParameterSource(caseloadDedBenfic));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (listCaseloadDedBenfi.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to delete records from  data base tables based on
	 *
	 * @param listCaseloadDedBenfi List<AllowableOffenceTypes>
	 *
	 * @throws SQLException
	 */
	@Override
	public Integer caseloadDedBenficDelete(final List<CaseloadDedBeneficiaries> listCaseloadDedBenfi) {
		final String sql = getQuery("OCMFAPRO_DELETE_CASELOAD_DED_BENEFICIARIES");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (CaseloadDedBeneficiaries caseloadDedBenfic : listCaseloadDedBenfi) {
			parameters.add(new BeanPropertySqlParameterSource(caseloadDedBenfic));
		}
		try {
			String tableName = "CASELOAD_DED_BENEFICIARIES";
			String whereClause = "CASELOAD_DED_BENEFICIARY_ID = :caseloadDedBeneficiaryId";
			batchUpdatePreDeletedRows(tableName, whereClause , parameters);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method caseloadDedBenficDelete", e);
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));

		if (listCaseloadDedBenfi.size() == returnArray.length) {
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
	@Override
	public List<CaseloadDeductionDetails> caseloadDedDetExecuteQuery(final CaseloadDeductionDetails objSearchDao) {
		final String sql = getQuery("OCMFAPRO_FIND_CASELOAD_DEDUCTION_DETAILS");
		final RowMapper<CaseloadDeductionDetails> mapper = Row2BeanRowMapper.makeMapping(sql,
				CaseloadDeductionDetails.class, csdDetMapping);
		final List<CaseloadDeductionDetails> returnList = namedParameterJdbcTemplate.query(sql,
				createParams("caseloadId", objSearchDao.getCaseloadId(), "deductionType",
						objSearchDao.getDeductionType()), mapper);
		return returnList;
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
	@Override
	public Integer caseloadDedDetInsert(final List<CaseloadDeductionDetails> lstCaseloadDeductionDetails) {
		String sql = getQuery("OCMFAPRO_INSERT_CASELOAD_DEDUCTION_DETAILS");
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
	 * This method is used to update the data base tables based on
	 *
	 * @param lstCaseloadDeductionDetails
	 *            List<CaseloadDeductionDetails>
	 *
	 *
	 */
	@Override
	public Integer caseloadDedDetUpdate(final List<CaseloadDeductionDetails> lstCaseloadDeductionDetails) {
		String sql = getQuery("OCMFAPRO_UPDATE_CASELOAD_DEDUCTION_DETAILS");
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
	@Override
	public Integer caseloadDedDetDelete(final List<CaseloadDeductionDetails> lstCaseloadDeductionDetails) {
		String sql = getQuery("OCMFAPRO_DELETE_CASELOAD_DEDUCTION_DETAILS");
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
			logger.error("Exception occured in " + this.getClass().getName() + " in method caseloadDedDetDelete", e);
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstCaseloadDeductionDetails.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	@Override
	public Integer updateCaseloadDedBeneficiariesAmount(final CaseloadDedBeneficiaries bean) {
		final String sql = getQuery("OCMFAPRO_UPDATE_CASELOAD_DED_BENEFICIARIES_AMOUNT");
		try {
			namedParameterJdbcTemplate.update(sql,
					createParams("caseloadId", bean.getCaseloadId(), "deductionType", bean.getDeductionType()));
		} catch (Exception e) {
			return 0;
		}
		return 1;
	}

	@Override
	public Integer updateCaseloadDedBeneficiariesPercentage(final BigDecimal percentage,
															BigDecimal caseloadDedBeneficiaryId,String modifyUserId) {
		final String sql = getQuery("OCMFAPRO_UPDATE_CASELOAD_DED_BENEFICIARIES_PERCENTAGE");
		try {
			namedParameterJdbcTemplate.update(sql,
					createParams("percent", percentage, "caseloadDedBeneficiaryId", caseloadDedBeneficiaryId, "modifyUserId", modifyUserId));
		} catch (Exception e) {
			return 0;
		}
		return 1;
	}

	@Override
	public List<CaseloadDedBeneficiaries> dedPriorities(final CaseloadDedBeneficiaries objSearchDao) {
		final String sql = getQuery("OCMFAPRO_DED_PRIORITIES");
		final RowMapper<CaseloadDedBeneficiaries> CaseloadDedBeneficiariesRowMapper = Row2BeanRowMapper.makeMapping(sql,
				CaseloadDedBeneficiaries.class, caseloadDedBeneficiariesMapping);
		final List<CaseloadDedBeneficiaries> returnList = namedParameterJdbcTemplate.query(sql,
				createParams("caseloadId", objSearchDao.getCaseloadId(), "deductionType",
						objSearchDao.getDeductionType()),
				CaseloadDedBeneficiariesRowMapper);
		return returnList;
	}

	@Override
	public BigDecimal getPriorityAmount(final String caseloadId, final String deductionType,
										final BigDecimal priority) {
		final String sql = getQuery("OCMFAPRO_GET_PRIORITY_AMOUNT");
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
	public Map<String, Object> calcBenTotal(final String caseloadId, final String deductionType) {
		final String sql = getQuery("OCMFAPRO_CALC_BEN_TOTAL");
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
	public BigDecimal getReceiptPercentage(String deductionType, String caseloadId) {
		final String sql = getQuery("OCMFAPRO_GET_RECEIPT_PERCENTAGE");
		return namedParameterJdbcTemplate.queryForObject(sql,
				createParams("deductionType", deductionType, "caseloadId", caseloadId), BigDecimal.class);
	}

	@Override
	public Integer updateCaseloadDeductionProfilesPercentage(BigDecimal percentage, String caseloadId,
															 String deductionType,String modifyUserId) {
		final String sql = getQuery("OCMFAPRO_UPDATE_CASELOAD_DEDUCTION_PROFILES_PERCENTAGE");
		return namedParameterJdbcTemplate.update(sql,
				createParams("percentage", percentage, "caseloadId", caseloadId, "deductionType", deductionType,"modifyUserId",modifyUserId));
	}

	@Override
	public List<FeeAccountProfiles> getExistingOffenders(CaseloadDeductionProfiles data) {
		final String sql = getQuery("OCMFAPRO_FIND_OFFDED_EXECUTEQUERY");
		final RowMapper<FeeAccountProfiles> OffencesRowMapper = Row2BeanRowMapper.makeMapping(sql,
				FeeAccountProfiles.class, csdBenMapping);
		final ArrayList<FeeAccountProfiles> returnList = (ArrayList<FeeAccountProfiles>) namedParameterJdbcTemplate.query(sql,
				createParams("deductionType", data.getDeductionType()), OffencesRowMapper);
		return returnList;
	}

	@Override
	public Integer updateExistingOff(List<FeeAccountProfiles> updateList) {
		final String sql = getQuery("OCMFAPRO_OFFDED_UPDATE");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (FeeAccountProfiles obj : updateList) {
			parameters.add(new BeanPropertySqlParameterSource(obj));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (returnArray.length > 0) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public List<CaseloadDedBeneficiaries> getExistingBenData(final CaseloadDedBeneficiaries data) {
		final String sql = getQuery("OCMFAPRO_GET_EXISTING_BEN_DATA");
		final RowMapper<CaseloadDedBeneficiaries> OffencesRowMapper = Row2BeanRowMapper.makeMapping(sql,
				CaseloadDedBeneficiaries.class, csdBenMapping);
		final ArrayList<CaseloadDedBeneficiaries> returnList = (ArrayList<CaseloadDedBeneficiaries>) namedParameterJdbcTemplate.query(sql,
				createParams("feeId", data.getOffenderFeeId()), OffencesRowMapper);
		return returnList;
	}

	@Override
	public Integer updateExistingBenData(List<CaseloadDedBeneficiaries> updateList) {
		final String sql = getQuery("OCMFAPRO_BEN_DATA_UPDATE");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (CaseloadDedBeneficiaries obj : updateList) {
			parameters.add(new BeanPropertySqlParameterSource(obj));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (returnArray.length > 0) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public List<SystemProfiles> sysPflExecuteQuery() {
		final String sql = getQuery("OCMFAPRO_SYSPFL_FIND_SYSTEM_PROFILES");
		final RowMapper<SystemProfiles> SystemProfilesRowMapper = Row2BeanRowMapper.makeMapping(sql,
				SystemProfiles.class, systemProfilesMapping);
		List<SystemProfiles> returnList = new ArrayList<>();
		returnList = namedParameterJdbcTemplate.query(sql, createParams(), SystemProfilesRowMapper);
		return returnList;
	}

	@Override
	public List<CaseloadDeductionDetails> getExistingDedData(final FeeAccountProfiles obj) {
		final String sql = getQuery("OCMFAPRO_GET_EXISTING_DED_DATA");
		final RowMapper<CaseloadDeductionDetails> OffencesRowMapper = Row2BeanRowMapper.makeMapping(sql,
				CaseloadDeductionDetails.class, csdBenMapping);
		final ArrayList<CaseloadDeductionDetails> returnList = (ArrayList<CaseloadDeductionDetails>) namedParameterJdbcTemplate.query(sql,
				createParams("feeId", obj.getOffenderFeeId()), OffencesRowMapper);
		return returnList;
	}

	@Override
	public Integer updateExistingDetData(List<CaseloadDeductionDetails> updateList) {
		final String sql = getQuery("OCMFAPRO_DED_DATA_UPDATE");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (CaseloadDeductionDetails obj : updateList) {
			parameters.add(new BeanPropertySqlParameterSource(obj));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (returnArray.length > 0) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public Integer updateAmountOffenderFeeAct(FeeAccountProfiles obj) {
		final String sql = getQuery("OCMFAPRO_UPDATE_AMOUNT_OFF_FEE_ACT");
		return namedParameterJdbcTemplate.update(sql,
				createParams("amount", obj.getAmount(), "deductionType", obj.getFeeCode(), "feeId", obj.getOffenderFeeId(), "offenderBookId", obj.getOffenderBookId()));
	}

	@Override
	public Integer deleteBenfData(FeeAccountProfiles obj) {
		final String sql = getQuery("OCMFAPRO_DELETE_EXSISTING_BEN_DATA");
		Integer returnVal = 0;
		try {
			String tableName = "OFF_FEE_DED_BENEFICIARIES";
			String whereClause = "OFFENDER_FEE_ID=:feeId";
			Map<String , Object> inputMap = new HashMap<String, Object>();
			inputMap.put("feeId", obj.getOffenderFeeId());
			inputMap.put("modifyUserId", obj.getModifyUserId());
			updatePreDeletedRow(tableName, whereClause , inputMap);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method deleteBenfData", e);
		}
		try {
			namedParameterJdbcTemplate.update(sql, createParams("feeId", obj.getOffenderFeeId()));
			returnVal = 1;
		} catch (Exception e) {
			returnVal = 0;
		}
		return returnVal;
	}

	@Override
	public Integer insertCsldBenData(List<CaseloadDedBeneficiaries> benList) {
		final String sql = getQuery("OCMFAPRO_CSLDDBEN_INSERT");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (CaseloadDedBeneficiaries obj : benList) {
			parameters.add(new BeanPropertySqlParameterSource(obj));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));

		if (returnArray.length > 0) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public BigDecimal getAmount(String caseloadId, String deductionType) {
		final String sql = getQuery("OCMFAPRO_GET_AMOUNT");
		BigDecimal PriorityAmount = BigDecimal.ZERO;
		try {
			PriorityAmount = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("caseloadId", caseloadId, "deductionType", deductionType),
					BigDecimal.class);
		} catch (Exception e) {
			throw new RuntimeException("Other error when getting the amount total.");
		}
		return PriorityAmount;
	}


	@Override
	public Integer deleteDedDetails(FeeAccountProfiles obj) {
		final String sql = getQuery("OCMFAPRO_DELETE_EXSISTING_DED_DETAILS");
		Integer returnVal = 0;
		try {
			String tableName = "OFF_FEE_DED_RECEIPTS";
			String whereClause = "OFFENDER_FEE_ID=:feeId";
			Map<String , Object> inputMap = new HashMap<String, Object>();
			inputMap.put("feeId", obj.getOffenderFeeId());
			inputMap.put("modifyUserId", obj.getModifyUserId());
			updatePreDeletedRow(tableName, whereClause , inputMap);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method deleteDedDetails", e);
		}
		try {
			namedParameterJdbcTemplate.update(sql, createParams("feeId", obj.getOffenderFeeId()));
			returnVal = 1;
		} catch (Exception e) {
			returnVal = 0;
		}
		return returnVal;
	}

	@Override
	public Integer insertCsldDedDetails(List<CaseloadDeductionDetails> dedDetails) {
		final String sql = getQuery("OCMFAPRO_CSLDDED_DETAILS_INSERT");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (CaseloadDeductionDetails obj : dedDetails) {
			parameters.add(new BeanPropertySqlParameterSource(obj));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));

		if (returnArray.length > 0) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public List<SystemProfiles> sysPfl2ExecuteQuery() {
		final String sql = getQuery("OCMFAPRO_SYSPFL_FIND_SYSTEM_PROFILES_FOR_ALL_OFFENDER");
		final RowMapper<SystemProfiles> SystemProfilesRowMapper = Row2BeanRowMapper.makeMapping(sql,
				SystemProfiles.class, systemProfilesMapping);
		List<SystemProfiles> returnList = new ArrayList<>();
		returnList = namedParameterJdbcTemplate.query(sql, createParams(), SystemProfilesRowMapper);
		return returnList;
	}

	@Override
	public BigDecimal getMaxAmount(final String feeActType, final String caseloadId) {
		final String sql = getQuery("OCMFAPRO_GET_MAXIMUM_FEE_ACCCOUNT_AMOUNT");
		BigDecimal PriorityAmount = BigDecimal.ZERO;
		try {
			PriorityAmount = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("feeActType", feeActType, "caseloadId", caseloadId),
					BigDecimal.class);
		} catch (Exception e) {
			PriorityAmount =  BigDecimal.ZERO;
		}
		return PriorityAmount;
	}

	
	@Override
	public List<SystemProfiles> sysPflExecuteQueryUpdateFeeAccounts() {
		final String sql = getQuery("OCMFAPRO_SYSPFL_FIND_SYSTEM_PROFILES_UPDATE_FEE_ACCOUNT");
		final RowMapper<SystemProfiles> SystemProfilesRowMapper = Row2BeanRowMapper.makeMapping(sql,
				SystemProfiles.class, systemProfilesMapping);
		List<SystemProfiles> returnList = new ArrayList<>();
		returnList = namedParameterJdbcTemplate.query(sql, createParams(), SystemProfilesRowMapper);
		return returnList;
	}

}

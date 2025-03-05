package net.syscon.s4.cf.deductions.impl;

import java.math.BigDecimal;
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
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.cf.deductions.OcdofaccRepository;
import net.syscon.s4.cf.deductions.beans.FeeAccountProfiles;
import net.syscon.s4.cf.deductions.beans.OffFeeBills;
import net.syscon.s4.common.beans.CaseloadDedBeneficiaries;
import net.syscon.s4.common.beans.OffenderBookings;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.CaseloadDeductionDetails;
import net.syscon.s4.im.beans.Corporates;
import net.syscon.s4.inmate.beans.CaseloadDeductionProfiles;
import net.syscon.s4.inst.booking.beans.Persons;

/**
 * Class OtdocfeeRepositoryImpl
 *
 */
@Repository
public class OcdofaccRepositoryImpl extends RepositoryBase implements OcdofaccRepository {

	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OcdofaccRepositoryImpl.class);

	private final Map<String, FieldMapper> cdpMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("EXTERNAL_PRIORITY_NO", new FieldMapper("externalPriorityNo"))
			.put("ACCOUNT_CODE", new FieldMapper("accountCode"))
			.put("CO_LIMIT_AMOUNT", new FieldMapper("coLimitAmount"))
			.put("CO_CREDIT_WHEN_INDIGENT_FLAG", new FieldMapper("coCreditWhenIndigentFlag"))
			.put("MAX_MONTHLY_AMOUNT", new FieldMapper("maxMonthlyAmount"))
			.put("MAX_TOTAL_AMOUNT", new FieldMapper("maxTotalAmount"))
			.put("EXPIRY_DATE", new FieldMapper("expiryDate")).put("PAYEE_PERSON_ID", new FieldMapper("payeePersonId"))
			.put("PAYEE_CORPORATE_ID", new FieldMapper("payeeCorporateId"))
			.put("MODIFY_USER_ID", new FieldMapper("modifyUserId")).put("MODIFY_DATE", new FieldMapper("modifyDate"))
			.put("LIST_SEQ", new FieldMapper("listSeq")).put("FLAT_RATE", new FieldMapper("flatRate"))
			.put("MINIMUM_TRUST_BALANCE", new FieldMapper("minimumTrustBalance"))
			.put("INDIGENT_MANDATORY_FLAG", new FieldMapper("indigentMandatoryFlag"))
			.put("COMM_CONDITION_TYPE", new FieldMapper("commConditionType"))
			.put("COMM_CONDITION_CODE", new FieldMapper("commConditionCode"))
			.put("MAX_RECURSIVE_AMOUNT", new FieldMapper("maxRecursiveAmount"))
			.put("CREATE_DATETIME", new FieldMapper("createDateTime"))
			.put("CREATE_USER_ID", new FieldMapper("createUserId"))
			.put("MODIFY_DATETIME", new FieldMapper("modifyDateTime"))
			.put("CATEGORY_TYPE", new FieldMapper("categoryType")).put("SEAL_FLAG", new FieldMapper("sealFlag"))
			.put("LOCATION", new FieldMapper("location")).put("AMOUNT", new FieldMapper("amount"))
			.put("FREQUENCY", new FieldMapper("frequency")).put("DAY_OF_MONTH", new FieldMapper("dayOfMonth"))
			.put("BACK_BILL", new FieldMapper("backBill")).put("CODE", new FieldMapper("code"))

			.put("FREQUENCY_CODE", new FieldMapper("frequencyCode"))
			.put("FREQUENCY_TYPE", new FieldMapper("frequencyType"))
			.put("non_billable_status", new FieldMapper("nonBillableStatus")).build();

	private final Map<String, FieldMapper> caseloadDedBeneficiariesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CASELOAD_DED_BENEFICIARY_ID", new FieldMapper("caseloadDedBeneficiaryId"))
			.put("CASELOAD_ID", new FieldMapper("caseloadId")).put("DEDUCTION_TYPE", new FieldMapper("deductionType"))
			.put("PERSON_ID", new FieldMapper("personId")).put("CORPORATE_ID", new FieldMapper("corporateId"))
			.put("PRIORITY", new FieldMapper("priority")).put("AMOUNT", new FieldMapper("amount"))
			.put("PERCENT", new FieldMapper("percent")).put("MODIFY_USER_ID", new FieldMapper("modifyUserId"))
			.put("MODIFY_DATE", new FieldMapper("modifyDate")).put("CREATE_DATETIME", new FieldMapper("createDatetime"))
			.put("CREATE_USER_ID", new FieldMapper("createUserId"))
			.put("MODIFY_DATETIME", new FieldMapper("modifyDatetime")).put("SEAL_FLAG", new FieldMapper("sealFlag"))
			.put("CO_CREDIT_WHEN_INDIGENT_FLAG", new FieldMapper("coCreditWhenIndigentFlag"))
			.put("FEE_ID", new FieldMapper("feeId")).put("BACK_BILL", new FieldMapper("backBill"))
			.put("FO_AL_ALL_OFFENDER_FLAG", new FieldMapper("foAlAllOffenderFlag"))
			.put("non_billable_status", new FieldMapper("nonBillableStatus")).build();

	private final Map<String, FieldMapper> corporatesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CORPORATE_NAME", new FieldMapper("corporateName")).put("CORPORATE_ID", new FieldMapper("corporateId"))
			.put("CASELOAD_ID", new FieldMapper("caseloadId")).build();

	private final Map<String, FieldMapper> personsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("PERSON_ID", new FieldMapper("personId")).put("LAST_NAME", new FieldMapper("lastName"))
			.put("FIRST_NAME", new FieldMapper("firstName")).build();
	private final Map<String, FieldMapper> csdBenMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CASELOAD_DED_BENEFICIARY_ID", new FieldMapper("caseloadDedBeneficiaryId"))
			.put("CASELOAD_ID", new FieldMapper("caseloadId")).put("DEDUCTION_TYPE", new FieldMapper("deductionType"))
			.put("PERSON_ID", new FieldMapper("personId")).put("CORPORATE_ID", new FieldMapper("corporateId"))
			.put("PRIORITY", new FieldMapper("priority")).put("AMOUNT", new FieldMapper("amount"))
			.put("PERCENT", new FieldMapper("percent")).put("MODIFY_USER_ID", new FieldMapper("modifyUserId"))
			.put("MODIFY_DATE", new FieldMapper("modifyDate")).put("CREATE_DATETIME", new FieldMapper("createDatetime"))
			.put("CREATE_USER_ID", new FieldMapper("createUserId"))
			.put("MODIFY_DATETIME", new FieldMapper("modifyDatetime")).put("SEAL_FLAG", new FieldMapper("sealFlag"))
			.put("CO_CREDIT_WHEN_INDIGENT_FLAG", new FieldMapper("coCreditWhenIndigentFlag"))
			.put("LONGEST_SUPV_EXP_DATE", new FieldMapper("longestSupvExpDate"))
			.put("SERVICE_DATE", new FieldMapper("serviceDate")).put("COMMENT_TEXT", new FieldMapper("commentText"))
			.put("OFFENDER_FEE_ID", new FieldMapper("offenderFeeId")).put("DAY_OF_MONTH", new FieldMapper("dayOfMonth"))
			.put("EFFECTIVE_DATE", new FieldMapper("effectiveDate")).put("EXPIRY_DATE", new FieldMapper("expiryDate"))
			.put("FEE_ACT_STATUS", new FieldMapper("feeActStatus")).put("FEE_CODE", new FieldMapper("feeCode"))
			.put("INFO_NUMBER", new FieldMapper("infoNumber")).put("ODP", new FieldMapper("odp"))
			.put("OFFENDER_BOOK_ID", new FieldMapper("offenderBookId")).put("START_DATE", new FieldMapper("startDate"))
			.put("STATUS_EFFECTIVE_DATE", new FieldMapper("statusEffectiveDate"))
			.put("OFF_FEE_DED_BENEFICIARY_ID", new FieldMapper("offFeeDedBeneficiaryId"))
			.put("non_billable_status", new FieldMapper("nonBillableStatus"))
			.put("supvPeriodDate", new FieldMapper("supvPeriodDate"))
			.put("overrideCount", new FieldMapper("overrideCount")).put("billCount", new FieldMapper("billCount"))
			.put("BILLABLE_FLAG", new FieldMapper("billableFlag")).build();

	private final Map<String, FieldMapper> csdDetMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CASELOAD_ID", new FieldMapper("caseloadId")).put("DEDUCTION_TYPE", new FieldMapper("deductionType"))
			.put("RECEIPT_TXN_TYPE", new FieldMapper("receiptTxnType")).put("PERCENTAGE", new FieldMapper("percentage"))
			.put("MODIFY_USER_ID", new FieldMapper("modifyUserId")).put("MODIFY_DATE", new FieldMapper("modifyDate"))
			.put("LIST_SEQ", new FieldMapper("listSeq")).put("FLAT_RATE", new FieldMapper("flatRate"))
			.put("MINIMUM_TRUST_BALANCE_FLAG", new FieldMapper("minimumTrustBalanceFlag"))
			.put("CREATE_DATETIME", new FieldMapper("createDatetime"))
			.put("CREATE_USER_ID", new FieldMapper("createUserId"))
			.put("MODIFY_DATETIME", new FieldMapper("modifyDatetime")).put("SEAL_FLAG", new FieldMapper("sealFlag"))
			.put("RECEIPT_PERCENT", new FieldMapper("receiptPercent"))
			.put("OFF_FEE_DED_RECEIPT_ID", new FieldMapper("offFeeDedReceiptId"))

			.build();

	private final Map<String, FieldMapper> mMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DEDUCTION_TYPE", new FieldMapper("deductionType")).put("CODE", new FieldMapper("code"))
			.put("DESCRIPTION", new FieldMapper("description")).put("DEDUCTION_DESC", new FieldMapper("deductionDesc"))
			.put("LIST_SEQ", new FieldMapper("listSeq")).put("ACTIVE_FLAG", new FieldMapper("activeFlag")).build();

	private final Map<String, FieldMapper> systemProfilesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("PROFILE_CODE", new FieldMapper("profileCode")).put("PROFILE_TYPE", new FieldMapper("profileType"))
			.put("CREATE_USER_ID", new FieldMapper("createUserId")).put("SEAL_FLAG", new FieldMapper("sealFlag"))
			.put("CREATE_DATETIME", new FieldMapper("createDatetime"))
			.put("MODIFY_USER_ID", new FieldMapper("modifyUserId"))
			.put("OLD_TABLE_NAME", new FieldMapper("oldTableName"))
			.put("PROFILE_VALUE", new FieldMapper("profileValue"))
			.put("MODIFY_DATETIME", new FieldMapper("modifyDatetime"))
			.put("PROFILE_VALUE_2", new FieldMapper("profileValue2")).put("DESCRIPTION", new FieldMapper("description"))
			.build();

	private final Map<String, FieldMapper> offenderBookingsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("OFFENDER_ID_DISPLAY", new FieldMapper("offenderIdDisplay"))
			.put("OFFENDER_ID", new FieldMapper("offenderId")).put("BIRTH_DATE", new FieldMapper("birthDate"))
			.put("ROOT_OFFENDER_ID", new FieldMapper("rootOffenderId")).put("AGY_LOC_ID", new FieldMapper(" agyLocId "))
			.put("LAST_NAME", new FieldMapper("lastName")).put("FIRST_NAME", new FieldMapper("firstName"))
			.put("OFFENDER_BOOK_ID", new FieldMapper("offenderBookId")).build();

	/**
	 * Creates new OtdocfeeRepositoryImpl class Object
	 */
	public OcdofaccRepositoryImpl() {
		// OtdocfeeRepositoryImpl
	}

	@Override
	public List<ReferenceCodes> feeActTypeRecordGroup(final String caseloadId) {
		final String sql = getQuery("OCDOFACC_FEE_ACCOUNT_TYPE_RG");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams("caseloadId", caseloadId), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("feeActTypeRecordGroup", e);
			return Collections.emptyList();
		}
	}

	@Override
	public List<CaseloadDeductionProfiles> caseloadDedProfExecuteQuery(final CaseloadDeductionProfiles objSearchDao) {
		final String sql = getQuery("OCDOFACC_FIND_CASELOAD_DEDUCTION_PROFILES");
		final RowMapper<CaseloadDeductionProfiles> OffencesRowMapper = Row2BeanRowMapper.makeMapping(sql,
				CaseloadDeductionProfiles.class, cdpMapping);
		final ArrayList<CaseloadDeductionProfiles> returnList = (ArrayList<CaseloadDeductionProfiles>) namedParameterJdbcTemplate
				.query(sql, createParams("caseloadId", objSearchDao.getCaseloadId(), "deductionType",
						objSearchDao.getDeductionType()), OffencesRowMapper);
		return returnList;
	}

	@Override
	public String calculateOn(final String deductionType) {
		final String sql = getQuery("OCDOFACC_CALCULATE_ON");
		String aCur = "";
		try {
			aCur = namedParameterJdbcTemplate.queryForObject(sql, createParams("deductionType", deductionType),
					String.class);
		} catch (Exception e) {

		}

		return aCur;
	}

	@Override
	public List<CaseloadDedBeneficiaries> caseloadDedBenficExecuteQuery(final CaseloadDedBeneficiaries objSearchDao) {
		final String sql = getQuery("OCDOFACC_FIND_CASELOAD_DED_BENEFICIARIES");
		List<CaseloadDedBeneficiaries> returnCaseLoad = new ArrayList<CaseloadDedBeneficiaries>();
		final RowMapper<CaseloadDedBeneficiaries> OffencesRowMapper = Row2BeanRowMapper.makeMapping(sql,
				CaseloadDedBeneficiaries.class, csdBenMapping);
		try {

			returnCaseLoad = namedParameterJdbcTemplate.query(sql, createParams("caseloadId",
					objSearchDao.getCaseloadId(), "deductionType", objSearchDao.getDeductionType()), OffencesRowMapper);
		} catch (Exception e) {
			return returnCaseLoad;
		}
		return returnCaseLoad;
	}

	@Override
	public List<FeeAccountProfiles> offDedExecuteQuery(FeeAccountProfiles searchObject) {
		final String sql = getQuery("OCDOFACC_FIND_OFFDED_EXECUTEQUERY");
		final RowMapper<FeeAccountProfiles> offencesRowMapper = Row2BeanRowMapper.makeMapping(sql,
				FeeAccountProfiles.class, csdBenMapping);
		final ArrayList<FeeAccountProfiles> returnList = (ArrayList<FeeAccountProfiles>) namedParameterJdbcTemplate
				.query(sql, createParams("offenderBookId", searchObject.getOffenderBookId()), offencesRowMapper);
		return returnList;
	}

	@Override
	public Persons ocmofaccPersons(final Long personId) {
		final String sql = getQuery("OCDOFACC_CGFKCHK_CSLD_DBEN_CSLD_DBEN_P");
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
	public Corporates ocmofaccCorporates(final BigDecimal corporateId) {
		final String sql = getQuery("OCDOFACC_CGFKCHK_CSLD_DBEN_CSLD_DBEN_C");
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

	@Override
	public List<CaseloadDeductionDetails> caseloadDedDetExecuteQuery(final CaseloadDeductionDetails objSearchDao) {
		final String sql = getQuery("OCDOFACC_FIND_CASELOAD_DEDUCTION_DETAILS");
		List<CaseloadDeductionDetails> returnList = new ArrayList<CaseloadDeductionDetails>();
		final RowMapper<CaseloadDeductionDetails> mapper = Row2BeanRowMapper.makeMapping(sql,
				CaseloadDeductionDetails.class, csdDetMapping);
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams("caseloadId", objSearchDao.getCaseloadId(),
					"deductionType", objSearchDao.getDeductionType()), mapper);
		} catch (Exception e) {
			return returnList;
		}
		return returnList;
	}

	@Override
	public List<ReferenceCodes> reciptTypeRecordGroup(final String caseloadType) {
		final String sql = getQuery("OCDOFACC_RECIPT_TYPE_RG");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams("caseloadType", caseloadType), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("reciptTypeRecordGroup", e);
			return Collections.emptyList();
		}
	}

	@Override
	public BigDecimal offdedPreInsert() {
		final String sql = getQuery("OCDOFACC_OFFDED_PREINSERT");
		BigDecimal obj = null;
		BigDecimal returnval = null;
		try {
			obj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), BigDecimal.class);

		} catch (Exception e) {
			logger.error("offdedPreInsert", e);
		}
		if (obj != null) {
			returnval = obj;
		}
		return returnval;
	}

	@Override
	public Integer offdedInsertQuery(List<FeeAccountProfiles> insertList) {
		final String sql = getQuery("OCDOFACC_OFFDED_INSERT");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (FeeAccountProfiles obj : insertList) {
			parameters.add(new BeanPropertySqlParameterSource(obj));
		}
		try {

			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error("offdedInsertQuery", e);
		}

		if (returnArray.length > 0) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public Integer offdedUpdateQuery(List<FeeAccountProfiles> updateList) {
		final String sql = getQuery("OCDOFACC_OFFDED_UPDATE");
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
	public Integer offdedDeleteQuery(List<FeeAccountProfiles> deleteList) {
		final String sql = getQuery("OCDOFACC_OFFDED_DELETE");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (FeeAccountProfiles obj : deleteList) {
			parameters.add(new BeanPropertySqlParameterSource(obj));
		}
		try {
			String tableName = "OFF_FEE_ACCOUNT_PROFILE";
			String whereClause = "OFFENDER_FEE_ID=:offenderFeeId";
			batchUpdatePreDeletedRows(tableName, whereClause , parameters);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method offdedDeleteQuery", e);
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (returnArray.length > 0) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public Long cslddbenPreInsert() {
		final String sql = getQuery("OCDOFACC_CSLDDBEN_PREINSERT");
		Long obj = null;
		Long returnval = null;
		obj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), Long.class);
		if (obj != null) {
			returnval = obj;
		}
		return returnval;

	}

	@Override
	public Integer cslddbenInsertQuery(List<CaseloadDedBeneficiaries> insertList) {
		final String sql = getQuery("OCDOFACC_CSLDDBEN_INSERT");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (CaseloadDedBeneficiaries obj : insertList) {
			parameters.add(new BeanPropertySqlParameterSource(obj));
		}
		try {

			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error("cslddbenInsertQuery", e);
		}

		if (returnArray.length > 0) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public Integer cslddbenUpdateQuery(List<CaseloadDedBeneficiaries> updateList) {
		final String sql = getQuery("OCDOFACC_CSLDDBEN_UPDATE");
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
	public Integer cslddbenDeleteQuery(List<CaseloadDedBeneficiaries> deleteList) {
		final String sql = getQuery("OCDOFACC_CSLDDBEN_DELETE");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (CaseloadDedBeneficiaries obj : deleteList) {
			parameters.add(new BeanPropertySqlParameterSource(obj));
		}
		try {
			String tableName = "OFF_FEE_DED_BENEFICIARIES";
			String whereClause = "OFF_FEE_DED_BENEFICIARY_ID = :offFeeDedBeneficiaryId";
			batchUpdatePreDeletedRows(tableName, whereClause , parameters);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method cslddbenDeleteQuery", e);
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (returnArray.length > 0) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public Integer csldddInsertQuery(List<CaseloadDeductionDetails> insertList) {
		final String sql = getQuery("OCDOFACC_CSLDDD_INSERT");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (CaseloadDeductionDetails obj : insertList) {
			parameters.add(new BeanPropertySqlParameterSource(obj));
		}
		try {

			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error("offdedInsertQuery", e);
		}

		if (returnArray.length > 0) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public Integer csldddUpdateQuery(List<CaseloadDeductionDetails> updateList) {
		final String sql = getQuery("OCDOFACC_CSLDDD_UPDATE");
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
	public Integer csldddDeleteQuery(List<CaseloadDeductionDetails> deleteList) {
		final String sql = getQuery("OCDOFACC_CSLDDD_DELETE");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (CaseloadDeductionDetails obj : deleteList) {
			parameters.add(new BeanPropertySqlParameterSource(obj));
		}
		try {
			String tableName = "OFF_FEE_DED_RECEIPTS";
			String whereClause = "OFF_FEE_DED_RECEIPT_ID=:offFeeDedReceiptId";
			batchUpdatePreDeletedRows(tableName, whereClause , parameters);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method csldddDeleteQuery", e);
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (returnArray.length > 0) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public Long feeOverrideDetailsExecuteQuery(Long feeId) {
		Long retVal = null;
		final String sql = getQuery("OCDOFACC_FEE_OVERRIDE_DETAILS");
		try {
			retVal = namedParameterJdbcTemplate.queryForObject(sql, createParams("fee_id", feeId), Long.class);
		} catch (Exception e) {
			logger.error(e);
		}
		return retVal;
	}

	@Override
	public List<CaseloadDedBeneficiaries> dedPriorities(final CaseloadDedBeneficiaries objSearchDao) {
		final String sql = getQuery("OCDOFACC_DED_PRIORITIES");
		final RowMapper<CaseloadDedBeneficiaries> CaseloadDedBeneficiariesRowMapper = Row2BeanRowMapper.makeMapping(sql,
				CaseloadDedBeneficiaries.class, caseloadDedBeneficiariesMapping);
		final List<CaseloadDedBeneficiaries> returnList = namedParameterJdbcTemplate.query(sql,
				createParams("caseloadId", objSearchDao.getCaseloadId(), "deductionType",
						objSearchDao.getDeductionType()),
				CaseloadDedBeneficiariesRowMapper);
		return returnList;
	}

	@Override
	public Integer updateCaseloadDedBeneficiariesPercentage(final BigDecimal percentage,
			final Long caseloadDedBeneficiaryId, final BigDecimal feeId, String userId) {
		final String sql = getQuery("OCDOFACC_UPDATE_CASELOAD_DED_BENEFICIARIES_PERCENTAGE");
		try {
			namedParameterJdbcTemplate.update(sql, createParams("percent", percentage, "offFeeDedBeneficiaryId",
					caseloadDedBeneficiaryId, "modifyUserId", userId));
		} catch (Exception e) {
			return 0;
		}
		return 1;
	}

	@Override
	public BigDecimal getPriorityAmount(final String caseloadId, final String deductionType, final BigDecimal priority,
			final BigDecimal feeId) {
		final String sql = getQuery("OCDOFACC_GET_PRIORITY_AMOUNT");
		BigDecimal PriorityAmount = BigDecimal.ZERO;
		try {
			PriorityAmount = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("offenderFeeId", feeId, "priority", priority), BigDecimal.class);
		} catch (Exception e) {
			throw new RuntimeException("Other error when getting the priority total.");
		}
		return PriorityAmount;
	}

	@Override
	public Map<String, Object> calcBenTotal(final String caseloadId, final String deductionType,
			final BigDecimal feeId) {
		final String sql = getQuery("OCDOFACC_CALC_BEN_TOTAL");
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			result = namedParameterJdbcTemplate.queryForMap(sql, createParams("offenderFeeId", feeId));
		} catch (Exception e) {
			throw new RuntimeException("otmfopro.errwhnsumbefire");
		}
		return result;
	}

	public List<ReferenceCodes> alAgyLocIdRgRecordGroup() {
		final String sql = getQuery("OCDOFACC_GET_CASELOAD_LOV");
		List<ReferenceCodes> returnList = new ArrayList<>();
		final List<ReferenceCodes> lstAgyRecords = new ArrayList<>();
		final RowMapper<ReferenceCodes> agyLocRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				mMapping);
		try {
			returnList = (ArrayList<ReferenceCodes>) namedParameterJdbcTemplate.query(sql, createParams(),
					agyLocRowMapper);

		} catch (Exception e) {
			logger.error("In alAgyLocIdRgRecordGroup method : ", e);
		}
		return returnList;
	}

	@Override
	public List<CaseloadDeductionProfiles> getBackBill(String feeCode, String caseloadId) {
		final String sql = getQuery("OCDOFACC_GET_BACK_BILL");
		List<CaseloadDeductionProfiles> returnList = new ArrayList();
		try {
			final RowMapper<CaseloadDeductionProfiles> mapper = Row2BeanRowMapper.makeMapping(sql,
					CaseloadDeductionProfiles.class, caseloadDedBeneficiariesMapping);
			returnList = namedParameterJdbcTemplate.query(sql,
					createParams("feeCode", feeCode, "caseloadId", caseloadId), mapper);
		} catch (Exception e) {
			return new ArrayList<>();
		}
		return returnList;

	}

	@Override
	public Date getSupvPeriodDate(final String caseloadId) {
		final String sql = getQuery("OCDOFACC_GET_SUPV_PERIOD_DATE");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql, createParams("caseloadId", caseloadId), Date.class);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public List<CaseloadDedBeneficiaries> caseloadFeeDedBenficExecuteQuery(
			final CaseloadDedBeneficiaries objSearchDao) {
		final String sql = getQuery("OCDOFACC_FEE_FIND_CASELOAD_DED_BENEFICIARIES");
		final RowMapper<CaseloadDedBeneficiaries> OffencesRowMapper = Row2BeanRowMapper.makeMapping(sql,
				CaseloadDedBeneficiaries.class, csdBenMapping);
		final ArrayList<CaseloadDedBeneficiaries> returnList = (ArrayList<CaseloadDedBeneficiaries>) namedParameterJdbcTemplate
				.query(sql, createParams("offenderFeeId", objSearchDao.getOffenderFeeId()), OffencesRowMapper);
		return returnList;
	}

	@Override
	public List<CaseloadDeductionDetails> caseloadFeeDetExecuteQuery(final CaseloadDeductionDetails objSearchDao) {
		final String sql = getQuery("OCDOFACC_FEE_FIND_CASELOAD_DEDUCTION_DETAILS");
		final RowMapper<CaseloadDeductionDetails> mapper = Row2BeanRowMapper.makeMapping(sql,
				CaseloadDeductionDetails.class, csdDetMapping);
		final List<CaseloadDeductionDetails> returnList = namedParameterJdbcTemplate.query(sql,
				createParams("offenderFeeId", objSearchDao.getOffenderFeeId()), mapper);
		return returnList;
	}

	@Override
	public List<FeeAccountProfiles> offdedPrevExecteQuery(FeeAccountProfiles searchObject) {
		final String sql = getQuery("OCDOFACC_FIND_OFFDED_PREVIOUSTAB_EXECUTEQUERY");
		final RowMapper<FeeAccountProfiles> offencesRowMapper = Row2BeanRowMapper.makeMapping(sql,
				FeeAccountProfiles.class, csdBenMapping);
		final ArrayList<FeeAccountProfiles> returnList = (ArrayList<FeeAccountProfiles>) namedParameterJdbcTemplate
				.query(sql, createParams("offenderBookId", searchObject.getOffenderBookId()), offencesRowMapper);
		return returnList;
	}

	@Override
	public List<SystemProfiles> sysPflExecuteQuery() {
		final String sql = getQuery("OCDOFACC_SYSPFL_FIND_SYSTEM_PROFILES");
		final RowMapper<SystemProfiles> SystemProfilesRowMapper = Row2BeanRowMapper.makeMapping(sql,
				SystemProfiles.class, systemProfilesMapping);
		List<SystemProfiles> returnList = new ArrayList<>();
		returnList = namedParameterJdbcTemplate.query(sql, createParams(), SystemProfilesRowMapper);
		return returnList;
	}

	@Override
	public List<FeeAccountProfiles> sysLongSupPflExecuteQuery(FeeAccountProfiles object) {
		final String sql = getQuery("OCDOFACC_SYSPFL_FIND_LONG_SUPER_EXPIRY_DATE_SYSTEM_PROFILES");
		final RowMapper<FeeAccountProfiles> rowMapper = Row2BeanRowMapper.makeMapping(sql, FeeAccountProfiles.class,
				csdBenMapping);
		List<FeeAccountProfiles> returnList = new ArrayList<>();
		returnList = namedParameterJdbcTemplate.query(sql, createParams("offenderBookId", object.getOffenderBookId()),
				rowMapper);
		return returnList;
	}

	@Override
	public List<CaseloadDedBeneficiaries> dedPrioritiesFeeBenf(final CaseloadDedBeneficiaries objSearchDao) {
		final String sql = getQuery("OCDOFACC_DED_PRIORITIES_FEE_BENF");
		final RowMapper<CaseloadDedBeneficiaries> CaseloadDedBeneficiariesRowMapper = Row2BeanRowMapper.makeMapping(sql,
				CaseloadDedBeneficiaries.class, caseloadDedBeneficiariesMapping);
		final List<CaseloadDedBeneficiaries> returnList = namedParameterJdbcTemplate.query(sql,
				createParams("offenderFeeId", objSearchDao.getOffenderFeeId()), CaseloadDedBeneficiariesRowMapper);
		return returnList;
	}

	@Override
	public String getDescription(final String code, final String domain) {
		final String sql = getQuery("OCDOFACC_GET_DESCRIPTION");
		String returnVal = "";
		try {
			returnVal = namedParameterJdbcTemplate.queryForObject(sql, createParams("code", code, "domain", domain),
					String.class);
		} catch (Exception e) {
			returnVal = "";
		}

		return returnVal;
	}

	@Override
	public List<CaseloadDeductionProfiles> getFreequencyValues(String location, String feeCode) {
		final String sql = getQuery("OCDOFACC_GET_FREEQUENCY_VALUES");
		final RowMapper<CaseloadDeductionProfiles> CaseloadDedBeneficiariesRowMapper = Row2BeanRowMapper
				.makeMapping(sql, CaseloadDeductionProfiles.class, cdpMapping);
		final List<CaseloadDeductionProfiles> returnList = namedParameterJdbcTemplate.query(sql,
				createParams("location", location, "feeCode", feeCode), CaseloadDedBeneficiariesRowMapper);
		;
		return returnList;
	}

	@Override
	public Integer updateSupvLongExpiryDate(List<FeeAccountProfiles> updateList) {
		final String sql = getQuery("OCDOFACC_SUPV_LONG_UPDATE");
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
	public Integer insertSupvLongExpiryDate(List<FeeAccountProfiles> updateList) {
		final String sql = getQuery("OCDOFACC_SUPV_LONG_INSERT");
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
	public List<FeeAccountProfiles> getSupvFeeActProfilesDet() {
		final String sql = getQuery("OCDOFACC_SUPV_FEE_ACT_PROFILES");
		final RowMapper<FeeAccountProfiles> offencesRowMapper = Row2BeanRowMapper.makeMapping(sql,
				FeeAccountProfiles.class, csdBenMapping);
		return namedParameterJdbcTemplate.query(sql, createParams(), offencesRowMapper);
	}

	@Override
	public Integer updateFeeActStatusForLongSupv(List<FeeAccountProfiles> newFeeActList) {
		final String sql = getQuery("OCDOFACC_UPDATE_FEE_ACT_STATUS_LONG_SUPV_DATE");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (FeeAccountProfiles obj : newFeeActList) {
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
	public List<FeeAccountProfiles> feeAccountProfileHistory(FeeAccountProfiles object) {
		final String sql = getQuery("OCDOFACC_RETRIVE_FEE_ACCOUNT_HISTORY_DATA");
		ArrayList<FeeAccountProfiles> returnList = new ArrayList<>();
		final RowMapper<FeeAccountProfiles> offencesRowMapper = Row2BeanRowMapper.makeMapping(sql,
				FeeAccountProfiles.class, csdBenMapping);
		try {
			returnList = (ArrayList<FeeAccountProfiles>) namedParameterJdbcTemplate.query(sql,
					createParams("offenderFeeId", object.getOffenderFeeId()), offencesRowMapper);
		} catch (Exception e) {
			return null;
		}
		return returnList;
	}

	@Override
	public Integer deleteFeeAccountProfileHistory(List<FeeAccountProfiles> returnList) {
		final String sql = getQuery("OCDOFACC_FEE_ACCOUNT_PROFILE_HISTORY_DELETE");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (FeeAccountProfiles obj : returnList) {
			parameters.add(new BeanPropertySqlParameterSource(obj));
		}
		try {
			String tableName = "OFF_FEE_ACCOUNT_PROFILE_HTY";
			String whereClause = "OFFENDER_FEE_ID=:offenderFeeId";
			batchUpdatePreDeletedRows(tableName, whereClause , parameters);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method deleteFeeAccountProfileHistory", e);
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (returnArray.length > 0) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public List<SystemProfiles> sysPflDedExecuteQuery() {
		final String sql = getQuery("OCDOFACC_SYSPFL_DEDUDCTION_FIND_SYSTEM_PROFILES");
		final RowMapper<SystemProfiles> SystemProfilesRowMapper = Row2BeanRowMapper.makeMapping(sql,
				SystemProfiles.class, systemProfilesMapping);
		List<SystemProfiles> returnList = new ArrayList<>();
		returnList = namedParameterJdbcTemplate.query(sql, createParams(), SystemProfilesRowMapper);
		return returnList;
	}

	@Override
	public List<OffenderBookings> returnOffenderBookingObject(Long offenderBookId) {
		final String sql = getQuery("OCDOFACC_FIND_OFFENDR_ID_FROM_BOOKINGS_OBJECT");
		final RowMapper<OffenderBookings> OffencesRowMapper = Row2BeanRowMapper.makeMapping(sql, OffenderBookings.class,
				offenderBookingsMapping);
		final ArrayList<OffenderBookings> returnList = (ArrayList<OffenderBookings>) namedParameterJdbcTemplate
				.query(sql, createParams("offenderBookId", offenderBookId), OffencesRowMapper);
		return returnList;
	}

	@Override
	public List<OffenderBookings> getOffenderPreviousOffenderBoookings(BigDecimal offenderId) {
		final String sql = getQuery("OCDOFACC_FIND_OFFENDR_BOOKING_IDS_FROM_BOOKINGS_OBJECT");
		final RowMapper<OffenderBookings> OffencesRowMapper = Row2BeanRowMapper.makeMapping(sql, OffenderBookings.class,
				offenderBookingsMapping);
		final ArrayList<OffenderBookings> returnList = (ArrayList<OffenderBookings>) namedParameterJdbcTemplate
				.query(sql, createParams("offenderId", offenderId), OffencesRowMapper);
		return returnList;
	}

	@Override
	public BigDecimal getMaxAmount(final BigDecimal feeId) {
		final String sql = getQuery("OCDOFACC_GET_MAXIMUM_FEE_ACCCOUNT_AMOUNT");
		BigDecimal PriorityAmount = BigDecimal.ZERO;
		try {
			final RowMapper<FeeAccountProfiles> offencesRowMapper = Row2BeanRowMapper.makeMapping(sql,
					FeeAccountProfiles.class, csdBenMapping);
			final ArrayList<FeeAccountProfiles> returnList = (ArrayList<FeeAccountProfiles>) namedParameterJdbcTemplate
					.query(sql, createParams("offenderFeeId", feeId), offencesRowMapper);
			if (returnList.size() > 0) {
				PriorityAmount = returnList.get(0).getAmount();
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Other error when getting the priority total.");
		}
		return PriorityAmount;
	}

	@Override
	public Integer offDedUpdateFullQuery(FeeAccountProfiles offDedUpdateFull) {
		final String sql = getQuery("OCDOFACC_OFFDED_UPDATE");
		int returnArray;
		SqlParameterSource sqlParam = new BeanPropertySqlParameterSource(offDedUpdateFull);
		returnArray = namedParameterJdbcTemplate.update(sql, sqlParam);
		if (returnArray > 0) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public Integer offFeeAcntUpdateQuery(FeeAccountProfiles offDedUpdateFull) {
		final String sql = getQuery("OCDOFACC_OFF_ACNT_PROFILE_UPDATE");
		int returnArray;
		SqlParameterSource sqlParam = new BeanPropertySqlParameterSource(offDedUpdateFull);
		returnArray = namedParameterJdbcTemplate.update(sql, sqlParam);
		if (returnArray > 0) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public List<FeeAccountProfiles> getCurrentDataRecord(BigDecimal offenderFeeId) {
		final String sql = getQuery("OCDOFACC_FIND_OFFDED_CURRENT_RECORD_EXECUTEQUERY");
		final RowMapper<FeeAccountProfiles> offencesRowMapper = Row2BeanRowMapper.makeMapping(sql,
				FeeAccountProfiles.class, csdBenMapping);
		final ArrayList<FeeAccountProfiles> returnList = (ArrayList<FeeAccountProfiles>) namedParameterJdbcTemplate
				.query(sql, createParams("offenderFeeId", offenderFeeId), offencesRowMapper);
		return returnList;
	}

	@Override
	public Integer updateFeeAcntProfileExpireDate(FeeAccountProfiles fap) {
		final String sql = getQuery("OCDOFACC_OFF_ACNT_PROFILE_UPDATE_EXPIRE_DATE");
		int returnArray = 0;
		SqlParameterSource sqlParam = new BeanPropertySqlParameterSource(fap);
		returnArray = namedParameterJdbcTemplate.update(sql, sqlParam);
		if (returnArray > 0) {
			return 1;
		} else {
			return 0;
		}
	}

	public Integer deletSupvHistoryRecord(final FeeAccountProfiles feeProfile) {
		final String sql = getQuery("OCDOFACC_OFF_ACNT_PROFILE_SUPV_STS_HISTORY");
		final Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("P_off_book_id", feeProfile.getOffenderBookId());
		Integer returnArray = null;
		try {
			String tableName = "OFF_SUPERVISION_DETAILS";
			String whereClause = "offender_book_id=:P_off_book_id";
			Map<String , Object> inputMap = new HashMap<String, Object>();
			inputMap.put("P_off_book_id", feeProfile.getOffenderBookId());
			inputMap.put("modifyUserId", feeProfile.getModifyUserId());
			updatePreDeletedRow(tableName, whereClause , inputMap);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method deletSupvHistoryRecord", e);
		}
		returnArray = namedParameterJdbcTemplate.update(sql, paramMap);
		if (returnArray != 0) {
			returnArray = 1;
			return returnArray;
		}
		return returnArray;
	}

	@Override
	public Date getOffenderEventDate(Long offenderBookId) {
		final String sql = getQuery("OCDOFACC_GET_OFFENDER_EVENT_DATE");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql, createParams("offenderBookId", offenderBookId),
					Date.class);
		} catch (Exception e) {
			logger.error("getOffenderEventDate : ", e);
			return null;
		}
	}

	@Override
	public List<FeeAccountProfiles> getFeeAccountListCaseloadBased(FeeAccountProfiles feeObject) {
		final String sql = getQuery("OCDOFACC_FIND_OFFDED_CASELOAD_BASED_EXECUTEQUERY");
		final RowMapper<FeeAccountProfiles> offencesRowMapper = Row2BeanRowMapper.makeMapping(sql,
				FeeAccountProfiles.class, csdBenMapping);
		final ArrayList<FeeAccountProfiles> returnList = (ArrayList<FeeAccountProfiles>) namedParameterJdbcTemplate
				.query(sql, createParams("offenderBookId", feeObject.getOffenderBookId(), "caseloadId",
						feeObject.getCaseloadId(), "feeCode", feeObject.getFeeCode()), offencesRowMapper);
		return returnList;
	}
	
	@Override
	public OffFeeBills getOldDataOffFeeBills(String billId) {
		final String sql = getQuery("OCDOFACC_GET_OLD_DATA_OFF_FEE_BILLS");
		final RowMapper<OffFeeBills> feeBillTrnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffFeeBills.class, csdBenMapping);
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("billId",billId),
				feeBillTrnRowMapper);
	}

}

package net.syscon.s4.inmate.trust.deductions.beneficiaryinquiry.impl;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
import net.syscon.s4.im.beans.CorporateAddressV;
import net.syscon.s4.im.beans.Corporates;
import net.syscon.s4.inmate.beans.OffenderBeneficiaries;
import net.syscon.s4.inmate.beans.OffenderDeductions;
import net.syscon.s4.inmate.trust.deductions.beneficiaryinquiry.OcicbeneRepository;
import oracle.jdbc.OracleTypes;
/**
 * Class OcicbeneRepositoryImpl
 */
@Repository
public class OcicbeneRepositoryImpl extends RepositoryBase implements OcicbeneRepository{

	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OcicbeneRepositoryImpl.class.getName());
private final Map<String, FieldMapper> corporateAddressVMapping = new ImmutableMap.Builder<String, FieldMapper>()
.put("OWNER_ID", 						new FieldMapper("ownerId"))
.put("PROV_STATE_DESC", 				new FieldMapper("provStateDesc"))
.put("ADDRESS_TYPE", 					new FieldMapper("addressType"))
.put("SUITE_NUMBER", 					new FieldMapper("suiteNumber"))
.put("ZIP_POSTAL_CODE", 				new FieldMapper("zipPostalCode"))
.put("OWNER_SEQ", 						new FieldMapper("ownerSeq"))
.put("MAIL_FLAG", 						new FieldMapper("mailFlag"))
.put("SUSPENDED_FLAG", 					new FieldMapper("suspendedFlag"))
.put("PRIMARY_FLAG", 					new FieldMapper("primaryFlag"))
.put("STREET", 						    new FieldMapper("street"))
.put("STREET_DIRECTION", 				new FieldMapper("streetDirection"))
.put("COUNTRY_DESC", 					new FieldMapper("countryDesc"))
.put("SUSPENDED_DATE", 					new FieldMapper("suspendedDate"))
.put("OWNER_CLASS", 					new FieldMapper("ownerClass"))
.put("OWNER_CODE", 						new FieldMapper("ownerCode"))
.put("CITY_CODE", 						new FieldMapper("cityCode"))
.put("CONTACT_PERSON_NAME", 			new FieldMapper("contactPersonName"))
.put("PROV_STATE_CODE", 				new FieldMapper("provStateCode"))
.put("CASELOAD_ID", 					new FieldMapper("caseloadId"))
.put("CORPORATE_NAME", 					new FieldMapper("corporateName"))
.put("STREET_NUMBER", 					new FieldMapper("streetNumber"))
.put("MAIL_CARE_OF", 					new FieldMapper("mailCareOf"))
.put("ADDRESS_ID", 						new FieldMapper("addressId"))
.put("COUNTRY_CODE", 					new FieldMapper("countryCode"))
.put("CORPORATE_ID", 					new FieldMapper("corporateId"))
.put("CITY_DESC", 						new FieldMapper("cityDesc"))
.build();
private final Map<String, FieldMapper> offenderBeneficiariesMapping = new ImmutableMap.Builder<String, FieldMapper>()
.put("OVERRIDE_AMOUNT", 				new FieldMapper("overrideAmount"))
.put("COMMENT_TEXT", 					new FieldMapper("commentText"))
.put("RECURSIVE_AMOUNT", 				new FieldMapper("recursiveAmount"))
.put("CREATE_USER_ID", 					new FieldMapper("createUserId"))
.put("OFFENDER_DEDUCTION_ID", 			new FieldMapper("offenderDeductionId"))
.put("PRIORITY", 						new FieldMapper("priority"))
.put("MODIFY_USER_ID", 					new FieldMapper("modifyUserId"))
.put("UNKNOWN_BEN_ID", 					new FieldMapper("unknownBenId"))
.put("PERSON_ID", 						new FieldMapper("personId"))
.put("OFFENDER_ID", 					new FieldMapper("offenderId"))
.put("MONTHLY_AMOUNT", 					new FieldMapper("monthlyAmount"))
.put("TBD_FLAG", 						new FieldMapper("tbdFlag"))
.put("PERCENT", 						new FieldMapper("percent"))
.put("AMOUNT", 						    new FieldMapper("amount"))
.put("RECEIVED_AMOUNT", 				new FieldMapper("receivedAmount"))
.put("SEAL_FLAG", 						new FieldMapper("sealFlag"))
.put("CREATE_DATETIME", 				new FieldMapper("createDateTime"))
.put("MODIFY_DATETIME", 				new FieldMapper("modifyDateTime"))
.put("BENEFICIARY_ID", 					new FieldMapper("beneficiaryId"))
.put("CORPORATE_ID", 					new FieldMapper("corporateId"))
.put("LAST_NAME", 						new FieldMapper("lastName"))
.put("OFFENDER_ID_DISPLAY", 			new FieldMapper("offenderIdDisplay"))

.build();
private final Map<String, FieldMapper> offenderDeductionsMapping = new ImmutableMap.Builder<String, FieldMapper>()
.put("EFFECTIVE_DATE", 					new FieldMapper("effectiveDate"))
.put("DEDUCTION_PRIORITY", 				new FieldMapper("deductionPriority"))
.put("PAYEE_PERSON_ID", 				new FieldMapper("payeePersonId"))
.put("MODIFY_USER_ID", 					new FieldMapper("modifyUserId"))
.put("ADJUSTMENT_TXN_ID", 				new FieldMapper("adjustmentTxnId"))
.put("INFORMATION_NUMBER", 				new FieldMapper("informationNumber"))
.put("OFFENDER_ID", 					new FieldMapper("offenderId"))
.put("MODIFY_DATE", 					new FieldMapper("modifyDate"))
.put("PAYEE_CORPORATE_ID", 				new FieldMapper("payeeCorporateId"))
.put("CASELOAD_ID", 					new FieldMapper("caseloadId"))
.put("ADJUSTMENT_REASON_CODE", 			new FieldMapper("adjustmentReasonCode"))
.put("OFFENDER_DEDUCTION_ID", 			new FieldMapper("offenderDeductionId"))
.put("ADJUSTMENT_TEXT", 				new FieldMapper("adjustmentText"))
.put("COMMENT_TEXT", 					new FieldMapper("commentText"))
.put("MAX_MONTHLY_AMOUNT", 				new FieldMapper("maxMonthlyAmount"))
.put("PAY_DEDUCTION_FLAG", 				new FieldMapper("payDeductionFlag"))
.put("DEDUCTION_AMOUNT", 				new FieldMapper("deductionAmount"))
.put("ADJUSTMENT_AMOUNT", 				new FieldMapper("adjustmentAmount"))
.put("ADJUSTMENT_USER_ID", 				new FieldMapper("adjustmentUserId"))
.put("DEDUCTION_TYPE", 					new FieldMapper("deductionType"))
.put("MAX_TOTAL_AMOUNT", 				new FieldMapper("maxTotalAmount"))
.put("CREDIT_LIMIT", 					new FieldMapper("creditLimit"))
.put("MAX_RECURSIVE_AMOUNT", 			new FieldMapper("maxRecursiveAmount"))
.put("PROCESS_PRIORITY_NUMBER", 		new FieldMapper("processPriorityNumber"))
.put("DEDUCTION_PERCENTAGE", 			new FieldMapper("deductionPercentage"))
.put("FIFO_FLAG", 						new FieldMapper("fifoFlag"))
.put("DEDUCTION_STATUS", 				new FieldMapper("deductionStatus"))
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

	/**
	 * Creates new OcicbeneRepositoryImpl class Object
	 */
	public OcicbeneRepositoryImpl() {
		// OcicbeneRepositoryImpl
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            CorporateAddressV
	 *
	 * @return List<CorporateAddressV>
	 *
	 * @
	 */
	public List<CorporateAddressV> vCorpExecuteQuery(final CorporateAddressV objSearchDao) {
		final String sql = getQuery("OCICBENE_VCORP_FIND_CORPORATE_ADDRESS_V");
		String preparedSql = null;
		final StringBuffer sqlQuery = new StringBuffer();
		String preSqlQuery = null;
		final MapSqlParameterSource params = new MapSqlParameterSource();
		sqlQuery.append(sql);
		if (objSearchDao != null) {
			sqlQuery.append(" WHERE ");
			if (objSearchDao.getCorporateName() != null) {
				sqlQuery.append(" CORPORATE_NAME  = :corporateName " + " AND ");
				params.addValue("corporateId", objSearchDao.getCorporateName());
			}
			if (objSearchDao.getCorporateId() != null) {
				sqlQuery.append(" CORPORATE_ID  = :corporateId " + " AND ");
				params.addValue("corporateId", objSearchDao.getCorporateId());
			}
			if (objSearchDao.getSuiteNumber() != null) {
				sqlQuery.append(" SUITE_NUMBER  = :suiteNumber " + " AND ");
				params.addValue("suiteNumber", objSearchDao.getSuiteNumber());
			}
			if ("Y".equals(objSearchDao.getPrimaryFlag())) {
				sqlQuery.append(" EXISTS (SELECT '1' FROM Offender_Beneficiaries OB, Offender_Deductions OD "
						+ " WHERE OB.offender_deduction_id = OD.offender_deduction_id "
						+ " AND OB.corporate_id = CORPORATE_ADDRESS_V.corporate_id) ");
			} else {
				sqlQuery.append(" EXISTS (SELECT ''1'' FROM Offender_Beneficiaries OB, Offender_Deductions OD "
						+ " WHERE OB.offender_deduction_id = OD.offender_deduction_id "
						+ " AND OB.corporate_id = CORPORATE_ADDRESS_V.corporate_id "
						+ " AND OD.caseload_id = :caseloadId)') ");
				params.addValue("caseloadId", objSearchDao.getCaseloadId());
			}
		}
		preparedSql = sqlQuery.toString().trim();
		if (preparedSql.endsWith("WHERE")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 5);
		}
		if (preparedSql.endsWith("AND")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 3);
		}
		preSqlQuery = preparedSql.concat(" ORDER BY CORPORATE_ID ");
		final RowMapper<CorporateAddressV> CorporateAddressVRowMapper = Row2BeanRowMapper.makeMapping(sql,
				CorporateAddressV.class, corporateAddressVMapping);
		List<CorporateAddressV> returnList = new ArrayList<>();
		returnList = namedParameterJdbcTemplate.query(preSqlQuery, params, CorporateAddressVRowMapper);
		return returnList;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            OffenderBeneficiaries
	 *
	 * @return List<OffenderBeneficiaries>
	 */
	public List<OffenderBeneficiaries> offBncExecuteQuery(final OffenderBeneficiaries objSearchDao) {
		final String sql = getQuery("OCICBENE_OFFBNC_FIND_OFFENDER_BENEFICIARIES");
		final RowMapper<OffenderBeneficiaries> OffenderBeneficiariesRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderBeneficiaries.class, offenderBeneficiariesMapping);
		List<OffenderBeneficiaries> returnList = new ArrayList<>();
		returnList = namedParameterJdbcTemplate.query(sql, createParams("CORPORATE_ID", objSearchDao.getCorporateId()),
				OffenderBeneficiariesRowMapper);
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
	 * @
	 */
	public List<SystemProfiles> sysPflExecuteQuery(final SystemProfiles objSearchDao) {
		final String sql = getQuery("OCICBENE_SYSPFL_FIND_SYSTEM_PROFILES");
		final RowMapper<SystemProfiles> SystemProfilesRowMapper = Row2BeanRowMapper.makeMapping(sql,
				SystemProfiles.class, systemProfilesMapping);
		List<SystemProfiles> returnList = new ArrayList<>();
		returnList = namedParameterJdbcTemplate.query(sql, createParams(), SystemProfilesRowMapper);
		return returnList;
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
		final String sql = getQuery("OCICBENE_CGFKCHK_OFF_BNC_OFF_BNC_OFF_D");
		final RowMapper<OffenderDeductions> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderDeductions.class, offenderDeductionsMapping);
		OffenderDeductions returnObj = new OffenderDeductions();
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
		} catch (Exception e) {
			return returnObj;
		}
		return returnObj;
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
	public OffenderBeneficiaries offBncPostQuery(final OffenderBeneficiaries objSearchDao) {
		final String sql = getQuery("OCICBENE_OFF_BNC_POSTQUERY");
		final RowMapper<OffenderBeneficiaries> OffenderBeneficiariesRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderBeneficiaries.class, offenderBeneficiariesMapping);
		OffenderBeneficiaries returnObj = new OffenderBeneficiaries();
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("OFFENDER_ID", objSearchDao.getOffenderId()), OffenderBeneficiariesRowMapper);
		} catch (Exception e) {
			return returnObj;
		}
		return returnObj;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            OffenderBeneficiaries
	 *
	 * @return OffenderDeductions
	 *
	 * @
	 */
	public OffenderDeductions offBncPopulateWriteOff(final OffenderBeneficiaries objSearchDao) {
		final String sql = getQuery("OCICBENE_OFF_BNC_POPULATE_WRITE_OFF");
		final RowMapper<OffenderDeductions> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderDeductions.class, offenderDeductionsMapping);
		OffenderDeductions returnObj = new OffenderDeductions();
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("OFFENDER_DEDUCTION_ID", objSearchDao.getOffenderDeductionId()), columnRowMapper);
		} catch (Exception e) {
			return returnObj;
		}
		return returnObj;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            OffenderBeneficiaries
	 *
	 * @return String
	 *
	 * @
	 */
	public String offBncCheckDedCat(final String deductionType) {
		final String sql = getQuery("OCICBENE_OFF_BNC_CHK_DED_CAT");
		String returnObj = null;
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams("deduction_type", deductionType),
					String.class);
		} catch (Exception e) {
			return returnObj;
		}
		return returnObj;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            OffenderBeneficiaries
	 *
	 * @return Integer
	 *
	 * @
	 */
	public Integer offBncGetMonAmt(final OffenderBeneficiaries objSearchDao) {
		final String sql = getQuery("OCICBENE_OFF_BNC_GET_MON_AMT");
		Integer returnObj = null;
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams("OFFENDER_DEDUCTION_ID",
					objSearchDao.getOffenderDeductionId(), "CORPORATE_ID", objSearchDao.getCorporateId()),
					Integer.class);
		} catch (Exception e) {
			return returnObj;
		}
		return returnObj;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            OffenderBeneficiaries
	 *
	 * @return BigDecimal
	 *
	 * @
	 */
	public BigDecimal offBncRecMonth(final OffenderBeneficiaries objSearchDao) {
		final String sql = getQuery("OCICBENE_OFF_BNC_REC_MONTH");
		BigDecimal returnObj = null;
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("OFFENDER_DEDUCTION_ID", objSearchDao.getOffenderDeductionId()), BigDecimal.class);
		} catch (Exception e) {
			return returnObj;
		}
		return returnObj;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param corpId
	 *
	 * @return BigDecimal
	 *
	 * @
	 */
	public BigDecimal getChequeSum(final BigDecimal corpId) {
		final String sql = getQuery("OCICBENE_GET_CHEQUE_SUM");
		BigDecimal returnObj = null;
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams("CORP_ID", corpId),
					BigDecimal.class);
		} catch (Exception e) {
			return returnObj;
		}
		return returnObj;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param corpId
	 *
	 * @return BigDecimal
	 *
	 * @
	 */
	public BigDecimal getPaymentAmount(final BigDecimal corpId) {
		final String sql = getQuery("OCICBENE_GET_PAYMENT_AMOUNT");
		BigDecimal returnObj = null;
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams("CORP_ID", corpId),
					BigDecimal.class);
		} catch (Exception e) {
			return returnObj;
		}
		return returnObj;
	}

	/**
	 * This method will get the default status code
	 * 
	 * @param objSearchDao
	 *            OffenderBeneficiaries
	 *
	 * @return BigDecimal
	 */
	public BigDecimal calculateBeneficiariesTotal(final OffenderBeneficiaries bean) {
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		Map<String, Object> returnObject = null;
		SqlParameter[] sqlParameters = new SqlParameter[4];
		sqlParameters = new SqlParameter[] { new SqlParameter("P_CORP_ID", OracleTypes.NUMBER),
				new SqlOutParameter("P_TOTL_AMT", OracleTypes.NUMBER),
				new SqlOutParameter("P_TOTL_PAID", OracleTypes.NUMBER),
				new SqlOutParameter("P_TOTL_OWING", OracleTypes.NUMBER) };
		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("TRUST").withProcedureName("CALCULATE_BENEFICIARIES_TOTAL")
				.declareParameters(sqlParameters);
		inParamMap.put("P_CORP_ID", bean.getCorporateId());
		inParamMap.put("P_TOTL_AMT", 0);
		inParamMap.put("P_TOTL_PAID", 0);
		inParamMap.put("P_TOTL_OWING", 0);
		final MapSqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		try {
			returnObject = simpleJDBCCall.execute(inParameter);

		} catch (Exception e) {
			logger.error("calculateBeneficiariesTotal: ", e);
		}
		return (BigDecimal) returnObject.get("P_TOTL_AMT");
	}

	/**
	 * This method is used to update the records in the data base tables based
	 * on
	 *
	 * @param lstAddresses
	 *
	 * @return Integer
	 *
	 * @
	 */
	public Integer corpUpdateSuspendDate(final Corporates beanObj) {
		final String sql = getQuery("OCICBENE_CORP_UPADTE_QUERY");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		parameters.add(new BeanPropertySqlParameterSource(beanObj));
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (returnArray.length == 1) {
			return 1;
		} else {
			return 0;
		}

	}

	@Override
	public String caseloadFlagData(String profileType, String profileCode) {
		final String sql = getQuery("OCICBENE_PROFILE_VALUE");
		String returnObj = null;
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("PPROFTYPE", profileType, "PPROFCODE", profileCode), String.class);
		} catch (Exception e) {
			return returnObj;
		}
		return returnObj;
	}
}

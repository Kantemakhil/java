package net.syscon.s4.inmate.trust.deductions.beneficiaryinquiry.impl;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;
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
import net.syscon.s4.im.beans.Offenders;
import net.syscon.s4.im.beans.SysDual;
import net.syscon.s4.inmate.beans.BankChequeBeneficiaries;
import net.syscon.s4.inmate.beans.OffenderBeneficiaries;
import net.syscon.s4.inmate.beans.OffenderDeductions;
import net.syscon.s4.inmate.beans.PersonAddressV;
import net.syscon.s4.inmate.trust.deductions.beneficiaryinquiry.OcipbeneRepository;
import net.syscon.s4.inmate.trust.deductions.impl.OcdcbeneRepositoryImpl;
import net.syscon.s4.inst.booking.beans.Persons;
import oracle.jdbc.OracleTypes;

/**
 * Class OcipbeneRepositoryImpl
 */
@Repository
public class OcipbeneRepositoryImpl extends RepositoryBase implements OcipbeneRepository{
	
	private static Logger logger = LogManager.getLogger(OcipbeneRepositoryImpl.class.getName());

	private final Map<String, FieldMapper> personsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("PROFILE_CODE", 				new FieldMapper("profileCode"))
			.put("CORONER_NUMBER", 				new FieldMapper("coronerNumber"))
			.put("ATTENTION", 					new FieldMapper("attention"))
			.put("LAST_NAME_SOUNDEX", 			new FieldMapper("lastNameSoundex"))
			.put("MIDDLE_NAME", 				new FieldMapper("middleName"))
			.put("PERSON_ID", 					new FieldMapper("personId"))
			.put("CITIZENSHIP", 				new FieldMapper("citizenship"))
			.put("CRIMINAL_HISTORY_TEXT", 		new FieldMapper("criminalHistoryText"))
			.put("LANGUAGE_CODE", 				new FieldMapper("languageCode"))
			.put("LAST_NAME", 					new FieldMapper("lastName"))
			.put("PRIMARY_LANGUAGE_CODE", 		new FieldMapper("primaryLanguageCode"))
			.put("SEAL_FLAG", 					new FieldMapper("sealFlag"))
			.put("CREATE_DATETIME", 			new FieldMapper("createDatetime"))
			.put("EMPLOYER", 					new FieldMapper("employer"))
			.put("MODIFY_DATETIME", 			new FieldMapper("modifyDatetime"))
			.put("SUSPENDED_FLAG", 				new FieldMapper("suspendedFlag"))
			.put("OCCUPATION_CODE", 			new FieldMapper("occupationCode"))
			.put("NAME_TYPE", 					new FieldMapper("nameType"))
			.put("COMPREHEND_ENGLISH_FLAG", 	new FieldMapper("comprehendEnglishFlag"))
			.put("CREATE_USER_ID", 				new FieldMapper("createUserId"))
			.put("MEMO_TEXT", 					new FieldMapper("memoText"))
			.put("DECEASED_DATE", 				new FieldMapper("deceasedDate"))
			.put("SUSPENDED_DATE", 				new FieldMapper("suspendedDate"))
			.put("MODIFY_USER_ID", 				new FieldMapper("modifyUserId"))
			.put("MIDDLE_NAME_KEY", 			new FieldMapper("middleNameKey"))
			.put("REMITTER_FLAG", 				new FieldMapper("remitterFlag"))
			.put("BIRTHDATE", 					new FieldMapper("birthdate"))
			.put("BIRTH_PLACE", 				new FieldMapper("birthPlace"))
			.put("LAST_NAME_KEY", 				new FieldMapper("lastNameKey"))
			.put("ALIAS_PERSON_ID", 			new FieldMapper("aliasPersonId"))
			.put("CARE_OF", 					new FieldMapper("careOf"))
			.put("STAFF_FLAG", 					new FieldMapper("staffFlag"))
			.put("ROOT_PERSON_ID", 				new FieldMapper("rootPersonId"))
			.put("FIRST_NAME_KEY", 				new FieldMapper("firstNameKey"))
			.put("MARITAL_STATUS", 				new FieldMapper("maritalStatus"))
			.put("SEX", 						new FieldMapper("sex"))
			.put("FIRST_NAME", 					new FieldMapper("firstName"))
			.put("INTERPRETER_REQUIRED", 		new FieldMapper("interpreterRequired"))
			.build();
	private final Map<String, FieldMapper> offenderBeneficiariesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("OVERRIDE_AMOUNT", 			new FieldMapper("overrideAmount"))
			.put("COMMENT_TEXT", 				new FieldMapper("commentText"))
			.put("RECURSIVE_AMOUNT", 			new FieldMapper("recursiveAmount"))
			.put("CREATE_USER_ID", 				new FieldMapper("createUserId"))
			.put("OFFENDER_DEDUCTION_ID", 		new FieldMapper("offenderDeductionId"))
			.put("PRIORITY", 					new FieldMapper("priority"))
			.put("MODIFY_USER_ID", 				new FieldMapper("modifyUserId"))
			.put("UNKNOWN_BEN_ID", 				new FieldMapper("unknownBenId"))
			.put("PERSON_ID", 					new FieldMapper("personId"))
			.put("OFFENDER_ID", 				new FieldMapper("offenderId"))
			.put("MONTHLY_AMOUNT", 				new FieldMapper("monthlyAmount"))
			.put("TBD_FLAG", 					new FieldMapper("tbdFlag"))
			.put("PERCENT", 					new FieldMapper("percent"))
			.put("AMOUNT", 						new FieldMapper("amount"))
			.put("RECEIVED_AMOUNT", 			new FieldMapper("receivedAmount"))
			.put("SEAL_FLAG", 					new FieldMapper("sealFlag"))
			.put("CREATE_DATETIME", 			new FieldMapper("createDateTime"))
			.put("MODIFY_DATETIME", 			new FieldMapper("modifyDateTime"))
			.put("BENEFICIARY_ID", 				new FieldMapper("beneficiaryId"))
			.put("CORPORATE_ID", 				new FieldMapper("corporateId"))
			.build();
	private final Map<String, FieldMapper> offenderDeductionsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("EFFECTIVE_DATE", 				new FieldMapper("effectiveDate"))
			.put("DEDUCTION_PRIORITY", 			new FieldMapper("deductionPriority"))
			.put("PAYEE_PERSON_ID", 			new FieldMapper("payeePersonId"))
			.put("MODIFY_USER_ID", 				new FieldMapper("modifyUserId"))
			.put("ADJUSTMENT_TXN_ID", 			new FieldMapper("adjustmentTxnId"))
			.put("INFORMATION_NUMBER", 			new FieldMapper("informationNumber"))
			.put("OFFENDER_ID", 				new FieldMapper("offenderId"))
			.put("MODIFY_DATE", 				new FieldMapper("modifyDate"))
			.put("PAYEE_CORPORATE_ID", 			new FieldMapper("payeeCorporateId"))
			.put("CASELOAD_ID", 				new FieldMapper("caseloadId"))
			.put("ADJUSTMENT_REASON_CODE", 		new FieldMapper("adjustmentReasonCode"))
			.put("OFFENDER_DEDUCTION_ID", 		new FieldMapper("offenderDeductionId"))
			.put("ADJUSTMENT_TEXT", 			new FieldMapper("adjustmentText"))
			.put("COMMENT_TEXT", 				new FieldMapper("commentText"))
			.put("MAX_MONTHLY_AMOUNT", 			new FieldMapper("maxMonthlyAmount"))
			.put("PAY_DEDUCTION_FLAG", 			new FieldMapper("payDeductionFlag"))
			.put("DEDUCTION_AMOUNT", 			new FieldMapper("deductionAmount"))
			.put("ADJUSTMENT_AMOUNT", 			new FieldMapper("adjustmentAmount"))
			.put("ADJUSTMENT_USER_ID", 			new FieldMapper("adjustmentUserId"))
			.put("DEDUCTION_TYPE", 				new FieldMapper("deductionType"))
			.put("MAX_TOTAL_AMOUNT", 			new FieldMapper("maxTotalAmount"))
			.put("CREDIT_LIMIT", 				new FieldMapper("creditLimit"))
			.put("MAX_RECURSIVE_AMOUNT", 		new FieldMapper("maxRecursiveAmount"))
			.put("PROCESS_PRIORITY_NUMBER", 	new FieldMapper("processPriorityNumber"))
			.put("DEDUCTION_PERCENTAGE", 		new FieldMapper("deductionPercentage"))
			.put("FIFO_FLAG",					new FieldMapper("fifoFlag"))
			.put("DEDUCTION_STATUS", 			new FieldMapper("deductionStatus"))
			.build();
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
	private final Map<String, FieldMapper> personAddressVMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CITY_DESC", 					new FieldMapper("cityDesc"))
			.put("STREET_NUMBER||'", 			new FieldMapper("streetNumber||'"))
			.put("STREET_DIRECTION", 			new FieldMapper("streetDirection"))
			.put("NULL", 						new FieldMapper("null"))
			.put("DECODE(STREET_NUMBER", 		new FieldMapper("decode(streetNumber"))
			.put("COUNTRY_DESC", 				new FieldMapper("countryDesc"))
			.put("PERSON_ID", 					new FieldMapper("personId"))
			.put("ZIP_POSTAL_CODE", 			new FieldMapper("zipPostalCode"))
			.put("')|", 						new FieldMapper("')|"))
			.put("STATE_DESC", 					new FieldMapper("stateDesc"))
			.put("SUITE_NUMBER", 				new FieldMapper("suiteNumber"))
			.build();
	private final Map<String, FieldMapper> sysDualMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("SYSDATE", 					new FieldMapper("sysDate"))
			.put("USER", 						new FieldMapper("user"))
			.build();
	private final Map<String, FieldMapper> headerMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("OFFENDEER_ID_DISPALY", 		new FieldMapper("offenderIdDisplay"))
			.put("LAST_NAME", 					new FieldMapper("lastName"))
			.put("FIRST_NAME", 					new FieldMapper("firstName"))
			.build();
	private final Map<String, FieldMapper> bankChequeBenMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CHEQUE_TXN_ID", 				new FieldMapper("chequeTxnId"))
			.put("CHEQUE_AMOUNT", 				new FieldMapper("chequeAmount"))
			.build();

	/**
	 * Creates new OcipbeneRepositoryImpl class Object
	 */
	public OcipbeneRepositoryImpl() {
		// OcipbeneRepositoryImpl
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            Persons
	 *
	 * @return List<Persons>
	 *
	 */
	public List<Persons> perExecuteQuery(final Persons objSearchDao) {
		final String sql = getQuery("OCIPBENE_PER_FIND_PERSONS");
		final RowMapper<Persons> personsRM = Row2BeanRowMapper.makeMapping(sql, Persons.class, personsMapping);
		String preparedSql = null;
		String preSqlQuery = null;
		final StringBuffer sqlQuery = new StringBuffer();
		final SystemProfiles objSystem = new SystemProfiles();
		final MapSqlParameterSource valuesList = new MapSqlParameterSource();
		List<Persons> lstPersons = new ArrayList<Persons>();
		if (objSearchDao != null) {
			objSystem.setProfileType("CLIENT");
			objSystem.setProfileCode("BEN_INQ_GLOB");
			final List<SystemProfiles> returnList = sysPflExecuteQuery(objSystem);
			Boolean blGlobalFlag = false;
			if (returnList != null && returnList.size() > 0) {
				final SystemProfiles objProfiles = returnList.get(0);
				if (objProfiles.getProfileValue() != null && "Y".equals(objProfiles.getProfileValue())) {
					blGlobalFlag = true;
				}
			}
			sqlQuery.append(sql);
			sqlQuery.append(" where ");
			if (objSearchDao.getLastName() != null) {
				sqlQuery.append(" last_name = :lastName " + " and");
				valuesList.addValue("lastName", objSearchDao.getLastName());
			}

			if (objSearchDao.getFirstName() != null) {
				sqlQuery.append(" first_name = :firstName " + " and");
				valuesList.addValue("firstName", objSearchDao.getFirstName());
			}

			if (objSearchDao.getPersonId() != null) {
				sqlQuery.append(" person_id = :personId " + " and");
				valuesList.addValue("personId", objSearchDao.getPersonId());
			}
			if (!blGlobalFlag) {
				sqlQuery.append(
						" person_id in (select ob.person_id  from offender_beneficiaries ob, offender_deductions od  where ob.offender_deduction_id = od.offender_deduction_id )");
			} else {
				if (objSearchDao.getGlobalCaseloadId() != null) {
					final String strCommCaseload = trustCommunityCsld(objSearchDao.getGlobalCaseloadId());
					sqlQuery.append(
							" person_id in (select ob.person_id  from offender_beneficiaries ob, offender_deductions od  where ob.offender_deduction_id = od.offender_deduction_id "
									+ "  and od.caseload_id = :caseloadId )");
					if (strCommCaseload != null) {
						valuesList.addValue("caseloadId", strCommCaseload);
					} else {
						valuesList.addValue("caseloadId", objSearchDao.getGlobalCaseloadId());
					}
				}
			}

			preparedSql = sqlQuery.toString().trim();
			if (objSearchDao.getLastName() != null) {
				preSqlQuery = preparedSql.concat(" ORDER BY FIRST_NAME ");
			} else {
				preSqlQuery = preparedSql.concat(" ORDER BY PERSON_ID ");
			}
			lstPersons = (List<Persons>) namedParameterJdbcTemplate.query(preSqlQuery, valuesList, personsRM);
		}
		return lstPersons;
	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstPersons
	 *            List<Persons>
	 *
	 */
	public Integer perUpdatePersons(final List<Persons> lstPersons) {
		final String sql = getQuery("OCIPBENE_PER_UPDATE_PERSONS");
		int returnValue = 0;
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final Persons persons : lstPersons) {
			parameters.add(new BeanPropertySqlParameterSource(persons));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstPersons.size() == returnArray.length) {
			returnValue = 1;
		}
		return returnValue;

	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            OffenderBeneficiaries
	 *
	 * @return List<OffenderBeneficiaries>
	 *
	 */
	public List<OffenderBeneficiaries> offBncExecuteQuery(final OffenderBeneficiaries objSearchDao) {
		final String sql = getQuery("OCIPBENE_OFFBNC_FIND_OFFENDER_BENEFICIARIES");
		final RowMapper<OffenderBeneficiaries> offBenRM = Row2BeanRowMapper.makeMapping(sql,
				OffenderBeneficiaries.class, offenderBeneficiariesMapping);
		List<OffenderBeneficiaries> returnList = new ArrayList<>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams("PERSONID", objSearchDao.getPersonId()),
					offBenRM);
		} catch (Exception e) {
			return returnList;
		}
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
	 */
	public List<SystemProfiles> sysPflExecuteQuery(final SystemProfiles objSearchDao) {
		final String sql = getQuery("OCIPBENE_SYSPFL_FIND_SYSTEM_PROFILES");
		final RowMapper<SystemProfiles> sysProfRM = Row2BeanRowMapper.makeMapping(sql, SystemProfiles.class,
				systemProfilesMapping);
		String preparedSql = null;
		final StringBuffer sqlQuery = new StringBuffer();
		List<SystemProfiles> returnList = null;
		final MapSqlParameterSource valuesList = new MapSqlParameterSource();
		if (objSearchDao != null) {
			sqlQuery.append(sql);
			sqlQuery.append(" where ");
			if (objSearchDao.getProfileType() != null) {
				sqlQuery.append(" profile_type = :profileType " + " and");
				valuesList.addValue("profileType", objSearchDao.getProfileType());
			}

			if (objSearchDao.getProfileCode() != null) {
				sqlQuery.append(" profile_code = :profilecode " + " and");
				valuesList.addValue("profilecode", objSearchDao.getProfileCode());
			}
			preparedSql = sqlQuery.toString().trim();
			if (preparedSql.endsWith("where")) {
				preparedSql = preparedSql.substring(0, preparedSql.length() - 5);
			}
			if (preparedSql.endsWith("and")) {
				preparedSql = preparedSql.substring(0, preparedSql.length() - 3);
			}
			returnList = (List<SystemProfiles>) namedParameterJdbcTemplate.query(preparedSql, valuesList, sysProfRM);
		}
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * perPostQuery
	 *
	 * @param params
	 *
	 */
	public PersonAddressV perPostQuery(final Persons paramBean) {
		final String sql = getQuery("OCIPBENE_PER_POSTQUERY");
		final RowMapper<PersonAddressV> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, PersonAddressV.class,
				personAddressVMapping);
		final List<PersonAddressV> lstPersons = (List<PersonAddressV>) namedParameterJdbcTemplate.query(sql,
				createParams("PERSONID", paramBean.getPersonId()), columnRowMapper);
		PersonAddressV objPersonAdd = null;
		if (lstPersons != null && lstPersons.size() > 0) {
			objPersonAdd = lstPersons.get(0);
		}
		return objPersonAdd;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfkchkOffBncOffBncOff
	 *
	 * @param params
	 *
	 */
	public OffenderDeductions cgfkchkOffBncOffBncOff(final OffenderBeneficiaries paramBean) {
		final String sql = getQuery("OCIPBENE_CGFKCHK_OFF_BNC_OFF_BNC_OFF_D");
		final RowMapper<OffenderDeductions> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderDeductions.class, offenderDeductionsMapping);
		OffenderDeductions returnObj = new OffenderDeductions();
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("OFFENDERDEDUCTIONID", paramBean.getOffenderDeductionId()), columnRowMapper);
		} catch (Exception e) {
			return returnObj;
		}
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
	public Offenders offenderDetailsExecuteQuery(final OffenderDeductions paramBean) {
		Offenders objHeader = null;
		final String sql = getQuery("OCIPBENE_OFFENDER_DETAILS");
		final RowMapper<Offenders> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, Offenders.class, headerMapping);
		final List<Offenders> returnList = (List<Offenders>) namedParameterJdbcTemplate.query(sql,
				createParams("OFFENDERID", paramBean.getOffenderId()), columnRowMapper);
		if (returnList != null && returnList.size() > 0) {
			objHeader = returnList.get(0);
		}
		return objHeader;
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
		final String sql = getQuery("OCIPBENE_CGWHEN_NEW_FORM_INSTANCE");
		final RowMapper<SysDual> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, SysDual.class, sysDualMapping);
		List<SysDual> returnList = new ArrayList<>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams(), columnRowMapper);
		} catch (Exception e) {
			return returnList;
		}
		return returnList;
	}

	/**
	 * 
	 * @param caseloadId
	 * @return String
	 */
	public String trustCommunityCsld(final String caseloadId) {
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[5];
		sqlParameters = new SqlParameter[] { new SqlParameter("P_CSLD_ID", OracleTypes.VARCHAR), };
		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("TRUST").withFunctionName("TRUST_COMMUNITY_CSLD").declareParameters(sqlParameters);
		inParamMap.put("P_CSLD_ID", caseloadId);
		final SqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		final String value = simpleJDBCCall.executeFunction(String.class, inParameter);
		return value;

	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            Persons
	 *
	 * @return List<BankChequeBeneficiaries>
	 *
	 */
	public List<BankChequeBeneficiaries> bankChequeBeneficiariesExecuteQuery(final Persons objSearchDao) {
		final String sql = getQuery("OCIPBENE_BANK_CHEQUE_BENEFICIARIES_TXN_ID");
		final RowMapper<BankChequeBeneficiaries> offBenRM = Row2BeanRowMapper.makeMapping(sql,
				BankChequeBeneficiaries.class, bankChequeBenMapping);
		List<BankChequeBeneficiaries> returnList = new ArrayList<>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams("PERSONID", objSearchDao.getPersonId()),
					offBenRM);
		} catch (Exception e) {
			return null;
		}
		return returnList;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            Persons
	 *
	 * @return List<BankChequeBeneficiaries>
	 *
	 */
	public BigDecimal bankChequeBeneficiariesCheckAmount(final BigDecimal objSearchDao) {
		final String sql = getQuery("OCIPBENE_BANK_CHEQUE_BENEFICIARIES_CHEQUE_AMOUNT");
		BigDecimal returnList = null;
		try {
			returnList = namedParameterJdbcTemplate.queryForObject(sql, createParams("chequeTxnId", objSearchDao),
					BigDecimal.class);
		} catch (Exception e) {
			return returnList;
		}
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * offenderCreditPriorPayments
	 *
	 * @param params
	 *
	 */
	public BigDecimal offenderCreditPriorPayments(final Persons paramBean) {
		final String sql = getQuery("OCIPBENE_OFFENDER_CREDIT_PRIOR_PAYMENTS");
		BigDecimal paymentAmount = null;
		try {
			paymentAmount = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("personId", paramBean.getPersonId()), BigDecimal.class);
		} catch (Exception e) {
			return paymentAmount;
		}
		return paymentAmount;

	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * montlyAmountRecieved
	 *
	 * @param params
	 *
	 */
	public BigDecimal montlyAmountRecieved(final BigDecimal offDeductionId, final BigDecimal personId) {
		final String sql = getQuery("OCIPBENE_OFFENDER_MON_BENEFICIARIES_PAYMENTS");
		BigDecimal paymentAmount = null;
		try {
			paymentAmount = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("offenderDeductionId", offDeductionId, "personId", personId), BigDecimal.class);
		} catch (Exception e) {
			return paymentAmount;
		}
		return paymentAmount;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * recursiveAmountRecieved
	 *
	 * @param params
	 *
	 */
	public BigDecimal recursiveAmountRecieved(final BigDecimal offDeductionId) {
		final String sql = getQuery("OCIPBENE_OFFENDER_REC_BENEFICIARIES_PAYMENTS");
		BigDecimal paymentAmount = null;
		try {
			paymentAmount = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("offenderDeductionId", offDeductionId), BigDecimal.class);
		} catch (Exception e) {
			return paymentAmount;
		}
		return paymentAmount;
	}

	@Override
	public Map<String, Object> calculateBeneficiaries(Long personId) {
		String procedureQuery = "{CALL OMS_OWNER.CALCULATE_BENEFICIARIES(:p_corp_id, :p_person_id, :p_totl_amt, :p_totl_paid, :p_totl_owing)}";
		Map<String, Object> returnMapData = new HashMap<>();
		try(Connection connection = dataSource.getConnection()) {
			CallableStatement callableStatement = connection.prepareCall(procedureQuery);
			callableStatement.setBigDecimal("p_corp_id", null);
			callableStatement.setLong("p_person_id", personId);
			callableStatement.registerOutParameter("p_totl_amt", Types.VARCHAR);
			callableStatement.registerOutParameter("p_totl_paid", Types.VARCHAR);
			callableStatement.registerOutParameter("p_totl_owing", Types.VARCHAR);
			callableStatement.execute();
			returnMapData.put("p_totl_amt", callableStatement.getString("p_totl_amt"));
			returnMapData.put("p_totl_paid", callableStatement.getString("p_totl_paid"));
			returnMapData.put("p_totl_owing", callableStatement.getString("p_totl_owing"));
		} catch (Exception e) {
			logger.error("calculateBeneficiaries", e);
		}
		return returnMapData;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            Persons
	 *
	 * @return List<BankChequeBeneficiaries>
	 *
	 */
	public OffenderBeneficiaries procedureQueryOne(final Long personId) {
		final String sql = getQuery("OCIPBENE_PROCEDURE_QUERY_ONE");
		final RowMapper<OffenderBeneficiaries> offBenRM = Row2BeanRowMapper.makeMapping(sql,
				OffenderBeneficiaries.class, bankChequeBenMapping);
		OffenderBeneficiaries returnList = new OffenderBeneficiaries();
		try {
			returnList = namedParameterJdbcTemplate.queryForObject(sql, createParams("PERSONID", personId), offBenRM);
		} catch (Exception e) {
			return null;
		}
		return returnList;
	}

	public BigDecimal procedureQueryTwo(final Long personId) {
		final String sql = getQuery("OCIPBENE_PROCEDURE_QUERY_TWO");
		BigDecimal paymentAmount = null;
		try {
			paymentAmount = namedParameterJdbcTemplate.queryForObject(sql, createParams("PERSONID", personId),
					BigDecimal.class);
		} catch (Exception e) {
			return paymentAmount;
		}
		return paymentAmount;
	}

	public BigDecimal procedureQueryThree(final Long personId) {
		final String sql = getQuery("OCIPBENE_PROCEDURE_QUERY_THREE");
		BigDecimal paymentAmount = null;
		try {
			paymentAmount = namedParameterJdbcTemplate.queryForObject(sql, createParams("PERSONID", personId),
					BigDecimal.class);
		} catch (Exception e) {
			return paymentAmount;
		}
		return paymentAmount;
	}
}

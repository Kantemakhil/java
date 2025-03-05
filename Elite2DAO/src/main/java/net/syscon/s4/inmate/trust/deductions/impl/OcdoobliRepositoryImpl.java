package net.syscon.s4.inmate.trust.deductions.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.beans.TransactionTypes;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.CaseloadDeductionDetails;
import net.syscon.s4.im.beans.Corporates;
import net.syscon.s4.im.beans.GroupedObligations;
import net.syscon.s4.im.beans.ObligationGroups;
import net.syscon.s4.im.beans.OffenderCases;
import net.syscon.s4.im.beans.OffenderDeductionReceipts;
import net.syscon.s4.inmate.beans.CaseloadDeductionProfiles;
import net.syscon.s4.inmate.beans.DeductionTypes;
import net.syscon.s4.inmate.beans.OffenderBeneficiaries;
import net.syscon.s4.inmate.beans.OffenderDeductions;
import net.syscon.s4.inmate.trust.deductions.OcdoobliRepository;
import net.syscon.s4.inst.booking.beans.Persons;

/**
 * Class OcdoobliRepositoryImpl
 */
@Repository
public class OcdoobliRepositoryImpl extends RepositoryBase implements OcdoobliRepository {

	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OcdoobliRepositoryImpl.class.getName());

	private final Map<String, FieldMapper> mmMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CASE_INFO_NUMBER", 				new FieldMapper("caseInfoNumber"))
			.put("CASE_ID", 						new FieldMapper(" caseId "))
			.build();
	private final Map<String, FieldMapper> transactionTypesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DESCRIPTION", 					new FieldMapper("description"))
			.put("RECEIPT_TXN_TYPE", 				new FieldMapper("receiptTxnType"))
			.build();
	private final Map<String, FieldMapper> offenderDeductionsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("SEAL_FLAG", 						new FieldMapper("sealFlag"))
			.put("CREATE_DATETIME", 				new FieldMapper("createDateTime"))
			.put("MODIFY_DATETIME", 				new FieldMapper("modifyDateTime"))
			.put("ROOT_OFFENDER_ID", 				new FieldMapper("rootOffenderId"))
			.put("OFFENDER_DEDUCTION_ID", 			new FieldMapper("offenderDeductionId"))
			.put("MAX_RECURSIVE_AMOUNT", 			new FieldMapper("maxRecursiveAmount"))
			.put("EFFECTIVE_DATE", 					new FieldMapper("effectiveDate"))
			.put("DEDUCTION_AMOUNT", 				new FieldMapper("deductionAmount"))
			.build();
	private final Map<String, FieldMapper> offenderBeneficiariesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("AMOUNT", 							new FieldMapper("amount"))
			.put("RECEIVED_AMOUNT", 				new FieldMapper("receivedAmount"))
			.put("SEAL_FLAG", 						new FieldMapper("sealFlag"))
			.put("CREATE_DATETIME", 				new FieldMapper("createDateTime"))
			.put("MODIFY_DATETIME", 				new FieldMapper("modifyDateTime"))
			.put("BENEFICIARY_ID", 					new FieldMapper("beneficiaryId"))
			.put("CORPORATE_ID", 					new FieldMapper("corporateId"))
			.put("OFFENDER_DEDUCTION_ID", 			new FieldMapper("offenderDeductionId"))
			.put("PRIORITY", 						new FieldMapper("priority"))
			.put("TOT_AMT", 						new FieldMapper("totalAmount"))
			.put("PERSON_ID", 						new FieldMapper("personId"))
			.put("PERCENT", 						new FieldMapper("percent"))
			.put("MAINTAINCE_FLAG", 				new FieldMapper("maintainceFlag"))
			.put("AMT", 							new FieldMapper("amount"))
			
			.build();
	private final Map<String, FieldMapper> caseloadDeductionDetailsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("EFFECTIVE_DATE", 					new FieldMapper("effectiveDate"))
			.put("DEDUCTION_TYPE", 					new FieldMapper("deductionType"))
			.put("MAX_TOTAL_AMOUNT", 				new FieldMapper("maxTotalAmount"))
			.put("MAX_RECURSIVE_AMOUNT", 			new FieldMapper("maxRecursiveAmount"))
			.put("MAX_MONTHLY_AMOUNT", 				new FieldMapper("maxMonthlyAmount"))
			.put("FIFO_FLAG",						new FieldMapper("fifoFlag"))
			.put("PAYEE_PERSON_ID", 				new FieldMapper("payeePersonId"))
			.put("PAYEE_CORPORATE_ID", 				new FieldMapper("payeeCorporateId"))
			.put("MODE", 							new FieldMapper("mode"))
			.put("FO_AL_ALL_OFFENDER_FLAG", 		new FieldMapper("foAlAllOffenderFlag"))
			.put("CASELOAD_ID", 					new FieldMapper("caseloadId"))
			.put("TRUST_CASELOAD_ID", 				new FieldMapper("trustCaseloadId"))
			.put("RECEIPT_TXN_TYPE", 				new FieldMapper("receiptTxnType"))
			.put("PERCENTAGE", 						new FieldMapper("percentage"))
			.put("FLAT_RATE", 						new FieldMapper("flatRate"))			
			.build();
	private final Map<String, FieldMapper> offenderDeductionReceiptsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("SEAL_FLAG", 						new FieldMapper("sealFlag"))
			.put("CREATE_DATETIME", 				new FieldMapper("createDateTime"))
			.put("MODIFY_DATETIME", 				new FieldMapper("modifyDateTime"))
			.put("ROOT_OFFENDER_ID", 				new FieldMapper("rootOffenderId"))
			.put("OFFENDER_DEDUCTION_ID", 			new FieldMapper("offenderDeductionId"))
			.put("DEDUCTION_TYPE", 					new FieldMapper("deductionType"))
			.put("PERCENTAGE_OF_PARENT", 			new FieldMapper("percentageOfParent"))
			.put("MAINTENANCE_FLAG", 				new FieldMapper("maintenanceFlag"))
			         
			
			.build();
	private final Map<String, FieldMapper> mMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DEDUCTION_TYPE", 					new FieldMapper("deductionType"))
			.put("CORPORATE_NAME", 					new FieldMapper("corporateName"))
			.put("CODE", 							new FieldMapper("code"))
			.put("FROM_BALANCE_TYPE", 				new FieldMapper("fromBalanceType"))
			.put("CORPORATE_ID", 					new FieldMapper("corporateId"))
			.put("DESCRIPTION", 					new FieldMapper("description"))
			.put("PERSON_ID", 						new FieldMapper("personId"))
			.put("DEDUCTION_DESC", 					new FieldMapper("deductionDesc"))
			.put("LAST_NAME", 						new FieldMapper("lastName"))
			.put("TXN_TYPE", 						new FieldMapper("txnType"))
			.put("DEDUCTION_CATEGORY", 				new FieldMapper("deductionCategory"))	
			.put("GROUP_CODE", 						new FieldMapper("groupCode"))
			.build();
	private final Map<String, FieldMapper> personsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("SUSPENDED_FLAG", 					new FieldMapper("suspendedFlag"))
			.put("PERSON_ID", 						new FieldMapper("personId"))
			.put("LAST_NAME", 						new FieldMapper("lastName"))
			.put("FIRST_NAME", 						new FieldMapper("firstName"))
			.build();
	private final Map<String, FieldMapper> referenceCodesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CODE", 							new FieldMapper("code"))
			.put("DESCRIPTION", 					new FieldMapper("description"))
			.put("DSP_DESCRIPTION", 				new FieldMapper("dspDescription"))
			.put("MODE", 							new FieldMapper("mode"))
			.put("DEDUCTION_STATUS", 				new FieldMapper("deductionStatus"))
			.put("ADJUSTMENT_REASON_CODE", 			new FieldMapper("adjustmentReasonCode"))
			.build();
	private final Map<String, FieldMapper> groupedObligationsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DEDUCTION_TYPE", 					new FieldMapper("deductionType"))
			.put("GROUP_ID", 						new FieldMapper("groupId"))
			.build();
	private final Map<String, FieldMapper> offenderCasesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("ROOT_OFFENDER_ID", 				new FieldMapper("rootOffenderId"))
			.put("INFORMATION_NUMBER", 				new FieldMapper("informationNumber"))
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
	private final Map<String, FieldMapper> corporatesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CORPORATE_NAME", 					new FieldMapper("corporateName"))
			.put("SUSPENDED_FLAG", 					new FieldMapper("suspendedFlag"))
			.put("CORPORATE_ID", 					new FieldMapper("corporateId"))
			.build();
	
	
	private final Map<String, FieldMapper> personMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("LAST_NAME", 			new FieldMapper("lastName"))
			.put("FIRST_NAME", 			new FieldMapper("firstName"))
			.put("SUSPENDED_FLAG", 		new FieldMapper("suspendedFlag"))			
			.put("CORPORATE_NAME", 		new FieldMapper("corporateName"))
			.build();
	
	private final Map<String, FieldMapper> deductionTypesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DEDUCTION_DESC", 			new FieldMapper("deductionDesc"))
			.put("FROM_BALANCE_TYPE", 			new FieldMapper("fromBalanceType"))
			.put("DEDUCTION_CATEGORY", 		new FieldMapper("deductionCategory"))			
			.put("INCREMENT_PAYABLES_FLAG", 		new FieldMapper("incrementPayablesFlag"))
			.put("PARENT_DEDUCTION_TYPE", 		new FieldMapper("parentDeductionType"))
			.build();
	

	/**
	 * Creates new OcdoobliRepositoryImpl class Object
	 */
	public OcdoobliRepositoryImpl() {
		// OcdoobliRepositoryImpl
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            OffenderDeductions
	 *
	 * @return List<OffenderDeductions>
	 *
	 * 
	 */
	public List<OffenderDeductions> offDedExecuteQuery(final OffenderDeductions objSearchDao) {
		final String sql = getQuery("OCDOOBLI_OFFDED_FIND_OFFENDER_DEDUCTIONS");
		final RowMapper<OffenderDeductions> OffenderDeductionsRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderDeductions.class, offenderDeductionsMapping);
		List<OffenderDeductions> returnList = new ArrayList<>();
		String preparedSql = null;
		final MapSqlParameterSource inParameterSource = new MapSqlParameterSource();
		final StringBuffer sqlQuery = new StringBuffer();
		sqlQuery.append(sql);
		if (objSearchDao.getOffenderId() != null) {
			sqlQuery.append("OFFENDER_ID = :OFFENDER_ID" + " AND  OFFENDER_DEDUCTIONS.DEDUCTION_TYPE IN (Select DEDUCTION_TYPE FROM deduction_types WHERE DEDUCTION_CATEGORY  ");
			inParameterSource.addValue("OFFENDER_ID", objSearchDao.getOffenderId());
		}
		if ("Y".equals(objSearchDao.getProfilePayplnFlg())) {
			sqlQuery.append("IN ('FXOB','CROB','TAX') AND caseload_code in ('BOTH',(SELECT CASELOAD_TYPE FROM CASELOADS WHERE CASELOAD_ID IN (SELECT WORKING_CASELOAD_ID FROM STAFF_MEMBERS WHERE USER_ID = :userId)))) ORDER BY OFFENDER_DEDUCTION_ID");
		} else {
			sqlQuery.append("IN ('FXOB','CROB') AND caseload_code in ('BOTH',(SELECT CASELOAD_TYPE FROM CASELOADS WHERE CASELOAD_ID IN (SELECT WORKING_CASELOAD_ID FROM STAFF_MEMBERS WHERE USER_ID = :userId)))) ORDER BY OFFENDER_DEDUCTION_ID");
		}
		preparedSql = sqlQuery.toString().trim();
		if (preparedSql.endsWith("WHERE")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 5);
		}
		if (preparedSql.endsWith("AND")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 3);
		}
		inParameterSource.addValue("userId", objSearchDao.getCreateUserId());
		returnList = namedParameterJdbcTemplate.query(preparedSql, inParameterSource,
				OffenderDeductionsRowMapper);
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
	 * @param lstOffenderDeductions
	 *            List<OffenderDeductions>
	 *
	 * @return List<Integer>
	 *
	 * 
	 */
	public Integer offDedInsertOffenderDeductions(final List<OffenderDeductions> lstOffenderDeductions) {
		final String sql = getQuery("OCDOOBLI_OFFDED_INSERT_OFFENDER_DEDUCTIONS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffenderDeductions offenderDeductions : lstOffenderDeductions) {
			parameters.add(new BeanPropertySqlParameterSource(offenderDeductions));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstOffenderDeductions.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
		

	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstOffenderDeductions
	 *            List<OffenderDeductions>
	 *
	 * 
	 */
	public Integer offDedUpdateOffenderDeductions(final List<OffenderDeductions> lstOffenderDeductions) {
		final String sql = getQuery("OCDOOBLI_OFFDED_UPDATE_OFFENDER_DEDUCTIONS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffenderDeductions offenderDeductions : lstOffenderDeductions) {
			parameters.add(new BeanPropertySqlParameterSource(offenderDeductions));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstOffenderDeductions.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to delete records from data base tables based on
	 *
	 * @param lstOffenderDeductions
	 *            List<OffenderDeductions>
	 *
	 * 
	 */
	public Integer offDedDeleteOffenderDeductions(final List<OffenderDeductions> lstOffenderDeductions) {
		final String sql = getQuery("OCDOOBLI_OFFDED_DELETE_OFFENDER_DEDUCTIONS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffenderDeductions offenderDeductions : lstOffenderDeductions) {
			parameters.add(new BeanPropertySqlParameterSource(offenderDeductions));
		}
		try {
			String tableName = "OFFENDER_DEDUCTIONS";
			String whereClause = "OFFENDER_DEDUCTION_ID  = :offenderDeductionId";
			batchUpdatePreDeletedRows(tableName, whereClause , parameters);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method offDedDeleteOffenderDeductions", e);
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstOffenderDeductions.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

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
	public List<OffenderBeneficiaries> offBncExecuteQuery(final OffenderBeneficiaries objSearchDao) {
		final String sql = getQuery("OCDOOBLI_OFFBNC_FIND_OFFENDER_BENEFICIARIES");
		final RowMapper<OffenderBeneficiaries> OffenderBeneficiariesRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderBeneficiaries.class, offenderBeneficiariesMapping);
		List<OffenderBeneficiaries> returnList = new ArrayList<>();
		returnList = namedParameterJdbcTemplate.query(sql,
				createParams("OFFENDERDEDUCTIONID", objSearchDao.getOffenderDeductionId(),"userName",objSearchDao.getCreateUserId()),
				OffenderBeneficiariesRowMapper);
		return returnList;
	}

	/**
	 * This method is used to insert the records in the data base tables based
	 * on
	 *
	 * @param lstOffenderBeneficiaries
	 *            List<OffenderBeneficiaries>
	 *
	 * @return List<Integer>
	 *
	 * 
	 */
	public Integer offBncInsertOffenderBeneficiaries(final List<OffenderBeneficiaries> lstOffenderBeneficiaries) {
		final String sql = getQuery("OCDOOBLI_OFFBNC_INSERT_OFFENDER_BENEFICIARIES");
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
	 * This method is used to update the data base tables based on
	 *
	 * @param lstOffenderBeneficiaries
	 *            List<OffenderBeneficiaries>
	 *
	 * 
	 */
	public Integer offBncUpdateOffenderBeneficiaries(final List<OffenderBeneficiaries> lstOffenderBeneficiaries) {
		final String sql = getQuery("OCDOOBLI_OFFBNC_UPDATE_OFFENDER_BENEFICIARIES");
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
	 * This method is used to delete records from data base tables based on
	 *
	 * @param lstOffenderBeneficiaries
	 *            List<OffenderBeneficiaries>
	 *
	 * 
	 */
	public Integer offBncDeleteOffenderBeneficiaries(final List<OffenderBeneficiaries> lstOffenderBeneficiaries) {
		final String sql = getQuery("OCDOOBLI_OFFBNC_DELETE_OFFENDER_BENEFICIARIES");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffenderBeneficiaries offenderBeneficiaries : lstOffenderBeneficiaries) {
			parameters.add(new BeanPropertySqlParameterSource(offenderBeneficiaries));
		}
		try {
			String tableName = "OFFENDER_BENEFICIARIES";
			String whereClause = "BENEFICIARY_ID = :beneficiaryId";
			batchUpdatePreDeletedRows(tableName, whereClause , parameters);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method offBncDeleteOffenderBeneficiaries", e);
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
	 *            OffenderDeductionReceipts
	 *
	 * @return List<OffenderDeductionReceipts>
	 *
	 * 
	 */
	public List<OffenderDeductionReceipts> offDrExecuteQuery(final OffenderDeductionReceipts objSearchDao) {
		final String sql = getQuery("OCDOOBLI_OFFDR_FIND_OFFENDER_DEDUCTION_RECEIPTS");
		final RowMapper<OffenderDeductionReceipts> OffenderDeductionReceiptsRowMapper = Row2BeanRowMapper
				.makeMapping(sql, OffenderDeductionReceipts.class, offenderDeductionReceiptsMapping);
		List<OffenderDeductionReceipts> returnList = new ArrayList<>();
		returnList = namedParameterJdbcTemplate.query(sql, createParams("OFFENDERDEDUCTIONID",
				objSearchDao.getOffenderDeductionId(), "OFFENDERID", objSearchDao.getOffenderId(),"userName",objSearchDao.getCreateUserId()),
				OffenderDeductionReceiptsRowMapper);
		return returnList;
	}

	/**
	 * This method is used to insert the records in the data base tables based
	 * on
	 *
	 * @param lstOffenderDeductionReceipts
	 *            List<OffenderDeductionReceipts>
	 *
	 * @return List<Integer>
	 *
	 * 
	 */
	public Integer offDrInsertOffenderDeductionReceipts(
			final List<OffenderDeductionReceipts> lstOffenderDeductionReceipts) {
		final String sql = getQuery("OCDOOBLI_OFFDR_INSERT_OFFENDER_DEDUCTION_RECEIPTS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffenderDeductionReceipts offenderDeductionReceipts : lstOffenderDeductionReceipts) {
			parameters.add(new BeanPropertySqlParameterSource(offenderDeductionReceipts));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstOffenderDeductionReceipts.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstOffenderDeductionReceipts
	 *            List<OffenderDeductionReceipts>
	 *
	 * 
	 */
	public Integer offDrUpdateOffenderDeductionReceipts(
			final List<OffenderDeductionReceipts> lstOffenderDeductionReceipts) {
		final String sql = getQuery("OCDOOBLI_OFFDR_UPDATE_OFFENDER_DEDUCTION_RECEIPTS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffenderDeductionReceipts offenderDeductionReceipts : lstOffenderDeductionReceipts) {
			parameters.add(new BeanPropertySqlParameterSource(offenderDeductionReceipts));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error(e);
		}
		if (lstOffenderDeductionReceipts.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to delete records from data base tables based on
	 *
	 * @param lstOffenderDeductionReceipts
	 *            List<OffenderDeductionReceipts>
	 *
	 * 
	 */
	public Integer offDrDeleteOffenderDeductionReceipts(
			final List<OffenderDeductionReceipts> lstOffenderDeductionReceipts) {
		final String sql = getQuery("OCDOOBLI_OFFDR_DELETE_OFFENDER_DEDUCTION_RECEIPTS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffenderDeductionReceipts offenderDeductionReceipts : lstOffenderDeductionReceipts) {
			parameters.add(new BeanPropertySqlParameterSource(offenderDeductionReceipts));
		}
		try {
			String tableName = "OFFENDER_DEDUCTION_RECEIPTS";
			String whereClause = "OFFENDER_DEDUCTION_ID  = :offenderDeductionId and RECEIPT_TXN_TYPE  = :receiptTxnType";
			batchUpdatePreDeletedRows(tableName, whereClause , parameters);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method offDrDeleteOffenderDeductionReceipts", e);
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstOffenderDeductionReceipts.size() == returnArray.length) {
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
		final String sql = getQuery("OCDOOBLI_SYSPFL_FIND_SYSTEM_PROFILES");
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
		final String sql = getQuery("OCDOOBLI_SYSPFL_INSERT_SYSTEM_PROFILES");
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
	 * This method is used to update the data base tables based on
	 *
	 * @param lstSystemProfiles
	 *            List<SystemProfiles>
	 *
	 * 
	 */
	public Integer sysPflUpdateSystemProfiles(final List<SystemProfiles> lstSystemProfiles) {
		final String sql = getQuery("OCDOOBLI_SYSPFL_UPDATE_SYSTEM_PROFILES");
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
	 * Used to capture results from select query
	 * 
	 * @return List<ReferenceCodes>
	 */
	public List<DeductionTypes> cgfkOffDedDeductionTypeRecordGroup(String user) {
		final String sql = getQuery("OCDOOBLI_FIND_CGFKOFFDEDDEDUCTIONTYPE");
		final RowMapper<DeductionTypes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, DeductionTypes.class,
				mmMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams( "userName", user), mRowMapper);
		} catch (final Exception e) {
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<MM>
	 */
	public List<ObligationGroups> cgfkGroupOblGroupIdRecordGroup(final String deductionType) {
		final String sql = getQuery("OCDOOBLI_FIND_CGFKGROUPOBLGROUPID");
		final RowMapper<ObligationGroups> mMRowMapper = Row2BeanRowMapper.makeMapping(sql, ObligationGroups.class,
				mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams("DEDUCTIONTYPE", deductionType), mMRowMapper);
		} catch (final Exception e) {
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<ReferenceCodes>
	 */
	public List<ReferenceCodes> cgfkOffDedDspDescriptionRecordGroup() {
		final String sql = getQuery("OCDOOBLI_FIND_CGFKOFFDEDDSPDESCRIPTION");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (final Exception e) {
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<ReferenceCodes>
	 */
	public List<ReferenceCodes> cgfkOffDed1AdjustmentReasoRecordGroup() {
		final String sql = getQuery("OCDOOBLI_FIND_CGFKOFFDED1ADJUSTMENTREASO");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (final Exception e) {
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<ReferenceCodes>
	 */
	public List<TransactionTypes> cgfkOffDrReceiptTxnTypeRecordGroup(String user) {
		final String sql = getQuery("OCDOOBLI_FIND_CGFKOFFDRRECEIPTTXNTYPE");
		List<TransactionTypes> returnList =new ArrayList<TransactionTypes>();
		final RowMapper<TransactionTypes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, TransactionTypes.class,
				mMapping);
		
		try {
			returnList= namedParameterJdbcTemplate.query(sql, createParams("userName",user), mRowMapper);
		} catch (final Exception e) {
			logger.error("cgfkOffDrReceiptTxnTypeRecordGroup",e); 
		}
		return returnList;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<MM>
	 */
	public List<OffenderCases> cgfkOffDedCaseInfoNumberRecordGroup() {
		final String sql = getQuery("OCDOOBLI_FIND_CGFKOFFDEDCASEINFONUMBER");
		final RowMapper<OffenderCases> mMRowMapper = Row2BeanRowMapper.makeMapping(sql, OffenderCases.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mMRowMapper);
		} catch (final Exception e) {
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<ReferenceCodes>
	 */
	public List<Persons> cgfkOffBncPersonIdRecordGroup() {
		final String sql = getQuery("OCDOOBLI_FIND_CGFKOFFBNCPERSONID");
		final RowMapper<Persons> mRowMapper = Row2BeanRowMapper.makeMapping(sql, Persons.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (final Exception e) {
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<ReferenceCodes>
	 */
	public List<Corporates> cgfkOffBncCorporateIdRecordGroup() {
		final String sql = getQuery("OCDOOBLI_FIND_CGFKOFFBNCCORPORATEID");
		final RowMapper<Corporates> mRowMapper = Row2BeanRowMapper.makeMapping(sql, Corporates.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (final Exception e) {
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
	public List<OffenderDeductions> offBkgOnCheckDeleteMaster(final OffenderDeductions paramBean) {
		final String sql = getQuery("OCDOOBLI_OFF_BKG_ONCHECKDELETEMASTER");
		final RowMapper<OffenderDeductions> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderDeductions.class, offenderDeductionsMapping);
		final List<OffenderDeductions> returnList =  namedParameterJdbcTemplate
				.query(sql, createParams(), columnRowMapper);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * offDedOnCheckDeleteMaster
	 *
	 * @param params
	 *
	 */
	public List<OffenderBeneficiaries> offDedOnCheckDeleteMaster(final OffenderBeneficiaries paramBean) {
		final String sql = getQuery("OCDOOBLI_OFF_DED_ONCHECKDELETEMASTER");
		final RowMapper<OffenderBeneficiaries> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderBeneficiaries.class, offenderBeneficiariesMapping);
		final List<OffenderBeneficiaries> returnList =  namedParameterJdbcTemplate
				.query(sql, createParams(), columnRowMapper);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * offDedOnCheckDeleteMaster
	 *
	 * @param params
	 *
	 */
	public OffenderDeductionReceipts offDedOnCheckDeleteMaster(final OffenderDeductionReceipts paramBean) {
		final String sql = getQuery("OCDOOBLI_OFF_DED_ONCHECKDELETEMASTER");
		final RowMapper<OffenderDeductionReceipts> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderDeductionReceipts.class, offenderDeductionReceiptsMapping);
		final OffenderDeductionReceipts returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(),
				columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfkchkOffDedOffDedCsld
	 *
	 * @param params
	 *
	 */
	public CaseloadDeductionProfiles cgfkchkOffDedOffDedCsld(final String deductinType,final String user) {
		final String sql = getQuery("OCDOOBLI_CGFKCHK_OFF_DED_OFF_DED_CSLD");
		final RowMapper<CaseloadDeductionProfiles> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				CaseloadDeductionProfiles.class, caseloadDeductionDetailsMapping);
		CaseloadDeductionProfiles returnObj = new CaseloadDeductionProfiles();
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams("DEDUCTIOTYPE", deductinType,"userName",user),
					columnRowMapper);
		} catch (final Exception e) {
			return returnObj;
		}
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfkchkOffDedOffDedRef
	 *
	 * @param params
	 *
	 */
	public List<ReferenceCodes> cgfkchkOffDedOffDedRef() {
		final String sql = getQuery("OCDOOBLI_CGFKCHK_OFF_DED_OFF_DED_REF_C");
		final RowMapper<ReferenceCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				referenceCodesMapping);
		return namedParameterJdbcTemplate.query(sql, createParams(), columnRowMapper);
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfklkpOffDedOffDedRef
	 *
	 * @param params
	 *
	 */
	public ReferenceCodes cgfklkpOffDedOffDedRef(final ReferenceCodes paramBean) {
		final String sql = getQuery("OCDOOBLI_CGFKLKP_OFF_DED_OFF_DED_REF_C");
		final RowMapper<ReferenceCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				referenceCodesMapping);
		final ReferenceCodes returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
		return returnObj;
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
		final String sql = getQuery("OCDOOBLI_CGFKCHK_OFF_BNC_OFF_BNC_CORP_");
		final RowMapper<Corporates> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, Corporates.class,
				corporatesMapping);
		final Corporates returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
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
	public Persons cgfkchkOffBncOffBncPer(final Persons paramBean) {
		final String sql = getQuery("OCDOOBLI_CGFKCHK_OFF_BNC_OFF_BNC_PER_F");
		final RowMapper<Persons> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, Persons.class, personsMapping);
		final Persons returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfkchkOffDrOffDrTxnTyp
	 *
	 * @param params
	 *
	 */
	public List<TransactionTypes> cgfkchkOffDrOffDrTxnTyp(final TransactionTypes paramBean) {
		final String sql = getQuery("OCDOOBLI_CGFKCHK_OFF_DR_OFF_DR_TXN_TYP");
		final RowMapper<TransactionTypes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, TransactionTypes.class,
				transactionTypesMapping);
		List<TransactionTypes> returnObj = new ArrayList<>();
		returnObj = namedParameterJdbcTemplate.query(sql, createParams(), columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfkchkOffDed1OffDedRef
	 *
	 * @param params
	 *
	 */
	public ReferenceCodes cgfkchkOffDed1OffDedRef(final ReferenceCodes paramBean) {
		final String sql = getQuery("OCDOOBLI_CGFKCHK_OFF_DED1_OFF_DED_REF_");
		final RowMapper<ReferenceCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				referenceCodesMapping);
		final ReferenceCodes returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfkchkOffDedGrpId
	 *
	 * @param params
	 *
	 */
	public GroupedObligations cgfkchkOffDedGrpId(final GroupedObligations paramBean) {
		final String sql = getQuery("OCDOOBLI_CGFKCHK_OFF_DED_GRP_ID");
		final RowMapper<GroupedObligations> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				GroupedObligations.class, groupedObligationsMapping);
		final GroupedObligations returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfklkpOffDedCaseNumber
	 *
	 * @param params
	 *
	 */
	public Integer cgfklkpOffDedCaseNumber(final Long rootOffenderId, final String informationNumber) {
		final String sql = getQuery("OCDOOBLI_CGFKLKP_OFF_DED_CASE_NUMBER");
		Integer caseId = 0;
		caseId = namedParameterJdbcTemplate.queryForObject(sql, createParams("rootOffenderId", rootOffenderId,"informationNumber",informationNumber),
				Integer.class);
		return caseId;
	}

	
	public String getDesc(final String deductionType) {
		final String sql = getQuery("OCDOOBLI_GET_DESCRIPTION");
		String desc = null;
		try {
			desc = namedParameterJdbcTemplate.queryForObject(sql, createParams("deductionType", deductionType),
					String.class);
		} catch (final Exception e) {

			logger.error("getDesc", e);
		}
		return desc;
	}

	
	public String getUnlFlg(final Long offenderId) {
		final String sql = getQuery("OCDOOBLI_GET_OBLFLAG");
		String unl = null;
		try {
			unl = namedParameterJdbcTemplate.queryForObject(sql, createParams("offenderId", offenderId), String.class);
		} catch (final Exception e) {

			logger.error("getUnlFlg", e);
		}
		return unl;
	}

	
	public OffenderBeneficiaries getFirstlastNamesusflg(final BigDecimal personId) {
		final String sql = getQuery("OCDOOBLI_GET_FIRST_LAST_NAMES_SUSFLG");
		final RowMapper<OffenderBeneficiaries> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderBeneficiaries.class, personMapping);
		OffenderBeneficiaries returnObj = null;
		returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams("personId", personId), columnRowMapper);
		return returnObj;
	}

	
	public OffenderBeneficiaries getCorporateNamesusflg(final BigDecimal corporateId) {
		final String sql = getQuery("OCDOOBLI_GET_CORPORATE_NAME_SUSFLG");
		final RowMapper<OffenderBeneficiaries> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderBeneficiaries.class, personMapping);
		OffenderBeneficiaries returnObj = null;
		returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams("corporateId", corporateId),
				columnRowMapper);
		return returnObj;
	}

	
	public BigDecimal calcBenPercent(final Long offenderDeductionId, final BigDecimal priority) {
		final String sql = getQuery("OCDOOBLI_CALCBENPERCENT");
		BigDecimal priorityTot =BigDecimal.valueOf(0);
		priorityTot = namedParameterJdbcTemplate.queryForObject(sql,
				createParams("offenderDeductionId", offenderDeductionId, "priority", priority), BigDecimal.class);
		return priorityTot;
	}

	
	public String getRecieptTxntypeDesc(final String receiptTxnType) {
		final String sql = getQuery("OCDOOBLI_GETRECIEPTTXNTYPEDESC");
		String desc = null;
		desc = namedParameterJdbcTemplate.queryForObject(sql, createParams("receiptTxnType", receiptTxnType),
				String.class);
		return desc;
	}

	
	public Integer getTCount(final Long offenderId, final String caseloadId, final String deductionType,
			final String deductionPriority) {
		final String sql = getQuery("OCDOOBLI_GETTCOUNT");
		Integer tCount = 0;
		tCount = namedParameterJdbcTemplate.queryForObject(sql, createParams("offenderId", offenderId, "caseloadId",
				caseloadId, "deductionType", deductionType, "deductionPriority", deductionPriority), Integer.class);
		return tCount;
	}

	
	public String checkCrTpe(final String deductionType) {
		final String sql = getQuery("OCDOOBLI_CHECK_CR_TYPE");
		String checkCrtype = null;
		try {
			checkCrtype = namedParameterJdbcTemplate.queryForObject(sql, createParams("deductionType", deductionType),
					String.class);
		} catch (final EmptyResultDataAccessException e) {
			checkCrtype = "EMPTY";
		}
		return checkCrtype;
	}

	
	public Integer setJsCondition(final Integer caseId) {
		final String sql = getQuery("OCDOOBLI_SET_JS_CONDITION");
		Integer count = 0;
		try {
			count = namedParameterJdbcTemplate.queryForObject(sql, createParams("caseId", caseId), Integer.class);
		} catch (final Exception e) {
			logger.error("setJsCondition", e);
		}
		return count;
	}

	
	public Integer omsUtilcomareDateTime(final String effectiveDate, final String dspEffectiveDate) {
		final String sql = getQuery("OCDOOBLI_COMPARE_DATE_TIME");
		Integer count = 0;
		try {
			count = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("effectiveDate", effectiveDate, "dspDate", dspEffectiveDate), Integer.class);
		} catch (final Exception e) {
			logger.error("setJsCondition", e);
		}
		return count;
	}

	
	public String displayErrorMessage() {
		final String sql = getQuery("OCDOOBLI_OMS_UTILS_DISPLAY_USER_MESSAGE");
		String textMsg = null;
		try {
			textMsg = namedParameterJdbcTemplate.queryForObject(sql, createParams(), String.class);
		} catch (final Exception e) {
			logger.error("displayErrorMessage", e);
		}

		return textMsg;
	}

	
	public String profilePlanFlag() {
		final String sql = getQuery("OCDOOBLI_PROFILE_VALUE");
		String textMsg = null;
		try {
			textMsg = namedParameterJdbcTemplate.queryForObject(sql, createParams(), String.class);
		} catch (final Exception e) {
			logger.error("profilePlanFlag", e);
		}

		return textMsg;
	}

	
	public Integer getCheckOne(final Long offenderId,final  String informationNumber, final BigDecimal groupId) {
		final String sql = getQuery("OCDOOBLI_GETCHECKONE");
		Integer checkOne = 0;
		try {
			checkOne = namedParameterJdbcTemplate.queryForObject(sql, createParams("offenderId", offenderId,
					"informationNumber", informationNumber, "groupId", groupId), Integer.class);
		} catch(EmptyResultDataAccessException e) {
			return null;
			
		}
		catch (final Exception e) {
			logger.error("getCheckOne", e);
		}
		return checkOne;
	}

	
	public OffenderDeductions getvsDamtCur(final Integer deductionId) {
		final String sql = getQuery("OCDOOBLI_VS_DAMTCUR");
		OffenderDeductions offded = null;
		final RowMapper<OffenderDeductions> OffenderDeductionsRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderDeductions.class, offenderDeductionsMapping);
		try {
			offded = namedParameterJdbcTemplate.queryForObject(sql, createParams("deductionId", deductionId),
					OffenderDeductionsRowMapper);
		} catch (final Exception e) {
			logger.error("getvsDamtCur", e);

		}

		return offded;
	}

	
	public Integer getvsDamtCurVal(final Integer deductionId) {
		final String sql = getQuery("OCDOOBLI_VS_DAMTCURVAL");
		Integer checkOne = 0;
		try {
			checkOne = namedParameterJdbcTemplate.queryForObject(sql, createParams("deductionId", deductionId),
					Integer.class);
		} catch (final Exception e) {
			logger.error("getvsDamtCurVal", e);
		}
		return checkOne;
	}

	
	public BigDecimal getDeductionAmnt(final Integer deductionId) {
		final String sql = getQuery("OCDOOBLI_GETDEDUCTIONAMNT");
		BigDecimal dedAmnt = BigDecimal.ZERO;
		try {
			dedAmnt = namedParameterJdbcTemplate.queryForObject(sql, createParams("deductionId", deductionId),
					BigDecimal.class);
		}
		catch (EmptyResultDataAccessException e) {
			return dedAmnt = BigDecimal.ZERO;

		}
		catch (Exception e) {
			logger.error("getDeductionAmnt", e);

		}

		return dedAmnt;
	}

	
	public Persons getLastFirstNames(final Long personId) {
		final String sql = getQuery("OCDOOBLI_GETLASTFIRSTNAMES");
		Persons offded = null;
		final RowMapper<Persons> OffenderDeductionsRowMapper = Row2BeanRowMapper.makeMapping(sql, Persons.class,
				mMapping);
//		try {
//			offded =
					return  namedParameterJdbcTemplate.queryForObject(sql, createParams("personId", personId),
					OffenderDeductionsRowMapper);
//		} catch (final Exception e) {
//			logger.error("Person Not Found : ", e);
//
//		}

//		return offded;
	}

	
	public String getLastFirstName(final String lastName, final String firstName) {
		final String sql = getQuery("OCDOOBLI_LASTFIRSTNAME");
		String lastfrst = null;
		try {
			lastfrst = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("lastName", lastName, "firstName", firstName), String.class);
		} catch (final Exception e) {
			logger.error("getvsDamtCurVal", e);
		}
		return lastfrst;
	}

	public Integer getPerExists(final Integer deductionId, final Integer personId) {
		final String sql = getQuery("OCDOOBLI_PER_EXIST");
		Integer count = 0;
		try {
			count = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("deductionId", deductionId, "personId", personId), Integer.class);
		} catch (final Exception e) {
			logger.error("getPerExists", e);
		}
		return count;
	}

	public Integer updateBenficiaryTransactions(final OffenderBeneficiaries updateBean) {
		final String sql = getQuery("OCDOOBLI_UPDATEBENFICIARYTRANSACTIONS");
		Integer count = 0;
		try {
			count = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("personId", updateBean.getPersonId(), "corporateId", updateBean.getCorporateId(),
							"offenderDeductionId", updateBean.getOffenderDeductionId(), "unknownBenId",
							updateBean.getUnknownBenId(),"modifyUserId",updateBean.getModifyUserId()),
					Integer.class);
		} catch (final Exception e) {
			logger.error("getPerExists", e);
		}
		return count;
	}

	
	public Integer getCorpExists(final Integer deductionId, final Integer corporateId) {
		final String sql = getQuery("OCDOOBLI_GETCORPEXISTS");
		Integer count = 0;
		try {
			count = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("deductionId", deductionId, "corporateId", corporateId), Integer.class);
		} catch (final Exception e) {
			logger.error("getCorpExists", e);
		}
		return count;
	}

	
	public List<OffenderDeductionReceipts> getoffdedRecieptList(final Long offenderDeductionId) {
		final String sql = getQuery("OCDOOBLI_GET_OFFENDER_DEDUCTION_ID");
		List<OffenderDeductionReceipts> returnList = null;
		final RowMapper<OffenderDeductionReceipts> rowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderDeductionReceipts.class, offenderDeductionReceiptsMapping);
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams("offenderDeductionId", offenderDeductionId),
					rowMapper);
		} catch (final Exception e) {
			logger.error("getoffdedRecieptList", e);
		}

		return returnList;
	}

	
	public List<OffenderDeductionReceipts> getreciepttxnType(final Long offenderDeductionId, final String receiptTxnType) {
		final String sql = getQuery("OCDOOBLI_GETRECIEPTTXNTYPE");
		List<OffenderDeductionReceipts> returnList = null;
		final RowMapper<OffenderDeductionReceipts> rowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderDeductionReceipts.class, offenderDeductionReceiptsMapping);
		try {
			returnList = namedParameterJdbcTemplate.query(sql,
					createParams("offenderDeductionId", offenderDeductionId, "receiptTxnType", receiptTxnType),
					rowMapper);
		} catch (final Exception e) {
			logger.error("getreciepttxnType", e);
		}

		return returnList;
	}

	
	public List<OffenderDeductionReceipts> getRcptPercent(final String caseloadId, final String deductionType,
			final String receiptTxnType) {
		final String sql = getQuery("OCDOOBLI_GETRECIEPTPERCENTAGE");
		List<OffenderDeductionReceipts> returnList = null;
		final RowMapper<OffenderDeductionReceipts> rowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderDeductionReceipts.class, offenderDeductionReceiptsMapping);
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams("caseloadId", caseloadId, "deductionType",
					deductionType, "receiptTxnType", receiptTxnType), rowMapper);
		} catch (final Exception e) {
			logger.error("getreciepttxnType", e);
		}

		return returnList;
	}

	
	public Integer getMonths(final Date vEffDate) {
		final String sql = getQuery("OCDOOBLI_GETMONTHS");
		Integer count = 0;
		try {
			count = namedParameterJdbcTemplate.queryForObject(sql, createParams("vEffDate", vEffDate), Integer.class);
		} catch (final Exception e) {
			logger.error("getMonths", e);
		}
		return count;
	}

	
	public Long getNxtVal() {
		final String sql = getQuery("OCDOOBLI_GETNXTVAL");
		Long count = null;
		try {
			count = namedParameterJdbcTemplate.queryForObject(sql, createParams(), Long.class);
		} catch (final Exception e) {
			logger.error("getNxtVal", e);
		}
		return count;
	}

	
	public List<OffenderDeductions> checkOneList(final Long offenderId, final String informationNumber, final BigDecimal groupId) {
		final String sql = getQuery("OCDOOBLI_GETCHECKONE");
		List<OffenderDeductions> returnList = null;
		final RowMapper<OffenderDeductions> rowMapper = Row2BeanRowMapper.makeMapping(sql, OffenderDeductions.class,
				offenderDeductionReceiptsMapping);
		try {
			returnList = namedParameterJdbcTemplate.query(sql,
					createParams("offenderId", offenderId, "informationNumber", informationNumber, "groupId", groupId),
					rowMapper);
		} catch (final Exception e) {
			logger.error("checkOneList", e);
		}

		return returnList;
	}

	
	public Integer getUnknownIdNextVal() {
		final String sql = getQuery("OCDOOBLI_GETUNKNOWNIDNEXTVAL");
		Integer unknownId = 0;
		try {
			unknownId = namedParameterJdbcTemplate.queryForObject(sql, createParams(), Integer.class);
		} catch (final Exception e) {
			logger.error("getUnknownIdNextVal", e);
		}
		return unknownId;
	}

	
	public List<OffenderBeneficiaries> postInsert(final Long offenderDeductionId) {
		final String sql = getQuery("OCDOOBLI_GETPRIORITY_TOTAMNT");
		List<OffenderBeneficiaries> returnList = new ArrayList<OffenderBeneficiaries>();
		final RowMapper<OffenderBeneficiaries> rowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderBeneficiaries.class, offenderBeneficiariesMapping);
		try {
			returnList = namedParameterJdbcTemplate.query(sql,
					createParams("offenderDeductionId", offenderDeductionId),rowMapper);
		} catch (final EmptyResultDataAccessException e) {
			return returnList;
			//logger.error("checkOneList", e);
		}

		return returnList;
	}

	
	public List<OffenderBeneficiaries>  benAmnt(final Long offenderDeductionId, final BigDecimal priority) {
		final String sql = getQuery("OCDOOBLI_GETBENAMNT");
		List<OffenderBeneficiaries>  returnData = new ArrayList<OffenderBeneficiaries>();
		final RowMapper<OffenderBeneficiaries> rowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderBeneficiaries.class, offenderBeneficiariesMapping);
		try {
			returnData = namedParameterJdbcTemplate.query(sql,
					createParams("offenderDeductionId", offenderDeductionId, "priority", priority), rowMapper);
		} catch (final EmptyResultDataAccessException e) {
			return returnData;
			// logger.error("benAmnt", e);
		}
		return returnData;
	}

	
	public void updateOffenderBeneficiaries(final Long beneficiaryId, final BigDecimal amount, final BigDecimal totalAmount,final String modifyUserId) {
		final String sql = getQuery("OCDOOBLI_UPDATEOFFENDERBENEFICIARIES");
		try {
			final Integer updateCount = namedParameterJdbcTemplate.update(sql,
					createParams("beneficiaryId", beneficiaryId, "amount", amount, "totalAmount", totalAmount,"modifyUserId",modifyUserId));
		} catch (final Exception e) {
			logger.error("UpdateOffenderBeneficiaries", e);
		}

	}

	
	public void updateElseOffenderBeneficiaries(final Long beneficiaryId,final String modifyUserId) {
		final String sql = getQuery("OCDOOBLI_ELSEUPDATEOFFENDERBENEFICIARIES");
		try {
			final Integer updateCount = namedParameterJdbcTemplate.update(sql, createParams("beneficiaryId", beneficiaryId,"modifyUserId",modifyUserId));
		} catch (final Exception e) {
			logger.error("UpdateOffenderBeneficiaries", e);
		}
	}

	
	public Integer getPercetanage(final Long offenderDeductionId, final BigDecimal priority) {
		final String sql = getQuery("OCDOOBLI_GETPERCETANAGE");
		Integer totpercent = 0;

		try {
			totpercent = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("offenderDeductionId", offenderDeductionId, "priority", priority), Integer.class);
		} catch (final Exception e) {
			logger.error("totpercent", e);
		}
		return totpercent;
	}

	
	public void updatePercentageInBenficiaries(final Integer totPercent, final BigDecimal priority, final Long offenderDeductionId,final String modifyUserId) {
		final String sql = getQuery("OCDOOBLI_TOT_PERCETANAGE");
		try {
			final Integer updateCount = namedParameterJdbcTemplate.update(sql, createParams("totPercent", totPercent,
					"priority", priority, "offenderDeductionId", offenderDeductionId,"modifyUserId",modifyUserId));
		} catch (final Exception e) {
			logger.error("updatePercentageInBenficiaries", e);
		}
	}

	
	public void updateOffenderPayments(final Long offenderId, final BigDecimal maxTotalAmount, final BigDecimal groupId, final String infoNumber,final String modifyUserId) {
		final String sql = getQuery("OCDOOBLI_UPDATEOFFENDERPAYMENTS");
		try {
			final Integer updateCount = namedParameterJdbcTemplate.update(sql,
					createParams("offenderId", offenderId, "maxTotalAmount", maxTotalAmount, "groupId", groupId,"vinfo", infoNumber,"modifyUserId",modifyUserId));
		} catch (final Exception e) {
			logger.error("updateOffenderPayments", e);
		}
	}

	
	public List<OffenderDeductions> getcurChildDeductions(final String deductionType) {
		final String sql = getQuery("OCDOOBLI_GETCURCHILDDEDUCTIONS");
		List<OffenderDeductions> returnList = null;
		final RowMapper<OffenderDeductions> rowMapper = Row2BeanRowMapper.makeMapping(sql, OffenderDeductions.class,
				offenderDeductionReceiptsMapping);
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams("deductionType", deductionType), rowMapper);
		} catch (final Exception e) {
			logger.error("checkOneList", e);
		}

		return returnList;
	}

	
	public List<OffenderDeductions> getcurChildCsldDed(final String deductionType, final String caseloadId) {
		final String sql = getQuery("OCDOOBLI_GETCURCHILDCSLDDED");
		List<OffenderDeductions> returnList = null;
		final RowMapper<OffenderDeductions> rowMapper = Row2BeanRowMapper.makeMapping(sql, OffenderDeductions.class,
				offenderDeductionReceiptsMapping);
		try {
			returnList = namedParameterJdbcTemplate.query(sql,
					createParams("deductionType", deductionType, "caseloadId", caseloadId), rowMapper);
		} catch (final Exception e) {
			logger.error("checkOneList", e);
		}

		return returnList;

	}

	
	public Integer insertIntoOffenderDeductions(final OffenderDeductions obj) {
		final String sql = getQuery("OCDOOBLI_CGNBT_MAXMONTHLY_INSERT_OFFENDER_DEDUCTIONS");
		Integer count = 0;
		count = namedParameterJdbcTemplate.update(sql,
				createParams("offenderDeductionId", obj.getOffenderDeductionId(), "offenderId", obj.getOffenderId(),
						"deductionType", obj.getDeductionType(), "infomationNumber", obj.getInformationNumber(),
						"caseloadId", obj.getCaseloadId(), "odp", obj.getDeductionPriority(), "maxTotalAmnt",
						obj.getMaxTotalAmount(), "maxMonthlyAmnt", obj.getMaxMonthlyAmount(), "maxRecursiveAmnt",
						obj.getMaxRecursiveAmount(), "fifoFlag", obj.getFifoFlag(), "groupId", obj.getGroupId(),
						"caseId", obj.getCaseId(), "offenderDeductionId", obj.getOffenderDeductionId(),"percentage_of_parent",obj.getPercentageOfParent(),"current_user",obj.getCreateUserId()));
		return count;
	}

	
	public Integer insertOffenderBenficiaries(final OffenderDeductions obj) {
		final String sql = getQuery("OCDOOBLI_CGNBT_MAXMONTHLY_INSERT_OFFENDER_BENEFICIARIES");
		Integer count = 0;
		Long benId = getBenfiNextId();
		Integer unknownBenId = getUnknownIdNextVal();
		try {
			count = namedParameterJdbcTemplate.update(sql,
					createParams("beneficiary_id",benId,"offenderDeductionId", obj.getOffenderDeductionId(),"offenderId", obj.getOffenderId(),
							"personId", obj.getPayeePersonId(), "corporateId", obj.getPayeeCorporateId(), "priority",
							obj.getDeductionPriority(), "maxTotalAmnt", obj.getMaxTotalAmount(), "maxMonthlyAmnt",
							obj.getMaxMonthlyAmount(), "maxRecursiveAmnt", obj.getMaxRecursiveAmount(),
							"percentageOfParent", obj.getPercentageOfParent(), "caseloadId", obj.getCaseloadId(),
							"deductionType", obj.getDeductionType(),"unknown_ben_id",unknownBenId,"createUserId",obj.getCreateUserId()));
		} catch (final Exception e) {
			final String sqlname = getQuery("OCDOOBLI_SQLNOTFOUND_INSERT_OFFENDER_BENEFICIARIES");
			count = namedParameterJdbcTemplate.update(sqlname,
					createParams("beneficiary_id",benId,"offenderDeductionId", obj.getOffenderDeductionId(), "offenderId", obj.getOffenderId(),
							"maxTotalAmnt", obj.getMaxTotalAmount(), "maxMonthlyAmnt", obj.getMaxMonthlyAmount(),
							"maxRecursiveAmnt", obj.getMaxRecursiveAmount(), "percentageOfParent",
							obj.getPercentageOfParent(),"caseloadId", obj.getCaseloadId(),"unknown_ben_id",unknownBenId,"createUserId",obj.getCreateUserId()));
		}
		return count;
	}

	
	public Integer insertIntoOffenderDeductionsReciepts(final OffenderDeductions obj) {
		final String sqlname = getQuery("OCDOOBLI_INSERTINTOOFFENDERDEDUCTIONSRECIEPTS");
		Integer count = 0;
		try {
			count = namedParameterJdbcTemplate.update(sqlname,
					createParams("offenderDeductionId", obj.getOffenderDeductionId(),"caseloadId", obj.getCaseloadId(),
							"deductionType", obj.getDeductionType(),"createUserId",obj.getCreateUserId()));
		} catch (final Exception e) {
			logger.error("insertIntoOffenderDeductionsReciepts", e);
		}

		return count;
	}

	
	public Long getBenfiNextId() {
		final String sqlname = getQuery("OCDOOBLI_OFF_BNC_PREINSERT");
		Long benId = null;
		try {
			benId = namedParameterJdbcTemplate.queryForObject(sqlname,	createParams(),Long.class);
		} catch (final Exception e) {
			logger.error("getBenfiNextId", e);
		}

		return benId;
	}

	
	public Integer checkBenficiaryInserted(final Long offenderDeductionId) {
		final String sql = getQuery("OCDOOBLI_V_DUP_CHECKBENFICIARYINSERTED");
		Integer count = null;
		try {
			count = namedParameterJdbcTemplate.queryForObject(sql, createParams("offenderDeductionId", offenderDeductionId),
					Integer.class);
		} catch (final Exception e) {

			logger.error("checkBenficiaryInserted", e);
		}
		return count;
	}

	
	public Integer insertMultipleBeneficiaries(final OffenderBeneficiaries objBeaninsert) {
		final String sqlname = getQuery("OCDOOBLI_INSERTMULTIPLEBENEFICIARIES");
		Integer count = 0;
		Long benId = getBenfiNextId();
		try {
			count = namedParameterJdbcTemplate.update(sqlname,
					createParams("offenderDeductionId", objBeaninsert.getOffenderDeductionId(),
							"beneficiary_id",benId,
							"offenderId",objBeaninsert.getOffenderId(),
							"unknownBenId",objBeaninsert.getUnknownBenId(), "dedAmt",objBeaninsert.getAmount(),
							"personId",objBeaninsert.getPersonId(),"corporateId",objBeaninsert.getCorporateId(),"createUserId",objBeaninsert.getCreateUserId()));
		} catch (final Exception e) {
			logger.error("insertMultipleBeneficiaries", e);
		}

		return count;
	}

	
	public Integer getvClientWash() {
		final String sql = getQuery("OCDOOBLI_GETVCLIENTWASH");
		Integer count = 0;
		try {
			count = namedParameterJdbcTemplate.queryForObject(sql,
					createParams(),Integer.class);
		} catch (final Exception e) {
			logger.error("getvClientWash", e);
		}

		return count;
	}

	
	public List<OffenderBeneficiaries> getMultiBeanData(final OffenderDeductions objBeaninsertBean) {
		final String sql = getQuery("OCDOOBLI_MULTIBEAN");
		List<OffenderBeneficiaries> returnData = new ArrayList<OffenderBeneficiaries>();
		final RowMapper<OffenderBeneficiaries> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderBeneficiaries.class, offenderBeneficiariesMapping);
		try {
			returnData = namedParameterJdbcTemplate.query(sql,createParams("caseloadId",objBeaninsertBean.getCaseloadId(),
					"deductionType",objBeaninsertBean.getDeductionType()),columnRowMapper);
		} catch (final EmptyResultDataAccessException  e) {
			returnData =null;
			logger.error("getMultiBeanData", e);
		}

		return returnData;
	}

	
	public Integer getLvCount(final String caseloadId, final String deductionType) {
		final String sql = getQuery("OCDOOBLI_GET_LV_COUNT");
		Integer count = 0;
		try {
			count = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("caseloadId",caseloadId,"deductionType",deductionType),Integer.class);
		} catch (final Exception e) {
			logger.error("getLvCount", e);
		}

		return count;
	}
	
	public Integer getLvFlatRate(final String caseloadId, final String deductionType) {
		final String sql = getQuery("OCDOOBLI_GETLV_FLATRATE");
		Integer count = 0;
		try {
			count = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("caseloadId",caseloadId,"deductionType",deductionType),Integer.class);
		} catch (final Exception e) {
			logger.error("getLvCount", e);
		}

		return count;
	}// 
	
	
	public Integer getNonLvFlatRate(final String caseloadId, final String deductionType) {
		final String sql = getQuery("OCDOOBLI_GET_LV_NON_FLATRATE");
		Integer count = 0;
		try {
			count = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("caseloadId",caseloadId,"deductionType",deductionType),Integer.class);
		} catch (final Exception e) {
			logger.error("getLvCount", e);
		}

		return count;
	}

	
	public Integer insertIntoOffenderBenficiaries(final OffenderBeneficiaries objBeaninsertBean) {
		final String sql = getQuery("OCDOOBLI_INSERTINTO_OFFENDERBENFICIARIES");
		Integer count = 0;
		Long benId = getBenfiNextId();
		
		if(objBeaninsertBean!= null && objBeaninsertBean.getPersonId() != null) {
			objBeaninsertBean.setUnknownBenId(objBeaninsertBean.getPersonId());
		} else if(objBeaninsertBean!= null && objBeaninsertBean.getCorporateId() != null){
			objBeaninsertBean.setUnknownBenId(objBeaninsertBean.getCorporateId());
		} else {
			objBeaninsertBean.setUnknownBenId(BigDecimal.valueOf(getUnknownIdNextVal()));
		}
	
		try {
			count = namedParameterJdbcTemplate.update(sql,
					createParams("beneficiaryId", objBeaninsertBean.getBeneficiaryId(),
							"offenderDeductionId", objBeaninsertBean.getOffenderDeductionId(),
							"offenderId", objBeaninsertBean.getOffenderId(),
							"personId", objBeaninsertBean.getPersonId(),
							"corporateId", objBeaninsertBean.getCorporateId(),
							"priority", objBeaninsertBean.getPriority(),
							"unknownBenId", objBeaninsertBean.getUnknownBenId(),
							"vRectifiedAmnt", objBeaninsertBean.getAmount(),
							"percent", objBeaninsertBean.getDspPercent(),
							"createUserId", objBeaninsertBean.getCreateUserId()));
		} catch (final Exception e) {
			logger.error("getLvCount", e);
		}

		return count;
	}

	
	public Integer checkReceiptTypeInserted(final Long offenderDeductionId) {
		final String sql = getQuery("OCDOOBLI_CHK_RECEIPT_TYPE_INSERTED");
		Integer count = 0;
		try {
			count = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("offenderDeductionId",offenderDeductionId),Integer.class);
		} catch (final Exception e) {
			logger.error("checkReceiptTypeInserted", e);
		}

		return count;
	}
	
	public List<CaseloadDeductionDetails> getDeductionReciepts(final OffenderDeductions objBeaninsertBean) {
		final String sql = getQuery("OCDOOBLI_GETDEDUCTION_RECIEPTS");
		List<CaseloadDeductionDetails> returnData = null;
		final RowMapper<CaseloadDeductionDetails> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				CaseloadDeductionDetails.class, caseloadDeductionDetailsMapping);
		try {
			returnData = namedParameterJdbcTemplate.query(sql, 
					createParams("caseloadId",objBeaninsertBean.getCaseloadId(),"dedcutionType",objBeaninsertBean.getDeductionType()),columnRowMapper);
		} catch (final Exception e) {
			logger.error("getDeductionReciepts", e);
		}

		return returnData;
	}

	
	public String getExistFlg(final Long offenderDeductionId, final String receiptTxnType) {
		final String sql = getQuery("OCDOOBLI_GET_EXISTFLG");
		String  existFlg = null;
		try {
			existFlg = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("offenderDeductionId",offenderDeductionId,"receiptTxnType",receiptTxnType),String.class);
		} catch (final EmptyResultDataAccessException e) {
			existFlg ="N";
		}

		return existFlg;
	}

	
	public Integer insertIntoOffenderDeductionreceipts(final OffenderDeductionReceipts cslodDedBean) {
		final String sql = getQuery("OCDOOBLI_INSERTINTOOFFENDER_DEDUCTIONRECEIPTS");
		Integer count = 0;
		try {
			count = namedParameterJdbcTemplate.update(sql,
					createParams("offenderDeductionId",cslodDedBean.getOffenderDeductionId(),
							"receiptTxnType",cslodDedBean.getReceiptTxnType(),
							"percentage",cslodDedBean.getPercentage(),
							"flatRate",cslodDedBean.getFlatRate(),"createUserId",cslodDedBean.getCreateUserId()));
		} catch (final Exception e) {
			logger.error("getLvCount", e);
		}

		return count;
	}

	
	public Integer updateOffenders(final String oblFlg,final String modifyUserId,final Long rootOffenderId) {
		final String sql = getQuery("OCDOOBLI_UPDATEOFFENDERS");
		Integer count = 0;
		try {
			count = namedParameterJdbcTemplate.update(sql,
					createParams("oblFlg",oblFlg,"modifyUserId",modifyUserId,"rootOffenderId",rootOffenderId));
		} catch (final Exception e) {
			logger.error("updateOffenders", e);
		}

		return count;
	}

	
	public String getDescriptionforReciptType(final String code) {
		final String sql = getQuery("OCDOOBLI_GETDESCRIPTIONFORRECIPTTYPE");
		String desc = null;
		try {
			desc = namedParameterJdbcTemplate.queryForObject(sql,createParams("code",code),String.class);
		} catch (final Exception e) {
			logger.error("getDescriptionforReciptType", e);
		}

		return desc;
	}

	
	public List<OffenderDeductions> getcurChildOffDed(final Long offenderDeductionId) {
		final String sql = getQuery("OCDOOBLI_CUR_CHILD_OFF_DED");
		List<OffenderDeductions> returnList = null;
		final RowMapper<OffenderDeductions> rowMapper =Row2BeanRowMapper.makeMapping(sql,
				OffenderDeductions.class, offenderDeductionsMapping);
		try {
			returnList = namedParameterJdbcTemplate.query(sql,createParams("offenderDeductionId",offenderDeductionId),rowMapper);
		} catch (final Exception e) {
			logger.error("getcurChildOffDed", e);
		}

		return returnList;
	}

	
	public void updateOffenderDeductions(final BigDecimal deductionPriority, final String informationNumber, final BigDecimal caseId,
			final BigDecimal groupId, final String deductionStatus, final BigDecimal maxTotalAmount, final String percentageOfParent,
			final BigDecimal deductionAmount, final BigDecimal maxMonthlyAmount, final BigDecimal maxRecursiveAmount,
			final Long offenderDeductionId,final String modifyUserId) {
		final String sql = getQuery("OCDOOBLI_UPDATEOFFENDERDEDUCTIONS");
		Integer count = 0;
		try {
			count = namedParameterJdbcTemplate.update(sql,
					createParams("deductionPriority",deductionPriority,"informationNumber",informationNumber,
							"caseId",caseId,"groupId",groupId,"deductionStatus",deductionStatus,"maxTotalAmount", maxTotalAmount, "percentageOfParent", percentageOfParent,
							"deductionAmount", deductionAmount, "maxRecursiveAmount", maxRecursiveAmount,"offenderDeductionId",offenderDeductionId,"modifyUserId",modifyUserId));
		} catch (final Exception e) {
			logger.error("getLvCount", e);
		}

	}

	
	public void updateOffenderOffenderBeneficiaries(final BigDecimal maxTotalAmount, final BigDecimal maxMonthlyAmount,
			final BigDecimal maxRecursiveAmount, final String percentageOfParent, final Long offenderDeductionId,final String modifyUserId) {
		final String sql = getQuery("OCDOOBLI_UPDATEOFFENDEROFFENDERBENEFICIARIES");
		Integer count = 0;
		try {
			count = namedParameterJdbcTemplate.update(sql,
					createParams("maxTotalAmount", maxTotalAmount, "maxMonthlyAmount", maxMonthlyAmount,
							"maxRecursiveAmount", maxRecursiveAmount,"percentageOfParent",percentageOfParent,"offenderDeductionId",offenderDeductionId,"modifyUserId",modifyUserId));
		} catch (final Exception e) {
			logger.error("getLvCount", e);
		}
	}

	
	public String getDeductionCategory(final String deductionType) {
		final String sql = getQuery("OCDOOBLI_GETDEDUCTIONCATEGORY");
		String desc = null;
		try {
			desc = namedParameterJdbcTemplate.queryForObject(sql,createParams("deductionType",deductionType),String.class);
		} catch (final Exception e) {
			logger.error("getDeductionCategory", e);
		}

		return desc;
	}

	
	public List<DeductionTypes> cgfkchkOffDedOffDedT(final String deductionType, final String caseloadId) {
		final String sql = getQuery("OCDOOBLI_CGFKCHKOFFDEDOFFDEDT");
		List<DeductionTypes> returnList = null;
		final RowMapper<DeductionTypes> rowMapper =Row2BeanRowMapper.makeMapping(sql,
				DeductionTypes.class, deductionTypesMapping);
		try {
			returnList = namedParameterJdbcTemplate.query(sql,createParams("deductionType",deductionType,"caseloadId",caseloadId),rowMapper);
		} catch (final Exception e) {
			logger.error("getcurChildOffDed", e);
		}

		return returnList;
	}

	
	public String getPreviousDedTxn(final Long offenderId, final String deductionType, final String informationNumber) throws EmptyResultDataAccessException {
		final String sql = getQuery("OCDOOBLI_GETPREVIOUSDEDTXN");
		String desc = null;
		try {
			desc = namedParameterJdbcTemplate.queryForObject(sql,createParams("offenderId",offenderId,
					"deductionType",deductionType,"informationNumber",informationNumber),String.class);
		}catch (final EmptyResultDataAccessException e) {
			return desc= "N";
		} catch (final IncorrectResultSizeDataAccessException e ) {
			return desc= "Y";
		} 

		return desc;
	}

	
	public String checkprevBenRec(final Long offenderDeductionId) {
		final String sql = getQuery("OCDOOBLI_CHECKPREVBENREC");
		String desc = null;
		try {
			desc = namedParameterJdbcTemplate.queryForObject(sql,createParams("offenderDeductionId",offenderDeductionId),String.class);
		} catch (final Exception e) {
			return desc= "N";
		}

		return desc;
	}

	
	public Integer updateOffenderpaymentPlans(final OffenderDeductions bean) {
		final String sql = getQuery("OCDOOBLI_UPDATEOFFENDERPAYMENTPLANS");
		Integer count =0 ;
		count = namedParameterJdbcTemplate.update(sql, createParams("maxTotalAmount",bean.getMaxTotalAmount(),
				"offnederId",bean.getOffenderId(),"informationNumber",bean.getInformationNumber(),"groupId",bean.getGroupId(),"modifyUserId",bean.getModifyUserId()));		
		return count;
	}


	public void deleteoffenderObligationHty(final Long offenderDeductionId) {
		final String sql = getQuery("OCDOOBLI_DELETEOFFENDEROBLIGATIONHTY");
		namedParameterJdbcTemplate.update(sql, createParams("offenderDeductionId",offenderDeductionId));
	}

	public Corporates getCorporateName(final Long corporateId) {
		final String sql = getQuery("OCDOOBLI_GET_CORPORATE_NAME_SUSFLG");
		final RowMapper<Corporates> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, Corporates.class,
				personMapping);
		Corporates returnObj = null;
		returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams("corporateId", corporateId),
				columnRowMapper);
		return returnObj;

	}

	public BigDecimal getMonthTotalAmntForObligation(final Long offenderDeductionId, final BigDecimal priority) {
		final String sql = getQuery("OCDOOBLI_GETMONTHTOTALAMNTFOROBLIGATION");
		BigDecimal amount = BigDecimal.ZERO;
		amount = namedParameterJdbcTemplate.queryForObject(sql, createParams("offenderDeductionId", offenderDeductionId,
				"priority",priority),BigDecimal.class);
		return amount;

	}

	public BigDecimal getMaxMonthlyAmountfromDeductions(Long offenderDeductionId) {
		final String sql = getQuery("OCDOOBLI_GETMAXMONTHLYAMOUNTFROMDEDUCTIONS");
		BigDecimal amount = null;
		try {
			amount = namedParameterJdbcTemplate.queryForObject(sql, createParams("offenderDeductionId", offenderDeductionId),BigDecimal.class);			
		} catch (Exception e) {
			logger.error("getMaxMonthlyAmountfromDeductions", e);
		}
		return amount;

	}

	public Integer checkinformationandDeductionType(final Integer offId, final String dedType, final String info) {
		final String sql = getQuery("OCDOOBLI_CHECKINFORMATION_AND_DEDUCTIONTYPE");
		Integer checkOne = null;
		try {
			checkOne = namedParameterJdbcTemplate.queryForObject(sql, createParams("offId", offId,"dedType",dedType,"info",info),Integer.class);			
		} catch (NullPointerException e) {
			return checkOne = null;
		}catch (Exception e) {
			return checkOne;
		}
		return checkOne;

	}

	public BigDecimal getmaxTotalAmntfromMainFixedObligation(final String dedType,final String caseloadId) {
		final String sql = getQuery("OCDOOBLI_GETMAXTOTALAMNTFROMMAINFIXEDOBLIGATION");
		BigDecimal checkOne = null;
		try {
			checkOne = namedParameterJdbcTemplate.queryForObject(sql, createParams("dedType", dedType,"caseloadId",caseloadId),BigDecimal.class);			
		} catch (final NullPointerException e) {
			return checkOne = null;
		}catch (final Exception e) {
			logger.error("getMaxMonthlyAmountfromDeductions", e);
		}
		return checkOne;

	}

	public BigDecimal getMaxMonthlyfromMainFixedObligation(final String dedType,final String caseloadId) {
		final String sql = getQuery("OCDOOBLI_GETMAXMONTHLYFROMMAINFIXEDOBLIGATION");
		BigDecimal checkOne = null;
		try {
			checkOne = namedParameterJdbcTemplate.queryForObject(sql, createParams("dedType", dedType,"caseloadId",caseloadId),BigDecimal.class);			
		} catch (final NullPointerException e) {
			return checkOne = null;
		}catch (final Exception e) {
			logger.error("getMaxMonthlyAmountfromDeductions", e);
		}
		return checkOne;

	}

	public BigDecimal getPercentFromMonthly(final BigDecimal monthlyAmount, final Long offenderDeductionId, final BigDecimal priority) {
		final String sql = getQuery("OCDOOBLI_GETPERCENTFROMMONTHLY");
		BigDecimal checkOne = null;
		try {
			checkOne = namedParameterJdbcTemplate.queryForObject(sql, createParams("monthlyAmount", monthlyAmount,"offenderDeductionId",offenderDeductionId,"priority",priority),BigDecimal.class);			
		} catch (final NullPointerException e) {
			return checkOne = null;
		}catch (final Exception e) {
			logger.error("getPercentFromMonthly", e);
		}
		return checkOne;

	}

	public BigDecimal getPercentFromMaxTot(BigDecimal maxtotAmount, Long offenderDeductionId, BigDecimal priority) {
		final String sql = getQuery("OCDOOBLI_GETPERCENTFROMMAXTOT");
		BigDecimal checkOne = null;
		try {
			checkOne = namedParameterJdbcTemplate.queryForObject(sql, createParams("maxtotAmount", maxtotAmount,"offenderDeductionId",offenderDeductionId,"priority",priority),BigDecimal.class);			
		} catch (final NullPointerException e) {
			return checkOne = null;
		}catch (final Exception e) {
			logger.error("getPercentFromMaxTot", e);
		}
		return checkOne;

	}

	public String getProfileVal() {		
		final String sql = getQuery("OCDOOBLI_PROFILE_VALUE_TAX_OBLIGN");
		String val= null;
		try{
			val= namedParameterJdbcTemplate.queryForObject(sql, createParams(), String.class);
		}catch (Exception e) {
			logger.error("getProfileVal", e);
		}
		return val;
	}
	
	public String getDisabledButton(final BigDecimal parentId) {		
		final String sql = getQuery("OCDOOBLI_GET_DISABLE_DBUTTON");
		String val= null;
		try{
			val= namedParameterJdbcTemplate.queryForObject(sql, createParams("PARENTMENUID", parentId), String.class);
		}catch (Exception e) {
			logger.error("getProfileVal", e);
		}
		return val;
	}
	
	@Override
	public OffenderDeductions offenderDeductionData(Long offenderDeductionId) {
		final String sql = getQuery("OCDOOBLI_OFFENDER_DEDUCTIONS_DATA");
		// List<OffenderDeductions> returnData = new ArrayList<>();
		OffenderDeductions returnData = new OffenderDeductions();
		final RowMapper<OffenderDeductions> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderDeductions.class, offenderDeductionsMapping);
		try {
			returnData = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("offenderDeductionId", offenderDeductionId), new BeanPropertyRowMapper<OffenderDeductions>(OffenderDeductions.class));
		} catch (Exception e) {
			logger.error("Offender Deduction ", e);
		}
		return returnData;
	}
	@Override
	public OffenderBeneficiaries offenderBeneficiariesData(Long beneficiaryId) {
		final String sql = getQuery("OCDOOBLI_OFFENDER_BENEFICIARIES_DATA");
		// List<OffenderDeductions> returnData = new ArrayList<>();
		OffenderBeneficiaries returnData = new OffenderBeneficiaries();
		final RowMapper<OffenderBeneficiaries> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderBeneficiaries.class, offenderBeneficiariesMapping);
		try {
			returnData = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("beneficiaryId", beneficiaryId),new BeanPropertyRowMapper<OffenderBeneficiaries>(OffenderBeneficiaries.class));
		} catch (Exception e) {
			logger.error("Offender Benefeciaries ", e);
		}
		return returnData;
	}
	@Override
	public OffenderBeneficiaries offenderBeneficiariesDatabyDeductionId(Long deductionId) {
		final String sql = getQuery("OCDOOBLI_OFFENDER_BENEFICIARIES_DATA_DEDUCTIONID");
		// List<OffenderDeductions> returnData = new ArrayList<>();
		OffenderBeneficiaries returnData = new OffenderBeneficiaries();
		final RowMapper<OffenderBeneficiaries> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderBeneficiaries.class, offenderBeneficiariesMapping);
		try {
			returnData = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("beneficiaryId", deductionId), new BeanPropertyRowMapper<OffenderBeneficiaries>(OffenderBeneficiaries.class));
		} catch (Exception e) {
			logger.error("Offender Benefeciaries ", e);
		}
		return returnData;
	}
}

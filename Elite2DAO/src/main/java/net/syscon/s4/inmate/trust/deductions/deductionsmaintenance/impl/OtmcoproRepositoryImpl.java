package net.syscon.s4.inmate.trust.deductions.deductionsmaintenance.impl;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.TransactionTypes;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.AccountCodes;
import net.syscon.s4.im.beans.CaseloadDeductionDetails;
import net.syscon.s4.im.beans.Corporates;
import net.syscon.s4.inmate.beans.CaseloadDeductionProfiles;
import net.syscon.s4.inmate.beans.DeductionTypes;
import net.syscon.s4.inmate.trust.deductions.deductionsmaintenance.OtmcoproRepository;
import net.syscon.s4.inst.booking.beans.Persons;
import net.syscon.s4.inst.visitsmanagement.impl.OidvisitRepositoryImpl;

@Repository
public class OtmcoproRepositoryImpl extends RepositoryBase implements OtmcoproRepository {

	private final Map<String, FieldMapper> personsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("PAYEE_PERSON_ID", 			 new FieldMapper("payeePersonId"))
			.put("1", 							 new FieldMapper("1"))
			.build();
	private final Map<String, FieldMapper> transactionTypesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DESCRIPTION", 				 new FieldMapper("description"))
			.put("CASELOAD_TYPE", 				 new FieldMapper("caseloadType"))
			.put("RECEIPT_TXN_TYPE",			 new FieldMapper("receiptTxnType"))
			.build();
	private final Map<String, FieldMapper> deductionTypesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DEDUCTION_TYPE", 				 new FieldMapper("deductionType"))
			.put("DEDUCTION_DESC", 				 new FieldMapper("deductionDesc"))
			.put("CASELOAD_TYPE", 				 new FieldMapper("caseloadType"))
			.build();
	private final Map<String, FieldMapper> corporateNameMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CORPORATE_NAME", 				 new FieldMapper("corporateName"))
			.put("CORPORATE_ID", 				 new FieldMapper("corporateId"))
			.put("CASELOAD_ID", 				 new FieldMapper("caseloadId"))
			.build();
	private final Map<String, FieldMapper> accountCodesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("ACCOUNT_NAME", 				 new FieldMapper("accountName"))
			.put("CASELOAD_TYPE", 				 new FieldMapper("caseloadType"))
			.put("ACCOUNT_CODE", 				 new FieldMapper("accountCode"))
			.build();
	private final Map<String, FieldMapper> mMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DEDUCTION_TYPE",				 new FieldMapper("deductionType"))
			.put("PERSON_ID", 					 new FieldMapper("personId"))
			.put("ACCOUNT_CODE", 				 new FieldMapper("accountCode"))
			.put("TXN_TYPE", 					 new FieldMapper("txnType"))
			.put("CODE", 						 new FieldMapper("code"))
			.put("DESCRIPTION", 				 new FieldMapper("description"))
			.build();
	private final Map<String, FieldMapper> corporatesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("PAYEE_CORPORATE_ID", 			 new FieldMapper("payeeCorporateId"))
			.put("1",							 new FieldMapper("1"))
			.build();
	private final Map<String, FieldMapper> limitMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("LIMIT_AMOUNT", 				 new FieldMapper("coLimitAmount"))
			.put("PERIOD_TYPE", 				 new FieldMapper("nbtDeductionType"))
			.build();
	private final Map<String, FieldMapper> caseloadProfileMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DELAY_RECAPTURE", 			 new FieldMapper("delayRecapture"))
			.put("ACTIVE_FLAG", 				 new FieldMapper("activeFlag"))
			.put("CASELOAD_ID", 				 new FieldMapper("caseloadId"))
			.put("DEDUCTION_TYPE", 				 new FieldMapper("deductionType"))
			.put("EFFECTIVE_DATE", 				 new FieldMapper("effectiveDate"))
			.put("FIFO_FLAG", 					 new FieldMapper("fifoFlag"))
			.put("FO_AL_ALL_OFFENDER_FLAG", 	 new FieldMapper("foAlAllOffenderFlag"))
			.put("PERCENTAGE", 					 new FieldMapper("percentage"))
			.put("INTERNAL_PRIORITY_NO", 		 new FieldMapper("internalPriorityNo"))
			.put("EXTERNAL_PRIORITY_NO", 		 new FieldMapper("externalPriorityNo"))
			.put("ACCOUNT_CODE",				 new FieldMapper("accountCode"))
			.put("CO_LIMIT_AMOUNT", 			 new FieldMapper("coLimitAmount"))
			.put("CO_CREDIT_WHEN_INDIGENT_FLAG", new FieldMapper("coCreditWhenIndigentFlag"))
			.put("MAX_MONTHLY_AMOUNT", 			 new FieldMapper("maxMonthlyAmount"))
			.put("MAX_TOTAL_AMOUNT", 			 new FieldMapper("maxTotalAmount"))
			.put("EXPIRY_DATE", 				 new FieldMapper("expiryDate"))
			.put("PAYEE_PERSON_ID", 			 new FieldMapper("payeePersonId"))
			.put("PAYEE_CORPORATE_ID", 			 new FieldMapper("payeeCorporateId"))
			.put("MODIFY_USER_ID", 				 new FieldMapper("modifyUserId"))
			.put("MODIFY_DATE", 				 new FieldMapper("modifyDate"))
			.put("LIST_SEQ", 					 new FieldMapper("listSeq"))
			.put("FLAT_RATE", 					 new FieldMapper("flatRate"))
			.put("MINIMUM_TRUST_BALANCE",  		 new FieldMapper("minimumTrustBalance"))
			.put("INDIGENT_MANDATORY_FLAG",  	 new FieldMapper("indigentMandatoryFlag"))
			.put("COMM_CONDITION_TYPE",  		 new FieldMapper("commConditionType"))
			.put("COMM_CONDITION_CODE",  		 new FieldMapper("commConditionCode"))
			.put("MAX_RECURSIVE_AMOUNT",  		 new FieldMapper("maxRecursiveAmount"))
			.put("CREATE_DATETIME",  			 new FieldMapper("createDateTime"))
			.put("CREATE_USER_ID",  			 new FieldMapper("createUserId"))
			.put("MODIFY_DATETIME",  			 new FieldMapper("modifyDateTime"))
			.put("CATEGORY_TYPE",  				 new FieldMapper("categoryType"))
			.put("SEAL_FLAG",  					 new FieldMapper("sealFlag"))
			.build();
	
	private static Logger logger = LogManager.getLogger(OtmcoproRepositoryImpl.class.getName());


	/**
	 * Creates new OtmcoproRepositoryImpl class Object
	 */
	public OtmcoproRepositoryImpl() {
		// OtmcoproRepositoryImpl
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            CaseloadDeductionProfiles
	 *
	 * @return List<CaseloadDeductionProfiles>
	 *
	 * @throws SQLException
	 */
	public List<CaseloadDeductionProfiles> csldDpExecuteQuery(final CaseloadDeductionProfiles objSearchDao) {
		final String sql = getQuery("OTMCOPRO_CSLDDP_FIND_CASELOAD_DEDUCTION_PROFILES");
		String preparedSql = null;
		final StringBuffer sqlQuery = new StringBuffer();
		final MapSqlParameterSource params = new MapSqlParameterSource();
		sqlQuery.append(sql);
		if (objSearchDao != null) {
			sqlQuery.append(" where ");
			if (objSearchDao != null && objSearchDao.getDeductionType() != null
					&& !("").equals(objSearchDao.getDeductionType())) {
				sqlQuery.append(" DEDUCTION_TYPE  = :deductionType  " + " and");
				params.addValue("deductionType", objSearchDao.getDeductionType());
			}
			if (objSearchDao != null && objSearchDao.getAccountCode() != null
					&& !("").equals(objSearchDao.getAccountCode())) {
				sqlQuery.append(" ACCOUNT_CODE  = :accountCode  " + " and");
				params.addValue("accountCode", objSearchDao.getAccountCode());
			}
			if (objSearchDao != null && objSearchDao.getDelayRecapture() != null
					&& !("").equals(objSearchDao.getDelayRecapture())) {
				sqlQuery.append(" DELAY_RECAPTURE  = :delayRecapture  " + " and");
				params.addValue("delayRecapture", objSearchDao.getDelayRecapture());
			}
			if (objSearchDao != null && objSearchDao.getPayeeCorporateId() != null
					&& !("").equals(objSearchDao.getPayeeCorporateId())) {
				sqlQuery.append(" PAYEE_CORPORATE_ID  = :payeeCorporateId  " + " and");
				params.addValue("payeeCorporateId", objSearchDao.getPayeeCorporateId());
			}
			if (objSearchDao != null && objSearchDao.getExpiryDate() != null
					&& !("").equals(objSearchDao.getExpiryDate())) {
				sqlQuery.append(" :expiryDate  = TO_CHAR(EXPIRY_DATE ,'MM/DD/YYYY') " + " and");
				params.addValue("expiryDate", new SimpleDateFormat("MM/dd/yyyy").format(objSearchDao.getExpiryDate()));
			}
			if (objSearchDao != null && objSearchDao.getListSeq() != null && !("").equals(objSearchDao.getListSeq())) {
				sqlQuery.append(" LIST_SEQ  = :listSeq  " + " and");
				params.addValue("listSeq", objSearchDao.getListSeq());
			}
			if (objSearchDao != null && objSearchDao.getEffectiveDate() != null
					&& !("").equals(objSearchDao.getEffectiveDate())) {
				sqlQuery.append(" :effectiveDate  = TO_CHAR(EFFECTIVE_DATE ,'MM/DD/YYYY') " + " and");
				params.addValue("effectiveDate",
						new SimpleDateFormat("MM/dd/yyyy").format(objSearchDao.getEffectiveDate()));
			}
			if (objSearchDao != null && objSearchDao.getActiveFlag() != null
					&& !("").equals(objSearchDao.getActiveFlag())) {
				sqlQuery.append(" ACTIVE_FLAG  = :activeFlag  " + " and");
				params.addValue("activeFlag", objSearchDao.getActiveFlag());
			}
			if (objSearchDao != null && objSearchDao.getCoCreditWhenIndigentFlag() != null
					&& !("").equals(objSearchDao.getCoCreditWhenIndigentFlag())) {
				sqlQuery.append(" CO_CREDIT_WHEN_INDIGENT_FLAG  = :coCreditWhenIndigentFlag  " + " and");
				params.addValue("coCreditWhenIndigentFlag", objSearchDao.getCoCreditWhenIndigentFlag());
			}
			if (objSearchDao != null && objSearchDao.getCaseloadId() != null
					&& objSearchDao.getCaseloadType() != null) {
				sqlQuery.append(
						" caseload_id = :caseloadId and deduction_type IN ( SELECT deduction_type FROM deduction_types "
								+ " WHERE deduction_category = 'CROB' AND CASELOAD_CODE IN ('BOTH',:caseloadType)) ");
				params.addValue("caseloadId", objSearchDao.getCaseloadId());
				params.addValue("caseloadType", objSearchDao.getCaseloadType());
			}
		}
		preparedSql = sqlQuery.toString().trim();
		if (preparedSql.endsWith("where")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 5);
		}
		if (preparedSql.endsWith("and")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 3);
		}
		final RowMapper<CaseloadDeductionProfiles> CaseloadDeductionProfilesRowMapper = Row2BeanRowMapper
				.makeMapping(sql, CaseloadDeductionProfiles.class, caseloadProfileMapping);
		List<CaseloadDeductionProfiles> returnList = new ArrayList<>();
		returnList = namedParameterJdbcTemplate.query(preparedSql, params, CaseloadDeductionProfilesRowMapper);
		return returnList;
	}

	/**
	 * This method is used to insert the records in the data base tables based
	 * on
	 *
	 * @param lstCaseloadDeductionProfiles
	 *            List<CaseloadDeductionProfiles>
	 *
	 * @return List<Integer>
	 *
	 * @throws SQLException
	 */
	public Integer csldDpInsertCaseloadDeductionProfiles(
			final List<CaseloadDeductionProfiles> lstCaseloadDeductionProfiles) {
		String sql = getQuery("OTMCOPRO_CSLDDP_INSERT_CASELOAD_DEDUCTION_PROFILES");
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
	 * @throws SQLException
	 */
	public Integer csldDpUpdateCaseloadDeductionProfiles(
			final List<CaseloadDeductionProfiles> lstCaseloadDeductionProfiles) {
		String sql = getQuery("OTMCOPRO_CSLDDP_UPDATE_CASELOAD_DEDUCTION_PROFILES");
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
	 * @throws SQLException
	 */
	public Integer csldDpDeleteCaseloadDeductionProfiles(
			final List<CaseloadDeductionProfiles> lstCaseloadDeductionProfiles) {
		String sql = getQuery("OTMCOPRO_CSLDDP_DELETE_CASELOAD_DEDUCTION_PROFILES");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (CaseloadDeductionProfiles caseloadDeductionProfiles : lstCaseloadDeductionProfiles) {
			parameters.add(new BeanPropertySqlParameterSource(caseloadDeductionProfiles));
		}
		try {
			String tableName = "CASELOAD_DEDUCTION_PROFILES";
			String whereClause = "CASELOAD_ID = :caseloadId AND DEDUCTION_TYPE = :deductionType";
			batchUpdatePreDeletedRows(tableName, whereClause , parameters);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method csldDpDeleteCaseloadDeductionProfiles", e);
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstCaseloadDeductionProfiles.size() == returnArray.length) {
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
	 * @throws SQLException
	 */
	public List<CaseloadDeductionDetails> csldDdExecuteQuery(final CaseloadDeductionDetails objSearchDao) {
		final String sql = getQuery("OTMCOPRO_CSLDDD_FIND_CASELOAD_DEDUCTION_DETAILS");
		final RowMapper<CaseloadDeductionDetails> CaseloadDeductionDetailsRowMapper = Row2BeanRowMapper.makeMapping(sql,
				CaseloadDeductionDetails.class, deductionTypesMapping);
		List<CaseloadDeductionDetails> returnList = new ArrayList<>();
		returnList = namedParameterJdbcTemplate.query(sql, createParams("CASELOADID", objSearchDao.getCaseloadId(),
				"DEDUCTIONTYPE", objSearchDao.getDeductionType()), CaseloadDeductionDetailsRowMapper);
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
	 * @throws SQLException
	 */
	public Integer csldDdInsertCaseloadDeductionDetails(
			final List<CaseloadDeductionDetails> lstCaseloadDeductionDetails) {
		String sql = getQuery("OTMCOPRO_CSLDDD_INSERT_CASELOAD_DEDUCTION_DETAILS");
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
	 * @throws SQLException
	 */
	public Integer csldDdUpdateCaseloadDeductionDetails(
			final List<CaseloadDeductionDetails> lstCaseloadDeductionDetails) {
		String sql = getQuery("OTMCOPRO_CSLDDD_UPDATE_CASELOAD_DEDUCTION_DETAILS");
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
	 * @throws SQLException
	 */
	public Integer csldDdDeleteCaseloadDeductionDetails(
			final List<CaseloadDeductionDetails> lstCaseloadDeductionDetails) {
		String sql = getQuery("OTMCOPRO_CSLDDD_DELETE_CASELOAD_DEDUCTION_DETAILS");
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
	 * @return List<MCrp.corporateName>
	 */
	public List<Corporates> cgfkCsldDpPayeeCorporateIRecordGroup() {
		final String sql = getQuery("OTMCOPRO_FIND_CGFKCSLDDPPAYEECORPORATEI");
		final RowMapper<Corporates> corporateNameRowMapper = Row2BeanRowMapper.makeMapping(sql, Corporates.class,
				corporateNameMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), corporateNameRowMapper);
		} catch (Exception e) {
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<TransactionTypes> cgfkCsldDdReceiptTxnTypeRecordGroup(final String caseloadType) {
		final String sql = getQuery("OTMCOPRO_FIND_CGFKCSLDDDRECEIPTTXNTYPE");
		final RowMapper<TransactionTypes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, TransactionTypes.class,
				mMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams("caseloadType", caseloadType), mRowMapper);
		} catch (Exception e) {
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<DeductionTypes>
	 */
	public List<DeductionTypes> cgfkCsldDpDeductionTypeRecordGroup(final String caseloadType) {
		final String sql = getQuery("OTMCOPRO_FIND_CGFKCSLDDPDEDUCTIONTYPE");
		final RowMapper<DeductionTypes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, DeductionTypes.class, mMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams("CASELOADTYPE", caseloadType), mRowMapper);
		} catch (Exception e) {
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<AccountCodes> cgfkCsldDpAccountCodeRecordGroup(final String caseloadType) {
		final String sql = getQuery("OTMCOPRO_FIND_CGFKCSLDDPACCOUNTCODE");
		final RowMapper<AccountCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, AccountCodes.class, mMapping);
		List<AccountCodes> returnList=new ArrayList<>();
		try {
			returnList=namedParameterJdbcTemplate.query(sql, createParams("CASELOADTYPE", caseloadType), mRowMapper);
			for (AccountCodes accountCodes : returnList) {
				accountCodes.setListSeq(new BigDecimal(accountCodes.getAccountCode()));
			}
		} catch (Exception e) {
			return Collections.emptyList();
		}
		return returnList; 
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<Persons> cgfkCsldDpPayeePersonIdRecordGroup() {
		final String sql = getQuery("OTMCOPRO_FIND_CGFKCSLDDPPAYEEPERSONID");
		final RowMapper<Persons> mRowMapper = Row2BeanRowMapper.makeMapping(sql, Persons.class, mMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (Exception e) {
			return Collections.emptyList();
		}
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfkchkCsldDpDedprofCorp
	 *
	 * @param params
	 *
	 */
	public List<Corporates> cgfkchkCsldDpDedprofCorp(final Corporates paramBean) {
		final String sql = getQuery("OTMCOPRO_CGFKCHK_CSLD_DP_DEDPROF_CORP_");
		final RowMapper<Corporates> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, Corporates.class,
				corporatesMapping);
		List<Corporates> returnList = new ArrayList<>();
		returnList = namedParameterJdbcTemplate.query(sql, createParams(), columnRowMapper);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfkchkCsldDpDedprofPer
	 *
	 * @param params
	 *
	 */
	public List<Persons> cgfkchkCsldDpDedprofPer(final Persons paramBean) {
		final String sql = getQuery("OTMCOPRO_CGFKCHK_CSLD_DP_DEDPROF_PER_F");
		final RowMapper<Persons> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, Persons.class, personsMapping);
		List<Persons> returnList = new ArrayList<>();
		returnList = namedParameterJdbcTemplate.query(sql, createParams(), columnRowMapper);
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
		final String sql = getQuery("OTMCOPRO_CGFKCHK_CSLD_DP_DEDPROF_AC_CO");
		final RowMapper<AccountCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, AccountCodes.class,
				accountCodesMapping);
		List<AccountCodes> returnList = new ArrayList<>();
		returnList = namedParameterJdbcTemplate.query(sql, createParams(), columnRowMapper);
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
		final String sql = getQuery("OTMCOPRO_CGFKCHK_CSLD_DP_DEDPROF_DEDTY");
		final RowMapper<DeductionTypes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, DeductionTypes.class,
				deductionTypesMapping);
		List<DeductionTypes> returnList = new ArrayList<>();
		returnList = namedParameterJdbcTemplate.query(sql, createParams(), columnRowMapper);
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
		final String sql = getQuery("OTMCOPRO_CGFKCHK_CSLD_DD_CSLD_DD_TXNTY");
		final RowMapper<TransactionTypes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, TransactionTypes.class,
				transactionTypesMapping);
		List<TransactionTypes> returnList = new ArrayList<>();
		returnList = namedParameterJdbcTemplate.query(sql, createParams(), columnRowMapper);
		return returnList;
	}

	@Override
	public Map<String, Object> getPercentAndEnternalPriority(final String caseloadId, final String deductionType) {
		final String sql = getQuery("OTMCOPRO_GET_PERCENT_AND_ENTERNAL_PRIORITY");
		Map<String, Object> param = new HashMap<>();
		param.put("caseloadId", caseloadId);
		param.put("deductionType", deductionType);
		return namedParameterJdbcTemplate.queryForMap(sql, param);
	}

	@Override
	public BigDecimal getMaxExternalPriorityNo(final String caseloadId) {
		final String sql = getQuery("OTMCOPRO_GET_MAX_EXTERNAL_PRIORITY_NO");

		try {
			return namedParameterJdbcTemplate.queryForObject(sql, createParams("caseloadId", caseloadId),
					BigDecimal.class);
		} catch (Exception e) {
			throw new RuntimeException("Other error when getting next available EXP/INP combination.");
		}
	}

	@Override
	public BigDecimal getMaxInternalPriorityNo(final String caseloadId, final BigDecimal externalPriorityNo) {
		final String sql = getQuery("OTMCOPRO_GET_MAX_INTERNAL_PRIORITY_NO");

		try {
			return namedParameterJdbcTemplate.queryForObject(sql,
					createParams("caseloadId", caseloadId, "externalPriorityNo", externalPriorityNo), BigDecimal.class);
		} catch (Exception e) {
			throw new RuntimeException("Other error when getting next available EXP/INP combination.");
		}
	}

	@Override
	public BigDecimal getReceiptPercentage(final String deductionType, final String caseloadId) {
		final String sql = getQuery("OTMCOPRO_GET_RECEIPT_PERCENTAGE");
		return namedParameterJdbcTemplate.queryForObject(sql,
				createParams("deductionType", deductionType, "caseloadId", caseloadId), BigDecimal.class);
	}

	@Override
	public Integer updateCaseloadDeductionProfilesPercentage(final BigDecimal percentage, final String caseloadId,
			String deductionType, String userId) {
		final String sql = getQuery("OTMCOPRO_UPDATE_CASELOAD_DEDUCTION_PROFILES_PERCENTAGE");
		return namedParameterJdbcTemplate.update(sql,
				createParams("percentage", percentage, "caseloadId", caseloadId, "deductionType", deductionType,"modifyUserId",userId));
	}

	@Override
	public String getCalculateOnVal(final String deductionType) {
		final String sql = getQuery("OTMCOPRO_GET_CALCULATE_ON_VAL");
		String aCur = "";
		try {
			aCur = namedParameterJdbcTemplate.queryForObject(sql, createParams("deductionType", deductionType),
					String.class);
		} catch (Exception e) {
			throw new RuntimeException(
					"Error in GET_CALCULATE_ON_VAL, getting From_Balance_Type from Deduction_Types.");
		}

		return aCur;
	}

	@Override
	public String chkDuplicate(final String caseloadId, final String deductionType) {
		final String sql = getQuery("OTMCOPRO_CHK_DUPLICATE");
		return namedParameterJdbcTemplate.queryForObject(sql,
				createParams("caseloadId", caseloadId, "deductionType", deductionType), String.class);
	}

	@Override
	public CaseloadDeductionProfiles getLimitAndPeriodType(final String caseloadId, final String deductionType) {
		final String sql = getQuery("OTMCOPRO_GET_LIMIT_AND_PERIOD_TYPE");
		try {
		final RowMapper<CaseloadDeductionProfiles> CaseloadDeductionProfilesRowMapper = Row2BeanRowMapper
				.makeMapping(sql, CaseloadDeductionProfiles.class, limitMapping);
		return namedParameterJdbcTemplate.queryForObject(sql,
				createParams("caseloadId", caseloadId, "deductionType", deductionType),
				CaseloadDeductionProfilesRowMapper);
		}catch (Exception e) {
			logger.error(this.getClass().getName()+" getLimitAndPeriodType"+e);
			return null;
		}

	}

	@Override
	public Corporates cgfkchkCsldDbenCsldDben(final BigDecimal corporateId) {
		final String sql = getQuery("OTMCOPRO_CGFKCHK_CSLD_DBEN_CSLD_DBEN_C");
		 Corporates returnList=null;
		final RowMapper<Corporates> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, Corporates.class,
				corporateNameMapping);
		try {
		 returnList = namedParameterJdbcTemplate.queryForObject(sql,
				createParams("corporateId", corporateId), columnRowMapper);
		}catch (Exception e) {
			logger.error(this.getClass().getName()+" getLimitAndPeriodType"+e);
		}
		return returnList;
	}

}

package net.syscon.s4.inmate.trust.deductions.deductionsmaintenance.impl;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
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
import net.syscon.s4.globaloffenderrecords.impl.OsiosearRepositoryImpl;
import net.syscon.s4.im.beans.AccountCodes;
import net.syscon.s4.im.beans.CaseloadDeductionDetails;
import net.syscon.s4.im.beans.SysDual;
import net.syscon.s4.inmate.beans.CaseloadDeductionProfiles;
import net.syscon.s4.inmate.beans.DeductionTypes;
import net.syscon.s4.inmate.trust.deductions.deductionsmaintenance.OtmalproRepository;

/**
 * Class OtmalproRepositoryImpl
 * 
 * @author Arkin Software Technologies
 * @version 1.0
 */
@Repository
public class OtmalproRepositoryImpl extends RepositoryBase implements OtmalproRepository {

	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OsiosearRepositoryImpl.class.getName());

	private final Map<String, FieldMapper> transactionTypesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DESCRIPTION", 			new FieldMapper("description"))
			.put("CASELOAD_TYPE", 			new FieldMapper("caseloadType"))
			.put("RECEIPT_TXN_TYPE", 		new FieldMapper("receiptTxnType")).build();
	private final Map<String, FieldMapper> deductionTypesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DEDUCTION_TYPE", 			new FieldMapper("deductionType"))
			.put("DEDUCTION_DESC", 			new FieldMapper("deductionDesc"))
			.put("CASELOAD_TYPE", 			new FieldMapper("caseloadType")).build();
	private final Map<String, FieldMapper> accountCodesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("ACCOUNT_NAME", 			new FieldMapper("accountName"))
			.put("CASELOAD_TYPE", 			new FieldMapper("caseloadType"))
			.put("ACCOUNT_CODE", 			new FieldMapper("accountCode")).build();
	private final Map<String, FieldMapper> mMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DEDUCTION_TYPE", 			new FieldMapper("deductionType"))
			.put("ACCOUNT_CODE", 			new FieldMapper("accountCode"))
			.put("TXN_TYPE", 				new FieldMapper("txnType"))
			.put("DESCRIPTION", 			new FieldMapper("description"))
			.put("calculateon", 			new FieldMapper("calculateON"))
			.build();
	private final Map<String, FieldMapper> caseloadDeductionProfilesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DEDUCTION_TYPE",			new FieldMapper("deductionType"))
			.put("DELAY_RECAPTURE",			new FieldMapper("delayRecapture"))
			.put("ACTIVE_FLAG",				new FieldMapper("activeFlag"))
			.put("CASELOAD_ID",				new FieldMapper("caseloadId"))
			.put("EFFECTIVE_DATE",			new FieldMapper("effectiveDate"))
			.put("FIFO_FLAG",				new FieldMapper("fifoFlag"))
			.put("FO_AL_ALL_OFFENDER_FLAG",	new FieldMapper("foAlAllOffenderFlag"))
			.put("PERCENTAGE",				new FieldMapper("percentage"))
			.put("INTERNAL_PRIORITY_NO",	new FieldMapper("internalPriorityNo"))
			.put("EXTERNAL_PRIORITY_NO",	new FieldMapper("externalPriorityNo"))
			.put("ACCOUNT_CODE",			new FieldMapper("accountCode"))
			.put("CO_LIMIT_AMOUNT",			new FieldMapper("coLimitAmount"))
			.put("CO_CREDIT_WHEN_INDIGENT_FLAG", 			new FieldMapper("coCreditWhenIndigentFlag"))
			.put("MAX_MONTHLY_AMOUNT", 		new FieldMapper("maxMonthlyAmount"))
			.put("MAX_TOTAL_AMOUNT", 		new FieldMapper("maxTotalAmount"))
			.put("EXPIRY_DATE", 			new FieldMapper("expiryDate"))
			.put("PAYEE_PERSON_ID", 		new FieldMapper("payeePersonId"))
			.put("PAYEE_CORPORATE_ID", 		new FieldMapper("payeeCorporateId"))
			.put("MODIFY_USER_ID", 			new FieldMapper("modifyUserId"))
			.put("MODIFY_DATE", 			new FieldMapper("modifyDate"))
			.put("LIST_SEQ", 				new FieldMapper("listSeq"))
			.put("FLAT_RATE", 				new FieldMapper("flatRate"))
			.put("MINIMUM_TRUST_BALANCE", 	new FieldMapper("minimumTrustBalance"))
			.put("INDIGENT_MANDATORY_FLAG", new FieldMapper("indigentMandatoryFlag"))
			.put("COMM_CONDITION_TYPE", 	new FieldMapper("commConditionType"))
			.put("COMM_CONDITION_CODE", 	new FieldMapper("commConditionCode"))
			.put("MAX_RECURSIVE_AMOUNT", 	new FieldMapper("maxRecursiveAmount"))
			.put("CREATE_DATETIME", 		new FieldMapper("createDateTime"))
			.put("CREATE_USER_ID", 			new FieldMapper("createUserId"))
			.put("MODIFY_DATETIME", 		new FieldMapper("modifyDateTime"))
			.put("CATEGORY_TYPE", 			new FieldMapper("categoryType"))
			.put("SEAL_FLAG", 				new FieldMapper("sealFlag"))
			.build();

	private final Map<String, FieldMapper> caseloadDeductionDetailsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CASELOAD_ID", 			new FieldMapper("caseloadId"))
			.put("DEDUCTION_TYPE",  		new FieldMapper("deductionType"))
			.put("RECEIPT_TXN_TYPE",  		new FieldMapper("receiptTxnType"))
			.put("PERCENTAGE",  			new FieldMapper("percentage"))
			.put("MODIFY_USER_ID",  		new FieldMapper("modifyUserId"))
			.put("MODIFY_DATE",  			new FieldMapper("modifyDate"))
			.put("LIST_SEQ",  				new FieldMapper("listSeq"))
			.put("FLAT_RATE",  				new FieldMapper("flatRate"))
			.put("MINIMUM_TRUST_BALANCE_FLAG", new FieldMapper("minimumTrustBalanceFlag"))
			.put("CREATE_DATETIME",  		new FieldMapper("createDatetime"))
			.put("CREATE_USER_ID",  		new FieldMapper("createUserId"))
			.put("MODIFY_DATETIME",  		new FieldMapper("modifyDatetime"))
			.put("SEAL_FLAG",  				new FieldMapper("sealFlag"))
			.build();

	/**
	 * Creates new OtmalproRepositoryImpl class Object
	 */
	public OtmalproRepositoryImpl() {
		// OtmalproRepositoryImpl
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
	public List<CaseloadDeductionProfiles> csldDpExecuteQuery(CaseloadDeductionProfiles objSearchDao) {
		final String sql = getQuery("OTMALPRO_CSLDDP_FIND_CASELOAD_DEDUCTION_PROFILES");
		final RowMapper<CaseloadDeductionProfiles> caseloadDeductionProfilesRowMapper = Row2BeanRowMapper
				.makeMapping(sql, CaseloadDeductionProfiles.class, caseloadDeductionProfilesMapping);
		List<CaseloadDeductionProfiles> returnList = new ArrayList<CaseloadDeductionProfiles>();
		String preparedSql = null;
		final MapSqlParameterSource inParameterSource = new MapSqlParameterSource();
		final StringBuffer sqlQuery = new StringBuffer();
		sqlQuery.append(sql);
		
		if (objSearchDao.getCaseloadId() != null) {
			sqlQuery.append("CASELOAD_ID = :CASELOAD_ID" + " AND  ");
			inParameterSource.addValue("CASELOAD_ID", objSearchDao.getCaseloadId());
		}
		if (objSearchDao.getDeductionType() != null) {
			sqlQuery.append("DEDUCTION_TYPE = :DEDUCTION_TYPE" + " AND ");
			inParameterSource.addValue("DEDUCTION_TYPE", objSearchDao.getDeductionType());
		}
		if (objSearchDao.getAccountCode() != null) {
			sqlQuery.append("ACCOUNT_CODE = :ACCOUNT_CODE" + " AND ");
			inParameterSource.addValue("ACCOUNT_CODE", objSearchDao.getAccountCode());
		}
		if (objSearchDao.getListSeq() != null) {
			sqlQuery.append("LIST_SEQ = :LIST_SEQ" + " AND ");
			inParameterSource.addValue("LIST_SEQ", objSearchDao.getListSeq());
		}
		if (objSearchDao.getCaseloadType() != null) {
			sqlQuery.append(" DEDUCTION_TYPE IN  (SELECT DEDUCTION_TYPE FROM DEDUCTION_TYPES WHERE DEDUCTION_CATEGORY = 'ALCN' AND CASELOAD_CODE IN ('BOTH',:CASELOAD_TYPE)) ");
			inParameterSource.addValue("CASELOAD_TYPE", objSearchDao.getCaseloadType());
		}
		
		sqlQuery.append(" order by list_seq ASC ");
		
		preparedSql = sqlQuery.toString().trim();
		
		if (preparedSql.endsWith("WHERE")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 5);
		}
		if (preparedSql.endsWith("AND")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 3);
		}
		try {
			returnList = namedParameterJdbcTemplate.query(preparedSql, inParameterSource, caseloadDeductionProfilesRowMapper);
		} catch (Exception e) {
			logger.error("csldDpExecuteQuery", e);

		}
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
		String sql = getQuery("OTMALPRO_CSLDDP_INSERT_CASELOAD_DEDUCTION_PROFILES");
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
	public Integer csldDpUpdateCaseloadDeductionProfiles(final List<CaseloadDeductionProfiles> lstCaseloadDeductionProfiles) {
		String sql = getQuery("OTMALPRO_CSLDDP_UPDATE_CASELOAD_DEDUCTION_PROFILES");
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
		int deleteCount = 0;
		String sql = getQuery("OTMALPRO_CSLDDP_DELETE_CASELOAD_DEDUCTION_PROFILES");
		int returnValue = 0;
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		String checkDelete  = getQuery("OTMALPRO_CHECK_FOR_DELETE");
		int count = 0;
		for (CaseloadDeductionProfiles caseloadDeductionProfiles : lstCaseloadDeductionProfiles) {
			count = namedParameterJdbcTemplate.queryForObject(checkDelete,
					createParams("caseLoad", caseloadDeductionProfiles.getCaseloadId(), "deductionType",
							caseloadDeductionProfiles.getDeductionType()),
					Integer.class);
			if (count !=0) {
				break;
			}
		}
		if (count == 0) {
			for (CaseloadDeductionProfiles caseloadDeductionProfiles : lstCaseloadDeductionProfiles) {
				parameters.add(new BeanPropertySqlParameterSource(caseloadDeductionProfiles));
			}
			try {
				String tableName = "CASELOAD_DEDUCTION_PROFILES";
				String whereClause = "DEDUCTION_TYPE = :deductionType AND CASELOAD_ID = :caseloadId";
				batchUpdatePreDeletedRows(tableName, whereClause , parameters);
			} catch (Exception e) {
				logger.error("Exception occured in " + this.getClass().getName() + " in method csldDpDeleteCaseloadDeductionProfiles", e);
			}
			try {
				returnArray = namedParameterJdbcTemplate.batchUpdate(sql,
						parameters.toArray(new SqlParameterSource[0]));
			} catch (Exception e) {
				String error = "Error : " + e.getMessage();
				if (error.toUpperCase().contains("OFF_DED_CSLD_DD_F1")) {
					return 5;
				}
			}
			if (lstCaseloadDeductionProfiles.size() == returnArray.length) {
				return 1;
			} else {
				return 0;
			}
		} else {
			return 101;
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
	public List<CaseloadDeductionDetails> csldDdExecuteQuery(CaseloadDeductionDetails objSearchDao) {
		final String sql = getQuery("OTMALPRO_CSLDDD_FIND_CASELOAD_DEDUCTION_DETAILS");
		final RowMapper<CaseloadDeductionDetails> caseloadDeductionDetailsRowMapper = Row2BeanRowMapper.makeMapping(sql,
				CaseloadDeductionDetails.class, caseloadDeductionDetailsMapping);
		List<CaseloadDeductionDetails> returnList= new ArrayList<CaseloadDeductionDetails>();
		try{
		returnList =  namedParameterJdbcTemplate.query(sql, createParams("caseloadId",objSearchDao.getCaseloadId(),
				"deductionType",objSearchDao.getDeductionType()), caseloadDeductionDetailsRowMapper);
		}catch (Exception e) {
			logger.error("csldDdExecuteQuery",e);
		}
		return returnList;
	}

	/**
	 * @param
	 *
	 * @throws SQLException
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
	 * @throws SQLException
	 */
	public Integer csldDdInsertCaseloadDeductionDetails(
			final List<CaseloadDeductionDetails> lstCaseloadDeductionDetails) {
		String sql = getQuery("OTMALPRO_CSLDDD_INSERT_CASELOAD_DEDUCTION_DETAILS");
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
		int insertCount = 0;
		String sql = getQuery("OTMALPRO_CSLDDD_UPDATE_CASELOAD_DEDUCTION_DETAILS");
		int returnValue = 0;
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
		int deleteCount = 0;
		String sql = getQuery("OTMALPRO_CSLDDD_DELETE_CASELOAD_DEDUCTION_DETAILS");
		int returnValue = 0;
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (CaseloadDeductionDetails caseloadDeductionDetails : lstCaseloadDeductionDetails) {
			parameters.add(new BeanPropertySqlParameterSource(caseloadDeductionDetails));
		}
		try {
			String tableName = "CASELOAD_DEDUCTION_DETAILS";
			String whereClause = "CASELOAD_ID = :caseloadId AND DEDUCTION_TYPE = :deductionType AND RECEIPT_TXN_TYPE = :receiptTxnType";
			batchUpdatePreDeletedRows(tableName, whereClause , parameters);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method csldDdDeleteCaseloadDeductionDetails", e);
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstCaseloadDeductionDetails.size() ==  returnArray.length) {
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
	public List<AccountCodes> cgfkCsldDpAccountCodeRecordGroup(final String caseloadType) {
		final String sql = getQuery("OTMALPRO_FIND_CGFKCSLDDPACCOUNTCODE");
		final RowMapper<AccountCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, AccountCodes.class, mMapping);
		List<AccountCodes> returnList = new ArrayList<AccountCodes>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams("CASELOADTYPE", caseloadType), mRowMapper);
		} catch (Exception e) {
			logger.error("cgfkCsldDpAccountCodeRecordGroup", e);
		}
		return returnList;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<TransactionTypes> cgfkCsldDdReceiptTxnTypeRecordGroup(final String caseloadType) {
		final String sql = getQuery("OTMALPRO_FIND_CGFKCSLDDDRECEIPTTXNTYPE");
		final RowMapper<TransactionTypes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, TransactionTypes.class,
				mMapping);
		List<TransactionTypes> returnList = new ArrayList<TransactionTypes>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams("CASELOADTYPE", caseloadType), mRowMapper);
		} catch (Exception e) {
			logger.error("cgfkCsldDdReceiptTxnTypeRecordGroup", e);
		}
		return returnList;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<DeductionTypes> cgfkCsldDpDeductionTypeRecordGroup(final String caseloadType) {
		final String sql = getQuery("OTMALPRO_FIND_CGFKCSLDDPDEDUCTIONTYPE");
		final RowMapper<DeductionTypes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, DeductionTypes.class, mMapping);
		List<DeductionTypes> returnList = new ArrayList<DeductionTypes>();

		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams("CASELOADTYPE", caseloadType), mRowMapper);
		} catch (Exception e) {
			logger.error("cgfkCsldDpDeductionTypeRecordGroup", e);
		}
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
	public List<AccountCodes> cgfkchkCsldDpDedprofAcCo(AccountCodes paramBean) {
		final String sql = getQuery("OTMALPRO_CGFKCHK_CSLD_DP_DEDPROF_AC_CO");
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
	public List<DeductionTypes> cgfkchkCsldDpDedprofDedty(DeductionTypes paramBean) {
		final String sql = getQuery("OTMALPRO_CGFKCHK_CSLD_DP_DEDPROF_DEDTY");
		final RowMapper<DeductionTypes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, DeductionTypes.class,
				deductionTypesMapping);
		final ArrayList<DeductionTypes> returnList = (ArrayList<DeductionTypes>) namedParameterJdbcTemplate.query(sql,
				createParams(), columnRowMapper);
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
	public List<Object> cgwhenNewFormInstance(SysDual paramBean) {
		final String sql = getQuery("OTMALPRO_CGWHEN_NEW_FORM_INSTANCE");
		final RowMapper<Object> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, Object.class, mMapping);
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
	public List<TransactionTypes> cgfkchkCsldDdCsldDdTxnty(TransactionTypes paramBean) {
		final String sql = getQuery("OTMALPRO_CGFKCHK_CSLD_DD_CSLD_DD_TXNTY");
		final RowMapper<TransactionTypes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, TransactionTypes.class,
				transactionTypesMapping);
		final ArrayList<TransactionTypes> returnList = (ArrayList<TransactionTypes>) namedParameterJdbcTemplate
				.query(sql, createParams(), columnRowMapper);
		return returnList;
	}

	@Override
	public List<CaseloadDeductionDetails> csldDdExecuteQuery(final String caseloadId, final String deductionType) {
		final String sql = getQuery("OTMALPRO_CSLDDD_FIND_CASELOAD_DEDUCTION_DETAILS");
		final RowMapper<CaseloadDeductionDetails> caseloadDeductionDetailsRowMapper = Row2BeanRowMapper.makeMapping(sql,
				CaseloadDeductionDetails.class, caseloadDeductionDetailsMapping);
		List<CaseloadDeductionDetails> returnList = new ArrayList<CaseloadDeductionDetails>();
		returnList =  namedParameterJdbcTemplate.query(sql, createParams("caseloadId",caseloadId,"deductionType",deductionType), caseloadDeductionDetailsRowMapper);
		return returnList;
	}

	@Override
	public String getDesc(final String caseloadType, final String receiptTxnType) {
		final String sql = getQuery("OTMALPRO_CGFKCHK_CSLD_DD_CSLD_DD_TXNTY");
		String desc=null;
		try{
		desc=namedParameterJdbcTemplate.queryForObject(sql, createParams("CASELOADTYPE",caseloadType,"RECEIPTTXNTYPE",receiptTxnType),String.class);
		} catch(Exception e){
			logger.error("getDesc",e);
			
		}
		return desc;
	}

	@Override
	public String getfromBalType(String deductionType) {
		final String sql = getQuery("OTMALPRO_FROM_BALANCE_TYPE");
		String desc=null;
		try{
		desc=namedParameterJdbcTemplate.queryForObject(sql, createParams("deductionType",deductionType),String.class);
		} catch(Exception e){
			logger.error("getfromBalType",e);
			
		}
		return desc;
	}

	public String getFormBalDsc(String fromBalType) {
		final String sql = getQuery("OTMALPRO_FROM_BALANCE_TYPE_DESC");
		String desc=null;
		try{
		desc=namedParameterJdbcTemplate.queryForObject(sql, createParams("fromBalType",fromBalType),String.class);
		} catch(Exception e){
			logger.error("getFormBalDsc",e);
			
		}
		return desc;
	}

	public BigDecimal getExternalPriority(final String caseloadId) {
		final String sql = getQuery("OTMALPRO_EXTERNAL_PRIORITY_NO");
		BigDecimal  exprt=BigDecimal.valueOf(0);
		try{
			exprt=namedParameterJdbcTemplate.queryForObject(sql, createParams("caseloadId",caseloadId),BigDecimal.class);
		} catch(Exception e){
			exprt =BigDecimal.valueOf(1);
			
		}
		return exprt;

}

	public List<CaseloadDeductionProfiles> gtePercentageandExternalPriority(final String caseloadId, final String deductionType) {
		final String sql = getQuery("OTMALPRO_GTEPERCENTAGEANDEXTERNALPRIORITY");
		List<CaseloadDeductionProfiles>  beanData=new ArrayList<CaseloadDeductionProfiles>();
		final RowMapper<CaseloadDeductionProfiles> caseloadDeductionProfilesRowMapper = Row2BeanRowMapper
				.makeMapping(sql, CaseloadDeductionProfiles.class, caseloadDeductionDetailsMapping);
		try{
			beanData=namedParameterJdbcTemplate.query(sql, createParams("caseloadId",caseloadId,"deductionType",deductionType)
					,caseloadDeductionProfilesRowMapper);
		} catch(Exception e){
			logger.error("gtePercentageandExternalPriority",e);
			
		}
		return beanData;
}

	@Override
	public String getfromBalDesc(String deductionType) {
		final String sql = getQuery("OTMALPRO_FROM_BALANCE_TYPE");
		String desc=null;
		try{
		desc=namedParameterJdbcTemplate.queryForObject(sql, createParams("deductionType",deductionType),String.class);
		} catch(Exception e){
			logger.error("getfromBalDesc",e);
			
		}
		return desc;
	}

	public Integer compareEffectiveDatec(String effectiveDate) {
		final String sql = getQuery("OTMALPRO_COMPAREEFFECTIVEDATEC");
		Integer desc=0;
		try{
		desc=namedParameterJdbcTemplate.queryForObject(sql, createParams("effectiveDate",effectiveDate),Integer.class);
		} catch(Exception e){
			logger.error("compareEffectiveDatec",e);
			
		}
		return desc;
	}

	public List<CaseloadDeductionProfiles> checkExists(String caseloadId, String deductionType) {
		final String sql = getQuery("OTMALPRO_CHECKEXISTS");
		final RowMapper<CaseloadDeductionProfiles> caseloadDeductionProfilesRowMapper = Row2BeanRowMapper
				.makeMapping(sql, CaseloadDeductionProfiles.class, caseloadDeductionProfilesMapping);
		List<CaseloadDeductionProfiles> returnList = new ArrayList<CaseloadDeductionProfiles>();
		try{
			returnList=namedParameterJdbcTemplate.query(sql, createParams("caseloadId",caseloadId,"deductionType",deductionType)
					,caseloadDeductionProfilesRowMapper);
		} catch(Exception e){
			logger.error("checkExists",e);
			
		}
		return returnList;
}

	public BigDecimal getMaxPercenatge(String caseloadId, String deductionType) {
		final String sql = getQuery("OTMALPRO_GETMAXPERCENATGE");
		BigDecimal desc=BigDecimal.valueOf(0);
		try{
		desc=namedParameterJdbcTemplate.queryForObject(sql, createParams("caseloadId",caseloadId,"deductionType",deductionType),BigDecimal.class);
		} catch(Exception e){
			logger.error("compareEffectiveDatec",e);
			
		}
		return desc;
	}

	public void updateCaseloadDeductionProfiles(String caseloadId, BigDecimal percentage, String deductionType,String modifyUserId) {
		
		final String sql = getQuery("OTMALPRO_UPDATECASELOADDEDUCTIONPROFILES");
		namedParameterJdbcTemplate.update(sql, createParams("caseloadId",caseloadId,"percentage",percentage,"deductionType",deductionType,"modifyUserId",modifyUserId));
	}

	@Override
	public void updateCaseloadDeductionProfilesWithouttxnType(String caseloadId, String deductionType,String modifyUserId) {
		final String sql = getQuery("OTMALPRO_UPDATECASELOADDEDUCTIONPROFILES_WITHOUTTXNTYPE");
		namedParameterJdbcTemplate.update(sql, createParams("caseloadId",caseloadId,"deductionType",deductionType,"modifyUserId",modifyUserId));
	}

	@Override
	public String getfromBalTypes(String deductionType) {
		final String sql = getQuery("OTMALPRO_FROM_BALANCE_TYPE");
		String desc=null;
		try{
		desc=namedParameterJdbcTemplate.queryForObject(sql, createParams("deductionType",deductionType),String.class);
		} catch(Exception e){
			logger.error("getfromBalType",e);
			
		}
		return desc;
	}
	public String allocTypeValidation(final String allocType,String caseloadId) {
		final String sql = getQuery("OTMALPRO_ALLOCTYPE_VALIDATION");
		String returnObj = "N";
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams("allocType", allocType,"caseloadId",caseloadId), String.class);
		} catch (Exception e) {
			return returnObj;
		}
		return returnObj;
	}
}

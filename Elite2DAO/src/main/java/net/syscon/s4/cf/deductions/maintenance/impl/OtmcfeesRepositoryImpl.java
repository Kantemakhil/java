package net.syscon.s4.cf.deductions.maintenance.impl;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.cf.deductions.maintenance.OtmcfeesRepository;
import net.syscon.s4.cf.deductions.maintenance.beans.TieredTransactionFeeAmounts;
import net.syscon.s4.cf.deductions.maintenance.beans.TransactionFeeDetails;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.AccountCodes;
import net.syscon.s4.im.beans.Corporates;
import net.syscon.s4.im.beans.SysDual;
import net.syscon.s4.inmate.beans.CaseloadDeductionProfiles;
import net.syscon.s4.inmate.beans.DeductionTypes;

/**
 * Class OtmcfeesRepositoryImpl
 * 
 */
@Repository
public class OtmcfeesRepositoryImpl extends RepositoryBase implements OtmcfeesRepository {

	private static Logger logger = LogManager.getLogger(OtmcfeesRepositoryImpl.class.getName());

	/**
	 * Creates new OtmcfeesRepositoryImpl class Object
	 */
	public OtmcfeesRepositoryImpl() {
		//constructor
	}

	private final Map<String, FieldMapper> cseloProfMapng = new ImmutableMap.Builder<String, FieldMapper>()
			.put("PERCENTAGE", new FieldMapper("percentage"))
			.put("CO_LIMIT_AMOUNT", new FieldMapper("coLimitAmount"))
			.put("MAX_RECURSIVE_AMOUNT", new FieldMapper("maxRecursiveAmount"))
			.put("DELAY_RECAPTURE", new FieldMapper("delayRecapture"))
			.put("FIFO_FLAG", new FieldMapper("fifoFlag"))
			.put("INTERNAL_PRIORITY_NO", new FieldMapper("internalPriorityNo"))
			.put("SEAL_FLAG", new FieldMapper("sealFlag"))
			.put("CREATE_DATETIME", new FieldMapper("createDatetime"))
			.put("EFFECTIVE_DATE", new FieldMapper("effectiveDate"))
			.put("MODIFY_DATETIME", new FieldMapper("modifyDatetime"))
			.put("PAYEE_PERSON_ID", new FieldMapper("payeePersonId"))
			.put("CO_CREDIT_WHEN_INDIGENT_FLAG", new FieldMapper("coCreditWhenIndigentFlag"))
			.put("CREATE_USER_ID", new FieldMapper("createUserId"))
			.put("ACTIVE_FLAG", new FieldMapper("activeFlag"))
			.put("MAX_TOTAL_AMOUNT", new FieldMapper("maxTotalAmount"))
			.put("MODIFY_USER_ID", new FieldMapper("modifyUserId"))
			.put("MINIMUM_TRUST_BALANCE", new FieldMapper("minimumTrustBalance"))
			.put("LIST_SEQ", new FieldMapper("listSeq"))
			.put("FLAT_RATE", new FieldMapper("flatRate"))
			.put("CATEGORY_TYPE", new FieldMapper("categoryType"))
			.put("CASELOAD_ID", new FieldMapper("caseloadId"))
			.put("MAX_MONTHLY_AMOUNT", new FieldMapper("maxMonthlyAmount"))
			.put("MODIFY_DATE", new FieldMapper("modifyDate"))
			.put("COMM_CONDITION_TYPE", new FieldMapper("commConditionType"))
			.put("DEDUCTION_TYPE", new FieldMapper("deductionType"))
			.put("FO_AL_ALL_OFFENDER_FLAG", new FieldMapper("foAlAllOffenderFlag"))
			.put("INDIGENT_MANDATORY_FLAG", new FieldMapper("indigentMandatoryFlag"))
			.put("COMM_CONDITION_CODE", new FieldMapper("commConditionCode"))
			.put("ACCOUNT_CODE", new FieldMapper("accountCode"))
			.put("PAYEE_CORPORATE_ID", new FieldMapper("payeeCorporateId"))
			.put("EXTERNAL_PRIORITY_NO", new FieldMapper("externalPriorityNo"))
			.put("EXPIRY_DATE", new FieldMapper("expiryDate"))
			.put("nbtAccountCode", new FieldMapper("nbtAccountCode"))
			.build();
	private final Map<String, FieldMapper> deduTypeMapng = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DEDUCTION_TYPE", new FieldMapper("deductionType"))
			.put("ACTIVE_FLAG", new FieldMapper("activeFlag"))
			.put("DEDUCTION_DESC", new FieldMapper("deductionDesc"))
			.put("CASELOAD_TYPE", new FieldMapper("caseloadType"))
			.put("MODE", new FieldMapper("mode"))
			.put("CASELOAD_CODE", new FieldMapper("caseloadCode"))
			.put("DEDUCTION_CATEGORY", new FieldMapper("deductionCategory"))
			.put("RECEIPT_DEDUCTION_TYPE", new FieldMapper("receiptDeductionType"))
			.build();
	private final Map<String, FieldMapper> tranFeeDeMapng = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CASELOAD_ID", new FieldMapper("caseloadId"))
			.put("MODIFY_DATE", new FieldMapper("modifyDate"))
			.put("CREATE_USER_ID", new FieldMapper("createUserId"))
			.put("SEAL_FLAG", new FieldMapper("sealFlag"))
			.put("CREATE_DATETIME", new FieldMapper("createDatetime"))
			.put("DEDUCTION_TYPE", new FieldMapper("deductionType"))
			.put("MODIFY_USER_ID", new FieldMapper("modifyUserId"))
			.put("LIST_SEQ", new FieldMapper("listSeq"))
			.put("MODIFY_DATETIME", new FieldMapper("modifyDatetime"))
			.put("RECEIPT_DEDUCTION_TYPE", new FieldMapper("receiptDeductionType"))
			.build();
	private final Map<String, FieldMapper> accountCodesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("ACCOUNT_NAME", new FieldMapper("accountName"))
			.put("CASELOAD_TYPE", new FieldMapper("caseloadType"))
			.put("ACCOUNT_CODE", new FieldMapper("accountCode"))
			.put("CODE", new FieldMapper("code"))
			.build();
	private final Map<String, FieldMapper> tranFeeAmnMapng = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CASELOAD_ID", new FieldMapper("caseloadId"))
			.put("CREATE_USER_ID", new FieldMapper("createUserId"))
			.put("SEAL_FLAG", new FieldMapper("sealFlag"))
			.put("CREATE_DATETIME", new FieldMapper("createDatetime"))
			.put("DEDUCTION_TYPE", new FieldMapper("deductionType"))
			.put("PERCENTAGE", new FieldMapper("percentage"))
			.put("MODIFY_USER_ID", new FieldMapper("modifyUserId"))
			.put("TO_AMOUNT", new FieldMapper("toAmount"))
			.put("MODIFY_DATETIME", new FieldMapper("modifyDatetime"))
			.put("FROM_AMOUNT", new FieldMapper("fromAmount"))
			.put("FEE_AMOUNT", new FieldMapper("feeAmount"))
			.build();
	private final Map<String, FieldMapper> corporatesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CORPORATE_NAME", new FieldMapper("corporateName"))
			.put("PAYEE_CORPORATE_ID", new FieldMapper("payeeCorporateId"))
			.build();

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
		final String sql = getQuery("OTMCFEES_CSLDDP_FIND_CASELOAD_DEDUCTION_PROFILES");
		final RowMapper<CaseloadDeductionProfiles> rowMaper = Row2BeanRowMapper
				.makeMapping(sql, CaseloadDeductionProfiles.class, cseloProfMapng);
		String preparedSql = null;
		final MapSqlParameterSource inParameterSource = new MapSqlParameterSource();
		final StringBuffer sqlQuery = new StringBuffer();
		sqlQuery.append(sql);
		if (objSearchDao != null) {
			if (objSearchDao.getDeductionType() != null && !objSearchDao.getDeductionType().isEmpty()
					&& !("".equals(objSearchDao.getDeductionType().trim()))) {
				sqlQuery.append(" DEDUCTION_TYPE = :DEDUCTION_TYPE" + " AND ");
				inParameterSource.addValue("DEDUCTION_TYPE", objSearchDao.getDeductionType().trim());
			}
			if (objSearchDao.getAccountCode() != null) {
				sqlQuery.append(" ACCOUNT_CODE = :ACCOUNT_CODE" + " AND ");
				inParameterSource.addValue("ACCOUNT_CODE", objSearchDao.getAccountCode());
			}
			if (objSearchDao.getPayeeCorporateId() != null) {
				sqlQuery.append(" PAYEE_CORPORATE_ID = :PAYEE_CORPORATE_ID" + " AND ");
				inParameterSource.addValue("PAYEE_CORPORATE_ID", objSearchDao.getPayeeCorporateId());
			}
			
			sqlQuery.append("CASELOAD_ID = :CASELOAD_ID   AND DEDUCTION_TYPE IN ( SELECT DT.deduction_type FROM DEDUCTION_TYPES DT WHERE DT.DEDUCTION_CATEGORY = 'CTF' AND DT.CASELOAD_CODE IN ('COMM',:CASELOADTYPE) )");
			inParameterSource.addValue("CASELOAD_ID", objSearchDao.getCaseloadId());
			inParameterSource.addValue("CASELOADTYPE", objSearchDao.getCaseloadType());

		}
		preparedSql = sqlQuery.toString().trim();
		if (preparedSql.endsWith("WHERE")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 5);
		}
		if (preparedSql.endsWith("AND")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 3);
		}
		preparedSql = preparedSql + " ORDER BY FO_AL_ALL_OFFENDER_FLAG";
		return namedParameterJdbcTemplate.query(preparedSql, inParameterSource, rowMaper);

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
			final List<CaseloadDeductionProfiles> list) {
		final String sql = getQuery("OTMCFEES_CSLDDP_INSERT_CASELOAD_DEDUCTION_PROFILES");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final CaseloadDeductionProfiles object : list) {
			parameters.add(new BeanPropertySqlParameterSource(object));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));

		if (list.size() == returnArray.length) {
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
			final List<CaseloadDeductionProfiles> list) {
		final String sql = getQuery("OTMCFEES_CSLDDP_UPDATE_CASELOAD_DEDUCTION_PROFILES");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final CaseloadDeductionProfiles object : list) {
			parameters.add(new BeanPropertySqlParameterSource(object));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (list.size() == returnArray.length) {
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
			final List<CaseloadDeductionProfiles> list) {
		final String sql = getQuery("OTMCFEES_CSLDDP_DELETE_CASELOAD_DEDUCTION_PROFILES");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final CaseloadDeductionProfiles object : list) {
			parameters.add(new BeanPropertySqlParameterSource(object));
		}
		try {
			String tableName = "CASELOAD_DEDUCTION_PROFILES";
			String whereClause = "CASELOAD_ID=:caseloadId AND DEDUCTION_TYPE=:deductionType";
			batchUpdatePreDeletedRows(tableName, whereClause , parameters);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method csldDpDeleteCaseloadDeductionProfiles", e);
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));

		if (list.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            TransactionFeeDetails
	 *
	 * @return List<TransactionFeeDetails>
	 *
	 * @throws SQLException
	 */
	public List<TransactionFeeDetails> tranFdExecuteQuery(final TransactionFeeDetails objSearchDao) {
		final String sql = getQuery("OTMCFEES_TRANFD_FIND_TRANSACTION_FEE_DETAILS");
		final RowMapper<TransactionFeeDetails> rowMaper = Row2BeanRowMapper.makeMapping(sql,
				TransactionFeeDetails.class, tranFeeDeMapng);
		final ArrayList<TransactionFeeDetails> returnList = (ArrayList<TransactionFeeDetails>) namedParameterJdbcTemplate
				.query(sql, createParams("caseloadId", objSearchDao.getCaseloadId(), "deductionType",
						objSearchDao.getDeductionType()), rowMaper);
		return returnList;
	}

	/**
	 * This method is used to insert the records in the data base tables based
	 * on
	 *
	 * @param lstTransactionFeeDetails
	 *            List<TransactionFeeDetails>
	 *
	 * @return List<Integer>
	 *
	 * @throws SQLException
	 */
	public Integer tranFdInsertTransactionFeeDetails(final List<TransactionFeeDetails> list) {
		final String sql = getQuery("OTMCFEES_TRANFD_INSERT_TRANSACTION_FEE_DETAILS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final TransactionFeeDetails object : list) {
			parameters.add(new BeanPropertySqlParameterSource(object));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (list.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstTransactionFeeDetails
	 *            List<TransactionFeeDetails>
	 *
	 * @throws SQLException
	 */
	public Integer tranFdUpdateTransactionFeeDetails(final List<TransactionFeeDetails> list) {
		final String sql = getQuery("OTMCFEES_TRANFD_UPDATE_TRANSACTION_FEE_DETAILS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final TransactionFeeDetails object : list) {
			parameters.add(new BeanPropertySqlParameterSource(object));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (list.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to delete records from data base tables based on
	 *
	 * @param lstTransactionFeeDetails
	 *            List<TransactionFeeDetails>
	 *
	 * @throws SQLException
	 */
	public Integer tranFdDeleteTransactionFeeDetails(final List<TransactionFeeDetails> list) {
		final String sql = getQuery("OTMCFEES_TRANFD_DELETE_TRANSACTION_FEE_DETAILS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final TransactionFeeDetails object : list) {
			parameters.add(new BeanPropertySqlParameterSource(object));
		}
		try {
			String tableName = "TRANSACTION_FEE_DETAILS";
			String whereClause = "ROW_ID = :rowId";
			batchUpdatePreDeletedRows(tableName, whereClause , parameters);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method tranFdDeleteTransactionFeeDetails", e);
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (list.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            TieredTransactionFeeAmounts
	 *
	 * @return List<TieredTransactionFeeAmounts>
	 *
	 * @throws SQLException
	 */
	public List<TieredTransactionFeeAmounts> tierTfaExecuteQuery(final TieredTransactionFeeAmounts objSearchDao) {
		final String sql = getQuery("OTMCFEES_TIERTFA_FIND_TIERED_TRANSACTION_FEE_AMOUNTS");
		final RowMapper<TieredTransactionFeeAmounts> rowMaper = Row2BeanRowMapper
				.makeMapping(sql, TieredTransactionFeeAmounts.class, tranFeeAmnMapng);
		final ArrayList<TieredTransactionFeeAmounts> returnList = (ArrayList<TieredTransactionFeeAmounts>) namedParameterJdbcTemplate
				.query(sql, createParams("caseloadId", objSearchDao.getCaseloadId(), "deductionType",
						objSearchDao.getDeductionType()), rowMaper);
		return returnList;
	}

	/**
	 * This method is used to insert the records in the data base tables based
	 * on
	 *
	 * @param lstTieredTransactionFeeAmounts
	 *            List<TieredTransactionFeeAmounts>
	 *
	 * @return List<Integer>
	 *
	 * @throws SQLException
	 */
	public Integer tierTfaInsertTieredTransactionFeeAmounts(
			final List<TieredTransactionFeeAmounts> list) {
		final String sql = getQuery("OTMCFEES_TIERTFA_INSERT_TIERED_TRANSACTION_FEE_AMOUNTS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final TieredTransactionFeeAmounts object : list) {
			parameters.add(new BeanPropertySqlParameterSource(object));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (list.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstTieredTransactionFeeAmounts
	 *            List<TieredTransactionFeeAmounts>
	 *
	 * @throws SQLException
	 */
	public Integer tierTfaUpdateTieredTransactionFeeAmounts(
			final List<TieredTransactionFeeAmounts> list) {
		final String sql = getQuery("OTMCFEES_TIERTFA_UPDATE_TIERED_TRANSACTION_FEE_AMOUNTS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final TieredTransactionFeeAmounts object : list) {
			parameters.add(new BeanPropertySqlParameterSource(object));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (list.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to delete records from data base tables based on
	 *
	 * @param lstTieredTransactionFeeAmounts
	 *            List<TieredTransactionFeeAmounts>
	 *
	 * @throws SQLException
	 */
	public Integer tierTfaDeleteTieredTransactionFeeAmounts(
			final List<TieredTransactionFeeAmounts> list) {
		final String sql = getQuery("OTMCFEES_TIERTFA_DELETE_TIERED_TRANSACTION_FEE_AMOUNTS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final TieredTransactionFeeAmounts objectNew : list) {
			parameters.add(new BeanPropertySqlParameterSource(objectNew));
		}
		try {
			String tableName = "TIERED_TRANSACTION_FEE_AMOUNTS";
			String whereClause = "ROW_ID = :rowId";
			batchUpdatePreDeletedRows(tableName, whereClause , parameters);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method tierTfaDeleteTieredTransactionFeeAmounts", e);
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (list.size() == returnArray.length) {
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
		final String sql = getQuery("OTMCFEES_FIND_CGFKCSLDDPPAYEECORPORATEI");
		final RowMapper<Corporates> rowmaper = Row2BeanRowMapper.makeMapping(sql, Corporates.class,
				corporatesMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), rowmaper);
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<DeductionTypes> cgfkTranFdReceiptDeductionRecordGroup(final String caseLoadType) {
		final String sql = getQuery("OTMCFEES_FIND_CGFKTRANFDRECEIPTDEDUCTION");
		final RowMapper<DeductionTypes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, DeductionTypes.class,
				deduTypeMapng);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams("caseLoadType", caseLoadType), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<DeductionTypes> cgfkCsldDpDeductionTypeRecordGroup(final String caseLoadType) {
		final String sql = getQuery("OTMCFEES_FIND_CGFKCSLDDPDEDUCTIONTYPE");
		final RowMapper<DeductionTypes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, DeductionTypes.class,
				deduTypeMapng);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams("caseLoadType", caseLoadType), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<AccountCodes> cgfkCsldDpAccountCodeRecordGroup(final String caseLoadType) {
		final String sql = getQuery("OTMCFEES_FIND_CGFKCSLDDPACCOUNTCODE");
		final RowMapper<AccountCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, AccountCodes.class,
				accountCodesMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams("caseLoadType", caseLoadType), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
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
	public Corporates cgfkchkCsldDpDedprofCorp(final Corporates paramBean) {
		final String sql = getQuery("OTMCFEES_CGFKCHK_CSLD_DP_DEDPROF_CORP_");
		final RowMapper<Corporates> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, Corporates.class,
				corporatesMapping);
		return namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfkchkCsldDpDedprofAcCo
	 *
	 * @param params
	 *
	 */
	public AccountCodes cgfkchkCsldDpDedprofAcCo(final AccountCodes paramBean) {
		final String sql = getQuery("OTMCFEES_CGFKCHK_CSLD_DP_DEDPROF_AC_CO");
		final RowMapper<AccountCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, AccountCodes.class,
				accountCodesMapping);
		return namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfkchkCsldDpDedprofDedty
	 *
	 * @param params
	 *
	 */
	public DeductionTypes cgfkchkCsldDpDedprofDedty(final DeductionTypes paramBean) {
		final String sql = getQuery("OTMCFEES_CGFKCHK_CSLD_DP_DEDPROF_DEDTY");
		final RowMapper<DeductionTypes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, DeductionTypes.class,
				deduTypeMapng);
		return  namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
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
		final String sql = getQuery("OTMCFEES_CGWHEN_NEW_FORM_INSTANCE");
		final RowMapper<SysDual> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, SysDual.class,
				deduTypeMapng);
		final ArrayList<SysDual> returnList = (ArrayList<SysDual>) namedParameterJdbcTemplate.query(sql, createParams(),
				columnRowMapper);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfkchkTranFdTranFdDed
	 *
	 * @param params
	 *
	 */
	public DeductionTypes cgfkchkTranFdTranFdDed(final DeductionTypes paramBean) {
		final String sql = getQuery("OTMCFEES_CGFKCHK_TRAN_FD_TRAN_FD_DED_T");
		final RowMapper<DeductionTypes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, DeductionTypes.class,
				deduTypeMapng);
		return namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
	}

	/**
	 * to validate the check box in times grid
	 */
	@Override
	public String getExternalPriorityNumber(final String caseloadId) {
		final String sql = getQuery("OTMSFEES_GET_EXTERNAL_PRIORITY_NUMBER");
		String returnValue = null;
		try {
			returnValue = namedParameterJdbcTemplate.queryForObject(sql, createParams("caseloadId", caseloadId),
					String.class);
		} catch (Exception e) {
			returnValue = "1";
			return returnValue;
		}
		return returnValue;
	}

	public Integer getOverLapCount(final TieredTransactionFeeAmounts paramBean) {
		final String sql = getQuery("OTMCFEES_GET_OVERLAP_COUNT");
		Integer returnList = 0;
		returnList = namedParameterJdbcTemplate.queryForObject(sql,
				createParams("pFlag", paramBean.getpFlag(), "caseloadId", paramBean.getCaseloadId(), "deductionType",
						paramBean.getDeductionType(), "fromAmount", paramBean.getFromAmount(), "toAmount",
						paramBean.getToAmount()),
				Integer.class);
		return returnList;
	}

	@Override
	public String getCorporateName(final String payeeCorporateId) {
		final String sql = getQuery("OTMCFEES_CGFKCHK_CSLD_DP_DEDPROF_CORP");
		String returnValue = null;
		try {
			returnValue = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("payeeCorporateId", payeeCorporateId != null ?new BigDecimal(payeeCorporateId):null), String.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return returnValue;
	}
}

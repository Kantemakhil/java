package net.syscon.s4.inmate.trust.trustaccounts.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
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
import org.springframework.transaction.support.TransactionOperations;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.beans.TransactionTypes;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.AccountCodes;
import net.syscon.s4.im.beans.Dual;
import net.syscon.s4.im.beans.Offenders;
import net.syscon.s4.im.beans.OmsModules;
import net.syscon.s4.inmate.beans.OffenderSubAccounts;
import net.syscon.s4.inmate.beans.OffenderTrustAccountsTemp;
import net.syscon.s4.inmate.beans.OffenderWorks;
import net.syscon.s4.inmate.trust.trustaccounts.OtdclinaRepository;
import net.syscon.s4.inst.automatedcounts.beans.LockedModules;
import oracle.jdbc.OracleTypes;

/**
 * Class OtdclinaRepositoryImpl
 */
@Repository
public class OtdclinaRepositoryImpl extends RepositoryBase implements OtdclinaRepository {

	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OtdclinaRepositoryImpl.class.getName());

	private final Map<String, FieldMapper> transactionTypesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("1", 							new FieldMapper("1"))
			.put("SUM(BALANCE)", 				new FieldMapper("sum(balance)"))
			.put("45)", 						new FieldMapper("45)"))
			.put("4", 							new FieldMapper("4"))
			.put("DESCRIPTION", 				new FieldMapper("description"))
			.put("TRUST_ACCOUNT_COD", 			new FieldMapper("trustAccountCod"))
			.put("ROOT_OFFENDER_ID", 			new FieldMapper("rootOffenderId"))
			.put("SUBST", 						new FieldMapper("subst"))
			.put("SUB_ACCOUNT_TYPE", 			new FieldMapper("subAccountType"))
			.put("CASELOAD_ID", 				new FieldMapper("caseloadId"))
			.put("SUBSTR(", 					new FieldMapper("substr("))
			.put("BALANC", 						new FieldMapper("balanc")).build();
	private final Map<String, FieldMapper> offenderSubAccountsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("1", 							new FieldMapper("1"))
			.put("SUM(BALANCE)", 				new FieldMapper("sum(balance)"))
			.put("45)", 						new FieldMapper("45)"))
			.put("4", 							new FieldMapper("4"))
			.put("DESCRIPTION", 				new FieldMapper("description"))
			.put("TRUST_ACCOUNT_COD", 			new FieldMapper("trustAccountCod"))
			.put("ROOT_OFFENDER_ID", 			new FieldMapper("rootOffenderId"))
			.put("SUBST", 						new FieldMapper("subst"))
			.put("SUB_ACCOUNT_TYPE", 			new FieldMapper("subAccountType"))
			.put("CASELOAD_ID", 				new FieldMapper("caseloadId"))
			.put("SUBSTR(", 					new FieldMapper("substr("))
			.put("BALANC", 						new FieldMapper("balanc")).build();
	private final Map<String, FieldMapper> offenderTrustAccountsTempMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CREATE_USER_ID", 				new FieldMapper("createUserId"))
			.put("MODIFY_USER_ID", 				new FieldMapper("modifyUserId"))
			.put("LIST_SEQ", 					new FieldMapper("listSeq"))
			.put("HOLD_BALANCE", 				new FieldMapper("holdBalance"))
			.put("CURRENT_BALANCE", 			new FieldMapper("currentBalance"))
			.put("OFFENDER_ID", 				new FieldMapper("offenderId"))
			.put("SESSION_ID", 					new FieldMapper("sessionId"))
			.put("LAST_NAME", 					new FieldMapper("lastName"))
			.put("CASELOAD_ID", 				new FieldMapper("caseloadId"))
			.put("MODIFY_DATE", 				new FieldMapper("modifyDate"))
			.put("SEAL_FLAG", 					new FieldMapper("sealFlag"))
			.put("ACCOUNT_CLOSED_FLAG", 		new FieldMapper("accountClosedFlag"))
			.put("AGY_LOC_ID", 					new FieldMapper("agyLocId"))
			.put("CREATE_DATETIME", 			new FieldMapper("createDateTime"))
			.put("MODIFY_DATETIME", 			new FieldMapper("modifyDateTime"))
			.put("NOTIFY_DATE",					new FieldMapper("notifyDate"))
			.put("MOVEMENT_DATE", 				new FieldMapper("movementDate"))
			.put("FIRST_NAME", 					new FieldMapper("firstName"))
			.build();
	private final Map<String, FieldMapper> offenderWorksMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("'1'",							new FieldMapper("'1'"))
			.build();
	private final Map<String, FieldMapper> lockedModulesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("Y", 							new FieldMapper("y"))
			.put("TRUST_ACCOUNT_CODE", 			new FieldMapper("trustAccountCode"))
			.put("BALANCE", 					new FieldMapper("balance"))
			.put("MODIFY_USER_ID", 				new FieldMapper("modifyUserId"))
			.build();
	private final Map<String, FieldMapper> offendersMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("1", 							new FieldMapper("1"))
			.put("SUM(BALANCE)", 				new FieldMapper("sum(balance)"))
			.put("45)", 						new FieldMapper("45)"))
			.put("4", 							new FieldMapper("4"))
			.put("DESCRIPTION", 				new FieldMapper("description"))
			.put("TRUST_ACCOUNT_COD", 			new FieldMapper("trustAccountCod"))
			.put("ROOT_OFFENDER_ID", 			new FieldMapper("rootOffenderId"))
			.put("SUBST", 						new FieldMapper("subst"))
			.put("SUB_ACCOUNT_TYPE", 			new FieldMapper("subAccountType"))
			.put("CASELOAD_ID", 				new FieldMapper("caseloadId"))
			.put("SUBSTR(", 					new FieldMapper("substr("))
			.put("BALANC", 						new FieldMapper("balanc"))
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
	private final Map<String, FieldMapper> mMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DESCRIPTION", 				new FieldMapper("description ")).build();
	private final Map<String, FieldMapper> transactionOperationsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CASELOAD_ID", 				new FieldMapper("caseloadId")).build();

	/**
	 * Creates new OtdclinaRepositoryImpl class Object
	 */
	public OtdclinaRepositoryImpl() {
		// OtdclinaRepositoryImpl
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
		final String sql = getQuery("OTDCLINA_SYSPFL_FIND_SYSTEM_PROFILES");
		final RowMapper<SystemProfiles> SystemProfilesRowMapper = Row2BeanRowMapper.makeMapping(sql,
				SystemProfiles.class, systemProfilesMapping);
		List<SystemProfiles> returnList = new ArrayList<>();
		returnList = namedParameterJdbcTemplate.query(sql, createParams(), SystemProfilesRowMapper);
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
		final String sql = getQuery("OTDCLINA_SYSPFL_INSERT_SYSTEM_PROFILES");
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
		final String sql = getQuery("OTDCLINA_SYSPFL_UPDATE_SYSTEM_PROFILES");
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
		final String sql = getQuery("OTDCLINA_SYSPFL_DELETE_SYSTEM_PROFILES");
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
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            OffenderTrustAccountsTemp
	 *
	 * @return List<OffenderTrustAccountsTemp>
	 *
	 * 
	 */
	public List<OffenderTrustAccountsTemp> offTracExecuteQuery(final OffenderTrustAccountsTemp objSearchDao) {
		final String sql = getQuery("OTDCLINA_OFFTRAC_FIND_OFFENDER_TRUST_ACCOUNTS_TEMP");
		final RowMapper<OffenderTrustAccountsTemp> OffenderTrustAccountsTempRowMapper = Row2BeanRowMapper
				.makeMapping(sql, OffenderTrustAccountsTemp.class, offenderTrustAccountsTempMapping);
		List<OffenderTrustAccountsTemp> returnList = new ArrayList<>();
		returnList = namedParameterJdbcTemplate.query(sql, createParams("sessionId", objSearchDao.getSessionId()),
				OffenderTrustAccountsTempRowMapper);
		return returnList;
	}

	/**
	 * This method is used to insert the records in the data base tables based
	 * on
	 *
	 * @param lstOffenderTrustAccountsTemp
	 *            List<OffenderTrustAccountsTemp>
	 *
	 * @return List<Integer>
	 *
	 * 
	 */
	public Integer offTracInsertOffenderTrustAccountsTemp(
			final List<OffenderTrustAccountsTemp> lstOffenderTrustAccountsTemp) {
		final String sql = getQuery("OTDCLINA_OFFTRAC_INSERT_OFFENDER_TRUST_ACCOUNTS_TEMP");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffenderTrustAccountsTemp offenderTrustAccountsTemp : lstOffenderTrustAccountsTemp) {
			parameters.add(new BeanPropertySqlParameterSource(offenderTrustAccountsTemp));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstOffenderTrustAccountsTemp.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstOffenderTrustAccountsTemp
	 *            List<OffenderTrustAccountsTemp>
	 *
	 * 
	 */
	public Integer offTracUpdateOffenderTrustAccountsTemp(
			final List<OffenderTrustAccountsTemp> lstOffenderTrustAccountsTemp) {
		final String sql = getQuery("OTDCLINA_OFFTRAC_UPDATE_OFFENDER_TRUST_ACCOUNTS_TEMP");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffenderTrustAccountsTemp offenderTrustAccountsTemp : lstOffenderTrustAccountsTemp) {
			parameters.add(new BeanPropertySqlParameterSource(offenderTrustAccountsTemp));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstOffenderTrustAccountsTemp.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to insert the records in the data base tables based
	 * on
	 *
	 * @param lstOffenderTransactions
	 *            List<OffenderTransactions>
	 *
	 * @return List<Integer>
	 */
	public Integer updateOffenderTrustAccounts(Long offenderId, Date notifyDate, String caseloadId) {
		final String sql = getQuery("OTDCLINA_OFFENDER_TRUST_ACCOUNTS");
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("OFFENDERID", offenderId);
		paramMap.put("NOTIFYDATE", notifyDate);
		paramMap.put("CASELOADID", caseloadId);
		Integer returnArray = null;
		returnArray = namedParameterJdbcTemplate.update(sql, paramMap);
		if (returnArray != 0) {
			return returnArray;
		}
		return returnArray;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<ReferenceCodes> selectMethodRgRecordGroup() {
		final String sql = getQuery("OTDCLINA_FIND_SELECTMETHODRG");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (Exception e) {
			return Collections.emptyList();
		}
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * offTracKeyExeqryKEY-EXEQRY
	 *
	 * @param params
	 *
	 */
	public OffenderTrustAccountsTemp offTracKeyExeqry(final OffenderTrustAccountsTemp paramBean) {
		final OffenderTrustAccountsTemp returnObj;
		final String sql = getQuery("OTDCLINA_OFF_TRAC_KEYEXEQRY_KEYEXEQRY");
		final RowMapper<OffenderTrustAccountsTemp> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderTrustAccountsTemp.class, offenderTrustAccountsTempMapping);
		returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * offTracKeyEntqry
	 *
	 * @param params
	 *
	 */
	public OffenderTrustAccountsTemp offTracKeyEntqry(final OffenderTrustAccountsTemp paramBean) {
		final OffenderTrustAccountsTemp returnObj;
		final String sql = getQuery("OTDCLINA_OFF_TRAC_KEYENTQRY");
		final RowMapper<OffenderTrustAccountsTemp> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderTrustAccountsTemp.class, offenderTrustAccountsTempMapping);
		returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * otdclinaWhenNewFormInstance
	 *
	 * @param params
	 *
	 */
	public Object otdclinaWhenNewFormInstance(final Dual paramBean) {
		final Dual returnObj;
		final String sql = getQuery("OTDCLINA_OTDCLINA_WHENNEWFORMINSTANCE");
		final RowMapper<Dual> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, Dual.class, mMapping);
		returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
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
	public OmsModules createFormGlobals(final OmsModules paramBean) {
		final OmsModules returnObj;
		final String sql = getQuery("OTDCLINA_CREATE_FORM_GLOBALS_CREATE_FORM_GLOBALS");
		final RowMapper<OmsModules> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, OmsModules.class, mMapping);
		returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgrichkOffenderTrustAccoun
	 *
	 * @param params
	 *
	 */
	public List<OffenderWorks> cgrichkOffenderTrustAccoun(final OffenderWorks paramBean) {
		final String sql = getQuery("OTDCLINA_CGRICHK_OFFENDER_TRUST_ACCOUN");
		final RowMapper<OffenderWorks> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, OffenderWorks.class,
				offenderWorksMapping);
		List<OffenderWorks> returnList = new ArrayList<>();
		returnList = (ArrayList<OffenderWorks>) namedParameterJdbcTemplate.query(sql, createParams(), columnRowMapper);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * clearZeroBalanceAccounts
	 *
	 * @param params
	 *
	 */
	public Offenders clearZeroBalanceAccounts(final Offenders paramBean) {
		final Offenders returnObj;
		final String sql = getQuery("OTDCLINA_CLEAR_ZERO_BALANCE_ACCOUNTS");
		final RowMapper<Offenders> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, Offenders.class,
				offendersMapping);
		returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * clearZeroBalanceAccounts
	 *
	 * @param params
	 *
	 */
	public TransactionTypes clearZeroBalanceAccounts(final TransactionTypes paramBean) {
		final TransactionTypes returnObj;
		final String sql = getQuery("OTDCLINA_CLEAR_ZERO_BALANCE_ACCOUNTS");
		final RowMapper<TransactionTypes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, TransactionTypes.class,
				transactionTypesMapping);
		returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * closeAccount
	 *
	 * @param params
	 *
	 */
	public BigDecimal closeAccount(final Long pOffid) {
		BigDecimal returnObj = null;
		final String sql = getQuery("OTDCLINA_CLOSE_ACCOUNT");
		try {
		returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams("POFFID", pOffid), BigDecimal.class);
		} catch (Exception e) {
			return returnObj;
		}
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * closeAccountroot_offender_id_c
	 *
	 * @param params
	 *
	 */
	public Integer closeAccountRootOffIdCur() {
		final String sql = getQuery("OTDCLINA_CLOSE_ACCOUNT_ROOT_OFFENDER_ID_C");
		Integer returnList = null;
		returnList = namedParameterJdbcTemplate.queryForObject(sql, createParams(), Integer.class);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * zeroAmount
	 *
	 * @param params
	 *
	 */
	public List<AccountCodes> zeroAmount(final AccountCodes paramBean) {
		final String sql = getQuery("OTDCLINA_ZERO_AMOUNT_ZERO_AMOUNT");
		final RowMapper<AccountCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, AccountCodes.class,
				mMapping);
		List<AccountCodes> returnList = new ArrayList<>();
		returnList = namedParameterJdbcTemplate.query(sql, createParams(), columnRowMapper);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * clearInactiveAccountsCLEAR_INACTIVE_ACCOUNTS
	 *
	 * @param params
	 *
	 */
	public Object clearInactiveAccounts(final OffenderSubAccounts paramBean) {
		final OffenderSubAccounts returnObj;
		final String sql = getQuery("OTDCLINA_CLEAR_INACTIVE_ACCOUNTS_CLEAR_INACTIVE_ACCOUNTS");
		final RowMapper<OffenderSubAccounts> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderSubAccounts.class, offenderSubAccountsMapping);
		returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * checkBalanceTally
	 *
	 * @param params
	 *
	 */
	public OffenderSubAccounts checkBalanceTally(final OffenderSubAccounts paramBean) {
		final OffenderSubAccounts returnObj;
		final String sql = getQuery("OTDCLINA_CHECK_BALANCE_TALLY");
		final RowMapper<OffenderSubAccounts> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderSubAccounts.class, offenderSubAccountsMapping);
		returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * checkSetupBeforeTransfer
	 *
	 * @param params
	 *
	 */
	public TransactionOperations checkSetupBeforeTransfer(final TransactionOperations paramBean) {
		final TransactionOperations returnObj;
		final String sql = getQuery("OTDCLINA_CHECK_SETUP_BEFORE_TRANSFER");
		final RowMapper<TransactionOperations> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				TransactionOperations.class, transactionOperationsMapping);
		returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * checkLock
	 *
	 * @param params
	 *
	 */
	public List<LockedModules> checkLock() {
		final String sql = getQuery("OTDCLINA_CHECK_LOCK");
		final RowMapper<LockedModules> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, LockedModules.class,
				lockedModulesMapping);
		List<LockedModules> returnList = new ArrayList<LockedModules>();
		returnList = namedParameterJdbcTemplate.query(sql, createParams(), columnRowMapper);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * transferFundsToProPer
	 *
	 * @param params
	 *
	 */
	public Offenders transferFundsToProPer(final Offenders paramBean) {
		final Offenders returnObj;
		final String sql = getQuery("OTDCLINA_TRANSFER_FUNDS_TO_PRO_PER");
		final RowMapper<Offenders> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, Offenders.class,
				offendersMapping);
		returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
		return returnObj;
	}

	@Override
	public BigDecimal offTracPopulateTempTable(final OffenderTrustAccountsTemp searchRecord) {
		BigDecimal returnVal = null;
		Map<String, Object> returnObject = null;
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[20];
		sqlParameters = new SqlParameter[] { new SqlParameter("P_SELECTION_METHOD", OracleTypes.VARCHAR),
				new SqlParameter("P_CASELOAD_ID", OracleTypes.VARCHAR),
				new SqlParameter("P_SESSION_ID", OracleTypes.NUMBER), new SqlParameter("P_FROM_DATE", OracleTypes.DATE),
				new SqlParameter("P_TO_DATE", OracleTypes.DATE), new SqlParameter("P_USER", OracleTypes.VARCHAR),
				new SqlParameter("P_CONFIRM_FLAG", OracleTypes.VARCHAR),
				new SqlOutParameter("P_TOTAL_BALANCE", OracleTypes.NUMBER) };
		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("OTDCLINA").withProcedureName("POPULATE_TABLE").declareParameters(sqlParameters);
		inParamMap.put("P_SELECTION_METHOD", searchRecord.getAgyLocId());
		inParamMap.put("P_CASELOAD_ID", searchRecord.getCaseloadId());
		inParamMap.put("P_SESSION_ID", searchRecord.getSessionId());
		inParamMap.put("P_FROM_DATE", searchRecord.getCreateDateTime());
		inParamMap.put("P_TO_DATE", searchRecord.getModifyDateTime());
		inParamMap.put("P_USER", searchRecord.getLastName());
		inParamMap.put("P_CONFIRM_FLAG", searchRecord.getSealFlag());
		final MapSqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		try {
			returnObject = simpleJDBCCall.execute(inParameter);
			returnVal = (BigDecimal) returnObject.get("P_TOTAL_BALANCE");
		} catch (Exception e) {
		}
		return returnVal;
	}

	@Override
	public String offenderSubAccountsMax(final Long offenderId,String userId) {
		final String sql = getQuery("OTDCLINA_OFFENDER_SUB_ACCOUNTS_MAX");
		String returnObj = null;
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql,createParams("OFFENDERID", offenderId, "userId", userId),
					String.class);
		} catch (Exception e) {
			return returnObj;
		}
		return returnObj;
	}

	@Override
	public BigDecimal sumBalanceOffenderSubAccounts(final Long offenderId, final String userId) {
		final String sql = getQuery("OTDCLINA_SUM_BALANCE");
		BigDecimal returnObj = null;
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams("OFFENDERID", offenderId,"userId",userId),
					BigDecimal.class);
		} catch (Exception e) {
			return returnObj;
		}
		return returnObj;
	}

	@Override
	public BigDecimal findRootOffenderId(final Long offenderId) {
		final String sql = getQuery("OTDCLINA_ROOT_OFFENDER_ID");
		BigDecimal returnObj = null;
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams("OFFID", offenderId),
					BigDecimal.class);
		} catch (Exception e) {
			return returnObj;
		}
		return returnObj;
	}

	@Override
	public String lastNameFirstName(final BigDecimal rootOffenderId,final String userId) {
		final String sql = getQuery("OTDCLINA_FIRST_NAME_LAST_NAME");
		String returnObj = null;
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams("ROOTOFFENDERID", rootOffenderId, "userId", userId),
					String.class);
		} catch (Exception e) {
			return returnObj;
		}
		return returnObj;
	}

	@Override
	public String txnIdDescription(final String txnType) {
		final String sql = getQuery("OTDCLINA_DESCRIPTION_TXNTYPE");
		String returnObj = null;
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams("TXNTYPE", txnType), String.class);
		} catch (Exception e) {
			return returnObj;
		}
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 *
	 * @return Integer
	 */
	public Integer txnIdNextValData() {
		final String sql = getQuery("OTDCLINA_TXN_ID");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams(), Integer.class);
	}

	public Integer insertIntoOffenderTransaction(final Integer txnIdNextVal, final BigDecimal pTxnEntrySeq,
			final String caseloadId, final BigDecimal rootOffenderId, final BigDecimal offenderBookId,
			final String vTxnType, final String lvTxnEntryDesc, final BigDecimal pBalance, final Date transDate,
			final String subActType, final String txnPostingType) {
		Integer genSeq = 0;
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		inParamMap.put("p_trans_number", txnIdNextVal);
		inParamMap.put("p_trans_seq", pTxnEntrySeq);
		inParamMap.put("p_csld_id", caseloadId);
		inParamMap.put("p_off_id", rootOffenderId);
		inParamMap.put("p_off_book_id", offenderBookId);
		inParamMap.put("p_trans_post_type", txnPostingType);
		inParamMap.put("p_trans_type", vTxnType);
		inParamMap.put("p_trans_desc", lvTxnEntryDesc);
		inParamMap.put("p_trans_amount", pBalance);
		inParamMap.put("p_trans_date", transDate);
		inParamMap.put("p_sub_act_type", subActType);
		inParamMap.put("p_deduction_flag", null);
		inParamMap.put("p_pre_ded_amount", null);
		inParamMap.put("p_deduction_type", null);
		inParamMap.put("p_payee_corp_id", null);
		inParamMap.put("p_payee_person_id", null);
		inParamMap.put("p_info_number", null);
		inParamMap.put("p_slip_print_flag", "N");
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

	public Integer updateOffenderTransactions(final String payeeCode, final String txnTypeDescription,
			final Integer txnIdNextVal, final BigDecimal pTxnEntrySeq,String modifyUserId) {
		final String sql = getQuery("OTDCLINA_UPDATE_OFFENDER_TRANSACTIONS");
		Map<String, Object> inParamMap = new HashMap<String, Object>();
		inParamMap.put("PPAYEECODE", payeeCode);
		inParamMap.put("LVPAYEENAME", txnTypeDescription);
		inParamMap.put("PTXNNUM", txnIdNextVal);
		inParamMap.put("PTXNENTRYSEQ", pTxnEntrySeq);
		inParamMap.put("MODIFYUSERID", modifyUserId);
		Integer returnArray = null;
		returnArray = namedParameterJdbcTemplate.update(sql, inParamMap);
		if (returnArray != 0) {
			return returnArray;
		}
		return returnArray;
	}

	/**
	 * This method will update the transaction in the table
	 * 
	 * @param offTrans
	 * @return Integer
	 */
	@Override
	public Integer processGlTransNew(final String caseloadId, final String vTxnType, final BigDecimal pBalance,
			final Integer txnIdNextVal, final Date transDate, final String lvTxnEntryDesc,
			final BigDecimal pTxnEntrySeq, final BigDecimal rootOffenderId, final BigDecimal offenderBookId,
			final BigDecimal pGlSeq, final String pOperationType, final String pSubAccountType,
			final String pSubAccountTypeCr) {
		Integer genSeq = 0;
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		inParamMap.put("P_CSLD_ID", caseloadId);
		inParamMap.put("P_TRANS_TYPE", vTxnType);
		inParamMap.put("P_OPERATION_TYPE", pOperationType);
		inParamMap.put("P_TRANS_AMOUNT", pBalance);
		inParamMap.put("P_TRANS_NUMBER", txnIdNextVal);
		inParamMap.put("P_TRANS_DATE", transDate);
		inParamMap.put("P_TRANS_DESC", lvTxnEntryDesc);
		inParamMap.put("P_TRANS_SEQ", pTxnEntrySeq);
		inParamMap.put("P_MODULE_NAME", "OTDCLINA");
		inParamMap.put("P_OFF_ID", rootOffenderId);
		inParamMap.put("P_OFF_BOOK_ID", offenderBookId);
		inParamMap.put("P_SUB_ACT_TYPE_DR", pSubAccountType);
		inParamMap.put("P_SUB_ACT_TYPE_CR", pSubAccountTypeCr);
		inParamMap.put("P_PAYEE_PERS_ID", null);
		inParamMap.put("P_PAYEE_CORP_ID", null);
		inParamMap.put("P_PAYEE_NAME_TEXT", null);
		inParamMap.put("P_GL_SQNC", 0);
		inParamMap.put("P_OFF_DED_ID", null);
		namedParameterJdbcTemplate
				.update("call OMS_OWNER.TRUST.PROCESS_GL_TRANS_NEW(:P_CSLD_ID, :P_TRANS_TYPE, :P_OPERATION_TYPE, :P_TRANS_AMOUNT, :P_TRANS_NUMBER, :P_TRANS_DATE, :P_TRANS_DESC,"
						+ " :P_TRANS_SEQ, :P_MODULE_NAME, :P_OFF_ID, :P_OFF_BOOK_ID, :P_SUB_ACT_TYPE_DR, :P_SUB_ACT_TYPE_CR, :P_PAYEE_PERS_ID, :P_PAYEE_CORP_ID, :P_PAYEE_NAME_TEXT,"
						+ " :P_GL_SQNC, :P_OFF_DED_ID)", inParamMap);
		genSeq = 2;
		return genSeq;
	}

	@Override
	public Integer accountCodeData(final String pSubAcType,String userId) {
		final String sql = getQuery("OTDCLINA_TRUST_ACCOUNT_CODE");
		Integer returnObj = null;
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams("PSUBACTYPE", pSubAcType,"userId",userId),
					Integer.class);
		} catch (Exception e) {
			return returnObj;
		}
		return returnObj;
	}

	public Integer updateOffenderSubAccounts(final Integer pTxnNum, final BigDecimal pTxnEntrySeq,
			final String caseloadId, final BigDecimal lvRootOffenderId, final Integer pTrustAccountCode, String modifyUserId) {
		final String sql = getQuery("OTDCLINA_OFFENDER_SUB_ACCOUNTS_BALANCE");
		Map<String, Object> inParamMap = new HashMap<String, Object>();
		inParamMap.put("PTXNNUM", pTxnNum);
		inParamMap.put("PTXNENTRYSEQ", pTxnEntrySeq);
		inParamMap.put("PCSLDID", caseloadId);
		inParamMap.put("POFFID", lvRootOffenderId);
		inParamMap.put("PTRUSTACCOUNTCODE", pTrustAccountCode);
		inParamMap.put("MODIFYUSERID", modifyUserId);
		Integer returnArray = null;
		returnArray = namedParameterJdbcTemplate.update(sql, inParamMap);
		if (returnArray != 0) {
			return returnArray;
		}
		return returnArray;
	}

	public Integer updateOffenderTrustAccountsY(final BigDecimal lvRootOffenderId, final String userId) {
		final String sql = getQuery("OTDCLINA_OFFENDER_TRUST_ACCOUNTS_Y");
		Map<String, Object> inParamMap = new HashMap<String, Object>();
		inParamMap.put("LVROOTOFFENDERID", lvRootOffenderId);
		inParamMap.put("USERID", userId);
		Integer returnArray = null;
		returnArray = namedParameterJdbcTemplate.update(sql, inParamMap);
		if (returnArray != 0) {
			return returnArray;
		}
		return returnArray;
	}

	@Override
	public Integer countSessionId() {
		final String sql = getQuery("OTDCLINA_SESSIONID_COUNT");
		Integer returnObj = null;
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), Integer.class);
		} catch (Exception e) {
			return returnObj;
		}
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * checkLock
	 *
	 * @param params
	 *
	 */
	public List<OffenderSubAccounts> balanceSubAccountCode(final BigDecimal rootOffenderId, String userId) {
		final String sql = getQuery("OTDCLINA_TRUST_ACCOUNT_CODE_BALANCE");
		final RowMapper<OffenderSubAccounts> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderSubAccounts.class, lockedModulesMapping);
		List<OffenderSubAccounts> returnList = new ArrayList<>();
		returnList = namedParameterJdbcTemplate.query(sql, createParams("ROOTOFFENDERID", rootOffenderId, "userId", userId),
				columnRowMapper);
		if (returnList != null) {
			return returnList;
		}
		return returnList;
	}

	public Integer updateOffenderTransactionsTransfer(final String txnTypeDescription, final Integer txnIdNextVal,
			final BigDecimal pTxnEntrySeq, String userId) {
		final String sql = getQuery("OTDCLINA_UPDATE_OFFENDER_TRANSACTIONS_TRANSFER");
		Map<String, Object> inParamMap = new HashMap<String, Object>();
		inParamMap.put("LVPAYEENAME", txnTypeDescription);
		inParamMap.put("PTXNNUM", txnIdNextVal);
		inParamMap.put("PTXNENTRYSEQ", pTxnEntrySeq);
		inParamMap.put("USERID", userId);
		Integer returnArray = null;
		returnArray = namedParameterJdbcTemplate.update(sql, inParamMap);
		if (returnArray != 0) {
			return returnArray;
		}
		return returnArray;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * checkLock
	 *
	 * @param params
	 *
	 */
	public List<OffenderSubAccounts> balanceSubAccountCodeProp(final BigDecimal rootOffenderId, String userId) {
		final String sql = getQuery("OTDCLINA_TRUST_ACCOUNT_CODE_BALANCE_PROP");
		final RowMapper<OffenderSubAccounts> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderSubAccounts.class, lockedModulesMapping);
		List<OffenderSubAccounts> returnList = new ArrayList<>();
		returnList = namedParameterJdbcTemplate.query(sql, createParams("ROOTOFFENDERID", rootOffenderId, "userId", userId),
				columnRowMapper);
		if (returnList != null) {
			return returnList;
		}
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * checkLock
	 *
	 * @param params
	 *
	 */
	public List<OffenderSubAccounts> balanceSubAccountCodeReg(final BigDecimal rootOffenderId, String userId) {
		final String sql = getQuery("OTDCLINA_TRUST_ACCOUNT_CODE_BALANCE_REG");
		final RowMapper<OffenderSubAccounts> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderSubAccounts.class, lockedModulesMapping);
		List<OffenderSubAccounts> returnList = new ArrayList<>();
		returnList = namedParameterJdbcTemplate.query(sql, createParams("ROOTOFFENDERID", rootOffenderId,"userId",userId),
				columnRowMapper);
		if (returnList != null) {
			return returnList;
		}
		return returnList;
	}

	public BigDecimal sumBalanceTemp(final BigDecimal rootOffenderId, String userId) {
		BigDecimal returnObj = null;
		final String sql = getQuery("OTDCLINA_SUM_BALANCE_TEMP");
		returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams("ROOTOFFENDERID", rootOffenderId, "USERID", userId),
				BigDecimal.class);
		if (returnObj != null) {
			return returnObj;
		}
		return returnObj;
	}

	/**
	 * This method is used to insert the records in the data base tables based
	 * on
	 *
	 * @param lstOffenderTransactions
	 *            List<OffenderTransactions>
	 *
	 * @return List<Integer>
	 */
	public Integer updateOffenderTrustAccountsBalances(final BigDecimal sumBalance, final BigDecimal rootOffenderId, String userId) {
		final String sql = getQuery("OTDCLINA_OFFENDER_TRUST_ACCOUNTS_BALANCE");
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("LVBALANCE", sumBalance);
		paramMap.put("ROOTOFFENDERID", rootOffenderId);
		paramMap.put("USERID", userId);
		Integer returnArray = null;
		returnArray = namedParameterJdbcTemplate.update(sql, paramMap);
		if (returnArray != 0) {
			return returnArray;
		}
		return returnArray;
	}

	@Override
	public Integer txnTypeCountData(String userId) {
		Integer returnObj = null;
		final String sql = getQuery("OTDCLINA_CHECK_SETUP_BEFORE_TRANSFER");
		returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams("userId", userId), Integer.class);
		if (returnObj != null) {
			return returnObj;
		}
		return returnObj;
	}
}

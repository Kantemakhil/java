package net.syscon.s4.inmate.trust.checks.checksmainataince.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
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
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.AccountCodes;
import net.syscon.s4.im.beans.BankAccountsMaintenances;
import net.syscon.s4.im.beans.CaseloadCurrentAccountsBase;
import net.syscon.s4.im.beans.Corporates;
import net.syscon.s4.im.beans.ReferenceCodes;
import net.syscon.s4.inmate.trust.checks.checksmainataince.OtmbaccoRepository;

/**
 * Class OtmbaccoRepositoryImpl
 * 
 * @author Arkin Software Technologies
 * @version 1.0
 */
@Repository
public class OtmbaccoRepositoryImpl extends RepositoryBase implements OtmbaccoRepository {

	
	
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OtmbaccoRepositoryImpl.class.getName());
	

	private final Map<String, FieldMapper> mmMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CORPORATE_ID", 							new FieldMapper("corporateId"))
			.put("CORPORATE_NAME", 							new FieldMapper("corporateName"))
			.build();
	private final Map<String, FieldMapper> referenceCodesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("EXPIRED_DATE", 							new FieldMapper("expiredDate"))
			.put("DOMAIN", 									new FieldMapper("domain"))
			.put("ACTIVE_FLAG", 							new FieldMapper("activeFlag"))
			.put("DESCRIPTION",							    new FieldMapper("description"))
			.put("BANK_ACCOUNT_TYPE",						new FieldMapper("bankAccountType"))
			.build();
	private final Map<String, FieldMapper> bankAccountsMaintenancesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CASELOAD_ID", 						    new FieldMapper("caseloadId"))
			.put("ACCOUNT_CODE",						    new FieldMapper("accountCode"))
			.build();
	private final Map<String, FieldMapper> accountCodesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("ACCOUNT_NAME", 							new FieldMapper("accountName"))
			.put("ACCOUNT_CODE",						    new FieldMapper("accountCode"))
			.build();
	private final Map<String, FieldMapper> mMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CODE", 									new FieldMapper("code"))
			.put("DOMAIN",								    new FieldMapper("domain"))
			.put("ACTIVE_FLAG", 							new FieldMapper("activeFlag"))
			.put("DESCRIPTION",							    new FieldMapper("description"))
			.put("ACCOUNT_CODE",						    new FieldMapper("accountCode"))
			.build();
	private final Map<String, FieldMapper> corporatesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CORPORATE_NAME", 						    new FieldMapper("corporateName"))
			.put("PAYEE_CORPORATE_ID", 						new FieldMapper("payeeCorporateId"))
			.build();

	private final Map<String, FieldMapper> caseloadCurrentAccountsBaseMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CASELOAD_ID",                                new FieldMapper("caseloadId"))
			.put("CASELOAD_TYPE",                              new FieldMapper("caseloadType"))
			.put("ACCOUNT_CODE",                               new FieldMapper("accountCode"))
			.put("ACCOUNT_PERIOD_ID",                          new FieldMapper("accountPeriodId"))
			.put("CURRENT_BALANCE",                            new FieldMapper("currentBalance"))
			.put("BANK_ACCOUNT_TYPE",                          new FieldMapper("bankAccountType"))
			.put("BANK_ACCOUNT_NUMBER", 					   new FieldMapper("bankAccountNumber"))
			.put("ACCOUNT_PARTY_TYPE", 						   new FieldMapper("accountPartyType"))
			.put("PAYEE_PERSON_ID", 						   new FieldMapper("payeePersonId"))
			.put("PAYEE_CORPORATE_ID", 						   new FieldMapper("payeeCorporateId"))
			.put("MODIFY_USER_ID",                             new FieldMapper("modifyUserId"))
			.put("MODIFY_DATE",                                new FieldMapper("modifyDate"))
			.put("ROUTING_NUMBER",                             new FieldMapper("routingNumber"))
			.put("LIST_SEQ",                                   new FieldMapper("listSeq"))
			.put("CREATE_DATETIME",                            new FieldMapper("createDatetime"))
			.put("CREATE_USER_ID",                             new FieldMapper("createUserId"))
			.put("MODIFY_DATETIME",                            new FieldMapper("modifyDatetime"))
			.put("SEAL_FLAG",                                  new FieldMapper("sealFlag"))
			.build();
	
	/**
	 * Creates new OtmbaccoRepositoryImpl class Object
	 */
	public OtmbaccoRepositoryImpl() {
		// OtmbaccoRepositoryImpl
	}

	/**
	 * Fetch the records from database table csldCabExecuteQuery
	 * 
	 * @param objSearchDao
	 * @return returnList
	 */
	public List<CaseloadCurrentAccountsBase> csldCabExecuteQuery(final CaseloadCurrentAccountsBase objSearchDao) {
		final String sql = getQuery("OTMBACCO_CSLDCAB_FIND_CASELOAD_CURRENT_ACCOUNTS_BASE");
		String preparedSql = null;
		final StringBuffer sqlQuery = new StringBuffer();
		String preSqlQuery = null;
		final MapSqlParameterSource params = new MapSqlParameterSource();
		sqlQuery.append(sql);
		if (objSearchDao != null) {
			sqlQuery.append(" where ");
			if (objSearchDao != null && objSearchDao.getAccountCode() != null
					&& !("").equals(objSearchDao.getAccountCode())) {
				sqlQuery.append(" ACCOUNT_CODE  = :accountCode  " + " and");
				params.addValue("accountCode", objSearchDao.getAccountCode());
			}
			sqlQuery.append(
					" CASELOAD_CURRENT_ACCOUNTS_BASE.CASELOAD_ID = :CASELOADID AND CASELOAD_CURRENT_ACCOUNTS_BASE.ACCOUNT_CODE "
							+ " IN (SELECT ACCOUNT_CODE FROM ACCOUNT_CODES WHERE ACCOUNT_TYPE = 'A' AND POSTING_STATUS_FLAG = 'Y'AND CASELOAD_TYPE  = :CASELOADTYPE  ) ");
			params.addValue("CASELOADID", objSearchDao.getCaseloadId());
			params.addValue("CASELOADTYPE", objSearchDao.getCaseloadType());
		}
		preparedSql = sqlQuery.toString().trim();
		if (preparedSql.endsWith("where")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 5);
		}
		if (preparedSql.endsWith("and")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 3);
		}
		preSqlQuery = preparedSql.concat(" ORDER BY ACCOUNT_CODE ");
		final RowMapper<CaseloadCurrentAccountsBase> CaseloadCurrentAccountsBaseRowMapper = Row2BeanRowMapper
				.makeMapping(sql, CaseloadCurrentAccountsBase.class, caseloadCurrentAccountsBaseMapping);
		List<CaseloadCurrentAccountsBase> returnList = new ArrayList<CaseloadCurrentAccountsBase>();
		try {
			returnList = namedParameterJdbcTemplate.query(preSqlQuery, params, CaseloadCurrentAccountsBaseRowMapper);
		} catch (Exception e) {
			logger.error("csldCabExecuteQuery", e);
		}
		return returnList;
	}

	/**
	 * This method is used to insert the records in the data base tables based
	 * on csldCabInsertCaseloadCurrentAccountsBase
	 * 
	 * @param lstCaseloadCurrentAccountsBase
	 * @return Integer
	 */

	public Integer csldCabInsertCaseloadCurrentAccountsBase(
			final List<CaseloadCurrentAccountsBase> lstCaseloadCurrentAccountsBase) {
		String sql = getQuery("OTMBACCO_CSLDCAB_INSERT_CASELOAD_CURRENT_ACCOUNTS_BASE");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		for (CaseloadCurrentAccountsBase caseloadCurrentAccountsBase : lstCaseloadCurrentAccountsBase) {
			parameters.add(new BeanPropertySqlParameterSource(caseloadCurrentAccountsBase));
		}
		if (lstCaseloadCurrentAccountsBase.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to update the data base tables based on
	 * csldCabUpdateCaseloadCurrentAccountsBase
	 * 
	 * @param lstCaseloadCurrentAccountsBase
	 */
	public Integer csldCabUpdateCaseloadCurrentAccountsBase(
			final List<CaseloadCurrentAccountsBase> lstCaseloadCurrentAccountsBase) {
		String sql = getQuery("OTMBACCO_CSLDCAB_UPDATE_CASELOAD_CURRENT_ACCOUNTS_BASE");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (CaseloadCurrentAccountsBase caseloadCurrentAccountsBase : lstCaseloadCurrentAccountsBase) {
			parameters.add(new BeanPropertySqlParameterSource(caseloadCurrentAccountsBase));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstCaseloadCurrentAccountsBase.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to delete records from data base tables based on
	 * csldCabDeleteCaseloadCurrentAccountsBase
	 * 
	 * @param lstCaseloadCurrentAccountsBase
	 */
	public Integer csldCabDeleteCaseloadCurrentAccountsBase(
			final List<CaseloadCurrentAccountsBase> lstCaseloadCurrentAccountsBase) {
		String sql = getQuery("OTMBACCO_CSLDCAB_DELETE_CASELOAD_CURRENT_ACCOUNTS_BASE");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (CaseloadCurrentAccountsBase caseloadCurrentAccountsBase : lstCaseloadCurrentAccountsBase) {
			parameters.add(new BeanPropertySqlParameterSource(caseloadCurrentAccountsBase));
		}
		try {
			String tableName = "CASELOAD_CURRENT_ACCOUNTS_BASE";
			String whereClause = "CASELOAD_ID = :caseloadId AND ACCOUNT_CODE = :accountCode";
			batchUpdatePreDeletedRows(tableName, whereClause , parameters);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method csldCabDeleteCaseloadCurrentAccountsBase", e);
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstCaseloadCurrentAccountsBase.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            bankAmExecuteQuery
	 * @return returnList
	 *
	 */
	public List<BankAccountsMaintenances> bankAmExecuteQuery(final BankAccountsMaintenances objSearchDao) {
		final String sql = getQuery("OTMBACCO_BANKAM_FIND_BANK_ACCOUNTS_MAINTENANCES");
		final RowMapper<BankAccountsMaintenances> BankAccountsMaintenancesRowMapper = Row2BeanRowMapper.makeMapping(sql,
				BankAccountsMaintenances.class, bankAccountsMaintenancesMapping);

		List<BankAccountsMaintenances> returnList = new ArrayList<BankAccountsMaintenances>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams("ACCOUNTCODE",
					objSearchDao.getAccountCode(), "CASELOADID", objSearchDao.getCaseloadId()),
					BankAccountsMaintenancesRowMapper);
		} catch (Exception e) {
			logger.error("bankAmExecuteQuery", e);
		}
		return returnList;
	}

	/**
	 * This method is used to insert the records in the data base tables based
	 * on
	 * 
	 * @param lstBankAccountsMaintenances
	 *            bankAmInsertBankAccountsMaintenances
	 * @return Integer
	 */

	public Integer bankAmInsertBankAccountsMaintenances(
			final List<BankAccountsMaintenances> lstBankAccountsMaintenances) {
		String sql = getQuery("OTMBACCO_BANKAM_INSERT_BANK_ACCOUNTS_MAINTENANCES");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (BankAccountsMaintenances bankAccountsMaintenances : lstBankAccountsMaintenances) {
			parameters.add(new BeanPropertySqlParameterSource(bankAccountsMaintenances));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstBankAccountsMaintenances.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to update the data base tables based on
	 * 
	 * @param lstBankAccountsMaintenances
	 *            bankAmUpdateBankAccountsMaintenances
	 * @return Integer
	 */
	public Integer bankAmUpdateBankAccountsMaintenances(
			final List<BankAccountsMaintenances> lstBankAccountsMaintenances) {
		String sql = getQuery("OTMBACCO_BANKAM_UPDATE_BANK_ACCOUNTS_MAINTENANCES");

		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (BankAccountsMaintenances bankAccountsMaintenances : lstBankAccountsMaintenances) {
			parameters.add(new BeanPropertySqlParameterSource(bankAccountsMaintenances));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstBankAccountsMaintenances.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to delete records from data base tables based on
	 * bankAmDeleteBankAccountsMaintenances
	 * 
	 * @param lstBankAccountsMaintenances\
	 * @return Integer
	 */
	public Integer bankAmDeleteBankAccountsMaintenances(
			final List<BankAccountsMaintenances> lstBankAccountsMaintenances) {
		String sql = getQuery("OTMBACCO_BANKAM_DELETE_BANK_ACCOUNTS_MAINTENANCES");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (BankAccountsMaintenances bankAccountsMaintenances : lstBankAccountsMaintenances) {
			parameters.add(new BeanPropertySqlParameterSource(bankAccountsMaintenances));
		}
		try {
			String tableName = "BANK_ACCOUNTS_MAINTENANCES";
			String whereClause = "CASELOAD_ID=:caseloadId AND ACCOUNT_CODE=:accountCode";
			batchUpdatePreDeletedRows(tableName, whereClause , parameters);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method bankAmDeleteBankAccountsMaintenances", e);
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));

		if (lstBankAccountsMaintenances.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * Used to capture results from select query
	 * cgfkCsldCabBankAccountTypRecordGroup
	 * 
	 * @return returnList
	 */
	public List<ReferenceCodes> cgfkCsldCabBankAccountTypRecordGroup() {
		final String sql = getQuery("OTMBACCO_FIND_CGFKCSLDCABBANKACCOUNTTYP");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);
		List<ReferenceCodes> returnList = new ArrayList<ReferenceCodes>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (Exception e) {
			logger.error("cgfkCsldCabBankAccountTypRecordGroup", e);
		}
		return returnList;
	}

	/**
	 * Used to capture results from select query
	 * cgfkCsldCabPayeeCorporateRecordGroup
	 * 
	 * @return returnList
	 */
	public List<Corporates> cgfkCsldCabPayeeCorporateRecordGroup() {
		final String sql = getQuery("OTMBACCO_FIND_CGFKCSLDCABPAYEECORPORATE");
		final RowMapper<Corporates> mMRowMapper = Row2BeanRowMapper.makeMapping(sql, Corporates.class, mmMapping);
		List<Corporates> returnList = new ArrayList<Corporates>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams(), mMRowMapper);
		} catch (Exception e) {
			logger.error("cgfkCsldCabPayeeCorporateRecordGroup", e);
		}
		return returnList;

	}

	/**
	 * Used to capture results from select query method
	 * cgfkCsldCabAccountCodeRecordGroup
	 * 
	 * @return returnList
	 */
	public List<AccountCodes> cgfkCsldCabAccountCodeRecordGroup(String str) {
		final String sql = getQuery("OTMBACCO_FIND_CGFKCSLDCABACCOUNTCODE");
		final RowMapper<AccountCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, AccountCodes.class, mMapping);
		List<AccountCodes> returnList = new ArrayList<AccountCodes>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams("userId",str), mRowMapper);
			for (AccountCodes accountCodes : returnList) {
				accountCodes.setListSeq(new BigDecimal(accountCodes.getAccountCode()));
			}
		} catch (Exception e) {
			logger.error("cgfkCsldCabAccountCodeRecordGroup", e);
		}
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * csldCabOnCheckDeleteMaster
	 *
	 * @param paramBean
	 *
	 */
	public List<BankAccountsMaintenances> csldCabOnCheckDeleteMaster(final BankAccountsMaintenances paramBean) {
		final String sql = getQuery("OTMBACCO_CSLD_CAB_ONCHECKDELETEMASTER");
		final RowMapper<BankAccountsMaintenances> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				BankAccountsMaintenances.class, bankAccountsMaintenancesMapping);
		List<BankAccountsMaintenances> returnList = new ArrayList<BankAccountsMaintenances>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams(), columnRowMapper);
		} catch (Exception e) {
			logger.error("csldCabOnCheckDeleteMaster", e);
		}
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfkchkCsldCabCsldCabRef
	 *
	 * @param paramBean
	 *
	 */
	public List<ReferenceCodes> cgfkchkCsldCabCsldCabRef(final ReferenceCodes paramBean) {
		final String sql = getQuery("OTMBACCO_CGFKCHK_CSLD_CAB_CSLD_CAB_REF");
		final RowMapper<ReferenceCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				referenceCodesMapping);
		List<ReferenceCodes> returnList = new ArrayList<ReferenceCodes>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams(), columnRowMapper);
		} catch (Exception e) {
			logger.error("cgfkchkCsldCabCsldCabRef", e);
		}
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfkchkCsldCabCsldCabAc
	 *
	 * @param paramBean
	 *
	 */
	public List<AccountCodes> cgfkchkCsldCabCsldCabAc(final AccountCodes paramBean) {
		final String sql = getQuery("OTMBACCO_CGFKCHK_CSLD_CAB_CSLD_CAB_AC_");
		final RowMapper<AccountCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, AccountCodes.class,
				accountCodesMapping);
		List<AccountCodes> returnList = new ArrayList<AccountCodes>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams(), columnRowMapper);
		} catch (Exception e) {
			logger.error("cgfkchkCsldCabCsldCabAc", e);
		}
		return returnList;

	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfkchkCsldCabCsldCabCor
	 *
	 * @param paramBean
	 *
	 */
	public List<Corporates> cgfkchkCsldCabCsldCabCor(Corporates paramBean) {
		final String sql = getQuery("OTMBACCO_CGFKCHK_CSLD_CAB_CSLD_CAB_COR");
		final RowMapper<Corporates> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, Corporates.class,
				corporatesMapping);
		List<Corporates> returnList = new ArrayList<Corporates>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams(), columnRowMapper);
		} catch (Exception e) {
			logger.error("cgfkchkCsldCabCsldCabCor", e);
		}
		return returnList;
	}

	@Override
	public Long accountPeriodIdData() {
		final String sql = getQuery("OTMBACCO_ACCOUNT_PERIOD_ID_ACCOUNT_PERIODS");
		Long returnObj = null;
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), Long.class);
		} catch (Exception e) {
			return returnObj;
		}
		return returnObj;
	}

	@Override
	public String corporeteNameData(final BigDecimal payeeCorporateId) {
		final String sql = getQuery("OTMBACCO_CORPORETE_NAME_DATA");
		String returnObj = null;
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams("CORPORATEID", payeeCorporateId),
					String.class);
		} catch (Exception e) {
			returnObj = "X";
			return returnObj;
		}
		return returnObj;
	}

}

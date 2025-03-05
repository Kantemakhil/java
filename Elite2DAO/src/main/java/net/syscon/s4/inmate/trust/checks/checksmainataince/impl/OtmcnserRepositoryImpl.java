package net.syscon.s4.inmate.trust.checks.checksmainataince.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.globaloffenderrecords.impl.OsiosearRepositoryImpl;
import net.syscon.s4.im.beans.AccountCodes;
import net.syscon.s4.im.beans.BankChequeBooks;
import net.syscon.s4.im.beans.CaseloadCurrentAccounts;
import net.syscon.s4.inmate.trust.checks.checksmainataince.OtmcnserRepository;

/**
 * Class OtmcnserRepositoryImpl
 */
@Repository
public class OtmcnserRepositoryImpl extends RepositoryBase implements OtmcnserRepository {
	
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OtmcnserRepositoryImpl.class.getName());

	private final Map<String, FieldMapper> accountCodesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("ACCOUNT_NAME",            new FieldMapper("accountName"))
			.put("ACCOUNT_CODE",            new FieldMapper("accountCode"))
			.put("VAL",                   new FieldMapper("value"))
			.build();

	private final Map<String, FieldMapper> caseloadCurrentAccountsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CASELOAD_ID", 			new FieldMapper("caseloadId"))
			.put("ACCOUNT_CODE", 			new FieldMapper("accountCode"))
			.put("ACCOUNT_PERIOD_ID", 		new FieldMapper("accountPeriodId"))
			.put("CURRENT_BALANCE", 		new FieldMapper("currentBalance"))
			.put("BANK_ACCOUNT_TYPE", 		new FieldMapper("bankAccountType"))
			.put("BANK_ACCOUNT_NUMBER", 	new FieldMapper("bankAccountNumber"))
			.put("ACCOUNT_PARTY_TYPE", 		new FieldMapper("accountPartyType"))
			.put("PAYEE_PERSON_ID", 		new FieldMapper("payeePersonId"))
			.put("PAYEE_CORPORATE_ID", 		new FieldMapper("payeeCorporateId"))
			.put("MODIFY_USER_ID", 			new FieldMapper("modifyUserId"))
			.put("MODIFY_DATE", 			new FieldMapper("modifyDate"))
			.put("LIST_SEQ", 				new FieldMapper("listSeq"))
			.put("ROUTING_NUMBER",          new FieldMapper("routingNumber"))
			.build();

	private final Map<String, FieldMapper> bankChequeBooksMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CASELOAD_ID",             new FieldMapper("caseloadId"))
			.put("ACCOUNT_CODE",            new FieldMapper("accountCode"))
			.put("CHEQUE_BOOK_SEQ",         new FieldMapper("chequeBookSeq"))
			.put("ACCOUNT_NUMBER",          new FieldMapper("accountNumber"))
			.put("FIRST_CHECK_NUMBER",      new FieldMapper("firstCheckNumber"))
			.put("LAST_CHECK_NUMBER",       new FieldMapper("lastCheckNumber"))
			.put("NEXT_CHECK_NUMBER",       new FieldMapper("nextCheckNumber"))
			.put("TXN_ENTRY_DATE",          new FieldMapper("txnEntryDate"))
			.put("MODIFY_USER_ID",          new FieldMapper("modifyUserId"))
			.put("MODIFY_DATE",             new FieldMapper("modifyDate"))
			.put("LIST_SEQ",                new FieldMapper("listSeq"))
			.put("CREATE_DATETIME",         new FieldMapper("createDatetime"))
			.put("CREATE_USER_ID",          new FieldMapper("createUserId"))
			.put("MODIFY_DATETIME",         new FieldMapper("modifyDatetime"))
			.put("SEAL_FLAG",               new FieldMapper("sealFlag"))
			.build();

	/**
	 * Fetch the records from database table
	 * method csldCaExecuteQuery
	 * @param objSearchDao
	 * @return returnList
	 */
	public List<CaseloadCurrentAccounts> csldCaExecuteQuery(CaseloadCurrentAccounts objCaseloadCurrentAccounts) {
		final String sql = getQuery("OTMCNSER_CSLDCA_FIND_CASELOAD_CURRENT_ACCOUNTS");
		final RowMapper<CaseloadCurrentAccounts> caseloadCurrentAccountsRowMapper = Row2BeanRowMapper.makeMapping(sql,
				CaseloadCurrentAccounts.class, caseloadCurrentAccountsMapping);
		List<CaseloadCurrentAccounts> returnList = new ArrayList<CaseloadCurrentAccounts>();
		String preSqlQuery =null;
		String preparedSql = null;
		String preSql = null;
		final MapSqlParameterSource inParameterSource = new MapSqlParameterSource();
		final StringBuffer sqlQuery = new StringBuffer();
		sqlQuery.append(sql);
		
		if (objCaseloadCurrentAccounts != null) {
			inParameterSource.addValue("ACCOUNT_CODE", objCaseloadCurrentAccounts.getAccountCode());
			sqlQuery.append("WHERE ");

			if (objCaseloadCurrentAccounts.getAccountCode() != null) {
				sqlQuery.append("ACCOUNT_CODE = :ACCOUNT_CODE" + " AND  ");
				inParameterSource.addValue("userId", objCaseloadCurrentAccounts.getCreateUserId());
			}
			if (objCaseloadCurrentAccounts.getCaseloadId() != null) {
				sqlQuery.append("CASELOAD_ID = :CASELOAD_ID" + " AND   bank_account_type = 'CHK' AND account_code IN (SELECT account_code FROM account_codes WHERE POSTING_STATUS_FLAG = 'Y'");
				inParameterSource.addValue("CASELOAD_ID", objCaseloadCurrentAccounts.getCaseloadId());
			}
			
			if (objCaseloadCurrentAccounts.getGlobalCaseloadType() != null) {
				sqlQuery.append(" AND  caseload_type = :CASELOAD_TYPE )");
				inParameterSource.addValue("CASELOAD_TYPE", objCaseloadCurrentAccounts.getGlobalCaseloadType());
			}
		}
		preparedSql = sqlQuery.toString().trim();
		if (preparedSql.endsWith("WHERE")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 5);
		}
		if (preparedSql.endsWith("AND")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 3);
		}
		preSqlQuery = preparedSql.concat(" ORDER BY ACCOUNT_CODE ");		
		try{
		 returnList =  namedParameterJdbcTemplate.query(preSqlQuery, inParameterSource, caseloadCurrentAccountsRowMapper);
		} catch (Exception e) {
			logger.error("csldCaExecuteQuery",e);
		}
		return returnList;
	}

	/**
	 * Fetch the records from database table
	 * method bankCbExecuteQuery
	 * @param objBankChequeBooks
	 * @return List<BankChequeBooks>
	 */
	public List<BankChequeBooks> bankCbExecuteQuery(BankChequeBooks objBankChequeBooks) {
		final String sql = getQuery("OTMCNSER_BANKCB_FIND_BANK_CHEQUE_BOOKS");
		final RowMapper<BankChequeBooks> BankChequeBooksRowMapper = Row2BeanRowMapper.makeMapping(sql,
				BankChequeBooks.class, bankChequeBooksMapping);
		List<BankChequeBooks> returnList = new ArrayList<BankChequeBooks>();
		
		String preparedSql = null;
		String preSql = null;
		final MapSqlParameterSource inParameterSource = new MapSqlParameterSource();
		final StringBuffer sqlQuery = new StringBuffer();
		sqlQuery.append(sql);
		
		if (objBankChequeBooks.getCaseloadId() != null) {
			sqlQuery.append("CASELOAD_ID = :CASELOAD_ID" + " AND  ");
			inParameterSource.addValue("CASELOAD_ID", objBankChequeBooks.getCaseloadId());
		}
		if (objBankChequeBooks.getAccountCode() != null) {
			sqlQuery.append("ACCOUNT_CODE = :ACCOUNT_CODE" + " AND  ");
			inParameterSource.addValue("ACCOUNT_CODE", objBankChequeBooks.getAccountCode());
		}
		if (objBankChequeBooks.getFirstCheckNumber() != null) {
			sqlQuery.append("FIRST_CHECK_NUMBER = :FIRST_CHECK_NUMBER" + " AND  ");
			inParameterSource.addValue("FIRST_CHECK_NUMBER", objBankChequeBooks.getFirstCheckNumber());
		}
		if (objBankChequeBooks.getLastCheckNumber() != null) {
			sqlQuery.append("LAST_CHECK_NUMBER = :LAST_CHECK_NUMBER" + " AND  ");
			inParameterSource.addValue("LAST_CHECK_NUMBER", objBankChequeBooks.getLastCheckNumber());
		}
		preparedSql = sqlQuery.toString().trim();

		if (preparedSql.endsWith("WHERE")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 5);
		}
		if (preparedSql.endsWith("AND")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 3);
		}
		preSql = preparedSql.concat("  ORDER BY FIRST_CHECK_NUMBER");
		
		
		 returnList =  namedParameterJdbcTemplate.query(preSql,inParameterSource, BankChequeBooksRowMapper);
		return returnList;
	}

	/**
	 * This method is used to insert the records in the data base tables based on
	 * method bankcbInsertBankChequeBooks
	 * @param lstBankChequeBooks
	 * @return List<Integer>

	 */
	public Integer bankcbInsertBankChequeBooks(final List<BankChequeBooks> lstBankChequeBooks) {
		String sql = getQuery("OTMCNSER_BANKCB_INSERT_BANK_CHEQUE_BOOKS");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (BankChequeBooks bankChequeBooks : lstBankChequeBooks) {
			parameters.add(new BeanPropertySqlParameterSource(bankChequeBooks));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error("bankcbInsertBankChequeBooks", e);
		}
		if (lstBankChequeBooks.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

	/**
	 * This method is used to delete records from data base tables based on
	 * method bankCbDeleteBankChequeBooks
	 * @param lstBankChequeBooks
	 * return Integer
	 */
	public Integer bankCbDeleteBankChequeBooks(final List<BankChequeBooks> lstBankChequeBooks) {
		String sql = getQuery("OTMCNSER_BANKCB_DELETE_BANK_CHEQUE_BOOKS");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (BankChequeBooks bankChequeBooks : lstBankChequeBooks) {
			parameters.add(new BeanPropertySqlParameterSource(bankChequeBooks));
		}
		try {
			String tableName = "BANK_CHEQUE_BOOKS";
			String whereClause = "CASELOAD_ID=:caseloadId and ACCOUNT_CODE =:accountCode and CHEQUE_BOOK_SEQ =:chequeBookSeq";
			batchUpdatePreDeletedRows(tableName, whereClause , parameters);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method bankCbDeleteBankChequeBooks", e);
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstBankChequeBooks.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * method cgfkchkCsldCaCsldAcAcCo
	 * @param paramBean
	 */
	public List<AccountCodes> cgfkchkCsldCaCsldAcAcCo(AccountCodes paramBean) {
		final String sql = getQuery("OTMCNSER_CGFKCHK_CSLD_CA_CSLD_AC_AC_CO");
		final RowMapper<AccountCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, AccountCodes.class,
				accountCodesMapping);
		final ArrayList<AccountCodes> returnList = (ArrayList<AccountCodes>) namedParameterJdbcTemplate.query(sql,
				createParams("userId",paramBean.getCreateUserId()), columnRowMapper);
		return returnList;
	}
	/**
	 * This method is execute a sql query when trigger event is called
	 * cgwhenNewFormInstance
	 * @param params
	 */
	// public List<Object> cgwhenNewFormInstance(SysDual paramBean) {
	// final String sql = getQuery("OTMCNSER_CGWHEN_NEW_FORM_INSTANCE");
	// final RowMapper<SysDual> columnRowMapper =
	// Row2BeanRowMapper.makeMapping(sql,SysDual.class, sysDualMapping);
	// final ArrayList<SysDual> returnList =(ArrayList<SysDual>)
	// namedParameterJdbcTemplate.query(sql, createParams(SysDual),
	// columnRowMapper);
	// return returnList;
	// }

	public String accountName(final BigDecimal accountCode,String userId) {
		final String sql = getQuery("OTMCNSER_GETACCOUNT_NAME");
		String accName = null;
		accName = namedParameterJdbcTemplate.queryForObject(sql, createParams("accountCode",accountCode,"userId",userId), String.class);		
		return accName;
	}

	public List<String> checkChecqueBooks(final Long firstChecknum,final  Long lastCheckNum, final Long accountCode, final  String caseloadId) {
		final String sql = getQuery("OTMCNSER_CHECKCHECQUEBOOKS");
		List<String> returnList =new ArrayList<String>();
		RowMapper<String> columnMapper =  Row2BeanRowMapper.makeMapping(sql, String.class,accountCodesMapping);
				returnList = namedParameterJdbcTemplate.query(sql, createParams("firstChecknum",firstChecknum,
				"lastCheckNum",lastCheckNum,"accountCode",accountCode,"caseloadId",caseloadId),columnMapper);
		return returnList;
	}

	public String checkifNewSeries(Long firstChecknum, Long accountCode, String caseloadId) {
		String sql = getQuery("OTMCNSER_CHECKIFNEWSERIES");
		String returnList =null;
		try{
				returnList = namedParameterJdbcTemplate.queryForObject(sql, createParams("firstChecknum",firstChecknum,
				"accountCode",accountCode,"caseloadId",caseloadId),String.class);
		} catch (NullPointerException e) {
			return "Z";			
		}
		catch (EmptyResultDataAccessException e) {
			return "Z";
			
		}
		catch (IncorrectResultSizeDataAccessException e) {
			return "Y";
			
		}
		return returnList;
	}

	public String checkChecqueBooksLastCheck(Long lastCheckNumLong, Long firstChecknumLong, Long accountCode,
			String caseloadId) {
		final String sql = getQuery("OTMCNSER_CHECKCHECQUEBOOKS");
		String returnList =null;
		try {
		returnList =namedParameterJdbcTemplate.queryForObject(sql, createParams("lastCheckNumLong",lastCheckNumLong,
				"firstChecknumLong",firstChecknumLong,
				"accountCode",accountCode,"caseloadId",caseloadId),String.class);
		} catch (EmptyResultDataAccessException e) {
			return "Z";
			
		}
		catch (NullPointerException e) {
			return "Z";
			
		}
		return returnList;
	}

	public Long getChequeBookSeq() {
		final String sql = getQuery("OTMCNSER_GETCHEQUEBOOKSEQ");
		Long chequeseq = null;
		try {
		chequeseq = namedParameterJdbcTemplate.queryForObject(sql, createParams(), Long.class);
		} catch (Exception e) {
			logger.error("getChequeBookSeq",e);
		}
		return chequeseq;
	}

	public String getaccountNumber(final String caseloadId, final Long accountCode,String userName) {
		final String sql = getQuery("OTMCNSER_GETACCOUNTNUMBER");
		String acctNum = null;
		try {
		acctNum = namedParameterJdbcTemplate.queryForObject(sql, createParams("caseloadId",caseloadId,"accountCode",accountCode,"userId",userName), String.class);
		} catch (Exception e) {
			logger.error("getaccountNumber",e);
		}
		return acctNum;
	}

}

package net.syscon.s4.inmate.trust.financialsmaintenance.transaction.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
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
import org.springframework.transaction.support.TransactionOperations;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.Caseloads;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.AccountCodes;
import net.syscon.s4.im.beans.TxnOpsInvalidAccounts;
import net.syscon.s4.inmate.trust.financialsmaintenance.transaction.OcmcoactRepository;

/**
 * Class OcmcoactRepositoryImpl
 */
@Repository
public class OcmcoactRepositoryImpl extends RepositoryBase implements OcmcoactRepository {
	
	private static Logger logger = LogManager.getLogger(OcmcoactRepositoryImpl.class.getName());

	private final Map<String, FieldMapper> referenceCodesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("LIST_SEQ", 					new FieldMapper("listSeq"))
			.put("TXN_POSTING_TYPE", 			new FieldMapper("txnPostingType"))
			.put("ACCOUNT_TYPE", 				new FieldMapper("accountType"))
			.put("DESCRIPTION", 				new FieldMapper("description"))
			.put("SUB_ACCOUNT_TYPE", 			new FieldMapper("subAccountType"))
			.build();
	private final Map<String, FieldMapper> txnOpsInvalidAccountsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("'1'", 						new FieldMapper("  '1' ")).build();
	private final Map<String, FieldMapper> caseloadsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DESCRIPTION", 				new FieldMapper("description"))
			.put("CASELOAD_ID", 				new FieldMapper("caseloadId"))
			.build();
	private final Map<String, FieldMapper> accountCodesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("ACCOUNT_NAME", 				new FieldMapper("accountName"))
			.put("REC_ACCOUNT_CODE", 			new FieldMapper("recAccountCode"))
			.put("ACCOUNT_CODE", 				new FieldMapper("accountCode"))
			.put("POSTING_STATUS_FLAG", 		new FieldMapper("postingStatusFlag"))
			.put("ACCOUNT_TYPE", 				new FieldMapper("accountType"))
			.put("TXN_POSTING_TYPE", 			new FieldMapper("txnPostingType"))
			.put("ALL_CASELOAD_FLAG", 			new FieldMapper("allCaseloadFlag"))
			.put("MODIFY_USER_ID", 				new FieldMapper("modifyUserId"))
			.put("MODIFY_DATE", 				new FieldMapper("modifyDate"))
			.put("LIST_SEQ", 					new FieldMapper("listSeq"))
			.put("CASELOAD_TYPE", 				new FieldMapper("caseloadType"))
			.put("PARENT_ACCOUNT_CODE", 		new FieldMapper("parentAccountCode"))
			.put("CREATE_DATETIME", 			new FieldMapper("createDatetime"))
			.put("CREATE_USER_ID", 				new FieldMapper("createUserId"))
			.put("MODIFY_DATETIME", 			new FieldMapper("modifyDatetime"))
			.put("SEAL_FLAG", 					new FieldMapper("sealFlag"))
			.build();
	private final Map<String, FieldMapper> mMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CODE", 						new FieldMapper("code"))
			.put("DESCRIPTION", 				new FieldMapper("description"))
			.put("ACCOUNT_CODE", 				new FieldMapper("accountCode"))
			.put("CASELOAD_ID", 				new FieldMapper("caseloadId"))
			.build();
	private final Map<String, FieldMapper> transactionOperationsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("'1'", 						new FieldMapper("  '1' "))
			.build();

	/**
	 * Creates new OcmcoactRepositoryImpl class Object
	 */
	public OcmcoactRepositoryImpl() {
		// OcmcoactRepositoryImpl
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            AccountCodes
	 *
	 * @return List<AccountCodes>
	 */
	public List<AccountCodes> acCodeExecuteQuery(AccountCodes objSearchDao) {
		final String sql = getQuery("OCMCOACT_ACCODE_FIND_ACCOUNT_CODES");
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
			if (objSearchDao != null && objSearchDao.getAccountName() != null
					&& !("").equals(objSearchDao.getAccountName())) {
				sqlQuery.append("  ACCOUNT_NAME  = :accountName " + " and");
				params.addValue("accountName", objSearchDao.getAccountName().trim());
			}
			if (objSearchDao != null && objSearchDao.getPostingStatusFlag() != null
					&& !("").equals(objSearchDao.getPostingStatusFlag())) {
				sqlQuery.append("  POSTING_STATUS_FLAG   = :postingStatusFlag" + " and");
				params.addValue("postingStatusFlag", objSearchDao.getPostingStatusFlag());
			}
			if (objSearchDao != null && objSearchDao.getAccountType() != null
					&& !("").equals(objSearchDao.getAccountType())) {
				sqlQuery.append("  ACCOUNT_TYPE   = :accountType" + " and");
				params.addValue("accountType", objSearchDao.getAccountType().trim());
			}
			if (objSearchDao != null && objSearchDao.getTxnPostingType() != null
					&& !("").equals(objSearchDao.getTxnPostingType())) {
				sqlQuery.append("  TXN_POSTING_TYPE   = :txnPostingType" + " and");
				params.addValue("txnPostingType", objSearchDao.getTxnPostingType().trim());
			}
			if (objSearchDao != null && objSearchDao.getSubAccountType() != null
					&& !("").equals(objSearchDao.getSubAccountType())) {
				sqlQuery.append("  SUB_ACCOUNT_TYPE   = :subAccountType" + " and");
				params.addValue("subAccountType", objSearchDao.getSubAccountType().trim());
			}
			if (objSearchDao != null && objSearchDao.getRecAccountCode() != null
					&& !("").equals(objSearchDao.getRecAccountCode())) {
				sqlQuery.append("  REC_ACCOUNT_CODE   = :recAccountCode" + " and");
				params.addValue("recAccountCode", objSearchDao.getRecAccountCode());
			}
			if (objSearchDao != null && objSearchDao.getParentAccountCode() != null
					&& !("").equals(objSearchDao.getParentAccountCode())) {
				sqlQuery.append("  PARENT_ACCOUNT_CODE   = :parentAccountCode" + " and");
				params.addValue("parentAccountCode", objSearchDao.getParentAccountCode());
			}
			if (objSearchDao != null && objSearchDao.getAllCaseloadFlag() != null
					&& !("").equals(objSearchDao.getAllCaseloadFlag())) {
				sqlQuery.append("  ALL_CASELOAD_FLAG   = :allCaseloadFlag" + " and");
				params.addValue("allCaseloadFlag", objSearchDao.getAllCaseloadFlag());
			}
			sqlQuery.append(" CASELOAD_TYPE = :CASELOADTYPE ");
			params.addValue("CASELOADTYPE", objSearchDao.getCaseloadType());
		}
		preparedSql = sqlQuery.toString().trim();
		if (preparedSql.endsWith("where")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 5);
		}
		if (preparedSql.endsWith("and")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 3);
		}
		preSqlQuery = preparedSql.concat(" ORDER BY ACCOUNT_CODE, ACCOUNT_NAME ");

		final RowMapper<AccountCodes> AccountCodesRowMapper = Row2BeanRowMapper.makeMapping(sql, AccountCodes.class,
				accountCodesMapping);
		List<AccountCodes> returnList = new ArrayList<>();
		returnList = namedParameterJdbcTemplate.query(preSqlQuery, params, AccountCodesRowMapper);
		return returnList;
	}

	/**
	 * This method is used to insert the records in the data base tables based
	 * on
	 *
	 * @param lstAccountCodes
	 *            List<AccountCodes>
	 *
	 * @return List<Integer>
	 * 
	 */
	public Integer acCodeInsertAccountCodes(final List<AccountCodes> lstAccountCodes) {
		String sql = getQuery("OCMCOACT_ACCODE_INSERT_ACCOUNT_CODES");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (AccountCodes accountCodes : lstAccountCodes) {
			parameters.add(new BeanPropertySqlParameterSource(accountCodes));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstAccountCodes.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstAccountCodes
	 *            List<AccountCodes>
	 */
	public Integer acCodeUpdateAccountCodes(final List<AccountCodes> lstAccountCodes) {
		String sql = getQuery("OCMCOACT_ACCODE_UPDATE_ACCOUNT_CODES");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (AccountCodes accountCodes : lstAccountCodes) {
			parameters.add(new BeanPropertySqlParameterSource(accountCodes));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstAccountCodes.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

	/**
	 * This method is used to delete records from data base tables based on
	 *
	 * @param lstAccountCodes
	 *            List<AccountCodes>
	 */
	public Integer acCodeDeleteAccountCodes(final List<AccountCodes> lstAccountCodes) {
		String sql = getQuery("OCMCOACT_ACCODE_DELETE_ACCOUNT_CODES");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (AccountCodes accountCodes : lstAccountCodes) {
			parameters.add(new BeanPropertySqlParameterSource(accountCodes));
		}
		try {
			String tableName = "ACCOUNT_CODES";
			String whereClause = "ACCOUNT_CODE=:accountCode";
			batchUpdatePreDeletedRows(tableName, whereClause , parameters);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method acCodeDeleteAccountCodes", e);
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstAccountCodes.size() == returnArray.length) {
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
	public List<AccountCodes> cgfkAcCodeRecAccountCodeRecordGroup() {
		final String sql = getQuery("OCMCOACT_FIND_CGFKACCODERECACCOUNTCODE");
		final RowMapper<AccountCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, AccountCodes.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<ReferenceCodes>
	 */
	public List<AccountCodes> cgfkAcCodeParentCodeRecordGroup() {
		final String sql = getQuery("OCMCOACT_FIND_CGFKACCODEPARENTCODE");
		final RowMapper<AccountCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, AccountCodes.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<ReferenceCodes>
	 */
	public List<Caseloads> cgfkCsldAcdCaseloadIdRecordGroup() {
		final String sql = getQuery("OCMCOACT_FIND_CGFKCSLDACDCASELOADID");
		final RowMapper<Caseloads> mRowMapper = Row2BeanRowMapper.makeMapping(sql, Caseloads.class, mMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (Exception e) {
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<ReferenceCodes>
	 */
	public List<ReferenceCodes> cgfkAcCodeAccountTypeRecordGroup() {
		final String sql = getQuery("OCMCOACT_FIND_CGFKACCODEACCOUNTTYPE");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (Exception e) {
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<ReferenceCodes>
	 */
	public List<ReferenceCodes> cgfkAcCodeTxnPostingTypeRecordGroup() {
		final String sql = getQuery("OCMCOACT_FIND_CGFKACCODETXNPOSTINGTYPE");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (Exception e) {
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<ReferenceCodes>
	 */
	public List<ReferenceCodes> cgfkAcCodeSubAccountTypeRecordGroup() {
		final String sql = getQuery("OCMCOACT_FIND_CGFKACCODESUBACCOUNTTYPE");
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
	 * cgfkchkAcCodeAcCodeAcCo
	 *
	 * @param params
	 *
	 */
	public List<AccountCodes> cgfkchkAcCodeAcCodeAcCo(AccountCodes paramBean) {
		final String sql = getQuery("OCMCOACT_CGFKCHK_AC_CODE_AC_CODE_AC_CO");
		final RowMapper<AccountCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, AccountCodes.class,
				accountCodesMapping);
		List<AccountCodes> returnList = new ArrayList<>();
		returnList = namedParameterJdbcTemplate.query(sql, createParams(), columnRowMapper);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfkchkAcCodeAcSubAcct
	 *
	 * @param params
	 *
	 */
	public List<ReferenceCodes> cgfkchkAcCodeAcSubAcct(ReferenceCodes paramBean) {
		final String sql = getQuery("OCMCOACT_CGFKCHK_AC_CODE_AC_SUB_ACCT_T");
		final RowMapper<ReferenceCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				referenceCodesMapping);
		List<ReferenceCodes> returnList = new ArrayList<>();
		returnList = namedParameterJdbcTemplate.query(sql, createParams(), columnRowMapper);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfkchkAcCodeAcPostType
	 *
	 * @param params
	 *
	 */
	public List<ReferenceCodes> cgfkchkAcCodeAcPostType(ReferenceCodes paramBean) {
		final String sql = getQuery("OCMCOACT_CGFKCHK_AC_CODE_AC_POST_TYPE_");
		final RowMapper<ReferenceCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				referenceCodesMapping);
		List<ReferenceCodes> returnList = new ArrayList<>();
		returnList = namedParameterJdbcTemplate.query(sql, createParams(), columnRowMapper);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfkchkAcCodeAcAcctType
	 *
	 * @param params
	 *
	 */
	public List<ReferenceCodes> cgfkchkAcCodeAcAcctType(ReferenceCodes paramBean) {
		final String sql = getQuery("OCMCOACT_CGFKCHK_AC_CODE_AC_ACCT_TYPE_");
		final RowMapper<ReferenceCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				referenceCodesMapping);
		List<ReferenceCodes> returnList = new ArrayList<>();
		returnList = namedParameterJdbcTemplate.query(sql, createParams(), columnRowMapper);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfkchkCsldAcdCsldAcdCsl
	 *
	 * @param params
	 *
	 */
	public List<Caseloads> cgfkchkCsldAcdCsldAcdCsl(Caseloads paramBean) {
		final String sql = getQuery("OCMCOACT_CGFKCHK_CSLD_ACD_CSLD_ACD_CSL");
		final RowMapper<Caseloads> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, Caseloads.class,
				caseloadsMapping);
		List<Caseloads> returnList = new ArrayList<>();
		returnList = namedParameterJdbcTemplate.query(sql, createParams(), columnRowMapper);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgrichkAccountCodes
	 *
	 * @param params
	 *
	 */
	public List<TransactionOperations> cgriChkAccountCodes(TransactionOperations paramBean) {
		final String sql = getQuery("OCMCOACT_CGRICHK_ACCOUNT_CODES");
		final RowMapper<TransactionOperations> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				TransactionOperations.class, transactionOperationsMapping);
		List<TransactionOperations> returnList = new ArrayList<>();
		returnList = namedParameterJdbcTemplate.query(sql, createParams(), columnRowMapper);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgrichkAccountCodes
	 *
	 * @param params
	 *
	 */
	public List<TransactionOperations> cgrichkAccountCodes(TransactionOperations paramBean) {
		final String sql = getQuery("OCMCOACT_CGRICHK_ACCOUNT_CODES");
		final RowMapper<TransactionOperations> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				TransactionOperations.class, transactionOperationsMapping);
		List<TransactionOperations> returnList = new ArrayList<>();
		returnList = namedParameterJdbcTemplate.query(sql, createParams(), columnRowMapper);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgrichkAccountCodes
	 *
	 * @param params
	 *
	 */
	public List<TxnOpsInvalidAccounts> cgrichkAccountCodes(TxnOpsInvalidAccounts paramBean) {
		final String sql = getQuery("OCMCOACT_CGRICHK_ACCOUNT_CODES");
		final RowMapper<TxnOpsInvalidAccounts> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				TxnOpsInvalidAccounts.class, txnOpsInvalidAccountsMapping);
		List<TxnOpsInvalidAccounts> returnList = new ArrayList<>();
		returnList = namedParameterJdbcTemplate.query(sql, createParams(), columnRowMapper);
		return returnList;
	}

	@Override
	public List<AccountCodes> caselaodAccountCodes(final Integer accountCode, final String caseloadId) {
		final String sql = getQuery("OCMCOACT_CASELAOD_ACCOUNT_CODES");
		final RowMapper<AccountCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, AccountCodes.class,
				accountCodesMapping);
		List<AccountCodes> txnEntryAmount = new ArrayList<>();
		try {
			txnEntryAmount = namedParameterJdbcTemplate.query(sql,
					createParams("ACODE", accountCode, "CSLDID", caseloadId), columnRowMapper);
		} catch (Exception e) {
			return txnEntryAmount;
		}
		return txnEntryAmount;
	}

	@Override
	public String chkSubAcTypeTxnCur(final Integer accountCode, final String caseloadId) {
		final String sql = getQuery("OCMCOACT_CHK_SUB_AC_TYPE_TXN_CUR");
		String subAcnType = null;
		try {
			subAcnType = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("ACODE", accountCode, "CSLDID", caseloadId), String.class);
		} catch (Exception e) {
			subAcnType = "AB";
			return subAcnType;
		}
		return subAcnType;
	}

	@Override
	public Integer chkDupSubAcTypeCur(final String caseloadId, final String subAcType) {
		final String sql = getQuery("OCMCOACT_CHK_DUP_SUB_AC_TYPE_CUR");
		Integer subAcnTypeCur = null;
		try {
			subAcnTypeCur = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("CSLDID", caseloadId, "NEWSUBACTYPE", subAcType), Integer.class);
		} catch (Exception e) {
			subAcnTypeCur = 11;
			return subAcnTypeCur;
		}
		return subAcnTypeCur;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * @param paramBean
	 * @return List<OffenderPostDatedTxns>
	 */
	public String accountCodeValidation(final Integer accountCode) {
		final String sql = getQuery("OCMCOACT_ACCOUNT_CODE_VALIDATION");
		String returnObj = null;
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams("ACCOUNTCODE", accountCode),
					String.class);
		} catch (Exception e) {
			return returnObj;
		}
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * @param paramBean
	 * @return List<OffenderPostDatedTxns>
	 */
	public String caseloadAccountCodeValidation(final Integer accountCode) {
		final String sql = getQuery("OCMCOACT_CASELOAD_ACCOUNT_CODE_VALIDATION");
		String returnObj = null;
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams("ACCOUNTCODE", accountCode),
					String.class);
		} catch (Exception e) {
			return returnObj;
		}
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * @param accountCode
	 * @return Integer
	 */
	public Integer transactionOperationsTxnOpeDr(final Integer accountCode) {
		final String sql = getQuery("OCMCOACT_CGRICHK_ACCOUNT_CODES_DR");
		Integer returnObj = 0;
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams("PACCOUNTCODE", accountCode),
					Integer.class);
		} catch (Exception e) {
			return returnObj;
		}
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * @param accountCode
	 * @return Integer
	 */
	public Integer transactionOperationsTxnOpeCr(final Integer accountCode) {
		final String sql = getQuery("OCMCOACT_CGRICHK_ACCOUNT_CODES_CR");
		Integer returnObj = 0;
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams("PACCOUNTCODE", accountCode),
					Integer.class);
		} catch (Exception e) {
			return returnObj;
		}
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * @param accountCode
	 * @return Integer
	 */
	public Integer transactionOperationsTxnOpeBankDr(final Integer accountCode) {
		final String sql = getQuery("OCMCOACT_CGRICHK_ACCOUNT_CODES_BANK_DR");
		Integer returnObj = 0;
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams("PACCOUNTCODE", accountCode),
					Integer.class);
		} catch (Exception e) {
			return returnObj;
		}
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * @param accountCode
	 * @return Integer
	 */
	public Integer transactionOperationsTxnOpeBankCr(final Integer accountCode) {
		final String sql = getQuery("OCMCOACT_CGRICHK_ACCOUNT_CODES_BANK_CR");
		Integer returnObj = 0;
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams("PACCOUNTCODE", accountCode),
					Integer.class);
		} catch (Exception e) {
			return returnObj;
		}
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * @param accountCode
	 * @return Integer
	 */
	public Integer transactionOperationsTxnOpeTxnNv(final Integer accountCode) {
		final String sql = getQuery("OCMCOACT_CGRICHK_ACCOUNT_CODES_TXN_INV");
		Integer returnObj = 0;
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams("PACCOUNTCODE", accountCode),
					Integer.class);
		} catch (Exception e) {
			return returnObj;
		}
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * @param accountCode
	 * @return Integer
	 */
	public Integer subAccountViewsAv(final Integer accountCode) {
		final String sql = getQuery("OCMCOACT_SUB_ACCOUNT_VIEWS_S_AV");
		Integer returnObj = 0;
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams("PACCOUNTCODE", accountCode),
					Integer.class);
		} catch (Exception e) {
			return returnObj;
		}
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * @param accountCode
	 * @return Integer
	 */
	public Integer payeeAccountBalancesValid(final Integer accountCode) {
		final String sql = getQuery("OCMCOACT_PAYEE_ACCOUNT_BALANCES");
		Integer returnObj = 0;
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams("PACCOUNTCODE", accountCode),
					Integer.class);
		} catch (Exception e) {
			return returnObj;
		}
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * @param accountCode
	 * @return Integer
	 */
	public Integer interestTransactionTypesValid(final Integer accountCode) {
		final String sql = getQuery("OCMCOACT_INTEREST_TRANSACTION_TYPES");
		Integer returnObj = 0;
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams("PACCOUNTCODE", accountCode),
					Integer.class);
		} catch (Exception e) {
			return returnObj;
		}
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * @param accountCode
	 * @return Integer
	 */
	public Integer glTransactionsValid(final Integer accountCode) {
		final String sql = getQuery("OCMCOACT_GL_TRANSACTIONS");
		Integer returnObj = 0;
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams("PACCOUNTCODE", accountCode),
					Integer.class);
		} catch (Exception e) {
			return returnObj;
		}
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * @param accountCode
	 * @return Integer
	 */
	public Integer offenderSubAccountsValid(final Integer accountCode) {
		final String sql = getQuery("OCMCOACT_OFFENDER_SUB_ACCOUNTS");
		Integer returnObj = 0;
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams("PACCOUNTCODE", accountCode),
					Integer.class);
		} catch (Exception e) {
			return returnObj;
		}
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * @param accountCode
	 * @return Integer
	 */
	public Integer caseloadDeductionProfilesValid(final Integer accountCode) {
		final String sql = getQuery("OCMCOACT_CASELOAD_DEDUCTION_PROFILES");
		Integer returnObj = 0;
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams("PACCOUNTCODE", accountCode),
					Integer.class);
		} catch (Exception e) {
			return returnObj;
		}
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * @param accountCode
	 * @return Integer
	 */
	public Integer accountCodesAcCodeValid(final Integer accountCode) {
		final String sql = getQuery("OCMCOACT_ACCOUNT_CODES_AC_CODE");
		Integer returnObj = 0;
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams("PACCOUNTCODE", accountCode),
					Integer.class);
		} catch (Exception e) {
			return returnObj;
		}
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * @param accountCode
	 * @return Integer
	 */
	public Integer lTxnOpExistsCur(final Integer accountCode) {
		final String sql = getQuery("OCMCOACT_TRANSACTION_OPERATIONS_CANNOT_DELETE");
		Integer returnObj = 0;
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams("PACCOUNTCODE", accountCode),
					Integer.class);
		} catch (Exception e) {
			return returnObj;
		}
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * @param accountCode
	 * @return Integer
	 */
	public Integer lGlExists(final Integer accountCode) {
		final String sql = getQuery("OCMCOACT_GL_EXISTS_CANNOT_DELETE");
		Integer returnObj = 0;
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams("PACCOUNTCODE", accountCode),
					Integer.class);
		} catch (Exception e) {
			return returnObj;
		}
		return returnObj;
	}

	@Override
	public Integer caseloadAccountSummaries(final AccountCodes lstAccountCodes) {
		final String sql = getQuery("OCMCOACT_DELETE_CASELOAD_ACCOUNT_SUMMARIES");
		Integer returnArray = null;
		returnArray = namedParameterJdbcTemplate.update(sql, new BeanPropertySqlParameterSource(lstAccountCodes));
		if (returnArray != 0) {
			return returnArray;
		}
		return returnArray;
	}

	@Override
	public Integer caseloadCurrentAccountsTxns(final AccountCodes lstAccountCodes) {
		final String sql = getQuery("OCMCOACT_DELETE_CASELOAD_CURRENT_ACCOUNTS_TXNS");
		Integer returnArray = null;
		returnArray = namedParameterJdbcTemplate.update(sql, new BeanPropertySqlParameterSource(lstAccountCodes));
		if (returnArray != 0) {
			return returnArray;
		}
		return returnArray;
	}

	@Override
	public Integer caseloadCurrentAccountsBase(final AccountCodes lstAccountCodes) {
		final String sql = getQuery("OCMCOACT_DELETE_CASELOAD_CURRENT_ACCOUNTS_BASE");
		Integer returnArray = null;
		returnArray = namedParameterJdbcTemplate.update(sql, new BeanPropertySqlParameterSource(lstAccountCodes));
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
	public Integer caseloadAccountCodesInsert(final AccountCodes lstAccountCodes) {
		Integer genSeq = 0;
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		inParamMap.put("P_CASELOAD_ID", lstAccountCodes.getCaseloadId());
		inParamMap.put("P_ACCOUNT_CODE", lstAccountCodes.getAccountCode());
		namedParameterJdbcTemplate
				.update("call OMS_OWNER.OTMONCOA_GEN_ACCOUNT_CODES(:P_CASELOAD_ID, :P_ACCOUNT_CODE)", inParamMap);
		genSeq = 2;
		return genSeq;
	}

}

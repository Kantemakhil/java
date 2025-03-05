package net.syscon.s4.inmate.trust.financialsmaintenance.transaction.impl;

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
import net.syscon.s4.common.beans.Caseloads;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.TransactionTypes;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.AccountCodes;
import net.syscon.s4.im.beans.OmsModules;
import net.syscon.s4.im.beans.TransactionOperation;
import net.syscon.s4.inmate.trust.financialsmaintenance.transaction.OcmtropsRepository;

/**
 * Class OcmtropsRepositoryImpl
 */
@Repository
public class OcmtropsRepositoryImpl extends RepositoryBase implements OcmtropsRepository {
	
	private static Logger logger = LogManager.getLogger(OcmtropsRepositoryImpl.class.getName());

	private final Map<String, FieldMapper> transactionTypesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("LIST_SEQ", 						new FieldMapper("listSeq"))
			.put("DESCRIPTION", 					new FieldMapper("description"))
			.put("CASELOAD_TYPE", 					new FieldMapper("caseloadType"))
			.put("TXN_TYPE", 						new FieldMapper("txnType"))
			.build();
	private final Map<String, FieldMapper> referenceCodesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("LIST_SEQ", 						new FieldMapper("listSeq"))
			.put("CODE", 							new FieldMapper("code"))
			.put("DESCRIPTION", 					new FieldMapper("description"))
			.put("TXN_OPERATION_TYPE", 				new FieldMapper("txnOperationType"))
			.build();
	private final Map<String, FieldMapper> omsModulesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("MODULE_NAME", 					new FieldMapper("moduleName"))
			.put("DESCRIPTION", 					new FieldMapper("description"))
			.build();
	private final Map<String, FieldMapper> caseloadsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("LIST_SEQ", 						new FieldMapper("listSeq"))
			.put("DESCRIPTION", 					new FieldMapper("description"))
			.put("CASELOAD_TYPE", 					new FieldMapper("caseloadType"))
			.put("CASELOAD_ID", 					new FieldMapper("caseloadId"))
			.build();
	private final Map<String, FieldMapper> accountCodesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("ACCOUNT_NAME", 					new FieldMapper("accountName"))
			.put("CR_ACCOUNT_CODE", 				new FieldMapper("crAccountCode"))
			.put("BANK_DR_ACCOUNT_CODE", 			new FieldMapper("bankDrAccountCode"))
			.put("CASELOAD_TYPE", 					new FieldMapper("caseloadType"))
			.put("ACCOUNT_CODE", 					new FieldMapper("accountCode"))
			.put("DR_ACCOUNT_CODE", 				new FieldMapper("drAccountCode"))
			.put("BANK_CR_ACCOUNT_CODE", 			new FieldMapper("bankCrAccountCode"))
			.build();
	private final Map<String, FieldMapper> transactionOperationsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CREATE_USER_ID", 					new FieldMapper("createUserId"))
			.put("RECEIPT_PRODUCTION_FLAG", 		new FieldMapper("receiptProductionFlag"))
			.put("MODIFY_USER_ID", 					new FieldMapper("modifyUserId"))
			.put("LIST_SEQ",						new FieldMapper("listSeq"))
			.put("BANK_CR_ACCOUNT_CODE", 			new FieldMapper("bankCrAccountCode"))
			.put("MODULE_NAME", 					new FieldMapper("moduleName"))
			.put("CR_ACCOUNT_CODE", 				new FieldMapper("crAccountCode"))
			.put("TXN_ITEM_CODE", 					new FieldMapper("txnItemCode"))
			.put("CHEQUE_PRODUCTION_FLAG", 			new FieldMapper("chequeProductionFlag"))
			.put("TXN_OPERATION_SEQ", 				new FieldMapper("txnOperationSeq"))
			.put("INVALID_ACCOUNTS_FLAG", 			new FieldMapper("invalidAccountsFlag"))
			.put("MODIFY_DATE", 					new FieldMapper("modifyDate"))
			.put("CASELOAD_ID", 					new FieldMapper("caseloadId"))
			.put("CHEQUE_PAYEE_TYPE", 				new FieldMapper("chequePayeeType"))
			.put("SEAL_FLAG", 						new FieldMapper("sealFlag"))
			.put("CREATE_DATETIME", 				new FieldMapper("createDatetime"))
			.put("TXN_TYPE", 						new FieldMapper("txnType"))
			.put("BANK_DR_ACCOUNT_CODE", 			new FieldMapper("bankDrAccountCode"))
			.put("UPDATE_ALLOWED_FLAG", 			new FieldMapper("updateAllowedFlag"))
			.put("MODIFY_DATETIME", 				new FieldMapper("modifyDatetime"))
			.put("DR_ACCOUNT_CODE", 				new FieldMapper("drAccountCode"))
			.put("TXN_OPERATION_TYPE", 				new FieldMapper("txnOperationType"))
			.build();
	
	/**
	 * Creates new OcmtropsRepositoryImpl class Object
	 */
	public OcmtropsRepositoryImpl() {
		// OcmtropsRepositoryImpl
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            TransactionOperations
	 * @return List<TransactionOperations>
	 */
	public List<TransactionOperation> txnOperExecuteQuery(final TransactionOperation objSearchDao) {
		final String sql = getQuery("OCMTROPS_TXNOPER_FIND_TRANSACTION_OPERATIONS");
		String preparedSql = null;
		final StringBuffer sqlQuery = new StringBuffer();
		String preSqlQuery = null;
		final MapSqlParameterSource params = new MapSqlParameterSource();
		sqlQuery.append(sql);
		if (objSearchDao != null) {
			sqlQuery.append(" where ");
			if (objSearchDao != null && objSearchDao.getCaseloadId() != null
					&& !("").equals(objSearchDao.getCaseloadId().trim())) {
				sqlQuery.append(" CASELOAD_ID  like :caseloadId  " + " and");
				params.addValue("caseloadId", objSearchDao.getCaseloadId().trim());
			}
			if (objSearchDao != null && objSearchDao.getModuleName() != null
					&& !("").equals(objSearchDao.getModuleName().trim())) {
				sqlQuery.append("  MODULE_NAME  like :moduleName " + " and");
				params.addValue("moduleName", objSearchDao.getModuleName().trim());
			}
			if (objSearchDao != null && objSearchDao.getTxnType() != null
					&& !("").equals(objSearchDao.getTxnType().trim())) {
				sqlQuery.append(" TXN_TYPE  like :txnType  " + " and");
				params.addValue("txnType", objSearchDao.getTxnType().trim());
			}
			if (objSearchDao != null && objSearchDao.getDrAccountCodeTemp() != null
					&& !("").equals(objSearchDao.getDrAccountCodeTemp())) {
				sqlQuery.append(" DR_ACCOUNT_CODE  =:drAccountCodeTemp " + " and ");
				params.addValue("drAccountCodeTemp", objSearchDao.getDrAccountCodeTemp());
			}
			if (objSearchDao != null && objSearchDao.getCrAccountCodeTemp() != null
					&& !("").equals(objSearchDao.getCrAccountCodeTemp())) {
				sqlQuery.append(" CR_ACCOUNT_CODE  = :crAccountCodeTemp  " + " and");
				params.addValue("crAccountCodeTemp", objSearchDao.getCrAccountCodeTemp());
			}
			if (objSearchDao != null && objSearchDao.getBankDrAccountCodeTemp() != null
					&& !("").equals(objSearchDao.getBankDrAccountCodeTemp())) {
				sqlQuery.append("  BANK_DR_ACCOUNT_CODE   = :bankDrAccountCodeTemp " + " and");
				params.addValue("bankDrAccountCodeTemp", objSearchDao.getBankDrAccountCodeTemp());
			}
			if (objSearchDao != null && objSearchDao.getBankCrAccountCodeTemp() != null
					&& !("").equals(objSearchDao.getBankCrAccountCodeTemp())) {
				sqlQuery.append("  BANK_CR_ACCOUNT_CODE   = :bankCrAccountCodeTemp" + " and");
				params.addValue("bankCrAccountCodeTemp", objSearchDao.getBankCrAccountCodeTemp());
			}
			if (objSearchDao != null && objSearchDao.getInvalidAccountsFlag() != null
					&& !("").equals(objSearchDao.getInvalidAccountsFlag().trim())) {
				if (objSearchDao.getInvalidAccountsFlag().equals("true")) {
					objSearchDao.setInvalidAccountsFlag("Y");
				} else {
					objSearchDao.setInvalidAccountsFlag("N");
				}
				sqlQuery.append("  INVALID_ACCOUNTS_FLAG  = :invalidAccountsFlag " + " and");
				params.addValue("invalidAccountsFlag", objSearchDao.getInvalidAccountsFlag());
			}
			if (objSearchDao != null && objSearchDao.getTxnOperationType() != null
					&& !("").equals(objSearchDao.getTxnOperationType())) {
				sqlQuery.append("  TXN_OPERATION_TYPE   = :txnOperationType" + " and");
				params.addValue("txnOperationType", objSearchDao.getTxnOperationType());
			}
			if (objSearchDao != null && objSearchDao.getTxnOperationSeq() != null
					&& !("").equals(objSearchDao.getTxnOperationSeq())) {
				sqlQuery.append("  TXN_OPERATION_SEQ  = :txnOperationSeq " + " and");
				params.addValue("txnOperationSeq", objSearchDao.getTxnOperationSeq());
			}
			if (objSearchDao != null && objSearchDao.getChequeProductionFlag() != null
					&& !("").equals(objSearchDao.getChequeProductionFlag().trim())) {
				if (objSearchDao.getChequeProductionFlag().equals("true")) {
					objSearchDao.setChequeProductionFlag("Y");
				} else {
					objSearchDao.setChequeProductionFlag("N");
				}
				sqlQuery.append("  CHEQUE_PRODUCTION_FLAG  = :chequeProductionFlag " + " and");
				params.addValue("chequeProductionFlag", objSearchDao.getChequeProductionFlag());
			}
			if (objSearchDao != null && objSearchDao.getChequePayeeType() != null
					&& !("").equals(objSearchDao.getChequePayeeType())) {
				sqlQuery.append("  CHEQUE_PAYEE_TYPE   = :chequePayeeType" + " and");
				params.addValue("chequePayeeType", objSearchDao.getChequePayeeType());
			}
			if (objSearchDao != null && objSearchDao.getReceiptProductionFlag() != null
					&& !("").equals(objSearchDao.getReceiptProductionFlag())) {
				if (objSearchDao.getReceiptProductionFlag().equals("true")) {
					objSearchDao.setReceiptProductionFlag("Y");
				} else {
					objSearchDao.setReceiptProductionFlag("N");
				}
				sqlQuery.append("  RECEIPT_PRODUCTION_FLAG  = :receiptProductionFlag " + " and");
				params.addValue("receiptProductionFlag", objSearchDao.getReceiptProductionFlag());
			}
			if (objSearchDao != null && objSearchDao.getListSeq() != null && !("").equals(objSearchDao.getListSeq())) {
				sqlQuery.append("  LIST_SEQ   = :listSeq" + " and");
				params.addValue("listSeq", objSearchDao.getListSeq());
			}
			if (objSearchDao != null && objSearchDao.getUpdateAllowedFlag() != null
					&& !("").equals(objSearchDao.getUpdateAllowedFlag())) {
				if (objSearchDao.getUpdateAllowedFlag().equals("true")) {
					objSearchDao.setUpdateAllowedFlag("Y");
				} else {
					objSearchDao.setUpdateAllowedFlag("N");
				}
				sqlQuery.append("  UPDATE_ALLOWED_FLAG  = :updateAllowedFlag " + " and");
				params.addValue("updateAllowedFlag", objSearchDao.getUpdateAllowedFlag());
			}
			sqlQuery.append(
					" TRANSACTION_OPERATIONS.CASELOAD_ID IN (SELECT CASELOAD_ID FROM CASELOADS WHERE CASELOADS.CASELOAD_TYPE = :CASELOADTYPE)");
			params.addValue("CASELOADTYPE", objSearchDao.getCaseloadType());
		}
		preparedSql = sqlQuery.toString().trim();
		if (preparedSql.endsWith("where")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 5);
		}
		if (preparedSql.endsWith("and")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 3);
		}
		preSqlQuery = preparedSql.concat(" ORDER BY MODULE_NAME, TXN_TYPE, LIST_SEQ ");

		final RowMapper<TransactionOperation> TransactionOperationsRowMapper = Row2BeanRowMapper.makeMapping(sql,
				TransactionOperation.class, transactionOperationsMapping);
		List<TransactionOperation> returnList = new ArrayList<TransactionOperation>();
		returnList = namedParameterJdbcTemplate.query(preSqlQuery, params, TransactionOperationsRowMapper);
		return returnList;
	}

	/**
	 * This method is used to insert the records in the data base tables based
	 * on
	 *
	 * @param lstTransactionOperations
	 *            List<TransactionOperations>
	 * @return Integer
	 */
	public Integer txnOperInsertTransactionOperations(final List<TransactionOperation> lstTransactionOperations) {
		String sql = getQuery("OCMTROPS_TXNOPER_INSERT_TRANSACTION_OPERATIONS");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (TransactionOperation transactionOperations : lstTransactionOperations) {
			parameters.add(new BeanPropertySqlParameterSource(transactionOperations));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstTransactionOperations.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstTransactionOperations
	 *            List<TransactionOperations>
	 * @return Integer
	 */
	public Integer txnOperUpdateTransactionOperations(final List<TransactionOperation> lstTransactionOperations) {
		String sql = getQuery("OCMTROPS_TXNOPER_UPDATE_TRANSACTION_OPERATIONS");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (TransactionOperation transactionOperations : lstTransactionOperations) {
			parameters.add(new BeanPropertySqlParameterSource(transactionOperations));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstTransactionOperations.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to delete records from data base tables based on
	 *
	 * @param lstTransactionOperations
	 *            List<TransactionOperations>
	 * @return Integer
	 */
	public Integer txnOperDeleteTransactionOperations(final List<TransactionOperation> lstTransactionOperations) {
		String sql = getQuery("OCMTROPS_TXNOPER_DELETE_TRANSACTION_OPERATIONS");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (TransactionOperation transactionOperations : lstTransactionOperations) {
			parameters.add(new BeanPropertySqlParameterSource(transactionOperations));
		}
		try {
			String tableName = "TRANSACTION_OPERATIONS";
			String whereClause = "MODULE_NAME  = :moduleName AND TXN_TYPE  = :txnType AND TXN_OPERATION_SEQ  = :txnOperationSeq AND CASELOAD_ID  = :caseloadId";
			batchUpdatePreDeletedRows(tableName, whereClause , parameters);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method txnOperDeleteTransactionOperations", e);
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstTransactionOperations.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<Caseloads>
	 */
	public List<Caseloads> cgfkTxnOperCaseloadIdRecordGroup(String userName) {
		final String sql = getQuery("OCMTROPS_FIND_CGFKTXNOPERCASELOADID");
		final RowMapper<Caseloads> caseloadsRowMapper = Row2BeanRowMapper.makeMapping(sql, Caseloads.class,
				caseloadsMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams("userId",userName), caseloadsRowMapper);
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<TransactionTypes>
	 */
	public List<TransactionTypes> cgfkTxnOperTxnTypeRecordGroup(String userName) {
		final String sql = getQuery("OCMTROPS_FIND_CGFKTXNOPERTXNTYPE");
		final RowMapper<TransactionTypes> transactionTypesRowMapper = Row2BeanRowMapper.makeMapping(sql,
				TransactionTypes.class, transactionTypesMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams("userId",userName), transactionTypesRowMapper);
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<AccountCodes>
	 */
	public List<AccountCodes> cgfkTxnOperDrAccountCodeRecordGroup(String userName) {
		final String sql = getQuery("OCMTROPS_FIND_CGFKTXNOPERDRACCOUNTCODE");
		final RowMapper<AccountCodes> accountCodesRowMapper = Row2BeanRowMapper.makeMapping(sql, AccountCodes.class,
				accountCodesMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams("userId",userName), accountCodesRowMapper);
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<AccountCodes>
	 */
	public List<AccountCodes> cgfkTxnOperCrAccountCodeRecordGroup(String userName) {
		final String sql = getQuery("OCMTROPS_FIND_CGFKTXNOPERCRACCOUNTCODE");
		final RowMapper<AccountCodes> accountCodesRowMapper = Row2BeanRowMapper.makeMapping(sql, AccountCodes.class,
				accountCodesMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams("userId",userName), accountCodesRowMapper);
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<AccountCodes>
	 */
	public List<AccountCodes> cgfkTxnOperBankDrAccountRecordGroup(String userName) {
		final String sql = getQuery("OCMTROPS_FIND_CGFKTXNOPERBANKDRACCOUNT");
		final RowMapper<AccountCodes> accountCodesRowMapper = Row2BeanRowMapper.makeMapping(sql, AccountCodes.class,
				accountCodesMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams("userId",userName), accountCodesRowMapper);
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<AccountCodes>
	 */
	public List<AccountCodes> cgfkTxnOperBankCrAccountRecordGroup(String userName) {
		final String sql = getQuery("OCMTROPS_FIND_CGFKTXNOPERBANKCRACCOUNT");
		final RowMapper<AccountCodes> accountCodesRowMapper = Row2BeanRowMapper.makeMapping(sql, AccountCodes.class,
				accountCodesMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams("userId",userName), accountCodesRowMapper);
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<ReferenceCodes>
	 */
	public List<ReferenceCodes> cgfkTxnOperTxnOperationTyRecordGroup() {
		final String sql = getQuery("OCMTROPS_FIND_CGFKTXNOPERTXNOPERATIONTY");
		final RowMapper<ReferenceCodes> referenceCodesRowMapper = Row2BeanRowMapper.makeMapping(sql,
				ReferenceCodes.class, referenceCodesMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), referenceCodesRowMapper);
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<OmsModules>
	 */
	public List<OmsModules> cgfkTxnOperModuleNameRecordGroup() {
		final String sql = getQuery("OCMTROPS_FIND_CGFKTXNOPERMODULENAME");
		final RowMapper<OmsModules> omsModulesRowMapper = Row2BeanRowMapper.makeMapping(sql, OmsModules.class,
				omsModulesMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), omsModulesRowMapper);
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * @param paramBean
	 * @return Caseloads
	 */
	public Caseloads cgfkchkTxnOperTxnOperCsl(final Caseloads paramBean) {
		final String sql = getQuery("OCMTROPS_CGFKCHK_TXN_OPER_TXN_OPER_CSL");
		final RowMapper<Caseloads> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, Caseloads.class,
				caseloadsMapping);
		Caseloads returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * @param paramBean
	 * @return OmsModules
	 */
	public OmsModules cgfkchkTxnOperTxnOperOms(final OmsModules paramBean) {
		final String sql = getQuery("OCMTROPS_CGFKCHK_TXN_OPER_TXN_OPER_OMS");
		final RowMapper<OmsModules> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, OmsModules.class,
				omsModulesMapping);
		OmsModules returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * @param paramBean
	 * @return ReferenceCodes
	 */
	public ReferenceCodes cgfkchkTxnOperTxnOperRef(final ReferenceCodes paramBean) {
		final String sql = getQuery("OCMTROPS_CGFKCHK_TXN_OPER_TXN_OPER_REF");
		final RowMapper<ReferenceCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				referenceCodesMapping);
		ReferenceCodes returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * @param paramBean
	 * @return AccountCodes
	 */
	public AccountCodes cgfkchkTxnOperTxnOperBnk(final AccountCodes paramBean) {
		final String sql = getQuery("OCMTROPS_CGFKCHK_TXN_OPER_TXN_OPER_BNK");
		final RowMapper<AccountCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, AccountCodes.class,
				accountCodesMapping);
		AccountCodes returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * @param paramBean
	 * @return AccountCodes
	 */
	public AccountCodes cgfkchkTxnOperTxnOper(final AccountCodes paramBean) {
		final String sql = getQuery("OCMTROPS_CGFKCHK_TXN_OPER_TXN_OPER_2");
		final RowMapper<AccountCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, AccountCodes.class,
				accountCodesMapping);
		AccountCodes returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * @param paramBean
	 * @return AccountCodes
	 */
	public AccountCodes cgfkchkTxnOperTxnOperCr(final AccountCodes paramBean) {
		final String sql = getQuery("OCMTROPS_CGFKCHK_TXN_OPER_TXN_OPER_CR_");
		final RowMapper<AccountCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, AccountCodes.class,
				accountCodesMapping);
		AccountCodes returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * @param paramBean
	 * @return AccountCodes
	 */
	public AccountCodes cgfkchkTxnOperTxnOperDr(final AccountCodes paramBean) {
		final String sql = getQuery("OCMTROPS_CGFKCHK_TXN_OPER_TXN_OPER_DR_");
		final RowMapper<AccountCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, AccountCodes.class,
				accountCodesMapping);
		AccountCodes returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
		return returnObj;
	}

	public String txnTypeValidation(final String txnType, final String moduleName, final String caseloadId,
			final Long txnOperationSeq) {
		final String sql = getQuery("OCMTROPS_TXN_OPREATION_VALIDATION");
		String returnObj = null;
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams("TXNTYPE", txnType, "MODULENAME",
					moduleName, "CASELOADID", caseloadId, "TXNOPERATIONSEQ", txnOperationSeq), String.class);
		} catch (Exception e) {
			return returnObj;
		}
		return returnObj;
	}

}

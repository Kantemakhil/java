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
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.AccountCodes;
import net.syscon.s4.im.beans.TxnOpsInvalidAccounts;
import net.syscon.s4.inmate.trust.financialsmaintenance.transaction.OtuinvacRepository;

/**
 * Class OcmtropsRepositoryImpl
 */
@Repository
public class OtuinvacRepositoryImpl extends RepositoryBase implements OtuinvacRepository {
	
	private static Logger logger = LogManager.getLogger(OtuinvacRepositoryImpl.class.getName());

	private final Map<String, FieldMapper> transactionTypesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("MODULE_NAME", 					new FieldMapper("moduleName"))
			.put("TXN_TYPE", 						new FieldMapper("txnType"))
			.put("INVALID_ACCOUNT_CODE", 			new FieldMapper("invalidAccountCode"))
			.put("MODIFY_USER_ID", 					new FieldMapper("modifyUserId"))
			.put("MODIFY_DATE", 					new FieldMapper("modifyDate"))
			.put("LIST_SEQ", 						new FieldMapper("listSeq"))
			.put("CREATE_DATETIME",					new FieldMapper("createDatetime"))
			.put("CREATE_USER_ID", 					new FieldMapper("createUserId"))
			.put("MODIFY_DATETIME", 				new FieldMapper("modifyDatetime"))
			.put("SEAL_FLAG", 						new FieldMapper("sealFlag"))
			.build();
	private final Map<String, FieldMapper> personsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DESCRIPTION", 						    new FieldMapper("description"))
			.put("CODE", 									new FieldMapper("code"))
			.put("ACCOUNT_CODE", 							new FieldMapper("accountCode"))
			.build();
	
	/**
	 * Creates new OtuinvacRepositoryImpl class Object
	 */
	public OtuinvacRepositoryImpl() {
		// OtuinvacRepositoryImpl
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 * @return List<TxnOpsInvalidAccounts>
	 */
	public List<TxnOpsInvalidAccounts> txnInvExecuteQuery(TxnOpsInvalidAccounts searchBean) {
		final String sql = getQuery("OTUINVAC_FIND_TXN_OPS_INVALID_ACCOUNTS");
		final RowMapper<TxnOpsInvalidAccounts> TransactionPayeesRowMapper = Row2BeanRowMapper.makeMapping(sql,
				TxnOpsInvalidAccounts.class, transactionTypesMapping);
		List<TxnOpsInvalidAccounts> returnList = new ArrayList<TxnOpsInvalidAccounts>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql,
					createParams("MODULENAME", searchBean.getModuleName(), "TXNTYPE", searchBean.getTxnType()),
					TransactionPayeesRowMapper);
		} catch (Exception e) {
			return returnList;
		}
		return returnList;
	}

	/**
	 * getting cgfkTxnInvInvalidAccountCRecordGroup LOV values
	 */
	public List<AccountCodes> cgfkTxnInvInvalidAccountCRecordGroup() {
		final String sql = getQuery("OTUINVAC_TXN_INV_INVALID_ACCOUNT_CR");
		final RowMapper<AccountCodes> deductionTypesRowMapper = Row2BeanRowMapper.makeMapping(sql, AccountCodes.class,
				personsMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), deductionTypesRowMapper);
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	/**
	 * This method is used to insert the records in the data base tables based
	 * on
	 *
	 * @param lstTransactionOperations
	 *            List<TransactionOperations>
	 * @return Integer
	 */
	public Integer txnInvInsertTxnOpsInvalidAccounts(final List<TxnOpsInvalidAccounts> lstTransactionOperations) {
		String sql = getQuery("OTUINVAC_TXN_INV_INSERT_TRANSACTION");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (TxnOpsInvalidAccounts transactionOperations : lstTransactionOperations) {
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
	public Integer txnInvUpdateTxnOpsInvalidAccounts(final List<TxnOpsInvalidAccounts> lstTransactionOperations) {
		String sql = getQuery("OTUINVAC_TXN_INV_UPDATE_TRANSACTION");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (TxnOpsInvalidAccounts transactionOperations : lstTransactionOperations) {
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
	public Integer txnInvDeleteTxnOpsInvalidAccounts(final List<TxnOpsInvalidAccounts> lstTransactionOperations) {
		String sql = getQuery("OTUINVAC_TXN_INV_DELETE_TRANSACTION");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (TxnOpsInvalidAccounts transactionOperations : lstTransactionOperations) {
			parameters.add(new BeanPropertySqlParameterSource(transactionOperations));
		}
		try {
			String tableName = "TXN_OPS_INVALID_ACCOUNTS";
			String whereClause = "MODULE_NAME = :moduleName AND TXN_TYPE = :txnType AND INVALID_ACCOUNT_CODE = :invalidAccountCode";
			batchUpdatePreDeletedRows(tableName, whereClause , parameters);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method txnInvDeleteTxnOpsInvalidAccounts", e);
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstTransactionOperations.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}
}

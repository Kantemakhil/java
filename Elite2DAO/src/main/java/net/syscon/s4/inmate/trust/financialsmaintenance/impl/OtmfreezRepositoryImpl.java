package net.syscon.s4.inmate.trust.financialsmaintenance.impl;

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
import net.syscon.s4.common.beans.TransactionTypes;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.AccountCodes;
import net.syscon.s4.inmate.beans.FreezeDisbursements;
import net.syscon.s4.inmate.trust.financialsmaintenance.OtmfreezRepository;

/**
 * Class OtmfreezRepositoryImpl
 * 
 * @author Arkin Software Technologies
 * @version 1.0
 */
@Repository
public class OtmfreezRepositoryImpl extends RepositoryBase implements OtmfreezRepository {

	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger log = LogManager.getLogger(OtmfreezRepositoryImpl.class.getName());

	private final Map<String, FieldMapper> transactionTypesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("ACTIVE_FLAG", 				new FieldMapper("activeFlag"))
			.put("UPDATE_ALLOWED_FLAG", 		new FieldMapper("updateAllowedFlag"))
			.put("CASELOAD_TYPE", 				new FieldMapper("caseloadType"))
			.put("ALL_CASELOAD_FLAG", 			new FieldMapper("allCaseloadFlag"))
			.put("MODIFY_USER_ID", 				new FieldMapper("modifyUserId"))
			.put("TXN_TYPE", 					new FieldMapper("txnType"))
			.put("GROSS_NET_FLAG", 				new FieldMapper("grossNetFlag"))
			.put("EXPIRY_DATE", 				new FieldMapper("expiryDate"))
			.put("MANUAL_INVOICE_FLAG", 		new FieldMapper("manualInvoiceFlag"))
			.put("LIST_SEQ", 					new FieldMapper("listSeq"))
			.put("DESCRIPTION", 				new FieldMapper("description"))
			.put("MODIFY_DATE", 				new FieldMapper("modifyDate"))
			.put("CREDIT_OBLIGATION_TYPE", 		new FieldMapper("creditObligationType"))
			.put("TXN_USAGE", 					new FieldMapper("txnUsage")).build();
	private final Map<String, FieldMapper> accountCodesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("POSTING_STATUS_FLAG", 		new FieldMapper("postingStatusFlag"))
			.put("LIST_SEQ", 					new FieldMapper("listSeq"))
			.put("ACCOUNT_NAME", 				new FieldMapper("accountName"))
			.put("TXN_POSTING_TYPE", 			new FieldMapper("txnPostingType"))
			.put("REC_ACCOUNT_CODE", 			new FieldMapper("recAccountCode"))
			.put("ACCOUNT_TYPE", 				new FieldMapper("accountType"))
			.put("MODIFY_DATE", 				new FieldMapper("modifyDate"))
			.put("CASELOAD_TYPE", 				new FieldMapper("caseloadType"))
			.put("ACCOUNT_CODE", 				new FieldMapper("accountCode"))
			.put("ALL_CASELOAD_FLAG", 			new FieldMapper("allCaseloadFlag"))
			.put("SUB_ACCOUNT_TYPE", 			new FieldMapper("subAccountType"))
			.put("MODIFY_USER_ID", 				new FieldMapper("modifyUserId")).build();
	private final Map<String, FieldMapper> mMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("REC_ACCOUNT_CODE", 			new FieldMapper("recAccountCode"))
			.put("ACCOUNT_TYPE", 				new FieldMapper("accountType"))
			.put("ACTIVE_FLAG", 				new FieldMapper("activeFlag"))
			.put("UPDATE_ALLOWED_FLAG", 		new FieldMapper("updateAllowedFlag"))
			.put("ALL_CASELOAD_FLAG", 			new FieldMapper("allCaseloadFlag"))
			.put("SUB_ACCOUNT_TYPE", 			new FieldMapper("subAccountType"))
			.put("MODIFY_USER_ID", 				new FieldMapper("modifyUserId"))
			.put("TXN_TYPE", 					new FieldMapper("txnType"))
			.put("GROSS_NET_FLAG", 				new FieldMapper("grossNetFlag"))
			.put("EXPIRY_DATE", 				new FieldMapper("expiryDate"))
			.put("MANUAL_INVOICE_FLAG", 		new FieldMapper("manualInvoiceFlag"))
			.put("POSTING_STATUS_FLAG", 		new FieldMapper("postingStatusFlag"))
			.put("LIST_SEQ", 					new FieldMapper("listSeq"))
			.put("ACCOUNT_NAME", 				new FieldMapper("accountName"))
			.put("TXN_POSTING_TYPE", 			new FieldMapper("txnPostingType"))
			.put("DESCRIPTION", 				new FieldMapper("description"))
			.put("MODIFY_DATE", 				new FieldMapper("modifyDate"))
			.put("ACCOUNT_CODE", 				new FieldMapper("accountCode"))
			.put("CREDIT_OBLIGATION_TYPE", 		new FieldMapper("creditObligationType"))
			.put("TXN_USAGE", 					new FieldMapper("txnUsage"))
			.build();

	/**
	 * Creates new OtmfreezRepositoryImpl class Object
	 */
	public OtmfreezRepositoryImpl() {
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            FreezeDisbursements
	 *
	 * @return List<FreezeDisbursements>
	 *
	 * @
	 */
	public List<FreezeDisbursements> freDisExecuteQuery(final FreezeDisbursements objSearchDao) {
		final String sql = getQuery("OTMFREEZ_FREDIS_FIND_FREEZE_DISBURSEMENTS");
		final RowMapper<FreezeDisbursements> FreezeDisbursementsRowMapper = Row2BeanRowMapper.makeMapping(sql,
				FreezeDisbursements.class, mMapping);
		String preparedSql = null;
		final MapSqlParameterSource inParameterSource = new MapSqlParameterSource();
		final StringBuffer sqlQuery = new StringBuffer();
		sqlQuery.append(sql);
		if (objSearchDao.getCaseloadType() != null) {
			sqlQuery.append("CASELOAD_TYPE = :CASELOAD_TYPE" + " AND ");
			inParameterSource.addValue("CASELOAD_TYPE", objSearchDao.getCaseloadType());
		}
		if (objSearchDao.getAccountCode() != null) {
			sqlQuery.append("ACCOUNT_CODE = :ACCOUNT_CODE" + " AND ");
			inParameterSource.addValue("ACCOUNT_CODE", objSearchDao.getAccountCode());
		}
		if (objSearchDao.getTxnType() != null) {
			sqlQuery.append("TXN_TYPE = :TXN_TYPE" + " AND ");
			inParameterSource.addValue("TXN_TYPE", objSearchDao.getTxnType());
		}
		if (objSearchDao.getFreezeFlag() != null) {
			sqlQuery.append("FREEZE_FLAG = :FREEZE_FLAG" + " AND ");
			inParameterSource.addValue("FREEZE_FLAG", objSearchDao.getFreezeFlag());
		}
		preparedSql = sqlQuery.toString().trim();
		if (preparedSql.endsWith("WHERE")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 5);
		}
		if (preparedSql.endsWith("AND")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 3);
		}
		return namedParameterJdbcTemplate.query(preparedSql, inParameterSource, FreezeDisbursementsRowMapper);
	}

	/**
	 * This method is used to insert the records in the data base tables based
	 * on
	 *
	 * @param lstFreezeDisbursements
	 *            List<FreezeDisbursements>
	 *
	 * @return List<Integer>
	 *
	 * @
	 */
	public Integer freDisInsertFreezeDisbursements(final List<FreezeDisbursements> lstFreezeDisbursements) {
		final String sql = getQuery("OTMFREEZ_FREDIS_INSERT_FREEZE_DISBURSEMENTS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final FreezeDisbursements freezeDisbursements : lstFreezeDisbursements) {
			parameters.add(new BeanPropertySqlParameterSource(freezeDisbursements));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstFreezeDisbursements.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstFreezeDisbursements
	 *            List<FreezeDisbursements>
	 *
	 * @
	 */
	public Integer freDisUpdateFreezeDisbursements(final List<FreezeDisbursements> lstFreezeDisbursements) {
		final String sql = getQuery("OTMFREEZ_FREDIS_UPDATE_FREEZE_DISBURSEMENTS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final FreezeDisbursements freezeDisbursements : lstFreezeDisbursements) {
			parameters.add(new BeanPropertySqlParameterSource(freezeDisbursements));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstFreezeDisbursements.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to delete records from data base tables based on
	 *
	 * @param lstFreezeDisbursements
	 *            List<FreezeDisbursements>
	 *
	 * @
	 */
	public Integer freDisDeleteFreezeDisbursements(final List<FreezeDisbursements> lstFreezeDisbursements) {
		final String sql = getQuery("OTMFREEZ_FREDIS_DELETE_FREEZE_DISBURSEMENTS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final FreezeDisbursements freezeDisbursements : lstFreezeDisbursements) {
			parameters.add(new BeanPropertySqlParameterSource(freezeDisbursements));
		}
		try {
			String tableName = "FREEZE_DISBURSEMENTS";
			String whereClause = "ACCOUNT_CODE=:accountCode  and TXN_TYPE=:txnType";
			batchUpdatePreDeletedRows(tableName, whereClause , parameters);
		} catch (Exception e) {
			log.error("Exception occured in " + this.getClass().getName() + " in method freDisDeleteFreezeDisbursements", e);
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstFreezeDisbursements.size() == returnArray.length) {
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
	public List<TransactionTypes> cgfkFreDisTxnTypeRecordGroup(final String caseloadType) {
		final String sql = getQuery("OTMFREEZ_FIND_CGFKFREDISTXNTYPE");
		List<TransactionTypes> returnList=new ArrayList<TransactionTypes>();
		final RowMapper<TransactionTypes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, TransactionTypes.class,
				mMapping);

		try {
			returnList= namedParameterJdbcTemplate.query(sql, createParams("caseloadType",caseloadType), mRowMapper);
		} catch (Exception e) {
			log.error("cgfkFreDisTxnTypeRecordGroup",e);
		}
		return returnList;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<AccountCodes> cgfkFreDisAccountCodeRecordGroup(final String caseloadType) {
		final String sql = getQuery("OTMFREEZ_FIND_CGFKFREDISACCOUNTCODE");
		List<AccountCodes> returnList=new ArrayList<AccountCodes>();
		final RowMapper<AccountCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, AccountCodes.class, mMapping);

		try {
			returnList= namedParameterJdbcTemplate.query(sql, createParams("caseloadType",caseloadType), mRowMapper);
		} catch (Exception e) {
			log.error("cgfkFreDisAccountCodeRecordGroup",e);
		}
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfkchkFreDisFreDisTxn
	 *
	 * @param params
	 *
	 */
	public List<TransactionTypes> cgfkchkFreDisFreDisTxn(final TransactionTypes paramBean) {
		final String sql = getQuery("OTMFREEZ_CGFKCHK_FRE_DIS_FRE_DIS_TXN_T");
		final RowMapper<TransactionTypes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, TransactionTypes.class,
				transactionTypesMapping);
		final List<TransactionTypes> returnList =  namedParameterJdbcTemplate
				.query(sql, createParams(), columnRowMapper);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfkchkFreDisFreDisAct
	 *
	 * @param params
	 *
	 */
	public List<AccountCodes> cgfkchkFreDisFreDisAct(final AccountCodes paramBean) {
		final String sql = getQuery("OTMFREEZ_CGFKCHK_FRE_DIS_FRE_DIS_ACT_C");
		final RowMapper<AccountCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, AccountCodes.class,
				accountCodesMapping);
		final List<AccountCodes> returnList =  namedParameterJdbcTemplate.query(sql,
				createParams(), columnRowMapper);
		return returnList;
	}

	public List<FreezeDisbursements> getRegDescription(final Long accountCode, final String caseloadType) {
		final String sql = getQuery("OTMFREEZ_CGFKCHK_FRE_DIS_FRE_DIS_ACT_C");
		List<FreezeDisbursements> returnObj = null;
		final RowMapper<FreezeDisbursements> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				FreezeDisbursements.class, accountCodesMapping);
		try{
			returnObj = namedParameterJdbcTemplate.query(sql,createParams("ACCOUNTCODE", accountCode, "CASELOADTYPE", caseloadType), columnRowMapper);
		} catch (Exception e){
			log.error("getRegDescription",e);
		}
		return returnObj;
	}

	public String getTxnDescription(final String caseloadType, final String txnType) {
		final String sql = getQuery("OTMFREEZ_CGFKCHK_FRE_DIS_FRE_DIS_TXN_DESCRIPTION");
		String desc= null;
		try {
			desc =   namedParameterJdbcTemplate.queryForObject(sql,createParams("txnType", txnType, "caseloadType", caseloadType), String.class);
		}catch (Exception e) {
			log.error(this.getClass().getName()+"getTxnDescription",e);
		}
		return desc;
	}
	public String getTxnUsage(final String caseloadType ,final String txnType ) {
		final String sql = getQuery("OTMFREEZ_CGFKCHK_FRE_DIS_FRE_DIS_TXN_USAGE");
		String desc= null;
		try {
			desc =   namedParameterJdbcTemplate.queryForObject(sql,createParams("txnType", txnType, "caseloadType", caseloadType), String.class);
		}catch (Exception e) {
			log.error(this.getClass().getName()+"getTxnUsage",e);
		}
		return desc;
	}

	@Override
	public String getTxnUage(final String txnType,final String caseloadType) {
		final String sql = getQuery("OTMFREEZ_GET_TXN_USAGE");
		String txnUsage= null;
		try {
		txnUsage =   namedParameterJdbcTemplate.queryForObject(sql,createParams("txnType", txnType), String.class);
		}catch (Exception e) {
			log.error(this.getClass().getName()+"getTxnUage",e);
		}
		return txnUsage;
	}

	@Override
	public String getCode(final String txnType,final String caseloadType) {
		final String sql = getQuery("OTMFREEZ_GET_TXN_CODE");
		String code= null;
		try {
		code =   namedParameterJdbcTemplate.queryForObject(sql,createParams("description", txnType,"caseloadType",caseloadType), String.class);
		}catch (Exception e) {
			log.error(this.getClass().getName()+"getCode",e);
		}
		return code;
	}
}

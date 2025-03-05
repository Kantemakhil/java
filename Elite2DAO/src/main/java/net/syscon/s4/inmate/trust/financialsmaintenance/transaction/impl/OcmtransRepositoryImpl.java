package net.syscon.s4.inmate.trust.financialsmaintenance.transaction.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.DataAccessException;
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
import net.syscon.s4.im.beans.Corporates;
import net.syscon.s4.im.beans.TransactionPayees;
import net.syscon.s4.inmate.beans.CaseloadTransactionTypes;
import net.syscon.s4.inmate.beans.DeductionTypes;
import net.syscon.s4.inmate.trust.financialsmaintenance.transaction.OcmtransRepository;
import net.syscon.s4.inst.booking.beans.Persons;
import net.syscon.s4.inst.legalscreens.impl.OimstatuRepositoryImpl;

/**
 * Class OcmtransRepositoryImpl
 */
@Repository
public class OcmtransRepositoryImpl extends RepositoryBase implements OcmtransRepository {
	
	private static Logger logger = LogManager.getLogger(OcmtransRepositoryImpl.class);

	private final Map<String, FieldMapper> transactionTypesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CREDIT_OBLIGATION_TYPE", 					new FieldMapper("creditObligationType"))
			.put("CREATE_USER_ID", 							new FieldMapper("createUserId"))
			.put("ACTIVE_FLAG", 							new FieldMapper("activeFlag"))
			.put("MODIFY_USER_ID", 							new FieldMapper("modifyUserId"))
			.put("LIST_SEQ", 								new FieldMapper("listSeq"))
			.put("CASELOAD_TYPE", 							new FieldMapper("caseloadType"))
			.put("MODIFY_DATE", 							new FieldMapper("modifyDate"))
			.put("TXN_USAGE", 								new FieldMapper("txnUsage"))
			.put("ALL_CASELOAD_FLAG", 						new FieldMapper("allCaseloadFlag"))
			.put("MANUAL_INVOICE_FLAG", 					new FieldMapper("manualInvoiceFlag"))
			.put("GROSS_NET_FLAG", 							new FieldMapper("grossNetFlag"))
			.put("SEAL_FLAG", 								new FieldMapper("sealFlag"))
			.put("DAYS", 									new FieldMapper("days"))
			.put("CREATE_DATETIME",							new FieldMapper("createDatetime"))
			.put("TXN_TYPE", 								new FieldMapper("txnType"))
			.put("UPDATE_ALLOWED_FLAG", 					new FieldMapper("updateAllowedFlag"))
			.put("MODIFY_DATETIME", 						new FieldMapper("modifyDatetime"))
			.put("EXPIRY_DATE", 							new FieldMapper("expiryDate"))
			.put("DESCRIPTION", 							new FieldMapper("description"))
			.build();
	private final Map<String, FieldMapper> caseloadsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DESCRIPTION", 							new FieldMapper("description"))
			.put("CASELOAD_ID", 							new FieldMapper("caseloadId"))
			.build();
	private final Map<String, FieldMapper> personsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("PERSON_ID", 								new FieldMapper("personId"))
			.put("PAYEE_PERSON_ID", 						new FieldMapper("payeePersonId"))
			.put("MIDDLE_NAME", 							new FieldMapper("middleName"))
			.put("LAST_NAME", 								new FieldMapper("lastName"))
			.put("FIRST_NAME", 								new FieldMapper("firstName"))
			.build();
	private final Map<String, FieldMapper> referenceCodesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("LIST_SEQ", 								new FieldMapper("listSeq"))
			.put("CODE", 									new FieldMapper("code"))
			.put("DESCRIPTION", 							new FieldMapper("description"))
			.put("TXN_USAGE", 								new FieldMapper("txnUsage"))
			.build();
	private final Map<String, FieldMapper> caseloadTransactionTypesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("SEAL_FLAG", 								new FieldMapper("sealFlag"))
			.put("CREATE_DATETIME", 						new FieldMapper("createDatetime"))
			.put("MODIFY_USER_ID", 							new FieldMapper("modifyUserId"))
			.put("LIST_SEQ", 								new FieldMapper("listSeq"))
			.put("MODIFY_DATETIME", 						new FieldMapper("modifyDatetime"))
			.put("TXN_TYPE", 								new FieldMapper("txnType"))
			.build();
	private final Map<String, FieldMapper> deductionTypesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DEDUCTION_TYPE", 							new FieldMapper("deductionType"))
			.put("LIST_SEQ", 								new FieldMapper("listSeq"))
			.put("DEDUCTION_DESC", 							new FieldMapper("deductionDesc"))
			.put("CREDIT_OBLIGATION_TYPE", 					new FieldMapper("creditObligationType"))
			.build();
	private final Map<String, FieldMapper> transactionPayeesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("SEAL_FLAG", 								new FieldMapper("sealFlag"))
			.put("CREATE_DATETIME", 						new FieldMapper("createDatetime"))
			.put("TXN_TYPE", 								new FieldMapper("txnType"))
			.put("MODIFY_DATETIME", 						new FieldMapper("modifyDatetime"))
			.put("PAYEE_PERSON_ID", 						new FieldMapper("payeePersonId"))
			.put("PAYEE_CORPORATE_ID", 						new FieldMapper("payeeCorporateId"))
			.build();
	private final Map<String, FieldMapper> corporatesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CORPORATE_NAME", 							new FieldMapper("corporateName"))
			.put("CORPORATE_ID", 							new FieldMapper("corporateId"))
			.put("PAYEE_CORPORATE_ID", 						new FieldMapper("payeeCorporateId"))
			.build();

	/**
	 * Creates new OcmtransRepositoryImpl class Object
	 */
	
	public OcmtransRepositoryImpl() {
		// OcmtransRepositoryImpl
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            TransactionTypes
	 * @return List<TransactionTypes>
	 */
	public List<TransactionTypes> txnTypeExecuteQuery(final TransactionTypes objSearchDao) {
		final String sql = getQuery("OCMTRANS_TXNTYPE_FIND_TRANSACTION_TYPES");
		String preparedSql = null;
		final StringBuffer sqlQuery = new StringBuffer();
		String preSqlQuery = null;
		String user = objSearchDao.getCreateUserId();
		final MapSqlParameterSource params = new MapSqlParameterSource();
		sqlQuery.append(sql);
		if (objSearchDao != null) {
			sqlQuery.append(" where ");
			if (objSearchDao != null && objSearchDao.getTxnType() != null && !("").equals(objSearchDao.getTxnType())) {
				sqlQuery.append(" TXN_TYPE  = :txnType  " + " and");
				params.addValue("txnType", objSearchDao.getTxnType().trim());
			}
			if (objSearchDao != null && objSearchDao.getDescription() != null
					&& !("").equals(objSearchDao.getDescription())) {
				sqlQuery.append("  DESCRIPTION  = :description " + " and");
				params.addValue("description", objSearchDao.getDescription().trim());
			}
			if (objSearchDao != null && objSearchDao.getTxnUsage() != null
					&& !("").equals(objSearchDao.getTxnUsage())) {
				sqlQuery.append("  TXN_USAGE   = :txnUsage" + " and");
				params.addValue("txnUsage", objSearchDao.getTxnUsage().trim());
			}
			if (objSearchDao != null && objSearchDao.getCreditObligationType() != null
					&& !("").equals(objSearchDao.getCreditObligationType())) {
				sqlQuery.append(" CREDIT_OBLIGATION_TYPE  =:creditObligationType " + " and ");
				params.addValue("creditObligationType", objSearchDao.getCreditObligationType());
			}
			if (objSearchDao != null && objSearchDao.getDays() != null && !("").equals(objSearchDao.getDays())) {
				sqlQuery.append(" DAYS  = :days  " + " and");
				params.addValue("days", objSearchDao.getDays());
			}
			if (objSearchDao != null && objSearchDao.getListSeq() != null && !("").equals(objSearchDao.getListSeq())) {
				sqlQuery.append(" LIST_SEQ  =:listSeq " + " and ");
				params.addValue("listSeq", objSearchDao.getListSeq());
			}
			if (objSearchDao != null && objSearchDao.getExpiryDate() != null
					&& !("").equals(objSearchDao.getExpiryDate())) {
				sqlQuery.append("  trunc(EXPIRY_DATE)   = trunc(:expiryDate)" + " and");
				params.addValue("expiryDate", objSearchDao.getExpiryDate());
			}
			sqlQuery.append(
					" CASELOAD_TYPE  IN (SELECT CASELOAD_TYPE FROM CASELOADS WHERE CASELOAD_ID IN (SELECT WORKING_CASELOAD_ID "
					+ "FROM STAFF_MEMBERS WHERE USER_ID = :userId)) ");
			params.addValue("userId", objSearchDao.getCreateUserId());

		}
		preparedSql = sqlQuery.toString().trim();
		if (preparedSql.endsWith("where")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 5);
		}
		if (preparedSql.endsWith("and")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 3);
		}
		preSqlQuery = preparedSql.concat(" ORDER BY LIST_SEQ, TXN_TYPE, DESCRIPTION ");
		final RowMapper<TransactionTypes> TransactionTypesRowMapper = Row2BeanRowMapper.makeMapping(sql,
				TransactionTypes.class, transactionTypesMapping);
		List<TransactionTypes> returnList = new ArrayList<TransactionTypes>();
		returnList = namedParameterJdbcTemplate.query(preSqlQuery, params, TransactionTypesRowMapper);
		return returnList;
	}

	/**
	 * This method is used to insert the records in the data base tables based
	 * on
	 *
	 * @param lstTransactionTypes
	 *            List<TransactionTypes>
	 * @return Integer
	 */
	public Integer txnTypeInsertTransactionTypes(final List<TransactionTypes> lstTransactionTypes) {
		String sql = getQuery("OCMTRANS_TXNTYPE_INSERT_TRANSACTION_TYPES");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (TransactionTypes transactionTypes : lstTransactionTypes) {
			parameters.add(new BeanPropertySqlParameterSource(transactionTypes));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstTransactionTypes.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstTransactionTypes
	 *            List<TransactionTypes>
	 * @return Integer
	 */
	public Integer txnTypeUpdateTransactionTypes(final List<TransactionTypes> lstTransactionTypes) {
		String sql = getQuery("OCMTRANS_TXNTYPE_UPDATE_TRANSACTION_TYPES");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (TransactionTypes transactionTypes : lstTransactionTypes) {
			parameters.add(new BeanPropertySqlParameterSource(transactionTypes));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstTransactionTypes.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to delete records from data base tables based on
	 *
	 * @param lstTransactionTypes
	 *            List<TransactionTypes>
	 * @return Integer
	 */
	public Integer txnTypeDeleteTransactionTypes(final List<TransactionTypes> lstTransactionTypes) {
		String sql = getQuery("OCMTRANS_TXNTYPE_DELETE_TRANSACTION_TYPES");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (TransactionTypes transactionTypes : lstTransactionTypes) {
			parameters.add(new BeanPropertySqlParameterSource(transactionTypes));
		}
		try {
			String tableName = "TRANSACTION_TYPES";
			String whereClause = "TXN_TYPE  = :txnType";
			batchUpdatePreDeletedRows(tableName, whereClause , parameters);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method txnTypeDeleteTransactionTypes", e);
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstTransactionTypes.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            CaseloadTransactionTypes
	 *
	 * @return List<CaseloadTransactionTypes>
	 */
	public List<CaseloadTransactionTypes> csldTtExecuteQuery(final CaseloadTransactionTypes objSearchDao) {
		final String sql = getQuery("OCMTRANS_CSLDTT_FIND_CASELOAD_TRANSACTION_TYPES");
		final RowMapper<CaseloadTransactionTypes> CaseloadTransactionTypesRowMapper = Row2BeanRowMapper.makeMapping(sql,
				CaseloadTransactionTypes.class, caseloadTransactionTypesMapping);
		final ArrayList<CaseloadTransactionTypes> returnList = (ArrayList<CaseloadTransactionTypes>) namedParameterJdbcTemplate
				.query(sql, createParams(), CaseloadTransactionTypesRowMapper);
		return returnList;
	}

	/**
	 * This method is used to insert the records in the data base tables based
	 * on
	 *
	 * @param lstCaseloadTransactionTypes
	 *            List<CaseloadTransactionTypes>
	 *
	 * @return List<Integer>
	 */
	public Integer csldTtInsertCaseloadTransactionTypes(
			final List<CaseloadTransactionTypes> lstCaseloadTransactionTypes) {
		String sql = getQuery("OCMTRANS_CSLDTT_INSERT_CASELOAD_TRANSACTION_TYPES");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (CaseloadTransactionTypes caseloadTransactionTypes : lstCaseloadTransactionTypes) {
			parameters.add(new BeanPropertySqlParameterSource(caseloadTransactionTypes));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstCaseloadTransactionTypes.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to delete records from data base tables based on
	 *
	 * @param lstCaseloadTransactionTypes
	 *            List<CaseloadTransactionTypes>
	 * @return Integer
	 */
	public Integer csldTtDeleteCaseloadTransactionTypes(
			final List<CaseloadTransactionTypes> lstCaseloadTransactionTypes) {
		String sql = getQuery("OCMTRANS_CSLDTT_DELETE_CASELOAD_TRANSACTION_TYPES");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (CaseloadTransactionTypes caseloadTransactionTypes : lstCaseloadTransactionTypes) {
			parameters.add(new BeanPropertySqlParameterSource(caseloadTransactionTypes));
		}
		try {
			String tableName = "CASELOAD_TRANSACTION_TYPES";
			String whereClause = null;
			batchUpdatePreDeletedRows(tableName, whereClause , parameters);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method csldTtDeleteCaseloadTransactionTypes", e);
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstCaseloadTransactionTypes.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            TransactionPayees
	 * @return List<TransactionPayees>
	 */
	public List<TransactionPayees> txnPayeeExecuteQuery(final TransactionPayees objSearchDao) {
		final String sql = getQuery("OCMTRANS_TXNPAYEE_FIND_TRANSACTION_PAYEES");
		final RowMapper<TransactionPayees> TransactionPayeesRowMapper = Row2BeanRowMapper.makeMapping(sql,
				TransactionPayees.class, transactionPayeesMapping);
		List<TransactionPayees> returnList = new ArrayList<TransactionPayees>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams("TXNTYPE", objSearchDao.getTxnType()),
					TransactionPayeesRowMapper);
		} catch (Exception e) {
			return returnList;
		}
		return returnList;
	}

	/**
	 * This method is used to insert the records in the data base tables based
	 * on
	 *
	 * @param lstTransactionPayees
	 *            List<TransactionPayees>
	 * @return Integer
	 */
	public Integer txnPayeeInsertTransactionPayees(final List<TransactionPayees> lstTransactionPayees) {
		String sql = getQuery("OCMTRANS_TXNPAYEE_INSERT_TRANSACTION_PAYEES");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (TransactionPayees transactionPayees : lstTransactionPayees) {
			parameters.add(new BeanPropertySqlParameterSource(transactionPayees));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstTransactionPayees.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstTransactionPayees
	 *            List<TransactionPayees>
	 * @return Integer
	 */
	public Integer txnPayeeUpdateTransactionPayees(final List<TransactionPayees> lstTransactionPayees) {
		String sql = getQuery("OCMTRANS_TXNPAYEE_UPDATE_TRANSACTION_PAYEES");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (TransactionPayees transactionPayees : lstTransactionPayees) {
			parameters.add(new BeanPropertySqlParameterSource(transactionPayees));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstTransactionPayees.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to delete records from data base tables based on
	 *
	 * @param lstTransactionPayees
	 *            List<TransactionPayees>
	 * @return Integer
	 */
	public Integer txnPayeeDeleteTransactionPayees(final List<TransactionPayees> lstTransactionPayees) {
		String sql = getQuery("OCMTRANS_TXNPAYEE_DELETE_TRANSACTION_PAYEES");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (TransactionPayees transactionPayees : lstTransactionPayees) {
			parameters.add(new BeanPropertySqlParameterSource(transactionPayees));
		}
		try {
			String tableName = "TRANSACTION_PAYEES";
			String whereClause = "TXN_TYPE = :txnType AND PAYEE_SEQ  = :payeeSeq";
			batchUpdatePreDeletedRows(tableName, whereClause , parameters);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method txnPayeeDeleteTransactionPayees", e);
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstTransactionPayees.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * oidadmisOffEmPreInsertc
	 *
	 * @param params
	 *
	 */
	public Long payeeSeqInsert(final String txnType) {
		final String sql = getQuery("OCMTRANS_TXNPAYEE_FIND_TRANSACTION_PAYEES_SEQ");
		Long returnObj = 0L;
		final Long obj = namedParameterJdbcTemplate.queryForObject(sql, createParams("txnType", txnType), Long.class);
		if (returnObj != null) {
			returnObj = Long.valueOf(obj.toString());
		}
		return returnObj;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<DeductionTypes>
	 */
	public List<DeductionTypes> cgfkTxnTypeCreditObligatioRecordGroup() {
		final String sql = getQuery("OCMTRANS_FIND_CGFKTXNTYPECREDITOBLIGATIO");
		final RowMapper<DeductionTypes> deductionTypesRowMapper = Row2BeanRowMapper.makeMapping(sql,
				DeductionTypes.class, deductionTypesMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), deductionTypesRowMapper);
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<Persons>
	 */
	public List<Persons> cgfkTxnPayeePayeePersonIdRecordGroup() {
		final String sql = getQuery("OCMTRANS_FIND_CGFKTXNPAYEEPAYEEPERSONID");
		final RowMapper<Persons> personsRowMapper = Row2BeanRowMapper.makeMapping(sql, Persons.class, personsMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), personsRowMapper);
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<Corporates>
	 */
	public List<Corporates> cgfkTxnPayeePayeeCorporateRecordGroup() {
		final String sql = getQuery("OCMTRANS_FIND_CGFKTXNPAYEEPAYEECORPORATE");
		final RowMapper<Corporates> corporatesRowMapper = Row2BeanRowMapper.makeMapping(sql, Corporates.class,
				corporatesMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), corporatesRowMapper);
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<Caseloads>
	 */
	public List<Caseloads> cgfkCsldTtCaseloadIdRecordGroup() {
		final String sql = getQuery("OCMTRANS_FIND_CGFKCSLDTTCASELOADID");
		final RowMapper<Caseloads> caseloadsRowMapper = Row2BeanRowMapper.makeMapping(sql, Caseloads.class,
				caseloadsMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), caseloadsRowMapper);
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<ReferenceCodes>
	 */
	public List<ReferenceCodes> cgfkTxnTypeTxnUsageRecordGroup() {
		final String sql = getQuery("OCMTRANS_FIND_CGFKTXNTYPETXNUSAGE");
		final RowMapper<ReferenceCodes> referenceCodesRowMapper = Row2BeanRowMapper.makeMapping(sql,
				ReferenceCodes.class, referenceCodesMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), referenceCodesRowMapper);
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * @param paramBean
	 * @return TransactionPayees
	 */
	public TransactionPayees txnTypeOnCheckDeleteMaster(final TransactionPayees paramBean) {
		final String sql = getQuery("OCMTRANS_TXN_TYPE_ONCHECKDELETEMASTER");
		final RowMapper<TransactionPayees> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, TransactionPayees.class,
				transactionPayeesMapping);
		TransactionPayees returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * @param paramBean
	 * @return CaseloadTransactionTypes
	 */
	public CaseloadTransactionTypes txnTypeOnCheckDeleteMaster(final CaseloadTransactionTypes paramBean) {
		final String sql = getQuery("OCMTRANS_TXN_TYPE_ONCHECKDELETEMASTER");
		final RowMapper<CaseloadTransactionTypes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				CaseloadTransactionTypes.class, caseloadTransactionTypesMapping);
		CaseloadTransactionTypes returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(),
				columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * @param paramBean
	 * @return DeductionTypes
	 */
	public DeductionTypes cgfkchkTxnTypeTxnTypeDed(final DeductionTypes paramBean) {
		final String sql = getQuery("OCMTRANS_CGFKCHK_TXN_TYPE_TXN_TYPE_DED");
		final RowMapper<DeductionTypes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, DeductionTypes.class,
				deductionTypesMapping);
		DeductionTypes returnObj=new DeductionTypes();
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("CREDITOBLIGATIONTYPE", paramBean.getDeductionType()), columnRowMapper);
		} catch (Exception e) {
			logger.error("Exception : cgfkchkTxnTypeTxnTypeDed", e);
			returnObj=null;
		}
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * @param paramBean
	 * @return ReferenceCodes
	 */
	public ReferenceCodes cgfkchkTxnTypeTxnTypeRef(final ReferenceCodes paramBean) {
		final String sql = getQuery("OCMTRANS_CGFKCHK_TXN_TYPE_TXN_TYPE_REF");
		final RowMapper<ReferenceCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				referenceCodesMapping);
		ReferenceCodes returnObj=new ReferenceCodes();
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("TXNUSAGE", paramBean.getDescription()), columnRowMapper);
		} catch (Exception e) {
			logger.error("Exception : cgfkchkTxnTypeTxnTypeRef", e);
			returnObj=null;
		}
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * @param paramBean
	 * @return Caseloads
	 */
	public Caseloads cgfkchkCsldTtCsldTtCsld(final Caseloads paramBean) {
		final String sql = getQuery("OCMTRANS_CGFKCHK_CSLD_TT_CSLD_TT_CSLD_");
		final RowMapper<Caseloads> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, Caseloads.class,
				caseloadsMapping);
		Caseloads returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * @param paramBean
	 * @return Corporates
	 */
	public String cgfkchkTxnPayeeTxnPayee(final BigDecimal corporateId) {
		final String sql = getQuery("OCMTRANS_CGFKCHK_TXN_PAYEE_TXN_PAYEE_C");
		String returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams("PAYEECORPORATEID", corporateId),
				String.class);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * @param paramBean
	 * @return Persons
	 */
	public String cgfkchkTxnPayeeTxnPayee(final Long personId) {
		final String sql = getQuery("OCMTRANS_CGFKCHK_TXN_PAYEE_TXN_PAYEE_P_ONE");
		String returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams("PAYEEPERSONID", personId),
				String.class);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * @param paramBean
	 * @return TransactionOperations
	 */
	public Integer cgrichkTransactionOperations(final String txnType) {
		final String sql = getQuery("OCMTRANS_CGRICHK_TRANSACTION_OPERATIONS");
		Integer returnObj = 0;
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams("TXNTYPE", txnType), Integer.class);
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
	public Integer cgrichkOffenderPostDatedTxns(final String txnType) {
		final String sql = getQuery("OCMTRANS_CGRICHK_OFFENDER_POST_DATED_TXNS");
		Integer returnObj = 0;
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams("TXNTYPE", txnType), Integer.class);
		} catch (Exception e) {
			return returnObj;
		}
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * @param paramBean
	 * @return InterestTransactionTypes
	 */
	public Integer cgrichkInterestTransactionTypes(final String txnType) {
		final String sql = getQuery("OCMTRANS_CGRICHK_INTEREST_TRANSACTION_TYPES");
		Integer returnObj = 0;
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams("TXNTYPE", txnType), Integer.class);
		} catch (Exception e) {
			return returnObj;
		}
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * @param paramBean
	 * @return List<GlTransactions>
	 */
	public Integer cgrichkGlTransactions(final String txnType) {
		final String sql = getQuery("OCMTRANS_CGRICHK_GL_TRANSACTIONS");
		Integer returnObj = 0;
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams("TXNTYPE", txnType), Integer.class);
		} catch (Exception e) {
			return returnObj;
		}
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * @param paramBean
	 * @return List<OffenderInterests>
	 */
	public Integer cgrichkOffenderInterests(final String txnType) {
		final String sql = getQuery("OCMTRANS_CGRICHK_OFFENDER_INTERESTS");
		Integer returnObj = 0;
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams("TXNTYPE", txnType), Integer.class);
		} catch (Exception e) {
			return returnObj;
		}
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * @param paramBean
	 * @return DeductionLimits
	 */
	public Integer cgrichkDeductionLimits(final String txnType) {
		final String sql = getQuery("OCMTRANS_CGRICHK_DEDUCTION_LIMITS");
		Integer returnObj = 0;
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams("TXNTYPE", txnType), Integer.class);
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
	public String txnTypeValidation(final String txnType,String userName) {
		final String sql = getQuery("OCMTRANS_TXNTYPE_VALIDATION");
		String returnObj = "N";
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams("TXNTYPE", txnType,"USER_NAME",userName), String.class);
		} catch (Exception e) {
			return returnObj;
		}
		return returnObj;
	}
}

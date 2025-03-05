package net.syscon.s4.inmate.trust.trustaccounts.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.inmate.beans.GlTransactions;
import net.syscon.s4.inmate.trust.trustaccounts.OtugltrdRepository;

/**
 * Class OtugltrdRepositoryImpl
 */
@Repository
public class OtugltrdRepositoryImpl extends RepositoryBase implements OtugltrdRepository {

	private final Map<String, FieldMapper> glTransactionsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("PAYEE_CLEAR_FLAG", 		new FieldMapper("payeeClearFlag"))
			.put("TXN_REFERENCE_NUMBER", 	new FieldMapper("txnReferenceNumber"))
			.put("REVERSED_TXN_ID",			new FieldMapper("reversedTxnId"))
			.put("INFO_NUMBER", 			new FieldMapper("infoNumber"))
			.put("TXN_ENTRY_TIME", 			new FieldMapper("txnEntryTime"))
			.put("PAYEE_NAME_TEXT", 		new FieldMapper("payeeNameText"))
			.put("OFFENDER_ID", 			new FieldMapper("offenderId"))
			.put("GL_ENTRY_SEQ", 			new FieldMapper("glEntrySeq"))
			.put("SEAL_FLAG", 				new FieldMapper("sealFlag"))
			.put("CREATE_DATETIME", 		new FieldMapper("createDatetime"))
			.put("TXN_TYPE", 				new FieldMapper("txnType"))
			.put("MODIFY_DATETIME", 		new FieldMapper("modifyDatetime"))
			.put("ACCOUNT_PERIOD_ID", 		new FieldMapper("accountPeriodId"))
			.put("TXN_OBJECT_ID", 			new FieldMapper("txnObjectId"))
			.put("PAYEE_PERSON_ID", 		new FieldMapper("payeePersonId"))
			.put("REVERSED_TXN_ENTRY_SEQ", 	new FieldMapper("reversedTxnEntrySeq"))
			.put("TXN_ID", 					new FieldMapper("txnId"))
			.put("OFFENDER_BOOK_ID", 		new FieldMapper("offenderBookId"))
			.put("CREATE_USER_ID", 			new FieldMapper("createUserId"))
			.put("DEDUCTION_ID", 			new FieldMapper("deductionId"))
			.put("TXN_ENTRY_DATE", 			new FieldMapper("txnEntryDate"))
			.put("RECON_CLEAR_FLAG", 		new FieldMapper("reconClearFlag"))
			.put("REVERSED_GL_ENTRY_SEQ", 	new FieldMapper("reversedGlEntrySeq"))
			.put("BANK_STATEMENT_DATE", 	new FieldMapper("bankStatementDate"))
			.put("MODIFY_USER_ID", 			new FieldMapper("modifyUserId"))
			.put("TXN_ENTRY_AMOUNT", 		new FieldMapper("txnEntryAmount"))
			.put("LIST_SEQ", 				new FieldMapper("listSeq"))
			.put("TXN_LOC_ID", 				new FieldMapper("txnLocId"))
			.put("RECEIPT_NUMBER", 			new FieldMapper("receiptNumber"))
			.put("REVERSAL_REASON_CODE", 	new FieldMapper("reversalReasonCode"))
			.put("CASELOAD_ID", 			new FieldMapper("caseloadId"))
			.put("TXN_OBJECT_CODE", 		new FieldMapper("txnObjectCode"))
			.put("TXN_ENTRY_SEQ", 			new FieldMapper("txnEntrySeq"))
			.put("ACCOUNT_CODE", 			new FieldMapper("accountCode"))
			.put("PAYEE_CORPORATE_ID", 		new FieldMapper("payeeCorporateId"))
			.put("CREATE_DATE", 			new FieldMapper("createDate"))
			.put("TXN_REVERSED_FLAG", 		new FieldMapper("txnReversedFlag"))
			.put("TXN_POST_USAGE", 			new FieldMapper("txnPostUsage"))
			.put("TXN_ENTRY_DESC", 			new FieldMapper("txnEntryDesc"))
			.build();
	
	/**
	 * Creates new OtugltrdRepositoryImpl class Object
	 */
	public OtugltrdRepositoryImpl() {
		// OtugltrdRepositoryImpl
	}


	/**
	 * Fetch the records from database table
	 * Method glTxnExecuteQuery
	 * @param objSearchDao
	 *
	 * @return List<GlTransactions>
	 *
	 */
	public List<GlTransactions> glTxnExecuteQuery(final GlTransactions objSearchDao) {
		final String sql = getQuery("OTUGLTRD_GLTXN_FIND_GL_TRANSACTIONS");
		String preparedSql = null;
		final StringBuffer sqlQuery = new StringBuffer();
		final MapSqlParameterSource params = new MapSqlParameterSource();
		sqlQuery.append(sql);
		if (objSearchDao != null) {
			if (objSearchDao.getTxnId() != null) {
				sqlQuery.append(" WHERE ");
				sqlQuery.append("TXN_ID = :TXN_ID " + " AND ");
				params.addValue("TXN_ID", objSearchDao.getTxnId());
			}
			if (objSearchDao.getTxnEntrySeq() != null) {
				sqlQuery.append("(TXN_ENTRY_SEQ = :TXN_ENTRY_SEQ OR :TXN_ENTRY_SEQ = '0')" + " AND ");
				params.addValue("TXN_ENTRY_SEQ", objSearchDao.getTxnEntrySeq());
			}
			if(objSearchDao.getTxnType() != null) {
				sqlQuery.append("TXN_TYPE = :TXN_TYPE " + " AND ");
				params.addValue("TXN_TYPE", objSearchDao.getTxnType());
				
			}
			if(objSearchDao.getCaseloadId() != null) {
				sqlQuery.append("CASELOAD_ID = :CASELOAD_ID " + " AND ");
				params.addValue("CASELOAD_ID", objSearchDao.getCaseloadId());
				
			}
		}
		preparedSql = sqlQuery.toString().trim();
		if (preparedSql.endsWith("WHERE")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 5);
		}
		if (preparedSql.endsWith("AND")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 3);
		}
		preparedSql = preparedSql.concat(" ORDER BY TXN_POST_USAGE DESC");
		final RowMapper<GlTransactions> glTransRowMapper = Row2BeanRowMapper.makeMapping(sql,
				GlTransactions.class, glTransactionsMapping);
		final List<GlTransactions> returnList = namedParameterJdbcTemplate.query(preparedSql,
				params, glTransRowMapper);
		return returnList;
	}

	public Integer getCalcn(final GlTransactions objSearchDao) {
		final String sql = getQuery("OTUGLTRD_C_ALCN");
		return namedParameterJdbcTemplate.queryForObject(sql,
				createParams("TXNID", objSearchDao.getTxnId(), "TXNSEQ", objSearchDao.getTxnEntrySeq()), Integer.class);

	}
	public Integer getCgl(final GlTransactions objSearchDao) {
		final String sql = getQuery("OTUGLTRD_C_GL");
		return namedParameterJdbcTemplate.queryForObject(sql,
				createParams("TXNID", objSearchDao.getTxnId(), "TXNSEQ", objSearchDao.getTxnEntrySeq()), Integer.class);

	}
}

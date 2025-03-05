package net.syscon.s4.inmate.trust.statements.impl;

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
import net.syscon.s4.im.beans.OffenderTransactions;
import net.syscon.s4.im.beans.Offenders;
import net.syscon.s4.inmate.trust.statements.OturnumbRepository;

/**
 * Class OturnumbRepositoryImpl
 */
@Repository
public class OturnumbRepositoryImpl extends RepositoryBase implements OturnumbRepository {

	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OturnumbRepositoryImpl.class.getName());

	private final Map<String, FieldMapper> offenderTransactionMapping = new ImmutableMap.Builder<String, FieldMapper>()
	     .put("TXN_ID", 						        new FieldMapper("txnId"))
	     .put("TXN_ENTRY_SEQ", 					    	new FieldMapper("txnEntrySeq"))
	     .put("CASELOAD_ID", 						    new FieldMapper("caseloadId"))
	     .put("OFFENDER_BOOK_ID", 						new FieldMapper("offenderBookId"))
	     .put("TXN_POSTING_TYPE", 						new FieldMapper("txnPostingType"))
	     .put("TXN_ENTRY_DESC", 						new FieldMapper("txnEntryDesc"))
	     .put("TXN_ENTRY_AMOUNT", 						new FieldMapper("txnEntryAmount"))
	     .put("TXN_ENTRY_DATE", 						new FieldMapper("txnEntryDate"))
	     .put("SUB_ACCOUNT_TYPE", 						new FieldMapper("subAccountType"))
	     .put("TXN_REFERENCE_NUMBER", 					new FieldMapper("txnReferenceNumber"))
	     .put("MODIFY_DATE", 						    new FieldMapper("modifyDate"))
	     .put("RECEIPT_NUMBER", 						new FieldMapper("receiptNumber"))
	     .put("SLIP_PRINTED_FLAG", 						new FieldMapper("slipPrintedFlag"))
	     .put("TRANSFER_CASELOAD_ID", 					new FieldMapper("transferCaseloadId"))
	     .put("RECEIPT_PRINTED_FLAG", 					new FieldMapper("receiptPrintedFlag"))
	     .put("PRE_WITHHOLD_AMOUNT", 					new FieldMapper("preWithholdAmount"))
	     .put("DEDUCTION_FLAG", 						new FieldMapper("deductionFlag"))
	     .put("CLOSING_CHEQUE_NUMBER", 					new FieldMapper("closingChequeNumber"))
	     .put("REMITTER_NAME", 						    new FieldMapper("remitterNumber"))
	     .put("PAYEE_CODE", 						    new FieldMapper("payeeCode"))
	     .put("PAYEE_NAME_TEXT",                        new FieldMapper("payeeNameText"))
	     .put("PAYEE_CORPORATE_ID", 					new FieldMapper("payeeCorporateId"))
	     .put("PAYEE_PERSON_ID", 						new FieldMapper("payeePersonId"))
	     .put("ADJUST_TXN_ID", 					    	new FieldMapper("adjustTxnId"))
	     .put("ADJUST_TXN_ENTRY_ID", 					new FieldMapper("adjustTxnEntryId"))
	     .put("ADJUST_OFFENDER_ID", 					new FieldMapper("adjustOffenderId"))
	     .put("ADJUST_ACCOUNT_CODE", 					new FieldMapper("adjustAccountCode"))
	     .put("TXN_ADJUSTED_FLAG", 						new FieldMapper("txnAdjustedCode"))
	     .put("DEDUCTION_TYPE", 					    new FieldMapper("deductionType"))
	     .put("INFO_NUMBER", 						    new FieldMapper("infoNumber"))
	     .put("HOLD_CLEAR_FLAG", 						new FieldMapper("holdClearFlag"))
	     .put("HOLD_UNTIL_DATE", 						new FieldMapper("holdUntilDate"))
	     .put("HOLD_NUMBER", 						    new FieldMapper("holdNumber"))
	     .put("GROSS_AMOUNT", 						    new FieldMapper("grossAmount"))
	     .put("GROSS_NET_FLAG", 						new FieldMapper("grossNetFlag"))
	     .put("REMITTER_ID", 						    new FieldMapper("remitterId"))
	     .put("APPLY_SPENDING_LIMIT_AMOUNT", 			new FieldMapper("applySpendingLimitAmount"))
	     .put("RECEIPT_PENDING_PRINT_FLAG", 			new FieldMapper("receiptPendingPrintFlag"))
	     .put("SEAL_FLAG", 						        new FieldMapper("sealFlag"))
	     .put("ORG_TXN_TYPE", 					    	new FieldMapper("orgTxnType"))
	     .put("CREATE_DATETIME", 				  		new FieldMapper("createDateTime"))
	     .put("CREATE_USER_ID", 					    new FieldMapper("createUserId"))
	     .put("MODIFY_DATETIME", 						new FieldMapper("modifyDateTime"))	
	     .put("MODIFY_USER_ID", 						new FieldMapper("modifyUserId"))
	     .build();
   
   private final Map<String, FieldMapper> offenderMapping = new ImmutableMap.Builder<String, FieldMapper>()
		   .put("LAST_NAME", 						    new FieldMapper("lastName"))
		   .put("OFFENDER_ID_DISPLAY", 					new FieldMapper("offenderIdDisplay"))
		   .build();
   
	/**
	 * Creates new OturnumbRepositoryImpl class Object
	 */
	public OturnumbRepositoryImpl() {
		// OturnumbRepositoryImpl
	}

	/**
	 * 
	 * fetch the records from database table
	 *
	 * @param objSearchDao
	 *            OffenderTransactions
	 *
	 * @return List<OffenderTransactions>
	 *
	 */

	public List<OffenderTransactions> offTxnExecuteQuery(OffenderTransactions objSearchDao) {
		final String sql = getQuery("OTURNUMB_OFFTXN_FIND_OFFENDER_TRANSACTIONS");
		final RowMapper<OffenderTransactions> OffenderTransactionsRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderTransactions.class, offenderTransactionMapping);
		List<OffenderTransactions> returnList = new ArrayList<OffenderTransactions>();
		try {
			StringBuffer paramSql = new StringBuffer(sql);
			MapSqlParameterSource param = new MapSqlParameterSource();
			paramSql.append(" offender_transactions.caseload_id = :caseloadId ");
			param.addValue("caseloadId", objSearchDao.getCaseloadId());
			paramSql.append(
					"    AND offender_transactions.receipt_number >= :caseloadId || LTRIM(TO_CHAR(:receiptNumber::int, '00000')) ");
			if (objSearchDao.getReceiptNumber() == null || objSearchDao.getReceiptNumber().isEmpty()) {
				param.addValue("receiptNumber", "00000");
			} else {
				param.addValue("receiptNumber", objSearchDao.getReceiptNumber());
			}
			paramSql.append("and offender_transactions.receipt_number <= :caseloadId || '99999' and exists ( select 1 from offender_bookings ob left outer join living_units lu on ob.living_unit_id = lu.living_unit_id where ob.offender_book_id = offender_transactions.offender_book_id and (lu.active_flag = 'Y' or lu.active_flag is null)");
			if (objSearchDao.getAgyLocId() != null && !objSearchDao.getAgyLocId().isEmpty()) {
				paramSql.append(" AND ob.agy_loc_id = :agyLocId   ");
				param.addValue("agyLocId", objSearchDao.getAgyLocId());
			}
			paramSql.append(" )  ");
			if (objSearchDao.getCreateUserId() != null && !objSearchDao.getCreateUserId().isEmpty()) {
				paramSql.append(" AND EXISTS  ");
				paramSql.append(" (SELECT 1  ");
				paramSql.append(" FROM gl_transactions gt  ");
				paramSql.append(" WHERE gt.txn_id = offender_transactions.txn_id  ");
				paramSql.append(" AND gt.txn_entry_seq = offender_transactions.txn_entry_seq  ");
				paramSql.append(" AND gt.create_user_id = :createUserId ) ");
				param.addValue("createUserId", objSearchDao.getCreateUserId());
			}
			if (objSearchDao.getTxnUsage() != null && !objSearchDao.getTxnUsage().isEmpty()) {
				paramSql.append(" AND EXISTS  ");
				paramSql.append(" (SELECT 1  ");
				paramSql.append(" FROM transaction_types tt  ");
				paramSql.append(" WHERE tt.txn_type = offender_transactions.txn_type  ");
				paramSql.append(" AND tt.txn_usage = :txnUsage ");
				paramSql.append(" )  ");
				param.addValue("txnUsage", objSearchDao.getTxnUsage());
			}
			paramSql.append(" ORDER BY RECEIPT_NUMBER ");
			final String pareparedSql = paramSql.toString();
			returnList = namedParameterJdbcTemplate.query(pareparedSql, param, OffenderTransactionsRowMapper);
		} catch (Exception e) {
			logger.error("offTxnExecuteQuery", e);
		}
		return returnList;
	}

	@Override
	public Offenders getOffenderDetails(Long offenderBookId) {
		final String sql = getQuery("OTURNUMB_GET_OFFENDER_DETAILS");
		final RowMapper<Offenders> OffenderRowMapper = Row2BeanRowMapper.makeMapping(sql, Offenders.class,
				offenderMapping);
		Offenders returnList = new Offenders();
		returnList = namedParameterJdbcTemplate.queryForObject(sql, createParams("offenderBookId", offenderBookId),
				OffenderRowMapper);
		return returnList;
	}

	/**
	 * This method is used to insert the records in the data base tables based
	 * on
	 *
	 * @param lstOffenderTransactions
	 *            List<OffenderTransactions>
	 *
	 * @return List<Integer>
	 *
	 */
	public Integer offTxnInsertOffenderTransactions(final List<OffenderTransactions> lstOffenderTransactions) {
		String sql = getQuery("OTURNUMB_OFFTXN_INSERT_OFFENDER_TRANSACTIONS");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (OffenderTransactions offenderTransactions : lstOffenderTransactions) {
			parameters.add(new BeanPropertySqlParameterSource(offenderTransactions));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstOffenderTransactions.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstOffenderTransactions
	 *            List<OffenderTransactions>
	 *
	 */
	public Integer offTxnUpdateOffenderTransactions(final List<OffenderTransactions> lstOffenderTransactions) {
		String sql = getQuery("OTURNUMB_OFFTXN_UPDATE_OFFENDER_TRANSACTIONS");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (OffenderTransactions offenderTransactions : lstOffenderTransactions) {
			parameters.add(new BeanPropertySqlParameterSource(offenderTransactions));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstOffenderTransactions.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}
}

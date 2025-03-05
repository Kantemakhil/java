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
import net.syscon.s4.im.beans.OffenderTransactions;
import net.syscon.s4.inmate.beans.OffenderDeductions;
import net.syscon.s4.inmate.trust.trustaccounts.OtupayinRepository;

/**
 * Class OtupayinRepositoryImpl
 * 
 */
@Repository
public class OtupayinRepositoryImpl extends RepositoryBase implements OtupayinRepository {

	/**
	 * Creates new OtupayinRepositoryImpl class Object
	 */
	public OtupayinRepositoryImpl() {
		// OtupayinRepositoryImpl
	}

	private final Map<String, FieldMapper> offDeducMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("ADJUSTMENT_USER_ID",    		new FieldMapper("adjustmentUserId"))
			.put("GROUP_ID", 					new FieldMapper("groupId"))
			.put("PROCESS_PRIORITY_NUMBER",     new FieldMapper("processPriorityNumber"))
			.put("PAY_DEDUCTION_FLAG", 			new FieldMapper("payDeductionFlag"))
			.put("DEDUCTION_STATUS",		 	new FieldMapper("deductionStatus"))
			.put("COLLECT_SENT_DATE", 			new FieldMapper("collectSentDate"))
			.put("MAX_RECURSIVE_AMOUNT", 		new FieldMapper("maxRecursiveAmount"))
			.put("JS_STATUS", 					new FieldMapper("jsStatus"))
			.put("OFFENDER_ID", 				new FieldMapper("offenderId"))
			.put("FIFO_FLAG", 					new FieldMapper("fifoFlag"))
			.put("ADJUSTMENT_AMOUNT", 			new FieldMapper("adjustmentAmount"))
			.put("DEDUCTION_PRIORITY", 			new FieldMapper("deductionPriority"))
			.put("SEAL_FLAG", 					new FieldMapper("sealFlag"))
			.put("CREATE_DATETIME", 			new FieldMapper("createDatetime"))
			.put("EFFECTIVE_DATE", 				new FieldMapper("effectiveDate"))
			.put("DEDUCTION_AMOUNT", 			new FieldMapper("deductionAmount"))
			.put("MODIFY_DATETIME", 			new FieldMapper("modifyDatetime"))
			.put("PAYEE_PERSON_ID", 			new FieldMapper("payeePersonId"))
			.put("ADJUSTMENT_TEXT", 			new FieldMapper("adjustmentText"))
			.put("COLLECT_AGENCY_FLAG", 		new FieldMapper("collectAgencyFlag"))
			.put("COMMENT_TEXT", 				new FieldMapper("commentText"))
			.put("ADJUSTMENT_REASON_CODE", 		new FieldMapper("adjustmentReasonCode"))
			.put("CREATE_USER_ID", 				new FieldMapper("createUserId"))
			.put("OFFENDER_DEDUCTION_ID", 		new FieldMapper("offenderDeductionId"))
			.put("OFFENDER_PAYMENT_PROFILE_ID", new FieldMapper("offenderPaymentProfileId"))
			.put("MAX_TOTAL_AMOUNT", 			new FieldMapper("maxTotalAmount"))
			.put("MODIFY_USER_ID", 				new FieldMapper("modifyUserId"))
			.put("INFORMATION_NUMBER", 			new FieldMapper("informationNumber"))
			.put("ADJUSTMENT_TXN_ID", 			new FieldMapper("adjustmentTxnId"))
			.put("CASE_ID", 					new FieldMapper("caseId"))
			.put("COLLECT_AGENCY_AMOUNT", 		new FieldMapper("collectAgencyAmount"))
			.put("CREDIT_LIMIT", 				new FieldMapper("creditLimit"))
			.put("DEDUCTION_PERCENTAGE", 		new FieldMapper("deductionPercentage"))
			.put("CASELOAD_ID", 				new FieldMapper("caseloadId"))
			.put("MAX_MONTHLY_AMOUNT", 			new FieldMapper("maxMonthlyAmount"))
			.put("MODIFY_DATE", 				new FieldMapper("modifyDate"))
			.put("DEDUCTION_TYPE", 				new FieldMapper("deductionType"))
			.put("PARENT_DEDUCTION_ID", 		new FieldMapper("parentDeductionId"))
			.put("PAYEE_CORPORATE_ID", 			new FieldMapper("payeeCorporateId"))
			.build();
	
	private final Map<String, FieldMapper> offTransMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("TXN_REFERENCE_NUMBER", 		new FieldMapper("txnReferenceNumber"))
			.put("INFO_NUMBER", 				new FieldMapper("infoNumber"))
			.put("APPLY_SPENDING_LIMIT_AMOUNT", new FieldMapper("applySpendingLimitAmount"))
			.put("TXN_ADJUSTED_FLAG", 			new FieldMapper("txnAdjustedFlag"))
			.put("ADJUST_TXN_ENTRY_ID", 		new FieldMapper("adjustTxnEntryId"))
			.put("PAYEE_NAME_TEXT", 			new FieldMapper("payeeNameText"))
			.put("RECEIPT_PRINTED_FLAG", 		new FieldMapper("receiptPrintedFlag"))
			.put("OFFENDER_ID", 				new FieldMapper("offenderId"))
			.put("HOLD_CLEAR_FLAG", 			new FieldMapper("holdClearFlag"))
			.put("GROSS_NET_FLAG", 				new FieldMapper("grossNetFlag"))
			.put("SEAL_FLAG", 					new FieldMapper("sealFlag"))
			.put("CREATE_DATETIME", 			new FieldMapper("createDatetime"))
			.put("HOLD_NUMBER", 				new FieldMapper("holdNumber"))
			.put("TXN_TYPE", 					new FieldMapper("txnType"))
			.put("GROSS_AMOUNT", 				new FieldMapper("grossAmount"))
			.put("RECEIPT_PENDING_PRINT_FLAG", 	new FieldMapper("receiptPendingPrintFlag"))
			.put("MODIFY_DATETIME", 			new FieldMapper("modifyDatetime"))
			.put("PAYEE_PERSON_ID", 			new FieldMapper("payeePersonId"))
			.put("ORG_TXN_TYPE", 				new FieldMapper("orgTxnType"))
			.put("TRANSFER_CASELOAD_ID", 		new FieldMapper("transferCaseloadId"))
			.put("TXN_ID", 						new FieldMapper("txnId"))
			.put("OFFENDER_BOOK_ID", 			new FieldMapper("offenderBookId"))
			.put("REMITTER_ID", 				new FieldMapper("remitterId"))
			.put("CREATE_USER_ID", 				new FieldMapper("createUserId"))
			.put("TXN_ENTRY_DATE", 				new FieldMapper("txnEntryDate"))
			.put("PAYEE_CODE", 					new FieldMapper("payeeCode"))
			.put("MODIFY_USER_ID", 				new FieldMapper("modifyUserId"))
			.put("TXN_ENTRY_AMOUNT", 			new FieldMapper("txnEntryAmount"))
			.put("ADJUST_OFFENDER_ID", 			new FieldMapper("adjustOffenderId"))
			.put("RECEIPT_NUMBER", 				new FieldMapper("receiptNumber"))
			.put("ADJUST_TXN_ID", 				new FieldMapper("adjustTxnId"))
			.put("CLOSING_CHEQUE_NUMBER", 		new FieldMapper("closingChequeNumber"))
			.put("HOLD_UNTIL_DATE", 			new FieldMapper("holdUntilDate"))
			.put("SLIP_PRINTED_FLAG", 			new FieldMapper("slipPrintedFlag"))
			.put("ADJUST_ACCOUNT_CODE", 		new FieldMapper("adjustAccountCode"))
			.put("CASELOAD_ID", 				new FieldMapper("caseloadId"))
			.put("MODIFY_DATE", 				new FieldMapper("modifyDate"))
			.put("DEDUCTION_FLAG", 				new FieldMapper("deductionFlag"))
			.put("TXN_ENTRY_SEQ", 				new FieldMapper("txnEntrySeq"))
			.put("DEDUCTION_TYPE", 				new FieldMapper("deductionType"))
			.put("REMITTER_NAME", 				new FieldMapper("remitterName"))
			.put("PAYEE_CORPORATE_ID", 			new FieldMapper("payeeCorporateId"))
			.put("SUB_ACCOUNT_TYPE", 			new FieldMapper("subAccountType"))
			.put("TXN_ENTRY_DESC", 				new FieldMapper("txnEntryDesc"))
			.put("PRE_WITHHOLD_AMOUNT", 		new FieldMapper("preWithholdAmount"))
			.put("TXN_POSTING_TYPE", 			new FieldMapper("txnPostingType"))
			.build();

	/**
	 * Fetch the records from database table
	 * Method offDedExecuteQuery
	 * @param objSearchDao
	 * 
	 * @return OffenderDeductions
	 *
	 * 
	 */
	public OffenderDeductions offDedExecuteQuery(final OffenderDeductions objSearchDao) {
		final String sql = getQuery("OTUPAYIN_OFFDED_FIND_OFFENDER_DEDUCTIONS");
		final RowMapper<OffenderDeductions> offDeducRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderDeductions.class, offDeducMapping);
		return namedParameterJdbcTemplate
				.queryForObject(sql, createParams("OFFENDERDEDUCTIONID",objSearchDao.getOffenderDeductionId()), offDeducRowMapper);
	}


	/**
	 * Fetch the records from database table
	 * Method offTxnExecuteQuery
	 * @param objSearchDao
	 *
	 * @return List<OffenderTransactions>
	 *
	 * 
	 */
	public List<OffenderTransactions> offTxnExecuteQuery(final OffenderDeductions objSearchDao) {
		final String sql = getQuery("OTUPAYIN_OFFTXN_FIND_OFFENDER_TRANSACTIONS");
		String preparedSql = null;
		final StringBuffer sqlQuery = new StringBuffer();
		final MapSqlParameterSource params = new MapSqlParameterSource();
		sqlQuery.append(sql);
		if (objSearchDao != null) {
			sqlQuery.append(" where ");
			if (objSearchDao.getOffenderId() != null) {
				sqlQuery.append("OFFENDER_ID = :OFFENDER_ID" + " and ");
				params.addValue("OFFENDER_ID", objSearchDao.getOffenderId());
			}
			if (objSearchDao.getDeductionType() != null) {
				sqlQuery.append("DEDUCTION_TYPE = :DEDUCTION_TYPE" + " and ");
				params.addValue("DEDUCTION_TYPE", objSearchDao.getDeductionType());
			}
			if (objSearchDao.getOffenderDeductionId() != null) {
				sqlQuery.append(
						" EXISTS (SELECT 1 FROM GL_TRANSACTIONS GL WHERE GL.DEDUCTION_ID = :OFFENDERDEDUCTIONID AND GL.TXN_ID = OFFENDER_TRANSACTIONS.TXN_ID AND GL.TXN_ENTRY_SEQ = OFFENDER_TRANSACTIONS.TXN_ENTRY_SEQ  )"
								+ " and ");
				params.addValue("OFFENDERDEDUCTIONID", objSearchDao.getOffenderDeductionId());
			}
		}
		preparedSql = sqlQuery.toString().trim();
		if (preparedSql.endsWith("where")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 5);
		}
		if (preparedSql.endsWith("and")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 3);
		}
		preparedSql = preparedSql.concat(" ORDER BY TXN_ENTRY_DATE DESC ,TXN_ID DESC");
		ArrayList<OffenderTransactions> returnList = new ArrayList<OffenderTransactions>();
		final RowMapper<OffenderTransactions> offTransRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderTransactions.class, offTransMapping);
		returnList = (ArrayList<OffenderTransactions>) namedParameterJdbcTemplate
				.query(preparedSql, params, offTransRowMapper);
		return returnList;
	}

	/**
	 * The method is used to get the Description of DEDUCTION_TYPE
	 * 
	 * offDedPostQuery
	 *
	 * @param paramBean
	 * Return String
	 *
	 */
	public String offDedPostQuery(final OffenderDeductions paramBean) {
		final String sql = getQuery("OTUPAYIN_OFF_DED_POSTQUERY");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("DEDUCTION_TYPE",paramBean.getDeductionType()),
				String.class);
	}

}

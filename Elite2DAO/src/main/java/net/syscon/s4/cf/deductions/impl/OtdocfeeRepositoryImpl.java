package net.syscon.s4.cf.deductions.impl;

import java.math.BigDecimal;
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
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.cf.deductions.OtdocfeeRepository;
import net.syscon.s4.cf.deductions.maintenance.beans.OffenderTierTxnFeeAmounts;
import net.syscon.s4.cf.deductions.maintenance.beans.OffenderTxnFeeDetails;
import net.syscon.s4.cf.deductions.maintenance.beans.TieredTransactionFeeAmounts;
import net.syscon.s4.cf.deductions.maintenance.beans.TransactionFeeDetails;
import net.syscon.s4.cf.offendertransactions.beans.OffenderPaymentPlan;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.OffenderTransactions;
import net.syscon.s4.im.beans.SysDual;
import net.syscon.s4.inmate.beans.CaseloadDeductionProfiles;
import net.syscon.s4.inmate.beans.DeductionTypes;
import net.syscon.s4.inmate.beans.OffenderBeneficiaries;
import net.syscon.s4.inmate.beans.OffenderDeductions;

/**
 * Class OtdocfeeRepositoryImpl
 * 
 */
@Repository
public class OtdocfeeRepositoryImpl extends RepositoryBase implements OtdocfeeRepository {

	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OtdocfeeRepositoryImpl.class);

	private final Map<String, FieldMapper> offenderPaymentPlanMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("'1'", new FieldMapper("  '1' ")).build();
	private final Map<String, FieldMapper> referenceCodesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CODE", new FieldMapper("code")).put("DESCRIPTION", new FieldMapper("description"))
			.put("DSP_DESCRIPTION", new FieldMapper("dspDescription")).put("MODE", new FieldMapper("mode"))
			.put("DEDUCTION_STATUS", new FieldMapper("deductionStatus")).build();
	private final Map<String, FieldMapper> deductionTypesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DEDUCTION_TYPE", new FieldMapper("deductionType"))
			.put("DEDUCTION_DESC", new FieldMapper("deductionDesc"))
			.put("CASELOAD_TYPE", new FieldMapper("caseloadType")).put("MODE", new FieldMapper("mode"))
			.put("CASELOAD_ID", new FieldMapper("caseloadId"))
			.put("DEDUCTION_CATEGORY", new FieldMapper("deductionCategory"))
			.put("RECEIPT_DEDUCTION_TYPE", new FieldMapper("receiptDeductionType")).build();
	private final Map<String, FieldMapper> mMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DEDUCTION_TYPE", new FieldMapper("deductionType")).put("CODE", new FieldMapper("code"))
			.put("DESCRIPTION", new FieldMapper("description")).put("DEDUCTION_DESC", new FieldMapper("deductionDesc"))
			.build();
	private final Map<String, FieldMapper> offenderTransactionsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("'1'", new FieldMapper("  '1' ")).build();
	private final Map<String, FieldMapper> offenderDeductionsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("ADJUSTMENT_USER_ID", new FieldMapper("adjustmentUserId")).put("GROUP_ID", new FieldMapper("groupId"))
			.put("PROCESS_PRIORITY_NUMBER", new FieldMapper("processPriorityNumber"))
			.put("PAY_DEDUCTION_FLAG", new FieldMapper("payDeductionFlag"))
			.put("DEDUCTION_STATUS", new FieldMapper("deductionStatus"))
			.put("COLLECT_SENT_DATE", new FieldMapper("collectSentDate"))
			.put("MAX_RECURSIVE_AMOUNT", new FieldMapper("maxRecursiveAmount"))
			.put("JS_STATUS", new FieldMapper("jsStatus")).put("OFFENDER_ID", new FieldMapper("offenderId"))
			.put("FIFO_FLAG", new FieldMapper("fifoFlag")).put("ADJUSTMENT_AMOUNT", new FieldMapper("adjustmentAmount"))
			.put("DEDUCTION_PRIORITY", new FieldMapper("deductionPriority"))
			.put("SEAL_FLAG", new FieldMapper("sealFlag")).put("CREATE_DATETIME", new FieldMapper("createDateTime"))
			.put("EFFECTIVE_DATE", new FieldMapper("effectiveDate"))
			.put("DEDUCTION_AMOUNT", new FieldMapper("deductionAmount"))
			.put("MODIFY_DATETIME", new FieldMapper("modifyDateTime"))
			.put("PAYEE_PERSON_ID", new FieldMapper("payeePersonId"))
			.put("ADJUSTMENT_TEXT", new FieldMapper("adjustmentText"))
			.put("COLLECT_AGENCY_FLAG", new FieldMapper("collectAgencyFlag"))
			.put("COMMENT_TEXT", new FieldMapper("commentText"))
			.put("ADJUSTMENT_REASON_CODE", new FieldMapper("adjustmentReasonCode"))
			.put("CREATE_USER_ID", new FieldMapper("createUserId"))
			.put("OFFENDER_DEDUCTION_ID", new FieldMapper("offenderDeductionId"))
			.put("OFFENDER_PAYMENT_PROFILE_ID", new FieldMapper("offenderPaymentProfileId"))
			.put("MAX_TOTAL_AMOUNT", new FieldMapper("maxTotalAmount"))
			.put("MODIFY_USER_ID", new FieldMapper("modifyUserId"))
			.put("INFORMATION_NUMBER", new FieldMapper("informationNumber"))
			.put("ADJUSTMENT_TXN_ID", new FieldMapper("adjustmentTxnId")).put("CASE_ID", new FieldMapper("caseId"))
			.put("COLLECT_AGENCY_AMOUNT", new FieldMapper("collectAgencyAmount"))
			.put("CREDIT_LIMIT", new FieldMapper("creditLimit"))
			.put("DEDUCTION_PERCENTAGE", new FieldMapper("deductionPercentage"))
			.put("CASELOAD_ID", new FieldMapper("caseloadId"))
			.put("MAX_MONTHLY_AMOUNT", new FieldMapper("maxMonthlyAmount"))
			.put("MODIFY_DATE", new FieldMapper("modifyDate")).put("DEDUCTION_TYPE", new FieldMapper("deductionType"))
			.put("PARENT_DEDUCTION_ID", new FieldMapper("parentDeductionId"))
			.put("PAYEE_CORPORATE_ID", new FieldMapper("payeeCorporateId")).build();
	private final Map<String, FieldMapper> offenderTxnFeeDetailsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("OFFENDER_DEDUCTION_ID", new FieldMapper("offenderDeductionId")).build();
	private final Map<String, FieldMapper> offenderTierTxnFeeAmountsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("OFFENDER_DEDUCTION_ID", new FieldMapper("offenderDeductionId")).put("ROWID", new FieldMapper("rowid"))
			.build();
	private final Map<String, FieldMapper> systemProfilesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("OLD_TABLE_NAME", new FieldMapper("oldTableName")).build();
	private final Map<String, FieldMapper> sysDualMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("", new FieldMapper("")).build();
	
	
		/**
		 * Creates new OtdocfeeRepositoryImpl class Object
		 */
	public OtdocfeeRepositoryImpl() {
//		OtdocfeeRepositoryImpl
	}


	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            OffenderDeductions
	 *
	 * @return List<OffenderDeductions>
	 *
	 * 
	 */
	public List<OffenderDeductions> offDedExecuteQuery(final OffenderDeductions objSearchDao) {
		final String sql = getQuery("OTDOCFEE_OFFDED_FIND_OFFENDER_DEDUCTIONS");
		final RowMapper<OffenderDeductions> offenderDeductionsRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderDeductions.class, offenderDeductionsMapping);
		return namedParameterJdbcTemplate.query(sql,
				createParams("CASELOAD_ID", objSearchDao.getCaseloadId(), "CASELOAD_CODE",
						objSearchDao.getCaseloadType(), "OFFENDER_ID", objSearchDao.getOffenderId()),
				offenderDeductionsRowMapper);
	}

	/**
	 * This method is used to insert the records in the data base tables based
	 * on
	 *
	 * @param lstOffenderDeductions
	 *            List<OffenderDeductions>
	 *
	 * @return List<Integer>
	 *
	 * 
	 */
	public Integer offDedInsertOffenderDeductions(final List<OffenderDeductions> lstOffenderDeductions) {
		final String sql = getQuery("OTDOCFEE_OFFDED_INSERT_OFFENDER_DEDUCTIONS");
		int[] returnArray;
		final List<SqlParameterSource> parameters = new ArrayList<>();
		Long seqId;
		for (final OffenderDeductions offenderDeduction : lstOffenderDeductions) {
			parameters.add(new BeanPropertySqlParameterSource(offenderDeduction));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
			if (lstOffenderDeductions.size() == returnArray.length) {
				return 1;
			}
		} catch (Exception e) {
			if (e.getMessage().contains("offender_deductions_u1")) {
				return 4;
			}
		}
		return 0;
	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstOffenderDeductions
	 *            List<OffenderDeductions>
	 *
	 * 
	 */
	public Integer offDedUpdateOffenderDeductions(final List<OffenderDeductions> lstOffenderDeductions) {
		final String sql = getQuery("OTDOCFEE_OFFDED_UPDATE_OFFENDER_DEDUCTIONS");
		int[] returnArray = null;
		final List<SqlParameterSource> parameters = new ArrayList<>();
		for (final OffenderDeductions offenderDeductions : lstOffenderDeductions) {
			parameters.add(new BeanPropertySqlParameterSource(offenderDeductions));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error(this.getClass().getName()+"Error in method offDedUpdateOffenderDeductions :", e);
		}
		if (lstOffenderDeductions.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            OffenderTxnFeeDetail
	 *
	 * @return List<OffenderTxnFeeDetail>
	 *
	 * 
	 */
	public List<OffenderTxnFeeDetails> offTfdExecuteQuery(final OffenderTxnFeeDetails objSearchDao) {
		final String sql = getQuery("OTDOCFEE_OFFTFD_FIND_OFFENDER_TXN_FEE_DETAILS");
		final RowMapper<OffenderTxnFeeDetails> offenderTxnFeeDetailsRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderTxnFeeDetails.class, offenderTxnFeeDetailsMapping);
		return namedParameterJdbcTemplate.query(sql,
				createParams("OFFENDER_DEDUCTION_ID", objSearchDao.getOffenderDeductionId()),
				offenderTxnFeeDetailsRowMapper);
	}

	/**
	 * This method is used to insert the records in the data base tables based
	 * on
	 *
	 * @param lstOffenderTxnFeeDetails
	 *            List<OffenderTxnFeeDetail>
	 *
	 * @return List<Integer>
	 *
	 * 
	 */
	public Integer offTfdInsertOffenderTxnFeeDetails(final List<OffenderTxnFeeDetails> lstOffenderTxnFeeDetails) {
		final String sql = getQuery("OTDOCFEE_OFFTFD_INSERT_OFFENDER_TXN_FEE_DETAILS");
		int[] returnArray = null;
		final List<SqlParameterSource> parameters = new ArrayList<>();
		for (final OffenderTxnFeeDetails offenderDeduction : lstOffenderTxnFeeDetails) {
			parameters.add(new BeanPropertySqlParameterSource(offenderDeduction));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error(this.getClass().getName()+"Error in method offTfdInsertOffenderTxnFeeDetails :", e);
		}
		if (lstOffenderTxnFeeDetails.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstOffenderTxnFeeDetails
	 *            List<OffenderTxnFeeDetail>
	 *
	 * 
	 */
	public Integer offTfdUpdateOffenderTxnFeeDetails(final List<OffenderTxnFeeDetails> lstOffenderTxnFeeDetails) {
		final String sql = getQuery("OTDOCFEE_OFFTFD_UPDATE_OFFENDER_TXN_FEE_DETAILS");
		int[] returnArray = null;
		final List<SqlParameterSource> parameters = new ArrayList<>();
		for (final OffenderTxnFeeDetails offenderTxnFeeDetails : lstOffenderTxnFeeDetails) {
			parameters.add(new BeanPropertySqlParameterSource(offenderTxnFeeDetails));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error(this.getClass().getName()+"Error in method offTfdUpdateOffenderTxnFeeDetails :", e);
		}
		if (lstOffenderTxnFeeDetails.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to delete records from data base tables based on
	 *
	 * @param lstOffenderTxnFeeDetails
	 *            List<OffenderTxnFeeDetail>
	 *
	 * 
	 */
	public Integer offTfdDeleteOffenderTxnFeeDetails(final List<OffenderTxnFeeDetails> lstOffenderTxnFeeDetails) {
		final String sql = getQuery("OTDOCFEE_OFFTFD_DELETE_OFFENDER_TXN_FEE_DETAILS");
		int[] returnArray = null;
		final List<SqlParameterSource> parameters = new ArrayList<>();
		for (final OffenderTxnFeeDetails offenderTxnFeeDetails : lstOffenderTxnFeeDetails) {
			parameters.add(new BeanPropertySqlParameterSource(offenderTxnFeeDetails));
		}
		try {
			String tableName = "OFFENDER_TXN_FEE_DETAILS";
			String whereClause = "OFFENDER_DEDUCTION_ID  = :offenderDeductionId and ROW_ID =(:rowId::bigint)";
			batchUpdatePreDeletedRows(tableName, whereClause , parameters);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method offTfdDeleteOffenderTxnFeeDetails", e);
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error(this.getClass().getName()+"Error in method offTfdDeleteOffenderTxnFeeDetails :", e);
		}
		if (lstOffenderTxnFeeDetails.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            OffenderTierTxnFeeAmount
	 *
	 * @return List<OffenderTierTxnFeeAmount>
	 *
	 * 
	 */
	public List<OffenderTierTxnFeeAmounts> offTtfExecuteQuery(final OffenderTierTxnFeeAmounts objSearchDao) {
		final String sql = getQuery("OTDOCFEE_OFFTTF_FIND_OFFENDER_TIER_TXN_FEE_AMOUNTS");
		final RowMapper<OffenderTierTxnFeeAmounts> offenderTierTxnFeeAmountsRowMapper = Row2BeanRowMapper
				.makeMapping(sql, OffenderTierTxnFeeAmounts.class, offenderTierTxnFeeAmountsMapping);
		return namedParameterJdbcTemplate.query(sql,
				createParams("OFFENDER_DEDUCTION_ID", objSearchDao.getOffenderDeductionId()),
				offenderTierTxnFeeAmountsRowMapper);
	}

	/**
	 * This method is used to insert the records in the data base tables based
	 * on
	 *
	 * @param lstOffenderTierTxnFeeAmounts
	 *            List<OffenderTierTxnFeeAmount>
	 *
	 * @return List<Integer>
	 *
	 */
	public Integer offTtfInsertOffenderTierTxnFeeAmounts(
			final List<OffenderTierTxnFeeAmounts> lstOffenderTierTxnFeeAmounts) {
		final String sql = getQuery("OTDOCFEE_OFFTTF_INSERT_OFFENDER_TIER_TXN_FEE_AMOUNTS");
		int[] returnArray = null;
		final List<SqlParameterSource> parameters = new ArrayList<>();
		for (final OffenderTierTxnFeeAmounts offenderTierTxnFeeAmounts : lstOffenderTierTxnFeeAmounts) {
			parameters.add(new BeanPropertySqlParameterSource(offenderTierTxnFeeAmounts));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error(this.getClass().getName()+"Error in method offTtfInsertOffenderTierTxnFeeAmounts :", e);
		}
		if (lstOffenderTierTxnFeeAmounts.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstOffenderTierTxnFeeAmounts
	 *            List<OffenderTierTxnFeeAmount>
	 *
	 * 
	 */
	public Integer offTtfUpdateOffenderTierTxnFeeAmounts(
			final List<OffenderTierTxnFeeAmounts> lstOffenderTierTxnFeeAmounts) {
		final String sql = getQuery("OTDOCFEE_OFFTTF_UPDATE_OFFENDER_TIER_TXN_FEE_AMOUNTS");
		int[] returnArray = null;
		final List<SqlParameterSource> parameters = new ArrayList<>();
		for (final OffenderTierTxnFeeAmounts offenderTierTxnFeeAmounts : lstOffenderTierTxnFeeAmounts) {
			parameters.add(new BeanPropertySqlParameterSource(offenderTierTxnFeeAmounts));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error(this.getClass().getName()+"Error in method offTtfUpdateOffenderTierTxnFeeAmounts :", e);
		}
		if (lstOffenderTierTxnFeeAmounts.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	public Long getOffenderId() {
		final String sql = getQuery("OTDOCFEE_OFF_DED_PREINSERT");
		Long returnObj = null;
		returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), Long.class);
		return returnObj;
	}

	/**
	 * This method is used to delete records from data base tables based on
	 *
	 * @param lstOffenderTierTxnFeeAmounts
	 *            List<OffenderTierTxnFeeAmount>
	 *
	 * 
	 */
	public Integer offTtfDeleteOffenderTierTxnFeeAmounts(
			final List<OffenderTierTxnFeeAmounts> lstOffenderTierTxnFeeAmounts) {
		final String sql = getQuery("OTDOCFEE_OFFTTF_DELETE_OFFENDER_TIER_TXN_FEE_AMOUNTS");
		int[] returnArray = null;
		final List<SqlParameterSource> parameters = new ArrayList<>();
		for (final OffenderTierTxnFeeAmounts offenderTierTxnFeeAmounts : lstOffenderTierTxnFeeAmounts) {
			parameters.add(new BeanPropertySqlParameterSource(offenderTierTxnFeeAmounts));
		}
		try {
			String tableName = "OFFENDER_TIER_TXN_FEE_AMOUNTS";
			String whereClause = "OFFENDER_DEDUCTION_ID  = :offenderDeductionId and ROW_ID =(:rowId::bigint)";
			batchUpdatePreDeletedRows(tableName, whereClause , parameters);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method offTtfDeleteOffenderTierTxnFeeAmounts", e);
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error(this.getClass().getName()+"Error in method offTtfDeleteOffenderTierTxnFeeAmounts :", e);
		}

		if (lstOffenderTierTxnFeeAmounts.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            SystemProfiles
	 *
	 * @return List<SystemProfiles>
	 *
	 * 
	 */
	public List<SystemProfiles> sysPflExecuteQuery(final SystemProfiles objSearchDao) {
		final String sql = getQuery("OTDOCFEE_SYSPFL_FIND_SYSTEM_PROFILES");
		final RowMapper<SystemProfiles> systemProfilesRowMapper = Row2BeanRowMapper.makeMapping(sql,
				SystemProfiles.class, systemProfilesMapping);
		return namedParameterJdbcTemplate.query(sql, createParams(), systemProfilesRowMapper);
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<ReferenceCodes> cgfkOffDedDspDescriptionRecordGroup() {
		final String sql = getQuery("OTDOCFEE_FIND_CGFKOFFDEDDSPDESCRIPTION");
		List<ReferenceCodes> returnList = new ArrayList<>();
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);

		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (final EmptyResultDataAccessException e) {
			logger.error(this.getClass().getName()+"Error in method cgfkOffDedDspDescriptionRecordGroup :", e);
			return Collections.emptyList();
		}
		return returnList;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<DeductionTypes> cgfkOffTfdReceiptDeductionRecordGroup(final String caseloadType) {
		final String sql = getQuery("OTDOCFEE_FIND_CGFKOFFTFDRECEIPTDEDUCTION");
		List<DeductionTypes> returnList = new ArrayList<>();
		final RowMapper<DeductionTypes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, DeductionTypes.class, mMapping);

		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams("CASELOADTYPE", caseloadType), mRowMapper);
		} catch (final EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
		return returnList;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<DeductionTypes> cgfkOffDedDeductionTypeRecordGroup(final String caseloadId, final String caseloadType) {
		final String sql = getQuery("OTDOCFEE_FIND_CGFKOFFDEDDEDUCTIONTYPE");
		List<DeductionTypes> returnList = new ArrayList<>();
		final RowMapper<DeductionTypes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, DeductionTypes.class, mMapping);

		try {
			returnList = namedParameterJdbcTemplate.query(sql,
					createParams("CASELOADID", caseloadId, "CASELOADTYPE", caseloadType), mRowMapper);
		} catch (final EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * offBkgOnCheckDeleteMaster
	 *
	 * @param params
	 *
	 */
	public Long offBkgOnCheckDeleteMaster(final OffenderDeductions paramBean) {
		final String sql = getQuery("OTDOCFEE_OFF_BKG_ONCHECKDELETEMASTER");
		return namedParameterJdbcTemplate.queryForObject(sql,createParams("OFFENDERID", paramBean.getOffenderId()),
				Long.class);
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * offDedPreInsert
	 *
	 * @param params
	 *
	 */
	public Object offDedPreInsert(final SysDual paramBean) {
		final String sql = getQuery("OTDOCFEE_OFF_DED_PREINSERT");
		final RowMapper<SysDual> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, SysDual.class, sysDualMapping);
		return namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfkchkOffDedOffDedRef
	 *
	 * @param params
	 *
	 */
	public List<ReferenceCodes> cgfkchkOffDedOffDedRef(final ReferenceCodes paramBean) {
		final String sql = getQuery("OTDOCFEE_CGFKCHK_OFF_DED_OFF_DED_REF_C");
		final RowMapper<ReferenceCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				referenceCodesMapping);
		return namedParameterJdbcTemplate.query(sql, createParams(), columnRowMapper);
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfklkpOffDedOffDedRef
	 *
	 * @param params
	 *
	 */
	public List<ReferenceCodes> cgfklkpOffDedOffDedRef(final ReferenceCodes paramBean) {
		final String sql = getQuery("OTDOCFEE_CGFKLKP_OFF_DED_OFF_DED_REF_C");
		final RowMapper<ReferenceCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				referenceCodesMapping);
		return namedParameterJdbcTemplate.query(sql, createParams(), columnRowMapper);
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfkchkOffDedOffDedDed
	 *
	 * @param params
	 *
	 */
	public List<DeductionTypes> cgfkchkOffDedOffDedDed(final DeductionTypes paramBean) {
		final String sql = getQuery("OTDOCFEE_CGFKCHK_OFF_DED_OFF_DED_DED_T");
		final RowMapper<DeductionTypes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, DeductionTypes.class,
				deductionTypesMapping);
		return namedParameterJdbcTemplate.query(sql, createParams(), columnRowMapper);
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfkchkOffTfdOffTfdDed
	 *
	 * @param params
	 *
	 */
	public List<DeductionTypes> cgfkchkOffTfdOffTfdDed(final DeductionTypes paramBean) {
		final String sql = getQuery("OTDOCFEE_CGFKCHK_OFF_TFD_OFF_TFD_DED_T");
		final RowMapper<DeductionTypes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, DeductionTypes.class,
				deductionTypesMapping);
		return namedParameterJdbcTemplate.query(sql, createParams(), columnRowMapper);
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgrichkOffenderDeductions
	 *
	 * @param params
	 *
	 */
	public Long cgrichkOffenderDeductions(final OffenderTransactions paramBean) {
		final String sql = getQuery("OTDOCFEE_CGRICHK_OFFENDER_DEDUCTIONS_ONE");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("OFFENDERID",paramBean.getOffenderId()), Long.class);
	}
	
	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgrichkOffenderDeductions
	 *
	 * @param params
	 *
	 */
	public Long r(final OffenderPaymentPlan paramBean) {
		final String sql = getQuery("OTDOCFEE_CGRICHK_OFFENDER_DEDUCTIONS_OFFENDER_DEDUCTION_ID");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("OFFENDERDEDUCTIONID",paramBean.getOffenderDeductionId()), Long.class);
	}
	
	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgrichkOffenderDeductions
	 *
	 * @param params
	 *
	 */
	public Long otdocfeeCgrichkOffenderDeductionsInformationNumber(final OffenderPaymentPlan paramBean) {
		final String sql = getQuery("OTDOCFEE_CGRICHK_OFFENDER_DEDUCTIONS_INFORMATION_NUMBER");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("INFORMATIONNUMBER",paramBean.getInformationNumber()), Long.class);
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgrichkOffenderDeductions
	 *
	 * @param params
	 *
	 */
	public Long cgrichkOffenderDeductions(final OffenderPaymentPlan paramBean) {
		final String sql = getQuery("OTDOCFEE_CGRICHK_OFFENDER_DEDUCTIONS");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams(), Long.class);
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgwhenNewFormInstance
	 *
	 * @param params
	 *
	 */
	public List<SysDual> cgwhenNewFormInstance(final SysDual paramBean) {
		final String sql = getQuery("OTDOCFEE_CGWHEN_NEW_FORM_INSTANCE");
		final RowMapper<SysDual> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, SysDual.class, sysDualMapping);
		return namedParameterJdbcTemplate.query(sql, createParams(), columnRowMapper);
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgrichkOffenderDeductions
	 *
	 * @param params
	 *
	 */
	public Integer otdocfeePopulateDetails(final OffenderTxnFeeDetails paramBean) {
		final String sql = getQuery("OTDOCFEE_POPULATE_DETAILS");
		Integer data = null;
		try {
			data = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("OFFENDERDEDUCTIONID", paramBean.getOffenderDeductionId()), Integer.class);
		} catch (Exception e) {
			logger.error(this.getClass().getName()+"Error in method otdocfeePopulateDetails :", e);
			return data;
		}
		return data;
	}
	

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgrichkOffenderDeductions
	 *
	 * @param params
	 *
	 */
	public List<TransactionFeeDetails> otdocfeePopulateDetailsReceiptDeductionType(final String deductionType , final String caseloadId) {
		final String sql = getQuery("OTDOCFEE_POPULATE_DETAILS_RECEIPT_DEDUCTION_TYPE");
		final RowMapper<TransactionFeeDetails> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, TransactionFeeDetails.class, mMapping);
		return namedParameterJdbcTemplate.query(sql, createParams("DEDUCTIONTYPE",deductionType,"CASELOADID" ,caseloadId), columnRowMapper );
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgrichkOffenderDeductions
	 *
	 * @param params
	 *
	 */
	public List<TieredTransactionFeeAmounts> otdocfeePopulateDetailsTieredTransactionFeeAmounts(final OffenderDeductions paramBean) {
		final String sql = getQuery("OTDOCFEE_POPULATE_DETAILS_TIERED_TRANSACTION_FEE_AMOUNTS");
		final RowMapper<TieredTransactionFeeAmounts> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				TieredTransactionFeeAmounts.class, offenderPaymentPlanMapping);
		return namedParameterJdbcTemplate.query(sql, createParams("deductionType",paramBean.getDeductionType(),"caseloadId", paramBean.getCaseloadId()), columnRowMapper);
	}
	
	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgrichkOffenderDeductions
	 *
	 * @param params
	 *
	 */
	 public Long otdocfeePostQueryTwo() {
	 final String sql = getQuery("OTDOCFEE_POST_QUERY_TWO");
	 return namedParameterJdbcTemplate.queryForObject(sql, createParams(),
			 Long.class);
	 }

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgrichkOffenderDeductions
	 *
	 * @param params
	 *
	 */
	public BigDecimal otdocfeePostQueryTwoPayeeCorporateId(final CaseloadDeductionProfiles paramBean) {
		final String sql = getQuery("OTDOCFEE_POST_QUERY_TWO_PAYEE_CORPORATE_ID");
		BigDecimal data = null;
		try {
			data = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("caseloadId", paramBean.getCaseloadId(), "deductionType", paramBean.getDeductionType()), BigDecimal.class);
		} catch(Exception e) {
			logger.error(this.getClass().getName()+"Error in method otdocfeePostQueryTwoPayeeCorporateId :", e);
			return data;
		}
		return data;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgrichkOffenderDeductions
	 *
	 * @param params
	 *
	 */

	public Integer otdocfeePostQueryTwoUnknownBenId() {
		final String sql = getQuery("OTDOCFEE_POST_QUERY_TWO_UNKNOWN_BEN_ID");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams(), Integer.class);
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgrichkOffenderDeductions
	 *
	 * @param params
	 *
	 */
	public Long otdocfeeGetOffenderDeductionId(final Long offenderDeductionId) {
		final String sql = getQuery("OTDOCFEE_GET_OFFENDER_DEDUCTION_ID");
		Long data = null;
		try {
			data = namedParameterJdbcTemplate.queryForObject(sql,createParams("OFFENDERDEDUCTIONID", offenderDeductionId), Long.class);;
		} catch(Exception e) {
			logger.error(this.getClass().getName()+"Error in method otdocfeeGetOffenderDeductionId :", e);
			return data;
		}
		return data;
	}
	
	public Integer insertIntoOffenderBeneficiaries(final OffenderBeneficiaries offBeneficiaries) {
		final String sql = getQuery("OTDOCFEE_INSERT_INTO_OFFENDER_BENEFICIARIES");
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("d_off_ded_id", offBeneficiaries.getOffenderDeductionId());
		paramMap.put("off_id", offBeneficiaries.getOffenderId());
		paramMap.put("p_corp_id", offBeneficiaries.getCorporateId());
		paramMap.put("off_ben_id", offBeneficiaries.getBeneficiaryId());
		paramMap.put("unknownben_id", offBeneficiaries.getUnknownBenId());
		paramMap.put("p_corp_id", offBeneficiaries.getCorporateId());
		paramMap.put("createUserId", offBeneficiaries.getCreateUserId());
		Integer returnArray = null;
		try {
			returnArray = namedParameterJdbcTemplate.update(sql, paramMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName()+"Error in method insertIntoOffenderBeneficiaries :", e);
		}
		if (returnArray != 0) {
			returnArray = 1;
			return returnArray;
		}
		return returnArray;

}
	public Integer insertIntoOffenderTxnFeeDetails(final OffenderTxnFeeDetails offTxnFeeDetails) {
		final String sql = getQuery("INSERT_INTO_OFFENDER_TXN_FEE_DETAILS");
		Map<String , Object> paramMap = new HashMap<>();
		paramMap.put("d_off_ded_id",offTxnFeeDetails.getOffenderDeductionId());
		paramMap.put("receipt_ded", offTxnFeeDetails.getReceiptDeductionType());
		paramMap.put("createUserId", offTxnFeeDetails.getCreateUserId());
		Integer returnArray = null;
		try {
			returnArray = namedParameterJdbcTemplate.update(sql, paramMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName()+"Error in method insertIntoOffenderTxnFeeDetails :", e);
		}
		if (returnArray != 0) {
			returnArray = 1;
			return returnArray;
		}
		return returnArray;
	}
	public Integer insertIntoOffenderTierTxnFeeAmounts(final OffenderTierTxnFeeAmounts offTierTxnFeeDetails) {
		final String sql = getQuery("INSERT_INTO_OFFENDER_TIER_TXN_FEE_AMOUNTS");
		Map<String , Object> paramMap = new HashMap<>();
		paramMap.put("d_off_ded_id", offTierTxnFeeDetails.getOffenderDeductionId());
		paramMap.put("c_from", offTierTxnFeeDetails.getFromAmount());
		paramMap.put("c_to", offTierTxnFeeDetails.getToAmount());
		paramMap.put("c_percent", offTierTxnFeeDetails.getPercentage());
		paramMap.put("c_fee_amt", offTierTxnFeeDetails.getFeeAmount());
		paramMap.put("createUserId", offTierTxnFeeDetails.getCreateUserId());
		Integer returnArray = null;
		try {
			returnArray = namedParameterJdbcTemplate.update(sql, paramMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName()+"Error in method insertIntoOffenderTierTxnFeeAmounts :", e);
		}
		if (returnArray != 0) {
			returnArray = 1;
			return returnArray;
		}
		return returnArray;
	}

	@Override
	public Long otdocfeeCgrichkOffenderDeductionsOffenderDeductionId(OffenderPaymentPlan paramBean) {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgrichkOffenderDeductions
	 *
	 * @param params
	 *
	 */
	public Integer otdocfee_offender_deductions_count(final String caseloadId, final String deductionType) {
		final String sql = getQuery("OTDOCFEE_OFFENDER_DEDUCTIONS_COUNT");
		return namedParameterJdbcTemplate.queryForObject(sql,
				createParams("caseloadId", caseloadId, "deductionType", deductionType), Integer.class);
	}
	public String otdocfeeGetCaseloadType(final String caseloadId) {
		final String sql = getQuery("OTDOCFEE_GET_CASELOAD_TYPE");
		return namedParameterJdbcTemplate.queryForObject(sql,
				createParams("caseloadId", caseloadId), String.class);
	}

	public Integer getOverLapCount(final OffenderTierTxnFeeAmounts paramBean) {
		final String sql = getQuery("OTDOCFEE_GET_OVERLAP_COUNT");
		Integer returnList = 0;
		returnList = namedParameterJdbcTemplate.queryForObject(sql,
				createParams("pFlag", paramBean.getpFlag(), "caseloadId", paramBean.getCaseloadId(), "deductionType",
						paramBean.getDeductionType(), "fromAmount", paramBean.getFromAmount(), "toAmount",
						paramBean.getToAmount()),
				Integer.class);
		return returnList;
	}
	
	}



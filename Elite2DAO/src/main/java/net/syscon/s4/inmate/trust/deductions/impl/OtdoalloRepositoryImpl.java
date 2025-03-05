package net.syscon.s4.inmate.trust.deductions.impl;
import java.math.BigDecimal;
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
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.beans.TransactionTypes;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.OffenderDeductionReceipts;
import net.syscon.s4.inmate.beans.CaseloadDeductionProfiles;
import net.syscon.s4.inmate.beans.DeductionTypes;
import net.syscon.s4.inmate.beans.OffenderDeductions;
import net.syscon.s4.inmate.trust.deductions.OtdoalloRepository;
/**
 * Class OtdoalloRepositoryImpl
 */
@Repository
public class OtdoalloRepositoryImpl extends RepositoryBase implements OtdoalloRepository{
	
	private static Logger logger = LogManager.getLogger(OtdoalloRepositoryImpl.class.getName());

/**
 * Creates new OtdoalloRepositoryImpl class Object 
 */
public OtdoalloRepositoryImpl() {
	// OtdoalloRepositoryImpl
}
private final Map<String, FieldMapper> transactionTypesMapping = new ImmutableMap.Builder<String, FieldMapper>()
.put("DESCRIPTION", 						new FieldMapper("description"))
.put("RECEIPT_TXN_TYPE", 						new FieldMapper("receiptTxnType"))
.build();
private final Map<String, FieldMapper> referenceCodesMapping = new ImmutableMap.Builder<String, FieldMapper>()
.put("DESCRIPTION", 						new FieldMapper("description"))
.put("DEDUCTION_STATUS", 						new FieldMapper("deductionStatus"))
.build();
private final Map<String, FieldMapper> offenderDeductionsMapping = new ImmutableMap.Builder<String, FieldMapper>()
.put("ADJUSTMENT_USER_ID", 						new FieldMapper("adjustmentUserId"))
.put("GROUP_ID", 						new FieldMapper("groupId"))
.put("PROCESS_PRIORITY_NUMBER", 						new FieldMapper("processPriorityNumber"))
.put("PAY_DEDUCTION_FLAG", 						new FieldMapper("payDeductionFlag"))
.put("DEDUCTION_STATUS", 						new FieldMapper("deductionStatus"))
.put("COLLECT_SENT_DATE", 						new FieldMapper("collectSentDate"))
.put("MAX_RECURSIVE_AMOUNT", 						new FieldMapper("maxRecursiveAmount"))
.put("JS_STATUS", 						new FieldMapper("jsStatus"))
.put("OFFENDER_ID", 						new FieldMapper("offenderId"))
.put("FIFO_FLAG", 						new FieldMapper("fifoFlag"))
.put("ADJUSTMENT_AMOUNT", 						new FieldMapper("adjustmentAmount"))
.put("DEDUCTION_PRIORITY", 						new FieldMapper("deductionPriority"))
.put("SEAL_FLAG", 						new FieldMapper("sealFlag"))
.put("CREATE_DATETIME", 						new FieldMapper("createDateTime"))
.put("EFFECTIVE_DATE", 						new FieldMapper("effectiveDate"))
.put("DEDUCTION_AMOUNT", 						new FieldMapper("deductionAmount"))
.put("MODIFY_DATETIME", 						new FieldMapper("modifyDatetime"))
.put("PAYEE_PERSON_ID", 						new FieldMapper("payeePersonId"))
.put("ADJUSTMENT_TEXT", 						new FieldMapper("adjustmentText"))
.put("COLLECT_AGENCY_FLAG", 						new FieldMapper("collectAgencyFlag"))
.put("COMMENT_TEXT", 						new FieldMapper("commentText"))
.put("ADJUSTMENT_REASON_CODE", 						new FieldMapper("adjustmentReasonCode"))
.put("CREATE_USER_ID", 						new FieldMapper("createUserId"))
.put("OFFENDER_DEDUCTION_ID", 						new FieldMapper("offenderDeductionId"))
.put("OFFENDER_PAYMENT_PROFILE_ID", 						new FieldMapper("offenderPaymentProfileId"))
.put("MAX_TOTAL_AMOUNT", 						new FieldMapper("maxTotalAmount"))
.put("MODIFY_USER_ID", 						new FieldMapper("modifyUserId"))
.put("INFORMATION_NUMBER", 						new FieldMapper("informationNumber"))
.put("ADJUSTMENT_TXN_ID", 						new FieldMapper("adjustmentTxnId"))
.put("CASE_ID", 						new FieldMapper("caseId"))
.put("COLLECT_AGENCY_AMOUNT", 						new FieldMapper("collectAgencyAmount"))
.put("CREDIT_LIMIT", 						new FieldMapper("creditLimit"))
.put("DEDUCTION_PERCENTAGE", 						new FieldMapper("deductionPercentage"))
.put("CASELOAD_ID", 						new FieldMapper("caseloadId"))
.put("MAX_MONTHLY_AMOUNT", 						new FieldMapper("maxMonthlyAmount"))
.put("MODIFY_DATE", 						new FieldMapper("modifyDate"))
.put("DEDUCTION_TYPE", 						new FieldMapper("deductionType"))
.put("PARENT_DEDUCTION_ID", 						new FieldMapper("parentDeductionId"))
.put("PAYEE_CORPORATE_ID", 						new FieldMapper("payeeCorporateId"))
.build();
private final Map<String, FieldMapper> deductionTypesMapping = new ImmutableMap.Builder<String, FieldMapper>()
.put("DEDUCTION_TYPE", 						new FieldMapper("deductionType"))
.put("DEDUCTION_DESC", 						new FieldMapper("deductionDesc"))
.put("CASELOAD_ID", 						new FieldMapper("caseloadId"))
.build();
private final Map<String, FieldMapper> offenderDeductionReceiptsMapping = new ImmutableMap.Builder<String, FieldMapper>()
.put("CREATE_USER_ID", 						new FieldMapper("createUserId"))
.put("OFFENDER_DEDUCTION_ID", 						new FieldMapper("offenderDeductionId"))
.put("SEAL_FLAG", 						new FieldMapper("sealFlag"))
.put("CREATE_DATETIME", 						new FieldMapper("createDatetime"))
.put("MODIFY_USER_ID", 						new FieldMapper("modifyUserId"))
.put("LIST_SEQ", 						new FieldMapper("listSeq"))
.put("RECEIPT_PERCENTAGE", 						new FieldMapper("receiptPercentage"))
.put("FLAT_RATE", 						new FieldMapper("flatRate"))
.put("MODIFY_DATETIME", 						new FieldMapper("modifyDatetime"))
.put("RECEIPT_TXN_TYPE", 						new FieldMapper("receiptTxnType"))
.build();
private final Map<String, FieldMapper> systemProfilesMapping = new ImmutableMap.Builder<String, FieldMapper>()
.put("PROFILE_CODE", 						new FieldMapper("profileCode"))
.put("PROFILE_TYPE", 						new FieldMapper("profileType"))
.put("CREATE_USER_ID", 						new FieldMapper("createUserId"))
.put("SEAL_FLAG", 						new FieldMapper("sealFlag"))
.put("CREATE_DATETIME", 						new FieldMapper("createDatetime"))
.put("MODIFY_USER_ID", 						new FieldMapper("modifyUserId"))
.put("OLD_TABLE_NAME", 						new FieldMapper("oldTableName"))
.put("PROFILE_VALUE", 						new FieldMapper("profileValue"))
.put("MODIFY_DATETIME", 						new FieldMapper("modifyDatetime"))
.put("PROFILE_VALUE_2", 						new FieldMapper("profileValue2"))
.put("DESCRIPTION", 						new FieldMapper("description"))
.build();
private final Map<String, FieldMapper> mMapping = new ImmutableMap.Builder<String, FieldMapper>()
.put("DEDUCTION_TYPE", 						new FieldMapper("deductionType"))
.put("CODE", 						new FieldMapper("code"))
.put("TXN_TYPE", 						new FieldMapper("txnType"))
.build();

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
		final String sql = getQuery("OTDOALLO_OFFDED_FIND_OFFENDER_DEDUCTIONS");
		final RowMapper<OffenderDeductions> OffenderDeductionsRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderDeductions.class, offenderDeductionsMapping);
		final ArrayList<OffenderDeductions> returnList = (ArrayList<OffenderDeductions>) namedParameterJdbcTemplate
				.query(sql, createParams("offenderId", objSearchDao.getOffenderId(), "caseLoadId",
						objSearchDao.getCaseloadId()), OffenderDeductionsRowMapper);
		return returnList;
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
		final String sql = getQuery("OTDOALLO_OFFDED_INSERT_OFFENDER_DEDUCTIONS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffenderDeductions offenderDeductions : lstOffenderDeductions) {
			parameters.add(new BeanPropertySqlParameterSource(offenderDeductions));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstOffenderDeductions.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

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
		final String sql = getQuery("OTDOALLO_OFFDED_UPDATE_OFFENDER_DEDUCTIONS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffenderDeductions offenderDeductions : lstOffenderDeductions) {
			parameters.add(new BeanPropertySqlParameterSource(offenderDeductions));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstOffenderDeductions.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to delete records from data base tables based on
	 *
	 * @param lstOffenderDeductions
	 *            List<OffenderDeductions>
	 *
	 *
	 */
	public Integer offDedDeleteOffenderDeductions(final List<OffenderDeductions> lstOffenderDeductions) {
		final String sql = getQuery("OTDOALLO_OFFDED_DELETE_OFFENDER_DEDUCTIONS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffenderDeductions offenderDeductions : lstOffenderDeductions) {
			parameters.add(new BeanPropertySqlParameterSource(offenderDeductions));
		}
		try {
			String tableName = "OFFENDER_DEDUCTIONS";
			String whereClause = "OFFENDER_DEDUCTION_ID  = :offenderDeductionId";
			batchUpdatePreDeletedRows(tableName, whereClause , parameters);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method offDedDeleteOffenderDeductions", e);
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
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
	 *            OffenderDeductionReceipts
	 *
	 * @return List<OffenderDeductionReceipts>
	 *
	 *
	 */
	public List<OffenderDeductionReceipts> offDrExecuteQuery(final OffenderDeductionReceipts objSearchDao) {
		final String sql = getQuery("OTDOALLO_OFFDR_FIND_OFFENDER_DEDUCTION_RECEIPTS");
		final RowMapper<OffenderDeductionReceipts> OffenderDeductionReceiptsRowMapper = Row2BeanRowMapper
				.makeMapping(sql, OffenderDeductionReceipts.class, offenderDeductionReceiptsMapping);
		final ArrayList<OffenderDeductionReceipts> returnList = (ArrayList<OffenderDeductionReceipts>) namedParameterJdbcTemplate
				.query(sql, createParams("offenderDeductionId", objSearchDao.getOffenderDeductionId()),
						OffenderDeductionReceiptsRowMapper);
		return returnList;
	}

	/**
	 * This method is used to insert the records in the data base tables based
	 * on
	 *
	 * @param lstOffenderDeductionReceipts
	 *            List<OffenderDeductionReceipts>
	 *
	 * @return List<Integer>
	 *
	 *
	 */
	public Integer offDrInsertOffenderDeductionReceipts(
			final List<OffenderDeductionReceipts> lstOffenderDeductionReceipts) {
		final String sql = getQuery("OTDOALLO_OFFDR_INSERT_OFFENDER_DEDUCTION_RECEIPTS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffenderDeductionReceipts offenderDeductionReceipts : lstOffenderDeductionReceipts) {
			parameters.add(new BeanPropertySqlParameterSource(offenderDeductionReceipts));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstOffenderDeductionReceipts.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstOffenderDeductionReceipts
	 *            List<OffenderDeductionReceipts>
	 *
	 *
	 */
	public Integer offDrUpdateOffenderDeductionReceipts(
		final List<OffenderDeductionReceipts> lstOffenderDeductionReceipts) {
		final String sql = getQuery("OTDOALLO_OFFDR_UPDATE_OFFENDER_DEDUCTION_RECEIPTS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffenderDeductionReceipts offenderDeductionReceipts : lstOffenderDeductionReceipts) {
			parameters.add(new BeanPropertySqlParameterSource(offenderDeductionReceipts));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstOffenderDeductionReceipts.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to delete records from data base tables based on
	 *
	 * @param lstOffenderDeductionReceipts
	 *            List<OffenderDeductionReceipts>
	 *
	 *
	 */
	public Integer offDrDeleteOffenderDeductionReceipts(
			final List<OffenderDeductionReceipts> lstOffenderDeductionReceipts) {
		final String sql = getQuery("OTDOALLO_OFFDR_DELETE_OFFENDER_DEDUCTION_RECEIPTS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffenderDeductionReceipts offenderDeductionReceipts : lstOffenderDeductionReceipts) {
			parameters.add(new BeanPropertySqlParameterSource(offenderDeductionReceipts));
		}
		try {
			String tableName = "OFFENDER_DEDUCTION_RECEIPTS";
			String whereClause = "RECEIPT_TXN_TYPE  = :receiptTxnType AND OFFENDER_DEDUCTION_ID  = :offenderDeductionId";
			batchUpdatePreDeletedRows(tableName, whereClause , parameters);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method offDrDeleteOffenderDeductionReceipts", e);
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstOffenderDeductionReceipts.size() == returnArray.length) {
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
		final String sql = getQuery("OTDOALLO_SYSPFL_FIND_SYSTEM_PROFILES");
		final RowMapper<SystemProfiles> SystemProfilesRowMapper = Row2BeanRowMapper.makeMapping(sql,
				SystemProfiles.class, systemProfilesMapping);
		final ArrayList<SystemProfiles> returnList = (ArrayList<SystemProfiles>) namedParameterJdbcTemplate.query(sql,
				createParams(), SystemProfilesRowMapper);
		return returnList;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<ReferenceCodes> cgfkOffDedDeductionStatusRecordGroup() {
		final String sql = getQuery("OTDOALLO_FIND_CGFKOFFDEDDEDUCTIONSTATUS");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<TransactionTypes> cgfkOffDrReceiptTxnTypeRecordGroup() {
		final String sql = getQuery("OTDOALLO_FIND_CGFKOFFDRRECEIPTTXNTYPE");
		final RowMapper<TransactionTypes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, TransactionTypes.class,
				mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<DeductionTypes> cgfkOffDedDeductionTypeRecordGroup(final String caseLoadId) {
		final String sql = getQuery("OTDOALLO_FIND_CGFKOFFDEDDEDUCTIONTYPE");
		final RowMapper<DeductionTypes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, DeductionTypes.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams("caseLoadId", caseLoadId), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfkchkOffDedOffDedRef
	 *
	 * @param params
	 *
	 */
	public ReferenceCodes cgfkchkOffDedOffDedRef(final ReferenceCodes paramBean) {
		ReferenceCodes returnObj;
		final String sql = getQuery("OTDOALLO_CGFKCHK_OFF_DED_OFF_DED_REF_C");
		final RowMapper<ReferenceCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				referenceCodesMapping);
		returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfkchkOffDedOffDedDed
	 *
	 * @param params
	 *
	 */
	public DeductionTypes cgfkchkOffDedOffDedDed(final DeductionTypes paramBean) {
		DeductionTypes returnObj;
		final String sql = getQuery("OTDOALLO_CGFKCHK_OFF_DED_OFF_DED_DED_T");
		final RowMapper<DeductionTypes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, DeductionTypes.class,
				deductionTypesMapping);
		returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfkchkOffDrOffDrTxnTyp
	 *
	 * @param params
	 *
	 */
	public TransactionTypes cgfkchkOffDrOffDrTxnTyp(final TransactionTypes paramBean) {
		final TransactionTypes returnObj;
		final String sql = getQuery("OTDOALLO_CGFKCHK_OFF_DR_OFF_DR_TXN_TYP");
		final RowMapper<TransactionTypes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, TransactionTypes.class,
				transactionTypesMapping);
		returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
		return returnObj;
	}

	@Override
	public String getModifyUserId(final String deductionType) {
		final String sql = getQuery("OTDOALLO_GETMODIFYUSERID_CUR");
		String returnList;
		returnList = namedParameterJdbcTemplate.queryForObject(sql, createParams("deductionType", deductionType),
				String.class);
		return returnList;
	}

	@Override
	public CaseloadDeductionProfiles offDedPostQuery(final OffenderDeductions returnBean) {
		final CaseloadDeductionProfiles returnObj;
		final String sql = getQuery("OTDOALLO_OFFDEDPOSTQUERY");
		final RowMapper<CaseloadDeductionProfiles> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				CaseloadDeductionProfiles.class, offenderDeductionsMapping);
		returnObj = namedParameterJdbcTemplate.queryForObject(sql,
				createParams("deductionType", returnBean.getDeductionType(), "caseLoadId", returnBean.getCaseloadId()),
				columnRowMapper);
		return returnObj;
	}

	@Override
	public OffenderDeductionReceipts offDrValidateRecieptTxnType(final OffenderDeductionReceipts returnBean) {
		final OffenderDeductionReceipts returnObj;
		final String sql = getQuery("OTDOALLO_VALIDATE_RECEIPT_TXN_TYPE");
		final RowMapper<OffenderDeductionReceipts> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderDeductionReceipts.class, offenderDeductionsMapping);
		returnObj = namedParameterJdbcTemplate.queryForObject(sql,
				createParams("deductionType", returnBean.getDeductionType(), "caseLoadId", returnBean.getCaseloadId(),
						"receiptTxnType", returnBean.getReceiptTxnType()),
				columnRowMapper);
		return returnObj;
	}
	
	@Override
	public List<OffenderDeductionReceipts> offDedFindPostInsert(final OffenderDeductions returnBean) {
		List<OffenderDeductionReceipts> returnList;
		final String sql = getQuery("OTDOALLO_OFF_DED_FIND_POST_INSERT");
		final RowMapper<OffenderDeductionReceipts> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderDeductionReceipts.class, offenderDeductionsMapping);
		returnList = (ArrayList<OffenderDeductionReceipts>) namedParameterJdbcTemplate
				.query(sql,createParams("deductionType", returnBean.getDeductionType(), "caseLoadId", returnBean.getCaseloadId()),
						columnRowMapper);
		return returnList;
	}

	@Override
	public Long offDedPreInsert() {
		final String sql = getQuery("OTDOALLO_GETMAX_ID_CUR");
		Long returnList;
		returnList = namedParameterJdbcTemplate.queryForObject(sql, createParams(),
				Long.class);
		return returnList;
	}

	@Override
	public String offDedFindInformationNumber() {
		final String sql = getQuery("OTDOALLO_FIND_INFORMATIONNUMBER");
		String returnList;
		returnList = namedParameterJdbcTemplate.queryForObject(sql, createParams(),
				String.class);
		return returnList;
	}

	@Override
	public Integer offDedFindInCount(final OffenderDeductions offDeducBean) {
		final String sql = getQuery("OTDOALLO_FIND_IN_COUNT");
		Integer returnList;
		returnList = namedParameterJdbcTemplate.queryForObject(sql, createParams("offenderId",offDeducBean.getOffenderId(),"deductionType",offDeducBean.getDeductionType(),
				"informationNum",offDeducBean.getInformationNumber()),
				Integer.class);
		return returnList;
	}
	
	@Override
	public List<String> offDrKeyDelrec(final String caseloadId, final Long offenderId, final String deductionType) {
		final String sql = getQuery("OTDOALLO_OFF_DR_KEY_DELREC");
		List<String> returnList = new ArrayList<>();
		try {
			returnList = namedParameterJdbcTemplate.queryForList(sql, createParams("caseloadId", caseloadId, "offenderId", offenderId, "deductionType", deductionType ), String.class);
		} catch(Exception e) {
			
		}
		return returnList;
	}

	@Override
	public BigDecimal cntDedRcpt(BigDecimal offenderDeductionId) {
		final String sql = getQuery("OTDOALLO_CNT_DED_RCPT");
		BigDecimal returnData = BigDecimal.ZERO;
		try {
			returnData = namedParameterJdbcTemplate.queryForObject(sql, createParams("offenderDeductionId", offenderDeductionId), BigDecimal.class);
		}catch (Exception e) {
			// TODO: handle exception
		}
		return returnData;
	}

}

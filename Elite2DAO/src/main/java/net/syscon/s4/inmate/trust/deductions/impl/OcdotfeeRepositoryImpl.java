package net.syscon.s4.inmate.trust.deductions.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlInOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.beans.TransactionTypes;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.OffenderDeductionReceipts;
import net.syscon.s4.im.beans.SysDual;
import net.syscon.s4.inmate.beans.DeductionTypes;
import net.syscon.s4.inmate.beans.OffenderDeductions;
import net.syscon.s4.inmate.trust.deductions.OcdotfeeRepository;
import oracle.jdbc.OracleTypes;

@Repository
public class OcdotfeeRepositoryImpl extends RepositoryBase implements OcdotfeeRepository {

	
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OcdotfeeRepositoryImpl.class.getName());


	private final Map<String, FieldMapper> transactionTypesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DESCRIPTION", 			new FieldMapper("description"))
			.put("RECEIPT_TXN_TYPE", 		new FieldMapper("receiptTxnType")).build();
	private final Map<String, FieldMapper> referenceCodesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CODE", 					new FieldMapper("code"))
			.put("DESCRIPTION", 			new FieldMapper("description"))
			.put("DSP_DESCRIPTION", 		new FieldMapper("dspDescription"))
			.put("MODE", 					new FieldMapper("mode"))
			.put("DEDUCTION_STATUS", 		new FieldMapper("deductionStatus")).build();
	private final Map<String, FieldMapper> offenderDeductionsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("ADJUSTMENT_USER_ID", 		new FieldMapper("adjustmentUserId"))
			.put("GROUP_ID", 				new FieldMapper("groupId"))
			.put("PROCESS_PRIORITY_NUMBER", new FieldMapper("processPriorityNumber"))
			.put("PAY_DEDUCTION_FLAG", 		new FieldMapper("payDeductionFlag"))
			.put("DEDUCTION_STATUS", 		new FieldMapper("deductionStatus"))
			.put("COLLECT_SENT_DATE", 		new FieldMapper("collectSentDate"))
			.put("MAX_RECURSIVE_AMOUNT", 	new FieldMapper("maxRecursiveAmount"))
			.put("JS_STATUS", 				new FieldMapper("jsStatus"))
			.put("OFFENDER_ID", 			new FieldMapper("offenderId"))
			.put("FIFO_FLAG", 				new FieldMapper("fifoFlag"))
			.put("ADJUSTMENT_AMOUNT", 		new FieldMapper("adjustmentAmount"))
			.put("DEDUCTION_PRIORITY", 		new FieldMapper("deductionPriority"))
			.put("SEAL_FLAG", 				new FieldMapper("sealFlag"))
			.put("CREATE_DATETIME",			new FieldMapper("createDateTime"))
			.put("EFFECTIVE_DATE", 			new FieldMapper("effectiveDate"))
			.put("DEDUCTION_AMOUNT", 		new FieldMapper("deductionAmount"))
			.put("MODIFY_DATETIME", 		new FieldMapper("modifyDateTime"))
			.put("PAYEE_PERSON_ID", 		new FieldMapper("payeePersonId"))
			.put("ADJUSTMENT_TEXT", 		new FieldMapper("adjustmentText"))
			.put("COLLECT_AGENCY_FLAG", 	new FieldMapper("collectAgencyFlag"))
			.put("COMMENT_TEXT", 			new FieldMapper("commentText"))
			.put("ADJUSTMENT_REASON_CODE",  new FieldMapper("adjustmentReasonCode"))
			.put("CREATE_USER_ID", 			new FieldMapper("createUserId"))
			.put("OFFENDER_DEDUCTION_ID",   new FieldMapper("offenderDeductionId"))
			.put("OFFENDER_PAYMENT_PROFILE_ID", new FieldMapper("offenderPaymentProfileId"))
			.put("MAX_TOTAL_AMOUNT",        new FieldMapper("maxTotalAmount"))
			.put("MODIFY_USER_ID", 			new FieldMapper("modifyUserId"))
			.put("INFORMATION_NUMBER", 		new FieldMapper("informationNumber"))
			.put("ADJUSTMENT_TXN_ID", 		new FieldMapper("adjustmentTxnId"))
			.put("CASE_ID", 				new FieldMapper("caseId"))
			.put("COLLECT_AGENCY_AMOUNT",   new FieldMapper("collectAgencyAmount"))
			.put("CREDIT_LIMIT", 			new FieldMapper("creditLimit"))
			.put("DEDUCTION_PERCENTAGE", 	new FieldMapper("deductionPercentage"))
			.put("CASELOAD_ID", 			new FieldMapper("caseloadId"))
			.put("MAX_MONTHLY_AMOUNT", 		new FieldMapper("maxMonthlyAmount"))
			.put("MODIFY_DATE", 			new FieldMapper("modifyDate"))
			.put("DEDUCTION_TYPE", 			new FieldMapper("deductionType"))
			.put("PARENT_DEDUCTION_ID", 	new FieldMapper("parentDeductionId"))
			.put("PAYEE_CORPORATE_ID", 		new FieldMapper("payeeCorporateId"))
			.build();
	private final Map<String, FieldMapper> deductionTypesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DEDUCTION_TYPE", 			new FieldMapper("deductionType"))
			.put("DEDUCTION_DESC", 			new FieldMapper("deductionDesc"))
			.put("CASELOAD_ID", 			new FieldMapper("caseloadId"))
			.build();
	private final Map<String, FieldMapper> offenderDeductionReceiptsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CREATE_USER_ID", 			new FieldMapper("createUserId"))
			.put("OFFENDER_DEDUCTION_ID", 	new FieldMapper("offenderDeductionId"))
			.put("SEAL_FLAG", 				new FieldMapper("sealFlag"))
			.put("CREATE_DATETIME", 		new FieldMapper("createDatetime"))
			.put("MODIFY_USER_ID", 			new FieldMapper("modifyUserId"))
			.put("LIST_SEQ", 				new FieldMapper("listSeq"))
			.put("RECEIPT_PERCENTAGE",		new FieldMapper("receiptPercentage"))
			.put("FLAT_RATE", 				new FieldMapper("flatRate"))
			.put("MODIFY_DATETIME", 		new FieldMapper("modifyDateTime"))
			.put("RECEIPT_TXN_TYPE", 		new FieldMapper("receiptTxnType")).build();
	private final Map<String, FieldMapper> systemProfilesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("PROFILE_CODE", 			new FieldMapper("profileCode"))
			.put("PROFILE_TYPE", 			new FieldMapper("profileType"))
			.put("CREATE_USER_ID", 			new FieldMapper("createUserId"))
			.put("SEAL_FLAG", 				new FieldMapper("sealFlag"))
			.put("CREATE_DATETIME", 		new FieldMapper("createDateTime"))
			.put("MODIFY_USER_ID", 			new FieldMapper("modifyUserId"))
			.put("OLD_TABLE_NAME", 			new FieldMapper("oldTableName"))
			.put("PROFILE_VALUE", 			new FieldMapper("profileValue"))
			.put("MODIFY_DATETIME", 		new FieldMapper("modifyDatetime"))
			.put("PROFILE_VALUE_2", 		new FieldMapper("profileValue2"))
			.put("DESCRIPTION", 			new FieldMapper("description"))
			.build();
	private final Map<String, FieldMapper> mMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DEDUCTION_TYPE", 			new FieldMapper("deductionType"))
			.put("DESCRIPTION", 			new FieldMapper("description"))
			.put("TXN_TYPE", 				new FieldMapper("txnType"))
			.put("DEDUCTION_DESC", 				new FieldMapper("deductionDesc"))
			.build();
	
	/**
	 * Creates new OcdotfeeRepositoryImpl class Object
	 */
	public OcdotfeeRepositoryImpl() {
		// OcdotfeeRepositoryImpl
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
	public List<OffenderDeductions> offDedExecuteQuery(OffenderDeductions objSearchDao)  {
		final String sql = getQuery("OCDOTFEE_OFFDED_FIND_OFFENDER_DEDUCTIONS");
		final RowMapper<OffenderDeductions> OffenderDeductionsRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderDeductions.class, offenderDeductionsMapping);
		List<OffenderDeductions> returnList = new ArrayList<OffenderDeductions>();
		try {
		returnList =  namedParameterJdbcTemplate.query(sql, createParams("caseloadId",objSearchDao.getCaseloadId(),"offenderId",objSearchDao.getOffenderId()
				,"caseloadType",objSearchDao.getCaseloadType()), OffenderDeductionsRowMapper);
		} catch (Exception e) {
			logger.error(this.getClass().getName()+"offDedExecuteQuery",e);
		}
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
	public Integer offDedInsertOffenderDeductions(final List<OffenderDeductions> lstOffenderDeductions)
			 {
		String sql = getQuery("OCDOTFEE_OFFDED_INSERT_OFFENDER_DEDUCTIONS");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (OffenderDeductions offenderDeductions : lstOffenderDeductions) {
			parameters.add(new BeanPropertySqlParameterSource(offenderDeductions));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error(this.getClass().getName()+"Error in method offDedInsertOffenderDeductions",e);
		}
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
	public Integer offDedUpdateOffenderDeductions(final List<OffenderDeductions> lstOffenderDeductions)
			 {
		String sql = getQuery("OCDOTFEE_OFFDED_UPDATE_OFFENDER_DEDUCTIONS");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (OffenderDeductions offenderDeductions : lstOffenderDeductions) {
			parameters.add(new BeanPropertySqlParameterSource(offenderDeductions));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error(this.getClass().getName()+"Error in method offDedUpdateOffenderDeductions",e);
		}
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
	public Integer offDedDeleteOffenderDeductions(final List<OffenderDeductions> lstOffenderDeductions)
			 {
		String sql = getQuery("OCDOTFEE_OFFDED_DELETE_OFFENDER_DEDUCTIONS");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (OffenderDeductions offenderDeductions : lstOffenderDeductions) {
			parameters.add(new BeanPropertySqlParameterSource(offenderDeductions));
		}
		try {
			String tableName = "OFFENDER_DEDUCTIONS";
			String whereClause = "OFFENDER_DEDUCTION_ID  = :offenderDeductionId";
			batchUpdatePreDeletedRows(tableName, whereClause , parameters);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method offDedDeleteOffenderDeductions", e);
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error(this.getClass().getName()+"Error in method offDedDeleteOffenderDeductions",e);
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
	 *            OffenderDeductionReceipts
	 *
	 * @return List<OffenderDeductionReceipts>
	 *
	 * 
	 */
	public List<OffenderDeductionReceipts> offDrExecuteQuery(OffenderDeductionReceipts objSearchDao) {
		final String sql = getQuery("OCDOTFEE_OFFDR_FIND_OFFENDER_DEDUCTION_RECEIPTS");
		final RowMapper<OffenderDeductionReceipts> OffenderDeductionReceiptsRowMapper = Row2BeanRowMapper
				.makeMapping(sql, OffenderDeductionReceipts.class, offenderDeductionReceiptsMapping);
		List<OffenderDeductionReceipts> returnList = new ArrayList<OffenderDeductionReceipts>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams("offenderDeductionId", objSearchDao.getOffenderDeductionId()), OffenderDeductionReceiptsRowMapper);
		} catch (Exception e) {
			logger.error(this.getClass().getName()+"Error in method offDrExecuteQuery", e);

		}

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
			final List<OffenderDeductionReceipts> lstOffenderDeductionReceipts)  {
		String sql = getQuery("OCDOTFEE_OFFDR_INSERT_OFFENDER_DEDUCTION_RECEIPTS");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (OffenderDeductionReceipts offenderDeductionReceipts : lstOffenderDeductionReceipts) {
			parameters.add(new BeanPropertySqlParameterSource(offenderDeductionReceipts));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error(this.getClass().getName()+"Exception : offDrInsertOffenderDeductionReceipts : ", e);
		}
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
			final List<OffenderDeductionReceipts> lstOffenderDeductionReceipts)  {
		String sql = getQuery("OCDOTFEE_OFFDR_UPDATE_OFFENDER_DEDUCTION_RECEIPTS");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (OffenderDeductionReceipts offenderDeductionReceipts : lstOffenderDeductionReceipts) {
			parameters.add(new BeanPropertySqlParameterSource(offenderDeductionReceipts));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
		logger.error(this.getClass().getName()+"Exception : offDrUpdateOffenderDeductionReceipts : ", e);
		}
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
			final List<OffenderDeductionReceipts> lstOffenderDeductionReceipts)  {
		String sql = getQuery("OCDOTFEE_OFFDR_DELETE_OFFENDER_DEDUCTION_RECEIPTS");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (OffenderDeductionReceipts offenderDeductionReceipts : lstOffenderDeductionReceipts) {
			parameters.add(new BeanPropertySqlParameterSource(offenderDeductionReceipts));
		}
		try {
			String tableName = "OFFENDER_DEDUCTION_RECEIPTS";
			String whereClause = "RECEIPT_TXN_TYPE = :receiptTxnType AND OFFENDER_DEDUCTION_ID = :offenderDeductionId";
			batchUpdatePreDeletedRows(tableName, whereClause , parameters);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method offDrDeleteOffenderDeductionReceipts", e);
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error(this.getClass().getName()+"Exception : offDrDeleteOffenderDeductionReceipts : ", e);
		}
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
	public List<SystemProfiles> sysPflExecuteQuery(SystemProfiles objSearchDao)  {
		final String sql = getQuery("OCDOTFEE_SYSPFL_FIND_SYSTEM_PROFILES");
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
	public List<ReferenceCodes> cgfkOffDedDspDescriptionRecordGroup()  {
		final String sql = getQuery("OCDOTFEE_FIND_CGFKOFFDEDDSPDESCRIPTION");
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
	public List<DeductionTypes> cgfkOffDedDeductionTypeRecordGroup(final String caseloadId)  {
		final String sql = getQuery("OCDOTFEE_FIND_CGFKOFFDEDDEDUCTIONTYPE");
		final RowMapper<DeductionTypes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, DeductionTypes.class, mMapping);
		List<DeductionTypes> returnList = new ArrayList<DeductionTypes>();
		try {
			returnList= namedParameterJdbcTemplate.query(sql, createParams("caseloadId",caseloadId), mRowMapper);
		} catch (Exception e) {
			logger.error(this.getClass().getName()+"Error in method cgfkOffDedDeductionTypeRecordGroup",e);
		}
		return returnList;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<TransactionTypes> cgfkOffDrReceiptTxnTypeRecordGroup()  {
		final String sql = getQuery("OCDOTFEE_FIND_CGFKOFFDRRECEIPTTXNTYPE");
		final RowMapper<TransactionTypes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, TransactionTypes.class,
				mMapping);
		List<TransactionTypes> returnList = new ArrayList<TransactionTypes>();

		try {
			returnList= namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (Exception e) {
			logger.error(this.getClass().getName()+"Error in method cgfkOffDrReceiptTxnTypeRecordGroup",e);
		}
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfkchkOffDedOffDedRef
	 *
	 * @param params
	 *
	 */
	public ReferenceCodes cgfkchkOffDedOffDedRef(ReferenceCodes paramBean)  {
		final String sql = getQuery("OCDOTFEE_CGFKCHK_OFF_DED_OFF_DED_REF_C");
		final RowMapper<ReferenceCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				referenceCodesMapping);
		ReferenceCodes returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfklkpOffDedOffDedRef
	 *
	 * @param params
	 *
	 */
	public ReferenceCodes cgfklkpOffDedOffDedRef(ReferenceCodes paramBean)  {
		final String sql = getQuery("OCDOTFEE_CGFKLKP_OFF_DED_OFF_DED_REF_C");
		final RowMapper<ReferenceCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				referenceCodesMapping);
		ReferenceCodes returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
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
	public DeductionTypes cgfkchkOffDedOffDedDed(DeductionTypes paramBean)  {
		final String sql = getQuery("OCDOTFEE_CGFKCHK_OFF_DED_OFF_DED_DED_T");
		final RowMapper<DeductionTypes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, DeductionTypes.class,
				deductionTypesMapping);
		DeductionTypes returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
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
	public TransactionTypes cgfkchkOffDrOffDrTxnTyp(TransactionTypes paramBean)  {
		final String sql = getQuery("OCDOTFEE_CGFKCHK_OFF_DR_OFF_DR_TXN_TYP");
		final RowMapper<TransactionTypes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, TransactionTypes.class,
				transactionTypesMapping);
		TransactionTypes returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgwhenNewFormInstance
	 *
	 * @param params
	 *
	 */
	public List<SysDual> cgwhenNewFormInstance(SysDual paramBean)  {
		final String sql = getQuery("OCDOTFEE_CGWHEN_NEW_FORM_INSTANCE");
		final RowMapper<SysDual> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, SysDual.class, mMapping);
		final ArrayList<SysDual> returnList = (ArrayList<SysDual>) namedParameterJdbcTemplate.query(sql, createParams(),
				columnRowMapper);
		return returnList;
	}

	public String getDedTypeDesc(final String deductionType,final String caseloadId) {
		final String sql = getQuery("OCDOTFEE_CGFKCHK_OFF_DED_OFF_DED_DED_T");
		String desc=null;
		try {
			desc= namedParameterJdbcTemplate.queryForObject(sql, createParams("deductionType",deductionType,"caseloadId",caseloadId), 
					String.class);
		} catch (Exception e) {
			logger.error(this.getClass().getName()+"getDedTypeDesc",e);
		}
		
		return desc;
	}
	public String getDedStatusDesc(final String deductionStatus) {
		final String sql = getQuery("OCDOTFEE_CGFKCHK_OFF_DED_OFF_DED_REF_C");
		String desc=null;
		try {
			desc= namedParameterJdbcTemplate.queryForObject(sql, createParams("deductionStatus",deductionStatus), 
					String.class);
		} catch (Exception e) {
			logger.error(this.getClass().getName()+"getDedTypeDesc",e);
		}
		
		return desc;
	}
	
	@Override
	public String checkUniqueConstraint(final OffenderDeductions paramBean) {
		final String sql = getQuery("OCDOTFEE_CHECK_UNIQUE_CONSTRAINT");
		String recordExistsC = null;
		try {
			recordExistsC = namedParameterJdbcTemplate.queryForObject(sql, createParams("caseloadId", paramBean.getCaseloadId(),
					"offenderId", paramBean.getOffenderId(), "deductionType", paramBean.getDeductionType()), String.class);
		}catch (Exception e) {
			logger.error(this.getClass().getName()+"OCDOTFEE_CHECK_UNIQUE_CONSTRAINT ", e);

		}
		return recordExistsC;
	}
	
	public String getCaseloadType(final String caseloadId) {
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		Map<String, Object> returnObject = null;
		SqlParameter[] sqlParameters = new SqlParameter[5];
		String caseloadType = null;
		sqlParameters = new SqlParameter[] { new SqlParameter("CSLD_ID", OracleTypes.VARCHAR),
				new SqlInOutParameter("CSLD_TYPE", OracleTypes.VARCHAR), };
		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("TRUST").withProcedureName("GET_CASELOAD_TYPE").declareParameters(sqlParameters);
		inParamMap.put("CSLD_ID", caseloadId);
		inParamMap.put("CSLD_TYPE", "true");
		final SqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
			returnObject = simpleJDBCCall.execute(inParameter);
			if (returnObject != null) {
				caseloadType = (String) returnObject.get("CSLD_TYPE");
			}
		return caseloadType;

	}
	
	@Override
	public Long recordExistsC(Long offenderDeductionId) {
		final String sql = getQuery("OCDOTFEE_RECORD_EXISTS_C");
		Long lvROffDedId = null;
		try {
			lvROffDedId = namedParameterJdbcTemplate.queryForObject(sql, createParams("offenderDeductionId", offenderDeductionId), Long.class);
		} catch (Exception e) {
			logger.error(this.getClass().getName()+"Error in method recordExistsC", e);
		}
		return lvROffDedId;
	}

	@Override
	public List<OffenderDeductionReceipts> detailsC(String deductionType, String caseloadId, String caseloadType) {
		final String sql = getQuery("OCDOTFEE_DETAILS_C");
		final RowMapper<OffenderDeductionReceipts> OffenderDeductionReceiptsRowMapper = Row2BeanRowMapper
				.makeMapping(sql, OffenderDeductionReceipts.class, offenderDeductionReceiptsMapping);
		List<OffenderDeductionReceipts> returnList = new ArrayList<OffenderDeductionReceipts>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams("deductionType", deductionType, "caseloadId", caseloadId, "caseloadType", caseloadType),
					OffenderDeductionReceiptsRowMapper);
		} catch (Exception e) {
			logger.error(this.getClass().getName()+"Error in method detailsC", e);

		}

		return returnList;
	}

	@Override
	public Long getBeneficiaryId() {
		final String sql = getQuery("OCDOTFEE_BENEFICIARY_ID");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams(), Long.class);
	}

	@Override
	public BigDecimal getPayeeCorporateId(String caseloadId, String deductionType) {
		final String sql = getQuery("OCDOTFEE_GET_PAYEE_CORPORATE_ID");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql, createParams("caseloadId", caseloadId, "deductionType", deductionType), BigDecimal.class);
		} catch (Exception e) {
			logger.error(this.getClass().getName()+"Error in method getPayeeCorporateId", e);
			return null;
		}
		
	}

	@Override
	public BigDecimal getUnknownBenId() {
		final String sql = getQuery("OCDOTFEE_GET_UNKNOWN_BEN_ID");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams(), BigDecimal.class);
	}

	@Override
	public void insertIntoOffenderBeneficiaries(Long beneficiaryId, Long offenderDeductionId, Long offenderId,
			BigDecimal corporateId, BigDecimal unknownbenId,String createUserId) {
		final String sql = getQuery("OCDOTFEE_INSERT_INTO_OFFENDER_BENEFICIARIES");
		try {
			namedParameterJdbcTemplate.update(sql, createParams("beneficiaryId", beneficiaryId, "offenderDeductionId", offenderDeductionId, "offenderId", offenderId,
					"corporateId", corporateId, "unknownBenId", unknownbenId,"createUserId",createUserId));
		} catch (DataAccessException e) {
			logger.error(this.getClass().getName()+"Error in method insertIntoOffenderBeneficiaries", e);
		}
		
		
	}

	@Override
	public Long getDeductionId() {
		final String sql = getQuery("OCDOTFEE_GET_DEDUCTION_ID");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams(), Long.class);
	}

	@Override
	public Integer getInfoSeq(Long offenderId, String deductionType, String informationNumber) {
		final String sql = getQuery("OCDOTFEE_GET_INFO_SEQ");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("offenderId", offenderId, "deductionType", deductionType, "informationNumber", informationNumber), Integer.class);
	}

	@Override
	public void keyDelrec(List<OffenderDeductions> deleteList) {
		String sql = getQuery("OCDOTFEE_OFFDED_DELETE_OFFENDER_BENEFICIARIES");
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (OffenderDeductions offenderDeductions : deleteList) {
			parameters.add(new BeanPropertySqlParameterSource(offenderDeductions));
		}
		try {
			String tableName = "offender_beneficiaries";
			String whereClause = "offender_deduction_id = :offenderDeductionId";
			batchUpdatePreDeletedRows(tableName, whereClause , parameters);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method keyDelrec", e);
		}
		try {
			namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error(this.getClass().getName()+"Error in method keyDelrec", e);
		}
		
		
	}
	@Override
	public List<OffenderDeductionReceipts> getPercentageAndFlatRate(final String deductionType, final String caseloadId, final String receiptTxnType) {
		final String sql = getQuery("OCDOTFEE_GET_PERCENTAGE_AND_FLAT_RATE");
		final RowMapper<OffenderDeductionReceipts> OffenderDeductionReceiptsRowMapper = Row2BeanRowMapper
				.makeMapping(sql, OffenderDeductionReceipts.class, offenderDeductionReceiptsMapping);
		List<OffenderDeductionReceipts> returnList = new ArrayList<OffenderDeductionReceipts>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams("deductionType", deductionType, "caseloadId", caseloadId, "receiptTxnType", receiptTxnType),
					OffenderDeductionReceiptsRowMapper);
		} catch (Exception e) {
			logger.error(this.getClass().getName()+"Error in method OCDOTFEE_GET_PERCENTAGE_AND_FLAT_RATE", e);

		}

		return returnList;
	}
	
	@Override
	public OffenderDeductions offenderOldData(Long offenderDeductionId) {
		final String sql = getQuery("OCDOTFEE_OFFENDER_OLD_DATA");
		// List<OffenderDeductions> returnData = new ArrayList<>();
		OffenderDeductions returnData = new OffenderDeductions();
		final RowMapper<OffenderDeductions> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderDeductions.class, offenderDeductionsMapping);
		try {
			returnData = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("offender_deduction_id", offenderDeductionId), new BeanPropertyRowMapper<OffenderDeductions>(OffenderDeductions.class));
		} catch (Exception e) {
			logger.error(this.getClass().getName()+"Error in method OCDOTFEE_OFFENDER_OLD_DATA", e);

		}
		return returnData;
	}

}

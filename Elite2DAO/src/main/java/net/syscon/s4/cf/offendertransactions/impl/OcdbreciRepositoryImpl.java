package net.syscon.s4.cf.offendertransactions.impl;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlInOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.cf.deductions.beans.FeeAccountProfiles;
import net.syscon.s4.cf.deductions.beans.OffFeeBillTransactions;
import net.syscon.s4.cf.offendertransactions.OcdbreciRepository;
import net.syscon.s4.cf.statements.beans.ocdbreciReportsBean;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.beans.TransactionTypes;
import net.syscon.s4.common.beans.VHeaderBlock;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.OffenderTransactions;
import net.syscon.s4.im.beans.VTrustHeader;
import net.syscon.s4.inmate.beans.OffenderBeneficiaries;
import net.syscon.s4.inmate.beans.OffenderDeductions;
import oracle.jdbc.internal.OracleTypes;

/**
 * Class OcdbreciRepositoryImpl
 */
@Repository
public class OcdbreciRepositoryImpl extends RepositoryBase implements OcdbreciRepository {

	private static Logger logger = LogManager.getLogger(OcdbreciRepositoryImpl.class.getName());

	private final Map<String, FieldMapper> transactionTypesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("LIST_SEQ", new FieldMapper("listSeq")).put("DESCRIPTION", new FieldMapper("description"))
			.put("CASELOAD_TYPE", new FieldMapper("caseloadType")).put("CASELOAD_ID", new FieldMapper("caseloadId"))
			.put("TXN_TYPE", new FieldMapper("txnType")).put("LAST_NAME", new FieldMapper("lastName"))
			.put("FIRST_NAME", new FieldMapper("firstName")).put("MIDDLE_NAME", new FieldMapper("middleName"))
			.put("RECEIVED_AMOUNT", new FieldMapper("receiveAmount")).put("AMOUNT", new FieldMapper("amount"))
			.put("OFFENDER_ID", new FieldMapper("offenderId")).put("DED_ID", new FieldMapper("offenderDeductionId"))
			.put("TOT_AMT", new FieldMapper("maxTotalAmount")).put("MON_AMT", new FieldMapper("maxMonthlyAmount"))
			.put("REC_AMT", new FieldMapper("maxRecursiveAmount")).put("DED_TYPE", new FieldMapper("deductionType"))
			.build();
	private final Map<String, FieldMapper> offenderDeductionsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("OFFENDER_ID", new FieldMapper("offenderId"))
			.put("DSP_INFORMATION_NUMBER", new FieldMapper("dspInformationNumber"))
			.put("CASELOAD_TYPE", new FieldMapper("caseloadType")).put("TXN_TYPE", new FieldMapper("txnType"))
			.put("OFFENDER_DEDUCTION_ID", new FieldMapper("offenderDeductionId"))
			.put("INFORMATION_NUMBER", new FieldMapper("informationNumber")).build();
	private final Map<String, FieldMapper> systemProfilesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("PROFILE_VALUE", new FieldMapper("profileValue")).put("DESCRIPTION", new FieldMapper("description"))
			.put("TXN_TYPE", new FieldMapper("txnType")).put("CODE", new FieldMapper("code")).build();
	private final Map<String, FieldMapper> mMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DESCRIPTION", new FieldMapper("description")).put("TXN_TYPE", new FieldMapper("txnType"))
			.put("CODE", new FieldMapper("code")).build();
	private final Map<String, FieldMapper> repBeanMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("FTXNDATE", new FieldMapper("fTxnDate")).put("FRECNO", new FieldMapper("fRecNo"))
			.put("TOTALAMOUNT", new FieldMapper("totalAmount")).put("FOFFNAME", new FieldMapper("fOffName"))
			.put("FOFFID", new FieldMapper("fOffId")).put("FLOCATION", new FieldMapper("fLocation"))
			.put("FTXNDESC", new FieldMapper("fTxnDesc")).put("USERID", new FieldMapper("fUserId"))
			.put("PAYEE", new FieldMapper("payee")).put("TXNID", new FieldMapper("txnId"))
			.put("OFFENDER_ID", new FieldMapper("offenderId")).put("AMT", new FieldMapper("amt"))
			.put("TXNSEQ", new FieldMapper("txnSeq")).put("TXNTYPE", new FieldMapper("txnType")).build();

	private final Map<String, FieldMapper> offFeeMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("FEECODE", new FieldMapper("feeCode")).put("BILL_TXN_AMOUNT", new FieldMapper("balance"))
			.put("BOOKING_NO", new FieldMapper("bookingNo"))
			.put("RECEIPTPRODUCTIONFLAG", new FieldMapper("receiptProductionFlag"))
			.put("CHECKIND", new FieldMapper("checkInd"))
			.build();
	
	private final Map<String, FieldMapper> offFeeBalanceMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("FEECODE", new FieldMapper("feeCode"))
			.put("BOOKING_NO", new FieldMapper("bookingNo"))
			.put("RECEIPTPRODUCTIONFLAG", new FieldMapper("receiptProductionFlag"))
			.put("CHECKIND", new FieldMapper("checkInd"))
			.build();

	/**
	 * Creates new OcdbreciRepositoryImpl class Object
	 */
	public OcdbreciRepositoryImpl() {
		// OcdbreciRepositoryImpl
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao OffenderTransactions
	 *
	 * @return List<OffenderTransactions>
	 */
	public List<OffenderTransactions> offTxn1ExecuteQuery(final OffenderTransactions objSearchDao) {
		final String sql = getQuery("OCDBRECI_OFFTXN1_FIND_OFFENDER_TRANSACTIONS");
		final RowMapper<OffenderTransactions> OffenderTransactionsRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderTransactions.class, transactionTypesMapping);
		List<OffenderTransactions> returnList = new ArrayList<>();
		returnList = namedParameterJdbcTemplate.query(sql, createParams(), OffenderTransactionsRowMapper);
		return returnList;
	}

	/**
	 * This method is used to insert the records in the data base tables based on
	 *
	 * @param lstOffenderTransactions List<OffenderTransactions>
	 *
	 * @return List<Integer>
	 */
	public Integer offTxn1InsertOffenderTransactions(final List<OffenderTransactions> lstOffenderTransactions) {
		String sql = getQuery("OCDBRECI_OFFTXN1_INSERT_OFFENDER_TRANSACTIONS");
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
	 * @param lstOffenderTransactions List<OffenderTransactions>
	 */
	public Integer offTxn1UpdateOffenderTransactions(final List<OffenderTransactions> lstOffenderTransactions) {
		String sql = getQuery("OCDBRECI_OFFTXN1_UPDATE_OFFENDER_TRANSACTIONS");
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
	 * Fetch the records from database table
	 *
	 * @param objSearchDao SystemProfiles
	 *
	 * @return List<SystemProfiles>
	 */
	public List<SystemProfiles> sysPflExecuteQuery(final SystemProfiles objSearchDao) {
		final String sql = getQuery("OCDBRECI_SYSPFL_FIND_SYSTEM_PROFILES");
		final RowMapper<SystemProfiles> SystemProfilesRowMapper = Row2BeanRowMapper.makeMapping(sql,
				SystemProfiles.class, systemProfilesMapping);
		final ArrayList<SystemProfiles> returnList = (ArrayList<SystemProfiles>) namedParameterJdbcTemplate.query(sql,
				createParams(), SystemProfilesRowMapper);
		return returnList;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<TransactionTypes>
	 */
	public List<TransactionTypes> cgfkOffTxn1TxnTypeRecordGroup(String user) {
		final String sql = getQuery("OCDBRECI_FIND_CGFKOFFTXN1_TXNTYPE");
		final RowMapper<TransactionTypes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, TransactionTypes.class,
				mMapping);
		List<TransactionTypes> returnList = new ArrayList<TransactionTypes>();

		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams("USER" ,user ), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error(this.getClass().getName()+" cgfkOffTxn1TxnTypeRecordGroup", e);
			return Collections.emptyList();
		}
		return returnList;
	}

	/**
	 * Used to capture results from select query
	 *
	 * @return List<MMOffenderDeductionShadowsOffenderMonDeductionsCaseloadDeductionProfiles>
	 */
	public List<ReferenceCodes> cgfkOffTxnDspInformationNRecordGroup(final Long offenderId, final String caseloadId,
			final String txnId) {
		final String sql = getQuery("OCDBRECI_FIND_CGFKOFFTXNDSPINFORMATIONN");
		final RowMapper<ReferenceCodes> referenceMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				mMapping);
		try {
			return namedParameterJdbcTemplate.query(sql,
					createParams("OFFENDERID", offenderId, "CASELOADTYPE", caseloadId, "TXNTYPE", txnId),
					referenceMapper);
		} catch (Exception e) {
			logger.error(this.getClass().getName()+" cgfkOffTxnDspInformationNRecordGroup", e);
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 *
	 * @return List<MMOffenderDeductionShadowsOffenderMonDeductionsCaseloadDeductionProfiles>
	 */
	public List<ReferenceCodes> cgfkOffTxnDspInformationNRecordGroupOne(final OffenderTransactions searchBean) {
		final String sql = getQuery("OCDBRECI_FIND_CGFKOFFTXNDSPINFORMATIONN");
		final RowMapper<ReferenceCodes> referenceMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				mMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams("OFFENDERID", searchBean.getOffenderId(),
					"CASELOADTYPE", searchBean.getCaseloadId(), "TXNTYPE", searchBean.getTxnType()), referenceMapper);
		} catch (Exception e) {
			logger.error(this.getClass().getName()+" cgfkOffTxnDspInformationNRecordGroupOne", e);
			return Collections.emptyList();
		}
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfkchkOffTxn1OffTxnTxn
	 *
	 * @param params
	 *
	 */
	public List<TransactionTypes> cgfkchkOffTxn1OffTxnTxn(final TransactionTypes paramBean) {
		final String sql = getQuery("OCDBRECI_CGFKCHK_OFF_TXN1_OFF_TXN_TXN_");
		final RowMapper<TransactionTypes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, TransactionTypes.class,
				transactionTypesMapping);
		final ArrayList<TransactionTypes> returnList = (ArrayList<TransactionTypes>) namedParameterJdbcTemplate
				.query(sql, createParams(), columnRowMapper);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfkchkOffTxnOffTxnOff
	 *
	 * @param params
	 *
	 */
	public Object cgfkchkOffTxnOffTxnOff(final OffenderDeductions paramBean) {
		final String sql = getQuery("OCDBRECI_CGFKCHK_OFF_TXN_OFF_TXN_OFF_D");
		final RowMapper<OffenderDeductions> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderDeductions.class, offenderDeductionsMapping);
		OffenderDeductions returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfklkpOffTxnOffTxnOff
	 *
	 * @param params
	 *
	 */
	public Object cgfklkpOffTxnOffTxnOff(final OffenderDeductions paramBean) {
		final String sql = getQuery("OCDBRECI_CGFKLKP_OFF_TXN_OFF_TXN_OFF_D");
		final RowMapper<OffenderDeductions> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderDeductions.class, offenderDeductionsMapping);
		OffenderDeductions returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * runReport
	 *
	 * @param params
	 *
	 */
	public List<SystemProfiles> runReport(final SystemProfiles paramBean) {
		final String sql = getQuery("OCDBRECI_RUN_REPORT");
		final RowMapper<SystemProfiles> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, SystemProfiles.class,
				systemProfilesMapping);
		final ArrayList<SystemProfiles> returnList = (ArrayList<SystemProfiles>) namedParameterJdbcTemplate.query(sql,
				createParams(), columnRowMapper);
		return returnList;
	}

	public VTrustHeader getOffenderIdDetails(final OffenderTransactions paramBean) {
		final String sql = getQuery("GET_OFFENDER_ID_DATA");
		final RowMapper<VTrustHeader> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, VTrustHeader.class,
				systemProfilesMapping);
		VTrustHeader returnList = new VTrustHeader();
		try {
			returnList = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("OFFIDDISPLAY", paramBean.getOffenderIdDisplay(), "CSLDID", paramBean.getCaseloadId()),
					columnRowMapper);
		} catch (Exception e) {
			logger.error(this.getClass().getName()+" getOffenderIdDetails", e);
			return returnList;
		}
		return returnList;
	}

	public Long countOffenderIdDet(final Long rootOffenderId) {
		final String sql = getQuery("GET_P_OFFENDER_ID_COUNT");
		Long returnObj = null;
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams("OFFENDERID", rootOffenderId),
					Long.class);
		} catch (Exception e) {
			logger.error(this.getClass().getName()+" countOffenderIdDet", e);
			return returnObj;
		}
		return returnObj;
	}

	public String getProfileValue() {
		final String sql = getQuery("GET_PROFILE_VALUE");
		String returnObj = "N";
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), String.class);
		} catch (Exception e) {
			logger.error(this.getClass().getName()+" getProfileValue", e);
			return returnObj;
		}
		return returnObj;
	}

	public Long getReferenceCodesValid(final String rootOffenderId) {
		final String sql = getQuery("GET_REFERENCE_CODES_VALID");
		Long returnObj = null;
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams("PPAYMENTMETHOD", rootOffenderId),
					Long.class);
		} catch (Exception e) {
			logger.error(this.getClass().getName()+" getReferenceCodesValid", e);
			return returnObj;
		}
		return returnObj;
	}

	@Override
	public String chkAccountStatus(final String caseloadId, final Long offenderId) {
		SqlParameter[] sqlParameters = new SqlParameter[] { new SqlParameter("P_CSLD_ID", OracleTypes.VARCHAR),
				new SqlParameter("P_OFFENDER_ID", OracleTypes.NUMBER) };
		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("TRUST").withProcedureName("CHK_ACCOUNT_STATUS").declareParameters(sqlParameters);
		Map<String, Object> inParamMap = new HashMap<String, Object>();
		inParamMap.put("P_CSLD_ID", caseloadId);
		inParamMap.put("P_OFFENDER_ID", offenderId);

		final MapSqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		final Map<String, Object> returnObject = simpleJDBCCall.execute(inParameter);

		if (returnObject.get("P_OPEN_AN_ACCOUNT") != null) {
			return returnObject.get("P_OPEN_AN_ACCOUNT").toString();
		}
		return "";
	}

	public String chkOffenderDeductions(final String caseloadId, final Long offfenderId, final String txnType) {
		SqlParameter[] sqlParameters = new SqlParameter[] { new SqlParameter("P_CSLD_ID", OracleTypes.VARCHAR),
				new SqlParameter("P_OFF_ID", OracleTypes.NUMBER), new SqlParameter("P_TRANS_TYPE", OracleTypes.VARCHAR),
				new SqlParameter("P_SHADOW_ID", OracleTypes.NUMBER),
				new SqlInOutParameter("P_DED_FLAG", OracleTypes.VARCHAR), };
		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("DEDUCTIONS").withProcedureName("CHK_OFFENDER_DEDUCTIONS")
				.declareParameters(sqlParameters);
		Map<String, Object> inParamMap = new HashMap<String, Object>();
		inParamMap.put("P_CSLD_ID", caseloadId);
		inParamMap.put("P_OFF_ID", offfenderId);
		inParamMap.put("P_TRANS_TYPE", txnType);
		inParamMap.put("P_SHADOW_ID", 0);
		inParamMap.put("P_DED_FLAG", "N");
		final MapSqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		final Map<String, Object> returnObject = simpleJDBCCall.execute(inParameter);
		if (returnObject.get("P_DED_FLAG") != null) {
			return returnObject.get("P_DED_FLAG").toString();
		}

		return "";
	}

	public List<OffenderDeductions> checkOffenderDeductionId(final Long rootOffenderId) {
		final String sql = getQuery("CHK_OFFENDER_DEDUCTION_ID");
		final RowMapper<OffenderDeductions> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderDeductions.class, offenderDeductionsMapping);
		List<OffenderDeductions> returnObj = namedParameterJdbcTemplate.query(sql,
				createParams("ROOTOFFID", rootOffenderId), columnRowMapper);
		return returnObj;
	}

	public String checkOffenderDeductionReceipts(final Long dedId, final String txnType) {
		final String sql = getQuery("CHK_OFFENDER_DEDUCTION_RECEIPTS");
		String returnObj = "N";
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("TMPOFFDEDID", dedId, "TXNTYPE", txnType), String.class);
		} catch (Exception e) {
			logger.error(this.getClass().getName()+" checkOffenderDeductionReceipts", e);
			return returnObj;
		}
		return returnObj;
	}

	public String chkUniqueObligationFlag(final Long offenderId) {
		final String sql = getQuery("CHK_UNIQUE_OBLIGATION_FLAG");
		String returnObj = "N";
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams("OFFENDERID", offenderId),
					String.class);
		} catch (Exception e) {
			logger.error(this.getClass().getName()+" chkUniqueObligationFlag", e);
			return returnObj;
		}
		return returnObj;
	}

	public String getProfileValuePaymentPlan() {
		final String sql = getQuery("GET_PROFILE_VALUE_PAYMENT_PLAN");
		String returnObj = "N";
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), String.class);
		} catch (Exception e) {
			logger.error(this.getClass().getName()+" getProfileValuePaymentPlan", e);
			return returnObj;
		}
		return returnObj;
	}

	public String chkMissingPayPlan(final Long offenderId, final String infoNumber) {
		final String sql = getQuery("CHK_MISSING_PAYMENT_PLAN");
		String returnObj = "N";
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("OFFENDERID", offenderId, "INFONUM", infoNumber), String.class);
		} catch (Exception e) {
			logger.error(this.getClass().getName()+" chkMissingPayPlan", e);
			return returnObj;
		}
		return returnObj;
	}

	public String checkAccountClosedFlag(final OffenderTransactions offTransactions) {
		final String sql = getQuery("CHK_ACCOUNT_CLOSED_FLAG");
		String returnObj = null;
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams("PCASELOADID",
					offTransactions.getCaseloadId(), "POFFENDERID", offTransactions.getOffenderId()), String.class);
		} catch (Exception e) {
			logger.error(this.getClass().getName()+" checkAccountClosedFlag", e);
			return returnObj;
		}
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 *
	 * @return Integer
	 */
	public Integer txnIdNextValData() {
		final String sql = getQuery("OCDBRECI_TXN_ID");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams(), Integer.class);
	}

	public String checkReceiptProductionFlag(final OffenderTransactions offTransactions) {
		final String sql = getQuery("OCDBRECI_RECEIPT_PRODUCTION_FLAG");
		String returnObj = "N";
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("TRANSTYPE", offTransactions.getTxnType(), "CSLDID", offTransactions.getCaseloadId()),
					String.class);
		} catch (Exception e) {
			return returnObj;
		}
		return returnObj;
	}

	/**
	 * This method will update the transaction in the table
	 * 
	 * @param offTrans
	 * @return String
	 */
	public Integer genTrustRcptNmbr(final String seqId) {
		try {
			final String sql = getQuery("OCDBRECI_RECEIPT_NUMBER");
			if (sql != null) {
				final String preparedSql = sql.replace("#SEQ", seqId);
				return namedParameterJdbcTemplate.queryForObject(preparedSql, createParams("seq" ,seqId ), Integer.class);
			}
		} catch (Exception e) {
			logger.error(this.getClass().getName()+" genTrustRcptNmbr", e);
		}
		return null;
	}

	@Override
	public Map<String, Object> getSubActType(final String moduleName, final String txnType, final String caseloadId) {
		String procedureQuery = "{CALL OMS_OWNER.TRUST.GET_SUB_ACT_TYPE(:P_MODULE_NAME, :P_TXN_TYPE, :P_TXN_POST_TYPE, :P_SUB_ACT_TYPE, :CSLD_ID)}";
		try {
			Connection connection = dataSource.getConnection();
			CallableStatement callableStatement = connection.prepareCall(procedureQuery);
			callableStatement.setString("P_MODULE_NAME", moduleName);
			callableStatement.setString("P_TXN_TYPE", txnType);
			callableStatement.registerOutParameter("P_TXN_POST_TYPE", Types.VARCHAR);
			callableStatement.registerOutParameter("P_SUB_ACT_TYPE", Types.VARCHAR);
			callableStatement.setString("CSLD_ID", caseloadId);
			callableStatement.execute();
			Map<String, Object> returnMapData = new HashMap<>();
			returnMapData.put("P_TXN_POST_TYPE", callableStatement.getString("P_TXN_POST_TYPE"));
			returnMapData.put("P_SUB_ACT_TYPE", callableStatement.getString("P_SUB_ACT_TYPE"));
			return returnMapData;
		} catch (Exception e) {
			logger.error(this.getClass().getName()+" getSubActType", e);
		}
		return null;
	}

	public String checkDescriptionTxnType(final OffenderTransactions offTransactions) {
		final String sql = getQuery("OCDBRECI_DESCR_TXN_TYPE");
		String returnObj = "N";
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("TRANSTYPE", offTransactions.getTxnType()), String.class);
		} catch (Exception e) {
			logger.error(this.getClass().getName()+" checkDescriptionTxnType", e);
			return returnObj;
		}
		return returnObj;
	}

	/**
	 * This method is used to insert the records in the data base tables based on
	 *
	 * @param lstOffenderTransactions List<OffenderTransactions>
	 *
	 * @return List<Integer>
	 *
	 * @
	 */
	public Integer insertIntoOffenderTransaction(final OffenderTransactions offTransactions) {
		final String sql = getQuery("OCDBRECI_INSERT_INTO_OFFENDER_TRANSACTIONS");
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("P_trans_number", offTransactions.getTxnId());
		paramMap.put("P_trans_seq", offTransactions.getTxnEntrySeq());
		paramMap.put("P_csld_id", offTransactions.getCaseloadId());
		paramMap.put("P_off_id", offTransactions.getOffenderId());
		paramMap.put("P_off_book_id", offTransactions.getOffenderBookId());
		paramMap.put("P_trans_post_type", offTransactions.getTxnPostingType());
		paramMap.put("P_trans_type", offTransactions.getTxnType());
		paramMap.put("p_trans_desc", offTransactions.getTxnEntryDesc());
		paramMap.put("p_trans_amount", offTransactions.getTxnEntryAmount());
		paramMap.put("P_trans_date", offTransactions.getTxnEntryDate());
		paramMap.put("p_sub_act_type", offTransactions.getSubAccountType());
		paramMap.put("p_slip_print_flag", "N");
		paramMap.put("p_pre_ded_amount", null);
		paramMap.put("p_deduction_flag", "N");
		paramMap.put("p_payee_code", null);
		paramMap.put("p_payee_corp_id", null);
		paramMap.put("p_payee_person_id", null);
		paramMap.put("p_payee_name_text", null);
		paramMap.put("p_deduction_type", null);
		paramMap.put("p_info_number", offTransactions.getInfoNumber());
		paramMap.put("p_remitter_name", null);
		paramMap.put("p_remitter_id", null);
		paramMap.put("p_receipt_number", offTransactions.getReceiptNumber());
		paramMap.put("p_txn_reference_number", offTransactions.getTxnReferenceNumber());
		paramMap.put("create_user_id",offTransactions.getCreateUserId() );
		paramMap.put("modify_user_id",offTransactions.getCreateUserId() );

		Integer returnArray = null;
		returnArray = namedParameterJdbcTemplate.update(sql, paramMap);
		if (returnArray != 0) {
			returnArray = 1;
			return returnArray;
		}
		return returnArray;
	}

	/**
	 * This method will update the transaction in the table
	 * 
	 * @param offTrans
	 * @return Integer
	 * @throws Exception
	 */
	@Override
	public Integer processGlTransNew(final OffenderTransactions offTrans) throws Exception {
		Integer genSeq = 0;

		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		inParamMap.put("P_CSLD_ID", offTrans.getCaseloadId());
		inParamMap.put("P_TRANS_TYPE", offTrans.getTxnType());
		inParamMap.put("P_OPERATION_TYPE", null);
		inParamMap.put("P_TRANS_AMOUNT", offTrans.getTxnEntryAmount());
		inParamMap.put("P_TRANS_NUMBER", offTrans.getTxnId());
		inParamMap.put("P_TRANS_DATE", offTrans.getTxnEntryDate());
		inParamMap.put("P_TRANS_DESC", offTrans.getTxnEntryDesc());
		inParamMap.put("P_TRANS_SEQ", offTrans.getTxnEntrySeq());
		inParamMap.put("P_MODULE_NAME", "OCDBRECI");
		inParamMap.put("P_OFF_ID", offTrans.getOffenderId());
		inParamMap.put("P_OFF_BOOK_ID", offTrans.getOffenderBookId());
		inParamMap.put("P_SUB_ACT_TYPE_DR", null);
		inParamMap.put("P_SUB_ACT_TYPE_CR", offTrans.getSubAccountType());
		inParamMap.put("P_PAYEE_PERS_ID", null);
		inParamMap.put("P_PAYEE_CORP_ID", null);
		inParamMap.put("P_PAYEE_NAME_TEXT", "");
		inParamMap.put("P_GL_SQNC", offTrans.getGlEntrySeq());
		inParamMap.put("P_OFF_DED_ID", null);
		try {
			namedParameterJdbcTemplate.update(
					"call OMS_OWNER.TRUST.PROCESS_GL_TRANS_NEW(:P_CSLD_ID, :P_TRANS_TYPE, :P_OPERATION_TYPE, :P_TRANS_AMOUNT, :P_TRANS_NUMBER, :P_TRANS_DATE, :P_TRANS_DESC,"
							+ " :P_TRANS_SEQ, :P_MODULE_NAME, :P_OFF_ID, :P_OFF_BOOK_ID, :P_SUB_ACT_TYPE_DR, :P_SUB_ACT_TYPE_CR, :P_PAYEE_PERS_ID, :P_PAYEE_CORP_ID, :P_PAYEE_NAME_TEXT,"
							+ " :P_GL_SQNC, :P_OFF_DED_ID)",
					inParamMap);
			genSeq = 2;

		} catch (Exception e) {
			logger.error(this.getClass().getName()+" processGlTransNew", e);
			throw new Exception("PROCESS_GL_TRANS_NEW");
		}
		return genSeq;
	}

	public void updateOffenderBalance(final OffenderTransactions paramBean) {
		SqlParameter[] sqlParameters = new SqlParameter[] { new SqlParameter("p_csld_id", OracleTypes.VARCHAR),
				new SqlParameter("p_off_id", OracleTypes.NUMBER),
				new SqlParameter("p_trans_post_type", OracleTypes.VARCHAR),
				new SqlParameter("p_trans_date", OracleTypes.DATE),
				new SqlParameter("p_trans_number", OracleTypes.NUMBER),
				new SqlParameter("p_trans_type", OracleTypes.VARCHAR),
				new SqlParameter("p_trans_amount", OracleTypes.NUMBER),
				new SqlParameter("p_sub_act_type", OracleTypes.VARCHAR),
				new SqlParameter("p_allow_overdrawn", OracleTypes.VARCHAR), };
		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("TRUST").withProcedureName("UPDATE_OFFENDER_BALANCE").declareParameters(sqlParameters);
		Map<String, Object> inParamMap = new HashMap<String, Object>();
		inParamMap.put("p_csld_id", paramBean.getCaseloadId());
		inParamMap.put("p_off_id", paramBean.getOffenderId());
		inParamMap.put("p_trans_post_type", paramBean.getTxnPostingType());
		inParamMap.put("p_trans_date", paramBean.getTxnEntryDate());
		inParamMap.put("p_trans_number", paramBean.getTxnId());
		inParamMap.put("p_trans_type", paramBean.getTxnType());
		inParamMap.put("p_trans_amount", paramBean.getTxnEntryAmount());
		inParamMap.put("p_sub_act_type", paramBean.getSubAccountType());
		inParamMap.put("p_allow_overdrawn", "N");
		final MapSqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		simpleJDBCCall.execute(inParameter);
	}

	@Override
	public Integer financialDoDuctionsFinancial(final String caseloadId, final Long nbtOffenderId,
			final Long crOffBookId, final String txnType, final Integer pTxnNum, final Date transDate,
			final String pCrSubAccountType, final String string, final Double txnEntryAmount,
			final Double txnEntryAmount2, final Integer pTxnEntrySeq, final String infoNumber) {
		Integer genSeq = 0;
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		inParamMap.put("P_CSLD_ID", caseloadId);
		inParamMap.put("P_OFF_ID", nbtOffenderId);
		inParamMap.put("P_OFF_BOOK_ID", crOffBookId);
		inParamMap.put("P_TRANS_TYPE", txnType);
		inParamMap.put("P_TRANS_NUMBER", pTxnNum);
		inParamMap.put("P_TRANS_DATE", transDate);
		inParamMap.put("P_SUB_ACT_TYPE", pCrSubAccountType);
		inParamMap.put("P_DED_FLAG", string);
		inParamMap.put("P_RECEIPT_AMOUNT", txnEntryAmount);
		inParamMap.put("P_SHADOW_ID", null);
		inParamMap.put("P_DED_AMOUNT", txnEntryAmount2);
		inParamMap.put("TXN_SEQUENCE", pTxnEntrySeq);
		inParamMap.put("P_INFO_NUMBER", infoNumber);
		inParamMap.put("P_MODULE_NAME", "OCDBRECI");
		try {
			namedParameterJdbcTemplate.update(
					"call OMS_OWNER.FINANCIAL.DO_DEDUCTIONS_FINANCIAL(:P_CSLD_ID, :P_OFF_ID, :P_OFF_BOOK_ID, :P_TRANS_TYPE, "
							+ ":P_TRANS_NUMBER, :P_TRANS_DATE, :P_SUB_ACT_TYPE, :P_DED_FLAG, :P_RECEIPT_AMOUNT, :P_SHADOW_ID, :P_DED_AMOUNT, "
							+ ":TXN_SEQUENCE, :P_INFO_NUMBER, :P_MODULE_NAME)",
					inParamMap);
			genSeq = 1;
		} catch (Exception e) {
			logger.error(this.getClass().getName()+" financialDoDuctionsFinancial :" + e);
			genSeq = 0;
			return genSeq;
		}
		return genSeq;
	}

	public String getAcAndSetIndDate(final OffenderTransactions paramBean) {
		String openAnAccount = null;
		Map<String, Object> inParamMap = new HashMap<String, Object>();
		inParamMap.put("p_csld_id", paramBean.getCaseloadId());
		inParamMap.put("p_off_id", paramBean.getOffenderId());
		try {
			namedParameterJdbcTemplate
					.update("call OMS_OWNER.DEDUCTIONS.GET_AC_AND_SET_IND_DATE(:p_off_id, :p_csld_id)", inParamMap);
			openAnAccount = "GETINDDATE";
		} catch (Exception e) {
			logger.error(this.getClass().getName()+" getAcAndSetIndDate :" + e);
		}
		return openAnAccount;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * runReportrole_cur
	 *
	 * @param params
	 *
	 */
	public Long runReportroleCur() {
		final String sql = getQuery("OCDBRECI_RUN_REPORT_ROLE_CUR");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams(), Long.class);
	}

	public Integer printReceiptsTmpDeletequery(Long sessionId, String modifyUserId) {
		final String sql = getQuery("OCDBRECI_PRINT_RECEIPTS_TMP_DELETEQUERY");
		try {
			String tableName = "print_receipts_tmp";
			String whereClause = "session_id = :p_session_id";
			Map<String , Object> inputMap = new HashMap<String, Object>();
			inputMap.put("p_session_id", sessionId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereClause , inputMap);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method printReceiptsTmpDeletequery", e);
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_session_id", sessionId));
	}

	@Override
	public Integer printReceitsInsertQuery(final List<OffenderTransactions> lstOmsRequests) {
		String sql = getQuery("OCDBRECI_PRINT_RECEIPTS_TMP_INSERT_QUERY");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<>();
		for (final OffenderTransactions obj : lstOmsRequests) {
			parameters.add(new BeanPropertySqlParameterSource(obj));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstOmsRequests.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public SystemProfiles getFclientValue() {
		final String sql = getQuery("OCDBRECI_CF_PROFILE_CLIENT");
		final RowMapper<SystemProfiles> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, SystemProfiles.class,
				systemProfilesMapping);
		return namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
	}

	@Override
	public String getfcaseloadDesc(final String caseloadId) {
		final String sql = getQuery("OCDBRECI_CF_CASELOAD_DESC");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("CASELOAD", caseloadId), String.class);
	}

	@Override
	public List<ocdbreciReportsBean> getReportData(final OffenderTransactions bean) {
		final String sql = getQuery("OCDBRECI_REPORTS_QUERY");
		final RowMapper<ocdbreciReportsBean> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				ocdbreciReportsBean.class, repBeanMapping);
		return namedParameterJdbcTemplate.query(sql, createParams("caseload", bean.getCaseloadId(), "from_module",
				bean.getModuleName(), "session_id", bean.getSessionId()), columnRowMapper);
	}

	public Integer updateOffenderTransactionsrpt(final String moduleName, final Long sessionId , String modifyUserId) {
		final String sql = getQuery("OCDBRECI_UPDATE_OFFENDER_TRANSACTIONS_RPT");
		return namedParameterJdbcTemplate.update(sql, createParams("from_module", moduleName, "session_id", sessionId));
	}

	public Integer printReceiptsTmpDeletequeryRpt(final String moduleName, final Long sessionId, String modifyUserId) {
		final String sql = getQuery("OCDBRECI_PRINT_RECEIPTS_TMP_DELETE_QUERY_RPT");
		try {
			String tableName = "print_receipts_tmp";
			String whereClause = "module_name = 'OCDBRECI' AND session_id = :session_id";
			Map<String , Object> inputMap = new HashMap<String, Object>();
			inputMap.put("session_id", sessionId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereClause , inputMap);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method printReceiptsTmpDeletequeryRpt", e);
		}
		return namedParameterJdbcTemplate.update(sql, createParams("session_id", sessionId));
	}

	@Override
	public String getOffenderIdData(final String caseloadId, final BigDecimal offenderId, String userName) {
		final String sql = getQuery("OCDRECEI_GET_OFFENDER_ID_DISPLAY");
		String ofId = null;
		try {
			ofId = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("CASELOAD", caseloadId, "OFFENDERID", offenderId, "USERID", userName), String.class);
		} catch (final Exception e) {
			logger.error("Error in Class " + this.getClass().getName() + " getOffenderIdData error :: ", e);
			return ofId;
		}
		return ofId;
	}


	@Override
	public VHeaderBlock getOffenderNameData(final String caseloadId, final BigDecimal offenderId, String userName) {
		final String sql = getQuery("OCDRECEI_GET_OFFENDER_NAME");
		VHeaderBlock vHeadBlock = null;
		final RowMapper<VHeaderBlock> vHdBlockRowMapper = Row2BeanRowMapper.makeMapping(sql, VHeaderBlock.class,
				transactionTypesMapping);
		try {
			vHeadBlock = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("CASELOAD", caseloadId, "OFFENDERID", offenderId, "USERID", userName),
					vHdBlockRowMapper);
		} catch (final Exception e) {
			logger.error("Error in Class " + this.getClass().getName() + " getOffenderNameData error :: ", e);
			return vHeadBlock;
		}
		return vHeadBlock;
	}


	public BigDecimal getTruncAmount(final BigDecimal amt) {
		final String sql = getQuery("OCDBRECI_GET_AMOUNT");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("AMOUNT", amt), BigDecimal.class);
	}

	public String getAmoutninWords(final BigDecimal amt) {
		final String sql = getQuery("OCDBRECI_AMOUNT_IN_WORDS");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("AMT", amt), String.class);
	}

	@Override
	public BigDecimal getTransfeesAmount(final Integer txnId, final BigDecimal txnSeq, final String txnType) {
		final String sql = getQuery("OCDBRECI_TXNFEE_AMOUNT");
		BigDecimal ofId = BigDecimal.ZERO;
		try {
			ofId = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("TXNID", txnId, "TXNSEQ", txnSeq, "TXNTYPE", txnType), BigDecimal.class);
		} catch (Exception e) {
			return ofId;
		}
		return ofId;
	}

	@Override
	public OffenderBeneficiaries getTotalAmountandOwingAmount(final BigDecimal offenderId) {
		final String sql = getQuery("OCDBRECI_OFFENDER_BENEFICIARIES_OWING_AMOUNT");
		OffenderBeneficiaries vHeadBlock = null;
		final RowMapper<OffenderBeneficiaries> vHdBlockRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderBeneficiaries.class, transactionTypesMapping);
		try {
			vHeadBlock = namedParameterJdbcTemplate.queryForObject(sql, createParams("OFFENDERID", offenderId),
					vHdBlockRowMapper);
		} catch (Exception e) {
			logger.error(this.getClass().getName()+" getTotalAmountandOwingAmount :" + e);
			return vHeadBlock;
		}
		return vHeadBlock;
	}

	@Override
	public BigDecimal getAmountData(final Integer txnId, final BigDecimal txnSeq, final String txnType,
			BigDecimal amount) {
		final String sql = getQuery("OCDBRECI_TXNFEE_AMOUNT");
		BigDecimal ofId = BigDecimal.ZERO;
		if (amount == null) {
			amount = BigDecimal.ZERO;
		}
		try {
			ofId = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("TXNID", txnId, "TXNSEQ", txnSeq, "TXNTYPE", txnType, "CF_TXNFEE", amount),
					BigDecimal.class);
		} catch (Exception e) {
			logger.error(this.getClass().getName()+" getAmountData :" + e);
			return ofId;
		}
		return ofId;
	}

	public List<OffenderDeductions> getReportDataQuery(final BigDecimal offederId) {
		final String sql = getQuery("OCDBRECI_REPORT_DATA");
		final RowMapper<OffenderDeductions> OffenderTransactionsRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderDeductions.class, transactionTypesMapping);
		List<OffenderDeductions> returnList = new ArrayList<>();
		returnList = namedParameterJdbcTemplate.query(sql, createParams("OFFENDERID", offederId),
				OffenderTransactionsRowMapper);
		return returnList;
	}

	@Override
	public String dedFlag(final String dedType) {
		final String sql = getQuery("OCDBRECI_DED_FLAG");
		String ofId = null;
		try {
			ofId = namedParameterJdbcTemplate.queryForObject(sql, createParams("DEDTYPE", dedType), String.class);
		} catch (Exception e) {
			logger.error(this.getClass().getName()+" dedFlag :" + e);
			ofId = "N";
			return ofId;
		}
		return ofId;
	}

	@Override
	public String existFlag(final Long dedId) {
		final String sql = getQuery("OCDBRECI_EXIST_FLAG");
		String ofId = null;
		try {
			ofId = namedParameterJdbcTemplate.queryForObject(sql, createParams("DEDID", dedId), String.class);
		} catch (Exception e) {
			logger.error(this.getClass().getName()+" existFlag :" + e);
			ofId = "N";
			return ofId;
		}
		return ofId;
	}

	@Override
	public BigDecimal monthsBetweenAmount(final Long dedId) {
		final String sql = getQuery("OCDBRECI_MONTHS_BETWEEN");
		BigDecimal ofId = BigDecimal.ZERO;
		try {
			ofId = namedParameterJdbcTemplate.queryForObject(sql, createParams("DEDID", dedId), BigDecimal.class);
		} catch (Exception e) {
			logger.error(this.getClass().getName()+" monthsBetweenAmount :" + e);
			return ofId;
		}
		return ofId;
	}

	@Override
	public Integer updateOffFeeAccountProfileHty(FeeAccountProfiles bean) {
		final String sql = getQuery("OCUACHIS_UPDATE_OFF_FEE_ACCOUNT_PROFILE_HTY");
		Integer count = null;
		try {
			count = namedParameterJdbcTemplate.update(sql, new BeanPropertySqlParameterSource(bean));
		} catch (Exception e) {
			logger.error(this.getClass().getName()+"OffFeeAccountProfileHty", e);
		}
		return count;
	}

	@Override
	public String getProfileValueDisableBtn() {
		final String sql = getQuery("SELECT_PROFILE_VALUE");
		String profileValue = null;
		try {
			profileValue = namedParameterJdbcTemplate.queryForObject(sql, createParams(), String.class);
		} catch (Exception e) {
			logger.error(this.getClass().getName()+" getProfileValueDisableBtn", e);
		}
		return profileValue.toUpperCase();
	}

	@Override
	public List<OffFeeBillTransactions> offFeeExecuteQuery(OffFeeBillTransactions serachBean) {
		final String sql = getQuery("OCDBRECI_OFF_FEE_BILL_TRN_DATA");
		final RowMapper<OffFeeBillTransactions> feeBillTrnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffFeeBillTransactions.class, offFeeMapping);
		return namedParameterJdbcTemplate.query(sql, createParams("OFFENDER_BOOK_ID", serachBean.getOffenderBookId()),
				feeBillTrnRowMapper);
	}

	@Override
	public Date longestSuperVisionDate(Long offenderBookId) {
		final String sql = getQuery("OCDBRECI_LONGEST_SUPER_VISION_DATE");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("OFFENDERBOOKID", offenderBookId),
				Date.class);
	}

	@Override
	public Integer offFeeUpdate(OffFeeBillTransactions offFeeBillTxn) {
		Integer result = 0;
		final String sql = getQuery("OCDBRECI_OFF_FEE_BILL_TRANSACTIONS_UPDATE");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("balance", offFeeBillTxn.getBalance());
		map.put("billId", offFeeBillTxn.getBillId());
		map.put("billTxnNo", offFeeBillTxn.getBillTxnNo());
		try {
			result = namedParameterJdbcTemplate.update(sql, map);
		} catch (Exception e) {
			logger.error(this.getClass().getName()+" offFeeUpdate", e);
			return 0;
		}
		return result;

	}

	@Override
	public Integer getstaffId(String userId) {
		final String sql = getQuery("OCDBRECI_GET_CURRENT_STAFFDETAILS_STAFF_ID");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("USER" ,userId), Integer.class);
	}

	public Integer getBillTranId(final String billId) {
		final String sql = getQuery("OCDBRECI_OFF_FEE_BILLS_TRANSACTIONS_PRE_INSERT");
		Integer id = null;
		try {
			id = namedParameterJdbcTemplate.queryForObject(sql, createParams("BILL_ID", billId), Integer.class);
		} catch (Exception e) {
			logger.error(this.getClass().getName()+" getBillTranId", e);
		}
		return id;
	}

	@Override
	public Integer updateOffenderFees(OffFeeBillTransactions offFeeBillTxn) {
		final String sql = getQuery("OCDBRECI_OFF_BILL_TRN_INSERT");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<>();
		offFeeBillTxn.setBillTxnStaffId(getstaffId(offFeeBillTxn.getCreateUserId()));
		parameters.add(new BeanPropertySqlParameterSource(offFeeBillTxn));
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (returnArray.length == 1) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public String getSubAccountTypeDesc(String txnType) {
		String sql = getQuery("OCDBRECI_GET_SUB_ACCOUNT_DESC");
		String subAccountType=null;
		try {
			subAccountType= namedParameterJdbcTemplate.queryForObject(sql, createParams("txnType", txnType), String.class);
		} catch (Exception e) {
			logger.error(this.getClass().getName()+" getSubAccountTypeDesc", e);
		}
		return subAccountType;
	}

	@Override
	public Integer prepaidAccountTransfer(OffenderTransactions offTrans) throws Exception {
		Integer genSeq = 0;
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		// getting txnEntrySeq
		inParamMap.put("p_trans_number", offTrans.getTxnId());
		inParamMap.put("p_trans_seq", offTrans.getTxnEntrySeq());
		inParamMap.put("p_csld_id", offTrans.getCaseloadId());
		inParamMap.put("p_off_id", offTrans.getOffenderId());
		inParamMap.put("p_off_book_id", offTrans.getOffenderBookId());
		inParamMap.put("p_trans_post_type", offTrans.getTxnPostingType());
		inParamMap.put("p_trans_type", offTrans.getTxnType());
		inParamMap.put("p_trans_desc", offTrans.getTxnEntryDesc());
		inParamMap.put("p_trans_amount", offTrans.getTxnEntryAmount());
		inParamMap.put("p_trans_date", offTrans.getTxnEntryDate());
		inParamMap.put("p_sub_act_type", offTrans.getSubAccountType());
		inParamMap.put("p_deduction_flag", null);
		inParamMap.put("p_pre_ded_amount", null);
		inParamMap.put("p_deduction_type", null);
		inParamMap.put("p_payee_corp_id", null);
		inParamMap.put("p_payee_person_id", null);
		inParamMap.put("p_info_number", offTrans.getInfoNumber());
		inParamMap.put("p_slip_print_flag", "N");
		inParamMap.put("p_allow_overdrawn", "N");
		try {
			namedParameterJdbcTemplate.update(
					" call OMS_OWNER.TRUST.INSERT_INTO_OFFENDER_TRANS(:p_trans_number, :p_trans_seq, :p_csld_id, :p_off_id, :p_off_book_id, :p_trans_post_type, "
							+ " :p_trans_type, :p_trans_desc, :p_trans_amount, :p_trans_date, :p_sub_act_type, :p_deduction_flag, :p_pre_ded_amount, :p_deduction_type, "
							+ " :p_payee_corp_id, :p_payee_person_id, :p_info_number, :p_slip_print_flag, :p_allow_overdrawn)",
					inParamMap);
			genSeq = 1;
		} catch (Exception e) {
			logger.error(this.getClass().getName()+" prepaidAccountTransfer", e);
			throw new Exception("PROCESS_GL_TRANS_NEW");
		}
		return genSeq;
	}

	@Override
	public BigDecimal getCrDeductAcntCode(BigDecimal offenderFeeId) {
		String sql = getQuery("OCDBRECI_CR_DEDUCT_TO");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("offenderFeeId", offenderFeeId),
				BigDecimal.class);
	}

	@Override
	public Integer getDrAccountCode(String txnType, String caseLoadId) {
		String sql = getQuery("OCDBRECI_DR_ACCOUNT_CODE");
		return namedParameterJdbcTemplate.queryForObject(sql,
				createParams("txnType", txnType, "caseloadId", caseLoadId), Integer.class);
	}

	@Override
	public Integer trustInsertGltransNew(OffenderTransactions offTxnObj) {
		Integer genSequence = 0;
		SqlParameter[] sqlParameters = new SqlParameter[] {

				new SqlParameter("p_post_type", OracleTypes.VARCHAR),
				new SqlParameter("p_account_code", OracleTypes.NUMBER),
				new SqlParameter("p_acnt_posting", OracleTypes.VARCHAR),
				new SqlParameter("p_csld_id", OracleTypes.VARCHAR),
				new SqlParameter("p_trans_type", OracleTypes.VARCHAR),
				new SqlParameter("p_trans_amount", OracleTypes.NUMBER),
				new SqlParameter("p_trans_number", OracleTypes.NUMBER),
				new SqlParameter("p_trans_date", OracleTypes.DATE),
				new SqlParameter("p_trans_desc", OracleTypes.VARCHAR),
				new SqlParameter("p_trans_seq", OracleTypes.NUMBER), new SqlParameter("p_gl_sqnc", OracleTypes.NUMBER),
				new SqlParameter("p_off_id", OracleTypes.NUMBER), new SqlParameter("p_off_book_id", OracleTypes.NUMBER),
				new SqlParameter("p_info_number", OracleTypes.VARCHAR),
				new SqlParameter("p_payee_person_id", OracleTypes.NUMBER),
				new SqlParameter("p_payee_corporate_id", OracleTypes.NUMBER),
				new SqlParameter("p_payee_name_text", OracleTypes.VARCHAR),
				new SqlParameter("p_revr_txn_id", OracleTypes.NUMBER),
				new SqlParameter("p_revr_txn_entry_seq", OracleTypes.NUMBER),
				new SqlParameter("p_revr_gl_entry_seq", OracleTypes.NUMBER),
				new SqlParameter("p_txn_ref_number", OracleTypes.VARCHAR),
				new SqlParameter("p_off_ded_id", OracleTypes.NUMBER), };
		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("TRUST").withProcedureName("INSERT_GL_TRANS_NEW").declareParameters(sqlParameters);
		Map<String, Object> inParamMap = new HashMap<String, Object>();
		inParamMap.put("p_post_type", offTxnObj.getTxnPostingType());
		inParamMap.put("p_account_code", offTxnObj.getAccountCode());
		inParamMap.put("p_acnt_posting", offTxnObj.getTxnPostUsage());
		inParamMap.put("p_csld_id", offTxnObj.getCaseloadId());
		inParamMap.put("p_trans_type", "DEDC");
		inParamMap.put("p_trans_amount", offTxnObj.getTxnEntryAmount());
		inParamMap.put("p_trans_number", offTxnObj.getTxnId());
		inParamMap.put("p_trans_date", offTxnObj.getTxnEntryDate());
		inParamMap.put("p_trans_desc", offTxnObj.getTxnEntryDesc());
		inParamMap.put("p_trans_seq", offTxnObj.getTxnEntrySeq());
		inParamMap.put("p_gl_sqnc", offTxnObj.getGlEntrySeq());
		inParamMap.put("p_off_id", offTxnObj.getOffenderId());
		inParamMap.put("p_off_book_id", offTxnObj.getOffenderBookId());
		inParamMap.put("p_info_number", offTxnObj.getInfoNumber());
		inParamMap.put("p_payee_person_id", null);
		inParamMap.put("p_payee_corporate_id", null);
		inParamMap.put("p_payee_name_text", null);
		inParamMap.put("p_revr_txn_id", null);
		inParamMap.put("p_revr_txn_entry_seq", null);
		inParamMap.put("p_revr_gl_entry_seq", null);
		inParamMap.put("p_txn_ref_number", offTxnObj.getTxnReferenceNumber());
		inParamMap.put("p_off_ded_id", null);
		final MapSqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);

		try {
			simpleJDBCCall.execute(inParameter);
			genSequence = 1;
		} catch (Exception e) {
			logger.error(this.getClass().getName()+" trustInsertGltransNew", e);
			genSequence = 0;
			return genSequence;
		}
		return genSequence;
	}

	@Override
	public List<ReferenceCodes> docketRecordGroup(Long offenderBookId) {
		final String sql = getQuery("OCDBRECI_DOCKET_TYPE_RECORD_GROUP");
		final RowMapper<ReferenceCodes> feeBillTrnRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				offFeeMapping);
		return namedParameterJdbcTemplate.query(sql, createParams("OFFENDER_BOOK_ID", offenderBookId),
				feeBillTrnRowMapper);

	}

	@Override
	public Integer cgfkOffTxnDspInformationNRecordGroupCount(Long offenderBookId) {
		final String sql = getQuery("OCDBRECI_DOCKET_TYPE_RECORD_GROUP_COUNT");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("OFFENDER_BOOK_ID", offenderBookId),
				Integer.class);
	}

	public Integer getTxnEntrySeq(Integer txnId, Long offenderId) {
		final String sql = getQuery("OCDBRECI_GET_TXN_ENTRYSEQ");
		Integer enterSeq=null;
		try {
			enterSeq= namedParameterJdbcTemplate.queryForObject(sql, createParams("txnId", txnId, "offenderId", offenderId),Integer.class);
		}catch (Exception e) {
			logger.error(this.getClass().getName()+" getTxnEntrySeq", e);
		}
		return enterSeq;
				
	}

	@Override
	public Integer getGlEntrySeqTxn(Integer txnId, Long offnderId) {
		final String sql = getQuery("OCDBRECI_GET_GL_ENTRY_SEQ_TXN");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("txnId", txnId, "offenderId", offnderId),
				Integer.class);
	}

	@Override
	public OffenderTransactions getProdFlagDetails(final OffenderTransactions searchBean) {
		OffenderTransactions bean=new OffenderTransactions();
		try {
			final String sql = getQuery("OCDBRECI_GET_PROD_FLAG_DETAILS");
			final RowMapper<OffenderTransactions> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
					OffenderTransactions.class, offFeeMapping);
			bean= namedParameterJdbcTemplate.queryForObject(sql,
					createParams("txntype", searchBean.getTxnType(), "csldId", searchBean.getCaseloadId()),
					columnRowMapper);
		} catch (final Exception e) {
			logger.error(this.getClass().getName()+" getProdFlagDetails", e);
		}
		return bean;
	}
	
	@Override
	public List<FeeAccountProfiles> getOffenderFeeIdList(Long offnderBookId, String feeCode) {
		String sql = getQuery("OCDBRECI_OFFENDER_FEE_ID");
		List<FeeAccountProfiles> list = new ArrayList<FeeAccountProfiles>();
		final RowMapper<FeeAccountProfiles> OffenderTransactionsRowMapper = Row2BeanRowMapper.makeMapping(sql,
				FeeAccountProfiles.class, transactionTypesMapping);
		try {
			list = namedParameterJdbcTemplate.query(sql,
					createParams("offenderBookId", offnderBookId, "feeCode", feeCode), OffenderTransactionsRowMapper);
		} catch (final Exception e) {
			logger.error(this.getClass().getName()+" getOffenderFeeIdList", e);
		}
		return list;
	}
	
	@Override
	public List<OffFeeBillTransactions> getFeeBillPrvsCurrentBlnc(Integer txnId, Long offenderBookId) {
		String sql = getQuery("OCDBRECI_GET_FEE_BILL_PREVIOUS_AND_CURRENT_BALANCE");
		List<OffFeeBillTransactions> returnList = new ArrayList<OffFeeBillTransactions>();
		final RowMapper<OffFeeBillTransactions> referenceMapper = Row2BeanRowMapper.makeMapping(sql,
				OffFeeBillTransactions.class, offFeeBalanceMapping);
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams("trustTxnId", txnId,"offenderBookId",offenderBookId), referenceMapper);
		} catch (final Exception e) {
			logger.error("Exception : getFeeBillPrvsCurrentBlnc ", e);
		}
		return returnList;
	}
	
	@Override
	public FeeAccountProfiles getOldDataFeeAccountProfiles (FeeAccountProfiles fap) {
		FeeAccountProfiles bean=new FeeAccountProfiles();
		try {
			final String sql = getQuery("GET_OLD_DATA_OFF_FEE_ACCOUNT_PROFILE");
			final RowMapper<FeeAccountProfiles> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
					FeeAccountProfiles.class, offFeeMapping);
			bean= namedParameterJdbcTemplate.queryForObject(sql,
					createParams("offenderBookId", fap.getOffenderBookId(),  "feeCode" ,fap.getFeeCode() , "caseloadId", fap.getCaseloadId()),
					columnRowMapper);
		} catch (final Exception e) {
			logger.error(this.getClass().getName()+" getProdFlagDetails", e);
		}
		return bean;
	}
}

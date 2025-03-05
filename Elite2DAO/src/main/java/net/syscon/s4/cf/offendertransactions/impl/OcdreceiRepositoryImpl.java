package net.syscon.s4.cf.offendertransactions.impl;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
import net.syscon.s4.cf.deductions.beans.OffFeeBills;
import net.syscon.s4.cf.deductions.beans.offBillingStatements;
import net.syscon.s4.cf.maintenance.beans.FeeAccounts;
import net.syscon.s4.cf.offendertransactions.OcdreceiRepository;
import net.syscon.s4.cf.offendertransactions.beans.PrintReceiptsTmp;
import net.syscon.s4.cf.statements.beans.ocdbreciReportsBean;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.beans.TransactionTypes;
import net.syscon.s4.common.beans.VHeaderBlock;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.CasePlans;
import net.syscon.s4.im.beans.OffenderTransactions;
import net.syscon.s4.im.beans.VAgencyAddresses;
import net.syscon.s4.im.beans.VTrustHeader;
import net.syscon.s4.inmate.beans.OffenderBeneficiaries;
import net.syscon.s4.inmate.beans.OffenderDeductions;
import oracle.jdbc.internal.OracleTypes;

/**
 * Class OcdreceiRepositoryImpl
 */
@Repository
public class OcdreceiRepositoryImpl extends RepositoryBase implements OcdreceiRepository {

	/**
	 * Creates new OcdreceiRepositoryImpl class Object
	 */
	public OcdreceiRepositoryImpl() {
		/*
		 * OcdreceiRepositoryImpl
		 */
	}

	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OcdreceiRepositoryImpl.class);
	private final Map<String, FieldMapper> systemProfilesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("PROFILE_VALUE", new FieldMapper("profileValue")).put("DESCRIPTION", new FieldMapper("description"))
			.put("TXN_TYPE", new FieldMapper("txnType")).put("CODE", new FieldMapper("code")).build();

	private final Map<String, FieldMapper> repBeanMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("FTXNDATE", new FieldMapper("fTxnDate")).put("FRECNO", new FieldMapper("fRecNo"))
			.put("TOTALAMOUNT", new FieldMapper("totalAmount")).put("FOFFNAME", new FieldMapper("fOffName"))
			.put("FOFFID", new FieldMapper("fOffId")).put("FLOCATION", new FieldMapper("fLocation"))
			.put("FTXNDESC", new FieldMapper("fTxnDesc")).put("USERID", new FieldMapper("fUserId"))
			.put("PAYEE", new FieldMapper("payee")).put("TXNID", new FieldMapper("txnId"))
			.put("OFFENDER_ID", new FieldMapper("offenderId")).put("AMT", new FieldMapper("amt"))
			.put("TXNSEQ", new FieldMapper("txnSeq")).put("TXNTYPE", new FieldMapper("txnType")).build();

	private final Map<String, FieldMapper> transactionTypesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("LIST_SEQ", new FieldMapper("listSeq")).put("DESCRIPTION", new FieldMapper("description"))
			.put("DSP_DESCRIPTION", new FieldMapper("dspDescription"))
			.put("CASELOAD_TYPE", new FieldMapper("caseloadType")).put("CASELOAD_ID", new FieldMapper("caseloadId"))
			.put("TXN_TYPE", new FieldMapper("txnType")).build();
	private final Map<String, FieldMapper> offenderDeductionsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("OFFENDER_ID", new FieldMapper("offenderId"))
			.put("DSP_INFORMATION_NUMBER", new FieldMapper("dspInformationNumber"))
			.put("CASELOAD_TYPE", new FieldMapper("caseloadType")).put("TXN_TYPE", new FieldMapper("txnType"))
			.put("INFORMATION_NUMBER", new FieldMapper("informationNumber")).build();
	private final Map<String, FieldMapper> moffenderDeductionShadowsoffenderMonDeductionscaseloadDeductionProfilesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("INFORMATION_NUMBER", new FieldMapper("informationNumber")).build();
	private final Map<String, FieldMapper> mMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DESCRIPTION", new FieldMapper("description")).put("TXN_TYPE", new FieldMapper("txnType"))
			.put("CODE", new FieldMapper("code")).build();
	private final Map<String, FieldMapper> offenderTransactionsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CASELOAD_ID", new FieldMapper("caseloadId")).build();
	private final Map<String, FieldMapper> transactionTypes = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CHECKIND", new FieldMapper("checkInd"))
			.put("RECEIPTPRODUCTIONFLAG", new FieldMapper("receiptProductionFlag")).build();
	private final Map<String, FieldMapper> offFeeMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("FEECODE", new FieldMapper("feeCode"))
			.put("RECEIPTPRODUCTIONFLAG", new FieldMapper("receiptProductionFlag")).build();
	private final Map<String, FieldMapper> vAgyAddrMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("ADDRESS_ID", new FieldMapper("addressId")).put("ADDRESS_TYPE", new FieldMapper("addressType"))
			.put("ADDRESS_TYPE_DESC", new FieldMapper("addressTypeDesc")).put("AGY_LOC_ID", new FieldMapper("agyLocId"))
			.put("START_DATE", new FieldMapper("startDate")).put("END_DATE", new FieldMapper("endDate"))
			.put("ACTIVE_FLAG", new FieldMapper("activeFlag")).put("STREET", new FieldMapper("street"))
			.put("AREA", new FieldMapper("area")).put("COUNTRY", new FieldMapper("country"))
			.put("STREET_NUMBER", new FieldMapper("streetNumber")).put("MAIL_CARE_OF", new FieldMapper("mailCareOf"))
			.put("COUNTRY_CODE", new FieldMapper("countryCode")).put("HOUSE", new FieldMapper("house"))
			.put("STREET_DIRECTION", new FieldMapper("streetDirection"))
			.put("STREET_DIRECTION_DESC", new FieldMapper("streetDirectionDesc"))
			.put("STREET_INFORMATION", new FieldMapper("streetInformation"))
			.put("CITY_CODE", new FieldMapper("cityCode")).put("CITY_NAME", new FieldMapper("cityName"))
			.put("PROV_STATE_CODE", new FieldMapper("provStateCode"))
			.put("PROV_STATE_DESC", new FieldMapper("provStateDesc"))
			.put("ZIP_POSTAL_CODE", new FieldMapper("zipPostalCode"))
			.put("SUITE_NUMBER", new FieldMapper("suiteNumber")).put("COMMENT_TEXT", new FieldMapper("commentText"))
			.put("PRIMARY_FLAG", new FieldMapper("primaryFlag")).put("MAIL_FLAG", new FieldMapper("mailFlag"))
			.put("VALIDATED_FLAG", new FieldMapper("validatedFlag")).build();

	private final Map<String, FieldMapper> casePlansMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CREATION_USER", new FieldMapper("creationUser")).put("AGY_LOC_ID", new FieldMapper("agyLocId"))
			.put("SAC_STAFF_I", new FieldMapper("sacStaffI"))
			.put("AUTO_ASSESS_MODIFY_DATETIME", new FieldMapper("autoAssessModifyDatetime"))
			.put("CAL_AGY_LOC_ID", new FieldMapper("calAgyLocId"))
			.put("PROFILE_VALUE", new FieldMapper(" profileValue "))
			.put("INST_POSITION", new FieldMapper("instPosition")).put("0)", new FieldMapper("0)"))
			.put("INST_SAC_STAFF_I", new FieldMapper("instSacStaffI"))
			.put("CREATION_DATE", new FieldMapper("creationDate"))
			.put("CASE_PLAN_STATUS", new FieldMapper("casePlanStatus")).build();

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao OffenderTransactions
	 *
	 * @return List<OffenderTransactions>
	 *
	 */
	public List<OffenderTransactions> offTxnExecuteQuery(final OffenderTransactions objSearchDao) {
		final String sql = getQuery("OCDRECEI_OFFTXN_FIND_OFFENDER_TRANSACTIONS");
		final RowMapper<OffenderTransactions> offenderTransactionsRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderTransactions.class, offenderTransactionsMapping);
		return namedParameterJdbcTemplate.query(sql, createParams("offenderId", objSearchDao.getRootOffenderId()),
				offenderTransactionsRowMapper);
	}

	/**
	 * Used to capture results from select query
	 *
	 * @return List<ReferenceCodes>
	 */
	public List<ReferenceCodes> cgfkOffTxnDspInformationNRecordGroup(Long offenderBookId) {
		final String sql = getQuery("OCDRECEI_FIND_CGFKOFFTXNDSPINFORMATIONN");
		final RowMapper<ReferenceCodes> referenceMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				mMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams("offenderBookId", offenderBookId),
					referenceMapper);
		} catch (final Exception e) {
			logger.error("Error in Class " + this.getClass().getName() + " cgfkOffTxnDspInformationNRecordGroup error :: ", e);
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<TransactionTypes> cgfkOffTxnTxnTypeRecordGroup(String user) {
		final String sql = getQuery("OCDRECEI_OFFTXNTXNTYPERECORDGROUP");
		final RowMapper<TransactionTypes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, TransactionTypes.class,
				mMapping);
		return namedParameterJdbcTemplate.query(sql, createParams("createUserId",user), mRowMapper);
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
		final String sql = getQuery("OCDRECEI_CGFKCHK_OFF_TXN_OFF_TXN_OFF_D");
		final RowMapper<OffenderDeductions> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderDeductions.class, offenderDeductionsMapping);
		return namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfkchkOffTxnOffTxnTxn
	 *
	 * @param params
	 *
	 */
	public List<TransactionTypes> cgfkchkOffTxnOffTxnTxn(final TransactionTypes paramBean) {
		final String sql = getQuery("OCDRECEI_CGFKCHK_OFF_TXN_OFF_TXN_TXN_T");
		final RowMapper<TransactionTypes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, TransactionTypes.class,
				transactionTypesMapping);
		return namedParameterJdbcTemplate.query(sql, createParams(), columnRowMapper);
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfklkpOffTxnOffTxnTxn
	 *
	 * @param params
	 *
	 */
	public List<TransactionTypes> cgfklkpOffTxnOffTxnTxn(final TransactionTypes paramBean) {
		final String sql = getQuery("OCDRECEI_CGFKLKP_OFF_TXN_OFF_TXN_TXN_T");
		final RowMapper<TransactionTypes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, TransactionTypes.class,
				transactionTypesMapping);
		return namedParameterJdbcTemplate.query(sql, createParams(), columnRowMapper);
	}

	@Override
	public OffenderTransactions getProdFlagDetails(final OffenderTransactions searchBean) {
		try {
			final String sql = getQuery("OCDRECEI_GET_PROD_FLAG_DETAILS");
			final RowMapper<OffenderTransactions> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
					OffenderTransactions.class, transactionTypes);
			return namedParameterJdbcTemplate.queryForObject(sql,
					createParams("txntype", searchBean.getTxnType(), "csldId", searchBean.getCaseloadId()),
					columnRowMapper);
		} catch (final Exception e) {
			logger.error("Error in Class " + this.getClass().getName() + " getProdFlagDetails error :: ", e);
		}
		return null;
	}

	@Override
	public String chkOffenderDeductions(final String caseloadId, final Long rootOffenderId, final String txnType) {
		final SqlParameter[] sqlParameters = new SqlParameter[] { new SqlParameter("P_CSLD_ID", OracleTypes.VARCHAR),
				new SqlParameter("P_OFF_ID", OracleTypes.NUMBER), new SqlParameter("P_TRANS_TYPE", OracleTypes.VARCHAR),
				new SqlParameter("P_SHADOW_ID", OracleTypes.NUMBER),
				new SqlInOutParameter("P_DED_FLAG", OracleTypes.VARCHAR), };
		final SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("DEDUCTIONS").withProcedureName("CHK_OFFENDER_DEDUCTIONS")
				.declareParameters(sqlParameters);
		final Map<String, Object> inParamMap = new HashMap<>();
		inParamMap.put("P_CSLD_ID", caseloadId);
		inParamMap.put("P_OFF_ID", rootOffenderId);
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

	@Override
	public Long getOffenderDeductionId(final Long rootOffenderId) {
		try {
			final String sql = getQuery("OCDRECEI_GET_OFFENDER_DEDUCTION_ID");
			return namedParameterJdbcTemplate.queryForObject(sql, createParams("rootOffId", rootOffenderId),
					Long.class);
		} catch (final Exception e) {
			logger.error("Error in Class " + this.getClass().getName() + " getOffenderDeductionId error :: ", e);
		}
		return 0L;
	}

	@Override
	public String checkOffenderDeductionReceipts(final Long dedId, final String txnType) {
		final String sql = getQuery("OCDRECEI_CHK_OFFENDER_DEDUCTION_RECEIPTS");
		String returnObj = "N";
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("TMPOFFDEDID", dedId, "TXNTYPE", txnType), String.class);
		} catch (final Exception e) {
			logger.error("Error in Class " + this.getClass().getName() + " checkOffenderDeductionReceipts error :: ", e);
			return returnObj;
		}
		return returnObj;
	}

	@Override
	public Long countOffenderIdDet(final Long rootOffenderId) {
		final String sql = getQuery("OCDRECEI_GET_P_OFFENDER_ID_COUNT");
		Long returnObj = null;
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams("OFFENDERID", rootOffenderId),
					Long.class);
		} catch (final Exception e) {
			logger.error("Error in Class " + this.getClass().getName() + " countOffenderIdDet error :: ", e);
			return returnObj;
		}
		return returnObj;
	}

	@Override
	public String getProfileValue() {
		final String sql = getQuery("OCDRECEI_GET_PROFILE_VALUE");
		String returnObj = "N";
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), String.class);
		} catch (final Exception e) {
			logger.error("Error in Class " + this.getClass().getName() + " getProfileValue error :: ", e);
			return returnObj;
		}
		return returnObj;
	}

	@Override
	public Long getReferenceCodesValid(final String rootOffenderId) {
		final String sql = getQuery("OCDRECEI_GET_REFERENCE_CODES_VALID");
		Long returnObj = null;
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams("PPAYMENTMETHOD", rootOffenderId),
					Long.class);
		} catch (final Exception e) {
			logger.error("Error in Class " + this.getClass().getName() + " getReferenceCodesValid error :: ", e);
			return returnObj;
		}
		return returnObj;
	}

	@Override
	public long getSessionId() {
		final String sql = getQuery("OCDRECEI_GET_SESSION_ID");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams(), Long.class);
	}

	@Override
	public void deletePrintReceiptsTmp(final long pSessionId, String modifyUserId) {
		final String sql = getQuery("OCDRECEI_DELETE_PRINT_RECEIPTS_TMP");
		try {
			String tableName = "print_receipts_tmp";
			String whereClause = "session_id = :pSessionId";
			Map<String , Object> inputMap = new HashMap<String, Object>();
			inputMap.put("pSessionId", pSessionId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereClause , inputMap);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method deletePrintReceiptsTmp", e);
		}
		namedParameterJdbcTemplate.update(sql, createParams("pSessionId", pSessionId));
	}

	@Override
	public void insertPrintReceiptsTmp(final Integer txnId, final Long offenderId, final String receiptNumber,
			final long pSessionId, final String createUserId) {
		final String sql = getQuery("OCDRECEI_INSERT_PRINT_RECEIPTS_TMP");

		namedParameterJdbcTemplate.update(sql, createParams("pTxnId", txnId, "pOffId", offenderId, "pReceiptNo",
				receiptNumber, "pSessionId", pSessionId, "createUserId", createUserId));
	}

	@Override
	public String checkAccountClosedFlag(final OffenderTransactions offTransactions) {
		final String sql = getQuery("OCDRECEI_CHK_ACCOUNT_CLOSED_FLAG");
		String returnObj = null;
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams("PCASELOADID",
					offTransactions.getCaseloadId(), "POFFENDERID", offTransactions.getRootOffenderId()), String.class);
		} catch (final Exception e) {
			logger.error("Error in Class " + this.getClass().getName() + " checkAccountClosedFlag error :: ", e);
			return returnObj;
		}
		return returnObj;
	}

	public String getProfileValuePaymentPlan() {
		final String sql = getQuery("OCDRECEI_GET_PROFILE_VALUE_PAYMENT_PLAN");
		String returnObj = "N";
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), String.class);
		} catch (final Exception e) {
			logger.error("Error in Class " + this.getClass().getName() + " getProfileValuePaymentPlan error :: ", e);
			return returnObj;
		}
		return returnObj;
	}

	public List<String> chkMissingPayPlan(final Long offenderId, final String infoNumber) {
		final String sql = getQuery("OCDRECEI_CHK_MISSING_PAYMENT_PLAN");
		try {
			return namedParameterJdbcTemplate.query(sql, createParams("OFFENDERID", offenderId, "INFONUM", infoNumber),
					new RowMapper<String>() {
						public String mapRow(ResultSet rs, int rowNum) throws SQLException {
							return rs.getString(1);
						}
					});
		} catch (final Exception e) {
			logger.error("Error in Class " + this.getClass().getName() + " chkMissingPayPlan error :: ", e);
		}
		return null;
	}

	@Override
	public String chkAccountStatus(final String caseloadId, final Long offenderId) {
		try {
			final SqlParameter[] sqlParameters = new SqlParameter[] {
					new SqlParameter("P_CSLD_ID", OracleTypes.VARCHAR),
					new SqlParameter("P_OFFENDER_ID", OracleTypes.NUMBER) };
			final SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
					.withCatalogName("TRUST").withProcedureName("CHK_ACCOUNT_STATUS").declareParameters(sqlParameters);
			final Map<String, Object> inParamMap = new HashMap<>();
			inParamMap.put("P_CSLD_ID", caseloadId);
			inParamMap.put("P_OFFENDER_ID", offenderId);

			final MapSqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
			final Map<String, Object> returnObject = simpleJDBCCall.execute(inParameter);

			if (returnObject.get("P_OPEN_AN_ACCOUNT") != null) {
				return returnObject.get("P_OPEN_AN_ACCOUNT").toString();
			}
		} catch (final Exception e) {
			logger.error("Error in Class " + this.getClass().getName() + " chkAccountStatus error :: ", e);
			return "ERROR";
		}

		return "";
	}

	@Override
	public Map<String, Object> getSubActType(final String moduleName, final String txnType, final String caseloadId) {
		final String procedureQuery = "{CALL OMS_OWNER.TRUST.GET_SUB_ACT_TYPE(:P_MODULE_NAME, :P_TXN_TYPE, :P_TXN_POST_TYPE, :P_SUB_ACT_TYPE, :CSLD_ID)}";
		try(Connection connection = dataSource.getConnection()) {
			final CallableStatement callableStatement = connection.prepareCall(procedureQuery);
			callableStatement.setString("P_MODULE_NAME", moduleName);
			callableStatement.setString("P_TXN_TYPE", txnType);
			callableStatement.registerOutParameter("P_TXN_POST_TYPE", Types.VARCHAR);
			callableStatement.registerOutParameter("P_SUB_ACT_TYPE", Types.VARCHAR);
			callableStatement.setString("CSLD_ID", caseloadId);
			callableStatement.execute();
			final Map<String, Object> returnMapData = new HashMap<>();
			returnMapData.put("P_TXN_POST_TYPE", callableStatement.getString("P_TXN_POST_TYPE"));
			returnMapData.put("P_SUB_ACT_TYPE", callableStatement.getString("P_SUB_ACT_TYPE"));
			return returnMapData;
		} catch (final Exception e) {
			logger.error("Error in Class " + this.getClass().getName() + " getSubActType error :: ", e);
		}
		return null;
	}

	/**
	 * This method will update the transaction in the table
	 * 
	 * @param offTrans
	 * @return Integer
	 * @throws Exception
	 */
	@Override
	public OffenderTransactions processGlTransNew(final OffenderTransactions offTrans) throws Exception {
		Integer genSeq = 0;
		final Map<String, Object> inParamMap = new HashMap<>();
		inParamMap.put("P_CSLD_ID", offTrans.getCaseloadId());
		inParamMap.put("P_TRANS_TYPE", offTrans.getTxnType());
		inParamMap.put("P_OPERATION_TYPE", null);
		inParamMap.put("P_TRANS_AMOUNT", offTrans.getTxnEntryAmount());
		inParamMap.put("P_TRANS_NUMBER", offTrans.getTxnId());
		inParamMap.put("P_TRANS_DATE", offTrans.getTxnEntryDate());
		inParamMap.put("P_TRANS_DESC", offTrans.getTxnEntryDesc());
		inParamMap.put("P_TRANS_SEQ", offTrans.getTxnEntrySeq());
		inParamMap.put("P_MODULE_NAME", offTrans.getModuleName());
		inParamMap.put("P_OFF_ID", offTrans.getRootOffenderId());
		inParamMap.put("P_OFF_BOOK_ID", offTrans.getOffenderBookId());
		inParamMap.put("P_SUB_ACT_TYPE_DR", offTrans.getFmSubAccountType());
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
		} catch (final Exception e) {
			offTrans.setSealFlag("8");
			logger.error("Error in Class " + this.getClass().getName() + " processGlTransNew error :: ", e);
			throw new Exception("PROCESS_GL_TRANS_NEW");
			// Other error in PROCESS_GL_TRANS_NEW:' || SQLERRM
		}
		return offTrans;
	}

	public OffenderTransactions updateOffenderBalance(final OffenderTransactions paramBean) throws Exception {
		try {
			final SqlParameter[] sqlParameters = new SqlParameter[] {
					new SqlParameter("p_csld_id", OracleTypes.VARCHAR),
					new SqlParameter("p_off_id", OracleTypes.NUMBER),
					new SqlParameter("p_trans_post_type", OracleTypes.VARCHAR),
					new SqlParameter("p_trans_date", OracleTypes.DATE),
					new SqlParameter("p_trans_number", OracleTypes.NUMBER),
					new SqlParameter("p_trans_type", OracleTypes.VARCHAR),
					new SqlParameter("p_trans_amount", OracleTypes.NUMBER),
					new SqlParameter("p_sub_act_type", OracleTypes.VARCHAR),
					new SqlParameter("p_allow_overdrawn", OracleTypes.VARCHAR), };
			final SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
					.withCatalogName("TRUST").withProcedureName("UPDATE_OFFENDER_BALANCE")
					.declareParameters(sqlParameters);
			final Map<String, Object> inParamMap = new HashMap<>();
			inParamMap.put("p_csld_id", paramBean.getCaseloadId());
			inParamMap.put("p_off_id", paramBean.getRootOffenderId());
			inParamMap.put("p_trans_post_type", paramBean.getTxnPostingType());
			inParamMap.put("p_trans_date", paramBean.getTxnEntryDate());
			inParamMap.put("p_trans_number", paramBean.getTxnId());
			inParamMap.put("p_trans_type", paramBean.getTxnType());
			inParamMap.put("p_trans_amount", paramBean.getTxnEntryAmount());
			inParamMap.put("p_sub_act_type", paramBean.getSubAccountType());
			inParamMap.put("p_allow_overdrawn", "N");
			final MapSqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
			simpleJDBCCall.execute(inParameter);
		} catch (final Exception e) {
			paramBean.setSealFlag("9");
			logger.error("Error in Class " + this.getClass().getName() + " updateOffenderBalance error :: ", e);
			// Other Error in TRUST: ' || SQLERRM
			throw new Exception("UPDATE_OFFENDER_BALANCE");
		}
		return paramBean;
	}

	@Override
	public Integer financialDoDuctionsFinancial(final OffenderTransactions offTransactions, final String pDedFlag) {
		Integer genSeq = 0;
		final Map<String, Object> inParamMap = new HashMap<>();
		inParamMap.put("P_CSLD_ID", offTransactions.getCaseloadId());
		inParamMap.put("P_OFF_ID", offTransactions.getRootOffenderId());
		inParamMap.put("P_OFF_BOOK_ID", offTransactions.getOffenderBookId());
		inParamMap.put("P_TRANS_TYPE", offTransactions.getTxnType());
		inParamMap.put("P_TRANS_NUMBER", offTransactions.getTxnId());
		inParamMap.put("P_TRANS_DATE", offTransactions.getTxnEntryDate());
		inParamMap.put("P_SUB_ACT_TYPE", offTransactions.getSubAccountType());
		inParamMap.put("P_DED_FLAG", "Y");
		inParamMap.put("P_RECEIPT_AMOUNT", offTransactions.getTxnEntryAmount());
		inParamMap.put("P_SHADOW_ID", null);
		inParamMap.put("P_DED_AMOUNT", offTransactions.getTxnEntryAmount());
		inParamMap.put("TXN_SEQUENCE", offTransactions.getTxnEntrySeq());
		inParamMap.put("P_INFO_NUMBER", offTransactions.getTxnReferenceNumber());
		try {
			namedParameterJdbcTemplate.update(
					"call OMS_OWNER.FINANCIAL.DO_DEDUCTIONS_FINANCIAL(:P_CSLD_ID, :P_OFF_ID, :P_OFF_BOOK_ID, :P_TRANS_TYPE, "
							+ ":P_TRANS_NUMBER, :P_TRANS_DATE, :P_SUB_ACT_TYPE, :P_DED_FLAG, :P_RECEIPT_AMOUNT, :P_SHADOW_ID, :P_DED_AMOUNT, "
							+ ":TXN_SEQUENCE, :P_INFO_NUMBER)",
					inParamMap);
			genSeq = 1;
		} catch (final Exception e) {
			logger.error("Error in Class " + this.getClass().getName() + " financialDoDuctionsFinancial error :: ", e);
			genSeq = 2;
			return genSeq;
		}
		return genSeq;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 *
	 * @return Integer
	 */
	public Integer txnIdNextValData() {
		final String sql = getQuery("OCDRECEI_TXN_ID");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams(), Integer.class);
	}

	public Integer genTrustRcptNmbr(final String seqId) {
		try {
			final String sql = getQuery("OCDRECEI_RECEIPT_NUMBER");
			if (sql != null) {
				final String preparedSql = sql.replace("#SEQ", seqId);
				return namedParameterJdbcTemplate.queryForObject(preparedSql, createParams(), Integer.class);
			}
		} catch (final Exception e) {
			logger.error("Error in Class " + this.getClass().getName() + " genTrustRcptNmbr error :: ", e);
		}
		return null;
	}

	@Override
	public String getSystemProfile() {
		final String sql = getQuery("GET_SYS_PFL");
		String returnObj = "N";
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), String.class);
		} catch (final Exception e) {
			logger.error("Error in Class " + this.getClass().getName() + " getSystemProfile error :: ", e);
			return returnObj;
		}
		return returnObj;
	}

	@Override
	public String getAutoSubmitProfileVal() {
		final String sql = getQuery("OCDRECEI_GET_AUTOSUBMIT_PFL_VAL");
		String returnObj = "N";
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), String.class);
		} catch (final Exception e) {
			logger.error("Error in Class " + this.getClass().getName() + " getAutoSubmitProfileVal error :: ", e);
			return returnObj;
		}
		return returnObj;
	}

	@Override
	public String getRoleBaseProfileVal() {
		final String sql = getQuery("OCDRECEI_GET_ROLEBASE_PFL_VAL");
		String returnObj = "N";
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), String.class);
		} catch (final Exception e) {
			logger.error("Error in Class " + this.getClass().getName() + " getRoleBaseProfileVal error :: ", e);
			return returnObj;
		}
		return returnObj;
	}

	public VTrustHeader getOffenderIdDetails(final OffenderTransactions paramBean) {
		final String sql = getQuery("OCDRECEI_GET_OFFENDER_ID_DATA");
		final RowMapper<VTrustHeader> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, VTrustHeader.class,
				mMapping);
		VTrustHeader returnList = new VTrustHeader();
		try {
			returnList = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("OFFIDDISPLAY", paramBean.getOffenderIdDisplay(), "CSLDID", paramBean.getCaseloadId()),
					columnRowMapper);
		} catch (final Exception e) {
			logger.error("Error in Class " + this.getClass().getName() + " getOffenderIdDetails error :: ", e);
			return returnList;
		}
		return returnList;
	}

	@Override
	public void updateOfndrTrustAcc(final Long rootOffenderId, final String caseloadId) {
		final String sql = getQuery("OCDRECEI_UPDATE_OFNDR_TRUST_ACC");
		namedParameterJdbcTemplate.update(sql, createParams("off_id", rootOffenderId, "csld_id", caseloadId));
	}

	@Override
	public String getUniqueObligationFlag(final Long rootOffenderId) {
		final String sql = getQuery("OCDRECEI_UPDATE_OFNDR_TRUST_ACC");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("off_id"), String.class);
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
		final String sql = getQuery("OCDRECEI_INSERT_INTO_OFFENDER_TRANSACTIONS");
		final Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("P_trans_number", offTransactions.getTxnId());
		paramMap.put("P_trans_seq", offTransactions.getTxnEntrySeq());
		paramMap.put("P_csld_id", offTransactions.getCaseloadId());
		paramMap.put("P_off_id", offTransactions.getRootOffenderId());
		paramMap.put("P_off_book_id", offTransactions.getOffenderBookId());
		paramMap.put("P_trans_post_type", offTransactions.getTxnPostingType());
		paramMap.put("P_trans_type", offTransactions.getTxnType());
		paramMap.put("p_trans_desc", offTransactions.getTxnEntryDesc());
		paramMap.put("p_trans_amount", offTransactions.getTxnEntryAmount());
		paramMap.put("P_trans_date", offTransactions.getTxnEntryDate());
		paramMap.put("p_sub_act_type", offTransactions.getSubAccountType());
		paramMap.put("p_slip_print_flag", "N");
		paramMap.put("p_pre_ded_amount", null);
		paramMap.put("p_deduction_flag", offTransactions.getDeductionFlag());
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
		paramMap.put("create_user_id", offTransactions.getCreateUserId());
		paramMap.put("createUserId", offTransactions.getCreateUserId());
		paramMap.put("modifyUserId", offTransactions.getCreateUserId());


		Integer returnArray = null;
		returnArray = namedParameterJdbcTemplate.update(sql, paramMap);
		if (returnArray != 0) {
			returnArray = 1;
			return returnArray;
		}
		return returnArray;
	}

	@Override
	public Long validateDspInfoNumber(final OffenderTransactions searchBean) throws Exception {
		final String sql = getQuery("OCDRECEI_VALIDATE_DSPINFONUMBER");
		return namedParameterJdbcTemplate.queryForObject(sql,
				createParams("dspInfNum", searchBean.getInfoNumber(), "caseLoadType", searchBean.getCaseloadType(),
						"offenderId", searchBean.getRootOffenderId(), "txnType", searchBean.getTxnType()),
				Long.class);
	}

	@Override
	public SystemProfiles getFclientValue() {
		final String sql = getQuery("OCDRECEI_CF_PROFILE_CLIENT");
		final RowMapper<SystemProfiles> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, SystemProfiles.class,
				systemProfilesMapping);
		return namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
	}

	@Override
	public String getfcaseloadDesc(final String caseloadId) {
		final String sql = getQuery("OCDRECEI_CF_CASELOAD_DESC");
		try {			
			return namedParameterJdbcTemplate.queryForObject(sql, createParams("CASELOAD", caseloadId), String.class);
		} catch(Exception e) {
			logger.error("OcdreceiRepositoryImpl getfcaseloadDesc :" + e.getMessage());
			return null;
		}
	}

	@Override
	public List<ocdbreciReportsBean> getReportData(final OffenderTransactions bean) {
		final String sql = getQuery("OCDRECEI_REPORTS_QUERY");
		final RowMapper<ocdbreciReportsBean> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				ocdbreciReportsBean.class, repBeanMapping);
		return namedParameterJdbcTemplate.query(sql, createParams("caseload", bean.getCaseloadId(), "from_module",
				bean.getModuleName(), "session_id", bean.getSessionId()), columnRowMapper);
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
		final String sql = getQuery("OCDRECEI_GET_AMOUNT");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("AMOUNT", amt), BigDecimal.class);
	}

	public String getAmoutninWords(final BigDecimal amt) {
		final String sql = getQuery("OCDRECEI_AMOUNT_IN_WORDS");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("TOTAMT", amt), String.class);
	}

	@Override
	public BigDecimal getTransfeesAmount(final Integer txnId, final BigDecimal txnSeq, final String txnType) {
		final String sql = getQuery("OCDRECEI_TXNFEE_AMOUNT");
		BigDecimal ofId = BigDecimal.ZERO;
		try {
			ofId = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("TXNID", txnId, "TXNSEQ", txnSeq, "TXNTYPE", txnType), BigDecimal.class);
		} catch (final Exception e) {
			logger.error("Error in Class " + this.getClass().getName() + " getTransfeesAmount error :: ", e);
			return ofId;
		}
		return ofId;
	}

	@Override
	public OffenderBeneficiaries getTotalAmountandOwingAmount(final BigDecimal offenderId) {
		final String sql = getQuery("OCDRECEI_OFFENDER_BENEFICIARIES_OWING_AMOUNT");
		OffenderBeneficiaries vHeadBlock = null;
		final RowMapper<OffenderBeneficiaries> vHdBlockRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderBeneficiaries.class, transactionTypesMapping);
		try {
			vHeadBlock = namedParameterJdbcTemplate.queryForObject(sql, createParams("OFFENDERID", offenderId),
					vHdBlockRowMapper);
		} catch (final Exception e) {
			logger.error("Error in Class " + this.getClass().getName() + " getTotalAmountandOwingAmount error :: ", e);
			return vHeadBlock;
		}
		return vHeadBlock;
	}

	@Override
	public BigDecimal getAmountData(final Integer txnId, final BigDecimal txnSeq, final String txnType,
			BigDecimal amount) {
		final String sql = getQuery("OCDRECEI_TXNFEE_AMOUNT");
		BigDecimal ofId = BigDecimal.ZERO;
		if (amount == null) {
			amount = BigDecimal.ZERO;
		}
		try {
			ofId = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("TXNID", txnId, "TXNSEQ", txnSeq, "TXNTYPE", txnType, "CF_TXNFEE", amount),
					BigDecimal.class);
		} catch (final Exception e) {
			logger.error("Error in Class " + this.getClass().getName() + " getAmountData error :: ", e);
			return ofId;
		}
		return ofId;
	}

	public List<OffenderDeductions> getReportDataQuery(final BigDecimal offederId) {
		final String sql = getQuery("OCDRECEI_REPORT_DATA");
		final RowMapper<OffenderDeductions> offenderTransactionsRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderDeductions.class, transactionTypesMapping);
		return namedParameterJdbcTemplate.query(sql, createParams("OFFENDERID", offederId),
				offenderTransactionsRowMapper);
	}

	@Override
	public String dedFlag(final String dedType) {
		final String sql = getQuery("OCDRECEI_DED_FLAG");
		String ofId = null;
		try {
			ofId = namedParameterJdbcTemplate.queryForObject(sql, createParams("DEDTYPE", dedType), String.class);
		} catch (final Exception e) {
			ofId = "N";
			logger.error("Error in Class " + this.getClass().getName() + " dedFlag error :: ", e);
			return ofId;
		}
		return ofId;
	}

	@Override
	public String existFlag(final Long dedId) {
		final String sql = getQuery("OCDRECEI_EXIST_FLAG");
		String ofId = null;
		try {
			ofId = namedParameterJdbcTemplate.queryForObject(sql, createParams("DEDID", dedId), String.class);
		} catch (final Exception e) {
			ofId = "N";
			logger.error("Error in Class " + this.getClass().getName() + " existFlag error :: ", e);
			return ofId;
		}
		return ofId;
	}

	@Override
	public BigDecimal monthsBetweenAmount(final Long dedId) {
		final String sql = getQuery("OCDRECEI_MONTHS_BETWEEN");
		BigDecimal ofId = BigDecimal.ZERO;
		try {
			ofId = namedParameterJdbcTemplate.queryForObject(sql, createParams("DEDID", dedId), BigDecimal.class);
		} catch (final Exception e) {
			logger.error("Error in Class " + this.getClass().getName() + " monthsBetweenAmount error :: ", e);
			return ofId;
		}
		return ofId;
	}

	public String getAcAndSetIndDate(final OffenderTransactions paramBean) {
		String openAnAccount = null;
		final Map<String, Object> inParamMap = new HashMap<>();
		inParamMap.put("p_csld_id", paramBean.getCaseloadId());
		inParamMap.put("p_off_id", paramBean.getOffenderId());
		try {
			namedParameterJdbcTemplate
					.update("call OMS_OWNER.DEDUCTIONS.GET_AC_AND_SET_IND_DATE(:p_off_id, :p_csld_id)", inParamMap);
			openAnAccount = "GETINDDATE";
		} catch (final Exception e) {
			logger.error("Error in Class " + this.getClass().getName() + " getAcAndSetIndDate error :: ", e);
		}
		return openAnAccount;
	}

	public Integer updateOffenderTransactionsrpt(final String moduleName, final Long sessionId, String modifyUserId) {
		final String sql = getQuery("OCDRECEI_UPDATE_OFFENDER_TRANSACTIONS_RPT");
		return namedParameterJdbcTemplate.update(sql, createParams("from_module", moduleName, "session_id", sessionId , "modifyUserId" ,modifyUserId ));
	}

	public Integer printReceiptsTmpDeletequeryRpt(final String moduleName, final Long sessionId, String modifyUserId) {
		final String sql = getQuery("OCDRECEI_PRINT_RECEIPTS_TMP_DELETE_QUERY_RPT");
		try {
			String tableName = "print_receipts_tmp";
			String whereClause = "module_name = 'OCDRECEI'  AND session_id = :session_id";
			Map<String , Object> inputMap = new HashMap<String, Object>();
			inputMap.put("session_id", sessionId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereClause , inputMap);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method deleteDedDetails", e);
		}
		return namedParameterJdbcTemplate.update(sql, createParams("session_id", sessionId));
	}

	@Override
	public void insertPrintReceiptsTmp(final OffenderTransactions offenderTransactions) {
		final String sql = getQuery("OCDRECEI_INSERT_PRINT_RECEIPTS_TMP_INSERT");

		namedParameterJdbcTemplate.update(sql, new BeanPropertySqlParameterSource(offenderTransactions));
	}

	@Override
	public String getSystemProfileValue() {
		final String sql = getQuery("OCDRECEI_GET_SYSTEM_PROFILE_VALUE");
		String returnVal = null;
		try {
			returnVal = namedParameterJdbcTemplate.queryForObject(sql, createParams(), String.class);
		} catch (Exception e) {
			logger.error("Error in Class " + this.getClass().getName() + " getSystemProfileValue error :: ", e);
		}
		return returnVal;
	}

	@Override
	public String getCfPaymentSystemProfileValue() {
		final String sql = getQuery("OCDRECEI_GET_CF_PAYMENT_SYSTEM_PROFILE_VALUE");
		String returnVal = null;
		try {
			returnVal = namedParameterJdbcTemplate.queryForObject(sql, createParams(), String.class);
		} catch (Exception e) {
			logger.error("Error in Class " + this.getClass().getName() + " getCfPaymentSystemProfileValue error :: ", e);
		}
		return returnVal;
	}

	@Override
	public List<OffFeeBillTransactions> getOffenederFeeSectionQuery(String offenderIdDisplay, String userName) {
		final String sql = getQuery("OCDRECEI_GET_OFFENDER_FEE_SECTION_QUERY");
		RowMapper<OffFeeBillTransactions> deductionTypeRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffFeeBillTransactions.class, offFeeMapping);
		try {
			return namedParameterJdbcTemplate.query(sql,
					createParams("OFFENDER_ID_DISPLAY", offenderIdDisplay, "USERID", userName), deductionTypeRowMapper);
		} catch (Exception e) {
			logger.error("Error in Class " + this.getClass().getName() + " getOffenederFeeSectionQuery error :: ", e);
			return Collections.emptyList();
		}

	}

	@Override
	public Date getLongestSupervisionExpireDate(Long offenderBookId) {
		final String sql = getQuery("OCDRECEI_GET_LONGEST_SUPV_EXPIRE_DATE");
		Date returnVal = null;
		try {
			returnVal = namedParameterJdbcTemplate.queryForObject(sql, createParams("offenderBookId", offenderBookId),
					Date.class);
		} catch (Exception e) {
			logger.error("Error in Class " + this.getClass().getName() + " getLongestSupervisionExpireDate error :: ", e);
		}
		return returnVal;
	}

	@Override
	public Integer updateOffenderFees(OffFeeBillTransactions offFeeBillTxn) {
		final String sql = getQuery("OCDRECEI_INSERT_OFFENDER_FEES");
//		Map<String, Object> paramMap = new HashMap<String, Object>();
//		paramMap.put("amount", offFeeBillTxn.getAmount());
//		paramMap.put("billStatus", offFeeBillTxn.getBillStatus());
//		paramMap.put("billId", offFeeBillTxn.getBillId());
//		paramMap.put("billTxnNo", offFeeBillTxn.getBillTxnNo());
//		Integer returnVal = namedParameterJdbcTemplate.update(sql, paramMap);
//		if (returnVal != 0) {
//			return 1;
//		}
//		return null;
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<>();
		parameters.add(new BeanPropertySqlParameterSource(offFeeBillTxn));
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (returnArray.length == 1) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public Integer getBillTranId(String billId) {
		final String sql = getQuery("OCDRECEI_OFF_FEE_BILLS_TRANSACTIONS_PRE_INSERT");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("BILL_ID", billId), Integer.class);
	}

	@Override
	public Integer prepaidAccountTransfer(OffenderTransactions offTrans) throws Exception {
		Integer genSeq = 0;
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
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
			logger.error("Error in Class " + this.getClass().getName() + " prepaidAccountTransfer error :: ", e);
			throw new Exception(e);
		}
		return genSeq;
	}

	@Override
	public String getSubAccountTypeDesc(String txnType) {
		String sql = getQuery("OCDRECEI_GET_SUB_ACCOUNT_DESC");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("txnType", txnType), String.class);
	}

	@Override
	public BigDecimal getCrDeductAcntCode(BigDecimal offenderFeeId) {
		String sql = getQuery("OCDRECEI_GET_CR_DEDUCT_TO");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("offenderFeeId", offenderFeeId),
				BigDecimal.class);
	}

	@Override
	public Integer getDrAccountCode(String txnType, String caseLoadId) {
		String sql = getQuery("OCDRECEI_GET_DR_ACCOUNT_CODE");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql,
					createParams("txnType", txnType, "caseloadId", caseLoadId), Integer.class);
		} catch (Exception e) {
			logger.error("Exception in ocdreceiRepository class getDrAccountCode method",e);
			return null;
		}
		
	}

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
			genSequence = 0;
			logger.error("Error in Class " + this.getClass().getName() + " trustInsertGltransNew error :: ", e);
			return genSequence;
		}
		return genSequence;
	}

	@Override
	public Integer getMaxTxnEntrySeq(Long offenderId, Integer txnInd) {
		final String sql = getQuery("OCDRECEI_GET_MAX_TXN_ENTRY_SEQ");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql,
					createParams("offenderId", offenderId, "txnId", txnInd), Integer.class);
		} catch (Exception e) {
			logger.error("Error in Class " + this.getClass().getName() + " getMaxTxnEntrySeq error :: ", e);
			return null;
		}
	}

	@Override
	public Integer getPaymentObligationCount(Long offenderId) {
		final String sql = getQuery("OCDRECEI_GET_PAYMENT_OBLIGATION_PROPERTY");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql, createParams("offenderId", offenderId),
					Integer.class);
		} catch (Exception e) {
			logger.error("Error in Class " + this.getClass().getName() + " getPaymentObligationCount error :: ", e);
			return 0;
		}
	}

	@Override
	public List<VAgencyAddresses> getAddreesDetails(final VAgencyAddresses paramBean) {
		final String sql = getQuery("OCDRECEI_GET_ADDRESS_ONE_TWO_DETAILS");
		ArrayList<VAgencyAddresses> returnList=new ArrayList<VAgencyAddresses>();
		final RowMapper<VAgencyAddresses> vAgyAddRowMapper = Row2BeanRowMapper.makeMapping(sql, VAgencyAddresses.class,
				vAgyAddrMapping);
		try {		
			 returnList = (ArrayList<VAgencyAddresses>) namedParameterJdbcTemplate
					.query(sql, createParams("agyLocId", paramBean.getAgyLocId()), vAgyAddRowMapper);
		} catch(Exception e) {
			logger.error(" OcdreceiRepositoryImpl getAddreesDetails :" + e.getMessage());
			return returnList;
		}
		return returnList;
	}

	@Override
	public String getClientName(Long offenderBookId, String userName) {
		final String sql = getQuery("OCDRECEI_GET_CLIENT_NAME_FOR_RECEIPT_REPORT");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql,
					createParams("offenderBookId", offenderBookId, "USERID", userName), String.class);
		} catch (Exception e) {
			logger.error("Error in Class " + this.getClass().getName() + " getClientName error :: ", e);
			return null;
		}
	}

	@Override
	public List<CasePlans> getCasePlanDetailsToGetPoName(Long offenderBookId) {
		final String sql = getQuery("OCDIPLAN_CASPLN_FIND_CASE_PLANS");
		ArrayList<CasePlans> returnList=new ArrayList<CasePlans>();
		final RowMapper<CasePlans> casePlansRowMapper = Row2BeanRowMapper.makeMapping(sql, CasePlans.class,
				casePlansMapping);
		try {		
			returnList = (ArrayList<CasePlans>) namedParameterJdbcTemplate.query(sql,
					createParams("offenderBookId", offenderBookId), casePlansRowMapper);
		} catch (Exception e) {
			logger.error("OcdreceiRepositoryImpl : getCasePlanDetailsToGetPoName :" + e.getMessage());
			return returnList;
		}
		return returnList;
	}

	@Override
	public String getTransActionDescription(String txnType) {
		final String sql = getQuery("OCDRECEI_GET_TRANSACTION_TYPE_DESCRIPTION");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql, createParams("txnType", txnType), String.class);
		} catch (Exception e) {
			logger.error("Error in Class " + this.getClass().getName() + " getTransActionDescription error :: ", e);
			return null;
		}
	}

	@Override
	public Integer getStaffID(String userId) {
		final String sql = getQuery("OCDRECEI_GET_STAFF_ID");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("USER_ID",userId ), Integer.class);
	}

	@Override
	public List<FeeAccounts> getFeeCodeRecordGroup() {
		String sql = getQuery("OCDRECEI_GET_PREPAID_ACNT_FEE_CODE");
		List<FeeAccounts> returnList = new ArrayList<FeeAccounts>();
		final RowMapper<FeeAccounts> referenceMapper = Row2BeanRowMapper.makeMapping(sql, FeeAccounts.class, mMapping);
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams(), referenceMapper);
		} catch (final Exception e) {
			logger.error("Error in Class " + this.getClass().getName() + " getFeeCodeRecordGroup error :: ", e);
		}
		return returnList;
	}

	@Override
	public List<OffFeeBillTransactions> getFeeBillPrvsCurrentBlnc(Integer trustTxnId) {
		String sql = getQuery("OCDRECEI_GET_FEE_BILL_PREVIOUS_AND_CURRENT_BALANCE");
		List<OffFeeBillTransactions> returnList = new ArrayList<OffFeeBillTransactions>();
		final RowMapper<OffFeeBillTransactions> referenceMapper = Row2BeanRowMapper.makeMapping(sql,
				OffFeeBillTransactions.class, offFeeMapping);
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams("trustTxnId", trustTxnId), referenceMapper);
		} catch (final Exception e) {
			logger.error("Error in Class " + this.getClass().getName() + " getFeeBillPrvsCurrentBlnc error :: ", e);
		}
		return returnList;
	}

	@Override
	public String getRemitterName(Integer txnId, Integer txnEntrySeq) {
		final String sql = getQuery("OCDRECEI_GET_REMITTER_NAME_FOR_REPORT");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql,
					createParams("txnId", txnId, "txnEntrySeq", txnEntrySeq), String.class);
		} catch (Exception e) {
			logger.error("Error in Class " + this.getClass().getName() + " getRemitterName error :: ", e);
			return null;
		}
	}

	@Override
	public void updateOffFeeBillsDate(List<OffFeeBillTransactions> updateOffFeeBillsList) {
		final String sql = getQuery("OCDRECEI_UPDATE_OFF_FEE_BILLS_DATE");
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (OffFeeBillTransactions obj : updateOffFeeBillsList) {
			parameters.add(new BeanPropertySqlParameterSource(obj));
		}
		namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
	}

	@Override
	public String getbillEndDayPfVal() {
		final String sql = getQuery("OCDRECEI_GET_SYS_PFL_VALUE");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql, createParams(), String.class);
		} catch (Exception e) {
			logger.error("Error in Class " + this.getClass().getName() + " getbillEndDayPfVal error :: ", e);
			return null;
		}
	}

	@Override
	public Integer offStmtCommit(offBillingStatements insertList) {
		String sql = getQuery("OCDRECEI_OFF_BILL_STMT_INSERT_QUERY");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<>();
		parameters.add(new BeanPropertySqlParameterSource(insertList));
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (returnArray.length == 1) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public Integer offFeeBillsUpdate(offBillingStatements updateBean) {
		String sql = getQuery("OCDRECEI_UPDATE_OFF_FEE_BILLS");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<>();
		parameters.add(new BeanPropertySqlParameterSource(updateBean));
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (returnArray.length == 1) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public Integer updateFeeAccountStatus(FeeAccountProfiles insertObj) {
		String sql = getQuery("OCDRECEI_UPDATE_FEE_ACCOUNT_STATUS");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<>();
		parameters.add(new BeanPropertySqlParameterSource(insertObj));
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (returnArray.length == 1 && returnArray[0]==1) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public BigDecimal getOffFeeIdTotalBalanceOwing(Long offenderBookId, String feeCode, String caseloadId) {
		String sql = getQuery("OCDRECEI_GET_OFF_FEE_ID_TOTAL_BALANCE_OWNING");
		try {
		return  namedParameterJdbcTemplate.queryForObject(sql,createParams("offenderBookId", offenderBookId,"feeCode",feeCode,"caseloadId",caseloadId),BigDecimal.class);
		}catch (Exception e) {
			logger.error("OcdreceiRepositoryImpl in +"+ this.getClass().getName() +" getOffFeeIdTotalBalanceOwing :" + e);
			return null;
		}
	}
	
	@Override
	public BigDecimal getPrepaidBalance(Long offenderId, String caseloadId, String feeCode) {
		String sql = getQuery("OCDRECEI_GET_OFFENDER_PREPAID_BALANCE");
		try {
		return  namedParameterJdbcTemplate.queryForObject(sql,createParams("offenderId", offenderId,"caseloadId",caseloadId,"feeCode",feeCode),BigDecimal.class);
		}catch (Exception e) {
			logger.error("OcdreceiRepositoryImpl in +"+ this.getClass().getName() +" getPrepaidBalance :" + e);
			return null;
		}
	}
	
	@Override
	public PrintReceiptsTmp getPrintReceiptsBySessionid(Long sessionId) {
		String sql = getQuery("OCDRECEI_GET_PRINT_RECEIPTS_BY_SESSIONID");
		final RowMapper<PrintReceiptsTmp> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, PrintReceiptsTmp.class,
				mMapping);
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("sessionId", sessionId), columnRowMapper);
	}

	@Override
	public PrintReceiptsTmp getPrintReceiptsTmpRecord(final String modulename, final Long sessionId) {
		String sql = getQuery("OCDRECEI_GET_PRINT_RECEIPTS_TMP");
		PrintReceiptsTmp temp = new PrintReceiptsTmp();
		
		final RowMapper<PrintReceiptsTmp> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, PrintReceiptsTmp.class,
				mMapping);
		try {
			
			temp = namedParameterJdbcTemplate.queryForObject(sql, createParams("sessionId", sessionId), columnRowMapper);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return temp;
	}

	@Override
	public OffFeeBills getOldDataOffFeeBills(String billId) {
		final String sql = getQuery("OCDRECEI_OLD_DATA_OFF_FEE_BILLS");
		final RowMapper<OffFeeBills> feeBillTrnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffFeeBills.class, offFeeMapping);
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("billId",billId),
				feeBillTrnRowMapper);
	}
	
}

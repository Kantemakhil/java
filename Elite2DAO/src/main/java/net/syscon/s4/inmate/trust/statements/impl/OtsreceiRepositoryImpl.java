package net.syscon.s4.inmate.trust.statements.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.CaseloadAgencyLocations;
import net.syscon.s4.common.beans.OmsRequests;
import net.syscon.s4.common.beans.OtrdreceReportBean;
import net.syscon.s4.common.beans.OtrreceiReportBean;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.OmsModules;
import net.syscon.s4.im.beans.SysDual;
import net.syscon.s4.inmate.beans.CaseloadDeductionProfiles;
import net.syscon.s4.inmate.beans.Printers;
import net.syscon.s4.inmate.trust.statements.OtsreceiRepository;

/**
 * Class OtsreceiRepositoryImpl
 */
@Repository
public class OtsreceiRepositoryImpl extends RepositoryBase implements OtsreceiRepository {

	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OtsreceiRepositoryImpl.class.getName());
	private final Map<String, FieldMapper> mmMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("AGY_LOC_ID", new FieldMapper("agyLocId")).build();
	private final Map<String, FieldMapper> printersMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("PRINTER_ID", new FieldMapper("printerId")).put("DESCRIPTION", new FieldMapper("description")).build();
	private final Map<String, FieldMapper> omsRequestsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CREATE_USER_ID", new FieldMapper("createUserId"))
			.put("MODIFY_USER_ID", new FieldMapper("modifyUserId")).put("REQUEST_ID", new FieldMapper("requestId"))
			.put("DISPLAY_FLAG", new FieldMapper("displayFlag"))
			.put("REQUEST_USER_ID", new FieldMapper("requestUserId")).put("MODULE_NAME", new FieldMapper("moduleName"))
			.put("REQUEST_STATUS", new FieldMapper("requestStatus")).put("REQUEST_DATE", new FieldMapper("requestDate"))
			.put("SEAL_FLAG", new FieldMapper("sealFlag")).put("CREATE_DATETIME", new FieldMapper("createDatetime"))
			.put("PRINTER_ID", new FieldMapper("printerId")).put("MODIFY_DATETIME", new FieldMapper("modifyDatetime"))
			.put("NUMBER_OF_COPY", new FieldMapper("numberOfCopy")).build();
	private final Map<String, FieldMapper> omsModulesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("MODULE_NAME", new FieldMapper("moduleName")).put("DESCRIPTION", new FieldMapper("description"))
			.build();
	private final Map<String, FieldMapper> staffmembersMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("MODULE_NAME", new FieldMapper("moduleName")).put("PRINTER_ID", new FieldMapper("printerId"))
			.put("USER_ID", new FieldMapper("userId")).put("LAST_NAME", new FieldMapper("lastName"))
			.put("FIRST_NAME", new FieldMapper("firstName")).put("STAFF_ID", new FieldMapper("staffId")).build();
	private final Map<String, FieldMapper> systemProfilesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("PROFILE_VALUE", new FieldMapper(" profileValue "))
			.put("LTRIM(RTRIM(DESCRIPTION))", new FieldMapper(" ltrim(rtrim(description)) ")).build();

	private final Map<String, FieldMapper> reportingMapping = new ImmutableMap.Builder<String, FieldMapper>()

			.put("TXN_DATE", new FieldMapper("txnDate")).put("REC_NUM", new FieldMapper("recNum"))
			.put("T_CSLD", new FieldMapper("tCsld")).put("AMT", new FieldMapper("amt"))
			.put("OFF_NAME", new FieldMapper("offName")).put("OFF_ID", new FieldMapper("offId"))
			.put("TXN_DESC", new FieldMapper("txnDesc")).put("USER_ID", new FieldMapper("userId"))
			.put("PAYEE_NAME", new FieldMapper("payeeName")).put("CHECK_NUM", new FieldMapper("checkNum"))
			.put("LOCATION", new FieldMapper("location")).put("BOOK_ID", new FieldMapper("bookId"))
			.put("OFF_ID_DISPLAY", new FieldMapper("offId")).put("REMITTER_ID", new FieldMapper("remitterId"))
			.put("REMITTER_NAME", new FieldMapper("remitter")).put("TXN_ID", new FieldMapper("txnId"))
			.put("TXN_POSTING_TYPE", new FieldMapper("txnPostingType"))
			.put("ACCOUNT_CODE", new FieldMapper("accountCode")).put("ACCOUNT_NAME", new FieldMapper("accountName"))
			.put("CLDESC", new FieldMapper("clDesc")).put("CLIENT", new FieldMapper("client")).build();

	/**
	 * Creates new OtsreceiRepositoryImpl class Object
	 */
	public OtsreceiRepositoryImpl() {
		// OtsreceiRepositoryImpl
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            OmsRequests
	 *
	 * @return List<OmsRequests>
	 *
	 * @
	 */
	public List<OmsRequests> omsReqExecuteQuery(final OmsRequests objSearchDao) {
		final String sql = getQuery("OTSRECEI_OMSREQ_FIND_OMS_REQUESTS");
		final RowMapper<OmsRequests> OmsRequestsRowMapper = Row2BeanRowMapper.makeMapping(sql, OmsRequests.class,
				omsRequestsMapping);
		final List<OmsRequests> returnList =namedParameterJdbcTemplate.query(sql,
				createParams(), OmsRequestsRowMapper);
		return returnList;
	}

	/**
	 * @param
	 *
	 * @
	 *
	 */
	public int preInsert() {
		return 0;
	}

	/**
	 * This method is used to insert the records in the data base tables based
	 * on
	 *
	 * @param lstOmsRequests
	 *            List<OmsRequests>
	 *
	 * @return List<Integer>
	 *
	 * @
	 */
	public Integer omsReqInsertOmsRequests(final List<OmsRequests> lstOmsRequests) {
		final String sql = getQuery("OTSRECEI_OMSREQ_INSERT_OMS_REQUESTS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstOmsRequests.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstOmsRequests
	 *            List<OmsRequests>
	 *
	 * @
	 */
	public Integer omsReqUpdateOmsRequests(final List<OmsRequests> lstOmsRequests) {
		final String sql = getQuery("OTSRECEI_OMSREQ_UPDATE_OMS_REQUESTS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OmsRequests omsRequests : lstOmsRequests) {
			parameters.add(new BeanPropertySqlParameterSource(omsRequests));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstOmsRequests.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to delete records from data base tables based on
	 *
	 * @param lstOmsRequests
	 *            List<OmsRequests>
	 *
	 * @
	 */
	public Integer omsReqDeleteOmsRequests(final List<OmsRequests> lstOmsRequests) {
		final String sql = getQuery("OTSRECEI_OMSREQ_DELETE_OMS_REQUESTS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OmsRequests omsRequests : lstOmsRequests) {
			parameters.add(new BeanPropertySqlParameterSource(omsRequests));
		}
		try {
			String tableName = "OMS_REQUESTS";
			String whereClause = null;
			batchUpdatePreDeletedRows(tableName, whereClause , parameters);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method omsReqDeleteOmsRequests", e);
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstOmsRequests.size() == returnArray.length) {
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
	public List<Printers> cgfkOmsReqPrinterIdRecordGroup() {
		final String sql = getQuery("OTSRECEI_FIND_CGFKOMSREQPRINTERID");
		final RowMapper<Printers> mRowMapper = Row2BeanRowMapper.makeMapping(sql, Printers.class, printersMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (Exception e) {
			logger.error("In method cgfkOmsReqPrinterIdRecordGroup : ", e);
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<OmsModules> cgfkOmsReqModuleNameRecordGroup() {
		final String sql = getQuery("OTSRECEI_FIND_CGFKOMSREQMODULENAME");
		final RowMapper<OmsModules> mRowMapper = Row2BeanRowMapper.makeMapping(sql, OmsModules.class,
				omsModulesMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (Exception e) {
			logger.error("In method cgfkOmsReqModuleNameRecordGroup : ", e);
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<MM>
	 */
	public List<CaseloadAgencyLocations> cgfkCsldDpAgyLocRecordGroup(final String caseloadId) {
		final String sql = getQuery("OTSRECEI_FIND_CGFKCSLDDPAGYLOC");
		final RowMapper<CaseloadAgencyLocations> mMRowMapper = Row2BeanRowMapper.makeMapping(sql,
				CaseloadAgencyLocations.class, mmMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams("CASELOAD_ID", caseloadId), mMRowMapper);
		} catch (Exception e) {
			logger.error("In method cgfkCsldDpAgyLocRecordGroup : ", e);
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<StaffMembers> cgfkRecptsCreatedUsersRecordGroup() {
		final String sql = getQuery("OTSRECEI_FIND_CGFKRECPTSCREATEDUSERS");
		final RowMapper<StaffMembers> mRowMapper = Row2BeanRowMapper.makeMapping(sql, StaffMembers.class,
				staffmembersMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (Exception e) {
			logger.error("In method cgfkRecptsCreatedUsersRecordGroup : ", e);
			return Collections.emptyList();
		}
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * omsReqPreInsert
	 *
	 * @param params
	 *
	 */
	public Long omsReqPreInsert(final SysDual paramBean) {
		final String sql = getQuery("OTSRECEI_OMS_REQ_PREINSERT");
		Long returnObj=null;
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), Long.class);
		}catch (Exception e) {
			logger.error(this.getClass().getName()+"omsReqPreInsert", e);
		}
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * otsreceiKeyCommit
	 *
	 * @param params
	 *
	 */
	public OmsModules otsreceiKeyCommit(final OmsModules paramBean) {
		final String sql = getQuery("OTSRECEI_OTSRECEI_KEYCOMMIT");
		OmsModules returnObj=new OmsModules();
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), new BeanPropertyRowMapper<OmsModules>(OmsModules.class));	
		}catch (Exception e) {
			logger.error(this.getClass().getName()+"Method in otsreceiKeyCommit"+e);
		}

		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfkchkOmsReqOmsReqPrint
	 *
	 * @param params
	 *
	 */
	public Printers cgfkchkOmsReqOmsReqPrint(final Printers paramBean) {
		final String sql = getQuery("OTSRECEI_CGFKCHK_OMS_REQ_OMS_REQ_PRINT");
		Printers returnObj=new Printers();
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), new BeanPropertyRowMapper<Printers>(Printers.class));
		}catch (Exception e) {
			logger.error(this.getClass().getName()+"method in cgfkchkOmsReqOmsReqPrint"+e);
		}
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
	public List<Object> cgwhenNewFormInstance(final SysDual paramBean) {
		final String sql = getQuery("OTSRECEI_CGWHEN_NEW_FORM_INSTANCE");
		final List<Object> returnList = namedParameterJdbcTemplate.queryForList(sql, createParams(), Object.class);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfkchkOmsReqOmsReqModul
	 *
	 * @param params
	 *
	 */
	public OmsModules cgfkchkOmsReqOmsReqModul(final OmsModules paramBean) {
		final String sql = getQuery("OTSRECEI_CGFKCHK_OMS_REQ_OMS_REQ_MODUL");
		OmsModules returnObj=new OmsModules();
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), new BeanPropertyRowMapper<OmsModules>(OmsModules.class));
		}catch (Exception e) {
			logger.error(this.getClass().getName()+" method in cgfkchkOmsReqOmsReqModul"+e);
		}
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
	public SystemProfiles runReport(final SystemProfiles paramBean) {
		final String sql = getQuery("OTSRECEI_RUN_REPORT");
		SystemProfiles returnObj=new SystemProfiles();
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(),new BeanPropertyRowMapper<SystemProfiles>(SystemProfiles.class));
		}catch (Exception e) {
			logger.error(this.getClass().getName()+"method in runReport"+e);
		}
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * checkValidReceipts
	 *
	 * @param paramBean
	 *
	 */
	public List<String> checkValidReceipts(final CaseloadDeductionProfiles paramBean) {
		final String sql = getQuery("OTSRECEI_CHECK_VALID_RECEIPTS");
		List<String> returnObj = null;
		try {
			returnObj = namedParameterJdbcTemplate.queryForList(sql,
					createParams("caseload_id", paramBean.getCaseloadId(), "caseload_id", paramBean.getCaseloadId(),
							"agy_loc_id", paramBean.getAgyLocId(), "agy_loc_id", paramBean.getAgyLocId(), "txn_usage",
							paramBean.getTxnUsage(), "create_user_id", paramBean.getCreateUserId(), "create_user_id",
							paramBean.getCreateUserId()),
					String.class);
		} catch (Exception e) {
			logger.error(this.getClass().getName()+"method in checkValidReceipts"+e);
			return returnObj;
		}
		return returnObj;
	}

	@Override
	public BigDecimal getDefaultCopies() {
		final String sql = getQuery("OTSRECEI_GET_DEFAULT_COPIES");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql, createParams(), BigDecimal.class);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public BigDecimal receiptNumExist(final CaseloadDeductionProfiles param) {
		final String sql = getQuery("OTSRECEI_RECEIPT_NUM_EXIST");
		return namedParameterJdbcTemplate.queryForObject(sql,
				createParams("caseloadId", param.getCaseloadId(), "receiptNumber", param.getReceiptNumber(), "agyLocId",
						param.getAgyLocId(), "userId", param.getCreateUserId()),
				BigDecimal.class);
	}

	@Override
	public List<OtrreceiReportBean> generateOtrreceireport(final CaseloadDeductionProfiles paramBean) {
		String sql = getQuery("OTSRECEI_GENERATE_OTRRECEI_REPORT");
		final StringBuffer sqlQuery = new StringBuffer();
		if (sql != null && !sql.isEmpty()) {
			if (paramBean != null) {
				if ("Y".equals(paramBean.getActiveFlag())) {
					final String param = "ot.receipt_number in (SELECT receipt_number "
							+ " FROM offender_transactions OT " + "  WHERE ot.caseload_id = :caseloadId "
							+ "  AND ot.receipt_number IS NOT NULL " + " AND ot.receipt_number LIKE '%'||:caseloadId ||'%' "
							+ " AND ot.receipt_printed_flag IS NULL " + " AND ( coalesce (:agyLocId::text,'')=''  "
							+ " OR EXISTS (SELECT 1 " + " FROM offender_bookings ob "
							+ " WHERE ob.root_offender_id = ot.offender_id " + " AND ob.agy_loc_id = :agyLocId) "
							+ " ) " + " AND EXISTS (SELECT 1 " + " FROM transaction_types tt "
							+ " WHERE tt.txn_usage = 'R' " + " AND tt.txn_type = ot.txn_type) "
							+ " AND (:createUserId IS NULL " + " OR EXISTS (SELECT 1 " + " FROM gl_transactions gt "
							+ " WHERE gt.txn_id = ot.txn_id " + " AND gt.txn_entry_seq = ot.txn_entry_seq "
							+ " AND gt.create_user_id = :createUserId))) ";
					if (paramBean.getNbtPersonIdOne() != null && !paramBean.getNbtPersonIdOne().isEmpty()
							&& (paramBean.getNbtPersonIdTwo() == null || paramBean.getNbtPersonIdTwo().isEmpty())) {
						sqlQuery.append(" AND LPAD(SUBSTR(ot.receipt_number, -7, 7),7,'0') = LPAD(:fromReceiptNumber,7,'0') ");
					} else if (paramBean.getNbtPersonIdTwo() != null && !paramBean.getNbtPersonIdTwo().isEmpty()
							&& (paramBean.getNbtPersonIdOne() == null || paramBean.getNbtPersonIdOne().isEmpty())) {
						sqlQuery.append(" AND LPAD(SUBSTR(ot.receipt_number, -7, 7),7,'0') = LPAD(:toReceiptNumber,7,'0') ");
						sql = sql.replace("#PARAM", param);
					} else if (paramBean.getNbtPersonIdTwo() != null && paramBean.getNbtPersonIdOne() != null) {
						sqlQuery.append(" AND LPAD(SUBSTR(ot.receipt_number, -7, 7),7,'0') BETWEEN LPAD(:fromReceiptNumber,7,'0') AND"
								+ " LPAD(:toReceiptNumber,7,'0')" );
					}

					String sqlTwo = param + sqlQuery.toString().trim();
					sql = sql.replace("#PARAM", sqlTwo);
				} else {
					if (paramBean.getNbtPersonIdOne() != null && !paramBean.getNbtPersonIdOne().isEmpty()
							&& (paramBean.getNbtPersonIdTwo() == null || paramBean.getNbtPersonIdTwo().isEmpty())) {
						final String param = "LPAD(SUBSTR(ot.receipt_number, -7, 7),7,'0') = LPAD(:fromReceiptNumber,7,'0')";
						sql = sql.replace("#PARAM", param);
					} else if (paramBean.getNbtPersonIdTwo() != null && !paramBean.getNbtPersonIdTwo().isEmpty()
							&& (paramBean.getNbtPersonIdOne() == null || paramBean.getNbtPersonIdOne().isEmpty())) {
						final String param = "LPAD(SUBSTR(ot.receipt_number, -7, 7),7,'0') = LPAD(:toReceiptNumber,7,'0')";
						sql = sql.replace("#PARAM", param);
					} else {
						final String param = "LPAD(SUBSTR(ot.receipt_number, -7, 7),7,'0') BETWEEN LPAD(:fromReceiptNumber,7,'0') AND"
								+ " LPAD(:toReceiptNumber,7,'0')";
						sql = sql.replace("#PARAM", param);
					}
				}
			}
		}
		final RowMapper<OtrreceiReportBean> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OtrreceiReportBean.class, reportingMapping);
		List<OtrreceiReportBean> returnObj=new ArrayList<OtrreceiReportBean>();
		try {
			returnObj= namedParameterJdbcTemplate.query(sql,
					createParams("caseloadId", paramBean.getCaseloadId(), "caseloadType", paramBean.getCaseloadType(),
							"agyLocId", paramBean.getAgyLocId(), "createUserId", paramBean.getCreateUserId(),
							"fromReceiptNumber", paramBean.getNbtPersonIdOne(), "toReceiptNumber",
							paramBean.getNbtPersonIdTwo()),
					columnRowMapper);
		}catch (Exception e) {
			logger.error("Exception in generateOtrreceireport : ", e);
		}
		return returnObj;
	}

	@Override
	public List<OtrdreceReportBean> generateOtrdrecereport(final CaseloadDeductionProfiles paramBean) {
		String sql = getQuery("OTSRECEI_GENERATE_OTRDRECE_REPORT");
		if (sql != null && !sql.isEmpty()) {
			if (paramBean != null) {
				if ("Y".equals(paramBean.getActiveFlag())) {
					final String param = "ot.receipt_number in (SELECT receipt_number "
							+ " FROM offender_transactions OT " + "  WHERE ot.caseload_id = :caseloadId "
							+ "  AND ot.receipt_number IS NOT NULL " + " AND ot.receipt_number LIKE '%'||:caseloadId ||'%' "
							+ " AND ot.receipt_printed_flag IS NULL " + " AND (coalesce(:agyLocId::text, '') = '' "
							+ " OR EXISTS (SELECT 1 " + " FROM offender_bookings ob "
							+ " WHERE ob.root_offender_id = ot.offender_id " + " AND ob.agy_loc_id = :agyLocId) "
							+ " ) " + " AND EXISTS (SELECT 1 " + " FROM transaction_types tt "
							+ " WHERE tt.txn_usage = 'D' " + " AND tt.txn_type = ot.txn_type) "
							+ " AND (coalesce(:createUserId::text , '') = '' " + " OR EXISTS (SELECT 1 " + " FROM gl_transactions gt "
							+ " WHERE gt.txn_id = ot.txn_id " + " AND gt.txn_entry_seq = ot.txn_entry_seq "
							+ " AND gt.create_user_id = :createUserId)))";
					sql = sql.replace("#PARAM", param);
				} else {
					if (paramBean.getNbtPersonIdOne() != null && !paramBean.getNbtPersonIdOne().isEmpty()
							&& (paramBean.getNbtPersonIdTwo() == null || paramBean.getNbtPersonIdTwo().isEmpty())) {
						final String param = "LPAD(SUBSTR(ot.receipt_number, -7, 7),7,'0') = LPAD(:fromReceiptNumber,7,'0')";
						sql = sql.replace("#PARAM", param);
					} else if (paramBean.getNbtPersonIdTwo() != null && !paramBean.getNbtPersonIdTwo().isEmpty()
							&& (paramBean.getNbtPersonIdOne() == null || paramBean.getNbtPersonIdOne().isEmpty())) {
						final String param = "LPAD(SUBSTR(ot.receipt_number, -7, 7),7,'0') = LPAD(:toReceiptNumber,7,'0')";
						sql = sql.replace("#PARAM", param);
					} else {
						final String param = "LPAD(SUBSTR(ot.receipt_number, -7, 7),7,'0') BETWEEN LPAD(:fromReceiptNumber,7,'0') AND"
								+ " LPAD(:toReceiptNumber,7,'0')";
						sql = sql.replace("#PARAM", param);
					}
				}
			}
		}
		final RowMapper<OtrdreceReportBean> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OtrdreceReportBean.class, reportingMapping);
		List<OtrdreceReportBean> returnGen=new ArrayList<OtrdreceReportBean>();
		try {
			
			returnGen= namedParameterJdbcTemplate.query(sql,
					createParams("caseloadId", paramBean.getCaseloadId(), "caseloadType", paramBean.getCaseloadType(),
							"agyLocId", paramBean.getAgyLocId(), "createUserId", paramBean.getCreateUserId(),"receipt_printed_flag",paramBean.getActiveFlag(),
							"fromReceiptNumber", paramBean.getNbtPersonIdOne(), "toReceiptNumber",paramBean.getNbtPersonIdTwo(),"receipt_number",paramBean.getReceiptNumber()),
					columnRowMapper);
		}catch (Exception e) {
			logger.error("generateOtrdrecereport", e);
		}
		return	returnGen;
	}

	@Override
	public String numberToWord(final BigDecimal amount) {
		final String sql = getQuery("OTDRDTFU_NUMBER_TO_WORD");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql, createParams("amount", amount), String.class);
		} catch (Exception e) {
			logger.error("NUMBER_TO_WORD", e);
		}
		return null;
	}

	@Override
	public String getCurrencySymbol() {
		final String sql = getQuery("OTSRECEI_GET_CURRENCY_SYMBOL"); 
		try {
			return namedParameterJdbcTemplate.queryForObject(sql, createParams(), String.class);
		}catch (Exception e) {
			logger.error("OTDRDTFU_GET_CURRENCY_SYMBOL", e);
		}
		return null;
	}

}

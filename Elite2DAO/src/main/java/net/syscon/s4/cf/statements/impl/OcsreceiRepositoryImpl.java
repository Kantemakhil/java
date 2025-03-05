package net.syscon.s4.cf.statements.impl;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.cf.offendertransactions.beans.PrintReceiptsTmp;
import net.syscon.s4.cf.statements.OcsreceiRepository;
import net.syscon.s4.cf.statements.beans.ocsreceiReportsBean;
import net.syscon.s4.common.beans.OffenderBookings;
import net.syscon.s4.common.beans.OmsRequests;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.OffenderTransactions;
import net.syscon.s4.im.beans.OmsModules;
import net.syscon.s4.inmate.beans.OffenderDeductions;
import net.syscon.s4.inmate.beans.Printers;
import net.syscon.s4.inst.correspondencetracking.beans.OffenderMailLog;

/**
 * Class OcsreceiRepositoryImpl
 */
@Repository
public class OcsreceiRepositoryImpl extends RepositoryBase implements OcsreceiRepository {

	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OcsreceiRepositoryImpl.class.getName());

private final Map<String, FieldMapper> printersMapping = new ImmutableMap.Builder<String, FieldMapper>()
.put("PRINTER_ID", 						new FieldMapper("printer_id"))
.put("DESCRIPTION", 						new FieldMapper("description"))
.build();
private final Map<String, FieldMapper> offenderBookingsMapping = new ImmutableMap.Builder<String, FieldMapper>()
.put("NAME", 						new FieldMapper("dspLastName"))
.put("OFFENDER_ID_DISPLAY", 						new FieldMapper("cgnbtBookingStatus"))
.build();
private final Map<String, FieldMapper> omsModulesMapping = new ImmutableMap.Builder<String, FieldMapper>()
.put("MODULE_NAME", 						new FieldMapper("module_name"))
.put("DESCRIPTION", 						new FieldMapper("description"))
.put("CODE", 						new FieldMapper("code"))
.put("PROFILE_VALUE", 						new FieldMapper(" profile_value "))
.put("PRINT_FORMAT_CODE", 						new FieldMapper(" print_format_code "))
.build();
private final Map<String, FieldMapper> systemProfilesMapping = new ImmutableMap.Builder<String, FieldMapper>()
.put("MODULE_NAME", 						new FieldMapper("module_name"))
.put("DESCRIPTION", 						new FieldMapper("description"))
.put("PROFILE_VALUE", 						new FieldMapper("profileValue"))
.put("PRINT_FORMAT_CODE", 						new FieldMapper(" print_format_code "))
.build();
private final Map<String, FieldMapper> repBeanMapping = new ImmutableMap.Builder<String, FieldMapper>()
.put("FTXNDATE", 						new FieldMapper("fTxnDate"))
.put("TXNDATE", 						new FieldMapper("txnDate"))
.put("FRECNO", 						new FieldMapper("fRecNo"))
.put("TOTALAMOUNT", 						new FieldMapper("totalAmount"))
.put("FOFFNAME", 						new FieldMapper("fOffName"))
.put("FOFFID", 						new FieldMapper("fOffId"))
.put("FLOCATION", 						new FieldMapper("fLocation"))
.put("FTXNDESC", 						new FieldMapper("fTxnDesc"))
.put("USERID", 						new FieldMapper("userId"))
.put("PAYEE", 						new FieldMapper("payee"))
.put("TXNID", 						new FieldMapper("txnId"))
.put("OFFENDER_ID", 				new FieldMapper("offenderId"))
.build();

private final Map<String, FieldMapper> omsRequestMapping = new ImmutableMap.Builder<String, FieldMapper>()
.put("REQUEST_ID", 		new FieldMapper("requestId"))
.put("MODULE_NAME", 		new FieldMapper("moduleName"))
.put("NUMBER_OF_COPY", 		new FieldMapper("numberOfCopy"))
.put("REQUEST_STATUS", 		new FieldMapper("requestStatus"))
.put("PRINTER_ID", 		new FieldMapper("printerId"))
.put("DISPLAY_FLAG", 		new FieldMapper("displayFlag"))
.put("REQUEST_DATE", 		new FieldMapper("requestDate"))
.put("REQUEST_USER_ID", 		new FieldMapper("requestUserId"))
.put("CREATE_DATETIME", 		new FieldMapper("createDatetime"))
.put("CREATE_USER_ID", 		new FieldMapper("createUserId"))
.put("MODIFY_DATETIME", 		new FieldMapper("modifyDatetime"))
.put("MODIFY_USER_ID", 		new FieldMapper("modifyUserId"))
.put("SEAL_FLAG", 		new FieldMapper("sealFlag"))
.build();

private final Map<String, FieldMapper> offenderTransactionsMapping = new ImmutableMap.Builder<String, FieldMapper>()
.put("TXN_ID", 		new FieldMapper("txnId"))
.put("TXN_ENTRY_SEQ", 		new FieldMapper("txnEntrySeq"))
.put("CASELOAD_ID", 		new FieldMapper("caseloadId"))
.put("OFFENDER_ID", 		new FieldMapper("offenderId"))
.put("OFFENDER_BOOK_ID", 		new FieldMapper("offenderBookId"))
.put("TXN_POSTING_TYPE", 		new FieldMapper("txnPostingType"))
.put("TXN_TYPE", 		new FieldMapper("txnType"))
.put("TXN_ENTRY_DESC", 		new FieldMapper("txnEntryDesc"))
.put("CREATE_DATETIME", 		new FieldMapper("createDatetime"))
.put("CREATE_USER_ID", 		new FieldMapper("createUserId"))
.put("MODIFY_DATETIME", 		new FieldMapper("modifyDatetime"))
.put("MODIFY_USER_ID", 		new FieldMapper("modifyUserId"))
.put("SEAL_FLAG", 		new FieldMapper("sealFlag"))
.build();

	/**
	 * Creates new OcsreceiRepositoryImpl class Object
	 */
	public OcsreceiRepositoryImpl() {
		// OcsreceiRepositoryImpl
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            OmsRequests
	 *
	 * @return List<OmsRequests>
	 *
	 * @throws SQLException
	 */
	public List<OmsRequests> omsReqExecuteQuery(OmsRequests objSearchDao) {
		final String sql = getQuery("OCSRECEI_OMSREQ_FIND_OMS_REQUESTS");
		final RowMapper<OmsRequests> OmsRequestsRowMapper = Row2BeanRowMapper.makeMapping(sql, OmsRequests.class,
				omsRequestMapping);
		return namedParameterJdbcTemplate.query(sql, createParams(), OmsRequestsRowMapper);
	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstOmsRequests
	 *            List<OmsRequests>
	 *
	 * @throws SQLException
	 */
	public Integer omsReqUpdateOmsRequests(final List<OmsRequests> lstOmsRequests) {
		String sql = getQuery("OCSRECEI_OMSREQ_UPDATE_OMS_REQUESTS");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<>();
		for (OmsRequests omsRequests : lstOmsRequests) {
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
	 * @throws SQLException
	 */
	public Integer omsReqDeleteOmsRequests(final List<OmsRequests> lstOmsRequests) {
		String sql = getQuery("OCSRECEI_OMSREQ_DELETE_OMS_REQUESTS");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<>();
		for (OmsRequests omsRequests : lstOmsRequests) {
			parameters.add(new BeanPropertySqlParameterSource(omsRequests));
		}
		try {
			String tableName = "OMS_REQUESTS";
			String whereClause = "REQUEST_ID=:requestId";
			batchUpdatePreDeletedRows(tableName, whereClause , parameters);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method caseloadDedProfDelete", e);
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstOmsRequests.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            OffenderTransactions
	 *
	 * @return List<OffenderTransactions>
	 *
	 * @throws SQLException
	 */
	public List<OffenderTransactions> offTxnExecuteQuery(OmsRequests objSearchDao) {
		StringBuffer sqlQuery = new StringBuffer();
		MapSqlParameterSource params = new MapSqlParameterSource();
		final String sql = getQuery("OCSRECEI_OFFTXN_FIND_OFFENDER_TRANSACTIONS");
		sqlQuery.append(sql);
		final RowMapper<OffenderTransactions> OffenderTransactionsRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderTransactions.class, offenderTransactionsMapping);
		if (objSearchDao != null) {
			sqlQuery.append(
					" where CASELOAD_ID = :CASELOAD_ID AND RECEIPT_NUMBER IS NOT NULL AND EXISTS (SELECT 1 FROM TRANSACTION_TYPES TT ");
			params.addValue("CASELOAD_ID", objSearchDao.getCaseLoadId());
			if ("OCRRECEI".equals(objSearchDao.getModuleName())) {
				sqlQuery.append(" WHERE TT.TXN_USAGE <> 'D' ");

			} else if ("OCRDRECE".equals(objSearchDao.getModuleName())) {
				sqlQuery.append(" WHERE TT.TXN_USAGE = 'D' ");

			}
			sqlQuery.append(" AND OFFENDER_TRANSACTIONS.TXN_TYPE = TT.TXN_TYPE) ");
			if (objSearchDao.getNbtUserId() != null && !objSearchDao.getNbtUserId().isEmpty()) {
				sqlQuery.append(" AND modify_user_id = :NBT_USER_ID ");
				params.addValue("NBT_USER_ID", objSearchDao.getNbtUserId());
			}
			if (objSearchDao.getNbtDateFrom() != null && objSearchDao.getNbtDateTo() != null) {
				sqlQuery.append(" and txn_entry_date >= :NBT_DATE_FROM and txn_entry_date <= :NBT_DATE_TO ");
				params.addValue("NBT_DATE_FROM", objSearchDao.getNbtDateFrom());
				params.addValue("NBT_DATE_TO", objSearchDao.getNbtDateTo());

			}
			if (objSearchDao.getNbtUserId() == null && objSearchDao.getNbtDateFrom() == null
					&& objSearchDao.getNbtDateTo() == null) {
				sqlQuery.append(" and COALESCE(receipt_printed_flag, 'N') = 'N'");
			}

		}
		return namedParameterJdbcTemplate.query(sqlQuery.toString(), params, OffenderTransactionsRowMapper);
	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstOffenderTransactions
	 *            List<OffenderTransactions>
	 *
	 * @throws SQLException
	 */
	public Integer offTxnUpdateOffenderTransactions(final List<OffenderTransactions> lstOffenderTransactions) {
		String sql = getQuery("OCSRECEI_OFFTXN_UPDATE_OFFENDER_TRANSACTIONS");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<>();
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
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<Printers> cgfkOmsReqPrinterIdRecordGroup() {
		final String sql = getQuery("OCSRECEI_FIND_CGFKOMSREQPRINTERID");
		final RowMapper<Printers> mRowMapper = Row2BeanRowMapper.makeMapping(sql, Printers.class, printersMapping);

		return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<OmsModules> cgfkOmsReqModuleNameRecordGroup() {
		final String sql = getQuery("OCSRECEI_FIND_CGFKOMSREQMODULENAME");
		final RowMapper<OmsModules> mRowMapper = Row2BeanRowMapper.makeMapping(sql, OmsModules.class,
				omsModulesMapping);
		return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * omsReqPreInsert
	 *
	 * @param params
	 *
	 */
	public Long omsReqPreInsert() {
		final String sql = getQuery("OCSRECEI_OMS_REQ_PREINSERT");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams(), Long.class);
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfkchkOmsReqOmsReqPrint
	 *
	 * @param params
	 *
	 */
	public String cgfkchkOmsReqOmsReqPrint(Printers paramBean) {
		final String sql = getQuery("OCSRECEI_CGFKCHK_OMS_REQ_OMS_REQ_PRINT");
		String returnVal = null;
		try {
			returnVal = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("PRINTER_ID", paramBean.getPrinterId()), String.class);
		} catch (EmptyResultDataAccessException e) {
			returnVal = null;
		}
		return returnVal;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfkchkOmsReqOmsReqOms
	 *
	 * @param params
	 *
	 */
	public String cgfkchkOmsReqOmsReqOms(OmsModules paramBean) {
		final String sql = getQuery("OCSRECEI_CGFKCHK_OMS_REQ_OMS_REQ_OMS_M");
		String returnVal = null;
		try {
			returnVal = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("MODULE_NAME", paramBean.getModuleType()), String.class);
		} catch (EmptyResultDataAccessException e) {
			returnVal = null;
		}
		return returnVal;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * runReport
	 *
	 * @param params
	 *
	 */
	public String runReportForClient() {
		final String sql = getQuery("OCSRECEI_RUN_REPORT_FOR_CLIENT");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams(), String.class);
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * runReport
	 *
	 * @param params
	 *
	 */
	public String runReportrolepwd() {
		final String sql = getQuery("OCSRECEI_RUN_REPORT_FOR_SYS");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams(), String.class);
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * runReport
	 *
	 * @param params
	 *
	 */
	public String runReportGetPrintFormatCode(OmsModules paramBean) {
		final String sql = getQuery("OCSRECEI_RUN_REPORT_GET_PRINT_FORMAT_CODE");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("P_REPORT_NAME"), String.class);
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
		final String sql = getQuery("OCSRECEI_RUN_REPORT_ROLE_CUR");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams(), Long.class);
	}

	@Override
	public Integer omsReqInsertOmsRequests(List<OmsRequests> lstOmsRequests) {
		String sql = getQuery("OCSRECEI_OMSREQ_INSERT_OMS_REQUESTS");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<>();
		for (final OmsRequests obj : lstOmsRequests) {
			parameters.add(new BeanPropertySqlParameterSource(obj));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstOmsRequests.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

	public OffenderBookings offTxnPostQueryTwo(final Long offenderId) {
		final String sql = getQuery("OCSRECEI_OFF_TXN_POST_QUERY_TWO");
		final RowMapper<OffenderBookings> OmsRequestsRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderBookings.class, offenderBookingsMapping);
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("off_id", offenderId), OmsRequestsRowMapper);
	}

	public Integer printReceiptsTmpDeletequery(BigDecimal sessionId, String modifyUserId) {
		
		final String sql = getQuery("OCSRECEI_PRINT_RECEIPTS_TMP_DELETEQUERY");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		try {
			String tableName = "print_receipts_tmp";
			String whereClause = "session_id = :p_session_id";
			Map<String , Object> inputMap = new HashMap<String, Object>();
			inputMap.put("p_session_id", sessionId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereClause , inputMap);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method deleteDedDetails", e);
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error("printReceiptsTmpDeletequery", e);
		}
		if (returnArray.length > 0) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public Integer printReceitsInsertQuery(final List<PrintReceiptsTmp> lstOmsRequests) {
		String sql = getQuery("OCSRECEI_PRINT_RECEIPTS_TMP_INSERT_QUERY");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<>();
		for (final PrintReceiptsTmp obj : lstOmsRequests) {
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
		final String sql = getQuery("OCSRECEI_CF_PROFILE_CLIENT");
		final RowMapper<SystemProfiles> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, SystemProfiles.class,
				systemProfilesMapping);
		return namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
	}

	@Override
	public String getfcaseloadDesc(String caseloadId) {
		final String sql = getQuery("OCSRECEI_CF_CASELOAD_DESC");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("CASELOAD", caseloadId), String.class);
	}

	public Integer updateOffenderTransactionsrpt(String moduleName, Long sessionId ,String modifyUserId) {
		final String sql = getQuery("OCSRECEI_UPDATE_OFFENDER_TRANSACTIONS_RPT");
		return namedParameterJdbcTemplate.update(sql, createParams("from_module", moduleName, "session_id", sessionId , "modify_user_id" , modifyUserId));
	}

	public Integer printReceiptsTmpDeletequeryRpt(String moduleName, Long sessionId, String modifyUserId) {
		final String sql = getQuery("OCSRECEI_PRINT_RECEIPTS_TMP_DELETE_QUERY_RPT");
		try {
			String tableName = "print_receipts_tmp";
			String whereClause = "module_name = :module_name  AND session_id = :p_session_id";
			Map<String , Object> inputMap = new HashMap<String, Object>();
			inputMap.put("module_name", moduleName);
			inputMap.put("session_id", sessionId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereClause , inputMap);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method printReceiptsTmpDeletequeryRpt", e);
		}
		return namedParameterJdbcTemplate.update(sql,
				createParams("module_name", moduleName, "p_session_id", sessionId));
	}

	@Override
	public List<ocsreceiReportsBean> getReportData(OmsRequests bean) {
		final String sql = getQuery("OCSRECEI_REPORTS_QUERY");
		List<ocsreceiReportsBean> returnList = new ArrayList<>();

		final RowMapper<ocsreceiReportsBean> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				ocsreceiReportsBean.class, repBeanMapping);
		try {
			
			returnList = namedParameterJdbcTemplate.query(sql,
					createParams("caseload", bean.getCaseLoadId(), "all_flag", bean.getDisplayFlag(), "COPY",
							bean.getNumberOfCopy(), "from_module", bean.getScreenId(), "session_id", bean.getSessionId()),
					columnRowMapper);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return returnList;
	}

	@Override
	public String numberToWord(Double amount) {
		final String sql = getQuery("OCSRECEI_NUMBER_TO_WORD");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql, createParams("TOTAMT", BigDecimal.valueOf(amount)), String.class);
		} catch (Exception e) {
			logger.error("NUMBER_TO_WORD", e);
		}
		return null;
	}
	
	@Override
	public String getOffenderIdData(final String caseloadId, final Long offenderId,String userId) {
		final String sql = getQuery("OCDBRECI_GET_OFFENDER_ID_DISPLAY");
		String ofId = null;
		try {
			ofId = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("CASELOAD", caseloadId, "OFFENDERID", offenderId,"USERID",userId), String.class);
		} catch (Exception e) {
			return ofId;
		}
		return ofId;
	}
	

	@Override
	public PrintReceiptsTmp getPrintReceiptsBySessionid( BigDecimal sessionId) {
		String sql = getQuery("OCSRECEI_GET_PRINT_RECEIPTS_BY_SESSIONID");
		PrintReceiptsTmp temp = new PrintReceiptsTmp();
		
		final RowMapper<PrintReceiptsTmp> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, PrintReceiptsTmp.class,
				omsRequestMapping);
		try {
			
			temp = namedParameterJdbcTemplate.queryForObject(sql, createParams("sessionId", sessionId), columnRowMapper);
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return temp;
	}


}

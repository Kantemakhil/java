package net.syscon.s4.pkgs.common.impl;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.Caseloads;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.AccountCodes;
import net.syscon.s4.im.beans.BedAssignmentHistories;
import net.syscon.s4.inst.classification.beans.OiiclassClassInquiry;
import net.syscon.s4.pkgs.common.CommonRepository;

@Repository
public class CommonRepositoryImpl extends RepositoryBase implements CommonRepository {

	private static Logger logger = LogManager.getLogger(CommonRepositoryImpl.class.getName());

	private final Map<String, FieldMapper> oiiclassMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("offender_book_id", new FieldMapper("offenderBookID"))
			.put("offender_id_display", new FieldMapper("offenderIdDisplay"))
			.put("booking_no", new FieldMapper("bookingNo")).put("OFFENDER_NAME", new FieldMapper("offenderName"))
			.put("LOCATION", new FieldMapper("location"))
			.put("OMS_INTAKE_GET_OFFENDER_SUPERVISION", new FieldMapper("omsIntakeGetOffenderSupervision"))
			.put("ASSESSMENT_TYPE", new FieldMapper("assessmentType"))
			.put("ASSESSMENT_ID", new FieldMapper("assessmentId")).put("SCHEDULE_DATE", new FieldMapper("scheduleDate"))
			.put("CURRENT_LEVEL", new FieldMapper("currentLevel")).put("P_AGY_LOC_ID", new FieldMapper("pAgyLocId"))
			.put("P_ASSESSMENT_ID", new FieldMapper("pAssessmentId")).put("P_CASELOAD", new FieldMapper("pCaseload"))
			.put("P_LOCATION", new FieldMapper("pLocation")).build();

	private final Map<String, FieldMapper> accCodesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CASELOAD_ID", new FieldMapper("caseloadId")).put("ACCOUNT_CODE", new FieldMapper("accountCode"))
			.put("CASELOAD_CURRENT_ACCOUNT_ID", new FieldMapper("caseloadCurrentAccountId"))
			.put("ACCOUNT_PERIOD_ID", new FieldMapper("accountPeriodId"))
			.put("CURRENT_BALANCE", new FieldMapper("currentBalance"))
			.put("MODIFY_USER_ID", new FieldMapper("modifyUserId")).put("MODIFY_DATE", new FieldMapper("modifyDate"))

			.put("ACCOUNT_PERIOD_STATUS", new FieldMapper("accountPeriodStatus"))
			.put("CLOSING_USER_ID", new FieldMapper("closingUserId"))
			.put("CLOSING_DATE", new FieldMapper("closingDate")).build();

	private final Map<String, FieldMapper> caseloadMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CASELOAD_ID", new FieldMapper("caseloadId")).build();

	@Override
	public String getImageColumnCur() {
		final String sql = getQuery("IMAGE_COLUMN_CUR");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams(), String.class);
	}

	@Override
	public String getResultImageMarkExist(final BigDecimal pOffenderBookId, final String pImageObjectType,
			final String pMarkType, final String pBodyPart, final BigDecimal pObjectSeq, final String lvColumnName) {
		List<String> returnString = null;
		final String sql = getQuery("GET_RESULT_IMAGE_MARK_EXISTS");
		returnString = namedParameterJdbcTemplate.query(sql,
				createParams("P_OFFENDER_BOOK_ID", pOffenderBookId, "P_IMAGE_OBJECT_TYPE", pImageObjectType,
						"P_MARK_TYPE", pMarkType, "P_BODY_PART", pBodyPart, "P_OBJECT_SEQ", pObjectSeq,
						"LV_COLUMN_NAME", lvColumnName),new RowMapper<String>() {
				    public String mapRow(ResultSet rs, int rowNum) throws SQLException {
				        return rs.getString(1);
				    }
				});
		
		if(returnString.isEmpty())
			return "N";
		else 
			return "Y";
	}

	@Override
	public Integer getCountForCreateTabOne() {
		final String sql = getQuery("GET_COUNT_USER_TABLES_ONE");
		Integer retCount = 0;
		try {
			retCount = namedParameterJdbcTemplate.queryForObject(sql, createParams(), Integer.class);
		} catch (Exception e) {
			logger.error("getCountForCreateTabOne :" + e);
		}
		return retCount;
	}

	@Override
	public void createAgencyTab(final String query) {
		final String sql = query;
		try {
			namedParameterJdbcTemplate.execute(sql, new PreparedStatementCallback<Boolean>() {
				@Override
				public Boolean doInPreparedStatement(final PreparedStatement ps)
						throws SQLException, DataAccessException {
					return ps.execute();
				}
			});
		} catch (Exception e) {
			logger.error("createAgencyTab :" + e);
		}
	}

	@Override
	public Integer getCountForCreateTabTwo() {
		final String sql = getQuery("GET_COUNT_USER_TABLES_TWO");
		Integer retCount = 0;
		try {
			retCount = namedParameterJdbcTemplate.queryForObject(sql, createParams(), Integer.class);
		} catch (Exception e) {
			logger.error("getCountForCreateTabTwo :" + e);
		}
		return retCount;
	}


	@Override
	public Integer unlockFormModule(final String caseloadId, final String moduleName, final String user) {
		final String sql = getQuery("UNLOCK_FORM_MODULE");
		Integer retVal = 0;
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "LOCKED_MODULES";
			String whereCondition = "MODULE_NAME = :P_MODULE_NAME AND CASELOAD_ID= :P_CASELOAD AND USER_ID = :P_USER";
			inputMap.put("P_MODULE_NAME", moduleName);
			inputMap.put("P_CASELOAD", caseloadId);
			inputMap.put("P_USER", user);
			inputMap.put("modifyUserId", user);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in methodname" + e.getMessage());
		}
		try {
			retVal = namedParameterJdbcTemplate.update(sql,
					createParams("P_MODULE_NAME", moduleName, "P_CASELOAD", caseloadId, "P_USER", user));
		} catch (DataAccessException dae) {
			logger.error("unlockFormModule :" + dae);
		}
		return retVal;
	}

	@Override
	public Long getMaxOffBookId(final Long offenderId) {
		final String sql = getQuery("GET_MAX_OFFENDER_BOOK_ID");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("P_OFF_ID", offenderId), Long.class);
	}

	@Override
	public Long getMaxTxnEntrySeq(final Integer txnId) {
		final String sql = getQuery("GET_MAX_TXN_ENTRY_SEQ");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("P_TXN_ID", txnId), Long.class);
	}

	@Override
	public Date getHoldUntilDate(final Integer holdDays) {
		final String sql = getQuery("GET_HOLD_UNTIL_DATE");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("P_HOLD_DAYS", holdDays), Date.class);
	}

	@Override
	public Integer insertOffenderTransactions(final Integer txnId, final Long pTxnEntrySeq, final String caseloadId,
			final Long offenderId, final Long pOffBookId, final String pTxnPostingType, final String pTxnType,
			final String txnEntryDesc, final Double txnEntryAmount, final String subAccountType,
			final String pTxnAdjustedFlag, final String pHoldClearFlag, final Integer holdNumbers,
			final Date pHoldUntilDate, final String txnReferenceNumber, final String txnType, final String userName) {
		final String sql = getQuery("INSERT_INTO_OFFENDER_TRANSACTIONS");
		final Map<String, Object> map = new HashMap<String, Object>();
		map.put("P_TXN_ID", txnId);
		map.put("P_TXN_ENTRY_SEQ", pTxnEntrySeq);
		map.put("P_CSLD_ID", caseloadId);
		map.put("P_OFF_ID", offenderId);
		map.put("P_OFF_BOOK_ID", pOffBookId);
		map.put("P_TXN_POSTING_TYPE", pTxnPostingType);
		map.put("P_TXN_TYPE", pTxnType);
		map.put("P_TXN_DESC", txnEntryDesc);
		map.put("P_TOT_AMT", txnEntryAmount);
		map.put("P_SUB_ACCOUNT_TYPE", subAccountType);
		map.put("P_TXN_ADJUSTED_FLAG", pTxnAdjustedFlag);
		map.put("P_HOLD_CLEAR_FLAG", pHoldClearFlag);
		map.put("P_HOLD_NUMBER", holdNumbers);
		map.put("P_HOLD_UNTIL_DATE", pHoldUntilDate);
		map.put("P_TXN_REF_NUM", txnReferenceNumber);
		map.put("P_ORG_TXN_TYPE", txnType);
		map.put("CREATEUSERID", userName);
		return namedParameterJdbcTemplate.update(sql, map);
	}

	@Override
	public Integer updateOffenderTrustAccounts(final Double txnEntryAmount, final Long offenderId,
			final String caseloadId, final String userName) {
		final String sql = getQuery("UPDATE_OFFENDER_TRUST_ACCOUNTS_A");
		return namedParameterJdbcTemplate.update(sql, createParams("P_TOT_AMT", txnEntryAmount, "P_OFF_ID", offenderId,
				"P_CSLD_ID", caseloadId, "modifyUserId", userName));
	}

	@Override
	public Integer updateOffenderSubAccounts(final Double txnEntryAmount, final Long offenderId,
			final String caseloadId, final String subAccountType, final String userName) {
		final String sql = getQuery("UPDATE_OFFENDER_SUB_ACCOUNTS_B");
		return namedParameterJdbcTemplate.update(sql, createParams("P_TOT_AMT", txnEntryAmount, "P_OFF_ID", offenderId,
				"P_CSLD_ID", caseloadId, "P_SUB_ACCOUNT_TYPE", subAccountType, "MODIFYUSERID", userName));
	}

	@Override
	public BigDecimal getScheduleDaysCur() {
		final String sql = getQuery("SCHEDULE_DAYS_CUR");
		BigDecimal days = null;
		try {
			days = namedParameterJdbcTemplate.queryForObject(sql, createParams(), BigDecimal.class);
		} catch (Exception e) {
			logger.error("getScheduleDaysCur :" + e);
		}
		return days;
	}

	@Override
	public List<OiiclassClassInquiry> oiiclassClassInquirySecondSelect(final OiiclassClassInquiry objSearchDao) {
		 String sql = null;	
			if(objSearchDao.getCaseloadType().equalsIgnoreCase("INST")) {
				 sql = getQuery("SELECT_OF_DUE_FOR_INITIAL");
						}
			else if(objSearchDao.getCaseloadType().equalsIgnoreCase("COMM")) {
			 sql = getQuery("SELECT_OF_DUE_FOR_INITIAL_COMM");

			} 
		List<OiiclassClassInquiry> returnList = new ArrayList<OiiclassClassInquiry>();
		final RowMapper<OiiclassClassInquiry> rowMapper = Row2BeanRowMapper.makeMapping(sql, OiiclassClassInquiry.class,
				oiiclassMapping);
		if(objSearchDao!=null && objSearchDao.getpAgyLocId()!=null) {
			if("".equalsIgnoreCase(objSearchDao.getpAgyLocId())) {
				objSearchDao.setpAgyLocId(null);
			}
		}
		try {
			returnList = namedParameterJdbcTemplate.query(sql,
					createParams("P_FROM_DATE", objSearchDao.getpFromDate(), "P_TO_DATE", objSearchDao.getpToDate(),
							"P_ASSESSMENT_ID", objSearchDao.getpAssessmentId(), "P_CASELOAD",
							objSearchDao.getpCaseload(), "P_AGY_LOC_ID", objSearchDao.getpAgyLocId(), "P_LOCATION",
							objSearchDao.getpLocation(),"P_USER_NAME",objSearchDao.getCreateUserId()),
					rowMapper);
		} catch (Exception e) {
			logger.error("oiiclassClassInquirySecondSelect :" + e);
		}
		return returnList;
	}

	@Override
	public List<OiiclassClassInquiry> oiiclassClassInquiryThirdSelect(final OiiclassClassInquiry objSearchDao) {
		 String sql = null;	
			if(objSearchDao.getCaseloadType().equalsIgnoreCase("INST")) {
				 sql = getQuery("SELECT_OF_DUE_FOR_REVIEW");
						}
			else if(objSearchDao.getCaseloadType().equalsIgnoreCase("COMM")) {
			 sql = getQuery("SELECT_OF_DUE_FOR_REVIEW_COMM");

			} 
		List<OiiclassClassInquiry> returnList = new ArrayList<OiiclassClassInquiry>();
		final RowMapper<OiiclassClassInquiry> rowMapper = Row2BeanRowMapper.makeMapping(sql, OiiclassClassInquiry.class,
				oiiclassMapping);
		if(objSearchDao!=null && objSearchDao.getpAgyLocId()!=null) {
			if("".equalsIgnoreCase(objSearchDao.getpAgyLocId())) {
				objSearchDao.setpAgyLocId(null);
			}
		}
		try {
			returnList = namedParameterJdbcTemplate.query(sql,
					createParams("P_FROM_DATE", objSearchDao.getpFromDate(), "P_TO_DATE", objSearchDao.getpToDate(),
							"P_ASSESSMENT_ID", objSearchDao.getpAssessmentId(), "P_CASELOAD",
							objSearchDao.getpCaseload(), "P_AGY_LOC_ID", objSearchDao.getpAgyLocId(), "P_LOCATION",
							objSearchDao.getpLocation(),"P_USER_NAME",objSearchDao.getCreateUserId()),
					rowMapper);
		} catch (Exception e) {
			logger.error("oiiclassClassInquiryThirdSelect :" + e);
		}
		return returnList;
	}

	@Override
	public List<OiiclassClassInquiry> oiiclassClassInquiryFirstSelect(final OiiclassClassInquiry objSearchDao) {
		 String sql = null;	
		if(objSearchDao.getCaseloadType().equalsIgnoreCase("INST")) {
			 sql = getQuery("SELECT_OF_OVERDUE");
					}
		else if(objSearchDao.getCaseloadType().equalsIgnoreCase("COMM")) {
		 sql = getQuery("SELECT_OF_OVERDUE_COMM");

		} 
		List<OiiclassClassInquiry> returnList = new ArrayList<OiiclassClassInquiry>();
		final RowMapper<OiiclassClassInquiry> rowMapper = Row2BeanRowMapper.makeMapping(sql, OiiclassClassInquiry.class,
				oiiclassMapping);
		if(objSearchDao!=null && objSearchDao.getpAgyLocId()!=null) {
			if("".equalsIgnoreCase(objSearchDao.getpAgyLocId())) {
				objSearchDao.setpAgyLocId(null);
			}
		}
		try {
			returnList = namedParameterJdbcTemplate.query(sql,
					createParams("P_ASSESSMENT_ID", objSearchDao.getpAssessmentId(), "P_CASELOAD",
							objSearchDao.getpCaseload(), "P_AGY_LOC_ID", objSearchDao.getpAgyLocId(), "P_LOCATION",
							objSearchDao.getpLocation(),"P_USER_NAME",objSearchDao.getCreateUserId(),"P_FROM_DATE", objSearchDao.getpFromDate(), 
							"P_TO_DATE", objSearchDao.getpToDate()),
					rowMapper);
		} catch (Exception e) {
			logger.error("oiiclassClassInquiryFirstSelect :" + e);
		}
		return returnList;
	}

	@Override
	public String getCurrentCur(final String userName) {
		final String sql = getQuery("GET_CURRENT_CUR");
		String result = null;
		try {
			result = namedParameterJdbcTemplate.queryForObject(sql, createParams("userName", userName), String.class);
		} catch (Exception e) {
			logger.error("GET_CURRENT_CUR :" + e);
		}
		return result;
	}

	@Override
	public Integer updateStaffMembers(final String pCaseLoadId, final String userName) {
		final String sql = getQuery("COMM_UPDATE_STAFF_MEMBERS");
		Integer count = null;
		try {
			count = namedParameterJdbcTemplate.update(sql,
					createParams("P_CASELOAD_ID", pCaseLoadId, "MODIFY_USER_ID", userName));
		} catch (DataAccessException e) {
			logger.error("UPDATE_STAFFMEMBERS :" + e);
		}
		return count;
	}

	@Override
	public List<Caseloads> getLCaseloadsCur(final String caseloadId) {
		final String sql = getQuery("L_CASELOADS_CUR");
		List<Caseloads> retList = new ArrayList<Caseloads>();
		final RowMapper<Caseloads> rowMapper = Row2BeanRowMapper.makeMapping(sql, Caseloads.class, caseloadMapping);
		try {
			retList = namedParameterJdbcTemplate.query(sql, createParams("p_caseload_id", caseloadId), rowMapper);
		} catch (Exception e) {
			logger.error("getLCaseloadsCur :" + e);
		}
		return retList;
	}

	@Override
	public Long getLCurrentPeriodCur(final Date pDate) {
		final String sql = getQuery("L_CURRENT_PERIOD_CUR_NEW");
		Long retVal = null;
		try {
			retVal = namedParameterJdbcTemplate.queryForObject(sql, createParams("P_DATE", pDate), Long.class);
		} catch (Exception e) {
			logger.error("getLCurrentPeriodCur :" + e);
			retVal = null;
		}
		return retVal;
	}

	@Override
	public Long getLLastClosedCur(final String caseloadId) {
		final String sql = getQuery("L_LAST_CLOSED_CUR_NEW");
		Long retVal = null;
		try {
			retVal = namedParameterJdbcTemplate.queryForObject(sql, createParams("P_CASELOAD_ID", caseloadId),
					Long.class);
		} catch (Exception e) {
			logger.error("getLLastClosedCur :" + e);
		}
		return retVal;
	}

	@Override
	public List<AccountCodes> getLCsldCurrAcctBaseCur(final String pCaseloadId, final Integer pAccountCode,
			final Long pAccountPeriodId) {
		final String sql = getQuery("L_CSLD_CURR_ACCT_BASE_CUR_NEW");
		List<AccountCodes> retList = new ArrayList<AccountCodes>();
		final RowMapper<AccountCodes> rowMapper = Row2BeanRowMapper.makeMapping(sql, AccountCodes.class,
				accCodesMapping);
		try {
			retList = namedParameterJdbcTemplate.query(sql, createParams("P_ACCOUNT_CODE", pAccountCode,
					"P_CASELOAD_ID", pCaseloadId, "P_ACCOUNT_PERIOD_ID", pAccountPeriodId), rowMapper);
		} catch (Exception e) {
			logger.error("getLCsldCurrAcctBaseCur :" + e);
		}
		return retList;
	}

	@Override
	public List<AccountCodes> getLCsldCurrAcctTxnsCur(final String pCaseloadId, final Integer pAccountCode,
			final Long pLastPeriodId, final Long pCurrentPeriodId, final String userName) {
		final String sql = getQuery("COMMON_L_CSLD_CURR_ACCT_TXNS_CUR");
		List<AccountCodes> retList = new ArrayList<AccountCodes>();
		final RowMapper<AccountCodes> rowMapper = Row2BeanRowMapper.makeMapping(sql, AccountCodes.class,
				accCodesMapping);
		try {
			retList = namedParameterJdbcTemplate
					.query(sql,
							createParams("P_ACCOUNT_CODE", pAccountCode, "P_CASELOAD_ID", pCaseloadId,
									"P_LAST_PERIOD_ID", pLastPeriodId, "P_CURRENT_PERIOD_ID", pCurrentPeriodId, "userName", userName),
							rowMapper);
		} catch (Exception e) {
			logger.error("getLCsldCurrAcctTxnsCur :" + e);
		}
		return retList;
	}

	@Override
	public List<AccountCodes> getLCsldAcctPeriodsCur(final String pCaseloadId, final Long pAccountPeriodId, final String userName) {
		final String sql = getQuery("COMMON_L_CSLD_ACCT_PERIODS_CUR");
		List<AccountCodes> retList = new ArrayList<AccountCodes>();
		final RowMapper<AccountCodes> rowMapper = Row2BeanRowMapper.makeMapping(sql, AccountCodes.class,
				accCodesMapping);
		try {
			retList = namedParameterJdbcTemplate.query(sql,
					createParams("p_caseload_id", pCaseloadId, "p_account_period_id", pAccountPeriodId, "userName", userName), rowMapper);
		} catch (Exception e) {
			logger.error("getLCsldAcctPeriodsCur :" + e);
		}
		return retList;
	}

	@Override
	public List<AccountCodes> getLCsldAcctSummCur(final String pCaseloadId, final Integer pAccountCode,
			final Long pAccountPeriodId, final String userName) {
		final String sql = getQuery("COMMON_L_CSLD_ACCT_SUMM_CUR");
		List<AccountCodes> retList = new ArrayList<AccountCodes>();
		final RowMapper<AccountCodes> rowMapper = Row2BeanRowMapper.makeMapping(sql, AccountCodes.class,
				accCodesMapping);
		try {
			retList = namedParameterJdbcTemplate.query(sql, createParams("P_CASELOAD_ID", pCaseloadId, "P_ACCOUNT_CODE",
					pAccountCode, "P_ACCOUNT_PERIOD_ID", pAccountPeriodId, "userName", userName), rowMapper);
		} catch (Exception e) {
			logger.error("getLCsldAcctSummCur :" + e);
		}
		return retList;
	}

	@Override
	public Integer insertCaseloadCurrentAccountsBase(final String caseloadId, final Integer accountCode,
			final Long lCurrentPeriodId, final Date cSysdate, final String createUserId) {
		final String sql = getQuery("INSERT_INTO_CASELOAD_CURRENT_ACCOUNTS_BASE");
		Integer retVal = 0;
		try {
			retVal = namedParameterJdbcTemplate.update(sql,
					createParams("CASELOAD_ID", caseloadId, "ACCOUNT_CODE", accountCode, "L_CURRENT_PERIOD_ID",
							lCurrentPeriodId, "C_SYSDATE", cSysdate, "CREATEUSERID", createUserId));
		} catch (DataAccessException e) {
			logger.error("insertCaseloadCurrentAccountsBase :" + e);
		}
		return retVal;
	}

	@Override
	public Integer insertCaseloadCurrentAccountsTxns(final AccountCodes txns) {
		final String sql = getQuery("INSERT_INTO_CASELOAD_CURRENT_ACCOUNTS_TXNS");
		Integer retVal = 0;
		try {
			retVal = namedParameterJdbcTemplate.update(sql,
					createParams("CASELOAD_CURRENT_ACCOUNT_ID", txns.getCaseloadCurrentAccountId(), "CASELOAD_ID",
							txns.getCaseloadId(), "ACCOUNT_CODE", txns.getAccountCode(), "ACCOUNT_PERIOD_ID",
							txns.getAccountPeriodId(), "CURRENT_BALANCE", txns.getCurrentBalance(), "MODIFY_DATE",
							txns.getModifyDate(), "CREATEUSERID", txns.getCreateUserId()));
		} catch (DataAccessException e) {
			logger.error("insertCaseloadCurrentAccountsTxns :" + e);
		}
		return retVal;
	}

	@Override
	public Integer insertCaseloadAccountPeriods(final AccountCodes periods) {
		final String sql = getQuery("INSERT_INTO_CASELOAD_ACCOUNT_PERIODS");
		Integer retVal = 0;
		try {
			retVal = namedParameterJdbcTemplate.update(sql,
					createParams("CASELOAD_ID", periods.getCaseloadId(), "ACCOUNT_PERIOD_ID",
							periods.getAccountPeriodId(), "ACCOUNT_PERIOD_STATUS", periods.getAccountPeriodStatus(),
							"CLOSING_USER_ID", periods.getClosingUserId(), "CLOSING_DATE", periods.getClosingDate(),
							"CREATEUSERID", periods.getCreateUserId()));
		} catch (DataAccessException e) {
			logger.error("insertCaseloadAccountPeriods :" + e);
		}
		return retVal;
	}

	@Override
	public Integer insertCaseloadAccountSummaries(final AccountCodes periods) {
		final String sql = getQuery("INSERT_INTO_CASELOAD_ACCOUNT_SUMMARIES");
		Integer retVal = 0;
		try {
			retVal = namedParameterJdbcTemplate.update(sql,
					createParams("CASELOAD_ID", periods.getCaseloadId(), "ACCOUNT_CODE", periods.getAccountCode(),
							"ACCOUNT_PERIOD_ID", periods.getAccountPeriodId(), "OPEN_BALANCE", periods.getOpenBalance(),
							"CLOSE_BALANCE", periods.getCloseBalance(), "PERIOD_TXN_AMOUNT",
							periods.getPeriodTxnAmount(), "MODIFY_DATE", periods.getModifyDate(), "CREATEUSERID",
							periods.getCreateUserId()));
		} catch (DataAccessException e) {
			logger.error("insertCaseloadAccountSummaries :" + e);
		}
		return retVal;
	}
	
	@Override
	public String getbillStatementExists(String billId) {
		final String sql = getQuery("BILL_STATEMENT_EXISTS");
		String retVal = null;
		try {
			retVal = namedParameterJdbcTemplate.queryForObject(sql, createParams("p_bill_id", billId), String.class);
		} catch (Exception e) {
			logger.error("getbillStatementExists :" + e);
		}
		return retVal;
	}

	@Override
	public Integer getBedAssSeqCur(Integer offenderBookId) {
		final String sql = getQuery("SELECT_GET_BED_ASS_SEQ_CUR");
		Integer retVal = null;
		try {
			retVal = namedParameterJdbcTemplate.queryForObject(sql, createParams("LV_OFFENDER_BOOK_ID", offenderBookId), Integer.class);
		} catch (Exception e) {
			logger.error(this.getClass().getName()+"::getBedAssSeqCur :" + e);
		}
		return retVal;
	}
	
	public Integer bedAhInsertBedAssignmentHistories(final List<BedAssignmentHistories> lstBedAssignmentHistories) {
		final String sql = getQuery("COMMON_INSERT_BED_ASSIGNMENT_HISTORIES");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (BedAssignmentHistories bedAssignmentHistories : lstBedAssignmentHistories) {
			parameters.add(new BeanPropertySqlParameterSource(bedAssignmentHistories));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstBedAssignmentHistories.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}
	
	public long agencyLocationsCount() {
		long retVal = 0;
		try {
			retVal = namedParameterJdbcTemplate.queryForObject(getQuery("COMMON_AGENCY_LOCATIONS_COUNT"), createParams(), Long.class);
		} catch (Exception e) {
			logger.error(this.getClass().getName()+"::agencyLocationsCount :" + e);
		}
		return retVal;
	} 
	
	public long saveAgencylocations() {
		long retVal = 0;
		try {
			retVal = namedParameterJdbcTemplate.update(getQuery("COMMON_INSERT_AGENCY_LOCATIONS"), createParams());
		} catch (Exception e) {
			logger.error(this.getClass().getName()+"::saveAgencylocations :" + e);
		}
		return retVal;
	}

	@Override
	public Boolean checkCallFormAccess(String userId, List<String> accessPrivileges,String callForm) {
		String qry = getQuery("COMMON_CHECK_CALL_FORM_MODULE_ACCESS");
		try {
			String accessFlag = null;
			accessFlag = namedParameterJdbcTemplate.queryForObject(qry, createParams("userId", userId, "accessPrivileges", accessPrivileges, "popUp",callForm), String.class);
			if("Y".equals(accessFlag)) {
				return true;
			}
		} catch (Exception e) {
			logger.error(this.getClass().getName() + "::userModulesList :" + e);
		}
		return false;
	}
	
	@Override
	public Boolean checkOffenderSpecificScreenAccess(String userId, List<String> accessPrivilegeList) {
		String qry = getQuery("COMMON_CHECK_OFFENDER_SPECFIC_MODULE_ACCESS");
		try {
			String accessFlag = null;
			accessFlag = namedParameterJdbcTemplate.queryForObject(qry, createParams("userId", userId, "accessPrivileges", accessPrivilegeList), String.class);
			if("Y".equals(accessFlag)) {
				return true;
			}
		} catch (Exception e) {
			logger.error(this.getClass().getName() + "::userModulesList :" + e);
		}
		return false;
	}
	
}

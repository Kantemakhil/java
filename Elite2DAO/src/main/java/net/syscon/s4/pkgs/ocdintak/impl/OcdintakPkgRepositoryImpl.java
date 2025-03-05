package net.syscon.s4.pkgs.ocdintak.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.OffenderBookingEvent;
import net.syscon.s4.common.beans.OffenderBookings;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.AccountCodes;
import net.syscon.s4.inmate.beans.OffenderDeductions;
import net.syscon.s4.inst.schedules.bean.OffenderIndSchedules;
import net.syscon.s4.pkgs.ocdintak.OcdintakPkgRepository;

@Repository
public class OcdintakPkgRepositoryImpl extends RepositoryBase implements OcdintakPkgRepository {

	static Logger logger = LogManager.getLogger(OcdintakPkgRepositoryImpl.class.getName());

	private final Map<String, FieldMapper> accCodesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DR_ACCOUNT_CODE", new FieldMapper("drAccountCode"))
			.put("B_TXN_POSTING_TYPE", new FieldMapper("bTxnPostingType"))
			.put("B_POSTING_STATUS_FLAG", new FieldMapper("bPostingStatusFlag"))
			.put("CR_ACCOUNT_CODE", new FieldMapper("crAccountCode"))
			.put("C_TXN_POSTING_TYPE", new FieldMapper("cTxnPostingType"))
			.put("C_POSTING_STATUS_FLAG", new FieldMapper("cPostingStatusFlag"))
			.put("bank_dr_account_code", new FieldMapper("bankDrAccountCode"))
			.put("D_TXN_POSTING_TYPE", new FieldMapper("dTxnPostingType"))
			.put("D_POSTING_STATUS_FLAG", new FieldMapper("dPostingStatusFlag"))
			.put("BANK_CR_ACCOUNT_CODE", new FieldMapper("bankCrAccountCode"))
			.put("E_TXN_POSTING_TYPE", new FieldMapper("txnPostingType"))
			.put("E_POSTING_STATUS_FLAG", new FieldMapper("ePostingStatusFlag"))
			.put("TXN_OPERATION_SEQ", new FieldMapper("txnOperationSeq")).build();

	private final Map<String, FieldMapper> acMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CR_ACCOUNT_CODE", new FieldMapper("accountCode"))
			.put("RECEIPT_PRODUCTION_FLAG", new FieldMapper("accountName")).build();

	@Override
	public Integer updateOffBookings(final String toAgyLocId, final OffenderBookings offbkg, final Integer staffId, final String pCommStatus, final String lvBookingType) {
		final String sql = getQuery("UPDATE_COUT_EVENTS");
		Integer count = null;
		try {
			count = namedParameterJdbcTemplate.update(sql,
					createParams("p_agy_loc_id", toAgyLocId, "p_staffid", staffId,
							"p_comm_status", pCommStatus, "p_booking_type", lvBookingType,
							"p_off_book_id", offbkg.getOffenderBookId(), "modifyUserId", offbkg.getModifyUserId()));
		} catch (DataAccessException e) {
			logger.error("updateOffBookings :" + e);
			return 0;
		}
		return count;
	}

	@Override
	public String cGetTrustActFlag(final String pCsldId) {
		final String sql = getQuery("C_GET_TRUST_ACT_FLAG");
		String retVal = null;
		try {
			retVal = namedParameterJdbcTemplate.queryForObject(sql, createParams("p_csld_id", pCsldId), String.class);
		} catch (Exception e) {
			logger.error("cGetTrustActFlag", e);
			retVal = null;
		}
		if (retVal.isEmpty() || retVal == null) {
			retVal = "N";
		}
		return retVal;
	}

	@Override
	public List<AccountCodes> getCrAcAndRecFlag(final String pCsldId) {
		final String sql = getQuery("GET_CRAC_AND_REC_FLAG");
		List<AccountCodes> retList = new ArrayList<AccountCodes>();
		final RowMapper<AccountCodes> rowMapper = Row2BeanRowMapper.makeMapping(sql, AccountCodes.class, acMapping);
		try {
			retList = namedParameterJdbcTemplate.query(sql, createParams("pCsldId", pCsldId), rowMapper);
		} catch (Exception e) {
			logger.error("getCrAcAndRecFlag", e);
			retList = null;
		}
		return retList;
	}

	@Override
	public List<AccountCodes> getPTxnPtgTypeAndSubacType(final Double crAc) {
		final String sql = getQuery("GET_PTXN_PTG_TYPE_AND_SUBAC_TYPE");
		List<AccountCodes> retList = new ArrayList<AccountCodes>();
		final RowMapper<AccountCodes> rowMapper = Row2BeanRowMapper.makeMapping(sql, AccountCodes.class, acMapping);
		try {
			retList = namedParameterJdbcTemplate.query(sql, createParams("cr_ac", crAc), rowMapper);
		} catch (Exception e) {
			logger.error("getPTxnPtgTypeAndSubacType", e);
			retList = null;
		}
		return retList;
	}

	@Override
	public String getPTxnDesc(final String pTxnType) {
		final String sql = getQuery("GET_P_TXN_DESC");
		String retVal = null;
		try {
			retVal = namedParameterJdbcTemplate.queryForObject(sql, createParams("p_txn_type", pTxnType), String.class);
		} catch (Exception e) {
			logger.error("getPTxnDesc", e);
			retVal = null;
		}
		return retVal;
	}

	@Override
	public Integer checkIfSequenceExists(final String inputStg) {
		final String sql = getQuery("CHECK_IF_SEQUENCE_EXISTS");
		Integer retVal = null;
		try {
			retVal = namedParameterJdbcTemplate.queryForObject(sql, createParams("inputStg", inputStg), Integer.class);
		} catch (Exception e) {
			logger.error("checkIfSequenceExists", e);
			retVal = null;
		}
		return retVal;
	}

	@Override
	public Integer generateReceiptNoFromSeq(final String trimCsLdId) {
		final String sql = "SELECT SEQUENCE_".concat(trimCsLdId).concat(".nextval next_seq from dual");

		Integer retVal = null;
		try {
			retVal = namedParameterJdbcTemplate.queryForObject(sql, createParams(), Integer.class);
		} catch (Exception e) {
			logger.error("generateReceiptNoFromSeq", e);
			retVal = null;
		}
		return retVal;
	}

	@Override
	public Integer generateReceiptNoFromSeqElBlock(final String trimCsLdId) {
		final String sql = "SELECT SEQUENCE_".concat(trimCsLdId).concat("_D.nextval next_seq from dual");

		Integer retVal = null;
		try {
			retVal = namedParameterJdbcTemplate.queryForObject(sql, createParams(), Integer.class);
		} catch (Exception e) {
			logger.error("generateReceiptNoFromSeqElBlock", e);
			retVal = null;
		}
		return retVal;
	}

	@Override
	public Integer getTxnId() {
		final String sql = getQuery("GET_TXN_ID");
		Integer retVal = null;
		try {
			retVal = namedParameterJdbcTemplate.queryForObject(sql, createParams(), Integer.class);
		} catch (Exception e) {
			logger.error("getTxnId", e);
			retVal = null;
		}
		return retVal;
	}

	@Override
	public Integer updateOffenSubAcc(final OffenderBookingEvent offEve) {
		final String sql = getQuery("UPDATE_OFFEN_SUB_ACC");
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		Integer retVal = 0;
		inParamMap.put("p_csld_id", offEve.getCaseloadId());
		inParamMap.put("p_off_id", offEve.getRootOffenderId());

		inParamMap.put("p_txn_id", offEve.getpTxnId());
		inParamMap.put("p_subac_type", offEve.getpSubacType());
		inParamMap.put("modifyUserId", offEve.getModifyUserId());

		try {
			retVal = namedParameterJdbcTemplate.update(sql, inParamMap);
		} catch (Exception e) {
			logger.error("updateOffenSubAcc", e);
			retVal = 0;
		}
		return retVal;
	}

	@Override
	public Integer updateOffenderTrustAccounts(final String pCsldId, final Integer pOffId, final String userName) {
		final String sql = getQuery("UPDATE_OFFENDER_TRUST_ACCOUNTS");
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		Integer retVal = 0;
		inParamMap.put("p_csld_id", pCsldId);
		inParamMap.put("p_off_id", pOffId);
		inParamMap.put("modifyUserId", userName);

		try {
			retVal = namedParameterJdbcTemplate.update(sql, inParamMap);
		} catch (Exception e) {
			logger.error("updateOffenderTrustAccounts", e);
			retVal = 0;
		}
		return retVal;
	}

	@Override
	public Integer insertOffenderTrustAccounts(final Integer pOffId, final String pCsldId, final String userName) {
		final String sql = getQuery("INSERT_OFFENDER_TRUST_ACCOUNTS");
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		Integer retVal = 0;
		inParamMap.put("p_csld_id", pCsldId);
		inParamMap.put("p_off_id", pOffId);
		inParamMap.put("createUserId", userName);

		try {
			retVal = namedParameterJdbcTemplate.update(sql, inParamMap);
		} catch (Exception e) {
			logger.error("insertOffenderTrustAccounts", e);
			retVal = 0;
		}
		return retVal;
	}

	@Override
	public Integer insertOffenderSubAccounts(final Integer pOffId, final String pCsldId, final Integer pTxnId,
			final String userName) {
		final String sql = getQuery("INSERT_OFFENDER_SUB_ACCOUNTS");
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		Integer retVal = 0;
		inParamMap.put("p_csld_id", pCsldId);
		inParamMap.put("p_off_id", pOffId);
		inParamMap.put("p_txn_id", pTxnId);
		inParamMap.put("createUserId", userName);

		try {
			retVal = namedParameterJdbcTemplate.update(sql, inParamMap);
		} catch (Exception e) {
			logger.error("insertOffenderSubAccounts", e);
			retVal = 0;
		}
		return retVal;
	}

	@Override
	public List<OffenderDeductions> dedTypeCursor(final Integer pOffId) {
		final String sql = getQuery("DED_TYPE_CURSOR");
		List<OffenderDeductions> retList = new ArrayList<OffenderDeductions>();
		final RowMapper<OffenderDeductions> mRowMapper = Row2BeanRowMapper.makeMapping(sql, OffenderDeductions.class,
				accCodesMapping);

		try {
			retList = namedParameterJdbcTemplate.query(sql, createParams("p_off_id", pOffId), mRowMapper);
		} catch (Exception e) {
			logger.error("dedTypeCursor", e);
			retList = null;
		}
		return retList;
	}

	@Override
	public String getCaseLoadCode(final String deductionType) {
		final String sql = getQuery("GET_CASE_LOAD_CODE");
		String retVal = null;
		try {
			retVal = namedParameterJdbcTemplate.queryForObject(sql, createParams("deduction_type", deductionType),
					String.class);
		} catch (Exception e) {
			logger.error("getCaseLoadCode", e);
			retVal = null;
		}
		return retVal;
	}

	@Override
	public Long getGroupId(final String deductionType) {
		final String sql = getQuery("GET_GROUP_ID");
		Long retVal = null;
		try {
			retVal = namedParameterJdbcTemplate.queryForObject(sql, createParams("deduction_type", deductionType),
					Long.class);
		} catch (Exception e) {
			logger.error("getGroupId", e);
			retVal = null;
		}
		return retVal;
	}

	@Override
	public void updateOffenderDeductions(final Long vGroupId, final String deductionType, final Integer pOffId,
			final String userName) {
		final String sql = getQuery("UPDATE_OFFENDER_DEDUCTIONS_OCDINTAK_PKG");
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		inParamMap.put("v_group_id", vGroupId);
		inParamMap.put("p_off_id", pOffId);
		inParamMap.put("deduction_type", deductionType);
		inParamMap.put("modifyUserId", userName);
		try {
			namedParameterJdbcTemplate.update(sql, inParamMap);
		} catch (Exception e) {
			logger.error("updateOffenderDeductions", e);
		}
	}

	@Override
	public Integer insertOffTxn(final OffenderBookingEvent offEve) {
		final String sql = getQuery("INSERT_OFF_TXN");
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		Integer retVal = 0;
		inParamMap.put("p_csld_id", offEve.getCaseloadId());
		inParamMap.put("p_off_id", offEve.getRootOffenderId());
		inParamMap.put("p_txn_id", offEve.getpTxnId());

		inParamMap.put("p_txn_ptg_type", offEve.getpTxnPtgType());
		inParamMap.put("p_subac_type", offEve.getpSubacType());
		inParamMap.put("p_txn_desc", offEve.getpTxnDesc());

		inParamMap.put("p_receipt_no", offEve.getvReceiptNo());
		inParamMap.put("createUserId", offEve.getCreateUserId());
		try {
			retVal = namedParameterJdbcTemplate.update(sql, inParamMap);
		} catch (Exception e) {
			logger.error("insertOffTxn", e);
			retVal = 0;
		}
		return retVal;
	}

	@Override
	public Integer instOffBooking(final OffenderBookingEvent object) {
		final String sql = getQuery("INST_OFF_BOOKING");
		Integer genSeq = 0;
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		inParamMap.put("P_BOOK_ID", object.getOffenderBookId());
		inParamMap.put("P_BEGIN_DATE", object.getEventDate());
		inParamMap.put("P_BOOKING_NO", object.getNbtOffenderBookId2());
		inParamMap.put("P_OFFENDER_ID", object.getOffenderId());
		inParamMap.put("P_ROOT_OFF_ID", object.getRootOffenderId());
		inParamMap.put("P_DISCLOSURE_FLAG", "N");
		inParamMap.put("P_IN_OUT_STATUS", object.getInOutStatus());
		inParamMap.put("P_BOOKING_STATUS", "O");
		inParamMap.put("P_YOUTH_ADULT_CODE", "N");
		inParamMap.put("P_BOOKING_TYPE", object.getBookingType());
		inParamMap.put("P_CREATE_AGY_LOC_ID", object.getToAgyLocId());
		inParamMap.put("P_BOOKING_CREATED_DATE", object.getCreateDatetime());
		inParamMap.put("P_STAFFID", object.getStaffId());
		inParamMap.put("P_CASELOAD_ID", object.getCaseloadId());
		inParamMap.put("P_COMMUNITY_ACTIVE_FLAG", "Y");
		inParamMap.put("P_INTAKE_AGY_LOC_ID", object.getToAgyLocId());
		inParamMap.put("P_COMM_STATUS", object.getpCommStatus());
		inParamMap.put("createUserId", object.getCreateUserId());
		try {
			namedParameterJdbcTemplate.update(sql, inParamMap);
			genSeq = 1;
		} catch (DataAccessException dae) {
			logger.error("instOffBooking :" + dae);
		}
		return genSeq;
	}

	@Override
	public Integer instOffBookAgyLoc(final OffenderBookingEvent object) {
		final String sql = getQuery("INST_OFF_BOOK_AGY_LOC");
		Integer returnValue = 0;
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		inParamMap.put("P_CASELOAD_ID", object.getCaseloadId());
		inParamMap.put("P_AGY_LOC_ID", object.getToAgyLocId());
		inParamMap.put("P_OFFENDER_BOOK_ID", object.getOffenderBookId());
		inParamMap.put("P_ADDITION_DATE", object.getEventDate());
		inParamMap.put("P_ADDITION_TIME", object.getEventTime());
		inParamMap.put("P_REASON_CODE", object.getReasonCode());
		inParamMap.put("P_REMOVED_DATE", null);
		inParamMap.put("P_ADDITION_COMMENT", object.getCommentText());
		inParamMap.put("createUserId", object.getCreateUserId());
		try {
			namedParameterJdbcTemplate.update(sql, inParamMap);
			returnValue = 1;
		} catch (DataAccessException dae) {
			logger.error("instOffBookAgyLoc :" + dae);
		}
		return returnValue;
	}

	@Override
	public Integer vOffenderAllSchedules(final OffenderIndSchedules object) {
		final String sql = getQuery("V_OFFENDER_ALL_SCHEDULES");
		return namedParameterJdbcTemplate.update(sql, new BeanPropertySqlParameterSource(object));
	}
	@Override
	public Integer getLatestBooking(OffenderBookingEvent obj) {
		Integer returnValue = null;
		String sql = getQuery("GET_OFFENDER_LATEST_BOOKING");
		try {
			returnValue=namedParameterJdbcTemplate.queryForObject(sql, createParams("rootOffenderId", obj.getRootOffenderId()),
					Integer.class);
		} catch (Exception dae) {
			logger.error("getLatestBooking :" + dae);
		}
		return returnValue;
	}
}
package net.syscon.s4.pkgs.payment_schedules.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.cf.offendertransactions.beans.OffenderPaymentPlan;
import net.syscon.s4.common.beans.OffenderPaymentSchedules;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.inmate.beans.OffenderDeductions;
import net.syscon.s4.pkgs.payment_schedules.PaymentSchedulesRepository;

@Repository
public class PaymentSchedulesRepositoryImpl extends RepositoryBase implements PaymentSchedulesRepository {

	private static Logger logger = LogManager.getLogger(PaymentSchedulesRepositoryImpl.class.getName());
	final Map<String, FieldMapper> courtCasesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("MAX_TOTAL_AMOUNT", new FieldMapper("maxTotalAmount"))
			.put("DEDUCTION_AMOUNT", new FieldMapper("deductionAmount"))
			.build();

	private final Map<String, FieldMapper> offPaymentSchMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("PAYMENT_PLAN_ID", new FieldMapper("paymentPlanId"))
			.put("PAYMENT_PLAN_SEQ", new FieldMapper("paymentPlanSeq"))
			.put("PAYMENT_DATE", new FieldMapper("paymentDate")).build();

	private static final String N = "N";
	private static final String Y = "Y";

	@Override
	public String getGroupCode(final BigDecimal groupId) {
		final String sql = getQuery("PAYMENT_SCHEDULES_GET_GROUP_CODE");
		String retVal = null;
		try {
			retVal = namedParameterJdbcTemplate.queryForObject(sql, createParams("group_id", groupId), String.class);
		} catch (Exception e) {
			logger.error("getGroupCode", e);
			retVal = null;
		}
		return retVal;

	}

	@Override
	public String chkRecursive(final BigDecimal offenderId, final String informationNumber, final BigDecimal groupId) {
		final String sql = getQuery("PAYMENT_SCHEDULES_CHK_RECURSIVE");
		String retVal = N;
		Integer count = 0;
		try {
			count = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("grp_id", groupId, "off_id", offenderId, "v_info", informationNumber),
					Integer.class);
		} catch (Exception e) {
			logger.error("chkRecursive", e);
		}
		if (count == 0) {
			retVal = N;
		} else {
			retVal = Y;
		}
		return retVal;
	}

	@Override
	public BigDecimal getRecursiveAmt(final BigDecimal offenderId, final String informationNumber,
			final BigDecimal groupId) {
		final String sql = getQuery("PAYMENT_SCHEDULES_GET_RECURSIVE_AMT");
		BigDecimal retVal = null;
		try {
			retVal = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("grp_id ", groupId, "off_id ", offenderId, "v_info ", informationNumber),
					BigDecimal.class);
		} catch (Exception e) {
			logger.error("getRecursiveAmt", e);
			retVal = null;
		}
		return retVal;

	}

	@Override
	public List<OffenderDeductions> paymentSchedulesSelect(final BigDecimal offenderId, final String informationNumber,
			final BigDecimal groupId) {
		final String sql = getQuery("PAYMENT_SCHEDULES_SELECT");
		final RowMapper<OffenderDeductions> courtCasesRowMapper = Row2BeanRowMapper.makeMapping(sql, OffenderDeductions.class,
				courtCasesMapping);
		List<OffenderDeductions> obj = new ArrayList<OffenderDeductions>();
		try {
			obj=namedParameterJdbcTemplate.query(sql,
					createParams("ni_offender_id", offenderId, "vi_info", informationNumber, "ni_grp_id", groupId),
					courtCasesRowMapper);
		} 
		catch (Exception e) {
			logger.error("paymentSchedulesSelect", e);

		}
		return obj;
	}

	@Override
	public Integer offenderPaymentPlansUpdate(final BigDecimal offenderId, final String informationNumber,
			final BigDecimal groupId, final Integer owningAmount, final String userName) {
		final String sql = getQuery("OFFENDER_PAYMENT_PLANS_UPDATE");
		return namedParameterJdbcTemplate.update(sql, createParams("owing_amount", owningAmount, "ni_offender_id",
				offenderId, "vi_info", informationNumber, "ni_grp_id", groupId, "modifyUserId", userName));
	}

	@Override
	public List<OffenderPaymentPlan> fetOffPayPlan(final Long niPlanId, final Long niPlanSeq) {

		final String sql = getQuery("CURSOR_PAYPLAN");
		List<OffenderPaymentPlan> retObj = new ArrayList<OffenderPaymentPlan>();
		final RowMapper<OffenderPaymentPlan> courtCasesRowMapper = Row2BeanRowMapper.makeMapping(sql, OffenderPaymentPlan.class,
				courtCasesMapping);
		try {
			retObj = namedParameterJdbcTemplate.query(sql,
					createParams("NI_PLAN_ID", niPlanId, "NI_PLAN_SEQ", niPlanSeq), courtCasesRowMapper);
		} catch (Exception e) {
			logger.error("fetOffPayPlan :", e);
		}
		return retObj;
	}

	@Override
	public List<OffenderDeductions> fetDedCur(final BigDecimal niOffenderId, final String viInfo, final BigDecimal niGrpId) {
		final String sql = getQuery("GET_DED_CUR");
		List<OffenderDeductions> retObj = new ArrayList<OffenderDeductions>();
		final RowMapper<OffenderDeductions> rowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderDeductions.class, offPaymentSchMapping);
		try {
			retObj = namedParameterJdbcTemplate.query(sql,
					createParams("NI_OFFENDER_ID", niOffenderId, "VI_INFO", viInfo, "NI_GRP_ID", niGrpId),
					rowMapper);
		} catch (Exception e) {
			logger.error("fetDedCur :", e);
		}
		return retObj;
	}

	@Override
	public List<OffenderPaymentSchedules> fetSchCur(final Long niPlanId) {
		final String sql = getQuery("GET_SCH_CUR_NEW");
		List<OffenderPaymentSchedules> retObj = new ArrayList<OffenderPaymentSchedules>();
		final RowMapper<OffenderPaymentSchedules> rowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderPaymentSchedules.class, offPaymentSchMapping);
		try {
			retObj = namedParameterJdbcTemplate.query(sql, createParams("NI_PLAN_ID", niPlanId),
					rowMapper);
		} catch (Exception e) {
			logger.error("fetSchCur :", e);
		}
		return retObj;
	}

	@Override
	public Long getNoMonth(final Date dLastDate, final Date dFirstDate) {
		final String sql = getQuery("GET_NO_MONTHS");
		Long noMonth = null;
		try {
			noMonth = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("dLastDate", dLastDate, "dFirstDate", dFirstDate), Long.class);
		} catch (Exception e) {
			logger.error("getNoMonth :", e);
		}
		return noMonth;
	}

	@Override
	public BigDecimal getNMaxTotalAmount(final BigDecimal niOffId, final String vInfo, final BigDecimal niGrpId) {
		final String sql = getQuery("GET_N_MAX_TOTAL_AMOUNT");
		BigDecimal getMaxTotal = null;
		try {
			getMaxTotal = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("OFFENDER_ID", niOffId, "INFORMATION_NUMBER", vInfo, "GROUP_ID", niGrpId),
					BigDecimal.class);
		} catch (Exception e) {
			logger.error("getNMaxTotalAmount :", e);
		}
		return getMaxTotal;
	}

	@Override
	public Integer updateOffPaymentSchedule(final BigDecimal nTotalDiff, final Long niPlanId, final Long niPlanSeq,
			final String userName) {
		final String sql = getQuery("UPDATE_OFFENDER_PAYMENT_SCHEDULES");
		Integer retValue = 0;
		try {
			retValue = namedParameterJdbcTemplate.update(sql, createParams("N_TOTAL_DIFF", nTotalDiff, "NI_PLAN_ID",
					niPlanId, "NI_PLAN_SEQ", niPlanSeq, "modifyUserId", userName));
		} catch (DataAccessException e) {
			logger.error("updateOffPaymentSchedule :", e);
			retValue = 0;
		}
		return retValue;
	}

	@Override
	public Integer deteleOffPaymentSchedule(final Long niPlanId, final Long niPlanSeq,String modifyUserId) {
		final String sql = getQuery("DELETE_FM_OFFENDER_PAYMENT_SCHEDULES");
		Integer retValue = 0;
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "OFFENDER_PAYMENT_SCHEDULES";
			String whereCondition = "PAYMENT_PLAN_ID = :NI_PLAN_ID and PAYMENT_PLAN_SEQ = :NI_PLAN_SEQ and PAYMENT_DATE >= CURRENT_TIMESTAMP and PAYMENT_AMOUNT = 0";
			inputMap.put("modifyUserId", modifyUserId);
			inputMap.put("NI_PLAN_ID", niPlanId);
			inputMap.put("NI_PLAN_SEQ", niPlanSeq);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deteleOffPaymentSchedule " + e.getMessage());
		}
		try {
			retValue = namedParameterJdbcTemplate.update(sql,
					createParams("NI_PLAN_ID", niPlanId, "NI_PLAN_SEQ", niPlanSeq));
		} catch (DataAccessException e) {
			logger.error("deteleOffPaymentSchedule :", e);
			retValue = 0;
		}
		return retValue;
	}

	@Override
	public Date getLastDay(final Date diDate) {
		final String sql = getQuery("GET_LAST_DAY");
		Date retVal = null;
		try {
			retVal = namedParameterJdbcTemplate.queryForObject(sql, createParams("diDate", diDate), Date.class);
		} catch (Exception e) {
			logger.error("getLastDay :", e);
		}
		return retVal;
	}

	@Override
	public Date getDateValidateddDateReturn(final Integer nmonth, final BigDecimal niDay, final Integer year) {
		final String sql = getQuery("GET_DATE_VALIDATED_D_DATE_RETURN");
		Date retVal = null;
		String mon = null;
		if(nmonth<=9) {
			mon="0"+nmonth;
		}
		try {
			retVal = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("V_MONTH", mon, "NI_DAY", niDay, "V_YEAR", year), Date.class);
		} catch (Exception e) {
			logger.error("getDateValidateddDateReturn :", e);
		}
		return retVal;
	}

	@Override
	public Integer insertPaymentSchedules(final Long niPlanId, final Long niPlanSeq, final BigDecimal niGrpId,
			final BigDecimal niDedId, final Date diDate, final BigDecimal niFixedAmt, final String userName) {
		final String sql = getQuery("INSERT_PAYMENT_SCHEDULES");
		Integer retVal = 0;
		try {
			retVal = namedParameterJdbcTemplate.update(sql,
					createParams("NI_PLAN_ID", niPlanId, "NI_PLAN_SEQ", niPlanSeq, "DI_DATE", diDate, "NI_GRP_ID",
							niGrpId, "NI_DED_ID", niDedId, "NI_FIXED_AMT", niFixedAmt, "createUserId", userName));
		} catch (DataAccessException dae) {
			logger.error("insertPaymentSchedules :" + dae);
		}
		return retVal;
	}

	@Override
	public Integer setPlanEnddate(final Long niPlanId, final Long niPlanSeq, final Date diEndDate,
			final String userName) {
		final String sql = getQuery("SET_PLAN_ENDDATE");
		Integer retVal = 0;
		try {
			retVal = namedParameterJdbcTemplate.update(sql, createParams("DI_END_DATE", diEndDate, "NI_PLAN_ID",
					niPlanId, "NI_PLAN_SEQ", niPlanSeq, "modifyUserId", userName));
		} catch (DataAccessException dae) {
			logger.error("setPlanEnddate :" + dae);
		}
		return retVal;
	}

	@Override
	public Integer setRecurAmts(final Long niPlanId, final Long niPlanSeq, final BigDecimal niRecurAmt,
			final String userName) {
		final String sql = getQuery("SET_RECUR_AMTS");
		Integer retVal = 0;
		try {
			retVal = namedParameterJdbcTemplate.update(sql, createParams("NI_RECUR_AMT", niRecurAmt, "NI_PLAN_ID",
					niPlanId, "NI_PLAN_SEQ", niPlanSeq, "MODIFYUSERID", userName));
		} catch (DataAccessException dae) {
			logger.error("setRecurAmts :" + dae);
		}
		return retVal;
	}

	@Override
	public Integer setRecurAmtsOne(final Long niPlanId, final Long niPlanSeq, final String userName) {
		final String sql = getQuery("SET_RECUR_AMTS_ONE");
		Integer retVal = 0;
		try {
			retVal = namedParameterJdbcTemplate.update(sql,
					createParams("NI_PLAN_ID", niPlanId, "NI_PLAN_SEQ", niPlanSeq, "MODIFYUSERID", userName));
		} catch (DataAccessException dae) {
			logger.error("setRecurAmtsOne :" + dae);
		}
		return retVal;
	}

	@Override
	public Date setLeniencyFlagGetPaymentDate(final Long niPlanId, final Long niPlanSeq) {
		final String sql = getQuery("SET_LENIENCY_FLAG_LAST_DATE_CUR");
		Date retMaxDate = null;
		try {
			retMaxDate = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("NI_PLAN_ID", niPlanId, "NI_PLAN_SEQ", niPlanSeq), Date.class);
		} catch (Exception e) {
			logger.error("setLeniencyFlagSelect :" + e);
		}
		return retMaxDate;
	}

	@Override
	public Date setLeniencyFlagGetEndDate(final Long niPlanId, final Long niPlanSeq) {
		final String sql = getQuery("SET_LENIENCY_FLAG_PAY_PLN_CUR");
		Date retEndDate = null;
		try {
			retEndDate = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("NI_PLAN_ID", niPlanId, "NI_PLAN_SEQ", niPlanSeq), Date.class);
		} catch (Exception e) {
			logger.error("setLeniencyFlagGetEndDate :" + e);
		}
		return retEndDate;
	}

	@Override
	public List<SystemProfiles> getSystemProfile(final String viProfileType, final String viProfileCode) {
		final String sql = getQuery("GET_SYSTEM_PROFILE");
		List<SystemProfiles> obj = new ArrayList<SystemProfiles>();
		final RowMapper<SystemProfiles> rowMapper = Row2BeanRowMapper.makeMapping(sql,
				SystemProfiles.class, offPaymentSchMapping);
		try {
			obj = namedParameterJdbcTemplate.query(sql,
					createParams("VI_PROFILE_TYPE", viProfileType, "VI_PROFILE_CODE", viProfileCode),
					rowMapper);
		} catch (Exception e) {
			logger.error("getSystemProfile :" + e);
		}
		return obj;
	}

	@Override
	public Integer setLeniencyFlagUpdate(final Long paymentPlanId, final String userName) {
		final String sql = getQuery("SET_LENIENCY_FLAG_UPDATE");
		Integer retVal = 0;
		try {
			retVal = namedParameterJdbcTemplate.update(sql,
					createParams("PAYMENT_PLAN_ID", paymentPlanId, "modifyUserId", userName));
		} catch (DataAccessException dae) {
			logger.error("setLeniencyFlagUpdate :" + dae);
		}
		return retVal;
	}

	@Override
	public String getWeekday(final String viWkCode) {
		final String sql = getQuery("GET_WEEKDAY");
		String retVal = null;
		try {
			retVal = namedParameterJdbcTemplate.queryForObject(sql, createParams("VI_WK_CODE", viWkCode), String.class);
		} catch (Exception e) {
			logger.error("getWeekday :" + e);
		}
		return retVal;
	}

	@Override
	public List<OffenderPaymentSchedules> getPaymentSchCur(final long paymentPlanId, final long paymentPlanSeq) {
		final String sql = getQuery("PAYMENT_SCH_CUR");
		List<OffenderPaymentSchedules> returnList = new ArrayList<OffenderPaymentSchedules>();
		final RowMapper<OffenderPaymentSchedules> rowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderPaymentSchedules.class, offPaymentSchMapping);
		try {
			returnList = namedParameterJdbcTemplate.query(sql,
					createParams("NI_PLAN_ID", paymentPlanId, "NI_PLAN_SEQ", paymentPlanSeq), rowMapper);
		} catch (Exception e) {
			logger.error("getPaymentSchCur :" + e);
		}
		return returnList;
	}

	@Override
	public Integer numberOfPaymentsLeft(final Long niPlanId, final Long niPlanSeq) {
		final String sql = getQuery("NUMBER_OF_PAYMENTS_LEFT");
		Integer count = 0;
		try {
			count = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("NI_PLAN_ID", niPlanId, "NI_PLAN_SEQ", niPlanSeq), Integer.class);
		} catch (Exception e) {
			logger.error("numberOfPaymentsLeft :" + e);
		}
		return count;
	}

	@Override
	public Integer updateReschPaymentSchedules(final Long paymentPlanId, final Long paymentPlanSeq,
			final BigDecimal nFixedAmt, final Date paymentDate, final String userName) {
		final String sql = getQuery("UPDATE_RESCH_PAYMENT_SCHEDULES");
		Integer retVal = 0;
		try {
			retVal = namedParameterJdbcTemplate.update(sql,
					createParams("N_FIXED_AMT", nFixedAmt, "PAYMENT_PLAN_ID", paymentPlanId, "PAYMENT_PLAN_SEQ",
							paymentPlanSeq, "PAYMENT_DATE", paymentDate, "modifyUserId", userName));
		} catch (DataAccessException e) {
			logger.error("updateReschPaymentSchedules :" + e);
		}
		return retVal;
	}

	@Override
	public BigDecimal getMaxRecursiveAmount(final BigDecimal offenderid, final String informationNumber,
			final BigDecimal groupId) {
		final String sql = getQuery("GET_MAX_RECURSIVE_AMOUNT");
		BigDecimal retVal = null;
		try {
			retVal = namedParameterJdbcTemplate.queryForObject(sql, createParams("OFFENDER_ID", offenderid,
					"INFORMATION_NUMBER", informationNumber, "GROUP_ID", groupId), BigDecimal.class);
		} catch (Exception e) {
			logger.error("getMaxRecursiveAmount :" + e);
		}
		return retVal;
	}

	@Override
	public Integer updateReschPaymentSchedulesSecond(final Long paymentPlanId, final Long paymentPlanSeq,
			final BigDecimal maxRecurAmount, final String userName) {

		final String sql = getQuery("UPDATE_RESCH_PAYMENT_SCHEDULES_SECOND");
		Integer retVal = 0;
		try {
			retVal = namedParameterJdbcTemplate.update(sql, createParams("MAX_RECUR_AMOUNT", maxRecurAmount,
					"NI_PLAN_ID", paymentPlanId, "NI_PLAN_SEQ", paymentPlanSeq, "modifyUserId", userName));
		} catch (DataAccessException e) {
			logger.error("updateReschPaymentSchedulesSecond :" + e);
		}
		return retVal;
	}
	@Override
	public Long paymentPlanSeq(final Long paymentPlanId) {
		final String sql = getQuery("PAYMENT_PLAN_ID_SEQ");
		Long retVal = null ;
		try {
			retVal = namedParameterJdbcTemplate.queryForObject(sql, createParams("paymentPlanid", paymentPlanId),Long.class);
		} catch (Exception e) {
			logger.error("paymentPlanSeq :" + e);
		}
       return retVal;
		
	}
	

}
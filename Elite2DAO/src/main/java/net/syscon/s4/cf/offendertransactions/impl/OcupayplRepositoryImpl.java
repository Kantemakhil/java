package net.syscon.s4.cf.offendertransactions.impl;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.cf.offendertransactions.OcupayplRepository;
import net.syscon.s4.cf.offendertransactions.beans.OffenderPaymentPlan;
import net.syscon.s4.cf.offendertransactions.beans.PaymentPlanBean;
import net.syscon.s4.cf.offendertransactions.beans.PaymentPlanReportBean;
import net.syscon.s4.cf.offendertransactions.beans.PaymentPlanTransaction;
import net.syscon.s4.cf.offendertransactions.beans.VOffenderPaymentSchedule;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.OmsModules;
import net.syscon.s4.im.beans.SysDual;
import net.syscon.s4.im.beans.VTrustHeader;
import net.syscon.s4.inmate.beans.OffenderDeductions;

/**
 * Class OcupayplRepositoryImpl
 * 
 */
@Repository
public class OcupayplRepositoryImpl extends RepositoryBase implements OcupayplRepository {
	
	/**
	* Logger object used to print the log in the file
	*/
	private static Logger logger = LogManager.getLogger(OcupayplRepositoryImpl.class.getName());


	private final Map<String, FieldMapper> offPlanMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("0", new FieldMapper("0")).put("PAYMENT_PLAN_ID", new FieldMapper("paymentPlanId"))
			.put("OFFENDER_ID", new FieldMapper("offenderId"))
			.put("NVL(MAX(PAYMENT_PLAN_SEQ)", new FieldMapper(" nvl(max(paymentPlanSeq)")).build();
	private final Map<String, FieldMapper> refMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CODE", new FieldMapper("code"))
			.put("WEEKLY", new FieldMapper("weekly"))
			.put("DESCRIPTION", new FieldMapper("description"))
			.put("BI_WEEKLY", new FieldMapper("biWeekly"))
			.put("DSP_DESCRIPTION", new FieldMapper("dspDescription"))
			.put("MODE", new FieldMapper("mode"))
			.put("CASELOADID", new FieldMapper("caseloadId"))
			.put("FREQUENCY", new FieldMapper("frequency"))
			.put("DSP_DESCRIPTION4", new FieldMapper("dspDescription4"))
			.put("DSP_DESCRIPTION3", new FieldMapper("dspDescription3"))
			.put("UNLIMITED", new FieldMapper("unlimited"))
			
			.build();
	private final Map<String, FieldMapper> offDedMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("TRUST_CASELOAD_ID", new FieldMapper("trustCaseloadId"))
			.put("'1'", new FieldMapper("  '1' "))
			.put("PP_OFFENDER_ID", new FieldMapper("ppOffenderId"))
			.put("GROUP_ID", new FieldMapper("groupId"))
			.put("INFORMATION_NUMBER", new FieldMapper("informationNumber"))
			.put("OFFENDER_ID", new FieldMapper("offenderId"))
			.put("PAYMENT_PLAN_ID", new FieldMapper("paymentPlanId"))
			.put("PAYMENT_PLAN_SEQ", new FieldMapper("paymentPlanSeq"))
			.put("MAX_TOTAL_AMOUNT", new FieldMapper("maxTotalAmount"))
			.put("DEDUCTION_AMOUNT", new FieldMapper("deductionAmount"))
			.put("TOTAL_ARREARS", new FieldMapper("totalArrears"))
			.put("CASELOAD_ID", new FieldMapper("caseLoadId"))
			.build();
	
	private final Map<String, FieldMapper> OffenderPaymentPlanMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DUE_DATE", new FieldMapper("dueDate"))
			.put("INFORMATION_NUMBER", new FieldMapper("informationNumber"))
			.put("FREQUENCY", new FieldMapper("frequency"))
			.put("END_DATE", new FieldMapper("endDate"))
			.put("START_DATE", new FieldMapper("startDate"))
			.put("PAYMENTAMOUNT", new FieldMapper("paymentAmount"))
			.put("PAYMENT_FREQUENCY", new FieldMapper("paymentFrequency"))		
	.build();
	private final Map<String, FieldMapper> PaymentPlanBeanMapping = new ImmutableMap.Builder<String, FieldMapper>()
	.put("due_Date", new FieldMapper("dueDate"))
	.put("INFORMATION_NUMBER", new FieldMapper("infoNumber"))
	.put("GROUP_CODE", new FieldMapper("code"))
	.put("PAYMENT_AMOUNT", new FieldMapper("amountDues"))
	.put("PAYMENTFREQ", new FieldMapper("paymentFreq"))
	.build();
	
	private final Map<String, FieldMapper> transMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("PAYMENT_PLAN_ID", new FieldMapper("paymentPlanId"))
			.put("PAYMENT_DATE", new FieldMapper("paymentDate"))
			.put("PAYMENT_PLAN_SEQ", new FieldMapper("paymentPlanSeq"))
			.build();
	private final Map<String, FieldMapper> sysprofMapp = new ImmutableMap.Builder<String, FieldMapper>()
			.put("LTRIM(RTRIM(DESCRIPTION))", new FieldMapper(" ltrim(rtrim(description)) "))
			.put("PROFILE_VALUE", new FieldMapper(" profileValue "))
			.build();
	private final Map<String, FieldMapper> mMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CODE", new FieldMapper("code"))
			.put("DESCRIPTION", new FieldMapper("description"))
			.put("INFORMATION_NUMBER", new FieldMapper("informationNumber"))
			.put("GROUP_ID", new FieldMapper("groupId"))
			.put("name", new FieldMapper("name"))
			.build();
	private final Map<String, FieldMapper> scheduleMapp = new ImmutableMap.Builder<String, FieldMapper>()
			.put("PAYMENT_PLAN_ID", new FieldMapper("paymentPlanId"))
			.build();
	private final Map<String, FieldMapper> sysDualMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("PAYMENT_PLAN_ID", new FieldMapper("paymentPlanId"))
			.build();
	private final Map<String, FieldMapper> omsModulesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("PAYMENT_PLAN_ID", new FieldMapper("paymentPlanId"))
			.build();
	private final Map<String, FieldMapper> vthMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("OFFENDER_ID_DISPLAY", new FieldMapper("offenderIdDisplay"))
			.put("NAME", new FieldMapper("name"))
			.put("root_offender_id", new FieldMapper("rootOffenderId"))
			.build();
	
	/**
	 * Creates new OcupayplRepositoryImpl class Object
	 */
	public OcupayplRepositoryImpl() {

		/* OcupayplRepositoryImpl */
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            OffenderPaymentPlans
	 *
	 * @return List<OffenderPaymentPlans>
	 *
	 * @throws SQLException
	 */
	public List<OffenderPaymentPlan> payPlnExecuteQuery(final OffenderPaymentPlan objSearchDao) {
		final String sql = getQuery("OCUPAYPL_PAYPLN_FIND_OFFENDER_PAYMENT_PLANS");

		String preparedSql = null;
		final StringBuffer sqlQuery = new StringBuffer();
		String preSqlQuery = null;
		final MapSqlParameterSource params = new MapSqlParameterSource();
		sqlQuery.append(sql);
		if (objSearchDao != null) {
			sqlQuery.append(" WHERE ");

			if (objSearchDao.getOffenderId() != null) {
				sqlQuery.append(" OFFENDER_ID  = :offenderId " + "AND ");
				params.addValue("offenderId", objSearchDao.getOffenderId());
			}

			if (objSearchDao.getInformationNumber() != null && !objSearchDao.getInformationNumber().isEmpty()) {
				sqlQuery.append(" INFORMATION_NUMBER  = :informationNumber " + "AND ");
				params.addValue("informationNumber", objSearchDao.getInformationNumber());
			}
			if (objSearchDao.getGroupId() != null) {
				sqlQuery.append(" GROUP_ID  = :groupId " + " AND ");
				params.addValue("groupId", objSearchDao.getGroupId());
			}
			if (objSearchDao.getStartDate() != null) {
				sqlQuery.append(" START_DATE  = :startDate " + "AND ");
				params.addValue("startDate", objSearchDao.getStartDate());
			}
			if (objSearchDao.getEndDate() != null) {
				sqlQuery.append(" END_DATE  = :endDate " + " AND ");
				params.addValue("endDate", objSearchDao.getEndDate());
			}
			if (objSearchDao.getFrequency() != null && !objSearchDao.getFrequency().isEmpty()) {
				sqlQuery.append(" FREQUENCY  = :frequency " + "AND ");
				params.addValue("frequency", objSearchDao.getFrequency());
			}
			if (objSearchDao.getMonthly() != null) {
				sqlQuery.append(" MONTHLY  = :monthly " + " AND ");
				params.addValue("monthly", objSearchDao.getMonthly());
			}
			if (objSearchDao.getAmount() != null) {
				sqlQuery.append(" AMOUNT  = :amount " + "AND ");
				params.addValue("amount", objSearchDao.getAmount());
			}

		}
		preparedSql = sqlQuery.toString().trim();
		if (preparedSql.endsWith("WHERE")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 5);
		}
		if (preparedSql.endsWith("AND")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 3);
		}

		preSqlQuery = preparedSql
				.concat("ORDER BY payment_closed_date DESC, information_number DESC, group_id ASC, start_date ASC");
		final RowMapper<OffenderPaymentPlan> offenderPplRowMapper = Row2BeanRowMapper.makeMapping(preSqlQuery,
				OffenderPaymentPlan.class, offPlanMapping);
		List<OffenderPaymentPlan> returnList = new ArrayList<>();
		returnList = namedParameterJdbcTemplate.query(preSqlQuery, params, offenderPplRowMapper);
		return returnList;
	}

	/**
	 * This method is used to insert the records in the data base tables based on
	 *
	 * @param lstOffenderPaymentPlans
	 *            List<OffenderPaymentPlans>
	 *
	 * @return List<Integer>
	 *
	 * @throws SQLException
	 */

	@Override
	public Integer payPlnInsertOffenderPaymentPlans(final List<OffenderPaymentPlan> list) {

		final String sql = getQuery("OCUPAYPL_PAYPLN_INSERT_OFFENDER_PAYMENT_PLANS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<>();
		for (final OffenderPaymentPlan offPlan : list) {
			parameters.add(new BeanPropertySqlParameterSource(offPlan));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));

		if (list.size() == returnArray.length) {
			return returnArray.length;
		} else {
			return 0;
		}
	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstOffenderPaymentPlans
	 *            List<OffenderPaymentPlans>
	 *
	 * @throws SQLException
	 */
	public Integer payPlnUpdateOffenderPaymentPlans(final List<OffenderPaymentPlan> list) {
		String sql = getQuery("OCUPAYPL_PAYPLN_UPDATE_OFFENDER_PAYMENT_PLANS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffenderPaymentPlan offenderPaymentPlans : list) {
			parameters.add(new BeanPropertySqlParameterSource(offenderPaymentPlans));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (list.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to delete records from data base tables based on
	 *
	 * @param lstOffenderPaymentPlans
	 *            List<OffenderPaymentPlans>
	 *
	 * @throws SQLException
	 */
	public Integer payPlnDeleteOffenderPaymentPlans(final List<OffenderPaymentPlan> list) {
		final String sql = getQuery("OCUPAYPL_PAYPLN_DELETE_OFFENDER_PAYMENT_PLANS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffenderPaymentPlan offPlan : list) {
			parameters.add(new BeanPropertySqlParameterSource(offPlan));
		}
		try {
			String tableName = "OFFENDER_PAYMENT_PLANS";
			String whereClause = "PAYMENT_PLAN_ID = :paymentPlanId and PAYMENT_PLAN_SEQ = :paymentPlanSeq";
			batchUpdatePreDeletedRows(tableName, whereClause , parameters);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method payPlnDeleteOffenderPaymentPlans", e);
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (list.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            VOffenderPaymentSchedules
	 *
	 * @return List<VOffenderPaymentSchedules>
	 *
	 * @throws SQLException
	 */
	public List<VOffenderPaymentSchedule> paySchExecuteQuery(final VOffenderPaymentSchedule objSearchDao) {
		final String sql = getQuery("OCUPAYPL_PAYSCH_FIND_V_OFFENDER_PAYMENT_SCHEDULES");
		final RowMapper<VOffenderPaymentSchedule> schRowMapper = Row2BeanRowMapper.makeMapping(sql,
				VOffenderPaymentSchedule.class, scheduleMapp);
		return (ArrayList<VOffenderPaymentSchedule>) namedParameterJdbcTemplate.query(sql,
				createParams("payment_plan_id", objSearchDao.getPaymentPlanId()), schRowMapper);
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            PaymentPlanTransactions
	 *
	 * @return List<PaymentPlanTransactions>
	 * @throws ParseException
	 *
	 * @throws SQLException
	 */
	public List<PaymentPlanTransaction> ppTxnExecuteQuery(final PaymentPlanTransaction objSearchDao)
			throws ParseException {

		final String sql = getQuery("OCUPAYPL_PPTXN_FIND_PAYMENT_PLAN_TRANSACTIONS");
		final RowMapper<PaymentPlanTransaction> transMapper = Row2BeanRowMapper.makeMapping(sql,
				PaymentPlanTransaction.class, transMapping);
		return (ArrayList<PaymentPlanTransaction>) namedParameterJdbcTemplate.query(sql,
				createParams("PAYMENT_PLAN_ID", objSearchDao.getPaymentPlanId(), "PAYMENT_PLAN_SEQ",
						objSearchDao.getPaymentPlanSeq(), "PAYMENT_DATE", objSearchDao.getPaymentDate()),
				transMapper);
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            SystemProfiles
	 *
	 * @return List<SystemProfiles>
	 *
	 * @throws SQLException
	 */
	public List<SystemProfiles> sysPflExecuteQuery(final SystemProfiles objSearchDao) {
		final String sql = getQuery("OCUPAYPL_SYSPFL_FIND_SYSTEM_PROFILES");
		final RowMapper<SystemProfiles> sysRowMapper = Row2BeanRowMapper.makeMapping(sql, SystemProfiles.class,
				sysprofMapp);
		return (ArrayList<SystemProfiles>) namedParameterJdbcTemplate.query(sql, createParams(), sysRowMapper);
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<OffenderDeductions> cgfkPayPlnInformationNumbeRecordGroup(final Long offenderId, final String caseloadId) {
		final String sql = getQuery("OCUPAYPL_FIND_CGFKPAYPLNINFORMATIONNUMBE");
		final RowMapper<OffenderDeductions> mRowMapper = Row2BeanRowMapper.makeMapping(sql, OffenderDeductions.class,
				refMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams("OFFENDER_ID", offenderId, "CASELOADID", caseloadId), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<ReferenceCodes> cgfkPayPlnDspDescription4RecordGroup() {
		final String sql = getQuery("OCUPAYPL_FIND_CGFKPAYPLNDSPDESCRIPTION4");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				refMapping);

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
	public List<ReferenceCodes> cgfkPayPlnDspDescription3RecordGroup() {
		final String sql = getQuery("OCUPAYPL_FIND_CGFKPAYPLNDSPDESCRIPTION3");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				refMapping);

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
	public List<ReferenceCodes> cgfkPayPlnDspDescriptionRecordGroup() {
		final String sql = getQuery("OCUPAYPL_FIND_CGFKPAYPLNDSPDESCRIPTION");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				refMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * payPlnOnCheckDeleteMaster
	 *
	 * @param params
	 *
	 */
	public List<VOffenderPaymentSchedule> payPlnOnCheckDeleteMaster(final VOffenderPaymentSchedule paramBean) {
		final String sql = getQuery("OCUPAYPL_PAY_PLN_ONCHECKDELETEMASTER");
		final RowMapper<VOffenderPaymentSchedule> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				VOffenderPaymentSchedule.class, scheduleMapp);
		return (ArrayList<VOffenderPaymentSchedule>) namedParameterJdbcTemplate.query(sql, createParams(),
				columnRowMapper);
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * payPlnPreInsert
	 *
	 * @param params
	 *
	 */
	public Object payPlnPreInsert(final SysDual paramBean) {
		final String sql = getQuery("OCUPAYPL_PAY_PLN_PREINSERT");
		final RowMapper<SysDual> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, SysDual.class, sysDualMapping);
		return namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * payPlnPreInsert
	 *
	 * @param params
	 *
	 */
	public List<OffenderPaymentPlan> payPlnPreInsert(final OffenderPaymentPlan paramBean) {
		final String sql = getQuery("OCUPAYPL_PAY_PLN_PREINSERT");
		final RowMapper<OffenderPaymentPlan> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderPaymentPlan.class, offPlanMapping);
		return (ArrayList<OffenderPaymentPlan>) namedParameterJdbcTemplate.query(sql, createParams(), columnRowMapper);
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * paySchOnCheckDeleteMaster
	 *
	 * @param params
	 *
	 */
	public List<PaymentPlanTransaction> paySchOnCheckDeleteMaster(final PaymentPlanTransaction paramBean) {
		final String sql = getQuery("OCUPAYPL_PAY_SCH_ONCHECKDELETEMASTER");
		final RowMapper<PaymentPlanTransaction> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				PaymentPlanTransaction.class, transMapping);
		return (ArrayList<PaymentPlanTransaction>) namedParameterJdbcTemplate.query(sql, createParams(),
				columnRowMapper);
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * createFormGlobals
	 *
	 * @param params
	 *
	 */
	public List<OmsModules> createFormGlobals(final OmsModules paramBean) {
		final String sql = getQuery("OCUPAYPL_CREATE_FORM_GLOBALS");
		final RowMapper<OmsModules> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, OmsModules.class,
				omsModulesMapping);
		return (ArrayList<OmsModules>) namedParameterJdbcTemplate.query(sql, createParams(), columnRowMapper);
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
		final String sql = getQuery("OCUPAYPL_CGWHEN_NEW_FORM_INSTANCE");
		final RowMapper<SysDual> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, SysDual.class, sysDualMapping);
		return (ArrayList<SysDual>) namedParameterJdbcTemplate.query(sql, createParams(), columnRowMapper);
	}

	@Override
	public Long preInsertPlanId(final String informationNumber, final BigDecimal groupId, final BigDecimal offenderId) {
		Long planId = null;
		final String sql = getQuery("OCUPAYPL_PAY_PLN_PREINSERT_PLAN_ID");
		try {

			planId = namedParameterJdbcTemplate.queryForObject(sql, createParams("INFORMATION_NUMBER",
					informationNumber, "GROUP_ID", groupId, "OFFENDER_ID", offenderId), Long.class);
		} catch (Exception e) {

			planId = preInsertSequence();

		}
		return planId;
	}

	public Long preInsertSequence() {
		final String sql = getQuery("OCUPAYPL_PAY_PLN_PREINSERT_SEQ");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams(), Long.class);
	}

	@Override
	public long preInsertPlanSeq(final long paymentPlanId) {
		long planSeq = 0;
		final String sql = getQuery("OCUPAYPL_PAY_PLN_PREINSERT_SEQ_ID");
		try {

			planSeq = namedParameterJdbcTemplate.queryForObject(sql, createParams("PAYMENT_PLAN_ID", paymentPlanId),
					long.class);
		} catch (Exception e) {
			planSeq = 0;
		}
		return planSeq;
	}

	@Override
	public BigDecimal calculatePaymentAmount(final OffenderPaymentPlan searchBean) {

		return null;
	}

	@Override
	public String getGroupCode(final BigDecimal groupId) {
		Map<String, Object> returnObject = null;
		String code = null;
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[3];
		sqlParameters = new SqlParameter[] { new SqlParameter("NI_GROUP_ID", Types.NUMERIC),
				new SqlOutParameter("RETURN_VALUE", Types.VARCHAR) };
		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("PAYMENT_SCHEDULES").withFunctionName("GET_GROUP_CODE")
				.declareParameters(sqlParameters);
		inParamMap.put("NI_GROUP_ID", groupId);

		final MapSqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);

		try {
			returnObject = simpleJDBCCall.execute(inParameter);
			code = (String) returnObject.get("RETURN_VALUE");
		} catch (Exception e) {
			code = null;
		}
		return code;

	}

	@Override
	public String chkRecursive(final BigDecimal offenderId, final String informationNumber, final BigDecimal groupId) {
		Map<String, Object> returnObject = null;
		String code = null;
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[3];
		sqlParameters = new SqlParameter[] { new SqlParameter("NI_OFF_ID", Types.NUMERIC),
				new SqlParameter("V_INFO", Types.VARCHAR), new SqlParameter("NI_GRP_ID", Types.NUMERIC),
				new SqlOutParameter("RETURN_VALUE", Types.VARCHAR) };

		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("PAYMENT_SCHEDULES").withFunctionName("CHK_RECURSIVE")
				.declareParameters(sqlParameters);

		inParamMap.put("NI_OFF_ID", offenderId);
		inParamMap.put("V_INFO", informationNumber);
		inParamMap.put("NI_GRP_ID", groupId);

		final MapSqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);

		try {
			returnObject = simpleJDBCCall.execute(inParameter);
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		code = (String) returnObject.get("RETURN_VALUE");
		return code;
	}

	@Override
	public String recurtExists(final BigDecimal offenderId, final String informationNumber, final BigDecimal groupId) {

		final String sql = getQuery("OCUPAYPL_PAY_PLN_RECUR_EXISTS");
		return namedParameterJdbcTemplate.queryForObject(sql,
				createParams("INFORMATION_NUMBER", informationNumber, "GROUP_ID", groupId, "OFFENDER_ID", offenderId),
				String.class);
	}

	@Override
	public BigDecimal getRecursiveAmt(final BigDecimal offenderId, final String informationNumber,
			final BigDecimal groupId) {

		Map<String, Object> returnObject = null;
		BigDecimal code = null;
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[3];
		sqlParameters = new SqlParameter[] { new SqlParameter("NI_OFF_ID", Types.NUMERIC),
				new SqlParameter("V_INFO", Types.VARCHAR), new SqlParameter("NI_GROUP_ID", Types.NUMERIC),
				new SqlOutParameter("RETURN_VALUE", Types.VARCHAR) };
		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("PAYMENT_SCHEDULES").withFunctionName("GET_RECURSIVE_AMT")
				.declareParameters(sqlParameters);
		inParamMap.put("NI_OFF_ID", offenderId);
		inParamMap.put("V_INFO", informationNumber);
		inParamMap.put("NI_GROUP_ID", groupId);

		final MapSqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);

		try {
			returnObject = simpleJDBCCall.execute(inParameter);
			code = (BigDecimal) returnObject.get("RETURN_VALUE");
		} catch (Exception e) {
			code = null;
		}
		return code;
	}

	@Override
	public OffenderPaymentPlan getMaxPlanIdAndSeq(final BigDecimal offenderId, final String informationNumber,
			final BigDecimal groupId) {
		final String sql = getQuery("OCUPAYPL_PAY_PLN_MAX_PLAN_ID_SEQ");
		final RowMapper<OffenderPaymentPlan> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderPaymentPlan.class, offDedMapping);
		return namedParameterJdbcTemplate.queryForObject(sql,
				createParams("INFORMATION_NUMBER", informationNumber, "GROUP_ID", groupId, "OFFENDER_ID", offenderId),
				columnRowMapper);
	}

	@Override
	public BigDecimal getPaymentAmount(final BigDecimal offenderId, final String informationNumber,
			final BigDecimal groupId) {
		final String sql = getQuery("OCUPAYPL_PAY_PLN_GET_PAYMENT_AMOUNT");
		return namedParameterJdbcTemplate.queryForObject(sql,
				createParams("INFORMATION_NUMBER", informationNumber, "GROUP_ID", groupId, "OFFENDER_ID", offenderId),
				BigDecimal.class);
	}

	@Override
	public BigDecimal getPaidAmount(final BigDecimal offenderId, final String informationNumber,
			final BigDecimal groupId) {
		final String sql = getQuery("OCUPAYPL_PAY_PLN_GET_PAID_AMOUNT");
		return namedParameterJdbcTemplate.queryForObject(sql,
				createParams("INFORMATION_NUMBER", informationNumber, "GROUP_ID", groupId, "OFFENDER_ID", offenderId),
				BigDecimal.class);
	}

	@Override
	public BigDecimal originalOwingaAmount(final BigDecimal offenderId, final String informationNumber,
			final BigDecimal groupId) {
		final String sql = getQuery("OCUPAYPL_PAY_PLN_GET_OWING_AMOUNT");
		try {
		return namedParameterJdbcTemplate.queryForObject(sql,
				createParams("INFORMATION_NUMBER", informationNumber, "GROUP_ID", groupId, "OFFENDER_ID", offenderId),
				BigDecimal.class);
		}catch(EmptyResultDataAccessException e) {
			return null;
		}
	}

	@Override
	public OffenderDeductions getTotDedAmount(final BigDecimal offenderId, final String informationNumber,
			final BigDecimal groupId) {
		final String sql = getQuery("OCUPAYPL_PAY_PLN_TOT_DED_AMT");
		final RowMapper<OffenderDeductions> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderDeductions.class, offDedMapping);
		return namedParameterJdbcTemplate.queryForObject(sql,
				createParams("INFORMATION_NUMBER", informationNumber, "GROUP_ID", groupId, "OFFENDER_ID", offenderId),
				columnRowMapper);
	}

	@Override
	public BigDecimal getTotalArrears(final BigDecimal offenderId, final String informationNumber,
			final BigDecimal groupId) {
		final String sql = getQuery("OCUPAYPL_PAY_PLN_GET_TOT_ARREARS");
		return namedParameterJdbcTemplate.queryForObject(sql,
				createParams("INFORMATION_NUMBER", informationNumber, "GROUP_ID", groupId, "OFFENDER_ID", offenderId),
				BigDecimal.class);
	}

	@Override
	public String payPlnPostQuery(final BigDecimal groupId) {
		return getGroupCode(groupId);
	}

	@Override
	public String getJSStatus(final BigDecimal offenderId, final String informationNumber, final BigDecimal groupId) {
		String jsFlag = null;
		final String sql = getQuery("OCUPAYPL_PAY_PLN_JS_STATUS");
		try {
			jsFlag = namedParameterJdbcTemplate.queryForObject(sql, createParams("INFORMATION_NUMBER",
					informationNumber, "GROUP_ID", groupId, "OFFENDER_ID", offenderId), String.class);
		} catch (EmptyResultDataAccessException e) {

			jsFlag = null;
		}

		return jsFlag;
	}

	@Override
	public VOffenderPaymentSchedule postBlockPlan(final OffenderPaymentPlan searchBean) {
		final String sql = getQuery("OCUPAYPL_PAY_PLN_ARREARS");
		final RowMapper<VOffenderPaymentSchedule> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				VOffenderPaymentSchedule.class, offDedMapping);
		return namedParameterJdbcTemplate.queryForObject(sql,
				createParams("INFORMATION_NUMBER", searchBean.getInformationNumber(), "GROUP_ID",
						searchBean.getGroupId(), "OFFENDER_ID", searchBean.getOffenderId()),
				columnRowMapper);
	}

	@Override
	public int whenNewBlockInstanceSch(final long paymentPlanId) {
		final String sql = getQuery("OCUPAYPL_PAY_SCH_MOST_CURRENT");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("PAYMENT_PLAN_ID", paymentPlanId),
				Integer.class);
	}

	@Override
	public BigDecimal checkEverPaid(final long paymentPlanId, final long paymentPlanSeq) {
		final String sql = getQuery("OCUPAYPL_PAY_PLN_CHECK_EVER_PAID");
		return namedParameterJdbcTemplate.queryForObject(sql,
				createParams("PAYMENT_PLAN_ID", paymentPlanId, "PAYMENT_PLAN_SEQ", paymentPlanSeq), BigDecimal.class);
	}

	@Override
	public Integer modifyDelSchdule(final long paymentPlanId, final long paymentPlanSeq) {
		int returnData = 0;
		final String sql = getQuery("OCUPAYPL_PAY_PLN_MODIFY_DEL_SCHEDULE");
		try {
			returnData = namedParameterJdbcTemplate.update(sql,
					createParams("PAYMENT_PLAN_ID", paymentPlanId, "PAYMENT_PLAN_SEQ", paymentPlanSeq));
		} catch (Exception e) {
			logger.error("Exception :", e);
		}

		return returnData;
	}

	@Override
	public Integer deleteSchdule(final long paymentPlanId, final long paymentPlanSeq, String modifyUserId) {
		int returnData = 0;
		final String sql = getQuery("OCUPAYPL_PAY_PLN_DELTE_SCHEDULE");
		try {
			String tableName = "offender_payment_schedules";
			String whereClause = "PAYMENT_PLAN_ID = :PAYMENT_PLAN_ID AND PAYMENT_PLAN_SEQ = :PAYMENT_PLAN_SEQ";
			Map<String , Object> inputMap = new HashMap<String, Object>();
			inputMap.put("PAYMENT_PLAN_ID", paymentPlanId);
			inputMap.put("PAYMENT_PLAN_SEQ", paymentPlanSeq);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereClause , inputMap);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method deleteSchdule", e);
		}
		try {
			returnData = namedParameterJdbcTemplate.update(sql,
					createParams("PAYMENT_PLAN_ID", paymentPlanId, "PAYMENT_PLAN_SEQ", paymentPlanSeq));
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return returnData;
	}

	@Override
	public void updateOwingAmount(final BigDecimal offenderId, final String informationNumber,
			final BigDecimal groupId) {
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[3];
		sqlParameters = new SqlParameter[] { new SqlParameter("NI_OFFENDER_ID", Types.NUMERIC),
				new SqlParameter("VI_INFO", Types.VARCHAR), new SqlParameter("NI_GRP_ID", Types.NUMERIC) };
		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("PAYMENT_SCHEDULES").withProcedureName("UPDATE_OWING_AMOUNT")
				.declareParameters(sqlParameters);
		inParamMap.put("NI_OFFENDER_ID", offenderId);
		inParamMap.put("VI_INFO", informationNumber);
		inParamMap.put("NI_GRP_ID", groupId);

		final MapSqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);

		try {
			simpleJDBCCall.execute(inParameter);

		} catch (Exception e) {
			logger.error("Exception :", e);
		}

	}

	@Override
	public Integer deletePayPlan(final long paymentPlanId, final long paymentPlanSeq, String modifyUserId) {
		int value = 0;
		final String sql = getQuery("OCUPAYPL_PAY_PLN_DELETE");
		try {
			String tableName = "OFFENDER_PAYMENT_PLANS";
			String whereClause = "PAYMENT_PLAN_ID = :PAYMENT_PLAN_ID AND PAYMENT_PLAN_SEQ = :PAYMENT_PLAN_SEQ";
			Map<String , Object> inputMap = new HashMap<String, Object>();
			inputMap.put("PAYMENT_PLAN_ID", paymentPlanId);
			inputMap.put("PAYMENT_PLAN_SEQ", paymentPlanSeq);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereClause , inputMap);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method deletePayPlan", e);
		}
		try {
			value = namedParameterJdbcTemplate.update(sql,
					createParams("PAYMENT_PLAN_ID", paymentPlanId, "PAYMENT_PLAN_SEQ", paymentPlanSeq));
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return value;
	}

	@Override
	public void reschPaymentSchedules(final long paymentPlanId, final long paymentPlanSeq, final Boolean biAutoAdjust) {
		final List<SqlParameter> paramList = new ArrayList<SqlParameter>();

		final JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		try {
			jdbcTemplate.call(new CallableStatementCreator() {
				Connection conn = jdbcTemplate.getDataSource().getConnection();

				@Override
				public CallableStatement createCallableStatement(Connection con) throws SQLException {
					CallableStatement callableStatement = null;
					try {
						callableStatement = con
								.prepareCall("{call OMS_OWNER.PAYMENT_SCHEDULES.RESCH_PAYMENT_SCHEDULES(?, ?)}");
						callableStatement.setBigDecimal(1, new BigDecimal(paymentPlanId));
						callableStatement.setBigDecimal(2, new BigDecimal(paymentPlanSeq));
						callableStatement.execute();

					} catch (SQLException e) {
						logger.error("Exception :", e);
					}
					return callableStatement;
				}
			}, paramList);
		} catch (DataAccessException | SQLException e) {
			logger.error("Exception :", e);
		}
	}

	@Override
	public void setPaymentSchedules(final long paymentPlanId, final long paymentPlanSeq, final Boolean biAutoAdjust) {

		final List<SqlParameter> paramList = new ArrayList<SqlParameter>();
		final JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		try {
			jdbcTemplate.call(new CallableStatementCreator() {
				Connection conn = jdbcTemplate.getDataSource().getConnection();

				@Override
				public CallableStatement createCallableStatement(final Connection conn) {
					CallableStatement callableStatement = null;
					try {
						callableStatement = conn
								.prepareCall("{call OMS_OWNER.PAYMENT_SCHEDULES.SET_PAYMENT_SCHEDULES(?, ?)}");
						callableStatement.setBigDecimal(1, new BigDecimal(paymentPlanId));
						callableStatement.setBigDecimal(2, new BigDecimal(paymentPlanSeq));
						callableStatement.execute();

					} catch (SQLException e) {
						logger.error("Exception :", e);

					}
					return callableStatement;
				}
			}, paramList);
		} catch (DataAccessException | SQLException e) {

			logger.error("Exception :", e);
		}
	}

	@Override
	public Date getDateValidated(final String startDate, final BigDecimal monthly, final Boolean bFlag)
			throws ParseException {

		final JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		final SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");

		List<SqlParameter> parameters = Arrays.asList(new SqlOutParameter("out", Types.DATE),
				new SqlParameter(Types.DATE), new SqlParameter(Types.BIGINT));

		Map<String, Object> outParam = null;
		try {
			outParam = jdbcTemplate.call(new CallableStatementCreator() {
				Connection conn = jdbcTemplate.getDataSource().getConnection();

				public CallableStatement createCallableStatement(Connection conn) throws SQLException {
					CallableStatement callableStatement = null;
					try {
						callableStatement = conn
								.prepareCall("{? = call OMS_OWNER.PAYMENT_SCHEDULES.GET_DATE_VALIDATED(?, ?)}");

						final java.util.Date d = sdf1.parse(startDate);
						final java.sql.Date sqlDate = new java.sql.Date(d.getTime());

						callableStatement.setDate(2, sqlDate);
						callableStatement.setBigDecimal(3, monthly);
						callableStatement.registerOutParameter(1, Types.DATE);
						callableStatement.execute();

					} catch (Exception e) {
						logger.error("Exception :", e);
					}
					return callableStatement;

				}
			}, parameters);
		} catch (DataAccessException | SQLException e) {
			logger.error("Exception :", e);
		}
		return (Date) outParam.get("out");

	}

	@Override
	public Boolean adjustForRoundoffs(final BigDecimal offenderId, final String informationNumber,
			final BigDecimal groupId, long paymentPlanId, long paymentPlanSeq) {
		Boolean flag = false;

		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[5];
		sqlParameters = new SqlParameter[] { new SqlParameter("NI_OFFENDER_ID", Types.NUMERIC),
				new SqlParameter("NI_PLAN_ID", Types.NUMERIC), new SqlParameter("NI_PLAN_SEQ", Types.NUMERIC),
				new SqlParameter("VI_INFO", Types.VARCHAR), new SqlParameter("NI_GRP_ID", Types.NUMERIC) };
		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("PAYMENT_SCHEDULES").withProcedureName("ADJUST_FOR_ROUNDOFFS")
				.declareParameters(sqlParameters);
		inParamMap.put("NI_OFFENDER_ID", offenderId);
		inParamMap.put("NI_PLAN_ID", paymentPlanId);
		inParamMap.put("NI_PLAN_SEQ", paymentPlanSeq);
		inParamMap.put("VI_INFO", informationNumber);
		inParamMap.put("NI_GRP_ID", groupId);

		final MapSqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);

		try {
			simpleJDBCCall.execute(inParameter);
			flag = true;

		} catch (DataAccessException e) {
			logger.error("Exception :", e);
			flag = false;
		}
		return flag;

	}

	@Override
	public OffenderDeductions getTotalAmt(final BigDecimal offenderId, final String informationNumber) {
		final String sql = getQuery("OCUPAYPL_PAY_PLN_GET_AMOUNT");
		final RowMapper<OffenderDeductions> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderDeductions.class, offDedMapping);
		return namedParameterJdbcTemplate.queryForObject(sql,
				createParams("INFORMATION_NUMBER", informationNumber, "OFFENDER_ID", offenderId), columnRowMapper);
	}

	@Override
	public Integer validateDelRow(final List<Long> paymentPlanId) {
		final String sql = getQuery("PAY_PLN_DEL");
		Integer val = null;
		try {
		for( Long planIdVal : paymentPlanId) {
			val	=  namedParameterJdbcTemplate.queryForObject(sql, createParams("PAYMENT_PLAN_ID", planIdVal),
					Integer.class);
		}
	}
		catch (Exception e) {
			logger.error("Exception :", e);
		}
		return val;
	}

	@Override
	public VTrustHeader offenderDisplay(final OffenderPaymentPlan searchBean) {

		final String sql = getQuery("OCUPAYPL_GET_OFFENDER_ID_DATA");
		final RowMapper<VTrustHeader> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, VTrustHeader.class,
				vthMapping);
		VTrustHeader returnData = new VTrustHeader();
		try {
			returnData = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("OFFENDER_ID", searchBean.getOffenderId(), "CASELOAD_ID", searchBean.getCaseLoadId()),
					columnRowMapper);
		} catch (Exception e) {
			return returnData;
		}
		return returnData;
	}

	@Override
	public OffenderPaymentPlan paymtSummary(final String informNumber, final BigDecimal groupId,
			final BigDecimal offenderId) {
		OffenderPaymentPlan returnData = new OffenderPaymentPlan();

		final String sql = getQuery("OCUPAYPL_PAY_PLN_SUMMARY");
		final RowMapper<OffenderPaymentPlan> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderPaymentPlan.class, OffenderPaymentPlanMapping);
		returnData = namedParameterJdbcTemplate.queryForObject(sql,
				createParams("OFFENDER_ID", offenderId, "GROUP_ID", groupId, "INFORMATION_NUMBER", informNumber),
				columnRowMapper);

		return returnData;
	}

	@Override
	public List<PaymentPlanBean> schedulePymt(final String informNumber, final BigDecimal groupId,
			final String paymentClosedFlag, final long paymentPlanId, final long paymentPlanSeq,
			final BigDecimal paidAmount) {
		final String sql = getQuery("OCUPAYPL_PAY_PLN_REPORT_LIST");
		final RowMapper<PaymentPlanBean> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, PaymentPlanBean.class,
				PaymentPlanBeanMapping);
		return namedParameterJdbcTemplate.query(sql,
				createParams("GROUP_ID", groupId, "INFORMATION_NUMBER", informNumber, "PAYMENT_CLOSED_FLAG", "Y",
						"PAYMENT_PLAN_ID", paymentPlanId, "PAYMENT_PLAN_SEQ", paymentPlanSeq, "PAID_AMOUNT",
						paidAmount),
				columnRowMapper);
	}

	@Override
	public List<PaymentPlanReportBean> mainHeaderQuery(final PaymentPlanReportBean paramBean) {
		return null;
	}

	@Override
	public BigDecimal totalDueAmount(final OffenderPaymentPlan searchBean) {
		final String sql = getQuery("OCUPAYPL_PAY_PLN_GET_TOT_DUE");
		return namedParameterJdbcTemplate.queryForObject(sql,
				createParams("INFORMATION_NUMBER", searchBean.getInformationNumber(), "GROUP_ID",
						searchBean.getGroupId(), "OFFENDER_ID", searchBean.getOffenderId()),
				BigDecimal.class);
	}

	@Override
	public String getCaseLoad(final String caseLoad) {
		final String sql = getQuery("OCUPAYPL_PAY_PLN_GET_CASELOAD");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("CASELOAD_ID", caseLoad), String.class);
	}

	@Override
	public String getProfileDesc(final String profileCode) {
		final String sql = getQuery("OCUPAYPL_PAY_PLN_GET_PROFILE");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("PROFILE_CODE", profileCode), String.class);
	}

	@Override
	public Integer getDayOfMonth(final String dateIfo) {
		final String sql = getQuery("OCUPAYPL_PAY_PLN_GET_DAY");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("START_DATE", dateIfo), Integer.class);

	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public Long gettingGroupId(BigDecimal offenderId, String groupId, String caseLoadId) {
		final String sql = getQuery("OCUPAYPL_GETTING_GROUP_ID");
		Long val = null;
		try {
			val = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("OFFENDER_ID", offenderId, "GRPID", groupId, "CASELOADID", caseLoadId), Long.class);
		} catch (Exception e) {
			return val;
		}
		return val;
	}

}

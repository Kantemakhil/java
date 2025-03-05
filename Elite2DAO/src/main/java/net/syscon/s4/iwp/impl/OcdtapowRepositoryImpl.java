package net.syscon.s4.iwp.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
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
import net.syscon.s4.common.beans.OffenderBookings;
import net.syscon.s4.common.beans.OffenderCommunityFile;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.CasePlans;
import net.syscon.s4.im.beans.Offenders;
import net.syscon.s4.inst.workflow.maintenance.beans.StaffLocationRoles;
import net.syscon.s4.iwp.OcdtapowRepository;
import net.syscon.s4.sa.usersystemsecurity.beans.AssignmentTransfers;
import net.syscon.s4.sa.usersystemsecurity.beans.OffenderWorkAssignments;
import oracle.jdbc.OracleTypes;

/**
 * Class OcdtapowRepositoryImpl
 */
@Repository
public class OcdtapowRepositoryImpl extends RepositoryBase implements OcdtapowRepository { 
	private static final String OFFENDER_FILE_SEQ = "OFFENDER_FILE_SEQ";
	private static final String OFFENDER_ID = "OFFENDER_ID";
	private static final String HOLD_AGY_LOC_ID = "HOLDING_AGY_LOC_ID";
	private static final String ROLE=	"ROLE";
	private static final String FROM_DATE="FROM_DATE";
	private static final String CAL_AGY_LOC_ID="CAL_AGY_LOC_ID";
	private static final String STAFF_ID="STAFF_ID";
	private static final String OFFENDER_BOOK_ID="OFFENDER_BOOK_ID";
	private static final String AGY_LOC_ID="AGY_LOC_ID";
	private static final String POSITION="POSITION";
	private static final String P_AGY_LOC_ID="P_AGY_LOC_ID";
	private static final String P_STAFF_ID="P_STAFF_ID";
	private static final String P_ROLE="P_ROLE";
	private static final String P_FROM_DATE="P_FROM_DATE";
	private static final String P_POSITION="P_POSITION";
	private static final String OFFBOOKID="offBookId";
	private static final String CASEPLANID="casePlanId";
	
	



	/**
	 * Logger object used to print the log in the file
	 */

	private static Logger logger = LogManager.getLogger(OcdtapowRepositoryImpl.class.getName());

	private final Map<String, FieldMapper> mMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("AGENCY_LOCATION_TYPE", new FieldMapper("agencyLocationType"))
			.put("CODE", new FieldMapper("code"))
			.put("DESCRIPTION", new FieldMapper("description"))
			.put("LAST_NAME", new FieldMapper("lastName"))
			.put("FIRST_NAME", new FieldMapper("firstName")).build();

	private final Map<String, FieldMapper> staffLocMapings = new ImmutableMap.Builder<String, FieldMapper>()
			.put("FIRST_NAME", new FieldMapper("firstName"))
			.put("SCHEDULE_TYPE", new FieldMapper("scheduleType"))
			.put("HOURS_PER_WEEK", new FieldMapper("hoursPerWeek"))
			.put("LAST_NAME", new FieldMapper("lastName"))
			.put("NAME", new FieldMapper("name"))
			.put(ROLE, new FieldMapper("role"))
			.put(POSITION, new FieldMapper("position"))
			.put(FROM_DATE, new FieldMapper("supervisorFromDate"))
			.put(CAL_AGY_LOC_ID, new FieldMapper("calAgyLocId"))
			.put(STAFF_ID, new FieldMapper("staffId"))
			.put("STATUS", new FieldMapper("status"))
			.put("OFFASS_ID", new FieldMapper("offassId")).build();
	private final Map<String, FieldMapper> offBookMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put(OFFENDER_BOOK_ID, new FieldMapper("offenderBookId"))
			.put("BOOKING_BEGIN_DATE", new FieldMapper("bookingBeginDate"))
			.put("BOOKING_END_DATE", new FieldMapper("bookingEndDate"))
			.put("BOOKING_NO", new FieldMapper("bookingNo"))
			.put(OFFENDER_ID, new FieldMapper("offenderId"))
			.put(AGY_LOC_ID, new FieldMapper("agyLocId"))
			.put("LIVING_UNIT_ID", new FieldMapper("livingUnitId"))
			.put("DISCLOSURE_FLAG", new FieldMapper("disclosureFlag"))
			.put("ACTIVE_FLAG", new FieldMapper("activeFlag"))
			.put("BOOKING_STATUS", new FieldMapper("bookingStatus"))
			.put("YOUTH_ADULT_CODE", new FieldMapper("youthAdultCode"))
			.put("FINGER_PRINTED_STAFF_ID", new FieldMapper("fingerPrintedStaffId"))
			.put("SEARCH_STAFF_ID", new FieldMapper("searchStaffId"))
			.put("PHOTO_TAKING_STAFF_ID", new FieldMapper("photoTakingStaffId"))
			.put("ASSIGNED_STAFF_ID", new FieldMapper("assignedStaffId"))
			.put("CREATE_AGY_LOC_ID", new FieldMapper("createAgyLocId"))
			.put("BOOKING_TYPE", new FieldMapper("bookingType"))
			.put("BOOKING_CREATED_DATE", new FieldMapper("bookingCreatedDate"))
			.put("ROOT_OFFENDER_ID", new FieldMapper("rootOffenderId"))
			.put("AGENCY_IML_ID", new FieldMapper("agencyImlId"))
			.put("SERVICE_FEE_FLAG", new FieldMapper("serviceFeeFlag"))
			.put("EARNED_CREDIT_LEVEL", new FieldMapper("earnedCreditLevel"))
			.put("EKSTRAND_CREDIT_LEVEL", new FieldMapper("ekstrandCreditLevel"))
			.put("INTAKE_AGY_LOC_ID", new FieldMapper("intakeAgyLocId"))
			.put("ACTIVITY_DATE", new FieldMapper("activityDate"))
			.put("INTAKE_CASELOAD_ID", new FieldMapper("intakeCaseloadId"))
			.put("INTAKE_USER_ID", new FieldMapper("intakeUserId"))
			.put("CASE_OFFICER_ID", new FieldMapper("caseOfficerId"))
			.put("CASE_DATE", new FieldMapper("caseDate"))
			.put("CASE_TIME", new FieldMapper("caseTime"))
			.put("COMMUNITY_ACTIVE_FLAG", new FieldMapper("communityActiveFlag"))
			.put("CREATE_INTAKE_AGY_LOC_ID", new FieldMapper("createIntakeAgyLocId"))
			.put("COMM_STAFF_ID", new FieldMapper("commStaffId"))
			.put("COMM_STATUS", new FieldMapper("commStatus"))
			.put("COMMUNITY_AGY_LOC_ID", new FieldMapper("communityAgyLocId"))
			.put("NO_COMM_AGY_LOC_ID", new FieldMapper("noCommAgyLocId"))
			.put("COMM_STAFF_ROLE", new FieldMapper("commStaffRole"))
			.put("AGY_LOC_ID_LIST", new FieldMapper("agyLocIdList"))
			.put("STATUS_REASON", new FieldMapper("statusReason"))
			.put("TOTAL_UNEXCUSED_ABSENCES", new FieldMapper("totalUnexcusedAbsences"))
			.put("REQUEST_NAME", new FieldMapper("requestName"))
			.put("RECORD_USER_ID", new FieldMapper("recordUserId"))
			.put("CREATE_USER_ID", new FieldMapper("createUserId"))
			.put("INTAKE_AGY_LOC_ASSIGN_DATE", new FieldMapper("intakeAgyLocAssignDate"))
			.put("CREATE_DATETIME", new FieldMapper("createDatetime"))
			.put("MODIFY_DATETIME", new FieldMapper("modifyDatetime"))
			.put("MODIFY_USER_ID", new FieldMapper("modifyUserId"))
			.put("SEAL_FLAG", new FieldMapper("sealFlag"))
			.build();

	private final Map<String, FieldMapper> offenderMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("LAST_NAME", new FieldMapper("lastName")).put("FIRST_NAME", new FieldMapper("firstName"))
			.put("OFFENDER_ID_DISPLAY", new FieldMapper("offenderIdDisplay")).build();

	private final Map<String, FieldMapper> offComFMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put(OFFENDER_FILE_SEQ, new FieldMapper("offenderFileSeq"))
			.put(OFFENDER_ID, new FieldMapper("offenderId"))
			.put("FILE_TYPE", new FieldMapper("fileType"))
			.put("FILE_SUB_TYPE", new FieldMapper("fileSubType"))
			.put("OFFENDER_FILE_NUM", new FieldMapper("offenderFileNum"))
			.put("VOLUME_SEQ", new FieldMapper("volumeSeq"))
			.put("FILE_REFERENCE", new FieldMapper("fileReference"))
			.put("FILE_CREATE_DATE", new FieldMapper("fileCreateDate"))
			.put(HOLD_AGY_LOC_ID, new FieldMapper("holdingAgyLocId"))
			.put("HOLDING_STAFF_ID", new FieldMapper("holdingStaffId"))
			.put("NON_OFFICER_STATUS", new FieldMapper("nonOfficerStatus"))
			.put("STORAGE", new FieldMapper("storage"))
			.put("RESUBMISSION_DATE", new FieldMapper("resubmissionDate"))
			.put("CREATION_DATE", new FieldMapper("creationDate"))
			.put("CREATION_USER", new FieldMapper("creationUser"))
			.put("CASELOAD_TYPE", new FieldMapper("caseloaType"))
			.put("TRANS_AGY_LOC_ID", new FieldMapper("transAgyLocId"))
			.put("TRANSACTION_ID", new FieldMapper("transactionId"))
			.put("TRANSFER_FLAG", new FieldMapper("transferFlag"))
			.put("CLOSE_FILE_NO", new FieldMapper("closeFileNo"))
			.build();

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            StaffLocationRoles
	 *
	 * @return List<StaffLocationRoles>
	 *
	 * 
	 */
	public List<StaffLocationRoles> staffLrExecuteQuery(final StaffLocationRoles objSearchDao) {
		final String sql = getQuery("OCDTAPOW_STAFFLR_FIND_STAFF_LOCATION_ROLES");
		final RowMapper<StaffLocationRoles> StaffLRMapper = Row2BeanRowMapper.makeMapping(sql, StaffLocationRoles.class,
				staffLocMapings);
		return (ArrayList<StaffLocationRoles>) namedParameterJdbcTemplate
				.query(sql, createParams("agyLocId", objSearchDao.getCalAgyLocId(), "staffId",
						objSearchDao.getSacStaffId()), StaffLRMapper);
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            OffenderBookings
	 *
	 * @return List<OffenderBookings>
	 *
	 */
	public List<OffenderBookings> offBkg1ExecuteQuery(final StaffMembers objSearchDao) { 
		final String sql = getQuery("OCDTAPOW_OFFBKG1_FIND_OFFENDER_BOOKINGS");
		final RowMapper<OffenderBookings> OffBookRowMapper = Row2BeanRowMapper.makeMapping(sql, OffenderBookings.class,
				offBookMapping);
		return (ArrayList<OffenderBookings>) namedParameterJdbcTemplate
				.query(sql,
						createParams("agyLocId", objSearchDao.getDescription(), "staffId", objSearchDao.getStaffId(),
								"position", objSearchDao.getPosition(), "role", objSearchDao.getRole()),
						OffBookRowMapper);

	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<AgencyLocations> cgfkStaffLrDspDescriptionRecordGroup(final String caseLoadId) {
		final String sql = getQuery("OCDTAPOW_FIND_CGFKSTAFFLRDSPDESCRIPTION");
		final RowMapper<AgencyLocations> mRowMapper = Row2BeanRowMapper.makeMapping(sql, AgencyLocations.class,
				mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams("caseLoad", caseLoadId), mRowMapper);
		} catch (final EmptyResultDataAccessException e) {
			logger.error("cgfkStaffLrDspDescriptionRecordGroup",e.getMessage());

			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<ReferenceCodes> cgfkVOffDetSkillTypeRecordGroup() {
		final String sql = getQuery("OCDTAPOW_FIND_CGFKVOFFDETSKILLTYPE");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (final EmptyResultDataAccessException e) {
			logger.error("cgfkVOffDetSkillTypeRecordGroup",e.getMessage());

			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<ReferenceCodes> cgfkVOffDetSkillSubTypeRecordGroup() {
		final String sql = getQuery("OCDTAPOW_FIND_CGFKVOFFDETSKILLSUBTYPE");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (final EmptyResultDataAccessException e) {
			logger.error("cgfkVOffDetSkillSubTypeRecordGroup",e.getMessage());
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<StaffMembers> cgfkStaffLrDspLastNameRecordGroup() {
		final String sql = getQuery("OCDTAPOW_FIND_CGFKSTAFFLRDSPLASTNAME");
		final RowMapper<StaffMembers> mRowMapper = Row2BeanRowMapper.makeMapping(sql, StaffMembers.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (final EmptyResultDataAccessException e) {
			logger.error("cgfkStaffLrDspLastNameRecordGroup",e.getMessage());
			return Collections.emptyList();
		}
	}

	@Override
	public Offenders getOffenderDetails(final OffenderBookings offenderBookings) {

		final String sql = getQuery("OCDTAPOW_CGFKCHK_OFF_BKG_OFF_BKG_OFF_N_");
		final RowMapper<Offenders> OffenderRowMapper = Row2BeanRowMapper.makeMapping(sql, Offenders.class,
				offenderMapping);
		Offenders offenders = new Offenders();
		try{
			offenders = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("OFFENDER_BOOK_ID", offenderBookings.getOffenderBookId()),
					OffenderRowMapper);
		}catch(Exception e){
			logger.error("getOffenderDetails",e.getMessage());
		}
		
		return offenders;

	}
	public Date getStartDate(final OffenderBookings paramBean) {
		final String sql = getQuery("OCDTAPOW_FIND_STARTDATE");
		Date startDate = null;
		try {
			startDate = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("OFFENDERBOOKID", paramBean.getOffenderBookId()), Date.class);
		} catch (final Exception e) {
			logger.error("getStartDate",e.getMessage());
			return startDate;
		}
		return startDate;
	}

	@Override
	public String getpreviosWorkData(final StaffLocationRoles searchRecord) {
		final String sql = getQuery("OCDTAPOW_GET_PREVIOSWORK_DATA");
		String data = null;
		try {
			data = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("staffId", searchRecord.getStaffId() != null ? new BigDecimal(searchRecord.getStaffId()):null, "offenderBookId", searchRecord.getSupervisorStaffId()),
					String.class);
			} catch (final Exception e) {
			logger.error("getpreviosWorkData()",e);
			return data;
		}
		return data;
	}

	@Override
	public BigDecimal getcaseOfficerId(final OffenderBookings offenderBookings) {
		final String sql = getQuery("OCDTAPOW_GET_CASE_OFFICERID");
		BigDecimal data = null;
		try {
			data = namedParameterJdbcTemplate.queryForObject(sql,
					createParams(OFFBOOKID, offenderBookings.getOffenderBookId()), BigDecimal.class);
		} catch (final Exception e) {
			logger.error("getcaseOfficerId()",e.getMessage());
			return data;
		}
		return data;
	}

	@Override
	public String officerWork(final StaffLocationRoles staffLocationRole) {

		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		Map<String, Object> returnObject = null;

		String workload = null;
		BigDecimal worklod = null;

		final SqlParameter[] sqlParameters = new SqlParameter[] { new SqlParameter(P_AGY_LOC_ID, OracleTypes.VARCHAR),
				new SqlParameter(P_STAFF_ID, OracleTypes.NUMBER), new SqlParameter(P_FROM_DATE, OracleTypes.DATE),
				new SqlParameter(P_ROLE, OracleTypes.VARCHAR), new SqlParameter(P_POSITION, OracleTypes.VARCHAR), };
		Date date1 = null;
		try {
			date1 = new SimpleDateFormat("dd/MM/yyyy",Locale.ENGLISH).parse(staffLocationRole.getFromDate());
		} catch (final ParseException e) {
			logger.error("officerWork()",e.getMessage());
		}

		final SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("pims_weight").withFunctionName("OFFICER_WORK").declareParameters(sqlParameters);

		inParamMap.put(P_AGY_LOC_ID, staffLocationRole.getCalAgyLocId());
		inParamMap.put("P_STAFF_ID", staffLocationRole.getStaffId());
		inParamMap.put("P_FROM_DATE", date1);
		inParamMap.put("P_ROLE", staffLocationRole.getRole());
		inParamMap.put("P_POSITION", staffLocationRole.getPosition());
		final SqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		try {
			returnObject = simpleJDBCCall.execute(inParameter);
			worklod = (BigDecimal) returnObject.get("return");
			workload = worklod.toString();
		} catch (final Exception e) {
			logger.error("officerWork()",e.getMessage());
		}

		return workload;
	}

	@Override
	public String getNoOffender(final StaffLocationRoles staffLocationRole) {

		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		Map<String, Object> returnObject = null;
		String offenderno = null;
		BigDecimal noOffender = null;

		final SqlParameter[] sqlParameters = new SqlParameter[] { new SqlParameter("P_AGY_LOC_ID", OracleTypes.VARCHAR),
				new SqlParameter("P_STAFF_ID", OracleTypes.NUMBER), new SqlParameter(P_FROM_DATE, OracleTypes.DATE),
				new SqlParameter("P_ROLE", OracleTypes.VARCHAR), new SqlParameter("P_POSITION", OracleTypes.VARCHAR), };
		Date date1 = null;
		try {
			date1 = new SimpleDateFormat("dd/MM/yyyy",Locale.ENGLISH).parse(staffLocationRole.getFromDate());
		} catch (final ParseException e) {
			logger.error("getNoOffender()",e.getMessage());
		}

		final SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("comunity_pkg").withFunctionName("get_officer_po").declareParameters(sqlParameters);

		inParamMap.put("P_AGY_LOC_ID", staffLocationRole.getCalAgyLocId());
		inParamMap.put("P_STAFF_ID", staffLocationRole.getStaffId());
		inParamMap.put("P_FROM_DATE", date1);
		inParamMap.put("P_ROLE", staffLocationRole.getRole());
		inParamMap.put("P_POSITION", staffLocationRole.getPosition());
		final SqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		try {
			returnObject = simpleJDBCCall.execute(inParameter);
			noOffender = (BigDecimal) returnObject.get("return");
			offenderno = noOffender.toString();

			logger.info("returnObject" + returnObject);
		} catch (final Exception e) {
			logger.error("getNoOffender()",e.getMessage());
		}

		return offenderno;
	}

	@Override
	public Long getCasePlanId(final Long offenderBookId) {
		final String sql = getQuery("OCDTAPOW_GET_CASE_PLANID");
		Long casePlanId = null;
		try {
			casePlanId = namedParameterJdbcTemplate.queryForObject(sql, createParams(OFFBOOKID, offenderBookId),
					Long.class);
		} catch (final Exception e) {
			logger.error("getCasePlanId()",e.getMessage());
			return casePlanId;
		}
		return casePlanId;

	}

	@Override
	public Date getFromDate(final StaffLocationRoles staffLocationRole) {
		final String sql = getQuery("OCDTAPOW_FIND_FROMDATE");
		Date startDate = null;
		try {
			startDate = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("staffId", Integer.parseInt(staffLocationRole.getStaffId()), "agyLocId",
							staffLocationRole.getCalAgyLocId(), "position", staffLocationRole.getPosition(), "role",
							staffLocationRole.getRole()),
					Date.class);
		} catch (final Exception e) {
			logger.error("getFromDate()",e);
			return startDate;
		}
		return startDate;
	}

	@Override
	public String getSupLevel(final Long offBookId) {
		final String value;
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[2];
		sqlParameters = new SqlParameter[] { new SqlInOutParameter("RETURN_VALUE", OracleTypes.VARCHAR),
				new SqlParameter("P_OFFENDER_BOOK_ID", OracleTypes.INTEGER) };
		final SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("PIMS_WEIGHT").withFunctionName("GET_SUP_LEVEL").declareParameters(sqlParameters);
		inParamMap.put("P_OFFENDER_BOOK_ID", offBookId);
		final SqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		value = simpleJDBCCall.executeFunction(String.class, inParameter);
		return value;
	}

	@Override
	public int getreviewPeriod(final String supervisionLevel) {
		final String sql = getQuery("OCDTAPOW_FIND_REVIEWPERIOD");
		int reviewDate = 0;
		try {
			reviewDate = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("supervisionLevel", supervisionLevel), Integer.class);
		} catch (final Exception e) {
			logger.error("getreviewPeriod()",e.getMessage());
			return reviewDate;
		}
		return reviewDate;
	}

	public Integer casePlanInsert(final CasePlans listObj) {
		final String sql = getQuery("OCDTAPOW_CASEPLAN_INSERT");
	return namedParameterJdbcTemplate.update(sql, new BeanPropertySqlParameterSource(listObj));

	}

	@Override
	public Integer insertAssessmentSummaries(final CasePlans casePlans) {
		final String sql = getQuery("OCDTAPOW_INSERT_ASSESSMENT_SUMMARIES");
		int val;

		val = namedParameterJdbcTemplate.update(sql,
				createParams(OFFBOOKID, casePlans.getOffenderBookId(), CASEPLANID, casePlans.getCasePlanId(),"createUserId",casePlans.getCreateUserId()));
		return val;
	}

	@Override
	public Integer insertplanDetails(final CasePlans casePlans) {
		final String sql = getQuery("OCDTAPOW_INSERT_PLAN_DETAILS");
		int val;

		val = namedParameterJdbcTemplate.update(sql,
				createParams(OFFBOOKID, casePlans.getOffenderBookId(), CASEPLANID, casePlans.getCasePlanId(),"createUserId",casePlans.getCreateUserId()));
		return val;
	}
	
	@Override
	public String casePlanPreInsert(final Long planId, final Long offenderBookId) {
		final String sql = getQuery("OCDTAPOW_GET_CREATE_USER");
		String val = null;
		try {
		val = namedParameterJdbcTemplate.queryForObject(sql,
				createParams("p_offender_book_id", offenderBookId, "v_id", planId), String.class);
		} catch (Exception e) {
			return val;
		}
		return val;
	}

	@Override
	public Integer insertCaseWorkSteps(final CasePlans casePlans) {
		final String sql = getQuery("OCDTAPOW_INSERTCASE_WORKSTEPS");
		int val;

		val = namedParameterJdbcTemplate.update(sql,
				createParams(OFFBOOKID, casePlans.getOffenderBookId(), "casePlanId", casePlans.getCasePlanId(),"createUserId",casePlans.getCreateUserId()));
		return val;
	}

	@Override
	public OffenderWorkAssignments getPreWorkAssignemetDet(final Long offenderBookId, final Integer staffId) {
		final String sql = getQuery("OCDTAPOW_GET_PRE_WORK_ASSIGNEMET_DET");
		final RowMapper<OffenderWorkAssignments> OffenderRowMapper = Row2BeanRowMapper.makeMapping(sql, OffenderWorkAssignments.class,
				offenderMapping);
		OffenderWorkAssignments offWorkAssign = new OffenderWorkAssignments();
		try{
			offWorkAssign = namedParameterJdbcTemplate.queryForObject(sql, createParams("offenderBookId", offenderBookId,"offenderBookId", offenderBookId,"staffId",staffId),
					OffenderRowMapper);
		}catch(final Exception e){
			logger.error("getPreWorkAssignemetDet()",e.getMessage());
		}

		return offWorkAssign;
	}


	@Override
	public Integer insertAssignmentTransfers(final AssignmentTransfers workassin) {
		final String sql = getQuery("OCDTAPOW_INSERT_ASSIGNMENT_TRANSFERS");

		return namedParameterJdbcTemplate.update(sql,
				createParams("assId", workassin.getAsstraId(), "fromDateFrom", workassin.getFromDateFrom(),
						"positionFrom", workassin.getPositionFrom(), "roleFrom", workassin.getRoleFrom(),
						"sacStaffIdFrom", workassin.getSacStaffIdFrom(), "calAgyLocId", workassin.getCalAgyLocIdFrom(),
						"statusFrom", workassin.getStatusFrom(), "agyLocId", workassin.getCalAgyLocId(), "fromDate",
						workassin.getFromDate(), "position", workassin.getPosition(), "role", workassin.getRole(),
						"sacStaffId", workassin.getSacStaffId(), "calAgyLocId", workassin.getCalAgyLocId(), "statusTo",
						null, "agyLocId", workassin.getCalAgyLocId(),"createUserId",workassin.getCreateUserId()));

	}

	@Override
	public Long getOffasIdSeq() {
		final String sql = getQuery("OCDTAPOW_GETOFFASID_SEQ");
		Long offasId = null ;
		try {
			offasId = namedParameterJdbcTemplate.queryForObject(sql,
					createParams(), Long.class);
		} catch (final Exception e) {
			logger.error("getOffasIdSeq()",e.getMessage());
			return offasId;
		}
		return offasId;

	}

	@Override
	public int updateWorkAssigments(final OffenderWorkAssignments offWorkAssign) {
		final String sql = getQuery("OCDTAPOW_UPDATEWORK_ASSIGMENTS");

		return namedParameterJdbcTemplate.update(sql,
				createParams("fromDate", offWorkAssign.getFromDate(), "position", offWorkAssign.getPosition(), "role",
						offWorkAssign.getRole(), "sacStaffId", offWorkAssign.getSacStaffId(), "status", null, "offasId",
						offWorkAssign.getOffassId(),"modifyUserId",offWorkAssign.getModifyUserId()));

	}

	@Override
	public OffenderCommunityFile getOffenderFileDetails(final BigDecimal offenderId) { 
		final String sql = getQuery("OCDTAPOW_GET_OFFENDER_FILE_DETAILS");
		final RowMapper<OffenderCommunityFile> OffenderRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderCommunityFile.class, offComFMapping);
		OffenderCommunityFile offComFile = new OffenderCommunityFile();
		try{
			offComFile = namedParameterJdbcTemplate.queryForObject(sql, createParams("offenderId", offenderId),
					OffenderRowMapper);
		}catch(Exception e){
			logger.error("getOffasIdSeq()",e.getMessage());
		}
	

		return offComFile;
	}

	@Override
	public String getProfileValue() {
		final String sql = getQuery("OCDTAPOW_GETPROFILEVALUE");
		String profValue = null;
		profValue = namedParameterJdbcTemplate.queryForObject(sql, createParams(), String.class);
		return profValue;
	}

	@Override
	public void updateCommunityFinanceFiles(final OffenderCommunityFile offComFile) {
		final String sql = getQuery("OCDTAPOW_UPDATE_COMMUNITY_FINANCEFILES");
		try {
			namedParameterJdbcTemplate.update(sql, createParams("holdingStaffId",offComFile.getHoldingStaffId(),"holdingAgyLocId",offComFile.getHoldingAgyLocId(),"offenderId", offComFile.getOffenderId(),"offFileSeq",offComFile.getOffenderFileSeq(),"modifyUserId",offComFile.getModifyUserId()));
		} catch (final Exception e) {
			logger.error("Exception : updateOffenderComm:", e);
		}

	}

	@Override
	public Integer pimsFileTracking(final OffenderCommunityFile offComm, final StaffMembers staffmembers) {
		Integer genSeq = 0;
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		inParamMap.put("P_OFFENDER_FILE_SEQ", offComm.getOffenderFileSeq());
		inParamMap.put("P_OFFENDER_ID", offComm.getOffenderId());
		inParamMap.put("P_FILE_TRANS_COMMENT", null);
		inParamMap.put("P_AGY_LOC_ID_FROM", offComm.getHoldingAgyLocId());
		inParamMap.put("P_AGY_LOC_ID_TO", offComm.getHoldingAgyLocId());
		inParamMap.put("P_STAFF_ID_FROM", offComm.getHoldingStaffId());
		inParamMap.put("P_STAFF_ID_TO", null);
		inParamMap.put("P_NON_OFFICER_FROM", offComm.getNonOfficerStatus());
		inParamMap.put("P_NON_OFFICER_TO", null);
		try {
			namedParameterJdbcTemplate.update(
					"call OMS_OWNER.PIMS_FILE_TRACKING.TRANSFER_FILE(:P_OFFENDER_FILE_SEQ, :P_OFFENDER_ID, :P_FILE_TRANS_COMMENT, :P_AGY_LOC_ID_FROM, :P_AGY_LOC_ID_TO, :P_STAFF_ID_FROM, :P_STAFF_ID_TO, :P_NON_OFFICER_FROM, :P_NON_OFFICER_TO);",
					inParamMap);
		} catch (final Exception e) {
			logger.error("pimsFileTracking", e);
			genSeq = 0;
		}
		genSeq = 2;
		return genSeq;
	}

	public Integer updatecasePlans(final CasePlans listObj, final Long offenderBookId) {
		final String sql = getQuery("OCDTAPOW_CASEPLAN_UPDATE");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		parameters.add(new BeanPropertySqlParameterSource(listObj));
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (returnArray.length == 1) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public void updateOffenderBookings(CasePlans casePlans, BigDecimal offenderId) {
		final String sql = getQuery("OCDTAPOW_UPDATE_OFFENDER_BOOKINGS");
		 namedParameterJdbcTemplate.update(sql,
				createParams( "staffRole",casePlans.getRole(),"agyLocId",casePlans.getAgyLocId(), "staffId", casePlans.getSacStaffId(), 
						"offId",offenderId,"modifyUserId",casePlans.getModifyUserId()));
	}
	
	@Override
	public BigDecimal getaliasOffenderId(OffenderBookings offenderBookings) {
		final String sql = getQuery("OCDTAPOW_GET_ALIAS_OFFENDER_ID");
		BigDecimal aliasOffender = null;
		try{
			aliasOffender = namedParameterJdbcTemplate.queryForObject(sql, createParams("offBookId",offenderBookings.getOffenderBookId()), BigDecimal.class);
		}catch(Exception e){
			logger.error("getaliasOffenderId", e);
		}
		
		return aliasOffender;
	}
}

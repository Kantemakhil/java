package net.syscon.s4.pkgs.tag_programmes.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.cm.programsservices.VAcpSchedules;
import net.syscon.s4.common.beans.OffenderCourseAttendance;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.CourseActivities;
import net.syscon.s4.im.beans.OffenderPrgObligationHty;
import net.syscon.s4.im.beans.OffenderPrgObligations;
import net.syscon.s4.im.beans.OffenderProgramProfiles;
import net.syscon.s4.inst.casemanagement.beans.ProgramServices;
import net.syscon.s4.inst.institutionalactivities.maintenance.beans.CourseSchedules;
import net.syscon.s4.pkgs.tag_programmes.TagProgrammesRepository;
import net.syscon.s4.triggers.OffenderCourseAttendances;

@Repository
public class TagProgrammesRepositoryImpl extends RepositoryBase implements TagProgrammesRepository {

	private static Logger logger = LogManager.getLogger(TagProgrammesRepositoryImpl.class.getName());

	private final Map<String, FieldMapper> mMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DESCRIPTION", new FieldMapper("description")).put("CODE", new FieldMapper("code")).build();

	private final Map<String, FieldMapper> couActyMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CRS_ACTY_ID", new FieldMapper("crsActyId")).build();

	@Override
	public List<OffenderCourseAttendance> selectCrsSchId(final Long crsSchId) {
		final String sql = getQuery("SELECT_OFF_COURSE_ATTEND");
		final RowMapper<OffenderCourseAttendance> mRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderCourseAttendance.class, mMapping);
		return namedParameterJdbcTemplate.query(sql, createParams("p_crs_sch_id", crsSchId), mRowMapper);
	}

	@Override
	public Integer updateOffCourseAttendance(final Long crsSchId, final String userName) {
		final String sql = getQuery("UPDATE_OFF_COURSE_ATTEND");
		return namedParameterJdbcTemplate.update(sql, createParams("p_crs_sch_id", crsSchId, "modifyUserId", userName));
	}

	private final Map<String, FieldMapper> mapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("ORDER_PROPOSAL_CONDITION_ID", new FieldMapper("orderProposalConditionId")).build();

	@Override
	public List<OffenderCourseAttendance> ocaCur(final Long pCrsSchId) {
		final String sql = getQuery("UPDATE_COURSE_ATTENDANCE_DATES_OCA_CUR");
		final RowMapper<OffenderCourseAttendance> tempOidcountRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderCourseAttendance.class, mapping);
		return namedParameterJdbcTemplate.query(sql, createParams("p_crs_sch_id", pCrsSchId), tempOidcountRowMapper);
	}

	@Override
	public Integer updateOffenderCourseAttendance(final BigDecimal pCrsSchId, final VAcpSchedules vacp,
			final String userName) {
		final String sql = getQuery("UPDATE_COURSE_ATTENDANCE_DATES_UPDATE_OFFENDER_COURSE_ATTENDANCES");
		return namedParameterJdbcTemplate.update(sql,
				createParams("p_event_date", vacp.getScheduleDate(), "p_start_time", vacp.getStartTime(), "p_end_time",
						vacp.getEndTime(), "crs_sch_id", pCrsSchId, "modifyUserId", userName));
	}

	@Override
	public String getXFromOffPrgObligations(final Long pOffenderPrgObligationId) {
		final String sql = getQuery("UPDATE_STATUS_GET_OFF_PRG_OBLI");
		String retVal = null;
		try {
			retVal = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("P_OFFENDER_PRG_OBLIGATION_ID", pOffenderPrgObligationId), String.class);
		} catch (final Exception e) {
			logger.error("getXFromOffPrgObligations :", e);
		}
		return retVal;
	}

	@Override
	public Integer updateStatusUpdateOffPrgObli(final String pStatus, final String pReason, final Date pDate,
			final Long pOffenderPrgObligationId, final String userName) {
		final String sql = getQuery("UPDATE_STATUS_UPDATE_OFF_PRG_OBLI");
		Integer retVal = null;
		try {
			retVal = namedParameterJdbcTemplate.update(sql,
					createParams("P_STATUS", pStatus, "P_REASON", pReason, "P_DATE", pDate,
							"P_OFFENDER_PRG_OBLIGATION_ID", pOffenderPrgObligationId, "modifyUserId", userName));
		} catch (final Exception e) {
			logger.error("updateStatusUpdateOffPrgObli :", e);
		}
		return retVal;
	}

	@Override
	public Integer insertOffenderCourseAttendance(final OffenderCourseAttendance bean) {
		final String sql = getQuery("INSERT_OFFENDER_COURSE_ATTENDANCE");
		final OffenderCourseAttendance offCrs = new OffenderCourseAttendance();
		try {
			return namedParameterJdbcTemplate.update(sql, new BeanPropertySqlParameterSource(offCrs));
		} catch (final Exception e) {
			logger.error("insertOffenderCourseAttendance :" + e);
			return 0;
		}

	}

	@Override
	public OffenderCourseAttendance selectOffCourseAtt(final OffenderCourseAttendance bean) {
		final String sql = getQuery("SELECT_OFFENDER_COURSE_ATTENDANCE");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql, createParams("P_OFFENDER_BOOK_ID",
					bean.getOffenderBookId(), "P_OLD_CRS_SCH_ID", bean.getCrsSchId()), OffenderCourseAttendance.class);
		} catch (final Exception e) {
			logger.error("selectOffCourseAtt :" + e);
		}
		return null;
	}

	@Override
	public void createOffWRReturnSchedule(final Integer eventId) {
		final String sql = getQuery("UPDATE_CREATE_OFF_WR_RETURN_SCHEDULE");
		final Map<String, Object> inParamMap = new HashMap<String, Object>();

		inParamMap.put("p_event_id", eventId);
		try {
			namedParameterJdbcTemplate.update(sql, inParamMap);
		} catch (final Exception e) {
			logger.error("createOffWRReturnSchedule", e);
		}
	}

	private final Map<String, FieldMapper> prgServiceMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CRS_ACTY_ID", new FieldMapper("crsActyId")).build();

	@Override
	public List<ProgramServices> getPgmCur(final Long offPrgrefId) {
		final String sql = getQuery("GET_PGM_CUR");

		final RowMapper<ProgramServices> rowMapper = Row2BeanRowMapper.makeMapping(sql, ProgramServices.class,
				prgServiceMapping);
		return namedParameterJdbcTemplate.query(sql, createParams("p_off_prgref_id", offPrgrefId), rowMapper);
	}

	@Override
	public List<ProgramServices> getPhaseCur(final Long parentOffPrgrefId) {
		final String sql = getQuery("GET_PHASE_CUR");
		final RowMapper<ProgramServices> rowMapper = Row2BeanRowMapper.makeMapping(sql, ProgramServices.class, mapping);
		return namedParameterJdbcTemplate.query(sql, createParams("v_parent_off_prgref_id", parentOffPrgrefId),
				rowMapper);
	}

	@Override
	public List<OffenderPrgObligations> getOffPrgOblDetails(final Long pOffenderPrgObligationId) {
		final String sql = getQuery("LOCK_OFF_PRG_OBLIGATION_ID");
		final RowMapper<OffenderPrgObligations> rowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderPrgObligations.class, prgServiceMapping);
		return namedParameterJdbcTemplate.query(sql,
				createParams("P_OFFENDER_PRG_OBLIGATION_ID", pOffenderPrgObligationId), rowMapper);

	}

	@Override
	public Long getPrgProfile(final Long pOffenderPrgObligationId, final Long lvProgramId) {
		final String sql = getQuery("GET_PRG_PROFILE");
		Long no = 0l;
		try {
			no = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("pOffenderPrgObligationId", pOffenderPrgObligationId, "lvProgramId", lvProgramId),
					Long.class);
		} catch (final Exception e) {
			logger.error("getPrgProfile", e);
			no = 0l;
		}
		return no;
	}

	@Override
	public String updateStatusSelect(final Long pOffenderPrgObligationId) {
		final String sql = getQuery("UPDATE_STATUS_SELECT");
		String retVal = null;
		try {
			retVal = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("pOffenderPrgObligationId", pOffenderPrgObligationId), String.class);
		} catch (final Exception e) {
			logger.error("updateStatusSelect", e);
			retVal = null;
		}
		return retVal;
	}

	@Override
	public void updateStatusUpOperation(final Long pOffenderPrgObligationId, final String userName) {
		final String sql = getQuery("UPDATE_STATUS_UP_OPERATION");
		final Map<String, Object> inParamMap = new HashMap<String, Object>();

		inParamMap.put("pOffenderPrgObligationId", pOffenderPrgObligationId);
		inParamMap.put("modifyUserId", userName);
		try {
			namedParameterJdbcTemplate.update(sql, inParamMap);
		} catch (final Exception e) {
			logger.error("updateStatusUpOperation", e);
		}
	}

	@Override
	public Long getRetOffPrgRefId() {
		final String sql = getQuery("GET_RET_OFF_PRG_REF_ID");
		Long no = 0l;
		try {
			no = namedParameterJdbcTemplate.queryForObject(sql, createParams(), Long.class);
		} catch (final Exception e) {
			logger.error("getRetOffPrgRefId", e);
			no = 0l;
		}
		return no;
	}

	@Override
	public void insertOffProgramProfiles(final Long retOffPrgRefId, final Long pOffenderBookId, final Long lvProgramId,
			final Long pCrsActyId, final Long pOffenderPrgObligationId, final Long pParentOffPrgrefId,
			final Long lvProgramOffPrgrefId, final Date lvScheduleStartDate, final Long lvStartSessionNo,
			final Date lvScheduleEndDate, final String userName) {
		final String sql = getQuery("INSERT_OFF_PROGRAM_PROFILES");
		final Map<String, Object> inParamMap = new HashMap<String, Object>();

		inParamMap.put("RETOFFPRGREFID", retOffPrgRefId);
		inParamMap.put("P_OFFENDER_BOOK_ID", pOffenderBookId);
		inParamMap.put("LV_PROGRAM_ID", lvProgramId);

		inParamMap.put("P_CRS_ACTY_ID", pCrsActyId);
		inParamMap.put("P_OFFENDER_PRG_OBLIGATION_ID", pOffenderPrgObligationId);
		inParamMap.put("P_PARENT_OFF_PRGREF_ID", pParentOffPrgrefId);

		inParamMap.put("LV_PROGRAM_OFF_PRGREF_ID", null);
		inParamMap.put("LV_SCHEDULE_START_DATE", lvScheduleStartDate);
		inParamMap.put("LV_START_SESSION_NO", lvStartSessionNo);
		inParamMap.put("LV_SCHEDULE_END_DATE", lvScheduleEndDate);
		inParamMap.put("createUserId", userName);

		try {
			namedParameterJdbcTemplate.update(sql, inParamMap);
		} catch (final Exception e) {
			logger.error("insertOffProgramProfiles", e);
		}
	}

	@Override
	public void insertCreateOppAttendances(final Long lvOffPrgrefId, final String userName) {
		final String sql = getQuery("INSERT_CREATE_OPP_ATTENDANCES");
		final Map<String, Object> inParamMap = new HashMap<String, Object>();

		inParamMap.put("p_off_prgref_id", lvOffPrgrefId);
		inParamMap.put("createUserId", userName);
		try {
			namedParameterJdbcTemplate.update(sql, inParamMap);
		} catch (final Exception e) {
			logger.error("insertCreateOppAttendances", e);
		}
	}

	@Override
	public List<CourseActivities> modCur(final Long pPhaseInstanceId) {
		final String sql = getQuery("GET_MOD_CUR");
		List<CourseActivities> couAct = new ArrayList<CourseActivities>();

		final RowMapper<CourseActivities> rowMapper = Row2BeanRowMapper.makeMapping(sql, CourseActivities.class,
				couActyMapping);
		try {
			couAct = namedParameterJdbcTemplate.query(sql, createParams("pPhaseInstanceId", pPhaseInstanceId),
					rowMapper);
		} catch (final Exception e) {
			logger.error("modCurS", e);
		}
		return couAct;

	}

	@Override
	public List<OffenderProgramProfiles> allocCur(final Long crsActyId) {
		List<OffenderProgramProfiles> returnList = null;
		final String sql = getQuery("CS_BUILD_ACP_SCHEDULE_ALLOC_CUR");
		final RowMapper<OffenderProgramProfiles> offPPRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderProgramProfiles.class, mapping);
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams("P_CRS_ACTY_ID", crsActyId),
					offPPRowMapper);
		} catch (final Exception e) {
			logger.error("Exception :", e);
		}
		return returnList;
	}

	@Override
	public Date endCur(final Long pOffPrgrefId, final Date lvBigDate) {
		final String sql = getQuery("CS_BUILD_ACP_SCHEDULE_END_CUR");
		Date retVal = null;
		try {
			retVal = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("LV_BIG_DATE", lvBigDate, "P_OFF_PRGREF_ID", pOffPrgrefId), Date.class);
		} catch (final Exception e) {
			logger.error("Exception :", e);
		}

		return retVal;
	}

	@Override
	public Integer updateOffenderProgramProfiles(final Long pOffPrgrefId, final Date lvOffenderStartDate,
			final Date lvOffenderEndDate, final String userName) {

		final String sql = getQuery("CS_BUILD_ACP_SCHEDULE_UPDATE_OFFENDER_PROGRAM_PROFILES");
		Integer count = null;
		try {
			count = namedParameterJdbcTemplate.update(sql,
					createParams("OFF_PRGREF_ID", pOffPrgrefId, "LV_OFFENDER_START_DATE", lvOffenderStartDate,
							"LV_OFFENDER_END_DATE", lvOffenderEndDate, "modifyUserId", userName));
		} catch (final Exception e) {
			logger.error("Exception :", e);
		}
		return count;
	}

	@Override
	public BigDecimal getCrsSchId() {
		final String sql = getQuery("GET_CRS_SCH_ID");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams(), BigDecimal.class);
	}

	@Override
	public void insertCourseSchedules(final OffenderCourseAttendance courseSchedules) {
		final String sql = getQuery("INSERT_COURSE_SCHEDULES");
		final Map<String, Object> inParamMap = new HashMap<String, Object>();

		inParamMap.put("v_crs_sch_id", courseSchedules.getCrsSchId());
		inParamMap.put("p_schedule_date", courseSchedules.getScheduleDate());

		inParamMap.put("p_start_time", courseSchedules.getStartTime());
		inParamMap.put("p_end_time", courseSchedules.getEndTime());
		inParamMap.put("p_catch_up_crs_sch_id", courseSchedules.getCatchUpCrsSchId());
		inParamMap.put("createUserId", courseSchedules.getCreateUserId());
		namedParameterJdbcTemplate.update(sql, inParamMap);
	}

	@Override
	public Date getOffenderDates(final Long offenderBooId) {
		final String sql = getQuery("GET_OFFENDER_DATES");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("p_offender_book_id", offenderBooId),
				Date.class);
	}

	@Override
	public CourseSchedules getCourseScheduleRec(final Long pCrsSchId) {
		final String sql = getQuery("GET_COURSE_SCHEDULE_REC");
		CourseSchedules bean = new CourseSchedules();
		try {
			bean = namedParameterJdbcTemplate.queryForObject(sql, createParams("P_CRS_SCH_ID", pCrsSchId),
					CourseSchedules.class);
		} catch (final Exception e) {
			logger.error("GET_COURSESCHEDULE_REC :" + e);
		}
		return bean;
	}

	@Override
	public Long getOffCourseAttendChecksum(final Long eventId) {
		final String sql = getQuery("GET_OFF_COURSE_ATTEND_CHECKSUM");
		Long checkSum = null;
		try {
			checkSum = namedParameterJdbcTemplate.queryForObject(sql, createParams("p_event_id", eventId), Long.class);
		} catch (final Exception e) {
			logger.error("GET_OFF_COURSE_ATTENDCHECK_SUM :" + e);
		}
		return checkSum;
	}

	@Override
	public List<OffenderPrgObligations> offPrgProfCur(final Long offenderBookId) {
		final String sql = getQuery("TAG_PROGRAMMES_OFF_PRG_PROF_CUR");
		final RowMapper<OffenderPrgObligations> inrowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderPrgObligations.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams("offenderBookId", offenderBookId), inrowMapper);
		} catch (final Exception e) {
			logger.error("offPrgProfCur", e);
			return Collections.emptyList();
		}
	}

	@Override
	public Integer updateOffenderProgPros(final Long offenderBookId, final Long offenPrgObligaId,
			final OffenderPrgObligations refObject) {
		final String sql = getQuery("TAG_PROGRAMMES_OFFENDER_PROGRAM_PROFILES_UPDATE");
		try {
			return namedParameterJdbcTemplate.update(sql, createParams("sysdate", refObject.getCreateDatetime(),
					"offenderBookId", offenderBookId, "offenPrgObligaId", offenPrgObligaId,"modifyUserId",refObject.getModifyUserId()));
		} catch (final Exception e) {
			logger.error("updateOffenderProgPros :", e);
			return null;
		}
	}

	@Override
	public Integer updateOffenderPrgOblig(final Long offenderBookId) {
		final String sql = getQuery("TAG_PROGRAMMES_OFFENDER_PRG_OBLIGATIONS_UPDATE");
		try {
			return namedParameterJdbcTemplate.update(sql, createParams("offenderBookId", offenderBookId));
		} catch (final Exception e) {
			logger.error("updateOffenderPrgOblig :", e);
			return null;
		}
	}

	@Override
	public List<OffenderPrgObligationHty> getOldRecordForUpdateStatus(final Integer offenderPrgObligationId) {
		final String sql = getQuery("GET_OLD_RECORD_FOR_UPDATE_STATUS");
		List<OffenderPrgObligationHty> list = new ArrayList<>();
		final RowMapper<OffenderPrgObligationHty> inrowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderPrgObligationHty.class, mMapping);
		try {
			list = namedParameterJdbcTemplate.query(sql,
					createParams("OFFENDER_PRG_OBLIGATION_ID", offenderPrgObligationId), inrowMapper);
		} catch (Exception e) {
			logger.error("getOldRecordForUpdateStatus :" + e);
		}
		return list;

	}

	@Override
	public Integer insertOffenderCourseAttendancesForCatchup(OffenderCourseAttendance offCouAttds) {
		Integer retVal = 1;
		final String sqlQuery = getQuery("INSERT_OFFENDER_COURSE_ATTENDANCES_FOR_CATCH_UP");
		Map<String, Object> inParams = new HashMap<String, Object>();

		inParams.put("P_OLD_CRS_SCH_ID", offCouAttds.getCatchUpCrsSchId());
		inParams.put("P_NEW_CRS_SCH_ID", offCouAttds.getCrsSchId());
		inParams.put("P_SCHEDULE_DATE", offCouAttds.getScheduleDate());
		inParams.put("P_START_TIME", offCouAttds.getStartTime());
		inParams.put("P_END_TIME", offCouAttds.getEndTime());
		inParams.put("P_OFFENDER_BOOK_ID", offCouAttds.getOffenderBookId());
		inParams.put("createUserId", offCouAttds.getCreateUserId());
		try {
			retVal = namedParameterJdbcTemplate.update(sqlQuery, inParams);
		} catch (Exception e) {
			logger.error("insertOffenderCourseAttendancesForCatchup", e);
			retVal = 0;
		}
		return retVal;
	}

	@Override
	public OffenderProgramProfiles getListSeqRange(final OffenderProgramProfiles offPrg) {
		final String sqlQuery = getQuery("GET_LIST_SEQ_RANGE");
		return namedParameterJdbcTemplate.queryForObject(sqlQuery,
				createParams("P_CRS_ACTY_ID", offPrg.getOffPrgrefId()),
				new BeanPropertyRowMapper<OffenderProgramProfiles>(OffenderProgramProfiles.class));
	}

	@Override
	public List<ProgramServices> pgmCurList(final Long programId) {
		final String sql = getQuery("PGM_CUR_LIST");
		final RowMapper<ProgramServices> tempOidcountRowMapper = Row2BeanRowMapper.makeMapping(sql,
				ProgramServices.class, mapping);
		return namedParameterJdbcTemplate.query(sql, createParams("P_PROGRAM_ID", programId), tempOidcountRowMapper);
	}

	@Override
	public void insertOffenderProgramProfiles(final OffenderProgramProfiles offPrg) {
		final String sql = getQuery("INSERT_OFFENDER_PROGRAM_PROFILES");
		try {
			namedParameterJdbcTemplate.update(sql, new BeanPropertySqlParameterSource(offPrg));
		} catch (final Exception e) {
			logger.error("insertOffenderProgramProfiles :" + e);
		}
	}

	@Override
	public Integer checkAllocationExists(final OffenderProgramProfiles offPrg) {
		final String sqlQuery = getQuery("CHECK_ALLOCATION_EXISTS");
		return namedParameterJdbcTemplate.queryForObject(sqlQuery,
				createParams("p_offender_prg_obligation_id", offPrg.getOffenderPrgObligationId(), "p_crs_acty_id",
						offPrg.getCrsActyId(), "p_parent_off_prgref_id", offPrg.getParentOffPrgrefId()),
				Integer.class);
	}

	@Override
	public Integer checkAttendanceOutcomes(final Long offPrgrefId) {
		final String sqlQuery = getQuery("CHECK_ATTENDANCE_OUTCOMES");
		return namedParameterJdbcTemplate.queryForObject(sqlQuery, createParams("p_off_prgref_id", offPrgrefId),
				Integer.class);
	}

	@Override
	public Integer checkAttendanceTaken(final Long offPrgrefId, final Date endDate) {
		final String sqlQuery = getQuery("CHECK_ATTENDANCE_TAKEN");
		try {
			return namedParameterJdbcTemplate.queryForObject(sqlQuery,
					createParams("p_off_prgref_id", offPrgrefId, "p_end_date", endDate), Integer.class);
		} catch(Exception e) {
			logger.error("Exception in TagProgrammesRepositoryImpl :: checkAttendanceTaken  " + e.getMessage());
			return 0;
		}
		
	}

	@Override
	public void updateCancelModuleAllocations(final OffenderProgramProfiles offPrg) {
		final String sql = getQuery("UPDATE_CANCEL_MODULE_ALLOCATIONS");
		namedParameterJdbcTemplate.update(sql, createParams("p_parent_off_prgref_id", offPrg.getOffPrgrefId(),
				"modifyUserId", offPrg.getModifyUserId()));
	}

	@Override
	public void updateEndModuleAllocations(final OffenderProgramProfiles offPrg) {
		final String sql = getQuery("UPDATE_END_MODULE_ALLOCATIONS");
		namedParameterJdbcTemplate.update(sql,
				createParams("p_end_date", offPrg.getOffenderEndDate(), "p_parent_off_prgref_id",
						offPrg.getOffPrgrefId(), "lv_big_date", offPrg.getCreateDatetime(), "modifyUserId",
						offPrg.getModifyUserId()));
	}

	@Override
	public OffenderProgramProfiles getAllocationListSeqRange(final OffenderProgramProfiles offPrg) {
		final String sqlQuery = getQuery("GET_ALLOCATION_LIST_SEQ_RANGE");
		return namedParameterJdbcTemplate.queryForObject(sqlQuery,
				createParams("p_parent_off_prgref_id", offPrg.getOffPrgrefId()),
				new BeanPropertyRowMapper<OffenderProgramProfiles>(OffenderProgramProfiles.class));
	}

	@Override
	public Integer checkSessionAllocated(final OffenderProgramProfiles offPrg) {
		final String sqlQuery = getQuery("CHECK_SESSION_ALLOCATED");
		return namedParameterJdbcTemplate.queryForObject(sqlQuery,
				createParams("p_offender_book_id", offPrg.getOffenderBookId(), "p_crs_acty_id", offPrg.getCrsActyId(),
						"p_start_session_no", offPrg.getStartSessionNo(), "p_offender_start_date",
						offPrg.getOffenderStartDate(), "p_offender_end_date", offPrg.getOffenderEndDate()),
				Integer.class);
	}

	@Override
	public Integer checkModuleAllocated(final OffenderProgramProfiles offPrg) {
		final String sqlQuery = getQuery("CHECK_MODULE_ALLOCATED");
		return namedParameterJdbcTemplate.queryForObject(sqlQuery,
				createParams("p_offender_book_id", offPrg.getOffenderBookId(), "p_crs_acty_id", offPrg.getCrsActyId(),
						"p_module_from", offPrg.getOffPrgrefId(), "p_module_to", offPrg.getOffEndDate()),
				Integer.class);
	}

	@Override
	public Integer validAllocationEndDate(final OffenderProgramProfiles offPrg) {
		final String sqlQuery = getQuery("VALID_ALLOCATION_END_DATE");
		return namedParameterJdbcTemplate.queryForObject(sqlQuery,
				createParams("p_off_prgref_id", offPrg.getOffPrgrefId(), "p_end_date", offPrg.getOffEndDate()),
				Integer.class);
	}

	@Override
	public List<OffenderCourseAttendances> attCur(final OffenderProgramProfiles offPrg) {
		final String sql = getQuery("ATT_CUR");
		final RowMapper<OffenderCourseAttendances> inrowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderCourseAttendances.class, mMapping);
		return namedParameterJdbcTemplate.query(sql,
				createParams("p_off_prgref_id", offPrg.getOffPrgrefId(), "p_end_date", offPrg.getOffEndDate()),
				inrowMapper);
	}

	@Override
	public void deleteOffenderCourseAttendances(OffenderCourseAttendances offCou) {
		final String sqlQuery = getQuery("DELETE_OFFENDER_COURSE_ATTENDANCES");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "offender_course_attendances";
			String whereCondition = "event_id = :eventId";
			inputMap.put("eventId", offCou.getEventId());
			inputMap.put("modifyUserId", offCou.getModifyUserId());
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteOffenderCourseAttendances " + e.getMessage());
		}
		namedParameterJdbcTemplate.update(sqlQuery, createParams("eventId", offCou.getEventId()));
	}

	@Override
	public List<CourseActivities> crsCur(final Long crsActyId) {
		final String sql = getQuery("CRS_CUR");
		final RowMapper<CourseActivities> inrowMapper = Row2BeanRowMapper.makeMapping(sql, CourseActivities.class,
				mMapping);
		return namedParameterJdbcTemplate.query(sql, createParams("p_phase_crs_acty_id", crsActyId), inrowMapper);
	}

	@Override
	public void offenderCourseAttendancesDelete(final Long offenderPrgObligationId, final Long crsActyId,
			final Long parentOffPrgrefId,String modifyUserId) {
		final String sqlQuery = getQuery("OFFENDER_COURSE_ATTENDANCES_DELETE");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "offender_course_attendances";
			String whereCondition = "off_prgref_id in ( select off_prgref_id from offender_program_profiles where offender_prg_obligation_id = :p_offender_prg_obligation_id and crs_acty_id = :p_crs_acty_id and parent_off_prgref_id = :p_parent_off_prgref_id)";
			inputMap.put("modifyUserId", modifyUserId);
			inputMap.put("p_offender_prg_obligation_id", offenderPrgObligationId);
			inputMap.put("p_crs_acty_id", crsActyId);
			inputMap.put("p_parent_off_prgref_id", parentOffPrgrefId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in offenderCourseAttendancesDelete " + e.getMessage());
		}
		namedParameterJdbcTemplate.update(sqlQuery, createParams("p_offender_prg_obligation_id",
				offenderPrgObligationId, "p_crs_acty_id", crsActyId, "p_parent_off_prgref_id", parentOffPrgrefId));
	}

	@Override
	public void offenderProgramProfilesDelete(Long offenderPrgObligationId, Long crsActyId, Long parentOffPrgrefId,String modifyUserId) {
		final String sqlQuery = getQuery("OFFENDER_PROGRAM_PROFILES_DELETE");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "offender_program_profiles";
			String whereCondition = "offender_prg_obligation_id = :p_offender_prg_obligation_id and crs_acty_id = :p_crs_acty_id and parent_off_prgref_id = :p_parent_off_prgref_id";
			inputMap.put("p_offender_prg_obligation_id", offenderPrgObligationId);
			inputMap.put("p_crs_acty_id", crsActyId);
			inputMap.put("p_parent_off_prgref_id", parentOffPrgrefId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in offenderProgramProfilesDelete " + e.getMessage());
		}
		namedParameterJdbcTemplate.update(sqlQuery, createParams("p_offender_prg_obligation_id",
				offenderPrgObligationId, "p_crs_acty_id", crsActyId, "p_parent_off_prgref_id", parentOffPrgrefId));
	}
	
	@Override
	public List<OffenderPrgObligations> getOldRecOffenderPrgObligations(final BigDecimal offenderPrgObligationId) {
		final String sql = getQuery("GET_OLD_REC_OFFENDER_PRG_OBLIGATIONS");
		final RowMapper<OffenderPrgObligations> mMMRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderPrgObligations.class, mapping);
		return namedParameterJdbcTemplate.query(sql, createParams("offenderPrgObligationId", offenderPrgObligationId),
				mMMRowMapper);
	}
}

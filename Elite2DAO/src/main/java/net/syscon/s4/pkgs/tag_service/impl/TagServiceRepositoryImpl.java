package net.syscon.s4.pkgs.tag_service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.cm.teamsworkflow.beans.TeamMembers;
import net.syscon.s4.common.beans.Caseloads;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.Areas;
import net.syscon.s4.im.beans.CourseActivities;
import net.syscon.s4.inst.casemanagement.beans.ProgramServices;
import net.syscon.s4.inst.classification.beans.Assessments;
import net.syscon.s4.pkgs.tag_service.TagServiceRepository;

@Repository
public class TagServiceRepositoryImpl extends RepositoryBase implements TagServiceRepository {

	private static Logger logger = LogManager.getLogger(TagServiceRepositoryImpl.class);

	private final Map<String, FieldMapper> mapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DESCRIPTION", new FieldMapper("description"))
			.put("ASSESSMENT_CODE", new FieldMapper("assessmentCode")).build();

	private final Map<String, FieldMapper> couActyMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CRS_ACTY_ID", new FieldMapper("crsActyId")).build();

	private final Map<String, FieldMapper> cAMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CODE", new FieldMapper("code")).put("provider_party_class", new FieldMapper("providerPartyClass"))
			.put("provider_party_id", new FieldMapper("providerPartyId"))
			.put("provider_party_code", new FieldMapper("providerPartyCode")).build();

	@Override
	public Integer chkCode(final String programCode) {
		final String sql = getQuery("C_CHK_CODE");
		Integer result=0;
		try {
			result= namedParameterJdbcTemplate.queryForObject(sql, createParams("P_PROGRAM_CODE", programCode),
					Integer.class);
		} catch (Exception e) {
			logger.error("chkCode", e);
			return 0;
		}
	
		return result;
	}

	@Override
	public Integer cChk(final Long programId) {
		final String sql = getQuery("C_CHK");
		Integer result=0;
		try {
			result= namedParameterJdbcTemplate.queryForObject(sql, createParams("P_PROGRAM_ID", programId),
					Integer.class);
		} catch (Exception e) {
			logger.error("chkCode", e);
			return 0;
		}
		if(result==0) {
			result=1;
		}
		if(result>0) {
			result=0;
		}
		return result;
	}

	@Override
	public Long updateCourseActivities(final Long pCrsActyId, final Long pTotal, final String userName) {
		final String sql = getQuery("DO_UPDATE_ON_CRS_PHASE_COURSE_ACTIVITIES");
		Long count = 0L;
		try {
			namedParameterJdbcTemplate.update(sql,
					createParams("P_TOTAL", pTotal, "P_CRS_ACTY_ID", pCrsActyId, "modifyUserId", userName));
			count = 1L;
		} catch (Exception e) {
			logger.error("Exception :", e);
			return count;
		}
		return count;
	}
	
	@Override
	public Integer updateVProgramPhases(final BigDecimal pProgramPhaseId, final BigDecimal pTotal) {
		final String sql = getQuery("DO_UPDATE_ON_PHASE_UPDATE_V_PROGRAM_PHASES");
		Integer retVal = null;
		try {
			namedParameterJdbcTemplate.update(sql,
					createParams("P_TOTAL", pTotal, "P_PROGRAM_PHASE_ID", pProgramPhaseId));
			retVal = 1;
		} catch (Exception e) {
			logger.error("Exception :", e);
			retVal = 0;
		}

		return retVal;
	}

	@Override
	public Integer deleteCourseActivityAreasDeleteOperation(final Long crsActyId,String modifyUserId) {
		String sql = getQuery("DELETE_COURSE_ACTIVITY_AREAS_DELETE_OPERATION");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "COURSE_ACTIVITY_AREAS";
			String whereCondition = "crs_acty_id = :crsActyId";
			inputMap.put("crsActyId", crsActyId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(
					this.getClass().getName() + " error in deleteCourseActivityAreasDeleteOperation " + e.getMessage());
		}
		try {
			namedParameterJdbcTemplate.update(sql,createParams("crsActyId", crsActyId));
		} catch (Exception e) {
			logger.error("deleteCourseActivityAreasDeleteOperation", e);
			return 0;
		}
		return 1;
	}

	@Override
	public Assessments getAssessmentDetails(final Long assessmentId) {
		String sql = getQuery("GET_ASSESSMENT_DETAILS");
		Assessments ass = new Assessments();
		final RowMapper<Assessments> mRowMapper = Row2BeanRowMapper.makeMapping(sql, Assessments.class, mapping);
		try {
			ass = namedParameterJdbcTemplate.queryForObject(sql, createParams("P_ASSESSMENT_ID", assessmentId),
					mRowMapper);
		} catch (Exception e) {
			logger.error("getAssessmentDetails :", e);
		}
		return ass;
	}

	@Override
	public CourseActivities getAllocationInfo(final Long crsActyId) {
		final String sql = getQuery("GET_ALLOCATION_INFO_ONE");
		final RowMapper<CourseActivities> csRowMapper = Row2BeanRowMapper.makeMapping(sql, CourseActivities.class,
				cAMapping);
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("P_CRS_ACTY_ID", crsActyId), csRowMapper);
	}

	@Override
	public Areas cArea(final String areaCode) {
		final String sql = getQuery("SELECT_C_AREA");
		Areas area = null;
		try {
			area = namedParameterJdbcTemplate.queryForObject(sql, createParams("areaCode", areaCode),
					BeanPropertyRowMapper.newInstance(Areas.class));
		} catch (Exception e) {
			logger.error("cArea", e);
		}
		return area;
	}

	@Override
	public TeamMembers getCommDefault(final String userId) {
		final String sql = getQuery("GET_COMM_DEFAULT");
		final RowMapper<TeamMembers> mRowMapper = Row2BeanRowMapper.makeMapping(sql, TeamMembers.class, mapping);
		TeamMembers members = new TeamMembers();
		try {
			members = namedParameterJdbcTemplate.queryForObject(sql, createParams("P_USER_ID", userId), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("getCommDefault", e);
		}
		return members;
	}

	@Override
	public CourseActivities getCrsDetails(Long pCrsActyId) {
		final String sql = getQuery("GET_CRS_DETAILS");
		CourseActivities couAct = new CourseActivities();

		final RowMapper<CourseActivities> rowMapper = Row2BeanRowMapper.makeMapping(sql, CourseActivities.class,
				couActyMapping);
		try {
			couAct = namedParameterJdbcTemplate.queryForObject(sql, createParams("pCrsActyId", pCrsActyId), rowMapper);
		} catch (Exception e) {
			logger.error("getCrsDetails", e);
		}
		return couAct;
	}

	@Override
	public List<CourseActivities> getCrsDetailsTwo(final Long pPhaseInstanceId) {
		final String sql = getQuery("GET_CRS_DETAILS_TWO");
		final RowMapper<CourseActivities> rowMapper = Row2BeanRowMapper.makeMapping(sql, CourseActivities.class,
				mapping);
		List<CourseActivities> retList = new ArrayList<CourseActivities>();
		try {
			retList = namedParameterJdbcTemplate.query(sql, createParams("pPhaseInstanceId", pPhaseInstanceId),
					rowMapper);
		} catch (Exception e) {
			logger.error("getCrsDetailsTwo", e);
			retList = null;
		}
		return retList;
	}

	@Override
	public ProgramServices getPrgSrvDetails(final Long lvProgramId) {
		final String sql = getQuery("GET_PRG_SRV_DETAILS");
		ProgramServices proSer = new ProgramServices();

		final RowMapper<ProgramServices> rowMapper = Row2BeanRowMapper.makeMapping(sql, ProgramServices.class,
				couActyMapping);
		try {
			proSer = namedParameterJdbcTemplate.queryForObject(sql, createParams("lvProgramId", lvProgramId),
					rowMapper);
		} catch (Exception e) {
			logger.error("getPrgSrvDetails", e);
		}
		return proSer;

	}

	@Override
	public Integer getNextPrgSrvListSeq(Long pParentProgId) {
		final String sql = getQuery("GET_NEXT_PRG_SRV_LIST_SEQ");
		Integer count = null;
		try {
			count = namedParameterJdbcTemplate.queryForObject(sql, createParams("p_parent_prog_id", pParentProgId),
					Integer.class);
		} catch (Exception e) {
			logger.error("Exception :", e);
			count = 0;
		}
		return count;
	}

	@Override
	public Integer checkNextPrgSrvSeqUnique(final Integer pParentProgId, final BigDecimal pListSeq) {
		final String sql = getQuery("CHECK_NEXT_PRG_SRV_SEQ_UNIQUE");
		Integer count = null;
		try {
			count = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("p_parent_prog_id", pParentProgId, "p_list_seq", pListSeq), Integer.class);
		} catch (EmptyResultDataAccessException e) {
			count = 0;
			logger.error("checkNextPrgSrvSeqUnique :", e);
		}
		return count;
	}

	@Override
	public Integer getProgramIdSeq() {
		final String sql = getQuery("GET_PROGRAM_ID_SEQ");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams(), Integer.class);
	}

	@Override
	public Long getCrsSessionCount(Long pCrsActyId) {
		final String sql = getQuery("GET_CRS_SESSION_COUNT_SUM");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("p_crs_acty_id", pCrsActyId), Long.class);
	}

	@Override
	public Integer getCrsActyChecksum(Long pCrsActyId) {
		final String sql = getQuery("GET_CRS_ACTY_CHECKSUM_CSM_CUR");
		return namedParameterJdbcTemplate
				.queryForObject(sql, createParams("p_crs_acty_id", pCrsActyId), BigDecimal.class).intValue();
	}

	@Override
	public Long getNextCsRuleSeq() {
		final String sql = getQuery("COURSE_SCHEDULE_RULE_ID");
		Long seq = null;
		try {
			seq = namedParameterJdbcTemplate.queryForObject(sql, createParams(), Long.class);
		} catch (Exception e) {
			logger.error("getNextCsRuleSeq :" + e);
		}
		return seq;
	}

	@Override
	public Long preInsertProgramService(final ProgramServices bean) {
		final String sql = getQuery("PRE_INSERT_PROGRAM_SERVICE");
		Long count = null;
		try {
			count = namedParameterJdbcTemplate.queryForObject(sql, createParams(), Long.class);
		} catch (Exception e) {
			logger.error("PreInsertProgramService :" + e);
		}
		return count;
	}

	@Override
	public Long getNextCsSeq() {
		final String sql = getQuery("CRS_SCH_ID");
		Long seq = null;
		try {
			seq = namedParameterJdbcTemplate.queryForObject(sql, createParams(), Long.class);
		} catch (Exception e) {
			logger.error("getNextCsSeq :" + e);
		}
		return seq;
	}

	@Override
	public Date getLastSchedDate(final Long pCrsActyId) {
		final String sql = getQuery("GET_LAST_SCHED_DATE");
		Date lastSchedDate = null;
		try {
			lastSchedDate = namedParameterJdbcTemplate.queryForObject(sql, createParams("P_CRS_ACTY_ID", pCrsActyId),
					Date.class);
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return lastSchedDate;
	}

	@Override
	public Caseloads getWorkingCaseload(final String user) {
		final String sql = getQuery("GET_WORKING_CASELOAD");
		Caseloads obj = new Caseloads();
		try {
			obj = namedParameterJdbcTemplate.queryForObject(sql, createParams("P_USER_ID", user), Caseloads.class);
		} catch (Exception e) {
			logger.error("getWorkingCaseload :" + e);
		}
		return obj;
	}
	
	public List<CourseActivities> getCaDates(final Long pCrsActyId) {
		final String sql = getQuery("GET_CA_DATES");
		final RowMapper<CourseActivities> rowMapper = Row2BeanRowMapper.makeMapping(sql, CourseActivities.class,
				mapping);
		List<CourseActivities> retList = new ArrayList<CourseActivities>();
		try {
			retList = namedParameterJdbcTemplate.query(sql, createParams("p_crs_acty_id", pCrsActyId),
					rowMapper);
		} catch (Exception e) {
			logger.error("getCrsDetailsTwo", e);
			retList = null;
		}
		return retList;
	}

	@Override
	public Integer deleteCourseActivityParties(Long crsActyId,String modifyUserId) {
		String sql = getQuery("DELETE_COURSE_ACTIVITY_PARTIES_DELETE_OPERATION");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "COURSE_ACTIVITY_PARTIES";
			String whereCondition = "crs_acty_id = :p_crs_acty_id";
			inputMap.put("p_crs_acty_id", crsActyId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteCourseActivityParties " + e.getMessage());
		}
		try {
			namedParameterJdbcTemplate.update(sql,createParams("p_crs_acty_id", crsActyId));
		} catch (Exception e) {
			logger.error("Excpetion occured in " + this.getClass().getName() + " in deleteCourseActivityParties()", e);
			return 0;
		}
		return 1;
	}

	@Override
	public Integer deleteCourseActivityProf(Long crsActyId,String modifyUserId) {
		String sql = getQuery("DELETE_COURSE_ACTIVITY_PROF_DELETE_OPERATION");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "COURSE_ACTIVITY_PROFILES";
			String whereCondition = "crs_acty_id IN ( SELECT crs_acty_id  FROM COURSE_ACTIVITIES WHERE crs_acty_id =  :p_crs_acty_id)";
			inputMap.put("p_crs_acty_id", crsActyId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteCourseActivityProf " + e.getMessage());
		}
		try {
			namedParameterJdbcTemplate.update(sql,createParams("p_crs_acty_id", crsActyId));
		} catch (Exception e) {
			logger.error("Excpetion occured in " + this.getClass().getName() + " in deleteCourseActivityProf()", e);
			return 0;
		}
		return 1;
	}

}
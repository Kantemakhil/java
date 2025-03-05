package net.syscon.s4.pkgs.oidcount.impl;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.inst.automatedcounts.beans.LockedModules;
import net.syscon.s4.inst.automatedcounts.beans.TempOidcount;
import net.syscon.s4.pkgs.oidcount.OidcountPkgRepository;
import oracle.jdbc.internal.OracleTypes;

@Repository
public class OidcountPkgRepositoryImpl extends RepositoryBase implements OidcountPkgRepository {
	private static Logger logger = LogManager.getLogger(OidcountPkgRepositoryImpl.class.getName());

	private final Map<String, FieldMapper> mMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DESCRIPTION", new FieldMapper("description")).put("CODE", new FieldMapper("code")).build();

	@Override
	public List<LockedModules> checkdeadJobes(final String sessionId) {
		final String sql = getQuery("L_CHECK_DEAD_JOBS");
		final RowMapper<LockedModules> mRowMapper = Row2BeanRowMapper.makeMapping(sql, LockedModules.class, mMapping);
		return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
	}

	@Override
	public TempOidcount tempOidCount(final String sessionId) {
		final String sql = getQuery("L_TEMP_OIDCOUNT_CUR");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("p_session_id", sessionId),
				TempOidcount.class);
	}

	@Override
	public List<ReferenceCodes> deadJobe(final String SessionId) {
		final String sql = getQuery("L_DEAD_JOB");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams("p_session_id", SessionId), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("deadJobe" + e);
			return Collections.emptyList();
		}
	}

	@Override
	public Integer tempOidCountDelete(final String sessioId,String modifyUserId) {
		final String sql = getQuery("DELETE_TEMP_OIDCOUNT");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "temp_oidcount";
			String whereCondition = "session_id =:p_session_id";
			inputMap.put("p_session_id", sessioId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in tempOidCountDelete " + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_session_id", sessioId));
	}

	@Override
	public Integer deleteAgyLocation(final Long reportingLocId,String modifyUserId) {
		final String sql = getQuery("DELETE_AGENCYLOCATION_COUNTS");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "agency_location_counts";
			String whereCondition = "reporting_loc_id =:reporting_loc_id";
			inputMap.put("reporting_loc_id", reportingLocId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteAgyLocation " + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("reporting_loc_id", reportingLocId));
	}

	@Override
	public Integer deleteAgencyLocatCount(final Long reportingLocId,String modifyUserId) {
		final String sql = getQuery("DELETE_AGENCY_COUNTS");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "agency_counts";
			String whereCondition = "reporting_loc_id =:reporting_loc_id";
			inputMap.put("reporting_loc_id", reportingLocId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteAgencyLocatCount " + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("reporting_loc_id", reportingLocId));
	}

	@Override
	public Integer deleteLockModules(final String sessionId) {
		final String sql = getQuery("DELETE_LOCKED_MODULES");
		return namedParameterJdbcTemplate.update(sql, createParams("p_session_id", sessionId));

	}

	@Override
	public Integer isCountExist(final Integer sessionId) {
		final String sql = getQuery("SUBMIT_COUNT_JOB_COUNT_EXISTS");
		Integer count = null;
		try {
			count = namedParameterJdbcTemplate.queryForObject(sql, createParams("P_SESSION_ID", sessionId),
					Integer.class);
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return count;
	}

	@Override
	public Integer cancelCount(final Long sessionId, final String userName) {
		final String sql = getQuery("SUBMIT_COUNT_JOB_CANCEL_COUNT");
		Integer count = null;
		try {
			count = namedParameterJdbcTemplate.update(sql,
					createParams("P_SESSION_ID", sessionId, "createUserId", userName));
		} catch (Exception e) {
			logger.error("Exception :", e);
		}

		return count;
	}

	@Override
	public void insertTempOidcount(final Integer sessionId, final String agyLocId, final Integer countTypeId,
			final String scheduledTime, final String userName) {
		final String sql = getQuery("SUBMIT_COUNT_JOB_CANCEL_COUNT");
		namedParameterJdbcTemplate.update(sql, createParams("P_SESSION_ID", sessionId, "P_AGY_LOC_ID", agyLocId,
				"P_COUNT_TYPE_ID", countTypeId, "P_SCHEDULED_TIME", scheduledTime, "createUserId", userName));
	}

	@Override
	public Integer getreportingLocId(final Integer sessionId) {
		final String sql = getQuery("SUBMIT_COUNT_JOB_REPORTING_LOC_ID");
		Integer retVal = null;
		try {
			retVal = namedParameterJdbcTemplate.queryForObject(sql, createParams("P_SESSION_ID", sessionId),
					Integer.class);
		} catch (Exception e) {
			logger.error("Exception :", e);
		}

		return retVal;
	}

	@Override
	public List<TempOidcount> lTempCountCur(final Integer sessionId) {
		List<TempOidcount> returnList = null;
		final String sql = getQuery("SUBMIT_COUNT_JOB_L_TEMP_COUNT_CUR");
		final RowMapper<TempOidcount> tempOidcountRowMapper = Row2BeanRowMapper.makeMapping(sql, TempOidcount.class,
				mMapping);
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams("P_SESSION_ID", sessionId),
					tempOidcountRowMapper);
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return returnList;
	}

	@Override
	public Integer lCancelFlag(final Integer sessionId) {
		final String sql = getQuery("SUBMIT_COUNT_JOB_L_CANCEL_FLAG");
		Integer count = null;
		try {
			count = namedParameterJdbcTemplate.queryForObject(sql, createParams("P_SESSION_ID", sessionId),
					Integer.class);
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return count;
	}

	@Override
	public Integer deleteTempOidcount(final Integer sessionId,String modifyUserId) {
		final String sql = getQuery("SUBMIT_COUNT_JOB_DELETE_TEMP_OIDCOUNT");
		Integer count = null;
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "TEMP_OIDCOUNT";
			String whereCondition = "SESSION_ID = :P_SESSION_ID";
			inputMap.put("P_SESSION_ID", sessionId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteTempOidcount " + e.getMessage());
		}
		try {
			count = namedParameterJdbcTemplate.update(sql, createParams("P_SESSION_ID", sessionId));
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return count;
	}

	@Override
	public Integer lTerminatedSessionFlag(final Integer sessionId) {
		final String sql = getQuery("SUBMIT_COUNT_JOB_L_TERMINATED_SESSION_FLAG");
		Integer count = null;
		try {
			count = namedParameterJdbcTemplate.queryForObject(sql, createParams("P_SESSION_ID", sessionId),
					Integer.class);
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return count;
	}

	@Override
	public Integer deleteAgencyLocationCounts(final Integer lReportingLocId,String modifyUserId) {
		final String sql = getQuery("SUBMIT_COUNT_JOB_DELETE_AGENCY_LOCATION_COUNTS");
		Integer count = null;
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "AGENCY_LOCATION_COUNTS";
			String whereCondition = "REPORTING_LOC_ID = :L_REPORTING_LOC_ID";
			inputMap.put("L_REPORTING_LOC_ID", lReportingLocId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteAgencyLocationCounts " + e.getMessage());
		}
		try {
			count = namedParameterJdbcTemplate.update(sql, createParams("L_REPORTING_LOC_ID", lReportingLocId));
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return count;
	}

	@Override
	public Integer deleteAgencyCounts(final Integer lReportingLocId,String modifyUserId) {
		final String sql = getQuery("SUBMIT_COUNT_JOB_DELETE_AGENCY_COUNTS");
		Integer count = null;
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "AGENCY_COUNTS";
			String whereCondition = "REPORTING_LOC_ID = :L_REPORTING_LOC_ID";
			inputMap.put("L_REPORTING_LOC_ID", lReportingLocId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteAgencyCounts " + e.getMessage());
		}
		try {
			count = namedParameterJdbcTemplate.update(sql, createParams("L_REPORTING_LOC_ID", lReportingLocId));
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return count;
	}

	@Override
	public Integer lCountInitCur(final String agyLocId, final Integer lowestLocationId) {
		final String sql = getQuery("SUBMIT_COUNT_JOB_L_COUNT_INIT_CUR");
		Integer count = null;
		try {
			count = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("P_AGY_LOC_ID", agyLocId, "P_LOCATION_ID", lowestLocationId), Integer.class);
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return count;
	}

	@Override
	public Integer lCountLivUnitCur(final String agyLocId, final Integer lowestLocationId) {
		final String sql = getQuery("SUBMIT_COUNT_JOB_L_COUNT_LIV_UNIT_CUR");
		Integer count = null;
		try {
			count = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("P_AGY_LOC_ID", agyLocId, "P_LOCATION_ID", lowestLocationId), Integer.class);
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return count;
	}

	@Override
	public Integer updateTempOidcountActualCount(final Integer lActualCount, final Long rowId,
			final String userName) {
		final String sql = getQuery("SUBMIT_COUNT_JOB_UPDATE_TEMP_OIDCOUNT_ACTUAL_COUNT");
		Integer count = null;
		try {
			count = namedParameterJdbcTemplate.update(sql,
					createParams("L_ACTUAL_COUNT", lActualCount, "L_ROWID", rowId, "modifyUserId", userName));
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return count;
	}

	@Override
	public Integer lCountInitMaleCur(final String agyLocId, final Integer lowestLocationId) {
		final String sql = getQuery("SUBMIT_COUNT_JOB_L_COUNT_INIT_MALE_CUR");
		Integer count = null;
		try {
			count = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("P_AGY_LOC_ID", agyLocId, "P_LOCATION_ID", lowestLocationId), Integer.class);
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return count;
	}

	@Override
	public Integer lCountLivUnitMaleCur(final String agyLocId, final Integer lowestLocationId) {
		final String sql = getQuery("SUBMIT_COUNT_JOB_L_COUNT_LIV_UNIT_MALE_CUR");
		Integer count = null;
		try {
			count = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("P_AGY_LOC_ID", agyLocId, "P_LOCATION_ID", lowestLocationId), Integer.class);
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return count;
	}

	@Override
	public Integer updateTempOidcountTempMale(final Integer lTotalMale, final Long rowId, final String userName) {
		final String sql = getQuery("SUBMIT_COUNT_JOB_UPDATE_TEMP_OIDCOUNT_TOTAL_MALE");
		Integer count = null;
		try {
			count = namedParameterJdbcTemplate.update(sql,
					createParams("L_TOTAL_MALE", lTotalMale, "L_ROWID", rowId, "modifyUserId", userName));
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return count;
	}

	@Override
	public Integer lCountInitFemaleCur(final String agyLocId, final Integer lowestLocationId) {
		final String sql = getQuery("SUBMIT_COUNT_JOB_L_COUNT_INIT_FEMALE_CUR");
		Integer count = null;
		try {
			count = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("P_AGY_LOC_ID", agyLocId, "P_LOCATION_ID", lowestLocationId), Integer.class);
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return count;
	}

	@Override
	public Integer lCountLivUnitFemaleCur(final String agyLocId, final Integer lowestLocationId) {
		final String sql = getQuery("SUBMIT_COUNT_JOB_L_COUNT_LIV_UNIT_FEMALE_CUR");
		Integer count = null;
		try {
			count = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("P_AGY_LOC_ID", agyLocId, "P_LOCATION_ID", lowestLocationId), Integer.class);
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return count;
	}

	@Override
	public Integer updateTempOidcountTempFemale(final Integer lTotalFemale, final Long rowId, final String userName) {
		final String sql = getQuery("SUBMIT_COUNT_JOB_UPDATE_TEMP_OIDCOUNT_TOTAL_FEMALE");
		Integer count = null;
		try {
			count = namedParameterJdbcTemplate.update(sql,
					createParams("L_TOTAL_FEMALE", lTotalFemale, "L_ROWID", rowId, "modifyUserId", userName));
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return count;
	}

	@Override
	public Integer lCountInitOtherCur(final String agyLocId, final Integer lowestLocationId) {
		final String sql = getQuery("SUBMIT_COUNT_JOB_L_COUNT_INIT_OTHER_CUR");
		Integer count = null;
		try {
			count = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("P_AGY_LOC_ID", agyLocId, "P_LOCATION_ID", lowestLocationId), Integer.class);
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return count;
	}

	@Override
	public Integer lCountLivUnitOtherCur(final String agyLocId, final Integer lowestLocationId) {
		final String sql = getQuery("SUBMIT_COUNT_JOB_L_COUNT_LIV_UNIT_OTHER_CUR");
		Integer count = null;
		try {
			count = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("P_AGY_LOC_ID", agyLocId, "P_LOCATION_ID", lowestLocationId), Integer.class);
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return count;
	}

	@Override
	public Integer updateTempOidcountTotalOther(final Integer lTotalOther, final Long rowId, final String userName) {
		final String sql = getQuery("SUBMIT_COUNT_JOB__UPDATE_TEMP_OIDCOUNT_TOTAL_OTHER");
		Integer count = null;
		try {
			count = namedParameterJdbcTemplate.update(sql,
					createParams("L_TOTAL_OTHER", lTotalOther, "L_ROWID", rowId, "modifyUserId", userName));
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return count;
	}

	@Override
	public Integer lOutInitCur(final String agyLocId, final Integer lowestLocationId) {
		final String sql = getQuery("SUBMIT_COUNT_JOB_L_OUT_INIT_CUR");
		Integer count = null;
		try {
			count = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("P_AGY_LOC_ID", agyLocId, "P_LOCATION_ID", lowestLocationId), Integer.class);
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return count;
	}

	@Override
	public Integer lOutLivUnitCur(final String agyLocId, final Integer lowestLocationId) {
		final String sql = getQuery("SUBMIT_COUNT_JOB_L_OUT_LIV_UNIT_CUR");
		Integer count = null;
		try {
			count = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("P_AGY_LOC_ID", agyLocId, "P_LOCATION_ID", lowestLocationId), Integer.class);
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return count;
	}

	@Override
	public Integer updateTempOidcountOutTotal(final Integer lOutTotal, final Long rowId, final String userName) {
		final String sql = getQuery("SUBMIT_COUNT_JOB_UPDATE_TEMP_OIDCOUNT_OUT_TOTAL");
		Integer count = null;
		try {
			count = namedParameterJdbcTemplate.update(sql,
					createParams("L_OUT_TOTAL", lOutTotal, "L_ROWID", rowId, "modifyUserId", userName));
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return count;
	}

	@Override
	public Integer lTotalMaleOutInitCur(final String agyLocId, final Integer lowestLocationId) {
		final String sql = getQuery("SUBMIT_COUNT_JOB_L_TOTAL_MALE_OUT_INIT_CUR");
		Integer count = null;
		try {
			count = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("P_AGY_LOC_ID", agyLocId, "P_LOCATION_ID", lowestLocationId), Integer.class);
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return count;
	}

	@Override
	public Integer lTotalMaleOutLivUnitCur(final String agyLocId, final Integer lowestLocationId) {
		final String sql = getQuery("SUBMIT_COUNT_JOB_L_TOTAL_MALE_OUT_LIV_UNIT_CUR");
		Integer count = null;
		try {
			count = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("P_AGY_LOC_ID", agyLocId, "P_LOCATION_ID", lowestLocationId), Integer.class);
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return count;
	}

	@Override
	public Integer updateTempOidcountTotalMaleOut(final Integer lTotalMaleOut, final Long rowId,
			final String userName) {
		final String sql = getQuery("SUBMIT_COUNT_JOB_UPDATE_TEMP_OIDCOUNT_TOTAL_MALE_OUT");
		Integer count = null;
		try {
			count = namedParameterJdbcTemplate.update(sql,
					createParams("L_TOTAL_MALE_OUT", lTotalMaleOut, "L_ROWID", rowId, "modifyUserId", userName));
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return count;
	}

	@Override
	public Integer lTotalFemaleOutInitCur(final String agyLocId, final Integer lowestLocationId) {
		final String sql = getQuery("SUBMIT_COUNT_JOB_L_TOTAL_FEMALE_OUT_INIT_CUR");
		Integer count = null;
		try {
			count = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("P_AGY_LOC_ID", agyLocId, "P_LOCATION_ID", lowestLocationId), Integer.class);
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return count;
	}

	@Override
	public Integer lTotFemaleOutLivUnitCur(final String agyLocId, final Integer lowestLocationId) {
		final String sql = getQuery("SUBMIT_COUNT_JOB_L_TOT_FEMALE_OUT_LIV_UNIT_CUR");
		Integer count = null;
		try {
			count = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("P_AGY_LOC_ID", agyLocId, "P_LOCATION_ID", lowestLocationId), Integer.class);
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return count;
	}

	@Override
	public Integer updateTempOidcountTotalFemaleOut(final Integer lTotalFemaleOut, final Long rowId,
			final String userName) {
		final String sql = getQuery("SUBMIT_COUNT_JOB_UPDATE_TEMP_OIDCOUNT_TOTAL_FEMALE_OUT");
		Integer count = null;
		try {
			count = namedParameterJdbcTemplate.update(sql,
					createParams("L_TOTAL_FEMALE_OUT", lTotalFemaleOut, "L_ROWID", rowId, "modifyUserId", userName));
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return count;
	}

	@Override
	public Integer lTotalOtherOutInitCur(final String agyLocId, final Integer lowestLocationId) {
		final String sql = getQuery("SUBMIT_COUNT_JOB_L_TOTAL_OTHER_OUT_INIT_CUR");
		Integer count = null;
		try {
			count = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("P_AGY_LOC_ID", agyLocId, "P_LOCATION_ID", lowestLocationId), Integer.class);
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return count;
	}

	@Override
	public Integer lTotOtherOutLivUnitCur(final String agyLocId, final Integer lowestLocationId) {
		final String sql = getQuery("SUBMIT_COUNT_JOB_L_TOT_OTHER_OUT_LIV_UNIT_CUR");
		Integer count = null;
		try {
			count = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("P_AGY_LOC_ID", agyLocId, "P_LOCATION_ID", lowestLocationId), Integer.class);
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return count;
	}

	@Override
	public Integer updateTempOidcountTotalOtherOut(final Integer lTotalOtherOut, final Long rowId,
			final String userName) {
		final String sql = getQuery("SUBMIT_COUNT_JOB_UPDATE_TEMP_OIDCOUNT_TOTAL_OTHER_OUT");
		Integer count = null;
		try {
			count = namedParameterJdbcTemplate.update(sql,
					createParams("L_TOTAL_OTHER_OUT", lTotalOtherOut, "L_ROWID", rowId, "modifyUserId", userName));
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return count;
	}

	@Override
	public Integer getJob(final Integer sessionId) {
		final String sql = getQuery("SUBMIT_COUNT_JOB_GET_JOB");
		Integer count = null;
		try {
			count = namedParameterJdbcTemplate.update(sql, createParams("P_SESSION_ID", sessionId));
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return count;
	}

	@Override
	public void removeOidcountJob(final Integer lJob) {
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[4];
		sqlParameters = new SqlParameter[] { new SqlParameter("", OracleTypes.NUMBER), };
		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("DBMS_JOB").withFunctionName("REMOVE").declareParameters(sqlParameters);
		inParamMap.put("", lJob);

		final SqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);

		try {
			simpleJDBCCall.execute(inParameter);
		} catch (Exception e) {
			logger.error(e);

		}

	}

	@Override
	public Integer cancelCount(final Long pSessionId) {
		final String sql = getQuery("INSERT_CANCEL_COUNT");
		Integer count = null;
		try {
			count = namedParameterJdbcTemplate.update(sql, createParams("session_id", pSessionId));
		} catch (DataAccessException e) {
			logger.error("CANCEL_COUNT :", e);
		}
		return count;
	}

}
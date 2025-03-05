package net.syscon.s4.inst.institutionalactivities.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.inst.institutionalactivities.OidacselRepository;
import net.syscon.s4.inst.institutionalactivities.beans.VPrisonActivities;

@Repository
public class OidacselRepositoryImpl extends RepositoryBase implements OidacselRepository {

	/**
	 * Creates new OidacselRepositoryImpl class Object
	 */
	public OidacselRepositoryImpl() {
	}

	private final Map<String, FieldMapper> vPrisonMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("SCHEDULE_END_DATE", new FieldMapper("scheduleEndDate"))
			.put("INTERNAL_LOCATION_DESC", new FieldMapper("internalLocationDesc"))
			.put("CRS_ACTY_ID", new FieldMapper("crsActyId")).put("ACTIVE_FLAG", new FieldMapper("activeFlag"))
			.put("LIST_SEQ", new FieldMapper("listSeq")).put("PROGRAM_ID", new FieldMapper("programId"))
			.put("PROGRAM_CODE", new FieldMapper("programCode")).put("SERVICE", new FieldMapper("service"))
			.put("AGY_LOC_DESC", new FieldMapper("agyLocDesc")).put("AGY_LOC_ID", new FieldMapper("agyLocId"))
			.put("INTERNAL_LOCATION_ID", new FieldMapper("internalLocationId"))
			.put("ACTIVITY", new FieldMapper("activity"))
			.put("SCHEDULE_START_DATE", new FieldMapper("scheduleStartDate")).build();

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao VPrisonActivities
	 *
	 * @return List<VPrisonActivities>
	 *
	 * @throws SQLException
	 */
	public List<VPrisonActivities> scheduledActivitiesExecuteQuery(final VPrisonActivities objSearchDao) {

		final String sql = getQuery("OIDACSEL_SCHEDULEDACTIVITIES_FIND_V_PRISON_ACTIVITIES");

		String preparedSql = null;
		final StringBuffer sqlQuery = new StringBuffer();
		String preSqlQuery = null;
		final MapSqlParameterSource params = new MapSqlParameterSource();
		sqlQuery.append(sql);
		if (objSearchDao != null) {
			sqlQuery.append("AND ");

			params.addValue("programId", objSearchDao.getProgramId());

			params.addValue("agyLocId", objSearchDao.getAgyLocId());

			if (objSearchDao.getActivity() != null) {
				sqlQuery.append(" ACTIVITY  = :activity " + "AND ");
				params.addValue("activity", objSearchDao.getActivity());
			}

			if (objSearchDao.getInternalLocationDesc() != null) {
				sqlQuery.append(" INTERNAL_LOCATION_DESC  = :internalLocationDesc " + "AND ");
				params.addValue("internalLocationDesc", objSearchDao.getInternalLocationDesc());
			}

			if (objSearchDao.getScheduleStartDate() != null) {
				sqlQuery.append(" SCHEDULE_START_DATE  = :scheduleStartDate " + "AND ");
				params.addValue("scheduleStartDate", objSearchDao.getScheduleStartDate());
			}
			if (objSearchDao.getScheduleEndDate() != null) {
				sqlQuery.append(" SCHEDULE_END_DATE  = :scheduleEndDate " + " AND ");
				params.addValue("scheduleEndDate", objSearchDao.getScheduleEndDate());
			}

		}
		preparedSql = sqlQuery.toString().trim();
		if (preparedSql.endsWith("WHERE")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 5);
		}
		if (preparedSql.endsWith("AND")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 3);
		}

		preSqlQuery = preparedSql.concat("  ORDER BY activity, schedule_start_date asc");
		final RowMapper<VPrisonActivities> mapper = Row2BeanRowMapper.makeMapping(preSqlQuery, VPrisonActivities.class,
				vPrisonMapping);
		return namedParameterJdbcTemplate.query(preSqlQuery, params, mapper);

	}

}

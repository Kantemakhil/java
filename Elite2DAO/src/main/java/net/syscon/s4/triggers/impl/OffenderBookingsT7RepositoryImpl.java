package net.syscon.s4.triggers.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.OffenderBookings;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.inst.workflow.maintenance.beans.AgyLocTeamFunctions;
import net.syscon.s4.inst.workflow.managingteams.beans.OffenderTeamAssignments;
import net.syscon.s4.triggers.OffenderBookingsT7Repository;

@Repository
public class OffenderBookingsT7RepositoryImpl extends RepositoryBase implements OffenderBookingsT7Repository {
	private static Logger logger = LogManager.getLogger(OffenderBookingsT7RepositoryImpl.class);
	private final Map<String, FieldMapper> mmMapping = new ImmutableMap.Builder<String, FieldMapper>().build();

	@Override
	public OffenderBookings getOffenderBookings(final Long offenderBookId) {
		final String sql = getQuery("OFFENDER_BOOKINGS_T7_OFFENDER_BOOKINGS");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql, createParams("offenderBookId", offenderBookId),
					new BeanPropertyRowMapper<OffenderBookings>(OffenderBookings.class));
		} catch (final Exception e) {
			logger.error("getOffenderBookings  ", e);
			return null;
		}
	}

	@Override
	public List<AgyLocTeamFunctions> getAgyLocTeamFunsCur(final String agyLocId) {
		final String sql = getQuery("OFFENDER_BOOKINGS_T7_GET_AGY_LOC_TEAM_FUNS_CUR");
		final RowMapper<AgyLocTeamFunctions> mRowMapper = Row2BeanRowMapper.makeMapping(sql, AgyLocTeamFunctions.class,
				mmMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams("pAgyLocId", agyLocId), mRowMapper);
		} catch (final Exception e) {
			logger.error("getAgyLocTeamFunsCur  ", e);
			return null;
		}
	}

	@Override
	public Long isOffTeamAssignExists(final Long offenderBookId, final String functionType) {
		final String sql = getQuery("OFFENDER_BOOKINGS_T7_IS_OFF_TEAM_ASSIGN_EXISTS");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql,
					createParams("pOffBookId", offenderBookId, "pFunctionType", functionType), Long.class);
		} catch (final Exception e) {
			logger.error("isOffTeamAssignExists  ", e);
			return 0l;
		}
	}

	@Override
	public Integer insOffenderTeamAssignments(final OffenderTeamAssignments offenTeamAssign) {
		final String sql = getQuery("OFFENDER_BOOKINGS_T7_INS_OFFENDER_TEAM_ASSIGNMENTS");
		Integer returnArray=null;
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		parameters.add(new BeanPropertySqlParameterSource(offenTeamAssign));
		try {
			returnArray = namedParameterJdbcTemplate.update(sql,new BeanPropertySqlParameterSource(offenTeamAssign));
		} catch (final Exception e) {
			logger.error("insOffenderTeamAssignments", e);
		}
		return returnArray;
	}

	@Override
	public Integer offenderTeamAssignmentsDelete(final OffenderTeamAssignments offenTeamAssignm) {
		final String sql = getQuery("OFFENDER_BOOKINGS_T7_OFFENDER_TEAM_ASSIGNMENTS_DELETE");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		parameters.add(new BeanPropertySqlParameterSource(offenTeamAssignm));
		try {
			String tableName = "offender_team_assignments";
			String whereClause = "offender_book_id = :offenderBookId AND function_type = item.function_type";
			batchUpdatePreDeletedRows(tableName, whereClause , parameters);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method offenderTeamAssignmentsDelete", e);
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (final Exception e) {
			logger.error("offenderTeamAssignmentsDelete", e);
		}
		if (1 == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

}

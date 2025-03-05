package net.syscon.s4.triggers.impl;

import org.springframework.stereotype.Repository;

import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.inst.workflow.managingteams.beans.OffenderTeamAssignments;
import net.syscon.s4.triggers.OffenderTeamAssignmentsT1Repository;

@Repository
public class OffenderTeamAssignmentsT1RepositoryImpl extends RepositoryBase
		implements OffenderTeamAssignmentsT1Repository {

	@Override
	public Integer insertIntoOffenderTeamAssignHty(final OffenderTeamAssignments objSearchDao) {
		final String sql = getQuery("INSERT_INTO_OFFENDER_TEAM_ASSIGN_HTY");
		return namedParameterJdbcTemplate.update(sql,
				createParams("OFFENDER_BOOK_ID", objSearchDao.getOffenderBookId(), "FUNCTION_TYPE",
						objSearchDao.getFunctionType(), "TEAM_ID", objSearchDao.getTeamId(), "ASSIGNMENT_DATE",
						objSearchDao.getAssignmentDate(), "CREATEUSERID", objSearchDao.getCreateUserId()));
	}

}

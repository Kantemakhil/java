package net.syscon.s4.triggers.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.triggers.TeamsT1Repository;

@Repository
public class TeamsT1RepositoryImpl extends RepositoryBase implements TeamsT1Repository {
	private static final Logger logger = LogManager.getLogger(TeamsT1RepositoryImpl.class);

	@Override
	public Integer teamsT1() {
		final String sql = getQuery("TEAMS_T1_TEAMID");
		Integer teamId = null;
		try {
			teamId = namedParameterJdbcTemplate.queryForObject(sql, createParams(), Integer.class);
		} catch (Exception e) {
			logger.error("TEAMST1 :" + e);
		}
		return teamId;
	}

}

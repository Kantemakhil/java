package net.syscon.s4.triggers.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.triggers.TeamsT2Repository;
@Repository
public class TeamsT2RepositoryImpl extends RepositoryBase implements TeamsT2Repository {

	private static final Logger logger = LogManager.getLogger(TeamsT2RepositoryImpl.class);

	@Override
	public Integer teamsT2() {
		final String sql = getQuery("TEaMS_T2_SELECT");
		Integer count = null;
		try {
			count = namedParameterJdbcTemplate.queryForObject(sql, createParams(), Integer.class);
		} catch (Exception e) {
			logger.error("TEAMST2 :" + e);
		}
		return count;
	}

	@Override
	public Integer teamsMod(final Long teamId, final Integer lvProfileValue) {
		final String sql = getQuery("TEAMS_T2_MOD");
		Integer count = null;
		try {
			count = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("TEAM_ID", teamId, "LV_PROFILE_VALUE", lvProfileValue), Integer.class);
		} catch (Exception e) {
			logger.error("TEAMSMOD :" + e);
		}
		return count;
	}
}

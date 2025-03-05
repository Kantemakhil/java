package net.syscon.s4.pkgs.tag_workflow_adhoc.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.stereotype.Repository;

import net.syscon.s4.cm.teamsworkflow.beans.TagWorkflowBrowseQueue;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.im.beans.CreateAdhocEmail;
import net.syscon.s4.pkgs.MessageQueue;
import net.syscon.s4.pkgs.tag_workflow_adhoc.TagWorkflowAdhocRepository;

@Repository
public class TagWorkflowAdhocRepositoryImpl extends RepositoryBase implements TagWorkflowAdhocRepository {

	private static Logger logger = LogManager.getLogger(TagWorkflowAdhocRepositoryImpl.class.getName());

	@Override
	public Integer insertAdhocEmail(final CreateAdhocEmail bean) {
		final String sql = getQuery("INSERT_DGOCE_EMIL");
		final CreateAdhocEmail adEmail = new CreateAdhocEmail();
		Integer count = null;
		try {
			namedParameterJdbcTemplate.update(sql, new BeanPropertySqlParameterSource(adEmail));
			count = 1;
		} catch (DataAccessException e) {
			logger.error("insertAdhocEmail", e);
			count = 2;
		}
		return count;
	}

	@Override
	public Long selectTeamMembers(TagWorkflowBrowseQueue bean) {
		final String sql = getQuery("SELECT_TEAM_MEMBERS");
		Long staffId = null;
		try {
			staffId = namedParameterJdbcTemplate.queryForObject(sql, createParams(), Long.class);
		} catch (Exception e) {
			logger.error("selectTeamMembers" + e);
		}
		return staffId;
	}
}
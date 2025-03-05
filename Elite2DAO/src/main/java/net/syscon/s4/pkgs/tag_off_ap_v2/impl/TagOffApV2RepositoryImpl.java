package net.syscon.s4.pkgs.tag_off_ap_v2.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import net.syscon.s4.common.beans.OffenderActionPlans;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.pkgs.tag_off_ap_v2.TagOffApV2Repository;

@Repository
public class TagOffApV2RepositoryImpl extends RepositoryBase implements TagOffApV2Repository {
	Logger logger = LogManager.getLogger(TagOffApV2RepositoryImpl.class);

	@Override
	public Integer prIns(final OffenderActionPlans targetObj) {
		final String sql = getQuery("TAG_OFF_AP_V2_OFFENDER_ACTION_PLANS_PRINS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		parameters.add(new BeanPropertySqlParameterSource(targetObj));
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (final Exception e) {
			logger.error("prIns", e);
		}
		if (1 == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public Integer prDel(final OffenderActionPlans lrOldRec) {
		final String sql = getQuery("TAG_OFF_AP_V2_OFFENDER_ACTION_PLANS_PRDEL");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		parameters.add(new BeanPropertySqlParameterSource(lrOldRec));
		try {
			String tableName = "offender_action_plans";
			String whereCondition = "off_action_plan_id = :offActionPlanId";
			batchUpdatePreDeletedRows(tableName, whereCondition, parameters);
		} catch (Exception e) {
			logger.error(e);
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (final Exception e) {
			logger.error("prDel", e);
		}
		if (1 == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public Integer prUpd(final OffenderActionPlans targetObj) {
		final String sql = getQuery("TAG_OFF_AP_V2_OFFENDER_ACTION_PLANS_PRUPD");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		parameters.add(new BeanPropertySqlParameterSource(targetObj));
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (final Exception e) {
			logger.error("prUpd", e);
		}
		if (1 == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

}

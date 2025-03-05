package net.syscon.s4.pkgs.tag_ass_off_needs.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.pkgs.tag_ass_off_needs.TagAssOffNeedsRepository;
import net.syscon.s4.triggers.AssessedOffenderNeeds;

@Repository
public class TagAssOffNeedsRepositoryImpl extends RepositoryBase implements TagAssOffNeedsRepository {
	Logger logger = LogManager.getLogger(TagAssOffNeedsRepositoryImpl.class);

	@Override
	public Integer prDel(final AssessedOffenderNeeds prRec) {
		final String sql = getQuery("TAG_ASS_OFF_NEEDS_ASSESSED_OFFENDER_NEEDS_PRDEL");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		parameters.add(new BeanPropertySqlParameterSource(prRec));
		try {
			String tableName = "assessed_offender_needs";
			String whereCondition = "off_ass_need_id  = :offAssNeedId";
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
	public Integer prIns(final AssessedOffenderNeeds prNewRec) {
		final String sql = getQuery("TAG_ASS_OFF_NEEDS_ASSESSED_OFFENDER_NEEDS_PRINS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		parameters.add(new BeanPropertySqlParameterSource(prNewRec));
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
	public Integer prUpd(final AssessedOffenderNeeds prRec) {
		final String sql = getQuery("TAG_ASS_OFF_NEEDS_ASSESSED_OFFENDER_NEEDS_PRUPD");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		parameters.add(new BeanPropertySqlParameterSource(prRec));
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

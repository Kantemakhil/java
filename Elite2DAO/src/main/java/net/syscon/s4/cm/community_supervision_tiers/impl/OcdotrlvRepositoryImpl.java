package net.syscon.s4.cm.community_supervision_tiers.impl;

import java.util.ArrayList;
import java.util.Collections;
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
import net.syscon.s4.cm.community_supervision_tiers.OcdotrlvRepository;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.community_supervision_tiers.MaintainTierDefaultEvents;
import net.syscon.s4.community_supervision_tiers.OffenderTierLevel;
import net.syscon.s4.common.impl.RepositoryBase;

@Repository
public class OcdotrlvRepositoryImpl extends RepositoryBase implements OcdotrlvRepository {

	private static Logger logger = LogManager.getLogger(OcdotrlvRepositoryImpl.class.getName());

	private final Map<String, FieldMapper> mapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("TIER_LEVEL_CODE", new FieldMapper("tierLevel"))
			.put("ASSIGNMENT_DATE", new FieldMapper("dateAssigned"))
			.put("NEXT_REVIEW_DATE", new FieldMapper("nextReviewDate"))
			.put("CREATE_DATETIME", new FieldMapper("createDateTime"))
			.put("DESCRIPTION", new FieldMapper("description"))
			.put("ACTIVE_FLAG", new FieldMapper("activeFlag"))
			.put("CODE", new FieldMapper("code")).build();

	@Override
	public List<OffenderTierLevel> offendertierLevelExecuteQuery(OffenderTierLevel bean) {
		final String sql = getQuery("OCDOTRLV_FIND_OFFENDER_TIER_LEVEL");
		final RowMapper<OffenderTierLevel> VOffassAssRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderTierLevel.class, mapping);
		try {
			return namedParameterJdbcTemplate.query(sql,
					createParams("offenderBookId", bean.getOffenderBookId()),
					VOffassAssRowMapper);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " in error ");
			return Collections.emptyList();
		}
	}

	@Override
	public Integer insertOffenderTierLevel(List<OffenderTierLevel> offenderTierLevel) {
		String sql = getQuery("OCDOTRLV_INSERT_OFFENDER_TIER_LEVEL");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffenderTierLevel OffenderTierLevel : offenderTierLevel) {
			parameters.add(new BeanPropertySqlParameterSource(OffenderTierLevel));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error("Exception in" + this.getClass().getName() + " in insertOffenderTierLevel", e);
		}
		if (offenderTierLevel.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public Integer updateOffenderTierLevel(List<OffenderTierLevel> offenderTierLevel) {
		String sql = getQuery("OCDOTRLV_UPDATE_OFFENDER_TIER_LEVEL");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffenderTierLevel OffenderTierLevel : offenderTierLevel) {
			parameters.add(new BeanPropertySqlParameterSource(OffenderTierLevel));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error("Exception in" + this.getClass().getName() + "in updateOffenderTierLevel", e);
		}
		if (offenderTierLevel.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public Integer deleteOffenderTierLevel(List<OffenderTierLevel> offenderTierLevel) {
		String sql = getQuery("OCDOTRLV_DELETE_OFFENDER_TIER_LEVEL");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffenderTierLevel OffenderTierLevel : offenderTierLevel) {
			parameters.add(new BeanPropertySqlParameterSource(OffenderTierLevel));
			System.out.println(OffenderTierLevel.getRecordCreationDatetime());
		}
		try {
			String tableName = "offender_tier_levels";
			String whereClause = "OFFENDER_BOOK_ID = :offenderBookId and offender_tier_level_id = :offenderTierLevelId";
			batchUpdatePreDeletedRows(tableName, whereClause , parameters);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method deleteOffenderTierLevel", e);
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error("Exception in" + this.getClass().getName() + "in deleteOffenderTierLevel", e);
		}
		if (offenderTierLevel.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public List<OffenderTierLevel> offenderTierLevesRecordGroup(String caseLoadId) {
		final String sql = getQuery("OCDOTRLV_OFFENDER_TIER_LEVES_RECORD_GROUP");
		final RowMapper<OffenderTierLevel> VOffassAssRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderTierLevel.class, mapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), VOffassAssRowMapper);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " offenderTierLevesRecordGroup in error ");
			return Collections.emptyList();
		}
	}

	@Override
	public OffenderTierLevel getDefaultIntakeTier() {
		final String sql = getQuery("OCDOTRLV_DEFAULT_INTAKE_TIER_FLAG");
		OffenderTierLevel bean = new OffenderTierLevel();
		try {
			bean = namedParameterJdbcTemplate.queryForObject(sql, createParams(),
					new BeanPropertyRowMapper<OffenderTierLevel>(OffenderTierLevel.class));
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " getDefaultIntakeTier in error ");
			bean = null;
		}
		return bean;
	}

	@Override
	public Long getStaffId() {
		final String sql = getQuery("OCDOTRLV_GET_STAFF_ID");
		Long result = null;
		try {
			result = namedParameterJdbcTemplate.queryForObject(sql, createParams(), Long.class);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " getStaffId in error ");
		}
		return result;
	}
	
	
	@Override
	public Long getReviewDays(String code) {
		final String sql = getQuery("OCDOTRLV_GET_REVIEW_DAYS");
		Long result = null;
		try {
			result = namedParameterJdbcTemplate.queryForObject(sql, createParams("code",code), Long.class);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " getReviewDays in error ");
		}
		return result;
	}
	
	@Override
	public List<MaintainTierDefaultEvents> getMainTierDefaultEvents(String tierLevel) {
		final String sql = getQuery("OCDOTRLV_GET_MAINTIER_DEFAULT_EVENTS");
		final RowMapper<MaintainTierDefaultEvents> VOffassAssRowMapper = Row2BeanRowMapper.makeMapping(sql,
				MaintainTierDefaultEvents.class, mapping);
		try {
			return namedParameterJdbcTemplate.query(sql,
					createParams("tierLevelCode", tierLevel), VOffassAssRowMapper);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " in error ");
			return Collections.emptyList();
		}
	}
	
	
	@Override
	public Long getMaxTierLevel(Long offenderBokId) {
		final String sql = getQuery("OCDOTRLV_GET_MAX_OFFENDER_TIER_LEVEL_ID");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql, createParams("offenderBookId", offenderBokId),
					Long.class);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " in error ");
			return 0l;
		}
	}
}

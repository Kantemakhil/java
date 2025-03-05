package net.syscon.s4.cm.community_supervision_tiers.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.cm.community_supervision_tiers.OcmtirlvRepository;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.community_supervision_tiers.MaintainTierLevels;

@Repository
public class OcmtirlvRepositoryImpl extends RepositoryBase implements OcmtirlvRepository {

	private static Logger logger = LogManager.getLogger(OcmtirlvRepositoryImpl.class.getName());

	private final Map<String, FieldMapper> mapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("LIST_SEQ", new FieldMapper("listSequence"))
			.put("EXPIRY_DATE", new FieldMapper("expiryDate"))
			.put("EDITABLE_BTN", new FieldMapper("editableBtn"))
			.put("TIER_LEVEL_CODE", new FieldMapper("code")).build();

	@Override
	public List<MaintainTierLevels> tierLevelExecuteQuery(MaintainTierLevels bean) {
		final String sql = getQuery("OCMTIRLV_FIND_MAINTAIN_TIER_LEVELS");
		final RowMapper<MaintainTierLevels> VOffassAssRowMapper = Row2BeanRowMapper.makeMapping(sql,
				MaintainTierLevels.class, mapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), VOffassAssRowMapper);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " tierLevelExecuteQuery in error ");
			return Collections.emptyList();
		}
	}

	@Override
	public Integer insertMaintainCommunityTierLevels(List<MaintainTierLevels> maintainTierLevels) {
		String sql = getQuery("OCMTIRLV_INSERT_MAINTAIN_COMMUNITY_TIER_LEVELS");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final MaintainTierLevels MaintainTierLevels : maintainTierLevels) {
			parameters.add(new BeanPropertySqlParameterSource(MaintainTierLevels));
		}
		try {
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		}catch (Exception e) {
			logger.error(this.getClass().getName() + " insertMaintainCommunityTierLevels in error "+e);
		}
		if (maintainTierLevels.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public Integer updateMaintainCommunityTierLevels(List<MaintainTierLevels> maintainTierLevels) {
		String sql = getQuery("OCMTIRLV_UPDATE_MAINTAIN_COMMUNITY_TIER_LEVELS");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final MaintainTierLevels MaintainTierLevels : maintainTierLevels) {
			parameters.add(new BeanPropertySqlParameterSource(MaintainTierLevels));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " updateMaintainCommunityTierLevels in error "+e);
		}
		if (maintainTierLevels.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public Integer deleteMaintainCommunityTierLevels(List<MaintainTierLevels> maintainTierLevels) {
		String sql = getQuery("OCMTIRLV_DELETE_MAINTAIN_COMMUNITY_TIER_LEVELS");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final MaintainTierLevels MaintainTierLevels : maintainTierLevels) {
			parameters.add(new BeanPropertySqlParameterSource(MaintainTierLevels));
		}
		try {
			String tableName = "maintain_tier_levels";
			String whereClause = "tier_level_code =:code";
			batchUpdatePreDeletedRows(tableName, whereClause , parameters);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method deleteMaintainCommunityTierLevels", e);
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " deleteMaintainCommunityTierLevels in error "+e);
			if (e.getMessage().contains("maintain_tier_default_events_fk") ||  e.getMessage().contains("offender_tier_levels_fk2") ) { 
				return 3;
			}
		}
		if (maintainTierLevels.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}
	
	
	@Override
	public List<ReferenceCodes> rgIntakeTierRecordGroup() {
		final String sql = getQuery("OCMTIRLV_RECORD_GROUP_MAINTAIN_TIER_LEVELS");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error(this.getClass().getName() + " rgIntakeTierRecordGroup in error ");
			return Collections.emptyList();
		}
	}
}

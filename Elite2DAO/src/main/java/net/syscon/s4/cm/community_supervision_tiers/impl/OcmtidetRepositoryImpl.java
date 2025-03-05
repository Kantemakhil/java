package net.syscon.s4.cm.community_supervision_tiers.impl;

import java.math.BigDecimal;
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
import net.syscon.s4.cm.community_supervision_tiers.OcmtidetRepository;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.community_supervision_tiers.MaintainTierDefaultEvents;

@Repository
public class OcmtidetRepositoryImpl extends RepositoryBase implements OcmtidetRepository {

	private static Logger logger = LogManager.getLogger(OcmtirlvRepositoryImpl.class.getName());

	private final Map<String, FieldMapper> mapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DESCRIPTION", new FieldMapper("description")).put("TIER_LEVEL_CODE", new FieldMapper("code")).build();
	
	private final Map<String, FieldMapper> mappingOne = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DESCRIPTION", new FieldMapper("description")).put("CODE", new FieldMapper("code")).build();

	@Override
	public Integer insertTierdefaultEvents(List<MaintainTierDefaultEvents> maintainTierLevels) {
		String sql = getQuery("OCMTIDET_INSERT_TIER_DEFAULT_EVENTS");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final MaintainTierDefaultEvents MaintainTierLevels : maintainTierLevels) {
			parameters.add(new BeanPropertySqlParameterSource(MaintainTierLevels));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " insertTierdefaultEvents in error ");
		}
		if (maintainTierLevels.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public Integer updateTierdefaultEvents(List<MaintainTierDefaultEvents> maintainTierLevels) {
		String sql = getQuery("OCMTIDET_UPDATE_TIER_DEFAULT_EVENTS");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final MaintainTierDefaultEvents MaintainTierLevels : maintainTierLevels) {
			parameters.add(new BeanPropertySqlParameterSource(MaintainTierLevels));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " updateTierdefaultEvents in error ");
		}
		if (maintainTierLevels.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public Integer deleteTierdefaultEvents(List<MaintainTierDefaultEvents> maintainTierLevels) {
		String sql = getQuery("OCMTIDET_DELETE_TIER_DEFAULT_EVENTS");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final MaintainTierDefaultEvents MaintainTierLevels : maintainTierLevels) {
			parameters.add(new BeanPropertySqlParameterSource(MaintainTierLevels));
		}
		try {
			String tableName = "maintain_tier_default_events";
			String whereClause = "tier_event_sch_version_id =:tierEventSchVersionId";
			batchUpdatePreDeletedRows(tableName, whereClause , parameters);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method deleteTierdefaultEvents", e);
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " deleteTierdefaultEvents in error ");
			if(e.getMessage().contains("off_ind_sch_tier_df_events_fk")) {
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
	public List<MaintainTierDefaultEvents> tierdefaultEventsExecuteQuery(MaintainTierDefaultEvents bean) {
		final String sql = getQuery("OCMTIDET_EXECUTE_TIER_DEFAULT_EVENTS");
		final RowMapper<MaintainTierDefaultEvents> VOffassAssRowMapper = Row2BeanRowMapper.makeMapping(sql,
				MaintainTierDefaultEvents.class, mapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams("tierLevelCode", bean.getTierLevelcode()),
					VOffassAssRowMapper);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " tierLevelExecuteQuery in error ");
			return Collections.emptyList();
		}
	}

	@Override
	public ReferenceCodes getActiveTierEvent(Long offenderBookId) {
		final String sql = getQuery("OCMTIDET_RECORD_GROUP_DATA_ACTIVE");
		ReferenceCodes bean = new ReferenceCodes();
		try {
			bean = namedParameterJdbcTemplate.queryForObject(sql, createParams("offenderBookId", offenderBookId),
					new BeanPropertyRowMapper<ReferenceCodes>(ReferenceCodes.class));
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " getActiveTierEvent in error ");
		}
		return bean;
	}
	
	@Override
	public List<ReferenceCodes> rgScheduleTypeRecordGroup(String scheduleType) {
		final String sql = getQuery("OCMTIDET_RG_SCHEDULE_TYPE_RECORDGROUP");
		List<ReferenceCodes> list = new ArrayList<ReferenceCodes>();
		final RowMapper<ReferenceCodes> rowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				mappingOne);
		try {
			list = namedParameterJdbcTemplate.query(sql, createParams("parentCode",scheduleType), rowMapper);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " rgScheduleTypeRecordGroup in error ");
		}
		return list;
	}
	
	@Override
	public String getTierLevelCode(BigDecimal eventId) {
		final String sql = getQuery("OCMTIDET_GET_TIER_LEVEL_CODE");
		String tierCode = null;
		try {
			tierCode = namedParameterJdbcTemplate.queryForObject(sql, createParams("eventId", eventId), String.class);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " getTierLevelCode in error ");
		}
		return tierCode;
	}

	@Override
	public List<MaintainTierDefaultEvents> getActiveTierLevelData(Long offenderBookId, BigDecimal offenderTierId ,String eventType,String eventSubType,Long TierEventSchVersionId) {
		final String sql = getQuery("OCMTIDET_GET_TIER_LEVEL_CODE_GET_ACTIVE_TIER_LEVEL_DATA");
		try {
			final RowMapper<MaintainTierDefaultEvents> VOffassAssRowMapper = Row2BeanRowMapper.makeMapping(sql,
					MaintainTierDefaultEvents.class, mapping);
			return namedParameterJdbcTemplate.query(sql,
					createParams("offenderBookId", offenderBookId, "offenderTierLevelId", offenderTierId,"eventType",eventType,"eventSubType",eventSubType,"TierEventSchVersionId",TierEventSchVersionId),
					VOffassAssRowMapper);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " getActiveTierLevelData in error ");
		}
		return null;
	}
	
	@Override
	public MaintainTierDefaultEvents getDeafultTierEventData(String eventType,String eventSubType,BigDecimal offenderTierLevelId,Long offenderBookId,Long version) {
		final String sql = getQuery("OCMTIDET_GET_DEAFULT_TIER_EVENT_DATA");
		MaintainTierDefaultEvents bean = new MaintainTierDefaultEvents();
		try {
			bean = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("eventType", eventType, "eventSubType", eventSubType, "offenderTierLevelId", offenderTierLevelId,"offenderBookId",offenderBookId,"versioNo",version),
					new BeanPropertyRowMapper<MaintainTierDefaultEvents>(MaintainTierDefaultEvents.class));
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " getDeafultTierEventData in error ");
		}
		return bean;
	}
	
	
	@Override
	public MaintainTierDefaultEvents getActiveTierLevelRecord(Long offenderBookId) {
		final String sql = getQuery("OCMTIDET_GET_ACTIVE_TIER_LEVEL_RECORD");
		MaintainTierDefaultEvents bean = new MaintainTierDefaultEvents();
		try {
			bean = namedParameterJdbcTemplate.queryForObject(sql, createParams("offenderBookId", offenderBookId),
					new BeanPropertyRowMapper<MaintainTierDefaultEvents>(MaintainTierDefaultEvents.class));
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " getActiveTierLevelRecord in error ");
		}
		return bean;
	}
	
	@Override
	public BigDecimal getOffenderTierLevelId(Long eventId) {
		final String sql = getQuery("OCMTIDET_GET_OFFENDER_TIERLEVEL_ID");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql, createParams("eventId",eventId), BigDecimal.class);
		}catch (Exception e) {
			logger.error(this.getClass().getName() + " getOffenderTierLevelId in error ");
		}
		return null;
	}
	
	
	@Override
	public Long getOffenderTierLevelVersionNo(Long eventId) {
		final String sql = getQuery("OCMTIDET_GET_OFFENDER_TIERLEVEL_VERSION_NO");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql, createParams("eventId",eventId), Long.class);
		}catch (Exception e) {
			logger.error(this.getClass().getName() + " getOffenderTierLevelVersionNo in error ");
		}
		return null;
	}
	
	
	@Override
	public MaintainTierDefaultEvents getOffenderTierlevelVersionNoBasedDetails(String eventType, String eventSubType,
			String tierLevelCode, Long version) {
		final String sql = getQuery("OCMTIDET_GET_OFFENDER_TIERLEVEL_VERSION_NO_BASE_DETAILS");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql, createParams("scheduleType", eventType, "scheduleSubType", eventSubType,"tierLevelCode",tierLevelCode,"versionNo",version), new BeanPropertyRowMapper<MaintainTierDefaultEvents>(MaintainTierDefaultEvents.class));
		}catch (Exception e) {
			logger.error(this.getClass().getName() + " getOffenderTierlevelVersionNoBasedDetails in error ");
		}
		return null;
	}
}

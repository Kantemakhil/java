package net.syscon.s4.inst.schedules.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
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
import net.syscon.s4.common.ScheduleSeries;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.community_supervision_tiers.MaintainTierDefaultEvents;
import net.syscon.s4.inst.schedules.RecurringScheduleRepository;
import net.syscon.s4.inst.schedules.bean.VOffenderAllSchedules;
import net.syscon.s4.of.payroll.maintenance.SystemEvents;

@Repository
public class RecurringScheduleRepositoryImpl extends RepositoryBase implements RecurringScheduleRepository {
	
	private static Logger logger = LogManager.getLogger(RecurringScheduleRepositoryImpl.class.getName());
	
	private final Map<String, FieldMapper> systemEventsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CREATE_DATETIME",                  new FieldMapper("createDatetime"))
			.put("CREATE_USER_ID",                   new FieldMapper("createUserId"))
			.put("DESCRIPTION",                      new FieldMapper("description"))
			.put("EVENT_DATE",                       new FieldMapper("eventDate"))
			.put("EVENT_END_DATE",                   new FieldMapper("eventEndDate"))
			.put("EVENT_SEQ",                        new FieldMapper("eventSeq"))
			.put("EVENT_TYPE",                       new FieldMapper("eventType"))
			.put("MODIFY_DATE",                      new FieldMapper("modifyDate"))
			.put("MODIFY_DATETIME",                  new FieldMapper("modifyDatetime"))
			.put("MODIFY_USER_ID",                   new FieldMapper("modifyUserId"))
			.put("SEAL_FLAG",                        new FieldMapper("sealFlag"))
			.put("SYSTEM_EVENT_ID",                  new FieldMapper("systemEventId"))
			.build();
	
	@Override
	public List<SystemEvents> getHolidaysList(Date fromDate) {
		String sql = getQuery("RECURR_GET_HOLIDAYS_LIST");
		try {
		final RowMapper<SystemEvents> SystemEventsRowMapper = Row2BeanRowMapper.makeMapping(sql, SystemEvents.class,
				systemEventsMapping);
		return namedParameterJdbcTemplate.query(sql, createParams("STARTDATE",fromDate),
				SystemEventsRowMapper);
		}catch(Exception e) {
			logger.error("Exception  in " + this.getClass().getName() + " getHolidaysList ", e);
			return Collections.emptyList();
		}
	}
	
	
	@Override
	public List<VOffenderAllSchedules> getScheduleConflicts(Integer offenderBookId,List<Date> eventDate,Integer seriesId) { 
		final String sql = getQuery("RECURR_SCHEDULE_CONFLICT");
		try {
			final RowMapper<VOffenderAllSchedules> rowMapper = Row2BeanRowMapper.makeMapping(sql, VOffenderAllSchedules.class,
					systemEventsMapping);
			return namedParameterJdbcTemplate.query(sql, createParams("OFFENDER_BOOK_ID",offenderBookId,"EVENT_DATE",eventDate,"SERIESID",seriesId), rowMapper);	
		
		} catch (Exception e) {
			logger.error("Exception  in " + this.getClass().getName() + " getScheduleConflicts ", e);
			return Collections.emptyList();
		}
	}
	
	@Override
	public Integer recurrRuleCommit(ScheduleSeries commitBean) {
		final String sql = getQuery("RECURR_SCHEDULE_INSERT");
		Integer returnVal = null;
		try {
			returnVal = namedParameterJdbcTemplate.update(sql, new BeanPropertySqlParameterSource(commitBean));
		} catch (Exception e) {
			returnVal = 0;
			logger.error("Exception  in " + this.getClass().getName() + " recurrRuleCommit ", e);
		}
		return returnVal;
	}
	
	@Override
	public Integer recurrPreInsert() {
		final String sql = getQuery("RECURR_SCHEDULE_PRE_INSERT");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql, createParams(), Integer.class);	
		
		} catch (Exception e) {
			logger.error("Exception  in " + this.getClass().getName() + " recurrPreInsert ", e);
			return null;
		}
	}
	
	@Override
	public ScheduleSeries getScheduleSeries(ScheduleSeries searchBean) {
		final String sql = getQuery("RECURR_GET_SCHEDULE_SERIES_RULE");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql, createParams("seriesId",searchBean.getSeriesId()), new BeanPropertyRowMapper<ScheduleSeries>(ScheduleSeries.class));	
		
		} catch (Exception e) {
			logger.error("Exception  in " + this.getClass().getName() + " getScheduleSeries ", e);
			return new ScheduleSeries();
		}
	}


	@Override
	public Integer schedulesInsert(List<VOffenderAllSchedules> insertList) {
		final String sql = getQuery("RECURR_SCH_INSERT_OFFENDER_IND_SCHEDULES");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final VOffenderAllSchedules offAllSch : insertList) {
			parameters.add(new BeanPropertySqlParameterSource(offAllSch));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error("Exception in" + this.getClass().getName() + "in schedulesInsert", e);
		}
		if (insertList.size() == returnArray.length) {
			return 1;
		}
		return 0;
	}


	@Override
	public Integer schedulesDelete(BigDecimal eventId,String modifyUserId) {
		final String sql = getQuery("RECURR_SCH_DELETE_OFFENDER_IND_SCHEDULES");
		Integer returnVal = null;
		try {
			returnVal = namedParameterJdbcTemplate.update(sql, createParams("eventId",eventId,"modifyUserId",modifyUserId));
		} catch (Exception e) {
			returnVal = 0;
			logger.error("Exception  in " + this.getClass().getName() + " schedulesDelete ", e);
		}
		return returnVal;
	}


	@Override
	public Integer schedulesUpdate(Integer seriesId,Date eventDate, String modifyUserId) {
		final String sql = getQuery("RECURR_SCH_UPDATE_OFFENDER_IND_SCHEDULES");
		Integer returnVal = null;
		try {
			returnVal = namedParameterJdbcTemplate.update(sql, createParams("seriesId",seriesId,"eventDate",eventDate,"modifyUserId",modifyUserId));
			if(returnVal > 0) {
				returnVal = 1;
			}
		} catch (Exception e) {
			returnVal = 0;
			logger.error("Exception  in " + this.getClass().getName() + " schedulesDelete ", e);
		}
		return returnVal;
	}
	

	@Override
	public Integer dTescheduleUpdate(List<MaintainTierDefaultEvents> list) {
		final String sql = getQuery("RECURR_SCH_UPDATE_OFFENDER_IND_SCHEDULES_DTE");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final MaintainTierDefaultEvents bean : list) {
			parameters.add(new BeanPropertySqlParameterSource(bean));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error("Exception in" + this.getClass().getName() + "in dTescheduleUpdate", e);
		}
		if (list.size() == returnArray.length) {
			return 1;
		}
		return 0;
	}
	
	
	@Override
	public Integer deleteDefaultTierEventsData(String tierLevel,String eventType,String eventSubType,Date startDate ,BigDecimal offenderTierLevelId,Long versionNo,String modifyUserId) {
		final String sql = getQuery("RECURR_SCH_DELETE_OFFENDER_IND_SCHEDULES_DTE");
		Integer returnVal = null;
		try {
			returnVal = namedParameterJdbcTemplate.update(sql, createParams("tierLevelCode", tierLevel,"eventType",eventType,"eventSubType" ,eventSubType,"startDate",startDate,"offenderTierLevelId",offenderTierLevelId,"versionNo",versionNo,"modifyUserId",modifyUserId));
		} catch (Exception e) {
			returnVal = 0;
			logger.error("Exception  in " + this.getClass().getName() + " deleteDefaultTierEventsData ", e);
		}
		return returnVal;
	}
	
	@Override
	public String getDefaultTierLevelUiRules(String eventType, String eventSubType, Long offenderBokId,BigDecimal offenderTierLevelId) {
		final String sql = getQuery("RECURR_SCH_GET_DEFAULT_TIER_LEVEL_UIRULES");
		String uiRules = null;
		try {
			uiRules = namedParameterJdbcTemplate.queryForObject(sql, createParams("eventType",eventType,"eventSubType" ,eventSubType,"offenderBookId",offenderBokId,"offenderTierLevelId",offenderTierLevelId), String.class);
		} catch (Exception e) {
			logger.error("Exception  in " + this.getClass().getName() + " getDefaultTierLevelUiRules ", e);
		}
		return uiRules;
	}
}

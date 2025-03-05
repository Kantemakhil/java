package net.syscon.s4.iwp.impl;

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
import net.syscon.s4.cm.casemanagement.maintenance.beans.EventMeasureOutcomes;
import net.syscon.s4.cm.casemanagement.maintenance.beans.EventMeasures;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.inst.schedules.impl.OidscmovRepositoryImpl;
import net.syscon.s4.iwp.OcmeventRepository;

/**
 * Class OcmeventRepositoryImpl
 * @version 1.0 
 */
@Repository
public class OcmeventRepositoryImpl extends RepositoryBase implements OcmeventRepository{
	
	private static Logger logger = LogManager.getLogger(OcmeventRepositoryImpl.class.getName());

	/**
	 * Creates new OcmeventRepositoryImpl class Object 
	 */
	public OcmeventRepositoryImpl() {
		//OcmeventRepositoryImpl
	}
	private final Map<String, FieldMapper> eventMeasureOutcomesMapping = new ImmutableMap.Builder<String, FieldMapper>()
	.put("EVENT_MEASURE_ID",           new FieldMapper("eventMeasureId"))
	.put("OUTCOME_CODE",               new FieldMapper("outcomeCode"))
	.put("SET_COUNTER_FLAG",           new FieldMapper("setCounterFlag"))
	.put("PROMPT_USER_FLAG",           new FieldMapper("promptUserFlag"))
	.put("LIST_SEQ",                   new FieldMapper("listSeq"))
	.put("ACTIVE_FLAG",                new FieldMapper("activeFlag"))
	.put("UPDATE_ALLOWED_FLAG",        new FieldMapper("updateAllowedFlag"))
	.put("EXPIRY_DATE",                new FieldMapper("expiryDate"))
	.put("CREATE_DATE",                new FieldMapper("createDate"))
	.put("CREATE_USER_ID",             new FieldMapper("createUserId"))
	.put("MODIFY_DATETIME",            new FieldMapper("modifyDatetime"))
	.put("MODIFY_USER_ID",             new FieldMapper("modifyUserId"))
	.put("CREATE_DATETIME",            new FieldMapper("createDatetime"))
	.put("UPDATE_ON_CONTACT_LOG",      new FieldMapper("updateOnContactLog"))
	.put("SEAL_FLAG",                  new FieldMapper("sealFlag"))
	.put("ROW_ID",                  new FieldMapper("rowId"))
	.build();
	private final Map<String, FieldMapper> mMapping = new ImmutableMap.Builder<String, FieldMapper>()
	.put("CODE", 						new FieldMapper("code"))
	.put("DESCRIPTION", 				new FieldMapper("description"))
	.build();
	private final Map<String, FieldMapper> eventMeasuresMapping = new ImmutableMap.Builder<String, FieldMapper>()
	.put("EVENT_MEASURE_ID",            new FieldMapper("eventMeasureId"))
	.put("EVENT_TYPE",                  new FieldMapper("eventType"))
	.put("EVENT_SUB_TYPE",              new FieldMapper("eventSubType"))
	.put("MEASURES_STANDARD_FLAG",      new FieldMapper("measuresStandardFlag"))
	.put("LIST_SEQ",                    new FieldMapper("listSeq"))
	.put("ACTIVE_FLAG",                 new FieldMapper("activeFlag"))
	.put("UPDATE_ALLOWED_FLAG",         new FieldMapper("updateAllowedFlag"))
	.put("EXPIRY_DATE",                 new FieldMapper("expiryDate"))
	.put("CREATE_DATE",                 new FieldMapper("createDate"))
	.put("CREATE_USER_ID",              new FieldMapper("createUserId"))
	.put("MODIFY_DATETIME",             new FieldMapper("modifyDatetime"))
	.put("MODIFY_USER_ID",              new FieldMapper("modifyUserId"))
	.put("CREATE_DATETIME",             new FieldMapper("createDatetime"))
	.put("SEAL_FLAG",                   new FieldMapper("sealFlag"))
	.put("ROW_ID",                   	new FieldMapper("rowId"))
	.put("SANCTIONS_FLAG",              new FieldMapper("sanctionsFlag"))
	
	.build();

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao EventMeasures
	 *
	 * @return List<EventMeasures>
	 *
	 * @throws SQLException
	 */
	public List<EventMeasures> scheduleExecuteQuery() {
		final String sql = getQuery("OCMEVENT_SCHEDULE_FIND_EVENT_MEASURES");
		final RowMapper<EventMeasures> EventMeasuresRowMapper = Row2BeanRowMapper.makeMapping(sql, EventMeasures.class,
				eventMeasuresMapping);
		final ArrayList<EventMeasures> returnList = (ArrayList<EventMeasures>) namedParameterJdbcTemplate.query(sql,
				createParams(), EventMeasuresRowMapper);
		return returnList;
	}

	/**
	 * This method is used to insert the records in the data base tables based on
	 *
	 * @param lstEventMeasures List<EventMeasures>
	 *
	 * @return List<Integer>
	 *
	 * @throws SQLException
	 */
	public Integer scheduleInsertEventMeasures(final List<EventMeasures> lstEventMeasures) {
		String sql = getQuery("OCMEVENT_SCHEDULE_INSERT_EVENT_MEASURES");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final EventMeasures eventMeasures : lstEventMeasures) {
			parameters.add(new BeanPropertySqlParameterSource(eventMeasures));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstEventMeasures.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstEventMeasures List<EventMeasures>
	 *
	 * @throws SQLException
	 */
	public Integer scheduleUpdateEventMeasures(final List<EventMeasures> lstEventMeasures) {
		String sql = getQuery("OCMEVENT_SCHEDULE_UPDATE_EVENT_MEASURES");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final EventMeasures eventMeasures : lstEventMeasures) {
			parameters.add(new BeanPropertySqlParameterSource(eventMeasures));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstEventMeasures.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to delete records from data base tables based on
	 *
	 * @param lstEventMeasures List<EventMeasures>
	 *
	 * @throws SQLException
	 */
	public Integer scheduleDeleteEventMeasures(final List<EventMeasures> lstEventMeasures) {
		String sql = getQuery("OCMEVENT_SCHEDULE_DELETE_EVENT_MEASURES");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final EventMeasures eventMeasures : lstEventMeasures) {
			parameters.add(new BeanPropertySqlParameterSource(eventMeasures));
		}
		try {
			String tableName = "EVENT_MEASURES";
			String whereCondition = "EVENT_MEASURE_ID = :eventMeasureId";
			batchUpdatePreDeletedRows(tableName, whereCondition, parameters);
		} catch (Exception e) {
			logger.error(e);
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstEventMeasures.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao EventMeasureOutcomes
	 *
	 * @return List<EventMeasureOutcomes>
	 *
	 * @throws SQLException
	 */
	public List<EventMeasureOutcomes> outcomeExecuteQuery(final EventMeasureOutcomes objSearchDao) {
		final String sql = getQuery("OCMEVENT_OUTCOME_FIND_EVENT_MEASURE_OUTCOMES");
		final RowMapper<EventMeasureOutcomes> EventMeasureOutcomesRowMapper = Row2BeanRowMapper.makeMapping(sql,
				EventMeasureOutcomes.class, eventMeasureOutcomesMapping);
		final ArrayList<EventMeasureOutcomes> returnList = (ArrayList<EventMeasureOutcomes>) namedParameterJdbcTemplate
				.query(sql, createParams("eventMeasureId", objSearchDao.getEventMeasureId()),
						EventMeasureOutcomesRowMapper);
		return returnList;
	}

	/**
	 * This method is used to insert the records in the data base tables based on
	 *
	 * @param lstEventMeasureOutcomes List<EventMeasureOutcomes>
	 *
	 * @return List<Integer>
	 *
	 * @throws SQLException
	 */
	public Integer outcomeInsertEventMeasureOutcomes(final List<EventMeasureOutcomes> lstEventMeasureOutcomes) {
		String sql = getQuery("OCMEVENT_OUTCOME_INSERT_EVENT_MEASURE_OUTCOMES");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final EventMeasureOutcomes eventMeasureOutcomes : lstEventMeasureOutcomes) {
			parameters.add(new BeanPropertySqlParameterSource(eventMeasureOutcomes));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstEventMeasureOutcomes.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstEventMeasureOutcomes List<EventMeasureOutcomes>
	 *
	 * @throws SQLException
	 */
	public Integer outcomeUpdateEventMeasureOutcomes(final List<EventMeasureOutcomes> lstEventMeasureOutcomes) {
		String sql = getQuery("OCMEVENT_OUTCOME_UPDATE_EVENT_MEASURE_OUTCOMES");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final EventMeasureOutcomes eventMeasureOutcomes : lstEventMeasureOutcomes) {
			parameters.add(new BeanPropertySqlParameterSource(eventMeasureOutcomes));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error("outcomeUpdateEventMeasureOutcomes :" + e);
		}
		if (lstEventMeasureOutcomes.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to delete records from data base tables based on
	 *
	 * @param lstEventMeasureOutcomes List<EventMeasureOutcomes>
	 *
	 * @throws SQLException
	 */
	public Integer outcomeDeleteEventMeasureOutcomes(final List<EventMeasureOutcomes> lstEventMeasureOutcomes) {
		String sql = getQuery("OCMEVENT_OUTCOME_DELETE_EVENT_MEASURE_OUTCOMES");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final EventMeasureOutcomes eventMeasureOutcomes : lstEventMeasureOutcomes) {
			eventMeasureOutcomes.setRowIdNo(Long.valueOf(eventMeasureOutcomes.getRowId()));
			parameters.add(new BeanPropertySqlParameterSource(eventMeasureOutcomes));
		}
		try {
			String tableName = "EVENT_MEASURE_OUTCOMES";
			String whereCondition = "EVENT_MEASURE_ID = :eventMeasureId AND ROW_ID = :rowIdNo";
			batchUpdatePreDeletedRows(tableName, whereCondition, parameters);
		} catch (Exception e) {
			logger.error(e);
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstEventMeasureOutcomes.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<ReferenceCodes> rgTypeRecordGroup() {
		final String sql = getQuery("OCMEVENT_FIND_RGTYPE");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<ReferenceCodes> rgSubTypeRecordGroup(final String eventType) {
		final String sql = getQuery("OCMEVENT_FIND_RGSUBTYPE");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams("eventType", eventType), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<ReferenceCodes> rgOutcomeCodeRecordGroup() {
		final String sql = getQuery("OCMEVENT_FIND_RGOUTCOMECODE");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	public List<ReferenceCodes> rgStaticRecordGroup() {
		final String sql = getQuery("OCMEVENT_STATIC_RECORD_GROUP");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * ocmeventPreInsert
	 *
	 * @param params
	 *
	 */
	public long ocmeventPreInsert() {
		final String sql = getQuery("OCMEVENT_OCMEVENT_PREINSERT");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams(), long.class);
	}

	@Override
	public EventMeasures getOldEventMeasure(Long eventMeasureId) {
		final String sql = getQuery("OCMEVENT_EVENT_MEASURES_OLD_RECORD");
		EventMeasures obj = new EventMeasures();
		try {
			obj = namedParameterJdbcTemplate.queryForObject(sql, createParams("eventMeasureId", eventMeasureId),
					EventMeasures.class);
		} catch (Exception e) {
			logger.error("getOldEventMeasure :", e);
		}
		return obj;
	}

	@Override
	public EventMeasureOutcomes getOldEventMeasureOutcomes(Long eventMeasureId) {
		final String sql = getQuery("OCMEVENT_EVENT_MEASURES_OUTCOMES_OLD_RECORD");
		EventMeasureOutcomes obj = new EventMeasureOutcomes();
		try {
			obj = namedParameterJdbcTemplate.queryForObject(sql, createParams("eventMeasureId", eventMeasureId),
					EventMeasureOutcomes.class);
		} catch (Exception e) {
			logger.error("getOldEventMeasure :", e);
		}
		return obj;
	}
	
}

package net.syscon.s4.sa.admin.impl;
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
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.of.payroll.maintenance.SystemEvents;
import net.syscon.s4.sa.admin.OymholidRepository;
import net.syscon.s4.sa.admin.beans.CaseloadGrpHolCompens;
import net.syscon.s4.sa.admin.beans.CaseloadWorkGroups;
/**
 * Class OymholidRepositoryImpl
 * @version 1.0 
 */
@Repository
public class OymholidRepositoryImpl extends RepositoryBase implements OymholidRepository{

	private static Logger logger = LogManager.getLogger(OymholidRepositoryImpl.class.getName());
	
	private final Map<String, FieldMapper> referenceCodesMapping = new ImmutableMap.Builder<String, FieldMapper>()
		.put("LIST_SEQ", 						new FieldMapper("listSeq"))
		.put("DESCRIPTION", 					new FieldMapper("description"))
		.put("CODE", 							new FieldMapper("code"))
		.put("COMPENSATION_CODE", 				new FieldMapper("compensationCode"))
		.build();
	private final Map<String, FieldMapper> caseloadGrpHolCompensMapping = new ImmutableMap.Builder<String, FieldMapper>()
		.put("CASELOAD_ID",                   new FieldMapper("caseloadId"))
		.put("COMPENSATION_CODE",             new FieldMapper("compensationCode"))
		.put("CREATE_DATETIME",               new FieldMapper("createDatetime"))
		.put("CREATE_USER_ID",                new FieldMapper("createUserId"))
		.put("HOLIDAY_EVENT_ID",              new FieldMapper("holidayEventId"))
		.put("MODIFY_DATETIME",               new FieldMapper("modifyDatetime"))
		.put("MODIFY_USER_ID",                new FieldMapper("modifyUserId"))
		.put("SEAL_FLAG",                     new FieldMapper("sealFlag"))
		.put("WORK_GROUP_ID",                 new FieldMapper("workGroupId"))
		.put("ROWID",                 		  new FieldMapper("rowId"))
		.build();
	private final Map<String, FieldMapper> mMapping = new ImmutableMap.Builder<String, FieldMapper>()
		.put("LIST_SEQ", 						new FieldMapper("listSeq"))
		.put("WORK_GROUP_ID", 					new FieldMapper("workGroupId"))
		.put("CODE", 							new FieldMapper("code"))
		.put("DESCRIPTION", 					new FieldMapper("description"))
		.put("CASELOAD_ID", 					new FieldMapper("caseloadId"))
		.build();
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

	/**
	 * Creates new OymholidRepositoryImpl class Object
	 */
	public OymholidRepositoryImpl() {
	}

	/**
	 * Fetch the records from database table
	 * 
	 * @param objSearchDao SystemEvents
	 * @return List<SystemEvents>
	 */
	public List<SystemEvents> sysEventExecuteQuery(final SystemEvents objSearchDao) {
		final String sql = getQuery("OYMHOLID_SYSEVENT_FIND_SYSTEM_EVENTS");
		final RowMapper<SystemEvents> SystemEventsRowMapper = Row2BeanRowMapper.makeMapping(sql, SystemEvents.class,
				systemEventsMapping);
		final List<SystemEvents> returnList = namedParameterJdbcTemplate.query(sql, createParams(),
				SystemEventsRowMapper);
		return returnList;
	}

	/**
	 * This method is used to insert the records in the data base tables based on
	 * 
	 * @param lstSystemEvents List<SystemEvents>
	 * @return List<Integer>
	 */
	public Integer insertSystemEvents(final List<SystemEvents> lstSystemEvents) {
		final String sql = getQuery("OYMHOLID_SYSEVENT_INSERT_SYSTEM_EVENTS");
		int[] returnArray = new int[] {};
		int eventSeq = getEventSeq();
		int sysEventId = preInsert();
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final SystemEvents systemEvents : lstSystemEvents) {
			systemEvents.setSystemEventId(sysEventId);
			systemEvents.setEventSeq(eventSeq);
			parameters.add(new BeanPropertySqlParameterSource(systemEvents));
			eventSeq++;
			sysEventId++;
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstSystemEvents.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to update the data base tables based on
	 * 
	 * @param lstSystemEvents List<SystemEvents>
	 */
	public Integer updateSystemEvents(final List<SystemEvents> lstSystemEvents) {
		final String sql = getQuery("OYMHOLID_SYSEVENT_UPDATE_SYSTEM_EVENTS");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final SystemEvents systemEvents : lstSystemEvents) {
			parameters.add(new BeanPropertySqlParameterSource(systemEvents));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstSystemEvents.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to delete records from data base tables based on
	 * 
	 * @param lstSystemEvents List<SystemEvents>
	 */
	public Integer deleteSystemEvents(final List<SystemEvents> lstSystemEvents) {
		final String sql = getQuery("OYMHOLID_SYSEVENT_DELETE_SYSTEM_EVENTS");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final SystemEvents systemEvents : lstSystemEvents) {
			parameters.add(new BeanPropertySqlParameterSource(systemEvents));
		}
		try {
			String tableName = "SYSTEM_EVENTS";
			String whereClause = "SYSTEM_EVENT_ID = :systemEventId";
			batchUpdatePreDeletedRows(tableName, whereClause , parameters);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method deleteSystemEvents", e);
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstSystemEvents.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * Fetch the records from database table
	 * 
	 * @param objSearchDao CaseloadGrpHolCompens
	 * @return List<CaseloadGrpHolCompens>
	 */
	public List<CaseloadGrpHolCompens> csldGhcExecuteQuery(final CaseloadGrpHolCompens objSearchDao) {
		final String sql = getQuery("OYMHOLID_CSLDGHC_FIND_CASELOAD_GRP_HOL_COMPENS");
		final RowMapper<CaseloadGrpHolCompens> CaseloadGrpHolCompensRowMapper = Row2BeanRowMapper.makeMapping(sql,
				CaseloadGrpHolCompens.class, caseloadGrpHolCompensMapping);
		final ArrayList<CaseloadGrpHolCompens> returnList = (ArrayList<CaseloadGrpHolCompens>) namedParameterJdbcTemplate
				.query(sql, createParams("caseloadId", objSearchDao.getCaseloadId(), "holidayEventId", objSearchDao.getHolidayEventId()),
						CaseloadGrpHolCompensRowMapper);
		return returnList;
	}   

	/**
	 * @param
	 */
	public Integer preInsert() {
		final String seqSql = getQuery("OYMHOLID_SYS_EVENT_PREINSERT");
		return namedParameterJdbcTemplate.queryForObject(seqSql, createParams(), Integer.class);
	}

	public Integer getEventSeq() {
		final String seqSql = getQuery("OYMHOLID_SYS_EVENT_EVENT_SEQ");
		return namedParameterJdbcTemplate.queryForObject(seqSql, createParams(), Integer.class);
	}

	/**
	 * This method is used to insert the records in the data base tables based on
	 * 
	 * @param lstCaseloadGrpHolCompens List<CaseloadGrpHolCompens>
	 * @return List<Integer>
	 */
	public Integer insertCaseloadGrpHolCompens(final List<CaseloadGrpHolCompens> lstCaseloadGrpHolCompens) {
		final String sql = getQuery("OYMHOLID_CSLDGHC_INSERT_CASELOAD_GRP_HOL_COMPENS");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final CaseloadGrpHolCompens caseloadGrpHolCompens : lstCaseloadGrpHolCompens) {
			parameters.add(new BeanPropertySqlParameterSource(caseloadGrpHolCompens));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstCaseloadGrpHolCompens.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to update the data base tables based on
	 * 
	 * @param lstCaseloadGrpHolCompens List<CaseloadGrpHolCompens>
	 */
	public Integer updateCaseloadGrpHolCompens(final List<CaseloadGrpHolCompens> lstCaseloadGrpHolCompens) {
		final String sql = getQuery("OYMHOLID_CSLDGHC_UPDATE_CASELOAD_GRP_HOL_COMPENS");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final CaseloadGrpHolCompens caseloadGrpHolCompens : lstCaseloadGrpHolCompens) {
			parameters.add(new BeanPropertySqlParameterSource(caseloadGrpHolCompens));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstCaseloadGrpHolCompens.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to delete records from data base tables based on
	 * 
	 * @param lstCaseloadGrpHolCompens List<CaseloadGrpHolCompens>
	 */
	public Integer deleteCaseloadGrpHolCompens(final List<CaseloadGrpHolCompens> lstCaseloadGrpHolCompens) {
		final String sql = getQuery("OYMHOLID_CSLDGHC_DELETE_CASELOAD_GRP_HOL_COMPENS");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final CaseloadGrpHolCompens caseloadGrpHolCompens : lstCaseloadGrpHolCompens) {
			parameters.add(new BeanPropertySqlParameterSource(caseloadGrpHolCompens));
		}
		try {
			String tableName = "CASELOAD_GRP_HOL_COMPENS";
			String whereClause = "HOLIDAY_EVENT_ID = :holidayEventId AND WORK_GROUP_ID = :workGroupId";
			batchUpdatePreDeletedRows(tableName, whereClause , parameters);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method deleteCaseloadGrpHolCompens", e);
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstCaseloadGrpHolCompens.size() == returnArray.length) {
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
	public List<ReferenceCodes> cgfkCsldGhcCompensationCodRecordGroup() {
		final String sql = getQuery("OYMHOLID_FIND_CGFKCSLDGHCCOMPENSATIONCOD");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				referenceCodesMapping);
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
	public List<CaseloadWorkGroups> cgfkCsldGhcWorkGroupIdRecordGroup(final String caseloadType) {
		final String sql = getQuery("OYMHOLID_FIND_CGFKCSLDGHCWORKGROUPID");
		final RowMapper<CaseloadWorkGroups> mRowMapper = Row2BeanRowMapper.makeMapping(sql, CaseloadWorkGroups.class,
				mMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams("caseloadId", caseloadType), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

}

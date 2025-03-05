package net.syscon.s4.cm.community_supervision_tiers.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.cm.community_supervision_tiers.OcmdspwdRepository;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.community_supervision_tiers.WlDefaultStaffUnits;
import net.syscon.s4.community_supervision_tiers.WlNonOffSpecificTasks;
import net.syscon.s4.im.beans.AgencyLocations;

@Repository
public class OcmdspwdRepositoryImpl extends RepositoryBase implements OcmdspwdRepository {

	private static Logger logger = LogManager.getLogger(OcmdspwdRepositoryImpl.class.getName());

	private final Map<String, FieldMapper> agyLocMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("AGY_LOC_ID", new FieldMapper("code"))
			.put("canDisplay", new FieldMapper("canDisplay")).build();
	
	private final Map<String, FieldMapper> nonOffMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("AGY_LOC_ID", new FieldMapper("agyLocId")).build();

	@Override
	public List<AgencyLocations> getAgyLocRecordGroup(String caseloadId) {
		final String sql = getQuery("OCMDSPWD_GET_AGY_RECORD_GROUP");
		List<AgencyLocations> list = new ArrayList<AgencyLocations>();
		final RowMapper<AgencyLocations> agyLocRowMapper = Row2BeanRowMapper.makeMapping(sql, AgencyLocations.class,
				agyLocMapping);
		list = namedParameterJdbcTemplate.query(sql, createParams("caseloadId",caseloadId),agyLocRowMapper);
		return list;
	}

	@Override
	public Integer insertMaintainStartingWLUnits(List<WlDefaultStaffUnits> startingUnitsInsertList) {
		String sql = getQuery("OCMDSPWD_INSERT_MAINTAIN_STARTING_WORKLOAD_UNITS");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final WlDefaultStaffUnits defStaWLUnits : startingUnitsInsertList) {
			parameters.add(new BeanPropertySqlParameterSource(defStaWLUnits));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " insertMaintainStartingWLUnits in error " + e);
		}
		if (startingUnitsInsertList.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public Integer updateMaintainStartingWLUnits(List<WlDefaultStaffUnits> startingUnitsUpdateList) {
		String sql = getQuery("OCMDSPWD_UPDATE_MAINTAIN_STARTING_WORKLOAD_UNITS");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final WlDefaultStaffUnits defStaWLUnits : startingUnitsUpdateList) {
			parameters.add(new BeanPropertySqlParameterSource(defStaWLUnits));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " updateMaintainStartingWLUnits in error " + e);
		}
		if (startingUnitsUpdateList.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public Integer deleteMaintainStartingWLUnits(List<WlDefaultStaffUnits> startingUnitsDeleteList) {
		String sql = getQuery("OCMDSPWD_DELETE_MAINTAIN_STARTING_WORKLOAD_UNITS");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final WlDefaultStaffUnits defStaWLUnits : startingUnitsDeleteList) {
			parameters.add(new BeanPropertySqlParameterSource(defStaWLUnits));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " deleteMaintainStartingWLUnits in error " + e);
			if (e.getMessage().contains("wl_non_off_specific_tasks_fk2")) { 
				return 3;
			}
		}
		if (startingUnitsDeleteList.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public Integer insertNonOffenderSpecificTasksforPosition(List<WlNonOffSpecificTasks> insertList) {
		String sql = getQuery("OCMDSPWD_INSERT_NON_OFFENDER_SPECIFIC_TASKS_POSITION");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final WlNonOffSpecificTasks nonOffSpeTask : insertList) {
			parameters.add(new BeanPropertySqlParameterSource(nonOffSpeTask));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " insertNonOffenderSpecificTasksforPosition in error " + e);
		}
		if (insertList.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public Integer updateNonOffenderSpecificTasksforPosition(List<WlNonOffSpecificTasks> updateList) {
		String sql = getQuery("OCMDSPWD_UPDATE_NON_OFFENDER_SPECIFIC_TASKS_POSITION");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final WlNonOffSpecificTasks nonOffSpeTask : updateList) {
			parameters.add(new BeanPropertySqlParameterSource(nonOffSpeTask));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " updateNonOffenderSpecificTasksforPosition in error " + e);
		}
		if (updateList.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public Integer deleteNonOffenderSpecificTasksforPosition(List<WlNonOffSpecificTasks> deleteList) {
		String sql = getQuery("OCMDSPWD_DELETE_NON_OFFENDER_SPECIFIC_TASKS_POSITION");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final WlNonOffSpecificTasks nonOffSpeTask : deleteList) {
			parameters.add(new BeanPropertySqlParameterSource(nonOffSpeTask));
		}
		try {
			String tableName = "wl_non_off_specific_tasks";
			String whereClause = "agy_loc_id = :agyLocId and workload_task_type = :workloadTaskType and staff_position = :staffPosition";
			batchUpdatePreDeletedRows(tableName, whereClause , parameters);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method deleteNonOffenderSpecificTasksforPosition", e);
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " deleteNonOffenderSpecificTasksforPosition in error " + e);
		}
		if (deleteList.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public List<WlDefaultStaffUnits> assStartingDefWLUnitsExecuteQuery() {
		final String sql = getQuery("OCMDSPWD_ASS_STARTING_DEF_WL_UNITS_EXECUTE_QUERY");
		List<WlDefaultStaffUnits> list = new ArrayList<WlDefaultStaffUnits>();
		final RowMapper<WlDefaultStaffUnits> agyLocRowMapper = Row2BeanRowMapper.makeMapping(sql,
				WlDefaultStaffUnits.class, agyLocMapping);
		list = namedParameterJdbcTemplate.query(sql, agyLocRowMapper);
		return list;
	}

	@Override
	public List<WlNonOffSpecificTasks> nonOffSpecTaskPosExecuteQuery(WlNonOffSpecificTasks obj) {
		final String sql = getQuery("OCMDSPWD_NON_OFF_SPEC_TASK_POS_EXECUTE_QUERY");
		List<WlNonOffSpecificTasks> list = new ArrayList<WlNonOffSpecificTasks>();
		final RowMapper<WlNonOffSpecificTasks> agyLocRowMapper = Row2BeanRowMapper.makeMapping(sql,
				WlNonOffSpecificTasks.class, nonOffMapping);
		list = namedParameterJdbcTemplate.query(sql,createParams("staffPosition",obj.getStaffPosition()),agyLocRowMapper);
		return list;
	}

	@Override
	public Integer assStartingDefUnitsPredelete(List<WlDefaultStaffUnits> startingUnitsDeleteList) {
		String sql = getQuery("OCMDSPWD_DELETE_MAINTAIN_STARTING_WORKLOAD_UNITS_PRE_DELETE");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final WlDefaultStaffUnits defStaWLUnits : startingUnitsDeleteList) {
			parameters.add(new BeanPropertySqlParameterSource(defStaWLUnits));
		}
		try {
			String tableName = "wl_default_staff_units";
			String whereClause = "staff_position = :staffPosition";
			batchUpdatePreDeletedRows(tableName, whereClause , parameters);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method assStartingDefUnitsPredelete", e);
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " assStartingDefUnitsPredelete in error " + e);
		}
		if (startingUnitsDeleteList.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}
}

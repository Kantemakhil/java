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
import net.syscon.s4.cm.community_supervision_tiers.OcdonostRepository;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.community_supervision_tiers.WlDefaultStaffUnits;
import net.syscon.s4.community_supervision_tiers.WlOfficerNonOffSpecificTasks;

@Repository
public class OcdonostRepositoryImpl extends RepositoryBase implements OcdonostRepository {

	private static Logger logger = LogManager.getLogger(OcdonostRepositoryImpl.class.getName());

	private final Map<String, FieldMapper> unitsMapping = new ImmutableMap.Builder<String, FieldMapper>().build();

	@Override
	public String getStaffName(Integer staffId) {
		final String sql = getQuery("OCDONOST_GET_STAFF_NAME");
		String staffName = null;
		try {
			staffName = namedParameterJdbcTemplate.queryForObject(sql, createParams("staffId", staffId), String.class);
		} catch (Exception e) {
			logger.error(" In getStaffName method", e);
		}
		return staffName;
	}

	@Override
	public List<WlDefaultStaffUnits> getNonOffenderSpecificTasks(String agyLocId, String offPosition) {
		final String sql = getQuery("OCDONOST_GET_NON_OFFENDER_SPECIFIC_TASK");
		List<WlDefaultStaffUnits> list = new ArrayList<WlDefaultStaffUnits>();
		try {
			final RowMapper<WlDefaultStaffUnits> rowMapper = Row2BeanRowMapper.makeMapping(sql,
					WlDefaultStaffUnits.class, unitsMapping);
			list = namedParameterJdbcTemplate.query(sql, createParams("agyLocId", agyLocId, "offPosition", offPosition),
					rowMapper);
		} catch (Exception e) {
			logger.error(" In getNonOffenderSpecificTasks method", e);
		}
		return list;
	}

	@Override
	public Integer getDefaultUnit(String staffPosition) {
		final String sql = getQuery("OCDONOST_GET_DEF_UNITS");
		Integer defUnit = null;
		try {
			defUnit = namedParameterJdbcTemplate.queryForObject(sql, createParams("staffPosition", staffPosition),
					Integer.class);
		} catch (Exception e) {
			logger.error(" In getDefaultUnit method", e);
		}
		return defUnit;
	}

	@Override
	public Integer insertOfficerNonOffSpeTask(List<WlOfficerNonOffSpecificTasks> insertList) {
		String sql = getQuery("OCDONOST_INSERT_OFFICER_NON_OFF_SPECIFIC_TASKS");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final WlOfficerNonOffSpecificTasks defStaWLUnits : insertList) {
			parameters.add(new BeanPropertySqlParameterSource(defStaWLUnits));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " insertOfficerNonOffSpeTask in error " + e);
		}
		if (insertList.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public Integer updateOfficerNonOffSpeTask(List<WlOfficerNonOffSpecificTasks> updateList) {
		String sql = getQuery("OCDONOST_UPDATE_OFFICER_NON_OFF_SPECIFIC_TASKS");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final WlOfficerNonOffSpecificTasks defStaWLUnits : updateList) {
			parameters.add(new BeanPropertySqlParameterSource(defStaWLUnits));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " updateOfficerNonOffSpeTask in error " + e);
		}
		if (updateList.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public Integer deleteOfficerNonOffSpeTask(List<WlOfficerNonOffSpecificTasks> deleteList) {
		String sql = getQuery("OCDONOST_DELETE_OFFICER_NON_OFF_SPECIFIC_TASKS");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final WlOfficerNonOffSpecificTasks defStaWLUnits : deleteList) {
			parameters.add(new BeanPropertySqlParameterSource(defStaWLUnits));
		}
		try {
			String tableName = "wl_officer_non_off_specific_tasks";
			String whereClause = "workload_task_type=:workloadTaskType and staff_loc_role_id =:staffLocRoleId";
			batchUpdatePreDeletedRows(tableName, whereClause , parameters);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method deleteOfficerNonOffSpeTask", e);
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " deleteOfficerNonOffSpeTask in error " + e);
		}
		if (deleteList.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public List<WlOfficerNonOffSpecificTasks> getNonOffSpecTaskforPos(WlOfficerNonOffSpecificTasks obj) {
		final String sql = getQuery("OCDONOST_GET_NON_OFFENDER_SPECIFIC_TASK");
		List<WlOfficerNonOffSpecificTasks> list = new ArrayList<WlOfficerNonOffSpecificTasks>();
		try {
			final RowMapper<WlOfficerNonOffSpecificTasks> rowMapper = Row2BeanRowMapper.makeMapping(sql,
					WlOfficerNonOffSpecificTasks.class, unitsMapping);
			list = namedParameterJdbcTemplate.query(sql,
					createParams("staffPosition", obj.getStaffPosition(), "agyLocId", obj.getAgyLocId()), rowMapper);
		} catch (Exception e) {
			logger.error(" In getNonOffSpecTaskforPos method", e);
		}
		return list;
	}

	@Override
	public List<WlOfficerNonOffSpecificTasks> getOfficeNonOffSpeTask(WlOfficerNonOffSpecificTasks obj) {
		final String sql = getQuery("OCDONOST_GET_OFFICER_NON_OFFENDER_SPE_TASK");
		List<WlOfficerNonOffSpecificTasks> list = new ArrayList<WlOfficerNonOffSpecificTasks>();
		try {
			final RowMapper<WlOfficerNonOffSpecificTasks> rowMapper = Row2BeanRowMapper.makeMapping(sql,
					WlOfficerNonOffSpecificTasks.class, unitsMapping);
			list = namedParameterJdbcTemplate.query(sql, createParams("staffLocRoleId", obj.getStaffLocRoleId()), rowMapper);
		} catch (Exception e) {
			logger.error(" In getOfficeNonOffSpeTask method", e);
		}
		return list;
	}

	@Override
	public Integer updateAvailableUnits(WlOfficerNonOffSpecificTasks obj) {
		String sql = getQuery("OCDONOST_UPDATE_AVAILABLE_UNITS");
		return namedParameterJdbcTemplate.update(sql,
				createParams("availableUnits", obj.getAvailableUnits(), "staffLocRoleId", obj.getStaffLocRoleId(),"modifyUserId",obj.getModifyUserId()));
	}

	@Override
	public Integer gettingUnitsUsedByEachOffenders(WlOfficerNonOffSpecificTasks bean) {
		final String sql = getQuery("OCDONOST_GETTING_UNITS_USED_BY_EACH_OFFENDERS");
		Integer workLoadValue = null;
		try {
			workLoadValue = namedParameterJdbcTemplate.queryForObject(sql, createParams("p_agy_loc_id", bean.getAgyLocId(),"p_staff_id",bean.getStaffId()),
					Integer.class);
		} catch (Exception e) {
			workLoadValue=0;
			logger.error(" In gettingUnitsUsedByEachOffenders method", e);
		}
		return workLoadValue==null?0:workLoadValue;
	}

}

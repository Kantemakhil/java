package net.syscon.s4.inst.schedules.maintenance.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapperResultSetExtractor;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.inst.schedules.bean.ScheduleMovementSetting;
import net.syscon.s4.inst.schedules.maintenance.OidsmsetRepository;

@Repository
public class OidsmsetRepositoryImpl extends RepositoryBase implements OidsmsetRepository {

	@Override
	public Integer tapScheduleSettingUpdate(List<ScheduleMovementSetting> updateList) {
		final String sql = getQuery("OIDSMSET_TEMP_ABSENCE_SETTING_UPDATE_QUERY");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final ScheduleMovementSetting object : updateList) {
			parameters.add(new BeanPropertySqlParameterSource(object));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (updateList.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public List<ScheduleMovementSetting> tapScheduleSettingExecuteQuery() {
		return findAll(getQuery("OIDSMSET_TEMP_ABSENCE_SETTING_DATA"), createParams(), ScheduleMovementSetting.class);
	}
	private <T> List<T> findAll(String query, MapSqlParameterSource map, Class<T> clazz) {
		return namedParameterJdbcTemplate.query(query, map,
				new RowMapperResultSetExtractor<T>(new BeanPropertyRowMapper<T>(clazz)));
	}

}

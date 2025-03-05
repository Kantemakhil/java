package net.syscon.s4.inst.schedules.maintenance.impl;

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
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.inst.schedules.maintenance.OimrelscRepository;
import net.syscon.s4.inst.schedules.maintenance.bean.ReleaseSchedulesSettingsBean;

@Repository
public class OimrelscRepositoryImpl extends RepositoryBase implements OimrelscRepository{
	private static Logger logger = LogManager.getLogger(OimrelscRepositoryImpl.class);
	private final Map<String, FieldMapper> uiBeanMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("COMP_ID",                  new FieldMapper("compId"))
			.put("COMP_TYPE",                new FieldMapper("compType"))
			.put("CREATED_BY",               new FieldMapper("createdBy"))
			.put("MODIFIED_BY",              new FieldMapper("modifiedBy"))
			.put("CREATE_DATE",              new FieldMapper("createDate"))
			.put("MODIFY_DATE",              new FieldMapper("modifyDate"))
			.put("REL_SCH_SETTING_VALUE",              new FieldMapper("relSchSettingValue"))
			.put("REL_SCH_SETTING_VALUE_BLOB",          new FieldMapper("relSchSettingValueBlob"))
			.put("REL_SCH_SETTING_TYPE",              new FieldMapper("relSchSettingType"))
			.build();
	
	@Override
	public List<ReleaseSchedulesSettingsBean> retrieveGridData(ReleaseSchedulesSettingsBean object) {
		String sql = getQuery("OIMRELSC_GET_RELAESE_SCHEDULE_SETTING_KEY_DATE");
		final RowMapper<ReleaseSchedulesSettingsBean> uiBeanMapper = Row2BeanRowMapper.makeMapping(sql, ReleaseSchedulesSettingsBean.class,
				uiBeanMapping);
		final ArrayList<ReleaseSchedulesSettingsBean> returnList = (ArrayList<ReleaseSchedulesSettingsBean>) namedParameterJdbcTemplate.query(sql,createParams(), uiBeanMapper);
		if(returnList.isEmpty()) {
			List<ReleaseSchedulesSettingsBean> returnListEx=new ArrayList<ReleaseSchedulesSettingsBean>();
			return returnListEx;
		}
		for (ReleaseSchedulesSettingsBean obj : returnList) {
			obj.setRelSchSettingValue(new String(obj.getRelSchSettingValueBlob()));
		}
		return returnList;
	}
	
	
	@Override
	public Integer getKeyDateCount() {
		final String sql = getQuery("OIMRELSC_GET_KEY_DATE_COUNT");
		Integer keyDateCount = 0;
		try {
			keyDateCount = namedParameterJdbcTemplate.queryForObject(sql, createParams(),
					Integer.class);
		} catch (Exception e) {
			logger.error("getKeyDateCount", e);
		}
		return keyDateCount;
	}


	@Override
	public Integer updateSubmitFormData(ReleaseSchedulesSettingsBean odynfrmSubmitDataBean) {
		final String sql = getQuery("OIMRELSC_UPDATE_RELEASE_SCHEDULE_SETTING_KEY_DATE");
		odynfrmSubmitDataBean.setRelSchSettingValueBlob(odynfrmSubmitDataBean.getRelSchSettingValue().getBytes());
		final SqlParameterSource parameters = new BeanPropertySqlParameterSource(odynfrmSubmitDataBean);
		return namedParameterJdbcTemplate.update(sql, parameters);
		
	}


	@Override
	public Integer submitFormData(ReleaseSchedulesSettingsBean odynfrmSubmitDataBean) {
		final String sql = getQuery("OIMRELSC_INSERT_RELEASE_SCHEDULE_SETTING_KEY_DATE");	
		odynfrmSubmitDataBean.setRelSchSettingValueBlob(odynfrmSubmitDataBean.getRelSchSettingValue().getBytes());
		final SqlParameterSource parameters = new BeanPropertySqlParameterSource(odynfrmSubmitDataBean);
		return namedParameterJdbcTemplate.update(sql, parameters);

	}


	@Override
	public ReleaseSchedulesSettingsBean retrieveAlertGridData(ReleaseSchedulesSettingsBean searchBean) {
		String sql = getQuery("OIMRELSC_GET_RELAESE_SCHEDULE_SETTING_ALERTS_DATA");
		final RowMapper<ReleaseSchedulesSettingsBean> uiBeanMapper = Row2BeanRowMapper.makeMapping(sql, ReleaseSchedulesSettingsBean.class,
				uiBeanMapping);
		final ArrayList<ReleaseSchedulesSettingsBean> returnList = (ArrayList<ReleaseSchedulesSettingsBean>) namedParameterJdbcTemplate.query(sql,createParams("relSchSettingType",searchBean.getRelSchSettingType()), uiBeanMapper);
		if(returnList.isEmpty()) {
			return new ReleaseSchedulesSettingsBean();
		}
		for (ReleaseSchedulesSettingsBean obj : returnList) {
			obj.setRelSchSettingValue(new String(obj.getRelSchSettingValueBlob()));
		}
		return returnList.get(0);
	}


	@Override
	public Integer getAlertsDateCount() {
		final String sql = getQuery("OIMRELSC_GET_ALERTS_DATA_COUNT");
		Integer keyDateCount = 0;
		try {
			keyDateCount = namedParameterJdbcTemplate.queryForObject(sql, createParams(),
					Integer.class);
		} catch (Exception e) {
			logger.error("getKeyDateCount", e);
		}
		return keyDateCount;
	}


	@Override
	public Integer updateSubmitAlertsFormData(ReleaseSchedulesSettingsBean finalalertsGridData) {
		final String sql = getQuery("OIMRELSC_UPDATE_RELEASE_SCHEDULE_SETTING_ALERTS_DATA");
		finalalertsGridData.setRelSchSettingValueBlob(finalalertsGridData.getRelSchSettingValue().getBytes());
		final SqlParameterSource parameters = new BeanPropertySqlParameterSource(finalalertsGridData);
		return namedParameterJdbcTemplate.update(sql, parameters);
	}
	
	@Override
	public Integer updateSubmitChargesFormData(ReleaseSchedulesSettingsBean finalalertsGridData) {
		final String sql = getQuery("OIMRELSC_UPDATE_RELEASE_SCHEDULE_SETTING_CHARGES_DATA");
		finalalertsGridData.setRelSchSettingValueBlob(finalalertsGridData.getRelSchSettingValue().getBytes());
		final SqlParameterSource parameters = new BeanPropertySqlParameterSource(finalalertsGridData);
		return namedParameterJdbcTemplate.update(sql, parameters);
	}
	@Override
	public Integer getChargeIndCount() {
		final String sql = getQuery("OIMRELSC_GET_CHARGE_INDICATOR_DATA_COUNT");
		Integer keyDateCount = 0;
		try {
			keyDateCount = namedParameterJdbcTemplate.queryForObject(sql, createParams(),
					Integer.class);
		} catch (Exception e) {
			logger.error("getKeyDateCount", e);
		}
		return keyDateCount;
	}
	
}

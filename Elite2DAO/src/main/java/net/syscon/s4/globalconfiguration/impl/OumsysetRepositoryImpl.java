package net.syscon.s4.globalconfiguration.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.RowMapperResultSetExtractor;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.Phones;
import net.syscon.s4.common.beans.SystemSettingsBean;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.globalconfiguration.OumsysetRepository;
import net.syscon.s4.im.beans.AgencyLocations;

/**
 * Class OumsysetRepositoryImpl
 */
@Repository
public class OumsysetRepositoryImpl extends RepositoryBase implements OumsysetRepository {

	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OumsysetRepositoryImpl.class.getName());
	
	public OumsysetRepositoryImpl() {
	}

	private final Map<String, FieldMapper> sysetMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("SETTING_TYPE",						new FieldMapper("settingType"))
			.put("SETTING_PROVIDER_CODE",               new FieldMapper("settingProviderCode"))
			.put("CREATE_DATETIME",                     new FieldMapper("createDatetime"))     
			.put("CREATE_USER_ID",                      new FieldMapper("createUserId"))       
			.put("MODIFY_DATETIME",                     new FieldMapper("modifyDatetime"))     
			.put("MODIFY_USER_ID",                      new FieldMapper("modifyUserId"))       
			.build();
			
	@Override
	public SystemSettingsBean getSysSettingData(SystemSettingsBean systemSettingsBean) {
		final String sql = getQuery("OUMSYSET_EXECUTE_QUERY");
		List<SystemSettingsBean> returnList = new ArrayList<SystemSettingsBean>();
		String preparedSql = null;
		final StringBuffer sqlQuery = new StringBuffer(sql);
		final MapSqlParameterSource inParameterSource = new MapSqlParameterSource();
		try {
			sqlQuery.append(" WHERE");
	
			if (systemSettingsBean != null && systemSettingsBean.getSettingProviderCode() != null && systemSettingsBean.getSettingProviderCode() != "") {
				sqlQuery.append(" SETTING_PROVIDER_CODE = :settingProviderCode " + " AND ");
				inParameterSource.addValue("settingProviderCode", systemSettingsBean.getSettingProviderCode());
			}
			
			if (systemSettingsBean != null && systemSettingsBean.getSettingType() != null && systemSettingsBean.getSettingType() != "") {
				sqlQuery.append(" SETTING_TYPE = :settingType ");
				inParameterSource.addValue("settingType", systemSettingsBean.getSettingType());
			}
			
			preparedSql = sqlQuery.toString().trim();
	
			if (preparedSql.endsWith("WHERE")) {
				preparedSql = preparedSql.trim().substring(0, preparedSql.length() - 5);
			}
			if (preparedSql.endsWith("AND")) {
				preparedSql = preparedSql.trim().substring(0, preparedSql.length() - 3);
			}
			
			final RowMapper<SystemSettingsBean> sysetRowMapper = Row2BeanRowMapper.makeMapping(sql, SystemSettingsBean.class, sysetMapping);
			returnList = namedParameterJdbcTemplate.query(preparedSql, inParameterSource, sysetRowMapper);
			if(returnList.isEmpty()) {
				return new SystemSettingsBean();
			}
			for (SystemSettingsBean obj : returnList) {
				obj.setSettingValue(new String(obj.getSettingValueBlob()));
				obj.setSettingValueBlob(null);
			}
			
		} catch(Exception e) {
			logger.error(this.getClass().getName() + " getSysSettingData and Exception is : {}", e.getMessage());
			e.printStackTrace();
			return new SystemSettingsBean();
		}
		return returnList.get(0);
	}
	
	@Override
	public Integer updateSysSettingData(SystemSettingsBean systemSettingsBean) {
		final String sql = getQuery("OUMSYSET_UPDATE_SYSSETTING_DATA");
		Integer result = 0;
		try {
			systemSettingsBean.setSettingValueBlob(systemSettingsBean.getSettingValue().getBytes());
			final SqlParameterSource parameters = new BeanPropertySqlParameterSource(systemSettingsBean);
			result = namedParameterJdbcTemplate.update(sql, parameters);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " updateSysSettingData and Exception is : {}", e.getMessage());
			e.printStackTrace();
		}
		return result;
	}
	
	@Override
	public Integer postUpdate(SystemSettingsBean systemSettingsBean) {
		final String sql = getQuery("OUMSYSET_POST_UPDATE");
		Integer result = 0;
		try {
			final SqlParameterSource parameters = new BeanPropertySqlParameterSource(systemSettingsBean);
			result = namedParameterJdbcTemplate.update(sql, parameters);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " postUpdate and Exception is : {}", e.getMessage());
			e.printStackTrace();
		}
		if(result > 0) {
			result = 1;
		}
		return result;
	}

	@Override
	public SystemSettingsBean getSelectedProvider(String settingType) {
		final String sql = getQuery("OUMSYSET_GET_SELECTED_PROVIDER");
		SystemSettingsBean returnObj = new SystemSettingsBean();
		try {
			final MapSqlParameterSource inParameterSource = new MapSqlParameterSource();
			inParameterSource.addValue("settingType", settingType);
			returnObj = namedParameterJdbcTemplate.queryForObject(sql, inParameterSource, new BeanPropertyRowMapper<SystemSettingsBean>(SystemSettingsBean.class));
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " getSelectedProvider and Exception is : {}", e.getMessage());
		}
		return returnObj;
	}
	
	public List<String> getPhoneFormates() {
		final String sql = getQuery("OUMSYSET_GET_PHONES_DATA");
		List<String> returnList = new ArrayList<>();
		try {
			returnList = namedParameterJdbcTemplate.queryForList(sql, createParams(),String.class);
		}catch (Exception e) {
			logger.error(this.getClass().getName() + " getPhoneFormates and Exception is : {}", e);
		}
		return returnList;
	}
	
}

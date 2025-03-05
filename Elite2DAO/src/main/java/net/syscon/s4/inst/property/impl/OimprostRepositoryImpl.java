package net.syscon.s4.inst.property.impl;

import java.util.Collections;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.inst.property.OimprostRepository;
import net.syscon.s4.inst.property.bean.PropertySettings;

@Repository
public class OimprostRepositoryImpl extends RepositoryBase implements OimprostRepository {

	private static Logger logger = LogManager.getLogger(OimprostRepositoryImpl.class.getName());

	@Override
	public List<PropertySettings> getPropertySettingData(PropertySettings propertySettings) {
		String sql = getQuery("OIMPROST_EXECUTE_QUERY");

		try {
			return namedParameterJdbcTemplate.query(sql, createParams("settingCode", propertySettings.getSettingCode()),
					new BeanPropertyRowMapper<PropertySettings>(PropertySettings.class));
		} catch (Exception e) {
			logger.error("Exception in OimprostRepositoryImpl class getPropertySettinData : ", e);
			return Collections.emptyList();
		}
	}

	@Override
	public Integer updatePropertySettingData(PropertySettings propertySettings) {
		final String sql = getQuery("OIMPROST_UPDATE_PROPERTY_SETTING_DATA");
		Integer result = 0;
		try {
			final SqlParameterSource parameters = new BeanPropertySqlParameterSource(propertySettings);
			result = namedParameterJdbcTemplate.update(sql, parameters);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " updatePropertySettingData and Exception is : {}",
					e.getMessage());
			e.printStackTrace();
		}
		return result;
	}

}

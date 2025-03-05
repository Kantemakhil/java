package net.syscon.s4.inst.legalscreens.maintenance.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapperResultSetExtractor;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.im.intake.beans.MovementReasons;
import net.syscon.s4.inst.legalscreens.maintenance.OcmlesetRepository;
import net.syscon.s4.inst.legalscreens.maintenance.bean.LegalSettings;
@Repository
public class OcmlesetRepositoryImpl extends RepositoryBase implements OcmlesetRepository {

	private static Logger logger = LogManager.getLogger(OcmlesetRepositoryImpl.class.getName());

	@Override
	public Integer updateLegalsData(List<LegalSettings> reasons) {
		final String sql = getQuery("OCMLESET_UPDATE_DATA");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (LegalSettings settings : reasons) {
			if(settings.getValue()==null) {
				settings.setValue("");
			}
			parameters.add(new BeanPropertySqlParameterSource(settings));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		}catch (Exception e) {
			getLogMessage("saveStautesData", e);
		}
		return returnArray.length>0?1:0;
	}
	
	@Override
	public List<LegalSettings> getLegalsData() {
		return findAll( getQuery("OCMLESET_FETCH_DATA"),createParams(),LegalSettings.class);
	}
	
	@Override
	public List<MovementReasons> fetchMovementTypes() {
		return findAll( getQuery("OCMLESET_FETCH_MOVEMENT_TYPES"),createParams(),MovementReasons.class);
	}
	
	@Override
	public List<ReferenceCodes> fetchRoles() {
		return findAll( getQuery("OCMLESET_FETCH_OMS_ROLES"),createParams(),ReferenceCodes.class);
	}
	
	private <T> List<T> findAll(String query,MapSqlParameterSource map, Class<T> clazz) {
		return namedParameterJdbcTemplate.query(query,map,new RowMapperResultSetExtractor<T>(new BeanPropertyRowMapper<T>(clazz)));
	}
	
	
	private void getLogMessage(String methodName,Exception e) {
		logger.error("Method in " + this.getClass().getName() +" "+ methodName , e);
	}

	@Override
	public String getLegalSettingValue(String code) {
		final String sql = getQuery("OCMLESET_GET_LEGAL_SETTING_VALUE");
		String count=null;
		try {
			count = namedParameterJdbcTemplate.queryForObject(sql, createParams("code",code), String.class);	
		} catch (Exception e) {
			logger.error("Exception in : getLegalSettingValue : {}", e);
		}
		return count;
	}
}

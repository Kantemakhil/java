package net.syscon.s4.pkgs.tag_establishment.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.OffenderExternalMovements;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.pkgs.tag_establishment.TagEstablishmentRepository;

@Repository
public class TagEstablishmentRepositoryImpl extends RepositoryBase implements TagEstablishmentRepository {

	final private static Logger logger = LogManager.getLogger(TagEstablishmentRepositoryImpl.class.getName());
	private final Map<String, FieldMapper> courtCasesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("ASSESSMENT_CODE", new FieldMapper("assessmentCode")).
			 put("CODE", new FieldMapper("code"))
			.put("DESCRIPTION", new FieldMapper("description"))
			.put("ASSESSMENT_ID", new FieldMapper("assessmentId"))
			.put("ASSESSMENT_TYPE", new FieldMapper("assessmentType")).build();
	
	@Override
	public AgencyLocations selectHousingCur(final String agyLocId) {
		final String sql = getQuery("SELECT_GET_HOUSING_LABELS");
		AgencyLocations bean = new AgencyLocations();
		try {
			final RowMapper<AgencyLocations> agencyLocationsRowMapper = Row2BeanRowMapper.makeMapping(sql, AgencyLocations.class,
					courtCasesMapping);
			bean = namedParameterJdbcTemplate.queryForObject(sql, createParams("p_agy_loc_id", agyLocId),agencyLocationsRowMapper
					);
		
		} catch (Exception e) {
			logger.error("selectHousingCur :" + e);
		}
		return bean;
	}

	@Override
	public String getAgyLocDesc(final String pAgyLocId) {
		final String sql = getQuery("GET_AGY_LOC_DESC");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("P_AGY_LOC_ID", pAgyLocId), String.class);
	}

	@Override
	public Integer getCountVsAgycur(final String pGlobalCaseloadId) {
		final String sql = getQuery("GET_COUNT_VS_AGYCUR");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("P_GLOBAL_CASELOAD_ID", pGlobalCaseloadId),
				Integer.class);
	}

	@Override
	public AgencyLocations getVsAgyLocCur(String pGlobalCaseloadId) {
		final String sql = getQuery("DEFAULT_AGENCY_VS_AGYLOCCUR");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("P_GLOBAL_CASELOAD_ID", pGlobalCaseloadId),
				new BeanPropertyRowMapper<AgencyLocations>(AgencyLocations.class));
	}

	@Override
	public String getActiveAgyLocDesc(final String caseloadId) {
		final String sql = getQuery("GET_ACTIVE_AGY_LOC_DESC");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("P_AGY_LOC_ID", caseloadId), String.class);
	}
	
	@Override
	public List<OffenderExternalMovements> lockRecordC(Integer internalLocationId,Integer pNoAdjustments) {
		final String sql = getQuery("LOCK_RECORD_C");
		RowMapper<OffenderExternalMovements> row=Row2BeanRowMapper.makeMapping(sql, OffenderExternalMovements.class, courtCasesMapping);
		return namedParameterJdbcTemplate.query(sql, createParams("p_internal_location_id", internalLocationId),row);
	}
	
	@Override
	public Integer updateAgencyLocations(Integer pNoAdjustments, Integer internalLocationId,String modifyUserId) {
		final String sql = getQuery("UPDATE_AGENCY_INTERNAL_LOCATIONS");
		int count = 0;
		Map<String,Object> map = new HashMap<>();
		map.put("p_no_adjustments", pNoAdjustments);
		map.put("p_internal_location_id", internalLocationId);
		map.put("modifyUserId", modifyUserId);

		try {
			count = namedParameterJdbcTemplate.update(sql,map);
		} catch (Exception e) {
			logger.error(this.getClass().getName()+" updateAgencyLocations :" + e);
			return count;
		}
		return count;
	}

}
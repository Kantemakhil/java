package net.syscon.s4.triggers.impl;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.triggers.CourseActivitiesT2Repository;

@Repository
public class CourseActivitiesT2RepositoryImpl extends RepositoryBase implements CourseActivitiesT2Repository {

	private static Logger logger = LogManager.getLogger(CourseActivitiesT2RepositoryImpl.class);

	private final Map<String, FieldMapper> corporatesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("AREA_CODE", new FieldMapper("areaCode"))
			.put("NOMS_REGION_CODE", new FieldMapper("nomsRegionCode"))
			.put("AGENCY_LOCATION_TYPE", new FieldMapper("agencyLocationType")).build();

	@Override
	public AgencyLocations getCAl(final String providerPartyCode) {
		final String sql = getQuery("C_AL");
		AgencyLocations obj = new AgencyLocations();
		final RowMapper<AgencyLocations> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, AgencyLocations.class,
				corporatesMapping);
		try {
			obj = namedParameterJdbcTemplate.queryForObject(sql, createParams("PROVIDER_PARTY_CODE", providerPartyCode),
					columnRowMapper);
		} catch (Exception e) {
			logger.error("getCAl :" + e);
		}
		return obj;
	}

	@Override
	public String getTeamCode(final Long providerPartyId) {
		final String sql = getQuery("C_T");
		String retVal = null;
		try {
			retVal = namedParameterJdbcTemplate.queryForObject(sql, createParams("PROVIDER_PARTY_ID", providerPartyId),
					String.class);
		} catch (Exception e) {
			logger.error("getTeamCode :" + e);
		}
		return retVal;
	}

	@Override
	public Integer insertCourseActivityAreas(final String lvAreaCode, final Long crsActyId,String createUserId) {
		final String sql = getQuery("INSERT_INTO_COURSE_ACTIVITY_AREAS");
		Integer retVal = 0;
		try {
			retVal = namedParameterJdbcTemplate.update(sql,
					createParams("CRS_ACTY_ID", crsActyId, "LV_AREA_CODE", lvAreaCode,"createUserId",createUserId));
		} catch (DataAccessException e) {
			logger.error("insertCourseActivityAreas :" + e);
		}
		return retVal;
	}

}

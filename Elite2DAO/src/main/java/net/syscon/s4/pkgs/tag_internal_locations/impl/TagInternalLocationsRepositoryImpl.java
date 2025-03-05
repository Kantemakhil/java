package net.syscon.s4.pkgs.tag_internal_locations.impl;

import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.AgencyInternalLocations;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.pkgs.tag_internal_locations.TagInternalLocationsRepository;

@Repository
public class TagInternalLocationsRepositoryImpl extends RepositoryBase implements TagInternalLocationsRepository {
	private static Logger logger = LogManager.getLogger(TagInternalLocationsRepositoryImpl.class.getName());

	private final Map<String, FieldMapper> mapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("ORDER_PROPOSAL_CONDITION_ID", new FieldMapper("orderProposalConditionId")).build();

	@Override
	public List<AgencyInternalLocations> queryOne(final AgencyInternalLocations bean) {
		List<AgencyInternalLocations> returnList = null;
		final String sql = getQuery("QUERY_INTERNAL_LOCATIONS_QUERY_ONE");
		final RowMapper<AgencyInternalLocations> tempOidcountRowMapper = Row2BeanRowMapper.makeMapping(sql,
				AgencyInternalLocations.class, mapping);
		try {
			returnList = namedParameterJdbcTemplate.query(sql,
					createParams("p_location_code", bean.getInternalLocationCode(), "p_description",
							bean.getDescription(), "p_agy_loc_id", bean.getAgyLocId(), "p_show_living_units",
							bean.getTrackingFlag(), "p_level", bean.getpLevel()),
					tempOidcountRowMapper);
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return returnList;
	}

	@Override
	public List<AgencyInternalLocations> queryTwo(final String pAgyLocId, final String pShowLivingUnits) {
		List<AgencyInternalLocations> returnList = null;
		final String sql = getQuery("QUERY_INTERNAL_LOCATIONS_QUERY_TWO");
		final RowMapper<AgencyInternalLocations> tempOidcountRowMapper = Row2BeanRowMapper.makeMapping(sql,
				AgencyInternalLocations.class, mapping);
		try {
			returnList = namedParameterJdbcTemplate.query(sql,
					createParams("p_agy_loc_id", pAgyLocId, "p_show_living_units", pShowLivingUnits),
					tempOidcountRowMapper);
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return returnList;
	}

	@Override
	public List<AgencyInternalLocations> queryThree(final AgencyInternalLocations bean) {
		List<AgencyInternalLocations> returnList = null;
		final String sql = getQuery("QUERY_INTERNAL_LOCATIONS_QUERY_THREE");
		final RowMapper<AgencyInternalLocations> tempOidcountRowMapper = Row2BeanRowMapper.makeMapping(sql,
				AgencyInternalLocations.class, mapping);
		try {
			returnList = namedParameterJdbcTemplate.query(sql,
					createParams("p_internal_location_id", bean.getInternalLocationId(), "p_location_code",
							bean.getInternalLocationCode(), "p_description", bean.getDescription(), "p_agy_loc_id",
							bean.getAgyLocId(), "p_show_living_units", bean.getTrackingFlag()),
					tempOidcountRowMapper);
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return returnList;
	}
	@Override
	public String getDefaultLocationDesc(final String pInternalLocationCode, final Integer pParentInternalLocationId) {
		final String sql = getQuery("DESC_CUR");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("P_INTERNAL_LOCATION_CODE",
				pInternalLocationCode, "P_PARENT_INTERNAL_LOCATION_ID", pParentInternalLocationId), String.class);
	}

	@Override
	public Integer getInternalLocationId() {
		final String sql = getQuery("INTERNAL_LOCATION_ID");
		Integer locId = 0;
		try {
			locId = namedParameterJdbcTemplate.queryForObject(sql, createParams(), Integer.class);
		} catch (Exception e) {
			logger.error("getInternalLocationId :" + e);
		}
		return locId;
	}

	@Override
	public AgencyInternalLocations getInternalLocationRecord(final Integer pInternalLocationId) {
		final String sql = getQuery("GET_INTERNAL_LOCATION_RECORD_LOC_CUR");
		AgencyInternalLocations returnVal = null;
		try {
			returnVal = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("p_internal_location_id", pInternalLocationId),
					new BeanPropertyRowMapper<AgencyInternalLocations>(AgencyInternalLocations.class));
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return returnVal;
	}

	@Override
	public Integer agencyInternalLocations(final AgencyInternalLocations ag) {
		final String sql = getQuery("AGENCY_INTERNAL_LOCATIONS");
		return namedParameterJdbcTemplate.update(sql, createParams("p_active_flag", ag.getActiveFlag(),
				"p_parent_lu_id", ag.getInternalLocationId(), "modified_user_id", ag.getModifyUserId()));
	}

	@Override
	public Integer agencyInternalLocationsUpdate(AgencyInternalLocations ag) {
		final String sql = getQuery("AGENCY_INTERNAL_LOCATIONS_UPDATE");
		return namedParameterJdbcTemplate.update(sql,
				createParams("p_active_flag", ag.getActiveFlag(), "p_parent_lu_id", ag.getInternalLocationId(),
						"PARENT_INTERNAL_LOCATION_ID", ag.getParentInternalLocationId(), "modified_user_id",
						ag.getModifyUserId()));
	}

	@Override
	public Integer updateRelatedTrackingFlagsUpdate(AgencyInternalLocations ag) {
		final String sql = getQuery("UPDATE_RELATED_TRACKING_FLAGS_UPDATE");
		return namedParameterJdbcTemplate.update(sql, createParams("p_tracking_flag", ag.getTrackingFlag(),
				"p_internal_location_id", ag.getInternalLocationId(), "modified_user_id", ag.getModifyUserId()));
	}

	@Override
	public AgencyInternalLocations getOldRecordAgencyIntLoc(final Integer internalLocationId) {
		final String sql = getQuery("GET_OLD_RECORD_AGENCY_INT_LOC");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("INTERNAL_LOCATION_ID", internalLocationId),
				new BeanPropertyRowMapper<>(AgencyInternalLocations.class));
	}
}
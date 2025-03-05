package net.syscon.s4.inst.casemanagement.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.inst.casemanagement.OiiiscouRepository;
import net.syscon.s4.inst.casemanagement.beans.VPrisonStatusCount;
import net.syscon.s4.inst.casemanagement.beans.VPrisonTotal;

@Repository
public class OiiiscouRepositoryImpl extends RepositoryBase implements OiiiscouRepository {

	private static Logger logger = LogManager.getLogger(OiiiscouRepositoryImpl.class.getName());

	private final Map<String, FieldMapper> vPrisonTotalMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("MALE_COUNT", new FieldMapper("maleCount")).put("AGY_LOC_ID", new FieldMapper("agyLocId"))
			.put("FEMALE_COUNT", new FieldMapper("femaleCount")).put("TOTAL_COUNT", new FieldMapper("totalCount"))
			.build();
	private final Map<String, FieldMapper> referenceCodesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DESCRIPTION", new FieldMapper("description")).put("CODE", new FieldMapper("code")).build();
	private final Map<String, FieldMapper> vPrisonStatusCountMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("IMPRISONMENT_STATUS", new FieldMapper("imprisonmentStatus"))
			.put("MALE_COUNT", new FieldMapper("maleCount")).put("AGY_LOC_ID", new FieldMapper("agyLocId"))
			.put("FEMALE_COUNT", new FieldMapper("femaleCount")).put("OTHER", new FieldMapper("other"))
			.put("TOTAL_COUNT", new FieldMapper("totalCount")).put("PERCENT", new FieldMapper("percent")).build();
	private final Map<String, FieldMapper> mMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("AGY_LOC_ID", new FieldMapper("agyLocId")).put("DESCRIPTION", new FieldMapper("description"))
			.put("AGENCY_LOCATION_TYPE", new FieldMapper("agencyLocationType")).build();
	private final Map<String, FieldMapper> systemProfilesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("PROFILE_VALUE_2", new FieldMapper(" profileValue2 ")).build();
	private final Map<String, FieldMapper> agencyLocationsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("AGENCY_LOCATION_TYPE", new FieldMapper("agencyLocationType"))
			.put("AGY_LOC_ID", new FieldMapper("agyLocId")).put("DESCRIPTION", new FieldMapper("description")).build();

	/**
	 * Creates new OiiiscouRepositoryImpl class Object
	 */
	public OiiiscouRepositoryImpl() {

	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao VPrisonStatusCount
	 *
	 * @return List<VPrisonStatusCount>
	 *
	 * 
	 */
	public List<VPrisonStatusCount> vPrisnCtExecuteQuery(final String agyLocId) {
		final String sql = getQuery("OIIISCOU_VPRISNCT_FIND_V_PRISON_STATUS_COUNT");
		final RowMapper<VPrisonStatusCount> VPrisonStatusCountRowMapper = Row2BeanRowMapper.makeMapping(sql,
				VPrisonStatusCount.class, vPrisonStatusCountMapping);

		List<VPrisonStatusCount> returnList = new ArrayList<VPrisonStatusCount>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams("agyLocId", agyLocId),
					VPrisonStatusCountRowMapper);
		} catch (Exception e) {
			logger.error("OIIISCOU_VPRISNCT_FIND_V_PRISON_STATUS_COUNT", e);
		}
		return returnList;
	}

	/**
	 * @param
	 *
	 * 
	 *
	 */
	public int PRE_INSERT() {
		return 0;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao VPrisonTotal
	 *
	 * @return List<VPrisonTotal>
	 *
	 * 
	 */
	public List<VPrisonTotal> vPrisnTotExecuteQuery(final String agyLocId) {
		final String sql = getQuery("OIIISCOU_VPRISNTOT_FIND_V_PRISON_TOTAL");
		final RowMapper<VPrisonTotal> VPrisonTotalRowMapper = Row2BeanRowMapper.makeMapping(sql, VPrisonTotal.class,
				vPrisonTotalMapping);
		List<VPrisonTotal> returnList = new ArrayList<>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams("agyLocId", agyLocId),
					VPrisonTotalRowMapper);
		} catch (Exception e) {
			logger.error("OIIISCOU_VPRISNTOT_FIND_V_PRISON_TOTAL", e);
		}
		return returnList;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<AgencyLocations> cgfkAgyLocAgyLocIdRecordGroup(final String caseloadId) {
		final String sql = getQuery("OIIISCOU_FIND_CGFKAGYLOCAGYLOCID");
		final RowMapper<AgencyLocations> mRowMapper = Row2BeanRowMapper.makeMapping(sql, AgencyLocations.class,
				mMapping);
		List<AgencyLocations> resultList = new ArrayList<>();
		try {
			resultList = namedParameterJdbcTemplate.query(sql, createParams("caseloadId", caseloadId), mRowMapper);
		} catch (Exception e) {
			logger.error("OIIISCOU_FIND_CGFKAGYLOCAGYLOCID", e);
		}
		return resultList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfkchkAgyLocAgyLocIdAg
	 *
	 * @param params
	 *
	 */
	public AgencyLocations cgfkchkAgyLocAgyLocIdAg(AgencyLocations paramBean) {
		final String sql = getQuery("OIIISCOU_CGFKCHK_AGY_LOC_AGY_LOC_ID_AG");
		final RowMapper<AgencyLocations> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, AgencyLocations.class,
				agencyLocationsMapping);
		AgencyLocations returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfdgetVPrisnCtDrvImpris
	 *
	 * @param params
	 *
	 */
	public List<ReferenceCodes> cgfdgetVPrisnCtDrvImpris(ReferenceCodes paramBean) {
		final String sql = getQuery("OIIISCOU_CGFDGET_V_PRISN_CT_DRV_IMPRIS");
		final RowMapper<ReferenceCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				referenceCodesMapping);
		List<ReferenceCodes> returnObj = namedParameterJdbcTemplate.query(sql, createParams(), columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * getProfileValue
	 *
	 * @param params
	 *
	 */
	public SystemProfiles getProfileValue(SystemProfiles paramBean) {
		final String sql = getQuery("OIIISCOU_GET_PROFILE_VALUE_2");
		final RowMapper<SystemProfiles> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, SystemProfiles.class,
				systemProfilesMapping);
		SystemProfiles returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
		return returnObj;
	}

}

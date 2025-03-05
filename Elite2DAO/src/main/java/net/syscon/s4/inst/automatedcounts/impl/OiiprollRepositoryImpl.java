package net.syscon.s4.inst.automatedcounts.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.VLivingUnitSummaries;
import net.syscon.s4.inst.automatedcounts.OiiprollRepository;
import net.syscon.s4.inst.movements.beans.VIntLocSummaries;

/**
 * Class OiiprollRepositoryImpl
 */
@Repository
public class OiiprollRepositoryImpl extends RepositoryBase implements OiiprollRepository {

	private final Map<String, FieldMapper> vLivingUnitSummariesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("LEVEL_1_CODE",                    new FieldMapper("level1Code"))
			.put("VACANCY",                         new FieldMapper("vacancy"))
			.put("CAPACITY",                        new FieldMapper("capacity"))
			.put("ACTIVE_FLAG",                     new FieldMapper("activeFlag"))
			.put("RESERVED_BEDS",                   new FieldMapper("reservedBeds"))
			.put("USER_DESC", 						new FieldMapper("userDesc"))
			.put("LIST_SEQ", 						new FieldMapper("listSeq"))
			.put("IN_LIVING_UNITS", 				new FieldMapper("inLivingUnits"))
			.put("LIVING_UNIT_ID", 					new FieldMapper("livingUnitId"))
			.put("LIVING_UNIT_DESC", 				new FieldMapper("livingUnitDesc"))
			.put("FILLED_FLAG", 					new FieldMapper("filledFlag"))
			.put("LIVING_UNIT_TYPE", 				new FieldMapper("livingUnitType"))
			.put("LEVEL_4_CODE", 					new FieldMapper("level4Code"))
			.put("DEACTIVATE_DATE", 				new FieldMapper("deactivateDate"))
			.put("AGY_LOC_ID", 						new FieldMapper("agyLocId"))
			.put("ALLOCATED", 						new FieldMapper("allocated"))
			.put("OUT_OF_LIVING_UNITS", 			new FieldMapper("outOfLivingUnits"))
			.put("OUT_OF_AGY", 						new FieldMapper("outOfAgy"))
			.put("PARENT_LIVING_UNIT_ID", 			new FieldMapper("parentLivingUnitId"))
			.put("LEVEL_3_CODE", 					new FieldMapper("level3Code"))
			.put("LEVEL_2_CODE", 					new FieldMapper("level2Code"))
			.put("NEXTBUTTON", 						new FieldMapper("nextButton"))
			.put("LIVING_UNIT_TYPE_DESC", 			new FieldMapper("livingUnitTypeDesc"))
			.build();
	private final Map<String, FieldMapper> vIntLocSummariesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("TRACKING_FLAG", 					new FieldMapper("trackingFlag"))
			.put("INTERNAL_LOCATION_DESC", 			new FieldMapper("internalLocationDesc"))
			.put("CAPACITY", 						new FieldMapper("capacity"))
			.put("ACTIVE_FLAG", 					new FieldMapper("activeFlag"))
			.put("USER_DESC", 						new FieldMapper("userDesc"))
			.put("LIST_SEQ", 						new FieldMapper("listSeq"))
			.put("INTERNAL_LOCATION_TYPE_DESC", 	new FieldMapper("internalLocationTypeDesc"))
			.put("INTERNAL_LOCATION_TYPE", 			new FieldMapper("internalLocationType"))
			.put("IN_LOCATIONS", 					new FieldMapper("inLocations"))
			.put("DEACTIVATE_DATE", 				new FieldMapper("deactivateDate"))
			.put("AGY_LOC_ID", 						new FieldMapper("agyLocId"))
			.put("INTERNAL_LOCATION_ID", 			new FieldMapper("internalLocationId"))
			.put("INTERNAL_LOCATION_CODE", 			new FieldMapper("internalLocationCode"))
			.put("NEXTBUTTON", 						new FieldMapper("nextButton"))
			.put("PARENT_INTERNAL_LOCATION_ID", 	new FieldMapper("parentInternalLocationId"))
			.build();
	private final Map<String, FieldMapper> agencyLocationsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("AGY_LOC_I", 						new FieldMapper("agyLocI"))
			.put("CASELOAD_ID", 					new FieldMapper("caseloadId"))
			.put("DESCRIPTIO", 						new FieldMapper("descriptio")).build();

	/**
	 * Creates new OiiprollRepositoryImpl class Object
	 */
	public OiiprollRepositoryImpl() {
		
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            VLivingUnitSummaries
	 *
	 * @return List<VLivingUnitSummaries>
	 *
	 * @throws SQLException
	 */
	public List<VLivingUnitSummaries> lvUntSmExecuteQuery(final VLivingUnitSummaries objSearchDao, final String type) {
		final String sql = getQuery("OIIPROLL_LVUNTSM_FIND_V_LIVING_UNIT_SUMMARIES");
		String preparedSql = null;
		final StringBuffer sqlQuery = new StringBuffer();
		String preSqlQuery = null;
		final MapSqlParameterSource params = new MapSqlParameterSource();
		sqlQuery.append(sql);
		if (objSearchDao != null) {
			sqlQuery.append(" WHERE ");
			if (objSearchDao.getAgyLocId() != null) {
				sqlQuery.append(" AGY_LOC_ID  = :agylocId " + " AND ");
				params.addValue("agylocId", objSearchDao.getAgyLocId());
			}
			sqlQuery.append(" ACTIVE_FLAG  = 'Y' " + " AND ");
			sqlQuery.append(" DEACTIVATE_DATE IS NULL " + " AND ");
			if (type.equals("P")) {
				sqlQuery.append(" ((SELECT oiiproll_get_unit_parent(:parentLivingUnitId) FROM DUAL) IS NULL OR  "
						+ " (SELECT oiiproll_get_unit_parent(:parentLivingUnitId) FROM DUAL) = PARENT_LIVING_UNIT_ID) "
						+ " AND  ((SELECT oiiproll_get_unit_parent(:parentLivingUnitId) FROM DUAL) IS NOT NULL OR "
						+ " PARENT_LIVING_UNIT_ID IS NULL ) " + " AND ");
				params.addValue("parentLivingUnitId", objSearchDao.getParentLivingUnitId());
			} else {
				if (objSearchDao != null && objSearchDao.getParentLivingUnitId() != null) {
					sqlQuery.append(" PARENT_LIVING_UNIT_ID  = :parentLivingUnitId " + " AND ");
					params.addValue("parentLivingUnitId", objSearchDao.getParentLivingUnitId());
				} else {
					sqlQuery.append(" PARENT_LIVING_UNIT_ID  IS NULL " + " AND ");
				}
			}
		}
		preparedSql = sqlQuery.toString().trim();
		if (preparedSql.endsWith("WHERE")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 5);
		}
		if (preparedSql.endsWith("AND")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 3);
		}
		preSqlQuery = preparedSql.concat(" ORDER BY LIST_SEQ, LIVING_UNIT_DESC ");
		final RowMapper<VLivingUnitSummaries> VLivingUnitSummariesRowMapper = Row2BeanRowMapper.makeMapping(preparedSql,
				VLivingUnitSummaries.class, vLivingUnitSummariesMapping);
		List<VLivingUnitSummaries> returnList = new ArrayList<>();
		returnList = namedParameterJdbcTemplate.query(preSqlQuery, params, VLivingUnitSummariesRowMapper);
		return returnList;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            VLivingUnitSummaries
	 *
	 * @return List<VLivingUnitSummaries>
	 *
	 * @throws SQLException
	 */
	public VLivingUnitSummaries lvUntSmTotalCount(final VLivingUnitSummaries objSearchDao, final String type) {
		final String sql = getQuery("OIIPROLL_LVUNTSM_FIND_V_LIVING_UNIT_SUMMARIES_TOTAL_COUNT");
		String preparedSql = null;
		final StringBuffer sqlQuery = new StringBuffer();
		String preSqlQuery = null;
		final MapSqlParameterSource params = new MapSqlParameterSource();
		sqlQuery.append(sql);
		if (objSearchDao != null) {
			sqlQuery.append(" WHERE ");
			if (objSearchDao.getAgyLocId() != null) {
				sqlQuery.append(" AGY_LOC_ID  = :agylocId " + " AND ");
				params.addValue("agylocId", objSearchDao.getAgyLocId());
			}
			sqlQuery.append(" ACTIVE_FLAG  = 'Y' " + " AND ");
			sqlQuery.append(" DEACTIVATE_DATE IS NULL " + " AND ");
			if (type.equals("P")) {
				sqlQuery.append(" ((SELECT oiiproll_get_unit_parent(:livingUnitId) FROM DUAL) IS NULL OR  "
						+ " (SELECT oiiproll_get_unit_parent(:livingUnitId) FROM DUAL) = living_unit_id) "
						+ " AND  ((SELECT oiiproll_get_unit_parent(:livingUnitId) FROM DUAL) IS NOT NULL OR "
						+ " PARENT_LIVING_UNIT_ID IS NULL ) " + " AND ");
				params.addValue("livingUnitId", objSearchDao.getLivingUnitId().longValue());
			} else {
				if (objSearchDao != null && objSearchDao.getParentLivingUnitId() != null) {
					sqlQuery.append(" PARENT_LIVING_UNIT_ID  = :parentLivingUnitId " + " AND ");
					params.addValue("parentLivingUnitId", objSearchDao.getParentLivingUnitId());
				} else {
					sqlQuery.append(" PARENT_LIVING_UNIT_ID  IS NULL " + " AND ");
				}
			}
		}
		preparedSql = sqlQuery.toString().trim();
		if (preparedSql.endsWith("WHERE")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 5);
		}
		if (preparedSql.endsWith("AND")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 3);
		}
		preSqlQuery = preparedSql.concat(" ORDER BY LIST_SEQ");
		final RowMapper<VLivingUnitSummaries> VLivingUnitSummariesRowMapper = Row2BeanRowMapper.makeMapping(preparedSql,
				VLivingUnitSummaries.class, vLivingUnitSummariesMapping);
		VLivingUnitSummaries returnList = new VLivingUnitSummaries();
		returnList = namedParameterJdbcTemplate.queryForObject(preSqlQuery, params, VLivingUnitSummariesRowMapper);
		return returnList;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            VIntLocSummaries
	 *
	 * @return List<VIntLocSummaries>
	 *
	 * @throws SQLException
	 */
	public List<VIntLocSummaries> itLcSmExecuteQuery(final VIntLocSummaries objSearchDao, final String type) {
		final String sql = getQuery("OIIPROLL_ITLCSM_FIND_V_INT_LOC_SUMMARIES");
		String preparedSql = null;
		final StringBuffer sqlQuery = new StringBuffer();
		String preSqlQuery = null;
		final MapSqlParameterSource params = new MapSqlParameterSource();
		sqlQuery.append(sql);
		if (objSearchDao != null) {
			sqlQuery.append(" WHERE ");
			if (objSearchDao.getAgyLocId() != null) {
				sqlQuery.append(" AGY_LOC_ID  = :agylocId " + " AND ");
				params.addValue("agylocId", objSearchDao.getAgyLocId());
			}
			sqlQuery.append(" TRACKING_FLAG  = 'Y' " + " AND ");
			sqlQuery.append(" ACTIVE_FLAG  = 'Y' " + " AND ");
			sqlQuery.append(" DEACTIVATE_DATE IS NULL " + " AND ");
			if (type.equals("P")) {
				sqlQuery.append(
						" ((SELECT oiiproll_get_int_loc_parent(:parentInternalLocationId::int8) FROM DUAL) IS NULL OR  "
								+ " (SELECT oiiproll_get_int_loc_parent(:parentInternalLocationId::int8) FROM DUAL) = PARENT_INTERNAL_LOCATION_ID) "
								+ " AND  ((SELECT oiiproll_get_int_loc_parent(:parentInternalLocationId::int8) FROM DUAL) IS NOT NULL OR "
								+ " PARENT_INTERNAL_LOCATION_ID IS NULL ) " + " AND ");
				params.addValue("parentInternalLocationId", objSearchDao.getParentInternalLocationId());
			} else {
				if (objSearchDao != null && objSearchDao.getParentInternalLocationId() != null) {
					sqlQuery.append(" PARENT_INTERNAL_LOCATION_ID  = :parentInternalLocationId " + " AND ");
					params.addValue("parentInternalLocationId", objSearchDao.getParentInternalLocationId());
				} else {
					sqlQuery.append(" PARENT_INTERNAL_LOCATION_ID  IS NULL " + " AND ");
				}
			}
		}
		preparedSql = sqlQuery.toString().trim();
		if (preparedSql.endsWith("WHERE")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 5);
		}
		if (preparedSql.endsWith("AND")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 3);
		}
		preSqlQuery = preparedSql.concat(" ORDER BY LIST_SEQ, INTERNAL_LOCATION_DESC ");
		final RowMapper<VIntLocSummaries> VIntLocSummariesRowMapper = Row2BeanRowMapper.makeMapping(preSqlQuery,
				VIntLocSummaries.class, vIntLocSummariesMapping);
		final ArrayList<VIntLocSummaries> returnList = (ArrayList<VIntLocSummaries>) namedParameterJdbcTemplate
				.query(preSqlQuery, params, VIntLocSummariesRowMapper);
		return returnList;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            VIntLocSummaries
	 *
	 * @return List<VIntLocSummaries>
	 *
	 * @throws SQLException
	 */
	public VIntLocSummaries itLcSmTotalCount(final VIntLocSummaries objSearchDao, final String type) {
		final String sql = getQuery("OIIPROLL_ITLCSM_FIND_V_INT_LOC_SUMMARIES_TOTAL_COUNT");
		String preparedSql = null;
		final StringBuffer sqlQuery = new StringBuffer();
		final MapSqlParameterSource params = new MapSqlParameterSource();
		sqlQuery.append(sql);
		if (objSearchDao != null) {
			sqlQuery.append(" WHERE ");
			if (objSearchDao.getAgyLocId() != null) {
				sqlQuery.append(" AGY_LOC_ID  = :agylocId " + " AND ");
				params.addValue("agylocId", objSearchDao.getAgyLocId());
			}
			sqlQuery.append(" TRACKING_FLAG  = 'Y' " + " AND ");
			sqlQuery.append(" ACTIVE_FLAG  = 'Y' " + " AND ");
			sqlQuery.append(" DEACTIVATE_DATE IS NULL " + " AND ");
			if (type.equals("P")) {
				sqlQuery.append(
						" ((SELECT oiiproll_get_int_loc_parent(:parentInternalLocationId) FROM DUAL) IS NULL OR  "
								+ " (SELECT oiiproll_get_int_loc_parent(:parentInternalLocationId) FROM DUAL) = PARENT_INTERNAL_LOCATION_ID) "
								+ " AND  ((SELECT oiiproll_get_int_loc_parent(:parentInternalLocationId) FROM DUAL) IS NOT NULL OR "
								+ " PARENT_INTERNAL_LOCATION_ID IS NULL ) " + " AND ");
				params.addValue("parentInternalLocationId", objSearchDao.getParentInternalLocationId());
			} else {
				if (objSearchDao != null && objSearchDao.getParentInternalLocationId() != null) {
					sqlQuery.append(" PARENT_INTERNAL_LOCATION_ID  = :parentInternalLocationId " + " AND ");
					params.addValue("parentInternalLocationId", objSearchDao.getParentInternalLocationId());
				} else {
					sqlQuery.append(" PARENT_INTERNAL_LOCATION_ID  IS NULL " + " AND ");
				}
			}
		}
		preparedSql = sqlQuery.toString().trim();
		if (preparedSql.endsWith("WHERE")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 5);
		}
		if (preparedSql.endsWith("AND")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 3);
		}
		final RowMapper<VIntLocSummaries> VIntLocSummariesRowMapper = Row2BeanRowMapper.makeMapping(preparedSql,
				VIntLocSummaries.class, vIntLocSummariesMapping);
		VIntLocSummaries returnList = new VIntLocSummaries();
		returnList = namedParameterJdbcTemplate.queryForObject(preparedSql, params, VIntLocSummariesRowMapper);
		return returnList;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<AgencyLocations> rgAgyLocRecordGroup(final String caseloadId) {
		final String sql = getQuery("OIIPROLL_FIND_RGAGYLOC");
		final RowMapper<AgencyLocations> mRowMapper = Row2BeanRowMapper.makeMapping(sql, AgencyLocations.class,
				agencyLocationsMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams("caseloadId", caseloadId), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * oiiprollWhenNewFormInstance
	 *
	 * @param params
	 *
	 */
	public AgencyLocations oiiprollWhenNewFormInstance(final AgencyLocations paramBean) {
		final String sql = getQuery("OIIPROLL_OIIPROLL_WHENNEWFORMINSTANCE");
		final RowMapper<AgencyLocations> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, AgencyLocations.class,
				agencyLocationsMapping);
		AgencyLocations returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
		return returnObj;
	}

}

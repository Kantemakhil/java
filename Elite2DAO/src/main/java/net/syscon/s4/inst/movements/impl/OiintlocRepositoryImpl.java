package net.syscon.s4.inst.movements.impl;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.inst.movements.OiintlocRepository;
import net.syscon.s4.inst.movements.beans.VIntLocUsageLocations;
/**
 * Class OiintlocRepositoryImpl
 */
@Repository
public class OiintlocRepositoryImpl extends RepositoryBase implements OiintlocRepository{

/**
 * Creates new OiintlocRepositoryImpl class Object 
 */
public OiintlocRepositoryImpl() {
}
private final Map<String, FieldMapper> vIntLocUsageLocationsMapping = new ImmutableMap.Builder<String, FieldMapper>()
.put("USAGE_LOCATION_TYPE", 						new FieldMapper("usageLocationType"))
.put("CAPACITY", 						new FieldMapper("capacity"))
.put("LIST_SEQ", 						new FieldMapper("listSeq"))
.put("INTERNAL_LOCATION_USAGE", 						new FieldMapper("internalLocationUsage"))
.put("INT_LOC_DEACTIVATE_DATE", 						new FieldMapper("intLocDeactivateDate"))
.put("PARENT_USAGE_LOCATION_ID", 						new FieldMapper("parentUsageLocationId"))
.put("AGY_LOC_ID", 						new FieldMapper("agyLocId"))
.put("EVENT_SUB_TYPE", 						new FieldMapper("eventSubType"))
.put("LOWEST_LEVEL_FLAG", 						new FieldMapper("lowestLevelFlag"))
.put("DESCRIPTION", 						new FieldMapper("description"))
.put("USAGE_LOCATION_ID", 						new FieldMapper("usageLocationId"))
.build();
private final Map<String, FieldMapper> mMapping = new ImmutableMap.Builder<String, FieldMapper>()
.put("ACTIVE_FLAG", new FieldMapper("activeFlag"))
.build();

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            VIntLocUsageLocations
	 *
	 * @return List<VIntLocUsageLocations>
	 */
	public List<VIntLocUsageLocations> intLocExecuteQuery(final VIntLocUsageLocations objSearchDao) {
		final String sql = getQuery("OIINTLOC_INTLOC_FIND_V_INT_LOC_USAGE_LOCATIONS");
		final RowMapper<VIntLocUsageLocations> VIntLocUsageLocationsRowMapper = Row2BeanRowMapper.makeMapping(sql,
				VIntLocUsageLocations.class, vIntLocUsageLocationsMapping);
		final ArrayList<VIntLocUsageLocations> returnList = (ArrayList<VIntLocUsageLocations>) namedParameterJdbcTemplate
				.query(sql, createParams("usageCode", objSearchDao.getInternalLocationUsage(), "agyLocId",
						objSearchDao.getAgyLocId()), VIntLocUsageLocationsRowMapper);
		return returnList;
	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstVIntLocUsageLocations
	 *            List<VIntLocUsageLocations>
	 *
	 */
	public Integer intLocUpdateVIntLocUsageLocations(final List<VIntLocUsageLocations> lstVIntLocUsageLocations) {
		final int insertCount = 0;
		final String sql = getQuery("OIINTLOC_INTLOC_UPDATE_V_INT_LOC_USAGE_LOCATIONS");
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final VIntLocUsageLocations vIntLocUsageLocations : lstVIntLocUsageLocations) {
			parameters.add(new BeanPropertySqlParameterSource(vIntLocUsageLocations));
		}
		namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstVIntLocUsageLocations.size() == insertCount) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<ReferenceCodes> rgUsagesRecordGroup() {
		final String sql = getQuery("OIINTLOC_FIND_RGUSAGES");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

}

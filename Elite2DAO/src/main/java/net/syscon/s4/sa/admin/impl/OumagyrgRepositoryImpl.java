package net.syscon.s4.sa.admin.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.Areas;
import net.syscon.s4.sa.admin.OumagyrgRepository;

/**
 * Class OumagyrgRepositoryImpl
 * 
 */
@Repository
public class OumagyrgRepositoryImpl extends RepositoryBase implements OumagyrgRepository {

	public OumagyrgRepositoryImpl() {

		/**
		 * Creates new OumagyrgRepositoryImpl class Object
		 */
	}

	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OumagyrgRepositoryImpl.class);
	private final Map<String, FieldMapper> mMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CODE", new FieldMapper("code"))
			.put("AREA_CODE", new FieldMapper("areaCode"))
			.put("LIST_SEQ", new FieldMapper("listSeq"))
			.put("DESCRIPTION", new FieldMapper("description"))
			.build();

	private final Map<String, FieldMapper> agencyLocationsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("AGY_LOC_ID", new FieldMapper("agyLocId"))
			.put("TEMP_NOMS_REGION_CODE", new FieldMapper("tempNomsRegionCode"))
			.put("TEMP_AREA_CODE", new FieldMapper("tempAreaCode"))
			.put("TEMP_SUB_AREA_CODE", new FieldMapper("tempSubAreaCode"))
			.put("TEMP_GEOGRAPHIC_REGION_CODE", new FieldMapper("tempGeographicRegionCode"))
			.build();

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            AgencyLocations
	 *
	 * @return List<AgencyLocations>
	 *
	 * 
	 */
	public List<AgencyLocations> agyLocExecuteQuery(final AgencyLocations objSearchDao) {
		final String sql = getQuery("OUMAGYRG_AGYLOC_FIND_AGENCY_LOCATIONS");
		final RowMapper<AgencyLocations> agencyLocationsRowMapper = Row2BeanRowMapper.makeMapping(sql,
				AgencyLocations.class, agencyLocationsMapping);
		List<AgencyLocations> returnList = new ArrayList<>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams(), agencyLocationsRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("agyLocExecuteQuery: ", e);
		}

		return returnList;
	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstAgencyLocations
	 *            List<AgencyLocations>
	 *
	 */
	public Integer agyLocUpdateAgencyLocations(final List<AgencyLocations> lstAgencyLocations) {
		String sql = getQuery("OUMAGYRG_AGYLOC_UPDATE_AGENCY_LOCATIONS");
		int[] returnArray;
		List<SqlParameterSource> parameters = new ArrayList<>();
		for (AgencyLocations agencyLocations : lstAgencyLocations) {
			parameters.add(new BeanPropertySqlParameterSource(agencyLocations));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstAgencyLocations.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<ReferenceCodes>
	 */
	public List<ReferenceCodes> agencyLocationTypeRgRecordGroup() {
		final String sql = getQuery("OUMAGYRG_FIND_AGENCYLOCATIONTYPERG");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);
		List<ReferenceCodes> returnList = new ArrayList<>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("agencyLocationTypeRgRecordGroup: ", e);
			return Collections.emptyList();
		}
		return returnList;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<ReferenceCodes>
	 */
	public List<ReferenceCodes> geographicRegionRgRecordGroup() {
		final String sql = getQuery("OUMAGYRG_FIND_GEOGRAPHICREGIONRG");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);
		List<ReferenceCodes> returnList = new ArrayList<>();

		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("geographicRegionRgRecordGroup: ", e);
			return Collections.emptyList();
		}
		return returnList;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<Areas>
	 */
	public List<Areas> subAreaRgRecordGroup(final String agencyLocationType, final String areaCode) {
		final String sql = getQuery("OUMAGYRG_FIND_SUBAREARG");
		final RowMapper<Areas> mRowMapper = Row2BeanRowMapper.makeMapping(sql, Areas.class, mMapping);
		List<Areas> returnList = new ArrayList<>();

		try {
			returnList = namedParameterJdbcTemplate.query(sql,
					createParams("AGENCYLOCATIONTYPE", agencyLocationType, "AREACODE", areaCode), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("subAreaRgRecordGroup: ", e);
			return Collections.emptyList();
		}
		return returnList;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<Areas>
	 */
	public List<Areas> subAreaRgRecordGroupTot() {
		final String sql = getQuery("OUMAGYRG_FIND_SUBAREARG_TOT");
		final RowMapper<Areas> mRowMapper = Row2BeanRowMapper.makeMapping(sql, Areas.class, mMapping);
		List returnList = new ArrayList<>();

		try {
			returnList = namedParameterJdbcTemplate.query(sql,
					createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("subAreaRgRecordGroup: ", e);
			return Collections.emptyList();
		}
		return returnList;
	}
	/**
	 * Used to capture results from select query
	 * 
	 * @return List<Areas>
	 */
	public List<Areas> areaRgRecordGroupTot() {
		final String sql = getQuery("OUMAGYRG_FIND_AREARG_TOT");
		final RowMapper<Areas> mRowMapper = Row2BeanRowMapper.makeMapping(sql, Areas.class, mMapping);
		List returnList = new ArrayList<>();

		try {
			returnList = namedParameterJdbcTemplate.query(sql,
					createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("subAreaRgRecordGroup: ", e);
			return Collections.emptyList();
		}
		return returnList;
	}
	/**
	 * Used to capture results from select query
	 * 
	 * @return List<Areas>
	 */
	public List<Areas> areaRgRecordGroup(final String agencyLocationType, final String nomsRegionCode) {
		final String sql = getQuery("OUMAGYRG_FIND_AREARG");
		final RowMapper<Areas> mRowMapper = Row2BeanRowMapper.makeMapping(sql, Areas.class, mMapping);
		List<Areas> returnList = new ArrayList<>();

		try {
			returnList = namedParameterJdbcTemplate.query(sql,
					createParams("AGENCYLOCATIONTYPE", agencyLocationType, "NOMSREGIONCODE", nomsRegionCode),
					mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("areaRgRecordGroup: ", e);
			return Collections.emptyList();
		}
		return returnList;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<ReferenceCodes>
	 */
	public List<ReferenceCodes> justiceAreaRgRecordGroup() {
		final String sql = getQuery("OUMAGYRG_FIND_JUSTICEAREARG");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);
		List<ReferenceCodes> returnList = new ArrayList<>();

		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("justiceAreaRgRecordGroup: ", e);
			return Collections.emptyList();
		}
		return returnList;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<Areas>
	 */
	public List<Areas> nomsRegionRgRecordGroup() {
		final String sql = getQuery("OUMAGYRG_FIND_NOMSREGIONRG");
		final RowMapper<Areas> mRowMapper = Row2BeanRowMapper.makeMapping(sql, Areas.class, mMapping);
		List<Areas> returnList = new ArrayList<>();

		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("nomsRegionRgRecordGroup: ", e);
			return Collections.emptyList();
		}
		return returnList;
	}

}

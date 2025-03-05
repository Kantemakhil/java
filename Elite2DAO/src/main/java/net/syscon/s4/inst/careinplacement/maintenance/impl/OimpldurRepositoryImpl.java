package net.syscon.s4.inst.careinplacement.maintenance.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.inst.careinplacement.beans.PlacementDurations;
import net.syscon.s4.inst.careinplacement.maintenance.OimpldurRepository;

/**
 * Class OimpldurRepositoryImpl
 */
@Repository
public class OimpldurRepositoryImpl extends RepositoryBase implements OimpldurRepository {

	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OimpldurRepositoryImpl.class);

	private final Map<String, FieldMapper> refCodesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DESCRIPTION", new FieldMapper("description")).put("PLACEMENT_TYPE", new FieldMapper("placementType"))
			.build();
	private final Map<String, FieldMapper> plDurMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CREATE_USER_ID", new FieldMapper("createUserId"))
			.put("DURATION_TYPE", new FieldMapper("durationType")).put("SEAL_FLAG", new FieldMapper("sealFlag"))
			.put("MAXIMUM_DURATION", new FieldMapper("maximumDuration"))
			.put("ACTIVE_FLAG", new FieldMapper("activeFlag")).put("CREATE_DATETIME", new FieldMapper("createDatetime"))
			.put("MODIFY_USER_ID", new FieldMapper("modifyUserId")).put("LIST_SEQ", new FieldMapper("listSeq"))
			.put("MODIFY_DATETIME", new FieldMapper("modifyDatetime"))
			.put("PLACEMENT_TYPE", new FieldMapper("placementType")).put("EXPIRY_DATE", new FieldMapper("expiryDate"))
			.put("MINIMUM_DURATION", new FieldMapper("minimumDuration")).build();
	private final Map<String, FieldMapper> mMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CODE", new FieldMapper("code ")).build();

	/**
	 * Creates new OimpldurRepositoryImpl class Object
	 */
	public OimpldurRepositoryImpl() {
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao PlacementDurations
	 *
	 * @return List<PlacementDurations>
	 */
	public List<PlacementDurations> placementDurExecuteQuery(final PlacementDurations objSearchDao) {
		final String sql = getQuery("OIMPLDUR_PLACEMENTDUR_FIND_PLACEMENT_DURATIONS");
		String preparedSql = null;
		final StringBuffer sqlQuery = new StringBuffer();
		String preSqlQuery = null;
		final MapSqlParameterSource params = new MapSqlParameterSource();
		sqlQuery.append(sql);
		if (objSearchDao != null) {
			sqlQuery.append(" WHERE ");
			if (objSearchDao.getPlacementType() != null && !"".equals(objSearchDao.getPlacementType())) {
				sqlQuery.append(" PLACEMENT_TYPE  = :placementType " + " AND ");
				params.addValue("placementType", objSearchDao.getPlacementType());
			}
			if (objSearchDao.getDurationType() != null && !"".equals(objSearchDao.getDurationType())) {
				sqlQuery.append(" DURATION_TYPE  = :durationType " + " AND ");
				params.addValue("durationType", objSearchDao.getDurationType());
			}
			if (objSearchDao.getMaximumDuration() != null) {
				sqlQuery.append(" MAXIMUM_DURATION  = :maximumDuration " + " AND ");
				params.addValue("maximumDuration", objSearchDao.getMaximumDuration());
			}
			if (objSearchDao.getMinimumDuration() != null) {
				sqlQuery.append(" MINIMUM_DURATION  = :minimumDuration " + " AND ");
				params.addValue("minimumDuration", objSearchDao.getMinimumDuration());
			}
			if (objSearchDao != null && objSearchDao.getListSeq() != null) {
				sqlQuery.append(" LIST_SEQ  = :listSeq " + " AND ");
				params.addValue("listSeq", objSearchDao.getListSeq());
			}
			if (objSearchDao.getActiveFlag() != null) {
				sqlQuery.append(" ACTIVE_FLAG  = :activeFlag " + " AND ");
				params.addValue("activeFlag", objSearchDao.getActiveFlag());
			}
			if (objSearchDao.getExpiryDate() != null) {
				sqlQuery.append(" EXPIRY_DATE  = :expiryDate " + " AND ");
				params.addValue("expiryDate", objSearchDao.getExpiryDate());
			}
		}

		preparedSql = sqlQuery.toString().trim();
		if (preparedSql.endsWith("WHERE")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 5);
		}
		if (preparedSql.endsWith("AND")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 3);
		}
		preSqlQuery = preparedSql.concat(" ORDER BY PLACEMENT_TYPE ");

		final RowMapper<PlacementDurations> plDurRowMapper = Row2BeanRowMapper.makeMapping(preSqlQuery,
				PlacementDurations.class, plDurMapping);
		ArrayList<PlacementDurations> returnList = new ArrayList<>();
		try {
			returnList = (ArrayList<PlacementDurations>) namedParameterJdbcTemplate.query(preSqlQuery, params,
					plDurRowMapper);
		} catch (Exception e) {
			logger.error("contactPersonTypesExecuteQuery", e);
		}
		return returnList;
	}

	/**
	 * This method is used to insert the records in the data base tables based on
	 *
	 * @param lstPlacementDurations List<PlacementDurations>
	 *
	 * @return List<Integer>
	 */
	public Integer placementDurInsertPlacementDurations(final List<PlacementDurations> lstPlacementDurations) {
		final String sql = getQuery("OIMPLDUR_PLACEMENTDUR_INSERT_PLACEMENT_DURATMODULE_NAMEIONS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final PlacementDurations placementDurations : lstPlacementDurations) {
			parameters.add(new BeanPropertySqlParameterSource(placementDurations));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstPlacementDurations.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstPlacementDurations List<PlacementDurations>
	 */
	public Integer placementDurUpdatePlacementDurations(final List<PlacementDurations> lstPlacementDurations) {
		final String sql = getQuery("OIMPLDUR_PLACEMENTDUR_UPDATE_PLACEMENT_DURATIONS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final PlacementDurations placementDurations : lstPlacementDurations) {
			placementDurations.setRowId1(Integer.parseInt(placementDurations.getRowId()));
			parameters.add(new BeanPropertySqlParameterSource(placementDurations));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstPlacementDurations.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

	/**
	 * This method is used to delete records from data base tables based on
	 *
	 * @param lstPlacementDurations List<PlacementDurations>
	 */
	public Integer placementDurDeletePlacementDurations(final List<PlacementDurations> lstPlacementDurations) {
		final String sql = getQuery("OIMPLDUR_PLACEMENTDUR_DELETE_PLACEMENT_DURATIONS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final PlacementDurations placementDurations : lstPlacementDurations) {
			placementDurations.setRowId1(Integer.parseInt(placementDurations.getRowId()));
			parameters.add(new BeanPropertySqlParameterSource(placementDurations));
		}
		try {
			String tableName = "PLACEMENT_DURATIONS";
			String whereCondition = "ROW_ID = :rowId1";
			batchUpdatePreDeletedRows(tableName, whereCondition, parameters);
		} catch (Exception e) {
			logger.error("placementDurDeletePlacementDurations :" + e);
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstPlacementDurations.size() == returnArray.length) {
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
	public List<ReferenceCodes> rgDurationTypeRecordGroup() {
		final String sql = getQuery("OIMPLDUR_FIND_RGDURATIONTYPE");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);
		List<ReferenceCodes> returnList = null;
		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (Exception e) {
			logger.error("rgDurationTypeRecordGroup :" + e);
		}
		return returnList;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<ReferenceCodes>
	 */
	public List<ReferenceCodes> rgPlacementTypeRecordGroup() {
		final String sql = getQuery("OIMPLDUR_FIND_RGPLACEMENTTYPE");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);
		List<ReferenceCodes> returnList = null;
		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (Exception e) {
			logger.error("rgPlacementTypeRecordGroup :" + e);
		}
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * placementDurPostQuery
	 *
	 * @param params
	 */
	public List<ReferenceCodes> placementDurPostQuery(final ReferenceCodes paramBean) {
		final String sql = getQuery("OIMPLDUR_PLACEMENT_DUR_POSTQUERY");
		final RowMapper<ReferenceCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				refCodesMapping);
		return namedParameterJdbcTemplate.query(sql, createParams(), columnRowMapper);
	}

}

package net.syscon.s4.cm.demographics.maintenance.impl;

import java.math.BigDecimal;
import java.sql.SQLException;
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
import net.syscon.s4.cm.demographics.maintenance.OcmohstaRepository;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.CommunityHeaderStatuses;

/**
 * Class OcmohstaRepositoryImpl
 */
@Repository
public class OcmohstaRepositoryImpl extends RepositoryBase implements OcmohstaRepository {
	
	private static Logger logger = LogManager.getLogger(OcmohstaRepositoryImpl.class.getName());

	private final Map<String, FieldMapper> communityHeaderStatusesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("ROW_ID", 						new FieldMapper("rowId"))
			.build();

	/**
	 * Creates new OcmohstaRepositoryImpl class Object
	 */
	public OcmohstaRepositoryImpl() {
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            CommunityHeaderStatuses
	 *
	 * @return List<CommunityHeaderStatuses>
	 *
	 * @throws SQLException
	 */
	public List<CommunityHeaderStatuses> comHdrStExecuteQuery(CommunityHeaderStatuses objSearchDao) {
		final String sql = getQuery("OCMOHSTA_COMHDRST_FIND_COMMUNITY_HEADER_STATUSES");
		final RowMapper<CommunityHeaderStatuses> CommunityHeaderStatusesRowMapper = Row2BeanRowMapper.makeMapping(sql,
				CommunityHeaderStatuses.class, communityHeaderStatusesMapping);

		String preparedSql = null;
		final MapSqlParameterSource inParameterSource = new MapSqlParameterSource();
		final StringBuffer sqlQuery = new StringBuffer();
		sqlQuery.append(sql);
		if (objSearchDao != null) {
			if (objSearchDao.getStatusCode() != null && !objSearchDao.getStatusCode().isEmpty()
					&& !("".equals(objSearchDao.getStatusCode().trim()))) {
				sqlQuery.append(" STATUS_CODE = :STATUS_CODE" + " AND ");
				inParameterSource.addValue("STATUS_CODE", objSearchDao.getStatusCode().trim());
			}
			if (objSearchDao.getDescription() != null && !objSearchDao.getDescription().isEmpty()
					&& !("".equals(objSearchDao.getDescription().trim()))) {
				sqlQuery.append(" DESCRIPTION = :DESCRIPTION" + " AND ");
				inParameterSource.addValue("DESCRIPTION", objSearchDao.getDescription());
			}
			if (objSearchDao.getHierarchySequence() != null) {
				sqlQuery.append(" HIERARCHY_SEQUENCE = :HIERARCHY_SEQUENCE" + " AND ");
				inParameterSource.addValue("HIERARCHY_SEQUENCE", objSearchDao.getHierarchySequence());
			}
			if (objSearchDao.getListSeq() != null) {
				sqlQuery.append(" LIST_SEQ = :LIST_SEQ" + " AND ");
				inParameterSource.addValue("LIST_SEQ", objSearchDao.getListSeq());
			}
			if (objSearchDao.getActiveFlag() != null) {
				if ("true".equals(objSearchDao.getActiveFlag())) {
					objSearchDao.setActiveFlag("Y");
				} else {
					objSearchDao.setActiveFlag("N");
				}
				sqlQuery.append(" ACTIVE_FLAG = :ACTIVE_FLAG" + " AND ");
				inParameterSource.addValue("ACTIVE_FLAG", objSearchDao.getActiveFlag());
			}
			if (objSearchDao.getUpdateAllowedFlag() != null) {
				if ("true".equals(objSearchDao.getUpdateAllowedFlag())) {
					objSearchDao.setUpdateAllowedFlag("Y");
				} else {
					objSearchDao.setUpdateAllowedFlag("N");
				}
				sqlQuery.append(" UPDATE_ALLOWED_FLAG = :UPDATE_ALLOWED_FLAG" + " AND ");
				inParameterSource.addValue("UPDATE_ALLOWED_FLAG", objSearchDao.getUpdateAllowedFlag());
			}
			if (objSearchDao.getExpiryDate() != null) {
				sqlQuery.append(" EXPIRY_DATE = :EXPIRY_DATE" + " AND ");
				inParameterSource.addValue("EXPIRY_DATE", objSearchDao.getExpiryDate());
			}
		}
		preparedSql = sqlQuery.toString().trim();
		if (preparedSql.endsWith("WHERE")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 5);
		}
		if (preparedSql.endsWith("AND")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 3);
		}
		preparedSql = preparedSql + " ORDER BY LIST_SEQ, HIERARCHY_SEQUENCE";
		return namedParameterJdbcTemplate.query(preparedSql, inParameterSource, CommunityHeaderStatusesRowMapper);

	}

	/**
	 * This method is used to insert the records in the data base tables based
	 * on
	 *
	 * @param lstCommunityHeaderStatuses
	 *            List<CommunityHeaderStatuses>
	 *
	 * @return List<Integer>
	 *
	 * @throws SQLException
	 */
	public Integer comHdrStInsertCommunityHeaderStatuses(final List<CommunityHeaderStatuses> lstObj) {
		String sql = getQuery("OCMOHSTA_COMHDRST_INSERT_COMMUNITY_HEADER_STATUSES");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<>();
		for (CommunityHeaderStatuses communityHeaderStatuses : lstObj) {
			parameters.add(new BeanPropertySqlParameterSource(communityHeaderStatuses));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstObj.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstCommunityHeaderStatuses
	 *            List<CommunityHeaderStatuses>
	 *
	 * @throws SQLException
	 */
	public Integer comHdrStUpdateCommunityHeaderStatuses(final List<CommunityHeaderStatuses> lstObj) {
		String sql = getQuery("OCMOHSTA_COMHDRST_UPDATE_COMMUNITY_HEADER_STATUSES");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<>();
		for (CommunityHeaderStatuses communityHeaderStatuses : lstObj) {
			parameters.add(new BeanPropertySqlParameterSource(communityHeaderStatuses));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstObj.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to delete records from data base tables based on
	 *
	 * @param lstCommunityHeaderStatuses
	 *            List<CommunityHeaderStatuses>
	 *
	 * @throws SQLException
	 */
	public Integer comHdrStDeleteCommunityHeaderStatuses(final List<CommunityHeaderStatuses> lstObj) {
		String sql = getQuery("OCMOHSTA_COMHDRST_DELETE_COMMUNITY_HEADER_STATUSES");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<>();
		for (CommunityHeaderStatuses communityHeaderStatuses : lstObj) {
			parameters.add(new BeanPropertySqlParameterSource(communityHeaderStatuses));
		}
		try {
			String tableName = "COMMUNITY_HEADER_STATUSES";
			String whereClause = "STATUS_CODE = :statusCode";
			batchUpdatePreDeletedRows(tableName, whereClause , parameters);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method comHdrStDeleteCommunityHeaderStatuses", e);
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));

		if (lstObj.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	public Integer getStatuscodeCount(final List<String> statusObj) {
		final String sql = getQuery("OCMOHSTA_STATUS_CUR");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("lv_status_code", statusObj), Integer.class);
	}

	public Integer gethierarchyCount(final List<BigDecimal> hirarchyObj) {
		final String sql = getQuery("OCMOHSTA_HIERARCHY_CUR");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("lv_hierarchy", hirarchyObj),
				Integer.class);
	}
	public Integer hirarchyDupValidationWhenUpdate(final List<BigDecimal> hirarchyObj, final List<String> rowId) {
		final String sql = getQuery("OCMOHSTA_HIERARCHY_CUR_UPDATE");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("lv_hierarchy", hirarchyObj, "rowId", rowId),
				Integer.class);
	}
	
}

package net.syscon.s4.cf.deductions.maintenance.impl;

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
import net.syscon.s4.cf.deductions.maintenance.OcmgobliRepository;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.GroupedObligations;
import net.syscon.s4.im.beans.ObligationGroups;
import net.syscon.s4.im.beans.SanctionNotices;
import net.syscon.s4.inmate.beans.DeductionTypes;

/**
 * Class OcmgobliRepositoryImpl
 */
@Repository
public class OcmgobliRepositoryImpl extends RepositoryBase implements OcmgobliRepository {

	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OcmgobliRepositoryImpl.class);

	private final Map<String, FieldMapper> groupedObligationsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("GROUP_ID", 				new FieldMapper("groupId"))
			.build();
	private final Map<String, FieldMapper> deductionTypesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DEDUCTION_TYPE", 			new FieldMapper("deductionType"))
			.put("DEDUCTION_DESC", 			new FieldMapper("deductionDesc"))
			.put("DEDUCTION_CATEGORY", 		new FieldMapper("deductionCategory"))
			.build();
	private final Map<String, FieldMapper> mMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DEDUCTION_TYPE", 			new FieldMapper("deductionType"))
			.put("SANCTION_NOTICE_CODE", 	new FieldMapper("sanctionNoticeCode"))
			.put("DEDUCTION_CATEGORY", 		new FieldMapper("deductionCategory"))
			.put("DESCRIPTION", 			new FieldMapper("description"))
			.put("LATE_DAYS", 				new FieldMapper("lateDays"))
			.build();

	/**
	 * Creates new OcmgobliRepositoryImpl class Object
	 */
	public OcmgobliRepositoryImpl() {
		// OcmgobliRepositoryImpl
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            ObligationGroups
	 *
	 * @return List<ObligationGroups>
	 *
	 */
	public List<ObligationGroups> obGrpExecuteQuery(final ObligationGroups objSearchDao) {
		final String sql = getQuery("OCMGOBLI_OBGRP_FIND_OBLIGATION_GROUPS");
		String preparedSql = null;
		final StringBuffer sqlQuery = new StringBuffer();
		final MapSqlParameterSource params = new MapSqlParameterSource();
		sqlQuery.append(sql);
		if (objSearchDao != null) {
			sqlQuery.append(" WHERE");
			if (objSearchDao.getGroupId() != null) {
				sqlQuery.append(" GROUP_ID  = :groupId and");
				params.addValue("groupId", objSearchDao.getGroupId());
			}
			if (objSearchDao.getGroupCode() != null && !objSearchDao.getGroupCode().trim().equals("")) {
				sqlQuery.append(" GROUP_CODE = :groupCode and");
				params.addValue("groupCode", objSearchDao.getGroupCode());
			}
			if (objSearchDao.getGroupDescription() != null && !objSearchDao.getGroupDescription().trim().equals("")) {
				sqlQuery.append(" GROUP_DESCRIPTION  = :groupDescription and");
				params.addValue("groupDescription", objSearchDao.getGroupDescription());
			}
		}
		preparedSql = sqlQuery.toString().trim();
		if (preparedSql.endsWith("WHERE")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 5);
		}
		if (preparedSql.endsWith("and")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 3);
		}
		preparedSql = preparedSql.concat(" ORDER BY GROUP_ID,GROUP_CODE");
		final RowMapper<ObligationGroups> obligationGroupsRowMapper = Row2BeanRowMapper.makeMapping(preparedSql,
				ObligationGroups.class, mMapping);
		return namedParameterJdbcTemplate.query(preparedSql, params, obligationGroupsRowMapper);
	}

	/**
	 * This method is used to insert the records in the data base tables based
	 * on
	 *
	 * @param lstObligationGroups
	 *            List<ObligationGroups>
	 *
	 * @return List<Integer>
	 *
	 */
	public Integer obGrpInsertObligationGroups(final List<ObligationGroups> lstObligationGroups) {
		final String sql = getQuery("OCMGOBLI_OBGRP_INSERT_OBLIGATION_GROUPS");
		int[] returnArray;
		final List<SqlParameterSource> parameters = new ArrayList<>();
		for (final ObligationGroups obligationGroups : lstObligationGroups) {
			parameters.add(new BeanPropertySqlParameterSource(obligationGroups));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstObligationGroups.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstObligationGroups
	 *            List<ObligationGroups>
	 */
	public Integer obGrpUpdateObligationGroups(final List<ObligationGroups> lstObligationGroups) {
		final String sql = getQuery("OCMGOBLI_OBGRP_UPDATE_OBLIGATION_GROUPS");
		int[] returnArray;
		final List<SqlParameterSource> parameters = new ArrayList<>();
		for (final ObligationGroups obligationGroups : lstObligationGroups) {
			parameters.add(new BeanPropertySqlParameterSource(obligationGroups));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstObligationGroups.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to delete records from data base tables based on
	 *
	 * @param lstObligationGroups
	 *            List<ObligationGroups>
	 *
	 */
	public Integer obGrpDeleteObligationGroups(final List<ObligationGroups> lstObligationGroups) {
		final String sql = getQuery("OCMGOBLI_OBGRP_DELETE_OBLIGATION_GROUPS");
		int[] returnArray;
		final List<SqlParameterSource> parameters = new ArrayList<>();
		for (final ObligationGroups obligationGroups : lstObligationGroups) {
			parameters.add(new BeanPropertySqlParameterSource(obligationGroups));
		}
		try {
			String tableName = "OBLIGATION_GROUPS";
			String whereClause = "GROUP_ID = :groupId";
			batchUpdatePreDeletedRows(tableName, whereClause , parameters);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method obGrpDeleteObligationGroups", e);
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstObligationGroups.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            GroupedObligations
	 *
	 * @return List<GroupedObligations>
	 *
	 */
	public List<GroupedObligations> grpObExecuteQuery(final GroupedObligations objSearchDao) {
		final String sql = getQuery("OCMGOBLI_GRPOB_FIND_GROUPED_OBLIGATIONS");
		final RowMapper<GroupedObligations> groupedObligationsRowMapper = Row2BeanRowMapper.makeMapping(sql,
				GroupedObligations.class, groupedObligationsMapping);
		return namedParameterJdbcTemplate.query(sql, createParams("groupId", objSearchDao.getGroupId()),
				groupedObligationsRowMapper);
	}

	/**
	 * This method is used to insert the records in the data base tables based
	 * on
	 *
	 * @param lstGroupedObligations
	 *            List<GroupedObligations>
	 *
	 * @return List<Integer>
	 *
	 */
	public Integer grpObInsertGroupedObligations(final List<GroupedObligations> lstGroupedObligations) {
		final String sql = getQuery("OCMGOBLI_GRPOB_INSERT_GROUPED_OBLIGATIONS");
		int[] returnArray;
		final List<SqlParameterSource> parameters = new ArrayList<>();
		for (final GroupedObligations groupedObligations : lstGroupedObligations) {
			parameters.add(new BeanPropertySqlParameterSource(groupedObligations));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstGroupedObligations.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstGroupedObligations
	 *            List<GroupedObligations>
	 * 
	 */
	public Integer grpObUpdateGroupedObligations(final List<GroupedObligations> lstGroupedObligations) {
		final String sql = getQuery("OCMGOBLI_GRPOB_UPDATE_GROUPED_OBLIGATIONS");
		int[] returnArray;
		final List<SqlParameterSource> parameters = new ArrayList<>();
		for (final GroupedObligations groupedObligations : lstGroupedObligations) {
			parameters.add(new BeanPropertySqlParameterSource(groupedObligations));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstGroupedObligations.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to delete records from data base tables based on
	 *
	 * @param lstGroupedObligations
	 *            List<GroupedObligations>
	 *
	 */
	public Integer grpObDeleteGroupedObligations(final List<GroupedObligations> lstGroupedObligations) {
		final String sql = getQuery("OCMGOBLI_GRPOB_DELETE_GROUPED_OBLIGATIONS");
		int[] returnArray;
		final List<SqlParameterSource> parameters = new ArrayList<>();
		for (final GroupedObligations groupedObligations : lstGroupedObligations) {
			parameters.add(new BeanPropertySqlParameterSource(groupedObligations));
		}
		try {
			String tableName = "GROUPED_OBLIGATIONS";
			String whereClause = "DEDUCTION_TYPE = :deductionType AND GROUP_ID = :groupId";
			batchUpdatePreDeletedRows(tableName, whereClause , parameters);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method grpObDeleteGroupedObligations", e);
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstGroupedObligations.size() == returnArray.length) {
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

	public List<DeductionTypes> cgfkGrpObDeductionTypeRecordGroup() {
		final String sql = getQuery("OCMGOBLI_FIND_CGFKGRPOBDEDUCTIONTYPE");
		final RowMapper<DeductionTypes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, DeductionTypes.class, mMapping);
		return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */

	public List<SanctionNotices> cgfkSanctionNoticesRecordGroup() {
		final String sql = getQuery("OCMGOBLI_FIND_CGFKSANCTIONNOTICES");
		final RowMapper<SanctionNotices> mRowMapper = Row2BeanRowMapper.makeMapping(sql, SanctionNotices.class,
				mMapping);
		return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
	}
}

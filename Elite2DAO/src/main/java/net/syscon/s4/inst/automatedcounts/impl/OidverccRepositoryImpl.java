package net.syscon.s4.inst.automatedcounts.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
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
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.inst.automatedcounts.OidverccRepository;
import net.syscon.s4.inst.automatedcounts.beans.AgencyCountTypes;
import net.syscon.s4.inst.automatedcounts.beans.AgencyLocationCounts;
import net.syscon.s4.inst.automatedcounts.beans.VReportingLocations;

/**
 * Class OidverccRepositoryImpl
 */
@Repository
public class OidverccRepositoryImpl extends RepositoryBase implements OidverccRepository {
	
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OidverccRepositoryImpl.class.getName());

	private final Map<String, FieldMapper> mMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DECODE(SCHEDULED_TIME",      new FieldMapper("decode(scheduledTime"))
			.put("COUNT_TYPE_CODE",            new FieldMapper("countTypeCode "))
			.put("CODE",                       new FieldMapper("code"))
			.put("SCHEDULED_TIME",             new FieldMapper("scheduledTime"))
			.put("AGY_LOC_ID",                 new FieldMapper("code"))
			.put("DESCRIPTION",                new FieldMapper("description"))
			.build();
	private final Map<String, FieldMapper> agencyLocationsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("AGY_LOC_ID",                new FieldMapper("agyLocId"))
			.put("DESCRIPTION",               new FieldMapper("description"))
			.build();
	private final Map<String, FieldMapper> agencyCountTypesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("AGY_LOC_ID",                new FieldMapper("agyLocId"))
			.put("'9999'",                    new FieldMapper("'9999'"))
			.put("REPORTING_LOC_ID",          new FieldMapper("reportingLocId"))
			.put("COUNT_TYPE_ID",             new FieldMapper("countTypeId"))
			.put("COUNT_TYPE_CODE",           new FieldMapper("countTypeCode"))
			.put("INITIATED_DATE",            new FieldMapper("nitiatedDate "))
			.put("'Y'",                       new FieldMapper("'y'"))
			.put("DECODE(SCHEDULED_TIME",     new FieldMapper("decode(scheduledTime"))
			.put("SCHEDULED_TIME)",           new FieldMapper("scheduledTime)"))
			.put("CASELOAD_ID)",              new FieldMapper("caseLoadId)"))
			.put("SESSION_ID)",               new FieldMapper("sessionID)"))
			.build();
	private final Map<String, FieldMapper> vReportLocationMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("AGY_LOC_ID",               new FieldMapper("agyLocId"))
			.put("COUNT_TYPE_ID",            new FieldMapper("countTypeId"))
			.put("COUNT_TYPE_CODE",          new FieldMapper("countTypeCode"))
			.put("SCHEDULED_TIME",           new FieldMapper("scheduledTime"))
			.put("AGY_SEQ)",                 new FieldMapper("agySeq"))
			.put("LOCATION_TYPE",            new FieldMapper("locationType"))
			.put("LOCATION1_ID",             new FieldMapper("location1Id"))
			.put("LOCATION2_ID",             new FieldMapper("location2Id"))
			.put("LOCATION3_ID",             new FieldMapper("location3Id"))
			.put("REPORTING_LOC_ID",         new FieldMapper("reportingLocId"))
			.put("LIST_SEQ",                 new FieldMapper("listSeq"))
			.put("DATE_SUBMITTED",           new FieldMapper("dateSubmitted"))
			.put("ACTUAL_COUN)",             new FieldMapper("actualCount"))
			.put("REPORTED_COUNT",           new FieldMapper("reportedCount"))
			.put("CONDUCTED_BY_USERID",      new FieldMapper("conductedByUserid"))
			.put("ENTERED_BY_USERID",        new FieldMapper("enteredByUserid"))
			.put("CONDUCTED_DATETIME",       new FieldMapper("conductedDateTime"))
			.put("RECOUNT_RSN_CODE",         new FieldMapper("recountRsnCode"))
			.put("DISCREP_RSN_CODE",         new FieldMapper("discrepRsnCode"))
			.put("RSN_CODE_USERID",          new FieldMapper("rsnCodeUserId"))
			.put("RSN_CODE_DATETIME",        new FieldMapper("rsnCodeDateTime"))
			.put("VERIFIED_USER_ID",         new FieldMapper("verifyUserId"))
			.put("VERIFIED_DATETIME",        new FieldMapper("verifyDateTime"))
			.put("RCNT_CONDUCTED_BY",        new FieldMapper("rcntConductedBy"))
			.put("RCNT_DATETIME",            new FieldMapper("rcntDateTime"))
			.put("RCNT_IN_PROGRESS_FLAG",    new FieldMapper("rcntInProgressFlag"))
			.put("RECOUNT_TOTAL",            new FieldMapper("recountTotal"))
			.put("REPORTED_TOTAL",           new FieldMapper("reportedTotal"))
			.put("LOCATION_DESCRIPTION",     new FieldMapper("locationDescription"))
			.build();

	/**
	 * Creates new OidverccRepositoryImpl class Object
	 */
	public OidverccRepositoryImpl() {
		// OidverccRepositoryImpl
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            AgencyCountTypes
	 *
	 * @return List<AgencyCountTypes>
	 *
	 */
	public List<AgencyCountTypes> agencyCountTypesExecuteQuery(final AgencyCountTypes objSearchDao) {
		final String sql = getQuery("OIDVERCC_AGENCYCOUNTTYPES_FIND_AGENCY_COUNT_TYPES");
		final RowMapper<AgencyCountTypes> actRowMapper = Row2BeanRowMapper.makeMapping(sql, AgencyCountTypes.class,
				agencyCountTypesMapping);
		final ArrayList<AgencyCountTypes> returnList = (ArrayList<AgencyCountTypes>) namedParameterJdbcTemplate
				.query(sql, createParams(), actRowMapper);
		return returnList;
	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstAgencyCountTypes
	 *            List<AgencyCountTypes>
	 *
	 */
	public Integer agencyCountTypesUpdateAgencyCountTypes(final List<AgencyCountTypes> list) {
		final String sql = getQuery("OIDVERCC_AGENCYCOUNTTYPES_UPDATE_AGENCY_COUNT_TYPES");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final AgencyCountTypes agencyCountTypes : list) {
			parameters.add(new BeanPropertySqlParameterSource(agencyCountTypes));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (list.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to delete records from data base tables based on
	 *
	 * @param lstAgencyCountTypes
	 *            List<AgencyCountTypes>
	 *
	 */
	public Integer agencyCountTypesDeleteAgencyCountTypes(final List<AgencyCountTypes> list) {
		final String sql = getQuery("OIDVERCC_AGENCYCOUNTTYPES_DELETE_AGENCY_COUNT_TYPES");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final AgencyCountTypes agencyCountTypes : list) {
			parameters.add(new BeanPropertySqlParameterSource(agencyCountTypes));
		}
		try {
			String tableName = "AGENCY_COUNT_TYPES";
			String whereClause = null;
			batchUpdatePreDeletedRows(tableName, whereClause , parameters);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method agencyCountTypesDeleteAgencyCountTypes", e);
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (list.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            VReportingLocations
	 *
	 * @return List<VReportingLocations>
	 *
	 */
	public List<VReportingLocations> reportingLocationsExecuteQuery(final VReportingLocations objSearchDao) {
		final String sql = getQuery("OIDVERCC_REPORTINGLOCATIONS_FIND_V_REPORTING_LOCATIONS");
		String preparedSql = null;
		ArrayList<VReportingLocations> returnList=null;
		final StringBuffer sqlQuery = new StringBuffer();
		final MapSqlParameterSource params = new MapSqlParameterSource();
		sqlQuery.append(sql);
		if (objSearchDao != null) {
			sqlQuery.append(" WHERE ");
			if (objSearchDao.getAgyLocId() != null) {
				sqlQuery.append("AGY_LOC_ID = :AGY_LOC_ID" + " and ");
				params.addValue("AGY_LOC_ID", objSearchDao.getAgyLocId());
				if (objSearchDao.getCountTypeCode() != null) {
					sqlQuery.append("(COUNT_TYPE_CODE = :COUNT_TYPE_CODE OR :COUNT_TYPE_CODE IS NULL) " + " and ");
					params.addValue("COUNT_TYPE_CODE", objSearchDao.getCountTypeCode());
				}
				if (objSearchDao.getScheduledTime() != null) {
					sqlQuery.append("(SCHEDULED_TIME = :SCHEDULED_TIME  OR :SCHEDULED_TIME IS NULL) " + " and ");
					params.addValue("SCHEDULED_TIME", objSearchDao.getScheduledTime());
				}
				if (objSearchDao.getConductedDateTime() != null) {
					sqlQuery.append("(CONDUCTED_DATETIME::date = to_date('"+new java.sql.Date(objSearchDao.getConductedDateTime().getTime())+"','yyyy-mm-dd') OR to_date(:NBT_COUNT_DATE,'yyyy-mm-dd') IS NULL) "
							+ " and ");
					params.addValue("NBT_COUNT_DATE", new java.sql.Date(objSearchDao.getConductedDateTime().getTime()));
				} else {
					sqlQuery.append("(CONDUCTED_DATETIME IS NULL) " + " and ");
				}
				sqlQuery.append("VERIFIED_DATETIME is null and VERIFIED_USER_ID is null AND CONDUCTED_BY_USERID = :USER"
						+ " AND ");
				params.addValue("USER", objSearchDao.getConductedByUserid());
				if (objSearchDao.getConductedDateTime() != null) {
					sqlQuery.append(
							"TO_CHAR(REPORTING_LOC_ID)||'X'||TO_CHAR(COUNT_TYPE_ID) IN (SELECT TO_CHAR(REPORTING_LOC_ID)||'X'||TO_CHAR(COUNT_TYPE_ID) FROM AGENCY_COUNTS  WHERE (INITIATED_DATE::date = to_date('"+new java.sql.Date(objSearchDao.getConductedDateTime().getTime())+"','yyyy-mm-dd') OR to_date(:NBT_COUNT_DATE,'yyyy-mm-dd') IS NULL) AND OUTCOME = 'CLEARED') "
									+ " AND ");
					params.addValue("NBT_COUNT_DATE", new java.sql.Date(objSearchDao.getConductedDateTime().getTime()));
				} else {
					sqlQuery.append(
							"TO_CHAR(REPORTING_LOC_ID)||'X'||TO_CHAR(COUNT_TYPE_ID) IN (SELECT TO_CHAR(REPORTING_LOC_ID)||'X'||TO_CHAR(COUNT_TYPE_ID) FROM AGENCY_COUNTS  WHERE (INITIATED_DATE IS NULL) AND OUTCOME = 'CLEARED') "
									+ " AND ");
				}
			}
		}
		preparedSql = sqlQuery.toString().trim();
		if (preparedSql.endsWith(" WHERE")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 5);
		}
		if (preparedSql.endsWith("AND")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 3);
		}
		preparedSql = preparedSql.concat(" ORDER BY CONDUCTED_DATETIME DESC ");
		final RowMapper<VReportingLocations> vReportLocMapper = Row2BeanRowMapper.makeMapping(sql,
				VReportingLocations.class, vReportLocationMapping);
		try {
			returnList = (ArrayList<VReportingLocations>) namedParameterJdbcTemplate.query(preparedSql, params, vReportLocMapper);
		}catch (Exception e) {
			logger.error(e);
		}
		return returnList;
	}

	/**
	 * This method is used to delete records from data base tables based on
	 *
	 * @param lstVReportingLocations
	 *            List<VReportingLocations>
	 *
	 */
	public Integer reportingLocationsDeleteVReportingLocations(final List<VReportingLocations> list) {
		final String sql = getQuery("OIDVERCC_REPORTINGLOCATIONS_DELETE_V_REPORTING_LOCATIONS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final VReportingLocations obj : list) {
			parameters.add(new BeanPropertySqlParameterSource(obj));
		}
		try {
			String tableName = "V_REPORTING_LOCATIONS";
			String whereClause = null;
			batchUpdatePreDeletedRows(tableName, whereClause , parameters);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method reportingLocationsDeleteVReportingLocations", e);
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (list.size() == returnArray.length) {
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
	public List<ReferenceCodes> cgfkAgyLocIdRecordGroup(final String caseLoadId) {
		final String sql = getQuery("OIDVERCC_FIND_CGFKAGYLOCID");
		List<ReferenceCodes> returnList = new ArrayList<ReferenceCodes>();
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);

		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams("CASELOADID", caseLoadId), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("", e);
		}
		return returnList;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<ReferenceCodes> cgfkCountTypesRecordGroup(final String agyLocId) {
		final String sql = getQuery("OIDVERCC_FIND_CGFKCOUNTTYPES");
		List<ReferenceCodes> returnList = new ArrayList<ReferenceCodes>();
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);

		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams("AGYLOCID", agyLocId), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("", e);
		}
		return returnList;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<AgencyCountTypes> cgfkScheduledTimeRecordGroup(final String agyLocId, final String countTypeCode) {
		final String sql = getQuery("OIDVERCC_FIND_CGFKSCHEDULEDTIME");
		List<AgencyCountTypes> returnList = new ArrayList<AgencyCountTypes>();
		final RowMapper<AgencyCountTypes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, AgencyCountTypes.class,
				agencyCountTypesMapping);
		try {
			returnList = namedParameterJdbcTemplate.query(sql,
					createParams("AGYLOCID", agyLocId, "COUNTTYPECODE", countTypeCode), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("", e);
		}
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * defaultAgyLocdefaultAgyLoc
	 *
	 * @param params
	 *
	 */
	public List<AgencyLocations> defaultAgyLocdefaultAgyLoc(final AgencyLocations paramBean) {
		final String sql = getQuery("OIDVERCC_DEFAULT_AGY_LOC_DEFAULT_AGY_LOC");
		final RowMapper<AgencyLocations> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, AgencyLocations.class,
				agencyLocationsMapping);
		final ArrayList<AgencyLocations> returnList = (ArrayList<AgencyLocations>) namedParameterJdbcTemplate.query(sql,
				createParams(paramBean), columnRowMapper);
		return returnList;
	}

	@Override
	public Integer agencyCountTypesInsertAgencyCountTypes(final List<AgencyCountTypes> list) {
		final String sql = getQuery("OIDVERCC_AGENCYCOUNTTYPES_INSERT_AGENCY_COUNT_TYPES");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final AgencyCountTypes obj : list) {
			parameters.add(new BeanPropertySqlParameterSource(obj));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (list.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

	/**
	 * Used to update the record in the data base
	 * 
	 * @param bean
	 * @return Integer
	 */
	public Integer updateAgencyLocationCounts(final AgencyLocationCounts bean) {
		final String sql = getQuery("OIDVERCC_UPDATE_AGENCY_LOCATION_COUNTS");
		int[] returnArray = new int[] {};
		final List<AgencyLocationCounts> updateList = new ArrayList<AgencyLocationCounts>();
		updateList.add(bean);
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final AgencyLocationCounts obj : updateList) {
			parameters.add(new BeanPropertySqlParameterSource(obj));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (updateList.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

}

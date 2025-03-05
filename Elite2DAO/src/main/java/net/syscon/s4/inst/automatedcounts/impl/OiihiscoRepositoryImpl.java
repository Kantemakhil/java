package net.syscon.s4.inst.automatedcounts.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.AgencyLocationCounts;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.inst.automatedcounts.OiihiscoRepository;
import net.syscon.s4.inst.automatedcounts.beans.AgencyCountTypes;
import net.syscon.s4.inst.automatedcounts.beans.AgencyCounts;

/**
 * Class OiihiscoRepositoryImpl
 */
@Repository
public class OiihiscoRepositoryImpl extends RepositoryBase implements OiihiscoRepository {

	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger log = LogManager.getLogger(OiihiscoRepositoryImpl.class.getName());
	private final Map<String, FieldMapper> referenceCodesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CODE", new FieldMapper("code")).put("DESCRIPTION", new FieldMapper("description")).build();
	private final Map<String, FieldMapper> agencyLocationCountsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("RCNT_IN_PROGRESS_FLAG", new FieldMapper("rcntInProgressFlag"))
			.put("RCNT_CONDUCTED_BY", new FieldMapper("rcntConductedBy"))
			.put("ENTERED_BY_USERID", new FieldMapper("enteredByUserid"))
			.put("DISCREP_RSN_CODE", new FieldMapper("discrepRsnCode"))
			.put("COUNT_TYPE_ID", new FieldMapper("countTypeId")).build();
	private final Map<String, FieldMapper> mMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("COUNT_TYPE_CODE", new FieldMapper("countTypeCode"))
			.put("SCHEDULED_TIME", new FieldMapper("scheduledTime")).put("AGY_LOC_ID", new FieldMapper("agyLocId"))
			.put("DESCRIPTION", new FieldMapper("description")).put("SCH_TIME", new FieldMapper("schTime")).build();
	private final Map<String, FieldMapper> agencyCountsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("TOTAL_ACTUAL", new FieldMapper("totalActual"))
			.put("TOTAL_FEMALE_OUT", new FieldMapper("totalFemaleOut"))
			.put("INITIATED_DATE", new FieldMapper("initiatedDate"))
			.put("RSN_CODE_USERID", new FieldMapper("rsnCodeUserid")).put("SEAL_FLAG", new FieldMapper("sealFlag"))
			.put("CREATE_DATETIME", new FieldMapper("createDatetime"))
			.put("TOTAL_FEMALE", new FieldMapper("totalFemale"))
			.put("RECOUNT_RSN_CODE", new FieldMapper("recountRsnCode"))
			.put("MODIFY_DATETIME", new FieldMapper("modifyDatetime")).put("TOTAL_OTHER", new FieldMapper("totalOther"))
			.put("TOTAL_OTHER_OUT", new FieldMapper("totalOtherOut"))
			.put("CONDUCTED_BY_USERID", new FieldMapper("conductedByUserid"))
			.put("COMMENT_TEXT", new FieldMapper("commentText")).put("OUT_TOTAL", new FieldMapper("outTotal"))
			.put("TOTAL_MALE_OUT", new FieldMapper("totalMaleOut"))
			.put("CREATE_USER_ID", new FieldMapper("createUserId"))
			.put("RSN_CODE_DATETIME", new FieldMapper("rsnCodeDatetime"))
			.put("PARENT_REPORTING_LOC_ID", new FieldMapper("parentReportingLocId"))
			.put("MODIFY_USER_ID", new FieldMapper("modifyUserId"))
			.put("TOTAL_REPORTED", new FieldMapper("totalReported"))
			.put("COMPLETION_DATE", new FieldMapper("completionDate"))
			.put("REPORTING_LOC_ID", new FieldMapper("reportingLocId"))
			.put("COUNT_IN_PROGRESS", new FieldMapper("countInProgress")).put("OUTCOME", new FieldMapper("outcome"))
			.put("DISCREP_RSN_CODE", new FieldMapper("discrepRsnCode"))
			.put("COUNT_TYPE_ID", new FieldMapper("countTypeId")).put("TOTAL_MALE", new FieldMapper("totalMale"))
			.build();

	/**
	 * Creates new OiihiscoRepositoryImpl class Object
	 */
	public OiihiscoRepositoryImpl() {
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao AgencyCounts
	 *
	 * @return List<AgencyCounts>
	 *
	 * @
	 */
	public List<AgencyCounts> agencyCountsExecuteQuery(final AgencyCounts searchBean) {
		final String sql = getQuery("OIIHISCO_AGENCYCOUNTS_FIND_AGENCY_COUNTS");
		final RowMapper<AgencyCounts> AgencyCountsRowMapper = Row2BeanRowMapper.makeMapping(sql, AgencyCounts.class,
				agencyCountsMapping);
		String preparedSql = null;
		String preSql = null;
		List<AgencyCounts> returnList = new ArrayList<AgencyCounts>();
		final MapSqlParameterSource inParameterSource = new MapSqlParameterSource();
		final StringBuffer sqlQuery = new StringBuffer();
		sqlQuery.append(sql);
		if (searchBean != null) {
			sqlQuery.append(" WHERE OUTCOME IS NOT NULL AND ");
			if (searchBean.getInitiatedDate() != null) {
				sqlQuery.append("INITIATED_DATE = :INITIATED_DATE" + " AND ");
				inParameterSource.addValue("INITIATED_DATE", searchBean.getInitiatedDate());
			}
			if (searchBean.getCompletionDate() != null) {
				sqlQuery.append("COMPLETION_DATE = :COMPLETION_DATE" + " AND ");
				inParameterSource.addValue("COMPLETION_DATE", searchBean.getCompletionDate());
			}
			if (searchBean.getFromDate() != null && searchBean.getToDate() != null) {
				sqlQuery.append("INITIATED_DATE::TIMESTAMP::DATE >= to_date('" + new java.sql.Date(searchBean.getFromDate().getTime())
						+ "','yyyy/MM/dd')" + " AND ");
				sqlQuery.append("INITIATED_DATE::TIMESTAMP::DATE <= to_date('" + new java.sql.Date(searchBean.getToDate().getTime())
						+ "','yyyy/MM/dd')" + " AND ");
			} else if (searchBean.getFromDate() != null && searchBean.getToDate() == null) {
				sqlQuery.append("INITIATED_DATE::TIMESTAMP::DATE >= to_date('" + new java.sql.Date(searchBean.getFromDate().getTime())
						+ "','yyyy/MM/dd')" + " AND ");
			} else if (searchBean.getFromDate() == null && searchBean.getToDate() != null) {
				sqlQuery.append("INITIATED_DATE::TIMESTAMP::DATE <= to_date('" + new java.sql.Date(searchBean.getToDate().getTime())
						+ "','yyyy/MM/dd')" + " AND ");
			}
			
			if (searchBean.getAgylocId() != null) {
				sqlQuery.append(
						"COUNT_TYPE_ID IN (SELECT COUNT_TYPE_ID FROM AGENCY_COUNT_TYPES WHERE AGY_LOC_ID = :AGY_LOC_ID )");
				inParameterSource.addValue("AGY_LOC_ID", searchBean.getAgylocId());
			}
			if (searchBean.getCountTypeCode() != null) {
				sqlQuery.append(
						" AND count_type_id IN (select count_type_id from agency_count_types where count_type_code = :COUNT_TYPE_CODE)");
				inParameterSource.addValue("COUNT_TYPE_CODE", searchBean.getCountTypeCode());
			}
			if (searchBean.getScheduledTime() != null) {
				sqlQuery.append(
						"  AND COUNT_TYPE_ID IN (SELECT COUNT_TYPE_ID FROM AGENCY_COUNT_TYPES WHERE SCHEDULED_TIME =  :SCHEDULED_TIME )");
				inParameterSource.addValue("SCHEDULED_TIME", (searchBean.getScheduledTime()));
			}

		}
		preparedSql = sqlQuery.toString().trim();
		if (preparedSql.endsWith(" WHERE")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 5);
		}
		if (preparedSql.endsWith("AND")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 3);
		}
		preSql = preparedSql.concat(" ORDER BY INITIATED_DATE DESC");
		returnList = namedParameterJdbcTemplate.query(preSql, inParameterSource, AgencyCountsRowMapper);
		return returnList;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao AgencyLocationCounts
	 *
	 * @return List<AgencyLocationCounts>
	 *
	 * @
	 */
	public List<AgencyLocationCounts> agencyLocationCountsExecuteQuery(AgencyLocationCounts objSearchDao) {
		final String sql = getQuery("OIIHISCO_AGENCYLOCATIONCOUNTS_FIND_AGENCY_LOCATION_COUNTS");
		final RowMapper<AgencyLocationCounts> AgencyLocationCountsRowMapper = Row2BeanRowMapper.makeMapping(sql,
				AgencyLocationCounts.class, agencyLocationCountsMapping);
		List<AgencyLocationCounts> returnList = new ArrayList<AgencyLocationCounts>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql,
					createParams("REPORTING_LOC_ID", objSearchDao.getReportingLocId()), AgencyLocationCountsRowMapper);
		} catch (Exception e) {
			return returnList;
		}

		return returnList;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<AgencyLocations> cgfkAgyLocIdRecordGroup(final String caseloadId) {
		final String sql = getQuery("OIIHISCO_FIND_CGFKAGYLOCID");
		final RowMapper<AgencyLocations> mRowMapper = Row2BeanRowMapper.makeMapping(sql, AgencyLocations.class,
				mMapping);
		List<AgencyLocations> returnList = new ArrayList<AgencyLocations>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams("CASELOAD_ID", caseloadId), mRowMapper);
		} catch (Exception e) {
			log.error("cgfkAgyLocIdRecordGroup", e);
		}
		return returnList;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<MM>
	 */
	public List<ReferenceCodes> cgfkCountTypesRecordGroup(final String location) {
		final String sql = getQuery("OIIHISCO_FIND_CGFKCOUNTTYPES");
		final RowMapper<ReferenceCodes> mMRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				referenceCodesMapping);
		List<ReferenceCodes> returnList = new ArrayList<ReferenceCodes>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams("LOCATION", location), mMRowMapper);
		} catch (Exception e) {
			log.error("cgfkCountTypesRecordGroup", e);
		}
		return returnList;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<AgencyCountTypes> cgfkSchTimeRecordGroup(final String countTypeCode, final String agylocId) {
		final String sql = getQuery("OIIHISCO_FIND_CGFKSCHTIME");
		final RowMapper<AgencyCountTypes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, AgencyCountTypes.class,
				mMapping);
		List<AgencyCountTypes> returnList = new ArrayList<AgencyCountTypes>();

		try {
			returnList = namedParameterJdbcTemplate.query(sql,
					createParams("COUNTTYPE", countTypeCode, "LOCATION", agylocId), mRowMapper);
		} catch (Exception e) {
			log.error("cgfkSchTimeRecordGroup", e);
		}
		return returnList;
	}

	@Override
	public String getLocationDesc(final Integer reportingLocId, final Integer agySeq) {
		final String sql = getQuery("OIIHISCO_AGENCY_LOCATION_COUNTS_POSTQUERY");
		String returnList = null;
		try {
			returnList = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("REPORTING_LOC_ID", reportingLocId, "AGY_SEQ", agySeq), String.class);
		} catch (Exception e) {
			log.error("cgfkSchTimeRecordGroup", e);
		}
		return returnList;
	}

	@Override
	public List<AgencyCountTypes> getCountIdAndTime(final Integer countTypeId) {
		final String sql = getQuery("OIIHISCO_AGENCY_COUNTS_POSTQUERY");
		final RowMapper<AgencyCountTypes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, AgencyCountTypes.class,
				mMapping);
		List<AgencyCountTypes> returnList = new ArrayList<AgencyCountTypes>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams("COUNT_TYPE_ID", countTypeId), mRowMapper);
		} catch (Exception e) {
			log.error("cgfkSchTimeRecordGroup", e);
		}
		return returnList;
	}

	@Override
	public String getRecountComment(Integer reportingLocId) {
		final String sql = getQuery("OIIHISCO_COMMENT_TEXT");
		String commentText = "";
		try {
			commentText = namedParameterJdbcTemplate.queryForObject(sql, createParams("reportingLocId", reportingLocId), String.class);
		}catch (Exception e) {
			log.error("getRecountComment", e);
		}
		return commentText;
	}

}

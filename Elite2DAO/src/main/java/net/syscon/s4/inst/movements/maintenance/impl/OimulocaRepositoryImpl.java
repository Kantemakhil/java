package net.syscon.s4.inst.movements.maintenance.impl;

import java.util.ArrayList;
import java.util.Collections;
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
import net.syscon.s4.common.beans.AgencyInternalLocations;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.inst.movements.maintenance.OimulocaRepository;
import net.syscon.s4.inst.property.bean.InternalLocationUsages;
import net.syscon.s4.inst.schedules.bean.IntLocUsageLocations;

/**
 * Class OimulocaRepositoryImpl
 */
@Repository
public class OimulocaRepositoryImpl extends RepositoryBase implements OimulocaRepository {

	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OimulocaRepositoryImpl.class.getName());
	private final Map<String, FieldMapper> internalLocationUsagesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("INTERNAL_LOCATION_USAGE", new FieldMapper("internalLocationUsage"))
			.put("CREATE_DATETIME", new FieldMapper("createDatetime"))
			.put("MODIFY_DATETIME", new FieldMapper("modifyDatetime")).build();
	private final Map<String, FieldMapper> intLocUsageLocationsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("INTERNAL_LOCATION_USAGE_ID", new FieldMapper("internalLocationUsageId"))
			.put("CREATE_DATETIME", new FieldMapper("createDatetime"))
			.put("MODIFY_DATETIME", new FieldMapper("modifyDatetime")).build();
	private final Map<String, FieldMapper> referenceCodesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DESCRIPTION", new FieldMapper("description"))
			.put("CODE", new FieldMapper("code"))
			.put("ACTIVE_FLAG", new FieldMapper("activeFlag")).build();
	private final Map<String, FieldMapper> agyLocMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DESCRIPTION", new FieldMapper("description"))
			.put("CODE", new FieldMapper("code"))
			.put("ACTIVE_FLAG", new FieldMapper("activeFlag")).build();
	private final Map<String, FieldMapper> agencyInternalLocationsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("INTERNAL_LOCATION_ID", new FieldMapper("internalLocationId"))
			.put("INTERNAL_LOCATION_CODE", new FieldMapper("internalLocationCode"))
			.put("AGY_LOC_ID", new FieldMapper("agyLocId"))
			.put("INTERNAL_LOCATION_TYPE", new FieldMapper("internalLocationType"))
			.put("DESCRIPTION", new FieldMapper("description"))
			.put("SECURITY_LEVEL_CODE", new FieldMapper("securityLevelCode"))
			.put("CAPACITY", new FieldMapper("capacity")).put("CREATE_USER_ID", new FieldMapper("createUserId"))
			.put("PARENT_INTERNAL_LOCATION_ID", new FieldMapper("parentInternalLocationId"))
			.put("ACTIVE_FLAG", new FieldMapper("activeFlag")).put("LIST_SEQ", new FieldMapper("listSeq"))
			.put("CREATE_DATETIME", new FieldMapper("createDateTime"))
			.put("MODIFY_DATETIME", new FieldMapper("modifyDateTime"))
			.put("MODIFY_USER_ID", new FieldMapper("modifyUserId")).put("CNA_NO", new FieldMapper("cnaNo"))
			.put("CERTIFIED_FLAG", new FieldMapper("certifiedFlag"))
			.put("DEACTIVATE_DATE", new FieldMapper("deactiveDate"))
			.put("REACTIVATE_DATE", new FieldMapper("reactiveDate"))
			.put("DEACTIVATE_REASON_CODE", new FieldMapper("deactiveReasonCode"))
			.put("COMMENT_TEXT", new FieldMapper("commentText")).put("USER_DESC", new FieldMapper("userDesc"))
			.put("ACA_CAP_RATING", new FieldMapper("acaCapRating")).put("UNIT_TYPE", new FieldMapper("unitType"))
			.put("OPERATION_CAPACITY", new FieldMapper("operationCapacity"))
			.put("NO_OF_OCCUPANT", new FieldMapper("noOfOccupant"))
			.put("TRACKING_FLAG", new FieldMapper("trackingFlag")).put("SEAL_FLAG", new FieldMapper("sealFlag"))
			.build();

	/**
	 * Creates new OimulocaRepositoryImpl class Object
	 */
	public OimulocaRepositoryImpl() {
		// OimulocaRepositoryImpl
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao InternalLocationUsages
	 *
	 * @return List<InternalLocationUsages>
	 */
	public List<InternalLocationUsages> usagesExecuteQuery(final InternalLocationUsages objSearchDao) {
		final String sql = getQuery("OIMULOCA_USAGES_FIND_INTERNAL_LOCATION_USAGES");
		final RowMapper<InternalLocationUsages> intLocRowMapper = Row2BeanRowMapper.makeMapping(sql,
				InternalLocationUsages.class, internalLocationUsagesMapping);
		List<InternalLocationUsages> returnList = new ArrayList<>();
		returnList = (ArrayList<InternalLocationUsages>) namedParameterJdbcTemplate.query(sql,
				createParams("AGY_LOC_ID", objSearchDao.getAgyLocId()), intLocRowMapper);
		return returnList;
	}

	/**
	 * This method is used to insert the records in the data base tables based on
	 *
	 * @param lstInternalLocationUsages List<InternalLocationUsages>
	 *
	 * @return List<Integer>
	 */
	public String usagesInsertInternalLocationUsages(final InternalLocationUsages bean) {
		final String sql = getQuery("OIMULOCA_USAGES_INSERT_INTERNAL_LOCATION_USAGES");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<>();
		parameters.add(new BeanPropertySqlParameterSource(bean));
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			return e.getMessage().toUpperCase();
		}
		if (returnArray.length == 1) {
			return "1";
		} else {
			return null;
		}

	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstInternalLocationUsages List<InternalLocationUsages>
	 */
	public Integer usagesUpdateInternalLocationUsages(final InternalLocationUsages bean) {
		final String sql = getQuery("OIMULOCA_USAGES_UPDATE_INTERNAL_LOCATION_USAGES");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<>();
		parameters.add(new BeanPropertySqlParameterSource(bean));
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (returnArray.length == 1) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstIntLocUsageLocations List<IntLocUsageLocations>
	 */
	public Integer usagesPreUpdateQuery(final InternalLocationUsages intLocBean) {
		final String sql = getQuery("USAGES_PRE_UPDATE");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<>();
		parameters.add(new BeanPropertySqlParameterSource(intLocBean));
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (returnArray.length == 1) {
			return 1;
		} else {
			return 0;
		}
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao IntLocUsageLocations
	 *
	 * @return List<IntLocUsageLocations>
	 */
	public List<IntLocUsageLocations> intLocL1ExecuteQuery(final IntLocUsageLocations objSearchDao) {
		final String sql = getQuery("OIMULOCA_INTLOCL1_FIND_INT_LOC_USAGE_LOCATIONS");
		final RowMapper<IntLocUsageLocations> IntLocUsageLocationsRowMapper = Row2BeanRowMapper.makeMapping(sql,
				IntLocUsageLocations.class, intLocUsageLocationsMapping);
		String preparedSql = null;
		final MapSqlParameterSource inParameterSource = new MapSqlParameterSource();
		final StringBuffer sqlQuery = new StringBuffer();
		sqlQuery.append(sql);
		if (objSearchDao != null) {
			if (objSearchDao.getInternalLocationUsageId() != null) {
				sqlQuery.append(" INTERNAL_LOCATION_USAGE_ID = :INTERNAL_LOCATION_USAGE_ID"
						+ " AND PARENT_USAGE_LOCATION_ID IS NULL");
				inParameterSource.addValue("INTERNAL_LOCATION_USAGE_ID", objSearchDao.getInternalLocationUsageId());
			}
			if (objSearchDao.getParentUsageLocationId() != null) {
				sqlQuery.append(" PARENT_USAGE_LOCATION_ID = :PARENT_USAGE_LOCATION_ID" + " AND ");
				inParameterSource.addValue("PARENT_USAGE_LOCATION_ID", objSearchDao.getParentUsageLocationId());
			}
		}
		preparedSql = sqlQuery.toString().trim();
		if (preparedSql.endsWith("WHERE")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 5);
		}
		if (preparedSql.endsWith("AND")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 3);
		}
		preparedSql = preparedSql + " order by LIST_SEQ, TAG_INTERNAL_LOCATIONS_SORT_USAGE_LOCATIONS(INTERNAL_LOCATION_ID)"; // TAG_INTERNAL_LOCATIONS_SORT_USAGE_LOCATIONS(INTERNAL_LOCATION_ID)
		return namedParameterJdbcTemplate.query(preparedSql, inParameterSource, IntLocUsageLocationsRowMapper);
	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstIntLocUsageLocations List<IntLocUsageLocations>
	 */
	public Integer usagePreInsert(final InternalLocationUsages intLocBean) {
		final String sql = getQuery("USAGES_PRE_INSERT");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<>();
		parameters.add(new BeanPropertySqlParameterSource(intLocBean));
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (returnArray.length == 1) {
			return 1;
		} else {
			return 0;
		}
	}

	/**
	 * This method is used to insert the records in the data base tables based on
	 *
	 * @param lstIntLocUsageLocations List<IntLocUsageLocations>
	 *
	 * @return List<Integer>
	 */
	public String intLocL1InsertIntLocUsageLocations(final List<IntLocUsageLocations> listObj) {
		final String sql = getQuery("OIMULOCA_INTLOCL1_INSERT_INT_LOC_USAGE_LOCATIONS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<>();
		for (final IntLocUsageLocations bean : listObj) {
			parameters.add(new BeanPropertySqlParameterSource(bean));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			return e.getMessage().toUpperCase();
		}
		if (listObj.size() == returnArray.length) {
			return "1";
		} else {
			return null;
		}

	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstIntLocUsageLocations List<IntLocUsageLocations>
	 */
	public Integer intLocL1UpdateIntLocUsageLocations(final List<IntLocUsageLocations> listObj) {
		final String sql = getQuery("OIMULOCA_INTLOCL1_UPDATE_INT_LOC_USAGE_LOCATIONS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<>();
		for (final IntLocUsageLocations bean : listObj) {
			parameters.add(new BeanPropertySqlParameterSource(bean));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (listObj.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to delete records from data base tables based on
	 *
	 * @param lstIntLocUsageLocations List<IntLocUsageLocations>
	 */
	public Integer intLocL1DeleteIntLocUsageLocations(final List<IntLocUsageLocations> listObj) {
		final String sql = getQuery("OIMULOCA_INTLOCL1_DELETE_INT_LOC_USAGE_LOCATIONS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<>();
		for (final IntLocUsageLocations bean : listObj) {
			parameters.add(new BeanPropertySqlParameterSource(bean));
		}
		try {
			batchUpdatePreDeletedRows("INT_LOC_USAGE_LOCATIONS", "USAGE_LOCATION_ID = :usageLocationId", parameters);
		} catch (Exception e) {
			logger.error("batchUpdatePreDeletedRows in intLocL1DeleteIntLocUsageLocations"+e);
		}
		
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (listObj.size() == returnArray.length) {
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
	public List<ReferenceCodes> rgLocationUsageRecordGroup() {
		final String sql = getQuery("OIMULOCA_FIND_RGLOCATIONUSAGE");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				referenceCodesMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("Exception In rgLocationUsageRecordGroup:", e);
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<AgencyLocations>
	 */
	public List<AgencyLocations> rgAgyLocRecordGroup(final String caseLoadId) {
		final String sql = getQuery("OIMULOCA_FIND_RGAGYLOC");
		final RowMapper<AgencyLocations> mRowMapper = Row2BeanRowMapper.makeMapping(sql, AgencyLocations.class,
				agyLocMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams("CASELOADID", caseLoadId), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("Exception In rgLocationUsageRecordGroup:", e);
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<ReferenceCodes>
	 */
	public List<ReferenceCodes> rgLevelTypeRecordGroup() {
		final String sql = getQuery("OIMULOCA_FIND_RGLEVELTYPE");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				referenceCodesMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("Exception In rgLocationUsageRecordGroup:", e);
			return Collections.emptyList();
		}
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * usagesPostQuery
	 *
	 * @param params
	 *
	 */
	public ReferenceCodes usagesPostQuery(final InternalLocationUsages paramBean) {
		final String sql = getQuery("OIMULOCA_USAGES_POSTQUERY");
		final RowMapper<ReferenceCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				referenceCodesMapping);
		return namedParameterJdbcTemplate.queryForObject(sql,
				createParams("INTERNALLOCATIONUSAGE", paramBean.getInternalLocationUsage()), columnRowMapper);
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * usagesOnCheckDeleteMaster
	 *
	 * @param params
	 *
	 */
	public List<IntLocUsageLocations> usagesOnCheckDeleteMaster(final IntLocUsageLocations paramBean) {
		final String sql = getQuery("OIMULOCA_USAGES_ONCHECKDELETEMASTER");
		final RowMapper<IntLocUsageLocations> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				IntLocUsageLocations.class, intLocUsageLocationsMapping);
		return namedParameterJdbcTemplate.query(sql, createParams(), columnRowMapper);
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * usagesPreUpdate
	 *
	 * @param params
	 *
	 */
	public ReferenceCodes usagesPreUpdate(final InternalLocationUsages paramBean) {
		final String sql = getQuery("OIMULOCA_USAGES_PREUPDATE");
		final RowMapper<ReferenceCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				referenceCodesMapping);
		ReferenceCodes returnObj = new ReferenceCodes();
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("INTERNALLOCATIONUSAGE", paramBean.getInternalLocationUsage()), columnRowMapper);
		} catch (Exception e) {
			returnObj.setCode("N");
			return returnObj;
		}
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * intLocL1OnCheckDeleteMaster
	 *
	 * @param params
	 *
	 */
	public Integer intLocL1OnCheckDeleteMaster(final List<Integer> usgLocId) {
		final String sql = getQuery("OIMULOCA_INT_LOC_L1_ONCHECKDELETEMASTER");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("USAGELOCATIONID", usgLocId), Integer.class);
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * intLocL2OnCheckDeleteMaster
	 *
	 * @param params
	 *
	 */
	public List<IntLocUsageLocations> intLocL2OnCheckDeleteMaster(final IntLocUsageLocations paramBean) {
		final String sql = getQuery("OIMULOCA_INT_LOC_L2_ONCHECKDELETEMASTER");
		final RowMapper<IntLocUsageLocations> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				IntLocUsageLocations.class, intLocUsageLocationsMapping);
		return namedParameterJdbcTemplate.query(sql, createParams(), columnRowMapper);
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * intLocL3OnCheckDeleteMaster
	 *
	 * @param params
	 *
	 */
	public List<IntLocUsageLocations> intLocL3OnCheckDeleteMaster(final IntLocUsageLocations paramBean) {
		final String sql = getQuery("OIMULOCA_INT_LOC_L3_ONCHECKDELETEMASTER");
		final RowMapper<IntLocUsageLocations> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				IntLocUsageLocations.class, intLocUsageLocationsMapping);
		return namedParameterJdbcTemplate.query(sql, createParams(), columnRowMapper);
	}

	/**
	 * This method will update the transaction in the table
	 * 
	 * @return String
	 */

	/**
	 * This method will update the transaction in the table
	 * 
	 * @return String
	 */
	public AgencyInternalLocations getInternalLocationRecords(final Integer intLocId) {
		final String sql = getQuery("AGY_INT_LOC_POST_QUERY");
		AgencyInternalLocations retObj = null;
		final RowMapper<AgencyInternalLocations> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				AgencyInternalLocations.class, agencyInternalLocationsMapping);
		try {
			retObj = namedParameterJdbcTemplate.queryForObject(sql, createParams("P_INTERNAL_LOCATION_ID", intLocId),
					columnRowMapper);
		} catch (Exception e) {
			logger.error("getInternalLocationRecords", e);
		}
		return retObj;
	}

	public List<AgencyInternalLocations> intLocOneLov(final String agylocId) {
		final String sql = getQuery("OIMULOCA_INTLOC_ONE_GETVALUES");
		final RowMapper<AgencyInternalLocations> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				AgencyInternalLocations.class, agencyInternalLocationsMapping);
		return namedParameterJdbcTemplate.query(sql, createParams("agy_loc_id", agylocId), columnRowMapper);
	}

	public List<AgencyInternalLocations> intLocTwoLov(final Integer internalLocationId, final String agylocId) {
		final String sql = getQuery("OIMULOCA_INTLOC_TWO_GETVALUES");
		final RowMapper<AgencyInternalLocations> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				AgencyInternalLocations.class, agencyInternalLocationsMapping);
		return namedParameterJdbcTemplate.query(sql,
				createParams("internal_location_id", internalLocationId, "agy_loc_id", agylocId), columnRowMapper);
	}

}

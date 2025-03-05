package net.syscon.s4.inst.property.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.AgencyInternalLocations;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.VHeaderBlock;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.PropertyStorages;
import net.syscon.s4.im.beans.SysDual;
import net.syscon.s4.inst.property.OiipclocRepository;
import net.syscon.s4.inst.property.bean.OffenderPptyContainers;

/**
 * Class OiipclocRepositoryImpl
 */
@Repository
public class OiipclocRepositoryImpl extends RepositoryBase implements OiipclocRepository {

	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OiipclocRepositoryImpl.class.getName());

	private final Map<String, FieldMapper> propertyStoragesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("PROPERTY_STORAGE_ID", new FieldMapper("propertyStorageId"))
			.put("USER_DESC", new FieldMapper("userDesc")).build();
	private final Map<String, FieldMapper> referenceCodesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CODE", new FieldMapper("code")).put("DESCRIPTION", new FieldMapper("description")).build();

	private final Map<String, FieldMapper> vHeaderBlockMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("OFFENDER_ID_DISPLAY", new FieldMapper("offenderIdDisplay"))
			.put("AGY_LOC_ID", new FieldMapper("agyLocId")).put("LAST_NAME", new FieldMapper("lastName"))
			.put("FIRST_NAME", new FieldMapper("firstName")).put("OFFENDER_BOOK_ID", new FieldMapper("offenderBookId"))
			.build();
	private final Map<String, FieldMapper> agencyInternalLocationsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("INTERNAL_LOCATION_CODE", new FieldMapper("internalLocationCode"))
			.put("DESCRIPTIO", new FieldMapper("descriptio"))
			.put("INTERNAL_LOCATION_ID", new FieldMapper("internalLocationId"))
			.put("errorMessage", new FieldMapper("errorMessage"))
			.put("ACTIVE_FLAG", new FieldMapper("activeFlag"))
			.put("canDisplay", new FieldMapper("canDisplay")).build();
	private final Map<String, FieldMapper> offenderPptyContainersMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("OFFENDER_BOOK_ID", new FieldMapper("offenderBookId"))
			.put("COMMENT_TEXT", new FieldMapper("commentText")).put("CREATE_USER_ID", new FieldMapper("createUserId"))
			.put("PROPERTY_ONLY_FLAG", new FieldMapper("propertyOnlyFlag"))
			.put("ACTIVE_FLAG", new FieldMapper("activeFlag")).put("MODIFY_USER_ID", new FieldMapper("modifyUserId"))
			.put("CONTAINER_CODE", new FieldMapper("containerCode"))
			.put("PROPERTY_CONTAINER_ID", new FieldMapper("propertyContainerId"))
			.put("SEAL_MARK", new FieldMapper("sealMark")).put("DISPOSED_TO", new FieldMapper("disposedTo"))
			.put("TRN_TO_AGY_LOC_ID", new FieldMapper("trnToAgyLocId")).put("SEAL_FLAG", new FieldMapper("sealFlag"))
			.put("AGY_LOC_ID", new FieldMapper("agyLocId")).put("CREATE_DATETIME", new FieldMapper("createDateTime"))
			.put("INTERNAL_LOCATION_ID", new FieldMapper("internalLocationId"))
			.put("MODIFY_DATETIME", new FieldMapper("modifyDateTime"))
			.put("DISPOSED_TO_NAME", new FieldMapper("disposedToName"))
			.put("PROPERTY_STORAGE_ID", new FieldMapper("propertyStorageId"))
			.put("PROPOSED_DISPOSAL_DATE", new FieldMapper("proposedDisposalDate"))
			.put("EXPIRY_DATE", new FieldMapper("expiryDate"))
			.put("TRN_FROM_AGY_LOC_ID", new FieldMapper("trnFromAgyLocId")).build();

	/**
	 * Creates new OiipclocRepositoryImpl class Object
	 */
	public OiipclocRepositoryImpl() {
		// OiipclocRepositoryImpl
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao OffenderPptyContainers
	 *
	 * @return List<OffenderPptyContainers>
	 *
	 * @
	 */
	public List<OffenderPptyContainers> offConExecuteQuery(final OffenderPptyContainers objSearchDao) {
		final String sql = getQuery("OIIPCLOC_OFFCON_FIND_OFFENDER_PPTY_CONTAINERS");
		String preparedSql = null;
		final StringBuffer sqlQuery = new StringBuffer();
		final MapSqlParameterSource params = new MapSqlParameterSource();
		sqlQuery.append(sql);
		if (objSearchDao != null) {
			sqlQuery.append(" where ");
			if (objSearchDao.getContainerCode() != null) {
				sqlQuery.append(" CONTAINER_CODE =:containerCode  " + " and");
				params.addValue("containerCode", objSearchDao.getContainerCode());
			}
			if (objSearchDao.getProposedDisposalDate() != null) {
				sqlQuery.append(" PROPOSED_DISPOSAL_DATE =:proposedDisposalDate " + " and");
				params.addValue("proposedDisposalDate", objSearchDao.getProposedDisposalDate());
			}
			if (objSearchDao.getExpiryDate() != null) {
				sqlQuery.append(" EXPIRY_DATE = :expiryDate " + " and");
				params.addValue("expiryDate", objSearchDao.getExpiryDate());
			}
			if (objSearchDao.getSealMark() != null) {
				sqlQuery.append(" SEAL_MARK = :sealMark " + " and");
				params.addValue("sealMark", objSearchDao.getSealMark());
			}
			if (objSearchDao.getInternalLocationId() != null) {
				sqlQuery.append(" INTERNAL_LOCATION_ID = :internalLocationId " + " and");
				params.addValue("internalLocationId", objSearchDao.getInternalLocationId());
			}
			if (objSearchDao.getActiveFlag() != null) {
				sqlQuery.append(" ACTIVE_FLAG = :activeFlag " + " and");
				params.addValue("activeFlag", objSearchDao.getActiveFlag());
			}
		}
		preparedSql = sqlQuery.toString().trim();
		if (preparedSql.endsWith("where")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 5);
		}
		if (preparedSql.endsWith("and")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 3);
		}
		if (objSearchDao != null) {
			if (objSearchDao.getInternalLocationId() != null && objSearchDao.getContainerCode() == null
					&& objSearchDao.getProposedDisposalDate() == null && objSearchDao.getExpiryDate() == null
					&& objSearchDao.getSealMark() == null) {
				preparedSql = preparedSql + " ORDER BY PROPERTY_CONTAINER_ID";
			} else if (objSearchDao.getContainerCode() != null || objSearchDao.getProposedDisposalDate() != null
					|| objSearchDao.getExpiryDate() != null || objSearchDao.getSealMark() != null
					|| objSearchDao.getActiveFlag() != null) {
				preparedSql = preparedSql
						+ " AND AGY_LOC_ID IN (  SELECT AGY_LOC_ID   FROM CASELOAD_AGENCY_LOCATIONS   WHERE CASELOAD_ID = (SELECT WORKING_CASELOAD_ID      FROM STAFF_MEMBERS  WHERE USER_ID =:USER_ID)) AND AGY_LOC_ID NOT IN ('OUT', 'TRN')  ORDER BY PROPERTY_CONTAINER_ID";
				params.addValue("USER_ID", objSearchDao.getCreateUserId());
			} else {
				preparedSql = preparedSql
						+ " WHERE  AGY_LOC_ID IN (  SELECT AGY_LOC_ID   FROM CASELOAD_AGENCY_LOCATIONS   WHERE CASELOAD_ID = (SELECT WORKING_CASELOAD_ID      FROM STAFF_MEMBERS  WHERE USER_ID =:USER_ID)) AND AGY_LOC_ID NOT IN ('OUT', 'TRN')  ORDER BY PROPERTY_CONTAINER_ID";
				params.addValue("USER_ID", objSearchDao.getCreateUserId());
			}
		} else {
			preparedSql = preparedSql
					+ " WHERE  AGY_LOC_ID IN (  SELECT AGY_LOC_ID   FROM CASELOAD_AGENCY_LOCATIONS   WHERE CASELOAD_ID = (SELECT WORKING_CASELOAD_ID      FROM STAFF_MEMBERS  WHERE USER_ID =:USER_ID)) AND AGY_LOC_ID NOT IN ('OUT', 'TRN')  ORDER BY PROPERTY_CONTAINER_ID";
			params.addValue("USER_ID", objSearchDao.getCreateUserId());
		}
		final RowMapper<OffenderPptyContainers> OffenderPptyContainersRowMapper = Row2BeanRowMapper
				.makeMapping(preparedSql, OffenderPptyContainers.class, offenderPptyContainersMapping);
		final ArrayList<OffenderPptyContainers> returnList = (ArrayList<OffenderPptyContainers>) namedParameterJdbcTemplate
				.query(preparedSql, params, OffenderPptyContainersRowMapper);
		return returnList;
	}

	/**
	 * @param
	 *
	 * @
	 *
	 */
	public int preInsert() {
		return 0;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<ReferenceCodes>
	 */
	public List<ReferenceCodes> rgContainerCodeRecordGroup() {
		final String sql = getQuery("OIIPCLOC_FIND_RGCONTAINERCODE");
		final RowMapper<ReferenceCodes> referenceCodesRowMapper = Row2BeanRowMapper.makeMapping(sql,
				ReferenceCodes.class, referenceCodesMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), referenceCodesRowMapper);
		} catch (Exception e) {
			logger.error("In method rgContainerCodeRecordGroup" + e);
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<ReferenceCodesIntLocUsageLocationsInternalLocationUsages>
	 */
	public List<AgencyInternalLocations> rgDescriptionRecordGroup(final String caseloadId) {
		final String sql = getQuery("OIIPCLOC_FIND_RGDESCRIPTION");
		final RowMapper<AgencyInternalLocations> agyInternalLocationUsagesRowMapper = Row2BeanRowMapper.makeMapping(sql,
				AgencyInternalLocations.class, agencyInternalLocationsMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams("CASELOADID", caseloadId),
					agyInternalLocationUsagesRowMapper);
		} catch (Exception e) {
			logger.error("In method rgDescriptionRecordGroup" + e);
			return Collections.emptyList();
		}
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfkchkOffConOffConVPhe
	 *
	 * @param params
	 *
	 */
	public VHeaderBlock cgfkchkOffConOffConVPhe(final VHeaderBlock paramBean) {
		final String sql = getQuery("OIIPCLOC_CGFKCHK_OFF_CON_OFF_CON_V_PHE");
		final RowMapper<VHeaderBlock> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, VHeaderBlock.class,
				vHeaderBlockMapping);
		final VHeaderBlock returnList = namedParameterJdbcTemplate.queryForObject(sql,
				createParams("OFFENDERBOOKID", paramBean.getOffenderBookId(),"USERID",paramBean.getCreateUserId()), columnRowMapper);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfklkpOffConOffConPpty
	 *
	 * @param params
	 *
	 */
	public List<PropertyStorages> cgfklkpOffConOffConPpty(final PropertyStorages paramBean) {
		final String sql = getQuery("OIIPCLOC_CGFKLKP_OFF_CON_OFF_CON_PPTY_");
		final RowMapper<PropertyStorages> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, PropertyStorages.class,
				propertyStoragesMapping);
		final ArrayList<PropertyStorages> returnList = (ArrayList<PropertyStorages>) namedParameterJdbcTemplate
				.query(sql, createParams(), columnRowMapper);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgwhenNewFormInstance
	 *
	 * @param params
	 *
	 */
	public List<Object> cgwhenNewFormInstance(final SysDual paramBean) {
		final String sql = getQuery("OIIPCLOC_CGWHEN_NEW_FORM_INSTANCE");
		final ArrayList<Object> returnList = (ArrayList<Object>) namedParameterJdbcTemplate.queryForObject(sql,
				createParams(), Object.class);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfkchkOffConOffConPpty
	 *
	 * @param params
	 *
	 */
	public List<AgencyInternalLocations> cgfkchkOffConOffConPpty(final AgencyInternalLocations paramBean) {
		final String sql = getQuery("OIIPCLOC_CGFKCHK_OFF_CON_OFF_CON_PPTY_");
		final RowMapper<AgencyInternalLocations> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				AgencyInternalLocations.class, agencyInternalLocationsMapping);
		final ArrayList<AgencyInternalLocations> returnList = (ArrayList<AgencyInternalLocations>) namedParameterJdbcTemplate
				.query(sql, createParams(), columnRowMapper);
		return returnList;
	}

}

package net.syscon.s4.inst.property.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.AgencyInternalLocations;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.Dual;
import net.syscon.s4.inst.property.OidtpconRepository;
import net.syscon.s4.inst.property.bean.OffenderPptyContainers;
import net.syscon.s4.inst.property.bean.OffenderPptyItems;
import net.syscon.s4.inst.property.bean.PropertyStorages;
import oracle.jdbc.OracleTypes;

/**
 * Class OidtpconRepositoryImpl
 */
@Repository
public class OidtpconRepositoryImpl extends RepositoryBase implements OidtpconRepository {

	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OidtpconRepositoryImpl.class.getName());

	private final Map<String, FieldMapper> propStoMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("PROPERTY_STORAGE_ID", new FieldMapper("propertyStorageId"))
			.put("DESCRIPTION", new FieldMapper("description")).build();

	private final Map<String, FieldMapper> ageIntLocMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DESCRIPTION", new FieldMapper("description"))
			.put("INTERNAL_LOCATION_ID", new FieldMapper("internalLocationId")).build();

	private final Map<String, FieldMapper> refCodMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("LIST_SEQ", new FieldMapper("listSeq")).put("DESCRIPTION", new FieldMapper("description"))
			.put("MODE", new FieldMapper("mode")).put("CONTAINER_CODE", new FieldMapper("containerCode")).build();

	private final Map<String, FieldMapper> offPptyConMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("SEAL_FLAG", new FieldMapper("sealFlag")).put("AGY_LOC_ID", new FieldMapper("agyLocId"))
			.put("CREATE_DATETIME", new FieldMapper("createDateTime"))
			.put("INTERNAL_LOCATION_ID", new FieldMapper("internalLocationId"))
			.put("MODIFY_DATETIME", new FieldMapper("modifyDateTime"))
			.put("DISPOSED_TO_NAME", new FieldMapper("disposedToName"))
			.put("PROPERTY_STORAGE_ID", new FieldMapper("propertyStorageId"))
			.put("PROPOSED_DISPOSAL_DATE", new FieldMapper("proposedDisposalDate"))
			.put("EXPIRY_DATE", new FieldMapper("expiryDate"))
			.put("TRN_FROM_AGY_LOC_ID", new FieldMapper("trnFromAgyLocId"))
			.put("TRN_TO_AGY_LOC_ID", new FieldMapper("trnToAgyLocId")).build();

	private final Map<String, FieldMapper> sysProfMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("PROFILE_CODE", new FieldMapper("profileCode")).put("PROFILE_TYPE", new FieldMapper("profileType"))
			.put("CREATE_USER_ID", new FieldMapper("createUserId")).put("SEAL_FLAG", new FieldMapper("sealFlag"))
			.put("CREATE_DATETIME", new FieldMapper("createDatetime"))
			.put("MODIFY_USER_ID", new FieldMapper("modifyUserId"))
			.put("OLD_TABLE_NAME", new FieldMapper("oldTableName"))
			.put("PROFILE_VALUE", new FieldMapper("profileValue"))
			.put("MODIFY_DATETIME", new FieldMapper("modifyDatetime"))
			.put("PROFILE_VALUE_2", new FieldMapper("profileValue2")).put("DESCRIPTION", new FieldMapper("description"))
			.build();

	private final Map<String, FieldMapper> ageLocMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("LIST_SEQ", new FieldMapper("listSeq")).put("PPTY_AGY_LOC_ID", new FieldMapper("pptyAgyLocId"))
			.put("AGY_LOC_ID", new FieldMapper("agyLocId")).put("TRN_TO_AGY_LOC_ID", new FieldMapper("trnToAgyLocId"))
			.put("DESCRIPTION", new FieldMapper("description"))
			.put("ACTIVE_FLAG", new FieldMapper("activeFlag")).build();

	private final Map<String, FieldMapper> dualMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("USER", new FieldMapper("user")).put("SYSDATE", new FieldMapper("sysDate")).build();

	/**
	 * Creates new OidtpconRepositoryImpl class Object
	 */
	public OidtpconRepositoryImpl() {
		// OidtpconRepositoryImpl
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao OffenderPptyContainers
	 *
	 * @return List<OffenderPptyContainers>
	 *
	 */
	public List<OffenderPptyContainers> offConExecuteQuery(final OffenderPptyContainers objSearchDao) {
		final String sql = getQuery("OIDTPCON_OFFCON_FIND_OFFENDER_PPTY_CONTAINERS");
		final RowMapper<OffenderPptyContainers> offrPpConRowMap = Row2BeanRowMapper.makeMapping(sql,
				OffenderPptyContainers.class, offPptyConMapping);
		ArrayList<OffenderPptyContainers> returnListTem = new ArrayList<>();
		ArrayList<OffenderPptyContainers> returnList = (ArrayList<OffenderPptyContainers>) namedParameterJdbcTemplate
				.query(sql, createParams("OFFENDERBOOKID", objSearchDao.getOffenderBookId(), "caseLoadId",
						objSearchDao.getAgyLocId()), offrPpConRowMap);
		for (OffenderPptyContainers offenderPptyContainers : returnList) {
			if (offenderPptyContainers.getPropertyContainerId() != null) {
				returnListTem.add(offenderPptyContainers);
			}
		}
		return returnListTem;
	}

	/**
	 * @param
	 *
	 *
	 */
	public int preInsert() {
		return 0;
	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstOffenderPptyContainers List<OffenderPptyContainers>
	 *
	 */
	public Integer offConUpdateOffenderPptyContainers(final List<OffenderPptyContainers> offderPptyCon) {
		final String sql = getQuery("OIDTPCON_OFFCON_UPDATE_OFFENDER_PPTY_CONTAINERS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffenderPptyContainers obj : offderPptyCon) {
			parameters.add(new BeanPropertySqlParameterSource(obj));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (offderPptyCon.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao SystemProfiles
	 *
	 * @return List<SystemProfiles>
	 *
	 */
	public List<SystemProfiles> sysPflExecuteQuery(final SystemProfiles objSearchDao) {
		final String sql = getQuery("OIDTPCON_SYSPFL_FIND_SYSTEM_PROFILES");
		final RowMapper<SystemProfiles> sysProfRowMapper = Row2BeanRowMapper.makeMapping(sql, SystemProfiles.class,
				sysProfMapping);
		final ArrayList<SystemProfiles> returnList = (ArrayList<SystemProfiles>) namedParameterJdbcTemplate.query(sql,
				createParams("PROFILETYPE", objSearchDao.getProfileType(), "PROFILECODE",
						objSearchDao.getProfileCode()),
				sysProfRowMapper);
		return returnList;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<AgencyLocations>
	 */
	public List<AgencyLocations> cgfkOffConTrnToAgyLocIdRecordGroup(final String agyLocId) {
		final StringBuffer sqlQuery = new StringBuffer();
		if (agyLocId == null) {
			sqlQuery.append(getQuery("OIDTPCON_FIND_RGSELECTALL"));
		} else {
			sqlQuery.append(getQuery("OIDTPCON_FIND_CGFKOFFCONTRNTOAGYLOCID"));
		}
		final String sql = sqlQuery.toString().trim();
		List<AgencyLocations> returnList = new ArrayList<>();
		final RowMapper<AgencyLocations> ageLocRowMapper = Row2BeanRowMapper.makeMapping(sql, AgencyLocations.class,
				ageLocMapping);
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams("AGYLOCID", agyLocId), ageLocRowMapper);
		} catch (Exception e) {
			logger.error(e);
		}
		return returnList;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<AgencyLocations>
	 */
	public List<AgencyLocations> rgSelectAllRecordGroup() {
		final String sql = getQuery("OIDTPCON_FIND_RGSELECTALL");
		List<AgencyLocations> returnList = new ArrayList<>();
		final RowMapper<AgencyLocations> ageLocRowMapper = Row2BeanRowMapper.makeMapping(sql, AgencyLocations.class,
				ageLocMapping);
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams(), ageLocRowMapper);
		} catch (Exception e) {
			logger.error(e);
		}
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * vPheadOnCheckDeleteMaster
	 *
	 * @param params
	 *
	 */
	public List<Object> vPheadOnCheckDeleteMaster(final OffenderPptyContainers paramBean) {
		final String sql = getQuery("OIDTPCON_V_PHEAD_ONCHECKDELETEMASTER");
		List<Object> returnList;
		returnList = (List<Object>) namedParameterJdbcTemplate.queryForList(sql,
				createParams("OFFENDERBOOKID", paramBean.getOffenderBookId()), Object.class);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * offConPostQuery
	 *
	 * @param params
	 *
	 */
	public AgencyInternalLocations offConPostQuery(final AgencyInternalLocations paramBean) {
		final String sql = getQuery("OIDTPCON_OFF_CON_POSTQUERY");
		final RowMapper<AgencyInternalLocations> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				AgencyInternalLocations.class, ageIntLocMapping);
		final AgencyInternalLocations returnObj = namedParameterJdbcTemplate.queryForObject(sql,
				createParams("INTERNALLOCATIONID", paramBean.getInternalLocationId()), columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * offConWhenNewRecordInstance
	 *
	 * @param params
	 *
	 */
	public String offConWhenNewRecordInstance(final Integer intLocId) {
		final String sql = getQuery("OIDTPCON_OFF_CON_WHENNEWRECORDINSTANCE");
		String returnObj = null;
		List<String> returnList = new ArrayList<String>();
		returnList = namedParameterJdbcTemplate.queryForList(sql, createParams("INTERNALLOCATIONID", intLocId),
				String.class);
		if (!returnList.isEmpty()) {
			returnObj = returnList.get(0);
		} else {
			returnObj = null;
		}
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfkchkOffConOffConRef
	 *
	 * @param params
	 *
	 */
	public ReferenceCodes cgfkchkOffConOffConRef(final ReferenceCodes paramBean) {
		final String sql = getQuery("OIDTPCON_CGFKCHK_OFF_CON_OFF_CON_REF_C");
		ReferenceCodes returnObj = null;
		final RowMapper<ReferenceCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				refCodMapping);
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("CONTAINERCODE", paramBean.getCode()), columnRowMapper);
		} catch (EmptyResultDataAccessException e) {

		}
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfkchkOffConOffConAgy
	 *
	 * @param params
	 *
	 */
	public AgencyLocations cgfkchkOffConOffConAgy(final AgencyLocations paramBean) {
		final String sql = getQuery("OIDTPCON_CGFKCHK_OFF_CON_OFF_CON_AGY_L");
		final RowMapper<AgencyLocations> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, AgencyLocations.class,
				ageLocMapping);
		final AgencyLocations returnObj = namedParameterJdbcTemplate.queryForObject(sql,
				createParams("TRNTOAGYLOCID", paramBean.getAgyLocId(), "PPTYAGYLOCID", paramBean.getAgyLocId()),
				columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfkchkOffConOffConPpty
	 *
	 * @param params
	 *
	 */
	public PropertyStorages cgfkchkOffConOffConPpty(final PropertyStorages paramBean) {
		final String sql = getQuery("OIDTPCON_CGFKCHK_OFF_CON_OFF_CON_PPTY");
		final RowMapper<PropertyStorages> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, PropertyStorages.class,
				propStoMapping);
		final PropertyStorages returnObj = namedParameterJdbcTemplate.queryForObject(sql,
				createParams("PROPERTYSTORAGEID", paramBean.getPropertyStorageCode()), columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgwhenNewFormInstance
	 *
	 * @param params
	 *
	 */
	public Dual cgwhenNewFormInstance(final String user) {
		final String sql = getQuery("OIDTPCON_CGWHEN_NEW_FORM_INSTANCE");
		final RowMapper<Dual> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, Dual.class, dualMapping);
		final Dual returnList = (Dual) namedParameterJdbcTemplate.queryForObject(sql, createParams("userName", user),
				columnRowMapper);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * getItemStatus
	 *
	 * @param paramBean
	 *
	 */
	public List<OffenderPptyItems> getItemStatus(final OffenderPptyItems paramBean) {
		final String sql = getQuery("OIDTPCON_GET_ITEM_STATUS");
		List<OffenderPptyItems> returnList;
		returnList =  namedParameterJdbcTemplate.query(sql,
				createParams("OFFENDERBOOKID", paramBean.getOffenderBookId(), "AGYLOCID", paramBean.getAgyLocId()),
				BeanPropertyRowMapper.newInstance(OffenderPptyItems.class));
		return returnList;
	}

	/**
	 * Method is used to update the offender_ppty_items table by using procedure.
	 */
	public Integer postUpdateOfOffenderPptyItems(final OffenderPptyContainers paramBean) {
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[3];
		sqlParameters = new SqlParameter[] { new SqlParameter("P_PROPERTY_CONTAINER_ID", OracleTypes.NUMBER),
				new SqlParameter("P_STATUS_CODE", OracleTypes.VARCHAR),
				new SqlParameter("P_AGY_LOC_ID", OracleTypes.VARCHAR) };
		final SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("OMS_PROPERTY").withProcedureName("UPDATE_TRANSFERRED_ITEMS")
				.declareParameters(sqlParameters);
		inParamMap.put("P_PROPERTY_CONTAINER_ID", paramBean.getPropertyContainerId());
		inParamMap.put("P_STATUS_CODE", "TRANSIT");
		inParamMap.put("P_AGY_LOC_ID", "OUT");
		final SqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		try {
			simpleJDBCCall.execute(inParameter);
		} catch (Exception e) {
			return 0;
		}
		return 1;
	}

	@Override
	public OffenderPptyItems gettingOldOffenderPptyItemsData(Integer propertyContainerId) {
		final String sql = getQuery("GETTING_OLD_OFFENDER_PPT_YITE_MSDATA1");
		OffenderPptyItems bean = new OffenderPptyItems();
		try {
			bean = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("property_container_id", propertyContainerId),
					new BeanPropertyRowMapper<OffenderPptyItems>(OffenderPptyItems.class));
		} catch (Exception e) {
			logger.error("gettingOldOffenderPptyItemsData :", e);
		}
		return bean;
	}
	
	@Override
	public List<OffenderPptyContainers> offPenPropConExecuteQuery(OffenderPptyContainers objSearchDao) {
		final String sql = getQuery("GET_OFF_PEN_PROP_CONTAINER_DETAils");
		final RowMapper<OffenderPptyContainers> offrPpConRowMap = Row2BeanRowMapper.makeMapping(sql,
				OffenderPptyContainers.class, offPptyConMapping);
		ArrayList<OffenderPptyContainers> returnListTem = new ArrayList<>();
		ArrayList<OffenderPptyContainers> returnList = (ArrayList<OffenderPptyContainers>) namedParameterJdbcTemplate
				.query(sql, createParams("OFFENDERBOOKID", objSearchDao.getOffenderBookId(), "caseLoadId",
						objSearchDao.getAgyLocId()), offrPpConRowMap);
		for (OffenderPptyContainers offenderPptyContainers : returnList) {
			if (offenderPptyContainers.getPropertyContainerId() != null) {
				returnListTem.add(offenderPptyContainers);
			}
		}
		return returnListTem;
	}
}

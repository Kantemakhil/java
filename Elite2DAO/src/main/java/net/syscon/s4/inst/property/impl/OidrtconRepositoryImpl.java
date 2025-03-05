package net.syscon.s4.inst.property.impl;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.OffenderBookings;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.SysDual;
import net.syscon.s4.inst.property.OidrtconRepository;
import net.syscon.s4.inst.property.bean.OffenderPptyContainers;
import net.syscon.s4.inst.property.bean.OffenderPptyItems;
import oracle.jdbc.OracleTypes;

/**
 * Class OidrtconRepositoryImpl
 */
@Repository
public class OidrtconRepositoryImpl extends RepositoryBase implements OidrtconRepository {

	private static Logger logger = LogManager.getLogger(OidrtconRepositoryImpl.class);

	private final Map<String, FieldMapper> referenceCodesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CODE", new FieldMapper("code")).put("DESCRIPTION", new FieldMapper("description"))
			.put("DSP_DESCRIPTION", new FieldMapper("dspDescription")).put("MODE", new FieldMapper("mode"))
			.put("CONTAINER_CODE", new FieldMapper("containerCode")).build();
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

	private final Map<String, FieldMapper> sysDualMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("SYSDATE", new FieldMapper("sysDate")).put("USER", new FieldMapper("user")).build();

	private final Map<String, FieldMapper> caseloadsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CASELOAD_ID", new FieldMapper("caseloadId")).put("DESCRIPTION", new FieldMapper("description"))
			.put("CASELOAD_TYPE", new FieldMapper("caseloadType")).put("LIST_SEQ", new FieldMapper("listSeq"))
			.put("TRUST_ACCOUNTS_FLAG", new FieldMapper("trustAccountsFlag"))
			.put("ACCESS_PROPERTY_FLAG", new FieldMapper("acessPropertyFlag"))
			.put("TRUST_CASELOAD_ID", new FieldMapper("trustCaseLoadId"))
			.put("PAYROLL_FLAG", new FieldMapper("payrollFlag")).put("ACTIVE_FLAG", new FieldMapper("activeFlag"))
			.put("DEACTIVATION_DATE", new FieldMapper("deactivationDate"))
			.put("COMMISSARY_FLAG", new FieldMapper("comissaryFlag"))
			.put("PAYROLL_TRUST_CASELOAD", new FieldMapper("payrollTrustCaseLoad"))
			.put("COMMISSARY_TRUST_CASELOAD", new FieldMapper("commissaryTrustCaseLoad"))
			.put("TRUST_COMMISSARY_CASELOAD", new FieldMapper("trustCommissaryCaseLoad"))
			.put("COMMUNITY_TRUST_CASELOAD", new FieldMapper("communityTrustCaseLoad"))
			.put("MDT_FLAG", new FieldMapper("mdtFlag")).put("CREATE_DATETIME", new FieldMapper("createDateTime"))
			.put("CREATE_USER_ID", new FieldMapper("createuserId"))
			.put("MODIFY_DATETIME", new FieldMapper("modifyDateTime"))
			.put("MODIFY_USER_ID", new FieldMapper("modifyUserId")).put("BILLING_FLAG", new FieldMapper("billingFlag"))
			.put("SEAL_FLAG", new FieldMapper("sealFlag")).build();

	private final Map<String, FieldMapper> agylocMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DESCRIPTION", new FieldMapper("description"))
			.put("AGY_LOC_ID", new FieldMapper("agyLocId"))
			.put("ACTIVE_FLAG", new FieldMapper("activeFlag")).build();

	/**
	 * Creates new OidrtconRepositoryImpl class Object
	 */
	public OidrtconRepositoryImpl() {
		// OidrtconRepositoryImpl
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
		final String sql = getQuery("OIDRTCON_OFFCON_FIND_OFFENDER_PPTY_CONTAINERS");
		final RowMapper<OffenderPptyContainers> offconPptyMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderPptyContainers.class, offenderPptyContainersMapping);
		final List<OffenderPptyContainers> returnList = namedParameterJdbcTemplate.query(sql,
				createParams("USER_id", objSearchDao.getCreateUserId()), offconPptyMapper);
		return returnList;
	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstOffenderPptyContainers List<OffenderPptyContainers>
	 *
	 * @throws SQLException
	 */
	public Integer offConUpdateOffenderPptyContainers(final List<OffenderPptyContainers> list) {
		final String sql = getQuery("OIDRTCON_OFFCON_UPDATE_OFFENDER_PPTY_CONTAINERS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffenderPptyContainers obj : list) {
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
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfkchkOffConOffConRef
	 *
	 * @param params
	 *
	 */
	public List<ReferenceCodes> cgfkchkOffConOffConRef(final ReferenceCodes paramBean) {
		final String sql = getQuery("OIDRTCON_CGFKCHK_OFF_CON_OFF_CON_REF_C");
		List<ReferenceCodes> returnObj = namedParameterJdbcTemplate.queryForList(sql,
				createParams("CONTAINERCODE", paramBean.getCode()), ReferenceCodes.class);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfklkpOffConOffConRef
	 *
	 * @param params
	 *
	 */
	public List<ReferenceCodes> cgfklkpOffConOffConRef(final ReferenceCodes paramBean) {
		final String param = "SYSTEMMODE";
		final String sql = getQuery("OIDRTCON_CGFKLKP_OFF_CON_OFF_CON_REF_C");
		final RowMapper<ReferenceCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				referenceCodesMapping);
		final ArrayList<ReferenceCodes> returnList = (ArrayList<ReferenceCodes>) namedParameterJdbcTemplate.query(sql,
				createParams("MODE", param), columnRowMapper);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfkchkOffConOffConOff
	 *
	 * @param params
	 *
	 */
	public Map<String, Object> cgfkchkOffConOffConOff(final OffenderBookings paramBean) {
		final String sql = getQuery("OIDRTCON_CGFKCHK_OFF_CON_OFF_CON_OFF_B");
		Map<String, Object> result = namedParameterJdbcTemplate.queryForMap(sql,
				createParams("OFFENDERBOOKID", paramBean.getOffenderBookId()));
		result.get("OFFENDER_ID");
		result.get("OFFENDER_ID_DISPLAY");
		result.get("OFFENDER_NAME");
		return result;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgwhenNewFormInstance
	 *
	 * @param params
	 *
	 */
	public List<SysDual> cgwhenNewFormInstance(final SysDual paramBean) {
		final String sql = getQuery("OIDRTCON_CGWHEN_NEW_FORM_INSTANCE");
		final RowMapper<SysDual> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, SysDual.class, sysDualMapping);
		final ArrayList<SysDual> returnList = (ArrayList<SysDual>) namedParameterJdbcTemplate.query(sql, createParams(),
				columnRowMapper);
		return returnList;
	}

	@Override
	public List<AgencyLocations> oidrtconRecievedFromLov() {
		final String sql = getQuery("OIDRTCON_RECIEVED_FROM_LOV");
		final RowMapper<AgencyLocations> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, AgencyLocations.class,
				agylocMapping);
		final ArrayList<AgencyLocations> returnList = (ArrayList<AgencyLocations>) namedParameterJdbcTemplate.query(sql,
				columnRowMapper);
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
		inParamMap.put("P_STATUS_CODE", "STORED");
		inParamMap.put("P_AGY_LOC_ID", paramBean.getAgyLocId());
		final SqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		try {
			simpleJDBCCall.execute(inParameter);
		} catch (Exception e) {
			return 0;
		}
		return 1;
	}

	public Integer getTranRoomStoradeId(final OffenderPptyContainers paramBean) {
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		Integer returnVAl = 0;
		SqlParameter[] sqlParameters = new SqlParameter[3];
		sqlParameters = new SqlParameter[] { new SqlParameter("P_AGY_LOC_ID", OracleTypes.VARCHAR),
				new SqlOutParameter("RETURN_VALUE", OracleTypes.NUMBER) };
		final SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("OMS_PROPERTY").withFunctionName("GET_TRAN_ROOM_STORAGE_ID")
				.declareParameters(sqlParameters);
		inParamMap.put("P_AGY_LOC_ID", paramBean.getTrnToAgyLocId());
		inParamMap.put("RETURN_VALUE", 0);
		final SqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		try {
			returnVAl = Integer.parseInt(simpleJDBCCall.executeFunction(BigDecimal.class, inParameter).toString());
		} catch (Exception e) {
			returnVAl = 0;
		}
		return returnVAl;
	}

	@Override
	public OffenderPptyItems gettingOldOffenderPptyItemsData(Integer propertyContainerId) {
		final String sql = getQuery("GETTING_OLD_OFFENDER_PPT_YITE_MSDATA");
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
}

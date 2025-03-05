package net.syscon.s4.inst.demographicsbiometrics.impl;

import java.util.ArrayList;
import java.util.Date;
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
import net.syscon.s4.common.beans.OffenderBookings;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.Dual;
import net.syscon.s4.im.beans.ModulePrivileges;
import net.syscon.s4.im.beans.OffenderAlerts;
import net.syscon.s4.im.beans.SysDual;
import net.syscon.s4.inst.casemanagement.beans.WorkFlowLogs;
import net.syscon.s4.inst.demographicsbiometrics.OcdalertRepository;

/**
 * class OcdalertRepositoryImpl
 */
@Repository
public class OcdalertRepositoryImpl extends RepositoryBase implements OcdalertRepository {
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OcdalertRepositoryImpl.class);
	private final Map<String, FieldMapper> alertMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("ALERT_DATE", 							new FieldMapper("alertDate"))
			.put("ALERT_CODE", 							new FieldMapper("alertCode"))
			.put("ALERT_STATUS", 						new FieldMapper("alertStatus"))
			.put("ALERT_TYPE", 							new FieldMapper("alertType"))
			.put("AUTHORIZE_PERSON_TEXT", 				new FieldMapper("authorizePersonText"))
			.put("CASELOAD_ID", 						new FieldMapper("caseloadId"))
			.put("CASELOAD_TYPE", 						new FieldMapper("caseloadType"))
			.put("CREATE_DATE", 						new FieldMapper("createDate"))
			.put("CREATE_DATETIME", 					new FieldMapper("createDatetime"))
			.put("CREATE_USER_ID", 						new FieldMapper("createUserId"))
			.put("EXPIRY_DATE", 						new FieldMapper("expiryDate"))
			.put("MODIFY_DATETIME", 					new FieldMapper("modifyDatetime"))
			.put("MODIFY_USER_ID", 						new FieldMapper("modifyUserId"))
			.put("ROOT_OFFENDER_ID", 					new FieldMapper("rootOffenderId"))
			.put("SEAL_FLAG", 							new FieldMapper("sealFlag"))
			.put("VERIFIED_FLAG", 						new FieldMapper("verifiedFlag"))
			.put("OFFENDER_BOOK_ID", 					new FieldMapper("offenderBookId"))
			.put("ALERT_SEQ", 							new FieldMapper("alertSeq"))
			.put("COMMENT_TEXT", 						new FieldMapper("commentText"))
			.put("DESCRIPTION", 						new FieldMapper("description"))
			.put("PROFILE_VALUE", 						new FieldMapper("profileValue"))
			.put("PROFILE_VALUE_2", 					new FieldMapper("profileValue2"))
			.put("PROFILE_TYPE", 						new FieldMapper("profileType"))
			.put("PROFILE_CODE", 						new FieldMapper("profileCode"))
			.put("LIST_SEQ", 							new FieldMapper("listSeq"))
			.put("CODE", 								new FieldMapper("code"))
			.put("PARENT_CODE", 						new FieldMapper("parentCode"))
			.put("ACTIVE_FLAG", 						new FieldMapper("activeFlag"))
			.put("BOOKING_BEGIN_DATE", 					new FieldMapper("bookingBeginDate"))
			.put("DOMAIN", 								new FieldMapper("domain"))
			.put("PARENT_DOMAIN", 						new FieldMapper("parentDomain"))
			
			.put("ALERT_TYPE_DESCRIPTION", 						new FieldMapper("alertTypeDescription"))
			.put("ALERT_CODE_DESCRIPTION", 						new FieldMapper("alertCodeDescription"))
			
			.build();
	private final Map<String, FieldMapper> referenceCodeMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DESCRIPTION", 						new FieldMapper("description"))
			.put("CODE", 								new FieldMapper("code"))
			.put("DOMAIN", 								new FieldMapper("domain"))
			.put("PARENT_DOMAIN", 						new FieldMapper("parentDomain"))
			.put("PARENT_CODE", 						new FieldMapper("parentCode"))
			.put("ACTIVE_FLAG", 						new FieldMapper("activeFlag"))
			.put("STAFF_ID", 						new FieldMapper("staffId"))
			.put("SEQ_VALUE", 						new FieldMapper("seqValue"))
			.build();
	private final Map<String, FieldMapper> dualMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("SYSDATE", 							new FieldMapper("sysDate"))
			.put("USER", 								new FieldMapper("user")).build();
	
	/**
	 * Creates new OcdalertRepositoryImpl class Object
	 */
	public OcdalertRepositoryImpl() {
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @return List<OffenderAlerts>
	 * @Param objOffenderAlerts
	 */
	public List<OffenderAlerts> searchOffenderAlerts(final OffenderAlerts objOffenderAlerts) {
		final String sql = getQuery("OCDALERT_ALERT_FIND_OFFENDER_ALERTS");
		String preparedSql = null;
		final StringBuffer sqlQuery = new StringBuffer();
		String preSqlQuery = null;
		final MapSqlParameterSource params = new MapSqlParameterSource();
		sqlQuery.append(sql);
		if (objOffenderAlerts != null) {
			sqlQuery.append(" WHERE ");
			if (objOffenderAlerts.getOffenderBookId() != null) {
				sqlQuery.append(" OFFENDER_BOOK_ID  = :offenderBookId " + " AND ");
				params.addValue("offenderBookId", objOffenderAlerts.getOffenderBookId());
			}
		}
		preparedSql = sqlQuery.toString().trim();
		if (preparedSql.endsWith("WHERE")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 5);
		}
		if (preparedSql.endsWith("AND")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 3);
		}
		if (objOffenderAlerts.getActiveFlag() != null && objOffenderAlerts.getActiveFlag().equals("Y")) {
			preSqlQuery = preparedSql.concat(" ORDER BY ALERT_STATUS DESC, EXPIRY_DATE DESC ");
		} else {
			preSqlQuery = preparedSql.concat(" ORDER BY ALERT_STATUS DESC, EXPIRY_DATE ");
		}
		final RowMapper<OffenderAlerts> alertOffenderRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderAlerts.class, alertMapping);
		List<OffenderAlerts> returnList = new ArrayList<OffenderAlerts>();
		returnList = namedParameterJdbcTemplate.query(preSqlQuery, params, alertOffenderRowMapper);
		return returnList;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @return Integer
	 * @Param lstOffenderAlerts1
	 */
	@Override
	public Integer alertInsertOffenderAlerts(final List<OffenderAlerts> lstOffenderAlerts) {
		final String sql = getQuery("OCDALERT_ALERT_INSERT_OFFENDER_ALERTS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffenderAlerts list : lstOffenderAlerts) {
			parameters.add(new BeanPropertySqlParameterSource(list));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error("Alert Insert Exception : ", e);
		}
		if (lstOffenderAlerts.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @return Integer
	 * @Param lstOffenderAlerts1
	 */
	public Integer alertUpdateOffenderAlerts(final List<OffenderAlerts> lstOffenderAlerts) {
		final String sql = getQuery("OCDALERT_ALERT_UPDATE_OFFENDER_ALERTS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffenderAlerts list : lstOffenderAlerts) {
			parameters.add(new BeanPropertySqlParameterSource(list));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error(e);
		}
		if (lstOffenderAlerts.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @return List<SystemProfiles>
	 * @Param lstOffenderAlerts1
	 */
	public Integer alertDeleteOffenderAlerts(final List<OffenderAlerts> lstOffenderAlerts) {
		final String sql = getQuery("OCDALERT_ALERT_DELETE_OFFENDER_ALERTS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (OffenderAlerts list : lstOffenderAlerts) {
			parameters.add(new BeanPropertySqlParameterSource(list));
		}
		try {
			String tableName = "OFFENDER_ALERTS";
			String whereClause = "OFFENDER_BOOK_ID = :offenderBookId and ALERT_SEQ = :alertSeq";
			batchUpdatePreDeletedRows(tableName, whereClause , parameters);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method alertDeleteOffenderAlerts", e);
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (EmptyResultDataAccessException e) {
			logger.error(e);
		}
		if (lstOffenderAlerts.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @return List<SystemProfiles>
	 * @Param objSystemProfiles
	 */
	public List<SystemProfiles> sysPflSearchSystemProfiles(final SystemProfiles objSystemProfiles) {
		final String sql = getQuery("OCDALERT_SYSPFL_FIND_SYSTEM_PROFILES");
		final RowMapper<SystemProfiles> systemProRowMapper = Row2BeanRowMapper.makeMapping(sql, SystemProfiles.class,
				alertMapping);
		ArrayList<SystemProfiles> returnList = new ArrayList<SystemProfiles>();
		String preparedSql = null;
		final StringBuffer sqlQuery = new StringBuffer();
		final MapSqlParameterSource valuesList = new MapSqlParameterSource();
		sqlQuery.append(sql);
		if (objSystemProfiles != null) {
			sqlQuery.append(" where ");

			if (objSystemProfiles.getProfileType() != null) {
				sqlQuery.append("PROFILE_TYPE =  :profileType " + " and ");
				valuesList.addValue("profileType", objSystemProfiles.getProfileType());
			}
		}
		preparedSql = sqlQuery.toString().trim();
		if (preparedSql.endsWith("where")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 5);
		}
		if (preparedSql.endsWith("and")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 3);
		}
		returnList = (ArrayList<SystemProfiles>) namedParameterJdbcTemplate.query(sql, valuesList, systemProRowMapper);
		return returnList;
	}

	/**
	 * method for preInsert
	 * 
	 * @return Integer
	 * @param offenderBookId
	 */
	public Integer alertPreInsertc(final Long offenderBookId) {
		Integer returnObj = null;
		final String sql = getQuery("OCDALERT_ALERT_PREINSERT_C");
		try {			
			returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams("offenderBookId", offenderBookId),
					Integer.class);
		} catch(Exception e) {			
			return returnObj;
		}
		return returnObj;
	}

	/**
	 * method for query callings
	 * 
	 * @param modulePrivileges
	 * @return Integer
	 */
	@Override
	public Integer alertPostQueryvarificationCurr(final ModulePrivileges modulePrivileges) {
		Integer returnObject = null;
		final String sql = getQuery("OCDALERT_ALERT_POSTQUERY_VARIFICATION_CURR");
		returnObject = namedParameterJdbcTemplate.queryForObject(sql,
				createParams("moduleName", modulePrivileges.getModuleName()), Integer.class);
		return returnObject;
	}

	@Override
	public List<ReferenceCodes> getAlertTypeCount() {
		final String sql = getQuery("ALERT_TYPE_COUNT");
		final RowMapper<ReferenceCodes> referenceCodesRowMapper = Row2BeanRowMapper.makeMapping(sql,
				ReferenceCodes.class, referenceCodeMapping);
		List<ReferenceCodes> returnList = new ArrayList<ReferenceCodes>();
		returnList = (namedParameterJdbcTemplate.query(sql, createParams(), referenceCodesRowMapper));
		return returnList;

	}

	@Override
	public List<ReferenceCodes> getAlertCodeCount() {
		final String sql = getQuery("ALERT_CODE_COUNT");
		final RowMapper<ReferenceCodes> referenceCodesRowMapper = Row2BeanRowMapper.makeMapping(sql,
				ReferenceCodes.class, referenceCodeMapping);
		List<ReferenceCodes> returnList = new ArrayList<ReferenceCodes>();
		returnList = (namedParameterJdbcTemplate.query(sql, createParams(), referenceCodesRowMapper));
		return returnList;

	}

	@Override
	public Long getStaffMemCount() {
		final String sql = getQuery("STAFF_MEMBER_ROLES_COUNT");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams(), Long.class);
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param sysDual
	 * @return List<Dual>
	 */
	public List<Dual> cgwhenNewFormInstancec(final SysDual sysDual) {
		final String sql = queryBuilderFactory
				.getQueryBuilder(getQuery("OCDALERT_CGWHEN_NEW_FORM_INSTANCE_C"), alertMapping).build();
		final RowMapper<Dual> dualRowMapper = Row2BeanRowMapper.makeMapping(sql, Dual.class, dualMapping);
		return namedParameterJdbcTemplate.query(sql, dualRowMapper);
	}

	/**
	 * method for query callings
	 * 
	 * @param mode
	 * @return List<ReferenceCodes>
	 */
	@Override
	public List<ReferenceCodes> cgfkchkAlertAlertRefAlertc(final String alertCode, final String mode) {
		final String sql = queryBuilderFactory
				.getQueryBuilder(getQuery("OCDALERT_CGFKCHK_ALERT_ALERT_REF_ALERT_C"), alertMapping).build();
		final RowMapper<ReferenceCodes> referenceCodesRowMapper = Row2BeanRowMapper.makeMapping(sql,
				ReferenceCodes.class, referenceCodeMapping);
		ArrayList<ReferenceCodes> refList = new ArrayList<>();
		refList = (ArrayList<ReferenceCodes>) (namedParameterJdbcTemplate.query(sql,
				createParams("domain", alertCode, "code", mode), referenceCodesRowMapper));
		return refList;

	}

	/**
	 * method for query callings
	 * 
	 * @param String
	 * @return List<ReferenceCodes>
	 */
	@Override
	public List<ReferenceCodes> cgfklkpAlertAlertRefAlertc(final String code, final String activeFlag,
			final String description, final String parentCode) {
		final String sql = getQuery("OCDALERT_CGFKLKP_ALERT_ALERT_REF_ALERT_C");
		final RowMapper<ReferenceCodes> referenceCodesRowMapper = Row2BeanRowMapper.makeMapping(sql,
				ReferenceCodes.class, referenceCodeMapping);
		ArrayList<ReferenceCodes> returnList = new ArrayList<ReferenceCodes>();
		returnList = (ArrayList<ReferenceCodes>) (namedParameterJdbcTemplate.query(sql,
				createParams("code", code, "activeFlag", activeFlag, "description", description, "description",
						description, "parentCode", parentCode),
				referenceCodesRowMapper));
		return returnList;
	}

	/**
	 * method for query callings
	 * 
	 * @return List<ReferenceCodes>
	 * @param code,domain
	 */
	@Override
	public List<ReferenceCodes> cgfkchkAlertAlertRefAlc(final String code, final String domain) {
		final String sql = getQuery("OCDALERT_CGFKCHK_ALERT_ALERT_REF_AL_C");
		final RowMapper<ReferenceCodes> alertOffenderRowMapper = Row2BeanRowMapper.makeMapping(sql,
				ReferenceCodes.class, referenceCodeMapping);
		ArrayList<ReferenceCodes> returnList = new ArrayList<ReferenceCodes>();
		returnList = (ArrayList<ReferenceCodes>) (namedParameterJdbcTemplate.query(sql,
				createParams("code", code, "domain", domain), alertOffenderRowMapper));
		return returnList;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param code, description, domain
	 * @return List<ReferenceCodes>
	 */
	@Override
	public List<ReferenceCodes> findDescriptionbyDomain(final ReferenceCodes referenceCodes) {
		final String sql = getQuery("FIND_DESCRIPTION_BY_DOMAIN");
		final RowMapper<ReferenceCodes> alertOffenderRowMapper = Row2BeanRowMapper.makeMapping(sql,
				ReferenceCodes.class, referenceCodeMapping);
		final ArrayList<ReferenceCodes> refList = new ArrayList<ReferenceCodes>();
		ReferenceCodes refObj = (namedParameterJdbcTemplate.queryForObject(sql,
				createParams("domain", referenceCodes.getDomain()), alertOffenderRowMapper));
		refList.add(refObj);
		return refList;
	}

	/**
	 * method for query callings
	 * 
	 * @return List<ReferenceCodes>
	 * @param code,
	 *            description, domain
	 */
	@Override
	public List<ReferenceCodes> findDescriptionbyDescriptionCodeAndParentCode(final ReferenceCodes referenceCodes) {
		final String sql = getQuery("FIND_DESCRIPTION_BY_DES_CODE_PERENTCODE");
		final RowMapper<ReferenceCodes> alertOffenderRowMapper = Row2BeanRowMapper.makeMapping(sql,
				ReferenceCodes.class, referenceCodeMapping);
		final ArrayList<ReferenceCodes> refList = new ArrayList<ReferenceCodes>();
		ReferenceCodes refObj = (namedParameterJdbcTemplate.queryForObject(sql,
				createParams("code", referenceCodes.getCode(), "domain", referenceCodes.getDomain(), "description",
						referenceCodes.getDescription(), "parentCode", referenceCodes.getParentCode()),
				alertOffenderRowMapper));
		refList.add(refObj);
		return refList;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param code, description, domain
	 * @return List<ReferenceCodes>
	 */
	@Override
	public List<ReferenceCodes> cgfklkpAlertAlertRefAlc(final ReferenceCodes referenceCodes) {
		final String sql = getQuery("OCDALERT_CGFKLKP_ALERT_ALERT_REF_AL_C");
		final RowMapper<ReferenceCodes> alertOffenderRowMapper = Row2BeanRowMapper.makeMapping(sql,
				ReferenceCodes.class, referenceCodeMapping);
		final ArrayList<ReferenceCodes> refList = new ArrayList<ReferenceCodes>();
		ReferenceCodes refObj = (namedParameterJdbcTemplate.queryForObject(sql,
				createParams("code", referenceCodes.getCode(), "domain", referenceCodes.getDomain(), "description",
						referenceCodes.getDescription()),
				alertOffenderRowMapper));
		refList.add(refObj);
		return refList;
	}

	/**
	 * method for query callings
	 * 
	 * @return List<OffenderAlerts>
	 * @param offenderBookId
	 */
	public List<OffenderAlerts> alertSearchOffenderAlerts(final String OffenderBookId) {
		final String sql = getQuery("OCDALERT_ALERT_FIND_OFFENDER_ALERTS");
		final RowMapper<OffenderAlerts> alertOffenderRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderAlerts.class, alertMapping);
		String preparedSql = null;
		final StringBuffer sqlQuery = new StringBuffer();
		final MapSqlParameterSource valuesList = new MapSqlParameterSource();
		sqlQuery.append(sql);
		if (OffenderBookId != null) {
			sqlQuery.append(" where ");
		}
		if (OffenderBookId != null) {
			sqlQuery.append("OFFENDER_BOOK_ID =  :offenderBookId " + " and ");
			valuesList.addValue("offenderBookId", OffenderBookId);
		}
		preparedSql = sqlQuery.toString().trim();
		if (preparedSql.endsWith("where")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 5);
		}
		if (preparedSql.endsWith("and")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 3);
		}
		List<OffenderAlerts> refList = new ArrayList<>();
		refList = namedParameterJdbcTemplate.query(preparedSql, valuesList, alertOffenderRowMapper);
		return refList;
	}

	/**
	 * method for query callings
	 * 
	 * @return List<ReferenceCodes>
	 */
	public List<ReferenceCodes> getCodeAlertDes() {
		final String sql = getQuery("FIND_CODE_ALERT_DESCRIPTION_FROM_REFERENCE_CODES");
		final RowMapper<ReferenceCodes> alertOffenderRowMapper = Row2BeanRowMapper.makeMapping(sql,
				ReferenceCodes.class, referenceCodeMapping);
		List<ReferenceCodes> returnList = new ArrayList<>();
		returnList = (namedParameterJdbcTemplate.query(sql, createParams(), alertOffenderRowMapper));
		return returnList;
	}

	/**
	 * method for query callings
	 * 
	 * @param cost0f
	 * @return Integer
	 */
	@Override
	public List<OffenderAlerts> cguvchkOffAlertUkc(final OffenderAlerts paramBean) {
		final String sql = getQuery("OCDALERT_CGUVCHK_OFF_ALERT_UK");
		List<OffenderAlerts> returnList = new ArrayList<OffenderAlerts>();
		final RowMapper<OffenderAlerts> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, OffenderAlerts.class,
				alertMapping);
		returnList = namedParameterJdbcTemplate.query(sql,
				createParams("rowId", paramBean.getAlertSeq(), "rowId", paramBean.getAlertSeq(), "alertDate",
						paramBean.getAlertDate(), "offenderBookId", paramBean.getOffenderBookId(), "alertType",
						paramBean.getAlertType(), "alertCode", paramBean.getAlertCode()),
				columnRowMapper);
		return returnList;
	}

	/**
	 * method for query callings
	 * 
	 * @param const0
	 * @return OffenderBookings
	 */
	@Override
	public OffenderBookings getBookingBeginDate(final String const0) {
		final String sql = getQuery("OCDALERT_GET_BOOKING_BEGIN_DATE");
		final OffenderBookings offenderBookings = new OffenderBookings();
		Object returnObj;
		returnObj = (namedParameterJdbcTemplate.queryForObject(sql, createParams("offenderBookId", const0),
				Object.class));
		offenderBookings.setBookingBeginDate((Date) returnObj);
		return offenderBookings;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param offenderId
	 * @return Integer
	 */
	@Override
	public Integer countOffenderBookings(final String offenderId) {
		Integer returnObject = null;
		final String sql = getQuery("FIND_CHECK_ALERT_DATATABLE_COUNT");
		returnObject = namedParameterJdbcTemplate.queryForObject(sql, createParams("offenderId", offenderId),
				Integer.class);
		return returnObject;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @return List<String>
	 */
	@Override
	public List<String> findAlertStatusList() {
		final String sql = getQuery("FIND_ALERT_STATUS_LIST");
		return namedParameterJdbcTemplate.queryForList(sql, createParams(), String.class);
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param offenderId, alertSeq
	 * @return Integer
	 */
	@Override
	public Integer workFlowIdCount(final Long offenderBookId, final Long alertSeq) {
		Integer returnObject = null;
		final String sql = getQuery("FIND_WORK_FLOW_ID_COUNT");
		returnObject = namedParameterJdbcTemplate.queryForObject(sql,
				createParams("OFFENDERBOOKID", offenderBookId, "ALERTSEQ", alertSeq), Integer.class);
		return returnObject;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @return Integer
	 */
	public Long workFlowLogsPreInsertc() {
		final String sql = getQuery("FIND_MAX_WORK_FLOW_ID");
		Long obj = null;
		Long returnval = null;
		obj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), Long.class);
		if (obj != null) {
			returnval = (obj);
		}
		return returnval;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @return Integer
	 * @Param lstOffenderAlerts1
	 */
	@Override
	public Integer workFlowsInsertWorkFlows(final List<OffenderAlerts> lstOffenderAlerts) {
		final String sql = getQuery("INSERT_WORK_FLOWS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffenderAlerts list : lstOffenderAlerts) {
			parameters.add(new BeanPropertySqlParameterSource(list));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error("Alert Insert Exception : ", e);
		}
		if (lstOffenderAlerts.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @return Integer
	 * @Param lstOffenderAlerts1
	 */
	@Override
	public Integer workFlowsInsertWorkFlowLogs(final List<OffenderAlerts> lstOffenderAlerts) {
		final String sql = getQuery("INSERT_WORK_FLOW_LOGS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffenderAlerts list : lstOffenderAlerts) {
			parameters.add(new BeanPropertySqlParameterSource(list));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error("Alert Insert Exception : ", e);
		}
		if (lstOffenderAlerts.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @return Integer
	 * @Param lstOffenderAlerts1
	 */
	@Override
	public Integer workFlowsInsertWorkFlowLogsUpdate(final List<OffenderAlerts> lstOffenderAlerts) {
		final String sql = getQuery("INSERT_WORK_FLOW_LOGS_UPDATE");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffenderAlerts list : lstOffenderAlerts) {
			parameters.add(new BeanPropertySqlParameterSource(list));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error("Alert Insert Exception : ", e);
		}
		if (lstOffenderAlerts.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param offenderId, alertSeq
	 * @return Integer
	 */
	@Override
	public Integer workFlowIdMaxVal(final Long offenderBookId, final Long alertSeq) {
		Integer returnObject = null;
		final String sql = getQuery("FIND_WORK_FLOW_ID_MAX_VAL_DEL");
		returnObject = namedParameterJdbcTemplate.queryForObject(sql,
				createParams("OFFENDERBOOKID", offenderBookId, "ALERTSEQ", alertSeq), Integer.class);
		return returnObject;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @return List<SystemProfiles>
	 * @Param lstOffenderAlerts1
	 */
	public Integer workFlowsDeleteWorkFlows(final List<OffenderAlerts> lstOffenderAlerts) {
		final String sql = getQuery("DELETE_WORK_FLOWS_WORK_FLOW_ID");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (OffenderAlerts list : lstOffenderAlerts) {
			parameters.add(new BeanPropertySqlParameterSource(list));
		}
		try {
			String tableName = "WORK_FLOWS";
			String whereClause = "WORK_FLOW_ID = :workFlowId";
			batchUpdatePreDeletedRows(tableName, whereClause , parameters);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method workFlowsDeleteWorkFlows", e);
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (EmptyResultDataAccessException e) {
			logger.error(e);
		}
		if (lstOffenderAlerts.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @return List<SystemProfiles>
	 * @Param lstOffenderAlerts1
	 */
	public Integer workFlowsDeleteWorkFlowLogs(final List<OffenderAlerts> lstOffenderAlerts) {
		final String sql = getQuery("DELETE_WORK_FLOW_LOGS_WORK_FLOW_ID");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (OffenderAlerts list : lstOffenderAlerts) {
			parameters.add(new BeanPropertySqlParameterSource(list));
		}
		try {
			String tableName = "WORK_FLOW_LOGS";
			String whereClause = "WORK_FLOW_ID = :workFlowId";
			batchUpdatePreDeletedRows(tableName, whereClause , parameters);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method workFlowsDeleteWorkFlowLogs", e);
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (EmptyResultDataAccessException e) {
			logger.error(e);
		}
		if (lstOffenderAlerts.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

	/**
	 * @param
	 *
	 *
	 */
	public Integer preInsert(final Long object) {
		final String sql = getQuery("OCDALERT_GET_WORK_FLOW_SEQ");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("WORK_FLOW_ID", object), Integer.class);
	}

	/**
	 * This method is used to update the records in the data base tables based
	 * on
	 *
	 * @param listObject
	 *            List<WorkFlowLogs>
	 *
	 * @return List<Integer>
	 *
	 * @
	 */
	public Integer workFlCommit(final List<WorkFlowLogs> listObject) {
		final String sql = getQuery("OCDALERT_WORK_FLOW_COMMIT");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final WorkFlowLogs obj : listObject) {
			parameters.add(new BeanPropertySqlParameterSource(obj));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (listObject.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

	/**
	 * This method is used to update the records in the data base tables based
	 * on
	 *
	 * @param listObject
	 *            List<WorkFlowLogs>
	 *
	 * @return List<Integer>
	 *
	 * @
	 */
	public Integer offAlertUpdate(final List<OffenderAlerts> listObject) {
		final String sql = getQuery("OCDALERT_OFF_ALERT_UPDATE");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffenderAlerts obj : listObject) {
			parameters.add(new BeanPropertySqlParameterSource(obj));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (listObject.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

	/**
	 * method for query callings
	 * 
	 * @param String
	 * @return List<ReferenceCodes>
	 */
	@Override
	public List<ReferenceCodes> cgfklkpAlertAlertRefCodes() {
		final String sql = getQuery("OCDALERT_REFERENCE_CODES_ALERT");
		final RowMapper<ReferenceCodes> referenceCodesRowMapper = Row2BeanRowMapper.makeMapping(sql,
				ReferenceCodes.class, referenceCodeMapping);
		List<ReferenceCodes> returnList = new ArrayList<ReferenceCodes>();
		returnList = (namedParameterJdbcTemplate.query(sql, createParams(), referenceCodesRowMapper));
		return returnList;
	}

	@Override
	public List<ReferenceCodes> cgfklkpAlertAlertCode(final String alertType) {
		final String sql = getQuery("OCDALERT_REFERENCE_CODES_ALERT_CODE");
		final RowMapper<ReferenceCodes> referenceCodesRowMapper = Row2BeanRowMapper.makeMapping(sql,
				ReferenceCodes.class, referenceCodeMapping);
		List<ReferenceCodes> returnList = new ArrayList<ReferenceCodes>();
		returnList = (namedParameterJdbcTemplate.query(sql, createParams("alertType", alertType), referenceCodesRowMapper));
		return returnList;
	}

	/**
	 * method for query callings
	 * 
	 * @param String
	 * @return List<ReferenceCodes>
	 */
	public Integer checkWorkActionCode(Long workFlowId) {
		Integer returnObject = null;
		final String sql = getQuery("CHECK_WORK_ACTION_CODE");
		returnObject = namedParameterJdbcTemplate.queryForObject(sql, createParams("WORKFLOWID", workFlowId),
				Integer.class);
		return returnObject;
	}

	@Override
	public String alertDeleteData() {
		String returnObject = null;
		final String sql = getQuery("CHECK_ALERT_DELETE");
		try {
			returnObject = namedParameterJdbcTemplate.queryForObject(sql, createParams(),
					String.class);
		} catch (Exception e) {
			logger.error("alertDeleteData : ", e);
		}
		return returnObject;
	}

	@Override
	public String alertCodeData() {
		String returnObject = null;
		final String sql = getQuery("CHECK_ALERT_CODE");
		try {
			returnObject = namedParameterJdbcTemplate.queryForObject(sql, createParams(),
					String.class);
		} catch (Exception e) {
			logger.error("alertCodeData : ", e);
		}
		return returnObject;
	}

	@Override
	public List<OffenderAlerts> getAlertDetails(OffenderAlerts obj) {
		final String sql = getQuery("OCDALERT_GET_ALERT_DETAILS");
		List<OffenderAlerts> returnList = new ArrayList<OffenderAlerts>();
		try {
			final RowMapper<OffenderAlerts> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, OffenderAlerts.class, alertMapping);
			returnList = namedParameterJdbcTemplate.query(sql, createParams("offenderBookId", obj.getOffenderBookId(), "alertType", obj.getAlertType(),
						"alertCode", obj.getAlertCode()), columnRowMapper);
		} catch (Exception e) {
			logger.error("getAlertDetails : ", e);
		}
		return returnList;
	}
}

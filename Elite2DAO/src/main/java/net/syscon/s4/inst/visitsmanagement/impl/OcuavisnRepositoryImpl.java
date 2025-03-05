package net.syscon.s4.inst.visitsmanagement.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.inst.booking.beans.OffenderContactPersons;
import net.syscon.s4.inst.visitsmanagement.OcuavisnRepository;
import net.syscon.s4.inst.visitsmanagement.beans.VOffContactPersons;
import oracle.jdbc.OracleTypes;

/**
 * Class OcuavisnRepositoryImpl
 */
@Repository
public class OcuavisnRepositoryImpl extends RepositoryBase implements OcuavisnRepository {

	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OcuavisnRepositoryImpl.class.getName());


	private final Map<String, FieldMapper> vOffContactPersonsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("OFFENDER_ID_DISPLAY", 		new FieldMapper("offenderIdDisplay"))
			.put("OFFENDER_BOOK_ID", 			new FieldMapper("offenderBookId"))
			.put("COMMENT_TEXT", 				new FieldMapper("commentText"))
			.put("PERSON_FIRST_NAME", 			new FieldMapper("personFirstName"))
			.put("CONTACT_TYPE", 				new FieldMapper("contactType"))
			.put("ACTIVE_FLAG", 				new FieldMapper("activeFlag"))
			.put("MODIFY_USER_ID", 				new FieldMapper("modifyUserId"))
			.put("MIDDLE_NAME", 				new FieldMapper("middleName"))
			.put("CONTACT_ROOT_OFFENDER_ID", 	new FieldMapper("contactRootOffenderId"))
			.put("PERSON_ID", 					new FieldMapper("personId"))
			.put("APPROVED_VISITOR_FLAG", 		new FieldMapper("approvedVisitorFlag"))
			.put("CHECK_SUM", 					new FieldMapper("checkSum"))
			.put("OFFENDER_ID", 				new FieldMapper("offenderId"))
			.put("LAST_NAME", 					new FieldMapper("lastName"))
			.put("CASELOAD_TYPE", 				new FieldMapper("caseloadType"))
			.put("PERSON_LAST_NAME", 			new FieldMapper("personLastName"))
			.put("CONTACT_TYPE_DESCRIPTION", 	new FieldMapper("contactTypeDescription"))
			.put("RELATIONSHIP_TYPE_DESCRIPTION", new FieldMapper("relationshipTypeDescription"))
			.put("RELATIONSHIP_TYPE", 			new FieldMapper("relationshipType"))
			.put("MODIFY_DATETIME", 			new FieldMapper("modifyDatetime"))
			.put("OFFENDER_CONTACT_PERSON_ID", 	new FieldMapper("offenderContactPersonId"))
			.put("FIRST_NAME", 					new FieldMapper("firstName"))
			.put("DESCRIPTION", new FieldMapper("description"))
			.put("CODE", new FieldMapper("code")).build();
	
	private final Map<String, FieldMapper> mMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DESCRIPTION", new FieldMapper("description"))
			.put("CODE", new FieldMapper("code"))
			.build();

	/**
	 * Creates new OcuavisnRepositoryImpl class Object
	 */
	public OcuavisnRepositoryImpl() {
		// OcuavisnRepositoryImpl
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            VOffContactPersons
	 *
	 * @return List<VOffContactPersons>
	 *
	 * @
	 */
	public List<VOffContactPersons> vOffAuthVisExecuteQuery(final VOffContactPersons objSearchDao) {
		final String sql = getQuery("OCUAVISN_VOFFAUTHVIS_FIND_V_OFF_CONTACT_PERSONS");
		String preparedSql = null;
		final StringBuffer sqlQuery = new StringBuffer();
		final MapSqlParameterSource params = new MapSqlParameterSource();
		sqlQuery.append(sql);
		if (objSearchDao != null) {
			sqlQuery.append(" WHERE ");
			sqlQuery.append("ACTIVE_FLAG >'N'" + " AND ");
			if (objSearchDao.getOffenderBookId() != null) {
				sqlQuery.append("OFFENDER_BOOK_ID = :offenderBookId" + " AND ");
				params.addValue("offenderBookId", objSearchDao.getOffenderBookId());
			}
			if (objSearchDao.getPersonId() != null) {
				sqlQuery.append("PERSON_ID = :personId" + " AND ");
				params.addValue("personId", objSearchDao.getPersonId());
			}
		}
		preparedSql = sqlQuery.toString().trim();
		if (preparedSql.endsWith("WHERE")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 5);
		}
		if (preparedSql.endsWith("AND")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 3);
		}
		preparedSql = preparedSql + " ORDER BY  PERSON_LAST_NAME ";
		final RowMapper<VOffContactPersons> vOffAuthVisitMapper = Row2BeanRowMapper
				.makeMapping(sql, VOffContactPersons.class, vOffContactPersonsMapping);
		final ArrayList<VOffContactPersons> returnList = (ArrayList<VOffContactPersons>) namedParameterJdbcTemplate
				.query(preparedSql, params, vOffAuthVisitMapper);
		return returnList;
	}

	/**
	 * This method is used to insert the records in the data base tables based
	 * on
	 *
	 * @param lstVOffContactPersons
	 *            List<VOffContactPersons>
	 *
	 * @return List<Integer>
	 *
	 * @
	 */
	public Integer vOffAuthVisInsertVOffContactPersons(final List<OffenderContactPersons> lstVOffContactPersons) {
		final String sql = getQuery("OCUAVISN_VOFFAUTHVIS_INSERT_V_OFF_CONTACT_PERSONS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffenderContactPersons offenderContactPersons : lstVOffContactPersons) {
			parameters.add(new BeanPropertySqlParameterSource(offenderContactPersons));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstVOffContactPersons.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstVOffContactPersons
	 *            List<VOffContactPersons>
	 *
	 * @
	 */
	public Integer vOffAuthVisUpdateVOffContactPersons(final List<OffenderContactPersons> lstVOffContactPersons) {
		final String sql = getQuery("OCUAVISN_VOFFAUTHVIS_UPDATE_V_OFF_CONTACT_PERSONS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffenderContactPersons vOffContactPersons : lstVOffContactPersons) {
			parameters.add(new BeanPropertySqlParameterSource(vOffContactPersons));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstVOffContactPersons.size() == returnArray.length) {
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
	public List<ReferenceCodes> rgContactTypeRecordGroup() {
		final String sql = getQuery("OCUAVISN_FIND_RGCONTACTTYPE");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				vOffContactPersonsMapping);
		List<ReferenceCodes> returnList = new ArrayList<ReferenceCodes>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (Exception e) {
			logger.error("rgContactTypeRecordGroup", e);
		}
		return returnList;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<ReferenceCodes> rgRelationshipTypeRecordGroup(final String contactType) {
		final String sql = getQuery("OCUAVISN_FIND_RGRELATIONSHIPTYPE");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				vOffContactPersonsMapping);
		List<ReferenceCodes> returnList = new ArrayList<ReferenceCodes>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams("CONTACTTYPE", contactType), mRowMapper);
		} catch (Exception e) {
			logger.error("rgRelationshipTypeRecordGroup", e);
		}
		return returnList;
	}

	@Override
	public VOffContactPersons populateVisitorDetails(final Integer offenderBookId,final  Integer personId,
			final Integer offenderContactPersonId, final Date visitDate) {
		final VOffContactPersons bean = new VOffContactPersons();
		Map<String, Object> returnObject = null;
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[20];
		sqlParameters = new SqlParameter[] { new SqlParameter("P_OFFENDER_BOOK_ID", OracleTypes.NUMBER),
				new SqlParameter("P_PERSON_ID", OracleTypes.NUMBER),
				new SqlParameter("P_OFFENDER_CONTACT_PERSON_ID", OracleTypes.NUMBER),
				new SqlParameter("P_VISIT_DATE", OracleTypes.DATE), };
		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("TAG_VISITS").withProcedureName("POPULATE_VISITOR_DETAILS")
				.declareParameters(sqlParameters);
		inParamMap.put("P_OFFENDER_BOOK_ID", offenderBookId);
		inParamMap.put("P_PERSON_ID", personId);
		inParamMap.put("P_OFFENDER_CONTACT_PERSON_ID", offenderContactPersonId);
		inParamMap.put("P_VISIT_DATE", visitDate);
		final MapSqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		try {
			returnObject = simpleJDBCCall.execute(inParameter);
			bean.setLastName((String) returnObject.get("P_LAST_NAME"));
			bean.setFirstName((String) returnObject.get("P_FIRST_NAME"));
			bean.setContactType((String) returnObject.get("P_CONTACT_TYPE"));
			bean.setRelationshipType((String) returnObject.get("P_RELATIONSHIP_TYPE"));
			bean.setRestriction((String) returnObject.get("P_RESTRICTION"));
			bean.setGlobalRestriction((String) returnObject.get("P_GL_RESTRICTION"));
			bean.setAge((BigDecimal) returnObject.get("P_AGE"));
		} catch (Exception e) {
		}
		return bean;
	}

	@Override
	public Integer getContactPersonId() {
		final String sql = getQuery("OCUAVISN_GET_OFFENDER_CONTACT_PERSON_ID");
		Integer refList;
		refList = namedParameterJdbcTemplate.queryForObject(sql, createParams(), Integer.class);
		return refList;
	}

	
	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<ReferenceCodes> rgRelationshipTypeTotalRecordGroup() {
		final String sql = getQuery("OCUAVISN_FIND_TOTAL_RELATIONSHIP");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				vOffContactPersonsMapping);
		List<ReferenceCodes> returnList = new ArrayList<ReferenceCodes>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (Exception e) {
			logger.error("rgRelationshipTypeRecordGroup", e);
		}
		return returnList;
	}
	@Override
	public String getConatctTypeDescription(String contactType) {
		final String sql = getQuery("OCUAVISN_GET_CONTACT_TYPE_DESCRIPTION");
		String refList;
		refList = namedParameterJdbcTemplate.queryForObject(sql, createParams("contactType", contactType), String.class);
		return refList;
	}
	
	@Override
	public String getRelationTypeDescription(String relationType) {
		final String sql = getQuery("OCUAVISN_GET_RELATIONTYPE_DESCRIPTION");
		String refList;
		refList = namedParameterJdbcTemplate.queryForObject(sql, createParams("relationType", relationType), String.class);
		return refList;
	}
}

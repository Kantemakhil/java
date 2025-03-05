package net.syscon.s4.inst.visitsmanagement.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.Offenders;
import net.syscon.s4.im.beans.VisitorRestrictions;
import net.syscon.s4.inst.booking.beans.Persons;
import net.syscon.s4.inst.visitsmanagement.OcuvwarnRepository;
import net.syscon.s4.inst.visitsmanagement.beans.OffenderRestrictions;
import oracle.jdbc.OracleTypes;

/**
 * Class OcuvwarnRepositoryImpl
 */
@Repository
public class OcuvwarnRepositoryImpl extends RepositoryBase implements OcuvwarnRepository {

	private final Map<String, FieldMapper> visitorMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("RESTRICTIONDATE", new FieldMapper("restrictionDate"))
			.put("VISITRESTRICTIONTYPE", new FieldMapper("visitRestrictionType"))
			.put("DESCRIPTION", new FieldMapper("description")).build();

	/**
	 * Creates new OcuvwarnRepositoryImpl class Object
	 */
	public OcuvwarnRepositoryImpl() {
		// OcuvwarnRepositoryImpl
	}

	public List<OffenderRestrictions> offenderRestrictionExecuteQuery(final OffenderRestrictions searchBean) {
		final String sql = getQuery("OCUVWARN_GET_OFFENDER_RESTRICTIONS");
		final RowMapper<OffenderRestrictions> visitorRestrictionsRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderRestrictions.class, visitorMapping);
		final ArrayList<OffenderRestrictions> returnList = (ArrayList<OffenderRestrictions>) namedParameterJdbcTemplate
				.query(sql, createParams("VISIT_DATE", searchBean.getVisitDate(), "OFFENDER_BOOK_ID",
						searchBean.getOffenderBookId()), visitorRestrictionsRowMapper);
		return returnList;
	}

	/**
	 * This method is used to insert the records in the data base tables based on
	 *
	 * @param lstTagVisitsGetOffenderRestrictions List<TagVisitsGetOffenderRestrictions>
	 *
	 * @return List<Integer>
	 *
	 * @
	 */
	public Integer offenderRestrictionInsertTagVisitsGetOffenderRestrictions(
			final List<OffenderRestrictions> lstTagVisitsGetOffenderRestrictions) {
		int insertCount = 0;
		final String sql = getQuery("OCUVWARN_OFFENDERRESTRICTION_INSERT_TAG_VISITS.GET_OFFENDER_RESTRICTIONS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		for (int i = 0; i < returnArray.length; i++) {
			insertCount = insertCount++;
		}
		if (lstTagVisitsGetOffenderRestrictions.size() == insertCount) {
			return 1;
		} else {
			return 0;
		}

	}

	public List<VisitorRestrictions> visitorRestrictionsExecuteQuery(final VisitorRestrictions objSearchDao) {
		final List<VisitorRestrictions> lListObj = new ArrayList<VisitorRestrictions>();
		return lListObj;
	}

	/**
	 * This method is used to insert the records in the data base tables based on
	 *
	 * @param lstTagVisitsGetVisitorRestrictions List<TagVisitsGetVisitorRestrictions>
	 *
	 * @return List<Integer>
	 *
	 * @
	 */
	public Integer visitorRestrictionsInsertTagVisitsGetVisitorRestrictions(
			final List<VisitorRestrictions> lstTagVisitsGetVisitorRestrictions) {
		int insertCount = 0;
		final String sql = getQuery("OCUVWARN_VISITORRESTRICTIONS_INSERT_TAG_VISITS.GET_VISITOR_RESTRICTIONS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		for (int i = 0; i < returnArray.length; i++) {
			insertCount = insertCount++;
		}
		if (lstTagVisitsGetVisitorRestrictions.size() == insertCount) {
			return 1;
		} else {
			return 0;
		}

	}

	@Override
	public List<VisitorRestrictions> populateVisitorDetailsExecuteQuery(final OffenderRestrictions searchBean) {
		final String sql = getQuery("OCUVWARN_TAG_VISIT_GET_VISITOR_RESTRICTIONS");
		final RowMapper<VisitorRestrictions> visitorRestrictionsRowMapper = Row2BeanRowMapper.makeMapping(sql,
				VisitorRestrictions.class, visitorMapping);
		final ArrayList<VisitorRestrictions> returnList = (ArrayList<VisitorRestrictions>) namedParameterJdbcTemplate
				.query(sql, createParams("PERSON_ID", searchBean.getPersonId(), "VISIT_DATE", searchBean.getVisitDate(),
						"OFFENDER_BOOK_ID", searchBean.getOffenderBookId()), visitorRestrictionsRowMapper);
		return returnList;
	}

	@Override
	public Persons getPersonNames(final OffenderRestrictions searchBean) {
		final Persons bean = new Persons();
		Map<String, Object> returnObject = null;
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[10];
		sqlParameters = new SqlParameter[] { new SqlParameter("P_PERSON_ID", OracleTypes.NUMBER),
				new SqlOutParameter("P_LAST_NAME", OracleTypes.VARCHAR),
				new SqlOutParameter("P_FIRST_NAME", OracleTypes.VARCHAR),
				new SqlOutParameter("P_AGE", OracleTypes.NUMBER),
				new SqlOutParameter("P_RESTRICTION", OracleTypes.VARCHAR),
				new SqlParameter("P_VISIT_DATE", OracleTypes.DATE) };
		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("TAG_VISITS").withProcedureName("GETPERSONNAMES").declareParameters(sqlParameters);
		inParamMap.put("P_PERSON_ID", searchBean.getPersonId());
		inParamMap.put("P_VISIT_DATE", searchBean.getVisitDate());
		final MapSqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		try {
			returnObject = simpleJDBCCall.execute(inParameter);
			bean.setLastName(
					returnObject.get("P_LAST_NAME") != null ? String.valueOf(returnObject.get("P_LAST_NAME")) : null);
			bean.setFirstName(
					returnObject.get("P_FIRST_NAME") != null ? String.valueOf(returnObject.get("P_FIRST_NAME")) : null);
			bean.setAge(returnObject.get("P_AGE") != null ? String.valueOf(returnObject.get("P_AGE")) : null);
		} catch (Exception e) {
		}
		return bean;

	}

	@Override
	public SystemProfiles getProfileValues(final String profileType, final String profileCode) {
		final String sql = getQuery("OCUVWARN_GET_PROFILE_VALUES");
		final RowMapper<SystemProfiles> visitorRestrictionsRowMapper = Row2BeanRowMapper.makeMapping(sql,
				SystemProfiles.class, visitorMapping);
		final SystemProfiles returnList = namedParameterJdbcTemplate.queryForObject(sql,
				createParams("PROFILE_TYPE", profileType, "PROFILE_CODE", profileCode), visitorRestrictionsRowMapper);
		return returnList;
	}

	@Override
	public Offenders getOffenderNames(final Long offenderId) {
		Offenders returnList = null;
		try {
			final String sql = getQuery("OCUVWARN_GET_OFFENDER_NAMES");
			final RowMapper<Offenders> visitorRestrictionsRowMapper = Row2BeanRowMapper.makeMapping(sql, Offenders.class,
					visitorMapping);
			returnList = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("offenderId", offenderId), visitorRestrictionsRowMapper);
			return returnList;
		} catch(Exception e) {
			e.printStackTrace();
		}
		return returnList;
	}

	/**
	 * 
	 * This method will get the default status code
	 * 
	 * @param strCode
	 * @param strDesc
	 * @return String
	 */
	public String getDescCode(final String strCode, final String strDesc) {
		final String sql = getQuery("OCUVWARN_GET_DESCCODE");
		final String returnList = namedParameterJdbcTemplate.queryForObject(sql,
				createParams("domain", strCode, "code", strDesc), String.class);
		return returnList;
	}

}

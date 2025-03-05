package net.syscon.s4.inst.visitsmanagement.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlInOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.Dual;
import net.syscon.s4.im.beans.PersonProfiles;
import net.syscon.s4.im.beans.ProfileCodes;
import net.syscon.s4.im.beans.ProfileTypes;
import net.syscon.s4.inst.visitsmanagement.OcuperprRepository;
import oracle.jdbc.OracleTypes;

/**
 * Class OcuperprRepositoryImpl
 */
@Repository
public class OcuperprRepositoryImpl extends RepositoryBase implements OcuperprRepository {

	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OcuperprRepositoryImpl.class.getName());

	private final Map<String, FieldMapper> personProfilesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("PROFILE_CODE", new FieldMapper("profileCode"))
			.put("PERSON_PROFILE_ID", new FieldMapper("personProfileId"))
			.put("PROFILE_TYPE", new FieldMapper("profileType"))
			.put("CREATE_USER_ID", new FieldMapper("createUserId"))
			.put("SEAL_FLAG", new FieldMapper("sealFlag"))
			.put("CREATE_DATETIME", new FieldMapper("createDatetime"))
			.put("MODIFY_USER_ID", new FieldMapper("modifyUserId"))
			.put("DISPLAY_SEQ", new FieldMapper("displaySeq"))
			.put("MODIFY_DATETIME", new FieldMapper("modifyDatetime"))
			.put("PROFILE_COMMENT", new FieldMapper("profileComment"))
			.put("PERSON_ID", new FieldMapper("personId"))
			.put("CODE_VALUE_TYPE", new FieldMapper("codeValueType"))
			.build();
	private final Map<String, FieldMapper> profileTypesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DESCRIPTION", new FieldMapper("description"))
			.put("PROFILE_TYPE", new FieldMapper("profileType"))
			.put("PROFILE_CODE", new FieldMapper("profileCode")).build();
	private final Map<String, FieldMapper> dualMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DESCRIPTION", new FieldMapper("description")).build();

	/**
	 * Creates new OcuperprRepositoryImpl class Object
	 */
	public OcuperprRepositoryImpl() {
		// OcuperprRepositoryImpl
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            PersonProfiles
	 *
	 * @return List<PersonProfiles>
	 *
	 * @
	 */
	public List<PersonProfiles> profilesExecuteQuery(final PersonProfiles objSearchDao) {
		final String sql = getQuery("OCUPERPR_PROFILES_FIND_PERSON_PROFILES");
		final RowMapper<PersonProfiles> PersonProfilesRowMapper = Row2BeanRowMapper.makeMapping(sql,
				PersonProfiles.class, personProfilesMapping);
		List<PersonProfiles> returnList = new ArrayList<PersonProfiles>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams("PERSONID",objSearchDao.getPersonId()), PersonProfilesRowMapper);
		} catch (Exception e) {
			logger.error("profilesExecuteQuery", e);
		}
		return returnList;
	}


	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstPersonProfiles
	 *            List<PersonProfiles>
	 *
	 * @
	 */
	public Integer profilesUpdatePersonProfiles(final List<PersonProfiles> lstPersonProfiles) {
		final String sql = getQuery("OCUPERPR_PROFILES_UPDATE_PERSON_PROFILES");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final PersonProfiles personProfiles : lstPersonProfiles) {
			parameters.add(new BeanPropertySqlParameterSource(personProfiles));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstPersonProfiles.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<ProfileCodes>
	 */
	public List<ProfileCodes> rgProfileCodeRecordGroup(final String profileType) {
		final String sql = getQuery("OCUPERPR_FIND_RGPROFILECODE");
		final RowMapper<ProfileCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ProfileCodes.class, profileTypesMapping);
		List<ProfileCodes> returnList = new ArrayList<ProfileCodes>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams("PROFILETYPE",profileType), mRowMapper);
		} catch (Exception e) {
			logger.error("rgProfileCodeRecordGroup", e);
		}
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * profilesPreInsert
	 *
	 * @param params
	 *
	 */
	public Object profilesPreInsert(final Dual paramBean) {
		final String sql = getQuery("OCUPERPR_PROFILES_PREINSERT");
		final RowMapper<Dual> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, Dual.class, dualMapping);
		Dual returnObj = new Dual();
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
		} catch (Exception e) {
			logger.error("profilesPreInsert", e);
		}
		return returnObj;
	}

	@Override
	public ProfileTypes getProfileTypeDesc(final PersonProfiles personProfiles) {
		final String sql = getQuery("OCUPERPR_PROFILES_TYPES");
		final RowMapper<ProfileTypes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, ProfileTypes.class,
				profileTypesMapping);
		ProfileTypes returnObj = new ProfileTypes();
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("PROFILETYPE", personProfiles.getProfileType()), columnRowMapper);
		} catch (Exception e) {
			returnObj = new ProfileTypes();
		}
		return returnObj;
	}

	@Override
	public ProfileCodes getProfileCodeDesc(final PersonProfiles personProfiles) {
		final String sql = getQuery("OCUPERPR_PROFILES_CODES");
		final RowMapper<ProfileCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, ProfileCodes.class,
				profileTypesMapping);
		ProfileCodes returnObj = new ProfileCodes();
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("PROFILE_CODE", personProfiles.getProfileCode()), columnRowMapper);
		} catch (Exception e) {
			returnObj = new ProfileCodes();
		}
		return returnObj;
	}

	public List<PersonProfiles> insertprofilesTypes(final Integer personId) {
		final List<PersonProfiles> returnList = new ArrayList<PersonProfiles>();
		try{
			 Map<String, Object> inParamMap = new HashMap<String, Object>();
			SqlParameter[] sqlParameters = new SqlParameter[1];
			sqlParameters = new SqlParameter[] {
					new SqlParameter("P_PERSON_ID", OracleTypes.VARCHAR),
					};
			SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
					.withCatalogName("TAG_PERSON_SEARCH").withProcedureName("INSERT_PERSON_PROFILE_TYPES").declareParameters(sqlParameters);
			inParamMap.put("P_PERSON_ID", personId);
			 SqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
				simpleJDBCCall.execute(inParameter);
		}catch(Exception e){
			logger.error("Exception : ",e);
		}
		return returnList;
	}
	
}

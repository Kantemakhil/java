package net.syscon.s4.inst.demographicsbiometrics.impl;

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
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.Dual;
import net.syscon.s4.im.beans.OffenderProfileDetails;
import net.syscon.s4.im.beans.Offenders;
import net.syscon.s4.im.beans.ProfileCodes;
import net.syscon.s4.im.beans.ProfileTypes;
import net.syscon.s4.im.beans.ReferenceCodes;
import net.syscon.s4.inst.demographicsbiometrics.OidpinfoRepository;

/**
 * Class OidpinfoRepositoryImpl
 */
@Repository
public class OidpinfoRepositoryImpl extends RepositoryBase implements OidpinfoRepository {
	
	public static final String PARAM = "QUERY";
	
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OidpinfoRepositoryImpl.class.getName());
	
	private final Map<String, FieldMapper> offendersMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("OFFENDER_ID_DISPLAY",    new FieldMapper("offenderIdDisplay"))
			.put("PARENT_OFFENDER_ID",     new FieldMapper("parentOffenderId"))
			.put("SUFFIX",                 new FieldMapper("suffix"))
			.put("OFFENDER_NAME_SEQ",      new FieldMapper("offenderNameSeq"))
			.put("LAST_NAME_SOUNDEX",      new FieldMapper("lastNameSoundex"))
			.put("ROOT_OFFENDER_ID",       new FieldMapper("rootOffenderId"))
			.put("ADD_INFO_CODE",          new FieldMapper("addInfoCode"))
			.put("MIDDLE_NAME_2",          new FieldMapper("middleName2"))
			.put("LAST_NAME_ALPHA_KEY",    new FieldMapper("lastNameAlphaKey"))
			.put("MIDDLE_NAME",            new FieldMapper("middleName"))
			.put("OFFENDER_ID",            new FieldMapper("offenderId"))
			.put("BIRTH_STATE",            new FieldMapper("birthState"))
			.put("LAST_NAME",              new FieldMapper("lastName"))
			.put("BIRTH_COUNTY",           new FieldMapper("birthCounty"))
			.put("CASELOAD_TYPE",          new FieldMapper("caseloadType"))
			.put("UNIQUE_OBLIGATION_FLAG", new FieldMapper("uniqueObligationFlag"))
			.put("ID_SOURCE_CODE",         new FieldMapper("idSourceCode"))
			.put("REMARK_CODE",            new FieldMapper("remarkCode"))
			.put("SEAL_FLAG",              new FieldMapper("sealFlag"))
			.put("CREATE_DATETIME",        new FieldMapper("createDateTime"))
			.put("MODIFY_DATETIME",        new FieldMapper("modifyDateTime"))
			.put("SUSPENDED_FLAG",         new FieldMapper("suspendedFlag"))
			.put("NAME_TYPE",              new FieldMapper("nameType"))
			.put("OFFENDER_BOOK_ID",       new FieldMapper("offenderBookId"))
			.put("COMMENT_TEXT",           new FieldMapper("commentText"))
			.put("CREATE_USER_ID",         new FieldMapper("createUserId"))
			.put("PROFILE_SEQ",            new FieldMapper("profileSeq"))
			.put("SUSPENDED_DATE",         new FieldMapper("suspendedDate"))
			.put("MODIFY_USER_ID",         new FieldMapper("modifyUserId"))
			.put("LIST_SEQ",               new FieldMapper("listSeq"))
			.put("BIRTH_COUNTRY_CODE",     new FieldMapper("birthCountryCode"))
			.put("MIDDLE_NAME_KEY",        new FieldMapper("middleNameKey"))
			.put("NAME_SEQUENCE",          new FieldMapper("nameSequence"))
			.put("TITLE",                  new FieldMapper("title"))
			.put("SEX_CODE",               new FieldMapper("sexCode"))
			.put("BIRTH_PLACE",            new FieldMapper("birthPlace"))
			.put("LAST_NAME_KEY",          new FieldMapper("lastNameKey"))
			.put("ALIAS_OFFENDER_ID",      new FieldMapper("aliasOffenderId"))
			.put("RACE_CODE",              new FieldMapper("raceCode"))
			.put("ALIAS_NAME_TYPE",        new FieldMapper("aliasNameType"))
			.put("CREATE_DATE",            new FieldMapper("createDate"))
			.put("FIRST_NAME_KEY",         new FieldMapper("firstNameKey"))
			.put("BIRTH_DATE",             new FieldMapper("birthDate"))
			.put("FIRST_NAME",             new FieldMapper("firstName"))
			.put("AGE",                    new FieldMapper("age"))
			.build();
	private final Map<String, FieldMapper> referenceCodesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("BIRTH_COUNTRY_CODE",     new FieldMapper("code"))
			.put("DSP_LIST_SEQ",           new FieldMapper("listSeq"))
			.put("DSP_DESCRIPTION",        new FieldMapper("description"))
			.put("MODE",                   new FieldMapper("mode"))
			.put("BIRTH_STATE",            new FieldMapper("birthState"))
			.build();
	private final Map<String, FieldMapper> profileCodesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DSP_PROFILE_TYPE",       new FieldMapper("profileType"))
			.put("DSP_LIST_SEQ",           new FieldMapper("listSeq"))
			.put("PROFILE_TYPE",           new FieldMapper("profileType"))
			.put("MODE",                   new FieldMapper("mode"))
			.put("DSP_DESCRIPTION2",       new FieldMapper("dspDescription2"))
			.put("PFL_CODE.DESCRIPTION",   new FieldMapper("pflCode.description"))
			.put("PROFILE_CODE",           new FieldMapper("profileCode"))
			.put("PFL_CODE.PROFILE_CODE",  new FieldMapper("pflCode.profileCode"))
			.put("ACTIVE_CODE", 		   new FieldMapper("activeFlag"))
			.build();
	private final Map<String, FieldMapper> offenderProfileDetailsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("OFFENDER_ID_DISPLAY",    new FieldMapper("offenderIdDisplay"))
			.put("PROFILE_CODE",           new FieldMapper("profileCode"))
			.put("PARENT_OFFENDER_ID",     new FieldMapper("parentOffenderId"))
			.put("SUFFIX",                 new FieldMapper("suffix"))
			.put("OFFENDER_NAME_SEQ",      new FieldMapper("offenderNameSeq"))
			.put("LAST_NAME_SOUNDEX",      new FieldMapper("lastNameSoundex"))
			.put("ROOT_OFFENDER_ID",       new FieldMapper("rootOffenderId"))
			.put("ADD_INFO_CODE",          new FieldMapper("addInfoCode"))
			.put("MIDDLE_NAME_2",          new FieldMapper("middleName2"))
			.put("LAST_NAME_ALPHA_KEY",    new FieldMapper("lastNameAlphaKey"))
			.put("MIDDLE_NAME",            new FieldMapper("middleName"))
			.put("OFFENDER_ID",            new FieldMapper("offenderId"))
			.put("BIRTH_STATE",            new FieldMapper("birthState"))
			.put("LAST_NAME",              new FieldMapper("lastName"))
			.put("BIRTH_COUNTY",           new FieldMapper("birthCounty"))
			.put("CASELOAD_TYPE",          new FieldMapper("caseloadType"))
			.put("UNIQUE_OBLIGATION_FLAG", new FieldMapper("uniqueObligationFlag"))
			.put("ID_SOURCE_CODE",         new FieldMapper("idSourceCode"))
			.put("REMARK_CODE",            new FieldMapper("remarkCode"))
			.put("SEAL_FLAG",              new FieldMapper("sealFlag"))
			.put("CREATE_DATETIME",        new FieldMapper("createDatetime"))
			.put("MODIFY_DATETIME",        new FieldMapper("modifyDatetime"))
			.put("SUSPENDED_FLAG",         new FieldMapper("suspendedFlag"))
			.put("NAME_TYPE",              new FieldMapper("nameType"))
			.put("OFFENDER_BOOK_ID",       new FieldMapper("offenderBookId"))
			.put("COMMENT_TEXT",           new FieldMapper("commentText"))
			.put("CREATE_USER_ID",         new FieldMapper("createUserId"))
			.put("PROFILE_TYPE",           new FieldMapper("profileType"))
			.put("PROFILE_SEQ",            new FieldMapper("profileSeq"))
			.put("SUSPENDED_DATE",         new FieldMapper("suspendedDate"))
			.put("MODIFY_USER_ID",         new FieldMapper("modifyUserId"))
			.put("LIST_SEQ",               new FieldMapper("listSeq"))
			.put("BIRTH_COUNTRY_CODE",     new FieldMapper("birthCountryCode"))
			.put("MIDDLE_NAME_KEY",        new FieldMapper("middleNameKey"))
			.put("NAME_SEQUENCE",          new FieldMapper("nameSequence"))
			.put("TITLE",                  new FieldMapper("title"))
			.put("SEX_CODE",               new FieldMapper("sexCode"))
			.put("BIRTH_PLACE",            new FieldMapper("birthPlace"))
			.put("LAST_NAME_KEY",          new FieldMapper("lastNameKey"))
			.put("ALIAS_OFFENDER_ID",      new FieldMapper("aliasOffenderId"))
			.put("RACE_CODE",              new FieldMapper("raceCode"))
			.put("ALIAS_NAME_TYPE",        new FieldMapper("aliasNameType"))
			.put("CREATE_DATE",            new FieldMapper("createDate"))
			.put("FIRST_NAME_KEY",         new FieldMapper("firstNameKey"))
			.put("BIRTH_DATE",             new FieldMapper("birthDate"))
			.put("FIRST_NAME",             new FieldMapper("firstName"))
			.put("AGE",                    new FieldMapper("age"))
			.build();
	private final Map<String, FieldMapper> profileTypesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("PROFILE_TYPE",            new FieldMapper("profileType"))
			.put("CODE_VALUE_TYPE LV_TEMP", new FieldMapper("codeValueType"))
			.put("DESCRIPTION",             new FieldMapper("description"))
			.put("LV_TEMP",                 new FieldMapper("lvTemp"))
			.build();
	private final Map<String, FieldMapper> sysDualMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("SYSDATE",                 new FieldMapper("sysDate"))
			.put("USER",                    new FieldMapper("user"))
			.build();

	/**
	 * Creates new OidpinfoRepositoryImpl class Object
	 */
	public OidpinfoRepositoryImpl() {
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<RecordGroup>
	 */
	public List<ReferenceCodes> cgfkOffNameDspDescriptionRgroup() {
		final String sql = getQuery("OIDPINFO_FIND_CGFKOFFNAMEDSPDESCRIPTION");
		final RowMapper<ReferenceCodes> referenceCodeRowMapper = Row2BeanRowMapper.makeMapping(sql,
				ReferenceCodes.class, referenceCodesMapping);
		List<ReferenceCodes> refList = new ArrayList<ReferenceCodes>();
		try {
			refList = namedParameterJdbcTemplate.query(sql, createParams("MODE", PARAM), referenceCodeRowMapper);
		} catch (Exception e) {
			logger.error(e);
		}
		return refList;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<RecordGroup>
	 */
	public List<ProfileCodes> cgfkOffPdDspDescriptionRgroup(final String profileType) {
		final String sql = getQuery("OIDPINFO_FIND_CGFKOFFPDDSPDESCRIPTION");
		final RowMapper<ProfileCodes> referenceCodeRowMapper = Row2BeanRowMapper.makeMapping(sql, ProfileCodes.class,
				profileCodesMapping);
		List<ProfileCodes> refList = new ArrayList<ProfileCodes>();
		try {
			refList = namedParameterJdbcTemplate.query(sql, createParams("PROFILETYPE", profileType),
					referenceCodeRowMapper);
		} catch (Exception e) {
			logger.error(e);
		}
		return refList;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<RecordGroup>
	 */
	public List<ReferenceCodes> rgBirthStateRecordGroup() {
		final String sql = getQuery("OIDPINFO_FIND_RGBIRTHSTATE");
		final RowMapper<ReferenceCodes> referenceCodeRowMapper = Row2BeanRowMapper.makeMapping(sql,
				ReferenceCodes.class, referenceCodesMapping);
		List<ReferenceCodes> refList = new ArrayList<ReferenceCodes>();
		try {
			refList = namedParameterJdbcTemplate.query(sql, createParams("MODE", PARAM), referenceCodeRowMapper);
		} catch (Exception e) {
			logger.error(e);
		}
		return refList;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            Offenders
	 *
	 * @return List<Offenders>
	 *
	 */
	public List<Offenders> offNameSearchOffenders(final Offenders offenders) {
		final String sql = getQuery("OIDPINFO_OFFNAME_FIND_OFFENDERS");
		String preparedSql = null;
		final StringBuffer sqlQuery = new StringBuffer();
		final MapSqlParameterSource params = new MapSqlParameterSource();
		sqlQuery.append(sql);
		if (offenders != null) {
			sqlQuery.append(" where ");
			if (offenders.getOffenderId() != 0 && offenders.getOffenderId() != null) {
				sqlQuery.append("OFFENDER_ID = :OFFENDER_ID" + " and ");
				params.addValue("OFFENDER_ID", offenders.getOffenderId());
			}
		}
		preparedSql = sqlQuery.toString().trim();
		if (preparedSql.endsWith("where")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 5);
		}
		if (preparedSql.endsWith("and")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 3);
		}
		final RowMapper<Offenders> OffendersRowMapper = Row2BeanRowMapper.makeMapping(sql, Offenders.class,
				offendersMapping);
		final ArrayList<Offenders> returnList = (ArrayList<Offenders>) namedParameterJdbcTemplate.query(preparedSql,
				params, OffendersRowMapper);
		return returnList;

	}

	/**
	 * @param
	 *
	 */
	public int preInsert() {
		return 0;
	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstOffenders
	 *            List<Offenders>
	 *
	 */
	public Integer offNameUpdateOffenders(final List<Offenders> lstOffenders) {
		final String sql = getQuery("OIDPINFO_OFFNAME_UPDATE_OFFENDERS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final Offenders offenders : lstOffenders) {
			parameters.add(new BeanPropertySqlParameterSource(offenders));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstOffenders.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            OffenderProfileDetails
	 *
	 * @return List<OffenderProfileDetails>
	 *
	 */
	public List<OffenderProfileDetails> offPdSearchOffenderProfileDetails(final OffenderProfileDetails objSearchDao) {
		final String sql = getQuery("OIDPINFO_OFFPD_FIND_OFFENDER_PROFILE_DETAILS");
		final RowMapper<OffenderProfileDetails> OffenderProfileDetailsRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderProfileDetails.class, offenderProfileDetailsMapping);
		final ArrayList<OffenderProfileDetails> returnList = (ArrayList<OffenderProfileDetails>) namedParameterJdbcTemplate
				.query(sql, createParams("OFFENDERBOOKID",objSearchDao.getOffenderBookId()),
						OffenderProfileDetailsRowMapper);
		return returnList;
	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstOffenderProfileDetails
	 *            List<OffenderProfileDetails>
	 *
	 */
	public Integer offPdUpdateOffenderProfileDetails(final List<OffenderProfileDetails> lstOffenderProfileDetails) {
		final String sql = getQuery("OIDPINFO_OFFPD_UPDATE_OFFENDER_PROFILE_DETAILS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffenderProfileDetails offenderProfileDetails : lstOffenderProfileDetails) {
			parameters.add(new BeanPropertySqlParameterSource(offenderProfileDetails));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));

		if (lstOffenderProfileDetails.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * offBkgOnCheckDeleteMasteroffPdCur
	 *
	 * @param params
	 *
	 */
	public List<Object> offBkgOnCheckDeleteMasteroffPdCur(final OffenderProfileDetails paramBean) {
		final String sql = getQuery("OIDPINFO_OFF_BKG_ONCHECKDELETEMASTER_OFF_PD_CUR");
		final List<Object> returnObj = (List<Object>) namedParameterJdbcTemplate.queryForList(sql,
				createParams("OFFENDERBOOKID", paramBean.getOffenderBookId()), Object.class);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * offNamePostQuerycOffBirthState
	 *
	 * @param params
	 *
	 */
	public ReferenceCodes offNamePostQuerycOffBirthState(final ReferenceCodes paramBean) {
		final String sql = getQuery("OIDPINFO_OFF_NAME_POSTQUERY_C_OFF_BIRTH_STATE");
		final RowMapper<ReferenceCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				referenceCodesMapping);
		final ReferenceCodes returnObj = namedParameterJdbcTemplate.queryForObject(sql,
				createParams("code", paramBean.getCode()), columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * profileCodePostChange
	 *
	 * @param params
	 *
	 */
	public ProfileTypes profileCodePostChange(final ProfileTypes paramBean) {
		final String sql = getQuery("OIDPINFO_PROFILE_CODE_POSTCHANGE");
		final RowMapper<ProfileTypes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, ProfileTypes.class,
				profileTypesMapping);
		final ProfileTypes returnObj = namedParameterJdbcTemplate.queryForObject(sql,
				createParams("PROFILE_TYPE", paramBean.getProfileType()), columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * dspDescriptionWhenValidateItemprofileTypes
	 *
	 * @param params
	 *
	 */
	public ProfileTypes dspDescriptionWhenValidateItemprofileTypes(final ProfileTypes paramBean) {
		final String sql = getQuery("OIDPINFO_DSP_DESCRIPTION_WHENVALIDATEITEM_PROFILE_TYPES_C");
		final RowMapper<ProfileTypes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, ProfileTypes.class,
				profileTypesMapping);
		final ProfileTypes returnObj = namedParameterJdbcTemplate.queryForObject(sql,
				createParams("PROFILE_TYPE", paramBean.getProfileType()), columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfkchkOffNameOffRefCodec
	 *
	 * @param params
	 *
	 */
	public ReferenceCodes cgfkchkOffNameOffRefCodec(final ReferenceCodes paramBean) {
		final String sql = getQuery("OIDPINFO_CGFKCHK_OFF_NAME_OFF_REF_CODE_C");
		final RowMapper<ReferenceCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				referenceCodesMapping);
		final ReferenceCodes returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(paramBean),
				columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfklkpOffNameOffRefCodec
	 *
	 * @param params
	 *
	 */
	public ReferenceCodes cgfklkpOffNameOffRefCodec(final ReferenceCodes paramBean) {
		final String sql = getQuery("OIDPINFO_CGFKLKP_OFF_NAME_OFF_REF_CODE_C");
		final RowMapper<ReferenceCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				referenceCodesMapping);
		final ReferenceCodes returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(paramBean),
				columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfkchkOffPdOffPdPflTypc
	 *
	 * @param params
	 *
	 */
	public ProfileTypes cgfkchkOffPdOffPdPflTypc(final OffenderProfileDetails paramBean) {
		final String sql = getQuery("OIDPINFO_CGFKCHK_OFF_PD_OFF_PD_PFL_TYP_C");
		final RowMapper<ProfileTypes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, ProfileTypes.class,
				profileTypesMapping);
		ProfileTypes returnObj = new ProfileTypes();
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("PROFILETYPE", paramBean.getProfileType()), columnRowMapper);
		} catch (EmptyResultDataAccessException e) {
//			logger.error(e);
		}
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfkchkOffPdOffPdPflCodc
	 *
	 * @param params
	 *
	 */
	public ProfileCodes cgfkchkOffPdOffPdPflCodc(final OffenderProfileDetails paramBean) {
		final String sql = getQuery("OIDPINFO_CGFKCHK_OFF_PD_OFF_PD_PFL_COD_C");
		final RowMapper<ProfileCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, ProfileCodes.class,
				profileCodesMapping);
		final ProfileCodes returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams("PROFILECODE",
				paramBean.getProfileCode(), "MODE", PARAM, "PROFILETYPE", paramBean.getProfileType()), columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfklkpOffPdOffPdPflCod
	 *
	 * @param params
	 *
	 */
	public ProfileTypes cgfklkpOffPdOffPdPflCod(final ProfileTypes paramBean) {
		final String sql = getQuery("OIDPINFO_CGFKLKP_OFF_PD_OFF_PD_PFL_COD_");
		final RowMapper<ProfileTypes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, ProfileTypes.class,
				profileTypesMapping);
		final ProfileTypes returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(paramBean),
				columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfklkpOffPdOffPdPflCodc
	 *
	 * @param params
	 *
	 */
	public ProfileCodes cgfklkpOffPdOffPdPflCodc(final ProfileCodes paramBean) {
		final String sql = getQuery("OIDPINFO_CGFKLKP_OFF_PD_OFF_PD_PFL_COD_C");
		final RowMapper<ProfileCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, ProfileCodes.class,
				profileCodesMapping);
		final ProfileCodes returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams("DSPDESCRIPTION2",paramBean.getDescription(),
				"PROFILETYPE",paramBean.getProfileType()),
				columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgwhenNewFormInstancec
	 * 
	 * @param params
	 *
	 */

	public List<Dual> cgwhenNewFormInstancec() {
		final String sql = getQuery("OIDPINFO_CGWHEN_NEW_FORM_INSTANCE_C");
		final RowMapper<Dual> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, Dual.class, sysDualMapping);
		final List<Dual> returnList = namedParameterJdbcTemplate.query(sql, columnRowMapper);
		return returnList;
	}

}

package net.syscon.s4.inst.demographicsbiometrics.impl;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlInOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.OffenderIdentifyingMark;
import net.syscon.s4.common.beans.OffenderProfiles;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.Images;
import net.syscon.s4.im.beans.OffenderPhysicalAttributes;
import net.syscon.s4.im.beans.OffenderProfileDetails;
import net.syscon.s4.im.beans.Offenders;
import net.syscon.s4.im.beans.ProfileCodes;
import net.syscon.s4.im.beans.ProfileTypes;
import net.syscon.s4.inst.demographicsbiometrics.OidpidenRepository;
import oracle.jdbc.OracleTypes;

@Repository
public class OidpidenRepositoryImpl extends RepositoryBase implements OidpidenRepository {
	private static Logger logger = LogManager.getLogger(OidpidenRepositoryImpl.class.getName());


	private final Map<String, FieldMapper> offenderIdentifyingMarksMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("OFFENDER_ID_DISPLAY", new FieldMapper("offenderIdDisplay"))
			.put("PARENT_OFFENDER_ID", new FieldMapper("parentOffenderId"))
			.put("ROOT_OFFENDER_ID", new FieldMapper("rootOffenderId"))
			.put("ADD_INFO_CODE", new FieldMapper("addInfoCode")).put("MIDDLE_NAME_2", new FieldMapper("middleName2"))
			.put("LAST_NAME_ALPHA_KEY", new FieldMapper("lastNameAlphaKey"))
			.put("MIDDLE_NAME", new FieldMapper("middleName")).put("HEIGHT_CM", new FieldMapper("heightCm"))
			.put("BIRTH_COUNTY", new FieldMapper("birthCounty")).put("CASELOAD_TYPE", new FieldMapper("caseloadType"))
			.put("UNIQUE_OBLIGATION_FLAG", new FieldMapper("uniqueObligationFlag"))
			.put("SEAL_FLAG", new FieldMapper("sealFlag")).put("CREATE_DATETIME", new FieldMapper("createDatetime"))
			.put("NAME_TYPE", new FieldMapper("nameType")).put("OFFENDER_BOOK_ID", new FieldMapper("offenderBookId"))
			.put("CREATE_USER_ID", new FieldMapper("createUserId")).put("PROFILE_TYPE", new FieldMapper("profileType"))
			.put("BODY_PART_CODE", new FieldMapper("bodyPartCode")).put("LIST_SEQ", new FieldMapper("listSeq"))
			.put("HEIGHT_FT", new FieldMapper("heightFt")).put("NAME_SEQUENCE", new FieldMapper("nameSequence"))
			.put("BIRTH_PLACE", new FieldMapper("birthPlace")).put("RACE_CODE", new FieldMapper("raceCode"))
			.put("AGE", new FieldMapper("age")).put("PROFILE_CODE", new FieldMapper("profileCode"))
			.put("PART_ORIENTATION_CODE", new FieldMapper("partOrientationCode"))
			.put("SUFFIX", new FieldMapper("suffix")).put("MARK_TYPE", new FieldMapper("markType"))
			.put("OFFENDER_NAME_SEQ", new FieldMapper("offenderNameSeq"))
			.put("LAST_NAME_SOUNDEX", new FieldMapper("lastNameSoundex"))
			.put("ID_MARK_SEQ", new FieldMapper("idMarkSeq")).put("SIDE_CODE", new FieldMapper("sideCode"))
			.put("OFFENDER_ID", new FieldMapper("offenderId")).put("BIRTH_STATE", new FieldMapper("birthState"))
			.put("LAST_NAME", new FieldMapper("lastName")).put("HEIGHT_IN", new FieldMapper("heightIn"))
			.put("ID_SOURCE_CODE", new FieldMapper("idSourceCode")).put("REMARK_CODE", new FieldMapper("remarkCode"))
			.put("MODIFY_DATETIME", new FieldMapper("modifyDatetime"))
			.put("SUSPENDED_FLAG", new FieldMapper("suspendedFlag")).put("WEIGHT_KG", new FieldMapper("weightKg"))
			.put("COMMENT_TEXT", new FieldMapper("commentText")).put("PROFILE_SEQ", new FieldMapper("profileSeq"))
			.put("SUSPENDED_DATE", new FieldMapper("suspendedDate"))
			.put("MODIFY_USER_ID", new FieldMapper("modifyUserId"))
			.put("BIRTH_COUNTRY_CODE", new FieldMapper("birthCountryCode"))
			.put("MIDDLE_NAME_KEY", new FieldMapper("middleNameKey")).put("TITLE", new FieldMapper("title"))
			.put("SEX_CODE", new FieldMapper("sexCode")).put("LAST_NAME_KEY", new FieldMapper("lastNameKey"))
			.put("ALIAS_OFFENDER_ID", new FieldMapper("aliasOffenderId"))
			.put("ALIAS_NAME_TYPE", new FieldMapper("aliasNameType"))
			.put("ATTRIBUTE_SEQ", new FieldMapper("attributeSeq")).put("CREATE_DATE", new FieldMapper("createDate"))
			.put("FIRST_NAME_KEY", new FieldMapper("firstNameKey")).put("WEIGHT_LBS", new FieldMapper("weightLbs"))
			.put("BIRTH_DATE", new FieldMapper("birthDate")).put("FIRST_NAME", new FieldMapper("firstName")).build();
	private final Map<String, FieldMapper> offendersMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("OFFENDER_ID_DISPLAY", new FieldMapper("offenderIdDisplay"))
			.put("PARENT_OFFENDER_ID", new FieldMapper("parentOffenderId"))
			.put("ROOT_OFFENDER_ID", new FieldMapper("rootOffenderId"))
			.put("ADD_INFO_CODE", new FieldMapper("addInfoCode")).put("MIDDLE_NAME_2", new FieldMapper("middleName2"))
			.put("LAST_NAME_ALPHA_KEY", new FieldMapper("lastNameAlphaKey"))
			.put("MIDDLE_NAME", new FieldMapper("middleName")).put("HEIGHT_CM", new FieldMapper("heightCm"))
			.put("BIRTH_COUNTY", new FieldMapper("birthCounty")).put("CASELOAD_TYPE", new FieldMapper("caseloadType"))
			.put("UNIQUE_OBLIGATION_FLAG", new FieldMapper("uniqueObligationFlag"))
			.put("SEAL_FLAG", new FieldMapper("sealFlag")).put("CREATE_DATETIME", new FieldMapper("createDateTime"))
			.put("NAME_TYPE", new FieldMapper("nameType")).put("OFFENDER_BOOK_ID", new FieldMapper("offenderBookId"))
			.put("CREATE_USER_ID", new FieldMapper("createUserId")).put("PROFILE_TYPE", new FieldMapper("profileType"))
			.put("BODY_PART_CODE", new FieldMapper("bodyPartCode")).put("LIST_SEQ", new FieldMapper("listSeq"))
			.put("HEIGHT_FT", new FieldMapper("heightFt")).put("NAME_SEQUENCE", new FieldMapper("nameSequence"))
			.put("BIRTH_PLACE", new FieldMapper("birthPlace")).put("RACE_CODE", new FieldMapper("raceCode"))
			.put("AGE", new FieldMapper("age")).put("PROFILE_CODE", new FieldMapper("profileCode"))
			.put("PART_ORIENTATION_CODE", new FieldMapper("partOrientationCode"))
			.put("SUFFIX", new FieldMapper("suffix")).put("MARK_TYPE", new FieldMapper("markType"))
			.put("OFFENDER_NAME_SEQ", new FieldMapper("offenderNameSeq"))
			.put("LAST_NAME_SOUNDEX", new FieldMapper("lastNameSoundex"))
			.put("ID_MARK_SEQ", new FieldMapper("idMarkSeq")).put("SIDE_CODE", new FieldMapper("sideCode"))
			.put("OFFENDER_ID", new FieldMapper("offenderId")).put("BIRTH_STATE", new FieldMapper("birthState"))
			.put("LAST_NAME", new FieldMapper("lastName")).put("HEIGHT_IN", new FieldMapper("heightIn"))
			.put("ID_SOURCE_CODE", new FieldMapper("idSourceCode")).put("REMARK_CODE", new FieldMapper("remarkCode"))
			.put("MODIFY_DATETIME", new FieldMapper("modifyDateTime"))
			.put("SUSPENDED_FLAG", new FieldMapper("suspendedFlag")).put("WEIGHT_KG", new FieldMapper("weightKg"))
			.put("COMMENT_TEXT", new FieldMapper("commentText")).put("PROFILE_SEQ", new FieldMapper("profileSeq"))
			.put("SUSPENDED_DATE", new FieldMapper("suspendedDate"))
			.put("MODIFY_USER_ID", new FieldMapper("modifyUserId"))
			.put("BIRTH_COUNTRY_CODE", new FieldMapper("birthCountryCode"))
			.put("MIDDLE_NAME_KEY", new FieldMapper("middleNameKey")).put("TITLE", new FieldMapper("title"))
			.put("SEX_CODE", new FieldMapper("sexCode")).put("LAST_NAME_KEY", new FieldMapper("lastNameKey"))
			.put("ALIAS_OFFENDER_ID", new FieldMapper("aliasOffenderId"))
			.put("ALIAS_NAME_TYPE", new FieldMapper("aliasNameType"))
			.put("ATTRIBUTE_SEQ", new FieldMapper("attributeSeq")).put("CREATE_DATE", new FieldMapper("createDate"))
			.put("FIRST_NAME_KEY", new FieldMapper("firstNameKey")).put("WEIGHT_LBS", new FieldMapper("weightLbs"))
			.put("BIRTH_DATE", new FieldMapper("birthDate")).put("FIRST_NAME", new FieldMapper("firstName")).build();
	private final Map<String, FieldMapper> profileCodesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DSP_LIST_SEQ", new FieldMapper("listSeq"))
			.put("DSP_PROFILE_TYPE", new FieldMapper("profileType"))
			.put("DSP_DESCRIPTION2", new FieldMapper("description"))
			.put("PROFILE_CODE", new FieldMapper("profileCode"))
			.put("ACTIVE_FLAG", new FieldMapper("activeFlag"))
			.build();
	private final Map<String, FieldMapper> offenderPhysicalAttributesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("OFFENDER_ID_DISPLAY", new FieldMapper("offenderIdDisplay"))
			.put("PARENT_OFFENDER_ID", new FieldMapper("parentOffenderId"))
			.put("ROOT_OFFENDER_ID", new FieldMapper("rootOffenderId"))
			.put("ADD_INFO_CODE", new FieldMapper("addInfoCode")).put("MIDDLE_NAME_2", new FieldMapper("middleName2"))
			.put("LAST_NAME_ALPHA_KEY", new FieldMapper("lastNameAlphaKey"))
			.put("MIDDLE_NAME", new FieldMapper("middleName")).put("HEIGHT_CM", new FieldMapper("heightCm"))
			.put("BIRTH_COUNTY", new FieldMapper("birthCounty")).put("CASELOAD_TYPE", new FieldMapper("caseloadType"))
			.put("UNIQUE_OBLIGATION_FLAG", new FieldMapper("uniqueObligationFlag"))
			.put("SEAL_FLAG", new FieldMapper("sealFlag")).put("CREATE_DATETIME", new FieldMapper("createDatetime"))
			.put("NAME_TYPE", new FieldMapper("nameType")).put("OFFENDER_BOOK_ID", new FieldMapper("offenderBookId"))
			.put("CREATE_USER_ID", new FieldMapper("createUserId")).put("PROFILE_TYPE", new FieldMapper("profileType"))
			.put("BODY_PART_CODE", new FieldMapper("bodyPartCode")).put("LIST_SEQ", new FieldMapper("listSeq"))
			.put("HEIGHT_FT", new FieldMapper("heightFt")).put("NAME_SEQUENCE", new FieldMapper("nameSequence"))
			.put("BIRTH_PLACE", new FieldMapper("birthPlace")).put("RACE_CODE", new FieldMapper("raceCode"))
			.put("AGE", new FieldMapper("age")).put("PROFILE_CODE", new FieldMapper("profileCode"))
			.put("PART_ORIENTATION_CODE", new FieldMapper("partOrientationCode"))
			.put("SUFFIX", new FieldMapper("suffix")).put("MARK_TYPE", new FieldMapper("markType"))
			.put("OFFENDER_NAME_SEQ", new FieldMapper("offenderNameSeq"))
			.put("LAST_NAME_SOUNDEX", new FieldMapper("lastNameSoundex"))
			.put("ID_MARK_SEQ", new FieldMapper("idMarkSeq")).put("SIDE_CODE", new FieldMapper("sideCode"))
			.put("OFFENDER_ID", new FieldMapper("offenderId")).put("BIRTH_STATE", new FieldMapper("birthState"))
			.put("LAST_NAME", new FieldMapper("lastName")).put("HEIGHT_IN", new FieldMapper("heightIn"))
			.put("ID_SOURCE_CODE", new FieldMapper("idSourceCode")).put("REMARK_CODE", new FieldMapper("remarkCode"))
			.put("MODIFY_DATETIME", new FieldMapper("modifyDatetime"))
			.put("SUSPENDED_FLAG", new FieldMapper("suspendedFlag")).put("WEIGHT_KG", new FieldMapper("weightKg"))
			.put("COMMENT_TEXT", new FieldMapper("commentText")).put("PROFILE_SEQ", new FieldMapper("profileSeq"))
			.put("SUSPENDED_DATE", new FieldMapper("suspendedDate"))
			.put("MODIFY_USER_ID", new FieldMapper("modifyUserId"))
			.put("BIRTH_COUNTRY_CODE", new FieldMapper("birthCountryCode"))
			.put("MIDDLE_NAME_KEY", new FieldMapper("middleNameKey")).put("TITLE", new FieldMapper("title"))
			.put("SEX_CODE", new FieldMapper("sexCode")).put("LAST_NAME_KEY", new FieldMapper("lastNameKey"))
			.put("ALIAS_OFFENDER_ID", new FieldMapper("aliasOffenderId"))
			.put("ALIAS_NAME_TYPE", new FieldMapper("aliasNameType"))
			.put("ATTRIBUTE_SEQ", new FieldMapper("attributeSeq")).put("CREATE_DATE", new FieldMapper("createDate"))
			.put("FIRST_NAME_KEY", new FieldMapper("firstNameKey")).put("WEIGHT_LBS", new FieldMapper("weightLbs"))
			.put("BIRTH_DATE", new FieldMapper("birthDate")).put("FIRST_NAME", new FieldMapper("firstName")).build();
	private final Map<String, FieldMapper> offenderProfileDetailsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("OFFENDER_ID_DISPLAY", new FieldMapper("offenderIdDisplay"))
			.put("PARENT_OFFENDER_ID", new FieldMapper("parentOffenderId"))
			.put("ROOT_OFFENDER_ID", new FieldMapper("rootOffenderId"))
			.put("ADD_INFO_CODE", new FieldMapper("addInfoCode")).put("MIDDLE_NAME_2", new FieldMapper("middleName2"))
			.put("LAST_NAME_ALPHA_KEY", new FieldMapper("lastNameAlphaKey"))
			.put("MIDDLE_NAME", new FieldMapper("middleName")).put("HEIGHT_CM", new FieldMapper("heightCm"))
			.put("BIRTH_COUNTY", new FieldMapper("birthCounty")).put("CASELOAD_TYPE", new FieldMapper("caseloadType"))
			.put("UNIQUE_OBLIGATION_FLAG", new FieldMapper("uniqueObligationFlag"))
			.put("SEAL_FLAG", new FieldMapper("sealFlag")).put("CREATE_DATETIME", new FieldMapper("createDatetime"))
			.put("NAME_TYPE", new FieldMapper("nameType")).put("OFFENDER_BOOK_ID", new FieldMapper("offenderBookId"))
			.put("CREATE_USER_ID", new FieldMapper("createUserId")).put("PROFILE_TYPE", new FieldMapper("profileType"))
			.put("BODY_PART_CODE", new FieldMapper("bodyPartCode")).put("LIST_SEQ", new FieldMapper("listSeq"))
			.put("HEIGHT_FT", new FieldMapper("heightFt")).put("NAME_SEQUENCE", new FieldMapper("nameSequence"))
			.put("BIRTH_PLACE", new FieldMapper("birthPlace")).put("RACE_CODE", new FieldMapper("raceCode"))
			.put("AGE", new FieldMapper("age")).put("PROFILE_CODE", new FieldMapper("profileCode"))
			.put("PART_ORIENTATION_CODE", new FieldMapper("partOrientationCode"))
			.put("SUFFIX", new FieldMapper("suffix")).put("MARK_TYPE", new FieldMapper("markType"))
			.put("OFFENDER_NAME_SEQ", new FieldMapper("offenderNameSeq"))
			.put("LAST_NAME_SOUNDEX", new FieldMapper("lastNameSoundex"))
			.put("ID_MARK_SEQ", new FieldMapper("idMarkSeq")).put("SIDE_CODE", new FieldMapper("sideCode"))
			.put("OFFENDER_ID", new FieldMapper("offenderId")).put("BIRTH_STATE", new FieldMapper("birthState"))
			.put("LAST_NAME", new FieldMapper("lastName")).put("HEIGHT_IN", new FieldMapper("heightIn"))
			.put("ID_SOURCE_CODE", new FieldMapper("idSourceCode")).put("REMARK_CODE", new FieldMapper("remarkCode"))
			.put("MODIFY_DATETIME", new FieldMapper("modifyDatetime"))
			.put("SUSPENDED_FLAG", new FieldMapper("suspendedFlag")).put("WEIGHT_KG", new FieldMapper("weightKg"))
			.put("COMMENT_TEXT", new FieldMapper("commentText")).put("PROFILE_SEQ", new FieldMapper("profileSeq"))
			.put("SUSPENDED_DATE", new FieldMapper("suspendedDate"))
			.put("MODIFY_USER_ID", new FieldMapper("modifyUserId"))
			.put("BIRTH_COUNTRY_CODE", new FieldMapper("birthCountryCode"))
			.put("MIDDLE_NAME_KEY", new FieldMapper("middleNameKey")).put("TITLE", new FieldMapper("title"))
			.put("SEX_CODE", new FieldMapper("sexCode")).put("LAST_NAME_KEY", new FieldMapper("lastNameKey"))
			.put("ALIAS_OFFENDER_ID", new FieldMapper("aliasOffenderId"))
			.put("ALIAS_NAME_TYPE", new FieldMapper("aliasNameType"))
			.put("ATTRIBUTE_SEQ", new FieldMapper("attributeSeq")).put("CREATE_DATE", new FieldMapper("createDate"))
			.put("FIRST_NAME_KEY", new FieldMapper("firstNameKey")).put("WEIGHT_LBS", new FieldMapper("weightLbs"))
			.put("BIRTH_DATE", new FieldMapper("birthDate")).put("CODE_VALUE_TYPE", new FieldMapper("codeValueType"))
			.put("FIRST_NAME", new FieldMapper("firstName"))
			.put("ACTIVE_FLAG", new FieldMapper("activeFlag")).build();
	private final Map<String, FieldMapper> profileTypesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("PFL_CODE.LIST_SEQ", new FieldMapper("pflCode.listSeq"))
			.put("PROFILE_TYPE", new FieldMapper("profileType")).put("DESCRIPTION", new FieldMapper("description"))
			.put("PFL_CODE.PROFILE_TYPE", new FieldMapper("pflCode.profileType"))
			.put("CODE_VALUE_TYPE", new FieldMapper("codeValueType"))
			.put("PFL_CODE.DESCRIPTION", new FieldMapper("pflCode.description"))
			.put("PROFILE_CODE", new FieldMapper("profileCode")).build();
	private final Map<String, FieldMapper> systemProfilesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("PROFILE_VALUE", new FieldMapper("profileValue"))
			.put("PROFILE_VALUE INTO LV_URL", new FieldMapper("profileValue into lvUrl")).build();
	private final Map<String, FieldMapper> referenceCodesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DESCRIPTION", new FieldMapper("description")).put("LIST_SEQ", new FieldMapper("listSeq"))
			.put("CODE", new FieldMapper("code")).build();

	private final Map<String, FieldMapper> images = new ImmutableMap.Builder<String, FieldMapper>()
			.put("IMAGE_ID", new FieldMapper("imageId")).put("CAPTURE_DATE", new FieldMapper("captureDate"))
			.put("IMAGE_OBJECT_TYPE", new FieldMapper("imageObjectType"))
			.put("IMAGE_OBJECT_ID", new FieldMapper("imageObjectId"))
			.put("IMAGE_VIEW_TYPE", new FieldMapper("imageViewType"))
			.put("IMAGE_OBJECT_SEQ", new FieldMapper("imageObjectSeq"))
			.put("IMAGE_THUMBNAIL", new FieldMapper("imageThumbnail")).put("ACTIVE_FLAG", new FieldMapper("activeFlag"))
			.put("ORIENTATION_TYPE", new FieldMapper("orientationType")).put("SEAL_FLAG", new FieldMapper("sealFlag"))
			.put("CREATE_DATETIME", new FieldMapper("createDatetime"))
			.put("CREATE_USER_ID", new FieldMapper("createUserId"))
			.put("MODIFY_DATETIME", new FieldMapper("modifyDatetime"))
			.put("MODIFY_USER_ID", new FieldMapper("modifyUserId")).put("COU", new FieldMapper("cou")).build();

	/**
	 * Creates new OidpidenRepositoryImpl class Object
	 */
	public OidpidenRepositoryImpl() {
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<RecordGroup>
	 */
	public List<ProfileCodes> rgProfileRecordGroup(final String profileType) {
		final String sql = getQuery("OIDPIDEN_FIND_RGPROFILE");
		final RowMapper<ProfileCodes> referenceCodeRowMapper = Row2BeanRowMapper.makeMapping(sql, ProfileCodes.class,
				profileCodesMapping);
		ArrayList<ProfileCodes> refList = null;
		refList = (ArrayList<ProfileCodes>) namedParameterJdbcTemplate.query(sql,
				createParams("profileType", profileType), referenceCodeRowMapper);
		return refList;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<RecordGroup>
	 */
	public List<ReferenceCodes> rgMarkTypeRecordGroup() {
		final String sql = getQuery("OIDPIDEN_FIND_RGMARKTYPE");
		final String enterQuery = "QUERY";
		final RowMapper<ReferenceCodes> referenceCodeRowMapper = Row2BeanRowMapper.makeMapping(sql,
				ReferenceCodes.class, referenceCodesMapping);
		List<ReferenceCodes> refList = new ArrayList<ReferenceCodes>();
		refList = namedParameterJdbcTemplate.query(sql, createParams("MODE", enterQuery), referenceCodeRowMapper);
		return refList;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<RecordGroup>
	 */
	public List<ReferenceCodes> rgBodyPartRecordGroup() {
		final String sql = getQuery("OIDPIDEN_FIND_RGBODYPART");
		final String enterQuery = "QUERY";
		final RowMapper<ReferenceCodes> referenceCodeRowMapper = Row2BeanRowMapper.makeMapping(sql,
				ReferenceCodes.class, referenceCodesMapping);
		List<ReferenceCodes> refList = new ArrayList<ReferenceCodes>();
		refList = namedParameterJdbcTemplate.query(sql, createParams("MODE", enterQuery), referenceCodeRowMapper);
		return refList;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<RecordGroup>
	 */
	public List<ReferenceCodes> rgSideRecordGroup() {
		final String sql = getQuery("OIDPIDEN_FIND_RGSIDE");
		final String enterQuery = "QUERY";
		final RowMapper<ReferenceCodes> referenceCodeRowMapper = Row2BeanRowMapper.makeMapping(sql,
				ReferenceCodes.class, referenceCodesMapping);
		List<ReferenceCodes> refList = new ArrayList<ReferenceCodes>();
		refList = namedParameterJdbcTemplate.query(sql, createParams("MODE", enterQuery), referenceCodeRowMapper);
		return refList;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<RecordGroup>
	 */
	public List<ReferenceCodes> rgPartOrientRecordGroup() {
		final String sql = getQuery("OIDPIDEN_FIND_RGPARTORIENT");
		final String enterQuery = "QUERY";
		final RowMapper<ReferenceCodes> referenceCodeRowMapper = Row2BeanRowMapper.makeMapping(sql,
				ReferenceCodes.class, referenceCodesMapping);
		List<ReferenceCodes> refList = new ArrayList<ReferenceCodes>();
		refList = namedParameterJdbcTemplate.query(sql, createParams("MODE", enterQuery), referenceCodeRowMapper);
		return refList;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<RecordGroup>
	 */
	public List<ReferenceCodes> rgRaceCodeRecordGroup() {
		final String sql = getQuery("OIDPIDEN_FIND_RGRACECODE");
		final RowMapper<ReferenceCodes> referenceCodeRowMapper = Row2BeanRowMapper.makeMapping(sql,
				ReferenceCodes.class, referenceCodesMapping);
		List<ReferenceCodes> refList = new ArrayList<ReferenceCodes>();
		refList = namedParameterJdbcTemplate.query(sql, createParams(), referenceCodeRowMapper);
		return refList;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao OffenderPhysicalAttributes
	 *
	 * @return List<OffenderPhysicalAttributes>
	 *
	 * @throws SQLException
	 */
	public List<OffenderPhysicalAttributes> offPaSearchOffenderPhysicalAttributes(
			final OffenderPhysicalAttributes offenderPhysicalAttributes) {
		final String sql = getQuery("OIDPIDEN_OFFPA_FIND_OFFENDER_PHYSICAL_ATTRIBUTES");
		List<OffenderPhysicalAttributes> returnList = new ArrayList<>();
		final RowMapper<OffenderPhysicalAttributes> OffenderPhysicalAttributesRowMapper = Row2BeanRowMapper
				.makeMapping(sql, OffenderPhysicalAttributes.class, offenderPhysicalAttributesMapping);
		returnList = namedParameterJdbcTemplate.query(sql,
				createParams("offenderBookId", offenderPhysicalAttributes.getOffenderBookId()),
				OffenderPhysicalAttributesRowMapper);
		return returnList;
	}

	public Integer getOffenderBook(final Long offenderPhysicalAttributes) {
		Integer offenderCount = 0;
		final String sql = getQuery("OIDPIDEN_VERIFY_OFFENDER_BOOK_ID");
		offenderCount = namedParameterJdbcTemplate.queryForObject(sql,
				createParams("offenderBookId", offenderPhysicalAttributes), Integer.class);
		return offenderCount;
	}

	/**
	 * This method is used to insert the records in the data base tables based on
	 *
	 * @param lstOffenderPhysicalAttributes List<OffenderPhysicalAttributes>
	 *
	 * @return List<Integer>
	 *
	 * @throws SQLException
	 */
	public Integer insertOffenderPhysicalAttributes(
			final List<OffenderPhysicalAttributes> lstOffenderPhysicalAttributes) {
		final String sql = getQuery("OIDPIDEN_OFFPA_INSERT_OFFENDER_PHYSICAL_ATTRIBUTES");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffenderPhysicalAttributes offenderPhysicalAttributes : lstOffenderPhysicalAttributes) {
			offenderPhysicalAttributes.setAttributeSeq(1L);
			parameters.add(new BeanPropertySqlParameterSource(offenderPhysicalAttributes));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstOffenderPhysicalAttributes.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstOffenderPhysicalAttributes List<OffenderPhysicalAttributes>
	 *
	 * @throws SQLException
	 */
	public Integer updateOffenderPhysicalAttributes(
			final List<OffenderPhysicalAttributes> lstOffenderPhysicalAttributes) {
		final String sql = getQuery("OIDPIDEN_OFFPA_UPDATE_OFFENDER_PHYSICAL_ATTRIBUTES");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffenderPhysicalAttributes offenderPhysicalAttributes : lstOffenderPhysicalAttributes) {
			parameters.add(new BeanPropertySqlParameterSource(offenderPhysicalAttributes));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstOffenderPhysicalAttributes.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to delete records from data base tables based on
	 *
	 * @param lstOffenderPhysicalAttributes List<OffenderPhysicalAttributes>
	 *
	 * @throws SQLException
	 */
	public Integer deleteOffenderPhysicalAttributes(
			final List<OffenderPhysicalAttributes> lstOffenderPhysicalAttributes) {
		final String sql = getQuery("OIDPIDEN_OFFPA_DELETE_OFFENDER_PHYSICAL_ATTRIBUTES");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffenderPhysicalAttributes offenderPhysicalAttributes : lstOffenderPhysicalAttributes) {
			parameters.add(new BeanPropertySqlParameterSource(offenderPhysicalAttributes));
		}
		try {
			String tableName = "OFFENDER_PHYSICAL_ATTRIBUTES";
			String whereClause = "OFFENDER_BOOK_ID = :offenderBookId  and ATTRIBUTE_SEQ = :attributeSeq";
			batchUpdatePreDeletedRows(tableName, whereClause , parameters);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method deleteOffenderPhysicalAttributes", e);
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstOffenderPhysicalAttributes.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao Offenders
	 *
	 * @return List<Offenders>
	 *
	 * @throws SQLException
	 */
	public List<Offenders> offRaceSearchOffenders(final Offenders objSearchDao) {
		final String sql = getQuery("OIDPIDEN_OFFRACE_FIND_OFFENDERS");
		final RowMapper<Offenders> OffendersRowMapper = Row2BeanRowMapper.makeMapping(sql, Offenders.class,
				offendersMapping);
		final ArrayList<Offenders> returnList = (ArrayList<Offenders>) namedParameterJdbcTemplate.query(sql,
				createParams("offenderId", objSearchDao.getOffenderId()), OffendersRowMapper);
		return returnList;
	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstOffenders List<Offenders>
	 *
	 * @throws SQLException
	 */
	public Integer offRaceUpdateOffenders(final List<Offenders> lstOffenders) {
		String sql = getQuery("OIDPIDEN_OFFRACE_UPDATE_OFFENDERS");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (Offenders offenders : lstOffenders) {
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
	 * @param objSearchDao OffenderProfileDetails
	 *
	 * @return List<OffenderProfileDetails>
	 *
	 * @throws SQLException
	 */
	public List<OffenderProfileDetails> offPdSearchOffenderProfileDetails(
			final OffenderProfileDetails offenderProfileDetails) {
		final String sql = getQuery("OIDPIDEN_OFFPD_FIND_OFFENDER_PROFILE_DETAILS");
		List<OffenderProfileDetails> returnList = new ArrayList<>();
		final RowMapper<OffenderProfileDetails> OffenderProfileDetailsRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderProfileDetails.class, offenderProfileDetailsMapping);
		returnList = namedParameterJdbcTemplate.query(sql,
				createParams("offnderBookId", offenderProfileDetails.getOffenderBookId()),
				OffenderProfileDetailsRowMapper);
		return returnList;
	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstOffenderProfileDetails List<OffenderProfileDetails>
	 *
	 * @throws SQLException
	 */
	public Integer offPdUpdateOffenderProfileDetails(final List<OffenderProfileDetails> lstOffenderProfileDetails) {
		final String sql = getQuery("OIDPIDEN_OFFPD_UPDATE_OFFENDER_PROFILE_DETAILS");
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
	 * Fetch the records from database table
	 *
	 * @param objSearchDao OffenderIdentifyingMarks
	 *
	 * @return List<OffenderIdentifyingMarks>
	 *
	 * @throws SQLException
	 */
	public List<OffenderIdentifyingMark> offImSearchOffenderIdentifyingMarks(
			final OffenderIdentifyingMark objSearchDao) {
		final String sql = getQuery("OIDPIDEN_OFFIM_FIND_OFFENDER_IDENTIFYING_MARKS");
		List<OffenderIdentifyingMark> returnList = new ArrayList<>();
		final RowMapper<OffenderIdentifyingMark> OffenderIdentifyingMarksRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderIdentifyingMark.class, offenderIdentifyingMarksMapping);
		returnList = namedParameterJdbcTemplate.query(sql,
				createParams("offenderBookId", objSearchDao.getOffenderBookId()), OffenderIdentifyingMarksRowMapper);

		if (returnList != null && !returnList.isEmpty()) {
			for (OffenderIdentifyingMark offenderPptyItems : returnList) {

				String sqlForImageExistence = getQuery("GET_IDENTIFY_MARK_IMAGE");
				final RowMapper<Images> imagesRowMapper = Row2BeanRowMapper.makeMapping(sql, Images.class, images);
				final MapSqlParameterSource inParameterSource = new MapSqlParameterSource();
				final StringBuffer sqlQuery = new StringBuffer();
				sqlQuery.append(sqlForImageExistence);
				if (offenderPptyItems.getMarkType()!=null) {
					inParameterSource.addValue("MARK_TYPE", offenderPptyItems.getMarkType());
				}
				if (offenderPptyItems.getOffenderBookId() != 0) {
					inParameterSource.addValue("OFFENDER_BOOK_ID", offenderPptyItems.getOffenderBookId());
				}
				if (offenderPptyItems.getIdMarkSeq() != 0) {
					inParameterSource.addValue("ID_MARK_SEQ", offenderPptyItems.getIdMarkSeq());
				}
				offenderPptyItems.setImages(namedParameterJdbcTemplate.query(sqlQuery.toString().trim(),
						inParameterSource, imagesRowMapper));

			}
		}
		return returnList;
	}

	/**
	 * method for preInsert
	 * 
	 * @return Integer
	 * @param offenderBookId
	 */
	public Integer getOffenderIdentifyingMarkIdMarkSeq(final String offenderBookId) {
		Integer idMarkSeq = 0;
		final String sql = getQuery("FIND_ID_MARK_SEQ_FOR_OFFENDER_IDENTIFYING_MARKS");
		idMarkSeq = namedParameterJdbcTemplate.queryForObject(sql,
				createParams("offenderBookId", offenderBookId != null ? new BigDecimal(offenderBookId) : null),
				Integer.class);
		return idMarkSeq;
	}

	/**
	 * This method is used to insert the records in the data base tables based on
	 *
	 * @param lstOffenderIdentifyingMarks List<OffenderIdentifyingMarks>
	 *
	 * @return List<Integer>
	 *
	 * @
	 */

	public Integer insertOffenderIdentifyingMarks(final List<OffenderIdentifyingMark> lstOffenderIdentifyingMarks) {
		final String sql = getQuery("OIDPIDEN_OFFIM_INSERT_OFFENDER_IDENTIFYING_MARKS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffenderIdentifyingMark offenderIdentifyingMarks : lstOffenderIdentifyingMarks) {
			parameters.add(new BeanPropertySqlParameterSource(offenderIdentifyingMarks));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstOffenderIdentifyingMarks.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstOffenderIdentifyingMarks List<OffenderIdentifyingMarks>
	 *
	 * @
	 */
	public Integer updateOffenderIdentifyingMarks(final List<OffenderIdentifyingMark> lstOffenderIdentifyingMarks) {
		final String sql = getQuery("OIDPIDEN_OFFIM_UPDATE_OFFENDER_IDENTIFYING_MARKS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffenderIdentifyingMark offenderIdentifyingMarks : lstOffenderIdentifyingMarks) {
			parameters.add(new BeanPropertySqlParameterSource(offenderIdentifyingMarks));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstOffenderIdentifyingMarks.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to delete records from data base tables based on
	 *
	 * @param lstOffenderIdentifyingMarks List<OffenderIdentifyingMarks>
	 *
	 * @throws SQLException
	 */
	public Integer deleteOffenderIdentifyingMarks(final List<OffenderIdentifyingMark> lstOffenderIdentifyingMarks) {
		final String sql = getQuery("OIDPIDEN_OFFIM_DELETE_OFFENDER_IDENTIFYING_MARKS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffenderIdentifyingMark offenderIdentifyingMarks : lstOffenderIdentifyingMarks) {
			parameters.add(new BeanPropertySqlParameterSource(offenderIdentifyingMarks));
		}
		try {
			String tableName = "OFFENDER_IDENTIFYING_MARKS";
			String whereClause = "OFFENDER_BOOK_ID = :offenderBookId  and ID_MARK_SEQ = :idMarkSeq";
			batchUpdatePreDeletedRows(tableName, whereClause , parameters);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method deleteOffenderIdentifyingMarks", e);
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstOffenderIdentifyingMarks.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * oidpidenOffBkgPreDelete
	 *
	 * @param params
	 *
	 */
	public Offenders offBkgPreDelete(final Offenders paramBean) {
		Offenders returnObj = new Offenders();
		final String sql = getQuery("OIDPIDEN_OFF_BKG_PREDELETE");
		final RowMapper<Offenders> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, Offenders.class,
				offendersMapping);
		returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(paramBean), columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * oidpidenNbtDetailDescWhenValidateItemprofileTypes
	 *
	 * @param params
	 *
	 */
	public List<Object> nbtDetailDescWhenValidateItemprofileTypes(final ProfileTypes profileTypes) {
		final String sql = getQuery("OIDPIDEN_NBT_DETAIL_DESC_WHENVALIDATEITEM_PROFILE_TYPES_C");
		List<Object> returnObj = new ArrayList<>();
		returnObj = namedParameterJdbcTemplate.queryForList(sql,
				createParams("PROFILETYPE", profileTypes.getProfileType()), Object.class);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * oidpidenNbtDetailDescWhenNewItemInstanceprofileTypes
	 *
	 * @param params
	 *
	 */
	public ProfileTypes nbtDetailDescWhenNewItemInstanceprofileTypes(final ProfileTypes profileTypes) {
		final String sql = getQuery("OIDPIDEN_NBT_DETAIL_DESC_WHEN_NEW_ITEM_INSTANCE_PROFILE_TYPES_C");
		final RowMapper<ProfileTypes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, ProfileTypes.class,
				profileTypesMapping);
		ProfileTypes returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(profileTypes),
				columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * oidpidenOffPdWhenNewRecordInstanceprofileTypes
	 *
	 * @param params
	 *
	 */
	public ProfileTypes offPdWhenNewRecordInstanceprofileTypes(final ProfileTypes profileTypes) {
		final String sql = getQuery("OIDPIDEN_OFF_PD_WHENNEWRECORDINSTANCE_PROFILE_TYPES_C");
		final RowMapper<ProfileTypes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, ProfileTypes.class,
				profileTypesMapping);
		ProfileTypes returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(profileTypes),
				columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * oidpidenOffPdPostQuerycharDescCur
	 *
	 * @param params
	 *
	 */
	public ProfileTypes offPdPostQuerycharDescCur(final OffenderProfileDetails profileTypes) {
		final String sql = getQuery("OIDPIDEN_OFF_PD_POSTQUERY_CHAR_DESC_CUR");
		final RowMapper<ProfileTypes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, ProfileTypes.class,
				profileTypesMapping);
		ProfileTypes returnObj = new ProfileTypes();
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("PROFILETYPE", profileTypes.getProfileType()), columnRowMapper);
		} catch (Exception e) {
			returnObj = new ProfileTypes();
		}
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * oidpidenCallToShowFingerprintOld
	 *
	 * @param params
	 *
	 */
	public List<SystemProfiles> callToShowFingerprintOld(final SystemProfiles systemProfiles) {
		final String sql = getQuery("OIDPIDEN_CALL_TO_SHOW_FINGERPRINT_OLD");
		List<SystemProfiles> returnList = new ArrayList<>();
		final RowMapper<SystemProfiles> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, SystemProfiles.class,
				systemProfilesMapping);
		returnList = (ArrayList<SystemProfiles>) namedParameterJdbcTemplate.query(sql, createParams(), columnRowMapper);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * oidpidenCgfkchkOffPdOffPdPflCodvalueTypeCur
	 *
	 * @param params
	 *
	 */
	public List<Object> cgfkchkOffPdOffPdPflCodvalueTypeCur(final ProfileTypes profileTypes) {
		final String sql = getQuery("OIDPIDEN_CGFKCHK_OFF_PD_OFF_PD_PFL_COD_VALUE_TYPE_CUR");
		List<Object> returnObj = new ArrayList<>();
		returnObj = namedParameterJdbcTemplate.queryForList(sql,
				createParams("PROFILETYPE", profileTypes.getProfileType()), Object.class);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * oidpidenCgfkchkOffPdOffPdPflCodc
	 *
	 * @param params
	 *
	 */
	public ProfileCodes cgfkchkOffPdOffPdPflCodc(final OffenderProfileDetails profileCodes) {
		final String sql = getQuery("OIDPIDEN_CGFKCHK_OFF_PD_OFF_PD_PFL_COD_C");
		final RowMapper<ProfileCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, ProfileCodes.class,
				profileCodesMapping);
		ProfileCodes returnObj = new ProfileCodes();
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams("PROFILECODE",
					profileCodes.getProfileCode(), "PROFILETYPE", profileCodes.getProfileType()), columnRowMapper);
		} catch (Exception e) {
			returnObj = new ProfileCodes();
		}
		return returnObj;
	}

	public String checkImage(final String imageObjType, final String offenderBookId, final String markType,
			final String bodypart, final String objectSeq) {

		Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[5];
		sqlParameters = new SqlParameter[] { new SqlParameter("P_OFFENDER_BOOK_ID", OracleTypes.VARCHAR),
				new SqlParameter("P_IMAGE_OBJECT_TYPE", OracleTypes.VARCHAR),
				new SqlParameter("P_MARK_TYPE", OracleTypes.VARCHAR),
				new SqlParameter("P_BODY_PART", OracleTypes.VARCHAR),
				new SqlParameter("P_OBJECT_SEQ", OracleTypes.VARCHAR),
				new SqlInOutParameter("RETURN_VALUE", OracleTypes.VARCHAR) };
		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withFunctionName("IMAGE_MARK_EXISTS").declareParameters(sqlParameters);
		inParamMap.put("P_OFFENDER_BOOK_ID", offenderBookId);
		inParamMap.put("P_IMAGE_OBJECT_TYPE", imageObjType);
		inParamMap.put("P_MARK_TYPE", markType);
		inParamMap.put("P_BODY_PART", bodypart);
		inParamMap.put("P_OBJECT_SEQ", objectSeq);
		inParamMap.put("P_RETURN", "true");
		final SqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		final String value = simpleJDBCCall.executeFunction(String.class, inParameter);
		return value;

	}

	/**
	 * This method is invoked to get profile SEQ
	 * 
	 */
	public Integer gettingListseq(String offenderBookId) {
		final String sql = getQuery("OIDPIDEN_GETTING_LISTSEQ");
		Integer returnObj = 0;
		List<OffenderProfileDetails> returnValue = new ArrayList<OffenderProfileDetails>();
		final RowMapper<OffenderProfileDetails> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderProfileDetails.class, profileTypesMapping);
		try {
			returnValue = namedParameterJdbcTemplate.query(sql,
					createParams("OFFENDER_BOOK_ID", offenderBookId != null ? new BigDecimal(offenderBookId) : null),
					columnRowMapper);
			if (returnValue.size() > 0) {
				returnObj = 1;
			} else {
				returnObj = 0;
			}
		} catch (EmptyResultDataAccessException e) {
			returnObj = 0;
		}

		return returnObj;
	}

	/**
	 * This method is invoked to insert profile seq
	 */
	public Integer insertOffenderprofileDetails(final String offenderBookId, final String userName) {
		final String sql = getQuery("OIDPIDEN_INSERT_OFFENDER_PROFILES");

		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		OffenderProfiles bean = new OffenderProfiles();
		bean.setOffenderBookId(Long.valueOf(offenderBookId));
		bean.setCreateUserId(userName);
		parameters.add(new BeanPropertySqlParameterSource(bean));
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (returnArray.length == 1) {
			return 1;
		} else {
			return 0;
		}

	}

	/*
	 * This method is invoked to get List the profile types that needs to be added
	 * for offender
	 */
	public List<OffenderProfileDetails> listofProfileTypes(String offenderBookId, String profileCategory) {
		final String sql = getQuery("OIDPIDEN_GETPROFILETYPES_AND_MINUS_PROFILE_DETAILS");
		List<OffenderProfileDetails> returnObj = new ArrayList<OffenderProfileDetails>();
		final RowMapper<OffenderProfileDetails> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderProfileDetails.class, profileTypesMapping);
		returnObj = namedParameterJdbcTemplate.query(sql, createParams("off_book_id",
				offenderBookId != null ? new BigDecimal(offenderBookId) : null, "profile_category", profileCategory),
				columnRowMapper);
		return returnObj;
	}

	/*
	 * This method is inserted to profile types
	 */
	public List<OffenderProfileDetails> insertProfileDetails(String offenderBookId, String caseloadType,
			String profileCategory, List<String> profileTypeList, String userName) {
		final String sql = getQuery("OIDPIDEN_INSERT_LIST_PROFILE_DETAILS");
		List<OffenderProfileDetails> returnList = new ArrayList<>();
		try {
		namedParameterJdbcTemplate.update(sql,
					createParams("off_book_id", offenderBookId != null ? new BigDecimal(offenderBookId) : null, "csld_type",
							caseloadType, "profile_category", profileCategory, "v_profile_types", profileTypeList,
							"userName", userName)
					);
		}catch (Exception e) {
			logger.error("insertProfileDetails"+e);
		}
		return returnList;

	}

	@Override
	public String getDescriptionofMarkType(final String markType) {
		String desc = "";
		final String sql = getQuery("OIDPIDEN_GETDESC_MARKTYPE");
		desc = namedParameterJdbcTemplate.queryForObject(sql, createParams("code", markType), String.class);
		return desc;
	}

	@Override
	public String getDescriptionofBodyPart(final String bodyPartCode) {
		String desc = "";
		final String sql = getQuery("OIDPIDEN_GETDESC_BODYPART");
		desc = namedParameterJdbcTemplate.queryForObject(sql, createParams("code", bodyPartCode), String.class);
		return desc;
	}

	@Override
	public List<Offenders> getoldlistoffenders(final Long offenderId) {
		final String sql = getQuery("OIDPIDEN_GET_OLD_LIST_OFFENDERS");
		final RowMapper<Offenders> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, Offenders.class,
				profileTypesMapping);
		List<Offenders> List = namedParameterJdbcTemplate.query(sql, createParams("OFFENDERID", offenderId),
				columnRowMapper);
		return List;
	}

}

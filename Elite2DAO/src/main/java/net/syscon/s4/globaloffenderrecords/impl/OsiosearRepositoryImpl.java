package net.syscon.s4.globaloffenderrecords.impl;

import java.math.BigDecimal;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.expression.ParseException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlInOutParameter;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.ApplicationConstants;
import net.syscon.s4.common.beans.Caseloads;
import net.syscon.s4.common.beans.OffenderBookings;
import net.syscon.s4.common.beans.OffenderFingerprints;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.beans.VHeaderBlock;
import net.syscon.s4.common.beans.VHeaderBlock2;
import net.syscon.s4.common.beans.VPropertyHeaderBlock;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.globaloffenderrecords.OsiosearRepository;
import net.syscon.s4.im.beans.Images;
import net.syscon.s4.im.beans.OffenderPhysicalAttributes;
import net.syscon.s4.im.beans.OffenderProfileDetails;
import net.syscon.s4.im.beans.Offenders;
import net.syscon.s4.im.beans.OmsModules;
import net.syscon.s4.im.beans.ProfileCodes;
import net.syscon.s4.im.beans.ProfileTypes;
import net.syscon.s4.im.beans.ReferenceCodes;
import net.syscon.s4.im.beans.TagSearchGetOffenderIdentifiers;
import net.syscon.s4.im.beans.TagSearchGetOffenderRecords;
import net.syscon.s4.im.beans.TagSearchGetPartialRecords;
import net.syscon.s4.im.beans.TagSearchPopulateOffDetails;
import net.syscon.s4.im.beans.VTrustHeader;
import oracle.jdbc.OracleTypes;

/**
 * Class OsiosearRepositoryImpl
 */
@Repository
public class OsiosearRepositoryImpl extends RepositoryBase implements OsiosearRepository {
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger log = LogManager.getLogger(OsiosearRepositoryImpl.class.getName());

	private final Map<String, FieldMapper> referenceCodeMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DESCRIPTION",             new FieldMapper("description"))
			.put("CODE",                    new FieldMapper("code"))
			.put("DOMAIN",                  new FieldMapper("domain"))
			.put("OID", 				    new FieldMapper("oid"))
			.put("NAME", 				    new FieldMapper("name")).build();
	private final Map<String, FieldMapper> vheaderBlock = new ImmutableMap.Builder<String, FieldMapper>()
			.put("OFFENDER_ID", 		    new FieldMapper("offenderId"))
			.put("ALIAS_OFFENDER_ID",    new FieldMapper("aliasOffenderId"))
			.put("OFFENDER_ID_DISPLAY",	    new FieldMapper("offenderIdDisplay"))
			.put("LAST_NAME", 			    new FieldMapper("lastName"))
			.put("FIRST_NAME", 			    new FieldMapper("firstName"))
			.put("MIDDLE_NAME", 			new FieldMapper("middleName"))
			.put("SUFFIX", 					new FieldMapper("suffix"))
			.put("BIRTH_DATE",			    new FieldMapper("birthDate"))
			.put("OFFENDER_BOOK_ID", 		new FieldMapper("offenderBookId"))
			.put("BOOKING_NO", 				new FieldMapper("bookingNo"))
			.put("BOOKING_BEGIN_DATE", 		new FieldMapper("bookingBeginDate"))
			.put("BOOKING_END_DATE", 		new FieldMapper("bookingEndDate"))
			.put("AGY_LOC_ID", 				new FieldMapper("agyLocId"))
			.put("AGENCY_IML_ID", 			new FieldMapper("agencyImlId"))
			.put("LIVING_UNIT_ID", 			new FieldMapper("livingUnitId"))
			.put("DISCLOSURE_FLAG", 		new FieldMapper("disclosureFlag"))
			.put("ACTIVE_FLAG", 			new FieldMapper("activeFlag"))
			.put("BOOKING_STATUS", 			new FieldMapper("bookingStatus"))
			.put("LIVING_UNIT_DESCRIPTION", new FieldMapper("livingUnitDescription"))
			.put("IN_OUT_STATUS", 			new FieldMapper("inOutStatus"))
			.put("STATUS_DISPLAY", 			new FieldMapper("statusDisplay"))
			.put("ROOT_OFFENDER_ID", 		new FieldMapper("rootOffenderId"))
			.put("ASSIGNED_STAFF_ID", 		new FieldMapper("assignedStaffId"))
			.put("AGY_LOC_TYPE", 			new FieldMapper("agyLocType"))
			.put("CREATE_AGY_LOC_ID", 		new FieldMapper("createAgyLocId"))
			.put("BOOKING_TYPE", 			new FieldMapper("bookingType"))
			.put("BOOKING_CREATED_DATE", 	new FieldMapper("bookingCreatedDate"))
			.put("LOCATION_CODE", 			new FieldMapper("locationCode"))
			.put("INTAKE_AGY_LOC_ID", 		new FieldMapper("intakeAgyLocId"))
			.put("COMMUNITY_ACTIVE_FLAG", 	new FieldMapper("communityActiveFlag"))
			.put("CREATE_INTAKE_AGY_LOC_ID",new FieldMapper("createIntakeAgyLocId"))
			.put("COMMUNITY_STATUS", 		new FieldMapper("communityStatus"))
			.put("STATUS_REASON", 			new FieldMapper("statusReason"))
			.put("HEADER_STATUS", 			new FieldMapper("headerStatus"))
			.put("AGE", 					new FieldMapper("age"))
			.put("GENDER",                  new FieldMapper("gender"))
			.put("OFF_ALERTS", 				new FieldMapper("offAlerts"))
			.put("STATUS_1", 				new FieldMapper("status1"))
			.put("STATUS_2", 				new FieldMapper("status2"))
			.put("STATUS_3", 				new FieldMapper("status3"))
			.put("ETHNICITY", 				new FieldMapper("ethnicity"))
			.put("MOVEMENT_REASON",		    new FieldMapper("movementReason"))
			.put("IMAGE_ID", 				new FieldMapper("imageId"))
			.put("IMAGE_THUMBNAIL", 		new FieldMapper("imageThumbnail"))
			.put("OFF_SUP_LEVEL", 			new FieldMapper("offSupLevel"))
			.put("PPTY_AGY_LOC_ID", 	    new FieldMapper("pptyAgyLocId"))
			.put("OFFICER",                 new FieldMapper("officer"))
			.put("TRUSTACCOUNT",            new FieldMapper("trustAccount"))
			.put("PRISON_LOCATION",            new FieldMapper("prisonLocation"))
			.put("SEAL_FLAG",            new FieldMapper("sealFlag"))
			
			.build();
	private final Map<String, FieldMapper> profileCodes = new ImmutableMap.Builder<String, FieldMapper>()
			.put(" PROFILE_TYPE", 			new FieldMapper("profileType"))
			.put("PROFILE_CODE", 			new FieldMapper("profileCode"))
			.put("DESCRIPTION", 			new FieldMapper("description"))
			.put("LIST_SEQ", 				new FieldMapper("listSeq"))
			.put("UPDATE_ALLOWED_FLAG", 	new FieldMapper("updateAllowedFlag"))
			.put("ACTIVE_FLAG", 			new FieldMapper("activeFlag"))
			.put("EXPIRY_DATE", 			new FieldMapper("expiryDate"))
			.put("USER_ID", 				new FieldMapper("userId"))
			.put("MODIFIED_DATE", 			new FieldMapper("modifiedDate"))
			.put(" CREATE_DATETIME ", 		new FieldMapper("createDatetime"))
			.put("CREATE_USER_ID", 			new FieldMapper("createUserId"))
			.put("MODIFY_DATETIME", 		new FieldMapper("modifyDatetime"))
			.put("MODIFY_USER_ID", 			new FieldMapper("modifyUserId"))
			.put("SEAL_FLAG", 				new FieldMapper("sealFlag"))
			.build();
	private final Map<String, FieldMapper> images = new ImmutableMap.Builder<String, FieldMapper>()
			.put("IMAGE_ID", 				new FieldMapper("imageId"))
			.put("CAPTURE_DATE", 			new FieldMapper("captureDate"))
			.put("IMAGE_OBJECT_TYPE", 		new FieldMapper("imageObjectType"))
			.put("IMAGE_OBJECT_ID", 		new FieldMapper("imageObjectId"))
			.put("IMAGE_VIEW_TYPE", 		new FieldMapper("imageViewType"))
			.put("IMAGE_OBJECT_SEQ", 		new FieldMapper("imageObjectSeq"))
			.put("IMAGE_THUMBNAIL", 		new FieldMapper("imageThumbnail"))
			.put("ACTIVE_FLAG", 			new FieldMapper("activeFlag"))
			.put("ORIENTATION_TYPE", 		new FieldMapper("orientationType"))
			.put("SEAL_FLAG", 				new FieldMapper("sealFlag"))
			.put("CREATE_DATETIME", 		new FieldMapper("createDatetime"))
			.put("CREATE_USER_ID", 			new FieldMapper("createUserId"))
			.put("MODIFY_DATETIME", 		new FieldMapper("modifyDatetime"))
			.put("MODIFY_USER_ID", 			new FieldMapper("modifyUserId"))
			.put("COU", 					new FieldMapper("cou")).build();
	private final Map<String, FieldMapper> omsModules = new ImmutableMap.Builder<String, FieldMapper>()
			.put("MODULE_NAME", 			new FieldMapper("moduleName"))
			.put("DESCRIPTION", 			new FieldMapper("description"))
			.put("MODULE_TYPE", 			new FieldMapper("moduleType"))
			.put("PRINT_FORMAT_CODE", 		new FieldMapper("printFormatCode"))
			.put("PREVIEW_FLAG", 			new FieldMapper("previewFlag"))
			.put("DEFAULT_COPY", 			new FieldMapper("defaultCopy"))
			.put("APPLN_CODE", 				new FieldMapper("applnCode"))
			.put("HELP_DIRECTORY", 			new FieldMapper("helpDirectory"))
			.put("CREATE_DATETIME", 		new FieldMapper("createDatetime"))
			.put("CREATE_USER_ID", 			new FieldMapper("createUserId"))
			.put("MODIFY_DATETIME", 		new FieldMapper("modifyDatetime"))
			.put("MODIFY_USER_ID", 			new FieldMapper("modifyUserId"))
			.put("SEAL_FLAG", 				new FieldMapper("sealFlag"))
			.put("OUTPUT_TYPE", 			new FieldMapper("outputType")).build();
	private final Map<String, FieldMapper> offenderBookingsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("OFFENDER_ID", 			new FieldMapper("offenderId"))
			.put("OFFENDER_BOOK_ID", 		new FieldMapper("offenderBookId"))
			.put("HEIGHT_CM", 				new FieldMapper("heightCm"))
			.put("WEIGHT_KG", 				new FieldMapper("weightKg")).build();
	private final Map<String, FieldMapper> profileTypes = new ImmutableMap.Builder<String, FieldMapper>()
			.put("PROFILE_TYPE", 			new FieldMapper("profileType"))
			.put("PROFILE_CATEGORY", 		new FieldMapper("profileCategory"))
			.put("DESCRIPTION", 			new FieldMapper("description"))
			.put("LIST_SEQ", 				new FieldMapper("listSeq"))
			.put("MANDATORY_FLAG", 			new FieldMapper("mandatoryFlag"))
			.put("UPDATED_ALLOWED_FLAG", 	new FieldMapper("updatedAllowedflag"))
			.put(" CODE_VALUE_TYPE ", 		new FieldMapper("codeValueType"))
			.put("ACTIVE_FLAG", 			new FieldMapper("activeFlag"))
			.put("EXPIRY_DATE", 			new FieldMapper("expiryDate"))
			.put("MODIFY_USER_ID", 			new FieldMapper("modifyUserId"))
			.put("CREATE_DATETIME", 		new FieldMapper("createDatetime"))
			.put("CREATE_USER_ID", 			new FieldMapper("createUserId"))
			.put("MODIFY_DATETIME", 		new FieldMapper("modifyDatetime"))
			.put("SEAL_FLAG", 				new FieldMapper("sealFlag"))
			.build();
	private final Map<String, FieldMapper> systemProfiles = new ImmutableMap.Builder<String, FieldMapper>()
			.put("PROFILE_TYPE", 			new FieldMapper("profileType"))
			.put("PROFILE_CODE", 			new FieldMapper("profileCode"))
			.put("DESCRIPTION", 			new FieldMapper("description"))
			.put("PROFILE_VALUE", 			new FieldMapper("profileValue"))
			.put("PROFILE_VALUE_2", 		new FieldMapper("profileValue2"))
			.put("MODIFY_USER_ID", 			new FieldMapper("modifyUserId"))
			.put("OLD_TABLE_NAME", 			new FieldMapper("oldTableName"))
			.put("CREATE_DATETIME", 		new FieldMapper("createDatetime"))
			.put("CREATE_USER_ID", 			new FieldMapper("createUserId"))
			.put("MODIFY_DATETIME", 		new FieldMapper("modifyDatetime"))
			.put(" SEAL_FLAG  ", 			new FieldMapper("sealFlag"))
			.build();
	private final Map<String, FieldMapper> offenderFingerPrintsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("ROOT_OFFENDER_ID", 		new FieldMapper("rootOffenderId"))
			.put(" HAND_ID", 				new FieldMapper("handId"))
			.put("FINGER_ID", 				new FieldMapper("fingerId"))
			.put("USER_CREATED", 			new FieldMapper("userCreated"))
			.put("DATE_CREATED", 			new FieldMapper("dateCreated"))
			.put("FINGER_CODE", 			new FieldMapper("fingerCode"))
			.put("CREATE_DATETIME", 		new FieldMapper("createDatetime"))
			.put("CREATE_USER_ID", 			new FieldMapper("createUserId"))
			.put("MODIFY_DATETIME", 		new FieldMapper("modifyDatetime"))
			.put("MODIFY_USER_ID", 			new FieldMapper("modifyUserId"))
			.put("SEAL_FLAG", 				new FieldMapper("sealFlag"))
			.build();
	private final Map<String, FieldMapper> offenderProfileDetailsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("OFFENDER_BOOK_ID", 		new FieldMapper("offenderBookId"))
			.put("PROFILE_CODE", 			new FieldMapper("profileCode"))
			.put("COMMENT_TEXT", 			new FieldMapper("commentText"))
			.put("PROFILE_TYPE", 			new FieldMapper("profileType"))
			.put("CREATE_USER_ID", 			new FieldMapper("createUserId"))
			.put("PROFILE_SEQ", 			new FieldMapper("profileSeq"))
			.put("ACTIVE_FLAG", 			new FieldMapper("activeFlag"))
			.put("MODIFY_USER_ID", 			new FieldMapper("modifyUserId"))
			.put("LIST_SEQ", 				new FieldMapper("listSeq"))
			.put("IMAGE_OBJECT_SEQ", 		new FieldMapper("imageObjectSeq"))
			.put("CAPTURE_DATE", 			new FieldMapper("captureDate"))
			.put("CASELOAD_TYPE", 			new FieldMapper("caseloadType"))
			.put("IMAGE_ID", 				new FieldMapper("imageId"))
			.put("IMAGE_VIEW_TYPE", 		new FieldMapper("imageViewType"))
			.put("SEAL_FLAG", 				new FieldMapper("sealFlag"))
			.put("CREATE_DATETIME", 		new FieldMapper("createDatetime"))
			.put("MODIFY_DATETIME", 		new FieldMapper("modifyDatetime"))
			.put("IMAGE_OBJECT_TYPE", 		new FieldMapper("imageObjectType"))
			.put("IMAGE_OBJECT_ID", 		new FieldMapper("imageObjectId"))
			.put("IMAGE_THUMBNAIL", 		new FieldMapper("imageThumbnail"))
			.put("ORIENTATION_TYPE", 		new FieldMapper("orientationType")).build();
	private final Map<String, FieldMapper> offendersMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("RACE_CODE", 				new FieldMapper("raceCode"))
			.build();
	private final Map<String, FieldMapper> caseloadsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CASELOAD_ID", 			new FieldMapper("caseloadId"))
			.put("DESCRIPTION", 			new FieldMapper("description"))
			.put("CASELOAD_TYPE", 			new FieldMapper("caseloadType"))
			.put("LIST_SEQ", 				new FieldMapper("listSeq"))
			.put("TRUST_ACCOUNTS_FLAG",		new FieldMapper("trustAccountsFlag"))
			.put("ACCESS_PROPERTY_FLAG", 	new FieldMapper("acessPropertyFlag"))
			.put("TRUST_CASELOAD_ID", 		new FieldMapper("trustCaseLoadId"))
			.put("PAYROLL_FLAG", 			new FieldMapper("payrollFlag"))
			.put("ACTIVE_FLAG", 			new FieldMapper("activeFlag"))
			.put("DEACTIVATION_DATE", 		new FieldMapper("deactivationDate"))
			.put("COMMISSARY_FLAG", 		new FieldMapper("comissaryFlag"))
			.put("PAYROLL_TRUST_CASELOAD", 	new FieldMapper("payrollTrustCaseLoad"))
			.put("COMMISSARY_TRUST_CASELOAD", new FieldMapper("commissaryTrustCaseLoad"))
			.put("TRUST_COMMISSARY_CASELOAD", new FieldMapper("trustCommissaryCaseLoad"))
			.put("COMMUNITY_TRUST_CASELOAD", new FieldMapper("communityTrustCaseLoad"))
			.put("MDT_FLAG", 				new FieldMapper("mdtFlag"))
			.put("CREATE_DATETIME", 		new FieldMapper("createDateTime"))
			.put("CREATE_USER_ID", 			new FieldMapper("createuserId"))
			.put("MODIFY_DATETIME", 		new FieldMapper("modifyDateTime"))
			.put("MODIFY_USER_ID", 			new FieldMapper("modifyUserId"))
			.put("BILLING_FLAG", 			new FieldMapper("billingFlag"))
			.put("SEAL_FLAG", 				new FieldMapper("sealFlag")).build();

	/** Creates new OsiosearDao class Object */
	public OsiosearRepositoryImpl() {
		// OsiosearRepositoryImpl
	}

	/**
	 * getting rgSearchType LOV values
	 **
	 * @param referenceCodes
	 * @return List<ReferenceCodes>
	 */
	public List<ReferenceCodes> rgSearchTypeRecordGroup(final ReferenceCodes referenceCodes) {
		final String sql = getQuery("OSIOSEAR_FIND_RGSEARCHTYPE");
		final RowMapper<ReferenceCodes> referenceCodeRowMapper = Row2BeanRowMapper.makeMapping(sql,
				ReferenceCodes.class, referenceCodeMapping);
		List<ReferenceCodes> refList = new ArrayList<ReferenceCodes>();
		try {
			refList = namedParameterJdbcTemplate.query(sql, referenceCodeRowMapper);
		} catch (Exception e) {
			log.error("rgSearchTypeRecordGroup", e);
		}
		return refList;
	}

	/**
	 * getting rgIdentifier LOV values ** @param referenceCodes
	 *
	 * @param referenceCodes
	 * @return List<ReferenceCodes>
	 */
	@Override
	public List<ReferenceCodes> rgIdentifierTypeRecordGroup(final ReferenceCodes referenceCodes) {
		final String sql = getQuery("OSIOSEAR_FIND_RGIDENTIFIERTYPE");
		final RowMapper<ReferenceCodes> referenceCodeRowMapper = Row2BeanRowMapper.makeMapping(sql,
				ReferenceCodes.class, referenceCodeMapping);
		List<ReferenceCodes> refList = new ArrayList<ReferenceCodes>();
		try {
			refList = namedParameterJdbcTemplate.query(sql, referenceCodeRowMapper);
		} catch (Exception e) {
			log.error("rgIdentifierTypeRecordGroup", e);
		}
		return refList;
	}

	/**
	 * returning GetPartialRecords table values
	 *
	 * @Param searchBean
	 * @return List<TagSearchGetPartialRecords>
	 */
	public List<TagSearchGetPartialRecords> psOffNameExecuteQuery(final TagSearchGetOffenderRecords objSearchDao) {
		List<TagSearchGetPartialRecords> partialRecordslist = null;
		Map<String, Object> returnObject = null;
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[20];
		sqlParameters = new SqlParameter[] { new SqlOutParameter("RESULTSET", OracleTypes.CURSOR),
				new SqlParameter("P_SEARCH_TYPE", OracleTypes.VARCHAR), new SqlParameter("P_LAST_NAME", Types.VARCHAR),
				new SqlParameter("P_FIRST_NAME", Types.VARCHAR), new SqlParameter("P_MIDDLE_NAME", Types.VARCHAR),
				new SqlParameter("P_SEX_CODE", Types.VARCHAR), new SqlParameter("P_BIRTH_DATE", Types.DATE),
				new SqlParameter("P_BIRTH_YEAR", Types.VARCHAR), new SqlParameter("P_BIRTH_RANGE", Types.NUMERIC),
				new SqlParameter("P_AGEDATE", Types.DATE), new SqlParameter("P_AGE_RANGE", Types.NUMERIC),
				new SqlParameter("P_ETHNICITY", Types.VARCHAR) };
		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("TAG_SEARCH").withProcedureName("GET_PARTIAL_RECORDS")
				.declareParameters(sqlParameters);
		inParamMap.put("RESULTSET", OracleTypes.CURSOR);
		inParamMap.put("P_SEARCH_TYPE", objSearchDao.getpSearchType());
		inParamMap.put("P_LAST_NAME", objSearchDao.getpLastName());
		inParamMap.put("P_FIRST_NAME", objSearchDao.getpFirstName());
		inParamMap.put("P_MIDDLE_NAME", objSearchDao.getpMiddleName());
		inParamMap.put("P_SEX_CODE", objSearchDao.getpSexCode());
		inParamMap.put("P_BIRTH_DATE", objSearchDao.getpBirthDate());
		inParamMap.put("P_BIRTH_YEAR", objSearchDao.getpBirthYear());
		inParamMap.put("P_BIRTH_RANGE", objSearchDao.getpBirthRange());
		inParamMap.put("P_AGEDATE", objSearchDao.getpAgedate());
		inParamMap.put("P_AGE_RANGE", objSearchDao.getpAgeRange());
		inParamMap.put("P_ETHNICITY", objSearchDao.getpEthnicity());
		final MapSqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);

		try {
			returnObject = simpleJDBCCall.execute(inParameter);
			partialRecordslist = new ArrayList<>();
			final List<Map<String, String>> list = (List<Map<String, String>>) returnObject.get("RESULTSET");
			for (int i = 0; i < list.size(); i++) {
				Map<String, String> childMap = list.get(i);
				TagSearchGetPartialRecords bean = new TagSearchGetPartialRecords();
				bean.setHits(String.valueOf(childMap.get("HITS")));
				bean.setLastName(String.valueOf(childMap.get("LAST_NAME")));
				partialRecordslist.add(bean);
			}

		} catch (Exception e) {
			log.error("psOffNameExecuteQuery", e);
		}

		return partialRecordslist;
	}

	/**
	 * returning GetOffenderRecords table values
	 *
	 * @param searchBean
	 * @return List<TagSearchGetOffenderRecords>
	 */
	public List<TagSearchGetOffenderRecords> searchResultsExecuteQuery(final TagSearchGetOffenderRecords objSearchDao) {
		Map<String, Object> returnObject = null;
		String OffenderIdDisplay = null;
		List<TagSearchGetOffenderRecords> lListObj = null;
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		final String sql = "select offender_id_display from V_HEADER_BLOCK_FN(:USERID) v_header_block where offender_id_display LIKE '%"+objSearchDao.getOffenderIdDisplay()+"'";
		if(objSearchDao.getOffenderIdDisplay() != null) {
			OffenderIdDisplay =  namedParameterJdbcTemplate.queryForObject(sql,createParams("USERID",objSearchDao.getCreateUserId()),String.class);
		}
		SqlParameter[] sqlParameters = new SqlParameter[20];
		sqlParameters = new SqlParameter[] { new SqlOutParameter("P_RESULTSET", OracleTypes.CURSOR),
				new SqlParameter("P_SEARCH_TYPE", OracleTypes.VARCHAR),
				new SqlParameter("P_LAST_NAME", OracleTypes.VARCHAR),
				new SqlParameter("P_FIRST_NAME", OracleTypes.VARCHAR),
				new SqlParameter("P_MIDDLE_NAME", OracleTypes.VARCHAR),
				new SqlParameter("P_IDENTIFIER_TYPE", OracleTypes.VARCHAR),
				new SqlParameter("P_IDENTIFIER_VALUE", OracleTypes.VARCHAR),
				new SqlParameter("P_OFFENDER_ID_DISPLAY", OracleTypes.VARCHAR),
				new SqlParameter("P_SEX_CODE", OracleTypes.VARCHAR),
				new SqlParameter("P_BIRTH_DATE", OracleTypes.TIMESTAMP),
				new SqlParameter("P_BIRTH_YEAR", OracleTypes.VARCHAR),
				new SqlParameter("P_BIRTH_RANGE", OracleTypes.NUMBER),
				new SqlParameter("P_AGEDATE", OracleTypes.TIMESTAMP),
				new SqlParameter("P_AGE_RANGE", OracleTypes.NUMBER),
				new SqlParameter("P_ETHNICITY", OracleTypes.VARCHAR),
				new SqlParameter("P_NAME_VARIATION", OracleTypes.VARCHAR),
				new SqlParameter("P_SWITCH_NAMES", OracleTypes.VARCHAR),
				new SqlParameter("P_BOOK_NO", OracleTypes.VARCHAR) };
		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("TAG_SEARCH").withProcedureName("GET_OFFENDER_RECORDS")
				.declareParameters(sqlParameters);
		inParamMap.put("P_SEARCH_TYPE", objSearchDao.getpSearchType());
		inParamMap.put("P_LAST_NAME", objSearchDao.getpLastName());
		inParamMap.put("P_FIRST_NAME", objSearchDao.getpFirstName());
		inParamMap.put("P_MIDDLE_NAME", objSearchDao.getpMiddleName());
		inParamMap.put("P_IDENTIFIER_TYPE", objSearchDao.getpIdentifierType());
		inParamMap.put("P_IDENTIFIER_VALUE", objSearchDao.getpIdentifierValue());
		inParamMap.put("P_OFFENDER_ID_DISPLAY", OffenderIdDisplay);
		inParamMap.put("P_SEX_CODE", objSearchDao.getpSexCode());
		inParamMap.put("P_BIRTH_DATE", objSearchDao.getpBirthDate());
		inParamMap.put("P_BIRTH_YEAR", objSearchDao.getpBirthYear());
		inParamMap.put("P_BIRTH_RANGE", objSearchDao.getpBirthRange());
		inParamMap.put("P_AGEDATE", objSearchDao.getpAgedate());
		inParamMap.put("P_AGE_RANGE", objSearchDao.getpAgeRange());
		inParamMap.put("P_ETHNICITY", objSearchDao.getpEthnicity());
		inParamMap.put("P_NAME_VARIATION", objSearchDao.getpNameVariation());
		inParamMap.put("P_SWITCH_NAMES", objSearchDao.getpSwitchNames());
		inParamMap.put("P_BOOK_NO", objSearchDao.getpBookNo());
		final SqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		try {

			returnObject = (Map<String, Object>) simpleJDBCCall.withoutProcedureColumnMetaDataAccess()
					.execute(inParameter);
			lListObj = new ArrayList<>();
			final List<Map<String, String>> list = (List<Map<String, String>>) returnObject.get("P_RESULTSET");
			for (int i = 0; i < list.size(); i++) {
				final Map<String, String> childMap = list.get(i);
				final TagSearchGetOffenderRecords bean = new TagSearchGetOffenderRecords();
				bean.setpLastName(childMap.get("LAST_NAME"));
				bean.setpFirstName(childMap.get("FIRST_NAME"));
				bean.setpMiddleName(childMap.get("MIDDLE_NAME"));
				bean.setpSexCode(childMap.get("SEX_CODE"));
				final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
				Date date;
				try {
					date = format.parse(String.valueOf(childMap.get("BIRTH_DATE")));
					bean.setpBirthDate(date);
				} catch (ParseException e) {
					log.error("searchResultsExecuteQuery", e);
				}
				bean.setOffenderId(
						childMap.get("OFFENDER_ID") != null ? String.valueOf(childMap.get("OFFENDER_ID")) : null);
				bean.setRootOffenderId(childMap.get("ROOT_OFFENDER_ID") != null
						? String.valueOf(childMap.get("ROOT_OFFENDER_ID")) : null);
				bean.setOffenderIdDisplay(childMap.get("OFFENDER_ID_DISPLAY") != null
						? String.valueOf(childMap.get("OFFENDER_ID_DISPLAY")) : null);
				bean.setWorkingNameFlag(childMap.get("WORKING_NAME_FLAG"));
				if (childMap.get("OFFENDER_ID") != null) {
					TagSearchGetOffenderRecords beanObj = new TagSearchGetOffenderRecords();
					beanObj.setOffenderIdDisplay(bean.getOffenderId());
					Offenders string = searchResultsPostQuerynameTypeCur(beanObj.getOffenderIdDisplay());
					bean.setNameType(string.getAliasNameType());
				}
				lListObj.add(bean);
			}
		} catch (Exception e) {
			log.error("", e);
		}

		return lListObj;
	}

	/**
	 * returning GetOffenderIdentifiers table values
	 *
	 * @param searchBean
	 * @return List<TagSearchGetOffenderIdentifiers>
	 */
	public List<TagSearchGetOffenderIdentifiers> offIdExecuteQuery(final TagSearchGetOffenderIdentifiers objSearchDao) {
		List<TagSearchGetOffenderIdentifiers> lListObj = null;
		Map<String, Object> returnObject = null;
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[20];
		sqlParameters = new SqlParameter[] { new SqlOutParameter("P_RESULTSET", OracleTypes.CURSOR),
				new SqlParameter("P_OFFENDER_ID", OracleTypes.NUMBER) };
		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("TAG_SEARCH").withProcedureName("GET_OFFENDER_IDENTIFIERS")
				.declareParameters(sqlParameters);
		inParamMap.put("P_RESULTSET", OracleTypes.CURSOR);
		inParamMap.put("P_OFFENDER_ID", objSearchDao.getOffenderId());
		final MapSqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		try {
			returnObject = simpleJDBCCall.execute(inParameter);
			lListObj = new ArrayList<>();
			final List<Map<String, String>> list = (List<Map<String, String>>) returnObject.get("P_RESULTSET");
			for (int i = 0; i < list.size(); i++) {
				final Map<String, String> childMap = list.get(i);
				final TagSearchGetOffenderIdentifiers bean = new TagSearchGetOffenderIdentifiers();
				bean.setIdentifier(String.valueOf(childMap.get("IDENTIFIER")));
				bean.setIdentifierType(String.valueOf(childMap.get("IDENTIFIER_TYPE")));
				bean.setOffenderId(Integer.parseInt(String.valueOf(childMap.get("OFFENDER_ID"))));
				lListObj.add(bean);
			}
		} catch (Exception e) {
			log.error("offIdExecuteQuery", e);
		}
		return lListObj;
	}

	/**
	 * returning PopulateOffDetails table values
	 *
	 * @param searchBean
	 * @return List<TagSearchPopulateOffDetails>
	 */
	@Override
	public List<TagSearchPopulateOffDetails> populateOffDetails(final TagSearchPopulateOffDetails searchBean) {
		Map<String, Object> returnObject = null;
		List<TagSearchPopulateOffDetails> lListObj = null;
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[20];
		sqlParameters = new SqlParameter[] { new SqlParameter("P_ROOT_OFFENDER_ID", OracleTypes.NUMBER),
				new SqlInOutParameter("P_PRISON_LOCATION", OracleTypes.VARCHAR),
				new SqlInOutParameter("P_COMMUNITY_OFFICER", OracleTypes.VARCHAR),
				new SqlInOutParameter("P_PRISON_STATUS", OracleTypes.VARCHAR),
				new SqlInOutParameter("P_COMMUNITY_STATUS", OracleTypes.VARCHAR),
				new SqlInOutParameter("P_OFFENDER_BOOK_ID", OracleTypes.VARCHAR),
				new SqlInOutParameter("P_ADDRESS", OracleTypes.VARCHAR), };
		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("TAG_SEARCH").withProcedureName("POPULATE_OFF_DETAILS")
				.declareParameters(sqlParameters);
		inParamMap.put("P_ROOT_OFFENDER_ID", searchBean.getPRootOffenderId());
		inParamMap.put("P_PRISON_LOCATION", searchBean.getPPrisonLocation());
		inParamMap.put("P_COMMUNITY_OFFICER", searchBean.getPCommunityOfficer());
		inParamMap.put("P_PRISON_STATUS", searchBean.getPPrisonStatus());
		inParamMap.put("P_COMMUNITY_STATUS", searchBean.getPCommunityStatus());
		inParamMap.put("P_OFFENDER_BOOK_ID", searchBean.getPOffenderBookId());
		inParamMap.put("P_ADDRESS", searchBean.getPAddress());
		final SqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		try {
			returnObject = (Map<String, Object>) simpleJDBCCall.withoutProcedureColumnMetaDataAccess()
					.execute(inParameter);
			lListObj = new ArrayList<>();
			final TagSearchPopulateOffDetails bean = new TagSearchPopulateOffDetails();
			bean.setPAddress(
					returnObject.get("P_ADDRESS") != null ? String.valueOf(returnObject.get("P_ADDRESS")) : null);
			bean.setPOffenderBookId(returnObject.get("P_OFFENDER_BOOK_ID") != null
					? new BigDecimal(String.valueOf(returnObject.get("P_OFFENDER_BOOK_ID"))) : null);
			bean.setPCommunityStatus(returnObject.get("P_COMMUNITY_STATUS") != null
					? String.valueOf(returnObject.get("P_COMMUNITY_STATUS")) : null);
			bean.setPPrisonStatus(returnObject.get("P_PRISON_STATUS") != null
					? String.valueOf(returnObject.get("P_PRISON_STATUS")) : null);
			bean.setPCommunityOfficer(returnObject.get("P_COMMUNITY_OFFICER") != null
					? String.valueOf(returnObject.get("P_COMMUNITY_OFFICER")) : null);
			bean.setPPrisonLocation(returnObject.get("P_PRISON_LOCATION") != null
					? String.valueOf(returnObject.get("P_PRISON_LOCATION")) : null);
			lListObj.add(bean);
		} catch (Exception e) {
			log.error("populateOffDetails", e);
		}
		return lListObj;
	}

	/**
	 * returning OffenderPhysicalAttributes table values
	 *
	 * @params offenderBookId
	 * @return List<OffenderPhysicalAttributes>
	 */
	@Override
	public List<OffenderPhysicalAttributes> populateOffDetailsBlockattrCur(final String offenderBookId) {

		final String sql = getQuery("OSIOSEAR_POPULATE_OFF_DETAILS_BLOCK_ATTR_CUR");

		final RowMapper<OffenderPhysicalAttributes> alertOffenderRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderPhysicalAttributes.class, offenderBookingsMapping);
		List<OffenderPhysicalAttributes> refList = new ArrayList<OffenderPhysicalAttributes>();
		try {
			refList = namedParameterJdbcTemplate.query(sql, createParams("OFFENDER_BOOK_ID", offenderBookId),
					alertOffenderRowMapper);
		} catch (Exception e) {
			log.error("populateOffDetailsBlockattrCur", e);
		}
		return refList;
	}

	/**
	 * return Header block details
	 *
	 * @param searchBean
	 * @return List<VHeaderBlock>
	 */
	@Override
	public List<VHeaderBlock> offbkgGlobalQuery(final VHeaderBlock searchBean) {
		final String sql = queryBuilderFactory.getQueryBuilder(getQuery("FIND_V_HEADER_BLOCK"), vheaderBlock).build();
		final String ConditonSql = queryBuilderFactory.getQueryBuilder(getQuery("OSIOSEAR_GET_ASSIGNED_CONDITIONS_OFFENDERS"), vheaderBlock).build();
		final String sqlTemp = getQuery("OSIOSEAR_GET_V_TRUST_HEADER_COUNT");
		final RowMapper<VHeaderBlock> aliasesRowMapper = Row2BeanRowMapper.makeMapping(sql, VHeaderBlock.class,
				vheaderBlock);
		String preparedSql = null;
		String CondPreparedSql = null;
		final MapSqlParameterSource inParameterSource = new MapSqlParameterSource();
		final StringBuffer sqlQuery = new StringBuffer();
		final StringBuffer CondSqlQuery = new StringBuffer(ConditonSql);
		String amendQuery="Select A.*,B.IMAGE_ID, "+ sqlTemp +" FROM ( ";
		sqlQuery.append(amendQuery);
		sqlQuery.append(sql);
		if (searchBean.getCreateuserId() != null) {
			inParameterSource.addValue("userId", searchBean.getCreateuserId());
		}
		if (searchBean.getOffenderId() != null) {
			sqlQuery.append("OFFENDER_ID = :OFFENDER_ID" + " AND ");
			CondSqlQuery.append("V.OFFENDER_ID = :OFFENDER_ID" + " AND ");
			inParameterSource.addValue("OFFENDER_ID", searchBean.getOffenderId());
		}

		if (searchBean.getRootOffenderId() != null) {
			sqlQuery.append("ROOT_OFFENDER_ID = :ROOT_OFFENDER_ID" + " AND ");
			CondSqlQuery.append("V.ROOT_OFFENDER_ID = :ROOT_OFFENDER_ID" + " AND ");
			inParameterSource.addValue("ROOT_OFFENDER_ID", searchBean.getRootOffenderId());
		}
		if (searchBean.getLastName() != null) {
			
			sqlQuery.append("regexp_replace(upper(LAST_NAME),'[- \\,''\"]','','g') LIKE regexp_replace(upper(:LAST_NAME),'[- \\,''\"]','','g')" + " AND ");
			CondSqlQuery.append("regexp_replace(upper(V.LAST_NAME),'[- \\,''\"]','','g') LIKE regexp_replace(upper(:LAST_NAME),'[- \\,''\"]','','g')" + " AND ");
			inParameterSource.addValue("LAST_NAME", searchBean.getLastName() + "%");

		}
		if (searchBean.getFirstName() != null) {
			sqlQuery.append("regexp_replace(upper(FIRST_NAME),'[- \\,''\"]','','g') LIKE regexp_replace(upper(:FIRST_NAME),'[- \\,''\"]','','g')" + " AND ");
			CondSqlQuery.append("regexp_replace(upper(V.FIRST_NAME),'[- \\,''\"]','','g') LIKE regexp_replace(upper(:FIRST_NAME),'[- \\,''\"]','','g')" + " AND ");			 
			inParameterSource.addValue("FIRST_NAME", searchBean.getFirstName() + "%");

		}
		if (searchBean.getMiddleName() != null) {
			sqlQuery.append("regexp_replace(upper(MIDDLE_NAME),'[- \\,''\"]','','g') LIKE regexp_replace(upper(:MIDDLE_NAME),'[- \\,''\"]','','g')" + " AND ");
			CondSqlQuery.append("regexp_replace(upper(V.MIDDLE_NAME),'[- \\,''\"]','','g') LIKE regexp_replace(upper(:MIDDLE_NAME),'[- \\,''\"]','','g')" + " AND ");		 
			inParameterSource.addValue("MIDDLE_NAME", searchBean.getMiddleName() + "%");

		}
		if (searchBean.getBirthDate() != null) {
			sqlQuery.append("BIRTH_DATE =  :BIRTH_DATE" + " AND ");
			CondSqlQuery.append("V.BIRTH_DATE =  :BIRTH_DATE" + " AND ");
			inParameterSource.addValue("BIRTH_DATE", searchBean.getBirthDate());

		}
		if (searchBean.getBookingNo() != null && !searchBean.getBookingNo().isEmpty()) {
			sqlQuery.append("LTRIM(BOOKING_NO::text, '0') like LTRIM(:BOOKING_NO::text,'0')" + " AND ");
			CondSqlQuery.append("LTRIM(V.BOOKING_NO::text, '0') like LTRIM(:BOOKING_NO::text,'0')" + " AND ");
			inParameterSource.addValue("BOOKING_NO", searchBean.getBookingNo());

		}
		if (searchBean.getOffenderBookId() != null) {
			sqlQuery.append("OFFENDER_BOOK_ID = :OFFENDER_BOOK_ID" + " AND ");
			CondSqlQuery.append("V.OFFENDER_BOOK_ID = :OFFENDER_BOOK_ID" + " AND ");
			inParameterSource.addValue("OFFENDER_BOOK_ID", searchBean.getOffenderBookId());

		}
		if (searchBean.getOffenderIdDisplay() != null) {
			sqlQuery.append("LTRIM(OFFENDER_ID_DISPLAY::text, '0') like LTRIM(:OFFENDER_ID_DISPLAY::text,'0')" + " AND ");
			CondSqlQuery.append("LTRIM(V.OFFENDER_ID_DISPLAY::text, '0') like LTRIM(:OFFENDER_ID_DISPLAY::text,'0')" + " AND ");
			inParameterSource.addValue("OFFENDER_ID_DISPLAY",searchBean.getOffenderIdDisplay());

		}
		if (searchBean.getAgyLocId() != null && !searchBean.getAgyLocId().endsWith("OIIGRIEV") ) {
			if(searchBean.getAgyLocType().equalsIgnoreCase("INST")) {
				
				sqlQuery.append("  ( V.OFFENDER_BOOK_ID IN"
				+ " ( SELECT vhb2.OFFENDER_BOOK_ID FROM offender_bookings vhb2 WHERE vhb2.root_offender_id = V.root_offender_id AND vhb2.active_flag='Y' "
				+ " AND EXISTS ( SELECT cal.agy_loc_id FROM caseload_agency_locations cal, caseloads csld WHERE cal.caseload_id = :AGY_LOC_ID "
				+ " AND csld.caseload_type = 'INST' AND csld.caseload_id = cal.caseload_id AND cal.agy_loc_id = vhb2.agy_loc_id AND :agyLocTpye = 'INST')) "
				+ " OR ( V.OFFENDER_BOOK_ID = ( SELECT MAX(vhb2.OFFENDER_BOOK_ID) FROM offender_bookings vhb2 "
				+ " WHERE vhb2.root_offender_id = V.root_offender_id AND EXISTS ( SELECT cal.agy_loc_id FROM caseload_agency_locations cal, caseloads csld "
				+ " WHERE cal.caseload_id = :AGY_LOC_ID AND csld.caseload_type = 'INST' AND csld.caseload_id = cal.caseload_id AND cal.agy_loc_id = vhb2.agy_loc_id "
				+ " AND :agyLocTpye = 'INST') AND NOT EXISTS ( SELECT '1' FROM OFFENDER_BOOKINGS BK,caseload_agency_locations cal,caseloads csld "
				+ " WHERE BK.ROOT_OFFENDER_ID = vhb2.ROOT_OFFENDER_ID AND BK.ACTIVE_FLAG = 'Y' AND csld.caseload_type = 'INST' AND csld.caseload_id = cal.caseload_id "
				+ " AND cal.agy_loc_id = BK.agy_loc_id AND :agyLocTpye = 'INST') ))) ");
			}
			if(searchBean.getAgyLocType().equalsIgnoreCase("COMM")) {
				
				sqlQuery.append("(  V.offender_book_id = ( SELECT MAX(vhb2.offender_book_id) "
						+ " FROM offender_bookings vhb2 WHERE vhb2.root_offender_id = V.root_offender_id "
						+ " AND ( vhb2.community_active_flag = 'Y' OR (NOT EXISTS( SELECT 'X' FROM offender_bookings vhb3 WHERE vhb3.community_active_flag = 'Y' "
						+ " AND vhb3.root_offender_id = vhb2.root_offender_id))) AND ((vhb2.intake_agy_loc_id <> 'CLOSE' AND EXISTS "
						+ " ( SELECT 'X' FROM offender_booking_agy_locs locs, caseload_agency_locations cal1  WHERE cal1.caseload_id =:AGY_LOC_ID "
						+ " AND cal1.agy_loc_id = locs.agy_loc_id AND locs.offender_book_id = vhb2.offender_book_id AND locs.removed_date IS  NULL "
						+ " AND :agyLocTpye  = 'COMM')) OR (vhb2.intake_agy_loc_id = 'CLOSE' AND EXISTS ( SELECT 'X'  FROM offender_booking_agy_locs locs, "
						+ " caseload_agency_locations cal1 WHERE cal1.caseload_id = :AGY_LOC_ID AND cal1.agy_loc_id = locs.agy_loc_id "
						+ " AND locs.offender_book_id = vhb2.offender_book_id AND :agyLocTpye  = 'COMM')))))");
			}
			sqlQuery.append("order by LAST_NAME ASC, FIRST_NAME ASC )"
					+ "A LEFT JOIN (select IMAGE_ID,IMAGE_OBJECT_ID FROM IMAGES where ACTIVE_FLAG = 'Y'AND IMAGE_OBJECT_TYPE = 'OFF_BKG' AND IMAGE_VIEW_TYPE = 'FACE')B "
					+ "on   A.OFFENDER_BOOK_ID = B.IMAGE_OBJECT_ID  ");
			// Jira
			// issue
			// 1679
			// modified
			// to
			// BOOKING_END_DATE
			// in
			// ordering
			inParameterSource.addValue("AGY_LOC_ID", searchBean.getAgyLocId());
			inParameterSource.addValue("agyLocTpye", searchBean.getAgyLocType());
			preparedSql = sqlQuery.toString().trim();
		} else {
			preparedSql = sqlQuery.toString().trim();
			if (preparedSql.endsWith("WHERE")) {
				preparedSql = preparedSql.substring(0, preparedSql.length() - 5);
			}
			if (preparedSql.endsWith("AND")) {
				preparedSql = preparedSql.substring(0, preparedSql.length() - 3);
			}
			preparedSql =  preparedSql + ") A " +
					" LEFT JOIN (select IMAGE_ID,IMAGE_OBJECT_ID FROM IMAGES where ACTIVE_FLAG = 'Y'AND IMAGE_OBJECT_TYPE = 'OFF_BKG' "
					+ " AND IMAGE_VIEW_TYPE = 'FACE')B " +
					" on   A.OFFENDER_BOOK_ID = B.IMAGE_OBJECT_ID ";
		}

		if (preparedSql.endsWith("WHERE")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 5);
		}
		if (preparedSql.endsWith("AND")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 3);
		}
		if(searchBean.getAgyLocType().equalsIgnoreCase("COMM")) {
				if(CondSqlQuery != null) {
					inParameterSource.addValue("AGY_LOC_ID", searchBean.getAgyLocId());
					inParameterSource.addValue("agyLocTpye", searchBean.getAgyLocType());
					
				CondPreparedSql = CondSqlQuery.toString().trim();
				if (CondPreparedSql.endsWith("WHERE")) {
					CondPreparedSql = CondPreparedSql.substring(0, CondPreparedSql.length() - 5);
				}
				if (CondPreparedSql.endsWith("AND")) {
					CondPreparedSql = CondPreparedSql.substring(0, CondPreparedSql.length() - 3);
				}
				
				preparedSql = preparedSql + " UNION " + CondPreparedSql + " ) A LEFT JOIN ( SELECT IMAGE_ID, IMAGE_OBJECT_ID FROM IMAGES WHERE ACTIVE_FLAG = 'Y' AND IMAGE_OBJECT_TYPE = 'OFF_BKG' AND IMAGE_VIEW_TYPE = 'FACE' ) B ON A.OFFENDER_BOOK_ID = B.IMAGE_OBJECT_ID";
			}
		}
		return namedParameterJdbcTemplate.query(preparedSql, inParameterSource, aliasesRowMapper);
	}

	/**
	 * Method is used to get the data from VPropertyHeaderBlock
	 *
	 * @param searchBean
	 * @return List<VPropertyHeaderBlock>
	 */
	@Override
	public List<VPropertyHeaderBlock> offbkgVPHeadGlobalQuery(final VPropertyHeaderBlock searchBean) {
		final String sql = queryBuilderFactory.getQueryBuilder(getQuery("FIND_V_PROPERTY_HEADER_BLOCK_FN"), vheaderBlock)
				.build();
		final RowMapper<VPropertyHeaderBlock> aliasesRowMapper = Row2BeanRowMapper.makeMapping(sql, VPropertyHeaderBlock.class,
				vheaderBlock);
		String preparedSql = null;
		final MapSqlParameterSource inParameterSource = new MapSqlParameterSource();
		final StringBuffer sqlQuery = new StringBuffer();
		String amendQuery="Select A.*,B.IMAGE_ID FROM ( ";
		sqlQuery.append(amendQuery);
		sqlQuery.append(sql);
		if (searchBean != null) {
			sqlQuery.append(" WHERE ");
            if(searchBean.getCreateUserId()!=null) {
            	inParameterSource.addValue("userId", searchBean.getCreateUserId());
            }
			if (searchBean.getOffenderId() != null) {
				sqlQuery.append("OFFENDER_ID = :OFFENDER_ID" + " AND ");
				inParameterSource.addValue("OFFENDER_ID", searchBean.getOffenderId());
			}

			if (searchBean.getRootOffenderId() != null) {
				sqlQuery.append("ROOT_OFFENDER_ID = :ROOT_OFFENDER_ID" + " AND ");
				inParameterSource.addValue("ROOT_OFFENDER_ID", searchBean.getRootOffenderId());
			}
			if (searchBean.getLastName() != null && searchBean.getLastName().trim().length() > 0) {			
				sqlQuery.append("regexp_replace(upper(LAST_NAME),'[- \\,''\"]','','g') LIKE regexp_replace(upper(:LAST_NAME),'[- \\,''\"]','','g')" + " AND ");
				inParameterSource.addValue("LAST_NAME", searchBean.getLastName() + "%");

			}
			if (searchBean.getFirstName() != null && searchBean.getFirstName().trim().length() > 0) {
				sqlQuery.append("regexp_replace(upper(FIRST_NAME),'[- \\,''\"]','','g') LIKE regexp_replace(upper(:FIRST_NAME),'[- \\,''\"]','','g')" + " AND ");
				inParameterSource.addValue("FIRST_NAME", searchBean.getFirstName() + "%");

			}
			if (searchBean.getMiddleName() != null && searchBean.getMiddleName().trim().length() > 0) {
				sqlQuery.append("regexp_replace(upper(MIDDLE_NAME),'[- \\,''\"]','','g') LIKE regexp_replace(upper(:MIDDLE_NAME),'[- \\,''\"]','','g')" + " AND ");
				inParameterSource.addValue("MIDDLE_NAME", searchBean.getMiddleName() + "%");

			}
			if (searchBean.getBirthDate() != null) {
				sqlQuery.append("BIRTH_DATE =  :BIRTH_DATE" + " AND ");
				inParameterSource.addValue("BIRTH_DATE", searchBean.getBirthDate());

			}
			if (searchBean.getBookingNo() != null && searchBean.getBookingNo().trim().length() > 0) {
				sqlQuery.append("LTRIM(BOOKING_NO::text, '0') like LTRIM(:BOOKING_NO::text,'0')" + " AND ");
				inParameterSource.addValue("BOOKING_NO", searchBean.getBookingNo());

			}
			if (searchBean.getOffenderBookId() != null) {
				sqlQuery.append("OFFENDER_BOOK_ID = :OFFENDER_BOOK_ID" + " AND ");
				inParameterSource.addValue("OFFENDER_BOOK_ID", searchBean.getOffenderBookId());

			}
			if (searchBean.getOffenderIdDisplay() != null && searchBean.getOffenderIdDisplay().trim().length() > 0) {
				sqlQuery.append("OFFENDER_ID_DISPLAY LIKE :OFFENDER_ID_DISPLAY" + " AND ");
				inParameterSource.addValue("OFFENDER_ID_DISPLAY", "%"+searchBean.getOffenderIdDisplay());

			}
			if (searchBean.getAgyLocId() != null) {
				sqlQuery.append(":GLOBALCASELOADID = ( SELECT csld.caseload_id FROM caseloads csld WHERE csld.caseload_id = :GLOBALCASELOADID AND csld.caseload_type = 'INST') "
						+ " AND  (    (v_property_header_block_fn.agy_loc_id IN "
						+ " (SELECT cal.agy_loc_id  FROM caseload_agency_locations cal  WHERE cal.caseload_id = :GLOBALCASELOADID AND cal.agy_loc_id NOT IN ('TRN')) )"
						+ "  OR ( v_property_header_block_fn.active_flag = 'Y' AND EXISTS( SELECT opi.offender_book_id FROM offender_ppty_items opi,offender_bookings off_bkg1"
						+ " WHERE opi.offender_book_id = off_bkg1.offender_book_id  AND off_bkg1.root_offender_id = v_property_header_block_fn.root_offender_id"
						+ " AND opi.agy_loc_id IN ( SELECT cal.agy_loc_id FROM caseload_agency_locations cal WHERE cal.caseload_id = :GLOBALCASELOADID AND cal.agy_loc_id NOT IN ('TRN'))))"
						+ "  OR ( v_property_header_block_fn.offender_book_id = ( SELECT MAX(vphb1.offender_book_id) FROM v_property_header_block_fn(:userId) vphb1"
						+ " WHERE vphb1.root_offender_id = v_property_header_block_fn.root_offender_id AND NOT EXISTS ( SELECT NULL  FROM offender_bookings off_bkg2"
						+ " WHERE off_bkg2.root_offender_id = v_property_header_block_fn.root_offender_id  AND off_bkg2.active_flag = 'Y')))) order by LAST_NAME ASC, FIRST_NAME ASC  )"
						+ "A LEFT JOIN (select IMAGE_ID,IMAGE_OBJECT_ID FROM IMAGES where ACTIVE_FLAG = 'Y'AND IMAGE_OBJECT_TYPE = 'OFF_BKG' AND IMAGE_VIEW_TYPE = 'FACE')B "
						+ "on   A.OFFENDER_BOOK_ID = B.IMAGE_OBJECT_ID  ");

				inParameterSource.addValue("GLOBALCASELOADID", searchBean.getAgyLocId());

			}
		}
		preparedSql = sqlQuery.toString().trim();
		if (preparedSql.endsWith("WHERE")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 5);
		}
		if (preparedSql.endsWith("AND")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 3);
		}

		return namedParameterJdbcTemplate.query(preparedSql, inParameterSource, aliasesRowMapper);
	}



	/**
	 * return Header block details
	 *
	 * @param searchBean
	 * @return List<VHeaderBlock>
	 */
	@Override
	public List<VHeaderBlock2> searchVHeaderBlock2(final VHeaderBlock2 searchBean) {
		final String sql = queryBuilderFactory.getQueryBuilder(getQuery("FIND_V_HEADER_BLOCK2"), vheaderBlock).build();
		final RowMapper<VHeaderBlock2> aliasesRowMapper = Row2BeanRowMapper.makeMapping(sql, VHeaderBlock2.class,
				vheaderBlock);
		String preparedSql = null;
		String preparedSql2=null;
		final MapSqlParameterSource inParameterSource = new MapSqlParameterSource();
		final StringBuffer sqlQuery = new StringBuffer();
		final StringBuffer sqlQuery2 = new StringBuffer();
		final StringBuffer sqlQuery3 = new StringBuffer();
		sqlQuery3.append("");
		sqlQuery2.append("where ");
		sqlQuery.append(sql);
		if(searchBean.getCreateUserId()!=null) {
			inParameterSource.addValue("username", searchBean.getCreateUserId());
		}
		if (searchBean.getOffenderId() != null) {
			sqlQuery.append("OFFENDER_ID = :OFFENDER_ID" + " and ");
			sqlQuery2.append("OFFENDER_ID = :OFFENDER_ID"+ " and ");
			inParameterSource.addValue("OFFENDER_ID", searchBean.getOffenderId());
		}
		if (searchBean.getOffenderIdDisplay() != null) {
			sqlQuery.append("LTRIM(OFFENDER_ID_DISPLAY::text, '0') like LTRIM(:OFFENDER_ID_DISPLAY::text,'0')" + " and ");
			sqlQuery2.append("LTRIM(OFFENDER_ID_DISPLAY::text, '0') like LTRIM(:OFFENDER_ID_DISPLAY::text,'0')"+ " and ");
			inParameterSource.addValue("OFFENDER_ID_DISPLAY",searchBean.getOffenderIdDisplay());
		}
		if (searchBean.getLastName() != null) {
			sqlQuery.append("regexp_replace(upper(LAST_NAME),'[- \\,''\"]','','g') LIKE regexp_replace(upper(:LAST_NAME),'[- \\,''\"]','','g')" + " AND ");
			sqlQuery2.append("regexp_replace(upper(LAST_NAME),'[- \\\\,''\\\"]','','g') like regexp_replace(upper(:LAST_NAME),'[- \\\\,''\\\"]','','g')"+ " and ");
			inParameterSource.addValue("LAST_NAME", searchBean.getLastName() + "%");
		}
		if (searchBean.getFirstName() != null) {
			sqlQuery.append("regexp_replace(upper(FIRST_NAME),'[- \\,''\"]','','g') LIKE regexp_replace(upper(:FIRST_NAME),'[- \\,''\"]','','g')" + " AND ");
			sqlQuery2.append("regexp_replace(upper(FIRST_NAME),'[- \\\\,''\\\"]','','g') like regexp_replace(upper(:FIRST_NAME),'[- \\\\,''\\\"]','','g')"+ " and ");
			inParameterSource.addValue("FIRST_NAME", searchBean.getFirstName() + "%");
		}
		if (searchBean.getMiddleName() != null) {
			sqlQuery.append("regexp_replace(upper(MIDDLE_NAME),'[- \\,''\"]','','g') LIKE regexp_replace(upper(:MIDDLE_NAME),'[- \\,''\"]','','g')" + " AND ");
			sqlQuery2.append("regexp_replace(upper(MIDDLE_NAME),'[- \\\\,''\\\"]','','g') like regexp_replace(upper(:MIDDLE_NAME),'[- \\\\,''\\\"]','','g')"+ " and ");
			inParameterSource.addValue("MIDDLE_NAME", searchBean.getMiddleName() + "%");

		}
		if (searchBean.getBirthDate() != null) {
			sqlQuery.append("BIRTH_DATE =  :BIRTH_DATE" + " and ");
			sqlQuery2.append("BIRTH_DATE =  :BIRTH_DATE"+ " and ");
			inParameterSource.addValue("BIRTH_DATE", searchBean.getBirthDate());
		}
		if (searchBean.getBookingNo() != null && !searchBean.getBookingNo().isEmpty()) {
			sqlQuery.append("LTRIM(BOOKING_NO::text, '0') like LTRIM(:BOOKING_NO::text,'0')" + " and ");
			sqlQuery3.append("(select * from offender_bookings where LTRIM(BOOKING_NO::text, '0') like LTRIM(:BOOKING_NO::text,'0')) ");
			inParameterSource.addValue("BOOKING_NO", searchBean.getBookingNo());
		}
		if (searchBean.getRootOffenderId() != null ) {
			sqlQuery.append(" ROOT_OFFENDER_ID = :ROOT_OFFENDER_ID" + " and ");
			inParameterSource.addValue("ROOT_OFFENDER_ID", searchBean.getRootOffenderId());
		}
		if (searchBean.getAgyLocId() != null) {
			sqlQuery.append(
					"((V_HEADER_BLOCK2_FN.OFFENDER_BOOK_ID =(SELECT MAX(ob.offender_book_id) FROM OFFENDER_BOOKINGS OB,  OFFENDERS O "
							+ "WHERE O.OFFENDER_ID = OB.OFFENDER_ID AND  OB.BOOKING_TYPE != 'INST' AND (O.OFFENDER_ID = V_HEADER_BLOCK2_FN.ROOT_OFFENDER_ID "
							+ " OR O.ALIAS_OFFENDER_ID = V_HEADER_BLOCK2_FN.ROOT_OFFENDER_ID)))"
							+ " OR (V_HEADER_BLOCK2_FN.OFFENDER_BOOK_ID = "
							+ " (SELECT MAX(OB.OFFENDER_BOOK_ID) FROM OFFENDER_BOOKINGS OB, OFFENDERS O  "
							+ "  WHERE O.OFFENDER_ID = OB.OFFENDER_ID AND OB.BOOKING_TYPE = 'INST'"
							+ " AND (O.OFFENDER_ID = V_HEADER_BLOCK2_FN.ROOT_OFFENDER_ID OR O.ALIAS_OFFENDER_ID = V_HEADER_BLOCK2_FN.ROOT_OFFENDER_ID))  "
							+ " AND NOT EXISTS (SELECT 'X' FROM OFFENDER_BOOKINGS OB WHERE BOOKING_TYPE != 'INST' "
							+ "  AND OB.OFFENDER_ID = V_HEADER_BLOCK2_FN.OFFENDER_ID))  OR  (V_HEADER_BLOCK2_FN.OFFENDER_BOOK_ID = 0 "
							+ " AND NOT EXISTS (SELECT 'X' FROM  OFFENDER_BOOKINGS OB, OFFENDERS  O "
							+ " WHERE O.OFFENDER_ID = OB.OFFENDER_ID AND (O.OFFENDER_ID = V_HEADER_BLOCK2_FN.ROOT_OFFENDER_ID "
							+ " OR O.ALIAS_OFFENDER_ID = V_HEADER_BLOCK2_FN.ROOT_OFFENDER_ID))))");
							
							inParameterSource.addValue("agy_loc_id", searchBean.getAgyLocId());
							inParameterSource.addValue("username", searchBean.getCreateUserId());
		}
		String s = sqlQuery.toString().trim();
		if(s.endsWith("and")) {
			s = sqlQuery.substring(0, s.length() - 3);
			sqlQuery.setLength(0);
			sqlQuery.append(s);
		}
		sqlQuery.append( ") A "
				+ "LEFT JOIN (select IMAGE_ID,IMAGE_OBJECT_ID FROM IMAGES where ACTIVE_FLAG = 'Y' AND IMAGE_OBJECT_TYPE = 'OFF_BKG' AND IMAGE_VIEW_TYPE = 'FACE') B ON A.OFFENDER_BOOK_ID = B.IMAGE_OBJECT_ID left join "
				+ "( select distinct off_name.offender_id, off_name.alias_offender_id, off_name.offender_id_display, off_name.last_name, off_name.first_name, off_name.middle_name, off_name.suffix, off_name.birth_date, "
				+ "coalesce(off_bkg.seal_flag , 'N'), off_bkg.offender_book_id, off_bkg.booking_no, off_bkg.booking_begin_date, off_bkg.booking_end_date, off_bkg.agy_loc_id, off_bkg.agency_iml_id, off_bkg.living_unit_id, "
				+ "off_bkg.disclosure_flag, off_bkg.active_flag, off_bkg.booking_status, substr(tag_header_get_header_location_u(off_bkg.active_flag, off_bkg.community_active_flag, off_bkg.community_agy_loc_id, off_bkg.agy_loc_id, "
				+ "off_bkg.living_unit_id, off_bkg.comm_staff_role, off_bkg.comm_staff_id::bigint, :username)::text, 0, 100) as living_unit_description, off_bkg.in_out_status, substr(tag_header_get_header_status_u(off_bkg.active_flag, off_bkg.community_active_flag, off_bkg.status_reason, mov_rsn.header_status_flag, off_bkg.comm_status, "
				+ "off_bkg.in_out_status, off_bkg.root_offender_id, off_bkg.offender_book_id, :username)::text, 0, 100) as status_display, off_name.root_offender_id, off_bkg.assigned_staff_id, substr(tag_header_get_location_type(off_bkg.agy_loc_id)::text, 0, 12) as agy_loc_type, off_bkg.create_agy_loc_id, off_bkg.booking_type, off_bkg.booking_created_date, "
				+ "substr(tag_header_get_int_location_code(off_bkg.agency_iml_id)::text, 0, 10) as location_code, substr(tag_header_get_living_desc(off_bkg.agy_loc_id, off_bkg.living_unit_id)::text, 0, 100) as liv_unit_desc, off_bkg.intake_agy_loc_id, off_bkg.community_active_flag, off_bkg.create_intake_agy_loc_id, off_bkg.comm_status as community_status, "
				+ "off_bkg.status_reason, mov_rsn.header_status_flag as header_status, tag_header_get_age(off_name.birth_date) as age, substr(oms_miscellaneous_getdesccode('SEX'::character varying, off_name.sex_code)::text, 0, 10) as sex, substr(oms_miscellaneous_getdesccode('GENDER'::character varying, off_name.gender_code)::text, 0, 10) as gender, "
				+ "substr(tag_header_get_officer_u(off_bkg.offender_book_id, :username)::text, 0, 105) as officer, substr(tag_header_get_prison_location_u(off_bkg.active_flag, off_bkg.community_active_flag, off_bkg.intake_agy_loc_id, off_bkg.agy_loc_id, off_bkg.living_unit_id, off_bkg.agency_iml_id, off_bkg.offender_book_id, :username)::text, 0, 105) as prison_location, "
				+ "substr(omkhead_get_alerts(off_bkg.offender_book_id)::text, 0, 40) as off_alerts, substr(tag_header_get_status_1_u(off_bkg.offender_book_id, off_bkg.in_out_status, off_bkg.comm_status, off_bkg.status_reason, :username)::text, 0, 40) as status_1, substr(tag_header_get_status_2_u(off_bkg.offender_book_id, :username)::text, 0, 40) as status_2, "
				+ "substr(tag_header_get_status_3(off_bkg.offender_book_id)::text, 0, 40) as status_3, substr(oms_miscellaneous_getdesccode('ETHNICITY'::character varying, off_name.race_code)::text, 0, 40) as ethnicity, ( select oem.movement_reason_code from offender_external_movements oem where oem.offender_book_id = off_bkg.offender_book_id and oem.movement_seq = (( select max(oem2.movement_seq) as max from offender_external_movements oem2 where oem2.offender_book_id = oem.offender_book_id)) and "
				+ "(exists ( select 'X'::text as text from movement_reasons mr where mr.movement_type::text = oem.movement_type::text and mr.movement_reason_code::text = oem.movement_reason_code::text and mr.header_status_flag::text = 'Y'::text))) as movement_reason, oms_intake_get_offender_supervision_u(off_bkg.offender_book_id, :username) as off_sup_level from ( select * from offenders  %DYNAMIC_VALUE% ) off_name, %BOOKING_NO%"
				+ " off_bkg left join movement_reasons mov_rsn on substr(off_bkg.status_reason::text, 1::numeric, instr(off_bkg.status_reason::text, '-'::text, 1::numeric) - 1::numeric) = mov_rsn.movement_type::text and substr(off_bkg.status_reason::text, instr(off_bkg.status_reason::text, '-'::text, 1::numeric) + 1::numeric) = mov_rsn.movement_reason_code::text where off_name.offender_id = off_bkg.offender_id)C on A.offender_book_id = C.offender_book_id");
		preparedSql2 = sqlQuery2.toString().trim();
		if (preparedSql2.endsWith("and")) {
			preparedSql2 = preparedSql2.substring(0, preparedSql2.length() - 3);
		}
		preparedSql = sqlQuery.toString().trim().replace("%DYNAMIC_VALUE%", ("where".equals(preparedSql2.toString().trim()) ? "" : preparedSql2));
		preparedSql =preparedSql.toString().trim().replace("%BOOKING_NO%",("".equals(sqlQuery3.toString()) ? "offender_bookings" : sqlQuery3));
		
		if (preparedSql.endsWith("WHERE")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 5);
		}
		if (preparedSql.endsWith("and")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 3);
		}
		return namedParameterJdbcTemplate.query(preparedSql, inParameterSource, aliasesRowMapper);
	}

	/**
	 * getting rgGender LOV values
	 *
	 * @param referenceCodes
	 * @return List<ReferenceCodes>
	 */
	@Override
	public List<ReferenceCodes> rgGenderRecordGroup(final ReferenceCodes referenceCodes) {
		final String sql = getQuery("OSIOSEAR_FIND_RGGENDER");
		final RowMapper<ReferenceCodes> referenceCodeRowMapper = Row2BeanRowMapper.makeMapping(sql,
				ReferenceCodes.class, referenceCodeMapping);
		List<ReferenceCodes> refList = new ArrayList<ReferenceCodes>();
		try {
			refList = namedParameterJdbcTemplate.query(sql, referenceCodeRowMapper);
		} catch (Exception e) {
			log.error("rgGenderRecordGroup", e);
		}
		return refList;
	}

	/**
	 * getting rgCrtLocation LOV values
	 *
	 * @param caseloadIid
	 * @return List<ReferenceCodes>
	 */
	@Override
	public List<ReferenceCodes> rgCrtLocationRecordGroup(final String caseLoadId) {
		ReferenceCodes objBaseBE;
		final List<ReferenceCodes> lstSerachRecords = new ArrayList<>();
		int count = 1;
		final String sql = getQuery("OSIOSEAR_FIND_RGCRTLOCATION");
		List<Map<String, Object>> lListObj;
		try {
			lListObj = namedParameterJdbcTemplate.queryForList(sql, createParams("CASELOAD_ID", caseLoadId));
			for (Map<String, Object> objSearchDAO : lListObj) {
				objBaseBE = new ReferenceCodes();
				for (Map.Entry<String, Object> list : objSearchDAO.entrySet()) {
					if (count == 2) {
						objBaseBE.setNewCode((String) list.getValue());
					} else {
						objBaseBE.setId(list.getValue().toString());
					}
					count++;
				}
				count = 1;
				lstSerachRecords.add(objBaseBE);
			}
		} catch (Exception e) {
			log.error("rgCrtLocationRecordGroup", e);
		}
		return lstSerachRecords;
	}

	/**
	 * returning max bookId from offenderBookings
	 *
	 * @params rootOffenderId
	 * @return String
	 */
	@Override
	public String getLatestBooking(final String rootOffenderId) {
		final String sql = getQuery("OSIOSEAR_GET_LATEST_BOOKING");
		String str = null;
		try {
			str = namedParameterJdbcTemplate.queryForObject(sql, createParams("ROOT_OFFENDER_ID", rootOffenderId),
					String.class);
		} catch (Exception e) {
			log.error("getLatestBooking", e);
		}
		return str;
	}

	/**
	 * @params const0
	 * @return List<ProfileCodes>
	 */
	@Override
	public List<ProfileCodes> profCodeDescpd(final ProfileCodes searchBean) {
		final String sql = getQuery("FIND_CODE_DESCRIPTION_PROFILECODES");
		List<ProfileCodes> returnList = new ArrayList<ProfileCodes>();
		final RowMapper<ProfileCodes> alertOffenderRowMapper = Row2BeanRowMapper.makeMapping(sql, ProfileCodes.class,
				profileCodes);
		try {
			returnList = namedParameterJdbcTemplate.query(sql,
					createParams("PROFILE_CODE", searchBean.getProfileCode()), alertOffenderRowMapper);
		} catch (Exception e) {
			log.error("profCodeDescpd", e);
		}
		return returnList;
	}

	/**
	 * @params imageObjectId,imageObjectType
	 * @return List<String>
	 */
	@Override
	public List<String> captureImageFindImageCur(final String imageObjectId, final String imageObjectType) {
		final String sql = getQuery("FIND_IMAGES_GETTING_1");
		List<String> returnList = new ArrayList<String>();
		final RowMapper<String> alertOffenderRowMapper = Row2BeanRowMapper.makeMapping(sql, String.class, images);
		try {
			returnList = namedParameterJdbcTemplate.query(sql,
					createParams("IMAGE_OBJECT_ID", imageObjectId, "IMAGE_OBJECT_TYPE", imageObjectType),
					alertOffenderRowMapper);
			if (returnList != null) {
				for (int i = 0; i < returnList.size(); i++) {
					returnList.set(0, "1");
					returnList.set(1, "1");
				}
			}
		} catch (Exception e) {
			log.error("captureImageFindImageCur", e);
		}
		return returnList;
	}

	/**
	 * @params rootOffenderId,rootOffenderIdd
	 * @return List<OffenderBookings>>
	 */
	@Override
	public List<OffenderBookings> getOffenderBookIdGetbookId(final String rootOffenderId,
															 final String rootOffenderIdd) {

		final String sql = getQuery("OSIOSEAR_GET_OFFENDER_BOOK_ID_GET_BOOK_ID");
		final OffenderBookings returnObject = new OffenderBookings();
		final RowMapper<OffenderBookings> alertOffenderRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderBookings.class, offenderBookingsMapping);
		final List<OffenderBookings> refList = new ArrayList<OffenderBookings>();
		try {
			Object obj = (namedParameterJdbcTemplate.queryForObject(sql, createParams("ROOT_OFFENDER_ID",
					rootOffenderId, "ROOT_OFFENDER_ID", rootOffenderIdd, alertOffenderRowMapper), Object.class));
			if (obj != null) {
				returnObject.setOffenderBookId(new Long(obj.toString()));
				refList.add(returnObject);
			}
		} catch (Exception e) {
			log.error("getOffenderBookIdGetbookId", e);
		}
		return refList;
	}

	/**
	 * @params const0
	 * @return List<OmsModules>
	 */
	@Override
	public List<OmsModules> createFormGlobals(final String const0) {
		final String sql = getQuery("OSIOSEAR_CREATE_FORM_GLOBALS");
		List<OmsModules> returnList = new ArrayList<>();
		final RowMapper<OmsModules> alertOffenderRowMapper = Row2BeanRowMapper.makeMapping(sql, OmsModules.class,
				omsModules);
		try {
			returnList = (namedParameterJdbcTemplate.query(sql, createParams("MODULE_NAME", const0),
					alertOffenderRowMapper));
		} catch (Exception e) {
			log.error("createFormGlobals", e);
		}
		return returnList;
	}

	/**
	 * @param offenderId
	 * @return List<OffenderBookings>
	 */
	@Override
	public List<OffenderBookings> searchResultsWhenNewItemInstanceLvobi(final String offenderId) {
		final String sql = getQuery("OSIOSEAR_SEARCH_RESULTS_WHENNEWITEMINSTANCE_LV_OBI");
		final OffenderBookings returnObject = new OffenderBookings();

		final List<OffenderBookings> refList = new ArrayList<OffenderBookings>();
		try {
			final Object obj = namedParameterJdbcTemplate.queryForObject(sql, createParams("OFFENDER_ID", offenderId),
					Object.class);
			if (obj != null) {
				returnObject.setOffenderBookId(new Long(obj.toString()));
				refList.add(returnObject);
			}
		} catch (Exception e) {
			log.error("searchResultsWhenNewItemInstanceLvobi", e);
		}
		return refList;
	}

	/**
	 * @params profileType
	 * @return List<ProfileTypes>
	 */
	@Override
	public List<ProfileTypes> profTypeDescpc(final String profileType) {
		final String sql = getQuery("OSIOSEAR_PROF_TYPE_DESCP_C");
		ProfileTypes returnObject = new ProfileTypes();
		final RowMapper<ProfileTypes> alertOffenderRowMapper = Row2BeanRowMapper.makeMapping(sql, ProfileTypes.class,
				profileTypes);
		final List<ProfileTypes> refList = new ArrayList<ProfileTypes>();
		try {
			returnObject = namedParameterJdbcTemplate.queryForObject(sql, createParams("PROFILE_TYPE", profileType),
					alertOffenderRowMapper);
			refList.add(returnObject);
		} catch (Exception e) {
			log.error("profTypeDescpc", e);
		}
		return refList;
	}

	/**
	 * @params const0
	 * @return List<ReferenceCodes>
	 */
	@Override
	public List<ReferenceCodes> setInitialSearchTypeSearchCur(final String const0) {
		final String sql = getQuery("OSIOSEAR_SET_INITIAL_SEARCH_TYPE_SEARCH_CUR");
		List<ReferenceCodes> returnList = new ArrayList<ReferenceCodes>();
		final RowMapper<ReferenceCodes> alertOffenderRowMapper = Row2BeanRowMapper.makeMapping(sql,
				ReferenceCodes.class, referenceCodeMapping);
		try {
			returnList = namedParameterJdbcTemplate.query(sql, alertOffenderRowMapper);
		} catch (Exception e) {
			log.error("setInitialSearchTypeSearchCur", e);
		}
		return returnList;
	}

	/**
	 * @params const0
	 * @return SystemProfiles
	 */
	@Override
	public SystemProfiles callToShowFingerprintOld(final String const0) {
		final String sql = getQuery("OSIOSEAR_CALL_TO_SHOW_FINGERPRINT_OLD");
		final SystemProfiles returnObject = new SystemProfiles();

		final List<SystemProfiles> refList = new ArrayList<SystemProfiles>();
		try {
			final Object obj = namedParameterJdbcTemplate.queryForObject(sql, createParams("", const0), Object.class);
			refList.add(returnObject);
		} catch (Exception e) {
			log.error("callToShowFingerprintOld", e);
		}
		return returnObject;
	}

	/**
	 * @Param searchBean
	 * @return List<Images>
	 */
	@Override
	public List<Images> imageExecuteQuery(final Images searchBean) {
		final String sql = queryBuilderFactory.getQueryBuilder(getQuery("OSIOSEAR_IMAGE_FIND_IMAGES"), images).build();
		final RowMapper<Images> imagesRowMapper = Row2BeanRowMapper.makeMapping(sql, Images.class, images);
		String preparedSql = null;
		final MapSqlParameterSource inParameterSource = new MapSqlParameterSource();
		final StringBuffer sqlQuery = new StringBuffer();
		sqlQuery.append(sql);

		if (searchBean.getImageId() != null) {
			sqlQuery.append("I.IMAGE_ID = :IMAGE_ID" + " AND ");
			inParameterSource.addValue("IMAGE_ID", searchBean.getImageId());
		}
		if (searchBean.getImageObjectId() != null) {
			sqlQuery.append("I.IMAGE_OBJECT_ID = :IMAGE_OBJECT_ID" + " AND ");
			inParameterSource.addValue("IMAGE_OBJECT_ID", searchBean.getImageObjectId());
		}
		if (searchBean.getImageObjectType() != null) {
			sqlQuery.append("I.IMAGE_OBJECT_TYPE = :IMAGE_OBJECT_TYPE" + " AND ");
			inParameterSource.addValue("IMAGE_OBJECT_TYPE", searchBean.getImageObjectType());
		}
		if (searchBean.getActiveFlag() != null) {
			sqlQuery.append("I.ACTIVE_FLAG = :ACTIVE_FLAG");
			inParameterSource.addValue("ACTIVE_FLAG", searchBean.getActiveFlag());
		}
		preparedSql = sqlQuery.toString().trim();
		if (preparedSql.endsWith("WHERE")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 5);
		}
		if (preparedSql.endsWith("AND")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 3);
		}
		return namedParameterJdbcTemplate.query(preparedSql, inParameterSource, imagesRowMapper);
	}

	/**
	 * @params profileType
	 * @return List<String>
	 */
	@Override
	public List<String> profCodeDescp(final String profileType) {
		final String sql = getQuery("OSIOSEAR_PROF_CODE_DESCP");

		List<String> returnList = new ArrayList<String>();
		final RowMapper<String> stringRowMapper = Row2BeanRowMapper.makeMapping(sql, String.class, profileTypes);
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams("PROFILE_TYPE", profileType),
					stringRowMapper);
			if (returnList != null) {
				returnList.set(0, "Y");
			}
		} catch (Exception e) {
			log.error("profCodeDescp", e);
		}
		return returnList;
	}

	/**
	 * @param const0
	 * @return List<SystemProfiles>
	 */
	@Override
	public List<SystemProfiles> captureImageFindImagingForm(final String const0) {
		final String sql = getQuery("FIND_PROFILEVALUE_SYSTEM_PROFILES");
		List<SystemProfiles> returnList = new ArrayList<SystemProfiles>();
		final RowMapper<SystemProfiles> systemProfilesRowMapper = Row2BeanRowMapper.makeMapping(sql,
				SystemProfiles.class, systemProfiles);
		try {
			returnList = namedParameterJdbcTemplate.query(sql, systemProfilesRowMapper);
		} catch (Exception e) {
			log.error("captureImageFindImagingForm", e);
		}
		return returnList;
	}

	/**
	 * returning Offenders table values
	 *
	 * @param offenderId
	 * @return Offenders
	 */
	@Override
	public Offenders searchResultsPostQuerynameTypeCur(final String offenderId) {
		final String sql = getQuery("OSIOSEAR_SEARCH_RESULTS_POSTQUERY_NAME_TYPE_CUR");
		final Offenders returnObject = new Offenders();
		try {
			Object obj = namedParameterJdbcTemplate.queryForObject(sql, createParams("OFFENDER_ID", offenderId != null ?Integer.parseInt(offenderId) : offenderId),
					Object.class);
			if (obj != null) {
				final String str = obj.toString();
				returnObject.setAliasNameType(str);
			} else {
				returnObject.setAliasNameType("");
			}
		} catch (Exception e) {
			log.error("searchResultsPostQuerynameTypeCur", e);
		}
		return returnObject;
	}

	/**
	 * @params imageObjectId,imageObjectType,imageObjectSeq
	 * @return List<String>
	 */
	@Override
	public List<String> captureImageFindMarkImageCur(final String imageObjectId, final String imageObjectType,
													 final String imageObjectSeq) {
		final String sql = getQuery("FIND_IMAGES_GETTING_1_PASS3_PARAMS");

		List<String> returnList = new ArrayList<String>();
		final RowMapper<String> stringRowMapper = Row2BeanRowMapper.makeMapping(sql, String.class, images);
		try {
			returnList = (namedParameterJdbcTemplate.query(sql, createParams("IMAGE_OBJECT_ID", imageObjectId,
					"IMAGE_OBJECT_TYPE", imageObjectType, "IMAGE_OBJECT_SEQ", imageObjectSeq), stringRowMapper));
			if (returnList != null) {
				for (int i = 0; i < returnList.size(); i++) {
					returnList.set(0, "1");
				}
			}
		} catch (Exception e) {
			log.error("", e);
		}
		return returnList;
	}

	/**
	 * @param const0
	 * @return List<OffenderFingerprints>
	 */
	@Override
	public List<OffenderFingerprints> searchResultsWhenNewRecordInstancecOffFingerPrints(final String const0) {
		final String sql = getQuery("OSIOSEAR_SEARCH_RESULTS_WHENNEWRECORDINSTANCE_C_OFF_FINGER_PRINTS");
		List<OffenderFingerprints> returnList = new ArrayList<OffenderFingerprints>();
		final RowMapper<OffenderFingerprints> offenderFingerprintsRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderFingerprints.class, offenderFingerPrintsMapping);
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams("ROOT_OFFENDER_ID", const0),
					offenderFingerprintsRowMapper);
		} catch (Exception e) {
			log.error("", e);
		}
		return returnList;
	}

	/**
	 * @params offenderId
	 * @return Offenders
	 */
	@Override
	public List<Offenders> populateOffDetailsBlocknameTypeCur(final String rootoffenderId,final String offenderId) {
		final String sql = getQuery("OSIOSEAR_POPULATE_OFF_DETAILS_BLOCK_NAME_TYPE_CUR");
		List<Offenders> returnObject = new ArrayList<Offenders>();
		final RowMapper<Offenders> offenderFingerprintsRowMapper = Row2BeanRowMapper.makeMapping(sql,
				Offenders.class, offendersMapping);

		try {
			returnObject =  namedParameterJdbcTemplate.query(sql, createParams("ROOT_OFFENDER_ID", rootoffenderId != null ?Integer.parseInt(rootoffenderId):rootoffenderId,"OFFENDER_ID",offenderId != null ?Integer.parseInt(offenderId):offenderId), offenderFingerprintsRowMapper);

		} catch (Exception e) {
			log.error("", e);
		}
		return returnObject;
	}

	/**
	 * @param searchRecord
	 * @return List<OffenderProfileDetails>
	 */
	public List<OffenderProfileDetails> offProfDtlsExecuteQuery(final OffenderProfileDetails searchRecord) {
		final String sql = getQuery("OSIOSEAR_OFFPROFDTLS_FIND_OFFENDER_PROFILE_DETAILS");
		List<OffenderProfileDetails> returnList = new ArrayList<OffenderProfileDetails>();
		final RowMapper<OffenderProfileDetails> OffenderProfileDetailsRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderProfileDetails.class, offenderProfileDetailsMapping);
		try {
			returnList = namedParameterJdbcTemplate.query(sql,
					createParams("OFFENDER_BOOK_ID", searchRecord.getOffenderBookId()),
					OffenderProfileDetailsRowMapper);
		} catch (Exception e) {
			log.error("", e);
		}
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
	public Caseloads caseloadTypeData(final Caseloads paramBean) {
		final String sql = getQuery("OSIOSEAR_CASELOAD_TYPE");
		final RowMapper<Caseloads> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				Caseloads.class, caseloadsMapping);
		Caseloads returnObj = namedParameterJdbcTemplate.queryForObject(sql,
				createParams("caseLoadId", paramBean.getCaseloadId()), columnRowMapper);
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
	public VHeaderBlock getVheaderBlockData(final VHeaderBlock paramBean) {
		VHeaderBlock returnObj=null;
		String sql = "SELECT A.*, B.IMAGE_ID, B.IMAGE_OBJECT_ID FROM ( ";

		String sql_A = queryBuilderFactory.getQueryBuilder(getQuery("OSIOSEAR_FIND_VHEADERBLOCK_DATA_ADMISSSION"), vheaderBlock).build();

		String sql_A_condition = " WHERE V_HEADER_BLOCK.OFFENDER_BOOK_ID = "+paramBean.getOffenderBookId() +") A "+
				"LEFT JOIN (select IMAGE_ID,IMAGE_OBJECT_ID FROM IMAGES where ACTIVE_FLAG = 'Y' AND IMAGE_OBJECT_TYPE = 'OFF_BKG' AND IMAGE_VIEW_TYPE = 'FACE') B" +
				" ON A.OFFENDER_BOOK_ID = B.IMAGE_OBJECT_ID";

		String sqlQ = sql+sql_A+sql_A_condition;

		final RowMapper<VHeaderBlock> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				VHeaderBlock.class, vheaderBlock);
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sqlQ,
					createParams("USERID",paramBean.getCreateuserId()), columnRowMapper);
		}catch (Exception e) {
			log.error(e);
		}
		return returnObj;
		
		/*final String sql = getQuery("OSIOSEAR_FIND_VHEADERBLOCK_DATA");
		final RowMapper<VHeaderBlock> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				VHeaderBlock.class, vheaderBlock);
		VHeaderBlock returnObj = namedParameterJdbcTemplate.queryForObject(sql,
				createParams("offenderBookId", paramBean.getOffenderBookId()), columnRowMapper);
		return returnObj;*/
	}

	public List<VHeaderBlock2> searchVHeaderBlock2ByOffenderIds(List<String> offenderIds,String userId) {
		String offendrIdCommaSepearetd = String.join(",", offenderIds);
		String sql = "SELECT A.*, B.IMAGE_ID, B.IMAGE_OBJECT_ID FROM ( ";
		String sql_A = queryBuilderFactory.getQueryBuilder(getQuery("FIND_V_HEADER_BLOCK2_IDS"), vheaderBlock).build();
		String sql_A_condition = " V.OFFENDER_ID IN ("+offendrIdCommaSepearetd +")) A "+
				"LEFT JOIN (select IMAGE_ID,IMAGE_OBJECT_ID FROM IMAGES where ACTIVE_FLAG = 'Y' AND IMAGE_OBJECT_TYPE = 'OFF_BKG' AND IMAGE_VIEW_TYPE = 'FACE') B" +
				" ON A.OFFENDER_BOOK_ID = B.IMAGE_OBJECT_ID";
		String sqlQ = sql+sql_A+sql_A_condition;
		final RowMapper<VHeaderBlock2> aliasesRowMapper = Row2BeanRowMapper.makeMapping(sqlQ, VHeaderBlock2.class,
				vheaderBlock);
		//sql = sql + " A.OFFENDER_ID IN ("+offendrIdCommaSepearetd+")";
		return namedParameterJdbcTemplate.query(sqlQ,createParams("userId",userId),  aliasesRowMapper);
	}


	public VHeaderBlock getVheaderBlockDataFromOffenderId(final VHeaderBlock paramBean) {
		final String sql = getQuery("OSIOSEAR_FIND_VHEADERBLOCK_DATA_OFFENDER_ID");
		final RowMapper<VHeaderBlock> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				VHeaderBlock.class, vheaderBlock);
		VHeaderBlock returnObj = namedParameterJdbcTemplate.queryForObject(sql,
				createParams("offenderId", paramBean.getOffenderId()), columnRowMapper);
		return returnObj;
	}


	@Override
	public String getCaptureImage() {
		String returnObj="";
		final String sql = getQuery("OSIOSEAR_GET_CAPTURE_IMAGE");
		returnObj = namedParameterJdbcTemplate.queryForObject(sql,	createParams(),String.class);
		return returnObj;
	}

	@Override
	public List<VTrustHeader> offbkgVTrustHeadGlobalQuery(final VTrustHeader param,String whereClause) {
		final String sql = getQuery("OSIOSEAR_OFFBKG_VTRUSTHEAD_GLOBAL_QUERY");
		List<VTrustHeader> returnList=new ArrayList<>();
		final StringBuffer sqlQuery = new StringBuffer(sql);
		final MapSqlParameterSource inParameterSource = new MapSqlParameterSource();
		try {
			if (param.getOffenderIdDisplay() != null && !param.getOffenderIdDisplay().isEmpty()) {
				sqlQuery.append("LTRIM(OFFENDER_ID_DISPLAY::text,'0')  LIKE LTRIM(:OFFENDER_ID_DISPLAY::text,'0')" + " AND ");
				inParameterSource.addValue("OFFENDER_ID_DISPLAY",param.getOffenderIdDisplay());
			}

			if (param.getLastName() != null) {
				sqlQuery.append(" LAST_NAME  LIKE :LAST_NAME" + " AND ");
				inParameterSource.addValue("LAST_NAME", param.getLastName() + "%");

			}
			if (param.getFirstName() != null) {
				sqlQuery.append(" FIRST_NAME LIKE :FIRST_NAME" + " AND ");
				inParameterSource.addValue("FIRST_NAME", param.getFirstName() + "%");

			}
			if (param.getMiddleName() != null) {
				sqlQuery.append(" MIDDLE_NAME LIKE :MIDDLE_NAME" + " AND ");
				inParameterSource.addValue("MIDDLE_NAME", param.getMiddleName() + "%");

			}
			if (param.getBirthDate() != null) {
				sqlQuery.append(" BIRTH_DATE =  :BIRTH_DATE" + " AND ");
				inParameterSource.addValue("BIRTH_DATE", param.getBirthDate());

			}
			if (param.getBookingNo() != null && !param.getBookingNo().isEmpty()) {
				sqlQuery.append(" LTRIM(BOOKING_NO::text, '0') like LTRIM(:BOOKING_NO::text,'0')" + " AND ");
				inParameterSource.addValue("BOOKING_NO", param.getBookingNo());
			}
			inParameterSource.addValue("USER_ID", param.getCreateUserId());
			if (param.getAgyLocId() != null && param.getModuleName() != null) {
				final String deffWhere =whereClause;
				//final String deffWhere = omsOwnerTrustHeaderQuery(param.getAgyLocId(), param.getModuleName().replaceFirst("/", ""));
				if (deffWhere != null && !deffWhere.isEmpty()) {
					sqlQuery.append(" " + deffWhere + " " );
				}
			}
			String preparedSql = sqlQuery.toString().trim();
			preparedSql = sqlQuery.toString().trim();
			if (preparedSql.endsWith("WHERE")) {
				preparedSql = preparedSql.substring(0, preparedSql.length() - 5);
			}
			if (preparedSql.endsWith("AND")) {
				preparedSql = preparedSql.substring(0, preparedSql.length() - 3);
			}
			final RowMapper<VTrustHeader> aliasesRowMapper = Row2BeanRowMapper.makeMapping(sql, VTrustHeader.class,
					vheaderBlock);
			returnList = namedParameterJdbcTemplate.query(preparedSql, inParameterSource, aliasesRowMapper);
		} catch (Exception e) {
			log.error("rgSearchTypeRecordGroup", e);
			return returnList;
		}
		return returnList;
	}

	public String omsOwnerTrustHeaderQuery(String caseloadId, String moduleName) {

		SqlParameter[] sqlParameters = new SqlParameter[] {
				new SqlParameter("P_CASELOAD_ID", OracleTypes.VARCHAR),
				new SqlParameter("P_FORM_NAME", OracleTypes.VARCHAR),
				new SqlOutParameter("P_DEF_WHERE", OracleTypes.VARCHAR)
		};
		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("TRUST_MAIN").withProcedureName("TRUST_HEADER_QUERY").declareParameters(sqlParameters);
		Map<String, Object> inParamMap = new HashMap<String, Object>();
		inParamMap.put("P_CASELOAD_ID", caseloadId);
		inParamMap.put("P_FORM_NAME", moduleName);

		final MapSqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		final Map<String, Object> returnObject = simpleJDBCCall.execute(inParameter);
		if(returnObject.get("P_DEF_WHERE") != null) {
			return returnObject.get("P_DEF_WHERE").toString();
		}
		return null;
	}

	@Override
	public String getOffenderIdDisplay(TagSearchGetOffenderRecords objSearchDao) {
		String offenderIdDisplay = null;
		final String sql = "select offender_id_display from V_HEADER_BLOCK_FN(:USERID) v_header_block where offender_id_display LIKE '%"+objSearchDao.getOffenderIdDisplay()+"'";
		if(objSearchDao.getOffenderIdDisplay() != null) {
			offenderIdDisplay =  namedParameterJdbcTemplate.queryForObject(sql,createParams("USERID",objSearchDao.getCreateUserId()),String.class);
		}
		return offenderIdDisplay;
	}

	@Override
	public List<VHeaderBlock> getOffenderDetials(Integer offenderBookId,String userName) {
		final String sql = getQuery("GET_OFFENDER_IMAGE_DETAILS");
		List<VHeaderBlock> returnList = new ArrayList<VHeaderBlock>();
		final RowMapper<VHeaderBlock> OffenderProfileDetailsRowMapper = Row2BeanRowMapper.makeMapping(sql,
				VHeaderBlock.class, vheaderBlock);
		try {
			returnList = namedParameterJdbcTemplate.query(sql,
					createParams("OFFENDER_BOOK_ID",offenderBookId,"USERID",userName),
					OffenderProfileDetailsRowMapper);
		} catch (Exception e) {
			log.error("", e);
		}
		return returnList;
	}

	@Override
	public String getDescription(String caseloadId, Long offenderId,String code) {
		final String sql=getQuery("OSIOSEAR_GET_DESCRIPTION");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("caseloadId",caseloadId,"offenderId",offenderId,"code",code), String.class);
	}

	@Override
	public OffenderBookings getOffenderBookingDetails(BigDecimal offenderBookId) {
		final String sql=getQuery("OSIOSEAR_GET_OFFENDER_BOOKINGS");
		final RowMapper<OffenderBookings> rowMapper = Row2BeanRowMapper.makeMapping(sql, OffenderBookings.class,
				offenderBookingsMapping);
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("offenderBookId", offenderBookId), rowMapper);
	}

	
	@Override
	public String getOffenderBookingNumber(String offenderIdDisplay, String userId) {
		final String sql = getQuery("OSIOSEAR_GET_BOOING_ID_OF_OFFENDER");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql,
					createParams("OFFENDER_ID_DISPLAY", offenderIdDisplay, "USER_ID", userId), String.class);
		} catch (Exception e) {
			log.error(this.getClass().getName() + " getOffenderBookingNumber" + e);
		}
		return null;
	}
	
	
	@Override
	public List<VHeaderBlock> getOffenderDetails(VHeaderBlock searchBean) {
		final String sql = getQuery("OSIOSEAR_GET_OFFENDER_DETAILS");
		List<VHeaderBlock> list = new ArrayList<VHeaderBlock>();
		final RowMapper<VHeaderBlock> aliasesRowMapper = Row2BeanRowMapper.makeMapping(sql, VHeaderBlock.class,
				vheaderBlock);
		try {
			list = namedParameterJdbcTemplate.query(sql, createParams("USER_ID", searchBean.getCreateuserId(),
					"OFFENDER_ID_DISPLAY", searchBean.getOffenderIdDisplay(),"BOOKING_NO",searchBean.getBookingNo()), aliasesRowMapper);
		} catch (Exception e) {
			log.error(this.getClass().getName() + " getOffenderDetails" + e);
			return null;
		}
		if (list != null && list.size() > 0) {
			list.forEach(bo -> {
				if (ApplicationConstants.YFLAG.equalsIgnoreCase(bo.getTrustFlag())) {
					bo.setTrustAccount(true);
				} else {
					bo.setTrustAccount(false);
				}
			});
		}
		return list;
	}
	
	@Override
	public Long getIntCorrelationIdSeq() {
		final String sql = getQuery("OSIOSEAR_GET_INT_CORRELATION_ID_SEQ");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql, createParams(), Long.class);
		} catch (Exception e) {
			log.error(this.getClass().getName() + " getIntCorrelationIdSeq" + e);
			return 0l;
		}
	}
	
	
	@Override
	public TagSearchGetOffenderRecords getDataFromJisCommonSystem(Long intCorrelationId) {
		final String sql = getQuery("OSIOSEAR_GET_DATA_FROM_JIS_COMMON_SYSTEM");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql, createParams("intCorrelationId", intCorrelationId),
					new BeanPropertyRowMapper<TagSearchGetOffenderRecords>(TagSearchGetOffenderRecords.class));
		} catch (Exception e) {
			log.error(this.getClass().getName() + " getDataFromJisCommonSystem" + e);
			return null;
		}
	}
	
	@Override
	public String getIdentifierData(BigDecimal offenderId) {
		final String sql = getQuery("OSIOSEAR_GET_IDENTIFIER_DATA");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql, createParams("offenderId", offenderId),String.class);
		} catch (Exception e) {
			log.error(this.getClass().getName() + " getIdentifierData" + e);
			return null;
		}
	}
	
	@Override
	public Integer removeOldDataExternalSystemResponse() {
		final String sql = getQuery("OSIOSEAR_REMOVE_DATA_EXTERNAL_SYSTEM_RESPONSE");
		try {
			return namedParameterJdbcTemplate.update(sql, createParams());
		}catch (Exception e) {
			log.error(this.getClass().getName() + " removeOldDataExternalSystemResponse" + e);
		}
		return null;
	}
	
	@Override
	public Long getCorrelationId() {
		final String sql = getQuery("OSIOSEAR_GET_CORRELATION_ID");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql, createParams(), Long.class);
		}catch (Exception e) {
			log.error(this.getClass().getName() + " getCorrelationId" + e);
		}
		return null;
	}
	
	@Override
	public String getRecentOffenders(Long offenderBookId, String user) {
		final String sql = getQuery("OSIOSEAR_GET_RECENT_OFFENDERS");
		String result = null;
		try {
			result = namedParameterJdbcTemplate.queryForObject(sql, createParams("offenderBookId", offenderBookId, "userId", user), String.class);
		} catch (Exception e) {
			log.error(this.getClass().getName() + " getRecentOffenders" + e);
		}
		return result;
	}
	
	@Override
	public String searchTypeCodeRetrieve() {
		final String sql = getQuery("OSIOSEAR_SEARCH_TYPE_CODE");
		String searchCode = null;
		try {
			searchCode = namedParameterJdbcTemplate.queryForObject(sql, createParams(),String.class);
		}catch (Exception e) {
			log.error(this.getClass().getName() + " searchTypeCodeRetrieve" + e);
		}
		return searchCode;
	}
}

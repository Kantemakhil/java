package net.syscon.s4.inst.movementexternal.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.Caseloads;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.beans.VHeaderBlock;
import net.syscon.s4.common.beans.VNameSearch;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.Dual;
import net.syscon.s4.im.beans.SysDual;
import net.syscon.s4.inst.movementexternal.OiinamesRepository;

/**
 * Class OiinamesRepositoryImpl
 * 
 */
@Repository
public class OiinamesRepositoryImpl extends RepositoryBase implements OiinamesRepository {

	private final Map<String, FieldMapper> vNameSearchMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("OFFENDER_BOOK_ID", new FieldMapper("offenderBookId"))
			.put("OFFENDER_ID_DISPLAY", new FieldMapper("offenderIdDisplay"))
			.put("ACTIVE_FLAG", new FieldMapper("activeFlag")).put("AGY_LOC_NAME", new FieldMapper("agyLocName"))
			.put("MIDDLE_NAME", new FieldMapper("middleName"))
			.put("LIVING_UNIT_DESCRIPTION", new FieldMapper("livingUnitDescription"))
			.put("IN_OUT_STATUS", new FieldMapper("inOutStatus")).put("OFFENDER_ID", new FieldMapper("offenderId"))
			.put("LAST_NAME", new FieldMapper("lastName")).put("AGY_LOC_ID", new FieldMapper("agyLocId"))
			.put("BOOKING_NO", new FieldMapper("bookingNo")).put("BIRTH_DATE", new FieldMapper("birthDate"))
			.put("FIRST_NAME", new FieldMapper("firstName")).build();
	private final Map<String, FieldMapper> systemProfilesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("PROFILE_CODE", new FieldMapper("profileCode")).put("PROFILE_TYPE", new FieldMapper("profileType"))
			.put("CREATE_USER_ID", new FieldMapper("createUserId")).put("SEAL_FLAG", new FieldMapper("sealFlag"))
			.put("CREATE_DATETIME", new FieldMapper("createDatetime"))
			.put("MODIFY_USER_ID", new FieldMapper("modifyUserId"))
			.put("OLD_TABLE_NAME", new FieldMapper("oldTableName"))
			.put("PROFILE_VALUE", new FieldMapper("profileValue"))
			.put("MODIFY_DATETIME", new FieldMapper("modifyDatetime"))
			.put("PROFILE_VALUE_2", new FieldMapper("profileValue2")).put("DESCRIPTION", new FieldMapper("description"))
			.build();
	private final Map<String, FieldMapper> vheaderBlock = new ImmutableMap.Builder<String, FieldMapper>()
			.put("OFFENDER_ID", new FieldMapper("offenderId"))
			.put(" ALIAS_OFFENDER_ID  ", new FieldMapper("aliasOffenderId"))
			.put("OFFENDER_ID_DISPLAY", new FieldMapper("offenderIdDisplay"))
			.put("LAST_NAME", new FieldMapper("lastName")).put("FIRST_NAME", new FieldMapper("firstName"))
			.put("MIDDLE_NAME", new FieldMapper("middleName")).put("SUFFIX", new FieldMapper("suffix"))
			.put("BIRTH_DATE", new FieldMapper("birthDate")).put("OFFENDER_BOOK_ID", new FieldMapper("offenderBookId"))
			.put("BOOKING_NO", new FieldMapper("bookingNo"))
			.put("BOOKING_BEGIN_DATE", new FieldMapper("bookingBeginDate"))
			.put("BOOKING_END_DATE", new FieldMapper("bookingEndDate")).put("AGY_LOC_ID", new FieldMapper("agyLocId"))
			.put("AGENCY_IML_ID", new FieldMapper("agencyImlId")).put("LIVING_UNIT_ID", new FieldMapper("livingUnitId"))
			.put("DISCLOSURE_FLAG", new FieldMapper("disclosureFlag")).put("ACTIVE_FLAG", new FieldMapper("activeFlag"))
			.put("BOOKING_STATUS", new FieldMapper("bookingStatus"))
			.put("LIVING_UNIT_DESCRIPTION", new FieldMapper("livingUnitDescription"))
			.put("IN_OUT_STATUS", new FieldMapper("inOutStatus"))
			.put("STATUS_DISPLAY", new FieldMapper("statusDisplay"))
			.put("ROOT_OFFENDER_ID", new FieldMapper("rootOffenderId"))
			.put("ASSIGNED_STAFF_ID", new FieldMapper("assignedStaffId"))
			.put("AGY_LOC_TYPE", new FieldMapper("agyLocType"))
			.put("CREATE_AGY_LOC_ID", new FieldMapper("createAgyLocId"))
			.put("BOOKING_TYPE", new FieldMapper("bookingType"))
			.put("BOOKING_CREATED_DATE", new FieldMapper("bookingCreatedDate"))
			.put("LOCATION_CODE", new FieldMapper("locationCode"))
			.put("INTAKE_AGY_LOC_ID", new FieldMapper("intakeAgyLocId"))
			.put("COMMUNITY_ACTIVE_FLAG", new FieldMapper("communityActiveFlag"))
			.put("CREATE_INTAKE_AGY_LOC_ID", new FieldMapper("createIntakeAgyLocId"))
			.put("COMMUNITY_STATUS", new FieldMapper("communityStatus"))
			.put("STATUS_REASON", new FieldMapper("statusReason")).put("HEADER_STATUS", new FieldMapper("headerStatus"))
			.put("AGE", new FieldMapper("age")).put("GENDER", new FieldMapper("gender"))
			.put("OFF_ALERTS", new FieldMapper("offAlerts")).put("STATUS_1", new FieldMapper("status1"))
			.put("STATUS_2", new FieldMapper("status2")).put("STATUS_3", new FieldMapper("status3"))
			.put("ETHNICITY", new FieldMapper("ethnicity")).put("MOVEMENT_REASON", new FieldMapper("movementReason"))
			.put("IMAGE_ID", new FieldMapper("imageId")).put("IMAGE_THUMBNAIL", new FieldMapper("imageThumbnail"))
			.put("OFF_SUP_LEVEL", new FieldMapper("offSupLevel")).build();
	private final Map<String, FieldMapper> dualMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("USER", new FieldMapper("user")).put("SYSDATE", new FieldMapper("sysDate")).build();

	/**
	 * Creates new OiinamesRepositoryImpl class Object
	 */
	public OiinamesRepositoryImpl() {
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao VNameSearch
	 *
	 * @return List<VNameSearch>
	 *
	 * @
	 */
	public List<VNameSearch> nameSrchExecuteQuery(final VNameSearch objSearchDao) {
		final String sql = getQuery("OIINAMES_NAMESRCH_FIND_V_NAME_SEARCH_FN");
		String preparedSql = null;
		final StringBuffer sqlQuery = new StringBuffer();
		String preSqlQuery = null;
		final MapSqlParameterSource params = new MapSqlParameterSource();
		sqlQuery.append(sql);
		if (objSearchDao.getCreateUserId() != null) {
			params.addValue("userId", objSearchDao.getCreateUserId());
		}
		if (objSearchDao != null) {
			sqlQuery.append(" where ");
		}
		if (objSearchDao != null && objSearchDao.getLastName() != null
				&& !("").equals(objSearchDao.getLastName().trim())) {
			sqlQuery.append("regexp_replace(upper(LAST_NAME),'[- \\,''\"]','','g') LIKE regexp_replace(upper(:lastName),'[- \\,''\"]','','g')" + " and ");
			params.addValue("lastName", objSearchDao.getLastName().trim() + "%");
		}
		if (objSearchDao != null && objSearchDao.getFirstName() != null
				&& !("").equals(objSearchDao.getFirstName().trim())) {
			sqlQuery.append("regexp_replace(upper(FIRST_NAME),'[- \\,''\"]','','g') LIKE regexp_replace(upper(:firstName),'[- \\,''\"]','','g')" + " and ");
			params.addValue("firstName", objSearchDao.getFirstName().trim() + "%");
		}
		if (objSearchDao != null && objSearchDao.getMiddleName() != null
				&& !("").equals(objSearchDao.getMiddleName().trim())) {
			sqlQuery.append("  MIDDLE_NAME  like :middleName " + " and ");
			params.addValue("middleName", objSearchDao.getMiddleName().trim() + "%");
		}
		if (objSearchDao != null && objSearchDao.getActiveFlag() != null
				&& !("").equals(objSearchDao.getActiveFlag().trim())) {
			sqlQuery.append("  ACTIVE_FLAG   = :activeFalg" + " and ");
			params.addValue("activeFalg", objSearchDao.getActiveFlag().trim());
		}
		if (objSearchDao != null && objSearchDao.getOffenderIdDisplay() != null
				&& !("").equals(objSearchDao.getOffenderIdDisplay().trim())) {
			sqlQuery.append("LTRIM(OFFENDER_ID_DISPLAY::text,'0') LIKE LTRIM(:offenderIdDisplay::text,'0')" + " and ");
			params.addValue("offenderIdDisplay" , objSearchDao.getOffenderIdDisplay().trim() + "%");

		}
		if (objSearchDao != null && objSearchDao.getBookingNo() != null
				&& !("").equals(objSearchDao.getBookingNo().trim())) {
			sqlQuery.append(" LTRIM(BOOKING_NO::text,'0') LIKE LTRIM(:bookingNo::text,'0')" + " and ");
			params.addValue("bookingNo", objSearchDao.getBookingNo().trim());
		}

		if (objSearchDao != null && objSearchDao.getLivingUnitDescription() != null
				&& !("").equals(objSearchDao.getLivingUnitDescription().trim())) {
			sqlQuery.append(" LIVING_UNIT_DESCRIPTION =:livingUnitDescription  " + " and ");
			params.addValue("livingUnitDescription", objSearchDao.getLivingUnitDescription().trim());
		}
		if (objSearchDao != null && objSearchDao.getAgyLocId() != null) {
			objSearchDao.setAgyLocId(objSearchDao.getAgyLocId().trim());
		}
		if (objSearchDao != null && ("OIDSCMOV".equals(objSearchDao.getModuleName())
				|| "OIDIDETA".equals(objSearchDao.getModuleName()) || "OCIWLIST".equals(objSearchDao.getModuleName())
				|| "OCDOATTE".equals(objSearchDao.getModuleName()))) {
			sqlQuery.append(
					" (AGY_LOC_ID = :agylocId OR coalesce (:agylocId::text,'')='') AND  ACTIVE_FLAG = 'Y' AND EXISTS (SELECT 1 FROM OFFENDER_BOOKINGS VHB WHERE  VHB.BOOKING_BEGIN_DATE = (SELECT MAX(OB.BOOKING_BEGIN_DATE) FROM OFFENDER_BOOKINGS OB  WHERE OB.OFFENDER_ID = VHB.OFFENDER_ID) AND OFFENDER_BOOK_ID = VHB.OFFENDER_BOOK_ID and exists (select 1 from caseload_agency_locations cal  where cal.agy_loc_id = vhb.agy_loc_id and cal.caseload_id = :caseloadId)) ");
			params.addValue("agylocId", objSearchDao.getAgyLocId());
			params.addValue("caseloadId", objSearchDao.getCaseloadId().trim());
		} else if (objSearchDao != null && ("OIDCHOLO".equals(objSearchDao.getModuleName())
				|| "OIDEHLOC".equals(objSearchDao.getModuleName()))) {
			sqlQuery.append(
					" (AGY_LOC_ID = :agylocId OR coalesce (:agylocId::text,'')='') AND (ACTIVE_FLAG = 'Y') AND EXISTS (SELECT 1 FROM OFFENDER_BOOKINGS VHB WHERE  VHB.BOOKING_BEGIN_DATE = (SELECT MAX(OB.BOOKING_BEGIN_DATE) FROM OFFENDER_BOOKINGS OB  WHERE OB.OFFENDER_ID = VHB.OFFENDER_ID) AND OFFENDER_BOOK_ID = VHB.OFFENDER_BOOK_ID   and exists (select 1 from caseload_agency_locations cal  where cal.agy_loc_id = vhb.agy_loc_id and cal.caseload_id = :caseloadId  and cal.agy_loc_id NOT IN ('OUT'||'TRN'))) ");
			params.addValue("agylocId", objSearchDao.getAgyLocId());
			params.addValue("caseloadId", objSearchDao.getCaseloadId().trim());
		} else if (objSearchDao != null && "OIDINTMV".equals(objSearchDao.getModuleName())) {
			sqlQuery.append(
					" (AGY_LOC_ID = :agylocId OR coalesce (:agylocId::text,'')='') AND  (ACTIVE_FLAG = 'Y') AND (IN_OUT_STATUS='IN') AND EXISTS (SELECT 1 FROM OFFENDER_BOOKINGS VHB WHERE VHB.BOOKING_BEGIN_DATE = (SELECT MAX(OB.BOOKING_BEGIN_DATE) FROM OFFENDER_BOOKINGS OB WHERE OB.OFFENDER_ID = VHB.OFFENDER_ID) AND OFFENDER_BOOK_ID = VHB.OFFENDER_BOOK_ID and exists (select 1 from caseload_agency_locations cal  where cal.agy_loc_id = vhb.agy_loc_id and cal.caseload_id = :caseloadId)) ");
			params.addValue("agylocId", objSearchDao.getAgyLocId().trim());
			params.addValue("caseloadId", objSearchDao.getCaseloadId().trim());
		} else if (objSearchDao != null && "OBDMCHRG".equals(objSearchDao.getModuleName())) {
			sqlQuery.append(
					" EXISTS (SELECT 1 FROM OFFENDER_BOOKINGS VHB WHERE  VHB.BOOKING_BEGIN_DATE = (SELECT MAX(OB.BOOKING_BEGIN_DATE) FROM OFFENDER_BOOKINGS OB  WHERE OB.OFFENDER_ID = VHB.OFFENDER_ID) AND OFFENDER_BOOK_ID = VHB.OFFENDER_BOOK_ID and exists (select 1 from caseload_agency_locations cal  where cal.agy_loc_id = vhb.agy_loc_id and cal.caseload_id = :caseloadId))");
			params.addValue("caseloadId", objSearchDao.getCaseloadId().trim());
		} else {
			if (objSearchDao != null && objSearchDao.getAgyLocId() != null
					&& !("").equals(objSearchDao.getAgyLocId().trim())) {
				sqlQuery.append(" AGY_LOC_ID  =:agylocId " + " and ");
				params.addValue("agylocId", objSearchDao.getAgyLocId().trim());
			}
			sqlQuery.append(" VHB.BOOKING_BEGIN_DATE = ( select MAX(OB.BOOKING_BEGIN_DATE) from OFFENDER_BOOKINGS OB where OB.OFFENDER_ID = VHB.OFFENDER_ID) and OFFENDER_BOOK_ID = VHB.OFFENDER_BOOK_ID and exists ( select 1 from caseload_agency_locations cal where case when (select caseload_type from caseloads c where caseload_id =:caseloadId) = 'INST' then cal.agy_loc_id = vhb.agy_loc_id and vhb.agy_loc_id not in ('OUT','TRN') else cal.agy_loc_id =vhb.intake_agy_loc_id and vhb.intake_agy_loc_id is not null end and cal.caseload_id = :caseloadId)");
			params.addValue("caseloadId", objSearchDao.getCaseloadId().trim());
		}

		preparedSql = sqlQuery.toString().trim();
		if (preparedSql.endsWith("where")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 5);
		}
		if (preparedSql.endsWith("and")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 3);
		}
		preSqlQuery = preparedSql.concat(" ORDER BY LAST_NAME, FIRST_NAME ");
		final RowMapper<VNameSearch> VNameSearchRowMapper = Row2BeanRowMapper.makeMapping(sql, VNameSearch.class,
				vNameSearchMapping);
		List<VNameSearch> returnList = new ArrayList<>();
		returnList = namedParameterJdbcTemplate.query(preSqlQuery, params, VNameSearchRowMapper);
		return returnList;
	}

	/**
	 * return Header block details
	 * 
	 * @param searchBean
	 * @return List<VHeaderBlock>
	 */
	@Override
	public List<VHeaderBlock> offbkgGlobalQuery(final Integer offBookId, String userId) {
		final String sql = queryBuilderFactory
				.getQueryBuilder(getQuery("OIINAMESFIND_V_PROPERTY_HEADER_BLOCK"), vheaderBlock).build();
		final RowMapper<VHeaderBlock> aliasesRowMapper = Row2BeanRowMapper.makeMapping(sql, VHeaderBlock.class,
				vheaderBlock);
		String preparedSql = null;
		final MapSqlParameterSource inParameterSource = new MapSqlParameterSource();
		final StringBuffer sqlQuery = new StringBuffer();
		sqlQuery.append(sql);
		if (userId != null) {
			inParameterSource.addValue("userId", userId);
		}
		if (offBookId != null) {
			sqlQuery.append("OFFENDER_BOOK_ID = :OFFENDER_BOOK_ID" + " AND ");
			inParameterSource.addValue("OFFENDER_BOOK_ID", offBookId);
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
	 * @param
	 *
	 * @
	 *
	 */
	public int PRE_INSERT() {
		return 0;
	}

	/**
	 * This method is used to insert the records in the data base tables based on
	 *
	 * @param lstVNameSearch List<VNameSearch>
	 *
	 * @return List<Integer>
	 *
	 * @
	 */
	public Integer namesrchInsertVNameSearch(final List<VNameSearch> lstVNameSearch) {
		int insertCount = 0;
		String sql = getQuery("OIINAMES_NAMESRCH_INSERT_V_NAME_SEARCH");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		for (int i = 0; i < returnArray.length; i++) {
		}
		if (lstVNameSearch.size() == insertCount) {
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
	 * @
	 */
	public List<SystemProfiles> sysPflExecuteQuery(final SystemProfiles objSearchDao) {
		final String sql = getQuery("OIINAMES_SYSPFL_FIND_SYSTEM_PROFILES");
		final RowMapper<SystemProfiles> SystemProfilesRowMapper = Row2BeanRowMapper.makeMapping(sql,
				SystemProfiles.class, systemProfilesMapping);
		final ArrayList<SystemProfiles> returnList = (ArrayList<SystemProfiles>) namedParameterJdbcTemplate.query(sql,
				createParams(), SystemProfilesRowMapper);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfdgetNameSrchDrvActive
	 *
	 * @param params
	 *
	 */
	public List<SysDual> cgfdgetNameSrchDrvActive(final SysDual paramBean) {
		final String sql = getQuery("OIINAMES_CGFDGET_NAME_SRCH_DRV_ACTIVE_");
		final RowMapper<SysDual> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, SysDual.class, dualMapping);
		List<SysDual> returnList = new ArrayList<>();
		returnList = namedParameterJdbcTemplate.query(sql, createParams(), columnRowMapper);
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
	public List<Dual> cgwhenNewFormInstance(final SysDual paramBean) {
		final String sql = getQuery("OIINAMES_CGWHEN_NEW_FORM_INSTANCE");
		final RowMapper<Dual> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, Dual.class, dualMapping);
		List<Dual> returnList = new ArrayList<>();
		returnList = namedParameterJdbcTemplate.query(sql, createParams(), columnRowMapper);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * List<VNameSearch>
	 *
	 */
	public Integer nameSrchInsertVNameSearch(final List<VNameSearch> lstVNameSearch) {
		return null;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * List<String>
	 *
	 */
	public List<String> findAgyLocIdList(String userName) {
		final String sql = getQuery("OIINAMES_AGY_LOC_ID");
		return namedParameterJdbcTemplate.queryForList(sql, createParams("userId",userName), String.class);
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * List<String>
	 *
	 */
	public List<String> findLivingUnitsList(String userName) {
		final String sql = getQuery("OIINAMES_LIVING_UNIT_DESCRIPTION");
		return namedParameterJdbcTemplate.queryForList(sql, createParams("userId",userName), String.class);
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * List<String>
	 *
	 */
	public List<String> findActiveFlagList() {
		final String sql = getQuery("OIINAMES_ACTIVE_FLAG");
		return namedParameterJdbcTemplate.queryForList(sql, createParams(), String.class);
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<ReferenceCodes>
	 */
	public List<Caseloads> findAgyLocIdListLov() {
		final String sql = getQuery("FIND_AGYLOCID_LIST_LOV");
		final RowMapper<Caseloads> mRowMapper = Row2BeanRowMapper.makeMapping(sql, Caseloads.class, dualMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (Exception e) {
			return Collections.emptyList();
		}
	}

}

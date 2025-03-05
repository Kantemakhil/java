package net.syscon.s4.inst.automatedcounts.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.OffenderBookings;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.LivingUnits;
import net.syscon.s4.im.beans.OmsModules;
import net.syscon.s4.inst.automatedcounts.OidsublcRepository;
import net.syscon.s4.inst.automatedcounts.beans.AgencyCountTypes;
import net.syscon.s4.inst.automatedcounts.beans.AgencyCounts;
import net.syscon.s4.inst.automatedcounts.beans.AgencyLocationCounts;
import net.syscon.s4.inst.automatedcounts.beans.AgencyReportingLocs;
import net.syscon.s4.inst.automatedcounts.beans.TempOidcount;
import net.syscon.s4.inst.movements.beans.VIntLocSummaries;

/**
 * Class OidsublcRepositoryImpl
 */
@Repository
public class OidsublcRepositoryImpl extends RepositoryBase implements OidsublcRepository {

	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OidsublcRepositoryImpl.class.getName());

	private final Map<String, FieldMapper> tempOidcountMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DATE_SUBMITTED", new FieldMapper("dateSubmitted")).put("TOTAL_FEMALE", new FieldMapper("totalFemale"))
			.put("DISCREPANCY_COUNT", new FieldMapper("discrepancyCount"))
			.put("REPORTED_COUNT", new FieldMapper("reportedCount"))
			.put("COUNT_TYPE_ID", new FieldMapper("countTypeId"))
			.put("COUNT_TYPE_CODE", new FieldMapper("countTypeCode"))
			.put("ENTERED_BY_USERID", new FieldMapper("enteredByUserid"))
			.put("REPORTING_LOC_ID", new FieldMapper("reportingLocId"))
			.put("TOTAL_OTHER", new FieldMapper("totalOther")).put("SCHEDULED_TIME", new FieldMapper("scheduledTime"))
			.put("TOTAL_MALE", new FieldMapper("totalMale"))
			.put("LOCATION_DESCRIPTION", new FieldMapper("locationDescription"))
			.put("AGY_LOC_ID", new FieldMapper("agyLocId")).put("ACTUAL_COUNT", new FieldMapper("actualCount"))
			.put("COUNT_TEMP", new FieldMapper("countTemp"))
			.put("ACTUAL_COUNT_TEMP", new FieldMapper("actualCountTemp"))
			.put("REPORTED_COUNT_TEMP", new FieldMapper("reportedCountTemp"))
			.put("LOWEST_LOCATION_ID", new FieldMapper("lowestLocationId")).put("ROW_ID", new FieldMapper("rowId"))
			.build();
	private final Map<String, FieldMapper> agencyLocationCountsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CONDUCTED_DATETIME", new FieldMapper("conductedDatetime"))
			.put("REPORTED_COUNT", new FieldMapper("reportedCount")).put("AGY_SEQ", new FieldMapper("agySeq"))
			.put("ACTUAL_COUNT", new FieldMapper("actualCount"))
			.put("VERIFIED_USER_ID", new FieldMapper("verifiedUserId"))
			.put("VERIFIED_DATETIME", new FieldMapper("verifiedDatetime"))
			.put("RSN_CODE_USERID", new FieldMapper("rsnCodeUserid"))
			.put("DATE_SUBMITTED", new FieldMapper("dateSubmitted")).put("SEAL_FLAG", new FieldMapper("sealFlag"))
			.put("CREATE_DATETIME", new FieldMapper("createDatetime"))
			.put("RECOUNT_RSN_CODE", new FieldMapper("recountRsnCode"))
			.put("MODIFY_DATETIME", new FieldMapper("modifyDatetime"))
			.put("RCNT_DATETIME", new FieldMapper("rcntDatetime"))
			.put("CONDUCTED_BY_USERID", new FieldMapper("conductedByUserid"))
			.put("COMMENT_TEXT", new FieldMapper("commentText")).put("CREATE_USER_ID", new FieldMapper("createUserId"))
			.put("RSN_CODE_DATETIME", new FieldMapper("rsnCodeDatetime"))
			.put("MODIFY_USER_ID", new FieldMapper("modifyUserId"))
			.put("RECOUNT_TOTAL", new FieldMapper("recountTotal"))
			.put("REPORTING_LOC_ID", new FieldMapper("reportingLocId"))
			.put("RCNT_IN_PROGRESS_FLAG", new FieldMapper("rcntInProgressFlag"))
			.put("RCNT_CONDUCTED_BY", new FieldMapper("rcntConductedBy"))
			.put("ENTERED_BY_USERID", new FieldMapper("enteredByUserid"))
			.put("DISCREP_RSN_CODE", new FieldMapper("discrepRsnCode"))
			.put("COUNT_TYPE_ID", new FieldMapper("countTypeId")).build();

	private final Map<String, FieldMapper> systemProfilesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("PROFILE_VALUE", new FieldMapper(" profileValue "))
			.put("LTRIM(RTRIM(DESCRIPTION))", new FieldMapper(" ltrim(rtrim(description)) ")).build();
	private final Map<String, FieldMapper> VIntLocSummariesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("INTERNAL_LOCATION_CODE", new FieldMapper("internalLocationCode"))
			.put("INTERNAL_LOCATION_ID", new FieldMapper("internalLocationId")).build();
	private final Map<String, FieldMapper> agencyLocationsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("AGY_LOC_ID", new FieldMapper("agyLocId")).put("DESCRIPTION", new FieldMapper("description"))
			.put("HOUSINGLEV1CODE", new FieldMapper("housingLev1Code"))
			.put("HOUSINGLEV2CODE", new FieldMapper("housingLev2Code"))
			.put("HOUSINGLEV3CODE", new FieldMapper("housingLev3Code")).
			put("HOUSINGLEV4CODE", new FieldMapper("housingLev4Code")).build();
	private final Map<String, FieldMapper> agencyCountsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("AGY_LOC_ID", new FieldMapper("agyLocId")).put("REPORTING_LOC_ID", new FieldMapper("reportingLocId"))
			.put("'9999'", new FieldMapper("'9999'")).put("COUNT_TYPE_ID", new FieldMapper("countTypeId"))
			.put("COUNT_IN_PROGRESS", new FieldMapper("countInProgress")).put("OUTCOME", new FieldMapper("outcome"))
			.put("COUNT_TYPE_CODE", new FieldMapper("countTypeCode"))
			.put("INITIATED_DATE", new FieldMapper("initiatedDate")).put("'Y'", new FieldMapper("'y'"))
			.put("SESSION_ID", new FieldMapper("sessionId")).put("AGY_SEQ", new FieldMapper("agySeq"))
			.put("DECODE(SCHEDULED_TIME", new FieldMapper("decode(scheduledTime"))
			.put("SCHEDULED_TIME)", new FieldMapper("scheduledTime)"))
			.put("COMMENTTEXT)", new FieldMapper("commentText)")).build();
	private final Map<String, FieldMapper> offenderBookingsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CAPACITY", new FieldMapper("capacity")).put("OFFENDER_BOOK_ID", new FieldMapper("offenderBookId"))
			.put("ROOT_OFFENDER_ID", new FieldMapper("rootOffenderId")).put("BOOKING_NO", new FieldMapper("bookingNo"))
			.build();
	private final Map<String, FieldMapper> livingUnitsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CAPACITY", new FieldMapper("capacity"))
			.put("OEM.MOVEMENT_REASON_CODE", new FieldMapper("oem.movementReasonCode"))
			.put("DSP_DESCRIPTION", new FieldMapper("dspDescription"))
			.put("LIV_UNIT.LIVING_UNIT_ID", new FieldMapper("livUnit.livingUnitId"))
			.put("LIV_UNITS.DESCRIPTION", new FieldMapper("livUnits.description"))
			.put("MR1.IN_MOVEMENT_REASON_CODE", new FieldMapper("mr1.inMovementReasonCode"))
			.put("CASELOAD_ID", new FieldMapper("caseloadId")).put("TO_AGY_LOC_ID", new FieldMapper("toAgyLocId"))
			.put("LIVING_UNIT_ID", new FieldMapper("livingUnitId"))
			.put("MR2.DESCRIPTION", new FieldMapper("mr2.description"))
			.put("DESCRIPTION", new FieldMapper("description"))
			.put("HOUSING_UNIT_TYPE", new FieldMapper("housingUnitType"))
			.put("LIV_UNIT.DESCRIPTION", new FieldMapper("livUnit.description")).build();
	private final Map<String, FieldMapper> staffMembersMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("STAFF_ID", new FieldMapper("staffId")).put("FIRST_NAME", new FieldMapper("firstName"))
			.put("LAST_NAME", new FieldMapper("lastName")).put("ASSIGNED_STAFF_ID", new FieldMapper("ASSIGNEDSTAFFID"))
			.put("CODE", new FieldMapper("code")).put("DESCRIPTION", new FieldMapper("description")).build();
	private final Map<String, FieldMapper> referenceCodesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("ARREST_AGENCY_LOC_ID", new FieldMapper("arrestAgencyLocId"))
			.put("REF_CODE1.CODE", new FieldMapper("refCode1.code"))
			.put("REFERENCE_CODES.DESCRIPTION", new FieldMapper("referenceCodes.description"))
			.put("BOOKING_STATUS", new FieldMapper("bookingStatus"))
			.put("DSP_DESCRIPTION", new FieldMapper("dspDescription"))
			.put("REF_CODE2.CODE", new FieldMapper("refCode2.code")).put("ESCORT_CODE", new FieldMapper("escortCode"))
			.put("REF_CODE2.DESCRIPTION", new FieldMapper("refCode2.description"))
			.put("REF_CODE1.DESCRIPTION", new FieldMapper("refCode1.description"))
			.put("AGY_LOC1.AGY_LOC_ID", new FieldMapper("toAgyLocId")).put("LIST_SEQ", new FieldMapper("listSeq"))
			.put("CODE", new FieldMapper("code")).put("DSP_DESCRIPTION3", new FieldMapper("dspDescription3")).build();
	private final Map<String, FieldMapper> omsModulesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("MODULE_NAME", new FieldMapper("moduleName")).put("MODULE_TYPE", new FieldMapper("moduleType"))
			.put("DESCRIPTION", new FieldMapper("description")).build();
	private final Map<String, FieldMapper> agencyReportingLocsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("AGY_LOC_ID", new FieldMapper("agyLocId")).put("REPORTING_LOC_ID", new FieldMapper("reportingLocId"))
			.put("COUNT_TYPE_ID", new FieldMapper("countTypeId"))
			.put("COUNT_IN_PROGRESS", new FieldMapper("countInProgress ")).put("OUTCOME", new FieldMapper(" outcome"))
			.put("COUNT_TYPE_CODE", new FieldMapper("countTypeCode"))
			.put("INITIATED_DATE", new FieldMapper("initiatedDate ")).put("AGY_SEQ", new FieldMapper(" agySeq "))
			.put("DECODE(SCHEDULED_TIME", new FieldMapper("decode(scheduledTime"))
			.put("SCHEDULED_TIME)", new FieldMapper("scheduledTime)")).build();
	private final Map<String, FieldMapper> agencyCountTypesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("AGY_LOC_ID", new FieldMapper("agyLocId")).put("REPORTING_LOC_ID", new FieldMapper("reportingLocId"))
			.put("COUNT_TYPE_ID", new FieldMapper("countTypeId"))
			.put("COUNT_TYPE_CODE", new FieldMapper("countTypeCode"))
			.put("INITIATED_DATE", new FieldMapper("initiatedDate "))
			.put("DECODE(SCHEDULED_TIME", new FieldMapper("decode(scheduledTime"))
			.put("SCHEDULED_TIME)", new FieldMapper("scheduledTime)"))
			.put("CASELOAD_ID)", new FieldMapper("caseLoadId)")).put("SESSION_ID)", new FieldMapper("sessionID)"))
			.build();

	/**
	 * Creates new OidsublcRepositoryImpl class Object
	 */
	public OidsublcRepositoryImpl() {
		
	}

	public List<AgencyLocationCounts> subLocCntExecuteQuery(final AgencyLocationCounts objSearchDao) {
		final String sql = getQuery("OIDSUBLC_SUBLOCCNT_FIND_AGENCY_LOCATION_COUNTS");
		final RowMapper<AgencyLocationCounts> AgencyLocationCountsRowMapper = Row2BeanRowMapper.makeMapping(sql,
				AgencyLocationCounts.class, agencyLocationCountsMapping);
		final ArrayList<AgencyLocationCounts> returnList = (ArrayList<AgencyLocationCounts>) namedParameterJdbcTemplate
				.query(sql, createParams(), AgencyLocationCountsRowMapper);
		return returnList;
	}

	/**
	 * @param
	 *
	 * @
	 *
	 */
	public int preInsert() {
		return 0;
	}

	/**
	 * This method is used to insert the records in the data base tables based on
	 *
	 * @param lstAgencyLocationCounts List<AgencyLocationCounts>
	 *
	 * @return List<Integer>
	 *
	 * @
	 */
	public Integer subLocCntInsertAgencyLocationCounts(final List<AgencyLocationCounts> lstAgencyLocationCounts) {
		final String sql = getQuery("OIDSUBLC_SUBLOCCNT_INSERT_AGENCY_LOCATION_COUNTS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstAgencyLocationCounts.size() == returnArray.length) {
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
	public List<AgencyLocations> cgfkAgyLocIdRecordGroup(final String caseloadId) {
		final String sql = getQuery("OIDSUBLC_FIND_CGFKAGYLOCID");
		final RowMapper<AgencyLocations> MRowMapper = Row2BeanRowMapper.makeMapping(sql, AgencyLocations.class,
				agencyLocationsMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams("CASELOADID", caseloadId), MRowMapper);
		} catch (Exception e) {
			logger.error("In method cgfkAgyLocIdRecordGroup", e);
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<TempOidcount> tempOidcountData(final Integer sessionId, final String caseloadId) {
		final String sql = getQuery("OIDSUBLC_COUNT_QUERY");
		final RowMapper<TempOidcount> MRowMapper = Row2BeanRowMapper.makeMapping(sql, TempOidcount.class,
				tempOidcountMapping);
		try {
			return namedParameterJdbcTemplate.query(sql,
					createParams("SESSION_ID", sessionId, "currentCaseLoadId", caseloadId), MRowMapper);
		} catch (Exception e) {
			logger.error("In method cgfkAgyLocIdRecordGroup", e);
			return Collections.emptyList();
		}
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgwhenNewFormInstanceCgPsessionId
	 *
	 * @param params
	 *
	 */
	public List<TempOidcount> cgwhenNewFormInstanceCgPsessionId(final String userId, final Integer sessionId) {
		final String sql = getQuery("OIDSUBLC_CGWHEN_NEW_FORM_INSTANCE_CG_P_SESSION_ID");
		List<TempOidcount> returnList = new ArrayList<TempOidcount>();
		final RowMapper<TempOidcount> MRowMapper = Row2BeanRowMapper.makeMapping(sql, TempOidcount.class,
				tempOidcountMapping);
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams("SESSIONID", sessionId), MRowMapper);
		} catch (Exception e) {
			logger.error(" ", e);
		}
		return returnList;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<MM>
	 */
	public List<ReferenceCodes> cgfkCountTypesRecordGroup() {
		final String sql = getQuery("OIDSUBLC_FIND_CGFKCOUNTTYPES");
		final RowMapper<ReferenceCodes> MMRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				referenceCodesMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), MMRowMapper);
		} catch (Exception e) {
			logger.error("In method cgfkCountTypesRecordGroup", e);
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<AgencyCountTypes> cgfkSchTimeRecordGroup() {
		final String sql = getQuery("OIDSUBLC_FIND_CGFKSCHTIME");
		final RowMapper<AgencyCountTypes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, AgencyCountTypes.class,
				agencyCountTypesMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (Exception e) {
			logger.error("In method cgfkSchTimeRecordGroup", e);
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<MM>
	 */
	public List<LivingUnits> cgfkHousingLevel1RecordGroup(final long countTypeCodeId) {
		final String sql = getQuery("OIDSUBLC_FIND_CGFKHOUSINGLEVEL1");
		final RowMapper<LivingUnits> mMRowMapper = Row2BeanRowMapper.makeMapping(sql, LivingUnits.class,
				livingUnitsMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams("COUNTTYPEID", countTypeCodeId), mMRowMapper);
		} catch (Exception e) {
			logger.error("In method cgfkHousingLevel1RecordGroup", e);
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<MMM>
	 */
	public List<LivingUnits> cgfkHousingLevel2RecordGroup(final long countTypeCodeId, final Integer livingUnitId) {
		final String sql = getQuery("OIDSUBLC_FIND_CGFKHOUSINGLEVEL2");
		final RowMapper<LivingUnits> mMMRowMapper = Row2BeanRowMapper.makeMapping(sql, LivingUnits.class,
				livingUnitsMapping);
		try {
			return namedParameterJdbcTemplate.query(sql,
					createParams("countTypeCodeId", countTypeCodeId, "livingUnitId", livingUnitId), mMMRowMapper);
		} catch (Exception e) {
			logger.error("In method cgfkHousingLevel2RecordGroup", e);
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<MMMM>
	 */
	public List<LivingUnits> cgfkHousingLevel3RecordGroup(final long countTypeCodeId, final Integer livingUnitId) {
		final String sql = getQuery("OIDSUBLC_FIND_CGFKHOUSINGLEVEL3");
		final RowMapper<LivingUnits> mMMMRowMapper = Row2BeanRowMapper.makeMapping(sql, LivingUnits.class,
				livingUnitsMapping);
		try {
			return namedParameterJdbcTemplate.query(sql,
					createParams("countTypeCodeId", countTypeCodeId, "livingUnitId", livingUnitId), mMMMRowMapper);
		} catch (Exception e) {
			logger.error("In method cgfkHousingLevel3RecordGroup", e);
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<MMMMM>
	 */
	public List<VIntLocSummaries> cgfkInitLocCodeRecordGroup(final long countTypeCodeId) {
		final String sql = getQuery("OIDSUBLC_FIND_CGFKINITLOCCODE");
		final RowMapper<VIntLocSummaries> mMMMMRowMapper = Row2BeanRowMapper.makeMapping(sql, VIntLocSummaries.class,
				VIntLocSummariesMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams("countTypeCodeId", countTypeCodeId),
					mMMMMRowMapper);
		} catch (Exception e) {
			logger.error("In method cgfkInitLocCodeRecordGroup", e);
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<StaffMembers> cgfkConductedByRecordGroup() {
		final String sql = getQuery("OIDSUBLC_FIND_CGFKCONDUCTEDBY");
		final RowMapper<StaffMembers> mRowMapper = Row2BeanRowMapper.makeMapping(sql, StaffMembers.class,
				staffMembersMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (Exception e) {
			logger.error("In method cgfkConductedByRecordGroup", e);
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<StaffMembers> cgfkConductedBy1RecordGroup() {
		final String sql = getQuery("OIDSUBLC_FIND_CGFKCONDUCTEDBY1");
		final RowMapper<StaffMembers> mRowMapper = Row2BeanRowMapper.makeMapping(sql, StaffMembers.class,
				staffMembersMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (Exception e) {
			logger.error("In method cgfkConductedBy1RecordGroup", e);
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<ReferenceCodes> cfgkRecountCodeRecordGroup() {
		final String sql = getQuery("OIDSUBLC_FIND_CFGKRECOUNTCODE");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				referenceCodesMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (Exception e) {
			logger.error("In method cfgkRecountCodeRecordGroup", e);
			return Collections.emptyList();
		}
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * subLocCntWhennewrecordinstance
	 *
	 * @param params
	 *
	 */
	public List<AgencyLocations> subLocCntWhennewrecordinstance(final AgencyLocations paramBean) {
		final String sql = getQuery("OIDSUBLC_SUB_LOC_CNT_WHENNEWRECORDINSTANCE_WHENNEWRECORDINSTANCE");
		final RowMapper<AgencyLocations> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, AgencyLocations.class,
				agencyLocationsMapping);
		final List<AgencyLocations> returnList = (ArrayList<AgencyLocations>) namedParameterJdbcTemplate.query(sql,
				createParams(), columnRowMapper);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * subLocCntWhenCreateRecord
	 *
	 * @param params
	 *
	 */
	public AgencyCountTypes subLocCntWhenCreateRecord(final AgencyCountTypes paramBean) {
		final String sql = getQuery("OIDSUBLC_SUB_LOC_CNT_WHENCREATERECORD");
		AgencyCountTypes returnObj = new AgencyCountTypes();
		final RowMapper<AgencyCountTypes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, AgencyCountTypes.class,
				agencyLocationsMapping);
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("caseloadId", paramBean.getCaseLoadId(), "agyLocId", paramBean.getAgyLocId()),
					columnRowMapper);
		} catch (Exception e) {
			logger.info("Error in subLocCntWhenCreateRecord method" + e.getMessage());
			return null;
		}
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * oidsublcKeyExit
	 *
	 * @param params
	 *
	 */
	public AgencyLocationCounts oidsublcKeyExit(final AgencyLocationCounts paramBean) {
		final String sql = getQuery("OIDSUBLC_OIDSUBLC_KEYEXIT");
		final RowMapper<AgencyLocationCounts> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				AgencyLocationCounts.class, agencyLocationCountsMapping);
		AgencyLocationCounts returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(),
				columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * createFormGlobalsCREATE_FORM_GLOBALS
	 *
	 * @param params
	 *
	 */
	public OmsModules createFormGlobalsCreateFormGlobals(final OmsModules paramBean) {
		final String sql = getQuery("OIDSUBLC_CREATE_FORM_GLOBALS_CREATE_FORM_GLOBALS");
		final RowMapper<OmsModules> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, OmsModules.class,
				omsModulesMapping);
		final OmsModules returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
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
	public AgencyLocations cgwhenNewFormInstance(final AgencyLocations paramBean) {
		final String sql = getQuery("OIDSUBLC_CGWHEN_NEW_FORM_INSTANCE_CGWHEN_NEW_FORM_INSTANCE");
		final RowMapper<AgencyLocations> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, AgencyLocations.class,
				agencyLocationsMapping);
		final AgencyLocations returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(),
				columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * defaultAgyLoc
	 *
	 * @param params
	 *
	 */
	public List<AgencyCountTypes> defaultAgyLoc(final String caseloadId) {
		final String sql = getQuery("OIDSUBLC_DEFAULT_AGY_LOC");
		List<AgencyCountTypes> returnObj = new ArrayList<AgencyCountTypes>();
		final RowMapper<AgencyCountTypes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, AgencyCountTypes.class,
				agencyLocationsMapping);
		try {
			returnObj = namedParameterJdbcTemplate.query(sql, createParams("caseloadId", caseloadId), columnRowMapper);
		} catch (Exception e) {
			logger.info("Error in defaultAgyLoc method" + e.getMessage());
			return null;
		}
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * housingLev1ValHOUSING_LEV_1_VAL
	 *
	 * @param params
	 *
	 */
	public List<AgencyReportingLocs> housingLev1ValHousingLev1Val(final AgencyReportingLocs paramBean) {
		final String sql = getQuery("OIDSUBLC_HOUSING_LEV_1_VAL_HOUSING_LEV_1_VAL");
		final RowMapper<AgencyReportingLocs> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				AgencyReportingLocs.class, agencyReportingLocsMapping);
		final ArrayList<AgencyReportingLocs> returnList = (ArrayList<AgencyReportingLocs>) namedParameterJdbcTemplate
				.query(sql, createParams(), columnRowMapper);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * housingLev2ValHOUSING_LEV_2_VAL
	 *
	 * @param params
	 *
	 */
	public List<AgencyReportingLocs> housingLev2ValHousingLev2Val(final AgencyReportingLocs paramBean) {
		final String sql = getQuery("OIDSUBLC_HOUSING_LEV_2_VAL_HOUSING_LEV_2_VAL");
		final RowMapper<AgencyReportingLocs> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				AgencyReportingLocs.class, agencyReportingLocsMapping);
		final ArrayList<AgencyReportingLocs> returnList = (ArrayList<AgencyReportingLocs>) namedParameterJdbcTemplate
				.query(sql, createParams(), columnRowMapper);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * updateCountRecordUPDATE_COUNT_RECORD
	 *
	 * @param params
	 *
	 */
	public AgencyReportingLocs updateCountRecordUpdateCountRecord(final AgencyReportingLocs paramBean) {
		final String sql = getQuery("OIDSUBLC_UPDATE_COUNT_RECORD_UPDATE_COUNT_RECORD");
		final RowMapper<AgencyReportingLocs> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				AgencyReportingLocs.class, agencyReportingLocsMapping);
		final AgencyReportingLocs returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(),
				columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * updateCountRecordUPDATE_COUNT_RECORD
	 *
	 * @param params
	 *
	 */
	public List<OffenderBookings> updateCountRecordUpdateCountRecord(final OffenderBookings paramBean) {
		final String sql = getQuery("OIDSUBLC_UPDATE_COUNT_RECORD_UPDATE_COUNT_RECORD");
		final RowMapper<OffenderBookings> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, OffenderBookings.class,
				offenderBookingsMapping);
		final ArrayList<OffenderBookings> returnList = (ArrayList<OffenderBookings>) namedParameterJdbcTemplate
				.query(sql, createParams(), columnRowMapper);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * updateCountRecordUPDATE_COUNT_RECORD
	 *
	 * @param params
	 *
	 */
	public List<AgencyCounts> updateCountRecordUpdateCountRecord(final AgencyCounts paramBean) {
		final String sql = getQuery("OIDSUBLC_UPDATE_COUNT_RECORD_UPDATE_COUNT_RECORD");
		final RowMapper<AgencyCounts> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, AgencyCounts.class,
				agencyCountsMapping);
		final ArrayList<AgencyCounts> returnList = (ArrayList<AgencyCounts>) namedParameterJdbcTemplate.query(sql,
				createParams(), columnRowMapper);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * updateCountRecordUPDATE_COUNT_RECORD
	 *
	 * @param params
	 *
	 */
	public AgencyLocationCounts updateCountRecordUpdateCountRecord(final AgencyLocationCounts paramBean) {
		final String sql = getQuery("OIDSUBLC_UPDATE_COUNT_RECORD_UPDATE_COUNT_RECORD");
		final RowMapper<AgencyLocationCounts> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				AgencyLocationCounts.class, agencyLocationCountsMapping);
		AgencyLocationCounts returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(),
				columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * runReport
	 *
	 * @param params
	 *
	 */
	public SystemProfiles runReport(final SystemProfiles paramBean) {
		final String sql = getQuery("OIDSUBLC_RUN_REPORT");
		final RowMapper<SystemProfiles> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, SystemProfiles.class,
				systemProfilesMapping);
		final SystemProfiles returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(),
				columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * getHousingLevels
	 *
	 * @param params
	 *
	 */
	public AgencyLocations getHousingLevels(final String caseloaId) {
		final String sql = getQuery("OIDSUBLC_CGWHEN_NEW_FORM_INSTANCE");
		final RowMapper<AgencyLocations> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, AgencyLocations.class,
				agencyLocationsMapping);
		final AgencyLocations returnObj = namedParameterJdbcTemplate.queryForObject(sql,
				createParams("AGY_LOC_ID", caseloaId), columnRowMapper);
		return returnObj;
	}

	public String getLabelDescription(final String data) {
		final String sql = getQuery("OIDSUBLC_GET_LABEL_DESCRIPTION");
		String returnObj = null;
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams("CODE", data), String.class);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in getLabelDescription " + e);
			return returnObj;
		}
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * getUpdateCountLcheckProc
	 *
	 * @param searchBean
	 *
	 */
	public String getUpdateCountLcheckProc(final AgencyLocationCounts searchBean) {
		final String sql = getQuery("OIDSUBLC_UPDATE_COUNT_RECORDUPDATE_COUNT_RECORD");
		String returnObj = null;
		try {
			returnObj = namedParameterJdbcTemplate
					.queryForObject(sql,
							createParams("COUNT_TYPE_ID", searchBean.getCountTypeId(), "AGY_SEQ",
									searchBean.getAgySeq(), "REPORTING_LOC_ID", searchBean.getReportingLocId()),
							String.class);
		} catch (EmptyResultDataAccessException e) {
			logger.error(this.getClass().getName() + " error in getUpdateCountLcheckProc " + e);
			return returnObj;
		}
		return returnObj;
	}

	public String getCountLcheckProc(final AgencyLocationCounts searchBean) {
		final String sql = getQuery("OIDSUBLC_COUNT_RECORDUPDATE_COUNT_RECORD");
		String returnObj = null;
		try {
			returnObj = namedParameterJdbcTemplate
					.queryForObject(sql,
							createParams("COUNT_TYPE_ID", searchBean.getCountTypeId(), "AGY_SEQ",
									searchBean.getAgySeq(), "REPORTING_LOC_ID", searchBean.getReportingLocId()),
							String.class);
		} catch (EmptyResultDataAccessException e) {
			logger.error(this.getClass().getName() + " error in getCountLcheckProc " + e);
			return returnObj;
		}
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * getAgySeqWithLivUnitId
	 *
	 * @param searchBean
	 *
	 */
	public Integer getAgySeqWithLivUnitId(final AgencyLocationCounts searchBean) {
		final String sql = getQuery("OIDSUBLC_UPDATE_COUNT_RECORD_AGYSEQ_LIVINGUNITID");
		Integer returnObj = null;
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("LIV_UNIT_ID1", searchBean.getLivingUnitId1(), "LIV_UNIT_ID2",
							searchBean.getLivingUnitId2(), "LIV_UNIT_ID3", searchBean.getLivingUnitId3(),
							"COUNT_TYPE_ID", searchBean.getCountTypeId()),
					Integer.class);
		} catch (EmptyResultDataAccessException e) {
			logger.error(this.getClass().getName() + " error in getAgySeqWithLivUnitId " + e);
			return returnObj;
		}
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * getActualCountWithLivUnitId
	 *
	 * @param searchBean
	 *
	 */
	public Integer getActualCountWithLivUnitId(final AgencyLocationCounts searchBean) {
		final String sql = getQuery("OIDSUBLC_UPDATE_COUNT_RECORD_ACTUAL_COUNT_LIVINGUNITID");
		Integer returnObj = null;
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("LIV_UNIT_ID", searchBean.getLivingUnitId(), "AGY_LOC_ID", searchBean.getAgyLocId()),
					Integer.class);
		} catch (EmptyResultDataAccessException e) {
			logger.error(this.getClass().getName() + " error in getActualCountWithLivUnitId " + e);
			return returnObj;
		}
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * getAgySeqWithIntLocId
	 *
	 * @param searchBean
	 *
	 */
	public Integer getAgySeqWithIntLocId(final AgencyLocationCounts searchBean) {
		final String sql = getQuery("OIDSUBLC_UPDATE_COUNT_RECORD_AGYSEQ_INTLOC");
		Integer returnObj = null;
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams("INTERNAL_LOCATION_ID",
					searchBean.getInternalLocationId(), "COUNT_TYPE_ID", searchBean.getCountTypeId()), Integer.class);
		} catch (EmptyResultDataAccessException e) {
			logger.error(this.getClass().getName() + " error in getAgySeqWithIntLocId " + e);
			return returnObj;
		}
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * getActualCountWithIntLocId
	 *
	 * @param searchBean
	 *
	 */
	public Integer getActualCountWithIntLocId(final AgencyLocationCounts searchBean) {
		final String sql = getQuery("OIDSUBLC_UPDATE_COUNT_RECORD_ACTUAL_COUNT_INTLOC");
		Integer returnObj = null;
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams("INTERNAL_LOCATION_ID",
					searchBean.getInternalLocationId(), "AGY_LOC_ID", searchBean.getAgyLocId()), Integer.class);
		} catch (EmptyResultDataAccessException e) {
			logger.error(this.getClass().getName() + " error in getActualCountWithIntLocId " + e);
			return returnObj;
		}
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * getMaxReportingId
	 *
	 * @param searchBean
	 *
	 */
	public Integer getMaxReportingId(final AgencyLocationCounts searchBean) {
		final String sql = getQuery("OIDSUBLC_UPDATE_COUNT_RECORD_GETMAXREPORTINGID");
		final Integer returnObj = namedParameterJdbcTemplate.queryForObject(sql,
				createParams("COUNT_TYPE_ID", searchBean.getCountTypeId()), Integer.class);
		return returnObj;
	}

	/**
	 * This method is used to update the records in the data base tables based on
	 *
	 * @param lstAgencyLocationCounts List<AgencyLocationCounts>
	 *
	 * @return List<Integer>
	 *
	 * @
	 */
	public Integer agencyLocationCountsUpdateCountNotEquals(final List<AgencyLocationCounts> lstAgencyLocationCounts) {
		final String sql = getQuery("OIDSUBLC_UPDATE_QUERY_COUNTNOTEQUALS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final AgencyLocationCounts agyLocObj : lstAgencyLocationCounts) {
			parameters.add(new BeanPropertySqlParameterSource(agyLocObj));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstAgencyLocationCounts.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

	/**
	 * This method is used to update the records in the data base tables based on
	 *
	 * @param lstAgencyLocationCounts List<AgencyLocationCounts>
	 *
	 * @return List<Integer>
	 *
	 * @
	 */
	public Integer agencyLocationCountsUpdateCountEquals(final List<AgencyLocationCounts> lstAgencyLocationCounts) {
		final String sql = getQuery("OIDSUBLC_UPDATE_QUERY_COUNTEQUALS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final AgencyLocationCounts agyLocObj : lstAgencyLocationCounts) {
			parameters.add(new BeanPropertySqlParameterSource(agyLocObj));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstAgencyLocationCounts.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

	/**
	 * This method is used to update the records in the data base tables based on
	 *
	 * @param lstAgencyLocationCounts List<AgencyLocationCounts>
	 *
	 * @return List<Integer>
	 *
	 * @
	 */
	public Integer resubmitCountUpdateQuery(final List<AgencyLocationCounts> lstAgencyLocationCounts) {
		final String sql = getQuery("OIDSUBLC_RESUBMITCOUNT_UPDATE_QUERY");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final AgencyLocationCounts agyLocObj : lstAgencyLocationCounts) {
			parameters.add(new BeanPropertySqlParameterSource(agyLocObj));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstAgencyLocationCounts.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

	/**
	 * This method is used to update the records in the data base tables based on
	 *
	 * @param lstAgencyLocationCounts List<AgencyLocationCounts>
	 *
	 * @return List<Integer>
	 *
	 * @
	 */
	public Integer resubmitCountDeleteQuery(final List<AgencyLocationCounts> lstAgencyLocationCounts) {
		final String sql = getQuery("OIDSUBLC_RESUBMITCOUNT_DELETE_QUERY");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final AgencyLocationCounts agyLocObj : lstAgencyLocationCounts) {
			parameters.add(new BeanPropertySqlParameterSource(agyLocObj));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstAgencyLocationCounts.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * getMaxReportingId
	 *
	 * @param searchBean
	 *
	 */
	public String getUserId(String userId) {
		final String sql = getQuery("OIDSUBLC_GET_USER_ID");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("USERID",userId), String.class);
	}

	public String getDescription(final String caseloadId,final String userId) {
		final String sql = getQuery("OIDSUBLC_GET_DESCRIPTION");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("AGYLOCID", caseloadId, "USERID",userId ), String.class);
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * getMaxReportingId
	 *
	 * @param searchBean
	 *
	 */
	public Integer getSessionId() {
		final String sql = getQuery("OIDSUBLC_GET_SESSIONID");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams(), Integer.class);
	}

}

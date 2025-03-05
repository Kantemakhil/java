package net.syscon.s4.inst.classification.impl;

import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.LivingUnits;
import net.syscon.s4.im.beans.OmsModules;
import net.syscon.s4.inst.classification.OiiclassRepository;
import net.syscon.s4.inst.classification.beans.Assessments;
import net.syscon.s4.inst.classification.beans.OiiclassClassInquiry;
import oracle.jdbc.OracleTypes;

@Repository
public class OiiclassRepositoryImpl extends RepositoryBase implements OiiclassRepository {

	private final Map<String, FieldMapper> livingUnitsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CERTIFIED_FLAG", new FieldMapper("certifiedFlag")).put("USER_DESC", new FieldMapper("userDesc"))
			.put("REACH_OPER_CAPACITY_FLAG", new FieldMapper("reachOperCapacityFlag"))
			.put("LIVING_UNIT_ID", new FieldMapper("livingUnitId")).put("LEVEL_4_CODE", new FieldMapper("level4Code"))
			.put("DEACTIVATE_DATE", new FieldMapper("deactivateDate"))
			.put("SECURITY_LEVEL_CODE", new FieldMapper("securityLevelCode"))
			.put("LIVING_UNIT_CODE", new FieldMapper("livingUnitCode"))
			.put("PARENT_LIVING_UNIT_ID", new FieldMapper("parentLivingUnitId"))
			.put("CONTROL_ACTIVE_FLAG", new FieldMapper("controlActiveFlag"))
			.put("LOWEST_LEVEL_FLAG", new FieldMapper("lowestLevelFlag"))
			.put("LEVEL_2_CODE", new FieldMapper("level2Code"))
			.put("HOUSING_UNIT_TYPE", new FieldMapper("housingUnitType"))
			.put("DEACTIVATE_REASON_CODE", new FieldMapper("deactivateReasonCode"))
			.put("DESCRIPTION", new FieldMapper("description")).put("LEVEL_1_CODE", new FieldMapper("level1Code"))
			.put("COMMENT_TEXT", new FieldMapper("commentText"))
			.put("OPERATION_CAPACITY", new FieldMapper("operationCapacity"))
			.put("CAPACITY", new FieldMapper("capacity")).put("ACTIVE_FLAG", new FieldMapper("activeFlag"))
			.put("LIST_SEQ", new FieldMapper("listSeq")).put("ACA_CAP_RATING", new FieldMapper("acaCapRating"))
			.put("LIVING_UNIT_TYPE", new FieldMapper("livingUnitType")).put("CNA_NO", new FieldMapper("cnaNo"))
			.put("AGY_LOC_ID", new FieldMapper("agyLocId")).put("REACTIVATE_DATE", new FieldMapper("reactivateDate"))
			.put("LEVEL_3_CODE", new FieldMapper("level3Code")).put("NO_OF_OCCUPANT", new FieldMapper("noOfOccupant"))
			.build();
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

	private final Map<String, FieldMapper> mMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("AGY_LOC_ID", new FieldMapper("agyLocId")).put("DESCRIPTION", new FieldMapper("description"))
			.put("LIVING_UNIT_CODE", new FieldMapper("livingUnitCode")).build();

	private final Map<String, FieldMapper> omsModulesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("AGY_LOC_ID", new FieldMapper("agyLocId")).put("DESCRIPTION", new FieldMapper("description"))
			.put("LIVING_UNIT_CODE", new FieldMapper("livingUnitCode")).build();

	private final Map<String, FieldMapper> agencyLocationsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("AGY_LOC_ID", new FieldMapper("agyLocId")).put("DESCRIPTION", new FieldMapper("description"))
			.put("LIVING_UNIT_CODE", new FieldMapper("livingUnitCode")).build();

	private final Map<String, FieldMapper> assesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("ASSESSMENT_TYPE", new FieldMapper("assessmentType"))
			.put("ASSESSMENT_ID", new FieldMapper("assessmentId")).build();

	/**
	 * Creates new OiiclassRepositoryImpl class Object
	 */
	public OiiclassRepositoryImpl() {
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao SystemProfiles
	 *
	 * @return List<SystemProfiles>
	 *
	 * @throws SQLException
	 */
	private static Logger logger = LogManager.getLogger(OiiclassRepositoryImpl.class.getName());

	public List<SystemProfiles> sysProfExecuteQuery(final SystemProfiles objSearchDao) {
		final String sql = getQuery("OIICLASS_SYSPROF_FIND_SYSTEM_PROFILES");
		final RowMapper<SystemProfiles> SystemProfilesRowMapper = Row2BeanRowMapper.makeMapping(sql,
				SystemProfiles.class, systemProfilesMapping);
		final ArrayList<SystemProfiles> returnList = (ArrayList<SystemProfiles>) namedParameterJdbcTemplate.query(sql,
				SystemProfilesRowMapper);
		return returnList;
	}

	/**
	 * @param
	 *
	 * @throws SQLException
	 *
	 */
	public int PRE_INSERT() {
		return 0;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao LivingUnits
	 *
	 * @return List<LivingUnits>
	 *
	 * @throws SQLException
	 */
	public List<LivingUnits> livUnitExecuteQuery(final LivingUnits objSearchDao) {
		final String sql = getQuery("OIICLASS_LIVUNIT_FIND_LIVING_UNITS");
		final RowMapper<LivingUnits> LivingUnitsRowMapper = Row2BeanRowMapper.makeMapping(sql, LivingUnits.class,
				livingUnitsMapping);
		final ArrayList<LivingUnits> returnList = (ArrayList<LivingUnits>) namedParameterJdbcTemplate.query(sql,
				LivingUnitsRowMapper);
		return returnList;
	}

	/**
	 * This method is used to insert the records in the data base tables based on
	 *
	 * @param lstLivingUnits List<LivingUnits>
	 *
	 * @return List<Integer>
	 *
	 * @throws SQLException
	 */
	public Integer livunitInsertLivingUnits(final List<LivingUnits> lstLivingUnits) {
		String sql = getQuery("OIICLASS_LIVUNIT_INSERT_LIVING_UNITS");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));

		if (lstLivingUnits.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstLivingUnits List<LivingUnits>
	 *
	 * @throws SQLException
	 */
	public Integer livUnitUpdateLivingUnits(final List<LivingUnits> lstLivingUnits) {
		String sql = getQuery("OIICLASS_LIVUNIT_UPDATE_LIVING_UNITS");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (LivingUnits livingUnits : lstLivingUnits) {
			parameters.add(new BeanPropertySqlParameterSource(livingUnits));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstLivingUnits.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao OiiclassClassInquiry
	 *
	 * @return List<OiiclassClassInquiry>
	 *
	 * @throws SQLException
	 */
	public List<OiiclassClassInquiry> oiiclassExecuteQuery(final OiiclassClassInquiry objSearchDao) {
		Map<String, Object> returnObject = null;
		final List<OiiclassClassInquiry> lListObj = new ArrayList<OiiclassClassInquiry>();
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[20];
		sqlParameters = new SqlParameter[] { new SqlParameter("P_SEARCH_TYPE", Types.VARCHAR),
				new SqlParameter("P_CASELOAD", Types.VARCHAR), new SqlParameter("P_FROM_DATE", Types.DATE),
				new SqlParameter("P_TO_DATE", Types.DATE), new SqlParameter("P_ASSESSMENT_ID", Types.NUMERIC),
				new SqlParameter("P_AGY_LOC_ID", Types.VARCHAR), new SqlParameter("P_LOCATION", Types.VARCHAR),
				new SqlOutParameter("RESULTSET", OracleTypes.CURSOR) };
		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withProcedureName("OIICLASS_CLASS_INQUIRY").declareParameters(sqlParameters);
		inParamMap.put("RESULTSET", OracleTypes.CURSOR);
		inParamMap.put("P_SEARCH_TYPE", objSearchDao.getpSearchType());
		inParamMap.put("P_CASELOAD", objSearchDao.getpCaseload());
		inParamMap.put("P_FROM_DATE", objSearchDao.getpFromDate());
		inParamMap.put("P_TO_DATE", objSearchDao.getpToDate());
		inParamMap.put("P_ASSESSMENT_ID", objSearchDao.getpAssessmentId());
		inParamMap.put("P_AGY_LOC_ID", objSearchDao.getpAgyLocId());
		inParamMap.put("P_LOCATION", objSearchDao.getpLocation());
		final MapSqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);

		try {
			returnObject = simpleJDBCCall.execute(inParameter);
			final List<Map<String, Object>> list = (ArrayList<Map<String, Object>>) returnObject.get("RESULTSET");
			for (int i = 0; i < list.size(); i++) {
				final Map<String, Object> childMap = list.get(i);
				final OiiclassClassInquiry bean = new OiiclassClassInquiry();
				bean.setOffenderBookID(Long.parseLong(String.valueOf(childMap.get("OFFENDER_BOOK_ID"))));
				bean.setOffenderIdDisplay(String.valueOf(childMap.get("OFFENDER_ID_DISPLAY")));
				bean.setBookingNo(String.valueOf(childMap.get("BOOKING_NO")));
				bean.setOffenderName(String.valueOf(childMap.get("OFFENDER_NAME")));
				bean.setLocation(String.valueOf(childMap.get("LOCATION")));
				bean.setCurrentLevel(String.valueOf(childMap.get("CURRENT_LEVEL")));
				if (bean.getCurrentLevel() == null || bean.getCurrentLevel().equals("null")) {
					bean.setCurrentLevel(
							String.valueOf(childMap.get("OMS_INTAKE.GET_OFFENDER_SUPERVISION(OB.OFFENDER_BOOK_ID)")));
				}
				bean.setAssessmentType(String.valueOf(childMap.get("ASSESSMENT_TYPE")));
				bean.setAssessmentId(String.valueOf(childMap.get("ASSESSMENT_ID")));
				Date date = (Date) childMap.get("SCHEDULE_DATE");
				bean.setScheduleDate(date);
				lListObj.add(bean);
			}

		} catch (Exception e) {
			logger.error("oiiclassExecuteQuery", e);
		}

		return lListObj;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<Assessments> cgfkAssessmentTypeRecordGroup(final String userId) {
		final String sql = getQuery("OIICLASS_FIND_CGFKASSESSMENTTYPE");
		final RowMapper<Assessments> mRowMapper = Row2BeanRowMapper.makeMapping(sql, Assessments.class, assesMapping);
		List<Assessments> resultList = new ArrayList<>();
		try {
			resultList = namedParameterJdbcTemplate.query(sql, createParams("userId", userId), mRowMapper);
		} catch (Exception e) {
			return null;
		}
		return resultList;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<AgencyLocations> cgfkAgyLocIdRecordGroup(final String userId) {
		final String sql = getQuery("OIICLASS_FIND_CGFKAGYLOCID");
		final RowMapper<AgencyLocations> mRowMapper = Row2BeanRowMapper.makeMapping(sql, AgencyLocations.class,
				agencyLocationsMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams("userId", userId), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<LivingUnits> cgfkHousingLevel1RecordGroup(final String agyLocId) {
		final String sql = getQuery("OIICLASS_FIND_CGFKHOUSINGLEVEL1");
		final RowMapper<LivingUnits> mRowMapper = Row2BeanRowMapper.makeMapping(sql, LivingUnits.class,
				livingUnitsMapping);
		List<LivingUnits> resultList = new ArrayList<>();
		try {

			resultList = namedParameterJdbcTemplate.query(sql, createParams("agyLocId", agyLocId), mRowMapper);
		} catch (Exception e) {
			return null;
		}
		return resultList;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<LivingUnits> cgfkHousingLevel2RecordGroup(final String agyLocId, final String livingUnitId) {
		final String sql = getQuery("OIICLASS_FIND_CGFKHOUSINGLEVEL2");
		final RowMapper<LivingUnits> mRowMapper = Row2BeanRowMapper.makeMapping(sql, LivingUnits.class,
				livingUnitsMapping);
		List<LivingUnits> resultList = new ArrayList<>();
		try {

			resultList = namedParameterJdbcTemplate.query(sql,
					createParams("agyLocId", agyLocId, "livingUnitId", livingUnitId), mRowMapper);
		} catch (Exception e) {
			return null;
		}
		return resultList;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<LivingUnits> cgfkHousingLevel3RecordGroup() {
		final String sql = getQuery("OIICLASS_FIND_CGFKHOUSINGLEVEL3");
		final RowMapper<LivingUnits> mRowMapper = Row2BeanRowMapper.makeMapping(sql, LivingUnits.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * createFormGlobals
	 *
	 * @param params
	 *
	 */
	public OmsModules createFormGlobals(final OmsModules paramBean) {
		final String sql = getQuery("OIICLASS_CREATE_FORM_GLOBALS");
		final RowMapper<OmsModules> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, OmsModules.class,
				omsModulesMapping);
		OmsModules returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * defaultAgyLocDEFAULT_AGY_LOC
	 *
	 * @param params
	 *
	 */
	public AgencyLocations defaultAgyLocDefaultAgyLoc(final AgencyLocations paramBean) {
		final String sql = getQuery("OIICLASS_DEFAULT_AGY_LOC_DEFAULT_AGY_LOC");
		final RowMapper<AgencyLocations> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, AgencyLocations.class,
				agencyLocationsMapping);
		AgencyLocations returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
		return returnObj;
	}

	@Override
	public Integer livUnitInsertLivingUnits(List<LivingUnits> lstLivingUnits) {
		return null;
	}

}

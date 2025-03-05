package net.syscon.s4.inst.movements.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.jdbc.support.oracle.SqlReturnStruct;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlInOutParameter;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.beans.VHeaderBlock;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.LivingUnits;
import net.syscon.s4.im.beans.Offenders;
import net.syscon.s4.inst.movements.OidintmvRepository;
import net.syscon.s4.inst.movements.beans.OffRec;
import net.syscon.s4.inst.movements.beans.OffenderInterMvmtLocations;
import net.syscon.s4.inst.movements.beans.VIntLocSummaries;
import net.syscon.s4.inst.movements.beans.VOffenderBookings;
import net.syscon.s4.inst.schedules.bean.VOffenderAllSchedules;
import oracle.jdbc.OracleTypes;

/**
 * Class OidintmvRepositoryImpl
 */
@Repository
public class OidintmvRepositoryImpl extends RepositoryBase implements OidintmvRepository {
	private static Logger logger = LogManager.getLogger(OidintmvRepositoryImpl.class.getName());

	/**
	 * Creates new OidintmvRepositoryImpl class Object
	 */
	public OidintmvRepositoryImpl() {
	}

	private final Map<String, FieldMapper> agencyInternalLocationsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DESCRIPTION", new FieldMapper(" description ")).put("AGENCY_IML_ID", new FieldMapper("agencyImlId"))
			.put("INTERNAL_LOCATION_ID", new FieldMapper("internalLocationId"))
			.put("INT_LOC_PROFILE_CODE", new FieldMapper("intLocProfileCode")).build();
	private final Map<String, FieldMapper> mMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("USER_DESC", new FieldMapper("userDesc"))
			.put("INTERNAL_LOCATION_CODE", new FieldMapper("internalLocationCode"))
			.put("ACTIVE_FLAG", new FieldMapper("activeFlag"))
			.build();
	private final Map<String, FieldMapper> systemProfilesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("PROFILE_VALUE_2", new FieldMapper(" profileValue2 ")).build();
	private final Map<String, FieldMapper> vOffSchMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("internal_location_id", new FieldMapper("toInternalLocationId"))
			.build();
	private final Map<String, FieldMapper> offeProgProfMap = new ImmutableMap.Builder<String, FieldMapper>()
			.put("OFFENDER_END_DATE", new FieldMapper("offenderEndDate"))
			.put("OFFENDER_PROGRAM_STATUS", new FieldMapper("offenderProgramStatus"))
			.put("OFFENDER_BOOK_ID", new FieldMapper("offenderBookId"))
			.put("OFFENDER_START_DATE", new FieldMapper("offenderStartDate"))
			.put("OFF_PRGREF_ID", new FieldMapper("offPrgrefId")).put("OFFENDER_ID", new FieldMapper("offenderId"))
			.put("PROGRAM_ID", new FieldMapper("programId"))
			.put("MIN(OFFENDER_START_DATE)", new FieldMapper("min(offenderStartDate)"))
			.put("REFERRAL_DATE", new FieldMapper("referralDate"))
			.put("REFCOMMENTVAL", new FieldMapper("refCommentVal")).put("REJDATE", new FieldMapper("rejDate"))
			.put("REJREASON", new FieldMapper("rejReason")).put("DECISION", new FieldMapper("decision"))
			.put("REFERRAL_COMMENT_TEXT", new FieldMapper("referralCommentText"))
			.put("REFERRAL_PRIORITY", new FieldMapper("referralPriority"))
			.put("REJECT_REASON_CODE", new FieldMapper("rejectReasonCode"))
			.put("REJECT_DATE", new FieldMapper("rejectDate"))
			.put("WAITLIST_DECISION_CODE", new FieldMapper("waitlistDecisionCode"))
			.put("SUSPENDED_FLAG", new FieldMapper("suspendedFlag"))
			.put("CREATE_USER_ID", new FieldMapper("createUserId"))
			.put("MODIFY_USER_ID", new FieldMapper("modifyUserId"))
			.put("MODIFY_DATETIME", new FieldMapper("modifyDatetime"))
			.put("COMMENT_TEXT", new FieldMapper("commentText")).put("AGY_LOC_ID", new FieldMapper("agyLocId"))
			.put("CRS_ACTY_ID", new FieldMapper("crsActyId")).build();

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            OidintmvGetSchedules
	 *
	 * @return List<OidintmvGetSchedules>
	 *
	 * 
	 */
	public List<VOffenderAllSchedules> offBlkExecuteQuery(final VOffenderAllSchedules objSearchDao) {
		Map<String, Object> returnObject = null;
		final List<VOffenderAllSchedules> lListObj = new ArrayList<VOffenderAllSchedules>();
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[20];
		sqlParameters = new SqlParameter[] { new SqlOutParameter("resultset", OracleTypes.CURSOR),
				new SqlParameter("P_AGY_LOC_ID", OracleTypes.VARCHAR),
				new SqlParameter("P_MOVE_DATE", OracleTypes.DATE), new SqlParameter("P_MOVE_TIME", OracleTypes.DATE),
				new SqlParameter("P_FROM_TIME", OracleTypes.DATE), new SqlParameter("P_TO_TIME", OracleTypes.DATE),
				new SqlParameter("P_MOVE_TYPE", OracleTypes.VARCHAR),
				new SqlInOutParameter("V_SCHEDULE_TAB", OracleTypes.ARRAY),
				new SqlParameter("P_LIVING_UNIT_LEVEL_2", OracleTypes.VARCHAR),
				new SqlParameter("P_LIVING_UNIT_LEVEL_1", OracleTypes.VARCHAR),
				new SqlParameter("P_LIVING_UNIT_LEVEL_3", OracleTypes.VARCHAR),
				new SqlParameter("P_AGENCY_IML_LEVEL_2", OracleTypes.VARCHAR),
				new SqlParameter("P_AGENCY_IML_LEVEL_1", OracleTypes.VARCHAR),
				new SqlParameter("P_AGENCY_IML_LEVEL_3", OracleTypes.VARCHAR),
				new SqlParameter("P_TO_INT_LOC_LEVEL_2", OracleTypes.VARCHAR),
				new SqlParameter("P_TO_INT_LOC_LEVEL_1", OracleTypes.VARCHAR),
				new SqlParameter("P_TO_INT_LOC_LEVEL_3", OracleTypes.VARCHAR),
				new SqlParameter("P_OFFENDER_ID_DISPLAY", OracleTypes.VARCHAR),
				new SqlParameter("P_EVENT_TYPE", OracleTypes.VARCHAR),
				new SqlParameter("P_EVENT_SUB_TYPE", OracleTypes.VARCHAR),
				new SqlParameter("P_TO_INT_LOC_ID", OracleTypes.NUMBER) };
		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("OIDINTMV").withProcedureName("GET_SCHEDULES").declareParameters(sqlParameters);
		inParamMap.put("P_LIVING_UNIT_LEVEL_2", null);
		inParamMap.put("P_LIVING_UNIT_LEVEL_1", null);
		inParamMap.put("P_LIVING_UNIT_LEVEL_3", null);
		inParamMap.put("P_TO_INT_LOC_ID", objSearchDao.getToInternalLocationId());
		inParamMap.put("P_TO_TIME", objSearchDao.getEndTime());
		inParamMap.put("P_MOVE_TIME", objSearchDao.getScheduleMovementTime());
		inParamMap.put("P_TO_INT_LOC_LEVEL_2", objSearchDao.getToIntLocLevel2Code());
		inParamMap.put("P_TO_INT_LOC_LEVEL_1", objSearchDao.getToIntLocLevel1Code());
		inParamMap.put("P_AGENCY_IML_LEVEL_3", objSearchDao.getAgencyImlLevel3Code());
		inParamMap.put("P_AGY_LOC_ID", objSearchDao.getAgyLocId());
		inParamMap.put("P_AGENCY_IML_LEVEL_2", objSearchDao.getAgencyImlLevel2Code());
		inParamMap.put("P_AGENCY_IML_LEVEL_1", objSearchDao.getAgencyImlLevel1Code());
		inParamMap.put("P_EVENT_TYPE", null);
		inParamMap.put("P_MOVE_DATE", objSearchDao.getEventDate());
		inParamMap.put("P_TO_INT_LOC_LEVEL_3", objSearchDao.getToIntLocLevel3Code());
		inParamMap.put("P_OFFENDER_ID_DISPLAY", objSearchDao.getOffenderIdDisplay());
		inParamMap.put("P_FROM_TIME", objSearchDao.getStartTime());
		inParamMap.put("P_MOVE_TYPE", objSearchDao.getEventType());
		inParamMap.put("P_EVENT_SUB_TYPE", objSearchDao.getEventSubType());
		final SqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		try {
			returnObject = (Map<String, Object>) simpleJDBCCall.withoutProcedureColumnMetaDataAccess()
					.execute(inParameter);
			List<Map<String, String>> list = (List<Map<String, String>>) returnObject.get("resultset");
			for (int i = 0; i < list.size(); i++) {
				Map<String, String> childMap = list.get(i);
				VOffenderAllSchedules bean = new VOffenderAllSchedules();
				bean.setEventType(childMap.get("EVENT_TYPE"));
				bean.setToInternalLocationDesc(childMap.get("TO_INTERNAL_LOCATION_DESC"));
				bean.setOffenderFirstName(childMap.get("OFFENDER_FIRST_NAME"));
				bean.setEventSubType(childMap.get("EVENT_SUB_TYPE"));
				bean.setOffenderId(BigDecimal.valueOf(Long.valueOf(childMap.get("OFFENDER_ID"))));
				bean.setOffenderLastName(childMap.get("OFFENDER_LAST_NAME"));
				bean.setEventStatus(childMap.get("EVENT_STATUS"));
				bean.setEventTypeDesc(childMap.get("EVENT_TYPE_DESC"));
				bean.setLivingUnitId(BigDecimal.valueOf(Long.valueOf(childMap.get("LIVING_UNIT_ID"))));
				bean.setToInternalLocationId(BigDecimal.valueOf(Long.valueOf(childMap.get("TO_INTERNAL_LOCATION_ID"))));
				bean.setAgyLocId(childMap.get("AGY_LOC_ID"));
				bean.setAgencyImlId(BigDecimal.valueOf(Long.valueOf(childMap.get("AGENCY_IML_ID"))));
				bean.setOffenderBookId(BigDecimal.valueOf(Long.valueOf(childMap.get("OFFENDER_BOOK_ID"))));
				bean.setLivingUnitDesc(childMap.get("LIVING_UNIT_DESC"));
				bean.setAgencyImlDesc(childMap.get("AGENCY_IML_DESC"));
				bean.setOffenderIdDisplay(childMap.get("OFFENDER_ID_DISPLAY"));
				bean.setEventId(BigDecimal.valueOf(Long.valueOf(childMap.get("EVENT_ID"))));
				bean.setEventSubTypeDesc(childMap.get("EVENT_SUB_TYPE_DESC"));
				lListObj.add(bean);
			}
		} catch (Exception e) {
			logger.error("offBlkExecuteQuery: ", e);
		}

		return lListObj;
	}

	/**
	 * This method is used to insert the records in the data base tables based on
	 *
	 * @param lstOidintmvGetSchedules
	 *            List<OidintmvGetSchedules>
	 *
	 * @return List<Integer>
	 *
	 * 
	 */
	public Integer offBlkInsertOidintmvGetSchedules(final List<OffenderInterMvmtLocations> lstOidintmvGetSchedules) {
		final String sql = getQuery("OIDINTMV_OFFBLK_INSERT_OIDINTMV");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffenderInterMvmtLocations oidintmvGetSchedules : lstOidintmvGetSchedules) {
			parameters.add(new BeanPropertySqlParameterSource(oidintmvGetSchedules));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstOidintmvGetSchedules.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstOidintmvGetSchedules
	 *            List<OidintmvGetSchedules>
	 *
	 */


	public Integer offBlkUpdateOidintmvGetSchedules(final OffenderInterMvmtLocations object) {
		final String sql = getQuery("OIDINTMV_OFFBLK_UPDATE_OIDINTMV");
		Integer updateCount = 0;
		try {
			final LocalDate assignmentDate = LocalDate
					.parse(new SimpleDateFormat("yyyy-MM-dd").format(object.getEventDate()));
			String eventDate = assignmentDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));

			updateCount = namedParameterJdbcTemplate.update(sql, createParams("offenderBookId",
					object.getOffenderBookId(), "eventDate", eventDate, "referenceId", object.getReferenceId()));

		} catch (final Exception e) {
			logger.error("UpdateOffenderBeneficiaries", e);
		}
		return updateCount;

	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstOidintmvGetSchedules
	 *            List<OidintmvGetSchedules>
	 *
	 */

	public Integer offBlkUpdateOidintmvGetSchedulesOne(final OffenderInterMvmtLocations object) {
		final String sql = getQuery("OIDINTMV_OFFBLK_UPDATE_OIDINTMV_ONE");
		Integer updateCount = 0;
		try {
			final LocalDate assignmentDate = LocalDate
					.parse(new SimpleDateFormat("yyyy-MM-dd").format(object.getEventDate()));
			String eventDate = assignmentDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));

			updateCount = namedParameterJdbcTemplate.update(sql, createParams("offenderBookId",
					object.getOffenderBookId(), "eventDate", eventDate, "eventId", object.getEventId()));

		} catch (final Exception e) {
			logger.error("UpdateOffenderBeneficiaries", e);
		}
		return updateCount;

	}



	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<AgencyLocations> rgEstablishmentRecordGroup(final String caseLoadId) {
		final String sql = getQuery("OIDINTMV_FIND_RGESTABLISHMENT");
		final RowMapper<AgencyLocations> mRowMapper = Row2BeanRowMapper.makeMapping(sql, AgencyLocations.class,
				mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams("caseLoadId", caseLoadId), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<LivingUnits> rgFromHlocLevelOneRecordGroup(final String agyLocId) {
		final String sql = getQuery("OIDINTMV_FIND_RGFROMHLOCLEVEL1");
		final RowMapper<LivingUnits> mRowMapper = Row2BeanRowMapper.makeMapping(sql, LivingUnits.class, mMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams("agyLocId", agyLocId), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<LivingUnits> rgFromHlocLevelTwoRecordGroup(final String agyLocId, final String fromLocLevelOne) {
		final String sql = getQuery("OIDINTMV_FIND_RGFROMHLOCLEVEL2");
		final RowMapper<LivingUnits> mRowMapper = Row2BeanRowMapper.makeMapping(sql, LivingUnits.class, mMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams("agyLocId", agyLocId, "fromLocLevelOne",
					fromLocLevelOne != null && !fromLocLevelOne.equals("") ? new BigDecimal(fromLocLevelOne) : null),
					mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<LivingUnits> rgFromHlocLevelThreeRecordGroup(final String agyLocId, final String fromLocLevelTwo) {
		final String sql = getQuery("OIDINTMV_FIND_RGFROMHLOCLEVEL3");
		final RowMapper<LivingUnits> mRowMapper = Row2BeanRowMapper.makeMapping(sql, LivingUnits.class, mMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams("agyLocId", agyLocId, "fromLocLevelTwo",
					fromLocLevelTwo != null && !fromLocLevelTwo.equals("") ? new BigDecimal(fromLocLevelTwo) : null),
					mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<VIntLocSummaries> rgFromIlocLevelOneRecordGroup(final String agyLocId) {
		final String sql = getQuery("OIDINTMV_FIND_RGFROMILOCLEVEL1");
		final RowMapper<VIntLocSummaries> mRowMapper = Row2BeanRowMapper.makeMapping(sql, VIntLocSummaries.class,
				mMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams("agyLocId", agyLocId), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<VIntLocSummaries> rgFromIlocLevelTwoRecordGroup(final String agyLocId,
			final String fromILocLevelOneId) {
		final String sql = getQuery("OIDINTMV_FIND_RGFROMILOCLEVEL2");
		final RowMapper<VIntLocSummaries> mRowMapper = Row2BeanRowMapper.makeMapping(sql, VIntLocSummaries.class,
				mMapping);
		try {
			return namedParameterJdbcTemplate.query(sql,
					createParams("agyLocId", agyLocId, "fromILocLevelOneId",
							fromILocLevelOneId != null && !fromILocLevelOneId.equals("")
									? new BigDecimal(fromILocLevelOneId)
									: null),
					mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<VIntLocSummaries> rgFromIlocLevelThreeRecordGroup(final String agyLocId,
			final String fromILocLevelTwoId) {
		final String sql = getQuery("OIDINTMV_FIND_RGFROMILOCLEVEL3");
		final RowMapper<VIntLocSummaries> mRowMapper = Row2BeanRowMapper.makeMapping(sql, VIntLocSummaries.class,
				mMapping);
		try {
			return namedParameterJdbcTemplate.query(sql,
					createParams("agyLocId", agyLocId, "fromILocLevelTwoId",
							fromILocLevelTwoId != null && !fromILocLevelTwoId.equals("")
									? new BigDecimal(fromILocLevelTwoId)
									: null),
					mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<VIntLocSummaries> rgToIlocLevelOneRecordGroup(final String agyLocId, final String fromILocLevelOneId,
			final String fromHLocLevelOne) {
		final String sql = getQuery("OIDINTMV_FIND_RGTOILOCLEVEL1");
		final RowMapper<VIntLocSummaries> mRowMapper = Row2BeanRowMapper.makeMapping(sql, VIntLocSummaries.class,
				mMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams("agyLocId", agyLocId, "fromHLocLevelOne",
					fromHLocLevelOne != null && !fromHLocLevelOne.equals("") ? new BigDecimal(fromHLocLevelOne) : null,
					"fromILocLevelOneId",
					fromILocLevelOneId != null && !fromILocLevelOneId.equals("") ? new BigDecimal(fromILocLevelOneId)
							: null),
					mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<VIntLocSummaries> rgToIlocLevelTwoRecordGroup(final String agyLocId, final String toILocLevelOneId) {
		final String sql = getQuery("OIDINTMV_FIND_RGTOILOCLEVEL2ONE");
		final RowMapper<VIntLocSummaries> mRowMapper = Row2BeanRowMapper.makeMapping(sql, VIntLocSummaries.class,
				mMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams("agyLocId", agyLocId, "toILocLevelOneId",
					toILocLevelOneId != null && !toILocLevelOneId.equals("") ? new BigDecimal(toILocLevelOneId) : null),
					mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<VIntLocSummaries> rgToIlocLevelThreeRecordGroup(final String agyLocId, final String toILocLevelTwoId) {
		final String sql = getQuery("OIDINTMV_FIND_RGTOILOCLEVEL3ONE");
		final RowMapper<VIntLocSummaries> mRowMapper = Row2BeanRowMapper.makeMapping(sql, VIntLocSummaries.class,
				mMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams("agyLocId", agyLocId, "toILocLevelTwoId",
					toILocLevelTwoId != null && !toILocLevelTwoId.equals("") ? new BigDecimal(toILocLevelTwoId) : null),
					mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<ReferenceCodes> rgSchTypeRecordGroup() {
		final String sql = getQuery("OIDINTMV_FIND_RGSCHTYPE");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<ReferenceCodes> rgSchReasonRecordGroup() {
		final String sql = getQuery("OIDINTMV_FIND_RGSCHREASON");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * getProfileValue
	 *
	 * @param params
	 *
	 */
	public SystemProfiles getProfileValue(final SystemProfiles paramBean) {
		SystemProfiles returnObj;
		final String sql = getQuery("OIDINTMV_GET_PROFILE_VALUE_2");
		final RowMapper<SystemProfiles> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, SystemProfiles.class,
				systemProfilesMapping);
		returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * getOffenderDetails
	 *
	 * @param params
	 *
	 */
	public VHeaderBlock getOffenderDetails(final VHeaderBlock paramBean) {
		VHeaderBlock returnObj = new VHeaderBlock();
		final String sql = getQuery("OIDINTMV_GET_OFFENDER_FULL_DETAILS");
		final RowMapper<VHeaderBlock> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, VHeaderBlock.class,
				agencyInternalLocationsMapping);
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("offenderIdDisplay", paramBean.getOffenderIdDisplay(), "agyLocId",
							paramBean.getAgyLocId(), "caseLoadId", paramBean.getCaseLoadId()),
					columnRowMapper);
		} catch (Exception e) {
			returnObj = new VHeaderBlock();
		}
		return returnObj;
	}

	public List<VOffenderAllSchedules> getSchedules(final VOffenderAllSchedules objSearchDao) {
		final String sql = getQuery("OIDINTMV_GET_SCHEDULES");
		String preparedSql = null;
		final StringBuffer sqlQuery = new StringBuffer();
		final MapSqlParameterSource valuesList = new MapSqlParameterSource();
		sqlQuery.append(sql);
		if (objSearchDao != null) {
			sqlQuery.append(" where ");
			sqlQuery.append("vOffSch.agy_loc_id = :agyLocId   AND vOffSch.active_flag = 'Y'  "
					+ " AND vOffSch.event_status = 'SCH' "
					+ " AND vOffSch.booking_active_flag = 'Y'   AND vOffSch.in_out_status = 'IN' "
					+ " AND vOffSch.living_unit_id = agyIntLoc.internal_location_id "
					+ " AND agyIntLoc.agy_loc_id = :agyLocId " + " AND vOffSch.event_date = :eventDate " + " and ");
			valuesList.addValue("agyLocId", objSearchDao.getAgyLocId());
			valuesList.addValue("eventDate", objSearchDao.getEventDate());
			if (objSearchDao.getStartTime() != null) {
				sqlQuery.append("to_char(START_TIME,'HH24:MI') >= to_char(:startTime,'HH24:MI')" + " and ");
				valuesList.addValue("startTime", objSearchDao.getStartTime());
			}
			if (objSearchDao.getEndTime() != null) {
				sqlQuery.append("to_char(START_TIME,'HH24:MI') <= to_char(:endTime,'HH24:MI')" + " and ");
				valuesList.addValue("endTime", objSearchDao.getEndTime());
			}

			if (objSearchDao.getLuLevel3Code() != null) {
				sqlQuery.append("LIVING_UNIT_ID = :livingUnitId" + " and ");
				valuesList.addValue("livingUnitId", objSearchDao.getLuLevel3Code());
			} else if (objSearchDao.getLuLevel2Code() != null) {
				sqlQuery.append("LIVING_UNIT_DESC like :livingUnitDesc " + " and ");
				valuesList.addValue("livingUnitDesc",
						"%" + getHousingLocationDesc(objSearchDao.getLuLevel2Code()) + "%");
			} else if (objSearchDao.getLuLevel1Code() != null) {
				sqlQuery.append("LIVING_UNIT_DESC like :livingUnitDesc " + " and ");
				valuesList.addValue("livingUnitDesc",
						"%" + getHousingLocationDesc(objSearchDao.getLuLevel1Code()) + "%");
			}

			if (objSearchDao.getAgencyImlLevel1Code() != null) {
				if (!(objSearchDao.getAgencyImlLevel1Code()).equals("")) {
					sqlQuery.append("AGENCY_IML_DESC like :agyImlIdDesc" + " and ");
					valuesList.addValue("agyImlIdDesc",
							"%" + getInternalLocationDesc(objSearchDao.getAgencyImlLevel1Code()) + "%");
				}
			}
			if (objSearchDao.getAgencyImlLevel2Code() != null) {
				sqlQuery.append("AGENCY_IML_DESC like :agyImlIdDesc" + " and ");
				valuesList.addValue("agyImlIdDesc",
						"%" + getInternalLocationDesc(objSearchDao.getAgencyImlLevel2Code()) + "%");
			}
			if (objSearchDao.getAgencyImlLevel3Code() != null) {
				sqlQuery.append("AGENCY_IML_DESC like :agyImlIdDesc" + " and ");
				valuesList.addValue("agyImlIdDesc",
						"%" + getInternalLocationDesc(objSearchDao.getAgencyImlLevel3Code()) + "%");
			}
			if (objSearchDao.getToIntLocLevel1Code() != null) {
				sqlQuery.append(" TO_INT_LOC_LEVEL_1_CODE like :toIntLocDesc" + " and ");
				valuesList.addValue("toIntLocDesc",
						"%" + getInternalLocationDesc(objSearchDao.getToIntLocLevel1Code()) + "%");
			}
			if (objSearchDao.getToIntLocLevel2Code() != null) {
				sqlQuery.append("TO_INT_LOC_LEVEL_2_CODE like :toIntLocDesc" + " and ");
				valuesList.addValue("toIntLocDesc",
						"%" + getInternalLocationDesc(objSearchDao.getToIntLocLevel2Code()) + "%");
			}
			if (objSearchDao.getToIntLocLevel3Code() != null) {
				sqlQuery.append("TO_INT_LOC_LEVEL_3_CODE like :toIntLocDesc" + " and ");
				valuesList.addValue("toIntLocDesc",
						"%" + getInternalLocationDesc(objSearchDao.getToIntLocLevel3Code()) + "%");
			}
		
			
		}
		preparedSql = sqlQuery.toString().trim();
		if (preparedSql.endsWith("where")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 5);
		}
		if (preparedSql.endsWith("and")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 3);
		}
		preparedSql = preparedSql + " ) a where (a.courtEventApp = 'Y'and  exists (select * from court_events ce, reference_codes rc  where "
				+ "ce.event_id =a.event_id and	rc.\"domain\" = 'CRT_APP_TYPE' and rc.code = ce.appearance_type  and rc.parent_code = 'INT') ) or a.courtEventApp = 'N' ";
		preparedSql = preparedSql + " order by  offender_last_name, offender_first_name ";
		final RowMapper<VOffenderAllSchedules> VOffenderAllSchedulesRowMapper = Row2BeanRowMapper
				.makeMapping(preparedSql, VOffenderAllSchedules.class, vOffSchMapping);
		List<VOffenderAllSchedules> returnList = new ArrayList<>();
		returnList = namedParameterJdbcTemplate.query(preparedSql, valuesList, VOffenderAllSchedulesRowMapper);
		return returnList;
	}

	public List<VOffenderBookings> getUnSchedules(final VOffenderAllSchedules objSearchDao) {
		final String sql = getQuery("OIDINTMV_GET_UNSCHEDULES");
		final RowMapper<VOffenderBookings> VOffenderAllSchedulesRowMapper = Row2BeanRowMapper.makeMapping(sql,
				VOffenderBookings.class, vOffSchMapping);
		List<VOffenderBookings> returnList = new ArrayList<>();
		final StringBuffer sqlQuery = new StringBuffer();
		final MapSqlParameterSource valuesList = new MapSqlParameterSource();
		sqlQuery.append(sql);
		String preparedSql = null;
		if (objSearchDao != null) {
			sqlQuery.append(" where ");
			sqlQuery.append(" AGY_LOC_ID = :P_AGY_LOC_ID and ACTIVE_FLAG = 'Y' and IN_OUT_STATUS = 'IN' and");
			valuesList.addValue("P_AGY_LOC_ID", objSearchDao.getAgyLocId());

			if (objSearchDao.getLuLevel1Code() != null || objSearchDao.getLuLevel2Code() != null
					|| objSearchDao.getLuLevel3Code() != null || objSearchDao.getLuLevel4Code() != null) {
				objSearchDao.setLivingUnitId((objSearchDao.getLuLevel4Code() != null)
						? new BigDecimal(objSearchDao.getLuLevel4Code())
						: (objSearchDao.getLuLevel3Code() != null) ? new BigDecimal(objSearchDao.getLuLevel3Code())
								: (objSearchDao.getLuLevel2Code() != null)
										? new BigDecimal(objSearchDao.getLuLevel2Code())
										: (objSearchDao.getLuLevel1Code() != null)
												? new BigDecimal(objSearchDao.getLuLevel1Code())
												: null);
			}

			if (objSearchDao.getLivingUnitId() != null) {
				sqlQuery.append(
						" LIVING_UNIT_ID in ( with recursive cte as ( select lu1.living_unit_id,lu1.parent_living_unit_id from	living_units as lu1 where lu1.living_unit_id = :livingUnitId \r\n"
								+ "union select	lu2.living_unit_id,lu2.parent_living_unit_id from living_units lu2 join cte on	cte.living_unit_id = lu2.parent_living_unit_id )\r\n"
								+ "select cte.living_unit_id  from cte) and");
				valuesList.addValue("livingUnitId", objSearchDao.getLivingUnitId());
			}

			if (objSearchDao.getAgencyImlId() != null) {
				sqlQuery.append(
						" (AGENCY_IML_ID in ( select internal_location_id from INT_LOC_USAGE_LOCATIONS where usage_location_id in (with recursive cte as \r\n"
								+ "( select lu1.usage_location_id,lu1.parent_usage_location_id from INT_LOC_USAGE_LOCATIONS as lu1 where \r\n"
								+ "lu1.internal_location_id = :agencyImlId::bigint union select lu2.usage_location_id,lu2.parent_usage_location_id \r\n"
								+ "from INT_LOC_USAGE_LOCATIONS lu2 join cte on cte.usage_location_id = lu2.parent_usage_location_id ) select cte.usage_location_id from cte) ))  and");
				valuesList.addValue("agencyImlId", objSearchDao.getAgencyImlId());
			}

		}
		preparedSql = sqlQuery.toString().trim();
		if (preparedSql.endsWith("where")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 5);
		}
		if (preparedSql.endsWith("and")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 3);
		}
		preparedSql = preparedSql + " order by LAST_NAME, FIRST_NAME ";
		returnList = namedParameterJdbcTemplate.query(preparedSql, valuesList, VOffenderAllSchedulesRowMapper);
		return returnList;
	}

	public String getHousingLocationDesc(final String livingUnitId) {
		final String sql = getQuery("OIDINTMV_LIVING_UNIT_ID_DESC");
		String returnList = null;
		LivingUnits livingUnitIdList = new LivingUnits();
		final RowMapper<LivingUnits> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, LivingUnits.class, mMapping);
		livingUnitIdList = namedParameterJdbcTemplate.queryForObject(sql, createParams("livingUnitId", livingUnitId),
				columnRowMapper);
		if (livingUnitIdList != null) {
			if (livingUnitIdList.getLevel3Code() != null) {
				returnList = livingUnitIdList.getLevel1Code() + "-" + livingUnitIdList.getLevel2Code() + "-"
						+ livingUnitIdList.getLevel3Code();
			} else if (livingUnitIdList.getLevel2Code() != null) {
				returnList = livingUnitIdList.getLevel1Code() + "-" + livingUnitIdList.getLevel2Code();
			} else if (livingUnitIdList.getLevel1Code() != null) {
				returnList = livingUnitIdList.getLevel1Code();
			}
		}
		return returnList;
	}

	public String getInternalLocationDesc(final String internalLocationId) {
		final String sql = getQuery("OIDINTMV_INTERNAL_LOCATION_DESC");
		String returnList = null;
		returnList = namedParameterJdbcTemplate.queryForObject(sql,
				createParams("internalLocationId", internalLocationId), String.class);
		return returnList;
	}

	public BigDecimal getEventIdCur(final VOffenderAllSchedules vAllBean) {
		final String sql = getQuery("OIDINTMV_OFFBLK_GET_EVENTID");
		BigDecimal returnList = null;
		try {
			returnList = namedParameterJdbcTemplate
					.queryForObject(sql,
							createParams("eventDate", vAllBean.getEventDate(), "offenderBookId",
									vAllBean.getOffenderBookId(), "referenceId", vAllBean.getReferenceId()),
							BigDecimal.class);
		} catch (Exception e) {
			returnList = null;
		}
		return returnList;
	}

	public Long getNxtOffenderIml() {
		final String sql = getQuery("OIDINTMV_OFFBLK_GET_NXT_OFFENDER_IML_ID");
		Long returnList = null;
		returnList = namedParameterJdbcTemplate.queryForObject(sql, createParams(), Long.class);
		return returnList;
	}

	public LivingUnits getLabels(final String agyLocId) {
		final LivingUnits bean = new LivingUnits();
		Map<String, Object> returnObject = null;
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[20];
		sqlParameters = new SqlParameter[] { new SqlParameter("P_AGY_LOC_ID", OracleTypes.VARCHAR),
				new SqlOutParameter("P_LEVEL_1_CODE", OracleTypes.VARCHAR),
				new SqlOutParameter("P_LEVEL_2_CODE", OracleTypes.VARCHAR),
				new SqlOutParameter("P_LEVEL_3_CODE", OracleTypes.VARCHAR),
				new SqlOutParameter("P_LEVEL_4_CODE", OracleTypes.VARCHAR) };
		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("TAG_ESTABLISHMENT").withProcedureName("GET_HOUSING_LABELS")
				.declareParameters(sqlParameters);
		inParamMap.put("P_AGY_LOC_ID", agyLocId);
		final MapSqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		try {
			returnObject = simpleJDBCCall.execute(inParameter);
			bean.setLevel1Code(returnObject.get("P_LEVEL_1_CODE").toString());
			bean.setLevel2Code(returnObject.get("P_LEVEL_2_CODE").toString());
			bean.setLevel3Code(returnObject.get("P_LEVEL_3_CODE").toString());
			bean.setLevel4Code(returnObject.get("P_LEVEL_4_CODE").toString());
		} catch (Exception e) {
			logger.error(" getLabels: ", e);
		}
		return bean;
	}

	public String chkNaIntLocConflict(final VOffenderAllSchedules voffBean) {
		String conflict = null;
		Map<String, Object> returnObject = null;
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[20];
		sqlParameters = new SqlParameter[] { new SqlParameter("P_OFFENDER_ID", OracleTypes.NUMBER),
				new SqlParameter("P_OFFENDER_BOOK_ID", OracleTypes.NUMBER),
				new SqlParameter("P_INTERNAL_LOC_ID", OracleTypes.NUMBER),
				new SqlOutParameter("P_OFF_INFO", OracleTypes.STRUCT, "TAG_CORE.OFF_INFO_TYPE",
						new SqlReturnStruct(OffRec.class)),
				new SqlOutParameter("P_CONFLICT_FLAG", OracleTypes.VARCHAR) };
		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("NON_ASSOCIATION").withProcedureName("CHK_NA_INT_LOC_CONFLICT")
				.declareParameters(sqlParameters);
		inParamMap.put("P_OFFENDER_ID", voffBean.getOffenderId());
		inParamMap.put("P_OFFENDER_BOOK_ID", voffBean.getOffenderBookId());
		inParamMap.put("P_INTERNAL_LOC_ID", voffBean.getToInternalLocationId());
		inParamMap.put("P_CONFLICT_FLAG", "true");
		final MapSqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		try {
			returnObject = simpleJDBCCall.execute(inParameter);
			conflict = returnObject.get("P_CONFLICT_FLAG").toString();
		} catch (Exception e) {
			logger.error("chkNaIntLocConflict: ", e);
			conflict = "N";
		}
		return conflict;
	}

	@Override
	public Integer isOffenderExists(final String offIdDisplay) {
		Integer returnIndex = null;
		Boolean value = null;
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[2];
		sqlParameters = new SqlParameter[] { new SqlParameter("P_OFF_ID_DISPLAY", OracleTypes.VARCHAR),
				new SqlInOutParameter("RETURN_VALUE", OracleTypes.BOOLEAN) };
		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("NON_ASSOCIATION").withFunctionName("IS_OFFENDER_EXISTS")
				.declareParameters(sqlParameters);
		inParamMap.put("P_OFF_ID_DISPLAY", offIdDisplay);
		final SqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		try {
			value = simpleJDBCCall.executeFunction(Boolean.class, inParameter);
		} catch (Exception e) {
			returnIndex = 0;
		}
		return returnIndex;
	}

	public String getInternalLocDesc(final String agencyImlId) {
		final String sql = getQuery("OIDINTMV_GET_OFFENDER_DETAILS");
		String returnList = null;
		try {
			returnList = namedParameterJdbcTemplate.queryForObject(sql, createParams("agyImlId", agencyImlId),
					String.class);
		} catch (Exception e) {
			returnList = "";
		}
		return returnList;
	}

	public String getNsType(final BigDecimal agencyImlId, BigDecimal offenderIdOne) {
		final String sql = getQuery("OIDINTMV_GET_OFFENDER_NSTYPE_DETAILS");
		String returnList = null;
		try {
			returnList = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("offenderId", agencyImlId, "offenderIdOne", offenderIdOne), String.class);
		} catch (Exception e) {
			returnList = "";
		}
		return returnList;
	}

	@Override
	public List<BigDecimal> getparentLocationCurserList(BigDecimal valueOf) {
		final String sql = getQuery("OIDINTMV_GET_PARENT_INTERNAL_LOCATION_DETAILS");
		List<BigDecimal> resultList = new ArrayList<>();
		try {
			resultList = namedParameterJdbcTemplate.queryForList(sql, createParams("internalLocationId", valueOf),
					BigDecimal.class);
		} catch (Exception e) {
			logger.error("OTDCRVOI_VERIFY_TXN_TYPE_COUNT :", e);
		}
		return resultList;
	}

	@Override
	public List<OffenderInterMvmtLocations> getRestricationCusrerList(BigDecimal boolean1, String string) {
		final String sql = getQuery("OIDINTMV_GET_RESTRICT_INTERNAL_LOCATION_DETAILS");
		final RowMapper<OffenderInterMvmtLocations> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderInterMvmtLocations.class, agencyInternalLocationsMapping);
		List<OffenderInterMvmtLocations> returnList = new ArrayList<>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql,
					createParams("internalLocationId", boolean1, "pstgIndicator", string), columnRowMapper);
		} catch (Exception e) {
			logger.error("senCalcOnCheckDeleteMaster", e);
		}
		return returnList;
	}

	public String getStgType(final BigDecimal offenderBookId, final BigDecimal offenderBookIdOne) {
		final String sql = getQuery("OIDINTMV_GET_OFFENDER_STG_DETAILS");
		String returnList = null;
		try {
			returnList = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("offenderBookId", offenderBookId, "offenderBookIdOne", offenderBookIdOne),
					String.class);
		} catch (Exception e) {
			returnList = "";
		}
		return returnList;
	}

	@Override
	public List<Offenders> getOffDetails(final BigDecimal obj) {
		final String sql = getQuery("OIDINTMV_CKECK_CONFLICT_GET_OFF_DETAILS");
		final RowMapper<Offenders> mRowMapper = Row2BeanRowMapper.makeMapping(sql, Offenders.class, offeProgProfMap);
		return namedParameterJdbcTemplate.query(sql, createParams("offenderId", obj), mRowMapper);

	}

	@Override
	public List<BigDecimal> getparentLocationSchedList(BigDecimal toInternalLocationId, String pStgIndecator) {
		final String sql = getQuery("OIDINTMV_GET_PARENTLOCATIONSERVER");
		List<BigDecimal> resultList = new ArrayList<>();
		try {
			resultList = namedParameterJdbcTemplate.queryForList(sql,
					createParams("internalLocationId", toInternalLocationId, "pStgIndecator", pStgIndecator),
					BigDecimal.class);
		} catch (Exception e) {
			logger.error("OTDCRVOI_VERIFY_TXN_TYPE_COUNT :", e);
		}
		return resultList;
	}

	@Override
	public List<OffenderInterMvmtLocations> getParentLocScheduleCusrerList(BigDecimal internalLocationId,
			String pStgIndecator) {
		final String sql = getQuery("OIDINTMV_GET_PARENT_LOCATIONSTG_LIST");
		final RowMapper<OffenderInterMvmtLocations> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderInterMvmtLocations.class, agencyInternalLocationsMapping);
		List<OffenderInterMvmtLocations> returnList = new ArrayList<>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql,
					createParams("internalLocationId", internalLocationId, "pStgIndecator", pStgIndecator),
					columnRowMapper);
		} catch (Exception e) {
			logger.error("senCalcOnCheckDeleteMaster", e);
		}
		return returnList;
	}

	@Override
	public List<Offenders> getOffDetailsNaIntLocList(BigDecimal internalLocationId, String intLocProfileCode,
			BigDecimal offenderId) {
		final String sql = getQuery("OIDINTMV_GET_PROFILECODE_INTLOC_OFFDER_DETAILS");
		final RowMapper<Offenders> mRowMapper = Row2BeanRowMapper.makeMapping(sql, Offenders.class, offeProgProfMap);
		return namedParameterJdbcTemplate.query(sql, createParams("internalLocationId", internalLocationId,
				"intLocProfileCode", intLocProfileCode, "offenderId", offenderId), mRowMapper);

	}

	@Override
	public List<Offenders> getOffDetailsStgIntLocList(BigDecimal internalLocationId, String intLocProfileCode,
			BigDecimal offenderId) {
		final String sql = getQuery("OIDINTMV_GET_PROFILECODE_INTLOC_STG_OFFDER_DETAILS");
		final RowMapper<Offenders> mRowMapper = Row2BeanRowMapper.makeMapping(sql, Offenders.class, offeProgProfMap);
		return namedParameterJdbcTemplate.query(sql, createParams("internalLocationId", internalLocationId,
				"internalLocationProfileCode", intLocProfileCode, "offenderBookId", offenderId), mRowMapper);

	}
	
	@Override
	public ReferenceCodes courtEventsLocation(String caseloadId, String locationCode) {
		final String sql = getQuery("OIDINTMV_COURT_LOCATIONS");
		ReferenceCodes bean = new ReferenceCodes();

		try {
			bean = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("AGY_LOC_ID", caseloadId, "locationCode", locationCode),
					new BeanPropertyRowMapper<ReferenceCodes>(ReferenceCodes.class));

		} catch (Exception e) {
			bean = null;
			logger.error(e);
		}

		return bean;
	}

}

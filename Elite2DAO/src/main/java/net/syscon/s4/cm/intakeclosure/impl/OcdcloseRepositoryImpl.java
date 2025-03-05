package net.syscon.s4.cm.intakeclosure.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.cm.intakeclosure.OcdcloseRepository;
import net.syscon.s4.common.beans.CaseloadAgencyLocations;
import net.syscon.s4.common.beans.OffenderBookingEvent;
import net.syscon.s4.common.beans.OffenderBookings;
import net.syscon.s4.common.beans.OffenderCommunityFile;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.VHeaderBlock;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.CasePlans;
import net.syscon.s4.legalorders.OffenderCommunityFiles;
import oracle.jdbc.OracleTypes;

/**
 * Class OcdcloseRepositoryImpl
 */
@Repository
public class OcdcloseRepositoryImpl extends RepositoryBase implements OcdcloseRepository {

	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OcdcloseRepositoryImpl.class.getName());
	
private final Map<String, FieldMapper> referenceCodesMapping = new ImmutableMap.Builder<String, FieldMapper>()
.put("LIST_SEQ", 					new FieldMapper("listSeq"))
.put("CODE", 						new FieldMapper("code"))
.put("DESCRIPTION", 				new FieldMapper("description"))
.put("DSP_DESCRIPTION", 			new FieldMapper("dspDescription"))
.put("MODE", 						new FieldMapper("mode"))
.put("REASON_CODE", 				new FieldMapper("reasonCode"))
.build();
private final Map<String, FieldMapper> offenderBookingEventsMapping = new ImmutableMap.Builder<String, FieldMapper>()
.put("0", 										new FieldMapper("0"))
.put("NVL(MAX(EVENT_SEQ)", 						new FieldMapper(" nvl(max(eventSeq)"))
.put("OFFENDER_BOOK_ID", 						new FieldMapper("offenderBookId"))
.put("EVENT_DATE", 								new FieldMapper("eventDate"))
.put("EVENT_TIME", 								new FieldMapper("eventTime"))
.build();
private final Map<String, FieldMapper> mMapping = new ImmutableMap.Builder<String, FieldMapper>()
.put("LIST_SEQ", 						new FieldMapper("listSeq"))
.put("DESCRIPTION", 					new FieldMapper("description"))
.build();
private final Map<String, FieldMapper> agencyLocationsMapping = new ImmutableMap.Builder<String, FieldMapper>()
.put("AGY_LOC_ID", 						new FieldMapper("agyLocId"))
.put("LIST_SEQ", 						new FieldMapper("listSeq"))
.put("CASELOAD_ID", 					new FieldMapper("caseloadId"))
.put("DSP_DESCRIPTION2", 				new FieldMapper("dspDescription2"))
.build();
private final Map<String, FieldMapper> caseplanMapping = new ImmutableMap.Builder<String, FieldMapper>()
.put("CREATION_USER", 						new FieldMapper("creationUser"))
.put("AGY_LOC_ID", 							new FieldMapper("agyLocId"))
.put("SAC_STAFF_I", 						new FieldMapper("sacStaffI"))
.put("AUTO_ASSESS_MODIFY_DATETIME", 		new FieldMapper("autoAssessModifyDatetime"))
.put("CAL_AGY_LOC_ID", 						new FieldMapper("calAgyLocId"))
.put("PROFILE_VALUE", 						new FieldMapper("profileValue"))
.put("INST_POSITION", 						new FieldMapper("instPosition"))
.put("INST_SAC_STAFF_ID", 					new FieldMapper("instSacStaffId"))
.put("CREATION_DATE", 						new FieldMapper("creationDate"))
.put("CASE_PLAN_STATUS", 					new FieldMapper("casePlanStatus"))
.put("OFFENDER_BOOK_ID", 					new FieldMapper("offenderBookId"))
.put("CASE_PLAN_ID", 						new FieldMapper("casePlanId"))
.put("SUPERVISION_LEVEL", 					new FieldMapper("supervisionLevel"))
.put("START_DATE", 							new FieldMapper("startDate"))
.put("CASELOAD_TYPE", 						new FieldMapper("caseloadType"))
.put("VERIFIED_FLAG", 						new FieldMapper("verifiedFlag"))
.put("INST_CAL_AGY_LOC_ID", 				new FieldMapper("instCalAgyLocId"))
.put("inst_from_date", 						new FieldMapper("instFromDate"))
.put("inst_role", 							new FieldMapper("instRole"))
.build();

	/**
	 * Creates new OcdcloseRepositoryImpl class Object
	 */
	public OcdcloseRepositoryImpl() {
		// OcdcloseRepositoryImpl
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            OffenderBookingEvent
	 *
	 * @return List<OffenderBookingEvent>
	 *
	 * @throws SQLException
	 */
	public List<OffenderBookingEvent> obeExecuteQuery(final OffenderBookingEvent objSearchDao) {
		final String sql = getQuery("OCDCLOSE_OBE_FIND_OFFENDER_BOOKING_EVENTS");
		final RowMapper<OffenderBookingEvent> offbkgeRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderBookingEvent.class, offenderBookingEventsMapping);
		return namedParameterJdbcTemplate.query(sql, createParams(), offbkgeRowMapper);
	}

	/**
	 * This method is used to insert the records in the data base tables based
	 * on
	 *
	 * @param lstOffenderBookingEvent
	 *            List<OffenderBookingEvent>
	 *
	 * @return List<Integer>
	 *
	 * @throws SQLException
	 */
	public Integer obeInsertOffenderBookingEvents(final List<OffenderBookingEvent> object) {
		final String sql = getQuery("OCDCLOSE_OBE_INSERT_OFFENDER_BOOKING_EVENTS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffenderBookingEvent obj : object) {
			parameters.add(new BeanPropertySqlParameterSource(obj));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));

		if (object.size() == returnArray.length) {
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
	public List<ReferenceCodes> cgfkObeDspDescriptionRecordGroup() {
		final String sql = getQuery("OCDCLOSE_FIND_CGFKOBEDSPDESCRIPTION");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("Exception : cgfkObeDspDescriptionRecordGroup:", e);
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<MM>
	 */
	public List<AgencyLocations> agencyLocationRecordGroup(final Integer offenderBookId, final String caseloadId) {
		final String sql = getQuery("OCDCLOSE_FIND_AGENCYLOCATIONRECORDGROUP");
		final RowMapper<AgencyLocations> mMRowMapper = Row2BeanRowMapper.makeMapping(sql, AgencyLocations.class,
				agencyLocationsMapping);

		try {
			return namedParameterJdbcTemplate.query(sql,
					createParams("OFFENDERBOOKID", offenderBookId, "CASELOADID", caseloadId), mMRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("Exception : agencyLocationRecordGroup:", e);
			return Collections.emptyList();
		}
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * obePreInsert
	 *
	 * @param params
	 *
	 */
	public Integer obePreInsert(final OffenderBookingEvent paramBean) {
		final String sql = getQuery("OCDCLOSE_OBE_PREINSERT");
		return namedParameterJdbcTemplate.queryForObject(sql,
				createParams("OFFENDERBOOKID", paramBean.getOffenderBookId()), Integer.class);
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfklkpObeObeToAgyLocF1
	 *
	 * @param params
	 *
	 */
	public List<AgencyLocations> cgfklkpObeObeToAgyLocF1(final AgencyLocations paramBean) {
		final String sql = getQuery("OCDCLOSE_CGFKLKP_OBE_OBE_TO_AGY_LOC_F1");
		final RowMapper<AgencyLocations> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, AgencyLocations.class,
				agencyLocationsMapping);
		return namedParameterJdbcTemplate.query(sql, createParams(), columnRowMapper);
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfkchkObeObeRefCodeF2
	 *
	 * @param params
	 *
	 */
	public List<ReferenceCodes> cgfkchkObeObeRefCodeF2(final ReferenceCodes paramBean) {
		final String sql = getQuery("OCDCLOSE_CGFKCHK_OBE_OBE_REF_CODE_F2");
		final RowMapper<ReferenceCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				referenceCodesMapping);
		return namedParameterJdbcTemplate.query(sql, createParams(), columnRowMapper);
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfklkpObeObeRefCodeF2
	 *
	 * @param params
	 *
	 */
	public List<ReferenceCodes> cgfklkpObeObeRefCodeF2(final ReferenceCodes paramBean) {
		final String sql = getQuery("OCDCLOSE_CGFKLKP_OBE_OBE_REF_CODE_F2");
		final RowMapper<ReferenceCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				referenceCodesMapping);
		return namedParameterJdbcTemplate.query(sql, createParams(), columnRowMapper);
	}

	/**
	 * This method is used to insert the records in the data base tables based
	 * on
	 *
	 * @param lstOffenderBookingEvent
	 *            List<OffenderBookingEvent>
	 *
	 * @return List<Integer>
	 *
	 * @throws SQLException
	 */
	public Integer checkInstUpdateOffenderBookings(final OffenderBookingEvent listObj) {
		final String sql = getQuery("OCDCLOSE_CHECK_INSTITUTION_OFFENDER_BOOKINGS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		parameters.add(new BeanPropertySqlParameterSource(listObj));
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (returnArray.length == 1) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to insert the records in the data base tables based
	 * on
	 *
	 * @param lstOffenderBookingEvent
	 *            List<OffenderBookingEvent>
	 *
	 * @return List<Integer>
	 *
	 * @throws SQLException
	 */
	public Integer checkInstUpdateOffenderBookingAgyLoc(final OffenderBookingEvent listObj) {
		final String sql = getQuery("OCDCLOSE_CHECK_INSTITUTION_OFFENDER_BOOKING_AGY_LOCS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		parameters.add(new BeanPropertySqlParameterSource(listObj));
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (returnArray.length == 1) {
			return 1;
		} else {
			return 0;
		}
	}

	/**
	 * This method is used to insert the records in the data base tables based
	 * on
	 *
	 * @param lstOffenderBookingEvent
	 *            List<OffenderBookingEvent>
	 *
	 * @return List<Integer>
	 *
	 * @throws SQLException
	 */
	public Integer checkMultyCsldUpdateOffenderBookings(final OffenderBookingEvent listObj) {
		final String sql = getQuery("OCDCLOSE_CHECK_MULTY_CASELOAD_OFFENDER_BOOKINGS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		parameters.add(new BeanPropertySqlParameterSource(listObj));
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (returnArray.length == 1) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to insert the records in the data base tables based
	 * on
	 *
	 * @param lstOffenderBookingEvent
	 *            List<OffenderBookingEvent>
	 *
	 * @return List<Integer>
	 *
	 * @throws SQLException
	 */
	public Integer checkMultyCsldUpdateOffenderBookingAgyLoc(final OffenderBookingEvent listObj) {
		final String sql = getQuery("OCDCLOSE_CHECK_MULTY_CASELOAD_OFFENDER_BOOKING_AGY_LOCS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		parameters.add(new BeanPropertySqlParameterSource(listObj));
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (returnArray.length == 1) {
			return 1;
		} else {
			return 0;
		}
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @return Date
	 * @Param paramBean
	 */
	@Override
	public OffenderBookingEvent getBokingBeginDate(final VHeaderBlock paramBean) {
		OffenderBookingEvent returnObj = new OffenderBookingEvent();
		final String sql = getQuery("OCDCLOSE_GET_BOOKING_BEGIN_DATE");
		final RowMapper<OffenderBookingEvent> OffBkgeRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderBookingEvent.class, offenderBookingEventsMapping);
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("offenderBookId", paramBean.getOffenderBookId()), OffBkgeRowMapper);
		} catch (EmptyResultDataAccessException e) {
			returnObj = new OffenderBookingEvent();
		}
		return returnObj;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @return Date
	 * @Param paramBean
	 */
	@Override
	public Integer checkExternalTransfer(final OffenderBookingEvent paramBean) {
		Integer returnVal = 0;
		final String sql = getQuery("OCDCLOSE_CHECK_EXTERNAL_TRANSFER");
		try {
			returnVal = namedParameterJdbcTemplate.queryForObject(sql, createParams("offenderBookId",
					paramBean.getOffenderBookId(), "toAgyLocId", paramBean.getToAgyLocId()), Integer.class);
		} catch (EmptyResultDataAccessException e) {
			returnVal = 0;
		}
		return returnVal;
	}

	/**
	 * This method is used to insert the records in the data base tables based
	 * on
	 *
	 * @param lstOffenderBookingEvent
	 *            List<OffenderBookingEvent>
	 *
	 * @return List<Integer>
	 *
	 * @throws SQLException
	 */
	public Integer updateOffenderBookings(final OffenderBookingEvent listObj) {
		final String sql = getQuery("OCDCLOSE_UPDATE_OFFENDER_BOOKINGS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		parameters.add(new BeanPropertySqlParameterSource(listObj));
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (returnArray.length == 1) {
			return 1;
		} else {
			return 0;
		}
	}

	/**
	 * This method is used to insert the records in the data base tables based
	 * on
	 *
	 * @param lstOffenderBookingEvent
	 *            List<OffenderBookingEvent>
	 *
	 * @return List<Integer>
	 *
	 * @throws SQLException
	 */
	public Integer updateOffenderBookingsy(final OffenderBookingEvent listObj) {
		final String sql = getQuery("OCDCLOSE_UPDATE_OFFENDER_BOOKINGS_Y");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		parameters.add(new BeanPropertySqlParameterSource(listObj));
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (returnArray.length == 1) {
			return 1;
		} else {
			return 0;
		}
	}

	/**
	 * This method is used to insert the records in the data base tables based
	 * on
	 *
	 * @param lstOffenderBookingEvent
	 *            List<OffenderBookingEvent>
	 *
	 * @return List<Integer>
	 *
	 * @throws SQLException
	 */
	public Integer updateOffenderComm(final String vno, final Long offenderId) {
		Integer updateCount = 0;
		final String sql = getQuery("OCDCLOSE_UPDATE_OFFENDER_COMM");
		try {
			updateCount = namedParameterJdbcTemplate.update(sql, createParams("vno", vno, "offenderId", offenderId));
		} catch (Exception e) {
			logger.error("Exception : updateOffenderComm:", e);
			updateCount = 0;
		}

		return updateCount;
	}

	/**
	 * This method is execute a Function when trigger event is called
	 * 
	 * omsMovementsCheckActiveCases
	 *
	 * @param searchdao
	 *
	 */
	public String tagTerminationChkTasks(final OffenderBookingEvent searchdao) {
		String value = "";
		try {
			final Map<String, Object> inParamMap = new HashMap<String, Object>();
			SqlParameter[] sqlParameters = new SqlParameter[2];
			sqlParameters = new SqlParameter[] { new SqlParameter("P_OFFENDER_BOOK_ID", OracleTypes.NUMBER),
					new SqlOutParameter("RETURN_VALUE", OracleTypes.VARCHAR) };
			SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
					.withCatalogName("TAG_TERMINATION").withFunctionName("CHK_TASKS").declareParameters(sqlParameters);
			inParamMap.put("P_OFFENDER_BOOK_ID", searchdao.getOffenderBookId());
			SqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
			value = simpleJDBCCall.executeFunction(String.class, inParameter);
		} catch (Exception e) {
			logger.error("Exception : tagTerminationChkTasks:", e);
			value = null;
		}
		return value;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @return Date
	 * @Param paramBean
	 */
	@Override
	public Integer checkCaseloadAccessCountOne(final OffenderBookingEvent paramBean) {
		Integer returnVal = 0;
		final String sql = getQuery("CHECK_CASELOAD_ACCESS_COUNT_ONE");
		try {
			returnVal = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("caseloadId", paramBean.getCaseloadId()), Integer.class);
		} catch (EmptyResultDataAccessException e) {
			returnVal = 0;
		}
		return returnVal;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @return Date
	 * @Param paramBean
	 */
	@Override
	public Integer checkCaseloadAccessCountTwo(final OffenderBookingEvent paramBean) {
		Integer returnVal = 0;
		final String sql = getQuery("CHECK_CASELOAD_ACCESS_COUNT_TWO");
		try {
			returnVal = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("offenderBookId", paramBean.getOffenderBookId()), Integer.class);
		} catch (EmptyResultDataAccessException e) {
			returnVal = 0;
		}
		return returnVal;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @return Date
	 * @Param paramBean
	 */
	@Override
	public List<CaseloadAgencyLocations> checkCaseloadAccessAgyLocCur(final OffenderBookingEvent paramBean) {
		List<CaseloadAgencyLocations> returnList = new ArrayList<>();
		final String sql = getQuery("check_caseload_access_agy_loc_cur");
		final RowMapper<CaseloadAgencyLocations> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				CaseloadAgencyLocations.class, agencyLocationsMapping);
		try {
			returnList = (ArrayList<CaseloadAgencyLocations>) namedParameterJdbcTemplate.query(sql,
					createParams("caseloadId", paramBean.getCaseloadId()), columnRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("Exception : checkCaseloadAccessAgyLocCur:", e);
			returnList = new ArrayList<>();
		}
		return returnList;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @return Date
	 * @Param paramBean
	 */
	@Override
	public List<CaseloadAgencyLocations> checkCaseloadAccessAgyLocCurTwo(final OffenderBookingEvent paramBean) {
		List<CaseloadAgencyLocations> returnVal = new ArrayList<>();
		final String sql = getQuery("check_caseload_access_agy_loc_two_cur");
		final RowMapper<CaseloadAgencyLocations> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				CaseloadAgencyLocations.class, agencyLocationsMapping);
		try {
			returnVal = (ArrayList<CaseloadAgencyLocations>) namedParameterJdbcTemplate.query(sql,
					createParams("offenderBookId", paramBean.getOffenderBookId()), columnRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("Exception : checkCaseloadAccessAgyLocCurTwo:", e);
			returnVal = new ArrayList<>();
		}
		return returnVal;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @return Date
	 * @Param paramBean
	 */
	@Override
	public CasePlans caseplanAndPaperfileCasePlanCur(final OffenderBookingEvent paramBean) {
		CasePlans returnVal = new CasePlans();
		final String sql = getQuery("CASEPLAN_AND_PAPERFILE_CASE_PLAN_CUR");
		final RowMapper<CasePlans> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, CasePlans.class,
				caseplanMapping);
		try {
			returnVal = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("offenderBookId", paramBean.getOffenderBookId()), columnRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("Exception : caseplanAndPaperfileCasePlanCur:", e);
			returnVal = new CasePlans();
		}
		return returnVal;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @return Date
	 * @Param paramBean
	 */
	@Override
	public List<OffenderCommunityFile> caseplanAndPaperfileVsPaperCur(final OffenderBookingEvent paramBean) {
		List<OffenderCommunityFile> returnVal = new ArrayList<>();
		final String sql = getQuery("CASEPLAN_AND_PAPERFILE_VS_PAPER_CUR");
		final RowMapper<OffenderCommunityFile> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderCommunityFile.class, caseplanMapping);
		try {
			returnVal = namedParameterJdbcTemplate.query(sql,
					createParams("offenderId", paramBean.getOffenderId(), "toAgyLocId", paramBean.getToAgyLocId()),
					columnRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("Exception : caseplanAndPaperfileVsPaperCur:", e);
			returnVal = new ArrayList<>();
		}
		return returnVal;
	}

	/**
	 * This method is used to insert the records in the data base tables based
	 * on
	 *
	 * @param lstOffenderBookingEvent
	 *            List<OffenderBookingEvent>
	 *
	 * @return List<Integer>
	 *
	 * @throws SQLException
	 */
	public Integer casePlanInsert(final CasePlans listObj) {
		final Long casePlanId = preInsertcasePlans(listObj);
		listObj.setCasePlanId(casePlanId);
		final String sql = getQuery("OCDCLOSE_CASEPLAN_INSERT");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		parameters.add(new BeanPropertySqlParameterSource(listObj));
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (returnArray.length == 1) {
			return 1;
		} else {
			return 0;
		}
	}

	private Long preInsertcasePlans(final CasePlans listObj) {
		final String sql = getQuery("OCDCLOSE_CASE_PLANS_PREINSERT");
		return namedParameterJdbcTemplate.queryForObject(sql,
				createParams("offenderBookId", listObj.getOffenderBookId()), Long.class);
	}

	/**
	 * This method is used to insert the records in the data base tables based
	 * on
	 *
	 * @param lstOffenderBookingEvent
	 *            List<OffenderBookingEvent>
	 *
	 * @return List<Integer>
	 *
	 * @throws SQLException
	 */
	public Integer casePlanUpdate(final CasePlans listObj) {
		final String sql = getQuery("OCDCLOSE_CASEPLAN_UPDATE");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		parameters.add(new BeanPropertySqlParameterSource(listObj));
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (returnArray.length == 1) {
			return 1;
		} else {
			return 0;
		}
	}

	//
	/**
	 * Fetching the record from database table
	 * 
	 * @return Date
	 * @Param paramBean
	 */
	@Override
	public String vagyLocTo() {
		String returnVal = null;
		final String sql = getQuery("OCDCLOSE_V_AGY_LOC_TO");
		try {
			returnVal = namedParameterJdbcTemplate.queryForObject(sql, createParams(), String.class);
		} catch (EmptyResultDataAccessException e) {
			logger.error("Exception : vagyLocTo:", e);
			returnVal = null;
		}
		return returnVal;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @return Date
	 * @Param paramBean
	 */
	@Override
	public String vProfileValue() {
		String returnVal = null;
		final String sql = getQuery("OCDCLOSE_V_PROFILE_VALUE");
		try {
			returnVal = namedParameterJdbcTemplate.queryForObject(sql, createParams(), String.class);
		} catch (EmptyResultDataAccessException e) {
			logger.error("Exception : vProfileValue:", e);
			returnVal = null;
		}
		return returnVal;
	}

	/**
	 * This method is used to insert the records in the data base tables based
	 * on
	 *
	 * @param lstOffenderBookingEvent
	 *            List<OffenderBookingEvent>
	 *
	 * @return List<Integer>
	 *
	 * @throws SQLException
	 */
	public Integer offenderCommunityFilesUpdate(final OffenderCommunityFile listObj) {
		final String sql = getQuery("OCDCLOSE_OFFENDER_COMMUNITY_FILES_UPDATE");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		parameters.add(new BeanPropertySqlParameterSource(listObj));
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (returnArray.length == 1) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public Integer pimsFileTracking(final OffenderCommunityFile offComm) {
		Integer genSeq = 0;
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		inParamMap.put("P_OFFENDER_FILE_SEQ", offComm.getOffenderFileSeq());
		inParamMap.put("P_OFFENDER_ID", offComm.getOffenderId());
		inParamMap.put("P_FILE_TRANS_COMMENT", null);
		inParamMap.put("P_AGY_LOC_ID_FROM", "");
		inParamMap.put("P_AGY_LOC_ID_TO", "");
		inParamMap.put("P_STAFF_ID_FROM", offComm.getHoldingStaffId());
		inParamMap.put("P_STAFF_ID_TO", null);
		inParamMap.put("P_NON_OFFICER_FROM", offComm.getNonOfficerStatus());
		inParamMap.put("P_NON_OFFICER_TO", "INT");
		try{
		namedParameterJdbcTemplate.update(
				"call OMS_OWNER.PIMS_FILE_TRACKING.TRANSFER_FILE(:P_OFFENDER_FILE_SEQ, :P_OFFENDER_ID, :P_FILE_TRANS_COMMENT, :P_AGY_LOC_ID_FROM, :P_AGY_LOC_ID_TO, :P_STAFF_ID_FROM, :P_STAFF_ID_TO, :P_NON_OFFICER_FROM, :P_NON_OFFICER_TO);",
				inParamMap);
		} catch(Exception e) {
			logger.error("pimsFileTracking",e);
			genSeq = 0;
		}
		genSeq = 2;
		return genSeq;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @return Date
	 * @Param paramBean
	 */
	@Override
	public String getvProfileValue() {
		final String sql = getQuery("GET_V_PROFILE_VALUE");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams(), String.class);
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @return Date
	 * @Param paramBean
	 */
	@Override
	public String getvNo() {
		final String sql = getQuery("UPDATE_OFFENDER_COMM_V_NO");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams(), String.class);
	}
	
	
	public List<ReferenceCodes> getListOfReasonRefCode() {
		final String sql = getQuery("OCDCLOSE_FIND_LIST_REFERENCE_CODE_FOR_REASON");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("Exception : cgfkObeDspDescriptionRecordGroup:", e);
			return Collections.emptyList();
		}
	}
	public Integer getRefCodes(final List<String> codes) {
		final String sql = getQuery("OCDCLOSE_FIND_LIST_REFERENCE_CODE_FOR_PARENT");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("CODE",codes), Integer.class);
	}
	@Override
	public OffenderBookings aluesOffenderBookings(final Long offenderBookId) {
		final String sql = getQuery("GET_OLD_VALUES_OFFENDER_BOOKINGS");
		OffenderBookings list = new OffenderBookings();
		
		try {
			
			list =namedParameterJdbcTemplate.queryForObject(sql, createParams("OFFENDER_BOOK_ID", offenderBookId), new BeanPropertyRowMapper<OffenderBookings>(OffenderBookings.class));
		} catch (Exception e) {
			logger.error("Exception : cgfkObeDspDescriptionRecordGroup:", e);
		}
		return list;
	}
	
	
	@Override
	public String ocdcloseTriggerDelete() {
		final String sql = getQuery("OCDCLOSE_TRIGGER_DELETE");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams(), String.class);
	}
	
	public List<OffenderCommunityFiles> offenderCommunityFiles(final Long offenderBookId) {
		final String sql = getQuery("GET_VALUES_OFFENDER_COMMUNITY_FILES");
		List<OffenderCommunityFiles> list = new ArrayList<OffenderCommunityFiles>();
		try {
			
			list =namedParameterJdbcTemplate.query(sql, createParams("OFFENDER_BOOK_ID", offenderBookId), new BeanPropertyRowMapper<OffenderCommunityFiles>(OffenderCommunityFiles.class));
		} catch (Exception e) {
			return list;
		}
		return list;
	}
	
	@Override
	public Boolean isActiveOrderPresent(Integer offenderBookId) {
		final String sql = getQuery("OCDCLOSE_CHK_ACTIVE_ORDERS_DEPENDENCIES");
		try {
			String result = namedParameterJdbcTemplate.queryForObject(sql, createParams("offenderBookId",offenderBookId), String.class);
			if("Y".equalsIgnoreCase(result)) {
				return true;
			}
		} catch (Exception e) {
			logger.error("In isActiveOrderPresent", e);
		}
		return false;
	}
	
	@Override
	public Boolean isActiveCourtReportPresent(Integer offenderBookId) {
		final String sql = getQuery("OCDCLOSE_CHK_ACTIVE_COURT_REPORTS");
		try {
			String result = namedParameterJdbcTemplate.queryForObject(sql, createParams("offenderBookId",offenderBookId), String.class);
			if("Y".equalsIgnoreCase(result)) {
				return true;
			}
		} catch (Exception e) {
			logger.error("In isActiveCourtReportPresent", e);
		}
		return false;
	}
}

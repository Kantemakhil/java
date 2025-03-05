package net.syscon.s4.cm.intakeclosure.impl;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
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
import net.syscon.s4.cf.deductions.beans.FeeAccountProfiles;
import net.syscon.s4.cm.intakeclosure.OcdintakRepository;
import net.syscon.s4.common.beans.OdynfrmSubmitDataBean;
import net.syscon.s4.common.beans.OffenderBookingEvent;
import net.syscon.s4.common.beans.OffenderBookings;
import net.syscon.s4.common.beans.OffenderCustodyStatus;
import net.syscon.s4.common.beans.OffenderResidence;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.common.beans.VHeaderBlock;
import net.syscon.s4.common.beans.VHeaderBlock2;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.ReferenceCodes;
import net.syscon.s4.im.beans.SystemProfiles;
import net.syscon.s4.inst.legalscreens.maintenance.bean.LegalCustodyStatuses;
import net.syscon.s4.sa.recordmaintenance.AutomationQueryParameters;
import oracle.jdbc.OracleTypes;

/**
 * Class OcdintakRepositoryImpl
 * 
 */
@Repository
public class OcdintakRepositoryImpl extends RepositoryBase implements OcdintakRepository {

	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OcdintakRepositoryImpl.class.getName());

	private final Map<String, FieldMapper> staffMembersMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("MIDDLE_NAME",                                  new FieldMapper("middleName"))
			.put("LAST_NAME",                                    new FieldMapper("lastName"))
			.put("FIRST_NAME",                                   new FieldMapper("firstName"))
			.put("ASSIGNED_STAFF_ID",                            new FieldMapper("assignedStaffId"))
			.build();

	private final Map<String, FieldMapper> referenceCodesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("LIST_SEQ",                                       new FieldMapper("listSeq"))
			.put("CODE",                                           new FieldMapper("code"))
			.put("DESCRIPTION",                                    new FieldMapper("description"))
			.put("BOOKING_STATUS",                                 new FieldMapper("bookingStatus"))
			.put("MODE",                                           new FieldMapper("mode"))
			.put("REASON_CODE",                                    new FieldMapper("reasonCode"))
			.put("BOOKING_EVENT_CODE",                             new FieldMapper("bookingEventCode"))
			.put("DSP_DESCRIPTION4",                               new FieldMapper("dspDescription4"))
			.put("DSP_DESCRIPTION5",                               new FieldMapper("dspDescription5")).build();
	private final Map<String, FieldMapper> mAgyLocIdMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DESCRIPTION",                               new FieldMapper("description"))
			.build();
	private final Map<String, FieldMapper> mMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("LIST_SEQ",                          new FieldMapper("listSeq"))
			.put("DESCRIPTION", 					  new FieldMapper("description")).build();
	private final Map<String, FieldMapper> agencyLocationsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("FROM_AGY_LOC_ID",								     new FieldMapper("fromAgyLocId"))
			.put("OFFENDER_ID",									     new FieldMapper("offenderId"))
			.put("AGY_LOC_ID",										 new FieldMapper("agyLocId"))
			.put("DESCRIPTION",										 new FieldMapper("description"))
			.put("DSP_DESCRIPTION",									 new FieldMapper("dspDescription"))
			.put("CASELOAD_ID",										 new FieldMapper("caseloadId"))
			.put("TO_AGY_LOC_ID",									 new FieldMapper("toAgyLocId"))
			.put("DSP_DESCRIPTION3",								 new FieldMapper("dspDescription3")).build();
	private final Map<String, FieldMapper> offenderBookingEventsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("0",													  new FieldMapper("0"))
			.put("NVL(MAX(EVENT_SEQ)",									  new FieldMapper(" nvl(max(eventSeq)"))
			.put("OFFENDER_BOOK_ID",                                      new FieldMapper("offenderBookId"))
			.build();
	private final Map<String, FieldMapper> offenderResidencesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.build();
	private final Map<String, FieldMapper> systemProfilesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.build();
	private final Map<String, FieldMapper> offenderBookingsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("OFFENDER_BOOK_ID",								 new FieldMapper("offenderBookId"))
			.put("BOOKING_BEGIN_DATE",								 new FieldMapper("bookingBeginDate"))
			.put("BOOKING_END_DATE",								 new FieldMapper("bookingEndDate"))
			.put("BOOKING_NO",										 new FieldMapper("bookingNo"))
			.put("OFFENDER_ID",										 new FieldMapper("offenderId"))
			.put("AGY_LOC_ID", 										 new FieldMapper("agyLocId"))
			.put("LIVING_UNIT_ID",									 new FieldMapper("livingUnitId"))
			.put("DISCLOSURE_FLAG",									 new FieldMapper("disclosureFlag"))
			.put("ACTIVE_FLAG",                                      new FieldMapper("activeFlag"))
			.put("BOOKING_STATUS",                                   new FieldMapper("bookingStatus"))
			.put("YOUTH_ADULT_CODE",                                 new FieldMapper("youthAdultCode"))
			.put("FINGER_PRINTED_STAFF_ID",                          new FieldMapper("fingerPrintedStaffId"))
			.put("SEARCH_STAFF_ID",                                  new FieldMapper("searchStaffId"))
			.put("PHOTO_TAKING_STAFF_ID",                            new FieldMapper("photoTakingStaffId"))
			.put("ASSIGNED_STAFF_ID",                                new FieldMapper("assignedStaffId"))
			.put("CREATE_AGY_LOC_ID",                                new FieldMapper("createAgyLocId"))
			.put("BOOKING_TYPE",                                     new FieldMapper("bookingType"))
			.put("BOOKING_CREATED_DATE",                             new FieldMapper("bookingCreatedDate"))
			.put("ROOT_OFFENDER_ID",                                 new FieldMapper("rootOffenderId"))
			.put("AGENCY_IML_ID",                                    new FieldMapper("agencyImlId"))
			.put("SERVICE_FEE_FLAG",                                 new FieldMapper("serviceFeeFlag"))
			.put("EARNED_CREDIT_LEVEL",                              new FieldMapper("earnedCreditLevel"))
			.put("EKSTRAND_CREDIT_LEVEL",                            new FieldMapper("ekstrandCreditLevel"))
			.put("INTAKE_AGY_LOC_ID",                                new FieldMapper("intakeAgyLocId"))
			.put("ACTIVITY_DATE",                                    new FieldMapper("activityDate"))
			.put("INTAKE_CASELOAD_ID",                               new FieldMapper("intakeCaseloadId"))
			.put("INTAKE_USER_ID",                                   new FieldMapper("intakeUserId"))
			.put("CASE_OFFICER_ID",                                  new FieldMapper("caseOfficerId"))
			.put("CASE_DATE",                                        new FieldMapper("caseDate"))
			.put("CASE_TIME",                                        new FieldMapper("caseTime"))
			.put("COMMUNITY_ACTIVE_FLAG",                            new FieldMapper("communityActiveFlag"))
			.put("CREATE_INTAKE_AGY_LOC_ID",                         new FieldMapper("createIntakeAgyLocId"))
			.put("COMM_STAFF_ID",                                    new FieldMapper("commStaffId"))
			.put("COMM_STATUS",                                      new FieldMapper("commStatus"))
			.put("COMMUNITY_AGY_LOC_ID",                             new FieldMapper("communityAgyLocId"))
			.put("NO_COMM_AGY_LOC_ID",                               new FieldMapper("noCommAgyLocId"))
			.put("COMM_STAFF_ROLE",                                  new FieldMapper("commStaffRole"))
			.put("AGY_LOC_ID_LIST",                                  new FieldMapper("agyLocIdList"))
			.put("STATUS_REASON",                                    new FieldMapper("statusReason"))
			.put("TOTAL_UNEXCUSED_ABSENCES",                         new FieldMapper("totalUnexcusedAbsences"))
			.put("REQUEST_NAME",                                     new FieldMapper("requestName"))
			.put("RECORD_USER_ID",                                   new FieldMapper("recordUserId"))
			.put("CREATE_USER_ID",                                   new FieldMapper("createUserId"))
			.put("INTAKE_AGY_LOC_ASSIGN_DATE",                       new FieldMapper("intakeAgyLocAssignDate"))
			.put("CREATE_DATETIME",                                  new FieldMapper("createDatetime"))
			.put("MODIFY_DATETIME",                                  new FieldMapper("modifyDatetime"))
			.put("MODIFY_USER_ID",                                   new FieldMapper("modifyUserId"))
			.put("SEAL_FLAG",                                        new FieldMapper("sealFlag"))
			.build();
	private final Map<String, FieldMapper> vHeaderBlock2Mapping = new ImmutableMap.Builder<String, FieldMapper>()
			.build();
	
	private final Map<String, FieldMapper> legalCustodyStatusesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("STATUS_DESCRIPTION",		new FieldMapper("statusDescription"))
		    .put("STATUS_CODE",         	new FieldMapper("statusCode")) 
		    .put("ALWAYS_DISPLAY_FLAG",     new FieldMapper("alwaysDisplayFlag")) 
		    .put("STATUS_RANK",         	new FieldMapper("statusRank")) 
			.build();
	private final Map<String, FieldMapper> uiBeanMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("COMP_ID",                  new FieldMapper("compId"))
			.put("COMP_TYPE",                new FieldMapper("compType"))
			.put("CREATED_BY",               new FieldMapper("createdBy"))
			.put("MODIFIED_BY",              new FieldMapper("modifiedBy"))
			.put("CREATE_DATE",              new FieldMapper("createDate"))
			.put("MODIFY_DATE",              new FieldMapper("modifyDate"))
			.put("COMP_CONFIG",              new FieldMapper("compConfig"))
			.put("COMP_CONFIG_DEF",          new FieldMapper("compConfigDef"))
			.build();
	
	/**
	 * Creates new OcdintakRepositoryImpl class Object
	 */

	public OcdintakRepositoryImpl() {
		// OcdintakRepositoryImpl
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            VHeaderBlock2
	 *
	 * @return List<VHeaderBlock2>
	 *
	 * 
	 */
	public List<VHeaderBlock2> offBkgExecuteQuery(VHeaderBlock2 objSearchDao) {
		final String sqlQueryTemp = getQuery("OCDINTAK_OFFBKG_FIND_V_HEADER_BLOCK2");
		final StringBuffer sqlQuery = new StringBuffer(sqlQueryTemp);
		String preparedSql = null;
		final MapSqlParameterSource valuesList = new MapSqlParameterSource();
		sqlQuery.append(sqlQueryTemp);
		if (objSearchDao != null) {
			sqlQuery.append(" where ");
		if(objSearchDao.getCreateUserId()!=null) {
			valuesList.addValue("userId", objSearchDao.getCreateUserId());
		}
if(objSearchDao.getCreateUserId()!=null) {
	valuesList.addValue("userId", objSearchDao.getCreateUserId());
}
			if (objSearchDao.getOffenderId() != null) {
				sqlQuery.append("OFFENDER_ID =  :offenderId " + " and ");
				valuesList.addValue("offenderId", objSearchDao.getOffenderId());
			}

		}
		preparedSql = sqlQuery.toString().trim();
		if (preparedSql.endsWith("where")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 5);
		}
		if (preparedSql.endsWith("AND")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 3);
		}

		final RowMapper<VHeaderBlock2> VHeaderBlock2RowMapper = Row2BeanRowMapper.makeMapping(preparedSql, VHeaderBlock2.class,
				vHeaderBlock2Mapping);
		 List<VHeaderBlock2> returnList = new  ArrayList<VHeaderBlock2>(); 
		returnList = namedParameterJdbcTemplate.query(preparedSql, valuesList, VHeaderBlock2RowMapper);
		return returnList;
	}
	

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            OffenderBookings
	 *
	 * @return List<OffenderBookings>
	 *
	 * 
	 */
	public List<OffenderBookings> offBkgsExecuteQuery(final OffenderBookings objSearchDao) {
		final String sql = getQuery("OCDINTAK_OFFBKGS_FIND_OFFENDER_BOOKINGS");
		final RowMapper<OffenderBookings> OffenderBookingsRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderBookings.class, offenderBookingsMapping);
		List<OffenderBookings> returnList = new ArrayList<OffenderBookings>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql,
					createParams("rootOffenderId", objSearchDao.getRootOffenderId()), OffenderBookingsRowMapper);
		} catch (Exception e) {

			logger.error("offBkgsExecuteQuery", e);
		}

		return returnList;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            OffenderBookingEvents
	 *
	 * @return List<OffenderBookingEvents>
	 *
	 * 
	 */
	public List<OffenderBookingEvent> offBkgeExecuteQuery(final OffenderBookingEvent objSearchDao) {
		final String sql = getQuery("OCDINTAK_OFFBKGE_FIND_OFFENDER_BOOKING_EVENTS");
		final RowMapper<OffenderBookingEvent> OffenderBookingEventsRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderBookingEvent.class, offenderBookingEventsMapping);
		final ArrayList<OffenderBookingEvent> returnList = (ArrayList<OffenderBookingEvent>) namedParameterJdbcTemplate
				.query(sql, createParams("caseloadId", objSearchDao.getCaseloadId()), OffenderBookingEventsRowMapper);
		return returnList;
	}

	/**
	 * This method is used to insert the records in the data base tables based
	 * on
	 *
	 * @param lstOffenderBookingEvents
	 *            List<OffenderBookingEvents>
	 *
	 * @return List<Integer>
	 *
	 * 
	 */
	@Override
	public List<ReferenceCodes>getBookingEventCode(final String dspDescription) {
		final String sql = getQuery("OCDINTAK_OFFBKGE_PRE_INSERT_OFFENDER_BOOKING_EVENTS");
		final RowMapper<ReferenceCodes> OffenderBookingEventsRowMapper = Row2BeanRowMapper.makeMapping(sql,
				ReferenceCodes.class, offenderBookingEventsMapping);
		final ArrayList<ReferenceCodes> returnList = (ArrayList<ReferenceCodes>) namedParameterJdbcTemplate
				.query(sql, createParams("DSP_DESCRIPTION5", dspDescription), OffenderBookingEventsRowMapper);
		return returnList;
	}
	
	public Integer offbkgeInsertOffenderBookingEvents(final List<OffenderBookingEvent> lstOffenderBookingEvents) {
		final String sql = getQuery("OCDINTAK_OFFBKGE_INSERT_OFFENDER_BOOKING_EVENTS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffenderBookingEvent offenderBookingEvents : lstOffenderBookingEvents) {
			parameters.add(new BeanPropertySqlParameterSource(offenderBookingEvents));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstOffenderBookingEvents.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            OffenderResidences
	 *
	 * @return List<OffenderResidences>
	 *
	 * 
	 */
	public List<OffenderResidence> reportInExecuteQuery(final OffenderResidence objSearchDao) {
		final String sql = getQuery("OCDINTAK_REPORTIN_FIND_OFFENDER_RESIDENCES");
		final RowMapper<OffenderResidence> OffenderResidencesRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderResidence.class, offenderResidencesMapping);
		List<OffenderResidence> returnList = new ArrayList<OffenderResidence>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams(), OffenderResidencesRowMapper);
		} catch (Exception e) {
			logger.error("reportInExecuteQuery", e);
		}
		return returnList;
	}

	/**
	 * This method is used to insert the records in the data base tables based
	 * on
	 *
	 * @param lstOffenderResidences
	 *            List<OffenderResidences>
	 *
	 * @return List<Integer>
	 *
	 * 
	 */
	public Integer reportinInsertOffenderResidences(final List<OffenderResidence> lstOffenderResidences) {
		String sql = getQuery("OCDINTAK_REPORTIN_INSERT_OFFENDER_RESIDENCES");
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		int[] returnArray = new int[] {};
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstOffenderResidences.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            SystemProfiles
	 *
	 * @return List<SystemProfiles>
	 * 
	 */
	public List<SystemProfiles> sysPflExecuteQuery(final SystemProfiles objSearchDao) {
		final String sql = getQuery("OCDINTAK_SYSPFL_FIND_SYSTEM_PROFILES");
		final RowMapper<SystemProfiles> SystemProfilesRowMapper = Row2BeanRowMapper.makeMapping(sql,
				SystemProfiles.class, systemProfilesMapping);
		List<SystemProfiles> returnList = new ArrayList<SystemProfiles>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams(), SystemProfilesRowMapper);
		} catch (Exception e) {
			logger.error("sysPflExecuteQuery", e);
		}
		return returnList;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<AgencyLocations> rgToAgyLocRecordGroup(final String caseloadId, final Long offenderId) {
		final String sql = getQuery("OCDINTAK_FIND_RGTOAGYLOC");
		final RowMapper<AgencyLocations> mRowMapper = Row2BeanRowMapper.makeMapping(sql, AgencyLocations.class,
				mMapping);
		List<AgencyLocations> returnList = new ArrayList<AgencyLocations>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql,
					createParams("caseloadId", caseloadId, "offenderId", offenderId), mRowMapper);
		} catch (Exception e) {
			logger.error("rgToAgyLocRecordGroup", e);
		}
		return returnList;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<ReferenceCodes> rgIntakeTypeRecordGroup() {
		final String sql = getQuery("OCDINTAK_FIND_RGINTAKETYPE");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);
		List<ReferenceCodes> returnList = new ArrayList<ReferenceCodes>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (Exception e) {
			logger.error("rgIntakeTypeRecordGroup", e);
		}
		return returnList;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<ReferenceCodes> rgIntakeRsnRecordGroup() {
		final String sql = getQuery("OCDINTAK_FIND_RGINTAKERSN");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);
		List<ReferenceCodes> returnList = new ArrayList<ReferenceCodes>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (Exception e) {
			logger.error("rgIntakeRsnRecordGroup", e);
		}
		return returnList;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<MAgyLocId>
	 */
	public List<AgencyLocations> rgFromAgyLocRecordGroup() {
		final String sql = getQuery("OCDINTAK_FIND_RGFROMAGYLOC");
		final RowMapper<AgencyLocations> AgyLocRowMapper = Row2BeanRowMapper.makeMapping(sql, AgencyLocations.class,
				mAgyLocIdMapping);
		List<AgencyLocations> returnList = new ArrayList<AgencyLocations>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams(), AgyLocRowMapper);
		} catch (Exception e) {
			logger.error("rgFromAgyLocRecordGroup", e);
		}
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfkchkOffBkgsOffBkgStaf
	 *
	 * @param params
	 *
	 */
	public List<StaffMembers> cgfkchkOffBkgsOffBkgStaf(final StaffMembers paramBean) {
		final String sql = getQuery("OCDINTAK_CGFKCHK_OFF_BKGS_OFF_BKG_STAF");
		final RowMapper<StaffMembers> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, StaffMembers.class,
				staffMembersMapping);
		List<StaffMembers> returnList = new ArrayList<StaffMembers>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams(), columnRowMapper);
		} catch (Exception e) {
			logger.error("cgfkchkOffBkgsOffBkgStaf", e);
		}
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfkchkOffBkgsOffBkgRef
	 *
	 * @param params
	 *
	 */
	public List<ReferenceCodes> cgfkchkOffBkgsOffBkgRef(final ReferenceCodes paramBean) {
		final String sql = getQuery("OCDINTAK_CGFKCHK_OFF_BKGS_OFF_BKG_REF_");
		final RowMapper<ReferenceCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				referenceCodesMapping);
		List<ReferenceCodes> returnList = new ArrayList<ReferenceCodes>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams(), columnRowMapper);
		} catch (Exception e) {
			logger.error("cgfkchkOffBkgsOffBkgRef", e);
		}
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfkchkOffBkgeOffBkgeAgy
	 *
	 * @param params
	 *
	 */
	public List<AgencyLocations> cgfkchkOffBkgeOffBkgeAgy(final AgencyLocations paramBean) {
		final String sql = getQuery("OCDINTAK_CGFKCHK_OFF_BKGE_OFF_BKGE_AGY");
		final RowMapper<AgencyLocations> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, AgencyLocations.class,
				agencyLocationsMapping);
		List<AgencyLocations> returnList = new ArrayList<AgencyLocations>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams(), columnRowMapper);
		} catch (Exception e) {
			logger.error("cgfkchkOffBkgeOffBkgeAgy", e);
		}
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfklkpOffBkgeOffBkgeAgy
	 *
	 * @param params
	 *
	 */
	public List<AgencyLocations> cgfklkpOffBkgeOffBkgeAgy(final AgencyLocations paramBean) {
		final String sql = getQuery("OCDINTAK_CGFKLKP_OFF_BKGE_OFF_BKGE_AGY");
		final RowMapper<AgencyLocations> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, AgencyLocations.class,
				agencyLocationsMapping);
		List<AgencyLocations> returnList = new ArrayList<AgencyLocations>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams(), columnRowMapper);
		} catch (Exception e) {
			logger.error("cgfklkpOffBkgeOffBkgeAgy", e);
		}
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfkchkOffBkgeOffBkge
	 *
	 * @param params
	 *
	 */
	public List<AgencyLocations> cgfkchkOffBkgeOffBkge(final AgencyLocations paramBean) {
		final String sql = getQuery("OCDINTAK_CGFKCHK_OFF_BKGE_OFF_BKGE_2");
		final RowMapper<AgencyLocations> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, AgencyLocations.class,
				agencyLocationsMapping);
		List<AgencyLocations> returnList = new ArrayList<AgencyLocations>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams(), columnRowMapper);
		} catch (Exception e) {
			logger.error("cgfkchkOffBkgeOffBkge", e);
		}
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfklkpOffBkgeOffBkge
	 *
	 * @param params
	 *
	 */
	public List<AgencyLocations> cgfklkpOffBkgeOffBkge(final AgencyLocations paramBean) {
		final String sql = getQuery("OCDINTAK_CGFKLKP_OFF_BKGE_OFF_BKGE_2");
		final RowMapper<AgencyLocations> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, AgencyLocations.class,
				agencyLocationsMapping);
		List<AgencyLocations> returnList = new ArrayList<AgencyLocations>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams(), columnRowMapper);
		} catch (Exception e) {
			logger.error("cgfklkpOffBkgeOffBkge", e);
		}
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfkchkOffBkgeOffBkgeRef
	 *
	 * @param params
	 *
	 */
	public List<ReferenceCodes> cgfkchkOffBkgeOffBkgeRef(final ReferenceCodes paramBean) {
		final String sql = getQuery("OCDINTAK_CGFKCHK_OFF_BKGE_OFF_BKGE_REF");
		final RowMapper<ReferenceCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				referenceCodesMapping);
		List<ReferenceCodes> returnList = new ArrayList<ReferenceCodes>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams(), columnRowMapper);
		} catch (Exception e) {
			logger.error("");
		}
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfklkpOffBkgeOffBkgeRef
	 *
	 * @param params
	 *
	 */
	public List<ReferenceCodes> cgfklkpOffBkgeOffBkgeRef(final ReferenceCodes paramBean) {
		final String sql = getQuery("OCDINTAK_CGFKLKP_OFF_BKGE_OFF_BKGE_REF");
		final RowMapper<ReferenceCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				referenceCodesMapping);
		List<ReferenceCodes> returnList = new ArrayList<ReferenceCodes>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams(), columnRowMapper);
		} catch (Exception e) {
			logger.error("cgfklkpOffBkgeOffBkgeRef", e);
		}
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfkchkOffBkgeOffBkge
	 *
	 * @param params
	 *
	 */
	public List<ReferenceCodes> cgfkchkOffBkgeOffBkge(final ReferenceCodes paramBean) {
		final String sql = getQuery("OCDINTAK_CGFKCHK_OFF_BKGE_OFF_BKGE_3");
		final RowMapper<ReferenceCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				referenceCodesMapping);
		List<ReferenceCodes> returnList = new ArrayList<ReferenceCodes>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams(), columnRowMapper);
		} catch (Exception e) {
			logger.error("cgfkchkOffBkgeOffBkge", e);
		}
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfklkpOffBkgeOffBkge
	 *
	 * @param params
	 *
	 */
	public List<ReferenceCodes> cgfklkpOffBkgeOffBkge(final ReferenceCodes paramBean) {
		final String sql = getQuery("OCDINTAK_CGFKLKP_OFF_BKGE_OFF_BKGE_3");
		final RowMapper<ReferenceCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				referenceCodesMapping);
		List<ReferenceCodes> returnList = new ArrayList<ReferenceCodes>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams(), columnRowMapper);
		} catch (Exception e) {
			logger.error("cgfklkpOffBkgeOffBkge", e);
		}
		return returnList;
	}

	@Override
	public Integer reportInInsertOffenderResidences(List<OffenderResidence> lstOffenderResidences) {
		return null;
	}

	@Override
	public Integer offBkgeInsertOffenderBookingEvents(List<OffenderBookingEvent> lstOffenderBookingEvents) {
		return null;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * offSchPreInsert
	 *
	 *
	 */
	public String getAgyStatus(final String statusValue) {
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[4];
		sqlParameters = new SqlParameter[] { new SqlParameter("RETURN_VALUE", OracleTypes.VARCHAR),
				new SqlParameter("P_PROFILE_TYPE", OracleTypes.VARCHAR),
				new SqlParameter("P_PROFILE_CODE", OracleTypes.VARCHAR),
				new SqlInOutParameter("P_VALUE_NO", OracleTypes.NUMBER) };
		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("OMS").withFunctionName("SYSTEM_PROFILE").declareParameters(sqlParameters);
		inParamMap.put("P_PROFILE_TYPE", "CLIENT");
		inParamMap.put("P_PROFILE_CODE", "B/C_STATUS");
		inParamMap.put("P_VALUE_NO", statusValue);
		final SqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		return simpleJDBCCall.executeFunction(String.class, inParameter);
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfklkpOffBkgeOffBkge
	 *
	 * @param params
	 *
	 */
	public Date checkPrevBooking(final OffenderBookings paramBean) {
		final String sql = getQuery("OCDINTAK_CHECK_PREV_BOOKING");
		Date returnDate = null;
		try {
			returnDate = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("bookingNo", paramBean.getBookingNo()), Date.class);
		} catch (NullPointerException e) {
			return returnDate;
		}
		return returnDate;
	}

	public String createBookingLocationRecExistCur(final OffenderBookingEvent paramBean) {
		final String sql = getQuery("OCDINTAK_CREATE_BOOKING_LOCATIONS_REC_EXIST_CUR");
		String returnData = "N";
		try {
			returnData = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("offenderBookId", paramBean.getOffenderBookId(), "caseloadId",
							paramBean.getCaseloadId(), "agyLocId", paramBean.getToAgyLocId(), "eventDate",
							paramBean.getEventDate()),
					String.class);
		} catch (Exception e) {
			return returnData;
		}
		return returnData;
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
	public Integer updateOffAgyLoc(final OffenderBookingEvent lstOffenderBookingEvent) {
		final String sql = getQuery("CREATE_BOOKING_LOC_UPDATE_OFFENDER_BOOKING_AGY_LOCS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (returnArray.length == 1) {
			return 1;
		} else {
			return 0;
		}
	}

	public String wNewBlockInstanceintakeCase() {
		final String sql = getQuery("OCDCLOSE_W_NEW_BLOCK_INSTANCE");
		String returnData = null;
		try {
			returnData = namedParameterJdbcTemplate.queryForObject(sql, createParams(), String.class);
		} catch (EmptyResultDataAccessException e) {
			return returnData;
		}
		return returnData;
	}

	public Integer checkForActiveBooking(final VHeaderBlock object) {
		final String sql = getQuery("CHECK_FOR_ACTIVE_BOOKING");
		Integer returnData = 0;
		try {
			returnData = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("rootOffenderId", object.getRootOffenderId()), Integer.class);
		} catch (EmptyResultDataAccessException e) {
			return returnData;
		}
		return returnData;
	}

	public String tagBookingCommActiveBookingExists(final VHeaderBlock object) {
		final String sql = getQuery("TAG_BOOKING_IS_COMM_ACTIVE_BOOKING_EXISTS");
		String returnData = null;
		try {
			returnData = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("rootOffenderId", object.getRootOffenderId()), String.class);
		} catch (EmptyResultDataAccessException e) {
			return returnData;
		}
		return returnData;
	}

	public String getProfileValue() {
		final String sql = getQuery("OCDINTAKE_GET_PROFILE_VALUE");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams(), String.class);
	}

	public String getProfileValueConstants(final String profileType, final String profileCode) {
		final String sql = getQuery("OCDINTAKE_GET_PROFILE_VALUE_CON_STS");
		String returnData = null;
		try {
			returnData = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("profileType", profileType, "profileCode", profileCode), String.class);
		} catch (EmptyResultDataAccessException e) {
			return returnData;
		}
		return returnData;
	}

	public String getProfileValueDisabled() {
		final String sql = getQuery("OIDADMIS_LABEL_INTAKE_NEWFL");
		String returnData = null;
		try {
			returnData = namedParameterJdbcTemplate.queryForObject(sql, createParams(), String.class);
		} catch (Exception e) {
			return returnData;
		}
		return returnData;
	}

	public String getProfileTrustValueDisabled() {
		final String sql = getQuery("OCDINTAK_LABEL_COM_FIN_BOX");
		String returnData = null;
		try {
			returnData = namedParameterJdbcTemplate.queryForObject(sql, createParams(), String.class);
		} catch (Exception e) {
			return returnData;
		}
		return returnData;
	}

	public Integer getDefaultIntakeValuesCountCur(final String profileCode) {
		final String sql = getQuery("GET_DEFAULT_INTAKE_VALUES_COUNT_CUR");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("profileCode", profileCode), Integer.class);
	}

	public String getRefCodeValues(final String profileCode) {
		final String sql = getQuery("OCDINTAKE_REFERENCE_CODES");
		String returnData = null;
		try {
			returnData = namedParameterJdbcTemplate.queryForObject(sql, createParams("P_DOMAIN", profileCode),
					String.class);
		} catch (EmptyResultDataAccessException e) {
			return returnData;
		}
		return returnData;
	}

	public Integer intakeCaseMultipleActBkgs(final VHeaderBlock obj) {
		final String sql = getQuery("OCDINTAKE_INTAKE_CASE_MULTIPLE");
		Integer returnData = 0;
		try {
			returnData = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("off_id", obj.getRootOffenderId(), "csld_id", obj.getCaseLoadId()), Integer.class);
		} catch (EmptyResultDataAccessException e) {
			return returnData;
		}
		return returnData;
	}

	public Integer intakeCaseMultipleNumOfComAgy(final VHeaderBlock obj) {
		final String sql = getQuery("OCDINTAKE_INTAKE_CASE_MULTIPLE_NUM_OF_COM_AGY");
		Integer returnData = 0;
		try {
			returnData = namedParameterJdbcTemplate.queryForObject(sql, createParams("csld_id", obj.getCaseLoadId()),
					Integer.class);
		} catch (EmptyResultDataAccessException e) {
			return returnData;
		}
		return returnData;
	}

	public String getDspDescription() {
		final String sql = getQuery("OCDINTAKE_DSP_DESCRIPTION");
		String returnData = null;
		try {
			returnData = namedParameterJdbcTemplate.queryForObject(sql, createParams(), String.class);
		} catch (NullPointerException e) {
			return returnData;
		}
		return returnData;
	}

	public String intakeCaseactBkgExistFlag(VHeaderBlock obj) {
		final String sql = getQuery("INTAKE_CASE_SINGLE_ACT_BKG_EXIST_FLAG");
		String returnData = null;
		try {
			returnData = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("rootOffenderId", obj.getRootOffenderId()), String.class);
		} catch (EmptyResultDataAccessException e) {
			return "2";
		}
		return returnData;
	}

	public String toAgyLoc(final OffenderBookingEvent obj) {
		final String sql = getQuery("OCDINTAK_TO_AGY_LOC");
		String returnData = null;
		try {
			returnData = namedParameterJdbcTemplate.queryForObject(sql, createParams("dspDescription",
					obj.getIntaketo(), "CASELOAD_ID", obj.getCaseloadId(), "offender_id", obj.getOffenderId()),
					String.class);
		} catch (EmptyResultDataAccessException e) {
			return "2";
		}
		return returnData;
	}

	/**
	 * This method will update the transaction in the table
	 * 
	 * @return String
	 */

	@Override
	public Integer instOffBookAgyLoc(final OffenderBookingEvent object) {
		Map<String, Object> returnObject = null;
		Integer returnValue = 0;
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		inParamMap.put("p_caseload_id", object.getCaseloadId());
		inParamMap.put("p_agy_loc_id", object.getToAgyLocId());
		inParamMap.put("p_offender_book_id", object.getOffenderBookId());
		inParamMap.put("p_addition_date", object.getEventDate());
		inParamMap.put("p_addition_time", object.getEventTime());
		inParamMap.put("p_reason_code", object.getReasonCode());
		inParamMap.put("p_removed_date", null);
		inParamMap.put("p_addition_comment", object.getCommentText());

		try {
			namedParameterJdbcTemplate.update(
					" CALL OMS_OWNER.OCDINTAK.INST_OFF_BOOK_AGY_LOC(:p_caseload_id, :p_agy_loc_id, :p_offender_book_id,"
							+ " :p_addition_date, :p_addition_time, :p_reason_code, :p_removed_date, :p_addition_comment) ",
					inParamMap);
			returnValue = 1;
		} catch (Exception e) {
			logger.error("instOffenderBooking :" + e);
		}
		return returnValue;

	}

	public String oldContactCheckBookingCur(final OffenderBookingEvent obj) {
		final String sql = getQuery("OLD_CONTACT_CHECK_BOOKING_CUR");
		String returnData = null;
		try {
			returnData = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("OFFENDER_BOOK_ID", obj.getOffenderBookId()), String.class);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
		return returnData;
	}

	public String oldContactLvYearCur() {
		final String sql = getQuery("OLD_CONTACT_LV_YEAR_CUR");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams(), String.class);
	}

	public Integer oldContactGetStaffId(String user) {
		final String sql = getQuery("OLD_CONTACT_GET_STAFF_ID");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("createUserId", user), Integer.class);
	}

	public Integer oldContactPbookCount(final OffenderBookingEvent obj) {
		final String sql = getQuery("OLD_CONTACT_P_BOOK_COUNT");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("ROOT_OFFENDER_ID", obj.getRootOffenderId()),
				Integer.class);
	}

	public Integer oldContactGetEventSeq(final OffenderBookingEvent obj) {
		final String sql = getQuery("OLD_CONTACT_GET_EVENT_SEQ");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("OFFENDER_BOOK_ID", obj.getOffenderBookId()),
				Integer.class);
	}

	public String oldContactGetLvBookingNo(final String obj) {
		final String sql = getQuery("OCDINTAK_LV_BOOKING_NO");
		String returnData = null;
		try {
			returnData = namedParameterJdbcTemplate.queryForObject(sql, createParams("LVYEAR", obj), String.class);
		} catch (Exception e) {
			return returnData;
		}
		return returnData;
	}

	/**
	 * This method will update the transaction in the table
	 * 
	 * @return String
	 */

	@Override
	public Integer updateOffenderBookings(String toAgyLocId, Integer staffId, String pCommStatus, String lvBookingType,
			Long offenderBookId) {
		Map<String, Object> returnObject = null;
		Integer returnValue = 0;
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[5];
		sqlParameters = new SqlParameter[] { new SqlParameter("P_AGY_LOC_ID", OracleTypes.VARCHAR),
				new SqlParameter("P_STAFFID", OracleTypes.NUMBER),
				new SqlParameter("P_COMM_STATUS", OracleTypes.VARCHAR),
				new SqlParameter("P_BOOKING_TYPE", OracleTypes.VARCHAR),
				new SqlParameter("P_BOOK_ID", OracleTypes.NUMBER) };

		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("OCDINTAK").withProcedureName("UPDATE_OFF_BOOKINGS").declareParameters(sqlParameters);
		inParamMap.put("P_AGY_LOC_ID", toAgyLocId);
		inParamMap.put("P_STAFFID", staffId);
		inParamMap.put("P_COMM_STATUS", pCommStatus);
		inParamMap.put("P_BOOKING_TYPE", lvBookingType);
		inParamMap.put("P_BOOK_ID", offenderBookId);

		final SqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		try {
			returnObject = simpleJDBCCall.execute(inParameter);
			if (returnObject.size() == 0) {
				returnValue = 1;
			}
		} catch (Exception e) {
			returnValue = 0;
		}
		return returnValue;
	}

	@Override
	public String getNvlProfileVal() {
		String sql = getQuery("CHK_PACKAGE_FAILURE_GET_NVL_PROFILE_VAL");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams(), String.class);
	}

	public String createBookingLocationsProfileval() {
		String sql = getQuery("CREATE_BOOKING_LOCATIONS_PROFILEVAL");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams(), String.class);
	}

	public String oidadmisGetNewBookingNo() {
		String sql = getQuery("OIDADMIS_GET_NEW_BOOKING_NO");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams(), String.class);
	}

	public Integer getNewBookId() {
		String sql = getQuery("GET_NEW_BOOK_ID");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams(), Integer.class);
	}

	public Integer getLatestBooking(OffenderBookingEvent obj) {
		String sql = getQuery("OLD_CONTACT_GET_LATEST_BOOKING");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("rootOffenderId", obj.getRootOffenderId(),"USERID",obj.getCreateUserId()),
				Integer.class);
	}

	public Integer instOffenderBooking(final OffenderBookingEvent object) {
		Integer genSeq = 0;
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		inParamMap.put("P_BOOK_ID", object.getOffenderBookId());
		inParamMap.put("P_BEGIN_DATE", object.getEventDate());
		inParamMap.put("P_BOOKING_NO", object.getNbtOffenderBookId2());
		inParamMap.put("P_OFFENDER_ID", object.getOffenderId());
		inParamMap.put("P_ROOT_OFF_ID", object.getRootOffenderId());
		inParamMap.put("P_DISCLOSURE_FLAG", "N");
		inParamMap.put("P_IN_OUT_STATUS", object.getInOutStatus());
		inParamMap.put("P_BOOKING_STATUS", "O");
		inParamMap.put("P_YOUTH_ADULT_CODE", "N");
		inParamMap.put("P_BOOKING_TYPE", object.getBookingType());
		inParamMap.put("P_CREATE_AGY_LOC_ID", object.getToAgyLocId());
		inParamMap.put("P_BOOKING_CREATED_DATE", object.getCreateDatetime());
		inParamMap.put("P_STAFFID", object.getStaffId());
		inParamMap.put("P_CASELOAD_ID", object.getCaseloadId());
		inParamMap.put("P_COMMUNITY_ACTIVE_FLAG", "Y");
		inParamMap.put("P_INTAKE_AGY_LOC_ID", object.getToAgyLocId());
		inParamMap.put("P_COMM_STATUS", object.getpCommStatus());
		try {
			namedParameterJdbcTemplate
					.update(" CALL OMS_OWNER.OCDINTAK.INST_OFF_BOOKING(:P_BOOK_ID, :P_BEGIN_DATE, :P_BOOKING_NO,"
							+ " :P_OFFENDER_ID, :P_ROOT_OFF_ID, :P_DISCLOSURE_FLAG, :P_IN_OUT_STATUS, :P_BOOKING_STATUS, :P_YOUTH_ADULT_CODE, "
							+ " :P_BOOKING_TYPE, :P_CREATE_AGY_LOC_ID, :P_BOOKING_CREATED_DATE, :P_STAFFID, :P_CASELOAD_ID, "
							+ " :P_COMMUNITY_ACTIVE_FLAG, :P_INTAKE_AGY_LOC_ID, :P_COMM_STATUS) ", inParamMap);
			genSeq = 1;
		} catch (Exception e) {
			logger.error("instOffenderBooking :" + e);
		}
		return genSeq;

	}

	@Override
	public Integer copyBookingData(final OffenderBookingEvent object) {
		Map<String, Object> returnObject = null;
		Integer returnValue = 0;
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[6];
		sqlParameters = new SqlParameter[] { new SqlParameter("P_MOVE_TYPE", OracleTypes.VARCHAR),
				new SqlParameter("P_MOVE_REASON", OracleTypes.VARCHAR),
				new SqlParameter("P_OLD_BOOK_ID", OracleTypes.NUMBER),
				new SqlParameter("P_NEW_BOOK_ID", OracleTypes.NUMBER),
				new SqlParameter("P_RETURN_TEXT", OracleTypes.VARCHAR),
				new SqlParameter("V_PARENT", OracleTypes.VARCHAR) };

		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("OMKCOPY").withProcedureName("COPY_BOOKING_DATA").declareParameters(sqlParameters);
		inParamMap.put("P_MOVE_TYPE", "CRI");
		inParamMap.put("P_MOVE_REASON", object.getReasonCode());
		inParamMap.put("P_OLD_BOOK_ID", object.getpBookIdOld());
		inParamMap.put("P_NEW_BOOK_ID", object.getOffenderBookId());
		inParamMap.put("P_RETURN_TEXT", null);
		inParamMap.put("V_PARENT", null);

		final SqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		try {
			returnObject = simpleJDBCCall.execute(inParameter);
			if (returnObject.size() == 0) {
				returnValue = 1;
			}
		} catch (Exception e) {
			returnValue = 0;
		}
		return returnValue;
	}

	public String onCommitcommunityTrustCaseloadId(String caseloadId) {
		String sql = getQuery("OCDINTAK_COMMUNITY_TRUST_CASELOAD_CASELOAD_ID");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("caseloadId", caseloadId), String.class);
	}

	@Override
	public Integer processOcdintakTrust(final OffenderBookingEvent object) {
		Map<String, Object> returnObject = null;
		Integer returnValue = 0;
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[9];
		sqlParameters = new SqlParameter[] { new SqlParameter("P_CSLD_ID", OracleTypes.VARCHAR),
				new SqlParameter("P_OFF_ID", OracleTypes.NUMBER), new SqlOutParameter("P_TXN_ID", OracleTypes.NUMBER),
				new SqlParameter("P_TXN_SEQ", OracleTypes.NUMBER),
				new SqlParameter("P_TRIGGER_BLK", OracleTypes.VARCHAR),
				new SqlOutParameter("P_RECEIPT_NO", OracleTypes.VARCHAR),
				new SqlOutParameter("P_TXN_PTG_TYPE", OracleTypes.VARCHAR),
				new SqlOutParameter("P_SUBAC_TYPE", OracleTypes.VARCHAR),
				new SqlOutParameter("P_TXN_DESC", OracleTypes.VARCHAR) };

		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("OCDINTAK").withProcedureName("PROCESS_OCDINTAK_TRUST")
				.declareParameters(sqlParameters);
		inParamMap.put("P_CSLD_ID", object.getCaseloadId());
		inParamMap.put("P_OFF_ID", object.getOffenderId());
		inParamMap.put("P_TXN_SEQ", 1);
		inParamMap.put("P_TRIGGER_BLK", "OFF_BKGE");

		final SqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		try {
			returnObject = simpleJDBCCall.execute(inParameter);
			if (returnObject.size() == 0) {
				returnValue = 1;
			}
		} catch (Exception e) {

		}
		return returnValue;
	}

	@Override
	public OffenderBookingEvent onCommitTwo(final OffenderBookingEvent object) {
		Map<String, Object> returnObject = null;
		OffenderBookingEvent returnValue = new OffenderBookingEvent();
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[9];
		sqlParameters = new SqlParameter[] { new SqlParameter("P_CSLD_ID", OracleTypes.VARCHAR),
				new SqlParameter("P_OFF_ID", OracleTypes.NUMBER), new SqlOutParameter("P_TXN_ID", OracleTypes.NUMBER),
				new SqlParameter("P_TXN_SEQ", OracleTypes.NUMBER),
				new SqlParameter("P_TRIGGER_BLK", OracleTypes.VARCHAR),
				new SqlOutParameter("P_RECEIPT_NO", OracleTypes.VARCHAR),
				new SqlOutParameter("P_TXN_PTG_TYPE", OracleTypes.VARCHAR),
				new SqlOutParameter("P_SUBAC_TYPE", OracleTypes.VARCHAR),
				new SqlOutParameter("P_TXN_DESC", OracleTypes.VARCHAR) };

		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("OCDINTAK").withProcedureName("PROCESS_OCDINTAK_TRUST")
				.declareParameters(sqlParameters);
		inParamMap.put("P_CSLD_ID", object.getCaseloadId());
		inParamMap.put("P_OFF_ID", object.getRootOffenderId());
		inParamMap.put("P_TXN_ID", null);
		inParamMap.put("P_TXN_SEQ", 1);
		inParamMap.put("P_TRIGGER_BLK", "OFF_BKGE");
		inParamMap.put("P_RECEIPT_NO", null);
		inParamMap.put("P_TXN_PTG_TYPE", null);
		inParamMap.put("P_SUBAC_TYPE", null);
		inParamMap.put("P_TXN_DESC", null);

		final SqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		try {
			returnObject = (Map<String, Object>) simpleJDBCCall.withoutProcedureColumnMetaDataAccess()
					.execute(inParameter);
			final List<Map<String, String>> list = (List<Map<String, String>>) returnObject.get("resultset");
			for (int i = 0; i < list.size(); i++) {
				final Map<String, String> childMap = list.get(i);
				returnValue.setCaseloadId(childMap.get("P_TXN_ID"));
				returnValue.setBookingType(childMap.get("P_RECEIPT_NO"));
				returnValue.setCreateUserId(childMap.get("P_TXN_PTG_TYPE"));
				returnValue.setEventUser(childMap.get("P_SUBAC_TYPE"));
				returnValue.setCommentText(childMap.get("P_TXN_DESC"));
			}
		} catch (Exception e) {
			returnValue.setSealFlag("2");
			return returnValue;
		}
		return returnValue;
	}

	public Integer insOffSchedule(final OffenderResidence object) {
		Integer genSeq = 0;
		Integer offBookId = null;
		if (object.getOffenderBookId() != null && object.getOffenderBookId() != 0) {
			offBookId = Integer.valueOf(object.getOffenderBookId().toString());
		} else {
			OffenderBookingEvent obj = new OffenderBookingEvent();
			BigDecimal offId = null;
			if (object.getRootOffenderId() != null) {
				offId = object.getRootOffenderId();
				obj.setRootOffenderId(Long.valueOf(offId.toString()));
				offBookId = getLatestBooking(obj);
			}

		}

		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		inParamMap.put("p_offender_book_id", offBookId);
		inParamMap.put("p_event_date", object.getMovedOutDate());
		inParamMap.put("p_start_time", object.getMovedOutTime());
		inParamMap.put("p_agy_loc_id", object.getCreateAgyLocId());
		inParamMap.put("p_direction_code", "IN");
		inParamMap.put("p_confirm_flag", null);
		inParamMap.put("p_event_type", "APP");
		inParamMap.put("p_event_sub_type", "REPORT_IN");
		inParamMap.put("p_comment_text", object.getContactName());
		try {
			namedParameterJdbcTemplate.update(
					" CALL OMS_OWNER.OCDINTAK.INS_OFF_SCH(:p_offender_book_id, :p_event_date, :p_start_time,"
							+ " :p_agy_loc_id, :p_direction_code, :p_confirm_flag, :p_event_type, :p_event_sub_type, :p_comment_text) ",
					inParamMap);
			genSeq = 1;
		} catch (Exception e) {
			logger.error("insOffSchedule :" + e);
		}
		return genSeq;
	}

	public String newContactCur(String userId) {
		final String sql = getQuery("NEW_CONTACT_CAS_CUR");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("USER_ID",userId), String.class);
	}

	public Integer newContactCheckBookingCur(final OffenderBookingEvent obj) {
		final String sql = getQuery("NEW_CONTACT_CAS_CUR_CHECK_BOOKING_CUR");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("rootOffenderId", obj.getRootOffenderId()),
				Integer.class);
	}

	public String getCommStatusOne() {
		final String sql = getQuery("NEW_CONTACT_LV_COMM_STATUS1");
		String returnData = null;
		try {
			returnData = namedParameterJdbcTemplate.queryForObject(sql, createParams(), String.class);
		} catch (Exception e) {
			return returnData;
		}
		return returnData;
	}

	public String getCommStatusTwo() {
		final String sql = getQuery("NEW_CONTACT_LV_COMM_STATUS2");
		String returnData = null;
		try {
			returnData = namedParameterJdbcTemplate.queryForObject(sql, createParams(), String.class);
		} catch (Exception e) {
			return returnData;
		}
		return returnData;
	}

	public Integer getlvsequence() {
		final String sql = getQuery("NEW_CONTACT_LV_SEQUENCE");
		Integer returnVal = null;
		try {
			returnVal = namedParameterJdbcTemplate.queryForObject(sql, createParams(), Integer.class);
		} catch (Exception e) {
			return returnVal;
		}
		return returnVal;
	}

	public Integer offBkgeInsertOffenderBookingEvents(final OffenderBookingEvent lstOffenderBookingEvents) {
		final String sql = getQuery("OCDINTAK_OFFBKGE_INSERT_OFFENDER_BOOKING_EVENTS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		parameters.add(new BeanPropertySqlParameterSource(lstOffenderBookingEvents));
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (returnArray.length == 1) {
			return 1;
		} else {
			return 0;
		}
	}

	public Integer updateFeeAccounts(final List<FeeAccountProfiles> updateList) {
		final String sql = getQuery("OCDINTAK_UPDATE_FEE_ACCOUNTS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<>();
		for (final FeeAccountProfiles bean : updateList) {
			parameters.add(new BeanPropertySqlParameterSource(bean));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (returnArray.length == 1) {
			return 1;
		} else {
			return 0;
		}
	}
	
	
	@Override
	public String getBackdatedAdmissionDate() {
		final String sql = getQuery("OCDINTAK_GET_COM_ADM_BKDT_SYS_PROF_VAL");
		String returnData = null;
		try {
			returnData = namedParameterJdbcTemplate.queryForObject(sql, createParams(), String.class);
		} catch (Exception e) {
			return returnData;
		}
		return returnData;
	}

	@Override
	public ReferenceCodes getBillableFlag(String supStatus) {
		final String sql = getQuery("OCDINTAK_GET_BILLABLE_FLAG_VALUE");
		final RowMapper<ReferenceCodes> refsRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				referenceCodesMapping);
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("CODE", supStatus), refsRowMapper);
	}
	
	@Override
	public String getCustodyStatus() {
		final String sql = getQuery("OCDINTAK_GET_CUSTODY_STATUS");
		String returnData = "";
		try {
			returnData = namedParameterJdbcTemplate.queryForObject(sql, createParams(), String.class);
		} catch (Exception e) {
			logger.error("In getCustodyStatus:", e);
			return returnData;
		}
		return returnData;
	}
	
	@Override
	public Integer insertCustodyStatus(OffenderCustodyStatus offenderCustodyStatus) {
		final String sql = getQuery("OCDINTAK_INSERT_CUSTODY_STATUS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<>();
		parameters.add(new BeanPropertySqlParameterSource(offenderCustodyStatus));
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (returnArray.length > 0) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public Integer updateCustodyStatus(List<OffenderCustodyStatus> offenderCustodyStatusList) {
		final String sql = getQuery("OCDINTAK_UPDATE_CUSTODY_STATUS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<>();
		for (final OffenderCustodyStatus offenderCustodyStatus : offenderCustodyStatusList) {
			parameters.add(new BeanPropertySqlParameterSource(offenderCustodyStatus));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (returnArray.length > 0) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public byte[] getSentences(String custFormIdentifier) {
		final String sql = getQuery("OCDINTAK_GET_SENTENCES");
		byte[] returnData = null;
		try {
			returnData = namedParameterJdbcTemplate.queryForObject(sql, createParams("formIdentifier", custFormIdentifier), byte[].class);
		} catch (Exception e) {
			logger.error("In getSentences:", e);
			return returnData;
		}
		return returnData;
	}

	@Override
	public List<String> getCustodyStatusForOrder(String orderStatus, String type) {
		final String sql = getQuery("OCDINTAK_GET_CUSTODY_STATUS_FOR_ORDER");
		List<String> statusList = new ArrayList<String>();
		Map<String, String> params = new HashMap<String, String>();
		params.put("orderStatus", orderStatus);
		params.put("sentenceCalcType", type);
		try {
			statusList = namedParameterJdbcTemplate.queryForList(sql, params, String.class);
		} catch (Exception e) {
			logger.error("In getCustodyStatusForOrder:", e);
			return statusList;
		}
		return statusList;
	}

	@Override
	public List<LegalCustodyStatuses> getLegalCustodyStatuses(List<String> orderStatusList) {
		final String sql = getQuery("OCDINTAK_GET_LEGAL_CUSTODY_STATUSES");
		ArrayList<LegalCustodyStatuses> returnList = new ArrayList<LegalCustodyStatuses>();
		try { 
			final RowMapper<LegalCustodyStatuses> legalCustodyStatusesRowMapper = Row2BeanRowMapper.makeMapping(sql, LegalCustodyStatuses.class,
					legalCustodyStatusesMapping);
			returnList = (ArrayList<LegalCustodyStatuses>) namedParameterJdbcTemplate.query(sql,
					createParams("orderStatusList", orderStatusList), legalCustodyStatusesRowMapper);
		} catch (Exception e) {
			logger.error("In getLegalCustodyStatuses:", e);
			return returnList;
		}
		return returnList;
	}
	
	@Override
	public String getOldOffenderBookId(OffenderBookingEvent object) {
		final String sql = getQuery("OCDINTAK_GET_OLDOFFENDER_BOOK_ID");
		String offenderBookId = "";
		try {
			offenderBookId = namedParameterJdbcTemplate.queryForObject(sql, createParams("rootOffenderId", object.getRootOffenderId()), String.class);
		} catch (Exception e) {
			logger.error(this.getClass().getName(), " getOldOffenderBookId and  Exception ", e);
			return offenderBookId;
		}
		return offenderBookId;
	}

	@Override
	public List<OdynfrmSubmitDataBean> getLegalSummaryData(String formIdentifier) {
		final String sql = getQuery("OCDINTAK_GET_LEGALSUMNMARY_DATA");
		List<OdynfrmSubmitDataBean> returnList = new ArrayList<OdynfrmSubmitDataBean>();
		try {
			final RowMapper<OdynfrmSubmitDataBean> uiBeanMapper = Row2BeanRowMapper.makeMapping(sql, OdynfrmSubmitDataBean.class,
					uiBeanMapping);
			returnList = (ArrayList<OdynfrmSubmitDataBean>) namedParameterJdbcTemplate.query(sql,
					createParams("formIdentifier", formIdentifier), uiBeanMapper);
		} catch(Exception e) {
			logger.error(this.getClass().getName(), " getLegalSummaryData and  Exception ", e);
		}
		return returnList;
	}

	
}

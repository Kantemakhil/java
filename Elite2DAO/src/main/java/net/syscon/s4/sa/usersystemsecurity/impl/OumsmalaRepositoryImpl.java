package net.syscon.s4.sa.usersystemsecurity.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
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
import net.syscon.s4.common.beans.Caseloads;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.ReferenceCodes;
import net.syscon.s4.sa.usersystemsecurity.OumsmalaRepository;
import net.syscon.s4.sa.usersystemsecurity.beans.AssignmentTransfers;
import net.syscon.s4.sa.usersystemsecurity.beans.OffenderWorkAssignments;
import net.syscon.s4.sa.usersystemsecurity.beans.StaffLocationRoles;

/**
 * Class OumsmalaRepositoryImpl
 * 
 */
@Repository
public class OumsmalaRepositoryImpl extends RepositoryBase implements OumsmalaRepository {

	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OumsmalaRepositoryImpl.class.getName());

	private final Map<String, FieldMapper> staffLocationRolesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CAL_AGY_LOC_ID", new FieldMapper("calAgyLocId"))
			.put("SAC_STAFF_ID", new FieldMapper("sacStaffId"))
			.put("FROM_DATE", new FieldMapper("fromDate"))
			.put("TO_DATE", new FieldMapper("toDate"))
			.put("POSITION", new FieldMapper("position"))
			.put("ROLE", new FieldMapper("role"))
			.put("SCHEDULE_TYPE", new FieldMapper("scheduleType"))
			.put("HOURS_PER_WEEK", new FieldMapper("hoursPerWeek"))
			.put("SUPERVISOR_AGY_LOC_ID", new FieldMapper("supervisorAgyLocId"))
			.put("SUPERVISOR_STAFF_ID", new FieldMapper("supervisorStaffId"))
			.put("SUPERVISOR_FROM_DATE", new FieldMapper("supervisorFromDate"))
			.put("SUPERVISOR_POSITION", new FieldMapper("supervisorPosition"))
			.put("SUPERVISOR_ROLE", new FieldMapper("supervisorRole"))
			.put("STAFF_UNIT", new FieldMapper("staffUnit"))
			.put("CREATE_DATETIME", new FieldMapper("createDatetime"))
			.put("CREATE_USER_ID", new FieldMapper("createUserId"))
			.put("MODIFY_USER_ID", new FieldMapper("modifyUserId"))
			.put("SEAL_FLAG", new FieldMapper("sealFlag"))
			.put("ROWID", new FieldMapper("rowid"))
			.build();
	private final Map<String, FieldMapper> StaffAccessibleCaseloadsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("STAFF_ID", new FieldMapper("staffId"))
			.put("CASELOAD_ID", new FieldMapper("caseloadId"))
			.put("CREATE_DATETIME", new FieldMapper("createDatetime"))
			.put("MODIFY_DATETIME", new FieldMapper("modifyDatetime"))
			.build();
	
	private final Map<String, FieldMapper> referenceCodesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("LIST_SEQ", new FieldMapper("listSeq"))
			.put("ROLE", new FieldMapper("role"))
			.put("SCHEDULE_TYPE", new FieldMapper("scheduleType"))
			.put("POSITION", new FieldMapper("position"))
			.put("DESCRIPTION", new FieldMapper("description"))
			.put("MODE", new FieldMapper("mode"))
			.build();
	private final Map<String, FieldMapper> offenderWorkAssignmentsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("'1'", new FieldMapper("'1'"))
			.build();
	private final Map<String, FieldMapper> assignmentTransfersMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CREATE_DATETIME", new FieldMapper("createDatetime"))
			.put("MODIFY_DATETIME", new FieldMapper("modifyDatetime"))
			.build();
	private final Map<String, FieldMapper> mMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("LIST_SEQ", new FieldMapper("listSeq"))
			.put("CODE", new FieldMapper("code"))
			.put("AGY_LOC_ID", new FieldMapper("agyLocId"))
			.put("DESCRIPTION", new FieldMapper("description"))
			.put("CASELOAD_ID", new FieldMapper("caseloadId"))
			.put("CREATE_DATETIME", new FieldMapper("createDatetime"))
			.put("MODIFY_DATETIME", new FieldMapper("modifyDatetime"))
			
			.build();
	private final Map<String, FieldMapper> agencyLocationsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("AGENCY_LOCATION_TYPE", new FieldMapper("agencyLocationType"))
			.put("AGY_LOC_ID", new FieldMapper("agyLocId"))
			.put("DESCRIPTION", new FieldMapper("description"))
			.put("DSP_DESCRIPTION", new FieldMapper("dspDescription"))
			.put("DSP_AGENCY_LOCATION_TYPE", new FieldMapper("dspAgencyLocationType"))
			.build();
	private final Map<String, FieldMapper> staffMembersMapping = new ImmutableMap.Builder<String, FieldMapper>()
			 .put("PERSONNEL_TYPE", 			new FieldMapper("personnelType"))
             .put("DEFAULT_PRINTER_ID", 		new FieldMapper("defaultPrinterId"))
             .put("ROLE", 						new FieldMapper("role"))
             .put("SUFFIX", 				    new FieldMapper("suffix"))
             .put("TERMINATION_DATE", 			new FieldMapper("terminationDate"))
             .put("AS_OF_DATE", 				new FieldMapper("asOfDate"))
             .put("MIDDLE_NAME", 				new FieldMapper("middleName"))
             .put("COMM_RECEIPT_PRINTER_ID", 	new FieldMapper("commReceiptPrinterId"))
             .put("LAST_NAME", 					new FieldMapper("lastName"))
             .put("POSITION", 					new FieldMapper("position"))
             .put("SEAL_FLAG", 					new FieldMapper("sealFlag"))
             .put("CREATE_DATETIME", 			new FieldMapper("createDateTime"))
             .put("FORCE_PASSWORD_CHANGE_FLAG", new FieldMapper("forcePasswordChangeFlag"))
             .put("MODIFY_DATETIME", 			new FieldMapper("modifyDateTime"))
             .put("WORKING_CASELOAD_ID", 		new FieldMapper("workingCaseloadId"))
             .put("STATUS", 					new FieldMapper("status"))
             .put("SUSPENDED_FLAG", 			new FieldMapper("suspendedFlag"))
             .put("ASSIGNED_CASELOAD_ID", 		new FieldMapper("assignedCaseloadId"))
             .put("CREATE_USER_ID", 			new FieldMapper("createUserId"))
             .put("MODIFY_USER_ID", 			new FieldMapper("modifyUserId"))
             .put("FIRST_LOGON_FLAG", 			new FieldMapper("firstLogonFlag"))
             .put("USER_ID", 					new FieldMapper("userId"))
             .put("LAST_PASSWORD_CHANGE_DATE", 	new FieldMapper("lastPasswordChangeDate"))
             .put("STAFF_ID", 					new FieldMapper("staffId"))
             .put("SUPERVISOR_STAFF_ID", 		new FieldMapper("supervisorStaffId"))
             .put("SUSPENSION_REASON", 			new FieldMapper("suspensionReason"))
             .put("NAME_SEQUENCE", 				new FieldMapper("nameSequence"))
             .put("ABBREVIATION", 				new FieldMapper("abbreviation"))
             .put("LICENSE_EXPIRY_DATE", 		new FieldMapper("licenseExpiryDate"))
             .put("BIRTHDATE", 					new FieldMapper("birthdate"))
             .put("TITLE", 						new FieldMapper("title"))
             .put("SEX_CODE", 					new FieldMapper("sexCode"))
             .put("SUSPENSION_DATE", 			new FieldMapper("suspensionDate"))
             .put("BADGE_ID", 					new FieldMapper("badgeId"))
             .put("WORKING_STOCK_LOC_ID", 		new FieldMapper("workingStockLocId"))
             .put("UPDATE_ALLOWED_FLAG", 		new FieldMapper("updateAllowedFlag"))
             .put("LICENSE_CODE", 				new FieldMapper("licenseCode"))
             .put("EMERGENCY_CONTACT", 			new FieldMapper("emergencyContact"))
             .put("QUEUE_CLUSTER_ID", 			new FieldMapper("queueClusterId"))
             .put("FIRST_NAME", 				new FieldMapper("firstName"))
             .put("MAIL_ID", 					new FieldMapper("mailId"))
             .build();

	/**
	 * Creates new OumsmalaRepositoryImpl class Object
	 */
	public OumsmalaRepositoryImpl() {
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            StaffMembers
	 *
	 * @return List<StaffMembers>
	 *
	 * 
	 */
	public List<StaffMembers> smExecuteQuery(final StaffMembers objSearchDao) {
		final String sql = getQuery("OUMSMALA_SM_FIND_STAFF_MEMBERS");
		final RowMapper<StaffMembers> StaffMembersRowMapper = Row2BeanRowMapper.makeMapping(sql, StaffMembers.class,
				staffMembersMapping);
		String preparedSql = null;
		final StringBuffer sqlQuery = new StringBuffer();
		final MapSqlParameterSource valuesList = new MapSqlParameterSource();
		sqlQuery.append(sql);
		if (objSearchDao != null) {
			sqlQuery.append(" where ");

			if (objSearchDao.getLastName() != null) {
				sqlQuery.append("LAST_NAME =  :lastName " + " and ");
				valuesList.addValue("lastName", objSearchDao.getLastName());
			}

			if (objSearchDao.getFirstName() != null) {
				sqlQuery.append("FIRST_NAME =  :firstName " + " and ");
				valuesList.addValue("firstName", objSearchDao.getFirstName());
			}
			if (objSearchDao.getMiddleName() != null) {
				sqlQuery.append("MIDDLE_NAME =  :middleName " + " and ");
				valuesList.addValue("middleName", objSearchDao.getMiddleName());
			}

			if (objSearchDao.getBirthdate() != null) {
				sqlQuery.append("BIRTHDATE =  :birthdate " + " and ");
				valuesList.addValue("birthdate", objSearchDao.getBirthdate());
			}
			if (objSearchDao.getStaffId() != null) {
				sqlQuery.append("STAFF_ID =  :staffId " + " and ");
				valuesList.addValue("staffId", objSearchDao.getStaffId());
			}
			if (objSearchDao.getUserId() != null) {
				sqlQuery.append("USER_ID =  :userId " + " and ");
				valuesList.addValue("userId", objSearchDao.getUserId());
			}
			
			if (objSearchDao.getSuspendedFlag() != null) {
				sqlQuery.append("SUSPENDED_FLAG =  :suspendedFlag " + " and ");
				valuesList.addValue("suspendedFlag", objSearchDao.getSuspendedFlag());
			}
			if (objSearchDao.getTerminationDate() != null) {
				sqlQuery.append("TERMINATION_DATE =  :terminationDate " + " and ");
				valuesList.addValue("terminationDate", objSearchDao.getTerminationDate());
			}
		}
		preparedSql = sqlQuery.toString().trim();
		if (preparedSql.endsWith("where")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 5);
		}
		if (preparedSql.endsWith("and")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 3);
		}
		List<StaffMembers> returnList = new ArrayList<StaffMembers>();
		try {
			returnList = namedParameterJdbcTemplate.query(preparedSql,valuesList, StaffMembersRowMapper);
		} catch (Exception e) {
			logger.error("smExecuteQuery", e);
		}
		return returnList;
	}


	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            CaseloadAgencyLocations
	 *
	 * @return List<calExecuteQuery>
	 *
	 */
	public List<AgencyLocations> calExecuteQuery(final AgencyLocations objSearchDao) {
		final String sql = getQuery("OUMSMALA_FIND_CGFKCALAGYLOCID");
		final RowMapper<AgencyLocations> StaffLocationRolesRowMapper = Row2BeanRowMapper.makeMapping(sql,
				AgencyLocations.class, agencyLocationsMapping);
		List<AgencyLocations> returnList = new ArrayList<AgencyLocations>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams("STAFFID", objSearchDao.getStaffId()), StaffLocationRolesRowMapper);
		} catch (Exception e) {
			logger.error("calExecuteQuery", e);
		}

		return returnList;
	}
	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            StaffLocationRoles
	 *
	 * @return List<StaffLocationRoles>
	 *
	 */
	public List<StaffLocationRoles> slrExecuteQuery(final StaffLocationRoles objSearchDao) {
		final String sql = getQuery("OUMSMALA_SLR_FIND_STAFF_LOCATION_ROLES");
		final RowMapper<StaffLocationRoles> StaffLocationRolesRowMapper = Row2BeanRowMapper.makeMapping(sql,
				StaffLocationRoles.class, staffLocationRolesMapping);
		List<StaffLocationRoles> returnList = new ArrayList<StaffLocationRoles>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql,
					createParams("STAFFID", objSearchDao.getSacStaffId(), "AGYLOCID", objSearchDao.getCalAgyLocId()),
					StaffLocationRolesRowMapper);
		} catch (Exception e) {
			logger.error("slrExecuteQuery", e);
		}

		return returnList;
	}

	/**
	 * This method is used to insert the records in the data base tables based
	 * on
	 *
	 * @param lstStaffLocationRoles
	 *            List<StaffLocationRoles>
	 *
	 * @return List<Integer>
	 *
	 */
	public StaffLocationRoles slrInsertStaffLocationRoles(final List<StaffLocationRoles> lstStaffLocationRoles) {
		String sql = getQuery("OUMSMALA_SLR_INSERT_STAFF_LOCATION_ROLES");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		StaffLocationRoles returnData = new StaffLocationRoles();
		for (StaffLocationRoles staffLocationRoles : lstStaffLocationRoles) {
			parameters.add(new BeanPropertySqlParameterSource(staffLocationRoles));
		}try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		}catch (Exception e){
			String error = "Error : " + e.getMessage();
			if (error.contains("constraint")) {
				error = error.substring(error.indexOf("constraint"), error.indexOf("Detail"))
						.replaceFirst("constraint", "").trim();
				final String tableName = errorNameValidation( error.substring(1, error.length()-1));

				returnData.setSealFlag(tableName);
				returnData.setListSeq(BigDecimal.valueOf(2291));
				return returnData;
			}
		}
		if (lstStaffLocationRoles.size() == returnArray.length) {
			returnData.setSealFlag("1");
			return returnData;
		} else {
			returnData.setSealFlag("0");
			return returnData;
		}

	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstStaffLocationRoles
	 *            List<StaffLocationRoles>
	 *
	 */
	public StaffLocationRoles slrUpdateStaffLocationRoles(final List<StaffLocationRoles> lstStaffLocationRoles) {
		String sql = getQuery("OUMSMALA_SLR_UPDATE_STAFF_LOCATION_ROLES");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		StaffLocationRoles returnData = new StaffLocationRoles();
		for (StaffLocationRoles staffLocationRoles : lstStaffLocationRoles) {
			parameters.add(new BeanPropertySqlParameterSource(staffLocationRoles));
		}try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		}catch (Exception e) {
			String error = "Error : " + e.getMessage();
			if (error.contains("constraint")) {
				error =  error.substring(error.indexOf("constraint"), error.indexOf("\" on"))
						.replaceFirst("constraint", "").trim();
					final String tableName = errorNameValidation( error.substring(1, error.length()));

				returnData.setSealFlag(tableName);
				returnData.setListSeq(BigDecimal.valueOf(2292));
				return returnData;
		}
		}
		if (lstStaffLocationRoles.size() == returnArray.length) {
			returnData.setSealFlag("1");
			return returnData;
		} else {
			returnData.setSealFlag("0");
			return returnData;
		}
	}
	public String errorNameValidation(final String errorName) {
		final String sql = getQuery("OUMSMALA_CONSTRAINT_VALIDATIONS");
		String returnData = null;
		try {
			returnData = namedParameterJdbcTemplate.queryForObject(sql, createParams("CONSTRAINTNAME", errorName),
					String.class);
		} catch (Exception e) {
			returnData = null;
			return returnData;
		}
		return returnData;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<ReferenceCodes> cgfkSlrPositionRecordGroup() {
		final String sql = getQuery("OUMSMALA_FIND_CGFKSLRPOSITION");
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
	public List<ReferenceCodes> cgfkSlrRoleRecordGroup() {
		final String sql = getQuery("OUMSMALA_FIND_CGFKSLRROLE");
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
	public List<ReferenceCodes> cgfkSlrStaffUnitRecordGroup() {
		final String sql = getQuery("OUMSMALA_FIND_CGFKSLRSTAFFUNIT");
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
	public List<ReferenceCodes> cgfkSlrScheduleTypeRecordGroup() {
		final String sql = getQuery("OUMSMALA_FIND_CGFKSLRSCHEDULETYPE");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	/**
//	 * Used to capture results from select query
//	 * 
//	 * @return List<M>
//	 */
	public List<AgencyLocations> cgfkCalAgyLocIdRecordGroup(final Integer staffid) {
		final String sql = getQuery("OUMSMALA_FIND_CGFKCALAGYLOCID");
		final RowMapper<AgencyLocations> mRowMapper = Row2BeanRowMapper.makeMapping(sql, AgencyLocations.class,
				mMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams("STAFFID", staffid), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<Caseloads> cgfkSacCaseloadIdRecordGroup() {
		final String sql = getQuery("OUMSMALA_FIND_CGFKSACCASELOADID");
		final RowMapper<Caseloads> mRowMapper = Row2BeanRowMapper.makeMapping(sql, Caseloads.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfkchkCalCsldAlAgyLoc
	 *
	 * @param params
	 *
	 */
	public List<AgencyLocations> cgfkchkCalCsldAlAgyLoc(AgencyLocations paramBean) {
		final String sql = getQuery("OUMSMALA_CGFKCHK_CAL_CSLD_AL_AGY_LOC_F");
		final RowMapper<AgencyLocations> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, AgencyLocations.class,
				agencyLocationsMapping);
		List<AgencyLocations> returnList = new ArrayList<AgencyLocations>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams("AGYLOCID", paramBean.getAgyLocId()),
					columnRowMapper);
		} catch (Exception e) {
			logger.error("cgfkchkCalCsldAlAgyLoc", e);
		}

		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfkchkSlrCSlrSchedType
	 *
	 * @param params
	 *
	 */
	public List<ReferenceCodes> cgfkchkSlrCSlrSchedType(ReferenceCodes paramBean) {
		final String sql = getQuery("OUMSMALA_CGFKCHK_SLR_C_SLR_SCHED_TYPE_");
		final RowMapper<ReferenceCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				referenceCodesMapping);
		List<ReferenceCodes> returnList = new ArrayList<ReferenceCodes>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams(), columnRowMapper);
		} catch (Exception e) {
			logger.error("cgfkchkSlrCSlrSchedType", e);
		}
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfkchkSlrCSlrRoleFk
	 *
	 * @param params
	 *
	 */
	public List<ReferenceCodes> cgfkchkSlrCSlrRoleFk(ReferenceCodes paramBean) {
		final String sql = getQuery("OUMSMALA_CGFKCHK_SLR_C_SLR_ROLE_FK");
		final RowMapper<ReferenceCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				referenceCodesMapping);
		List<ReferenceCodes> returnList = new ArrayList<ReferenceCodes>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams(), columnRowMapper);
		} catch (Exception e) {
			logger.error("", e);
		}
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfkchkSlrCSlrPosnFk
	 *
	 * @param params
	 *
	 */
	public List<ReferenceCodes> cgfkchkSlrCSlrPosnFk(ReferenceCodes paramBean) {
		final String sql = getQuery("OUMSMALA_CGFKCHK_SLR_C_SLR_POSN_FK");
		final RowMapper<ReferenceCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				referenceCodesMapping);
		List<ReferenceCodes> returnList = new ArrayList<ReferenceCodes>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams(), columnRowMapper);
		} catch (Exception e) {
			logger.error("", e);
		}
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cguvchkSlrPk
	 *
	 * @param params
	 *
	 */
	public Integer cguvchkSlrPk(final StaffLocationRoles paramBean) {
		final String sql = getQuery("OUMSMALA_CGUVCHK_SLR_PK");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("PCALAGYLOCID",paramBean.getCalAgyLocId(),"SACSTAFFID",paramBean.getSacStaffId(),
				"POSITION",paramBean.getPosition(),"ROLE",paramBean.getRole(),"FROMDATE",paramBean.getFromDate()),
				Integer.class);

	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgrichkStaffLocationRoles
	 *
	 * @param params
	 *
	 */
	public List<OffenderWorkAssignments> cgrichkStaffLocationRoles(OffenderWorkAssignments paramBean) {
		final String sql = getQuery("OUMSMALA_CGRICHK_STAFF_LOCATION_ROLES");
		final RowMapper<OffenderWorkAssignments> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderWorkAssignments.class, offenderWorkAssignmentsMapping);
		final ArrayList<OffenderWorkAssignments> returnList = (ArrayList<OffenderWorkAssignments>) namedParameterJdbcTemplate
				.query(sql, createParams(), columnRowMapper);
		return returnList;
	}


	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgrichkStaffLocationRoles
	 *
	 * @param params
	 *
	 */
	public List<AssignmentTransfers> cgrichkStaffLocationRoles(final AssignmentTransfers paramBean) {
		final String sql = getQuery("OUMSMALA_CGRICHK_STAFF_LOCATION_ROLES");
		final RowMapper<AssignmentTransfers> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				AssignmentTransfers.class, assignmentTransfersMapping);
		final ArrayList<AssignmentTransfers> returnList = (ArrayList<AssignmentTransfers>) namedParameterJdbcTemplate
				.query(sql, createParams(), columnRowMapper);
		return returnList;
	}

	@Override
	public List<AgencyLocations> cgfkCalAgyLocIdRecordGroup() {
		return null;
	}


	
}

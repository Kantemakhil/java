package net.syscon.s4.iwp.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.CaseloadAgencyLocations;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.iwp.OcmshierRepository;
import net.syscon.s4.sa.usersystemsecurity.beans.StaffLocationRoles;
@Repository
public class OcmshierRepositoryImpl extends RepositoryBase implements OcmshierRepository {

	/**
	 * Creates new OcmshierRepositoryImpl class Object
	 */
	public OcmshierRepositoryImpl() {
	}

	/**
	 * Logger object used to print the log in the file
	 */
	 
	private final Map<String, FieldMapper> staffLocR = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CREATE_USER_ID", 				   	new FieldMapper("createUserId"))
			.put("ROLE", 							new FieldMapper("role"))
			.put("MODIFY_USER_ID", 					new FieldMapper("modifyUserId"))
			.put("TO_DATE", 						new FieldMapper("toDate"))
			.put("SUPERVISOR_FROM_DATE",			new FieldMapper("supervisorFromDate"))
			.put("SUPERVISOR_STAFF_ID", 			new FieldMapper("supervisorStaffId"))
			.put("STAFF_UNIT",					    new FieldMapper("staffUnit"))
			.put("FROM_DATE", 						new FieldMapper("fromDate"))
			.put("SCHEDULE_TYPE", 					new FieldMapper("scheduleType"))
			.put("SUPERVISOR_AGY_LOC_ID", 			new FieldMapper("supervisorAgyLocId"))
			.put("SAC_STAFF_ID",				    new FieldMapper("sacStaffId"))
			.put("POSITION", 						new FieldMapper("position"))
			.put("SUPERVISOR_ROLE", 				new FieldMapper("supervisorRole"))
			.put("SEAL_FLAG", 						new FieldMapper("sealFlag"))
			.put("CREATE_DATETIME", 				new FieldMapper("createDatetime"))
			.put("HOURS_PER_WEEK", 					new FieldMapper("hoursPerWeek"))
			.put("CAL_AGY_LOC_ID", 					new FieldMapper("calAgyLocId"))
			.put("MODIFY_DATETIME",					new FieldMapper("modifyDatetime"))
			.put("SUPERVISOR_POSITION", 			new FieldMapper("supervisorPosition"))
			.put("LAST_NAME", 						new FieldMapper("lastName"))
			.put("FIRST_NAME", 						new FieldMapper("firstName"))
			.put("BIRTHDATE", 						new FieldMapper("birthdate"))
			.put("STAFF_ID", 						new FieldMapper("staffId"))
			.build();
	private final Map<String, FieldMapper> staffMMap  = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DSP_LAST_NAME", 					new FieldMapper("dspLastName"))
			.put("DSP_FIRST_NAME",					new FieldMapper("dspFirstName"))
			.put("AGY_LOC_ID", 						new FieldMapper("agyLocId"))
			.put("STAFF_ID", 						new FieldMapper("staffId"))
			.put("DSP_BIRTHDATE", 					new FieldMapper("dspBirthdate"))
			.put("BIRTHDATE", 						new FieldMapper("birthdate"))
			.put("LAST_NAME", 						new FieldMapper("lastName"))
			.put("FIRST_NAME", 						new FieldMapper("firstName"))
			.put("SAC_STAFF_ID", 					new FieldMapper("sacStaffId"))
			.build();
	private final Map<String, FieldMapper> referenceCodesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("SCHEDULE_TYPE",					 new FieldMapper("scheduleType"))
			.put("ROLE",							 new FieldMapper("role"))
			.put("POSITION", 						 new FieldMapper("position"))
			.put("DOMAIN", 							 new FieldMapper("domain"))
			.put("DESCRIPTION", 					 new FieldMapper("description"))
			.put("MODE", 							 new FieldMapper("mode"))
			.build();
	private final Map<String, FieldMapper> mMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CODE", 							new FieldMapper("code"))
			.put("DOMAIN", 							new FieldMapper("domain"))
			.put("AGY_LOC_ID", 						new FieldMapper("agyLocId"))
			.put("BIRTHDATE", 						new FieldMapper("birthdate"))
			.put("LAST_NAME",						new FieldMapper("lastName"))
			.put("FIRST_NAME", 						new FieldMapper("firstName"))
			.put("STAFF_ID", 						new FieldMapper("staffId"))
			.build();
	private final Map<String, FieldMapper> agencyLocationsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("AGY_LOC_ID", 						new FieldMapper("agyLocId"))
			.put("DESCRIPTION", 					new FieldMapper("description"))
			.put("CASELOAD_ID", 					new FieldMapper("caseloadId"))
			.build();	
	private final Map<String, FieldMapper> caseloadALM  = new ImmutableMap.Builder<String, FieldMapper>()
			.build();

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao CaseloadAgencyLocations
	 *
	 * @return List<CaseloadAgencyLocations>
	 *
	 * 
	 */
	public List<CaseloadAgencyLocations> calExecuteQuery(final CaseloadAgencyLocations objSearchDao) {
		final String sql = getQuery("OCMSHIER_CAL_FIND_CASELOAD_AGENCY_LOCATIONS");
		final RowMapper<CaseloadAgencyLocations>  rowMapper = Row2BeanRowMapper.makeMapping(sql,
				CaseloadAgencyLocations.class, caseloadALM);// caseloadAgencyLocationsMapping
		final ArrayList<CaseloadAgencyLocations> returnList = (ArrayList<CaseloadAgencyLocations>) namedParameterJdbcTemplate
				.query(sql, createParams(), rowMapper);
		return returnList;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @param caseloadid
	 * @return List<AgencyLocations>
	 */
	public List<AgencyLocations> calAgyLocIdRecordGroup(final String caseloadId) {
		final String sql = getQuery("OCMSHIER_FIND_CGFKCALAGYLOCID");
		final RowMapper<AgencyLocations> mRowMapper = Row2BeanRowMapper.makeMapping(sql, AgencyLocations.class,
				mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams("CASELOADID", caseloadId), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @param agyLocId String
	 * @return List<StaffMembers>
	 */
	public List<StaffMembers> staffLrDspLastNameRecordGroup(final String agyLocIdLov) {
		final String sql = getQuery("OCMSHIER_FIND_STAFFLRDSPLASTNAME");
		final RowMapper<StaffMembers> mRowMapper = Row2BeanRowMapper.makeMapping(sql, StaffMembers.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams("calAgyLocId", agyLocIdLov), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<ReferenceCodes>
	 */
	public List<ReferenceCodes> staffLrPositionRecordGroup() {
		final String sql = getQuery("OCMSHIER_FIND_STAFFLRPOSITION");
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
	 * @return List<ReferenceCodes>
	 */
	public List<ReferenceCodes> staffLrRoleRecordGroup() {
		final String sql = getQuery("OCMSHIER_FIND_STAFFLRROLE");
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
	public List<ReferenceCodes> staffLrScheduleTypeRecordGroup() {
		final String sql = getQuery("OCMSHIER_FIND_STAFFLRSCHEDULETYPE");
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
	public List<StaffMembers> staffLr1DspLastNameRecordGroup(final String agyLocIdLov) {
		final String sql = getQuery("OCMSHIER_FIND_STAFFLR1DSPLASTNAME");
		final RowMapper<StaffMembers> mRowMapper = Row2BeanRowMapper.makeMapping(sql, StaffMembers.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams("calAgyLocId", agyLocIdLov), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao StaffLocationRoles
	 *
	 * @return List<StaffLocationRoles>
	 *
	 * 
	 */
	public List<StaffLocationRoles> staffLrExecuteQuery(final StaffLocationRoles objSearchDao) {
		final String sql = getQuery("OCMSHIER_STAFFLR_FIND_STAFF_LOCATION_ROLES");
		final RowMapper<StaffLocationRoles> rowMapper = Row2BeanRowMapper.makeMapping(sql,
				StaffLocationRoles.class, staffLocR);
		final ArrayList<StaffLocationRoles> returnList = (ArrayList<StaffLocationRoles>) namedParameterJdbcTemplate
				.query(sql, createParams("CASELOADID", objSearchDao.getCalAgyLocId()), rowMapper);
		return returnList;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao StaffLocationRoles
	 *
	 * @return List<StaffLocationRoles>
	 *
	 * 
	 */
	@Override
	public List<StaffLocationRoles> staffLr1ExecuteQuery(final StaffLocationRoles searchRecord) {
		final String sql = getQuery("OCMSHIER_STAFFLR1_FIND_STAFF_LOCATION_ROLES");
		final RowMapper<StaffLocationRoles>  rowMapper = Row2BeanRowMapper.makeMapping(sql,
				StaffLocationRoles.class, staffLocR);
		final ArrayList<StaffLocationRoles> returnList = (ArrayList<StaffLocationRoles>) namedParameterJdbcTemplate
				.query(sql,
						createParams("sac_staff_id", searchRecord.getSacStaffId(), "position",
								searchRecord.getPosition(), "role", searchRecord.getRole(), "from_date",
								searchRecord.getFromDate(), "agy_loc_id", searchRecord.getCalAgyLocId()),
						rowMapper);
		return returnList;

	}

	public Integer deleteStaffLr1Officer(final StaffLocationRoles staffLocRoles) {
		final String sql = getQuery("OCMSHIER_REMOVE_OFFICER");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		parameters.add(new BeanPropertySqlParameterSource(staffLocRoles));
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
	final	int recordsize = returnArray.length;
		if (recordsize == 0) {
			return 0;
		} else {
			return 1;
		}
	}

	/**
	 * This method is used to insert the records in the data base tables based on
	 *
	 * @param lstCaseloadAgencyLocations List<CaseloadAgencyLocations>
	 *
	 * @return List<Integer>
	 *
	 * @throws SQLException
	 */
	public Integer calInsertCaseloadAgencyLocations(final List<CaseloadAgencyLocations> lstCaseloadAL ) {
		int insertCount = 0;
		final String sql = getQuery("OCMSHIER_CAL_INSERT_CASELOAD_AGENCY_LOCATIONS");
	    int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		for (int i = 0; i < returnArray.length; i++) {
			insertCount = insertCount++;
		}
		if (lstCaseloadAL.size() == insertCount) {
			return 1;
		} else {
			return 0;
		}

	}
	/**
	 * Used to capture results from select query
	 * 
	 * @return List<ReferenceCodes>
	 */
	public List<ReferenceCodes> cgfkStaffLr1PositionRecordGroup() {
		final String sql = getQuery("OCMSHIER_FIND_CGFKSTAFFLR1POSITION");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);
		return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<ReferenceCodes>
	 */
	public List<ReferenceCodes> cgfkStaffLr1ScheduleTypeRecordGroup() {
		final String sql = getQuery("OCMSHIER_FIND_CGFKSTAFFLR1SCHEDULETYPE");
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
	 * @return List<ReferenceCodes>
	 */
	public List<ReferenceCodes> cgfkStaffLr1RoleRecordGroup() {
		final String sql = getQuery("OCMSHIER_FIND_CGFKSTAFFLR1ROLE");
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
	 * cgfkchkCalCsldAlAgyLoc
	 *
	 * @param params
	 * @return AgencyLocations
	 *
	 */
	public AgencyLocations cgfkchkCalCsldAlAgyLoc(final AgencyLocations paramBean) {
		final String sql = getQuery("OCMSHIER_CGFKCHK_CAL_CSLD_AL_AGY_LOC_F");
		final RowMapper<AgencyLocations> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, AgencyLocations.class,
				agencyLocationsMapping);
		final AgencyLocations returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfkchkStaffLrStaffLrRef
	 *
	 * @param params
	 *@return ReferenceCodes
	 */
	public ReferenceCodes cgfkchkStaffLrStaffLrRef(final ReferenceCodes paramBean) {
		final String sql = getQuery("OCMSHIER_CGFKCHK_STAFF_LR_STAFF_LR_REF");
		final RowMapper<ReferenceCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				referenceCodesMapping);
		final ReferenceCodes returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfkchkStaffLrStaffLr
	 *
	 * @param params
	 * @return ReferenceCodes
	 *
	 */
	public ReferenceCodes cgfkchkStaffLrStaffLr(final ReferenceCodes paramBean) {
		final String sql = getQuery("OCMSHIER_CGFKCHK_STAFF_LR_STAFF_LR_3");
		final RowMapper<ReferenceCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				referenceCodesMapping);
		final ReferenceCodes returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfkchkStaffLrStaffLrSta
	 *
	 * @param params
	 * @return StaffMembers
	 *
	 */
	public StaffMembers cgfkchkStaffLrStaffLrSta(final StaffMembers paramBean) {
		final String sql = getQuery("OCMSHIER_CGFKCHK_STAFF_LR_STAFF_LR_STA");
		final RowMapper<StaffMembers> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, StaffMembers.class,
				staffMMap);
		final StaffMembers returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfklkpStaffLrStaffLrSta
	 *
	 * @param params
	 * @return StaffMembers
	 *
	 */
	public StaffMembers cgfklkpStaffLrStaffLrSta(final StaffMembers paramBean) {
		final String sql = getQuery("OCMSHIER_CGFKLKP_STAFF_LR_STAFF_LR_STA");
		final RowMapper<StaffMembers> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, StaffMembers.class,
				staffMMap);
		final StaffMembers returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfkchkStaffLr1StaffLrRe
	 *
	 * @param params
	 * @return ReferenceCodes
	 *
	 */
	public ReferenceCodes cgfkchkStaffLr1StaffLrRe(final ReferenceCodes paramBean) {
		final String sql = getQuery("OCMSHIER_CGFKCHK_STAFF_LR1_STAFF_LR_RE");
		final RowMapper<ReferenceCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				referenceCodesMapping);
		final ReferenceCodes returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfkchkStaffLr1StaffLr2
	 *
	 * @param params
	 * @return ReferenceCodes
	 *
	 */
	public ReferenceCodes cgfkchkStaffLr1StaffLr2(final ReferenceCodes paramBean) {
		final String sql = getQuery("OCMSHIER_CGFKCHK_STAFF_LR1_STAFF_LR2");
		final RowMapper<ReferenceCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				referenceCodesMapping);
		final ReferenceCodes returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfkchkStaffLr1StaffLr3
	 *
	 * @param params
	 * @return ReferenceCodes
	 *
	 */
	public ReferenceCodes cgfkchkStaffLr1StaffLr3(final ReferenceCodes paramBean) {
		final String sql = getQuery("OCMSHIER_CGFKCHK_STAFF_LR1_STAFF_LR3");
		final RowMapper<ReferenceCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				referenceCodesMapping);
		final ReferenceCodes returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfkchkStaffLr1StaffLrSt
	 *
	 * @param params
	 * @return StaffMembers
	 *
	 */
	public StaffMembers cgfkchkStaffLr1StaffLrSt(final StaffMembers paramBean) {
		final String sql = getQuery("OCMSHIER_CGFKCHK_STAFF_LR1_STAFF_LR_ST");
		final RowMapper<StaffMembers> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, StaffMembers.class,
				staffMMap);
		final StaffMembers returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfklkpStaffLr1StaffLrSt
	 *
	 * @param params
	 * @return StaffMembers
	 *
	 */
	public StaffMembers cgfklkpStaffLr1StaffLrSt(final StaffMembers paramBean) {
		final String sql = getQuery("OCMSHIER_CGFKLKP_STAFF_LR1_STAFF_LR_ST");
		final RowMapper<StaffMembers> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, StaffMembers.class,
				staffMMap);
		final StaffMembers returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
		return returnObj;
	}

}

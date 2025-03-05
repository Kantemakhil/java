package net.syscon.s4.globalrbac.impl;

import java.sql.Types;
import java.util.ArrayList;
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
import net.syscon.s4.common.beans.CaseloadAgencyLocations;
import net.syscon.s4.common.beans.Caseloads;
import net.syscon.s4.common.beans.Images;
import net.syscon.s4.common.beans.OffenderBookings;
import net.syscon.s4.common.beans.StaffMemberRoles;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.common.beans.dao.RecordGroup;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.globalrbac.OumusersRepository;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.OffenderMedicalScreenings;
import net.syscon.s4.im.beans.OmsRoles;
import net.syscon.s4.im.beans.PersonnelIdentifications;
import net.syscon.s4.im.beans.PersonnelIssuedCards;
import net.syscon.s4.im.beans.StaffAccessibleCaseloads;

/**
 * Class OumusersRepositoryImpl
 */
@Repository
public class OumusersRepositoryImpl extends RepositoryBase implements OumusersRepository {

	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OumusersRepositoryImpl.class.getName());

	private final Map<String, FieldMapper> stMemMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("PERSONNEL_TYPE", new FieldMapper("personnelType"))
			.put("DEFAULT_PRINTER_ID", new FieldMapper("defaultPrinterId")).put("ROLE", new FieldMapper("role"))
			.put("SUFFIX", new FieldMapper("suffix")).put("TERMINATION_DATE", new FieldMapper("terminationDate"))
			.put("AS_OF_DATE", new FieldMapper("asOfDate")).put("MIDDLE_NAME", new FieldMapper("middleName"))
			.put("COMM_RECEIPT_PRINTER_ID", new FieldMapper("commReceiptPrinterId"))
			.put("LAST_NAME", new FieldMapper("lastName")).put("POSITION", new FieldMapper("position"))
			.put("SEAL_FLAG", new FieldMapper("sealFlag")).put("CREATE_DATETIME", new FieldMapper("createDateTime"))
			.put("FORCE_PASSWORD_CHANGE_FLAG", new FieldMapper("forcePasswordChangeFlag"))
			.put("MODIFY_DATETIME", new FieldMapper("modifyDateTime"))
			.put("WORKING_CASELOAD_ID", new FieldMapper("workingCaseloadId")).put("STATUS", new FieldMapper("status"))
			.put("SUSPENDED_FLAG", new FieldMapper("suspendedFlag"))
			.put("ASSIGNED_CASELOAD_ID", new FieldMapper("assignedCaseloadId"))
			.put("CREATE_USER_ID", new FieldMapper("createUserId"))
			.put("MODIFY_USER_ID", new FieldMapper("modifyUserId"))
			.put("FIRST_LOGON_FLAG", new FieldMapper("firstLogonFlag")).put("USER_ID", new FieldMapper("userId"))
			.put("LAST_PASSWORD_CHANGE_DATE", new FieldMapper("lastPasswordChangeDate"))
			.put("STAFF_ID", new FieldMapper("staffId"))
			.put("SUPERVISOR_STAFF_ID", new FieldMapper("supervisorStaffId"))
			.put("SUSPENSION_REASON", new FieldMapper("suspensionReason"))
			.put("NAME_SEQUENCE", new FieldMapper("nameSequence")).put("ABBREVIATION", new FieldMapper("abbreviation"))
			.put("LICENSE_EXPIRY_DATE", new FieldMapper("licenseExpiryDate"))
			.put("BIRTHDATE", new FieldMapper("birthdate")).put("TITLE", new FieldMapper("title"))
			.put("SEX_CODE", new FieldMapper("sexCode")).put("SUSPENSION_DATE", new FieldMapper("suspensionDate"))
			.put("BADGE_ID", new FieldMapper("badgeId"))
			.put("WORKING_STOCK_LOC_ID", new FieldMapper("workingStockLocId"))
			.put("UPDATE_ALLOWED_FLAG", new FieldMapper("updateAllowedFlag"))
			.put("LICENSE_CODE", new FieldMapper("licenseCode"))
			.put("EMERGENCY_CONTACT", new FieldMapper("emergencyContact"))
			.put("QUEUE_CLUSTER_ID", new FieldMapper("queueClusterId")).put("FIRST_NAME", new FieldMapper("firstName"))
			.put("USERIDVAL", new FieldMapper("userIdVal"))
			.put("MAIL_ID", new FieldMapper("mailId"))
			.put("AD_USER", new FieldMapper("adUser")).build();
	private final Map<String, FieldMapper> stAccClMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CASELOAD_ID", new FieldMapper("caseloadId")).put("CREATE_USER_ID", new FieldMapper("createUserId"))
			.put("SEAL_FLAG", new FieldMapper("sealFlag")).put("CREATE_DATETIME", new FieldMapper("createDatetime"))
			.put("MODIFY_USER_ID", new FieldMapper("modifyUserId")).put("STAFF_ID", new FieldMapper("staffId"))
			.put("UPDATE_ALLOWED_FLAG", new FieldMapper("updateAllowedFlag"))
			.put("MODIFY_DATETIME", new FieldMapper("modifyDatetime")).build();
	private final Map<String, FieldMapper> perIdenMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("'1'", new FieldMapper("  '1' ")).build();
	private final Map<String, FieldMapper> offMedScrnMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("'1'", new FieldMapper("  '1' ")).build();
	private final Map<String, FieldMapper> caseloadsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("LIST_SEQ", new FieldMapper("listSeq"))
			.put("ASSIGNED_CASELOAD_ID", new FieldMapper("assignedCaseloadId"))
			.put("DESCRIPTION", new FieldMapper("description")).put("CASELOAD_ID", new FieldMapper("caseloadId"))
			.build();
	private final Map<String, FieldMapper> omsRolesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("ROLE_NAME", new FieldMapper("roleName")).put("ROLE_CODE", new FieldMapper("roleCode")).build();
	private final Map<String, FieldMapper> pIssCardsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("'1'", new FieldMapper("  '1' ")).build();
	private final Map<String, FieldMapper> staMemRlsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CREATE_USER_ID", new FieldMapper("createUserId")).put("SEAL_FLAG", new FieldMapper("sealFlag"))
			.put("CREATE_DATETIME", new FieldMapper("createDateTime"))
			.put("MODIFY_USER_ID", new FieldMapper("modifyUserId")).put("STAFF_ID", new FieldMapper("staffId"))
			.put("ROLE_CODE", new FieldMapper("roleCode")).put("MODIFY_DATETIME", new FieldMapper("modifyDateTime"))
			.put("ROLE_ID", new FieldMapper("roleId")).build();
	private final Map<String, FieldMapper> offBookMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("'1'", new FieldMapper("  '1' ")).build();
	private final Map<String, FieldMapper> agyLocMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("AGENCY_LOCATION_TYPE", new FieldMapper("agencyLocationType"))
			.put("AGY_LOC_ID", new FieldMapper("agyLocId")).put("DESCRIPTION", new FieldMapper("description")).build();
	private final Map<String, FieldMapper> clAgyLocsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CASELOAD_ID", new FieldMapper("caseloadId")).put("CREATE_USER_ID", new FieldMapper("createUserId"))
			.put("SEAL_FLAG", new FieldMapper("sealFlag")).put("AGY_LOC_ID", new FieldMapper("agyLocId"))
			.put("CREATE_DATETIME", new FieldMapper("createDateTime"))
			.put("MODIFY_USER_ID", new FieldMapper("modifyUserId"))
			.put("UPDATE_ALLOWED_FLAG", new FieldMapper("updateAllowedFlag"))
			.put("MODIFY_DATETIME", new FieldMapper("modifyDateTime")).build();
	private final Map<String, FieldMapper> systemProfilesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("PROFILE_VALUE",   new FieldMapper("profileValue"))
			.put("MODIFY_USER_ID",  new FieldMapper("modifyUserId"))
			.put("CREATE_DATETIME", new FieldMapper("createDatetime"))
			.put("CREATE_USER_ID",  new FieldMapper("createUserId"))
			.put("MODIFY_DATETIME", new FieldMapper("modifyDatetime"))
			.build();
	private final Map<String, FieldMapper> imgMapping = new ImmutableMap.Builder<String, FieldMapper>().build();

	/**
	 * Creates new OumusersRepositoryImpl class Object
	 */
	public OumusersRepositoryImpl() {
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            StaffMembers
	 *
	 * @return List<StaffMembers>
	 *
	 * @
	 */
	public List<StaffMembers> staffExecuteQuery(final StaffMembers objSearchDao) {
		final String sql = getQuery("OUMUSERS_STAFF_FIND_STAFF_MEMBERS");
		final RowMapper<StaffMembers> staMemRowMapper = Row2BeanRowMapper.makeMapping(sql, StaffMembers.class,
				stMemMapping);
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
			if (objSearchDao.getAsOfDate() != null) {
				sqlQuery.append("AS_OF_DATE =  :asOfDate " + " and ");
				valuesList.addValue("asOfDate", objSearchDao.getAsOfDate());
			}
			if (objSearchDao.getAssignedCaseloadId() != null) {
				sqlQuery.append("ASSIGNED_CASELOAD_ID =  :assignedCaseloadId " + " and ");
				valuesList.addValue("assignedCaseloadId", objSearchDao.getAssignedCaseloadId());
			}
		}
		preparedSql = sqlQuery.toString().trim();
		if (preparedSql.endsWith("where")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 5);
		}
		if (preparedSql.endsWith("and")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 3);
		}

		return namedParameterJdbcTemplate.query(preparedSql, valuesList, staMemRowMapper);
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
	 * This method is used to update the data base tables based on
	 *
	 * @param lstStaffMembers
	 *            List<StaffMembers>
	 *
	 * @
	 */
	public String staffUpdateStaffMembers(final List<StaffMembers> lstStaffMembers) {
		final String sql = getQuery("OUMUSERS_STAFF_UPDATE_STAFF_MEMBERS");
		String returnValue = null;
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final StaffMembers staffMembers : lstStaffMembers) {
			parameters.add(createStaffMember(staffMembers));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstStaffMembers.size() == returnArray.length) {
			returnValue = "1";
		}
		return returnValue;

	}

	/**
	 * This method is used to delete records from data base tables based on
	 *
	 * @param lstStaffMembers
	 *            List<StaffMembers>
	 *
	 * @
	 */
	public String staffDeleteStaffMembers(final List<StaffMembers> lstStaffMembers) {
		final String sql = getQuery("OUMUSERS_STAFF_DELETE_STAFF_MEMBERS");
		String returnValue = null;
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final StaffMembers staffMembers : lstStaffMembers) {
			parameters.add(createStaffMember(staffMembers));
		}
		try {
			String tableName = "STAFF_MEMBERS";
			String whereClause = null;
			batchUpdatePreDeletedRows(tableName, whereClause , parameters);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method staffDeleteStaffMembers", e);
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstStaffMembers.size() == returnArray.length) {
			returnValue = "1";
		}
		return returnValue;
	}

	/**
	 * This method is used to create new BeanPropertySqlParameterSource
	 * 
	 * @param paramBean
	 *            StaffMembers
	 * 
	 * @return BeanPropertySqlParameterSource
	 */
	private BeanPropertySqlParameterSource createStaffMember(final StaffMembers paramBean) {
		return new BeanPropertySqlParameterSource(paramBean);
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            StaffAccessibleCaseloads
	 *
	 * @return List<StaffAccessibleCaseloads>
	 *
	 * @
	 */
	public List<StaffAccessibleCaseloads> staffAcExecuteQuery(final StaffAccessibleCaseloads objSearchDao) {
		final String sql = getQuery("OUMUSERS_STAFFAC_FIND_STAFF_ACCESSIBLE_CASELOADS");
		final RowMapper<StaffAccessibleCaseloads> staAccClRowMapper = Row2BeanRowMapper.makeMapping(sql,
				StaffAccessibleCaseloads.class, stAccClMapping);
		List<StaffAccessibleCaseloads> lstStaffAccCl = (List<StaffAccessibleCaseloads>) namedParameterJdbcTemplate
				.query(sql, createParams("staffId", objSearchDao.getStaffId()), staAccClRowMapper);
		return lstStaffAccCl;

	}

	/**
	 * This method is used to create new BeanPropertySqlParameterSource
	 * 
	 * @param caseloadAgyLoc
	 *            StaffAccessibleCaseloads
	 * 
	 * @return BeanPropertySqlParameterSource
	 */
	private BeanPropertySqlParameterSource createCaseLoadAgyLoc(final StaffAccessibleCaseloads caseloadAgyLoc) {
		return new BeanPropertySqlParameterSource(caseloadAgyLoc);
	}

	/**
	 * This method is used to insert the records in the data base tables based on
	 *
	 * @param lstStaffAccessibleCaseloads
	 *            List<StaffAccessibleCaseloads>
	 *
	 * @return List<Integer>
	 *
	 * @
	 */
	public Integer staffAcInsertStaffAccessibleCaseloads(final List<StaffAccessibleCaseloads> lstStaAccClds) {
		int result = 0;
		final String sql = getQuery("OUMUSERS_STAFFAC_INSERT_STAFF_ACCESSIBLE_CASELOADS");
		int[] returnArray = new int[] {};
		try {
			final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
			for (final StaffAccessibleCaseloads clAgyLocations : lstStaAccClds) {
				parameters.add(createCaseLoadAgyLoc(clAgyLocations));
			}
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
			if (lstStaAccClds.size() == returnArray.length) {
				result = 1;
			}
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " staffAcInsertStaffAccessibleCaseloads and Exception is : {}", e.getMessage());
		}
		return result;
	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstStaffAccessibleCaseloads
	 *            List<StaffAccessibleCaseloads>
	 *
	 * @
	 */
	public Integer staffAcUpdateStaffAccessibleCaseloads(final List<StaffAccessibleCaseloads> lstStaffAccCl) {
		final String sql = getQuery("OUMUSERS_STAFFAC_UPDATE_STAFF_ACCESSIBLE_CASELOADS");
		int returnValue = 0;
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final StaffAccessibleCaseloads staffAccClds : lstStaffAccCl) {
			parameters.add(createCaseLoadAgyLoc(staffAccClds));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstStaffAccCl.size() == returnArray.length) {
			returnValue = 1;
		}
		return returnValue;
	}

	/**
	 * This method is used to delete records from data base tables based on
	 *
	 * @param lstStaffAccessibleCaseloads
	 *            List<StaffAccessibleCaseloads>
	 *
	 * @
	 */
	public Integer staffAcDeleteStaffAccessibleCaseloads(final List<StaffAccessibleCaseloads> lstStaffAccClds) {
		final String sql = getQuery("OUMUSERS_STAFFAC_DELETE_STAFF_ACCESSIBLE_CASELOADS");
		int returnValue = 0;
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final StaffAccessibleCaseloads staffAccClds : lstStaffAccClds) {
			parameters.add(createCaseLoadAgyLoc(staffAccClds));
		}
		try {
			String tableName = "STAFF_ACCESSIBLE_CASELOADS";
			String whereClause = "STAFF_ID  = :staffId and CASELOAD_ID  = :caseloadId";
			batchUpdatePreDeletedRows(tableName, whereClause , parameters);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method staffAcDeleteStaffAccessibleCaseloads", e);
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstStaffAccClds.size() == returnArray.length) {
			returnValue = 1;
		}
		return returnValue;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            StaffMemberRoles
	 *
	 * @return List<StaffMemberRoles>
	 *
	 * @
	 */
	public List<StaffMemberRoles> staffMemberRolesExecuteQuery(final StaffMemberRoles objSearchDao) {
		final String sql = getQuery("OUMUSERS_STAFFMEMBERROLES_FIND_STAFF_MEMBER_ROLES");
		final RowMapper<StaffMemberRoles> staMemRolesRM = Row2BeanRowMapper.makeMapping(sql, StaffMemberRoles.class,
				staMemRlsMapping);
		return namedParameterJdbcTemplate.query(sql, createParams("staffId", objSearchDao.getStaffId()), staMemRolesRM);
	}

	/**
	 * This method is used to insert the records in the data base tables based on
	 *
	 * @param lstStaMemRoles
	 *            List<StaffMemberRoles>
	 *
	 * @return List<Integer>
	 *
	 * @
	 */
	public Integer staffMemberRolesInsertStaffMemberRoles(final List<StaffMemberRoles> lstStaMemRoles) {
		int result = 0;
		final String sql = getQuery("OUMUSERS_STAFFMEMBERROLES_INSERT_STAFF_MEMBER_ROLES");
		int[] returnArray = new int[] {};
		try {
			final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
			for (final StaffMemberRoles staffMemberRoles : lstStaMemRoles) {
				parameters.add(createStaffMemRoles(staffMemberRoles));
			}
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
			if (lstStaMemRoles.size() == returnArray.length) {
				result = 1;
			}
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " staffMemberRolesInsertStaffMemberRoles and Exception is : {}", e.getMessage());
		}
		return result;

	}

	/**
	 * This method is used to delete records from data base tables based on
	 *
	 * @param lstStaMemRoles
	 *            List<StaffMemberRoles>
	 *
	 * @
	 */
	public Integer staffMemberRolesDeleteStaffMemberRoles(final List<StaffMemberRoles> lstStaMemRoles) {
		final String sql = getQuery("OUMUSERS_STAFFMEMBERROLES_DELETE_STAFF_MEMBER_ROLES");
		int returnValue = 0;
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final StaffMemberRoles staffMemberRoles : lstStaMemRoles) {
			parameters.add(createStaffMemRoles(staffMemberRoles));
		}
		try {
			String tableName = "STAFF_MEMBER_ROLES";
			String whereClause = "ROLE_ID = :roleId and STAFF_ID = :staffId";
			batchUpdatePreDeletedRows(tableName, whereClause , parameters);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method staffMemberRolesDeleteStaffMemberRoles", e);
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstStaMemRoles.size() == returnArray.length) {
			returnValue = 1;
		}
		return returnValue;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            CaseloadAgencyLocations
	 *
	 * @return List<CaseloadAgencyLocations>
	 *
	 * @
	 */
	public List<CaseloadAgencyLocations> calExecuteQuery(final CaseloadAgencyLocations objSearchDao) {
		final String sql = getQuery("OUMUSERS_CAL_FIND_CASELOAD_AGENCY_LOCATIONS");
		final RowMapper<CaseloadAgencyLocations> clAgyLocsRM = Row2BeanRowMapper.makeMapping(sql,
				CaseloadAgencyLocations.class, clAgyLocsMapping);
		List<CaseloadAgencyLocations> lstCaseLoadAgy = (List<CaseloadAgencyLocations>) namedParameterJdbcTemplate
				.query(sql, createParams("caseloadId", objSearchDao.getCaseloadId()), clAgyLocsRM);
		return lstCaseLoadAgy;
	}

	/**
	 * This method is used to create new BeanPropertySqlParameterSource
	 * 
	 * @param staffMemberRoles
	 *            StaffMemberRoles
	 * 
	 * @return BeanPropertySqlParameterSource
	 */
	private BeanPropertySqlParameterSource createClAgencyLocations(final CaseloadAgencyLocations clAgencyLocations) {
		return new BeanPropertySqlParameterSource(clAgencyLocations);
	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstCaseloadAgencyLocations
	 *            List<CaseloadAgencyLocations>
	 *
	 * @return Integer
	 */
	public Integer calUpdateCaseloadAgencyLocations(final List<CaseloadAgencyLocations> lstClAgyLocations) {
		final String sql = getQuery("OUMUSERS_CAL_UPDATE_CASELOAD_AGENCY_LOCATIONS");
		int returnValue = 0;
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final CaseloadAgencyLocations clAgencyLocations : lstClAgyLocations) {
			parameters.add(createClAgencyLocations(clAgencyLocations));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstClAgyLocations.size() == returnArray.length) {
			returnValue = 1;
		}
		return returnValue;
	}

	/**
	 * This method is used to create new BeanPropertySqlParameterSource
	 * 
	 * @param staffMemberRoles
	 *            StaffMemberRoles
	 * 
	 * @return BeanPropertySqlParameterSource
	 */
	private BeanPropertySqlParameterSource createStaffMemRoles(final StaffMemberRoles staffMemberRoles) {
		return new BeanPropertySqlParameterSource(staffMemberRoles);
	}

	/**
	 * This method is used to create new BeanPropertySqlParameterSource
	 * 
	 * @param objCaseLoads
	 *            Caseloads
	 * 
	 * @return RecordGroup
	 */
	private RecordGroup createCaseLoadRecordGroup(final Caseloads objCaseLoads) {
		final RecordGroup objRecord = new RecordGroup();
		objRecord.setCode(objCaseLoads.getCaseloadId());
		objRecord.setDescription(objCaseLoads.getDescription());
		return objRecord;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<Caseloads>
	 */
	public List<RecordGroup> rgStaffAssignedCaseloadRecordGroup() {

		final List<RecordGroup> lstClRecords = new ArrayList<>();
		List<Caseloads> returnList = new ArrayList<>();
		RecordGroup objRecord = new RecordGroup();
		try {
			final String sql = getQuery("OUMUSERS_FIND_RGSTAFFASSIGNEDCASELOAD");
			final RowMapper<Caseloads> clRowMapper = Row2BeanRowMapper.makeMapping(sql, Caseloads.class,
					caseloadsMapping);
			returnList = (List<Caseloads>) namedParameterJdbcTemplate.query(sql, createParams(), clRowMapper);

			for (final Caseloads objCaseLoads : returnList) {
				objRecord = createCaseLoadRecordGroup(objCaseLoads);
				lstClRecords.add(objRecord);
			}
		} catch (Exception e) {
			logger.error(" In method rgStaffAssignedCaseloadRecordGroup " + e);
		}
		return lstClRecords;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<OmsRoles>
	 */
	public List<OmsRoles> rgStaffMemberRolesRoleRecordGroup() {
		final String sql = getQuery("OUMUSERS_FIND_RGSTAFFMEMBERROLESROLE");
		List<OmsRoles> lstOmsRoles = new ArrayList<OmsRoles>();
		try {
			final RowMapper<OmsRoles> omsRolesRowMapper = Row2BeanRowMapper.makeMapping(sql, OmsRoles.class,
					omsRolesMapping);
			lstOmsRoles = (List<OmsRoles>) namedParameterJdbcTemplate.query(sql, createParams(), omsRolesRowMapper);
		} catch (Exception e) {
			logger.error(" In method rgStaffAssignedCaseloadRecordGroup " + e);
		}
		return lstOmsRoles;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<Caseloads>
	 */
	public List<Caseloads> rgStaffAcCaseloadIdRecordGroup() {
		final String sql = getQuery("OUMUSERS_FIND_RGSTAFFACCASELOADID");
		final RowMapper<Caseloads> clRowMapper = Row2BeanRowMapper.makeMapping(sql, Caseloads.class, caseloadsMapping);
		List<Caseloads> returnList = new ArrayList<Caseloads>();
		try {
			returnList = (List<Caseloads>) namedParameterJdbcTemplate.query(sql, createParams(), clRowMapper);
		} catch (Exception e) {
			logger.error(" In method rgStaffAcCaseloadIdRecordGroup " + e);
		}
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfkchkStaffStaffCsldF1
	 *
	 * @param paramBean
	 *
	 */
	public Caseloads cgfkchkStaffStaffCsldF1(final Caseloads paramBean) {
		final String sql = getQuery("OUMUSERS_CGFKCHK_STAFF_STAFF_CSLD_F1");
		final RowMapper<Caseloads> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, Caseloads.class,
				caseloadsMapping);
		return namedParameterJdbcTemplate.queryForObject(sql, createParams(paramBean), columnRowMapper);
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfkchkStaffAcStaffAcCsl
	 *
	 * @param paramBean
	 *
	 */
	public Caseloads cgfkchkStaffAcStaffAcCsl(final Caseloads paramBean) {
		final String sql = getQuery("OUMUSERS_CGFKCHK_STAFF_AC_STAFF_AC_CSL_");
		final RowMapper<Caseloads> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, Caseloads.class,
				caseloadsMapping);
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("CASELOADID", paramBean.getCaseloadId()),
				columnRowMapper);
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfkchkStaffMemberRolesSt
	 *
	 * @param paramBean
	 *
	 */
	public List<OmsRoles> cgfkchkStaffMemberRolesSt(final OmsRoles paramBean) {
		final String sql = getQuery("OUMUSERS_CGFKCHK_STAFF_MEMBER_ROLES_ST");
		final RowMapper<OmsRoles> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, OmsRoles.class, omsRolesMapping);
		return namedParameterJdbcTemplate.query(sql, createParams(paramBean), columnRowMapper);
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgrichkStaffMembers
	 *
	 * @param paramBean
	 *
	 */
	public List<PersonnelIdentifications> cgrichkStaffMembers(final PersonnelIdentifications paramBean) {
		final String sql = getQuery("OUMUSERS_CGRICHK_STAFF_MEMBERS");
		final RowMapper<PersonnelIdentifications> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				PersonnelIdentifications.class, perIdenMapping);
		return namedParameterJdbcTemplate.query(sql, createParams(paramBean), columnRowMapper);
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgrichkStaffMembers
	 *
	 * @param paramBean
	 *
	 */
	public PersonnelIssuedCards cgrichkStaffMembers(final PersonnelIssuedCards paramBean) {
		final String sql = getQuery("OUMUSERS_CGRICHK_STAFF_MEMBERS");
		final RowMapper<PersonnelIssuedCards> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				PersonnelIssuedCards.class, pIssCardsMapping);
		return namedParameterJdbcTemplate.queryForObject(sql, createParams(paramBean), columnRowMapper);
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgrichkStaffMembers
	 *
	 * @param paramBean
	 *
	 */
	public List<OffenderMedicalScreenings> cgrichkStaffMembers(final OffenderMedicalScreenings paramBean) {
		final String sql = getQuery("OUMUSERS_CGRICHK_STAFF_MEMBERS");
		final RowMapper<OffenderMedicalScreenings> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderMedicalScreenings.class, offMedScrnMapping);
		return namedParameterJdbcTemplate.query(sql, createParams(paramBean), columnRowMapper);
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgrichkStaffMembers
	 *
	 * @param paramBean
	 *
	 */
	public List<OffenderBookings> cgrichkStaffMembers(final OffenderBookings paramBean) {
		final String sql = getQuery("OUMUSERS_CGRICHK_STAFF_MEMBERS");
		final RowMapper<OffenderBookings> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, OffenderBookings.class,
				offBookMapping);
		return namedParameterJdbcTemplate.query(sql, createParams(paramBean), columnRowMapper);
	}

	/**
	 * /** This method is execute a sql query when trigger event is called
	 * 
	 * cgfkchkCalCsldAlAgyLoc
	 *
	 * @param params
	 *
	 */
	public List<AgencyLocations> cgfkchkCalCsldAlAgyLoc(final AgencyLocations paramBean) {
		final String sql = getQuery("OUMUSERS_CGFKCHK_CAL_CSLD_AL_AGY_LOC_F_");
		final RowMapper<AgencyLocations> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, AgencyLocations.class,
				agyLocMapping);
		return namedParameterJdbcTemplate.query(sql, createParams("AGYLOCID", paramBean.getAgyLocId()),
				columnRowMapper);
	}

	public Integer userIdCheckCur(final StaffMembers paramBean) {
		final String sql = getQuery("OUMUSERS_USERID_CHECK_CUR");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("V_USER_ID", paramBean.getUserId()),
				Integer.class);
	}

	public String cSysUsers(final StaffMembers paramBean) {
		String returnVal = null;
		final String sql = getQuery("OUMUSERS_C_SYSTEM_USERS");
		try {
			returnVal = namedParameterJdbcTemplate.queryForObject(sql, createParams("userid", paramBean.getUserId()),
					String.class);
		} catch (EmptyResultDataAccessException e) {
			returnVal = null;
		}
		return returnVal;
	}

	@Override
	public Map<String, Object> validateUserid(final StaffMembers paramBean) {
		Map<String, Object> returnObject = null;
		final Map<String, Object> inParamMap = new HashMap<>();
		SqlParameter[] sqlParameters = new SqlParameter[3];
		sqlParameters = new SqlParameter[] { new SqlParameter("P_USERID", Types.VARCHAR),
				new SqlOutParameter("P_ILLEGAL_CHARS", Types.VARCHAR),
				new SqlOutParameter("RETURN_VALUE", Types.INTEGER) };
		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("OMS_UTILS").withFunctionName("VALIDATE_USERID").declareParameters(sqlParameters);
		inParamMap.put("P_USERID", paramBean.getUserId());

		final MapSqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);

		try {
			returnObject = simpleJDBCCall.execute(inParameter);
		} catch (Exception e) {
			returnObject = null;
		}
		return returnObject;
	}

	public Integer validateuseridChar(final StaffMembers paramBean) {
		final String sql = getQuery("OUMUSERS_VALIDATE_USERID");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("userid", paramBean.getUserId()),
				Integer.class);
	}

	@Override
	public List<StaffAccessibleCaseloads> staffCaseLoadData(String userId) {
		final String sql = getQuery("OUMUSERS_CASELOAD_DATA");
		final RowMapper<StaffAccessibleCaseloads> staAccClRowMapper = Row2BeanRowMapper.makeMapping(sql,
				StaffAccessibleCaseloads.class, stAccClMapping);
		List<StaffAccessibleCaseloads> caseLoadList = (List<StaffAccessibleCaseloads>) namedParameterJdbcTemplate
				.query(sql, createParams("userId", userId), staAccClRowMapper);
		return caseLoadList;
	}

	@Override
	public List<Images> imageExecuteQuery(final StaffMembers paramBean) {
		final String sql = getQuery("OUMUSERS_IMAGE_EXECUTEQUERY");
		final RowMapper<Images> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, Images.class, imgMapping);
		return namedParameterJdbcTemplate.query(sql, createParams("STAFF_ID", paramBean.getStaffId()), columnRowMapper);
	}

	@Override
	public StaffMembers triggerStaffExceQuery(final Integer staffId) {
		 final String sql = getQuery("OUMUSERS_TRIGGER_STAFFEXCEQUERY");
		StaffMembers bean = new StaffMembers();
		final RowMapper<StaffMembers> staMemRowMapper = Row2BeanRowMapper.makeMapping(sql, StaffMembers.class,
				stMemMapping);
		try {
			bean = namedParameterJdbcTemplate.queryForObject(sql, createParams("STAFF_ID", staffId), staMemRowMapper);
		} catch (Exception e) {
			logger.error("triggerStaffExceQuery :" + e);
		}
		return bean;
	}

	@Override
	public Integer getValueAssignedCountForStaff(StaffAccessibleCaseloads object) {
		final String sql = getQuery("OUMUSERS_CASELOAD_ASSIGNED_COUNT_FOR_STAFF");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("staffId",object.getStaffId(),"caseloadId",object.getCaseloadId()),
				Integer.class);
	}

	@Override
	public Integer staffPasswordReset(StaffMembers objStaffMembers,String pass) {
		String sql = getQuery("OUMUSERS_RESET_PASSWORD");
		try {
			namedParameterJdbcTemplate.update(sql, createParams("password",pass,"userId",objStaffMembers.getUserId(),"modifyUserId",objStaffMembers.getModifyUserId()));
		} catch (Exception e) {
			logger.error(this.getClass().getName() + "staffPasswordReset and Exception is", e.getMessage());
			return 0;
		}
		return 1;
	}

	public String getDefaultUserName() {
		final String sql = getQuery("OUMUSERS_USER_DATA");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams(), String.class);
	}

	@Override
	public Integer getValueWorkingCountForStaff(StaffAccessibleCaseloads object) {
		final String sql = getQuery("OUMUSERS_CASELOAD_WORKING_CASELOAD_COUNT_FOR_STAFF");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("staffId",object.getStaffId(),"caseloadId",object.getCaseloadId()),
				Integer.class);
	}
}
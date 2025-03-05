package net.syscon.s4.globalrbac.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.globalrbac.OumpersdRepository;

/**
 * Class OumpersdRepositoryImpl
 */
@Repository
public class OumpersdRepositoryImpl extends RepositoryBase implements OumpersdRepository {

	/**
	 * Creates new OumpersdRepositoryImpl class Object
	 */
	public OumpersdRepositoryImpl() {
	}

	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OumpersdRepositoryImpl.class);
	private final Map<String, FieldMapper> staffMembersMapping = new ImmutableMap.Builder<String, FieldMapper>()
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
			.build();

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            StaffMembers
	 *
	 * @return List<StaffMembers>
	 */
	public List<StaffMembers> staffExecuteQuery(final Integer staffId) {
		final String sql = getQuery("OUMPERSD_STAFF_FIND_STAFF_MEMBERS");
		String preparedSql = null;
		final MapSqlParameterSource valueList = new MapSqlParameterSource();
		final StringBuffer sqlQuery = new StringBuffer();
		sqlQuery.append(sql);
		if (staffId != null) {
			sqlQuery.append(" where ");
			sqlQuery.append("STAFF_ID = :staffId");
			valueList.addValue("staffId", staffId);
		}
		preparedSql = sqlQuery.toString();
		final RowMapper<StaffMembers> staffMembersRowMapper = Row2BeanRowMapper.makeMapping(sql, StaffMembers.class,
				staffMembersMapping);
		return namedParameterJdbcTemplate.query(preparedSql, valueList, staffMembersRowMapper);
	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstStaffMembers
	 *            List<StaffMembers>
	 */
	public Integer staffUpdateStaffMembers(final List<StaffMembers> lstStaffMembers) {
		final String sql = getQuery("OUMPERSD_STAFF_UPDATE_STAFF_MEMBERS");
		int[] returnArray;
		final List<SqlParameterSource> parameters = new ArrayList<>();
		for (final StaffMembers staffMembers : lstStaffMembers) {
			parameters.add(new BeanPropertySqlParameterSource(staffMembers));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstStaffMembers.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}
}

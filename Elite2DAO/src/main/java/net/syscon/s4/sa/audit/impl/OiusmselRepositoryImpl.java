package net.syscon.s4.sa.audit.impl;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.sa.audit.OiusmselRepository;

/**
 * Class OiusmselRepositoryImpl
 */
@Repository
public class OiusmselRepositoryImpl extends RepositoryBase implements OiusmselRepository {
	private static Logger logger = LogManager.getLogger(OiusmselRepositoryImpl.class.getName());

	/**
	 * Creates new OiusmselRepositoryImpl class Object
	 */
	public OiusmselRepositoryImpl() {
	}

	private final Map<String, FieldMapper> vStaffMemberMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("STAFF_ID", new FieldMapper("staffId"))
			.put("ASSIGNED_CASELOAD_ID", new FieldMapper("assignedCaseloadId"))
			.put("WORKING_STOCK_LOC_ID", new FieldMapper("workingStockLocId"))
			.put("WORKING_CASELOAD_ID", new FieldMapper("workingCaseloadId")).put("USER_ID", new FieldMapper("userId"))
			.put("BADGE_ID", new FieldMapper("badgeId")).put("LAST_NAME", new FieldMapper("lastName"))
			.put("FIRST_NAME", new FieldMapper("firstName")).put("MIDDLE_NAME", new FieldMapper("middleName"))
			.put("ABBREVIATION", new FieldMapper("abbreviation")).put("POSITION", new FieldMapper("position"))
			.put("BIRTHDATE", new FieldMapper("birthdate")).put("TERMINATION_DATE", new FieldMapper("terminationDate"))
			.put("UPDATE_ALLOWED_FLAG", new FieldMapper("updateAllowedFlag"))
			.put("DEFAULT_PRINTER_ID", new FieldMapper("defaultPrinterId"))
			.put("SUSPENDED_FLAG", new FieldMapper("suspendedFlag"))
			.put("SUPERVISOR_STAFF_ID", new FieldMapper("supervisorStaffId"))
			.put("COMM_RECEIPT_PRINTER_ID", new FieldMapper("commReceiptPrinterId"))
			.put("AS_OF_DATE", new FieldMapper("asOfDate"))
			.put("EMERGENCY_CONTACT", new FieldMapper("emergencyContact")).put("ROLE", new FieldMapper("role"))
			.put("SEX_CODE", new FieldMapper("sexCode")).put("STATUS", new FieldMapper("status"))
			.put("SUSPENSION_DATE", new FieldMapper("suspensionDate"))
			.put("SUSPENSION_REASON", new FieldMapper("suspensionReason"))
			.put("FORCE_PASSWORD_CHANGE_FLAG", new FieldMapper("forcePasswordChangeFlag"))
			.put("LAST_PASSWORD_CHANGE_DATE", new FieldMapper("lastPasswordChangeDate"))
			.put("LICENSE_CODE", new FieldMapper("licenseCode")).put("CREATE_USER_ID", new FieldMapper("createUserId"))
			.put("MODIFY_USER_ID", new FieldMapper("modifyUserId")).put("TITLE", new FieldMapper("title"))
			.put("NAME_SEQUENCE", new FieldMapper("nameSequence"))
			.put("QUEUE_CLUSTER_ID", new FieldMapper("queueClusterId"))
			.put("FIRST_LOGON_FLAG", new FieldMapper("firstLogonFlag")).put("SUFFIX", new FieldMapper("suffix"))
			.build();

	/**
	 * Fetch the records from database table
	 *
	 * @param StaffMembers
	 *
	 * @return List<VStaffMember>
	 *
	 */
	public List<StaffMembers> staffMembersExecuteQuery(final StaffMembers objSearchDao) {
		final String sql = getQuery("OIUSMSEL_STAFFMEMBERS_FIND_V_STAFF_MEMBER");
		String preparedSql = null;
		final StringBuffer sqlQuery = new StringBuffer();
		final MapSqlParameterSource params = new MapSqlParameterSource();
		sqlQuery.append(sql);
		if (objSearchDao != null) {
			sqlQuery.append(" WHERE");

			if (objSearchDao.getLastName() != null && !objSearchDao.getLastName().trim().equals("")) {
				sqlQuery.append(" LAST_NAME = :lastName and");
				params.addValue("lastName", objSearchDao.getLastName());
			}
			if (objSearchDao.getFirstName() != null && !objSearchDao.getFirstName().trim().equals("")) {
				sqlQuery.append(" FIRST_NAME = :firstName and");
				params.addValue("firstName", objSearchDao.getFirstName());
			}
			if (objSearchDao.getStaffId() != null) {
				sqlQuery.append(" STAFF_ID = :staffId and");
				params.addValue("staffId", objSearchDao.getStaffId());
			}
			if (objSearchDao.getUserId() != null && !objSearchDao.getUserId().trim().equals("")) {
				sqlQuery.append(" USER_ID = :userId and");
				params.addValue("userId", objSearchDao.getUserId());
			}
		}
		preparedSql = sqlQuery.toString().trim();
		if (preparedSql.endsWith("WHERE")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 5);
		}
		if (preparedSql.endsWith("and")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 3);
		}
		preparedSql = preparedSql + " ORDER BY LAST_NAME, FIRST_NAME";
		final RowMapper<StaffMembers> VStaffMemberRowMapper = Row2BeanRowMapper.makeMapping(sql, StaffMembers.class,
				vStaffMemberMapping);
		try {
			return namedParameterJdbcTemplate.query(preparedSql, params, VStaffMemberRowMapper);
		} catch (final Exception e) {
			logger.error("offSchExecuteQuery", e);
			return Collections.emptyList();
		}
	}

}

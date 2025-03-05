package net.syscon.s4.triggers.impl;

import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.CaseloadAgencyLocations;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.triggers.StaffAccessibleCaseloadsT1Repository;

@Repository
public class StaffAccessibleCaseloadsT1RepositoryImpl extends RepositoryBase implements StaffAccessibleCaseloadsT1Repository{

	private static Logger logger = LogManager.getLogger(StaffAccessibleCaseloadsT1RepositoryImpl.class.getName());

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
			.put("USERIDVAL", new FieldMapper("userIdVal")).build();
	@Override
	public List<CaseloadAgencyLocations> gettingAgyLocId(String caseloadId) {
		final String sql = getQuery("GETTING_CASELOAD_AGENCY_LOCATIONS_DATA");
		final RowMapper<CaseloadAgencyLocations> staAccClRowMapper = Row2BeanRowMapper.makeMapping(sql,
				CaseloadAgencyLocations.class, stMemMapping);
		List<CaseloadAgencyLocations> lstStaffAccCl = (List<CaseloadAgencyLocations>) namedParameterJdbcTemplate
				.query(sql, createParams("CASELOAD_ID", caseloadId), staAccClRowMapper);
		return lstStaffAccCl;
	}
	@Override
	public Integer checkRoleExist(String agyLocId,Integer staffId) {
		String sql = getQuery("GETTING_CUR_CHK_ROLE_EXISTS_COUNT");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("p_agy_loc_id",agyLocId,"staff_id",staffId ), Integer.class);
	}
	@Override
	public void updateStaffLocationsRoles(String agyLocId, Integer staffId, String modifyUserId) {
		String sql = getQuery("UPDATE_STAFF_LOCATION_ROLES");
		namedParameterJdbcTemplate.update(sql, createParams("staff_id",staffId,"AGY_LOC_ID",agyLocId,"modifyUserId",modifyUserId ));
	}

}

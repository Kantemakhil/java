package net.syscon.s4.inst.incidentsoic.impl;


import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.AgencyInternalLocations;
import net.syscon.s4.common.beans.CaseloadAgencyLocations;
import net.syscon.s4.common.beans.OicOffences;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.StaffMemberRoles;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.beans.VNameSearch;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.Offenders;
import net.syscon.s4.im.beans.OmsRoles;
import net.syscon.s4.im.incidentsoic.beans.AgencyIncidentCharges;
import net.syscon.s4.im.incidentsoic.beans.AgencyIncidentParties;
import net.syscon.s4.im.incidentsoic.beans.AgencyIncidentRepairs;
import net.syscon.s4.im.incidentsoic.beans.AgencyIncidents;
import net.syscon.s4.im.incidentsoic.beans.IncidentFollowUpDetails;
import net.syscon.s4.im.incidentsoic.beans.SignificantIncident;
import net.syscon.s4.inst.incidentsoic.OidincdeRepository;
import oracle.jdbc.OracleTypes;

/**
 * Class OidincdeRepositoryImpl
 */
@Repository
public class OidincdeRepositoryImpl extends RepositoryBase implements OidincdeRepository {
	private static Logger logger = LogManager.getLogger(OidincdeRepositoryImpl.class);

	private final Map<String, FieldMapper> agencyincidentsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("REPORTED_STAFF_ID", new FieldMapper("reportedStaffId"))
			.put("AGENCY_INCIDENT_ID", new FieldMapper("agencyIncidentId"))
			.put("INCIDENT_DATE", new FieldMapper("incidentDate"))
			.put("INTERNAL_LOCATION_ID", new FieldMapper("internalLocationId"))
			.put("INCIDENT_TIME", new FieldMapper("incidentTime"))
			.put("INCIDENT_TYPE", new FieldMapper("incidentType"))
			.put("INCIDENT_STATUS", new FieldMapper("incidentStatus"))
			.put("CREATE_DATETIME", new FieldMapper("createDateTime"))
			.put("CREATE_USER_ID", new FieldMapper("createUserId"))
			.put("MODIFY_USER_ID", new FieldMapper("modifyUserId"))
			.put("MODIFY_DATETIME", new FieldMapper("modifyDateTime"))
			.put("LOCK_FLAG", new FieldMapper("lockFlag"))
			.put("INCIDENT_DETAILS", new FieldMapper("incidentDetails"))
			.put("REPORT_DATE", new FieldMapper("reportDate"))
			.put("REPORT_TIME", new FieldMapper("reportTime"))
			.put("AGY_LOC_ID", new FieldMapper("agyLocId"))
			.put("LEVEL_CODE", new FieldMapper("levelCode"))
			.put("LOG_NO", new FieldMapper("logNo"))
			.put("INCIDENT_TEXT", new FieldMapper("incidentText"))
			.put("SEAL_FLAG", new FieldMapper("sealFlag"))
			.put("FROM_DATE", new FieldMapper("fromDate"))
			.put("TO_DATE", new FieldMapper("toDate"))
			.put("INCIDENT_TYPE_DESCRIPTION", new FieldMapper("incidentTypeDescription"))
			.put("REPORT_STAFF_ID_AS_CODE", new FieldMapper("reportStaffIdAsCode")).build();
			

	private final Map<String, FieldMapper> agencyincidentchargesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("AGENCY_INCIDENT_ID", new FieldMapper("agencyIncidentId"))
			.put("CHARGE_SEQ", new FieldMapper("chargeSeq"))
			.put("PARTY_SEQ", new FieldMapper("partySeq"))
			.put("OIC_CHARGE_ID", new FieldMapper("oicChargeId"))
			.put("FINDING_CODE", new FieldMapper("findingCode"))
			.put("GUILTY_EVIDENCE_TEXT", new FieldMapper("guiltyEvidenceText"))
			.put("REPORT_TEXT", new FieldMapper("reportText"))
			.put("EVIDENCE_DISPOSE_TEXT", new FieldMapper("evidenceDisposeText"))
			.put("CREATE_DATETIME", new FieldMapper("createDateTime"))
			.put("CREATE_USER_ID", new FieldMapper("createUserId"))
			.put("MODIFY_USER_ID", new FieldMapper("modifyUserId"))
			.put("MODIFY_DATETIME", new FieldMapper("modifyDateTime"))
			.put("LIDS_CHARGE_NUMBER", new FieldMapper("lidsChargeNumber"))
			.put("CHARGED_OIC_OFFENCE_ID", new FieldMapper("chargedOicOffenceId"))
			.put("RESULT_OIC_OFFENCE_ID", new FieldMapper("resultOicOffenceId"))
			.put("SEAL_FLAG", new FieldMapper("sealFlag")).build();

	private final Map<String, FieldMapper> referenceCodeMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CREATE_DATETIME", new FieldMapper("createDateTime"))
			.put("CREATE_USER_ID", new FieldMapper("createUserId"))
			.put("CODE", new FieldMapper("code"))
			.put("DOMAIN", new FieldMapper("domain"))
			.put("PARENT_DOMAIN", new FieldMapper("parentDomainId"))
			.put("PARENT_CODE", new FieldMapper("parentCode"))
			.put("ACTIVE_FLAG", new FieldMapper("activeFlag"))
			.put("INTERNAL_LOCATION_ID", new FieldMapper("internalLocationId"))
			.put("INTERNAL_LOCATION_CODE", new FieldMapper("internalLocationCode"))
			.put("AGY_LOC_ID", new FieldMapper("agyLocId")).build();

	private final Map<String, FieldMapper> staffMembersMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("STAFF_ID", new FieldMapper("staffId"))
			.put("ASSIGNED_CASELOAD_ID", new FieldMapper("assignedCaseloadId"))
			.put("WORKING_STOCK_LOC_ID", new FieldMapper("workingStockLocId"))
			.put("WORKING_CASELOAD_ID", new FieldMapper("workingCaseloadId"))
			.put("USER_ID", new FieldMapper("userId"))
			.put("BADGE_ID", new FieldMapper("badgeId"))
			.put("LAST_NAME", new FieldMapper("lastName"))
			.put("FIRST_NAME", new FieldMapper("firstName"))
			.put("MIDDLE_NAME", new FieldMapper("middleName"))
			.put("ABBREVIATION", new FieldMapper("abbreviation"))
			.put("POSITION", new FieldMapper("position"))
			.put("BIRTHDATE", new FieldMapper("birthdate"))
			.put("TERMINATION_DATE", new FieldMapper("terminationDate"))
			.put("UPDATE_ALLOWED_FLAG", new FieldMapper("updateAllowedFlag"))
			.put("DEFAULT_PRINTER_ID", new FieldMapper("defaultPrinterId"))
			.put("SUSPENDED_FLAG", new FieldMapper("suspendedFlag"))
			.put("SUPERVISOR_STAFF_ID", new FieldMapper("supervisorStaffId"))
			.put("COMM_RECEIPT_PRINTER_ID", new FieldMapper("commReceiptPrinterId"))
			.put("PERSONNEL_TYPE", new FieldMapper("personnelType"))
			.put("AS_OF_DATE", new FieldMapper("asOfDate"))
			.put("EMERGENCY_CONTACT", new FieldMapper("emergencyContact"))
			.put("ROLE", new FieldMapper("role"))
			.put("SEX_CODE", new FieldMapper("sexCode"))
			.put("STATUS", new FieldMapper("status"))
			.put("SUSPENSION_DATE", new FieldMapper("suspensionDate"))
			.put("SUSPENSION_REASON", new FieldMapper("suspensionReason"))
			.put("FORCE_PASSWORD_CHANGE_FLAG", new FieldMapper("forcePasswordChangeFlag"))
			.put("LAST_PASSWORD_CHANGE_DATE", new FieldMapper("lastPasswordChangeDate"))
			.put("LICENSE_CODE", new FieldMapper("licenseCode"))
			.put("LICENSE_EXPIRY_DATE", new FieldMapper("licenseExpiryDate"))
			.put("CREATE_DATETIME", new FieldMapper("createDateTime"))
			.put("CREATE_USER_ID", new FieldMapper("CreateUserId"))
			.put("MODIFY_DATETIME", new FieldMapper("modifyDateTime"))
			.put("MODIFY_USER_ID", new FieldMapper("modifyUserId"))
			.put("TITLE", new FieldMapper("title"))
			.put("NAME_SEQUENCE", new FieldMapper("nameSequence"))
			.put("QUEUE_CLUSTER_ID", new FieldMapper("queueClusterId"))
			.put("FIRST_LOGON_FLAG", new FieldMapper("firstLogonFlag"))
			.put("SUFFIX", new FieldMapper("Suffix"))
			.put("SEAL_FLAG", new FieldMapper("sealFlag")).build();

	private final Map<String, FieldMapper> oicOffencesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("OIC_OFFENCE_CODE", new FieldMapper("oicOffenceCode"))
			.put("DESCRIPTION", new FieldMapper("description"))
			.put("ACTIVE_FLAG", new FieldMapper("activeFlag"))
			.put("LIST_SEQ", new FieldMapper("listSeq"))
			.put("UPDATE_ALLOWED_FLAG", new FieldMapper("updateAllowedFlag"))
			.put("EXPIRY_DATE", new FieldMapper("expireDate"))
			.put("MODIFY_USER_ID", new FieldMapper("modifyUserId"))
			.put("OIC_OFFENCE_CATEGORY", new FieldMapper("oicOffenceCategory"))
			.put("OIC_OFFENCE_TYPE", new FieldMapper("oicOffenceType"))
			.put("MAX_PENALTY_MONTHS", new FieldMapper("maxPenaltyMonths"))
			.put("MAX_PENALTY_DAYS", new FieldMapper("maxPenaltyDays"))
			.put("CREATE_DATETIME", new FieldMapper("createDateTime"))
			.put("CREATE_USER_ID", new FieldMapper("createUserId"))
			.put("MODIFY_DATETIME", new FieldMapper("modifyDateTime"))
			.put("OIC_OFFENCE_ID", new FieldMapper("oicOffenceId"))
			.put("START_DATE", new FieldMapper("startDate"))
			.put("END_DATE", new FieldMapper("endDate"))
			.put("SEAL_FLAG", new FieldMapper("sealFlag")).build();

	private final Map<String, FieldMapper> agencyincidentrepairsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("AGENCY_INCIDENT_ID", new FieldMapper("agencyIncidentId"))
			.put("REPAIR_SEQ", new FieldMapper("repairSeq"))
			.put("REPAIR_TYPE", new FieldMapper("repairType"))
			.put("COMMENT_TEXT", new FieldMapper("commentText"))
			.put("MODIFY_USER_ID", new FieldMapper("modifyUserId"))
			.put("MODIFY_DATETIME", new FieldMapper("modifyDateTime"))
			.put("REPAIR_COST", new FieldMapper("repairCost"))
			.put("CREATE_DATETIME", new FieldMapper("createDateTime"))
			.put("CREATE_USER_ID", new FieldMapper("createUserId"))
			.put("SEAL_FLAG", new FieldMapper("sealFlag"))
			.build();

	private final Map<String, FieldMapper> agencyincidentpartiesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("AGENCY_INCIDENT_ID", new FieldMapper("agencyIncidentId"))
			.put("PARTY_SEQ", new FieldMapper("partySeq"))
			.put("INCIDENT_ROLE", new FieldMapper("incidentRole"))
			.put("OFFENDER_BOOK_ID", new FieldMapper("offenderBookId"))
			.put("STAFF_ID", new FieldMapper("staffId"))
			.put("PERSON_ID", new FieldMapper("personId"))
			.put("DISPOSITION_TYPE", new FieldMapper("dispositionType"))
			.put("DISPOSITION_DATE", new FieldMapper("dispositionDate"))
			.put("OIC_INCIDENT_ID", new FieldMapper("oicIncidentId"))
			.put("COMMENT_TEXT", new FieldMapper("commentText"))
			.put("CREATE_DATETIME", new FieldMapper("createDateTime"))
			.put("CREATE_USER_ID", new FieldMapper("createUserId"))
			.put("ACTION_CODE", new FieldMapper("actionCode"))
			.put("PARTY_ADDED_DATE", new FieldMapper("partyAddedDate"))
			.put("MODIFY_DATETIME", new FieldMapper("modifyDateTime"))
			.put("MODIFY_USER_ID", new FieldMapper("modifyUserId"))
			.put("SEAL_FLAG", new FieldMapper("sealFlag"))
			.put("FORCE_USED_FLAG" , new FieldMapper("forceUsedFlag"))
			.put("REPORT_COMPLETE_FLAG" , new FieldMapper("reportTypeFlag"))
			.put("REPORT_TYPE" , new FieldMapper("reportType"))
			.build();

	private final Map<String, FieldMapper> offendersMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("OFFENDER_ID", new FieldMapper("offenderId"))
			.put("OFFENDER_NAME_SEQ", new FieldMapper("offenderNameSeq"))
			.put("ID_SOURCE_CODE", new FieldMapper("idSourceCode"))
			.put("LAST_NAME", new FieldMapper("lastName"))
			.put("NAME_TYPE", new FieldMapper("nameType"))
			.put("FIRST_NAME", new FieldMapper("firstName"))
			.put("MIDDLE_NAME", new FieldMapper("middleName"))
			.put("BIRTH_DATE", new FieldMapper("birthDate"))
			.put("SEX_CODE", new FieldMapper("sexCode"))
			.put("SUFFIX", new FieldMapper("suffix"))
			.put("LAST_NAME_SOUNDEX", new FieldMapper("lastNameSoundex"))
			.put("BIRTH_PLACE", new FieldMapper("birthPlace"))
			.put("BIRTH_COUNTRY_CODE", new FieldMapper("birthCountryCode"))
			.put("CREATE_DATE", new FieldMapper("createDate"))
			.put("LAST_NAME_KEY", new FieldMapper("lastNameKey"))
			.put("ALIAS_OFFENDER_ID", new FieldMapper("aliasOffenderId"))
			.put("FIRST_NAME_KEY", new FieldMapper("firstNameKey"))
			.put("MIDDLE_NAME_KEY", new FieldMapper("middleNameKey"))
			.put("OFFENDER_ID_DISPLAY", new FieldMapper("offenderIdDisplay"))
			.put("ROOT_OFFENDER_ID", new FieldMapper("rootOffenderId"))
			.put("CASELOAD_TYPE", new FieldMapper("caseloadType"))
			.put("MODIFY_USER_ID", new FieldMapper("modifyUserId"))
			.put("MODIFY_DATETIME", new FieldMapper("modifyDateTime"))
			.put("ALIAS_NAME_TYPE", new FieldMapper("aliasNameType"))
			.put("PARENT_OFFENDER_ID", new FieldMapper("parentOffenderId"))
			.put("UNIQUE_OBLIGATION_FLAG", new FieldMapper("uniqueObligationFlag"))
			.put("SUSPENDED_FLAG", new FieldMapper("suspendedFlag"))
			.put("SUSPENDED_DATE", new FieldMapper("suspendedDate"))
			.put("RACE_CODE", new FieldMapper("raceCode"))
			.put("REMARK_CODE", new FieldMapper("remarkCode"))
			.put("ADD_INFO_CODE", new FieldMapper("addInfoCode"))
			.put("BIRTH_COUNTY", new FieldMapper("birthCounty"))
			.put("BIRTH_STATE", new FieldMapper("birthState"))
			.put("MIDDLE_NAME_2", new FieldMapper("middleName2"))
			.put("TITLE", new FieldMapper("title"))
			.put("AGE", new FieldMapper("age"))
			.put("CREATE_USER_ID", new FieldMapper("createUserId"))
			.put("LAST_NAME_ALPHA_KEY", new FieldMapper("lastNameAlphaKey"))
			.put("CREATE_DATETIME", new FieldMapper("createDateTime"))
			.put("NAME_SEQUENCE", new FieldMapper("nameSequence"))
			.put("SEAL_FLAG", new FieldMapper("sealFlag"))
			.build();

	private final Map<String, FieldMapper> systemProfiles = new ImmutableMap.Builder<String, FieldMapper>()
			.put("PROFILE_TYPE", new FieldMapper("profileType"))
			.put("PROFILE_CODE", new FieldMapper("profileCode"))
			.put("DESCRIPTION", new FieldMapper("description"))
			.put("PROFILE_VALUE", new FieldMapper("profileValue"))
			.put("PROFILE_VALUE_2", new FieldMapper("profileValue2"))
			.put("MODIFY_USER_ID", new FieldMapper("modifyUserId"))
			.put("OLD_TABLE_NAME", new FieldMapper("oldTableName"))
			.put("CREATE_DATETIME", new FieldMapper("createDateTime"))
			.put("CREATE_USER_ID", new FieldMapper("createUserId"))
			.put("MODIFY_DATETIME", new FieldMapper("modifyDateTime"))
			.put(" SEAL_FLAG  ", new FieldMapper("sealFlag"))
			.build();

	private final Map<String, FieldMapper> staffMemberRoles = new ImmutableMap.Builder<String, FieldMapper>()
			.put("STAFF_ID", new FieldMapper("staffId"))
			.put("ROLE_ID", new FieldMapper("roleId"))
			.put("CREATE_DATETIME", new FieldMapper("createDateTime"))
			.put("CREATE_USER_ID", new FieldMapper("createUserId"))
			.put("MODIFY_DATETIME", new FieldMapper("modifyDateTime"))
			.put("MODIFY_USER_ID", new FieldMapper("modifyUserId"))
			.put("ROLE_CODE", new FieldMapper("roleCode"))
			.put("SEAL_FLAG", new FieldMapper("sealFlag")).build();

	private final Map<String, FieldMapper> omsRoles = new ImmutableMap.Builder<String, FieldMapper>()
			.put("ROLE_ID", new FieldMapper("roleId"))
			.put("ROLE_NAME", new FieldMapper("roleName"))
			.put("ROLE_SEQ", new FieldMapper("roleSeq"))
			.put("CREATE_DATETIME", new FieldMapper("createDateTime"))
			.put("CREATE_USER_ID", new FieldMapper("createUserId"))
			.put("MODIFY_DATETIME", new FieldMapper("modifyDateTime"))
			.put("MODIFY_USER_ID", new FieldMapper("modifyUserId"))
			.put("ROLE_CODE", new FieldMapper("roleCode"))
			.put("PARENT_ROLE_CODE", new FieldMapper("parentRoleCode"))
			.put("SEAL_FLAG", new FieldMapper("sealflag"))
			.build();
	
	private final Map<String, FieldMapper> vNameSearchMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("OFFENDER_BOOK_ID", 				new FieldMapper("offenderBookId"))
			.put("OFFENDER_ID_DISPLAY", 		    new FieldMapper("offenderIdDisplay"))
			.put("ACTIVE_FLAG", 				    new FieldMapper("activeFlag"))
			.put("AGY_LOC_NAME", 				    new FieldMapper("agyLocName"))
			.put("MIDDLE_NAME", 					new FieldMapper("middleName"))
			.put("LIVING_UNIT_DESCRIPTION", 	    new FieldMapper("livingUnitDescription"))
			.put("IN_OUT_STATUS", 					new FieldMapper("inOutStatus"))
			.put("OFFENDER_ID", 					new FieldMapper("offenderId"))
			.put("LAST_NAME", 						new FieldMapper("lastName"))
			.put("AGY_LOC_ID", 						new FieldMapper("agyLocId"))
			.put("BOOKING_NO", 						new FieldMapper("bookingNo"))
			.put("BIRTH_DATE", 						new FieldMapper("birthDate"))
			.put("FIRST_NAME", 						new FieldMapper("firstName"))
			.build();
	private final Map<String, FieldMapper> SignificantIncidentMappping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("AGENCY_INCIDENT_ID", 				new FieldMapper("agencyIncidentId"))
			.put("SIGNIFICANCE_TYPE", 		    new FieldMapper("significanceType"))
			.put("MODIFY_USER_ID", 				    new FieldMapper("modifyUserId"))
			.put("CREATE_USER_ID", 				    new FieldMapper("createUserId"))
			.put("CREATE_DATETIME", 					new FieldMapper("createDateTime"))
			.put("MODIFY_DATETIME", 					new FieldMapper("modifyDateTime"))
			.put("SEAL_FLAG", 					new FieldMapper("sealFlag"))
			.put("ENTRY_DATE", 					new FieldMapper("entryDate"))
			.put("MODIFIED_STAFF_ID", 					new FieldMapper("modifiedStaffId"))
			.build();
	
	private final Map<String, FieldMapper> followUpDataMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("AGENCY_INCIDENT_ID", new FieldMapper("agencyIncidentId"))
			.put("INCIDENT_FOLLOW_UP_ID", new FieldMapper("incidentFollowUpId"))
			.put("POLICY", new FieldMapper("policy"))
			.put("COMPLIANCE", new FieldMapper("compliance"))
			.put("COMMENT_TEXT", new FieldMapper("commentText"))
			.put("MODIFY_USER_ID", new FieldMapper("modifyUserId"))
			.put("MODIFY_DATETIME", new FieldMapper("modifyDateTime"))
			.put("CREATE_DATETIME", new FieldMapper("createDateTime"))
			.put("CREATE_USER_ID", new FieldMapper("createUserId"))
			.put("SEAL_FLAG", new FieldMapper("sealFlag"))
			.build();
	
	private Integer agencyIncidentId;

	/**
	 * Creates new OidincdeServiceImpl class Object
	 */
	public OidincdeRepositoryImpl() {
		super();
	}

	/**
	 * method for query callings
	 * 
	 * @return List<AgencyIncidents>
	 * @param AgencyIncidents
	 *            searchBean
	 */
	@Override
	public List<AgencyIncidents> agencyIncidentsExecuteQuery(final AgencyIncidents searchBean) { 
		final String sql = queryBuilderFactory
				.getQueryBuilder(getQuery("OIDINCDE_AGENCYINCIDENTS_FIND_AGENCY_INCIDENTS"), agencyincidentsMapping)
				.build();
		String preparedSql = null;
		final StringBuffer sqlQuery = new StringBuffer();
		final MapSqlParameterSource params = new MapSqlParameterSource();
		sqlQuery.append(sql);
		if (searchBean != null) {
			sqlQuery.append(" where ");

			if (searchBean != null && searchBean.getReportedStaffId() != null) {
				sqlQuery.append(" REPORTED_STAFF_ID =:reportedStaffId  " + " and");
				params.addValue("reportedStaffId", searchBean.getReportedStaffId());
			}
			if (searchBean != null && searchBean.getReportStaffIdAsCode() != null) {
				sqlQuery.append(" REPORT_STAFF_ID_AS_CODE =:reportStaffIdAsCode  " + " and");
				params.addValue("reportStaffIdAsCode", searchBean.getReportStaffIdAsCode());
			}
			if (searchBean != null && searchBean.getAgencyIncidentId() != null) {
				sqlQuery.append(" AGENCY_INCIDENT_ID =:agencyIncidentId " + " and");
				params.addValue("agencyIncidentId", searchBean.getAgencyIncidentId());
			}
			if (searchBean != null && searchBean.getFromDate() != null && searchBean.getToDate() != null ) {
				sqlQuery.append(" domain='INC_TYPE'");
				sqlQuery.append(" and"+" INCIDENT_DATE between :fromDate and :toDate ORDER BY INCIDENT_DATE ,INCIDENT_TIME DESC " );
				params.addValue("fromDate", new Date(searchBean.getFromDate().getTime()));
				params.addValue("toDate", new Date(searchBean.getToDate().getTime()));
			}
			if (searchBean != null && searchBean.getInternalLocationId() != null) {
				sqlQuery.append(" INTERNAL_LOCATION_ID =:internalLocationId " + " and");
				params.addValue("internalLocationId", searchBean.getInternalLocationId());
			}
			if (searchBean != null && searchBean.getIncidentTime() != null) {
				sqlQuery.append(" TRUNC (INCIDENT_TIME) =to_date(:incidentTime) " + " and");
				params.addValue("incidentTime", new java.sql.Date(searchBean.getIncidentTime().getTime()));
			}
			if (searchBean != null && searchBean.getIncidentType() != null && !searchBean.getIncidentType().trim().equals("")) {
				sqlQuery.append(" INCIDENT_TYPE like  :incidentType " + " and");
				params.addValue("incidentType", "%" + searchBean.getIncidentType() + "%");
			}
			if (searchBean != null && searchBean.getIncidentTypeDescription() != null && !searchBean.getIncidentTypeDescription().trim().equals("")) {
				sqlQuery.append(" INCIDENT_TYPE_DESCRIPTION like  :incidentTypeDescription " + " and");
				params.addValue("incidentTypeDescription", "%" + searchBean.getIncidentTypeDescription() + "%");
			}
			if (searchBean != null && searchBean.getIncidentStatus() != null) {
				sqlQuery.append(" INCIDENT_STATUS like  :incidentStatus  " + " and");
				params.addValue("incidentStatus", "%" + searchBean.getIncidentStatus() + "%");
			}
			if (searchBean != null && searchBean.getCreateDateTime() != null) {
				sqlQuery.append(" TRUNC (CREATE_DATETIME) = :createDateTime " + " and");
				params.addValue("createDateTime", new Date(searchBean.getCreateDateTime().getTime()));
			}

			if (searchBean != null && searchBean.getCreateUserId() != null) {
				sqlQuery.append(" CREATE_USER_ID like  :createUserId   " + " and");
				params.addValue("createUserId", "%" + searchBean.getCreateUserId() + "%");
			}
			if (searchBean != null && searchBean.getModifyUserId() != null) {
				sqlQuery.append(" MODIFY_USER_ID like  :modifyUserId  " + " and");
				params.addValue("modifyUserId", "%" + searchBean.getModifyUserId() + "%");
			}
			if (searchBean != null && searchBean.getModifyDateTime() != null) {
				sqlQuery.append(" TRUNC (MODIFY_DATETIME) = :modifyDateTime " + " and");
				params.addValue("modifyDateTime", new Date(searchBean.getModifyDateTime().getTime()));
			}
			if (searchBean != null && searchBean.getLockFlag() != null) {
				sqlQuery.append(" LOCK_FLAG like  :lockFlag " + " and");
				params.addValue("lockFlag", "%" + searchBean.getLockFlag() + "%");
			}
			if (searchBean != null && searchBean.getIncidentDetails() != null) {
				sqlQuery.append(" INCIDENT_DETAILS  like :incidentDetailes " + " and");
				params.addValue("incidentDetailes", "%" + searchBean.getIncidentDetails() + "%");
			}
			if (searchBean != null && searchBean.getReportDate() != null) {
				sqlQuery.append(" REPORT_DATE =:reportDate  " + " and");
				params.addValue("reportDate", new Date(searchBean.getReportDate().getTime()));
			}
			if (searchBean != null && searchBean.getReportTime() != null) {
				sqlQuery.append(" TRUNC (REPORT_TIME) =:reportTime and");
				params.addValue("reportTime", new Date(searchBean.getReportTime().getTime()));
			}
			if (searchBean != null && searchBean.getLevelCode() != null) {
				sqlQuery.append(" LEVEL_CODE like :levelCode " + " and");
				params.addValue("levelCode", "%" + searchBean.getLevelCode() + "%");
			}
			if (searchBean != null && searchBean.getLogNo() != null) {
				sqlQuery.append(" LOG_NO like  :logNo " + " and");
				params.addValue("logNo", "%" + searchBean.getLogNo() + "%");
			}
			if (searchBean != null && searchBean.getIncidentText() != null) {
				sqlQuery.append(" INCIDENT_TEXT  like :incidentText  " + " and");
				params.addValue("incidentText", "%" + searchBean.getIncidentText() + "%");
			}
			if (searchBean != null && searchBean.getSealFlag() != null) {
				sqlQuery.append(" SEAL_FLAG  like :sealFlag  " + " and ");
				params.addValue("sealFlag", "%" + searchBean.getSealFlag() + "%");
			} 
			if (searchBean.getOffenderIdDisplay() != null) {
				sqlQuery.append(" (:offenderIdDisplay IS NULL OR AGENCY_INCIDENT_ID IN (SELECT AIP.AGENCY_INCIDENT_ID FROM AGENCY_INCIDENT_PARTIES AIP " +
	                            " WHERE AIP.OFFENDER_BOOK_ID IN (SELECT ob.offender_book_id FROM offenders o, offender_bookings ob " + 
	                            " WHERE o.offender_id_display like :offenderDisplayID AND o.root_offender_id = ob.root_offender_id )) )" + " and ");
				params.addValue("offenderIdDisplay",searchBean.getOffenderIdDisplay());
				params.addValue("offenderDisplayID","%" +searchBean.getOffenderIdDisplay() + "%");
			}
			if (searchBean.getAgyLocId() != null) {
				sqlQuery.append(
						" AGY_LOC_ID =:AGY_LOC_ID"
								+ " ORDER BY AGENCY_INCIDENT_ID DESC"); 
				params.addValue("AGY_LOC_ID",searchBean.getAgyLocId());
			}
		}
		preparedSql = sqlQuery.toString().trim();
		if (preparedSql.endsWith("where")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 5);
		}
		if (preparedSql.endsWith("and")) {
			sqlQuery.append(" domain='INC_TYPE'");
			preparedSql = sqlQuery.toString().trim();
			
		}
		final RowMapper<AgencyIncidents> offenderRowMapper = Row2BeanRowMapper.makeMapping(preparedSql,
				AgencyIncidents.class, agencyincidentsMapping);
		List<AgencyIncidents> returnList = new ArrayList<AgencyIncidents>();

		try {
			returnList = (namedParameterJdbcTemplate.query(preparedSql, params,
					offenderRowMapper));
			if(!returnList.isEmpty()){
				for(final AgencyIncidents agencyIncits :returnList ){
					agencyIncits.setCreateUserId(agencyIncits.getCreateUserId());
				}
			}
		} catch (Exception e) {
			logger.error(e);
		}
		
		return returnList;
	}

	/**
	 * Insert the records from database table
	 * 
	 * @param lstAgencyIncidents
	 * 
	 */
	@Override
	public Integer agencyIncidentsInsertAgencyIncidents(final List<AgencyIncidents> lstAgencyIncidents1) {
		final String sql = getQuery("OIDINCDE_AGENCYINCIDENTS_INSERT_AGENCY_INCIDENTS");
		for(AgencyIncidents obj:lstAgencyIncidents1){
			if (obj.getAgencyIncidentId() == null) {
				this.agencyIncidentId = agencyIncidentPreInsertcDAO();
				obj.setAgencyIncidentId(this.agencyIncidentId);
			}
		}
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final AgencyIncidents list : lstAgencyIncidents1) {
			parameters.add(new BeanPropertySqlParameterSource(list));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error(e);
		}
		if (lstAgencyIncidents1.size() == returnArray.length) {
			return this.agencyIncidentId;
		} else {
			return 0;
		}
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * AgencyIncidentPreInsertcDAO
	 *
	 *
	 */
	public Integer agencyIncidentPreInsertcDAO() {
		final String sql = getQuery("NEXT_ID_GET_AGENCY_INCIDENT_ID");
		final Integer returnObj = namedParameterJdbcTemplate.queryForObject(sql,createParams(),Integer.class);
		return returnObj;
	}

	/**
	 * Update the records from database table
	 * 
	 * @param lstAgencyIncidents
	 *
	 */
	public Integer agencyIncidentsUpdateAgencyIncidents(final List<AgencyIncidents> lstAgencyIncident) {
		final String sql = getQuery("OIDINCDE_AGENCYINCIDENTS_UPDATE_AGENCY_INCIDENTS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final AgencyIncidents list : lstAgencyIncident) {
			parameters.add(new BeanPropertySqlParameterSource(list));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error(e);
		}
		if (lstAgencyIncident.size() == returnArray.length) {
			return lstAgencyIncident.get(lstAgencyIncident.size()-1).getAgencyIncidentId();
		} else {
			return 0;
		}
	}

	/**
	 * method for query callings
	 * 
	 * @return List<AgencyIncidents>
	 * @param String
	 *            lstagencyincident
	 */
	@Override
	public List<AgencyIncidents> setUpdatedIncidentDetailsupdIncDetCur(final String lstagencyincident) {
		final String sql = queryBuilderFactory.getQueryBuilder(
				getQuery("OIDINCDE_SET_UPDATED_INCIDENT_DETAILS_UPD_INC_DET_CUR"), agencyincidentsMapping).build();
		final RowMapper<AgencyIncidents> offenderRowMapper = Row2BeanRowMapper.makeMapping(sql, AgencyIncidents.class,
				agencyincidentsMapping);
		List<AgencyIncidents> returnList = new ArrayList<AgencyIncidents>();
		try {
			returnList = (namedParameterJdbcTemplate.query(sql, createParams("agencyIncidentId", lstagencyincident),
					offenderRowMapper));
		} catch (Exception e) {
			logger.error(e);
		}
		return returnList;
	}

	/**
	 * method for query callings
	 * 
	 * @return List<AgencyIncidentCharges>
	 * @param AgencyIncidentCharges
	 *            searchRecord
	 */
	@Override
	public List<AgencyIncidentCharges> agencyIncidentChargesExecuteQuery(final AgencyIncidentCharges objSearchDao) {
		final String sql = queryBuilderFactory
				.getQueryBuilder(getQuery("OIDINCDE_AGENCYINCIDENTCHARGES_FIND_AGENCY_INCIDENT_CHARGES"),
						agencyincidentchargesMapping)
				.build();
		String preparedSql = null;
		final StringBuffer sqlQuery = new StringBuffer();
		final MapSqlParameterSource params = new MapSqlParameterSource();
		sqlQuery.append(sql);
		if (objSearchDao != null) {
			sqlQuery.append(" where ");

			if (objSearchDao != null && objSearchDao.getAgencyIncidentId() != null) {
				sqlQuery.append("AGENCY_INCIDENT_ID = :agendyIncidentId  " + " and");
				params.addValue("agendyIncidentId", objSearchDao.getAgencyIncidentId());
			}
		
			if (objSearchDao != null && objSearchDao.getPartySeq() != null) {
				sqlQuery.append(" PARTY_SEQ =:partyseq  " + " and");
				params.addValue("partyseq", objSearchDao.getPartySeq());
			}

		}
		preparedSql = sqlQuery.toString().trim();
		if (preparedSql.endsWith("where")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 5);
		}
		if (preparedSql.endsWith("and")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 3);
		}
	
		final RowMapper<AgencyIncidentCharges> offenderRowMapper = Row2BeanRowMapper.makeMapping(sql,
				AgencyIncidentCharges.class, agencyincidentchargesMapping);
		List<AgencyIncidentCharges> returnList = new ArrayList<AgencyIncidentCharges>();
		try {
			returnList = namedParameterJdbcTemplate.query(preparedSql, params, offenderRowMapper);
		} catch (Exception e) {
			logger.error(e);
		}
		return returnList;
	}

	/**
	 * Insert the records from database table
	 * 
	 * @param lstAgencyIncidentCharges
	 * 
	 */
	public Integer agencyIncidentChargesInsertAgencyIncidentCharges(
			final List<AgencyIncidentCharges> lstAgencyIncidentCharges) {
		final String sql = getQuery("OIDINCDE_AGENCYINCIDENTCHARGES_INSERT_AGENCY_INCIDENT_CHARGES");
		int returnArray = 0;
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		try {
		for (final AgencyIncidentCharges list : lstAgencyIncidentCharges) {
			parameters.add(new BeanPropertySqlParameterSource(list));
			returnArray = namedParameterJdbcTemplate.update(sql, new BeanPropertySqlParameterSource(list));
		}
		} catch (Exception e) {
			logger.error(e);
		}
		if (returnArray == 1) {
			return 1;
		} else {
			return 0;
		}
	}

	/**
	 * Update the records from database table
	 * 
	 * @param lstAgencyIncidentCharges
	 * 
	 */
	@Override
	public Integer agencyIncidentChargesUpdateAgencyIncidentCharges(
			final List<AgencyIncidentCharges> lstAgencyIncidentCharges) {
		final String sql = getQuery("OIDINCDE_AGENCYINCIDENTCHARGES_UPDATE_AGENCY_INCIDENT_CHARGES");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final AgencyIncidentCharges list : lstAgencyIncidentCharges) {
			parameters.add(new BeanPropertySqlParameterSource(list));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error(e);
		}
		if (lstAgencyIncidentCharges.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

	/**
	 * Delete the records from database table
	 * 
	 * @param lstAgencyIncidentCharges
	 * 
	 */
	@Override
	public Integer agencyIncidentChargesDeleteAgencyIncidentCharges(
			final List<AgencyIncidentCharges> lstAgencyIncidentCharges) {
		final String sql = getQuery("OIDINCDE_AGENCYINCIDENTCHARGES_DELETE_AGENCY_INCIDENT_CHARGES");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final AgencyIncidentCharges list : lstAgencyIncidentCharges) {
			parameters.add(new BeanPropertySqlParameterSource(list));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (DataIntegrityViolationException e) {
			final Integer listReturn=2;
			return listReturn;
		}
		if (lstAgencyIncidentCharges.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

	/**
	 * method for query callings
	 * 
	 * @return List<Object>
	 * @param String
	 *            lstagencyincident, String lstpartyseq
	 */
	@Override
	public List<Object> agyIncPartiesOffenderOnCheckDeleteMasteragencyIncidentChargesCur(final String lstagencyincident,
			final String lstpartyseq) {
		final String sql = queryBuilderFactory
				.getQueryBuilder(getQuery("OIDINCDE_AGY_INC_PARTIES_OFFENDER_ONCHECKDELETEMASTER_AGENCY"),
						agencyincidentchargesMapping)
				.build();
		List<Object> returnList = new ArrayList<Object>();
		try {
			returnList = namedParameterJdbcTemplate.queryForList(sql,
					createParams("agencyIncidentId", lstagencyincident, "partySeq", lstpartyseq), Object.class);
		} catch (Exception e) {
			logger.error(e);
		}
		return returnList;
	}

	/**
	 * method for query callings
	 * 
	 * @return List<CaseloadAgencyLocations>
	 * @param String
	 *            caseloadId
	 */
	@Override
	public List<CaseloadAgencyLocations> rgAgyLocIdsRecordGroup(final String caseloadId) {
		final String sql = queryBuilderFactory
				.getQueryBuilder(getQuery("OIDINCDE_FIND_RGAGYLOCIDS"), referenceCodeMapping).build();
		final RowMapper<CaseloadAgencyLocations> referenceCodeRowMapper = Row2BeanRowMapper.makeMapping(sql,
				CaseloadAgencyLocations.class, referenceCodeMapping);
		List<CaseloadAgencyLocations> returnList = new ArrayList<CaseloadAgencyLocations>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams("caseloadId", caseloadId),
					referenceCodeRowMapper);

		} catch (EmptyResultDataAccessException e) {
			logger.error(e);
			return Collections.emptyList();
		}
		return returnList;
	}

	/**
	 * method for query callings
	 * 
	 * @return List<CaseloadAgencyLocations>
	 * @param String
	 *            caseloadId
	 */
	@Override
	public List<CaseloadAgencyLocations> oidincdeState(final String caseloadId) {
		final String sql = queryBuilderFactory
				.getQueryBuilder(getQuery("OIDINCDE_OIDINCDE_STATE"), referenceCodeMapping).build();
		final RowMapper<CaseloadAgencyLocations> referenceCodeRowMapper = Row2BeanRowMapper.makeMapping(sql,
				CaseloadAgencyLocations.class, referenceCodeMapping);
		List<CaseloadAgencyLocations> returnList = new ArrayList<CaseloadAgencyLocations>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams("caseloadId", caseloadId),
					referenceCodeRowMapper);

		} catch (EmptyResultDataAccessException e) {
			logger.error(e);
			return Collections.emptyList();
		}
		return returnList;
	}

	/**
	 * method for query callings
	 * 
	 * @return List<ReferenceCodes>
	 * @param String
	 *            domain
	 */
	@Override
	public List<ReferenceCodes> rgIncidentTypesRecordGroup(final String domain) {
		final String sql = queryBuilderFactory
				.getQueryBuilder(getQuery("OIDINCDE_FIND_REFERENCE_CODES"), referenceCodeMapping).build();
		final RowMapper<ReferenceCodes> referenceCodeRowMapper = Row2BeanRowMapper.makeMapping(sql,
				ReferenceCodes.class, referenceCodeMapping);
		List<ReferenceCodes> returnList = new ArrayList<ReferenceCodes>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams("domain", domain), referenceCodeRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error(e);
			return Collections.emptyList();
		}
		return returnList;
	}

	/**
	 * method for query callings
	 * 
	 * @return List<AgencyInternalLocations>
	 * @param String
	 *            agencyLocId
	 */
	@Override
	public List<AgencyInternalLocations> rgLevelInternalLocationIdsRecordGroup(final String agencyLocId) {
		final String sql = queryBuilderFactory
				.getQueryBuilder(getQuery("OIDINCDE_FIND_RGLEVELINTERNALLOCATIONIDS"), referenceCodeMapping).build();
		final RowMapper<AgencyInternalLocations> referenceCodeRowMapper = Row2BeanRowMapper.makeMapping(sql,
				AgencyInternalLocations.class, referenceCodeMapping);
		List<AgencyInternalLocations> returnList = new ArrayList<AgencyInternalLocations>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams("agyLocId", agencyLocId),
					referenceCodeRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error(e);
			return Collections.emptyList();
		}
		return returnList;
	}

	/**
	 * method for query callings
	 * 
	 * @return List<StaffMembers>
	 * @param String
	 *            caseloadId
	 */
	@Override
	public List<StaffMembers> rgReportedStaffIdsRecordGroup(final String caseloadId) {
		final String sql = queryBuilderFactory
				.getQueryBuilder(getQuery("OIDINCDE_FIND_RGREPORTEDSTAFFIDS"), staffMembersMapping).build();
		final RowMapper<StaffMembers> referenceCodeRowMapper = Row2BeanRowMapper.makeMapping(sql, StaffMembers.class,
				staffMembersMapping);
		List<StaffMembers> returnList = new ArrayList<StaffMembers>();
		try {
			returnList = (namedParameterJdbcTemplate.query(sql, createParams("caseloadId", caseloadId),
					referenceCodeRowMapper));
		} catch (EmptyResultDataAccessException e) {
			logger.error(e);
		}
		return returnList;

	}

	/**
	 * method for query callings
	 * 
	 * @return List<OicOffences>
	 * @param String
	 *            startDate, String endDate
	 */
	@Override
	public List<OicOffences> rgOicOffenceCodesRecordGroup(final String startDate, final String endDate) {
		List<OicOffences> returnList = new ArrayList<OicOffences>();
		try {
			Date stDate = new SimpleDateFormat("dd-MM-yyyy").parse(startDate);  
			Date edDate = new SimpleDateFormat("dd-MM-yyyy").parse(endDate);  
			SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");  
			String stFormat = formatter.format(stDate);
			String edFormat = formatter.format(edDate);
		    final String sql = queryBuilderFactory
					.getQueryBuilder(getQuery("OIDINCDE_FIND_RGOICOFFENCECODES"), oicOffencesMapping).build();
			final RowMapper<OicOffences> referenceCodeRowMapper = Row2BeanRowMapper.makeMapping(sql, OicOffences.class,
					oicOffencesMapping);
		
			returnList = namedParameterJdbcTemplate.query(sql, createParams("startDate", stFormat, "endDate", edFormat),
					referenceCodeRowMapper);

		} catch (EmptyResultDataAccessException e) {
			logger.error(e);
			return Collections.emptyList();
		} catch (Exception e) {
			logger.error(e);
			return Collections.emptyList();
		}
		return returnList;
	}
	
	/**
	 * method for query callings
	 * 
	 * @return List<OicOffences>
	 * @param String
	 *            startDate, String endDate
	 */
	@Override
	public List<OicOffences> rgOicOffenceCodesRecord() {
		final String sql = queryBuilderFactory
				.getQueryBuilder(getQuery("CHAREGE_DATA"), oicOffencesMapping).build();
		final RowMapper<OicOffences> referenceCodeRowMapper = Row2BeanRowMapper.makeMapping(sql, OicOffences.class,
				oicOffencesMapping);
		List<OicOffences> returnList = new ArrayList<OicOffences>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams(),
					referenceCodeRowMapper);

		} catch (EmptyResultDataAccessException e) {
			logger.error(e);
			return Collections.emptyList();
		}
		return returnList;
	}

	/**
	 * method for query callings
	 * 
	 * @return List<OicOffences>
	 * @param String
	 *            systemMode
	 */
	@Override
	public List<OicOffences> agencyIncidentChargesPostQuery(final String systemMode) {
		final String sql = queryBuilderFactory
				.getQueryBuilder(getQuery("OIDINCDE_AGENCY_INCIDENT_CHARGES_POSTQUERY"), agencyincidentpartiesMapping)
				.build();
		final RowMapper<OicOffences> offenderRowMapper = Row2BeanRowMapper.makeMapping(sql, OicOffences.class,
				agencyincidentpartiesMapping);
		List<OicOffences> returnList = new ArrayList<OicOffences>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams("const0", systemMode), offenderRowMapper);
		} catch (Exception e) {
			logger.error(e);
		}
		return returnList;
	}

	/**
	 * method for query callings
	 * 
	 * @return List<ReferenceCodes>
	 * @param String
	 *            domain
	 */
	@Override
	public List<ReferenceCodes> rgRepairTypesRecordGroup(final String domain) {
		final String sql = queryBuilderFactory
				.getQueryBuilder(getQuery("OIDINCDE_FIND_REFERENCE_CODES"), referenceCodeMapping).build();
		final RowMapper<ReferenceCodes> referenceCodeRowMapper = Row2BeanRowMapper.makeMapping(sql,
				ReferenceCodes.class, referenceCodeMapping);
		List<ReferenceCodes> returnList = new ArrayList<>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams("domain", domain), referenceCodeRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error(e);
			return Collections.emptyList();
		}
		return returnList;
	}

	/**
	 * method for query callings
	 * 
	 * @return List<ReferenceCodes>
	 * @param String
	 *            domain
	 */
	@Override
	public List<ReferenceCodes> rgOffInvActionCodesRecordGroup(final String domain) {
		final String sql = queryBuilderFactory
				.getQueryBuilder(getQuery("OIDINCDE_FIND_REFERENCE_CODES"), referenceCodeMapping).build();
		final RowMapper<ReferenceCodes> referenceCodeRowMapper = Row2BeanRowMapper.makeMapping(sql,
				ReferenceCodes.class, referenceCodeMapping);
		List<ReferenceCodes> returnList = new ArrayList<>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams("domain", domain), referenceCodeRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error(e);
			return Collections.emptyList();
		}
		return returnList;
	}

	/**
	 * method for query callings
	 * 
	 * @return List<ReferenceCodes>
	 * @param String
	 *            domain
	 */
	@Override
	public List<ReferenceCodes> rgOffInvIncidentRolesRecordGroup(final String domain) {
		final String sql = queryBuilderFactory
				.getQueryBuilder(getQuery("OIDINCDE_FIND_REFERENCE_CODES"), referenceCodeMapping).build();
		final RowMapper<ReferenceCodes> referenceCodeRowMapper = Row2BeanRowMapper.makeMapping(sql,
				ReferenceCodes.class, referenceCodeMapping);
		List<ReferenceCodes> returnList = new ArrayList<>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams("domain", domain), referenceCodeRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error(e);
			return Collections.emptyList();
		}
		return returnList;
	}

	/**
	 * method for query callings
	 * 
	 * @return List<ReferenceCodes>
	 * @param String
	 *            domain
	 */
	@Override
	public List<ReferenceCodes> rgStaffInvIncidentRolesRecordGroup(final String domain) {
		final String sql = queryBuilderFactory
				.getQueryBuilder(getQuery("OIDINCDE_FIND_REFERENCE_CODES"), referenceCodeMapping).build();
		final RowMapper<ReferenceCodes> referenceCodeRowMapper = Row2BeanRowMapper.makeMapping(sql,
				ReferenceCodes.class, referenceCodeMapping);
		List<ReferenceCodes> returnList = new ArrayList<>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams("domain", domain), referenceCodeRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error(e);
			return Collections.emptyList();
		}
		return returnList;
	}

	/**
	 * method for query callings
	 * 
	 * @return List<ReferenceCodes>
	 * @param ReferenceCodes
	 *            refCodeBean
	 */
	@Override
	public List<ReferenceCodes> searchEvidenceType(final ReferenceCodes refCodeBean) {
		final String sql = queryBuilderFactory
				.getQueryBuilder(getQuery("OIDINCDE_FIND_SEARCHEVIDENCETYPE"), referenceCodeMapping).build();
		final RowMapper<ReferenceCodes> offenderRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				referenceCodeMapping);
		List<ReferenceCodes> returnList = new ArrayList<>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql,
					createParams("domain", refCodeBean.getDomain(), "code", refCodeBean.getCode()), offenderRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error(e);
			return Collections.emptyList();
		}
		return returnList;
	}

	/**
	 * method for query callings
	 * 
	 * @return List<AgencyIncidentRepairs>
	 * @param AgencyIncidentRepairs
	 *            objSearchDao
	 */
	@Override
	public List<AgencyIncidentRepairs> agencyIncidentRepairsExecuteQuery(final AgencyIncidentRepairs objSearchDao) {
		final String sql = queryBuilderFactory
				.getQueryBuilder(getQuery("OIDINCDE_AGENCYINCIDENTREPAIRS_FIND_AGENCY_INCIDENT_REPAIRS"),
						agencyincidentrepairsMapping)
				.build();
 		final RowMapper<AgencyIncidentRepairs> offenderRowMapper = Row2BeanRowMapper.makeMapping(sql,
				AgencyIncidentRepairs.class, agencyincidentrepairsMapping);
		List<AgencyIncidentRepairs> returnList = new ArrayList<AgencyIncidentRepairs>();
		try {
			returnList = (namedParameterJdbcTemplate.query(sql,
					createParams("agencyIncidentId", objSearchDao.getAgencyIncidentId()), offenderRowMapper));
		} catch (EmptyResultDataAccessException e) {
			logger.error(e);
		}
		return returnList;
	}

	/**
	 * method for query callings
	 * 
	 * @return List<Object>
	 * @param String
	 *            lstagencyincident
	 */
	@Override
	public List<Object> agencyIncidentsOnCheckDeleteMasteragencyIncidentRepairsCur(final String lstagencyincident) {
		final String sql = queryBuilderFactory.getQueryBuilder(
				getQuery("OIDINCDE_AGENCY_INCIDENTS_ONCHECKDELETEMASTER_AGENCY"), agencyincidentrepairsMapping).build();
		List<Object> returnList = new ArrayList<Object>();
		try {
			returnList = namedParameterJdbcTemplate.queryForList(sql,
					createParams("agencyIncidentId", lstagencyincident), Object.class);
		} catch (Exception e) {
			logger.error(e);
		}
		return returnList;
	}

	/**
	 * Insert the records from database table
	 * 
	 * @param lstAgencyIncidentRepairs
	 *
	 */
	@Override
	public Integer agencyIncidentRepairsInsertAgencyIncidentRepairs(
			final List<AgencyIncidentRepairs> lstAgencyIncidentRepairs) {
		final String sql = getQuery("OIDINCDE_AGENCYINCIDENTREPAIRS_INSERT_AGENCY_INCIDENT_REPAIRS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final AgencyIncidentRepairs list : lstAgencyIncidentRepairs) {
			parameters.add(new BeanPropertySqlParameterSource(list));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error(e);
		}
		if (lstAgencyIncidentRepairs.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

	/**
	 * Update the records from database table
	 * 
	 * @param lstAgencyIncidentRepairs
	 * 
	 */
	@Override
	public Integer agencyIncidentRepairsUpdateAgencyIncidentRepairs(
			final List<AgencyIncidentRepairs> lstAgencyIncidentRepairs) {
		final String sql = getQuery("OIDINCDE_AGENCYINCIDENTREPAIRS_UPDATE_AGENCY_INCIDENT_REPAIRS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final AgencyIncidentRepairs list : lstAgencyIncidentRepairs) {
			parameters.add(new BeanPropertySqlParameterSource(list));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error(e);
		}
		if (lstAgencyIncidentRepairs.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

	/**
	 * Delete the records from database table
	 * 
	 * @param lstAgencyIncidentRepairs
	 * 
	 */
	@Override
	public Integer agencyIncidentRepairsDeleteAgencyIncidentRepairs(
			final List<AgencyIncidentRepairs> lstAgencyIncidentRepairs) {
		final String sql = getQuery("OIDINCDE_AGENCYINCIDENTREPAIRS_DELETE_AGENCY_INCIDENT_REPAIRS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final AgencyIncidentRepairs list : lstAgencyIncidentRepairs) {
			parameters.add(new BeanPropertySqlParameterSource(list));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error(e);
		}
		if (lstAgencyIncidentRepairs.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

	/**
	 * method for query callings
	 * 
	 * @return List<AgencyIncidentParties>
	 * @param AgencyIncidentParties
	 *            objSearchDao
	 */
	@Override
	public List<AgencyIncidentParties> agyIncPartiesOffenderExecuteQuery(final AgencyIncidentParties objSearchDao) {
		final String sql = queryBuilderFactory
				.getQueryBuilder(getQuery("OIDINCDE_AGYINCPARTIESOFFENDER_FIND_AGENCY_INCIDENT_PARTIES"),
						agencyincidentpartiesMapping)
				.build(); 
		final RowMapper<AgencyIncidentParties> offenderRowMapper = Row2BeanRowMapper.makeMapping(sql,
				AgencyIncidentParties.class, agencyincidentpartiesMapping);
		List<AgencyIncidentParties> returnList = new ArrayList<AgencyIncidentParties>();
		final MapSqlParameterSource params = new MapSqlParameterSource();
		String preparedSql = null;
		String preSql = null;
		final StringBuffer sqlQuery = new StringBuffer();
		sqlQuery.append(sql);
		if (objSearchDao != null) {
			sqlQuery.append(" where ");

			if (objSearchDao.getAgencyIncidentId() != null) {
				sqlQuery.append("AGENCY_INCIDENT_ID = :agencyincidentid   " + " and");
				params.addValue("agencyincidentid", objSearchDao.getAgencyIncidentId());
			}
			if (objSearchDao.getPartySeq() != null) {
				sqlQuery.append(" PARTY_SEQ = :partyseq " + " and");
				params.addValue("partyseq", objSearchDao.getPartySeq());
			}
			if (objSearchDao.getIncidentRole() != null) {
				sqlQuery.append(" INCIDENT_ROLE LIKE   :incidentRole  " + " and");
				params.addValue("incidentRole", "%" + objSearchDao.getIncidentRole() + "%");
			}
			if (objSearchDao.getOffenderBookId() != null) {
				sqlQuery.append(" OFFENDER_BOOK_ID =:offenderBookId  " + " and");
				params.addValue("offenderBookId", objSearchDao.getOffenderBookId());
			}
			if (objSearchDao.getStaffId() != null) {
				sqlQuery.append(" STAFF_ID =:staffId  " + " and");
				params.addValue("staffId", objSearchDao.getStaffId());
			}
			if (objSearchDao.getPersonId() != null) {
				sqlQuery.append(" PERSON_ID =:personId  " + " and");
				params.addValue("personId", objSearchDao.getPersonId());
			}
			if (objSearchDao.getDispositionType() != null) {
				sqlQuery.append(" DISPOSITION_TYPE LIKE  :dispositionTypeLike  " + " and");
				params.addValue("dispositionTypeLike", "%" + objSearchDao.getDispositionType() + "%");
			}
			if (objSearchDao.getDispositionDate() != null) {
				sqlQuery.append(" DISPOSITION_DATE = :dispositionDate  " + " and");
				params.addValue("dispositionDate", new Date(objSearchDao.getDispositionDate().getTime()));
			}
			if (objSearchDao.getOicIncidentId() != null) {
				sqlQuery.append(" OIC_INCIDENT_ID =:oiciIncidentId  " + " and");
				params.addValue("oiciIncidentId", objSearchDao.getOicIncidentId());
			}
			if (objSearchDao.getCommentText() != null) {
				sqlQuery.append(" COMMENT_TEXT LIKE  :commenttextlike    " + " and");
				params.addValue("commenttextlike", "%" + objSearchDao.getCommentText() + "%");
			}
			if (objSearchDao.getCreateDateTime() != null) {
				sqlQuery.append(" CREATE_DATETIME = :createDateTime  " + " and");
				params.addValue("createDateTime", new Date(objSearchDao.getCreateDateTime().getTime()));
			}
			if (objSearchDao.getCreateUserId() != null) {
				sqlQuery.append(" CREATE_USER_ID LIKE  :createUserId   " + " and");
				params.addValue("createUserId", "%" + objSearchDao.getCreateUserId() + "%");
			}
			if (objSearchDao.getActionCode() != null) {
				sqlQuery.append(" ACTION_CODE LIKE  :actionCode " + " and");
				params.addValue("actionCode", "%" + objSearchDao.getCreateUserId() + "%");
			}
			if (objSearchDao.getPartyAddedDate() != null) {
				sqlQuery.append(" PARTY_ADDED_DATE = :partyAddedDate " + " and");
				params.addValue("partyAddedDate", new Date(objSearchDao.getPartyAddedDate().getTime()));
			}
			if (objSearchDao.getModifyDateTime() != null) {
				sqlQuery.append(" MODIFY_DATETIME = :modifyDateTime " + " and");
				params.addValue("modifyDateTime", new Date(objSearchDao.getModifyDateTime().getTime()));
			}
			if (objSearchDao.getModifyUserId() != null) {
				sqlQuery.append(" MODIFY_USER_ID LIKE  :modifyUserId " + " and");
				params.addValue("modifyUserId", "%" + objSearchDao.getModifyUserId() + "%");
			}
			if (objSearchDao.getSealFlag() != null) {
				sqlQuery.append(" SEAL_FLAG LIKE  :sealFlag and");
				params.addValue("sealFlag", "%" + objSearchDao.getSealFlag() + "%");
			}
			if (objSearchDao.getRepCompletFlag() != null) {
				sqlQuery.append(" REP_COMPLET_FLAG LIKE  :repCompleteFlag  " + " and");
				params.addValue("repCompleteFlag", "%" + objSearchDao.getSealFlag() + "%");
			}
		}
		preparedSql = sqlQuery.toString().trim();
		if (preparedSql.endsWith("where")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 5);
		}
		if (preparedSql.endsWith("and")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 3);
		}
		preSql = preparedSql.concat("AND OFFENDER_BOOK_ID IS NOT NULL AND STAFF_ID IS NULL ORDER BY  PARTY_SEQ");
		try {
			returnList = (namedParameterJdbcTemplate.query(preSql, params, offenderRowMapper));
		} catch (Exception e) {
			logger.error(e);
		}
		return returnList;

	}
	
	@Override
	public List<AgencyIncidentParties> agyIncPartiesOffenderStaffxecuteQuery(final AgencyIncidentParties objSearchDao) {
		final String sql = queryBuilderFactory
				.getQueryBuilder(getQuery("OIDINCDE_AGYINCPARTIES_STAFF_FIND_AGENCY_INCIDENT_PARTIES"),
						agencyincidentpartiesMapping)
				.build();
		final RowMapper<AgencyIncidentParties> offenderRowMapper = Row2BeanRowMapper.makeMapping(sql,
				AgencyIncidentParties.class, agencyincidentpartiesMapping);
		List<AgencyIncidentParties> returnList = new ArrayList<AgencyIncidentParties>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams("agencyIncidentId",objSearchDao.getAgencyIncidentId()),
					offenderRowMapper);
			
		} catch (Exception e) {
			logger.error(e);
		}
		return returnList;

	}

	/**
	 * Insert the records from database table
	 * 
	 * @param lstAgencyIncidentParties
	 * 
	 */
	@Override
	public Integer agyIncPartiesOffenderInsertAgencyIncidentParties(
			final List<AgencyIncidentParties> lstAgencyIncidentParties) {
		Integer partySeq = 0;
		for (final AgencyIncidentParties list : lstAgencyIncidentParties) {
			if (list.getAgencyIncidentId() != null &&  partySeq == 0) {
				partySeq = agencyIncidentPartiesPreSeqPreInsertcDAO(list.getAgencyIncidentId());
			}
				list.setPartySeq(partySeq);
				partySeq = partySeq + 1;
		}
		final String sql = getQuery("OIDINCDE_AGYINCPARTIESOFFENDER_INSERT_AGENCY_INCIDENT_PARTIES");
		int returnArray = 0;
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		try{
		for (final AgencyIncidentParties list : lstAgencyIncidentParties) {
			parameters.add(new BeanPropertySqlParameterSource(list));
			returnArray = namedParameterJdbcTemplate.update(sql, new BeanPropertySqlParameterSource(list));
		}
		} catch (Exception e) {
			logger.error(e);
		}
		if (returnArray == 1) {
			return 1;
		} else {
			return 0;
		}
	}
	public List<VNameSearch> nameSrchExecuteQuery(final VNameSearch objSearchDao) {
		final String sql = getQuery("OIINAMES_NAMESRCH_FIND_V_NAME_SEARCH");
		final RowMapper<VNameSearch> VNameSearchRowMapper = Row2BeanRowMapper.makeMapping(sql, VNameSearch.class,
				vNameSearchMapping);
		final ArrayList<VNameSearch> returnList = (ArrayList<VNameSearch>) namedParameterJdbcTemplate.query(sql,
				createParams("OFFENDER_ID_DISPLAY",objSearchDao.getOffenderIdDisplay(),"USERID",objSearchDao.getCreateUserId()), VNameSearchRowMapper);
		logger.info("nameSrchExecuteQuery Listnames : {}", returnList);
		return returnList;
	}
	/**
	 * Insert the records from database table
	 * 
	 * @param lstAgencyIncidentParties
	 * 
	 */
	@Override
	public Integer agyIncPartiesStaffInsertAgency(
			final List<AgencyIncidentParties> lstAgencyIncidentParties) {
		Integer partySeq = 0;
		for (final AgencyIncidentParties list : lstAgencyIncidentParties) {
			if (list.getAgencyIncidentId() != null) {
				partySeq = agencyIncidentPartiesPreSeqPreInsertcDAO(list.getAgencyIncidentId());
				list.setPartySeq(partySeq);
			}
		}
		final String sql = getQuery("OIDINCDE_AGYINCPARTIESOFFENDER_INSERT_AGENCY_INCIDENT_PARTIES");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final AgencyIncidentParties list : lstAgencyIncidentParties) {
			parameters.add(new BeanPropertySqlParameterSource(list));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error(e);
		}
		if (lstAgencyIncidentParties.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * agencyIncidentPartiesPreSeqPreInsertcDAO
	 *
	 *
	 */

	/**
	 * Update the records from database table
	 * 
	 * @param lstAgencyIncidentParties
	 *
	 */
	@Override
	public Integer agyIncPartiesOffenderUpdateAgencyIncidentParties(
			final List<AgencyIncidentParties> lstAgencyIncidentParties) {
		final String sql = getQuery("OIDINCDE_AGYINCPARTIESOFFENDER_UPDATE_AGENCY_INCIDENT_PARTIES");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final AgencyIncidentParties list : lstAgencyIncidentParties) {
			parameters.add(new BeanPropertySqlParameterSource(list));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error(e);
		}
		if (lstAgencyIncidentParties.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

	/**
	 * Delete the records from database table
	 * 
	 * @param lstAgencyIncidentParties
	 * 
	 */
	@Override
	public Integer agyIncPartiesOffenderDeleteAgencyIncidentParties(
			final List<AgencyIncidentParties> lstAgencyIncidentParties) {
		final String sql = getQuery("OIDINCDE_AGYINCPARTIESOFFENDER_DELETE_AGENCY_INCIDENT_PARTIES");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final AgencyIncidentParties list : lstAgencyIncidentParties) {
			parameters.add(new BeanPropertySqlParameterSource(list));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) { 
			logger.error(e);
			if(e.getMessage().contains("incident_staff_reports_fk1")|| e.getMessage().contains("incident_offender_weapons_fk") || e.getMessage().contains("incident_reportable_details_fk1")) {
				return 2;
			}
			
		}
		if (lstAgencyIncidentParties.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

	/**
	 * Insert the records from database table
	 * 
	 * @param lstAgencyIncidentParties
	 * 
	 */
	public Integer agencyIncidentPartiesPreSeqPreInsert(final List<AgencyIncidentParties> lstAgencyIncidentParties) {
		final String sql = queryBuilderFactory
				.getQueryBuilder(getQuery("OIDINCDE_AGENCYINCIDENTPARTIES_FIND_AGENCY_INCIDENT_PARTIES_OIC"),
						agencyincidentpartiesMapping)
				.build();
		return (Integer) namedParameterJdbcTemplate.queryForObject(sql, createParams(), Integer.class);
	}

	/**
	 * method for query callings
	 * 
	 * @return List<Object>
	 * @param String
	 *            lstagencyincident
	 */
	@Override
	public List<Object> agencyIncidentsOnCheckDeleteMasteragyIncPartiesOffenderCur(final String lstagencyincident) {
		final String sql = queryBuilderFactory
				.getQueryBuilder(getQuery("OIDINCDE_AGENCY_INCIDENTS_ONCHECKDELETEMASTER_AGY_INC_PARTIES_CUR"),
						agencyincidentpartiesMapping)
				.build();
		List<Object> returnList = new ArrayList<Object>();
		try {
			returnList = namedParameterJdbcTemplate.queryForList(sql,
					createParams("agencyIncidentId", lstagencyincident), Object.class);
		} catch (Exception e) {
			logger.error(e);
		}
		return returnList;
	}

	/**
	 * method for query callings
	 * 
	 * @return List<AgencyIncidentParties>
	 * @param String
	 *            offenderBookId, String agencyIncidentId
	 */
	@Override
	public Integer agyIncPartiesOffenderPreInsert(final Integer offenderbookId,
			final String agencyIncidentId) {
		final String sql = queryBuilderFactory
				.getQueryBuilder(getQuery("OIDINCDE_AGY_INC_PARTIES_OFFENDER_PREINSERT"), agencyincidentpartiesMapping)
				.build();
		Integer returnList = 0;
		try {
			returnList = namedParameterJdbcTemplate.queryForObject(sql, createParams("OFFENDER_BOOK_ID", offenderbookId,
					"AGENCY_INCIDENT_ID", agencyIncidentId != null ? new BigDecimal(agencyIncidentId) : null),
					Integer.class);
		} catch (Exception e) {
			logger.error(e);
		}
		return returnList;
	}

	/**
	 * method for query callings
	 * 
	 * @return List<Object>
	 * @param String
	 *            lstagencyincident
	 */
	@Override
	public List<Object> agencyIncidentsOnCheckDeleteMasteragyIncPartiesStaffCur(final String lstagencyincident) {
		final String sql = queryBuilderFactory
				.getQueryBuilder(getQuery("OIDINCDE_AGENCY_INCIDENTS_ONCHECKDELETEMASTER_AGY_INC_PARTIES_CUR"),
						agencyincidentpartiesMapping)
				.build();
		List<Object> returnList = new ArrayList<Object>();
		try {
			returnList = namedParameterJdbcTemplate.queryForList(sql,
					createParams("agencyIncidentId", lstagencyincident), Object.class);
		} catch (Exception e) {
			logger.error(e);
		}
		return returnList;
	}

	/**
	 * method for query callings
	 * 
	 * @return List<OicOffences>
	 * @param AgencyIncidentCharges
	 *            searchRecord
	 */
	@Override
	public List<OicOffences> agyIncidentChargePostQuery(final AgencyIncidentCharges searchRecord) {
		final String sql = queryBuilderFactory
				.getQueryBuilder(getQuery("OIDINCDE_FIND_RGOICOFFENCECODES"), oicOffencesMapping).build();
		final RowMapper<OicOffences> offenderRowMapper = Row2BeanRowMapper.makeMapping(sql, OicOffences.class,
				oicOffencesMapping);
		List<OicOffences> returnList = new ArrayList<OicOffences>();
		try {
			returnList = (ArrayList<OicOffences>) (namedParameterJdbcTemplate.query(sql,
					createParams("OIC_OFFENCE_ID", searchRecord.getChargedOicOffenceId()), offenderRowMapper));
		} catch (Exception e) {
			logger.error(e);
		}
		return returnList;
	}

	/**
	 * method for query callings
	 * 
	 * @return List<StaffMembers>
	 * @param StaffMembers
	 *            staffCodeBean
	 */
	@Override
	public List<StaffMembers> searchStaffMembers(final StaffMembers staffCodeBean) {
		final String sql = queryBuilderFactory
				.getQueryBuilder(getQuery("OIDINCDE_FIND_SEARCHSTAFFMEMBERS"), staffMembersMapping).build();
		final RowMapper<StaffMembers> offenderRowMapper = Row2BeanRowMapper.makeMapping(sql, StaffMembers.class,
				staffMembersMapping);
		List<StaffMembers> returnList = new ArrayList<StaffMembers>();
		try {
			returnList = (ArrayList<StaffMembers>) namedParameterJdbcTemplate.query(sql,
					createParams("staffId", staffCodeBean.getStaffId()), offenderRowMapper);
		} catch (Exception e) {
			logger.error(e);
		}
		return returnList;
	}
	
	/**
	 * method for query callings
	 * 
	 * @return List<StaffMembers>
	 * @param StaffMembers staffCodeBean
	 */
	@Override
	public List<StaffMembers> searchStaffMembersByUserId(final StaffMembers staffCodeBean) {
		final String sql = queryBuilderFactory
				.getQueryBuilder(getQuery("OIDINCDE_FIND_SEARCHSTAFFMEMBERS_BY_USERID"), staffMembersMapping).build();
		final RowMapper<StaffMembers> offenderRowMapper = Row2BeanRowMapper.makeMapping(sql, StaffMembers.class,
				staffMembersMapping);
		List<StaffMembers> returnList = new ArrayList<StaffMembers>();
		try {
			returnList = (ArrayList<StaffMembers>) namedParameterJdbcTemplate.query(sql,
					createParams("userId", staffCodeBean.getUserId()), offenderRowMapper);
		} catch (Exception e) {
			logger.error(e);
		}
		return returnList;
	}

	/**
	 * method for query callings
	 * 
	 * @return List<StaffMembers>
	 * @param String
	 *            systemMode
	 */
	@Override
	public List<StaffMembers> setGlobalCaseloadIdworkingCaseloadCur(final String systemMode) {
		final String sql = queryBuilderFactory.getQueryBuilder(
				getQuery("OIDINCDE_SET_GLOBAL_CASELOAD_ID_WORKING_CASELOAD_CUR"), agencyincidentpartiesMapping).build();
		final RowMapper<StaffMembers> offenderRowMapper = Row2BeanRowMapper.makeMapping(sql, StaffMembers.class,
				agencyincidentpartiesMapping);
		List<StaffMembers> returnList = new ArrayList<StaffMembers>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams("const0", systemMode), offenderRowMapper);
		} catch (Exception e) {
			logger.error(e);
		}
		return returnList;
	}

	/**
	 * method for query callings
	 * 
	 * @return Object
	 * @param String
	 *            systemMode
	 */
	@Override
	public Object oidincdeStateadmUsrCsr(final String systemMode, final String userName) {
		final String sql = queryBuilderFactory
				.getQueryBuilder(getQuery("OIDINCDE_OIDINCDE_STATE_ADM_USR_CSR"), agencyincidentpartiesMapping).build();
		final RowMapper<StaffMembers> offenderRowMapper = Row2BeanRowMapper.makeMapping(sql, StaffMembers.class,
				agencyincidentpartiesMapping);
		List<StaffMembers> returnList = new ArrayList<StaffMembers>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams("const0", systemMode, "USERNAME", userName),
					offenderRowMapper);
		} catch (Exception e) {
			logger.error(e);
		}
		return returnList;
	}

	/**
	 * method for query callings
	 * 
	 * @return List<Offenders>
	 * @param Offenders
	 *            offenders
	 */
	public List<Offenders> agencyIncidentsPartiesOffenderIdCur(final Offenders offenders) {
		final String sql = queryBuilderFactory.getQueryBuilder(getQuery("OIDINCDE_FIND_OFFENDERS"), offendersMapping)
				.build();
		final RowMapper<Offenders> offenderRowMapper = Row2BeanRowMapper.makeMapping(sql, Offenders.class,
				offendersMapping);
		List<Offenders> returnList = new ArrayList<Offenders>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql,
					createParams("offenderBookId", offenders.getOffenderId()), offenderRowMapper);
		} catch (Exception e) {
			logger.error(e);
		}
		return returnList;
	}

	/**
	 * method for query callings
	 * 
	 * @return List<SystemProfiles>
	 * @param String
	 *            systemMode
	 */
	@Override
	public List<SystemProfiles> checkUnlockAccessspProfileCur(final String systemMode) {
		final String sql = queryBuilderFactory
				.getQueryBuilder(getQuery("OIDINCDE_CHECK_UNLOCK_ACCESS_SP_PROFILE_CUR"), systemProfiles).build();
		final RowMapper<SystemProfiles> offenderRowMapper = Row2BeanRowMapper.makeMapping(sql, SystemProfiles.class,
				systemProfiles);
		List<SystemProfiles> refList = new ArrayList<SystemProfiles>();
		try {
			refList = namedParameterJdbcTemplate.query(sql, createParams("const0", systemMode), offenderRowMapper);
		} catch (Exception e) {
			logger.error(e);
		}
		return refList;
	}

	/**
	 * method for query callings
	 * 
	 * @return List<StaffMemberRoles>
	 * @param String
	 *            pValue3
	 */
	@Override
	public List<StaffMemberRoles> checkUnlockAccesscheckUnlockAccess(final String pValue) {
		final String sql = queryBuilderFactory
				.getQueryBuilder(getQuery("OIDINCDE_CHECK_UNLOCK_ACCESS_CHECK_UNLOCK_ACCESS"), staffMemberRoles)
				.build();
		final RowMapper<StaffMemberRoles> offenderRowMapper = Row2BeanRowMapper.makeMapping(sql, StaffMemberRoles.class,
				staffMemberRoles);
		List<StaffMemberRoles> returnList = new ArrayList<StaffMemberRoles>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams("pValue3", pValue), offenderRowMapper);
		} catch (Exception e) {
			logger.error(e);
		}
		return returnList;
	}

	/**
	 * method for query callings
	 * 
	 * @return List<OmsRoles>
	 * @param String
	 *            pValue, String pValue2
	 */
	@Override
	public List<OmsRoles> checkUnlockAccesscheckRoleAccess(final String pValue, final String pValue2) {
		final String sql = queryBuilderFactory
				.getQueryBuilder(getQuery("OIDINCDE_CHECK_UNLOCK_ACCESS_CHECK_ROLE_ACCESS"), omsRoles).build();
		final RowMapper<OmsRoles> offenderRowMapper = Row2BeanRowMapper.makeMapping(sql, OmsRoles.class, omsRoles);
		List<OmsRoles> returnList = new ArrayList<OmsRoles>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams("pValue", pValue, "pValue2", pValue2),
					offenderRowMapper);
		} catch (Exception e) {
			logger.error(e);
		}
		return returnList;
	}

	public List<StaffMembers> staffidExecuteQuery(final String userId) {
		final String sql = queryBuilderFactory.getQueryBuilder(getQuery("OIDINCDE_STAFF_ID"), staffMemberRoles).build();
		final RowMapper<StaffMembers> offenderRowMapper = Row2BeanRowMapper.makeMapping(sql, StaffMembers.class,
				agencyincidentpartiesMapping);
		final List<StaffMembers> returnList = namedParameterJdbcTemplate.query(sql, createParams("userId", userId),
				offenderRowMapper);
		return returnList;

	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * agencyIncidentPartiesPreSeqPreInsertcDAO
	 *
	 *
	 */
	public Integer agencyIncidentRepaiPreInsertcDAO(final Integer agencyIncidentId) {
		Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[1];
		sqlParameters = new SqlParameter[] {new SqlParameter("P_AGENCY_INCIDENT_ID", OracleTypes.NUMBER)
				};
		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("TAG_ADJUDICATION").withFunctionName("GETNEXTREPAIRSEQ")
				.declareParameters(sqlParameters);
		inParamMap.put("P_AGENCY_INCIDENT_ID", agencyIncidentId);
		SqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		BigDecimal value = simpleJDBCCall.executeFunction(BigDecimal.class, inParameter);
		return Integer.valueOf(value.toString());
	}
	
	public List<VNameSearch> nameSrchExecuteoffenderQuery(final VNameSearch objSearchDao) {
		final String sql = getQuery("OIINAMES_NAMESRCH_SEARCH_V_NAME_SEARCH_INCIDENT"); 
		List<VNameSearch> returnList=new  ArrayList<VNameSearch>();
		final RowMapper<VNameSearch> VNameSearchRowMapper = Row2BeanRowMapper.makeMapping(sql, VNameSearch.class,
				vNameSearchMapping);
	        returnList = namedParameterJdbcTemplate.query(sql,
				createParams("OFFENDER_BOOK_ID",objSearchDao.getOffenderBookId()), VNameSearchRowMapper);
		return returnList;
	}

	@Override
	public Integer agencyIncidentChargesInsertAgencyIncidentChargesSeq(Integer agencyIncidentId) {
		Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[1];
		sqlParameters = new SqlParameter[] {new SqlParameter("P_AGENCY_INCIDENT_ID", OracleTypes.NUMBER)
				};
		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("TAG_ADJUDICATION").withFunctionName("GETNEXTCHARGESEQ")
				.declareParameters(sqlParameters);
		inParamMap.put("P_AGENCY_INCIDENT_ID", agencyIncidentId);
		SqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		BigDecimal value = simpleJDBCCall.executeFunction(BigDecimal.class, inParameter);
		return Integer.valueOf(value.toString());
	}

	@Override
	public OicOffences agyInciChgPostQuery(OicOffences paramBean) {
		final String sql = getQuery("OCUOICCH_AGY_INCIDENT_CHG_POSTQUERY");
		final RowMapper<OicOffences> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, OicOffences.class,
				oicOffencesMapping);
		OicOffences returnObj = (OicOffences) namedParameterJdbcTemplate.queryForObject(sql,
				createParams("OIC_OFFENCE_ID", paramBean.getOicOffenceId()), columnRowMapper);
		return returnObj;
	}

	@Override
	public List<SignificantIncident> sigificantIncidentExecuteQuery(SignificantIncident commitBean) {
		final String sql = queryBuilderFactory.getQueryBuilder(getQuery("OIDINCDE_DATA_SIGNIFICANT_INCIDENT"),SignificantIncidentMappping)
				.build();
		String preparedSql = null;
		final StringBuffer sqlQuery = new StringBuffer();
		final MapSqlParameterSource params = new MapSqlParameterSource();
		sqlQuery.append(sql);
		if(commitBean!=null) {
			sqlQuery.append(" where ");
			if (commitBean != null && commitBean.getAgencyIncidentId() != null) {
				sqlQuery.append("ASI.AGENCY_INCIDENT_ID =:agencyIncidentId"  );
				params.addValue("agencyIncidentId", commitBean.getAgencyIncidentId());
			}		
		}
		preparedSql = sqlQuery.toString().trim();
		final RowMapper<SignificantIncident> offenderRowMapper = Row2BeanRowMapper.makeMapping(preparedSql,
				SignificantIncident.class, SignificantIncidentMappping);
		List<SignificantIncident> returnList = new ArrayList<>();
		try {
			returnList = (namedParameterJdbcTemplate.query(preparedSql, params,offenderRowMapper));
		} catch (EmptyResultDataAccessException e) {
			logger.error(e);
		}

		return returnList;
	}

	@Override
	public int offenderWeaponcommitInsert(List<SignificantIncident> insertList) {
final String sql = getQuery("OIDINCDE_INSERT_VALUES_SIGNIFICANT_INCIDENT");

		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final SignificantIncident list : insertList) {
			parameters.add(new BeanPropertySqlParameterSource(list));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		if (insertList.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public int offenderWeaponcommitupdate(List<SignificantIncident> updateList) {
     final String sql = getQuery("OIDINCDE_UPDATE_SIGNIFICANT_INCIDENT");

		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final SignificantIncident list : updateList) {
			parameters.add(new BeanPropertySqlParameterSource(list));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		if (updateList.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public int offenderWeaponcommitdelete(List<SignificantIncident> deleteList) {
final String sql = getQuery("OIDINCDE_DELETE_SIGNIFICANT_INCIDENT");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final SignificantIncident list : deleteList) {
			parameters.add(new BeanPropertySqlParameterSource(list));
		}
		try {
			String tableName = "AGENCY_SIGNIFICANT_INCIDENTS";
			String whereClause = "AGENCY_INCIDENT_ID=:agencyIncidentId AND SIGNIFICANCE_TYPE=:significanceType";
			batchUpdatePreDeletedRows(tableName, whereClause , parameters);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method offenderWeaponcommitdelete", e);
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		if (deleteList.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}
	
	@Override
	public Integer agencyIncidentPartiesPreSeqPreInsertcDAO(final Integer agencyIncidentId) {
		final String sql = getQuery("GET_NEXT_PARTY_SEQ");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("P_AGENCY_INCIDENT_ID", agencyIncidentId),
				Integer.class);
	}
	
	@Override
	public String getEnhancedStaffReporter(Integer staffId) {
		final String sql = getQuery("OIDINCDE_GET_ENHANCED_STAF_REPORTER");
		String result = "";
		try {
			result = namedParameterJdbcTemplate.queryForObject(sql, createParams("staffId", staffId), String.class);
		} catch (Exception e) {
			logger.error(this.getClass().getName()+" getEnhancedStaffReporter", e);
			return result;
		}
		return result;
	}
	
	@Override
	public int updIncStaffReportType(Integer agencyIncidentId,Integer staffId, String staffReportType , Integer partySeq ,Date modifyDateTime ,String modifyUserId) {
		final String sql = getQuery("OIDINCDE_UPDATE_INCIDENT_STAFF_REPORT_REPORT_TYPE"); 
		int retVal = 0;
		try {
			retVal = namedParameterJdbcTemplate.update(sql, createParams("agencyIncidentId",agencyIncidentId , "partySeq", partySeq , "staffReportType", staffReportType , "modifyDateTime" , modifyDateTime ,"modifyUserId" , modifyUserId));
		} catch (Exception e) {
			logger.error(this.getClass().getName()+" updIncStaffReportType", e);
			return retVal;
		}
		return retVal;
	}

	@Override
	public Integer incidentFollowUpInsertData(List<IncidentFollowUpDetails> insertList) {
		final String sql = getQuery("OIDINCDE_INSERT_OFFENDER_FOLLOW_UP_DATA");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final IncidentFollowUpDetails sentenceTerms : insertList) {
			parameters.add(new BeanPropertySqlParameterSource(sentenceTerms));
		}
		try {
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		}catch (Exception e) {
			logger.error("incidentFollowUpInsertData : ", e);
			
		}
		if (insertList.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public Integer incidentFollowUpUpdateData(List<IncidentFollowUpDetails> updateList) {
		final String sql = getQuery("OIDINCDE_UPDATE_OFFENDER_FOLLOW_UP_DATA");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final IncidentFollowUpDetails sentenceTerms : updateList) {
			parameters.add(new BeanPropertySqlParameterSource(sentenceTerms));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error("incidentFollowUpUpdateData : ", e);
		}
		if (updateList.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public Integer incidentFollowUpDeleteData(List<IncidentFollowUpDetails> deleteList) {
		final String sql = getQuery("OIDINCDE_DELETE_OFFENDER_FOLLOW_UP_DATA");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final IncidentFollowUpDetails sentenceTerms : deleteList) {
			parameters.add(new BeanPropertySqlParameterSource(sentenceTerms));
		}
		try {
			String tableName = "INCIDENT_FOLLOW_UP_DETAILS";
			String whereClause = "INCIDENT_FOLLOW_UP_ID=:incidentFollowUpId";
			batchUpdatePreDeletedRows(tableName, whereClause , parameters);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method incidentFollowUpDeleteData", e);
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error("incidentFollowUpDeleteData : ", e);
		}
		if (deleteList.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public List<IncidentFollowUpDetails> getIncidentFollowUpDetails(IncidentFollowUpDetails searchBean) {
		final String sql = getQuery("OIDINCDE_GET_OFFENDER_FOLLOW_UP_GRID_DATA");
		ArrayList<IncidentFollowUpDetails> returnList =new ArrayList<IncidentFollowUpDetails>();
		try {		
			final RowMapper<IncidentFollowUpDetails> observationTypesRowMpr = Row2BeanRowMapper.makeMapping(sql,
					IncidentFollowUpDetails.class, followUpDataMapping);
			 returnList = (ArrayList<IncidentFollowUpDetails>) namedParameterJdbcTemplate
					.query(sql, createParams("agencyIncidentId",searchBean.getAgencyIncidentId()), observationTypesRowMpr);
		} catch(Exception e) {
			logger.error("getZoneDetailsExecuteQuery", e);
		}
		return returnList;
	}

	@Override
	public Map<String, Boolean> checkPermissionForTabAccess(String userName) {
	    final String sql = getQuery("OIDINCDE_GET_USER_ROLES");
	    Map<String, Boolean> permissionMap = new HashMap<>();
	    try {
	        List<String> rolesList = namedParameterJdbcTemplate.queryForList(sql, createParams("userID", userName), String.class);

	        permissionMap.put("INCIDENT_FOLLOW_UP", false);
	        permissionMap.put("SIGNFCT_INCIDENT_ROLE", false);

	        for (String per : rolesList) {
	            String role = per.trim();

	            if (role.equals("INCIDENT FOLLOW-UP")) {
	                permissionMap.put("INCIDENT_FOLLOW_UP", true);
	            } else if (role.equals("SIGNFCT_INCIDENT_ROLE")) {
	                permissionMap.put("SIGNFCT_INCIDENT_ROLE", true);
	            }
	        }
	    } catch (Exception e) {
	        logger.error(this.getClass().getName() + " error in  checkPermissionForTabAccess " + e);
	    }

	    return permissionMap;
	}
	
	@Override
	public Integer deleteIncidentStaffReport(
			final List<AgencyIncidentParties> lstAgencyIncidentParties) {
		final String sql = getQuery("OIDINCDE_INCIDENTSTAFF_DELETE_INCIDENT_STAFF_REPORTS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final AgencyIncidentParties list : lstAgencyIncidentParties) {
			parameters.add(new BeanPropertySqlParameterSource(list));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error(e);
			if(e.getMessage().contains("incident_staff_equipments_fk1") || e.getMessage().contains("incident_staff_forces_fk1")) {
				return 2;
			}
		}
		if (lstAgencyIncidentParties.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}
	
	@Override
	public Integer deleteIncidentStaffForcesData(
			final List<AgencyIncidentParties> lstAgencyIncidentParties) {
		final String sql = getQuery("OIDINCDE_DELETE_INCIDENT_STAFF_FORCES");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final AgencyIncidentParties list : lstAgencyIncidentParties) {
			parameters.add(new BeanPropertySqlParameterSource(list));
		}
		try {
			String tableName = "incident_staff_forces";
			String whereClause = "incident_report_id = :incidentReportId";
			batchUpdatePreDeletedRows(tableName, whereClause , parameters);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method deleteIncidentStaffForcesData", e);
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) { 
			logger.error(e);
			if(e.getMessage().contains("incident_staff_equipments_fk") || e.getMessage().contains("incident_staff_forces_fk") ) {
				return 2;
			}
			
		}
		if (lstAgencyIncidentParties.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}
	
	@Override
	public Integer deleteIncidentStaffEquipmentData(
			final List<AgencyIncidentParties> lstAgencyIncidentParties) {
		final String sql = getQuery("OIDINCDE_DELETE_INCIDENT_STAFF_EQUIPMENT");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final AgencyIncidentParties list : lstAgencyIncidentParties) {
			parameters.add(new BeanPropertySqlParameterSource(list));
		}
		try {
			String tableName = "incident_staff_equipments";
			String whereClause = "incident_report_id = :incidentReportId";
			batchUpdatePreDeletedRows(tableName, whereClause , parameters);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method deleteIncidentStaffEquipmentData", e);
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) { 
			logger.error(e);
			
			
		}
		if (lstAgencyIncidentParties.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}
	
	@Override
	public String getProfileCode() {
		final String sql = getQuery("OIDINCDE_GET_SYSTEM_PROFILE_CODE");
		String result = null;
		try {
			result = namedParameterJdbcTemplate.queryForObject(sql, SignificantIncidentMappping, String.class);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " getProfileCode");
			return null;
		}
		return result;
	}

	@Override
	public List<CaseloadAgencyLocations> getCommunityOfficesData(String caseloadId) {
		final String sql = queryBuilderFactory
				.getQueryBuilder(getQuery("OIDINCDE_FIND_COMMUNITY_OFFICES_DATA"), referenceCodeMapping).build();
		final RowMapper<CaseloadAgencyLocations> referenceCodeRowMapper = Row2BeanRowMapper.makeMapping(sql,
				CaseloadAgencyLocations.class, referenceCodeMapping);
		List<CaseloadAgencyLocations> returnList = new ArrayList<CaseloadAgencyLocations>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams("caseloadId", caseloadId),
					referenceCodeRowMapper);

		} catch (EmptyResultDataAccessException e) {
			logger.error(e);
			return Collections.emptyList();
		}
		return returnList;

	}
	
	@Override
	public List<ReferenceCodes> rgIncidentTypesCommRecordGroup(final String domain) {
		final String sql = queryBuilderFactory
				.getQueryBuilder(getQuery("OIDINCDE_FIND_REFERENCE_CODES_COMM_CASELOAD"), referenceCodeMapping).build();
		final RowMapper<ReferenceCodes> referenceCodeRowMapper = Row2BeanRowMapper.makeMapping(sql,
				ReferenceCodes.class, referenceCodeMapping);
		List<ReferenceCodes> returnList = new ArrayList<ReferenceCodes>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams("domain", domain), referenceCodeRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error(e);
			return Collections.emptyList();
		}
		return returnList;
	}
	
	@Override
	public List<StaffMembers> rgRoleStaffIdsRecordGroup(final String caseloadId,String agyLocId) {
		final String sql = queryBuilderFactory
				.getQueryBuilder(getQuery("OIDINCDE_FIND_RGROLESTAFFIDS"), staffMembersMapping).build();
		final RowMapper<StaffMembers> referenceCodeRowMapper = Row2BeanRowMapper.makeMapping(sql, StaffMembers.class,
				staffMembersMapping);
		List<StaffMembers> returnList = new ArrayList<StaffMembers>();
		try {
			returnList = (namedParameterJdbcTemplate.query(sql, createParams("caseloadId", caseloadId,"agyLocId",agyLocId),
					referenceCodeRowMapper));
		} catch (EmptyResultDataAccessException e) {
			logger.error(e);
		}
		return returnList;

	}

	@Override
	public boolean getStaffReportsMaintCount(String reportType) {
		int returnVal=0;
		final String sql = getQuery("OIDINCDE_GET_REPORT_TYPE_COUNT_IN_STAFF_REPORT_MAINT");
		try {
			returnVal= namedParameterJdbcTemplate.queryForObject(sql, createParams("report_type",reportType), Integer.class);
		} catch (Exception e) {
			logger.error("error in"+ this.getClass().getName() +" getStaffReportsMaintCount", e);
		}
		return returnVal>0?true:false;
	
	}

		@Override
	public String getProfileCodeForStaffSearch() {
		final String sql = getQuery("OIDINCDE_GET_PROFILE_CODE_FOR_STAFF_INVOLVEMENT");
		String result = null;
		try {
			result = namedParameterJdbcTemplate.queryForObject(sql, SignificantIncidentMappping, String.class);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " getProfileCode");
			return null;
		}
		return result;

	}
	
	@Override
	public List<StaffMembers> rgRoleStaffIdsForAllAgyLocId(String agyLocId) {
		final String sql = queryBuilderFactory
				.getQueryBuilder(getQuery("OIDINCDE_FIND_ALL_AGYLOC_RGROLESTAFFIDS"), staffMembersMapping).build();
		final RowMapper<StaffMembers> referenceCodeRowMapper = Row2BeanRowMapper.makeMapping(sql, StaffMembers.class,
				staffMembersMapping);
		List<StaffMembers> returnList = new ArrayList<StaffMembers>();
		try {
			returnList = (namedParameterJdbcTemplate.query(sql, createParams("agyLocId",agyLocId),
					referenceCodeRowMapper));
		} catch (EmptyResultDataAccessException e) {
			logger.error(e);
		}
		return returnList;

	}

	

}

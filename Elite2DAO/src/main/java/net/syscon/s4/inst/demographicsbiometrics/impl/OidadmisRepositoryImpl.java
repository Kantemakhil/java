package net.syscon.s4.inst.demographicsbiometrics.impl;

import java.math.BigDecimal;
import java.sql.Types;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.RowMapperResultSetExtractor;
import org.springframework.jdbc.core.SqlInOutParameter;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.support.TransactionOperations;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.ApplicationConstants;
import net.syscon.s4.common.beans.CaseloadAgencyLocations;
import net.syscon.s4.common.beans.Caseloads;
import net.syscon.s4.common.beans.OffenderBookings;
import net.syscon.s4.common.beans.OffenderEscape;
import net.syscon.s4.common.beans.OffenderExternalMovements;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.beans.TransactionTypes;
import net.syscon.s4.common.beans.WorkflowScreens;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.AgencyBillingProfiles;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.BedAssignmentHistories;
import net.syscon.s4.im.beans.CaseloadAdmOtherProfiles;
import net.syscon.s4.im.beans.LivingUnits;
import net.syscon.s4.im.beans.MovementReasons;
import net.syscon.s4.im.beans.OffenderTransactions;
import net.syscon.s4.im.beans.SystemMessages;
import net.syscon.s4.im.beans.TransactionOperation;
import net.syscon.s4.im.beans.VHeaderBlock2;
import net.syscon.s4.inst.demographicsbiometrics.OidadmisRepository;
import net.syscon.s4.inst.legals.beans.OffenderSentenceAdjustment;
import net.syscon.s4.inst.visitsmanagement.beans.IepLevelBean;
import oracle.jdbc.OracleTypes;

/**
 * Class OidadmisRepositoryImpl
 */
@Repository
public class OidadmisRepositoryImpl extends RepositoryBase implements OidadmisRepository {
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OidadmisRepositoryImpl.class.getName());

	private final Map<String, FieldMapper> transactionTypesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DESCRIPTION INTO P_TXN_DESC", new FieldMapper("description into pTxnDesc")).build();
	private final Map<String, FieldMapper> workflowScreensMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("MODULE_NAME", new FieldMapper("moduleName")).put("WORKFLOW_CODE", new FieldMapper("workflowCode"))
			.put("WORKFLOW_SEQ", new FieldMapper("workflowSeq")).build();
	private final Map<String, FieldMapper> vHeaderBlock2Mapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("LIV_UNIT_DESC", new FieldMapper("livUnitDesc"))
			.put("STAFF_FIRST_NAME", new FieldMapper("staffFirstName"))
			.put("COMM_STAFF_ID", new FieldMapper("commStaffId"))
			.put("TXN_ADJUSTED_FLAG", new FieldMapper("txnAdjustedFlag"))
			.put("MIDDLE_NAME", new FieldMapper("middleName"))
			.put("BOOKING_CREATED_DATE", new FieldMapper("bookingCreatedDate"))
			.put("APPLICATION_TIME", new FieldMapper("applicationTime"))
			.put("GROSS_NET_FLAG", new FieldMapper("grossNetFlag"))
			.put("INTAKE_AGY_LOC_ID", new FieldMapper("intakeAgyLocId"))
			.put("TO_PROV_STAT_CODE", new FieldMapper("toProvStatCode"))
			.put("ESCORT_TEXT", new FieldMapper("escortText")).put("TXN_TYPE", new FieldMapper("txnType"))
			.put("GROSS_AMOUNT", new FieldMapper("grossAmount")).put("PROFILE_VALUE", new FieldMapper("profileValue"))
			.put("PAYEE_PERSON_ID", new FieldMapper("payeePersonId"))
			.put("OFFENDER_BOOK_ID", new FieldMapper("offenderBookId"))
			.put("CREATE_USER_ID", new FieldMapper("createUserId")).put("PROFILE_TYPE", new FieldMapper("profileType"))
			.put("TXN_ENTRY_DATE", new FieldMapper("txnEntryDate")).put("AGENCY_IML_ID", new FieldMapper("agencyImlId"))
			.put("FROM_ADDRESS_ID", new FieldMapper("fromAddressId"))
			.put("APPLICATION_DATE", new FieldMapper("applicationDate"))
			.put("RECEIPT_NUMBER", new FieldMapper("receiptNumber"))
			.put("ADJUST_TXN_ID", new FieldMapper("adjustTxnId"))
			.put("INTERNAL_SCHEDULE_TYPE", new FieldMapper("internalScheduleType"))
			.put("EVENT_ID", new FieldMapper("eventId"))
			.put("CLOSING_CHEQUE_NUMBER", new FieldMapper("closingChequeNumber"))
			.put("STATUS_DISPLAY", new FieldMapper("statusDisplay")).put("BOOKING_TYPE", new FieldMapper("bookingType"))
			.put("ACTIVITY_DATE", new FieldMapper("activityDate")).put("CASELOAD_ID", new FieldMapper("caseloadId"))
			.put("NO_COMM_AGY_LOC_ID", new FieldMapper("noCommAgyLocId"))
			.put("RECORD_USER_ID", new FieldMapper("recordUserId"))
			.put("HEADER_STATUS", new FieldMapper("headerStatus")).put("REMITTER_NAME", new FieldMapper("remitterName"))
			.put("REQUEST_NAME", new FieldMapper("requestName"))
			.put("ARREST_AGENCY_LOC_ID", new FieldMapper("arrestAgencyLocId"))
			.put("BOOKING_STATUS", new FieldMapper("bookingStatus")).put("COMM_STATUS", new FieldMapper("commStatus"))
			.put("PROFILE_CODE", new FieldMapper("profileCode")).put("SUFFIX", new FieldMapper("suffix"))
			.put("APPLY_SPENDING_LIMIT_AMOUNT", new FieldMapper("applySpendingLimitAmount"))
			.put("SEARCH_STAFF_ID", new FieldMapper("searchStaffId"))
			.put("TOTAL_UNEXCUSED_ABSENCES", new FieldMapper("totalUnexcusedAbsences"))
			.put("TO_AGY_LOC_ID", new FieldMapper("toAgyLocId"))
			.put("HOLD_CLEAR_FLAG", new FieldMapper("holdClearFlag")).put("LAST_NAME", new FieldMapper("lastName"))
			.put("ASSIGNMENT_DATE", new FieldMapper("assignmentDate"))
			.put("MODIFY_DATETIME", new FieldMapper("modifyDatetime"))
			.put("DESCRIPTION", new FieldMapper("description")).put("REMITTER_ID", new FieldMapper("remitterId"))
			.put("CASE_OFFICER_ID", new FieldMapper("caseOfficerId")).put("PAYEE_CODE", new FieldMapper("payeeCode"))
			.put("BOOKING_BEGIN_DATE", new FieldMapper("bookingBeginDate"))
			.put("TXN_ENTRY_AMOUNT", new FieldMapper("txnEntryAmount"))
			.put("MOVEMENT_TIME", new FieldMapper("movementTime")).put("STATUS_REASON", new FieldMapper("statusReason"))
			.put("SLIP_PRINTED_FLAG", new FieldMapper("slipPrintedFlag"))
			.put("EARNED_CREDIT_LEVEL", new FieldMapper("earnedCreditLevel"))
			.put("COMMUNITY_ACTIVE_FLAG", new FieldMapper("communityActiveFlag"))
			.put("DEDUCTION_TYPE", new FieldMapper("deductionType"))
			.put("YOUTH_ADULT_CODE", new FieldMapper("youthAdultCode"))
			.put("ASSIGNED_STAFF_ID", new FieldMapper("assignedStaffId"))
			.put("INTAKE_AGY_LOC_ASSIGN_DATE", new FieldMapper("intakeAgyLocAssignDate"))
			.put("CREATE_INTAKE_AGY_LOC_ID", new FieldMapper("createIntakeAgyLocId"))
			.put("AGY_LOC_ID_LIST", new FieldMapper("agyLocIdList"))
			.put("TXN_POSTING_TYPE", new FieldMapper("txnPostingType"))
			.put("OFFENDER_ID_DISPLAY", new FieldMapper("offenderIdDisplay"))
			.put("TXN_REFERENCE_NUMBER", new FieldMapper("txnReferenceNumber"))
			.put("REPORTING_TIME", new FieldMapper("reportingTime"))
			.put("ROOT_OFFENDER_ID", new FieldMapper("rootOffenderId"))
			.put("PHOTO_TAKING_STAFF_ID", new FieldMapper("photoTakingStaffId"))
			.put("ADJUST_TXN_ENTRY_ID", new FieldMapper("adjustTxnEntryId"))
			.put("PAYEE_NAME_TEXT", new FieldMapper("payeeNameText"))
			.put("IN_OUT_STATUS", new FieldMapper("inOutStatus"))
			.put("RECEIPT_PRINTED_FLAG", new FieldMapper("receiptPrintedFlag"))
			.put("SERVICE_FEE_FLAG", new FieldMapper("serviceFeeFlag")).put("TO_CITY", new FieldMapper("toCity"))
			.put("SEAL_FLAG", new FieldMapper("sealFlag")).put("CREATE_DATETIME", new FieldMapper("createDatetime"))
			.put("COMMUNITY_AGY_LOC_ID", new FieldMapper("communityAgyLocId"))
			.put("RECEIPT_PENDING_PRINT_FLAG", new FieldMapper("receiptPendingPrintFlag"))
			.put("MOVEMENT_TYPE", new FieldMapper("movementType"))
			.put("FINGER_PRINTED_STAFF_ID", new FieldMapper("fingerPrintedStaffId"))
			.put("ASSIGNMENT_END_TIME", new FieldMapper("assignmentEndTime"))
			.put("BED_ASSIGN_SEQ", new FieldMapper("bedAssignSeq"))
			.put("TRANSFER_CASELOAD_ID", new FieldMapper("transferCaseloadId"))
			.put("EKSTRAND_CREDIT_LEVEL", new FieldMapper("ekstrandCreditLevel"))
			.put("DIRECTION_CODE", new FieldMapper("directionCode"))
			.put("COMM_STAFF_ROLE", new FieldMapper("commStaffRole")).put("CASE_TIME", new FieldMapper("caseTime"))
			.put("OJ_LOCATION_CODE", new FieldMapper("ojLocationCode"))
			.put("FROM_AGY_LOC_ID", new FieldMapper("fromAgyLocId"))
			.put("ADJUST_OFFENDER_ID", new FieldMapper("adjustOffenderId"))
			.put("PROFILE_VALUE_2", new FieldMapper("profileValue2"))
			.put("MOVEMENT_SEQ", new FieldMapper("movementSeq")).put("AGY_LOC_ID", new FieldMapper("agyLocId"))
			.put("DEDUCTION_FLAG", new FieldMapper("deductionFlag"))
			.put("MOVEMENT_REASON_CODE", new FieldMapper("movementReasonCode"))
			.put("AGY_LOC_TYPE", new FieldMapper("agyLocType")).put("MOVEMENT_DATE", new FieldMapper("movementDate"))
			.put("ASSIGNMENT_END_DATE", new FieldMapper("assignmentEndDate"))
			.put("TXN_ENTRY_DESC", new FieldMapper("txnEntryDesc")).put("INFO_NUMBER", new FieldMapper("infoNumber"))
			.put("CASE_DATE", new FieldMapper("caseDate")).put("DISCLOSURE_FLAG", new FieldMapper("disclosureFlag"))
			.put("TO_ADDRESS_ID", new FieldMapper("toAddressId"))
			.put("CREATE_AGY_LOC_ID", new FieldMapper("createAgyLocId"))
			.put("LIVING_UNIT_ID", new FieldMapper("livingUnitId"))
			.put("COMMUNITY_STATUS", new FieldMapper("communityStatus"))
			.put("LIVING_UNIT_DESCRIPTION", new FieldMapper("livingUnitDescription"))
			.put("STAFF_LAST_NAME", new FieldMapper("staffLastName")).put("ESCORT_CODE", new FieldMapper("escortCode"))
			.put("OFFENDER_ID", new FieldMapper("offenderId")).put("LOCATION_CODE", new FieldMapper("locationCode"))
			.put("HOLD_NUMBER", new FieldMapper("holdNumber")).put("PARENT_EVENT_ID", new FieldMapper("parentEventId"))
			.put("ORG_TXN_TYPE", new FieldMapper("orgTxnType")).put("TO_COUNTRY_CODE", new FieldMapper("toCountryCode"))
			.put("TXN_ID", new FieldMapper("txnId")).put("COMMENT_TEXT", new FieldMapper("commentText"))
			.put("REPORTING_DATE", new FieldMapper("reportingDate"))
			.put("INTAKE_USER_ID", new FieldMapper("intakeUserId")).put("ACTIVE_FLAG", new FieldMapper("activeFlag"))
			.put("INTAKE_CASELOAD_ID", new FieldMapper("intakeCaseloadId"))
			.put("MODIFY_USER_ID", new FieldMapper("modifyUserId"))
			.put("OLD_TABLE_NAME", new FieldMapper("oldTableName"))
			.put("INTERNAL_SCHEDULE_REASON_CODE", new FieldMapper("internalScheduleReasonCode"))
			.put("HOLD_UNTIL_DATE", new FieldMapper("holdUntilDate")).put("FROM_CITY", new FieldMapper("fromCity"))
			.put("ADJUST_ACCOUNT_CODE", new FieldMapper("adjustAccountCode"))
			.put("ALIAS_OFFENDER_ID", new FieldMapper("aliasOffenderId"))
			.put("MODIFY_DATE", new FieldMapper("modifyDate"))
			.put("BOOKING_END_DATE", new FieldMapper("bookingEndDate"))
			.put("ASSIGNMENT_TIME", new FieldMapper("assignmentTime"))
			.put("ASSIGNMENT_REASON", new FieldMapper("assignmentReason"))
			.put("BOOKING_NO", new FieldMapper("bookingNo")).put("TXN_ENTRY_SEQ", new FieldMapper("txnEntrySeq"))
			.put("PAYEE_CORPORATE_ID", new FieldMapper("payeeCorporateId"))
			.put("SUB_ACCOUNT_TYPE", new FieldMapper("subAccountType")).put("BIRTH_DATE", new FieldMapper("birthDate"))
			.put("FIRST_NAME", new FieldMapper("firstName"))
			.put("PRE_WITHHOLD_AMOUNT", new FieldMapper("preWithholdAmount")).build();
	private final Map<String, FieldMapper> agencyBillingProfilesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("AGENCY_ID", new FieldMapper("agencyId")).put("BILLING_TYPE", new FieldMapper("billingType"))
			.put("RATE", new FieldMapper("rate")).put("FREQUENCY", new FieldMapper("frequency")).build();
	private final Map<String, FieldMapper> movementReasonsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("ADMISSION_TYPE", new FieldMapper("admissionType")).put("CAPACITY", new FieldMapper("capacity"))
			.put("MOVE_RSN1.MOVEMENT_REASON_CODE", new FieldMapper("moveRsn1.movementReasonCode"))
			.put("MOVE_RSN1.DESCRIPTION", new FieldMapper("moveRsn1.description"))
			.put("MOVEMENT_REASON_CODE", new FieldMapper("movementReasonCode"))
			.put("OEM.MOVEMENT_REASON_CODE", new FieldMapper("oem.movementReasonCode"))
			.put("LIV_UNITS.DESCRIPTION", new FieldMapper("livUnits.description"))
			.put("MR1.IN_MOVEMENT_REASON_CODE", new FieldMapper("mr1.inMovementReasonCode"))
			.put("RECORD_STATUS", new FieldMapper("recordStatus"))
			.put("DSP_DESCRIPTION4", new FieldMapper("dspDescription4"))
			.put("MR2.DESCRIPTION", new FieldMapper("mr2.description")).build();
	private final Map<String, FieldMapper> caseloadAdmAlertProfilesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("ROOT_OFFENDER_ID", new FieldMapper("rootOffenderId"))
			.put("CASELOAD_ID", new FieldMapper("caseloadId"))
			.put("CA.MESSAGE_NUMBER", new FieldMapper("ca.messageNumber"))
			.put("OFFENDER_ID", new FieldMapper("offenderId")).build();
	private final Map<String, FieldMapper> agencyLocationsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DSP_DESCRIPTION6", new FieldMapper("description")).put("FROM_AGY_LOC_ID", new FieldMapper("agyLocId"))
			.put("AGY_LOC1.AGY_LOC_ID", new FieldMapper("agyLoc1.agyLocId"))
			.put("AGY_LOC.AGY_LOC_ID TO_AGY_LOC_ID", new FieldMapper("agyLoc.agyLocId toAgyLocId"))
			.put("AGY_LOC.DESCRIPTION DSP_DESCRIPTION5", new FieldMapper("agyLoc.description dspDescription5"))
			.put("AGY_LOC.AGY_LOC_ID", new FieldMapper("agyLoc.agyLocId"))
			.put("AGY_LOC1.DESCRIPTION", new FieldMapper("agyLoc1.description"))
			.put("CASELOAD_ID", new FieldMapper("caseloadId")).put("TO_AGY_LOC_ID", new FieldMapper("toAgyLocId"))
			.put("DSP_DESCRIPTION5", new FieldMapper("dspDescription5"))
			.put("AGY_LOC.DESCRIPTION", new FieldMapper("agyLoc.description"))
			.put("ACTIVE_FLAG", new FieldMapper("activeFlag")).build();
	private final Map<String, FieldMapper> caseloadAgencyLocationsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("ROOT_OFFENDER_ID", new FieldMapper("rootOffenderId"))
			.put("CASELOAD_ID", new FieldMapper("caseloadId"))
			.put("CA.MESSAGE_NUMBER", new FieldMapper("ca.messageNumber")).build();
	private final Map<String, FieldMapper> caseloadAdmOtherProfilesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("AGY_LOC_ID", new FieldMapper("agyLocId")).put("ROOT_OFFENDER_ID", new FieldMapper("rootOffenderId"))
			.put("CASELOAD_ID", new FieldMapper("caseloadId")).put("TO_AGY_LOC_ID", new FieldMapper("toAgyLocId"))
			.put("LIVING_UNIT_ID", new FieldMapper("livingUnitId"))
			.put("CA.MESSAGE_NUMBER", new FieldMapper("ca.messageNumber")).build();
	private final Map<String, FieldMapper> staffMembersMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("STAFF.STAFF_ID", new FieldMapper("ASSIGNEDSTAFFID"))
			.put("STAFF.FIRST_NAME", new FieldMapper("staff.firstName"))
			.put("STAFF.LAST_NAME", new FieldMapper("staff.lastName"))
			.put("DESCRIPTION", new FieldMapper("description")).put("CODE", new FieldMapper("code"))
			.put("ASSIGNED_STAFF_ID", new FieldMapper("ASSIGNEDSTAFFID")).build();
	private final Map<String, FieldMapper> referenceCodesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("ARREST_AGENCY_LOC_ID", new FieldMapper("arrestAgencyLocId"))
			.put("REF_CODE1.CODE", new FieldMapper("refCode1.code"))
			.put("REFERENCE_CODES.DESCRIPTION", new FieldMapper("referenceCodes.description"))
			.put("BOOKING_STATUS", new FieldMapper("bookingStatus"))
			.put("DSP_DESCRIPTION", new FieldMapper("dspDescription"))
			.put("REF_CODE2.CODE", new FieldMapper("refCode2.code"))
			.put("ESCORT_CODE", new FieldMapper("escortCode"))
			.put("REF_CODE2.DESCRIPTION", new FieldMapper("refCode2.description"))
			.put("REF_CODE1.DESCRIPTION", new FieldMapper("refCode1.description"))
			.put("AGY_LOC1.AGY_LOC_ID", new FieldMapper("toAgyLocId"))
			.put("LIST_SEQ", new FieldMapper("listSeq"))
			.put("CODE", new FieldMapper("code"))
			.put("DSP_DESCRIPTION3", new FieldMapper("dspDescription3"))
			.put("ACTIVE_FLAG", new FieldMapper("activeFlag")).build();
	private final Map<String, FieldMapper> systemProfilesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("LIV_UNIT_DESC", new FieldMapper("livUnitDesc"))
			.put("STAFF_FIRST_NAME", new FieldMapper("staffFirstName"))
			.put("COMM_STAFF_ID", new FieldMapper("commStaffId"))
			.put("TXN_ADJUSTED_FLAG", new FieldMapper("txnAdjustedFlag"))
			.put("MIDDLE_NAME", new FieldMapper("middleName"))
			.put("BOOKING_CREATED_DATE", new FieldMapper("bookingCreatedDate"))
			.put("APPLICATION_TIME", new FieldMapper("applicationTime"))
			.put("GROSS_NET_FLAG", new FieldMapper("grossNetFlag"))
			.put("INTAKE_AGY_LOC_ID", new FieldMapper("intakeAgyLocId"))
			.put("TO_PROV_STAT_CODE", new FieldMapper("toProvStatCode"))
			.put("ESCORT_TEXT", new FieldMapper("escortText")).put("TXN_TYPE", new FieldMapper("txnType"))
			.put("GROSS_AMOUNT", new FieldMapper("grossAmount")).put("PROFILE_VALUE", new FieldMapper("profileValue"))
			.put("PAYEE_PERSON_ID", new FieldMapper("payeePersonId"))
			.put("OFFENDER_BOOK_ID", new FieldMapper("offenderBookId"))
			.put("CREATE_USER_ID", new FieldMapper("createUserId")).put("PROFILE_TYPE", new FieldMapper("profileType"))
			.put("TXN_ENTRY_DATE", new FieldMapper("txnEntryDate")).put("AGENCY_IML_ID", new FieldMapper("agencyImlId"))
			.put("FROM_ADDRESS_ID", new FieldMapper("fromAddressId"))
			.put("APPLICATION_DATE", new FieldMapper("applicationDate"))
			.put("RECEIPT_NUMBER", new FieldMapper("receiptNumber"))
			.put("ADJUST_TXN_ID", new FieldMapper("adjustTxnId"))
			.put("INTERNAL_SCHEDULE_TYPE", new FieldMapper("internalScheduleType"))
			.put("EVENT_ID", new FieldMapper("eventId"))
			.put("CLOSING_CHEQUE_NUMBER", new FieldMapper("closingChequeNumber"))
			.put("STATUS_DISPLAY", new FieldMapper("statusDisplay")).put("BOOKING_TYPE", new FieldMapper("bookingType"))
			.put("ACTIVITY_DATE", new FieldMapper("activityDate")).put("CASELOAD_ID", new FieldMapper("caseloadId"))
			.put("NO_COMM_AGY_LOC_ID", new FieldMapper("noCommAgyLocId"))
			.put("RECORD_USER_ID", new FieldMapper("recordUserId"))
			.put("HEADER_STATUS", new FieldMapper("headerStatus")).put("REMITTER_NAME", new FieldMapper("remitterName"))
			.put("REQUEST_NAME", new FieldMapper("requestName"))
			.put("ARREST_AGENCY_LOC_ID", new FieldMapper("arrestAgencyLocId"))
			.put("BOOKING_STATUS", new FieldMapper("bookingStatus")).put("COMM_STATUS", new FieldMapper("commStatus"))
			.put("PROFILE_CODE", new FieldMapper("profileCode")).put("SUFFIX", new FieldMapper("suffix"))
			.put("APPLY_SPENDING_LIMIT_AMOUNT", new FieldMapper("applySpendingLimitAmount"))
			.put("SEARCH_STAFF_ID", new FieldMapper("searchStaffId"))
			.put("TOTAL_UNEXCUSED_ABSENCES", new FieldMapper("totalUnexcusedAbsences"))
			.put("TO_AGY_LOC_ID", new FieldMapper("toAgyLocId"))
			.put("HOLD_CLEAR_FLAG", new FieldMapper("holdClearFlag")).put("LAST_NAME", new FieldMapper("lastName"))
			.put("ASSIGNMENT_DATE", new FieldMapper("assignmentDate"))
			.put("MODIFY_DATETIME", new FieldMapper("modifyDatetime"))
			.put("DESCRIPTION", new FieldMapper("description")).put("REMITTER_ID", new FieldMapper("remitterId"))
			.put("CASE_OFFICER_ID", new FieldMapper("caseOfficerId")).put("PAYEE_CODE", new FieldMapper("payeeCode"))
			.put("BOOKING_BEGIN_DATE", new FieldMapper("bookingBeginDate"))
			.put("TXN_ENTRY_AMOUNT", new FieldMapper("txnEntryAmount"))
			.put("MOVEMENT_TIME", new FieldMapper("movementTime")).put("STATUS_REASON", new FieldMapper("statusReason"))
			.put("SLIP_PRINTED_FLAG", new FieldMapper("slipPrintedFlag"))
			.put("EARNED_CREDIT_LEVEL", new FieldMapper("earnedCreditLevel"))
			.put("COMMUNITY_ACTIVE_FLAG", new FieldMapper("communityActiveFlag"))
			.put("DEDUCTION_TYPE", new FieldMapper("deductionType"))
			.put("YOUTH_ADULT_CODE", new FieldMapper("youthAdultCode"))
			.put("ASSIGNED_STAFF_ID", new FieldMapper("assignedStaffId"))
			.put("INTAKE_AGY_LOC_ASSIGN_DATE", new FieldMapper("intakeAgyLocAssignDate"))
			.put("CREATE_INTAKE_AGY_LOC_ID", new FieldMapper("createIntakeAgyLocId"))
			.put("AGY_LOC_ID_LIST", new FieldMapper("agyLocIdList"))
			.put("TXN_POSTING_TYPE", new FieldMapper("txnPostingType"))
			.put("OFFENDER_ID_DISPLAY", new FieldMapper("offenderIdDisplay"))
			.put("TXN_REFERENCE_NUMBER", new FieldMapper("txnReferenceNumber"))
			.put("REPORTING_TIME", new FieldMapper("reportingTime"))
			.put("ROOT_OFFENDER_ID", new FieldMapper("rootOffenderId"))
			.put("PHOTO_TAKING_STAFF_ID", new FieldMapper("photoTakingStaffId"))
			.put("ADJUST_TXN_ENTRY_ID", new FieldMapper("adjustTxnEntryId"))
			.put("PAYEE_NAME_TEXT", new FieldMapper("payeeNameText"))
			.put("IN_OUT_STATUS", new FieldMapper("inOutStatus"))
			.put("RECEIPT_PRINTED_FLAG", new FieldMapper("receiptPrintedFlag"))
			.put("SERVICE_FEE_FLAG", new FieldMapper("serviceFeeFlag")).put("TO_CITY", new FieldMapper("toCity"))
			.put("SEAL_FLAG", new FieldMapper("sealFlag")).put("CREATE_DATETIME", new FieldMapper("createDatetime"))
			.put("COMMUNITY_AGY_LOC_ID", new FieldMapper("communityAgyLocId"))
			.put("RECEIPT_PENDING_PRINT_FLAG", new FieldMapper("receiptPendingPrintFlag"))
			.put("MOVEMENT_TYPE", new FieldMapper("movementType"))
			.put("FINGER_PRINTED_STAFF_ID", new FieldMapper("fingerPrintedStaffId"))
			.put("ASSIGNMENT_END_TIME", new FieldMapper("assignmentEndTime"))
			.put("BED_ASSIGN_SEQ", new FieldMapper("bedAssignSeq"))
			.put("TRANSFER_CASELOAD_ID", new FieldMapper("transferCaseloadId"))
			.put("EKSTRAND_CREDIT_LEVEL", new FieldMapper("ekstrandCreditLevel"))
			.put("DIRECTION_CODE", new FieldMapper("directionCode"))
			.put("COMM_STAFF_ROLE", new FieldMapper("commStaffRole")).put("CASE_TIME", new FieldMapper("caseTime"))
			.put("OJ_LOCATION_CODE", new FieldMapper("ojLocationCode"))
			.put("FROM_AGY_LOC_ID", new FieldMapper("fromAgyLocId"))
			.put("ADJUST_OFFENDER_ID", new FieldMapper("adjustOffenderId"))
			.put("PROFILE_VALUE_2", new FieldMapper("profileValue2"))
			.put("MOVEMENT_SEQ", new FieldMapper("movementSeq")).put("AGY_LOC_ID", new FieldMapper("agyLocId"))
			.put("DEDUCTION_FLAG", new FieldMapper("deductionFlag"))
			.put("MOVEMENT_REASON_CODE", new FieldMapper("movementReasonCode"))
			.put("AGY_LOC_TYPE", new FieldMapper("agyLocType")).put("MOVEMENT_DATE", new FieldMapper("movementDate"))
			.put("ASSIGNMENT_END_DATE", new FieldMapper("assignmentEndDate"))
			.put("TXN_ENTRY_DESC", new FieldMapper("txnEntryDesc")).put("INFO_NUMBER", new FieldMapper("infoNumber"))
			.put("CASE_DATE", new FieldMapper("caseDate")).put("DISCLOSURE_FLAG", new FieldMapper("disclosureFlag"))
			.put("TO_ADDRESS_ID", new FieldMapper("toAddressId"))
			.put("CREATE_AGY_LOC_ID", new FieldMapper("createAgyLocId"))
			.put("LIVING_UNIT_ID", new FieldMapper("livingUnitId"))
			.put("COMMUNITY_STATUS", new FieldMapper("communityStatus"))
			.put("LIVING_UNIT_DESCRIPTION", new FieldMapper("livingUnitDescription"))
			.put("STAFF_LAST_NAME", new FieldMapper("staffLastName")).put("ESCORT_CODE", new FieldMapper("escortCode"))
			.put("OFFENDER_ID", new FieldMapper("offenderId")).put("LOCATION_CODE", new FieldMapper("locationCode"))
			.put("HOLD_NUMBER", new FieldMapper("holdNumber")).put("PARENT_EVENT_ID", new FieldMapper("parentEventId"))
			.put("ORG_TXN_TYPE", new FieldMapper("orgTxnType")).put("TO_COUNTRY_CODE", new FieldMapper("toCountryCode"))
			.put("TXN_ID", new FieldMapper("txnId")).put("COMMENT_TEXT", new FieldMapper("commentText"))
			.put("REPORTING_DATE", new FieldMapper("reportingDate"))
			.put("INTAKE_USER_ID", new FieldMapper("intakeUserId")).put("ACTIVE_FLAG", new FieldMapper("activeFlag"))
			.put("INTAKE_CASELOAD_ID", new FieldMapper("intakeCaseloadId"))
			.put("MODIFY_USER_ID", new FieldMapper("modifyUserId"))
			.put("OLD_TABLE_NAME", new FieldMapper("oldTableName"))
			.put("INTERNAL_SCHEDULE_REASON_CODE", new FieldMapper("internalScheduleReasonCode"))
			.put("HOLD_UNTIL_DATE", new FieldMapper("holdUntilDate")).put("FROM_CITY", new FieldMapper("fromCity"))
			.put("ADJUST_ACCOUNT_CODE", new FieldMapper("adjustAccountCode"))
			.put("ALIAS_OFFENDER_ID", new FieldMapper("aliasOffenderId"))
			.put("MODIFY_DATE", new FieldMapper("modifyDate"))
			.put("BOOKING_END_DATE", new FieldMapper("bookingEndDate"))
			.put("ASSIGNMENT_TIME", new FieldMapper("assignmentTime"))
			.put("ASSIGNMENT_REASON", new FieldMapper("assignmentReason"))
			.put("BOOKING_NO", new FieldMapper("bookingNo")).put("TXN_ENTRY_SEQ", new FieldMapper("txnEntrySeq"))
			.put("PAYEE_CORPORATE_ID", new FieldMapper("payeeCorporateId"))
			.put("SUB_ACCOUNT_TYPE", new FieldMapper("subAccountType")).put("BIRTH_DATE", new FieldMapper("birthDate"))
			.put("FIRST_NAME", new FieldMapper("firstName"))
			.put("PRE_WITHHOLD_AMOUNT", new FieldMapper("preWithholdAmount")).build();
	private final Map<String, FieldMapper> livingUnitsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CAPACITY", new FieldMapper("capacity"))
			.put("OEM.MOVEMENT_REASON_CODE", new FieldMapper("oem.movementReasonCode"))
			.put("DSP_DESCRIPTION", new FieldMapper("dspDescription"))
			.put("LIV_UNIT.LIVING_UNIT_ID", new FieldMapper("livUnit.livingUnitId"))
			.put("LIV_UNITS.DESCRIPTION", new FieldMapper("livUnits.description"))
			.put("MR1.IN_MOVEMENT_REASON_CODE", new FieldMapper("mr1.inMovementReasonCode"))
			.put("CASELOAD_ID", new FieldMapper("caseloadId")).put("TO_AGY_LOC_ID", new FieldMapper("toAgyLocId"))
			.put("LIVING_UNIT_ID", new FieldMapper("livingUnitId"))
			.put("MR2.DESCRIPTION", new FieldMapper("mr2.description"))
			.put("DESCRIPTION", new FieldMapper("description"))
			.put("HOUSING_UNIT_TYPE", new FieldMapper("housingUnitType"))
			.put("LIV_UNIT.DESCRIPTION", new FieldMapper("livUnit.description")).build();
	private final Map<String, FieldMapper> offenderTransactionsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("LIV_UNIT_DESC", new FieldMapper("livUnitDesc"))
			.put("STAFF_FIRST_NAME", new FieldMapper("staffFirstName"))
			.put("COMM_STAFF_ID", new FieldMapper("commStaffId"))
			.put("TXN_ADJUSTED_FLAG", new FieldMapper("txnAdjustedFlag"))
			.put("MIDDLE_NAME", new FieldMapper("middleName"))
			.put("BOOKING_CREATED_DATE", new FieldMapper("bookingCreatedDate"))
			.put("APPLICATION_TIME", new FieldMapper("applicationTime"))
			.put("GROSS_NET_FLAG", new FieldMapper("grossNetFlag"))
			.put("INTAKE_AGY_LOC_ID", new FieldMapper("intakeAgyLocId"))
			.put("TO_PROV_STAT_CODE", new FieldMapper("toProvStatCode"))
			.put("ESCORT_TEXT", new FieldMapper("escortText")).put("TXN_TYPE", new FieldMapper("txnType"))
			.put("GROSS_AMOUNT", new FieldMapper("grossAmount")).put("PROFILE_VALUE", new FieldMapper("profileValue"))
			.put("PAYEE_PERSON_ID", new FieldMapper("payeePersonId"))
			.put("OFFENDER_BOOK_ID", new FieldMapper("offenderBookId"))
			.put("CREATE_USER_ID", new FieldMapper("createUserId")).put("PROFILE_TYPE", new FieldMapper("profileType"))
			.put("TXN_ENTRY_DATE", new FieldMapper("txnEntryDate")).put("AGENCY_IML_ID", new FieldMapper("agencyImlId"))
			.put("FROM_ADDRESS_ID", new FieldMapper("fromAddressId"))
			.put("APPLICATION_DATE", new FieldMapper("applicationDate"))
			.put("RECEIPT_NUMBER", new FieldMapper("receiptNumber"))
			.put("ADJUST_TXN_ID", new FieldMapper("adjustTxnId"))
			.put("INTERNAL_SCHEDULE_TYPE", new FieldMapper("internalScheduleType"))
			.put("EVENT_ID", new FieldMapper("eventId"))
			.put("CLOSING_CHEQUE_NUMBER", new FieldMapper("closingChequeNumber"))
			.put("STATUS_DISPLAY", new FieldMapper("statusDisplay")).put("BOOKING_TYPE", new FieldMapper("bookingType"))
			.put("ACTIVITY_DATE", new FieldMapper("activityDate")).put("CASELOAD_ID", new FieldMapper("caseloadId"))
			.put("NO_COMM_AGY_LOC_ID", new FieldMapper("noCommAgyLocId"))
			.put("RECORD_USER_ID", new FieldMapper("recordUserId"))
			.put("HEADER_STATUS", new FieldMapper("headerStatus")).put("REMITTER_NAME", new FieldMapper("remitterName"))
			.put("REQUEST_NAME", new FieldMapper("requestName"))
			.put("ARREST_AGENCY_LOC_ID", new FieldMapper("arrestAgencyLocId"))
			.put("BOOKING_STATUS", new FieldMapper("bookingStatus")).put("COMM_STATUS", new FieldMapper("commStatus"))
			.put("PROFILE_CODE", new FieldMapper("profileCode")).put("SUFFIX", new FieldMapper("suffix"))
			.put("APPLY_SPENDING_LIMIT_AMOUNT", new FieldMapper("applySpendingLimitAmount"))
			.put("SEARCH_STAFF_ID", new FieldMapper("searchStaffId"))
			.put("TOTAL_UNEXCUSED_ABSENCES", new FieldMapper("totalUnexcusedAbsences"))
			.put("TO_AGY_LOC_ID", new FieldMapper("toAgyLocId"))
			.put("HOLD_CLEAR_FLAG", new FieldMapper("holdClearFlag")).put("LAST_NAME", new FieldMapper("lastName"))
			.put("ASSIGNMENT_DATE", new FieldMapper("assignmentDate"))
			.put("MODIFY_DATETIME", new FieldMapper("modifyDatetime"))
			.put("DESCRIPTION", new FieldMapper("description")).put("REMITTER_ID", new FieldMapper("remitterId"))
			.put("CASE_OFFICER_ID", new FieldMapper("caseOfficerId")).put("PAYEE_CODE", new FieldMapper("payeeCode"))
			.put("BOOKING_BEGIN_DATE", new FieldMapper("bookingBeginDate"))
			.put("TXN_ENTRY_AMOUNT", new FieldMapper("txnEntryAmount"))
			.put("MOVEMENT_TIME", new FieldMapper("movementTime")).put("STATUS_REASON", new FieldMapper("statusReason"))
			.put("SLIP_PRINTED_FLAG", new FieldMapper("slipPrintedFlag"))
			.put("EARNED_CREDIT_LEVEL", new FieldMapper("earnedCreditLevel"))
			.put("COMMUNITY_ACTIVE_FLAG", new FieldMapper("communityActiveFlag"))
			.put("DEDUCTION_TYPE", new FieldMapper("deductionType"))
			.put("YOUTH_ADULT_CODE", new FieldMapper("youthAdultCode"))
			.put("ASSIGNED_STAFF_ID", new FieldMapper("assignedStaffId"))
			.put("INTAKE_AGY_LOC_ASSIGN_DATE", new FieldMapper("intakeAgyLocAssignDate"))
			.put("CREATE_INTAKE_AGY_LOC_ID", new FieldMapper("createIntakeAgyLocId"))
			.put("AGY_LOC_ID_LIST", new FieldMapper("agyLocIdList"))
			.put("TXN_POSTING_TYPE", new FieldMapper("txnPostingType"))
			.put("OFFENDER_ID_DISPLAY", new FieldMapper("offenderIdDisplay"))
			.put("TXN_REFERENCE_NUMBER", new FieldMapper("txnReferenceNumber"))
			.put("REPORTING_TIME", new FieldMapper("reportingTime"))
			.put("ROOT_OFFENDER_ID", new FieldMapper("rootOffenderId"))
			.put("PHOTO_TAKING_STAFF_ID", new FieldMapper("photoTakingStaffId"))
			.put("ADJUST_TXN_ENTRY_ID", new FieldMapper("adjustTxnEntryId"))
			.put("PAYEE_NAME_TEXT", new FieldMapper("payeeNameText"))
			.put("IN_OUT_STATUS", new FieldMapper("inOutStatus"))
			.put("RECEIPT_PRINTED_FLAG", new FieldMapper("receiptPrintedFlag"))
			.put("SERVICE_FEE_FLAG", new FieldMapper("serviceFeeFlag")).put("TO_CITY", new FieldMapper("toCity"))
			.put("SEAL_FLAG", new FieldMapper("sealFlag")).put("CREATE_DATETIME", new FieldMapper("createDatetime"))
			.put("COMMUNITY_AGY_LOC_ID", new FieldMapper("communityAgyLocId"))
			.put("RECEIPT_PENDING_PRINT_FLAG", new FieldMapper("receiptPendingPrintFlag"))
			.put("MOVEMENT_TYPE", new FieldMapper("movementType"))
			.put("FINGER_PRINTED_STAFF_ID", new FieldMapper("fingerPrintedStaffId"))
			.put("ASSIGNMENT_END_TIME", new FieldMapper("assignmentEndTime"))
			.put("BED_ASSIGN_SEQ", new FieldMapper("bedAssignSeq"))
			.put("TRANSFER_CASELOAD_ID", new FieldMapper("transferCaseloadId"))
			.put("EKSTRAND_CREDIT_LEVEL", new FieldMapper("ekstrandCreditLevel"))
			.put("DIRECTION_CODE", new FieldMapper("directionCode"))
			.put("COMM_STAFF_ROLE", new FieldMapper("commStaffRole")).put("CASE_TIME", new FieldMapper("caseTime"))
			.put("OJ_LOCATION_CODE", new FieldMapper("ojLocationCode"))
			.put("FROM_AGY_LOC_ID", new FieldMapper("fromAgyLocId"))
			.put("ADJUST_OFFENDER_ID", new FieldMapper("adjustOffenderId"))
			.put("PROFILE_VALUE_2", new FieldMapper("profileValue2"))
			.put("MOVEMENT_SEQ", new FieldMapper("movementSeq")).put("AGY_LOC_ID", new FieldMapper("agyLocId"))
			.put("DEDUCTION_FLAG", new FieldMapper("deductionFlag"))
			.put("MOVEMENT_REASON_CODE", new FieldMapper("movementReasonCode"))
			.put("AGY_LOC_TYPE", new FieldMapper("agyLocType")).put("MOVEMENT_DATE", new FieldMapper("movementDate"))
			.put("ASSIGNMENT_END_DATE", new FieldMapper("assignmentEndDate"))
			.put("TXN_ENTRY_DESC", new FieldMapper("txnEntryDesc")).put("INFO_NUMBER", new FieldMapper("infoNumber"))
			.put("CASE_DATE", new FieldMapper("caseDate")).put("DISCLOSURE_FLAG", new FieldMapper("disclosureFlag"))
			.put("TO_ADDRESS_ID", new FieldMapper("toAddressId"))
			.put("CREATE_AGY_LOC_ID", new FieldMapper("createAgyLocId"))
			.put("LIVING_UNIT_ID", new FieldMapper("livingUnitId"))
			.put("COMMUNITY_STATUS", new FieldMapper("communityStatus"))
			.put("LIVING_UNIT_DESCRIPTION", new FieldMapper("livingUnitDescription"))
			.put("STAFF_LAST_NAME", new FieldMapper("staffLastName")).put("ESCORT_CODE", new FieldMapper("escortCode"))
			.put("OFFENDER_ID", new FieldMapper("offenderId")).put("LOCATION_CODE", new FieldMapper("locationCode"))
			.put("HOLD_NUMBER", new FieldMapper("holdNumber")).put("PARENT_EVENT_ID", new FieldMapper("parentEventId"))
			.put("ORG_TXN_TYPE", new FieldMapper("orgTxnType")).put("TO_COUNTRY_CODE", new FieldMapper("toCountryCode"))
			.put("TXN_ID", new FieldMapper("txnId")).put("COMMENT_TEXT", new FieldMapper("commentText"))
			.put("REPORTING_DATE", new FieldMapper("reportingDate"))
			.put("INTAKE_USER_ID", new FieldMapper("intakeUserId")).put("ACTIVE_FLAG", new FieldMapper("activeFlag"))
			.put("INTAKE_CASELOAD_ID", new FieldMapper("intakeCaseloadId"))
			.put("MODIFY_USER_ID", new FieldMapper("modifyUserId"))
			.put("OLD_TABLE_NAME", new FieldMapper("oldTableName"))
			.put("INTERNAL_SCHEDULE_REASON_CODE", new FieldMapper("internalScheduleReasonCode"))
			.put("HOLD_UNTIL_DATE", new FieldMapper("holdUntilDate")).put("FROM_CITY", new FieldMapper("fromCity"))
			.put("ADJUST_ACCOUNT_CODE", new FieldMapper("adjustAccountCode"))
			.put("ALIAS_OFFENDER_ID", new FieldMapper("aliasOffenderId"))
			.put("MODIFY_DATE", new FieldMapper("modifyDate"))
			.put("BOOKING_END_DATE", new FieldMapper("bookingEndDate"))
			.put("ASSIGNMENT_TIME", new FieldMapper("assignmentTime"))
			.put("ASSIGNMENT_REASON", new FieldMapper("assignmentReason"))
			.put("BOOKING_NO", new FieldMapper("bookingNo")).put("TXN_ENTRY_SEQ", new FieldMapper("txnEntrySeq"))
			.put("PAYEE_CORPORATE_ID", new FieldMapper("payeeCorporateId"))
			.put("SUB_ACCOUNT_TYPE", new FieldMapper("subAccountType")).put("BIRTH_DATE", new FieldMapper("birthDate"))
			.put("FIRST_NAME", new FieldMapper("firstName"))
			.put("PRE_WITHHOLD_AMOUNT", new FieldMapper("preWithholdAmount")).build();
	private final Map<String, FieldMapper> offenderExternalMovementsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("MAX(MOVEMENT_DATE)", new FieldMapper("max(movementDate)"))
			.put("CAPACITY", new FieldMapper("capacity")).put("NBT_ACTIVE_FLAG2", new FieldMapper("nbtActiveFlag2"))
			.put("OEM.MOVEMENT_REASON_CODE", new FieldMapper("oem.movementReasonCode"))
			.put("MAX(MOVEMENT_TIME)", new FieldMapper("max(movementTime)"))
			.put("OFFENDER_BOOK_ID", new FieldMapper("offenderBookId")).put("0) + 1", new FieldMapper("0) + 1"))
			.put("'Y'", new FieldMapper("'y'")).put("MAX(OFFENDER_BOOK_ID)", new FieldMapper("max(offenderBookId)"))
			.put("NVL(MAX(MOVEMENT_SEQ)", new FieldMapper("nvl(max(movementSeq)"))
			.put("ROOT_OFFENDER_ID", new FieldMapper("rootOffenderId"))
			.put("LIV_UNITS.DESCRIPTION", new FieldMapper("livUnits.description"))
			.put("MR1.IN_MOVEMENT_REASON_CODE", new FieldMapper("mr1.inMovementReasonCode"))
			.put("BOOKING_NO", new FieldMapper("bookingNo")).put("MR2.DESCRIPTION", new FieldMapper("mr2.description"))
			.put("ROWID", new FieldMapper("rowid")).build();
	private final Map<String, FieldMapper> bedAssignmentHistoriesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("0) + 1", new FieldMapper("0) + 1"))
			.put("NVL (MAX (BED_ASSIGN_SEQ)", new FieldMapper("nvl (max (bedAssignSeq)"))
			.put("OFFENDER_BOOK_ID", new FieldMapper("offenderBookId")).build();
	private final Map<String, FieldMapper> offenderBookingsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("MAX(MOVEMENT_DATE)", new FieldMapper("max(movementDate)"))
			.put("CAPACITY", new FieldMapper("capacity")).put("NBT_ACTIVE_FLAG2", new FieldMapper("nbtActiveFlag2"))
			.put("OEM.MOVEMENT_REASON_CODE", new FieldMapper("oem.movementReasonCode"))
			.put("MAX(MOVEMENT_TIME)", new FieldMapper("max(movementTime)"))
			.put("OFFENDER_BOOK_ID", new FieldMapper("offenderBookId")).put("0) + 1", new FieldMapper("0) + 1"))
			.put("'Y'", new FieldMapper("'y'")).put("MAX(OFFENDER_BOOK_ID)", new FieldMapper("max(offenderBookId)"))
			.put("NVL(MAX(MOVEMENT_SEQ)", new FieldMapper("nvl(max(movementSeq)"))
			.put("ROOT_OFFENDER_ID", new FieldMapper("rootOffenderId"))
			.put("LIV_UNITS.DESCRIPTION", new FieldMapper("livUnits.description"))
			.put("MR1.IN_MOVEMENT_REASON_CODE", new FieldMapper("mr1.inMovementReasonCode"))
			.put("BOOKING_NO", new FieldMapper("bookingNo")).put("MR2.DESCRIPTION", new FieldMapper("mr2.description"))
			.build();
	private final Map<String, FieldMapper> transactionOperationsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CR_ACCOUNT_CODE", new FieldMapper("crAccountCode"))
			.put("RECEIPT_PRODUCTION_FLAG", new FieldMapper("receiptProductionFlag")).build();

	/**
	 * Creates new OidadmisRepositoryImpl class Object
	 */
	public OidadmisRepositoryImpl() {
		// OidadmisRepositoryImpl
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @param systemMode
	 * 
	 * @return List<RecordGroup>
	 */
	public List<ReferenceCodes> cgfkOffEmDspDescriptionRecordGroup(final String systemMode) {
		String mode = "ENTER-QUERY";
		final String sql = getQuery("OIDADMIS_FIND_CGFKOFFEMDSPDESCRIPTION");
		final RowMapper<ReferenceCodes> referenceCodeRowMapper = Row2BeanRowMapper.makeMapping(sql,
				ReferenceCodes.class, referenceCodesMapping);
		List<ReferenceCodes> returnList = new ArrayList<>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams("MODE", mode), referenceCodeRowMapper);
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
		return returnList;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @return List<ReferenceCodes>
	 */
	public List<ReferenceCodes> cgfkBedAhDspDescriptionRecordGroup(final String systemMode) {
		final String sql = getQuery("OIDADMIS_FIND_CGFKBEDAHDSPDESCRIPTION");
		final RowMapper<ReferenceCodes> referenceCodeRowMapper = Row2BeanRowMapper.makeMapping(sql,
				ReferenceCodes.class, referenceCodesMapping);
		List<ReferenceCodes> returnList = new ArrayList<ReferenceCodes>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams("MODE", systemMode),
					referenceCodeRowMapper);
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
		return returnList;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<AgencyLocations>
	 */
	public List<AgencyLocations> cgfkOffEmDspDescriptionAgyLocIdRecordGroup() {
		final String sql = getQuery("OIDADMIS_FIND_CGFKOFFEMDSPDESCRIPTION_AGYLOCID");
		final RowMapper<AgencyLocations> referenceCodeRowMapper = Row2BeanRowMapper.makeMapping(sql,
				AgencyLocations.class, agencyLocationsMapping);
		List<AgencyLocations> returnList = new ArrayList<>();
		returnList = namedParameterJdbcTemplate.query(sql, createParams(), referenceCodeRowMapper);
		return returnList;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @return List<MovementReasons>
	 */
	public List<MovementReasons> cgfkOffEmDspDescriptionMrRecordGroup(final String movementReasonCode) {
		final String sql = getQuery("OIDADMIS_FIND_CGFKOFFEMDSPDESCRIPTION_MR");
		final RowMapper<MovementReasons> referenceCodeRowMapper = Row2BeanRowMapper.makeMapping(sql,
				MovementReasons.class, referenceCodesMapping);
		List<MovementReasons> returnList = new ArrayList<>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams("ADMISSIONTYPE", movementReasonCode),
					referenceCodeRowMapper);
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
		return returnList;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<MovementReasons>
	 */
	public List<MovementReasons> cgfkOffEmDspDescriptionMRsnRecordGroup() {
		final String sql = getQuery("OIDADMIS_FIND_CGFKOFFEMDSPDESCRIPTION_MRSN");
		final RowMapper<MovementReasons> referenceCodeRowMapper = Row2BeanRowMapper.makeMapping(sql,
				MovementReasons.class, referenceCodesMapping);
		List<MovementReasons> returnList = new ArrayList<>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams(), referenceCodeRowMapper);
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
		return returnList;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<AgencyLocations>
	 */
	public List<AgencyLocations> cgfkOffEmDspDescriptionCaseloadIdRecordGroup(final String systemMode) {
		final String sql = getQuery("OIDADMIS_FIND_CGFKOFFEMDSPDESCRIPTION_CASELOAD_ID");
		final RowMapper<AgencyLocations> referenceCodeRowMapper = Row2BeanRowMapper.makeMapping(sql,
				AgencyLocations.class, referenceCodesMapping);
		List<AgencyLocations> returnList = new ArrayList<AgencyLocations>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams("CASELOADID", systemMode),
					referenceCodeRowMapper);
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
		return returnList;
	}

	/**
	 * method for query callings
	 * 
	 * @return List<AgencyLocations>
	 */
	public List<AgencyLocations> cgfkOffEmDspDescriptionRGroup() {
		final String sql = getQuery("OIDADMIS_FIND_CGFKOFFEMDSPDESCRIPTION");
		final RowMapper<AgencyLocations> alertOffenderRowMapper = Row2BeanRowMapper.makeMapping(sql,
				AgencyLocations.class, referenceCodesMapping);
		List<AgencyLocations> returnList = new ArrayList<>();
		returnList = (namedParameterJdbcTemplate.query(sql, createParams(), alertOffenderRowMapper));
		return returnList;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<ReferenceCodes>
	 */
	public List<ReferenceCodes> cgfkOffEmDspDescriptionRcRecordGroup(final String systemMode) {
		String mode = "'ENTER-QUERY'";
		final String sql = getQuery("OIDADMIS_FIND_CGFKOFFEMDSPDESCRIPTION_RC");
		final RowMapper<ReferenceCodes> referenceCodeRowMapper = Row2BeanRowMapper.makeMapping(sql,
				ReferenceCodes.class, referenceCodesMapping);
		List<ReferenceCodes> returnList = new ArrayList<ReferenceCodes>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams("MODE", mode), referenceCodeRowMapper);
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
		return returnList;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao VHeaderBlock2
	 *
	 * @return List<VHeaderBlock2>
	 */
	public List<VHeaderBlock2> offbkgExecuteQuery(final VHeaderBlock2 vHeaderBlock2) {
		final String sqlQueryTemp = getQuery("OIDADMIS_OFFBKG_FIND_V_HEADER_BLOCK");
		final StringBuffer sqlQuery = new StringBuffer(sqlQueryTemp);
		String preparedSql = null;
		final MapSqlParameterSource valuesList = new MapSqlParameterSource();
		sqlQuery.append(sqlQueryTemp);
		if (vHeaderBlock2 != null) {
			sqlQuery.append(" where ");
			if (vHeaderBlock2.getCreateUserId() != null) {
				valuesList.addValue("userId", vHeaderBlock2.getCreateUserId());
			}
			if (vHeaderBlock2.getOffenderId() != null) {
				sqlQuery.append("OFFENDER_ID =  :offenderId " + " and ");
				valuesList.addValue("offenderId", vHeaderBlock2.getOffenderId());
			}

		}
		preparedSql = sqlQuery.toString().trim();
		if (preparedSql.endsWith("where")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 5);
		}
		if (preparedSql.endsWith("AND")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 3);
		}

		final RowMapper<VHeaderBlock2> VHeaderBlock2RowMapper = Row2BeanRowMapper.makeMapping(preparedSql,
				VHeaderBlock2.class, vHeaderBlock2Mapping);
		List<VHeaderBlock2> returnList = new ArrayList<>();
		returnList = namedParameterJdbcTemplate.query(preparedSql, valuesList, VHeaderBlock2RowMapper);
		return returnList;
	}

	/**
	 * @param
	 */
	public int PRE_INSERT() {
		return 0;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao OffenderBookings
	 *
	 * @return List<OffenderBookings>
	 *
	 * @
	 */
	public List<OffenderBookings> offBkgsSearchOffenderBookings(final OffenderBookings offenderBookings) {
		final String sqlQueryTemp = getQuery("OIDADMIS_OFFBKGS_FIND_OFFENDER_BOOKINGS");

		String preparedSql = null;
		final StringBuffer sqlQuery = new StringBuffer();
		final MapSqlParameterSource valuesList = new MapSqlParameterSource();
		sqlQuery.append(sqlQueryTemp);
		if (offenderBookings != null) {
			sqlQuery.append(" where ");
			if (offenderBookings.getRootOffenderId() != null) {
				sqlQuery.append("root_offender_id =:rootOffenderId " + " and ");
				valuesList.addValue("rootOffenderId", offenderBookings.getRootOffenderId());
			}

		}
		preparedSql = sqlQuery.toString().trim();
		if (preparedSql.endsWith("where")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 5);
		}
		if (preparedSql.endsWith("and")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 3);
		}
		sqlQuery.delete(0, sqlQuery.length());
		sqlQuery.append(preparedSql);
		sqlQuery.append(" Order by BOOKING_BEGIN_DATE desc");
		preparedSql = sqlQuery.toString().trim();
		final RowMapper<OffenderBookings> OffenderBookingsRowMapper = Row2BeanRowMapper.makeMapping(preparedSql,
				OffenderBookings.class, offenderBookingsMapping);
		List<OffenderBookings> returnList = new ArrayList<OffenderBookings>();
		returnList = namedParameterJdbcTemplate.query(preparedSql, valuesList, OffenderBookingsRowMapper);
		return returnList;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao OffenderExternalMovements
	 *
	 * @return List<OffenderExternalMovements>
	 */
	public List<OffenderExternalMovements> offEmSearchOffenderExternalMovements(
			final OffenderExternalMovements objSearchDao) {
		final String sql = getQuery("OIDADMIS_OFFEM_FIND_OFFENDER_EXTERNAL_MOVEMENTS");
		String preparedSql = null;
		final StringBuffer sqlQuery = new StringBuffer();
		final MapSqlParameterSource valuesList = new MapSqlParameterSource();
		sqlQuery.append(sql);
		if (objSearchDao != null) {
			sqlQuery.append(" where ");

			if (objSearchDao.getOffenderBookId() != null) {
				sqlQuery.append("OFFENDER_BOOK_ID =  :offenderBookId " + " and ");
				valuesList.addValue("offenderBookId", objSearchDao.getOffenderBookId());
			}
			if (objSearchDao.getMovementSeq() != null) {
				sqlQuery.append("MOVEMENT_SEQ =  :movementSeq " + " and ");
				valuesList.addValue("movementSeq", objSearchDao.getMovementSeq());
			}
		}
		preparedSql = sqlQuery.toString().trim();
		if (preparedSql.endsWith("where")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 5);
		}
		if (preparedSql.endsWith("and")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 3);
		}
		final RowMapper<OffenderExternalMovements> OffenderExternalMovementsRowMapper = Row2BeanRowMapper
				.makeMapping(preparedSql, OffenderExternalMovements.class, offenderExternalMovementsMapping);
		List<OffenderExternalMovements> returnList = new ArrayList<OffenderExternalMovements>();
		returnList = namedParameterJdbcTemplate.query(preparedSql, valuesList, OffenderExternalMovementsRowMapper);
		return returnList;
	}

	/**
	 * This method is used to insert the records in the data base tables based on
	 *
	 * @param lstOffenderExternalMovements List<OffenderExternalMovements>
	 *
	 * @return List<Integer>
	 */
	public Integer offemInsertOffenderExternalMovements(
			final List<OffenderExternalMovements> lstOffenderExternalMovements) {
		final String sql = getQuery("OIDADMIS_OFFEM_INSERT_OFFENDER_EXTERNAL_MOVEMENTS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (OffenderExternalMovements offenderExternalMovements : lstOffenderExternalMovements) {
			parameters.add(new BeanPropertySqlParameterSource(offenderExternalMovements));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstOffenderExternalMovements.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to insert the records in the data base tables based on
	 *
	 * @param offBook OffenderBookings
	 *
	 * @return Integer
	 */
	public Integer insertOffenderBookings(final OffenderBookings offBook) {
		offBook.setActiveFlag("Y");
		offBook.setBookingStatus("O");
		offBook.setInOutStatus("IN");
		offBook.setBookingType("INST");
		final String sql = getQuery("OIDADMIS_OFFBKGS_INSERT_OFFENDER_BOOKINGS");
		final List<String> returnList = new ArrayList<>();
		Integer returnArray = null;
		try {
		returnArray = namedParameterJdbcTemplate.update(sql, new BeanPropertySqlParameterSource(offBook));
		if (returnArray != 0) {
			returnList.add(offBook.getOffenderBookId().toString());
			returnList.add(offBook.getLivUnitDesc());
			returnList.add(offBook.getStatusReason());

		}
		}catch (Exception e) {
			logger.error("in " + this.getClass().getName() + " in insertOffenderBookings ", e);
		}
		try {
			if(offBook.getIepLevel()!=null) {				
				final String iepLevelInsertsql = getQuery("OIDIEPLV_OIDADMIS_INSERT_DATA");
				MapSqlParameterSource map = new MapSqlParameterSource();
				map.addValue("offenderBookId", offBook.getOffenderBookId());
				map.addValue("iepLevelCode", offBook.getIepLevel());
				map.addValue("reviewComment", ApplicationConstants.INITIAL_INTAKE);
				map.addValue("staffId", getStaffId(offBook.getSystemGeneratedStaff()));
				map.addValue("dateAsigned", new Date());
				map.addValue("modifyUserId", null);
				map.addValue("createUserId", offBook.getCreateUserId());
				namedParameterJdbcTemplate.update(iepLevelInsertsql, map);
			}
		} catch (Exception e) {
			logger.error("in " + this.getClass().getName() + " in insertOffenderBookings ", e);
			returnArray=18;
		}
		return returnArray;
	}

	public List<IepLevelBean> getIEPLov() {
		List<IepLevelBean> lovList = new ArrayList<>();
		String sql = getQuery("OIDIEPLV_OIDADMIS_IEP_LEVEL_DETAILS");
		RowMapper<IepLevelBean> mapper = Row2BeanRowMapper.makeMapping(sql, IepLevelBean.class,
				transactionOperationsMapping);
		try {
			lovList = namedParameterJdbcTemplate.query(sql, createParams(), mapper);
		} catch (Exception e) {
			logger.error("getIEPLov", e);
		}
		return lovList;
	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstOffenderExternalMovements OffenderExternalMovements
	 */
	@Override
	public Integer offEmUpdateOffenderExternalMovements(final OffenderExternalMovements lstOffenderExternalMovements) {
		final String sql = getQuery("OIDADMIS_OFFEM_UPDATE_OFFENDER_EXTERNAL_MOVEMENTS");
		Integer returnArray = null;
		try {
			returnArray = namedParameterJdbcTemplate.update(sql,
					new BeanPropertySqlParameterSource(lstOffenderExternalMovements));
		} catch (Exception e) {
			return returnArray;
		}
		return returnArray;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param offBook OffenderBookings
	 *
	 * @return Integer
	 */
	public Integer updateOffenderBookings(final OffenderBookings offBook, final Integer staffId) {
		final String sql = getQuery("OIDADMIS_OFFBKG_UPDATE_OFFENDER_BOOKINGS");
		Integer returnArray = null;
		offBook.setActiveFlag("Y");
		offBook.setBookingStatus("O");
		offBook.setInOutStatus("IN");
		offBook.setAssignedStaffId(BigDecimal.valueOf(staffId));
		final List<String> returnList = new ArrayList<>();
		returnArray = namedParameterJdbcTemplate.update(sql, new BeanPropertySqlParameterSource(offBook));
		if (returnArray != 0) {
			returnList.add(offBook.getOffenderBookId().toString());
			returnList.add(offBook.getLivUnitDesc());
			returnList.add(offBook.getStatusReason());
		}
		return returnArray;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao BedAssignmentHistories
	 *
	 * @return List<BedAssignmentHistories>
	 */
	public List<BedAssignmentHistories> bedAhSearchBedAssignmentHistories(final BedAssignmentHistories objSearchDao) {
		final String sql = getQuery("OIDADMIS_BEDAH_FIND_BED_ASSIGNMENT_HISTORIES");
		final RowMapper<BedAssignmentHistories> BedAssignmentHistoriesRowMapper = Row2BeanRowMapper.makeMapping(sql,
				BedAssignmentHistories.class, bedAssignmentHistoriesMapping);
		List<BedAssignmentHistories> returnList = new ArrayList<>();
		returnList = namedParameterJdbcTemplate.query(sql, createParams("offenderBookId",
				objSearchDao.getOffenderBookId(), "bedAssignSeq", objSearchDao.getBedAssignSeq()),
				BedAssignmentHistoriesRowMapper);
		return returnList;
	}

	/**
	 * This method is used to insert the records in the data base tables based on
	 *
	 * @param lstBedAssignmentHistories List<BedAssignmentHistories>
	 *
	 * @return List<Integer>
	 */
	public Integer bedAhInsertBedAssignmentHistories(final List<BedAssignmentHistories> lstBedAssignmentHistories) {
		final String sql = getQuery("OIDADMIS_BEDAH_INSERT_BED_ASSIGNMENT_HISTORIES");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (BedAssignmentHistories bedAssignmentHistories : lstBedAssignmentHistories) {
			parameters.add(new BeanPropertySqlParameterSource(bedAssignmentHistories));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstBedAssignmentHistories.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao OffenderTransactions
	 *
	 * @return List<OffenderTransactions>
	 */
	public List<OffenderTransactions> offTxnSearchOffenderTransactions(final OffenderTransactions objSearchDao) {
		final String sql = getQuery("OIDADMIS_OFFTXN_FIND_OFFENDER_TRANSACTIONS");
		final RowMapper<OffenderTransactions> OffenderTransactionsRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderTransactions.class, offenderTransactionsMapping);
		List<OffenderTransactions> returnList = new ArrayList<>();
		returnList = namedParameterJdbcTemplate.query(sql,
				createParams("txnId", objSearchDao.getTxnId(), "txnEntrySeq", objSearchDao.getTxnEntrySeq()),
				OffenderTransactionsRowMapper);
		return returnList;
	}

	/**
	 * This method is used to insert the records in the data base tables based on
	 *
	 * @param lstOffenderTransactions List<OffenderTransactions>
	 *
	 * @return List<Integer>
	 */
	public Integer offtxnInsertOffenderTransactions(final List<OffenderTransactions> lstOffenderTransactions) {
		final String sql = getQuery("OIDADMIS_OFFTXN_INSERT_OFFENDER_TRANSACTIONS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (OffenderTransactions offenderTransactions : lstOffenderTransactions) {
			parameters.add(new BeanPropertySqlParameterSource(offenderTransactions));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstOffenderTransactions.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao SystemProfiles
	 *
	 * @return List<SystemProfiles>
	 */
	public List<SystemProfiles> sysPflSearchSystemProfiles(final SystemProfiles objSearchDao) {
		final String sql = getQuery("OIDADMIS_SYSPFL_FIND_SYSTEM_PROFILES");
		final RowMapper<SystemProfiles> SystemProfilesRowMapper = Row2BeanRowMapper.makeMapping(sql,
				SystemProfiles.class, systemProfilesMapping);
		List<SystemProfiles> returnList = new ArrayList<>();
		returnList = namedParameterJdbcTemplate.query(sql, createParams("profileType", objSearchDao.getProfileType(),
				"profileCode", objSearchDao.getProfileCode()), SystemProfilesRowMapper);
		return returnList;
	}

	/**
	 * This method is used to insert the records in the data base tables based on
	 *
	 * @param lstSystemProfiles List<SystemProfiles>
	 *
	 * @return List<Integer>
	 */
	public Integer syspflInsertSystemProfiles(final List<SystemProfiles> lstSystemProfiles) {
		final String sql = getQuery("OIDADMIS_SYSPFL_INSERT_SYSTEM_PROFILES");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (SystemProfiles systemProfiles : lstSystemProfiles) {
			parameters.add(new BeanPropertySqlParameterSource(systemProfiles));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstSystemProfiles.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

	/**
	 * This method is used to delete records from data base tables based on
	 *
	 * @param lstSystemProfiles List<SystemProfiles>
	 *
	 * @
	 */
	public Integer sysPflDeleteSystemProfiles(final List<SystemProfiles> lstSystemProfiles) {
		final String sql = getQuery("OIDADMIS_SYSPFL_DELETE_SYSTEM_PROFILES");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (SystemProfiles systemProfiles : lstSystemProfiles) {
			parameters.add(new BeanPropertySqlParameterSource(systemProfiles));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstSystemProfiles.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * @param params
	 *
	 */
	public List<LivingUnits> dspDescriptionWhenValidateItemgetCapacityCur(final LivingUnits paramBean) {
		final String sql = getQuery("OIDADMIS_DSP_DESCRIPTION5_WHENVALIDATEITEM_GET_CAPACITY_CUR");
		final RowMapper<LivingUnits> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, LivingUnits.class,
				livingUnitsMapping);
		List<LivingUnits> returnList = new ArrayList<>();
		returnList = namedParameterJdbcTemplate.query(sql, createParams(), columnRowMapper);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * oidadmisDspDescription5WhenValidateItemlivDesc
	 *
	 * @param params
	 *
	 */
	public List<LivingUnits> dspDescriptionWhenValidateItemlivDesc(final LivingUnits paramBean) {
		final String sql = getQuery("OIDADMIS_DSP_DESCRIPTION_WHENVALIDATEITEM_LIV_DESC");
		final RowMapper<LivingUnits> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, LivingUnits.class,
				livingUnitsMapping);
		List<LivingUnits> returnList = new ArrayList<>();
		returnList = namedParameterJdbcTemplate.query(sql, createParams(), columnRowMapper);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * oidadmisNbtActiveFlag2WhenValidateItembookNoCrsr
	 *
	 * @param params
	 *
	 */
	public OffenderBookings nbtActiveFlagWhenValidateItembookNoCrsr(final OffenderBookings paramBean) {
		final String sql = getQuery("OIDADMIS_NBT_ACTIVE_FLAG_WHENVALIDATEITEM_BOOK_NO_CRSR");
		final RowMapper<OffenderBookings> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, OffenderBookings.class,
				offenderBookingsMapping);
		final OffenderBookings returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(),
				columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * oidadmisOffEmWhenNewBlockInstanceoffAlertCur
	 *
	 * @param params
	 *
	 */
	public String offEmWhenNewBlockInstanceoffAlertCur(final Integer pMsgNumber) {
		final String sql = getQuery("OIDADMIS_OFF_EM_WHENNEWBLOCKINSTANCE_OFF_ALERT_CUR");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql, createParams("P_NUMBER", pMsgNumber), String.class);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * offEmWhenNewBlockInstancecasAgyCur
	 *
	 * @param params
	 *
	 */
	public Integer offEmWhenNewBlockInstancecasAgyCur(final CaseloadAgencyLocations paramBean) {
		final String sql = getQuery("OIDADMIS_OFF_EM_WHENNEWBLOCKINSTANCE_CAS_AGY_CUR");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("CASELOADID", paramBean.getCaseloadId()),
				Integer.class);
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * oidadmisOffEmWhenNewBlockInstance
	 *
	 * @param params
	 *
	 */
	public SystemMessages offEmWhenNewBlockInstance(final SystemMessages paramBean) {
		final String sql = getQuery("OIDADMIS_OFF_EM_WHENNEWBLOCKINSTANCE_");
		final RowMapper<CaseloadAgencyLocations> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				CaseloadAgencyLocations.class, caseloadAgencyLocationsMapping);
		SystemMessages returnObj = (SystemMessages) namedParameterJdbcTemplate.query(sql, createParams(),
				columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * oidadmisOffEmWhenNewRecordInstancegetCapacityCur
	 *
	 * @param params
	 *
	 */
	public LivingUnits offEmWhenNewRecordInstancegetCapacityCur(final LivingUnits paramBean) {
		final String sql = getQuery("OIDADMIS_OFF_EM_WHENNEWRECORDINSTANCE_GET_CAPACITY_CUR");
		final RowMapper<LivingUnits> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, LivingUnits.class,
				livingUnitsMapping);
		final ArrayList<LivingUnits> returnList = (ArrayList<LivingUnits>) namedParameterJdbcTemplate.query(sql,
				createParams(), columnRowMapper);
		return returnList.get(0);
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * oidadmisOffEmWhenNewRecordInstanceadm
	 *
	 * @param params
	 *
	 */
	public OffenderExternalMovements offEmWhenNewRecordInstanceadm(final OffenderExternalMovements paramBean) {
		final String sql = getQuery("OIDADMIS_OFF_EM_WHENNEWRECORDINSTANCE_ADM");
		final RowMapper<OffenderExternalMovements> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderExternalMovements.class, offenderExternalMovementsMapping);
		OffenderExternalMovements returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(),
				columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * oidadmisOffEmWhenNewRecordInstance
	 *
	 * @param params
	 *
	 */
	public CaseloadAgencyLocations offEmWhenNewRecordInstance(final CaseloadAgencyLocations paramBean) {
		final String sql = getQuery("OIDADMIS_AGY_LOC_WHENNEWRECORDINSTANCE");
		final RowMapper<CaseloadAgencyLocations> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				CaseloadAgencyLocations.class, caseloadAgencyLocationsMapping);
		CaseloadAgencyLocations returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(),
				columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * oidadmisOffEmWhenNewRecordInstance
	 *
	 * @param params
	 *
	 */
	public List<AgencyLocations> offEmWhenNewRecordInstance(final AgencyLocations paramBean) {
		final String sql = getQuery("OIDADMIS_OFF_EM_WHENNEWRECORDINSTANCE");
		final RowMapper<AgencyLocations> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, AgencyLocations.class,
				agencyLocationsMapping);
		List<AgencyLocations> returnList = new ArrayList<>();
		returnList = namedParameterJdbcTemplate.query(sql, createParams(), columnRowMapper);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * oidadmisOffEmPreInsertc
	 *
	 * @param params
	 *
	 */
	public OffenderExternalMovements offEmPreInsertc(final OffenderExternalMovements paramBean) {
		final String sql = getQuery("OIDADMIS_OFF_EM_PREINSERT_C");
		final OffenderExternalMovements returnObj = new OffenderExternalMovements();
		final Object obj = namedParameterJdbcTemplate.queryForObject(sql,
				createParams("offenderBookId", paramBean.getOffenderBookId()), Object.class);
		if (returnObj != null) {
			returnObj.setMovementSeq(Long.valueOf(obj.toString()));
		}
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * oidadmisOffEmPreInsertc
	 *
	 * @param params
	 *
	 */
	public Long offbkgPreInsertc() {
		final String sql = getQuery("OIDADMIS_OFF_BKG_PREINSERT_C");
		final Long obj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), Long.class);

		return obj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * oidadmisDspDescriptionWhenValidateItemvsLcdCur
	 *
	 * @param params
	 *
	 */
	public List<LivingUnits> dspDescriptionWhenValidateItemvsLcdCur(final LivingUnits paramBean) {
		final String sql = getQuery("OIDADMIS_DSP_DESCRIPTION_WHENVALIDATEITEM_VS_LCD_CUR");
		final RowMapper<LivingUnits> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, LivingUnits.class,
				livingUnitsMapping);
		List<LivingUnits> returnList = new ArrayList<>();
		returnList = namedParameterJdbcTemplate.query(sql, createParams(), columnRowMapper);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * oidadmisCgfkchkOffBkgsOffBkgStafc
	 *
	 * @param params
	 *
	 */
	public StaffMembers cgfkchkOffBkgsOffBkgStafc(final StaffMembers paramBean) {
		final String sql = getQuery("OIDADMIS_CGFKCHK_OFF_BKGS_OFF_BKG_STAF_C");
		final RowMapper<StaffMembers> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, StaffMembers.class,
				staffMembersMapping);
		StaffMembers returnList = new StaffMembers();
		returnList = namedParameterJdbcTemplate.queryForObject(sql, createParams("userId", paramBean.getCreateUserId()),
				columnRowMapper);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * oidadmisCgfkchkOffEmOffEmRefCodc
	 *
	 * @param params
	 *
	 */
	public ReferenceCodes cgfkchkOffEmOffEmRefCodc(final ReferenceCodes paramBean) {
		final String sql = getQuery("OIDADMIS_CGFKCHK_OFF_EM_OFF_EM_REF_COD_C");
		final RowMapper<ReferenceCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				referenceCodesMapping);
		final ReferenceCodes returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(),
				columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * oidadmisCgfklkpOffEmOffEmRefCodc
	 *
	 * @param params
	 *
	 */
	public ReferenceCodes cgfkkpOffEmOffEmRefCodc(final ReferenceCodes paramBean) {
		final String sql = getQuery("OIDADMIS_CGFKLKP_OFF_EM_OFF_EM_REF_COD_C");
		final RowMapper<ReferenceCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				referenceCodesMapping);
		final ReferenceCodes returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(),
				columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * oidadmisCgfkchkOffEmOffEmRefc
	 *
	 * @param params
	 *
	 */
	public ReferenceCodes cgfkchkOffEmOffEmRefc(final ReferenceCodes paramBean) {
		final String sql = getQuery("OIDADMIS_CGFKCHK_OFF_EM_OFF_EM_REF_C");
		final RowMapper<ReferenceCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				referenceCodesMapping);
		final ReferenceCodes returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(),
				columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * oidadmisCgfklkpOffEmOffEmRef2c
	 *
	 * @param params
	 *
	 */
	public ReferenceCodes cgfklkpOffEmOffEmRef2c(final ReferenceCodes paramBean) {
		final String sql = getQuery("OIDADMIS_CGFKLKP_OFF_EM_OFF_EM_REF_C");
		final RowMapper<ReferenceCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				referenceCodesMapping);
		final ReferenceCodes returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(),
				columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * oidadmisCgfkchkOffEmOffEmMoveRsc
	 *
	 * @param params
	 *
	 */
	public MovementReasons cgfkchkOffEmOffEmMoveRsc(final MovementReasons paramBean) {
		final String sql = getQuery("OIDADMIS_CGFKCHK_OFF_EM_OFF_EM_MOVE_RS_C");
		final RowMapper<MovementReasons> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, MovementReasons.class,
				movementReasonsMapping);
		final MovementReasons returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(),
				columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * oidadmisCgfklkpOffEmOffEmMoveRsc
	 *
	 * @param params
	 *
	 */
	public MovementReasons cgfklkpOffEmOffEmMoveRsc(final MovementReasons paramBean) {
		final String sql = getQuery("OIDADMIS_CGFKLKP_OFF_EM_OFF_EM_MOVE_RS_C");
		final RowMapper<MovementReasons> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, MovementReasons.class,
				movementReasonsMapping);
		final MovementReasons returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(),
				columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * oidadmisCgfkchkOffEmOffEmAgyLocc
	 *
	 * @param params
	 *
	 */
	public AgencyLocations cgfkchkOffEmOffEmAgyLocc(final AgencyLocations paramBean) {
		final String sql = getQuery("OIDADMIS_CGFKCHK_OFF_EM_OFF_EM_AGY_LOC_C");
		final RowMapper<AgencyLocations> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, AgencyLocations.class,
				agencyLocationsMapping);
		final AgencyLocations returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(),
				columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * oidadmisCgfklkpOffEmOffEmAgyLocc
	 *
	 * @param params
	 *
	 */
	public AgencyLocations cgfklkpOffEmOffEmAgyLocc(final AgencyLocations paramBean) {
		final String sql = getQuery("OIDADMIS_CGFKLKP_OFF_EM_OFF_EM_AGY_LOC_C");
		final RowMapper<AgencyLocations> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, AgencyLocations.class,
				agencyLocationsMapping);
		final AgencyLocations returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(),
				columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * oidadmisCgfkchkOffEmOffEmAgy2c
	 *
	 * @param params
	 *
	 */
	public AgencyLocations cgfkchkOffEmOffEmAgyc(final AgencyLocations paramBean) {
		final String sql = getQuery("OIDADMIS_CGFKCHK_OFF_EM_OFF_EM_AGY_C");
		final RowMapper<AgencyLocations> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, AgencyLocations.class,
				agencyLocationsMapping);
		final AgencyLocations returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(),
				columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * oidadmisCgfklkpOffEmOffEmAgy2c
	 *
	 * @param params
	 *
	 */
	public List<AgencyLocations> cgfklkpOffEmOffEmAgyc(final AgencyLocations paramBean) {
		final String sql = getQuery("OIDADMIS_CGFKLKP_OFF_EM_OFF_EM_AGY_C");
		final RowMapper<AgencyLocations> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, AgencyLocations.class,
				agencyLocationsMapping);
		List<AgencyLocations> returnList = new ArrayList<>();
		returnList = namedParameterJdbcTemplate.query(sql, createParams(), columnRowMapper);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * oidadmisCgfkchkBedAhBedAhLivUnic
	 *
	 * @param params
	 *
	 */
	public List<LivingUnits> cgfkchkBedAhBedAhLivUnic(final LivingUnits paramBean) {
		final String sql = getQuery("OIDADMIS_CGFKCHK_BED_AH_BED_AH_LIV_UNI_C");
		final RowMapper<LivingUnits> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, LivingUnits.class,
				livingUnitsMapping);
		List<LivingUnits> returnList = new ArrayList<>();
		returnList = namedParameterJdbcTemplate.query(sql, createParams(), columnRowMapper);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * oidadmisCgfklkpBedAhBedAhLivUnic
	 *
	 * @param params
	 *
	 */
	public LivingUnits cgfklkpBedAhBedAhLivUnic(final LivingUnits paramBean) {
		final String sql = getQuery("OIDADMIS_CGFKLKP_BED_AH_BED_AH_LIV_UNI_C");
		final RowMapper<LivingUnits> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, LivingUnits.class,
				livingUnitsMapping);
		LivingUnits returnList = new LivingUnits();
		returnList = namedParameterJdbcTemplate.queryForObject(sql,
				createParams("DSPDESCRIPTION", paramBean.getDescription()), columnRowMapper);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * oidadmisCgfkchkOffBkgsOffBkgRef
	 *
	 * @param params
	 *
	 */
	public ReferenceCodes cgfkchkOffBkgsOffBkgRef(final ReferenceCodes paramBean) {
		final String sql = getQuery("OIDADMIS_CGFKCHK_OFF_BKGS_OFF_BKG_REF_C");
		final RowMapper<ReferenceCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				referenceCodesMapping);
		final ReferenceCodes returnObj = namedParameterJdbcTemplate.queryForObject(sql,
				createParams("BOOKINGSTATUS", paramBean.getCode()), columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * oidadmisInsertMasterRec
	 *
	 * @param params
	 *
	 */
	public TransactionOperations insertMasterRec(final TransactionOperations paramBean) {
		final String sql = getQuery("OIDADMIS_INSERT_MASTER_REC_TRAN");
		final RowMapper<TransactionOperations> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				TransactionOperations.class, transactionOperationsMapping);
		TransactionOperations returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(),
				columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * oidadmisInsertMasterRec
	 *
	 * @param params
	 *
	 */
	public TransactionTypes insertMasterRecTransTypes(final TransactionTypes paramBean) {
		final String sql = getQuery("OIDADMIS_INSERT_MASTER_REC");
		final RowMapper<TransactionTypes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, TransactionTypes.class,
				transactionTypesMapping);
		final TransactionTypes returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(),
				columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * oidadmisStorGlobalsgetCrAccount
	 *
	 * @param params
	 *
	 */
	public TransactionOperation storGlobalsgetCrAccount(final TransactionOperation paramBean) {
		final String sql = getQuery("OIDADMIS_STOR_GLOBALS_GET_CR_ACCOUNT");
		final RowMapper<TransactionOperation> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				TransactionOperation.class, transactionOperationsMapping);
		TransactionOperation returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(),
				columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * oidadmisStorGlobals
	 *
	 * @param params
	 *
	 */
	public TransactionTypes storGlobals(final TransactionTypes paramBean) {
		final String sql = getQuery("OIDADMIS_STOR_GLOBALS");
		final RowMapper<TransactionTypes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, TransactionTypes.class,
				transactionTypesMapping);
		final TransactionTypes returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(),
				columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * oidadmisCheckActiveYnactCurr
	 *
	 * @param params
	 *
	 */
	public List<String> checkActiveYnactCurr(final OffenderExternalMovements bean) {
		final String sql = getQuery("OIDADMIS_CHECK_ACTIVE_YN_ACT_CURR");
		List<String> returnList = new ArrayList<>();
		returnList = namedParameterJdbcTemplate.queryForList(sql,
				createParams("P_ROOT_OFF_ID", bean.getRootOffenderId()), String.class);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * oidadmisCheckPrevBkgClosedchekCrsr
	 *
	 * @param params
	 *
	 */
	public OffenderBookings checkPrevBkgClosedchekCrsr(final OffenderBookings paramBean) {
		final String sql = getQuery("OIDADMIS_CHECK_PREV_BKG_CLOSED_CHEK_CRSR");
		final RowMapper<OffenderBookings> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, OffenderBookings.class,
				offenderBookingsMapping);
		final OffenderBookings returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(),
				columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * oidadmisGetStaffNamedefStaffCur
	 *
	 * @param params
	 *
	 */
	public List<StaffMembers> getStaffNamedefStaffCur(final StaffMembers paramBean) {
		final String sql = getQuery("OIDADMIS_GET_STAFF_NAME_DEF_STAFF_CUR");
		final RowMapper<StaffMembers> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, StaffMembers.class,
				staffMembersMapping);
		List<StaffMembers> returnList = new ArrayList<>();
		returnList = namedParameterJdbcTemplate.query(sql, createParams(), columnRowMapper);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * oidadmisCallThePreInsertc
	 *
	 * @param params
	 *
	 */
	public Long callThePreInsertc(final BedAssignmentHistories paramBean) {
		final String sql = getQuery("OIDADMIS_CALL_THE_PRE_INSERT_C");
		final String returnValue = namedParameterJdbcTemplate.queryForObject(sql, createParams(), String.class);
		return new Long(returnValue);
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * oidadmisCheckCapacityvsLcdCur
	 *
	 * @param params
	 *
	 */
	public List<LivingUnits> checkCapacityvsLcdCur(final LivingUnits paramBean) {
		final String sql = getQuery("OIDADMIS_CHECK_CAPACITY_VS_LCD_CUR");
		final RowMapper<LivingUnits> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, LivingUnits.class,
				livingUnitsMapping);
		List<LivingUnits> returnList = new ArrayList<>();
		returnList = namedParameterJdbcTemplate.query(sql, createParams(), columnRowMapper);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * oidadmisPopulateGlobalCapacityvsLcdCur
	 *
	 * @param params
	 *
	 */
	public List<LivingUnits> populateGlobalCapacityvsLcdCur(final LivingUnits paramBean) {
		final String sql = getQuery("OIDADMIS_POPULATE_GLOBAL_CAPACITY_VS_LCD_CUR");
		final RowMapper<LivingUnits> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, LivingUnits.class,
				livingUnitsMapping);
		List<LivingUnits> returnList = new ArrayList<>();
		returnList = namedParameterJdbcTemplate.query(sql, createParams(), columnRowMapper);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * oidadmisGetCountOfAgyInCasegetCountCur
	 *
	 * @param params
	 *
	 */
	public CaseloadAdmOtherProfiles getCountOfAgyInCasegetCountCur(final CaseloadAdmOtherProfiles paramBean) {
		final String sql = getQuery("OIDADMIS_GET_COUNT_OF_AGY_IN_CASE_GET_COUNT_CUR");
		final RowMapper<CaseloadAdmOtherProfiles> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				CaseloadAdmOtherProfiles.class, caseloadAdmOtherProfilesMapping);
		CaseloadAdmOtherProfiles returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(),
				columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * oidadmisGetCountOfAgyInCasegetAgyCur
	 *
	 * @param params
	 *
	 */
	public CaseloadAdmOtherProfiles getCountOfAgyInCasegetAgyCur(final CaseloadAdmOtherProfiles paramBean) {
		final String sql = getQuery("OIDADMIS_GET_COUNT_OF_AGY_IN_CASE_GET_AGY_CUR");
		final RowMapper<CaseloadAdmOtherProfiles> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				CaseloadAdmOtherProfiles.class, caseloadAdmOtherProfilesMapping);
		CaseloadAdmOtherProfiles returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(),
				columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * oidadmisGetCountOfAgyInCaseagyLocCur
	 *
	 * @param params
	 *
	 */
	public String getCountOfAgyInCaseagyLocCur(final CaseloadAgencyLocations paramBean) {
		final String sql = getQuery("OIDADMIS_GET_COUNT_OF_AGY_IN_CASE_AGY_LOC_CUR");
		final String returnValue = namedParameterJdbcTemplate.queryForObject(sql, createParams(), String.class);
		return returnValue;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * oidadmisPopulateDspDescription5Rg
	 *
	 * @param params
	 *
	 */
	public AgencyLocations populateDspDescriptionRg(final AgencyLocations paramBean) {
		final String sql = getQuery("OIDADMIS_POPULATE_DSP_DESCRIPTION5_RG");
		final RowMapper<AgencyLocations> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, AgencyLocations.class,
				agencyLocationsMapping);
		final AgencyLocations returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(),
				columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * oidadmisValidateMovementDatetimemaxBookId
	 *
	 * @param params
	 *
	 */
	public Long validateMovementDatetimemaxBookId(final OffenderBookings paramBean) {
		final String sql = getQuery("OIDADMIS_VALIDATE_MOVEMENT_DATETIME_MAX_BOOK_ID");
		final String returnValue = namedParameterJdbcTemplate.queryForObject(sql, createParams(), String.class);
		return new Long(returnValue);
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * oidadmisValidateMovementDatetimemaxDate
	 *
	 * @param params
	 *
	 */
	public Object validateMovementDatetimemaxDate(final OffenderExternalMovements paramBean) {
		final String sql = getQuery("OIDADMIS_VALIDATE_MOVEMENT_DATETIME_MAX_DATE");
		final RowMapper<OffenderExternalMovements> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderExternalMovements.class, offenderExternalMovementsMapping);
		final Object returnObject = namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
		return returnObject;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * oidadmisValidateMovementDatetimemaxDateTime
	 *
	 * @param params
	 *
	 */
	public OffenderExternalMovements validateMovementDatetimemaxDateTime(final OffenderExternalMovements paramBean) {
		final String sql = getQuery("OIDADMIS_VALIDATE_MOVEMENT_DATETIME_MAX_DATE_TIME");
		final RowMapper<OffenderExternalMovements> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderExternalMovements.class, offenderExternalMovementsMapping);
		OffenderExternalMovements returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(),
				columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * oidadmisValidateMovementDatemaxBookId
	 *
	 * @param params
	 *
	 */
	public Long validateMovementDatemaxBookId(final OffenderBookings paramBean) {
		final String sql = getQuery("OIDADMIS_VALIDATE_MOVEMENT_DATE_MAX_BOOK_ID");
		final String returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams("ROOTOFFENDERID" ,paramBean.getRootOffenderId() ), String.class);
		return new Long(returnObj);
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * oidadmisValidateMovementDatemaxDate
	 *
	 * @param params
	 *
	 */
	public OffenderExternalMovements validateMovementDatemaxDate(final Integer offenderBookId) {
		final String sql = getQuery("OIDADMIS_VALIDATE_MOVEMENT_DATE_MAX_DATE");
		final RowMapper<OffenderExternalMovements> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderExternalMovements.class, offenderExternalMovementsMapping);
		final OffenderExternalMovements returnObj = namedParameterJdbcTemplate.queryForObject(sql,
				createParams("OFFENDER_BOOK_ID", offenderBookId), columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * oidadmisGetLivingUnitIdgetLivUnitId
	 *
	 * @param params
	 *
	 */
	public CaseloadAdmOtherProfiles getLivingUnitIdgetLivUnitId(final CaseloadAdmOtherProfiles paramBean) {
		final String sql = getQuery("OIDADMIS_GET_LIVING_UNIT_ID_GET_LIV_UNIT_ID");
		final RowMapper<CaseloadAdmOtherProfiles> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				CaseloadAdmOtherProfiles.class, caseloadAdmOtherProfilesMapping);
		CaseloadAdmOtherProfiles returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(),
				columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * oidadmisDeactivateActiveOffExmRecsetLockCur
	 *
	 * @param params
	 *
	 */
	public OffenderExternalMovements deactivateActiveOffExmRecsetLockCur(final OffenderExternalMovements paramBean) {
		final String sql = getQuery("OIDADMIS_DEACTIVATE_ACTIVE_OFF_EXM_REC_SET_LOCK_CUR");
		final RowMapper<OffenderExternalMovements> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderExternalMovements.class, offenderExternalMovementsMapping);
		OffenderExternalMovements returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(),
				columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * oidadmisCreateUpdateOffBillingProfgetAgyProfInfoCur
	 *
	 * @param params
	 *
	 */
	public AgencyBillingProfiles createUpdateOffBillingProfgetAgyProfInfoCur(final AgencyBillingProfiles paramBean) {
		final String sql = getQuery("OIDADMIS_CREATE_UPDATE_OFF_BILLING_PROF_GET_AGY_PROF_INFO_CUR");
		final RowMapper<AgencyBillingProfiles> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				AgencyBillingProfiles.class, agencyBillingProfilesMapping);
		AgencyBillingProfiles returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(),
				columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * oidadmisCreateUpdateOffBillingProf
	 *
	 * @param params
	 *
	 */
	public AgencyBillingProfiles createUpdateOffBillingProf(final AgencyBillingProfiles paramBean) {
		final String sql = getQuery("OIDADMIS_CREATE_UPDATE_OFF_BILLING_PROF");
		final RowMapper<AgencyBillingProfiles> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				AgencyBillingProfiles.class, agencyBillingProfilesMapping);
		AgencyBillingProfiles returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(),
				columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * oidadmisWorkflowDownFormcountCur
	 *
	 * @param params
	 *
	 */
	public WorkflowScreens workflowDownFormcountCur(final WorkflowScreens paramBean) {
		final String sql = getQuery("OIDADMIS_WORKFLOW_DOWN_FORM_COUNT_CUR");
		final RowMapper<WorkflowScreens> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, WorkflowScreens.class,
				workflowScreensMapping);
		final WorkflowScreens returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(),
				columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * oidadmisWorkflowDownFormseqNoCur
	 *
	 * @param params
	 *
	 */
	public WorkflowScreens workflowDownFormseqNoCur(final WorkflowScreens paramBean) {
		final String sql = getQuery("OIDADMIS_WORKFLOW_DOWN_FORM_SEQ_NO_CUR");
		final RowMapper<WorkflowScreens> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, WorkflowScreens.class,
				workflowScreensMapping);
		final WorkflowScreens returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(),
				columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * oidadmisWorkflowDownFormcallFormCur
	 *
	 * @param params
	 *
	 */
	public List<String> workflowDownFormcallFormCur(WorkflowScreens paramBean) {
		final String sql = getQuery("OIDADMIS_WORKFLOW_DOWN_FORM_CALL_FORM_CUR");
		final List<String> returnObj = namedParameterJdbcTemplate.queryForList(sql, createParams(), String.class);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * oidadmisChkAssignedLochouUnTypeCur
	 *
	 * @param params
	 *
	 */
	public List<LivingUnits> chkAssignedLochouUnTypeCur(final LivingUnits paramBean) {
		final String sql = getQuery("OIDADMIS_CHK_ASSIGNED_LOC_HOU_UN_TYPE_CUR");
		final RowMapper<LivingUnits> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, LivingUnits.class,
				livingUnitsMapping);
		List<LivingUnits> returnList = new ArrayList<>();
		returnList = namedParameterJdbcTemplate.query(sql, createParams(), columnRowMapper);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * oidadmisCheckBedAhBlkNavcasAgyCur
	 *
	 * @param params
	 *
	 */
	public String checkBedAhBlkNavcasAgyCur(final CaseloadAgencyLocations paramBean) {
		final String sql = getQuery("OIDADMIS_CHECK_BED_AH_BLK_NAV_CAS_AGY_CUR");
		final String returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), String.class);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * oidadmisValidateLivingUnitsvsLcdCur
	 *
	 * @param params
	 *
	 */
	public List<LivingUnits> validateLivingUnitsvsLcdCur(final LivingUnits paramBean) {
		final String sql = getQuery("OIDADMIS_VALIDATE_LIVING_UNITS_VS_LCD_CUR");
		final RowMapper<LivingUnits> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, LivingUnits.class,
				livingUnitsMapping);
		List<LivingUnits> returnList = new ArrayList<>();
		returnList = namedParameterJdbcTemplate.query(sql, createParams(), columnRowMapper);
		return returnList;
	}

	@Override
	public Integer offBookingUpdateOffenderExternalMovements(final VHeaderBlock2 vblock) {

		final String sql = getQuery("OIDADMIS_OFFEM_UPDATE_OFFENDER_BOOKINGS");
		Integer returnArray = null;
		returnArray = namedParameterJdbcTemplate.update(sql, new BeanPropertySqlParameterSource(vblock));
		return returnArray;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * rgReferenceCodesStatus
	 *
	 *
	 */
	@Override
	public List<ReferenceCodes> rgReferenceCodesStatus() {
		final String sql = getQuery("OIDADMIS_REFERENCECODES_STATUS_DESC");
		final RowMapper<ReferenceCodes> referenceCodeRowMapper = Row2BeanRowMapper.makeMapping(sql,
				ReferenceCodes.class, referenceCodesMapping);
		List<ReferenceCodes> returnList = new ArrayList<>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams(), referenceCodeRowMapper);
		} catch (Exception e) {
			logger.error("rgReferenceCodesStatus: ", e);
		}
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * oidadmisCgfkchkOffBkgsOffBkgStafc
	 *
	 * @param params
	 *
	 */
	public List<ReferenceCodes> getSaffmembersDescription() {
		final String sql = getQuery("OIDADMIS_STAFFMEMBERS_DESC");
		final RowMapper<ReferenceCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				staffMembersMapping);
		List<ReferenceCodes> returnList = new ArrayList<>();
		returnList = namedParameterJdbcTemplate.query(sql, createParams(), columnRowMapper);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * oidadmisOffEmPreInsertc
	 *
	 * @param params
	 *
	 */
	public BedAssignmentHistories bedAhPreInsertc(final BedAssignmentHistories paramBean) {
		final String sql = getQuery("OIDADMIS_BED_AH_PREINSERT_C");
		final BedAssignmentHistories returnObj = new BedAssignmentHistories();
		final Integer obj = namedParameterJdbcTemplate.queryForObject(sql,
				createParams("offenderBookId", paramBean.getOffenderBookId()), Integer.class);
		if (returnObj != null) {
			returnObj.setBedAssignSeq(obj);
		}
		return returnObj;
	}

	@Override
	public Integer getEscSeq(final OffenderBookings offenderBookId) {

		final String sql = getQuery("OIDADMIS_GET_ESCAPE_SEQ");
		Integer returnArray = null;
		try {
			returnArray = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("offenderBookId", offenderBookId.getOffenderBookId()), Integer.class);
		} catch (Exception e) {
			logger.error("getEscSeq", e);
		}
		return returnArray;
	}

	public Integer updateOffenderEscapes(final OffenderEscape offBook) {
		final String sql = getQuery("UPDATE_ESC_OFFENDERS");
		Integer returnArray = null;
		try {
			returnArray = namedParameterJdbcTemplate.update(sql, new BeanPropertySqlParameterSource(offBook));
		} catch (Exception e) {
			logger.error("updateOffenderEscapes", e);
		}
		return returnArray;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @param systemMode
	 * 
	 * @return List<Caseloads>
	 */
	public List<Caseloads> caseloadIdValue(final BigDecimal offenderId, String userName) {
		final String sql = getQuery("OIDADMIS_CASELOAD_ID");
		final RowMapper<Caseloads> referenceCodeRowMapper = Row2BeanRowMapper.makeMapping(sql, Caseloads.class,
				caseloadAdmAlertProfilesMapping);
		List<Caseloads> returnList = new ArrayList<>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql,
					createParams("offenderId", offenderId, "USERID", userName), referenceCodeRowMapper);
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * oidadmisCgfkchkOffBkgsOffBkgStafc
	 *
	 * @param params
	 *
	 */
	public StaffMembers cgfkchkOffBkgsOffBkgStafId() {
		final String sql = getQuery("OIDADMIS_CGFKCHK_OFF_BKGS_OFF_BKG_STAF_ID");
		final RowMapper<StaffMembers> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, StaffMembers.class,
				staffMembersMapping);
		StaffMembers returnList = new StaffMembers();
		returnList = namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * oidadmisgetnewbookingno
	 *
	 * @param params
	 *
	 */
	public String oidadmisgetnewbookingno() {
		final String sql = getQuery("OIDADMIS_GET_NEW_BOOKING_NO");
		String returnList = new String();
		returnList = namedParameterJdbcTemplate.queryForObject(sql, createParams(), String.class);
		return returnList;
	}

	/**
	 * Fetch the records from procedure
	 *
	 * @param agyLocId
	 *
	 * @return OffenderBookings
	 */
	public OffenderBookings dafaultLocationData(final String agyLocId) {
		Map<String, Object> returnObject = null;
		final OffenderBookings bean = new OffenderBookings();
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[20];
		sqlParameters = new SqlParameter[] { new SqlInOutParameter("P_CASELOAD_ID", OracleTypes.VARCHAR),
				new SqlInOutParameter("P_AGY_LOC_ID", OracleTypes.VARCHAR),
				new SqlInOutParameter("P_LIVING_UNIT_ID", OracleTypes.NUMBER)

		};
		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("OIDADMIS").withProcedureName("GET_DEFAULTS").declareParameters(sqlParameters);
		inParamMap.put("P_CASELOAD_ID", agyLocId);
		inParamMap.put("P_AGY_LOC_ID", null);
		inParamMap.put("P_LIVING_UNIT_ID", null);
		final SqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		try {
			returnObject = (Map<String, Object>) simpleJDBCCall.withoutProcedureColumnMetaDataAccess()
					.execute(inParameter);
			returnObject.forEach((k, v) -> {
				if ("P_AGY_LOC_ID".equals(k)) {
					if (v != null) {
						bean.setAgyLocId(v.toString());
					} else {
						bean.setAgyLocId(null);
					}
				}
				if ("P_LIVING_UNIT_ID".equals(k)) {
					if (v != null) {
						bean.setLivingUnitId(new BigDecimal(v.toString()));
					} else {
						bean.setLivingUnitId(null);
					}
				}
				if ("P_CASELOAD_ID".equals(k)) {
					if (v != null) {
						bean.setCreateAgyLocId(v.toString());
					} else {
						bean.setCreateAgyLocId(null);
					}
				}
			});
		} catch (Exception e) {
			logger.error("", e);
		}
		return bean;
	}

	@Override
	public String getAdmissionType(VHeaderBlock2 searchBean) {
		Map<String, Object> returnObject = null;
		String returnObj = null;
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[5];
		sqlParameters = new SqlParameter[] { new SqlParameter("P_OFFENDER_BOOK_ID", OracleTypes.NUMBER),
				new SqlParameter("P_BOOKING_TYPE", OracleTypes.VARCHAR),
				new SqlOutParameter("P_ADMISSION_TYPE", OracleTypes.VARCHAR),
				new SqlOutParameter("P_ADMISSION_REASON", OracleTypes.VARCHAR) };

		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("OIDADMIS").withProcedureName("GET_ADMISSION_TYPE").declareParameters(sqlParameters);
		inParamMap.put("P_OFFENDER_BOOK_ID", searchBean.getOffenderBookId());
		inParamMap.put("P_BOOKING_TYPE", searchBean.getBookingType());
		final SqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		try {
			returnObject = simpleJDBCCall.withoutProcedureColumnMetaDataAccess().execute(inParameter);
			return (returnObject.get("P_ADMISSION_TYPE") != null ? returnObject.get("P_ADMISSION_TYPE").toString()
					: null);
		} catch (Exception e) {
			return returnObj;
		}
	}

	@Override
	public Integer getPoffAge(OffenderExternalMovements bean) {
		final String sql = getQuery("OIDADMIS_GET_P_OFF_AGE");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("BIRTH_DATE", bean.getBirthDate()),
				Integer.class);
	}

	@Override
	public Integer checkYoungOff(String caseloadId, Integer pOffAge) {
		Map<String, Object> returnObject = null;
		final Map<String, Object> inParamMap = new HashMap<>();
		SqlParameter[] sqlParameters = new SqlParameter[3];
		sqlParameters = new SqlParameter[] { new SqlParameter("P_CASELOAD_ID", Types.VARCHAR),
				new SqlParameter("P_OFFENDER_AGE", Types.NUMERIC), new SqlOutParameter("RETURN", Types.NUMERIC) };
		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("OIDADMIS").withFunctionName("CHECK_YOUNG_OFF").declareParameters(sqlParameters);
		inParamMap.put("P_CASELOAD_ID", caseloadId);
		inParamMap.put("P_OFFENDER_AGE", pOffAge);
		final MapSqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		try {
			returnObject = simpleJDBCCall.execute(inParameter);
			return (Integer) returnObject.get("RETURN");
		} catch (Exception e) {
			return 0;
		}
	}

	@Override
	public OffenderBookings getConflictEvent(OffenderExternalMovements bean) {
		Map<String, Object> returnObject = null;
		OffenderBookings bkgBean = new OffenderBookings();
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[5];
		sqlParameters = new SqlParameter[] { new SqlParameter("P_OFFENDER_BOOK_ID", OracleTypes.NUMBER),
				new SqlParameter("P_OFFENDER_ID", OracleTypes.NUMBER),
				new SqlParameter("P_LIVING_UNIT_ID", OracleTypes.NUMBER),
				new SqlOutParameter("P_WARNING_MSG", OracleTypes.VARCHAR),
				new SqlOutParameter("P_WARNING_PROMPT", OracleTypes.VARCHAR) };

		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("OMUAVBED").withProcedureName("get_conflict_warning_no_cs")
				.declareParameters(sqlParameters);
		inParamMap.put("P_OFFENDER_BOOK_ID", bean.getOffenderBookId());
		inParamMap.put("P_OFFENDER_ID", bean.getOffenderId());
		inParamMap.put("P_LIVING_UNIT_ID", Integer.valueOf(bean.getLivingUnitId()));
		final SqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		try {
			returnObject = simpleJDBCCall.withoutProcedureColumnMetaDataAccess().execute(inParameter);
			bkgBean.setWarningMsg(
					returnObject.get("P_WARNING_MSG") != null ? returnObject.get("P_WARNING_MSG").toString() : null);
			bkgBean.setWarningPrompt(
					returnObject.get("P_WARNING_PROMPT") != null ? returnObject.get("P_WARNING_PROMPT").toString()
							: null);
			return bkgBean;
		} catch (Exception e) {
			return bkgBean;
		}
	}

//trust
	public String chkOffenderDeductions(final OffenderTransactions searchBean) {
		Map<String, Object> returnObject = null;
		String returnObj = null;
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[5];
		sqlParameters = new SqlParameter[] { new SqlParameter("p_csld_id", OracleTypes.VARCHAR),
				new SqlParameter("p_off_id", OracleTypes.NUMBER), new SqlParameter("p_trans_type", OracleTypes.VARCHAR),
				new SqlParameter("p_shadow_id", OracleTypes.NUMBER),
				new SqlOutParameter("p_ded_flag", OracleTypes.VARCHAR) };

		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("DEDUCTIONS").withProcedureName("CHK_OFFENDER_DEDUCTIONS")
				.declareParameters(sqlParameters);
		inParamMap.put("p_csld_id", searchBean.getCaseloadId());
		inParamMap.put("p_off_id", searchBean.getRootOffenderId());
		inParamMap.put("p_trans_type", "AD");
		inParamMap.put("p_shadow_id", null);
		final SqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		try {
			returnObject = simpleJDBCCall.execute(inParameter);
			returnObj = (String) returnObject.get("p_ded_flag");
		} catch (Exception e) {
			return returnObj;
		}
		return returnObj;
	}

	public String chkTrustFlag(final String caseloadId) {
		Map<String, Object> returnObject = null;
		String returnObj = null;
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[5];
		sqlParameters = new SqlParameter[] { new SqlParameter("p_caseload_id", OracleTypes.VARCHAR) };

		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("OIDADMIS").withFunctionName("GET_TRUST_FLAG").declareParameters(sqlParameters);
		inParamMap.put("p_caseload_id", caseloadId);
		final SqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		try {
			returnObject = simpleJDBCCall.execute(inParameter);
			returnObj = (String) returnObject.get("return");
		} catch (Exception e) {
			return returnObj;
		}
		return returnObj;
	}

	@Override
	public String ChkAccountStatus(String caseLoadId, String offenderId) {
		Map<String, Object> returnObject = null;
		String returnObj = null;
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[5];
		sqlParameters = new SqlParameter[] { new SqlParameter("p_csld_id", OracleTypes.VARCHAR),
				new SqlParameter("p_offender_id", OracleTypes.VARCHAR),
				new SqlOutParameter("p_open_an_account", OracleTypes.VARCHAR) };

		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("TRUST").withProcedureName("CHK_ACCOUNT_STATUS").declareParameters(sqlParameters);
		inParamMap.put("p_csld_id", caseLoadId);
		inParamMap.put("p_offender_id", offenderId);
		final SqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		try {
			returnObject = simpleJDBCCall.execute(inParameter);
			returnObj = (String) returnObject.get("p_open_an_account");
		} catch (Exception e) {
			logger.error("ChkAccountStatus: ", e);
			return returnObj;
		}
		return returnObj;
	}

	@Override
	public TransactionOperation drAccountCodeCrAccountCode(String caseLoadId) {
		final String sql = getQuery("OTDCNTAC_DR_ACCOUNT_CODE_CR_ACCOUNT_CODE");
		final RowMapper<TransactionOperation> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				TransactionOperation.class, offenderTransactionsMapping);
		TransactionOperation returnList = null;
		try {
			returnList = namedParameterJdbcTemplate.queryForObject(sql, createParams("caseLoadId", caseLoadId),
					columnRowMapper);
		} catch (Exception e) {
			logger.error("drAccountCodeCrAccountCode: ", e);
			return returnList;
		}
		return returnList;
	}

	@Override
	public BedAssignmentHistories getOffenderBedDetails(BigDecimal offenderBookId) {
		final String sql = getQuery("OFFENDER_BED_ASSIGNMENT_HISTORIES");
		final RowMapper<BedAssignmentHistories> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				BedAssignmentHistories.class, bedAssignmentHistoriesMapping);
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("offenderBookId", offenderBookId),
				columnRowMapper);
	}

	@Override
	public Integer getStaffId(String userId) {
		String sql = getQuery("OIDADMIS_GET_STAFF_ID");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("userId", userId), Integer.class);
	}

	@Override
	public String getSystemGeneratedUser() {
		String sql = getQuery("OIDADMIS_SYSPFL_FIND_SYSTEM_PROFILES_GET_SYSTEM_GENERATED_USER");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams(), String.class);
	}

	@Override
	public String overridingDetails(BigDecimal offenderId) {
		String sql = getQuery("OIDADMIS_OFFENDER_DETAILS");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql, createParams("offenderId", offenderId), String.class);
		} catch (Exception e) {
			logger.error("Error in overridingDetails");
			return null;
		}
	}
	
	public String getiepCode(String description) {
		String sql = getQuery("OIDADMIS_GET_IEP_CODE");
		String iepCode=null;
		try {
			iepCode = namedParameterJdbcTemplate.queryForObject(sql, createParams("iepLevelDescription", description), String.class);
		} catch(Exception e) {
			logger.error("Error in getiepCode"+e);
			return iepCode;
		}
		return iepCode;
	}
	
	public Date geReleaseDate(Long offenderBookId) {
		String sql = getQuery("OIDCUSTAD_FETCH_RELEASE_DATE");
		Date  date=null;
		try {
			date = namedParameterJdbcTemplate.queryForObject(sql, createParams("offenderBookId", offenderBookId), Date.class);
		} catch(Exception e) {
			logger.error("Error in getiepCode"+e);
		}
		return date;
	}
	
	public Integer updateCustodyAdjustments(Date intakeDate,Long offenderBookId) {
		List<OffenderSentenceAdjustment> adjustemnts=fetchAdjustments(offenderBookId);
		List<OffenderSentenceAdjustment> escapeAdjusment=new ArrayList<>();
		OffenderSentenceAdjustment escape=new OffenderSentenceAdjustment();
		Integer returnValue=0;
		Date releasedate=geReleaseDate(offenderBookId);
		try {
			if(adjustemnts!=null && adjustemnts.size()>0) {
				escapeAdjusment=adjustemnts.stream().filter(adj->adj.getAdjustCode()!=null && adj.getSealFlag()!=null && adj.getAdjustCode().equals("ESCP") && adj.getSealFlag().equals("Y")).collect(Collectors.toList());
			}
			if(escapeAdjusment!=null && escapeAdjusment.size()>0) {
				escape=escapeAdjusment.get(0);
				escape.setAdjustToDate(intakeDate);
				escape.setAdjustDays(null);
				if(escape.getAdjustFromDate()!=null && escape.getAdjustToDate()!=null ) {
					int adjustedEscDays = (int) Math.abs(ChronoUnit.DAYS.between(escape.getAdjustFromDate().toInstant(), escape.getAdjustToDate().toInstant()));
					escape.setAdjustDays(adjustedEscDays != 0 ? adjustedEscDays - 1 : 0);
				}
			}else {
				final String sql = getQuery("OIDADMIS_SAVE_CUSTODY_BOOKINGS");
				OffenderSentenceAdjustment adjustments=new OffenderSentenceAdjustment();
				adjustments.setAdjustCode("ESCP");
				adjustments.setOffenderBookId(offenderBookId);
				adjustments.setObjectId(1l);
				adjustments.setObjectType("BOOKING");
				adjustments.setAdjustDate(new Date());
				adjustments.setAdjustFromDate(releasedate);
				adjustments.setCreateUserId("OMS_OWNER");
				adjustments.setCreateDatetime(new Date());
				adjustments.setSealFlag("Y");
				adjustments.setAdjustToDate(intakeDate);
				if(adjustments.getAdjustFromDate()!=null && adjustments.getAdjustToDate()!=null ) {
					int adjustedEscDays =(int)Math.abs(ChronoUnit.DAYS.between(adjustments.getAdjustFromDate().toInstant(), adjustments.getAdjustToDate().toInstant()));
					adjustments.setAdjustDays(adjustedEscDays != 0 ? adjustedEscDays - 1 : 0);
				}else {
					adjustments.setAdjustDays(0);
				}
				try {
					returnValue=namedParameterJdbcTemplate.update(sql, new BeanPropertySqlParameterSource(adjustments));
				}catch (Exception e) {
					logger.error("Error in updateCustodyAdjustments"+e);
				}
			}
			returnValue=namedParameterJdbcTemplate.update(getQuery("OIDADMIS_CUSTODY_UPDATE_BOOKINGS"), new BeanPropertySqlParameterSource(escape));
		}catch (Exception e) {
			logger.error("Error in updateCustodyAdjustments"+e);
		}
		
		return returnValue;
	}
	
	public List<OffenderSentenceAdjustment> fetchAdjustments(Long offenderBookId) {
		return namedParameterJdbcTemplate.query(getQuery("OIDADMIS_FETCH_BOOKING_DETAILS"),createParams("offenderBookId",offenderBookId),new RowMapperResultSetExtractor<OffenderSentenceAdjustment>(new BeanPropertyRowMapper<OffenderSentenceAdjustment>(OffenderSentenceAdjustment.class)));
	}

	@Override
	public List<String> getAlertMsgForReleaseOffender(VHeaderBlock2 searchBean) {
		String sql = getQuery("OIDADMIS_SHOW_ALERT_FOR_RELEASE_OFFENDER");
		List<String> returnList = new ArrayList<>();

		try {
			returnList = namedParameterJdbcTemplate.queryForList(sql,
					 createParams("offenderId", searchBean.getOffenderId(), "caseloadId" , searchBean.getCaseLoadId()), String.class);
		} catch (Exception e) {
			logger.error("Error in getAlertMsgForReleaseOffender");
		}
		return returnList;
	}
	
}

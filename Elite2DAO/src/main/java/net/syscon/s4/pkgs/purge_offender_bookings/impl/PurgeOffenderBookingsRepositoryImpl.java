package net.syscon.s4.pkgs.purge_offender_bookings.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.RowMapperResultSetExtractor;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.cm.programsservices.OffenderCourseApptGrp;
import net.syscon.s4.common.beans.Addresses;
import net.syscon.s4.common.beans.Images;
import net.syscon.s4.common.beans.OffenderAssocPrtyContact;
import net.syscon.s4.common.beans.OffenderBookings;
import net.syscon.s4.common.beans.OffenderClothingIssue;
import net.syscon.s4.common.beans.OffenderCurfews;
import net.syscon.s4.common.beans.OffenderDrugAdmission;
import net.syscon.s4.common.beans.OffenderDrugSample;
import net.syscon.s4.common.beans.OffenderExternalMovements;
import net.syscon.s4.common.beans.OffenderHwd;
import net.syscon.s4.common.beans.OffenderOicSanctions;
import net.syscon.s4.common.beans.Orders;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.AgyIncInvestigations;
import net.syscon.s4.im.beans.CasePlans;
import net.syscon.s4.im.beans.CourseActivities;
import net.syscon.s4.im.beans.OffenderCaseNotes;
import net.syscon.s4.im.beans.OffenderCases;
import net.syscon.s4.im.beans.OffenderGrievances;
import net.syscon.s4.im.beans.OffenderPrgObligations;
import net.syscon.s4.im.beans.OffenderProgramProfiles;
import net.syscon.s4.im.beans.OffenderStgAffiliations;
import net.syscon.s4.im.beans.ReleasePlans;
import net.syscon.s4.im.incidentsoic.beans.AgencyIncidentCharges;
import net.syscon.s4.im.incidentsoic.beans.AgencyIncidentParties;
import net.syscon.s4.im.incidentsoic.beans.OicHearingResults;
import net.syscon.s4.im.incidentsoic.beans.OicHearings;
import net.syscon.s4.inst.booking.beans.OffenderContactPersons;
import net.syscon.s4.inst.casemanagement.beans.OffenderCaseConditions;
import net.syscon.s4.inst.casemanagement.beans.OffenderCriminogenicNeeds;
import net.syscon.s4.inst.casemanagement.beans.PlanDetails;
import net.syscon.s4.inst.casemanagement.beans.WorkFlows;
import net.syscon.s4.inst.classification.beans.AssessmentResults;
import net.syscon.s4.inst.incidentsoic.maintenance.OffenderOicAppealIncidents;
import net.syscon.s4.inst.institutionalactivities.maintenance.beans.CourseSchedules;
import net.syscon.s4.inst.legals.beans.OffenderProceedings;
import net.syscon.s4.inst.legals.beans.OffenderSentences;
import net.syscon.s4.inst.legalscreens.maintenance.bean.OffenderCharges;
import net.syscon.s4.inst.property.bean.OffenderPptyItemTxns;
import net.syscon.s4.inst.schedules.bean.CourtEvents;
import net.syscon.s4.inst.schedules.bean.OffenderIndSchedules;
import net.syscon.s4.inst.schedules.bean.VOffExtMovements;
import net.syscon.s4.inst.visitsmanagement.beans.VOffenderVisits;
import net.syscon.s4.pkgs.OffenderAssociatedParties;
import net.syscon.s4.pkgs.purge_offender_bookings.PurgeOffenderBookingsRepository;
import net.syscon.s4.triggers.HdcRequestReferrals;
import net.syscon.s4.triggers.OffenderCourseAttendances;
import net.syscon.s4.triggers.OffenderOasysSections;
import net.syscon.s4.triggers.OffenderPtr;
import net.syscon.s4.triggers.QmActivitiesIns;

@Repository
public class PurgeOffenderBookingsRepositoryImpl extends RepositoryBase implements PurgeOffenderBookingsRepository {
	private final Map<String, FieldMapper> courtListMapping = new ImmutableMap.Builder<String, FieldMapper>().build();
	private static Logger logger = LogManager.getLogger(PurgeOffenderBookingsRepositoryImpl.class.getName());
	
	@Override
	public List<AgencyIncidentParties> prAgencyIncidentParties(Integer pOffenderBookId) {
		final String sql=getQuery("PURGE_GET_AGENCY_INCIDENT_PARTIES");
		RowMapper<AgencyIncidentParties> mapper=Row2BeanRowMapper.makeMapping(sql, AgencyIncidentParties.class, courtListMapping);
		return namedParameterJdbcTemplate.query(sql, createParams("p_offender_book_id",pOffenderBookId),mapper);
	}
	
	@Override
	public List<OffenderBookings> getOffenderBookId(Long pOffenderBookId) {
		final String sql=getQuery("PURGE_GET_OFFENDER_BOOK_ID");
		//RowMapper<BigDecimal> mapper=Row2BeanRowMapper.makeMapping(sql, BigDecimal.class, courtListMapping);
		return namedParameterJdbcTemplate.query(sql, createParams("p_offender_book_id",pOffenderBookId),new BeanPropertyRowMapper<OffenderBookings>(OffenderBookings.class));
	}
	
	@Override
	public List<AgencyIncidentCharges> getAgencyIncidentCharges(Integer pAgencyIncidentId, Integer partySeq) {
		final String sql=getQuery("PURGE_GET_AGENCY_INCIDENT_CHARGES");
		RowMapper<AgencyIncidentCharges> mapper=Row2BeanRowMapper.makeMapping(sql,AgencyIncidentCharges.class, courtListMapping);
		return namedParameterJdbcTemplate.query(sql, createParams("p_agency_incident_id",pAgencyIncidentId,"p_party_seq",partySeq),mapper);
	}
	
	@Override
	public List<OicHearingResults> prOicHearingResults1(BigDecimal pOicHearingId) {
		final String sql=getQuery("PURGE_GET_OIC_HEARING_RESULTS");
		RowMapper<OicHearingResults> mapper=Row2BeanRowMapper.makeMapping(sql, OicHearingResults.class, courtListMapping);
		return namedParameterJdbcTemplate.query(sql, createParams("p_oic_hearing_id",pOicHearingId),mapper);
	}
	
	@Override
	public List<OffenderOicAppealIncidents> prOffenderOicAppelIncidnts(Integer oicHearingId, Integer resultSeq) {
		final String sql=getQuery("PURGE_GET_OFFENDER_OIC_APPEAL_INCIDENTS");
		RowMapper<OffenderOicAppealIncidents> mapper=Row2BeanRowMapper.makeMapping(sql, OffenderOicAppealIncidents.class, courtListMapping);
		return namedParameterJdbcTemplate.query(sql, createParams("p_oic_hearing_id",oicHearingId,"p_result_seq",resultSeq),mapper);
	}
	
	@Override
	public Integer prOffenderOicAppealPenltis(Integer oicApprealId, Integer oicHearingId, Integer resultSeq, String modifyUserId) {
		final String sql=getQuery("PURGE_DELETE_OFFENDER_OIC_APPEAL_PENALTIES");
		try {
			String tableName = "offender_oic_appeal_penalties";
			String whereClause = "oic_appreal_id = :p_oic_appreal_id AND oic_hearing_id = :p_oic_hearing_id AND result_seq = :p_result_seq";
			Map<String , Object> inputMap = new HashMap<String, Object>();
			inputMap.put("p_oic_appreal_id", oicApprealId);
			inputMap.put("p_oic_hearing_id", oicHearingId);
			inputMap.put("p_result_seq", resultSeq);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereClause , inputMap);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method prOffenderOicAppealPenltis", e);
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_oic_appreal_id",oicApprealId,"p_oic_hearing_id",oicHearingId,"p_result_seq",resultSeq));
	}
	
	public Integer deleteOffenderOicAppealIncidents(Integer oicHearingId, Integer resultSeq, String modifyUserId) {
		final String sql=getQuery("PURGE_DELETE_OFFENDER_OIC_APPEAL_INCIDENTS");
		try {
			String tableName = "offender_oic_appeal_incidents";
			String whereClause = "oic_hearing_id = :p_oic_hearing_id AND result_seq = :p_result_seq";
			Map<String , Object> inputMap = new HashMap<String, Object>();
			inputMap.put("p_oic_hearing_id", oicHearingId);
			inputMap.put("p_result_seq", resultSeq);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereClause , inputMap);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method deleteOffenderOicAppealIncidents", e);
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_oic_hearing_id",oicHearingId,"p_result_seq",resultSeq));
	}
	
	@Override
	public List<OffenderOicSanctions> prOffenderOicSanctions1(Integer oicHearingId, Integer resultSeq) {
		final String sql=getQuery("PURGE_GET_OFFENDER_OIC_SANCTIONS");
		RowMapper<OffenderOicSanctions> mapper=Row2BeanRowMapper.makeMapping(sql, OffenderOicSanctions.class, courtListMapping);
		return namedParameterJdbcTemplate.query(sql, createParams("p_oic_hearing_id",oicHearingId,"p_result_seq",resultSeq),mapper);
	}
	
	@Override
	public List<OffenderOicSanctions> prOffenderOicSanctions2(Integer consecutiveOffenderBookId,Integer consecutiveSanctionSeq) {
		final String sql=getQuery("PURGE_GET_OFFENDER_OIC_SANCTIONS1");
		RowMapper<OffenderOicSanctions> mapper=Row2BeanRowMapper.makeMapping(sql, OffenderOicSanctions.class, courtListMapping);
		return namedParameterJdbcTemplate.query(sql, createParams("p_consecutiv_offndr_book_id",consecutiveOffenderBookId,"p_consecutive_sanction_seq",consecutiveSanctionSeq),mapper);

	}
	
	@Override
	public Integer prOffenderOicSancReviews(BigDecimal pOffenderBookId, BigDecimal pSanctionSeq, String modifyUserId) {
		final String sql=getQuery("PURGE_DELETE_OFFENDER_OIC_SANC_REVIEWS");
		try {
			String tableName = "offender_oic_sanc_reviews";
			String whereClause = "offender_book_id = :p_offender_book_id AND sanction_seq = :p_sanction_seq";
			Map<String , Object> inputMap = new HashMap<String, Object>();
			inputMap.put("p_offender_book_id", pOffenderBookId);
			inputMap.put("p_sanction_seq", pSanctionSeq);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereClause , inputMap);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method prOffenderOicSancReviews", e);
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_book_id",pOffenderBookId,"p_sanction_seq",pSanctionSeq));
	}
	
	@Override
	public Integer deleteOffenderOicSanctions(Integer oicHearingId, Integer resultSeq, String modifyUserId) {
		final String sql=getQuery("PURGE_DELETE_OFFENDER_OIC_SANCTIONS");
		try {
			String tableName = "offender_oic_sanctions";
			String whereClause = "oic_hearing_id = :p_oic_hearing_id AND result_seq = :p_result_seq";
			Map<String , Object> inputMap = new HashMap<String, Object>();
			inputMap.put("p_oic_hearing_id", oicHearingId);
			inputMap.put("p_result_seq", resultSeq);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereClause , inputMap);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method deleteOffenderOicSanctions", e);
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_oic_hearing_id",oicHearingId,"p_result_seq",resultSeq));
	}
	
	@Override
	public Integer deleteOicHearingResults(BigDecimal pOicHearingId, String modifyUserId) {
		final String sql=getQuery("PURGE_DELETE_OIC_HEARING_RESULTS");
		try {
			String tableName = "oic_hearing_results";
			String whereClause = "oic_hearing_id = :p_oic_hearing_id";
			Map<String , Object> inputMap = new HashMap<String, Object>();
			inputMap.put("p_oic_hearing_id", pOicHearingId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereClause , inputMap);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method deleteOicHearingResults", e);
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_oic_hearing_id",pOicHearingId));
	}
	
	
	@Override
	public List<AgyIncInvestigations> prAgyIncInvestigations(Integer pAgencyIncidentId, Integer partySeq) {
		final String sql=getQuery("PURGE_GET_AGY_INC_INVESTIGATIONS");
		RowMapper<AgyIncInvestigations> mapper=Row2BeanRowMapper.makeMapping(sql, AgyIncInvestigations.class, courtListMapping);
		return namedParameterJdbcTemplate.query(sql, createParams("p_agency_incident_id",pAgencyIncidentId,"p_party_seq",partySeq),mapper);

	}
	
	
	@Override
	public List<OicHearingResults> prOicHearingResults(BigDecimal agencyIncidentId, BigDecimal chargeSeq) {
		final String sql=getQuery("PURGE_GET_OIC_HEARING_RESULTS1");
		RowMapper<OicHearingResults> mapper=Row2BeanRowMapper.makeMapping(sql, OicHearingResults.class, courtListMapping);
		return namedParameterJdbcTemplate.query(sql, createParams("p_agency_incident_id",agencyIncidentId,"p_charge_seq",chargeSeq),mapper);
	}
	
	@Override
	public Integer deleteprOicHearingResults(BigDecimal agencyIncidentId, BigDecimal chargeSeq, String modifyUserId) {
		final String sql=getQuery("PURGE_DELETE_OIC_HEARING_RESULTS1");
		try {
			String tableName = "oic_hearing_results";
			String whereClause = "agency_incident_id = :p_agency_incident_id AND charge_seq = :p_charge_seq";
			Map<String , Object> inputMap = new HashMap<String, Object>();
			inputMap.put("p_agency_incident_id", agencyIncidentId);
			inputMap.put("p_charge_seq", chargeSeq);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereClause , inputMap);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method deleteprOicHearingResults", e);
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_agency_incident_id",agencyIncidentId,"p_charge_seq",chargeSeq));
	}
	
	@Override
	public Integer deleteprAgencyIncidentCharges(Integer pAgencyIncidentId, Integer partySeq, String modifyUserId) {
		final String sql=getQuery("PURGE_DELETE_AGENCY_INCIDENT_CHARGES");
		try {
			String tableName = "agency_incident_charges";
			String whereClause = "agency_incident_id = :p_agency_incident_id AND party_seq = :p_party_seq";
			Map<String , Object> inputMap = new HashMap<String, Object>();
			inputMap.put("p_agency_incident_id", pAgencyIncidentId);
			inputMap.put("p_party_seq", partySeq);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereClause , inputMap);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method deleteprAgencyIncidentCharges", e);
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_agency_incident_id",pAgencyIncidentId,"p_party_seq",partySeq));
	}
	
	@Override
	public Integer deleteprAgyIncInvStatements(Integer agyIncInvestigationId, String modifyUserId) {
		final String sql=getQuery("PURGE_DELETE_AGY_INC_INV_STATEMENTS");
		try {
			String tableName = "agy_inc_inv_statements";
			String whereClause = "agy_inc_investigation_id = :p_agy_inc_investigation_id";
			Map<String , Object> inputMap = new HashMap<String, Object>();
			inputMap.put("p_agy_inc_investigation_id", agyIncInvestigationId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereClause , inputMap);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method deleteprAgyIncInvStatements", e);
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_agy_inc_investigation_id",agyIncInvestigationId));
	}
	
	@Override
	public Integer deleteprAgyIncInvestigations(Integer pAgencyIncidentId, Integer partySeq, String modifyUserId) {
		final String sql=getQuery("PURGE_DELETE_AGY_INC_INVESTIGATIONS");
		try {
			String tableName = "agy_inc_investigations";
			String whereClause = "agency_incident_id = :p_agency_incident_id AND party_seq = :p_party_seq";
			Map<String , Object> inputMap = new HashMap<String, Object>();
			inputMap.put("p_agency_incident_id", pAgencyIncidentId);
			inputMap.put("p_party_seq", partySeq);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereClause , inputMap);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method deleteprAgyIncInvestigations", e);
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_agency_incident_id",pAgencyIncidentId,"p_party_seq",partySeq));
	}
	
	@Override
	public List<OffenderGrievances> prOffenderGrievances1(Long agencyIncidentId, Long partySeq) {
		final String sql=getQuery("PURGE_GET_OFFENDER_GRIEVANCES");
		RowMapper<OffenderGrievances> mapper=Row2BeanRowMapper.makeMapping(sql, OffenderGrievances.class, courtListMapping);
		return namedParameterJdbcTemplate.query(sql, createParams("p_agency_incident_id",agencyIncidentId,"p_party_seq",partySeq),mapper);
	}
	
	@Override
	public Integer deleteprOffenderGrievanceTxns(Long grievanceId, String modifyUserId) {
		final String sql=getQuery("PURGE_DELETE_OFFENDER_GRIEVANCE_TXNS");
		try {
			String tableName = "offender_grievance_txns";
			String whereClause = "grievance_id = :p_grievance_id";
			Map<String , Object> inputMap = new HashMap<String, Object>();
			inputMap.put("p_grievance_id", grievanceId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereClause , inputMap);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method deleteprOffenderGrievanceTxns", e);
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_grievance_id",grievanceId));
	}
	
	@Override
	public Integer deleteprOffenderGrievStaffs(Long grievanceId, String modifyUserId) {
		final String sql=getQuery("PURGE_DELETE_OFFENDER_GRIEV_STAFFS");
		try {
			String tableName = "offender_griev_staffs";
			String whereClause = "grievance_id = :p_grievance_id";
			Map<String , Object> inputMap = new HashMap<String, Object>();
			inputMap.put("p_grievance_id", grievanceId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereClause , inputMap);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method deleteprOffenderGrievStaffs", e);
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_grievance_id",grievanceId));
	}
	@Override
	public Integer deleteprOffenderGrievances1(Long agencyIncidentId, Long partySeq, String modifyUserId) {
		final String sql=getQuery("PURGE_DELETE_OFFENDER_GRIEVANCES1");
		try {
			String tableName = "offender_grievances";
			String whereClause = "agency_incident_id = :p_agency_incident_id AND party_seq = :p_party_seq";
			Map<String , Object> inputMap = new HashMap<String, Object>();
			inputMap.put("p_agency_incident_id", agencyIncidentId);
			inputMap.put("p_party_seq", partySeq);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereClause , inputMap);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method deleteprOffenderGrievances1", e);
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_agency_incident_id",agencyIncidentId,"p_party_seq",partySeq));
	}
	
	@Override
	public Integer deleteprOffenderIncidentDetails(BigDecimal pAgencyIncidentId, BigDecimal pPartySeq, String modifyUserId) {
		final String sql=getQuery("PURGE_DELETE_OFFENDER_INCIDENT_DETAILS");
		try {
			String tableName = "offender_incident_details";
			String whereClause = "agency_incident_id = :p_agency_incident_id AND party_seq = :p_party_seq";
			Map<String , Object> inputMap = new HashMap<String, Object>();
			inputMap.put("p_agency_incident_id", pAgencyIncidentId);
			inputMap.put("p_party_seq", pPartySeq);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereClause , inputMap);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method deleteprOffenderIncidentDetails", e);
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_agency_incident_id",pAgencyIncidentId,"p_party_seq",pPartySeq));
	}
	
	@Override
	public List<OicHearings> prOicHearings(Integer oicIncidentId) {
		final String sql=getQuery("PURGE_GET_OIC_HEARINGS");
		RowMapper<OicHearings> mapper=Row2BeanRowMapper.makeMapping(sql, OicHearings.class, courtListMapping);
		return namedParameterJdbcTemplate.query(sql, createParams("p_oic_incident_id",oicIncidentId),mapper);
	}
	
	@Override
	public Integer deleteprOicHearingComments(BigDecimal pOicHearingId, String modifyUserId) {
		final String sql=getQuery("PURGE_DELETE_OIC_HEARING_COMMENTS");
		try {
			String tableName = "oic_hearing_comments";
			String whereClause = "oic_hearing_id = :p_oic_hearing_id";
			Map<String , Object> inputMap = new HashMap<String, Object>();
			inputMap.put("p_oic_hearing_id", pOicHearingId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereClause , inputMap);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method deleteprOicHearingComments", e);
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_oic_hearing_id",pOicHearingId));
	}
	
	@Override
	public Integer deleteprOicHearingNotices(Integer oicHearingId, String modifyUserId) {
		final String sql=getQuery("PURGE_DELETE_OIC_HEARING_NOTICES");
		try {
			String tableName = "oic_hearing_notices";
			String whereClause = "oic_hearing_id = :p_oic_hearing_id";
			Map<String , Object> inputMap = new HashMap<String, Object>();
			inputMap.put("p_oic_hearing_id", oicHearingId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereClause , inputMap);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method deleteprOicHearingNotices", e);
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_oic_hearing_id",oicHearingId));
	}
	@Override
	public Integer deleteprOicHearingReviews(BigDecimal oicHearingId, String modifyUserId) {
		final String sql=getQuery("PURGE_DELETE_OIC_HEARING_REVIEWS");
		try {
			String tableName = "oic_hearing_reviews";
			String whereClause = "oic_hearing_id = :p_oic_hearing_id";
			Map<String , Object> inputMap = new HashMap<String, Object>();
			inputMap.put("p_oic_hearing_id", oicHearingId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereClause , inputMap);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method deleteprOicHearingReviews", e);
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_oic_hearing_id",oicHearingId));
	}
	
	@Override
	public Integer deleteprAgencyIncidentParties(Integer pOffenderBookId, String modifyUserId) {
		final String sql=getQuery("PURGE_DELETE_AGENCY_INCIDENT_PARTIES");
		try {
			String tableName = "agency_incident_parties";
			String whereClause = "offender_book_id = :p_offender_book_id";
			Map<String , Object> inputMap = new HashMap<String, Object>();
			inputMap.put("p_offender_book_id", pOffenderBookId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereClause , inputMap);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method deleteprAgencyIncidentParties", e);
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_book_id",pOffenderBookId));
	}
	
	@Override
	public Integer deleteprArrests(Long offenderBookId, String modifyUserId) {
		final String sql=getQuery("PURGE_DELETE_ARRESTS");
		try {
			String tableName = "arrests";
			String whereClause = "offender_book_id = :p_offender_book_id";
			Map<String , Object> inputMap = new HashMap<String, Object>();
			inputMap.put("p_offender_book_id", offenderBookId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereClause , inputMap);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method deleteprArrests", e);
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_book_id",offenderBookId));
	}
	
	@Override
	public Integer deleteprAssignments(Long offenderBookId, String modifyUserId) {
		final String sql=getQuery("PURGE_DELETE_ASSIGNMENTS");
		try {
			String tableName = "assignments";
			String whereClause = "offender_book_id = :p_offender_book_id";
			Map<String , Object> inputMap = new HashMap<String, Object>();
			inputMap.put("p_offender_book_id", offenderBookId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereClause , inputMap);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method deleteprAssignments", e);
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_book_id",offenderBookId));
	}
	
	@Override
	public Integer deleteprBedAssignmentHistories(Long offenderBookId, String modifyUserId) {
		final String sql=getQuery("PURGE_DELETE_BED_ASSIGNMENT_HISTORIES");
		try {
			String tableName = "bed_assignment_histories";
			String whereClause = "offender_book_id = :p_offender_book_id";
			Map<String , Object> inputMap = new HashMap<String, Object>();
			inputMap.put("p_offender_book_id", offenderBookId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereClause , inputMap);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method deleteprBedAssignmentHistories", e);
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_book_id",offenderBookId));
	}
	
	@Override
	public Integer deleteprCaseAssociatedPersons(Long offenderBookId, String modifyUserId) {
		final String sql=getQuery("PURGE_DELETE_CASE_ASSOCIATED_PERSONS_PURGE");
		try {
			String tableName = "case_associated_persons";
			String whereClause = "offender_book_id = :p_offender_book_id";
			Map<String , Object> inputMap = new HashMap<String, Object>();
			inputMap.put("p_offender_book_id", offenderBookId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereClause , inputMap);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method deleteprCaseAssociatedPersons", e);
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_book_id",offenderBookId));
	}
	
	@Override
	public Integer deleteprCaseNotes(Long offenderBookId, String modifyUserId) {
		final String sql=getQuery("PURGE_DELETE_CASE_NOTES");
		try {
			String tableName = "case_notes";
			String whereClause = "offender_book_id = :p_offender_book_id";
			Map<String , Object> inputMap = new HashMap<String, Object>();
			inputMap.put("p_offender_book_id", offenderBookId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereClause , inputMap);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method deleteprCaseNotes", e);
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_book_id",offenderBookId));
	}
	
	@Override
	public List<CasePlans> prCasePlans(Long offenderBookId) {
		final String sql=getQuery("PURGE_GET_CASE_PLANS_PURGE");
		RowMapper<CasePlans> mapper=Row2BeanRowMapper.makeMapping(sql, CasePlans.class, courtListMapping);
		return namedParameterJdbcTemplate.query(sql, createParams("p_offender_book_id",offenderBookId),mapper);
	}
	
	@Override
	public Integer deleteprAssessmentSummaries(Long offenderBookId, Long casePlanId, String modifyUserId) {
		final String sql=getQuery("PURGE_DELETE_ASSESSMENT_SUMMARIES");
		try {
			String tableName = "assessment_summaries";
			String whereClause = "offender_book_id = :p_offender_book_id AND case_plan_id = :p_case_plan_id";
			Map<String , Object> inputMap = new HashMap<String, Object>();
			inputMap.put("p_offender_book_id", offenderBookId);
			inputMap.put("p_case_plan_id", casePlanId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereClause , inputMap);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method deleteprAssessmentSummaries", e);
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_book_id",offenderBookId,"p_case_plan_id",casePlanId));
	}
	
	@Override
	public List<OffenderCaseConditions> prOffenderCaseConditions(Long offenderBookId, Long casePlanId) {
		final String sql=getQuery("PURGE_OFFENDER_CASE_CONDITIONS");
		RowMapper<OffenderCaseConditions> mapper=Row2BeanRowMapper.makeMapping(sql, OffenderCaseConditions.class, courtListMapping);
		return namedParameterJdbcTemplate.query(sql, createParams("p_offender_book_id",offenderBookId,"p_case_plan_id",casePlanId),mapper);
	}
	
	@Override
	public Integer deleteprOffenderActionPlans(BigDecimal offCaseCondId, String modifyUserId) {
		final String sql=getQuery("PURGE_DELETE_OFFENDER_ACTION_PLANS");
		try {
			String tableName = "offender_action_plans";
			String whereClause = "off_case_cond_id = :p_off_case_cond_id";
			Map<String , Object> inputMap = new HashMap<String, Object>();
			inputMap.put("p_off_case_cond_id", offCaseCondId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereClause , inputMap);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method deleteprOffenderActionPlans", e);
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_off_crim_need_id",offCaseCondId));
	}
	
	@Override
	public Integer deleteprOffenderCaseConditions(Long offenderBookId, Long casePlanId, String modifyUserId) {
		final String sql=getQuery("PURGE_DELETE_OFFENDER_CASE_CONDITIONS");
		try {
			String tableName = "offender_case_conditions";
			String whereClause = "offender_book_id = :p_offender_book_id AND case_plan_id = :p_case_plan_id";
			Map<String , Object> inputMap = new HashMap<String, Object>();
			inputMap.put("p_offender_book_id", offenderBookId);
			inputMap.put("p_case_plan_id", casePlanId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereClause , inputMap);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method deleteprOffenderCaseConditions", e);
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_book_id",offenderBookId,"p_case_plan_id",casePlanId));
	}
	
	@Override
	public List<OffenderCriminogenicNeeds> prOffenderCriminogenicNeeds(BigDecimal offenderBookId,
			BigDecimal casePlanId) {
		final String sql=getQuery("PURGE_GET_OFFENDER_CRIMINOGENIC_NEEDS");
		RowMapper<OffenderCriminogenicNeeds> mapper=Row2BeanRowMapper.makeMapping(sql, OffenderCriminogenicNeeds.class, courtListMapping);
		return namedParameterJdbcTemplate.query(sql, createParams("p_offender_book_id",offenderBookId,"p_case_plan_id",casePlanId),mapper);
	}
	
	@Override
	public Integer deleteprOffenderCriminogenicNeeds(BigDecimal offenderBookId, BigDecimal casePlanId, String modifyUserId) {
		final String sql=getQuery("PURGE_DELETE_OFFENDER_CRIMINOGENIC_NEEDS");
		try {
			String tableName = "offender_criminogenic_needs";
			String whereClause = "offender_book_id = :p_offender_book_id AND case_plan_id = :p_case_plan_id";
			Map<String , Object> inputMap = new HashMap<String, Object>();
			inputMap.put("p_offender_book_id", offenderBookId);
			inputMap.put("p_case_plan_id", casePlanId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereClause , inputMap);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method deleteprOffenderCriminogenicNeeds", e);
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_book_id",offenderBookId,"p_case_plan_id",casePlanId));
	}
	
	@Override
	public List<PlanDetails> prPlanDetails(Long offenderBookId, Long casePlanId) {
		final String sql=getQuery("PURGE_GET_PLAN_DETAILS");
		RowMapper<PlanDetails> mapper=Row2BeanRowMapper.makeMapping(sql, PlanDetails.class, courtListMapping);
		return namedParameterJdbcTemplate.query(sql, createParams("p_offender_book_id",offenderBookId,"p_case_plan_id",casePlanId),mapper);
	}
	
	@Override
	public Integer deleteprCaseworkSteps(Long offenderBookId, Long casePlanId, Long detailSeq, String modifyUserId) {
		final String sql=getQuery("PURGE_DELETE_CASEWORK_STEPS");
		try {
			String tableName = "casework_steps";
			String whereClause = "offender_book_id = :p_offender_book_id AND case_plan_id = :p_case_plan_id AND detail_seq = :p_detail_seq";
			Map<String , Object> inputMap = new HashMap<String, Object>();
			inputMap.put("p_offender_book_id", offenderBookId);
			inputMap.put("p_case_plan_id", casePlanId);
			inputMap.put("p_detail_seq", detailSeq);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereClause , inputMap);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method deleteprCaseworkSteps", e);
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_book_id",offenderBookId,"p_case_plan_id",casePlanId,"p_detail_seq",detailSeq));
	}
	
	@Override
	public Integer deleteprPlanDetails(Long offenderBookId, Long casePlanId, String modifyUserId) {
		final String sql=getQuery("PURGE_DELETE_PLAN_DETAILS");
		try {
			String tableName = "plan_details";
			String whereClause = "offender_book_id = :p_offender_book_id AND case_plan_id = :p_case_plan_id";
			Map<String , Object> inputMap = new HashMap<String, Object>();
			inputMap.put("p_offender_book_id", offenderBookId);
			inputMap.put("p_case_plan_id", casePlanId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereClause , inputMap);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method deleteprPlanDetails", e);
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_book_id",offenderBookId,"p_case_plan_id",casePlanId));
	}
	
	@Override
	public Integer deleteprCasePlans(Long offenderBookId, String modifyUserId) {
		final String sql=getQuery("PURGE_DELETE_CASE_PLANS");
		try {
			String tableName = "case_plans";
			String whereClause = "offender_book_id = :p_offender_book_id";
			Map<String , Object> inputMap = new HashMap<String, Object>();
			inputMap.put("p_offender_book_id", offenderBookId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereClause , inputMap);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method deleteprPlanDetails", e);
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_book_id",offenderBookId));
	}
	
	@Override
	public List<CourtEvents> prCourtEvents(Integer offenderBookId) {
		final String sql=getQuery("PURGE_GET_COURT_EVENTS_PURGE");
		///RowMapper<BigDecimal> mapper=Row2BeanRowMapper.makeMapping(sql, BigDecimal.class, courtListMapping);
		return namedParameterJdbcTemplate.query(sql, createParams("p_offender_book_id",offenderBookId),new RowMapperResultSetExtractor<CourtEvents>(new BeanPropertyRowMapper<CourtEvents>(CourtEvents.class)) );
	}
	
	@Override
	public Integer deleteprCourtEventCharges(BigDecimal eventId, String modifyUserId) {
		final String sql=getQuery("PURGE_DELETE_COURT_EVENT_CHARGES");
		try {
			String tableName = "court_event_charges";
			String whereClause = "event_id = :p_event_id";
			Map<String , Object> inputMap = new HashMap<String, Object>();
			inputMap.put("p_event_id", eventId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereClause , inputMap);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method deleteprCourtEventCharges", e);
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_event_id",eventId));
	}
	
	@Override
	public Integer deleteprCourtEventSentences(BigDecimal eventId, String modifyUserId) {
		final String sql=getQuery("PURGE_DELETE_COURT_EVENT_SENTENCES1");
		try {
			String tableName = "court_event_sentences";
			String whereClause = "event_id = :p_event_id";
			Map<String , Object> inputMap = new HashMap<String, Object>();
			inputMap.put("p_event_id", eventId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereClause , inputMap);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method deleteprCourtEventSentences", e);
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_event_id",eventId));
	}
	
	@Override
	public Integer deleteprLinkCaseTxns(BigDecimal eventId, String modifyUserId) {
		final String sql=getQuery("PURGE_DELETE_LINK_CASE_TXNS");
		try {
			String tableName = "link_case_txns";
			String whereClause = "case_id = :p_case_id";
			Map<String , Object> inputMap = new HashMap<String, Object>();
			inputMap.put("p_case_id", eventId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereClause , inputMap);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method deleteprLinkCaseTxns", e);
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_case_id",eventId));
	}
	
	@Override
	public Integer deleteprCourtEvents(Integer offenderBookId, String modifyUserId) {
		final String sql=getQuery("PURGE_DELETE_COURT_EVENTS");
		try {
			String tableName = "court_events";
			String whereClause = "offender_book_id = :p_offender_book_id";
			Map<String , Object> inputMap = new HashMap<String, Object>();
			inputMap.put("p_offender_book_id", offenderBookId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereClause , inputMap);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method deleteprCourtEvents", e);
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_book_id",offenderBookId));
	}
	
	@Override
	public Integer deleteprDocumentTemplateQueues(BigDecimal offenderBookId, String modifyUserId) {
		final String sql=getQuery("PURGE_DELETE_DOCUMENT_TEMPLATE_QUEUES");
		try {
			String tableName = "document_template_queues";
			String whereClause = "offender_book_id = :p_offender_book_id";
			Map<String , Object> inputMap = new HashMap<String, Object>();
			inputMap.put("p_offender_book_id", offenderBookId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereClause , inputMap);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method deleteprDocumentTemplateQueues", e);
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_book_id",offenderBookId));
	}
	
	@Override
	public Integer updateprGlTransactions(BigDecimal offenderBookId) {
		final String sql=getQuery("PURGE_UPDATE_GL_TRANSACTIONS_PURGE");
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_book_id",offenderBookId));
	}
	
	@Override
	public List<HdcRequestReferrals> prHdcRequestReferrals(Long offenderBookId) {
		final String sql=getQuery("PURGE_GET_HDC_REQUEST_REFERRALS_PURGE");
		return namedParameterJdbcTemplate.query(sql, createParams("p_offender_book_id",offenderBookId),new BeanPropertyRowMapper<HdcRequestReferrals>(HdcRequestReferrals.class));
	}
	
	@Override
	public Integer deleteprHdcBoardDecisions(BigDecimal pHdcRequestReferralId, String modifyUserId) {
		final String sql=getQuery("PURGE_DELETE_HDC_BOARD_DECISIONS");
		try {
			String tableName = "hdc_board_decisions";
			String whereClause = "hdc_request_referral_id = :p_hdc_request_referral_id";
			Map<String , Object> inputMap = new HashMap<String, Object>();
			inputMap.put("p_hdc_request_referral_id", pHdcRequestReferralId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereClause , inputMap);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method deleteprHdcBoardDecisions", e);
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_hdc_request_referral_id",pHdcRequestReferralId));
	}
	
	@Override
	public Integer deleteprHdcGovernorDecisions(BigDecimal pHdcRequestReferralId, String modifyUserId) {
		final String sql=getQuery("PURGE_DELETE_HDC_GOVERNOR_DECISIONS");
		try {
			String tableName = "hdc_governor_decisions";
			String whereClause = "hdc_request_referral_id = :p_hdc_request_referral_id";
			Map<String , Object> inputMap = new HashMap<String, Object>();
			inputMap.put("p_hdc_request_referral_id", pHdcRequestReferralId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereClause , inputMap);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method deleteprHdcGovernorDecisions", e);
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_hdc_request_referral_id",pHdcRequestReferralId));
	}
	
	@Override
	public Integer deleteprHdcProbStaffComments(BigDecimal pHdcRequestReferralId, String modifyUserId) {
		final String sql=getQuery("PURGE_DELETE_HDC_PROB_STAFF_COMMENTS");
		try {
			String tableName = "hdc_prob_staff_comments";
			String whereClause = "hdc_request_referral_id = p_hdc_request_referral_id";
			Map<String , Object> inputMap = new HashMap<String, Object>();
			inputMap.put("p_hdc_request_referral_id", pHdcRequestReferralId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereClause , inputMap);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method deleteprHdcProbStaffComments", e);
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_hdc_request_referral_id",pHdcRequestReferralId));
	}
	
	@Override
	public Integer deleteprHdcProbStaffResponses(BigDecimal pHdcRequestReferralId, String modifyUserId) {
		final String sql=getQuery("PURGE_DELETE_HDC_PROB_STAFF_RESPONSES");
		try {
			String tableName = "hdc_prob_staff_responses";
			String whereClause = "hdc_request_referral_id = :p_hdc_request_referral_id";
			Map<String , Object> inputMap = new HashMap<String, Object>();
			inputMap.put("p_hdc_request_referral_id", pHdcRequestReferralId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereClause , inputMap);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method deleteprHdcProbStaffResponses", e);
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_hdc_request_referral_id",pHdcRequestReferralId));
	}
	
	@Override
	public List<HdcRequestReferrals> prHdcRequestReferrals1(Long parentHdcRequestReferralId) {
		final String sql=getQuery("PURGE_GET_HDC_REQUEST_REFERRALS1");
	//	RowMapper<BigDecimal> mapper=Row2BeanRowMapper.makeMapping(sql, BigDecimal.class, courtListMapping);
		return namedParameterJdbcTemplate.query(sql, createParams("p_parent_hdc_reqst_rfrrl_id",parentHdcRequestReferralId),new BeanPropertyRowMapper<HdcRequestReferrals>(HdcRequestReferrals.class));
	}
	
	@Override
	public Integer deleteprHdcRequestReferrals1(Long parentHdcRequestReferralId, String modifyUserId) {
		final String sql=getQuery("PURGE_DELETE_HDC_REQUEST_REFERRALS");
		try {
			String tableName = "hdc_request_referrals";
			String whereClause = "parent_hdc_request_referral_id = :p_parent_hdc_reqst_rfrrl_id";
			Map<String , Object> inputMap = new HashMap<String, Object>();
			inputMap.put("p_parent_hdc_reqst_rfrrl_id", parentHdcRequestReferralId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereClause , inputMap);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method deleteprHdcRequestReferrals1", e);
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_parent_hdc_reqst_rfrrl_id",parentHdcRequestReferralId));
	}
	
	@Override
	public Integer deleteprHdcRequestReferrals(Long offenderBookId, String modifyUserId) {
		final String sql=getQuery("PURGE_DELETE_HDC_REQUEST_REFERRALS1");
		try {
			String tableName = "hdc_request_referrals";
			String whereClause = "offender_book_id = :p_offender_book_id";
			Map<String , Object> inputMap = new HashMap<String, Object>();
			inputMap.put("p_offender_book_id", offenderBookId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereClause , inputMap);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method deleteprHdcRequestReferrals", e);
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_book_id",offenderBookId));
	}
	
	@Override
	public Integer deleteprIncidentCaseParties(BigDecimal offenderBookId, String modifyUserId) {
		final String sql=getQuery("PURGE_DELETE_INCIDENT_CASE_PARTIES");
		try {
			String tableName = "incident_case_parties";
			String whereClause = "offender_book_id = :p_offender_book_id";
			Map<String , Object> inputMap = new HashMap<String, Object>();
			inputMap.put("p_offender_book_id", offenderBookId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereClause , inputMap);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method deleteprIncidentCaseParties", e);
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_book_id",offenderBookId));
	}
	
	@Override
	public Integer deleteprIwpDocuments(BigDecimal offenderBookId, String modifyUserId) {
		final String sql=getQuery("PURGE_DELETE_IWP_DOCUMENTS");
		try {
			String tableName = "iwp_documents";
			String whereClause = "offender_book_id = :p_offender_book_id";
			Map<String , Object> inputMap = new HashMap<String, Object>();
			inputMap.put("p_offender_book_id", offenderBookId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereClause , inputMap);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method deleteprIwpDocuments", e);
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_book_id",offenderBookId));
	}
	
	@Override
	public Integer deleteprOffenderActivityEvents(BigDecimal offenderBookId, String modifyUserId) {
		final String sql=getQuery("PURGE_DELETE_OFFENDER_ACTIVITY_EVENTS");
		try {
			String tableName = "offender_activity_events";
			String whereClause = "offender_book_id = :p_offender_book_id";
			Map<String , Object> inputMap = new HashMap<String, Object>();
			inputMap.put("p_offender_book_id", offenderBookId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereClause , inputMap);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method deleteprOffenderActivityEvents", e);
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_book_id",offenderBookId));
	}
	
	@Override
	public Integer deleteprOffenderAlerts(Long offenderBookId, String modifyUserId) {
		final String sql=getQuery("PURGE_DELETE_OFFENDER_ALERTS_PURGE");
		try {
			String tableName = "offender_alerts";
			String whereClause = "offender_book_id = :p_offender_book_id";
			Map<String , Object> inputMap = new HashMap<String, Object>();
			inputMap.put("p_offender_book_id", offenderBookId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereClause , inputMap);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method deleteprOffenderAlerts", e);
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_book_id",offenderBookId));
	}
	
	@Override
	public Integer deleteprOffenderAssessments(Long offenderBookId, String modifyUserId) {
		String sql = getQuery("PURGE_BOOKING_DELETE_OFFENDER_ASSESSMENTS");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "offender_assessments";
			String whereCondition = "offender_book_id = :p_offender_book_id";
			inputMap.put("p_offender_book_id", offenderBookId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprOffenderAssessments " + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_book_id", offenderBookId));
	}
	
	
	
	@Override
	public Integer deleteprOffenderAssets(Long offenderBookId, String modifyUserId) {
		final String sql=getQuery("PURGE_DELETE_OFFENDER_ASSETS");
		try {
			String tableName = "offender_assets";
			String whereClause = "offender_book_id = :p_offender_book_id";
			Map<String , Object> inputMap = new HashMap<String, Object>();
			inputMap.put("p_offender_book_id", offenderBookId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereClause , inputMap);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method deleteprOffenderAssessments", e);
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_book_id",offenderBookId));
	}
	
	@Override
	public List<OffenderAssociatedParties> prOffenderAssociatedParties(Long offenderBookId) {
		final String sql=getQuery("PURGE_GET_OFFENDER_ASSOCIATED_PARTIES");
		return namedParameterJdbcTemplate.query(sql, createParams("p_offender_book_id",offenderBookId),new BeanPropertyRowMapper<OffenderAssociatedParties>(OffenderAssociatedParties.class));
	}
	
	@Override
	public Integer deleteprOffenderAssocPartyNotes(Long associatedPartyId, String modifyUserId) {
		final String sql=getQuery("PURGE_DELETE_OFFENDER_ASSOC_PARTY_NOTES");
		try {
			String tableName = "offender_assoc_party_notes";
			String whereClause = "associated_party_id = :p_associated_party_id";
			Map<String , Object> inputMap = new HashMap<String, Object>();
			inputMap.put("p_associated_party_id", associatedPartyId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereClause , inputMap);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method deleteprOffenderAssocPartyNotes", e);
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_associated_party_id",associatedPartyId));
	}
	
	@Override
	public List<OffenderAssocPrtyContact> prOffenderAssocPrtyContcts(Long associatedPartyId) {
		final String sql=getQuery("PURGE_GET_OFFENDER_ASSOC_PRTY_CONTACTS");
		RowMapper<OffenderAssocPrtyContact> mapper=Row2BeanRowMapper.makeMapping(sql, OffenderAssocPrtyContact.class, courtListMapping);
		return namedParameterJdbcTemplate.query(sql, createParams("p_offender_book_id",associatedPartyId),mapper);
	}
	
	@Override
	public Integer deleteprOffenderAssocPCntNotifs(Long associatedPartyId, Long partySeq, String modifyUserId) {
		final String sql=getQuery("PURGE_DELETE_OFFENDER_ASSOC_P_CNT_NOTIFS");
		try {
			String tableName = "offender_assoc_p_cnt_notifs";
			String whereClause = "associated_party_id = :p_associated_party_id AND party_seq = :p_party_seq";
			Map<String , Object> inputMap = new HashMap<String, Object>();
			inputMap.put("p_associated_party_id", associatedPartyId);
			inputMap.put("p_party_seq", partySeq);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereClause , inputMap);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method deleteprOffenderAssocPCntNotifs", e);
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_associated_party_id",associatedPartyId,"p_party_seq",partySeq));
	}
	
	@Override
	public Integer deleteprOffenderAssocPrtyContcts(Long associatedPartyId, String modifyUserId) {
		final String sql=getQuery("PURGE_DELETE_OFFENDER_ASSOC_PRTY_CONTACTS");
		try {
			String tableName = "offender_assoc_prty_contacts";
			String whereClause = "associated_party_id = :p_associated_party_id";
			Map<String , Object> inputMap = new HashMap<String, Object>();
			inputMap.put("p_associated_party_id", associatedPartyId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereClause , inputMap);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method deleteprOffenderAssocPrtyContcts", e);
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_associated_party_id",associatedPartyId));
	}
	
	@Override
	public Integer deleteprOffenderAssocPNotifs(Long associatedPartyId, String modifyUserId) {
		final String sql=getQuery("PURGE_DELETE_OFFENDER_ASSOC_P_NOTIFS");
		try {
			String tableName = "offender_assoc_p_notifs";
			String whereClause = "associated_party_id = :p_associated_party_id";
			Map<String , Object> inputMap = new HashMap<String, Object>();
			inputMap.put("p_associated_party_id", associatedPartyId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereClause , inputMap);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method deleteprOffenderAssocPNotifs", e);
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_associated_party_id",associatedPartyId));
	}
	
	@Override
	public Integer deleteprOffenderAssociatedParties(Long offenderBookId, String modifyUserId) {
		final String sql=getQuery("PURGE_DELETE_OFFENDER_ASSOCIATED_PARTIES");
		try {
			String tableName = "offender_associated_parties";
			String whereClause = "offender_book_id = :p_offender_book_id";
			Map<String , Object> inputMap = new HashMap<String, Object>();
			inputMap.put("p_offender_book_id", offenderBookId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereClause , inputMap);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method deleteprOffenderAssociatedParties", e);
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_book_id",offenderBookId));
	}
	
	@Override
	public Integer deleteprOffenderAuthorisdVisitors(BigDecimal offenderBookId, String modifyUserId) {
		final String sql=getQuery("PURGE_DELETE_OFFENDER_AUTHORISED_VISITORS");
		try {
			String tableName = "offender_authorised_visitors";
			String whereClause = "offender_book_id = :p_offender_book_id";
			Map<String , Object> inputMap = new HashMap<String, Object>();
			inputMap.put("p_offender_book_id", offenderBookId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereClause , inputMap);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method deleteprOffenderAuthorisdVisitors", e);
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_book_id",offenderBookId));
	}
	
	@Override
	public Integer deleteprOffenderAthorisdVisitors1(BigDecimal offenderBookId, String modifyUserId) {
		final String sql=getQuery("PURGE_DELETE_OFFENDER_AUTHORISED_VISITORS1");
		try {
			String tableName = "offender_authorised_visitors";
			String whereClause = "visitor_offender_book_id = :p_visitor_offender_book_id";
			Map<String , Object> inputMap = new HashMap<String, Object>();
			inputMap.put("p_visitor_offender_book_id", offenderBookId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereClause , inputMap);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method deleteprOffenderAthorisdVisitors1", e);
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_visitor_offender_book_id",offenderBookId));
	}
	
	@Override
	public Integer deleteprOffenderBailDetails(Long offenderBookId, String modifyUserId) {
		final String sql=getQuery("PURGE_DELETE_OFFENDER_BAIL_DETAILS");
		try {
			String tableName = "offender_bail_details";
			String whereClause = "offender_book_id = :p_offender_book_id";
			Map<String , Object> inputMap = new HashMap<String, Object>();
			inputMap.put("p_offender_book_id", offenderBookId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereClause , inputMap);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method deleteprOffenderBailDetails", e);
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_book_id",offenderBookId));
	}
	
	@Override
	public Integer deleteprOffenderBookingAgyLocs(Long offenderBookId, String modifyUserId) {
		final String sql=getQuery("PURGE_DELETE_OFFENDER_BOOKING_AGY_LOCS");
		try {
			String tableName = "offender_booking_agy_locs";
			String whereClause = "offender_book_id = :p_offender_book_id";
			Map<String , Object> inputMap = new HashMap<String, Object>();
			inputMap.put("p_offender_book_id", offenderBookId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereClause , inputMap);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method deleteprOffenderBookingAgyLocs", e);
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_book_id",offenderBookId));
	}
	
	@Override
	public Integer deleteprOffenderBookingDetails(Long offenderBookId, String modifyUserId) {
		final String sql=getQuery("PURGE_DELETE_OFFENDER_BOOKING_DETAILS");
		try {
			String tableName = "offender_booking_details";
			String whereClause = "offender_book_id = :p_offender_book_id";
			Map<String , Object> inputMap = new HashMap<String, Object>();
			inputMap.put("p_offender_book_id", offenderBookId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereClause , inputMap);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method deleteprOffenderBookingDetails", e);
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_book_id",offenderBookId));
	}
	
	@Override
	public Integer deleteprOffenderBookingEvents(Long offenderBookId, String modifyUserId) {
		final String sql=getQuery("PURGE_DELETE_OFFENDER_BOOKING_EVENTS");
		try {
			String tableName = "offender_booking_events";
			String whereClause = "offender_book_id = :p_offender_book_id";
			Map<String , Object> inputMap = new HashMap<String, Object>();
			inputMap.put("p_offender_book_id", offenderBookId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereClause , inputMap);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method deleteprOffenderBookingEvents", e);
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_book_id",offenderBookId));
	}
	
	@Override
	public List<OffenderCases> prOffenderCases(Long offenderBookId) {
		final String sql=getQuery("PURGE_GET_OFFENDER_CASES");
		return namedParameterJdbcTemplate.query(sql, createParams("p_offender_book_id",offenderBookId),new BeanPropertyRowMapper<OffenderCases>(OffenderCases.class));		
	}
	
	@Override
	public List<CourtEvents> prCourtEvents1(BigDecimal eventID) {
		final String sql=getQuery("PURGE_GET_COURT_EVENTS");
		return namedParameterJdbcTemplate.query(sql, createParams("p_case_id",eventID),new BeanPropertyRowMapper<CourtEvents>(CourtEvents.class));	
	}
	
	@Override
	public Integer deleteprCourtEvents1(BigDecimal eventId, String modifyUserId) {
		final String sql=getQuery("PURGE_DELETE_COURT_EVENTS1");
		try {
			String tableName = "court_events";
			String whereClause = "case_id = :p_case_id";
			Map<String , Object> inputMap = new HashMap<String, Object>();
			inputMap.put("p_case_id", eventId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereClause , inputMap);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method deleteprCourtEvents1", e);
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_case_id",eventId));
	}
	
	@Override
	public Integer deleteprLinkCaseTxns1(BigDecimal eventId) {
		final String sql=getQuery("PURGE_DELETE_LINK_CASE_TXNS");
		return namedParameterJdbcTemplate.update(sql, createParams("p_case_id",eventId));
	}
	
	@Override
	public Integer deleteprLinkCaseTxns2(BigDecimal eventId, String modifyUserId) {
		final String sql=getQuery("PURGE_DELETE_LINK_CASE_TXNS1");
		try {
			String tableName = "link_case_txns";
			String whereClause = "combined_case_id = :p_combined_case_id";
			Map<String , Object> inputMap = new HashMap<String, Object>();
			inputMap.put("p_combined_case_id", eventId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereClause , inputMap);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method deleteprLinkCaseTxns2", e);
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_combined_case_id",eventId));
	}
	
	@Override
	public List<OffenderAssociatedParties> prOffenderAssociatedPartis1(BigDecimal eventId) {
		final String sql=getQuery("PURGE_GET_OFFENDER_ASSOCIATED_PARTIES1");
		return namedParameterJdbcTemplate.query(sql, createParams("p_case_id",eventId),new BeanPropertyRowMapper<OffenderAssociatedParties>(OffenderAssociatedParties.class));	
	}
	
	@Override
	public List<OffenderCaseNotes> prOffenderCaseNotes(Integer offenderBookId) {
		final String sql=getQuery("PURGE_GET_OFFENDER_CASE_NOTES");
		return namedParameterJdbcTemplate.query(sql, createParams("p_offender_book_id",offenderBookId),new BeanPropertyRowMapper<OffenderCaseNotes>(OffenderCaseNotes.class));	
	}
	
	@Override
	public Integer deleteprOffenderCaseNoteSents1(Integer caseNoteId, String modifyUserId) {
		final String sql=getQuery("PURGE_DELETE_OFFENDER_CASE_NOTE_SENTS1");
		try {
			String tableName = "offender_case_note_sents";
			String whereClause = "case_note_id = :p_case_note_id";
			Map<String , Object> inputMap = new HashMap<String, Object>();
			inputMap.put("p_case_note_id", caseNoteId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereClause , inputMap);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method deleteprOffenderCaseNoteSents1", e);
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_case_note_id",caseNoteId));
	}
	
	@Override
	public Integer deleteprOffCaseNoteRecipients(Integer caseNoteId, String modifyUserId) {
		final String sql=getQuery("PURGE_DELETE_OFF_CASE_NOTE_RECIPIENTS");
		try {
			String tableName = "off_case_note_recipients";
			String whereClause = "case_note_id = :p_case_note_id";
			Map<String , Object> inputMap = new HashMap<String, Object>();
			inputMap.put("p_case_note_id", caseNoteId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereClause , inputMap);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method deleteprOffCaseNoteRecipients", e);
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_case_note_id",caseNoteId));
	}
	@Override
	public Integer deleteprOffenderCaseNotes(Integer offenderBookId, String modifyUserId) {
		final String sql=getQuery("PURGE_DELETE_OFFENDER_CASE_NOTES");
		try {
			String tableName = "offender_case_notes";
			String whereClause = "offender_book_id = :p_offender_book_id";
			Map<String , Object> inputMap = new HashMap<String, Object>();
			inputMap.put("p_offender_book_id", offenderBookId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereClause , inputMap);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method deleteprOffenderCaseNotes", e);
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_book_id",offenderBookId));
	}
	
	@Override
	public List<OffenderCharges> prOffenderCharges(Long offenderBookId) {
		final String sql=getQuery("PURGE_GET_OFFENDER_CHARGES_PURGE");
		return namedParameterJdbcTemplate.query(sql, createParams("p_offender_book_id",offenderBookId),new BeanPropertyRowMapper<OffenderCharges>(OffenderCharges.class));
	}
	
	@Override
	public Integer deleteprCourtEventCharges1(BigDecimal pOffenderChargeId, String modifyUserId) {
		final String sql=getQuery("PURGE_DELETE_COURT_EVENT_CHARGES1");
		try {
			String tableName = "court_event_charges";
			String whereClause = "offender_charge_id = :p_offender_charge_id";
			Map<String , Object> inputMap = new HashMap<String, Object>();
			inputMap.put("p_offender_charge_id", pOffenderChargeId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereClause , inputMap);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method deleteprCourtEventCharges1", e);
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_charge_id",pOffenderChargeId));
	}
	
	@Override
	public Integer deleteprLinkCaseTxns3(BigDecimal pOffenderChargeId, String modifyUserId) {
		final String sql=getQuery("PURGE_DELETE_LINK_CASE_TXNS2");
		try {
			String tableName = "link_case_txns";
			String whereClause = "offender_charge_id = :p_offender_charge_id";
			Map<String , Object> inputMap = new HashMap<String, Object>();
			inputMap.put("p_offender_charge_id", pOffenderChargeId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereClause , inputMap);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method deleteprLinkCaseTxns3", e);
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_charge_id",pOffenderChargeId));
	}
	
	@Override
	public Integer deleteprOffenderSentenceCharges(BigDecimal pOffenderChargeId, String modifyUserId) {
		final String sql=getQuery("PURGE_DELETE_OFFENDER_SENTENCE_CHARGES");
		try {
			String tableName = "offender_sentence_charges";
			String whereClause = "offender_charge_id = :p_offender_charge_id";
			Map<String , Object> inputMap = new HashMap<String, Object>();
			inputMap.put("p_offender_charge_id", pOffenderChargeId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereClause , inputMap);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method deleteprOffenderSentenceCharges", e);
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_charge_id",pOffenderChargeId));
	}
	
	@Override
	public Integer deleteprOffenderCharges(Long offenderBookId, String modifyUserId) {
		final String sql=getQuery("PURGE_DELETE_OFFENDER_CHARGES_PURGE");
		try {
			String tableName = "offender_charges";
			String whereClause = "offender_book_id = :p_offender_book_id";
			Map<String , Object> inputMap = new HashMap<String, Object>();
			inputMap.put("p_offender_book_id", offenderBookId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereClause , inputMap);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method deleteprOffenderCharges", e);
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_book_id",offenderBookId));
	}
	
	@Override
	public Integer deleteprOffenderCipDetails(Long offenderBookId, String modifyUserId) {
		final String sql=getQuery("PURGE_DELETE_OFFENDER_CIP_DETAILS");
		try {
			String tableName = "offender_cip_details";
			String whereClause = "offender_book_id = :p_offender_book_id";
			Map<String , Object> inputMap = new HashMap<String, Object>();
			inputMap.put("p_offender_book_id", offenderBookId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereClause , inputMap);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method deleteprOffenderCipDetails", e);
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_book_id",offenderBookId));
	}
	
	@Override
	public Integer deleteprOffenderClassPrograms(Long offenderBookId, String modifyUserId) {
		final String sql=getQuery("PURGE_DELETE_OFFENDER_CLASS_PROGRAMS");
		try {
			String tableName = "offender_class_programs";
			String whereClause = "offender_book_id = :p_offender_book_id";
			Map<String , Object> inputMap = new HashMap<String, Object>();
			inputMap.put("p_offender_book_id", offenderBookId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereClause , inputMap);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method deleteprOffenderClassPrograms", e);
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_book_id",offenderBookId));
	}
	
	@Override
	public Integer prOffenderClothes(Long offenderBookId, String modifyUserId) {
		final String sql=getQuery("PURGE_DELETE_OFFENDER_CLOTHES");
		try {
			String tableName = "offender_clothes";
			String whereClause = "offender_book_id = :p_offender_book_id";
			Map<String , Object> inputMap = new HashMap<String, Object>();
			inputMap.put("p_offender_book_id", offenderBookId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereClause , inputMap);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method prOffenderClothes", e);
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_book_id",offenderBookId));
	}
	
	@Override
	public Integer deleteprOffenderClothingBundles(Long offenderBookId, String modifyUserId) {
		final String sql=getQuery("PURGE_DELETE_OFFENDER_CLOTHING_BUNDLES");
		try {
			String tableName = "offender_clothing_bundles";
			String whereClause = "offender_book_id = :p_offender_book_id";
			Map<String , Object> inputMap = new HashMap<String, Object>();
			inputMap.put("p_offender_book_id", offenderBookId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereClause , inputMap);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method deleteprOffenderClothingBundles", e);
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_book_id",offenderBookId));
	}
	
	@Override
	public List<OffenderClothingIssue> prOffenderClothingIssue(Long offenderBookId) {
		final String sql=getQuery("PURGE_GET_OFFENDER_CLOTHING_ISSUE_PURGE");
		return namedParameterJdbcTemplate.query(sql, createParams("p_offender_book_id",offenderBookId),new BeanPropertyRowMapper<OffenderClothingIssue>(OffenderClothingIssue.class));
	}
	
	@Override
	public Integer deleteprOffenderClothingItems1(Long offenderClothingIssueId, String modifyUserId) {
		final String sql=getQuery("PURGE_DELETE_OFFENDER_CLOTHING_ITEMS");
		try {
			String tableName = "offender_clothing_items";
			String whereClause = "offender_clothing_issue_id = :p_offender_clothing_issu_id";
			Map<String , Object> inputMap = new HashMap<String, Object>();
			inputMap.put("p_offender_clothing_issu_id", offenderClothingIssueId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereClause , inputMap);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method deleteprOffenderClothingItems1", e);
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_clothing_issu_id",offenderClothingIssueId));
	}
	
	@Override
	public Integer deleteprOffenderClothingIssue(Long offenderBookId, String modifyUserId) {
		final String sql=getQuery("PURGE_DELETE_OFFENDER_CLOTHING_ISSUE");
		try {
			String tableName = "offender_clothing_issue";
			String whereClause = "offender_book_id = :p_offender_book_id";
			Map<String , Object> inputMap = new HashMap<String, Object>();
			inputMap.put("p_offender_book_id", offenderBookId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereClause , inputMap);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method deleteprOffenderClothingIssue", e);
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_book_id",offenderBookId));
	}
	
	@Override
	public Integer deleteprOffenderClothingItems(Long offenderBookId, String modifyUserId) {
		final String sql=getQuery("PURGE_DELETE_OFFENDER_CLOTHING_ITEMS1");
		try {
			String tableName = "offender_clothing_items";
			String whereClause = "offender_book_id = :p_offender_book_id";
			Map<String , Object> inputMap = new HashMap<String, Object>();
			inputMap.put("p_offender_book_id", offenderBookId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereClause , inputMap);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method deleteprOffenderClothingItems", e);
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_book_id",offenderBookId));
	}
	
	@Override
	public Integer deleteprOffenderCodefendants(Long offenderBookId, String modifyUserId) {
		final String sql=getQuery("PURGE_DELETE_OFFENDER_CODEFENDANTS");
		try {
			String tableName = "offender_codefendants";
			String whereClause = "cod_offender_book_id = :p_cod_offender_book_id";
			Map<String , Object> inputMap = new HashMap<String, Object>();
			inputMap.put("p_cod_offender_book_id", offenderBookId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereClause , inputMap);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method deleteprOffenderCodefendants", e);
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_cod_offender_book_id",offenderBookId));
	}
	
	@Override
	public Integer deleteprOffenderCodefendants1(Long offenderBookId, String modifyUserId) {
		final String sql=getQuery("PURGE_DELETE_OFFENDER_CODEFENDANTS1");
		try {
			String tableName = "offender_codefendants";
			String whereClause = "offender_book_id = :p_offender_book_id";
			Map<String , Object> inputMap = new HashMap<String, Object>();
			inputMap.put("p_offender_book_id", offenderBookId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereClause , inputMap);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method deleteprOffenderCodefendants1", e);
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_book_id",offenderBookId));
	}
	
	@Override
	public Integer deleteprOffenderComments(Long offenderBookId, String modifyUserId) {
		final String sql=getQuery("PURGE_DELETE_OFFENDER_COMMENTS");
		try {
			String tableName = "offender_comments";
			String whereClause = "offender_book_id = :p_offender_book_id";
			Map<String , Object> inputMap = new HashMap<String, Object>();
			inputMap.put("p_offender_book_id", offenderBookId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereClause , inputMap);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method deleteprOffenderComments", e);
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_book_id",offenderBookId));
	}
	
	@Override
	public Integer deleteprOffendrCommnityConditions(Long offenderBookId, String modifyUserId) {
		final String sql=getQuery("PURGE_DELETE_OFFENDER_COMMUNITY_CONDITIONS");
		try {
			String tableName = "offender_community_conditions";
			String whereClause = "offender_book_id = :p_offender_book_id";
			Map<String , Object> inputMap = new HashMap<String, Object>();
			inputMap.put("p_offender_book_id", offenderBookId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereClause , inputMap);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method deleteprOffendrCommnityConditions", e);
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_book_id",offenderBookId));
	}
	
	@Override
	public Integer deleteprOffenderCommSupHistories(Long offenderBookId, String modifyUserId) {
		final String sql=getQuery("PURGE_DELETE_OFFENDER_COMM_SUP_HISTORIES");
		try {
			String tableName = "offender_comm_sup_histories";
			String whereClause = "offender_book_id = :p_offender_book_id";
			Map<String , Object> inputMap = new HashMap<String, Object>();
			inputMap.put("p_offender_book_id", offenderBookId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereClause , inputMap);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method deleteprOffenderCommSupHistories", e);
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_book_id",offenderBookId));
	}
	
	@Override
	public Integer deleteprOffenderCompactAgreements(Long offenderBookId, String modifyUserId) {
		final String sql=getQuery("PURGE_DELETE_OFFENDER_COMPACT_AGREEMENTS");
		try {
			String tableName = "offender_compact_agreements";
			String whereClause = "offender_book_id = :p_offender_book_id";
			Map<String , Object> inputMap = new HashMap<String, Object>();
			inputMap.put("p_offender_book_id", offenderBookId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereClause , inputMap);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method deleteprOffenderCommSupHistories", e);
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_book_id",offenderBookId));
	}
	
	@Override
	public List<OffenderContactPersons> prOffenderContactPersons(BigDecimal offenderBookId) {
		final String sql=getQuery("PURGE_GET_OFFENDER_CONTACT_PERSONS_PURGE");
		return namedParameterJdbcTemplate.query(sql, createParams("p_offender_book_id",offenderBookId),new BeanPropertyRowMapper<OffenderContactPersons>(OffenderContactPersons.class));
	}
	
	@Override
	public Integer deleteprOffenderPersonRestricts(Integer offenderContactPersonId, String modifyUserId) {
		final String sql=getQuery("PURGE_DELETE_OFFENDER_PERSON_RESTRICTS");
		try {
			String tableName = "offender_person_restricts";
			String whereClause = "offender_contact_person_id = :p_offender_contact_prson_id";
			Map<String , Object> inputMap = new HashMap<String, Object>();
			inputMap.put("p_offender_contact_prson_id", offenderContactPersonId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereClause , inputMap);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method deleteprOffenderPersonRestricts", e);
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_contact_prson_id",offenderContactPersonId));
	}
	
	@Override
	public Integer deleteprOffenderContactPersons(BigDecimal offenderBookId, String modifyUserId) {
		final String sql=getQuery("PURGE_DELETE_OFFENDER_CONTACT_PERSONS1");
		try {
			String tableName = "offender_contact_persons";
			String whereClause = "offender_book_id = :p_offender_book_id";
			Map<String , Object> inputMap = new HashMap<String, Object>();
			inputMap.put("p_offender_book_id", offenderBookId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereClause , inputMap);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method deleteprOffenderContactPersons", e);
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_book_id",offenderBookId));
	}
	
	@Override
	public List<OffenderCourseAttendances> prOffenderCourseAttendances(Long offenderBookId) {
		final String sql=getQuery("PURGE_GET_OFFENDER_COURSE_ATTENDANCES_PURGE");
		return namedParameterJdbcTemplate.query(sql, createParams("p_offender_book_id",offenderBookId),new BeanPropertyRowMapper<OffenderCourseAttendances>(OffenderCourseAttendances.class));
	}
	
	@Override
	public Integer deleteprOffenderCourseSkills(Long eventId, String modifyUserId) {
		final String sql=getQuery("PURGE_DELETE_OFFENDER_COURSE_SKILLS");
		try {
			String tableName = "offender_course_skills";
			String whereClause = "event_id = :p_event_id";
			Map<String , Object> inputMap = new HashMap<String, Object>();
			inputMap.put("p_event_id", eventId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereClause , inputMap);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method deleteprOffenderCourseSkills", e);
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_event_id",eventId));
	}
	
	@Override
	public Integer deleteprOffenderCourseAttendances(Long offenderBookId, String modifyUserId) {
		final String sql=getQuery("PURGE_DELETE_OFFENDER_COURSE_ATTENDANCES_PURGE");
		try {
			String tableName = "offender_course_attendances";
			String whereClause = "offender_book_id = :p_offender_book_id";
			Map<String , Object> inputMap = new HashMap<String, Object>();
			inputMap.put("p_offender_book_id", offenderBookId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereClause , inputMap);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method deleteprOffenderCourseAttendances", e);
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_book_id",offenderBookId));
	}
	
	@Override
	public List<OffenderCurfews> prOffenderCurfews(BigDecimal offenderBookId) {
		final String sql=getQuery("PURGE_GET_OFFENDER_CURFEWS");
		return namedParameterJdbcTemplate.query(sql, createParams("p_offender_book_id",offenderBookId),new BeanPropertyRowMapper<OffenderCurfews>(OffenderCurfews.class));
	}
	
	@Override
	public List<OffenderCurfews> prCurfewAddresses(BigDecimal pOffenderCurfewId) {
		final String sql=getQuery("PURGE_GET_CURFEW_ADDRESSES");
		return namedParameterJdbcTemplate.query(sql, createParams("p_offender_curfew_id",pOffenderCurfewId),new BeanPropertyRowMapper<OffenderCurfews>(OffenderCurfews.class));
	}
	
	@Override
	public Integer deleteprCurfewAddressOccupants(BigDecimal pOffenderCurfewId,String modifyUserId) {
		final String sql=getQuery("PURGE_DELETE_CURFEW_ADDRESS_OCCUPANTS");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "curfew_address_occupants";
			String whereCondition = "curfew_address_id = :p_curfew_address_id";
			inputMap.put("p_curfew_address_id", pOffenderCurfewId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprCurfewAddressOccupants " + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_curfew_address_id",pOffenderCurfewId));
	}
	
	@Override
	public Integer deleteprCurfewAddresses(BigDecimal pOffenderCurfewId, String modifyUserId) {
		final String sql=getQuery("PURGE_DELETE_CURFEW_ADDRESSES");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "curfew_addresses";
			String whereCondition = "offender_curfew_id = :p_offender_curfew_id";
			inputMap.put("p_offender_curfew_id", pOffenderCurfewId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprCurfewAddresses " + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_curfew_id",pOffenderCurfewId));
	}
	
	@Override
	public Integer deleteprHdcPrisonStaffComments(BigDecimal pOffenderCurfewId, String modifyUserId) {
		final String sql=getQuery("PURGE_DELETE_HDC_PRISON_STAFF_COMMENTS");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "hdc_prison_staff_comments";
			String whereCondition = "offender_curfew_id = :p_offender_curfew_id";
			inputMap.put("p_offender_curfew_id", pOffenderCurfewId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprHdcPrisonStaffComments " + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_curfew_id",pOffenderCurfewId));
	}
	
	@Override
	public List<HdcRequestReferrals> prHdcRequestReferrals2(BigDecimal pOffenderCurfewId) {
		final String sql=getQuery("PURGE_GET_HDC_REQUEST_REFERRALS2");
		return namedParameterJdbcTemplate.query(sql, createParams("p_offender_curfew_id",pOffenderCurfewId),new BeanPropertyRowMapper<HdcRequestReferrals>(HdcRequestReferrals.class));
	}
	
	@Override
	public Integer deleteprHdcRequestReferrals2(BigDecimal pOffenderCurfewId, String modifyUserId) {
		final String sql=getQuery("PURGE_DELETE_HDC_REQUEST_REFERRALS2");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "hdc_request_referrals";
			String whereCondition = "offender_curfew_id = :p_offender_curfew_id";
			inputMap.put("p_offender_curfew_id", pOffenderCurfewId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprHdcRequestReferrals2 " + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_curfew_id",pOffenderCurfewId));
	}
	
	@Override
	public Integer deleteprOffenderCurfews(BigDecimal offenderBookId, String modifyUserId) {
		final String sql=getQuery("PURGE_DELETE_OFFENDER_CURFEWS");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "offender_curfews";
			String whereCondition = "offender_book_id = :p_offender_book_id";
			inputMap.put("p_offender_book_id", offenderBookId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprOffenderCurfews " + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_book_id",offenderBookId));
	}
	
	@Override
	public List<OffenderDrugAdmission> prOffenderDrugAdmissions(BigDecimal offenderBookId) {
		final String sql=getQuery("PURGE_GET_OFFENDER_DRUG_ADMISSIONS");
		return namedParameterJdbcTemplate.query(sql, createParams("p_offender_book_id",offenderBookId),new BeanPropertyRowMapper<OffenderDrugAdmission>(OffenderDrugAdmission.class));
	}
	
	@Override
	public Integer deleteprOffenderDrugAdmDrugs(Long offenderDrugAdmissionId, String modifyUserId) {
		final String sql=getQuery("PURGE_DELETE_OFFENDER_DRUG_ADM_DRUGS");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "offender_drug_adm_drugs";
			String whereCondition = "offender_drug_admission_id = :p_offender_drug_dmission_id";
			inputMap.put("p_offender_drug_dmission_id", offenderDrugAdmissionId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprOffenderDrugAdmDrugs " + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_drug_dmission_id",offenderDrugAdmissionId));
	}
	
	@Override
	public Integer deleteprOffenderDrugAdmissions(BigDecimal offenderBookId, String modifyUserId) {
		final String sql=getQuery("PURGE_DELETE_OFFENDER_DRUG_ADMISSIONS");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "offender_drug_admissions";
			String whereCondition = "offender_book_id = :p_offender_book_id";
			inputMap.put("p_offender_book_id", offenderBookId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprOffenderDrugAdmissions " + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_book_id",offenderBookId));
	}
	
	@Override
	public List<OffenderDrugSample> prOffenderDrugSamples(BigDecimal offenderBookId) {
		final String sql=getQuery("PURGE_GET_OFFENDER_DRUG_SAMPLES");
		RowMapper<OffenderDrugSample> mapper=Row2BeanRowMapper.makeMapping(sql, OffenderDrugSample.class, courtListMapping);
		return namedParameterJdbcTemplate.query(sql, createParams("p_offender_book_id",offenderBookId),mapper);
	}
	
	@Override
	public Integer deleteprOffenderDrugResults(Long offenderDrugSampleId, String modifyUserId) {
		final String sql=getQuery("PURGE_DELETE_OFFENDER_DRUG_RESULTS");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "offender_drug_results";
			String whereCondition = "offender_drug_sample_id = :p_offender_drug_sample_id";
			inputMap.put("p_offender_drug_sample_id", offenderDrugSampleId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprOffenderDrugResults " + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_drug_sample_id",offenderDrugSampleId));
	}
	
	@Override
	public Integer deleteprOffenderDrugSamples(BigDecimal offenderBookId, String modifyUserId) {
		final String sql=getQuery("PURGE_DELETE_OFFENDER_DRUG_SAMPLES");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "offender_drug_samples";
			String whereCondition = "offender_book_id = :p_offender_book_id";
			inputMap.put("p_offender_book_id", offenderBookId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprOffenderDrugSamples " + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_book_id",offenderBookId));
	}
	
	@Override
	public Integer deleteprOffenderEducations(Long offenderBookId, String modifyUserId) {
		final String sql=getQuery("PURGE_DELETE_OFFENDER_EDUCATIONS");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "offender_educations";
			String whereCondition = "offender_book_id = :p_offender_book_id";
			inputMap.put("p_offender_book_id", offenderBookId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprOffenderEducations " + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_book_id",offenderBookId));
	}
	
	@Override
	public Integer deleteprOffenderEmployments(Long offenderBookId, String modifyUserId) {
		final String sql=getQuery("PURGE_DELETE_OFFENDER_EMPLOYMENTS");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "offender_employments";
			String whereCondition = "offender_book_id = :p_offender_book_id";
			inputMap.put("p_offender_book_id", offenderBookId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprOffenderEmployments " + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_book_id",offenderBookId));
	}
	
	@Override
	public Integer deleteprOffenderEscapes(Integer offenderBookId, String modifyUserId) {
		final String sql=getQuery("PURGE_DELETE_OFFENDER_ESCAPES");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "offender_escapes";
			String whereCondition = "offender_book_id = :p_offender_book_id";
			inputMap.put("p_offender_book_id", offenderBookId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprOffenderEscapes " + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_book_id",offenderBookId));
	}
	
	@Override
	public Integer deleteprOffenderExpenses(BigDecimal offenderBookId, String modifyUserId) {
		final String sql=getQuery("PURGE_DELETE_OFFENDER_EXPENSES");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "offender_expenses";
			String whereCondition = "offender_book_id = :p_offender_book_id";
			inputMap.put("p_offender_book_id", offenderBookId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprOffenderExpenses " + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_book_id",offenderBookId));
	}
	
	@Override
	public Integer deleteprOffenderExternalMovements(Long offenderBookId, String modifyUserId) {
		final String sql=getQuery("PURGE_DELETE_OFFENDER_EXTERNAL_MOVEMENTS");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "offender_external_movements";
			String whereCondition = "offender_book_id = :p_offender_book_id";
			inputMap.put("p_offender_book_id", offenderBookId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprOffenderExternalMovements " + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_book_id",offenderBookId));
	}
	
	@Override
	public Integer deleteprOffenderFileLocations(Long offenderBookId, String modifyUserId) {
		final String sql=getQuery("PURGE_DELETE_OFFENDER_EXTERNAL_MOVEMENTS");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "offender_external_movements";
			String whereCondition = "offender_book_id = :p_offender_book_id";
			inputMap.put("p_offender_book_id", offenderBookId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprOffenderFileLocations " + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_book_id",offenderBookId));
	}
	
	@Override
	public Integer deleteprOffenderGangAffiliations(Long offenderBookId, String modifyUserId) {
		final String sql=getQuery("PURGE_DELETE_OFFENDER_EXTERNAL_MOVEMENTS");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "offender_external_movements";
			String whereCondition = "offender_book_id = :p_offender_book_id";
			inputMap.put("p_offender_book_id", offenderBookId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprOffenderGangAffiliations " + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_book_id",offenderBookId));
	}
	
	@Override
	public Integer deleteprOffenderGangEvidences(Long offenderBookId, String modifyUserId) {
		final String sql=getQuery("PURGE_DELETE_OFFENDER_GANG_EVIDENCES");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "offender_gang_evidences";
			String whereCondition = "offender_book_id = :p_offender_book_id";
			inputMap.put("p_offender_book_id", offenderBookId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprOffenderGangEvidences " + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_book_id",offenderBookId));
	}
	
	@Override
	public Integer deleteprOffenderGangInvests(Long offenderBookId, String modifyUserId) {
		final String sql=getQuery("PURGE_DELETE_OFFENDER_GANG_INVESTS");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "offender_gang_invests";
			String whereCondition = "offender_book_id = :p_offender_book_id";
			inputMap.put("p_offender_book_id", offenderBookId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprOffenderGangInvests " + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_book_id",offenderBookId));
	}
	
	@Override
	public List<OffenderGrievances> prOffenderGrievances(Long offenderBookId) {
		final String sql=getQuery("PURGE_GET_OFFENDER_GRIEVANCES1");
		return namedParameterJdbcTemplate.query(sql, createParams("p_offender_book_id",offenderBookId),new BeanPropertyRowMapper<OffenderGrievances>(OffenderGrievances.class));
	}
	
	@Override
	public Integer deleteprOffenderGrievances(Long offenderBookId, String modifyUserId) {
		final String sql=getQuery("PURGE_DELETE_OFFENDER_GRIEVANCES");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "offender_grievances";
			String whereCondition = "offender_book_id = :p_offender_book_id";
			inputMap.put("p_offender_book_id", offenderBookId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprOffenderGrievances " + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_book_id",offenderBookId));
	}
	
	@Override
	public Integer deleteprOffenderHospitalVisits(Long offenderBookId, String modifyUserId) {
		final String sql=getQuery("PURGE_DELETE_OFFENDER_HOSPITAL_VISITS");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "offender_hospital_visits";
			String whereCondition = "offender_book_id = :p_offender_book_id";
			inputMap.put("p_offender_book_id", offenderBookId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprOffenderHospitalVisits " + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_book_id",offenderBookId));
	}
	
	@Override
	public List<OffenderHwd> prOffenderHwd(Long offenderBookId) {
		final String sql=getQuery("PURGE_GET_OFFENDER_HWD");
		return namedParameterJdbcTemplate.query(sql, createParams("p_offender_book_id",offenderBookId),new BeanPropertyRowMapper<OffenderHwd>(OffenderHwd.class));
	}
	
	@Override
	public Integer deleteprOffenderHwdCharges(Long hwdId, String modifyUserId) {
		final String sql=getQuery("PURGE_DELETE_OFFENDER_HWD_CHARGES");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "offender_hwd_charges";
			String whereCondition = "hwd_id = :p_hwd_id";
			inputMap.put("p_hwd_id", hwdId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprOffenderHwdCharges " + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_hwd_id",hwdId));
	}
	
	@Override
	public Integer deleteprOffenderHwdHty(Long hwdId, String modifyUserId) {
		final String sql=getQuery("PURGE_DELETE_OFFENDER_HWD_HTY");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "offender_hwd_hty";
			String whereCondition = "hwd_id = :p_hwd_id";
			inputMap.put("p_hwd_id", hwdId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprOffenderHwdHty " + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_hwd_id",hwdId));
	}
	
	@Override
	public Integer deleteprOffenderHwd(Long offenderBookId, String modifyUserId) {
		final String sql=getQuery("PURGE_DELETE_OFFENDER_HWD");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "offender_hwd";
			String whereCondition = "offender_book_id = :p_offender_book_id";
			inputMap.put("p_offender_book_id", offenderBookId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprOffenderHwd " + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_book_id",offenderBookId));
	}
	
	@Override
	public Integer deleteprOffenderIdentifyingMarks(Long offenderBookId, String modifyUserId) {
		final String sql=getQuery("PURGE_DELETE_OFFENDER_IDENTIFYING_MARKS");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "offender_identifying_marks";
			String whereCondition = "offender_book_id = :p_offender_book_id";
			inputMap.put("p_offender_book_id", offenderBookId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprOffenderIdentifyingMarks " + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_book_id",offenderBookId));
	}
	
	@Override
	public Integer deleteprOffenderIepLevels(Long offenderBookId, String modifyUserId) {
		final String sql=getQuery("PURGE_DELETE_OFFENDER_IEP_LEVELS");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "offender_iep_levels";
			String whereCondition = "offender_book_id = :p_offender_book_id";
			inputMap.put("p_offender_book_id", offenderBookId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprOffenderIepLevels " + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_book_id",offenderBookId));
	}
	
	@Override
	public Integer deleteprOffenderImages(Long offenderBookId, String modifyUserId) {
		final String sql=getQuery("PURGE_DELETE_OFFENDER_IMAGES");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "offender_images";
			String whereCondition = "offender_book_id = :p_offender_book_id";
			inputMap.put("p_offender_book_id", offenderBookId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprOffenderImages " + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_book_id",offenderBookId));
	}
	
	@Override
	public Integer deleteprOffenderImprisonStatuses(Long offenderBookId, String modifyUserId) {
		final String sql=getQuery("PURGE_DELETE_OFFENDER_IMPRISON_STATUSES");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "offender_imprison_statuses";
			String whereCondition = "offender_book_id = :p_offender_book_id";
			inputMap.put("p_offender_book_id", offenderBookId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprOffenderImprisonStatuses " + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_book_id",offenderBookId));
	}
	
	@Override
	public Integer deleteprOffenderIncome(Long offenderBookId, String modifyUserId) {
		final String sql=getQuery("PURGE_DELETE_OFFENDER_INCOME");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "offender_income";
			String whereCondition = "offender_book_id = :p_offender_book_id";
			inputMap.put("p_offender_book_id", offenderBookId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprOffenderIncome " + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_book_id",offenderBookId));
	}
	
	@Override
	public List<OffenderIndSchedules> prOffenderIndSchedules(Integer offenderBookId) {
		final String sql=getQuery("PURGE_GET_OFFENDER_IND_SCHEDULES_PURGE_SCREEN");
		return namedParameterJdbcTemplate.query(sql, createParams("p_offender_book_id",offenderBookId),new BeanPropertyRowMapper<OffenderIndSchedules>(OffenderIndSchedules.class));
	}
	
	@Override
	public Integer deleteprOffenderIndSchSents1(Integer eventId, String modifyUserId) {
		final String sql=getQuery("PURGE_DELETE_OFFENDER_IND_SCH_SENTS");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "offender_ind_sch_sents";
			String whereCondition = "event_id = :p_event_id";
			inputMap.put("p_event_id", eventId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprOffenderIndSchSents1 " + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_event_id",eventId));
	}
	
	@Override
	public Integer deleteprOffenderIndSchWaitLists(Integer eventId, String modifyUserId) {
		final String sql=getQuery("PURGE_DELETE_OFFENDER_IND_SCH_WAIT_LISTS");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "offender_ind_sch_wait_lists";
			String whereCondition = "event_id = :p_event_id";
			inputMap.put("p_event_id", eventId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprOffenderIndSchWaitLists " + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_event_id",eventId));
	}
	
	@Override
	public Integer deleteprOffenderIndSchedules(Integer offenderBookId, String modifyUserId) {
		final String sql=getQuery("PURGE_DELETE_OFFENDER_IND_SCHEDULES_PURGE_SCREEN");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "offender_ind_schedules";
			String whereCondition = "offender_book_id = :p_offender_book_id";
			inputMap.put("p_offender_book_id", offenderBookId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprOffenderIndSchedules " + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_book_id",offenderBookId));
	}
	
	@Override
	public List<OffenderExternalMovements> prOffenderInternalMovements(Long offenderBookId) {
		final String sql=getQuery("PURGE_GET_OFFENDER_INTERNAL_MOVEMENTS");
		RowMapper<OffenderExternalMovements> mapper=Row2BeanRowMapper.makeMapping(sql, OffenderExternalMovements.class, courtListMapping);
		return namedParameterJdbcTemplate.query(sql, createParams("p_offender_book_id",offenderBookId),mapper);
	}
	
	@Override
	public Integer deleteprOffenderIntMovPersons(Long offenderBookId, Long movementSeq, String modifyUserId) {
		final String sql=getQuery("PURGE_DELETE_OFFENDER_INT_MOV_PERSONS");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "offender_int_mov_persons";
			String whereCondition = "offender_book_id = :p_offender_book_id AND movement_seq = :p_movement_seq";
			inputMap.put("p_offender_book_id", offenderBookId);
			inputMap.put("p_movement_seq", movementSeq);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprOffenderIntMovPersons " + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_book_id",offenderBookId,"p_movement_seq",movementSeq));
	}
	
	@Override
	public Integer deleteprOffenderInternalMovements(Long offenderBookId, String modifyUserId) {
		final String sql=getQuery("PURGE_DELETE_OFFENDER_INTERNAL_MOVEMENTS");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "offender_internal_movements";
			String whereCondition = "offender_book_id = :p_offender_book_id";
			inputMap.put("p_offender_book_id", offenderBookId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprOffenderInternalMovements " + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_book_id",offenderBookId));
	}
	
	@Override
	public Integer deleteprOffenderInternalStatuses(Integer offenderBookId, String modifyUserId) {
		final String sql=getQuery("PURGE_DELETE_OFFENDER_INTERNAL_STATUSES");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "offender_internal_statuses";
			String whereCondition = "offender_book_id = :p_offender_book_id";
			inputMap.put("p_offender_book_id", offenderBookId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprOffenderInternalStatuses " + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_book_id",offenderBookId));
	}
	
	@Override
	public Integer deleteprOffenderInterventionTiers(Long offenderBookId, String modifyUserId) {
		final String sql=getQuery("PURGE_DELETE_OFFENDER_INTERVENTION_TIERS");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "offender_intervention_tiers";
			String whereCondition = "offender_book_id = :p_offender_book_id";
			inputMap.put("p_offender_book_id", offenderBookId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprOffenderInterventionTiers " + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_book_id",offenderBookId));
	}
	
	@Override
	public Integer deleteprOffenderLanguages(Long offenderBookId, String modifyUserId) {
		final String sql=getQuery("PURGE_DELETE_OFFENDER_LANGUAGES");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "offender_languages";
			String whereCondition = "offender_book_id = :p_offender_book_id";
			inputMap.put("p_offender_book_id", offenderBookId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprOffenderLanguages " + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_book_id",offenderBookId));
	}
	
	@Override
	public Integer deleteprOffenderLiabilities(Long offenderBookId, String modifyUserId) {
		final String sql=getQuery("PURGE_DELETE_OFFENDER_LIABILITIES");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "offender_liabilities";
			String whereCondition = "offender_book_id = :p_offender_book_id";
			inputMap.put("p_offender_book_id", offenderBookId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprOffenderLiabilities " + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_book_id",offenderBookId));
	}
	
	@Override
	public Integer deleteprOffenderMailLogs(Long offenderBookId) {
		final String sql=getQuery("PURGE_DELETE_OFFENDER_LIABILITIES");
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_book_id",offenderBookId));
	}
	
	@Override
	public Integer deleteprOffenderMailRestrictions(Long offenderBookId, String modifyUserId) {
		final String sql=getQuery("PURGE_DELETE_OFFENDER_MAIL_RESTRICTIONS");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "offender_mail_restrictions";
			String whereCondition = "offender_book_id = :p_offender_book_id";
			inputMap.put("p_offender_book_id", offenderBookId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprOffenderMailRestrictions " + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_book_id",offenderBookId));
	}
	
	@Override
	public Integer deleteprOffendrMilitryDiscCtions(Long offenderBookId, String modifyUserId) {
		final String sql=getQuery("PURGE_DELETE_OFFENDER_MILITARY_DISC_ACTIONS");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "offender_military_disc_actions";
			String whereCondition = "offender_book_id = :p_offender_book_id";
			inputMap.put("p_offender_book_id", offenderBookId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprOffendrMilitryDiscCtions " + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_book_id",offenderBookId));
	}
	
	@Override
	public Integer deleteprOffenderMilitaryRecords(Long offenderBookId, String modifyUserId) {
		final String sql=getQuery("PURGE_DELETE_OFFENDER_MILITARY_RECORDS");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "offender_military_records";
			String whereCondition = "offender_book_id = :p_offender_book_id";
			inputMap.put("p_offender_book_id", offenderBookId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprOffenderMilitaryRecords " + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_book_id",offenderBookId));
	}
	
	@Override
	public Integer prOffenderMilitaryWarZones(Long offenderBookId, String modifyUserId) {
		final String sql=getQuery("PURGE_DELETE_OFFENDER_MILITARY_WAR_ZONES");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "offender_military_war_zones";
			String whereCondition = "offender_book_id = :p_offender_book_id";
			inputMap.put("p_offender_book_id", offenderBookId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in prOffenderMilitaryWarZones " + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_book_id",offenderBookId));
	}
	
	@Override
	public List<OffenderOicSanctions> prOffenderOasysPlans(Long offenderBookId) {
		final String sql=getQuery("PURGE_GET_OFFENDER_INTERNAL_MOVEMENTS");
		RowMapper<OffenderOicSanctions> mapper=Row2BeanRowMapper.makeMapping(sql, OffenderOicSanctions.class, courtListMapping);
		return namedParameterJdbcTemplate.query(sql, createParams("p_offender_book_id",offenderBookId),mapper);
	}
	
	@Override
	public Integer deleteprOffenderOasysConcerns(Long offenderBookId,Long pPlanSeq, String modifyUserId) {
		final String sql=getQuery("PURGE_DELETE_OFFENDER_OASYS_CONCERNS");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "offender_oasys_concerns";
			String whereCondition = "offender_book_id = :p_offender_book_id AND plan_seq = :p_plan_seq";
			inputMap.put("p_offender_book_id", offenderBookId);
			inputMap.put("p_plan_seq", pPlanSeq);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprOffenderOasysConcerns " + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_book_id",offenderBookId,"pPlanSeq",pPlanSeq));
	}
	
	@Override
	public Integer deleteprOffenderOsysRiskToOthrs(Long offenderBookId, Long pPlanSeq, String modifyUserId) {
		final String sql=getQuery("PURGE_DELETE_OFFENDER_OASYS_RISK_TO_OTHERS");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "offender_oasys_risk_to_others";
			String whereCondition = "offender_book_id = :p_offender_book_id AND plan_seq = :p_plan_seq";
			inputMap.put("p_offender_book_id", offenderBookId);
			inputMap.put("p_plan_seq", pPlanSeq);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprOffenderOsysRiskToOthrs " + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_book_id",offenderBookId,"pPlanSeq",pPlanSeq));
	}
	
	@Override
	public List<OffenderOasysSections> prOffenderOasysSections(Long offenderBookId, Integer planSeq) {
		final String sql=getQuery("PURGE_GET_OFFENDER_OASYS_SECTIONS");
		RowMapper<OffenderOasysSections> mapper=Row2BeanRowMapper.makeMapping(sql, OffenderOasysSections.class, courtListMapping);
		return namedParameterJdbcTemplate.query(sql, createParams("p_offender_book_id",offenderBookId,"p_plan_seq",planSeq),mapper);
	}
	
	@Override
	public List<OffenderOasysSections> prOffenderOasysObjectives(Long offenderBookId, Integer planSeq, String sectionCode) {
		final String sql=getQuery("PURGE_GET_OFFENDER_OASYS_OBJECTIVES");
		return namedParameterJdbcTemplate.query(sql, createParams("p_offender_book_id",offenderBookId,"p_plan_seq",planSeq,"p_section_code",sectionCode),new BeanPropertyRowMapper<OffenderOasysSections>(OffenderOasysSections.class));
	}
	
	@Override
	public List<OffenderOasysSections> prOffenderOasysActions(BigDecimal objectiveId) {
		final String sql=getQuery("PURGE_GET_OFFENDER_OASYS_ACTIONS");
		RowMapper<OffenderOasysSections> mapper=Row2BeanRowMapper.makeMapping(sql, OffenderOasysSections.class, courtListMapping);
		return namedParameterJdbcTemplate.query(sql, createParams("p_objective_id",objectiveId),mapper);
	}
	
	@Override
	public Integer deleteprOffenderOasysSupervisions(BigDecimal objectiveId,Long actionSeq) {
		final String sql=getQuery("PURGE_DELETE_OFFENDER_OASYS_SUPERVISIONS");
		return namedParameterJdbcTemplate.update(sql, createParams("p_objective_id",objectiveId,"p_action_seq",actionSeq));
	}
	
	@Override
	public Integer deleteprOffenderOasysActions(BigDecimal objectiveId, String modifyUserId) {
		final String sql=getQuery("PURGE_DELETE_OFFENDER_OASYS_ACTIONS");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "offender_oasys_actions";
			String whereCondition = "objective_id = :p_objective_id";
			inputMap.put("p_objective_id", objectiveId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprOffenderOasysActions " + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_objective_id",objectiveId));
	}
	
	@Override
	public Integer deleteprOffenderOasysObjectives(Long offenderBookId, Integer planSeq, String sectionCode, String modifyUserId) {
		final String sql=getQuery("PURGE_DELETE_OFFENDER_OASYS_OBJECTIVES");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "offender_oasys_objectives";
			String whereCondition = "offender_book_id = :p_offender_book_id AND plan_seq = :p_plan_seq AND section_code = :p_section_code";
			inputMap.put("p_offender_book_id", offenderBookId);
			inputMap.put("p_plan_seq", planSeq);
			inputMap.put("p_section_code", sectionCode);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprOffenderOasysObjectives " + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_book_id",offenderBookId,"p_plan_seq",planSeq,"p_section_code",sectionCode));
	}
	
	@Override
	public List<OffenderOicSanctions> prOffenderOicSanctions(Integer offenderBookId) {
		final String sql=getQuery("PURGE_GET_OFFENDER_OIC_SANCTIONS2");
		RowMapper<OffenderOicSanctions> mapper=Row2BeanRowMapper.makeMapping(sql, OffenderOicSanctions.class, courtListMapping);
		return namedParameterJdbcTemplate.query(sql, createParams("p_offender_book_id",offenderBookId),mapper);
	}
	
	@Override
	public Integer deleteprOffenderOicSanctions(Integer offenderBookId, String modifyUserId) {
		final String sql=getQuery("PURGE_DELETE_OFFENDER_OIC_SANCTIONS1");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "offender_oic_sanctions";
			String whereCondition = "offender_book_id = :p_offender_book_id";
			inputMap.put("p_offender_book_id", offenderBookId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprOffenderOicSanctions " + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_book_id",offenderBookId));
	}
	
	@Override
	public List<OffenderOicSanctions> prOffenderParoleHearings(Long offenderBookId) {
		final String sql=getQuery("PURGE_GET_OFFENDER_OIC_SANCTIONS2");
		RowMapper<OffenderOicSanctions> mapper=Row2BeanRowMapper.makeMapping(sql, OffenderOicSanctions.class, courtListMapping);
		return namedParameterJdbcTemplate.query(sql, createParams("p_offender_book_id",offenderBookId),mapper);
	}
	
	@Override
	public List<OffenderOicSanctions> prOffenderParoleDecisions(Long offenderBookId, Long paroleHearingSeq) {
		final String sql=getQuery("PURGE_GET_OFFENDER_PAROLE_DECISIONS");
		RowMapper<OffenderOicSanctions> mapper=Row2BeanRowMapper.makeMapping(sql, OffenderOicSanctions.class, courtListMapping);
		return namedParameterJdbcTemplate.query(sql, createParams("p_offender_book_id",offenderBookId,"p_parole_hearing_seq",paroleHearingSeq),mapper);
	}
	
	@Override
	public Integer deleteprOffenderParoleConditions(Long pScheduleId, Long pParoleDecisionSeq, String modifyUserId) {
		final String sql=getQuery("PURGE_DELETE_OFFENDER_PAROLE_CONDITIONS");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "offender_parole_conditions";
			String whereCondition = "schedule_id = :p_schedule_id AND parole_decision_seq = :p_parole_decision_seq";
			inputMap.put("p_schedule_id", pScheduleId);
			inputMap.put("p_parole_decision_seq", pParoleDecisionSeq);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprOffenderParoleConditions " + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_schedule_id",pScheduleId,"p_parole_decision_seq",pParoleDecisionSeq));
	}
	
	@Override
	public Integer deleteprOffenderParoleStipultions(Long pScheduleId, Long pParoleDecisionSeq, String modifyUserId) {
		final String sql=getQuery("PURGE_DELETE_OFFENDER_PAROLE_STIPULATIONS");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "offender_parole_stipulations";
			String whereCondition = "schedule_id = :p_schedule_id AND parole_decision_seq = :p_parole_decision_seq";
			inputMap.put("p_schedule_id", pScheduleId);
			inputMap.put("parole_decision_seq", pParoleDecisionSeq);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprOffenderParoleStipultions " + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_schedule_id",pScheduleId,"p_parole_decision_seq",pParoleDecisionSeq));
	}
	
	@Override
	public Integer deleteprOffenderParoleDecisions(Long offenderBookId, Long paroleHearingSeq, String modifyUserId) {
		final String sql=getQuery("PURGE_DELETE_OFFENDER_PAROLE_DECISIONS");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "offender_parole_decisions";
			String whereCondition = "offender_book_id = :p_offender_book_id AND parole_hearing_seq = :p_parole_hearing_seq";
			inputMap.put("p_offender_book_id", offenderBookId);
			inputMap.put("p_parole_hearing_seq", paroleHearingSeq);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprOffenderParoleDecisions " + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_book_id",offenderBookId,"p_parole_hearing_seq",paroleHearingSeq));
	}
	
	@Override
	public Integer deleteprOffenderParolePlans(Long offenderBookId, String modifyUserId) {
		final String sql=getQuery("PURGE_DELETE_OFFENDER_PAROLE_PLANS");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "offender_parole_plans";
			String whereCondition = "offender_book_id = :p_offender_book_id";
			inputMap.put("p_offender_book_id", offenderBookId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprOffenderParolePlans " + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_book_id",offenderBookId));
	}
	@Override
	public Integer deleteprOffenderPlanedActivities(Long offenderBookId, String modifyUserId) {
		final String sql=getQuery("PURGE_DELETE_OFFENDER_PLANED_ACTIVITIES");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "offender_planed_activities";
			String whereCondition = "offender_book_id = :p_offender_book_id";
			inputMap.put("p_offender_book_id", offenderBookId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprOffenderPlanedActivities " + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_book_id",offenderBookId));
	}
	
	@Override
	public Integer deleteprOffenderPptyContainers(Integer offenderBookId, String modifyUserId) {
		final String sql=getQuery("PURGE_DELETE_OFFENDER_PPTY_CONTAINERS");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "offender_ppty_containers";
			String whereCondition = "offender_book_id = :p_offender_book_id";
			inputMap.put("p_offender_book_id", offenderBookId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprOffenderPptyContainers " + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_book_id",offenderBookId));
	}
	
	@Override
	public Integer deleteprOffenderPptyItems(Integer offenderBookId, String modifyUserId) {
		final String sql=getQuery("PURGE_DELETE_OFFENDER_PPTY_ITEMS");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "offender_ppty_items";
			String whereCondition = "offender_book_id = :p_offender_book_id";
			inputMap.put("p_offender_book_id", offenderBookId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprOffenderPptyItems " + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_book_id",offenderBookId));
	}
	
	@Override
	public List<OffenderPptyItemTxns> prOffenderPptyItemEvents(Integer offenderBookId) {
		final String sql=getQuery("PURGE_GET_OFFENDER_OIC_SANCTIONS2");
		RowMapper<OffenderPptyItemTxns> mapper=Row2BeanRowMapper.makeMapping(sql, OffenderPptyItemTxns.class, courtListMapping);
		return namedParameterJdbcTemplate.query(sql, createParams("p_offender_book_id",offenderBookId),mapper);
	}
	
	@Override
	public Integer deleteprOffenderPptyItemTxns(Integer offenderBookId, Integer eventSeq) {
		final String sql=getQuery("PURGE_DELETE_OFFENDER_PPTY_ITEM_TXNS");
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_book_id",offenderBookId,"p_event_seq",eventSeq));
	}
	
	@Override
	public Integer deleteprOffenderPptyItemEvents(Integer offenderBookId) {
		final String sql=getQuery("PURGE_DELETE_OFFENDER_PPTY_ITEM_EVENTS");
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_book_id",offenderBookId));
	}
	
	@Override
	public List<OffenderPrgObligations> prOffenderPrgObligations(Long offenderBookId) {
		final String sql=getQuery("PURGE_GET_OFFENDER_PRG_OBLIGATIONS");
		return namedParameterJdbcTemplate.query(sql, createParams("p_offender_book_id",offenderBookId),new BeanPropertyRowMapper<OffenderPrgObligations>(OffenderPrgObligations.class));
	}
	
	@Override
	public List<OffenderCourseAttendances> prOffenderCourseAttendancs1(Long offenderPrgObligationId) {
		final String sql=getQuery("PURGE_GET_OFFENDER_COURSE_ATTENDANCES1");
		return namedParameterJdbcTemplate.query(sql, createParams("p_offender_prg_obligtion_id",offenderPrgObligationId),new BeanPropertyRowMapper<OffenderCourseAttendances>(OffenderCourseAttendances.class));
	}
	
	@Override
	public Integer deleteprOffenderCourseAttendancs1(Long offenderPrgObligationId) {
		final String sql=getQuery("PURGE_DELETE_OFFENDER_COURSE_ATTENDANCES1");
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_prg_obligtion_id",offenderPrgObligationId));
	}
	
	@Override
	public List<OffenderIndSchedules> prOffenderIndSchedules1(Integer offenderPrgObligationId) {
		final String sql=getQuery("PURGE_GET_OFFENDER_IND_SCHEDULES1");
		return namedParameterJdbcTemplate.query(sql, createParams("p_offender_prg_obligtion_id",offenderPrgObligationId),new BeanPropertyRowMapper<OffenderIndSchedules>(OffenderIndSchedules.class));
	}
	
	@Override
	public Integer deleteprOffenderIndSchedules1(Integer offenderPrgObligationId) {
		final String sql=getQuery("PURGE_DELETE_OFFENDER_IND_SCHEDULES1");
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_prg_obligtion_id",offenderPrgObligationId));
	}
	
	@Override
	public Integer deleteprOffenderObligationAdjs(Integer offenderPrgObligationId) {
		final String sql=getQuery("PURGE_DELETE_OFFENDER_OBLIGATION_ADJS");
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_prg_obligtion_id",offenderPrgObligationId));
	}
	
	@Override
	public Integer deleteprOffenderPrgObligationHty(Integer offenderPrgObligationId) {
		final String sql=getQuery("PURGE_DELETE_OFFENDER_PRG_OBLIGATION_HTY");
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_prg_obligtion_id",offenderPrgObligationId));
	}
	
	@Override
	public List<OffenderProgramProfiles> prOffenderProgramProfiles1(Integer offenderPrgObligationId) {
		final String sql=getQuery("PURGE_GET_OFFENDER_PROGRAM_PROFILES");
		return namedParameterJdbcTemplate.query(sql, createParams("p_offender_prg_obligtion_id",offenderPrgObligationId),new BeanPropertyRowMapper<OffenderProgramProfiles>(OffenderProgramProfiles.class));
	}
	
	@Override
	public List<OffenderCourseApptGrp> prOffenderCourseApptGrps(BigDecimal offPrgrefId) {
		final String sql=getQuery("PURGE_GET_OFFENDER_COURSE_APPT_GRPS");
		return namedParameterJdbcTemplate.query(sql, createParams("p_off_prgref_id",offPrgrefId),new BeanPropertyRowMapper<OffenderCourseApptGrp>(OffenderCourseApptGrp.class));
	}
	
	@Override
	public Integer deleteprOffenderCourseApptRules(Long offenderCourseApptGrpId,String modifyUserId) {
		final String sql=getQuery("PURGE_DELETE_OFFENDER_COURSE_APPT_RULES");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "offender_course_appt_rules";
			String whereCondition = "offender_course_appt_grp_id = :p_offender_cours_ppt_grp_id";
			inputMap.put("p_offender_cours_ppt_grp_id", offenderCourseApptGrpId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprOffenderCourseApptRules " + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_cours_ppt_grp_id",offenderCourseApptGrpId));
	}
	
	@Override
	public Integer deleteprOffenderCourseApptGrps(BigDecimal offPrgrefId,String modifyUserId) {
		final String sql=getQuery("PURGE_DELETE_OFFENDER_COURSE_APPT_GRPS");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "offender_course_appt_grps";
			String whereCondition = "off_prgref_id = :p_off_prgref_id";
			inputMap.put("p_off_prgref_id", offPrgrefId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprOffenderCourseApptGrps " + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_off_prgref_id",offPrgrefId));
	}
	@Override
	public List<OffenderCourseAttendances> prOffenderCourseAttendancs2(Long offPrgrefId) {
		final String sql=getQuery("PURGE_GET_OFFENDER_COURSE_ATTENDANCES2");
		return namedParameterJdbcTemplate.query(sql, createParams("p_off_prgref_id",offPrgrefId),new BeanPropertyRowMapper<OffenderCourseAttendances>(OffenderCourseAttendances.class));
	}
	
	@Override
	public Integer deleteprOffenderCourseAttendancs2(Long offPrgrefId) {
		final String sql=getQuery("PURGE_DELETE_OFFENDER_COURSE_ATTENDANCES2");
		return namedParameterJdbcTemplate.update(sql, createParams("p_off_prgref_id",offPrgrefId));
	}
	
	@Override
	public List<OffenderProgramProfiles> prOffenderProgramProfiles2(Long offPrgrefId) {
		final String sql=getQuery("PURGE_GET_OFFENDER_PROGRAM_PROFILES2");
		return namedParameterJdbcTemplate.query(sql, createParams("p_program_off_prgref_id",offPrgrefId),new BeanPropertyRowMapper<OffenderProgramProfiles>(OffenderProgramProfiles.class));
	}
	@Override
	public Integer deleteprOffenderProgramProfiles2(Long offPrgrefId,String modifyUserId) {
		final String sql=getQuery("PURGE_DELETE_OFFENDER_PROGRAM_PROFILES");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "offender_program_profiles";
			String whereCondition = "program_off_prgref_id = :p_program_off_prgref_id";
			inputMap.put("p_program_off_prgref_id", offPrgrefId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprOffenderProgramProfiles2 " + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_program_off_prgref_id",offPrgrefId));
	}
	
	@Override
	public Integer deleteprOffenderProgramProfiles1(Integer offenderPrgObligationId) {
		final String sql=getQuery("PURGE_DELETE_OFFENDER_PROGRAM_PROFILES1");
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_prg_obligtion_id",offenderPrgObligationId));
	}
	
	@Override
	public Integer deleteprOffenderPrgObligations(Long offenderBookId) {
		final String sql=getQuery("PURGE_DELETE_OFFENDER_PRG_OBLIGATIONS");
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_book_id",offenderBookId));
	}
	
	@Override
	public List<OffenderProceedings> prOffenderProceedings(BigDecimal offenderBookId) {
		final String sql=getQuery("PURGE_GET_OFFENDER_PROCEEDINGS");
		return namedParameterJdbcTemplate.query(sql, createParams("p_offender_book_id",offenderBookId),new BeanPropertyRowMapper<OffenderProceedings>(OffenderProceedings.class));
	}
	
	@Override
	public List<CourtEvents> prCourtEvents2(Integer offenderProceedingId) {
		final String sql=getQuery("PURGE_GET_COURT_EVENTS1");
		return namedParameterJdbcTemplate.query(sql, createParams("p_offender_proceeding_id",offenderProceedingId),new BeanPropertyRowMapper<CourtEvents>(CourtEvents.class));
	}
	
	@Override
	public Integer deleteprCourtEvents2(Integer offenderProceedingId) {
		final String sql=getQuery("PURGE_DELETE_COURT_EVENTS2");
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_proceeding_id",offenderProceedingId));
	}
	
	@Override
	public Integer deleteprOffenderProceedingSents1(BigDecimal offenderProceedingId) {
		final String sql=getQuery("PURGE_DELETE_OFFENDER_PROCEEDING_SENTS");
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_proceeding_id",offenderProceedingId));
	}
	
	@Override
	public Integer deleteprOffenderProceedings(BigDecimal offenderBookId) {
		final String sql=getQuery("PURGE_DELETE_OFFENDER_PROCEEDINGS");
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_book_id",offenderBookId));
	}
	
	@Override
	public Integer deleteprOffenderProfiles(Long offenderBookId) {
		final String sql=getQuery("PURGE_OFFENDER_BOOKING_DELETE_OFFENDER_PROFILES");
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_book_id",offenderBookId));
	}
	
	@Override
	public List<OffenderProgramProfiles> prOffenderProgramProfiles(Long offenderBookId) {
		final String sql=getQuery("PURGE_GET_OFFENDER_PROGRAM_PROFILES3_PURGE");
		return namedParameterJdbcTemplate.query(sql, createParams("p_offender_book_id",offenderBookId),new BeanPropertyRowMapper<OffenderProgramProfiles>(OffenderProgramProfiles.class));
	}
	
	@Override
	public Integer deleteprOffenderProgramProfiles(Long offenderBookId) {
		final String sql=getQuery("PURGE_DELETE_OFFENDER_PROGRAM_PROFILES2_PURGE");
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_book_id",offenderBookId));
	}
	
	@Override
	public Integer deleteprOffenderPropertyBundles(BigDecimal offenderBookId) {
		final String sql=getQuery("PURGE_DELETE_OFFENDER_PROPERTY_BUNDLES");
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_book_id",offenderBookId));
	}
	
	@Override
	public List<VOffExtMovements> prOffenderProposedMvmnts(Long offenderBookId) {
		final String sql=getQuery("PURGE_GET_OFFENDER_PROPOSED_MVMNTS");
		RowMapper<VOffExtMovements> mapper=Row2BeanRowMapper.makeMapping(sql, VOffExtMovements.class, courtListMapping);
		return namedParameterJdbcTemplate.query(sql, createParams("p_offender_book_id",offenderBookId),mapper);
	}
	
	@Override
	public Integer deleteprOffenderMovementDetails(Long offenderBookId, Long movememntseq) {
		final String sql=getQuery("PURGE_DELETE_OFFENDER_MOVEMENT_DETAILS");
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_book_id",offenderBookId,"p_movement_seq",movememntseq));
	}
	
	@Override
	public Integer deleteprOffenderProposedMvmnts(Long offenderBookId) {
		final String sql=getQuery("PURGE_DELETE_OFFENDER_PROPOSED_MVMNTS");
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_book_id",offenderBookId));
	}
	
	@Override
	public List<OffenderPtr> prOffenderPtr(Long offenderBookId) {
		final String sql=getQuery("PURGE_GET_OFFENDER_PTR");
		return namedParameterJdbcTemplate.query(sql, createParams("p_offender_book_id",offenderBookId),new BeanPropertyRowMapper<OffenderPtr>(OffenderPtr.class));
	}
	
	@Override
	public Integer deleteprExtOwnershipTransfer(Long ptrId) {
		final String sql=getQuery("PURGE_DELETE_EXT_OWNERSHIP_TRANSFER");
		return namedParameterJdbcTemplate.update(sql, createParams("p_ptr_id",ptrId));
	}
	
	@Override
	public Integer deleteprOffenderBookingAgyLocs1(Long ptrId) {
		final String sql=getQuery("PURGE_DELETE_OFFENDER_BOOKING_AGY_LOCS1");
		return namedParameterJdbcTemplate.update(sql, createParams("p_ptr_id",ptrId));
	}
	
	@Override
	public Integer deleteprOffenderPtrDetails(Long ptrId) {
		final String sql=getQuery("PURGE_DELETE_OFFENDER_PTR_DETAILS");
		return namedParameterJdbcTemplate.update(sql, createParams("p_ptr_id",ptrId));
	}
	
	@Override
	public Integer deleteprOffenderPtr(Long offenderBookId) {
		final String sql=getQuery("PURGE_DELETE_OFFENDER_PTR");
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_book_id",offenderBookId));
	}
	
	@Override
	public Integer deleteprOffenderReferrals(Long offenderBookId) {
		final String sql=getQuery("PURGE_DELETE_OFFENDER_REFERRALS");
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_book_id",offenderBookId));
	}
	
	@Override
	public Integer deleteprOffenderReleaseDetails(Long offenderBookId) {
		final String sql=getQuery("PURGE_DELETE_OFFENDER_RELEASE_DETAILS");
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_book_id",offenderBookId));
	}
	
	@Override
	public Integer deleteprOffenderResidences(Long offenderBookId) {
		final String sql=getQuery("PURGE_DELETE_OFFENDER_RESIDENCES");
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_book_id",offenderBookId));
	}
	
	@Override
	public Integer deleteprOffenderRiskPredictors(Long offenderBookId) {
		final String sql=getQuery("PURGE_DELETE_OFFENDER_RISK_PREDICTORS");
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_book_id",offenderBookId));
	}
	
	@Override
	public Integer deleteprOffenderSentCalculations(Long offenderBookId) {
		final String sql=getQuery("PURGE_DELETE_OFFENDER_SENT_CALCULATIONS");
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_book_id",offenderBookId));
	}
	
	@Override
	public List<OffenderStgAffiliations> prOffenderStgAffiliations(BigDecimal offenderBookId) {
		final String sql=getQuery("PURGE_GET_OFFENDER_STG_AFFILIATIONS");
		RowMapper<OffenderStgAffiliations> mapper=Row2BeanRowMapper.makeMapping(sql, OffenderStgAffiliations.class, courtListMapping);
		return namedParameterJdbcTemplate.query(sql, createParams("p_offender_book_id",offenderBookId),mapper);
	}
	
	@Override
	public Integer deleteprOffenderStgDetails(BigDecimal offenderBookId, BigDecimal stgSeq) {
		final String sql=getQuery("PURGE_DELETE_OFFENDER_STG_DETAILS");
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_book_id",offenderBookId,"p_stg_seq",stgSeq));
	}
	
	@Override
	public Integer deleteprOffenderStgAffiliations(BigDecimal offenderBookId) {
		final String sql=getQuery("PURGE_DELETE_OFFENDER_STG_AFFILIATIONS");
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_book_id",offenderBookId));
	}
	
	@Override
	public Integer deleteprOffenderStgAssociations(BigDecimal offenderBookId) {
		final String sql=getQuery("PURGE_DELETE_OFFENDER_SUBSTANCE_ABUSES");
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_book_id",offenderBookId));
	}
	
	@Override
	public Integer deleteprOffenderSubstanceAbuses(BigDecimal offenderBookId) {
		final String sql=getQuery("PURGE_DELETE_OFFENDER_SUPERVISING_COURTS");
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_book_id",offenderBookId));
	}
	
	@Override
	public List<AssessmentResults> prOffenderSupervisionLevels(BigDecimal offenderBookId) {
		final String sql=getQuery("PURGE_GET_OFFENDER_SUPERVISION_LEVELS");
		RowMapper<AssessmentResults> mapper=Row2BeanRowMapper.makeMapping(sql, AssessmentResults.class, courtListMapping);
		return namedParameterJdbcTemplate.query(sql, createParams("p_offender_book_id",offenderBookId),mapper);
	}
	
	@Override
	public List<OffenderOicSanctions> prOffenderSupervisionPlans(Integer offenderBookId, Long pSupervisionLevelType) {
		final String sql=getQuery("PURGE_GET_OFFENDER_SUPERVISION_PLANS");
		RowMapper<OffenderOicSanctions> mapper=Row2BeanRowMapper.makeMapping(sql, OffenderOicSanctions.class, courtListMapping);
		return namedParameterJdbcTemplate.query(sql, createParams("p_offender_book_id",offenderBookId,"p_supervision_level_type",pSupervisionLevelType),mapper);
	}
	
	@Override
	public Integer deleteprOffenderPlanedActivities1(Integer offenderBookId, Long planSeq) {
		final String sql=getQuery("PURGE_DELETE_OFFENDER_PLANED_ACTIVITIES1");
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_book_id",offenderBookId,"p_plan_seq",planSeq));
	}
	
	@Override
	public Integer deleteprOffenderSupervisionPlans(Integer offenderBookId, Long pSupervisionLevelType) {
		final String sql=getQuery("PURGE_DELETE_OFFENDER_SUPERVISION_PLANS");
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_book_id",offenderBookId,"p_supervision_level_type",pSupervisionLevelType));
	}
	
	@Override
	public Integer deleteprOffenderSupervisionRates(Integer offenderBookId, Long pSupervisionType) {
		final String sql=getQuery("PURGE_DELETE_OFFENDER_SUPERVISION_RATES");
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_book_id",offenderBookId,"p_supervision_type",pSupervisionType));
	}
	
	@Override
	public Integer deleteprOffenderSupervisionLevels(BigDecimal offenderBookId) {
		final String sql=getQuery("PURGE_DELETE_OFFENDER_SUPERVISION_LEVELS");
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_book_id",offenderBookId));
	}
	
	@Override
	public Integer deleteprOffenderTeamAssignments(Integer offenderBookId) {
		final String sql=getQuery("PURGE_DELETE_OFFENDER_TEAM_ASSIGNMENTS");
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_book_id",offenderBookId));
	}
	
	@Override
	public Integer deleteprOffenderTeamAssignHty(Long offenderBookId) {
		final String sql=getQuery("PURGE_DELETE_OFFENDER_TEAM_ASSIGN_HTY");
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_book_id",offenderBookId));
	}
	
	@Override
	public Integer deleteprOffenderTemporaryAbsences(Long offenderBookId) {
		final String sql=getQuery("PURGE_DELETE_OFFENDER_TEMPORARY_ABSENCES");
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_book_id",offenderBookId));
	}
	
	@Override
	public Integer deleteprOffenderTmpRelSchedules(Long offenderBookId) {
		final String sql=getQuery("PURGE_DELETE_OFFENDER_TMP_REL_SCHEDULES");
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_book_id",offenderBookId));
	}
	
	@Override
	public List<VOffenderVisits> prOffenderVisits(Long offenderBookId) {
		final String sql=getQuery("PURGE_GET_OFFENDER_VISITS");
		return namedParameterJdbcTemplate.query(sql, createParams("p_offender_book_id",offenderBookId),new BeanPropertyRowMapper<VOffenderVisits>(VOffenderVisits.class));
	}
	
	@Override
	public Integer deleteprOffenderVisitVisitors(Long offenderVisitVisitorId) {
		final String sql=getQuery("PURGE_DELETE_OFFENDER_VISIT_VISITORS");
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_visit_id",offenderVisitVisitorId));
	}
	
	@Override
	public Integer deleteprOffenderVisits(Long offenderBookId) {
		final String sql=getQuery("PURGE_DELETE_OFFENDER_VISITS");
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_book_id",offenderBookId));
	}
	
	@Override
	public Integer deleteprOffenderVitalSigns(BigDecimal offenderBookId) {
		final String sql=getQuery("PURGE_DELETE_OFFENDER_VITAL_SIGNS_PURGE");
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_book_id",offenderBookId));
	}
	
	@Override
	public List<Orders> prOrders(BigDecimal offenderBookId) {
		final String sql=getQuery("PURGE_GET_ORDERS_PURGE");
		return namedParameterJdbcTemplate.query(sql, createParams("p_offender_book_id",offenderBookId),new BeanPropertyRowMapper<Orders>(Orders.class));
	}
	
	@Override
	public List<OffenderSentences> prOffenderSentences1(BigDecimal orderId) {
		final String sql=getQuery("PURGE_GET_OFFENDER_SENTENCES");
		RowMapper<OffenderSentences> mapper=Row2BeanRowMapper.makeMapping(sql, OffenderSentences.class, courtListMapping);
		return namedParameterJdbcTemplate.query(sql, createParams("p_order_id",orderId),mapper);
	}
	
	@Override
	public Integer deleteprCourtEventSentences1(Long offenderBookId, Long sentenceSeq) {
		final String sql=getQuery("PURGE_DELETE_OFFENDER_VITAL_SIGNS");
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_book_id",offenderBookId,"p_sentence_seq",sentenceSeq));
	}
	
	@Override
	public Integer deleteprOffenderCaseNoteSents(Long offenderBookId, Long sentenceSeq,String modifyUserId) {
		final String sql=getQuery("PURGE_DELETE_OFFENDER_CASE_NOTE_SENTS");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "offender_case_note_sents";
			String whereCondition = " offender_book_id = :p_offender_book_id AND sentence_seq = :p_sentence_seq";
			inputMap.put("p_offender_book_id", offenderBookId);
			inputMap.put("p_sentence_seq", sentenceSeq);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprOffenderCaseNoteSents" + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_book_id",offenderBookId,"p_sentence_seq",sentenceSeq));
	}
	
	@Override
	public Integer deleteprOffndrCommnityConditions1(Long offenderBookId, Long sentenceSeq,String modifyUserId) {
		final String sql=getQuery("PURGE_DELETE_OFFENDER_COMMUNITY_CONDITIONS1");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "offender_community_conditions";
			String whereCondition = " offender_book_id = :p_offender_book_id AND sentence_seq = :p_sentence_seq";
			inputMap.put("p_offender_book_id", offenderBookId);
			inputMap.put("p_sentence_seq", sentenceSeq);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprOffndrCommnityConditions1" + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_book_id",offenderBookId,"p_sentence_seq",sentenceSeq));
	}
	
	@Override
	public Integer deleteprOffenderIndSchSents(Long offenderBookId, Long sentenceSeq,String modifyUserId) {
		final String sql=getQuery("PURGE_DELETE_OFFENDER_IND_SCH_SENTS1");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "offender_ind_sch_sents";
			String whereCondition = " offender_book_id = :p_offender_book_id AND sentence_seq = :p_sentence_seq";
			inputMap.put("p_offender_book_id", offenderBookId);
			inputMap.put("p_sentence_seq", sentenceSeq);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprOffenderIndSchSents" + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_book_id",offenderBookId,"p_sentence_seq",sentenceSeq));
	}
	
	@Override
	public Integer deleteprOffenderLicenceSentences1(Long offenderBookId, Long sentenceSeq,String modifyUserId) {
		final String sql=getQuery("PURGE_DELETE_OFFENDER_LICENCE_SENTENCES");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "offender_licence_sentences";
			String whereCondition = "offender_book_id = :p_offender_book_id AND sentence_seq = :p_sentence_seq";
			inputMap.put("p_offender_book_id", offenderBookId);
			inputMap.put("p_sentence_seq", sentenceSeq);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprOffenderLicenceSentences1" + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_book_id",offenderBookId,"p_sentence_seq",sentenceSeq));
	}
	
	@Override
	public Integer deleteprOffenderLicenceSentences2(Long offenderBookId, Long pLicenceSentenceSeq) {
		final String sql=getQuery("PURGE_DELETE_OFFENDER_LICENCE_SENTENCES1");
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_book_id",offenderBookId,"p_licence_sentence_seq",pLicenceSentenceSeq));
	}
	
	@Override
	public Integer deleteprOffenderProceedingSents(Long offenderBookId, Long sentenceSeq,String modifyUserId) {
		final String sql=getQuery("PURGE_DELETE_OFFENDER_PROCEEDING_SENTS1");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "offender_proceeding_sents";
			String whereCondition = "offender_book_id = :p_offender_book_id AND sentence_seq = :p_sentence_seq";
			inputMap.put("p_offender_book_id", offenderBookId);
			inputMap.put("p_sentence_seq", sentenceSeq);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprOffenderProceedingSents" + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_book_id",offenderBookId,"p_sentence_seq",sentenceSeq));
	}
	
	@Override
	public Integer deleteprOffenderSentenceAdjusts(Long offenderBookId, Long sentenceSeq) {
		final String sql=getQuery("PURGE_DELETE_OFFENDER_SENTENCE_ADJUSTS");
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_book_id",offenderBookId,"p_sentence_seq",sentenceSeq));
	}
	
	@Override
	public Integer deleteprOffenderSentenceCharges1(Long offenderBookId, Long sentenceSeq) {
		final String sql=getQuery("PURGE_DELETE_OFFENDER_SENTENCE_ADJUSTS");
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_book_id",offenderBookId,"p_sentence_seq",sentenceSeq));
	}
	
	@Override
	public Integer deleteprOffenderSentenceHty(Long offenderBookId, Long sentenceSeq,String modifyUserId) {
		final String sql=getQuery("PURGE_DELETE_OFFENDER_SENTENCE_HTY");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "offender_sentence_hty";
			String whereCondition = "offender_book_id = :p_offender_book_id AND sentence_seq = :p_sentence_seq";
			inputMap.put("p_offender_book_id", offenderBookId);
			inputMap.put("p_sentence_seq", sentenceSeq);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprOffenderSentenceHty" + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_book_id",offenderBookId,"p_sentence_seq",sentenceSeq));
	}
	
	@Override
	public Integer deleteprOffenderSentenceStatuses(Long offenderBookId, Long sentenceSeq,String modifyUserId) {
		final String sql=getQuery("PURGE_DELETE_OFFENDER_SENTENCE_STATUSES");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "offender_sentence_statuses";
			String whereCondition = "offender_book_id = :p_offender_book_id AND sentence_seq = :p_sentence_seq";
			inputMap.put("p_offender_book_id", offenderBookId);
			inputMap.put("p_sentence_seq", sentenceSeq);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprOffenderSentenceStatuses" + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_book_id",offenderBookId,"p_sentence_seq",sentenceSeq));
	}
	
	@Override
	public Integer deleteprOffenderSentenceTerms(Long offenderBookId, Long sentenceSeq,String modifyUserId) {
		final String sql=getQuery("PURGE_DELETE_OFFENDER_SENTENCE_TERMS");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "offender_sentence_terms";
			String whereCondition = "offender_book_id = :p_offender_book_id AND sentence_seq = :p_sentence_seq";
			inputMap.put("p_offender_book_id", offenderBookId);
			inputMap.put("p_sentence_seq", sentenceSeq);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprOffenderSentenceTerms" + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_book_id",offenderBookId,"p_sentence_seq",sentenceSeq));
	}
	
	@Override
	public Integer deleteprOffenderSentenceUaEvents(Long offenderBookId, Long sentenceSeq,String modifyUserId) {
		final String sql=getQuery("PURGE_DELETE_OFFENDER_SENTENCE_UA_EVENTS");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "offender_sentence_ua_events";
			String whereCondition = "offender_book_id = :p_offender_book_id AND sentence_seq = :p_sentence_seq";
			inputMap.put("p_offender_book_id", offenderBookId);
			inputMap.put("p_sentence_seq", sentenceSeq);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprOffenderSentenceUaEvents" + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_book_id",offenderBookId,"p_sentence_seq",sentenceSeq));
	}
	
	@Override
	public Integer deleteprOffenderUnpaidWorkAdj(Long offenderBookId, Long sentenceSeq,String modifyUserId) {
		final String sql=getQuery("PURGE_DELETE_OFFENDER_UNPAID_WORK_ADJ");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "offender_unpaid_work_adj";
			String whereCondition = "offender_book_id = :p_offender_book_id AND sentence_seq = :p_sentence_seq";
			inputMap.put("p_offender_book_id", offenderBookId);
			inputMap.put("p_sentence_seq", sentenceSeq);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprOffenderUnpaidWorkAdj" + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_book_id",offenderBookId,"p_sentence_seq",sentenceSeq));
	}
	
	@Override
	public Integer deleteprOffenderSentences1(BigDecimal orderId,String modifyUserId) {
		final String sql=getQuery("PURGE_DELETE_OFFENDER_SENTENCES");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "offender_sentences";
			String whereCondition = "order_id = :p_order_id";
			inputMap.put("p_order_id", orderId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprOffenderSentences1" + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_order_id",orderId));
	}
	
	@Override
	public Integer deleteprOrderPurposes(BigDecimal orderId,String modifyUserId) {
		final String sql=getQuery("PURGE_DELETE_OFFENDER_SENTENCES");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "offender_sentences";
			String whereCondition = "order_id = :p_order_id";
			inputMap.put("p_order_id", orderId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprOffenderSentences1" + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_order_id",orderId));
	}
	
	@Override
	public Integer deleteprOrders(BigDecimal offenderBookId) {
		final String sql=getQuery("PURGE_DELETE_ORDERS_PURGE");
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_book_id",offenderBookId));
	}
	
	@Override
	public List<ReleasePlans> prReleasePlans(Long offenderBookId) {
		final String sql=getQuery("PURGE_GET_RELEASE_PLANS");
		return namedParameterJdbcTemplate.query(sql, createParams("p_offender_book_id",offenderBookId),new BeanPropertyRowMapper<ReleasePlans>(ReleasePlans.class));
	}
	
	@Override
	public Integer deleteprRpOtherOccupants(Long releasePlanId) {
		final String sql=getQuery("PURGE_DELETE_RP_OTHER_OCCUPANTS");
		return namedParameterJdbcTemplate.update(sql, createParams("p_release_plan_id",releasePlanId));
	}
	
	@Override
	public Integer deleteprReleasePlans(Long offenderBookId) {
		final String sql=getQuery("PURGE_DELETE_RELEASE_PLANS");
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_book_id",offenderBookId));
	}
	
	@Override
	public List<Orders> prStaffWorks(Long offenderBookId) {
		final String sql=getQuery("PURGE_GET_STAFF_WORKS");
		return namedParameterJdbcTemplate.query(sql, createParams("p_offender_book_id",offenderBookId),new BeanPropertyRowMapper<Orders>(Orders.class));
	}
	
	@Override
	public Integer deleteprStaffWorkAssignments(BigDecimal staffId) {
		final String sql=getQuery("PURGE_DELETE_STAFF_WORK_ASSIGNMENTS");
		return namedParameterJdbcTemplate.update(sql, createParams("p_staff_work_id",staffId));
	}
	
	@Override
	public Integer deleteprStaffWorks(Long offenderBookId) {
		final String sql=getQuery("PURGE_DELETE_STAFF_WORKS");
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_book_id",offenderBookId));
	}
	
	@Override
	public Integer deleteprSupervisionTransactions(Long offenderBookId) {
		final String sql=getQuery("PURGE_DELETE_SUPERVISION_TRANSACTIONS");
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_book_id",offenderBookId));
	}
	
	@Override
	public Integer deleteprTaskAssignmentHty(BigDecimal offenderBookId) {
		final String sql=getQuery("PURGE_DELETE_TASK_ASSIGNMENT_HTY");
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_book_id",offenderBookId));
	}
	
	@Override
	public List<Addresses> prAddresses(BigDecimal ownerId, String ownerClass) {
		final String sql=getQuery("PURGE_GET_ADDRESSES");
		return namedParameterJdbcTemplate.query(sql, createParams("p_owner_id",ownerId,"p_owner_class",ownerClass),new BeanPropertyRowMapper<Addresses>(Addresses.class));
	}
	
	
	@Override
	public Integer deleteprAddressUsages(Long addressId,String modifyUserId) {
		final String sql=getQuery("PURGE_DELETE_ADDRESS_USAGES");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "address_usages";
			String whereCondition = "address_id = :p_address_id";
			inputMap.put("p_address_id", addressId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprAddressUsages" + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_address_id",addressId));
	}
	
	@Override
	public List<CourseActivities> prCourseActivities(Long servicesAddressId) {
		final String sql=getQuery("PURGE_GET_COURSE_ACTIVITIES");
		return namedParameterJdbcTemplate.query(sql, createParams("p_services_address_id",servicesAddressId),new BeanPropertyRowMapper<CourseActivities>(CourseActivities.class));
	}
	
	@Override
	public Integer deleteprCourseActivityAreas(Long parentCrsActyId,String modifyUserId) {
		final String sql=getQuery("PURGE_DELETE_COURSE_ACTIVITY_AREAS");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "course_activity_areas";
			String whereCondition = "crs_acty_id = :p_crs_acty_id";
			inputMap.put("p_crs_acty_id", parentCrsActyId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprCourseActivityAreas" + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_crs_acty_id",parentCrsActyId));
	}
	
	@Override
	public Integer deleteprCourseActivityParties(Long crsActyId,String modifyUserId) {
		final String sql=getQuery("PURGE_DELETE_COURSE_ACTIVITY_PARTIES");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "course_activity_parties";
			String whereCondition = "crs_acty_id = :p_crs_acty_id";
			inputMap.put("p_crs_acty_id", crsActyId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprCourseActivityParties " + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_crs_acty_id",crsActyId));
	}
	
	@Override
	public Integer deleteprCourseActivityProfiles(Long crsActyId,String modifyUserId) {
		final String sql=getQuery("PURGE_DELETE_COURSE_ACTIVITY_PROFILES");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "course_activity_profiles";
			String whereCondition = "crs_acty_id = :p_crs_acty_id";
			inputMap.put("p_crs_acty_id", crsActyId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprCourseActivityProfiles" + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_crs_acty_id",crsActyId));
	}
	
	@Override
	public Integer deleteprCourseActivityReviews(Long crsActyId) {
		final String sql=getQuery("PURGE_DELETE_COURSE_ACTIVITY_REVIEWS");
		return namedParameterJdbcTemplate.update(sql, createParams("p_crs_acty_id",crsActyId));
	}
	
	@Override
	public List<CourseSchedules> prCourseSchedules(Long crsActyId) {
		final String sql=getQuery("PURGE_GET_COURSE_SCHEDULES");
		return namedParameterJdbcTemplate.query(sql, createParams("p_crs_acty_id",crsActyId),new BeanPropertyRowMapper<CourseSchedules>(CourseSchedules.class));
	}
	
	@Override
	public List<CourseSchedules> prCourseSchedules1(Long catchUpCrsSchId) {
		final String sql=getQuery("PURGE_GET_COURSE_SCHEDULES1");
		return namedParameterJdbcTemplate.query(sql, createParams("p_catch_up_crs_sch_id",catchUpCrsSchId),new BeanPropertyRowMapper<CourseSchedules>(CourseSchedules.class));
	}
	
	@Override
	public Integer deleteprCourseScheduleStaffs(BigDecimal crsSchId,String modifyUserId) {
		final String sql=getQuery("PURGE_DELETE_COURSE_ACTIVITY_REVIEWS");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "course_activity_reviews";
			String whereCondition = "crs_acty_id = :p_crs_acty_id";
			inputMap.put("p_crs_acty_id", crsSchId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprCourseScheduleStaffs " + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_crs_acty_id",crsSchId));
	}
	
	@Override
	public List<OffenderCurfews> prVideoReviewSummaries(BigDecimal crsSchId) {
		final String sql=getQuery("PURGE_GET_VIDEO_REVIEW_SUMMARIES");
		return namedParameterJdbcTemplate.query(sql, createParams("p_crs_sch_id",crsSchId),new BeanPropertyRowMapper<OffenderCurfews>(OffenderCurfews.class));
	}
	
	@Override
	public List<OffenderCurfews> prVideoReviewSections(Long pVideoReviewSummaryId) {
		final String sql=getQuery("PURGE_GET_VIDEO_REVIEW_SECTIONS");
		return namedParameterJdbcTemplate.query(sql, createParams("p_video_review_summary_id",pVideoReviewSummaryId),new BeanPropertyRowMapper<OffenderCurfews>(OffenderCurfews.class));
	}
	
	@Override
	public Integer deleteprVideoReviewSubsections(String sectionCode, BigDecimal pVideoReviewSummaryId) {
		final String sql=getQuery("PURGE_DELETE_VIDEO_REVIEW_SUBSECTIONS");
		return namedParameterJdbcTemplate.update(sql, createParams("p_crs_acty_id",sectionCode,"p_video_review_summary_id",pVideoReviewSummaryId));
	}
	
	@Override
	public Integer deleteprVideoReviewSections(Long pVideoReviewSummaryId) {
		final String sql=getQuery("PURGE_DELETE_VIDEO_REVIEW_SUBSECTIONS");
		return namedParameterJdbcTemplate.update(sql, createParams("p_video_review_summary_id",pVideoReviewSummaryId));
	}
	
	@Override
	public Integer deleteprVideoReviewSummaries(BigDecimal crsSchId) {
		final String sql=getQuery("PURGE_DELETE_VIDEO_REVIEW_SUMMARIES");
		return namedParameterJdbcTemplate.update(sql, createParams("p_crs_sch_id",crsSchId));
	}
	
	@Override
	public Integer deleteprCourseSchedules(Long crsActyId) {
		final String sql=getQuery("PURGE_DELETE_COURSE_SCHEDULES");
		return namedParameterJdbcTemplate.update(sql, createParams("p_crs_acty_id",crsActyId));
	}
	
	@Override
	public Integer deleteprCourseScheduleRules(Long crsActyId,String modifyUserId) {
		final String sql=getQuery("PURGE_DELETE_COURSE_SCHEDULE_RULES");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "course_schedule_rules";
			String whereCondition = "crs_acty_id = :p_crs_acty_id";
			inputMap.put("p_crs_acty_id", crsActyId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprCourseScheduleRules " + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_crs_acty_id",crsActyId));
	}
	
	@Override
	public List<OffenderCourseAttendances> prOffenderCourseAttendancs3(Long crsActyId) {
		final String sql=getQuery("PURGE_GET_OFFENDER_COURSE_ATTENDANCES3");
		return namedParameterJdbcTemplate.query(sql, createParams("p_crs_acty_id",crsActyId),new BeanPropertyRowMapper<OffenderCourseAttendances>(OffenderCourseAttendances.class));
	}
	
	@Override
	public Integer deleteprOffenderCourseAttendancs3(Long crsActyId) {
		final String sql=getQuery("PURGE_DELETE_OFFENDER_COURSE_ATTENDANCES3");
		return namedParameterJdbcTemplate.update(sql, createParams("p_off_prgref_id",crsActyId));
	}
	@Override
	public List<OffenderProgramProfiles> prOffenderProgramProfiles3(Long crsActyId) {
		final String sql=getQuery("PURGE_GET_OFFENDER_PROGRAM_PROFILES4");
		return namedParameterJdbcTemplate.query(sql, createParams("p_crs_acty_id",crsActyId),new BeanPropertyRowMapper<OffenderProgramProfiles>(OffenderProgramProfiles.class));
	}
	
	@Override
	public Integer deleteprCourseActivities(Long servicesAddressId,String modifyUserId) {
		final String sql=getQuery("PURGE_DELETE_COURSE_ACTIVITIES");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "course_activities";
			String whereCondition = "services_address_id = :p_services_address_id";
			inputMap.put("p_services_address_id", servicesAddressId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprCourseActivities " + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_services_address_id",servicesAddressId));
	}
	
	@Override
	public List<OffenderCurfews> prCurfewAddresses1(BigDecimal pAddressId) {
		final String sql=getQuery("PURGE_GET_CURFEW_ADDRESSES1");
		return namedParameterJdbcTemplate.query(sql, createParams("p_address_id",pAddressId),new BeanPropertyRowMapper<OffenderCurfews>(OffenderCurfews.class));
	}
	
	@Override
	public Integer deleteprCurfewAddresses1(BigDecimal pAddressId) {
		final String sql=getQuery("PURGE_DELETE_CURFEW_ADDRESSES");
		return namedParameterJdbcTemplate.update(sql, createParams("p_address_id",pAddressId));
	}
	
	@Override
	public Integer deleteprOffenderExternalMovemnts1(BigDecimal toAddressId,String modifyUserId) {
		final String sql=getQuery("PURGE_DELETE_OFFENDER_EXTERNAL_MOVEMENTS1");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "offender_external_movements";
			String whereCondition = "to_address_id = :p_to_address_id";
			inputMap.put("p_to_address_id", toAddressId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprOffenderExternalMovemnts1 " + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_to_address_id",toAddressId));
	}
	
	@Override
	public Integer deleteprOffenderExternalMovemnts2(BigDecimal fromAddressId,String modifyUserId) {
		final String sql=getQuery("PURGE_DELETE_OFFENDER_EXTERNAL_MOVEMENTS2");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "offender_external_movements";
			String whereCondition = "from_address_id = :p_from_address_id";
			inputMap.put("p_from_address_id", fromAddressId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprOffenderExternalMovemnts2 " + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_to_address_id",fromAddressId));
	}
	
	@Override
	public Integer deleteprOffenderMailLogs1(BigDecimal pMailAddressId,String modifyUserId) {
		final String sql=getQuery("PURGE_DELETE_OFFENDER_MAIL_LOGS");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "offender_mail_logs";
			String whereCondition = "mail_address_id = :p_mail_address_id";
			inputMap.put("p_mail_address_id", pMailAddressId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprOffenderMailLogs1 " + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_mail_address_id",pMailAddressId));
	}
	
	@Override
	public Integer deleteprOffenderMailRestrictions1(BigDecimal pRestrictionAddressId,String modifyUserId) {
		final String sql=getQuery("PURGE_DELETE_OFFENDER_MAIL_RESTRICTIONS1");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "offender_mail_restrictions";
			String whereCondition = "restriction_address_id = :p_restriction_address_id";
			inputMap.put("p_restriction_address_id", pRestrictionAddressId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprOffenderMailRestrictions1 " + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_restriction_address_id",pRestrictionAddressId));
	}
	
	@Override
	public Integer deleteprPhones(BigDecimal ownerId, String ownerClass) {
		final String sql=getQuery("PURGE_DELETE_PHONES_PURGE");
		return namedParameterJdbcTemplate.update(sql, createParams("p_owner_id",ownerId,"p_owner_class",ownerClass));
	}
	
	@Override
	public Integer deleteprAddresses(BigDecimal ownerId, String ownerClass) {
		final String sql=getQuery("PURGE_DELETE_ADDRESSES");
		return namedParameterJdbcTemplate.update(sql, createParams("p_owner_id",ownerId,"p_owner_class",ownerClass));
	}
	
	@Override
	public Integer deleteprInternetAddresses(BigDecimal ownerId, String ownerClass) {
		final String sql=getQuery("PURGE_DELETE_INTERNET_ADDRESSES");
		return namedParameterJdbcTemplate.update(sql, createParams("p_owner_id",ownerId,"p_owner_class",ownerClass));
	}
	
	@Override
	public List<Images> prImages(BigDecimal imageObjectId, String imageObjectType) {
		final String sql=getQuery("PURGE_GET_IMAGES");
		return namedParameterJdbcTemplate.query(sql, createParams("p_image_object_id",imageObjectId,"p_image_object_type",imageObjectType),new BeanPropertyRowMapper<Images>(Images.class));
	}
	
	@Override
	public Integer deleteprImageOriginals(Long imageId) {
		final String sql=getQuery("PURGE_DELETE_IMAGE_ORIGINALS");
		return namedParameterJdbcTemplate.update(sql, createParams("p_image_id",imageId));
	}
	
	@Override
	public Integer deleteprImageProperties(Integer imageId) {
		final String sql=getQuery("PURGE_DELETE_IMAGE_PROPERTIES");
		return namedParameterJdbcTemplate.update(sql, createParams("p_image_id",imageId));
	}
	
	@Override
	public Integer deleteprImages(BigDecimal imageObjectId, String imageObjectType) {
		final String sql=getQuery("PURGE_DELETE_IMAGES");
		return namedParameterJdbcTemplate.update(sql, createParams("p_image_object_id",imageObjectId,"p_image_object_type",imageObjectType));
	}
	
	@Override
	public Integer deleteprOffenderCaseAssociations(Long offenderBookId) {
		final String sql=getQuery("PURGE_DELETE_OFFENDER_CASE_ASSOCIATIONS_PURGE");
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_book_id",offenderBookId));
	}
	
	@Override
	public Integer deleteprOffenderCaseAssociations1(BigDecimal pAssociatedOffBookId) {
		final String sql=getQuery("PURGE_DELETE_OFFENDER_CASE_ASSOCIATIONS1_PURGE");
		return namedParameterJdbcTemplate.update(sql, createParams("p_associated_off_book_id",pAssociatedOffBookId));
	}
	
	@Override
	public List<WorkFlows> prWorkFlows(BigDecimal objectId) {
		final String sql=getQuery("PURGE_GET_WORK_FLOWS");
		return namedParameterJdbcTemplate.query(sql, createParams("p_object_id",objectId),new BeanPropertyRowMapper<WorkFlows>(WorkFlows.class));
	}
	
	@Override
	public Integer deleteprWorkFlowLogs(Long workFlowId) {
		final String sql=getQuery("PURGE_DELETE_WORK_FLOW_LOGS");
		return namedParameterJdbcTemplate.update(sql, createParams("p_work_flow_id",workFlowId));
	}
	
	@Override
	public Integer deleteprWorkFlows(BigDecimal objectId) {
		final String sql=getQuery("PURGE_DELETE_WORK_FLOWS");
		return namedParameterJdbcTemplate.update(sql, createParams("p_object_id",objectId));
	}
	
	@Override
	public Integer deleteprOffenderProfileDetails(Long offenderBookId) {
		final String sql=getQuery("PURGE_DELETE_OFFENDER_PROFILE_DETAILS");
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_book_id",offenderBookId));
	}
	
	@Override
	public Integer deleteprOffenderBillingProfiles(Long offenderBookingId) {
		final String sql=getQuery("PURGE_DELETE_OFFENDER_BILLING_PROFILES");
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_booking_id",offenderBookingId));
	}
	
	@Override
	public List<QmActivitiesIns> prQmProcessesIns(String detail) {
		final String sql=getQuery("PURGE_GET_QM_PROCESSES_INS");
		return namedParameterJdbcTemplate.query(sql, createParams("p_detail",detail),new BeanPropertyRowMapper<QmActivitiesIns>(QmActivitiesIns.class));
	}
	
	@Override
	public Integer deleteprQmActivitiesIns(BigDecimal pProcessInsId) {
		final String sql=getQuery("PURGE_DELETE_OFFENDER_BILLING_PROFILES");
		return namedParameterJdbcTemplate.update(sql, createParams("p_process_ins_id",pProcessInsId));
	}
	
	@Override
	public Integer deleteprQmObjectIns(BigDecimal pProcessInsId) {
		final String sql=getQuery("PURGE_DELETE_QM_OBJECT_INS");
		return namedParameterJdbcTemplate.update(sql, createParams("p_process_ins_id",pProcessInsId));
	}
	
	@Override
	public Integer deleteprQmProcessesIns(String detail) {
		final String sql=getQuery("PURGE_DELETE_QM_PROCESSES_INS");
		return namedParameterJdbcTemplate.update(sql, createParams("p_detail",detail));
	}
	
	@Override
	public List<QmActivitiesIns> prQmProcessesInsHist(String detail) {
		final String sql=getQuery("PURGE_GET_QM_PROCESSES_INS_HIST");
		return namedParameterJdbcTemplate.query(sql, createParams("p_detail",detail),new BeanPropertyRowMapper<QmActivitiesIns>(QmActivitiesIns.class));
	}
	
	@Override
	public Integer deleteprQmActivitiesInsHist(BigDecimal pProcessInsId) {
		final String sql=getQuery("PURGE_DELETE_QM_ACTIVITIES_INS_HIST");
		return namedParameterJdbcTemplate.update(sql, createParams("p_process_ins_id",pProcessInsId));
	}
	
	@Override
	public Integer deleteprQmObjectInsHist(BigDecimal pProcessInsId) {
		final String sql=getQuery("PURGE_DELETE_QM_OBJECT_INS_HIST");
		return namedParameterJdbcTemplate.update(sql, createParams("p_process_ins_id",pProcessInsId));
	}
	
	@Override
	public Integer deleteprOffenderSupervisingCourts(BigDecimal offenderBookId) {
		final String sql=getQuery("PURGE_DELETE_OFFENDER_SUPERVISING_COURTS");
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_book_id",offenderBookId));
	}
	
	@Override
	public Integer deleteproffenderrestrictions(BigDecimal offenderBookId) {
		final String sql=getQuery("PURGE_DELETE_OFFENDER_RESTRICTIONS_DELETE");
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_book_id",offenderBookId));
	}
	
	@Override
	public Integer deleteprOffenderBookings(Long pOffenderBookId) {
		final String sql=getQuery("PURGE_DELETE_OFFENDER_BOOKINGS_PURGE");
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_id",pOffenderBookId));
	}
	@Override
	public Integer deleteprOffenderOffIncentivesEarnPrivs(Long offenderBookId) {
		final String sql=getQuery("PURGE_DELETE_OFFENDER_OFF_INCENTIVES_EARN_PRIVS");
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_book_id",offenderBookId));
	}

	@Override
	public Integer purgeDeleteOffenderCustodyStatus(Long offenderBookId,String modifyUserId) {
		final String sql=getQuery("PURGE_DELETE_OFFENDER_CUSTODY_STATUS");
		try {
			String tableName = "offender_associated_parties";
			String whereClause = "offender_book_id = :p_offender_book_id";
			Map<String , Object> inputMap = new HashMap<String, Object>();
			inputMap.put("p_offender_book_id", offenderBookId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereClause , inputMap);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method deleteprOffenderAssociatedParties", e);
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_book_id",offenderBookId));
	}
}

package net.syscon.s4.pkgs.purge_offender.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.cm.programsservices.OffenderCourseApptGrp;
import net.syscon.s4.common.beans.BioSubjectsUpd;
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
import net.syscon.s4.im.beans.Addresses;
import net.syscon.s4.im.beans.CasePlans;
import net.syscon.s4.im.beans.CourseActivities;
import net.syscon.s4.im.beans.OffenderCaseNotes;
import net.syscon.s4.im.beans.OffenderCases;
import net.syscon.s4.im.beans.OffenderGrievances;
import net.syscon.s4.im.beans.OffenderNonAssociations;
import net.syscon.s4.im.beans.OffenderPrgObligations;
import net.syscon.s4.im.beans.OffenderProgramProfiles;
import net.syscon.s4.im.beans.OffenderStgAffiliations;
import net.syscon.s4.im.beans.OffenderTransactions;
import net.syscon.s4.im.beans.OffenderTrustAccounts;
import net.syscon.s4.im.beans.Offenders;
import net.syscon.s4.im.beans.ReleasePlans;
import net.syscon.s4.im.incidentsoic.beans.AgencyIncidentCharges;
import net.syscon.s4.im.incidentsoic.beans.AgencyIncidentParties;
import net.syscon.s4.im.incidentsoic.beans.OicHearingResults;
import net.syscon.s4.inmate.beans.OffenderBeneficiaries;
import net.syscon.s4.inmate.beans.OffenderDeductions;
import net.syscon.s4.inst.booking.beans.OffenderContactPersons;
import net.syscon.s4.inst.casemanagement.beans.OffenderCriminogenicNeeds;
import net.syscon.s4.inst.casemanagement.beans.PlanDetails;
import net.syscon.s4.inst.classification.beans.AssessmentResults;
import net.syscon.s4.inst.incidentsoic.maintenance.OffenderOicAppealIncidents;
import net.syscon.s4.inst.institutionalactivities.maintenance.beans.CourseSchedules;
import net.syscon.s4.inst.legals.beans.OffenderProceedings;
import net.syscon.s4.inst.legals.beans.OffenderSentences;
import net.syscon.s4.inst.legalscreens.maintenance.bean.OffenderCharges;
import net.syscon.s4.inst.property.bean.OffenderPptyItemEvents;
import net.syscon.s4.inst.schedules.bean.CourtEvents;
import net.syscon.s4.inst.schedules.bean.OffenderIndSchedules;
import net.syscon.s4.inst.visitsmanagement.beans.VOffenderVisits;
import net.syscon.s4.pkgs.OffenderAssociatedParties;
import net.syscon.s4.pkgs.purge_offender.PurgeOffenderRepository;
import net.syscon.s4.triggers.HdcRequestReferrals;
import net.syscon.s4.triggers.OffenderCourseAttendances;
import net.syscon.s4.triggers.OffenderOasysSections;
import net.syscon.s4.triggers.OffenderPtr;
@Repository
public class PurgeOffenderRepositoryImpl extends RepositoryBase implements PurgeOffenderRepository {

	private final Map<String, FieldMapper> courtListMapping = new ImmutableMap.Builder<String, FieldMapper>().build();
	private static Logger logger = LogManager.getLogger(PurgeOffenderRepositoryImpl.class.getName());
	

	@Override
	public List<Offenders> prOffenders(Long offenderId) {
		String sql=getQuery("BOOKING_GET_OFFENDERS_BOOKING");
		return namedParameterJdbcTemplate.query(sql, createParams("p_offender_id",offenderId),new BeanPropertyRowMapper<Offenders>(Offenders.class));
	}

	@Override
	public List<BioSubjectsUpd> prBioSubjects(BigDecimal pRootOffenderId) {
		String sql=getQuery("BOOKING_GET_BIO_SUBJECTS");
		return namedParameterJdbcTemplate.query(sql, createParams("p_root_offender_id",pRootOffenderId),new BeanPropertyRowMapper<BioSubjectsUpd>(BioSubjectsUpd.class));
	}

	@Override
	public List<BioSubjectsUpd> prBioScans(BigDecimal pSubjectId) {
		String sql=getQuery("BOOKING_GET_BIO_SCANS");
		RowMapper<BioSubjectsUpd> mapper= Row2BeanRowMapper.makeMapping(sql, BioSubjectsUpd.class, courtListMapping);
		return namedParameterJdbcTemplate.query(sql, createParams("p_subject_id",pSubjectId),mapper);
	}

	@Override
	public Integer deleteprBioFpSamples(BigDecimal pScanId, BigDecimal pSubjectId,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_BIO_FP_SAMPLES");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "bio_fp_samples";
			String whereCondition = "scan_id = :p_scan_id AND subject_id = :p_subject_id";
			inputMap.put("p_scan_id", pScanId);
			inputMap.put("p_subject_id", pSubjectId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprBioFpSamples" + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_scan_id",pScanId,"p_subject_id",pSubjectId));
	}

	@Override
	public Integer deleteprBioScans(BigDecimal pSubjectId,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_BIO_SCANS");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "bio_scans";
			String whereCondition = "subject_id = p_subject_id";
			inputMap.put("p_subject_id", pSubjectId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprBioScans" + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_subject_id",pSubjectId));
	}

	@Override
	public Integer deleteprBioEventLogs(BigDecimal pSubjectId,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_BIO_EVENT_LOGS");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "bio_event_logs";
			String whereCondition = "subject_id = :p_subject_id";
			inputMap.put("p_subject_id", pSubjectId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprBioEventLogs" + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_subject_id",pSubjectId));
	}

	@Override
	public Integer deleteprBioSubjects(BigDecimal pRootOffenderId,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_BIO_SUBJECTS");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "bio_subjects";
			String whereCondition = "root_offender_id = :p_root_offender_id";
			inputMap.put("p_root_offender_id", pRootOffenderId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprBioSubjects" + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_root_offender_id",pRootOffenderId));
	}

	@Override
	public Integer updateprGlTransactions(BigDecimal offenderId,String modifyUserId) {
		String sql=getQuery("BOOKING_UPDATE_GL_TRANSACTIONS");
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_id",offenderId));
	}

	@Override
	public List<Offenders> prOffenders1(Long aliasOffenderId) {
		String sql=getQuery("BOOKING_GET_OFFENDERS1");
		return namedParameterJdbcTemplate.query(sql, createParams("p_alias_offender_id",aliasOffenderId),new BeanPropertyRowMapper<Offenders>(Offenders.class));
	}

	@Override
	public Integer deleteprOffenderBalances(BigDecimal offenderId,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_OFFENDER_BALANCES");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "offender_balances";
			String whereCondition = "offender_id = :p_offender_id";
			inputMap.put("p_offender_id", offenderId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprOffenderBalances" + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_id",offenderId));
	}

	@Override
	public List<OffenderBookings> prOffenderBookings(BigDecimal offenderId) {
		String sql=getQuery("BOOKING_GET_OFFENDER_BOOKINGS");
		return namedParameterJdbcTemplate.query(sql, createParams("p_offender_id",offenderId),new BeanPropertyRowMapper<OffenderBookings>(OffenderBookings.class));
	}

	@Override
	public List<AgencyIncidentParties> prAgencyIncidentParties(Integer offenderBookId) {
		String sql=getQuery("BOOKING_GET_AGENCY_INCIDENT_PARTIES");
		RowMapper<AgencyIncidentParties> mapper= Row2BeanRowMapper.makeMapping(sql, AgencyIncidentParties.class, courtListMapping);
		return namedParameterJdbcTemplate.query(sql, createParams("p_offender_book_id",offenderBookId),mapper);
	}	

	@Override
	public List<AgencyIncidentCharges> prAgencyIncidentCharges(Integer agencyIncidentId, Integer partySeq) {
		String sql=getQuery("BOOKING_GET_AGENCY_INCIDENT_CHARGES");
		RowMapper<AgencyIncidentCharges> mapper= Row2BeanRowMapper.makeMapping(sql, AgencyIncidentCharges.class, courtListMapping);
		return namedParameterJdbcTemplate.query(sql, createParams("p_agency_incident_id",agencyIncidentId,"p_party_seq",partySeq),mapper);
	}

	@Override
	public List<OicHearingResults> prOicHearingResults(BigDecimal agencyIncidentId, BigDecimal chargeSeq) {
		String sql=getQuery("BOOKING_GET_OIC_HEARING_RESULTS");
		RowMapper<OicHearingResults> mapper= Row2BeanRowMapper.makeMapping(sql, OicHearingResults.class, courtListMapping);
		return namedParameterJdbcTemplate.query(sql, createParams("p_agency_incident_id",agencyIncidentId,"p_charge_seq",chargeSeq),mapper);
	}

	@Override
	public List<OffenderOicAppealIncidents> proffenderoicappelincidnts(Integer oicHearingId, Integer resultSeq) {
		String sql=getQuery("BOOKING_GET_OFFENDER_OIC_APPEAL_INCIDENTS");
		RowMapper<OffenderOicAppealIncidents> mapper= Row2BeanRowMapper.makeMapping(sql, OffenderOicAppealIncidents.class, courtListMapping);
		return namedParameterJdbcTemplate.query(sql, createParams("p_oic_hearing_id",oicHearingId,"p_result_seq",resultSeq),mapper);
	}

	@Override
	public Integer deleteprOffenderOicAppealPenltis(Integer oicApprealId, Integer oicHearingId, Integer resultSeq,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_OFFENDER_OIC_APPEAL_PENALTIES");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "offender_oic_appeal_penalties";
			String whereCondition = "oic_appreal_id = :p_oic_appreal_id AND oic_hearing_id = :p_oic_hearing_id AND result_seq = :p_result_seq";
			inputMap.put("p_oic_appreal_id", oicApprealId);
			inputMap.put("p_oic_hearing_id", oicHearingId);
			inputMap.put("p_result_seq", resultSeq);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprOffenderOicAppealPenltis" + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_oic_appreal_id",oicApprealId,"p_oic_hearing_id",oicHearingId,"p_result_seq",resultSeq));
	}

	@Override
	public Integer deleteproffenderoicappelincidnts(Integer oicHearingId, Integer resultSeq,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_OFFENDER_OIC_APPEAL_INCIDENTS");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "offender_oic_appeal_incidents";
			String whereCondition = "oic_hearing_id = :p_oic_hearing_id AND result_seq = :p_result_seq";
			inputMap.put("p_oic_hearing_id", oicHearingId);
			inputMap.put("p_result_seq", resultSeq);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteproffenderoicappelincidnts" + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_oic_hearing_id",oicHearingId,"p_result_seq",resultSeq));
	}

	@Override
	public Integer deleteprOffenderOicSancReviews(BigDecimal offenderBookId, Long pSanctionSeq,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_OFFENDER_OIC_SANC_REVIEWS");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "offender_oic_sanc_reviews";
			String whereCondition = "offender_book_id = :p_offender_book_id AND sanction_seq = :p_sanction_seq";
			inputMap.put("p_offender_book_id", offenderBookId);
			inputMap.put("p_sanction_seq", pSanctionSeq);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprOffenderOicSancReviews" + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_book_id",offenderBookId,"p_sanction_seq",pSanctionSeq));
	}

	@Override
	public List<OffenderOicSanctions> prOffenderOicSanctions2(Integer pConsecutivOffndrBookId,Integer pConsecutiveSanctionSeq) {
		String sql=getQuery("BOOKING_GET_OFFENDER_OIC_SANCTIONS");
		RowMapper<OffenderOicSanctions> mapper= Row2BeanRowMapper.makeMapping(sql, OffenderOicSanctions.class, courtListMapping);
		return namedParameterJdbcTemplate.query(sql, createParams("p_consecutiv_offndr_book_id",pConsecutivOffndrBookId,"p_consecutive_sanction_seq",pConsecutiveSanctionSeq),mapper);
	}

	@Override
	public Integer deleteprOffenderOicSanctions2(Integer pConsecutivOffndrBookId, Integer pConsecutiveSanctionSeq,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_OFFENDER_OIC_SANCTIONS");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "offender_oic_sanctions";
			String whereCondition = "consecutive_offender_book_id = :p_consecutiv_offndr_book_id AND consecutive_sanction_seq = :p_consecutive_sanction_seq";
			inputMap.put("p_consecutiv_offndr_book_id", pConsecutivOffndrBookId);
			inputMap.put("p_consecutive_sanction_seq", pConsecutiveSanctionSeq);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprOffenderOicSanctions2" + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_consecutiv_offndr_book_id",pConsecutivOffndrBookId,"p_consecutive_sanction_seq",pConsecutiveSanctionSeq));
	}

	@Override
	public List<OffenderOicSanctions> purgeoffenderrepository(Integer oicHearingId, Integer resultSeq) {
		String sql=getQuery("BOOKING_GET_OFFENDER_OIC_SANCTIONS1");
		RowMapper<OffenderOicSanctions> mapper= Row2BeanRowMapper.makeMapping(sql, OffenderOicSanctions.class, courtListMapping);
		return namedParameterJdbcTemplate.query(sql, createParams("p_oic_hearing_id",oicHearingId,"p_result_seq",resultSeq),mapper);
	}

	@Override
	public Integer deleteprOffenderOicSanctions1(Integer oicHearingId, Integer resultSeq,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_OFFENDER_OIC_SANCTIONS1");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "offender_oic_sanctions";
			String whereCondition = "oic_hearing_id = :p_oic_hearing_id AND result_seq = :p_result_seq";
			inputMap.put("p_oic_hearing_id", oicHearingId);
			inputMap.put("p_result_seq", resultSeq);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprOffenderOicSanctions1" + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_oic_hearing_id",oicHearingId,"p_result_seq",resultSeq));
	}

	@Override
	public Integer deleteprOffenderCaseNoteSents(Long offenderBookId, Long sentenceSeq,String modifyUserId) {
		String sql = getQuery("BOOKING_DELETE_OFFENDER_CASE_NOTE_SENTS");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "offender_case_note_sents";
			String whereCondition = "offender_book_id = :p_offender_book_id AND sentence_seq = :p_sentence_seq";
			inputMap.put("p_offender_book_id", offenderBookId);
			inputMap.put("p_sentence_seq", sentenceSeq);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);

		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprOffenderCaseNoteSents" + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql,createParams("p_offender_book_id", offenderBookId, "p_sentence_seq", sentenceSeq));
	}

	@Override
	public Integer deleteprOffndrCommnityConditions1(Long offenderBookId, Long sentenceSeq,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_OFFENDER_COMMUNITY_CONDITIONS");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "offender_community_conditions";
			String whereCondition = "offender_book_id = :p_offender_book_id AND sentence_seq = :p_sentence_seq";
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
		String sql=getQuery("BOOKING_DELETE_OFFENDER_IND_SCH_SENTS");
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
		String sql=getQuery("BOOKING_DELETE_OFFENDER_LICENCE_SENTENCES");
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
	public Integer deleteprOffenderLicenceSentences2(Long offenderBookId, Long pLicenceSentenceSeq,String modifyUserId) {
		String sql=getQuery("DELETE_OFFENDER_LICENCE_SENTENCES1");
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_book_id",offenderBookId,"p_licence_sentence_seq",pLicenceSentenceSeq));
	}

	@Override
	public Integer deleteprOffenderProceedingSents(Long offenderBookId, Long sentenceSeq,String modifyUserId) {
		String sql = getQuery("BOOKING_DELETE_OFFENDER_PROCEEDING_SENTS");
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
	public Integer deleteprOffenderSentenceAdjusts(Long offenderBookId, Long sentenceSeq,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_OFFENDER_SENTENCE_ADJUSTS");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "offender_sentence_adjusts";
			String whereCondition = "offender_book_id = :p_offender_book_id AND sentence_seq = :p_sentence_seq";
			inputMap.put("p_offender_book_id", offenderBookId);
			inputMap.put("p_sentence_seq", sentenceSeq);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprOffenderSentenceAdjusts" + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_book_id",offenderBookId,"p_sentence_seq",sentenceSeq));
	}

	@Override
	public Integer deleteprOffenderSentenceCharges1(Long offenderBookId, Long sentenceSeq,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_OFFENDER_SENTENCE_CHARGES");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "offender_sentence_charges";
			String whereCondition = "offender_book_id = :p_offender_book_id AND sentence_seq = :p_sentence_seq";
			inputMap.put("p_offender_book_id", offenderBookId);
			inputMap.put("p_sentence_seq", sentenceSeq);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprOffenderSentenceCharges1" + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_book_id",offenderBookId,"p_sentence_seq",sentenceSeq));
	}

	@Override
	public Integer deleteprOffenderSentenceHty(Long offenderBookId, Long sentenceSeq,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_OFFENDER_SENTENCE_HTY");
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
		String sql=getQuery("BOOKING_DELETE_OFFENDER_SENTENCE_STATUSES");
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
		String sql=getQuery("BOOKING_DELETE_OFFENDER_SENTENCE_TERMS");
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
		String sql=getQuery("BOOKING_DELETE_OFFENDER_SENTENCE_UA_EVENTS");
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
		String sql=getQuery("BOOKING_DELETE_OFFENDER_UNPAID_WORK_ADJ");
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
	public List<OffenderSentences> prOffenderSentences1(BigDecimal orderId) {
		String sql=getQuery("BOOKING_GET_OFFENDER_SENTENCES");
		RowMapper<OffenderSentences> mapper= Row2BeanRowMapper.makeMapping(sql, OffenderSentences.class, courtListMapping);
		return namedParameterJdbcTemplate.query(sql, createParams("p_order_id",orderId),mapper);
	}

	@Override
	public Integer deleteprCourtEventSentences1(Long offenderBookId, Long sentenceSeq,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_COURT_EVENT_SENTENCES");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "court_event_sentences";
			String whereCondition = "offender_book_id = :p_offender_book_id AND sentence_seq = :p_sentence_seq";
			inputMap.put("p_offender_book_id", offenderBookId);
			inputMap.put("p_sentence_seq", sentenceSeq);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprCourtEventSentences1" + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_book_id",offenderBookId,"p_sentence_seq",sentenceSeq));
	}


	@Override
	public Integer deleteprOffenderSentences1(BigDecimal orderId,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_OFFENDER_SENTENCES");
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
	public Integer deleteprOffenderCommunityFiles(BigDecimal offenderId,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_OFFENDER_COMMUNITY_FILES");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "offender_community_files";
			String whereCondition = "offender_id = :p_offender_id";
			inputMap.put("p_offender_id", offenderId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprOffenderCommunityFiles" + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_id",offenderId));
	}

	@Override
	public Integer deleteprOffenderIdentifiers(BigDecimal offenderId,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_OFFENDER_IDENTIFIERS");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "offender_identifiers";
			String whereCondition = "offender_id = :p_offender_id";
			inputMap.put("p_offender_id", offenderId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprOffenderIdentifiers" + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_id",offenderId));
	}

	@Override
	public Integer deleteprOffenderObliVerifications(BigDecimal offenderId,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_OFFENDER_OBLI_VERIFICATIONS");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "offender_obli_verifications";
			String whereCondition = "offender_id = :p_offender_id";
			inputMap.put("p_offender_id", offenderId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprOffenderObliVerifications" + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_id",offenderId));
	}

	@Override
	public List<OffenderDeductions> prOffenderPaymentProfiles(BigDecimal offenderId) {
		String sql=getQuery("BOOKING_GET_OFFENDER_PAYMENT_PROFILES");
		RowMapper<OffenderDeductions> mapper= Row2BeanRowMapper.makeMapping(sql, OffenderDeductions.class, courtListMapping);
		return namedParameterJdbcTemplate.query(sql, createParams("p_offender_id",offenderId),mapper);
	}

	@Override
	public List<OffenderDeductions> prOffenderDeductions(BigDecimal offenderPaymentProfileId) {
		String sql=getQuery("BOOKING_GET_OFFENDER_DEDUCTIONS");
		RowMapper<OffenderDeductions> mapper= Row2BeanRowMapper.makeMapping(sql, OffenderDeductions.class, courtListMapping);
		return namedParameterJdbcTemplate.query(sql, createParams("p_offender_paymnt_profil_id",offenderPaymentProfileId),mapper);
	}

	@Override
	public Integer deleteprOffenderAdjustmentTxns(String adjustmentUserId,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_OFFENDER_ADJUSTMENT_TXNS");
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_deduction_id",adjustmentUserId));
	}

	@Override
	public List<OffenderBeneficiaries> prOffenderBeneficiaries(Long offenderDeductionId) {
		String sql=getQuery("BOOKING_GET_OFFENDER_BENEFICIARIES");
		RowMapper<OffenderBeneficiaries> mapper= Row2BeanRowMapper.makeMapping(sql, OffenderBeneficiaries.class, courtListMapping);
		return namedParameterJdbcTemplate.query(sql, createParams("p_offender_deduction_id",offenderDeductionId),mapper);
	}

	@Override
	public Integer deleteprBeneficiaryTransactions(BigDecimal beneficiaryId,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_BENEFICIARY_TRANSACTIONS");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "beneficiary_transactions";
			String whereCondition = "beneficiary_id = :p_beneficiary_id";
			inputMap.put("p_beneficiary_id", beneficiaryId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprBeneficiaryTransactions" + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_beneficiary_id",beneficiaryId));
	}

	@Override
	public Integer deleteprOffenderCrditPriorPymnts(BigDecimal beneficiaryId,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_OFFENDER_CREDIT_PRIOR_PAYMENTS");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "offender_credit_prior_payments";
			String whereCondition = "beneficiary_id = :p_beneficiary_id";
			inputMap.put("p_beneficiary_id", beneficiaryId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprOffenderCrditPriorPymnts" + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_beneficiary_id",beneficiaryId));
	}

	@Override
	public Integer deleteprOffenderMonBeneficiaries1(BigDecimal beneficiaryId,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_OFFENDER_MON_BENEFICIARIES");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "offender_mon_beneficiaries";
			String whereCondition = "beneficiary_id = :p_beneficiary_id";
			inputMap.put("p_beneficiary_id", beneficiaryId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprOffenderMonBeneficiaries1" + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_beneficiary_id",beneficiaryId));
	}

	@Override
	public Integer deleteprOffenderBeneficiaries(Long offenderDeductionId,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_OFFENDER_BENEFICIARIES");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "offender_beneficiaries";
			String whereCondition = "offender_deduction_id = :p_offender_deduction_id";
			inputMap.put("p_offender_deduction_id", offenderDeductionId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprOffenderBeneficiaries" + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_deduction_id",offenderDeductionId));
	}

	@Override
	public Integer deleteprOffenderDeductionReceipts(Long offenderDeductionId,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_OFFENDER_DEDUCTION_RECEIPTS");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "offender_deduction_receipts";
			String whereCondition = "offender_deduction_id = :p_offender_deduction_id";
			inputMap.put("p_offender_deduction_id", offenderDeductionId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprOffenderDeductionReceipts" + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_deduction_id",offenderDeductionId));
	}

	@Override
	public Integer deleteprOffenderDeductionShadows(Long offenderDeductionId,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_OFFENDER_DEDUCTION_SHADOWS");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "offender_deduction_shadows";
			String whereCondition = "offender_deduction_id = :p_offender_deduction_id";
			inputMap.put("p_offender_deduction_id", offenderDeductionId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprOffenderDeductionShadows" + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_deduction_id",offenderDeductionId));
	}

	@Override
	public Integer deleteprOffenderMonBeneficiaries(Long offenderDeductionId,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_OFFENDER_MON_BENEFICIARIES1");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "offender_mon_beneficiaries";
			String whereCondition = "offender_deduction_id = :p_offender_deduction_id";
			inputMap.put("p_offender_deduction_id", offenderDeductionId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprOffenderMonBeneficiaries" + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_deduction_id",offenderDeductionId));
	}

	@Override
	public Integer deleteprOffenderMonDeductions(Long offenderDeductionId,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_OFFENDER_MON_DEDUCTIONS");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "offender_mon_deductions";
			String whereCondition = "offender_deduction_id = :p_offender_deduction_id";
			inputMap.put("p_offender_deduction_id", offenderDeductionId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprOffenderMonDeductions" + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_deduction_id",offenderDeductionId));
	}

	@Override
	public Integer deleteprOffenderTierTxnFeeMonts(Long offenderDeductionId,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_OFFENDER_TIER_TXN_FEE_AMOUNTS");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "offender_tier_txn_fee_amounts";
			String whereCondition = "offender_deduction_id = :p_offender_deduction_id";
			inputMap.put("p_offender_deduction_id", offenderDeductionId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprOffenderTierTxnFeeMonts" + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_deduction_id",offenderDeductionId));
	}

	@Override
	public Integer deleteprOffenderTxnFeeDetails(Long offenderDeductionId,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_OFFENDER_TXN_FEE_DETAILS");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "offender_txn_fee_details";
			String whereCondition = "offender_deduction_id = :p_offender_deduction_id";
			inputMap.put("p_offender_deduction_id", offenderDeductionId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprOffenderTxnFeeDetails" + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_deduction_id",offenderDeductionId));
	}

	@Override
	public List<OffenderDeductions> prOffenderDeductions1(BigDecimal parentDeductionId) {
		String sql=getQuery("BOOKING_GET_OFFENDER_DEDUCTIONS1");
		RowMapper<OffenderDeductions> mapper= Row2BeanRowMapper.makeMapping(sql, OffenderDeductions.class, courtListMapping);
		return namedParameterJdbcTemplate.query(sql, createParams("p_parent_deduction_id",parentDeductionId),mapper);
	}

	@Override
	public Integer deleteprOffenderDeductions1(BigDecimal parentDeductionId,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_OFFENDER_DEDUCTIONS");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "offender_deductions";
			String whereCondition = "parent_deduction_id = :p_parent_deduction_id";
			inputMap.put("p_parent_deduction_id", parentDeductionId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprOffenderDeductions1" + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_parent_deduction_id",parentDeductionId));
	}

	@Override
	public Integer deleteprOffenderDeductions(BigDecimal offenderPaymentProfileId,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_OFFENDER_DEDUCTIONS1");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "offender_deductions";
			String whereCondition = "offender_payment_profile_id = :p_offender_paymnt_profil_id";
			inputMap.put("p_offender_paymnt_profil_id", offenderPaymentProfileId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprOffenderDeductions" + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_paymnt_profil_id",offenderPaymentProfileId));
	}

	@Override
	public Integer deleteprOffenderPlacementScores(BigDecimal rootOffenderId,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_OFFENDER_PLACEMENT_SCORES");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "offender_placement_scores";
			String whereCondition = "root_offender_id = :p_root_offender_id";
			inputMap.put("p_root_offender_id", rootOffenderId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprOffenderPlacementScores" + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_root_offender_id",rootOffenderId));
	}

	@Override
	public List<OffenderTransactions> prOffenderTransactions(Long offenderId,String modifyUserId) {
		String sql=getQuery("BOOKING_GET_OFFENDER_DEDUCTIONS1");
		RowMapper<OffenderTransactions> mapper= Row2BeanRowMapper.makeMapping(sql, OffenderTransactions.class, courtListMapping);
		return namedParameterJdbcTemplate.query(sql, createParams("p_parent_deduction_id",offenderId),mapper);
	}

	@Override
	public Integer deleteprOffendrCrditPriorPymnts1(Long txnId, Long txnEntrySeq,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_OFFENDER_CREDIT_PRIOR_PAYMENTS1");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "offender_credit_prior_payments";
			String whereCondition = "txn_id = :p_txn_id AND txn_entry_seq = :p_txn_entry_seq";
			inputMap.put("p_txn_id", txnId);
			inputMap.put("p_txn_entry_seq", txnEntrySeq);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprOffendrCrditPriorPymnts1" + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_txn_id",txnId,"p_txn_entry_seq",txnEntrySeq));
	}

	@Override
	public Integer deleteprOffenderPayrollTxns(Long txnId, Long txnEntrySeq,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_OFFENDER_PAYROLL_TXNS");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "offender_payroll_txns";
			String whereCondition = "txn_id = :p_txn_id AND txn_entry_seq = :p_txn_entry_seq";
			inputMap.put("p_txn_id", txnId);
			inputMap.put("p_txn_entry_seq", txnEntrySeq);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprOffenderPayrollTxns" + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_txn_id",txnId,"p_txn_entry_seq",txnEntrySeq));
	}

	@Override
	public List<OffenderTransactions> prOffenderTransactions1(Long txnId, Long txnEntrySeq) {
		String sql=getQuery("BOOKING_GET_OFFENDER_TRANSACTIONS1");
		RowMapper<OffenderTransactions> mapper= Row2BeanRowMapper.makeMapping(sql, OffenderTransactions.class, courtListMapping);
		return namedParameterJdbcTemplate.query(sql, createParams("p_adjust_txn_id",txnId,"p_adjust_txn_entry_id",txnEntrySeq),mapper);
	}

	@Override
	public Integer deleteprOffenderTransactions1(Long txnId, Long txnEntrySeq,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_OFFENDER_TRANSACTIONS");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "offender_transactions";
			String whereCondition = "adjust_txn_id = :p_adjust_txn_id AND adjust_txn_entry_id = :p_adjust_txn_entry_id";
			inputMap.put("p_adjust_txn_id", txnId);
			inputMap.put("p_adjust_txn_entry_id", txnEntrySeq);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprOffenderTransactions1" + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_adjust_txn_id",txnId,"p_adjust_txn_entry_id",txnEntrySeq));
	}

	@Override
	public Integer deleteprOffenderTransactions(Long offenderId,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_OFFENDER_TRANSACTIONS1");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "offender_transactions";
			String whereCondition = "offender_id = :p_offender_id";
			inputMap.put("p_offender_id", offenderId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprOffenderTransactions" + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_id",offenderId));
	}

	@Override
	public Integer deleteprOffenderTrustAccountsTmp(Long offenderId,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_OFFENDER_TRUST_ACCOUNTS_TEMP");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "offender_trust_accounts_temp";
			String whereCondition = "offender_id = :p_offender_id";
			inputMap.put("p_offender_id", offenderId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprOffenderTrustAccountsTmp" + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_id",offenderId));
	}

	@Override
	public Integer deleteprOffenderVitalSigns(Long offenderId,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_OFFENDER_VITAL_SIGNS");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "offender_vital_signs";
			String whereCondition = "offender_id = :p_offender_id";
			inputMap.put("p_offender_id", offenderId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprOffenderVitalSigns" + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_id",offenderId));
	}

	@Override
	public List<Addresses> prAddresses(BigDecimal ownerId, String ownerClass) {
		String sql=getQuery("BOOKING_GET_ADDRESSES_BOOKING");
		return namedParameterJdbcTemplate.query(sql, createParams("p_owner_id",ownerId,"p_owner_class",ownerClass),new BeanPropertyRowMapper<Addresses>(Addresses.class));
	}

	@Override
	public Integer deleteprAddressUsages(BigDecimal addressId,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_ADDRESS_USAGES");
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
	public  List<CourseActivities> prCourseActivities(Long servicesAddressId) {
		String sql=getQuery("BOOKING_GET_COURSE_ACTIVITIES_BOOKING");
		return namedParameterJdbcTemplate.query(sql, createParams("p_services_address_id",servicesAddressId),new BeanPropertyRowMapper<CourseActivities>(CourseActivities.class));
	}

	@Override
	public List<CourseActivities> prCourseActivities1(Long parentCrsActyId) {
		String sql=getQuery("BOOKING_GET_COURSE_ACTIVITIES1");
		return namedParameterJdbcTemplate.query(sql, createParams("p_parent_crs_acty_id",parentCrsActyId),new BeanPropertyRowMapper<CourseActivities>(CourseActivities.class));
	}

	@Override
	public Integer deleteprCourseActivityAreas(BigDecimal crsActyId,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_COURSE_ACTIVITY_AREAS");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "course_activity_areas";
			String whereCondition = "crs_acty_id = :p_crs_acty_id";
			inputMap.put("p_crs_acty_id", crsActyId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprCourseActivityAreas" + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_crs_acty_id",crsActyId));
	}

	@Override
	public Integer deleteprCourseActivityParties(BigDecimal crsActyId,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_COURSE_ACTIVITY_PARTIES");
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
	public Integer deleteprCourseActivityProfiles(BigDecimal crsActyId,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_COURSE_ACTIVITY_PROFILES");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "course_activity_profiles";
			String whereCondition = "crs_acty_id = :p_crs_acty_id";
			inputMap.put("p_crs_acty_id", crsActyId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprCourseActivityProfiles " + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_crs_acty_id",crsActyId));
	}

	@Override
	public Integer deleteprCourseActivityReviews(BigDecimal crsActyId,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_COURSE_ACTIVITY_REVIEWS");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "course_activity_reviews";
			String whereCondition = "crs_acty_id = :p_crs_acty_id";
			inputMap.put("p_crs_acty_id", crsActyId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprCourseActivityReviews " + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_crs_acty_id",crsActyId));
	}

	@Override
	public List<CourseSchedules> prCourseSchedules(BigDecimal crsActyId) {
		String sql=getQuery("BOOKING_GET_COURSE_SCHEDULES");
		return namedParameterJdbcTemplate.query(sql, createParams("p_crs_acty_id",crsActyId),new BeanPropertyRowMapper<CourseSchedules>(CourseSchedules.class));
	}

	@Override
	public Integer deleteprCourseScheduleRules(BigDecimal crsActyId,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_COURSE_SCHEDULE_RULES");
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
	public List<OffenderCourseAttendances> prOffenderCourseAttendancs3(BigDecimal crsActyId) {
		String sql=getQuery("BOOKING_GET_OFFENDER_COURSE_ATTENDANCES_BOOKING");
		return namedParameterJdbcTemplate.query(sql, createParams("p_crs_acty_id",crsActyId),new BeanPropertyRowMapper<OffenderCourseAttendances>(OffenderCourseAttendances.class));
	}

	@Override
	public Integer deleteprOffenderCourseSkills(Long eventId,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_OFFENDER_COURSE_SKILLS");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "offender_course_skills";
			String whereCondition = "event_id = :p_event_id";
			inputMap.put("p_event_id", eventId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprOffenderCourseSkills " + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_event_id",eventId));
	}

	@Override
	public Integer deleteprOffenderCourseAttendancs3(BigDecimal crsActyId,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_OFFENDER_COURSE_ATTENDANCES");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "offender_course_attendances";
			String whereCondition = "crs_acty_id = :p_crs_acty_id";
			inputMap.put("p_crs_acty_id", crsActyId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprOffenderCourseAttendancs3 " + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_crs_acty_id",crsActyId));
	}

	@Override
	public List<OffenderProgramProfiles> prOffenderProgramProfiles3(Long crsActyId) {
		String sql=getQuery("BOOKING_GET_OFFENDER_PROGRAM_PROFILES_BOOKING");
		return namedParameterJdbcTemplate.query(sql, createParams("p_crs_acty_id",crsActyId),new BeanPropertyRowMapper<OffenderProgramProfiles>(OffenderProgramProfiles.class));
	}

	@Override
	public List<OffenderCourseApptGrp> prOffenderCourseApptGrps(BigDecimal offPrgrefId) {
		String sql=getQuery("BOOKING_GET_OFFENDER_COURSE_APPT_GRPS_BOOKING");
		return namedParameterJdbcTemplate.query(sql, createParams("offender_course_appt_grp_id",offPrgrefId),new BeanPropertyRowMapper<OffenderCourseApptGrp>(OffenderCourseApptGrp.class));
	}

	@Override
	public Integer deleteprOffenderCourseApptRules(Long offenderCourseApptRuleId,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_OFFENDER_COURSE_APPT_RULES");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "offender_course_appt_rules";
			String whereCondition = "offender_course_appt_grp_id = :p_offender_cours_ppt_grp_id";
			inputMap.put("p_offender_cours_ppt_grp_id", offenderCourseApptRuleId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprOffenderCourseApptRules " + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_cours_ppt_grp_id",offenderCourseApptRuleId));
	}

	@Override
	public Integer deleteprOffenderCourseApptGrps(BigDecimal offPrgrefId,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_OFFENDER_COURSE_APPT_GRPS");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "offender_course_appt_grps";
			String whereCondition = "off_prgref_id = :p_off_prgref_id";
			inputMap.put("p_off_prgref_id", offPrgrefId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprOffenderCourseApptGrps" + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_off_prgref_id",offPrgrefId));
	}

	@Override
	public List<OffenderCourseAttendances> prOffenderCourseAttendancs2(BigDecimal offPrgrefId) {
		String sql=getQuery("BOOKING_GET_OFFENDER_COURSE_ATTENDANCES1_BOOKING");
		return namedParameterJdbcTemplate.query(sql, createParams("p_off_prgref_id",offPrgrefId),new BeanPropertyRowMapper<OffenderCourseAttendances>(OffenderCourseAttendances.class));
	}

	@Override
	public List<OffenderProgramProfiles> prOffenderProgramProfiles2(Long offPrgrefId) {
		String sql=getQuery("BOOKING_GET_OFFENDER_PROGRAM_PROFILES1_BOOKING");
		return namedParameterJdbcTemplate.query(sql, createParams("p_program_off_prgref_id",offPrgrefId),new BeanPropertyRowMapper<OffenderProgramProfiles>(OffenderProgramProfiles.class));
	}

	@Override
	public Integer deleteprOffenderProgramProfiles2(Long offPrgrefId,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_OFFENDER_PROGRAM_PROFILES");
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
	public Integer deleteprCourseActivities1(Long parentCrsActyId,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_COURSE_ACTIVITIES");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "course_activities";
			String whereCondition = "parent_crs_acty_id = :p_parent_crs_acty_id";
			inputMap.put("p_parent_crs_acty_id", parentCrsActyId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprCourseActivities1 " + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_parent_crs_acty_id",parentCrsActyId));
	}

	@Override
	public Integer deleteprCourseActivities(Long servicesAddressId,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_COURSE_ACTIVITIES1");
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
	public List<OffenderCurfews> prCurfewAddresses1(BigDecimal addressId) {
		String sql=getQuery("BOOKING_GET_CURFEW_ADDRESSES_BOOKING");
		return namedParameterJdbcTemplate.query(sql, createParams("p_address_id",addressId),new BeanPropertyRowMapper<OffenderCurfews>(OffenderCurfews.class));
	}

	@Override
	public Integer deleteprCurfewAddressOccupants(BigDecimal pCurfewAddressId,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_CURFEW_ADDRESS_OCCUPANTS");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "curfew_address_occupants";
			String whereCondition = "curfew_address_id = :p_curfew_address_id";
			inputMap.put("p_curfew_address_id", pCurfewAddressId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprCurfewAddressOccupants " + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_curfew_address_id",pCurfewAddressId));
	}

	@Override
	public Integer deleteprOffenderExternalMovemnts1(BigDecimal toAddressId,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_OFFENDER_EXTERNAL_MOVEMENTS");
		try {
			 Map<String, Object> inputMap = new HashMap<String, Object>();
			 String tableName = "offender_external_movements";
			String whereCondition = "to_address_id = :p_to_address_id";
			inputMap.put("p_to_address_id",toAddressId);
			 inputMap.put("modifyUserId",modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
			}catch(Exception e){
			logger.error(this.getClass().getName() + " error in deleteprOffenderExternalMovemnts1 " + e.getMessage());
			}
		return namedParameterJdbcTemplate.update(sql, createParams("p_to_address_id",toAddressId));
	}

	@Override
	public Integer deleteprOffenderExternalMovemnts2(BigDecimal fromAddressId,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_OFFENDER_EXTERNAL_MOVEMENTS1");
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
		return namedParameterJdbcTemplate.update(sql, createParams("p_from_address_id",fromAddressId));
	}

	@Override
	public Integer deleteprOffenderMailLogs1(BigDecimal pMailAddressId,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_OFFENDER_MAIL_LOGS");
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
		String sql=getQuery("BOOKING_DELETE_OFFENDER_MAIL_RESTRICTIONS");
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
	public Integer deleteprPhones(BigDecimal ownerId, String ownerClass,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_PHONES");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "phones";
			String whereCondition = "owner_id = :p_owner_id AND owner_class = :p_owner_class";
			inputMap.put("p_owner_id", ownerId);
			inputMap.put("p_owner_class", ownerClass);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprPhones " + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_owner_id",ownerId,"p_owner_class",ownerClass));
	}

	@Override
	public Integer deleteprAddresses(BigDecimal ownerId, String ownerClass,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_ADDRESSES");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "addresses";
			String whereCondition = "owner_id = :p_owner_id AND owner_class = :p_owner_class";
			inputMap.put("p_owner_id", ownerId);
			inputMap.put("p_owner_class", ownerClass);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprAddresses " + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_owner_id",ownerId,"p_owner_class",ownerClass));
	}

	@Override
	public List<OffenderNonAssociations> prOffenderNonAssociations(Long offenderId) {
		String sql=getQuery("BOOKING_GET_OFFENDER_NON_ASSOCIATIONS");
		RowMapper<OffenderNonAssociations> mapper= Row2BeanRowMapper.makeMapping(sql, OffenderNonAssociations.class, courtListMapping);
		return namedParameterJdbcTemplate.query(sql, createParams("p_offender_id",offenderId),mapper);
	}

	@Override
	public Integer deleteprOffenderNaDetails(Long offenderId, Long nsOffenderId,String modifyUserId) {
		String sql=getQuery("BOOKING_GET_OFFENDER_NON_ASSOCIATIONS");
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_id",offenderId,"p_ns_offender_id",nsOffenderId));
	}

	@Override
	public Integer deleteprOffenderNonAssociations(Long offenderId,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_OFFENDER_NON_ASSOCIATIONS");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "offender_non_associations";
			String whereCondition = "offender_id = :p_offender_id";
			inputMap.put("p_offender_id", offenderId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprOffenderNonAssociations " + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_id",offenderId));
	}

	@Override
	public List<OffenderNonAssociations> prOffenderNonAssociations1(Long nsOffenderId) {
		String sql=getQuery("BOOKING_GET_OFFENDER_NON_ASSOCIATIONS1");
		RowMapper<OffenderNonAssociations> mapper= Row2BeanRowMapper.makeMapping(sql, OffenderNonAssociations.class, courtListMapping);
		return namedParameterJdbcTemplate.query(sql, createParams("p_ns_offender_id",nsOffenderId),mapper);
	}

	@Override
	public Integer deleteprOffenderNonAssociations1(Long nsOffenderId,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_OFFENDER_NON_ASSOCIATIONS1");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "offender_non_associations";
			String whereCondition = "ns_offender_id = :p_ns_offender_id";
			inputMap.put("p_ns_offender_id", nsOffenderId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprOffenderNonAssociations1 " + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_ns_offender_id",nsOffenderId));
	}

	@Override
	public List<OffenderContactPersons> prOffenderContactPersons(BigDecimal contactRootOffenderId) {
		String sql=getQuery("BOOKING_GET_OFFENDER_CONTACT_PERSONS");
		return namedParameterJdbcTemplate.query(sql, createParams("p_contact_root_offender_id",contactRootOffenderId),new BeanPropertyRowMapper<OffenderContactPersons>(OffenderContactPersons.class));
	}

	@Override
	public Integer deleteprOffenderPersonRestricts(Integer offenderContactPersonId,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_OFFENDER_PERSON_RESTRICTS");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "offender_person_restricts";
			String whereCondition = "offender_contact_person_id = :p_offender_contact_prson_id";
			inputMap.put("p_offender_contact_prson_id", offenderContactPersonId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprOffenderPersonRestricts " + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_contact_prson_id",offenderContactPersonId));
	}

	@Override
	public Integer deleteprOffenderContactPersons(BigDecimal contactRootOffenderId,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_OFFENDER_CONTACT_PERSONS");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "offender_contact_persons";
			String whereCondition = "contact_root_offender_id = :p_contact_root_offender_id";
			inputMap.put("p_contact_root_offender_id", contactRootOffenderId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprOffenderContactPersons " + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_contact_root_offender_id",contactRootOffenderId));
	}

	@Override
	public List<OffenderTrustAccounts> prOffenderTrustAccounts(Long offenderId) {
		String sql=getQuery("BOOKING_GET_OFFENDER_TRUST_ACCOUNTS");
		RowMapper<OffenderTrustAccounts> mapper= Row2BeanRowMapper.makeMapping(sql, OffenderTrustAccounts.class, courtListMapping);
		return namedParameterJdbcTemplate.query(sql, createParams("p_offender_id",offenderId),mapper);
	}

	@Override
	public List<OffenderDeductions> prOffenderDeductions2(String caseloadId, Long offenderId) {
		String sql=getQuery("BOOKING_GET_OFFENDER_DEDUCTIONS2");
		RowMapper<OffenderDeductions> mapper= Row2BeanRowMapper.makeMapping(sql, OffenderDeductions.class, courtListMapping);
		return namedParameterJdbcTemplate.query(sql, createParams("caseloadId",caseloadId,"p_offender_id",offenderId),mapper);
	}

	@Override
	public Integer deleteprOffenderAdjustmentTxns(Long offenderDeductionId,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_OFFENDER_ADJUSTMENT_TXNS");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "offender_adjustment_txns";
			String whereCondition = "offender_deduction_id = :p_offender_deduction_id";
			inputMap.put("p_offender_deduction_id", offenderDeductionId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprOffenderAdjustmentTxns" + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_deduction_id",offenderDeductionId));
	}

	@Override
	public Integer deleteprOffenderPaymentProfiles(BigDecimal offenderId,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_OFFENDER_PAYMENT_PROFILES");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "offender_payment_profiles";
			String whereCondition = "offender_id = :p_offender_id";
			inputMap.put("p_offender_id", offenderId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprOffenderPaymentProfiles " + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_id",offenderId));
	}

	@Override
	public Integer deleteprOffenders(BigDecimal offenderId,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_OFFENDERS");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "offenders";
			String whereCondition = "offender_id = :p_offender_id";
			inputMap.put("p_offender_id", offenderId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprOffenders " + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_id",offenderId));
	}

	@Override
	public Integer deleteprOffenderForeignCurrencies(String caseloadId, Long offenderId,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_OFFENDER_FOREIGN_CURRENCIES");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "offender_foreign_currencies";
			String whereCondition = "caseload_id = :p_caseload_id AND offender_id = :p_offender_id";
			inputMap.put("p_caseload_id", caseloadId);
			inputMap.put("p_offender_id", offenderId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprOffenderForeignCurrencies " + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_caseload_id",caseloadId,"p_offender_id",offenderId));
	}

	@Override
	public Integer deleteprOffenderSubAccounts(String caseloadId, Long offenderId,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_OFFENDER_SUB_ACCOUNTS");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "offender_sub_accounts";
			String whereCondition = "caseload_id = :p_caseload_id AND offender_id = :p_offender_id";
			inputMap.put("p_caseload_id", caseloadId);
			inputMap.put("p_offender_id", offenderId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprOffenderSubAccounts " + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_caseload_id",caseloadId,"p_offender_id",offenderId));
	}

	@Override
	public Integer deleteprArrests(Long offenderBookId,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_ARRESTS_BOOKING");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "arrests";
			String whereCondition = "offender_book_id = :p_offender_book_id";
			inputMap.put("p_offender_book_id", offenderBookId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprArrests " + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_book_id",offenderBookId));
	}

	@Override
	public Integer deleteprBedAssignmentHistories(Integer offenderBookId,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_BED_ASSIGNMENT_HISTORIES_BOOKING");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "bed_assignment_histories";
			String whereCondition = "offender_book_id = :p_offender_book_id";
			inputMap.put("p_offender_book_id", offenderBookId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprBedAssignmentHistories " + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_book_id",offenderBookId));
	}

	@Override
	public Integer deleteprCaseAssociatedPersons(Long offenderBookId,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_CASE_ASSOCIATED_PERSONS_BOOKING");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "case_associated_persons";
			String whereCondition = "offender_book_id = :p_offender_book_id";
			inputMap.put("p_offender_book_id", offenderBookId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprCaseAssociatedPersons " + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_book_id",offenderBookId));
	}

	@Override
	public Integer deleteprCaseNotes(Long offenderBookId,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_CASE_NOTES_BOOKING");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "case_notes";
			String whereCondition = "offender_book_id = :p_offender_book_id";
			inputMap.put("p_offender_book_id", offenderBookId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprCaseNotes " + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_book_id",offenderBookId));
	}

	@Override
	public List<CasePlans> prCasePlans(Long offenderBookId) {
		String sql=getQuery("BOOKING_GET_CASE_PLANS_BOOKING");
		RowMapper<CasePlans> mapper= Row2BeanRowMapper.makeMapping(sql, CasePlans.class, courtListMapping);
		return namedParameterJdbcTemplate.query(sql, createParams("p_offender_book_id",offenderBookId),mapper);
	}

	@Override
	public Integer deleteprAssessmentSummaries(Long offenderBookId,Long casePlanId,String modifyUserId){
		String sql=getQuery("BOOKING_DELETE_ASSESSMENT_SUMMARIES_BOOKING");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "assessment_summaries";
			String whereCondition = "offender_book_id = :p_offender_book_id AND case_plan_id = :p_case_plan_id";
			inputMap.put("p_offender_book_id", offenderBookId);
			inputMap.put("p_case_plan_id", casePlanId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprAssessmentSummaries " + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_book_id",offenderBookId,"p_case_plan_id",casePlanId));
	}

	@Override
	public List<BigDecimal> prOffenderCaseConditions(BigDecimal offenderBookId, BigDecimal casePlanId) {
		String sql=getQuery("BOOKING_GET_OFFENDER_CASE_CONDITIONS_BOOKING");
		RowMapper<BigDecimal> mapper= Row2BeanRowMapper.makeMapping(sql, BigDecimal.class, courtListMapping);
		return namedParameterJdbcTemplate.query(sql, createParams("p_offender_book_id",offenderBookId,"p_case_plan_id",casePlanId),mapper);
	}

	@Override
	public Integer deleteprOffenderActionPlans(BigDecimal offCaseCondId,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_OFFENDER_ACTION_PLANS_BOOKING");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "offender_action_plans";
			String whereCondition = "off_case_cond_id = :p_off_case_cond_id";
			inputMap.put("p_off_case_cond_id", offCaseCondId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprOffenderActionPlans " + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_off_case_cond_id",offCaseCondId));
	}

	@Override
	public Integer deleteprOffenderCaseConditions(BigDecimal offenderBookId, BigDecimal casePlanId,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_OFFENDER_CASE_CONDITIONS_BOOKING");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "offender_case_conditions";
			String whereCondition = "offender_book_id = :p_offender_book_id AND case_plan_id = :p_case_plan_id";
			inputMap.put("p_offender_book_id", offenderBookId);
			inputMap.put("p_case_plan_id", casePlanId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprOffenderCaseConditions " + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_book_id",offenderBookId,"p_case_plan_id",casePlanId));
	}

	@Override
	public List<OffenderCriminogenicNeeds> prOffenderCriminogenicNeeds(BigDecimal offenderBookId, BigDecimal casePlanId) {
		String sql=getQuery("BOOKING_GET_OFFENDER_CRIMINOGENIC_NEEDS_BOOKING");
		RowMapper<OffenderCriminogenicNeeds> mapper= Row2BeanRowMapper.makeMapping(sql, OffenderCriminogenicNeeds.class, courtListMapping);
		return namedParameterJdbcTemplate.query(sql, createParams("p_offender_book_id",offenderBookId,"p_case_plan_id",casePlanId),mapper);
	}

	@Override
	public Integer deleteprOffenderActionPlans1(BigDecimal offCrimNeedId,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_OFFENDER_ACTION_PLANS_BOOKING_CRIM");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "offender_action_plans";
			String whereCondition = "off_crim_need_id = :p_off_crim_need_id";
			inputMap.put("p_off_crim_need_id", offCrimNeedId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprOffenderActionPlans1 " + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_off_crim_need_id",offCrimNeedId));
	}

	@Override
	public Integer deleteprOffenderCriminogenicNeeds(BigDecimal offenderBookId, BigDecimal casePlanId,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_OFFENDER_CRIMINOGENIC_NEEDS_BOOKING");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "offender_criminogenic_needs";
			String whereCondition = "offender_book_id = :p_offender_book_id AND case_plan_id = :p_case_plan_id";
			inputMap.put("p_offender_book_id", offenderBookId);
			inputMap.put("p_case_plan_id", casePlanId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprOffenderCriminogenicNeeds " + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_book_id",offenderBookId,"p_case_plan_id",casePlanId));
	}

	@Override
	public List<PlanDetails> prPlanDetails(Long offenderBookId, Long casePlanId) {
		String sql=getQuery("BOOKING_GET_PLAN_DETAILS_BOOKING");
		RowMapper<PlanDetails> mapper= Row2BeanRowMapper.makeMapping(sql, PlanDetails.class, courtListMapping);
		return namedParameterJdbcTemplate.query(sql, createParams("p_offender_book_id",offenderBookId,"p_case_plan_id",casePlanId),mapper);
	}

	@Override
	public Integer deleteprCaseworkSteps(Long offenderBookId, Long casePlanId, Long detailSeq,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_CASEWORK_STEPS_BOOKING");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "casework_steps";
			String whereCondition = "offender_book_id = :p_offender_book_id AND case_plan_id = :p_case_plan_id AND detail_seq = :p_detail_seq";
			inputMap.put("p_offender_book_id", offenderBookId);
			inputMap.put("p_case_plan_id", casePlanId);
			inputMap.put("p_detail_seq", detailSeq);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprCaseworkSteps " + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_book_id",offenderBookId,"p_case_plan_id",casePlanId,"p_detail_seq",detailSeq));
	}

	@Override
	public Integer deleteprPlanDetails(Long offenderBookId, Long casePlanId,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_PLAN_DETAILS_BOOKING");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "plan_details";
			String whereCondition = "offender_book_id = :p_offender_book_id AND case_plan_id = :p_case_plan_id";
			inputMap.put("p_offender_book_id", offenderBookId);
			inputMap.put("p_case_plan_id", casePlanId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprPlanDetails " + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_book_id",offenderBookId,"p_case_plan_id",casePlanId));
	}

	@Override
	public Integer deleteprCasePlans(Long offenderBookId,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_CASE_PLANS1");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "case_plans";
			String whereCondition = "offender_book_id = :p_offender_book_id";
			inputMap.put("p_offender_book_id", offenderBookId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprCasePlans " + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_book_id",offenderBookId));
	}

	@Override
	public List<CourtEvents> prCourtEvents(Integer offenderBookId) {
		String sql=getQuery("BOOKING_GET_COURT_EVENTS_BOOKING");
		return namedParameterJdbcTemplate.query(sql, createParams("p_offender_book_id",offenderBookId),new BeanPropertyRowMapper<CourtEvents>(CourtEvents.class));
	}

	@Override
	public Integer deleteprCourtEventCharges(BigDecimal eventId,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_COURT_EVENT_CHARGES1_BOOKING");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "court_event_charges";
			String whereCondition = "event_id = :p_event_id";
			inputMap.put("p_event_id", eventId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprCourtEventCharges " + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_event_id",eventId));
	}

	@Override
	public Integer deleteprCourtEventSentences(BigDecimal eventId,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_COURT_EVENT_SENTENCES1");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "court_event_sentences";
			String whereCondition = "event_id = :p_event_id";
			inputMap.put("p_event_id", eventId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprCourtEventSentences " + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_event_id",eventId));
	}

	@Override
	public Integer deleteprLinkCaseTxns(BigDecimal eventId,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_LINK_CASE_TXNS_BOOKING_EVENT_ID");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "link_case_txns";
			String whereCondition = "event_id = :p_event_id";
			inputMap.put("p_event_id", eventId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprLinkCaseTxns " + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_event_id",eventId));
	}

	@Override
	public Integer deleteprCourtEvents(Integer offenderBookId,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_COURT_EVENTS_BOOKING");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "court_events";
			String whereCondition = "offender_book_id = :p_offender_book_id";
			inputMap.put("p_offender_book_id", offenderBookId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprCourtEvents " + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_book_id",offenderBookId));
	}

	@Override
	public Integer deleteprDocumentTemplateQueues(BigDecimal offenderBookId,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_DOCUMENT_TEMPLATE_QUEUES_BOOKING");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "document_template_queues";
			String whereCondition = "offender_book_id = :p_offender_book_id";
			inputMap.put("p_offender_book_id", offenderBookId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprDocumentTemplateQueues " + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_book_id",offenderBookId));
	}

	@Override
	public Integer updateprGlTransactions1(BigDecimal offenderBookId,String modifyUserId) {
		String sql=getQuery("BOOKING_UPDATE_GL_TRANSACTIONS1");
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_book_id",offenderBookId));
	}

	@Override
	public List<HdcRequestReferrals> prHdcRequestReferrals(Long offenderBookId) {
		String sql=getQuery("BOOKING_GET_HDC_REQUEST_REFERRALS_BOOKING");
		return namedParameterJdbcTemplate.query(sql, createParams("p_offender_curfew_id",offenderBookId),new BeanPropertyRowMapper<HdcRequestReferrals>(HdcRequestReferrals.class));
	}

	@Override
	public Integer deleteprHdcBoardDecisions(Long hdcRequestReferralId,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_HDC_BOARD_DECISIONS_BOOKING");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "hdc_board_decisions";
			String whereCondition = "hdc_request_referral_id = :p_hdc_request_referral_id";
			inputMap.put("p_hdc_request_referral_id", hdcRequestReferralId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprHdcBoardDecisions " + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_hdc_request_referral_id",hdcRequestReferralId));
	}

	@Override
	public Integer deleteprHdcGovernorDecisions(Long hdcRequestReferralId,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_HDC_GOVERNOR_DECISIONS_BOOKING");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "hdc_governor_decisions";
			String whereCondition = "hdc_request_referral_id = :p_hdc_request_referral_id";
			inputMap.put("p_hdc_request_referral_id", hdcRequestReferralId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprHdcGovernorDecisions " + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_hdc_request_referral_id",hdcRequestReferralId));
	}

	@Override
	public Integer deleteprHdcProbStaffComments(Long hdcRequestReferralId,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_HDC_PROB_STAFF_COMMENTS_BOOKING");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "hdc_prob_staff_comments";
			String whereCondition = "hdc_request_referral_id = :p_hdc_request_referral_id";
			inputMap.put("p_hdc_request_referral_id", hdcRequestReferralId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprHdcProbStaffComments " + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_hdc_request_referral_id",hdcRequestReferralId));
	}

	@Override
	public Integer deleteprHdcProbStaffResponses(Long hdcRequestReferralId,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_HDC_PROB_STAFF_RESPONSES_BOOKING");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "hdc_prob_staff_responses";
			String whereCondition = "hdc_request_referral_id = :p_hdc_request_referral_id";
			inputMap.put("p_hdc_request_referral_id", hdcRequestReferralId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprHdcProbStaffResponses " + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_hdc_request_referral_id",hdcRequestReferralId));
	}

	@Override
	public List<HdcRequestReferrals> prHdcRequestReferrals1(Long parentHdcRequestReferralId) {
		String sql=getQuery("BOOKING_GET_HDC_REQUEST_REFERRALS1_BOOKING");
		return namedParameterJdbcTemplate.query(sql, createParams("p_parent_hdc_reqst_rfrrl_id",parentHdcRequestReferralId),new BeanPropertyRowMapper<HdcRequestReferrals>(HdcRequestReferrals.class));
	}

	@Override
	public Integer deleteprHdcRequestReferrals1(Long parentHdcRequestReferralId,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_HDC_REQUEST_REFERRALS_BOOKING");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "hdc_request_referrals";
			String whereCondition = "parent_hdc_request_referral_id = :p_parent_hdc_reqst_rfrrl_id";
			inputMap.put("p_parent_hdc_reqst_rfrrl_id", parentHdcRequestReferralId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprHdcRequestReferrals1 " + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_parent_hdc_reqst_rfrrl_id",parentHdcRequestReferralId));
	}

	@Override
	public Integer deleteprHdcRequestReferrals(Long offenderBookId,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_HDC_REQUEST_REFERRALS1_BOOKING");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "hdc_request_referrals";
			String whereCondition = "offender_book_id = :p_offender_book_id";
			inputMap.put("p_offender_book_id", offenderBookId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprHdcRequestReferrals " + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_book_id",offenderBookId));
	}

	@Override
	public Integer deleteprIncidentCaseParties(Long offenderBookId,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_INCIDENT_CASE_PARTIES_BOOKING");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "incident_case_parties";
			String whereCondition = "offender_book_id = :p_offender_book_id";
			inputMap.put("p_offender_book_id", offenderBookId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprIncidentCaseParties " + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_book_id",offenderBookId));
	}

	@Override
	public Integer deleteprIwpDocuments(BigDecimal offenderBookId,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_IWP_DOCUMENTS_BOOKING");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "iwp_documents";
			String whereCondition = "offender_book_id = :p_offender_book_id";
			inputMap.put("p_offender_book_id", offenderBookId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprIwpDocuments " + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_book_id",offenderBookId));
	}

	@Override
	public Integer deleteprOffenderActivityEvents(BigDecimal offenderBookId,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_OFFENDER_ACTIVITY_EVENTS_BOOKING");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "offender_activity_events";
			String whereCondition = "offender_book_id = :p_offender_book_id";
			inputMap.put("p_offender_book_id", offenderBookId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprOffenderActivityEvents " + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_book_id",offenderBookId));
	}

	@Override
	public Integer deleteprOffenderAlerts(BigDecimal offenderBookId,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_OFFENDER_ALERTS_BOOKING");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "offender_alerts";
			String whereCondition = "offender_book_id = :p_offender_book_id";
			inputMap.put("p_offender_book_id", offenderBookId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprOffenderAlerts " + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_book_id",offenderBookId));
	}

	@Override
	public Integer deleteprOffenderAssessments(BigDecimal offenderBookId,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_OFFENDER_ASSESSMENTS");
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
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_book_id",offenderBookId));
	}

	@Override
	public Integer deleteprOffenderAssets(BigDecimal offenderBookId,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_OFFENDER_ASSETS_BOOKING");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "offender_assets";
			String whereCondition = "offender_book_id = :p_offender_book_id";
			inputMap.put("p_offender_book_id", offenderBookId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprOffenderAssets " + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_book_id",offenderBookId));
	}

	@Override
	public List<OffenderAssociatedParties> prOffenderAssociatedParties(Long offenderBookId) {
		String sql=getQuery("BOOKING_GET_OFFENDER_ASSOCIATED_PARTIES_BOOKING");
		return namedParameterJdbcTemplate.query(sql, createParams("p_offender_book_id",offenderBookId),new BeanPropertyRowMapper<OffenderAssociatedParties>(OffenderAssociatedParties.class));
	}

	@Override
	public Integer deleteprOffenderAssocPartyNotes(Long associatedPartyId,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_OFFENDER_ASSOC_PARTY_NOTES_BOOKING");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "offender_assoc_party_notes";
			String whereCondition = "associated_party_id = :p_associated_party_id";
			inputMap.put("p_associated_party_id", associatedPartyId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprOffenderAssocPartyNotes " + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_associated_party_id",associatedPartyId));
	}

	@Override
	public List<OffenderAssocPrtyContact> prOffenderAssocPrtyContcts(Long associatedPartyId) {
		String sql=getQuery("BOOKING_GET_OFFENDER_ASSOC_PRTY_CONTACTS_BOOKING");
		RowMapper<OffenderAssocPrtyContact> mapper= Row2BeanRowMapper.makeMapping(sql, OffenderAssocPrtyContact.class, courtListMapping);
		return namedParameterJdbcTemplate.query(sql, createParams("p_associated_party_id",associatedPartyId),mapper);
	}

	@Override
	public Integer deleteprOffenderAssocPCntNotifs(Long associatedPartyId,Long partySeq,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_OFFENDER_ASSOC_P_CNT_NOTIFS_BOOKING");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "offender_assoc_p_cnt_notifs";
			String whereCondition = "associated_party_id = :p_associated_party_id AND party_seq = :p_party_seq";
			inputMap.put("p_associated_party_id", associatedPartyId);
			inputMap.put("p_party_seq", partySeq);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprOffenderAssocPCntNotifs " + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_associated_party_id",associatedPartyId,"p_party_seq",partySeq));
	}

	@Override
	public Integer deleteprOffenderAssocPrtyContcts(Long associatedPartyId,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_OFFENDER_ASSOC_PRTY_CONTACTS_BOOKING");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "offender_assoc_prty_contacts";
			String whereCondition = "associated_party_id = :p_associated_party_id";
			inputMap.put("p_associated_party_id", associatedPartyId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprOffenderAssocPrtyContcts " + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_associated_party_id",associatedPartyId));
	}

	@Override
	public Integer deleteprOffenderAssocPNotifs(Long associatedPartyId,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_OFFENDER_ASSOC_P_NOTIFS_BOOKING");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "offender_assoc_p_notifs";
			String whereCondition = "associated_party_id = :p_associated_party_id";
			inputMap.put("p_associated_party_id", associatedPartyId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprOffenderAssocPNotifs " + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_associated_party_id",associatedPartyId));
	}

	@Override
	public Integer deleteprOffenderAssociatedParties(Long offenderBookId,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_OFFENDER_ASSOCIATED_PARTIES_BOOKING");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "offender_associated_parties";
			String whereCondition = "offender_book_id = :p_offender_book_id";
			inputMap.put("p_offender_book_id", offenderBookId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprOffenderAssociatedParties " + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_book_id",offenderBookId));
	}

	@Override
	public Integer deleteprOffenderAuthorisdVisitors(BigDecimal offenderBookId,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_OFFENDER_AUTHORISED_VISITORS_BOOKING");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "offender_authorised_visitors";
			String whereCondition = "offender_book_id = :p_offender_book_id";
			inputMap.put("p_offender_book_id", offenderBookId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprOffenderAuthorisdVisitors " + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_book_id",offenderBookId));
	}
	@Override
	public Integer deleteprOffenderAthorisdVisitors1(BigDecimal offenderBookId,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_OFFENDER_AUTHORISED_VISITORS1_BOOKING");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "offender_authorised_visitors";
			String whereCondition = "visitor_offender_book_id = :p_visitor_offender_book_id";
			inputMap.put("p_visitor_offender_book_id", offenderBookId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprOffenderAthorisdVisitors1 " + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_visitor_offender_book_id",offenderBookId));
	}

	@Override
	public Integer deleteprOffenderBailDetails(Long offenderBookId,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_OFFENDER_BAIL_DETAILS_BOOKING");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "offender_bail_details";
			String whereCondition = "offender_book_id = :p_offender_book_id";
			inputMap.put("p_offender_book_id", offenderBookId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprOffenderBailDetails " + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_book_id",offenderBookId));
	}

	@Override
	public Integer deleteprOffenderBookingAgyLocs(Long offenderBookId,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_OFFENDER_BOOKING_AGY_LOCS_BOOKING");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "offender_booking_agy_locs";
			String whereCondition = "offender_book_id = :p_offender_book_id";
			inputMap.put("p_offender_book_id", offenderBookId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprOffenderBookingAgyLocs " + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_book_id",offenderBookId));
	}

	@Override
	public Integer deleteprOffenderBookingDetails(Long offenderBookId,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_OFFENDER_BOOKING_DETAILS_BOOKING");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "offender_booking_details";
			String whereCondition = "offender_book_id = :p_offender_book_id";
			inputMap.put("p_offender_book_id", offenderBookId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprOffenderBookingDetails " + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_book_id",offenderBookId));
	}

	@Override
	public Integer deleteprOffenderBookingEvents(Long offenderBookId,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_OFFENDER_BOOKING_EVENTS_BOOKING");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "offender_booking_events";
			String whereCondition = "offender_book_id = :p_offender_book_id";
			inputMap.put("p_offender_book_id", offenderBookId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprOffenderBookingEvents " + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_book_id",offenderBookId));
	}

	@Override
	public  List<OffenderCases> prOffenderCases(Long offenderBookId) {
		String sql=getQuery("BOOKING_GET_OFFENDER_CASES_BOOKING");
		return namedParameterJdbcTemplate.query(sql, createParams("p_offender_book_id",offenderBookId),new BeanPropertyRowMapper<OffenderCases>(OffenderCases.class));
	}

	@Override
	public List<CourtEvents> prCourtEvents1(Integer caseId) {
		String sql=getQuery("BOOKING_GET_COURT_EVENTS1_BOOKING");
		return namedParameterJdbcTemplate.query(sql, createParams("p_case_id",caseId),new BeanPropertyRowMapper<CourtEvents>(CourtEvents.class));
	}

	@Override
	public Integer deleteprCourtEvents1(Integer caseId,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_COURT_EVENTS1_BOOKING");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "court_events";
			String whereCondition = "case_id = :p_case_id";
			inputMap.put("p_case_id", caseId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprCourtEvents1 " + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_case_id",caseId));
	}

	@Override
	public Integer deleteprLinkCaseTxns1(Long caseId,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_LINK_CASE_TXNS_BOOKING");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "link_case_txns";
			String whereCondition = "case_id = :p_case_id";
			inputMap.put("p_case_id", caseId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprLinkCaseTxns1 " + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_case_id",caseId));
	}

	@Override
	public Integer deleteprLinkCaseTxns2(Long caseId,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_LINK_CASE_TXNS1_BOOKING");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "link_case_txns";
			String whereCondition = "combined_case_id = :p_combined_case_id";
			inputMap.put("p_combined_case_id",caseId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprLinkCaseTxns2 " + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_combined_case_id",caseId));
	}

	@Override
	public List<OffenderAssociatedParties> prOffenderAssociatedPartis1(Long caseId) {
		String sql=getQuery("BOOKING_GET_OFFENDER_ASSOCIATED_PARTIES1_BOOKING");
		return namedParameterJdbcTemplate.query(sql, createParams("p_case_id",caseId),new BeanPropertyRowMapper<OffenderAssociatedParties>(OffenderAssociatedParties.class));
	}

	@Override
	public Integer deleteprOffenderAssociatedPartis1(Long caseId,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_OFFENDER_ASSOCIATED_PARTIES1");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "offender_associated_parties";
			String whereCondition = "case_id = :p_case_id";
			inputMap.put("p_case_id", caseId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprOffenderAssociatedPartis1 " + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_case_id",caseId));
	}

	@Override
	public List<OffenderCases> prOffenderCases1(BigDecimal combinedCaseId) {
		String sql=getQuery("BOOKING_GET_OFFENDER_CASES1");
		return namedParameterJdbcTemplate.query(sql, createParams("p_combined_case_id",combinedCaseId),new BeanPropertyRowMapper<OffenderCases>(OffenderCases.class));
	}

	@Override
	public Integer deleteprOffenderCaseIdentifiers(Long caseId,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_OFFENDER_CASE_IDENTIFIERS");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "offender_case_identifiers";
			String whereCondition = "case_id = :p_case_id";
			inputMap.put("p_case_id", caseId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprOffenderCaseIdentifiers " + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_case_id",caseId));
	}

	@Override
	public Integer deleteprOffenderCaseStatuses(Long caseId,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_OFFENDER_CASE_STATUSES_BOOKING");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "offender_case_statuses";
			String whereCondition = "case_id = :p_case_id";
			inputMap.put("p_case_id", caseId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprOffenderCaseStatuses " + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_case_id",caseId));
	}

	@Override
	public List<OffenderCharges> prOffenderCharges1(Long caseId) {
		String sql=getQuery("BOOKING_GET_OFFENDER_CHARGES_BOOKING");
		return namedParameterJdbcTemplate.query(sql, createParams("p_case_id",caseId),new BeanPropertyRowMapper<OffenderCharges>(OffenderCharges.class));
	}

	@Override
	public Integer deleteprCourtEventCharges1(BigDecimal offenderChargeId,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_COURT_EVENT_CHARGES_BOOKING");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "court_event_charges";
			String whereCondition = "offender_charge_id = :p_offender_charge_id";
			inputMap.put("p_offender_charge_id", offenderChargeId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprCourtEventCharges1 " + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_charge_id",offenderChargeId));
	}

	@Override
	public Integer deleteprLinkCaseTxns3(BigDecimal offenderChargeId,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_LINK_CASE_TXNS2_BOOKING");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "link_case_txns";
			String whereCondition = "offender_charge_id = :p_offender_charge_id";
			inputMap.put("p_offender_charge_id", offenderChargeId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprLinkCaseTxns3 " + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_charge_id",offenderChargeId));
	}

	@Override
	public Integer deleteprOffenderSentenceCharges(Long offenderChargeId,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_OFFENDER_SENTENCE_CHARGES1_BOOKING");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "offender_sentence_charges";
			String whereCondition = "offender_charge_id = :p_offender_charge_id";
			inputMap.put("p_offender_charge_id", offenderChargeId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprOffenderSentenceCharges " + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_charge_id",offenderChargeId));
	}

	@Override
	public Integer deleteprOffenderCharges1(Long caseId,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_OFFENDER_CHARGES_BOOKING");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "offender_charges";
			String whereCondition = "case_id = :p_case_id";
			inputMap.put("p_case_id", caseId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprOffenderCharges1 " + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_case_id",caseId));
	}

	@Override
	public Integer deleteprOffenderLicenceSentences(Long caseId,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_OFFENDER_LICENCE_SENTENCES2_BOOKING");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "offender_licence_sentences";
			String whereCondition = "case_id = :p_case_id";
			inputMap.put("p_case_id", caseId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprOffenderLicenceSentences " + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_case_id",caseId));
	}

	@Override
	public List<OffenderSentences> prOffenderSentences(BigDecimal caseId) {
		String sql=getQuery("BOOKING_GET_OFFENDER_SENTENCES1");
		RowMapper<OffenderSentences> mapper= Row2BeanRowMapper.makeMapping(sql, OffenderSentences.class, courtListMapping);
		return namedParameterJdbcTemplate.query(sql, createParams("p_case_id",caseId),mapper);
	}

	@Override
	public Integer deleteprOffenderSentences(BigDecimal caseId,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_OFFENDER_SENTENCES1");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "offender_sentences";
			String whereCondition = "case_id = :p_case_id";
			inputMap.put("p_case_id", caseId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprOffenderSentences " + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_case_id",caseId));
	}

	@Override
	public List<Orders> prOrders1(Long caseId) {
		String sql=getQuery("BOOKING_GET_ORDERS_BOOKING");
		return namedParameterJdbcTemplate.query(sql, createParams("p_case_id",caseId),new BeanPropertyRowMapper<Orders>(Orders.class));
	}

	@Override
	public Integer deleteprOrderPurposes(Long orderId,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_ORDER_PURPOSES_BOOKING");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "order_purposes";
			String whereCondition = "order_id = :p_order_id";
			inputMap.put("p_order_id", orderId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprOrderPurposes " + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_order_id",orderId));
	}

	@Override
	public Integer deleteprOrders1(Long caseId,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_ORDERS_BOOKING");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "orders";
			String whereCondition = "case_id = :p_case_id";
			inputMap.put("p_case_id", caseId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprOrders1 " + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_case_id",caseId));
	}

	@Override
	public Integer deleteprOffenderCases(Long offenderBookId,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_OFFENDER_CASES");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "offender_cases";
			String whereCondition = "offender_book_id = :p_offender_book_id";
			inputMap.put("p_offender_book_id", offenderBookId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprOffenderCases " + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_book_id",offenderBookId));
	}

	@Override
	public List<OffenderCaseNotes> prOffenderCaseNotes(Integer offenderBookId) {
		String sql=getQuery("BOOKING_GET_OFFENDER_CASE_NOTES_BOOKING");
		return namedParameterJdbcTemplate.query(sql, createParams("p_offender_book_id",offenderBookId),new BeanPropertyRowMapper<OffenderCaseNotes>(OffenderCaseNotes.class));
	}

	@Override
	public Integer deleteprOffenderCaseNoteSents1(Integer caseNoteId,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_OFFENDER_CASE_NOTE_SENTS1");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "offender_case_note_sents";
			String whereCondition = "case_note_id = :p_case_note_id";
			inputMap.put("p_case_note_id", caseNoteId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprOffenderCaseNoteSents1 " + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_case_note_id",caseNoteId));
	}

	@Override
	public Integer deleteprOffCaseNoteRecipients(Integer caseNoteId,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_OFF_CASE_NOTE_RECIPIENTS_BOOKING");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "off_case_note_recipients";
			String whereCondition = "case_note_id = :p_case_note_id";
			inputMap.put("p_case_note_id", caseNoteId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprOffCaseNoteRecipients " + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_case_note_id",caseNoteId));
	}

	@Override
	public Integer deleteprOffenderCaseNotes(Integer offenderBookId,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_OFFENDER_CASE_NOTES_BOOKING");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "offender_case_notes";
			String whereCondition = "offender_book_id = :p_offender_book_id";
			inputMap.put("p_offender_book_id", offenderBookId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprOffenderCaseNotes " + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_book_id",offenderBookId));
	}

	@Override
	public List<OffenderCharges> prOffenderCharges(Long offenderBookId) {
		String sql=getQuery("BOOKING_GET_OFFENDER_CHARGES1_BOOKING");
		return namedParameterJdbcTemplate.query(sql, createParams("p_offender_book_id",offenderBookId),new BeanPropertyRowMapper<OffenderCharges>(OffenderCharges.class));
	}

	@Override
	public Integer deleteprOffenderCharges(Long offenderBookId,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_OFFENDER_CHARGES1");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "offender_charges";
			String whereCondition = "offender_book_id = :p_offender_book_id";
			inputMap.put("p_offender_book_id", offenderBookId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprOffenderCharges " + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_book_id",offenderBookId));
	}

	@Override
	public Integer deleteprOffenderCipDetails(Long offenderBookId,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_OFFENDER_CIP_DETAILS_BOOKING");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "offender_cip_details";
			String whereCondition = "offender_book_id = :p_offender_book_id";
			inputMap.put("p_offender_book_id", offenderBookId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprOffenderCipDetails " + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_book_id",offenderBookId));
	}

	@Override
	public Integer deleteprOffenderClassPrograms(Long offenderBookId,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_OFFENDER_CLASS_PROGRAMS_BOOKING");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "offender_class_programs";
			String whereCondition = "offender_book_id = :p_offender_book_id";
			inputMap.put("p_offender_book_id", offenderBookId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprOffenderClassPrograms " + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_book_id",offenderBookId));
	}

	@Override
	public Integer deleteprOffenderClothes(Long offenderBookId,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_OFFENDER_CLOTHES_BOOKING");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "offender_clothes";
			String whereCondition = "offender_book_id = :p_offender_book_id";
			inputMap.put("p_offender_book_id", offenderBookId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprOffenderClothes " + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_book_id",offenderBookId));
	}

	@Override
	public Integer deleteprOffenderClothingBundles(Long offenderBookId,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_OFFENDER_CLOTHING_BUNDLES_BOOKING");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "offender_clothing_bundles";
			String whereCondition = "offender_book_id = :p_offender_book_id";
			inputMap.put("p_offender_book_id", offenderBookId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprOffenderClothingBundles " + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_book_id",offenderBookId));
	}

	@Override
	public List<OffenderClothingIssue> prOffenderClothingIssue(Long offenderBookId) {
		String sql=getQuery("BOOKING_GET_OFFENDER_CLOTHING_ISSUE_BOOKING");
		return namedParameterJdbcTemplate.query(sql, createParams("p_offender_book_id",offenderBookId),new BeanPropertyRowMapper<OffenderClothingIssue>(OffenderClothingIssue.class));
	}


	@Override
	public Integer deleteprOffenderClothingItems1(Long offenderClothingIssueId,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_OFFENDER_CLOTHING_ITEMS_BOOKING");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "offender_clothing_items";
			String whereCondition = "offender_clothing_issue_id = :p_offender_clothing_issu_id";
			inputMap.put("p_offender_clothing_issu_id", offenderClothingIssueId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprOffenderClothingItems1 " + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_clothing_issu_id",offenderClothingIssueId));
	}

	@Override
	public Integer deleteprOffenderClothingIssue(Long offenderBookId,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_OFFENDER_CLOTHING_ISSUE_BOOKING");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "offender_clothing_issue";
			String whereCondition = "offender_book_id = :p_offender_book_id";
			inputMap.put("p_offender_book_id", offenderBookId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprOffenderClothingIssue " + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_book_id",offenderBookId));
	}

	@Override
	public Integer deleteprOffenderClothingItems(Long offenderBookId,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_OFFENDER_CLOTHING_ITEMS1_BOOKING");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "offender_clothing_items";
			String whereCondition = "offender_book_id = :p_offender_book_id";
			inputMap.put("p_offender_book_id", offenderBookId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprOffenderClothingItems " + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_book_id",offenderBookId));
	}

	@Override
	public Integer deleteprOffenderCodefendants(Long codOffenderBookId,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_OFFENDER_CODEFENDANTS_BOOKING");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "offender_codefendants";
			String whereCondition = "cod_offender_book_id = :p_cod_offender_book_id";
			inputMap.put("p_cod_offender_book_id", codOffenderBookId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprOffenderCodefendants " + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_cod_offender_book_id",codOffenderBookId));
	}

	@Override
	public Integer deleteprOffenderCodefendants1(Long offenderBookId,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_OFFENDER_CODEFENDANTS1_BOOKING");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "offender_codefendants";
			String whereCondition = "offender_book_id = :p_offender_book_id";
			inputMap.put("p_offender_book_id", offenderBookId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprOffenderCodefendants1 " + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_book_id",offenderBookId));
	}

	@Override
	public Integer deleteprOffenderComments(Long offenderBookId,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_OFFENDER_COMMENTS_BOOKING");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "offender_comments";
			String whereCondition = "offender_book_id = :p_offender_book_id";
			inputMap.put("p_offender_book_id", offenderBookId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprOffenderComments " + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_book_id",offenderBookId));
	}

	@Override
	public Integer deleteprOffendrCommnityConditions(Long offenderBookId,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_OFFENDER_COMMUNITY_CONDITIONS_BOOKING");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "offender_community_conditions";
			String whereCondition = "offender_book_id = :p_offender_book_id";
			inputMap.put("p_offender_book_id", offenderBookId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprOffendrCommnityConditions " + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_book_id",offenderBookId));
	}

	@Override
	public Integer deleteprOffenderCommSupHistories(Long offenderBookId,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_OFFENDER_COMM_SUP_HISTORIES_BOOKING");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "offender_comm_sup_histories";
			String whereCondition = "offender_book_id = :p_offender_book_id";
			inputMap.put("p_offender_book_id", offenderBookId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprOffenderCommSupHistories " + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_book_id",offenderBookId));
	}

	@Override
	public Integer deleteprOffenderCompactAgreements(Long offenderBookId,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_OFFENDER_COMPACT_AGREEMENTS_BOOKING");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "offender_compact_agreements";
			String whereCondition = "offender_book_id = :p_offender_book_id";
			inputMap.put("p_offender_book_id", offenderBookId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprOffenderCompactAgreements " + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_book_id",offenderBookId));
	}

	@Override
	public List<OffenderContactPersons> prOffenderContactPersons1(BigDecimal offenderBookId) {
		String sql=getQuery("BOOKING_GET_OFFENDER_CONTACT_PERSONS1");
		return namedParameterJdbcTemplate.query(sql, createParams("p_offender_book_id",offenderBookId),new BeanPropertyRowMapper<OffenderContactPersons>(OffenderContactPersons.class));
	}

	@Override
	public Integer deleteprOffenderContactPersons1(BigDecimal offenderBookId,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_OFFENDER_CONTACT_PERSONS1_BOOKING");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "offender_contact_persons";
			String whereCondition = "offender_book_id = :p_offender_book_id";
			inputMap.put("p_offender_book_id", offenderBookId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprOffenderContactPersons1 " + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_book_id",offenderBookId));
	}

	@Override
	public List<OffenderCourseAttendances> prOffenderCourseAttendances(Long offenderBookId) {
		String sql=getQuery("BOOKING_GET_OFFENDER_COURSE_ATTENDANCES2");
		return namedParameterJdbcTemplate.query(sql, createParams("p_offender_book_id",offenderBookId),new BeanPropertyRowMapper<OffenderCourseAttendances>(OffenderCourseAttendances.class));
	}

	@Override
	public Integer deleteprOffenderCourseAttendances(Long offenderBookId,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_OFFENDER_COURSE_ATTENDANCES1");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "offender_course_attendances";
			String whereCondition = "offender_book_id = :p_offender_book_id";
			inputMap.put("p_offender_book_id", offenderBookId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprOffenderCourseAttendances " + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_book_id",offenderBookId));
	}

	@Override
	public List<OffenderCurfews> prOffenderCurfews(Long offenderBookId) {
		String sql=getQuery("BOOKING_GET_OFFENDER_CURFEWS_BOOKING");
		return namedParameterJdbcTemplate.query(sql, createParams("p_offender_book_id",offenderBookId),new BeanPropertyRowMapper<OffenderCurfews>(OffenderCurfews.class));
	}

	@Override
	public List<OffenderCurfews> prCurfewAddresses(BigDecimal offenderCurfewId) {
		String sql=getQuery("BOOKING_GET_CURFEW_ADDRESSES1_BOOKING");
		return namedParameterJdbcTemplate.query(sql, createParams("p_offender_curfew_id",offenderCurfewId),new BeanPropertyRowMapper<OffenderCurfews>(OffenderCurfews.class));
	}


	@Override
	public Integer deleteprCurfewAddresses(BigDecimal offenderCurfewId,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_CURFEW_ADDRESSES_BOOKING");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "curfew_addresses";
			String whereCondition = "offender_curfew_id = :p_offender_curfew_id";
			inputMap.put("p_offender_curfew_id", offenderCurfewId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprCurfewAddresses " + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_curfew_id",offenderCurfewId));
	}

	@Override
	public Integer deleteprHdcPrisonStaffComments(BigDecimal offenderCurfewId,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_HDC_PRISON_STAFF_COMMENTS_BOOKING");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "hdc_prison_staff_comments";
			String whereCondition = "offender_curfew_id = :p_offender_curfew_id";
			inputMap.put("p_offender_curfew_id", offenderCurfewId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprHdcPrisonStaffComments " + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_curfew_id",offenderCurfewId));
	}

	@Override
	public List<HdcRequestReferrals> prHdcRequestReferrals2(BigDecimal offenderCurfewId) {
		String sql=getQuery("BOOKING_GET_HDC_REQUEST_REFERRALS_BOOKING");
		return namedParameterJdbcTemplate.query(sql, createParams("p_offender_curfew_id",offenderCurfewId),new BeanPropertyRowMapper<HdcRequestReferrals>(HdcRequestReferrals.class));
	}

	@Override
	public Integer deleteprHdcRequestReferrals2(BigDecimal offenderCurfewId,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_HDC_REQUEST_REFERRALS2_BOOKING");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "hdc_request_referrals";
			String whereCondition = "offender_curfew_id = :p_offender_curfew_id";
			inputMap.put("p_offender_curfew_id", offenderCurfewId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprHdcRequestReferrals2 " + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_curfew_id",offenderCurfewId));
	}

	@Override
	public Integer deleteprOffenderCurfews(Long offenderBookId,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_OFFENDER_CURFEWS_BOOKING");
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
	public List<OffenderDrugAdmission> prOffenderDrugAdmissions(Long offenderBookId) {
		String sql=getQuery("BOOKING_GET_OFFENDER_DRUG_ADMISSIONS_BOOKING");
		return namedParameterJdbcTemplate.query(sql, createParams("p_offender_book_id",offenderBookId),new BeanPropertyRowMapper<OffenderDrugAdmission>(OffenderDrugAdmission.class));
	}

	@Override
	public Integer deleteprOffenderDrugAdmDrugs(Long offenderDrugAdmissionId,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_OFFENDER_DRUG_ADM_DRUGS_BOOKING");
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
	public Integer deleteprOffenderDrugAdmissions(Long offenderBookId,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_OFFENDER_DRUG_ADMISSIONS_BOOKING");
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
	public List<OffenderDrugSample> prOffenderDrugSamples(Long offenderBookId) {
		String sql=getQuery("BOOKING_GET_OFFENDER_DRUG_SAMPLES_BOOKING");
		RowMapper<OffenderDrugSample> mapper= Row2BeanRowMapper.makeMapping(sql, OffenderDrugSample.class, courtListMapping);
		return namedParameterJdbcTemplate.query(sql, createParams("p_offender_book_id",offenderBookId),mapper);
	}

	@Override
	public Integer deleteprOffenderDrugResults(Long offenderDrugSampleId,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_OFFENDER_DRUG_RESULTS_BOOKING");
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
	public Integer deleteprOffenderDrugSamples(Long offenderBookId,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_OFFENDER_DRUG_SAMPLES_BOOKING");
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
	public Integer deleteprOffenderEducations(Long offenderBookId,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_OFFENDER_EDUCATIONS_BOOKING");
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
	public Integer deleteprOffenderEmployments(Long offenderBookId,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_OFFENDER_EMPLOYMENTS_BOOKING");
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
	public Integer deleteprOffenderEscapes(Integer offenderBookId,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_OFFENDER_ESCAPES_BOOKING");
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
	public Integer deleteprOffenderExpenses(Long offenderBookId,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_OFFENDER_EXPENSES_BOOKING");
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
	public Integer deleteprOffenderExternalMovements(Long offenderBookId,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_OFFENDER_EXTERNAL_MOVEMENTS_BOOKING");
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
	public Integer deleteprOffenderFileLocations(Long offenderBookId,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_OFFENDER_FILE_LOCATIONS_BOOKING");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "offender_file_locations";
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
	public Integer deleteprOffenderGangAffiliations(Long offenderBookId,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_OFFENDER_GANG_AFFILIATIONS_BOOKING");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "offender_gang_affiliations";
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
	public Integer deleteprOffenderGangEvidences(Long offenderBookId,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_OFFENDER_GANG_EVIDENCES_BOOKING");
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
	public Integer deleteprOffenderGangInvests(Long offenderBookId,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_OFFENDER_GANG_INVESTS_BOOKING");
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
		String sql=getQuery("BOOKING_OFFENDER_GRIEVANCES_BOOKING");
		return namedParameterJdbcTemplate.query(sql, createParams("p_offender_book_id",offenderBookId),new BeanPropertyRowMapper<OffenderGrievances>(OffenderGrievances.class));
	}

	@Override
	public Integer deleteprOffenderGrievanceTxns(Long grievanceId,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_OFFENDER_GRIEVANCE_TXNS_BOOKING");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "offender_grievance_txns";
			String whereCondition = "grievance_id = :p_grievance_id";
			inputMap.put("p_grievance_id", grievanceId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprOffenderGrievanceTxns " + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_book_id",grievanceId));
	}

	@Override
	public Integer deleteprOffenderGrievStaffs(Long grievanceId,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_OFFENDER_GRIEV_STAFFS_BOOKING");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "offender_griev_staffs";
			String whereCondition = "grievance_id = :p_grievance_id";
			inputMap.put("p_grievance_id", grievanceId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprOffenderGrievStaffs " + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_grievance_id",grievanceId));
	}

	@Override
	public Integer deleteprOffenderGrievances(Long offenderBookId,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_OFFENDER_GRIEVANCES_BOOKING");
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
	public Integer deleteprOffenderHospitalVisits(Long offenderBookId,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_OFFENDER_HOSPITAL_VISITS_BOOKING");
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
		String sql=getQuery("BOOKING_GET_OFFENDER_HWD_BOOKING");
		return namedParameterJdbcTemplate.query(sql, createParams("p_offender_book_id",offenderBookId),new BeanPropertyRowMapper<OffenderHwd>(OffenderHwd.class));
	}

	@Override
	public Integer deleteprOffenderHwdCharges(Long hwdId,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_OFFENDER_HWD_CHARGES_BOOKING");
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
	public Integer deleteprOffenderHwdHty(Long hwdId,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_OFFENDER_HWD_HTY_BOOKING");
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
	public Integer deleteprOffenderHwd(Long offenderBookId,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_OFFENDER_HWD_BOOKING");
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
	public Integer deleteprOffenderIdentifyingMarks(Long offenderBookId,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_OFFENDER_IDENTIFYING_MARKS_BOOKING");
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
	public Integer deleteprOffenderIepLevels(Long offenderBookId,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_OFFENDER_IEP_LEVELS_BOOKING");
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
	public Integer deleteprOffenderImages(Long offenderBookId,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_OFFENDER_IMAGES_BOOKING");
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
	public Integer deleteprOffenderImprisonStatuses(Long offenderBookId,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_OFFENDER_IMPRISON_STATUSES_BOOKING");
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
	public Integer deleteprOffenderIncome(Long offenderBookId,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_OFFENDER_INCOME_BOOKING");
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
	public List<OffenderIndSchedules> prOffenderIndSchedules(Long offenderBookId) {
		String sql=getQuery("BOOKING_GET_OFFENDER_IND_SCHEDULES_BOOKING");
		return namedParameterJdbcTemplate.query(sql, createParams("p_offender_book_id",offenderBookId),new BeanPropertyRowMapper<OffenderIndSchedules>(OffenderIndSchedules.class));
	}

	@Override
	public Integer deleteprOffenderIndSchSents1(Long eventId,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_OFFENDER_IND_SCH_SENTS1_BOOKING");
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
	public Integer deleteprOffenderIndSchedules(Long offenderBookId,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_OFFENDER_IND_SCHEDULES_BOOKING");
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
		String sql=getQuery("BOOKING_GET_OFFENDER_INTERNAL_MOVEMENTS_BOOKING");
		RowMapper<OffenderExternalMovements> mapper= Row2BeanRowMapper.makeMapping(sql, OffenderExternalMovements.class, courtListMapping);
		return namedParameterJdbcTemplate.query(sql, createParams("p_offender_book_id",offenderBookId),mapper);
	}

	@Override
	public Integer deleteprOffenderIntMovPersons(Long offenderBookId, Long movementSeq,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_OFFENDER_INT_MOV_PERSONS_BOOKING");
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
	public Integer deleteprOffenderInternalMovements(Long offenderBookId,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_OFFENDER_INTERNAL_MOVEMENTS_BOOKING");
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
	public Integer deleteprOffenderInternalStatuses(Integer offenderBookId,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_OFFENDER_INTERNAL_STATUSES_BOOKING");
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
	public Integer deleteprOffenderInterventionTiers(Long offenderBookId,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_OFFENDER_INTERVENTION_TIERS_BOOKING");
		try {
			 Map<String, Object> inputMap = new HashMap<String, Object>();
			 String tableName = "offender_intervention_tiers";
			String whereCondition = "offender_book_id = :p_offender_book_id";
			inputMap.put("p_offender_book_id",offenderBookId);
			 inputMap.put("modifyUserId",modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
			}catch(Exception e){
			logger.error(this.getClass().getName() + " error in deleteprOffenderInterventionTiers " + e.getMessage());
			}
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_book_id",offenderBookId));
	}

	@Override
	public Integer deleteprOffenderLanguages(Long offenderBookId,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_OFFENDER_LANGUAGES_BOOKING");
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
	public Integer deleteprOffenderLiabilities(Long offenderBookId,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_OFFENDER_LIABILITIES_BOOKING");
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
	public Integer deleteprOffenderMailLogs(Long offenderBookId,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_OFFENDER_MAIL_LOGS1");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "offender_mail_logs";
			String whereCondition = "offender_book_id = :p_offender_book_id";
			inputMap.put("p_offender_book_id", offenderBookId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprOffenderMailLogs" + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_book_id",offenderBookId));
	}

	@Override
	public Integer deleteprOffenderMailRestrictions(Long offenderBookId,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_OFFENDER_MAIL_RESTRICTIONS1_BOOKING");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "offender_mail_restrictions";
			String whereCondition = "offender_book_id = :p_offender_book_id";
			inputMap.put("p_offender_book_id", offenderBookId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprOffenderMailRestrictions" + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_book_id",offenderBookId));
	}

	@Override
	public Integer deleteprOffendrMilitryDiscCtions(Long offenderBookId,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_OFFENDER_MILITARY_DISC_ACTIONS_BOOKING");
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
	public Integer deleteprOffenderMilitaryRecords(Long offenderBookId,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_OFFENDER_MILITARY_RECORDS_BOOKING");
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
	public Integer deleteprOffenderMilitaryWarZones(Long offenderBookId,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_OFFENDER_MILITARY_WAR_ZONES_BOOKING");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "offender_military_war_zones";
			String whereCondition = "offender_book_id = :p_offender_book_id";
			inputMap.put("p_offender_book_id", offenderBookId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprOffenderMilitaryWarZones " + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_book_id",offenderBookId));
	}

	@Override
	public List<OffenderOicSanctions> prOffenderOasysPlans(Long offenderBookId) {
		String sql=getQuery("BOOKING_GET_OFFENDER_OASYS_PLANS");
		RowMapper<OffenderOicSanctions> mapper= Row2BeanRowMapper.makeMapping(sql, OffenderOicSanctions.class, courtListMapping);
		return namedParameterJdbcTemplate.query(sql, createParams("p_offender_book_id",offenderBookId),mapper);
	}

	@Override
	public Integer deleteprOffenderOasysConcerns(Long offenderBookId, Long planSeq,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_OFFENDER_OASYS_CONCERNS_BOOKING");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "offender_oasys_concerns";
			String whereCondition = "offender_book_id = :p_offender_book_id AND plan_seq = :p_plan_seq";
			inputMap.put("p_offender_book_id", offenderBookId);
			inputMap.put("p_plan_seq", planSeq);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprOffenderOasysConcerns " + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_book_id",offenderBookId,"p_plan_seq",planSeq));
	}

	@Override
	public Integer deleteprOffenderOsysRiskToOthrs(Long offenderBookId, Long planSeq,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_OFFENDER_OASYS_RISK_TO_OTHERS_BOOKING");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "offender_oasys_risk_to_others";
			String whereCondition = "offender_book_id = :p_offender_book_id AND plan_seq = :p_plan_seq";
			inputMap.put("p_offender_book_id", offenderBookId);
			inputMap.put("p_plan_seq", planSeq);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprOffenderOsysRiskToOthrs " + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_book_id",offenderBookId,"p_plan_seq",planSeq));
	}

	@Override
	public List<OffenderOasysSections> prOffenderOasysSections(Long offenderBookId, Long planSeq) {
		String sql=getQuery("BOOKING_GET_OFFENDER_OASYS_SECTIONS_BOOKING");
		RowMapper<OffenderOasysSections> mapper= Row2BeanRowMapper.makeMapping(sql, OffenderOasysSections.class, courtListMapping);
		return namedParameterJdbcTemplate.query(sql, createParams("p_offender_book_id",offenderBookId,"p_plan_seq",planSeq),mapper);
	}

	@Override
	public List<OffenderOasysSections> prOffenderOasysObjectives(Long offenderBookId, Long planSeq, String sectionCode) {
		String sql=getQuery("BOOKING_GET_OFFENDER_OASYS_OBJECTIVES_BOOKING");
		return namedParameterJdbcTemplate.query(sql, createParams("p_offender_book_id",offenderBookId,"p_plan_seq",planSeq,"p_section_code",sectionCode),new BeanPropertyRowMapper<OffenderOasysSections>(OffenderOasysSections.class));
	}

	@Override
	public List<OffenderOasysSections> prOffenderOasysActions(BigDecimal objectiveId) {
		String sql=getQuery("BOOKING_GET_OFFENDER_OASYS_ACTIONS_BOOKING");
		RowMapper<OffenderOasysSections> mapper= Row2BeanRowMapper.makeMapping(sql, OffenderOasysSections.class, courtListMapping);
		return namedParameterJdbcTemplate.query(sql, createParams("p_objective_id",objectiveId),mapper);
	}

	@Override
	public Integer deleteprOffenderOasysSupervisions(BigDecimal objectiveId, Long actionSeq,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_OFFENDER_OASYS_SUPERVISIONS_BOOKING");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "offender_oasys_supervisions";
			String whereCondition = "objective_id = :p_objective_id AND action_seq = :p_action_seq";
			inputMap.put("p_objective_id", objectiveId);
			inputMap.put("p_action_seq", actionSeq);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprOffenderOasysSupervisions " + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_objective_id",objectiveId,"p_action_seq",actionSeq));
	}

	@Override
	public Integer deleteprOffenderOasysActions(BigDecimal objectiveId,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_OFFENDER_OASYS_ACTIONS");
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
	public Integer deleteprOffenderOasysObjectives(Long offenderBookId, Long planSeq, String sectionCode,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_OFFENDER_OASYS_OBJECTIVES_BOOKING");
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
	public Integer deleteprOffenderOasysSections(Long offenderBookId, Long planSeq,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_OFFENDER_OASYS_SECTIONS");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "offender_oasys_sections";
			String whereCondition = "offender_book_id = :p_offender_book_id AND plan_seq = :p_plan_seq";
			inputMap.put("p_offender_book_id", offenderBookId);
			inputMap.put("p_plan_seq", planSeq);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprOffenderOasysSections " + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_book_id",offenderBookId,"p_plan_seq",planSeq));
	}

	@Override
	public Integer deleteprOffenderOasysPlans(Long offenderBookId,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_OFFENDER_OASYS_PLANS");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "offender_oasys_plans";
			String whereCondition = "offender_book_id = :p_offender_book_id";
			inputMap.put("p_offender_book_id", offenderBookId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprOffenderOasysPlans " + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_book_id",offenderBookId));
	}

	@Override
	public List<OffenderOicSanctions> prOffenderOicSanctions(Long offenderBookId) {
		String sql=getQuery("BOOKING_GET_OFFENDER_OIC_SANCTIONS2_BOOKING");
		RowMapper<OffenderOicSanctions> mapper= Row2BeanRowMapper.makeMapping(sql, OffenderOicSanctions.class, courtListMapping);
		return namedParameterJdbcTemplate.query(sql, createParams("p_offender_book_id",offenderBookId),mapper);
	}

	@Override
	public Integer deleteprOffenderOicSanctions(Long offenderBookId,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_OFFENDER_OIC_SANCTIONS2");
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
		String sql=getQuery("BOOKING_GET_OFFENDER_PAROLE_HEARINGS_BOOKING");
		RowMapper<OffenderOicSanctions> mapper= Row2BeanRowMapper.makeMapping(sql, OffenderOicSanctions.class, courtListMapping);
		return namedParameterJdbcTemplate.query(sql, createParams("p_offender_book_id",offenderBookId),mapper);
	}

	@Override
	public List<OffenderOicSanctions> prOffenderParoleDecisions(Long offenderBookId, Long paroleHearingSeq) {
		String sql=getQuery("BOOKING_GET_OFFENDER_PAROLE_DECISIONS_BOOKING");
		RowMapper<OffenderOicSanctions> mapper= Row2BeanRowMapper.makeMapping(sql, OffenderOicSanctions.class, courtListMapping);
		return namedParameterJdbcTemplate.query(sql, createParams("p_offender_book_id",offenderBookId,"p_parole_hearing_seq",paroleHearingSeq),mapper);
	}

	@Override
	public Integer deleteprOffenderParoleStipultions(Long scheduleId, Integer paroleDecisionSeq,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_OFFENDER_OIC_SANCTIONS2");
		return namedParameterJdbcTemplate.update(sql, createParams("p_schedule_id",scheduleId,"p_parole_decision_seq",paroleDecisionSeq));
	}

	@Override
	public Integer deleteprOffenderParoleConditions(Long scheduleId, Integer paroleDecisionSeq,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_OFFENDER_PAROLE_CONDITIONS_BOOKING");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "offender_parole_conditions";
			String whereCondition = "schedule_id = :p_schedule_id AND parole_decision_seq = :p_parole_decision_seq";
			inputMap.put("p_schedule_id", scheduleId);
			inputMap.put("p_parole_decision_seq", paroleDecisionSeq);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprOffenderParoleConditions " + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_schedule_id",scheduleId,"p_parole_decision_seq",paroleDecisionSeq));
	}

	@Override
	public Integer deleteprOffenderParoleDecisions(Long offenderBookId, Long paroleHearingSeq,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_OFFENDER_PAROLE_DECISIONS1");
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
	public Integer deleteprOffenderParoleHearings(Long offenderBookId,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_OFFENDER_PAROLE_HEARINGS");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "offender_parole_hearings";
			String whereCondition = "offender_book_id = :p_offender_book_id";
			inputMap.put("p_offender_book_id", offenderBookId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprOffenderParoleHearings " + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_book_id",offenderBookId));
	}

	@Override
	public Integer deleteprOffenderParolePlans(Long offenderBookId,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_OFFENDER_PAROLE_PLANS_BOOKING");
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
	public Integer deleteprOffenderPlanedActivities(Long offenderBookId,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_OFFENDER_PLANED_ACTIVITIES_BOOKING");
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
	public Integer deleteprOffenderPptyContainers(Long offenderBookId,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_OFFENDER_PPTY_CONTAINERS_BOOKING");
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
	public Integer deleteprOffenderPptyItems(Long offenderBookId,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_OFFENDER_PPTY_ITEMS_BOOKING");
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
	public List<OffenderPptyItemEvents> prOffenderPptyItemEvents(Integer offenderBookId) {
		String sql=getQuery("BOOKING_GET_OFFENDER_PPTY_ITEM_EVENTS_BOOKING");
		RowMapper<OffenderPptyItemEvents> mapper= Row2BeanRowMapper.makeMapping(sql, OffenderPptyItemEvents.class, courtListMapping);
		return namedParameterJdbcTemplate.query(sql, createParams("p_offender_book_id",offenderBookId),mapper);
	}

	@Override
	public Integer deleteprOffenderPptyItemTxns(Integer offenderBookId, Integer eventSeq,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_OFFENDER_PPTY_ITEM_TXNS_BOOKING");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "offender_ppty_item_txns";
			String whereCondition = "offender_book_id = :p_offender_book_id AND event_seq = :p_event_seq";
			inputMap.put("p_offender_book_id", offenderBookId);
			inputMap.put("p_event_seq", eventSeq);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprOffenderPptyItemTxns " + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_book_id",offenderBookId,"p_event_seq",eventSeq));
	}

	@Override
	public Integer deleteprOffenderPptyItemEvents(Integer offenderBookId,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_OFFENDER_PPTY_ITEM_EVENTS_BOOKING");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "offender_ppty_item_events";
			String whereCondition = "offender_book_id = :p_offender_book_id";
			inputMap.put("p_offender_book_id", offenderBookId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprOffenderPptyItemEvents " + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_book_id",offenderBookId));
	}

	@Override
	public List<OffenderPrgObligations> prOffenderPrgObligations(Long offenderBookId) {
		String sql=getQuery("BOOKING_GET_OFFENDER_PRG_OBLIGATIONS_BOOKING");
		return namedParameterJdbcTemplate.query(sql, createParams("p_offender_book_id",offenderBookId),new BeanPropertyRowMapper<OffenderPrgObligations>(OffenderPrgObligations.class));
	}

	@Override
	public List<OffenderCourseAttendances> prOffenderCourseAttendancs1(Long offenderPrgObligationId) {
		String sql=getQuery("BOOKING_GET_OFFENDER_COURSE_ATTENDANCES3_BOOKING");
		return namedParameterJdbcTemplate.query(sql, createParams("p_offender_prg_obligtion_id",offenderPrgObligationId),new BeanPropertyRowMapper<OffenderCourseAttendances>(OffenderCourseAttendances.class));
	}


	@Override
	public Integer deleteprOffenderCourseAttendancs1(Long offenderPrgObligationId,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_OFFENDER_COURSE_ATTENDANCES2_BOOKING");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "offender_course_attendances";
			String whereCondition = "offender_prg_obligation_id = :p_offender_prg_obligtion_id";
			inputMap.put("p_offender_prg_obligtion_id", offenderPrgObligationId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprOffenderCourseAttendancs1 " + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_prg_obligtion_id",offenderPrgObligationId));
	}

	@Override
	public List<OffenderIndSchedules> prOffenderIndSchedules1(Integer offenderPrgObligationId) {
		String sql=getQuery("BOOKING_GET_OFFENDER_IND_SCHEDULES1_BOOKING");
		return namedParameterJdbcTemplate.query(sql, createParams("p_offender_prg_obligtion_id",offenderPrgObligationId),new BeanPropertyRowMapper<OffenderIndSchedules>(OffenderIndSchedules.class));
	}

	@Override
	public Integer deleteprOffenderObligationAdjs(Integer offenderPrgObligationId,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_OFFENDER_OBLIGATION_ADJS_BOOKING");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "offender_obligation_adjs";
			String whereCondition = "offender_prg_obligation_id = :p_offender_prg_obligtion_id";
			inputMap.put("p_offender_prg_obligtion_id", offenderPrgObligationId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprOffenderObligationAdjs " + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_prg_obligtion_id",offenderPrgObligationId));
	}

	@Override
	public Integer deleteprOffenderPrgObligationHty(Integer offenderPrgObligationId,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_OFFENDER_PRG_OBLIGATION_HTY_BOOKING");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "offender_prg_obligation_hty";
			String whereCondition = "offender_prg_obligation_id = :p_offender_prg_obligtion_id";
			inputMap.put("p_offender_prg_obligtion_id", offenderPrgObligationId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprOffenderPrgObligationHty " + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_prg_obligtion_id",offenderPrgObligationId));
	}

	@Override
	public List<OffenderProgramProfiles> prOffenderProgramProfiles1(Integer offenderPrgObligationId) {
		String sql=getQuery("BOOKING_GET_OFFENDER_PROGRAM_PROFILES2_BOOKING");
		return namedParameterJdbcTemplate.query(sql, createParams("p_offender_prg_obligtion_id",offenderPrgObligationId),new BeanPropertyRowMapper<OffenderProgramProfiles>(OffenderProgramProfiles.class));
	}
	
	@Override
	public Integer deleteprOffenderProgramProfiles1(Integer offenderPrgObligationId,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_OFFENDER_PROGRAM_PROFILES2_BOOKING");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "offender_program_profiles";
			String whereCondition = "offender_prg_obligation_id = :p_offender_prg_obligtion_id";
			inputMap.put("p_offender_prg_obligtion_id", offenderPrgObligationId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprOffenderProgramProfiles1 " + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_prg_obligtion_id",offenderPrgObligationId));
	}
	
	@Override
	public Integer deleteprOffenderPrgObligations(Long offenderBookId,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_OFFENDER_PRG_OBLIGATIONS_BOOKING");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "offender_prg_obligations";
			String whereCondition = "offender_book_id = :p_offender_book_id";
			inputMap.put("p_offender_book_id", offenderBookId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprOffenderPrgObligations " + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_book_id",offenderBookId));
	}
	
	@Override
	public List<OffenderProceedings> prOffenderProceedings(BigDecimal offenderBookId) {
		String sql=getQuery("BOOKING_GET_OFFENDER_PROCEEDINGS_BOOKING");
		return namedParameterJdbcTemplate.query(sql, createParams("p_offender_book_id",offenderBookId),new BeanPropertyRowMapper<OffenderProceedings>(OffenderProceedings.class));
	}
	
	@Override
	public List<CourtEvents> prCourtEvents2(Integer offenderProceedingId) {
		String sql=getQuery("BOOKING_GET_COURT_EVENTS2");
		return namedParameterJdbcTemplate.query(sql, createParams("p_offender_proceeding_id",offenderProceedingId),new BeanPropertyRowMapper<CourtEvents>(CourtEvents.class));
	}
	
	@Override
	public Integer deleteprCourtEvents2(Integer offenderProceedingId,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_COURT_EVENTS2_BOOKING");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "court_events";
			String whereCondition = "offender_proceeding_id = :p_offender_proceeding_id";
			inputMap.put("p_offender_proceeding_id", offenderProceedingId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprCourtEvents2 " + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_proceeding_id",offenderProceedingId));
	}
	
	@Override
	public Integer deleteprOffenderProceedingSents1(BigDecimal offenderProceedingId,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_OFFENDER_PROCEEDING_SENTS1_BOOKING");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "offender_proceeding_sents";
			String whereCondition = "offender_proceeding_id = :p_offender_proceeding_id";
			inputMap.put("p_offender_proceeding_id", offenderProceedingId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprOffenderProceedingSents1 " + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_proceeding_id",offenderProceedingId));
	}
	
	@Override
	public Integer deleteprOffenderProceedings(BigDecimal offenderBookId,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_OFFENDER_PROCEEDINGS_BOOKING");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "offender_proceedings";
			String whereCondition = "offender_book_id = :p_offender_book_id";
			inputMap.put("p_offender_book_id", offenderBookId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprOffenderProceedings " + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_book_id",offenderBookId));
	}
	
	@Override
	public Integer deleteprOffenderProfiles(BigDecimal offenderBookId,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_OFFENDER_PROFILES");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "offender_profiles";
			String whereCondition = "offender_book_id = :p_offender_book_id";
			inputMap.put("p_offender_book_id", offenderBookId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprOffenderProfiles " + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_book_id",offenderBookId));
	}
	
	@Override
	public Integer deleteprOffenderPropertyBundles(BigDecimal offenderBookId,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_OFFENDER_PROPERTY_BUNDLES_BOOKING");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "offender_property_bundles";
			String whereCondition = "offender_book_id = :p_offender_book_id";
			inputMap.put("p_offender_book_id", offenderBookId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprOffenderPropertyBundles " + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_book_id",offenderBookId));
	}
	
	@Override
	public List<OffenderProgramProfiles> prOffenderProgramProfiles(BigDecimal offenderBookId) {
		String sql=getQuery("BOOKING_GET_OFFENDER_PROGRAM_PROFILES4_BOOKING");
		return namedParameterJdbcTemplate.query(sql, createParams("p_offender_book_id",offenderBookId),new BeanPropertyRowMapper<OffenderProgramProfiles>(OffenderProgramProfiles.class));
	}
	
	@Override
	public Integer deleteprOffenderProgramProfiles(BigDecimal offenderBookId,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_OFFENDER_PROGRAM_PROFILES3_BOOKING");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "offender_program_profiles";
			String whereCondition = "offender_book_id = :p_offender_book_id";
			inputMap.put("p_offender_book_id", offenderBookId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprOffenderProgramProfiles " + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_book_id",offenderBookId));
	}
	
	@Override
	public List<OffenderExternalMovements> prOffenderProposedMvmnts(BigDecimal offenderBookId) {
		String sql=getQuery("BOOKING_GET_OFFENDER_PROPOSED_MVMNTS_BOOKING");
		RowMapper<OffenderExternalMovements> mapper= Row2BeanRowMapper.makeMapping(sql, OffenderExternalMovements.class, courtListMapping);
		return namedParameterJdbcTemplate.query(sql, createParams("p_offender_book_id",offenderBookId),mapper);
	}
	
	@Override
	public Integer deleteprOffenderMovementDetails(Long offenderBookId, Long movementSeq,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_OFFENDER_MOVEMENT_DETAILS_BOOKING");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "offender_movement_details";
			String whereCondition = "offender_book_id = :p_offender_book_id AND movement_seq = :p_movement_seq";
			inputMap.put("p_offender_book_id", offenderBookId);
			inputMap.put("p_movement_seq", movementSeq);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprOffenderMovementDetails " + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_book_id",offenderBookId,"p_movement_seq",movementSeq));
	}
	
	@Override
	public Integer deleteprOffenderProposedMvmnts(BigDecimal offenderBookId,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_OFFENDER_PROPOSED_MVMNTS_BOOKING");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "offender_proposed_mvmnts";
			String whereCondition = "offender_book_id = :p_offender_book_id";
			inputMap.put("p_offender_book_id", offenderBookId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprOffenderProposedMvmnts " + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_book_id",offenderBookId));
	}
	
	@Override
	public List<OffenderPtr> proffenderptr(Long offenderBookId) {
		String sql=getQuery("BOOKING_GET_OFFENDER_PTR_BOOKING");
		return namedParameterJdbcTemplate.query(sql, createParams("p_offender_book_id",offenderBookId),new BeanPropertyRowMapper<OffenderPtr>(OffenderPtr.class));
	}
	
	@Override
	public Integer deleteprExtOwnershipTransfer(Long ptrId,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_EXT_OWNERSHIP_TRANSFER_BOOKING");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "ext_ownership_transfer";
			String whereCondition = "ptr_id = :p_ptr_id";
			inputMap.put("p_ptr_id", ptrId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprExtOwnershipTransfer " + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_ptr_id",ptrId));
	}
	
	@Override
	public Integer deleteprOffenderBookingAgyLocs1(BigDecimal ptrId,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_OFFENDER_BOOKING_AGY_LOCS1_BOOKING");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "offender_booking_agy_locs";
			String whereCondition = "ptr_id = :p_ptr_id";
			inputMap.put("p_ptr_id", ptrId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprOffenderBookingAgyLocs1 " + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_ptr_id",ptrId));
	}
	
	@Override
	public Integer deleteprOffenderPtrDetails(BigDecimal ptrId,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_OFFENDER_PTR_DETAILS_BOOKING");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "offender_ptr_details";
			String whereCondition = "ptr_id = :p_ptr_id";
			inputMap.put("p_ptr_id", ptrId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprOffenderPtrDetails" + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_ptr_id",ptrId));
	}
	
	@Override
	public Integer deleteproffenderptr(Long offenderBookId,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_OFFENDER_PTR_BOOKING");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "offender_ptr";
			String whereCondition = "offender_book_id = :p_offender_book_id";
			inputMap.put("p_offender_book_id", offenderBookId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteproffenderptr " + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_book_id",offenderBookId));
	}
	
	@Override
	public Integer deleteprOffenderReferrals(Long offenderBookId,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_OFFENDER_REFERRALS_BOOKING");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "offender_referrals";
			String whereCondition = "offender_book_id = :p_offender_book_id";
			inputMap.put("p_offender_book_id", offenderBookId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprOffenderReferrals " + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_book_id",offenderBookId));
	}
	
	@Override
	public Integer deleteprOffenderReleaseDetails(BigDecimal offenderBookId,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_OFFENDER_RELEASE_DETAILS_BOOKING");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "offender_release_details";
			String whereCondition = "offender_book_id = :p_offender_book_id";
			inputMap.put("p_offender_book_id", offenderBookId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprOffenderReleaseDetails " + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_book_id",offenderBookId));
	}
	
	@Override
	public Integer deleteprOffenderResidences(BigDecimal offenderBookId,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_OFFENDER_RESIDENCES_BOOKING");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "offender_residences";
			String whereCondition = "offender_book_id = :p_offender_book_id";
			inputMap.put("p_offender_book_id", offenderBookId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprOffenderResidences " + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_book_id",offenderBookId));
	}
	
	@Override
	public Integer deleteprOffenderRestrictions(BigDecimal offenderBookId,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_OFFENDER_RESTRICTIONS");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "offender_restrictions";
			String whereCondition = "offender_book_id = :p_offender_book_id";
			inputMap.put("p_offender_book_id", offenderBookId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprOffenderRestrictions " + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_book_id",offenderBookId));
	}
	
	@Override
	public Integer deleteprOffenderRiskPredictors(BigDecimal offenderBookId,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_OFFENDER_RISK_PREDICTORS_BOOKING");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "offender_risk_predictors";
			String whereCondition = "offender_book_id = :p_offender_book_id";
			inputMap.put("p_offender_book_id", offenderBookId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprOffenderRiskPredictors " + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_book_id",offenderBookId));
	}
	
	@Override
	public Integer deleteprOffenderSentCalculations(BigDecimal offenderBookId,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_OFFENDER_SENT_CALCULATIONS_BOOKING");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "offender_sent_calculations";
			String whereCondition = "offender_book_id = :p_offender_book_id";
			inputMap.put("p_offender_book_id", offenderBookId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprOffenderSentCalculations " + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_book_id",offenderBookId));
	}
	
	@Override
	public List<OffenderStgAffiliations> prOffenderStgAffiliations(BigDecimal offenderBookId) {
		String sql=getQuery("BOOKING_GET_OFFENDER_STG_AFFILIATIONS_BOOKING");
		RowMapper<OffenderStgAffiliations> mapper= Row2BeanRowMapper.makeMapping(sql, OffenderStgAffiliations.class, courtListMapping);
		return namedParameterJdbcTemplate.query(sql, createParams("p_offender_book_id",offenderBookId),mapper);
	}
	
	@Override
	public Integer deleteprOffenderStgDetails(BigDecimal offenderBookId, BigDecimal stgSeq,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_OFFENDER_STG_DETAILS_BOOKING");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "offender_stg_details";
			String whereCondition = "offender_book_id = :p_offender_book_id AND stg_seq = :p_stg_seq";
			inputMap.put("p_offender_book_id", offenderBookId);
			inputMap.put("p_stg_seq", stgSeq);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprOffenderStgDetails " + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_book_id",offenderBookId,"p_stg_seq",stgSeq));
	}
	
	@Override
	public Integer deleteprOffenderStgAffiliations(BigDecimal offenderBookId,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_OFFENDER_STG_AFFILIATIONS_BOOKING");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "offender_stg_affiliations";
			String whereCondition = "offender_book_id = :p_offender_book_id";
			inputMap.put("p_offender_book_id", offenderBookId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprOffenderStgAffiliations " + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_book_id",offenderBookId));
	}
	
	@Override
	public Integer deleteprOffenderStgAssociations(BigDecimal offenderBookId,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_OFFENDER_STG_ASSOCIATIONS_BOOKING");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "offender_stg_associations";
			String whereCondition = "offender_book_id = :p_offender_book_id";
			inputMap.put("p_offender_book_id", offenderBookId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprOffenderStgAssociations " + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_book_id",offenderBookId));
	}
	
	@Override
	public Integer deleteprOffenderSubstanceAbuses(BigDecimal offenderBookId,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_OFFENDER_SUBSTANCE_ABUSES_BOOKING");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "offender_substance_abuses";
			String whereCondition = "offender_book_id = :p_offender_book_id";
			inputMap.put("p_offender_book_id", offenderBookId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprOffenderSubstanceAbuses " + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_book_id",offenderBookId));
	}
	
	@Override
	public Integer deleteprOffenderSupervisingCourts(BigDecimal offenderBookId,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_OFFENDER_SUPERVISING_COURTS_BOOKING");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "offender_supervising_courts";
			String whereCondition = "offender_book_id = :p_offender_book_id";
			inputMap.put("p_offender_book_id", offenderBookId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprOffenderSupervisingCourts " + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_book_id",offenderBookId));
	}
	
	@Override
	public List<AssessmentResults> prOffenderSupervisionLevels(BigDecimal offenderBookId) {
		String sql=getQuery("BOOKING_GET_OFFENDER_SUPERVISION_LEVELS_BOOKING");
		RowMapper<AssessmentResults> mapper= Row2BeanRowMapper.makeMapping(sql, AssessmentResults.class, courtListMapping);
		return namedParameterJdbcTemplate.query(sql, createParams("p_offender_book_id",offenderBookId),mapper);
	}
	
	@Override
	public List<OffenderOicSanctions> prOffenderSupervisionPlans(Long offenderBookId, String supervisionLevelType) {
		String sql=getQuery("BOOKING_GET_OFFENDER_SUPERVISION_PLANS_BOOKING");
		RowMapper<OffenderOicSanctions> mapper= Row2BeanRowMapper.makeMapping(sql, OffenderOicSanctions.class, courtListMapping);
		return namedParameterJdbcTemplate.query(sql, createParams("p_offender_book_id",offenderBookId,"p_supervision_level_type",supervisionLevelType),mapper);
	}
	
	@Override
	public Integer deleteprOffenderPlanedActivities1(Long offenderBookId, Long planSeq,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_OFFENDER_PLANED_ACTIVITIES1_BOOKING");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "offender_planed_activities";
			String whereCondition = "offender_book_id = :p_offender_book_id AND plan_seq = :p_plan_seq";
			inputMap.put("p_offender_book_id", offenderBookId);
			inputMap.put("p_plan_seq", planSeq);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprOffenderPlanedActivities1 " + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_book_id",offenderBookId,"p_plan_seq",planSeq));
	}
	
	@Override
	public Integer deleteprOffenderSupervisionPlans(Long offenderBookId, String supervisionLevelType,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_OFFENDER_SUPERVISION_PLANS_BOOKING");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "offender_supervision_plans";
			String whereCondition = "offender_book_id = :p_offender_book_id AND supervision_level_type = :p_supervision_level_type";
			inputMap.put("p_offender_book_id", offenderBookId);
			inputMap.put("p_supervision_level_type", supervisionLevelType);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprOffenderSupervisionPlans " + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_book_id",offenderBookId,"p_supervision_level_type",supervisionLevelType));
	}
	
	@Override
	public Integer deleteprOffenderSupervisionRates(Long offenderBookId, String supervisionType,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_OFFENDER_SUPERVISION_RATES_BOOKING");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "offender_supervision_rates";
			String whereCondition = "offender_book_id = :p_offender_book_id AND supervision_type = :p_supervision_type";
			inputMap.put("p_offender_book_id", offenderBookId);
			inputMap.put("p_supervision_type", supervisionType);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprOffenderSupervisionRates " + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_book_id",offenderBookId,"p_supervision_type",supervisionType));
	}
	
	@Override
	public Integer deleteprOffenderSupervisionLevels(BigDecimal offenderBookId,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_OFFENDER_SUPERVISION_LEVELS_BOOKING");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "offender_supervision_levels";
			String whereCondition = "offender_book_id = :p_offender_book_id";
			inputMap.put("p_offender_book_id", offenderBookId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprOffenderSupervisionLevels " + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_book_id",offenderBookId));
	}
	
	@Override
	public Integer deleteprOffenderTeamAssignments(Long offenderBookId,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_OFFENDER_TEAM_ASSIGNMENTS_BOOKING");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "offender_team_assignments";
			String whereCondition = "offender_book_id = :p_offender_book_id";
			inputMap.put("p_offender_book_id", offenderBookId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprOffenderTeamAssignments " + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_book_id",offenderBookId));
	}
	
	@Override
	public Integer deleteprOffenderTeamAssignHty(Long offenderBookId,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_OFFENDER_TEAM_ASSIGN_HTY_BOOKING");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "offender_team_assign_hty";
			String whereCondition = "offender_book_id = :p_offender_book_id";
			inputMap.put("p_offender_book_id", offenderBookId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprOffenderTeamAssignHty " + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_book_id",offenderBookId));
	}
	
	@Override
	public Integer deleteprOffenderTemporaryAbsences(Long offenderBookId,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_OFFENDER_TEMPORARY_ABSENCES_BOOKING");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "offender_temporary_absences";
			String whereCondition = "offender_book_id = :p_offender_book_id";
			inputMap.put("p_offender_book_id", offenderBookId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprOffenderTemporaryAbsences " + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_book_id",offenderBookId));
	}
	
	@Override
	public Integer deleteprOffenderTmpRelSchedules(Long offenderBookId,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_OFFENDER_TMP_REL_SCHEDULES_BOOKING");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "offender_tmp_rel_schedules";
			String whereCondition = "offender_book_id = :p_offender_book_id";
			inputMap.put("p_offender_book_id", offenderBookId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprOffenderTmpRelSchedules " + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_book_id",offenderBookId));
	}
	
	@Override
	public List<VOffenderVisits> prOffenderVisits(Long offenderBookId) {
		String sql=getQuery("BOOKING_GET_OFFENDER_VISITS_BOOKING");
		return namedParameterJdbcTemplate.query(sql, createParams("p_offender_book_id",offenderBookId),new BeanPropertyRowMapper<VOffenderVisits>(VOffenderVisits.class));
	}
	
	@Override
	public Integer deleteprOffenderVisitVisitors(BigDecimal offenderVisitId,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_OFFENDER_VISIT_VISITORS_BOOKING");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "offender_visit_visitors";
			String whereCondition = "offender_visit_id = :p_offender_visit_id";
			inputMap.put("p_offender_visit_id", offenderVisitId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprOffenderVisitVisitors " + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_visit_id",offenderVisitId));
	}
	
	@Override
	public Integer deleteprOffenderVisits(Long offenderBookId,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_OFFENDER_VISITS_BOOKING");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "offender_visits";
			String whereCondition = "offender_book_id = :p_offender_book_id";
			inputMap.put("p_offender_book_id", offenderBookId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprOffenderVisits " + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_book_id",offenderBookId));
	}
	
	@Override
	public Integer deleteprOffenderVitalSigns1(Long offenderBookId,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_OFFENDER_VITAL_SIGNS1");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "offender_vital_signs";
			String whereCondition = "offender_book_id = :p_offender_book_id";
			inputMap.put("p_offender_book_id", offenderBookId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprOffenderVitalSigns1 " + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_book_id",offenderBookId));
	}
	
	@Override
	public List<Orders> prOrders(BigDecimal offenderBookId) {
		String sql=getQuery("BOOKING_GET_ORDERS1");
		return namedParameterJdbcTemplate.query(sql, createParams("p_offender_book_id",offenderBookId),new BeanPropertyRowMapper<Orders>(Orders.class, false));
	}
	
	@Override
	public Integer deleteprOrders(BigDecimal offenderBookId,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_ORDERS1");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "orders";
			String whereCondition = "offender_book_id = :p_offender_book_id";
			inputMap.put("p_offender_book_id", offenderBookId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprOrders " + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_book_id",offenderBookId));
	}
	
	@Override
	public List<ReleasePlans> prReleasePlans(BigDecimal offenderBookId) {
		String sql=getQuery("BOOKING_GET_RELEASE_PLANS_BOOKING");
		return namedParameterJdbcTemplate.query(sql, createParams("p_offender_book_id",offenderBookId),new BeanPropertyRowMapper<ReleasePlans>(ReleasePlans.class));
	}
	
	@Override
	public Integer deleteprRpOtherOccupants(BigDecimal releasePlanId,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_RP_OTHER_OCCUPANTS_BOOKING");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "rp_other_occupants";
			String whereCondition = "release_plan_id = :p_release_plan_id";
			inputMap.put("p_release_plan_id", releasePlanId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprRpOtherOccupants " + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_release_plan_id",releasePlanId));
	}
	
	@Override
	public Integer deleteprReleasePlans(BigDecimal offenderBookId,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_RELEASE_PLANS_BOOKING");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "release_plans";
			String whereCondition = "offender_book_id = :p_offender_book_id";
			inputMap.put("p_offender_book_id", offenderBookId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprReleasePlans " + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_book_id",offenderBookId));
	}
	
	@Override
	public List<Orders> prStaffWorks(BigDecimal offenderBookId) {
		String sql=getQuery("BOOKING_GET_STAFF_WORKS_BOOKING");
		return namedParameterJdbcTemplate.query(sql, createParams("p_offender_book_id",offenderBookId),new BeanPropertyRowMapper<Orders>(Orders.class));
	}
	
	@Override
	public Integer deleteprStaffWorkAssignments(BigDecimal staffWorkId,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_STAFF_WORK_ASSIGNMENTS_BOOKING");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "staff_work_assignments";
			String whereCondition = "staff_work_id = :p_staff_work_id";
			inputMap.put("p_staff_work_id", staffWorkId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprStaffWorkAssignments " + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_staff_work_id",staffWorkId));
	}
	
	@Override
	public Integer deleteprStaffWorks(BigDecimal offenderBookId,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_STAFF_WORKS_BOOKING");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "staff_works";
			String whereCondition = "offender_book_id = :p_offender_book_id";
			inputMap.put("p_offender_book_id", offenderBookId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprStaffWorks " + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_book_id",offenderBookId));
	}
	
	@Override
	public Integer deleteprSupervisionTransactions(BigDecimal offenderBookId,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_SUPERVISION_TRANSACTIONS_BOOKING");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "supervision_transactions";
			String whereCondition = "offender_book_id = :p_offender_book_id";
			inputMap.put("p_offender_book_id", offenderBookId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprSupervisionTransactions " + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_book_id",offenderBookId));
	}
	
	@Override
	public Integer deleteprTaskAssignmentHty(BigDecimal offenderBookId,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_TASK_ASSIGNMENT_HTY_BOOKING");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "task_assignment_hty";
			String whereCondition = "offender_book_id = :p_offender_book_id";
			inputMap.put("p_offender_book_id", offenderBookId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprTaskAssignmentHty " + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_book_id",offenderBookId));
	}
	
	@Override
	public Integer deleteprAssignments(Long offenderBookId,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_ASSIGNMENTS_BOOKING");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "assignments";
			String whereCondition = "offender_book_id = :p_offender_book_id";
			inputMap.put("p_offender_book_id", offenderBookId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprAssignments " + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_book_id",offenderBookId));
	}
	
	@Override
	public Integer deleteprOicHearingResults(BigDecimal agencyIncidentId, BigDecimal chargeSeq,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_OIC_HEARING_RESULTS_BOOKING");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "oic_hearing_results";
			String whereCondition = "agency_incident_id = :p_agency_incident_id AND charge_seq = :p_charge_seq";
			inputMap.put("p_agency_incident_id", agencyIncidentId);
			inputMap.put("p_charge_seq", chargeSeq);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprOicHearingResults " + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_agency_incident_id",agencyIncidentId,"p_charge_seq",chargeSeq));
	}
	
	@Override
	public Integer deleteprOffenderCourseAttendancs2(BigDecimal offPrgrefId,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_OFFENDER_COURSE_ATTENDANCES3_BOOKING");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "offender_course_attendances";
			String whereCondition = "off_prgref_id = :p_off_prgref_id";
			inputMap.put("p_off_prgref_id", offPrgrefId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprOffenderCourseAttendancs2 " + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_off_prgref_id",offPrgrefId));
	}
	
	@Override
	public Integer deleteprOffenderBookings(BigDecimal offenderId,String modifyUserId) {
		String sql=getQuery("BOOKING_DELETE_OFFENDER_BOOKINGS_BOOKING");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "offender_bookings";
			String whereCondition = "offender_id = :p_offender_id";
			inputMap.put("p_offender_id", offenderId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprOffenderBookings " + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_id",offenderId));
	}

	@Override
	public Integer purgeDeleteOffenderCustodyStatus(Long offenderBookId,String modifyUserId) {
		final String sql=getQuery("BOOKING_DELETE_OFFENDER_CUSTODY_STATUS");
		
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "offender_custody_status";
			String whereCondition = "offender_id = :p_offender_id";
			inputMap.put("p_offender_id", offenderBookId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteprOffenderBookings " + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_book_id",offenderBookId));
	}
}

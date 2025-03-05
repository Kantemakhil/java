package net.syscon.s4.pkgs.purge_offender_bookings.impl;

import java.math.BigDecimal;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.pkgs.oumpurge.impl.OumpurgePkgServiceImpl;
import net.syscon.s4.pkgs.purge_offender_bookings.PurgeOffenderBookingsRepository;
import net.syscon.s4.pkgs.purge_offender_bookings.PurgeOffenderBookingsService;

@Service
public class PurgeOffenderBookingsServiceImpl implements PurgeOffenderBookingsService {
	@Autowired
	private PurgeOffenderBookingsRepository purgeOffenderBookingsRepository;
	private static Logger logger = LogManager.getLogger(PurgeOffenderBookingsServiceImpl.class.getName());
	@Override
	public void prAgencyIncidentParties(Integer pOffenderBookId, String modifyUserId) {
		purgeOffenderBookingsRepository.prAgencyIncidentParties(pOffenderBookId).forEach(bean -> {
			this.prAgencyIncidentCharges(bean.getAgencyIncidentId(), bean.getPartySeq(), modifyUserId);
			this.prAgyIncInvestigations(bean.getAgencyIncidentId(), bean.getPartySeq(), modifyUserId);
			this.prOffenderGrievances1(bean.getAgencyIncidentId()!=null?bean.getAgencyIncidentId().longValue():null, bean.getPartySeq()!=null?bean.getPartySeq().longValue():null, modifyUserId);
			this.prOffenderIncidentDetails(bean.getAgencyIncidentId()!=null?new BigDecimal(bean.getAgencyIncidentId()):null,
					bean.getPartySeq()!=null?new BigDecimal(bean.getPartySeq()):null, modifyUserId);
			this.prOicHearings(bean.getOicIncidentId(), modifyUserId);
		});
		purgeOffenderBookingsRepository.deleteprAgencyIncidentParties(pOffenderBookId, modifyUserId);

	}

	@Override
	public void prAgyIncInvestigations(Integer pAgencyIncidentId, Integer partySeq, String modifyUserId) {
		purgeOffenderBookingsRepository.prAgyIncInvestigations(pAgencyIncidentId, partySeq)
		.forEach(bean -> this.prAgyIncInvStatements(bean.getAgyIncInvestigationId(), modifyUserId));
		purgeOffenderBookingsRepository.deleteprAgyIncInvestigations(pAgencyIncidentId, partySeq, modifyUserId);

	}

	@Override
	public void prOffenderBookings(Long pOffenderBookId,String modifyUserId) {
		purgeOffenderBookingsRepository.getOffenderBookId(pOffenderBookId).forEach(val->{
			prAgencyIncidentParties(val.getOffenderBookId()!=null?val.getOffenderBookId().intValue():null, modifyUserId);
			prArrests(val.getOffenderBookId(), modifyUserId);
			prAssignments(val.getOffenderBookId(), modifyUserId);
			prBedAssignmentHistories(val.getOffenderBookId(), modifyUserId);
			prCaseAssociatedPersons(val.getOffenderBookId(), modifyUserId);
			prCaseNotes(val.getOffenderBookId(), modifyUserId);
			prCasePlans(val.getOffenderBookId(), modifyUserId);
			prCourtEvents(val.getOffenderBookId()!=null?val.getOffenderBookId().intValue():null, modifyUserId);
			prDocumentTemplateQueues(val.getOffenderBookId()!=null?new BigDecimal(val.getOffenderBookId()):null, modifyUserId);
			prGlTransactions(val.getOffenderBookId()!=null?new BigDecimal(val.getOffenderBookId()):null);
			prHdcRequestReferrals(val.getOffenderBookId(), modifyUserId);
			prIncidentCaseParties(val.getOffenderBookId()!=null?new BigDecimal(val.getOffenderBookId()):null, modifyUserId);
			prIwpDocuments(val.getOffenderBookId()!=null?new BigDecimal(val.getOffenderBookId()):null, modifyUserId);
			prOffenderActivityEvents(val.getOffenderBookId()!=null?new BigDecimal(val.getOffenderBookId()):null, modifyUserId);
			prOffenderAlerts(val.getOffenderBookId(), modifyUserId);
			prOffenderAssessments(val.getOffenderBookId(), modifyUserId);
			prOffenderAssets(val.getOffenderBookId(), modifyUserId);
			prOffenderAssociatedParties(val.getOffenderBookId(), modifyUserId);
			prOffenderAuthorisdVisitors(val.getOffenderBookId()!=null?new BigDecimal(val.getOffenderBookId()):null, modifyUserId);
			prOffenderAthorisdVisitors1(val.getOffenderBookId()!=null?new BigDecimal(val.getOffenderBookId()):null, modifyUserId);
			prOffenderBailDetails(val.getOffenderBookId(), modifyUserId);
			prOffenderBookingAgyLocs(val.getOffenderBookId(), modifyUserId);
			prOffenderBookingDetails(val.getOffenderBookId(), modifyUserId);
			prOffenderBookingEvents(val.getOffenderBookId(), modifyUserId);
			prOffenderCases(val.getOffenderBookId());
			prOffenderCaseNotes(val.getOffenderBookId()!=null?val.getOffenderBookId().intValue():null, modifyUserId);
			prOffenderCharges(val.getOffenderBookId(), modifyUserId);
			prOffenderCipDetails(val.getOffenderBookId(), modifyUserId);
			prOffenderClassPrograms(val.getOffenderBookId(), modifyUserId);
			prOffenderClothes(val.getOffenderBookId(), modifyUserId);
			prOffenderClothingBundles(val.getOffenderBookId(), modifyUserId);
			prOffenderClothingIssue(val.getOffenderBookId(), modifyUserId);
			prOffenderClothingItems(val.getOffenderBookId());
			prOffenderCodefendants(val.getOffenderBookId(), modifyUserId);
			prOffenderCodefendants1(val.getOffenderBookId(), modifyUserId);
			prOffenderComments(val.getOffenderBookId(), modifyUserId);
			prOffendrCommnityConditions(val.getOffenderBookId(), modifyUserId);
			prOffenderCommSupHistories(val.getOffenderBookId(), modifyUserId);
			prOffenderCompactAgreements(val.getOffenderBookId(), modifyUserId);
			prOffenderContactPersons(val.getOffenderBookId()!=null?new BigDecimal(val.getOffenderBookId()):null, modifyUserId);
			prOffenderCourseAttendances(val.getOffenderBookId(), modifyUserId);
			prOffenderCurfews(val.getOffenderBookId()!=null?new BigDecimal(val.getOffenderBookId()):null,modifyUserId);
			prOffenderDrugAdmissions(val.getOffenderBookId()!=null?new BigDecimal(val.getOffenderBookId()):null, modifyUserId);
			prOffenderDrugSamples(val.getOffenderBookId()!=null?new BigDecimal(val.getOffenderBookId()):null, modifyUserId);
			prOffenderEducations(val.getOffenderBookId(), modifyUserId);
			prOffenderEmployments(val.getOffenderBookId(), modifyUserId);
			prOffenderEscapes(val.getOffenderBookId()!=null?val.getOffenderBookId().intValue():null, modifyUserId);
			prOffenderExpenses(val.getOffenderBookId()!=null?new BigDecimal(val.getOffenderBookId()):null, modifyUserId);
			prOffenderExternalMovements(val.getOffenderBookId(), modifyUserId);
			prOffenderFileLocations(val.getOffenderBookId(), modifyUserId);
			prOffenderGangAffiliations(val.getOffenderBookId(), modifyUserId);
			prOffenderGangEvidences(val.getOffenderBookId(), modifyUserId);
			prOffenderGangInvests(val.getOffenderBookId(), modifyUserId);
			prOffenderGrievances(val.getOffenderBookId(), modifyUserId);
			prOffenderHospitalVisits(val.getOffenderBookId(), modifyUserId);
			prOffenderHwd(val.getOffenderBookId(), modifyUserId);
			prOffenderIdentifyingMarks(val.getOffenderBookId(), modifyUserId);
			prOffenderIepLevels(val.getOffenderBookId(), modifyUserId);
			prOffenderImages(val.getOffenderBookId(), modifyUserId);
			prOffenderImprisonStatuses(val.getOffenderBookId(), modifyUserId);
			prOffenderIncome(val.getOffenderBookId(), modifyUserId);
			prOffenderIndSchedules(val.getOffenderBookId()!=null?val.getOffenderBookId().intValue():null, modifyUserId);
			prOffenderInternalMovements(val.getOffenderBookId(), modifyUserId);
			prOffenderInternalStatuses(val.getOffenderBookId()!=null?val.getOffenderBookId().intValue():null, modifyUserId);
			prOffenderInterventionTiers(val.getOffenderBookId(), modifyUserId);
			prOffenderLanguages(val.getOffenderBookId(), modifyUserId);
			prOffenderLiabilities(val.getOffenderBookId(), modifyUserId);
			prOffenderMailLogs(val.getOffenderBookId());
			prOffendrMilitryDiscCtions(val.getOffenderBookId(), modifyUserId);
			prOffenderMilitaryRecords(val.getOffenderBookId(), modifyUserId);
			prOffenderMilitaryWarZones(val.getOffenderBookId(), modifyUserId);
			prOffenderOasysPlans(val.getOffenderBookId(), modifyUserId);
			prOffenderOicSanctions(val.getOffenderBookId()!=null?val.getOffenderBookId().intValue():null, modifyUserId);
			prOffenderParoleHearings(val.getOffenderBookId(), modifyUserId);
			prOffenderParolePlans(val.getOffenderBookId(), modifyUserId);
			prOffenderPlanedActivities(val.getOffenderBookId(), modifyUserId);
			prOffenderPptyContainers(val.getOffenderBookId()!=null?val.getOffenderBookId().intValue():null, modifyUserId);
			prOffenderPptyItems(val.getOffenderBookId()!=null?val.getOffenderBookId().intValue():null, modifyUserId);
			prOffenderPptyItemEvents(val.getOffenderBookId()!=null?val.getOffenderBookId().intValue():null);
			prOffenderPrgObligations(val.getOffenderBookId(),modifyUserId);
			prOffenderProceedings(val.getOffenderBookId()!=null?new BigDecimal(val.getOffenderBookId()):null, modifyUserId);
			prOffenderProfiles(val.getOffenderBookId());
			prOffenderProgramProfiles(val.getOffenderBookId(),modifyUserId);
			prOffenderPropertyBundles(val.getOffenderBookId()!=null?new BigDecimal(val.getOffenderBookId()):null);
			prOffenderProposedMvmnts(val.getOffenderBookId());
			prOffenderPtr(val.getOffenderBookId());
			prOffenderReferrals(val.getOffenderBookId());
			prOffenderReleaseDetails(val.getOffenderBookId());
			prOffenderResidences(val.getOffenderBookId());
			proffenderrestrictions(val.getOffenderBookId()!=null?new BigDecimal(val.getOffenderBookId()):null);
			prOffenderRiskPredictors(val.getOffenderBookId());
			prOffenderSentCalculations(val.getOffenderBookId());
			prOffenderStgAffiliations(val.getOffenderBookId()!=null?new BigDecimal(val.getOffenderBookId()):null);
			prOffenderStgAssociations(val.getOffenderBookId()!=null?new BigDecimal(val.getOffenderBookId()):null);
			prOffenderSubstanceAbuses(val.getOffenderBookId()!=null?new BigDecimal(val.getOffenderBookId()):null);
			prOffenderSupervisingCourts(val.getOffenderBookId()!=null?new BigDecimal(val.getOffenderBookId()):null);
			prOffenderSupervisionLevels(val.getOffenderBookId()!=null?new BigDecimal(val.getOffenderBookId()):null);
			prOffenderTeamAssignments(val.getOffenderBookId()!=null?val.getOffenderBookId().intValue():null);
			prOffenderTeamAssignHty(val.getOffenderBookId());
			prOffenderTemporaryAbsences(val.getOffenderBookId());
			prOffenderTmpRelSchedules(val.getOffenderBookId());
			prOffenderVisits(val.getOffenderBookId());
			prOffenderVitalSigns(val.getOffenderBookId()!=null?new BigDecimal(val.getOffenderBookId()):null);
			prOrders(val.getOffenderBookId()!=null?new BigDecimal(val.getOffenderBookId()):null,modifyUserId);
			prReleasePlans(val.getOffenderBookId());
			prStaffWorks(val.getOffenderBookId());
			prSupervisionTransactions(val.getOffenderBookId());
			prTaskAssignmentHty(val.getOffenderBookId()!=null?new BigDecimal(val.getOffenderBookId()):null);
			prAddresses((val.getOffenderBookId()!=null?new BigDecimal(val.getOffenderBookId()):null),"OFF_EDU",modifyUserId);
			prPhones((val.getOffenderBookId()!=null?new BigDecimal(val.getOffenderBookId()):null),"OFF_EDU");
			prInternetAddresses((val.getOffenderBookId()!=null?new BigDecimal(val.getOffenderBookId()):null),"OFF_EDU");
			prAddresses((val.getOffenderBookId()!=null?new BigDecimal(val.getOffenderBookId()):null),"OFF_EMP",modifyUserId);
			prPhones((val.getOffenderBookId()!=null?new BigDecimal(val.getOffenderBookId()):null),"OFF_EMP");
			prInternetAddresses((val.getOffenderBookId()!=null?new BigDecimal(val.getOffenderBookId()):null),"OFF_EMP");
			prImages((val.getOffenderBookId()!=null?new BigDecimal(val.getOffenderBookId()):null),"PPTY");
			prImages((val.getOffenderBookId()!=null?new BigDecimal(val.getOffenderBookId()):null),"OFF_IDM");
			prImages((val.getOffenderBookId()!=null?new BigDecimal(val.getOffenderBookId()):null),"OFF_BKG");
			prOffenderCaseAssociations(val.getOffenderBookId());
			prOffenderCaseAssociations1(val.getOffenderBookId()!=null?new BigDecimal(val.getOffenderBookId()):null);
			prWorkFlows(val.getOffenderBookId()!=null?new BigDecimal(val.getOffenderBookId()):null);
			prOffenderProfileDetails(val.getOffenderBookId());
			prOffenderBillingProfiles(val.getOffenderBookId());
			proffenderOffIncentivesEarnPrivs(val.getOffenderBookId());
			proffendercustodystatus(val.getOffenderBookId(),modifyUserId);
			prQmProcessesIns(val+"");
			prQmProcessesInsHist(val+"");
		});;
		purgeOffenderBookingsRepository.deleteprOffenderBookings(pOffenderBookId);


	}// method

	@Override
	public void prAgencyIncidentCharges(Integer pAgencyIncidentId, Integer partySeq, String modifyUserId) {
		purgeOffenderBookingsRepository.getAgencyIncidentCharges(pAgencyIncidentId, partySeq)
		.forEach(bean -> prOicHearingResults(new BigDecimal(bean.getAgencyIncidentId()),
				new BigDecimal(bean.getChargeSeq()), modifyUserId));
		;
		purgeOffenderBookingsRepository.deleteprAgencyIncidentCharges(pAgencyIncidentId, partySeq, modifyUserId);

	}

	@Override
	public void prOicHearingResults1(BigDecimal pOicHearingId, String modifyUserId) {
		purgeOffenderBookingsRepository.prOicHearingResults1(pOicHearingId).forEach(bean -> {
			prOffenderOicAppelIncidnts(bean.getOicHearingId().intValue(), bean.getResultSeq().intValue(),modifyUserId);
			prOffenderOicSanctions1(bean.getOicHearingId().intValue(), bean.getResultSeq().intValue(), modifyUserId);
		});
		purgeOffenderBookingsRepository.deleteOicHearingResults(pOicHearingId, modifyUserId);

	}

	@Override
	public void prOffenderOicAppelIncidnts(Integer oicHearingId, Integer resultSeq, String modifyUserId) {
		purgeOffenderBookingsRepository.prOffenderOicAppelIncidnts(oicHearingId, resultSeq)
		.forEach(bean -> prOffenderOicAppealPenltis(bean.getOicApprealId(), bean.getOicHearingId(),
				bean.getResultSeq(), modifyUserId));
		purgeOffenderBookingsRepository.deleteOffenderOicAppealIncidents(oicHearingId, resultSeq, modifyUserId);
	}

	@Override
	public void prOffenderOicAppealPenltis(Integer oicApprealId, Integer oicHearingId, Integer resultSeq, String modifyUserId) {
		purgeOffenderBookingsRepository.prOffenderOicAppealPenltis(oicApprealId, oicHearingId, resultSeq, modifyUserId);

	}

	@Override
	public void prOffenderOicSanctions1(Integer oicHearingId, Integer resultSeq, String modifyUserId) {
		purgeOffenderBookingsRepository.prOffenderOicSanctions1(oicHearingId, resultSeq).forEach(bean -> {
			prOffenderOicSanctions2(bean.getOffenderBookId(), bean.getSanctionSeq());
			prOffenderOicSancReviews(new BigDecimal(bean.getOffenderBookId()), new BigDecimal(bean.getSanctionSeq()), modifyUserId);
		});

	}

	@Override
	public void prOffenderOicSanctions2(Integer consecutiveOffenderBookId, Integer consecutiveSanctionSeq) {

	}

	@Override
	public void prOffenderOicSancReviews(BigDecimal pOffenderBookId, BigDecimal pSanctionSeq, String modifyUserId) {
		purgeOffenderBookingsRepository.prOffenderOicSancReviews(pOffenderBookId, pSanctionSeq, modifyUserId);

	}

	@Override
	public void prOicHearingResults(BigDecimal agencyIncidentId, BigDecimal chargeSeq, String modifyUserId) {
		purgeOffenderBookingsRepository.prOicHearingResults(agencyIncidentId, chargeSeq).forEach(bean -> {
			prOffenderOicAppelIncidnts(bean.getOicHearingId().intValue(), bean.getResultSeq().intValue(), modifyUserId);
			prOffenderOicSanctions1(bean.getOicHearingId().intValue(), bean.getResultSeq().intValue(), modifyUserId);
			;
		});
		purgeOffenderBookingsRepository.deleteprOicHearingResults(agencyIncidentId, chargeSeq, modifyUserId);

	}

	@Override
	public void prAgyIncInvStatements(Integer agyIncInvestigationId, String modifyUserId) {
		purgeOffenderBookingsRepository.deleteprAgyIncInvStatements(agyIncInvestigationId, modifyUserId);

	}

	@Override
	public void prOffenderGrievances1(Long agencyIncidentId, Long partySeq, String modifyUserId) {
		purgeOffenderBookingsRepository.prOffenderGrievances1(agencyIncidentId, partySeq).forEach(bean -> {
			prOffenderGrievanceTxns(bean.getGrievanceId(), modifyUserId);
			prOffenderGrievStaffs(bean.getGrievanceId(), modifyUserId);
		});
		purgeOffenderBookingsRepository.deleteprOffenderGrievances1(agencyIncidentId, partySeq, modifyUserId);
	}

	@Override
	public void prOffenderGrievanceTxns(Long grievanceId, String modifyUserId) {
		purgeOffenderBookingsRepository.deleteprOffenderGrievanceTxns(grievanceId, modifyUserId);

	}

	@Override
	public void prOffenderGrievStaffs(Long grievanceId, String modifyUserId) {
		purgeOffenderBookingsRepository.deleteprOffenderGrievStaffs(grievanceId, modifyUserId);

	}

	@Override
	public void prOffenderIncidentDetails(BigDecimal pAgencyIncidentId, BigDecimal pPartySeq, String modifyUserId) {
		purgeOffenderBookingsRepository.deleteprOffenderIncidentDetails(pAgencyIncidentId, pPartySeq, modifyUserId);
	}

	@Override
	public void prOicHearings(Integer oicIncidentId, String modifyUserId) {
		purgeOffenderBookingsRepository.prOicHearings(oicIncidentId).forEach(bean -> {
			this.prOicHearingComments(new BigDecimal(bean.getOicHearingId()), modifyUserId);
			this.prOicHearingNotices(bean.getOicHearingId(), modifyUserId);
			this.prOicHearingResults1(new BigDecimal(bean.getOicHearingId()), modifyUserId);
			this.prOicHearingReviews(new BigDecimal(bean.getOicHearingId()), modifyUserId);

		});
		;

	}

	@Override
	public void prOicHearingComments(BigDecimal pOicHearingId, String modifyUserId) {
		purgeOffenderBookingsRepository.deleteprOicHearingComments(pOicHearingId, modifyUserId);

	}

	@Override
	public void prOicHearingNotices(Integer oicHearingId, String modifyUserId) {
		purgeOffenderBookingsRepository.deleteprOicHearingNotices(oicHearingId, modifyUserId);

	}

	@Override
	public void prOicHearingReviews(BigDecimal oicHearingId, String modifyUserId) {
		purgeOffenderBookingsRepository.deleteprOicHearingReviews(oicHearingId, modifyUserId);

	}

	@Override
	public void prArrests(Long offenderBookId, String modifyUserId) {
		purgeOffenderBookingsRepository.deleteprArrests(offenderBookId, modifyUserId);
	}

	@Override
	public void prAssignments(Long offenderBookId, String modifyUserId) {
		purgeOffenderBookingsRepository.deleteprAssignments(offenderBookId, modifyUserId);
	}

	@Override
	public void prBedAssignmentHistories(Long offenderBookId, String modifyUserId) {
		purgeOffenderBookingsRepository.deleteprBedAssignmentHistories(offenderBookId, modifyUserId);
	}

	@Override
	public void prCaseAssociatedPersons(Long offenderBookId, String modifyUserId) {
		purgeOffenderBookingsRepository.deleteprCaseAssociatedPersons(offenderBookId, modifyUserId);
	}
	@Override
	public void prCaseNotes(Long offenderBookId, String modifyUserId) {
		purgeOffenderBookingsRepository.deleteprCaseNotes(offenderBookId, modifyUserId);
	}

	@Override
	public void prCasePlans(Long offenderBookId, String modifyUserId) {
		purgeOffenderBookingsRepository.prCasePlans(offenderBookId).forEach(bean->{
			this.prAssessmentSummaries(bean.getOffenderBookId(), bean.getCasePlanId(), modifyUserId);
			this.prOffenderCaseConditions(bean.getOffenderBookId(), bean.getCasePlanId(), modifyUserId);
			this.prOffenderCriminogenicNeeds(new BigDecimal(bean.getOffenderBookId()), new BigDecimal(bean.getCasePlanId()), modifyUserId);
			this.prPlanDetails(bean.getOffenderBookId(), bean.getCasePlanId(), modifyUserId);
		});
		purgeOffenderBookingsRepository.deleteprCasePlans(offenderBookId, modifyUserId);
	}

	@Override
	public void prAssessmentSummaries(Long offenderBookId, Long casePlanId, String modifyUserId) {
		purgeOffenderBookingsRepository.deleteprAssessmentSummaries(offenderBookId, casePlanId, modifyUserId);
	}

	@Override
	public void prOffenderCaseConditions(Long offenderBookId, Long casePlanId, String modifyUserId) {
		purgeOffenderBookingsRepository.prOffenderCaseConditions(offenderBookId, casePlanId).forEach(bean->this.prOffenderActionPlans(new BigDecimal(bean.getOffCaseCondId()), modifyUserId));
		purgeOffenderBookingsRepository.deleteprOffenderCaseConditions(offenderBookId, casePlanId, modifyUserId);   	
	}

	@Override
	public void prOffenderActionPlans(BigDecimal offCaseCondId, String modifyUserId) {
		purgeOffenderBookingsRepository.deleteprOffenderActionPlans(offCaseCondId, modifyUserId);

	}

	@Override
	public void prOffenderCriminogenicNeeds(BigDecimal offenderBookId, BigDecimal casePlanId, String modifyUserId) {
		purgeOffenderBookingsRepository.prOffenderCriminogenicNeeds(offenderBookId, casePlanId).forEach(bean->this.prOffenderActionPlans1(new BigDecimal(bean.getOffCrimNeedId()), modifyUserId));;
		purgeOffenderBookingsRepository.deleteprOffenderCriminogenicNeeds(offenderBookId, casePlanId, modifyUserId);
	}

	@Override
	public void prOffenderActionPlans1(BigDecimal offCrimNeedId, String modifyUserId) {
		purgeOffenderBookingsRepository.deleteprOffenderActionPlans(offCrimNeedId, modifyUserId);
	}

	@Override
	public void prPlanDetails(Long offenderBookId, Long casePlanId, String modifyUserId) {
		purgeOffenderBookingsRepository.prPlanDetails(offenderBookId, casePlanId).forEach(bean->this.prCaseworkSteps(bean.getOffenderBookId(), bean.getCasePlanId(), bean.getDetailSeq(), modifyUserId));;
		purgeOffenderBookingsRepository.deleteprPlanDetails(offenderBookId, casePlanId, modifyUserId);
	}

	@Override
	public void prCaseworkSteps(Long offenderBookId, Long casePlanId, Long detailSeq, String modifyUserId) {
		purgeOffenderBookingsRepository.deleteprCaseworkSteps(offenderBookId, casePlanId, detailSeq, modifyUserId);
	}


	@Override
	public void prCourtEvents(Integer offenderBookId, String modifyUserId) {
		purgeOffenderBookingsRepository.prCourtEvents(offenderBookId).forEach(eventId->{
			prCourtEventCharges(new BigDecimal(eventId.getEventId()), modifyUserId);
			prCourtEventSentences(new BigDecimal(eventId.getEventId()), modifyUserId);
			prLinkCaseTxns(new BigDecimal(eventId.getEventId()), modifyUserId);
		});
	}

	@Override
	public void prCourtEventCharges(BigDecimal eventId, String modifyUserId) {
		purgeOffenderBookingsRepository.deleteprCourtEventCharges(eventId, modifyUserId);
	}
	@Override
	public void prCourtEventSentences(BigDecimal eventId, String modifyUserId) {
		purgeOffenderBookingsRepository.deleteprCourtEventSentences(eventId, modifyUserId);
	}

	@Override
	public void prLinkCaseTxns(BigDecimal eventId, String modifyUserId) {
		purgeOffenderBookingsRepository.deleteprLinkCaseTxns(eventId, modifyUserId);    	
	}
	@Override
	public void prDocumentTemplateQueues(BigDecimal offenderBookId, String modifyUserId) {
		purgeOffenderBookingsRepository.deleteprDocumentTemplateQueues(offenderBookId, modifyUserId);
	}

	@Override
	public void prGlTransactions(BigDecimal offenderBookId) {
		purgeOffenderBookingsRepository.updateprGlTransactions(offenderBookId);
	}

	@Override
	public void prHdcRequestReferrals(Long offenderBookId, String modifyUserId) {
		purgeOffenderBookingsRepository.prHdcRequestReferrals(offenderBookId).forEach(referalId->{
			this.prHdcBoardDecisions(referalId.getHdcRequestReferralId()!=null?new BigDecimal(referalId.getHdcRequestReferralId()):null, modifyUserId);
			this.prHdcGovernorDecisions(referalId.getHdcRequestReferralId()!=null?new BigDecimal(referalId.getHdcRequestReferralId()):null, modifyUserId);
			this.prHdcProbStaffComments(referalId.getHdcRequestReferralId()!=null?new BigDecimal(referalId.getHdcRequestReferralId()):null, modifyUserId);
			this.prHdcProbStaffResponses(referalId.getHdcRequestReferralId()!=null?new BigDecimal(referalId.getHdcRequestReferralId()):null, modifyUserId);
			this.prHdcRequestReferrals1(referalId.getHdcRequestReferralId(), modifyUserId);
		});
		purgeOffenderBookingsRepository.deleteprHdcRequestReferrals(offenderBookId, modifyUserId);
	}

	@Override
	public void prHdcBoardDecisions(BigDecimal pHdcRequestReferralId, String modifyUserId) {
		purgeOffenderBookingsRepository.deleteprHdcBoardDecisions(pHdcRequestReferralId, modifyUserId);
	}

	@Override
	public void prHdcGovernorDecisions(BigDecimal pHdcRequestReferralId, String modifyUserId) {
		purgeOffenderBookingsRepository.deleteprHdcGovernorDecisions(pHdcRequestReferralId, modifyUserId);
	}

	@Override
	public void prHdcProbStaffComments(BigDecimal pHdcRequestReferralId, String modifyUserId) {
		purgeOffenderBookingsRepository.deleteprHdcProbStaffComments(pHdcRequestReferralId, modifyUserId);
	}

	@Override
	public void prHdcProbStaffResponses(BigDecimal pHdcRequestReferralId, String modifyUserId) {
		purgeOffenderBookingsRepository.deleteprHdcProbStaffResponses(pHdcRequestReferralId, modifyUserId);		
	}

	@Override
	public void prHdcRequestReferrals1(Long parentHdcRequestReferralId, String modifyUserId) {
		purgeOffenderBookingsRepository.prHdcRequestReferrals(parentHdcRequestReferralId).forEach(referalId->{
			this.prHdcBoardDecisions(referalId.getHdcRequestReferralId()!=null?new BigDecimal(referalId.getHdcRequestReferralId()):null, modifyUserId);
			this.prHdcGovernorDecisions(referalId.getHdcRequestReferralId()!=null?new BigDecimal(referalId.getHdcRequestReferralId()):null, modifyUserId);
			this.prHdcProbStaffComments(referalId.getHdcRequestReferralId()!=null?new BigDecimal(referalId.getHdcRequestReferralId()):null, modifyUserId);
			this.prHdcProbStaffResponses(referalId.getHdcRequestReferralId()!=null?new BigDecimal(referalId.getHdcRequestReferralId()):null, modifyUserId);
			this.prHdcRequestReferrals1(referalId.getHdcRequestReferralId(), modifyUserId);
		});
		purgeOffenderBookingsRepository.deleteprHdcRequestReferrals1(parentHdcRequestReferralId, modifyUserId);
	}

	@Override
	public void prIncidentCaseParties(BigDecimal offenderBookId, String modifyUserId) {
		purgeOffenderBookingsRepository.deleteprIncidentCaseParties(offenderBookId, modifyUserId);		
	}

	@Override
	public void prIwpDocuments(BigDecimal offenderBookId, String modifyUserId) {
		purgeOffenderBookingsRepository.deleteprIwpDocuments(offenderBookId, modifyUserId);		
	}

	@Override
	public void prOffenderActivityEvents(BigDecimal offenderBookId, String modifyUserId) {
		purgeOffenderBookingsRepository.deleteprOffenderActivityEvents(offenderBookId, modifyUserId);
	}

	@Override
	public void prOffenderAlerts(Long offenderBookId, String modifyUserId) {
		purgeOffenderBookingsRepository.deleteprOffenderAlerts(offenderBookId, modifyUserId);
	}

	@Override
	public void prOffenderAssessments(Long offenderBookId, String modifyUserId) {
		purgeOffenderBookingsRepository.deleteprOffenderAssessments(offenderBookId, modifyUserId);
	}

	@Override
	public void prOffenderAssets(Long offenderBookId, String modifyUserId) {
		purgeOffenderBookingsRepository.deleteprOffenderAssets(offenderBookId, modifyUserId);
	}

	@Override
	public void prOffenderAssociatedParties(Long offenderBookId, String modifyUserId) {
		purgeOffenderBookingsRepository.prOffenderAssociatedParties(offenderBookId).forEach(value->{
			prOffenderAssocPartyNotes(value.getAssociatedPartyId(), modifyUserId);
			prOffenderAssocPrtyContcts(value.getAssociatedPartyId(), modifyUserId);
			prOffenderAssocPNotifs(value.getAssociatedPartyId(), modifyUserId);
		});		
	}

	@Override
	public void prOffenderAssocPartyNotes(Long associatedPartyId, String modifyUserId) {
		purgeOffenderBookingsRepository.deleteprOffenderAssocPartyNotes(associatedPartyId, modifyUserId);
	}

	@Override
	public void prOffenderAssocPrtyContcts(Long associatedPartyId, String modifyUserId) {
		purgeOffenderBookingsRepository.prOffenderAssocPrtyContcts(associatedPartyId).forEach(bean->this.prOffenderAssocPCntNotifs(bean.getAssociatedPartyId(), bean.getPartySeq(), modifyUserId));;		
		purgeOffenderBookingsRepository.deleteprOffenderAssocPrtyContcts(associatedPartyId, modifyUserId);
	}

	@Override
	public void prOffenderAssocPCntNotifs(Long associatedPartyId, Long partySeq, String modifyUserId) {
		purgeOffenderBookingsRepository.deleteprOffenderAssocPCntNotifs(associatedPartyId, partySeq, modifyUserId);
	}

	@Override
	public void prOffenderAssocPNotifs(Long associatedPartyId, String modifyUserId) {
		purgeOffenderBookingsRepository.deleteprOffenderAssocPNotifs(associatedPartyId, modifyUserId);
	}

	@Override
	public void prOffenderAuthorisdVisitors(BigDecimal offenderBookId, String modifyUserId) {
		purgeOffenderBookingsRepository.deleteprOffenderAuthorisdVisitors(offenderBookId, modifyUserId);
	}

	@Override
	public void prOffenderAthorisdVisitors1(BigDecimal offenderBookId, String modifyUserId) {
		purgeOffenderBookingsRepository.deleteprOffenderAthorisdVisitors1(offenderBookId, modifyUserId);
	}

	@Override
	public void prOffenderBailDetails(Long offenderBookId, String modifyUserId) {
		purgeOffenderBookingsRepository.deleteprOffenderBailDetails(offenderBookId, modifyUserId);
	}

	@Override
	public void prOffenderBookingAgyLocs(Long offenderBookId, String modifyUserId) {
		purgeOffenderBookingsRepository.deleteprOffenderBookingAgyLocs(offenderBookId, modifyUserId);		
	}

	@Override
	public void prOffenderBookingDetails(Long offenderBookId, String modifyUserId) {
		purgeOffenderBookingsRepository.deleteprOffenderBookingDetails(offenderBookId, modifyUserId);
	}

	@Override
	public void prOffenderBookingEvents(Long offenderBookId, String modifyUserId) {
		purgeOffenderBookingsRepository.deleteprOffenderBookingEvents(offenderBookId, modifyUserId);
	}

	@Override
	public void prOffenderCases(Long offenderBookId) {
		purgeOffenderBookingsRepository.prOffenderCases(offenderBookId);
	}

	@Override
	public void prCourtEvents1(BigDecimal eventID, String modifyUserId) {
		purgeOffenderBookingsRepository.prCourtEvents1(eventID).forEach(value->{
			prCourtEventCharges(new BigDecimal(value.getEventId()), modifyUserId);
			prCourtEventSentences(new BigDecimal(value.getEventId()), modifyUserId);
			prLinkCaseTxns(new BigDecimal(value.getEventId()), modifyUserId);
		});
		//missed
	}

	@Override
	public void prLinkCaseTxns1(BigDecimal eventId) {
		purgeOffenderBookingsRepository.deleteprLinkCaseTxns1(eventId);
	}

	@Override
	public void prLinkCaseTxns2(BigDecimal eventId, String modifyUserId) {
		purgeOffenderBookingsRepository.deleteprLinkCaseTxns2(eventId, modifyUserId);
	}

	@Override
	public void prOffenderAssociatedPartis1(BigDecimal eventId) {
		purgeOffenderBookingsRepository.prOffenderAssociatedPartis1(eventId);
	}

	@Override
	public void prOffenderCaseNotes(Integer offenderBookId, String modifyUserId) {
		purgeOffenderBookingsRepository.prOffenderCaseNotes(offenderBookId).forEach(caseNoteId->{
			prOffenderCaseNoteSents1(caseNoteId.getCaseNoteId(), modifyUserId);
			prOffCaseNoteRecipients(caseNoteId.getCaseNoteId(), modifyUserId);
		});
		//missing
	}

	@Override
	public void prOffenderCaseNoteSents1(Integer caseNoteId, String modifyUserId) {
		purgeOffenderBookingsRepository.deleteprOffenderCaseNoteSents1(caseNoteId, modifyUserId);
	}

	@Override
	public void prOffCaseNoteRecipients(Integer caseNoteId, String modifyUserId) {
		purgeOffenderBookingsRepository.deleteprOffCaseNoteRecipients(caseNoteId, modifyUserId);
	}

	@Override
	public void prOffenderCharges(Long offenderBookId, String modifyUserId) {
		purgeOffenderBookingsRepository.prOffenderCharges(offenderBookId).forEach(pOffenderChargeId->{
			prCourtEventCharges1(pOffenderChargeId.getOffenderChargeId()!=null?new BigDecimal(pOffenderChargeId.getOffenderChargeId()):null, modifyUserId);
			prLinkCaseTxns3(pOffenderChargeId.getOffenderChargeId()!=null?new BigDecimal(pOffenderChargeId.getOffenderChargeId()):null, modifyUserId);
			prOffenderSentenceCharges(pOffenderChargeId.getOffenderChargeId()!=null?new BigDecimal(pOffenderChargeId.getOffenderChargeId()):null, modifyUserId);

		});
		purgeOffenderBookingsRepository.deleteprOffenderCharges(offenderBookId, modifyUserId);
	}

	@Override
	public void prCourtEventCharges1(BigDecimal pOffenderChargeId, String modifyUserId) {
		purgeOffenderBookingsRepository.deleteprCourtEventCharges1(pOffenderChargeId, modifyUserId);
	}

	@Override
	public void prLinkCaseTxns3(BigDecimal pOffenderChargeId, String modifyUserId) {
		purgeOffenderBookingsRepository.deleteprLinkCaseTxns3(pOffenderChargeId, modifyUserId);
	}

	@Override
	public void prOffenderSentenceCharges(BigDecimal pOffenderChargeId, String modifyUserId) {
		purgeOffenderBookingsRepository.deleteprOffenderSentenceCharges(pOffenderChargeId, modifyUserId);
	}

	@Override
	public void prOffenderCipDetails(Long offenderBookId, String modifyUserId) {
		purgeOffenderBookingsRepository.deleteprOffenderCipDetails(offenderBookId, modifyUserId);
	}

	@Override
	public void prOffenderClassPrograms(Long offenderBookId, String modifyUserId) {
		purgeOffenderBookingsRepository.deleteprOffenderClassPrograms(offenderBookId, modifyUserId);
	}

	@Override
	public void prOffenderClothes(Long offenderBookId, String modifyUserId) {
		purgeOffenderBookingsRepository.prOffenderClothes(offenderBookId, modifyUserId);
	}

	@Override
	public void prOffenderClothingBundles(Long offenderBookId, String modifyUserId) {
		purgeOffenderBookingsRepository.deleteprOffenderClothingBundles(offenderBookId, modifyUserId);
	}

	@Override
	public void prOffenderClothingIssue(Long offenderBookId, String modifyUserId) {
		purgeOffenderBookingsRepository.prOffenderClothingIssue(offenderBookId).forEach(val->prOffenderClothingItems1(val.getOffenderClothingIssueId(), modifyUserId));;
		purgeOffenderBookingsRepository.deleteprOffenderClothingIssue(offenderBookId, modifyUserId);
	}

	@Override
	public void prOffenderClothingItems1(Long offenderClothingIssueId, String modifyUserId) {
		purgeOffenderBookingsRepository.deleteprOffenderClothingItems1(offenderClothingIssueId, modifyUserId);
	}

	@Override
	public void prOffenderClothingItems(Long offenderBookId) {
		purgeOffenderBookingsRepository.prOffenderClothingIssue(offenderBookId);
	}

	@Override
	public void prOffenderCodefendants(Long offenderBookId, String modifyUserId) {
		purgeOffenderBookingsRepository.deleteprOffenderCodefendants(offenderBookId, modifyUserId);
	}

	@Override
	public void prOffenderCodefendants1(Long offenderBookId, String modifyUserId) {
		purgeOffenderBookingsRepository.deleteprOffenderCodefendants1(offenderBookId, modifyUserId);
	}

	@Override
	public void prOffenderComments(Long offenderBookId, String modifyUserId) {
		purgeOffenderBookingsRepository.deleteprOffenderComments(offenderBookId, modifyUserId);
	}

	@Override
	public void prOffendrCommnityConditions(Long offenderBookId, String modifyUserId) {
		purgeOffenderBookingsRepository.deleteprOffendrCommnityConditions(offenderBookId, modifyUserId);
	}

	@Override
	public void prOffenderCommSupHistories(Long offenderBookId, String modifyUserId) {
		purgeOffenderBookingsRepository.deleteprOffenderCommSupHistories(offenderBookId, modifyUserId);
	}

	@Override
	public void prOffenderCompactAgreements(Long offenderBookId, String modifyUserId) {
		purgeOffenderBookingsRepository.deleteprOffenderCompactAgreements(offenderBookId, modifyUserId);
	}
	@Override
	public void prOffenderContactPersons(BigDecimal offenderBookId, String modifyUserId) {
		purgeOffenderBookingsRepository.prOffenderContactPersons(offenderBookId).forEach(val->prOffenderPersonRestricts((int)val.getOffenderContactPersonId(), modifyUserId));;
		purgeOffenderBookingsRepository.deleteprOffenderContactPersons(offenderBookId, modifyUserId);
	}

	@Override
	public void prOffenderPersonRestricts(Integer offenderContactPersonId, String modifyUserId) {
		purgeOffenderBookingsRepository.deleteprOffenderPersonRestricts(offenderContactPersonId, modifyUserId);
	}

	@Override
	public void prOffenderCourseAttendances(Long offenderBookId, String modifyUserId) {
		purgeOffenderBookingsRepository.prOffenderCourseAttendances(offenderBookId).forEach(bean->prOffenderCourseSkills(bean.getEventId(), modifyUserId));;
		purgeOffenderBookingsRepository.deleteprOffenderCourseAttendances(offenderBookId, modifyUserId);
	}

	@Override
	public void prOffenderCourseSkills(Long eventId, String modifyUserId) {
		purgeOffenderBookingsRepository.deleteprOffenderCourseSkills(eventId, modifyUserId);
	}

	@Override
	public void prOffenderCurfews(BigDecimal offenderBookId,String modifyUserId) {
		purgeOffenderBookingsRepository.prOffenderCurfews(offenderBookId).forEach(val->{
			prCurfewAddresses(new BigDecimal(val.getOffenderCurfewId()),modifyUserId);
			prHdcPrisonStaffComments(new BigDecimal(val.getOffenderCurfewId()), modifyUserId);
			prHdcRequestReferrals2(new BigDecimal(val.getOffenderCurfewId()), modifyUserId);
		});;
		//missing
	}

	@Override
	public void prCurfewAddresses(BigDecimal pOffenderCurfewId,String modifyUserId) {
		purgeOffenderBookingsRepository.prCurfewAddresses(pOffenderCurfewId).forEach(val->prCurfewAddressOccupants(new BigDecimal(val.getOffenderCurfewId()),modifyUserId));
	}

	@Override
	public void prCurfewAddressOccupants(BigDecimal pOffenderCurfewId,String modifyUserId) {
		purgeOffenderBookingsRepository.deleteprCurfewAddressOccupants(pOffenderCurfewId,modifyUserId);
	}

	@Override
	public void prHdcPrisonStaffComments(BigDecimal pOffenderCurfewId, String modifyUserId) {
		purgeOffenderBookingsRepository.deleteprHdcPrisonStaffComments(pOffenderCurfewId, modifyUserId);
	}

	@Override
	public void prHdcRequestReferrals2(BigDecimal pOffenderCurfewId, String modifyUserId) {
		purgeOffenderBookingsRepository.prHdcRequestReferrals2(pOffenderCurfewId).forEach(val->{
			prHdcBoardDecisions(val.getHdcRequestReferralId()!=null?new BigDecimal(val.getHdcRequestReferralId()):null, modifyUserId);
			prHdcGovernorDecisions(val.getHdcRequestReferralId()!=null?new BigDecimal(val.getHdcRequestReferralId()):null, modifyUserId);
			prHdcProbStaffComments(val.getHdcRequestReferralId()!=null?new BigDecimal(val.getHdcRequestReferralId()):null, modifyUserId);
			prHdcProbStaffResponses(val.getHdcRequestReferralId()!=null?new BigDecimal(val.getHdcRequestReferralId()):null, modifyUserId);
			prHdcRequestReferrals1(val.getHdcRequestReferralId(), modifyUserId);
		});
		purgeOffenderBookingsRepository.deleteprHdcRequestReferrals2(pOffenderCurfewId, modifyUserId);
	}

	@Override
	public void prOffenderDrugAdmissions(BigDecimal offenderBookId, String modifyUserId) {
		purgeOffenderBookingsRepository.prOffenderDrugAdmissions(offenderBookId).forEach(val->prOffenderDrugAdmDrugs(val.getOffenderDrugAdmissionId(), modifyUserId));;
		purgeOffenderBookingsRepository.deleteprOffenderDrugAdmissions(offenderBookId, modifyUserId);
	}

	@Override
	public void prOffenderDrugAdmDrugs(Long offenderDrugAdmissionId, String modifyUserId) {
		purgeOffenderBookingsRepository.deleteprOffenderDrugAdmDrugs(offenderDrugAdmissionId, modifyUserId);
	}

	@Override
	public void prOffenderDrugSamples(BigDecimal offenderBookId, String modifyUserId) {
		purgeOffenderBookingsRepository.prOffenderDrugSamples(offenderBookId).forEach(bean->prOffenderDrugResults(bean.getOffenderDrugSampleId(), modifyUserId));;
		purgeOffenderBookingsRepository.deleteprOffenderDrugSamples(offenderBookId, modifyUserId);
	}

	@Override
	public void prOffenderDrugResults(Long offenderDrugSampleId, String modifyUserId) {
		purgeOffenderBookingsRepository.deleteprOffenderDrugResults(offenderDrugSampleId, modifyUserId);
	}

	@Override
	public void prOffenderEducations(Long offenderBookId, String modifyUserId) {
		purgeOffenderBookingsRepository.deleteprOffenderEducations(offenderBookId, modifyUserId);
	}

	@Override
	public void prOffenderEmployments(Long offenderBookId, String modifyUserId) {
		purgeOffenderBookingsRepository.deleteprOffenderEmployments(offenderBookId, modifyUserId);
	}

	@Override
	public void prOffenderEscapes(Integer offenderBookId, String modifyUserId) {
		purgeOffenderBookingsRepository.deleteprOffenderEscapes(offenderBookId, modifyUserId);
	}

	@Override
	public void prOffenderExpenses(BigDecimal offenderBookId, String modifyUserId) {
		purgeOffenderBookingsRepository.deleteprOffenderExpenses(offenderBookId, modifyUserId);
	}

	@Override
	public void prOffenderExternalMovements(Long offenderBookId, String modifyUserId) {
		purgeOffenderBookingsRepository.deleteprOffenderExternalMovements(offenderBookId, modifyUserId);
	}

	@Override
	public void prOffenderFileLocations(Long offenderBookId, String modifyUserId) {
		purgeOffenderBookingsRepository.deleteprOffenderFileLocations(offenderBookId, modifyUserId);
	}

	@Override
	public void prOffenderGangAffiliations(Long offenderBookId, String modifyUserId) {
		purgeOffenderBookingsRepository.deleteprOffenderGangAffiliations(offenderBookId, modifyUserId);
	}

	@Override
	public void prOffenderGangEvidences(Long offenderBookId, String modifyUserId) {
		purgeOffenderBookingsRepository.deleteprOffenderGangEvidences(offenderBookId, modifyUserId);
	}

	@Override
	public void prOffenderGangInvests(Long offenderBookId, String modifyUserId) {
		purgeOffenderBookingsRepository.deleteprOffenderGangInvests(offenderBookId, modifyUserId);
	}

	@Override
	public void prOffenderGrievances(Long offenderBookId, String modifyUserId) {
		purgeOffenderBookingsRepository.prOffenderGrievances(offenderBookId).forEach(val->{
			prOffenderGrievanceTxns(val.getGrievanceId(), modifyUserId);
			prOffenderGrievStaffs(val.getGrievanceId(), modifyUserId);
		});
		purgeOffenderBookingsRepository.deleteprOffenderGrievances(offenderBookId, modifyUserId);
	}

	@Override
	public void prOffenderHospitalVisits(Long offenderBookId, String modifyUserId) {
		purgeOffenderBookingsRepository.deleteprOffenderHospitalVisits(offenderBookId, modifyUserId);
	}

	@Override
	public void prOffenderHwd(Long offenderBookId, String modifyUserId) {
		purgeOffenderBookingsRepository.prOffenderHwd(offenderBookId).forEach(val->{
			prOffenderHwdCharges(val.getHwdId(), modifyUserId);
			prOffenderHwdHty(val.getHwdId(), modifyUserId);
		});
		purgeOffenderBookingsRepository.deleteprOffenderHwd(offenderBookId, modifyUserId);
	}

	@Override
	public void prOffenderHwdCharges(Long hwdId, String modifyUserId) {
		purgeOffenderBookingsRepository.deleteprOffenderHwdCharges(hwdId, modifyUserId);
	}

	@Override
	public void prOffenderHwdHty(Long hwdId, String modifyUserId) {
		purgeOffenderBookingsRepository.deleteprOffenderHwdHty(hwdId, modifyUserId);
	}

	@Override
	public void prOffenderIdentifyingMarks(Long offenderBookId, String modifyUserId) {
		purgeOffenderBookingsRepository.deleteprOffenderIdentifyingMarks(offenderBookId, modifyUserId);		
	}

	@Override
	public void prOffenderIepLevels(Long offenderBookId, String modifyUserId) {
		purgeOffenderBookingsRepository.deleteprOffenderIepLevels(offenderBookId, modifyUserId);
	}

	@Override
	public void prOffenderImages(Long offenderBookId, String modifyUserId) {
		purgeOffenderBookingsRepository.deleteprOffenderImages(offenderBookId, modifyUserId);
	}

	@Override
	public void prOffenderImprisonStatuses(Long offenderBookId, String modifyUserId) {
		purgeOffenderBookingsRepository.deleteprOffenderImprisonStatuses(offenderBookId, modifyUserId);
	}

	@Override
	public void prOffenderIncome(Long offenderBookId, String modifyUserId) {
		purgeOffenderBookingsRepository.deleteprOffenderIncome(offenderBookId, modifyUserId);															
	}

	@Override
	public void prOffenderIndSchedules(Integer offenderBookId, String modifyUserId) {
		purgeOffenderBookingsRepository.prOffenderIndSchedules(offenderBookId).forEach(val->{
			prOffenderIndSchSents1(val.getEventId(), modifyUserId);
			prOffenderIndSchWaitLists(val.getEventId(), modifyUserId);
		});
		purgeOffenderBookingsRepository.deleteprOffenderIndSchedules(offenderBookId, modifyUserId);
	}

	@Override
	public void prOffenderIndSchSents1(Integer eventId, String modifyUserId) {
		purgeOffenderBookingsRepository.deleteprOffenderIndSchSents1(eventId, modifyUserId);
	}

	@Override
	public void prOffenderIndSchWaitLists(Integer eventId, String modifyUserId) {
		purgeOffenderBookingsRepository.deleteprOffenderIndSchWaitLists(eventId, modifyUserId);
	}

	@Override
	public void prOffenderInternalMovements(Long offenderBookId, String modifyUserId) {
		purgeOffenderBookingsRepository.prOffenderInternalMovements(offenderBookId).forEach(bean->prOffenderIntMovPersons(bean.getOffenderBookId(), bean.getMovementSeq(), modifyUserId));;
	}

	@Override
	public void prOffenderIntMovPersons(Long offenderBookId, Long movementSeq, String modifyUserId) {
		purgeOffenderBookingsRepository.deleteprOffenderIntMovPersons(offenderBookId, movementSeq, modifyUserId);
	}

	@Override
	public void prOffenderInternalStatuses(Integer offenderBookId, String modifyUserId) {
		purgeOffenderBookingsRepository.deleteprOffenderInternalStatuses(offenderBookId, modifyUserId);
	}

	@Override
	public void prOffenderInterventionTiers(Long offenderBookId, String modifyUserId) {
		purgeOffenderBookingsRepository.deleteprOffenderInterventionTiers(offenderBookId, modifyUserId);
	}

	@Override
	public void prOffenderLanguages(Long offenderBookId, String modifyUserId) {
		purgeOffenderBookingsRepository.deleteprOffenderLanguages(offenderBookId, modifyUserId);
	}

	@Override
	public void prOffenderLiabilities(Long offenderBookId, String modifyUserId) {
		purgeOffenderBookingsRepository.deleteprOffenderLiabilities(offenderBookId, modifyUserId);
	}

	@Override
	public void prOffenderMailLogs(Long offenderBookId) {
		purgeOffenderBookingsRepository.deleteprOffenderMailLogs(offenderBookId);
	}

	@Override
	public void prOffenderMailRestrictions(Long offenderBookId, String modifyUserId) {
		purgeOffenderBookingsRepository.deleteprOffenderMailRestrictions(offenderBookId, modifyUserId);
	}

	@Override
	public void prOffendrMilitryDiscCtions(Long offenderBookId, String modifyUserId) {
		purgeOffenderBookingsRepository.deleteprOffendrMilitryDiscCtions(offenderBookId, modifyUserId);
	}

	@Override
	public void prOffenderMilitaryRecords(Long offenderBookId, String modifyUserId) {
		purgeOffenderBookingsRepository.deleteprOffenderMilitaryRecords(offenderBookId, modifyUserId);
	}

	@Override
	public void prOffenderMilitaryWarZones(Long offenderBookId, String modifyUserId) {
		purgeOffenderBookingsRepository.prOffenderMilitaryWarZones(offenderBookId, modifyUserId);
	}

	@Override
	public void prOffenderOasysPlans(Long offenderBookId, String modifyUserId) {
		purgeOffenderBookingsRepository.prOffenderOasysPlans(offenderBookId).forEach(bean->{
			prOffenderOasysConcerns(bean.getOffenderBookId().longValue(), bean.getPlanSeq(), modifyUserId);
			prOffenderOsysRiskToOthrs(bean.getOffenderBookId().longValue(), bean.getPlanSeq(), modifyUserId);
			prOffenderOasysSections(bean.getOffenderBookId().longValue(), bean.getPlanSeq().intValue(), modifyUserId);
		});;
	}

	@Override
	public void prOffenderOasysConcerns(Long offenderBookId,Long pPlanSeq, String modifyUserId) {
		purgeOffenderBookingsRepository.deleteprOffenderOasysConcerns(offenderBookId, pPlanSeq, modifyUserId);
	}

	@Override
	public void prOffenderOsysRiskToOthrs(Long offenderBookId, Long pPlanSeq, String modifyUserId) {
		purgeOffenderBookingsRepository.deleteprOffenderOsysRiskToOthrs(offenderBookId, pPlanSeq, modifyUserId);
	}

	@Override
	public void prOffenderOasysSections(Long offenderBookId, Integer planSeq, String modifyUserId) {
		purgeOffenderBookingsRepository.prOffenderOasysSections(offenderBookId, planSeq).forEach(bean->prOffenderOasysObjectives(bean.getOffenderBookId(), bean.getPlanSeq(), bean.getSectionCode(), modifyUserId));;
	}

	@Override
	public void prOffenderOasysObjectives(Long offenderBookId, Integer planSeq,String sectionCode, String modifyUserId) {
		purgeOffenderBookingsRepository.prOffenderOasysObjectives(offenderBookId, planSeq, sectionCode).forEach(val->prOffenderOasysActions(val.getObjectiveId(), modifyUserId));
		purgeOffenderBookingsRepository.deleteprOffenderOasysObjectives(offenderBookId, planSeq, sectionCode, modifyUserId);
	}

	@Override
	public void prOffenderOasysActions(BigDecimal objectiveId, String modifyUserId) {
		purgeOffenderBookingsRepository.prOffenderOasysActions(objectiveId).forEach(val->prOffenderOasysSupervisions(val.getObjectiveId(),val.getActionSeq()));
		purgeOffenderBookingsRepository.deleteprOffenderOasysActions(objectiveId, modifyUserId);
	}

	@Override
	public void prOffenderOasysSupervisions(BigDecimal objectiveId,Long actionSeq) {
		purgeOffenderBookingsRepository.deleteprOffenderOasysSupervisions(objectiveId,actionSeq);
	}

	@Override
	public void prOffenderOicSanctions(Integer offenderBookId, String modifyUserId) {
		purgeOffenderBookingsRepository.prOffenderOicSanctions(offenderBookId).forEach(bean->{
			prOffenderOicSanctions2(bean.getOffenderBookId(), bean.getConsecutiveSanctionSeq());
			prOffenderOicSancReviews(new BigDecimal(bean.getOffenderBookId()), new BigDecimal(bean.getSanctionSeq()), modifyUserId);
		});
	}

	@Override
	public void prOffenderParoleHearings(Long offenderBookId, String modifyUserId) {
		purgeOffenderBookingsRepository.prOffenderParoleHearings(offenderBookId).forEach(bean->prOffenderParoleDecisions(bean.getOffenderBookId().longValue(), bean.getParoleHearingSeq(), modifyUserId));;
	}

	@Override
	public void prOffenderParoleDecisions(Long offenderBookId, Long paroleHearingSeq, String modifyUserId) {
		purgeOffenderBookingsRepository.prOffenderParoleDecisions(offenderBookId, paroleHearingSeq).forEach(bean->{
			prOffenderParoleConditions(bean.getScheduleId(), bean.getParoleDecisionSeq().longValue(), modifyUserId);
			prOffenderParoleStipultions(bean.getScheduleId(), bean.getParoleDecisionSeq().longValue(), modifyUserId);
		});
		purgeOffenderBookingsRepository.deleteprOffenderParoleDecisions(offenderBookId, paroleHearingSeq, modifyUserId);
	}

	@Override
	public void prOffenderParoleConditions(Long pScheduleId, Long pParoleDecisionSeq, String modifyUserId) {
		purgeOffenderBookingsRepository.deleteprOffenderParoleConditions(pScheduleId, pParoleDecisionSeq, modifyUserId);
	}

	@Override
	public void prOffenderParoleStipultions(Long pScheduleId, Long pParoleDecisionSeq, String modifyUserId) {
		purgeOffenderBookingsRepository.deleteprOffenderParoleStipultions(pScheduleId, pParoleDecisionSeq, modifyUserId);
	}

	@Override
	public void prOffenderParolePlans(Long offenderBookId, String modifyUserId) {
		purgeOffenderBookingsRepository.deleteprOffenderParolePlans(offenderBookId, modifyUserId);
	}

	@Override
	public void prOffenderPlanedActivities(Long offenderBookId, String modifyUserId) {
		purgeOffenderBookingsRepository.deleteprOffenderPlanedActivities(offenderBookId, modifyUserId);
	}

	@Override
	public void prOffenderPptyContainers(Integer offenderBookId, String modifyUserId) {
		purgeOffenderBookingsRepository.deleteprOffenderPptyContainers(offenderBookId, modifyUserId);
	}

	@Override
	public void prOffenderPptyItems(Integer offenderBookId, String modifyUserId) {
		purgeOffenderBookingsRepository.deleteprOffenderPptyItems(offenderBookId, modifyUserId);
	}

	@Override
	public void prOffenderPptyItemEvents(Integer offenderBookId) {
		purgeOffenderBookingsRepository.prOffenderPptyItemEvents(offenderBookId).forEach(bean->prOffenderPptyItemTxns(bean.getOffenderBookId(), bean.getEventSeq()));;
		purgeOffenderBookingsRepository.deleteprOffenderPptyItemEvents(offenderBookId);
	}

	@Override
	public void prOffenderPptyItemTxns(Integer offenderBookId, Integer eventSeq) {
		purgeOffenderBookingsRepository.deleteprOffenderPptyItemTxns(offenderBookId, eventSeq);
	}

	@Override
	public void prOffenderPrgObligations(Long offenderBookId,String modifyUserId) {
		purgeOffenderBookingsRepository.prOffenderPrgObligations(offenderBookId).forEach(val->{
			prOffenderCourseAttendancs1(val.getOffenderPrgObligationId(), modifyUserId);
			prOffenderIndSchedules1(val.getOffenderPrgObligationId()!=null?val.getOffenderPrgObligationId().intValue():null, modifyUserId);
			prOffenderObligationAdjs(val.getOffenderPrgObligationId()!=null?val.getOffenderPrgObligationId().intValue():null);
			prOffenderPrgObligationHty(val.getOffenderPrgObligationId()!=null?val.getOffenderPrgObligationId().intValue():null);
			prOffenderProgramProfiles1(val.getOffenderPrgObligationId()!=null?val.getOffenderPrgObligationId().intValue():null,modifyUserId);
		});
		purgeOffenderBookingsRepository.deleteprOffenderPrgObligations(offenderBookId);
	}

	@Override
	public void prOffenderCourseAttendancs1(Long offenderPrgObligationId, String modifyUserId) {
		purgeOffenderBookingsRepository.prOffenderCourseAttendancs1(offenderPrgObligationId).forEach(val->prOffenderCourseSkills(val.getEventId(), modifyUserId));;
		purgeOffenderBookingsRepository.deleteprOffenderCourseAttendancs1(offenderPrgObligationId);
	}

	@Override
	public void prOffenderIndSchedules1(Integer offenderPrgObligationId, String modifyUserId) {
		purgeOffenderBookingsRepository.prOffenderIndSchedules1(offenderPrgObligationId).forEach(val->{
			prOffenderIndSchSents1(val.getOffenderPrgObligationId(), modifyUserId);
			prOffenderIndSchWaitLists(val.getOffenderPrgObligationId(), modifyUserId);
		});
		purgeOffenderBookingsRepository.deleteprOffenderIndSchedules1(offenderPrgObligationId);
	}

	@Override
	public void prOffenderObligationAdjs(Integer offenderPrgObligationId) {
		purgeOffenderBookingsRepository.deleteprOffenderObligationAdjs(offenderPrgObligationId);
	}

	@Override
	public void prOffenderPrgObligationHty(Integer offenderPrgObligationId) {
		purgeOffenderBookingsRepository.deleteprOffenderPrgObligationHty(offenderPrgObligationId);
	}

	@Override
	public void prOffenderProgramProfiles1(Integer offenderPrgObligationId,String modifyUserId) {
		purgeOffenderBookingsRepository.prOffenderProgramProfiles1(offenderPrgObligationId).forEach(val->{
			prOffenderCourseApptGrps(val.getOffPrgrefId()!=null?new BigDecimal(val.getOffPrgrefId()):null,modifyUserId);
			prOffenderCourseAttendancs2(val.getOffPrgrefId(), modifyUserId);
			prOffenderCourseAttendancs2(val.getOffPrgrefId(), modifyUserId);
		});
		purgeOffenderBookingsRepository.deleteprOffenderProgramProfiles1(offenderPrgObligationId);
	}

	@Override
	public void prOffenderCourseApptGrps(BigDecimal offPrgrefId,String modifyUserId) {
		purgeOffenderBookingsRepository.prOffenderCourseApptGrps(offPrgrefId).forEach(val->prOffenderCourseApptRules(val.getOffenderCourseApptGrpId(),modifyUserId));;
		purgeOffenderBookingsRepository.deleteprOffenderCourseApptGrps(offPrgrefId,modifyUserId);
	}

	@Override
	public void prOffenderCourseApptRules(Long offenderCourseApptGrpId,String modifyUserId) {
		purgeOffenderBookingsRepository.deleteprOffenderCourseApptRules(offenderCourseApptGrpId,modifyUserId);
	}

	@Override
	public void prOffenderCourseAttendancs2(Long offPrgrefId, String modifyUserId) {
		purgeOffenderBookingsRepository.prOffenderCourseAttendancs2(offPrgrefId).forEach(val->prOffenderCourseSkills(val.getEventId(), modifyUserId));
		purgeOffenderBookingsRepository.deleteprOffenderCourseAttendancs2(offPrgrefId);
	}

	@Override
	public void prOffenderProgramProfiles2(Long offPrgrefId,String modifyUserId) {
		purgeOffenderBookingsRepository.prOffenderProgramProfiles2(offPrgrefId).forEach(val->{
			prOffenderCourseApptRules(val.getOffPrgrefId(),modifyUserId);
			prOffenderCourseAttendancs2(val.getOffPrgrefId(), modifyUserId);
			prOffenderProgramProfiles2(val.getOffPrgrefId(),modifyUserId);
		});
		purgeOffenderBookingsRepository.deleteprOffenderProgramProfiles2(offPrgrefId,modifyUserId);
	}

	@Override
	public void prOffenderProceedings(BigDecimal offenderBookId, String modifyUserId) {
		purgeOffenderBookingsRepository.prOffenderProceedings(offenderBookId).forEach(val->{
			prCourtEvents2(val.getOffenderProceedingId()!=null?val.getOffenderProceedingId().intValue():null, modifyUserId);
			prOffenderProceedingSents1(val.getOffenderProceedingId());
		});
		purgeOffenderBookingsRepository.deleteprOffenderProceedings(offenderBookId);
	}

	@Override
	public void prCourtEvents2(Integer offenderProceedingId, String modifyUserId) {
		purgeOffenderBookingsRepository.prCourtEvents2(offenderProceedingId).forEach(val->{
			prCourtEventCharges(new BigDecimal(val.getEventId()), modifyUserId);
			prCourtEventSentences(new BigDecimal(val.getEventId()), modifyUserId);
			prLinkCaseTxns(new BigDecimal(val.getEventId()), modifyUserId);
		});
		purgeOffenderBookingsRepository.deleteprCourtEvents2(offenderProceedingId);
	}

	@Override
	public void prOffenderProceedingSents1(BigDecimal offenderProceedingId) {
		purgeOffenderBookingsRepository.deleteprOffenderProceedingSents1(offenderProceedingId);    	
	}

	@Override
	public void prOffenderProfiles(Long offenderBookId) {
		purgeOffenderBookingsRepository.deleteprOffenderProfiles(offenderBookId);
	}

	@Override
	public void prOffenderProgramProfiles(Long offenderBookId,String modifyUserId) {
		purgeOffenderBookingsRepository.prOffenderProgramProfiles(offenderBookId).forEach(val->{
			prOffenderCourseApptGrps(val.getOffPrgrefId()!=null?new BigDecimal(val.getOffPrgrefId()):null,modifyUserId);
			prOffenderCourseAttendancs2(val.getOffPrgrefId(), modifyUserId);
			prOffenderProgramProfiles2(val.getOffPrgrefId(),modifyUserId);
		});
		purgeOffenderBookingsRepository.deleteprOffenderProgramProfiles(offenderBookId);
	}

	@Override
	public void prOffenderPropertyBundles(BigDecimal offenderBookId) {
		purgeOffenderBookingsRepository.deleteprOffenderPropertyBundles(offenderBookId);    	
	}

	@Override
	public void prOffenderProposedMvmnts(Long offenderBookId) {
		purgeOffenderBookingsRepository.prOffenderProposedMvmnts(offenderBookId).forEach(bean->prOffenderMovementDetails(bean.getOffenderBookId().longValue(), bean.getMovementSeq().longValue()));;
		purgeOffenderBookingsRepository.deleteprOffenderProposedMvmnts(offenderBookId);
	}

	@Override
	public void prOffenderMovementDetails(Long offenderBookId,Long movememntseq) {
		purgeOffenderBookingsRepository.deleteprOffenderMovementDetails(offenderBookId, movememntseq);
	}

	@Override
	public void prOffenderPtr(Long offenderBookId) {
		purgeOffenderBookingsRepository.prOffenderPtr(offenderBookId).forEach(val->{
			prExtOwnershipTransfer(val.getPtrId());
			prOffenderBookingAgyLocs1(val.getPtrId());
			prOffenderPtrDetails(val.getPtrId());
		});;
		//missing
	}

	@Override
	public void prExtOwnershipTransfer(Long ptrId) {
		purgeOffenderBookingsRepository.deleteprExtOwnershipTransfer(ptrId);
	}

	@Override
	public void prOffenderBookingAgyLocs1(Long ptrId) {
		purgeOffenderBookingsRepository.deleteprOffenderBookingAgyLocs1(ptrId);
	}

	@Override
	public void prOffenderPtrDetails(Long ptrId) {
		purgeOffenderBookingsRepository.deleteprOffenderPtrDetails(ptrId);
	}

	@Override
	public void prOffenderReferrals(Long offenderBookId) {
		purgeOffenderBookingsRepository.deleteprOffenderReferrals(offenderBookId);
	}

	@Override
	public void prOffenderReleaseDetails(Long offenderBookId) {
		purgeOffenderBookingsRepository.deleteprOffenderReleaseDetails(offenderBookId);
	}

	@Override
	public void prOffenderResidences(Long offenderBookId) {
		purgeOffenderBookingsRepository.deleteprOffenderResidences(offenderBookId);
	}

	@Override
	public void prOffenderRiskPredictors(Long offenderBookId) {
		purgeOffenderBookingsRepository.deleteprOffenderRiskPredictors(offenderBookId);
	}
	@Override
	public void prOffenderSentCalculations(Long offenderBookId) {
		purgeOffenderBookingsRepository.deleteprOffenderSentCalculations(offenderBookId);    	
	}

	@Override
	public void prOffenderStgAffiliations(BigDecimal offenderBookId) {
		purgeOffenderBookingsRepository.prOffenderStgAffiliations(offenderBookId).forEach(bean->prOffenderStgDetails(bean.getOffenderBookId(), bean.getStgSeq()));;
		purgeOffenderBookingsRepository.deleteprOffenderStgAffiliations(offenderBookId);
	}

	@Override
	public void prOffenderStgDetails(BigDecimal offenderBookId, BigDecimal stgSeq) {
		purgeOffenderBookingsRepository.deleteprOffenderStgDetails(offenderBookId, stgSeq);
	}

	@Override
	public void prOffenderStgAssociations(BigDecimal offenderBookId) {
		purgeOffenderBookingsRepository.prOffenderStgAffiliations(offenderBookId);
	}

	@Override
	public void prOffenderSubstanceAbuses(BigDecimal offenderBookId) {
		purgeOffenderBookingsRepository.deleteprOffenderSubstanceAbuses(offenderBookId);
	}

	@Override
	public void prOffenderSupervisionLevels(BigDecimal offenderBookId) {
		purgeOffenderBookingsRepository.prOffenderSupervisionLevels(offenderBookId).forEach(bean->{
			prOffenderSupervisionPlans(bean.getOffenderBookId().intValue(),bean.getSupervisionLevelType()!=null?Long.parseLong(bean.getSupervisionLevelType()):null);
			prOffenderSupervisionRates(bean.getOffenderBookId().intValue(),bean.getSupervisionLevelType()!=null?Long.parseLong(bean.getSupervisionLevelType()):null);
		});
		purgeOffenderBookingsRepository.deleteprOffenderSupervisionLevels(offenderBookId);
	}

	@Override
	public void prOffenderSupervisionPlans(Integer offenderBookId, Long pSupervisionLevelType) {
		purgeOffenderBookingsRepository.prOffenderSupervisionPlans(offenderBookId, pSupervisionLevelType).forEach(bean->prOffenderPlanedActivities1(bean.getOffenderBookId(), bean.getPlanSeq()));;
		purgeOffenderBookingsRepository.deleteprOffenderSupervisionPlans(offenderBookId, pSupervisionLevelType);
	}

	@Override
	public void prOffenderPlanedActivities1(Integer offenderBookId, Long pSupervisionLevelType) {
		purgeOffenderBookingsRepository.deleteprOffenderPlanedActivities1(offenderBookId, pSupervisionLevelType);
	}

	@Override
	public void prOffenderSupervisionRates(Integer offenderBookId, Long pSupervisionType) {
		purgeOffenderBookingsRepository.deleteprOffenderSupervisionRates(offenderBookId, pSupervisionType);
	}

	@Override
	public void prOffenderTeamAssignments(Integer offenderBookId) {
		purgeOffenderBookingsRepository.deleteprOffenderTeamAssignments(offenderBookId);
	}

	@Override
	public void prOffenderTeamAssignHty(Long offenderBookId) {
		purgeOffenderBookingsRepository.deleteprOffenderTeamAssignHty(offenderBookId);
	}

	@Override
	public void prOffenderTemporaryAbsences(Long offenderBookId) {
		purgeOffenderBookingsRepository.deleteprOffenderTemporaryAbsences(offenderBookId);
	}

	@Override
	public void prOffenderTmpRelSchedules(Long offenderBookId) {
		purgeOffenderBookingsRepository.deleteprOffenderTmpRelSchedules(offenderBookId);
	}

	@Override
	public void prOffenderVisits(Long offenderBookId) {
		purgeOffenderBookingsRepository.prOffenderVisits(offenderBookId).forEach(val->prOffenderVisitVisitors(val.getOffenderVisitVisitorId()!=null?val.getOffenderVisitVisitorId().longValue():null));;
		purgeOffenderBookingsRepository.prOffenderVisits(offenderBookId);
	}

	@Override
	public void prOffenderVisitVisitors(Long offenderVisitVisitorId) {
		purgeOffenderBookingsRepository.deleteprOffenderVisitVisitors(offenderVisitVisitorId);
	}

	@Override
	public void prOffenderVitalSigns(BigDecimal offenderBookId) {
		purgeOffenderBookingsRepository.deleteprOffenderVitalSigns(offenderBookId);    	
	}

	@Override
	public void prOrders(BigDecimal offenderBookId,String modifyUserId) {
		purgeOffenderBookingsRepository.prOrders(offenderBookId).forEach(val->{
			prOffenderSentences1(val.getOrderId(),modifyUserId);
			prOrderPurposes(val.getOrderId(),modifyUserId);
		});
		purgeOffenderBookingsRepository.deleteprOrders(offenderBookId);
	}

	@Override
	public void prOffenderSentences1(BigDecimal orderId,String modifyUserId) {
		purgeOffenderBookingsRepository.prOffenderSentences1(orderId).forEach(bean->{
			prCourtEventSentences1(bean.getOffenderBookId(),bean.getSentenceSeq());
			prOffenderCaseNoteSents(bean.getOffenderBookId(),bean.getSentenceSeq(),modifyUserId);
			prOffndrCommnityConditions1(bean.getOffenderBookId(),bean.getSentenceSeq(),modifyUserId);
			prOffenderIndSchSents(bean.getOffenderBookId(),bean.getSentenceSeq(),modifyUserId);
			prOffenderLicenceSentences1(bean.getOffenderBookId(),bean.getSentenceSeq(),modifyUserId);
			prOffenderLicenceSentences2(bean.getOffenderBookId(),bean.getSentenceSeq());
			prOffenderProceedingSents(bean.getOffenderBookId(),bean.getSentenceSeq(),modifyUserId);
			prOffenderSentenceAdjusts(bean.getOffenderBookId(),bean.getSentenceSeq());
			prOffenderSentenceCharges1(bean.getOffenderBookId(),bean.getSentenceSeq());
			prOffenderSentenceHty(bean.getOffenderBookId(),bean.getSentenceSeq(),modifyUserId);
			prOffenderSentenceStatuses(bean.getOffenderBookId(),bean.getSentenceSeq(),modifyUserId);
			prOffenderSentenceTerms(bean.getOffenderBookId(),bean.getSentenceSeq(),modifyUserId);
			prOffenderSentenceUaEvents(bean.getOffenderBookId(),bean.getSentenceSeq(),modifyUserId);
			prOffenderUnpaidWorkAdj(bean.getOffenderBookId(),bean.getSentenceSeq(),modifyUserId);
		});
		purgeOffenderBookingsRepository.deleteprOffenderSentences1(orderId,modifyUserId);
	}

	@Override
	public void prCourtEventSentences1(Long offenderBookId, Long sentenceSeq) {
		purgeOffenderBookingsRepository.deleteprCourtEventSentences1(offenderBookId, sentenceSeq);
	}
	@Override
	public void prOffenderCaseNoteSents(Long offenderBookId, Long sentenceSeq,String modifyUserId) {
		purgeOffenderBookingsRepository.deleteprOffenderCaseNoteSents(offenderBookId, sentenceSeq,modifyUserId);
	}

	@Override
	public void prOffndrCommnityConditions1(Long offenderBookId, Long sentenceSeq,String modifyUserId) {
		purgeOffenderBookingsRepository.deleteprOffndrCommnityConditions1(offenderBookId, sentenceSeq,modifyUserId);
	}

	@Override
	public void prOffenderIndSchSents(Long offenderBookId, Long sentenceSeq,String modifyUserId) {
		purgeOffenderBookingsRepository.deleteprOffenderIndSchSents(offenderBookId, sentenceSeq,modifyUserId);
	}

	@Override
	public void prOffenderLicenceSentences1(Long offenderBookId, Long sentenceSeq,String modifyUserId) {
		purgeOffenderBookingsRepository.deleteprOffenderLicenceSentences1(offenderBookId, sentenceSeq,modifyUserId);
	}

	@Override
	public void prOffenderLicenceSentences2(Long offenderBookId, Long pLicenceSentenceSeq) {
		purgeOffenderBookingsRepository.deleteprOffenderLicenceSentences2(offenderBookId, pLicenceSentenceSeq);
	}

	@Override
	public void prOffenderProceedingSents(Long offenderBookId, Long sentenceSeq,String modifyUserId) {
		purgeOffenderBookingsRepository.deleteprOffenderProceedingSents(offenderBookId, sentenceSeq,modifyUserId);
	}

	@Override
	public void prOffenderSentenceAdjusts(Long offenderBookId, Long sentenceSeq) {
		purgeOffenderBookingsRepository.deleteprOffenderSentenceAdjusts(offenderBookId, sentenceSeq);
	}

	@Override
	public void prOffenderSentenceCharges1(Long offenderBookId, Long sentenceSeq) {
		purgeOffenderBookingsRepository.deleteprOffenderSentenceCharges1(offenderBookId, sentenceSeq);
	}

	@Override
	public void prOffenderSentenceHty(Long offenderBookId, Long sentenceSeq,String modifyUserId) {
		purgeOffenderBookingsRepository.deleteprOffenderSentenceHty(offenderBookId, sentenceSeq,modifyUserId);
	}

	@Override
	public void prOffenderSentenceStatuses(Long offenderBookId, Long sentenceSeq,String modifyUserId) {
		purgeOffenderBookingsRepository.deleteprOffenderSentenceStatuses(offenderBookId, sentenceSeq,modifyUserId);
	}

	@Override
	public void prOffenderSentenceTerms(Long offenderBookId, Long sentenceSeq,String modifyUserId) {
		purgeOffenderBookingsRepository.deleteprOffenderSentenceTerms(offenderBookId, sentenceSeq,modifyUserId);
	}

	@Override
	public void prOffenderSentenceUaEvents(Long offenderBookId, Long sentenceSeq,String modifyUserId) {
		purgeOffenderBookingsRepository.deleteprOffenderSentenceUaEvents(offenderBookId, sentenceSeq,modifyUserId);
	}

	@Override
	public void prOffenderUnpaidWorkAdj(Long offenderBookId, Long sentenceSeq,String modifyUserId) {
		purgeOffenderBookingsRepository.deleteprOffenderUnpaidWorkAdj(offenderBookId, sentenceSeq,modifyUserId);
	}

	@Override
	public void prOrderPurposes(BigDecimal orderId,String modifyUserId) {
		purgeOffenderBookingsRepository.deleteprOrderPurposes(orderId,modifyUserId);
	}

	@Override
	public void prReleasePlans(Long offenderBookId) {
		purgeOffenderBookingsRepository.prReleasePlans(offenderBookId).forEach(val->prRpOtherOccupants(val.getReleasePlanId()));;
		purgeOffenderBookingsRepository.deleteprReleasePlans(offenderBookId);
	}

	@Override
	public void prRpOtherOccupants(Long releasePlanId) {
		purgeOffenderBookingsRepository.deleteprRpOtherOccupants(releasePlanId);
	}

	@Override
	public void prStaffWorks(Long offenderBookId) {
		purgeOffenderBookingsRepository.prStaffWorks(offenderBookId).forEach(val->prStaffWorkAssignments(val.getStaffWorkId()));
		purgeOffenderBookingsRepository.deleteprStaffWorks(offenderBookId);	
	}

	@Override
	public void prStaffWorkAssignments(BigDecimal staffId) {
		purgeOffenderBookingsRepository.deleteprStaffWorkAssignments(staffId);
	}

	@Override
	public void prSupervisionTransactions(Long offenderBookId) {
		purgeOffenderBookingsRepository.deleteprSupervisionTransactions(offenderBookId);
	}

	@Override
	public void prTaskAssignmentHty(BigDecimal offenderBookId) {
		purgeOffenderBookingsRepository.deleteprTaskAssignmentHty(offenderBookId);
	}

	@Override
	public void prAddresses(BigDecimal ownerId, String ownerClass,String modifyUserId) {
		purgeOffenderBookingsRepository.prAddresses(ownerId, ownerClass).forEach(val->{
			prAddressUsages(val.getAddressId(),modifyUserId);
			prCourseActivities(val.getAddressId(),modifyUserId);
			prCurfewAddresses1(new BigDecimal(val.getAddressId()),modifyUserId);
			prOffenderExternalMovemnts1(new BigDecimal(val.getAddressId()),modifyUserId);
			prOffenderExternalMovemnts2(new BigDecimal(val.getAddressId()),modifyUserId);
			prOffenderMailLogs1(new BigDecimal(val.getAddressId()),modifyUserId);
			prOffenderMailRestrictions1(new BigDecimal(val.getAddressId()),modifyUserId);
			prPhones(new BigDecimal(val.getAddressId()),"ADDR");
		});;
		purgeOffenderBookingsRepository.deleteprAddresses(ownerId, ownerClass);
	}

	@Override
	public void prAddressUsages(Long addressId,String modifyUserId) {
		purgeOffenderBookingsRepository.deleteprAddressUsages(addressId,modifyUserId);    	
	}

	@Override
	public void prCourseActivities(Long servicesAddressId,String modifyUserId) {
		purgeOffenderBookingsRepository.prCourseActivities(servicesAddressId).forEach(val->{
			prCourseActivities1(val.getCrsActyId());
			prCourseActivityAreas(val.getCrsActyId(),modifyUserId);
			prCourseActivityParties(val.getCrsActyId(),modifyUserId);
			prCourseActivityProfiles(val.getCrsActyId(),modifyUserId);
			prCourseActivityReviews(val.getCrsActyId());
			prCourseSchedules(val.getCrsActyId(),modifyUserId);
			prCourseScheduleRules(val.getCrsActyId(),modifyUserId);
			prOffenderProgramProfiles3(val.getCrsActyId());
		});
		purgeOffenderBookingsRepository.deleteprCourseActivities(servicesAddressId,modifyUserId);
	}

	@Override
	public void prCourseActivities1(Long parentCrsActyId) {
		purgeOffenderBookingsRepository.prCourseActivities(parentCrsActyId);
	}

	@Override
	public void prCourseActivityAreas(Long parentCrsActyId,String modifyUserId) {
		purgeOffenderBookingsRepository.deleteprCourseActivityAreas(parentCrsActyId,modifyUserId);
	}

	@Override
	public void prCourseActivityParties(Long crsActyId,String modifyUserId) {
		purgeOffenderBookingsRepository.deleteprCourseActivityParties(crsActyId,modifyUserId);
	}

	@Override
	public void prCourseActivityProfiles(Long crsActyId,String modifyUserId) {
		purgeOffenderBookingsRepository.deleteprCourseActivityProfiles(crsActyId,modifyUserId);
	}

	@Override
	public void prCourseActivityReviews(Long crsActyId) {
		purgeOffenderBookingsRepository.deleteprCourseActivityReviews(crsActyId);
	}

	@Override
	public void prCourseSchedules(Long crsActyId,String modifyUserId) {
		purgeOffenderBookingsRepository.prCourseSchedules(crsActyId).forEach(val->{
			prCourseSchedules1(val.getCrsSchId());
			prCourseScheduleStaffs(val.getCrsSchId()!=null?new BigDecimal(val.getCrsSchId()):null,modifyUserId);
			prVideoReviewSummaries(val.getCrsSchId()!=null?new BigDecimal(val.getCrsSchId()):null);
		});
		purgeOffenderBookingsRepository.deleteprCourseSchedules(crsActyId);
	}

	@Override
	public void prCourseSchedules1(Long catchUpCrsSchId) {
		purgeOffenderBookingsRepository.prCourseSchedules1(catchUpCrsSchId);
	}

	@Override
	public void prCourseScheduleStaffs(BigDecimal crsSchId,String modifyUserId) {
		purgeOffenderBookingsRepository.deleteprCourseScheduleStaffs(crsSchId,modifyUserId);
	}

	@Override
	public void prVideoReviewSummaries(BigDecimal crsSchId) {
		purgeOffenderBookingsRepository.prVideoReviewSummaries(crsSchId).forEach(val->prVideoReviewSections(val.getVideoReviewSummaryId()!=null?val.getVideoReviewSummaryId().longValue():null));
		purgeOffenderBookingsRepository.deleteprVideoReviewSummaries(crsSchId);
	}

	@Override
	public void prVideoReviewSections(Long pVideoReviewSummaryId) {
		purgeOffenderBookingsRepository.prVideoReviewSections(pVideoReviewSummaryId).forEach(obj->prVideoReviewSubsections(obj.getSectionCode() ,obj.getVideoReviewSummaryId()));;
		purgeOffenderBookingsRepository.deleteprVideoReviewSections(pVideoReviewSummaryId);
	}

	@Override
	public void prVideoReviewSubsections(String sectionCode, BigDecimal pVideoReviewSummaryId) {
		purgeOffenderBookingsRepository.deleteprVideoReviewSubsections(sectionCode, pVideoReviewSummaryId);
	}

	@Override
	public void prCourseScheduleRules(Long crsActyId,String modifyUserId) {
		purgeOffenderBookingsRepository.deleteprCourseScheduleRules(crsActyId,modifyUserId);
	}


	@Override
	public void prOffenderCourseAttendancs3(Long crsActyId, String modifyUserId) {
		purgeOffenderBookingsRepository.prOffenderCourseAttendancs3(crsActyId).forEach(val->prOffenderCourseSkills(val.getEventId(), modifyUserId));;
		purgeOffenderBookingsRepository.deleteprOffenderCourseAttendancs3(crsActyId);
	}

	@Override
	public void prOffenderProgramProfiles3(Long crsActyId) {
		purgeOffenderBookingsRepository.prOffenderProgramProfiles3(crsActyId);
	}

	@Override
	public void prCurfewAddresses1(BigDecimal pAddressId,String modifyUserId) {
		purgeOffenderBookingsRepository.prCurfewAddresses1(pAddressId).forEach(val->prCurfewAddressOccupants(new BigDecimal(val.getOffenderCurfewId()),modifyUserId));
		purgeOffenderBookingsRepository.deleteprCurfewAddresses1(pAddressId);
	}

	@Override
	public void prOffenderExternalMovemnts1(BigDecimal toAddressId,String modifyUserId) {
		purgeOffenderBookingsRepository.deleteprOffenderExternalMovemnts1(toAddressId,modifyUserId);
	}

	@Override
	public void prOffenderExternalMovemnts2(BigDecimal fromAddressId,String modifyUserId) {
		purgeOffenderBookingsRepository.deleteprOffenderExternalMovemnts2(fromAddressId,modifyUserId);
	}

	@Override
	public void prOffenderMailLogs1(BigDecimal pMailAddressId,String modifyUserId) {
		purgeOffenderBookingsRepository.deleteprOffenderMailLogs1(pMailAddressId,modifyUserId);
	}

	@Override
	public void prOffenderMailRestrictions1(BigDecimal pRestrictionAddressId,String modifyUserId) {
		purgeOffenderBookingsRepository.deleteprOffenderMailRestrictions1(pRestrictionAddressId,modifyUserId);
	}

	@Override
	public void prPhones(BigDecimal ownerId, String ownerClass) {
		purgeOffenderBookingsRepository.deleteprPhones(ownerId, ownerClass);
	}

	@Override
	public void prInternetAddresses(BigDecimal ownerId, String ownerClass) {
		purgeOffenderBookingsRepository.deleteprInternetAddresses(ownerId, ownerClass);
	}
	@Override
	public void prImages(BigDecimal imageObjectId, String imageObjectType) {
		purgeOffenderBookingsRepository.prImages(imageObjectId, imageObjectType).forEach(val->{
			prImageOriginals(val.getImageId());
			prImageProperties((int)val.getImageId());
		});
		purgeOffenderBookingsRepository.deleteprImages(imageObjectId, imageObjectType);
	}

	@Override
	public void prImageOriginals(Long imageId) {
		purgeOffenderBookingsRepository.deleteprImageOriginals(imageId);
	}

	@Override
	public void prImageProperties(Integer imageId) {
		purgeOffenderBookingsRepository.deleteprImageProperties(imageId);
	}

	@Override
	public void prOffenderCaseAssociations(Long offenderBookId) {
		purgeOffenderBookingsRepository.deleteprOffenderCaseAssociations(offenderBookId);
	}

	@Override
	public void prOffenderCaseAssociations1(BigDecimal pAssociatedOffBookId) {
		purgeOffenderBookingsRepository.deleteprOffenderCaseAssociations1(pAssociatedOffBookId);
	}

	@Override
	public void prWorkFlows(BigDecimal objectId) {
		purgeOffenderBookingsRepository.prWorkFlows(objectId).forEach(val->prWorkFlowLogs(val.getWorkFlowId()));;
		purgeOffenderBookingsRepository.deleteprWorkFlows(objectId);
	}

	@Override
	public void prWorkFlowLogs(Long workFlowId) {
		purgeOffenderBookingsRepository.deleteprWorkFlowLogs(workFlowId);
	}

	@Override
	public void prOffenderProfileDetails(Long offenderBookId) {
		purgeOffenderBookingsRepository.deleteprOffenderProfileDetails(offenderBookId);
	}

	@Override
	public void prOffenderBillingProfiles(Long offenderBookingId) {
		purgeOffenderBookingsRepository.deleteprOffenderBillingProfiles(offenderBookingId);
	}

	@Override
	public void prQmProcessesIns(String detail) {
		purgeOffenderBookingsRepository.prQmProcessesIns(detail).forEach(val->{
			prQmActivitiesIns(val.getProcessInsId());
			prQmObjectIns(val.getProcessInsId());
		});;
		purgeOffenderBookingsRepository.deleteprQmProcessesIns(detail);
	}

	@Override
	public void prQmActivitiesIns(BigDecimal pProcessInsId) {
		purgeOffenderBookingsRepository.deleteprQmActivitiesIns(pProcessInsId);
	}

	@Override
	public void prQmObjectIns(BigDecimal pProcessInsId) {
		purgeOffenderBookingsRepository.deleteprQmObjectIns(pProcessInsId);
	}

	@Override
	public void prQmProcessesInsHist(String detail) {
		purgeOffenderBookingsRepository.prQmProcessesInsHist(detail).forEach(val->{
			prQmActivitiesInsHist(val.getProcessInsId());
			prQmObjectInsHist(val.getProcessInsId());
		});;
		//missed
	}

	@Override
	public void prQmActivitiesInsHist(BigDecimal pProcessInsId) {
		purgeOffenderBookingsRepository.deleteprQmActivitiesInsHist(pProcessInsId);
	}

	@Override
	public void prQmObjectInsHist(BigDecimal pProcessInsId) {
		purgeOffenderBookingsRepository.deleteprQmObjectInsHist(pProcessInsId);
	}

	@Override
	public void prOffenderSupervisingCourts(BigDecimal offenderBookId) {
		purgeOffenderBookingsRepository.deleteprOffenderSupervisingCourts(offenderBookId);    	
	}

	@Override
	public void proffenderrestrictions(BigDecimal offenderBookId) {
		purgeOffenderBookingsRepository.deleteproffenderrestrictions(offenderBookId);
	}

	@Override
	public void proffenderOffIncentivesEarnPrivs(Long offenderBookId) {
		purgeOffenderBookingsRepository.deleteprOffenderOffIncentivesEarnPrivs(offenderBookId);
	}

	@Override
	public void proffendercustodystatus(Long offenderBookId,String modifyUserId) {
		purgeOffenderBookingsRepository.purgeDeleteOffenderCustodyStatus(offenderBookId,modifyUserId);
		
	}
}

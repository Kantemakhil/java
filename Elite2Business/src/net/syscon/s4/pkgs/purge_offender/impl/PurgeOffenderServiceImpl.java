package net.syscon.s4.pkgs.purge_offender.impl;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.pkgs.purge_offender.PurgeOffenderRepository;
import net.syscon.s4.pkgs.purge_offender.PurgeOffenderService;
@Service
public class PurgeOffenderServiceImpl  implements PurgeOffenderService {
	@Autowired
	private PurgeOffenderRepository purgeOffenderRepository;

	@Override
	public void prOffenders(BigDecimal offenderId,String modifyUserId) {
		purgeOffenderRepository.prOffenders(offenderId!=null?offenderId.longValue():null).forEach(val->{
			prBioSubjects(val.getOffenderId()!=null?new BigDecimal(val.getOffenderId()):null,modifyUserId);
			prGlTransactions(val.getOffenderId()!=null?new BigDecimal(val.getOffenderId()):null,modifyUserId);
			prOffenders1(val.getOffenderId());
			prOffenderBalances(val.getOffenderId()!=null?new BigDecimal(val.getOffenderId()):null,modifyUserId);
			prOffenderBookings(val.getOffenderId()!=null?new BigDecimal(val.getOffenderId()):null,modifyUserId);
			prOffenderCommunityFiles(val.getOffenderId()!=null?new BigDecimal(val.getOffenderId()):null,modifyUserId);
			prOffenderIdentifiers(val.getOffenderId()!=null?new BigDecimal(val.getOffenderId()):null,modifyUserId);
			prOffenderObliVerifications(val.getOffenderId()!=null?new BigDecimal(val.getOffenderId()):null,modifyUserId);
			prOffenderPaymentProfiles(val.getOffenderId()!=null?new BigDecimal(val.getOffenderId()):null,modifyUserId);
			prOffenderPlacementScores(val.getOffenderId()!=null?new BigDecimal(val.getOffenderId()):null,modifyUserId);
			prOffenderTransactions(val.getOffenderId(),modifyUserId);
			prOffenderTrustAccounts(val.getOffenderId(),modifyUserId);
			prOffenderTrustAccountsTmp(val.getOffenderId(),modifyUserId);
			prOffenderVitalSigns(val.getOffenderId(),modifyUserId);
			prAddresses(val.getOffenderId()!=null?new BigDecimal(val.getOffenderId()):null,"OFF",modifyUserId);
			prPhones(val.getOffenderId()!=null?new BigDecimal(val.getOffenderId()):null,"OFF",modifyUserId);
			prOffenderNonAssociations(val.getOffenderId(),modifyUserId);
			prOffenderNonAssociations1(val.getOffenderId(),modifyUserId);
			prOffenderContactPersons(val.getOffenderId()!=null?new BigDecimal(val.getOffenderId()):null,modifyUserId);
		});
		purgeOffenderRepository.deleteprOffenders(offenderId,modifyUserId);
	}


	@Override
	public void prBioSubjects(BigDecimal pRootOffenderId,String modifyUserId) {
		purgeOffenderRepository.prBioSubjects(pRootOffenderId).forEach(val->{
			prBioScans(val.getSubjectId(),modifyUserId);
			prBioEventLogs(val.getSubjectId(),modifyUserId);
		});
		purgeOffenderRepository.deleteprBioSubjects(pRootOffenderId,modifyUserId);
	}

	@Override
	public void prBioScans(BigDecimal pSubjectId,String modifyUserId) {
		purgeOffenderRepository.prBioScans(pSubjectId).forEach(bean->prBioFpSamples(bean.getScanId(), bean.getSubjectId(),modifyUserId));
		purgeOffenderRepository.deleteprBioScans(pSubjectId,modifyUserId);
	}

	@Override
	public void prBioFpSamples(BigDecimal pScanId, BigDecimal pSubjectId,String modifyUserId) {
		purgeOffenderRepository.deleteprBioFpSamples(pScanId, pSubjectId,modifyUserId);
	}

	@Override
	public void prBioEventLogs(BigDecimal pSubjectId,String modifyUserId) {
		purgeOffenderRepository.deleteprBioEventLogs(pSubjectId,modifyUserId);
	}

	@Override
	public void prGlTransactions(BigDecimal offenderId,String modifyUserId) {
		purgeOffenderRepository.updateprGlTransactions(offenderId,modifyUserId);
	}

	@Override
	public void prOffenders1(Long aliasOffenderId) {
		purgeOffenderRepository.prOffenders1(aliasOffenderId);
	}

	@Override
	public void prOffenderBalances(BigDecimal offenderId,String modifyUserId) {
		purgeOffenderRepository.deleteprOffenderBalances(offenderId,modifyUserId);
	}

	@Override
	public void prOffenderBookings(BigDecimal offenderId,String modifyUserId) {
		purgeOffenderRepository.prOffenderBookings(offenderId).forEach(val->{
			prAgencyIncidentParties(val.getOffenderBookId() != null ? val.getOffenderBookId().intValue() : null);
			prArrests(val.getOffenderBookId(),modifyUserId);
			prAssignments(val.getOffenderBookId(),modifyUserId);
			prBedAssignmentHistories(val.getOffenderBookId()!=null?val.getOffenderBookId().intValue():null,modifyUserId);
			prCaseAssociatedPersons(val.getOffenderBookId(),modifyUserId);
			prCaseNotes(val.getOffenderBookId(),modifyUserId);
			prCasePlans(val.getOffenderBookId(),modifyUserId);
			prCourtEvents(val.getOffenderBookId()!=null?val.getOffenderBookId().intValue():null,modifyUserId);
			prDocumentTemplateQueues(val.getOffenderBookId()!=null?new BigDecimal(val.getOffenderBookId()):null,modifyUserId);
			prGlTransactions1(val.getOffenderBookId()!=null?new BigDecimal(val.getOffenderBookId()):null,modifyUserId);
			prHdcRequestReferrals(val.getOffenderBookId(),modifyUserId);
			prIncidentCaseParties(val.getOffenderBookId(),modifyUserId);
			prIwpDocuments(val.getOffenderBookId()!=null?new BigDecimal(val.getOffenderBookId()):null,modifyUserId);
			prOffenderActivityEvents(val.getOffenderBookId()!=null?new BigDecimal(val.getOffenderBookId()):null,modifyUserId);
			prOffenderAlerts(val.getOffenderBookId()!=null?new BigDecimal(val.getOffenderBookId()):null,modifyUserId);
			prOffenderAssessments(val.getOffenderBookId()!=null?new BigDecimal(val.getOffenderBookId()):null,modifyUserId);
			prOffenderAssets(val.getOffenderBookId()!=null?new BigDecimal(val.getOffenderBookId()):null,modifyUserId);
			prOffenderAssociatedParties(val.getOffenderBookId(),modifyUserId);
			prOffenderAuthorisdVisitors(val.getOffenderBookId()!=null?new BigDecimal(val.getOffenderBookId()):null,modifyUserId);
			prOffenderAthorisdVisitors1(val.getOffenderBookId()!=null?new BigDecimal(val.getOffenderBookId()):null,modifyUserId);
			prOffenderBailDetails(val.getOffenderBookId(),modifyUserId);
			prOffenderBookingAgyLocs(val.getOffenderBookId(),modifyUserId);
			prOffenderBookingDetails(val.getOffenderBookId(),modifyUserId);
			prOffenderBookingEvents(val.getOffenderBookId(),modifyUserId);
			prOffenderCases(val.getOffenderBookId(),modifyUserId);
			prOffenderCaseNotes(val.getOffenderBookId()!=null?val.getOffenderBookId().intValue():null,modifyUserId);
			prOffenderCharges(val.getOffenderBookId(),modifyUserId);
			prOffenderCipDetails(val.getOffenderBookId(),modifyUserId);
			prOffenderClassPrograms(val.getOffenderBookId(),modifyUserId);
			prOffenderClothes(val.getOffenderBookId(),modifyUserId);
			prOffenderClothingBundles(val.getOffenderBookId(),modifyUserId);
			prOffenderClothingIssue(val.getOffenderBookId(),modifyUserId);
			prOffenderClothingItems(val.getOffenderBookId(),modifyUserId);
			prOffenderCodefendants(val.getOffenderBookId(),modifyUserId);
			prOffenderCodefendants1(val.getOffenderBookId(),modifyUserId);
			prOffenderComments(val.getOffenderBookId(),modifyUserId);
			prOffendrCommnityConditions(val.getOffenderBookId(),modifyUserId);
			prOffenderCommSupHistories(val.getOffenderBookId(),modifyUserId);
			prOffenderCompactAgreements(val.getOffenderBookId(),modifyUserId);
			prOffenderContactPersons1(val.getOffenderBookId()!=null?new BigDecimal(val.getOffenderBookId()):null,modifyUserId);
			prOffenderCourseAttendances(val.getOffenderBookId(),modifyUserId);
			prOffenderCurfews(val.getOffenderBookId(),modifyUserId);
			prOffenderDrugAdmissions(val.getOffenderBookId(),modifyUserId);
			prOffenderDrugSamples(val.getOffenderBookId(),modifyUserId);
			prOffenderEducations(val.getOffenderBookId(),modifyUserId);
			prOffenderEmployments(val.getOffenderBookId(),modifyUserId);
			prOffenderEscapes(val.getOffenderBookId()!=null?val.getOffenderBookId().intValue():null,modifyUserId);
			prOffenderExpenses(val.getOffenderBookId(),modifyUserId);
			prOffenderExternalMovements(val.getOffenderBookId(),modifyUserId);
			prOffenderFileLocations(val.getOffenderBookId(),modifyUserId);
			prOffenderGangAffiliations(val.getOffenderBookId(),modifyUserId);
			prOffenderGangEvidences(val.getOffenderBookId(),modifyUserId);
			prOffenderGangInvests(val.getOffenderBookId(),modifyUserId);
			prOffenderGrievances(val.getOffenderBookId(),modifyUserId);
			prOffenderHospitalVisits(val.getOffenderBookId(),modifyUserId);
			prOffenderHwd(val.getOffenderBookId(),modifyUserId);
			prOffenderIdentifyingMarks(val.getOffenderBookId(),modifyUserId);
			prOffenderIepLevels(val.getOffenderBookId(),modifyUserId);
			prOffenderImages(val.getOffenderBookId(),modifyUserId);
			prOffenderImprisonStatuses(val.getOffenderBookId(),modifyUserId);
			prOffenderIncome(val.getOffenderBookId(),modifyUserId);
			prOffenderIndSchedules(val.getOffenderBookId(),modifyUserId);
			prOffenderInternalMovements(val.getOffenderBookId(),modifyUserId);
			prOffenderInternalStatuses(val.getOffenderBookId()!=null?val.getOffenderBookId().intValue():null,modifyUserId);
			prOffenderInterventionTiers(val.getOffenderBookId(),modifyUserId);
			prOffenderLanguages(val.getOffenderBookId(),modifyUserId);
			prOffenderLiabilities(val.getOffenderBookId(),modifyUserId);
			prOffenderMailLogs(val.getOffenderBookId(),modifyUserId);
			prOffenderMailRestrictions(val.getOffenderBookId(),modifyUserId);
			prOffendrMilitryDiscCtions(val.getOffenderBookId(),modifyUserId);
			prOffenderMilitaryRecords(val.getOffenderBookId(),modifyUserId);
			prOffenderMilitaryWarZones(val.getOffenderBookId(),modifyUserId);
			prOffenderOasysPlans(val.getOffenderBookId(),modifyUserId);
			prOffenderOicSanctions(val.getOffenderBookId(),modifyUserId);
			prOffenderParoleHearings(val.getOffenderBookId(),modifyUserId);
			prOffenderParolePlans(val.getOffenderBookId(),modifyUserId);
			prOffenderPlanedActivities(val.getOffenderBookId(),modifyUserId);
			prOffenderPptyContainers(val.getOffenderBookId(),modifyUserId);
			prOffenderPptyItems(val.getOffenderBookId(),modifyUserId);
			prOffenderPptyItemEvents(val.getOffenderBookId()!=null?val.getOffenderBookId().intValue():null,modifyUserId);
			prOffenderPrgObligations(val.getOffenderBookId(),modifyUserId);
			prOffenderProceedings(val.getOffenderBookId()!=null?new BigDecimal(val.getOffenderBookId()):null,modifyUserId);
			prOffenderProfiles(val.getOffenderBookId()!=null?new BigDecimal(val.getOffenderBookId()):null,modifyUserId);
			prOffenderProgramProfiles(val.getOffenderBookId()!=null?new BigDecimal(val.getOffenderBookId()):null,modifyUserId);
			prOffenderPropertyBundles(val.getOffenderBookId()!=null?new BigDecimal(val.getOffenderBookId()):null,modifyUserId);
			prOffenderProposedMvmnts(val.getOffenderBookId()!=null?new BigDecimal(val.getOffenderBookId()):null,modifyUserId);
			proffenderptr(val.getOffenderBookId(),modifyUserId);
			prOffenderReferrals(val.getOffenderBookId(),modifyUserId);
			prOffenderReleaseDetails(val.getOffenderBookId()!=null?new BigDecimal(val.getOffenderBookId()):null,modifyUserId);
			prOffenderResidences(val.getOffenderBookId()!=null?new BigDecimal(val.getOffenderBookId()):null,modifyUserId);
			prOffenderRestrictions(val.getOffenderBookId()!=null?new BigDecimal(val.getOffenderBookId()):null,modifyUserId);
			prOffenderRiskPredictors(val.getOffenderBookId()!=null?new BigDecimal(val.getOffenderBookId()):null,modifyUserId);
			prOffenderSentCalculations(val.getOffenderBookId()!=null?new BigDecimal(val.getOffenderBookId()):null,modifyUserId);
			prOffenderStgAffiliations(val.getOffenderBookId()!=null?new BigDecimal(val.getOffenderBookId()):null,modifyUserId);
			prOffenderStgAssociations(val.getOffenderBookId()!=null?new BigDecimal(val.getOffenderBookId()):null,modifyUserId);
			prOffenderSubstanceAbuses(val.getOffenderBookId()!=null?new BigDecimal(val.getOffenderBookId()):null,modifyUserId);
			prOffenderSupervisingCourts(val.getOffenderBookId()!=null?new BigDecimal(val.getOffenderBookId()):null,modifyUserId);
			prOffenderSupervisionLevels(val.getOffenderBookId()!=null?new BigDecimal(val.getOffenderBookId()):null,modifyUserId);
			prOffenderTeamAssignments(val.getOffenderBookId(),modifyUserId);
			prOffenderTeamAssignHty(val.getOffenderBookId(),modifyUserId);
			prOffenderTemporaryAbsences(val.getOffenderBookId(),modifyUserId);
			prOffenderTmpRelSchedules(val.getOffenderBookId(),modifyUserId);
			prOffenderVisits(val.getOffenderBookId(),modifyUserId);
			prOffenderVitalSigns1(val.getOffenderBookId(),modifyUserId);
			prOrders(val.getOffenderBookId()!=null?new BigDecimal(val.getOffenderBookId()):null,modifyUserId);
			prReleasePlans(val.getOffenderBookId()!=null?new BigDecimal(val.getOffenderBookId()):null,modifyUserId);
			prStaffWorks(val.getOffenderBookId()!=null?new BigDecimal(val.getOffenderBookId()):null,modifyUserId);
			prSupervisionTransactions(val.getOffenderBookId()!=null?new BigDecimal(val.getOffenderBookId()):null,modifyUserId);
			prTaskAssignmentHty(val.getOffenderBookId()!=null?new BigDecimal(val.getOffenderBookId()):null,modifyUserId);
			proffendercustodystatus(val.getOffenderBookId(),modifyUserId);
		});
		purgeOffenderRepository.deleteprOffenderBookings(offenderId,modifyUserId);
	}


	@Override
	public void prAssignments(Long offenderBookId,String modifyUserId) {
		purgeOffenderRepository.deleteprAssignments(offenderBookId,modifyUserId);
	}

	@Override
	public void prAgencyIncidentParties(Integer offenderBookId) {
		purgeOffenderRepository.prAgencyIncidentParties(offenderBookId);
	}

	@Override
	public void prAgencyIncidentCharges(Integer agencyIncidentId, Integer partySeq) {
		purgeOffenderRepository.prAgencyIncidentCharges(agencyIncidentId, partySeq);
	}

	@Override
	public void prOicHearingResults(BigDecimal agencyIncidentId, BigDecimal chargeSeq,String modifyUserId) {
		purgeOffenderRepository.prOicHearingResults(agencyIncidentId, chargeSeq).forEach(bean->{
			proffenderoicappelincidnts(bean.getOicHearingId()!=null?bean.getOicHearingId().intValue():null,bean.getResultSeq()!=null?bean.getResultSeq().intValue():null,modifyUserId);
			prOffenderOicSanctions1(bean.getOicHearingId()!=null?bean.getOicHearingId().intValue():null,bean.getResultSeq()!=null?bean.getResultSeq().intValue():null,modifyUserId);
		});
		purgeOffenderRepository.deleteprOicHearingResults(agencyIncidentId, chargeSeq,modifyUserId);
	}

	@Override
	public void proffenderoicappelincidnts(Integer oicHearingId, Integer resultSeq,String modifyUserId) {
		purgeOffenderRepository.proffenderoicappelincidnts(oicHearingId, resultSeq).forEach(bean->prOffenderOicAppealPenltis(bean.getOicApprealId(),bean.getOicHearingId(),bean.getResultSeq(),modifyUserId));
		purgeOffenderRepository.deleteproffenderoicappelincidnts(oicHearingId, resultSeq,modifyUserId);
	}

	@Override
	public void prOffenderOicAppealPenltis(Integer oicApprealId, Integer oicHearingId, Integer resultSeq,String modifyUserId) {
		purgeOffenderRepository.deleteprOffenderOicAppealPenltis(oicApprealId, oicHearingId, resultSeq,modifyUserId);
	}

	@Override
	public void prOffenderOicSancReviews(BigDecimal offenderBookId, Long pSanctionSeq,String modifyUserId) {
		purgeOffenderRepository.deleteprOffenderOicSancReviews(offenderBookId, pSanctionSeq,modifyUserId);
	}

	@Override
	public void prOffenderOicSanctions2(Integer pConsecutivOffndrBookId, Integer pConsecutiveSanctionSeq,String modifyUserId) {
		purgeOffenderRepository.prOffenderOicSanctions2(pConsecutivOffndrBookId, pConsecutiveSanctionSeq).forEach(bean->{
			//calling same method
			prOffenderOicSanctions2(bean.getOffenderBookId(),bean.getSanctionSeq(),modifyUserId);
			prOffenderOicSancReviews(bean.getOffenderBookId()!=null?new BigDecimal(bean.getOffenderBookId()):BigDecimal.ZERO,bean.getSanctionSeq()!=null?bean.getSanctionSeq().longValue():null,modifyUserId);
		});
		purgeOffenderRepository.deleteprOffenderOicSanctions2(pConsecutivOffndrBookId, pConsecutiveSanctionSeq,modifyUserId);
	}

	@Override
	public void prOffenderOicSanctions1(Integer oicHearingId, Integer resultSeq,String modifyUserId) {
		purgeOffenderRepository.purgeoffenderrepository(oicHearingId, resultSeq).forEach(bean->{
			prOffenderOicSanctions2(bean.getOffenderBookId(),bean.getSanctionSeq(),modifyUserId);
			prOffenderOicSancReviews(bean.getOffenderBookId()!=null?new BigDecimal(bean.getOffenderBookId()):BigDecimal.ZERO,bean.getSanctionSeq()!=null?bean.getSanctionSeq().longValue():null,modifyUserId);
		});
		purgeOffenderRepository.deleteprOffenderOicSanctions1(oicHearingId, resultSeq,modifyUserId);
	}

	@Override
	public void prOffenderCaseNoteSents(Long offenderBookId, Long sentenceSeq,String modifyUserId) {
		purgeOffenderRepository.deleteprOffenderCaseNoteSents(offenderBookId, sentenceSeq,modifyUserId);
	}

	@Override
	public void prOffndrCommnityConditions1(Long offenderBookId, Long sentenceSeq,String modifyUserId) {
		purgeOffenderRepository.deleteprOffndrCommnityConditions1(offenderBookId, sentenceSeq,modifyUserId);
	}

	@Override
	public void prOffenderIndSchSents(Long offenderBookId, Long sentenceSeq,String modifyUserId) {
		purgeOffenderRepository.deleteprOffenderIndSchSents(offenderBookId, sentenceSeq,modifyUserId);
	}

	@Override
	public void prOffenderLicenceSentences1(Long offenderBookId, Long sentenceSeq,String modifyUserId) {
		purgeOffenderRepository.deleteprOffenderLicenceSentences1(offenderBookId, sentenceSeq,modifyUserId);
	}

	@Override
	public void prOffenderLicenceSentences2(Long offenderBookId, Long pLicenceSentenceSeq,String modifyUserId) {
		purgeOffenderRepository.deleteprOffenderLicenceSentences2(offenderBookId, pLicenceSentenceSeq,modifyUserId);
	}

	@Override
	public void prOffenderProceedingSents(Long offenderBookId, Long sentenceSeq,String modifyUserId) {
		purgeOffenderRepository.deleteprOffenderProceedingSents(offenderBookId, sentenceSeq,modifyUserId);
	}

	@Override
	public void prOffenderSentenceAdjusts(Long offenderBookId, Long sentenceSeq,String modifyUserId) {
		purgeOffenderRepository.deleteprOffenderSentenceAdjusts(offenderBookId, sentenceSeq,modifyUserId);
	}

	@Override
	public void prOffenderSentenceCharges1(Long offenderBookId, Long sentenceSeq,String modifyUserId) {
		purgeOffenderRepository.deleteprOffenderSentenceCharges1(offenderBookId, sentenceSeq,modifyUserId);
	}

	@Override
	public void prOffenderSentenceHty(Long offenderBookId, Long sentenceSeq,String modifyUserId) {
		purgeOffenderRepository.deleteprOffenderSentenceHty(offenderBookId, sentenceSeq,modifyUserId);
	}

	@Override
	public void prOffenderSentenceStatuses(Long offenderBookId, Long sentenceSeq,String modifyUserId) {
		purgeOffenderRepository.deleteprOffenderSentenceStatuses(offenderBookId, sentenceSeq,modifyUserId);
	}

	@Override
	public void prOffenderSentenceTerms(Long offenderBookId, Long sentenceSeq,String modifyUserId) {
		purgeOffenderRepository.deleteprOffenderSentenceTerms(offenderBookId, sentenceSeq,modifyUserId);
	}

	@Override
	public void prOffenderSentenceUaEvents(Long offenderBookId, Long sentenceSeq,String modifyUserId) {
		purgeOffenderRepository.deleteprOffenderSentenceUaEvents(offenderBookId, sentenceSeq,modifyUserId);
	}

	@Override
	public void prOffenderUnpaidWorkAdj(Long offenderBookId, Long sentenceSeq,String modifyUserId) {
		purgeOffenderRepository.deleteprOffenderUnpaidWorkAdj(offenderBookId, sentenceSeq,modifyUserId);
	}

	@Override
	public void prOffenderSentences1(BigDecimal orderId,String modifyUserId) {
		purgeOffenderRepository.prOffenderSentences1(orderId).forEach(bean->{
			prCourtEventSentences1(bean.getOffenderBookId(),bean.getSentenceSeq(),modifyUserId);
			prOffenderCaseNoteSents(bean.getOffenderBookId(),bean.getSentenceSeq(),modifyUserId);
			prOffndrCommnityConditions1(bean.getOffenderBookId(),bean.getSentenceSeq(),modifyUserId);
			prOffenderIndSchSents(bean.getOffenderBookId(),bean.getSentenceSeq(),modifyUserId);
			prOffenderLicenceSentences1(bean.getOffenderBookId(),bean.getSentenceSeq(),modifyUserId);
			prOffenderLicenceSentences2(bean.getOffenderBookId(),bean.getSentenceSeq(),modifyUserId);
			prOffenderProceedingSents(bean.getOffenderBookId(),bean.getSentenceSeq(),modifyUserId);
			prOffenderSentenceAdjusts(bean.getOffenderBookId(),bean.getSentenceSeq(),modifyUserId);
			prOffenderSentenceCharges1(bean.getOffenderBookId(),bean.getSentenceSeq(),modifyUserId);
			prOffenderSentenceHty(bean.getOffenderBookId(),bean.getSentenceSeq(),modifyUserId);
			prOffenderSentenceStatuses(bean.getOffenderBookId(),bean.getSentenceSeq(),modifyUserId);
			prOffenderSentenceTerms(bean.getOffenderBookId(),bean.getSentenceSeq(),modifyUserId);
			prOffenderSentenceUaEvents(bean.getOffenderBookId(),bean.getSentenceSeq(),modifyUserId);
			prOffenderUnpaidWorkAdj(bean.getOffenderBookId(),bean.getSentenceSeq(),modifyUserId);
		});
		purgeOffenderRepository.deleteprOffenderSentences1(orderId,modifyUserId);
	}

	@Override
	public void prCourtEventSentences1(Long offenderBookId, Long sentenceSeq,String modifyUserId) {
		purgeOffenderRepository.deleteprCourtEventSentences1(offenderBookId, sentenceSeq,modifyUserId);
	}

	@Override
	public void prOffenderCommunityFiles(BigDecimal offenderId,String modifyUserId) {
		purgeOffenderRepository.deleteprOffenderCommunityFiles(offenderId,modifyUserId);
	}

	@Override
	public void prOffenderIdentifiers(BigDecimal offenderId,String modifyUserId) {
		purgeOffenderRepository.deleteprOffenderIdentifiers(offenderId,modifyUserId);
	}

	@Override
	public void prOffenderObliVerifications(BigDecimal offenderId,String modifyUserId) {
		purgeOffenderRepository.deleteprOffenderObliVerifications(offenderId,modifyUserId);
	}

	@Override
	public void prOffenderPaymentProfiles(BigDecimal offenderId,String modifyUserId) {
		purgeOffenderRepository.prOffenderPaymentProfiles(offenderId).forEach(bean->prOffenderDeductions(bean.getOffenderPaymentProfileId(),modifyUserId));
		purgeOffenderRepository.deleteprOffenderPaymentProfiles(offenderId,modifyUserId);
	}

	@Override
	public void prOffenderDeductions(BigDecimal offenderPaymentProfileId,String modifyUserId) {
		purgeOffenderRepository.prOffenderDeductions(offenderPaymentProfileId).forEach(bean->{
			prOffenderAdjustmentTxns(bean.getOffenderDeductionId()+"",modifyUserId);
			prOffenderBeneficiaries(bean.getOffenderDeductionId(),modifyUserId);
			prOffenderDeductions1(new BigDecimal(bean.getOffenderDeductionId()),modifyUserId);
			prOffenderDeductionReceipts(bean.getOffenderDeductionId(),modifyUserId);
			prOffenderDeductionShadows(bean.getOffenderDeductionId(),modifyUserId);
			prOffenderDeductionReceipts(bean.getOffenderDeductionId(),modifyUserId);
			prOffenderMonBeneficiaries(bean.getOffenderDeductionId(),modifyUserId);
			prOffenderMonDeductions(bean.getOffenderDeductionId(),modifyUserId);
			prOffenderTierTxnFeeMonts(bean.getOffenderDeductionId(),modifyUserId);
			prOffenderTxnFeeDetails(bean.getOffenderDeductionId(),modifyUserId);
		});
		purgeOffenderRepository.deleteprOffenderDeductions(offenderPaymentProfileId,modifyUserId);
	}

	@Override
	public void prOffenderAdjustmentTxns(String adjustmentUserId,String modifyUserId) {
		purgeOffenderRepository.deleteprOffenderAdjustmentTxns(adjustmentUserId,modifyUserId);
	}

	@Override
	public void prOffenderBeneficiaries(Long offenderDeductionId,String modifyUserId) {
		purgeOffenderRepository.prOffenderBeneficiaries(offenderDeductionId).forEach(bean->{
			prBeneficiaryTransactions(bean.getBeneficiaryId()!=null?new BigDecimal(bean.getBeneficiaryId()):null,modifyUserId);
			prBeneficiaryTransactions(bean.getBeneficiaryId()!=null?new BigDecimal(bean.getBeneficiaryId()):null,modifyUserId);
			prOffenderMonBeneficiaries1(bean.getBeneficiaryId()!=null?new BigDecimal(bean.getBeneficiaryId()):null,modifyUserId);
		});
		purgeOffenderRepository.deleteprOffenderBeneficiaries(offenderDeductionId,modifyUserId);
	}

	@Override
	public void prBeneficiaryTransactions(BigDecimal beneficiaryId,String modifyUserId) {
		purgeOffenderRepository.deleteprBeneficiaryTransactions(beneficiaryId,modifyUserId);
	}

	@Override
	public void prOffenderCrditPriorPymnts(BigDecimal beneficiaryId,String modifyUserId) {
		purgeOffenderRepository.deleteprOffenderCrditPriorPymnts(beneficiaryId,modifyUserId);
	}

	@Override
	public void prOffenderMonBeneficiaries1(BigDecimal beneficiaryId,String modifyUserId) {
		purgeOffenderRepository.deleteprOffenderMonBeneficiaries1(beneficiaryId,modifyUserId);
	}

	@Override
	public void prOffenderDeductionReceipts(Long offenderDeductionId,String modifyUserId) {
		purgeOffenderRepository.deleteprOffenderDeductionReceipts(offenderDeductionId,modifyUserId);
	}

	@Override
	public void prOffenderDeductionShadows(Long offenderDeductionId,String modifyUserId) {
		purgeOffenderRepository.deleteprOffenderDeductionShadows(offenderDeductionId,modifyUserId);
	}

	@Override
	public void prOffenderMonBeneficiaries(Long offenderDeductionId,String modifyUserId) {
		purgeOffenderRepository.deleteprOffenderMonBeneficiaries(offenderDeductionId,modifyUserId);
	}

	@Override
	public void prOffenderMonDeductions(Long offenderDeductionId,String modifyUserId) {
		purgeOffenderRepository.deleteprOffenderMonDeductions(offenderDeductionId,modifyUserId);
	}

	@Override
	public void prOffenderTierTxnFeeMonts(Long offenderDeductionId,String modifyUserId) {
		purgeOffenderRepository.deleteprOffenderTierTxnFeeMonts(offenderDeductionId,modifyUserId);
	}

	@Override
	public void prOffenderTxnFeeDetails(Long offenderDeductionId,String modifyUserId) {
		purgeOffenderRepository.deleteprOffenderTxnFeeDetails(offenderDeductionId,modifyUserId);
	}

	@Override
	public void prOffenderDeductions1(BigDecimal parentDeductionId,String modifyUserId) {
		purgeOffenderRepository.prOffenderDeductions1(parentDeductionId).forEach(bean->{
			prOffenderAdjustmentTxns(bean.getOffenderDeductionId()+"",modifyUserId);
			prOffenderBeneficiaries(bean.getOffenderDeductionId(),modifyUserId);
			prOffenderDeductions1(bean.getOffenderDeductionId()!=null?new BigDecimal(bean.getOffenderDeductionId()):null,modifyUserId);
			prOffenderDeductionReceipts(bean.getOffenderDeductionId(),modifyUserId);
			prOffenderMonBeneficiaries(bean.getOffenderDeductionId(),modifyUserId);
			prOffenderMonDeductions(bean.getOffenderDeductionId(),modifyUserId);
			prOffenderTierTxnFeeMonts(bean.getOffenderDeductionId(),modifyUserId);
			prOffenderTxnFeeDetails(bean.getOffenderDeductionId(),modifyUserId);
		});
		purgeOffenderRepository.deleteprOffenderDeductions1(parentDeductionId,modifyUserId);
	}

	@Override
	public void prOffenderPlacementScores(BigDecimal rootOffenderId,String modifyUserId) {
		purgeOffenderRepository.deleteprOffenderPlacementScores(rootOffenderId,modifyUserId);
	}

	@Override
	public void prOffenderTransactions(Long offenderId,String modifyUserId) {
		purgeOffenderRepository.prOffenderTransactions(offenderId,modifyUserId).forEach(bean->{
			prOffendrCrditPriorPymnts1((long)bean.getTxnId(),(long)bean.getTxnEntrySeq(),modifyUserId);
			prOffenderPayrollTxns((long)bean.getTxnId(),(long)bean.getTxnEntrySeq(),modifyUserId);
			prOffenderTransactions1((long)bean.getTxnId(),(long)bean.getTxnEntrySeq(),modifyUserId);
		});
		purgeOffenderRepository.deleteprOffenderTransactions(offenderId,modifyUserId);
	}

	@Override
	public void prOffendrCrditPriorPymnts1(Long txnId, Long txnEntrySeq,String modifyUserId) {
		purgeOffenderRepository.deleteprOffendrCrditPriorPymnts1(txnId, txnEntrySeq,modifyUserId);
	}

	@Override
	public void prOffenderPayrollTxns(Long txnId, Long txnEntrySeq,String modifyUserId) {
		purgeOffenderRepository.deleteprOffenderPayrollTxns(txnId, txnEntrySeq,modifyUserId);
	}

	@Override
	public void prOffenderTransactions1(Long txnId, Long txnEntrySeq,String modifyUserId) {
		purgeOffenderRepository.prOffenderTransactions1(txnId, txnEntrySeq).forEach(bean->{
			prOffendrCrditPriorPymnts1(bean.getTxnId()!=null?bean.getTxnId().longValue():null,bean.getTxnEntrySeq()!=null?bean.getTxnEntrySeq().longValue():null,modifyUserId);
			prOffenderPayrollTxns(bean.getTxnId()!=null?bean.getTxnId().longValue():null,bean.getTxnEntrySeq()!=null?bean.getTxnEntrySeq().longValue():null,modifyUserId);
			prOffenderTransactions1(bean.getTxnId()!=null?bean.getTxnId().longValue():null,bean.getTxnEntrySeq()!=null?bean.getTxnEntrySeq().longValue():null,modifyUserId);
		});
		purgeOffenderRepository.deleteprOffenderTransactions1(txnId, txnEntrySeq,modifyUserId);
	}

	@Override
	public void prOffenderTrustAccountsTmp(Long offenderId,String modifyUserId) {
		purgeOffenderRepository.deleteprOffenderTrustAccountsTmp(offenderId,modifyUserId);
	}

	@Override
	public void prOffenderVitalSigns(Long offenderId,String modifyUserId) {
		purgeOffenderRepository.deleteprOffenderVitalSigns(offenderId,modifyUserId);
	}

	@Override
	public void prAddresses(BigDecimal ownerId, String ownerClass,String modifyUserId) {
		purgeOffenderRepository.prAddresses(ownerId, ownerClass).forEach(val->{
			prAddressUsages(val.getAddressId()!=null?new BigDecimal(val.getAddressId()):null,modifyUserId);
			prCourseActivities(val.getAddressId(),modifyUserId);
			prCurfewAddresses1(val.getAddressId()!=null?new BigDecimal(val.getAddressId()):null,modifyUserId);
			prOffenderExternalMovemnts1(val.getAddressId()!=null?new BigDecimal(val.getAddressId()):null,modifyUserId);
			prOffenderExternalMovemnts2(val.getAddressId()!=null?new BigDecimal(val.getAddressId()):null,modifyUserId);
			prOffenderMailLogs1(val.getAddressId()!=null?new BigDecimal(val.getAddressId()):null,modifyUserId);
			prOffenderMailRestrictions1(val.getAddressId()!=null?new BigDecimal(val.getAddressId()):null,modifyUserId);
			prPhones((val.getAddressId()!=null?new BigDecimal(val.getAddressId()):null),"ADDR",modifyUserId);
		});
		purgeOffenderRepository.deleteprAddresses(ownerId, ownerClass,modifyUserId);
	}

	@Override
	public void prAddressUsages(BigDecimal addressId,String modifyUserId) {
		purgeOffenderRepository.deleteprAddressUsages(addressId,modifyUserId);
	}

	@Override
	public void prCourseActivities(Long servicesAddressId,String modifyUserId) {
		purgeOffenderRepository.prCourseActivities(servicesAddressId).forEach(val->{
			prCourseActivities1(val.getParentCrsActyId(),modifyUserId);
			prCourseActivityAreas(val.getParentCrsActyId()!=null?new BigDecimal(val.getParentCrsActyId()):null,modifyUserId);
			prCourseActivityParties(val.getParentCrsActyId()!=null?new BigDecimal(val.getParentCrsActyId()):null,modifyUserId);
			prCourseActivityProfiles(val.getParentCrsActyId()!=null?new BigDecimal(val.getParentCrsActyId()):null,modifyUserId);
			prCourseActivityReviews(val.getParentCrsActyId()!=null?new BigDecimal(val.getParentCrsActyId()):null,modifyUserId);
			prCourseSchedules(val.getParentCrsActyId()!=null?new BigDecimal(val.getParentCrsActyId()):null);
			prCourseScheduleRules(val.getParentCrsActyId()!=null?new BigDecimal(val.getParentCrsActyId()):null,modifyUserId);
			prOffenderCourseAttendancs3(val.getParentCrsActyId()!=null?new BigDecimal(val.getParentCrsActyId()):null,modifyUserId);
			prOffenderProgramProfiles3(val.getParentCrsActyId());
		});
		purgeOffenderRepository.prCourseActivities(servicesAddressId);
	}

	@Override
	public void prCourseActivities1(Long parentCrsActyId,String modifyUserId) {
		purgeOffenderRepository.prCourseActivities1(parentCrsActyId).forEach(val->{
			prCourseActivities1(val.getParentCrsActyId(),modifyUserId);
			prCourseActivityAreas(val.getParentCrsActyId()!=null?new BigDecimal(val.getParentCrsActyId()):null,modifyUserId);
			prCourseActivityParties(val.getParentCrsActyId()!=null?new BigDecimal(val.getParentCrsActyId()):null,modifyUserId);
			prCourseActivityProfiles(val.getParentCrsActyId()!=null?new BigDecimal(val.getParentCrsActyId()):null,modifyUserId);
			prCourseActivityReviews(val.getParentCrsActyId()!=null?new BigDecimal(val.getParentCrsActyId()):null,modifyUserId);
			prCourseSchedules(val.getParentCrsActyId()!=null?new BigDecimal(val.getParentCrsActyId()):null);
			prCourseScheduleRules(val.getParentCrsActyId()!=null?new BigDecimal(val.getParentCrsActyId()):null,modifyUserId);
			prOffenderCourseAttendancs3(val.getParentCrsActyId()!=null?new BigDecimal(val.getParentCrsActyId()):null,modifyUserId);
			prOffenderProgramProfiles3(val.getParentCrsActyId()!=null?val.getParentCrsActyId().longValue():null);
		});
		purgeOffenderRepository.deleteprCourseActivities1(parentCrsActyId,modifyUserId);
	}

	@Override
	public void prCourseActivityAreas(BigDecimal crsActyId,String modifyUserId) {
		purgeOffenderRepository.deleteprCourseActivityAreas(crsActyId,modifyUserId);
	}

	@Override
	public void prCourseActivityParties(BigDecimal crsActyId,String modifyUserId) {
		purgeOffenderRepository.deleteprCourseActivityParties(crsActyId,modifyUserId);
	}

	@Override
	public void prCourseActivityProfiles(BigDecimal crsActyId,String modifyUserId) {
		purgeOffenderRepository.deleteprCourseActivityProfiles(crsActyId,modifyUserId);
	}

	@Override
	public void prCourseActivityReviews(BigDecimal crsActyId,String modifyUserId) {
		purgeOffenderRepository.deleteprCourseActivityReviews(crsActyId,modifyUserId);
	}

	@Override
	public void prCourseSchedules(BigDecimal crsActyId) {
		purgeOffenderRepository.prCourseSchedules(crsActyId);
	}

	@Override
	public void prCourseScheduleRules(BigDecimal crsActyId,String modifyUserId) {
		purgeOffenderRepository.deleteprCourseScheduleRules(crsActyId,modifyUserId);
	}

	@Override
	public void prOffenderCourseAttendancs3(BigDecimal crsActyId,String modifyUserId) {
		purgeOffenderRepository.prOffenderCourseAttendancs3(crsActyId).forEach(val->prOffenderCourseSkills(val.getEventId(),modifyUserId));
		purgeOffenderRepository.deleteprOffenderCourseAttendancs3(crsActyId,modifyUserId);
	}

	@Override
	public void prOffenderCourseSkills(Long eventId,String modifyUserId) {
		purgeOffenderRepository.deleteprOffenderCourseSkills(eventId,modifyUserId);
	}

	@Override
	public void prOffenderProgramProfiles3(Long crsActyId) {
		purgeOffenderRepository.prOffenderProgramProfiles3(crsActyId);
	}

	@Override
	public void prOffenderCourseApptGrps(BigDecimal offPrgrefId,String modifyUserId) {
		purgeOffenderRepository.prOffenderCourseApptGrps(offPrgrefId).forEach(val->prOffenderCourseApptRules(val.getOffenderCourseApptGrpId(),modifyUserId));;
		purgeOffenderRepository.deleteprOffenderCourseApptGrps(offPrgrefId,modifyUserId);
	}

	@Override
	public void prOffenderCourseApptRules(Long offenderCourseApptRuleId,String modifyUserId) {
		purgeOffenderRepository.deleteprOffenderCourseApptRules(offenderCourseApptRuleId,modifyUserId);
	}

	@Override
	public void prOffenderCourseAttendancs2(BigDecimal offPrgrefId,String modifyUserId) {
		purgeOffenderRepository.prOffenderCourseAttendancs2(offPrgrefId).forEach(val->prOffenderCourseSkills(val.getEventId(),modifyUserId));
		purgeOffenderRepository.deleteprOffenderCourseAttendancs2(offPrgrefId,modifyUserId);
	}

	@Override
	public void prOffenderProgramProfiles2(Long offPrgrefId,String modifyUserId) {
		purgeOffenderRepository.prOffenderProgramProfiles2(offPrgrefId).forEach(val->{
			prOffenderCourseApptGrps(val.getOffPrgrefId()!=null?new BigDecimal(val.getOffPrgrefId()):null,modifyUserId);
			prOffenderCourseAttendancs2(val.getOffPrgrefId()!=null?new BigDecimal(val.getOffPrgrefId()):null,modifyUserId);
			prOffenderProgramProfiles2(val.getOffPrgrefId(),modifyUserId);
		});
		purgeOffenderRepository.prOffenderProgramProfiles2(offPrgrefId);
	}

	@Override
	public void prCurfewAddresses1(BigDecimal addressId,String modifyUserId) {
		purgeOffenderRepository.prCurfewAddresses1(addressId).forEach(val->prCurfewAddressOccupants(val.getCurfewAddressId(),modifyUserId));
	}

	@Override
	public void prCurfewAddressOccupants(BigDecimal pCurfewAddressId,String modifyUserId) {
		purgeOffenderRepository.deleteprCurfewAddressOccupants(pCurfewAddressId,modifyUserId);
	}

	@Override
	public void prOffenderExternalMovemnts1(BigDecimal toAddressId,String modifyUserId) {
		purgeOffenderRepository.deleteprOffenderExternalMovemnts1(toAddressId,modifyUserId);
	}

	@Override
	public void prOffenderExternalMovemnts2(BigDecimal fromAddressId,String modifyUserId) {
		purgeOffenderRepository.deleteprOffenderExternalMovemnts2(fromAddressId,modifyUserId);
	}

	@Override
	public void prOffenderMailLogs1(BigDecimal pMailAddressId,String modifyUserId) {
		purgeOffenderRepository.deleteprOffenderMailLogs1(pMailAddressId,modifyUserId);
	}

	@Override
	public void prOffenderMailRestrictions1(BigDecimal pRestrictionAddressId,String modifyUserId) {
		purgeOffenderRepository.deleteprOffenderMailRestrictions1(pRestrictionAddressId,modifyUserId);
	}

	@Override
	public void prPhones(BigDecimal ownerId, String ownerClass,String modifyUserId) {
		purgeOffenderRepository.deleteprPhones(ownerId, ownerClass,modifyUserId);
	}

	@Override
	public void prOffenderNonAssociations(Long offenderId,String modifyUserId) {
		purgeOffenderRepository.prOffenderNonAssociations(offenderId).forEach(bean->prOffenderNaDetails(bean.getOffenderId(), bean.getNsOffenderId(),modifyUserId));
		purgeOffenderRepository.deleteprOffenderNonAssociations(offenderId,modifyUserId);
	}

	@Override
	public void prOffenderNaDetails(Long offenderId, Long nsOffenderId,String modifyUserId) {
		purgeOffenderRepository.deleteprOffenderNaDetails(offenderId, nsOffenderId,modifyUserId);
	}

	@Override
	public void prOffenderNonAssociations1(Long nsOffenderId,String modifyUserId) {
		purgeOffenderRepository.prOffenderNonAssociations1(nsOffenderId).forEach(bean->prOffenderNaDetails(bean.getOffenderId(),bean.getNsOffenderId(),modifyUserId));;
		purgeOffenderRepository.deleteprOffenderNonAssociations1(nsOffenderId,modifyUserId);
	}

	@Override
	public void prOffenderContactPersons(BigDecimal contactRootOffenderId,String modifyUserId) {
		purgeOffenderRepository.prOffenderContactPersons(contactRootOffenderId).forEach(val->prOffenderPersonRestricts((int)val.getOffenderContactPersonId(),modifyUserId));;
		purgeOffenderRepository.deleteprOffenderContactPersons(contactRootOffenderId,modifyUserId);
	}

	@Override
	public void prOffenderPersonRestricts(Integer offenderContactPersonId,String modifyUserId) {
		purgeOffenderRepository.deleteprOffenderPersonRestricts(offenderContactPersonId,modifyUserId);
	}

	@Override
	public void prOffenderTrustAccounts(Long offenderId,String modifyUserId) {
		purgeOffenderRepository.prOffenderTrustAccounts(offenderId).forEach(bean->{
			prOffenderDeductions2(bean.getCaseloadId(),bean.getOffenderId());
			prOffenderForeignCurrencies(bean.getCaseloadId(),bean.getOffenderId(),modifyUserId);
			prOffenderSubAccounts(bean.getCaseloadId(),bean.getOffenderId(),modifyUserId);
		});
		purgeOffenderRepository.deleteprOffenderTrustAccountsTmp(offenderId,modifyUserId);
	}

	@Override
	public void prOffenderDeductions2(String caseloadId, Long offenderId) {
		purgeOffenderRepository.prOffenderDeductions2(caseloadId, offenderId);
	}

	@Override
	public void prOffenderForeignCurrencies(String caseloadId, Long offenderId,String modifyUserId) {
		purgeOffenderRepository.deleteprOffenderForeignCurrencies(caseloadId, offenderId,modifyUserId);
	}

	@Override
	public void prOffenderSubAccounts(String caseloadId, Long offenderId,String modifyUserId) {
		purgeOffenderRepository.deleteprOffenderSubAccounts(caseloadId, offenderId,modifyUserId);
	}

	@Override
	public void prArrests(Long offenderBookId,String modifyUserId) {
		purgeOffenderRepository.deleteprArrests(offenderBookId,modifyUserId);
	}

	@Override
	public void prBedAssignmentHistories(Integer offenderBookId,String modifyUserId) {
		purgeOffenderRepository.deleteprBedAssignmentHistories(offenderBookId,modifyUserId);
	}

	@Override
	public void prCaseAssociatedPersons(Long offenderBookId,String modifyUserId) {
		purgeOffenderRepository.deleteprCaseAssociatedPersons(offenderBookId,modifyUserId);
	}

	@Override
	public void prCaseNotes(Long offenderBookId,String modifyUserId) {
		purgeOffenderRepository.deleteprCaseNotes(offenderBookId,modifyUserId);
	}

	@Override
	public void prAssessmentSummaries(Long offenderBookId,Long casePlanId,String modifyUserId) {
		purgeOffenderRepository.deleteprAssessmentSummaries(offenderBookId,casePlanId,modifyUserId);
	}

	@Override
	public void prCasePlans(Long offenderBookId,String modifyUserId) {
		purgeOffenderRepository.prCasePlans(offenderBookId).forEach(bean->{
			prAssessmentSummaries(bean.getOffenderBookId(),bean.getCasePlanId(),modifyUserId);
			prOffenderCaseConditions(bean.getOffenderBookId()!=null?new BigDecimal(bean.getOffenderBookId()):null,bean.getCasePlanId()!=null?new BigDecimal(bean.getCasePlanId()):null,modifyUserId);
			prOffenderCriminogenicNeeds(bean.getOffenderBookId()!=null?new BigDecimal(bean.getOffenderBookId()):null,bean.getCasePlanId()!=null?new BigDecimal(bean.getCasePlanId()):null,modifyUserId);
			prPlanDetails(bean.getOffenderBookId(),bean.getCasePlanId(),modifyUserId);
		});
		purgeOffenderRepository.prCasePlans(offenderBookId);
	}

	@Override
	public void prOffenderCaseConditions(BigDecimal offenderBookId, BigDecimal casePlanId,String modifyUserId) {
		purgeOffenderRepository.prOffenderCaseConditions(offenderBookId, casePlanId).forEach(val->prOffenderActionPlans(val,modifyUserId));
		purgeOffenderRepository.deleteprOffenderCaseConditions(offenderBookId, casePlanId,modifyUserId);
	}

	@Override
	public void prOffenderActionPlans(BigDecimal offCaseCondId,String modifyUserId) {
		purgeOffenderRepository.deleteprOffenderActionPlans(offCaseCondId,modifyUserId);
	}

	@Override
	public void prOffenderCriminogenicNeeds(BigDecimal offenderBookId, BigDecimal casePlanId,String modifyUserId) {
		purgeOffenderRepository.prOffenderCriminogenicNeeds(offenderBookId, casePlanId).forEach(val->prOffenderActionPlans1(new BigDecimal(val.getOffCrimNeedId()),modifyUserId));
		purgeOffenderRepository.deleteprOffenderCriminogenicNeeds(offenderBookId, casePlanId,modifyUserId);
	}

	@Override
	public void prOffenderActionPlans1(BigDecimal offCrimNeedId,String modifyUserId) {
		purgeOffenderRepository.deleteprOffenderActionPlans1(offCrimNeedId,modifyUserId);
	}

	@Override
	public void prPlanDetails(Long offenderBookId, Long casePlanId,String modifyUserId) {
		purgeOffenderRepository.prPlanDetails(offenderBookId, casePlanId).forEach(bean->prCaseworkSteps(bean.getOffenderBookId(), bean.getCasePlanId(), bean.getDetailSeq(),modifyUserId));
		purgeOffenderRepository.prPlanDetails(offenderBookId, casePlanId);
	}

	@Override
	public void prCaseworkSteps(Long offenderBookId, Long casePlanId,Long detailSeq,String modifyUserId) {
		purgeOffenderRepository.deleteprCaseworkSteps(offenderBookId, casePlanId, detailSeq,modifyUserId);
	}

	@Override
	public void prCourtEvents(Integer offenderBookId,String modifyUserId) {
		purgeOffenderRepository.prCourtEvents(offenderBookId).forEach(val->{
			prCourtEventCharges(new BigDecimal(val.getEventId()),modifyUserId);
			prCourtEventSentences(new BigDecimal(val.getEventId()),modifyUserId);
			prLinkCaseTxns(new BigDecimal(val.getEventId()),modifyUserId);
		});
		purgeOffenderRepository.prCourtEvents(offenderBookId);
	}

	@Override
	public void prCourtEventCharges(BigDecimal eventId,String modifyUserId) {
		purgeOffenderRepository.deleteprCourtEventCharges(eventId,modifyUserId);
	}

	@Override
	public void prCourtEventSentences(BigDecimal eventId,String modifyUserId) {
		purgeOffenderRepository.deleteprCourtEventSentences(eventId,modifyUserId);
	}

	@Override
	public void prLinkCaseTxns(BigDecimal eventId,String modifyUserId) {
		purgeOffenderRepository.deleteprLinkCaseTxns(eventId,modifyUserId);
	}

	@Override
	public void prDocumentTemplateQueues(BigDecimal offenderBookId,String modifyUserId) {
		purgeOffenderRepository.deleteprDocumentTemplateQueues(offenderBookId,modifyUserId);
	}

	@Override
	public void prGlTransactions1(BigDecimal offenderBookId,String modifyUserId) {
		purgeOffenderRepository.updateprGlTransactions1(offenderBookId,modifyUserId);
	}

	@Override
	public void prHdcRequestReferrals(Long offenderBookId,String modifyUserId) {
		purgeOffenderRepository.prHdcRequestReferrals(offenderBookId).forEach(val->{
			prHdcBoardDecisions(val.getHdcRequestReferralId(),modifyUserId);
			prHdcGovernorDecisions(val.getHdcRequestReferralId(),modifyUserId);
			prHdcProbStaffComments(val.getHdcRequestReferralId(),modifyUserId);
			prHdcProbStaffResponses(val.getHdcRequestReferralId(),modifyUserId);
			prHdcRequestReferrals1(val.getHdcRequestReferralId(),modifyUserId);
		});;
		purgeOffenderRepository.deleteprHdcRequestReferrals(offenderBookId,modifyUserId);
	}

	@Override
	public void prHdcBoardDecisions(Long hdcRequestReferralId,String modifyUserId) {
		purgeOffenderRepository.deleteprHdcBoardDecisions(hdcRequestReferralId,modifyUserId);
	}

	@Override
	public void prHdcGovernorDecisions(Long hdcRequestReferralId,String modifyUserId) {
		purgeOffenderRepository.deleteprHdcGovernorDecisions(hdcRequestReferralId,modifyUserId);
	}

	@Override
	public void prHdcProbStaffComments(Long hdcRequestReferralId,String modifyUserId) {
		purgeOffenderRepository.deleteprHdcProbStaffComments(hdcRequestReferralId,modifyUserId);
	}

	@Override
	public void prHdcProbStaffResponses(Long hdcRequestReferralId,String modifyUserId) {
		purgeOffenderRepository.deleteprHdcProbStaffResponses(hdcRequestReferralId,modifyUserId);
	}

	@Override
	public void prHdcRequestReferrals1(Long parentHdcRequestReferralId,String modifyUserId) {
		purgeOffenderRepository.prHdcRequestReferrals1(parentHdcRequestReferralId).forEach(val->{
			prHdcBoardDecisions(val.getHdcRequestReferralId(),modifyUserId);
			prHdcGovernorDecisions(val.getHdcRequestReferralId(),modifyUserId);
			prHdcProbStaffComments(val.getHdcRequestReferralId(),modifyUserId);
			prHdcProbStaffResponses(val.getHdcRequestReferralId(),modifyUserId);
			prHdcRequestReferrals1(val.getHdcRequestReferralId(),modifyUserId);
		});
		purgeOffenderRepository.deleteprHdcRequestReferrals1(parentHdcRequestReferralId,modifyUserId);
	}

	@Override
	public void prIncidentCaseParties(Long offenderBookId,String modifyUserId) {
		purgeOffenderRepository.deleteprIncidentCaseParties(offenderBookId,modifyUserId);
	}

	@Override
	public void prIwpDocuments(BigDecimal offenderBookId,String modifyUserId) {
		purgeOffenderRepository.deleteprIwpDocuments(offenderBookId,modifyUserId);
	}

	@Override
	public void prOffenderActivityEvents(BigDecimal offenderBookId,String modifyUserId) {
		purgeOffenderRepository.deleteprOffenderActivityEvents(offenderBookId,modifyUserId);
	}

	@Override
	public void prOffenderAlerts(BigDecimal offenderBookId,String modifyUserId) {
		purgeOffenderRepository.deleteprOffenderAlerts(offenderBookId,modifyUserId);
	}

	@Override
	public void prOffenderAssessments(BigDecimal offenderBookId,String modifyUserId) {
		purgeOffenderRepository.deleteprOffenderAssessments(offenderBookId,modifyUserId);
	}

	@Override
	public void prOffenderAssets(BigDecimal offenderBookId,String modifyUserId) {
		purgeOffenderRepository.deleteprOffenderAssets(offenderBookId,modifyUserId);
	}

	@Override
	public void prOffenderAssociatedParties(Long offenderBookId,String modifyUserId) {
		purgeOffenderRepository.prOffenderAssociatedParties(offenderBookId).forEach(val->{
			prOffenderAssocPartyNotes(val.getAssociatedPartyId(),modifyUserId);
			prOffenderAssocPrtyContcts(val.getAssociatedPartyId(),modifyUserId);
			prOffenderAssocPNotifs(val.getAssociatedPartyId(),modifyUserId);
		});
		purgeOffenderRepository.deleteprOffenderAssociatedParties(offenderBookId,modifyUserId);
	}

	@Override
	public void prOffenderAssocPartyNotes(Long associatedPartyId,String modifyUserId) {
		purgeOffenderRepository.deleteprOffenderAssocPartyNotes(associatedPartyId,modifyUserId);
	}

	@Override
	public void prOffenderAssocPrtyContcts(Long associatedPartyId,String modifyUserId) {
		purgeOffenderRepository.prOffenderAssocPrtyContcts(associatedPartyId).forEach(bean->prOffenderAssocPCntNotifs(bean.getAssociatedPartyId(),bean.getPartySeq(),modifyUserId));
		purgeOffenderRepository.deleteprOffenderAssocPrtyContcts(associatedPartyId,modifyUserId);
	}

	@Override
	public void prOffenderAssocPCntNotifs(Long associatedPartyId,Long partySeq,String modifyUserId) {
		purgeOffenderRepository.deleteprOffenderAssocPCntNotifs(associatedPartyId, partySeq,modifyUserId);
	}

	@Override
	public void prOffenderAssocPNotifs(Long associatedPartyId,String modifyUserId) {
		purgeOffenderRepository.deleteprOffenderAssocPNotifs(associatedPartyId,modifyUserId);
	}

	@Override
	public void prOffenderAuthorisdVisitors(BigDecimal offenderBookId,String modifyUserId) {
		purgeOffenderRepository.deleteprOffenderAuthorisdVisitors(offenderBookId,modifyUserId);
	}

	@Override
	public void prOffenderAthorisdVisitors1(BigDecimal offenderBookId,String modifyUserId) {
		purgeOffenderRepository.deleteprOffenderAthorisdVisitors1(offenderBookId,modifyUserId);
	}

	@Override
	public void prOffenderBailDetails(Long offenderBookId,String modifyUserId) {
		purgeOffenderRepository.deleteprOffenderBailDetails(offenderBookId,modifyUserId);
	}

	@Override
	public void prOffenderBookingAgyLocs(Long offenderBookId,String modifyUserId) {
		purgeOffenderRepository.deleteprOffenderBookingAgyLocs(offenderBookId,modifyUserId);
	}

	@Override
	public void prOffenderBookingDetails(Long offenderBookId,String modifyUserId) {
		purgeOffenderRepository.deleteprOffenderBookingDetails(offenderBookId,modifyUserId);
	}

	@Override
	public void prOffenderBookingEvents(Long offenderBookId,String modifyUserId) {
		purgeOffenderRepository.deleteprOffenderBookingEvents(offenderBookId,modifyUserId);
	}

	@Override
	public void prOffenderCases(Long offenderBookId,String modifyUserId) {
		purgeOffenderRepository.prOffenderCases(offenderBookId).forEach(val->{
			prCourtEvents1(val.getCaseId()!=null?val.getCaseId().intValue():null,modifyUserId);
			prLinkCaseTxns1(val.getCaseId(),modifyUserId);
			prLinkCaseTxns2(val.getCaseId(),modifyUserId);
			prOffenderAssociatedPartis1(val.getCaseId(),modifyUserId);
			prOffenderCases1(val.getCaseId()!=null?new BigDecimal(val.getCaseId()):null);
			prOffenderCaseIdentifiers(val.getCaseId(),modifyUserId);
			prOffenderCaseStatuses(val.getCaseId(),modifyUserId);
			prOffenderCharges1(val.getCaseId(),modifyUserId);
			prOffenderLicenceSentences(val.getCaseId(),modifyUserId);
			prOffenderSentences(val.getCaseId()!=null?new BigDecimal(val.getCaseId()):null,modifyUserId);
			prOrders1(val.getCaseId(),modifyUserId);
		});
		purgeOffenderRepository.deleteprOffenderCases(offenderBookId,modifyUserId);
	}

	@Override
	public void prCourtEvents1(Integer caseId,String modifyUserId) {
		purgeOffenderRepository.prCourtEvents1(caseId).forEach(val->{
			prCourtEventCharges(new BigDecimal(val.getEventId()),modifyUserId);
			prCourtEventSentences(new BigDecimal(val.getEventId()),modifyUserId);
			prLinkCaseTxns(new BigDecimal(val.getEventId()),modifyUserId);
		});
		purgeOffenderRepository.deleteprCourtEvents1(caseId,modifyUserId);
	}

	@Override
	public void prLinkCaseTxns1(Long caseId,String modifyUserId) {
		purgeOffenderRepository.deleteprLinkCaseTxns1(caseId,modifyUserId);
	}

	@Override
	public void prLinkCaseTxns2(Long caseId,String modifyUserId) {
		purgeOffenderRepository.deleteprLinkCaseTxns2(caseId,modifyUserId);
	}

	@Override
	public void prOffenderAssociatedPartis1(Long caseId,String modifyUserId) {
		purgeOffenderRepository.prOffenderAssociatedPartis1(caseId).forEach(val->{
			prOffenderAssocPartyNotes(val.getAssociatedPartyId(),modifyUserId);
			prOffenderAssocPrtyContcts(val.getAssociatedPartyId(),modifyUserId);
			prOffenderAssocPNotifs(val.getAssociatedPartyId(),modifyUserId);
		});
		purgeOffenderRepository.deleteprOffenderAssociatedPartis1(caseId,modifyUserId);
	}

	@Override
	public void prOffenderCases1(BigDecimal combinedCaseId) {
		purgeOffenderRepository.prOffenderCases1(combinedCaseId);
	}

	@Override
	public void prOffenderCaseIdentifiers(Long caseId,String modifyUserId) {
		purgeOffenderRepository.deleteprOffenderCaseIdentifiers(caseId,modifyUserId);
	}

	@Override
	public void prOffenderCaseStatuses(Long caseId,String modifyUserId) {
		purgeOffenderRepository.deleteprOffenderCaseStatuses(caseId,modifyUserId);
	}

	@Override
	public void prOffenderCharges1(Long caseId,String modifyUserId) {
		purgeOffenderRepository.prOffenderCharges1(caseId).forEach(val->{
			prCourtEventCharges1(val.getOffenderChargeId()!=null?new BigDecimal(val.getOffenderChargeId()):null,modifyUserId);
			prLinkCaseTxns3(val.getOffenderChargeId()!=null?new BigDecimal(val.getOffenderChargeId()):null,modifyUserId);
			prOffenderSentenceCharges(val.getOffenderChargeId(),modifyUserId);
		});
		purgeOffenderRepository.deleteprOffenderCharges1(caseId,modifyUserId);
	}

	@Override
	public void prCourtEventCharges1(BigDecimal offenderChargeId,String modifyUserId) {
		purgeOffenderRepository.deleteprCourtEventCharges1(offenderChargeId,modifyUserId);
	}

	@Override
	public void prLinkCaseTxns3(BigDecimal offenderChargeId,String modifyUserId) {
		purgeOffenderRepository.deleteprLinkCaseTxns3(offenderChargeId,modifyUserId);
	}

	@Override
	public void prOffenderSentenceCharges(Long offenderChargeId,String modifyUserId) {
		purgeOffenderRepository.deleteprOffenderSentenceCharges(offenderChargeId,modifyUserId);
	}

	@Override
	public void prOffenderLicenceSentences(Long caseId,String modifyUserId) {
		purgeOffenderRepository.deleteprOffenderLicenceSentences(caseId,modifyUserId);
	}

	@Override
	public void prOffenderSentences(BigDecimal caseId,String modifyUserId) {
		purgeOffenderRepository.prOffenderSentences(caseId).forEach(bean->{
			prCourtEventSentences1(bean.getOffenderBookId(),bean.getSentenceSeq(),modifyUserId);
			prOffenderCaseNoteSents(bean.getOffenderBookId(),bean.getSentenceSeq(),modifyUserId);
			prOffndrCommnityConditions1(bean.getOffenderBookId(),bean.getSentenceSeq(),modifyUserId);
			prOffenderIndSchSents(bean.getOffenderBookId(),bean.getSentenceSeq(),modifyUserId);
			prOffenderLicenceSentences1(bean.getOffenderBookId(),bean.getSentenceSeq(),modifyUserId);
			prOffenderLicenceSentences2(bean.getOffenderBookId(),bean.getSentenceSeq(),modifyUserId);
			prOffenderProceedingSents(bean.getOffenderBookId(),bean.getSentenceSeq(),modifyUserId);
			prOffenderSentenceAdjusts(bean.getOffenderBookId(),bean.getSentenceSeq(),modifyUserId);
			prOffenderSentenceCharges1(bean.getOffenderBookId(),bean.getSentenceSeq(),modifyUserId);
			prOffenderSentenceHty(bean.getOffenderBookId(),bean.getSentenceSeq(),modifyUserId);
			prOffenderSentenceStatuses(bean.getOffenderBookId(),bean.getSentenceSeq(),modifyUserId);
			prOffenderSentenceTerms(bean.getOffenderBookId(),bean.getSentenceSeq(),modifyUserId);
			prOffenderSentenceUaEvents(bean.getOffenderBookId(),bean.getSentenceSeq(),modifyUserId);
			prOffenderUnpaidWorkAdj(bean.getOffenderBookId(),bean.getSentenceSeq(),modifyUserId);
		});
		purgeOffenderRepository.deleteprOffenderSentences(caseId,modifyUserId);
	}

	@Override
	public void prOrders1(Long caseId,String modifyUserId) {
		purgeOffenderRepository.prOrders1(caseId).forEach(val->{
			prOffenderSentences1(val.getOrderId(),modifyUserId);
			prOrderPurposes(val.getOrderId()!=null?val.getOrderId().longValue():null,modifyUserId);
		});
		purgeOffenderRepository.deleteprOrders1(caseId,modifyUserId);
	}

	@Override
	public void prOrderPurposes(Long orderId,String modifyUserId) {
		purgeOffenderRepository.deleteprOrderPurposes(orderId,modifyUserId);
	}

	@Override
	public void prOffenderCaseNotes(Integer offenderBookId,String modifyUserId) {
		purgeOffenderRepository.prOffenderCaseNotes(offenderBookId).forEach(val->{
			prOffenderCaseNoteSents1(val.getCaseNoteId(),modifyUserId);
			prOffCaseNoteRecipients(val.getCaseNoteId(),modifyUserId);
		});
		purgeOffenderRepository.deleteprOffenderCaseNotes(offenderBookId,modifyUserId);
	}

	@Override
	public void prOffenderCaseNoteSents1(Integer caseNoteId,String modifyUserId) {
		purgeOffenderRepository.deleteprOffenderCaseNoteSents1(caseNoteId,modifyUserId);
	}

	@Override
	public void prOffCaseNoteRecipients(Integer caseNoteId,String modifyUserId) {
		purgeOffenderRepository.deleteprOffCaseNoteRecipients(caseNoteId,modifyUserId);
	}

	@Override
	public void prOffenderCharges(Long offenderBookId,String modifyUserId) {
		purgeOffenderRepository.prOffenderCharges(offenderBookId).forEach(val->{
			prCourtEventCharges1(val.getOffenderChargeId()!=null?new BigDecimal(val.getOffenderChargeId()):null,modifyUserId);
			prLinkCaseTxns3(val.getOffenderChargeId()!=null?new BigDecimal(val.getOffenderChargeId()):null,modifyUserId);
			prOffenderSentenceCharges(val.getOffenderChargeId(),modifyUserId);
		});
		purgeOffenderRepository.deleteprOffenderCharges(offenderBookId,modifyUserId);
	}

	@Override
	public void prOffenderCipDetails(Long offenderBookId,String modifyUserId) {
		purgeOffenderRepository.deleteprOffenderCipDetails(offenderBookId,modifyUserId);
	}

	@Override
	public void prOffenderClassPrograms(Long offenderBookId,String modifyUserId) {
		purgeOffenderRepository.deleteprOffenderClassPrograms(offenderBookId,modifyUserId);
	}

	@Override
	public void prOffenderClothes(Long offenderBookId,String modifyUserId) {
		purgeOffenderRepository.deleteprOffenderClothes(offenderBookId,modifyUserId);
	}

	@Override
	public void prOffenderClothingBundles(Long offenderBookId,String modifyUserId) {
		purgeOffenderRepository.deleteprOffenderClothingBundles(offenderBookId,modifyUserId);
	}

	@Override
	public void prOffenderClothingIssue(Long offenderBookId,String modifyUserId) {
		purgeOffenderRepository.prOffenderClothingIssue(offenderBookId).forEach(val->prOffenderClothingItems1(offenderBookId,modifyUserId));
		purgeOffenderRepository.deleteprOffenderClothingIssue(offenderBookId,modifyUserId);
	}

	@Override
	public void prOffenderClothingItems1(Long offenderClothingIssueId,String modifyUserId) {
		purgeOffenderRepository.deleteprOffenderClothingItems1(offenderClothingIssueId,modifyUserId);
	}

	@Override
	public void prOffenderClothingItems(Long offenderBookId,String modifyUserId) {
		purgeOffenderRepository.deleteprOffenderClothingItems(offenderBookId,modifyUserId);
	}

	@Override
	public void prOffenderCodefendants(Long codOffenderBookId,String modifyUserId) {
		purgeOffenderRepository.deleteprOffenderCodefendants(codOffenderBookId,modifyUserId);
	}

	@Override
	public void prOffenderCodefendants1(Long offenderBookId,String modifyUserId) {
		purgeOffenderRepository.deleteprOffenderCodefendants1(offenderBookId,modifyUserId);
	}

	@Override
	public void prOffenderComments(Long offenderBookId,String modifyUserId) {
		purgeOffenderRepository.deleteprOffenderComments(offenderBookId,modifyUserId);
	}
	@Override
	public void prOffendrCommnityConditions(Long offenderBookId,String modifyUserId) {
		purgeOffenderRepository.deleteprOffendrCommnityConditions(offenderBookId,modifyUserId);
	}

	@Override
	public void prOffenderCommSupHistories(Long offenderBookId,String modifyUserId) {
		purgeOffenderRepository.deleteprOffenderCommSupHistories(offenderBookId,modifyUserId);
	}

	@Override
	public void prOffenderCompactAgreements(Long offenderBookId,String modifyUserId) {
		purgeOffenderRepository.deleteprOffenderCompactAgreements(offenderBookId,modifyUserId);
	}

	@Override
	public void prOffenderContactPersons1(BigDecimal offenderBookId,String modifyUserId) {
		purgeOffenderRepository.prOffenderContactPersons1(offenderBookId).forEach(val->prOffenderPersonRestricts((int)val.getOffenderContactPersonId(),modifyUserId));
		purgeOffenderRepository.deleteprOffenderContactPersons1(offenderBookId,modifyUserId);
	}

	@Override
	public void prOffenderCourseAttendances(Long offenderBookId,String modifyUserId) {
		purgeOffenderRepository.prOffenderCourseAttendances(offenderBookId).forEach(val->prOffenderCourseSkills(val.getEventId(),modifyUserId));
		purgeOffenderRepository.deleteprOffenderCourseAttendances(offenderBookId,modifyUserId);
	}


	@Override
	public void prOffenderCurfews(Long offenderBookId,String modifyUserId) {
		purgeOffenderRepository.prOffenderCurfews(offenderBookId).forEach(val->{
			prCurfewAddresses(new BigDecimal(val.getOffenderCurfewId()),modifyUserId);
			prHdcPrisonStaffComments(new BigDecimal(val.getOffenderCurfewId()),modifyUserId);
			prHdcRequestReferrals2(new BigDecimal(val.getOffenderCurfewId()),modifyUserId);
		});
			purgeOffenderRepository.deleteprOffenderCurfews(offenderBookId,modifyUserId);
	}

	@Override
	public void prCurfewAddresses(BigDecimal offenderCurfewId,String modifyUserId) {
		purgeOffenderRepository.prCurfewAddresses(offenderCurfewId).forEach(val->prCurfewAddressOccupants(val.getCurfewAddressId(),modifyUserId));
		purgeOffenderRepository.deleteprCurfewAddresses(offenderCurfewId,modifyUserId);
	}

	@Override
	public void prHdcPrisonStaffComments(BigDecimal offenderCurfewId,String modifyUserId) {
		purgeOffenderRepository.deleteprHdcPrisonStaffComments(offenderCurfewId,modifyUserId);
	}

	@Override
	public void prHdcRequestReferrals2(BigDecimal offenderCurfewId,String modifyUserId) {
		purgeOffenderRepository.prHdcRequestReferrals2(offenderCurfewId).forEach(val->{
			prHdcBoardDecisions(val.getHdcRequestReferralId(),modifyUserId);
			prHdcGovernorDecisions(val.getHdcRequestReferralId(),modifyUserId);
			prHdcProbStaffComments(val.getHdcRequestReferralId(),modifyUserId);
			prHdcProbStaffResponses(val.getHdcRequestReferralId(),modifyUserId);
			prHdcRequestReferrals1(val.getHdcRequestReferralId(),modifyUserId);
		});
		purgeOffenderRepository.deleteprHdcRequestReferrals2(offenderCurfewId,modifyUserId);
	}


	@Override
	public void prOffenderDrugAdmissions(Long offenderBookId,String modifyUserId) {
		purgeOffenderRepository.prOffenderDrugAdmissions(offenderBookId).forEach(val->prOffenderDrugAdmDrugs(val.getOffenderDrugAdmissionId(),modifyUserId));
		purgeOffenderRepository.deleteprOffenderDrugAdmissions(offenderBookId,modifyUserId);
	}

	@Override
	public void prOffenderDrugAdmDrugs(Long offenderDrugAdmissionId,String modifyUserId) {
		purgeOffenderRepository.deleteprOffenderDrugAdmDrugs(offenderDrugAdmissionId,modifyUserId);
	}

	@Override
	public void prOffenderDrugSamples(Long offenderBookId,String modifyUserId) {
		purgeOffenderRepository.prOffenderDrugSamples(offenderBookId).forEach(bean->prOffenderDrugResults(bean.getOffenderDrugSampleId(),modifyUserId));
		purgeOffenderRepository.deleteprOffenderDrugSamples(offenderBookId,modifyUserId);
	}

	@Override
	public void prOffenderDrugResults(Long offenderDrugSampleId,String modifyUserId) {
		purgeOffenderRepository.deleteprOffenderDrugResults(offenderDrugSampleId,modifyUserId);
	}

	@Override
	public void prOffenderEducations(Long offenderBookId,String modifyUserId) {
		purgeOffenderRepository.deleteprOffenderEducations(offenderBookId,modifyUserId);
	}

	@Override
	public void prOffenderEmployments(Long offenderBookId,String modifyUserId) {
		purgeOffenderRepository.deleteprOffenderEmployments(offenderBookId,modifyUserId);
	}

	@Override
	public void prOffenderEscapes(Integer offenderBookId,String modifyUserId) {
		purgeOffenderRepository.deleteprOffenderEscapes(offenderBookId,modifyUserId);
	}

	@Override
	public void prOffenderExpenses(Long offenderBookId,String modifyUserId) {
		purgeOffenderRepository.deleteprOffenderExpenses(offenderBookId,modifyUserId);
	}

	@Override
	public void prOffenderExternalMovements(Long offenderBookId,String modifyUserId) {
		purgeOffenderRepository.deleteprOffenderExternalMovements(offenderBookId,modifyUserId);
	}

	@Override
	public void prOffenderFileLocations(Long offenderBookId,String modifyUserId) {
		purgeOffenderRepository.deleteprOffenderFileLocations(offenderBookId,modifyUserId);
	}

	@Override
	public void prOffenderGangAffiliations(Long offenderBookId,String modifyUserId) {
		purgeOffenderRepository.deleteprOffenderGangAffiliations(offenderBookId,modifyUserId);
	}

	@Override
	public void prOffenderGangEvidences(Long offenderBookId,String modifyUserId) {
		purgeOffenderRepository.deleteprOffenderGangEvidences(offenderBookId,modifyUserId);
	}

	@Override
	public void prOffenderGangInvests(Long offenderBookId,String modifyUserId) {
		purgeOffenderRepository.deleteprOffenderGangInvests(offenderBookId,modifyUserId);
	}

	@Override
	public void prOffenderGrievances(Long offenderBookId,String modifyUserId) {
		purgeOffenderRepository.prOffenderGrievances(offenderBookId).forEach(val->{
			prOffenderGrievanceTxns(val.getGrievanceId(),modifyUserId);
			prOffenderGrievStaffs(val.getGrievanceId(),modifyUserId);
		});
		purgeOffenderRepository.deleteprOffenderGrievances(offenderBookId,modifyUserId);
	}

	@Override
	public void prOffenderGrievanceTxns(Long grievanceId,String modifyUserId) {
		purgeOffenderRepository.deleteprOffenderGrievanceTxns(grievanceId,modifyUserId);
	}

	@Override
	public void prOffenderGrievStaffs(Long grievanceId,String modifyUserId) {
		purgeOffenderRepository.deleteprOffenderGrievStaffs(grievanceId,modifyUserId);
	}

	@Override
	public void prOffenderHospitalVisits(Long offenderBookId,String modifyUserId) {
		purgeOffenderRepository.deleteprOffenderHospitalVisits(offenderBookId,modifyUserId);
	}

	@Override
	public void prOffenderHwd(Long offenderBookId,String modifyUserId) {
		purgeOffenderRepository.prOffenderHwd(offenderBookId).forEach(val->{
			prOffenderHwdCharges(val.getHwdId(),modifyUserId);
			prOffenderHwdHty(val.getHwdId(),modifyUserId);
		});
		purgeOffenderRepository.deleteprOffenderHwd(offenderBookId,modifyUserId);
	}

	@Override
	public void prOffenderHwdCharges(Long hwdId,String modifyUserId) {
		purgeOffenderRepository.deleteprOffenderHwdCharges(hwdId,modifyUserId);
	}
	@Override
	public void prOffenderHwdHty(Long hwdId,String modifyUserId) {
		purgeOffenderRepository.deleteprOffenderHwdHty(hwdId,modifyUserId);
	}

	@Override
	public void prOffenderIdentifyingMarks(Long offenderBookId,String modifyUserId) {
		purgeOffenderRepository.deleteprOffenderIdentifyingMarks(offenderBookId,modifyUserId);
	}

	@Override
	public void prOffenderIepLevels(Long offenderBookId,String modifyUserId) {
		purgeOffenderRepository.deleteprOffenderIepLevels(offenderBookId,modifyUserId);
	}

	@Override
	public void prOffenderImages(Long offenderBookId,String modifyUserId) {
		purgeOffenderRepository.deleteprOffenderImages(offenderBookId,modifyUserId);
	}

	@Override
	public void prOffenderImprisonStatuses(Long offenderBookId,String modifyUserId) {
		purgeOffenderRepository.deleteprOffenderImprisonStatuses(offenderBookId,modifyUserId);
	}

	@Override
	public void prOffenderIncome(Long offenderBookId,String modifyUserId) {
		purgeOffenderRepository.deleteprOffenderIncome(offenderBookId,modifyUserId);
	}

	@Override
	public void prOffenderIndSchedules(Long offenderBookId,String modifyUserId) {
		purgeOffenderRepository.prOffenderIndSchedules(offenderBookId).forEach(val->prOffenderIndSchSents1(val.getEventId()!=null?val.getEventId().longValue():null,modifyUserId));
		purgeOffenderRepository.deleteprOffenderIndSchedules(offenderBookId,modifyUserId);
	}

	@Override
	public void prOffenderIndSchSents1(Long eventId,String modifyUserId) {
		purgeOffenderRepository.deleteprOffenderIndSchSents1(eventId,modifyUserId);
	}

	@Override
	public void prOffenderInternalMovements(Long offenderBookId,String modifyUserId) {
		purgeOffenderRepository.prOffenderInternalMovements(offenderBookId).forEach(bean->prOffenderIntMovPersons(bean.getOffenderBookId(),bean.getMovementSeq(),modifyUserId));
		purgeOffenderRepository.deleteprOffenderInternalMovements(offenderBookId,modifyUserId);
	}

	@Override
	public void prOffenderIntMovPersons(Long offenderBookId, Long movementSeq,String modifyUserId) {
		purgeOffenderRepository.deleteprOffenderIntMovPersons(offenderBookId, movementSeq,modifyUserId);
	}

	@Override
	public void prOffenderInternalStatuses(Integer offenderBookId,String modifyUserId) {
		purgeOffenderRepository.deleteprOffenderInternalStatuses(offenderBookId,modifyUserId);
	}

	@Override
	public void prOffenderInterventionTiers(Long offenderBookId,String modifyUserId) {
		purgeOffenderRepository.deleteprOffenderInterventionTiers(offenderBookId,modifyUserId);
	}

	@Override
	public void prOffenderLanguages(Long offenderBookId,String modifyUserId) {
		purgeOffenderRepository.deleteprOffenderLanguages(offenderBookId,modifyUserId);
	}

	@Override
	public void prOffenderLiabilities(Long offenderBookId,String modifyUserId) {
		purgeOffenderRepository.deleteprOffenderLiabilities(offenderBookId,modifyUserId);
	}

	@Override
	public void prOffenderMailLogs(Long offenderBookId,String modifyUserId) {
		purgeOffenderRepository.deleteprOffenderMailLogs(offenderBookId,modifyUserId);
	}

	@Override
	public void prOffenderMailRestrictions(Long offenderBookId,String modifyUserId) {
		purgeOffenderRepository.deleteprOffenderMailRestrictions(offenderBookId,modifyUserId);
	}

	@Override
	public void prOffendrMilitryDiscCtions(Long offenderBookId,String modifyUserId) {
		purgeOffenderRepository.deleteprOffendrMilitryDiscCtions(offenderBookId,modifyUserId);
	}

	@Override
	public void prOffenderMilitaryRecords(Long offenderBookId,String modifyUserId) {
		purgeOffenderRepository.deleteprOffenderMilitaryRecords(offenderBookId,modifyUserId);
	}

	@Override
	public void prOffenderMilitaryWarZones(Long offenderBookId,String modifyUserId) {
		purgeOffenderRepository.deleteprOffenderMilitaryWarZones(offenderBookId,modifyUserId);
	}

	@Override
	public void prOffenderOasysPlans(Long offenderBookId,String modifyUserId) {
		purgeOffenderRepository.prOffenderOasysPlans(offenderBookId).forEach(bean->{
			prOffenderOasysConcerns(bean.getOffenderBookId()!=null?bean.getOffenderBookId().longValue():null,bean.getPlanSeq(),modifyUserId);
			prOffenderOsysRiskToOthrs(bean.getOffenderBookId()!=null?bean.getOffenderBookId().longValue():null,bean.getPlanSeq(),modifyUserId);
			prOffenderOasysSections(bean.getOffenderBookId()!=null?bean.getOffenderBookId().longValue():null,bean.getPlanSeq(),modifyUserId);
		});
		purgeOffenderRepository.deleteprOffenderOasysPlans(offenderBookId,modifyUserId);
	}

	@Override
	public void prOffenderOasysConcerns(Long offenderBookId, Long planSeq,String modifyUserId) {
		purgeOffenderRepository.deleteprOffenderOasysConcerns(offenderBookId, planSeq,modifyUserId);
	}

	@Override
	public void prOffenderOsysRiskToOthrs(Long offenderBookId, Long planSeq,String modifyUserId) {
		purgeOffenderRepository.deleteprOffenderOsysRiskToOthrs(offenderBookId, planSeq,modifyUserId);
	}

	@Override
	public void prOffenderOasysSections(Long offenderBookId, Long planSeq,String modifyUserId) {
		purgeOffenderRepository.prOffenderOasysSections(offenderBookId, planSeq).forEach(bean->prOffenderOasysObjectives(bean.getOffenderBookId(),bean.getPlanSeq()!=null?bean.getPlanSeq().longValue():null,bean.getSectionCode(),modifyUserId));
		purgeOffenderRepository.deleteprOffenderOasysSections(offenderBookId, planSeq,modifyUserId);
	}

	@Override
	public void prOffenderOasysObjectives(Long offenderBookId, Long planSeq, String sectionCode,String modifyUserId) {
		purgeOffenderRepository.prOffenderOasysObjectives(offenderBookId, planSeq, sectionCode).forEach(val->prOffenderOasysActions(val.getObjectiveId(),modifyUserId));
		purgeOffenderRepository.deleteprOffenderOasysObjectives(offenderBookId, planSeq, sectionCode,modifyUserId);
	}

	@Override
	public void prOffenderOasysActions(BigDecimal objectiveId,String modifyUserId) {
		purgeOffenderRepository.prOffenderOasysActions(objectiveId).forEach(bean->prOffenderOasysSupervisions(bean.getObjectiveId(),bean.getActionSeq(),modifyUserId));
		purgeOffenderRepository.deleteprOffenderOasysActions(objectiveId,modifyUserId);
	}

	@Override
	public void prOffenderOasysSupervisions(BigDecimal objectiveId, Long actionSeq,String modifyUserId) {
		purgeOffenderRepository.deleteprOffenderOasysSupervisions(objectiveId, actionSeq,modifyUserId);
	}

	@Override
	public void prOffenderOicSanctions(Long offenderBookId,String modifyUserId) {
		purgeOffenderRepository.prOffenderOicSanctions(offenderBookId).forEach(bean->{
			prOffenderOicSanctions2(bean.getOffenderBookId(),bean.getSanctionSeq(),modifyUserId);
			prOffenderOicSancReviews(bean.getOffenderBookId()!=null?new BigDecimal(bean.getOffenderBookId()):BigDecimal.ZERO,bean.getSanctionSeq()!=null?bean.getSanctionSeq().longValue():null,modifyUserId);
		});
		purgeOffenderRepository.deleteprOffenderOicSanctions(offenderBookId,modifyUserId);
	}

	@Override
	public void prOffenderParoleHearings(Long offenderBookId,String modifyUserId) {
		purgeOffenderRepository.prOffenderParoleHearings(offenderBookId).forEach(bean->prOffenderParoleDecisions(bean.getOffenderBookId()!=null?bean.getOffenderBookId().longValue():null,bean.getParoleHearingSeq(),modifyUserId));
		purgeOffenderRepository.deleteprOffenderParoleHearings(offenderBookId,modifyUserId);
	}

	@Override
	public void prOffenderParoleDecisions(Long offenderBookId, Long paroleHearingSeq,String modifyUserId) {
		purgeOffenderRepository.prOffenderParoleDecisions(offenderBookId, paroleHearingSeq).forEach(bean->{
			prOffenderParoleConditions(bean.getScheduleId(),bean.getParoleDecisionSeq(),modifyUserId);
			prOffenderParoleStipultions(bean.getScheduleId(),bean.getParoleDecisionSeq(),modifyUserId);
		});
		purgeOffenderRepository.deleteprOffenderParoleDecisions(offenderBookId, paroleHearingSeq,modifyUserId);
	}

	@Override
	public void prOffenderParoleConditions(Long scheduleId, Integer paroleDecisionSeq,String modifyUserId) {
		purgeOffenderRepository.deleteprOffenderParoleConditions(scheduleId, paroleDecisionSeq,modifyUserId);
	}

	@Override
	public void prOffenderParoleStipultions(Long scheduleId, Integer paroleDecisionSeq,String modifyUserId) {
		purgeOffenderRepository.deleteprOffenderParoleStipultions(scheduleId, paroleDecisionSeq,modifyUserId);
	}

	@Override
	public void prOffenderParolePlans(Long offenderBookId,String modifyUserId) {
		purgeOffenderRepository.deleteprOffenderParolePlans(offenderBookId,modifyUserId);
	}

	@Override
	public void prOffenderPlanedActivities(Long offenderBookId,String modifyUserId) {
		purgeOffenderRepository.deleteprOffenderPlanedActivities(offenderBookId,modifyUserId);
	}

	@Override
	public void prOffenderPptyContainers(Long offenderBookId,String modifyUserId) {
		purgeOffenderRepository.deleteprOffenderPptyContainers(offenderBookId,modifyUserId);
	}

	@Override
	public void prOffenderPptyItems(Long offenderBookId,String modifyUserId) {
		purgeOffenderRepository.deleteprOffenderPptyItems(offenderBookId,modifyUserId);
	}

	@Override
	public void prOffenderPptyItemEvents(Integer offenderBookId,String modifyUserId) {
		purgeOffenderRepository.prOffenderPptyItemEvents(offenderBookId).forEach(bean->prOffenderPptyItemTxns((int)bean.getOffenderBookId(),(int)bean.getEventSeq(),modifyUserId));
		purgeOffenderRepository.deleteprOffenderPptyItemEvents(offenderBookId,modifyUserId);
	}

	@Override
	public void prOffenderPptyItemTxns(Integer offenderBookId, Integer eventSeq,String modifyUserId) {
		purgeOffenderRepository.deleteprOffenderPptyItemTxns(offenderBookId, eventSeq,modifyUserId);
	}

	@Override
	public void prOffenderPrgObligations(Long offenderBookId,String modifyUserId) {
		purgeOffenderRepository.prOffenderPrgObligations(offenderBookId).forEach(val->{
			prOffenderCourseAttendancs1(val.getOffenderPrgObligationId(),modifyUserId);
			prOffenderIndSchedules1(val.getOffenderPrgObligationId()!=null?val.getOffenderPrgObligationId().intValue():null,modifyUserId);
			prOffenderObligationAdjs(val.getOffenderPrgObligationId()!=null?val.getOffenderPrgObligationId().intValue():null,modifyUserId);
			prOffenderPrgObligationHty(val.getOffenderPrgObligationId()!=null?val.getOffenderPrgObligationId().intValue():null,modifyUserId);
			prOffenderProgramProfiles1(val.getOffenderPrgObligationId()!=null?val.getOffenderPrgObligationId().intValue():null,modifyUserId);
		});
		purgeOffenderRepository.deleteprOffenderPrgObligations(offenderBookId,modifyUserId);
	}

	@Override
	public void prOffenderCourseAttendancs1(Long offenderPrgObligationId,String modifyUserId) {
		purgeOffenderRepository.prOffenderCourseAttendancs1(offenderPrgObligationId).forEach(val->prOffenderCourseSkills(val.getEventId(),modifyUserId));
		purgeOffenderRepository.deleteprOffenderCourseAttendancs1(offenderPrgObligationId,modifyUserId);
	}

	@Override
	public void prOffenderIndSchedules1(Integer offenderPrgObligationId,String modifyUserId) {
		purgeOffenderRepository.prOffenderIndSchedules1(offenderPrgObligationId).forEach(val->prOffenderIndSchSents1(val.getEventId()!=null?val.getEventId().longValue():null,modifyUserId));
	}


	@Override
	public void prOffenderObligationAdjs(Integer offenderPrgObligationId,String modifyUserId) {
		purgeOffenderRepository.deleteprOffenderObligationAdjs(offenderPrgObligationId,modifyUserId);
	}

	@Override
	public void prOffenderPrgObligationHty(Integer offenderPrgObligationId,String modifyUserId) {
		purgeOffenderRepository.deleteprOffenderPrgObligationHty(offenderPrgObligationId,modifyUserId);
	}

	@Override
	public void prOffenderProgramProfiles1(Integer offenderPrgObligationId,String modifyUserId) {
		purgeOffenderRepository.prOffenderProgramProfiles1(offenderPrgObligationId).forEach(val->{
			prOffenderCourseApptRules(val.getOffPrgrefId(),modifyUserId);
			prOffenderCourseAttendancs2(val.getOffPrgrefId()!=null?new BigDecimal(val.getOffPrgrefId()):null,modifyUserId);
			prOffenderProgramProfiles2(val.getOffPrgrefId(),modifyUserId);
		});
		purgeOffenderRepository.deleteprOffenderProgramProfiles1(offenderPrgObligationId,modifyUserId);
	}

	@Override
	public void prOffenderProceedings(BigDecimal offenderBookId,String modifyUserId) {
		purgeOffenderRepository.prOffenderProceedings(offenderBookId).forEach(val->{
			prCourtEvents2(val.getOffenderProceedingId()!=null?val.getOffenderProceedingId().intValue():null,modifyUserId);
			prOffenderProceedingSents1(val.getOffenderProceedingId(),modifyUserId);
		});
		purgeOffenderRepository.deleteprOffenderProceedings(offenderBookId,modifyUserId);
	}

	@Override
	public void prCourtEvents2(Integer offenderProceedingId,String modifyUserId) {
		purgeOffenderRepository.prCourtEvents2(offenderProceedingId).forEach(val->{
			prCourtEventCharges(new BigDecimal(val.getEventId()),modifyUserId);
			prCourtEventSentences(new BigDecimal(val.getEventId()),modifyUserId);
			prLinkCaseTxns(new BigDecimal(val.getEventId()),modifyUserId);
		});
		purgeOffenderRepository.deleteprCourtEvents2(offenderProceedingId,modifyUserId);
	}

	@Override
	public void prOffenderProceedingSents1(BigDecimal offenderProceedingId,String modifyUserId) {
		purgeOffenderRepository.deleteprOffenderProceedingSents1(offenderProceedingId,modifyUserId);
	}

	@Override
	public void prOffenderProfiles(BigDecimal offenderBookId,String modifyUserId) {
		purgeOffenderRepository.deleteprOffenderProfiles(offenderBookId,modifyUserId);
	}

	@Override
	public void prOffenderProgramProfiles(BigDecimal offenderBookId,String modifyUserId) {
		purgeOffenderRepository.prOffenderProgramProfiles(offenderBookId).forEach(val->{
			prOffenderCourseApptGrps(val.getOffPrgrefId()!=null?new BigDecimal(val.getOffPrgrefId()):null,modifyUserId);
			prOffenderCourseAttendancs2(val.getOffPrgrefId()!=null?new BigDecimal(val.getOffPrgrefId()):null,modifyUserId);
			prOffenderProgramProfiles2(val.getOffPrgrefId(),modifyUserId);
		});
		purgeOffenderRepository.deleteprOffenderProgramProfiles(offenderBookId,modifyUserId);
	}

	@Override
	public void prOffenderPropertyBundles(BigDecimal offenderBookId,String modifyUserId) {
		purgeOffenderRepository.deleteprOffenderPropertyBundles(offenderBookId,modifyUserId);
	}

	@Override
	public void prOffenderProposedMvmnts(BigDecimal offenderBookId,String modifyUserId) {
		purgeOffenderRepository.prOffenderProposedMvmnts(offenderBookId).forEach(bean->{
			if(bean.getOffenderBookId()!=null && bean.getMovementSeq()!=null)
				prOffenderMovementDetails(bean.getOffenderBookId(),bean.getMovementSeq(),modifyUserId);
		});
		purgeOffenderRepository.deleteprOffenderProposedMvmnts(offenderBookId,modifyUserId);
	}

	@Override
	public void prOffenderMovementDetails(Long offenderBookId, Long movementSeq,String modifyUserId) {
		purgeOffenderRepository.deleteprOffenderMovementDetails(offenderBookId, movementSeq,modifyUserId);
	}

	@Override
	public void proffenderptr(Long offenderBookId,String modifyUserId) {
		purgeOffenderRepository.proffenderptr(offenderBookId).forEach(val->{
			prExtOwnershipTransfer(val.getPtrId(),modifyUserId);
			prOffenderBookingAgyLocs1(val.getPtrId()!=null?new BigDecimal(val.getPtrId()):null,modifyUserId);
			prOffenderPtrDetails(val.getPtrId()!=null?new BigDecimal(val.getPtrId()):null,modifyUserId);
		});
		purgeOffenderRepository.deleteproffenderptr(offenderBookId,modifyUserId);
	}

	@Override
	public void prExtOwnershipTransfer(Long ptrId,String modifyUserId) {
		purgeOffenderRepository.deleteprExtOwnershipTransfer(ptrId,modifyUserId);
	}

	@Override
	public void prOffenderBookingAgyLocs1(BigDecimal ptrId,String modifyUserId) {
		purgeOffenderRepository.deleteprOffenderBookingAgyLocs1(ptrId,modifyUserId);
	}

	@Override
	public void prOffenderPtrDetails(BigDecimal ptrId,String modifyUserId) {
		purgeOffenderRepository.deleteprOffenderPtrDetails(ptrId,modifyUserId);
	}

	@Override
	public void prOffenderReferrals(Long offenderBookId,String modifyUserId) {
		purgeOffenderRepository.deleteprOffenderReferrals(offenderBookId,modifyUserId);
	}

	@Override
	public void prOffenderReleaseDetails(BigDecimal offenderBookId,String modifyUserId) {
		purgeOffenderRepository.deleteprOffenderReleaseDetails(offenderBookId,modifyUserId);
	}

	@Override
	public void prOffenderResidences(BigDecimal offenderBookId,String modifyUserId) {
		purgeOffenderRepository.deleteprOffenderResidences(offenderBookId,modifyUserId);
	}

	@Override
	public void prOffenderRestrictions(BigDecimal offenderBookId,String modifyUserId) {
		purgeOffenderRepository.deleteprOffenderRestrictions(offenderBookId,modifyUserId);
	}

	@Override
	public void prOffenderRiskPredictors(BigDecimal offenderBookId,String modifyUserId) {
		purgeOffenderRepository.deleteprOffenderRiskPredictors(offenderBookId,modifyUserId);
	}

	@Override
	public void prOffenderSentCalculations(BigDecimal offenderBookId,String modifyUserId) {
		purgeOffenderRepository.deleteprOffenderSentCalculations(offenderBookId,modifyUserId);
	}

	@Override
	public void prOffenderStgAffiliations(BigDecimal offenderBookId,String modifyUserId) {
		purgeOffenderRepository.prOffenderStgAffiliations(offenderBookId).forEach(bean->prOffenderStgDetails(bean.getOffenderBookId(),bean.getStgSeq(),modifyUserId));
		purgeOffenderRepository.deleteprOffenderStgAffiliations(offenderBookId,modifyUserId);
	}

	@Override
	public void prOffenderStgDetails(BigDecimal offenderBookId, BigDecimal stgSeq,String modifyUserId) {
		purgeOffenderRepository.deleteprOffenderStgDetails(offenderBookId, stgSeq,modifyUserId);
	}

	@Override
	public void prOffenderStgAssociations(BigDecimal offenderBookId,String modifyUserId) {
		purgeOffenderRepository.deleteprOffenderStgAssociations(offenderBookId,modifyUserId);
	}

	@Override
	public void prOffenderSubstanceAbuses(BigDecimal offenderBookId,String modifyUserId) {
		purgeOffenderRepository.deleteprOffenderSubstanceAbuses(offenderBookId,modifyUserId);
	}

	@Override
	public void prOffenderSupervisingCourts(BigDecimal offenderBookId,String modifyUserId) {
		purgeOffenderRepository.deleteprOffenderSupervisingCourts(offenderBookId,modifyUserId);
	}

	@Override
	public void prOffenderSupervisionLevels(BigDecimal offenderBookId,String modifyUserId) {
		purgeOffenderRepository.prOffenderSupervisionLevels(offenderBookId).forEach(bean->{
			prOffenderSupervisionPlans(bean.getOffenderBookId(),bean.getSupervisionLevelType(),modifyUserId);
			prOffenderSupervisionRates(bean.getOffenderBookId(),bean.getSupervisionLevelType(),modifyUserId);
		});
		purgeOffenderRepository.deleteprOffenderSupervisionLevels(offenderBookId,modifyUserId);
	}

	@Override
	public void prOffenderSupervisionPlans(Long offenderBookId, String supervisionLevelType,String modifyUserId) {
		purgeOffenderRepository.prOffenderSupervisionPlans(offenderBookId, supervisionLevelType).forEach(bean->prOffenderPlanedActivities1(bean.getOffenderBookId()!=null?bean.getOffenderBookId().longValue():null,bean.getPlanSeq(),modifyUserId));;
		purgeOffenderRepository.deleteprOffenderSupervisionPlans(offenderBookId, supervisionLevelType,modifyUserId);
	}

	@Override
	public void prOffenderPlanedActivities1(Long offenderBookId, Long planSeq,String modifyUserId) {
		purgeOffenderRepository.deleteprOffenderPlanedActivities1(offenderBookId, planSeq,modifyUserId);
	}

	@Override
	public void prOffenderSupervisionRates(Long offenderBookId, String supervisionType,String modifyUserId) {
		purgeOffenderRepository.deleteprOffenderSupervisionRates(offenderBookId, supervisionType,modifyUserId);
	}

	@Override
	public void prOffenderTeamAssignments(Long offenderBookId,String modifyUserId) {
		purgeOffenderRepository.deleteprOffenderTeamAssignments(offenderBookId,modifyUserId);
	}

	@Override
	public void prOffenderTeamAssignHty(Long offenderBookId,String modifyUserId) {
		purgeOffenderRepository.deleteprOffenderTeamAssignHty(offenderBookId,modifyUserId);
	}

	@Override
	public void prOffenderTemporaryAbsences(Long offenderBookId,String modifyUserId) {
		purgeOffenderRepository.deleteprOffenderTemporaryAbsences(offenderBookId,modifyUserId);
	}

	@Override
	public void prOffenderTmpRelSchedules(Long offenderBookId,String modifyUserId) {
		purgeOffenderRepository.deleteprOffenderTmpRelSchedules(offenderBookId,modifyUserId);
	}

	@Override
	public void prOffenderVisits(Long offenderBookId,String modifyUserId) {
		purgeOffenderRepository.prOffenderVisits(offenderBookId).forEach(val->prOffenderVisitVisitors(val.getOffenderVisitId(),modifyUserId));;
		purgeOffenderRepository.deleteprOffenderVisits(offenderBookId,modifyUserId);
	}

	@Override
	public void prOffenderVisitVisitors(BigDecimal offenderVisitId,String modifyUserId) {
		purgeOffenderRepository.deleteprOffenderVisitVisitors(offenderVisitId,modifyUserId);
	}

	@Override
	public void prOffenderVitalSigns1(Long offenderBookId,String modifyUserId) {
		purgeOffenderRepository.deleteprOffenderVitalSigns1(offenderBookId,modifyUserId);
	}

	@Override
	public void prOrders(BigDecimal offenderBookId,String modifyUserId) {
		purgeOffenderRepository.prOrders(offenderBookId).forEach(val->{
			prOffenderSentences1(val.getOrderId(),modifyUserId);
			prOrderPurposes(val.getOrderId()!=null?val.getOrderId().longValue():null,modifyUserId);
		});
		purgeOffenderRepository.deleteprOrders(offenderBookId,modifyUserId);
	}

	@Override
	public void prReleasePlans(BigDecimal offenderBookId,String modifyUserId) {
		purgeOffenderRepository.prReleasePlans(offenderBookId).forEach(val->prRpOtherOccupants(val.getReleasePlanId()!=null?new BigDecimal(val.getReleasePlanId()):null,modifyUserId));
		purgeOffenderRepository.deleteprReleasePlans(offenderBookId,modifyUserId);
	}

	@Override
	public void prRpOtherOccupants(BigDecimal releasePlanId,String modifyUserId) {
		purgeOffenderRepository.deleteprRpOtherOccupants(releasePlanId,modifyUserId);
	}

	@Override
	public void prStaffWorks(BigDecimal offenderBookId,String modifyUserId) {
		purgeOffenderRepository.prStaffWorks(offenderBookId).forEach(val->prStaffWorkAssignments(val.getStaffWorkId(),modifyUserId));
		purgeOffenderRepository.deleteprStaffWorks(offenderBookId,modifyUserId);
	}

	@Override
	public void prStaffWorkAssignments(BigDecimal staffWorkId,String modifyUserId) {
		purgeOffenderRepository.deleteprStaffWorkAssignments(staffWorkId,modifyUserId);
	}

	@Override
	public void prSupervisionTransactions(BigDecimal offenderBookId,String modifyUserId) {
		purgeOffenderRepository.deleteprSupervisionTransactions(offenderBookId,modifyUserId);
	}

	@Override
	public void prTaskAssignmentHty(BigDecimal offenderBookId,String modifyUserId) {
		purgeOffenderRepository.deleteprTaskAssignmentHty(offenderBookId,modifyUserId);
	}


	@Override
	public void proffendercustodystatus(Long offenderBookId,String modifyUserId) {
		purgeOffenderRepository.purgeDeleteOffenderCustodyStatus(offenderBookId,modifyUserId);
		
	}
}

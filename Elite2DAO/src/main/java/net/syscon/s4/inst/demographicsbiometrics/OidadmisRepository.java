package net.syscon.s4.inst.demographicsbiometrics;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.transaction.support.TransactionOperations;

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
import net.syscon.s4.im.beans.AgencyBillingProfiles;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.BedAssignmentHistories;
import net.syscon.s4.im.beans.CaseloadAdmAlertProfiles;
import net.syscon.s4.im.beans.CaseloadAdmOtherProfiles;
import net.syscon.s4.im.beans.LivingUnits;
import net.syscon.s4.im.beans.MovementReasons;
import net.syscon.s4.im.beans.OffenderTransactions;
import net.syscon.s4.im.beans.SystemMessages;
import net.syscon.s4.im.beans.TransactionOperation;
import net.syscon.s4.im.beans.VHeaderBlock2;

/**
 * Class OidadmisRepository
 */
public interface OidadmisRepository {
	List<MovementReasons> cgfkOffEmDspDescriptionMrRecordGroup(String movementReasonCode);

	List<ReferenceCodes> cgfkOffEmDspDescriptionRecordGroup(String systemMode);

	List<ReferenceCodes> cgfkBedAhDspDescriptionRecordGroup(String systemMode);

	List<AgencyLocations> cgfkOffEmDspDescriptionCaseloadIdRecordGroup(String systemMode);

	List<ReferenceCodes> cgfkOffEmDspDescriptionRcRecordGroup(String systemMode);

	List<AgencyLocations> cgfkOffEmDspDescriptionAgyLocIdRecordGroup();

	AgencyBillingProfiles createUpdateOffBillingProf(AgencyBillingProfiles paramBean);

	Object validateMovementDatetimemaxDateTime(OffenderExternalMovements paramBean);

	List<String> checkActiveYnactCurr(OffenderExternalMovements paramBean);

	List<BedAssignmentHistories> bedAhSearchBedAssignmentHistories(BedAssignmentHistories objBedAssignmentHistories);

	LivingUnits offEmWhenNewRecordInstancegetCapacityCur(LivingUnits paramBean);

	AgencyLocations cgfkchkOffEmOffEmAgyLocc(AgencyLocations paramBean);

	CaseloadAdmOtherProfiles getCountOfAgyInCasegetAgyCur(CaseloadAdmOtherProfiles paramBean);

	List<LivingUnits> validateLivingUnitsvsLcdCur(LivingUnits paramBean);

	List<OffenderBookings> offBkgsSearchOffenderBookings(OffenderBookings objOffenderBookings);

	List<StaffMembers> getStaffNamedefStaffCur(StaffMembers paramBean);

	List<LivingUnits> chkAssignedLochouUnTypeCur(LivingUnits paramBean);

	StaffMembers cgfkchkOffBkgsOffBkgStafc(StaffMembers paramBean);

	List<LivingUnits> dspDescriptionWhenValidateItemlivDesc(LivingUnits paramBean);

	Long callThePreInsertc(BedAssignmentHistories paramBean);

	OffenderExternalMovements deactivateActiveOffExmRecsetLockCur(OffenderExternalMovements paramBean);

	List<String> workflowDownFormcallFormCur(WorkflowScreens paramBean);

	List<AgencyLocations> offEmWhenNewRecordInstance(AgencyLocations paramBean);

	ReferenceCodes cgfkchkOffEmOffEmRefc(ReferenceCodes paramBean);

	Integer syspflInsertSystemProfiles(List<SystemProfiles> lstSystemProfiles);

	TransactionOperation storGlobalsgetCrAccount(TransactionOperation paramBean);

	SystemMessages offEmWhenNewBlockInstance(SystemMessages paramBean);

	OffenderExternalMovements offEmPreInsertc(OffenderExternalMovements paramBean);

	List<LivingUnits> cgfkchkBedAhBedAhLivUnic(LivingUnits paramBean);

	TransactionTypes insertMasterRecTransTypes(TransactionTypes paramBean);

	WorkflowScreens workflowDownFormcountCur(WorkflowScreens paramBean);

	List<LivingUnits> dspDescriptionWhenValidateItemgetCapacityCur(LivingUnits dataObj);

	CaseloadAdmOtherProfiles getLivingUnitIdgetLivUnitId(CaseloadAdmOtherProfiles paramBean);

	List<OffenderTransactions> offTxnSearchOffenderTransactions(OffenderTransactions objOffenderTransactions);

	LivingUnits cgfklkpBedAhBedAhLivUnic(LivingUnits paramBean);

	OffenderExternalMovements offEmWhenNewRecordInstanceadm(OffenderExternalMovements paramBean);

	Integer sysPflDeleteSystemProfiles(List<SystemProfiles> lstSystemProfiles);

	ReferenceCodes cgfkchkOffEmOffEmRefCodc(ReferenceCodes paramBean);

	Integer offEmWhenNewBlockInstancecasAgyCur(CaseloadAgencyLocations paramBean);

	String offEmWhenNewBlockInstanceoffAlertCur(Integer pMsgNumber);

	TransactionTypes storGlobals(TransactionTypes paramBean);

	AgencyBillingProfiles createUpdateOffBillingProfgetAgyProfInfoCur(AgencyBillingProfiles paramBean);

	Integer bedAhInsertBedAssignmentHistories(List<BedAssignmentHistories> lstBedAssignmentHistories);

	List<SystemProfiles> sysPflSearchSystemProfiles(SystemProfiles objSystemProfiles);

	List<VHeaderBlock2> offbkgExecuteQuery(VHeaderBlock2 objVHeaderBlock2);

	List<LivingUnits> dspDescriptionWhenValidateItemvsLcdCur(LivingUnits paramBean);

	Object validateMovementDatetimemaxDate(OffenderExternalMovements paramBean);

	List<OffenderExternalMovements> offEmSearchOffenderExternalMovements(
			OffenderExternalMovements objOffenderExternalMovements);

	List<LivingUnits> populateGlobalCapacityvsLcdCur(LivingUnits paramBean);

	MovementReasons cgfklkpOffEmOffEmMoveRsc(MovementReasons paramBean);

	WorkflowScreens workflowDownFormseqNoCur(WorkflowScreens paramBean);

	Integer offtxnInsertOffenderTransactions(List<OffenderTransactions> lstOffenderTransactions);

	ReferenceCodes cgfkchkOffBkgsOffBkgRef(ReferenceCodes paramBean);

	MovementReasons cgfkchkOffEmOffEmMoveRsc(MovementReasons paramBean);

	CaseloadAdmOtherProfiles getCountOfAgyInCasegetCountCur(CaseloadAdmOtherProfiles paramBean);

	OffenderExternalMovements validateMovementDatemaxDate(Integer offenderBookId);

	OffenderBookings checkPrevBkgClosedchekCrsr(OffenderBookings paramBean);

	TransactionOperations insertMasterRec(TransactionOperations paramBean);

	Long validateMovementDatemaxBookId(OffenderBookings paramBean);

	OffenderBookings nbtActiveFlagWhenValidateItembookNoCrsr(OffenderBookings paramBean);

	Long validateMovementDatetimemaxBookId(OffenderBookings paramBean);

	ReferenceCodes cgfkkpOffEmOffEmRefCodc(ReferenceCodes paramBean);

	CaseloadAgencyLocations offEmWhenNewRecordInstance(CaseloadAgencyLocations paramBean);

	AgencyLocations cgfklkpOffEmOffEmAgyLocc(AgencyLocations paramBean);

	List<LivingUnits> checkCapacityvsLcdCur(LivingUnits paramBean);

	String checkBedAhBlkNavcasAgyCur(CaseloadAgencyLocations paramBean);

	Integer offemInsertOffenderExternalMovements(List<OffenderExternalMovements> lstOffenderExternalMovements);

	String getCountOfAgyInCaseagyLocCur(CaseloadAgencyLocations dataObj);

	AgencyLocations populateDspDescriptionRg(AgencyLocations paramBean);

	AgencyLocations cgfkchkOffEmOffEmAgyc(AgencyLocations dataObj);

	List<AgencyLocations> cgfklkpOffEmOffEmAgyc(AgencyLocations paramBean);

	Integer updateOffenderBookings(OffenderBookings lstOffenderBookings, final Integer userName);

	Integer insertOffenderBookings(OffenderBookings offBook);

	Long offbkgPreInsertc();

	Integer offBookingUpdateOffenderExternalMovements(VHeaderBlock2 vblock);

	List<MovementReasons> cgfkOffEmDspDescriptionMRsnRecordGroup();

	List<ReferenceCodes> rgReferenceCodesStatus();

	List<ReferenceCodes> getSaffmembersDescription();

	List<AgencyLocations> cgfkOffEmDspDescriptionRGroup();

	BedAssignmentHistories bedAhPreInsertc(BedAssignmentHistories paramBean);

	Integer getEscSeq(OffenderBookings offenderBookId);

	Integer updateOffenderEscapes(OffenderEscape offBook);

	Integer offEmUpdateOffenderExternalMovements(OffenderExternalMovements offExMnts);

	List<Caseloads> caseloadIdValue(BigDecimal offenderId,String userName);

	StaffMembers cgfkchkOffBkgsOffBkgStafId();
	
	String oidadmisgetnewbookingno();

	OffenderBookings dafaultLocationData(String agyLocId);

	String getAdmissionType(VHeaderBlock2 searchBean);

	Integer getPoffAge(OffenderExternalMovements bean);

	Integer checkYoungOff(String caseloadId, Integer pOffAge);

	OffenderBookings getConflictEvent(OffenderExternalMovements bean);
	
	String chkOffenderDeductions(OffenderTransactions searchRecord);

	String chkTrustFlag(String caseloadId);
	public Integer getStaffId(String userId);
	
	String ChkAccountStatus(String caseLoadId, String offenderId);
	
	TransactionOperation drAccountCodeCrAccountCode(String caseLoadId);
	
	BedAssignmentHistories getOffenderBedDetails(BigDecimal offenderBookId);
	
	String getSystemGeneratedUser();
	
	String overridingDetails(BigDecimal offenderId);
	
	String getiepCode(String description);
	
	Integer updateCustodyAdjustments(Date intakeDate,Long offenderBookId);

	List<String> getAlertMsgForReleaseOffender(VHeaderBlock2 searchBean);
}
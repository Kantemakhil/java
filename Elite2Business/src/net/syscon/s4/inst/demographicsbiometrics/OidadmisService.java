package net.syscon.s4.inst.demographicsbiometrics;

import java.math.BigDecimal;
import java.util.List;

import net.syscon.s4.common.beans.CaseloadAgencyLocations;
import net.syscon.s4.common.beans.Caseloads;
import net.syscon.s4.common.beans.OffenderBookings;
import net.syscon.s4.common.beans.OffenderExternalMovements;
import net.syscon.s4.common.beans.OffenderExternalMovementsCommitBean;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.beans.TransactionTypes;
import net.syscon.s4.common.beans.WorkflowScreens;
import net.syscon.s4.common.beans.dao.OidadmisCommitBean;
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
 * Class OidadmisService
 */
public interface OidadmisService {
	List<MovementReasons> cgfkOffEmDspDescriptionMrRecordGroup(String movementReasonCode);

	List<ReferenceCodes> cgfkOffEmDspDescriptionRecordGroup(String systemMode);

	List<ReferenceCodes> cgfkBedAhDspDescriptionRecordGroup(String systemMode);

	List<AgencyLocations> cgfkOffEmDspDescriptionCaseloadIdRecordGroup(String systemMode);

	List<ReferenceCodes> cgfkOffEmDspDescriptionRcRecordGroup(String systemMode);

	List<AgencyLocations> cgfkOffEmDspDescriptionAgyLocIdRecordGroup();

	Long validateMovementDatemaxBookId(OffenderBookings paramBean);

	OffenderBookings nbtActiveFlagWhenValidateItembookNoCrsr(OffenderBookings dataObj);

	List<LivingUnits> populateGlobalCapacityvsLcdCur(LivingUnits dataObj);

	TransactionOperation storGlobalsgetCrAccount(TransactionOperation dataObj);

	List<LivingUnits> dspDescriptionWhenValidateItemlivDesc(LivingUnits dataObj);

	Integer bedahInsertBedAssignmentHistories(List<BedAssignmentHistories> lstBedAssignmentHistories);

	TransactionTypes storGlobals(TransactionTypes dataObj);

	Long validateMovementDatetimemaxBookId(OffenderBookings dataObj);

	List<OffenderTransactions> offTxnSearchOffenderTransactions(OffenderTransactions objOffenderTransactions);

	MovementReasons cgfklkpOffEmOffEmMoveRsc(MovementReasons dataObj);

	String checkBedAhBlkNavcasAgyCur(CaseloadAgencyLocations bean);

	Integer offEmUpdateOffenderExternalMovements(OffenderExternalMovements lstOffenderExternalMovements);

	OffenderExternalMovements offEmWhenNewRecordInstanceadm(OffenderExternalMovements dataObj);

	TransactionTypes insertMasterRecTransTypes(TransactionTypes dataObj);

	OffenderBookings offemInsertOffenderExternalMovements(List<OffenderExternalMovements> lstOffenderExternalMovements, final String userName);

	ReferenceCodes cgfkchkOffEmOffEmRefc(ReferenceCodes dataObj);

	CaseloadAdmOtherProfiles getLivingUnitIdgetLivUnitId(CaseloadAdmOtherProfiles paramBean);

	List<LivingUnits> chkAssignedLochouUnTypeCur(LivingUnits paramBean);

	Integer sysPflDeleteSystemProfiles(List<SystemProfiles> lstSystemProfiles);

	Integer offtxnInsertOffenderTransactions(List<OffenderTransactions> lstOffenderTransactions);

	List<LivingUnits> validateLivingUnitsvsLcdCur(LivingUnits paramBean);

	OffenderExternalMovements deactivateActiveOffExmRecsetLockCur(OffenderExternalMovements paramBean);

	List<String> checkActiveYnactCurr(OffenderExternalMovements paramBean);

	List<String> workflowDownFormcallFormCur(WorkflowScreens paramBean);

	List<OffenderExternalMovements> offEmSearchOffenderExternalMovements(
			OffenderExternalMovements objOffenderExternalMovements);

	List<VHeaderBlock2> offbkgExecuteQuery(VHeaderBlock2 objVHeaderBlock2);

	List<OffenderBookings> offBkgsSearchOffenderBookings(OffenderBookings objOffenderBookings);

	List<LivingUnits> dspDescriptionWhenValidateItemgetCapacityCur(LivingUnits dataObj);

	WorkflowScreens workflowDownFormcountCur(WorkflowScreens bean);

	Long callThePreInsertc(BedAssignmentHistories dataObj);

	OffenderExternalMovements validateMovementDatemaxDate(Integer offenderBookId);

	List<BedAssignmentHistories> oidadmisBedAhSearchBedAssignmentHistories(
			BedAssignmentHistories objBedAssignmentHistories);

	OffenderBookings checkPrevBkgClosedchekCrsr(OffenderBookings dataObj);

	MovementReasons cgfkchkOffEmOffEmMoveRsc(MovementReasons dataobj);

	SystemMessages offEmWhenNewBlockInstance(SystemMessages dataObj);

	WorkflowScreens workflowDownFormseqNoCur(WorkflowScreens paramBean);

	String offEmWhenNewBlockInstanceoffAlertCur(Integer dataObj);

	AgencyBillingProfiles createUpdateOffBillingProf(AgencyBillingProfiles paramBean);

	List<LivingUnits> checkCapacityvsLcdCur(LivingUnits dataObj);

	Integer syspflInsertSystemProfiles(List<SystemProfiles> lstSystemProfiles);

	AgencyBillingProfiles createUpdateOffBillingProfgetAgyProfInfoCur(AgencyBillingProfiles paramBean);

	ReferenceCodes cgfkchkOffEmOffEmRefCodc(ReferenceCodes dataObj);

	String getCountOfAgyInCaseagyLocCur(CaseloadAgencyLocations dataObj);

	StaffMembers cgfkchkOffBkgsOffBkgStafc(StaffMembers bean);

	List<LivingUnits> cgfkchkBedAhBedAhLivUnic(LivingUnits dataObj);

	List<SystemProfiles> sysPflSearchSystemProfiles(SystemProfiles objSystemProfiles);

	AgencyLocations cgfkchkOffEmOffEmAgyLocc(AgencyLocations dataObj);

	ReferenceCodes cgfkkpOffEmOffEmRefCodc(ReferenceCodes dataObj);

	AgencyLocations cgfklkpOffEmOffEmAgyLocc(AgencyLocations dataObj);

	ReferenceCodes cgfkchkOffBkgsOffBkgRef(ReferenceCodes dataObj);

	AgencyLocations cgfkchkOffEmOffEmAgyc(AgencyLocations dataObj);

	List<LivingUnits> dspDescriptionWhenValidateItemvsLcdCur(LivingUnits bean);

	LivingUnits offEmWhenNewRecordInstancegetCapacityCur(LivingUnits dataObj);

	Integer offEmWhenNewBlockInstancecasAgyCur(CaseloadAgencyLocations bean);

	CaseloadAgencyLocations offEmWhenNewRecordInstance(CaseloadAgencyLocations dataObj);

	LivingUnits cgfklkpBedAhBedAhLivUnic(LivingUnits dataObj);

	Integer oidadmisCommit(OidadmisCommitBean commitBean);

	CaseloadAdmOtherProfiles getCountOfAgyInCasegetAgyCur(CaseloadAdmOtherProfiles dataObj);

	OffenderBookings offemCommit(OffenderExternalMovementsCommitBean commitBean);

	AgencyLocations populateDspDescriptionRg(AgencyLocations dataObj);

	Integer offBookingUpdateOffenderExternalMovements(VHeaderBlock2 commitBean);

	List<ReferenceCodes> rgReferenceCodesStatus();

	List<ReferenceCodes> getSaffmembersDescription();

	List<AgencyLocations> cgfkOffEmDspDescriptionRGroup();

	List<Caseloads> caseloadIdValue(BigDecimal offenderId,String userName);

	OffenderBookings dafaultLocationData(String agyLocId);

	String getAdmissionType(VHeaderBlock2 searchBean);

	OffenderBookings getConflictEvent(OffenderExternalMovements searchBean);

	String chkTrustFlag(String caseloadId);

	String chkOffenderDeductions(OffenderTransactions searchBean);
	
	Integer updateCustodyStatus(Long offenderBookId, boolean newBooking, String userName); 
	
	String calculateCustodyStatus(Long offenderBookId);

	List<String> getOffenderAlertMsg(VHeaderBlock2 searchBean);
}

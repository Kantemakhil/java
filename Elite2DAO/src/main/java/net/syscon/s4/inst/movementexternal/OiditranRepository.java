package net.syscon.s4.inst.movementexternal;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.transaction.support.TransactionOperations;

import net.syscon.s4.common.beans.CaseloadAgencyLocations;
import net.syscon.s4.common.beans.Caseloads;
import net.syscon.s4.common.beans.OffenderBookings;
import net.syscon.s4.common.beans.OffenderExternalMovements;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.TransactionTypes;
import net.syscon.s4.common.beans.VOffExm;
import net.syscon.s4.im.beans.AccountCodes;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.BedAssignmentHistories;
import net.syscon.s4.im.beans.CaseloadAdmAlertProfiles;
import net.syscon.s4.im.beans.CaseloadAdmOtherProfiles;
import net.syscon.s4.im.beans.LivingUnits;
import net.syscon.s4.im.beans.Offenders;
import net.syscon.s4.im.beans.OmsModules;

public interface OiditranRepository {

	Object initFormCaseloadAgencyLocations(CaseloadAgencyLocations paramBean);

	List<ReferenceCodes> moveRsnLovRecordGroup();

	BedAssignmentHistories insIntoBedAssHist(BedAssignmentHistories paramBean);

	CaseloadAdmOtherProfiles getCountOfAgyInCase(CaseloadAdmOtherProfiles paramBean);

	List<CaseloadAdmOtherProfiles> cgfkOffEmToAgyLocIdRecordGroup(String caseloadId);

	List<VOffExm> offEmExecuteQuery(String caseloadId);

	TransactionOperations insertMasterRecInsertMasterRec(TransactionOperations paramBean);

	Integer offEmDeleteVOffExm(List<OffenderExternalMovements> lstVOffExm);

	Integer offEmUpdateVOffExm(List<OffenderExternalMovements> lstVOffExm);

	Integer offEmInsertOffenderExternalMovements(List<OffenderExternalMovements> lstVOffExm);

	Caseloads initForm(Caseloads paramBean);

	List<LivingUnits> tohlocLov(LivingUnits paramBean);

	OffenderExternalMovements insIntoOffExtMovement(OffenderExternalMovements paramBean);

	List<LivingUnits> checkCapacityIndividual(LivingUnits paramBean);

	AccountCodes insertMasterRecinsertMasterRec(AccountCodes paramBean);

	TransactionTypes storGlobals(TransactionTypes paramBean);

	Offenders cgfkchkOffEmOffEmOffBkg(OffenderBookings paramBean);

	List<LivingUnits> populateLivingUnit(LivingUnits paramBean);

	AccountCodes storGlobals(AccountCodes paramBean);

	AgencyLocations cgfkchkOffEmOffEmAgyLoc(AgencyLocations paramBean);

	CaseloadAdmAlertProfiles displayNecessaryAlerts(CaseloadAdmAlertProfiles paramBean);

	TransactionTypes insertMasterRecinsertMasterRecTransactionTypes(TransactionTypes paramBean);

	TransactionOperations storGlobals(TransactionOperations paramBean);

	OmsModules createFormGlobalscreateFormGlobals(OmsModules paramBean);

	Object initForm(CaseloadAgencyLocations paramBean);

	OffenderBookings nonassSecValProc(OffenderBookings paramBean);

	Integer offExternalMovmentssgetMaxBookIdMovmentSeq(final Long offenderBookId);
	
	List<String> findToAgyLocIdList();

	Integer offBkgCommit(List<OffenderBookings> updateList);

	Integer getCountOfAgyInCase(String caseloadId);

	String oidadmisGetDefaults(String caseloadId);

	String getActiveAgyLocDesc(String string);

	LivingUnits populateLivingUnit(String livUnitId);
	
	Integer offEmUpdateOffenderExternalMovements(List<OffenderExternalMovements> lstVOffExm);

	Integer noOfBedAvailableInTheGivenLocation(BigDecimal living_unit_id);
	
}

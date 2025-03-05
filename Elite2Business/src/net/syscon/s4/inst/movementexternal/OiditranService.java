package net.syscon.s4.inst.movementexternal;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.transaction.support.TransactionOperations;

import net.syscon.s4.common.beans.Caseloads;
import net.syscon.s4.common.beans.OffenderBookings;
import net.syscon.s4.common.beans.OffenderBookingsCommitBean;
import net.syscon.s4.common.beans.OffenderExternalMovements;
import net.syscon.s4.common.beans.OffenderExternalMovementsCommitBean;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.VOffExm;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.BedAssignmentHistories;
import net.syscon.s4.im.beans.CaseloadAdmAlertProfiles;
import net.syscon.s4.im.beans.CaseloadAdmOtherProfiles;
import net.syscon.s4.im.beans.LivingUnits;
import net.syscon.s4.im.beans.OmsModules;

public interface OiditranService {
	List<TransactionOperations> insertMasterRec(TransactionOperations paramBean);

	TransactionOperations storGlobals(TransactionOperations paramBean);

	List<ReferenceCodes> moveRsnLovRecordGroup();

	LivingUnits tohlocLov(LivingUnits paramBean);

	CaseloadAdmAlertProfiles displayNecessaryAlerts(CaseloadAdmAlertProfiles paramBean);

	AgencyLocations cgfkchkOffEmOffEmAgyLoc(AgencyLocations paramBean);

	List<CaseloadAdmOtherProfiles> cgfkOffEmToAgyLocIdRecordGroup(String caseloadId);

	BedAssignmentHistories insIntoBedAssHist(BedAssignmentHistories paramBean);

	void cgfkchkOffEmOffEmOffBkg(OffenderBookings paramBean);

	List<VOffExm> offEmExecuteQuery(String caseloadId);

	Integer offEmCommit(OffenderExternalMovementsCommitBean commitBean);

	List<OffenderBookings> nonassSecValProc(OffenderBookings paramBean);

	List<LivingUnits> populateLivingUnit(LivingUnits paramBean);

	OffenderExternalMovements insIntoOffExtMovement(OffenderExternalMovements paramBean);

	List<CaseloadAdmOtherProfiles> getCountOfAgyInCase(CaseloadAdmOtherProfiles paramBean);

	Caseloads initForm(Caseloads paramBean);

	LivingUnits checkCapacityIndividual(LivingUnits paramBean);

	OmsModules createFormGlobals(OmsModules paramBean);

	List<String> findToAgyLocIdList();

	Integer offBkgCommit(List<OffenderBookings> commitBean);

	CaseloadAdmOtherProfiles getCountOfAgyInCase(String caseloadId);
	
	Integer noOfBedAvailableInTheGivenLocation(BigDecimal living_unit_id);

}

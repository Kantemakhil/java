package net.syscon.s4.triggers;

import net.syscon.s4.common.beans.OffenderBookings;
import net.syscon.s4.common.beans.dao.RecordGroup;
import net.syscon.s4.im.beans.Offenders;
import net.syscon.s4.inst.legalscreens.maintenance.bean.OffenderCharges;

public interface OffenderChargesVineIntfTrgRepository {

	OffenderBookings curActBook(Long pOffBookId);

	Offenders curOff(Long pOffId);

	OffenderCharges getOldOffenderCharge(Long caseId);

	Integer inserting(ChAudit chAudit);

	Integer updating(ChAudit chAudit);

	Integer deleting(ChAudit chAudit);

	RecordGroup curOffDesc(String pOffCode, String pStatCode);
}

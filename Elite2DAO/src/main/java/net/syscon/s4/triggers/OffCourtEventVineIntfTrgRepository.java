package net.syscon.s4.triggers;

import java.util.List;

import net.syscon.s4.common.beans.OffenderBookings;
import net.syscon.s4.im.beans.OffenderCases;
import net.syscon.s4.im.beans.Offenders;
import net.syscon.s4.inst.schedules.bean.CourtEvents;

public interface OffCourtEventVineIntfTrgRepository {
	OffenderBookings curActBook(Integer pOffBookId);

	Offenders curOff(Long pOffId);

	OffenderCases curOffCase(Integer pCaseId);

	CourtEvents getCourtEvents(Integer eventId);

	Integer insertUpdateDelete(List<CeAudit> courtevents);

	Integer update(List<CeAudit> ceAuditList);

	Integer delete(List<CeAudit> ceAuditList);
}

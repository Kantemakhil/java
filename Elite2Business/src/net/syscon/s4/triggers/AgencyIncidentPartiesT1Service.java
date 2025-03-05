package net.syscon.s4.triggers;

import net.syscon.s4.genericservices.CustomException;

public interface AgencyIncidentPartiesT1Service {
	Integer agencyIncidentPartiesT1Trigger(Long offenderBookId, Long agencyIncidentId) throws CustomException;
}

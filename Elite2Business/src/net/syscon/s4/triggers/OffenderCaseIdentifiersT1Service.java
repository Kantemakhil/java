package net.syscon.s4.triggers;

import java.util.List;

import net.syscon.s4.genericservices.CustomException;
import net.syscon.s4.inst.legals.beans.OffenderCaseIdentifiers;

public interface OffenderCaseIdentifiersT1Service {
	Integer getOffenderCaseIdentifiers(List<OffenderCaseIdentifiers> offenderCaseIdentifiersList)
			throws CustomException;
}

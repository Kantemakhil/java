package net.syscon.s4.triggers;

import net.syscon.s4.pkgs.OffenderAssociatedParties;

public interface OffenderAssociatedPartiesT1Repository {
	OffenderAssociatedParties getOffenderAssociatedParties(Long associatedPartyId);

	OffenderAssocPEventNotifs lCheckExistCur(Long offenderBookId);

	Long trgEventId();

	Integer save(OffenderAssocPEventNotifs offObj);

}

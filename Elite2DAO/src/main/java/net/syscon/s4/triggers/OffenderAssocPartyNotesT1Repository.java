package net.syscon.s4.triggers;

public interface OffenderAssocPartyNotesT1Repository {
	Integer saveOffenderAssocPEventNotifs(OffenderAssocPEventNotifs offenAssPEventNot);

	Long trgEventIdSeq();

	Long offenderBookId(Long associatedPartyId);
	
}

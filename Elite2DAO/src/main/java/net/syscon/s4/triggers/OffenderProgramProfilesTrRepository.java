package net.syscon.s4.triggers;

public interface OffenderProgramProfilesTrRepository {
	
	Integer offenderProgramStatusCount(String offenderProgramStatus);
	Integer waitlistDecisionCodeCount(String waitlistDecisionCode);
	Integer crsActyIdCount(Long crsActyId);
}

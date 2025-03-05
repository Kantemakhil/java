package net.syscon.s4.triggers;

import java.util.Date;

import net.syscon.s4.inst.schedules.bean.OffenderReleaseDetails;

public interface OffenderReleaseDetailsT2Repository {
	String lCheckExistCur(Long offenderBookId, Date releaseDate);

	String lSexCheckExistCur(Long offenderBookId);

	Long lTrgEventId();

	Integer inserting(OffenderAssocPEventNotifs offendAssPEvntNo);

	Integer updating(OffenderAssocPEventNotifs offendAssPEvntNo);

	OffenderReleaseDetails getOffenderReleaseDetails(Long offenderBookId);

}

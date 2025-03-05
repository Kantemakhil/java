package net.syscon.s4.pkgs.ocdenfor;

import net.syscon.s4.im.beans.CourseActivities;
import net.syscon.s4.im.beans.OffenceResultCodes;
import net.syscon.s4.inst.legals.beans.CourtEventSentences;
import net.syscon.s4.inst.legals.beans.VOffenderProceedingSents;

public interface OcdenforPkgRepository {
	CourseActivities getCourseActivitiesData(final Long crsActyId);

	String getRequirement(final Long programId);

	String getLocation(final String providerPartyCode);

	String getLocationFrmTeams(final Long providerPartyId);

	String getLocationFrmCorporates(final Long providerPartyId);

	OffenceResultCodes getCourtEventsentence(final VOffenderProceedingSents bean);

	Integer deleteCourtEventSentence(final VOffenderProceedingSents objSearchDao);

	Integer updateCourtEventSentence(final VOffenderProceedingSents objSearchDao);

	Integer insertCourtEventSentences(final VOffenderProceedingSents objSearchDao);
}
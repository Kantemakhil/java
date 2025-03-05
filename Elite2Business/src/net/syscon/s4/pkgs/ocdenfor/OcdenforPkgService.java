package net.syscon.s4.pkgs.ocdenfor;

import net.syscon.s4.im.beans.OffenderCaseNotes;
import net.syscon.s4.inst.legals.beans.VOffenderProceedingSents;
import net.syscon.s4.inst.legals.beans.VOffenderSentenceEvents;

public interface OcdenforPkgService {

	VOffenderSentenceEvents getActivityDetails(final VOffenderSentenceEvents vOff);

	VOffenderProceedingSents getCourtEventSentence(final VOffenderProceedingSents bean);

	String popOcuwarni(final OffenderCaseNotes offenderCaseNotes);

	Integer updateCourtEventSentence(final VOffenderProceedingSents objSearchDao, final String userName);
}

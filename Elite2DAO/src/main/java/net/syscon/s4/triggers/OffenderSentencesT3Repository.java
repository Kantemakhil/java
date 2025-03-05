package net.syscon.s4.triggers;

import net.syscon.s4.im.beans.OffenderPrgObligations;
import net.syscon.s4.inst.legals.beans.OffenderSentences;

public interface OffenderSentencesT3Repository {
	OffenderSentences getOffenderSentences(OffenderSentences offenderSentences);

	String isDrrCur(String sentenceCategory, String sentenceCalcType);

	Integer insert(OffenderPrgObligations offenPrgOliga);

	Integer update(OffenderSentences offenderSentences);
}

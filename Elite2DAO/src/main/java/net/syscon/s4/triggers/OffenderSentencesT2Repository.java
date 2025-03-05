package net.syscon.s4.triggers;

import net.syscon.s4.inst.legals.beans.OffenderSentences;

public interface OffenderSentencesT2Repository {
	OffenderSentences getOffenderSentences(OffenderSentences offenderSentences);

	Integer insert(OffenderSentences offenderSentences);
}

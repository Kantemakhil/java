package net.syscon.s4.triggers;

import net.syscon.s4.inst.legals.beans.OffenderSentences;

public interface OffenderSentencesTwfRepository {
	OffenderSentences getOffenderSentences(Long offenderBookId, Long sentenceSeq);

	String lSentenceCalcType(String sentenceCategory, String sentenceCalcType);
}

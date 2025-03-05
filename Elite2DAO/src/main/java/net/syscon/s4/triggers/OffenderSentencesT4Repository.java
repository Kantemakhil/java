package net.syscon.s4.triggers;

import net.syscon.s4.inst.legals.beans.OffenderSentences;

public interface OffenderSentencesT4Repository {

	OffenderSentences getOffenderSentences(OffenderSentences offenderSentences);

	Long lTrgEventId();

	Integer insert(OffenderAssocPEventNotifs offenderAssocPEventNotifs);

}

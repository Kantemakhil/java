package net.syscon.s4.triggers;

import java.util.List;

import net.syscon.s4.inst.legals.beans.OffenderSentences;

public interface OffenderSentencesT4Service {
	Integer offenderSentencesT4Tgr(List<OffenderSentences> offenderSentencesList);
}

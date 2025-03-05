package net.syscon.s4.triggers;

import net.syscon.s4.inst.legals.beans.OffenderSentences;

public interface OffenderSentencesTwfService {
	Integer offenderSentencesTwfTgr(OffenderSentences offenderSentences,String sqlOperation);
}

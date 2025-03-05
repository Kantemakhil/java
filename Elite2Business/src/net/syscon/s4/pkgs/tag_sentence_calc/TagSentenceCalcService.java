package net.syscon.s4.pkgs.tag_sentence_calc;

import java.util.Date;
import java.util.List;

import net.syscon.s4.inst.legals.beans.Adjustments;
import net.syscon.s4.inst.legals.beans.SentenceCalculation;

public interface TagSentenceCalcService {
	Integer insertAdjustDays(final List<Adjustments> recordList, final String userName);

	String calculateSentence(final SentenceCalculation sentenceCalc);

	Date getEndDate(final Long offenderBookId, final Long sentenceSeq, final Date vStartDate, final int i);

	Long getDaysBetween(final Date endDAte, final Date startDAte);

	Long getAdjustDays(final Long offenderBookId, final long sentenceSeq, final String string);
}
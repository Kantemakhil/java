package net.syscon.s4.triggers;

import java.math.BigDecimal;

import net.syscon.s4.im.beans.OffenderPrgObligations;
import net.syscon.s4.inst.legals.beans.OffenderSentConditions;

public interface OffenderSentConditionsT2Repository {
	OffenderSentConditions getOffenderSentConditions(Long offenderSentConditionId);

	String prgMethodCur(String commConditionType, String commConditionCode, String categoryType);

	String oblCur(BigDecimal offenderBookId, Long offenderSentConditionId);

	Long lvCntCommunityConditions(String commConditionType, String commConditionCode);

	Long lvCntOffePrgOblig(Long offenderSentConditionId, Long offenderBookId);

	Integer update(OffenderPrgObligations offePrgOblig, String oblCur);

	Integer insert(OffenderPrgObligations offePrgOblig);
}

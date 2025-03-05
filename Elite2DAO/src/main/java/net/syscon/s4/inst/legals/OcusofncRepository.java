package net.syscon.s4.inst.legals;

import java.util.List;

import net.syscon.s4.inst.legals.beans.OffensesOutcome;
import net.syscon.s4.inst.legals.beans.Sentences;

public interface OcusofncRepository {
	List<OffensesOutcome> fetechOffensesdialogData(OffensesOutcome selectedSentenceData);

	String fetchSentenceType(String category, String sentenceCalcType);
}

package net.syscon.s4.inst.legals;

import java.util.List;

import net.syscon.s4.inst.legals.beans.Condition;
import net.syscon.s4.inst.legals.beans.CourtCases;
import net.syscon.s4.inst.legals.beans.CourtEvent;
import net.syscon.s4.inst.legals.beans.OffensesOutcome;
import net.syscon.s4.inst.legals.beans.Sentences;

public interface OiddecasService {
	
	Integer deleteChildToCourtCases(CourtCases deletedCourtCase);
	
	Integer deleteOffenderCourtCases(CourtCases deletedCourtCase, String userName);

	Integer deleteOffenderCourtEvent(CourtEvent deletedCourtEvent, String userName);

	Boolean isLinkedCaseExist(Integer offenderChargeId);

	Boolean isSentenceAttached(OffensesOutcome offenses);

	Integer deleteOffenderOffenses(OffensesOutcome offenses, String userName);
	
	List<OffensesOutcome> populateOffensesOnSentence(Sentences sentence);

	List<Condition> populateConditionsData(Sentences sentence);

	Integer deleteSentences(Sentences sentence, String userName);

	Boolean isConsecutiveSentenceExist(Sentences sentence);

	Integer deleteConditionsRecord(Condition conditionRecord);

	Integer okToDeleteRecord(Long primaryKeyId, String tableName, String columnName, String excludeTable, String owner);

	Integer deleteSentenceCharges(OffensesOutcome sentenceCharges);

	Integer deleteChildToSentenceCharges(OffensesOutcome sentenceCharges);

	Integer deleteFineSentence(OffensesOutcome sentenceCharges);

	Integer deleteSentencesProdc(Sentences sentenceCharges, String userName);

	Integer deleteOffensesOnSentence(OffensesOutcome sentenceCharges);

	Integer okToModifyRecord(String keyString, String tableName, String columnName, String excludeTable, String owner);

	Boolean isSentAdjustAttached(Sentences sentence);

	Integer deleteSentencesOnCharges(OffensesOutcome sentenceCharges);
	
}

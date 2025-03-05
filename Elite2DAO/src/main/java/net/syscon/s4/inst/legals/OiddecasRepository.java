package net.syscon.s4.inst.legals;

import java.sql.SQLException;
import java.util.List;

import net.syscon.s4.inst.legals.beans.Condition;
import net.syscon.s4.inst.legals.beans.CourtCases;
import net.syscon.s4.inst.legals.beans.CourtEvent;
import net.syscon.s4.inst.legals.beans.OffensesOutcome;
import net.syscon.s4.inst.legals.beans.Sentences;

public interface OiddecasRepository {

	Integer deleteOffenderCourtCases(CourtCases delteCourtCase, String userName);

	Integer deleteChildToCourtCases(CourtCases deletedCourtCase);

	Integer deleteOffenderCourtEvents(CourtEvent deletedCourtEvent, String userName);

	Integer deleteEventChildRec(CourtEvent deletedCourtEvent);

	Boolean isLinkedCaseExist(Integer offenderChargeId);

	Boolean isSentenceAttached(OffensesOutcome offenses);

	Integer deleteOffenderOffenses(OffensesOutcome deletedOffenses, String userName);

	Integer deleteCourtEventCharges(OffensesOutcome deletedOffenses, String userName);
	
	List<OffensesOutcome> populateOffensesOnSentence(Sentences sentence);

	List<Condition> populateConditionsData(Sentences sentence);

	Integer deleteSentences(Sentences sentence, String userName);

	Boolean isConsecutiveSentenceExist(Sentences sentence);

	Integer deleteConditionsRecord(Condition conditionRecord);

	Integer okToDeleteRecord(Long keyCode, String tableName, String columnName, String excludeTable, String owner);
	

	Integer deleteSentenceCharges(OffensesOutcome sentenceCharges);

	Integer deleteChildToSentenceCharges(OffensesOutcome sentenceCharges);

	Integer deleteFineSentence(OffensesOutcome sentenceCharges);

	Integer deleteSentencesProdc(Sentences sentenceCharges, String userName);

	Integer deleteOffensesOnSentence(OffensesOutcome sentenceCharges);

	Integer okToModifyRecord(String keyString, String tableName, String columnName, String excludeTable, String owner);

	Boolean isSentAdjustAttached(Sentences sentence);

	Integer deleteSentencesOnCharges(OffensesOutcome sentenceCharges);
	
	

}

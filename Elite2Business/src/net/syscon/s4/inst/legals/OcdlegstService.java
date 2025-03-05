package net.syscon.s4.inst.legals;

import java.util.List;

import net.syscon.s4.common.beans.OffenderCaseCommitBean;
import net.syscon.s4.inst.legals.beans.Condition;
import net.syscon.s4.inst.legals.beans.Sentences;

public interface OcdlegstService {

	Integer updateSetenceReason(List<Sentences> sentence);

	List<Condition> populateConditionData(Sentences sentence);

	Integer updateConditionData(List<Condition> condition);

	List<Condition> getConditionLov();

	List<Condition> populateCaseStatus();

	List<Sentences> statusReasonUpdateLov(String sentenceCalcType);

	int updateCaseReason(OffenderCaseCommitBean courtCaseCommit);

}

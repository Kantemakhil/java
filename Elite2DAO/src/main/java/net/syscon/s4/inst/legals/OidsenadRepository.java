package net.syscon.s4.inst.legals;

import java.util.List;

import net.syscon.s4.inst.legals.beans.AdjustmentDetails;
import net.syscon.s4.inst.legals.beans.Adjustments;
import net.syscon.s4.inst.legals.beans.BailStatus;
import net.syscon.s4.inst.legals.beans.CourtCases;
import net.syscon.s4.inst.legals.beans.SentenceAdjustment;

public interface OidsenadRepository {
	 List<SentenceAdjustment> populateSentAdjustment(CourtCases offenderCases);
	 List<BailStatus> populateDebitTypeData();
	 List<AdjustmentDetails> populateDebitCredit(SentenceAdjustment sentenceAdjustment);
	 Long getPreInsertSentAdjId();
	 Integer insertDebitCreditRecord(List<AdjustmentDetails> adjustmentDetails);
	 Integer updateDebitCreditRecord(List<AdjustmentDetails> adjustmentDetails);
	 Integer deleteDebitCreditRecord(List<AdjustmentDetails> adjustmentDetails);
	 
	 List<BailStatus> populateAdjustTypeLov();
	 List<Adjustments> populateAdjustData(Long offenderBookId);
	Integer insertAdjustRecord(List<Adjustments> insertList);
	Long getNextAdjustId();
	Integer updateAdjustRecord(List<Adjustments> updateList);
	Integer prePostEventCall(List<Adjustments> insertList);
	Integer postInsertAdjustRecord(List<Adjustments> insertList);
	Integer deleteAdjustRecord(List<Adjustments> deleteList);
}

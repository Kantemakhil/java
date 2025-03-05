package net.syscon.s4.inst.legals;

import java.util.List;

import net.syscon.s4.inst.legals.beans.AdjustCommitBean;
import net.syscon.s4.inst.legals.beans.AdjustmentDetails;
import net.syscon.s4.inst.legals.beans.AdjustmentDetailsCommitBean;
import net.syscon.s4.inst.legals.beans.Adjustments;
import net.syscon.s4.inst.legals.beans.BailStatus;
import net.syscon.s4.inst.legals.beans.CourtCases;
import net.syscon.s4.inst.legals.beans.SentenceAdjustment;

public interface OidsenadService {
	List<SentenceAdjustment> populateSentAdjustment(CourtCases offenderCases);
	List<BailStatus> populateDebitTypeData();
	List<AdjustmentDetails> populateDebitCredit(SentenceAdjustment sentenceAdjustment);
	Integer insertDebitCreditRecord(AdjustmentDetailsCommitBean adjustmentDetailsCommitBean);	
	
	List<BailStatus> populateAdjustTypeLov();
	List<Adjustments> populateAdjustData(Long offenderBookId);
	Integer updateAdjustData(AdjustCommitBean adjustBean);
	Integer postInsertAdjustRecord(List<Adjustments> insertList);
 }

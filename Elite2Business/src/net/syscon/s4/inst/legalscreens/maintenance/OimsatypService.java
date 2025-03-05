package net.syscon.s4.inst.legalscreens.maintenance;

import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.inst.legals.beans.SentenceAdjustment;
import net.syscon.s4.inst.legals.beans.SentenceAdjustmentCommitBean;

/**
 * Interface OimsatypService
 */
public interface OimsatypService {

	Integer sentenceAdjustmentsCommit(SentenceAdjustmentCommitBean commitBean);

	List<ReferenceCodes> cgfkSentenceAdjustmentsDspRecordGroup();

	List<SentenceAdjustment> sentenceAdjustmentsExecuteQuery(SentenceAdjustment object);

	List<ReferenceCodes> cgfkSentenceAdjustmentsUsagRecordGroup();

}

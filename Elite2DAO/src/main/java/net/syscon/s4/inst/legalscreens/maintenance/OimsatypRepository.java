package net.syscon.s4.inst.legalscreens.maintenance;

import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.inst.legals.beans.SentenceAdjustment;

/**
 * Interface OimsatypRepository
 */
public interface OimsatypRepository {
	List<ReferenceCodes> cgfkSentenceAdjustmentDspRecordGroup();

	Integer sentenceAdjustmentDeleteQuery(List<SentenceAdjustment> object);

	List<SentenceAdjustment> sentenceAdjustmentExecuteQuery(SentenceAdjustment object);

	List<ReferenceCodes> cgfkSentenceAdjustmentUsagRecordGroup();

	String sentenceAdjustmentInsertQuery(List<SentenceAdjustment> object);

	Integer sentenceAdjustmentUpdateQuery(List<SentenceAdjustment> insertList);

}

package net.syscon.s4.inst.legalscreens.maintenance;

import java.util.List;

import net.syscon.s4.common.beans.LegalUpdateReasons;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.im.beans.LegalUpdateUsages;

/**
 * Interface OimlegsuRepository
 */
public interface OimlegsuRepository {
	Integer lglUpdUsagesInsertLegalUpdateUsages(List<LegalUpdateUsages> objSearchDao);

	List<LegalUpdateUsages> lglUpdUsagesExecuteQuery(LegalUpdateUsages objSearchDao);

	LegalUpdateUsages lglUpdUsagesDeleteLegalUpdateUsages(List<LegalUpdateUsages> objSearchDao);

	List<LegalUpdateReasons> rgUpdateReasonCodeRecordGroup();

	Integer lglUpdUsagesUpdateLegalUpdateUsages(List<LegalUpdateUsages> objSearchDao);

	List<ReferenceCodes> rgLegalClassRecordGroup();

	LegalUpdateUsages postQueryData(String updateReasonCode);

	Integer deleteKeyDelRec(LegalUpdateUsages objSearchDao);

}

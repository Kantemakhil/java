package net.syscon.s4.inst.legalscreens.maintenance;

import java.util.List;

import net.syscon.s4.common.beans.LegalUpdateReasons;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.im.beans.LegalUpdateUsages;
import net.syscon.s4.im.beans.LegalUpdateUsagesCommitBean;

/**
 * Interface OimlegsuService
 */
public interface OimlegsuService {
	List<LegalUpdateUsages> lglUpdUsagesExecuteQuery(LegalUpdateUsages objSearchDao);

	List<LegalUpdateReasons> rgUpdateReasonCodeRecordGroup();

	LegalUpdateUsages lglUpdUsagesCommit(LegalUpdateUsagesCommitBean commitBean);

	List<ReferenceCodes> rgLegalClassRecordGroup();

	LegalUpdateUsages postQueryData(String updateReasonCode);

	Integer deleteKeyDelRec(LegalUpdateUsages objSearchDao);
}

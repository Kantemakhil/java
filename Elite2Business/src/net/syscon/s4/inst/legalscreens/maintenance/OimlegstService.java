package net.syscon.s4.inst.legalscreens.maintenance;

import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.inst.legalscreens.maintenance.bean.LegalUpdateReasons;
import net.syscon.s4.inst.legalscreens.maintenance.bean.LegalUpdateReasonsCommitBean;

/**
 * Interface OimlegstService
 */
public interface OimlegstService {
	List<ReferenceCodes> rgStatRecordGroup();

	List<ReferenceCodes> rgCatRecordGroup();

	List<LegalUpdateReasons> updateReasonsExecuteQuery(LegalUpdateReasons paremBean);

	List<LegalUpdateReasons> updateReasonsCommit(LegalUpdateReasonsCommitBean commitBean);

	Integer getDeleteRecordOrNot(LegalUpdateReasons searchRecord);

}

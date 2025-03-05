package net.syscon.s4.inst.securitythreatgroups;

import java.util.List;

import net.syscon.s4.common.beans.SecurityThreatGroups;
import net.syscon.s4.common.beans.StgSearchV1;
import net.syscon.s4.common.beans.StgSearchV1CommitBean;

/**
 * Interface OsistgkwService
 */
public interface OsistgkwService {
	List<SecurityThreatGroups> getStgGroupDescription(SecurityThreatGroups paramBean);

	Integer stgSearchV1Commit(StgSearchV1CommitBean commitBean);

	List<StgSearchV1> stgSearchV1ExecuteQuery(StgSearchV1 objStgSearchV1);

}

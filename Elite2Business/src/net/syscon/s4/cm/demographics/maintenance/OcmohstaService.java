package net.syscon.s4.cm.demographics.maintenance;

import java.util.List;

import net.syscon.s4.im.beans.CommunityHeaderStatuses;
import net.syscon.s4.im.beans.CommunityHeaderStatusesCommitBean;

/**
 * Interface OcmohstaService
 */
public interface OcmohstaService {
	Integer comHdrStCommit(CommunityHeaderStatusesCommitBean commitBean);

	List<CommunityHeaderStatuses> comHdrStExecuteQuery(CommunityHeaderStatuses obj);

}

package net.syscon.s4.globalconfiguration;

import java.util.List;

import net.syscon.s4.im.beans.SanctionNotices;
import net.syscon.s4.im.beans.SanctionNoticesCommitBean;

/**
 * Interface OcmsnotiService
 */
public interface OcmsnotiService {
	SanctionNotices sanNotCommit(SanctionNoticesCommitBean commitBean);

	List<SanctionNotices> sanNotExecuteQuery(SanctionNotices objSanctionNotices);

}

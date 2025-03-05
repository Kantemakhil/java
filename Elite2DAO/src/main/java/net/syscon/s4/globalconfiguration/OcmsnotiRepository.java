package net.syscon.s4.globalconfiguration;

import java.util.List;

import net.syscon.s4.im.beans.SanctionNotices;

/**
 * Interface OcmsnotiRepository
 */
public interface OcmsnotiRepository {

	Integer sanNotInsertSanctionNotices(List<SanctionNotices> lstSanctionNotices);

	Integer sanNotUpdateSanctionNotices(List<SanctionNotices> lstSanctionNotices);

	List<SanctionNotices> sanNotExecuteQuery(SanctionNotices objSanctionNotices);

}

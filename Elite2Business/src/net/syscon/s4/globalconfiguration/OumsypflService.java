package net.syscon.s4.globalconfiguration;

import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.beans.SystemProfilesCommitBean;
import net.syscon.s4.im.beans.Dual;

/**
 * Interface OumsypflService
 */
public interface OumsypflService {

	Dual cgwhenNewFormInstance(final String createUserId);

	String sysPflCommit(SystemProfilesCommitBean commitBean);

	List<SystemProfiles> sysPflExecuteQuery(SystemProfiles objSystemProfiles);

	ReferenceCodes cgfkchkSysPflSystemProfil(ReferenceCodes paramBean);

	List<ReferenceCodes> cgfkSysPflProfileTypeRecordGroup();

	List<SystemProfiles> getSystemProfileRecords(SystemProfiles searchBean);

}

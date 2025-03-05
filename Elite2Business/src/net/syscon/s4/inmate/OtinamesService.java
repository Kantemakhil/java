package net.syscon.s4.inmate;

import java.util.List;

import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.beans.SystemProfilesCommitBean;
import net.syscon.s4.im.beans.OffenderTrustAccounts;
import net.syscon.s4.im.beans.VTrustHeader;
import net.syscon.s4.im.beans.VTrustHeaderCommitBean;

/**
 * Interface OtinamesService
 */
public interface OtinamesService {
	List<VTrustHeader> vThaExecuteQuery(VTrustHeader objVTrustHeader);

	List<SystemProfiles> sysPflExecuteQuery(SystemProfiles objSystemProfiles);

	Integer sysPflCommit(SystemProfilesCommitBean commitBean);

	OffenderTrustAccounts cgfkchkVThaVThaOffTaF(OffenderTrustAccounts paramBean);

	Integer vThaCommit(VTrustHeaderCommitBean CommitBean);

}

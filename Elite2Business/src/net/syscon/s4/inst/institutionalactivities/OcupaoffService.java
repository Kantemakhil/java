package net.syscon.s4.inst.institutionalactivities;

import java.util.List;

import net.syscon.s4.im.beans.VOffenderProgramProfiles;
import net.syscon.s4.im.beans.VOffenderProgramProfilesCommitBean;

/**
 * Interface OcupaoffService
 */
public interface OcupaoffService {
	List<VOffenderProgramProfiles> vOffPrgProfilesExecuteQuery(VOffenderProgramProfiles objVOffenderProgramProfiles);

	Integer vOffPrgProfilesCommit(VOffenderProgramProfilesCommitBean commitBean);

}

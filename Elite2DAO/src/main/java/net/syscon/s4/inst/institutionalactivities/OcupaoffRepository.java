package net.syscon.s4.inst.institutionalactivities;

import java.util.List;

import net.syscon.s4.im.beans.VOffenderProgramProfiles;

/**
 * Interface OcupaoffRepository
 */
public interface OcupaoffRepository {
	List<VOffenderProgramProfiles> vOffPrgProfilesExecuteQuery(VOffenderProgramProfiles objVOffenderProgramProfiles);

	List<VOffenderProgramProfiles> postQuery(final VOffenderProgramProfiles object);

}

package net.syscon.s4.inst.movements.housingchanges;

import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.beans.SystemProfilesCommitBean;
import net.syscon.s4.common.beans.VHeaderBlock;
import net.syscon.s4.im.beans.SysDual;
import net.syscon.s4.inst.movementexternal.beans.VHeaderBlockCommitBean;

/**
 * Interface OidehlocService
 */
public interface OidehlocService {
	List<Object> cgwhenNewFormInstance(SysDual paramBean);

	Integer vOffBkgCommit(VHeaderBlockCommitBean commitBean);

	List<VHeaderBlock> vOffBkgExecuteQuery(VHeaderBlock objVHeaderBlock);

	List<SystemProfiles> sysPflExecuteQuery(SystemProfiles objSystemProfiles);

	Integer sysPflCommit(SystemProfilesCommitBean commitBean);

	List<ReferenceCodes> rgAssignmentReasonRecordGroup();
	
	VHeaderBlockCommitBean nonAssocationOffendersList(VHeaderBlockCommitBean commitBean);

}

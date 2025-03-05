package net.syscon.s4.inst.visitsmanagement;

import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.common.beans.VHeaderBlock;
import net.syscon.s4.inst.movementexternal.beans.VHeaderBlockCommitBean;
import net.syscon.s4.inst.visitsmanagement.beans.OffenderRestrictions;
import net.syscon.s4.inst.visitsmanagement.beans.VOffenderRestrictions;
import net.syscon.s4.inst.visitsmanagement.beans.VOffenderRestrictionsCommitBean;

/**
 * Interface OcuprestService
 */
public interface OcuprestService {
	Integer vOffRestCommit(VOffenderRestrictionsCommitBean commitBean);

	List<VOffenderRestrictions> vOffRestExecuteQuery(VOffenderRestrictions object);

	List<ReferenceCodes> rgRestrictionTypeRecordGroup();

	Integer offCommit(VHeaderBlockCommitBean commitBean);

	List<StaffMembers> rgAuthorisedByRecordGroup();

	List<VHeaderBlock> offExecuteQuery(VHeaderBlock objVHeaderBlock);

	OffenderRestrictions offOnCheckDeleteMaster(OffenderRestrictions paramBean);

}

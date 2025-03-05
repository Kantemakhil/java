package net.syscon.s4.inst.visitsmanagement;

import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.common.beans.VHeaderBlock;
import net.syscon.s4.inst.visitsmanagement.beans.OffenderRestrictions;
import net.syscon.s4.inst.visitsmanagement.beans.VOffenderRestrictions;

/**
 * Interface OcuprestRepository
 */
public interface OcuprestRepository {
	List<VOffenderRestrictions> vOffRestExecuteQuery(VOffenderRestrictions object);

	List<ReferenceCodes> rgRestrictionTypeRecordGroup();

	List<StaffMembers> rgAuthorisedByRecordGroup();

	List<VHeaderBlock> offExecuteQuery(VHeaderBlock objVHeaderBlock);

	List<OffenderRestrictions> offOnCheckDeleteMaster(OffenderRestrictions paramBean);

	Integer getOffenderBookId(VHeaderBlock searchRecord);

}

package net.syscon.s4.inst.offenderissuestracking;

import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.im.beans.OmsModules;
import net.syscon.s4.inst.offenderissuestracking.beans.OffenderGrievStaffs;
import net.syscon.s4.inst.offenderissuestracking.beans.OffenderGrievStaffsCommitBean;

/**
 * Interface OiustinvService
 */
public interface OiustinvService {
	List<OffenderGrievStaffs> offenderGrievStaffsCommit(OffenderGrievStaffsCommitBean commitBean);

	List<StaffMembers> offenderGrievStaffsPostQuery(StaffMembers paramBean);

	List<OffenderGrievStaffs> offenderGrievStaffsExecuteQuery(OffenderGrievStaffs objOffenderGrievStaffs);

	List<ReferenceCodes> rgStaffRecordGroup();

	List<OmsModules> createFormGlobals(OmsModules paramBean);

}

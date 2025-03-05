package net.syscon.s4.inst.offenderissuestracking;

import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.im.beans.OmsModules;
import net.syscon.s4.inst.offenderissuestracking.beans.OffenderGrievStaffs;

/**
 * Interface OiustinvRepository
 */
public interface OiustinvRepository {
	Integer offenderGrievStaffsUpdateOffenderGrievStaffs(List<OffenderGrievStaffs> lstOffenderGrievStaffs);

	Integer offenderGrievStaffsDeleteOffenderGrievStaffs(List<OffenderGrievStaffs> lstOffenderGrievStaffs);

	List<StaffMembers> offenderGrievStaffsPostQuery(StaffMembers paramBean);

	List<OffenderGrievStaffs> offenderGrievStaffsExecuteQuery(OffenderGrievStaffs objOffenderGrievStaffs);

	List<ReferenceCodes> rgStaffRecordGroup();

	List<OmsModules> createFormGlobals(OmsModules paramBean);

	Integer offenderGrievStaffsInsertOffenderGrievStaffs(List<OffenderGrievStaffs> lstOffenderGrievStaffs);

}

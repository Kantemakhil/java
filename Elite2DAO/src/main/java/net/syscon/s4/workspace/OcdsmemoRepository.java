package net.syscon.s4.workspace;

import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.common.beans.VHeaderBlock;
import net.syscon.s4.im.beans.OmsModules;
import net.syscon.s4.im.beans.VPimsNameSearch;

/**
 * Interface OcdsmemoRepository
 */
public interface OcdsmemoRepository {
	List<OmsModules> createFormGlobals(OmsModules paramBean);

	List<ReferenceCodes> rgWorkTypeRecordGroup(String caseloadType);

	List<ReferenceCodes> rgWorkSubTypeRecordGroup(String workType, String caseloadType);

	List<StaffMembers> rgStaffRecordGroup();

	List<VPimsNameSearch> getOffDetails(VPimsNameSearch paramBean);

	List<VHeaderBlock> getOffDetails(VHeaderBlock paramBean);

	List<ReferenceCodes> rgSeverityRecordGroup();

	String staffMemoComitt(StaffMembers object);

	String getStaffMessage(StaffMembers object);

}

package net.syscon.s4.workspace;

import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.common.beans.VHeaderBlock;

/**
 * Interface OcdsmemoService
 */
public interface OcdsmemoService {
	List<VHeaderBlock> getOffDetails(VHeaderBlock paramBean);

	List<ReferenceCodes> rgWorkTypeRecordGroup(String caseloadType);

	List<ReferenceCodes> rgWorkSubTypeRecordGroup(String workType, String caseloadType);

	List<StaffMembers> rgStaffRecordGroup();

	List<ReferenceCodes> rgSeverityRecordGroup();

	String staffMemoComitt(StaffMembers object);

	String getStaffMessage(StaffMembers object);

}

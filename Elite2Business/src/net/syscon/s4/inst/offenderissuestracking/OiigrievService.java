package net.syscon.s4.inst.offenderissuestracking;

import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.inst.offenderissuestracking.beans.VGrievanceInquiry;

public interface OiigrievService {
	List<AgencyLocations> rgAgyRecordGroup();

	Integer grieDetCommit(VGrievanceInquiry CommitBean);

	List<VGrievanceInquiry> grieDetExecuteQuery(VGrievanceInquiry objVGrievanceInquiry);

	List<ReferenceCodes> rgLevelRecordGroup();

	List<StaffMembers> rgStaffInvRecordGroup();

	List<StaffMembers> rgStaffAsgRecordGroup();

	List<ReferenceCodes> rgGrieTypeRecordGroup( String user);

	String whenNewRecordInstance(final String userId);

	List<ReferenceCodes> rgGrieReasonCodeRecordGroup(String grievType);

	List<ReferenceCodes> rgGrieTransactionRecordGroup(String grievType);

}

package net.syscon.s4.inst.offenderissuestracking;

import java.math.BigDecimal;
import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.OmsModules;
import net.syscon.s4.inst.offenderissuestracking.beans.VGrievanceInquiry;

public interface OiigrievRepository {
	List<AgencyLocations> rgAgyRecordGroup();

	OmsModules createFormGlobals(OmsModules paramBean);

	List<VGrievanceInquiry> grieDetExecuteQuery(VGrievanceInquiry objVGrievanceInquiry);

	List<ReferenceCodes> rgLevelRecordGroup();

	List<StaffMembers> rgStaffInvRecordGroup();

	List<StaffMembers> rgStaffAsgRecordGroup();

	String whenNewRecordInstance(final String userId);
	
	Long getResponceDays(BigDecimal id);
	
    List<ReferenceCodes> rgGrieTypeRecordGroup(String user);

	List<ReferenceCodes> rgGrievReasonRecordGroup(String grievType);

	List<ReferenceCodes> rgGrieTransactionRecordGroup(String grievType);


}

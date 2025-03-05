package net.syscon.s4.iwp;

import java.math.BigDecimal;
import java.util.List;

import net.syscon.s4.cm.primaryofficeragentassignment.beans.VAssignedOffenders;
import net.syscon.s4.common.beans.Images;
import net.syscon.s4.common.beans.StaffMemberRoles;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.inst.legals.au.StaffWorkAssignmentsV1;

public interface OcipowofRepository {
	List<StaffWorkAssignmentsV1> vswaExecuteQuery(StaffWorkAssignmentsV1 object);

	List<VAssignedOffenders> vAssOffExecuteQuery(VAssignedOffenders object);

	List<StaffMembers> staffExecuteQuery(StaffMembers objStaffMembers);

	List<StaffMemberRoles> gettingStaffPostQuery(StaffMembers data);

	Integer gettingWorkload(StaffMemberRoles searchRecord);

	Integer gettingOffenderStaffPostQuerry(StaffMembers staffMembers);

	Images imageData(Integer offenderBookId);

	Integer gettingHpCountNumber(Integer offenderBookId);

	Integer gettingYCountNumber(Integer offenderBookId);

	Integer gettingACountNumber(Integer offenderBookId);

	BigDecimal gettingLinePostQuerry(StaffWorkAssignmentsV1 element);

}

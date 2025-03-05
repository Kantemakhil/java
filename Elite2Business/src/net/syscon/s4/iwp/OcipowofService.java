package net.syscon.s4.iwp;

import java.util.List;

import net.syscon.s4.cm.primaryofficeragentassignment.beans.VAssignedOffenders;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.inst.legals.au.StaffWorkAssignmentsV1;

public interface OcipowofService {

	List<StaffWorkAssignmentsV1> vswaExecuteQuery(StaffWorkAssignmentsV1 object);

	List<VAssignedOffenders> vAssOffExecuteQuery(VAssignedOffenders object);

	List<StaffMembers> staffExecuteQuery(StaffMembers objStaffMembers);

}

package net.syscon.s4.triggers;

import java.util.List;

import net.syscon.s4.inst.legals.legalorders.WorkAssignments;

public interface WorkAssignmentsTjnService {
	Integer workAssignmentsTjnTgr(List<WorkAssignments> workAssignmentsList, String sqlOperation);
}

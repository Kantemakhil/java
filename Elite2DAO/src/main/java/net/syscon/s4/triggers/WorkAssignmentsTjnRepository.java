package net.syscon.s4.triggers;

import net.syscon.s4.inst.legals.legalorders.WorkAssignments;

public interface WorkAssignmentsTjnRepository {
	WorkAssignments getWorkAssignments(WorkAssignments workAssignments);

	Integer insert(WorkAssignments workAssignments);

	Integer update(WorkAssignments workAssignments);

	Integer delete(WorkAssignments workAssignments);
}

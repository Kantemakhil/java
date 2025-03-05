package net.syscon.s4.inst.workflow.managingworkassignments;

import java.util.List;

import net.syscon.s4.im.beans.OmsModules;

/**
 * Interface OcuwkhtyRepository
 */
public interface OcuwkhtyRepository {

	List<VWorkAssignmentHistory> vWorkAssignmentHistoryExecuteQuery(VWorkAssignmentHistory objVWorkAssignmentHistory);

}

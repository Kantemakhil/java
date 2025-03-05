package net.syscon.s4.inst.schedules;

import java.util.List;

import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.inst.schedules.bean.VOffenderAllSchedules;
import net.syscon.s4.inst.schedules.bean.VOffenderAllSchedulesCommitBean;

/**
 * Interface OiuscinqService
 */
public interface OiuscinqService {
	Integer offSchCommit(VOffenderAllSchedulesCommitBean commitBean);

	List<VOffenderAllSchedules> offSchExecuteQuery(VOffenderAllSchedules object);

	StaffMembers oiuscinqPostInsert(StaffMembers paramBean);

}

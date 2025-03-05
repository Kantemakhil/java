package net.syscon.s4.inst.schedules;

import java.util.List;

import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.im.beans.OmsModules;
import net.syscon.s4.inst.schedules.bean.VOffenderAllSchedules;

/**
 * Interface OiuscinqRepository
 */
public interface OiuscinqRepository {
	OmsModules createFormGlobals(OmsModules paramBean);

	List<VOffenderAllSchedules> offSchExecuteQuery(VOffenderAllSchedules object);

	StaffMembers oiuscinqPostInsert(StaffMembers paramBean);

}

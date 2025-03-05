package net.syscon.s4.globaloffenderrecords;

import net.syscon.s4.inst.schedules.bean.VOffenderAllSchedulesCommitBean;

public interface OcunawrnService {

	VOffenderAllSchedulesCommitBean checkNonAssociationConflicts(VOffenderAllSchedulesCommitBean commitBean);

}

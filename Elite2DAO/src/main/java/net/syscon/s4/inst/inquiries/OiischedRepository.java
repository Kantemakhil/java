package net.syscon.s4.inst.inquiries;

import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.inst.casemanagement.beans.InternalScheduleReasons;
import net.syscon.s4.inst.schedules.bean.VOffenderAllSchedules;

/**
 * Interface OiischedRepository
 */
public interface OiischedRepository {
	List<ReferenceCodes> rgTypeRecordGroup(String scheduleFilter);

	List<VOffenderAllSchedules> offSchExecuteQuery(VOffenderAllSchedules objVOffenderAllSchedules);

	List<InternalScheduleReasons> rgSubtypeRecordGroup(String scheduleFilter, String scheduleType);

}

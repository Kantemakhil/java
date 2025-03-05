package net.syscon.s4.inst.schedules.maintenance;

import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.inst.casemanagement.beans.InternalScheduleReasons;
import net.syscon.s4.inst.schedules.bean.InternalScheduleReasonsCommitBean;

/**
 * Interface OimisreaService
 */
public interface OimisreaService {
	Long intSrKeyDelrec(InternalScheduleReasons paramBean);

	InternalScheduleReasons intSrCommit(InternalScheduleReasonsCommitBean commitBean);

	List<InternalScheduleReasons> intSrExecuteQuery(InternalScheduleReasons objSearchDao);

	List<ReferenceCodes> rgIntSchRsnRecordGroup();

	List<ReferenceCodes> rgIntSchTypeRecordGroup();

}

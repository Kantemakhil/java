package net.syscon.s4.inst.schedules.maintenance;

import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.inst.casemanagement.beans.InternalScheduleReasons;

/**
 * Interface OimisreaRepository
 */
public interface OimisreaRepository {
	Integer intSrUpdateInternalScheduleReasons(List<InternalScheduleReasons> objSearchDao);

	List<InternalScheduleReasons> intSrExecuteQuery(InternalScheduleReasons objSearchDao);

	Long intSrKeyDelrec(InternalScheduleReasons paramBean);

	List<ReferenceCodes> rgIntSchRsnRecordGroup();

	Integer intSrDeleteInternalScheduleReasons(List<InternalScheduleReasons> objSearchDao);

	Integer intSrInsertInternalScheduleReasons(List<InternalScheduleReasons> objSearchDao);

	List<ReferenceCodes> rgIntSchTypeRecordGroup();

}

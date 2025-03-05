package net.syscon.s4.inst.movementexternal;

import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.MovementReasons;
import net.syscon.s4.inst.schedules.bean.VOffenderAllSchedules;
import net.syscon.s4.inst.schedules.bean.VOffenderAllSchedulesCommitBean;

/**
 * Interface OidbstrnService
 * 
 * @author Arkin Software Technologies
 * @version 1.0
 */
public interface OidbstrnService {
	List<AgencyLocations> rgAllAgyLocRecordGroup(String agyLocId);

	List<ReferenceCodes> rgEscortRecordGroup();

	String offAllSchCommit(VOffenderAllSchedulesCommitBean CommitBean);

	List<ReferenceCodes> rgCancelReasonRecordGroup();

	List<VOffenderAllSchedules> offAllSchExecuteQuery(String caseLoad, VOffenderAllSchedules objVOffenderAllSchedules);

	List<AgencyLocations> rgAgyLocRecordGroup(String caseLoadId);

	List<MovementReasons> rgReasonRecordGroup();

}

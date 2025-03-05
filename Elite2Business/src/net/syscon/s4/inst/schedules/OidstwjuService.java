package net.syscon.s4.inst.schedules;

import java.util.Date;
import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.genericservices.CustomException;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.MovementReasons;
import net.syscon.s4.inst.schedules.bean.OffenderIndSchWaitLists;
import net.syscon.s4.inst.schedules.bean.OffenderIndSchWaitListsCommitBean;
import net.syscon.s4.inst.schedules.bean.OffenderIndSchedules;
import net.syscon.s4.inst.schedules.bean.VOffenderAllSchedules;
import net.syscon.s4.inst.schedules.bean.VOffenderAllSchedulesCommitBean;

/**
 * Interface OidstwjuService
 */
public interface OidstwjuService {
	StaffMembers showApprovedDetails(StaffMembers paramBean);

	List<ReferenceCodes> rgEscortRecordGroup();

	List<Object> offSchOnCheckDeleteMaster(String eventId);

	List<VOffenderAllSchedules> offSchExecuteQuery(VOffenderAllSchedules objVOffAllSch);

	List<OffenderIndSchedules> offBkgOnCheckDeleteMaster(OffenderIndSchedules paramBean);

	Integer offSchCommit(VOffenderAllSchedulesCommitBean commitBean) throws CustomException;
	
	String checkNonAssociations(VOffenderAllSchedulesCommitBean commitBean);

	List<AgencyLocations> rgAgencyLocationRecordGroup(String agyLocId);

	List<StaffMembers> rgApprovedByRecordGroup(String caseLoadId);

	Integer offSwlCommit(OffenderIndSchWaitListsCommitBean commitBean);

	List<ReferenceCodes> rgPriorityRecordGroup();

	ReferenceCodes getParentCode(ReferenceCodes paramBean);

	List<ReferenceCodes> rgStatusRecordGroup();

	List<ReferenceCodes> rgCancelReasonRecordGroup();

	List<MovementReasons> rgMoveReasonRecordGroup();

	List<OffenderIndSchWaitLists> offSwlExecuteQuery(OffenderIndSchWaitLists objOffIndSchWait);
	
	Date getCurrentDate();
	
	Integer getStaffId(String userName);
	
	Integer checkScheduleConflict(VOffenderAllSchedulesCommitBean offSch);

	Integer chkNonAssociation(VOffenderAllSchedulesCommitBean commitBean);

}

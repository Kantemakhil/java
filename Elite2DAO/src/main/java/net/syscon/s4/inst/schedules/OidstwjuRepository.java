package net.syscon.s4.inst.schedules;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.MovementReasons;
import net.syscon.s4.im.beans.OffenderNaDetails;
import net.syscon.s4.im.beans.OffenderNonAssociations;
import net.syscon.s4.inst.schedules.bean.OffenderIndSchWaitLists;
import net.syscon.s4.inst.schedules.bean.OffenderIndSchedules;
import net.syscon.s4.inst.schedules.bean.VOffenderAllSchedules;
import net.syscon.s4.inst.schedules.bean.VOffenderAllSchedulesCommitBean;

/**
 * Interface OidstwjuRepository
 */
public interface OidstwjuRepository {
	List<Object> offSchOnCheckDeleteMaster(String eventId);

	List<ReferenceCodes> rgEscortRecordGroup();

	Integer offSwlUpdateOffenderIndSchWaitLists(List<OffenderIndSchWaitLists> lstOffIndSchWait);

	List<VOffenderAllSchedules> offSchExecuteQuery(VOffenderAllSchedules objVOffAllSch);

	StaffMembers showApprovedDetails(StaffMembers paramBean);

	ReferenceCodes getParentCode(ReferenceCodes paramBean);

	Integer offSchInsertVOffenderAllSchedules(List<OffenderIndSchedules> lstVOffAllSch);

	Integer offSchDeleteVOffenderAllSchedules(List<OffenderIndSchedules> lstVOffAllSch);

	Integer offSwlInsertOffenderIndSchWaitLists(List<OffenderIndSchWaitLists> lstOffIndSchWait);

	Integer offSwlDeleteOffenderIndSchWaitLists(List<OffenderIndSchWaitLists> lstOffIndSchWait);

	SystemProfiles getProfileValue(SystemProfiles paramBean);

	List<AgencyLocations> rgAgencyLocationRecordGroup(String agyLocId);

	List<StaffMembers> rgApprovedByRecordGroup(String caseLoadId);

	List<ReferenceCodes> rgPriorityRecordGroup();

	List<ReferenceCodes> rgStatusRecordGroup();

	List<ReferenceCodes> rgCancelReasonRecordGroup();

	Integer offSchUpdateVOffenderAllSchedules(List<OffenderIndSchedules> lstVOffAllSch);

	List<OffenderIndSchedules> offBkgOnCheckDeleteMaster(OffenderIndSchedules paramBean);

	List<MovementReasons> rgMoveReasonRecordGroup();

	List<OffenderIndSchWaitLists> offSwlExecuteQuery(OffenderIndSchWaitLists objOffIndSchWait);

	Date getCurrentDate();

	Integer offSchPreInsert();

	Integer createSchedule(VOffenderAllSchedules vOffAllSch);

	Integer getStaffId();

	Integer checkScheduleConflict(VOffenderAllSchedulesCommitBean offSch);

	Integer offSchUpdateVOffenderAllSchedulesStatus(List<VOffenderAllSchedules> lstOffAllSch);
	
	Integer insNotification(OffenderIndSchedules offSch);
	
	Integer delNotification(OffenderIndSchedules offSch);
	
	Integer chkNonAssociation(OffenderIndSchedules offSch);
	
    Integer chkClassification(OffenderIndSchedules offSch);

	List<OffenderNonAssociations> checkNonAssociations(VOffenderAllSchedules vOffSch);

	List<VOffenderAllSchedules> checkOffenderScheduleConflicts(BigDecimal offenderBookId,
			VOffenderAllSchedules vOffSch);

	List<OffenderNaDetails> getNonAssociationOffenderListSameLocation(Integer offenderBookId);

	List<VOffenderAllSchedules> checkOffenderScheduleConflictsSameFacility(BigDecimal nsOffenderBookId,
			VOffenderAllSchedules vOffSch);
	

}

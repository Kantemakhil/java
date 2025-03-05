package net.syscon.s4.cm.weeklyactivityplans;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.common.beans.VHeaderBlock;
import net.syscon.s4.inst.schedules.bean.VOffenderAllSchedules;

public interface OweacplnRepository {

	List<WeeklyActivityPlans> getWeeklyActivity(WeeklyActivityPlans searchBean);

	Integer emDetailsInsertData(List<OffenderEmTagDetails> insertList);

	Integer emDetailsUpdateData(List<OffenderEmTagDetails> updateList);

	List<OffenderEmTagDetails> retrieveEmDetails(OffenderEmTagDetails searchBean);

	Integer weeklyActivityCommitInsertData(List<WeeklyActivityPlans> insertList);

	Integer weeklyActivityCommitUpdateData(List<WeeklyActivityPlans> updateList);

	Integer weeklyActivityDeleteData(List<WeeklyActivityPlans> updateList);

	Integer weeklyActivityHtyCommitInsertData(List<WeeklyActivityPlansHty> insertList);

	List<WeeklyActivityPlansHty> getWeeklyActivityHty(WeeklyActivityPlansHty searchBean);
	
	Integer weeklyActivityCommentInsertList(List<WeeklyActivityPlansHty> insertList);
	
	Integer weeklyActivityCommentUpdateList(List<WeeklyActivityPlansHty> updateList);
	
    VHeaderBlock getClientDetails(Long offenderBookId, String userId);
    
    String getWapComment(WeeklyActivityPlansHty bean);

	BigDecimal getMaxVersionNo(WeeklyActivityPlansHty weeklyActivityPlansHty);

	List<WeeklyActivityPlans> getWeeklyActivityHtyMaxData(WeeklyActivityPlansHty searchBean);

	String getModeOfTransportDesc(String modeOfTransport);
	
	BigDecimal getMaxHtyVersion(WeeklyActivityPlans searchBean);

	Integer weeklyActivityCommitUpdateParentData(List<WeeklyActivityPlansHty> insertList);
	
	List<WeeklyActivityPlans> getPreviousWeekData(Date previousWeekStartDate,Date currentWeekStartDate,BigDecimal offenderBookId);
	
	Integer copyOverCommit(List<WeeklyActivityPlans> insertList);
	
	List<StaffMembers> userDetails(String userId);

	String getActivityDescriptionValue(String  activityNew);

	List<VOffenderAllSchedules> getWapManualActivities(VOffenderAllSchedules obj);
	
	List<VOffenderAllSchedules> getWapManualActivitiesBasedOnEventDate(VOffenderAllSchedules obj, List<Date> eventDates);

	List<WeeklyActivityPlans> getActiveWapList(Long programInstanceId, Long phaseInstanceId, Long crsSchId);
	
	BigDecimal getMaxHtyVersionData(WeeklyActivityPlans searchBean);

	Integer getWeeklyActivityHtyDelete(List<WeeklyActivityPlans> deleteList);

	BigDecimal getHistoryTableMaxVersion(WeeklyActivityPlans searchBean);
}

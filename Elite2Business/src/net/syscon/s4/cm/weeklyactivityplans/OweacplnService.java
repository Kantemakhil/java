package net.syscon.s4.cm.weeklyactivityplans;

import java.math.BigDecimal;
import java.util.List;

public interface OweacplnService {

	List<WeeklyActivityPlans> getWeeklyActivity(WeeklyActivityPlans searchBean);

	List<OffenderEmTagDetails> saveEmDetails(OffenderEmTagDetailsCommitBean commitBean);

	List<OffenderEmTagDetails> retrieveEmDetails(OffenderEmTagDetails searchBean);

	List<WeeklyActivityPlans> weeklyActivityCommit(WeeklyActivityPlansCommitBean commitBean);

	List<WeeklyActivityPlansHty> weeklyActivityHtyCommit(WeeklyActivityPlansHtyCommitBean commitBean);

	List<WeeklyActivityPlansHty> getWeeklyActivityHty(WeeklyActivityPlansHty searchBean);
	
	Integer weeklyActivityCommentCommit(WeeklyActivityPlansHtyCommitBean commitBean);

	List<OcrwapstReportBean> printReportForStaff(OcrwapstReportBean commitBean);

	List<OcrwapstReportBean> printReportForOffender(OcrwapstReportBean commitBean);

	 String getWapComment(WeeklyActivityPlansHty bean);

	List<WeeklyActivityPlans> getWeeklyActivityHtyMaxData(WeeklyActivityPlansHty searchBean);
	
	BigDecimal getMaxHtyVersion(WeeklyActivityPlans searchBean);

	Integer copyOverPreviousWeekData(WeeklyActivityPlans searchBean);

	String populateLoggedStaffName(String userName);
	
	BigDecimal getMaxHtyVersionData(WeeklyActivityPlans searchBean);
}

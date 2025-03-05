package net.syscon.s4.iwp;
import java.util.List;


import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.inst.legals.beans.CommonLov;
import net.syscon.s4.inst.legals.beans.OffenderProceedings;
import net.syscon.s4.inst.legals.beans.OffenderProceedingsCommitBean;
import net.syscon.s4.inst.legals.beans.OffenderSentConditions;
import net.syscon.s4.inst.legals.beans.OffenderSentConditionsCommitBean;
import net.syscon.s4.inst.schedules.bean.CourtEvents;
/**
 * Interface OcdenforService 
 */
public interface OcdenforService  {
	
	List<AgencyLocations> rgAgyLocsRecordGroup(String proceedingType) ;
	
	List<ReferenceCodes> rgTeamResponsibleRecordGroup(String userId, String agylocId);
	
	List<ReferenceCodes> rgStaffResponsibleRecordGroup(String caseloadId, String teamResponsible);
	
	List<OffenderProceedings> actionsExecuteQuery(OffenderProceedings searchBean);
	
	Integer actionsCommit(OffenderProceedingsCommitBean commitBean);
	
	Long scheduleConflict(CourtEvents searchBean);

	List<OffenderSentConditions> getConditionTypeGridData(OffenderSentConditions condition);

	Integer conditionProceedSave(OffenderSentConditionsCommitBean commitBean);

	String getPersutHideShowValue(String code);

	List<CommonLov> getProgram();
	
}

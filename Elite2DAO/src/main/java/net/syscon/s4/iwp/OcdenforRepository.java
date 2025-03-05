package net.syscon.s4.iwp;

import java.math.BigDecimal;
import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.inst.legals.beans.OffenderProceedings;
import net.syscon.s4.inst.legals.beans.OffenderSentConditions;
import net.syscon.s4.inst.schedules.bean.CourtEvents;
/**
 * Interface OcdenforRepository
 */
public interface OcdenforRepository {
	List<AgencyLocations> rgAgyLocsRecordGroup(String proceedingType);
	
	List<ReferenceCodes> rgTeamResponsibleRecordGroup(String userId, String agylocId);
	
	List<ReferenceCodes> rgStaffResponsibleRecordGroup(String caseloadId, String teamResponsible);
	
	List<OffenderProceedings> actionsExecuteQuery(OffenderProceedings searchBean);
	
	Integer insertCourtActions(List<OffenderProceedings> insertList);
	
	Integer updateCourtActions(List<OffenderProceedings> updateList);
	
	BigDecimal getNextProceedingId();
	
	Integer getScheduleCount(CourtEvents object);

	List<OffenderSentConditions> getConditionTypeGridData(OffenderSentConditions condition);

	BigDecimal getNextOffProceedingCondId();
	
	Integer insertOffenderProceedingCondition(List<OffenderSentConditions> insertList);

	List<OffenderSentConditions> getExistingOffenderProceedingData(OffenderSentConditions offenderSentConditions);

	Integer deleteExistingOffenderProceedingData(List<OffenderSentConditions> resultData);

	String getPersutHideShowValue(String code);
	
}

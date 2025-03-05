package net.syscon.s4.inst.schedules;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import net.syscon.s4.common.beans.VHeaderBlock;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.MovementReasons;
import net.syscon.s4.im.beans.Offenders;
import net.syscon.s4.im.beans.ReferenceCodes;
import net.syscon.s4.inst.casemanagement.beans.WorkFlowLogs;
import net.syscon.s4.inst.casemanagement.beans.WorkFlows;
import net.syscon.s4.inst.schedules.bean.OffenderReleaseDetailKeyDatesBean;
import net.syscon.s4.inst.schedules.bean.OffenderReleaseDetails;

/**
 * Interface OidrelscRepository
 */
public interface OidrelscRepository {
	List<MovementReasons> rgMovementReasonsRecordGroup();

	Object checkOffAgencyLocation(Offenders paramBean);

	List<WorkFlows> insWorkFlows(WorkFlows paramBean);

	List<AgencyLocations> rgAgyLocationsRecordGroup(String caseloadId);

	Integer offRelDetUpdateOffenderReleaseDetails(List<OffenderReleaseDetails> list);

	List<OffenderReleaseDetails> offRelDetExecuteQuery(OffenderReleaseDetails object);

	Long ctlWhenValidateRecord(OffenderReleaseDetails object);

	List<WorkFlows> updWorkFlows(WorkFlows paramBean);

	Integer offRelDetInsertOffenderReleaseDetails(List<OffenderReleaseDetails> list);

	List<WorkFlows> verificationButton(WorkFlows paramBean);

	Long updWorkFlows(WorkFlowLogs paramBean);

	WorkFlows insertWorkFlowEnt(WorkFlows paramBean);

	WorkFlows getMaxFlowId(WorkFlows paramBean);

	List<ReferenceCodes> rgDateTypeRecordGroup();

	//VHeaderBlock getOffDetailsByBookId(VHeaderBlock objSearchDao);

	BigDecimal insertWorkFlowEntInsert(WorkFlows paramBean);

	Long getCountCursor(WorkFlows paramBean);

	Long getWorkFlowInsertSequence();

	Integer insertIntoWorkFlows(WorkFlows workFlow);

	Integer insertIntoWorkFlowLogs(WorkFlowLogs workFlow);

	Integer chkWorkFlows(WorkFlows paramBean);

	List<VHeaderBlock> getOffenderList(VHeaderBlock paramBean);

	String getOffIdDisplay(Long offenderBookId,String userId);
	
	BigDecimal getEventId();

	List<OffenderReleaseDetailKeyDatesBean> offRelDetLegalExecuteQuery(OffenderReleaseDetails searchBean);
	
	List<OffenderReleaseDetailKeyDatesBean>  getOffenderKeyDatesBasedOnBookId(BigDecimal offenderBookId);

	List<ReferenceCodes> getKeyDatesDataLovData(String domainName);
	
	Date getReleaseDate(Long offenderBookId);

	WorkFlowLogs getWorkFLowMaxID(Long objectId,Long objectSeq);
	
	Integer updateCommentText(OffenderReleaseDetails offenderReleaseDetails);
}

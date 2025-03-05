package net.syscon.s4.inst.schedules;

import java.util.Date;
import java.util.List;

import net.syscon.s4.common.beans.VHeaderBlock;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.MovementReasons;
import net.syscon.s4.im.beans.ReferenceCodes;
import net.syscon.s4.inst.casemanagement.beans.WorkFlows;
import net.syscon.s4.inst.schedules.bean.OffenderReleaseDetails;
import net.syscon.s4.inst.schedules.bean.OffenderReleaseDetailsCommitBean;

/**
 * Interface OidrelscService
 */
public interface OidrelscService {
	List<MovementReasons> rgMovementReasonsRecordGroup();

	List<OffenderReleaseDetails> offRelDetExecuteQuery(OffenderReleaseDetails object);

	Long ctlWhenValidateRecord(OffenderReleaseDetails paramBean);

	List<OffenderReleaseDetails> offRelDetCommit(OffenderReleaseDetailsCommitBean commitBean);

	List<AgencyLocations> rgAgyLocationsRecordGroup(String caseloadId);

	List<WorkFlows> verificationButton(WorkFlows paramBean);

	List<WorkFlows> insWorkFlows(WorkFlows paramBean);

	List<WorkFlows> updWorkFlows(WorkFlows paramBean);

	List<ReferenceCodes> rgDateTypeRecordGroup();

	List<VHeaderBlock> getOffenderList(VHeaderBlock paramBean);

	List<OffenderReleaseDetails> offRelDetLegalExecuteQuery(OffenderReleaseDetails searchBean);

	List<ReferenceCodes> getKeyDatesDataLovData(String domainName);

	List<ReferenceCodes> rgKeyDatesRecordGroup();
	
	Date getReleaseDate(Long offenderBookId);

	String getErdHideShowValue(String code);
	
	Integer updateCommentText(OffenderReleaseDetails offenderReleaseDetails);

}

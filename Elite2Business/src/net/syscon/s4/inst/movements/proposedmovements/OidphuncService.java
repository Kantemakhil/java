package net.syscon.s4.inst.movements.proposedmovements;

import java.math.BigDecimal;
import java.util.List;

import net.syscon.s4.im.beans.LivingUnits;
import net.syscon.s4.inst.casemanagement.beans.InternalScheduleReasons;
import net.syscon.s4.inst.movements.proposedmovements.beans.OffenderProposedIntlocs;
import net.syscon.s4.inst.movements.proposedmovements.beans.OffenderProposedIntlocsCommitBean;
import net.syscon.s4.inst.transportation.maintenance.OffenderLocChngDtls;

/**
 * Interface OidphuncService
 */
public interface OidphuncService {
	
	List<LivingUnits> rgLevelTwoRecordGroup(BigDecimal livingUnitId);

	List<LivingUnits> rgLevelOneRecordGroup(String agyLocId);

	List<LivingUnits> rgLevelThreeRecordGroup(BigDecimal livingUnitId);

	List<LivingUnits> rgFromLivUnitRecordGroup();
	
	List<InternalScheduleReasons> rgTypeLivUnitRecordGroup();
	
	List<InternalScheduleReasons> rgReasonLivUnitRecordGroup(String internalScheduleType);

	List<LivingUnits> rgLevelFourRecordGroup(BigDecimal livingUnitId);

	List<OffenderProposedIntlocs> propMoveExecuteQuery(OffenderProposedIntlocs objOffenderProposedIntlocs);

	Integer propMoveCommit(OffenderProposedIntlocsCommitBean CommitBean);

	OffenderLocChngDtls getStatuses(OffenderProposedIntlocs searchBean);

	OffenderProposedIntlocs checkNonAssociationAndSecurity(List<OffenderProposedIntlocs> listObj);

}

package net.syscon.s4.inst.movements.proposedmovements;

import java.math.BigDecimal;
import java.util.List;

import net.syscon.s4.im.beans.LivingUnits;
import net.syscon.s4.inst.casemanagement.beans.InternalScheduleReasons;
import net.syscon.s4.inst.movements.proposedmovements.beans.OffenderProposedIntlocs;

/**
 * Interface OidphuncRepository
 */
public interface OidphuncRepository {
	List<LivingUnits> rgLevelTwoRecordGroup(BigDecimal livingUnitId);

	List<LivingUnits> rgLevelOneRecordGroup(String agyLocId);

	List<LivingUnits> rgLevelThreeRecordGroup(BigDecimal livingUnitId);

	Integer propMoveInsertOffenderProposedIntlocs(List<OffenderProposedIntlocs> lstOffenderProposedIntlocs);

	List<LivingUnits> rgFromLivUnitRecordGroup();

	List<InternalScheduleReasons> rgTypeLivUnitRecordGroup();
	
	List<InternalScheduleReasons> rgReasonLivUnitRecordGroup(String internalScheduleType);
	
	List<LivingUnits> rgLevelFourRecordGroup(BigDecimal livingUnitId);

	List<OffenderProposedIntlocs> propMoveExecuteQuery(OffenderProposedIntlocs objOffenderProposedIntlocs);

	Integer propMoveUpdateOffenderProposedIntlocs(List<OffenderProposedIntlocs> lstOffenderProposedIntlocs);

	List<LivingUnits> getNodesCur(Long toLivingUnitId);

}

package net.syscon.s4.inst.incidentsoic;

import java.math.BigDecimal;
import java.util.List;

import net.syscon.s4.common.beans.OffenderOicSanctions;
import net.syscon.s4.common.beans.OicOffences;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.im.incidentsoic.beans.OicHearingResults;
import net.syscon.s4.inst.incidentsoic.maintenance.OffenderOicAppealIncidents;
import net.syscon.s4.inst.incidentsoic.maintenance.OffenderOicAppealPenalties;
import net.syscon.s4.inst.incidentsoic.maintenance.OffenderOicAppeals;

/**
 * Interface OidoicapRepository
 */
public interface OidoicapRepository {

	List<StaffMembers> rgHeardByRecordGroup();

	List<OicHearingResults> rgHearingOffencesRecordGroup(int offenderBookingId);

	List<OffenderOicAppealPenalties> rgOicSeqLogRecordGroup(OffenderOicAppealPenalties searchRecord);

	List<OffenderOicAppeals> offOicaExecuteQuery(OffenderOicAppeals searchRecord);

	List<OffenderOicAppealIncidents> offOicaiExecuteQuery(OffenderOicAppealIncidents searchRecord);

	List<OffenderOicAppealPenalties> offOicapExecuteQuery(OffenderOicAppealPenalties searchRecord);

	List<OicHearingResults> offOicaiPostQuery(OffenderOicAppealIncidents searchRecord);

	Integer offOicaInsertOffenderOicAppeals(List<OffenderOicAppeals> lstOffenderOicAppeals);

	Long offOicaPreInsert();

	Integer offOicaUpdateOffenderOicAppeals(List<OffenderOicAppeals> lstOffenderOicAppealPenalties);

	Integer offOicaDeleteOffenderOicAppeals(List<OffenderOicAppeals> lstOffenderOicAppeals);

	int onAppealCheckDeleteMaster(Long oicApprealId);

	Integer offOicaiInsertoffOffenderOicAppealIncidents(List<OffenderOicAppealIncidents> lstOffenderOicAppealIncidents);

	Integer offOicaiUpdateOffenderOicAppealIncidents(List<OffenderOicAppealIncidents> lstOffenderOicAppealIncidents);

	Integer offOicaiDeleteOffenderOicAppealIncidents(List<OffenderOicAppealIncidents> lstOffenderOicAppealIncidents);

	Integer offOicapInsertOffenderOicAppealPenalties(OffenderOicAppealPenalties lstOffenderOicAppealPenalties);

	Integer offOicapUpdateOffenderOicAppealPenalties(List<OffenderOicAppealPenalties> lstOffenderOicAppeals);

	Integer offOicapDeleteOffenderOicAppealPenalties(List<OffenderOicAppealPenalties> lstOffenderOicAppealPenalties);

	Integer offOicapPreInsertOffenderOicAppealPenalties(BigDecimal offenderBookingId);

	int onOicAppealPenaltiesCheckDeleteMaster(OffenderOicAppealPenalties deleteRecord);

	int offOicaiOnCheckDeleteMaster(OffenderOicAppealIncidents paramBean);

	OicOffences oidoicapWhenValidateRecord(OicOffences paramBean);

	List<OffenderOicSanctions> populatePenalty(OffenderOicSanctions paramBean);

	ReferenceCodes offOicapPostQuery(ReferenceCodes paramBean);

	String butOriginalPenalty(OffenderOicAppealIncidents searchBean);

	String getoffencedetails(OffenderOicAppealIncidents searchBean);

	OicOffences getOicOffenceCodeCur(Integer oicOffenceId);
}

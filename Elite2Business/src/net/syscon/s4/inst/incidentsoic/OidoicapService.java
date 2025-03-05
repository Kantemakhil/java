package net.syscon.s4.inst.incidentsoic;

import java.util.List;

import net.syscon.s4.common.beans.OicOffences;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.im.incidentsoic.beans.OicHearingResults;
import net.syscon.s4.inst.incidentsoic.maintenance.OffenderOicAppealIncidents;
import net.syscon.s4.inst.incidentsoic.maintenance.OffenderOicAppealIncidentsCommitBean;
import net.syscon.s4.inst.incidentsoic.maintenance.OffenderOicAppealPenalties;
import net.syscon.s4.inst.incidentsoic.maintenance.OffenderOicAppealPenaltiesCommitBean;
import net.syscon.s4.inst.incidentsoic.maintenance.OffenderOicAppeals;
import net.syscon.s4.inst.incidentsoic.maintenance.OffenderOicAppealsCommitBean;

/**
 * Interface OidoicapService
 */
public interface OidoicapService {

	List<StaffMembers> rgHeardByRecordGroup();

	List<OicHearingResults> rgHearingOffencesRecordGroup(int offenderBookingId);

	List<OffenderOicAppealPenalties> rgOicSeqLogRecordGroup(OffenderOicAppealPenalties searchRecord);

	List<OffenderOicAppeals> offOicaExecuteQuery(OffenderOicAppeals searchReord);

	List<OffenderOicAppealIncidents> offOicaiExecuteQuery(OffenderOicAppealIncidents serachRecord);

	List<OffenderOicAppealPenalties> offOicapExecuteQuery(OffenderOicAppealPenalties searchRecord);

	Integer offOicaCommit(OffenderOicAppealsCommitBean CommitBean);

	Integer offOicaiCommit(OffenderOicAppealIncidentsCommitBean CommitBean);

	Integer offOicapCommit(OffenderOicAppealPenaltiesCommitBean CommitBean);

	String butOriginalPenalty(OffenderOicAppealIncidents searchBean);

	String getoffencedetails(OffenderOicAppealIncidents searchBean);

	OicOffences getOicOffenceCodeCur(Integer oicOffenceId);

}

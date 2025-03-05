package net.syscon.s4.inst.casemanagement;
import java.util.List;

import net.syscon.s4.common.beans.OffenderBillingProfiles;
import net.syscon.s4.common.beans.OffenderExternalMovements;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.inst.casemanagement.beans.OffenderImprisonStatuses;
import net.syscon.s4.inst.casemanagement.beans.OffenderImprisonStatusesCommitBean;
/**
 * Interface OidistatService 
 */
public interface OidistatService  {
	Integer chkImpDate(OffenderExternalMovements paramBean);

	List<AgencyLocations> rgAgyLocIdRecordGroup(String caseloadId);

	List<ReferenceCodes> rgImprisonmentStaRecordGroup();

	OffenderImprisonStatuses offImpsPostQuery(OffenderImprisonStatuses paramBean);

	OffenderBillingProfiles processBillProfile(OffenderBillingProfiles paramBean);

	Integer offImpsCommit(OffenderImprisonStatusesCommitBean commitBean);

	List<OffenderImprisonStatuses> offImpsExecuteQuery(OffenderImprisonStatuses objOffImpStat);

	OffenderImprisonStatuses offBkgOnCheckDeleteMaster(OffenderImprisonStatuses paramBean);

    Integer offImpsPreInsert(OffenderImprisonStatuses paramBean);

	Integer chkImpDateEffectiveTime(OffenderExternalMovements paramBean);
	
	Integer offImpsUpdateCommit(OffenderImprisonStatusesCommitBean commitBean);
}

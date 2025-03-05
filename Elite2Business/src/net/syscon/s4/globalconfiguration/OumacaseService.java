package net.syscon.s4.globalconfiguration;

import java.util.List;

import net.syscon.s4.common.beans.CaseloadAgencyLocations;
import net.syscon.s4.common.beans.Caseloads;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.CaseloadAgencyLocationsCommitBean;
import net.syscon.s4.im.beans.CaseloadsCommitBean;

/**
 * Interface OumacaseService
 */
public interface OumacaseService {
	Integer alCommit(CaseloadAgencyLocationsCommitBean commitBean);

	List<Caseloads> commissaryTrustRgRecordGroup();

	List<AgencyLocations> alAgyLocIdRgRecordGroup();

	List<Caseloads> communityTrustRgRecordGroup();

	List<Caseloads> payrollTrustRgRecordGroup();

	List<Caseloads> executeQuery(Caseloads objCaseloads);

	List<ReferenceCodes> typeRgRecordGroup();

	List<CaseloadAgencyLocations> onCheckDeleteMaster(CaseloadAgencyLocations paramBean);

	Integer csldCommit(CaseloadsCommitBean commitBean);

	List<Caseloads> trustCommissaryRgRecordGroup();

	List<CaseloadAgencyLocations> alExecuteQuery(CaseloadAgencyLocations clAgencyLocations);

	List<Object> checkAgency(CaseloadAgencyLocations paramBean);
}

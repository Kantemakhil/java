package net.syscon.s4.globalconfiguration;

import java.util.List;

import net.syscon.s4.common.beans.CaseloadAgencyLocations;
import net.syscon.s4.common.beans.Caseloads;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.im.beans.AgencyLocations;

/**
 * Interface OumacaseRepository
 */
public interface OumacaseRepository {

	List<AgencyLocations> alAgyLocIdRgRecordGroup();

	Integer updateCaseloads(List<Caseloads> lstCaseloads);

	Integer insertCaseloadAgencyLocations(List<CaseloadAgencyLocations> lstClAgencyLoc);

	Integer deleteCaseloadAgencyLocations(List<CaseloadAgencyLocations> lstClAgencyLoc);

	List<Caseloads> trustCommissaryRgRecordGroup();

	List<Caseloads> commissaryTrustRgRecordGroup();

	List<Caseloads> communityTrustRgRecordGroup();

	List<Caseloads> payrollTrustRgRecordGroup();

	Integer insertCaseloads(List<Caseloads> lstCaseloads);

	Integer updateCaseloadAgencyLocations(List<CaseloadAgencyLocations> lstClAgencyLoc);

	List<Caseloads> executeQuery(Caseloads objCaseloads);

	List<ReferenceCodes> typeRgRecordGroup();

	List<Object> checkAgency(CaseloadAgencyLocations paramBean);

	List<CaseloadAgencyLocations> onCheckDeleteMaster(CaseloadAgencyLocations paramBean);

	List<CaseloadAgencyLocations> alExecuteQuery(CaseloadAgencyLocations lstClAgencyLoc);

	Integer checkCaseloadSeqExistorNot(String seqName);

	Integer generateCaseloadSeq(String seqName);
}

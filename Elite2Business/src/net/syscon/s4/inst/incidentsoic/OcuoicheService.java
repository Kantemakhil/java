package net.syscon.s4.inst.incidentsoic;

import java.util.List;

import net.syscon.s4.common.beans.AgencyInternalLocations;
import net.syscon.s4.common.beans.CaseloadAgencyLocations;
import net.syscon.s4.common.beans.OicOffences;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.im.beans.VOicIncidents;
import net.syscon.s4.im.incidentsoic.beans.OicHearingResults;
import net.syscon.s4.im.incidentsoic.beans.OicHearingResultsCommitBean;
import net.syscon.s4.im.incidentsoic.beans.OicHearings;
import net.syscon.s4.im.incidentsoic.beans.OicHearingsCommitBean;

/**
 * Interface OcuoicheBusiness
 */
public interface OcuoicheService {
	List<ReferenceCodes> rgOffenceCodeRecordGroup();

	List<Object> ocuoicheKeyDelrecoicSancCur(String oicHearingId, String resultSeq);

	List<OicHearings> oicHearSearchOicHearings(OicHearings objOicHearings);

	Integer oicHearDeleteOicHearings(List<OicHearings> lstOicHearings);

	List<Object> oicHearOnCheckDeleteMasteroicHearResCur(String oichearingid);

	CaseloadAgencyLocations oicHearPreQuery(CaseloadAgencyLocations paramBean);

	Integer oicHearResDeleteOicHearingResults(List<OicHearingResults> lstOicHearingResults);

	List<OicHearingResults> oicHearResSearchOicHearingResults(OicHearingResults objOicHearingResults);

	Integer oicHearResUpdateOicHearingResults(List<OicHearingResults> lstOicHearingResults);

	Integer oicHearResCommit(OicHearingResultsCommitBean commitBean);

	Integer oicHearResInsertOicHearingResults(List<OicHearingResults> lstOicHearingResults);

	Integer oicHearUpdateOicHearings(List<OicHearings> lstOicHearings);

	Integer oicHearInsertOicHearings(List<OicHearings> lstOicHearings);

	List<OicHearings> oicHearCommit(OicHearingsCommitBean commitBean);

	List<AgencyInternalLocations> rgInternalLocationsRecordGroup(String caseloadId);

	List<StaffMembers> rgAgyIncpStaffIdRecordGroup(StaffMembers caseloadId);

	List<ReferenceCodes> rgHearingTypeRecordGroup();

	List<ReferenceCodes> rgPleaRecordGroup();

	List<OicOffences> rgIncidentChargesRecordGroup(VOicIncidents searchBean);

	List<ReferenceCodes> rgFindingRecordGroup();

}

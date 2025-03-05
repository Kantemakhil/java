package net.syscon.s4.inst.incidentsoic;

import java.util.List;

import net.syscon.s4.common.beans.AgencyInternalLocations;
import net.syscon.s4.common.beans.CaseloadAgencyLocations;
import net.syscon.s4.common.beans.OicOffences;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.im.beans.VOicIncidents;
import net.syscon.s4.im.incidentsoic.beans.OicHearingResults;
import net.syscon.s4.im.incidentsoic.beans.OicHearings;

/**
 * Interface OcuoicheRepository
 */
public interface OcuoicheRepository {
	List<OicOffences> rgIncidentChargesRecordGroup(VOicIncidents searchBean);

	List<ReferenceCodes> rgHearingTypeRecordGroup();

	List<AgencyInternalLocations> rgInternalLocationsRecordGroup(String caseloadId);

	List<ReferenceCodes> rgPleaRecordGroup();

	List<ReferenceCodes> rgFindingRecordGroup();

	List<StaffMembers> rgAgyIncpStaffIdRecordGroup(StaffMembers caseloadId);

	List<Object> oicHearOnCheckDeleteMasteroicHearResCur(String oichearingid);

	List<OicHearings> oicHearSearchOicHearings(OicHearings objOicHearings);

	List<Object> ocuoicheKeyDelrecoicSancCur(String oichearingid, String resultSeq);

	Integer oicHearDeleteOicHearings(List<OicHearings> lstOicHearings);

	CaseloadAgencyLocations oicHearPreQuery(CaseloadAgencyLocations paramBean);

	Integer oicHearResDeleteOicHearingResults(List<OicHearingResults> lstOicHearingResults);

	List<OicHearingResults> oicHearResSearchOicHearingResults(OicHearingResults objOicHearingResults);

	OicOffences agyInciChgPostQuery(final OicOffences paramBean);

	Integer oicHearResUpdateOicHearingResults(List<OicHearingResults> lstOicHearingResults);

	Integer oicHearResInsertOicHearingResults(List<OicHearingResults> lstOicHearingResults);

	Integer oicHearUpdateOicHearings(List<OicHearings> lstOicHearings);

	Integer oicHearInsertOicHearings(List<OicHearings> lstOicHearings);

	List<ReferenceCodes> rgOffenceCodeRecordGroup();

	StaffMembers getLastNameOfStaffId(StaffMembers staffMember);
}

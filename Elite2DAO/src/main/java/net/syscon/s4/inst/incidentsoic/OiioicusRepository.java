package net.syscon.s4.inst.incidentsoic;

import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.im.beans.SystemProfiles;
import net.syscon.s4.im.incidentsoic.beans.VOffenderOicSanctions;
import net.syscon.s4.im.incidentsoic.beans.VOicHearingResults;
import net.syscon.s4.im.incidentsoic.beans.VOicHearings;
import net.syscon.s4.im.incidentsoic.beans.VOicIncidents;

/**
 * class OiioicusRepository
 */
public interface OiioicusRepository {
	List<ReferenceCodes> rgOicHearingTypeRecordGroup();

	List<ReferenceCodes> rgIncidentTypeRecordGroup();

	List<ReferenceCodes> rgOffenceTypeRecordGroup(String date);

	List<ReferenceCodes> rgSanctionCodeRecordGroup();

	List<VOicHearingResults> vOicHearResSearchVOicHearingResults(VOicHearingResults vOicHearingRes);

	List<String> offBkgOnCheckDeleteMastervOicInciCur(VOicIncidents paramBean);

	List<VOicHearings> vOicHearSearchVOicHearings(VOicHearings objVOicHearings);

	List<VOicIncidents> vOicInciSearchVOicIncidents(VOicIncidents objVOicIncidents);

	List<String> vOicHearOnCheckDeleteMastervOicHearResCur(VOicHearingResults paramBean);

	List<String> vOicHearResOnCheckDeleteMastervOffOicSanctCur(VOffenderOicSanctions paramBean);

	SystemProfiles getProfileValuevsProfvalCur(SystemProfiles paramBean);

	List<String> vOicInciOnCheckDeleteMastervOicHearCur(VOicHearings paramBean);

	List<VOffenderOicSanctions> vOffOicSanctSearchVOffenderOicSanctions(VOffenderOicSanctions vOffenderOicSanc);

	List<String> getHearingStaffNameList();

	List<String> getHearingResultsOicOffenceDes();

	List<String> getHearingResultsType();

	List<String> getDiscOicSanctionDes();

	List<String> getDiscStatusDes();
}

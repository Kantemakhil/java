package net.syscon.s4.inst.incidentsoic;

import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.im.beans.VOicIncidents;
import net.syscon.s4.im.incidentsoic.beans.IncidentStaffReport;

public interface OidoicusRepository {
	List<ReferenceCodes> rgIncTypeRecordGroup();

	List<VOicIncidents> vOicInciSearchVOicIncidents(VOicIncidents objVOicIncidents);

	List<Object> offBkgOnCheckDeleteMastervOicInciCur(String offenderbookid);

	String getDescriptionOfStaffId(Integer reportedStaffId);

	List<String> findLocationList();

	List<IncidentStaffReport> staffReportsData(final VOicIncidents objSearchDao);
}

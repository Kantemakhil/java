package net.syscon.s4.inst.incidentsoic;

import java.util.List;

import net.syscon.s4.common.beans.AgencyIncidentAssoTostg;
import net.syscon.s4.common.beans.SecurityThreatGroups;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.im.beans.OmsModules;

public interface OiustgasRepository {
	Integer agencyIncidentAssoTostgDeleteAgencyIncidentAssoTostg(List<AgencyIncidentAssoTostg> object);

	AgencyIncidentAssoTostg agencyIncidentAssoTostgPreInsert(AgencyIncidentAssoTostg paramBean);

	OmsModules createFormGlobals(OmsModules paramBean);

	List<AgencyIncidentAssoTostg> agencyIncidentAssoTostgExecuteQuery(AgencyIncidentAssoTostg object);

	List<SecurityThreatGroups> rgStgORecordGroup();

	Integer agencyIncidentAssoTostgUpdateAgencyIncidentAssoTostg(List<AgencyIncidentAssoTostg> object);

	List<SecurityThreatGroups> rgStgLRecordGroup();

	Integer agencyincidentassotostgInsertAgencyIncidentAssoTostg(List<AgencyIncidentAssoTostg> object);

	List<SecurityThreatGroups> stgGrpRecordGroup();

	Object agencyIncidentAssoTostgPreInsertInc(AgencyIncidentAssoTostg paramBean);

	List<SecurityThreatGroups> rgStgRecordGroup();

	SystemProfiles populateRg();

}

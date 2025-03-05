package net.syscon.s4.inst.incidentsoic;

import java.util.List;

import net.syscon.s4.common.beans.AgencyIncidentAssoTostg;
import net.syscon.s4.common.beans.AgencyIncidentAssoTostgCommitBean;
import net.syscon.s4.common.beans.SecurityThreatGroups;
import net.syscon.s4.common.beans.SystemProfiles;

public interface OiustgasService {
	List<AgencyIncidentAssoTostg> agencyIncidentAssoTostgExecuteQuery(
			AgencyIncidentAssoTostg objAgencyIncidentAssoTostg);

	SystemProfiles populateRg();

	List<SecurityThreatGroups> rgStgORecordGroup();

	List<AgencyIncidentAssoTostg> agencyIncidentAssoTostgPreInsert(AgencyIncidentAssoTostg paramBean);

	Integer agencyIncidentAssoTostgCommit(AgencyIncidentAssoTostgCommitBean commitBean);

	List<SecurityThreatGroups> rgStgLRecordGroup();

	List<SecurityThreatGroups> stgGrpRecordGroup();

	List<SecurityThreatGroups> rgStgRecordGroup();

	Integer agencyIncidentAssoTostgDeleteAgencyIncidentAssoTostg(
			List<AgencyIncidentAssoTostg> lstAgencyIncidentAssoTostg);

	Integer agencyIncidentAssoTostgUpdateAgencyIncidentAssoTostg(
			List<AgencyIncidentAssoTostg> lstAgencyIncidentAssoTostg);

	Integer agencyincidentassotostgInsertAgencyIncidentAssoTostg(
			List<AgencyIncidentAssoTostg> lstAgencyIncidentAssoTostg);

}

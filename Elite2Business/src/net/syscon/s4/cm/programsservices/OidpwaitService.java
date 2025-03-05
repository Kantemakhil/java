package net.syscon.s4.cm.programsservices;

import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.Areas;
import net.syscon.s4.im.beans.Offenders;
import net.syscon.s4.inst.casemanagement.beans.ProgramServices;

public interface OidpwaitService {

	List<ProgramServices> rgProgramServicesRecordGroup();

	List<Areas> rgRegionRecordGroup();

	List<ProgramServices> rgAreasRecordGroup(String areaInputs);

	List<AgencyLocations> rgAgyLocsRecordGroup(String facilityInputs);

	List<Areas> rgAllTeamsRecordGroup();

	List<VOffenderPrgObligations> vOffPrgOblExecuteQuery(VOffenderPrgObligations searchBean);

	Integer nonAssociation(VOffenderPrgObligations searchBean);

	Integer assignServicesToOffenders(VOffenderPrgObligations searchBean);

	List<ReferenceCodes> rgPsPrgAvailRecordGroup();

	List<ReferenceCodes> rgRestrictTeamsRecordGroup(String teamInputs);

	List<VOffenderPrgObligations> vOffPrgOblCommit(VOffenderPrgObligationsCommitBean commitBean);

	void vOffPrgOblPostQuery(Offenders paramBean);

	public Integer clearTempList(String modifyUserId);

	public List<ReferenceCodes> whenNewFormInstance(final String curCaseLoadId);

	public List<ReferenceCodes> getcommareadefault(final String curCaseLoadId);

}

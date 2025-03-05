package net.syscon.s4.cm.programsservices;

import java.math.BigDecimal;
import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.Areas;
import net.syscon.s4.im.beans.OffenderPrgObligations;
import net.syscon.s4.im.beans.Offenders;
import net.syscon.s4.inst.casemanagement.beans.ProgramServices;

/**
 * Interface OidpwaitRepository
 */
public interface OidpwaitRepository {

	List<ProgramServices> rgProgramServicesRecordGroup();

	List<Areas> rgRegionRecordGroup();

	List<ProgramServices> rgAreasRecordGroup(String caseLoadType, String regionCode);

	List<AgencyLocations> rgAgyLocsRecordGroup(String caseLoadType, String regionCode, String areaCode);

	List<Areas> rgAllTeamsRecordGroup();

	List<VOffenderPrgObligations> vOffPrgOblExecuteQuery(VOffenderPrgObligations searchBean);

	BigDecimal getOffenderIdCur(BigDecimal offbookId);

	List<ReferenceCodes> rgRestrictTeamsRecordGroup(String regionCode, String areaCode);

	List<ReferenceCodes> rgPsPrgAvailRecordGroup();

	VOffenderPrgObligations vOffPrgOblUpdateVOffenderPrgObligations(
			List<VOffenderPrgObligations> lstVOffenderPrgObligations);

	Object vOffPrgOblPostQuery(Offenders paramBean);

	public Integer clearTempList();

	Integer addToProgramList(VOffenderPrgObligations searchBean);

	Integer removeFromProgramList(VOffenderPrgObligations searchBean);

	BigDecimal postUpdate(VOffenderPrgObligations vOff);

	Integer getSelectedServices();

	List<ReferenceCodes> defaultAgency(final String curCaseLoadId);

	List<ReferenceCodes> getcommareadefault(final String curCaseLoadId);

	public void allocateCourseToOffender(VOffenderPrgObligations vOff);

	public Integer asnSerToOffUpdate(VOffenderPrgObligations object);

	public Integer lockOffPrgObligationId(VOffenderPrgObligations vOff);

	public List<OffenderPrgObligations> getOldRecOffenderPrgObligations(final BigDecimal offenderPrgObligationId);

}

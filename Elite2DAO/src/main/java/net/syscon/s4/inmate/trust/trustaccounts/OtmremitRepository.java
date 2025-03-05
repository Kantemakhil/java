package net.syscon.s4.inmate.trust.trustaccounts;

import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.inmate.beans.Remitters;

/**
 * Interface OtmremitRepository
 */
public interface OtmremitRepository {
	Integer sysPflInsertSystemProfiles(List<SystemProfiles> lstSystemProfiles);

	List<ReferenceCodes> cgfklkpRemRemRefCodeF(ReferenceCodes paramBean);

	List<ReferenceCodes> cgfkRemDspDescriptionRecordGroup();

	List<ReferenceCodes> cgfkRemitDspDescriptionRecordGroup();

	Integer sysPflDeleteSystemProfiles(List<SystemProfiles> lstSystemProfiles);

	Integer remDeleteRemitters(List<Remitters> lstRemitters);

	Integer remUpdateRemitters(List<Remitters> lstRemitters);

	ReferenceCodes cgfkchkRemRemRefCode(ReferenceCodes paramBean);

	Remitters cgrichkRemitters(Remitters paramBean);

	List<ReferenceCodes> cgfklkpRemRemRefCodes(ReferenceCodes paramBean);

	Integer remInsertRemitters(List<Remitters> lstRemitters);

	List<SystemProfiles> sysPflExecuteQuery(SystemProfiles objSystemProfiles);

	ReferenceCodes cgfkchkRemRemRefCodeF(ReferenceCodes paramBean);

	List<Remitters> remExecuteQuery(Remitters objRemitters);

	Remitters remitExecuteQuery(Remitters searchRecord);

	List<ReferenceCodes> getCodes();

}

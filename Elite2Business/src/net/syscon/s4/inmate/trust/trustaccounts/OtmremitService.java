package net.syscon.s4.inmate.trust.trustaccounts;

import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.beans.SystemProfilesCommitBean;
import net.syscon.s4.inmate.beans.Remitters;
import net.syscon.s4.inmate.beans.RemittersCommitBean;

/**
 * Interface OtmremitService
 */
public interface OtmremitService {
	ReferenceCodes cgfklkpRemRemRefCodeF(ReferenceCodes paramBean);

	List<Remitters> cgrichkRemitters(Remitters paramBean);

	Integer remCommit(RemittersCommitBean commitBean);

	List<ReferenceCodes> cgfkRemDspDescriptionRecordGroup();

	List<SystemProfiles> sysPflExecuteQuery(SystemProfiles objSystemProfiles);

	Integer rem1Commit(RemittersCommitBean commitBean);

	Integer sysPflCommit(SystemProfilesCommitBean commitBean);

	ReferenceCodes cgfklkpRemRemRefCode(ReferenceCodes paramBean);

	List<ReferenceCodes> cgfkRemitDspDescriptionRecordGroup();

	List<ReferenceCodes> cgfkchkRemRemRefCodeF(ReferenceCodes paramBean);

	List<Remitters> remExecuteQuery(Remitters objRemitters);

	Remitters remitExecuteQuery(Remitters searchBean);

	List<ReferenceCodes> getCodes();

}

package net.syscon.s4.inmate.trust.trustaccounts;

import java.util.List;

import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.beans.SystemProfilesCommitBean;
import net.syscon.s4.inmate.beans.OffenderBeneficiaries;
import net.syscon.s4.inmate.beans.OffenderBeneficiariesCommitBean;

public interface OcuovrobService {

	Integer offBncCommit(OffenderBeneficiariesCommitBean commitBean);

	List<OffenderBeneficiaries> offBncExecuteQuery(OffenderBeneficiaries objOffenderBeneficiaries);

	List<SystemProfiles> sysPflExecuteQuery(SystemProfiles objSystemProfiles);

	Integer sysPflCommit(SystemProfilesCommitBean commitBean);

}

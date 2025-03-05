package net.syscon.s4.inmate.trust.deductions.beneficiaryinquiry;

import java.util.List;

import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.beans.SystemProfilesCommitBean;
import net.syscon.s4.im.beans.CorporateAddressV;
import net.syscon.s4.im.beans.Corporates;
import net.syscon.s4.inmate.beans.OffenderBeneficiaries;
import net.syscon.s4.inmate.beans.OffenderBeneficiariesCommitBean;
import net.syscon.s4.inmate.beans.OffenderDeductions;

/**
 * Interface OcicbeneService
 * 
 */
public interface OcicbeneService {

	List<Object> cgwhenNewFormInstance();

	OffenderDeductions cgfkchkOffBncOffBncOff(OffenderDeductions paramBean);

	Integer offBncCommit(OffenderBeneficiariesCommitBean commitBean);

	List<OffenderBeneficiaries> offBncExecuteQuery(OffenderBeneficiaries object);

	List<SystemProfiles> sysPflExecuteQuery(SystemProfiles object);

	Integer sysPflCommit(SystemProfilesCommitBean commitBean);

	List<CorporateAddressV> vCorpExecuteQuery(CorporateAddressV object);

	Integer vCorpCommit(Corporates beanObj);

}

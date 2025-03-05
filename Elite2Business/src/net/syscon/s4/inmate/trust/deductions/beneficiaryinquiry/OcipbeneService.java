package net.syscon.s4.inmate.trust.deductions.beneficiaryinquiry;
import java.util.List;

import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.beans.SystemProfilesCommitBean;
import net.syscon.s4.inmate.beans.OffenderBeneficiaries;
import net.syscon.s4.inmate.beans.OffenderBeneficiariesCommitBean;
import net.syscon.s4.inmate.beans.PersonAddressV;
import net.syscon.s4.inst.booking.beans.Persons;
import net.syscon.s4.inst.booking.beans.PersonsCommitBean;
/**
 * Interface OcipbeneService 
 */
public interface OcipbeneService  {
	List<Object> cgwhenNewFormInstance();

	List<Persons> perExecuteQuery(Persons objPersons);

	Integer offBncCommit(OffenderBeneficiariesCommitBean commitBean);

	List<OffenderBeneficiaries> offBncExecuteQuery(OffenderBeneficiaries objOffBen);

	SystemProfiles sysPflExecuteQuery(Persons objSystemProfiles);

	Integer sysPflCommit(SystemProfilesCommitBean commitBean);

	Integer perCommit(PersonsCommitBean commitBean);

    PersonAddressV perPostQuery(Persons paramBean);

}

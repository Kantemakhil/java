package net.syscon.s4.inmate.trust.financialsmaintenance.subaccounts;

import java.util.List;

import net.syscon.s4.common.beans.CaseloadAgencyLocations;
import net.syscon.s4.common.beans.Caseloads;
import net.syscon.s4.common.beans.InstitutionMiniBalances;
import net.syscon.s4.common.beans.InstitutionMiniBalancesCommitBean;
import net.syscon.s4.im.beans.AccountCodes;

/**
 * Interface OtmisambService
 */
public interface OtmisambService {

	List<InstitutionMiniBalances> instMnbalExecuteQuery(InstitutionMiniBalances objInstitutionMiniBalances);

	String instMnbalCommit(InstitutionMiniBalancesCommitBean commitBean);

	List<CaseloadAgencyLocations> cgfkInstMnbalAgyLocIdRecordGroup(String caseloadId);

	List<AccountCodes> cgfkInstMnbalAccountCodeRecordGroup();

	List<Caseloads> cgfkInstMnbalCaseloadIdRecordGroup();

}

package net.syscon.s4.inmate.trust.financialsmaintenance.subaccounts;

import java.util.List;

import net.syscon.s4.common.beans.CaseloadAgencyLocations;
import net.syscon.s4.common.beans.Caseloads;
import net.syscon.s4.common.beans.InstitutionMiniBalances;
import net.syscon.s4.im.beans.AccountCodes;

/**
 * Interface OtmisambRepository
 */
public interface OtmisambRepository {

	Integer instMnbalInsertInstitutionMiniBalances(List<InstitutionMiniBalances> lstInstitutionMiniBalances);

	Integer instMnbalUpdateInstitutionMiniBalances(List<InstitutionMiniBalances> lstInstitutionMiniBalances);

	List<CaseloadAgencyLocations> cgfkchkInstMnbalInstMb2(CaseloadAgencyLocations paramBean);

	List<InstitutionMiniBalances> instMnbalExecuteQuery(InstitutionMiniBalances objInstitutionMiniBalances);

	Integer instMnbalDeleteInstitutionMiniBalances(List<InstitutionMiniBalances> lstInstitutionMiniBalances);

	List<Caseloads> cgfkchkInstMnbalInstMbCs(Caseloads paramBean);

	List<CaseloadAgencyLocations> cgfkInstMnbalAgyLocIdRecordGroup(String caseloadId);

	List<AccountCodes> cgfkInstMnbalAccountCodeRecordGroup();

	List<AccountCodes> cgfkchkInstMnbalInstMbAc(AccountCodes paramBean);

	List<Caseloads> cgfkInstMnbalCaseloadIdRecordGroup();

}

package net.syscon.s4.inmate.trust.financialsmaintenance.payees;

import java.util.List;

import net.syscon.s4.common.beans.CorporateTypesCommitBean;
import net.syscon.s4.im.beans.CorporateTypes;
import net.syscon.s4.im.beans.Corporates;
import net.syscon.s4.im.beans.ReferenceCodes;

/**
 * Interface OcucorptService
 */
public interface OcucorptService {
	List<ReferenceCodes> rgCorpTypeRecordGroup();

	List<CorporateTypes> corporateTypesExecuteQuery(CorporateTypes objCorporateTypes);

	Integer prevCaseloadCorpExists(Corporates paramBean);

	Integer corporateTypesCommit(CorporateTypesCommitBean commitBean);

}

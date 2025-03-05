package net.syscon.s4.inmate.trust.financialsmaintenance.payees;

import java.util.List;

import net.syscon.s4.im.beans.CorporateTypes;
import net.syscon.s4.im.beans.Corporates;
import net.syscon.s4.im.beans.ReferenceCodes;

/**
 * Interface OcucorptRepository
 */
public interface OcucorptRepository {
	List<ReferenceCodes> rgCorpTypeRecordGroup();

	Integer corporateTypesDeleteCorporateTypes(List<CorporateTypes> lstCorporateTypes);

	Integer prevCaseloadCorpExists(Corporates paramBean);

	List<CorporateTypes> corporateTypesExecuteQuery(CorporateTypes objCorporateTypes);

	Integer corporateTypesInsertCorporateTypes(List<CorporateTypes> lstCorporateTypes);

	Integer corporateTypesUpdateCorporateTypes(List<CorporateTypes> lstCorporateTypes);

}

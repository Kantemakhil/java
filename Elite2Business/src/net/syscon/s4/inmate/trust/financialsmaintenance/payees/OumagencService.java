package net.syscon.s4.inmate.trust.financialsmaintenance.payees;

import java.math.BigDecimal;
import java.util.List;

import net.syscon.s4.common.beans.Caseloads;
import net.syscon.s4.common.beans.InternetAddresses;
import net.syscon.s4.common.beans.Phones;
import net.syscon.s4.im.beans.Addresses;
import net.syscon.s4.im.beans.AddressesCommitBean;
import net.syscon.s4.im.beans.Corporates;
import net.syscon.s4.im.beans.CorporatesCommitBean;
import net.syscon.s4.im.beans.InternetAddressesCommitBean;
import net.syscon.s4.im.beans.PhonesCommitBean;
import net.syscon.s4.im.beans.ReferenceCodes;
import net.syscon.s4.inst.schedules.bean.VCorporateAddresses;
import net.syscon.s4.inst.schedules.bean.VCorporateAddressesCommitBean;

/**
 * Interface OumagencService
 */
public interface OumagencService {
	String corpCommit(CorporatesCommitBean commitBean);

	List<ReferenceCodes> rgTypeRecordGroup();

	Integer corPhoneCommit(CorporatesCommitBean CommitBean);

	List<InternetAddresses> iAddExecuteQuery(InternetAddresses objInternetAddresses);

	Integer iAddCommit(InternetAddressesCommitBean commitBean) throws Exception;

	List<ReferenceCodes> rgIclassRecordGroup();

	List<Addresses> addressesExecuteQuery(Addresses objAddresses);

	Integer addressesCommit(AddressesCommitBean commitBean) throws Exception;

	List<Caseloads> rgCaseloadRecordGroup();

	List<Corporates> corpExecuteQuery(Corporates objCorporates);

	Integer addrCommit(VCorporateAddressesCommitBean commitBean);

	List<VCorporateAddresses> addrExecuteQuery(VCorporateAddresses objVCorporateAddresses);

	List<Phones> addPhExecuteQuery(Phones objPhones);

	Integer addPhCommit(PhonesCommitBean commitBean) throws Exception;

	List<Phones> corPhoneExecuteQuery(Phones searchRecord);

	BigDecimal getCorporateChilds(BigDecimal corporateId);

}

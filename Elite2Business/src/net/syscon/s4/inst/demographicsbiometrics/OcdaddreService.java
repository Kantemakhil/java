package net.syscon.s4.inst.demographicsbiometrics;

import java.util.List;

import net.syscon.s4.common.beans.Addresses;
import net.syscon.s4.common.beans.AddressesCommitBean;
import net.syscon.s4.common.beans.InternetAddresses;
import net.syscon.s4.common.beans.OffenderCurfews;
import net.syscon.s4.common.beans.Phones;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.im.beans.AddressUsages;
import net.syscon.s4.im.beans.AddressUsagesCommitBean;
//import net.syscon.s4.im.beans.Addresses;
//import net.syscon.s4.im.beans.AddressesCommitBean;
import net.syscon.s4.im.beans.InternetAddressesCommitBean;
import net.syscon.s4.im.beans.OmsModules;
import net.syscon.s4.im.beans.PhonesCommitBean;
import net.syscon.s4.im.beans.VAddresses;

/**
 * Interface OcdaddreBusiness
 */
public interface OcdaddreService {

	List<AddressUsages> addrUsageSearchAddressUsages(AddressUsages addressUsages);

	List<ReferenceCodes> addrWhenCreateRecordgetCountryCur(ReferenceCodes referenceCodes);

	OmsModules createFormGlobals(OmsModules omsModules);

	List<VAddresses> vAddSearchVAddresses(VAddresses vAddresses);

	List<Object> addrOnCheckDeleteMasterphoneAddrCur(Phones phones);

	Addresses searchAddresses(Addresses addresses);

	Object vAddOnCheckDeleteMasteraddrCur(Addresses addresses);

	Object checkHdcAddressExistcheckHdcAddressCur(VAddresses vAddresses);

	Object validateCityInfogetCityDescription(ReferenceCodes referenceCodes);

	List<Object> offBkgOnCheckDeleteMastervAddCur(VAddresses vAddresses);

	List<Object> offBkgOnCheckDeleteMasteremailCur(InternetAddresses internetAddresses);

	List<OffenderCurfews> checkHdcAddressActive(OffenderCurfews offenderCurfews);

	List<Phones> phoneAddrSearchPhones(Phones phones);

	List<Object> nbtCityKeyListvalgetCityDescription(ReferenceCodes referenceCodes);

	List<ReferenceCodes> validateCityInfogetCityCode(ReferenceCodes referenceCodes);

	Object addrOnCheckDeleteMasteraddrUsageCur(AddressUsages addressUsages);

	List<InternetAddresses> emailSearchInternetAddresses(InternetAddresses internetAddresses);

	Object checkHdcAddressActivecheckHdcAddressCur(VAddresses vAddresses);

	Object addressTypecheckActiveTypeCur(AddressUsages addressUsages);

	List<Object> offBkgOnCheckDeleteMasteraddrCur(Addresses addresses);

	List<Object> offBkgOnCheckDeleteMasterphoneGlobalCur(Phones phones);

	List<ReferenceCodes> rgTownRecordGroup();

	List<ReferenceCodes> rgCountryRecordGroup();

	List<ReferenceCodes> rgSdirRecordGroup();

	List<ReferenceCodes> rgStateRecordGroup();

	List<ReferenceCodes> rgAddressTypeRecordGroup();

	List<ReferenceCodes> rgPhoneTypeRecordGroup();

	List<Phones> phoneGlobalSearchPhones(Phones phones);

	Integer addrCommit(AddressesCommitBean commitBean);

	Integer phoneAddrCommit(PhonesCommitBean commitBean);

	Integer phoneGlobalCommit(PhonesCommitBean commitBean);

	Integer emailCommit(InternetAddressesCommitBean commitBean);

	Integer addrUsageCommit(final AddressUsagesCommitBean commitBean);

	List<VAddresses> vAddressAndPhoneExecuteQuery(VAddresses vAddresses);
	
}

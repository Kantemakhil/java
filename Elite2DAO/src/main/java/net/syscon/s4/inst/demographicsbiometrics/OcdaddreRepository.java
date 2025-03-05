package net.syscon.s4.inst.demographicsbiometrics;

import java.util.List;

import net.syscon.s4.common.beans.Addresses;
import net.syscon.s4.common.beans.InternetAddresses;
import net.syscon.s4.common.beans.OffenderCurfews;
import net.syscon.s4.common.beans.Phones;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.im.beans.AddressUsages;
import net.syscon.s4.im.beans.OmsModules;
import net.syscon.s4.im.beans.VAddresses;

/**
 * Interface OcdaddreRepository
 * 
 */
public interface OcdaddreRepository {

	List<ReferenceCodes> rgTownRecordGroup();

	List<ReferenceCodes> rgCountryRecordGroup();

	List<ReferenceCodes> rgSdirRecordGroup();

	List<ReferenceCodes> rgStateRecordGroup();

	List<ReferenceCodes> rgAddressTypeRecordGroup();

	List<ReferenceCodes> rgPhoneTypeRecordGroup();

	Integer emailUpdateInternetAddresses(List<InternetAddresses> lstInternetAddresses);

	List<OffenderCurfews> checkHdcAddressActive(OffenderCurfews offenderCurfews);

	Integer phoneAddrInsertPhones(List<Phones> lstPhones);

	Integer emailInsertInternetAddresses(List<InternetAddresses> lstInternetAddresses);

	List<AddressUsages> addrUsageSearchAddressUsages(AddressUsages addressUsages);

	List<VAddresses> vAddSearchVAddresses(VAddresses vAddresses);

	Integer addrDeleteAddresses(List<Addresses> lstAddresses);

	Object checkHdcAddressExistcheckHdcAddressCur(VAddresses vAddresses);

	Integer emailDeleteInternetAddresses(List<InternetAddresses> lstInternetAddresses);

	List<ReferenceCodes> addrWhenCreateRecordgetCountryCur(ReferenceCodes referenceCodes);

	Object vAddOnCheckDeleteMasteraddrCur(Addresses addresses);

	Object addrOnCheckDeleteMasteraddrUsageCur(AddressUsages addressUsages);

	Integer phoneAddrUpdatePhones(List<Phones> lstPhones);

	Addresses searchAddresses(Addresses addresses);

	List<Object> offBkgOnCheckDeleteMasteraddrCur(Addresses addresses);

	List<Object> offbkgoncheckdeletemastervaddcur(VAddresses vAddresses);

	Object validateCityInfogetCityDescription(ReferenceCodes referenceCodes);

	Integer phoneAddrDeletePhones(List<Phones> lstPhones);

	List<Object> offBkgOnCheckDeleteMasterphoneGlobalCur(Phones phones);

	Integer addrUpdateAddresses(List<Addresses> lstAddresses);

	List<Phones> phoneAddrSearchPhones(Phones phones);

	Integer addrUsageInsertAddressUsages(List<AddressUsages> lstAddressUsages);

	List<Object> nbtCityKeyListvalgetCityDescription(ReferenceCodes referenceCodes);

	Object addressTypecheckActiveTypeCur(AddressUsages addressUsages);

	Object checkHdcAddressActivecheckHdcAddressCur(VAddresses vAddresses);

	List<ReferenceCodes> validateCityInfogetCityCode(ReferenceCodes referenceCodes);

	List<Object> addrOnCheckDeleteMasterphoneAddrCur(Phones phones);

	Integer addrUsageUpdateAddressUsages(List<AddressUsages> lstAddressUsages);

	Integer addrInsertAddresses(List<Addresses> lstAddresses);

	List<InternetAddresses> emailSearchInternetAddresses(InternetAddresses internetAddresses);

	Integer addrUsageDeleteAddressUsages(List<AddressUsages> lstAddressUsages);

	List<Object> offBkgOnCheckDeleteMasteremailCur(InternetAddresses internetAddresses);

	OmsModules createFormGlobals(OmsModules omsModule);

	Object preInsertgetAddrId();

	Object preInsertgetPhoneId();

	Integer selectAddressUsageCount(final Long addressId, final String addressUsage);

	Object preInsertgetInternetAddrId();

	Integer preUpdateAddresses(Addresses adrObj);

	Integer preUpdateAddressesMailFlag(Addresses adrObj);

	Addresses gettingOldRecord(long addressId);
	
}

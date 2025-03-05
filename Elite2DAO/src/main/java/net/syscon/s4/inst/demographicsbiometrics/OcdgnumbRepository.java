package net.syscon.s4.inst.demographicsbiometrics;

import java.util.List;

import net.syscon.s4.common.beans.InternetAddresses;
import net.syscon.s4.common.beans.Phones;
import net.syscon.s4.common.beans.ReferenceCodes;

/**
 * Interface OcdgnumbRepository
 * 
 * 
 */
public interface OcdgnumbRepository {
	Integer phonesUpdatePhones(List<Phones> lstPhones);

	List<ReferenceCodes> rgPhoneTypeRecordGroup();

	Integer phonesInsertPhones(List<Phones> lstPhones);

	Integer phonesDeletePhones(List<Phones> lstPhones);

	Integer internetAddrInsertInternetAddresses(List<InternetAddresses> lstInternetAddre);

	Integer internetAddrUpdateInternetAddresses(List<InternetAddresses> lstInternetAddre);

	List<InternetAddresses> internetAddrExecuteQuery(InternetAddresses objInternetAddre);

	Object internetAddrPreInsert();

	List<Phones> phonesExecuteQuery(Phones objPhones);

	Object phonesPreInsertPreInsert();

	Integer internetAddrDeleteInternetAddresses(List<InternetAddresses> lstInternetAddr);
 
	Phones getOldDataOfPhones(Long phoneId);
	
	List<String> gettingEmailDomains();
}

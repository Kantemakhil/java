package net.syscon.s4.inst.demographicsbiometrics;


import java.util.List;

import net.syscon.s4.common.beans.InternetAddresses;
import net.syscon.s4.common.beans.Phones;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.im.beans.InternetAddressesCommitBean;
import net.syscon.s4.im.beans.PhonesCommitBean;

/**
 * Interface OcdgnumbService
 * 
 * 
 * 
 */
public interface OcdgnumbService {
	List<ReferenceCodes> rgPhoneTypeRecordGroup();

	Object internetAddrPreInsert();

	Integer phonesCommit(PhonesCommitBean commitBean);

	Integer internetAddrCommit(InternetAddressesCommitBean commitBean);

	List<InternetAddresses> internetAddrExecuteQuery(InternetAddresses objInternetAdd);

	List<Phones> phonesExecuteQuery(Phones objPhones);

	Object phonesPreInsert();
	
	List<String> gettingEmailDomains();

}

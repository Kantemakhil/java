package net.syscon.s4.pkgs.oumagyht;

import net.syscon.s4.im.beans.AgencyLocationAmendments;

public interface OumagyhtPkgService {
	Integer insertIntoAgyLocAmendments(AgencyLocationAmendments agyLocAmendments);

	Boolean checkChanged(String pOldValue, String pNewValue);

	String getAddressOwnerCode(Long pAddrId);

}

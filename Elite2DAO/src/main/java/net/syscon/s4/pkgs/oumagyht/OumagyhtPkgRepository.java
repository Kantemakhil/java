package net.syscon.s4.pkgs.oumagyht;

import java.util.List;

import net.syscon.s4.common.beans.Addresses;
import net.syscon.s4.im.beans.AgencyLocationAmendments;

public interface OumagyhtPkgRepository {
	Integer insertIntoAgyLocAmendments(AgencyLocationAmendments agyLocAmendments);

	List<Addresses> getAddressOwnerCode(Long pAddrId);

}

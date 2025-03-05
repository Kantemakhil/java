package net.syscon.s4.triggers.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.common.beans.InternetAddresses;
import net.syscon.s4.im.beans.AgencyLocationAmendments;
import net.syscon.s4.pkgs.oumagyht.impl.OumagyhtPkgServiceImpl;
import net.syscon.s4.triggers.InternetAddressesT2Repository;
import net.syscon.s4.triggers.InternetAddressesT2Service;
/* =========================================================
Below comments are copied from INTERNET_ADDRESSES_T2 Trigger
========================================================= */

/* MODIFICATION HISTORY
Person      Date            Version       Comments
---------   ------       ------------  ------------------------------
Patrick     27-JUN-2005           1.0   Initial Version

*/
@Service
public class InternetAddressesT2ServiceImpl implements InternetAddressesT2Service {
	@Autowired
	OumagyhtPkgServiceImpl oumagyhtServiceImpl;
	@Autowired
	private InternetAddressesT2Repository internetAddressesT2Repository;

	@Override
	public void internetAddressesT2Trigger(InternetAddresses object) {
		InternetAddresses oldObject = new InternetAddresses();
		String lvAgyLocId = "";
		if (Optional.ofNullable(object.getInternetAddressId()) != null) {
			oldObject = internetAddressesT2Repository.getInternetAddrsesOldObject(object);
		}
		  if (oldObject != null) {
			if ("AGY".equals(object.getOwnerClass())) {
				lvAgyLocId = object.getOwnerCode();
			} else if ("AGY".equals(oldObject.getOwnerClass())) {
				lvAgyLocId = oldObject.getOwnerCode();
			}

			AgencyLocationAmendments agyLocnAmendments = new AgencyLocationAmendments();
			if (Optional.ofNullable(lvAgyLocId).isPresent()) {
				if (oumagyhtServiceImpl.checkChanged(oldObject.getInternetAddressClass(),
						object.getInternetAddressClass())) {
					agyLocnAmendments = new AgencyLocationAmendments();
					agyLocnAmendments.setLvAgyLocId(lvAgyLocId);
					agyLocnAmendments.setpColName("INTERNET_ADDRESS_CLASS");
					agyLocnAmendments.setpOldValue(oldObject.getInternetAddressClass());
					agyLocnAmendments.setpNewValue(object.getInternetAddressClass());
					agyLocnAmendments.setpDomain("IADDR_CLASS");
					agyLocnAmendments.setpDescType("REF_CODE");
					agyLocnAmendments.setAmendUser((agyLocnAmendments.getCreateUserId()!=null?agyLocnAmendments.getCreateUserId():agyLocnAmendments.getModifyUserId()));
					oumagyhtServiceImpl.insertIntoAgyLocAmendments(agyLocnAmendments);
				}
				if (oumagyhtServiceImpl.checkChanged(oldObject.getInternetAddress(), object.getInternetAddress())) {
					agyLocnAmendments = new AgencyLocationAmendments();
					agyLocnAmendments.setLvAgyLocId(lvAgyLocId);
					agyLocnAmendments.setpColName("INTERNET_ADDRESS");
					agyLocnAmendments.setpOldValue(oldObject.getInternetAddress());
					agyLocnAmendments.setpNewValue(object.getInternetAddress());
					agyLocnAmendments.setpDomain(" ");
					agyLocnAmendments.setpDescType(" ");
					agyLocnAmendments.setAmendUser((agyLocnAmendments.getCreateUserId()!=null?agyLocnAmendments.getCreateUserId():agyLocnAmendments.getModifyUserId()));
					oumagyhtServiceImpl.insertIntoAgyLocAmendments(agyLocnAmendments);
				}
			}

		}

	}
}
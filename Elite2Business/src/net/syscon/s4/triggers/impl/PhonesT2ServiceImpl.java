package net.syscon.s4.triggers.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.common.beans.Phones;
import net.syscon.s4.im.beans.AgencyLocationAmendments;
import net.syscon.s4.pkgs.oumagyht.impl.OumagyhtPkgServiceImpl;
import net.syscon.s4.triggers.PhonesT2Service;

/* =========================================================
 Below comments are copied from PHONES_T2 Trigger
========================================================= */

/* MODIFICATION HISTORY
   Person      Date            Version       Comments
   ---------   ------       ------------  ------------------------------
   Patrick     27-JUN-2005           1.0   Initial Version

*/
@Service
public class PhonesT2ServiceImpl implements PhonesT2Service{
	@Autowired
	OumagyhtPkgServiceImpl oumagyhtServiceImpl;
	@Override
	public void phonesT2Trigger(Phones old, Phones newObj) {
		String lvAgyLocId = "";
		if(newObj.getPhoneId() != null) {
		
		if ("ADDR".equals(newObj.getOwnerClass())) {
			lvAgyLocId = oumagyhtServiceImpl.getAddressOwnerCode(newObj.getOwnerId().longValue());
		} else if ("ADDR".equals(old.getOwnerClass())) {
			lvAgyLocId = oumagyhtServiceImpl.getAddressOwnerCode(old.getOwnerId().longValue());
		}
		if ("AGY".equals(newObj.getOwnerClass())) {
			lvAgyLocId = newObj.getOwnerCode();
		} else if ("AGY".equals(old.getOwnerClass())) {
			lvAgyLocId = old.getOwnerCode();
		}
		
		AgencyLocationAmendments agyLocnAmendments = new AgencyLocationAmendments();
		if (Optional.ofNullable(lvAgyLocId).isPresent()) {
			if (oumagyhtServiceImpl.checkChanged(old.getPhoneType(), newObj.getPhoneType())) {
				agyLocnAmendments = new AgencyLocationAmendments();
				agyLocnAmendments.setLvAgyLocId(lvAgyLocId);
				agyLocnAmendments.setpColName("PHONE_TYPE");
				agyLocnAmendments.setpOldValue(old.getPhoneType());
				agyLocnAmendments.setpNewValue(newObj.getPhoneType());
				agyLocnAmendments.setpDomain("PHONE_USAGE");
				agyLocnAmendments.setpDescType("REF_CODE");
				agyLocnAmendments.setCreateUserId(old.getCreateUserId());
				agyLocnAmendments.setAmendUser(newObj.getCreateUserId()!=null?newObj.getCreateUserId():newObj.getModifyUserId());
				oumagyhtServiceImpl.insertIntoAgyLocAmendments(agyLocnAmendments);
			}
			if (oumagyhtServiceImpl.checkChanged(old.getPhoneNo(), newObj.getPhoneNo())) {
				agyLocnAmendments = new AgencyLocationAmendments();
				agyLocnAmendments.setLvAgyLocId(lvAgyLocId);
				agyLocnAmendments.setpColName("PHONE_NO");
				agyLocnAmendments.setpOldValue(old.getPhoneNo());
				agyLocnAmendments.setpNewValue(newObj.getPhoneNo());
				agyLocnAmendments.setpDomain(" ");
				agyLocnAmendments.setpDescType(" ");
				agyLocnAmendments.setCreateUserId(old.getCreateUserId());
				agyLocnAmendments.setAmendUser(newObj.getCreateUserId()!=null?newObj.getCreateUserId():newObj.getModifyUserId());
				oumagyhtServiceImpl.insertIntoAgyLocAmendments(agyLocnAmendments);
			}
			
			if (oumagyhtServiceImpl.checkChanged(old.getExtNo(), newObj.getExtNo())) {
				agyLocnAmendments = new AgencyLocationAmendments();
				agyLocnAmendments.setLvAgyLocId(lvAgyLocId);
				agyLocnAmendments.setpColName("EXT_NO");
				agyLocnAmendments.setpOldValue(old.getExtNo());
				agyLocnAmendments.setpNewValue(newObj.getExtNo());
				agyLocnAmendments.setpDomain(" ");
				agyLocnAmendments.setpDescType(" ");
				agyLocnAmendments.setCreateUserId(old.getCreateUserId());
				agyLocnAmendments.setAmendUser(newObj.getCreateUserId()!=null?newObj.getCreateUserId():newObj.getModifyUserId());
				oumagyhtServiceImpl.insertIntoAgyLocAmendments(agyLocnAmendments);
			}
			}
		}
	}

}

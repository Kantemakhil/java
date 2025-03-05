package net.syscon.s4.triggers.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.common.beans.Addresses;
import net.syscon.s4.im.beans.AgencyLocationAmendments;
import net.syscon.s4.pkgs.oumagyht.impl.OumagyhtPkgServiceImpl;
import net.syscon.s4.triggers.AddressesT3Repository;
import net.syscon.s4.triggers.AddressesT3Service;
/* =========================================================
Below comments are copied from ADDRESSES_T3 Trigger
========================================================= */

/* MODIFICATION HISTORY
Person      Date                   Version       Comments
---------       ------                       ------------  ------------------------------
Niko         26-JAN-2007      1.0.1.0        Replace the columns in V_STF_ADDR block:
								 				  		  	  		  	 SUITE_NUMBER  (replaces the FLAT in the UK model)
																		 STREET_NUMBER (replaces PREMISE in the UK model)
																		 STREET_DIRECTION    (replaces LOCALITY in the UK model)
																		 PROV_STATE_CODE     (replaces COUNTY in the UK model)
																		 ZIP_POSTAL_CODE     (replaces POSTAL in the UK model)
Patrick     27-JUN-2005    	    1.0   Initial Version

*/
@Service
public class AddressesT3ServiceImpl implements AddressesT3Service {
	@Autowired
	OumagyhtPkgServiceImpl oumagyhtServiceImpl;
	@Autowired
	private AddressesT3Repository addresesT3Repository;
	final DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

	@Override
	public void AddresesT3Trigger(Addresses old, final Addresses newObj) {
		String lvAgyLocId =null;
		if (old == null) {
			if (Optional.ofNullable(newObj.getAddressId()) != null) {
				old = addresesT3Repository.getAddressOldObject(newObj);
			}
		}
		if ("AGY".equals(newObj.getOwnerClass())) {
			lvAgyLocId = newObj.getOwnerCode();
		} else if ("AGY".equals(old.getOwnerClass()!=null?old.getOwnerClass():null)) {
			lvAgyLocId = old.getOwnerCode();
		}
		AgencyLocationAmendments agyLocnAmendments = new AgencyLocationAmendments();
		agyLocnAmendments.setCreateUserId(newObj.getCreateUserId());
		if (lvAgyLocId !=null) {
		if (Optional.ofNullable(lvAgyLocId).isPresent()) {
			if (oumagyhtServiceImpl.checkChanged(old.getAddressType(), newObj.getAddressType())) {
				agyLocnAmendments = new AgencyLocationAmendments();
				agyLocnAmendments.setLvAgyLocId(lvAgyLocId);
				agyLocnAmendments.setCreateUserId(newObj.getCreateUserId());
				agyLocnAmendments.setAmendUser(newObj.getCreateUserId());
				agyLocnAmendments.setpColName("ADDRESS_TYPE");
				agyLocnAmendments.setpOldValue(old.getAddressType());
				agyLocnAmendments.setpNewValue(newObj.getAddressType());
				agyLocnAmendments.setpDomain("NEW_ADDR_TYPE");
				agyLocnAmendments.setpDescType("REF_CODE");
				agyLocnAmendments.setAgyLocId(lvAgyLocId);
				oumagyhtServiceImpl.insertIntoAgyLocAmendments(agyLocnAmendments);
			}
		}
		if (oumagyhtServiceImpl.checkChanged(old.getStreet(), newObj.getStreet())) {
				agyLocnAmendments = new AgencyLocationAmendments();
				agyLocnAmendments.setAmendUser(newObj.getCreateUserId());
				agyLocnAmendments.setCreateUserId(newObj.getCreateUserId());
				agyLocnAmendments.setLvAgyLocId(lvAgyLocId);
				agyLocnAmendments.setpColName("STREET");
				agyLocnAmendments.setpOldValue(old.getStreet());
				agyLocnAmendments.setpNewValue(newObj.getStreet());
				agyLocnAmendments.setpDomain("");
				agyLocnAmendments.setpDescType("");
				agyLocnAmendments.setAgyLocId(lvAgyLocId);
				oumagyhtServiceImpl.insertIntoAgyLocAmendments(agyLocnAmendments);
			}
		if (oumagyhtServiceImpl.checkChanged(old.getCityCode(), newObj.getCityCode())) {
				agyLocnAmendments = new AgencyLocationAmendments();
				agyLocnAmendments.setCreateUserId(newObj.getCreateUserId());
				agyLocnAmendments.setAmendUser(newObj.getCreateUserId());
				agyLocnAmendments.setLvAgyLocId(lvAgyLocId);
				agyLocnAmendments.setpColName("CITY_CODE");
				agyLocnAmendments.setpOldValue(old.getCityCode());
				agyLocnAmendments.setpNewValue(newObj.getCityCode());
				agyLocnAmendments.setpDomain("CITY");
				agyLocnAmendments.setpDescType("REF_CODE");
				agyLocnAmendments.setAgyLocId(lvAgyLocId);
				oumagyhtServiceImpl.insertIntoAgyLocAmendments(agyLocnAmendments);
			}
			if (oumagyhtServiceImpl.checkChanged(old.getCountryCode(), newObj.getCountryCode())) {
				agyLocnAmendments = new AgencyLocationAmendments();
				agyLocnAmendments.setCreateUserId(newObj.getCreateUserId());
				agyLocnAmendments.setAmendUser(newObj.getCreateUserId());
				agyLocnAmendments.setLvAgyLocId(lvAgyLocId);
				agyLocnAmendments.setpColName("COUNTRY_CODE");
				agyLocnAmendments.setpOldValue(old.getCountryCode());
				agyLocnAmendments.setpNewValue(newObj.getCountryCode());
				agyLocnAmendments.setpDomain("COUNTRY");
				agyLocnAmendments.setpDescType("REF_CODE");
				agyLocnAmendments.setAgyLocId(lvAgyLocId);
				oumagyhtServiceImpl.insertIntoAgyLocAmendments(agyLocnAmendments);
			}
			if (oumagyhtServiceImpl.checkChanged(old.getValidatedPafFlag(), newObj.getValidatedPafFlag())) {
				agyLocnAmendments = new AgencyLocationAmendments();
				agyLocnAmendments.setCreateUserId(newObj.getCreateUserId());
				agyLocnAmendments.setAmendUser(newObj.getCreateUserId());
				agyLocnAmendments.setLvAgyLocId(lvAgyLocId);
				agyLocnAmendments.setpColName("VALIDATED_PAF_FLAG");
				agyLocnAmendments.setpOldValue(old.getValidatedPafFlag());
				agyLocnAmendments.setpNewValue(newObj.getValidatedPafFlag());
				agyLocnAmendments.setpDomain("");
				agyLocnAmendments.setpDescType("");
				agyLocnAmendments.setAgyLocId(lvAgyLocId);
				oumagyhtServiceImpl.insertIntoAgyLocAmendments(agyLocnAmendments);
			}
			if (oumagyhtServiceImpl.checkChanged(old.getPrimaryFlag(), newObj.getPrimaryFlag())) {
				agyLocnAmendments = new AgencyLocationAmendments();
				agyLocnAmendments.setCreateUserId(newObj.getCreateUserId());
				agyLocnAmendments.setAmendUser(newObj.getCreateUserId());
				agyLocnAmendments.setLvAgyLocId(lvAgyLocId);
				agyLocnAmendments.setpColName("PRIMARY_FLAG");
				agyLocnAmendments.setpOldValue(old.getPrimaryFlag());
				agyLocnAmendments.setpNewValue(newObj.getPrimaryFlag());
				agyLocnAmendments.setpDomain("");
				agyLocnAmendments.setpDescType("");
				agyLocnAmendments.setAgyLocId(lvAgyLocId);
				oumagyhtServiceImpl.insertIntoAgyLocAmendments(agyLocnAmendments);
			}
			if (oumagyhtServiceImpl.checkChanged(old.getMailFlag(), newObj.getMailFlag())) {
				agyLocnAmendments = new AgencyLocationAmendments();
				agyLocnAmendments.setCreateUserId(newObj.getCreateUserId());
				agyLocnAmendments.setAmendUser(newObj.getCreateUserId());
				agyLocnAmendments.setLvAgyLocId(lvAgyLocId);
				agyLocnAmendments.setpColName("MAIL_FLAG");
				agyLocnAmendments.setpOldValue(old.getMailFlag());
				agyLocnAmendments.setpNewValue(newObj.getMailFlag());
				agyLocnAmendments.setpDomain("");
				agyLocnAmendments.setpDescType("");
				agyLocnAmendments.setAgyLocId(lvAgyLocId);
				oumagyhtServiceImpl.insertIntoAgyLocAmendments(agyLocnAmendments);
			}
			if (oumagyhtServiceImpl.checkChanged(String.valueOf(old.getCapacity()),
					String.valueOf(newObj.getCapacity()))) {
				agyLocnAmendments = new AgencyLocationAmendments();
				agyLocnAmendments.setCreateUserId(newObj.getCreateUserId());
				agyLocnAmendments.setAmendUser(newObj.getCreateUserId());
				agyLocnAmendments.setLvAgyLocId(lvAgyLocId);
				agyLocnAmendments.setpColName("CAPACITY");
				agyLocnAmendments.setpOldValue(String.valueOf(old.getCapacity()));
				agyLocnAmendments.setpNewValue(String.valueOf(newObj.getCapacity()));
				agyLocnAmendments.setpDomain("");
				agyLocnAmendments.setpDescType("");
				agyLocnAmendments.setAgyLocId(lvAgyLocId);
				oumagyhtServiceImpl.insertIntoAgyLocAmendments(agyLocnAmendments);
			}
			if (oumagyhtServiceImpl.checkChanged(old.getCommentText(), newObj.getCommentText())) {
				agyLocnAmendments = new AgencyLocationAmendments();
				agyLocnAmendments.setCreateUserId(newObj.getCreateUserId());
				agyLocnAmendments.setAmendUser(newObj.getCreateUserId());
				agyLocnAmendments.setLvAgyLocId(lvAgyLocId);
				agyLocnAmendments.setpColName("COMMENT_TEXT");
				agyLocnAmendments.setpOldValue(old.getCommentText());
				agyLocnAmendments.setpNewValue(newObj.getCommentText());
				agyLocnAmendments.setpDomain("");
				agyLocnAmendments.setpDescType("");
				agyLocnAmendments.setAgyLocId(lvAgyLocId);
				oumagyhtServiceImpl.insertIntoAgyLocAmendments(agyLocnAmendments);
			}
			if (oumagyhtServiceImpl.checkChanged(old.getNoFixedAddressFlag(), newObj.getNoFixedAddressFlag())) {
				agyLocnAmendments = new AgencyLocationAmendments();
				agyLocnAmendments.setCreateUserId(newObj.getCreateUserId());
				agyLocnAmendments.setAmendUser(newObj.getCreateUserId());
				agyLocnAmendments.setLvAgyLocId(lvAgyLocId);
				agyLocnAmendments.setpColName("NO_FIXED_ADDRESS_FLAG");
				agyLocnAmendments.setpOldValue(old.getNoFixedAddressFlag());
				agyLocnAmendments.setpNewValue(newObj.getNoFixedAddressFlag());
				agyLocnAmendments.setpDomain("");
				agyLocnAmendments.setpDescType("");
				agyLocnAmendments.setAgyLocId(lvAgyLocId);
				oumagyhtServiceImpl.insertIntoAgyLocAmendments(agyLocnAmendments);
			}
			if (oumagyhtServiceImpl.checkChanged(old.getServicesFlag(), newObj.getServicesFlag())) {
				agyLocnAmendments = new AgencyLocationAmendments();
				agyLocnAmendments.setCreateUserId(newObj.getCreateUserId());
				agyLocnAmendments.setAmendUser(newObj.getCreateUserId());
				agyLocnAmendments.setLvAgyLocId(lvAgyLocId);
				agyLocnAmendments.setpColName("SERVICES_FLAG");
				agyLocnAmendments.setpOldValue(old.getServicesFlag());
				agyLocnAmendments.setpNewValue(newObj.getServicesFlag());
				agyLocnAmendments.setpDomain("");
				agyLocnAmendments.setpDescType("");
				agyLocnAmendments.setAgyLocId(lvAgyLocId);
				oumagyhtServiceImpl.insertIntoAgyLocAmendments(agyLocnAmendments);
			}
			if (oumagyhtServiceImpl.checkChanged(old.getSpecialNeedsCode(), newObj.getSpecialNeedsCode())) {
				agyLocnAmendments = new AgencyLocationAmendments();
				agyLocnAmendments.setCreateUserId(newObj.getCreateUserId());
				agyLocnAmendments.setAmendUser(newObj.getCreateUserId());
				agyLocnAmendments.setLvAgyLocId(lvAgyLocId);
				agyLocnAmendments.setpColName("SPECIAL_NEEDS_CODE");
				agyLocnAmendments.setpOldValue(old.getSpecialNeedsCode());
				agyLocnAmendments.setpNewValue(newObj.getSpecialNeedsCode());
				agyLocnAmendments.setpDomain("LOCAL_AUTH");
				agyLocnAmendments.setpDescType("REF_CODE");
				agyLocnAmendments.setAgyLocId(lvAgyLocId);
				oumagyhtServiceImpl.insertIntoAgyLocAmendments(agyLocnAmendments);
			}
			if (oumagyhtServiceImpl.checkChanged(old.getContactPersonName(), newObj.getContactPersonName())) {
				agyLocnAmendments = new AgencyLocationAmendments();
				agyLocnAmendments.setCreateUserId(newObj.getCreateUserId());
				agyLocnAmendments.setAmendUser(newObj.getCreateUserId());
				agyLocnAmendments.setLvAgyLocId(lvAgyLocId);
				agyLocnAmendments.setpColName("CONTACT_PERSON_NAME");
				agyLocnAmendments.setpOldValue(old.getContactPersonName());
				agyLocnAmendments.setpNewValue(newObj.getContactPersonName());
				agyLocnAmendments.setpDomain("");
				agyLocnAmendments.setpDescType("");
				agyLocnAmendments.setAgyLocId(lvAgyLocId);
				oumagyhtServiceImpl.insertIntoAgyLocAmendments(agyLocnAmendments);
			}
			if (oumagyhtServiceImpl.checkChanged(old.getBusinessHour(), newObj.getBusinessHour())) {
				agyLocnAmendments = new AgencyLocationAmendments();
				agyLocnAmendments.setCreateUserId(newObj.getCreateUserId());
				agyLocnAmendments.setAmendUser(newObj.getCreateUserId());
				agyLocnAmendments.setLvAgyLocId(lvAgyLocId);
				agyLocnAmendments.setpColName("BUSINESS_HOUR");
				agyLocnAmendments.setpOldValue(old.getBusinessHour());
				agyLocnAmendments.setpNewValue(newObj.getBusinessHour());
				agyLocnAmendments.setpDomain("");
				agyLocnAmendments.setpDescType("");
				agyLocnAmendments.setAgyLocId(lvAgyLocId);
				oumagyhtServiceImpl.insertIntoAgyLocAmendments(agyLocnAmendments);
			}
			if (oumagyhtServiceImpl.checkChanged(old.getStartDate()!=null?dateFormat.format(old.getStartDate()):null,
					newObj.getStartDate()!=null?dateFormat.format(newObj.getStartDate()):null)) {
				agyLocnAmendments = new AgencyLocationAmendments();
				agyLocnAmendments.setCreateUserId(newObj.getCreateUserId());
				agyLocnAmendments.setAmendUser(newObj.getCreateUserId());
				agyLocnAmendments.setLvAgyLocId(lvAgyLocId);
				agyLocnAmendments.setpColName("START_DATE");
				agyLocnAmendments.setpOldValue(old.getStartDate()!=null?dateFormat.format(old.getStartDate()):null);
				agyLocnAmendments.setpNewValue(newObj.getStartDate()!=null?dateFormat.format(newObj.getStartDate()):null);
				agyLocnAmendments.setpDomain("");
				agyLocnAmendments.setpDescType("");
				agyLocnAmendments.setAgyLocId(lvAgyLocId);
				oumagyhtServiceImpl.insertIntoAgyLocAmendments(agyLocnAmendments);
			}
			if (oumagyhtServiceImpl.checkChanged(old.getEndDate()!=null?dateFormat.format(old.getEndDate()):null,
					newObj.getEndDate()!=null?dateFormat.format(newObj.getEndDate()):null)) {
				agyLocnAmendments = new AgencyLocationAmendments();
				agyLocnAmendments.setCreateUserId(newObj.getCreateUserId());
				agyLocnAmendments.setAmendUser(newObj.getCreateUserId());
				agyLocnAmendments.setLvAgyLocId(lvAgyLocId);
				agyLocnAmendments.setpColName("END_DATE");
				agyLocnAmendments.setpOldValue(old.getEndDate()!=null?dateFormat.format(old.getEndDate()):null);
				agyLocnAmendments.setpNewValue(newObj.getEndDate()!=null?dateFormat.format(newObj.getEndDate()):null);
				agyLocnAmendments.setpDomain("");
				agyLocnAmendments.setpDescType("");
				agyLocnAmendments.setAgyLocId(lvAgyLocId);
				oumagyhtServiceImpl.insertIntoAgyLocAmendments(agyLocnAmendments);
			}
		}

	}

}

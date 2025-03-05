package net.syscon.s4.triggers.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.im.beans.AgencyLocationAmendments;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.pkgs.oumagyht.impl.OumagyhtPkgServiceImpl;
import net.syscon.s4.triggers.AgencyLocationsT1Service;
/* =========================================================
Below comments are copied from AGENCY_LOCATIONS_T1 Trigger
========================================================= */

/* MODIFICATION HISTORY
Person        Date               Version     Comments
---------   	 ------            ----------  ------------------------------
Vikas Grover 	 22-FEB-2007  		1.1.1.0     Delete code for deleted columns cjit_code, service_required_flag,
				 									 update_allowed_flag and long_description
Patrick      	 27-JUN-2005        1.1   		Changed for more history tracking.
Patrick      	 21-JUN-2005    	1.0   		Initial Version

*/
@Service
public class AgencyLocationsT1ServiceImpl implements AgencyLocationsT1Service {

	@Autowired
	OumagyhtPkgServiceImpl oumagyhtServiceImpl;
	final DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

	@Override
	public Integer agencyLocationsT1Trigger(final AgencyLocations oldRef, final AgencyLocations newRef, final String userId) {
		AgencyLocationAmendments agyLocnAmendments = new AgencyLocationAmendments();
		if (oumagyhtServiceImpl.checkChanged(oldRef.getAgyLocId(), newRef.getAgyLocId())) {
			agyLocnAmendments = new AgencyLocationAmendments();
			agyLocnAmendments.setCreateUserId(userId);
			agyLocnAmendments.setModifyUserId(userId);
			agyLocnAmendments.setAmendUser(userId);
			agyLocnAmendments.setLvAgyLocId(newRef.getAgyLocId());
			agyLocnAmendments.setAgyLocId(newRef.getAgyLocId());
			agyLocnAmendments.setpColName("AGY_LOC_ID");
			agyLocnAmendments.setpOldValue(oldRef.getAgyLocId());
			agyLocnAmendments.setpNewValue(newRef.getAgyLocId());
			agyLocnAmendments.setpDomain("");
			agyLocnAmendments.setpDescType("");
			oumagyhtServiceImpl.insertIntoAgyLocAmendments(agyLocnAmendments);
		}
		if (oumagyhtServiceImpl.checkChanged(oldRef.getDescription(), newRef.getDescription())) {
			agyLocnAmendments = new AgencyLocationAmendments();
			agyLocnAmendments.setCreateUserId(userId);
			agyLocnAmendments.setModifyUserId(userId);
			agyLocnAmendments.setAmendUser(userId);
			agyLocnAmendments.setLvAgyLocId(newRef.getAgyLocId());
			agyLocnAmendments.setAgyLocId(newRef.getAgyLocId());
			agyLocnAmendments.setpColName("DESCRIPTION");
			agyLocnAmendments.setpOldValue(oldRef.getDescription());
			agyLocnAmendments.setpNewValue(newRef.getDescription());
			agyLocnAmendments.setpDomain("");
			agyLocnAmendments.setpDescType("AGY");
			oumagyhtServiceImpl.insertIntoAgyLocAmendments(agyLocnAmendments);
		}
		if (oumagyhtServiceImpl.checkChanged(oldRef.getAgencyLocationType(), newRef.getAgencyLocationType())) {
			agyLocnAmendments = new AgencyLocationAmendments();
			agyLocnAmendments.setCreateUserId(userId);
			agyLocnAmendments.setModifyUserId(userId);
			agyLocnAmendments.setAmendUser(userId);
			agyLocnAmendments.setLvAgyLocId(newRef.getAgyLocId());
			agyLocnAmendments.setAgyLocId(newRef.getAgyLocId());
			agyLocnAmendments.setpColName("AGENCY_LOCATION_TYPE");
			agyLocnAmendments.setpOldValue(oldRef.getAgencyLocationType());
			agyLocnAmendments.setpNewValue(newRef.getAgencyLocationType());
			agyLocnAmendments.setpDomain("AGY_LOC_TYPE");
			agyLocnAmendments.setpDescType("REF_CODE");
			oumagyhtServiceImpl.insertIntoAgyLocAmendments(agyLocnAmendments);
		}
		if (oumagyhtServiceImpl.checkChanged(oldRef.getDistrictCode(), newRef.getDistrictCode())) {
			agyLocnAmendments = new AgencyLocationAmendments();
			agyLocnAmendments.setCreateUserId(userId);
			agyLocnAmendments.setModifyUserId(userId);
			agyLocnAmendments.setAmendUser(userId);
			agyLocnAmendments.setLvAgyLocId(newRef.getAgyLocId());
			agyLocnAmendments.setAgyLocId(newRef.getAgyLocId());
			agyLocnAmendments.setpColName("DISTRICT_CODE");
			agyLocnAmendments.setpOldValue(oldRef.getDistrictCode());
			agyLocnAmendments.setpNewValue(newRef.getDistrictCode());
			agyLocnAmendments.setpDomain("DISTRICT");
			agyLocnAmendments.setpDescType("REF_CODE");
			oumagyhtServiceImpl.insertIntoAgyLocAmendments(agyLocnAmendments);
		}
		if (oumagyhtServiceImpl.checkChanged(oldRef.getAbbreviation(), newRef.getAbbreviation())) {
			agyLocnAmendments = new AgencyLocationAmendments();
			agyLocnAmendments.setCreateUserId(userId);
			agyLocnAmendments.setModifyUserId(userId);
			agyLocnAmendments.setAmendUser(userId);
			agyLocnAmendments.setLvAgyLocId(newRef.getAgyLocId());
			agyLocnAmendments.setAgyLocId(newRef.getAgyLocId());
			agyLocnAmendments.setpColName("ABBREVIATION");
			agyLocnAmendments.setpOldValue(oldRef.getAbbreviation());
			agyLocnAmendments.setpNewValue(newRef.getAbbreviation());
			agyLocnAmendments.setpDomain("");
			agyLocnAmendments.setpDescType("");
			oumagyhtServiceImpl.insertIntoAgyLocAmendments(agyLocnAmendments);
		}
		if (oumagyhtServiceImpl.checkChanged(dateFormat.format(oldRef.getDeactivationDate()),
				dateFormat.format(newRef.getDeactivationDate()))) {
			agyLocnAmendments = new AgencyLocationAmendments();
			agyLocnAmendments.setCreateUserId(userId);
			agyLocnAmendments.setModifyUserId(userId);
			agyLocnAmendments.setAmendUser(userId);
			agyLocnAmendments.setLvAgyLocId(newRef.getAgyLocId());
			agyLocnAmendments.setAgyLocId(newRef.getAgyLocId());
			agyLocnAmendments.setpColName("DEACTIVATION_DATE");
			agyLocnAmendments.setpOldValue(dateFormat.format(oldRef.getDeactivationDate()));
			agyLocnAmendments.setpNewValue(dateFormat.format(newRef.getDeactivationDate()));
			agyLocnAmendments.setpDomain("");
			agyLocnAmendments.setpDescType("DATE");
			oumagyhtServiceImpl.insertIntoAgyLocAmendments(agyLocnAmendments);
		}
		if (oumagyhtServiceImpl.checkChanged(oldRef.getContactName(), newRef.getContactName())) {
			agyLocnAmendments = new AgencyLocationAmendments();
			agyLocnAmendments.setCreateUserId(userId);
			agyLocnAmendments.setModifyUserId(userId);
			agyLocnAmendments.setAmendUser(userId);
			agyLocnAmendments.setLvAgyLocId(newRef.getAgyLocId());
			agyLocnAmendments.setAgyLocId(newRef.getAgyLocId());
			agyLocnAmendments.setpColName("CONTACT_NAME");
			agyLocnAmendments.setpOldValue(oldRef.getContactName());
			agyLocnAmendments.setpNewValue(newRef.getContactName());
			agyLocnAmendments.setpDomain("");
			agyLocnAmendments.setpDescType("");
			oumagyhtServiceImpl.insertIntoAgyLocAmendments(agyLocnAmendments);
		}
		if (oumagyhtServiceImpl.checkChanged(oldRef.getPrintQueue(), newRef.getPrintQueue())) {
			agyLocnAmendments = new AgencyLocationAmendments();
			agyLocnAmendments.setCreateUserId(userId);
			agyLocnAmendments.setModifyUserId(userId);
			agyLocnAmendments.setAmendUser(userId);
			agyLocnAmendments.setLvAgyLocId(newRef.getAgyLocId());
			agyLocnAmendments.setAgyLocId(newRef.getAgyLocId());
			agyLocnAmendments.setpColName("PRINT_QUEUE");
			agyLocnAmendments.setpOldValue(oldRef.getPrintQueue());
			agyLocnAmendments.setpNewValue(newRef.getPrintQueue());
			agyLocnAmendments.setpDomain("");
			agyLocnAmendments.setpDescType("");
			oumagyhtServiceImpl.insertIntoAgyLocAmendments(agyLocnAmendments);
		}
		if (oumagyhtServiceImpl.checkChanged(oldRef.getJurisdictionCode(), newRef.getJurisdictionCode())) {
			agyLocnAmendments = new AgencyLocationAmendments();
			agyLocnAmendments.setCreateUserId(userId);
			agyLocnAmendments.setModifyUserId(userId);
			agyLocnAmendments.setAmendUser(userId);
			agyLocnAmendments.setLvAgyLocId(newRef.getAgyLocId());
			agyLocnAmendments.setAgyLocId(newRef.getAgyLocId());
			agyLocnAmendments.setpColName("JURISDICTION_CODE");
			agyLocnAmendments.setpOldValue(oldRef.getJurisdictionCode());
			agyLocnAmendments.setpNewValue(newRef.getJurisdictionCode());
			agyLocnAmendments.setpDomain("JURISDICTION");
			agyLocnAmendments.setpDescType("REF_CODE");
			oumagyhtServiceImpl.insertIntoAgyLocAmendments(agyLocnAmendments);
		}
		if (oumagyhtServiceImpl.checkChanged(oldRef.getBailOfficeFlag(), newRef.getBailOfficeFlag())) {
			agyLocnAmendments = new AgencyLocationAmendments();
			agyLocnAmendments.setCreateUserId(userId);
			agyLocnAmendments.setModifyUserId(userId);
			agyLocnAmendments.setAmendUser(userId);
			agyLocnAmendments.setLvAgyLocId(newRef.getAgyLocId());
			agyLocnAmendments.setAgyLocId(newRef.getAgyLocId());
			agyLocnAmendments.setpColName("BAIL_OFFICE_FLAG");
			agyLocnAmendments.setpOldValue(oldRef.getBailOfficeFlag());
			agyLocnAmendments.setpNewValue(newRef.getBailOfficeFlag());
			agyLocnAmendments.setpDomain("");
			agyLocnAmendments.setpDescType("");
			oumagyhtServiceImpl.insertIntoAgyLocAmendments(agyLocnAmendments);
		}
		if (oumagyhtServiceImpl.checkChanged(String.valueOf(oldRef.getListSeq()),
				String.valueOf(newRef.getListSeq()))) {
			agyLocnAmendments = new AgencyLocationAmendments();
			agyLocnAmendments.setCreateUserId(userId);
			agyLocnAmendments.setModifyUserId(userId);
			agyLocnAmendments.setAmendUser(userId);
			agyLocnAmendments.setLvAgyLocId(newRef.getAgyLocId());
			agyLocnAmendments.setAgyLocId(newRef.getAgyLocId());
			agyLocnAmendments.setpColName("LIST_SEQ");
			agyLocnAmendments.setpOldValue(String.valueOf(oldRef.getListSeq()));
			agyLocnAmendments.setpNewValue(String.valueOf(newRef.getListSeq()));
			agyLocnAmendments.setpDomain("");
			agyLocnAmendments.setpDescType("");
			oumagyhtServiceImpl.insertIntoAgyLocAmendments(agyLocnAmendments);
		}
		if (oumagyhtServiceImpl.checkChanged(oldRef.getHousingLev1Code(), newRef.getHousingLev1Code())) {
			agyLocnAmendments = new AgencyLocationAmendments();
			agyLocnAmendments.setCreateUserId(userId);
			agyLocnAmendments.setModifyUserId(userId);
			agyLocnAmendments.setAmendUser(userId);
			agyLocnAmendments.setAgyLocId(newRef.getAgyLocId());
			agyLocnAmendments.setLvAgyLocId(newRef.getAgyLocId());
			agyLocnAmendments.setpColName("HOUSING_LEV_1_CODE");
			agyLocnAmendments.setpOldValue(oldRef.getHousingLev1Code());
			agyLocnAmendments.setpNewValue(newRef.getHousingLev1Code());
			agyLocnAmendments.setpDomain("LIVING_UNIT");
			agyLocnAmendments.setpDescType("REF_CODE");
			oumagyhtServiceImpl.insertIntoAgyLocAmendments(agyLocnAmendments);
		}
		if (oumagyhtServiceImpl.checkChanged(oldRef.getHousingLev2Code(), newRef.getHousingLev2Code())) {
			agyLocnAmendments = new AgencyLocationAmendments();
			agyLocnAmendments.setCreateUserId(userId);
			agyLocnAmendments.setModifyUserId(userId);
			agyLocnAmendments.setAmendUser(userId);
			agyLocnAmendments.setLvAgyLocId(newRef.getAgyLocId());
			agyLocnAmendments.setAgyLocId(newRef.getAgyLocId());
			agyLocnAmendments.setpColName("HOUSING_LEV_2_CODE");
			agyLocnAmendments.setpOldValue(oldRef.getHousingLev2Code());
			agyLocnAmendments.setpNewValue(newRef.getHousingLev2Code());
			agyLocnAmendments.setpDomain("LIVING_UNIT");
			agyLocnAmendments.setpDescType("REF_CODE");
			oumagyhtServiceImpl.insertIntoAgyLocAmendments(agyLocnAmendments);
		}
		if (oumagyhtServiceImpl.checkChanged(oldRef.getHousingLev3Code(), newRef.getHousingLev3Code())) {
			agyLocnAmendments = new AgencyLocationAmendments();
			agyLocnAmendments.setCreateUserId(userId);
			agyLocnAmendments.setModifyUserId(userId);
			agyLocnAmendments.setAmendUser(userId);
			agyLocnAmendments.setLvAgyLocId(newRef.getAgyLocId());
			agyLocnAmendments.setAgyLocId(newRef.getAgyLocId());
			agyLocnAmendments.setpColName("HOUSING_LEV_3_CODE");
			agyLocnAmendments.setpOldValue(oldRef.getHousingLev3Code());
			agyLocnAmendments.setpNewValue(newRef.getHousingLev3Code());
			agyLocnAmendments.setpDomain("LIVING_UNIT");
			agyLocnAmendments.setpDescType("REF_CODE");
			oumagyhtServiceImpl.insertIntoAgyLocAmendments(agyLocnAmendments);
		}
		if (oumagyhtServiceImpl.checkChanged(oldRef.getHousingLev4Code(), newRef.getHousingLev4Code())) {
			agyLocnAmendments = new AgencyLocationAmendments();
			agyLocnAmendments.setCreateUserId(userId);
			agyLocnAmendments.setModifyUserId(userId);
			agyLocnAmendments.setAmendUser(userId);
			agyLocnAmendments.setLvAgyLocId(newRef.getAgyLocId());
			agyLocnAmendments.setAgyLocId(newRef.getAgyLocId());
			agyLocnAmendments.setpColName("HOUSING_LEV_4_CODE");
			agyLocnAmendments.setpOldValue(oldRef.getHousingLev4Code());
			agyLocnAmendments.setpNewValue(newRef.getHousingLev4Code());
			agyLocnAmendments.setpDomain("LIVING_UNIT");
			agyLocnAmendments.setpDescType("REF_CODE");
			oumagyhtServiceImpl.insertIntoAgyLocAmendments(agyLocnAmendments);
		}
		if (oumagyhtServiceImpl.checkChanged(oldRef.getPropertyLev1Code(), newRef.getPropertyLev1Code())) {
			agyLocnAmendments = new AgencyLocationAmendments();
			agyLocnAmendments.setCreateUserId(userId);
			agyLocnAmendments.setModifyUserId(userId);
			agyLocnAmendments.setAmendUser(userId);
			agyLocnAmendments.setLvAgyLocId(newRef.getAgyLocId());
			agyLocnAmendments.setAgyLocId(newRef.getAgyLocId());
			agyLocnAmendments.setpColName("PROPERTY_LEV_1_CODE");
			agyLocnAmendments.setpOldValue(oldRef.getPropertyLev1Code());
			agyLocnAmendments.setpNewValue(newRef.getPropertyLev1Code());
			agyLocnAmendments.setpDomain("PPTY_STG");
			agyLocnAmendments.setpDescType("REF_CODE");
			oumagyhtServiceImpl.insertIntoAgyLocAmendments(agyLocnAmendments);
		}
		if (oumagyhtServiceImpl.checkChanged(oldRef.getPropertyLev2Code(), newRef.getPropertyLev2Code())) {
			agyLocnAmendments = new AgencyLocationAmendments();
			agyLocnAmendments.setCreateUserId(userId);
			agyLocnAmendments.setModifyUserId(userId);
			agyLocnAmendments.setAmendUser(userId);
			agyLocnAmendments.setLvAgyLocId(newRef.getAgyLocId());
			agyLocnAmendments.setAgyLocId(newRef.getAgyLocId());
			agyLocnAmendments.setpColName("PROPERTY_LEV_2_CODE");
			agyLocnAmendments.setpOldValue(oldRef.getPropertyLev2Code());
			agyLocnAmendments.setpNewValue(newRef.getPropertyLev2Code());
			agyLocnAmendments.setpDomain("PPTY_STG");
			agyLocnAmendments.setpDescType("REF_CODE");
			oumagyhtServiceImpl.insertIntoAgyLocAmendments(agyLocnAmendments);
		}
		if (oumagyhtServiceImpl.checkChanged(oldRef.getPropertyLev3Code(), newRef.getPropertyLev3Code())) {
			agyLocnAmendments = new AgencyLocationAmendments();
			agyLocnAmendments.setCreateUserId(userId);
			agyLocnAmendments.setModifyUserId(userId);
			agyLocnAmendments.setAmendUser(userId);
			agyLocnAmendments.setLvAgyLocId(newRef.getAgyLocId());
			agyLocnAmendments.setAgyLocId(newRef.getAgyLocId());
			agyLocnAmendments.setpColName("PROPERTY_LEV_3_CODE");
			agyLocnAmendments.setpOldValue(oldRef.getPropertyLev3Code());
			agyLocnAmendments.setpNewValue(newRef.getPropertyLev3Code());
			agyLocnAmendments.setpDomain("PPTY_STG");
			agyLocnAmendments.setpDescType("REF_CODE");
			oumagyhtServiceImpl.insertIntoAgyLocAmendments(agyLocnAmendments);
		}
		if (oumagyhtServiceImpl.checkChanged(String.valueOf(oldRef.getLastBookingNo()),
				String.valueOf(newRef.getLastBookingNo()))) {
			agyLocnAmendments = new AgencyLocationAmendments();
			agyLocnAmendments.setCreateUserId(userId);
			agyLocnAmendments.setModifyUserId(userId);
			agyLocnAmendments.setAmendUser(userId);
			agyLocnAmendments.setLvAgyLocId(newRef.getAgyLocId());
			agyLocnAmendments.setAgyLocId(newRef.getAgyLocId());
			agyLocnAmendments.setpColName("LAST_BOOKING_NO");
			agyLocnAmendments.setpOldValue(String.valueOf(oldRef.getLastBookingNo()));
			agyLocnAmendments.setpDomain(String.valueOf(newRef.getLastBookingNo()));
			agyLocnAmendments.setpDescType("");
			oumagyhtServiceImpl.insertIntoAgyLocAmendments(agyLocnAmendments);
		}
		if (oumagyhtServiceImpl.checkChanged(oldRef.getCommissaryPrivilege(), newRef.getCommissaryPrivilege())) {
			agyLocnAmendments = new AgencyLocationAmendments();
			agyLocnAmendments.setCreateUserId(userId);
			agyLocnAmendments.setModifyUserId(userId);
			agyLocnAmendments.setAmendUser(userId);
			agyLocnAmendments.setLvAgyLocId(newRef.getAgyLocId());
			agyLocnAmendments.setAgyLocId(newRef.getAgyLocId());
			agyLocnAmendments.setpColName("COMMISSARY_PRIVILEGE");
			agyLocnAmendments.setpOldValue(oldRef.getCommissaryPrivilege());
			agyLocnAmendments.setpDomain(newRef.getCommissaryPrivilege());
			agyLocnAmendments.setpDescType("");
			oumagyhtServiceImpl.insertIntoAgyLocAmendments(agyLocnAmendments);
		}
		if (oumagyhtServiceImpl.checkChanged(oldRef.getBusinessHours(), newRef.getBusinessHours())) {
			agyLocnAmendments = new AgencyLocationAmendments();
			agyLocnAmendments.setCreateUserId(userId);
			agyLocnAmendments.setModifyUserId(userId);
			agyLocnAmendments.setAmendUser(userId);
			agyLocnAmendments.setLvAgyLocId(newRef.getAgyLocId());
			agyLocnAmendments.setAgyLocId(newRef.getAgyLocId());
			agyLocnAmendments.setpColName("BUSINESS_HOURS");
			agyLocnAmendments.setpOldValue(oldRef.getBusinessHours());
			agyLocnAmendments.setpDomain(newRef.getBusinessHours());
			agyLocnAmendments.setpDescType("");
			oumagyhtServiceImpl.insertIntoAgyLocAmendments(agyLocnAmendments);
		}
		if (oumagyhtServiceImpl.checkChanged(oldRef.getAddressType(), newRef.getAddressType())) {
			agyLocnAmendments = new AgencyLocationAmendments();
			agyLocnAmendments.setCreateUserId(userId);
			agyLocnAmendments.setModifyUserId(userId);
			agyLocnAmendments.setAmendUser(userId);
			agyLocnAmendments.setLvAgyLocId(newRef.getAgyLocId());
			agyLocnAmendments.setAgyLocId(newRef.getAgyLocId());
			agyLocnAmendments.setpColName("ADDRESS_TYPE");
			agyLocnAmendments.setpOldValue(oldRef.getAddressType());
			agyLocnAmendments.setpNewValue(newRef.getAddressType());
			agyLocnAmendments.setpDomain("ADDR_TYPE");
			agyLocnAmendments.setpDescType("REF_CODE");
			oumagyhtServiceImpl.insertIntoAgyLocAmendments(agyLocnAmendments);
		}
		if (oumagyhtServiceImpl.checkChanged(oldRef.getActiveFlag(), newRef.getActiveFlag())) {
			agyLocnAmendments = new AgencyLocationAmendments();
			agyLocnAmendments.setCreateUserId(userId);
			agyLocnAmendments.setModifyUserId(userId);
			agyLocnAmendments.setAmendUser(userId);
			agyLocnAmendments.setLvAgyLocId(newRef.getAgyLocId());
			agyLocnAmendments.setAgyLocId(newRef.getAgyLocId());
			agyLocnAmendments.setpColName("ACTIVE_FLAG");
			agyLocnAmendments.setpOldValue(oldRef.getActiveFlag());
			agyLocnAmendments.setpNewValue(newRef.getActiveFlag());
			agyLocnAmendments.setpDomain("");
			agyLocnAmendments.setpDescType("");
			oumagyhtServiceImpl.insertIntoAgyLocAmendments(agyLocnAmendments);
		}
		if (oumagyhtServiceImpl.checkChanged(oldRef.getDisabilityAccessCode(), newRef.getDisabilityAccessCode())) {
			agyLocnAmendments = new AgencyLocationAmendments();
			agyLocnAmendments.setCreateUserId(userId);
			agyLocnAmendments.setModifyUserId(userId);
			agyLocnAmendments.setAmendUser(userId);
			agyLocnAmendments.setLvAgyLocId(newRef.getAgyLocId());
			agyLocnAmendments.setAgyLocId(newRef.getAgyLocId());
			agyLocnAmendments.setpColName("DISABILITY_ACCESS_CODE");
			agyLocnAmendments.setpOldValue(oldRef.getDisabilityAccessCode());
			agyLocnAmendments.setpNewValue(newRef.getDisabilityAccessCode());
			agyLocnAmendments.setpDomain("");
			agyLocnAmendments.setpDescType("");
			oumagyhtServiceImpl.insertIntoAgyLocAmendments(agyLocnAmendments);
		}
		if (oumagyhtServiceImpl.checkChanged(oldRef.getIntakeFlag(), newRef.getIntakeFlag())) {
			agyLocnAmendments = new AgencyLocationAmendments();
			agyLocnAmendments.setCreateUserId(userId);
			agyLocnAmendments.setModifyUserId(userId);
			agyLocnAmendments.setAmendUser(userId);
			agyLocnAmendments.setLvAgyLocId(newRef.getAgyLocId());
			agyLocnAmendments.setAgyLocId(newRef.getAgyLocId());
			agyLocnAmendments.setpColName("INTAKE_FLAG");
			agyLocnAmendments.setpOldValue(oldRef.getIntakeFlag());
			agyLocnAmendments.setpNewValue(newRef.getIntakeFlag());
			agyLocnAmendments.setpDomain("");
			agyLocnAmendments.setpDescType("");
			oumagyhtServiceImpl.insertIntoAgyLocAmendments(agyLocnAmendments);
		}
		if (oumagyhtServiceImpl.checkChanged(oldRef.getSubAreaCode(), newRef.getSubAreaCode())) {
			agyLocnAmendments = new AgencyLocationAmendments();
			agyLocnAmendments.setCreateUserId(userId);
			agyLocnAmendments.setModifyUserId(userId);
			agyLocnAmendments.setAmendUser(userId);
			agyLocnAmendments.setLvAgyLocId(newRef.getAgyLocId());
			agyLocnAmendments.setAgyLocId(newRef.getAgyLocId());
			agyLocnAmendments.setpColName("SUB_AREA_CODE");
			agyLocnAmendments.setpOldValue(oldRef.getSubAreaCode());
			agyLocnAmendments.setpNewValue(newRef.getSubAreaCode());
			agyLocnAmendments.setpDomain("SUB_AREA");
			agyLocnAmendments.setpDescType("REF_CODE");
			oumagyhtServiceImpl.insertIntoAgyLocAmendments(agyLocnAmendments);
		}
		if (oumagyhtServiceImpl.checkChanged(oldRef.getAreaCode(), newRef.getAreaCode())) {
			agyLocnAmendments = new AgencyLocationAmendments();
			agyLocnAmendments.setCreateUserId(userId);
			agyLocnAmendments.setModifyUserId(userId);
			agyLocnAmendments.setAmendUser(userId);
			agyLocnAmendments.setLvAgyLocId(newRef.getAgyLocId());
			agyLocnAmendments.setAgyLocId(newRef.getAgyLocId());
			agyLocnAmendments.setpColName("AREA_CODE");
			agyLocnAmendments.setpOldValue(oldRef.getAreaCode());
			agyLocnAmendments.setpNewValue(newRef.getAreaCode());
			agyLocnAmendments.setpDomain("AREA");
			agyLocnAmendments.setpDescType("REF_CODE");
			oumagyhtServiceImpl.insertIntoAgyLocAmendments(agyLocnAmendments);
		}
		if (oumagyhtServiceImpl.checkChanged(oldRef.getNomsRegionCode(), newRef.getNomsRegionCode())) {
			agyLocnAmendments = new AgencyLocationAmendments();
			agyLocnAmendments.setCreateUserId(userId);
			agyLocnAmendments.setModifyUserId(userId);
			agyLocnAmendments.setAmendUser(userId);
			agyLocnAmendments.setLvAgyLocId(newRef.getAgyLocId());
			agyLocnAmendments.setAgyLocId(newRef.getAgyLocId());
			agyLocnAmendments.setpColName("NOMS_REGION_CODE");
			agyLocnAmendments.setpOldValue(oldRef.getNomsRegionCode());
			agyLocnAmendments.setpNewValue(newRef.getNomsRegionCode());
			agyLocnAmendments.setpDomain("REGION");
			agyLocnAmendments.setpDescType("REF_CODE");
			oumagyhtServiceImpl.insertIntoAgyLocAmendments(agyLocnAmendments);
		}
		if (oumagyhtServiceImpl.checkChanged(oldRef.getGeographicRegionCode(), newRef.getGeographicRegionCode())) {
			agyLocnAmendments = new AgencyLocationAmendments();
			agyLocnAmendments.setCreateUserId(userId);
			agyLocnAmendments.setModifyUserId(userId);
			agyLocnAmendments.setAmendUser(userId);
			agyLocnAmendments.setLvAgyLocId(newRef.getAgyLocId());
			agyLocnAmendments.setAgyLocId(newRef.getAgyLocId());
			agyLocnAmendments.setpColName("GEOGRAPHIC_REGION_CODE");
			agyLocnAmendments.setpOldValue(oldRef.getGeographicRegionCode());
			agyLocnAmendments.setpNewValue(newRef.getGeographicRegionCode());
			agyLocnAmendments.setpDomain("GEOGRAPHIC");
			agyLocnAmendments.setpDescType("REF_CODE");
			oumagyhtServiceImpl.insertIntoAgyLocAmendments(agyLocnAmendments);
		}
		if (oumagyhtServiceImpl.checkChanged(oldRef.getJusticeAreaCode(), newRef.getJusticeAreaCode())) {
			agyLocnAmendments = new AgencyLocationAmendments();
			agyLocnAmendments.setCreateUserId(userId);
			agyLocnAmendments.setModifyUserId(userId);
			agyLocnAmendments.setAmendUser(userId);
			agyLocnAmendments.setAgyLocId(newRef.getAgyLocId());
			agyLocnAmendments.setLvAgyLocId(newRef.getAgyLocId());
			agyLocnAmendments.setpColName("JUSTICE_AREA_CODE");
			agyLocnAmendments.setpOldValue(oldRef.getJusticeAreaCode());
			agyLocnAmendments.setpNewValue(newRef.getJusticeAreaCode());
			agyLocnAmendments.setpDomain("LOCAL_AREA");
			agyLocnAmendments.setpDescType("REF_CODE");
			oumagyhtServiceImpl.insertIntoAgyLocAmendments(agyLocnAmendments);
		}

		return null;
	}

}

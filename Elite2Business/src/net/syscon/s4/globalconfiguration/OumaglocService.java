package net.syscon.s4.globalconfiguration;

import java.util.List;

import net.syscon.s4.common.beans.Phones;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.AgencyLocationsCommitBean;
import net.syscon.s4.im.beans.AgyLocEstablishments;
import net.syscon.s4.im.beans.AgyLocEstablishmentsCommitBean;
import net.syscon.s4.im.beans.PhonesCommitBean;
import net.syscon.s4.im.beans.VAgencyAddresses;
import net.syscon.s4.im.beans.VAgencyAddressesCommitBean;
import net.syscon.s4.inst.visitsmanagement.beans.IEPLevelCommitBean;
import net.syscon.s4.inst.visitsmanagement.beans.IepLevelBean;

/**
 * Interface OumaglocService
 */
public interface OumaglocService {
	List<ReferenceCodes> rgEstablishmentTypeRecordGroup();

	List<ReferenceCodes> rgDisabilityAccessCodeRecordGroup();

	VAgencyAddresses agyLocOnCheckDeleteMaster(VAgencyAddresses paramBean);

	Integer phonesCommit(PhonesCommitBean commitBean);

	List<AgencyLocations> agyLocExecuteQuery(AgencyLocations objAgyLoc);

	List<Phones> phonesExecuteQuery(Phones objPhones);

	Object phonesPreInsert();

	Integer vAgyAddrCommit(VAgencyAddressesCommitBean commitBean);

	List<VAgencyAddresses> vAgyAddrExecuteQuery(VAgencyAddresses objVAgyAddresses);

	List<ReferenceCodes> rgPhoneTypeRecordGroup();

	List<ReferenceCodes> rgHousingLevelCodesRecordGroup();

	List<AgyLocEstablishments> agyLocEstExecuteQuery(AgyLocEstablishments objAgyLocEst);

	List<ReferenceCodes> rgJurisdictionRecordGroup();

	Integer agyLocEstCommit(AgyLocEstablishmentsCommitBean commitBean);

	Integer agyLocCommit(AgencyLocationsCommitBean commitBean);

	Phones vAgyAddrOnCheckDeleteMaster(Phones paramBean);

	List<ReferenceCodes> rgAgencyLocationTypeRecordGroup();

	List<ReferenceCodes> rgYnFlagRecordGroup();
	
	Integer  iepLevelCommit(final IEPLevelCommitBean commitBean);
	
	 IepLevelBean getIepdetails(String agyLocId);

}

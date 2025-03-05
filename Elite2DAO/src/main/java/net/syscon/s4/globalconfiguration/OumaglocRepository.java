package net.syscon.s4.globalconfiguration;

import java.util.List;

import net.syscon.s4.common.beans.Phones;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.AgyLocEstablishments;
import net.syscon.s4.im.beans.VAgencyAddresses;
import net.syscon.s4.inst.visitsmanagement.beans.IepLevelBean;

/**
 * Interface OumaglocRepository
 */
public interface OumaglocRepository {
	
	Integer phonesUpdatePhones(List<Phones> lstPhones);

	List<ReferenceCodes> rgEstablishmentTypeRecordGroup();

	List<ReferenceCodes> rgDisabilityAccessCodeRecordGroup();

	List<VAgencyAddresses> agyLocOnCheckDeleteMaster(VAgencyAddresses paramBean);

	List<AgencyLocations> agyLocExecuteQuery(AgencyLocations paramBean);

	Integer agyLocEstInsertAgyLocEstablishments(List<AgyLocEstablishments> lstAgyLocEst);

	List<Phones> phonesExecuteQuery(Phones objPhones);

	Object phonesPreInsert();

	List<VAgencyAddresses> vAgyAddrExecuteQuery(VAgencyAddresses objVAgencyAdd);

	List<Phones> vAgyAddrOnCheckDeleteMaster(Phones paramBean);

	List<ReferenceCodes> rgPhoneTypeRecordGroup();

	Integer agyLocInsertAgencyLocations(List<AgencyLocations> lstAgyLocations);

	AgyLocEstablishments agyLocOnCheckDeleteMaster(AgyLocEstablishments paramBean);

	List<ReferenceCodes> rgHousingLevelCodesRecordGroup();

	Integer phonesInsertPhones(List<Phones> lstPhones);

	Integer phonesDeletePhones(List<Phones> lstPhones);

	List<AgyLocEstablishments> agyLocEstExecuteQuery(AgyLocEstablishments objAgyLocEst);

	Integer agyLocEstDeleteAgyLocEstablishments(List<AgyLocEstablishments> lstAgyLocEst);

	Integer agyLocUpdateAgencyLocations(List<AgencyLocations> lstAgyLocations);

	List<ReferenceCodes> rgJurisdictionRecordGroup();

	Integer agyLocEstUpdateAgyLocEstablishments(List<AgyLocEstablishments> lstAgyLocEst);

	List<ReferenceCodes> rgAgencyLocationTypeRecordGroup();
	
	List<ReferenceCodes> rgYnFlagRecordGroup() ;
	
	AgencyLocations getOldAgencyLocations(String agyLocId);
	
	Integer iepLevelCommit(final IepLevelBean bean);
	
	Integer iepLevelCommitUpdate(final String agyLocid,String iepLevelCode, String modifyUserId);
	
	IepLevelBean getIepdetails(String agyLocId);
	
	Integer iepLevelCommitDelete(final String agyLocid, String modifyUserId);

}

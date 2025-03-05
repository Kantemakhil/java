package net.syscon.s4.inst.property;

import java.util.List;

import net.syscon.s4.common.beans.AgencyInternalLocations;
import net.syscon.s4.im.beans.Images;
import net.syscon.s4.inst.property.bean.OffenderPptyContainers;

/**
 * class OidmpconRepository
 */
public interface OidmpconRepository {
	List<OffenderPptyContainers> vPheadOnCheckDeleteMaster(OffenderPptyContainers paramBean);

	List<AgencyInternalLocations> rgLocationAllRecordGroup(String caseloadId);

	AgencyInternalLocations cgfkchkOffConOffConPpty(AgencyInternalLocations paramBean);

	Integer offConInsertOffenderPptyContainers(List<OffenderPptyContainers> list);
	
	Integer getPropertyContainerId();

	List<OffenderPptyContainers> offConExecuteQuery(OffenderPptyContainers obj);

	Integer offConUpdateOffenderPptyContainers(List<OffenderPptyContainers> list);
	
	List<String> findRgContainerCode();

	Integer checkStorageLocation(String internalLocId);

	List<String> checkPptyItems(Integer propertyConId);

	Integer checkContainerEmptyValue(Integer propertyConId);

	OffenderPptyContainers getSealMarkValueOfpropertyConId(Integer propertyConId);

	AgencyInternalLocations getLocationValue(String agyLocId);

	List<AgencyInternalLocations> rgStoreLocation(String agyLocId);

	List<AgencyInternalLocations> rgDescription(String caseLoadId);

	String lvGetAgyLoc(String offBkgId, String intLocId);
	
	Integer insertContainerImg(OffenderPptyContainers offenderPptyContainers);
	
	List<Images> getImageForVirtualContainers(Object offenderBookId , Integer propertyContainerId );

	int offConUpdateSeal(OffenderPptyContainers commitBean);

	int offConUpdateMultipleSeal(List<OffenderPptyContainers> commitBean);
	
	Integer updateContainerLocation(OffenderPptyContainers obj);
	
	List<AgencyInternalLocations> getAllLocations(String caseloadId);


}

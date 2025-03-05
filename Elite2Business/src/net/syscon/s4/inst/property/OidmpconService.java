package net.syscon.s4.inst.property;

import java.util.List;

import net.syscon.s4.common.beans.AgencyInternalLocations;
import net.syscon.s4.inst.property.bean.OffenderPptyContainers;
import net.syscon.s4.inst.property.bean.OffenderPptyContainersCommitBean;

/**
 * Interface OidmpconService
 */
public interface OidmpconService {
	List<AgencyInternalLocations> rgLocationAllRecordGroup(String caseloadId);

	List<OffenderPptyContainers> vPheadOnCheckDeleteMaster(OffenderPptyContainers paramBean);

	Integer offConCommit(OffenderPptyContainersCommitBean commitBean);

	List<OffenderPptyContainers> offConExecuteQuery(OffenderPptyContainers obj);
	
	List<String> findRgContainerCode();

	Integer checkStorageLocation(String internalLocId);

	List<String> checkPptyItems(Integer propertyConId);

	AgencyInternalLocations cgfkchkOffConOffConPpty(AgencyInternalLocations paramBean);

	Integer checkContainerEmptyValue(Integer propertyConId);

	OffenderPptyContainers getSealMarkValueOfpropertyConId(Integer propertyConId);

	AgencyInternalLocations getLocationValue(String agyLocId);

	List<AgencyInternalLocations> getLocationValuesOfLov(String parentField);
	
	Integer insertContainerImg(OffenderPptyContainers offenderPptyContainers);

	int offConUpdateSeal(OffenderPptyContainers commitBean);

	int offConUpdateMultipleSeal(List<OffenderPptyContainers> commitBean);
	
List<AgencyInternalLocations> getAgyLocationValuesOfLov(String agyLocId,String caseloadId);
	
	Integer updateConatinerIntLocation(OffenderPptyContainers offenderPptyContainers);
	
	List<AgencyInternalLocations> getAllLocations(String caseloadId);


}

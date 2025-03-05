package net.syscon.s4.pkgs.oms_property;

import net.syscon.s4.inst.property.bean.OffenderPptyContainers;
import net.syscon.s4.inst.property.bean.OffenderPptyItems;

public interface OmsPropertyService {

	Integer checkStorageCapacity(final String internalLocId);

	Integer getTranRoomStorageId(final String trnToAgyLocId);

	Integer updateTransferredItems(final OffenderPptyContainers paramBean, final String userName);
	
	void unsealContainer(final Integer propertyContainerId);
	String getUsrAgyLoc();
	String getConAgyLoc(final Integer pPropertyContainerId);
	
	
	Integer updatePropertyItemDetails(final OffenderPptyContainers paramBean);
	
	Integer InsertContainerTransaction(final OffenderPptyContainers newbean,String actionCode);
	
}
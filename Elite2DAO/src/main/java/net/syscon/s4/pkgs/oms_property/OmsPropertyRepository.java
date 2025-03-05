package net.syscon.s4.pkgs.oms_property;

import java.util.List;

import net.syscon.s4.inst.property.bean.OffenderPptyContainers;
import net.syscon.s4.inst.property.bean.OffenderPptyItems;

public interface OmsPropertyRepository {

	Integer checkStorageCapacity(final String internalLocId);

	Integer getTranRoomStorageId(final String trnToAgyLocId);

	Integer offenderPptyItemsUpdate(final OffenderPptyContainers paramBean);

	Integer unsealContainerUpdate(final Integer propertyContainerId);

	String getUsrAgyLocSelect();

	String getConAgyLocSelect(final Integer propertyContainerId);

	List<OffenderPptyItems> getOldDataOffenderPptyItems(Integer propertyContainerId);

}
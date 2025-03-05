package net.syscon.s4.inst.property;

import java.util.List;

import net.syscon.s4.inst.property.bean.Group;
import net.syscon.s4.inst.property.bean.OffenderPptyContainers;
import net.syscon.s4.inst.property.bean.OffenderPptyItems;

/**
 * Interface OidmpitmService
 * 
 * 
 */
public interface OidmpitmService {
	List<OffenderPptyItems> offPiExecuteQuery(OffenderPptyItems objOffPptyItems);

	List<Group> fetchGroupNames(String caseloadId);

	List<OffenderPptyItems> getDefaultValuesForSelecteGroup(String groupId);

	List<OffenderPptyItems> setpropDescForPropertyAttr(List<OffenderPptyItems> propertyItems);

	List<OffenderPptyContainers> offPiSearchOffenderPptyItemsForcontainer(List<OffenderPptyContainers> containersList);

	boolean isRegisterProOrContainerExist(Integer offenderBookId);

	Integer deactivateContainer(OffenderPptyItems property);
}

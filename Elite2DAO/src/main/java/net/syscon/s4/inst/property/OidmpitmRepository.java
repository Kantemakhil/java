package net.syscon.s4.inst.property;

import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.im.beans.SysDual;
import net.syscon.s4.inst.property.bean.Group;
import net.syscon.s4.inst.property.bean.OffenderPptyContainers;
import net.syscon.s4.inst.property.bean.OffenderPptyItemEvents;
import net.syscon.s4.inst.property.bean.OffenderPptyItems;

public interface OidmpitmRepository {

	List<OffenderPptyItems> offPiExecuteQuery(OffenderPptyItems obj);

	List<Group> fetchGroupNames(String caseloadId);

	List<OffenderPptyItems> getDefaultValuesForSelecteGroup(String groupId);

	List<OffenderPptyItems> offPiSearchOffenderPptyItemsForcontainer(OffenderPptyContainers container);

	boolean isRegisterProOrContainerExist(Integer offenderBookId);
	List<OffenderPptyItems> getContainerProps(OffenderPptyItems property);

	Integer getEventSeq();

	Integer saveEvent(List<OffenderPptyItemEvents> insertEventList);

	Integer updateProperties(List<OffenderPptyItems> propList);

	Integer deactivateContainer(List<OffenderPptyContainers> updateCon);
	
	OffenderPptyItems updatePropertiesOldData(OffenderPptyItems offPpty);


	
}

package net.syscon.s4.inst.property;

import java.util.List;
import java.util.Map;

import net.syscon.s4.common.beans.OffenderBookings;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.SysDual;
import net.syscon.s4.inst.property.bean.OffenderPptyContainers;
import net.syscon.s4.inst.property.bean.OffenderPptyItems;

/**
 * Interface OidrtconRepository
 */
public interface OidrtconRepository {
	List<ReferenceCodes> cgfklkpOffConOffConRef(ReferenceCodes paramBean);

	List<SysDual> cgwhenNewFormInstance(SysDual paramBean);

	List<ReferenceCodes> cgfkchkOffConOffConRef(ReferenceCodes paramBean);

	Map<String, Object> cgfkchkOffConOffConOff(OffenderBookings paramBean);

	List<OffenderPptyContainers> offConExecuteQuery(OffenderPptyContainers obj);

	Integer offConUpdateOffenderPptyContainers(List<OffenderPptyContainers> obj);

	List<AgencyLocations> oidrtconRecievedFromLov();

	Integer postUpdateOfOffenderPptyItems(OffenderPptyContainers obj);

	Integer getTranRoomStoradeId(OffenderPptyContainers obj);

	OffenderPptyItems gettingOldOffenderPptyItemsData(Integer propertyContainerId);

}

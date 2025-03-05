package net.syscon.s4.inst.property;

import java.util.List;

import net.syscon.s4.common.beans.AgencyInternalLocations;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.Dual;
import net.syscon.s4.inst.property.bean.OffenderPptyContainers;
import net.syscon.s4.inst.property.bean.OffenderPptyItems;
import net.syscon.s4.inst.property.bean.PropertyStorages;

/**
 * Interface OidtpconRepository
 */
public interface OidtpconRepository {
	List<Object> vPheadOnCheckDeleteMaster(OffenderPptyContainers paramBean);

	List<AgencyLocations> cgfkOffConTrnToAgyLocIdRecordGroup(String agyLocId);

	AgencyLocations cgfkchkOffConOffConAgy(AgencyLocations paramBean);

	String offConWhenNewRecordInstance(Integer paramBean);

	PropertyStorages cgfkchkOffConOffConPpty(PropertyStorages paramBean);

	Dual cgwhenNewFormInstance(String user);

	ReferenceCodes cgfkchkOffConOffConRef(ReferenceCodes paramBean);

	AgencyInternalLocations offConPostQuery(AgencyInternalLocations paramBean);

	List<AgencyLocations> rgSelectAllRecordGroup();

	List<SystemProfiles> sysPflExecuteQuery(SystemProfiles objSystemProfiles);

	List<OffenderPptyContainers> offConExecuteQuery(OffenderPptyContainers offPptyCon);

	Integer offConUpdateOffenderPptyContainers(List<OffenderPptyContainers> offPptyCon);

	List<OffenderPptyItems> getItemStatus(OffenderPptyItems paramBean);

	Integer postUpdateOfOffenderPptyItems(OffenderPptyContainers offderPptyCon);

	OffenderPptyItems gettingOldOffenderPptyItemsData(Integer propertyContainerId);
	

	List<OffenderPptyContainers> offPenPropConExecuteQuery(OffenderPptyContainers offPropContObj);

	


}

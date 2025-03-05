package net.syscon.s4.inst.property;

import java.util.List;

import net.syscon.s4.common.beans.AgencyInternalLocations;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.PropertyStorages;
import net.syscon.s4.inst.property.bean.OffenderPptyConTxns;
import net.syscon.s4.inst.property.bean.OffenderPptyContainers;

/**
 * Interface OiipctraRepository
 */
public interface OiipctraRepository {
	List<OffenderPptyContainers> vPheadOnCheckDeleteMaster(OffenderPptyContainers paramBean);

	AgencyLocations offConPostQuery(AgencyLocations paramBean);

	List<OffenderPptyContainers> offConExecuteQuery(OffenderPptyContainers objOffenderPptyContainers);

	List<OffenderPptyConTxns> conTxExecuteQuery(OffenderPptyConTxns objOffenderPptyConTxns);

	List<AgencyLocations> conTxPostQuery(AgencyLocations paramBean);

	List<PropertyStorages> cgfkchkOffConOffConPpty(PropertyStorages paramBean);

	AgencyInternalLocations cgfkchkOffConOffConPpty(AgencyInternalLocations paramBean);
	
	List<AgencyInternalLocations> rgLocationAllRecordGroup(String caseloadId);

}

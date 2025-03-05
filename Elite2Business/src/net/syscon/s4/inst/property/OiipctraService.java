package net.syscon.s4.inst.property;

import java.util.List;

import net.syscon.s4.common.beans.AgencyInternalLocations;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.inst.property.bean.OffenderPptyConTxns;
import net.syscon.s4.inst.property.bean.OffenderPptyContainers;

/**
 * Interface OiipctraService
 */
public interface OiipctraService {
	AgencyLocations offConPostQuery(AgencyLocations paramBean);

	List<AgencyLocations> conTxPostQuery(AgencyLocations paramBean);

	List<OffenderPptyContainers> vPheadOnCheckDeleteMaster(OffenderPptyContainers paramBean);

	List<OffenderPptyContainers> offConExecuteQuery(OffenderPptyContainers objOffenderPptyContainers);

	List<OffenderPptyConTxns> conTxExecuteQuery(OffenderPptyConTxns objOffenderPptyConTxns);
	
	List<AgencyInternalLocations> rgLocationAllRecordGroup(String caseloadId);

}

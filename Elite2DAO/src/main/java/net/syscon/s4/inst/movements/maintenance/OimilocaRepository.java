package net.syscon.s4.inst.movements.maintenance;

import java.util.List;

import net.syscon.s4.common.beans.AgencyInternalLocations;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.im.beans.AgencyLocations;

/**
 * Interface OimilocaRepository
 */
public interface OimilocaRepository {
	List<AgencyLocations> rgAgyLocRecordGroup(String userName);

	Integer intLocLOneInsertAgencyInternalLocations(List<AgencyInternalLocations> paremBean);

	AgencyInternalLocations intLocLOneDeleteAgencyInternalLocations(List<AgencyInternalLocations> paremBean);

	List<AgencyInternalLocations> intLocLTwoOnCheckDeleteMaster(AgencyInternalLocations paramBean);

	List<AgencyInternalLocations> intLocLThreeOnCheckDeleteMaster(AgencyInternalLocations paramBean);

	Integer intLocLOneUpdateAgencyInternalLocations(List<AgencyInternalLocations> paremBean);

	List<ReferenceCodes> rgLevelTypeRecordGroup();

	List<AgencyInternalLocations> intLocLOneOnCheckDeleteMaster(AgencyInternalLocations paramBean);

	List<AgencyInternalLocations> intLocLOneExecuteQuery(AgencyInternalLocations paremBean);

	List<AgencyInternalLocations> intLocLTwoExecuteQuery(AgencyInternalLocations searchRecord);

	AgencyInternalLocations getResDescValues(AgencyInternalLocations objSearchDao);

	Integer getInternalLocationId();

	Integer updateIntoActDeactIntChildLocation(AgencyInternalLocations paremBean);

	Integer updateIntoRelatedTrackingFlags(AgencyInternalLocations paremBean);

	AgencyInternalLocations gettingOldData(Integer internalLocationId);
	
	List<ReferenceCodes> locationTypeLOVRecordGroup(String domain);
	
}

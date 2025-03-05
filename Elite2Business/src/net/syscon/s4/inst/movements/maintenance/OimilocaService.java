package net.syscon.s4.inst.movements.maintenance;

import java.util.List;

import net.syscon.s4.common.beans.AgencyInternalLocations;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.im.beans.AgencyInternalLocationsCommitBean;
import net.syscon.s4.im.beans.AgencyLocations;

/**
 * Interface OimilocaService
 */
public interface OimilocaService {
	  List<AgencyLocations> rgAgyLocRecordGroup(String userName);

	List<AgencyInternalLocations> intLocLTwoOnCheckDeleteMaster(AgencyInternalLocations paramBean);

	List<AgencyInternalLocations> intLocLThreeOnCheckDeleteMaster(AgencyInternalLocations paramBean);

	List<ReferenceCodes> rgLevelOneTypeRecordGroup();

	AgencyInternalLocations intLocLOneCommit(AgencyInternalLocationsCommitBean commitBean);

	List<AgencyInternalLocations> intLocLTennCheckDeleteMaster(AgencyInternalLocations paramBean);

	List<AgencyInternalLocations> intLocLOneExecuteQuery(AgencyInternalLocations paramBean);

	List<AgencyInternalLocations> intLocLTwoExecuteQuery(AgencyInternalLocations searchBean);

	AgencyInternalLocations getResDescValues(AgencyInternalLocations searchBean);

	List<ReferenceCodes> locationTypeLOVRecordGroup(String unitType);
	
}

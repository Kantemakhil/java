package net.syscon.s4.inst.movements.maintenance;

import java.util.List;

import net.syscon.s4.common.beans.AgencyInternalLocations;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.inst.property.bean.InternalLocationUsages;
import net.syscon.s4.inst.property.bean.InternalLocationUsagesCommitBean;
import net.syscon.s4.inst.schedules.bean.IntLocUsageLocations;
import net.syscon.s4.inst.schedules.bean.IntLocUsageLocationsCommitBean;

/**
 * Interface OimulocaService
 */
public interface OimulocaService {
	List<IntLocUsageLocations> intLocL1ExecuteQuery(IntLocUsageLocations object);

	Integer usagesCommit(InternalLocationUsagesCommitBean commitBean);

	Integer intLocL1Commit(IntLocUsageLocationsCommitBean commitBean);

	List<AgencyLocations> rgAgyLocRecordGroup(final String caseLoadId);

	List<ReferenceCodes> rgLocationUsageRecordGroup();

	List<InternalLocationUsages> usagesExecuteQuery(InternalLocationUsages object);

	List<ReferenceCodes> rgLevelTypeRecordGroup();

	AgencyInternalLocations getInternalLocationRecords(Integer intLocId);

	List<AgencyInternalLocations> intLocOneLov(String agyLocId);

	List<AgencyInternalLocations> intLocTwoLov(String agyLocId);

}

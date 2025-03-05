package net.syscon.s4.inst.movements.maintenance;

import java.util.List;

import net.syscon.s4.common.beans.AgencyInternalLocations;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.inst.property.bean.InternalLocationUsages;
import net.syscon.s4.inst.schedules.bean.IntLocUsageLocations;

/**
 * Interface OimulocaRepository
 */
public interface OimulocaRepository {
	String usagesInsertInternalLocationUsages(InternalLocationUsages object);

	List<IntLocUsageLocations> intLocL3OnCheckDeleteMaster(IntLocUsageLocations paramBean);

	List<IntLocUsageLocations> intLocL1ExecuteQuery(IntLocUsageLocations object);

	Integer intLocL1DeleteIntLocUsageLocations(List<IntLocUsageLocations> object);

	ReferenceCodes usagesPreUpdate(InternalLocationUsages paramBean);

	List<IntLocUsageLocations> intLocL2OnCheckDeleteMaster(IntLocUsageLocations paramBean);

	String intLocL1InsertIntLocUsageLocations(List<IntLocUsageLocations> object);

	Integer intLocL1OnCheckDeleteMaster(List<Integer> object);

	List<AgencyLocations> rgAgyLocRecordGroup(final String caseLoadIds);

	Integer intLocL1UpdateIntLocUsageLocations(List<IntLocUsageLocations> object);

	List<ReferenceCodes> rgLocationUsageRecordGroup();

	Integer usagesUpdateInternalLocationUsages(InternalLocationUsages object);

	ReferenceCodes usagesPostQuery(InternalLocationUsages paramBean);

	List<InternalLocationUsages> usagesExecuteQuery(InternalLocationUsages object);

	List<ReferenceCodes> rgLevelTypeRecordGroup();

	List<IntLocUsageLocations> usagesOnCheckDeleteMaster(IntLocUsageLocations paramBean);

	Integer usagePreInsert(InternalLocationUsages bean);

	Integer usagesPreUpdateQuery(InternalLocationUsages bean);

	AgencyInternalLocations getInternalLocationRecords(Integer intLocId);

	List<AgencyInternalLocations> intLocOneLov(String agyLocId);

	List<AgencyInternalLocations> intLocTwoLov(Integer internallocationId, String agyLocId);

}

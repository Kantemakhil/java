package net.syscon.s4.inst.automatedcounts;

import java.util.List;
import java.util.Map;

import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.im.beans.AgencyLocationCounts;
import net.syscon.s4.im.beans.AgencyLocationCountsCommitBean;
import net.syscon.s4.im.beans.LivingUnits;

/**
 * Interface OidremcsService
 */
public interface OidremcsService {

	Integer subRemCntCommit(AgencyLocationCountsCommitBean commitBean);

	List<LivingUnits> cgfkHousingLevel1RecordGroup(Integer countTypeId);

	List<LivingUnits> cgfkHousingLevel2RecordGroup(Integer countTypeId, Integer livingUnitId);

	List<LivingUnits> cgfkHousingLevel3RecordGroup(Integer countTypeId, Integer livingUnitId);

	List<LivingUnits> cgfkInitLocCodeRecordGroup(Integer countTypeId);

	List<StaffMembers> cgfkConductedByRecordGroup();

	List<AgencyLocationCounts> subRemCntExecuteQuery(AgencyLocationCounts objAgencyLocationCounts);

	Map<String, Object> changeHousingLevelOne(Integer livingUnitId);

	Integer getInternalLocationCount(Integer livingUnitId);

	Map<String, Object> getHousingLocationLovNames(Integer countTypeId);

	Map<String, Object> changeHousingLevelTwo(Integer livingUnitIdOne, Integer livingUnitIdTwo);

	int calculateActualCount(Integer livingUnitId);;

}

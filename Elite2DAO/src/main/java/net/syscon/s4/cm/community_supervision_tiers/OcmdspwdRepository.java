package net.syscon.s4.cm.community_supervision_tiers;

import java.util.List;

import net.syscon.s4.community_supervision_tiers.WlDefaultStaffUnits;
import net.syscon.s4.community_supervision_tiers.WlNonOffSpecificTasks;
import net.syscon.s4.im.beans.AgencyLocations;

public interface OcmdspwdRepository {

	List<AgencyLocations> getAgyLocRecordGroup(String caseloadId);

	Integer insertMaintainStartingWLUnits(List<WlDefaultStaffUnits> startingUnitsInsertList);

	Integer updateMaintainStartingWLUnits(List<WlDefaultStaffUnits> startingUnitsUpdateList);

	Integer deleteMaintainStartingWLUnits(List<WlDefaultStaffUnits> startingUnitsDeleteList);

	Integer insertNonOffenderSpecificTasksforPosition(List<WlNonOffSpecificTasks> insertList);

	Integer updateNonOffenderSpecificTasksforPosition(List<WlNonOffSpecificTasks> updateList);

	Integer deleteNonOffenderSpecificTasksforPosition(List<WlNonOffSpecificTasks> deleteList);

	List<WlDefaultStaffUnits> assStartingDefWLUnitsExecuteQuery();

	List<WlNonOffSpecificTasks> nonOffSpecTaskPosExecuteQuery(WlNonOffSpecificTasks obj);

	Integer assStartingDefUnitsPredelete(List<WlDefaultStaffUnits> startingUnitsDeleteList);

}

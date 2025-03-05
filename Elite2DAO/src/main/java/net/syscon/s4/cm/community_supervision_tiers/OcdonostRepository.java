package net.syscon.s4.cm.community_supervision_tiers;

import java.util.List;

import net.syscon.s4.community_supervision_tiers.WlDefaultStaffUnits;
import net.syscon.s4.community_supervision_tiers.WlOfficerNonOffSpecificTasks;

public interface OcdonostRepository {

	String getStaffName(Integer staffId);

	List<WlDefaultStaffUnits> getNonOffenderSpecificTasks(String agyLocId, String offPosition);

	Integer getDefaultUnit(String staffPosition);

	Integer insertOfficerNonOffSpeTask(List<WlOfficerNonOffSpecificTasks> insertList);

	Integer updateOfficerNonOffSpeTask(List<WlOfficerNonOffSpecificTasks> updateList);

	Integer deleteOfficerNonOffSpeTask(List<WlOfficerNonOffSpecificTasks> deleteList);

	List<WlOfficerNonOffSpecificTasks> getNonOffSpecTaskforPos(WlOfficerNonOffSpecificTasks obj);

	List<WlOfficerNonOffSpecificTasks> getOfficeNonOffSpeTask(WlOfficerNonOffSpecificTasks obj);

	Integer updateAvailableUnits(WlOfficerNonOffSpecificTasks obj);
	
	Integer gettingUnitsUsedByEachOffenders(WlOfficerNonOffSpecificTasks bean);

}

package net.syscon.s4.inst.workflow.maintenance;

import java.util.List;

import net.syscon.s4.cm.teamsworkflow.beans.TeamMembers;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.Teams;
import net.syscon.s4.inst.workflow.maintenance.beans.StaffLocationRoles;

/**
 * Interface OcmteamMainRepository
 */
public interface OcmteamMainRepository {
	
	
	List<ReferenceCodes> getCaseloads();

	List<Teams> teamsExecuteQuery();
	
	Integer insertTeamsData(List<Teams> teamList);
	
	Integer deleteTeamsData(List<Teams> teamList);
	
	Integer updateTeamsData(List<Teams> teamList);
	
	List<AgencyLocations> getAgyLocRecords();
	
	Integer insertTeamMainData(List<Teams> teamData);
	
	Integer insertTeamFunctionData(List<Teams> teamList);
	
	Integer insertTeamAgyLocData(List<Teams> teamList);
	
	Integer getTeamId();
	
	
	Integer deleteTeamFunctionData(Integer teamId,String modifyUserId);
	
	
	Integer deleteTeamAgyLocData(Integer teamId,String modifyUserId);
	
	List<Teams> teamAgencyExecuteQuery(Integer teamId);
	
	List<Teams> teamFunctionExecuteQuery(Integer teamId);
	
	List<StaffMembers> getStaffDetails();
	
	List<TeamMembers> teamMembersExecuteQuery(TeamMembers objTeamMembers);
	
	List<StaffLocationRoles> getStaffMemberDetails(TeamMembers paramBean);
	
	Integer teamMembersInsert(List<TeamMembers> teamList);
	
	Integer teamMembersUpdate(List<TeamMembers> teamList);
	
	Integer teamMembersDelete(List<TeamMembers> teamList);
	
	Long getTeamMemberId();
}

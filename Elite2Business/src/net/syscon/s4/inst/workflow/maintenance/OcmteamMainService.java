package net.syscon.s4.inst.workflow.maintenance;

import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;

import net.syscon.s4.cm.teamsworkflow.beans.TeamMembers;
import net.syscon.s4.cm.teamsworkflow.beans.TeamMembersCommitBean;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.Teams;
import net.syscon.s4.inst.workflow.maintenance.beans.TeamsCommitBean;


/**
 * Interface OcmteamMainService
 */
public interface OcmteamMainService {
	
	List<ReferenceCodes> getCaseLoadList();
	
	List<Teams> teamsExecuteQuery();
	
	List<Teams> teamsCommit(TeamsCommitBean commitBean);
	
	List<AgencyLocations> getAgyLocRecords();
	
	List<Teams>  teamDataCommit(TeamsCommitBean  teamData);
	
	List<StaffMembers> getStaffDetails();
	
	
	List<TeamMembers> teamMembersExecuteQuery(TeamMembers teamObj);
	
	List<TeamMembers> teamMembersCommit(TeamMembersCommitBean commitBean);
}

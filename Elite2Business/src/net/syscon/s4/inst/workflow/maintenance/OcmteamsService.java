package net.syscon.s4.inst.workflow.maintenance;

import java.util.List;

import net.syscon.s4.cm.teamsworkflow.beans.TeamMembers;
import net.syscon.s4.cm.teamsworkflow.beans.TeamMembersCommitBean;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.Areas;
import net.syscon.s4.im.beans.ReferenceCodes;
import net.syscon.s4.im.beans.Teams;
import net.syscon.s4.inst.workflow.maintenance.beans.TeamFunctions;
import net.syscon.s4.inst.workflow.maintenance.beans.TeamFunctionsCommitBean;
import net.syscon.s4.inst.workflow.maintenance.beans.TeamsCommitBean;

/**
 * Interface OcmteamsService
 */
public interface OcmteamsService {
	List<Teams> rgTeamsTeamCodeRecordGroup();

	List<AgencyLocations> rgAgyLocIdRecordGroup();

	List<ReferenceCodes> rgPositionRecordGroup();

	List<TeamFunctions> teamFunctionsExecuteQuery(TeamFunctions objTeamFunctions);

	List<TeamMembers> teamMembersExecuteQuery(TeamMembers objTeamMembers);

	List<Areas> rgTeamAreaRecordGroup(String areaType);

	List<ReferenceCodes> rgFuctionTypeRecordGroup();

	List<ReferenceCodes> rgTeamsTeamCategoryRecordGroup();

	List<AgencyLocations> rgAdmAgyLocRecordGroup(String areaCode);

	List<TeamMembers> teamMembersCommit(TeamMembersCommitBean commitBean);

	List<Teams> teamsCommit(TeamsCommitBean commitBean);

	List<ReferenceCodes> rgAreaTypeRecordGroup();

	List<ReferenceCodes> rgRoleRecordGroup();

	List<Teams> teamsExecuteQuery(Teams objTeams);

	List<Teams> rgAvailTeamTeamCodeRecordGroup();

	Integer ocmteamsKeyDelrec(TeamMembers paramBean);

	List<TeamFunctions> teamFunctionsCommit(TeamFunctionsCommitBean commitBean);

	Integer availTeamOnCheckDeleteMaster(TeamMembers paramBean);

	List<Teams> availTeamExecuteQuery(Teams searchBean);

	List<Teams> availTeamActiveExecuteQuery(Teams searchBean);

	Integer validateTeamCode(Teams teams);

	Integer getUpdateAllowedCheck(String caseloadId, String agyLocId);

	Integer validateFunctionCode(TeamFunctions searchBean);
	
	List<Teams> getAllTeams();
	
	String errorNameValidationTimes( String errorName);
}

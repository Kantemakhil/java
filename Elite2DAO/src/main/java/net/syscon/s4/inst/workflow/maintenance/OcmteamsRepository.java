package net.syscon.s4.inst.workflow.maintenance;

import java.util.List;

import net.syscon.s4.cm.teamsworkflow.beans.TeamMembers;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.Areas;
import net.syscon.s4.im.beans.ReferenceCodes;
import net.syscon.s4.im.beans.Teams;
import net.syscon.s4.inst.workflow.maintenance.beans.StaffLocationRoles;
import net.syscon.s4.inst.workflow.maintenance.beans.TeamFunctions;

/**
 * Interface OcmteamsRepository
 */
public interface OcmteamsRepository {
	List<Teams> rgTeamsTeamCodeRecordGroup();

	List<ReferenceCodes> rgPositionRecordGroup();

	List<TeamFunctions> teamFunctionsExecuteQuery(TeamFunctions objTeamFunctions);

	List<TeamMembers> teamMembersExecuteQuery(TeamMembers objTeamMembers);

	List<Areas> rgTeamAreaRecordGroup(String areaType);

	Integer availTeamOnCheckDeleteMaster(TeamMembers paramBean);

	List<ReferenceCodes> rgFuctionTypeRecordGroup();

	List<AgencyLocations> rgAdmAgyLocRecordGroup(String areaCode);

	Integer teamsInsertTeams(List<Teams> lstTeams);

	Integer teamMembersUpdateTeamMembers(List<TeamMembers> lstTeamMembers);

	Integer teamsDeleteTeams(List<Teams> lstTeams);

	TeamFunctions teamsOnCheckDeleteMaster(TeamFunctions paramBean);

	List<Teams> rgAvailTeamTeamCodeRecordGroup();

	TeamFunctions teamFunctionsDeleteTeamFunctions(List<TeamFunctions> lstTeamFunctions);

	Integer ocmteamsKeyDelrec(TeamMembers paramBean);

	List<AgencyLocations> rgAgyLocIdRecordGroup();

	Integer teamMembersInsertTeamMembers(List<TeamMembers> lstTeamMembers);

	List<ReferenceCodes> rgTeamsTeamCategoryRecordGroup();

	List<ReferenceCodes> rgAreaTypeRecordGroup();

	List<ReferenceCodes> rgRoleRecordGroup();

	List<Teams> teamsExecuteQuery(Teams objTeams);

	Integer teamFunctionsInsertTeamFunctions(List<TeamFunctions> lstTeamFunctions);

	Integer teamsUpdateTeams(List<Teams> lstTeams);

	List<StaffLocationRoles> ocmteamsPostQuery(TeamMembers paramBean);

	List<Teams> availTeamExecuteQuery(Teams searchRecord);

	List<Teams> availTeamActiveExecuteQuery(Teams searchRecord);

	Integer getTeamId();

	Long getTeamMemberId();

	Integer validateTeamCode(Teams teams);

	Integer getUpdateAllowedCheck(String caseloadId, String agyLocId);

	Integer validateFunctionCode(TeamFunctions searchBean);

	TeamFunctions teamFunctionsUpdateTeamFunctions(List<TeamFunctions> updateList);
	
	List<Teams> getTeamsBasedOnUser(String userId);
	
	List<Teams> getAllTeams();
	
	String errorNameValidationTimes( String errorName);
	

}

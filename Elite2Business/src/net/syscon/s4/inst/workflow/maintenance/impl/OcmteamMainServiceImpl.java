package net.syscon.s4.inst.workflow.maintenance.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.cm.teamsworkflow.beans.TeamMembers;
import net.syscon.s4.cm.teamsworkflow.beans.TeamMembersCommitBean;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.Teams;
import net.syscon.s4.inst.workflow.maintenance.OcmteamMainRepository;
import net.syscon.s4.inst.workflow.maintenance.OcmteamMainService;
import net.syscon.s4.inst.workflow.maintenance.beans.StaffLocationRoles;
import net.syscon.s4.inst.workflow.maintenance.beans.TeamsCommitBean;

/**
 * Class OcmteamMainServiceImpl
 */
@Service
public class OcmteamMainServiceImpl extends BaseBusiness implements OcmteamMainService {

	@Autowired
	private OcmteamMainRepository ocmteamMainRepository;

	@Override
	public List<ReferenceCodes> getCaseLoadList() {
		
		return ocmteamMainRepository.getCaseloads();
	}

	@Override
	public List<Teams> teamsExecuteQuery() {
		List<Teams> teamsList=ocmteamMainRepository.teamsExecuteQuery();
		if(teamsList!=null && !teamsList.isEmpty()) {
			for(Teams team:teamsList) {
				List<Teams> teamAgencyList=ocmteamMainRepository.teamAgencyExecuteQuery(team.getTeamId());
				if(teamAgencyList!=null && !teamAgencyList.isEmpty()) {
					String agyLocId=getAgencyString(teamAgencyList,team);
					team.setAgyLocId(agyLocId);
				}
				List<Teams> functionList=ocmteamMainRepository.teamFunctionExecuteQuery(team.getTeamId());
				if(functionList!=null && !functionList.isEmpty()) {
					String functionType=getFunctionString(functionList,team);
					team.setFunctionType(functionType);
				}
			}
			
			
		}
		return teamsList;
	}

	private String getAgencyString(List<Teams> teams,Teams teamObj) {
		List<String> teamList = new ArrayList<>();
		String agyLocId=null;
		if (teams != null && !teams.isEmpty()) {
			for (Teams team : teams) {
				teamList.add(team.getAgyLocId());
			}
			teamObj.setAgyLocList(teamList);
			agyLocId = String.join(",  ", teamList);
		}
		return agyLocId;
	}
	
	private String getFunctionString(List<Teams> teams,Teams teamObj) {
		List<String> teamList = new ArrayList<>();
		String functionType=null;
		if (teams != null && !teams.isEmpty()) {
			for (Teams team : teams) {
				teamList.add(team.getFunctionType());
			}
			teamObj.setFunctionList(teamList);
			functionType = String.join(",  ", teamList);
		}
		return functionType;
	}
	
	
	@Override
	public List<Teams> teamsCommit(TeamsCommitBean commitBean) {
		final List<Teams> returnList = new ArrayList<Teams>();
		final Teams returnObject = new Teams();
		int liReturn = 0;
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			liReturn = ocmteamMainRepository.insertTeamsData(commitBean.getInsertList());

		}
		// updateRecords
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			liReturn = ocmteamMainRepository.updateTeamsData(commitBean.getUpdateList());
		}
		// deleteRecords
		if (commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {
			commitBean.getDeleteList().forEach(ele->ele.setModifyUserId(commitBean.getCreateUserId()));
			liReturn = ocmteamMainRepository.deleteTeamsData(commitBean.getDeleteList());
		}
		returnObject.setReturnValue(liReturn);
		returnList.add(returnObject);
		return returnList;
	}

	@Override
	public List<AgencyLocations> getAgyLocRecords() {
		return ocmteamMainRepository.getAgyLocRecords();
	}

	@Transactional
	public List<Teams>  teamDataCommit(TeamsCommitBean commitBean) {
		List<Teams> insertList = null; 
		Integer returnValue=null;
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			insertList = commitBean.getInsertList();
			if(insertList.get(0).getTeamId() == null) {
				insertList.get(0).setTeamId(ocmteamMainRepository.getTeamId());
				returnValue=insertTeamData(insertList);
			}else {
				returnValue=updateTeamData(insertList,commitBean.getCreateUserId());
			}
			if(returnValue == 1) {
				return insertList;
			}else {
				return null;
			}

		}

		return insertList;
	}

	private Integer updateTeamData(List<Teams> teamData,String modifyUserId) {
		Integer retrunValue=null;
		retrunValue=ocmteamMainRepository.updateTeamsData(teamData);
		if(retrunValue == 1) {
			if(teamData.get(0).getAgyLocList()!=null && !teamData.get(0).getAgyLocList().isEmpty()) {
				retrunValue=ocmteamMainRepository.deleteTeamAgyLocData(teamData.get(0).getTeamId(),modifyUserId);
				if(retrunValue == 1) {
					retrunValue=saveTeamAgyLocData(teamData);
				}
				if(retrunValue == 1) {
					 if(teamData.get(0).getFunctionList()!=null && !teamData.get(0).getFunctionList().isEmpty()) {
						 retrunValue=ocmteamMainRepository.deleteTeamFunctionData(teamData.get(0).getTeamId(),modifyUserId);
						 if(retrunValue == 1) {
								retrunValue=saveTeamFunctionData(teamData);
							}
						}
				 }
				
			}else if(teamData.get(0).getFunctionList()!=null && !teamData.get(0).getFunctionList().isEmpty()) {
				retrunValue=ocmteamMainRepository.deleteTeamFunctionData(teamData.get(0).getTeamId(),modifyUserId);
				 if(retrunValue == 1) {
						retrunValue=saveTeamFunctionData(teamData);
					}
				
		 
			}
		}
		
		
		
		return retrunValue;
	}

	private Integer insertTeamData(List<Teams> teamData) {
		Integer retrunValue=null;
		if(teamData!=null && !teamData.isEmpty()) {
			retrunValue=ocmteamMainRepository.insertTeamMainData(teamData);
			if(retrunValue == 1) {
				if(teamData.get(0).getAgyLocList()!=null && !teamData.get(0).getAgyLocList().isEmpty()) {
					retrunValue=saveTeamAgyLocData(teamData);
					 if(retrunValue == 1) {
						 if(teamData.get(0).getFunctionList()!=null && !teamData.get(0).getFunctionList().isEmpty()) {
								retrunValue=saveTeamFunctionData(teamData);
							}
					 }
				}else if(teamData.get(0).getFunctionList()!=null && !teamData.get(0).getFunctionList().isEmpty()) {
					retrunValue=saveTeamFunctionData(teamData);
				}
			}
			
		}
		

		return retrunValue;
	}
	
	private Integer saveTeamAgyLocData(List<Teams> teamData) {
		List<Teams> agyLocList=new ArrayList<>();
		Integer insertAgyLoc =null;
		if(teamData.get(0).getAgyLocList()!=null && !teamData.get(0).getAgyLocList().isEmpty()) {
			for(String obj:teamData.get(0).getAgyLocList()) {
				Teams team=new Teams();
				team.setTeamId(teamData.get(0).getTeamId());
				team.setAgyLocId(obj);
				team.setCreateUserId(teamData.get(0).getCreateUserId());
				team.setCreateDatetime(teamData.get(0).getCreateDatetime());
				team.setActiveFlag("Y");
				agyLocList.add(team);
			}
			 insertAgyLoc=ocmteamMainRepository.insertTeamAgyLocData(agyLocList);
			
		}
		return insertAgyLoc;
		
		
	}
	
private Integer saveTeamFunctionData(List<Teams> teamData) {
	List<Teams> functionList=new ArrayList<>();
	Integer insertFunction =null;
	if(teamData.get(0).getFunctionList()!=null && !teamData.get(0).getFunctionList().isEmpty()) {
		for(String obj:teamData.get(0).getFunctionList()) {
			Teams team=new Teams();
			team.setTeamId(teamData.get(0).getTeamId());
			team.setFunctionType(obj);
			team.setCreateUserId(teamData.get(0).getCreateUserId());
			team.setCreateDatetime(teamData.get(0).getCreateDatetime());
			team.setActiveFlag("Y");
			functionList.add(team);
		}
		insertFunction=ocmteamMainRepository.insertTeamFunctionData(functionList);
		
	}
	return insertFunction;
		
	}

@Override
public List<StaffMembers> getStaffDetails() {
	List<StaffMembers> stafffList=ocmteamMainRepository.getStaffDetails();
	for(StaffMembers staff:stafffList) {
		if(staff.getLastName()!=null) {
			if(staff.getFirstName()!=null) {
				staff.setDescription(staff.getLastName().concat(", ").concat(staff.getFirstName()));
			}else {
				staff.setDescription(staff.getLastName());
			}
			
		}
		
	}
	
	return stafffList;
}

@Override
public List<TeamMembers> teamMembersExecuteQuery(TeamMembers teamObj) {
	List<TeamMembers> returnList = new ArrayList<>();
	List<StaffLocationRoles> staffData = new ArrayList<>();
	returnList = ocmteamMainRepository.teamMembersExecuteQuery(teamObj);
	for (final TeamMembers teamMembers : returnList) {
		staffData = ocmteamMainRepository.getStaffMemberDetails(teamMembers);
		if (!staffData.isEmpty()) {
			teamMembers.setLastName(staffData.get(0).getLastName());
			teamMembers.setFirstName(staffData.get(0).getFirstName());
			teamMembers.setScheduleType(staffData.get(0).getScheduleType());
			teamMembers.setHoursPerWeek(staffData.get(0).getHoursPerWeek());
		}
	}
	return returnList;
}

@Override
public List<TeamMembers> teamMembersCommit(TeamMembersCommitBean commitBean) {
	final List<TeamMembers> returnList = new ArrayList<TeamMembers>();
	final List<TeamMembers> recordSaveLit = new ArrayList<TeamMembers>();
	final TeamMembers returnObject = new TeamMembers();
	int liReturn = 0;
	if (commitBean.getInsertList() != null && !commitBean.getInsertList().isEmpty()) {
		for (int i = 0; i < commitBean.getInsertList().size(); i++) {
			final TeamMembers object = commitBean.getInsertList().get(i);
			object.setTeamMemberId(ocmteamMainRepository.getTeamMemberId());
			object.setCreateUserId(commitBean.getCreateUserId());
			recordSaveLit.add(object);
		}
		liReturn = ocmteamMainRepository.teamMembersInsert(recordSaveLit);

	}
	if (commitBean.getUpdateList() != null && !commitBean.getUpdateList().isEmpty()) {
		commitBean.getUpdateList().forEach(ele->ele.setModifyUserId(commitBean.getCreateUserId()));
		liReturn = ocmteamMainRepository.teamMembersUpdate(commitBean.getUpdateList());
	}
	
	if (commitBean.getDeleteList() != null && !commitBean.getDeleteList().isEmpty()) {
		commitBean.getDeleteList().forEach(ele->ele.setModifyUserId(commitBean.getCreateUserId()));
		liReturn = ocmteamMainRepository.teamMembersDelete(commitBean.getDeleteList());
	}
	returnObject.setReturnValue(liReturn);
	returnList.add(returnObject);
	return returnList;
}

}

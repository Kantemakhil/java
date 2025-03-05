package net.syscon.s4.inst.workflow.maintenance.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.cm.teamsworkflow.beans.TeamMembers;
import net.syscon.s4.cm.teamsworkflow.beans.TeamMembersCommitBean;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.Areas;
import net.syscon.s4.im.beans.ReferenceCodes;
import net.syscon.s4.im.beans.Teams;
import net.syscon.s4.inst.workflow.maintenance.OcmteamsRepository;
import net.syscon.s4.inst.workflow.maintenance.OcmteamsService;
import net.syscon.s4.inst.workflow.maintenance.beans.StaffLocationRoles;
import net.syscon.s4.inst.workflow.maintenance.beans.TeamFunctions;
import net.syscon.s4.inst.workflow.maintenance.beans.TeamFunctionsCommitBean;
import net.syscon.s4.inst.workflow.maintenance.beans.TeamsCommitBean;

/**
 * Class OcmteamsServiceImpl
 */
@Service
public class OcmteamsServiceImpl extends BaseBusiness implements OcmteamsService {

	@Autowired
	private OcmteamsRepository ocmteamsRepository;

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @throws SQLException
	 */
	public List<Teams> teamsExecuteQuery(final Teams searchRecord) {
		List<Teams> returnList= new ArrayList<>();
		returnList = ocmteamsRepository.teamsExecuteQuery(searchRecord);
		for (Teams teams : returnList) {
			teams.setUpdateDeleteAllowedCount(ocmteamsRepository.getUpdateAllowedCheck(searchRecord.getCurrentCaseLoad(),teams.getAgyLocId()));
		}		
		return returnList;

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstTEAMS
	 *
	 * @throws SQLException
	 */
	@Transactional
	public List<Teams> teamsCommit(final TeamsCommitBean commitBean) {
		final List<Teams> returnList = new ArrayList<Teams>();
		final List<Teams> recordSaveLit = new ArrayList<Teams>();
		final Teams returnObject = new Teams();
		int liReturn = 0;
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			for (int i = 0; i < commitBean.getInsertList().size(); i++) {
				final Teams object = commitBean.getInsertList().get(i);
				object.setTeamId(ocmteamsRepository.getTeamId());
				object.setCreateUserId(commitBean.getCreateUserId());
				recordSaveLit.add(object);
			}
			liReturn = ocmteamsRepository.teamsInsertTeams(recordSaveLit);
		}
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			for (Teams bean : commitBean.getUpdateList()) {
				bean.setModifyUserId(commitBean.getCreateUserId());
			}
			liReturn = ocmteamsRepository.teamsUpdateTeams(commitBean.getUpdateList());
		}
		if (commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {
			commitBean.getDeleteList().forEach(ele->ele.setModifyUserId(commitBean.getCreateUserId()));
			liReturn = ocmteamsRepository.teamsDeleteTeams(commitBean.getDeleteList());
		}
		returnObject.setReturnValue(liReturn);
		returnList.add(returnObject);
		return returnList;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @throws SQLException
	 */
	public List<TeamFunctions> teamFunctionsExecuteQuery(final TeamFunctions searchRecord) {
		return ocmteamsRepository.teamFunctionsExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstTEAM_FUNCTIONS
	 *
	 * @throws SQLException
	 */
	@Transactional
	public List<TeamFunctions> teamFunctionsCommit(final TeamFunctionsCommitBean commitBean) {
		final List<TeamFunctions> returnList = new ArrayList<>();
		 TeamFunctions returnObject = new TeamFunctions();
		int liReturn = 0;
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			for (TeamFunctions bean : commitBean.getInsertList()) {
				bean.setCreateUserId(commitBean.getCreateUserId());
			}
			liReturn = ocmteamsRepository.teamFunctionsInsertTeamFunctions(commitBean.getInsertList());
			returnObject.setReturnValue(liReturn);
		}
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			for (TeamFunctions bean : commitBean.getUpdateList()) {
				bean.setModifyUserId(commitBean.getCreateUserId());
			}
			returnObject = ocmteamsRepository.teamFunctionsUpdateTeamFunctions(commitBean.getUpdateList());
		}
		if (commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {
			commitBean.getDeleteList().forEach(ele->ele.setModifyUserId(commitBean.getCreateUserId()));
			returnObject = ocmteamsRepository.teamFunctionsDeleteTeamFunctions(commitBean.getDeleteList());
			
		}
		
		returnList.add(returnObject);
		return returnList;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @throws SQLException
	 */
	public List<Teams> availTeamExecuteQuery(final Teams searchRecord) {
		return ocmteamsRepository.availTeamExecuteQuery(searchRecord);

	}

	public List<Teams> availTeamActiveExecuteQuery(final Teams searchRecord) {
		return ocmteamsRepository.availTeamActiveExecuteQuery(searchRecord);

	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @throws SQLException
	 */
	public List<TeamMembers> teamMembersExecuteQuery(final TeamMembers searchRecord) {
		List<TeamMembers> returnList = new ArrayList<>();
		List<StaffLocationRoles> postQueryObject = new ArrayList<>();
		returnList = ocmteamsRepository.teamMembersExecuteQuery(searchRecord);
		for (final TeamMembers teamMembers : returnList) {
			postQueryObject = ocmteamsRepository.ocmteamsPostQuery(teamMembers);
			if (!postQueryObject.isEmpty()) {
				teamMembers.setLastName(postQueryObject.get(0).getLastName());
				teamMembers.setFirstName(postQueryObject.get(0).getFirstName());
				teamMembers.setScheduleType(postQueryObject.get(0).getScheduleType());
				teamMembers.setHoursPerWeek(postQueryObject.get(0).getHoursPerWeek());
			}
		}
		return returnList;

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstTEAM_MEMBERS
	 *
	 * @throws SQLException
	 */
	@Transactional
	public List<TeamMembers> teamMembersCommit(final TeamMembersCommitBean commitBean) {
		final List<TeamMembers> returnList = new ArrayList<TeamMembers>();
		final List<TeamMembers> recordSaveLit = new ArrayList<TeamMembers>();
		final TeamMembers returnObject = new TeamMembers();
		int liReturn = 0;
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			for (int i = 0; i < commitBean.getInsertList().size(); i++) {
				final TeamMembers object = commitBean.getInsertList().get(i);
				object.setTeamMemberId(ocmteamsRepository.getTeamMemberId());
				object.setCreateUserId(commitBean.getCreateUserId());
				recordSaveLit.add(object);
			}
			liReturn = ocmteamsRepository.teamMembersInsertTeamMembers(recordSaveLit);

		}
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			for (TeamMembers bean : commitBean.getUpdateList()) {
				bean.setModifyUserId(commitBean.getCreateUserId());
			}
			liReturn = ocmteamsRepository.teamMembersUpdateTeamMembers(commitBean.getUpdateList());
		}
		returnObject.setReturnValue(liReturn);
		returnList.add(returnObject);
		return returnList;
	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<Areas> rgTeamAreaRecordGroup(final String areaType) {
		List<Areas> areaList=new ArrayList<>();
		areaList = ocmteamsRepository.rgTeamAreaRecordGroup(areaType);
		Integer order = 0;
		for (Areas areas : areaList) {
			order =order+1;
			areas.setListSeq(order);
		}
		return areaList;

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<Teams> rgTeamsTeamCodeRecordGroup() {
		return ocmteamsRepository.rgTeamsTeamCodeRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<ReferenceCodes> rgTeamsTeamCategoryRecordGroup() {
		return ocmteamsRepository.rgTeamsTeamCategoryRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<ReferenceCodes> rgAreaTypeRecordGroup() {
		return ocmteamsRepository.rgAreaTypeRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<ReferenceCodes> rgFuctionTypeRecordGroup() {
		return ocmteamsRepository.rgFuctionTypeRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<Teams> rgAvailTeamTeamCodeRecordGroup() {
		return ocmteamsRepository.rgAvailTeamTeamCodeRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<ReferenceCodes> rgPositionRecordGroup() {
		return ocmteamsRepository.rgPositionRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<ReferenceCodes> rgRoleRecordGroup() {
		return ocmteamsRepository.rgRoleRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<AgencyLocations> rgAgyLocIdRecordGroup() {
		return ocmteamsRepository.rgAgyLocIdRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<AgencyLocations> rgAdmAgyLocRecordGroup(final String areaCode) {
		List<AgencyLocations> returnList=new ArrayList<>();
		returnList = ocmteamsRepository.rgAdmAgyLocRecordGroup(areaCode);
		returnList.forEach(element -> {
			if ("N".equals(element.getActiveFlag())) {
				element.setCanDisplay(false);
			}
		});
		return returnList;

	}

	@Override
	public Integer ocmteamsKeyDelrec(final TeamMembers paramBean) {
		return ocmteamsRepository.ocmteamsKeyDelrec(paramBean);
	}

	@Override
	public Integer availTeamOnCheckDeleteMaster(final TeamMembers paramBean) {
		return ocmteamsRepository.availTeamOnCheckDeleteMaster(paramBean);
	}

	public Integer validateTeamCode(final Teams teams) {
		return ocmteamsRepository.validateTeamCode(teams);
	}

	@Override
	public Integer getUpdateAllowedCheck(final String caseloadId, final String agyLocId) {
		return ocmteamsRepository.getUpdateAllowedCheck(caseloadId, agyLocId);
	}

	@Override
	public Integer validateFunctionCode(final TeamFunctions searchBean) {
		return ocmteamsRepository.validateFunctionCode(searchBean);
	}
	@Override
	public List<Teams> getAllTeams() {
		return ocmteamsRepository.getAllTeams();
	}

	@Override
	public String errorNameValidationTimes(String errorName) {
		return  ocmteamsRepository.errorNameValidationTimes(errorName);
	}
}

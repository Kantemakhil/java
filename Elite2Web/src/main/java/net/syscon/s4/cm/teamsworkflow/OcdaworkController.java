package net.syscon.s4.cm.teamsworkflow;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;

import net.syscon.s4.cm.teamsworkflow.beans.TagWorkflowBrowseQueue;
import net.syscon.s4.cm.teamsworkflow.beans.TagWorkflowBrowseQueueCommitBean;
import net.syscon.s4.cm.teamsworkflow.beans.TeamMembers;
import net.syscon.s4.cm.teamsworkflow.beans.TeamMembersCommitBean;
import net.syscon.s4.common.EliteController;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.Teams;

/**
 * Class OcdaworkController
 */
@EliteController
public class OcdaworkController {
	@Autowired
	private OcdaworkService ocdaworkService;
	@Autowired
	private OcdtworkService ocdtworkService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OcdaworkController.class.getName());

	/**
	 * getting rgReason LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdawork/rgReasonRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgReasonRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = ocdaworkService.rgReasonRecordGroup();
		} catch (Exception e) {
			final ReferenceCodes obj = new ReferenceCodes();
			logger.error("In method rgReasonRecordGroup", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgSex LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdawork/rgSexRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgSexRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = ocdaworkService.rgSexRecordGroup();
		} catch (Exception e) {
			final ReferenceCodes obj = new ReferenceCodes();
			logger.error("In method rgSexRecordGroup", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgWorkType LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdawork/rgWorkTypeRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgWorkTypeRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = ocdaworkService.rgWorkTypeRecordGroup();
		} catch (Exception e) {
			final ReferenceCodes obj = new ReferenceCodes();
			logger.error("In method rgWorkTypeRecordGroup", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgWorkSubType LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdawork/rgWorkSubTypeRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgWorkSubTypeRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = ocdaworkService.rgWorkSubTypeRecordGroup();
		} catch (Exception e) {
			final ReferenceCodes obj = new ReferenceCodes();
			logger.error("In method rgWorkSubTypeRecordGroup", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgWorkflowType LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdawork/rgWorkflowTypeRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgWorkflowTypeRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = ocdaworkService.rgWorkflowTypeRecordGroup();
		} catch (Exception e) {
			final ReferenceCodes obj = new ReferenceCodes();
			logger.error("In method rgWorkflowTypeRecordGroup", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgPosition LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdawork/rgPositionRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgPositionRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = ocdaworkService.rgPositionRecordGroup();
		} catch (Exception e) {
			final ReferenceCodes obj = new ReferenceCodes();
			logger.error("In method rgPositionRecordGroup", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgRole LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdawork/rgRoleRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgRoleRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = ocdaworkService.rgRoleRecordGroup();
		} catch (Exception e) {
			final ReferenceCodes obj = new ReferenceCodes();
			logger.error("In method rgRoleRecordGroup", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgTeamStaff LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdawork/rgTeamStaffRecordGroup", method = RequestMethod.GET)
	public List<Teams> rgTeamStaffRecordGroup(@RequestParam String agyLocId) {
		List<Teams> recordList = new ArrayList<Teams>();
		try {
			recordList = ocdaworkService.rgTeamStaffRecordGroup(agyLocId);
		} catch (Exception e) {
			final Teams obj = new Teams();
			logger.error("In method rgTeamStaffRecordGroup", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgTeamMembers LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdawork/rgTeamMembersRecordGroup", method = RequestMethod.GET)
	public List<StaffMembers> rgTeamMembersRecordGroup() {
		List<StaffMembers> recordList = new ArrayList<StaffMembers>();
		try {
			recordList = ocdaworkService.rgTeamMembersRecordGroup();
		} catch (Exception e) {
			final StaffMembers obj = new StaffMembers();
			logger.error("In method rgTeamMembersRecordGroup", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @param searchBean {@link TagWorkflowBrowseQueue}
	 * @return a list of the TagWorkflowBrowseQueue {@link TagWorkflowBrowseQueue} for the matching passed data
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdawork/teamQueueExecuteQuery", method = RequestMethod.POST)
	public List<TagWorkflowBrowseQueue> teamQueueExecuteQuery(@RequestBody final TagWorkflowBrowseQueue searchBean) {
		List<TagWorkflowBrowseQueue> searchResult = new ArrayList<>();
		try {
			searchResult = ocdaworkService.teamQueueExecuteQuery(searchBean);
		} catch (Exception e) {
			final TagWorkflowBrowseQueue bean = new TagWorkflowBrowseQueue();
			logger.error("In method teamQueueExecuteQuery", e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}

	/**
	 * Perfomring basic Oracle form functions i.e. insert,delete, update int the
	 * database table
	 *
	 * @param commitBean {@link TagWorkflowBrowseQueueCommitBean}
	 * @return success/failure of the insert/update {@link Integer} for the matching passed data
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/ocdawork/teamQueueCommit", method = RequestMethod.POST)
	public @ResponseBody Integer teamQueueCommit(@RequestBody TagWorkflowBrowseQueueCommitBean commitBean) {
		int liReturn = 0;
		try {
			liReturn = ocdaworkService.teamQueueCommit(commitBean);
		} catch (Exception e) {
			logger.error("In method teamQueueCommit", e);
		}
		return liReturn;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @param searchBean {@link TeamMembers}
	 * @return a list of the TeamMembers {@link TeamMembers} for the matching passed data
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdawork/teamMembersExecuteQuery", method = RequestMethod.POST)
	public List<TeamMembers> teamMembersExecuteQuery(@RequestBody final TeamMembers searchBean) {
		List<TeamMembers> searchResult = new ArrayList<>();
		try {
			searchBean.setCreateUserId(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
			searchResult = ocdaworkService.teamMembersExecuteQuery(searchBean);
		} catch (Exception e) {
			final TeamMembers bean = new TeamMembers();
			logger.error("In method teamMembersExecuteQuery", e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}

	/**
	 * Perfomring basic Oracle form functions i.e. insert,delete, update int the
	 * database table
	 *
	 * @param commitBean {@link TeamMembersCommitBean}
	 * @return success/failure of the insert/update {@link Integer} for the matching passed data
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/ocdawork/teamMembersCommit", method = RequestMethod.POST)
	public @ResponseBody Integer teamMembersCommit(@RequestBody TeamMembersCommitBean commitBean) {
		int liReturn = 0;
		try {
			liReturn = ocdaworkService.teamMembersCommit(commitBean);
		} catch (Exception e) {

			logger.error("In method teamMembersCommit", e);
		}
		return liReturn;
	}

	/**
	 * Fetching the record from database table
	 *
	 * @param searchBean {@link TagWorkflowBrowseQueueCommitBean}
	 * @return success/failure of the insert/udpate {@link Integer} for the matching passed data
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/ocdawork/getCompleteTask", method = RequestMethod.POST)
	public Integer getCompleteTask(@RequestBody final TagWorkflowBrowseQueue searchBean) {
		int liReturn = 0;
		try {
			liReturn = ocdaworkService.getCompleteTask(searchBean);
		} catch (Exception e) {
			final TagWorkflowBrowseQueue bean = new TagWorkflowBrowseQueue();
			logger.error("In method getCompleteTask", e);
			bean.setErrorMessage(e.getMessage());
		}
		return liReturn;
	}
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdawork/rgStaffSearchRecordGroup", method = RequestMethod.GET)
	public List<StaffMembers> rgStaffSearchRecordGroup(@RequestParam(value = "teamId") final Integer teamId) {
		List<StaffMembers> recordList = new ArrayList<StaffMembers>();
		try {
			recordList = ocdaworkService.rgStaffSearchRecordGroup(teamId);
		} catch (Exception e) {
			final StaffMembers obj = new StaffMembers();
			logger.error(e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}
	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdawork/cgfkCrtMvTmpAgyLocIdRecordGroup", method = RequestMethod.GET)
	public List<AgencyLocations> cgfkCrtMvTmpAgyLocIdRecordGroup(
			@RequestParam(value = "caseLoadId") final String caseLoadId) {
		List<AgencyLocations> recordList = new ArrayList<AgencyLocations>();
		try {
			recordList = ocdtworkService.cgfkCrtMvTmpAgyLocIdRecordGroup(caseLoadId);
		} catch (Exception e) {
			final AgencyLocations obj = new AgencyLocations();
			logger.error(e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

}
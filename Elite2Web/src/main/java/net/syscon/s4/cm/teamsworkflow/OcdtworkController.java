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
import net.syscon.s4.pkgs.common.CommonService;

/**
 * Class OcdtworkController
 */
@EliteController
public class OcdtworkController {
	@Autowired
	private OcdtworkService ocdtworkService;
	@Autowired
	private CommonService commonService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OcdtworkController.class.getName());

	/**
	 * getting rgReason LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdtwork/rgReasonRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgReasonRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = ocdtworkService.rgReasonRecordGroup();
		} catch (Exception e) {
			final ReferenceCodes obj = new ReferenceCodes();
			logger.error(e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgCompleted LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdtwork/rgCompletedRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgCompletedRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = ocdtworkService.rgCompletedRecordGroup();
		} catch (Exception e) {
			final ReferenceCodes obj = new ReferenceCodes();
			logger.error(e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting cgfkCrtMvTmpAgyLocId LOV values
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ocdtwork/cgfkCrtMvTmpAgyLocIdRecordGroup", method = RequestMethod.GET)
	public List<AgencyLocations> cgfkCrtMvTmpAgyLocIdRecordGroup(
			@RequestParam(value = "caseLoadId") final String caseLoadId) {
		List<AgencyLocations> recordList = new ArrayList<AgencyLocations>();
		if(!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
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

	/**
	 * getting rgSex LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdtwork/rgSexRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgSexRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = ocdtworkService.rgSexRecordGroup();
		} catch (Exception e) {
			final ReferenceCodes obj = new ReferenceCodes();
			logger.error(e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgWorkType LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdtwork/rgWorkTypeRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgWorkTypeRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = ocdtworkService.rgWorkTypeRecordGroup();
		} catch (Exception e) {
			final ReferenceCodes obj = new ReferenceCodes();
			logger.error(e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgWorkSubType LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdtwork/rgWorkSubTypeRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgWorkSubTypeRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = ocdtworkService.rgWorkSubTypeRecordGroup();
		} catch (Exception e) {
			final ReferenceCodes obj = new ReferenceCodes();
			logger.error(e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgPosition LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdtwork/rgPositionRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgPositionRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = ocdtworkService.rgPositionRecordGroup();
		} catch (Exception e) {
			final ReferenceCodes obj = new ReferenceCodes();
			logger.error(e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgRole LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdtwork/rgRoleRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgRoleRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = ocdtworkService.rgRoleRecordGroup();
		} catch (Exception e) {
			final ReferenceCodes obj = new ReferenceCodes();
			logger.error(e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgTeamStaff LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdtwork/rgTeamStaffRecordGroup", method = RequestMethod.GET)
	public List<Teams> rgTeamStaffRecordGroup() {
		List<Teams> recordList = new ArrayList<Teams>();
		try {
			recordList = ocdtworkService.rgTeamStaffRecordGroup();
		} catch (Exception e) {
			final Teams obj = new Teams();
			logger.error(e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgStaffSearch LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdtwork/rgStaffSearchRecordGroup", method = RequestMethod.GET)
	public List<StaffMembers> rgStaffSearchRecordGroup(@RequestParam(value = "agylocId") final String agylocId) {
		List<StaffMembers> recordList = new ArrayList<StaffMembers>();
		try {
			recordList = ocdtworkService.rgStaffSearchRecordGroup(agylocId);
		} catch (Exception e) {
			final StaffMembers obj = new StaffMembers();
			logger.error(e);
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
	@RequestMapping(value = "/ocdtwork/staffQueueExecuteQuery", method = RequestMethod.POST)
	public List<TagWorkflowBrowseQueue> staffQueueExecuteQuery(@RequestBody final TagWorkflowBrowseQueue searchBean) {
		List<TagWorkflowBrowseQueue> searchResult = new ArrayList<>();
		final String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		searchBean.setCreateUserId(userName);
		try {
		} catch (Exception e) {
			final TagWorkflowBrowseQueue bean = new TagWorkflowBrowseQueue();
			logger.error(e);
			bean.setErrorMessage(e.getMessage());
		}
		return searchResult;
	}

	/**
	 * Performing basic Oracle form functions i.e. insert,delete, update int the
	 * database table
	 * 
	 * @param commitBean {@link TagWorkflowBrowseQueueCommitBean}
	 * @return success/failure of the insert/udpate {@link Integer} for the matching passed data
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdtwork/staffQueueCommit", method = RequestMethod.POST)
	public @ResponseBody Integer staffQueueCommit(@RequestBody final TagWorkflowBrowseQueueCommitBean commitBean) {
		int liReturn = 0;
		try {
			liReturn = ocdtworkService.staffQueueCommit(commitBean);
		} catch (Exception e) {

			logger.error(e);
		}
		return liReturn;
	}

	/**
	 * Fetching the record from database table
	 *
	 * @param searchBean {@link TagWorkflowBrowseQueue}
	 * @return a list of the TagWorkflowBrowseQueue {@link TagWorkflowBrowseQueue} for the matching passed data
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdtwork/staffMemoQueueExecuteQuery", method = RequestMethod.POST)
	public List<TagWorkflowBrowseQueue> staffMemoQueueExecuteQuery(
			@RequestBody final TagWorkflowBrowseQueue searchBean) {

		List<TagWorkflowBrowseQueue> searchResult = new ArrayList<>();
		final String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		searchBean.setCreateUserId(userName);
		try {
			searchResult = ocdtworkService.staffMemoQueueExecuteQuery(searchBean);
		} catch (Exception e) {
			final TagWorkflowBrowseQueue bean = new TagWorkflowBrowseQueue();
			logger.error(e);
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
	 * @return success/failure response of the insert/update {@link Integer} for the matching passed data
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdtwork/staffMemoQueueCommit", method = RequestMethod.POST)
	public @ResponseBody Integer staffMemoQueueCommit(@RequestBody final TagWorkflowBrowseQueueCommitBean commitBean) {
		int liReturn = 0;
		try {
			commitBean.setCreateUserId(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
			liReturn = ocdtworkService.staffMemoQueueCommit(commitBean);
		} catch (Exception e) {

			logger.error(e);
		}
		return liReturn;
	}

	/**
	 * Fetching the record from database table
	 *
	 * @param searchBean {@link TeamMembers}
	 * @return a list of the TeamMembers {@link TeamMembers} for the matched TeamMembers
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdtwork/teamMembersExecuteQuery", method = RequestMethod.POST)
	public List<TeamMembers> teamMembersExecuteQuery(@RequestBody final TeamMembers searchBean) {
		List<TeamMembers> searchResult = new ArrayList<>();
		try {
			searchResult = ocdtworkService.teamMembersExecuteQuery(searchBean);
		} catch (Exception e) {
			final StaffMembers bean = new StaffMembers();
			logger.error(e);
			bean.setErrorMessage(e.getMessage());
		}
		return searchResult;
	}

	/**
	 * Perfomring basic Oracle form functions i.e. insert,delete, update int the
	 * database table
	 *
	 * @param commitBean {@link TeamMembersCommitBean}
	 * @return a list of the TeamMembersCommitBean {@link TeamMembersCommitBean} for the matched TeamMembersCommitBean
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdtwork/teamMembersCommit", method = RequestMethod.POST)
	public @ResponseBody Integer teamMembersCommit(@RequestBody final TeamMembersCommitBean commitBean) {
		int liReturn = 0;
		try {
			commitBean.setCreateUserId(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
			liReturn = ocdtworkService.teamMembersCommit(commitBean);
		} catch (Exception e) {

			logger.error(e);
		}
		return liReturn;
	}
	private Boolean checkCallFormAccess(String role) {
		String userId = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		return commonService.checkCallFormAccess(userId, role,"OCDTWORK");
	}
}
package net.syscon.s4.inst.workflow.managingteams;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import net.syscon.s4.common.EliteController;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.im.beans.Teams;
import net.syscon.s4.im.beans.VPimsNameSearch;
import net.syscon.s4.inst.workflow.managingteams.beans.TagWorkflowAdmQueryTeamTasks;
import net.syscon.s4.inst.workflow.managingteams.beans.TagWorkflowAdmQueryTeamTasksCommitBean;

/**
 * OcittaskController
 */
@EliteController
public class OcittaskController {
	@Autowired
	private OcittaskService ocittaskService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OcittaskController.class.getName());

	/**
	 * getting rgStaff LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocittask/rgStaffRecordGroup", method = RequestMethod.GET)
	public List<StaffMembers> rgStaffRecordGroup(@RequestParam("teamCode") final String teamCode) {
		List<StaffMembers> recordList = new ArrayList<>();
		try {
			recordList = ocittaskService.rgStaffRecordGroup(teamCode,SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
		} catch (final Exception e) {
			final StaffMembers obj = new StaffMembers();
			logger.error("rgStaffRecordGroup :", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgStaff LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocittask/getTeamId", method = RequestMethod.GET)
	public Teams getTeamId(@RequestParam("teamCode") final String teamCode) {
		Teams teams = new Teams();
		try {
			teams = ocittaskService.getTeamId(teamCode,SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
		} catch (final Exception e) {
			final Teams obj = new Teams();
			logger.error("rgStaffRecordGroup :", e);
			obj.setErrorMessage(e.getMessage());
		}
		return teams;
	}

	/**
	 * getting rgCompleteStatus LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocittask/rgCompleteStatusRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgCompleteStatusRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<>();
		try {
			recordList = ocittaskService.rgCompleteStatusRecordGroup();
		} catch (final Exception e) {
			final ReferenceCodes obj = new ReferenceCodes();
			logger.error("rgCompleteStatusRecordGroup :", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgTaskSubType LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocittask/rgTaskSubTypeRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgTaskSubTypeRecordGroup(@RequestParam("taskType") final String taskType) {
		List<ReferenceCodes> recordList = new ArrayList<>();
		try {
			recordList = ocittaskService.rgTaskSubTypeRecordGroup(taskType);
		} catch (final Exception e) {
			final ReferenceCodes obj = new ReferenceCodes();
			logger.error("rgTaskSubTypeRecordGroup :", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgTaskType LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocittask/rgTaskTypeRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgTaskTypeRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<>();
		try {
			recordList = ocittaskService.rgTaskTypeRecordGroup();
		} catch (final Exception e) {
			final ReferenceCodes obj = new ReferenceCodes();
			logger.error("rgTaskTypeRecordGroup :", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgTeam LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocittask/rgTeamRecordGroup", method = RequestMethod.GET)
	public List<Teams> rgTeamRecordGroup() {
		List<Teams> recordList = new ArrayList<>();
		try {
			recordList = ocittaskService.rgTeamRecordGroup(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
		} catch (final Exception e) {
			final Teams obj = new Teams();
			logger.error("rgTeamRecordGroup :", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocittask/tasksExecuteQuery", method = RequestMethod.POST)
	public List<TagWorkflowAdmQueryTeamTasks> tasksExecuteQuery(
			@RequestBody final TagWorkflowAdmQueryTeamTasks searchBean) {
		List<TagWorkflowAdmQueryTeamTasks> searchResult = new ArrayList<>();
		try {
			searchResult = ocittaskService.tasksExecuteQuery(searchBean);
		} catch (final Exception e) {
			final TagWorkflowAdmQueryTeamTasks bean = new TagWorkflowAdmQueryTeamTasks();
			logger.error("tasksExecuteQuery :", e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}

	/**
	 * Perfomring basic Oracle form functions i.e. insert,delete, update int the
	 * database table
	 * 
	 * @Param commitBean
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/ocittask/tasksCommit", method = RequestMethod.POST)
	public @ResponseBody Integer tasksCommit(@RequestBody final TagWorkflowAdmQueryTeamTasksCommitBean commitBean) {
		int liReturn = 0;
		if (commitBean != null) {
			String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			commitBean.setCreateUserId(userName);
		}
		try {
			liReturn = ocittaskService.tasksCommit(commitBean);
		} catch (final Exception e) {

			logger.error("tasksCommit :", e);
		}
		return liReturn;
	}

	/**
	 * getting rgTaskSubType LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocittask/getOffenderBookId", method = RequestMethod.GET)
	public VPimsNameSearch getOffenderBookId(@RequestParam("offenderIdDisplay") final String offenderIdDisplay,
			@RequestParam("caseloadId") final String caseloadId) {
		VPimsNameSearch vPimsNameSearch = new VPimsNameSearch();
		try {
			vPimsNameSearch = ocittaskService.getOffenderBookId(offenderIdDisplay, caseloadId);
		} catch (final Exception e) {
			final VPimsNameSearch obj = new VPimsNameSearch();
			logger.error("rgTaskSubTypeRecordGroup :", e);
			obj.setErrorMessage(e.getMessage());
		}
		return vPimsNameSearch;
	}

}
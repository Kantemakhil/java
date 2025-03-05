package net.syscon.s4.programswithoutschedules;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import net.syscon.s4.cm.teamsworkflow.beans.TagWorkflowBrowseQueue;
import net.syscon.s4.cm.teamsworkflow.beans.TeamMembers;
import net.syscon.s4.common.EliteController;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.im.beans.OmsModules;
import net.syscon.s4.inst.programswithoutschedules.OsuntaskService;
import net.syscon.s4.sa.recordmaintenance.CmdModules;
import net.syscon.s4.sa.recordmaintenance.CmdworkService;


@EliteController
public class OsuntaskController {
	@Autowired
	private OsuntaskService osuntaskService;
	
	@Autowired
	private CmdworkService cmdworkService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OsuntaskController.class.getName());

	/**
	 * getting rgWorks LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/osuntask/rgWorksRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgWorksRecordGroup(@RequestParam(value = "caseloadType") final String caseloadType) {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = osuntaskService.rgWorksRecordGroup(caseloadType);
		} catch (Exception e) {
			final ReferenceCodes obj = new ReferenceCodes();
			logger.error("Exception :", e);
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgStaff LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/osuntask/rgStaffRecordGroup", method = RequestMethod.GET)
	public List<StaffMembers> rgStaffRecordGroup(@RequestParam(value = "workAndObId") final String workAndObId) {
		List<StaffMembers> recordList = new ArrayList<StaffMembers>();
		try {
			recordList = osuntaskService.rgStaffRecordGroup(workAndObId);
		} catch (Exception e) {
			final StaffMembers obj = new StaffMembers();
			logger.error("Exception :", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgTeams LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/osuntask/rgTeamsRecordGroup", method = RequestMethod.GET)
	public List<TeamMembers> rgTeamsRecordGroup(
			@RequestParam(value = "staffWorkAndObId") final String staffWorkAndObId) {
		List<TeamMembers> recordList = new ArrayList<TeamMembers>();
		try {
			recordList = osuntaskService.rgTeamsRecordGroup(staffWorkAndObId);
		} catch (Exception e) {
			final TeamMembers obj = new TeamMembers();
			logger.error("Exception :", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/osuntask/getTeamemberId", method = RequestMethod.GET)
	public @ResponseBody Integer getTeamemberId(@RequestParam(value = "teamMemberId") final String teamMemberId) {
		Integer liReturn = 0;
		try {
			liReturn = osuntaskService.getTeamemberId(teamMemberId);

		} catch (Exception e) {
			final String errorMsg = "Error : osuntask  submitAdhocWorkflow" + e.getMessage();
			logger.error("Exception : submitAdhocWorkflow", errorMsg);
		}

		return liReturn;
	}

	/**
	 * save data
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/osuntask/submitAdhocWorkflow", method = RequestMethod.POST)
	public List<TagWorkflowBrowseQueue> submitAdhocWorkflow(@RequestBody final TagWorkflowBrowseQueue newTaskModel) {
		List<TagWorkflowBrowseQueue> liReturn = new ArrayList<>();
		try {
			liReturn = osuntaskService.submitAdhocWorkflow(newTaskModel);

		} catch (Exception e) {
			final TagWorkflowBrowseQueue error = new TagWorkflowBrowseQueue();
			final String errorMsg = "Error : osuntask  submitAdhocWorkflow" + e.getMessage();
			error.setErrorMessage(errorMsg);
			liReturn.add(error);
			logger.error("Exception : submitAdhocWorkflow", e);
		}

		return liReturn;
	}
	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/osuntask/rgModulesRecordGroup", method = RequestMethod.GET)
	public List<CmdModules> rgModulesRecordGroup() {
		List<CmdModules> recordList = new ArrayList<>();
		try {
			recordList = cmdworkService.rgModulesRecordGroup("USER_TASK");
		} catch (Exception e) {
			CmdModules obj = new CmdModules();
			logger.error("Exception in rgModulesRecordGroup: Cmdwork:", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}
}
package net.syscon.s4.cm.teamsworkflow;

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

import net.syscon.s4.cm.teamsworkflow.beans.TaskAssignmentHty;
import net.syscon.s4.common.EliteController;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.StaffMembers;

/**
 * Class OcdotaskController
 */
@EliteController
public class OcdotaskController {
	@Autowired
	private OcdotaskService ocdotaskService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OcdotaskController.class.getName());

	/**
	 * getting rgCompleteRsn LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdotask/rgCompleteRsnRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgCompleteRsnRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = ocdotaskService.rgCompleteRsnRecordGroup();
		} catch (Exception e) {
			logger.error("Exception in rgCompleteRsnRecordGroup:", e);
		}
		return recordList;
	}

	/**
	 * getting rgStaff LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdotask/rgStaffRecordGroup", method = RequestMethod.GET)
	public List<StaffMembers> rgStaffRecordGroup(@RequestParam(value = "teamCode") final String teamCode) {
		List<StaffMembers> recordList = new ArrayList<StaffMembers>();
		try {
			recordList = ocdotaskService.rgStaffRecordGroup(teamCode);
		} catch (Exception e) {
			logger.error("Exception in rgStaffRecordGroup:", e);
		}
		return recordList;
	}

	/**
	 * getting rgTeam LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdotask/rgTeamRecordGroup", method = RequestMethod.GET)
	public List<StaffMembers> rgTeamRecordGroup() {
		List<StaffMembers> recordList = new ArrayList<>();
		try {
			recordList = ocdotaskService.rgTeamRecordGroup();
		} catch (Exception e) {
			logger.error("Exception in rgTeamRecordGroup", e);
		}
		return recordList;
	}

	/**
	 * getting rgTaskSubType LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdotask/rgTaskSubTypeRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgTaskSubTypeRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = ocdotaskService.rgTaskSubTypeRecordGroup();
		} catch (Exception e) {
			logger.error("Exception in rgTaskSubTypeRecordGroup:", e);
		}
		return recordList;
	}

	/**
	 * getting rgTaskType LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdotask/rgTaskTypeRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgTaskTypeRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = ocdotaskService.rgTaskTypeRecordGroup();
		} catch (Exception e) {
			logger.error("Exception in rgTaskTypeRecordGroup:", e);
		}
		return recordList;
	}

	/**
	 * Fetching the record from database table
	 *
	 * @param searchBean {@link TaskAssignmentHty}
	 * @return a list of the TaskAssignmentHty {@link TaskAssignmentHty} for the matching passed data
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdotask/tasksExecuteQuery", method = RequestMethod.POST)
	public List<TaskAssignmentHty> tasksExecuteQuery(@RequestBody final TaskAssignmentHty searchBean) {
		List<TaskAssignmentHty> searchResult = new ArrayList<>();
		try {
			searchResult = ocdotaskService.tasksExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error("Exception in tasksExecuteQuery:", e);
		}
		return searchResult;
	}

}
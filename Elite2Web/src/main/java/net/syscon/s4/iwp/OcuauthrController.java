package net.syscon.s4.iwp;

import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import net.syscon.s4.cm.teamsworkflow.beans.TaskAssignmentHty;
import net.syscon.s4.common.EliteController;
import net.syscon.s4.common.beans.ReferenceCodes;

@EliteController
public class OcuauthrController {
	@Autowired
	private OcuauthrService ocuauthrService;
	/**
	 * Logger object used to print the log in the file
	 */
	private final static Logger logger = LogManager.getLogger(OcuauthrController.class.getName());

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocuauthr/tskAssHtyExecuteQuery", method = RequestMethod.POST)
	public List<TaskAssignmentHty> tskAssHtyExecuteQuery(@RequestBody final TaskAssignmentHty tskAsshty) {
		List<TaskAssignmentHty> searchResult = new ArrayList<>();
		try {
			searchResult = ocuauthrService.tskAssHtyExecuteQuery(tskAsshty);
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return searchResult;
	}

	/**
	 * getting rgStaffName LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocuauthr/rgStaffNameRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgStaffNameRecordGroup(final String teamId) {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = ocuauthrService.rgStaffNameRecordGroup(teamId);
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return recordList;
	}

}
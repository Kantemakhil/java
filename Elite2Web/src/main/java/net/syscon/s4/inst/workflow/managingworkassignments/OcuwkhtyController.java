package net.syscon.s4.inst.workflow.managingworkassignments;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import net.syscon.s4.common.EliteController;

@EliteController
public class OcuwkhtyController {
	@Autowired
	private OcuwkhtyService ocuwkhtyService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OcuwkhtyController.class.getName());

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocuwkhty/vWorkAssignmentHistoryExecuteQuery", method = RequestMethod.POST)
	public List<VWorkAssignmentHistory> vWorkAssignmentHistoryExecuteQuery(
			@RequestBody final VWorkAssignmentHistory searchBean) {
		List<VWorkAssignmentHistory> searchResult = new ArrayList<>();
		try {
			searchResult = ocuwkhtyService.vWorkAssignmentHistoryExecuteQuery(searchBean);
		} catch (final Exception e) {
			logger.error("Exception :", e);
		}
		return searchResult;
	}

}
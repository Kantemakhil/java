package net.syscon.s4.workspace;


import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import net.syscon.s4.common.EliteController;
import net.syscon.s4.common.beans.OffendersIntakeSummary;


@EliteController
public class OwintakeController {
	
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OwintakeController.class.getName());

	@Autowired
	private OwintakeService owintakeService;
	
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/workspace/InmateSummary", method = RequestMethod.GET)
	public @ResponseBody List<OffendersIntakeSummary> getInmateSummary(@RequestParam(value = "caseLoadId") final String caseLoadId) {
		List<OffendersIntakeSummary> offenderSummary = null;
		try {
			offenderSummary = owintakeService.getOffendersSummary(caseLoadId);
		} catch (Exception e) {
			logger.error(e);
		}
		return offenderSummary;
	}

}

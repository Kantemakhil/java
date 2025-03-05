package net.syscon.s4.cf.deductions;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import net.syscon.s4.cf.deductions.beans.FeeAccountProfiles;
import net.syscon.s4.common.EliteController;

@EliteController
public class OcuachisController {
	@Autowired
	private OcuachisService ocuachisService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OcdofaccController.class.getName());
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocuachis/accountHistoryQuery", method = RequestMethod.POST)
	public List<FeeAccountProfiles> offDedExecuteQuery(@RequestBody final FeeAccountProfiles searchObject) {
		List<FeeAccountProfiles> searchResult = new ArrayList<>();
		try {
			searchResult = ocuachisService.accountHistoryQuery(searchObject);
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return searchResult;
	}
	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocuachis/getDescription", method = RequestMethod.POST)
	public String getDescription(@RequestBody final FeeAccountProfiles searchObject) {
		String searchResult = null;
		try {
			searchResult = ocuachisService.getDescription(searchObject);
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return searchResult;
	}
}

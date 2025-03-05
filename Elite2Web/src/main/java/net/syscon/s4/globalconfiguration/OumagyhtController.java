package net.syscon.s4.globalconfiguration;

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
import net.syscon.s4.im.beans.AgencyLocationAmendments;
import net.syscon.s4.im.beans.AgencyLocations;
@EliteController
public class OumagyhtController {
	@Autowired
	private OumagyhtService oumagyhtService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OumagyhtController.class.getName());

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oumagyht/agyLocExecuteQuery", method = RequestMethod.GET)
	public List<AgencyLocations> agyLocExecuteQuery() {
		List<AgencyLocations> searchResult = new ArrayList<>();
		try {
			searchResult = oumagyhtService.agyLocExecuteQuery();
		} catch (final Exception e) {
			logger.error("Exception :", e);
		}
		return searchResult;
	}

	/**
	 * Fetching the record from database table
	 *
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oumagyht/agyLocAmExecuteQuery", method = RequestMethod.POST)
	public List<AgencyLocationAmendments> agyLocAmExecuteQuery(@RequestBody final AgencyLocationAmendments searchBean) {
		List<AgencyLocationAmendments> searchResult = new ArrayList<>();
		try {
			searchResult = oumagyhtService.agyLocAmExecuteQuery(searchBean);
		} catch (final Exception e) {
			logger.error("Exception :", e);
		}
		return searchResult;
	}

}
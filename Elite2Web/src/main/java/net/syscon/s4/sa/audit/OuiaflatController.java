package net.syscon.s4.sa.audit;

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

/**
 * class OuiaflatController
 */
@EliteController
public class OuiaflatController {
	@Autowired
	private OuiaflatService ouiaflatService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OuiaflatController.class.getName());

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ouiaflat/loginAlertsBlkExecuteQuery", method = RequestMethod.POST)
	public List<TagLoginAlerts> loginAlertsBlkExecuteQuery(@RequestBody final TagLoginAlerts searchBean) {
		List<TagLoginAlerts> searchResult = new ArrayList<>();
		try {
			searchResult = ouiaflatService.loginAlertsBlkExecuteQuery(searchBean);
		} catch (final Exception e) {
			final TagLoginAlerts bean = new TagLoginAlerts();
			logger.error("Exception :", e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}

}
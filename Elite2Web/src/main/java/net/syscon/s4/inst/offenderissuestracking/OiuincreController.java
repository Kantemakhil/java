package net.syscon.s4.inst.offenderissuestracking;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import net.syscon.s4.common.EliteController;
import net.syscon.s4.im.incidentsoic.beans.AgencyIncidents;

/**
 * Class OiuincreController
 */
@EliteController
public class OiuincreController {
	@Autowired
	private OiuincreService oiuincreService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OiuincreController.class.getName());

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oiuincre/agencyIncidentsExecuteQuery", method = RequestMethod.GET)
	public List<AgencyIncidents> agencyIncidentsExecuteQuery(
			@RequestParam(value = "rootOffenderId") final Integer rootOffenderId) {
		List<AgencyIncidents> searchResult = new ArrayList<>();
		try {
			searchResult = oiuincreService.agencyIncidentsExecuteQuery(rootOffenderId);
		} catch (Exception e) {
			final AgencyIncidents bean = new AgencyIncidents();
			logger.error(e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}

}
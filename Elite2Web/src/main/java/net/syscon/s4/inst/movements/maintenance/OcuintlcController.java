package net.syscon.s4.inst.movements.maintenance;

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
import net.syscon.s4.common.beans.AgencyInternalLocations;

/**
 * Class OcuintlcController
 */
@EliteController
public class OcuintlcController {
	@Autowired
	private OcuintlcService ocuintlcService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OcuintlcController.class.getName());

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocuintlc/intLocExecuteQuery", method = RequestMethod.POST)
	public List<AgencyInternalLocations> intLocExecuteQuery(@RequestBody final AgencyInternalLocations searchBean) {
		List<AgencyInternalLocations> searchResult = new ArrayList<>();
		try {
			searchResult = ocuintlcService.intLocExecuteQuery(searchBean);
		} catch (Exception e) {
			final AgencyInternalLocations bean = new AgencyInternalLocations();
			logger.error("Exception :", e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}

}
package net.syscon.s4.inst.movementexternal;

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
import net.syscon.s4.inst.movementexternal.beans.VOiusstri;

@EliteController
public class OiusstriController {

	private static Logger logger = LogManager.getLogger(OiusstriController.class.getName());

	@Autowired
	private OiusstriService oiusstriService;

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oiusstri/scheduledTripsExecuteQuery", method = RequestMethod.POST)
	public List<VOiusstri> scheduledtripsExecuteQuery(@RequestBody VOiusstri searchBean) {
		List<VOiusstri> searchResult = new ArrayList<>();
		try {
			searchResult = oiusstriService.scheduledtripsExecuteQuery(searchBean);
		} catch (Exception e) {
			VOiusstri bean = new VOiusstri();
			logger.error("Exception occured in " + this.getClass().getName() + " scheduledtripsExecuteQuery() ", e);
			searchResult.add(bean);
		}
		return searchResult;
	}

}

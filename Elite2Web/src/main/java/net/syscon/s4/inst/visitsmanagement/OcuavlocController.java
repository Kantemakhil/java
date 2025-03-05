package net.syscon.s4.inst.visitsmanagement;

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
import net.syscon.s4.inst.visitsmanagement.beans.VOcuavlocAvailable;
import net.syscon.s4.inst.visitsmanagement.beans.VOcuavlocUnavailable;

/**
 * Class OcuavlocController
 */
@EliteController
public class OcuavlocController {
	@Autowired
	private OcuavlocService ocuavlocService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OcuavlocController.class.getName());

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ocuavloc/avlLocExecuteQuery", method = RequestMethod.POST)
	public List<VOcuavlocAvailable> avlLocExecuteQuery(@RequestBody final VOcuavlocAvailable searchBean) {
		List<VOcuavlocAvailable> searchResult = new ArrayList<>();
		try {
			searchResult = ocuavlocService.avlLocExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error("avlLocExecuteQuery:",e);
		}
		return searchResult;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ocuavloc/fboLocExecuteQuery", method = RequestMethod.POST)
	public List<VOcuavlocUnavailable> fboLocExecuteQuery(@RequestBody final VOcuavlocUnavailable searchBean) {
		List<VOcuavlocUnavailable> searchResult = new ArrayList<>();
		try {
			searchResult = ocuavlocService.fboLocExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error("fboLocExecuteQuery:",e);
		}
		return searchResult;
	}
	/**
	 * used to execute GET_OCUAVLOC_AVAILABLE procedure.
	 * 
	 * @Param objSearchDao
	 * @return List<VOcuavlocAvailable>
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ocuavloc/getOcuavlocAvailable", method = RequestMethod.POST)
	public List<VOcuavlocAvailable> getOcuavlocAvailable(@RequestBody final VOcuavlocAvailable searchBean) {
		List<VOcuavlocAvailable> searchResult = new ArrayList<>();
		try {
			searchResult = ocuavlocService.getOcuavlocAvailable(searchBean);
		} catch (Exception e) {
			logger.error("getOcuavlocAvailable:",e);
		}
		return searchResult;
	}
	/**
	 * used to execute GET_OCUAVLOC_UNAVAILABLE procedure.
	 * 
	 * @Param objSearchDao
	 * @return List<VOcuavlocUnavailable>
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ocuavloc/getOcuavlocUnAvailable", method = RequestMethod.POST)
	public List<VOcuavlocUnavailable> getOcuavlocUnAvailable(@RequestBody final VOcuavlocUnavailable searchBean) {
		List<VOcuavlocUnavailable> searchResult = new ArrayList<>();
		try {
			searchResult = ocuavlocService.getOcuavlocUnAvailable(searchBean);
		} catch (Exception e) {
			logger.error("getOcuavlocUnAvailable:",e);
		}
		return searchResult;
	}
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ocuavloc/reCheckTimeSlot", method = RequestMethod.POST)
	public Integer reCheckTimeSlot(@RequestBody final VOcuavlocAvailable searchBean) {
		Integer returnValue = 0;
		try {
			returnValue = ocuavlocService.reCheckTimeSlot(searchBean);
		} catch (Exception e) {
			logger.error("reCheckTimeSlot:",e);
		}
		return returnValue;
	}
}
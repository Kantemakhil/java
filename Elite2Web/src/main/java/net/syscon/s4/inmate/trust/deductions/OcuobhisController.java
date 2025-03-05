package net.syscon.s4.inmate.trust.deductions;

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
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.inmate.beans.OffenderObligationHty;

/**
 * class OcuobhisController
 */
@EliteController
public class OcuobhisController {
	@Autowired
	private OcuobhisService ocuobhisService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OcuobhisController.class.getName());

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocuobhis/offOblHtyExecuteQuery", method = RequestMethod.POST)
	public List<OffenderObligationHty> offOblHtyExecuteQuery(@RequestBody final OffenderObligationHty searchBean) {
		List<OffenderObligationHty> searchResult = new ArrayList<>();
		try {
			searchResult = ocuobhisService.offOblHtyExecuteQuery(searchBean);
		} catch (Exception e) {
			final OffenderObligationHty bean = new OffenderObligationHty();
			logger.error("In offOblHtyExecuteQuery method : ", e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocuobhis/whenNewFormInstance", method = RequestMethod.POST)
	public SystemProfiles whenNewFormInstance(@RequestBody final SystemProfiles searchBean) {
		SystemProfiles searchResult = null;
		try {
			searchResult = ocuobhisService.whenNewFormInstance(searchBean);
		} catch (Exception e) {
			final SystemProfiles bean = new SystemProfiles();
			logger.error("In whenNewFormInstance method : ", e);
			bean.setErrorMessage(e.getMessage());
		}
		return searchResult;
	}

}
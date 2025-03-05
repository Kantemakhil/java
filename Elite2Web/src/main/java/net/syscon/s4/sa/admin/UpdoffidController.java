package net.syscon.s4.sa.admin;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import net.syscon.s4.common.EliteController;
import net.syscon.s4.common.beans.VHeaderBlock;

/**
 * Class UpdoffidController
 */
@EliteController
public class UpdoffidController {
	@Autowired
	private UpdoffidService updoffidService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(UpdoffidController.class.getName());

	/**
	 * getting subAreaRg LOV values
	 * 
	 * @return List<Areas>
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/updoffid/checkOffenderIdDisplay", method = RequestMethod.GET)
	public Integer checkOffenderIdDisplay(@RequestParam(value = "offIdDisplay") final String offIdDisplay) {
		Integer returnValue = 0;
		try {
			returnValue = updoffidService.checkOffenderIdDisplay(offIdDisplay);
		} catch (Exception e) {
			returnValue = 2;
			logger.error("Exception in checkOffenderIdDisplay: ", e);
		}
		return returnValue;
	}

	/**
	 * getting subAreaRg LOV values
	 * 
	 * @return List<Areas>
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/updoffid/updateOffIdDisplay", method = RequestMethod.POST)
	public Integer updateOffIdDisplay(@RequestBody final VHeaderBlock searchBean) {
		Integer returnValue = 0;
		try {
			if (searchBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				searchBean.setCreateUserId(userName);
			}
			returnValue = updoffidService.updateOffIdDisplay(searchBean);
		} catch (Exception e) {
			returnValue = 2;
			logger.error("Exception in checkOffenderIdDisplay: ", e);
		}
		return returnValue;
	}

}
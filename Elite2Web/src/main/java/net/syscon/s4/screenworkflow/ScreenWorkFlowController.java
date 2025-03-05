package net.syscon.s4.screenworkflow;

import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import net.syscon.s4.common.EliteController;
import net.syscon.s4.common.beans.ScreenFlowWork;
import net.syscon.s4.common.screenworkflow.ScreenWorkFlowService;

@EliteController
public class ScreenWorkFlowController {
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(ScreenWorkFlowController.class.getName());
	
	@Autowired
	ScreenWorkFlowService screenflow;
	/**
	 * returning ScreenFlowWork table values
	 * 
	 * @param screenId
	 * @return List<ScreenFlowWork>
	 */
	
	//@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/screen-workflow/getWorkFlowScreens", method = RequestMethod.GET)
	public @ResponseBody List<ScreenFlowWork> getWorkFlowScreens() {
		List<ScreenFlowWork> searchResult = new ArrayList<>();
		try {
			searchResult = screenflow.getWorkFlowScreens();
		} catch (Exception e) {
			logger.error("Exception in Reference Codes: ", e);
		}

		return searchResult;
	}
	

}

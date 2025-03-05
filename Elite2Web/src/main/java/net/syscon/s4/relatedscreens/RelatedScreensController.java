package net.syscon.s4.relatedscreens;

import java.util.List;
import java.util.ArrayList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import net.syscon.s4.common.EliteController;
import net.syscon.s4.common.beans.RelatedScreens;
import net.syscon.s4.relatedscreens.RelatedScreensService;

@EliteController
public class RelatedScreensController {
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(RelatedScreensController.class.getName());
	
	@Autowired
	RelatedScreensService relatedScreens;
	/**
	 * returning RelatedScreens table values
	 * 
	 * @return List<RelatedScreens>
	 */
	
	
	@RequestMapping(value = "/relatedScreens/getRelatedScreens", method = RequestMethod.GET)
	public @ResponseBody List<RelatedScreens> getRelatedScreens() {
		List<RelatedScreens> relatedResult = new ArrayList<>();
		try {
			relatedResult = relatedScreens.getRelatedScreens();
		} catch (Exception e) {
			logger.error("Exception in Related Screens Reference: ", e);
		}

		return relatedResult;
	}
}

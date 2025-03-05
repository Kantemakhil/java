package net.syscon.s4.inst.automatedcounts;

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
import net.syscon.s4.im.beans.VLivingUnitOffenders;

/**
 * Class OiiunrolController
 */
@EliteController
public class OiiunrolController {
	@Autowired
	private OiiunrolService oiiunrolService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OiiunrolController.class.getName());

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oiiunrol/rollListExecuteQuery", method = RequestMethod.POST)
	public List<VLivingUnitOffenders> rollListExecuteQuery(@RequestBody final VLivingUnitOffenders searchBean) {
		List<VLivingUnitOffenders> searchResult = new ArrayList<>();
		try {
			searchResult = oiiunrolService.rollListExecuteQuery(searchBean);
		} catch (Exception e) {
			final VLivingUnitOffenders bean = new VLivingUnitOffenders();
			logger.error("In method rollListExecuteQuery",e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}

}
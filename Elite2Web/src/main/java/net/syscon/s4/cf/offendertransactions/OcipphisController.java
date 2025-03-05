package net.syscon.s4.cf.offendertransactions;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import net.syscon.s4.cf.offendertransactions.beans.VPaymentPlanHistories;
import net.syscon.s4.common.EliteController;

/**
 * OcipphisController
 */
@EliteController
public class OcipphisController {
	@Autowired
	private OcipphisService ocipphisService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OcipphisController.class.getName());

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocipphis/vPaymentPlanHistoriesExecuteQuery", method = RequestMethod.POST)
	public List<VPaymentPlanHistories> vPaymentPlanHistoriesExecuteQuery(
			@RequestBody final VPaymentPlanHistories searchBean) {
		List<VPaymentPlanHistories> searchResult = new ArrayList<>();
		try {
			searchResult = ocipphisService.vPaymentPlanHistoriesExecuteQuery(searchBean);
		} catch (Exception e) {
			final VPaymentPlanHistories bean = new VPaymentPlanHistories();
			logger.error("Exception :", e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}

}
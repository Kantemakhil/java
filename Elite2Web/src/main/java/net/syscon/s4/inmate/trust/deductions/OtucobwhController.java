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
import net.syscon.s4.inmate.beans.OffenderAdjustmentTxns;

/**
 */
@EliteController
public class OtucobwhController {
@Autowired
private OtucobwhService otucobwhService;
	/**
	* Logger object used to print the log in the file
	*/
	private static Logger logger = LogManager.getLogger(OtucobwhController.class.getName());
	/**
	 *Fetching the record from database table
	 *@Param searchRecord
	*/
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value="/otucobwh/cowohExecuteQuery", method=RequestMethod.POST)
	public List<OffenderAdjustmentTxns> cowohExecuteQuery(@RequestBody final OffenderAdjustmentTxns searchBean) {
		List<OffenderAdjustmentTxns> searchResult = new ArrayList<>();
		try {
			searchResult = otucobwhService.cowohExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error("Exception in cowohExecuteQuery:",e);
		}
		return searchResult;
	}

}
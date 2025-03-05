package net.syscon.s4.inmate.trust.trustaccounts;

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
import net.syscon.s4.im.beans.OffenderTransactions;
import net.syscon.s4.inmate.beans.OffenderDeductions;

/**
 * Class OtupayinController
 */
@EliteController
public class OtupayinController {
	@Autowired
	private OtupayinService otupayinService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OtupayinController.class.getName());

	/**
	 * Fetching the record from database table 
	 * Method offDedExecuteQuery
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otupayin/offDedExecuteQuery", method = RequestMethod.POST)
	public OffenderDeductions offDedExecuteQuery(@RequestBody final OffenderDeductions searchBean) {
		OffenderDeductions searchResult = new OffenderDeductions();
		try {
			searchResult = otupayinService.offDedExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error("Exception in offDedExecuteQuery :", e);
		}
		return searchResult;
	}

	/**
	 * Fetching the record from database table 
	 * Method offTxnExecuteQuery
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otupayin/offTxnExecuteQuery", method = RequestMethod.POST)
	public List<OffenderTransactions> offTxnExecuteQuery(@RequestBody final OffenderDeductions searchBean) {
		List<OffenderTransactions> searchResult = new ArrayList<>();
		try {
			searchResult = otupayinService.offTxnExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error("Exception in offTxnExecuteQuery:", e);
		}
		return searchResult;
	}

}
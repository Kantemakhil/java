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
import net.syscon.s4.im.beans.OffenderTransactions;
import net.syscon.s4.inmate.beans.DeductionTypes;
import net.syscon.s4.inmate.beans.OffenderDeductions;

@EliteController
public class OtiopinqController {
	@Autowired
	private OtiopinqService otiopinqService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OtiopinqController.class.getName());

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otiopinq/offDedExecuteQuery", method = RequestMethod.POST)
	public List<OffenderDeductions> offDedExecuteQuery(@RequestBody final OffenderDeductions searchBean) {
		List<OffenderDeductions> searchResult = new ArrayList<>();
		try {
			searchResult = otiopinqService.offDedExecuteQuery(searchBean);
		} catch (Exception e) {
			OffenderDeductions bean = new OffenderDeductions();
			logger.error("OtiopingController offDedExecuteQuery unable to call the service", e.getMessage());
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
	@RequestMapping(value = "/otiopinq/offTxnExecuteQuery", method = RequestMethod.POST)
	public List<OffenderTransactions> offTxnExecuteQuery(@RequestBody OffenderTransactions searchBean) {
		List<OffenderTransactions> searchResult = new ArrayList<>();
		try {
			searchResult = otiopinqService.offTxnExecuteQuery(searchBean);
		} catch (Exception e) {
			OffenderTransactions bean = new OffenderTransactions();
			logger.error("OtiopingController offTxnExecuteQuery unable to call the service", e.getMessage());
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
	@RequestMapping(value = "/otiopinq/sysPflExecuteQuery", method = RequestMethod.POST)
	public List<SystemProfiles> sysPflExecuteQuery(@RequestBody SystemProfiles searchBean) {
		List<SystemProfiles> searchResult = new ArrayList<>();
		try {
			searchResult = otiopinqService.sysPflExecuteQuery(searchBean);
		} catch (Exception e) {
			SystemProfiles bean = new SystemProfiles();
			logger.error("OtiopingController sysPflExecuteQuery unable to call the service", e.getMessage());
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
	@RequestMapping(value = "/otiopinq/cgfkchkOffDedOffDedDed", method = RequestMethod.GET)
	public List<DeductionTypes> cgfkchkOffDedOffDedDed() {
		List<DeductionTypes> searchResult = new ArrayList<>();
		try {
			searchResult = otiopinqService.cgfkchkOffDedOffDedDed();
		} catch (Exception e) {
			logger.error("OtiopingController cgfkchkOffDedOffDedDed unable to call the service", e.getMessage());
		}
		return searchResult;
	}

}
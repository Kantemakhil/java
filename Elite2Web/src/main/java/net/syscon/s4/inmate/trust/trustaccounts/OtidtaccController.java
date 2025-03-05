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
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.im.beans.OffenderTransactions;
import net.syscon.s4.im.beans.OffenderTrustAccounts;
import net.syscon.s4.inmate.beans.OffenderDeductions;
import net.syscon.s4.inmate.beans.OffenderSubAccounts;

/**
 * Class OtidtaccController
 */
@EliteController
public class OtidtaccController {
	@Autowired
	private OtidtaccService otidtaccService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OtidtaccController.class.getName());

	/**
	 * getting calcAccountBalances LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otidtacc/calcAccountBalancesRecordGroup", method = RequestMethod.GET)
	public List<OffenderTransactions> calcAccountBalancesRecordGroup() {
		List<OffenderTransactions> recordList = new ArrayList<OffenderTransactions>();
		try {
			recordList = otidtaccService.calcAccountBalancesRecordGroup();
		} catch (Exception e) {
			logger.error(e);
		}
		return recordList;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otidtacc/offTaExecuteQuery", method = RequestMethod.POST)
	public List<OffenderTrustAccounts> offTaExecuteQuery(@RequestBody final OffenderTrustAccounts searchBean) {
		List<OffenderTrustAccounts> searchResult = new ArrayList<>();
		try {
			searchResult = otidtaccService.offTaExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error(e);
		}
		return searchResult;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otidtacc/offSubaExecuteQuery", method = RequestMethod.POST)
	public List<OffenderSubAccounts> offSubaExecuteQuery(@RequestBody final OffenderSubAccounts searchBean) {
		List<OffenderSubAccounts> searchResult = new ArrayList<>();
		try {
			searchResult = otidtaccService.offSubaExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error(e);
		}
		return searchResult;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otidtacc/offDedExecuteQuery", method = RequestMethod.POST)
	public List<OffenderDeductions> offDedExecuteQuery(@RequestBody final OffenderDeductions searchBean) {
		List<OffenderDeductions> searchResult = new ArrayList<>();
		try {
			searchResult = otidtaccService.offDedExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error(e);
		}
		return searchResult;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otidtacc/offTxnExecuteQuery", method = RequestMethod.POST)
	public List<OffenderTransactions> offTxnExecuteQuery(@RequestBody final OffenderTransactions searchBean) {
		List<OffenderTransactions> searchResult = new ArrayList<>();
		try {
			searchResult = otidtaccService.offTxnExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error(e);
		}
		return searchResult;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otidtacc/sysPflExecuteQuery", method = RequestMethod.POST)
	public List<SystemProfiles> sysPflExecuteQuery(@RequestBody final SystemProfiles searchBean) {
		List<SystemProfiles> searchResult = new ArrayList<>();
		try {
			searchResult = otidtaccService.sysPflExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error(e);
		}
		return searchResult;
	}
	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otidtacc/populateCreditObligation", method = RequestMethod.POST)
	public OffenderDeductions populateCreditObligation(@RequestBody final OffenderSubAccounts searchBean) {
		OffenderDeductions searchResult = new OffenderDeductions();
		try {
			searchResult = otidtaccService.populateCreditObligation(searchBean);
		} catch (Exception e) {
			logger.error("Exception in populateCreditObligation:", e);
		}
		return searchResult;
	}

}
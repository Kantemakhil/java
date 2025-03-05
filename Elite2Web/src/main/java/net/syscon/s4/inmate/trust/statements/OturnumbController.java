package net.syscon.s4.inmate.trust.statements;

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
import net.syscon.s4.common.beans.OffenderTransactionsCommitBean;
import net.syscon.s4.im.beans.OffenderTransactions;

/**
 * class OturnumbController
 * 
 */

@EliteController
public class OturnumbController {

	@Autowired
	private OturnumbService oturnumbService;

	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OturnumbController.class.getName());

	/**
	 * Fetching the record from database table
	 *
	 * @param searchBean {@link OffenderTransactions}
	 * @return a list of the OffenderTransactions {@link OffenderTransactions} for the matched OffenderTransactions
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oturnumb/offTxnExecuteQuery", method = RequestMethod.POST)
	public List<OffenderTransactions> offTxnExecuteQuery(@RequestBody final OffenderTransactions searchBean) {
		List<OffenderTransactions> searchResult = new ArrayList<>();
		try {
			searchResult = oturnumbService.offTxnExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error("Exception offTxnExecuteQuery :", e);
		}
		return searchResult;
	}

	/**
	 * Perfomring basic Oracle form functions i.e. insert,delete, update int the
	 * database table
	 * 
	 * @param commitBean
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/oturnumb/offTxnCommit", method = RequestMethod.POST)
	public Integer offTxnCommit(@RequestBody final OffenderTransactionsCommitBean commitBean) {
		int liReturn = 0;
		try {
			liReturn = oturnumbService.offTxnCommit(commitBean);
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return liReturn;
	}

}
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
import org.springframework.web.bind.annotation.ResponseBody;

import net.syscon.s4.common.EliteController;
import net.syscon.s4.im.beans.OffenderTransactions;

@EliteController
public class OcdcrefuController {
	@Autowired
	private OcdcrefuService ocdcrefuService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OcdcrefuController.class.getName());

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdcrefu/offTxnExecuteQuery", method = RequestMethod.POST)
	public List<OffenderTransactions> offTxnExecuteQuery(@RequestBody final OffenderTransactions searchBean) {
		OffenderTransactions offenderTransactions = new OffenderTransactions();
		final List<OffenderTransactions> searchResult = new ArrayList<OffenderTransactions>();
		try {
			offenderTransactions = ocdcrefuService.offTxnExecuteQuery(searchBean);
			if (offenderTransactions != null) {
				searchResult.add(offenderTransactions);
			}
		} catch (final Exception e) {
			final OffenderTransactions bean = new OffenderTransactions();
			logger.error("Exception :", e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}

	/**
	 * validateQuery
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdcrefu/onValidateTxnEntryAmount", method = RequestMethod.POST)
	public List<OffenderTransactions> offTxnValidateQuery(@RequestBody final OffenderTransactions searchBean) {
		OffenderTransactions offenderTransactions = new OffenderTransactions();
		final List<OffenderTransactions> searchResult = new ArrayList<OffenderTransactions>();
		try {
			offenderTransactions = ocdcrefuService.offTxnValidateQuery(searchBean);
			if (offenderTransactions != null) {
				searchResult.add(offenderTransactions);
			}
		} catch (final Exception e) {
			final OffenderTransactions bean = new OffenderTransactions();
			logger.error("Exception :", e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}

	/**
	 * Perfomring basic Oracle form functions i.e. insert,delete, update int the
	 * database table
	 * 
	 * @Param commitBean
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/ocdcrefu/offTxnCommit", method = RequestMethod.POST)
	public @ResponseBody OffenderTransactions offTxnCommit(@RequestBody final OffenderTransactions commitBean) {
		OffenderTransactions offenderTransactions = null;
		try {
			offenderTransactions = ocdcrefuService.offTxnCommit(commitBean);
		} catch (final Exception e) {

			logger.error("Exception : Ocdcrefu", e);
		}
		return offenderTransactions;
	}

}
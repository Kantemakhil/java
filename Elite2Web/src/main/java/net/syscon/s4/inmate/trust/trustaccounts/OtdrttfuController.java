package net.syscon.s4.inmate.trust.trustaccounts;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import net.syscon.s4.common.EliteController;
import net.syscon.s4.common.beans.OffenderTransactionsCommitBean;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.im.beans.OffenderTransactions;
import net.syscon.s4.inmate.beans.OffenderTrustTransfers;
import net.syscon.s4.inmate.beans.OffenderTrustTransfersCommitBean;

/**
 * class OtdrttfuController
 */
@EliteController
public class OtdrttfuController {
	@Autowired
	private OtdrttfuService otdrttfuService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OtdrttfuController.class.getName());

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otdrttfu/offTtExecuteQuery", method = RequestMethod.POST)
	public List<OffenderTrustTransfers> offTtExecuteQuery(@RequestBody final OffenderTrustTransfers searchBean) {
		List<OffenderTrustTransfers> searchResult = new ArrayList<>();
		try {
			searchResult = otdrttfuService.offTtExecuteQuery(searchBean);
		} catch (Exception e) {
			final OffenderTrustTransfers bean = new OffenderTrustTransfers();
			logger.error("In offTtExecuteQuery method : ", e);
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
	@RequestMapping(value = "/otdrttfu/offTtCommit", method = RequestMethod.POST)
	public @ResponseBody List<OffenderTrustTransfers>  offTtCommit(@RequestBody final OffenderTrustTransfersCommitBean commitBean) {
		List<OffenderTrustTransfers>  returnList =new ArrayList<OffenderTrustTransfers>();
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		commitBean.setCreateUserId(userName);
		try {
			returnList = otdrttfuService.offTtCommit(commitBean);
		} catch (Exception e) {
			logger.error("In offTtCommit method : ", e);
		}
		return returnList;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otdrttfu/offTxnExecuteQuery", method = RequestMethod.POST)
	public List<OffenderTransactions> offTxnExecuteQuery(@RequestBody final OffenderTransactions searchBean) {
		List<OffenderTransactions> searchResult = new ArrayList<>();
		try {
			searchResult = otdrttfuService.offTxnExecuteQuery(searchBean);
		} catch (Exception e) {
			final OffenderTransactions bean = new OffenderTransactions();
			logger.error("In offTxnExecuteQuery method : ", e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}

	/**
	 * Performing basic Oracle form functions i.e. insert,delete, update into
	 * the database table
	 * 
	 * @Param commitBean
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/otdrttfu/offTxnCommit", method = RequestMethod.POST)
	public @ResponseBody Integer offTxnCommit(@RequestBody final OffenderTransactionsCommitBean commitBean) {
		int liReturn = 0;
		try {
			liReturn = otdrttfuService.offTxnCommit(commitBean);
		} catch (Exception e) {
			logger.error("In offTxnCommit method : ", e);
		}
		return liReturn;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otdrttfu/sysPflExecuteQuery", method = RequestMethod.POST)
	public List<SystemProfiles> sysPflExecuteQuery(@RequestBody final SystemProfiles searchBean) {
		List<SystemProfiles> searchResult = new ArrayList<>();
		try {
			searchResult = otdrttfuService.sysPflExecuteQuery(searchBean);
		} catch (Exception e) {
			final SystemProfiles bean = new SystemProfiles();
			logger.error("In sysPflExecuteQuery method : ", e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}

}
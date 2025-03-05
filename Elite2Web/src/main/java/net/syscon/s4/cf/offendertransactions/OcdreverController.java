package net.syscon.s4.cf.offendertransactions;

import java.math.BigDecimal;
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

import net.syscon.s4.cf.deductions.beans.OffFeeBillTransactions;
import net.syscon.s4.cf.deductions.beans.OffFeeBillTransactionsCommitBean;
import net.syscon.s4.common.EliteController;

@EliteController
public class OcdreverController {
	@Autowired
	private OcdreverService ocdreverService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OcdbreciController.class.getName());

	/**
	 * offFeeTxnExecuteQuery
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdrever/offFeeTxnExecuteQuery", method = RequestMethod.GET)
	public List<OffFeeBillTransactions> offFeeTxnExecuteQuery(final BigDecimal offenderBookId) {
		List<OffFeeBillTransactions> returnList = new ArrayList<>();
		try {
			returnList = ocdreverService.offFeeTxnExecuteQuery(offenderBookId);
		} catch (Exception e) {
			logger.error("Exception in offFeeTxnExecuteQuery :", e);
		}
		return returnList;
	}

	/**
	 * Perfomring basic Oracle form functions i.e. insert,delete, update int the
	 * database table
	 * 
	 * @Param commitBean
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/ocdrever/adjustRevCommit", method = RequestMethod.POST)
	public @ResponseBody Integer adjustRevCommit(@RequestBody final OffFeeBillTransactionsCommitBean commitBean) {
		Integer liReturn = 0;
		try {
			if (commitBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				commitBean.setCreateUserId(userName);
			}
			liReturn = ocdreverService.adjustRevCommit(commitBean);
		} catch (Exception e) {

			logger.error("Exception in adjustRevCommit:", e);
		}
		return liReturn;
	}

}

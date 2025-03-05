package net.syscon.s4.cf.maintenance;

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

import net.syscon.s4.cf.maintenance.beans.FeeAccounts;
import net.syscon.s4.cf.maintenance.beans.FeeAccountsCommitBean;
import net.syscon.s4.common.EliteController;
import net.syscon.s4.im.beans.AccountCodes;
import net.syscon.s4.inmate.beans.DeductionTypes;
import net.syscon.s4.inmate.trust.financialsmaintenance.transaction.OcmcoactController;

@EliteController
public class OcmpfaccController {
	@Autowired
	private OcmpfaccService ocmpfacservice;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OcmcoactController.class.getName());

	/**
	 * getting cgfkAcCodeRecAccountCode LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocmpfacc/getAccCodes", method = RequestMethod.GET)
	public List<AccountCodes> getAccCodes() {
		List<AccountCodes> recordList = new ArrayList<>();
		try {
			recordList = ocmpfacservice.getAccCodes();
		} catch (Exception e) {
			logger.error("getAccCodes :", e);
		}
		return recordList;
	}

	/**
	 * getting cgfkAcCodeRecAccountCode LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocmpfacc/getFeeCodes", method = RequestMethod.GET)
	public List<DeductionTypes> getFeeCodes() {
		List<DeductionTypes> recordList = new ArrayList<>();
		try {
			recordList = ocmpfacservice.getFeeCodes();
		} catch (Exception e) {
			logger.error("getFeeCodes :", e);
		}
		return recordList;
	}

	/**
	 * getting cgfkAcCodeRecAccountCode LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocmpfacc/feeAccountsExecuteQuery", method = RequestMethod.GET)
	public List<FeeAccounts> feeAccountsExecuteQuery() {
		List<FeeAccounts> recordList = new ArrayList<>();
		try {
			recordList = ocmpfacservice.feeAccountsExecuteQuery();
		} catch (Exception e) {
			logger.error("accountCodesExecuteQuery :", e);
		}
		return recordList;
	}

	/**
	 * Perfomring basic Oracle form functions i.e. insert,delete, update int the
	 * database table
	 * 
	 * @Param commitBean
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/ocmpfacc/feeAccountCommit", method = RequestMethod.POST)
	public @ResponseBody FeeAccounts feeAccountCommit(@RequestBody final FeeAccountsCommitBean commitBean) {
		FeeAccounts liReturn = new FeeAccounts();
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		commitBean.setCreateUserId(userName);
		try {
			liReturn = ocmpfacservice.feeAccountCommit(commitBean);
		} catch (Exception e) {
			logger.error("Exception in feeAccountCommit:", e);
		}
		return liReturn;
	}
}

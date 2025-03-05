package net.syscon.s4.inmate.trust.financialsmaintenance;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import net.syscon.s4.common.EliteController;
import net.syscon.s4.common.beans.TransactionTypes;
import net.syscon.s4.im.beans.AccountCodes;
import net.syscon.s4.inmate.beans.FreezeDisbursements;
import net.syscon.s4.inmate.beans.FreezeDisbursementsCommitBean;

/**
 * @author Arkin Software Technologies
 * @version 1.0
 */
@EliteController
public class OtmfreezController {
@Autowired
private OtmfreezService otmfreezService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OtmfreezController.class.getName());

	/**
	 * getting cgfkFreDisTxnType LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otmfreez/cgfkFreDisTxnTypeRecordGroup", method = RequestMethod.GET)
	public List<TransactionTypes> cgfkFreDisTxnTypeRecordGroup(@RequestParam (value="caseloadType") final String caseloadType) {
		List<TransactionTypes> recordList = new ArrayList<TransactionTypes>();
		try {
			recordList = otmfreezService.cgfkFreDisTxnTypeRecordGroup(caseloadType);
		} catch (Exception e) {
			logger.error(e);
		}
		return recordList;
	}

	/**
	 * getting cgfkFreDisAccountCode LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otmfreez/cgfkFreDisAccountCodeRecordGroup", method = RequestMethod.GET)
	public List<AccountCodes> cgfkFreDisAccountCodeRecordGroup(@RequestParam (value="caseloadType") final String caseloadType) {
		List<AccountCodes> recordList = new ArrayList<AccountCodes>();
		try {
			recordList = otmfreezService.cgfkFreDisAccountCodeRecordGroup(caseloadType);
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
	@RequestMapping(value = "/otmfreez/freDisExecuteQuery", method = RequestMethod.POST)
	public List<FreezeDisbursements> freDisExecuteQuery(@RequestBody final FreezeDisbursements searchBean) {
		List<FreezeDisbursements> searchResult = new ArrayList<>();
		try {
			searchResult = otmfreezService.freDisExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error(e);
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
	@RequestMapping(value = "/otmfreez/freDisCommit", method = RequestMethod.POST)
	public @ResponseBody Integer freDisCommit(@RequestBody final FreezeDisbursementsCommitBean commitBean) {
		int liReturn = 0;
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		commitBean.setCreateUserId(userName);
		try {
			liReturn = otmfreezService.freDisCommit(commitBean);
		} catch (Exception e) {

			logger.error(e);
		}
		return liReturn;
	}
	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otmfreez/getTxnUage", method = RequestMethod.GET)
	public @ResponseBody String getTxnUage(@RequestParam(value="txnCode") final String txnCode,
			@RequestParam(value="caseloadType") final String caseloadType) {
		String liReturn = null;
		try {
			liReturn = otmfreezService.getTxnUage(txnCode,caseloadType);
		} catch (Exception e) {

			logger.error(e);
		}
		return liReturn;
	}
}
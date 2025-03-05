package net.syscon.s4.cf.offendertransactions;

import java.math.BigDecimal;
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

import net.syscon.s4.cf.deductions.beans.OffFeeBillTransactions;
import net.syscon.s4.cf.deductions.beans.OffFeeBillTransactionsCommitBean;
import net.syscon.s4.common.EliteController;
import net.syscon.s4.common.beans.TransactionTypes;
import net.syscon.s4.common.beans.VHeaderBlock;
import net.syscon.s4.im.beans.OffenderTransactions;
import net.syscon.s4.im.beans.VTrustHeader;

@EliteController
public class OcdadjusController {
	@Autowired
	private OcdadjusService ocdadjusService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OcdbreciController.class.getName());

	/**
	 * offFeeExecuteQuery
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdadjus/offFeeExecuteQuery", method = RequestMethod.GET)
	public List<OffFeeBillTransactions> offFeeExecuteQuery(final BigDecimal offenderBookId) {
		List<OffFeeBillTransactions> returnList = new ArrayList<>();
		try {
			returnList = ocdadjusService.offFeeExecuteQuery(offenderBookId);
		} catch (Exception e) {
			logger.error("Exception in offFeeExecuteQuery :", e);
		}
		return returnList;
	}

	/**
	 * TransactionTypes
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdadjus/rgAdjustType", method = RequestMethod.GET)
	public List<TransactionTypes> rgAdjustType() {
		List<TransactionTypes> returnList = new ArrayList<>();
		try {
			returnList = ocdadjusService.rgAdjustType();
		} catch (Exception e) {
			logger.error("Exception in rgAdjustType :", e);
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
	@RequestMapping(value = "/ocdadjus/adjustAmountCommit", method = RequestMethod.POST)
	public @ResponseBody Integer adjustAmountCommit(@RequestBody final OffFeeBillTransactionsCommitBean commitBean) {
		Integer liReturn = 0;
		try {
			liReturn = ocdadjusService.offTxnCommit(commitBean);
		} catch (Exception e) {

			logger.error("Exception :", e);
		}
		return liReturn;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdadjus/getbillEndDayPfVal", method = RequestMethod.GET)
	public String getbillEndDayPfVal() {
		String returnVal = null;
		try {
			returnVal = ocdadjusService.getbillEndDayPfVal();
		} catch (Exception e) {
			logger.error("Exception in getbillEndDayPfVal :", e);
		}
		return returnVal;
	}

	//@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdadjus/getCasePlanId", method = RequestMethod.POST)
	public Integer getCasePlanId(@RequestBody final VTrustHeader bean) {
		Integer returnVal = 0;
		try {
			returnVal = ocdadjusService.getCasePlanId(bean);
		} catch (Exception e) {
			logger.error("Exception in getCasePlanId :", e);
		}
		return returnVal;
	}

}

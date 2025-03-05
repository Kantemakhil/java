package net.syscon.s4.cf.deductions;

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
import net.syscon.s4.common.beans.ReferenceCodes;

/**
 * class OcufovdtController
 */
@EliteController
public class OcubadjsController {

	@Autowired
	private OcubadjsService ocubadjsService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OcubadjsController.class.getName());

	/**
	 * getting cgfkOffDedDspDescription LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocubadjs/adjustmentTypeRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> adjustmentTypeRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<>();
		try {
			recordList = ocubadjsService.adjustmentTypeRecordGroup();
		} catch (final Exception e) {
			final ReferenceCodes obj = new ReferenceCodes();
			logger.error("overrideTypeRecordGroup: ", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * Fetching the record from database table
	 *
	 * @param searchBean
	 *            {@link OffFeeBillTransactions}
	 * @return a list of the FeeAccountProfiles {@link OffFeeBillTransactions} for the
	 *         matching passed data
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocubadjs/billAdjustDetailsExecuteQuery", method = RequestMethod.POST)
	public List<OffFeeBillTransactions> billAdjustDetailsExecuteQuery(@RequestBody final OffFeeBillTransactions searchBean) {
		List<OffFeeBillTransactions> searchResult = new ArrayList<>();
		try {
			searchResult = ocubadjsService.billAdjustDetailsExecuteQuery(searchBean);
		} catch (final Exception e) {
			final OffFeeBillTransactions bean = new OffFeeBillTransactions();
			logger.error("Exception: ", e);
			bean.setErrorMessage(e.getMessage());
		}
		return searchResult;
	}

	/**
	 * Perfomring basic Oracle form functions i.e. insert,delete, update int the
	 * database tablE
	 * 
	 * @param commitBean
	 *            {@link OffFeeBillTransactionsCommitBean}
	 * @return a list of the FeeOverrideDetails
	 *         {@link OffFeeBillTransactionsCommitBean} for the matching passed data
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/ocubadjs/saveBillAdjustdDetCommit", method = RequestMethod.POST)
	public @ResponseBody List<OffFeeBillTransactions> saveBillAdjustdDetCommit(
			@RequestBody final OffFeeBillTransactionsCommitBean commitBean) {
		List<OffFeeBillTransactions> liReturn = new ArrayList<>();
		try {
			String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			commitBean.setCreateUserId(userName);
			liReturn = ocubadjsService.saveBillAdjustdDetCommit(commitBean);
		} catch (final Exception e) {
			final OffFeeBillTransactions error = new OffFeeBillTransactions();
			final String errorMsg = "Error : " + e.getMessage();
			error.setErrorMessage(errorMsg);
			liReturn.add(error);
			logger.error("Exception :", e);

			logger.error("saveBillAdjustdDetCommit : ", e);
		}
		return liReturn;
	}
	
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/ocubadjs/getSelectedOverrideType", method = RequestMethod.POST)
	public @ResponseBody String getSelectedOverrideType(
			@RequestBody final OffFeeBillTransactions commitBean) {
		String liReturn = null;
		try {
			liReturn = ocubadjsService.getSelectedOverrideType(commitBean);
		} catch (final Exception e) {

			logger.error("getSelectedOverrideType : ", e);
		}
		return liReturn;
	}
	

}

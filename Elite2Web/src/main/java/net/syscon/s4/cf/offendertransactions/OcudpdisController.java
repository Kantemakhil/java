package net.syscon.s4.cf.offendertransactions;

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
import net.syscon.s4.im.beans.OffenderTransactions;
import net.syscon.s4.inmate.beans.OffenderDeductions;
import net.syscon.s4.inmate.beans.OffenderDeductionsCommitBean;

/**
 * Class OcudpdisController
 */
@EliteController
public class OcudpdisController {
	@Autowired
	private OcudpdisService ocudpdisService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OcudpdisController.class.getName());

	/**
	 * Perfomring basic Oracle form functions i.e. insert,delete, update int the
	 * database table
	 * 
	 * @Param commitBean
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/ocudpdis/tbdCommit", method = RequestMethod.POST)
	public @ResponseBody OffenderTransactions tbdCommit(@RequestBody final OffenderDeductionsCommitBean commitBean) {
		OffenderTransactions tbdDeduction = new OffenderTransactions();
		try {
			String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			commitBean.setCreateUserId(userName);
			tbdDeduction = ocudpdisService.tbdCommit(commitBean);

		} catch (final Exception e) {

			final String errorMsg = "Error : " + e.getMessage();
			tbdDeduction.setErrorMessage(errorMsg);
			logger.error("contactPersonTypesCommit :", e);
		}

		return tbdDeduction;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchBean
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocudpdis/tbdExecuteQuery", method = RequestMethod.POST)
	public OffenderDeductions tbdExecuteQuery(@RequestBody final OffenderDeductions searchBean) {
		OffenderDeductions tbdDeduction = new OffenderDeductions();
		try {
			tbdDeduction = ocudpdisService.tbdExecuteQuery(searchBean);
		} catch (final Exception e) {
			logger.error("Exception :", e);
			tbdDeduction.setErrorMessage(e.getMessage());
		}
		return tbdDeduction;
	}
}

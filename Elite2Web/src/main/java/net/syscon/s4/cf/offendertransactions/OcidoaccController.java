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
import org.springframework.web.bind.annotation.RequestParam;

import net.syscon.s4.cf.offendertransactions.beans.VOffGroupedPaymentPlans;
import net.syscon.s4.common.EliteController;
import net.syscon.s4.inmate.beans.OffenderDeductions;
import net.syscon.s4.inmate.beans.OffenderSubAccounts;

/**
 * Class OcidoaccController
 */
@EliteController
public class OcidoaccController {
	@Autowired
	private OcidoaccService ocidoaccService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OcidoaccController.class.getName());

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocidoacc/offSubaExecuteQuery", method = RequestMethod.POST)
	public List<OffenderSubAccounts> offSubaExecuteQuery(@RequestBody OffenderSubAccounts searchBean) {
		List<OffenderSubAccounts> searchResult = new ArrayList<>();
		try {
			searchResult = ocidoaccService.offSubaExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error("Exception in offSubaExecuteQuery:", e);
		}
		return searchResult;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocidoacc/paySchExecuteQuery", method = RequestMethod.POST)
	public List<VOffGroupedPaymentPlans> paySchExecuteQuery(@RequestBody VOffGroupedPaymentPlans searchBean) {
		List<VOffGroupedPaymentPlans> searchResult = new ArrayList<>();
		try {
			searchResult = ocidoaccService.paySchExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error("Exception in paySchExecuteQuery:", e);
		}
		return searchResult;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocidoacc/offBncExecuteQuery", method = RequestMethod.POST)
	public List<OffenderDeductions> offBncExecuteQuery(@RequestBody OffenderDeductions searchBean) {
		List<OffenderDeductions> searchResult = new ArrayList<>();
		try {
			searchResult = ocidoaccService.offBncExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error("Exception in offBncExecuteQuery:", e);
		}
		return searchResult;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocidoacc/sysPflExecuteQuery", method = RequestMethod.GET)
	public Integer sysPflExecuteQuery(@RequestParam(value = "rootOffenderid") final Integer rootOffenderid) {
		Integer searchResult = 0;
		try {
			searchResult = ocidoaccService.sysPflExecuteQuery(rootOffenderid);
		} catch (Exception e) {
			logger.error("Exception in sysPflExecuteQuery:", e);
		}
		return searchResult;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocidoacc/getActClosedFlag", method = RequestMethod.GET)
	public String getActClosedFlag(@RequestParam(value = "offenderId") final Integer offenderId,
			@RequestParam(value = "caseloadid") final String caseloadid) {
		String closeActFlag = null;
		try {
			closeActFlag = ocidoaccService.getActClosedFlag(offenderId, caseloadid);
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return closeActFlag;
	}

}
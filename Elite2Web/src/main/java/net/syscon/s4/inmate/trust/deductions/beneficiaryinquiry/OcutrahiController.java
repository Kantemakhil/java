package net.syscon.s4.inmate.trust.deductions.beneficiaryinquiry;

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
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.inmate.beans.BeneficiaryTransactions;
import net.syscon.s4.inmate.beans.VBankChequeBeneficiaries;

/**
 * Class OcutrahiController
 */
@EliteController
public class OcutrahiController {
	@Autowired
	private OcutrahiService ocutrahiService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OcutrahiController.class.getName());

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocutrahi/benTxnExecuteQuery", method = RequestMethod.POST)
	public List<BeneficiaryTransactions> benTxnExecuteQuery(@RequestBody final BeneficiaryTransactions searchBean) {
		List<BeneficiaryTransactions> searchResult = new ArrayList<>();
		try {
			searchResult = ocutrahiService.benTxnExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error("benTxnExecuteQuery", e);
		}
		return searchResult;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocutrahi/vBcBenExecuteQuery", method = RequestMethod.POST)
	public List<VBankChequeBeneficiaries> vBcBenExecuteQuery(@RequestBody final VBankChequeBeneficiaries searchBean) {
		List<VBankChequeBeneficiaries> searchResult = new ArrayList<>();
		try {
			searchResult = ocutrahiService.vBcBenExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error("vBcBenExecuteQuery", e);
		}
		return searchResult;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocutrahi/sysPflExecuteQuery", method = RequestMethod.POST)
	public List<SystemProfiles> sysPflExecuteQuery(@RequestBody final SystemProfiles searchBean) {
		List<SystemProfiles> searchResult = new ArrayList<>();
		try {
			searchResult = ocutrahiService.sysPflExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error("sysPflExecuteQuery", e);
		}
		return searchResult;
	}

}
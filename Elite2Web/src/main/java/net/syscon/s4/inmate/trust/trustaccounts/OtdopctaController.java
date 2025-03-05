package net.syscon.s4.inmate.trust.trustaccounts;

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
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.im.beans.OffenderTrustAccounts;
import net.syscon.s4.inmate.beans.OffenderSubAccounts;
import net.syscon.s4.inmate.beans.OffenderTrustAccountsCommitBean;

/**
 * class OtdopctaController
 */
@EliteController
public class OtdopctaController {
	@Autowired
	private OtdopctaService otdopctaService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OtdopctaController.class.getName());

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otdopcta/offTaExecuteQuery", method = RequestMethod.POST)
	public List<OffenderTrustAccounts> offTaExecuteQuery(@RequestBody final OffenderTrustAccounts searchBean) {
		List<OffenderTrustAccounts> searchResult = new ArrayList<>();
		try {
			searchResult = otdopctaService.offTaExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error("In offTaExecuteQuery method : ", e);
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
	@RequestMapping(value = "/otdopcta/offTaCommit", method = RequestMethod.POST)
	public @ResponseBody Integer offTaCommit(@RequestBody final OffenderTrustAccountsCommitBean commitBean) {
		int liReturn = 0;
		try {
			liReturn = otdopctaService.offTaCommit(commitBean);
		} catch (Exception e) {
			logger.error("In offTaCommit method : ", e);
		}
		return liReturn;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otdopcta/sysPflExecuteQuery", method = RequestMethod.POST)
	public List<SystemProfiles> sysPflExecuteQuery(@RequestBody final SystemProfiles searchBean) {
		List<SystemProfiles> searchResult = new ArrayList<>();
		try {
			searchResult = otdopctaService.sysPflExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error("In sysPflExecuteQuery method : ", e);
		}
		return searchResult;
	}

	/**
	 * This method will get the default agency code
	 * 
	 * @return Integer
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otdopcta/preInsert", method = RequestMethod.GET)
	public Integer preInsert() {
		Integer txnId = null;
		try {
			txnId = otdopctaService.preInsert();
		} catch (Exception e) {
			logger.error("In preInsert method : ", e);
		}
		return txnId;
	}

	/**
	 * Before deleting the record it verifying whether any accounts are assigned
	 * to the offender
	 * 
	 * @Param offSub
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otdopcta/cgrichkOffenderTrustAccoun", method = RequestMethod.POST)
	public @ResponseBody List<OffenderSubAccounts> cgrichkOffenderTrustAccoun(
			@RequestBody final OffenderSubAccounts offSub) {
		List<OffenderSubAccounts> vSubAccounts = null;
		try {
			vSubAccounts = otdopctaService.cgrichkOffenderTrustAccoun(offSub);
		} catch (Exception e) {
			logger.error("In cgrichkOffenderTrustAccoun method : ", e);
		}
		return vSubAccounts;
	}

}
package net.syscon.s4.inmate.trust.financialsmaintenance;

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
import net.syscon.s4.im.beans.AccountCodes;
import net.syscon.s4.inmate.beans.OffenderSubAccounts;
import net.syscon.s4.inmate.beans.OffenderSubAccountsCommitBean;

/**
 * OtmmbalaController
 */
@EliteController
public class OtmmbalaController {

	@Autowired
	private OtmmbalaService otmmbalaService;

	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OtmmbalaController.class.getName());

	/**
	 * getting cgfkOffSubaTrustAccountCo LOV values
	 * cgfkOffSubaTrustAccountCoRecordGroup
	 * 
	 * @return List<AccountCodes>
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otmmbala/cgfkOffSubaTrustAccountCoRecordGroup", method = RequestMethod.GET)
	public List<AccountCodes> cgfkOffSubaTrustAccountCoRecordGroup() {
		List<AccountCodes> recordList = new ArrayList<AccountCodes>();
		try {
			recordList = otmmbalaService.cgfkOffSubaTrustAccountCoRecordGroup();
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return recordList;
	}

	/**
	 * Fetching the record from database table offSubaExecuteQuery
	 * offSubaExecuteQuery
	 * 
	 * @Param searchRecord
	 * @return List<OffenderSubAccounts>
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otmmbala/offSubaExecuteQuery", method = RequestMethod.POST)
	public List<OffenderSubAccounts> offSubaExecuteQuery(@RequestBody OffenderSubAccounts searchBean) {
		List<OffenderSubAccounts> searchResult = new ArrayList<OffenderSubAccounts>();
		try {
			searchResult = otmmbalaService.offSubaExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return searchResult;
	}

	/**
	 * Perfomring basic Oracle form functions i.e. insert,delete, update int the
	 * database table offSubaCommit offSubaCommit
	 * 
	 * @Param commitBean
	 * @return Integer
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/otmmbala/offSubaCommit", method = RequestMethod.POST)
	public Integer offSubaCommit(@RequestBody OffenderSubAccountsCommitBean commitBean) {
		int liReturn = 0;
		try {
			liReturn = otmmbalaService.offSubaCommit(commitBean);
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return liReturn;
	}
}
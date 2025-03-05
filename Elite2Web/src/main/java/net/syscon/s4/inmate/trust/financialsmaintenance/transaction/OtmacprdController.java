package net.syscon.s4.inmate.trust.financialsmaintenance.transaction;

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
import net.syscon.s4.inmate.beans.AccountPeriods;
import net.syscon.s4.inmate.beans.AccountPeriodsCommitBean;

/**
 * Class OtmacprdController
 */
@EliteController
public class OtmacprdController {
	@Autowired
	private OtmacprdService otmacprdService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OtmacprdController.class.getName());

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otmacprd/acPrdExecuteQuery", method = RequestMethod.POST)
	public List<AccountPeriods> acPrdExecuteQuery(@RequestBody final AccountPeriods searchBean) {
		List<AccountPeriods> searchResult = new ArrayList<>();
		try {
			searchResult = otmacprdService.acPrdExecuteQuery(searchBean);
		} catch (Exception e) {
			AccountPeriods bean = new AccountPeriods();
			logger.error("Exception :", e);
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
	@RequestMapping(value = "/otmacprd/acPrdCommit", method = RequestMethod.POST)
	public @ResponseBody String acPrdCommit(@RequestBody AccountPeriodsCommitBean commitBean) {
		String liReturn = "0";
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		commitBean.setCreateUserId(userName);
		try {
			liReturn = otmacprdService.acPrdCommit(commitBean);
		} catch (Exception e) {
			liReturn = e.getMessage().toUpperCase();
			logger.error("Exception :", e);
		}
		return liReturn;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otmacprd/overlapdates", method = RequestMethod.GET)
	public List<AccountPeriods> overlapdates() {
		List<AccountPeriods> searchResult = new ArrayList<>();
		try {
			searchResult = otmacprdService.overlapdates();
		} catch (Exception e) {
			AccountPeriods bean = new AccountPeriods();
			logger.error("Exception :", e);
		}
		return searchResult;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otmacprd/duplicateAccountperiodId", method = RequestMethod.GET)
	public Integer duplicateAccountperiodId(@RequestParam(value = "accountPeriodId") final Long accountPeriodId) {
		Integer searchResult = 0;
		try {
			searchResult = otmacprdService.duplicateAccountperiodId(accountPeriodId);
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return searchResult;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otmacprd/duplicateOverlapDate", method = RequestMethod.GET)
	public Integer duplicateOverlapDate(@RequestParam(value = "overlapDate") final String overlapDate) {
		Integer searchResult = 0;
		try {
			searchResult = otmacprdService.duplicateOverlapDate(overlapDate);
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return searchResult;
	}
}
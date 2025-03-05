package net.syscon.s4.inmate.trust.checks.checksmainataince;

import java.math.BigDecimal;
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
import net.syscon.s4.im.beans.AccountCodes;
import net.syscon.s4.im.beans.BankAccountsMaintenances;
import net.syscon.s4.im.beans.BankAccountsMaintenancesCommitBean;
import net.syscon.s4.im.beans.CaseloadCurrentAccountsBase;
import net.syscon.s4.im.beans.CaseloadCurrentAccountsBaseCommitBean;
import net.syscon.s4.im.beans.Corporates;
import net.syscon.s4.im.beans.ReferenceCodes;

/**
 * OtmbaccoController
 */
@EliteController
public class OtmbaccoController {

	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OtmbaccoController.class.getName());
	@Autowired
	private OtmbaccoService otmbaccoService;

	/**
	 * csldCabExecuteQuery
	 * 
	 * @param searchBean
	 * @return
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otmbacco/csldCabExecuteQuery", method = RequestMethod.POST)
	public List<CaseloadCurrentAccountsBase> csldCabExecuteQuery(
			@RequestBody final CaseloadCurrentAccountsBase searchBean) {
		List<CaseloadCurrentAccountsBase> searchResult = new ArrayList<>();
		try {
			searchResult = otmbaccoService.csldCabExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error("csldCabExecuteQuery :", e);
		}
		return searchResult;
	}

	/**
	 * Perfomring basic Oracle form functions i.e. insert,delete, update int the
	 * database table csldCabCommit
	 * 
	 * @Param commitBean
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/otmbacco/csldCabCommit", method = RequestMethod.POST)
	public @ResponseBody Integer csldCabCommit(@RequestBody final CaseloadCurrentAccountsBaseCommitBean commitBean) {
		int liReturn = 0;
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		commitBean.setCreateUserId(userName);
		try {
			liReturn = otmbaccoService.csldCabCommit(commitBean);
		} catch (Exception e) {
			logger.error("csldCabCommit :", e);
		}
		return liReturn;
	}

	/**
	 * getting cgfkCsldCabBankAccountTyp LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otmbacco/cgfkCsldCabBankAccountTypRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> cgfkCsldCabBankAccountTypRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = otmbaccoService.cgfkCsldCabBankAccountTypRecordGroup();
		} catch (Exception e) {
			logger.error("cgfkCsldCabBankAccountTypRecordGroup :", e);
		}
		return recordList;
	}

	/**
	 * getting cgfkCsldCabPayeeCorporate LOV values
	 * cgfkCsldCabPayeeCorporateRecordGroup
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otmbacco/cgfkCsldCabPayeeCorporateRecordGroup", method = RequestMethod.GET)
	public List<Corporates> cgfkCsldCabPayeeCorporateRecordGroup() {
		List<Corporates> recordList = new ArrayList<Corporates>();
		try {
			recordList = otmbaccoService.cgfkCsldCabPayeeCorporateRecordGroup();
		} catch (Exception e) {
			logger.error("cgfkCsldCabPayeeCorporateRecordGroup :", e);
		}
		return recordList;
	}

	/**
	 * getting cgfkCsldCabAccountCode LOV values
	 * cgfkCsldCabAccountCodeRecordGroup
	 * 
	 * @return recordList
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otmbacco/cgfkCsldCabAccountCodeRecordGroup", method = RequestMethod.GET)
	public List<AccountCodes> cgfkCsldCabAccountCodeRecordGroup() {
		List<AccountCodes> recordList = new ArrayList<AccountCodes>();
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		try {
			recordList = otmbaccoService.cgfkCsldCabAccountCodeRecordGroup(userName);
		} catch (Exception e) {
			logger.error("cgfkCsldCabAccountCodeRecordGroup :", e);
		}
		return recordList;
	}

	/**
	 * Fetching the record from database table bankAmExecuteQuery
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otmbacco/bankAmExecuteQuery", method = RequestMethod.POST)
	public List<BankAccountsMaintenances> bankAmExecuteQuery(@RequestBody final BankAccountsMaintenances searchBean) {
		List<BankAccountsMaintenances> searchResult = new ArrayList<>();
		try {
			searchResult = otmbaccoService.bankAmExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error("bankAmExecuteQuery :", e);
		}
		return searchResult;
	}

	/**
	 * Perfomring basic Oracle form functions i.e. insert,delete, update int the
	 * database table bankAmCommit
	 * 
	 * @Param commitBean
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/otmbacco/bankAmCommit", method = RequestMethod.POST)
	public Integer bankAmCommit(@RequestBody final BankAccountsMaintenancesCommitBean commitBean) {
		int liReturn = 0;
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		commitBean.setCreateUserId(userName);
		try {
			liReturn = otmbaccoService.bankAmCommit(commitBean);
		} catch (Exception e) {
			logger.error("bankAmCommit :", e);
		}
		return liReturn;
	}

	/**
	 * getting cgfkOffEmDspDescription3 LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otmbacco/corporeteNameData", method = RequestMethod.GET)
	public String corporeteNameData(@RequestParam("corporateName") final BigDecimal corporateName) {
		String recordList = null;
		try {
			recordList = otmbaccoService.corporeteNameData(corporateName);
		} catch (Exception e) {
			recordList = null;
		}
		return recordList;
	}

}
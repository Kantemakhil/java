package net.syscon.s4.inmate.trust.checks.checksmainataince;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import net.syscon.s4.common.EliteController;
import net.syscon.s4.im.beans.BankChequeBooks;
import net.syscon.s4.im.beans.BankChequeBooksCommitBean;
import net.syscon.s4.im.beans.CaseloadCurrentAccounts;

/**
 * Class OtmcnserController
 */
@EliteController
public class OtmcnserController {

	@Autowired
	private OtmcnserService otmcnserService;

	/**
	 * Fetching the record from database table method csldCaExecuteQuery
	 * 
	 * @Param searchRecord return searchResult
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otmcnser/csldCaExecuteQuery", method = RequestMethod.POST)
	public List<CaseloadCurrentAccounts> csldCaExecuteQuery(@RequestBody CaseloadCurrentAccounts searchBean) {
		List<CaseloadCurrentAccounts> searchResult = new ArrayList<>();
		try {
			if (searchBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				searchBean.setCreateUserId(userName);
			}
			searchResult = otmcnserService.csldCaExecuteQuery(searchBean);
		} catch (Exception e) {
			CaseloadCurrentAccounts bean = new CaseloadCurrentAccounts();
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}

	/**
	 * Fetching the record from database table method bankCbExecuteQuery
	 * 
	 * @Param searchBean return searchResult
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otmcnser/bankCbExecuteQuery", method = RequestMethod.POST)
	public List<BankChequeBooks> bankCbExecuteQuery(@RequestBody BankChequeBooks searchBean) {
		List<BankChequeBooks> searchResult = new ArrayList<>();
		try {
			searchResult = otmcnserService.bankCbExecuteQuery(searchBean);
		} catch (Exception e) {
			BankChequeBooks bean = new BankChequeBooks();
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}

	/**
	 * Perfomring basic Oracle form functions i.e. insert,delete, update int the
	 * database table method bankCbCommit return Integer
	 * 
	 * @Param commitBean
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/otmcnser/bankCbCommit", method = RequestMethod.POST)
	public Integer bankCbCommit(@RequestBody BankChequeBooksCommitBean commitBean) {
		int liReturn = 0;
		try {
			if (commitBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				commitBean.setCreateUserId(userName);
			}
		liReturn = otmcnserService.bankCbCommit(commitBean);
		} catch(Exception e) {
			if (e.getMessage().equals("SERIES ALREADY EXIST")) {
				liReturn = 3;
			}
			return liReturn;
		}
		return liReturn;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otmcnser/checkChecqueBooks", method = RequestMethod.GET)
	public String checkChecqueBooks(@RequestParam(value = "firstCheckNum") final String firstCheckNum,
			@RequestParam(value = "accountCode") final Long accountCode,
			@RequestParam(value = "caseloadId") final String caseloadId) {
		String searchResult = null;
		Long firstChecknumLong = Long.valueOf(firstCheckNum);
		try {
			searchResult = otmcnserService.checkChecqueBooks(firstChecknumLong, accountCode, caseloadId);
		} catch (Exception e) {

		}
		return searchResult;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otmcnser/checkChecqueBooksLastCheck", method = RequestMethod.GET)
	public String checkChecqueBooksLastCheck(@RequestParam(value = "lastCheckNum") final String lastCheckNum,
			@RequestParam(value = "firstCheckNum") final String firstCheckNum,
			@RequestParam(value = "accountCode") final Long accountCode,
			@RequestParam(value = "caseloadId") final String caseloadId) {
		String searchResult = null;
		Long lastCheckNumLong = Long.valueOf(lastCheckNum);
		Long firstChecknumLong = Long.valueOf(firstCheckNum);
		try {
			searchResult = otmcnserService.checkChecqueBooksLastCheck(lastCheckNumLong, firstChecknumLong, accountCode,
					caseloadId);
		} catch (Exception e) {

		}
		return searchResult;
	}

}
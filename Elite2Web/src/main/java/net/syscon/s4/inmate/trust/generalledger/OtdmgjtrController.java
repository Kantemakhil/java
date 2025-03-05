package net.syscon.s4.inmate.trust.generalledger;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
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
import net.syscon.s4.im.beans.Corporates;
import net.syscon.s4.inmate.beans.GlTransactions;
import net.syscon.s4.inmate.beans.GlTransactionsCommitBean;
import net.syscon.s4.inst.booking.beans.Persons;

/**
 * Class OtdmgjtrController
 */
@EliteController
public class OtdmgjtrController {
	@Autowired
	private OtdmgjtrService otdmgjtrService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OtdmgjtrController.class.getName());

	/**
	 * getting cgfkGlTxnPayeeCorporateId LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otdmgjtr/cgfkGlTxnPayeeCorporateIdRecordGroup", method = RequestMethod.GET)
	public List<Corporates> cgfkGlTxnPayeeCorporateIdRecordGroup() {
		List<Corporates> recordList = new ArrayList<Corporates>();
		try {
			recordList = otdmgjtrService.cgfkGlTxnPayeeCorporateIdRecordGroup();
		} catch (Exception e) {
		}
		return recordList;
	}

	/**
	 * getting cgfkGlTxn1AccountCode LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value ="/otdmgjtr/cgfkGlTxn1AccountCodeRecordGroup", method = RequestMethod.GET)
	public List<AccountCodes> cgfkGlTxn1AccountCodeRecordGroup(@RequestParam(value = "caseloadType") final String caseloadType,
			@RequestParam(value= "caseloadId") final String caseloadId) {
		List<AccountCodes> recordList = new ArrayList<AccountCodes>();
		try {
			recordList = otdmgjtrService.cgfkGlTxn1AccountCodeRecordGroup(caseloadType,caseloadId);
		} catch (Exception e) {
			logger.error(e);
		}
		return recordList;
	}

	/**
	 * getting cgfkGlTxnAccountCode LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otdmgjtr/cgfkGlTxnAccountCodeRecordGroup", method = RequestMethod.GET)
	public List<AccountCodes> cgfkGlTxnAccountCodeRecordGroup() {
		List<AccountCodes> recordList = new ArrayList<AccountCodes>();
		try {
			recordList = otdmgjtrService.cgfkGlTxnAccountCodeRecordGroup();
		} catch (Exception e) {
			AccountCodes obj = new AccountCodes();
			logger.error(e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting cgfkGlTxnPayeePersonId LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otdmgjtr/cgfkGlTxnPayeePersonIdRecordGroup", method = RequestMethod.GET)
	public List<Persons> cgfkGlTxnPayeePersonIdRecordGroup() {
		List<Persons> recordList = new ArrayList<Persons>();
		try {
			recordList = otdmgjtrService.cgfkGlTxnPayeePersonIdRecordGroup();
		} catch (Exception e) {
			Persons obj = new Persons();
			logger.error(e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otdmgjtr/glTxnExecuteQuery", method = RequestMethod.POST)
	public List<GlTransactions> glTxnExecuteQuery(@RequestBody final GlTransactions searchBean) {
		List<GlTransactions> searchResult = new ArrayList<>();
		try {
			searchResult = otdmgjtrService.glTxnExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error(e);
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
	@RequestMapping(value = "/otdmgjtr/glTxnCommit", method = RequestMethod.POST)
	public @ResponseBody Integer glTxnCommit(@RequestBody final GlTransactionsCommitBean commitBean) {
		int liReturn = 0;
		try {
			if (commitBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				commitBean.setCreateUserId(userName);
			}
			liReturn = otdmgjtrService.glTxnCommit(commitBean);
		} catch (Exception e) {

			logger.error(e);
		}
		return liReturn;
	}
	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otdmgjtr/getDescandType", method = RequestMethod.GET)
	public List<AccountCodes> getDescandType(@RequestParam(value="code") final String code,
			@RequestParam(value="caseloadType") final String caseloadType) {
		List<AccountCodes> searchResult = new ArrayList<>();
		try {
			searchResult = otdmgjtrService.getDescandType(code,caseloadType);
		} catch (Exception e) {
			logger.error(e);
		}
		return searchResult;
	}
	
	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otdmgjtr/lvlLastclosedPeriod", method = RequestMethod.GET)
	public Integer lvlLastclosedPeriod(@RequestParam(value="caseloadId") final String caseloadId) {
		Integer  searchResult = 0;
		try {
			searchResult = otdmgjtrService.lvlLastclosedPeriod(caseloadId);
		} catch (Exception e) {
			logger.error(e);
		}
		return searchResult;
	}
	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otdmgjtr/lvAllowedReopenPeriod", method = RequestMethod.GET)
	public Integer lvAllowedReopenPeriod(@RequestParam(value="caseloadId") final String caseloadId) {
		Integer  searchResult = 0;
		try {
			searchResult = otdmgjtrService.lvAllowedReopenPeriod(caseloadId);
		} catch (Exception e) {
			logger.error(e);
		}
		return searchResult;
	}
	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otdmgjtr/lvEnteraccountPeriodId", method = RequestMethod.GET)
	public Integer lvEnteraccountPeriod(@RequestParam(value="txnEntryDate") final Long txnEntryDate) {
		Integer  searchResult = 0;
		try {
			searchResult = otdmgjtrService.lvEnteraccountPeriod(txnEntryDate);
		} catch (Exception e) {
			logger.error(e);
		}
		return searchResult;
	}
	
	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otdmgjtr/isPeriodValid", method = RequestMethod.GET)
	public Integer isPeriodValid(@RequestParam(value="caseloadId") final String caseloadId,
			@RequestParam(value="lventerAccountPeriod")final Integer lventerAccountPeriod) {
		Integer  searchResult = 0;
		try {
			searchResult = otdmgjtrService.isPeriodValid(caseloadId,lventerAccountPeriod);
		} catch (Exception e) {
			logger.error(e);
		}
		return searchResult;
	}
	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otdmgjtr/lvAccountStatus", method = RequestMethod.GET)
	public String lvAccountStatus(@RequestParam(value="lventerAccountPeriod")final Integer lventerAccountPeriod,
			@RequestParam(value="caseloadId") final String caseloadId
			) {
		String  searchResult = null;
		try {
			searchResult = otdmgjtrService.lvAccountStatus(lventerAccountPeriod,caseloadId);
		} catch (Exception e) {
			logger.error(e);
		}
		return searchResult;
	}
	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otdmgjtr/getPeriodStartDate", method = RequestMethod.GET)
	public Date lvAccountStatus(@RequestParam(value="lventerAccountPeriod")final Integer lventerAccountPeriod) {
		Date  searchResult = null;
		try {
			searchResult = otdmgjtrService.getPeriodStartDate(lventerAccountPeriod);
		} catch (Exception e) {
			logger.error(e);
		}
		return searchResult;
	}
	
	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otdmgjtr/getperiodEndDate", method = RequestMethod.GET)
	public Date getperiodEndDate(@RequestParam(value="lvlastClosedPeriod") final Integer lvlastClosedPeriod) {
		Date  searchResult = null;
		try {
			searchResult = otdmgjtrService.getperiodEndDate(lvlastClosedPeriod);
		} catch (Exception e) {
			logger.error(e);
		}
		return searchResult;
	}
	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otdmgjtr/isAccountchecking", method = RequestMethod.GET)
	public Integer isAccountchecking(@RequestParam(value="caseloadId") final String caseloadId,
			@RequestParam(value="accountCode")final Integer accountCode) {
		Integer  searchResult = 0;
		try {
			searchResult = otdmgjtrService.isAccountchecking(caseloadId,accountCode);
		} catch (Exception e) {
			logger.error(e);
		}
		return searchResult;
	}
	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otdmgjtr/cStatus", method = RequestMethod.POST)
	public AccountCodes cStatus(@RequestBody final AccountCodes accountCodes) {
		AccountCodes  searchResult =new AccountCodes();
		try {
			searchResult = otdmgjtrService.cStatus(accountCodes);
		} catch (Exception e) {
			logger.error(e);
		}
		return searchResult;
	}
	
	/**
	 * Fetching the record from database table
	 * 
	 * @Param caseloadId,accountCode
	 */
	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otdmgjtr/getCurrentBalance", method = RequestMethod.GET)
	public BigDecimal getCurrentBalance(@RequestParam(value="caseloadId") final String caseloadId,
			@RequestParam(value="accountCode") final Integer accountCode) {
		BigDecimal  searchResult = BigDecimal.valueOf(0);
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		try {
			searchResult = otdmgjtrService.getCurrentBalance(caseloadId,accountCode,userName);
		} catch (Exception e) {
			logger.error(e);
		}
		return searchResult;
	}
}
package net.syscon.s4.inmate.trust.deductions.deductionsmaintenance;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import net.syscon.s4.common.beans.TransactionTypes;
import net.syscon.s4.im.beans.AccountCodes;
import net.syscon.s4.im.beans.CaseloadDeductionDetails;
import net.syscon.s4.im.beans.CaseloadDeductionDetailsCommitBean;
import net.syscon.s4.im.beans.Corporates;
import net.syscon.s4.inmate.beans.CaseloadDeductionProfiles;
import net.syscon.s4.inmate.beans.CaseloadDeductionProfilesCommitBean;
import net.syscon.s4.inmate.beans.DeductionTypes;
import net.syscon.s4.inst.booking.beans.Persons;

@EliteController
public class OtmcoproController {
	@Autowired
	private OtmcoproService otmcoproService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OtmcoproController.class.getName());

	/**
	 * getting cgfkCsldDpPayeeCorporateI LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otmcopro/cgfkCsldDpPayeeCorporateIRecordGroup", method = RequestMethod.GET)
	public List<Corporates> cgfkCsldDpPayeeCorporateIRecordGroup() {
		List<Corporates> recordList = new ArrayList<Corporates>();
		try {
			recordList = otmcoproService.cgfkCsldDpPayeeCorporateIRecordGroup();
		} catch (Exception e) {
			logger.error("cgfkCsldDpPayeeCorporateIRecordGroup", e);
		}
		return recordList;
	}

	/**
	 * getting cgfkCsldDdReceiptTxnType LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otmcopro/cgfkCsldDdReceiptTxnTypeRecordGroup", method = RequestMethod.GET)
	public List<TransactionTypes> cgfkCsldDdReceiptTxnTypeRecordGroup(
			@RequestParam(value = "caseloadType") final String caseloadType) {
		List<TransactionTypes> recordList = new ArrayList<TransactionTypes>();
		try {
			recordList = otmcoproService.cgfkCsldDdReceiptTxnTypeRecordGroup(caseloadType);
		} catch (Exception e) {
			logger.error("cgfkCsldDdReceiptTxnTypeRecordGroup", e);
		}
		return recordList;
	}

	/**
	 * getting cgfkCsldDpDeductionType LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otmcopro/cgfkCsldDpDeductionTypeRecordGroup", method = RequestMethod.GET)
	public List<DeductionTypes> cgfkCsldDpDeductionTypeRecordGroup(
			@RequestParam(value = "caseloadType") final String caseloadType) {
		List<DeductionTypes> recordList = new ArrayList<DeductionTypes>();
		try {
			recordList = otmcoproService.cgfkCsldDpDeductionTypeRecordGroup(caseloadType);
		} catch (Exception e) {
			logger.error("cgfkCsldDpDeductionTypeRecordGroup", e);
		}
		return recordList;
	}

	/**
	 * getting cgfkCsldDpAccountCode LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otmcopro/cgfkCsldDpAccountCodeRecordGroup", method = RequestMethod.GET)
	public List<AccountCodes> cgfkCsldDpAccountCodeRecordGroup(
			@RequestParam(value = "caseloadType") final String caseloadType) {
		List<AccountCodes> recordList = new ArrayList<AccountCodes>();
		try {
			recordList = otmcoproService.cgfkCsldDpAccountCodeRecordGroup(caseloadType);
		} catch (Exception e) {
			logger.error("cgfkCsldDpAccountCodeRecordGroup", e);
		}
		return recordList;
	}

	/**
	 * getting cgfkCsldDpPayeePersonId LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otmcopro/cgfkCsldDpPayeePersonIdRecordGroup", method = RequestMethod.GET)
	public List<Persons> cgfkCsldDpPayeePersonIdRecordGroup() {
		List<Persons> recordList = new ArrayList<Persons>();
		try {
			recordList = otmcoproService.cgfkCsldDpPayeePersonIdRecordGroup();
		} catch (Exception e) {
			logger.error("cgfkCsldDpPayeePersonIdRecordGroup", e);
		}
		return recordList;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otmcopro/csldDpExecuteQuery", method = RequestMethod.POST)
	public List<CaseloadDeductionProfiles> csldDpExecuteQuery(@RequestBody final CaseloadDeductionProfiles searchBean) {
		List<CaseloadDeductionProfiles> searchResult = new ArrayList<>();
		try {
			searchResult = otmcoproService.csldDpExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error("csldDpExecuteQuery", e);
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
	@RequestMapping(value = "/otmcopro/csldDpCommit", method = RequestMethod.POST)
	public @ResponseBody String csldDpCommit(@RequestBody final CaseloadDeductionProfilesCommitBean commitBean) {
		String liReturn = "0";
		try {
			if (commitBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				commitBean.setCreateUserId(userName);
			}
			liReturn = otmcoproService.csldDpCommit(commitBean);
		} catch (Exception e) {
			liReturn = e.getMessage();
			logger.error("csldDpCommit", e);
		}
		return liReturn;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otmcopro/csldDdExecuteQuery", method = RequestMethod.POST)
	public List<CaseloadDeductionDetails> csldDdExecuteQuery(@RequestBody final CaseloadDeductionDetails searchBean) {
		List<CaseloadDeductionDetails> searchResult = new ArrayList<>();
		try {
			searchResult = otmcoproService.csldDdExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error("csldDdCommit", e);
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
	@RequestMapping(value = "/otmcopro/csldDdCommit", method = RequestMethod.POST)
	public String csldDdCommit(@RequestBody final CaseloadDeductionDetailsCommitBean commitBean) {
		String liReturn = "0";
		try {
			if (commitBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				commitBean.setCreateUserId(userName);
			}
			liReturn = otmcoproService.csldDdCommit(commitBean);
		} catch (Exception e) {
			liReturn = e.getMessage();
			logger.error("csldDdCommit", e);
		}
		return liReturn;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otmcopro/preCommit", method = RequestMethod.GET)
	public Map<String, Object> preCommit(@RequestParam(value = "caseloadId") final String caseloadId,
			@RequestParam(value = "deductionType") final String deductionType) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			result = otmcoproService.preCommit(caseloadId, deductionType);
		} catch (Exception e) {
			result.put("per", 0);
			result.put("extPrioNo", 0);
			logger.error("Exception : OtmcoproController preCommit  unable to call the service", e);
		}
		return result;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otmcopro/chkDuplicate", method = RequestMethod.GET)
	public String chkDuplicate(@RequestParam(value = "caseloadId") final String caseloadId,
			@RequestParam(value = "deductionType") final String deductionType) {
		String result = "N";
		try {
			result = otmcoproService.chkDuplicate(caseloadId, deductionType);
		} catch (Exception e) {

			logger.error("Exception : OtmcoproController chkDuplicate  unable to call the service", e);
		}
		return result;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otmcopro/getCalculateOnVal", method = RequestMethod.GET)
	public String getCalculateOnVal(@RequestParam(value = "deductionType") final String deductionType) {
		String result = "";
		try {
			result = otmcoproService.getCalculateOnVal(deductionType);
		} catch (Exception e) {

			logger.error("Exception : OtmcoproController getCalculateOnVal  unable to call the service", e);
		}
		return result;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otmcopro/cgfkchkCsldDbenCsldDbenC", method = RequestMethod.GET)
	public @ResponseBody Corporates cgfkchkCsldDbenCsldDbenC(
			@RequestParam(value = "corporateId") final BigDecimal corporateId) {
		Corporates result = new Corporates();
		try {
			result = otmcoproService.cgfkchkCsldDbenCsldDben(corporateId);
		} catch (Exception e) {
			result.setErrorMessage("Error : " + e.getMessage());
			logger.error("Exception : OtmcoproController cgfkchkCsldDbenCsldDbenC  unable to call the service", e);
		}
		return result;
	}

}
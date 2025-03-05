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
import net.syscon.s4.common.beans.CaseloadDedBeneficiaries;
import net.syscon.s4.common.beans.CaseloadDedBeneficiariesCommitBean;
import net.syscon.s4.common.beans.TransactionTypes;
import net.syscon.s4.im.beans.AccountCodes;
import net.syscon.s4.im.beans.CaseloadDeductionDetails;
import net.syscon.s4.im.beans.CaseloadDeductionDetailsCommitBean;
import net.syscon.s4.im.beans.Corporates;
import net.syscon.s4.inmate.beans.CaseloadDeductionProfiles;
import net.syscon.s4.inmate.beans.CaseloadDeductionProfilesCommitBean;
import net.syscon.s4.inmate.beans.DeductionTypes;
import net.syscon.s4.inst.booking.beans.Persons;
import net.syscon.s4.inst.casemanagement.beans.CommunityConditions;

/**
 * OtmfoproController
 */
@EliteController
public class OtmfoproController {
	@Autowired
	private OtmfoproService otmfoproService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OtmfoproController.class.getName());

	/**
	 * getting cgfkCsldDdReceiptTxnType LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otmfopro/cgfkCsldDdReceiptTxnTypeRecordGroup", method = RequestMethod.GET)
	public List<TransactionTypes> cgfkCsldDdReceiptTxnTypeRecordGroup(
			@RequestParam(value = "caseloadType") final String caseloadType) {
		List<TransactionTypes> recordList = new ArrayList<>();
		try {
			recordList = otmfoproService.cgfkCsldDdReceiptTxnTypeRecordGroup(caseloadType);
		} catch (Exception e) {
			logger.error(
					"Exception : OtmfoproController cgfkCsldDdReceiptTxnTypeRecordGroup  unable to call the service",
					e);
		}
		return recordList;
	}

	/**
	 * getting rgCondition LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otmfopro/rgConditionRecordGroup", method = RequestMethod.GET)
	public List<CommunityConditions> rgConditionRecordGroup() {
		List<CommunityConditions> recordList = new ArrayList<>();
		try {
			recordList = otmfoproService.rgConditionRecordGroup();
		} catch (Exception e) {
			logger.error("Exception : OtmfoproController rgConditionRecordGroup  unable to call the service", e);
		}
		return recordList;
	}

	/**
	 * getting cgfkCsldDpAccountCode LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otmfopro/cgfkCsldDpAccountCodeRecordGroup", method = RequestMethod.GET)
	public List<AccountCodes> cgfkCsldDpAccountCodeRecordGroup(
			@RequestParam(value = "caseloadType") final String caseloadType) {
		List<AccountCodes> recordList = new ArrayList<AccountCodes>();
		try {
			recordList = otmfoproService.cgfkCsldDpAccountCodeRecordGroup(caseloadType);
		} catch (Exception e) {
			AccountCodes obj = new AccountCodes();
			logger.error("Exception : OtmfoproController  unable to call the service", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgCorp LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otmfopro/rgCorpRecordGroup", method = RequestMethod.GET)
	public List<Corporates> rgCorpRecordGroup() {
		List<Corporates> recordList = new ArrayList<Corporates>();
		try {
			recordList = otmfoproService.rgCorpRecordGroup();
		} catch (Exception e) {
			Corporates obj = new Corporates();
			logger.error("Exception : OtmfoproController cgfkCsldDpAccountCodeRecordGroup  unable to call the service",
					e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting cgfkCsldDpDeductionType LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otmfopro/cgfkCsldDpDeductionTypeRecordGroup", method = RequestMethod.GET)
	public List<DeductionTypes> cgfkCsldDpDeductionTypeRecordGroup(
			@RequestParam(value = "caseloadType") final String caseloadId) {
		List<DeductionTypes> recordList = new ArrayList<DeductionTypes>();
		try {
			recordList = otmfoproService.cgfkCsldDpDeductionTypeRecordGroup(caseloadId);
		} catch (Exception e) {
			DeductionTypes obj = new DeductionTypes();
			logger.error(
					"Exception : OtmfoproController cgfkCsldDpDeductionTypeRecordGroup  unable to call the service", e);
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
	@RequestMapping(value = "/otmfopro/csldDpExecuteQuery", method = RequestMethod.POST)
	public List<CaseloadDeductionProfiles> csldDpExecuteQuery(@RequestBody final CaseloadDeductionProfiles searchBean) {
		List<CaseloadDeductionProfiles> searchResult = new ArrayList<>();
		try {
			searchResult = otmfoproService.csldDpExecuteQuery(searchBean);
		} catch (Exception e) {
			CaseloadDeductionProfiles bean = new CaseloadDeductionProfiles();
			logger.error("Exception : OtmfoproController csldDpExecuteQuery  unable to call the service", e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
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
	@RequestMapping(value = "/otmfopro/csldDpCommit", method = RequestMethod.POST)
	public @ResponseBody Integer csldDpCommit(@RequestBody final CaseloadDeductionProfilesCommitBean commitBean) {
		int liReturn = 0;
		try {
			liReturn = otmfoproService.csldDpCommit(commitBean);
		} catch (Exception e) {

			logger.error("Exception : OtmfoproController csldDpCommit  unable to call the service", e);
		}
		return liReturn;
	}

	/**
	 * Perfomring basic Oracle form functions i.e. insert,delete, update int the
	 * database table
	 * 
	 * @Param commitBean
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/otmfopro/singleCommit", method = RequestMethod.POST)
	public @ResponseBody String singleCommit(@RequestBody final CaseloadDeductionProfilesCommitBean commitBean) {
		String liReturn = "";
		try {
			if (commitBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				commitBean.setCreateUserId(userName);
			}
			liReturn = otmfoproService.singleCommit(commitBean);
		} catch (Exception e) {
			liReturn = e.getMessage();
			logger.error("Exception : OtmfoproController singleCommit  unable to call the service", e);
		}
		return liReturn;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otmfopro/csldDbenExecuteQuery", method = RequestMethod.POST)
	public List<CaseloadDedBeneficiaries> csldDbenExecuteQuery(@RequestBody final CaseloadDedBeneficiaries searchBean) {
		List<CaseloadDedBeneficiaries> searchResult = new ArrayList<>();
		try {
			searchResult = otmfoproService.csldDbenExecuteQuery(searchBean);
		} catch (Exception e) {
			CaseloadDedBeneficiaries bean = new CaseloadDedBeneficiaries();
			logger.error("Exception : OtmfoproController csldDbenExecuteQuery  unable to call the service", e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
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
	@RequestMapping(value = "/otmfopro/csldDbenCommit", method = RequestMethod.POST)
	public @ResponseBody Integer csldDbenCommit(@RequestBody final CaseloadDedBeneficiariesCommitBean commitBean) {
		int liReturn = 0;
		try {
			if (commitBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				commitBean.setCreateUserId(userName);
			}
			liReturn = otmfoproService.csldDbenCommit(commitBean);
		} catch (Exception e) {

			logger.error("Exception : OtmfoproController csldDbenCommit  unable to call the service", e);
		}
		return liReturn;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otmfopro/csldDdExecuteQuery", method = RequestMethod.POST)
	public List<CaseloadDeductionDetails> csldDdExecuteQuery(@RequestBody final CaseloadDeductionDetails searchBean) {
		List<CaseloadDeductionDetails> searchResult = new ArrayList<>();
		try {
			searchResult = otmfoproService.csldDdExecuteQuery(searchBean);
		} catch (Exception e) {
			CaseloadDeductionDetails bean = new CaseloadDeductionDetails();
			logger.error("Exception : OtmfoproController csldDdExecuteQuery  unable to call the service", e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
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
	@RequestMapping(value = "/otmfopro/csldDdCommit", method = RequestMethod.POST)
	public @ResponseBody String csldDdCommit(@RequestBody final CaseloadDeductionDetailsCommitBean commitBean) {
		String liReturn = null;
		try {
			if (commitBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				commitBean.setCreateUserId(userName);
			}
			liReturn = otmfoproService.csldDdCommit(commitBean);
		} catch (Exception e) {

			logger.error("Exception : OtmfoproController csldDdCommit  unable to call the service", e);
		}
		return liReturn;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otmfopro/cgfkchkCsldDbenCsldDben", method = RequestMethod.GET)
	public @ResponseBody Persons cgfkchkCsldDbenCsldDben(@RequestParam(value = "personId") final Long personId) {
		Persons result = new Persons();
		try {
			result = otmfoproService.CgfkchkCsldDbenCsldDben(personId);
		} catch (Exception e) {
			result.setErrorMessage("Error : " + e.getMessage());
			logger.error("Exception : OtmfoproController cgfkchkCsldDbenCsldDben  unable to call the service", e);
		}
		return result;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otmfopro/cgfkchkCsldDbenCsldDbenC", method = RequestMethod.GET)
	public @ResponseBody Corporates cgfkchkCsldDbenCsldDbenC(
			@RequestParam(value = "corporateId") final BigDecimal corporateId) {
		Corporates result = new Corporates();
		try {
			result = otmfoproService.CgfkchkCsldDbenCsldDben(corporateId);
		} catch (Exception e) {
			result.setErrorMessage("Error : " + e.getMessage());
			logger.error("Exception : OtmfoproController cgfkchkCsldDbenCsldDbenC  unable to call the service", e);
		}
		return result;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otmfopro/calculateOn", method = RequestMethod.GET)
	public @ResponseBody String calculateOn(@RequestParam(value = "deductionType") final String deductionType) {
		String result = "";
		try {
			result = otmfoproService.calculateOn(deductionType);
		} catch (Exception e) {
			logger.error("Exception : OtmfoproController calculateOn  unable to call the service", e);
		}
		return result;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otmfopro/countMinBalLogic", method = RequestMethod.GET)
	public @ResponseBody BigDecimal countMinBalLogic(@RequestParam(value = "caseloadId") final String caseloadId,
			@RequestParam(value = "deductionType") final String deductionType) {
		BigDecimal result = BigDecimal.ZERO;
		try {
			result = otmfoproService.countMinBalLogic(caseloadId, deductionType);
		} catch (Exception e) {
			logger.error(
					"Exception : OtmfoproController countMinBalLogic Other error in checking minimum trust balance logic unable to call the service",
					e);
		}
		return result;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otmfopro/preCommit", method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> preCommit(@RequestParam(value = "caseloadId") final String caseloadId,
			@RequestParam(value = "deductionType") final String deductionType) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			result = otmfoproService.preCommit(caseloadId, deductionType);
		} catch (Exception e) {
			result.put("per", 0);
			result.put("extPrioNo", 0);
			logger.error("Exception : OtmfoproController preCommit  unable to call the service", e);
		}
		return result;
	}

}
package net.syscon.s4.cf.deductions.maintenance;

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

import net.syscon.s4.cf.deductions.maintenance.beans.TieredTransactionFeeAmounts;
import net.syscon.s4.cf.deductions.maintenance.beans.TieredTransactionFeeAmountsCommitBean;
import net.syscon.s4.cf.deductions.maintenance.beans.TransactionFeeDetails;
import net.syscon.s4.cf.deductions.maintenance.beans.TransactionFeeDetailsCommitBean;
import net.syscon.s4.common.EliteController;
import net.syscon.s4.im.beans.AccountCodes;
import net.syscon.s4.im.beans.Corporates;
import net.syscon.s4.inmate.beans.CaseloadDeductionProfiles;
import net.syscon.s4.inmate.beans.CaseloadDeductionProfilesCommitBean;
import net.syscon.s4.inmate.beans.DeductionTypes;

/**
 * OtmcfeesController
 *
 */
@EliteController
public class OtmcfeesController {
	@Autowired
	private OtmcfeesService otmcfeesService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OtmcfeesController.class.getName());

	/**
	 * getting cgfkCsldDpPayeeCorporateI LOV values
	 * @return a list of the Corporates {@link Corporates} from the DB.
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otmcfees/cgfkCsldDpPayeeCorporateIRecordGroup", method = RequestMethod.GET)
	public List<Corporates> cgfkCsldDpPayeeCorporateIRecordGroup() {
		List<Corporates> recordList = new ArrayList<Corporates>();
		try {
			recordList = otmcfeesService.cgfkCsldDpPayeeCorporateIRecordGroup();
		} catch (Exception e) {
			final Corporates obj = new Corporates();
			logger.error("Exception : cgfkCsldDpPayeeCorporateIRecordGroup:", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting cgfkTranFdReceiptDeduction LOV values
	 * @param caseLoadType {@link String}
	 * @return a list of the DeductionTypes {@link DeductionTypes} from the DB.
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otmcfees/cgfkTranFdReceiptDeductionRecordGroup", method = RequestMethod.GET)
	public List<DeductionTypes> cgfkTranFdReceiptDeductionRecordGroup(
			@RequestParam("caseLoadType") final String caseLoadType) {
		List<DeductionTypes> recordList = new ArrayList<DeductionTypes>();
		try {
			recordList = otmcfeesService.cgfkTranFdReceiptDeductionRecordGroup(caseLoadType);
		} catch (Exception e) {
			final DeductionTypes obj = new DeductionTypes();
			logger.error("Exception : cgfkTranFdReceiptDeductionRecordGroup:", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting cgfkCsldDpDeductionType LOV values
	 * @param caseLoadType {@link String}
	 * @return a list of the DeductionTypes {@link DeductionTypes} for the matched caseloadType
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otmcfees/cgfkCsldDpDeductionTypeRecordGroup", method = RequestMethod.GET)
	public List<DeductionTypes> cgfkCsldDpDeductionTypeRecordGroup(
			@RequestParam("caseLoadType") final String caseLoadType) {
		List<DeductionTypes> recordList = new ArrayList<DeductionTypes>();
		try {
			recordList = otmcfeesService.cgfkCsldDpDeductionTypeRecordGroup(caseLoadType);
		} catch (Exception e) {
			final DeductionTypes obj = new DeductionTypes();
			logger.error("Exception : cgfkCsldDpDeductionTypeRecordGroup:", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting cgfkCsldDpAccountCode LOV values
	 * @param caseLoadType {@link String}
	 * @return a list of the AccountCodes {@link AccountCodes} for the matched caseloadtype
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otmcfees/cgfkCsldDpAccountCodeRecordGroup", method = RequestMethod.GET)
	public List<AccountCodes> cgfkCsldDpAccountCodeRecordGroup(
			@RequestParam("caseLoadType") final String caseLoadType) {
		List<AccountCodes> recordList = new ArrayList<AccountCodes>();
		try {
			recordList = otmcfeesService.cgfkCsldDpAccountCodeRecordGroup(caseLoadType);
		} catch (Exception e) {
			final AccountCodes obj = new AccountCodes();
			logger.error("Exception : cgfkCsldDpAccountCodeRecordGroup:", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * Fetching the record from database table
	 *
	 * @param searchBean {@link CaseloadDeductionProfiles}
	 * @return a list of the CaseloadDeductionProfiles {@link CaseloadDeductionProfiles} for the matched CaseloadDeductionProfiles
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otmcfees/csldDpExecuteQuery", method = RequestMethod.POST)
	public List<CaseloadDeductionProfiles> csldDpExecuteQuery(@RequestBody final CaseloadDeductionProfiles searchBean) {
		List<CaseloadDeductionProfiles> searchResult = new ArrayList<>();
		try {
			searchResult = otmcfeesService.csldDpExecuteQuery(searchBean);
		} catch (Exception e) {
			final CaseloadDeductionProfiles bean = new CaseloadDeductionProfiles();
			logger.error("Exception :csldDpExecuteQuery", e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}

	/**
	 * Performsing basic Oracle form functions i.e. insert,delete, update int
	 * the database table
	 *
	 * @param commitBean {@link CaseloadDeductionProfilesCommitBean}
	 * @return a list of the CaseloadDeductionProfiles {@link CaseloadDeductionProfiles} for the matched CaseloadDeductionProfilesCommitBean
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/otmcfees/csldDpCommit", method = RequestMethod.POST)
	public @ResponseBody List<CaseloadDeductionProfiles> csldDpCommit(
			@RequestBody final CaseloadDeductionProfilesCommitBean commitBean) {
		List<CaseloadDeductionProfiles> liReturn = new ArrayList<>();
		try {
			if (commitBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				commitBean.setCreateUserId(userName);
			}
			liReturn = otmcfeesService.csldDpCommit(commitBean);
		} catch (Exception e) {
			final CaseloadDeductionProfiles bean = new CaseloadDeductionProfiles();
			logger.error("Exception : csldDpCommit", e);
			bean.setErrorMessage(e.getMessage().toUpperCase());
			liReturn.add(bean);
		}
		return liReturn;
	}

	/**
	 * Fetching the record from database table
	 *
	 * @param searchBean {@link TransactionFeeDetails}
	 * @return a list of the TransactionFeeDetails {@link TransactionFeeDetails} for the matched TransactionFeeDetails
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otmcfees/tranFdExecuteQuery", method = RequestMethod.POST)
	public List<TransactionFeeDetails> tranFdExecuteQuery(@RequestBody final TransactionFeeDetails searchBean) {
		List<TransactionFeeDetails> searchResult = new ArrayList<>();
		try {
			searchResult = otmcfeesService.tranFdExecuteQuery(searchBean);
		} catch (Exception e) {
			final TransactionFeeDetails bean = new TransactionFeeDetails();
			logger.error("Exception : tranFdExecuteQuery", e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}

	/**
	 * Perfomring basic Oracle form functions i.e. insert,delete, update int the
	 * database table
	 *
	 * @param commitBean {@link TransactionFeeDetails}
	 * @return a list of the InternalScheduleReasons {@link TransactionFeeDetails} for the matched TransactionFeeDetails
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/otmcfees/tranFdCommit", method = RequestMethod.POST)
	public @ResponseBody List<TransactionFeeDetails> tranFdCommit(
			@RequestBody final TransactionFeeDetailsCommitBean commitBean) {
		List<TransactionFeeDetails> liReturn = new ArrayList<>();
		try {
			if (commitBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				commitBean.setCreateUserId(userName);
			}
			liReturn = otmcfeesService.tranFdCommit(commitBean);
		} catch (Exception e) {
			final TransactionFeeDetails bean = new TransactionFeeDetails();
			logger.error("Exception : tranFdCommit", e);
			bean.setErrorMessage(e.getMessage().toUpperCase());
			liReturn.add(bean);
		}
		return liReturn;
	}

	/**
	 * Fetching the record from database table
	 *
	 * @param searchBean {@link TieredTransactionFeeAmounts}
	 * @return a list of the TieredTransactionFeeAmounts {@link TieredTransactionFeeAmounts} for the matched TieredTransactionFeeAmounts
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otmcfees/tierTfaExecuteQuery", method = RequestMethod.POST)
	public List<TieredTransactionFeeAmounts> tierTfaExecuteQuery(
			@RequestBody final TieredTransactionFeeAmounts searchBean) {
		List<TieredTransactionFeeAmounts> searchResult = new ArrayList<>();
		try {
			searchResult = otmcfeesService.tierTfaExecuteQuery(searchBean);
		} catch (Exception e) {
			final TieredTransactionFeeAmounts bean = new TieredTransactionFeeAmounts();
			logger.error("Exception : tierTfaExecuteQuery", e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}

	/**
	 * Perfomring basic Oracle form functions i.e. insert,delete, update int the
	 * database table
	 *
	 * @param commitBean {@link TieredTransactionFeeAmounts}
	 * @return a list of the TieredTransactionFeeAmounts {@link TieredTransactionFeeAmounts} for the matched TieredTransactionFeeAmounts
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/otmcfees/tierTfaCommit", method = RequestMethod.POST)
	public @ResponseBody List<TieredTransactionFeeAmounts> tierTfaCommit(
			@RequestBody final TieredTransactionFeeAmountsCommitBean commitBean) {
		List<TieredTransactionFeeAmounts> liReturn = new ArrayList<>();
		try {
			if (commitBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				commitBean.setCreateUserId(userName);
			}
			liReturn = otmcfeesService.tierTfaCommit(commitBean);
		} catch (Exception e) {
			final TieredTransactionFeeAmounts bean = new TieredTransactionFeeAmounts();
			logger.error("Exception : tierTfaCommit", e);
			bean.setErrorMessage(e.getMessage().toUpperCase());
			liReturn.add(bean);
		}
		return liReturn;
	}
	/**
	 *This method is used to get the overlap count to validate in validate row
	 * @param paramBean {@link TieredTransactionFeeAmounts}
	 * @return a list of the TieredTransactionFeeAmounts {@link TieredTransactionFeeAmounts} for the matched TieredTransactionFeeAmounts
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/otmcfees/getOverLapCount", method = RequestMethod.POST)
	public Integer getOverLapCount(@RequestBody final TieredTransactionFeeAmounts paramBean) {
		Integer liReturn = 0;
		try {
			liReturn = otmcfeesService.getOverLapCount(paramBean);
		} catch (Exception e) {

			logger.error("Exception :", e);
		}
		return liReturn;
	}
	/**
	 *This method is used to get the corporate name based on corporateid
	 * @param paramBean {@link CaseloadDeductionProfiles}
	 * @return the CaseloadDeductionProfiles {@link CaseloadDeductionProfiles} for the matched CaseloadDeductionProfiles
	 */
	
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/otmcfees/getCorporateName", method = RequestMethod.POST)
	public CaseloadDeductionProfiles getCorporateName(@RequestBody  CaseloadDeductionProfiles paramBean) {
		try {
			paramBean = otmcfeesService.getCorporateName(paramBean);
		} catch (Exception e) {

			logger.error("Exception :", e);
		}
		return paramBean;
	}

}
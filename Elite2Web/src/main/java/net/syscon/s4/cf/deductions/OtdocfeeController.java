package net.syscon.s4.cf.deductions;

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

import net.syscon.s4.cf.deductions.maintenance.beans.OffenderTierTxnFeeAmounts;
import net.syscon.s4.cf.deductions.maintenance.beans.OffenderTierTxnFeeAmountsCommitBean;
import net.syscon.s4.cf.deductions.maintenance.beans.OffenderTxnFeeDetails;
import net.syscon.s4.cf.deductions.maintenance.beans.OffenderTxnFeeDetailsCommitBean;
import net.syscon.s4.common.EliteController;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.inmate.beans.DeductionTypes;
import net.syscon.s4.inmate.beans.OffenderDeductions;
import net.syscon.s4.inmate.beans.OffenderDeductionsCommitBean;

/**
 * class OtdocfeeController
 * 
 */
@EliteController
public class OtdocfeeController {
	@Autowired
	private OtdocfeeService otdocfeeService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OtdocfeeController.class.getName());

	/**
	 * getting cgfkOffDedDspDescription LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otdocfee/cgfkOffDedDspDescriptionRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> cgfkOffDedDspDescriptionRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<>();
		try {
			recordList = otdocfeeService.cgfkOffDedDspDescriptionRecordGroup();
		} catch (final Exception e) {
			final ReferenceCodes obj = new ReferenceCodes();
			logger.error(this.getClass().getName()+"Error in method cgfkOffDedDspDescriptionRecordGroup: ", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting cgfkOffTfdReceiptDeduction LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otdocfee/cgfkOffTfdReceiptDeductionRecordGroup", method = RequestMethod.GET)
	public List<DeductionTypes> cgfkOffTfdReceiptDeductionRecordGroup(
			@RequestParam(value = "caseloadType") final String caseloadType) {
		List<DeductionTypes> recordList = new ArrayList<>();
		try {
			recordList = otdocfeeService.cgfkOffTfdReceiptDeductionRecordGroup(caseloadType);
		} catch (final Exception e) {
			final DeductionTypes obj = new DeductionTypes();
			logger.error(this.getClass().getName()+"Error in method cgfkOffTfdReceiptDeductionRecordGroup", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting cgfkOffDedDeductionType LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otdocfee/cgfkOffDedDeductionTypeRecordGroup", method = RequestMethod.GET)
	public List<DeductionTypes> cgfkOffDedDeductionTypeRecordGroup(
			@RequestParam(value = "caseloadId") final String caseloadId,
			@RequestParam(value = "caseloadType") final String caseloadType) {
		List<DeductionTypes> recordList = new ArrayList<>();
		try {
			recordList = otdocfeeService.cgfkOffDedDeductionTypeRecordGroup(caseloadId, caseloadType);
		} catch (final Exception e) {
			final DeductionTypes obj = new DeductionTypes();
			logger.error(this.getClass().getName()+"Error in method cgfkOffDedDeductionTypeRecordGroup", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @param searchBean {@link OffenderDeductions}
	 * @return a list of the OffenderDeductions {@link OffenderDeductions} for the matching passed data
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otdocfee/offDedExecuteQuery", method = RequestMethod.POST)
	public List<OffenderDeductions> offDedExecuteQuery(@RequestBody final OffenderDeductions searchBean) {
		List<OffenderDeductions> searchResult = new ArrayList<>();
		try {
			searchResult = otdocfeeService.offDedExecuteQuery(searchBean);
		} catch (final Exception e) {
			final OffenderDeductions bean = new OffenderDeductions();
			logger.error(this.getClass().getName()+"Error in method offDedExecuteQuery: ", e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}

	/**
	 * Perfomring basic Oracle form functions i.e. insert,delete, update int the
	 * database table
	 * 
	 * @param commitBean {@link OffenderDeductionsCommitBean}
	 * @return a list of the OffenderTxnFeeDetails {@link OffenderDeductionsCommitBean} for the matching passed data
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/otdocfee/offDedCommit", method = RequestMethod.POST)
	public @ResponseBody Integer offDedCommit(@RequestBody final OffenderDeductionsCommitBean commitBean) {
		int liReturn = 0;
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		commitBean.setCreateUserId(userName);
		try {
			liReturn = otdocfeeService.offDedCommit(commitBean);
		} catch (final Exception e) {

			logger.error(this.getClass().getName()+"Error in method offDedCommit : ", e);
		}
		return liReturn;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @param searchBean {@link OffenderTxnFeeDetails}
	 * @return a list of the OffenderTxnFeeDetails {@link OffenderTxnFeeDetails} for the matching passed data
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otdocfee/offTfdExecuteQuery", method = RequestMethod.POST)
	public List<OffenderTxnFeeDetails> offTfdExecuteQuery(@RequestBody final OffenderTxnFeeDetails searchBean) {
		List<OffenderTxnFeeDetails> searchResult = new ArrayList<>();
		try {
			searchResult = otdocfeeService.offTfdExecuteQuery(searchBean);
		} catch (final Exception e) {
			final OffenderTxnFeeDetails bean = new OffenderTxnFeeDetails();
			logger.error(this.getClass().getName()+"Error in method offTfdExecuteQuery: ", e);
			searchResult.add(bean);
		}
		return searchResult;
	}

	/**
	 * Perfomring basic Oracle form functions i.e. insert,delete, update int the
	 * database table
	 * 
	 * @param commitBean {@link OffenderTxnFeeDetailsCommitBean}
	 * @return a list of the OffenderTxnFeeDetailsCommitBean {@link OffenderTxnFeeDetailsCommitBean} for the matching passed data
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/otdocfee/offTfdCommit", method = RequestMethod.POST)
	public @ResponseBody Integer offTfdCommit(@RequestBody final OffenderTxnFeeDetailsCommitBean commitBean) {
		int liReturn = 0;
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		commitBean.setCreateUserId(userName);
		try {
			liReturn = otdocfeeService.offTfdCommit(commitBean);
		} catch (final Exception e) {

			logger.error(this.getClass().getName()+"Error in method offTfdCommit : ", e);
		}
		return liReturn;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @param searchBean {@link OffenderTierTxnFeeAmounts}
	 * @return a list of the OffenderTierTxnFeeAmounts {@link OffenderTierTxnFeeAmounts} for the matching passed data
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otdocfee/offTtfExecuteQuery", method = RequestMethod.POST)
	public List<OffenderTierTxnFeeAmounts> offTtfExecuteQuery(@RequestBody final OffenderTierTxnFeeAmounts searchBean) {
		List<OffenderTierTxnFeeAmounts> searchResult = new ArrayList<>();
		try {
			searchResult = otdocfeeService.offTtfExecuteQuery(searchBean);
		} catch (final Exception e) {
			final OffenderTierTxnFeeAmounts bean = new OffenderTierTxnFeeAmounts();
			logger.error(this.getClass().getName()+"Error in method offTtfExecuteQuery: ", e);
			searchResult.add(bean);
		}
		return searchResult;
	}

	/**
	 * Performing basic Oracle form functions i.e. insert,delete, update int the database table
	 *
	 * @param commitBean {@link OffenderTierTxnFeeAmountsCommitBean}
	 * @return success/failure of the insert/update {@link Integer} for the matching passed data
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/otdocfee/offTtfCommit", method = RequestMethod.POST)
	public @ResponseBody Integer offTtfCommit(@RequestBody final OffenderTierTxnFeeAmountsCommitBean commitBean) {
		int liReturn = 0;
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		commitBean.setCreateUserId(userName);
		try {
			liReturn = otdocfeeService.offTtfCommit(commitBean);
		} catch (final Exception e) {

			logger.error(this.getClass().getName()+"Error in method offTtfCommit: ", e);
		}
		return liReturn;
	}

	/**
	 * Fetching the record from database table
	 *
	 * @param searchBean {@link SystemProfiles}
	 * @return a list of the SystemProfiles {@link SystemProfiles} for the matched SystemProfiles
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otdocfee/sysPflExecuteQuery", method = RequestMethod.POST)
	public List<SystemProfiles> sysPflExecuteQuery(@RequestBody final SystemProfiles searchBean) {
		List<SystemProfiles> searchResult = new ArrayList<>();
		try {
			searchResult = otdocfeeService.sysPflExecuteQuery(searchBean);
		} catch (final Exception e) {
			final SystemProfiles bean = new SystemProfiles();
			logger.error(this.getClass().getName()+"Error in method sysPflExecuteQuery: ", e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otdocfee/otdocfeePopulateDetailsData", method = RequestMethod.POST)
	public List<OffenderDeductions> otdocfeePopulateDetailsData(@RequestBody final OffenderDeductions searchBean) {
		List<OffenderDeductions> searchResult = new ArrayList<>();
		Integer resultInt=0;
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		searchBean.setCreateUserId(userName);
		try {
			resultInt=otdocfeeService.otdocfeePopulateDetailsData(searchBean);
			searchResult = otdocfeeService.offDedExecuteQuery(searchBean);
		} catch (final Exception e) {
			final OffenderDeductions bean = new OffenderDeductions();
			logger.error(this.getClass().getName()+"Error in method otdocfeePopulateDetailsData: ", e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}

	/**
	 *This method is used to get the overlap count to validate in validate row
	 * @param paramBean {@link OffenderTierTxnFeeAmounts}
	 * @return success/failure of the insert/update {@link Integer} for the matching passed data
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/otdocfee/getOverLapCount", method = RequestMethod.POST)
	public Integer getOverLapCount(@RequestBody final OffenderTierTxnFeeAmounts paramBean) {
		Integer liReturn = 0;
		try {
			liReturn = otdocfeeService.getOverLapCount(paramBean);
		} catch (Exception e) {

			logger.error(this.getClass().getName()+"Error in method getOverLapCount :", e);
		}
		return liReturn;
	}
	
}
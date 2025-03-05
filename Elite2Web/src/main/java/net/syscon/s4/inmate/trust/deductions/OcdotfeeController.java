package net.syscon.s4.inmate.trust.deductions;

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
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.beans.TransactionTypes;
import net.syscon.s4.im.beans.OffenderDeductionReceipts;
import net.syscon.s4.im.beans.OffenderDeductionReceiptsCommitBean;
import net.syscon.s4.inmate.beans.DeductionTypes;
import net.syscon.s4.inmate.beans.OffenderDeductions;
import net.syscon.s4.inmate.beans.OffenderDeductionsCommitBean;


@EliteController
public class OcdotfeeController {
	@Autowired
	private OcdotfeeService ocdotfeeService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OcdotfeeController.class.getName());

	/**
	 * getting cgfkOffDedDspDescription LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdotfee/cgfkOffDedDspDescriptionRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> cgfkOffDedDspDescriptionRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = ocdotfeeService.cgfkOffDedDspDescriptionRecordGroup();
		} catch (Exception e) {
			logger.error(this.getClass().getName()+"Exception: cgfkOffDedDspDescriptionRecordGroup ", e);
		}
		return recordList;
	}

	/**
	 * getting cgfkOffDedDeductionType LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdotfee/cgfkOffDedDeductionTypeRecordGroup", method = RequestMethod.GET)
	public List<DeductionTypes> cgfkOffDedDeductionTypeRecordGroup(@RequestParam  (value="caseloadId") final String caseloadId) {
		List<DeductionTypes> recordList = new ArrayList<DeductionTypes>();
		try {
			recordList = ocdotfeeService.cgfkOffDedDeductionTypeRecordGroup(caseloadId);
		} catch (Exception e) {
			logger.error(this.getClass().getName()+"Exception : cgfkOffDedDeductionTypeRecordGroup : ", e);
		}
		return recordList;
	}

	/**
	 * getting cgfkOffDrReceiptTxnType LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdotfee/cgfkOffDrReceiptTxnTypeRecordGroup", method = RequestMethod.GET)
	public List<TransactionTypes> cgfkOffDrReceiptTxnTypeRecordGroup() {
		List<TransactionTypes> recordList = new ArrayList<TransactionTypes>();
		try {
			recordList = ocdotfeeService.cgfkOffDrReceiptTxnTypeRecordGroup();
		} catch (Exception e) {
			logger.error(this.getClass().getName()+"Exception cgfkOffDrReceiptTxnTypeRecordGroup : ", e);
		}
		return recordList;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdotfee/offDedExecuteQuery", method = RequestMethod.POST)
	public List<OffenderDeductions> offDedExecuteQuery(@RequestBody OffenderDeductions searchBean) {
		List<OffenderDeductions> searchResult = new ArrayList<>();
		try {
			searchResult = ocdotfeeService.offDedExecuteQuery(searchBean);
		} catch (Exception e) {
			OffenderDeductions bean = new OffenderDeductions();
			logger.error(this.getClass().getName()+"Exception offDedExecuteQuery : ", e);
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
	@RequestMapping(value = "/ocdotfee/offDedCommit", method = RequestMethod.POST)
	public @ResponseBody String offDedCommit(@RequestBody OffenderDeductionsCommitBean commitBean) {
		String liReturn = "0";
		if (commitBean != null) {
			String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			commitBean.setCreateUserId(userName);
		}
		try {
			liReturn = ocdotfeeService.offDedCommit(commitBean);
		} catch (Exception e) {
			liReturn = e.getMessage();
			logger.error(this.getClass().getName()+"Exception offDedCommit : ", e);
		}
		return liReturn;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdotfee/offDrExecuteQuery", method = RequestMethod.POST)
	public List<OffenderDeductionReceipts> offDrExecuteQuery(@RequestBody OffenderDeductionReceipts searchBean) {
		List<OffenderDeductionReceipts> searchResult = new ArrayList<>();
		try {
			searchResult = ocdotfeeService.offDrExecuteQuery(searchBean);
		} catch (Exception e) {
			OffenderDeductionReceipts bean = new OffenderDeductionReceipts();
			logger.error(this.getClass().getName()+"Exception offDrExecuteQuery : ", e);
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
	@RequestMapping(value = "/ocdotfee/offDrCommit", method = RequestMethod.POST)
	public @ResponseBody String offDrCommit(@RequestBody OffenderDeductionReceiptsCommitBean commitBean) {
		String liReturn = "0";
		if (commitBean != null) {
			String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			commitBean.setCreateUserId(userName);
		}
		try {
			liReturn = ocdotfeeService.offDrCommit(commitBean);
		} catch (Exception e) {
			liReturn = e.getMessage();
			logger.error(this.getClass().getName()+"Exception offDrCommit : ", e);
		}
		return liReturn;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdotfee/sysPflExecuteQuery", method = RequestMethod.POST)
	public List<SystemProfiles> sysPflExecuteQuery(@RequestBody SystemProfiles searchBean) {
		List<SystemProfiles> searchResult = new ArrayList<>();
		try {
			searchResult = ocdotfeeService.sysPflExecuteQuery(searchBean);
		} catch (Exception e) {
			SystemProfiles bean = new SystemProfiles();
			logger.error(this.getClass().getName()+"Exception sysPflExecuteQuery : ", e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}
	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdotfee/getPercentageAndFlatRate", method = RequestMethod.GET)
	public List<OffenderDeductionReceipts> getPercentageAndFlatRate(@RequestParam(value="deductionType") final String deductionType, @RequestParam(value="caseloadId")  final String caseloadId,
			@RequestParam(value="receiptTxnType")  final String receiptTxnType) {
		List<OffenderDeductionReceipts> searchResult = new ArrayList<>();
		try {
			searchResult = ocdotfeeService.getPercentageAndFlatRate(deductionType, caseloadId, receiptTxnType);
		} catch (Exception e) {
			OffenderDeductionReceipts bean = new OffenderDeductionReceipts();
			logger.error(this.getClass().getName()+"getPercentageAndFlatRate : ", e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}
}
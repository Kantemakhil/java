package net.syscon.s4.inmate.trust.statements;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import net.syscon.s4.common.EliteController;
import net.syscon.s4.common.beans.CaseloadAgencyLocations;
import net.syscon.s4.common.beans.OmsRequests;
import net.syscon.s4.common.beans.OmsRequestsCommitBean;
import net.syscon.s4.common.beans.ReportBean;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.im.beans.OmsModules;
import net.syscon.s4.inmate.beans.CaseloadDeductionProfiles;
import net.syscon.s4.inmate.beans.Printers;

/**
 * Class OtsreceiController
 */
@EliteController
public class OtsreceiController {
	@Autowired
	private OtsreceiService otsreceiService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OtsreceiController.class.getName());

	/**
	 * getting cgfkOmsReqPrinterId LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otsrecei/cgfkOmsReqPrinterIdRecordGroup", method = RequestMethod.GET)
	public List<Printers> cgfkOmsReqPrinterIdRecordGroup() {
		List<Printers> recordList = new ArrayList<Printers>();
		try {
			recordList = otsreceiService.cgfkOmsReqPrinterIdRecordGroup();
		} catch (Exception e) {
			final Printers obj = new Printers();
			logger.error("In method cgfkOmsReqPrinterIdRecordGroup : ", e);
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting cgfkOmsReqModuleName LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otsrecei/cgfkOmsReqModuleNameRecordGroup", method = RequestMethod.GET)
	public List<OmsModules> cgfkOmsReqModuleNameRecordGroup() {
		List<OmsModules> recordList = new ArrayList<OmsModules>();
		try {
			recordList = otsreceiService.cgfkOmsReqModuleNameRecordGroup();
		} catch (Exception e) {
			final OmsModules obj = new OmsModules();
			logger.error("In method cgfkOmsReqModuleNameRecordGroup : ", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting cgfkCsldDpAgyLoc LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otsrecei/cgfkCsldDpAgyLocRecordGroup", method = RequestMethod.GET)
	public List<CaseloadAgencyLocations> cgfkCsldDpAgyLocRecordGroup(
			@RequestParam(value = "caseloadId") final String caseloadId) {
		List<CaseloadAgencyLocations> recordList = new ArrayList<CaseloadAgencyLocations>();
		try {
			recordList = otsreceiService.cgfkCsldDpAgyLocRecordGroup(caseloadId);
		} catch (Exception e) {
			final CaseloadAgencyLocations obj = new CaseloadAgencyLocations();
			logger.error("In method cgfkCsldDpAgyLocRecordGroup : ", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting cgfkRecptsCreatedUsers LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otsrecei/cgfkRecptsCreatedUsersRecordGroup", method = RequestMethod.GET)
	public List<StaffMembers> cgfkRecptsCreatedUsersRecordGroup() {
		List<StaffMembers> recordList = new ArrayList<StaffMembers>();
		try {
			recordList = otsreceiService.cgfkRecptsCreatedUsersRecordGroup();
		} catch (Exception e) {
			final StaffMembers obj = new StaffMembers();
			logger.error("In method cgfkRecptsCreatedUsersRecordGroup : ", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * Fetching the record from database table
	 *
	 * @param searchBean {@link OmsRequests}
	 * @return a list of the OmsRequests {@link OmsRequests} for the matched OmsRequests
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otsrecei/omsReqExecuteQuery", method = RequestMethod.POST)
	public List<OmsRequests> omsReqExecuteQuery(@RequestBody final OmsRequests searchBean) {
		List<OmsRequests> searchResult = new ArrayList<>();
		try {
			searchResult = otsreceiService.omsReqExecuteQuery(searchBean);
		} catch (Exception e) {
			final OmsRequests bean = new OmsRequests();
			logger.error("In method omsReqExecuteQuery : ", e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}

	/**
	 * Perfomring basic Oracle form functions i.e. insert,delete, update int the
	 * database table
	 * 
	 * @param commitBean
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/otsrecei/omsReqCommit", method = RequestMethod.POST)
	public @ResponseBody Integer omsReqCommit(@RequestBody final OmsRequestsCommitBean commitBean) {
		int liReturn = 0;
		try {
			liReturn = otsreceiService.omsReqCommit(commitBean);
		} catch (Exception e) {

			logger.error("In method omsReqCommit : ", e);
		}
		return liReturn;
	}

	/**
	 * Fetching the record from database table
	 *
	 * @param paramBean {@link CaseloadDeductionProfiles}
	 * @return a list of the valid receipts {@link String} for the matched CaseloadDeductionProfiles
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otsrecei/checkValidReceipts", method = RequestMethod.POST)
	public @ResponseBody List<String> checkValidReceipts(@RequestBody final CaseloadDeductionProfiles paramBean) {
		List<String> liReturn = null;
		try {
			liReturn = otsreceiService.checkValidReceipts(paramBean);
		} catch (Exception e) {
			logger.error("In method checkValidReceipts : ", e);
		}
		return liReturn;
	}

	/**
	 * Fetching the record from database table
	 *
	 * @return the count of default copies as Bigdecimal {@link BigDecimal} value
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otsrecei/getDefaultCopies", method = RequestMethod.GET)
	public @ResponseBody BigDecimal getDefaultCopies() {
		BigDecimal liReturn = null;
		try {
			liReturn = otsreceiService.getDefaultCopies();
		} catch (Exception e) {
			logger.error("In method getDefaultCopies : ", e);
		}
		return liReturn;
	}

	/**
	 * Fetching the record from database table
	 *
	 * @param param {@link CaseloadDeductionProfiles}
	 * @return the value receipt {@link BigDecimal} for the matched CaseloadDeductionProfiles
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otsrecei/receiptNumExist", method = RequestMethod.POST)
	public @ResponseBody BigDecimal receiptNumExist(@RequestBody final CaseloadDeductionProfiles param) {
		BigDecimal liReturn = null;
		try {
			liReturn = otsreceiService.receiptNumExist(param);
		} catch (Exception e) {
			logger.error("In method receiptNumExist : ", e);
		}
		return liReturn;
	}

	/**
	 * Fetching the record from database table
	 *
	 * @param param {@link CaseloadDeductionProfiles}
	 * @return a ReportBean {@link ReportBean} for the matched CaseloadDeductionProfiles
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otsrecei/getReport", method = RequestMethod.POST)
	public @ResponseBody ReportBean getReport(@RequestBody final CaseloadDeductionProfiles param) {
		ReportBean liReturn = new ReportBean();
		try {
			liReturn = otsreceiService.getReport(param);
		} catch (Exception e) {
			logger.error("In method getReport : ", e);
		}
		return liReturn;
	}

}
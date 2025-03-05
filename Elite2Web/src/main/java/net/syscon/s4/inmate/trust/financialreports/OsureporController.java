package net.syscon.s4.inmate.trust.financialreports;

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
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.ReportBean;
import net.syscon.s4.im.beans.AccountCodes;
import net.syscon.s4.im.beans.Dual;
import net.syscon.s4.im.beans.OffenderTransactions;
import net.syscon.s4.im.beans.OmsModuleParameters;
import net.syscon.s4.im.beans.OtrbnrcnBean;

/**
 * Class OsureporController
 */
@EliteController
public class OsureporController {
	@Autowired
	private OsureporService osureporService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OsureporController.class.getName());

	/**
	 * Fetching the record from database table
	 *
	 * @param omsModuleParameters {@link OmsModuleParameters}
	 * @return a list of the OmsModuleParameters {@link OmsModuleParameters} for the matched OmsModuleParameters
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/osurepor/populateRecords", method = RequestMethod.POST)
	public List<OmsModuleParameters> populateRecords(@RequestBody final OmsModuleParameters omsModuleParameters) {
		List<OmsModuleParameters> searchResult = new ArrayList<>();
		try {
			searchResult = osureporService.populateRecords(omsModuleParameters);
		} catch (Exception e) {
			logger.error("populateRecords: ", e);
		}
		return searchResult;
	}

	/**
	 * getting rg1c LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/osurepor/rgcRecordGroup", method = RequestMethod.GET)
	public List<Dual> rg1cRecordGroup() {
		List<Dual> recordList = new ArrayList<Dual>();
		try {
			recordList = osureporService.rg1cRecordGroup();
		} catch (Exception e) {
			logger.error("rg1cRecordGroup: ", e);
		}
		return recordList;
	}

	/**
	 * getting rg2c LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/osurepor/rg2cRecordGroup", method = RequestMethod.GET)
	public List<Dual> rg2cRecordGroup() {
		List<Dual> recordList = new ArrayList<Dual>();
		try {
			recordList = osureporService.rg2cRecordGroup();
		} catch (Exception e) {
			logger.error("rg2cRecordGroup: ", e);
		}
		return recordList;
	}

	/**
	 * getting rg3c LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/osurepor/rg3cRecordGroup", method = RequestMethod.GET)
	public List<Dual> rg3cRecordGroup() {
		List<Dual> recordList = new ArrayList<Dual>();
		try {
			recordList = osureporService.rg3cRecordGroup();
		} catch (Exception e) {
			logger.error("rg3cRecordGroup: ", e);
		}
		return recordList;
	}

	/**
	 * getting rg4c LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/osurepor/rg4cRecordGroup", method = RequestMethod.GET)
	public List<Dual> rg4cRecordGroup() {
		List<Dual> recordList = new ArrayList<Dual>();
		try {
			recordList = osureporService.rg4cRecordGroup();
		} catch (Exception e) {
			logger.error("rg4cRecordGroup: ", e);
		}
		return recordList;
	}

	/**
	 * getting rg5c LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/osurepor/rg5cRecordGroup", method = RequestMethod.GET)
	public List<Dual> rg5cRecordGroup() {
		List<Dual> recordList = new ArrayList<Dual>();
		try {
			recordList = osureporService.rg5cRecordGroup();
		} catch (Exception e) {
			logger.error("rg5cRecordGroup: ", e);
		}
		return recordList;
	}

	/**
	 * getting rg5c1 LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/osurepor/rg5c1RecordGroup", method = RequestMethod.GET)
	public List<Dual> rg5c1RecordGroup() {
		List<Dual> recordList = new ArrayList<Dual>();
		try {
			recordList = osureporService.rg5c1RecordGroup();
		} catch (Exception e) {
			logger.error("rg5c1RecordGroup", e);
		}
		return recordList;
	}

	/**
	 * method for preInsert
	 *
	 * @param qry {@link String}
	 * @return a list of the VPrisonTotal {@link ReferenceCodes} for the matched agency location Id
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/osurepor/getDynamicLov", method = RequestMethod.GET)
	public @ResponseBody List<ReferenceCodes> getDynamicLov(@RequestParam(value = "qry") final String qry,
			@RequestParam(value = "caseload") final String caseload,
			@RequestParam(value = "accCode") final BigDecimal accCode) {
		List<ReferenceCodes> listOfRecords = new ArrayList<>();
		try {
			listOfRecords = osureporService.getDynamicLov(qry, caseload, accCode);
		} catch (Exception e) {
			logger.error("getDynamicLov: ", e);
		}
		return listOfRecords;
	}

	/**
	 * method for preInsert
	 *
	 * @param caseloadId {@link String}
	 * @return a list of the AccountCodes {@link AccountCodes} for the matched case load Id
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/osurepor/getReport", method = RequestMethod.GET)
	public @ResponseBody List<AccountCodes> getReport(@RequestParam(value = "caseloadId") final String caseloadId,
			@RequestParam(value = "datetoLong") final String datetoLong,
			@RequestParam(value = "flag") final String flag) {
		List<AccountCodes> listOfRecords = new ArrayList<>();
		try {
	String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();

			listOfRecords = osureporService.getReport(caseloadId, datetoLong, flag , userName);
		} catch (Exception e) {
			logger.error("getReport: ", e);
		}
		return listOfRecords;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/osurepor/getBankReconciliationReport", method = RequestMethod.GET)
	public @ResponseBody List<OtrbnrcnBean> getBankReconciliationReport(
			@RequestParam(value = "accountCode") final Long accountCode,
			@RequestParam(value = "userDate") final String userDate,
			@RequestParam(value = "caseloadId") final String caseloadId) {
		List<OtrbnrcnBean> listOfRecords = new ArrayList<>();
		try {
			String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();

			listOfRecords = osureporService.getBankReconciliationReport(accountCode, userDate, caseloadId ,userName );
		} catch (Exception e) {
			logger.error("getBankReconciliationReport: ", e);
		}
		return listOfRecords;
	}
	
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/osurepor/getReportOtrbstat", method = RequestMethod.POST)
	public ReportBean getReportOtrbstat(@RequestBody List<OffenderTransactions> paramBean) {
		ReportBean returnList = new ReportBean();
		try {
			returnList = osureporService.getReportOtrbstat(paramBean);
		} catch (Exception e) {
			returnList.setErrorMessage("Exception : " + e.getMessage());
			logger.error("mainReprtProcess : ", e);
		}
		return returnList;
	}
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/osurepor/getLovOtrbstat", method = RequestMethod.GET)
	public @ResponseBody List<ReferenceCodes> getLovOtrbstat(@RequestParam(value = "parameterName") final String parameterName,
			@RequestParam(value = "caseloadId") final String caseloadId) {
		List<ReferenceCodes> listOfRecords = new ArrayList<>();
		try {
			listOfRecords = osureporService.getLovOtrbstat(parameterName,caseloadId);
		} catch (Exception e) {
			logger.error("getBankReconciliationReport: ", e);
		}
		return listOfRecords;
	}
}
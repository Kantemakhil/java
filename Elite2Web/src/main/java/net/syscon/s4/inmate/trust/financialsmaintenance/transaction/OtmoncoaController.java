package net.syscon.s4.inmate.trust.financialsmaintenance.transaction;

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
import net.syscon.s4.common.beans.CaseloadAccountPeriods;
import net.syscon.s4.common.beans.CaseloadAccountPeriodsCommitBean;
import net.syscon.s4.common.beans.Caseloads;

@EliteController
public class OtmoncoaController {
	@Autowired
	private OtmoncoaService otmoncoaService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OtmoncoaController.class.getName());

	/**
	 * getting cgfkCsldApCaseloadId LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otmoncoa/cgfkCsldApCaseloadIdRecordGroup", method = RequestMethod.GET)
	public List<Caseloads> cgfkCsldApCaseloadIdRecordGroup(
			@RequestParam(value = "caseloadType") final String caseloadType) {
		List<Caseloads> recordList = new ArrayList<Caseloads>();
		try {
			recordList = otmoncoaService.cgfkCsldApCaseloadIdRecordGroup(caseloadType);
		} catch (Exception e) {
			Caseloads obj = new Caseloads();
			logger.error("Exception cgfkCsldApCaseloadIdRecordGroup :", e);
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
	@RequestMapping(value = "/otmoncoa/csldApExecuteQuery", method = RequestMethod.POST)
	public List<CaseloadAccountPeriods> csldApExecuteQuery(@RequestBody CaseloadAccountPeriods searchBean) {
		List<CaseloadAccountPeriods> searchResult = new ArrayList<>();
		try {
			searchResult = otmoncoaService.csldApExecuteQuery(searchBean);
		} catch (Exception e) {
			CaseloadAccountPeriods bean = new CaseloadAccountPeriods();
			logger.error("Exception csldApExecuteQuery :", e);
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
	@RequestMapping(value = "/otmoncoa/csldApCommit", method = RequestMethod.POST)
	public @ResponseBody String csldApCommit(@RequestBody CaseloadAccountPeriodsCommitBean commitBean) {
		String liReturn = "0";
		try {
			if(commitBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				commitBean.setCreateUserId(userName);
				}
			liReturn = otmoncoaService.csldApCommit(commitBean);
		} catch (Exception e) {
			liReturn = e.getMessage().toUpperCase();
			if(liReturn.contains("CSLD_AP_CSLD_F2") || liReturn.contains("CSLD_AP_AC_PRD_F1")) {
				liReturn = liReturn + " " +2291;
			}else if(liReturn.contains("CSLD_AH_CSLD_AP_F1")) {
				liReturn = liReturn + " " + 2292;
			}
			logger.error("Exception csldApCommit :", e);
		}
		return liReturn;
	}

	/**
	 * Perfomring basic Oracle form functions i.e. insert,delete, update int the
	 * database table
	 * 
	 * @Param commitBean
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otmoncoa/getTotalCount", method = RequestMethod.GET)
	public BigDecimal getTotalCount(@RequestParam(value = "caseloadId") final String caseloadId) {
		BigDecimal liReturn = BigDecimal.ZERO;
		try {
			liReturn = otmoncoaService.getTotalCount(caseloadId);
		} catch (Exception e) {

			logger.error("Exception getTotalCount :", e);
		}
		return liReturn;
	}

}
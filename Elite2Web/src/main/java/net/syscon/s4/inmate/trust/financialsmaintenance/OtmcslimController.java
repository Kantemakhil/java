package net.syscon.s4.inmate.trust.financialsmaintenance;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import net.syscon.s4.common.EliteController;
import net.syscon.s4.common.beans.Caseloads;
import net.syscon.s4.im.beans.CaseloadLimits;
import net.syscon.s4.im.beans.CaseloadLimitsCommitBean;
import net.syscon.s4.im.beans.ReferenceCodes;

/**
 * Class OtmcslimController
 */
@EliteController
public class OtmcslimController {
	@Autowired
	private OtmcslimService otmcslimService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OtmcslimController.class.getName());

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otmcslim/csldLimExecuteQuery", method = RequestMethod.POST)
	public List<CaseloadLimits> csldLimExecuteQuery(@RequestBody CaseloadLimits searchBean) {
		List<CaseloadLimits> searchResult = new ArrayList<>();
		try {
			searchResult = otmcslimService.csldLimExecuteQuery(searchBean);
		} catch (Exception e) {
			CaseloadLimits bean = new CaseloadLimits();
			logger.error("Exception :", e);
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
	@RequestMapping(value = "/otmcslim/csldLimCommit", method = RequestMethod.POST)
	public @ResponseBody String csldLimCommit(@RequestBody CaseloadLimitsCommitBean commitBean) {
		String liReturn = "0";
		try {
			liReturn = otmcslimService.csldLimCommit(commitBean);
		} catch (Exception e) {
			liReturn = e.getMessage();
			if(e.getMessage().contains("caseload_limits_pk"))
				return "CASELOAD_LIMITS_PK";
			logger.error("Exception :", e);
		}
		return liReturn;
	}

	/**
	 * getting cgfkCsldLimCaseloadId LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otmcslim/cgfkCsldLimCaseloadIdRecordGroup", method = RequestMethod.GET)
	public List<Caseloads> cgfkCsldLimCaseloadIdRecordGroup() {
		List<Caseloads> recordList = new ArrayList<Caseloads>();
		try {
			recordList = otmcslimService.cgfkCsldLimCaseloadIdRecordGroup();
		} catch (Exception e) {
			Caseloads obj = new Caseloads();
			logger.error("Exception :", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting cgfkCsldLimLimitType LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otmcslim/cgfkCsldLimLimitTypeRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> cgfkCsldLimLimitTypeRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = otmcslimService.cgfkCsldLimLimitTypeRecordGroup();
		} catch (Exception e) {
			ReferenceCodes obj = new ReferenceCodes();
			logger.error("Exception :", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting cgfkCsldLimPeriodType LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otmcslim/cgfkCsldLimPeriodTypeRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> cgfkCsldLimPeriodTypeRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = otmcslimService.cgfkCsldLimPeriodTypeRecordGroup();
		} catch (Exception e) {
			ReferenceCodes obj = new ReferenceCodes();
			logger.error("Exception :", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

}
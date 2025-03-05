package net.syscon.s4.inmate.trust.financialsmaintenance.subaccounts;

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

import net.syscon.s4.common.EliteController;
import net.syscon.s4.common.beans.CaseloadAgencyLocations;
import net.syscon.s4.common.beans.Caseloads;
import net.syscon.s4.common.beans.InstitutionMiniBalances;
import net.syscon.s4.common.beans.InstitutionMiniBalancesCommitBean;
import net.syscon.s4.im.beans.AccountCodes;

/**
 * class OtmisambController
 */
@EliteController
public class OtmisambController {

	@Autowired
	private OtmisambService otmisambService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OtmisambController.class.getName());

	/**
	 * getting cgfkInstMnbalCaseloadId LOV values
	 * method:cgfkInstMnbalCaseloadIdRecordGroup
	 * 
	 * @return List<Caseloads>
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otmisamb/cgfkInstMnbalCaseloadIdRecordGroup", method = RequestMethod.GET)
	public List<Caseloads> cgfkInstMnbalCaseloadIdRecordGroup() {
		List<Caseloads> recordList = new ArrayList<Caseloads>();
		try {
			recordList = otmisambService.cgfkInstMnbalCaseloadIdRecordGroup();
		} catch (Exception e) {
			logger.error("Exception cgfkInstMnbalCaseloadIdRecordGroup :", e);

		}
		return recordList;
	}

	/**
	 * getting cgfkInstMnbalAccountCode LOV values
	 * method:cgfkInstMnbalAccountCodeRecordGroup
	 * 
	 * @return List<AccountCodes>
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otmisamb/cgfkInstMnbalAccountCodeRecordGroup", method = RequestMethod.GET)
	public List<AccountCodes> cgfkInstMnbalAccountCodeRecordGroup() {
		List<AccountCodes> recordList = new ArrayList<AccountCodes>();
		try {
			recordList = otmisambService.cgfkInstMnbalAccountCodeRecordGroup();
		} catch (Exception e) {
			logger.error("Exception cgfkInstMnbalAccountCodeRecordGroup :", e);
		}
		return recordList;
	}

	/**
	 * getting cgfkInstMnbalAgyLocId LOV values
	 * method:cgfkInstMnbalAgyLocIdRecordGroup
	 * 
	 * @return List<CaseloadAgencyLocations>
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otmisamb/cgfkInstMnbalAgyLocIdRecordGroup", method = RequestMethod.GET)
	public List<CaseloadAgencyLocations> cgfkInstMnbalAgyLocIdRecordGroup(
			@RequestParam(value = "caseloadId") final String caseloadId) {
		List<CaseloadAgencyLocations> recordList = new ArrayList<CaseloadAgencyLocations>();
		try {
			recordList = otmisambService.cgfkInstMnbalAgyLocIdRecordGroup(caseloadId);
		} catch (Exception e) {
			logger.error("Exception cgfkInstMnbalAgyLocIdRecordGroup :", e);
		}
		return recordList;
	}

	/**
	 * Fetching the record from database table method:instMnbalExecuteQuery
	 * 
	 * @Param searchRecord
	 * @return List<InstitutionMiniBalances>
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otmisamb/instMnbalExecuteQuery", method = RequestMethod.POST)
	public List<InstitutionMiniBalances> instMnbalExecuteQuery(@RequestBody InstitutionMiniBalances searchBean) {
		List<InstitutionMiniBalances> searchResult = new ArrayList<InstitutionMiniBalances>();
		try {
			searchResult = otmisambService.instMnbalExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error("Exception instMnbalExecuteQuery :", e);
		}
		return searchResult;
	}

	/**
	 * Perfomring basic Oracle form functions i.e. insert,delete, update int the
	 * database table method:instMnbalCommit
	 * 
	 * @Param commitBean
	 * @return Integer
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/otmisamb/instMnbalCommit", method = RequestMethod.POST)
	public String instMnbalCommit(@RequestBody InstitutionMiniBalancesCommitBean commitBean) {
		String liReturn = "0";
		try {
			String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			commitBean.setCreateUserId(userName);
			liReturn = otmisambService.instMnbalCommit(commitBean);
		} catch (Exception e) {
			liReturn = e.getMessage();
			logger.error("Exception instMnbalCommit :", e);
		}
		return liReturn;
	}

}
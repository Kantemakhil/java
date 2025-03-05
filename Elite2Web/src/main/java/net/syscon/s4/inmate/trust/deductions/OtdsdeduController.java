package net.syscon.s4.inmate.trust.deductions;

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
import net.syscon.s4.common.beans.Caseloads;
import net.syscon.s4.im.beans.SuspendDeductionTypes;
import net.syscon.s4.im.beans.SuspendDeductions;
import net.syscon.s4.inmate.beans.DeductionTypes;
import net.syscon.s4.inmate.beans.SuspendDeductionTypesCommitBean;
import net.syscon.s4.inmate.beans.SuspendDeductionsCommitBean;

@EliteController
public class OtdsdeduController {
	@Autowired
	private OtdsdeduService otdsdeduService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OtdsdeduController.class.getName());

	/**
	 * getting cgfkSusDedCaseloadId LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otdsdedu/cgfkSusDedCaseloadIdRecordGroup", method = RequestMethod.GET)
	public List<Caseloads> cgfkSusDedCaseloadIdRecordGroup() {
		List<Caseloads> recordList = new ArrayList<Caseloads>();
		try {
			recordList = otdsdeduService.cgfkSusDedCaseloadIdRecordGroup();
		} catch (Exception e) {
			logger.error(
					"OtdsdeduController cgfkSusDedCaseloadIdRecordGroup unable to call service  unable to call service ",
					e);
		}
		return recordList;
	}

	/**
	 * getting cgfkSusDtDeductionType LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otdsdedu/cgfkSusDtDeductionTypeRecordGroup", method = RequestMethod.GET)
	public List<DeductionTypes> cgfkSusDtDeductionTypeRecordGroup(
			@RequestParam("caseloadType") final String caseloadType) {
		List<DeductionTypes> recordList = new ArrayList<DeductionTypes>();
		try {
			recordList = otdsdeduService.cgfkSusDtDeductionTypeRecordGroup(caseloadType);
		} catch (Exception e) {
			logger.error("OtdsdeduController cgfkSusDtDeductionTypeRecordGroup unable to call service ", e);
		}
		return recordList;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otdsdedu/susDedExecuteQuery", method = RequestMethod.POST)
	public List<SuspendDeductions> susDedExecuteQuery(@RequestBody final SuspendDeductions searchBean) {
		List<SuspendDeductions> searchResult = new ArrayList<>();
		try {
			searchResult = otdsdeduService.susDedExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error("OtdsdeduController susDedExecuteQuery unable to call service ", e);
		}
		return searchResult;
	}

	/**
	 * Performing basic Oracle form functions i.e. insert,delete, update into
	 * the database table
	 * 
	 * @Param commitBean
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/otdsdedu/susDedCommit", method = RequestMethod.POST)
	public @ResponseBody String susDedCommit(@RequestBody final SuspendDeductionsCommitBean commitBean) {
		String liReturn = "0";
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		commitBean.setCreateUserId(userName);
		try {
			liReturn = otdsdeduService.susDedCommit(commitBean);
		} catch (Exception e) {
			logger.error("OtdsdeduController susDedCommit unable to call service unable to call service ", e);
			liReturn = e.getMessage();
		}
		return liReturn;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otdsdedu/susDtExecuteQuery", method = RequestMethod.POST)
	public List<SuspendDeductionTypes> susDtExecuteQuery(@RequestBody final SuspendDeductionTypes searchBean) {
		List<SuspendDeductionTypes> searchResult = new ArrayList<>();
		try {
			searchResult = otdsdeduService.susDtExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error("OtdsdeduController susDtExecuteQuery unable to call service ", e);
		}
		return searchResult;
	}

	/**
	 * Performing basic Oracle form functions i.e. insert,delete, update into
	 * the database table
	 * 
	 * @Param commitBean
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/otdsdedu/susDtCommit", method = RequestMethod.POST)
	public @ResponseBody String susDtCommit(@RequestBody final SuspendDeductionTypesCommitBean commitBean) {
		String liReturn = "0";
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		commitBean.setCreateUserId(userName);
		try {
			liReturn = otdsdeduService.susDtCommit(commitBean);
		} catch (Exception e) {
			liReturn = e.getMessage();
			logger.error("OtdsdeduController susDtCommit unable to call service ", e);
		}
		return liReturn;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otdsdedu/chkOverlapDate", method = RequestMethod.GET)
	public @ResponseBody BigDecimal chkOverlapDate(@RequestParam(value = "caseloadId") final String caseloadId,
			@RequestParam(value = "startDate") final String startDate,
			@RequestParam(value = "endDate") final String endDate, @RequestParam(value = "flag") final String flag) {
		BigDecimal overlapCount = BigDecimal.ZERO;
		try {
			overlapCount = otdsdeduService.chkOverlapDate(caseloadId, startDate, endDate, flag);
		} catch (Exception e) {
			logger.error("OtdsdeduController chkOverlapDate unable to call service ", e);
		}
		return overlapCount;
	}

}
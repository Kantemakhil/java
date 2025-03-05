package net.syscon.s4.inst.institutionalactivities;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
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
import net.syscon.s4.common.beans.AgencyInternalLocations;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.inst.institutionalactivities.beans.OffenderAllowances;
import net.syscon.s4.inst.institutionalactivities.beans.OffenderAllowancesCommitBean;
import net.syscon.s4.inst.institutionalactivities.maintenance.beans.Allowances;
import net.syscon.s4.pkgs.common.CommonService;

@EliteController
public class OidallowController {

	
	@Autowired
	private CommonService commonService;
	
	@Autowired
	private OidallowService oidallowService;
	
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OcdcgpayController.class.getName());
	
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/oidallow/getOffenderAllowenceExecuteQuery", method = RequestMethod.POST)
	public List<OffenderAllowances> getOffenderAllowenceExecuteQuery(@RequestBody final OffenderAllowances searchBean) {
		List<OffenderAllowances> searchResult = new ArrayList<>();
		try {
			searchResult = oidallowService.getOffenderAllowenceExecuteQuery(searchBean);
		} catch (Exception e) {
			final OffenderAllowances bean = new OffenderAllowances();
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
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/oidallow/offenderAllowenceDataCommit", method = RequestMethod.POST)
	public @ResponseBody List<OffenderAllowances> offenderAllowenceDataCommit(
			@RequestBody final OffenderAllowancesCommitBean commitBean) {
		List<OffenderAllowances> liReturn = new ArrayList<>();
		try { 
			if (commitBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				commitBean.setCreateUserId(userName);
			}
			 liReturn = oidallowService.offenderAllowenceDataCommit(commitBean);
		} catch (Exception e) {
			final OffenderAllowances bean = new OffenderAllowances();
			logger.error("Exception :", e);
			bean.setErrorMessage(e.getMessage().toUpperCase());
			liReturn.add(bean);
			logger.error("Exception : Ocmssvct", e);
		}
		return liReturn;
	}
	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidallow/getAllowenceLovData", method = RequestMethod.GET)
	public List<Allowances> getAllowenceLovData() {
		List<Allowances> liReturn = new ArrayList<Allowances>();
		try {
			liReturn = oidallowService.getAllowenceLovData();
		} catch (Exception e) {
			logger.error(this.getClass().getName() + "error in getAllowenceLovData  : " + e);
		}
		return liReturn;
	}
	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidallow/getUnitDataLov", method = RequestMethod.GET)
	public List<ReferenceCodes> getUnit() {
		List<ReferenceCodes> liReturn = new ArrayList<ReferenceCodes>();
		try {
			liReturn = oidallowService.getUnitDataLov();
		} catch (Exception e) {
			logger.error(this.getClass().getName() + "error in getUnitDataLov  : " + e);
		}
		return liReturn;
	}
	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidallow/getRateVersionData", method = RequestMethod.GET)
	public List<Allowances> getRateVersionData(@RequestParam("allowanceType") final String allowanceType) {
		List<Allowances> recordList = new ArrayList<>();
		try {
			recordList = oidallowService.getRateVersionData(allowanceType);
		} catch (Exception e) {
			final Allowances obj = new Allowances();
			logger.error("Exception : getRateVersionData:", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}
	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidallow/getLastPaidDate", method = RequestMethod.GET)
	public Date getLastPaidDate(@RequestParam(value = "offenderBookId") final BigDecimal offenderBookId, @RequestParam(value = "offAllowanceId") final BigDecimal offAllowanceId) {
		Date lastPaidDate = null;
		try {
			 lastPaidDate = oidallowService.getLastPaidDate(offenderBookId, offAllowanceId);
		} catch (Exception e) {
			logger.error("Exception : getLastPaidDate:", e);
		}
		return lastPaidDate;
	}
}

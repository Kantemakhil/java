package net.syscon.s4.inmate.trust.trustaccounts;

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
import org.springframework.web.bind.annotation.ResponseBody;

import net.syscon.s4.common.EliteController;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.beans.SystemProfilesCommitBean;
import net.syscon.s4.inmate.beans.OffenderTrustAccountsTemp;
import net.syscon.s4.inmate.beans.OffenderTrustAccountsTempCommitBean;
import net.syscon.s4.inst.automatedcounts.beans.LockedModules;

/**
 * @Class OtdclinaController
 */
@EliteController
public class OtdclinaController {
	@Autowired
	private OtdclinaService otdclinaService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OtdclinaController.class.getName());

	/**
	 * getting selectMethodRg LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otdclina/selectMethodRgRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> selectMethodRgRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = otdclinaService.selectMethodRgRecordGroup();
		} catch (Exception e) {
			logger.error("selectMethodRgRecordGroup", e);
		}
		return recordList;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otdclina/sysPflExecuteQuery", method = RequestMethod.POST)
	public List<SystemProfiles> sysPflExecuteQuery(@RequestBody final SystemProfiles searchBean) {
		List<SystemProfiles> searchResult = new ArrayList<>();
		try {
			searchResult = otdclinaService.sysPflExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error("sysPflExecuteQuery", e);
		}
		return searchResult;
	}

	/**
	 * Perfomring basic Oracle form functions i.e. insert,delete, update int the
	 * database table
	 * 
	 * @Param commitBean
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otdclina/sysPflCommit", method = RequestMethod.POST)
	public @ResponseBody Integer sysPflCommit(@RequestBody final SystemProfilesCommitBean commitBean) {
		int liReturn = 0;
		try {
			liReturn = otdclinaService.sysPflCommit(commitBean);
		} catch (Exception e) {
			logger.error("sysPflCommit", e);
		}
		return liReturn;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otdclina/offTracExecuteQuery", method = RequestMethod.POST)
	public List<OffenderTrustAccountsTemp> offTracExecuteQuery(
			@RequestBody final OffenderTrustAccountsTemp searchBean) {
		List<OffenderTrustAccountsTemp> searchResult = new ArrayList<>();
		try {
			if (searchBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				searchBean.setCreateUserId(userName);
			}
			searchResult = otdclinaService.offTracExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error("offTracExecuteQuery", e);
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
	@RequestMapping(value = "/otdclina/offTracCommit", method = RequestMethod.POST)
	public @ResponseBody Integer offTracCommit(@RequestBody final OffenderTrustAccountsTempCommitBean commitBean) {
		int liReturn = 0;
		try {
			liReturn = otdclinaService.offTracCommit(commitBean);
		} catch (Exception e) {
			logger.error("offTracCommit", e);
		}
		return liReturn;
	}

	/**
	 * Perfomring basic Oracle form functions i.e. insert,delete, update int the
	 * database table
	 * 
	 * @Param commitBean
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/otdclina/whenButtonPressed", method = RequestMethod.POST)
	public @ResponseBody Integer whenButtonPressed(@RequestBody final OffenderTrustAccountsTempCommitBean commitBean) {
		int liReturn = 0;
		try {
			if (commitBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				commitBean.setCreateUserId(userName);
			}
			liReturn = otdclinaService.whenButtonPressed(commitBean);
		} catch (Exception e) {
			logger.error("whenButtonPressed", e);
			if(e.getMessage().contains("PROCESS_GL_TRANS_NEW")) {
				return 3;
			}
		}
		return liReturn;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otdclina/checkLock", method = RequestMethod.GET)
	public List<LockedModules> checkLock() {
		List<LockedModules> searchResult = new ArrayList<>();
		try {
			searchResult = otdclinaService.checkLock();
		} catch (Exception e) {
			logger.error("checkLock", e);
		}
		return searchResult;
	}

}
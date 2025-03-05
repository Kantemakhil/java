package net.syscon.s4.inst.securitythreatgroups;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;

import net.syscon.s4.common.EliteController;
import net.syscon.s4.common.beans.StgValidations;
import net.syscon.s4.common.beans.StgValidationsCommitBean;
import net.syscon.s4.im.beans.ReferenceCodes;
import net.syscon.s4.pkgs.common.CommonService;

/**
 * @class OidstgvlController
 */
@EliteController
public class OidstgvlController {
	@Autowired
	private OidstgvlService oidstgvlService;
	@Autowired
	private CommonService commonService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OidstgvlController.class.getName());

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/oidstgvl/stgValidationsExecuteQuery", method = RequestMethod.POST)
	public List<StgValidations> stgValidationsExecuteQuery(@RequestBody final StgValidations searchBean) {
		List<StgValidations> searchResult = new ArrayList<>();
		if (!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		try {
			searchResult = oidstgvlService.stgValidationsExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error("stgValidationsExecuteQuery", e);
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
	@RequestMapping(value = "/oidstgvl/stgValidationsCommit", method = RequestMethod.POST)
	public Integer stgValidationsCommit(@RequestBody final StgValidationsCommitBean commitBean) {
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		commitBean.setCreateUserId(userName);
		if (!checkCallFormAccess("full")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		int liReturn = 0;
		try {
			liReturn = oidstgvlService.stgValidationsCommit(commitBean);
		} catch (Exception e) {

			logger.error("stgValidationsCommit", e);
		}
		return liReturn;
	}

	/**
	 * getting rgAction LOV values
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/oidstgvl/rgActionRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgActionRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		if (!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		try {
			recordList = oidstgvlService.rgActionRecordGroup();
		} catch (Exception e) {
			logger.error("rgActionRecordGroup", e);
		}
		return recordList;
	}

	/**
	 * getting rgDesignation LOV values
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/oidstgvl/rgDesignationRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgDesignationRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		if (!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		try {
			recordList = oidstgvlService.rgDesignationRecordGroup();
		} catch (Exception e) {
			logger.error("rgDesignationRecordGroup", e);
		}
		return recordList;
	}

	/**
	 * getting rgReason LOV values
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/oidstgvl/rgReasonRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgReasonRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		if (!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		try {
			recordList = oidstgvlService.rgReasonRecordGroup();
		} catch (Exception e) {
			logger.error("rgReasonRecordGroup", e);
		}
		return recordList;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param offenderId
	 * @return Integer
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/oidstgvl/reviewDateData", method = RequestMethod.GET)
	public @ResponseBody Date reviewDateData(@RequestParam(value = "stgId") final Long stgId) {
		Date count = null;
		if (!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		try {
			count = oidstgvlService.reviewDateData(stgId);
		} catch (Exception e) {
			logger.error("offbkgsExecuteQuery: ", e);
		}
		return count;
	}
	
	private Boolean checkCallFormAccess(String role) {
		String userId = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		return commonService.checkCallFormAccess(userId, role, "OIDSTGVL");
	}
}
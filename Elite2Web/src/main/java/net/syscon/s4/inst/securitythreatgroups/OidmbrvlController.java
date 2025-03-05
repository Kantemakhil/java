package net.syscon.s4.inst.securitythreatgroups;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;

import net.syscon.s4.common.EliteController;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.im.beans.OffenderStgDetails;
import net.syscon.s4.im.beans.OffenderStgDetailsCommitBean;
import net.syscon.s4.pkgs.common.CommonService;

@EliteController
public class OidmbrvlController {
	@Autowired
	private OidmbrvlService oidmbrvlService;
	@Autowired
	private CommonService commonService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OidmbrvlController.class.getName());

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/oidmbrvl/offenderStgDetailsExecuteQuery", method = RequestMethod.POST)
	public List<OffenderStgDetails> offenderStgDetailsExecuteQuery(@RequestBody final OffenderStgDetails searchBean) {
		List<OffenderStgDetails> searchResult = new ArrayList<>();
		if (!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		try {
			searchResult = oidmbrvlService.offenderStgDetailsExecuteQuery(searchBean);
		} catch (final Exception e) {
			logger.error("Exception : offenderStgDetailsExecuteQuery ", e);
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
	@RequestMapping(value = "/oidmbrvl/offenderStgDetailsCommit", method = RequestMethod.POST)
	public @ResponseBody Integer offenderStgDetailsCommit(@RequestBody final OffenderStgDetailsCommitBean commitBean) {
		int liReturn = 0;
		if (!checkCallFormAccess("full")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		try {
			commitBean.setCreateUserId(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
			liReturn = oidmbrvlService.offenderStgDetailsCommit(commitBean);
		} catch (final Exception e) {

			logger.error("Exception : Oidmbrvl : offenderStgDetailsCommit", e);
		}
		return liReturn;
	}

	/**
	 * getting rgAction LOV values
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/oidmbrvl/rgActionRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgActionRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<>();
		if (!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		try {
			recordList = oidmbrvlService.rgActionRecordGroup();
		} catch (final Exception e) {
			logger.error("Exception : Oidmbrvl: rgActionRecordGroup", e);
		}
		return recordList;
	}

	/**
	 * getting rgReason LOV values
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/oidmbrvl/rgReasonRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgReasonRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<>();
		if (!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		try {
			recordList = oidmbrvlService.rgReasonRecordGroup();
		} catch (final Exception e) {
			final ReferenceCodes obj = new ReferenceCodes();
			logger.error("Exception : Oidmbrvl: rgReasonRecordGroup", e);
			obj.setErrorMessage(e.getMessage());
		}
		return recordList;
	}
	
	private Boolean checkCallFormAccess(String role) {
		String userId = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		return commonService.checkCallFormAccess(userId, role, "OIDMBRVL");
	}
}
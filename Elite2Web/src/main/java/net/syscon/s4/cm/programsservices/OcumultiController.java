package net.syscon.s4.cm.programsservices;

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
import net.syscon.s4.inst.schedules.bean.VOffenderAllSchedules;
import net.syscon.s4.inst.schedules.bean.VOffenderAllSchedulesCommitBean;
import net.syscon.s4.pkgs.common.CommonService;

@EliteController
public class OcumultiController {
	@Autowired
	private OcumultiService ocumultiService;
	@Autowired
	private CommonService commonService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OcumultiController.class.getName());

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ocumulti/offBlockExecuteQuery", method = RequestMethod.POST)
	public List<VOffenderAllSchedules> offBlockExecuteQuery(@RequestBody final VOffenderAllSchedules searchBean) {
		if(!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		List<VOffenderAllSchedules> searchResult = new ArrayList<>();
		try {
			searchResult = ocumultiService.offBlockExecuteQuery(searchBean);
		} catch (final Exception e) {
			final VOffenderAllSchedules bean = new VOffenderAllSchedules();
			logger.error("Exception :", e);
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
	@RequestMapping(value = "/ocumulti/offBlockCommit", method = RequestMethod.POST)
	public @ResponseBody Integer offBlockCommit(@RequestBody final VOffenderAllSchedulesCommitBean commitBean) {
		if(!checkCallFormAccess("full")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		int liReturn = 0;
		try {
			liReturn = ocumultiService.offBlockCommit(commitBean);
		} catch (final Exception e) {

			logger.error("Exception : Ocumulti", e);
		}
		return liReturn;
	}

	/**
	 * getting rgYnFlag LOV values
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ocumulti/rgYnFlagRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgYnFlagRecordGroup() {
		if(!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		List<ReferenceCodes> recordList = new ArrayList<>();
		try {
			recordList = ocumultiService.rgYnFlagRecordGroup();

		} catch (final Exception e) {
			logger.error("Exception : Ocumulti:", e);
		}
		return recordList;
	}
	
	private Boolean checkCallFormAccess(String role) {
		String userId = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		return commonService.checkCallFormAccess(userId, role,"OCUMULTI");
	}

}
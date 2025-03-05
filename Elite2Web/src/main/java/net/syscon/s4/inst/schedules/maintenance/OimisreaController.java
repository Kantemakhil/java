package net.syscon.s4.inst.schedules.maintenance;

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
import net.syscon.s4.inst.casemanagement.beans.InternalScheduleReasons;
import net.syscon.s4.inst.schedules.bean.InternalScheduleReasonsCommitBean;

@EliteController
public class OimisreaController {
	@Autowired
	private OimisreaService oimisreaService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OimisreaController.class.getName());

	/**
	 * Fetching the record from database table
	 *
	 * @param searchBean {@link InternalScheduleReasons}
	 * @return a list of the InternalScheduleReasons {@link InternalScheduleReasons} for the matched InternalScheduleReasons
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oimisrea/intSrExecuteQuery", method = RequestMethod.POST)
	public List<InternalScheduleReasons> intSrExecuteQuery(@RequestBody final InternalScheduleReasons searchBean) {
		List<InternalScheduleReasons> searchResult = new ArrayList<>();
		try {
			searchResult = oimisreaService.intSrExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error("intSrExecuteQuery :", e);
		}
		return searchResult;
	}

	/**
	 * Performing basic Oracle form functions i.e. insert,delete, update into
	 * the database table
	 *
	 * @param commitBean {@link InternalScheduleReasonsCommitBean}
	 * @return the InternalScheduleReasons {@link InternalScheduleReasons} for the matched InternalScheduleReasonsCommitBean
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/oimisrea/intSrCommit", method = RequestMethod.POST)
	public @ResponseBody InternalScheduleReasons intSrCommit(
			@RequestBody final InternalScheduleReasonsCommitBean commitBean) {
		InternalScheduleReasons liReturn = new InternalScheduleReasons();
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		commitBean.setCreateUserId(userName);
		try {
			liReturn = oimisreaService.intSrCommit(commitBean);
		} catch (Exception e) {
			final String errorMsg = "Error : " + e.getMessage();
			liReturn.setErrorMessage(errorMsg.toUpperCase());
			return liReturn;
		}
		return liReturn;
	}

	/**
	 * getting rgIntSchRsn LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oimisrea/rgIntSchRsnRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgIntSchRsnRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = oimisreaService.rgIntSchRsnRecordGroup();
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return recordList;
	}

	/**
	 * getting rgIntSchType LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oimisrea/rgIntSchTypeRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgIntSchTypeRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = oimisreaService.rgIntSchTypeRecordGroup();
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return recordList;
	}

	/**
	 * getting rgIntSchRsn LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oimisrea/intSrKeyDelrec", method = RequestMethod.POST)
	public Long intSrKeyDelrec(@RequestBody final InternalScheduleReasons searchBean) {
		Long resultCount = null;
		try {
			resultCount = oimisreaService.intSrKeyDelrec(searchBean);
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return resultCount;
	}
}
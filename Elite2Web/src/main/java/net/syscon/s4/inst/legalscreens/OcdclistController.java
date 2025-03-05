package net.syscon.s4.inst.legalscreens;

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
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.inst.legalscreens.bean.OcdclistCourtListQuery;
import net.syscon.s4.inst.legalscreens.bean.OcdclistCourtListQueryCommitBean;
import net.syscon.s4.inst.legalscreens.bean.VCourtEvents;
import net.syscon.s4.inst.schedules.bean.CourtEventsCommitBean;

/**
 * Class OcdclistController
 */
@EliteController
public class OcdclistController {
	@Autowired
	private OcdclistService ocdclistService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OcdclistController.class.getName());

	/**
	 * getting rgAgyLoc LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdclist/rgAgyLocRecordGroup", method = RequestMethod.GET)
	public List<AgencyLocations> rgAgyLocRecordGroup() {
		List<AgencyLocations> recordList = new ArrayList<AgencyLocations>();
		try {
			recordList = ocdclistService.rgAgyLocRecordGroup();
		} catch (Exception e) {
			final AgencyLocations obj = new AgencyLocations();
			logger.error("In method rgAgyLocRecordGroup", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgHearing LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdclist/rgHearingRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgHearingRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = ocdclistService.rgHearingRecordGroup();
		} catch (Exception e) {
			final ReferenceCodes obj = new ReferenceCodes();
			logger.error("In method rgHearingRecordGroup", e);
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
	@RequestMapping(value = "/ocdclist/ctlLstExecuteQuery", method = RequestMethod.POST)
	public List<OcdclistCourtListQuery> ctlLstExecuteQuery(@RequestBody final OcdclistCourtListQuery searchBean) {
		List<OcdclistCourtListQuery> searchResult = new ArrayList<>();
		try {
			searchResult = ocdclistService.ctlLstExecuteQuery(searchBean);
		} catch (Exception e) {
			final OcdclistCourtListQuery bean = new OcdclistCourtListQuery();
			logger.error("In method ctlLstExecuteQuery", e);
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
	@RequestMapping(value = "/ocdclist/ctlLstCommit", method = RequestMethod.POST)
	public @ResponseBody Integer ctlLstCommit(@RequestBody final OcdclistCourtListQueryCommitBean commitBean) {
		int liReturn = 0;
		final String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		commitBean.setCreateUserId(userName);
		try {
			liReturn = ocdclistService.ctlLstCommit(commitBean);
		} catch (Exception e) {
			logger.error("In method ctlUnExecuteQuery", e);
		}
		return liReturn;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdclist/ctlUnExecuteQuery", method = RequestMethod.POST)
	public List<VCourtEvents> ctlUnExecuteQuery(@RequestBody final VCourtEvents searchBean) {
		List<VCourtEvents> searchResult = new ArrayList<>();
		try {
			searchResult = ocdclistService.ctlUnExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error("In method ctlUnExecuteQuery", e);
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
	@RequestMapping(value = "/ocdclist/ctlLstCommitQuery", method = RequestMethod.POST)
	public @ResponseBody Integer ctlLstCommitQuery(@RequestBody final CourtEventsCommitBean commitBean) {
		int liReturn = 0;
		final String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		commitBean.setCreateUserId(userName);
		try {
			liReturn = ocdclistService.ctlLstCommitQuery(commitBean);
		} catch (Exception e) {
			logger.error("In method ctlLstCommitQuery", e);
		}
		return liReturn;
	}

}
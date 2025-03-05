package net.syscon.s4.inst.movementexternal;

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
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.inst.movementexternal.beans.OffenderEscapes;
import net.syscon.s4.inst.movementexternal.beans.OffenderEscapesCommitBean;

/**
 * class OidescapController
 */
@EliteController
public class OidescapController {
	@Autowired
	private OidescapService oidescapService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OidescapController.class.getName());

	/**
	 * getting cgfkOffEscEscapeAgyLocId LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidescap/cgfkOffEscEscapeAgyLocIdRecordGroup", method = RequestMethod.GET)
	public List<AgencyLocations> cgfkOffEscEscapeAgyLocIdRecordGroup() {
		List<AgencyLocations> recordList = new ArrayList<AgencyLocations>();
		final AgencyLocations obj = new AgencyLocations();
		try {
			recordList = oidescapService.cgfkOffEscEscapeAgyLocIdRecordGroup();
		} catch (Exception e) {
			logger.error("In cgfkOffEscEscapeAgyLocIdRecordGroup method : ", e);
			obj.setErrorMessage(e.getMessage());
		}
		return recordList;
	}

	/**
	 * getting cgfkOffEscEscapeEscortCod LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidescap/cgfkOffEscEscapeEscortCodRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> cgfkOffEscEscapeEscortCodRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = oidescapService.cgfkOffEscEscapeEscortCodRecordGroup();
		} catch (Exception e) {
			final ReferenceCodes obj = new ReferenceCodes();
			logger.error("In cgfkOffEscEscapeEscortCodRecordGroup method : ", e);
			obj.setErrorMessage(e.getMessage());
		}
		return recordList;
	}

	/**
	 * getting cgfkOffEscEscapeCircumstan LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidescap/cgfkOffEscEscapeCircumstanRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> cgfkOffEscEscapeCircumstanRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = oidescapService.cgfkOffEscEscapeCircumstanRecordGroup();
		} catch (Exception e) {
			final ReferenceCodes obj = new ReferenceCodes();
			logger.error("In cgfkOffEscEscapeCircumstanRecordGroup method : ", e);
			obj.setErrorMessage(e.getMessage());
		}
		return recordList;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidescap/offEscExecuteQuery", method = RequestMethod.POST)
	public List<OffenderEscapes> offEscExecuteQuery(@RequestBody final OffenderEscapes searchBean) {
		List<OffenderEscapes> searchResult = new ArrayList<>();
		final OffenderEscapes obj = new OffenderEscapes();
		try {
			if (searchBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				searchBean.setCreateUserId(userName);
			}
			searchResult = oidescapService.offEscExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error("In offEscExecuteQuery method : ", e);
			obj.setErrorMessage(e.getMessage());
		}
		return searchResult;
	}

	/**
	 * Performing basic Oracle form functions i.e. insert,delete, update int the
	 * database table
	 * 
	 * @Param commitBean
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/oidescap/offEscCommit", method = RequestMethod.POST)
	public @ResponseBody Integer offEscCommit(@RequestBody final OffenderEscapesCommitBean commitBean) {
		int liReturn = 0;
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		commitBean.setCreateUserId(userName);
		try {
			liReturn = oidescapService.offEscCommit(commitBean);
		} catch (Exception e) {
			logger.error("In offEscCommit method : ", e);
		}
		return liReturn;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidescap/sysPflExecuteQuery", method = RequestMethod.POST)
	public List<SystemProfiles> sysPflExecuteQuery(@RequestBody final SystemProfiles searchBean) {
		List<SystemProfiles> searchResult = new ArrayList<>();
		try {
			searchResult = oidescapService.sysPflExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error("In sysPflExecuteQuery method : ", e);
		}
		return searchResult;
	}

	/**
	 * getting cgfkOffEscSecurityBreachC LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidescap/cgfkOffEscSecurityBreachCRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> cgfkOffEscSecurityBreachCRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		final ReferenceCodes obj = new ReferenceCodes();
		try {
			recordList = oidescapService.cgfkOffEscSecurityBreachCRecordGroup();
		} catch (Exception e) {
			logger.error("In cgfkOffEscSecurityBreachCRecordGroup method : ", e);
			obj.setErrorMessage(e.getMessage());
		}
		return recordList;
	}

	/**
	 * getting cgfkOffEscArrestAgyCode LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidescap/cgfkOffEscArrestAgyCodeRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> cgfkOffEscArrestAgyCodeRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		final ReferenceCodes obj = new ReferenceCodes();
		try {
			recordList = oidescapService.cgfkOffEscArrestAgyCodeRecordGroup();
		} catch (Exception e) {
			logger.error("In cgfkOffEscArrestAgyCodeRecordGroup method : ", e);
			obj.setErrorMessage(e.getMessage());
		}
		return recordList;
	}

	/**
	 * getting max escape date
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidescap/getMaxEscapeDate", method = RequestMethod.GET)
	public Date getMaxEscapeDate(@RequestParam(value = "offenderBookId") final Integer offenderBookId) {
		Date escapeMax = null;
		try {
			escapeMax = oidescapService.getMaxEscapeDate(offenderBookId);
		} catch (Exception e) {
			final OffenderEscapes bean = new OffenderEscapes();
			logger.error("In getMaxEscapeDate method : ", e);
			bean.setErrorMessage(e.getMessage());
		}
		return escapeMax;
	}

}
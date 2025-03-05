package net.syscon.s4.inst.booking.maintainence;

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
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.CaseloadAdmAlertProfiles;
import net.syscon.s4.im.beans.CaseloadAdmOtherProfiles;
import net.syscon.s4.im.beans.LivingUnits;
import net.syscon.s4.im.beans.SystemMessages;
import net.syscon.s4.inst.booking.beans.CaseloadAdmAlertProfilesCommitBean;
import net.syscon.s4.inst.booking.beans.CaseloadAdmOtherProfilesCommitBean;

/**
 * OimadmisController
 */
@EliteController
public class OimadmisController {
	@Autowired
	private OimadmisService oimadmisService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OimadmisController.class.getName());

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oimadmis/caseloadAdmAlertProfilesExecuteQuery", method = RequestMethod.POST)
	public List<CaseloadAdmAlertProfiles> caseloadAdmAlertProfilesExecuteQuery(
			@RequestBody final CaseloadAdmAlertProfiles searchBean) {
		List<CaseloadAdmAlertProfiles> searchResult = new ArrayList<>();
		try {
			searchResult = oimadmisService.caseloadAdmAlertProfilesExecuteQuery(searchBean);
		} catch (final Exception e) {
			final CaseloadAdmAlertProfiles bean = new CaseloadAdmAlertProfiles();
			logger.error("caseloadAdmAlertProfilesExecuteQuery :", e);
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
	@RequestMapping(value = "/oimadmis/caseloadAdmAlertProfilesCommit", method = RequestMethod.POST)
	public @ResponseBody String caseloadAdmAlertProfilesCommit(
			@RequestBody final CaseloadAdmAlertProfilesCommitBean commitBean) {
		String liReturn = "0";
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		commitBean.setCreateUserId(userName);
		try {
			liReturn = oimadmisService.caseloadAdmAlertProfilesCommit(commitBean);
		} catch (final Exception e) {
			liReturn = e.getMessage().toUpperCase();
			logger.error("caseloadAdmAlertProfilesCommit :", e);
		}
		return liReturn;
	}

	/**
	 * getting rgSystemMsg LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oimadmis/rgSystemMsgRecordGroup", method = RequestMethod.GET)
	public List<SystemMessages> rgSystemMsgRecordGroup() {
		List<SystemMessages> recordList = new ArrayList<>();
		try {
			recordList = oimadmisService.rgSystemMsgRecordGroup();
		} catch (final Exception e) {
			final SystemMessages obj = new SystemMessages();
			logger.error("rgSystemMsgRecordGroup :", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgOtherSystemMsg LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oimadmis/rgOtherSystemMsgRecordGroup", method = RequestMethod.GET)
	public List<SystemMessages> rgOtherSystemMsgRecordGroup() {
		List<SystemMessages> recordList = new ArrayList<>();
		try {
			recordList = oimadmisService.rgOtherSystemMsgRecordGroup();
		} catch (final Exception e) {
			final SystemMessages obj = new SystemMessages();
			logger.error("rgOtherSystemMsgRecordGroup :", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgAgencyLocations LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oimadmis/rgAgencyLocationsRecordGroup", method = RequestMethod.GET)
	public List<AgencyLocations> rgAgencyLocationsRecordGroup(@RequestParam("caseloadId") final String caseloadId) {
		List<AgencyLocations> recordList = new ArrayList<>();
		try {
			recordList = oimadmisService.rgAgencyLocationsRecordGroup(caseloadId);
		} catch (final Exception e) {
			final AgencyLocations obj = new AgencyLocations();
			logger.error("Exception :", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgLivingUnits LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oimadmis/rgLivingUnitsRecordGroup", method = RequestMethod.GET)
	public List<LivingUnits> rgLivingUnitsRecordGroup() {
		List<LivingUnits> recordList = new ArrayList<>();
		try {
			recordList = oimadmisService.rgLivingUnitsRecordGroup();
		} catch (final Exception e) {
			final LivingUnits obj = new LivingUnits();
			logger.error("rgLivingUnitsRecordGroup :", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgAlert LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oimadmis/rgAlertRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgAlertRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<>();
		try {
			recordList = oimadmisService.rgAlertRecordGroup();
		} catch (final Exception e) {
			final ReferenceCodes obj = new ReferenceCodes();
			logger.error("rgAlertRecordGroup :", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgAlertCode LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oimadmis/rgAlertCodeRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgAlertCodeRecordGroup(@RequestParam("alerType") final String alerType) {
		List<ReferenceCodes> recordList = new ArrayList<>();
		try {
			recordList = oimadmisService.rgAlertCodeRecordGroup(alerType);
		} catch (final Exception e) {
			final ReferenceCodes obj = new ReferenceCodes();
			logger.error("Exception :", e);
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
	@RequestMapping(value = "/oimadmis/caseloadAdmOtherProfilesExecuteQuery", method = RequestMethod.POST)
	public List<CaseloadAdmOtherProfiles> caseloadAdmOtherProfilesExecuteQuery(
			@RequestBody final CaseloadAdmOtherProfiles searchBean) {
		List<CaseloadAdmOtherProfiles> searchResult = new ArrayList<>();
		try {
			searchResult = oimadmisService.caseloadAdmOtherProfilesExecuteQuery(searchBean);
		} catch (final Exception e) {
			final CaseloadAdmOtherProfiles bean = new CaseloadAdmOtherProfiles();
			logger.error("caseloadAdmOtherProfilesExecuteQuery :", e);
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
	@RequestMapping(value = "/oimadmis/caseloadAdmOtherProfilesCommit", method = RequestMethod.POST)
	public @ResponseBody String caseloadAdmOtherProfilesCommit(
			@RequestBody final CaseloadAdmOtherProfilesCommitBean commitBean) {
		String liReturn = "0";
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		commitBean.setCreateUserId(userName);
		try {
			liReturn = oimadmisService.caseloadAdmOtherProfilesCommit(commitBean);
		} catch (final Exception e) {
			logger.error("caseloadAdmOtherProfilesCommit :", e);
			liReturn = e.getMessage().toUpperCase();
		}
		return liReturn;
	}

}
package net.syscon.s4.inst.incidentsoic;

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

import net.syscon.s4.common.EliteController;
import net.syscon.s4.common.beans.OicOffences;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.im.incidentsoic.beans.OicHearingResults;
import net.syscon.s4.inst.incidentsoic.maintenance.OffenderOicAppealIncidents;
import net.syscon.s4.inst.incidentsoic.maintenance.OffenderOicAppealIncidentsCommitBean;
import net.syscon.s4.inst.incidentsoic.maintenance.OffenderOicAppealPenalties;
import net.syscon.s4.inst.incidentsoic.maintenance.OffenderOicAppealPenaltiesCommitBean;
import net.syscon.s4.inst.incidentsoic.maintenance.OffenderOicAppeals;
import net.syscon.s4.inst.incidentsoic.maintenance.OffenderOicAppealsCommitBean;

/**
 * @author Arkin Software Technologies
 * @version 1.0
 */
@EliteController
public class OidoicapController {
	@Autowired
	private OidoicapService oidoicapService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OidoicapController.class.getName());

	/**
	 * getting rgHearingOffences LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidoicap/rgHearingOffencesRecordGroup", method = RequestMethod.GET)
	public List<OicHearingResults> rgHearingOffencesRecordGroup(@RequestParam int offenderBookingId) {
		List<OicHearingResults> recordList = new ArrayList<OicHearingResults>();
		try {
			recordList = oidoicapService.rgHearingOffencesRecordGroup(offenderBookingId);
		} catch (Exception e) {
			OicHearingResults obj = new OicHearingResults();
			logger.error("Exception :", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgOicSeqLog LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidoicap/rgOicSeqLogRecordGroup", method = RequestMethod.POST)
	public List<OffenderOicAppealPenalties> rgOicSeqLogRecordGroup(
			@RequestBody OffenderOicAppealPenalties searchRecord) {
		List<OffenderOicAppealPenalties> recordList = new ArrayList<OffenderOicAppealPenalties>();
		try {
			recordList = oidoicapService.rgOicSeqLogRecordGroup(searchRecord);
		} catch (Exception e) {
			OffenderOicAppealPenalties obj = new OffenderOicAppealPenalties();
			logger.error("Exception :", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgHeardBy LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidoicap/rgHeardByRecordGroup", method = RequestMethod.GET)
	public List<StaffMembers> rgHeardByRecordGroup() {
		List<StaffMembers> recordList = new ArrayList<StaffMembers>();
		try {
			recordList = oidoicapService.rgHeardByRecordGroup();
		} catch (Exception e) {
			StaffMembers obj = new StaffMembers();
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
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidoicap/offOicaExecuteQuery", method = RequestMethod.POST)
	public List<OffenderOicAppeals> offOicaExecuteQuery(@RequestBody OffenderOicAppeals searchBean) {
		List<OffenderOicAppeals> searchResult = new ArrayList<>();
		try {
			searchResult = oidoicapService.offOicaExecuteQuery(searchBean);
		} catch (Exception e) {
			OffenderOicAppeals bean = new OffenderOicAppeals();
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
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/oidoicap/offOicaCommit", method = RequestMethod.POST)
	public Integer offOicaCommit(@RequestBody OffenderOicAppealsCommitBean commitBean) {
		int liReturn = 0;
		try {
			String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			commitBean.setCreateUserId(userName);
			liReturn = oidoicapService.offOicaCommit(commitBean);
		} catch (Exception e) {

			logger.error("Exception :", e);
		}
		return liReturn;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidoicap/offOicaiExecuteQuery", method = RequestMethod.POST)
	public List<OffenderOicAppealIncidents> offOicaiExecuteQuery(@RequestBody OffenderOicAppealIncidents searchBean) {
		List<OffenderOicAppealIncidents> searchResult = new ArrayList<>();
		try {
			searchResult = oidoicapService.offOicaiExecuteQuery(searchBean);
		} catch (Exception e) {
			OffenderOicAppealIncidents bean = new OffenderOicAppealIncidents();
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
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/oidoicap/offOicaiCommit", method = RequestMethod.POST)
	public Integer offOicaiCommit(@RequestBody OffenderOicAppealIncidentsCommitBean commitBean) {
		int liReturn = 0;
		try {
			String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			commitBean.setCreateUserId(userName);
			liReturn = oidoicapService.offOicaiCommit(commitBean);
		} catch (Exception e) {

			logger.error("Exception :", e);
		}
		return liReturn;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidoicap/offOicapExecuteQuery", method = RequestMethod.POST)
	public List<OffenderOicAppealPenalties> offOicapExecuteQuery(@RequestBody OffenderOicAppealPenalties searchBean) {
		List<OffenderOicAppealPenalties> searchResult = new ArrayList<>();
		try {
			searchResult = oidoicapService.offOicapExecuteQuery(searchBean);
		} catch (Exception e) {
			OffenderOicAppealPenalties bean = new OffenderOicAppealPenalties();
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
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/oidoicap/offOicapCommit", method = RequestMethod.POST)
	public Integer offOicapCommit(@RequestBody OffenderOicAppealPenaltiesCommitBean commitBean) {
		int liReturn = 0;
		try {
			String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			commitBean.setCreateUserId(userName);
			liReturn = oidoicapService.offOicapCommit(commitBean);
		} catch (Exception e) {

			logger.error("Exception :", e);
		}
		return liReturn;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidoicap/butOriginalPenalty", method = RequestMethod.POST)
	public String butOriginalPenalty(@RequestBody OffenderOicAppealIncidents searchBean) {
		String searchResult = null;
		try {
			searchResult = oidoicapService.butOriginalPenalty(searchBean);
		} catch (Exception e) {

			logger.error("butOriginalPenalty :", e);
		}
		return searchResult;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidoicap/getoffencedetails", method = RequestMethod.POST)
	public String getoffencedetails(@RequestBody OffenderOicAppealIncidents searchBean) {
		String searchResult = null;
		try {
			searchResult = oidoicapService.getoffencedetails(searchBean);
		} catch (Exception e) {

			logger.error("butOriginalPenalty :", e);
		}
		return searchResult;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidoicap/getOicOffenceCodeCur", method = RequestMethod.GET)
	public OicOffences getOicOffenceCodeCur(@RequestParam Integer oicOffenceId) {
		OicOffences searchResult = new OicOffences();
		try {
			searchResult = oidoicapService.getOicOffenceCodeCur(oicOffenceId);
		} catch (Exception e) {

			logger.error("getOicOffenceCodeCur :", e);
		}
		return searchResult;
	}

}

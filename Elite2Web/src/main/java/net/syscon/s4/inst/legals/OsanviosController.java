package net.syscon.s4.inst.legals;

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
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.inst.legals.beans.Court;
import net.syscon.s4.inst.legals.beans.CourtEvnetAppointmentOutcome;
import net.syscon.s4.inst.legals.beans.CourtEvnetAppointmentOutcomeCommitBean;
import net.syscon.s4.inst.legals.beans.OffenderCommunitySentense;
import net.syscon.s4.inst.schedules.bean.CourtEvents;
import net.syscon.s4.inst.schedules.bean.CourtEventsCommitBean;

@EliteController
public class OsanviosController {

	private static Logger logger = LogManager.getLogger(OidcrtevController.class.getName());
	@Autowired
	private OsanviosService osanviosService;

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/osanvios/hearingreasonRecordGroup", method = RequestMethod.GET)
	public List<AgencyLocations> hearingreasonRecordGroup() {
		List<AgencyLocations> recordList = new ArrayList<>();
		try {
			recordList = osanviosService.hearingreasonRecordGroup();
		} catch (Exception e) {
		}
		return recordList;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/osanvios/populateCourtData", method = RequestMethod.GET)
	public List<Court> populateCourtData() {
		List<Court> courtList = new ArrayList<Court>();
		try {
			courtList = osanviosService.populateCourtData();
		} catch (Exception e) {
		}
		return courtList;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/osanvios/offCourtEventsDataRetrieve", method = RequestMethod.POST)
	public List<CourtEvents> offCourtEventsDataRetrieve(@RequestBody final CourtEvents searchBean) {
		List<CourtEvents> searchResult = new ArrayList<>();
		final CourtEvents bean = new CourtEvents();
		try {
			searchResult = osanviosService.crtEveExecuteQuery(searchBean);
		} catch (Exception e) {
			bean.setErrorMessage(e.getMessage());
		}
		return searchResult;
	}

	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/osanvios/courtEventCommit", method = RequestMethod.POST)
	public @ResponseBody Integer crtEveCommit(@RequestBody final CourtEventsCommitBean commitBean) {
		int liReturn = 0;
		try {
			if (commitBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				commitBean.setCreateUserId(userName);
			}
			liReturn = osanviosService.crtEveCommit(commitBean);
		} catch (Exception e) {
		}
		return liReturn;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/osanvios/retriveSentenceData", method = RequestMethod.POST)
	public List<OffenderCommunitySentense> retriveSentenceData(
			@RequestBody final OffenderCommunitySentense searchBean) {
		List<OffenderCommunitySentense> searchResult = new ArrayList<>();
		final OffenderCommunitySentense bean = new OffenderCommunitySentense();
		try {
			searchResult = osanviosService.retriveSentenceData(searchBean);
		} catch (Exception e) {
			bean.setErrorMessage(e.getMessage());
		}
		return searchResult;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/osanvios/appointMentsData", method = RequestMethod.POST)
	public List<CourtEvnetAppointmentOutcome> appointMentsData(
			@RequestBody final CourtEvnetAppointmentOutcome searchBean) {
		List<CourtEvnetAppointmentOutcome> searchResult = new ArrayList<>();
		final CourtEvnetAppointmentOutcome bean = new CourtEvnetAppointmentOutcome();
		try {
			searchResult = osanviosService.appointMentsData(searchBean);
		} catch (Exception e) {
			bean.setErrorMessage(e.getMessage());
		}
		return searchResult;
	}

	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/osanvios/crtEventAppointmentCommit", method = RequestMethod.POST)
	public @ResponseBody Integer crtEventAppointmentCommit(
			@RequestBody final CourtEvnetAppointmentOutcomeCommitBean commitBean) {
		int liReturn = 0;
		try {
			if (commitBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				commitBean.setCreateUserId(userName);
			}
			liReturn = osanviosService.crtEventAppointmentCommit(commitBean);
		} catch (Exception e) {
		}
		return liReturn;
	}

	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/osanvios/getDefaultCancellationReason", method = RequestMethod.GET)
	public String getDefaultCancellationReason() {
		String cancelReason = "";
		try {
			cancelReason = osanviosService.getDefaultCancellationReason();
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in getDefaultCancellationReason : ", e);
		}
		return cancelReason;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/osanvios/progOutComeEecuteQuery", method = RequestMethod.POST)
	public List<CourtEvnetAppointmentOutcome> progOutComeEecuteQuery(
			@RequestBody final CourtEvnetAppointmentOutcome searchBean) {
		List<CourtEvnetAppointmentOutcome> searchResult = new ArrayList<>();
		final CourtEvnetAppointmentOutcome bean = new CourtEvnetAppointmentOutcome();
		try {
			searchResult = osanviosService.progOutComeEecuteQuery(searchBean);
		} catch (Exception e) {
			bean.setErrorMessage(e.getMessage());
		}
		return searchResult;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/osanvios/progAppointmentEecuteQuery", method = RequestMethod.POST)
	public List<CourtEvnetAppointmentOutcome> progAppointmentEecuteQuery(
			@RequestBody final CourtEvnetAppointmentOutcome searchBean) {
		List<CourtEvnetAppointmentOutcome> searchResult = new ArrayList<>();
		final CourtEvnetAppointmentOutcome bean = new CourtEvnetAppointmentOutcome();
		try {
			searchResult = osanviosService.progAppointmentEecuteQuery(searchBean);
		} catch (Exception e) {
			bean.setErrorMessage(e.getMessage());
		}
		return searchResult;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/osanvios/comServiceEecuteQuery", method = RequestMethod.POST)
	public List<CourtEvnetAppointmentOutcome> comServiceEecuteQuery(
			@RequestBody final CourtEvnetAppointmentOutcome searchBean) {
		List<CourtEvnetAppointmentOutcome> searchResult = new ArrayList<>();
		final CourtEvnetAppointmentOutcome bean = new CourtEvnetAppointmentOutcome();
		try {
			searchResult = osanviosService.comServiceEecuteQuery(searchBean);
		} catch (Exception e) {
			bean.setErrorMessage(e.getMessage());
		}
		return searchResult;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/osanvios/populateLoggedStaffName", method = RequestMethod.GET)
	public String populateLoggedStaffName() {
			String staffName="";
		try {
			String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			staffName = osanviosService.populateLoggedStaffName(userName);
			}
		catch (Exception e) {
			logger.error("populateStaffName", e);
		}
		return staffName;
	}
}

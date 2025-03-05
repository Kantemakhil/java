package net.syscon.s4.inst.legals;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import net.syscon.s4.common.EliteController;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.inst.legals.beans.Court;
import net.syscon.s4.inst.schedules.OidsiappService;
import net.syscon.s4.inst.schedules.bean.CourtEvents;
import net.syscon.s4.inst.schedules.bean.CourtEventsCommitBean;
import net.syscon.s4.inst.schedules.bean.VOffenderAllSchedules;
import net.syscon.s4.sa.recordmaintenance.ProsmainService;

@EliteController
public class OidcrtevController {

	private static Logger logger = LogManager.getLogger(OidcrtevController.class.getName());

	@Autowired
	private OidcrtevService oidcrtevService;
	
	@Autowired
	private ProsmainService prosmainService;
	
	@Autowired
	private OidsiappService oidsiappService;

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidcrtev/courtEveExecuteQuery", method = RequestMethod.POST)
	public List<CourtEvents> courtEveExecuteQuery(@RequestBody final CourtEvents searchBean) {
		logger.info(
				this.getClass().getName() + " courtEveExecuteQuery Start " + searchBean != null ? searchBean.toString()
						: null);
		List<CourtEvents> searchResult = new ArrayList<>();
		final CourtEvents bean = new CourtEvents();
		try {
			searchResult = oidcrtevService.crtEveExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + "error in courtEveExecuteQuery method : ", e);
			bean.setErrorMessage(e.getMessage());
		}
		return searchResult;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidcrtev/populateCrtData", method = RequestMethod.GET)
	public List<Court> populateCourtData() {
		logger.info(this.getClass().getName() + " populateCourtData Start");
		List<Court> courtList = new ArrayList<Court>();
		try {
			courtList = oidcrtevService.populateCourtData();
		} catch (Exception e) {
			logger.error(this.getClass().getName() + "error in populateCourtData", e);
		}
		return courtList;
	}

	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/oidcrtev/courtEventCommit", method = RequestMethod.POST)
	public @ResponseBody Integer crtEveCommit(@RequestBody final CourtEventsCommitBean commitBean, @RequestHeader HttpHeaders headers) {
		int liReturn = 0;
		List<String> authorizationList = headers.get("Authorization");
		String authorization = authorizationList.get(0).split(",")[0];
		try {
			if (commitBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				commitBean.setCreateUserId(userName);
			}
			liReturn = oidcrtevService.crtEveCommit(commitBean);
			if(0 != liReturn ) {
				prosmainService.enableTriggers(commitBean, authorization, "90");
			}
		} catch (ArithmeticException e) {
			liReturn = 2;
			return liReturn;
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in crtEveCommit : ", e);
		}
		return liReturn;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidcrtev/hearingreasonRecordGroup", method = RequestMethod.GET)
	public List<AgencyLocations> hearingreasonRecordGroup() {
		logger.info(this.getClass().getName() + " hearingreasonRecordGroup start");
		List<AgencyLocations> recordList = new ArrayList<>();
		try {
			recordList = oidcrtevService.hearingreasonRecordGroup();
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in hearingreason", e);
		}
		return recordList;
	}

	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/oidcrtev/apperancelocationRecordGroup", method = RequestMethod.GET)
	public List<AgencyLocations> apperancelocationRecordGroup(@RequestParam("caseLoadId") String agyLocId) {
		logger.info(this.getClass().getName() + " apperancelocationRecordGroup start " + agyLocId != null ? agyLocId
				: null);
		List<AgencyLocations> recordList = new ArrayList<>();
		try {
			recordList = oidcrtevService.apperancelocationRecordGroup(agyLocId);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in apperancelocationRecordGroup", e);
		}
		return recordList;
	}

	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/oidcrtev/checkNonAssociations", method = RequestMethod.POST)
	public String serarchNonAssociationOffender(@RequestBody final CourtEventsCommitBean commitBean) {
		logger.info(this.getClass().getName() + commitBean != null ? commitBean.toString() : null);
		String liReturn = "";
		try {
			if (commitBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				commitBean.setCreateUserId(userName);
			}
			liReturn = oidcrtevService.checkNonAssociations(commitBean);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in crtEveCommit : ", e);
		}
		return liReturn;
	}

	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/oidcrtev/getDefaultCancellationReason", method = RequestMethod.GET)
	public String getDefaultCancellationReason() {
		String cancelReason = "";
		try {
			cancelReason = oidcrtevService.getDefaultCancellationReason();
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in getDefaultCancellationReason : ", e);
		}
		return cancelReason;
	}
	
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/oidcrtev/phoneNumberAndEmailCheck", method = RequestMethod.GET)
	public CourtEvents phoneNumberAndEmailCheck(
			@RequestParam(value = "offenderBookId") final BigDecimal offenderBookId) {
		CourtEvents emailAndPhoneNumberCheck = new CourtEvents();
		try {
			emailAndPhoneNumberCheck = oidcrtevService.phoneNumberAndEmailCheck(offenderBookId);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in phoneNumberAndEmailCheck : ", e);
		}
		return emailAndPhoneNumberCheck;
	}
	
	/**
	 * Before inserting the record it verifying whether any other schedules are
	 * assigned to the offender
	 *
	 * @param vOffAllSch {@link VOffenderAllSchedules}
	 * @return success/failure of the insert/update {@link Integer} for the matching
	 *         passed data
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidcrtev/checkScheduleConflict", method = RequestMethod.POST)
	public @ResponseBody Integer checkScheduleConflict(@RequestBody final VOffenderAllSchedules vOffAllSch) {
		int liReturn = 0;
		try {
			liReturn = oidsiappService.checkScheduleConflict(vOffAllSch);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + "In checkScheduleConflict method : ", e);
		}
		return liReturn;
	}

}

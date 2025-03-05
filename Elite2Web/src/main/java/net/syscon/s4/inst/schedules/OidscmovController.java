package net.syscon.s4.inst.schedules;

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
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.CourseActivities;
import net.syscon.s4.im.beans.MovementReasons;
import net.syscon.s4.inst.schedules.bean.CourtEvents;
import net.syscon.s4.inst.schedules.bean.CourtEventsCommitBean;

/**
 * class OidscmovController
 */
@EliteController
public class OidscmovController {
	@Autowired
	private OidscmovService oidscmovService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OidscmovController.class.getName());

	/**
	 * getting rgCtrlInst LOV values
	 * @param caseloadId {@link String}
	 * @return a list of the AgencyLocations {@link AgencyLocations} for the matched caseloadId
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidscmov/rgCtrlInstRecordGroup", method = RequestMethod.GET)
	public List<AgencyLocations> rgCtrlInstRecordGroup(@RequestParam(value = "caseloadId") final String caseloadId) {
		List<AgencyLocations> recordList = new ArrayList<AgencyLocations>();
		final AgencyLocations bean = new AgencyLocations();
		try {
			recordList = oidscmovService.rgCtrlInstRecordGroup(caseloadId);
		} catch (Exception e) {
			logger.error("In rgCtrlInstRecordGroup method : ", e);
			bean.setErrorMessage(e.getMessage());
		}
		return recordList;
	}

	/**
	 * getting rgCtrlReason LOV values
	 * @return a list of the MovementReasons {@link MovementReasons}
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidscmov/rgCtrlReasonRecordGroup", method = RequestMethod.GET)
	public List<MovementReasons> rgCtrlReasonRecordGroup() {
		List<MovementReasons> recordList = new ArrayList<MovementReasons>();
		final MovementReasons bean = new MovementReasons();
		try {
			recordList = oidscmovService.rgCtrlReasonRecordGroup();
		} catch (Exception e) {
			logger.error("In rgCtrlReasonRecordGroup method : ", e);
			bean.setErrorMessage(e.getMessage());
		}
		return recordList;
	}

	/**
	 * getting rgCtrlCourt LOV values
	 * @return a list of the AgencyLocations {@link AgencyLocations}
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidscmov/rgCtrlCourtRecordGroup", method = RequestMethod.GET)
	public List<AgencyLocations> rgCtrlCourtRecordGroup() {
		List<AgencyLocations> recordList = new ArrayList<AgencyLocations>();
		final AgencyLocations obj = new AgencyLocations();
		try {
			recordList = oidscmovService.rgCtrlCourtRecordGroup();
		} catch (Exception e) {
			logger.error("In rgCtrlCourtRecordGroup method : ", e);
			obj.setErrorMessage(e.getMessage());
		}
		return recordList;
	}

	/**
	 * getting rgCourtRea LOV values
	 * @return a list of the MovementReasons {@link MovementReasons}
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidscmov/rgCourtReaRecordGroup", method = RequestMethod.GET)
	public List<MovementReasons> rgCourtReaRecordGroup() {
		List<MovementReasons> recordList = new ArrayList<MovementReasons>();
		final MovementReasons obj = new MovementReasons();
		try {
			recordList = oidscmovService.rgCourtReaRecordGroup();
		} catch (Exception e) {
			logger.error("In rgCourtReaRecordGroup method : ", e);
			obj.setErrorMessage(e.getMessage());
		}
		return recordList;
	}

	/**
	 * Fetching the record from database table
	 *
	 * @param searchBean {@link CourtEvents}
	 * @return a list of the CourtEvents {@link CourtEvents} for the matched CourtEvents
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidscmov/crtEveExecuteQuery", method = RequestMethod.POST)
	public List<CourtEvents> crtEveExecuteQuery(@RequestBody final CourtEvents searchBean) {
		List<CourtEvents> searchResult = new ArrayList<>();
		final CourtEvents bean = new CourtEvents();
		try {
			String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			searchBean.setCreateUserId(userName);
			searchResult = oidscmovService.crtEveExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error("In crtEveExecuteQuery method : ", e);
			bean.setErrorMessage(e.getMessage());
		}
		return searchResult;
	}

	/**
	 * Performing basic Oracle form functions i.e. insert,delete, update into
	 * the database table
	 *
	 * @param commitBean {@link CourtEventsCommitBean}
	 * @return success/failure of the insert/update {@link Integer} for the matching CourtEventsCommitBean
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/oidscmov/crtEveCommit", method = RequestMethod.POST)
	public @ResponseBody Integer crtEveCommit(@RequestBody final CourtEventsCommitBean commitBean) {
		int liReturn = 0;
		try {
			if (commitBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				commitBean.setCreateUserId(userName);
			}
			liReturn = oidscmovService.crtEveCommit(commitBean);
		} catch(NullPointerException e) {
			liReturn = 3;
			return liReturn;
		} catch (ArithmeticException e) {
			liReturn = 2;
			return liReturn;
		}catch (Exception e) {
			logger.error("In crtEveCommit method : ", e);
		}
		return liReturn;
	}

	/**
	 *
	 * @return the CurrentDate {@link Date}
	 */
	@RequestMapping(value = "/oidscmov/getCurrentDate", method = RequestMethod.GET)
	public Date getCurrentDate() {
		Date value = null;
		try {
			value = oidscmovService.getCurrentDate();
		} catch (Exception e) {
			logger.error("getCurrentDate", e);
		}
		return value;
	}

	/**
	 * Before inserting the record it verifying whether any other schedules are
	 * assigned to the offender
	 *
	 * @param offCourts {@link CourtEvents}
	 * @return success/failure of the insert/update {@link Integer} for the matching passed data
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidscmov/checkScheduleConflict", method = RequestMethod.POST)
	public @ResponseBody Integer checkScheduleConflict(@RequestBody final CourtEvents offCourts) {
		int liReturn = 0;
		try {
			liReturn = oidscmovService.checkScheduleConflict(offCourts);
		} catch (Exception e) {
			logger.error("In checkScheduleConflict method : ", e);
		}
		return liReturn;
	}

	/**
	 * Retrieves offender details for the given offender display Id , global caseload id and agyloc Id
	 * @param nbtOffDisplayId {@link String}
	 * @param agyLocId {@link String}
	 * @param caseloadId {@link String}
	 * @return a list of the CourtEvents {@link CourtEvents} for the matched agency location Id, nbtOffDisplayId and
	 * caseloadId
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidscmov/getOffenderDetails", method = RequestMethod.GET)
	public List<CourtEvents> getOffenderDetails(@RequestParam(value = "nbtOffDisplayId") final String nbtOffDisplayId,
			@RequestParam(value = "agyLocId") final String agyLocId,
			@RequestParam(value = "caseloadId") final String caseloadId) {
		List<CourtEvents> courtObj = new ArrayList<CourtEvents>();
		try {
			courtObj = oidscmovService.getOffenderDetails(nbtOffDisplayId, agyLocId, caseloadId);
		} catch (Exception e) {
			logger.error("In getOffenderDetails method : ", e);
		}
		return courtObj;
	}
	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidscmov/getChkNaConflictFlag", method = RequestMethod.POST)
	public Boolean getChkNaConflictFlag(@RequestBody CourtEvents courtEvents) {
		Boolean courtObj = null;
		try {
			courtObj = oidscmovService.getChkNaConflictFlag(courtEvents.getOffenderBookId(), courtEvents.getAgyLocId(), courtEvents.getEventDate());
		} catch (Exception e) {
			logger.error("In getChkNaConflictFlag method : ", e);
		}
		return courtObj;
	}
	
	
	@RequestMapping(value = "/oidscmov/getNonAssociationWarnings", method = RequestMethod.POST)
	public List<CourseActivities> getNonAssociationWarnings(@RequestBody List<CourseActivities> courtEvents) {
		List<CourseActivities> courtObj = null;
		try {
			courtObj = oidscmovService.getNonAssociationWarnings(courtEvents);
		} catch (Exception e) {
			logger.error("In getNonAssociationWarnings method : ", e);
		}
		return courtObj;
	}
	
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/oidscmov/checkNonAssociations", method = RequestMethod.POST)
	public CourtEventsCommitBean serarchNonAssociationOffender(@RequestBody final CourtEventsCommitBean commitBean) {
		logger.info(this.getClass().getName() + commitBean != null ? commitBean.toString() : null);
		CourtEventsCommitBean returnCommitBean = new CourtEventsCommitBean();
		try {
			if (commitBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				commitBean.setCreateUserId(userName);
			}
			returnCommitBean =oidscmovService.getNonAssociationWarningsForINPOrVIDOrOME(commitBean);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in serarchNonAssociationOffender : ", e);
		}
		return returnCommitBean;
	}
}
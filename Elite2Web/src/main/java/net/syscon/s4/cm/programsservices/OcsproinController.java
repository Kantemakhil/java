package net.syscon.s4.cm.programsservices;

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
import net.syscon.s4.common.beans.OffenderBookings;
import net.syscon.s4.im.beans.CourseActivities;
import net.syscon.s4.im.beans.Teams;

@EliteController
public class OcsproinController {

	@Autowired
	private OcsproinService ocsproinService;

	private static Logger logger = LogManager.getLogger(OcsproinController.class.getName());

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocsproin/rgTeamRecordGroup", method = RequestMethod.GET)
	public List<Teams> rgTeamRecordGroup() {
		List<Teams> recordList = new ArrayList<Teams>();
		try {
			recordList = ocsproinService.rgTeamRecordGroup(
					SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
		} catch (Exception e) {
			Teams obj = new Teams();
			logger.error("Exception :", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocsproin/rgProjectsRecordGroup", method = RequestMethod.GET)
	public List<CourseActivities> rgProjectsRecordGroup(@RequestParam("teamId") final String teamId) {
		List<CourseActivities> recordList = new ArrayList<>();
		try {
			recordList = ocsproinService.rgProjectsRecordGroup(teamId);
		} catch (final Exception e) {
			final CourseActivities obj = new CourseActivities();
			logger.error("rgProjectsRecordGroup :", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocsproin/courseActExecuteQuery", method = RequestMethod.POST)
	public List<CourseActivities> courseActExecuteQuery(@RequestBody CourseActivities searchBean) {
		List<CourseActivities> searchResult = new ArrayList<>();
		try {
			searchResult = ocsproinService.courseActExecuteQuery(searchBean);
		} catch (Exception e) {
			CourseActivities bean = new CourseActivities();
			logger.error("Exception :", e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocsproin/rgProjectsRgNoTeam", method = RequestMethod.GET)
	public List<CourseActivities> rgProjectsRgNoTeam() {
		List<CourseActivities> recordList = new ArrayList<>();
		try {
			recordList = ocsproinService.rgProjectsRgNoTeam();
		} catch (final Exception e) {
			final CourseActivities obj = new CourseActivities();
			logger.error("rgProjectsRecordGroup :", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocsproin/offenderExecQuery", method = RequestMethod.GET)
	public List<OffenderBookings> offenderExecQuery(@RequestParam Integer crsActyId) {
		List<OffenderBookings> recordList = new ArrayList<OffenderBookings>();
		try {
			recordList = ocsproinService.offenderExecQuery(crsActyId);
		} catch (Exception e) {
			logger.error("Exception in Class " + this.getClass().getName() + "offenderExecQuery :" + e.getMessage());
		}
		return recordList;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocsproin/referredExeQuery", method = RequestMethod.POST)
	public List<OffenderBookings> refExecuteQuery(@RequestBody CourseActivities searchBean) {
		List<OffenderBookings> searchResult = new ArrayList<>();
		try {
			//Communityserviceenquiry Referred offenders executequery 
			searchResult = ocsproinService.refExecuteQuery(searchBean);

		} catch (Exception e) {
			OffenderBookings bean = new OffenderBookings();
			logger.error("Exception :", e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}
}

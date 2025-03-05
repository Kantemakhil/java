package net.syscon.s4.cm.programsservices.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.cm.casemanagement.maintenance.beans.EventMeasureOutcomes;
import net.syscon.s4.cm.casemanagement.maintenance.beans.EventMeasures;
import net.syscon.s4.cm.programsservices.CourseScheduleStaff;
import net.syscon.s4.cm.programsservices.CourseScheduleStaffsCommitBean;
import net.syscon.s4.cm.programsservices.OcdpatteCommitBean;
import net.syscon.s4.cm.programsservices.OcdpatteRepository;
import net.syscon.s4.cm.programsservices.OcdpatteService;
import net.syscon.s4.cm.programsservices.OffenderCourseAttendancesCommitBean;
import net.syscon.s4.cm.programsservices.VAcpSchedules;
import net.syscon.s4.cm.teamsworkflow.beans.TeamMembers;
import net.syscon.s4.cm.weeklyactivityplans.WeeklyActivityPlans;
import net.syscon.s4.common.ApplicationConstants;
import net.syscon.s4.common.beans.OffenderCourseAttendance;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.CourseActivities;
import net.syscon.s4.im.beans.Offenders;
import net.syscon.s4.im.beans.VAddresses;
import net.syscon.s4.im.beans.VOffenderCourseAttendances;
import net.syscon.s4.inst.casemanagement.beans.ProgramServices;
import net.syscon.s4.inst.institutionalactivities.maintenance.OcmpssetService;
import net.syscon.s4.inst.institutionalactivities.maintenance.beans.CourseSchedules;
import net.syscon.s4.inst.institutionalactivities.maintenance.beans.CourseSchedulesCommitBean;
import net.syscon.s4.inst.legals.beans.VOffenderSentenceEvents;
import net.syscon.s4.inst.schedules.bean.VOffenderAllSchedules;
import net.syscon.s4.iwp.impl.OcdclogsServiceImpl;
import net.syscon.s4.pkgs.ocdpatte.OcdpattePkgService;
import net.syscon.s4.pkgs.ocdtmrev.OcdtmrevPkgService;
import net.syscon.s4.pkgs.tag_multiple_failure.TagMultipleFailureService;
import net.syscon.s4.triggers.OffenderCourseAttendances;
import net.syscon.s4.triggers.OffenderCourseAttendancesT1Service;
import net.syscon.s4.triggers.OffenderCourseAttndnsTwfService;

/**
 * Class OcdpatteServiceImpl
 */
@Service
public class OcdpatteServiceImpl extends BaseBusiness implements OcdpatteService {

	@Autowired
	private OcdpatteRepository ocdpatteRepository;
	@Autowired
	private TagMultipleFailureService tagMultipleFailureService;
	@Autowired
	private OcdpattePkgService ocdpattePkgService;
	@Autowired
	private OcdtmrevPkgService ocdtmrevPkgService;
	@Autowired
	private OffenderCourseAttendancesT1Service offenderCourseAttendancesT1Service;
	@Autowired
	private OffenderCourseAttndnsTwfService offenderCourseAttndnsTwfService;
	@Autowired
	private OcmpssetService ocmpssetService;
	
	@Autowired
	private OcdclogsServiceImpl ocdclogsServiceImpl;

	private static Logger logger = LogManager.getLogger(OcdpatteServiceImpl.class.getName());

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 */
	public List<OffenderCourseAttendance> offCourseAttendancesExecuteQuery(
			final OffenderCourseAttendance searchRecord) {
		final List<OffenderCourseAttendance> offcoursAttList = ocdpatteRepository
				.offCourseAttendancesExecuteQuery(searchRecord);
		for (final OffenderCourseAttendance offCourseAtt : offcoursAttList) {
			final Offenders details = ocdpatteRepository.getOffenderDetails(offCourseAtt);
			offCourseAtt.setOffenderName(details.getLastName() + ", " + details.getFirstName());
			offCourseAtt.setOffenderIdDisplay(details.getOffenderIdDisplay());
			if (offCourseAtt.getEventStatus() != null) {
				offCourseAtt.setEventStatus("COMP");
			}
			if (searchRecord.getCaseloadType().equalsIgnoreCase("INST")) {
				final String provType = getproviderType(searchRecord.getCaseloadType());
				if (ApplicationConstants.YES.equals(provType)) {
					final String paylockflag = ocdpattePkgService.getPaylock(offCourseAtt.getEventId());
					offCourseAtt.setPayLockFlag(paylockflag);
				}
			}
		}
		return offcoursAttList;

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstOFF_COURSE_ATTENDANCES
	 *
	 */
	@Transactional
	public Integer offCourseAttendancesCommit(final OffenderCourseAttendancesCommitBean commitBean) {
		int liReturn = 0;
		OffenderCourseAttendances attendence=new OffenderCourseAttendances();
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			commitBean.getUpdateList().forEach(action-> action.setModifyUserId(commitBean.getCreateUserId()));
			final List<OffenderCourseAttendance> OffCoAttList = commitBean.getUpdateList();
			for (final OffenderCourseAttendance course : OffCoAttList) {
				VOffenderAllSchedules schedule = new VOffenderAllSchedules();
				BeanUtils.copyProperties(course, schedule);
				schedule.setEventId(new BigDecimal(course.getEventId()));
				tagMultipleFailureService.adjustUa(schedule, schedule.getCreateUserId());
				if (course.getEventOutcome() != null) {
					course.setEventStatus("COMP");
				}
				attendence.setSealFlag(course.getSealFlag());
				attendence.setEventOutcome(course.getEventOutcome());
				attendence.setEventStatus(course.getEventStatus());
				attendence.setEventDate(course.getEventDate());
			}
			VOffenderCourseAttendances vatt=new VOffenderCourseAttendances();
			BeanUtils.copyProperties(attendence, vatt);
			try {
			offenderCourseAttendancesT1Service.offenderCourseAttendancesT1(vatt);
			}catch (Exception e) {
				logger.error(e);
			}
			liReturn = ocdpatteRepository
					.offCourseAttendancesUpdateOffenderCourseAttendances(commitBean.getUpdateList());
			offenderCourseAttndnsTwfService.offenderCourseAttndnsTwf(attendence, "UPDATING");
			for (final OffenderCourseAttendance bean : OffCoAttList) {
				if (liReturn == 1) {
					ocmpssetService.updateSchedulePay(BigDecimal.valueOf(bean.getOffenderBookId()), Integer.valueOf(String.valueOf(bean.getEventId())),
							"ACP",bean.getModifyUserId());
					liReturn = 1;
				}
			}		
			for (OffenderCourseAttendance bean : commitBean.getUpdateList()) {
				try {
					VOffenderAllSchedules offsch = new VOffenderAllSchedules();
					offsch.setEventDate(bean.getEventDate());
					offsch.setStartTime(bean.getInTime());
					offsch.setEndTime(bean.getOutTime());
					offsch.setOffenderBookId(BigDecimal.valueOf(bean.getOffenderBookId()));
					offsch.setEventOutcome(bean.getEventOutcome());
					offsch.setScheduleType("ACP");
					offsch.setCreateUserId(commitBean.getCreateUserId());
					offsch.setEventId(BigDecimal.valueOf(bean.getEventId()));
					List<WeeklyActivityPlans> returnList = ocdclogsServiceImpl.getWeeklyActivityPlanData(offsch);
					ocdclogsServiceImpl.saveWeeklyActivitePlanData(returnList, offsch);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}			
		}
		return liReturn;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 */
	public List<CourseScheduleStaff> courseScheduleStaffsExecuteQuery(final CourseScheduleStaff searchRecord) {
		return ocdpatteRepository.courseScheduleStaffsExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstCOURSE_SCHEDULE_STAFFS
	 *
	 */
	@Transactional
	public Integer courseScheduleStaffsCommit(final CourseScheduleStaffsCommitBean commitBean) {
		int liReturn = 0;
		if (commitBean != null && commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			commitBean.getInsertList().forEach(bean-> bean.setCreateUserId(commitBean.getCreateUserId()));
			liReturn = ocdpatteRepository.courseScheduleStaffsInsertCourseScheduleStaffs(commitBean.getInsertList());
			return liReturn;
		}
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			commitBean.getUpdateList().forEach(action-> action.setModifyUserId(commitBean.getCreateUserId()));
			liReturn = ocdpatteRepository.courseScheduleStaffsUpdateCourseScheduleStaffs(commitBean.getUpdateList());
		}
		if (commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {
			commitBean.getDeleteList().forEach(action-> action.setModifyUserId(commitBean.getCreateUserId()));
			liReturn = ocdpatteRepository.courseScheduleStaffsDeleteCourseScheduleStaffs(commitBean.getDeleteList());
		}
		return liReturn;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 */
	public List<CourseSchedules> deliveryDetailsExecuteQuery(final CourseSchedules searchRecord) {
		List<CourseSchedules> courseScheduleList = null;
		courseScheduleList = ocdpatteRepository.deliveryDetailsExecuteQuery(searchRecord);
		final Date reviewDate = ocdtmrevPkgService.getReviewDate(searchRecord);
		courseScheduleList.forEach(data -> {
			data.setReviewDate(reviewDate);
		});

		return courseScheduleList;

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstDELIVERY_DETAILS
	 *
	 */
	@Transactional
	public Integer deliveryDetailsCommit(final CourseSchedulesCommitBean commitBean) {
		int liReturn = 0;
		if (commitBean != null && commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			commitBean.getUpdateList().forEach(bean->bean.setModifyUserId(commitBean.getCreateUserId()));
			liReturn = ocdpatteRepository.deliveryDetailsUpdateCourseSchedules(commitBean.getUpdateList());
		}
		return liReturn;
	}

	/**
	 * This method is used to execute a record group
	 *
	 */
	public List<ReferenceCodes> rgScheduleTypeRecordGroup() {
		return ocdpatteRepository.rgScheduleTypeRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 */
	public List<ProgramServices> rgServiceRecordGroup() {
		return ocdpatteRepository.rgServiceRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 */
	public List<ReferenceCodes> rgEngagementRecordGroup() {
		return ocdpatteRepository.rgEngagementRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 */
	public List<AgencyLocations> rgInstProviderRecordGroup(String caseLoadId) {
		return ocdpatteRepository.rgInstProviderRecordGroup(caseLoadId);

	}

	/**
	 * This method is used to execute a record group
	 *
	 */
	@Override
	public List<ReferenceCodes> rgCommProviderRecordGroup(String createUserId) {
		final List<ReferenceCodes> returnList = ocdpatteRepository.rgCommProviderRecordGroup(createUserId);
		Integer count = 0;
		for (final ReferenceCodes areas : returnList) {
			count = count + 1;
			areas.setListSeq(BigDecimal.valueOf(count));
		}
		return returnList;

	}

	/**
	 * This method is used to execute a record group
	 *
	 */
	public List<ReferenceCodes> rgConfirmAttendanceRecordGroup() {
		return ocdpatteRepository.rgConfirmAttendanceRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 */
	public List<ReferenceCodes> rgUnderstandingRecordGroup() {
		return ocdpatteRepository.rgUnderstandingRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 */
	public List<ReferenceCodes> rgStaffRoleRecordGroup() {
		final List<ReferenceCodes> returnList = ocdpatteRepository.rgStaffRoleRecordGroup();
		Integer count = 0;
		for (final ReferenceCodes areas : returnList) {
			count = count + 1;
			areas.setListSeq(BigDecimal.valueOf(count));
		}
		return returnList;
	}

	/**
	 * This method is used to execute a record group
	 *
	 */
	public List<StaffMembers> rgStaffNameRecordGroup(final String progInstId) {
		return ocdpatteRepository.rgStaffNameRecordGroup(!progInstId.equals("") ? new BigDecimal(progInstId) : null);

	}

	@Override
	public CourseSchedules getcourseScheduleExecuteQuery(final CourseSchedules courseSchedule) {
		List<VAcpSchedules> list = null;
		CourseSchedules cs = new CourseSchedules();
		Integer vCount = 0;
		if (courseSchedule.getCatchUpSessionFlag() != null && courseSchedule.getCatchUpSessionFlag().equals("true")) {
			courseSchedule.setCatchUpSessionFlag("Y");
		} else {
			courseSchedule.setCatchUpSessionFlag("N");
		}
		list = ocdpatteRepository.getcourseSchedule(courseSchedule);
		if (list != null) {
			for (VAcpSchedules data : list) {
				vCount = vCount + 1;
				if (vCount == 1) {
					cs.setStartTime(data.getStartTime());
					cs.setEndTime(data.getEndTime());
					cs.setOccurrenceCode(data.getProgramInstanceCode());
					cs.setModuleDesc(data.getModuleInstanceDesc());
					cs.setPhaseDesc(data.getPhaseInstanceDesc());
					cs.setSessionNo(data.getSessionNo());
					cs.setCrsSchId(data.getCrsSchId());
					cs.setPhaseId(data.getPhaseInstanceId().toString());
					cs.setProgramInstanceId(
							data.getProgramInstanceId() != null ? BigDecimal.valueOf(data.getProgramInstanceId())
									: null);
					cs.setScheduleStatus(data.getScheduleStatus());
				} else if (vCount > 1) {
					cs.setCrsSchId(-1l);
				}

			}
		}
		if (cs.getCrsSchId() == null) {
			cs.setCrsSchId(-9l);
		}

		return cs;
	}

	@Override
	public Map checkUa(final OffenderCourseAttendance searchBean) {
		final Map<String, Object> map = new HashMap<>();
		final String newUa = ocdpatteRepository.checkUaCheckUaEventOutcome(searchBean);
		final String oldOutcome = searchBean.getEventOutcomeDbVal();
		searchBean.setEventOutcome(oldOutcome);
		final String oldUa = ocdpatteRepository.checkUaCheckUaEventOutcome(searchBean);
		if (Optional.ofNullable(oldUa).isPresent() && "Y".equals(oldUa)) {
			map.put("lvOldUa", true);
		} else {
			map.put("lvOldUa", false);
		}
		if (Optional.ofNullable(newUa).isPresent() && "Y".equals(newUa)) {
			map.put("lvNewUa", true);
		} else {
			map.put("lvNewUa", false);
		}

		VOffenderSentenceEvents events = ocdpatteRepository.getOffenderSentenceEvents(searchBean);
		events.setEventId(new BigDecimal(searchBean.getEventId()));
		Integer count = ocdpatteRepository.getOffenderSentenceUa(events);
		if (count > 0) {
			map.put("lvMultipleFailure", true);
		} else {
			map.put("lvMultipleFailure", false);
		}

		return map;
	}

	@Override
	public String getproviderType(final String caseloadId) {
		return ocdpattePkgService.getProviderType(caseloadId);
	}

	@Override
	public OffenderCourseAttendance getActOutcomeFlag(final OffenderCourseAttendance searchBean) {
		final OffenderCourseAttendance offenderatendance = ocdpatteRepository.getActOutcomeFlag(searchBean);
		return offenderatendance;
	}

	@Override
	public Integer isStaffExists(final CourseSchedules searchBean) {
		return ocdpatteRepository.isStaffExists(searchBean);
	}

	@Override
	public VAddresses getProgLocation(final CourseSchedules courseSchedules) {
		VAddresses address = new VAddresses();
		String intLocaDesc = null;
		final CourseActivities courseActivities = ocdpatteRepository.getcrsactivityDetails(courseSchedules);
		if (courseActivities != null && courseActivities.getServicesAddressId() != null) {
			address = ocdpatteRepository.getprogLoc(courseActivities.getServicesAddressId());
		}
		if (courseActivities != null && courseActivities.getInternalLocationId() != null) {
			intLocaDesc = ocdpatteRepository.getInternalLocationDescription(courseActivities.getInternalLocationId());
			address.setFacility(intLocaDesc);
		}
		return address;
	}

	@Override
	@Transactional
	public Integer ocdpatteCommitBean(OcdpatteCommitBean commitBean) {
		Integer returnValue = 0;
		try {
			commitBean.getCrsSchCommitBean().getInsertList().forEach(bean->bean.setCreateUserId(commitBean.getCreateUserId()));
			commitBean.getCrsSchCommitBean().getUpdateList().forEach(bean->bean.setModifyUserId(commitBean.getCreateUserId()));
			commitBean.getOffCrsCommitBean().getInsertList().forEach(bean->bean.setCreateUserId(commitBean.getCreateUserId()));
			commitBean.getOffCrsCommitBean().getUpdateList().forEach(bean->bean.setModifyUserId(commitBean.getCreateUserId()));
			commitBean.getOffCrsCommitBean().setCreateUserId(commitBean.getCreateUserId());
			commitBean.getCrsSchCommitBean().setCreateUserId(commitBean.getCreateUserId());
			offCourseAttendancesCommit(commitBean.getOffCrsCommitBean());
			courseScheduleStaffsCommit(commitBean.getCrsSchCommitBean());
			List<CourseSchedules> list = new ArrayList<CourseSchedules>();
			list.add(commitBean.getDeliveryDetailsModel());
			list.forEach(data->data.setModifyUserId(commitBean.getCreateUserId()));
			ocdpatteRepository.deliveryDetailsUpdateCourseSchedules(list);
			returnValue = 1;
		} catch (Exception e) {
			logger.error(e);
			returnValue = 0;
		}
		return returnValue;
	}
	
	public List<AgencyLocations> rgAgyLocsRecordGroup(String caseLoadId) {
		final List<AgencyLocations> returnList = ocdpatteRepository.rgAgyLocsRecordGroup(caseLoadId);
		Integer count = 0;
		for (final AgencyLocations areas : returnList) {
			count = count + 1;
			areas.setListSeq(count);
			areas.setCanDisplay(true);
		}
		return returnList;
	}
	
	public List<TeamMembers> rgTeamAgyLocsRecordGroup(String caseLoadId) {
		final List<TeamMembers> returnList = ocdpatteRepository.rgTeamAgyLocsRecordGroup(caseLoadId);

		Integer count = 0;
		for (final TeamMembers areas : returnList) {
			count = count + 1;
			areas.setListSeq(count);
			areas.setCanDisplay(true);
		}
		return returnList;

	

	}
	
	public List<TeamMembers> rgCorpLocsRecordGroup() {
		List<TeamMembers> returnList = ocdpatteRepository.rgCorpLocsRecordGroup();
		returnList.forEach(action -> {
			action.setCanDisplay(true);
		});
		return returnList;

	}

	@Override
	public List<EventMeasureOutcomes> cancelFlagOutcomeList(EventMeasures eventMeasureObj) {
		return ocdpatteRepository.cancelFlagOutcomeList(eventMeasureObj);
	}
	
}
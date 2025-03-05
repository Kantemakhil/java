package net.syscon.s4.cm.programsservices.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.cm.programsservices.OcduatteRepository;
import net.syscon.s4.cm.programsservices.OcduatteService;
import net.syscon.s4.cm.programsservices.OcduprojRepository;
import net.syscon.s4.cm.programsservices.maintenance.OffenderCourseSkills;
import net.syscon.s4.cm.programsservices.maintenance.OffenderCourseSkillsCommitBean;
import net.syscon.s4.common.beans.OffenderCourseAttendance;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.CourseActivities;
import net.syscon.s4.im.beans.Teams;
import net.syscon.s4.inst.institutionalactivities.maintenance.beans.VOffenderCourseEvents;
import net.syscon.s4.inst.institutionalactivities.maintenance.beans.VOffenderCourseEventsCommitBean;
import net.syscon.s4.inst.legals.beans.VOffenderSentenceEvents;
import net.syscon.s4.inst.schedules.bean.VOffenderAllSchedules;
import net.syscon.s4.pkgs.tag_multiple_failure.TagMultipleFailureService;
import net.syscon.s4.pkgs.tag_unpaid_work.TagUnpaidWorkService;
import net.syscon.s4.triggers.VOffenderCourseEventsTuService;

/**
 * Class OcduatteServiceImpl
 */
@Service
public class OcduatteServiceImpl extends BaseBusiness implements OcduatteService {

	@Autowired
	private OcduatteRepository ocduatteRepository;
	
	@Autowired
	private OcduprojRepository ocduprojRepository;

	
	@Autowired
	private TagUnpaidWorkService tagUnpaidWorkService;
	
	@Autowired
	private TagMultipleFailureService tagMultipleFailureService;
	
	@Autowired
	private VOffenderCourseEventsTuService vOffenderCourseEventsTuService;
	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 */
	public List<OffenderCourseSkills> offCourseAttendOnCheckDeleteMaster(final OffenderCourseSkills paramBean) {
		return ocduatteRepository.offCourseAttendOnCheckDeleteMaster(paramBean);
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 */
	public List<VOffenderCourseEvents> offCourseAttendExecuteQuery(final VOffenderCourseEvents searchRecord) { //OffenderCourseAttendance
		final List<VOffenderCourseEvents> vOffenderCourseEventsList = ocduatteRepository
				.offCourseAttendExecuteQuery(searchRecord);
		if (!vOffenderCourseEventsList.isEmpty()) {
			vOffenderCourseEventsList.forEach(action -> {
				if (Optional.ofNullable(action).isPresent()) {
//					final VOffenderCourseEvents vOffenderCourseEvents = ocduatteRepository
//							.offCourseAttendPostQuery(action);
					OffenderCourseAttendance oca = new OffenderCourseAttendance();

         		oca.setOffenderBookId(action.getOffenderBookId().longValue());
					oca.setEventOutcome(action.getEventOutcome());
					oca.setBehaviourCode(action.getBehaviourCode());
					oca.setPerformanceCode(action.getPerformanceCode());
					oca.setSupervisorStaffId(action.getSupervisorStaffId());
					oca.setCrsActyId(action.getCrsActyId());
					final VOffenderCourseEvents vOffenderCourseEvents = tagUnpaidWorkService.getAttendInfo(oca);
					
					if (Optional.ofNullable(vOffenderCourseEvents).isPresent()) {
						action.setpOffenderIdDisplay(vOffenderCourseEvents.getpOffenderIdDisplay());
						action.setpName(vOffenderCourseEvents.getpName());
						action.setpAttendance(vOffenderCourseEvents.getpAttendance());
						action.setpBehaviour(vOffenderCourseEvents.getpBehaviour());
						action.setpWorkQuality(vOffenderCourseEvents.getpWorkQuality());
						action.setpSupervisorName(vOffenderCourseEvents.getpSupervisorName());
						action.setpCode(vOffenderCourseEvents.getpCode());
						action.setpActivityDesc(vOffenderCourseEvents.getpActivityDesc());
						if (Optional.ofNullable(action.getCrsActyId()).isPresent()) {
							action.setNbtRecordCrsActyId(action.getCrsActyId().longValue());
						}
						if (Optional.ofNullable(action.getOffPrgrefId()).isPresent()) {
							action.setNbtRecordOffPrgrefId(action.getOffPrgrefId().longValue());
						}
					}
				}
			});
		}
		return vOffenderCourseEventsList;
	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstOFF_COURSE_ATTEND
	 *
	 */
	@Transactional
	public Integer offCourseAttendCommit(final VOffenderCourseEventsCommitBean commitBean) {
		int liReturn = 0;
		VOffenderCourseEvents odlList = new VOffenderCourseEvents();
		// updateRecords
		if (Optional.ofNullable(commitBean.getUpdateList()).isPresent() && !commitBean.getUpdateList().isEmpty()) {

			for (final VOffenderCourseEvents actionObj : commitBean.getUpdateList()) {
				actionObj.setCreateUserId(commitBean.getCreateUserId());
				if (!(actionObj.getEventOutcome().equals(actionObj.getEventOutcomeDbVal()))) {
					//ocduatteRepository.saveOffCourseAttendPreUpdate(actionObj);
					VOffenderAllSchedules vOffSch = new VOffenderAllSchedules();
					vOffSch.setEventType(actionObj.getEventType());
					vOffSch.setEventSubType(actionObj.getEventSubType());
					vOffSch.setEventOutcome(actionObj.getEventOutcome());
					tagMultipleFailureService.adjustUa(vOffSch, commitBean.getCreateUserId());
				}
				odlList = ocduatteRepository.ocduatteVoffenderCourseEventsOldObject(actionObj);

			}
//			liReturn = ocduatteRepository.offCourseAttendUpdateVOffenderCourseEvents(commitBean.getUpdateList());
//			List<VOffenderCourseEvents> insertAttendanceList = new ArrayList<VOffenderCourseEvents>();
//			List<VOffenderCourseEvents> updateAttendanceList = new ArrayList<VOffenderCourseEvents>();
//			int result = 0;
			for (final VOffenderCourseEvents actionObj : commitBean.getUpdateList()) {

//				if(actionObj.getEventId() == null) {
//					actionObj.setEventId(ocduprojRepository.getCourseAttendenceId());
//					result = ocduprojRepository.createOffCourseAttendance(actionObj);
//				}
				actionObj.setCreateUserId(commitBean.getCreateUserId());			
				liReturn = vOffenderCourseEventsTuService.vOffenderCourseEventsTuTgr(odlList, actionObj);
				if(liReturn>0) {
					liReturn = 1;
				}
			}
//			liReturn = ocduatteRepository.offCourseAttendUpdateVOffenderCourseEvents(commitBean.getUpdateList());
			for (final VOffenderCourseEvents actionObj : commitBean.getUpdateList()) {
				//ocduatteRepository.saveOffCourseAttendPostUpdate(actionObj);
				tagUnpaidWorkService.updateCredithrs(actionObj, actionObj.getCreateUserId());
				
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
	public List<OffenderCourseSkills> offCourseSkillsExecuteQuery(final OffenderCourseSkills searchRecord) {
		final List<OffenderCourseSkills> offenderCourseSkillsList = ocduatteRepository
				.offCourseSkillsExecuteQuery(searchRecord);
		if (!offenderCourseSkillsList.isEmpty()) {
			offenderCourseSkillsList.forEach(action -> {
				if (Optional.ofNullable(action).isPresent()) {
					final OffenderCourseSkills offenderCourseSkills = ocduatteRepository.getStaffNamePostQuery(action);
					if (Optional.ofNullable(offenderCourseSkills).isPresent()) {
						action.setStaffDesc(offenderCourseSkills.getStaffDesc());
					}
				}
			});
		}
		return offenderCourseSkillsList;
	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstOFF_COURSE_SKILLS
	 *
	 */
	@Transactional
	public List<OffenderCourseSkills> offCourseSkillsCommit(final OffenderCourseSkillsCommitBean commitBean) {
		final List<OffenderCourseSkills> returnList = new ArrayList<>();
		final OffenderCourseSkills returnObject = new OffenderCourseSkills();
		int liReturn = 0;
		// insertRecords
		if (Optional.ofNullable(commitBean.getInsertList()).isPresent() && !commitBean.getInsertList().isEmpty()) {
			commitBean.getInsertList().forEach(data->data.setCreateUserId(commitBean.getCreateUserId()));
			liReturn = ocduatteRepository.offCourseSkillsInsertOffenderCourseSkills(commitBean.getInsertList());
		}
		// updateRecords
		if (Optional.ofNullable(commitBean.getUpdateList()).isPresent() && !commitBean.getUpdateList().isEmpty()) {
			commitBean.getUpdateList().forEach(data->data.setModifyUserId(commitBean.getCreateUserId()));
			liReturn = ocduatteRepository.offCourseSkillsUpdateOffenderCourseSkills(commitBean.getUpdateList());
		}
		// deleteRecords
		if (Optional.ofNullable(commitBean.getDeleteList()).isPresent() && !commitBean.getDeleteList().isEmpty()) {
			liReturn = ocduatteRepository.offCourseSkillsDeleteOffenderCourseSkills(commitBean.getDeleteList());
		}
		returnObject.setReturnValue(liReturn);
		returnList.add(returnObject);
		return returnList;
	}

	/**
	 * This method is used to execute a record group
	 *
	 */
	public List<ReferenceCodes> rgAttendanceRecordGroup(final String pShowOutcome) {
		final List<ReferenceCodes> referenceCodesList = ocduatteRepository.rgAttendanceRecordGroup(pShowOutcome);
		if (!referenceCodesList.isEmpty()) {
			for (final ReferenceCodes obj : referenceCodesList) {
				if ("Y".equals(obj.getActiveFlag())) {
					obj.setCanDisplay(true);
				} else {
					obj.setCanDisplay(false);
				}
			}
		}
		return referenceCodesList;
	}

	/**
	 * This method is used to execute a Superviosr team list
	 *
	 */
	public List<ReferenceCodes> rgSupervisorRecordGroup(final Long crsActyId) {
		List<ReferenceCodes> returnList= ocduprojRepository.rgSupervisorRecordGroup(crsActyId);
		returnList.forEach(bo->{
			bo.setCodes(bo.getCode());
			bo.setCode(bo.getStaffId().toString());
		});
		return returnList;
	}


	/**
	 * This method is used to execute a record group
	 *
	 */
	public List<ReferenceCodes> rgBehaviourRecordGroup() {
		return ocduatteRepository.rgBehaviourRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 */
	public List<ReferenceCodes> rgWorkQualityRecordGroup() {
		return ocduatteRepository.rgWorkQualityRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 */
	public List<CourseActivities> rgProjectsRecordGroup(final String teamId) {
		final List<CourseActivities> courseActivitiesList = ocduatteRepository.rgProjectsRecordGroup(teamId);
		long count = 0;
		if (!courseActivitiesList.isEmpty()) {
			for (final CourseActivities teams : courseActivitiesList) {
				count = count + 1;
				teams.setListSeq(count);
			}
		}
		return courseActivitiesList;
	}

	/**
	 * This method is used to execute a record group
	 *
	 */
	public List<CourseActivities> rgProjects2RecordGroup(final Long offenderBookId) {
		final List<CourseActivities> courseActivitiesList = ocduatteRepository.rgProjects2RecordGroup(offenderBookId);
		long count = 0;
		if (!courseActivitiesList.isEmpty()) {
			for (final CourseActivities teams : courseActivitiesList) {
				count = count + 1;
				teams.setListSeq(count);
			}
		}
		return courseActivitiesList;
	}

	/**
	 * This method is used to execute a record group
	 *
	 */
	public List<ReferenceCodes> rgSkillsRecordGroup() {
		return ocduatteRepository.rgSkillsRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 */
	public List<StaffMembers> rgStaffCheckRecordGroup() {
		return ocduatteRepository.rgStaffCheckRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 */
	public List<Teams> rgTeamsRecordGroup(final String userId) {
		return ocduatteRepository.rgTeamsRecordGroup(userId);

	}

	/**
	 * This method is used to execute a record group
	 *
	 */
	@Override
	public Integer compareDateTime(final VOffenderCourseEvents actionObj) {
		return ocduatteRepository.compareDateTime(actionObj);

	}

	/**
	 * Fetch the records from database table
	 *
	 * @param staffId
	 *
	 */
	@Override
	public String getStaffName(final long staffId) {
		final OffenderCourseSkills input = new OffenderCourseSkills();
		input.setStaffId(staffId);
		final OffenderCourseSkills offenderCourseSkills = ocduatteRepository.getStaffNamePostQuery(input);
		if (Optional.ofNullable(offenderCourseSkills).isPresent()) {
			return offenderCourseSkills.getStaffDesc();
		}
		return null;

	}

	/**
	 * Fetch the records from database table
	 *
	 * @param actionObj
	 *
	 */

	@Override
	public VOffenderCourseEvents checkUa(final VOffenderCourseEvents actionObj) {
		final VOffenderCourseEvents returnObj = new VOffenderCourseEvents();
		final List<VOffenderSentenceEvents> failCur = ocduatteRepository.checkUaFailCur(actionObj);
		returnObj.setpMultipleFailure(false);
		final String pOldUa = ocduatteRepository.checkUaCheckUaEventOutcome(actionObj,
				actionObj.getEventOutcomeDbVal());
		final String pNewUa = ocduatteRepository.checkUaCheckUaEventOutcome(actionObj, actionObj.getEventOutcome());
		if (Optional.ofNullable(pOldUa).isPresent() && "Y".equals(pOldUa)) {
			returnObj.setpOldUa(true);
		}
		if (Optional.ofNullable(pNewUa).isPresent() && "Y".equals(pNewUa)) {
			returnObj.setpNewUa(true);
		}
		if ((pOldUa == null && pNewUa == null)) {
			return returnObj;
		}
		if (!failCur.isEmpty()) {
			for (final VOffenderSentenceEvents obj : failCur) {
				final Integer lvResult = ocduatteRepository.checkUaCountSentenceUa(obj, actionObj.getEventId());
				if (Optional.ofNullable(lvResult).isPresent() && lvResult > 0) {
					returnObj.setpMultipleFailure(true);
				}
			}
		}
		return returnObj;
	}

}

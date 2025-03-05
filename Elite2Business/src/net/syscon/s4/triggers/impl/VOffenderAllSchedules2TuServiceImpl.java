package net.syscon.s4.triggers.impl;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.genericservices.CustomException;
import net.syscon.s4.im.beans.OffenderCaseNotes;
import net.syscon.s4.im.incidentsoic.beans.OicHearings;
import net.syscon.s4.inst.institutionalactivities.maintenance.beans.VOffenderCourseEvents;
import net.syscon.s4.inst.schedules.bean.CourtEvents;
import net.syscon.s4.inst.schedules.bean.OffenderIndSchedules;
import net.syscon.s4.inst.schedules.bean.OffenderReleaseDetails;
import net.syscon.s4.triggers.OffenderIndSchedulesT1Service;
import net.syscon.s4.triggers.OffenderIndSchedulesT2Service;
import net.syscon.s4.triggers.OffenderIndSchedulesT3Service;
import net.syscon.s4.triggers.OffenderIndSchedulesT4Service;
import net.syscon.s4.triggers.OffenderIndSchedulesTwfService;
import net.syscon.s4.triggers.OffenderReleaseDetailsT2Service;
import net.syscon.s4.triggers.VOffenderAllSchedules2TuRepository;
import net.syscon.s4.triggers.VOffenderAllSchedules2TuService;
import net.syscon.s4.triggers.VOffenderCourseEventsTuService;
import net.syscon.s4.triggers.VOffenderVisitSchedules;
@Service
public class VOffenderAllSchedules2TuServiceImpl implements VOffenderAllSchedules2TuService {
	
	private static Logger logger = LogManager.getLogger(VOffenderAllSchedules2TuServiceImpl.class.getName());
	@Autowired
	VOffenderAllSchedules2TuRepository vOffenderAllSchedules2TuRepository;
	
	@Autowired
	private OffenderIndSchedulesT2Service offenderIndSchedulesT2Service;
	
	@Autowired
	private OffenderIndSchedulesTwfService offenderIndSchedulesTwfService;
	
	@Autowired
	private OffenderIndSchedulesT1Service offenderIndSchedulesT1Service;
	
	@Autowired
	private OffenderIndSchedulesT3Service offenderIndSchedulesT3Service;
	
	@Autowired
	private OffenderIndSchedulesT4Service offenderIndSchedulesT4Service;
	
	@Autowired
	private VOffenderCourseEventsTuService vOffenderCourseEventsTuService;
	
	@Autowired
	private OffenderReleaseDetailsT2Service offenderReleaseDetailsT2Service;

	@Override
	public Integer vOffenderAllSchedules2TuTrigger(final OffenderIndSchedules newRef, final OffenderIndSchedules oldRef,
			final String recordSource,final String source) throws CustomException {
		Integer result=null;
		final OffenderIndSchedules offenderIndSched = new OffenderIndSchedules();
		final LocalDate ld = new Date(newRef.getEventDate().getTime()).toLocalDate();
		final LocalDate ld2 = LocalDate.now();
		final LocalDate finalDate = ld2.minusMonths(3);
		List<OffenderIndSchedules> offenderIndSchedulesList = new ArrayList<OffenderIndSchedules>();
		if(oldRef !=null && oldRef.getEventDate() !=null) {
			if (oldRef.getEventDate().equals(newRef.getEventDate()) && ld.isBefore(finalDate)) {
				throw new CustomException("No Back-dating 3-month old record is allowed.");
	
			}
		}
		if ("SCH".equals(recordSource)) {
			BeanUtils.copyProperties(newRef, offenderIndSched);
			offenderIndSched.setEventId(oldRef.getEventId());
			offenderIndSchedulesList.add(offenderIndSched);
			offenderIndSchedulesTwfService.offenderIndSchedulesTwfTgr(offenderIndSched, "UPDATING");
			offenderIndSchedulesList = offenderIndSchedulesT2Service.offenderIndSchedulesT2Tgr(offenderIndSchedulesList);
			offenderIndSchedulesList = offenderIndSchedulesT1Service.offenderIndSchedulesT1Tgn(offenderIndSchedulesList);
			result=vOffenderAllSchedules2TuRepository.schOffenderIndSchedules(offenderIndSchedulesList);
			OffenderCaseNotes offenderCaseNotes = new OffenderCaseNotes();
			offenderIndSchedulesT4Service.offenderIndSchedulesT4(offenderCaseNotes, offenderIndSched , source);
		} else if ("V_OFF_CRS".equals(recordSource)) {
			VOffenderCourseEvents targetObject = new VOffenderCourseEvents();
			VOffenderCourseEvents oldRef1 = new VOffenderCourseEvents();
			VOffenderCourseEvents newRef1 = new VOffenderCourseEvents();
			if (oldRef.getEventId() == null) {
				targetObject = new VOffenderCourseEvents();
				dataMapping(newRef, oldRef, targetObject);
				
				oldRef1.setEventId(oldRef.getEventId() !=null?new BigDecimal(oldRef.getEventId()):null);
				BeanUtils.copyProperties(newRef, newRef1);
				newRef1.setCreateUserId(newRef.getCreateUserId());
				newRef1.setEventId(newRef.getEventId() !=null?new BigDecimal(newRef.getEventId()):null);
				newRef1.setEventStatus(newRef.getEventStatus());
				newRef1.setEventOutcome(newRef.getEventOutcome() !=null?newRef.getEventOutcome():null);
				newRef1.setEventDate(newRef.getEventDate());
				newRef1.setEventClass(newRef.getEventClass());
				newRef1.setOffenderBookId(new BigDecimal(newRef.getOffenderBookId()));
				newRef1.setEventSubType(newRef.getEventSubType());
				newRef1.setEventType(newRef.getEventType());
				newRef1.setOffPrgrefId(newRef.getOffPrgrefId()!=null? new BigDecimal(newRef.getOffPrgrefId()):null);
				newRef1.setReferenceId(oldRef.getReferenceIdTemp()!=null?oldRef.getReferenceIdTemp().longValue():null);
				newRef1.setInTime(newRef.getInTime());
				newRef1.setOutTime(newRef.getOutTime());
				newRef1.setStartTime(newRef.getStartTime());
				newRef1.setEndTime(newRef.getEndTime());
				newRef1.setEventIdTemp(newRef.getEventIdTemp()!=null ? newRef.getEventIdTemp() : null);
				oldRef1.setEventIdTemp(newRef.getEventIdTemp()!=null ? newRef.getEventIdTemp() : null);
				oldRef1.setInTime(oldRef.getInTime());
				oldRef1.setOutTime(oldRef.getOutTime());
				oldRef1.setStartTime(oldRef.getStartTime());
				oldRef1.setEndTime(oldRef.getEndTime());
				oldRef1.setOffPrgrefId(oldRef.getOffPrgrefId()!=null? new BigDecimal(oldRef.getOffPrgrefId()):null);
				result = vOffenderCourseEventsTuService.vOffenderCourseEventsTuTgr(oldRef1, newRef1);
			} else {
				targetObject = new VOffenderCourseEvents();
				oldRef1.setEventId(new BigDecimal(oldRef.getEventId()));
				dataMappingTwo(newRef, oldRef, targetObject);
				BeanUtils.copyProperties(newRef, newRef1);
				newRef1.setCreateUserId(newRef.getCreateUserId());
				newRef1.setEventId(new BigDecimal(newRef.getEventId()));
				newRef1.setEventStatus(newRef.getEventStatus());
				newRef1.setEventOutcome(newRef.getEventOutcome());
				newRef1.setEventDate(newRef.getEventDate());
				newRef1.setEventClass(newRef.getEventClass());
				newRef1.setOffenderBookId(new BigDecimal(newRef.getOffenderBookId()));
				newRef1.setEventSubType(newRef.getEventSubType());
				newRef1.setEventType(newRef.getEventType());
				newRef1.setOffPrgrefId(newRef.getOffPrgrefId()!=null? new BigDecimal(newRef.getOffPrgrefId()):null);
				oldRef1.setOffPrgrefId(newRef.getOffPrgrefId()!=null ? new BigDecimal(newRef.getOffPrgrefId()):null);
				
				newRef1.setInTime(newRef.getInTime());
				newRef1.setOutTime(newRef.getOutTime());
				newRef1.setStartTime(newRef.getStartTime());
				newRef1.setEndTime(newRef.getEndTime());
				
				oldRef1.setInTime(oldRef.getInTime());
				oldRef1.setOutTime(oldRef.getOutTime());
				oldRef1.setStartTime(oldRef.getStartTime());
				oldRef1.setEndTime(oldRef.getEndTime());
				result = vOffenderCourseEventsTuService.vOffenderCourseEventsTuTgr(oldRef1, newRef1);
			}
		} else if ("OFF_VIS".equals(recordSource)) {
			final VOffenderVisitSchedules vOffenderVisitSchedules = new VOffenderVisitSchedules();
			vOffenderVisitSchedules.setEventId(Long.valueOf(newRef.getEventId()));
			vOffenderVisitSchedules.setEventOutcome(newRef.getEventOutcome());
			vOffenderVisitSchedules.setEventStatus(newRef.getEventStatus());
			vOffenderVisitSchedules.setOutcomeReasonCode(newRef.getOutcomeReasonCode());
			vOffenderVisitSchedules.setVisitorCommentText(oldRef.getCommentText());
			result=vOffenderAllSchedules2TuRepository.offVisVOffenderVisitSchedules(vOffenderVisitSchedules);
		} else if ("OIC_HEARING".equals(recordSource)) {
			final OicHearings oicHearings = new OicHearings();
			oicHearings.setHearingDate(newRef.getEventDate());
			oicHearings.setHearingTime(newRef.getStartTime());
			oicHearings.setEventStatus(newRef.getEventStatus());
			oicHearings.setCommentText(newRef.getCommentText());
			oicHearings.setEventId(oldRef.getEventId());
			oicHearings.setModifyUserId(newRef.getCreateUserId());
			result=vOffenderAllSchedules2TuRepository.oicHearingOicHearings(oicHearings);
		} else if ("OFF_REL".equals(recordSource)) {
			final OffenderReleaseDetails offenderReleaseDetails = new OffenderReleaseDetails();
			offenderReleaseDetails.setReleaseDate(newRef.getEventDate());
			offenderReleaseDetails.setMovementReasonCode(newRef.getEventSubType());
			offenderReleaseDetails.setEventStatus(newRef.getEventStatus());
			offenderReleaseDetails.setCommentText(newRef.getCommentText());
			offenderReleaseDetails.setEventId(new BigDecimal(oldRef.getEventId()));
			offenderReleaseDetails.setOffenderBookId(newRef.getOffenderBookId().longValue());
			result=vOffenderAllSchedules2TuRepository.offRelOffenderReleaseDetails(offenderReleaseDetails); 
			//Trigger call
			offenderReleaseDetailsT2Service.offenderReleaseDetailsT2(offenderReleaseDetails);
		} else if ("COURT".equals(recordSource)) {
			final CourtEvents courtEvents = new CourtEvents();
			courtEvents.setEventDate(newRef.getEventDate());
			courtEvents.setStartTime(newRef.getStartTime());
			courtEvents.setCourtEventType(newRef.getEventSubType());
			courtEvents.setEventStatus(newRef.getEventStatus());
			courtEvents.setEventOutcome(newRef.getEventOutcome());
			courtEvents.setCommentText(newRef.getCommentText());
			courtEvents.setAgyLocId(newRef.getAgyLocId());
			courtEvents.setScheduledTripId(newRef.getScheduledTripId());
			courtEvents.setOutcomeReasonCode(newRef.getOutcomeReasonCode());
			courtEvents.setEventId(oldRef.getEventId());
			courtEvents.setToAgyLocId(oldRef.getToAgyLocId());
			result=vOffenderAllSchedules2TuRepository.courtCourtEvents(courtEvents);
		} else {
			final Integer vRecCount = vOffenderAllSchedules2TuRepository.getvRecCount(oldRef.getOffenderBookId(),
					oldRef.getEventType(), oldRef.getReferenceId());
			List<OffenderIndSchedules> lstInsertOff = new ArrayList<OffenderIndSchedules>();
			if (vRecCount == 0) {
				final OffenderIndSchedules targetObj = new OffenderIndSchedules();
				BeanUtils.copyProperties(newRef, targetObj);
				lstInsertOff.add(targetObj);
				try {
					lstInsertOff = offenderIndSchedulesT1Service.offenderIndSchedulesT1Tgn(lstInsertOff);
					logger.info(this.getClass().getName()+"vOffenderAllSchedules2TuTrigger calling OFFENDER_IND_SCHEDULES_T1 trigger");
				} catch (Exception e) {
					logger.error("offenderIndSchedulesT1Service:" + e);
				}
				try {
					lstInsertOff = offenderIndSchedulesT2Service.offenderIndSchedulesT2Tgr(lstInsertOff);
					logger.info(this.getClass().getName()+"vOffenderAllSchedules2TuTrigger calling OFFENDER_IND_SCHEDULES_T2 trigger");
				} catch (Exception e) {
					logger.error("offenderIndSchedulesT2Service:" + e);
				}
				for (int i = 0; i < lstInsertOff.size(); i++) {
					try {
						offenderIndSchedulesT3Service.getVnumRows(lstInsertOff.get(i));
						logger.info(this.getClass().getName()+"vOffenderAllSchedules2TuTrigger calling OFFENDER_IND_SCHEDULES_T3 trigger");
					} catch (Exception e) {
						logger.error("offenderIndSchedulesT3Service:" + e);
					}
					// Trigger call
					offenderIndSchedulesTwfService.offenderIndSchedulesTwfTgr(lstInsertOff.get(i), "INSERTING");
					logger.info(this.getClass().getName()+"vOffenderAllSchedules2TuTrigger calling OFFENDER_IND_SCHEDULES_TWF trigger");
					try {
						result = vOffenderAllSchedules2TuRepository.courtOffenderIndSchedules(lstInsertOff.get(i));
						logger.info(this.getClass().getName()+ "vOffenderAllSchedules2TuTrigger calling repo method courtOffenderIndSchedules ");
					} catch (Exception e) {
						logger.error(this.getClass().getName() + "error in courtOffenderIndSchedules method");
					}
				}
				
			}
		}
		return result;
	}

	private void dataMappingTwo(final OffenderIndSchedules newRef, final OffenderIndSchedules oldRef,
			final VOffenderCourseEvents targetObject) {
		BeanUtils.copyProperties(newRef, targetObject);
		targetObject.setBehaviourCode(newRef.getUnpaidWorkBehaviour());
		targetObject.setActionCode(newRef.getUnpaidWorkAction());
		targetObject.setEventId(new BigDecimal(oldRef.getEventId()));
	}

	private void dataMapping(final OffenderIndSchedules newRef, final OffenderIndSchedules oldRef,
			final VOffenderCourseEvents targetObject) {
		BeanUtils.copyProperties(newRef, targetObject);
		targetObject.setActionCode(newRef.getUnpaidWorkAction() !=null?newRef.getUnpaidWorkAction():null);
		targetObject.setSupervisorStaffId(newRef.getInChargeStaffId()!=null?new BigDecimal(newRef.getInChargeStaffId()):null);
		targetObject.setOffenderBookId(new BigDecimal(oldRef.getOffenderBookId()));
		targetObject.setReferenceId(oldRef.getReferenceId()!=null?Long.valueOf(oldRef.getReferenceId()):null);
		targetObject.setEventDate(oldRef.getEventDate());
	}

}

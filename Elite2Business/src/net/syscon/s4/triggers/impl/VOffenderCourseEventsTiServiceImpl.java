package net.syscon.s4.triggers.impl;

import java.math.BigDecimal;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.inst.institutionalactivities.maintenance.beans.VOffenderCourseEvents;
import net.syscon.s4.triggers.OffenderCourseAttendances;
import net.syscon.s4.triggers.VOffenderCourseEventsTiRepository;
import net.syscon.s4.triggers.VOffenderCourseEventsTiService;

@Service
public class VOffenderCourseEventsTiServiceImpl implements VOffenderCourseEventsTiService {
	private static Logger logger = LogManager.getLogger(VOffenderCourseEventsTiServiceImpl.class);
	@Autowired
	VOffenderCourseEventsTiRepository vOffenderCourseEventsTiRepository;

	@Override
	public Integer vOffenderCourseEventsTiTgr(final VOffenderCourseEvents newObj) {
		Integer result = 0;
		OffenderCourseAttendances offenderCourseAttendances;
		try {
			if (Optional.ofNullable(newObj).isPresent() && Optional.ofNullable(newObj.getOffPrgrefId()).isPresent()
					&& !Optional.ofNullable(newObj.getEventClass()).isPresent()) {
				offenderCourseAttendances = new OffenderCourseAttendances();
				dataMapper(newObj, offenderCourseAttendances);
				result = vOffenderCourseEventsTiRepository.insertOffenderCourseAttendances1(offenderCourseAttendances);
			} else {
				offenderCourseAttendances = new OffenderCourseAttendances();
				dataMapper(newObj, offenderCourseAttendances);
				result = vOffenderCourseEventsTiRepository.insertOffenderCourseAttendances2(offenderCourseAttendances);
			}
		} catch (final Exception e) {
			result = 0;
			logger.error("vOffenderCourseEventsTiTgr", e);
		}
		return result;
	}

	private void dataMapper(final VOffenderCourseEvents newObj,
			final OffenderCourseAttendances offenderCourseAttendances) {
		offenderCourseAttendances.setEventId(newObj.getEventId()!=null?newObj.getEventId().longValue():0);
		offenderCourseAttendances.setEventClass(newObj.getEventClass()==null?"COMM":newObj.getEventClass());
		offenderCourseAttendances.setOffenderBookId(newObj.getOffenderBookId()!=null?newObj.getOffenderBookId().longValue():null);
		offenderCourseAttendances.setEventDate(newObj.getEventDate());
		offenderCourseAttendances.setOffPrgrefId(newObj.getOffPrgrefId()!=null?newObj.getOffPrgrefId().longValue():null);
		offenderCourseAttendances.setEventStatus(newObj.getEventStatus()==null?"":newObj.getEventStatus());
		offenderCourseAttendances.setReferenceId(newObj.getReferenceId()!=null?new BigDecimal(newObj.getReferenceId()):null);
		offenderCourseAttendances.setCrsSchId(newObj.getCrsSchId()!=null?newObj.getCrsSchId().longValue():null);
		offenderCourseAttendances.setCrsApptId(newObj.getCrsApptId()!=null?new BigDecimal(newObj.getCrsApptId()):null);
		offenderCourseAttendances.setCrsActyId(newObj.getCrsActyId()!=null?newObj.getCrsActyId().longValue():null);
		offenderCourseAttendances.setEventSubType(newObj.getEventSubType()==null?"":newObj.getEventSubType());
		offenderCourseAttendances.setEventType(newObj.getEventType()==null?"":newObj.getEventType());
		offenderCourseAttendances.setStartTime(newObj.getStartTime());
		offenderCourseAttendances.setEndTime(newObj.getEndTime());
		offenderCourseAttendances.setInTime(newObj.getInTime());
		offenderCourseAttendances.setOutTime(newObj.getOutTime());
		offenderCourseAttendances.setEventOutcome(newObj.getEventOutcome());
		offenderCourseAttendances.setAgyLocId(newObj.getAgyLocId());
		offenderCourseAttendances.setCommentText(newObj.getCommentText());
		offenderCourseAttendances.setPieceWork(newObj.getPieceWork());
		offenderCourseAttendances.setPerformanceCode(newObj.getPerformanceCode());
		offenderCourseAttendances.setOutcomeReasonCode(newObj.getOutcomeReasonCode());
		offenderCourseAttendances.setToAgyLocId(newObj.getToAgyLocId());
		offenderCourseAttendances.setToInternalLocationId(newObj.getToInternalLocationId()!=null?newObj.getToInternalLocationId().longValue():null);
		offenderCourseAttendances.setEngagementCode(newObj.getEngagementCode());
		offenderCourseAttendances.setUnderstandingCode(newObj.getUnderstandingCode());
		offenderCourseAttendances.setCreditedHours(newObj.getCreditedHours());
		offenderCourseAttendances.setAgreedTravelHour(newObj.getAgreedTravelHour());
		offenderCourseAttendances.setBehaviourCode(newObj.getBehaviourCode());
		offenderCourseAttendances.setSupervisorStaffId(newObj.getSupervisorStaffId()!=null?newObj.getSupervisorStaffId().longValue():null);
		offenderCourseAttendances.setUnexcusedAbsenceFlag(newObj.getUnexcusedAbsenceFlag());
		offenderCourseAttendances.setToAddressId(newObj.getToAddressId()!=null?newObj.getToAddressId().longValue():null);
		offenderCourseAttendances.setDirectionCode(newObj.getDirectionCode());
		offenderCourseAttendances.setSessionNo(newObj.getSessionNo()!=null?newObj.getSessionNo().longValue():null);
		offenderCourseAttendances.setCreateDatetime(newObj.getCreateDatetime());
		offenderCourseAttendances.setModifyDatetime(newObj.getCreateDatetime());
		offenderCourseAttendances.setCreateUserId(newObj.getCreateUserId());
	}

}

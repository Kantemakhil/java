package net.syscon.s4.triggers.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.genericservices.CustomException;
import net.syscon.s4.inst.institutionalactivities.maintenance.beans.VOffenderCourseEvents;
import net.syscon.s4.inst.schedules.bean.OffenderIndSchedules;
import net.syscon.s4.inst.schedules.bean.VOffenderAllSchedules2;
import net.syscon.s4.pkgs.tag_schedule.TagScheduleService;
import net.syscon.s4.triggers.OffenderIndSchedulesT1Service;
import net.syscon.s4.triggers.OffenderIndSchedulesT2Service;
import net.syscon.s4.triggers.OffenderIndSchedulesT4Service;
import net.syscon.s4.triggers.OffenderIndSchedulesTwfService;
import net.syscon.s4.triggers.VOffenderAllSchedules2TdRepository;
import net.syscon.s4.triggers.VOffenderAllSchedules2TdService;
import net.syscon.s4.triggers.VOffenderCourseEventsTuService;
/* =========================================================
Below comments are copied from V_OFFENDER_ALL_SCHEDULES_2_TD Trigger
========================================================= */
/*
============================================================
  MODIFICATION HISTORY
   Person       Date                   version      Comments
 -----------  -------------------------- -----------  -------------------------------
  NIKO           MAR 13, 2007   1.0               Audit column trigger
*/
@Service
public class VOffenderAllSchedules2TdServiceImpl implements VOffenderAllSchedules2TdService {
	private static Logger logger = LogManager.getLogger(VOffenderAllSchedules2TdServiceImpl.class);
	@Autowired
	VOffenderAllSchedules2TdRepository vOffenderAllSchedules2TdRepository;
	
	@Autowired
	private VOffenderCourseEventsTuService vOffenderCourseEventsTuService;
	
	@Autowired
	private OffenderIndSchedulesT4Service offenderIndSchedulesT4Service;
	
	@Autowired
	private OffenderIndSchedulesT1Service offenderIndSchedulesT1Service;
	
	@Autowired
	private OffenderIndSchedulesT2Service offenderIndSchedulesT2Service;
	
	@Autowired
	private OffenderIndSchedulesTwfService offenderIndSchedulesTwfService;
	

	@Autowired
	TagScheduleService tagScheduleService;

	@Override
	public Integer vOffenderAllSchedules2TdTgr(final VOffenderAllSchedules2 old, final VOffenderAllSchedules2 newObj, final String source)
			throws CustomException {
		OffenderIndSchedules offenderIndSchedules = new OffenderIndSchedules();
		Integer result = 0;
		List<OffenderIndSchedules> lstInsertOff = new ArrayList<OffenderIndSchedules>();
		if(old!=null && old.getRecordSource()!=null) {
		if ("SCH".equals(old.getRecordSource()) && "COMM".equals(old.getEventClass())) {
				offenderIndSchedules = new OffenderIndSchedules();
				offenderIndSchedules.setEventId(old.getEventId().intValue());
				offenderIndSchedules.setEndTime((Date) newObj.getEndTime());
				offenderIndSchedules.setEventDate(newObj.getEventDate());
				offenderIndSchedules.setStartTime((Date)newObj.getStartTime());
				offenderIndSchedules.setAgyLocId(newObj.getAgyLocId());
				offenderIndSchedules.setInChargeStaffId(newObj.getInChargeStaffId() !=null ? newObj.getInChargeStaffId().intValue() : null);
				offenderIndSchedules.setCommentText(newObj.getCommentText());
				offenderIndSchedules.setEventOutcome(newObj.getEventOutcome());
				offenderIndSchedules.setCreateUserId(newObj.getCreateUserId());
				lstInsertOff.add(offenderIndSchedules);
				// Trigger call
				lstInsertOff = offenderIndSchedulesT1Service.offenderIndSchedulesT1Tgn(lstInsertOff);
				// Trigger call
				try {
					lstInsertOff = offenderIndSchedulesT2Service.offenderIndSchedulesT2Tgr(lstInsertOff);
				} catch (Exception e) {
					logger.error("offenderIndSchedulesT2Service:" + e);
				}
				// Trigger call
				lstInsertOff.forEach(bo->{
					offenderIndSchedulesTwfService.offenderIndSchedulesTwfTgr(bo, "UPDATING");
				});
				// Below line is used for updating OFFENDER_IND_SCHEDULES table
				result = vOffenderAllSchedules2TdRepository.updateOffenderIndSchedules(offenderIndSchedules);
				offenderIndSchedulesT4Service.offenderIndSchedulesT4(null, offenderIndSchedules, source);
			} else {
				offenderIndSchedules = new OffenderIndSchedules();
				offenderIndSchedules.setEventId(old.getEventId().intValue());
				offenderIndSchedules.setModifyUserId(old.getModifyUserId());
				// Below line is used for deleting OFFENDER_IND_SCHEDULES table
				result = vOffenderAllSchedules2TdRepository.deleteOffenderIndSchedules(offenderIndSchedules);
			}
		} else if ("V_OFF_CRS".equals(old.getRecordSource())) {
			final VOffenderCourseEvents inputObj = new VOffenderCourseEvents();
			inputObj.setOffenderBookId(old.getOffenderBookId());
			inputObj.setEventId(old.getEventId());
			inputObj.setReferenceId(old.getReferenceId().longValue());
			inputObj.setEventDate(old.getEventDate());
			if (old.getEventId() == null) {
				// Below line is used for updating v_Offender_course_events table
				//result = vOffenderAllSchedules2TdRepository.updateVOffenderCourseEvents(inputObj);
				result = vOffenderCourseEventsTuService.vOffenderCourseEventsTuTgr(inputObj, inputObj);
			} else {
				// Below line is used for deleting v_Offender_course_events table
				//result = vOffenderAllSchedules2TdRepository.updateVOffenderCourseEvents2(inputObj);
				result = vOffenderCourseEventsTuService.vOffenderCourseEventsTuTgr(inputObj, inputObj);
			}
		} else if ("COURT".equals(old.getRecordSource())) {
			throw new CustomException("Deletion of Court Event record is not allowed ");
		} else if ("OFF_REL".equals(old.getRecordSource())) {
			throw new CustomException("Deletion of Offender Release Event record is not allowed ");
		} else if ("OFF_VIS".equals(old.getRecordSource())) {
			throw new CustomException("Delete of Visit_Offender Order/Visit is not allowed ");
		} else {
			if (null == old.getEventId()) {
				old.setStatusCode("CANC");
				old.setReasonCode(null);
				result = tagScheduleService.setScheduleStatus(old);
			} else {
				throw new CustomException("Delete of Offender Order/Visit is not allowed ");
			}
		}
		return result;
	}

}

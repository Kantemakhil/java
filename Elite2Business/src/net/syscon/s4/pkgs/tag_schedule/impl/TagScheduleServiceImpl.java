package net.syscon.s4.pkgs.tag_schedule.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.inst.schedules.bean.OffenderIndSchedules;
import net.syscon.s4.inst.schedules.bean.VOffenderAllSchedules;
import net.syscon.s4.inst.schedules.bean.VOffenderAllSchedules2;
import net.syscon.s4.pkgs.tag_schedule.TagScheduleRepository;
import net.syscon.s4.pkgs.tag_schedule.TagScheduleService;
import net.syscon.s4.triggers.VOffenderAllSchedules2TuService;

/*
 * Below comments are copied from package tag_schedule 
-- Version No : V2.0 Modification Date : 09-APR-2009
-- Purpose: The dedicated package for Prison Movement - Schedules
--
-- MODIFICATION HISTORY
-- Person      Date               Version 	 		  Comments
-- ---------      ------          ------------    ------------------------------
-- Brad        27-Feb-2012        1.5             HPQC 13405 Performance Issue OIISCHED
--                                                Added no_data_found exception handling for description fetching in
--                                                reference_codes table in functions: event_sub_type_desc and event_type_desc
-- Nasir       22-DEC-2011        1.4             Replace max(description) with description when fetching description from reference_codes table in  event_sub_type_desc and event_type_desc procedure
-- David Ng    02-SEP-2009        1.3             Materialize schedules with PS_CATEGORY = EXT_MOV
--                                                and materialize the IN schedules
-- Niko        09-APR-2009        1.2             Replaced Nomis with Elite
--                                                Added PROCEDURE show_version
-- Niko        30-Jan-2008        1.1             Issue No.  4527 : fixed : form validats schedule record with itself and displays schedule conflict
-- Niko        12-Mar-2007        1.0             Removed 2 columns in offender_ind_schedules table and added prov_stat_code column
-- GJC         06-Aug-2006 Stop auto closing ACP allocations
-- GJC         04-Aug-2006 Defect 4437, PARENT_CODE should be used not PARENT_DOMAIN in
--                         select where cluase when inserting OFFENDER_COURSE_ATTENDANCES
-- David Ng    03-Jan-2006 Initial version 10.2.0
-- David Ng    11-Jan-2006 Court event sub-type based on MOVE_RSN domain
-- David Ng    20-Jan-2006 Lock event - Lock before comparing checksum
-- David Ng    24-Jan-2006 Drop submit batch procedure
-- David Ng    15-Feb-2006 Drop Offender_Schedule_Outcomes
--                         Add V_Offender_Course_Events
-- David Ng    19-May-2006 Add Offender_Release_Details to Schedules
--                         Tune materialization of virtual schedules
-- David Ng    24-May-2006 Add Audit Module Info
-- David Ng    11-AUg-2006 Add Accredited programme Catch up Session
-- David Ng    15-AUg-2006 Populate To_Agy_Loc_ID in Offender Course Attendance
-- David Ng    23-Aug-2006 Populate Session No for Offender Course_Attendance
--
*/
@Service
public class TagScheduleServiceImpl implements TagScheduleService {
	@Autowired
	private TagScheduleRepository tagScheduleRepository;
	@Autowired
	private VOffenderAllSchedules2TuService vOffenderAllSchedules2TuService;
	
	public static  Date currentScheduleDate ;
	
	private static Logger logger = LogManager.getLogger(TagScheduleServiceImpl.class);

	@Override
	public Integer createSchedule(final OffenderIndSchedules bean) {
		Integer eventId = null;
		if (bean.getEventId() != null) {
			eventId = bean.getEventId();
		}
		tagScheduleRepository.offnderIndScchedule(bean);
		return eventId;
	}

	@Override
	@Transactional
	public Integer deleteSchedule(final BigDecimal eventId,String modifyUserId) {
		return tagScheduleRepository.deleteScheduleDeleteOperation(eventId,modifyUserId);
	}

	public Long GetIntoCheckSum(final Long eventId) {
		final Long vCheckSum = null;
		Long pCheckSum;
		pCheckSum = tagScheduleRepository.GetIntoCheckSum(eventId);
		if (pCheckSum == vCheckSum) {
			final Long vRowId = tagScheduleRepository.GetIntoRowId(eventId);
		}
		return pCheckSum;
	}

	public Long checkSum(final Date pTimestamp) {
		return tagScheduleRepository.getCheckSumNum(pTimestamp);

	}

	@Override
	public Integer checkScheduleConflict(final VOffenderAllSchedules schedules) {
		return tagScheduleRepository.checkScheduleConflict(schedules);
	}

	@Override
	public Integer checkScheduleConflict(final Long pOffenderBookId, final Date pEventDate, final Integer pEventId) {
		return tagScheduleRepository.checkScheduleConflict(pOffenderBookId, pEventDate, pEventId);
	}

	@Override
	public Integer setScheduleStatus(VOffenderAllSchedules2 obj) {
		return tagScheduleRepository.setScheduleStatus(obj);
	}

	@Override
	@Async
	public void flushSchedules() {
		try {
			LocalDate currentDate = new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			Date currTimeStamp = new SimpleDateFormat("yyyy-MM-dd").parse(currentDate.toString());
			while (scheduleDate().compareTo(currTimeStamp)<0) {
				LocalDateTime locDate =  currentScheduleDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
				locDate = locDate.plusDays(1);
				Date newSchDate = Date.from(locDate.atZone(ZoneId.systemDefault()).toInstant());
				setScheduleDate(newSchDate);
				flushDailySchedules(newSchDate);
			}	
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " Error in flushSchedules  :: ");
		}
		tagScheduleRepository.updateoffProgramstatus();
		tagScheduleRepository.updateCourseSchDays();
	}
		
	

	@Override
	public Date scheduleDate() {
		if(currentScheduleDate == null) {
			currentScheduleDate = tagScheduleRepository.scheduleDate();
		}
		return currentScheduleDate;
		
	}

	@Override
	public Integer setScheduleDate(Date pScheduleDate) {
		currentScheduleDate = pScheduleDate;
		Integer retVal = tagScheduleRepository.updateScheduleDate(pScheduleDate);
		return retVal;
	}

	@Override
	public void flushDailySchedules(Date pScheduleDate) {
		logger.info(this.getClass().getName() + " materializePrgSchA method call");
		tagScheduleRepository.materializePrgSchA(pScheduleDate);
		logger.info(this.getClass().getName() + " materializePrgSchB method call");
		tagScheduleRepository.materializePrgSchB(pScheduleDate); 
		logger.info(this.getClass().getName() + " getVoffAllSch method call");
		List<VOffenderAllSchedules2> oldRec = tagScheduleRepository.getVoffAllSch(pScheduleDate);
		try {
			for (VOffenderAllSchedules2 obj : oldRec) {
				OffenderIndSchedules oldObj = new OffenderIndSchedules();
				BeanUtils.copyProperties(obj, oldObj);
				vOffenderAllSchedules2TuService.vOffenderAllSchedules2TuTrigger(oldObj, oldObj, oldObj.getRecordSource(), null);
			}
		} catch (Exception e) {
			logger.error(this.getClass().getName() + "Error in vOffenderAllSchedules2TuTrigger ");
		}
		tagScheduleRepository.flushDailySchUpdOffIndSch(pScheduleDate);
		tagScheduleRepository.flushDailySchUpdOffCourse(pScheduleDate);
		List<VOffenderAllSchedules2> oldRecExp = tagScheduleRepository.getVoffAllSchExp(pScheduleDate);
		try {
			for (VOffenderAllSchedules2 obj : oldRecExp) {
				OffenderIndSchedules oldObj = new OffenderIndSchedules();
				BeanUtils.copyProperties(obj, oldObj);
				OffenderIndSchedules newObj = new OffenderIndSchedules();
				BeanUtils.copyProperties(oldObj,newObj);
				newObj.setEventStatus("EXP");
				vOffenderAllSchedules2TuService.vOffenderAllSchedules2TuTrigger(newObj, oldObj, oldObj.getRecordSource(), null);
			}
		} catch (Exception e) {
			logger.error(this.getClass().getName() + "Error in vOffenderAllSchedules2TuTrigger ");
		}
	}
	
	
	
	
}

package net.syscon.s4.triggers.impl;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.im.beans.VOffenderCourseAttendances;
import net.syscon.s4.inst.institutionalactivities.maintenance.beans.VOffenderCourseEvents;
import net.syscon.s4.triggers.OffenderCourseAttendances;
import net.syscon.s4.triggers.OffenderCourseAttendancesT1Service;
import net.syscon.s4.triggers.OffenderCourseAttndnsTwfService;
import net.syscon.s4.triggers.VOffenderCourseEventsTuRepository;
import net.syscon.s4.triggers.VOffenderCourseEventsTuService;

@Service
public class VOffenderCourseEventsTuServiceImpl implements VOffenderCourseEventsTuService {
	@Autowired
	VOffenderCourseEventsTuRepository vOffenderCourseEventsTuRepository;
	@Autowired
	private OffenderCourseAttendancesT1Service offenderCourseAttendancesT1Service;
	@Autowired
	private OffenderCourseAttndnsTwfService offenderCourseAttndnsTwfService;

	@Override
	public Integer vOffenderCourseEventsTuTgr(final VOffenderCourseEvents oldObj, final VOffenderCourseEvents newObj) {
		Integer result = 0;
		VOffenderCourseEvents targetObj = new VOffenderCourseEvents();
		OffenderCourseAttendances offCa = new OffenderCourseAttendances();
		BeanUtils.copyProperties(newObj, offCa);
		if (null == oldObj || (Optional.ofNullable(oldObj).isPresent() && null == oldObj.getEventId())) {
			targetObj = new VOffenderCourseEvents();
			BeanUtils.copyProperties(newObj, targetObj);
			targetObj.setInTime(newObj.getInTime());
			targetObj.setOutTime(newObj.getOutTime());
			targetObj.setStartTime(newObj.getStartTime());
			targetObj.setEndTime(newObj.getEndTime());
			targetObj.setOffPrgrefId(newObj.getOffPrgrefId());
			if (newObj.getEventIdTemp() == null) {
				targetObj.setEventId(vOffenderCourseEventsTuRepository.getEventId());
			}
			result = vOffenderCourseEventsTuRepository.insert(targetObj);
			offenderCourseAttndnsTwfService.offenderCourseAttndnsTwf(offCa, "INSERTING");
		} else {
			targetObj = new VOffenderCourseEvents();
			BeanUtils.copyProperties(newObj, targetObj);
			targetObj.setInTime(newObj.getInTime());
			targetObj.setOutTime(newObj.getOutTime());
			targetObj.setStartTime(newObj.getStartTime());
			targetObj.setEndTime(newObj.getEndTime());
			targetObj.setOffPrgrefId(newObj.getOffPrgrefId());
			targetObj.setEventId(oldObj.getEventId());
			targetObj.setModifyUserId(newObj.getCreateUserId());
			VOffenderCourseAttendances vOff = new VOffenderCourseAttendances();
			BeanUtils.copyProperties(newObj, vOff);
			vOff.setEventId(newObj.getEventId()!= null ?newObj.getEventId().longValue(): oldObj.getEventId() != null ? oldObj.getEventId().longValue() : null);
			vOff.setEventStatus(newObj.getEventStatus());
			vOff.setEventOutcome(newObj.getEventOutcome());
			vOff.setEventDate(newObj.getEventDate());
			vOff.setOffPrgrefId(newObj.getOffPrgrefId() !=null?newObj.getOffPrgrefId().longValue():null);
			VOffenderCourseAttendances  triObj = offenderCourseAttendancesT1Service.offenderCourseAttendancesT1(vOff);
			targetObj.setEventStatus(triObj.getEventStatus());
			result = vOffenderCourseEventsTuRepository.update(targetObj);
			
			 offenderCourseAttndnsTwfService.offenderCourseAttndnsTwf(offCa, "UPDATING");
		}
		if(result ==1) {
			result = Integer.valueOf(targetObj.getEventId().toString());
		}
		return result;
	}

}

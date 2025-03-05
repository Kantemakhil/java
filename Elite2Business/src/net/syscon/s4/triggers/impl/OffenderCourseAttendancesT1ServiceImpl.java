package net.syscon.s4.triggers.impl;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.im.beans.VOffenderCourseAttendances;
import net.syscon.s4.triggers.OffenderCourseAttendancesT1Repository;
import net.syscon.s4.triggers.OffenderCourseAttendancesT1Service;
@Service
public class OffenderCourseAttendancesT1ServiceImpl implements OffenderCourseAttendancesT1Service{
	@Autowired
	private OffenderCourseAttendancesT1Repository offenderCourseAttendancesT1Repository;
	@Override 
	public VOffenderCourseAttendances offenderCourseAttendancesT1(final VOffenderCourseAttendances newOffenderCourseAttendances) {
		if(Objects.nonNull(newOffenderCourseAttendances)) {
			VOffenderCourseAttendances oldOffenderCourseAttendances = offenderCourseAttendancesT1Repository.getOffenderCourseAttendances(newOffenderCourseAttendances.getEventId());
			if(Objects.isNull(newOffenderCourseAttendances.getSealFlag()) || StringUtils.equals(newOffenderCourseAttendances.getSealFlag(), newOffenderCourseAttendances.getSealFlag())) {
				if(StringUtils.equals(newOffenderCourseAttendances.getEventStatus(), "DEL")) {
					return newOffenderCourseAttendances;
				} 
				if(Objects.nonNull(newOffenderCourseAttendances.getEventOutcome()) &&
						Objects.isNull(oldOffenderCourseAttendances.getEventOutcome()) &&
						("SCH".equals(newOffenderCourseAttendances.getEventStatus()) 
								&& "EXP".equals(newOffenderCourseAttendances.getEventStatus()))){
					newOffenderCourseAttendances.setEventStatus("COMP");
				}else if(!"CANC".equals(newOffenderCourseAttendances.getEventStatus()) 
						&& !"COMP".equals(newOffenderCourseAttendances.getEventStatus()) && !"PROG_CANC".equals(newOffenderCourseAttendances.getEventStatus())) {
					Instant now = Instant.now();
					Instant yesterday = now.minus(1, ChronoUnit.DAYS);
					Instant eventDate=newOffenderCourseAttendances.getEventDate().toInstant();
					if(eventDate.equals(yesterday) || eventDate.isBefore(yesterday)) {
						newOffenderCourseAttendances.setEventStatus("EXP");
					}else {
						newOffenderCourseAttendances.setEventStatus("SCH");
					}
				}
			}
		}
		return newOffenderCourseAttendances;
	}

}

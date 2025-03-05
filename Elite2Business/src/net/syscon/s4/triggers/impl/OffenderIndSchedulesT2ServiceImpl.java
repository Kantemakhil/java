package net.syscon.s4.triggers.impl;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.genericservices.CustomException;
import net.syscon.s4.inst.schedules.bean.OffenderIndSchedules;
import net.syscon.s4.triggers.OffenderIndSchedulesT2Repository;
import net.syscon.s4.triggers.OffenderIndSchedulesT2Service;
/*===========================================================================================================
Below comments are copied from OFFENDER_IND_SCHEDULES_T2 Trigger
=============================================================================================================*/
/* MODIFICATION HISTORY

Person       Date        Version       Comments
David Ng     15/10/2005  2.0           NOMIS project(10.2.0)
David Ng     07/07/2006  2.1           Add Syn event_status and Event_Outcome
Venu		07/09/2006  2.2           In case of Prison schedule when a schedule is confirmed it is updated as COMP
                                       but the trigger was reverting it back to SCH, incorrect and it is fixed now.
Vikas Grover 28/Jun/2007 1.1 	  Taken from UK ver 2.2
*/
@Service
public class OffenderIndSchedulesT2ServiceImpl implements OffenderIndSchedulesT2Service {
	Logger logger = LogManager.getLogger(OffenderIndSchedulesT2ServiceImpl.class.getName());
	@Autowired
	private OffenderIndSchedulesT2Repository offenderIndSchedulesT2Repository;

	@Override
	public List<OffenderIndSchedules> offenderIndSchedulesT2Tgr(
			final List<OffenderIndSchedules> offenderIndSchedulesList) throws CustomException {
		final LocalDate localDate = LocalDate.now().minusDays(1);
		if (!offenderIndSchedulesList.isEmpty()) {
			for (final OffenderIndSchedules newObj : offenderIndSchedulesList) {
				final OffenderIndSchedules old = offenderIndSchedulesT2Repository.getOffenderIndSchedules(newObj);
				if (newObj.getSealFlag()==null ||  (old != null && (newObj.getSealFlag().equals(old.getSealFlag())))) {
					Integer vNumrows = offenderIndSchedulesT2Repository.eventType(newObj.getEventType());
					if (vNumrows == 0) {
						logger.info("Invalid Event Type");
					}
					vNumrows = offenderIndSchedulesT2Repository.eventStatus(newObj.getEventStatus());
					if (vNumrows == 0) {
						logger.info("Invalid Event Status");
					}
					if(newObj!=null && newObj.getEventOutcome()!=null) {
					if (Optional.ofNullable(newObj.getEventOutcome()).isPresent()) {
						if (old.getEventOutcome() == null
								&& ("SCH".equals(newObj.getEventStatus()) || "".equals(newObj.getEventStatus()))) {
							newObj.setEventStatus("COMP");
						}
					} 
					}
					 else {
						if(newObj.getEventStatus()!=null) {
						if (!("CANC".equals(newObj.getEventStatus()))) {
							final Date sysdateMinusOne = Date
									.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
							if (newObj!=null && newObj.getEventDate()!=null && newObj.getEventDate().compareTo(sysdateMinusOne) < 0) {
								newObj.setEventStatus("EXP");
							} else if (newObj!=null && newObj.getEventStatus()!=null && newObj.getEventStatus() == null) {
								newObj.setEventStatus("SCH");
							}
						}
					}
					}
				}
			}
		}
		return offenderIndSchedulesList;
	}

}

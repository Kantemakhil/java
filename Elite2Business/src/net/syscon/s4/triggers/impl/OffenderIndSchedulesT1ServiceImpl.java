package net.syscon.s4.triggers.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.inst.schedules.bean.OffenderIndSchedules;
import net.syscon.s4.triggers.OffenderIndSchedulesT1Repository;
import net.syscon.s4.triggers.OffenderIndSchedulesT1Service;
/*
============================================================
Below comments are copied from OFFENDER_IND_SCHEDULES_T1 Trigger

============================================================
  MODIFICATION HISTORY
   Person       Date      version      Comments
   David Ng  12/10/2005  2.0          Popluate reference ID
*/
@Service
public class OffenderIndSchedulesT1ServiceImpl implements OffenderIndSchedulesT1Service {
	Logger logger = LogManager.getLogger(OffenderIndSchedulesT1ServiceImpl.class.getName());
	@Autowired
	OffenderIndSchedulesT1Repository offenderIndSchedulesT1Repository;

	@Override
	public List<OffenderIndSchedules> offenderIndSchedulesT1Tgn(
			final List<OffenderIndSchedules> offenderIndSchedulesList) {
		if (!offenderIndSchedulesList.isEmpty()) {
			try {
				for (final OffenderIndSchedules newObj : offenderIndSchedulesList) {
					final OffenderIndSchedules old = offenderIndSchedulesT1Repository.getOffenderIndSchedules(newObj);
					if ( newObj.getSealFlag() == null
							|| (old != null && (newObj.getSealFlag().equals(old.getSealFlag())))) {
						if ("PRG".equals(newObj.getEventType())) {
							newObj.setCrsSchId(newObj.getReferenceId());
						} else if ("TAP".equals(newObj.getEventType())) {
							newObj.setTempAbsSchId(newObj.getReferenceId());
						}
					}
				}
			} catch (Exception e) {
				logger.error("offenderIndSchedulesT1Tgn", e);
			}
		}
		return offenderIndSchedulesList;
	}

}

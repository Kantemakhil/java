package net.syscon.s4.triggers.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.triggers.TempAbsenceTimeTables;
import net.syscon.s4.triggers.TempAbsenceTimeTablesT1Repository;
import net.syscon.s4.triggers.TempAbsenceTimeTablesT1Service;

@Service
public class TempAbsenceTimeTablesT1ServiceImpl implements TempAbsenceTimeTablesT1Service {
	private static final Logger logger = LogManager.getLogger(TempAbsenceTimeTablesT1ServiceImpl.class.getName());
	@Autowired
	TempAbsenceTimeTablesT1Repository tempAbsenceTimeTablesT1Repository;

	@Override
	public List<TempAbsenceTimeTables> tempAbsenceTimeTablesT1Tgr(
			final List<TempAbsenceTimeTables> tempAbsenceTimeTablesList) {
		if (!tempAbsenceTimeTablesList.isEmpty()) {
			try {
				for (final TempAbsenceTimeTables newObj : tempAbsenceTimeTablesList) {
					final TempAbsenceTimeTables old = tempAbsenceTimeTablesT1Repository
							.getTempAbsenceTimeTables(newObj);
					if (newObj.getSealFlag() == null
							|| (old != null && (newObj.getSealFlag().equals(old.getSealFlag())))) {
						if (null != newObj && null == newObj.getTempAbsSchId()) {
							newObj.setTempAbsSchId(tempAbsenceTimeTablesT1Repository.lvTempAbsSchId());
						}
					}
				}
			} catch (final Exception e) {
				logger.error("tempAbsenceTimeTablesT1Tgr", e);
			}
		}
		return tempAbsenceTimeTablesList;
	}

}

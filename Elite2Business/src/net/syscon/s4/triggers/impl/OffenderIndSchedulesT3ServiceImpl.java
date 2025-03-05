package net.syscon.s4.triggers.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.genericservices.CustomException;
import net.syscon.s4.inst.schedules.bean.OffenderIndSchedules;
import net.syscon.s4.triggers.OffenderIndSchedulesT3Repository;
import net.syscon.s4.triggers.OffenderIndSchedulesT3Service;
@Service
public class OffenderIndSchedulesT3ServiceImpl implements OffenderIndSchedulesT3Service {
@Autowired
OffenderIndSchedulesT3Repository offenderindschedulest3repository;
	@Override
	public Integer getVnumRows(OffenderIndSchedules object) throws CustomException {
		final Integer vNumrowsOff =  offenderindschedulest3repository.getVnumRows(object);
		if (vNumrowsOff > 0) {
		}
		return vNumrowsOff;
	}
	
}

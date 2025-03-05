package net.syscon.s4.pkgs.oidintmv.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import net.syscon.s4.inst.schedules.bean.VOffenderAllSchedules;
import net.syscon.s4.pkgs.oidintmv.OidintmvPkgRepository;
import net.syscon.s4.pkgs.oidintmv.OidintmvPkgService;

@Service
public class OidintmvPkgServiceImpl implements OidintmvPkgService {
	@Autowired
	private OidintmvPkgRepository oidintmvRepository;

	@Override
	public List<VOffenderAllSchedules> getSchedules(final VOffenderAllSchedules objSearchDao, String pMoveType) {
		final List<VOffenderAllSchedules> returnList1 = oidintmvRepository.getSchedules(objSearchDao);
		final List<VOffenderAllSchedules> returnList2 = oidintmvRepository.getUnschedulesCur(objSearchDao);
		final List<VOffenderAllSchedules> retList = new ArrayList<VOffenderAllSchedules>();
		final VOffenderAllSchedules retObj = new VOffenderAllSchedules();
		if ("SCHEDULED".equals(pMoveType.toUpperCase())) {
			for (final VOffenderAllSchedules item : returnList1) {
				// -- Get last row used and add one. Integer vNextRow;
				// v_next_row := NVL (v_schedule_tab.LAST, 0) + 1;
				retObj.setAgyLocId(objSearchDao.getAgyLocId());
				retObj.setEventId(item.getEventId());
				retObj.setOffenderId(item.getOffenderId());
				retObj.setOffenderBookId(item.getOffenderBookId());
				retObj.setOffenderIdDisplay(item.getOffenderIdDisplay());
				retObj.setOffenderLastName(item.getOffenderLastName());
				retObj.setOffenderFirstName(item.getOffenderFirstName());
				retObj.setLivingUnitId(item.getLivingUnitId());
				retObj.setLivingUnitDesc(item.getLivingUnitDesc());
				retObj.setEventType(item.getEventType());
				retObj.setEventTypeDesc(item.getEventTypeDesc());
				retObj.setEventSubType(item.getEventType());
				retObj.setEventSubTypeDesc(item.getEventSubTypeDesc());
				retObj.setAgencyImlId(item.getAgencyImlId());
				retObj.setAgencyImlDesc(item.getAgencyImlDesc());
				retObj.setToInternalLocationId(item.getToInternalLocationId());
				retObj.setToInternalLocationDesc(item.getToIntLocUserDesc());
				retObj.setConfirmMove("N");
				retObj.setReferenceId(item.getReferenceId());
				retObj.setEventStatus("SCH");
				retObj.setStartTime(item.getStartTime());
				retList.add(retObj);
			}
		} else {
			for (final VOffenderAllSchedules item : returnList2) {
				// -- Get last row used and add one.
				// v_next_row := NVL (v_schedule_tab.LAST, 0) + 1;
				retObj.setAgyLocId(objSearchDao.getAgyLocId());
				retObj.setOffenderId(item.getOffenderId());
				retObj.setOffenderBookId(item.getOffenderBookId());
				retObj.setOffenderIdDisplay(item.getOffenderIdDisplay());
				retObj.setOffenderLastName(item.getOffenderLastName());
				retObj.setOffenderFirstName(item.getOffenderFirstName());
				retObj.setLivingUnitId(item.getLivingUnitId());
				retObj.setLivingUnitDesc(item.getLivingUnitDesc());
				retObj.setAgencyImlId(item.getAgencyImlId());
				retObj.setAgencyImlDesc(item.getAgencyImlDesc());
				retObj.setToInternalLocationId(null);
				retObj.setToInternalLocationDesc(null);
				retObj.setConfirmMove("N");
				retList.add(retObj);
			}
		}
		return retList;
	}
}

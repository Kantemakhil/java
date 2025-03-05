package net.syscon.s4.triggers.impl;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.ApplicationConstants;
import net.syscon.s4.common.beans.OffenderBookings;
import net.syscon.s4.im.beans.CasePlans;
import net.syscon.s4.triggers.CasePlansT1Repository;
import net.syscon.s4.triggers.CasePlansT1Service;
import net.syscon.s4.triggers.OffenderBookingsBkadmTrgService;
import net.syscon.s4.triggers.OffenderBookingsT2Service;
import net.syscon.s4.triggers.OffenderBookingsT7Service;
import net.syscon.s4.triggers.OffendersBookVineIntfTrgService;


@Service
public class CasePlansT1ServiceImpl implements CasePlansT1Service{
	
	@Autowired
	CasePlansT1Repository casePlansT1Repository;
	@Autowired
	private OffenderBookingsT7Service offenderBookingsT7Service;
	@Autowired
	private OffendersBookVineIntfTrgService offendersBookVineIntfTrgService;
	@Autowired
	private OffenderBookingsT2Service offenderBookingsT2Service;
	@Autowired
	private OffenderBookingsBkadmTrgService offenderBookingsBkadmTrgService;
	

	@Transactional
	@Override
	public void casePlansT1(CasePlans casePlans) {
		if(Objects.nonNull(casePlans)) {
			OffenderBookings oldRec = new OffenderBookings();
			List<OffenderBookings>  oldRecList = casePlansT1Repository.getOffBookOldRec(casePlans.getOffenderBookId());
			if(oldRecList!= null)
			 oldRec =  oldRecList.get(0);
			OffenderBookings  obj = new OffenderBookings();
			obj.setOffenderBookId(casePlans.getOffenderBookId());
			obj.setAgyLocId(casePlans.getAgyLocId());
			obj.setCreateUserId(casePlans.getCreateUserId());
			obj.setModifyUserId(casePlans.getCreateUserId());
			if("ACTIVE".equals(casePlans.getCasePlanStatus()) && Objects.nonNull(casePlans.getEndDate())){
				//before each row
				offenderBookingsT2Service.offenderBookingsT2(obj, oldRec);
				
				casePlansT1Repository.updateOffenderBookings(casePlans);
				//after each row
				offenderBookingsT7Service.offenderBookingsT7Trigger(obj);
				offendersBookVineIntfTrgService.OffendersBookVineIntfTrgTrigger(obj,ApplicationConstants.UPDATING);
				offenderBookingsBkadmTrgService.offenderBookingsBkadmTrg(oldRec, obj, "UPDATING");
			}else {
				casePlans.setRole(null);
				casePlans.setSacStaffId(null);
				//before each row
				offenderBookingsT2Service.offenderBookingsT2(obj, oldRec);
				
				casePlansT1Repository.updateOffenderBookings(casePlans);
				//after each row
				offenderBookingsT7Service.offenderBookingsT7Trigger(obj);
				offendersBookVineIntfTrgService.OffendersBookVineIntfTrgTrigger(obj,ApplicationConstants.UPDATING);
				offenderBookingsBkadmTrgService.offenderBookingsBkadmTrg(oldRec, obj, "UPDATING");
			}
		}
	}

}

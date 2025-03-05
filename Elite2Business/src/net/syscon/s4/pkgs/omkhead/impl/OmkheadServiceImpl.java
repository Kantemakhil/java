package net.syscon.s4.pkgs.omkhead.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.common.ApplicationConstants;
import net.syscon.s4.common.beans.OffenderBookings;
import net.syscon.s4.pkgs.omkhead.OmkheadRepository;
import net.syscon.s4.pkgs.omkhead.OmkheadService;
import net.syscon.s4.triggers.OffenderBookingsT7Service;
import net.syscon.s4.triggers.OffendersBookVineIntfTrgService;

@Service("OmkheadServiceImpl_pkg")
public class OmkheadServiceImpl implements OmkheadService {

	private static final String IN = "IN";
	@Autowired
	private OmkheadRepository omkheadRepository;
	private static Logger logger = LogManager.getLogger(OmkheadServiceImpl.class.getName());
	
	@Autowired
	private OffendersBookVineIntfTrgService offendersBookVineIntfTrgService;
	
	@Autowired
	private OffenderBookingsT7Service offenderBookingsT7Service;
	
	@Override
	public Integer toggleInOutStatus(final Long offBookId, final String userName) {
		Integer returnVal = null;
		try {
			final String vInOutStatus = omkheadRepository.inOutStatus(offBookId);
			OffenderBookings bookings=new OffenderBookings();
			bookings.setOffenderBookId(offBookId);bookings.setCreateUserId(userName);
			bookings.setModifyUserId(userName);
			if (IN.equals(vInOutStatus)) {
				omkheadRepository.offenderBookingsUpdateOut(offBookId, userName);
				offendersBookVineIntfTrgService.OffendersBookVineIntfTrgTrigger(bookings,ApplicationConstants.UPDATING);
				offenderBookingsT7Service.offenderBookingsT7Trigger(bookings);
			}else {
				omkheadRepository.offenderBookingsUpdateIn(offBookId, userName);
				offendersBookVineIntfTrgService.OffendersBookVineIntfTrgTrigger(bookings,ApplicationConstants.UPDATING);
				offenderBookingsT7Service.offenderBookingsT7Trigger(bookings);
			}
		} catch (Exception e) {
			logger.error("toggleInOutStatus: ", e);
			returnVal = 1;
		}

		return returnVal;
	}
}

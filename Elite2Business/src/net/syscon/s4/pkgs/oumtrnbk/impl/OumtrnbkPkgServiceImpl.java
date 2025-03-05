package net.syscon.s4.pkgs.oumtrnbk.impl;

import java.util.Date;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.common.beans.OffenderBookings;
import net.syscon.s4.pkgs.oumtrnbk.OumtrnbkPkgRepository;
import net.syscon.s4.pkgs.oumtrnbk.OumtrnbkPkgService;

@Service
public class OumtrnbkPkgServiceImpl implements OumtrnbkPkgService {
	private static Logger logger = LogManager.getLogger(OumtrnbkPkgServiceImpl.class.getName());

	@Autowired
	private OumtrnbkPkgRepository oumtrnbkpkgrepo;

	@Override
	public Boolean chkOffendersForTransfer(OffenderBookings bean) {
		Date lvTrustBeginDate = null;
		Date lvTrustEndDate = null;
		if (Optional.of(bean).isPresent() && bean.getFromOffRootId() != null && bean.getToOffRootId() != null
				&& bean.getFromOffRootId() == bean.getToOffRootId()) {

		}
		return null;
	}

	@Override
	public Boolean isOneBookingOnly(OffenderBookings bean) {
		Integer count = oumtrnbkpkgrepo.isOneBookingOnly(bean);
		return count == 0 ? false : true;
	}

	@Override
	public Boolean isBothBookingsActive(OffenderBookings bean) {
		Integer lvFromActive = oumtrnbkpkgrepo.getInstBookActive(bean.getFromOffenderBookId());
		Integer lvToActive = oumtrnbkpkgrepo.getToBookActive(bean.getToOffRootId());
		return (lvFromActive > 0 && lvToActive > 0) ? true : false;
	}
	
	@Override
	public Boolean isInactiveAfterActive(OffenderBookings bean) {
		Date lvFromOffBookDate = null;// another method call
		Date lvToOffBookDate = null;// another method call
		Integer lvExistsActive = 0;
		if (lvFromOffBookDate.compareTo(lvToOffBookDate) > 0) {
			lvExistsActive = oumtrnbkpkgrepo.getActiveBookingCur(bean.getToOffenderBookId());
		} else {
			lvExistsActive = oumtrnbkpkgrepo.getActiveBookingCur(bean.getFromOffenderBookId());
		}
		return lvExistsActive > 0 ? true : false;
	}

}

package net.syscon.s4.triggers.impl;

import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.common.beans.OffenderBookings;
import net.syscon.s4.common.beans.OffenderExternalMovements;
import net.syscon.s4.pkgs.tag_establishment.TagEstablishmentService;
import net.syscon.s4.triggers.OffenderBookingAgyLocsT1Repository;
import net.syscon.s4.triggers.OffenderBookingsT10Service;

@Service
public class OffenderBookingsT10ServiceImpl implements OffenderBookingsT10Service {
	Logger logger = LogManager.getLogger(OffenderBookingsT10ServiceImpl.class);
	@Autowired
	TagEstablishmentService tagEstablishmentService;
	@Autowired
	OffenderBookingAgyLocsT1Repository offenderBookingAgyLocsT1Repository;

	@Override
	public OffenderExternalMovements offenderBookingsT10Trigger(final OffenderBookings offenderBookings) {
		OffenderExternalMovements offenderExternalMovements = new OffenderExternalMovements();
		final OffenderBookings offenderBookingsOld = offenderBookingAgyLocsT1Repository
				.getOffenderBookings(offenderBookings.getOffenderBookId());
		try {
			if (Optional.ofNullable(offenderBookingsOld).isPresent()
					&& Optional.ofNullable(offenderBookingsOld.getLivingUnitId()).isPresent()) {
				offenderExternalMovements = tagEstablishmentService
						.adjustOccupants(offenderBookings.getLivingUnitId().intValue(), -1,offenderBookings.getCreateUserId()!=null?offenderBookings.getCreateUserId():offenderBookings.getModifyUserId());
			}
		} catch (final Exception e) {
			logger.error("offenderBookingsT10Trigger", e);
			return null;
		}
		return offenderExternalMovements;
	}

}

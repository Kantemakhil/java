package net.syscon.s4.triggers.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.pkgs.OffenderBookingDetails;
import net.syscon.s4.pkgs.tag_utils.TagUtilsService;
import net.syscon.s4.triggers.OffenderBookingDetailsT1Service;

@Service
public class OffenderBookingDetailsT1ServiceImpl implements OffenderBookingDetailsT1Service {
	private static Logger logger = LogManager.getLogger(OffenderBookingDetailsT1ServiceImpl.class);
	@Autowired
	TagUtilsService tagUtilsService;

	@Override
	public OffenderBookingDetails offenderBookingDetailsT1Trigger(final OffenderBookingDetails offenderBookingDetails) {
		try {
			final String vSp = tagUtilsService.getSysProfile("CLIENT", "ADM_CSA");
			if ("N".equals(vSp)) {
				offenderBookingDetails.setCellSharingAlertFlag("N");
			} else {
				offenderBookingDetails.setCellSharingAlertFlag("Y");
			}
		} catch (final Exception e) {
			logger.error("offenderBookingDetailsT1Trigger", e);
			return null;
		}
		return offenderBookingDetails;
	}

}

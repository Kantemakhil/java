package net.syscon.s4.triggers.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.common.beans.OffenderBookings;
import net.syscon.s4.common.beans.OffenderExternalMovements;
import net.syscon.s4.im.beans.Offenders;
import net.syscon.s4.pkgs.OrAudit;
import net.syscon.s4.triggers.OffExtMvVineIntfTrgRepository;
import net.syscon.s4.triggers.OffExtMvVineIntfTrgService;
/* ============================================================
Below comments are copied from OFF_EXT_MV_VINE_INTF_TRG Trigger
============================================================== */
/*
   MODIFICATION HISTORY (Please put version history in a reverse-chronological order below)
   --------------------
   Person      Date        Comments
   ---------   ---------   ------------------------------------------
   EDWARD      11-DEC-2008 Initial version.
   Edward      06-JAN-2009 Use tag_error package for exception handling.
*/
@Service
public class OffExtMvVineIntfTrgServiceImpl implements OffExtMvVineIntfTrgService {
	private final Logger logger = LogManager.getLogger(OffExtMvVineIntfTrgServiceImpl.class);
	@Autowired
	OffExtMvVineIntfTrgRepository offExtMvVineIntfTrgRepository;
	@Override
	public Integer offExtMvVineIntfTrg(final OffenderExternalMovements offenderExtMov) {
		List<OrAudit> orAuditList = new ArrayList<OrAudit>();
		Integer result = 0;
		OrAudit orAudit = new OrAudit();
		final OffenderBookings curBookOr = offExtMvVineIntfTrgRepository.curBookOr(offenderExtMov.getOffenderBookId());
		if ("REL".equals(offenderExtMov.getMovementType()) || ("TRN".equals(offenderExtMov.getMovementType())
				&& "OJ".equals(offenderExtMov.getMovementReasonCode()))) {
			if (Optional.ofNullable(curBookOr).isPresent()) {
				final Offenders curOff = offExtMvVineIntfTrgRepository.curOff(curBookOr.getOffenderId());
				try {
					orAudit.setOffenderId(orAudit.getOffenderId() == null ? curBookOr.getOffenderId().longValue() : orAudit.getOffenderId() );
					dataMapper(offenderExtMov, orAudit, curBookOr, curOff);
					orAuditList.add(orAudit);
					result = offExtMvVineIntfTrgRepository.updateOrAudit(orAuditList);
					if (result == 0) {
						orAudit = new OrAudit();
						orAuditList = new ArrayList<OrAudit>();
						orAudit.setOffenderId(orAudit.getOffenderId() == null ? curBookOr.getOffenderId().longValue() : orAudit.getOffenderId() );
						dataMapper(offenderExtMov, orAudit, curBookOr, curOff);
						orAuditList.add(orAudit);
						result = offExtMvVineIntfTrgRepository.insertOrAudit(orAuditList);
					}
					
				} catch (final Exception e) {
					logger.error("offExtMvVineIntfTrg", e);
				}
			}
		}
		return result;
	}

	private void dataMapper(final OffenderExternalMovements offenderExtMov, final OrAudit orAudit,
			final OffenderBookings curBookOr, final Offenders curOff) {
		orAudit.setActionType("U");
		orAudit.setAgyLocId(curBookOr.getAgyLocId());
		orAudit.setOffenderIdDisplay(curOff.getOffenderIdDisplay());
		orAudit.setBookingNo(curBookOr.getBookingNo());
		orAudit.setBookingBeginDate(curBookOr.getBookingBeginDate());
		orAudit.setFirstName(curOff.getFirstName());
		orAudit.setMiddleName(curOff.getMiddleName());
		orAudit.setLastName(curOff.getLastName());
		orAudit.setBirthDate(curOff.getBirthDate());
		orAudit.setRaceCode(curOff.getRaceCode());
		orAudit.setSexCode(curOff.getSexCode());
		orAudit.setReleaseDate(offenderExtMov.getMovementDate());
		orAudit.setReleaseTime(offenderExtMov.getMovementTime());
		orAudit.setRelReasonCode(offenderExtMov.getMovementReasonCode());
		orAudit.setModifiedDate(offenderExtMov.getModifyDatetime());
		orAudit.setOffenderBookId(offenderExtMov.getOffenderBookId());
		orAudit.setModifyDatetime(offenderExtMov.getModifyDatetime());
		orAudit.setModifyUserId(offenderExtMov.getModifyUserId());
	}

}

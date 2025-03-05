package net.syscon.s4.triggers.impl;

import java.text.SimpleDateFormat;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.common.beans.OffenderBookings;
import net.syscon.s4.common.beans.OffenderExternalMovements;
import net.syscon.s4.common.beans.VHeaderBlock;
import net.syscon.s4.im.beans.Offenders;
import net.syscon.s4.pkgs.comunity_dbtrg_pkg.ComunityDbtrgPkgService;
import net.syscon.s4.pkgs.oms_miscellaneous.OmsMiscellaneousService;
import net.syscon.s4.triggers.OffenderExternalMovementsT3Repository;
import net.syscon.s4.triggers.OffenderExternalMovementsT3Service;

/*===========================================================================================================
Below comments are copied from OFFENDER_EXTERNAL_MOVEMENTS_T3 Trigger
=============================================================================================================*/

   /* MODIFICATION HISTORY
      Person      Date           Version       Comments
      ---------   -----------    ------------  --------------------------------------------------------------------
      Rajshree    07/08/2006     2.3           Corrected the version no. Defect Id 3813.
      Rajshree    07/08/2006     2.2           Removed code referencing to offender_parole_css_details.Defect ID 3813.
                                               Also removed previously commented code.
      SNEZANA     27-FEB-2001    4.11.0.0      trigger created - Notify Parole Board when offender is incarcerated
      Surya       29-Jun-2005    2.1           Tr#598:Commented the PROCESS_EMAIL_QUEUE_PBOARD, as it is not in use now.
   */

@Service
public class OffenderExternalMovementsT3ServiceImpl implements OffenderExternalMovementsT3Service {
	Logger logger = LogManager.getLogger(OffenderExternalMovementsT3ServiceImpl.class);
	@Autowired
	OffenderExternalMovementsT3Repository offenderExternalMovementsT3Repository;
	@Autowired
	ComunityDbtrgPkgService comunityDbtrgPkgService;
	@Autowired
	OmsMiscellaneousService omsMiscellaneousService;
  // Below method is call AFTER INSERT ON TABLE OFFENDER_EXTERNAL_MOVEMENTS
	@Override
	public String offenderExternalMovementsT3Trigger(final OffenderExternalMovements offenExternalMov,
			final Offenders offenders) {
		boolean lvEmailFlag;
		final SimpleDateFormat sDF = new SimpleDateFormat("DD/MM/YYYY");
		String lMsgString = null;
		String lOffenderIdDisplay;
		String lLastName;
		String lFirstName;
		final StringBuffer msg = new StringBuffer();
		// Below Method is called for Cursor to get location description
		final String getLocationCur = offenderExternalMovementsT3Repository
				.getLocationCur(offenExternalMov.getToAgyLocId());
		//Below Method is called for Cursor to check if offender is active in the community
		final OffenderBookings communityActiveCur = offenderExternalMovementsT3Repository
				.communityActiveCur(offenExternalMov);
		if (communityActiveCur == null) {
			return null;
		}

		if ("ADM".equals(offenExternalMov.getMovementType())) {
			try {
				// Below Method  to Check the automatic_case_notes table for expiry date and type CA / AIP
				lvEmailFlag = comunityDbtrgPkgService.getActiveFlag("CA", "AIP", "EMAIL");
			} catch (final Exception e) {
				lvEmailFlag = false;
				logger.info("offenderExternalMovementsT3Trigger", e);
			}

			if (lvEmailFlag == true) {
				// Belew Method for Procedure call to select Offender_ID_display/LastName/FirstName
				final VHeaderBlock vHeaderBlock = comunityDbtrgPkgService
						.commEmailParams(offenExternalMov.getOffenderBookId(),offenExternalMov.getCreateUserId());
				if (Optional.ofNullable(vHeaderBlock).isPresent()) {
					lOffenderIdDisplay = vHeaderBlock.getOffenderIdDisplay();
					lLastName = vHeaderBlock.getLastName();
					lFirstName = vHeaderBlock.getFirstName();
				} else {
					lOffenderIdDisplay = null;
					lLastName = null;
					lFirstName = null;
				}
				final String lvReason = omsMiscellaneousService.getDescCode("MOVE_RSN",
						offenExternalMov.getMovementReasonCode());
				lMsgString = msg.append(lLastName).append(" ").append(lFirstName).append(" ").append(lOffenderIdDisplay)
						.append(" admitted to ").append(getLocationCur).append(" on ")
						.append(sDF.format(offenExternalMov.getMovementDate())).append(" for reason : ")
						.append(lvReason).toString();
			}
		}
		return lMsgString;
	}

}

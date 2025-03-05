package net.syscon.s4.triggers.impl;

import java.util.Date;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.common.beans.OffenderBillingProfiles;
import net.syscon.s4.common.beans.OffenderExternalMovements;
import net.syscon.s4.im.beans.MovementReasons;
import net.syscon.s4.triggers.OffenderExternalMovementT13Repository;
import net.syscon.s4.triggers.OffenderExternalMovementT13Service;

/* ==============================================================
Below comments are copied from OFFENDER_EXTERNAL_MOVEMENT_T13 Trigger
================================================================= */
/* MODIFICATION HISTORY
Person     Date          Version     Comments
---------  -----------   ---------   ------------------------------
Edward     04-DEC-2014   1.1         HPQC#24659: Changed Movement_type to TRN for transfers outside jurisdiction.
Jagdeep    21-OCT-2014   1.0         HPQC#21693 - Initial version
*/
@Service
public class OffenderExternalMovementT13ServiceImpl implements OffenderExternalMovementT13Service {
	Logger logger = LogManager.getLogger(OffenderExternalMovementT13ServiceImpl.class);
	@Autowired
	OffenderExternalMovementT13Repository offenderExternalMovementT13Repository;

	// Below method is call after insert or update of movement_type on OFFENDER_EXTERNAL_MOVEMENTS table
	@Override
	public Integer OffenderExternalMovementT1(final OffenderExternalMovements offenderExternalMovements) {
		Integer result = 0;
		OffenderBillingProfiles target = new OffenderBillingProfiles();
		if (Optional.ofNullable(offenderExternalMovements.getOffenderBookId()).isPresent()) {
			target.setEffectiveDateEnd(offenderExternalMovements.getMovementDate());
			target.setOffenderBookingId(offenderExternalMovements.getOffenderBookId());
			target.setEffectiveDateEnd(offenderExternalMovements.getMovementDate());
			target.setModifyUserId(offenderExternalMovements.getCreateUserId()!=null?offenderExternalMovements.getCreateUserId():offenderExternalMovements.getModifyUserId());
			target.setModifyDatetime(new Date());
		}
		try {
			// below method is used to get the CLOSE_CONTACT_FLAG
			final MovementReasons closeBookingCur = offenderExternalMovementT13Repository.closeBookingCur(
					offenderExternalMovements.getMovementType(), offenderExternalMovements.getMovementReasonCode());
			if (Optional.ofNullable(closeBookingCur).isPresent()
					&& "TRN".equals(offenderExternalMovements.getMovementType())
					&& "Y".equals(closeBookingCur.getCloseContactFlag())) {
				// below method is used to update data in OFFENDER_BILLING_PROFILES table
				result = offenderExternalMovementT13Repository.update(target);
			}
		} catch (final Exception e) {
			result = 0;
			logger.error("OffenderExternalMovementT1", e);
		}
		return result;
	}

}

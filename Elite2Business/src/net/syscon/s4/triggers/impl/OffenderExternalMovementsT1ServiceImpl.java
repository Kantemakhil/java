package net.syscon.s4.triggers.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.im.beans.MovementReasons;
import net.syscon.s4.triggers.OffenderExternalMovementsT1Repository;
import net.syscon.s4.triggers.OffenderExternalMovementsT1Service;

@Service
public class OffenderExternalMovementsT1ServiceImpl implements OffenderExternalMovementsT1Service {
	@Autowired
	OffenderExternalMovementsT1Repository offenderExternalMovementsT1Repository;

	//Below method call after insert or update of movement_reason_code,movement_type on OFFENDER_EXTERNAL_MOVEMENTS table
	@Transactional
	@Override
	public Integer OffenderExternalMovementsT1Trigger(final MovementReasons oldRef, final MovementReasons newRef,
			final Long offenderBookId, final String operationType) {
		Integer result = 0;
		if ("INSERTING".equals(operationType)) {
			if(newRef.getMovementType() != null && newRef.getMovementReasonCode() != null) {
			//below method call is used to update data in offender_bookings table
			result = offenderExternalMovementsT1Repository.save(oldRef, newRef, offenderBookId);
			}
		} else if ("UPDATING".equals(operationType)) {
			//below method call is used to update data in offender_bookings table
			result = offenderExternalMovementsT1Repository.update(oldRef, newRef, offenderBookId);
		}
		return result;
	}

}

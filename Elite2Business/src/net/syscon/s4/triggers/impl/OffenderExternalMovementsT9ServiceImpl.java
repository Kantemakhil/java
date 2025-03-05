package net.syscon.s4.triggers.impl;

import java.util.Date;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.common.beans.OffenderExternalMovements;
import net.syscon.s4.triggers.OffenderExternalMovementsT9Repository;
import net.syscon.s4.triggers.OffenderExternalMovementsT9Service;
/*
============================================================
  Below comments are copied from OFFENDER_EXTERNAL_MOVEMENTS_T9 Trigger
============================================================
  MODIFICATION HISTORY
   Person     Date         version   Comments
-----------  --------- -----------  -------------------------------
   Sarah      05-Apr-2012   3.0      D#14467: Removed the code to repopulate movement date with movement time. Move date should have only the date and not time.
   Niko       28-Feb-2011   2.0      Modify trigger by adding
                                     BEFORE INSERT or UPDATE ON OMS_OWNER.OFFENDER_EXTERNAL_MOVEMENTS
   Niko       18-JAN-2011   1.0      Bug fix on hpqc#3845
*/
@Service
public class OffenderExternalMovementsT9ServiceImpl implements OffenderExternalMovementsT9Service {
	@Autowired
	private OffenderExternalMovementsT9Repository offenderExternalMovementsT9Repository;
	@Override
	public Date offenderExternalMovementsT9(OffenderExternalMovements newOffenderExternalMovements) {
		OffenderExternalMovements oldOffenderExternalMovements = offenderExternalMovementsT9Repository.
				getOffenderExternalMovements(newOffenderExternalMovements.getOffenderBookId(),newOffenderExternalMovements.getMovementSeq());
		if(Objects.isNull(newOffenderExternalMovements.getSealFlag()) || 
				StringUtils.equals(oldOffenderExternalMovements.getSealFlag(), newOffenderExternalMovements.getSealFlag())
				&& Objects.nonNull(newOffenderExternalMovements.getMovementDate())
				&& Objects.nonNull(newOffenderExternalMovements.getMovementTime())) {
			return newOffenderExternalMovements.getMovementTime();
		}else {
			return null;
		}
	}

}

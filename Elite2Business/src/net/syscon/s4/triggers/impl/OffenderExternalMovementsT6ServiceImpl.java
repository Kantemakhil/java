package net.syscon.s4.triggers.impl;

import java.text.SimpleDateFormat;

import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.beans.OffenderExternalMovements;
import net.syscon.s4.triggers.OffenderExternalMovementsT6Repository;
import net.syscon.s4.triggers.OffenderExternalMovementsT6Service;
/* =========================================================
Below comments are copied from OFFENDER_EXTERNAL_MOVEMENTS_T6 Trigger
========================================================= */
/*  MODIFICATION HISTORY
Person      Date          Version       Comments
---------   ------       ------------  ------------------------------------------
Simanta      04/03/2001    1.1         TRACKING#16334 :FOR ONTARIO'S CARE AND PLACEMENT
                                       ENHANCEMENT.
*/
@Service
public class OffenderExternalMovementsT6ServiceImpl implements OffenderExternalMovementsT6Service {
	@Autowired
	private OffenderExternalMovementsT6Repository offenderExternalMovementsT6Repository;
	@Transactional
	@Override
	public void offenderExternalMovementsT6(OffenderExternalMovements newOffenderExternalMovements) {
		if(Objects.nonNull(newOffenderExternalMovements)) {
			//Getting old Records from offender_external_movements table
			OffenderExternalMovements oldOffenderExternalMovements = 
					offenderExternalMovementsT6Repository.
					getOffenderExternalMovements(newOffenderExternalMovements.getOffenderBookId(),newOffenderExternalMovements.getMovementSeq());
			if(Objects.isNull(oldOffenderExternalMovements)) {
				if((StringUtils.equals("TRN", newOffenderExternalMovements.getMovementType()) || StringUtils.equals("REL", newOffenderExternalMovements.getMovementType()))
						|| (StringUtils.equals("INTER", newOffenderExternalMovements.getMovementType()) &&
								StringUtils.equals("INTER", newOffenderExternalMovements.getMovementReasonCode()) && 
								StringUtils.equals("OUT", newOffenderExternalMovements.getDirectionCode()))) {
					String lvCommentText="<";
					lvCommentText=lvCommentText+new SimpleDateFormat("MMM DD YYYY HH:MI(AM)").format(newOffenderExternalMovements.getMovementTime());
					// -- Get Movement Type Description
					String lvMovementType = offenderExternalMovementsT6Repository.getReferenceCodesDesc("MOVE_TYPE", lvCommentText);
					//   -- Get Movement Reason Description
					String lvMovementReason = offenderExternalMovementsT6Repository.getReferenceCodesDesc("MOVE_RSN", lvCommentText);
					lvCommentText=lvCommentText+lvMovementType+" - "+lvMovementReason;
					//  -- Deactivate active care and placement Record if exists
					offenderExternalMovementsT6Repository.updateOffenderCipDetails(newOffenderExternalMovements.getMovementTime(), lvCommentText,newOffenderExternalMovements.getModifyUserId());
				}
			}
		}
	}

}

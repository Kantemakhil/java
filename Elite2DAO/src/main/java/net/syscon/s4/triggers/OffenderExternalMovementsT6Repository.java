package net.syscon.s4.triggers;

import java.util.Date;

import net.syscon.s4.common.beans.OffenderExternalMovements;

public interface OffenderExternalMovementsT6Repository {

	OffenderExternalMovements getOffenderExternalMovements(Long pOffenderBookId,Long movementSeq);

	String getReferenceCodesDesc(String cpRefDomain, String cpRefCode);

	Integer updateOffenderCipDetails(Date movementTime, String lvCommentText,String modifyUserId);

}

package net.syscon.s4.pkgs.omkcopy;

import java.util.Map;

public interface OmkcopyService {

	Integer copyBookingData(final String moveType, String pMoveReason, final Long pOldBookId, final Long pNewBookId,
			final String userName);    

	void copyWorkFlows(final Long pOldBookId, final Long pNewBookId, final String userName);

	Map<String, Object> copyBookData(final String pMoveType, final String pMoveReason, final Long pOldBookId,
			final Long pNewBookId, final String pReturnText, final String vParent);

	void dynamicCopyTable(final String pTableName, final String pColName, final String pSeqName,
			final String pParentTable, final String vMoveType, final String vMoveReason, final Long vOldBookId,
			final Long vNewBookId);

}

package net.syscon.s4.pkgs.tag_termination;

import net.syscon.s4.common.beans.OffenderExternalMovements;
import net.syscon.s4.inst.legalscreens.bean.UpdateCase;

public interface TagTerminationService {

	String chkTasks(final Long offenderBookId);

	String chkActiveLicences(final UpdateCase updateCase);

	String chkActiveSentences(final UpdateCase updateCase);

	Long getCaseId(final String pRowid);

	String chkActiveSentences(final Long pCaseId);

	String chkActiveConditions(final UpdateCase updateCase);

	Long getSentenceSeq(final String pRowid);

	String chkActiveConditions(final Long pOffenderBookId, final Long pSentenceSeq);

	String chkActiveCases(final OffenderExternalMovements searchdao);

}
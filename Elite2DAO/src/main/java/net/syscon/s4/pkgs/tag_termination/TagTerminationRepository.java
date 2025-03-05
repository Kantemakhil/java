package net.syscon.s4.pkgs.tag_termination;

import net.syscon.s4.common.beans.OffenderExternalMovements;
import net.syscon.s4.inst.legalscreens.bean.UpdateCase;

public interface TagTerminationRepository {

	public String chkActiveLicences(final UpdateCase updateCase);

	public Long getCaseId(final String pRowid);

	public String chkActiveSentences(final Long pCaseId);

	public Long getSentenceSeq(final String pRowid);

	public String chkActiveConditions(final Long pOffenderBookId, final Long pSentenceSeq);

	public String chkActiveCases(final OffenderExternalMovements searchDao);

}
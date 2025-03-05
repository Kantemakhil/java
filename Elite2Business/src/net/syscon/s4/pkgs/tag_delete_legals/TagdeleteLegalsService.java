package net.syscon.s4.pkgs.tag_delete_legals;

import java.math.BigDecimal;
import java.util.List;

import net.syscon.s4.inst.legals.beans.Condition;
import net.syscon.s4.inst.legals.beans.CourtEvent;
import net.syscon.s4.inst.legals.beans.OffensesOutcome;
import net.syscon.s4.inst.legals.beans.Sentences;

public interface TagdeleteLegalsService {

	Integer deleteOrders(final CourtEvent courtEvent);

	Integer deleteChildToSent(final BigDecimal offBookId, final Long sentseq,String modifyUserId);

	Integer deleteChildToCase(final BigDecimal offBookId, final Long caseId,String modifyUserId);

	Integer deleteCondition(final BigDecimal offBookId, final Long sentseq, final Long sentcondId,String modifyUserId);

	Integer deleteSentenceCharges(final BigDecimal offBookId, final Long OffSentenSeq, final BigDecimal offChargeId,String modifyUserId);

	Integer deleteSentences(final Long offenderBookId, final Long sentenceSeq,String modifyUserId);

	Boolean isCaselinkedByFunction(final Integer offenderChargeId);

	Boolean isSentenceAttachedAlternateCalling(final OffensesOutcome deletedOffenses);

	Integer deleteFineSentence(OffensesOutcome sentenceCharges);

	List<OffensesOutcome> sentenceChargesQuery(final Long offendeerBookId, final Long sentenceseq);

	List<Condition> sentencesCondiQuery(final Sentences sentence);
}

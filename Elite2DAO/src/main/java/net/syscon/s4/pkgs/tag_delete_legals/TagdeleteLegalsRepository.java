package net.syscon.s4.pkgs.tag_delete_legals;

import java.math.BigDecimal;
import java.util.List;

import net.syscon.s4.common.beans.Orders;
import net.syscon.s4.inst.legals.beans.OffensesOutcome;
import net.syscon.s4.inst.legals.beans.Sentences;

public interface TagdeleteLegalsRepository {

	List<Orders> orderCur(final BigDecimal offenderBookId, final Long caseId, final Long eventid);

	Integer deleteOrder(final BigDecimal offenderBookId, final Long caseId, final Long eventid,String modifyUserId);

	Integer deleteOrderCondActivities(final BigDecimal orderId,String modifyUserId);

	Integer deleteOrderPurposes(final BigDecimal orderid,String modifyUserId);

	Integer deleteOrderProposalCond(final BigDecimal orderid,String modifyUserId);

	Integer deleteorderProposals(final BigDecimal orderid,String modifyUserId);

	Integer deleteOffSentterms(final BigDecimal offBookId, final Long sentseq,String modifyUserId);

	Integer deleteOffSentAdjust(final BigDecimal offBookId, final Long sentseq,String modifyUserId);

	Integer deleteOffSentStatus(final BigDecimal offBookId, final Long sentseq,String modifyUserId);

	Integer deleteOffSentHty(final BigDecimal offBookId, final Long sentseq,String modifyUserId);

	Integer deleteOffLiceSentence(final BigDecimal offBookId, final Long sentseq,String modifyUserId);

	Integer deleteCaseAssociatedPersons(final BigDecimal offBookId, final Long caseId,String modifyUserId);

	Integer deleteOffcaseAssociatin(final BigDecimal offBookId, final Long caseId,String modifyUserId);

	Integer deleteOffenderCaseIdentifier(final Long caseId,String modifyUserId);

	Integer deleteOffenderCaseStatus(final Long caseId,String modifyUserId);

	Integer deleteOffendersentCondStatus(final Long sentCondId,String modifyUserId);

	Integer deleteOffenderSentConditions(final BigDecimal offBookId, final Long sentseq, final Long sentCondId,String modifyUserId);

	Integer deleteSentCharges(final BigDecimal offBookId, final Long OffSentenSeq, final BigDecimal offChargeId,String modifyUserId);

	void deleteOffenderSentences(final Long offenderBookId, final Long sentenceSeq,String modifyUserId);

	Integer getCount(final Integer charge);

	String existCur(final Long offBookId, final Long offChargeId);

	Integer existFineSent(final Long offBookId);

	void offenderFinePayments(final Long offBookId,String modifyUserId);

	List<OffensesOutcome> pRefCurs(final Long OffenderBookId, final Long SetenceSeq);

	List<Object[]> tagReferenceCodesGetdesccode(final Sentences sentence);

}

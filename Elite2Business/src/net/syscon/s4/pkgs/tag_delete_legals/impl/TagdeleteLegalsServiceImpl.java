package net.syscon.s4.pkgs.tag_delete_legals.impl;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.beans.Orders;
import net.syscon.s4.inst.legals.beans.Condition;
import net.syscon.s4.inst.legals.beans.CourtEvent;
import net.syscon.s4.inst.legals.beans.OffensesOutcome;
import net.syscon.s4.inst.legals.beans.SentenceCalculation;
import net.syscon.s4.inst.legals.beans.Sentences;
import net.syscon.s4.pkgs.tag_delete_legals.TagdeleteLegalsRepository;
import net.syscon.s4.pkgs.tag_delete_legals.TagdeleteLegalsService;
import net.syscon.s4.pkgs.tag_sentence_calc.TagSentenceCalcService;

@Service
public class TagdeleteLegalsServiceImpl implements TagdeleteLegalsService {
	@Autowired
	private TagdeleteLegalsRepository TagdeleteLegalsRepository;
	@Autowired
	private TagSentenceCalcService tagSentenceCalcService;
	private final static Logger logger = LogManager.getLogger(TagdeleteLegalsServiceImpl.class.getName());

	@Override
	public Integer deleteOrders(final CourtEvent courtEvent) {
		try {
			final List<Orders> list = TagdeleteLegalsRepository.orderCur(
					BigDecimal.valueOf(courtEvent.getOffenderBookId()), courtEvent.getCaseId(),
					courtEvent.getEventId());
			list.forEach(bo -> {
				TagdeleteLegalsRepository.deleteOrderCondActivities(bo.getOrderId(),courtEvent.getModifyUserId());
				TagdeleteLegalsRepository.deleteOrderProposalCond(bo.getOrderId(),courtEvent.getModifyUserId());
				TagdeleteLegalsRepository.deleteOrderPurposes(bo.getOrderId(),courtEvent.getModifyUserId());
				TagdeleteLegalsRepository.deleteorderProposals(bo.getOrderId(),courtEvent.getModifyUserId());
			});
			TagdeleteLegalsRepository.deleteOrder(BigDecimal.valueOf(courtEvent.getOffenderBookId()),
					courtEvent.getCaseId(), courtEvent.getEventId(),courtEvent.getModifyUserId());
		} catch (Exception e) {
			logger.error("deleteOrders :" + e);
			return 0;
		}
		return 1;
	}

	@Override
	public Integer deleteChildToSent(final BigDecimal offBookId, final Long sentseq,String modifyUserId) {
		try {
			TagdeleteLegalsRepository.deleteOffSentterms(offBookId, sentseq,modifyUserId);
			TagdeleteLegalsRepository.deleteOffSentAdjust(offBookId, sentseq,modifyUserId);
			TagdeleteLegalsRepository.deleteOffSentStatus(offBookId, sentseq,modifyUserId);
			TagdeleteLegalsRepository.deleteOffSentHty(offBookId, sentseq,modifyUserId);
		} catch (Exception e) {
			logger.error("deleteChildToSent :" + e);
			return 0;
		}
		return 1;
	}

	@Override
	public Integer deleteChildToCase(final BigDecimal offBookId, final Long caseId,String modifyUserId) {
		try {
			TagdeleteLegalsRepository.deleteCaseAssociatedPersons(offBookId, caseId,modifyUserId);
			TagdeleteLegalsRepository.deleteOffcaseAssociatin(offBookId, caseId,modifyUserId);
			TagdeleteLegalsRepository.deleteOffenderCaseIdentifier(caseId,modifyUserId);
			TagdeleteLegalsRepository.deleteOffenderCaseStatus(caseId,modifyUserId);
		} catch (Exception e) {
			logger.error("deleteChildToCase :" + e);
			return 0;
		}
		return 1;
	}

	@Override
	public Integer deleteCondition(final BigDecimal offBookId, final Long sentseq, final Long sentCondId,String modifyUserId) {
		try {
			TagdeleteLegalsRepository.deleteOffendersentCondStatus(sentCondId,modifyUserId);
			TagdeleteLegalsRepository.deleteOffenderSentConditions(offBookId, sentseq, sentCondId,modifyUserId);
		} catch (Exception e) {
			logger.error("deleteCondition :" + e);
			return 0;
		}
		return 1;
	}

	@Override
	public Integer deleteSentenceCharges(final BigDecimal offBookId, final Long OffSentenSeq,
			final BigDecimal offChargeId,String modifyUserId) {
		return TagdeleteLegalsRepository.deleteSentCharges(offBookId, OffSentenSeq, offChargeId,modifyUserId);
	}

	@Override
	@Transactional
	public Integer deleteSentences(final Long offenderBookId, final Long sentenceSeq,String modifyUserId) {
		final SentenceCalculation sentenceCalc = new SentenceCalculation();
		try {
			// offender_sentences delete Operation
			TagdeleteLegalsRepository.deleteOffenderSentences(offenderBookId, sentenceSeq,modifyUserId);
			sentenceCalc.setOffenderBookId(offenderBookId);
			sentenceCalc.setCalculationReason("DELETE");
			sentenceCalc.setComment("Sentence Deleted");

			// tag_sentence_calc.calculate_sentences 300
			tagSentenceCalcService.calculateSentence(sentenceCalc);
		} catch (Exception e) {
			logger.error("deleteCondition :" + e);
			return 0;
		}
		return 1;
	}

	@Override
	public Boolean isCaselinkedByFunction(final Integer offenderChargeId) {
		final Integer vCount = TagdeleteLegalsRepository.getCount(offenderChargeId);
		if (vCount > 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public Boolean isSentenceAttachedAlternateCalling(final OffensesOutcome off) {
		final String vExist = TagdeleteLegalsRepository.existCur(off.getOffenderBookId(), off.getOffenderChargeId());
		if (vExist == null) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public Integer deleteFineSentence(final OffensesOutcome off) {
		final Integer count = TagdeleteLegalsRepository.existFineSent(off.getOffenderBookId());
		if (count > 1) {
			TagdeleteLegalsRepository.offenderFinePayments(off.getOffenderBookId(),off.getCreateUserId());
		}
		return count;
	}

	@Override
	public List<OffensesOutcome> sentenceChargesQuery(final Long offendeerBookId, final Long sentenceseq) {
		return TagdeleteLegalsRepository.pRefCurs(offendeerBookId, sentenceseq);
	}

	@Override
	public List<Condition> sentencesCondiQuery(final Sentences sentence) {
		TagdeleteLegalsRepository.tagReferenceCodesGetdesccode(sentence);
		return Arrays.asList();
	}
}

package net.syscon.s4.pkgs.tag_legal_cases;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import net.syscon.s4.common.beans.Orders;
import net.syscon.s4.im.beans.OffenderCases;
import net.syscon.s4.inst.legals.beans.CourtEvent;
import net.syscon.s4.inst.legals.beans.OcdapealBean;
import net.syscon.s4.inst.legals.beans.OffenderCaseIdentifiers;
import net.syscon.s4.inst.legals.beans.OffenderSentConditions;
import net.syscon.s4.inst.legals.beans.OffensesOutcome;
import net.syscon.s4.inst.legals.beans.Sentences;
import net.syscon.s4.inst.legalscreens.maintenance.bean.OffenderCharges;
import net.syscon.s4.inst.schedules.bean.CourtEvents;
import net.syscon.s4.pkgs.GOffenceRecBean;

public interface TagLegalCasesService {

	String workFlowInsert(final Integer eventId, final String type, final String from, final String userName);

	Integer eventChargesInsert(final List<CourtEvent> insertList);

	// String outcomeReasonCode,
	// String resultCodeIndicator, String chargeStatus);

	Integer eventOrderInsert(final OffensesOutcome offenceOutcome, final String userName);

	Boolean isOrderExist(final BigDecimal offenderBookId, final Long eventId);

	void ordersInsert(final BigDecimal offenderBookId, final Long eventId, Long caseId, final String userName);

	String getConditionDesc(final OffenderSentConditions bean);

	CourtEvent nextEventsInsert(List<CourtEvent> list);

	Date getLatestMovement(final Long offenderBookId);

	Date getBookingStartDate(final Long offenderBookId);

	void eventChargesInsert(final OffenderCharges offenderCharges);

	void offencesInsert(final GOffenceRecBean obj);

	List<OffensesOutcome> sentenceChargesQuery(final Sentences sentence);

	Integer updateCasesAgyLocId(final List<CourtEvent> list, final String userName);

	Integer updateOrderCourtDate(final CourtEvent courtEvent, final String userName);

	List<OffenderCharges> offencesQuery(final Long pOffenderBookId, final Long pCaseId, final Integer pEventId);

	List<OffenderCases> getLinkcaseDetails(final BigDecimal pCaseSeq, final Long pOffenderBookId,
			final Integer pCombinedCaseId);

	List<CourtEvent> linkCases(final CourtEvent courtEvent, final String userName);

	String getTerminationStatus(final String pReasonCode);

	OffensesOutcome eventChargesUpdate(List<CourtEvent> updateList);

	OffenderCharges selectStatus(long offenderCgargeId);

	Integer insertOrders(final CourtEvents events);

	Long populateLavyAmount(final Long pEventId);

	Boolean chkSentences(Integer caseId, Integer offenderBookId);

	List<OffensesOutcome> chargeSentencesQuery(final OffensesOutcome selectedSentenceData);

	Integer sentenceChargesInsert(final OffensesOutcome offensesOnsentencingList);

	List<CourtEvent> unlinkCases(final CourtEvent courtEvent);

	Integer sentenceHistory(final Long pOffenderBookId, final Long pSentenceSeq, final String userName);

	String insertAppealDetails(final OcdapealBean appealData);

	String getRcDescription(String addDaysRsn, String pAddRsn);

	void copyBailDetails(Integer pOffenderBookId, Integer pEventId, Integer pCaseId, String pEventType, String pMode,String modifyUserId);

	Integer caseIdentifiersInsert(OffenderCaseIdentifiers offCaseIden);
	
	Integer ordersDelete(Long offBkgId, Long eventId,String modifyUserId);

}
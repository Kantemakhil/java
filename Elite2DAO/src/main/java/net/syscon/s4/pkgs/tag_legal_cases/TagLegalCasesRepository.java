package net.syscon.s4.pkgs.tag_legal_cases;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import net.syscon.s4.common.beans.Orders;
import net.syscon.s4.im.beans.OffenderCases;
import net.syscon.s4.inst.casemanagement.beans.WorkFlowLogs;
import net.syscon.s4.inst.casemanagement.beans.WorkFlows;
import net.syscon.s4.inst.legals.au.OffenderBailConditions;
import net.syscon.s4.inst.legals.beans.CourtEvent;
import net.syscon.s4.inst.legals.beans.OcdapealBean;
import net.syscon.s4.inst.legals.beans.OffenderBailDetails;
import net.syscon.s4.inst.legals.beans.OffenderCaseIdentifiers;
import net.syscon.s4.inst.legals.beans.OffensesOutcome;
import net.syscon.s4.inst.legalscreens.maintenance.bean.OffenderCharges;
import net.syscon.s4.inst.schedules.bean.CourtEvents;
import net.syscon.s4.pkgs.GOffenceRecBean;

public interface TagLegalCasesRepository {

	Long lWorkflowCur(final String from, final Integer eventId);

	Integer insertWorkFlows(final WorkFlows workFlows);

	Long getWorkFlowId();

	Integer getStaffId();

	Integer insertWorkFlowLogs(final WorkFlowLogs workFlows);

	Long getLWorkseq(final Long lWorkflowId);

	WorkFlowLogs getWorkFlowLogs(final Long lWorkflowId);

	Integer updateCourtEvents(final Integer eventId, final String userName);

	Integer eventChargesInsert(final CourtEvent bean);

	Boolean isOrderExist(final BigDecimal offenderBookId, Long eventId);

	CourtEvents getAgyLocIdEventDate(final Long eventId);

	List<Orders> ordersInsertCasePlans(final Orders returnList);

	String getConditionDesc(final String commConditionCode, final String commConditionType, final Long offenderBookId);

	Date getBookingStartDate(final Long offenderBookId);

	Date getLatestMovement(final Long offenderBookId);

	Integer getEventId();

	Integer insertCourtEvents(final CourtEvents courtEvents);

	Integer insertCourtEventCharges(final OffenderCharges offenderCharges);

	Integer insertOffenderCharges(final GOffenceRecBean obj);

	Long getOffChargeId();

	public Integer insertCourtEventCharges(final Integer eventId, final Long vOffenderChargeId,
			final Long offenderChargeId, final String pleaCode, final String resultcode1, final String resultCode2,
			final String resultCode1Indicator, final String resultCode2Indicator, final Long propertyValue,
			final String mostSeriousFlag, final Long noOfOffence, final Long totalPropertyValue, final Date offenceDate,
			final Date offenceRangeDate, final String cjitOffenceCode1, final String cjitOffenceCode2,
			final String cjitOffenceCode3, final String userName);

	List<OffensesOutcome> pRefCurs(final Long OffenderBookId, final Long SetenceSeq);

	public Integer updateCasesAgyLocId(final String agyLocId, final Long locId, final String userName);

	Integer updateOrderCourtDate(final Long offenderBookId, final Integer eventId, final Date eventDate,
			final String userName);

	public List<OffenderCharges> getOffencesQuery(final Integer pEventId, final String pOffenceCode,
			final String pStatuteCode, final String pPleaCode, final Date pOffenceDate, final Date pOffenceRangeDate,
			final String pResultCode1, final String pResultCode2, final String pOffenceType);

	public String getOffeCodeDesc(final String pOffenceCode, final String pStatuteCode);

	Integer lSentEventCur();

	Integer offenderSentencesHty(final Long pOffenderBookId, final Long pSentenceSeq);

	public String getResultCodeDesc(final String pResultCode);

	public List<OffenderCases> getLinkcaseDetails(final BigDecimal pCaseSeq, final Long pOffenderBookId,
			final Integer pCombinedCaseId);

	public List<OffenderCharges> getOffCharges(final Long pCaseId, final Long pOffBookId);

	public Long getLinkDetails(final Long pCaseId);

	public Integer updateOffeCases(final Long pCaseId1, final String pCaseStatus, final Integer pStaffId,
			final Long pCaseId, final String userName);

	public String getTerminationStatus(final String pReasonCode);

	public Integer insertLinkCaseTxns(final Long pCaseId, final Long pCaseId1, final Long pOffenderChargeId,
			final Integer pEventId, final String userName);

	public Integer insertCourtEventCharges(final OffenderCharges bean, final Integer pEventId);

	public Integer insertLinkCaseTxnsSecond(final Long pCaseId, final Long pCaseId1, final Long pOffenderChargeId,
			final String userName);

	public Integer updateOffenderCharges(final Long pCaseId1, final Long pCaseId, final Long pOffBookId,
			final String userName);

	public List<OffenderCharges> getChargeDetails(final Long pCaseId, final Long lChargeId, final Long pOffBookId);

	public List<OffenderCharges> eventChgUpCur(final Long eventId);

	public Integer updateCourteventcharges(final String outcomeReasonCode, final String resultCodeIndicator,
			final Long offenderChardid, final String userName);

	public OffenderCharges selectStatus(final Long offenderCgargeId);

	public Integer updateOffenderCharges(final OffenderCharges offchrge, final Long offenderChargeId);

	public Integer offenderChargesUpdateOne(final String outcomeReasonCode, final String resultCodeIndicator,
			final String chargeStatus, final Long offenderChargeId, final String userName);

	public String isOrderExist(final Long offenderBookId, final Long eventId);

	public CourtEvents eventCur(final Long eventId);

	public Integer insertOrders(final CourtEvents events);

	String lAgyLoc(final Long lEventId);

	String lCourtType(final String pAgyLocId);

	Integer getSentences(final Integer offenderBookId, final Integer caseId);

	List<OffensesOutcome> chargeSentencesQuery(final Long caseId, final Long offenderBookId);

	List<OffensesOutcome> chargeSentencesQueryComm(final Long caseId, final Long offenderBookId,
			final Long pSentenceSeq);

	List<OffensesOutcome> chargeSentencesQueryCommOne(final Long caseId, final Long offenderBookId,
			final Long pSentenceSeq);

	Integer offenderSentenceCharges(final OffensesOutcome offensesOnsentencingList);

	List<Object[]> unlinkCur(final CourtEvent courtEvent);

	Integer offenderCharges(final Long offenderChargeId, final Long cseId, final String userName);

	Integer courtEventCharges(final Long offenderChargeId, final Long eventId);

	Integer linkCaseTxns(final Long caseload, final Long caseload1);

	Integer offenderCasesUpdateFirst(final Long caseload, final Long offenderBookId, final String userName);

	List<Long> chgCur(final Long pOffenderBookId, final Long pSentenceSeq);

	Integer offenderChargesHty(final Long vChgId);

	Integer offenderSentenceTermsHty(final Long pOffenderBookId, final Long pSentenceSeq);

	Integer offenderSentConditionsHty(final Long pOffenderBookId, final Long pSentenceSeq);

	Integer getLAppExists(final Long pAppealId, final Long pOffChg, final Long pOffBkgId);

	List<Long> getLSentCur(final Long pOffChg, final Long pOffBkgId);

	Integer insertIntoAppealOffenderCharges(final OcdapealBean appealData);

	Integer updateOfCourtEventCharges(final OcdapealBean appealData);

	Integer updateOfOffenderCharges(final Long pOffChg, final String modifyUserId);

	Integer insertOfAppealOffenderSentences(final OcdapealBean appealData, final Long lSentSeq);

	Integer deleteOfAppealOffenderCharges(final OcdapealBean appealData);

	Integer updateOfCourtEventChargesOne(final OcdapealBean appealData);

	Integer updateOfOffenderChargesOne(final Long pOffChg, final String modifyUserId);

	Integer deleteOfAppealOffenderSentencesOne(final OcdapealBean appealData, final Long lSentSeq);

	String getRcDescription(String domain, String code);

	Integer lEventCur(Integer pEventId, Integer pCaseId, String pEventType);

	List<OffenderBailDetails> lBailCur(Integer lEventRec);

	void offenderBailDetailsInsert(OffenderBailDetails offBail);

	List<OffenderBailConditions> lBailCondCur(Integer lEventRec);

	void offenderBailConditionsInsert(OffenderBailConditions offBailCon);

	void offenderBailDetailsUpdate(Integer pEventId);

	void offenderBailConditionsDelete(Integer pEventId,String modifyUserId);

	Integer caseIdentifiersInsert(OffenderCaseIdentifiers offCaseIden);

	Integer ordersDelete(Long offBkgId, Long eventId,String modifyUserId);
}
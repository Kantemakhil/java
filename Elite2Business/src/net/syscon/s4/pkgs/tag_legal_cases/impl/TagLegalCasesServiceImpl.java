package net.syscon.s4.pkgs.tag_legal_cases.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.commons.collections4.map.HashedMap;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.beans.Orders;
import net.syscon.s4.im.beans.OffenderCaseNotes;
import net.syscon.s4.im.beans.OffenderCases;
import net.syscon.s4.inst.casemanagement.beans.WorkFlowLogs;
import net.syscon.s4.inst.casemanagement.beans.WorkFlows;
import net.syscon.s4.inst.legals.au.OffenderBailConditions;
import net.syscon.s4.inst.legals.beans.CourtEvent;
import net.syscon.s4.inst.legals.beans.OcdapealBean;
import net.syscon.s4.inst.legals.beans.OffenderBailDetails;
import net.syscon.s4.inst.legals.beans.OffenderCaseIdentifiers;
import net.syscon.s4.inst.legals.beans.OffenderSentConditions;
import net.syscon.s4.inst.legals.beans.OffensesOutcome;
import net.syscon.s4.inst.legals.beans.Sentences;
import net.syscon.s4.inst.legalscreens.maintenance.bean.OffenderCharges;
import net.syscon.s4.inst.schedules.bean.CourtEvents;
import net.syscon.s4.pkgs.GOffenceRecBean;
import net.syscon.s4.pkgs.oms_miscellaneous.OmsMiscellaneousService;
import net.syscon.s4.pkgs.oms_utils.OmsUtilsService;
import net.syscon.s4.pkgs.tag_legal_cases.TagLegalCasesRepository;
import net.syscon.s4.pkgs.tag_legal_cases.TagLegalCasesService;

@Service
public class TagLegalCasesServiceImpl implements TagLegalCasesService {

	@Autowired
	private TagLegalCasesRepository tagLegalCasesRepository;
	@Autowired
	private OmsUtilsService omsUtilsService;
	@Autowired
	private OmsMiscellaneousService omsMiscellaneousService;

	private static final String COMP = "COMP";
	private static final String SCH = "SCH";
	private static final String Y = "Y";
	private static final String INSERT = "INSERT";
	private static final String SENT_HTY = "SENT_HTY";
	private static final String ONE = "ONE";
	private static final String TWO = "TWO";
	private static final String N = "N";
	private static final String INS = "INS";
	private static final String UPD = "UPD";
	private static Logger logger = LogManager.getLogger(TagLegalCasesServiceImpl.class);

	@Override
	@Transactional
	public String workFlowInsert(final Integer eventId, final String type, final String from, final String userName) {
		Long lWorkflowId;
		Long lWorkseq;
		Integer lStaffId;
		String lModAction = null;
		String lCridAction = null;
		String code = null;

		try {
			lWorkflowId = tagLegalCasesRepository.lWorkflowCur(from, eventId);
			if (lWorkflowId == null && type.equals("INSERT")) {
				lWorkflowId = tagLegalCasesRepository.getWorkFlowId();
				final WorkFlows workFlows = new WorkFlows();
				workFlows.setWorkFlowId(lWorkflowId);
				workFlows.setObjectCode(type);
				workFlows.setObjectId(new BigDecimal(eventId));
				workFlows.setCreateUserId(userName);
				tagLegalCasesRepository.insertWorkFlows(workFlows);

				lWorkseq = tagLegalCasesRepository.getLWorkseq(lWorkflowId);
				lStaffId = tagLegalCasesRepository.getStaffId();

				final WorkFlowLogs workFlowLogs = new WorkFlowLogs();
				workFlowLogs.setWorkFlowId(lWorkflowId);
				workFlowLogs.setWorkFlowSeq(lWorkseq);
				workFlowLogs.setActionUserId(new BigDecimal(lStaffId));
				workFlowLogs.setWorkActionCode("ENT");
				workFlowLogs.setCreateUserId(userName);
				tagLegalCasesRepository.insertWorkFlowLogs(workFlowLogs);

			} else if (type.equals("UPDATE") && lWorkflowId != null) {
				final WorkFlowLogs workFlowLogs = tagLegalCasesRepository.getWorkFlowLogs(lWorkflowId);
				lModAction = workFlowLogs.getWorkActionCode();
				lCridAction = workFlowLogs.getCreateUserId();

				if (lModAction.equals("VER") || (lModAction.equals("ENT") && !lCridAction.equals("USER"))) {
					lWorkseq = tagLegalCasesRepository.getLWorkseq(lWorkflowId);
					lStaffId = tagLegalCasesRepository.getStaffId();
					final WorkFlowLogs workFlowLogsNew = new WorkFlowLogs();
					workFlowLogsNew.setWorkFlowId(lWorkflowId);
					workFlowLogsNew.setWorkFlowSeq(lWorkseq);
					workFlowLogsNew.setActionUserId(new BigDecimal(lStaffId));
					workFlowLogsNew.setWorkActionCode("MOD");
					tagLegalCasesRepository.insertWorkFlowLogs(workFlowLogs);
					tagLegalCasesRepository.updateCourtEvents(eventId, userName);
				}
			}
			code = ONE;
		} catch (Exception e) {
			logger.error("Exception :", e);
			code = TWO;
		}
		return code;
	}

	@Override
	public Integer eventChargesInsert(final List<CourtEvent> insertList) {
		Integer liReturn = 1;
		try {
			for (CourtEvent courtEvent : insertList) {
				tagLegalCasesRepository.eventChargesInsert(courtEvent);
			}
		} catch (Exception e) {
			liReturn = 0;
			logger.error("insertEventCharges" + e.getMessage());
		}
		return liReturn;
	}

	@Override
	public Integer eventOrderInsert(final OffensesOutcome offenceOutcome, final String userName) {
		Integer liReturn = 1;
		try {
			if (offenceOutcome.getChargeStatus() == "A" && offenceOutcome.getResultcode1indicator() == "A"
					|| offenceOutcome.getResultcode1indicator() == "F") {
				final Boolean orderVal = isOrderExist(BigDecimal.valueOf(offenceOutcome.getOffenderBookId()),
						offenceOutcome.getEventId());
				if (!orderVal) {
					ordersInsert(BigDecimal.valueOf(offenceOutcome.getOffenderBookId()), offenceOutcome.getEventId(),
							offenceOutcome.getCaseId(), userName);
				}
			}
		} catch (final Exception e) {
			liReturn = 0;
			logger.error("eventOrderInsert" + e.getMessage());
		}
		return liReturn;

	}

	@Override
	public Boolean isOrderExist(final BigDecimal offenderBookId, final Long eventId) {
		return tagLegalCasesRepository.isOrderExist(offenderBookId, eventId);
	}

	@Override
	public void ordersInsert(final BigDecimal offenderBookId, final Long eventId, final Long caseId,
			final String userName) {
		final Orders list = new Orders();
		list.setCreateUserId(userName);
		final CourtEvents eventDetails = tagLegalCasesRepository.getAgyLocIdEventDate(eventId);
		if (eventDetails != null) {
			eventDetails.getAgyLocId();
			eventDetails.getEventDate();
			tagLegalCasesRepository.ordersInsertCasePlans(list);
		}
	}

	@Override
	public String getConditionDesc(final OffenderSentConditions bean) {
		return tagLegalCasesRepository.getConditionDesc(bean.getCommConditionCode(), bean.getCommConditionType(),
				bean.getOffenderBookId());
	}

	@Override
	@Transactional
	public CourtEvent nextEventsInsert(List<CourtEvent> list) {
		Integer lvEventd = null;
		String lvEventStatus = null;
		Date bookingStartDate = null;
		Date latestMovement = null;
		final CourtEvent bean = new CourtEvent();
		final CourtEvents courtEvents = new CourtEvents();
		final OffenderCharges offenderCharges = new OffenderCharges();

		for (final CourtEvent courtEvent : list) {
			try {
				bookingStartDate = getBookingStartDate(courtEvent.getOffenderBookId());
				latestMovement = getLatestMovement(courtEvent.getOffenderBookId());
				if ((courtEvent.getNextEventDate() != null && bookingStartDate != null
						&& courtEvent.getNextEventDate().compareTo(bookingStartDate) < 0)
						|| (courtEvent.getNextEventDate() != null && latestMovement != null
								&& courtEvent.getNextEventDate().compareTo(latestMovement) < 0)) {
					lvEventStatus = COMP;
				} else {
					lvEventStatus = SCH;
				}

				courtEvents.setOffenderBookId(courtEvent.getOffenderBookId().intValue());
				courtEvents.setCaseId(courtEvent.getCaseId().intValue());
				courtEvents.setEventId(tagLegalCasesRepository.getEventId());
				courtEvents.setEventDate(courtEvent.getNextEventDate());
				courtEvents.setStartTime(courtEvent.getNextEventStartTime());
				courtEvents.setCourtEventType(courtEvent.getHearingType());
				courtEvents.setAgyLocId(courtEvent.getAgyLocId());
				courtEvents.setEventStatus(lvEventStatus);
				courtEvents.setCreateUserId(courtEvent.getCreateUserId());
				tagLegalCasesRepository.insertCourtEvents(courtEvents);

				lvEventd = courtEvents.getEventId();

				offenderCharges.setEventId(new BigDecimal(lvEventd));
				offenderCharges.setOffenderBookId(courtEvent.getOffenderBookId());
				offenderCharges.setCaseId(courtEvent.getCaseId());
				eventChargesInsert(offenderCharges);
			} catch (Exception e) {
				logger.error("nextEventsInsert" + e.getMessage());
			}
		}

		return bean;
	}

	@Override
	public void eventChargesInsert(final OffenderCharges offenderCharges) {
		tagLegalCasesRepository.insertCourtEventCharges(offenderCharges);
	}

	@Override
	public Date getLatestMovement(final Long offenderBookId) {
		return tagLegalCasesRepository.getLatestMovement(offenderBookId);
	}

	@Override
	public Date getBookingStartDate(final Long offenderBookId) {
		return tagLegalCasesRepository.getBookingStartDate(offenderBookId);
	}

	@Override
	public void offencesInsert(final GOffenceRecBean obj) {
		Long vOffenderChargeId = null;
		vOffenderChargeId = tagLegalCasesRepository.getOffChargeId();
		GOffenceRecBean insert = new GOffenceRecBean();
		insert = obj;
		if (obj.getOffenderChargeId() == null) {
			insert.setOffenderChargeId(vOffenderChargeId);
			tagLegalCasesRepository.insertOffenderCharges(insert);
		}

		tagLegalCasesRepository.insertCourtEventCharges(obj.getEventId(), vOffenderChargeId, obj.getOffenderChargeId(),
				obj.getPleaCode(), obj.getResultcode1(), obj.getResultCode2(), obj.getResultCode1Indicator(),
				obj.getResultCode2Indicator(), obj.getPropertyValue(), obj.getMostSeriousFlag(), obj.getNoOfOffences(),
				obj.getTotalPropertyValue(), obj.getOffenceDate(), obj.getOffenceRangeDate(), obj.getCjitOffenceCode1(),
				obj.getCjitOffenceCode2(), obj.getCjitOffenceCode3(), obj.getCreateUserId());

		final Boolean orderExist = isOrderExist(BigDecimal.valueOf(obj.getOffenderBookId()),
				Long.valueOf(obj.getEventId()));
		if ("A".equals(obj.getChargeStatus()) && "P".equals(obj.getResultCode1Indicator())
				|| "F".equals(obj.getResultCode1Indicator())) {
			if (!orderExist) {
				ordersInsert(BigDecimal.valueOf(obj.getOffenderBookId()), obj.getCaseId(),
						Long.valueOf(obj.getEventId()), obj.getCreateUserId());
			} // end if
		} // end if
	}

	@Override
	public List<OffensesOutcome> sentenceChargesQuery(final Sentences sentence) {
		return tagLegalCasesRepository.pRefCurs(sentence.getOffenderBookId(), sentence.getLine());
	}

	@Override
	public Integer updateCasesAgyLocId(final List<CourtEvent> list, final String userName) {
		try {
			for (final CourtEvent courtEvent : list) {
				tagLegalCasesRepository.updateCasesAgyLocId(courtEvent.getAgyLocId(), courtEvent.getCaseId(), userName);
			}
		} catch (Exception e) {
			logger.error("UpdateCasesAgyLocId :", e);
		}
		return 1;
	}

	@Override
	public Integer updateOrderCourtDate(final CourtEvent courtEvent, final String userName) {
		return tagLegalCasesRepository.updateOrderCourtDate(courtEvent.getOffenderBookId(),
				courtEvent.getEventId().intValue(), courtEvent.getEventDate(), userName);
	}

	@Override
	public List<OffenderCharges> offencesQuery(final Long pOffenderBookId, final Long pCaseId, final Integer pEventId) {
		List<OffenderCharges> pRefCur = new ArrayList<OffenderCharges>();
		pRefCur = tagLegalCasesRepository.getOffencesQuery(pEventId, null, null, null, null, null, null, null, null);
		pRefCur.stream().forEach(data-> {
			final String offCodeDesc = offenseCodeDescription(data.getOffenceCode(), data.getStatuteCode());
			data.setOffenceDescription(offCodeDesc);
			final String resultCodedesc1 = getResultCodeDescription(data.getResultCode1());
			data.setResultCode1Desc(resultCodedesc1);
			final String resultCodedesc2 = getResultCodeDescription(data.getResultCode2());
			data.setResultCode2Desc(resultCodedesc2);
			data.setApplyFlag(Y);
		});
		
		return pRefCur;
	}

	private String offenseCodeDescription(final String pOffenceCode, final String pStatuteCode) {
		return tagLegalCasesRepository.getOffeCodeDesc(pOffenceCode, pStatuteCode);

	}

	private String getResultCodeDescription(final String pResultCode) {
		return tagLegalCasesRepository.getResultCodeDesc(pResultCode);
	}

	@Override
	public List<OffenderCases> getLinkcaseDetails(final BigDecimal pCaseSeq, final Long pOffenderBookId,
			final Integer pCombinedCaseId) {
		return tagLegalCasesRepository.getLinkcaseDetails(pCaseSeq, pOffenderBookId, pCombinedCaseId);
	}

	@Override
	public List<CourtEvent> linkCases(final CourtEvent courtEvent, final String userName) {
		final List<CourtEvent> link = null;
		Long lCaseId;
		final String pCaseStatus = getTerminationStatus("LINKED");
		final Integer staffIdLC = omsUtilsService.getStaffId(userName);
		tagLegalCasesRepository.updateOffeCases((long) courtEvent.getCaseIdl(), pCaseStatus, staffIdLC,
				courtEvent.getCaseId(), userName);
		final List<OffenderCharges> offChrg = tagLegalCasesRepository.getOffCharges(courtEvent.getCaseId(),
				courtEvent.getOffenderBookId());
		offChrg.forEach(bo -> {
			tagLegalCasesRepository.insertLinkCaseTxns(courtEvent.getCaseId(), (long) courtEvent.getCaseIdl(),
					bo.getOffenderChargeId(), courtEvent.getEventId().intValue(), userName);
			tagLegalCasesRepository.insertCourtEventCharges(bo, courtEvent.getEventId().intValue());
		});
		lCaseId = tagLegalCasesRepository.getLinkDetails(courtEvent.getCaseId());
		final List<OffenderCharges> offChrg1 = tagLegalCasesRepository.getOffCharges(lCaseId,
				courtEvent.getOffenderBookId());
		if (lCaseId != null) {
			offChrg1.forEach(bo -> {
				tagLegalCasesRepository.insertLinkCaseTxnsSecond(courtEvent.getCaseId(), (long) courtEvent.getCaseIdl(),
						bo.getOffenderChargeId(), userName);
				tagLegalCasesRepository.insertCourtEventCharges(bo, courtEvent.getEventId().intValue());
			});
		}
		tagLegalCasesRepository.updateOffenderCharges((long) courtEvent.getCaseIdl(), courtEvent.getCaseId(),
				courtEvent.getOffenderBookId(), userName);
		return link;
	}

	@Override
	public String getTerminationStatus(final String pReasonCode) {
		return tagLegalCasesRepository.getTerminationStatus(pReasonCode);
	}

	@Override
	public OffensesOutcome eventChargesUpdate(List<CourtEvent> updateList) {
		final OffensesOutcome bean = new OffensesOutcome();

		for (CourtEvent courtEvent : updateList) {
			try {
				final List<OffenderCharges> list = tagLegalCasesRepository.eventChgUpCur(courtEvent.getEventId());
				OffenderCharges offcharges = new OffenderCharges();
				Integer totalCount = null;
				if (list != null && list.size() > 0) {
					for (final OffenderCharges pffChargs : list) {
						// update court event charges
						totalCount = tagLegalCasesRepository.updateCourteventcharges(courtEvent.getOutcomeReasonCode(),
								courtEvent.getDisposition(), pffChargs.getOffenderChargeId(),
								courtEvent.getModifyUserId());
						if (pffChargs.getOffenderChargeId() > 1) {
							// select qury of ststus
							offcharges = tagLegalCasesRepository.selectStatus(pffChargs.getChargeSeq());
							// update offenderCharges
							offcharges.setModifyUserId(courtEvent.getModifyUserId());
							totalCount = tagLegalCasesRepository.updateOffenderCharges(offcharges,
									pffChargs.getOffenderChargeId());
						} else {
							// update offenderCharges
							totalCount = tagLegalCasesRepository.offenderChargesUpdateOne(
									courtEvent.getOutcomeReasonCode(), courtEvent.getDisposition(),
									courtEvent.getChargeStatus(), pffChargs.getOffenderChargeId(),
									courtEvent.getModifyUserId());
						}
					}
				}
				if (courtEvent.getChargeStatus() != null && "A".equals( courtEvent.getChargeStatus())
						&& "P".equals(courtEvent.getDisposition())  || "F".equals(courtEvent.getDisposition())) {
					Boolean exist = null;
					String result = tagLegalCasesRepository.isOrderExist(courtEvent.getOffenderBookId(),
							courtEvent.getEventId());
					if (result != null) {
						exist = true;
					} else {
						exist = false;
					}
					if (exist) {
						final CourtEvents events = tagLegalCasesRepository.eventCur(courtEvent.getEventId());
						final int offenderBookId = courtEvent.getOffenderBookId().intValue();
						events.setOffenderBookId(offenderBookId);
						events.setCaseId(courtEvent.getCaseId().intValue());
						events.setEventId(courtEvent.getEventId().intValue());
						events.setCreateUserId(courtEvent.getCreateUserId());
						tagLegalCasesRepository.insertOrders(events);

					}
				}
			} catch (Exception e) {
				logger.error("eventChargesUpdate" + e.getMessage());
			}
		}
		return bean;
	}

	@Override
	public OffenderCharges selectStatus(final long offenderCgargeId) {
		return tagLegalCasesRepository.selectStatus(offenderCgargeId);
	}

	@Override
	public Integer insertOrders(final CourtEvents events) {
		return tagLegalCasesRepository.insertOrders(events);
	}

	@Override
	public Long populateLavyAmount(final Long pEventId) {
		final Long lEventId = pEventId;
		String lAgyId = null;
		String lJurisCode = null;
		Long pLevyAmount = null;
		final Map<String, Object> returnMap = new HashedMap<String, Object>();
		Map<String, Object> map = null;

		try {
			lAgyId = tagLegalCasesRepository.lAgyLoc(lEventId);
			lJurisCode = tagLegalCasesRepository.lCourtType(lAgyId);
			if (lJurisCode.equals("DISTRICT") || lJurisCode.equals("SUPREME")) {
				map = omsMiscellaneousService.getProfileValues("CLIENT", "VCL_VALUE");
				pLevyAmount = (Long) map.get("P_PROFILE_VALUE_2");
			} else {
				map = omsMiscellaneousService.getProfileValues("CLIENT", "VCL_VALUE");
				pLevyAmount = (Long) map.get("P_PROFILE_VALUE");
			}
			returnMap.put("P_LEVY_AMOUNT", pLevyAmount);
		} catch (DataAccessException dae) {
			logger.error("populateLavyAmount :" + dae);
		}
		return pLevyAmount;
	}

	@Override
	public Boolean chkSentences(final Integer caseId, final Integer offenderBookId) {
		final int count = tagLegalCasesRepository.getSentences(offenderBookId, caseId);
		if (count > 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public List<OffensesOutcome> chargeSentencesQuery(final OffensesOutcome offOut) {
		List<OffensesOutcome> list = new ArrayList<OffensesOutcome>();
		if (offOut.getSentenceType() != null && offOut.getSentenceType().equals("INST")) {
			list = tagLegalCasesRepository.chargeSentencesQuery(offOut.getCaseId(), offOut.getOffenderBookId());
		} else if (offOut.getSentenceType() != null && offOut.getSentenceType().equals("COMM")) {
			list = tagLegalCasesRepository.chargeSentencesQueryComm(offOut.getCaseId(), offOut.getOffenderBookId(),
					offOut.getSentenceSeq());
		} else {
			list = tagLegalCasesRepository.chargeSentencesQueryCommOne(offOut.getCaseId(), offOut.getOffenderBookId(),
					offOut.getSentenceSeq());
		}
		return list;
	}

	@Override
	public Integer sentenceChargesInsert(final OffensesOutcome offensesOnsentencingList) {
		try {
			tagLegalCasesRepository.offenderSentenceCharges(offensesOnsentencingList);
		} catch (Exception e) {
			return 0;
		}
		return 1;
	}

	@Override
	public List<CourtEvent> unlinkCases(final CourtEvent courtEvent) {
		try {
			tagLegalCasesRepository.offenderCasesUpdateFirst(courtEvent.getCaseId(), courtEvent.getOffenderBookId(),
					courtEvent.getModifyUserId());
			final List<Object[]> list = tagLegalCasesRepository.unlinkCur(courtEvent);
			for (final Object[] obj : list) {
				tagLegalCasesRepository.offenderCharges((long) obj[0], courtEvent.getCaseId(),
						courtEvent.getModifyUserId());
				tagLegalCasesRepository.courtEventCharges((long) obj[0], (long) obj[1]);
			}
			tagLegalCasesRepository.linkCaseTxns(courtEvent.getCaseId(), (long) courtEvent.getCaseIdl());
		} catch (Exception e) {
			logger.error("unLinkCase", e.getMessage());
		}
		return new ArrayList<CourtEvent>();
	}

	@Override
	public Integer sentenceHistory(final Long pOffenderBookId, final Long pSentenceSeq, final String userName) {
		Long vChgId = null;
		Integer liReturn = 1;
		try {
			final Integer vSentEventId = tagLegalCasesRepository.lSentEventCur();
			tagLegalCasesRepository.offenderSentencesHty(pOffenderBookId, pSentenceSeq);
			if (vSentEventId != null) {
				this.workFlowInsert(vSentEventId, INSERT, SENT_HTY, userName);

				final List<Long> list = tagLegalCasesRepository.chgCur(pOffenderBookId, pSentenceSeq);
				for (final Long l : list) {
					vChgId = l;

					if (list == null) {
						return 0;
					}
					tagLegalCasesRepository.offenderChargesHty(vChgId);
				} // loop

				tagLegalCasesRepository.offenderSentenceTermsHty(pOffenderBookId, pSentenceSeq);
				tagLegalCasesRepository.offenderSentConditionsHty(pOffenderBookId, pSentenceSeq);
			}
		} catch (final Exception e) {
			liReturn = 0;
			logger.error("sentenceHistory", e.getMessage());
		}
		return liReturn;

	}

	@Override
	public String insertAppealDetails(final OcdapealBean appealData) {
		Integer lExists = 0;
		List<Long> lSentSeq = null;
		String code = null;
		try {
			lExists = tagLegalCasesRepository.getLAppExists(appealData.getAppealId(), appealData.getOffenderChargeId(),
					appealData.getOffenderBookId());
			if (Y.equals(appealData.getApplyFlag()) && lExists == 0) {
				tagLegalCasesRepository.insertIntoAppealOffenderCharges(appealData);
				tagLegalCasesRepository.updateOfCourtEventCharges(appealData);
				tagLegalCasesRepository.updateOfOffenderCharges(appealData.getOffenderChargeId(),
						appealData.getModifyUserId());
				lSentSeq = tagLegalCasesRepository.getLSentCur(appealData.getOffenderChargeId(),
						appealData.getOffenderBookId());
				for (final Long seq : lSentSeq) {
					if (seq == null) {
						break;
					} else {
						tagLegalCasesRepository.insertOfAppealOffenderSentences(appealData, seq);
					}
				}
			} else if (N.equals(appealData.getApplyFlag())) {
				tagLegalCasesRepository.deleteOfAppealOffenderCharges(appealData);
				tagLegalCasesRepository.updateOfCourtEventChargesOne(appealData);
				tagLegalCasesRepository.updateOfOffenderChargesOne(appealData.getOffenderChargeId(),
						appealData.getModifyUserId());

				lSentSeq = tagLegalCasesRepository.getLSentCur(appealData.getOffenderChargeId(),
						appealData.getOffenderBookId());
				for (final Long seq : lSentSeq) {
					if (seq == null) {
						break;
					} else {
						tagLegalCasesRepository.deleteOfAppealOffenderSentencesOne(appealData, seq);
					}
				}
			}
			code = ONE;
		} catch (Exception e) {
			logger.error("Exception :", e);
			code = TWO;
		}
		return code;
	}

	@Override
	public String getRcDescription(final String domain, final String code) {
		return tagLegalCasesRepository.getRcDescription(domain, code);
	}

	@Override
	public void copyBailDetails(final Integer pOffenderBookId, final Integer pEventId, final Integer pCaseId,
			final String pEventType, final String pMode,String modifyUserId) {
		Integer lEventRec = null;
		List<OffenderBailDetails> lvRecBail = null;
		List<OffenderBailConditions> lvConBail = null;
		try {
			if (INS.equals(pMode)) {
				lEventRec = tagLegalCasesRepository.lEventCur(pEventId, pCaseId, pEventType);
				if (Optional.ofNullable(lEventRec).isPresent()) {
					lvRecBail = tagLegalCasesRepository.lBailCur(lEventRec);
					for (final OffenderBailDetails offBail : lvRecBail) {
						offBail.setEventId(pEventId != null ? pEventId.longValue() : null);
						tagLegalCasesRepository.offenderBailDetailsInsert(offBail);

						lvConBail = tagLegalCasesRepository.lBailCondCur(lEventRec);
						for (final OffenderBailConditions offBailCon : lvConBail) {
							offBailCon.setEventId(pEventId != null ? pEventId.longValue() : null);
							tagLegalCasesRepository.offenderBailConditionsInsert(offBailCon);
						}
					}
				}
			} else if (UPD.equals(pMode)) {
				tagLegalCasesRepository.offenderBailDetailsUpdate(pEventId);
				tagLegalCasesRepository.offenderBailConditionsDelete(pEventId,modifyUserId);
			}
		} catch (Exception e) {
			logger.error("copyBailDetails :", e);
		}
	}

	@Override
	public Integer caseIdentifiersInsert(OffenderCaseIdentifiers offCaseIden) {
		try {
		return tagLegalCasesRepository.caseIdentifiersInsert(offCaseIden);
		}catch (Exception e) {
			logger.error("caseIdentifiersInsert :", e);
			return null;
		}

	}

	@Override
	public Integer ordersDelete(final Long offBkgId, final Long eventId,String modifyUserId) {
		return tagLegalCasesRepository.ordersDelete(offBkgId, eventId,modifyUserId);
	}

}
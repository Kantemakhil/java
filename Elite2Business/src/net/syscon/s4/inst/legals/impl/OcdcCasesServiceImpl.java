package net.syscon.s4.inst.legals.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.ApplicationConstants;
import net.syscon.s4.common.beans.LovList;
import net.syscon.s4.common.beans.OffenderCaseCommitBean;
import net.syscon.s4.common.beans.SentenceCommitBean;
import net.syscon.s4.common.beans.SentenceTermCommitBean;
import net.syscon.s4.globalconfiguration.OcmpconfRepository;
import net.syscon.s4.inst.legals.OcdcCasesRepository;
import net.syscon.s4.inst.legals.OcdcCasesService;
import net.syscon.s4.inst.legals.OcucalcrRepository;
import net.syscon.s4.inst.legals.beans.BailDetailsCommitBean;
import net.syscon.s4.inst.legals.beans.BailStatus;
import net.syscon.s4.inst.legals.beans.BondType;
import net.syscon.s4.inst.legals.beans.CaseStatus;
import net.syscon.s4.inst.legals.beans.Category;
import net.syscon.s4.inst.legals.beans.Court;
import net.syscon.s4.inst.legals.beans.CourtCases;
import net.syscon.s4.inst.legals.beans.CourtEvent;
import net.syscon.s4.inst.legals.beans.HearingType;
import net.syscon.s4.inst.legals.beans.OffenceOutcomeCommitBean;
import net.syscon.s4.inst.legals.beans.OffenderBailDetails;
import net.syscon.s4.inst.legals.beans.OffenderCourtEventCommitBean;
import net.syscon.s4.inst.legals.beans.OffenderDetailsOffenses;
import net.syscon.s4.inst.legals.beans.OffenseResultCodes;
import net.syscon.s4.inst.legals.beans.OffenseType;
import net.syscon.s4.inst.legals.beans.OffensesOutcome;
import net.syscon.s4.inst.legals.beans.OffensesOutcomeCommitBean;
import net.syscon.s4.inst.legals.beans.Outcome;
import net.syscon.s4.inst.legals.beans.Plea;
import net.syscon.s4.inst.legals.beans.SentenceCalculation;
import net.syscon.s4.inst.legals.beans.SentenceDate;
import net.syscon.s4.inst.legals.beans.Sentences;
import net.syscon.s4.inst.legals.beans.Terms;
import net.syscon.s4.inst.legals.beans.Type;
import net.syscon.s4.inst.schedules.OidrelscService;

@Service
public class OcdcCasesServiceImpl implements OcdcCasesService {

	@Autowired
	OcdcCasesRepository OcdcCasesRepository;

	@Autowired
	OcucalcrRepository ocucalcrRepository;
	
	@Autowired
	OidrelscService oidrelscService;
	
	@Autowired
	private OcmpconfRepository ocmpconfRepository;


	/**
	 * Calling Database layer to populate court case data
	 */
	@Override
	public List<CourtCases> searchCourtCases(CourtCases offenderCases) {
		List<CourtCases> courtCasesData = new ArrayList<>();
		courtCasesData = OcdcCasesRepository.searchCourtCases(offenderCases);
		return courtCasesData;
	}

	/**
	 * Calling Database layer to populate agency description
	 */
	@Override
	public List<Court> populateCourtData() {
		List<Court> courtList = new ArrayList<Court>();
		courtList = OcdcCasesRepository.populateCourtData();
		courtList.forEach(refcode->{
			if (ApplicationConstants.YFLAG.equals(refcode.getActiveFlag())) {
				refcode.setCanDisplay(true);
			} else {
				refcode.setCanDisplay(false);
			}
		});
		return courtList;
	}

	@Override
	public List<Type> populateTypeData() {
		List<Type> typeList = new ArrayList<>();
		typeList = OcdcCasesRepository.populateTypeData();
		return null;
	}

	 /**
	 * Calling Database layer to populate court event data
	 */
	@Override
	public List<CourtEvent> searchCourtEventData(String caseId) {
		List<CourtEvent> courtEventData = new ArrayList<>();
		courtEventData = OcdcCasesRepository.searchCourtEvents(caseId);
		return courtEventData;
	}


	/**
	 * Calling Database layer to populate hearing type description
	 */
	@Override
	public List<HearingType> populateHearingTypeData() {
		List<HearingType> hearingTypeList = new ArrayList<>();
		hearingTypeList = OcdcCasesRepository.populateHearingType();
		return hearingTypeList;

	}

	@Override
	public List<Outcome> populateOutcomeData() {
		List<Outcome> outcomeList = new ArrayList<>();
		outcomeList = OcdcCasesRepository.populateOutcomeData();
		return outcomeList;

	}

	@Override
	public List<OffensesOutcome> searchOffensesOutcomeData(CourtEvent courtEvent) {
		List<OffensesOutcome> offensesDataList = new ArrayList<>();
		offensesDataList = OcdcCasesRepository.searchOffensesOutcomeData(courtEvent);
		return offensesDataList;
	}

	@Override
	public List<OffenseType> populateOffenseType(String offenceCode) {
		List<OffenseType> offensesTypeList = new ArrayList<>();
		offensesTypeList = OcdcCasesRepository.populateOffenseType(offenceCode);
		return offensesTypeList;

	}

	@Override
	public List<Plea> populatePleaData() {
		List<Plea> pleaList = new ArrayList<>();
		pleaList = OcdcCasesRepository.populatePleaData();
		return pleaList;
	}

	@Override
	public List<OffenseResultCodes> populateResultData() {
		List<OffenseResultCodes> resultList = new ArrayList<>();
		resultList = OcdcCasesRepository.populateResultData();
		return resultList;
	}

	@Override
	public List<Sentences> populateSentencesData(CourtCases offenderCases) {
		List<Sentences> sentencesList = new ArrayList<>();
		sentencesList = OcdcCasesRepository.populateSentencesData(offenderCases);
		return sentencesList;
	}

	@Override
	public List<Category> populateSentencesCategory() {
		List<Category> sentencesCategoryList = new ArrayList<>();
		sentencesCategoryList = OcdcCasesRepository.populateSentencesCategory();
		return sentencesCategoryList;
	}

	@Override
	public List<Category> populateSentencesType(String category) {
		List<Category> sentencesTypeList = new ArrayList<>();
		sentencesTypeList = OcdcCasesRepository.populateSentencesType(category);
		return sentencesTypeList;
	}

	@Override
	public List<SentenceDate> populateSentenceDate(Long caseId) {
		List<SentenceDate> sentencesDateList = new ArrayList<>();
		sentencesDateList = OcdcCasesRepository.populateSentenceDate(caseId);
		return sentencesDateList;
	}

	@Override
	public List<Sentences> populateConsecutiveToLineData(Sentences sentences) {
		List<Sentences> sentencesDateList = new ArrayList<>();
		sentencesDateList = OcdcCasesRepository.populateConsecutiveToLineData(sentences);
		return sentencesDateList;
	}

	@Override
	public List<Category> populateSentenceStatus() {
		List<Category> sentenceStatusList = new ArrayList<>();
		sentenceStatusList = OcdcCasesRepository.populateSentenceStatus();
		return sentenceStatusList;
	}


	@Override
	public List<Terms> populateTermsData(Sentences sentences) {
		List<Terms> termsList = new ArrayList<>();
		termsList = OcdcCasesRepository.populatetermsData(sentences);
		return termsList;
	}

	@Override
	public List<LovList> populateSentenceTermCode(String sentenceCalcType, String sentenceCategory) {
		List<LovList> resultList = new ArrayList<LovList>();
		resultList = OcdcCasesRepository.populateSentenceTermCode(sentenceCalcType,sentenceCategory);
		return resultList;
	}

	@Override
	public List<OffensesOutcome> populateOffensesData(Sentences sentence) {
		List<OffensesOutcome> offensesDiscriptionData = new ArrayList<>();
		offensesDiscriptionData = OcdcCasesRepository.populateOffensesData(sentence);
		return offensesDiscriptionData;
	}

	@Transactional
	public Integer insertNewCase(OffenderCaseCommitBean courtCaseCommitBean) {
		int liReturn = 0;
		if (courtCaseCommitBean.getInsertList() != null && courtCaseCommitBean.getInsertList().size() > 0) {
			List<CourtCases> recordSavingObject = new ArrayList<>();
			if (courtCaseCommitBean.getInsertList().size() > 0) {
				for (CourtCases courtCase :courtCaseCommitBean.getInsertList()) {
					recordSavingObject = new ArrayList<>();
					final Long valueCaseSeq = getPreInsertCaseSeq(courtCase.getOffenderBookId());
					final Long valueCaseId = getPreInsertCaseSeq();
					courtCase.setCase_Seq(Long.valueOf(valueCaseSeq));
					courtCase.setcaseId(valueCaseId);
					recordSavingObject.add(courtCase);
					liReturn = insertNewCourtCase(recordSavingObject);
					if(liReturn==1) {
						if(courtCase.getCourtEventList()!=null) {
							for (CourtEvent courtEvent : courtCase.getCourtEventList()) {
								OffenderCourtEventCommitBean courtEventCommitBean = new OffenderCourtEventCommitBean();
								List<CourtEvent> eventInsertList = new ArrayList<CourtEvent>();
								List<CourtEvent> eventUpdateList = new ArrayList<CourtEvent>();
								if(courtEvent.getCommitFlag()=="u") {
									eventUpdateList.add(courtEvent);
								}else if(courtEvent.getCommitFlag().equals("i")) {
									courtEvent.setCaseId(valueCaseId);
									eventInsertList.add(courtEvent);
								}
								courtEventCommitBean.setInsertList(eventInsertList);
								courtEventCommitBean.setUpdateList(eventUpdateList);
								courtEventCommitBean.setSelectedCourtcase(courtCase);
								liReturn = insertNewEvent(courtEventCommitBean);
							}
						}

					}
				}
			}
		}
		if (courtCaseCommitBean.getUpdateList() != null && courtCaseCommitBean.getUpdateList().size() > 0) {
			liReturn = updateCourtCase(courtCaseCommitBean.getUpdateList());
			}
		return liReturn;
		}



	private Long getPreInsertCaseSeq() {
		return OcdcCasesRepository.getPreInsertCaseId();
	}

	@Transactional
	private Integer insertNewCourtCase(List<CourtCases> insertList) {
		int liReturn = 0;
		liReturn = OcdcCasesRepository.insertNewCase(insertList);
		return liReturn;
	}

	@Transactional
	private Integer updateCourtCase(List<CourtCases> updatedList) {
		int liReturn = 0;
		liReturn = OcdcCasesRepository.updateCourtCase(updatedList);
		return liReturn;
	}

	public Long getPreInsertCaseSeq(final Long offenderBookId) {
		return OcdcCasesRepository.getPreInsertCaseSeq(offenderBookId);
	}

	@Override
	public List<CaseStatus> populateCaseStatus() {
		List<CaseStatus> caseStatusList = new ArrayList<CaseStatus>();
		caseStatusList = OcdcCasesRepository.populateCaseStatus();
		return caseStatusList;
	}

	@Override
	public Object getPreInsertCaseType() {
		return OcdcCasesRepository.getPreInsertCaseType();
	}

	@Override
	public List<CaseStatus> preInsertCasePrefixInfo(String caseType) {
		List<CaseStatus> casePrefixinfo = new ArrayList<CaseStatus>();
		casePrefixinfo = OcdcCasesRepository.preInsertCasePrefixInfo(caseType);
		return casePrefixinfo;
	}

	@Override
	public Object getPreInsertAgyLocation(String offenderBookId) {
		return OcdcCasesRepository.getPreInsertAgyLocation(offenderBookId);
	}

	@Transactional
	public Integer insertNewEvent(OffenderCourtEventCommitBean courtEventCommitBean) {
		int liReturn = 0;
		CourtEvent nextEventInserted=new CourtEvent();
		CourtEvent nextEventUpdated=new CourtEvent();
		CourtEvent courtOrderDateUpdated=new CourtEvent();
		OffensesOutcome insertEventCharges = new OffensesOutcome();
		Date bookingDate;
		Date latestMovement;
		if (courtEventCommitBean.getInsertList() != null && courtEventCommitBean.getInsertList().size() > 0) {
			List<CourtEvent> recordSavingObject = new ArrayList<>();
			if (courtEventCommitBean.getInsertList().size() > 0) {
				for (int i = 0; i < courtEventCommitBean.getInsertList().size(); i++) {

					final Long valueEventId = getPreInsertEventId();
					final CourtEvent newCourtEvent = courtEventCommitBean.getInsertList().get(i);
					newCourtEvent.setEventId(valueEventId);
					newCourtEvent.setOffenderBookId(courtEventCommitBean.getInsertList().get(i).getOffenderBookId());

					bookingDate=getPreInsertBookingDate(courtEventCommitBean.getInsertList().get(i).getOffenderBookId());
					latestMovement=getLatestMovement(courtEventCommitBean.getInsertList().get(i).getOffenderBookId());
					if(courtEventCommitBean.getInsertList().get(i).getEventDate().before(bookingDate) || courtEventCommitBean.getInsertList().get(i).getStartTime().before(latestMovement)) {
						newCourtEvent.setEventStatus("COMP");
					} else {
						newCourtEvent.setEventStatus("SCH");
					}
					if(courtEventCommitBean.getInsertList().get(i).getHoldFlag()=="true") {
						newCourtEvent.setHoldFlag("Y");
					} else if(courtEventCommitBean.getInsertList().get(i).getHoldFlag()=="false") {
						newCourtEvent.setHoldFlag("N");
					}
					if(courtEventCommitBean.getInsertList().get(i).getOrderRequestedFlag()=="true") {
						newCourtEvent.setOrderRequestedFlag("Y");
					} else if(courtEventCommitBean.getInsertList().get(i).getOrderRequestedFlag()=="false") {
						newCourtEvent.setOrderRequestedFlag("N");
					}
					recordSavingObject.clear();
					recordSavingObject.add(newCourtEvent);
					liReturn = insertNewCourtEvent(recordSavingObject);
					if(liReturn==1) {
						if(courtEventCommitBean.getInsertList().get(i).getNextEventDate()!=null) {
							nextEventInserted=insertNextEventRecord(recordSavingObject);
							if(nextEventInserted!=null) {
								liReturn=1;
							}else {
								liReturn=0;
								}
							}
					recordSavingObject.get(0).setNbtEventDate(recordSavingObject.get(0).getEventDate());
					if(recordSavingObject.get(0).getAgyLocId().equals(courtEventCommitBean.getSelectedCourtcase().getAgy_loc_id())){
						} else {
							liReturn =updateAgyLocation(recordSavingObject);
						}
					liReturn=insertEventCharges(recordSavingObject);

					if(newCourtEvent.getOffenseOutcomeList()!=null) {
						for (OffensesOutcome outcome : newCourtEvent.getOffenseOutcomeList()) {
							OffenceOutcomeCommitBean outcomeCommitBean = new OffenceOutcomeCommitBean();
							List<OffensesOutcome> outcomeInsertList = new ArrayList<OffensesOutcome>();
							List<OffensesOutcome> outcomeUpdateList = new ArrayList<OffensesOutcome>();
							if(outcome.getCommitFlag()=="u") {
								outcome.setResultcode1indicator(outcome.getDisposition());
								outcomeUpdateList.add(outcome);
							}else if(outcome.getCommitFlag().equals("i")) {
								if(outcome.getCaseId()==null) {
									outcome.setCase_id(newCourtEvent.getCaseId());
								}
								outcome.setEventId(valueEventId);
								outcome.setResultcode1indicator(outcome.getDisposition());
								outcome.setChargeStatus("A");
								outcomeInsertList.add(outcome);
							}
							outcomeCommitBean.setInsertList(outcomeInsertList);
							outcomeCommitBean.setUpdateList(outcomeUpdateList);
							liReturn = insertUpdateOffenseData(outcomeCommitBean);
						}


						}

					}

				}
			}
		}

		if (courtEventCommitBean.getUpdateList() != null && courtEventCommitBean.getUpdateList().size() > 0) {
			List<CourtEvent> recordSavingObject = new ArrayList<>();
			for (int i = 0; i < courtEventCommitBean.getUpdateList().size(); i++) {
				final CourtEvent newCourtEvent = courtEventCommitBean.getUpdateList().get(i);
					newCourtEvent.setOffenderBookId(courtEventCommitBean.getUpdateList().get(i).getOffenderBookId());
					bookingDate=getPreInsertBookingDate(courtEventCommitBean.getUpdateList().get(i).getOffenderBookId());
					latestMovement=getLatestMovement(courtEventCommitBean.getUpdateList().get(i).getOffenderBookId());
					if(courtEventCommitBean.getUpdateList().get(i).getEventDate().before(bookingDate) || courtEventCommitBean.getUpdateList().get(i).getStartTime().before(latestMovement)) {
						newCourtEvent.setEventStatus("COMP");
					} else {
						newCourtEvent.setEventStatus("SCH");
					}
					if(courtEventCommitBean.getUpdateList().get(i).getHoldFlag()=="true") {
						newCourtEvent.setHoldFlag("Y");
					} else if(courtEventCommitBean.getUpdateList().get(i).getHoldFlag()=="false") {
						newCourtEvent.setHoldFlag("N");
					}
					if(courtEventCommitBean.getUpdateList().get(i).getOrderRequestedFlag()=="true") {
						newCourtEvent.setOrderRequestedFlag("Y");
					} else if(courtEventCommitBean.getUpdateList().get(i).getOrderRequestedFlag()=="false") {
						newCourtEvent.setOrderRequestedFlag("N");
					}
					if(courtEventCommitBean.getUpdateList().get(i).getOrderRequestedFlag()=="true") {
						newCourtEvent.setOrderRequestedFlag("Y");
					} else if(courtEventCommitBean.getUpdateList().get(i).getOrderRequestedFlag()=="false") {
						newCourtEvent.setOrderRequestedFlag("N");
					}
					recordSavingObject.clear();
					recordSavingObject.add(newCourtEvent);
					if(courtEventCommitBean.getUpdateList().get(i).getNextEventDate()!=null) {
						if(courtEventCommitBean.getUpdateList().get(i).getNextEventDate().equals(courtEventCommitBean.getUpdateList().get(i).getPreExistedEventDate())) {
							}else {
									if(!isSameEventExist(recordSavingObject.get(0))) {
										if(getActiveAgencyLocationDesc(recordSavingObject.get(0).getAgyLocId())==null) {
											return 2;
										}
									}
									nextEventUpdated=insertNextEventRecord(recordSavingObject);
									if(nextEventUpdated!=null) {
										liReturn=1;
									}else {
										liReturn=0;
										}
									}
						}
					if(recordSavingObject.get(0).getAgyLocId().equals(courtEventCommitBean.getSelectedCourtcase().getAgy_loc_id())){
						} else {
							liReturn =updateAgyLocation(recordSavingObject);
							}
					if(recordSavingObject.get(0).getOutcomeReasonCode()!=null) {
						insertEventCharges=updateEventCharges(recordSavingObject);
						}
					liReturn = updateOrderCourtDate(recordSavingObject);
					if(liReturn==1) {
						liReturn = updateCourtEvent(recordSavingObject);
						}

					}
			}
		return liReturn;
	}

	private Boolean isSameEventExist(CourtEvent courtEvent) {
		return OcdcCasesRepository.isSameEventExist(courtEvent);
	}

	private String getActiveAgencyLocationDesc(String agyLoc) {
		String abc= OcdcCasesRepository.getActiveAgencyLocationDesc(agyLoc);
		return abc;
	}

	@Transactional
	private Integer updateCourtEvent(List<CourtEvent> updateList) {
		int liReturn = 0;
		liReturn = OcdcCasesRepository.updateCourtEvent(updateList);
		return liReturn;
	}

	@Transactional
	private Integer insertNewCourtEvent(List<CourtEvent> insertList) {
		int liReturn = 0;
		liReturn = OcdcCasesRepository.insertNewCourtEvent(insertList);
		return liReturn;
	}

	private Long getPreInsertEventId() {
		return OcdcCasesRepository.getPreInsertEventId();
	}

	private Date getPreInsertBookingDate(Long OffenderBookId) {
		return OcdcCasesRepository.getPreInsertBookingDate(OffenderBookId);
	}

	private Date getLatestMovement(Long OffenderBookId) {
		return OcdcCasesRepository.getPreInsertBookingDate(OffenderBookId);
	}

	@Transactional
	private CourtEvent insertNextEventRecord(List<CourtEvent> list) {
		int liReturn = 0;
		CourtEvent nextCourtEvent = new CourtEvent();
		nextCourtEvent = OcdcCasesRepository.insertNextEventRecord(list);
		return nextCourtEvent;
	}

	@Transactional
	private Integer updateOrderCourtDate(List<CourtEvent> updateList) {
		return OcdcCasesRepository.updateOrderCourtDate(updateList);
	}

	@Transactional
	public Integer updateAgyLocation(List<CourtEvent> list) {
		int liReturn = 0;
		liReturn=OcdcCasesRepository.updateAgyLocation(list);
		return liReturn;
	}



	@Transactional
	private Integer insertEventCharges(List<CourtEvent> insertList) {
		return OcdcCasesRepository.insertEventCharges(insertList);

	}

	@Transactional
	private OffensesOutcome updateEventCharges(List<CourtEvent> updateList) {
		int liReturn = 0;
		OffensesOutcome offenseCharges = new OffensesOutcome();
		offenseCharges = OcdcCasesRepository.updateEventCharges(updateList);
		return offenseCharges;
	}

	@Override
	public List<BailStatus> populateBailStatus() {
		return OcdcCasesRepository.populateBailStatus();
	}

	@Override
	public List<BondType> populateBondType() {
		return OcdcCasesRepository.populateBondType();
	}

	@Transactional
	public Integer updateBailDetailsData(BailDetailsCommitBean bailDetailsBeanCommit) {
		Integer returnRows = 0;
		if(null!=bailDetailsBeanCommit.getUpdateList() && bailDetailsBeanCommit.getUpdateList().size()>0) {
			returnRows = updateData(bailDetailsBeanCommit.getUpdateList());
		}
		return  returnRows;
	}

	@Transactional
	private int[] insertBailData(List<OffenderBailDetails> insertList,String userId) {
		int[] returnRows = {};
		List<OffenderBailDetails> recordSavingObject = new ArrayList<>();
		for ( OffenderBailDetails newRecord :insertList) {
			recordSavingObject = new ArrayList<>();
			newRecord.setCreateUserId(userId);
			newRecord.setModifyUserId(userId);
			recordSavingObject.add(newRecord);
		}
		returnRows = OcdcCasesRepository.insertBailDetail(insertList);
		return returnRows;
	}

	@Transactional
	private Integer updateData(List<OffenderBailDetails> updatetList) {
		int liReturn = 0;
		List<OffenderBailDetails> recordSavingObject = new ArrayList<>();
		for ( OffenderBailDetails newDetailData :updatetList) {
			recordSavingObject = new ArrayList<>();
			newDetailData.setCreateDatetime(new Date());
			newDetailData.setModifyDateTime(new Date());
			recordSavingObject.add(newDetailData);
		}
		liReturn = OcdcCasesRepository.updateBailDetails(updatetList);
		return liReturn;
	}

	@Override
	public List<OffenderDetailsOffenses> populateBailOfences(Integer caseId) {
		return OcdcCasesRepository.populateBailOfences(caseId);
	}

	@Override
	public Integer updateBailBondDetails(OffenderBailDetails bailBond) {
		return OcdcCasesRepository.updateBailBondDetails(bailBond);
	}

	@Override
	public List<OffenderBailDetails> getAllBailDetails(Integer bookId, Integer caseId) {
		return OcdcCasesRepository.getAllBailDetails(bookId, caseId);
	}

	@Transactional
	public Integer insertOffenderSentenceDetails(SentenceCommitBean offenderSentenceCommitBean) {
		int liReturn = 0;

		if (offenderSentenceCommitBean.getInsertList() != null && offenderSentenceCommitBean.getInsertList().size() > 0) {
			List<Sentences> recordSavingObject = new ArrayList<>();
			if (offenderSentenceCommitBean.getInsertList().size() > 0) {
				for (int i = 0; i < offenderSentenceCommitBean.getInsertList().size(); i++) {
					recordSavingObject = new ArrayList<>();
					final Long valueSentenceSeq = getPreInsertSentenceSeq(offenderSentenceCommitBean.getInsertList().get(i).getOffenderBookId());
					final Long valueLineSeq = getPreInsertLineSeq(offenderSentenceCommitBean.getInsertList().get(i).getOffenderBookId());
					final Sentences insertSentenceData = offenderSentenceCommitBean.getInsertList().get(i);
					insertSentenceData.setSentenceSeq(Long.valueOf(valueSentenceSeq));
					insertSentenceData.setLine(Long.valueOf(valueLineSeq));
					insertSentenceData.setSentenceLevel("IND");
					recordSavingObject.add(insertSentenceData);
					liReturn = insertOffenderSentenceRecord(recordSavingObject);
					if(liReturn==1) {
						if(insertSentenceData.getOffensesOnSentenceList()!=null) {
							for (OffensesOutcome offenseOnSent : insertSentenceData.getOffensesOnSentenceList()) {
								OffensesOutcomeCommitBean offenseOnSentCommitBean = new OffensesOutcomeCommitBean();
								List<OffensesOutcome> offensesInsertList = new ArrayList<OffensesOutcome>();
								if(offenseOnSent.getCommitFlag().equals("i")) {
									offenseOnSent.setSentenceSeq(valueSentenceSeq);
									offensesInsertList.add(offenseOnSent);
								}
								offenseOnSentCommitBean.setInsertList(offensesInsertList);
								liReturn = insertOffensesOnSentencing(offenseOnSentCommitBean);
							}

						}

						if(insertSentenceData.getSentenceTermList()!=null) {
							for (Terms sentTerm : insertSentenceData.getSentenceTermList()) {
								SentenceTermCommitBean termCommitBean = new SentenceTermCommitBean();
								List<Terms> senTermInsertList = new ArrayList<Terms>();
								if(sentTerm.getCommitFlag().equals("i")) {
									sentTerm.setSentenceSeq(valueSentenceSeq);
									senTermInsertList.add(sentTerm);
								}
								termCommitBean.setInsertList(senTermInsertList);
								liReturn = insertOffenderSentenceTerms(termCommitBean);
							}

						}
					}
				}
			}
		}
		if (offenderSentenceCommitBean.getUpdateList() != null && offenderSentenceCommitBean.getUpdateList().size() > 0) {
			liReturn = OffenderSentenceRecord(offenderSentenceCommitBean.getUpdateList());
			}
		return liReturn;
		}

	@Transactional
	private Integer insertOffenderSentenceRecord(List<Sentences> newSentenceRecord) {
		Integer liReturn=0;
		liReturn = OcdcCasesRepository.insertOffenderSentenceDetails(newSentenceRecord);
		return liReturn;
	}

	@Transactional
	private Integer OffenderSentenceRecord(List<Sentences> newSentenceRecord) {
		Integer liReturn=0;
		liReturn = OcdcCasesRepository.OffenderSentenceRecord(newSentenceRecord);
		return liReturn;
	}

	private Long getPreInsertSentenceSeq(final Long offenderBookId) {
		return OcdcCasesRepository.getPreInsertSentenceSeq(offenderBookId);
	}

	private Long getPreInsertLineSeq(final Long offenderBookId) {
		return OcdcCasesRepository.getPreInsertLineSeq(offenderBookId);
	}

	@Transactional
	public Integer insertOffenderSentenceTerms(SentenceTermCommitBean offenderSentenceTermCommitBean) {
		Integer liReturn = 0;
		String successFlag="F";
		SentenceCalculation sentenceCalculation = null;
		if (offenderSentenceTermCommitBean.getInsertList() != null && offenderSentenceTermCommitBean.getInsertList().size() > 0) {
			List<Terms> recordSavingObject = new ArrayList<>();
			if (offenderSentenceTermCommitBean.getInsertList().size() > 0) {
				for (int i = 0; i < offenderSentenceTermCommitBean.getInsertList().size(); i++) {
					recordSavingObject = new ArrayList<>();
					final Integer valueTermSeq = getPreInsertTermSeq(offenderSentenceTermCommitBean.getInsertList().get(i).getOffenderBookId(),offenderSentenceTermCommitBean.getInsertList().get(i).getSentenceSeq());
					final Terms insertSentenceTerm = offenderSentenceTermCommitBean.getInsertList().get(i);
					insertSentenceTerm.setTermSeq(Integer.valueOf(valueTermSeq));
					recordSavingObject.add(insertSentenceTerm);
					liReturn = insertOffenderSentenceTermRecord(recordSavingObject);
					if(liReturn==1) {
						if(insertSentenceTerm.getSentenceCalculation()!=null) {
							sentenceCalculation = insertSentenceTerm.getSentenceCalculation();
						}
					}
				}
			}
		}
		if (offenderSentenceTermCommitBean.getUpdateList() != null && offenderSentenceTermCommitBean.getUpdateList().size() > 0) {
			liReturn = updateOffenderSentenceTerm(offenderSentenceTermCommitBean.getUpdateList());
			if(liReturn==1) {
				for(Terms updatedTerm : offenderSentenceTermCommitBean.getUpdateList()) {
					if(updatedTerm.getSentenceCalculation()!=null) {
						sentenceCalculation = updatedTerm.getSentenceCalculation();
				   }
				}
				
			  }
			}
		/*if(sentenceCalculation !=null) {
			successFlag = ocucalcrRepository.calExpDate(sentenceCalculation);
		}*/
		return liReturn;
	}

	private Integer getPreInsertTermSeq(final Long offenderBookId,final Long sentenceSeq) {
		return OcdcCasesRepository.getPreInsertTermSeq(offenderBookId,sentenceSeq);
	}

	@Transactional
	private Integer insertOffenderSentenceTermRecord(List<Terms> newSentenceRecord) {
		Integer liReturn=0;
		liReturn = OcdcCasesRepository.insertOffenderSentenceTermRecord(newSentenceRecord);
		return liReturn;
	}

	@Transactional
	private Integer updateOffenderSentenceTerm(List<Terms> updatedList) {
		int liReturn = 0;
		liReturn = OcdcCasesRepository.updateOffenderSentenceTerm(updatedList);
		return liReturn;
	}

	@Transactional
	public Integer insertOffensesOnSentencing(OffensesOutcomeCommitBean offensesOnSentenceCommitBean) {
	Integer liReturn = 0;

		if (offensesOnSentenceCommitBean.getInsertList() != null && offensesOnSentenceCommitBean.getInsertList().size() > 0) {
			List<OffensesOutcome> recordSavingObject = new ArrayList<>();
			if (offensesOnSentenceCommitBean.getInsertList().size() > 0) {
				for (int i = 0; i < offensesOnSentenceCommitBean.getInsertList().size(); i++) {
					recordSavingObject = new ArrayList<>();
					final OffensesOutcome offensesList = offensesOnSentenceCommitBean.getInsertList().get(i);
					recordSavingObject.add(offensesList);
					liReturn = insertOffensesOnSentence(recordSavingObject);
				}
			}
		}
		return liReturn;
	}

	@Transactional
	private Integer insertOffensesOnSentence(List<OffensesOutcome> offensesList) {
		Integer liReturn=0;
		liReturn = OcdcCasesRepository.insertOffensesOnSentenceByQuery(offensesList);
		return liReturn;
	}



	@Override
	public List<OffensesOutcome> getOffenderOffences(String offenderBookId) {
		OffensesOutcome data = new OffensesOutcome();
		List<OffensesOutcome> legals = new ArrayList<OffensesOutcome>();
		SimpleDateFormat sdf1 = new SimpleDateFormat(("dd/MM/yyyy"));
		List<OffensesOutcome> legalsDetails = OcdcCasesRepository.getLegalsDetails(offenderBookId);
		if (legalsDetails.size() > 0 && legalsDetails != null) {
			legalsDetails.forEach(obj -> {
				if (ApplicationConstants.PAROLE_ELIGIBLE_DATE.equals(obj.getCode())) {
					if (!"-".equals(obj.getOverride())) {
						data.setParoleEligibilityDate(obj.getOverride());
					} else {
						data.setParoleEligibilityDate("-".equals(obj.getValue()) ? null : obj.getValue());
					}
				} else if (ApplicationConstants.REMISSION_ELIGIBITY_DATE.equals(obj.getCode())) {
					if (!"-".equals(obj.getOverride())) {
						data.setRemissionEligibilityDate(obj.getOverride());
					} else {
						data.setRemissionEligibilityDate("-".equals(obj.getValue()) ? null : obj.getValue());
					}
				} else if (ApplicationConstants.LATEST_RELEASE_DATE.equals(obj.getCode())) {
					if (!"-".equals(obj.getOverride())) {
						data.setLatestReleaseDate(obj.getOverride());
					} else {
						data.setLatestReleaseDate("-".equals(obj.getValue()) ? null : obj.getValue());
					}
				} else if (ApplicationConstants.ERD.equals(obj.getCode())) {
					if (!"-".equals(obj.getOverride())) {
						data.setExpectedReleaseDate(obj.getOverride());
					} else {
						data.setExpectedReleaseDate("-".equals(obj.getValue()) ? null : obj.getValue());
					}
				} else if (obj.getCustodyStatus() != null) {
					String custodyStatus = obj.getCustodyStatus();
					String status= "";
					if(custodyStatus!=null && !"".equals(custodyStatus)) {  
						if(custodyStatus.contains("-")) {
							String[] statusList = custodyStatus.split("-");
							for (int i = 0, size = statusList.length; i < size; i++) {
								String statusDesc = ocmpconfRepository.getStatusDesc(statusList[i].trim());
								if(i == 0) {
									status = statusDesc; 
								} else  {
									status = status + " - " + statusDesc;
								}
							}
						} else {
							String statusDesc = ocmpconfRepository.getStatusDesc(custodyStatus);
							status = statusDesc;
						}
					}
					data.setCustodyStatus(status);
				}
			});
		}
		Date releaseDate = oidrelscService.getReleaseDate(offenderBookId != null ? Long.valueOf(offenderBookId) : null);
		if (releaseDate != null) {
			String relDate = sdf1 != null ? sdf1.format(releaseDate) : "";
			data.setConfirmedReleaseDate(relDate);
		}
		legals.add(data);
		return legals;
	}

	/*******************************insert Offenses Outcome *********************/

	@Override
	public Integer  insertUpdateOffenseData( OffenceOutcomeCommitBean OffenceCommitBean) {
	Integer liReturn = 0;
		if (OffenceCommitBean.getInsertList() != null && OffenceCommitBean.getInsertList().size() > 0) {
			List<OffensesOutcome> recordSavingObject = new ArrayList<>();
			if (OffenceCommitBean.getInsertList().size() > 0) {
				for (int i = 0; i < OffenceCommitBean.getInsertList().size(); i++) {
					recordSavingObject = new ArrayList<>();
					final OffensesOutcome newOffenceOut = OffenceCommitBean.getInsertList().get(i);
					String statuteCode = fetchStatuteCode(newOffenceOut.getOffenceCode());
					newOffenceOut.setOffenderChargeId(null);
					newOffenceOut.setStatuteCode(statuteCode);
					newOffenceOut.setMostseriousflag("N");
					newOffenceOut.setResultcode2(null);
					newOffenceOut.setResultcode2desc(null);
					newOffenceOut.setResultcode2indicator(null);
					newOffenceOut.setCjitoffencecode1(null);
					newOffenceOut.setCjitoffencecode2(null);
					newOffenceOut.setCjitoffencecode3(null);
					newOffenceOut.setChecksum(null);
					newOffenceOut.setPropertyValue(null);
					newOffenceOut.setTotalpropertyvalue(null);
					newOffenceOut.setNoofoffences(null);
					recordSavingObject.clear();
					recordSavingObject.add(newOffenceOut);
					liReturn = insertOffenceOutcome(recordSavingObject);
				}
			}
		}
		if (OffenceCommitBean.getUpdateList() != null && OffenceCommitBean.getUpdateList().size() > 0) {
			liReturn = updateOffenceOutcome(OffenceCommitBean.getUpdateList());
			}
		return liReturn;
	}

	private String fetchStatuteCode(String offenceCode) {
		return OcdcCasesRepository.fetchStatuteCode(offenceCode);
	}
	@Transactional
	private Integer insertOffenceOutcome(List<OffensesOutcome> insertList) {
		Integer liReturn =0;
		liReturn = OcdcCasesRepository.insertNewOffenceOutcome(insertList);
		return liReturn;
	}

	@Transactional
	private Integer updateOffenceOutcome(List<OffensesOutcome> updatedList) {
		Integer liReturn = 0;
		liReturn = OcdcCasesRepository.updateOffenceOutcome(updatedList);
		return liReturn;
	}

	@Override
	public Boolean isOffenceExist(OffensesOutcome offensesOutcome) {
		boolean isExist=false;
		isExist=OcdcCasesRepository.isOffenceExist(offensesOutcome);
		return isExist;
	}

	@Override
	public Integer updateOrderDate(CourtEvent courtEvent) {
		return OcdcCasesRepository.updateOrderCourtDate(courtEvent);
	}

	@Transactional
	@Override
	public Integer commitCases(List<CourtCases> courtCaseList) {
		int liReturn = 0;
		List<CourtCases> insertList = new ArrayList<CourtCases>();
		List<CourtCases> updateList = new ArrayList<CourtCases>();
		List<CourtEvent> eventInsertList = new ArrayList<CourtEvent>();
		List<CourtEvent> eventUpdateList = new ArrayList<CourtEvent>();
		List<Sentences> sentenceInsertList = new ArrayList<Sentences>();
		List<Sentences> sentenceUpdateList = new ArrayList<Sentences>();
		List<OffensesOutcome> outcomeInsertList = new ArrayList<OffensesOutcome>();
		List<OffensesOutcome> outcomeUpdateList = new ArrayList<OffensesOutcome>();
		OffenderCaseCommitBean commitBean = new OffenderCaseCommitBean();
		List<OffensesOutcome> offensesOnSentInsertList = new ArrayList<OffensesOutcome>();
		List<OffensesOutcome> offensesOnSentUpdateList = new ArrayList<OffensesOutcome>();
		List<Terms> termInsertList = new ArrayList<Terms>();
		List<Terms> termUpdateList = new ArrayList<Terms>();
		List<OffenderBailDetails> bailDetailUpdateList = new ArrayList<OffenderBailDetails>();

		for(CourtCases courtCase : courtCaseList) {
			if(courtCase.getCommitFlag()!=null) {
				if(courtCase.getCommitFlag().equals("u")) {
					updateList.add(courtCase);
				}else if(courtCase.getCommitFlag().equals("i")) {
					insertList.add(courtCase);
				}
			}
			if(!"i".equals(courtCase.getCommitFlag()) && courtCase.getCourtEventList()!=null && courtCase.getCourtEventList().size()>0) {
					OffenderCourtEventCommitBean eventCommitBean = new OffenderCourtEventCommitBean();
					for(CourtEvent courtEvent : courtCase.getCourtEventList()) {
						if(courtEvent.getCommitFlag()!=null) {
							if(courtEvent.getCommitFlag().equals("u")) {
								eventUpdateList.add(courtEvent);
							}else if(courtEvent.getCommitFlag().equals("i")) {
								eventInsertList.add(courtEvent);
							}
						}

						if(!"i".equals(courtEvent.getCommitFlag()) && courtEvent.getOffenseOutcomeList()!=null && courtEvent.getOffenseOutcomeList().size()>0) {
							OffenceOutcomeCommitBean outcomeCommitBean = new OffenceOutcomeCommitBean();
							for(OffensesOutcome offensesOutcome : courtEvent.getOffenseOutcomeList()) {
								if(offensesOutcome.getCommitFlag()!=null) {
									if(offensesOutcome.getCommitFlag().equals("u")) {
										offensesOutcome.setResultcode1indicator(offensesOutcome.getDisposition());
										offensesOutcome.setCase_id(courtEvent.getCaseId());
										outcomeUpdateList.add(offensesOutcome);
									}else if(offensesOutcome.getCommitFlag().equals("i")) {
										offensesOutcome.setResultcode1indicator(offensesOutcome.getDisposition());
										offensesOutcome.setChargeStatus("A");
										offensesOutcome.setCase_id(courtEvent.getCaseId());
										outcomeInsertList.add(offensesOutcome);
									}
								}
							}

							if((insertList.size()==0 || updateList.size()>0) && (outcomeInsertList.size()>0 || outcomeUpdateList.size()>0)) {
								outcomeCommitBean.setInsertList(outcomeInsertList);
								outcomeCommitBean.setUpdateList(outcomeUpdateList);
								//eventCommitBean.setSelectedCourtcase(courtCase);
								liReturn = insertUpdateOffenseData(outcomeCommitBean);
								outcomeInsertList = new ArrayList<>();
								outcomeUpdateList = new ArrayList<>();

							}
						}

					}
					if((insertList.size()==0 || updateList.size()>0) && (eventInsertList.size()>0 || eventUpdateList.size()>0)) {
						eventCommitBean.setInsertList(eventInsertList);
						eventCommitBean.setUpdateList(eventUpdateList);
						eventCommitBean.setSelectedCourtcase(courtCase);
						liReturn = insertNewEvent(eventCommitBean);
						eventInsertList = new ArrayList<>();
						eventUpdateList = new ArrayList<>();

					}
				}
			if(courtCase.getSentencesList()!=null && courtCase.getSentencesList().size()>0) {
					for(Sentences sentence : courtCase.getSentencesList()) {
						if(sentence.getCommitFlag()!=null) {
							if(sentence.getCommitFlag().equals("u")) {
								sentenceUpdateList.add(sentence);
							}else if(sentence.getCommitFlag().equals("i")){
								sentenceInsertList.add(sentence);
							}
						}

						if(!"i".equals(sentence.getCommitFlag()) && sentence.getOffensesOnSentenceList()!=null && sentence.getOffensesOnSentenceList().size()>0) {
							OffensesOutcomeCommitBean offenseCommitBean = new OffensesOutcomeCommitBean();
							for(OffensesOutcome offenseOnSent : sentence.getOffensesOnSentenceList()) {
								if(offenseOnSent.getCommitFlag()!=null) {
									if(offenseOnSent.getCommitFlag().equals("u")) {
										offensesOnSentUpdateList.add(offenseOnSent);
									}else if(offenseOnSent.getCommitFlag().equals("i")) {
										offensesOnSentInsertList.add(offenseOnSent);
									}
								}
							}

							if((sentenceInsertList.size()==0 || sentenceUpdateList.size()>0) && (offensesOnSentInsertList.size()>0 || offensesOnSentUpdateList.size()>0)) {
								offenseCommitBean.setInsertList(offensesOnSentInsertList);
								offenseCommitBean.setUpdateList(offensesOnSentUpdateList);
								liReturn = insertOffensesOnSentencing(offenseCommitBean);
								offensesOnSentInsertList = new ArrayList<>();
								offensesOnSentUpdateList = new ArrayList<>();

							}
						}

						if(!"i".equals(sentence.getCommitFlag()) && sentence.getSentenceTermList()!=null && sentence.getSentenceTermList().size()>0) {
							SentenceTermCommitBean termCommitBean = new SentenceTermCommitBean();
							for(Terms term : sentence.getSentenceTermList()) {
								if(term.getCommitFlag()!=null) {
									if(term.getCommitFlag().equals("u")) {
										termUpdateList.add(term);
									}else if(term.getCommitFlag().equals("i")) {
										termInsertList.add(term);
									}
								}
							}

							if((sentenceInsertList.size()==0 || sentenceUpdateList.size()>0) && (termInsertList.size()>0 || termUpdateList.size()>0)) {
								termCommitBean.setInsertList(termInsertList);
								termCommitBean.setUpdateList(termUpdateList);
								liReturn = insertOffenderSentenceTerms(termCommitBean);
								termInsertList = new ArrayList<>();
								termUpdateList = new ArrayList<>();

							}
						}
					}
				}
			if(courtCase.getBailDetailsList()!=null && courtCase.getBailDetailsList().size()>0) {
					for(OffenderBailDetails bailDetails : courtCase.getBailDetailsList()) {
						if(bailDetails.getCommitFlag()!=null && bailDetails.getCommitFlag().equals("u")) {
							bailDetailUpdateList.add(bailDetails);
						}
					}
				}
			}
		if(insertList.size()>0 || updateList.size()>0) {
			commitBean.setInsertList(insertList);
			commitBean.setUpdateList(updateList);
			liReturn = insertNewCase(commitBean);
			insertList = new ArrayList<>();
			updateList = new ArrayList<>();
		}

		if(sentenceInsertList.size()>0 || sentenceUpdateList.size()>0) {
			SentenceCommitBean sentenceCommitBean = new SentenceCommitBean();
			sentenceCommitBean.setInsertList(sentenceInsertList);
			sentenceCommitBean.setUpdateList(sentenceUpdateList);
			liReturn = insertOffenderSentenceDetails(sentenceCommitBean);
			updateSentenceCalculation(sentenceInsertList, sentenceUpdateList);
			sentenceInsertList = new ArrayList<>();
			sentenceUpdateList = new ArrayList<>();
		}

		if(bailDetailUpdateList.size()>0) {
			BailDetailsCommitBean bailDetailsCommitBean = new BailDetailsCommitBean();
			bailDetailsCommitBean.setUpdateList(bailDetailUpdateList);
			liReturn=updateBailDetailsData(bailDetailsCommitBean);
		}
		for(CourtCases courtCase : courtCaseList) {
			if(courtCase.getBailDetailsList()!=null && courtCase.getBailDetailsList().size()>0) {
				for(OffenderBailDetails bondDetails : courtCase.getBailDetailsList()) {
						if(bondDetails.getPreferedDateTime()!=null) {
							liReturn=updateBailBondDetails(bondDetails);
						}
				}
			}
		}
		return liReturn;
	}
	
	private void updateSentenceCalculation(List<Sentences> sentenceInsertList, List<Sentences> sentenceUpdateList) {
		if(sentenceInsertList!=null) {
			for(Sentences sentences:sentenceInsertList) {
				try {
					ocucalcrRepository.calExpDate(sentences.getSentenceCalculation());
				} catch(Exception ex) {
					ex.printStackTrace();
				}
			}
		}
		if(sentenceUpdateList!=null) {
			for(Sentences sentences:sentenceUpdateList) {
				try {
					ocucalcrRepository.calExpDate(sentences.getSentenceCalculation());
				} catch(Exception ex) {
					ex.printStackTrace();
				}
			}
		}
	}
	
	
	
	
	public CourtEvent fetchLatestCourtevent(Long courtCaseId) {
		return OcdcCasesRepository.fetchLatestCourtevent(courtCaseId);
	}
	
	@Override
	public OffenceOutcomeCommitBean  insertUpdateOffenseDataROI( OffenceOutcomeCommitBean OffenceCommitBean) {
	Integer liReturn = 0;
		if (OffenceCommitBean.getInsertList() != null && OffenceCommitBean.getInsertList().size() > 0) {
			List<OffensesOutcome> recordSavingObject = new ArrayList<>();
			if (OffenceCommitBean.getInsertList().size() > 0) {
				for (int i = 0; i < OffenceCommitBean.getInsertList().size(); i++) {
					recordSavingObject = new ArrayList<>();
					final OffensesOutcome newOffenceOut = OffenceCommitBean.getInsertList().get(i);
					String statuteCode = fetchStatuteCode(newOffenceOut.getOffenceCode());
					newOffenceOut.setOffenderChargeId(null);
					newOffenceOut.setStatuteCode(statuteCode);
					newOffenceOut.setMostseriousflag("N");
					newOffenceOut.setResultcode2(null);
					newOffenceOut.setResultcode2desc(null);
					newOffenceOut.setResultcode2indicator(null);
					newOffenceOut.setCjitoffencecode1(null);
					newOffenceOut.setCjitoffencecode2(null);
					newOffenceOut.setCjitoffencecode3(null);
					newOffenceOut.setChecksum(null);
					newOffenceOut.setPropertyValue(null);
					newOffenceOut.setTotalpropertyvalue(null);
					newOffenceOut.setNoofoffences(null);
					recordSavingObject.clear();
					recordSavingObject.add(newOffenceOut);
					liReturn = insertOffenceOutcome(recordSavingObject);
				}
			}
		}
		if (OffenceCommitBean.getUpdateList() != null && OffenceCommitBean.getUpdateList().size() > 0) {
			liReturn = updateOffenceOutcome(OffenceCommitBean.getUpdateList());
			}
		return OffenceCommitBean;
	}

	@Transactional
	public OffenderCourtEventCommitBean insertNewEventREO(OffenderCourtEventCommitBean courtEventCommitBean) {
		int liReturn = 0;
		CourtEvent nextEventInserted=new CourtEvent();
		CourtEvent nextEventUpdated=new CourtEvent();
		CourtEvent courtOrderDateUpdated=new CourtEvent();
		OffensesOutcome insertEventCharges = new OffensesOutcome();
		Date bookingDate;
		Date latestMovement;
		if (courtEventCommitBean.getInsertList() != null && courtEventCommitBean.getInsertList().size() > 0) {
			List<CourtEvent> recordSavingObject = new ArrayList<>();
			if (courtEventCommitBean.getInsertList().size() > 0) {
				for (int i = 0; i < courtEventCommitBean.getInsertList().size(); i++) {

					final Long valueEventId = getPreInsertEventId();
					final CourtEvent newCourtEvent = courtEventCommitBean.getInsertList().get(i);
					newCourtEvent.setEventId(valueEventId);
					newCourtEvent.setOffenderBookId(courtEventCommitBean.getInsertList().get(i).getOffenderBookId());

					bookingDate=getPreInsertBookingDate(courtEventCommitBean.getInsertList().get(i).getOffenderBookId());
					latestMovement=getLatestMovement(courtEventCommitBean.getInsertList().get(i).getOffenderBookId());
					if(courtEventCommitBean.getInsertList().get(i).getEventDate().before(bookingDate) || courtEventCommitBean.getInsertList().get(i).getStartTime().before(latestMovement)) {
						newCourtEvent.setEventStatus("COMP");
					} else {
						newCourtEvent.setEventStatus("SCH");
					}
					if(courtEventCommitBean.getInsertList().get(i).getHoldFlag()=="true") {
						newCourtEvent.setHoldFlag("Y");
					} else if(courtEventCommitBean.getInsertList().get(i).getHoldFlag()=="false") {
						newCourtEvent.setHoldFlag("N");
					}
					if(courtEventCommitBean.getInsertList().get(i).getOrderRequestedFlag()=="true") {
						newCourtEvent.setOrderRequestedFlag("Y");
					} else if(courtEventCommitBean.getInsertList().get(i).getOrderRequestedFlag()=="false") {
						newCourtEvent.setOrderRequestedFlag("N");
					}
					recordSavingObject.clear();
					recordSavingObject.add(newCourtEvent);
					liReturn = insertNewCourtEvent(recordSavingObject);
					if(liReturn==1) {
						if(courtEventCommitBean.getInsertList().get(i).getNextEventDate()!=null) {
							nextEventInserted=insertNextEventRecord(recordSavingObject);
							if(nextEventInserted!=null) {
								liReturn=1;
							}else {
								liReturn=0;
								}
							}
					recordSavingObject.get(0).setNbtEventDate(recordSavingObject.get(0).getEventDate());
					if(courtEventCommitBean.getSelectedCourtcase()!=null && recordSavingObject.get(0).getAgyLocId().equals(courtEventCommitBean.getSelectedCourtcase().getAgy_loc_id())){
						} else if(courtEventCommitBean.getSelectedCourtcase()!=null ) {
							liReturn =updateAgyLocation(recordSavingObject);
						}
					liReturn=insertEventCharges(recordSavingObject);

					if(newCourtEvent.getOffenseOutcomeList()!=null) {
						for (OffensesOutcome outcome : newCourtEvent.getOffenseOutcomeList()) {
							OffenceOutcomeCommitBean outcomeCommitBean = new OffenceOutcomeCommitBean();
							List<OffensesOutcome> outcomeInsertList = new ArrayList<OffensesOutcome>();
							List<OffensesOutcome> outcomeUpdateList = new ArrayList<OffensesOutcome>();
							if(outcome.getCommitFlag()=="u") {
								outcome.setResultcode1indicator(outcome.getDisposition());
								outcomeUpdateList.add(outcome);
							}else if(outcome.getCommitFlag().equals("i")) {
								if(outcome.getCaseId()==null) {
									outcome.setCase_id(newCourtEvent.getCaseId());
								}
								outcome.setEventId(valueEventId);
								outcome.setResultcode1indicator(outcome.getDisposition());
								outcome.setChargeStatus("A");
								outcomeInsertList.add(outcome);
							}
							outcomeCommitBean.setInsertList(outcomeInsertList);
							outcomeCommitBean.setUpdateList(outcomeUpdateList);
							liReturn = insertUpdateOffenseData(outcomeCommitBean);
						}


						}

					}

				}
			}
		}

		if (courtEventCommitBean.getUpdateList() != null && courtEventCommitBean.getUpdateList().size() > 0) {
			List<CourtEvent> recordSavingObject = new ArrayList<>();
			for (int i = 0; i < courtEventCommitBean.getUpdateList().size(); i++) {
				final CourtEvent newCourtEvent = courtEventCommitBean.getUpdateList().get(i);
					newCourtEvent.setOffenderBookId(courtEventCommitBean.getUpdateList().get(i).getOffenderBookId());
					bookingDate=getPreInsertBookingDate(courtEventCommitBean.getUpdateList().get(i).getOffenderBookId());
					latestMovement=getLatestMovement(courtEventCommitBean.getUpdateList().get(i).getOffenderBookId());
					if(courtEventCommitBean.getUpdateList().get(i).getEventDate().before(bookingDate) || courtEventCommitBean.getUpdateList().get(i).getStartTime().before(latestMovement)) {
						newCourtEvent.setEventStatus("COMP");
					} else {
						newCourtEvent.setEventStatus("SCH");
					}
					if(courtEventCommitBean.getUpdateList().get(i).getHoldFlag()=="true") {
						newCourtEvent.setHoldFlag("Y");
					} else if(courtEventCommitBean.getUpdateList().get(i).getHoldFlag()=="false") {
						newCourtEvent.setHoldFlag("N");
					}
					if(courtEventCommitBean.getUpdateList().get(i).getOrderRequestedFlag()=="true") {
						newCourtEvent.setOrderRequestedFlag("Y");
					} else if(courtEventCommitBean.getUpdateList().get(i).getOrderRequestedFlag()=="false") {
						newCourtEvent.setOrderRequestedFlag("N");
					}
					if(courtEventCommitBean.getUpdateList().get(i).getOrderRequestedFlag()=="true") {
						newCourtEvent.setOrderRequestedFlag("Y");
					} else if(courtEventCommitBean.getUpdateList().get(i).getOrderRequestedFlag()=="false") {
						newCourtEvent.setOrderRequestedFlag("N");
					}
					recordSavingObject.clear();
					recordSavingObject.add(newCourtEvent);
					if(courtEventCommitBean.getUpdateList().get(i).getNextEventDate()!=null) {
						if(courtEventCommitBean.getUpdateList().get(i).getNextEventDate().equals(courtEventCommitBean.getUpdateList().get(i).getPreExistedEventDate())) {
							}else {
									if(!isSameEventExist(recordSavingObject.get(0))) {
										if(getActiveAgencyLocationDesc(recordSavingObject.get(0).getAgyLocId())==null) {
											return null;
										}
									}
									nextEventUpdated=insertNextEventRecord(recordSavingObject);
									if(nextEventUpdated!=null) {
										liReturn=1;
									}else {
										liReturn=0;
										}
									}
						}
					if(courtEventCommitBean.getSelectedCourtcase()!=null && recordSavingObject.get(0).getAgyLocId().equals(courtEventCommitBean.getSelectedCourtcase().getAgy_loc_id())){
						} else if(courtEventCommitBean.getSelectedCourtcase()!=null) {
							liReturn =updateAgyLocation(recordSavingObject);
							}
					if(recordSavingObject.get(0).getOutcomeReasonCode()!=null) {
						insertEventCharges=updateEventCharges(recordSavingObject);
						}
					liReturn = updateOrderCourtDate(recordSavingObject);
					if(liReturn==1) {
						liReturn = updateCourtEvent(recordSavingObject);
						}

					}
			}
		return courtEventCommitBean;
	}
	
	
	@Transactional
	public OffenderCaseCommitBean insertNewCaseRCE(OffenderCaseCommitBean courtCaseCommitBean) {
		int liReturn = 0;
		if (courtCaseCommitBean.getInsertList() != null && courtCaseCommitBean.getInsertList().size() > 0) {
			List<CourtCases> recordSavingObject = new ArrayList<>();
			if (courtCaseCommitBean.getInsertList().size() > 0) {
				for (CourtCases courtCase :courtCaseCommitBean.getInsertList()) {
					recordSavingObject = new ArrayList<>();
					final Long valueCaseSeq = getPreInsertCaseSeq(courtCase.getOffenderBookId());
					final Long valueCaseId = getPreInsertCaseSeq();
					courtCase.setCase_Seq(Long.valueOf(valueCaseSeq));
					courtCase.setcaseId(valueCaseId);
					recordSavingObject.add(courtCase);
					liReturn = insertNewCourtCase(recordSavingObject);
					if(liReturn==1) {
						if(courtCase.getCourtEventList()!=null) {
							for (CourtEvent courtEvent : courtCase.getCourtEventList()) {
								OffenderCourtEventCommitBean courtEventCommitBean = new OffenderCourtEventCommitBean();
								List<CourtEvent> eventInsertList = new ArrayList<CourtEvent>();
								List<CourtEvent> eventUpdateList = new ArrayList<CourtEvent>();
								if(courtEvent.getCommitFlag()=="u") {
									eventUpdateList.add(courtEvent);
								}else if(courtEvent.getCommitFlag().equals("i")) {
									courtEvent.setCaseId(valueCaseId);
									eventInsertList.add(courtEvent);
								}
								courtEventCommitBean.setInsertList(eventInsertList);
								courtEventCommitBean.setUpdateList(eventUpdateList);
								courtEventCommitBean.setSelectedCourtcase(courtCase);
								OffenderCourtEventCommitBean returnCourtEventCommitBean = insertNewEventREO(courtEventCommitBean);
								Long eventId = returnCourtEventCommitBean.getInsertList().get(0).getEventId();
								courtEvent.setEventId(eventId);
							}
						}

					}
				}
			}
		}
		if (courtCaseCommitBean.getUpdateList() != null && courtCaseCommitBean.getUpdateList().size() > 0) {
			liReturn = updateCourtCase(courtCaseCommitBean.getUpdateList());
			}
		return courtCaseCommitBean;
		}

	@Transactional
	@Override
	public SentenceCommitBean insertOffenderSentenceDetailsRSB(SentenceCommitBean offenderSentenceCommitBean) {
		int liReturn = 0;

		if (offenderSentenceCommitBean.getInsertList() != null && offenderSentenceCommitBean.getInsertList().size() > 0) {
			List<Sentences> recordSavingObject = new ArrayList<>();
			if (offenderSentenceCommitBean.getInsertList().size() > 0) {
				for (int i = 0; i < offenderSentenceCommitBean.getInsertList().size(); i++) {
					recordSavingObject = new ArrayList<>();
					final Long valueSentenceSeq = getPreInsertSentenceSeq(offenderSentenceCommitBean.getInsertList().get(i).getOffenderBookId());
					final Long valueLineSeq = getPreInsertLineSeq(offenderSentenceCommitBean.getInsertList().get(i).getOffenderBookId());
					final Sentences insertSentenceData = offenderSentenceCommitBean.getInsertList().get(i);
					insertSentenceData.setSentenceSeq(Long.valueOf(valueSentenceSeq));
					insertSentenceData.setLine(Long.valueOf(valueLineSeq));
					insertSentenceData.setSentenceLevel("IND");
					recordSavingObject.add(insertSentenceData);
					liReturn = insertOffenderSentenceRecord(recordSavingObject);
					
					if(liReturn==1) {
						Sentences sentences =  offenderSentenceCommitBean.getInsertList().get(i);
						sentences.setSentenceSeq(valueSentenceSeq);
						sentences = insertUpdateSentenceSubData(offenderSentenceCommitBean.getInsertList().get(i));
					}
				}
			}
		}
		if (offenderSentenceCommitBean.getUpdateList() != null && offenderSentenceCommitBean.getUpdateList().size() > 0) {
			liReturn = OffenderSentenceRecord(offenderSentenceCommitBean.getUpdateList());
			for(Sentences sentences: offenderSentenceCommitBean.getUpdateList()) {
				sentences = insertUpdateSentenceSubData(sentences);
			}
		}
		updateSentenceCalculation(offenderSentenceCommitBean.getInsertList(), offenderSentenceCommitBean.getUpdateList());
		return offenderSentenceCommitBean;
	}

	private Sentences insertUpdateSentenceSubData(Sentences sentences) {

		Long valueSentenceSeq = sentences.getSentenceSeq();
		if(sentences.getOffensesOnSentenceList()!=null) {
			for (OffensesOutcome offenseOnSent : sentences.getOffensesOnSentenceList()) {
				OffensesOutcomeCommitBean offenseOnSentCommitBean = new OffensesOutcomeCommitBean();
				List<OffensesOutcome> offensesInsertList = new ArrayList<OffensesOutcome>();
				if(offenseOnSent.getCommitFlag().equals("i")) {
					offenseOnSent.setSentenceSeq(valueSentenceSeq);
					offensesInsertList.add(offenseOnSent);
				}
				offenseOnSentCommitBean.setInsertList(offensesInsertList);
				int liReturn = insertOffensesOnSentencing(offenseOnSentCommitBean);
			}
		}

		if(sentences.getSentenceTermList()!=null) {
			for (Terms sentTerm : sentences.getSentenceTermList()) {
				SentenceTermCommitBean termCommitBean = new SentenceTermCommitBean();
				List<Terms> senTermInsertList = new ArrayList<Terms>();
				List<Terms> senTermUpdateList = new ArrayList<Terms>();
				if(sentTerm.getCommitFlag().equals("i")) {
					sentTerm.setSentenceSeq(valueSentenceSeq);
					senTermInsertList.add(sentTerm);
				} else {
					senTermUpdateList.add(sentTerm);
				}
				termCommitBean.setInsertList(senTermInsertList);
				termCommitBean.setUpdateList(senTermUpdateList);
				insertOffenderSentenceTermsRSTB(termCommitBean);
			}
		}
		return sentences;
	}
	
	@Transactional
	public SentenceTermCommitBean insertOffenderSentenceTermsRSTB(SentenceTermCommitBean offenderSentenceTermCommitBean) {
		if (offenderSentenceTermCommitBean.getInsertList() != null && offenderSentenceTermCommitBean.getInsertList().size() > 0) {
			List<Terms> recordSavingObject = new ArrayList<>();
			if (offenderSentenceTermCommitBean.getInsertList().size() > 0) {
				for (int i = 0; i < offenderSentenceTermCommitBean.getInsertList().size(); i++) {
					recordSavingObject = new ArrayList<>();
					final Integer valueTermSeq = getPreInsertTermSeq(offenderSentenceTermCommitBean.getInsertList().get(i).getOffenderBookId(),offenderSentenceTermCommitBean.getInsertList().get(i).getSentenceSeq());
					final Terms insertSentenceTerm = offenderSentenceTermCommitBean.getInsertList().get(i);
					insertSentenceTerm.setTermSeq(Integer.valueOf(valueTermSeq));
					recordSavingObject.add(insertSentenceTerm);
					insertOffenderSentenceTermRecord(recordSavingObject);
				}
			}
		}
		if (offenderSentenceTermCommitBean.getUpdateList() != null && offenderSentenceTermCommitBean.getUpdateList().size() > 0) {
			updateOffenderSentenceTerm(offenderSentenceTermCommitBean.getUpdateList());
		}
		return offenderSentenceTermCommitBean;
	}


}

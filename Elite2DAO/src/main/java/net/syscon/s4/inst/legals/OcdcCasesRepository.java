package net.syscon.s4.inst.legals;

import java.util.Date;
import java.util.List;

import net.syscon.s4.common.beans.LovList;
import net.syscon.s4.inst.legals.beans.BailStatus;
import net.syscon.s4.inst.legals.beans.BondType;
import net.syscon.s4.inst.legals.beans.CaseStatus;
import net.syscon.s4.inst.legals.beans.Category;
import net.syscon.s4.inst.legals.beans.Court;
import net.syscon.s4.inst.legals.beans.CourtCases;
import net.syscon.s4.inst.legals.beans.CourtEvent;
import net.syscon.s4.inst.legals.beans.HearingType;
import net.syscon.s4.inst.legals.beans.OffenderBailDetails;
import net.syscon.s4.inst.legals.beans.OffenderDetailsOffenses;
import net.syscon.s4.inst.legals.beans.OffenseResultCodes;
import net.syscon.s4.inst.legals.beans.OffenseType;
import net.syscon.s4.inst.legals.beans.OffensesOutcome;
import net.syscon.s4.inst.legals.beans.Outcome;
import net.syscon.s4.inst.legals.beans.Plea;
import net.syscon.s4.inst.legals.beans.SentenceDate;
import net.syscon.s4.inst.legals.beans.Sentences;
import net.syscon.s4.inst.legals.beans.Terms;
import net.syscon.s4.inst.legals.beans.Type;

public interface OcdcCasesRepository {
	
	List<CourtCases> searchCourtCases(CourtCases offenderCases);
	
	List<Court> populateCourtData();
	
	List<Type> populateTypeData();
	
	List<CourtEvent> searchCourtEvents(String caseId);
	
	List<HearingType> populateHearingType();
	
	List<Outcome> populateOutcomeData();
	
	List<OffensesOutcome> searchOffensesOutcomeData(CourtEvent courtEvent);
	
	List<OffenseType> populateOffenseType(String offenceCode);
	
	List<Plea> populatePleaData();
	
	List<OffenseResultCodes> populateResultData();

	List<Sentences> populateSentencesData(CourtCases offenderCases);

	List<Terms> populatetermsData(Sentences sentences);

	List<OffensesOutcome> populateOffensesData(Sentences sentence);
	
	Integer insertNewCase(List<CourtCases> insertList);

	Integer updateCourtCase(List<CourtCases> updatedList);

	List<CaseStatus> populateCaseStatus();

	List<CaseStatus> preInsertCasePrefixInfo(String caseType);

	Object getPreInsertCaseType();

	Object getPreInsertAgyLocation(String offenderBookId);

	Long getPreInsertCaseSeq(Long offenderBookId);

	Long getPreInsertCaseId();

	Integer insertNewCourtEvent(List<CourtEvent> insertList);

	Long getPreInsertEventId();

	CourtEvent insertNextEventRecord(List<CourtEvent> insertList);
	
	Integer updateAgyLocation(List<CourtEvent> insertList);
	
	Integer insertEventCharges(List<CourtEvent> insertList);

	Date getPreInsertBookingDate(Long offenderBookId);

	Date getLatestMovement(Long offenderBookId);

	Integer updateCourtEvent(List<CourtEvent> updateList);

	List<Category> populateSentencesCategory();

	OffensesOutcome updateEventCharges(List<CourtEvent> updateList);

	Integer updateOrderCourtDate(List<CourtEvent> updateList);

	List<Sentences> populateConsecutiveToLineData(Sentences sentence);

	List<Category> populateSentencesType(String category);

	List<SentenceDate> populateSentenceDate(Long caseId);

	List<Category> populateSentenceStatus();
	
	List<BailStatus> populateBailStatus();
	
	List<BondType> populateBondType();
	
	int[]  insertBailDetail(List<OffenderBailDetails> insertList);
	
	Integer updateBailDetails(List<OffenderBailDetails> updateRecord);
	
	List<OffenderDetailsOffenses> populateBailOfences(Integer bookingId);
	
	Integer updateBailBondDetails(OffenderBailDetails bailBond);
	
	List<OffenderBailDetails> getAllBailDetails(Integer bookId, Integer caseId);
	
	Integer insertOffenderSentenceDetails(List<Sentences> newSentenceRecord);
	
	Integer OffenderSentenceRecord(List<Sentences> newSentenceRecord);

	Long getPreInsertSentenceSeq(Long offenderBookId);

	Long getPreInsertLineSeq(Long offenderBookId);

	Integer insertOffenderSentenceTermRecord(List<Terms> newSentenceRecord);

	Integer getPreInsertTermSeq(Long offenderBookId, Long sentenceSeq);

	List<LovList> populateSentenceTermCode(String sentenceCalcType, String sentenceCategory);

	Integer updateOffenderSentenceTerm(List<Terms> updatedList);

	Integer insertOffensesOnSentence(List<OffensesOutcome> offensesList);

	Integer insertOffensesOnSentenceByQuery(List<OffensesOutcome> offensesList);
	
	public List<OffensesOutcome> getOffenderOffences(String offenderBookId);

	//Integer insertnewOffenceOutcome(List<OffensesOutcome> insertList);

	Integer updateOffenceOutcome(List<OffensesOutcome> updatedList);

	Boolean isOffenceExist(OffensesOutcome offensesOutcome);

	String fetchStatuteCode(String offenceCode);
	
	Integer insertNewOffenceOutcome(List<OffensesOutcome> insertList);
	
	Integer updateOrderCourtDate(CourtEvent courtEvent);

	Boolean isSameEventExist(CourtEvent courtEvent);

	String getActiveAgencyLocationDesc(String agyLoc);
	
	CourtEvent fetchLatestCourtevent(Long courtCaseId);
	
	List<OffensesOutcome> insertNewOffenceOutcomeROI(List<OffensesOutcome> insertList);
	
	List<OffensesOutcome> getLegalsDetails(String offenderBookId);
	
}

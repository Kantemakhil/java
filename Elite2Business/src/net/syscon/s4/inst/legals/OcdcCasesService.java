package net.syscon.s4.inst.legals;

import java.util.Date;
import java.util.List;

import net.syscon.s4.common.beans.LovList;
import net.syscon.s4.common.beans.OffenderCaseCommitBean;
import net.syscon.s4.common.beans.SentenceCommitBean;
import net.syscon.s4.common.beans.SentenceTermCommitBean;
import net.syscon.s4.inst.legals.beans.BailDetailsCommitBean;
import net.syscon.s4.inst.legals.beans.BailStatus;
import net.syscon.s4.inst.legals.beans.BondType;
import net.syscon.s4.inst.legals.beans.CaseStatus;
import net.syscon.s4.inst.legals.beans.Category;
import net.syscon.s4.inst.legals.beans.Court;
import net.syscon.s4.inst.legals.beans.CourtCases;
import net.syscon.s4.inst.legals.beans.CourtEvent;
import net.syscon.s4.inst.legals.beans.HearingType;
import net.syscon.s4.inst.legals.beans.IdentifierCommitBean;
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
import net.syscon.s4.inst.legals.beans.SentenceDate;
import net.syscon.s4.inst.legals.beans.Sentences;
import net.syscon.s4.inst.legals.beans.Terms;
import net.syscon.s4.inst.legals.beans.Type;

public interface OcdcCasesService {

	List<CourtCases> searchCourtCases(CourtCases offenderCases);
	
	List<Court> populateCourtData();
	
	List<Type> populateTypeData();
	
	List<CourtEvent> searchCourtEventData(String caseId);
	
	List<HearingType> populateHearingTypeData();
	
	List<Outcome> populateOutcomeData();
	
	List<OffensesOutcome> searchOffensesOutcomeData(CourtEvent courtEvent);
	
	List<OffenseType> populateOffenseType(String offenceCode);
	
	List<Plea> populatePleaData();
	
	List<OffenseResultCodes> populateResultData();
	
	List<Sentences> populateSentencesData(CourtCases offenderCases);

	List<Terms> populateTermsData(Sentences sentences);

	List<OffensesOutcome> populateOffensesData(Sentences sentence);

	Integer insertNewCase(OffenderCaseCommitBean courtCaseCommit);

	List<CaseStatus> populateCaseStatus();

	Object getPreInsertCaseType();
	
	List<CaseStatus> preInsertCasePrefixInfo(String caseType);

	Object getPreInsertAgyLocation(String offenderBookId);

	Integer insertNewEvent(OffenderCourtEventCommitBean courtEventCommitBean);

	List<Category> populateSentencesCategory();

	List<Sentences> populateConsecutiveToLineData(Sentences sentences);

	List<Category> populateSentencesType(String sentenceCategory);

	List<SentenceDate> populateSentenceDate(Long caseId);

	List<Category> populateSentenceStatus();
	
	List<BailStatus> populateBailStatus();

	List<BondType> populateBondType();	
	
	List<OffenderDetailsOffenses> populateBailOfences(Integer bookingId);
	
	Integer  updateBailDetailsData(BailDetailsCommitBean bailDetailBeanCommit);
	
	Integer updateBailBondDetails(OffenderBailDetails bailBond);
	
	public List<OffensesOutcome> getOffenderOffences(String offenderBookId);
	
	List<OffenderBailDetails> getAllBailDetails(Integer bookId, Integer caseId);	

	Integer insertOffenderSentenceDetails(SentenceCommitBean offenderSentenceCommit);

	Integer insertOffenderSentenceTerms(SentenceTermCommitBean offenderSentenceTermCommit);

	List<LovList> populateSentenceTermCode(String sentenceCalcType, String sentenceCategory);

	Integer insertOffensesOnSentencing(OffensesOutcomeCommitBean offensesOnSentenceCommitBean);
	
	Boolean isOffenceExist(OffensesOutcome offensesOutcome);
	
	Integer insertUpdateOffenseData(OffenceOutcomeCommitBean offenceOutcomeCommitBean);	

	Integer updateOrderDate(CourtEvent courtEvent);	

	Integer commitCases(List<CourtCases> courtCaseList);
	
	CourtEvent fetchLatestCourtevent(Long courtCaseId);
	
	OffenderCaseCommitBean insertNewCaseRCE(OffenderCaseCommitBean courtCaseCommitBean);
	
	OffenceOutcomeCommitBean  insertUpdateOffenseDataROI( OffenceOutcomeCommitBean OffenceCommitBean);
	
	OffenderCourtEventCommitBean insertNewEventREO(OffenderCourtEventCommitBean courtEventCommitBean) ;

	SentenceCommitBean insertOffenderSentenceDetailsRSB(SentenceCommitBean offenderSentenceCommitBean);
}

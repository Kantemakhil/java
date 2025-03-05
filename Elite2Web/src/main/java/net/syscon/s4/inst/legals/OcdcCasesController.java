package net.syscon.s4.inst.legals;


import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import net.syscon.s4.common.EliteController;
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
import net.syscon.s4.inst.legals.beans.OffenceOutcomeCommitBean;
import net.syscon.s4.inst.legals.beans.OffenderBailDetails;
import net.syscon.s4.inst.legals.beans.OffenderCourtEventCommitBean;
import net.syscon.s4.inst.legals.beans.OffenderDetailsOffenses;
import net.syscon.s4.inst.legals.beans.OffenseType;
import net.syscon.s4.inst.legals.beans.OffensesOutcome;
import net.syscon.s4.inst.legals.beans.OffensesOutcomeCommitBean;
import net.syscon.s4.inst.legals.beans.Outcome;
import net.syscon.s4.inst.legals.beans.Plea;
import net.syscon.s4.inst.legals.beans.SentenceDate;
import net.syscon.s4.inst.legals.beans.Sentences;
import net.syscon.s4.inst.legals.beans.Terms;
import net.syscon.s4.inst.legals.beans.Type;


@EliteController
public class OcdcCasesController {
	
	@Autowired
	private OcdcCasesService ocdcCasesService;
	
	private static Logger logger = LogManager.getLogger(OcdcCasesController.class.getName());
	
	/**
	 * 
	 * @param offenderCases
	 * @return
	 * To search court cases based on selected offender
	 */
	@RequestMapping(value = "/ocdccase/searchCourtCases", method = RequestMethod.POST)
	public List<CourtCases> searchCourtCases(@RequestBody final CourtCases offenderCases) {
		List<CourtCases> searchResult = new ArrayList<CourtCases>();
		try {			
				searchResult = ocdcCasesService.searchCourtCases(offenderCases);	
			}
			
		catch (Exception e) {
			logger.error("searchCourtCases", e);
		}
		return searchResult;
	}
	
	/**
	 * To populate agency location description based on agency code.
	 * @return
	 */
	@RequestMapping(value = "/ocdccase/populateCourtData", method = RequestMethod.GET)
	public List<Court> populateCourtData() {
		List<Court> courtList = new ArrayList<Court>();
		try {			
			courtList = ocdcCasesService.populateCourtData();
		} catch (Exception e) {
			logger.error("populateCourtData", e);
		}
		return courtList;
	}
	
	@RequestMapping(value = "/ocdccase/populateTypeData", method = RequestMethod.GET)
	public List<Type> populateTypeData() {
		List<Type> typeList = new ArrayList<Type>();
		try {			
			typeList = ocdcCasesService.populateTypeData();
			}
		catch (Exception e) {
			logger.error("populateTypeData", e);
		}
		return typeList;
	}
	
	/**
	 * To populate case status description based on the status code.
	 * @return
	 */
	@RequestMapping(value = "/ocdccase/populateCaseStatus", method = RequestMethod.GET)
	public List<CaseStatus> populateCaseStatus() {
		List<CaseStatus> caseStatusList = new ArrayList<CaseStatus>();
		try {			
			caseStatusList = ocdcCasesService.populateCaseStatus();
			}
		catch (Exception e) {
			logger.error("populateCaseStatus", e);
		}
		return caseStatusList;
	}
	
	
	/**
	 * To search court event data based on the selected case Id from court case grid.
	 * @param caseId
	 * @return
	 */
	@RequestMapping(value = "/ocdccase/searchCourtEvents", method = RequestMethod.GET)
	public List<CourtEvent> searchCourtEvents(
			@RequestParam final String caseId) {
		List<CourtEvent> courtEventData = new ArrayList<CourtEvent>();
		try {						
			courtEventData = ocdcCasesService.searchCourtEventData(caseId);
		} catch (Exception e) {
			logger.error("searchCourtEvents", e);
		}
		return courtEventData;
	}
	
	/**
	 * To populate hearing type description based on hearing code.
	 * @return
	 */
	@RequestMapping(value = "/ocdccase/populateHearingTypeData", method = RequestMethod.GET)
	public List<HearingType> populateHearingTypeData() {
		List<HearingType> hearingTypeList = new ArrayList<HearingType>();
		try {			
			hearingTypeList = ocdcCasesService.populateHearingTypeData();
			}
		catch (Exception e) {
			logger.error("populateHearingTypeData", e);
		}
		return hearingTypeList;
	}
	
	/**
	 * To populate outcome lov description on court event grid based on the codes.
	 * @return a list of the Outcome {@link Outcome} form the DB
	 */
	@RequestMapping(value = "/ocdccase/populateOutcomeData", method = RequestMethod.GET)
	public List<Outcome> populateOutcomeData() {
	
		List<Outcome> outcomeList = new ArrayList<Outcome>();
		try {
			outcomeList = ocdcCasesService.populateOutcomeData();
			} catch (Exception e) {
			logger.error("populateOutcomeData", e);
		}
		return outcomeList;
	}
	
	/**
	 * To populate offenses/Outcome grid data
	 * @param courtEvent
	 * @return
	 */
	@RequestMapping(value = "/ocdccase/searchOffensesOutcome", method = RequestMethod.POST)
	public List<OffensesOutcome> searchOffensesOutcome(
			@RequestBody final CourtEvent courtEvent) {
		List<OffensesOutcome> offensesDataList = new ArrayList<OffensesOutcome>();
		try {
			offensesDataList = ocdcCasesService.searchOffensesOutcomeData(courtEvent);
			}catch (Exception e) {
				logger.error("searchOffensesOutcome", e);
				}
		return offensesDataList;
		}
	
	/**
	 * To populate data for offense type LOv and will also provide the description of offense
	 * type corresponding to the value
	 * @return
	 */
	@RequestMapping(value = "/ocdccase/populateOffenseType", method = RequestMethod.GET)
	public List<OffenseType> populateOffenseType(@RequestParam String offenceCode) {
		List<OffenseType> offensesTypeList = new ArrayList<OffenseType>();
		try {			
			offensesTypeList = ocdcCasesService.populateOffenseType(offenceCode);
			} catch (Exception e) {
				logger.error("populateOffenseType", e);
				}
		return offensesTypeList;
		}
	
	/**
	 * To populate data for Plea LOv and will also provide the description of offense
	 * type corresponding to the value
	 * @return
	 */
	@RequestMapping(value = "/ocdccase/populatePleaData", method = RequestMethod.GET)
	public List<Plea> populatePleaData() {
		List<Plea> pleaList = new ArrayList<Plea>();
		try {			
			pleaList = ocdcCasesService.populatePleaData();
			}
			catch (Exception e) {
			logger.error("populatePleaData", e);
		}
		return pleaList;
	}
	
	
	@RequestMapping(value = "/ocdccase/populateSentencesData", method = RequestMethod.POST)
	public List<Sentences> populateSentenceData(@RequestBody final CourtCases courtCaseBean) {
		
		List<Sentences> sentencesDataList =  new ArrayList<Sentences>();
		
		try {
			 sentencesDataList = ocdcCasesService.populateSentencesData(courtCaseBean);
			}
			catch (Exception e) {
			logger.error("PleaExecuteQuery", e);
		}
		return sentencesDataList;
		
	}
	
	@RequestMapping(value = "/ocdccase/populateSentencesCategory", method = RequestMethod.GET)
	public List<Category> populateSentencesCategory() {
		
		List<Category> sentencesCategoryList =  new ArrayList<Category>();
		
		try {
			sentencesCategoryList = ocdcCasesService.populateSentencesCategory();
			}
			catch (Exception e) {
			logger.error("populateSentencesCategory", e);
		}
		return sentencesCategoryList;
		
	}
	
	@RequestMapping(value = "/ocdccase/populateSentencesType", method = RequestMethod.GET)
	public List<Category> populateSentencesType(@RequestParam String category) {
		List<Category> sentencesTypeList =  new ArrayList<Category>();
		
		try {
			sentencesTypeList = ocdcCasesService.populateSentencesType(category);
			}
			catch (Exception e) {
			logger.error("populateSentencesType", e);
		}
		return sentencesTypeList;
		
	}
	
	@RequestMapping(value = "/ocdccase/populateSentenceDate", method = RequestMethod.GET)
	public List<SentenceDate> populateSentenceDate(@RequestParam final Long caseId) {
		
		List<SentenceDate> sentencesDateList =  new ArrayList<SentenceDate>();
		
		try {
			sentencesDateList = ocdcCasesService.populateSentenceDate(caseId);
			}
			catch (Exception e) {
			logger.error("populateSentenceDate", e);
		}
		return sentencesDateList;
	}
	
	@RequestMapping(value = "/ocdccase/populateConsecutiveToLineDate", method = RequestMethod.GET)
	public List<Sentences> populateConsecutiveToLineDate(@RequestParam final Long offenderBookId,
			@RequestParam final Long sentenceSeq) {

		List<Sentences> consecutiveToLineList = new ArrayList<Sentences>();
		Sentences sentence = new Sentences();
		sentence.setOffenderBookId(offenderBookId);
		sentence.setSentenceSeq(sentenceSeq);
		try {
			consecutiveToLineList = ocdcCasesService.populateConsecutiveToLineData(sentence);
		} catch (Exception e) {
			logger.error("populateConsecutiveToLineDate", e);
		}
		return consecutiveToLineList;
	}
	
	@RequestMapping(value = "/ocdccase/populateSentenceStatus", method = RequestMethod.GET)
	public List<Category> populateSentenceStatus() {
		
		List<Category> sentenceStatusList =  new ArrayList<Category>();
		
		try {
			sentenceStatusList = ocdcCasesService.populateSentenceStatus();
			}
			catch (Exception e) {
			logger.error("populateSentencesCategory", e);
		}
		return sentenceStatusList;
		
	}
		
	
	@RequestMapping(value = "/ocdccase/populateTermsData", method = RequestMethod.POST)
	public List<Terms> populateTermsData(@RequestBody final Sentences sentences) {
			
			List<Terms> termsDataList =  new ArrayList<Terms>();
			
			try {
				termsDataList = ocdcCasesService.populateTermsData(sentences);
				}
				catch (Exception e) {
				logger.error("TERM CONTROLLER EXCEPTION", e);
			}
			return termsDataList;
			
		}
	
	@RequestMapping(value = "/ocdccase/populateSentenceTermCodeLov", method = RequestMethod.GET)
	public List<LovList> populateSentenceTermCode(@RequestParam final String sentenceCalcType,@RequestParam final String sentenceCategory) {
			List<LovList> result = new ArrayList<LovList>();
		try {	
			
			result = ocdcCasesService.populateSentenceTermCode(sentenceCalcType,sentenceCategory);
			}
		catch (Exception e) {
			logger.error("populateSentenceTermCode", e);
		}
		return result;
	}
	
	
	@RequestMapping(value = "/ocdccase/populateOffenses", method = RequestMethod.POST)
	public List<OffensesOutcome> populateOffensesData(@RequestBody final Sentences sentence) {
				
		List<OffensesOutcome> offenseDiscriptionList = new ArrayList<OffensesOutcome>();
			try {
				offenseDiscriptionList = ocdcCasesService.populateOffensesData(sentence);
				}
			catch (Exception e) {
				logger.error("OFFENCES ON SENTENCE EXCEPTION", e);
			}
			return offenseDiscriptionList;
		}
	
	/**
	 * To Insert the new court case
	 * @param courtCaseCommit
	 * @return
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/ocdccase/newcourtCase", method = RequestMethod.POST)
	public @ResponseBody Integer newcourtCase(@RequestBody OffenderCaseCommitBean courtCaseCommit) {
		int liReturn = 0;
			try {
				liReturn = ocdcCasesService.insertNewCase(courtCaseCommit);
				
			}catch(Exception e) {
				logger.error("newcourtCase", e);
			}
		return liReturn;
	}
	
	/**
	 * To fetch the pre-inserted case type at the time of new case insertion  
	 * @return
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdccase/getPreInsertCaseType", method = RequestMethod.GET)
	public @ResponseBody Object getPreInsertCaseType() {
		Object caseTypeCode = null;
		try {
			caseTypeCode = ocdcCasesService.getPreInsertCaseType();
		} catch (Exception e) {
			logger.error("getPreInsertCaseType", e);
		}
		return caseTypeCode;
	}
	
	/**
	 * To fetch the pre-inserted case prefix info at the time of new case insertion  
	 * @return
	 */
	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdccase/preInsertCasePrefixInfo", method = RequestMethod.GET)
	public List<CaseStatus> preInsertCasePrefixInfo(@RequestParam String caseType) {
		List<CaseStatus> casePrefixinfo = new ArrayList<CaseStatus>();
		try {			
			casePrefixinfo = ocdcCasesService.preInsertCasePrefixInfo(caseType);
			}
		catch (Exception e) {
			logger.error("preInsertCasePrefixInfo", e);
		}
		return casePrefixinfo;
	}
	
	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdccase/getPreInsertAgyLocation", method = RequestMethod.GET)
	public @ResponseBody Object getPreInsertAgyLocation(@RequestParam String offenderBookId) {
		Object agyLocation = null;
		try {
			agyLocation = ocdcCasesService.getPreInsertAgyLocation(offenderBookId);
		} catch (Exception e) {
			logger.error("getPreInsertAgyLocation", e);
		}
		return agyLocation;
	}
	
	 /***
	  * insert,update court events
	  * @param courtEventCommitBean
	  * @return
	  */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/ocdccase/newCourtEvent", method = RequestMethod.POST)
	public @ResponseBody Integer newCourtEvent(@RequestBody OffenderCourtEventCommitBean courtEventCommitBean) {
		int liReturn = 0;
			try {
				liReturn = ocdcCasesService.insertNewEvent(courtEventCommitBean);
				
			}catch(Exception e) {
				logger.error("newCourtEvent:", e);
			}
		return liReturn;
	}
	// =========================== Bail Details
	// ========================================

	/***
	 * method written for BailDetails fetching Types in Bail Status
	 */
	@RequestMapping(value = "/ocdccase/bailStatus", method = RequestMethod.GET)
	public List<BailStatus> populateBailStatus() {
		List<BailStatus> Result = new ArrayList<BailStatus>();
		try {
			Result = ocdcCasesService.populateBailStatus();
			
		} catch (Exception e) {
			logger.error("populateBailStatus", e);
		}
		return Result;
	}

	/***
	 * method written for BailDetails fetching Types in Bail Status
	 */
	@RequestMapping(value = "/ocdccase/bondType", method = RequestMethod.GET)
	public List<BondType> populateBondType() {
		List<BondType> Result = new ArrayList<BondType>();
		try {
			Result = ocdcCasesService.populateBondType();
			
		} catch (Exception e) {
			logger.error("populateBondType", e);
		}
		return Result;
	}

	/***
	 * method written for BailDetails fetching OffenderOffenses in Bail Status
	 */
	@RequestMapping(value = "/ocdccase/offenses", method = RequestMethod.GET)
	public List<OffenderDetailsOffenses> populateBailOfences(@RequestParam Integer caseId) {
		List<OffenderDetailsOffenses> Result = new ArrayList<OffenderDetailsOffenses>();
		try {
			Result = ocdcCasesService.populateBailOfences(caseId);
			if(Result.size() > 0) {
				for(int i=0; i < Result.size(); i++) {
					Result.get(i).setSeqNo(i+1);
				}
			}
			
		} catch (Exception e) {
			logger.error("populateBailOfences", e);
		}
		return Result;
	}
	
	/***
	 * insert update delete bail details
	 * @param bailDetailBeanCommit
	 * @return
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/ocdccase/insertDetails", method = RequestMethod.POST)
	public @ResponseBody Integer insertDetails(@RequestBody BailDetailsCommitBean bailDetailBeanCommit) {
		
		Integer result = 0;
		try {
			//final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			result = ocdcCasesService.updateBailDetailsData(bailDetailBeanCommit);
			 
		} catch (Exception e) {
			logger.error("insertDetails", e);
		}
		return result;
	}
	
	@RequestMapping(value = "/ocdccase/getOffenderOffences", method = RequestMethod.GET)
	public @ResponseBody Object getOffenderOffences(@RequestParam String offenderBookId) {
		List<OffensesOutcome> offences = null;
		try {
			offences = ocdcCasesService.getOffenderOffences(offenderBookId);
		} catch (Exception e) {
			logger.error("getOffenderOffences", e);
		}
		return offences;
	}
	
	
	
	/***
	 * function for insert,update Recored bail bond
	 *
	 * @param bondDetails {@link OffenderBailDetails}
	 * @return success/failure of the insert/update {@link Integer} for the matching passed data
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/ocdccase/updateBailBondDetails", method = RequestMethod.POST)
	public @ResponseBody Integer updateOffenderDetails(@RequestBody OffenderBailDetails bondDetails) {
		Integer result=0;
		try {
			final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			result = ocdcCasesService.updateBailBondDetails(bondDetails);
			 
		} catch (Exception e) {
			logger.error("updateOffenderDetails", e);
		}
		return result;
	}
	
	/**
	 *
	 * @param bookId {@link Integer}
	 * @return a list of the OffenderBailDetails {@link OffenderBailDetails} for the matched bookId
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdccase/getAllBailDetails", method = RequestMethod.GET)
	public List<OffenderBailDetails> getAllBailDetails(@RequestParam("bookId") Integer bookId, @RequestParam("caseId") Integer caseId) {
		List<OffenderBailDetails> searchResult = new ArrayList<OffenderBailDetails>();
		try {
			searchResult = ocdcCasesService.getAllBailDetails(bookId, caseId);
			if(searchResult.size() > 0) {
				for(int i=0; i<searchResult.size(); i++ ) {
					searchResult.get(i).setSeqNo(i+1);
				}
			}
			
		}
		catch (Exception e) {
			logger.error("getAllBailDetails", e);
		}
		return searchResult;
	}
	
	/***
	 * insert update delete offender sentence details
	 * @param offenderSentenceCommit
	 * @return
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/ocdccase/insertOffenderSentenceDetails", method = RequestMethod.POST)
	public @ResponseBody Integer insertOffenderSentenceDetails(@RequestBody SentenceCommitBean offenderSentenceCommit) {
		int liReturn = 0;
			try {
				liReturn = ocdcCasesService.insertOffenderSentenceDetails(offenderSentenceCommit);
				
			}catch(Exception e) {
				logger.error("insertOffenderSentenceDetails", e);
			}
		return liReturn;
	}
	
	/***
	 * insert update delete terms
	 * @param offenderSentenceTermCommit
	 * @return
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/ocdccase/insertOffenderSentenceTerms", method = RequestMethod.POST)
	public @ResponseBody Integer insertOffenderSentenceTerms(@RequestBody SentenceTermCommitBean offenderSentenceTermCommit) {
		int liReturn = 0;
			try {
				liReturn = ocdcCasesService.insertOffenderSentenceTerms(offenderSentenceTermCommit);
				
			}catch(Exception e) {
				logger.error("insertOffenderSentenceDetails", e);
			}
		return liReturn;
	}
	
	/***
	 * insert, update
	 * @param offensesOnSentenceCommitBean
	 * @return
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/ocdccase/insertOffensesOnSentencing", method = RequestMethod.POST)
	public @ResponseBody Integer insertOffensesOnSentencing(@RequestBody OffensesOutcomeCommitBean offensesOnSentenceCommitBean) {
		int liReturn = 0;
			try {
				liReturn = ocdcCasesService.insertOffensesOnSentencing(offensesOnSentenceCommitBean);
				
			}catch(Exception e) {
				logger.error("insertOffenderSentenceDetails", e);
			}
		return liReturn;
	}
	
	/***
	 * 
	 * @param offenceOutcomeCommitBean
	 * @return
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/ocdccase/insertUpdateOffense", method = RequestMethod.POST)
	public @ResponseBody Integer insertUpdateOffense(@RequestBody OffenceOutcomeCommitBean offenceOutcomeCommitBean ) {
		int liReturn = 0;
		try{
			liReturn = ocdcCasesService.insertUpdateOffenseData(offenceOutcomeCommitBean);
		}
		catch (Exception e){
			logger.error("insertUpdateOffenseData", e);
		}
		return liReturn;
	}
	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdccase/isOffenceExist", method = RequestMethod.POST)
	public @ResponseBody Boolean isOffenceExist(@RequestBody OffensesOutcome offensesOutcome) {
		boolean liReturn=false;
		try{
			liReturn = ocdcCasesService.isOffenceExist(offensesOutcome);
		}
		catch (Exception e){
			logger.error("isOffenceExist", e);
			}
		return liReturn;
	}	
	
	
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/ocdccase/updateOrderDate", method = RequestMethod.POST)
	public @ResponseBody Integer updateOrderDate(@RequestBody CourtEvent courtEvent) {
		int liReturn = 0;
		try{
			liReturn = ocdcCasesService.updateOrderDate(courtEvent);
		}
		catch (Exception e){
			logger.error("updateOrderDate:", e);
		}
		return liReturn;
	}
	
	
	/***
	 * function for insert,update Recored
	 *
	 * @param courtCaseList {@link CourtCases}
	 * @return success/failure of the insert/update {@link Integer} for the matching passed data
	 */

	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/ocdccase/commitCases", method = RequestMethod.POST)
	public @ResponseBody Integer commitCases(@RequestBody List<CourtCases> courtCaseList) {
		int liReturn = 0;
			try {
				liReturn = ocdcCasesService.commitCases(courtCaseList);
				
			}catch(Exception e) {
				logger.error("commitCases", e);
			}
		return liReturn;
	}

}

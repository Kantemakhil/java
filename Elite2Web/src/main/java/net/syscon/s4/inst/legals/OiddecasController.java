package net.syscon.s4.inst.legals;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import net.syscon.s4.common.EliteController;
import net.syscon.s4.common.beans.LovList;
import net.syscon.s4.inst.legals.beans.Condition;
import net.syscon.s4.inst.legals.beans.CourtCases;
import net.syscon.s4.inst.legals.beans.CourtEvent;
import net.syscon.s4.inst.legals.beans.OffensesOutcome;
import net.syscon.s4.inst.legals.beans.SentenceCalculation;
import net.syscon.s4.inst.legals.beans.Sentences;



@EliteController
public class OiddecasController {
	
	@Autowired
	private OiddecasService oiddecasService;
	
	private static Logger logger = LogManager.getLogger(OiddecasController.class.getName());
	
	/***
	 * fetch data for court Report from Order table
	 * @param paramBean
	 * @return
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/oiddecas/deleteChildToCourtCases", method = RequestMethod.POST)
	public Integer deleteChildToCourtCases(@RequestBody CourtCases deletedCourtCase) {
			Integer liReturn=0;
		try {
			liReturn = oiddecasService.deleteChildToCourtCases(deletedCourtCase);
			}
		catch (Exception e) {
			logger.error("deleteChildToCourtCases", e);
		}
		return liReturn;
	}
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/oiddecas/deleteOffenderCourtCases", method = RequestMethod.POST)
	public Integer deleteOffenderCourtCases(@RequestBody CourtCases deletedCourtCase) {
			Integer liReturn=0;
		try {
			String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			liReturn = oiddecasService.deleteOffenderCourtCases(deletedCourtCase,userName);
			}
		catch (Exception e) {
			logger.error("deleteOffenderCourtCases", e);
		}
		return liReturn;
	}
	
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/oiddecas/deleteOffenderCourtEvent", method = RequestMethod.POST)
	public Integer deleteOffenderCourtEvent(@RequestBody CourtEvent deletedCourtEvent) {
			Integer liReturn=0;
		try {
			String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			liReturn = oiddecasService.deleteOffenderCourtEvent(deletedCourtEvent,userName);
			}
		catch (Exception e) {
			logger.error("deleteOffenderCourtEvent", e);
		}
		return liReturn;
	}
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oiddecas/isLinkedCaseExist", method = RequestMethod.GET)
	public Boolean isLinkedCaseExist(@RequestParam Integer offenderChargeId) {
			Boolean isExist=false;
		try {
			isExist = oiddecasService.isLinkedCaseExist(offenderChargeId);
			}
		catch (Exception e) {
			logger.error("isLinkedCaseExist", e);
		}
		return isExist;
	}
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oiddecas/isSentenceAttached", method = RequestMethod.POST)
	public Boolean isSentenceAttached(@RequestBody OffensesOutcome offenses) {
			Boolean isExist=false;
		try {
			isExist = oiddecasService.isSentenceAttached(offenses);
			}
		catch (Exception e) {
			logger.error("isSentenceAttached", e);
		}
		return isExist;
	}
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/oiddecas/deleteOffenderOffenses", method = RequestMethod.POST)
	public Integer deleteOffenderOffenses(@RequestBody OffensesOutcome offenses) {
			Integer offenseDeletedFlag=0;
		try {
			String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			offenseDeletedFlag = oiddecasService.deleteOffenderOffenses(offenses,userName);
			}
		catch (Exception e) {
			logger.error("deleteOffenderOffenses", e);
		}
		return offenseDeletedFlag;
	}
	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oiddecas/populateOffensesOnSentence", method = RequestMethod.POST)
	public List<OffensesOutcome> populateOffensesOnSentence(@RequestBody final Sentences sentence) {
					
			List<OffensesOutcome> offenseDiscriptionList = new ArrayList<OffensesOutcome>();
				try {
					offenseDiscriptionList = oiddecasService.populateOffensesOnSentence(sentence);
					}
				catch (Exception e) {
					logger.error("populateOffensesOnSentence", e);
				}
				return offenseDiscriptionList;
			}
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oiddecas/populateConditionsData", method = RequestMethod.POST)
	public List<Condition> populateConditionsData(@RequestBody final Sentences sentence) {
					
			List<Condition> conditionsData = new ArrayList<Condition>();
				try {
					conditionsData = oiddecasService.populateConditionsData(sentence);
					}
				catch (Exception e) {
					logger.error("populateConditionsData", e);
				}
				return conditionsData;
			}
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/oiddecas/deleteSentences", method = RequestMethod.POST)
	public Integer deleteSentences(@RequestBody Sentences sentence) {
			Integer sentenceDeletedFlag=0;
		try {
			String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			sentenceDeletedFlag = oiddecasService.deleteSentences(sentence,userName);
			}
		catch (Exception e) {
			logger.error("deleteSentences", e);
		}
		return sentenceDeletedFlag;
	}
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oiddecas/isConsecutiveSentenceExist", method = RequestMethod.POST)
	public Boolean isConsecutiveSentenceExist(@RequestBody Sentences sentence) {
			Boolean isExist=false;
		try {
			isExist = oiddecasService.isConsecutiveSentenceExist(sentence);
			}
		catch (Exception e) {
			logger.error("isConsecutiveSentenceExist", e);
		}
		return isExist;
	}
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/oiddecas/deleteConditionsRecords", method = RequestMethod.POST)
	public Integer deleteConditionsRecord(@RequestBody Condition conditionRecords) {
			Integer sentenceDeletedFlag=0;
		try {
			sentenceDeletedFlag = oiddecasService.deleteConditionsRecord(conditionRecords);
			}
		catch (Exception e) {
			logger.error("deleteConditionsRecord", e);
		}
		return sentenceDeletedFlag;
	}
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/oiddecas/okToDeleteRecord", method = RequestMethod.GET)
	public Integer okToDeleteRecord(@RequestParam Long keyCode,@RequestParam String tableName,@RequestParam String columnName,String excludeTable,String owner) {
			Integer deletedFlag=0;
		try {
			deletedFlag = oiddecasService.okToDeleteRecord(keyCode,tableName,columnName,excludeTable,owner);
			}
		catch (Exception e) {
			logger.error("deleteConditionsRecord", e);
		}
		return deletedFlag;
	}
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/oiddecas/deleteSentenceCharges", method = RequestMethod.POST)
	public Integer deleteSentenceCharges(@RequestBody OffensesOutcome sentenceCharges) {
			Integer deletedFlag=0;
		try {
			deletedFlag = oiddecasService.deleteSentenceCharges(sentenceCharges);
			}
		catch (Exception e) {
			logger.error("deleteSentenceCharges", e);
		}
		return deletedFlag;
	}
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/oiddecas/deleteChildToSentenceCharges", method = RequestMethod.POST)
	public Integer deleteChildToSentenceCharges(@RequestBody OffensesOutcome sentenceCharges) {
			Integer deletedFlag=0;
		try {
			deletedFlag = oiddecasService.deleteChildToSentenceCharges(sentenceCharges);
			}
		catch (Exception e) {
			logger.error("deleteChildToSentenceCharges", e);
		}
		return deletedFlag;
	}
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/oiddecas/deleteFineSentence", method = RequestMethod.POST)
	public Integer deleteFineSentence(@RequestBody OffensesOutcome sentenceCharges) {
			Integer deletedFlag=0;
		try {
			deletedFlag = oiddecasService.deleteFineSentence(sentenceCharges);
			}
		catch (Exception e) {
			logger.error("deleteFineSentence", e);
		}
		return deletedFlag;
	}
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/oiddecas/deleteSentencesProdc", method = RequestMethod.POST)
	public Integer deleteSentencesProdc(@RequestBody Sentences sentenceCharges) {
			Integer deletedFlag=0;
		try {
			String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			deletedFlag = oiddecasService.deleteSentencesProdc(sentenceCharges,userName);
			}
		catch (Exception e) {
			logger.error("deleteSentencesProdc", e);
		}
		return deletedFlag;
	}
	
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/oiddecas/deleteOffensesOnSentence", method = RequestMethod.POST)
	public Integer deleteOffensesOnSentence(@RequestBody OffensesOutcome sentenceCharges) {
			Integer deletedFlag=0;
		try {
			deletedFlag = oiddecasService.deleteOffensesOnSentence(sentenceCharges);
			}
		catch (Exception e) {
			logger.error("deleteOffensesOnSentence", e);
		}
		return deletedFlag;
	}
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/oiddecas/okToModifyRecord", method = RequestMethod.GET)
	public Integer okToModifyRecord(@RequestParam String keyString,@RequestParam String tableName,@RequestParam String columnName,String excludeTable,String owner) {
			Integer deletedFlag=0;
		try {
			deletedFlag = oiddecasService.okToModifyRecord(keyString,tableName,columnName,excludeTable,owner);
			}
		catch (Exception e) {
			logger.error("okToModifyRecord", e);
		}
		return deletedFlag;
	}
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oiddecas/isSentAdjustAttached", method = RequestMethod.POST)
	public Boolean isSentAdjustAttached(@RequestBody Sentences sentence) {
			Boolean isExist=false;
		try {
			isExist = oiddecasService.isSentAdjustAttached(sentence);
			}
		catch (Exception e) {
			logger.error("isSentAdjustAttached", e);
		}
		return isExist;
	}
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/oiddecas/deleteSentencesOnCharges", method = RequestMethod.POST)
	public Integer deleteSentencesOnCharges(@RequestBody OffensesOutcome sentenceCharges) {
			Integer deletedFlag=0;
		try {
			deletedFlag = oiddecasService.deleteSentencesOnCharges(sentenceCharges);
			}
		catch (Exception e) {
			logger.error("deleteSentencesOnCharges", e);
		}
		return deletedFlag;
	}
}
	
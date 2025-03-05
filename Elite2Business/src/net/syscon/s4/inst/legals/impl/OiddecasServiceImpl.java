package net.syscon.s4.inst.legals.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.inst.legals.OiddecasRepository;
import net.syscon.s4.inst.legals.OiddecasService;
import net.syscon.s4.inst.legals.beans.Condition;
import net.syscon.s4.inst.legals.beans.CourtCases;
import net.syscon.s4.inst.legals.beans.CourtEvent;
import net.syscon.s4.inst.legals.beans.OffensesOutcome;
import net.syscon.s4.inst.legals.beans.Sentences;


@Service
public class OiddecasServiceImpl implements OiddecasService {
	
	@Autowired
	OiddecasRepository oiddecasRepository;

	@Transactional
	public Integer deleteChildToCourtCases(CourtCases deletedCourtCase) {
		/*Integer childRecDeleteFlg = 0;
		Integer delCourtCase = 0;
		childRecDeleteFlg = deleteChildRec(deletedCourtCase);*/
		/*if(childRecDeleteFlg==1) {
			delCourtCase = oiddecasRepository.deleteOffenderCourtCases(deletedCourtCase);
		}*/
		return oiddecasRepository.deleteChildToCourtCases(deletedCourtCase);
		
	}
	
	/*@Transactional
	private Integer deleteChildRec(CourtCases deletedCourtCase) {
		return oiddecasRepository.deleteChildRec(deletedCourtCase);
	}*/
	
	
	@Transactional
	public Integer deleteOffenderCourtCases(CourtCases deletedCourtCase,String userName) {
		/*Integer childRecDeleteFlg = 0;
		Integer delCourtCase = 0;
		childRecDeleteFlg = deleteChildRec(deletedCourtCase);*/
		/*if(childRecDeleteFlg==1) {
			delCourtCase = oiddecasRepository.deleteOffenderCourtCases(deletedCourtCase);
		}*/
		return oiddecasRepository.deleteOffenderCourtCases(deletedCourtCase,userName);
		
	}
	
	@Transactional
	public Integer deleteOffenderCourtEvent(CourtEvent deletedCourtEvent,String userName) {
		Integer childRecDeleteFlg = 0;
		Integer delCourtCase = 0;
		childRecDeleteFlg = deleteEventChildRec(deletedCourtEvent);
		if(childRecDeleteFlg==1) {
			delCourtCase = oiddecasRepository.deleteOffenderCourtEvents(deletedCourtEvent,userName);
		}
		return delCourtCase;
		
	}
	
	@Transactional
	private Integer deleteEventChildRec(CourtEvent deletedCourtEvent) {
		return oiddecasRepository.deleteEventChildRec(deletedCourtEvent);
	}

	@Override
	public Boolean isLinkedCaseExist(Integer offenderChargeId) {
		return oiddecasRepository.isLinkedCaseExist(offenderChargeId);
	}

	@Override
	public Boolean isSentenceAttached(OffensesOutcome offenses) {
		return oiddecasRepository.isSentenceAttached(offenses);
	}

	@Override
	public Integer deleteOffenderOffenses(OffensesOutcome offenses,String userName) {
		Integer liReturn=0;
		final Integer deleteEventChargesFlag = deleteCourtEventCharges(offenses,userName);
		if(deleteEventChargesFlag==1) {
			liReturn = oiddecasRepository.deleteOffenderOffenses(offenses,userName);
		}
		return liReturn;
	}
	
	private Integer deleteCourtEventCharges(OffensesOutcome deletedOffenses, String userName) {
		return oiddecasRepository.deleteCourtEventCharges(deletedOffenses,userName);
	}
	
	@Override
	public List<OffensesOutcome> populateOffensesOnSentence(Sentences sentence) {
		List<OffensesOutcome> offensesDiscriptionData = new ArrayList<>();
		offensesDiscriptionData = oiddecasRepository.populateOffensesOnSentence(sentence);
		return offensesDiscriptionData;
	}
	
	@Override
	public List<Condition> populateConditionsData(Sentences sentence) {
		List<Condition> conditionsData = new ArrayList<>();
		conditionsData = oiddecasRepository.populateConditionsData(sentence);
		return conditionsData;
	}
	
	@Transactional
	public Integer deleteSentences(Sentences sentence,String userName) {
		return oiddecasRepository.deleteSentences(sentence,userName);
		}
	
	@Override
	public Boolean isConsecutiveSentenceExist(Sentences sentence) {
		return oiddecasRepository.isConsecutiveSentenceExist(sentence);
	}
	
	@Override
	public Integer deleteConditionsRecord(Condition conditionRecord) {
		return oiddecasRepository.deleteConditionsRecord(conditionRecord);
	}

	@Override
	public Integer okToDeleteRecord(Long keyCode,String tableName,String columnName, String excludeTable, String owner) {
		return oiddecasRepository.okToDeleteRecord(keyCode,tableName,columnName,excludeTable,owner);
	}

	@Override
	public Integer deleteSentenceCharges(OffensesOutcome sentenceCharges) {
		return oiddecasRepository.deleteSentenceCharges(sentenceCharges);
	}

	@Override
	public Integer deleteChildToSentenceCharges(OffensesOutcome sentenceCharges) {
		return oiddecasRepository.deleteChildToSentenceCharges(sentenceCharges);
	}

	@Override
	public Integer deleteFineSentence(OffensesOutcome sentenceCharges) {
		return oiddecasRepository.deleteFineSentence(sentenceCharges);
	}

	@Override
	public Integer deleteSentencesProdc(Sentences sentenceCharges,String userName) {
		return oiddecasRepository.deleteSentencesProdc(sentenceCharges,userName);
	}
	
	@Override
	public Integer deleteOffensesOnSentence(OffensesOutcome sentenceCharges) {
		return oiddecasRepository.deleteOffensesOnSentence(sentenceCharges);
	}

	@Override
	public Integer okToModifyRecord(String keyString, String tableName, String columnName, String excludeTable,
			String owner) {
		return oiddecasRepository.okToModifyRecord(keyString,tableName,columnName,excludeTable,owner);
	}

	@Override
	public Boolean isSentAdjustAttached(Sentences sentence) {
		return oiddecasRepository.isSentAdjustAttached(sentence);
	}

	@Override
	public Integer deleteSentencesOnCharges(OffensesOutcome sentenceCharges) {
		return oiddecasRepository.deleteSentencesOnCharges(sentenceCharges);
	}
}
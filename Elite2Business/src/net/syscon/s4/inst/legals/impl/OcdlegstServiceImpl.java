package net.syscon.s4.inst.legals.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.beans.OffenderCaseCommitBean;
import net.syscon.s4.inst.legals.OcdlegstRepository;
import net.syscon.s4.inst.legals.OcdlegstService;
import net.syscon.s4.inst.legals.beans.Condition;
import net.syscon.s4.inst.legals.beans.Sentences;


@Service
public class OcdlegstServiceImpl implements OcdlegstService{
	
	@Autowired
	OcdlegstRepository OcdlegstRepository;

	@Transactional	
	public Integer updateSetenceReason( List<Sentences> sentence) {
		List<Sentences> recordSavingObject = new ArrayList<Sentences>();
		Integer liReturn = 0;
		for(Sentences sent : sentence){
			recordSavingObject = new ArrayList<>();	
			if(sent.getStatusUpdateReason().equals("EXPIRED")){
				sent.setStatus("I");
			}else{
				sent.setStatus("A");
			}
			recordSavingObject.add(sent);
		}
		liReturn = OcdlegstRepository.updateSetenceReason(sentence);
		return liReturn;
	}

	@Override
	public List<Condition> populateConditionData(Sentences sentence) {
		List<Condition> conditionList = new ArrayList<Condition>();
		conditionList = OcdlegstRepository.populateConditionData(sentence);
		return conditionList;
	}

	@Override
	public Integer updateConditionData(List<Condition> condition) {
		List<Condition> recordSavingObject = new ArrayList<Condition>();
		Integer liReturn = 0;
		for(Condition cond : condition){
			if(cond.getStatusUpdateReason().equals("EXPIRED")){
				cond.setConditionStatus("I");
			}else{
				cond.setConditionStatus("A");
			}
			recordSavingObject = new ArrayList<>();	
			recordSavingObject.add(cond);
		}
		liReturn = OcdlegstRepository.updateConditionData(condition);
		return liReturn;
	}

	@Override
	public List<Condition> getConditionLov() {
		return OcdlegstRepository.getConditionLov();
	}

	@Override
	public List<Condition> populateCaseStatus() {
		
		return OcdlegstRepository.populateCaseStatus();
	}

	@Override
	public List<Sentences> statusReasonUpdateLov(String sentenceCalcType) {
		return OcdlegstRepository.statusReasonUpdateLov(sentenceCalcType);
	}

	@Override
	public int updateCaseReason(OffenderCaseCommitBean courtCaseCommit) {
		//pValidate();
		return OcdlegstRepository.updateCaseReason(courtCaseCommit);
	}
	
	//privAET 

}

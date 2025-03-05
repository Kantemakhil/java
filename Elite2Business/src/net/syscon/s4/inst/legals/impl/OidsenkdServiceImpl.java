package net.syscon.s4.inst.legals.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.inst.legals.OidsenkdRepository;
import net.syscon.s4.inst.legals.OidsenkdService;
import net.syscon.s4.inst.legals.beans.SentenceAggregates;

@Service
public class OidsenkdServiceImpl implements OidsenkdService{
	
	@Autowired
	OidsenkdRepository oidsenkdRepository; 

	@Override
	public List<SentenceAggregates> populateAggregatesData(Long offenderBookId) {
		List<SentenceAggregates> sentenceAggregateList = new ArrayList<SentenceAggregates>();
		sentenceAggregateList =  oidsenkdRepository.populateAggregatesData(offenderBookId);
		calculateDaysToArdCrd(sentenceAggregateList);
		return sentenceAggregateList;
	}
	
	private List<SentenceAggregates> calculateDaysToArdCrd(List<SentenceAggregates> sentAggrList) {
		List<SentenceAggregates> sentenceAggregateList = new ArrayList<SentenceAggregates>();
		for(SentenceAggregates sentAggr : sentAggrList) {
			if(sentAggr.getArdCalculatedDate()!=null) {
				sentAggr.setArdCalculatedDate(sentAggr.getArdCalculatedDate());
			}else if(sentAggr.getCrdCalculatedDate()!=null) {
				sentAggr.setArdCalculatedDate(sentAggr.getCrdCalculatedDate());
			}else if(sentAggr.getPedCalculatedDate()!=null) {
				sentAggr.setArdCalculatedDate(sentAggr.getPedCalculatedDate());
			}
			Integer daysToArdCrd = oidsenkdRepository.calculateDaysBetween(sentAggr.getStartDate(),sentAggr.getArdCalculatedDate());
			Integer aggregateTerms = oidsenkdRepository.calculateDaysBetween(sentAggr.getStartDate(),sentAggr.getEndDate());
			sentAggr.setDaysToArdCrd(daysToArdCrd);
			sentAggr.setAggregateTerm(aggregateTerms);
		}
		return sentenceAggregateList;
	}

}

package net.syscon.s4.inst.legals.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.inst.legals.OidsenhyRepository;
import net.syscon.s4.inst.legals.OidsenhyService;
import net.syscon.s4.inst.legals.beans.CourtCases;
import net.syscon.s4.inst.legals.beans.KeyDates;
import net.syscon.s4.inst.legals.beans.SentenceHistory;
@Service
public class OidsenhyServiceImpl implements OidsenhyService{
	@Autowired
	OidsenhyRepository oidsenhyRepository;
	@Override
	public List<SentenceHistory> populateSentenceHistoryData(CourtCases offenderCases,String userId) {
		List<SentenceHistory> resultList = new ArrayList<SentenceHistory>();
		resultList = oidsenhyRepository.populateSentenceHistoryData(offenderCases,userId);
		for (SentenceHistory sentenceHistory : resultList) {
			if(null==sentenceHistory.getComment()||sentenceHistory.getComment().equals("null"))
			{
				sentenceHistory.setComment("");
			}
			else
			{
				sentenceHistory.setComment(sentenceHistory.getComment());
			}
		} 
		return resultList;
	
	}
	
	@Override
	public List<KeyDates> populateKeyDatesValues(List<KeyDates> keyDatesLablesList) {
		return oidsenhyRepository.populateKeyDatesValues(keyDatesLablesList);
	}
	

}

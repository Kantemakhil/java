package net.syscon.s4.inst.legals.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.inst.legals.OcusofncRepository;
import net.syscon.s4.inst.legals.OcusofncService;
import net.syscon.s4.inst.legals.beans.OffensesOutcome;
import net.syscon.s4.inst.legals.beans.Sentences;

@Service
public class OcusofncServiceImpl implements OcusofncService {
	
	@Autowired
	OcusofncRepository ocusofncRepository;

	@Override
	public List<OffensesOutcome> fetechOffensesdialogData(OffensesOutcome selectedSentenceData) {
		List<OffensesOutcome> offensesDailogList = new ArrayList<OffensesOutcome>();
		final String sentenceType= fetchSentenceType(selectedSentenceData.getCategory(), selectedSentenceData.getSentenceCalcType());
		selectedSentenceData.setSentenceType(sentenceType);
		offensesDailogList = ocusofncRepository.fetechOffensesdialogData(selectedSentenceData);
		return offensesDailogList;
		}
		
	private String  fetchSentenceType(String category, String sentenceCalcType) {
		return ocusofncRepository.fetchSentenceType(category, sentenceCalcType);
	}
}
	
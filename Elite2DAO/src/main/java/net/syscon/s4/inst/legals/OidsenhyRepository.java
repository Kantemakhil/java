package net.syscon.s4.inst.legals;

import java.util.List;

import net.syscon.s4.inst.legals.beans.CourtCases;
import net.syscon.s4.inst.legals.beans.KeyDates;
import net.syscon.s4.inst.legals.beans.SentenceHistory;

public interface OidsenhyRepository {
	 List<SentenceHistory> populateSentenceHistoryData(CourtCases offenderCases,String userId);
	 
	 List<KeyDates> populateKeyDatesValues(List<KeyDates> keyDatesLablesList);
}

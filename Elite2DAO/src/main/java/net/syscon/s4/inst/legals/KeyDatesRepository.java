package net.syscon.s4.inst.legals;

import java.util.List;
import java.util.Map;

import net.syscon.s4.inst.legals.beans.KeyDateValueMapping;
import net.syscon.s4.inst.legals.beans.KeyDates;
import net.syscon.s4.inst.legals.beans.SentenceKeyDates;

public interface KeyDatesRepository {

	List<KeyDates> fetchKeyDates();

	List<KeyDates> populateKeyDates(List<KeyDates> keyDatesLablesList);
	
	Boolean isCurfewRecordExist(Long offenderBookId);

	Integer createKeyDatesHistory(List<KeyDateValueMapping> updatedKeyDatesData);

	Integer fetchstaffId(String staffName);
	
	
	

}

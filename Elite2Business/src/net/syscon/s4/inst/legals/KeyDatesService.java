package net.syscon.s4.inst.legals;

import java.util.List;

import net.syscon.s4.inst.legals.beans.KeyDates;

public interface KeyDatesService {

	List<KeyDates> fetchKeyDates();
	
	List<KeyDates> populateKeyDates(List<KeyDates> keyDatesLablesList);

	Integer updateKeyDates(List<KeyDates> updatedKeyDatesList);
	
	List<KeyDates> populateKeyDatesApi(Long OffednerBookId);
	
	
	

}

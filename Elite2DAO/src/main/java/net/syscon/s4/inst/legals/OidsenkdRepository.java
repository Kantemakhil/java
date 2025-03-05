package net.syscon.s4.inst.legals;

import java.util.Date;
import java.util.List;

import net.syscon.s4.inst.legals.beans.SentenceAggregates;

public interface OidsenkdRepository {
	
	List<SentenceAggregates> populateAggregatesData(Long offenderBookId);

	Integer calculateDaysBetween(Date startDate, Date endDate);
}

package net.syscon.s4.inst.legals;

import java.util.List;

import net.syscon.s4.inst.legals.beans.SentenceAggregates;

public interface OidsenkdService {

	List<SentenceAggregates> populateAggregatesData(Long offenderBookId);
}

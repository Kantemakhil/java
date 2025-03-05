package net.syscon.s4.triggers;

import java.util.List;

import net.syscon.s4.inst.legals.beans.OffenderSentConditions;


public interface OffenderSentConditionsTwfService {
	Integer offenderSentConditionsTwfTgr(List<OffenderSentConditions> offenderSentConditionsList);
}

package net.syscon.s4.triggers;

import java.util.List;

import net.syscon.s4.im.beans.OffenderSentConditions;

public interface OffenderSentConditionsT1Service {
	Integer offenderSentConditionsT1Trigger(List<OffenderSentConditions> offenderSentConditionsList);
}

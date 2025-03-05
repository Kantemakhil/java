package net.syscon.s4.triggers;

import net.syscon.s4.cm.casemanagement.maintenance.beans.EventMeasureOutcomes;

public interface EventMeasureOutcomesTjnService {

	Integer eventMeasureOutcomesTjnTrigger(EventMeasureOutcomes newObj, EventMeasureOutcomes oldObj, String operation);
}

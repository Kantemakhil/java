package net.syscon.s4.triggers;

import net.syscon.s4.cm.casemanagement.maintenance.beans.EventMeasures;

public interface EventMeasuresTjnService {

	Integer eventMeasuresTjnTrigger(EventMeasures newObj, EventMeasures oldObj, String operation);

}

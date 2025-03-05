package net.syscon.s4.triggers;

import net.syscon.s4.cm.casemanagement.maintenance.beans.EventMeasures;

public interface EventMeasuresTjnRepository {
	
	Integer eventMeasuresTjnInsert(EventMeasures newObj);
	
	Integer eventMeasuresTjnUpdate(EventMeasures oldObj);
	
	Integer eventMeasuresTjnDelete(EventMeasures oldObj);

}

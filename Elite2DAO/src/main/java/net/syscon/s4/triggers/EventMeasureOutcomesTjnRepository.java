package net.syscon.s4.triggers;

import net.syscon.s4.cm.casemanagement.maintenance.beans.EventMeasureOutcomes;

public interface EventMeasureOutcomesTjnRepository {

	Integer eventMeasuresOutcomesTjnInsert(EventMeasureOutcomes newObj);

	Integer eventMeasuresOutcomesTjnUpdate(EventMeasureOutcomes oldObj);

	Integer eventMeasuresOutcomesTjnDelete(EventMeasureOutcomes oldObj);

}

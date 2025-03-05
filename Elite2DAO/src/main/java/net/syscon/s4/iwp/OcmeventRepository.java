package net.syscon.s4.iwp;

import java.util.List;

import net.syscon.s4.cm.casemanagement.maintenance.beans.EventMeasureOutcomes;
import net.syscon.s4.cm.casemanagement.maintenance.beans.EventMeasures;
import net.syscon.s4.common.beans.ReferenceCodes;

/**
 * Interface OcmeventRepository
 */
public interface OcmeventRepository {
	Integer scheduleInsertEventMeasures(List<EventMeasures> lstEventMeasures);

	List<ReferenceCodes> rgTypeRecordGroup();

	long ocmeventPreInsert();

	List<ReferenceCodes> rgSubTypeRecordGroup(String eventType);

	Integer outcomeUpdateEventMeasureOutcomes(List<EventMeasureOutcomes> lstEventMeasureOutcomes);

	List<ReferenceCodes> rgOutcomeCodeRecordGroup();

	Integer outcomeDeleteEventMeasureOutcomes(List<EventMeasureOutcomes> lstEventMeasureOutcomes);

	Integer scheduleDeleteEventMeasures(List<EventMeasures> lstEventMeasures);

	List<EventMeasures> scheduleExecuteQuery();

	List<EventMeasureOutcomes> outcomeExecuteQuery(EventMeasureOutcomes objEventMeasureOutcomes);

	Integer outcomeInsertEventMeasureOutcomes(List<EventMeasureOutcomes> lstEventMeasureOutcomes);

	Integer scheduleUpdateEventMeasures(List<EventMeasures> lstEventMeasures);

	List<ReferenceCodes> rgStaticRecordGroup();

	EventMeasures getOldEventMeasure(Long eventMeasureId);

	EventMeasureOutcomes getOldEventMeasureOutcomes(Long eventMeasureId);

}

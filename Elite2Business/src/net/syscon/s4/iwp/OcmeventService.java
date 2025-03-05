package net.syscon.s4.iwp;

import net.syscon.s4.cm.casemanagement.maintenance.beans.EventMeasureOutcomes;
import net.syscon.s4.cm.casemanagement.maintenance.beans.EventMeasureOutcomesCommitBean;
import net.syscon.s4.cm.casemanagement.maintenance.beans.EventMeasures;
import net.syscon.s4.cm.casemanagement.maintenance.beans.EventMeasuresCommitBean;
import net.syscon.s4.common.beans.ReferenceCodes;
import java.util.List;

/**
 * Interface OcmeventService
 */
public interface OcmeventService {

	List<ReferenceCodes> rgTypeRecordGroup();

	Integer outcomeCommit(EventMeasureOutcomesCommitBean CommitBean);

	List<EventMeasures> scheduleExecuteQuery();

	Integer scheduleCommit(EventMeasuresCommitBean CommitBean);

	List<ReferenceCodes> rgSubTypeRecordGroup(String eventType);

	List<EventMeasureOutcomes> outcomeExecuteQuery(EventMeasureOutcomes objEventMeasureOutcomes);

	List<ReferenceCodes> rgOutcomeCodeRecordGroup();

	List<ReferenceCodes> rgStaticRecordGroup();

}

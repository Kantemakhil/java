package net.syscon.s4.workspace;

import java.util.List;

import net.syscon.s4.cm.teamsworkflow.beans.TagWorkflowBrowseQueue;
import net.syscon.s4.common.beans.ReferenceCodes;

/**
 * Interface OcdmworkRepository
 */
public interface OcdmworkRepository {
	List<ReferenceCodes> rgTypeRecordGroup();

	List<ReferenceCodes> rgCompletedRecordGroup();

	List<ReferenceCodes> rgSeverityRecordGroup();

	List<ReferenceCodes> rgSubtypeRecordGroup();

	List<TagWorkflowBrowseQueue> workExecuteQuery(TagWorkflowBrowseQueue object);

	Integer workUpdateTagWorkflowBrowseQueue(TagWorkflowBrowseQueue object);

	List<ReferenceCodes> memoPostQuery(ReferenceCodes object);

	List<ReferenceCodes> rgReasonRecordGroup();

	List<ReferenceCodes> rgWorkTypeRecordGroup();

	List<TagWorkflowBrowseQueue> memoExecuteQuery(TagWorkflowBrowseQueue searchRecord);

}

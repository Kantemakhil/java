package net.syscon.s4.workspace;

import java.util.List;

import net.syscon.s4.cm.teamsworkflow.beans.TagWorkflowBrowseQueue;
import net.syscon.s4.cm.teamsworkflow.beans.TagWorkflowBrowseQueueCommitBean;
import net.syscon.s4.common.beans.ReferenceCodes;

/**
 * Interface OcdmworkService
 */
public interface OcdmworkService {
	List<ReferenceCodes> rgTypeRecordGroup();

	Integer workCommit(TagWorkflowBrowseQueueCommitBean object);

	List<ReferenceCodes> rgCompletedRecordGroup();

	List<ReferenceCodes> rgSeverityRecordGroup();

	List<ReferenceCodes> rgSubtypeRecordGroup();

	List<TagWorkflowBrowseQueue> workExecuteQuery(TagWorkflowBrowseQueue object);

	List<ReferenceCodes> memoPostQuery(ReferenceCodes paramBean);

	Integer memoCommit(TagWorkflowBrowseQueueCommitBean object);

	List<ReferenceCodes> rgReasonRecordGroup();

	List<ReferenceCodes> rgWorkTypeRecordGroup();

	List<TagWorkflowBrowseQueue> memoExecuteQuery(TagWorkflowBrowseQueue searchBean);

}

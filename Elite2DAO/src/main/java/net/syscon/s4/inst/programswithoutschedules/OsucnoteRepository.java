package net.syscon.s4.inst.programswithoutschedules;
import java.util.List;

import net.syscon.s4.cm.teamsworkflow.beans.TagWorkflowBrowseQueue;
import net.syscon.s4.common.beans.ReferenceCodes;

/**
 * Interface OsucnoteRepository
 */
public interface OsucnoteRepository {
	
	List<ReferenceCodes> rgWorksRecordGroup(final String caseLoadType) ;
	
	String submitAdhocWorkflow(final TagWorkflowBrowseQueue newMemoModel);
	
	String getDisplayAuto(final String offenderBookId);

	String getSubString(final String offName, final String messageText);

	Integer getStaffId(final String userId);
	
}

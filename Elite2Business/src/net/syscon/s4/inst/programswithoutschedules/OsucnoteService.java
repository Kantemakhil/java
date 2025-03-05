package net.syscon.s4.inst.programswithoutschedules;
import java.util.List;

import net.syscon.s4.cm.teamsworkflow.beans.TagWorkflowBrowseQueue;
import net.syscon.s4.common.beans.ReferenceCodes;

/**
 * Interface OsucnoteService 
 */
public interface OsucnoteService  {
	
	List<ReferenceCodes> rgWorksRecordGroup(final String caseLoadType) ;
	
	List<TagWorkflowBrowseQueue> submitAdhocWorkflow(final TagWorkflowBrowseQueue newMemoModel);
	
	String getDisplayAuto(final String offenderBookId);
	
}

package net.syscon.s4.inst.programswithoutschedules;

import java.util.List;

import net.syscon.s4.cm.teamsworkflow.beans.Work;
import net.syscon.s4.im.beans.NewEmailCommitBean;

/**
 * Interface OsuemailService 
 */
public interface OsuemailService  {
	List<Work> rgWorksRecordGroup(String caseloadType) ;

	Integer createAdhocEmail(NewEmailCommitBean newEmailCommitBean);

	NewEmailCommitBean osuemailExecuteQuery(NewEmailCommitBean newEmailCommitBean);

	String getOffendersDetails(Integer offenderBookId);

}

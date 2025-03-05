package net.syscon.s4.inst.workflow.maintenance;

import java.util.List;

import net.syscon.s4.cm.teamsworkflow.beans.Work;
import net.syscon.s4.cm.teamsworkflow.beans.WorkCommitBean;

public interface OcmcnperSevice {

	Integer caseNotePermissionCommit(WorkCommitBean commitBean);

	List<Work> caseNotePermissionExecuteQuery(Work searchBean);

}

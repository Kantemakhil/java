package net.syscon.s4.inst.workflow.maintenance;

import java.util.List;

import net.syscon.s4.cm.teamsworkflow.beans.Work;

public interface OcmcnperRepository {

	Integer gettingCountFromCaseNotePermission(long workId, Integer roleId);

	Integer caseNotePermissionInseting(Work updateList);

	Integer caseNotePermissionUpdating(Work updateList);

	List<Work> caseNotePermissionExecuteQuery(Work searchBean);

}

package net.syscon.s4.sa.admin;

import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.WorkFlowFolders;
import net.syscon.s4.common.beans.WorkflowScreens;
import net.syscon.s4.im.beans.OmsModules;
import net.syscon.s4.sa.admin.beans.WorkflowFoldersCommitBean;
import net.syscon.s4.sa.admin.beans.WorkflowScreenCommitBean;

/**
 * Interface OumwmenuService
 */
public interface OumwmenuService {
	List<ReferenceCodes> rgMenusNameRecordGroup();

	List<OmsModules> rgScreensModuleNameRecordGroup();

	Integer wfScreensCommit(WorkflowScreenCommitBean commitBean);

	Integer wfFoldersCommit(WorkflowFoldersCommitBean commitBean);

	List<WorkFlowFolders> wfFoldersExecuteQuery(WorkFlowFolders obj);

	List<WorkflowScreens> wfScreensExecuteQuery(WorkflowScreens obj);

	List<ReferenceCodes> rgCaseloadTypeRecordGroup();

	ReferenceCodes wfFoldersPostQuery(WorkFlowFolders paramBean);

}

package net.syscon.s4.sa.admin;

import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.WorkFlowFolders;
import net.syscon.s4.common.beans.WorkflowScreens;
import net.syscon.s4.im.beans.OmsModules;

/**
 * Interface OumwmenuRepository
 */
public interface OumwmenuRepository {

	List<ReferenceCodes> rgMenusNameRecordGroup();

	List<OmsModules> rgScreensModuleNameRecordGroup();

	Integer wfFoldersUpdateWorkflowFolders(List<WorkFlowFolders> obj);

	Integer wfFoldersDeleteWorkflowFolders(List<WorkFlowFolders> obj);

	ReferenceCodes wfFoldersPostQuery(WorkFlowFolders paramBean);

	String wfScreensInsertWorkflowScreens(List<WorkflowScreens> obj);

	String wfFoldersInsertWorkflowFolders(List<WorkFlowFolders> obj);

	Integer wfScreensUpdateWorkflowScreens(List<WorkflowScreens> obj);

	Integer wfScreensDeleteWorkflowScreens(List<WorkflowScreens> obj);

	List<WorkFlowFolders> wfFoldersExecuteQuery(WorkFlowFolders obj);

	List<WorkflowScreens> wfScreensExecuteQuery(WorkflowScreens obj);

	List<ReferenceCodes> rgCaseloadTypeRecordGroup();

	Integer wfFoldersDeleteWorkflowFoldersPredelete(List<WorkFlowFolders> deleteList);

}

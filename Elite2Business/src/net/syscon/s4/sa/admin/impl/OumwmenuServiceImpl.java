package net.syscon.s4.sa.admin.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.WorkFlowFolders;
import net.syscon.s4.common.beans.WorkflowScreens;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.OmsModules;
import net.syscon.s4.sa.admin.OumwmenuRepository;
import net.syscon.s4.sa.admin.OumwmenuService;
import net.syscon.s4.sa.admin.beans.WorkflowFoldersCommitBean;
import net.syscon.s4.sa.admin.beans.WorkflowScreenCommitBean;

/**
 * Class OumwmenuServiceImpl
 */
@Service
public class OumwmenuServiceImpl extends BaseBusiness implements OumwmenuService {

	@Autowired
	private OumwmenuRepository oumwmenuRepository;

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @throws SQLException
	 */
	public List<WorkFlowFolders> wfFoldersExecuteQuery(final WorkFlowFolders searchRecord) {
		return oumwmenuRepository.wfFoldersExecuteQuery(searchRecord);
	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstWF_FOLDERS
	 *
	 * @throws SQLException
	 */
	@Transactional
	public Integer wfFoldersCommit(final WorkflowFoldersCommitBean commitBean) {
		int liReturn = 0;
		if (commitBean.getInsertList() != null && !commitBean.getInsertList().isEmpty()) {
			for (WorkFlowFolders obj : commitBean.getInsertList()) {
				obj.setCreateUserId(commitBean.getCreateUserId());
			}
			final String returnValue = oumwmenuRepository.wfFoldersInsertWorkflowFolders(commitBean.getInsertList());
			if (returnValue.contains("WORKFLOW_FOLDERS_PK")) {
				return 2;
			} else if ("1".equals(returnValue)) { 
				return 1;
			} else {
				return 0;
			}
		}
		if (commitBean.getUpdateList() != null && !commitBean.getUpdateList().isEmpty()) {
			for (WorkFlowFolders obj : commitBean.getUpdateList()) {
				obj.setModifyUserId(commitBean.getCreateUserId());
			}
			liReturn = oumwmenuRepository.wfFoldersUpdateWorkflowFolders(commitBean.getUpdateList());
		}
		if (commitBean.getDeleteList() != null && !commitBean.getDeleteList().isEmpty()) {
			commitBean.getDeleteList().forEach(bo -> {bo.setModifyUserId(commitBean.getCreateUserId());});
			final Integer removechild = oumwmenuRepository.wfFoldersDeleteWorkflowFoldersPredelete(commitBean.getDeleteList());
			if(removechild == 1) {
				liReturn = oumwmenuRepository.wfFoldersDeleteWorkflowFolders(commitBean.getDeleteList());
			}
		}
		return liReturn;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @throws SQLException
	 */
	public List<WorkflowScreens> wfScreensExecuteQuery(final WorkflowScreens searchRecord) {
		return oumwmenuRepository.wfScreensExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstWF_SCREENS
	 *
	 * @throws SQLException
	 */
	@Transactional
	public Integer wfScreensCommit(final WorkflowScreenCommitBean commitBean) {
		int liReturn = 0;
		if (commitBean.getInsertList() != null && !commitBean.getInsertList().isEmpty()) {
			for (WorkflowScreens obj : commitBean.getInsertList()) {
				obj.setCreateUserId(commitBean.getCreateUserId());
			}
			final String returnValue = oumwmenuRepository.wfScreensInsertWorkflowScreens(commitBean.getInsertList());
			if (returnValue.contains("WORKFLOW_SCREENS_PK")) {
				return 2;
			} else if ("1".equals(returnValue)) {
				liReturn = 1;
			} else {
				return 0;
			}
		}
		if (commitBean.getUpdateList() != null && !commitBean.getUpdateList().isEmpty()) {
			for (WorkflowScreens obj : commitBean.getUpdateList()) {
				obj.setModifyUserId(commitBean.getCreateUserId());
			}
			liReturn = oumwmenuRepository.wfScreensUpdateWorkflowScreens(commitBean.getUpdateList());
		}
		if (commitBean.getDeleteList() != null && !commitBean.getDeleteList().isEmpty()) {
			commitBean.getDeleteList().forEach(bo -> {bo.setModifyUserId(commitBean.getCreateUserId());});
			liReturn = oumwmenuRepository.wfScreensDeleteWorkflowScreens(commitBean.getDeleteList());
		}
		return liReturn;
	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<ReferenceCodes> rgMenusNameRecordGroup() {
		return oumwmenuRepository.rgMenusNameRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<OmsModules> rgScreensModuleNameRecordGroup() {
		return oumwmenuRepository.rgScreensModuleNameRecordGroup();

	}

	@Override
	public List<ReferenceCodes> rgCaseloadTypeRecordGroup() {
		return oumwmenuRepository.rgCaseloadTypeRecordGroup();
	}

	@Override
	public ReferenceCodes wfFoldersPostQuery(final WorkFlowFolders paramBean) {

		return oumwmenuRepository.wfFoldersPostQuery(paramBean);

	}

}
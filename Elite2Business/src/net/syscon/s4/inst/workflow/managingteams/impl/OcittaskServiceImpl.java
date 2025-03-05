package net.syscon.s4.inst.workflow.managingteams.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.Teams;
import net.syscon.s4.im.beans.VPimsNameSearch;
import net.syscon.s4.inst.workflow.managingteams.OcittaskRepository;
import net.syscon.s4.inst.workflow.managingteams.OcittaskService;
import net.syscon.s4.inst.workflow.managingteams.beans.TagWorkflowAdmQueryTeamTasks;
import net.syscon.s4.inst.workflow.managingteams.beans.TagWorkflowAdmQueryTeamTasksCommitBean;

/**
 * Class OcittaskServiceImpl
 */
@Service
public class OcittaskServiceImpl extends BaseBusiness implements OcittaskService {

	@Autowired
	private OcittaskRepository ocittaskRepository;

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 */
	public List<TagWorkflowAdmQueryTeamTasks> tasksExecuteQuery(final TagWorkflowAdmQueryTeamTasks searchRecord) {
		return ocittaskRepository.tasksExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstTASKS
	 *
	 */
	@Transactional
	public Integer tasksCommit(final TagWorkflowAdmQueryTeamTasksCommitBean commitBean) {
		int liReturn = 0;
		// insertRecords
		if (commitBean.getInsertList() != null && !commitBean.getInsertList().isEmpty()) {
			liReturn = ocittaskRepository.tasksInsertTagWorkflowAdmQueryTeamTasks(commitBean.getInsertList());
			return liReturn;
		}
		// updateRecords
		if (commitBean.getUpdateList() != null && !commitBean.getUpdateList().isEmpty()) {
			liReturn = ocittaskRepository.tasksUpdateTagWorkflowAdmQueryTeamTasks(commitBean.getUpdateList());
		}
		// deleteRecords
		if (commitBean.getDeleteList() != null && !commitBean.getDeleteList().isEmpty()) {
			commitBean.getDeleteList().forEach(ele->ele.setModifyUserId(commitBean.getCreateUserId()));
			liReturn = ocittaskRepository.tasksDeleteTagWorkflowAdmQueryTeamTasks(commitBean.getDeleteList());
		}
		return liReturn;
	}

	/**
	 * This method is used to execute a record group
	 *
	 */
	public List<StaffMembers> rgStaffRecordGroup(final String teamCode,String user) {
		List<StaffMembers> staffMembersList = new ArrayList<>();
		final Teams teams = ocittaskRepository.rgTeamIdRecordGroup(teamCode,user);
		if (teams != null) {
			staffMembersList = ocittaskRepository.rgStaffRecordGroup(teams.getTeamId());
			if (staffMembersList != null && !staffMembersList.isEmpty()) {
				staffMembersList.forEach(action -> action.setCode(action.getStaffId()));
			}
		}
		return staffMembersList;
	}

	/**
	 * This method is used to execute a record group
	 *
	 */
	public Teams getTeamId(final String teamCode,String user) {
		return ocittaskRepository.rgTeamIdRecordGroup(teamCode,user);
	}

	/**
	 * This method is used to execute a record group
	 *
	 */
	public List<ReferenceCodes> rgCompleteStatusRecordGroup() {
		return ocittaskRepository.rgCompleteStatusRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 */
	public List<ReferenceCodes> rgTaskSubTypeRecordGroup(final String taskType) {
		return ocittaskRepository.rgTaskSubTypeRecordGroup(taskType);

	}

	/**
	 * This method is used to execute a record group
	 *
	 */
	public List<ReferenceCodes> rgTaskTypeRecordGroup() {
		return ocittaskRepository.rgTaskTypeRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 */
	public List<Teams> rgTeamRecordGroup(String user) {
		final List<Teams> returnList = ocittaskRepository.rgTeamRecordGroup(user);
		Integer count = 0;
		for (final Teams teams : returnList) {
			count = count + 1;
			teams.setListSeq(count);
		}
		return returnList;

	}

	/**
	 * This method is used to execute a record group
	 *
	 */
	@Override
	public VPimsNameSearch getOffenderBookId(final String offenderIdDisplay, final String caseloadId) {
		return ocittaskRepository.getOffenderBookId(offenderIdDisplay, caseloadId);
	}

}
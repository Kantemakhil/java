package net.syscon.s4.cm.teamsworkflow.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.cm.teamsworkflow.OcdaworkRepository;
import net.syscon.s4.cm.teamsworkflow.OcdtworkRepository;
import net.syscon.s4.cm.teamsworkflow.OcdtworkService;
import net.syscon.s4.cm.teamsworkflow.beans.TagWorkflowBrowseQueue;
import net.syscon.s4.cm.teamsworkflow.beans.TagWorkflowBrowseQueueCommitBean;
import net.syscon.s4.cm.teamsworkflow.beans.TeamMembers;
import net.syscon.s4.cm.teamsworkflow.beans.TeamMembersCommitBean;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.Teams;
import net.syscon.s4.pkgs.tag_workflow.TagWorkflowService;

/**
 * Class OcdtworkServiceImpl
 * 
 */
@Service
public class OcdtworkServiceImpl extends BaseBusiness implements OcdtworkService {

	@Autowired
	private OcdtworkRepository ocdtworkRepository;

	@Autowired
	private OcdaworkRepository ocdaworkRepository;
	@Autowired
	private TagWorkflowService tagWorkflowService;

	/**
	 * Creates new OcdtworkServiceImpl class Object
	 */
	public OcdtworkServiceImpl() {
		// OcdtworkServiceImpl
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 */
	public List<TagWorkflowBrowseQueue> staffQueueExecuteQuery(final TagWorkflowBrowseQueue searchRecord) {
		List<TagWorkflowBrowseQueue> returnList = new ArrayList<>();
		Integer clusterId = null;
		TagWorkflowBrowseQueue returnObj;
		if (searchRecord.getStaffId() != null) {
			clusterId = ocdaworkRepository.getClusterFunctionStaffId(searchRecord);
		}
		if (clusterId != null) {
			if (clusterId == 1) {
				searchRecord.setClusterId("TAGWFC1.TAG_WF_PERSONAL_T_C1");
			} else if (clusterId == 2) {
				searchRecord.setClusterId("TAGWFC2.TAG_WF_PERSONAL_T_C2");
			}
			returnList = ocdtworkRepository.staffQueueExecuteQuery(searchRecord);
			for (final TagWorkflowBrowseQueue returnBean : returnList) {
				returnObj = new TagWorkflowBrowseQueue();
				returnObj = ocdtworkRepository.getOffenderDetails(returnBean.getOffenderBookId(),searchRecord.getCreateUserId());
				returnBean.setOffenderIdDisplay(returnObj.getOffenderIdDisplay());
				returnBean.setLastName(returnObj.getLastName());
				returnBean.setFirstName(returnObj.getFirstName());
				returnObj = ocdtworkRepository.getWorkDetails(returnBean.getWorkId());
				returnBean.setWorkflowType(returnObj.getWorkflowType());
				returnBean.setWorkType(returnObj.getWorkType());
				returnBean.setWorkSubType(returnObj.getWorkSubType());
				returnBean.setModuleName(returnObj.getModuleName());
				returnBean.setManualCloseFlag(returnObj.getManualCloseFlag());
			}
		}
		return returnList;

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstSTAFF_QUEUE
	 *
	 */
	@Transactional
	public Integer staffQueueCommit(final TagWorkflowBrowseQueueCommitBean commitBean) {
		Integer liReturn = 0;
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			liReturn = ocdtworkRepository.staffQueueUpdateTagWorkflowBrowseQueue(commitBean.getUpdateList());
		}
		return liReturn;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 */
	public List<TagWorkflowBrowseQueue> staffMemoQueueExecuteQuery(final TagWorkflowBrowseQueue searchRecord) {
		List<TagWorkflowBrowseQueue> returnList = new ArrayList<>();
		Integer clusterId = null;
		TagWorkflowBrowseQueue returnObj;
		if (searchRecord.getStaffId() != null) {
			clusterId = ocdaworkRepository.getClusterFunctionStaffId(searchRecord);
		}
		if (clusterId != null) {
			if (clusterId == 1) {
				searchRecord.setClusterId("TAGWFC1.TAG_WF_PERSONAL_M_C1");
			} else if (clusterId == 2) {
				searchRecord.setClusterId("TAGWFC2.TAG_WF_PERSONAL_M_C2");
			}
			returnList = ocdtworkRepository.staffMemoQueueExecuteQuery(searchRecord);
			for (final TagWorkflowBrowseQueue returnBean : returnList) {
				returnObj = new TagWorkflowBrowseQueue();
				returnObj = ocdtworkRepository.getOffenderDetails(returnBean.getOffenderBookId(),searchRecord.getCreateUserId());
				returnBean.setOffenderIdDisplay(returnObj.getOffenderIdDisplay());
				returnBean.setLastName(returnObj.getLastName());
				returnBean.setFirstName(returnObj.getFirstName());
				returnObj = ocdtworkRepository.getWorkDetails(returnBean.getWorkId());
				returnBean.setWorkflowType(returnObj.getWorkflowType());
				returnBean.setWorkType(returnObj.getWorkType());
				returnBean.setWorkSubType(returnObj.getWorkSubType());
				returnBean.setModuleName(returnObj.getModuleName());
				returnBean.setManualCloseFlag(returnObj.getManualCloseFlag());
			}
		}
		return returnList;
	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstSTAFF_MEMO_QUEUE
	 *
	 */
	@Transactional
	public Integer staffMemoQueueCommit(final TagWorkflowBrowseQueueCommitBean commitBean) {
		int liReturn = 0;
		// updateRecords
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			for (final TagWorkflowBrowseQueue obj : commitBean.getUpdateList()) {
				if (obj.getAssignedFlag() != null && obj.getAssignedTeamId() != null) {
					obj.setEnableOrDisable(1);
					liReturn =tagWorkflowService.reassignToTeam(obj);
				} else if (obj.getAssignedFlag() != null && obj.getCompleteFlag() == null) {
					obj.setEnableOrDisable(0);
					liReturn =tagWorkflowService.reassignToTeamMember(obj);
					liReturn =tagWorkflowService.removeFromQueue(obj);

				} else if (obj.getCompleteFlag() != null) {
					if ("TASK".equals(obj.getWorkflowType())) {
						liReturn = tagWorkflowService.completeTask(obj, commitBean.getCreateUserId());
					} else if ("MEMO".equals(obj.getWorkflowType())) {
						liReturn =tagWorkflowService.removeFromQueue(obj);
					}
				}
			}
		}
		return liReturn;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 */
	public List<TeamMembers> teamMembersExecuteQuery(final TeamMembers searchRecord) {
		return ocdtworkRepository.teamMembersExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstTEAM_MEMBERS
	 *
	 */
	@Transactional
	public Integer teamMembersCommit(final TeamMembersCommitBean commitBean) {
		int liReturn = 0;
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			liReturn = ocdtworkRepository.teamMembersUpdate(commitBean.getUpdateList());
		}
		return liReturn;
	}

	/**
	 * This method is used to execute a record group
	 *
	 */
	public List<ReferenceCodes> rgReasonRecordGroup() {
		return ocdtworkRepository.rgReasonRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 */
	public List<ReferenceCodes> rgCompletedRecordGroup() {
		return ocdtworkRepository.rgCompletedRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 */
	public List<AgencyLocations> cgfkCrtMvTmpAgyLocIdRecordGroup(final String caseLoadId) {
		return ocdtworkRepository.cgfkCrtMvTmpAgyLocIdRecordGroup(caseLoadId);

	}

	/**
	 * This method is used to execute a record group
	 *
	 */
	public List<ReferenceCodes> rgSexRecordGroup() {
		return ocdtworkRepository.rgSexRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 */
	public List<ReferenceCodes> rgWorkTypeRecordGroup() {
		return ocdtworkRepository.rgWorkTypeRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 */
	public List<ReferenceCodes> rgWorkSubTypeRecordGroup() {
		return ocdtworkRepository.rgWorkSubTypeRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 */
	public List<ReferenceCodes> rgPositionRecordGroup() {
		return ocdtworkRepository.rgPositionRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 */
	public List<ReferenceCodes> rgRoleRecordGroup() {
		return ocdtworkRepository.rgRoleRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 */
	public List<Teams> rgTeamStaffRecordGroup() {
		return ocdtworkRepository.rgTeamStaffRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 */
	public List<StaffMembers> rgStaffSearchRecordGroup(final String agylocId) {
		return ocdtworkRepository.rgStaffSearchRecordGroup(agylocId);
	}

}
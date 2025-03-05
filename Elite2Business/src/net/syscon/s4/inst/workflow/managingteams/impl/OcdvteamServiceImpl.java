package net.syscon.s4.inst.workflow.managingteams.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.ReferenceCodes;
import net.syscon.s4.inst.workflow.managingteams.OcdvteamRepository;
import net.syscon.s4.inst.workflow.managingteams.OcdvteamService;
import net.syscon.s4.inst.workflow.managingteams.beans.OffenderTeamAssignments;
import net.syscon.s4.inst.workflow.managingteams.beans.OffenderTeamAssignmentsCommitBean;
import net.syscon.s4.pkgs.tag_workflow_adm.TagWorkflowAdmService;

/**
 * Class OcdvteamServiceImpl
 */
@Service
public class OcdvteamServiceImpl extends BaseBusiness implements OcdvteamService {

	@Autowired
	private OcdvteamRepository ocdvteamRepository;
	
	@Autowired
	private TagWorkflowAdmService tagWorkflowAdmService;

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @throws SQLException
	 */
	public List<OffenderTeamAssignments> offTeamAssignExecuteQuery(final OffenderTeamAssignments searchRecord) {
		List<OffenderTeamAssignments> list = new ArrayList<>();
		//String teamIdDesc = null;
		Map<String, Object> procedureOutputMap=new HashMap<String, Object>();
		list = ocdvteamRepository.offTeamAssignExecuteQuery(searchRecord);
		for (final OffenderTeamAssignments object : list) {
			procedureOutputMap = tagWorkflowAdmService.getTeamDesc(object.getTeamId());
			//OffenderTeamAssignments obj = ocdvteamRepository.getTeamDesc(object);
			if (procedureOutputMap.get("P_TEAM_CODE").toString() != null) {
				object.setTeamCode(procedureOutputMap.get("P_TEAM_CODE").toString());
			}
			if (procedureOutputMap.get("P_TEAM_NAME").toString()!=null) {
				object.setTeamIdDesc(procedureOutputMap.get("P_TEAM_NAME").toString());
			}
		}
		return list;

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstOFF_TEAM_ASSIGN
	 *
	 * @throws SQLException
	 */
	@Transactional
	public List<OffenderTeamAssignments> offTeamAssignCommit(final OffenderTeamAssignmentsCommitBean commitBean) {
		final List<OffenderTeamAssignments> liReturnData = new ArrayList<>();
		OffenderTeamAssignments object = new OffenderTeamAssignments();
		OffenderTeamAssignments updateObject = new OffenderTeamAssignments();
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			for (OffenderTeamAssignments offenderTeamAssignments : commitBean.getInsertList()) {
				offenderTeamAssignments.setCreateUserId(commitBean.getCreateUserId());				
			}
			object = ocdvteamRepository.offTeamAssignInsertOffenderTeamAssignments(commitBean.getInsertList());
			liReturnData.add(object);
		}
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			for (final OffenderTeamAssignments objectNew : commitBean.getUpdateList()) {
				objectNew.setModifyUserId(commitBean.getCreateUserId());
				if ("N".equals(objectNew.getNbtActiveFlag())) {
					updateObject =tagWorkflowAdmService.deleteOffVteamDtls(objectNew);
							//ocdvteamRepository.deleteOffenderAssignment(objectNew);
					liReturnData.add(updateObject);
				}

			}

		}

		return liReturnData;
	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<ReferenceCodes> rgFunctionRecordGroup() {
		return ocdvteamRepository.rgFunctionRecordGroup();

	}

	@Override
	public List<OffenderTeamAssignments> offBkgOnCheckDeleteMaster(final OffenderTeamAssignments paramBean) {
		final List<OffenderTeamAssignments> list = new ArrayList<OffenderTeamAssignments>();
		OffenderTeamAssignments offenderTeamAssignments = new OffenderTeamAssignments();
		offenderTeamAssignments = ocdvteamRepository.offBkgOnCheckDeleteMaster(paramBean);
		list.add(offenderTeamAssignments);
		return list;
	}
	public OffenderTeamAssignments getTeamDetails(final OffenderTeamAssignments paramBean) {
		return ocdvteamRepository.getTeamDetails(paramBean);
	}
}
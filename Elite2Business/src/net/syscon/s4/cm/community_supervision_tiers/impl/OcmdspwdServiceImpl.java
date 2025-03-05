package net.syscon.s4.cm.community_supervision_tiers.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.cm.community_supervision_tiers.OcmdspwdRepository;
import net.syscon.s4.cm.community_supervision_tiers.OcmdspwdService;
import net.syscon.s4.community_supervision_tiers.WlDefaultStaffUnits;
import net.syscon.s4.community_supervision_tiers.WlNonOffSpecificTasks;
import net.syscon.s4.community_supervision_tiers.WlNonOffSpecificTasksCommitBean;
import net.syscon.s4.im.beans.AgencyLocations;

@Service
public class OcmdspwdServiceImpl implements OcmdspwdService {

	@Autowired
	private OcmdspwdRepository ocmdspwdRepository;

	@Override
	public List<AgencyLocations> getAgyLocRecordGroup(String caseloadId) {
		List<AgencyLocations> list = ocmdspwdRepository.getAgyLocRecordGroup(caseloadId);
		return list;	
	}

	@Transactional
	public Integer maintainDefStaffPosWL(final WlNonOffSpecificTasksCommitBean commitBean) {
		Integer liReturn = 0;
		if (commitBean.getStartingUnitsInsertList() != null && commitBean.getStartingUnitsInsertList().size() > 0) {
			commitBean.getStartingUnitsInsertList().forEach(bo -> {
				bo.setCreateUserId(commitBean.getCreateUserId());
			});
			liReturn = ocmdspwdRepository.insertMaintainStartingWLUnits(commitBean.getStartingUnitsInsertList());
		}
		if (commitBean.getStartingUnitsUpdateList() != null && commitBean.getStartingUnitsUpdateList().size() > 0) {
			commitBean.getStartingUnitsUpdateList().forEach(bo -> {
				bo.setModifyUserId(commitBean.getCreateUserId());
			});
			liReturn = ocmdspwdRepository.updateMaintainStartingWLUnits(commitBean.getStartingUnitsUpdateList());
		}
		if (commitBean.getStartingUnitsDeleteList() != null && commitBean.getStartingUnitsDeleteList().size() > 0) {
			liReturn = ocmdspwdRepository.deleteMaintainStartingWLUnits(commitBean.getStartingUnitsDeleteList());
		}

		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			commitBean.getInsertList().forEach(bo -> {
				bo.setCreateUserId(commitBean.getCreateUserId());
			});
			liReturn = ocmdspwdRepository.insertNonOffenderSpecificTasksforPosition(commitBean.getInsertList());
		}

		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			commitBean.getUpdateList().forEach(bo -> {
				bo.setModifyUserId(commitBean.getCreateUserId());
			});
			liReturn = ocmdspwdRepository.updateNonOffenderSpecificTasksforPosition(commitBean.getUpdateList());
		}

		if (commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {
			commitBean.getDeleteList().forEach(bo -> {
				bo.setModifyUserId(commitBean.getCreateUserId());
			});
			liReturn = ocmdspwdRepository.deleteNonOffenderSpecificTasksforPosition(commitBean.getDeleteList());
		}

		return liReturn;
	}

	@Override
	public List<WlDefaultStaffUnits> assStartingDefWLUnitsExecuteQuery() {
		return ocmdspwdRepository.assStartingDefWLUnitsExecuteQuery();
	}

	@Override
	public List<WlNonOffSpecificTasks> nonOffSpecTaskPosExecuteQuery(WlNonOffSpecificTasks obj) {
		return ocmdspwdRepository.nonOffSpecTaskPosExecuteQuery(obj);
	}
}

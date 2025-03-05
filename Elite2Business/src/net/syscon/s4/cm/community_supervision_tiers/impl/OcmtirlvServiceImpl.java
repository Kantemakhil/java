package net.syscon.s4.cm.community_supervision_tiers.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.cm.community_supervision_tiers.OcmtirlvRepository;
import net.syscon.s4.cm.community_supervision_tiers.OcmtirlvService;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.community_supervision_tiers.MaintainTierLevels;
import net.syscon.s4.community_supervision_tiers.MaintainTierLevelsCommitBean;

@Service
public class OcmtirlvServiceImpl implements OcmtirlvService {

	@Autowired
	private OcmtirlvRepository ocmtirlvrepository;

	@Override
	public List<MaintainTierLevels> tierLevelExecuteQuery(MaintainTierLevels bean) {
		return ocmtirlvrepository.tierLevelExecuteQuery(bean);
	}

	@Transactional
	public Integer tierLevelCommit(final MaintainTierLevelsCommitBean commitBean) {
		int liReturn = 0;
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			commitBean.getInsertList().forEach(bo -> {
				bo.setCreateUserId(commitBean.getCreateUserId());
			});
			liReturn = ocmtirlvrepository.insertMaintainCommunityTierLevels(commitBean.getInsertList());
		}
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			commitBean.getUpdateList().forEach(bo -> {
				bo.setModifyUserId(commitBean.getCreateUserId());
			});
			liReturn = ocmtirlvrepository.updateMaintainCommunityTierLevels(commitBean.getUpdateList());
		}
		if (commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {
			commitBean.getDeleteList().forEach(bo -> {
				bo.setModifyUserId(commitBean.getCreateUserId());
			});
			liReturn = ocmtirlvrepository.deleteMaintainCommunityTierLevels(commitBean.getDeleteList());
		}
		return liReturn;
	}

	@Override
	public List<ReferenceCodes> rgIntakeTierRecordGroup() {
		return ocmtirlvrepository.rgIntakeTierRecordGroup();
	}

}

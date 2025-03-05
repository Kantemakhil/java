package net.syscon.s4.sa.admin.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.im.beans.OmsModules;
import net.syscon.s4.sa.admin.OumrelmdRepository;
import net.syscon.s4.sa.admin.OumrelmdService;
import net.syscon.s4.sa.admin.beans.OmsModulesCommitBean;

@Service
public class OumrelmdServiceImpl implements OumrelmdService {

	@Autowired
	private OumrelmdRepository oumrelmdRepository;

	@Override
	public List<OmsModules> getRelatedModulesByModuleName(String moduleName) {
		List<OmsModules> returnList = new ArrayList<OmsModules>();

		returnList = oumrelmdRepository.getRelatedModulesByModuleName(moduleName);
		if (returnList != null && returnList.size() > 0) {
			for (OmsModules omsModules : returnList) {
				omsModules.setModuleType(omsModules.getAccessModuleName());
			}
		}
		return returnList;
	}

	@Override
	public List<OmsModules> getRelatedModulesLov(String moduleName) {

		return oumrelmdRepository.getRelatedModulesLov(moduleName);
	}

	@Override
	public Integer insertUpdateDelete(OmsModulesCommitBean commitBean) {
		Integer result = 0;
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			commitBean.getInsertList().forEach(e -> e.setCreateUserId(commitBean.getCreateUserId()));
			result = oumrelmdRepository.insertRelatedModules(commitBean.getInsertList());
		}

		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			commitBean.getUpdateList().forEach(e -> e.setModifyUserId(commitBean.getCreateUserId()));
			result = oumrelmdRepository.updateRelatedModules(commitBean.getUpdateList());
		}

		if (commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {
			commitBean.getDeleteList().forEach(bo -> {bo.setModifyUserId(commitBean.getCreateUserId());});
			result = oumrelmdRepository.deleteRelatedModules(commitBean.getDeleteList());
		}

		return result;
	}

}

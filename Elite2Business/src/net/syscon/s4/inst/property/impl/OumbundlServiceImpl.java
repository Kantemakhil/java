package net.syscon.s4.inst.property.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.common.beans.Caseloads;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.inst.property.OumbundlRepository;
import net.syscon.s4.inst.property.OumbundlService;
import net.syscon.s4.sa.admin.beans.PropertyBundles;
import net.syscon.s4.sa.admin.beans.PropertyBundleCommitBean;
import net.syscon.s4.sa.admin.beans.PropertyBundleItems;
import net.syscon.s4.sa.admin.beans.PropertyBundleItemsCommitBean;

@Service
public class OumbundlServiceImpl extends BaseBusiness implements OumbundlService{
	
	@Autowired
	private OumbundlRepository oumbundlRepository;

	@Override
	public List<PropertyBundles> getPropertyGroups() {
		return oumbundlRepository.getPropertyGroups();
	}

	@Override
	public List<PropertyBundleItems> getPropertyItems(String groupId) {
		return oumbundlRepository.getPropertyItems(groupId);
	}

	@Override
	public Integer insertUpdateDeletePropertyBundles(PropertyBundleCommitBean commitBean) {
		Integer savedResult = 0;
		if(commitBean.getInsertList() != null && commitBean.getInsertList().size()>0) {
			savedResult = oumbundlRepository.insertPropertyBundles(commitBean.getInsertList());
		}
		if(commitBean.getUpdateList() != null && commitBean.getUpdateList().size()>0) {
			savedResult = oumbundlRepository.updatePropertyBundles(commitBean.getUpdateList());
		}
		if(commitBean.getDeleteList() != null && commitBean.getDeleteList().size()>0) {
			commitBean.getDeleteList().forEach(ele->ele.setModifyUserId(commitBean.getCreateUserId()));
			savedResult = oumbundlRepository.deletePropertyBundles(commitBean.getDeleteList());
		}
		return savedResult;
	}

	@Override
	public Integer insertUpdateDeletePropertyItems(PropertyBundleItemsCommitBean commitBean) {
		Integer savedResult = 0;
		if(commitBean.getInsertList() != null && commitBean.getInsertList().size()>0) {
			savedResult = oumbundlRepository.insertPropertyItems(commitBean.getInsertList());
		}
		if(commitBean.getUpdateList() != null && commitBean.getUpdateList().size()>0) {
			savedResult = oumbundlRepository.updatePropertyItems(commitBean.getUpdateList());
		}
		if(commitBean.getDeleteList() != null && commitBean.getDeleteList().size()>0) {
			commitBean.getDeleteList().forEach(ele->ele.setModifyUserId(commitBean.getCreateUserId()));
			savedResult = oumbundlRepository.deletePropertyItems(commitBean.getDeleteList());
		}
		return savedResult;
	}

	@Override
	public List<Caseloads> getCaseloads(StaffMembers searchBean) {
		return oumbundlRepository.getCaseloads( searchBean);
	}

}

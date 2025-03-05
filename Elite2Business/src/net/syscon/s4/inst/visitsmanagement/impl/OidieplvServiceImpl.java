package net.syscon.s4.inst.visitsmanagement.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.ApplicationConstants;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.inst.visitsmanagement.OidieplvRepository;
import net.syscon.s4.inst.visitsmanagement.OidieplvService;
import net.syscon.s4.inst.visitsmanagement.beans.IEPLevelCommitBean;
import net.syscon.s4.inst.visitsmanagement.beans.IepLevelBean;

@Service
public class OidieplvServiceImpl implements OidieplvService {
	@Autowired
	private OidieplvRepository oidieplvRepository;

	@Override
	public List<IepLevelBean> getIEPLov() {
		List<IepLevelBean> iepLevelsList = new ArrayList<IepLevelBean>();
		iepLevelsList = oidieplvRepository.getIEPLov();
		if (iepLevelsList != null && iepLevelsList.size() > 0)
		for(IepLevelBean ele:iepLevelsList) {
			if (ApplicationConstants.YFLAG.equals(ele.getActiveFlag())) {
				ele.setCanDisplay(true);
			} else {
				ele.setCanDisplay(false);
			}
		}
				
			

		return iepLevelsList;
	}

	@Override
	public List<IepLevelBean> getAllData(Integer offenderBookId) {
		List<IepLevelBean> returnList=new ArrayList<IepLevelBean>();
		Integer sysGenearetedStaffId= oidieplvRepository.getSystemGeneratedStaffId();
		Long systemStaffId=oidieplvRepository.getSystemStaffId();
		returnList = oidieplvRepository.getAllData(offenderBookId);
		for (IepLevelBean iepLevelBean : returnList) {
			if(sysGenearetedStaffId == iepLevelBean.getStaffId()) {
				iepLevelBean.setApprovedStaff("System Generated");
			}else if(systemStaffId==iepLevelBean.getStaffId().longValue()) {
				iepLevelBean.setApprovedStaff("System");
			}
	}
		return returnList;
	}

	@Transactional
	public Integer saveAllData(IEPLevelCommitBean commitBean) {
		int liReturn = 0;
		// insertRecords
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			commitBean.getInsertList().forEach(iep->iep.setCreateUserId(commitBean.getCreateUserId()));
			liReturn = oidieplvRepository.saveAllData(commitBean.getInsertList());
		}

		// updateRecords
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			for (IepLevelBean bean : commitBean.getUpdateList()) {
				bean.setModifyUserId(commitBean.getCreateUserId());
				liReturn = oidieplvRepository.updateComment(bean);

			}
		}
		return liReturn;

	}

	@Override
	public StaffMembers lvLoginUserStaffName(String userName) {
		return oidieplvRepository.lvLoginUserStaffName(userName);
	}

	@Override
	public List<IepLevelBean> getReviewDaysForIepLevelCode() {
		return oidieplvRepository.getReviewDaysForIepLevelCode();
	}

}

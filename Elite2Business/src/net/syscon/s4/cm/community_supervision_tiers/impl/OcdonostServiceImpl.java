package net.syscon.s4.cm.community_supervision_tiers.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.cm.community_supervision_tiers.OcdonostRepository;
import net.syscon.s4.cm.community_supervision_tiers.OcdonostService;
import net.syscon.s4.common.ApplicationConstants;
import net.syscon.s4.community_supervision_tiers.WlOfficerNonOffSpecificTasks;
import net.syscon.s4.community_supervision_tiers.WlOfficerNonOffSpecificTasksCommitBean;

@Service
public class OcdonostServiceImpl implements OcdonostService {

	@Autowired
	private OcdonostRepository ocdonostRepository;

	@Override
	public WlOfficerNonOffSpecificTasks getStaffName(WlOfficerNonOffSpecificTasks bean) {
		WlOfficerNonOffSpecificTasks obj = new WlOfficerNonOffSpecificTasks();
		List<WlOfficerNonOffSpecificTasks> list = new ArrayList<WlOfficerNonOffSpecificTasks>();
		Integer workLoadValue=0;
		String staffName = ocdonostRepository.getStaffName(bean.getStaffId());
		Integer defUnit = ocdonostRepository.getDefaultUnit(bean.getStaffPosition());
		if (defUnit == null) {
			defUnit = 0;
		}
		if(bean.getAvailableUnits() == null) {
			bean.setAvailableUnits(BigDecimal.ONE);
		}
		workLoadValue=ocdonostRepository.gettingUnitsUsedByEachOffenders(bean);           
		List<WlOfficerNonOffSpecificTasks> updateList = ocdonostRepository.getOfficeNonOffSpeTask(bean);
		if (updateList.size() > 0) {
			Integer sumUpdate = 0;
			BigDecimal avaiUnitUpdate = BigDecimal.ZERO;
			if (bean.getAvailableUnits() != null) {
				avaiUnitUpdate = bean.getAvailableUnits().multiply(new BigDecimal(defUnit));
			}
			for (WlOfficerNonOffSpecificTasks data : updateList) {
				if(data.getActiveFlag() != null && data.getActiveFlag().equals(ApplicationConstants.YES)) {	
					sumUpdate = sumUpdate + data.getUnits();
				}
			}
			Integer usedUnits= workLoadValue+sumUpdate;
			BigDecimal subtUpdate = avaiUnitUpdate.subtract(new BigDecimal(usedUnits));
			obj.setAvailableUnits(subtUpdate);
			obj.setStaffLocRoleId(bean.getStaffLocRoleId());
			obj.setModifyUserId(bean.getModifyUserId());
			ocdonostRepository.updateAvailableUnits(obj);
		}
		
		list = ocdonostRepository.getNonOffSpecTaskforPos(bean);
		if (list.size() > 0) {
			BigDecimal avaiUnit = BigDecimal.ZERO;
			if (bean.getAvailableUnits() != null) {
				avaiUnit = bean.getAvailableUnits().multiply(new BigDecimal(defUnit));
			}
			Integer sum = 0;
			for (WlOfficerNonOffSpecificTasks data : list) {
				data.setStaffLocRoleId(bean.getStaffLocRoleId());
				data.setStaffPosition(bean.getStaffPosition());
				data.setAgyLocId(bean.getAgyLocId());
				data.setActiveFlag(ApplicationConstants.YES);
				sum = sum + data.getUnits();
			}
			Integer usedUnits= workLoadValue+sum;
			BigDecimal subt = avaiUnit.subtract(new BigDecimal(usedUnits));
			list.forEach(wo -> {
				wo.setAvailableUnits(subt);
			});
			insertOfficerNonOffSpectask(list);

		}

		obj.setStaffPosition(staffName);
		obj.setUnits(defUnit);
		return obj;
	}

	@Transactional
	private void insertOfficerNonOffSpectask(List<WlOfficerNonOffSpecificTasks> list) {
		if (list.size() > 0) {
			ocdonostRepository.insertOfficerNonOffSpeTask(list);
		}
	}

	@Override
	public List<WlOfficerNonOffSpecificTasks> getNonOffenderSpecificTasks(WlOfficerNonOffSpecificTasks obj) {
		return ocdonostRepository.getOfficeNonOffSpeTask(obj);
	}

	@Transactional
	public Integer offNonOffSpeTaskCommit(WlOfficerNonOffSpecificTasksCommitBean commitBean) {
		Integer liReturn = 0;
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			commitBean.getInsertList().forEach(bo -> {
				bo.setCreateUserId(commitBean.getCreateUserId());
			});
			liReturn = ocdonostRepository.insertOfficerNonOffSpeTask(commitBean.getInsertList());
			if (liReturn == 1) {
				WlOfficerNonOffSpecificTasks obj = commitBean.getInsertList().get(0);
				obj.setAvailableUnits(commitBean.getAvailableWLUnits());
				obj.setModifyUserId(commitBean.getCreateUserId());
			}
		}

		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			commitBean.getUpdateList().forEach(bo -> {
				bo.setCreateUserId(commitBean.getCreateUserId());
			});
			liReturn = ocdonostRepository.updateOfficerNonOffSpeTask(commitBean.getUpdateList());
			if (liReturn == 1) {
				WlOfficerNonOffSpecificTasks obj = commitBean.getUpdateList().get(0);
				obj.setAvailableUnits(commitBean.getAvailableWLUnits());
			}
		}

		if (commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {
			commitBean.getDeleteList().forEach(bo -> {
				bo.setModifyUserId(commitBean.getCreateUserId());
			});
			liReturn = ocdonostRepository.deleteOfficerNonOffSpeTask(commitBean.getDeleteList());
			if (liReturn == 1) {
				WlOfficerNonOffSpecificTasks obj = commitBean.getDeleteList().get(0);
				obj.setAvailableUnits(commitBean.getAvailableWLUnits());
				obj.setModifyUserId(commitBean.getCreateUserId());
			}
		}

		return liReturn;
	}
}

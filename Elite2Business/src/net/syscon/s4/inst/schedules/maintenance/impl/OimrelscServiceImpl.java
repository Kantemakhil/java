package net.syscon.s4.inst.schedules.maintenance.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.inst.schedules.maintenance.OimrelscRepository;
import net.syscon.s4.inst.schedules.maintenance.OimrelscService;
import net.syscon.s4.inst.schedules.maintenance.bean.FinalSubmitBeanReleaseScheduleSetting;
import net.syscon.s4.inst.schedules.maintenance.bean.ReleaseSchedulesSettingsBean;
@Service
public class OimrelscServiceImpl extends BaseBusiness implements OimrelscService{
	@Autowired
	private OimrelscRepository oimrelscRepository;

	@Override
	public List<ReleaseSchedulesSettingsBean> retrieveGridData(ReleaseSchedulesSettingsBean searchBean) {
		return oimrelscRepository.retrieveGridData(searchBean);
	}

	@Override
	public Integer submitFormData(FinalSubmitBeanReleaseScheduleSetting odynfrmSubmitDataBean) {
		Integer finalReturn=0;
		if(odynfrmSubmitDataBean.getKeyDatesData()!=null) {
			ReleaseSchedulesSettingsBean finalKeyDateData=new 	ReleaseSchedulesSettingsBean();
			finalKeyDateData=odynfrmSubmitDataBean.getKeyDatesData();
			finalKeyDateData.setCreateUserId(odynfrmSubmitDataBean.getCreateUserId());
			finalKeyDateData.setModifyUserId(odynfrmSubmitDataBean.getCreateUserId());
			Integer count=0;
			count =  oimrelscRepository.getKeyDateCount();
			if(count > 0) {
				finalReturn = oimrelscRepository.updateSubmitFormData(finalKeyDateData);
			} else {
				finalReturn =  oimrelscRepository.submitFormData(finalKeyDateData);
			}
		}
		if(odynfrmSubmitDataBean.getAlertsGridData()!=null) {
			ReleaseSchedulesSettingsBean finalalertsGridData=new 	ReleaseSchedulesSettingsBean();
			finalalertsGridData=odynfrmSubmitDataBean.getAlertsGridData();
			finalalertsGridData.setCreateUserId(odynfrmSubmitDataBean.getCreateUserId());
			finalalertsGridData.setModifyUserId(odynfrmSubmitDataBean.getCreateUserId());
			Integer count=0;
			count =  oimrelscRepository.getAlertsDateCount();
			if(count > 0) {
				finalReturn = oimrelscRepository.updateSubmitAlertsFormData(finalalertsGridData);
			} else {
				finalReturn =  oimrelscRepository.submitFormData(finalalertsGridData);
			}
		}
		
		if(odynfrmSubmitDataBean.getFinalChargeIndData()!=null) {
			ReleaseSchedulesSettingsBean finalchargesGridData=new 	ReleaseSchedulesSettingsBean();
			finalchargesGridData=odynfrmSubmitDataBean.getFinalChargeIndData();
			finalchargesGridData.setCreateUserId(odynfrmSubmitDataBean.getCreateUserId());
			finalchargesGridData.setModifyUserId(odynfrmSubmitDataBean.getCreateUserId());
			Integer count=0;
			count =  oimrelscRepository.getChargeIndCount();
			if(count > 0) {
				finalReturn = oimrelscRepository.updateSubmitChargesFormData(finalchargesGridData);
			} else {
				finalReturn =  oimrelscRepository.submitFormData(finalchargesGridData);
			}
		}
		return finalReturn;
		
	}

	@Override
	public ReleaseSchedulesSettingsBean retrieveAlertGridData(ReleaseSchedulesSettingsBean searchBean) {
		 return oimrelscRepository.retrieveAlertGridData(searchBean);
	}
}

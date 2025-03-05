package net.syscon.s4.inst.legalscreens.maintenance.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.im.intake.beans.MovementReasons;
import net.syscon.s4.inst.legalscreens.maintenance.OcmlesetRepository;
import net.syscon.s4.inst.legalscreens.maintenance.OcmlesetService;
import net.syscon.s4.inst.legalscreens.maintenance.bean.LegalSettings;
import net.syscon.s4.inst.legalscreens.maintenance.bean.LegalSettingsCommitBean;
@Service
public class OcmlesetServiceImpl implements OcmlesetService {
	@Autowired
	private OcmlesetRepository ocmlesetRepository;

	@Override
	@Transactional
	public Integer updateLegalsData(LegalSettingsCommitBean commitBean) {
		int liReturn = 0;
		// updateRecords
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			commitBean.getUpdateList().forEach(bean->bean.setModifyUserId(commitBean.getCreateUserId()));
			liReturn = ocmlesetRepository.updateLegalsData(commitBean.getUpdateList());
		}
		return liReturn;
	}
	
	public List<LegalSettings> getLegalsData(){
		return ocmlesetRepository.getLegalsData();
	}
	
	public List<ReferenceCodes> fetchRoles(){
		return ocmlesetRepository.fetchRoles();
	}
	
	
	@Override
	public List<MovementReasons> fetchMovementTypes() {
		return ocmlesetRepository.fetchMovementTypes();
	}
	
	//To retrieve the legal setting value based on the code
	@Override
	public String getLegalSettingValue(String code) {
		return ocmlesetRepository.getLegalSettingValue(code);
	}

}

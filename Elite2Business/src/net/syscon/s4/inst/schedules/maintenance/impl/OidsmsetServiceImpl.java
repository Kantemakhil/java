package net.syscon.s4.inst.schedules.maintenance.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.inst.schedules.bean.SchMovSettingCommitBean;
import net.syscon.s4.inst.schedules.bean.ScheduleMovementSetting;
import net.syscon.s4.inst.schedules.maintenance.OidsmsetRepository;
import net.syscon.s4.inst.schedules.maintenance.OidsmsetService;

@Service
public class OidsmsetServiceImpl extends BaseBusiness implements OidsmsetService {

	@Autowired
	private OidsmsetRepository oidsmsetRepository;

	@Override
	@Transactional
	public Integer tapScheduleSettingCommit(SchMovSettingCommitBean commitBean) {

		Integer liReturn = 0;
		if (!commitBean.getUpdateList().isEmpty() && commitBean.getUpdateList().size() > 0) {
			for (ScheduleMovementSetting bean : commitBean.getUpdateList()) {
				bean.setModifyUserId(commitBean.getCreateUserId());
				bean.setSettingCodeValue(bean.getSettingValue().getBytes());

			}
			liReturn = oidsmsetRepository.tapScheduleSettingUpdate(commitBean.getUpdateList());
		}
		return liReturn;

	}

	@Override
	public List<ScheduleMovementSetting> tapScheduleSettingExecuteQuery() {
		List<ScheduleMovementSetting> returnList = oidsmsetRepository.tapScheduleSettingExecuteQuery();
		for (ScheduleMovementSetting bean : returnList) {
			bean.setSettingValue(new String(bean.getSettingCodeValue()));
		}
		return returnList;
	}

}

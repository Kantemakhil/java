package net.syscon.s4.inst.institutionalactivities.maintenance.impl;

import java.math.BigDecimal;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.VOffenderCourseAttendances;
import net.syscon.s4.inst.institutionalactivities.maintenance.OcmpssetRepository;
import net.syscon.s4.inst.institutionalactivities.maintenance.OcmpssetService;
import net.syscon.s4.inst.institutionalactivities.maintenance.beans.ProgramPaySettingsBean;
import net.syscon.s4.inst.institutionalactivities.maintenance.beans.ProgramPaySettingsCommitBean;

@Service
public class OcmpssetServiceImpl extends BaseBusiness implements OcmpssetService {

	@Autowired
	private OcmpssetRepository ocmpssetRepository;

	@Override
	public List<ReferenceCodes> iepLevelRecordGroup() {
		return ocmpssetRepository.iepLevelRecordGroup();
	}

	@Override
	public List<ReferenceCodes> acpOutcomeCodesRecordGroup() {
		return ocmpssetRepository.acpOutcomeCodesRecordGroup();
	}

	@Override
	public List<ProgramPaySettingsBean> progServSettingExecuteQuery() {
		List<ProgramPaySettingsBean> returnList = ocmpssetRepository.progServSettingExecuteQuery();
		for (ProgramPaySettingsBean bean : returnList) {
			bean.setInstActAttCode(new String(bean.getInstActAttCodeVal()));
			bean.setAcpAttCode(new String(bean.getAcpAttCodeVal()));
		}
		return returnList;
	}

	@Transactional
	public Integer prgSrvSettingCommit(ProgramPaySettingsCommitBean commitBean) {
		Integer liReturn = null;
		if (!commitBean.getInsertList().isEmpty() && commitBean.getInsertList().size() > 0) {
			for (ProgramPaySettingsBean bean : commitBean.getInsertList()) {
				bean.setInstActAttCodeVal(bean.getInstActAttCode().getBytes());
				bean.setAcpAttCodeVal(bean.getAcpAttCode().getBytes());
				bean.setCreateUserId(commitBean.getCreateUserId());
			}
			liReturn = ocmpssetRepository.offProgServSetingInsert(commitBean.getInsertList());
		} else if (!commitBean.getUpdateList().isEmpty() && commitBean.getUpdateList().size() > 0) {
			for (ProgramPaySettingsBean bean : commitBean.getUpdateList()) {
				bean.setInstActAttCodeVal(bean.getInstActAttCode().getBytes());
				bean.setAcpAttCodeVal(bean.getAcpAttCode().getBytes());
				bean.setModifyUserId(commitBean.getCreateUserId());
			}
			liReturn = ocmpssetRepository.offProgServSetingUpdate(commitBean.getUpdateList());
		}
		return liReturn;
	}

	@Override
	public Integer getHours() {
		return ocmpssetRepository.getHours();
	}

	@Override
	public String getProgramServicePayFlag() {
		return ocmpssetRepository.getProgramServicePayFlag();
	}

	@Transactional
	public Integer updateSchedulePay(final BigDecimal offenderBookId, final Integer eventId, final String modulename,
			final String userId) {
		Integer liReturn = 1;
		List<VOffenderCourseAttendances> obj = ocmpssetRepository.getSchedulePayRate(offenderBookId, eventId,
				modulename);
		if (obj.size() > 0 && obj.get(0).getStartTime() != null && obj.get(0).getEndTime() != null) {
			obj.get(0).setModifyUserId(userId);
			Format formatter = new SimpleDateFormat("HH:mm");
			String startDate = formatter.format(obj.get(0).getStartTime());
			String endDate = formatter.format(obj.get(0).getEndTime());

			LocalTime startTime = LocalTime.parse(startDate);
			LocalTime endTime = LocalTime.parse(endDate);

			long totalMinutes = ChronoUnit.MINUTES.between(startTime, endTime);
			double payhoursval = (double) totalMinutes / (double) Duration.ofHours(1).toMinutes();
			obj.get(0).setPayHours(BigDecimal.valueOf(payhoursval));
			if (obj.get(0).getPayHours() != null && obj.get(0).getPaySystemRate() != null) {
				obj.get(0).setPayActualAmount(obj.get(0).getPayHours().multiply(obj.get(0).getPaySystemRate()));
			}
			liReturn = ocmpssetRepository.updateSystemPay(obj.get(0));
		}
		return liReturn;
	}

	@Override
	public List<ReferenceCodes> eliteFinancialsRecordGroup() {
		return ocmpssetRepository.eliteFinancialsRecordGroup();
	}
}
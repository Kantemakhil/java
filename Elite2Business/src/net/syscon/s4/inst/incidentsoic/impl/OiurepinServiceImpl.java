package net.syscon.s4.inst.incidentsoic.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.cm.intakeclosure.OcdintakRepository;
import net.syscon.s4.im.incidentsoic.beans.ReportableIncedentDetails;
import net.syscon.s4.im.incidentsoic.beans.ReportableIncedentDetailsCommitBean;
import net.syscon.s4.inst.incidentsoic.OiurepinRepository;
import net.syscon.s4.inst.incidentsoic.OiurepinService;

@Service
public class OiurepinServiceImpl implements OiurepinService {

	@Autowired
	private OiurepinRepository oiurepinDao;
	
	@Autowired
	private OcdintakRepository ocdintakRepository;
	
	@Override
	public List<ReportableIncedentDetails> getReportDetailsExecuteQuery(ReportableIncedentDetails searchBean) {
		return oiurepinDao.getReportDetailsExecuteQuery(searchBean);
	}

	@Override
	public List<ReportableIncedentDetails> reportableIncedentDetailsCommit(
			ReportableIncedentDetailsCommitBean commitBean) {
		final List<ReportableIncedentDetails> liReturnData = new ArrayList<>();
		final ReportableIncedentDetails reportableIncedentDetails = new ReportableIncedentDetails();
		Integer liReturn = 0;
		if (commitBean.getInsertList() != null && !commitBean.getInsertList().isEmpty()) {
			for (ReportableIncedentDetails obj : commitBean.getInsertList()) {
				obj.setCreateUserId(commitBean.getCreateUserId());
				Integer staffId = ocdintakRepository.oldContactGetStaffId(commitBean.getCreateUserId());
				obj.setReportableStaffId(staffId);
			}
			liReturn = oiurepinDao.reportableIncedentDetailsInsertData(commitBean.getInsertList());
		}
		if (commitBean.getUpdateList() != null && !commitBean.getUpdateList().isEmpty()) {
			for (ReportableIncedentDetails obj : commitBean.getUpdateList()) {
				obj.setModifyUserId(commitBean.getCreateUserId());
				Integer staffId = ocdintakRepository.oldContactGetStaffId(commitBean.getCreateUserId());
				obj.setReportableStaffId(staffId);
			}
			liReturn = oiurepinDao.reportableIncedentDetailsUpdateData(commitBean.getUpdateList());
		}
		if (commitBean.getDeleteList() != null && !commitBean.getDeleteList().isEmpty()) {
			commitBean.getDeleteList().forEach(bo -> {bo.setModifyUserId(commitBean.getCreateUserId());});
			liReturn = oiurepinDao.reportableIncedentDetailsDeleteData(commitBean.getDeleteList());
		}
		reportableIncedentDetails.setReturnedOutput(BigDecimal.valueOf(liReturn));
		liReturnData.add(reportableIncedentDetails);
		return liReturnData;
	}

	@Override
	public String getUserNameLog(String userNameLog) {
		return oiurepinDao.getUserNameLog(userNameLog);
	}

}

package net.syscon.s4.inst.demographicsbiometrics.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.common.beans.StaffMemberRoles;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.HealthRecordDetails;
import net.syscon.s4.im.beans.HealthRecordDetailsCommitBean;
import net.syscon.s4.im.beans.OffHealthRecordsData;
import net.syscon.s4.im.beans.OffHealthRecordsDataCommitBean;
import net.syscon.s4.inst.demographicsbiometrics.OcdhealtRepository;
import net.syscon.s4.inst.demographicsbiometrics.OcdhealtService;
import net.syscon.s4.inst.securitythreatgroups.OidstghlRepository;
import net.syscon.s4.pkgs.tag_security.TagSecurityService;

@Service
public class OcdhealtServiceImpl extends BaseBusiness implements OcdhealtService {
@Autowired
private OcdhealtRepository ocdhealtRepository;

@Autowired
private TagSecurityService tagSecurityService;

@Autowired
private OidstghlRepository oidstghlRepository;

	@Override
	public Integer getUserRoleForHealUser(String userName) {
		String reasonTabsecty = tagSecurityService.getGroupPrivilege("OCDHEALT", userName);		
		Integer accessCount = 0;
		if("A".equals(reasonTabsecty)) {
			accessCount =1;
		} else {
			accessCount = 0;
		}
		return accessCount;
	}

	@Override
	public Integer getUserRoleForHealAdvUser(String userName) {
		Integer roleId= ocdhealtRepository.getRoleId("ADV_HEAL_USER");
		Integer accessCount = 0;
		final List<StaffMemberRoles> returnList = oidstghlRepository.cgwhenNewFormInstance(userName);
		for (StaffMemberRoles obj : returnList) {
			if (obj.getRoleId() != null && roleId != null && (roleId.compareTo(obj.getRoleId())==0)) {
					accessCount = 1;
					break;
			}
		}
		return accessCount;
	}

	@Override
	public List<OffHealthRecordsData> offenderRowHealthDataCommit(OffHealthRecordsDataCommitBean commitBean) {
		final List<OffHealthRecordsData> liReturnData = new ArrayList<>();
		final OffHealthRecordsData returnObj = new OffHealthRecordsData();
		Integer liReturn = 0;
		// insertRecords
		if (commitBean.getInsertList() != null && !commitBean.getInsertList().isEmpty()) {
			for (OffHealthRecordsData obj : commitBean.getInsertList()) {
				obj.setCreateUserId(commitBean.getCreateUserId());
			}
			liReturn = ocdhealtRepository.offenderRowHealthDataInsertData(commitBean.getInsertList());

		}
		// updateRecords
		if (commitBean.getUpdateList() != null && !commitBean.getUpdateList().isEmpty()) {
			for (OffHealthRecordsData obj : commitBean.getUpdateList()) {
				obj.setModifyUserId(commitBean.getCreateUserId());
			}
			liReturn = ocdhealtRepository.offenderRowHealthDataUpdateData(commitBean.getUpdateList());
		}
		// deleteRecords
		if (commitBean.getDeleteList() != null && !commitBean.getDeleteList().isEmpty()) {
			commitBean.getDeleteList().forEach(bo -> {bo.setModifyUserId(commitBean.getCreateUserId());});
			liReturn = ocdhealtRepository.offenderRowHealthDataDeleteData(commitBean.getDeleteList());
		}
		returnObj.setReturnedOutput(BigDecimal.valueOf(liReturn));
		liReturnData.add(returnObj);
		return liReturnData;
	}

	@Override
	public List<OffHealthRecordsData> getOffenderRowHealthExecuteQuery(OffHealthRecordsData searchBean) {
		return ocdhealtRepository.getOffenderRowHealthExecuteQuery(searchBean);
	}

	@Override
	public List<HealthRecordDetails> getHealthDetailExecuteQuery(HealthRecordDetails searchBean) {
		return ocdhealtRepository.getHealthDetailExecuteQuery(searchBean);
	}

	@Override
	public List<HealthRecordDetails> healthRecordDetailDataCommit(HealthRecordDetailsCommitBean commitBean) {
		final List<HealthRecordDetails> liReturnData = new ArrayList<>();
		final HealthRecordDetails returnObj = new HealthRecordDetails();
		Integer liReturn = 0;
		// insertRecords
		if (commitBean.getInsertList() != null && !commitBean.getInsertList().isEmpty()) {
			for (HealthRecordDetails obj : commitBean.getInsertList()) {
				obj.setCreateUserId(commitBean.getCreateUserId());
			}
			liReturn = ocdhealtRepository.healthRecordDetailsInsertData(commitBean.getInsertList());

		}
		// updateRecords
		if (commitBean.getUpdateList() != null && !commitBean.getUpdateList().isEmpty()) {
			for (HealthRecordDetails obj : commitBean.getUpdateList()) {
				obj.setModifyUserId(commitBean.getCreateUserId());
			}
			liReturn = ocdhealtRepository.healthRecordDetailsUpdateData(commitBean.getUpdateList());
		}
		// deleteRecords
		if (commitBean.getDeleteList() != null && !commitBean.getDeleteList().isEmpty()) {
			commitBean.getDeleteList().forEach(bo -> {bo.setModifyUserId(commitBean.getCreateUserId());});
			liReturn = ocdhealtRepository.healthRecordDetailsDeleteData(commitBean.getDeleteList());
		}
		returnObj.setReturnedOutput(liReturn);
		liReturnData.add(returnObj);
		return liReturnData;

	}

}

package net.syscon.s4.inst.securitythreatgroups.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SecurityThreatGroups;
import net.syscon.s4.common.beans.StaffMemberRoles;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.SecurityThreatGroupsCommitBean;
import net.syscon.s4.im.beans.StgLocations;
import net.syscon.s4.im.beans.StgLocationsCommitBean;
import net.syscon.s4.inst.securitythreatgroups.OidstghlRepository;
import net.syscon.s4.inst.securitythreatgroups.OidstghlService;

/**
 * Class OidstghlServiceImpl
 */
@Service
public class OidstghlServiceImpl extends BaseBusiness implements OidstghlService {

	@Autowired
	private OidstghlRepository oidstghlRepository;

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 */
	public List<StgLocations> stgOnCheckDeleteMaster(final StgLocations paramBean) {
		return oidstghlRepository.stgOnCheckDeleteMaster(paramBean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 */
	public List<SecurityThreatGroups> stgPreInsert(final SecurityThreatGroups paramBean) {
		return oidstghlRepository.stgPreInsert(paramBean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 */
	public List<ReferenceCodes> stgLocationsPostQuery(final ReferenceCodes paramBean) {
		return oidstghlRepository.stgLocationsPostQuery(paramBean);
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 */
	public List<SecurityThreatGroups> stgExecuteQuery(final SecurityThreatGroups searchRecord) {
		return oidstghlRepository.stgExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstSTG
	 */
	@Transactional
	public Integer stgCommit(final SecurityThreatGroupsCommitBean commitBean) {
		int liReturn = 0;
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			commitBean.getInsertList().forEach(data->data.setCreateUserId(commitBean.getCreateUserId()));
			liReturn = oidstghlRepository.stgInsertSecurityThreatGroups(commitBean.getInsertList());
		}
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			for (SecurityThreatGroups bean : commitBean.getUpdateList()) {
				bean.setModifyUserId(commitBean.getCreateUserId());
			}
			liReturn = oidstghlRepository.stgUpdateSecurityThreatGroups(commitBean.getUpdateList());
		}
		if (commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {
			commitBean.getDeleteList().forEach(ele->ele.setModifyUserId(commitBean.getCreateUserId()));
			liReturn = oidstghlRepository.stgDeleteSecurityThreatGroups(commitBean.getDeleteList());
		}
		return liReturn;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 */
	public List<StgLocations> stgLocationsExecuteQuery(final StgLocations searchRecord) {
		return oidstghlRepository.stgLocationsExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstSTG_LOCATIONS
	 */
	@Transactional
	public Integer stgLocationsCommit(final StgLocationsCommitBean commitBean) {
		int liReturn = 0;
		Long stgId = null;
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			for (final StgLocations stgLocations : commitBean.getInsertList()) {
				stgId = stgLocations.getStgId();
			}
			List<StgLocations> saveObj = new ArrayList<>();
			StgLocations offenderAlertObj = new StgLocations();
			if (commitBean.getInsertList().size() > 0) {
				for (int i = 0; i < commitBean.getInsertList().size(); i++) {
					saveObj = new ArrayList<>();
					Long valueAlertSeq = oidstghlRepository.stgLocationsPreInsert(stgId);
					if (valueAlertSeq == null) {
						valueAlertSeq = 1L;
					} else {
						valueAlertSeq = valueAlertSeq + 1L;
					}
					offenderAlertObj = commitBean.getInsertList().get(i);
					offenderAlertObj.setLocationSeq(valueAlertSeq);
					offenderAlertObj.setCreateUserId(commitBean.getCreateUserId());
					saveObj.add(offenderAlertObj);
					liReturn = oidstghlRepository.stgLocationsInsertStgLocations(saveObj);
				}
			}
		}
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			for (final StgLocations bean : commitBean.getUpdateList()) {
				bean.setModifyUserId(commitBean.getCreateUserId());
			}
			liReturn = oidstghlRepository.stgLocationsUpdateStgLocations(commitBean.getUpdateList());
		}
		if (commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {
			commitBean.getDeleteList().forEach(ele->ele.setModifyUserId(commitBean.getCreateUserId()));
			liReturn = oidstghlRepository.stgLocationsDeleteStgLocations(commitBean.getDeleteList());
		}
		return liReturn;
	}

	/**
	 * This method is used to execute a record group
	 */
	public List<ReferenceCodes> recCityRecordGroup() {
		return oidstghlRepository.recCityRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 */
	public List<ReferenceCodes> recStateRecordGroup() {
		return oidstghlRepository.recStateRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 */
	public List<ReferenceCodes> recCountryRecordGroup() {
		return oidstghlRepository.recCountryRecordGroup();

	}

	public List<StaffMemberRoles> cgwhenNewFormInstance(String userName) {
		final List<StaffMemberRoles> returnList = oidstghlRepository.cgwhenNewFormInstance(userName);
		final String profileValue = oidstghlRepository.getProfileValue();
		returnList.forEach(action -> {
			if (action.getRoleId() != null && profileValue != null) {
				final String amount = action.getRoleId().toString();
				if (amount.equals(profileValue)) {
					action.setSealFlag("Y");
				}
			}
		});
		return returnList;
	}
}
package net.syscon.s4.sa.admin.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.StaffMemberRoles;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.OmsRoles;
import net.syscon.s4.im.beans.StaffMemberRolesCommitBean;
import net.syscon.s4.sa.admin.OumsmugaRepository;
import net.syscon.s4.sa.admin.OumsmugaService;

/**
 * Class OumsmugaServiceImpl
 */
@Service
public class OumsmugaServiceImpl extends BaseBusiness implements OumsmugaService {

	@Autowired
	private OumsmugaRepository oumsmugaRepository;

	/**
	 * Fetch the records from database table
	 * 
	 * @param searchRecord
	 */
	public List<OmsRoles> omsRoleExecuteQuery(final OmsRoles searchRecord) {
		return oumsmugaRepository.omsRoleExecuteQuery(searchRecord);
	}

	/**
	 * Fetch the records from database table
	 * 
	 * @param searchRecord
	 */
	public List<StaffMemberRoles> staffRoleExecuteQuery(final StaffMemberRoles searchRecord) {
		List<StaffMemberRoles> response = oumsmugaRepository.staffRoleExecuteQuery(searchRecord);
		for (StaffMemberRoles staffMemberRoles : response) {
			final List<StaffMembers> staffMemList = oumsmugaRepository.getStaffUserId(staffMemberRoles.getStaffId());
			staffMemberRoles.setUserId(staffMemList.get(0).getUserId());
			staffMemberRoles.setLastName(staffMemList.get(0).getLastName());
			staffMemberRoles.setFirstName(staffMemList.get(0).getFirstName());
			staffMemberRoles.setMiddleName(staffMemList.get(0).getMiddleName());
		}
		return response;

	}

	/**
	 * Insert the records from database table
	 * 
	 * @param lstSTAFF_ROLE
	 */
	@Transactional
	public StaffMemberRoles staffRoleCommit(final StaffMemberRolesCommitBean commitBean) {
		final StaffMemberRoles staffMemRoles = new StaffMemberRoles();
		int liReturn = 0;
		int totalRecords = 0;
		if (commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {
			commitBean.getDeleteList().forEach(bo -> {bo.setModifyUserId(commitBean.getCreateUserId());});
			liReturn = oumsmugaRepository.staffRoleDeleteStaffMemberRoles(commitBean.getDeleteList());
			if (liReturn == 1) {
				totalRecords = totalRecords + commitBean.getDeleteList().size();
			}
		}
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			for(StaffMemberRoles staffMemberRoles : commitBean.getInsertList()) {
				staffMemberRoles.setCreateUserId(commitBean.getCreateUserId());
			}
			liReturn = oumsmugaRepository.staffRoleInsertStaffMemberRoles(commitBean.getInsertList());
			if (liReturn == 1) {
				totalRecords = totalRecords + commitBean.getInsertList().size();
			}
		}
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			commitBean.getUpdateList().forEach(data->data.setModifyUserId(commitBean.getCreateUserId()));
			liReturn = oumsmugaRepository.staffRoleUpdateStaffMemberRoles(commitBean.getUpdateList());
			if (liReturn == 1) {
				totalRecords = totalRecords + commitBean.getUpdateList().size();
			}
		}
		staffMemRoles.setLiReturn(liReturn);
		staffMemRoles.setTotalRecords(totalRecords);
		return staffMemRoles;
	}

	/**
	 * This method is used to execute a record group
	 */
	public List<ReferenceCodes> cgfkStaffRoleDspUserIdRecordGroup() {
		return oumsmugaRepository.cgfkStaffRoleDspUserIdRecordGroup();

	}

}
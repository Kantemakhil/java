package net.syscon.s4.globalrbac.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.globalrbac.OumpersdRepository;
import net.syscon.s4.globalrbac.OumpersdService;
import net.syscon.s4.im.beans.StaffMembersCommitBean;

/**
 * Class OumpersdServiceImpl
 */
@Service
public class OumpersdServiceImpl extends BaseBusiness implements OumpersdService {

	@Autowired
	private OumpersdRepository oumpersdRepository;

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 */
	public List<StaffMembers> staffExecuteQuery(final Integer staffId) {
		return oumpersdRepository.staffExecuteQuery(staffId);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstSTAFF
	 */
	@Transactional
	public Integer staffCommit(final StaffMembersCommitBean commitBean) {
		int liReturn = 0;
		if (!commitBean.getUpdateList().isEmpty()) {
			for (StaffMembers obj : commitBean.getUpdateList()) {
				obj.setModifyUserId(commitBean.getCreateUserId());
			}
			liReturn = oumpersdRepository.staffUpdateStaffMembers(commitBean.getUpdateList());
		}
		return liReturn;
	}
}
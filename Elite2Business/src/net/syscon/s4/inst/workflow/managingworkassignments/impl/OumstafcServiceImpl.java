package net.syscon.s4.inst.workflow.managingworkassignments.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.inst.casemanagement.beans.ProgramServices;
import net.syscon.s4.inst.workflow.managingworkassignments.OumstafcRepository;
import net.syscon.s4.inst.workflow.managingworkassignments.OumstafcService;
import net.syscon.s4.sa.usersystemsecurity.beans.StaffSkills;
import net.syscon.s4.sa.usersystemsecurity.beans.StaffSkillsCommitBean;

/**
 * Class OumstafcServiceImpl
 */
@Service
public class OumstafcServiceImpl extends BaseBusiness implements OumstafcService {

	@Autowired
	private OumstafcRepository oumstafcRepository;

	/**
	 * Fetch the records from database table
	 *
	 * @param StaffSkills
	 *
	 */
	public List<StaffSkills> stskExecuteQuery(final StaffSkills searchRecord) {
		final List<StaffSkills> staffSkillsList = oumstafcRepository.stskExecuteQuery(searchRecord);
		if (staffSkillsList != null && !staffSkillsList.isEmpty()) {
			staffSkillsList.forEach(action -> action.setProgId(String.valueOf(action.getProgramId())));
		}
		return staffSkillsList;
	}

	/**
	 * Insert the records from database table
	 *
	 * @param StaffSkillsCommitBean
	 *
	 */
	@Transactional
	public Integer stskCommit(final StaffSkillsCommitBean commitBean) {
		int liReturn = 0;
		long staffSkillIdSeq;
		if (commitBean.getInsertList() != null && !commitBean.getInsertList().isEmpty()) {
			for (final StaffSkills staffSkills : commitBean.getInsertList()) {
				staffSkillIdSeq = oumstafcRepository.oumstafcStaffSkillIdSeq();
				staffSkills.setStaffSkillId(new BigDecimal(staffSkillIdSeq));
				staffSkills.setCreateUserId(commitBean.getCreateUserId());
			}
			liReturn = oumstafcRepository.stskInsertStaffSkills(commitBean.getInsertList());
			
		}

		if (commitBean.getUpdateList() != null && !commitBean.getUpdateList().isEmpty()) {
			for (final StaffSkills staffSkills : commitBean.getUpdateList()) {
				staffSkills.setModifyUserId(commitBean.getCreateUserId());
			}
			liReturn = oumstafcRepository.stskUpdateStaffSkills(commitBean.getUpdateList());
		}
		return liReturn;
	}

	/**
	 * This method is used to execute a record group
	 *
	 */
	public List<ReferenceCodes> rgStaffSkillRecordGroup() {
		return oumstafcRepository.rgStaffSkillRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 */
	public List<ProgramServices> rgProgramRecordGroup() {
		final List<ProgramServices> programServicesList = oumstafcRepository.rgProgramRecordGroup();
		if (programServicesList != null && !programServicesList.isEmpty()) {
			programServicesList.forEach(action -> action.setCode(String.valueOf(action.getProgramId())));
		}
		return programServicesList;
	}

	/**
	 * This method is used to execute a record group
	 *
	 */
	public List<ReferenceCodes> rgSubTypeRecordGroup(final String skillType) {
		return oumstafcRepository.rgSubTypeRecordGroup(skillType);

	}

}
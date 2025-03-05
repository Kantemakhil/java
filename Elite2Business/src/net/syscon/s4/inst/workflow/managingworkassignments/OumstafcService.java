package net.syscon.s4.inst.workflow.managingworkassignments;

import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.inst.casemanagement.beans.ProgramServices;
import net.syscon.s4.sa.usersystemsecurity.beans.StaffSkills;
import net.syscon.s4.sa.usersystemsecurity.beans.StaffSkillsCommitBean;

/**
 * Interface OumstafcService
 */
public interface OumstafcService {
	Integer stskCommit(StaffSkillsCommitBean commitBean);

	List<ProgramServices> rgProgramRecordGroup();

	List<ReferenceCodes> rgSubTypeRecordGroup(String skillType);

	List<StaffSkills> stskExecuteQuery(StaffSkills objStaffSkills);

	List<ReferenceCodes> rgStaffSkillRecordGroup();

}

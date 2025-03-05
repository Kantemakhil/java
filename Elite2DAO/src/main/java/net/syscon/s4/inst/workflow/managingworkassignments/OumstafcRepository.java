package net.syscon.s4.inst.workflow.managingworkassignments;

import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.inst.casemanagement.beans.ProgramServices;
import net.syscon.s4.sa.usersystemsecurity.beans.StaffSkills;

/**
 * Interface OumstafcRepository
 */
public interface OumstafcRepository {
	List<ProgramServices> rgProgramRecordGroup();

	Integer stskUpdateStaffSkills(List<StaffSkills> lstStaffSkills);

	List<ReferenceCodes> rgSubTypeRecordGroup(String skillType);

	List<StaffSkills> stskExecuteQuery(StaffSkills objStaffSkills);

	List<ReferenceCodes> rgStaffSkillRecordGroup();

	Integer stskInsertStaffSkills(List<StaffSkills> lstStaffSkills);

	Long oumstafcStaffSkillIdSeq();

}

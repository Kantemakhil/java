package net.syscon.s4.sa.usersystemsecurity;

import java.util.List;

import net.syscon.s4.common.beans.InternetAddresses;
import net.syscon.s4.common.beans.Phones;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.Areas;
import net.syscon.s4.im.beans.InternetAddressesCommitBean;
import net.syscon.s4.im.beans.PhonesCommitBean;
import net.syscon.s4.im.beans.ReferenceCodes;
import net.syscon.s4.sa.usersystemsecurity.beans.StaffSkills;
import net.syscon.s4.sa.usersystemsecurity.beans.StaffSkillsCommitBean;
import net.syscon.s4.sa.usersystemsecurity.beans.VMemberSkills;
import net.syscon.s4.sa.usersystemsecurity.beans.VMemberSkillsCommitBean;

/**
 * Interface OuisdireService
 */
public interface OuisdireService {
	List<Areas> nomRegionRgRecordGroup();

	List<InternetAddresses> vms1OnCheckDeleteMaster(InternetAddresses paramBean);

	List<ReferenceCodes> cgfkStskSubTypeRecordGroup(String subType);

	List<AgencyLocations> cgfkVmsAgyLocIdRecordGroup();

	Integer vms1Commit(VMemberSkillsCommitBean commitBean);

	List<ReferenceCodes> cgfkVmsCityRecordGroup();

	List<Phones> hmPhoneExecuteQuery(Phones objPhones);

	List<Phones> busPhoneExecuteQuery(Phones objPhones);

	List<AgencyLocations> cgfkchkVms1AgyLocIdF3(AgencyLocations paramBean);

	Integer stskCommit(StaffSkillsCommitBean commitBean);

	List<VMemberSkills> vms1ExecuteQuery(VMemberSkills objVMemberSkills);

	Integer bsPhoneCommit(PhonesCommitBean commitBean);

	Phones vms1WhenNewRecordInstance(Phones paramBean);

	List<ReferenceCodes> cgfkVmsAgencyLocationTypeRecordGroup();

	List<InternetAddresses> emailExecuteQuery(InternetAddresses object);

	List<ReferenceCodes> cgfkStskSkillTypeRecordGroup();

	List<StaffSkills> stskExecuteQuery(StaffSkills objStaffSkills);

	Integer hmPhoneCommit(PhonesCommitBean commitBean);

	Integer emailCommit(InternetAddressesCommitBean commitBean);

	List<ReferenceCodes> cgfkVmsScheduleTypeRecordGroup();

	List<ReferenceCodes> cgfkVmsSexCodeRecordGroup();

	List<ReferenceCodes> cgfkVmsPositionRecordGroup();

	List<ReferenceCodes> cgfkVmsRoleRecordGroup();

}

package net.syscon.s4.sa.usersystemsecurity;

import java.util.List;

import net.syscon.s4.common.beans.InternetAddresses;
import net.syscon.s4.common.beans.Phones;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.Areas;
import net.syscon.s4.im.beans.ReferenceCodes;
import net.syscon.s4.sa.usersystemsecurity.beans.StaffSkills;
import net.syscon.s4.sa.usersystemsecurity.beans.VMemberSkills;

/**
 * Interface OuisdireRepository
 */
public interface OuisdireRepository {
	List<Areas> nomRegionRgRecordGroup();

	List<ReferenceCodes> cgfkStskSubTypeRecordGroup(String subType);

	List<AgencyLocations> cgfkVmsAgyLocIdRecordGroup();

	List<AgencyLocations> cgfkchkVms1AgyLocIdF3c(AgencyLocations paramBean);

	List<ReferenceCodes> cgfkVmsCityRecordGroup();

	List<Phones> hmPhoneExecuteQuery(Phones objPhones);

	List<Phones> busPhoneExecuteQuery(Phones objPhones);

	ReferenceCodes postQuery(ReferenceCodes paramBean);

	Integer stskInsertStaffSkills(List<StaffSkills> lstStaffSkills);

	Phones vms1WhenNewRecordInstance(Phones paramBean);

	List<VMemberSkills> vms1ExecuteQuery(VMemberSkills objVMemberSkills);

	StaffSkills cguvchkStskPk(StaffSkills paramBean);

	List<InternetAddresses> vms1OnCheckDeleteMaster(InternetAddresses paramBean);

	List<AgencyLocations> cgfkchkVms1AgyLocIdF3(AgencyLocations paramBean);

	List<ReferenceCodes> cgfkVmsAgencyLocationTypeRecordGroup();

	Integer vms1InsertVMemberSkills(List<VMemberSkills> lstVMemberSkills);

	List<InternetAddresses> emailExecuteQuery(InternetAddresses object);

	List<ReferenceCodes> cgfkStskSkillTypeRecordGroup();

	List<StaffSkills> stskExecuteQuery(StaffSkills objStaffSkills);

	StaffSkills vms1OnCheckDeleteMaster(StaffSkills paramBean);

	List<ReferenceCodes> cgfkVmsScheduleTypeRecordGroup();

	List<ReferenceCodes> cgfkVmsSexCodeRecordGroup();

	List<ReferenceCodes> cgfkVmsPositionRecordGroup();

	List<ReferenceCodes> cgfkVmsRoleRecordGroup();

	String getcityCode(String agnyLocId);

}

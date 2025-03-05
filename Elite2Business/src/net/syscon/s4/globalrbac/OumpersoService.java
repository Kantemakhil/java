package net.syscon.s4.globalrbac;

import java.util.Date;
import java.util.List;

import net.syscon.s4.common.beans.Images;
import net.syscon.s4.common.beans.InternetAddresses;
import net.syscon.s4.common.beans.Phones;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.im.beans.ImagesCommitBean;
import net.syscon.s4.im.beans.InternetAddressesCommitBean;
import net.syscon.s4.im.beans.PhonesCommitBean;
import net.syscon.s4.im.beans.StaffMembersCommitBean;
import net.syscon.s4.im.beans.TagImages;
import net.syscon.s4.im.beans.VStaffAddresses;
import net.syscon.s4.im.beans.VStaffAddressesCommitBean;

/**
 * Interface OumpersoService
 */
public interface OumpersoService {
	List<Images> imageExecuteQuery(Images objImages);

	List<Phones> addrPhonesExecuteQuery(Phones objPhones);

	List<ReferenceCodes> rgPersonnelTypeRecordGroup();

	Object stfPhonesPreInsert();

	String staffCommit(StaffMembersCommitBean commitBean);

	Object staffPreInsert();

	List<VStaffAddresses> vStfAddrExecuteQuery(VStaffAddresses objVStfAddr);

	Integer emailAddrCommit(InternetAddressesCommitBean commitBean);

	Object emailAddrPreInsert();

	Integer imageCommit(ImagesCommitBean commitBean);

	List<ReferenceCodes> rgPhoneTypeRecordGroup();

	List<ReferenceCodes> rgStatusRecordGroup();

	List<Phones> vStfAddrOnCheckDeleteMaster(Phones paramBean);

	Integer vStfAddrCommit(VStaffAddressesCommitBean commitBean);

	List<ReferenceCodes> rgPositionRecordGroup();

	List<TagImages> staffOnCheckDeleteMaster(TagImages paramBean);

	List<ReferenceCodes> rgSuffixRecordGroup();

	Integer stfPhonesCommit(PhonesCommitBean commitBean);

	List<InternetAddresses> emailAddrExecuteQuery(InternetAddresses objIntAddresses);

	Object addrPhonesPreInsert();

	List<ReferenceCodes> rgSexCodeRecordGroup();

	Integer addrPhonesCommit(PhonesCommitBean commitBean);

	List<StaffMembers> staffExecuteQuery(StaffMembers objStaffMembers);
	
	List<StaffMembers> getStaffDetails(Date fromDate);

}

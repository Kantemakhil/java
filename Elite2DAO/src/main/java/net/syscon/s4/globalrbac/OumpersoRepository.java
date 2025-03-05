package net.syscon.s4.globalrbac;
import java.util.Date;
import java.util.List;

import net.syscon.s4.common.beans.Images;
import net.syscon.s4.common.beans.InternetAddresses;
import net.syscon.s4.common.beans.Phones;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.im.beans.TagImages;
import net.syscon.s4.im.beans.VStaffAddresses;
/**
 * Interface OumpersoRepository
 */
public interface OumpersoRepository {
	List<Images> imageExecuteQuery(Images objImages);

	List<Phones> addrPhonesExecuteQuery(Phones objPhones);

	Object staffPreInsert();

	String staffUpdateStaffMembers(List<StaffMembers> lstStaffMembers);

	List<Phones> vStfAddrOnCheckDeleteMaster(Phones paramBean);

	List<ReferenceCodes> rgPersonnelTypeRecordGroup();

	List<InternetAddresses> staffOnCheckDeleteMaster(InternetAddresses paramBean);

	List<VStaffAddresses> vStfAddrExecuteQuery(VStaffAddresses objVStfAddr);

	List<ReferenceCodes> rgPhoneTypeRecordGroup();

	String staffInsertStaffMembers(List<StaffMembers> lstStaffMembers);

	List<ReferenceCodes> rgPositionRecordGroup();

	List<ReferenceCodes> rgSuffixRecordGroup();

	Integer emailAddrUpdateInternetAddresses(List<InternetAddresses> lstIntAddr);

	List<InternetAddresses> emailAddrExecuteQuery(InternetAddresses objIntAddr);

	Integer emailAddrDeleteInternetAddresses(List<InternetAddresses> lstIntAddr);

	List<VStaffAddresses> staffOnCheckDeleteMaster(VStaffAddresses paramBean);

	Integer addrPhonesInsertPhones(List<Phones> lstPhones);

	Object stfPhonesPreInsert();

	List<Phones> staffOnCheckDeleteMaster(Phones paramBean);

	List<ReferenceCodes> rgStatusRecordGroup();

	Object addrPhonesPreInsert();

	Integer addrPhonesDeletePhones(List<Phones> lstPhones);

	Object emailAddrPreInsert();

	Integer emailAddrInsertInternetAddresses(List<InternetAddresses> lstIntAddr);

	Integer addrPhonesUpdatePhones(List<Phones> lstPhones);

	List<TagImages> staffOnCheckDeleteMaster(TagImages paramBean);

	List<ReferenceCodes> rgSexCodeRecordGroup();

	List<StaffMembers> staffExecuteQuery(StaffMembers objStaffMembers);
	
	List<StaffMembers> getStaffDetails(Date fromDate);
	
	Phones getPhonesOldObject(Phones object);
	
	String checkMailId(int staffId);
	
}

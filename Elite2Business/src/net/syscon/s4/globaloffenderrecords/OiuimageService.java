package net.syscon.s4.globaloffenderrecords;

import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.StaffMemberRoles;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.beans.SystemProfilesCommitBean;
import net.syscon.s4.im.beans.ImageOriginals;
import net.syscon.s4.im.beans.ImageOriginalsCommitBean;
import net.syscon.s4.im.beans.ImageProperties;
import net.syscon.s4.im.beans.ImagePropertiesCommitBean;
import net.syscon.s4.im.beans.Images;
import net.syscon.s4.im.beans.ImagesCommitBean;
import net.syscon.s4.im.beans.ModulePrivileges;
import net.syscon.s4.im.beans.OmsModules;
import net.syscon.s4.im.beans.StaffMembersCommitBean;
import net.syscon.s4.inst.booking.beans.Persons;
import net.syscon.s4.inst.booking.beans.PersonsCommitBean;


/**
 * Interface OiuimageService
 */
public interface OiuimageService {

	List<ImageOriginals> imageOriginalsExecuteQuery(ImageOriginals objImageOriginals);

	List<ReferenceCodes> rgImagePropertiesRecordGroup();

	Integer staffMembersCommit(StaffMembersCommitBean commitBean);

	Integer imagePropertiesCommit(ImagePropertiesCommitBean commitBean);

	Integer personsCommit(PersonsCommitBean commitBean);

	List<OmsModules> rgReportRecordGroup();

	List<Persons> personsExecuteQuery(Persons objPersons);

	List<ReferenceCodes> rgImageViewTypeRecordGroup();

	List<Images> imagesExecuteQuery(Images objImages);

	Integer imagesCommit(ImagesCommitBean commitBean);

	List<StaffMembers> staffMembersExecuteQuery(StaffMembers objStaffMembers);

	List<ReferenceCodes> rgDummyImageViewTypeRecordGroup();

	List<SystemProfiles> sysPflExecuteQuery(SystemProfiles objSystemProfiles);

	List<ImageProperties> imagePropertiesExecuteQuery(ImageProperties imageProperties);

	List<ReferenceCodes> getImageOicCodeDescription();
	
	Integer imageOriginalsUpdateImageOriginals(ImageOriginalsCommitBean commitBean);
	
	Long getNextImageId();

	String rgGetCode(String code);

	Integer allowDelete(String userName);

	Integer checkUserRole(String moduleName, String userName);

	String pptyGetDescription(String code);

}

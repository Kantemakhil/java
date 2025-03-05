package net.syscon.s4.globaloffenderrecords;

import java.sql.SQLException;
import java.util.List;

import net.syscon.s4.im.beans.Images;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.StaffMemberRoles;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.im.beans.Dual;
import net.syscon.s4.im.beans.ImageOriginals;
import net.syscon.s4.im.beans.ImageOriginalsCommitBean;
import net.syscon.s4.im.beans.ImageProperties;
import net.syscon.s4.im.beans.ModulePrivileges;
import net.syscon.s4.im.beans.OmsModules;
import net.syscon.s4.inst.booking.beans.Persons;

/**
 * Interface OiuimageRepository
 * 
 * @author Arkin Software Technologies
 * @version 1.0
 */
public interface OiuimageRepository {
	Images disableButtons(Images paramBean);

	ReferenceCodes imagePropertiesPostQuery(ReferenceCodes paramBean);

	List<ImageOriginals> imageOriginalsExecuteQuery(ImageOriginals objImageOriginals);

	List<ImageProperties> imagesOnCheckDeleteMaster(ImageProperties paramBean);

	List<Images> imagesKeyDelrec(Images paramBean);

	List<ReferenceCodes> rgImagePropertiesRecordGroup();

	Integer imagePropertiesDeleteImageProperties(List<ImageProperties> lstImageProperties);

	Integer imagesInsertImages(List<Images> lstImages);

	ReferenceCodes initializeImageRecord(ReferenceCodes paramBean);

	ReferenceCodes imagesWhenNewRecordInstance(ReferenceCodes paramBean);

	SystemProfiles populateStgGroup(SystemProfiles paramBean);

	List<OmsModules> rgReportRecordGroup();

	List<Persons> personsExecuteQuery(Persons objPersons);

	List<ReferenceCodes> rgImageViewTypeRecordGroup();

	ReferenceCodes imagesPostQuery(ReferenceCodes paramBean);

	List<Images> imagesExecuteQuery(Images objImages);

	Integer imageOriginalsDeleteImageOriginals(List<ImageOriginals> lstImageOriginals);
	
	Integer imageOriginalsUpdateImageOriginals(List<ImageOriginals> lstImageOriginals);

	List<SystemProfiles> readProfile(SystemProfiles paramBean);

	Integer staffMembersInsertStaffMembers(List<StaffMembers> lstStaffMembers);

	Integer imagesDeleteImages(List<Images> lstImages);

	Integer staffMembersUpdateStaffMembers(List<StaffMembers> lstStaffMembers);

	Integer staffMembersDeleteStaffMembers(List<StaffMembers> lstStaffMembers);

	List<Images> controlOneDefaultImage(Images paramBean);

	Integer imagepropertiesInsertImageProperties(List<ImageProperties> lstImageProperties);

	List<StaffMembers> staffMembersExecuteQuery(StaffMembers objStaffMembers);

	Integer imagesUpdateImages(List<Images> lstImages);

	ImageOriginals imagesOnCheckDeleteMaster(ImageOriginals paramBean);

	List<ReferenceCodes> rgDummyImageViewTypeRecordGroup();

	List<SystemProfiles> sysPflExecuteQuery(SystemProfiles objSystemProfiles);

	SystemProfiles runReportOnTheWeb(SystemProfiles paramBean);

	List<ImageProperties> imagePropertiesExecuteQuery(ImageProperties objImageProperties);

	Integer imagePropertiesUpdateImageProperties(List<ImageProperties> lstImageProperties);

	Long getNextImageId();

	Integer updateActiveFlagInImages(List<Images> tableData);

	Integer omsOwnerEliteImagingSaveBlob(String table, String column, String where);

	String getOiiptranPptyCode(String imageViewType);

	String getOiiptranPptyDescription(String imageViewType);

	List<ReferenceCodes> getImageOicCodeDescription();

	List<ReferenceCodes> imageGetPropertyDecription(String property);

	Integer getImagePropertyId();

	String rgGetCode(String code);

	Integer allowDelete(String userName);

	Integer checkUserRole(String moduleName, String userName);

	

}

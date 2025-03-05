package net.syscon.s4.globaloffenderrecords;

import java.util.List;

import javax.validation.Valid;

import net.syscon.s4.common.beans.OffenderBookings;
import net.syscon.s4.common.beans.OffenderFingerprints;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.beans.VHeaderBlock;
import net.syscon.s4.common.beans.VHeaderBlock2;
import net.syscon.s4.common.beans.VPropertyHeaderBlock;
import net.syscon.s4.im.beans.Images;
import net.syscon.s4.im.beans.OffenderPhysicalAttributes;
import net.syscon.s4.im.beans.OffenderProfileDetails;
import net.syscon.s4.im.beans.Offenders;
import net.syscon.s4.im.beans.OmsModules;
import net.syscon.s4.im.beans.ProfileCodes;
import net.syscon.s4.im.beans.ProfileTypes;
import net.syscon.s4.im.beans.ReferenceCodes;
import net.syscon.s4.im.beans.TagSearchGetOffenderIdentifiers;
import net.syscon.s4.im.beans.TagSearchGetOffenderRecords;
import net.syscon.s4.im.beans.TagSearchGetPartialRecords;
import net.syscon.s4.im.beans.TagSearchPopulateOffDetails;
import net.syscon.s4.im.beans.VTrustHeader;

/**
 * Interface OsiosearService
 */
public interface OsiosearService {

	List<ReferenceCodes> rgSearchTypeRecordGroup(ReferenceCodes referenceCodes);

	List<ReferenceCodes> rgIdentifierTypeRecordGroup(ReferenceCodes referenceCodes);

	List<ReferenceCodes> rgGenderRecordGroup(ReferenceCodes referenceCodes);

	List<ReferenceCodes> rgCrtLocationRecordGroup(String caseLoadId);

	List<ProfileCodes> profCodeDescpd(final ProfileCodes searchBean);

	List<ReferenceCodes> setInitialSearchTypeSearchCur(String const0);

	List<TagSearchGetOffenderIdentifiers> offIdExecuteQuery(
			TagSearchGetOffenderIdentifiers objTagSearchGetOffenderIdentifiers);

	List<OffenderBookings> searchResultsWhenNewItemInstanceLvobi(String offenderId);

	List<TagSearchGetPartialRecords> psOffNameExecuteQuery(TagSearchGetOffenderRecords objTagSearchGetPartialRecords);

	String getLatestBooking(String rootOffenderId);

	List<String> captureImageFindMarkImageCur(String imageObjectIid, String imageObjectType, String imageObjectSeq);

	List<String> profCodeDescp(String profileType);

	List<OffenderFingerprints> searchResultsWhenNewRecordInstancecOffFingerPrints(String const0);

	List<OmsModules> createFormGlobals(String const0);

	List<Images> imageExecuteQuery(Images searchBean);
	
	List<VHeaderBlock> offbkgGlobalQueryQueue(final VHeaderBlock searchBean);

	List<TagSearchGetOffenderRecords> searchResultsExecuteQuery(
			TagSearchGetOffenderRecords objTagSearchGetOffenderRecords);

	List<SystemProfiles> captureImageFindImagingForm(String const0);

	SystemProfiles callToShowFingerprintOld(String const0);

	Offenders searchResultsPostQuerynameTypeCur(String offenderId);

	List<OffenderBookings> getOffenderBookIdGetbookId(String rootOffenderId, String rootOffenderIdd);

	List<Offenders> populateOffDetailsBlocknameTypeCur(String rootoffenderId,String offenderId);

	List<ProfileTypes> profTypeDescpc(String profileType);

	List<String> captureImageFindImageCur(String imageObjectId, String imageObjectType);

	List<OffenderPhysicalAttributes> populateOffDetailsBlockattrCur(String offenderBookId);

	List<TagSearchPopulateOffDetails> populateOffDetails(TagSearchPopulateOffDetails searchBean);

	List<VHeaderBlock> offbkgGlobalQuery(VHeaderBlock searchBean);

	List<VHeaderBlock2> searchVHeaderBlock2(VHeaderBlock2 searchBean);

	List<OffenderProfileDetails> offProfDtlsExecuteQuery(OffenderProfileDetails searchRecord);
	 List<VPropertyHeaderBlock> offbkgVPHeadGlobalQuery(final @Valid VPropertyHeaderBlock searchBean);

	String getCaptureImage();

	List<VTrustHeader> offbkgVTrustHeadGlobalQuery(VTrustHeader param);
	
	Long getIntCorrelationIdSeq();
		
	List<TagSearchGetOffenderRecords> getDataFromJisCommonSystem(Long intCorrelationId,String nameSearchType,String moduleName);
	
	Long getCorrelationId();
	
	String getRecentOffenders(Long offenderBookId, String user);
	
	String searchTypeCodeRetrieve();
	
	void setGlobalOffSearchAuditLogData(List<TagSearchGetOffenderRecords> searchResult,TagSearchGetOffenderRecords searchBean, String moduleName);
	
	void auditLogInjector(List<VHeaderBlock> resultSet, VHeaderBlock inputParameters , String moduleName,String userId);

	Integer getSystemProfileUserAccValue(String userId);

	<T> void auditLogInjProcessResult(List<T> resultList, T searchParam, String moduleName, String userId);

}

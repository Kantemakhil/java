package net.syscon.s4.globaloffenderrecords;

import java.math.BigDecimal;
import java.util.List;

import net.syscon.s4.common.beans.Caseloads;
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
 * Interface OsiosearRepository
 */
public interface OsiosearRepository {
	List<ReferenceCodes> rgSearchTypeRecordGroup(ReferenceCodes referenceCodes);

	List<ReferenceCodes> rgIdentifierTypeRecordGroup(ReferenceCodes referenceCodes);

	List<ReferenceCodes> rgGenderRecordGroup(ReferenceCodes referenceCodes);

	List<ReferenceCodes> rgCrtLocationRecordGroup(String caseLoadId);

	List<TagSearchGetOffenderIdentifiers> offIdExecuteQuery(TagSearchGetOffenderIdentifiers objTagSearchGetOffenderIdentifiers);

	String getLatestBooking(String rootOffenderId);

	List<ProfileCodes> profCodeDescpd(ProfileCodes searchBean);

	List<String> captureImageFindImageCur(String imageObjectId, String imageObjectType);

	List<TagSearchGetPartialRecords> psOffNameExecuteQuery(TagSearchGetOffenderRecords objTagSearchGetPartialRecords);

	List<OffenderPhysicalAttributes> populateOffDetailsBlockattrCur(String offenderBookId);

	List<OffenderBookings> getOffenderBookIdGetbookId(String rootOffenderId, String rootOffenderIdd);

	List<OmsModules> createFormGlobals(String const0);

	List<OffenderBookings> searchResultsWhenNewItemInstanceLvobi(String offenderId);

	List<ProfileTypes> profTypeDescpc(String profileType);

	List<ReferenceCodes> setInitialSearchTypeSearchCur(String const0);

	SystemProfiles callToShowFingerprintOld(String const0);

	List<VHeaderBlock2> searchVHeaderBlock2ByOffenderIds(List<String> offenderIds,String userId) ;

	public VHeaderBlock getVheaderBlockDataFromOffenderId(final VHeaderBlock paramBean);

	List<Images> imageExecuteQuery(Images searchBean);

	List<TagSearchGetOffenderRecords> searchResultsExecuteQuery(TagSearchGetOffenderRecords objTagSearchGetOffenderRecords);

	List<String> profCodeDescp(String profileType);

	List<SystemProfiles> captureImageFindImagingForm(String const0);

	Offenders searchResultsPostQuerynameTypeCur(String offenderId);

	List<String> captureImageFindMarkImageCur(String imageObjectId, String imageObjectType, String imageObjectSeq);

	List<OffenderFingerprints> searchResultsWhenNewRecordInstancecOffFingerPrints(String const0);

	List<Offenders> populateOffDetailsBlocknameTypeCur(String rootoffenderId,String offenderId);

	List<TagSearchPopulateOffDetails> populateOffDetails(TagSearchPopulateOffDetails searchBean);

	List<VHeaderBlock> offbkgGlobalQuery(VHeaderBlock searchBean);

	List<VHeaderBlock2> searchVHeaderBlock2(VHeaderBlock2 searchBean);

	List<OffenderProfileDetails> offProfDtlsExecuteQuery(OffenderProfileDetails searchRecord);

	List<VPropertyHeaderBlock> offbkgVPHeadGlobalQuery(VPropertyHeaderBlock searchBean);

	Caseloads caseloadTypeData(Caseloads paramBean);

	VHeaderBlock getVheaderBlockData(VHeaderBlock vhbBean);

	String getCaptureImage();

	List<VTrustHeader> offbkgVTrustHeadGlobalQuery(VTrustHeader param,String whereClause);

	String getOffenderIdDisplay(final TagSearchGetOffenderRecords objSearchDao);

	String getDescription(String caseloadId,final Long offenderId,String code);

	List<VHeaderBlock> getOffenderDetials(Integer offenderBookId,String userName);
	
	OffenderBookings getOffenderBookingDetails(BigDecimal offenderBookId);
	
	String getOffenderBookingNumber(String offenderIdDisplay,String userId);
	
	List<VHeaderBlock> getOffenderDetails( VHeaderBlock searchBean);

	Long getIntCorrelationIdSeq();
	
	TagSearchGetOffenderRecords getDataFromJisCommonSystem(Long intCorrelationId);
	
	String getIdentifierData(BigDecimal offenderId);
	
	Integer removeOldDataExternalSystemResponse();
	
	Long getCorrelationId();
	
	String getRecentOffenders(Long offenderBookId, String user);
	
	String searchTypeCodeRetrieve();

}

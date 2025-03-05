package net.syscon.s4.inst.demographicsbiometrics;

import java.util.List;

import net.syscon.s4.common.beans.OffenderIdentifyingMark;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.im.beans.OffenderPhysicalAttributes;
import net.syscon.s4.im.beans.OffenderProfileDetails;
import net.syscon.s4.im.beans.Offenders;
import net.syscon.s4.im.beans.ProfileCodes;
import net.syscon.s4.im.beans.ProfileTypes;

public interface OidpidenRepository {
	List<ReferenceCodes> rgRaceCodeRecordGroup();

	List<ProfileCodes> rgProfileRecordGroup(String profileType);

	List<ReferenceCodes> rgMarkTypeRecordGroup();

	List<ReferenceCodes> rgBodyPartRecordGroup();

	List<ReferenceCodes> rgPartOrientRecordGroup();

	List<ReferenceCodes> rgSideRecordGroup();

	Integer offRaceUpdateOffenders(List<Offenders> lstOffenders);

	Offenders offBkgPreDelete(Offenders paramBean);

	List<OffenderProfileDetails> offPdSearchOffenderProfileDetails(OffenderProfileDetails objOffenderProfileDetails);

	List<OffenderPhysicalAttributes> offPaSearchOffenderPhysicalAttributes(
			OffenderPhysicalAttributes objOffenderPhysicalAttributes);

	List<Object> nbtDetailDescWhenValidateItemprofileTypes(ProfileTypes paramBean);

	List<Object> cgfkchkOffPdOffPdPflCodvalueTypeCur(ProfileTypes paramBean);

	Integer offPdUpdateOffenderProfileDetails(List<OffenderProfileDetails> lstOffenderProfileDetails);

	Integer updateOffenderPhysicalAttributes(List<OffenderPhysicalAttributes> lstOffenderPhysicalAttributes);

	Integer updateOffenderIdentifyingMarks(List<OffenderIdentifyingMark> lstOffenderIdentifyingMarks);

	List<OffenderIdentifyingMark> offImSearchOffenderIdentifyingMarks(
			OffenderIdentifyingMark objOffenderIdentifyingMarks);

	ProfileTypes offPdWhenNewRecordInstanceprofileTypes(ProfileTypes paramBean);

	ProfileTypes offPdPostQuerycharDescCur(OffenderProfileDetails paramBean);

	Integer getOffenderIdentifyingMarkIdMarkSeq(String offenderBookId);

	Integer insertOffenderIdentifyingMarks(List<OffenderIdentifyingMark> lstOffenderIdentifyingMarks);

	Integer deleteOffenderPhysicalAttributes(List<OffenderPhysicalAttributes> lstOffenderPhysicalAttributes);

	List<Offenders> offRaceSearchOffenders(Offenders objOffenders);

	Integer deleteOffenderIdentifyingMarks(List<OffenderIdentifyingMark> lstOffenderIdentifyingMarks);

	ProfileCodes cgfkchkOffPdOffPdPflCodc(OffenderProfileDetails paramBean);

	ProfileTypes nbtDetailDescWhenNewItemInstanceprofileTypes(ProfileTypes paramBean);

	Integer insertOffenderPhysicalAttributes(final List<OffenderPhysicalAttributes> lstOffenderPhysicalAttributes);

	List<SystemProfiles> callToShowFingerprintOld(SystemProfiles paramBean);

	Integer getOffenderBook(Long offenderBookId);

	String checkImage(String imageObjType, String offenderBookId, String markType, String bodypart, String objectSeq);

	Integer gettingListseq(String offenderBookId);

	Integer insertOffenderprofileDetails(String offenderBookId, String userName);

	List<OffenderProfileDetails> listofProfileTypes(String offenderBookId, String profileCategory);

	List<OffenderProfileDetails> insertProfileDetails(String offenderBookId, String caseloadType,
			String profileCategory, List<String> profileTypeList, String userName);

	String getDescriptionofMarkType(String markType);

	String getDescriptionofBodyPart(String bodyPartCode);

	List<Offenders> getoldlistoffenders(Long offenderId);

}

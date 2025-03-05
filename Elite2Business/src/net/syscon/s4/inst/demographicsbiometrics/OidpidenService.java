package net.syscon.s4.inst.demographicsbiometrics;

import java.util.List;

import net.syscon.s4.common.beans.OffenderIdentifyingMark;
import net.syscon.s4.common.beans.OffenderIdentifyingMarksCommitBean;
import net.syscon.s4.common.beans.OffenderPhysicalAttributesCommitBean;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.im.beans.OffenderPhysicalAttributes;
import net.syscon.s4.im.beans.OffenderProfileDetails;
import net.syscon.s4.im.beans.OffenderProfileDetailsCommitBean;
import net.syscon.s4.im.beans.Offenders;
import net.syscon.s4.im.beans.OffendersCommitBean;
import net.syscon.s4.im.beans.ProfileCodes;
import net.syscon.s4.im.beans.ProfileTypes;

public interface OidpidenService {

	Integer offRaceUpdateOffenders( OffendersCommitBean commitBean);

	Offenders offBkgPreDelete(Offenders paramBean);

	List<OffenderProfileDetails> offPdSearchOffenderProfileDetails(OffenderProfileDetails objOffenderProfileDetails);

	List<OffenderPhysicalAttributes> offPaSearchOffenderPhysicalAttributes(
			OffenderPhysicalAttributes objOffenderPhysicalAttributes);

	Integer offPdUpdateOffenderProfileDetails(OffenderProfileDetailsCommitBean lstOffenderProfileDetails);

	ProfileTypes offPdWhenNewRecordInstanceprofileTypes(ProfileTypes paramBean);

	Integer updateOffenderPhysicalAttributes(List<OffenderPhysicalAttributes> lstOffenderPhysicalAttributes);

	Integer updateOffenderIdentifyingMarks(List<OffenderIdentifyingMark> lstOffenderIdentifyingMarks);

	List<Object> cgfkchkOffPdOffPdPflCodvalueTypeCur(ProfileTypes paramBean);

	List<OffenderIdentifyingMark> offImSearchOffenderIdentifyingMarks(
			OffenderIdentifyingMark objOffenderIdentifyingMarks, String imageObjectType);

	ProfileCodes cgfkchkOffPdOffPdPflCodc(OffenderProfileDetails paramBean);

	Integer getOffenderIdentifyingMarkIdMarkSeq(String offenderBookId);

	Integer insertOffenderIdentifyingMarks(List<OffenderIdentifyingMark> lstOffenderIdentifyingMarks);

	Integer insertUpdateDeleteOffenderIdentifyingMarks(OffenderIdentifyingMarksCommitBean commitBean);

	Integer insertUpdateDeleteOffenderPhysicalAttributes(OffenderPhysicalAttributesCommitBean commitBean);

	List<Object> nbtDetailDescWhenValidateItemprofileTypes(ProfileTypes paramBean);

	Integer deleteOffenderPhysicalAttributes(List<OffenderPhysicalAttributes> lstOffenderPhysicalAttributes);

	List<SystemProfiles> callToShowFingerprintOld(SystemProfiles paramBean);

	List<Offenders> offRaceSearchOffenders(Offenders objOffenders);

	Integer deleteOffenderIdentifyingMarks(List<OffenderIdentifyingMark> lstOffenderIdentifyingMarks);

	ProfileTypes nbtDetailDescWhenNewItemInstanceprofileTypes(ProfileTypes paramBean);

	ProfileTypes offPdPostQuerycharDescCur(OffenderProfileDetails paramBean);

	Integer insertOffenderPhysicalAttributes(List<OffenderPhysicalAttributes> lstOffenderPhysicalAttributes);

	List<ProfileCodes> rgProfileRecordGroup(String profileTypes);

	List<ReferenceCodes> rgMarkTypeRecordGroup();

	List<ReferenceCodes> rgSideRecordGroup();

	List<ReferenceCodes> rgBodyPartRecordGroup();

	List<ReferenceCodes> rgPartOrientRecordGroup();

	List<ReferenceCodes> rgRaceCodeRecordGroup();

	String checkImage(String imageObjType, String offenderBookId, String markType, String bodypart, String objectSeq);
	
	
}

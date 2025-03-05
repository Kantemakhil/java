package net.syscon.s4.inst.demographicsbiometrics;

import java.util.List;

import net.syscon.s4.common.beans.Addresses;
import net.syscon.s4.common.beans.InternetAddresses;
import net.syscon.s4.common.beans.OffenderEducations;
import net.syscon.s4.common.beans.OffenderEmployments;
import net.syscon.s4.common.beans.Phones;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.beans.VOffenderEducationAddresses;
import net.syscon.s4.common.beans.VOffenderEmployAddresses;

/**
 * Interface OcdedempRepository
 */
public interface OcdedempRepository {

	List<ReferenceCodes> eduSchedRgRgroup();

	List<ReferenceCodes> payPeriodRgRgroup();

	List<ReferenceCodes> occupationRgRgroup();

	List<ReferenceCodes> scheduleTypeRgRgroup();

	List<ReferenceCodes> employStsRgRgroup();

	List<ReferenceCodes> studyAreaRgRgroup();

	List<ReferenceCodes> eduLevelRgRgroup();

	Integer offEducationsInsertOffenderEducations(List<OffenderEducations> lstOffenderEducations);

	Integer offEducationsUpdateOffenderEducations(List<OffenderEducations> lstOffenderEducations);

	Integer offEmploymentsDeleteOffenderEmployments(List<OffenderEmployments> lstOffenderEmployments);

	Integer offEmploymentsPreInsert(OffenderEmployments paramBean);

	List<OffenderEducations> offEducationsSearchOffenderEducations(OffenderEducations searchBean);

	Integer offEducationsPreDeleteInternetAddress(InternetAddresses paramBean);

	Integer offEmploymentsUpdateOffenderEmployments(List<OffenderEmployments> lstOffenderEmployments);

	List<String> offBkgOnCheckDeleteMasteroffEducationsCur(OffenderEducations paramBean);

	List<OffenderEmployments> offEmploymentsSearchOffenderEmployments(OffenderEmployments searchBean);

	List<String> offEmploymentsOnCheckDeleteMastervOffEmpAddrCur(VOffenderEmployAddresses paramBean);

	Integer offEmploymentsPreDeleteAddress(Addresses paramBean);

	List<VOffenderEducationAddresses> vOffEduAddrSearchVOffenderEducationAddresses(
			VOffenderEducationAddresses searchBean);

	List<VOffenderEmployAddresses> vOffEmpAddrSearchVOffenderEmployAddresses(VOffenderEmployAddresses searchBean);

	Integer offEducationsPreDeletePhones(Phones paramBean);

	List<String> offEducationsOnCheckDeleteMastervOffEduAddrCur(VOffenderEducationAddresses paramBean);

	Integer offEmploymentsPreDeletePhones(Phones paramBean);

	Integer offEducationsDeleteOffenderEducations(List<OffenderEducations> lstOffenderEducations);

	Integer offEducationsPreDeleteAddress(Addresses paramBean);

	Integer offEmploymentsInsertOffenderEmployments(List<OffenderEmployments> lstOffenderEmployments);

	Integer offEducationsPreInsert(OffenderEducations paramBean);

	List<String> offBkgOnCheckDeleteMasteroffEmploymentsCur(OffenderEmployments paramBean);

	Integer offEmploymentsPreDeleteInternetAddress(InternetAddresses paramBean);

	List<ReferenceCodes> rgStreetDirRgroup();

	List<ReferenceCodes> rgCityRgroup();

	List<ReferenceCodes> rgProvStateCodeRgroup();

	List<ReferenceCodes> rgCountryRgroup();

	List<ReferenceCodes> rgTypeRgroup();

	List<ReferenceCodes> rgSpecialNeedsRgroup();

	List<Phones> addressKeyDelrecphoneEx(Phones paramBean);

	List<Addresses> addressSearchAddresses(Addresses paramBean);

	SystemProfiles citySystemProfilecityProfileCur();

	ReferenceCodes addressWhenCreateRecordgetCountryDesc();

	Object validateCityInfogetCityDescription();

	Integer addressInsertAddresses(List<Addresses> lstAddresses);

	Integer addressDeleteAddresses(List<Addresses> lstAddresses);

	List<ReferenceCodes> validateCityInfogetCityCode();

	Integer addressUpdateAddresses(List<Addresses> lstAddresses);

	Object preInsertaddressIdCur();

	List<ReferenceCodes> rgCountyRgroup();

	Integer offEducationsgetMaxBookIdEduSeq(Long offenderBookId);

	Integer offEmploymentsgetMaxBookIdEmpSeq(Long offenderBookId);

}

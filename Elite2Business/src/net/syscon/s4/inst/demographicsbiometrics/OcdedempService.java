package net.syscon.s4.inst.demographicsbiometrics;

import java.util.List;

import net.syscon.s4.common.beans.Addresses;
import net.syscon.s4.common.beans.AddressesCommitBean;
import net.syscon.s4.common.beans.InternetAddresses;
import net.syscon.s4.common.beans.OffenderEducations;
import net.syscon.s4.common.beans.OffenderEducationsCommitBean;
import net.syscon.s4.common.beans.OffenderEmployments;
import net.syscon.s4.common.beans.OffenderEmploymentsCommitBean;
import net.syscon.s4.common.beans.Phones;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.beans.VOffenderEducationAddresses;
import net.syscon.s4.common.beans.VOffenderEmployAddresses;

/**
 * Interface OcdedempService
 */
public interface OcdedempService {

	List<ReferenceCodes> eduSchedRgRgroup();

	List<ReferenceCodes> payPeriodRgRgroup();

	List<ReferenceCodes> occupationRgRgroup();

	List<ReferenceCodes> scheduleTypeRgRgroup();

	List<ReferenceCodes> employStsRgRgroup();

	List<ReferenceCodes> studyAreaRgRgroup();

	List<ReferenceCodes> eduLevelRgRgroup();

	List<String> offEducationsOnCheckDeleteMastervOffEduAddrCur(VOffenderEducationAddresses paramBean);

	Integer offEducationsInsertOffenderEducations(List<OffenderEducations> commitBean);

	Integer offEducationsUpdateOffenderEducations(List<OffenderEducations> lstOffenderEducations);

	Integer offEmploymentsDeleteOffenderEmployments(List<OffenderEmployments> lstOffenderEmployments);

	List<OffenderEducations> offEducationsSearchOffenderEducations(OffenderEducations searchBean);

	List<String> offBkgOnCheckDeleteMasteroffEducationsCur(OffenderEducations paramBean);

	Integer offEmploymentsUpdateOffenderEmployments(List<OffenderEmployments> lstOffenderEmployments);

	List<OffenderEmployments> offEmploymentsSearchOffenderEmployments(OffenderEmployments searchBean);

	Integer offEmploymentsPreInsert(OffenderEmployments paramBean);

	Integer offEducationsPreDeletePhones(Phones paramBean);

	List<String> offEmploymentsOnCheckDeleteMastervOffEmpAddrCur(VOffenderEmployAddresses paramBean);

	Integer offEmploymentsPreDeleteInternetAddress(InternetAddresses paramBean);

	List<VOffenderEducationAddresses> vOffEduAddrSearchVOffenderEducationAddresses(
			VOffenderEducationAddresses searchBean);

	List<VOffenderEmployAddresses> vOffEmpAddrSearchVOffenderEmployAddresses(VOffenderEmployAddresses searchBean);

	Integer offEducationsDeleteOffenderEducations(List<OffenderEducations> lstOffenderEducations);

	Integer offEmploymentsInsertOffenderEmployments(List<OffenderEmployments> lstOffenderEmployments);

	Integer offEducationsPreDeleteInternetAddress(InternetAddresses paramBean);

	Integer offEducationsPreInsert(OffenderEducations paramBean);

	Integer offEmploymentsPreDeletePhones(Phones paramBean);

	List<String> offBkgOnCheckDeleteMasteroffEmploymentsCur(OffenderEmployments paramBean);

	Integer offEducationsPreDeleteAddress(Addresses paramBean);

	Integer offEmploymentsPreDeleteAddress(Addresses paramBean);

	List<ReferenceCodes> rgStreetDirRgroup();

	List<ReferenceCodes> rgCityRgroup();

	List<ReferenceCodes> rgProvStateCodeRgroup();

	List<ReferenceCodes> rgCountryRgroup();

	List<ReferenceCodes> rgTypeRgroup();

	List<ReferenceCodes> rgSpecialNeedsRgroup();

	List<ReferenceCodes> validateCityInfogetCityCode();

	ReferenceCodes addressWhenCreateRecordgetCountryDesc();

	Object validateCityInfogetCityDescription();

	List<Addresses> addressSearchAddresses(Addresses paramBean);

	Integer addressInsertAddresses(List<Addresses> lstAddresses);

	SystemProfiles citySystemProfilecityProfileCur();

	Object preInsertaddressIdCur();

	Integer addressDeleteAddresses(List<Addresses> lstAddresses);

	Integer addressUpdateAddresses(List<Addresses> lstAddresses);

	List<Phones> addressKeyDelrecphoneEx(Phones paramBean);

	List<ReferenceCodes> rgCountyRgroup();

	Integer offEducationsCommit(OffenderEducationsCommitBean commitBean);

	Integer offEmploymentsCommit(OffenderEmploymentsCommitBean commitBean);

	Integer addressCommit(AddressesCommitBean commitBean);

}

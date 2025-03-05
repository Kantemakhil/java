package net.syscon.s4.inst.demographicsbiometrics;

import java.util.List;

import net.syscon.s4.im.beans.Dual;
import net.syscon.s4.im.beans.OffenderProfileDetails;
import net.syscon.s4.im.beans.Offenders;
import net.syscon.s4.im.beans.ProfileCodes;
import net.syscon.s4.im.beans.ProfileTypes;
import net.syscon.s4.im.beans.ReferenceCodes;

/**
 * Interface OidpinfoRepository
 */
public interface OidpinfoRepository {
	List<ReferenceCodes> cgfkOffNameDspDescriptionRgroup();

	List<ProfileCodes> cgfkOffPdDspDescriptionRgroup(String profileType);

	List<ReferenceCodes> rgBirthStateRecordGroup();

	ReferenceCodes cgfklkpOffNameOffRefCodec(ReferenceCodes paramBean);

	Integer offNameUpdateOffenders(List<Offenders> lstOffenders);

	List<Object> offBkgOnCheckDeleteMasteroffPdCur(OffenderProfileDetails paramBean);

	ProfileTypes cgfklkpOffPdOffPdPflCod(ProfileTypes paramBean);

	ProfileCodes cgfklkpOffPdOffPdPflCodc(ProfileCodes paramBean);

	List<Offenders> offNameSearchOffenders(Offenders objOffenders);

	ProfileTypes cgfkchkOffPdOffPdPflTypc(OffenderProfileDetails offenderProfdet);

	ReferenceCodes offNamePostQuerycOffBirthState(ReferenceCodes paramBean);

	ProfileCodes cgfkchkOffPdOffPdPflCodc(OffenderProfileDetails offenderProfdet);

	ProfileTypes dspDescriptionWhenValidateItemprofileTypes(ProfileTypes paramBean);

	ProfileTypes profileCodePostChange(ProfileTypes paramBean);

	ReferenceCodes cgfkchkOffNameOffRefCodec(ReferenceCodes paramBean);

	Integer offPdUpdateOffenderProfileDetails(List<OffenderProfileDetails> offenderProfdet);

	List<Dual> cgwhenNewFormInstancec();

	List<OffenderProfileDetails> offPdSearchOffenderProfileDetails(OffenderProfileDetails offenderProfdet);
}

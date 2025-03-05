package net.syscon.s4.inst.demographicsbiometrics;

import java.util.List;

import net.syscon.s4.im.beans.Dual;
import net.syscon.s4.im.beans.OffenderProfileDetails;
import net.syscon.s4.im.beans.OffenderProfileDetailsCommitBean;
import net.syscon.s4.im.beans.Offenders;
import net.syscon.s4.im.beans.OffendersCommitBean;
import net.syscon.s4.im.beans.ProfileCodes;
import net.syscon.s4.im.beans.ProfileTypes;
import net.syscon.s4.im.beans.ReferenceCodes;

/**
 * Interface OidpinfoService
 */
public interface OidpinfoService {
	List<ReferenceCodes> cgfkOffNameDspDescriptionRgroup();

	List<ProfileCodes> cgfkOffPdDspDescriptionRgroup(String profileType);

	List<ReferenceCodes> rgBirthStateRecordGroup();

	Integer offNameUpdateOffenders(List<Offenders> lstOffenders);

	ProfileTypes profileCodePostChange(ProfileTypes paramBean);

	List<Object> offBkgOnCheckDeleteMasteroffPdCur(OffenderProfileDetails paramBean);

	ReferenceCodes offNamePostQuerycOffBirthState(ReferenceCodes paramBean);

	ReferenceCodes cgfkchkOffNameOffRefCodec(ReferenceCodes paramBean);

	ProfileTypes cgfkchkOffPdOffPdPflTypc(OffenderProfileDetails paramBean);

	ProfileCodes cgfklkpOffPdOffPdPflCodc(ProfileCodes paramBean);

	ProfileTypes cgfklkpOffPdOffPdPflCod(ProfileTypes paramBean);

	ProfileCodes cgfkchkOffPdOffPdPflCodc(OffenderProfileDetails paramBean);

	List<Offenders> offNameSearchOffenders(Offenders objOffenders);

	ReferenceCodes cgfklkpOffNameOffRefCodec(ReferenceCodes paramBean);

	Integer offPdUpdateOffenderProfileDetails(List<OffenderProfileDetails> list);

	ProfileTypes dspDescriptionWhenValidateItemprofileTypes(ProfileTypes paramBean);

	List<OffenderProfileDetails> offPdSearchOffenderProfileDetails(OffenderProfileDetails offenderProfDet);

	List<Dual> cgwhenNewFormInstancec();

	Integer offNameCommit(OffendersCommitBean commitBean);

	Integer offPdCommit(OffenderProfileDetailsCommitBean commitBean);
}

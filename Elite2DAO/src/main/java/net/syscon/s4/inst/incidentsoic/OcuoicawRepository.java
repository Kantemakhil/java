package net.syscon.s4.inst.incidentsoic;

import java.util.List;

import net.syscon.s4.common.beans.OffenderOicSanctions;
import net.syscon.s4.common.beans.OicOffences;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.inst.incidentsoic.maintenance.OicSanctionLimits;

/**
 * class OcuoicawRepository
 */
public interface OcuoicawRepository {

	List<ReferenceCodes> rgSanctRecordGroup();

	List<ReferenceCodes> rgSanctStRecordGroup();

	List<OffenderOicSanctions> rgOtherSanctionsRecordGroup(OffenderOicSanctions offenderOicSan);

	List<OffenderOicSanctions> oicSancSearchOffenderOicSanctions(OffenderOicSanctions objSearchDao);

	List<OicOffences> whenValidateItemgetOicOffenceCodeCur(OicOffences paramBean);

	Integer oicsancInsertOffenderOicSanctions(List<OffenderOicSanctions> lstOffenderOicSan);

	Integer oicSancUpdateOffenderOicSanctions(List<OffenderOicSanctions> lstOffenderOicSan);

	SystemProfiles getprofilevaluevsprofvalcur(SystemProfiles paramBean);

	Integer offesencPreInsertcsanseq(Integer offBookId);

	Integer offesencPostInsertcsanseq(Integer offBookId, Integer consecativeSeq, Integer  incidentid);

	Integer getOicIncidentValueFromSanction(Integer sanctionSeq, Integer offenderBookId);

	OicSanctionLimits gettingSactionLimits(String oicHearingType, String oicSanctionCode);
}

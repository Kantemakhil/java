package net.syscon.s4.inst.incidentsoic;

import java.util.List;

import net.syscon.s4.common.beans.OffenderOicSanctions;
import net.syscon.s4.common.beans.OffenderOicSanctionsCommitBean;
import net.syscon.s4.common.beans.OicOffences;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.inst.incidentsoic.maintenance.OicSanctionLimits;

public interface OcuoicawService {
	List<ReferenceCodes> rgSanctRecordGroup();

	List<ReferenceCodes> rgSanctStRecordGroup();

	List<OffenderOicSanctions> rgOtherSanctionsRecordGroup(OffenderOicSanctions offenderOicSan);

	List<OicOffences> whenValidateItemgetOicOffenceCodeCur(OicOffences paramBean);

	SystemProfiles getprofilevaluevsprofvalcur(SystemProfiles paramBean);

	List<OffenderOicSanctions> oicSancSearchOffenderOicSanctions(OffenderOicSanctions objOffenderOicSan);

	List<OffenderOicSanctions> oicsancInsertOffenderOicSanctions(List<OffenderOicSanctions> lstOffenderOicSan);

	List<OffenderOicSanctions> oicSancUpdateOffenderOicSanctions(List<OffenderOicSanctions> lstOffenderOicSan);

	List<OffenderOicSanctions> oicSancCommit(OffenderOicSanctionsCommitBean commitBean);

	OicSanctionLimits getHearingType(OffenderOicSanctions offenderOicSan);
}

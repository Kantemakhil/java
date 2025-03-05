package net.syscon.s4.inst.incidentsoic.maintenance;
import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.beans.SystemProfilesCommitBean;
/**
 * Interface OimoicmpService 
 */
public interface OimoicmpService  {
	List<ReferenceCodes> cgfkOicSlOicSanctionCodeRecordGroup() ;

	List<ReferenceCodes> cgfkOicSlOicHearingTypeRecordGroup() ;

	List<OicSanctionLimits> oicSlCommit(OicSanctionLimitsCommitBean commitBean) ;

	Integer sysPflCommit(SystemProfilesCommitBean commitBean) ;

	List<OicSanctionLimits> oicSlExecuteQuery(OicSanctionLimits objOicSanctionLimits) ;

	List<SystemProfiles> sysPflExecuteQuery(SystemProfiles objSystemProfiles) ;

}

package net.syscon.s4.inst.incidentsoic.maintenance;
import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SystemProfiles;
/**
 * Interface OimoicmpRepository
 */
public interface OimoicmpRepository {
	List<ReferenceCodes> cgfkOicSlOicSanctionCodeRecordGroup() ;

	List<ReferenceCodes> cgfkOicSlOicHearingTypeRecordGroup() ;

	Integer oicSlUpdateOicSanctionLimits(List<OicSanctionLimits> lstOicSanctionLimits) ;

	Integer oicSlDeleteOicSanctionLimits(List<OicSanctionLimits> lstOicSanctionLimits) ;

	Integer oicSlInsertOicSanctionLimits(List<OicSanctionLimits> lstOicSanctionLimits) ;

	List<OicSanctionLimits> oicSlExecuteQuery(OicSanctionLimits objOicSanctionLimits) ;

	List<SystemProfiles> sysPflExecuteQuery(SystemProfiles objSystemProfiles) ;

}

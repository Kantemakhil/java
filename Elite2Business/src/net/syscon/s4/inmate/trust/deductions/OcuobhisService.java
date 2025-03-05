package net.syscon.s4.inmate.trust.deductions;
import java.util.List;

import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.im.beans.OmsModules;
import net.syscon.s4.inmate.beans.OffenderObligationHty;
import net.syscon.s4.inmate.beans.OffenderObligationHtyCommitBean;
/**
 * Interface OcuobhisService 
 */
public interface OcuobhisService  {
	SystemProfiles whenNewFormInstance(SystemProfiles paramBean);

	List<OffenderObligationHty> offOblHtyExecuteQuery(OffenderObligationHty objOffObli);

	OmsModules createFormGlobals(OmsModules paramBean);

}

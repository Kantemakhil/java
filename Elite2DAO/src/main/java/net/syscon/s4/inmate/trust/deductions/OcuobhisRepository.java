package net.syscon.s4.inmate.trust.deductions;
import java.util.List;

import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.im.beans.OmsModules;
import net.syscon.s4.inmate.beans.OffenderObligationHty;
/**
 * Interface OcuobhisRepository
 */
public interface OcuobhisRepository {
	SystemProfiles ocuobhisWhenNewFormInstance(SystemProfiles paramBean);

	List<OffenderObligationHty> offOblHtyExecuteQuery(OffenderObligationHty objOffObl);

	OmsModules createFormGlobals(OmsModules paramBean);
	
	String getCaseloadType(String caseloadId);

}

package net.syscon.s4.inst.casemanagement;

import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.inst.casemanagement.beans.CasePlans;

public interface OciiplanService {
	List<CasePlans> casePlansExecuteQuery(CasePlans objCasePlans);

	Integer casePlansCommit(CasePlans CommitBean);

	List<AgencyLocations> rgInstAgyLocRecordGroup(String caseLoadId);

	List<AgencyLocations> comInstAgyLocRecordGroup(String caseLoadId);

	List<ReferenceCodes> rgVerifiedFilterRecordGroup();
	
	Boolean ociiplanTagMainGetOffender(String caseLoadId, String caseLoadType, String OffenderIdDisplay,String userName); 

}

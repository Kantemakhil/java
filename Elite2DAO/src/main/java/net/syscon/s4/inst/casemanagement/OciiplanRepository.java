package net.syscon.s4.inst.casemanagement;

import java.util.List;

import net.syscon.s4.common.beans.VHeaderBlock;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.inst.casemanagement.beans.CasePlans;

public interface OciiplanRepository {
	List<CasePlans> casePlansExecuteQuery(CasePlans objCasePlans);

	List<AgencyLocations> rgInstAgyLocRecordGroup(String caseLoadId);

	List<AgencyLocations> comInstAgyLocRecordGroup(String caseLoadId);
	
	Boolean ociiplanTagMainGetOffender(String caseLoadId, String caseLoadType, String OffenderIdDisplay,String userName);
	
	List<VHeaderBlock> ociiplanCaseplansTagMainGetOffender(final String whereClause,String caseLoadId, String caseLoadType, String offenderIdDisplay,String userName);

}

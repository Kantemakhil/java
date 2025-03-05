package net.syscon.s4.inst.incidentsoic.maintenance;

import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.ReferenceDomains;

public interface OimsrlucRepository {

	List<ReferenceDomains> refDmnExcuteQuery();

	Integer refCodeCondUpdateReference(List<ReferenceCodes> updateList);

	Integer refCodeCondInsertReference(List<ReferenceCodes> updateList);

	ReferenceCodes refCodeCondExecuteQuery(String reportType);

	List<ReferenceCodes> unitLovExecuteQuery();
	
	

}

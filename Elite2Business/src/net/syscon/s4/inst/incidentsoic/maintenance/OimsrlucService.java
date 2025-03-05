package net.syscon.s4.inst.incidentsoic.maintenance;

import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.ReferenceCodesCommitBean;
import net.syscon.s4.common.beans.ReferenceDomains;

public interface OimsrlucService {

	List<ReferenceDomains> refDmnExcuteQuery();

	List<ReferenceCodes> refCodeExecuteQuery(ReferenceCodes searchBean);

	ReferenceCodes refCodeCondExecuteQuery(String code);

	Integer refCodeCondCommit(ReferenceCodesCommitBean commitBean);

	List<ReferenceCodes> unitLovExecuteQuery();

	
	

}

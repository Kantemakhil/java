package net.syscon.s4.inst.casemanagement;
import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;
/**
 * Interface OiuiwpgnService 
 */
public interface OiuiwpgnService  {
	List<ReferenceCodes> getReferenceCode(ReferenceCodes paramBean);

//	AssignContextParam(IwpTemplateModules paramBean);

	List<ReferenceCodes> rgStatusRecordGroup();

//	Integer paramsCommit(Iwp10gGetParameterscommitBean commitBean);
//
//	List<Iwp10gGetParameters> paramsExecuteQuery(Iwp10gGetParameters objIwp10gGetParameters);

}

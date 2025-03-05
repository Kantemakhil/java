package net.syscon.s4.inst.casemanagement;
import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.im.beans.IwpTemplateModules;
/**
 * Interface OiuiwpgnRepository
 */
public interface OiuiwpgnRepository {
//	Integer paramsUpdateIwp10gGetParameters(List<Iwp10gGetParameters> lstIwp10gGetParameters);

	List<ReferenceCodes> rgStatusRecordGroup();

//	List<Iwp10gGetParameters> paramsExecuteQuery(Iwp10gGetParameters objIwp10gGetParameters);

	List<ReferenceCodes> getReferenceCode(ReferenceCodes paramBean);

	List<IwpTemplateModules> assignContextParam(IwpTemplateModules paramBean);

}

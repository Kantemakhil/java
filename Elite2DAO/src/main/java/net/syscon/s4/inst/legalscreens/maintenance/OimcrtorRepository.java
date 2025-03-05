package net.syscon.s4.inst.legalscreens.maintenance;

import java.math.BigDecimal;
import java.util.List;

import net.syscon.s4.im.beans.IwpTemplateObjects;
import net.syscon.s4.im.beans.IwpTemplates;
import net.syscon.s4.im.beans.OrderTypes;

/**
 * Interface OimcrtorRepository
 */
public interface OimcrtorRepository {
	List<OrderTypes> orderTypesExecuteQuery(OrderTypes objOrderTypes);

	Integer iwpTemplateObjectsInsertIwpTemplateObjects(List<IwpTemplateObjects> lstIwpTemplateObjects);

	Integer iwpTemplateObjectsUpdateIwpTemplateObjects(List<IwpTemplateObjects> lstIwpTemplateObjects);

	Integer orderTypesInsertOrderTypes(List<OrderTypes> lstOrderTypes);

	Integer iwpTemplateObjectsDeleteIwpTemplateObjects(List<IwpTemplateObjects> lstIwpTemplateObjects);

	Integer orderTypesUpdateOrderTypes(List<OrderTypes> lstOrderTypes);

	Integer iwpTempOnCheckDeleteMaster(BigDecimal templateId);

	List<IwpTemplateObjects> iwpTemplateObjectsExecuteQuery(IwpTemplateObjects objIwpTemplateObjects);

	List<IwpTemplates> rgTemplateRecordGroup();

	String getAttchedTemplateCode(BigDecimal templateId);

	BigDecimal getNextIwpId();

	BigDecimal getTemplateId(String templateName);

}

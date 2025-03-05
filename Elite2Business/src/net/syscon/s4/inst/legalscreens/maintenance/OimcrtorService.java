package net.syscon.s4.inst.legalscreens.maintenance;

import java.math.BigDecimal;
import java.util.List;

import net.syscon.s4.im.beans.IwpTemplateObjects;
import net.syscon.s4.im.beans.IwpTemplateObjectsCommitBean;
import net.syscon.s4.im.beans.IwpTemplates;
import net.syscon.s4.im.beans.OrderTypes;
import net.syscon.s4.im.beans.OrderTypesCommitBean;

/**
 * Interface OimcrtorService
 */
public interface OimcrtorService {
	Integer orderTypesCommit(OrderTypesCommitBean commitBean);

	Integer iwpTemplateObjectsCommit(IwpTemplateObjectsCommitBean commitBean);

	List<OrderTypes> orderTypesExecuteQuery(OrderTypes objOrderTypes);

	List<IwpTemplateObjects> iwpTemplateObjectsExecuteQuery(IwpTemplateObjects objIwpTemplateObjects);

	List<IwpTemplates> rgTemplateRecordGroup();

	Integer iwpTempOnCheckDeleteMaster(BigDecimal templateId);
}

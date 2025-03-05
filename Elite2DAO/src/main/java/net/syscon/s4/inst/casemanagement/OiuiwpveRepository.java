package net.syscon.s4.inst.casemanagement;
import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.im.beans.IwpDocuments;
import net.syscon.s4.im.beans.IwpTemplateModules;
import net.syscon.s4.im.beans.IwpTemplateObjects;
import net.syscon.s4.im.beans.IwpTemplates;
/**
 * Interface OiuiwpveRepository
 */
public interface OiuiwpveRepository {
	List<IwpTemplates> isTemplateHasBody(IwpTemplates paramBean);

	Integer iwpDocUpdateIwpDocuments(List<IwpDocuments> lstIwpDocuments);

	List<IwpTemplateModules> populateDefltDoc(IwpTemplateModules paramBean);

	List<IwpTemplateObjects> checkVicNotifTemplActive(IwpTemplateObjects paramBean);

	List<ReferenceCodes> rgStatusRecordGroup();

	List<IwpTemplates> rgTemplateRecordGroup(String offenderBookId, String moduleName, String pObjectType, String pType, String pSubType,String  pObjectId, String blockName, String username);

	List<IwpDocuments> iwpDocExecuteQuery(IwpDocuments objIwpDocuments);

	List<IwpTemplates> populateDefltDoc(IwpTemplates paramBean);

}

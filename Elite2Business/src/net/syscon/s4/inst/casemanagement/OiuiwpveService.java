package net.syscon.s4.inst.casemanagement;
import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.im.beans.IwpDocuments;
import net.syscon.s4.im.beans.IwpDocumentsCommitBean;
import net.syscon.s4.im.beans.IwpTemplateObjects;
import net.syscon.s4.im.beans.IwpTemplates;
/**
 * Interface OiuiwpveService 
 */
public interface OiuiwpveService  {
	List<IwpTemplates> populateDefltDoc(IwpTemplates paramBean);

	Integer iwpDocCommit(IwpDocumentsCommitBean commitBean);

	List<IwpTemplates> isTemplateHasBody(IwpTemplates paramBean);

	List<ReferenceCodes> rgStatusRecordGroup();

	List<IwpTemplateObjects> checkVicNotifTemplActive(IwpTemplateObjects paramBean);

	List<ReferenceCodes> rgTemplateRecordGroup(String offenderBookId, String moduleName, String pObjectType, String pType, String pSubType,String  pObjectId, String blockName, String username);

	List<IwpDocuments> iwpDocExecuteQuery(IwpDocuments objIwpDocuments);

}

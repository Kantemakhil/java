package net.syscon.s4.sa.admin.integratedwordprocessing;
import java.math.BigDecimal;
import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.im.beans.IwpTemplateModules;
import net.syscon.s4.im.beans.IwpTemplates;
import net.syscon.s4.im.beans.OmsModules;
import net.syscon.s4.im.beans.OmsRoles;
import net.syscon.s4.sa.admin.beans.IwpBookmarkParameters;
import net.syscon.s4.sa.admin.beans.IwpBookmarks;
import net.syscon.s4.sa.admin.beans.IwpBookmarksGroups;
import net.syscon.s4.sa.admin.beans.IwpParameterMappings;
import net.syscon.s4.sa.admin.beans.IwpTemplateRoles;
/**
 * Interface OumdtempRepository
 */
public interface OumdtempRepository {
	Integer aIwpTagRelationsDeleteIwpTemplateModules(List<IwpTemplateModules> lstIwpTemplateModules) ;

	List<OmsRoles> rgRolesRecordGroup() ;

	List<IwpParameterMappings> iwpParameterMappingsExecuteQuery(IwpParameterMappings objIwpParameterMappings) ;

	List<OmsModules> rgReportNameRecordGroup() ;

	List<StaffMembers> rgStaffRecordGroup() ;

	List<ReferenceCodes> rgObjectTypeRecordGroup() ;

	Integer aIwpTemplatesDeleteIwpTemplates(List<IwpTemplates> lstIwpTemplates) ;

	Integer aIwpTemplatesInsertIwpTemplates(List<IwpTemplates> lstIwpTemplates) ;

	Integer aIwpTemplatesUpdateIwpTemplates(List<IwpTemplates> lstIwpTemplates) ;

	List<IwpBookmarkParameters> iwpParameterMappingsPostQuery(IwpBookmarkParameters paramBean);

	List<OmsModules> rgOmsModuleRecordGroup() ;

	String aIwpTagRelationsInsertIwpTemplateModules(List<IwpTemplateModules> lstIwpTemplateModules) ;

	List<IwpBookmarksGroups> contextRule(IwpBookmarksGroups paramBean);

	String aIwpTagRelationsUpdateIwpTemplateModules(List<IwpTemplateModules> lstIwpTemplateModules) ;

	Integer aIwpTagRelationsPostUpdate(IwpTemplateModules paramBean);

	List<IwpBookmarks> rgBmListRecordGroup() ;

	Integer iwpParameterMappingsUpdateIwpParameterMappings(List<IwpParameterMappings> lstIwpParameterMappings) ;

	BigDecimal aIwpTagRelationsPreInsert();

	List<IwpTemplateModules> aIwpTagRelationsExecuteQuery(IwpTemplateModules objIwpTemplateModules) ;

	List<IwpTemplates> aIwpTemplatesExecuteQuery(IwpTemplates objIwpTemplates) ;

	List<IwpParameterMappings> aIwpTagRelationsOnCheckDeleteMaster(IwpParameterMappings paramBean);

	List<IwpTemplateRoles> iwpRolesExecuteQuery(IwpTemplates searchRecord);

	Integer templateNameexists(List<String> returnObj);

	Integer isContextRecCur(IwpTemplates searchBean);

	Integer bookmarkGrpCur(IwpTemplates searchBean);

	Integer recExistsCur(IwpTemplates searchBean);

	Integer aIwpTagRelationsInsertQuery(List<IwpTemplateRoles> insertList);

	Integer aIwpTagRelationsUpdateQuery(List<IwpTemplateRoles> updateList);

	int aIwpTagRelationsDeleteQuery(List<IwpTemplateRoles> deleteList);

	Integer isTemplRoleExists(List<Long> templateId, List<String> roleCode);

	Integer tagRelationsPostInsert(List<IwpTemplateModules> insertList);

	List<OmsModules> rgDocNameRecordGroup();
	
	List<IwpTemplateRoles> iwpRolesForStaffExecuteQuery(String user);

}

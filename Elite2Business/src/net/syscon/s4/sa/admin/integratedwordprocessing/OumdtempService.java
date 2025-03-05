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
import net.syscon.s4.sa.admin.beans.IwpParameterMappings;
import net.syscon.s4.sa.admin.beans.IwpParameterMappingsCommitBean;
import net.syscon.s4.sa.admin.beans.IwpTemplateModulesCommitBean;
import net.syscon.s4.sa.admin.beans.IwpTemplateRoles;
import net.syscon.s4.sa.admin.beans.IwpTemplateRolesCommitBean;
import net.syscon.s4.sa.admin.beans.IwpTemplatesCommitBean;

/**
 * Interface OumdtempService
 */
public interface OumdtempService {
	List<OmsModules> rgOmsModuleRecordGroup();

	Integer aIwpTagRelationsCommit(IwpTemplateModulesCommitBean commitBean);

	Integer iwpParameterMappingsCommit(IwpParameterMappingsCommitBean commitBean);

	List<OmsRoles> rgRolesRecordGroup();

	List<IwpParameterMappings> iwpParameterMappingsExecuteQuery(IwpParameterMappings objIwpParameterMappings);

	List<OmsModules> rgReportNameRecordGroup();

	List<StaffMembers> rgStaffRecordGroup();

	List<ReferenceCodes> rgObjectTypeRecordGroup();

	List<IwpBookmarkParameters> iwpParameterMappingsPostQuery(IwpBookmarkParameters paramBean);

	List<IwpBookmarks> rgBmListRecordGroup();

	List<IwpTemplateModules> aIwpTagRelationsExecuteQuery(IwpTemplateModules objIwpTemplateModules);

	Integer aIwpTemplatesCommit(IwpTemplatesCommitBean commitBean);

	List<IwpTemplates> aIwpTemplatesExecuteQuery(IwpTemplates objIwpTemplates);

	List<IwpParameterMappings> aIwpTagRelationsOnCheckDeleteMaster(IwpParameterMappings paramBean);

	List<IwpTemplateRoles> iwpRolesExecuteQuery(IwpTemplates searchBean);

	String whenActChangedEvent(IwpTemplates searchBean);

	Integer templRolesCommit(IwpTemplateRolesCommitBean commitBean);
	
	IwpTemplates viewTemplate(BigDecimal bigDecimal, String savedLocation);

	int[] uploadTemplate(List<IwpTemplates> iwpDocuments, String savedLocation);

}

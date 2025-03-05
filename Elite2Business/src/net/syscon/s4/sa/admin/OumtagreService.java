package net.syscon.s4.sa.admin;

import java.util.List;

import net.syscon.s4.common.beans.AuditLog;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.im.beans.OmsModules;
import net.syscon.s4.sa.admin.beans.ModuleTabColumns;
import net.syscon.s4.sa.admin.beans.ModuleTabColumnsCommitBean;
import net.syscon.s4.sa.admin.beans.ModuleTables;
import net.syscon.s4.sa.admin.beans.ModuleTablesCommitBean;

/**
 * Interface OumtagreService
 */
public interface OumtagreService {
	ModuleTables moduleTablesCommit(ModuleTablesCommitBean CommitBean);

    List<OmsModules> rgSetupModuleRecordGroup();

    Integer moduleTabColumnsCommit(ModuleTabColumnsCommitBean CommitBean);

     List<ReferenceCodes> rgModuleNameRecordGroup();

    List<ReferenceCodes> rgObjectNameRecordGroup();

    List<OmsModules> rleInarcExecuteQuery(OmsModules objOmsModules);

    AuditLog moduleTablesExecuteQuery(ModuleTables objModuleTables);

    List<ModuleTabColumns> moduleTabColumnsExecuteQuery(ModuleTabColumns objModuleTabColumns);

    List<ReferenceCodes> rgColumnNameRecordGroup(String objectName);
    
    Integer validateTriggerExist(String objectName);
    
    String getModuleAssociatedWithTable(ModuleTables objModuleTables);
    
    List<ModuleTables> getTablesDescription();

	String getViewAuditFlag(String moduleName);

}

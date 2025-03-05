package net.syscon.s4.sa.admin;

import java.util.List;

import net.syscon.s4.common.beans.AuditLog;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.im.beans.OmsModules;
import net.syscon.s4.sa.admin.beans.ModuleTabColumns;
import net.syscon.s4.sa.admin.beans.ModuleTables;

/**
 * Interface OumtagreRepository
 */
public interface OumtagreRepository {
    Integer moduleTabColumnsInsertModuleTabColumns(List<ModuleTabColumns> lstModuleTabColumns);

    List<OmsModules> rgSetupModuleRecordGroup();

    ModuleTables moduleTablesDeleteModuleTables(List<ModuleTables> lstModuleTables);

     List<ReferenceCodes> rgModuleNameRecordGroup();

    List<ReferenceCodes> rgObjectNameRecordGroup();

    List<OmsModules> rleInarcExecuteQuery(OmsModules objOmsModules);

    List<ModuleTables> moduleTablesExecuteQuery(ModuleTables objModuleTables);

    Integer moduleTabColumnsUpdateModuleTabColumns(List<ModuleTabColumns> lstModuleTabColumns);

    Integer moduleTabColumnsDeleteModuleTabColumns(List<ModuleTabColumns> lstModuleTabColumns);

    ModuleTables moduleTablesUpdateModuleTables(List<ModuleTables> lstModuleTables);

    List<ModuleTabColumns> moduleTabColumnsExecuteQuery(ModuleTabColumns objModuleTabColumns);

    List<ReferenceCodes> rgColumnNameRecordGroup(String tableName, String modTabId);

    Long moduleTabColumnsPreInsert(Long moduleTabId);

    Long moduleTabPreInsert();

    ModuleTables moduleTablesInsertModuleTables(List<ModuleTables> lstModuleTables);
    
    ModuleTables enableAuditOnTable(String tableName);
    
    ModuleTables deleteAuditOnTable(String tableName);
    
    Integer validateTriggerExist(String tableName);
    
    ModuleTables insertTableAuditDetails(List<ModuleTables> obj);
    
    ModuleTables updateTableAuditDetails(List<ModuleTables> obj);
    
    ModuleTables insertTableAuditData(ModuleTables obj);
    
    ModuleTables  updateTableAuditData(ModuleTables obj);
    
    List<ModuleTables> auditTablesExecuteQuery();
    
    List<ModuleTables> getModuleNamesAssociatedWithTable(ModuleTables obj);
    
    Integer getTableAuditRecord(String tableName);
    
    List<ModuleTables> getTablesDescription();

	String getViewAuditFlag(String moduleName);

	int updateViewAuditFlag(AuditLog auditLog);
}

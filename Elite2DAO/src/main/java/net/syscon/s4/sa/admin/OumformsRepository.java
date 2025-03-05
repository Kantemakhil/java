package net.syscon.s4.sa.admin;

import java.util.List;

import net.syscon.s4.common.beans.FormAccessibleForms;
import net.syscon.s4.im.beans.OmsModules;
import net.syscon.s4.im.beans.ReferenceCodes;
import net.syscon.s4.sa.admin.beans.AccessibleFormTables;
import net.syscon.s4.sa.admin.beans.AllTabColumns;

/**
 * Interface OumformsRepository
 */
public interface OumformsRepository {
	Integer fafDeleteFormAccessibleForms(final List<FormAccessibleForms> lstFormAccessibleForms);

	List<AllTabColumns> rgTableNameRecordGroup();

	Integer fafUpdateFormAccessibleForms(final List<FormAccessibleForms> lstFormAccessibleForms);

	List<ReferenceCodes> rgModuleNameRecordGroup();

	List<AccessibleFormTables> accessTabExecuteQuery(final AccessibleFormTables objAccessibleFormTables);

	AccessibleFormTables accessTabInsertAccessibleFormTables(final List<AccessibleFormTables> lstAccessibleFormTables);

	List<FormAccessibleForms> fafExecuteQuery(final FormAccessibleForms objFormAccessibleForms);

	AccessibleFormTables accessTabDeleteAccessibleFormTables(final List<AccessibleFormTables> getDeleteList);

	List<OmsModules> rgDestinationFormRecordGroup();

	List<OmsModules> omsModExecuteQuery(final OmsModules objOmsModules);

	Integer fafInsertFormAccessibleForms(final List<FormAccessibleForms> lstFormAccessibleForms);

	AccessibleFormTables accessTabDeleteAccessibleFormTablesTwo(List<AccessibleFormTables> deleteList);

}

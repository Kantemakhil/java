package net.syscon.s4.sa.admin;

import java.util.List;

import net.syscon.s4.common.beans.FormAccessibleForms;
import net.syscon.s4.common.beans.FormAccessibleFormsCommitBean;
import net.syscon.s4.im.beans.OmsModules;
import net.syscon.s4.im.beans.ReferenceCodes;
import net.syscon.s4.sa.admin.beans.AccessibleFormTables;
import net.syscon.s4.sa.admin.beans.AccessibleFormTablesCommitBean;
import net.syscon.s4.sa.admin.beans.AllTabColumns;

/**
 * Interface OumformsService
 */
public interface OumformsService {

	List<AllTabColumns> rgTableNameRecordGroup();

	Integer fafCommit(FormAccessibleFormsCommitBean commitBean);

	AccessibleFormTables accessTabCommit(AccessibleFormTablesCommitBean commitBean);

	List<ReferenceCodes> rgModuleNameRecordGroup();

	List<AccessibleFormTables> accessTabExecuteQuery(AccessibleFormTables objAccessibleFormTables);

	List<FormAccessibleForms> fafExecuteQuery(FormAccessibleForms objFormAccessibleForms);

	List<OmsModules> rgDestinationFormRecordGroup();

	List<OmsModules> omsModExecuteQuery(OmsModules objOmsModules);

}

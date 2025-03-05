package net.syscon.s4.sa.admin;

import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.of.payroll.maintenance.SystemEvents;
import net.syscon.s4.sa.admin.beans.CaseloadGrpHolCompens;
import net.syscon.s4.sa.admin.beans.CaseloadWorkGroups;

/**
 * Interface OymholidRepository
 */
public interface OymholidRepository {
	Integer updateCaseloadGrpHolCompens(List<CaseloadGrpHolCompens> lstCaseloadGrpHolCompens);

	Integer deleteCaseloadGrpHolCompens(List<CaseloadGrpHolCompens> lstCaseloadGrpHolCompens);

	List<SystemEvents> sysEventExecuteQuery(SystemEvents objSystemEvents);

	Integer insertSystemEvents(List<SystemEvents> lstSystemEvents);

	Integer deleteSystemEvents(List<SystemEvents> lstSystemEvents);

	List<CaseloadGrpHolCompens> csldGhcExecuteQuery(CaseloadGrpHolCompens objCaseloadGrpHolCompens);

	List<CaseloadWorkGroups> cgfkCsldGhcWorkGroupIdRecordGroup(String caseloadType);

	List<ReferenceCodes> cgfkCsldGhcCompensationCodRecordGroup();

	Integer insertCaseloadGrpHolCompens(List<CaseloadGrpHolCompens> lstCaseloadGrpHolCompens);

	Integer updateSystemEvents(List<SystemEvents> lstSystemEvents);

}

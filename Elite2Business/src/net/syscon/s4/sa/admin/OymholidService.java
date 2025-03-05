package net.syscon.s4.sa.admin;

import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.of.payroll.maintenance.SystemEvents;
import net.syscon.s4.of.payroll.maintenance.SystemEventsCommitBean;
import net.syscon.s4.sa.admin.beans.CaseloadGrpHolCompens;
import net.syscon.s4.sa.admin.beans.CaseloadGrpHolCompensCommitBean;
import net.syscon.s4.sa.admin.beans.CaseloadWorkGroups;

/**
 * Interface OymholidService
 */
public interface OymholidService {
	Integer csldGhcCommit(CaseloadGrpHolCompensCommitBean CommitBean);

	List<ReferenceCodes> cgfkCsldGhcCompensationCodRecordGroup();

	Integer sysEventCommit(SystemEventsCommitBean CommitBean);

	List<SystemEvents> sysEventExecuteQuery(SystemEvents objSystemEvents);

	List<CaseloadGrpHolCompens> csldGhcExecuteQuery(CaseloadGrpHolCompens objCaseloadGrpHolCompens);

	List<CaseloadWorkGroups> cgfkCsldGhcWorkGroupIdRecordGroup(String caseloadType);

}

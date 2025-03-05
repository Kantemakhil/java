package net.syscon.s4.cf.deductions.maintenance;

import java.util.List;

import net.syscon.s4.im.beans.GroupedObligations;
import net.syscon.s4.im.beans.GroupedObligationsCommitBean;
import net.syscon.s4.im.beans.ObligationGroups;
import net.syscon.s4.im.beans.ObligationGroupsCommitBean;
import net.syscon.s4.im.beans.SanctionNotices;
import net.syscon.s4.inmate.beans.DeductionTypes;

/**
 * Interface OcmgobliService
 */
public interface OcmgobliService {

	List<ObligationGroups> obGrpExecuteQuery(ObligationGroups objObligationGroups);

	ObligationGroups obGrpCommit(ObligationGroupsCommitBean commitBean);

	List<GroupedObligations> grpObExecuteQuery(GroupedObligations objGroupedObligations);

	Integer grpObCommit(GroupedObligationsCommitBean commitBean);

	List<SanctionNotices> cgfkSanctionNoticesRecordGroup();

	List<DeductionTypes> cgfkGrpObDeductionTypeRecordGroup();

}

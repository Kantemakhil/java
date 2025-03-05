package net.syscon.s4.cf.deductions.maintenance;

import java.util.List;

import net.syscon.s4.im.beans.GroupedObligations;
import net.syscon.s4.im.beans.ObligationGroups;
import net.syscon.s4.im.beans.SanctionNotices;
import net.syscon.s4.inmate.beans.DeductionTypes;

/**
 * Interface OcmgobliRepository
 */
public interface OcmgobliRepository {

	List<ObligationGroups> obGrpExecuteQuery(ObligationGroups objObligationGroups);

	Integer obGrpInsertObligationGroups(List<ObligationGroups> lstObligationGroups);

	Integer obGrpUpdateObligationGroups(List<ObligationGroups> lstObligationGroups);

	Integer obGrpDeleteObligationGroups(List<ObligationGroups> lstObligationGroups);

	List<GroupedObligations> grpObExecuteQuery(GroupedObligations objGroupedObligations);

	Integer grpObInsertGroupedObligations(List<GroupedObligations> lstGroupedObligations);

	Integer grpObUpdateGroupedObligations(List<GroupedObligations> lstGroupedObligations);

	Integer grpObDeleteGroupedObligations(List<GroupedObligations> lstGroupedObligations);

	List<SanctionNotices> cgfkSanctionNoticesRecordGroup();

	List<DeductionTypes> cgfkGrpObDeductionTypeRecordGroup();

}

package net.syscon.s4.iwp;

import java.util.List;
import net.syscon.s4.common.beans.OffenderBookings;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.inst.schedules.bean.VOffenderAllSchedules;
import net.syscon.s4.inst.schedules.bean.VOffenderAllSchedulesCommitBean;

/**
 * Interface OcidiaryService
 */
public interface OcidiaryService {
	Integer offSchCommit(VOffenderAllSchedulesCommitBean CommitBean);

	List<ReferenceCodes> rgSubTypeRecordGroup();

	List<VOffenderAllSchedules> offSchExecuteQuery(VOffenderAllSchedules objVOffenderAllSchedules);

	List<OffenderBookings> DefineWhereClause(OffenderBookings paramBean);

	List<AgencyLocations> rgLocationRecordGroup(final String caseloadid);

	List<ReferenceCodes> rgOutcomeRecordGroup();

}

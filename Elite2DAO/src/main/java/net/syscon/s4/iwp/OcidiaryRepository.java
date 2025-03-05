package net.syscon.s4.iwp;

import java.math.BigDecimal;
import java.util.List;
import net.syscon.s4.common.beans.OffenderBookings;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.OmsModules;
import net.syscon.s4.inst.schedules.bean.VOffenderAllSchedules;

/**
 * Interface OcidiaryRepository
 */
public interface OcidiaryRepository {
	Integer offSchUpdateVOffenderAllSchedules(List<VOffenderAllSchedules> lstVOffenderAllSchedules);

	List<OmsModules> createFormGlobals(OmsModules paramBean);

	List<ReferenceCodes> rgSubTypeRecordGroup();

	List<VOffenderAllSchedules> offSchExecuteQuery(VOffenderAllSchedules objVOffenderAllSchedules);

	List<AgencyLocations> rgLocationRecordGroup(final String caseloadid);

	List<OffenderBookings> defineWhereClause(OffenderBookings paramBean);

	List<ReferenceCodes> rgOutcomeRecordGroup();

	BigDecimal getStaffName(String firstName, String lastName);

	String checkUaEventOutcome(List<VOffenderAllSchedules> lstVOffenderAllSchedules);

}

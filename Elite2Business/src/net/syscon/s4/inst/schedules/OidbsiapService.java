package net.syscon.s4.inst.schedules;

import java.math.BigDecimal;
import java.util.List;

import net.syscon.s4.common.beans.AgencyInternalLocations;
import net.syscon.s4.common.beans.VHeaderBlock;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.OffenderNaDetails;
import net.syscon.s4.im.beans.OffenderNonAssociations;
import net.syscon.s4.inst.casemanagement.beans.InternalScheduleReasons;
import net.syscon.s4.inst.schedules.bean.OffenderIndSchedules;
import net.syscon.s4.inst.schedules.bean.OffenderIndSchedulesCommitBean;
import net.syscon.s4.inst.schedules.bean.VOffenderAllSchedules2;

/**
 * Interface OidbsiapService
 * 
 */
public interface OidbsiapService {
	List<AgencyInternalLocations> rgInternalMoveLocationsRecordGroup(String agyLocId);

	Integer offSchCommit(OffenderIndSchedulesCommitBean commitBean);

	List<VHeaderBlock> checkNa(VHeaderBlock paramBean);

	List<VOffenderAllSchedules2> offSchExecuteQuery(VOffenderAllSchedules2 object);

	List<AgencyLocations> rgAgyLocRecordGroup(String caseloadId);

	List<InternalScheduleReasons> rgSchInternalScheduleRecordGroup();

	Integer offSchCheckScheduleConflict(VOffenderAllSchedules2 searchBean);
	
	List<OffenderNaDetails> checkNonAssociationOffendersWithLivingUnit(BigDecimal offenderBookId,
			BigDecimal livingUnitId);
	
	List<String> checkNonAssociations(OffenderIndSchedulesCommitBean commitBean);

	List<OffenderNonAssociations> getNsOffenderBookId(OffenderIndSchedulesCommitBean commitBean);
	
	List<OffenderIndSchedules> getNonAssociationWarnings(List<OffenderIndSchedules> courseActivitiesList);

}

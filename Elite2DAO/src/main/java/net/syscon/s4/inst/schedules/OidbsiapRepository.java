package net.syscon.s4.inst.schedules;

import net.syscon.s4.common.beans.AgencyInternalLocations;
import net.syscon.s4.common.beans.AgyIntLocProfiles;
import net.syscon.s4.common.beans.VHeaderBlock;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.OffenderNaDetails;
import net.syscon.s4.im.beans.OffenderNonAssociations;
import net.syscon.s4.inst.casemanagement.beans.InternalScheduleReasons;
import net.syscon.s4.inst.schedules.bean.OffenderIndSchedules;
import net.syscon.s4.inst.schedules.bean.VOffenderAllSchedules2;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Interface OidbsiapRepository
 */
public interface OidbsiapRepository {
	List<AgencyInternalLocations> rgInternalMoveLocationsRecordGroup(String agyLocId);

	Integer offSchUpdateVOffenderAllSchedules2(List<OffenderIndSchedules> object);

	List<VOffenderAllSchedules2> offSchExecuteQuery(VOffenderAllSchedules2 object);

	List<AgencyLocations> rgAgyLocRecordGroup(String caseloadId);

	List<VHeaderBlock> checkNa(VHeaderBlock paramBean);

	List<InternalScheduleReasons> rgSchInternalScheduleRecordGroup();

	Integer offSchInsertVOffenderAllSchedules2(List<OffenderIndSchedules> object);

	VHeaderBlock getOffDetailsByBookId(final VHeaderBlock objSearchDao);

	VHeaderBlock getLivingUnitDesc(final VHeaderBlock objSearchDao);

	Integer offSchCheckScheduleConflict(VOffenderAllSchedules2 obj);
    
	List<OffenderNaDetails> getNonAssociationOffenders(final BigDecimal offenderBookId, final List<AgyIntLocProfiles> profileCodeList);
	
	List<AgyIntLocProfiles> getNonAssociationConfigForLocation(final BigDecimal locationId);
	

	List<OffenderNonAssociations> getNsOffenderBookId(BigDecimal offenderBookId);

	List<OffenderIndSchedules> getNonAssociationScheduleConflicts(BigDecimal offenderBookID, Date eventDate,
			BigDecimal toInterLocationId);
	
	 List<OffenderNaDetails> checkNonAssociationOffendersWithLivingUnit(BigDecimal offenderBookId, BigDecimal livingUnitId, String livingUnitCode);
	 String getOffenderName(Long offenderBookId);
	
}

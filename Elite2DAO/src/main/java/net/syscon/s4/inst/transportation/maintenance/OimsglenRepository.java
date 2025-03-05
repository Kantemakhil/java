package net.syscon.s4.inst.transportation.maintenance;
import java.util.List;

import net.syscon.s4.inst.transportation.maintenance.beans.AgencySegmentLengths;
/**
 * Interface OimsglenRepository
 */
public interface OimsglenRepository {
	List<AgencySegmentLengths> agencySegmentLengthsExecuteQuery(AgencySegmentLengths objAgencySegmentLengths) ;

	Integer agencySegmentLengthsUpdateAgencySegmentLengths(List<AgencySegmentLengths> lstAgencySegmentLengths) ;

}

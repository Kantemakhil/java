package net.syscon.s4.inst.transportation.maintenance;

import java.util.List;

import net.syscon.s4.inst.transportation.maintenance.beans.AgencySegmentLengths;
import net.syscon.s4.inst.transportation.maintenance.beans.AgencySegmentLengthsCommitBean;
/**
 * Interface OimsglenService 
 */
public interface OimsglenService  {
	Integer agencySegmentLengthsCommit(AgencySegmentLengthsCommitBean CommitBean) ;

	List<AgencySegmentLengths> agencySegmentLengthsExecuteQuery(AgencySegmentLengths objAgencySegmentLengths) ;

}

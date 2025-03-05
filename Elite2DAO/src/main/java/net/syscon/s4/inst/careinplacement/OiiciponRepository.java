package net.syscon.s4.inst.careinplacement;

import java.util.List;

import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.inst.careinplacement.beans.OffenderCipDetails;

public interface OiiciponRepository {
	
	List<AgencyLocations> rgAgyLocsRecordGroup(String caseloadId);

	List<OffenderCipDetails> offCipDetailsExecuteQuery(OffenderCipDetails searchRecord);

}

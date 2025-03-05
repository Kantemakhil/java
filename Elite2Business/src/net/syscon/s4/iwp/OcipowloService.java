package net.syscon.s4.iwp;

import java.util.List;

import net.syscon.s4.cm.primaryofficeragentassignment.beans.VAssignedOffenders;
import net.syscon.s4.cm.primaryofficeragentassignment.beans.VAssignedOffendersCommitBean;
import net.syscon.s4.cm.primaryofficeragentassignment.beans.VStaffLocation;
import net.syscon.s4.cm.primaryofficeragentassignment.beans.VStaffLocationCommitBean;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.im.beans.AgencyLocations;

/**
 * Interface OcipowloService
 */
public interface OcipowloService {
	
	List<AgencyLocations> cgfkStaffLr1DspDescriptionRecordGroup(final String caseLoadId);
	
	List<ReferenceCodes> positionLovRecordGroup();
	
	List<ReferenceCodes> roleLovRecordGroup();
	
	List<ReferenceCodes> scheduleTypeLovRecordGroup();
	
	List<VAssignedOffenders> vAssOffExecuteQuery(VAssignedOffenders objVAssignedOffenders);
	
	
	List<ReferenceCodes> CgfkchkVOffDetVOffDet(ReferenceCodes paramBean);

	ReferenceCodes CgfklkpVOffDetVOffDet(ReferenceCodes paramBean);

	List<VStaffLocation> vOffDetExecuteQuery(VStaffLocation objVStaffLocation);

	ReferenceCodes CgfklkpVOffDetVOffDe3(ReferenceCodes paramBean);

	AgencyLocations CgfklkpStaffLr1StaffLrAg(AgencyLocations paramBean);


	List<AgencyLocations> CgfkchkStaffLr1StaffLrAg(AgencyLocations paramBean);

	Integer vAssOffCommit(VAssignedOffendersCommitBean commitBean);

	ReferenceCodes CgfkchkVOffDetVOffDe2(ReferenceCodes paramBean);

	ReferenceCodes CgfklkpVOffDetVOffDe2(ReferenceCodes paramBean);

	Integer vOffDetCommit(VStaffLocationCommitBean commitBean);

	ReferenceCodes CgfkchkVOffDetVOffDe3(ReferenceCodes paramBean);

}

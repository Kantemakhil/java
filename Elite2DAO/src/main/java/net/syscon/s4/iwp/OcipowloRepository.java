package net.syscon.s4.iwp;
 

import java.math.BigDecimal;
import java.util.List;

import net.syscon.s4.cm.primaryofficeragentassignment.beans.VAssignedOffenders;
import net.syscon.s4.cm.primaryofficeragentassignment.beans.VStaffLocation;
import net.syscon.s4.common.beans.Images;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.im.beans.AgencyLocations;
/**
 * Interface OcipowloRepository
 */
public interface OcipowloRepository {
	
	List<AgencyLocations> cgfkStaffLr1DspDescriptionRecordGroup(final String caseLoadId) ;
	
	List<ReferenceCodes> positionLovRecordGroup() ;
	
	List<ReferenceCodes> roleLovRecordGroup() ;
	
	List<ReferenceCodes> scheduleTypeLovRecordGroup() ;
	
	List<VStaffLocation> vOffDetExecuteQuery(VStaffLocation objVStaffLocation) ;
	
	List<VAssignedOffenders> vAssOffExecuteQuery(VAssignedOffenders objVAssignedOffenders) ;
	
	BigDecimal populateCurrentWorkload(VStaffLocation searchRecord);

	long populateNoOffenders(VStaffLocation vStaffLocation);

	Integer curGetHpCase(VAssignedOffenders searchRecord);

	Integer curGetYCase(VAssignedOffenders element);

	Integer curGetACase(VAssignedOffenders element);
	
	
	ReferenceCodes cgfklkpVOffDetVOffDe2(ReferenceCodes paramBean);

	ReferenceCodes cgfkchkVOffDetVOffDe3(ReferenceCodes paramBean);

	 
	ReferenceCodes cgfklkpVOffDetVOffDe3(ReferenceCodes paramBean);


	ReferenceCodes cgfkchkVOffDetVOffDe2(ReferenceCodes paramBean);

	List<AgencyLocations> cgfklkpStaffLr1StaffLrAg(AgencyLocations paramBean);

	ReferenceCodes cgfklkpVOffDetVOffDet(ReferenceCodes paramBean);

	AgencyLocations cgfkchkStaffLr1StaffLrAg(AgencyLocations paramBean);

	ReferenceCodes cgfkchkVOffDetVOffDet(ReferenceCodes paramBean);

	Images imageData(Integer offenderBookId);

	

	
}

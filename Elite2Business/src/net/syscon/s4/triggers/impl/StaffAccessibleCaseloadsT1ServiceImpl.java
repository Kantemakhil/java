package net.syscon.s4.triggers.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.common.beans.CaseloadAgencyLocations;
import net.syscon.s4.im.beans.StaffAccessibleCaseloads;
import net.syscon.s4.triggers.StaffAccessibleCaseloadsT1Repository;
import net.syscon.s4.triggers.StaffAccessibleCaseloadsT1Service;
@Service
public class StaffAccessibleCaseloadsT1ServiceImpl implements StaffAccessibleCaseloadsT1Service {
    @Autowired
	private StaffAccessibleCaseloadsT1Repository staffAccessibleCaseloadsT1Repository;
	@Override
	public void staffAccessibleCaseloadsT1(StaffAccessibleCaseloads bean) {
	List<CaseloadAgencyLocations> listdata=new ArrayList<CaseloadAgencyLocations>();
		
	listdata=staffAccessibleCaseloadsT1Repository.gettingAgyLocId(bean.getCaseloadId());
	
	
	for (CaseloadAgencyLocations caseloadAgencyLocations : listdata) {
		
		Integer count=staffAccessibleCaseloadsT1Repository.checkRoleExist(caseloadAgencyLocations.getAgyLocId(),bean.getStaffId());
		
		if(count==1) {
			staffAccessibleCaseloadsT1Repository.updateStaffLocationsRoles(caseloadAgencyLocations.getAgyLocId(),bean.getStaffId(),bean.getModifyUserId());
		}
		
	}
		
	}

}

/**
 * 
 */
package net.syscon.s4.triggers.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.triggers.OffenderEmploymentsT1Repository;
import net.syscon.s4.triggers.OffenderEmploymentsT1Service;

@Service
public class OffenderEmploymentsT1ServiceImpl implements OffenderEmploymentsT1Service {

	@Autowired
	private OffenderEmploymentsT1Repository offenderEmploymentsT1Repository;
	// Below method call after update of EMPLOYER_NAME  ON OFFENDER_EMPLOYMENTS table
	@Override
	public Integer updateReleasePlans(Long offenderBookId) {
		//below method is used to updating employment_status on release_plans table
		return offenderEmploymentsT1Repository.updateReleasePlans(offenderBookId);
	}

}

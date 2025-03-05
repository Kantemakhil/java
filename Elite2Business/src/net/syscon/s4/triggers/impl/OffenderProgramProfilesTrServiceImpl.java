package net.syscon.s4.triggers.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.OffenderProgramProfiles;
import net.syscon.s4.triggers.OffenderProgramProfilesTrRepository;
import net.syscon.s4.triggers.OffenderProgramProfilesTrService;
/*
==============================================================================================================================
   Below comments are copied from OFFENDER_PROGRAM_PROFILES_TR Trigger
==============================================================================================================================
/* MODIFICATION HISTORY
Person       Date        Version       Comments
David Ng     08/08/2006  2.1           remove constraint on the start date
David Ng     06/03/2006  2.0           Check reference codes
*/
@Service
public class OffenderProgramProfilesTrServiceImpl extends BaseBusiness implements OffenderProgramProfilesTrService{
	
	@Autowired
	private OffenderProgramProfilesTrRepository repository;

	@Override
	public Integer offenderProgramProfilesTr(OffenderProgramProfiles newRec) {
		Integer vNumrows=null;
		if(newRec.getOffenderProgramStatus() != null) {
			vNumrows=repository.offenderProgramStatusCount(newRec.getOffenderProgramStatus());
			if(vNumrows == 0) {
				throw new IllegalArgumentException("Invalid Offender Program Status ");
			}
			
			if(newRec.getOffenderProgramStatus().equals("END") && newRec.getOffenderEndDate() == null) {
			}
		}
		
		if(newRec.getWaitlistDecisionCode() != null) {
			vNumrows=repository.waitlistDecisionCodeCount(newRec.getWaitlistDecisionCode());
			if(vNumrows == 0) {
				throw new IllegalArgumentException("Invalid decision code ");
			}
		}
		
		if(newRec.getCrsActyId() != null) {
			vNumrows=repository.crsActyIdCount(newRec.getCrsActyId());
			if(vNumrows == 0) {
				throw new IllegalArgumentException("Course without course Activity type defined ");
			}
		}
		return null;
	}
	
	
	
	
}

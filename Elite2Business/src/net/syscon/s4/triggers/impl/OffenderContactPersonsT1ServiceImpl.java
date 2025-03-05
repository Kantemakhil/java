package net.syscon.s4.triggers.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.inst.booking.beans.OffenderContactPersons;
import net.syscon.s4.triggers.OffenderContactPersonsT1Repository;
import net.syscon.s4.triggers.OffenderContactPersonsT1Service;
/*
==================================================================
  Below comments are copied from OFFENDER_CONTACT_PERSONS_T1 Trigger
==================================================================
  MODIFICATION HISTORY
   Person       Date      version      Comments
   David Ng  03/01/2006  2.0        Populate Offender Contact Person ID
*/
@Service
public class OffenderContactPersonsT1ServiceImpl implements OffenderContactPersonsT1Service {
	@Autowired
	private OffenderContactPersonsT1Repository offenderContactPersonsT1Repository;
	@Override
	public void offenderContactPersonsT1(OffenderContactPersons addrs) {
		Long id=addrs.getOffenderContactPersonId();
		if(id!=null) {
			Long lvOffenderContactPersonId=offenderContactPersonsT1Repository.personSequence();
			id=lvOffenderContactPersonId;
		}
		
	}

}

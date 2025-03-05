package net.syscon.s4.triggers.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.core.EliteDateService;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.OffenderPrgObligations;
import net.syscon.s4.triggers.OffenderPrgObligationsT1Repository;
import net.syscon.s4.triggers.OffenderPrgObligationsT1Service;

@Service
public class OffenderPrgObligationsT1ServiceImpl extends BaseBusiness implements OffenderPrgObligationsT1Service{
	
	@Autowired
	private OffenderPrgObligationsT1Repository repository;
	
	@Autowired
	private EliteDateService eliteDateService;
	
	@Override
	public Integer offenderPrgObligationsT1(OffenderPrgObligations newRec, OffenderPrgObligations oldRec) {
		if(newRec.getStatus() != null && !newRec.getStatus().equals(oldRec != null ?oldRec.getStatus():null)){
			if(newRec.getStatusChangeDate() == null) {
				newRec.setStatusChangeDate(eliteDateService.getDBTime());
			}
			repository.insertOffenderPrgObligationHty(newRec);
		}
		return null;
	}

}

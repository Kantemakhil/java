package net.syscon.s4.triggers.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.common.beans.OffenderBookings;
import net.syscon.s4.pkgs.tag_establishment.TagEstablishmentService;
import net.syscon.s4.triggers.OffenderBookingsT2Service;
@Service
public class OffenderBookingsT2ServiceImpl implements OffenderBookingsT2Service {
	
	private static final String OUT = "OUT";
	@Autowired
	private TagEstablishmentService tagEstablishmentService;

	@Override
	public OffenderBookings offenderBookingsT2(OffenderBookings newbean, OffenderBookings old) {
		
		if(!equals(newbean.getAgyLocId() , newbean.getCreateAgyLocId()) && !newbean.getAgyLocId().equals(OUT))
			newbean.setCreateAgyLocId(newbean.getAgyLocId());
		if(!equals((old.getLivingUnitId() != null ?old.getLivingUnitId().toString():null), (newbean.getLivingUnitId() != null ? newbean.getLivingUnitId().toString():null))) {
		  if(old.getLivingUnitId()!=null)
				tagEstablishmentService.adjustOccupants(old.getLivingUnitId().intValue(), -1,newbean.getCreateUserId()!=null?newbean.getCreateUserId():newbean.getModifyUserId());
			if(newbean.getLivingUnitId()!=null)
				tagEstablishmentService.adjustOccupants(newbean.getLivingUnitId().intValue(), 1,newbean.getCreateUserId()!=null?newbean.getCreateUserId():newbean.getModifyUserId());
		}
		return newbean;
	}
	
	 private Boolean equals(String s1,String s2) {
		if(s1 == null && s2==null)
			return true;
		else if ((s1 != null && s2 == null) || (s1 == null && s2 != null))
			return false;
		else
			return s1.equals(s2);
			
	}

}

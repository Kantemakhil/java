package net.syscon.s4.triggers.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.common.beans.OffenderBookings;
import net.syscon.s4.common.beans.OffenderExternalMovements;
import net.syscon.s4.pkgs.tag_establishment.TagEstablishmentService;
import net.syscon.s4.triggers.OffenderBookingsT1Service;
@Service
public class OffenderBookingsT1ServiceImpl implements OffenderBookingsT1Service {
	@Autowired
	private TagEstablishmentService tagEstablishmentService;
	
	@Override
	public OffenderExternalMovements offenderBookingsT1(final OffenderBookings book) {
       book.setCreateAgyLocId(book.getAgyLocId());
       return tagEstablishmentService.adjustOccupants(book!=null && book.getLivingUnitId()!=null?book.getLivingUnitId().intValue():0,1,book.getCreateUserId()!=null?book.getCreateUserId():book.getModifyUserId());
	}

}

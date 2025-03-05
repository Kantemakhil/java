package net.syscon.s4.triggers.impl;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import net.syscon.s4.common.beans.OffenderBookings;
import net.syscon.s4.common.beans.OffenderExternalMovements;
import net.syscon.s4.triggers.OffenderBookingsT3Service;
@Service
public class OffenderBookingsT3ServiceImpl implements OffenderBookingsT3Service {

	@Override
	public void offenderBookingsT3(OffenderBookings bookings) {
		Long lOffenderBookId=bookings.getOffenderBookId();
		BigDecimal lRootOffenderId=bookings.getRootOffenderId();
		//l_count := nvl(TAG_UTILS.l_tab.COUNT,0) + 1;

		 //  TAG_UTILS.l_tab (l_count).offender_book_id := l_offender_book_id ;
		   //TAG_UTILS.l_tab (l_count).root_offender_id := l_root_offender_id ;
		
	}
	
	
}

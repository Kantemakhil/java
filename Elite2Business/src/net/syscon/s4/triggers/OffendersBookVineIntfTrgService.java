package net.syscon.s4.triggers;

import net.syscon.s4.common.beans.OffenderBookings;

public interface OffendersBookVineIntfTrgService {

	Integer OffendersBookVineIntfTrgTrigger(OffenderBookings offenderBookings, String operation);

}

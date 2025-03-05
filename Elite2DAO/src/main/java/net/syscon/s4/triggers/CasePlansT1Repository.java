package net.syscon.s4.triggers;

import java.util.List;

import net.syscon.s4.common.beans.OffenderBookings;
import net.syscon.s4.im.beans.CasePlans;

public interface CasePlansT1Repository {

	public Integer updateOffenderBookings(final CasePlans casePlans);

	List<OffenderBookings> getOffBookOldRec(final Long offenderBookId);

}

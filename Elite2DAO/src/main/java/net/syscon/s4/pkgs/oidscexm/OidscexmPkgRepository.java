package net.syscon.s4.pkgs.oidscexm;

import java.util.Date;
import java.util.List;

import net.syscon.s4.common.beans.OffenderBookings;
import net.syscon.s4.common.beans.OffenderExternalMovements;
import net.syscon.s4.inst.schedules.bean.VOffenderAllSchedules;

public interface OidscexmPkgRepository {

	Long getSeq(final Long offenderBookId);

	Long getParentCur(final Long offenderBookId);

	Long getNewEventId();

	Integer insertOffenderExtMov(final OffenderExternalMovements bean);

	String getCloseContactFlag();

	String getCommActiveFlag(final Long offenderBookId);

	Integer updateOffenderBookings(final String lvInOutStatus, final String lvAgyLocId, final Long offenderBookId,
			final String userName);

	Integer updateOffenderBookingsOne(final String lvInOutStatus, final String lvAgyLocId, final Long offenderBookId,
			final String userName);

	Integer updateOffenderBookingsTwo(final String lvInOutStatus, final String lvAgyLocId, final Long offenderBookId,
			final String userName);

	Integer updatevOffenderAllSchedules2(final Date upDate, final Integer eventId);

	Integer insertCourtEvents(final VOffenderAllSchedules offIndSchedls,final String userName);

	void insertOffenderIndSchedules(final VOffenderAllSchedules offIndSchedls, final String userName);

	Integer updatecrsEventStatus(final Integer eveId, final String userName);

	Integer updateOffExtmvnts(final VOffenderAllSchedules offExtMov, final String userName);

	Integer updateOffSchStatus(final Integer eventId);

	Integer UpdateInOutStatus(final String lvInOut, final Long pBookId, final String userName);
	
	List<VOffenderAllSchedules> offSchExecuteQuery(final VOffenderAllSchedules searchRecord);
	
	List<OffenderBookings> getOldRecOffBooking( final Long pBookId);
	
	List<OffenderExternalMovements> getOldRecOffExtMov(Long getOffenderBookId,Integer movementSeq);
}

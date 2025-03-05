package net.syscon.s4.inst.movements.housingchanges;

import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.beans.SystemProfilesCommitBean;
import net.syscon.s4.common.beans.VHeaderBlock;
import net.syscon.s4.inst.movements.beans.ReserveBedLocations;
import net.syscon.s4.inst.movements.beans.ReserveBedLocationscommitBean;

/**
 * Interface OmurmresService
 */
public interface OmurmresService {
	List<SystemProfiles> sysPflExecuteQuery(SystemProfiles objSystemProfiles);

	Integer sysPflCommit(SystemProfilesCommitBean commitBean);

	VHeaderBlock cgfkchkResBlResBlVHbF1(VHeaderBlock paramBean);

	List<ReferenceCodes> cgfkResBlRemoveReasonRecordGroup();

	List<ReserveBedLocations> resBlExecuteQuery(ReserveBedLocations objReserveBedLocations);

	Integer resBlCommit(ReserveBedLocationscommitBean commitBean);

	List<ReferenceCodes> cgfkchkResBlResBlRefCod(ReferenceCodes paramBean);

}

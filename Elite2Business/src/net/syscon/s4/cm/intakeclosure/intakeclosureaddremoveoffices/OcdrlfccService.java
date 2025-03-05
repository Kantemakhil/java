package net.syscon.s4.cm.intakeclosure.intakeclosureaddremoveoffices;

import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.OffenderBookingAgyLocs;
import net.syscon.s4.im.beans.OffenderBookingAgyLocsCommitBean;

/**
 * Interface OcdrlfccService
 */
public interface OcdrlfccService {
	List<ReferenceCodes> cgfkchkOffagy1OffagyRefCo(ReferenceCodes paramBean);

	List<ReferenceCodes> cgfkOffagy1DspDescriptionRecordGroup();

	Integer offagyCommit(OffenderBookingAgyLocsCommitBean commitBean);

	List<OffenderBookingAgyLocs> offBkgOnCheckDeleteMaster(OffenderBookingAgyLocs paramBean);

	List<ReferenceCodes> cgfklkpOffagy1OffagyRefCo(ReferenceCodes paramBean);

	List<OffenderBookingAgyLocs> offagyExecuteQuery(OffenderBookingAgyLocs objOffBkgAgyLocs);

	List<OffenderBookingAgyLocs> offagyOnCheckDeleteMaster(OffenderBookingAgyLocs paramBean);

}

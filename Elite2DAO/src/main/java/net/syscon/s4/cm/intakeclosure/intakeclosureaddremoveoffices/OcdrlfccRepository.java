package net.syscon.s4.cm.intakeclosure.intakeclosureaddremoveoffices;

import java.math.BigDecimal;
import java.util.List;

import net.syscon.s4.common.beans.OffenderBookingAgyLocs;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.im.beans.AgencyLocations;

/**
 * Interface OcdrlfccRepository
 */
public interface OcdrlfccRepository {
	String cgfkchkOffagyAgencyLocation(OffenderBookingAgyLocs paramBean);

	List<ReferenceCodes> cgfkOffagy1DspDescriptionRecordGroup();

	List<OffenderBookingAgyLocs> offagyOnCheckDeleteMaster(OffenderBookingAgyLocs paramBean);

	List<ReferenceCodes> cgfklkpOffagy1OffagyRefCo(ReferenceCodes paramBean);

	List<OffenderBookingAgyLocs> offBkgOnCheckDeleteMaster(OffenderBookingAgyLocs paramBean);

	List<AgencyLocations> cgfkchkOffagyAgencyLocatio(AgencyLocations paramBean);

	List<OffenderBookingAgyLocs> offagyExecuteQuery(OffenderBookingAgyLocs objOffBkgAgyLocs);

	List<ReferenceCodes> cgfkchkOffagy1OffagyRefCo(ReferenceCodes paramBean);

	String gettingValidation(OffenderBookingAgyLocs obj);

	Integer countOffenderBookId(Long offenderBookId);

	String getYOffenderBookingAgyLocs(OffenderBookingAgyLocs obj);

	String agyLocIdEndDate(OffenderBookingAgyLocs obj);

	Integer updateOffenderBookingAgyLocs(OffenderBookingAgyLocs obj);

	Integer insertOffenderBookingAgyLocs(OffenderBookingAgyLocs obj);

	BigDecimal alertPreInsertc(Long offenderBookId);

}

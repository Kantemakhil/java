package net.syscon.s4.cm.intakeclosure;

import java.util.Date;
import java.util.List;

import net.syscon.s4.common.beans.OffenderBookingAgyLocs;
import net.syscon.s4.common.beans.OffenderBookingEvent;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.CommunityHeaderStatuses;
import net.syscon.s4.im.beans.ReferenceCodes;

/**
 * Interface OcdaltccRepository
 */
public interface OcdaltccRepository {
	List<AgencyLocations> cgfkchkOffagy1AgencyLocati(AgencyLocations paramBean);

	List<AgencyLocations> cgfkchkOffagyAgencyLocatio(AgencyLocations paramBean);

	List<OffenderBookingAgyLocs> offagyExecuteQuery(OffenderBookingAgyLocs obj);
	
	List<OffenderBookingAgyLocs> offagy1ExecuteQuery(OffenderBookingAgyLocs obj);

	List<AgencyLocations> cgfklkpOffagy1AgencyLocati(AgencyLocations paramBean);

	List<OffenderBookingAgyLocs> cguvchkOffagyPk(OffenderBookingAgyLocs paramBean);

	List<CommunityHeaderStatuses> offenderStatusRecordGroup();

	List<ReferenceCodes> cgfkOffagy1DspDescriptionRecordGroup();

	Integer offagyUpdateOffenderBookingAgyLocs(List<OffenderBookingAgyLocs> obj);

	List<OffenderBookingAgyLocs> offagyOnCheckDeleteMaster(OffenderBookingAgyLocs paramBean);

	List<ReferenceCodes> cgfklkpOffagy1OffagyRefCo(ReferenceCodes paramBean);

	List<AgencyLocations> cgfkOffagy1DspDescription22RecordGroup(Long offenderBookId);

	//Integer offagyInsertOffenderBookingAgyLocs(List<OffenderBookingAgyLocs> obj);
	Integer offagyInsertOffenderBookingAgyLocs(OffenderBookingAgyLocs obj);

	List<ReferenceCodes> cgfkchkOffagy1OffagyRefCo(ReferenceCodes paramBean);

	String cgfkchkOffagyAgencyLocatio(String agyLocId);

	Date eventDate(OffenderBookingAgyLocs searchRecord);

	OffenderBookingEvent evntDate(OffenderBookingAgyLocs searchBean);

	Long eventSeq(Long offenderBookId);

	Integer offagyInsertOffenderBookingAgyLocsEvents(List<OffenderBookingAgyLocs> requestData);

	 List<ReferenceCodes> getOPenCtRsndomainValues();

	
	Date ocdaltccAdditionDateTrigger(Date additionDate,Date additionTime);
}

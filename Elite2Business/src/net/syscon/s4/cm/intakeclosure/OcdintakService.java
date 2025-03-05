package net.syscon.s4.cm.intakeclosure;

import java.util.Date;
import java.util.List;

import net.syscon.s4.common.beans.OffenderBookingEvent;
import net.syscon.s4.common.beans.OffenderBookingEventCommitBean;
import net.syscon.s4.common.beans.OffenderBookings;
import net.syscon.s4.common.beans.OffenderBookingsCommitBean;
import net.syscon.s4.common.beans.OffenderResidence;
import net.syscon.s4.common.beans.OffenderResidenceCommitBean;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.common.beans.SystemProfilesCommitBean;
import net.syscon.s4.common.beans.VHeaderBlock;
import net.syscon.s4.common.beans.VHeaderBlock2;
import net.syscon.s4.common.beans.VHeaderBlock2CommitBean;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.ReferenceCodes;
import net.syscon.s4.im.beans.SystemProfiles;

/**
 * Interface OcdintakService
 */

public interface OcdintakService {

	List<AgencyLocations> cgfklkpOffBkgeOffBkge(AgencyLocations paramBean);

	Integer sysPflCommit(SystemProfilesCommitBean commitBean);

	List<ReferenceCodes> rgIntakeTypeRecordGroup();

	List<VHeaderBlock2> offBkgExecuteQuery(VHeaderBlock2 obj);

	List<ReferenceCodes> cgfkchkOffBkgsOffBkgRef(ReferenceCodes paramBean);

	List<ReferenceCodes> cgfkchkOffBkgeOffBkge(ReferenceCodes paramBean);

	List<OffenderBookings> offBkgsExecuteQuery(OffenderBookings obj);

	List<ReferenceCodes> rgIntakeRsnRecordGroup();

	List<AgencyLocations> cgfkchkOffBkgeOffBkgeAgy(AgencyLocations paramBean);

	List<AgencyLocations> cgfklkpOffBkgeOffBkgeAgy(AgencyLocations paramBean);

	List<OffenderBookingEvent> offBkgeExecuteQuery(OffenderBookingEvent obj);

	List<AgencyLocations> rgFromAgyLocRecordGroup();

	List<OffenderResidence> reportInExecuteQuery(OffenderResidence obj);

	List<ReferenceCodes> cgfklkpOffBkgeOffBkge(ReferenceCodes paramBean);

	Integer offBkgCommit(VHeaderBlock2CommitBean commitBean);

	Integer offBkgeCommit(OffenderBookingEventCommitBean commitBean);

	Integer reportInCommit(OffenderResidenceCommitBean commitBean);

	List<ReferenceCodes> cgfklkpOffBkgeOffBkgeRef(ReferenceCodes paramBean);

	List<AgencyLocations> cgfkchkOffBkgeOffBkge(AgencyLocations paramBean);

	List<StaffMembers> cgfkchkOffBkgsOffBkgStaf(StaffMembers paramBean);

	List<AgencyLocations> rgToAgyLocRecordGroup(String caseloadId, Long offenderId);

	List<ReferenceCodes> cgfkchkOffBkgeOffBkgeRef(ReferenceCodes paramBean);

	List<SystemProfiles> sysPflExecuteQuery(SystemProfiles objSystemProfiles);

	Integer offBkgsCommit(OffenderBookingsCommitBean commitBean);

	Date checkPrevBooking(OffenderBookings obj);

	String wNewBlockInstanceintakeCase();

	Integer checkForActiveBooking(VHeaderBlock obj);

	OffenderBookingEvent setNewContactFlag(OffenderBookings searchBean);

	OffenderBookingEvent getDefaultIntakeValues(String intakeType, String intakeReason);

	Integer intakeCaseMultiple(VHeaderBlock searchBean);

	String getDspDescription();

	String intakeCaseactBkgExistFlag(VHeaderBlock searchBean);

	String getTrustValues(String intakeTrust, String client);

	String toAgyLoc(OffenderBookingEvent searchBean);

	Integer oldContact(OffenderBookingEvent object);

	String getProfileValueDisabled();

	String getProfileTrustValueDisabled();

	// String getProfilevalue();
	//
	// Integer setNewcontactcheckForActiveBooking(VHeaderBlock obj);

	// String createBookingLocationRecExistCur(OffenderBookings obj);
	
	Date getBackdatedAdmissionDate();
	
	String caclucateSentStatus(String offenderBookId);

}

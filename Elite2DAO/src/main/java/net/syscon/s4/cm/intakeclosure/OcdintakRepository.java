package net.syscon.s4.cm.intakeclosure;

import java.util.Date;
import java.util.List;

import net.syscon.s4.cf.deductions.beans.FeeAccountProfiles;
import net.syscon.s4.common.beans.OdynfrmSubmitDataBean;
import net.syscon.s4.common.beans.OffenderBookingEvent;
import net.syscon.s4.common.beans.OffenderBookings;
import net.syscon.s4.common.beans.OffenderCustodyStatus;
import net.syscon.s4.common.beans.OffenderResidence;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.common.beans.VHeaderBlock;
import net.syscon.s4.common.beans.VHeaderBlock2;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.ReferenceCodes;
import net.syscon.s4.im.beans.SystemProfiles;
import net.syscon.s4.inst.legalscreens.maintenance.bean.LegalCustodyStatuses;

/**
 * Interface OcdintakRepository
 */

public interface OcdintakRepository {

	List<ReferenceCodes> rgIntakeTypeRecordGroup();

	List<VHeaderBlock2> offBkgExecuteQuery(VHeaderBlock2 obj);

	List<OffenderBookings> offBkgsExecuteQuery(OffenderBookings obj);

	List<StaffMembers> cgfkchkOffBkgsOffBkgStaf(StaffMembers paramBean);

	List<ReferenceCodes> rgIntakeRsnRecordGroup();

	List<ReferenceCodes> cgfkchkOffBkgsOffBkgRef(ReferenceCodes paramBean);

	List<ReferenceCodes> cgfklkpOffBkgeOffBkgeRef(ReferenceCodes paramBean);

	List<OffenderBookingEvent> offBkgeExecuteQuery(OffenderBookingEvent obj);

	List<AgencyLocations> rgFromAgyLocRecordGroup();

	List<OffenderResidence> reportInExecuteQuery(OffenderResidence obj);

	Integer reportInInsertOffenderResidences(List<OffenderResidence> obj);

	List<AgencyLocations> cgfklkpOffBkgeOffBkge(AgencyLocations paramBean);

	List<ReferenceCodes> cgfkchkOffBkgeOffBkgeRef(ReferenceCodes paramBean);

	List<ReferenceCodes> cgfklkpOffBkgeOffBkge(ReferenceCodes paramBean);

	List<AgencyLocations> cgfkchkOffBkgeOffBkge(AgencyLocations paramBean);

	List<ReferenceCodes> cgfkchkOffBkgeOffBkge(ReferenceCodes paramBean);

	List<AgencyLocations> rgToAgyLocRecordGroup(String caseloadId, Long offenderId);

	List<AgencyLocations> cgfklkpOffBkgeOffBkgeAgy(AgencyLocations paramBean);

	List<AgencyLocations> cgfkchkOffBkgeOffBkgeAgy(AgencyLocations paramBean);

	List<SystemProfiles> sysPflExecuteQuery(SystemProfiles obj);

	Integer offBkgeInsertOffenderBookingEvents(List<OffenderBookingEvent> obj);

	String getAgyStatus(String value);

	Date checkPrevBooking(OffenderBookings paramBean);

	String createBookingLocationRecExistCur(OffenderBookingEvent paramBean);

	Integer updateOffAgyLoc(OffenderBookingEvent obj);

	String wNewBlockInstanceintakeCase();

	Integer checkForActiveBooking(VHeaderBlock obj);

	String tagBookingCommActiveBookingExists(VHeaderBlock obj);

	String getProfileValue();

	String getProfileValueConstants(String string, String stringOne);

	Integer getDefaultIntakeValuesCountCur(String intakeType);

	String getRefCodeValues(String intakeType);

	Integer intakeCaseMultipleActBkgs(VHeaderBlock searchBean);

	Integer intakeCaseMultipleNumOfComAgy(VHeaderBlock searchBean);

	String getDspDescription();

	String intakeCaseactBkgExistFlag(VHeaderBlock obj);

	String toAgyLoc(OffenderBookingEvent obj);

	Integer instOffBookAgyLoc(OffenderBookingEvent obj);

	String getNvlProfileVal();

	String oldContactCheckBookingCur(OffenderBookingEvent object);

	String oldContactLvYearCur();

	Integer oldContactGetStaffId(String user);

	Integer oldContactPbookCount(OffenderBookingEvent object);

	Integer oldContactGetEventSeq(OffenderBookingEvent object);

	Integer updateOffenderBookings(String toAgyLocId, Integer staffId, String pCommStatus, String lvBookingType,
			Long offenderBookId);

	String createBookingLocationsProfileval();

	String oidadmisGetNewBookingNo();

	Integer getNewBookId();

	Integer getLatestBooking(OffenderBookingEvent object);

	Integer instOffenderBooking(OffenderBookingEvent object);

	Integer copyBookingData(OffenderBookingEvent offenderBookingEvent);

	Integer processOcdintakTrust(OffenderBookingEvent object);

	OffenderBookingEvent onCommitTwo(OffenderBookingEvent object);

	String onCommitcommunityTrustCaseloadId(String caseloadId);

	Integer insOffSchedule(OffenderResidence object);

	String getProfileValueDisabled();

	String getProfileTrustValueDisabled();

	String oldContactGetLvBookingNo(String obj);

	String newContactCur(String userId);

	Integer newContactCheckBookingCur(OffenderBookingEvent obj);

	String getCommStatusOne();

	String getCommStatusTwo();
	
    List<ReferenceCodes>getBookingEventCode(final String dspDescription) ;
    
    Integer updateFeeAccounts(List<FeeAccountProfiles> feeUpdatelist);

	Integer offBkgeInsertOffenderBookingEvents(OffenderBookingEvent obj);

    
    String getBackdatedAdmissionDate();

	ReferenceCodes getBillableFlag(String supStatus);
	
	String getCustodyStatus();
	
	Integer insertCustodyStatus(OffenderCustodyStatus offenderCustodyStatus); 
	
	Integer updateCustodyStatus(List<OffenderCustodyStatus> offenderCustodyStatusList);
	
	byte[] getSentences(String custFormIdentifier);
	
	List<String> getCustodyStatusForOrder(String orderStatus, String type);
	
	List<LegalCustodyStatuses> getLegalCustodyStatuses(List<String> orderStatusList);
	
	String getOldOffenderBookId(OffenderBookingEvent object);
	
	List<OdynfrmSubmitDataBean> getLegalSummaryData(String formIdentifier);
}

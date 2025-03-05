package net.syscon.s4.inst.visitsmanagement;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import net.syscon.s4.common.beans.AgencyVisitTimes;
import net.syscon.s4.common.beans.Images;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.inst.automatedcounts.beans.AgencyCounts;
import net.syscon.s4.inst.visitsmanagement.beans.OffenderVisitVisitors;
import net.syscon.s4.inst.visitsmanagement.beans.VOffenderVisitVisitors;
import net.syscon.s4.inst.visitsmanagement.beans.VOffenderVisits;

/**
 * Interface OidvisitRepository
 */
public interface OidvisitRepository {
	Integer offVstPersInsertVOffenderVisitVisitors(List<VOffenderVisitVisitors> list);

	List<VOffenderVisits> rgVisitTimeSlotsRecordGroup(String visitAgyLocId, String agyLocId, String visitDate);

	List<ReferenceCodes> rgMoveCancRsRecordGroup();

	Integer offVstInsertVOffenderVisits(List<VOffenderVisits> list);

	Integer offVstOffUpdateOffenderVisitVisitors(List<OffenderVisitVisitors> list);

	List<ReferenceCodes> rgVisCompleteRecordGroup();

	List<Images> imagesVisitorsExecuteQuery(Images objImages);

	List<VOffenderVisitVisitors> offVstPersExecuteQuery(VOffenderVisitVisitors obj);

	Integer offVstOffInsertOffenderVisitVisitors(List<OffenderVisitVisitors> list);

	Integer offVstUpdateVOffenderVisits(List<VOffenderVisits> list);

	Integer offVstPersUpdateVOffenderVisitVisitors(List<VOffenderVisitVisitors> list);

	List<ReferenceCodes> rgVisitTypeRecordGroup();

	List<VOffenderVisits> offVstExecuteQuery(VOffenderVisits obj);

	Integer offVstOffDeleteOffenderVisitVisitors(List<OffenderVisitVisitors> list);

	Integer offVstPersDeleteVOffenderVisitVisitors(List<VOffenderVisitVisitors> list);

	List<OffenderVisitVisitors> offVstOffExecuteQuery(OffenderVisitVisitors obj);

	String getSupLevel(BigDecimal offBookId);

	VOffenderVisits doVisitCal(String hAgyLocId, BigDecimal hOffenderBookId, String supLevel, String visitType,
			Date visitDate);

	String classPending();

	VOffenderVisitVisitors populateVisitorDetails(BigDecimal offenderBookId, BigDecimal personId, Date visitDate);

	BigDecimal getRootOffenderId(BigDecimal offBookId);

	OffenderVisitVisitors populateOffenderDetails(BigDecimal hOffenderBookId, BigDecimal rootOffenderId,
			Date visitDate);

	BigDecimal getNxtOffVisitId();

	BigDecimal getEventId();

	VOffenderVisits getTimeSlotDetails(Integer valueOf);

	Integer insertIntoOffenderVisits(List<VOffenderVisits> insertList);

	Integer offvstInsertOffenderVisitVisitors(List<VOffenderVisits> insertList);

	Integer insertOffenderVisitVisitor(VOffenderVisitVisitors insertList);

	BigDecimal getOffenderBookId(String offenderIdDisplay, String userId);

	List<Images> imagesVisitingOffExecuteQuery(Images searchRecord);

	String duplicateVisit(VOffenderVisits searchRecord);

	String duplicateVisitQueryTwo(VOffenderVisits searchRecord);

	String offenderVisitLimit(String agyLocId, String supLevel);

	String offenderVisitLimitForVisitType(String agyLocId, String supLevel, String visitType);

	Integer isAuthorisedPerson(Integer personId, Integer offenderBookId);

	BigDecimal getRootOffenderIdFromBook(Integer offenderBookId);

	Integer isAuthorisedOffender(BigDecimal contactRootOffId, Integer offenderBookId);

	Integer duplicateVisitorPerson(VOffenderVisitVisitors searchBean);

	BigDecimal overlapVisit(VOffenderVisitVisitors searchBean);

	BigDecimal overlapVisitBookId(BigDecimal offenderBookId, BigDecimal offenderVisitId, Date visitDate, Date startTime,
			Date endTime);

	Integer totalBooked(VOffenderVisits searchBean);

	Integer findMaxVisitorType(VOffenderVisits searchBean);

	Integer cancelVisitors(VOffenderVisits visitsPostUpdate);

	Integer completeVisitors(VOffenderVisits visitsPostUpdate);

	Integer findAdultAge();

	AgencyCounts findCSlots(BigDecimal agencyVisitSlotId);

	AgencyCounts findCVis(VOffenderVisits searchBean);

	AgencyCounts findCAll(VOffenderVisits searchBean);

	Integer duplicateVisitorOffender(OffenderVisitVisitors searchBean);

	Integer getOffenderRestrictions(VOffenderVisits searchBean);

	BigDecimal getOffenderId(String offenderIdDisplay);

	VOffenderVisits getOnVisitMaster(BigDecimal offenderVisitId, BigDecimal offenderBookId);

	Integer offVstPersMaster(BigDecimal offenderVisitId, BigDecimal visitOffBookId);

	Integer offVstOffMaster(BigDecimal offenderVisitId);

	VOffenderVisits getOnVisitMasterFromVisitingOffenders(BigDecimal offenderVisitId, BigDecimal offenderBookId);

	Integer getVisitorRestrictions(BigDecimal personId, BigDecimal offenderBookId, Date visitDate);

	String getlOverlapDetails(BigDecimal offBookId);

	BigDecimal overlapVisitPerId(Integer personId, BigDecimal offenderVisitId, Date visitDate, Date startTime,
			Date endTime);

	Integer checkContactActive(Integer offenderBookId, Integer personId);

	BigDecimal getNextOffVisitVisitorId();

	List<AgencyVisitTimes> oidvisitCheckListEntry();

	String getOverLapVisitor(Integer personId, BigDecimal offenderVisitId, Date visitDate, Date startTime, Date endTime,
			BigDecimal pOffenderBookId);

	String chkVisitConflicts(VOffenderVisits insertBean);
	
	List<VOffenderVisits> checkNonAssocationDetails(BigDecimal offenderBookId, Date visitDate,Integer locationId);

	String checkIeplevel();

	String checkIepSecConfig(String agyLocId);

	String getOffenderIepConfigData(Long offenderBookId);

	String offenderVisitLimitForVisitTypeForIpLevel(String agyLocId, String iepLevel, String visitType);

	Date getCycleEndForOffender(String code ,Integer offenderBookId);
	
	Integer getMaxVisitors(VOffenderVisits vOffVisitrs);
	
	List<ReferenceCodes> rgVisitTypeRecordGroup(String agyLocId, Long offenderBookId);
	
	Integer OidvisitAttendedChanges(BigDecimal offenderVisitId);
	
	Integer updateOffenderVisits(BigDecimal offenderVisitId,  String userName);
	
	List<ReferenceCodes> getRgVisitType(String agyLocId, Long offenderBookId,String caseLoadType,String supLvlType);

	String getSupLevel(Long offenderBookId, String caseLoadType);

	List<ReferenceCodes> getRgVisitTypesOff(String agyLocId, Long offenderBookId, String caseLoadType);

	

}

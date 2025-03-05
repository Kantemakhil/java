package net.syscon.s4.inst.visitsmanagement;

import java.util.List;

import net.syscon.s4.common.beans.AgencyVisitTimes;
import net.syscon.s4.common.beans.Images;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.inst.visitsmanagement.beans.OffenderVisitVisitors;
import net.syscon.s4.inst.visitsmanagement.beans.OffenderVisitVisitorsCommitBean;
import net.syscon.s4.inst.visitsmanagement.beans.VOffenderVisitVisitors;
import net.syscon.s4.inst.visitsmanagement.beans.VOffenderVisitVisitorsCommitBean;
import net.syscon.s4.inst.visitsmanagement.beans.VOffenderVisits;
import net.syscon.s4.inst.visitsmanagement.beans.VOffenderVisitsCommitBean;
import net.syscon.s4.inst.visitsmanagement.beans.ValidateVisitorBean;

/**
 * Interface OidvisitService
 */
public interface OidvisitService {
	List<VOffenderVisitVisitors> offVstPersExecuteQuery(VOffenderVisitVisitors obj);

	List<VOffenderVisits> rgVisitTimeSlotsRecordGroup(String parentField);

	List<ReferenceCodes> rgMoveCancRsRecordGroup();

	Integer offVstPersCommit(VOffenderVisitVisitorsCommitBean commitBean);

	Integer offVstOffCommit(OffenderVisitVisitorsCommitBean commitBean);

	List<VOffenderVisits> offVstExecuteQuery(VOffenderVisits obj);

	Integer offVstCommit(VOffenderVisitsCommitBean commitBean);

	List<ReferenceCodes> rgVisCompleteRecordGroup();

	List<Images> imagesVisitorsExecuteQuery(Images objImages);

	List<OffenderVisitVisitors> offVstOffExecuteQuery(OffenderVisitVisitors obj);

	List<Images> imagesVisitingOffExecuteQuery(Images searchBean);

	VOffenderVisits visitTypeValidateQuery(VOffenderVisits searchBean);

	Integer duplicateVisit(VOffenderVisits searchBean);

	VOffenderVisits endTimeValidateQuery(VOffenderVisits searchBean);

	String validateVisitor(ValidateVisitorBean bean);

	Integer visitPerPreInsert(VOffenderVisitVisitors searchBean);

	Integer checkVisitorLimit(VOffenderVisits searchBean);

	Integer recheckTimeSlots(VOffenderVisits searchBean);

	String visitOffPreInsert(OffenderVisitVisitors searchBean);

	Integer getOffenderRestrictions(VOffenderVisits searchBean);

	VOffenderVisits getOffenderDetails(String offenderIdDisplay, String userName);

	Integer isAuthorisedOffender(Integer hoffenderBookId, Integer voffenderBookId);

	Integer isAuthorisedPerson(Integer personId, Integer offenderBookId);

	Integer getVisitorRestrictions(VOffenderVisitVisitors searchBean);

	String overlapVisitForVisitors(VOffenderVisits searchBean);

	Integer checkContactActive(Integer offenderBookId, Integer personId);

	List<AgencyVisitTimes> oidvisitCheckListEntry();

	VOffenderVisits chkVisitConflicts(VOffenderVisits searchBean);
	
	String checkNonAssocationDetails(VOffenderVisitsCommitBean commitBean);
	
	Integer getMaxVisitors(VOffenderVisits vOffVisitrs);
	
	List<ReferenceCodes> rgVisitTypeRecordGroup(String agyLocId, Long offenderBookId ,String iepSecLevels ,String caseLoadType);

}

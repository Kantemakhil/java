package net.syscon.s4.iwp;

import java.math.BigDecimal;
import java.util.List;

import net.syscon.s4.cm.casemanagement.maintenance.beans.EventMeasures;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.Areas;
import net.syscon.s4.im.beans.CourseActivities;
import net.syscon.s4.im.beans.OffenderCaseNotes;
import net.syscon.s4.im.beans.OffenderNonAssociations;
import net.syscon.s4.inst.schedules.bean.OffenderIndSchedules;
import net.syscon.s4.inst.schedules.bean.VOffenderAllSchedules;
import net.syscon.s4.inst.schedules.bean.VOffenderAllSchedules2;
import net.syscon.s4.inst.workflow.maintenance.beans.StaffLocationRoles;

/**
 * Interface OcdclogsRepository
 */
public interface OcdclogsRepository {
	List<ReferenceCodes> rgCasenoteTypeRecordGroup(final String caseloadType,String username);

	List<ReferenceCodes> rgCasenoteSubtypeRecordGroup(final String caseNoteType,String username,String caseloadType);

	List<OffenderCaseNotes> offNotesExecuteQuery(OffenderCaseNotes objOffenderCaseNotes);

	Integer validateNoteTypeSubType(OffenderCaseNotes objOffenderCaseNotes);

	List<OffenderCaseNotes> rgCasenotestaffNameRecordGroup(String caseloadId, String agyLocId, Long offenderBookId);

	List<VOffenderAllSchedules> schExecuteQuery(final BigDecimal OffenderBookId);

	Integer offNotesInsertOffenderCaseNotes(List<OffenderCaseNotes> lstOffenderCaseNotes);

	List<StaffLocationRoles> rgStaffnameRecordGroup();

	public Integer getcaseNoteId();

	public Integer getStaffId(String userName);

	public Integer postInsert(OffenderCaseNotes obj);

	public Integer checkPrivilegeExists(String lvRoleCode,String userName);

	public Integer okToDeleteRecord(Integer cni);

	Integer offIndSchInsert(List<OffenderIndSchedules> insertList);

	public BigDecimal offSchEventId();

	BigDecimal inChargeStaffId(String StaffName);

	List<VOffenderAllSchedules> offSchExecuteQuery(VOffenderAllSchedules objVOffenderAllSchedules);

	List<ReferenceCodes> rgScheduleSubTypeRecordGroup(final String eventTypeDesc);

	Integer offNotesDeleteOffenderCaseNotes(OffenderCaseNotes obj);

	List<ReferenceCodes> rgScheduleTypeRecordGroup();

	List<OffenderCaseNotes> offBkgOnCheckDeleteMaster(OffenderCaseNotes paramBean);

	List<ReferenceCodes> rgnoteSourceRecordGroup();

	Integer offSchDeleteVOffenderAllSchedules(List<VOffenderAllSchedules> lstVOffenderAllSchedules);

	List<VOffenderAllSchedules> offBkgOnCheckDeleteMaster(VOffenderAllSchedules paramBean);

	List<ReferenceCodes> rgEventOutcomeRecordGroup(String eventType, String eventSubType, String nbtUpdOutcomeFlag);

	List<AgencyLocations> rgLocationRecordGroup();

	Long checkForSheduleConflict(VOffenderAllSchedules offsch);

	Integer adjustUa(VOffenderAllSchedules offsch);

	BigDecimal offSchPostInsert(BigDecimal eventId);

	Integer offNotesUpdateOffenderCaseNotes(List<OffenderCaseNotes> lstOffenderCaseNotes);

	Integer insNoteSchAssociation(BigDecimal OffenderBookId, BigDecimal eventId);

	Integer updateSchedule1(VOffenderAllSchedules searchBean);

	Integer updateSchedule2(VOffenderAllSchedules searchBean);

	Integer delNoteSchAssociation(VOffenderAllSchedules searchBean);

	Integer okToDeleteRecordDelOperation(VOffenderAllSchedules searchBean);

	Integer deleteSchedule(VOffenderAllSchedules searchBean);

	Integer deleteVirtualSchedule(VOffenderAllSchedules searchBean);

	StaffMembers lvLoginUserStaffName(String userName) ;

	String getModuleName(OffenderCaseNotes offNotes);
	
	List<Areas> rglovOutComeRecordGroup();

	String getOutcomeUpdatedFlag(VOffenderAllSchedules vOffenderAllSchedules);

	VOffenderAllSchedules2 getOldData(Long EventId);
	Integer getStaffIdOne(Integer caseNoteId);

	String getcaseNoteTextData(OffenderCaseNotes caseNotesObj);
	
	List<OffenderIndSchedules> checkOffenderScheduleConflicts(OffenderNonAssociations offNa,VOffenderAllSchedules vOffSch);
	
	List<EventMeasures> getEmailSmsFlag(VOffenderAllSchedules beanOne);
	
	String getCancelFlag(VOffenderAllSchedules beanOne);
	
	String checkCasenoteSubType(String caseNoteType, String caseNoteCode, String userName);
	
	List<AgencyLocations> getAllCorporates();

	String getAgencyLocationDescription(String agyLocId);

}

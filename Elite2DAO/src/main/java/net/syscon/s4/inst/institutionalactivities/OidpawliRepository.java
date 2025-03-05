package net.syscon.s4.inst.institutionalactivities;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.VHeaderBlock;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.CourseActivities;
import net.syscon.s4.im.beans.OffenderProgramProfiles;
import net.syscon.s4.im.beans.Offenders;
import net.syscon.s4.inst.institutionalactivities.beans.VPrisonActivities;

/**
 * Interface OidpawliRepository
 */
public interface OidpawliRepository {
	List<AgencyLocations> rgEstablishmentRecordGroup(final String agyLocId);

	Integer waitlistUpdateOffenderProgramProfiles(final List<OffenderProgramProfiles> list, String user);

	List<OffenderProgramProfiles> checkAssignConflict(final OffenderProgramProfiles paramBean);

	List<VPrisonActivities> rgServicesRecordGroup(final String ageLocId);

	List<OffenderProgramProfiles> waitlistExecuteQuery(final OffenderProgramProfiles opf);

	List<CourseActivities> rgActDescRecordGroup(final String programId, final String agyLocId);

	Integer waitlistInsertOffenderProgramProfiles(final List<OffenderProgramProfiles> list);

	List<ReferenceCodes> rgDecisionRecordGroup(final String systemMode);

	List<VHeaderBlock> checkConflict(final VHeaderBlock paramBean);

	List<ReferenceCodes> rgPriorityRecordGroup();

	List<ReferenceCodes> rgReasonRecordGroup();

	Integer waitlistDeleteOffenderProgramProfiles(final List<OffenderProgramProfiles> list);

	String getRejectName(final String rejectReasonCode);

	Long ocdxprogOffPrgrefId();

	Date checkAssignConflictLvDate(Long offenderBookId, Long programId);

	Date checkAssignConflictLvDateOne(Date offenderStartDate, Date offenderEndDate, Long offenderBookId,
			Long programId);

	List<Offenders> getOffDetails(OffenderProgramProfiles bean);

	List<Offenders> getNaPrgSrv(OffenderProgramProfiles bean);

	List<Offenders> getStgNaPrgSrv(OffenderProgramProfiles bean);

	OffenderProgramProfiles checkConflictDateLv(Date lvDate, OffenderProgramProfiles paramBean) throws ParseException;

	Map<String, Object> chkAllocated(OffenderProgramProfiles searchRecord);

	Long getOffenderId(Long offenderBookId, String userId);

	Long futureDays(Date offenderStartDate);

	Map<String, Object> bulkUpdate(OffenderProgramProfiles inputObj);

	Offenders getOffernderDataWithOffenderBookId(final BigDecimal offenderBookId);
	
	List<Offenders> getNonAssocationOffenderDetailsForInd(OffenderProgramProfiles bean,List<Long> offenderIdsList);
	
	List<Offenders> getNonAssocationOffenderDetailsForGang(OffenderProgramProfiles bean,List<Long> offenderIdsList);

}

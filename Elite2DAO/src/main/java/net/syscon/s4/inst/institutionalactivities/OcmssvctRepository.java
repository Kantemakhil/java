package net.syscon.s4.inst.institutionalactivities;

import java.math.BigDecimal;
import java.util.List;

import net.syscon.s4.cm.teamsworkflow.beans.TeamMembers;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.im.beans.CourseActivityParties;

/**
 * Interface OcmssvctRepository
 */
public interface OcmssvctRepository {
	List<TeamMembers> rgTeamMembersRecordGroup();

	List<ReferenceCodes> rgStaffNameCommRecordGroup(Long providerId);

	Integer crsActPtyUpdateCourseActivityParties(List<CourseActivityParties> list);

	Integer crsActPtyInsertCourseActivityParties(List<CourseActivityParties> list);

	Object extConPreInsert();

	List<CourseActivityParties> crsActPtyExecuteQuery(CourseActivityParties object);

	CourseActivityParties crsActPtyDeleteCourseActivityParties(List<CourseActivityParties> list);

	List<ReferenceCodes> rgStaffNameInstRecordGroup(String caseloadType, String providerPartyCode);

	List<ReferenceCodes> rgStaffNameInstProgRecordGroup(String providerPartyCode, Long programId);

	List<ReferenceCodes> rgStaffNameCommProgRecordGroup(Long providerPartyId, Long programId);

	Object crsActPtyPreInsert();

	List<StaffMembers> getStaffMemberRecord(BigDecimal staffId);

	Long getCrtPartyId();

	List<CourseActivityParties> extConExecuteQuery(CourseActivityParties objCourseActivityParties);

}

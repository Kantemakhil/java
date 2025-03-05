package net.syscon.s4.inst.institutionalactivities;

import java.util.List;

import net.syscon.s4.cm.teamsworkflow.beans.TeamMembers;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.im.beans.CourseActivityParties;
import net.syscon.s4.im.beans.CourseActivityPartiesCommitBean;

/**
 * Interface OcmssvctService
 */
public interface OcmssvctService {
	List<TeamMembers> rgTeamMembersRecordGroup();

	List<ReferenceCodes> rgStaffNameCommRecordGroup(Long providerId);

	Object crsActPtyPreInsert();

	List<CourseActivityParties> crsActPtyCommit(CourseActivityPartiesCommitBean commitBean);

	List<CourseActivityParties> crsActPtyExecuteQuery(CourseActivityParties object);

	List<CourseActivityParties> extConExecuteQuery(CourseActivityParties object);

	Object extConPreInsert();

	List<ReferenceCodes> rgStaffNameInstRecordGroup(String caseloadType, String providerPartyCode);

	List<ReferenceCodes> rgStaffNameInstProgRecordGroup(String providerPartyCode, Long programId);

	List<ReferenceCodes> rgStaffNameCommProgRecordGroup(Long providerPartyId, Long programId);

}

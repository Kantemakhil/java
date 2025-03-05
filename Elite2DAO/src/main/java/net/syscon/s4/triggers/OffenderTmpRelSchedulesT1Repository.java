package net.syscon.s4.triggers;

import java.util.Date;

public interface OffenderTmpRelSchedulesT1Repository {
	OffenderTmpRelSchedules getOffenderTmpRelSchedules(Long offenderBookId, Long sessionId);

	String lCheckExistCur(Long offenderBookId, Date pReleaseDate);

	String lSexCheckExistCur(Long offenderBookId);

	Long trgEventIdSeq();

	Integer insert(OffenderAssocPEventNotifs offeAssPEvntNoti);

	Integer update(OffenderAssocPEventNotifs offeAssPEvntNoti);
}

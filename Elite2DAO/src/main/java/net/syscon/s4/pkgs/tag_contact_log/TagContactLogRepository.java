package net.syscon.s4.pkgs.tag_contact_log;

import java.math.BigDecimal;
import java.util.List;

import net.syscon.s4.common.beans.OffenderCaseNoteSent;
import net.syscon.s4.common.beans.OffenderIndSchSent;
import net.syscon.s4.inst.legals.beans.OffenderSentences;
import net.syscon.s4.inst.schedules.bean.VOffenderAllSchedules;

public interface TagContactLogRepository {

	Integer deleteVirtualSchedule(final VOffenderAllSchedules vOffAllSche);

	List<OffenderSentences> activeSentaCur(final Long offenderBookId);

	Integer insertOffenderCaseNoteSen(final OffenderCaseNoteSent bean);

	Integer offenderIndSchSentens(final OffenderIndSchSent oofIndSch);

	Integer updateScheduleForPhysicalRecord(final VOffenderAllSchedules offsch);

	Integer updateScheduleForVirtualRecords(final VOffenderAllSchedules offsch);

	Integer deleteNoteschAss(final Long pOffenderBookId, final BigDecimal pSchId,String modifyUserId);

	String getOutcomeFlagCur(final String pEventType, final String pEventSubType, final String pEventOutcome);

	String getModuleNameFmWork(final String pContactType, final String pContactSubType);

}
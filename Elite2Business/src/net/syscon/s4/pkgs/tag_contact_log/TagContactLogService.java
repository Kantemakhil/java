package net.syscon.s4.pkgs.tag_contact_log;

import java.math.BigDecimal;
import java.util.Map;

import net.syscon.s4.inst.schedules.bean.VOffenderAllSchedules;

public interface TagContactLogService {

	Integer deleteVirtualSchedule(final VOffenderAllSchedules lstVOffenderAllSchedules2);

	Integer insNoteSchAssociation(final Long offenderBookId, final Long caseNoteId, final Long schId,
			final String userName);

	Integer UpdateSchedule(final VOffenderAllSchedules offsch);

	Integer delNoteSchAssociation(final Long pOffenderBookId, final BigDecimal pSchId,String modifyUserId);

	String checkUpdOutcomeLogValid(final VOffenderAllSchedules offSch);

	String getModuleName(final String pContactType, final String pContactSubType);
}

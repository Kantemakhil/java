package net.syscon.s4.triggers;

import java.util.List;

import net.syscon.s4.im.beans.OffenderCaseNotes;
import net.syscon.s4.inst.schedules.bean.OffenderIndSchedules;

public interface OffenderIndSchedulesT4Repository {
	OffenderIndSchedules getOffenderIndSchedules(Integer eventId);

	OffenderCaseNotes getCasenoteDetailsC(Integer eventId);

	Integer insert(List<OffenderCaseNotes> offeCaseNotesList);

	Integer caseNoteIdNextval();
}

package net.syscon.s4.globaloffenderrecords;

import java.math.BigDecimal;
import java.util.List;

import net.syscon.s4.im.beans.OffenderNonAssociations;
import net.syscon.s4.inst.schedules.bean.OffenderIndSchedules;
import net.syscon.s4.inst.schedules.bean.VOffenderAllSchedules;

public interface OcunawrnRepository {
	
	public List<OffenderNonAssociations> getNonAssociationsOffenders(final BigDecimal offenderBookId);
	
	public List<OffenderIndSchedules> checkOffenderScheduleConflicts(OffenderNonAssociations offNa,VOffenderAllSchedules vOffSch);

}

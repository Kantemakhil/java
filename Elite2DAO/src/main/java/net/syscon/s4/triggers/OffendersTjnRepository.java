package net.syscon.s4.triggers;

import net.syscon.s4.im.beans.Offenders;

public interface OffendersTjnRepository {
	
	Integer insertOffendersJn(OffendersJn obj);

	Offenders offendersExecuteQuery(final Long offenderId);
}

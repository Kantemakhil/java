package net.syscon.s4.triggers;

import net.syscon.s4.im.beans.Offenders;

public interface OmtoffsrcRepository {
	
	Offenders offendersExecuteQuery(final Long offenderId);
}

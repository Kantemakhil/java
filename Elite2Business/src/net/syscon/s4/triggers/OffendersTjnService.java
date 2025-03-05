package net.syscon.s4.triggers;

import java.util.List;

import net.syscon.s4.im.beans.Offenders;

public interface OffendersTjnService {
	
	void offendersTjn(final List<Offenders> offenderList,final List<Offenders> oldUpdatedList, final String operation );
}
